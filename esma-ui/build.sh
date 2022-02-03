#!/usr/bin/env bash
set -e

# BUILD IMAGE
builder=$(buildah from registry.redhat.io/jboss-webserver-5/jws56-openjdk11-openshift-rhel8)

buildah add --chown jboss:0 $builder ./ /tmp/src

if [ "$INCREMENTAL" = "true" ]; then

echo "incremental mode on"

if [ -f "/tmp/artifacts.tar" ]; then
 buildah add --chown jboss:0 $builder /tmp/artifacts.tar /tmp/artifacts
fi

fi

#ENVIRONMENTS="LOGGING_SCRIPT_DEBUG"

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

buildah run $builder -- /bin/bash -c 'if [ -x "/usr/local/s2i/save-artifacts" ]; then /usr/local/s2i/save-artifacts ; fi' > /tmp/artifacts.tar
fi

buildah commit $builder mytest

buildah rm $builder

exit

# RUN IMAGE
builder=$(buildah from registry.redhat.io/jboss-eap-7/eap74-openjdk11-openshift-rhel8)
echo "builder image2 is $builder"

buildah add --chown jboss:0 $builder ./ /tmp/src
buildah add --chown jboss:0 $builder /tmp/artifacts.tar /tmp/artifacts

buildah config --env MAVEN_CLEAR_REPO="true" $builder

buildah run $builder -- /usr/local/s2i/assemble

buildah commit $builder mytest

buildah rm $builder
