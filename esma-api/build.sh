#!/usr/bin/env bash
set -e

INCREMENTAL=true

MAVEN_S2I_ARTIFACT_DIRS="\"target/quarkus-app\""
S2I_SOURCE_DEPLOYMENTS_FILTER="\"app lib quarkus quarkus-run.jar\""
MAVEN_S2I_GOALS="\"package test\""
JAVA_APP_JAR="\"/deployments/quarkus-run.jar\""
ENVIRONMENTS="MAVEN_S2I_ARTIFACT_DIRS,S2I_SOURCE_DEPLOYMENTS_FILTER,MAVEN_S2I_GOALS,JAVA_APP_JAR"

builder=$(buildah from registry.access.redhat.com/ubi8/openjdk-11)

buildah add --chown jboss:0 $builder ./ /tmp/src

if [ "$INCREMENTAL" = "true" ]; then

    if [ -f "./artifacts.tar" ]; then
    echo "restoring artifacts"
    buildah add --chown jboss:0 $builder ./artifacts.tar /tmp/artifacts
    fi

fi

COMMAND=""

if [ -n "$ENVIRONMENTS" ]; then

    COMMAND+="buildah config "

    IFS=','; for word in $ENVIRONMENTS; do COMMAND+="--env $word=${!word} "; done

    COMMAND+='$builder'

fi

if [ ! -z "$COMMAND" ]; then
    echo "executing $COMMAND"

    eval "$COMMAND"
fi

buildah config --cmd /usr/local/s2i/run $builder

if [ -x ".s2i/bin/assemble" ]; then
    echo "Using assemble from .s2i"
    buildah run $builder -- /tmp/src/.s2i/bin/assemble
else
    buildah run $builder -- /usr/local/s2i/assemble
fi

if [ "$INCREMENTAL" = "true" ]; then

    echo "saving artifacts"
    if [ -f "./artifacts.tar" ]; then
    rm ./artifacts.tar
    fi

    buildah run $builder -- /bin/bash -c 'if [ -x "/usr/local/s2i/save-artifacts" ]; then /usr/local/s2i/save-artifacts ; fi' > ./artifacts.tar

fi


# Build lightweight image
runner=$(buildah from registry.access.redhat.com/ubi8/openjdk-11)

buildah copy --chown jboss:0 --from $builder $runner /deployments/ /deployments/

buildah commit $runner esma-api

buildah rm $builder
