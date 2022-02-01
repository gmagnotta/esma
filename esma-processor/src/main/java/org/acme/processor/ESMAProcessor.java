package org.acme.processor;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.bind.JAXBElement;

import org.acme.model.TransparencyDataReport;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.gmagnotta.jaxb.BusinessDataHeaderV01;
import org.gmagnotta.jaxb.FinancialInstrumentReportingNonEquityTradingActivityResultV02;
import org.gmagnotta.jaxb.TransparencyDataReport181;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.jboss.logging.Logger;

@ApplicationScoped
@Named("esmaprocessor")
/**
 * This class is a Camel Processor used to process ESMA data
 */
public class ESMAProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(ESMAProcessor.class);

    @Inject
    RemoteCacheManager cacheManager;

    RemoteCache<String, TransparencyDataReport> cache;

    @ConfigProperty(name = "cachename")
    String cachename;

    @PostConstruct
    private void init() {
        LOGGER.info("Getting esma cache");
        cache = cacheManager.getCache(cachename);
    }

    public void process(Exchange exchange) throws Exception {

        BusinessDataHeaderV01 businessDataHeaderV01 = exchange.getIn().getBody(BusinessDataHeaderV01.class);

        //LOGGER.info("Received " + ((JAXBElement) businessDataHeaderV01.getPyld().getAny()).getValue());

        org.gmagnotta.jaxb.Document doc = (org.gmagnotta.jaxb.Document) ((JAXBElement) businessDataHeaderV01.getPyld()
                .getAny()).getValue();

        FinancialInstrumentReportingNonEquityTradingActivityResultV02 res = doc
                .getFinInstrmRptgNonEqtyTradgActvtyRslt();

        List<TransparencyDataReport181> items = res.getNonEqtyTrnsprncyData();

        LOGGER.info("available items: " + items.size());

        for (TransparencyDataReport181 report : items) {
            LOGGER.info(" persisting id " + report.getTechRcrdId());
            TransparencyDataReport r = new TransparencyDataReport();

            r.techRcrdId = report.getTechRcrdId();
            r.id = report.getId();
            r.finInstrmClssfctn = report.getFinInstrmClssfctn().value();
            
            // next fields can be null
            r.fullNm = (report.getFullNm() != null) ? report.getFullNm(): null;

            r.rptgPrd_frDtToDt_frDt = Long.valueOf(report.getRptgPrd().getFrDtToDt().getFrDt().toGregorianCalendar().getTimeInMillis());
            r.rptgPrd_frDtToDt_toDt = Long.valueOf(report.getRptgPrd().getFrDtToDt().getToDt().toGregorianCalendar().getTimeInMillis());
            r.lqdty = report.isLqdty();
            r.preTradLrgInScaleThrshld_nb = report.getPreTradInstrmSzSpcfcThrshld().getNb();
            r.preTradLrgInScaleThrshld_amt_value = report.getPreTradInstrmSzSpcfcThrshld().getAmt().getValue();
            r.preTradLrgInScaleThrshld_amt_ccy = report.getPreTradInstrmSzSpcfcThrshld().getAmt().getCcy();
            r.pstTradLrgInScaleThrshld_nb = report.getPstTradLrgInScaleThrshld().getNb();
            r.pstTradLrgInScaleThrshld_nb_amt_value = report.getPstTradLrgInScaleThrshld().getAmt().getValue();
            r.pstTradLrgInScaleThrshld_nb_amt_ccy = report.getPstTradLrgInScaleThrshld().getAmt().getCcy();
            
            r.sttstcs_ttlNbOfTxsExctd = (report.getSttstcs() != null) ? report.getSttstcs().getTtlNbOfTxsExctd() : null;
            r.sttstcs_ttlVolOfTxsExctd = (report.getSttstcs() != null) ? report.getSttstcs().getTtlVolOfTxsExctd() : null;

            cache.put(UUID.randomUUID().toString(), r);
        }

    }

}
