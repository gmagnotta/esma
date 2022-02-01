package org.acme.route;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

@ApplicationScoped
/**
 * Camel Route to parse incoming ESMA xml files
 */
public class ESMAXmlFileRoute extends RouteBuilder {

    private static final Logger LOGGER = Logger.getLogger(ESMAXmlFileRoute.class);

    @ConfigProperty(name = "xmls.dir")
    String xmlsDirectory;
    
    @ConfigProperty(name = "processingthreads")
    int processingThreads;
    
    @Override
    public void configure() throws Exception {
    	
        JaxbDataFormat jaxbDataFormat = new JaxbDataFormat("org.gmagnotta.jaxb");

        onException(Exception.class)
         .log("Exception occurred during route processing: ${exception.stacktrace}")
         .markRollbackOnly();

        // from("file:" + xmlsDirectory + "?moveFailed=.error&readLock=markerFile&readLockTimeout=100&readLockCheckInterval=20&maxMessagesPerPoll=" + processingThreads)
        //  .routeId("fileRoute")
        //  .log("Consuming file ${headers.CamelFileAbsolutePath}")
        //  .unmarshal().zipFile()
        //  .threads(processingThreads, processingThreads, "fileProcessor")
        //   .unmarshal(jaxbDataFormat)
        //   .to("bean://esmaprocessor")
        //   .log("done processing order ${headers.OrderEntity.id}");

          from("file:" + xmlsDirectory + "?moveFailed=.error&readLock=markerFile&readLockTimeout=100&readLockCheckInterval=20&maxMessagesPerPoll=" + processingThreads)
          .routeId("fileRoute")
          .log("Consuming file ${headers.CamelFileAbsolutePath}")
          .split().tokenizeXML("NonEqtyTrnsprncyData", "*").streaming().parallelProcessing()
            .unmarshal(jaxbDataFormat)
            .to("bean://esmaprocessor").end();
        
    }
}
