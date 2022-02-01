package org.acme.initializer;

import org.acme.model.TransparencyDataReport;
import org.infinispan.protostream.SerializationContextInitializer;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;
import org.infinispan.protostream.types.java.math.BigDecimalAdapter;

@AutoProtoSchemaBuilder(includeClasses = {TransparencyDataReport.class, BigDecimalAdapter.class},  schemaFileName = "esma.proto", schemaFilePath = "proto", schemaPackageName = "esma")
public interface TransparencyDataReportInitializer extends SerializationContextInitializer {
    
}