package org.acme.model;

import java.math.BigDecimal;

import org.infinispan.protostream.annotations.ProtoDoc;
import org.infinispan.protostream.annotations.ProtoField;

@ProtoDoc("@Indexed")
public class TransparencyDataReport {
    
	@ProtoDoc("@Field(index=Index.YES, analyze = Analyze.NO, store = Store.NO)")
    @ProtoField(number =  1, required = true)
    public String techRcrdId;

    @ProtoDoc("@Field(index=Index.YES, analyze = Analyze.NO, store = Store.NO)")
    @ProtoField(number =  2, required = true)
    public String id;

    @ProtoDoc("@Field(index=Index.YES, analyze = Analyze.NO, store = Store.NO)")
    @ProtoField(number =  3, required = true)
    public String finInstrmClssfctn;

    @ProtoDoc("@Field(index=Index.YES, analyze = Analyze.NO, store = Store.NO)")
    @ProtoField(number =  4, defaultValue = "")
    public String fullNm;

    @ProtoField(number =  5)
    public Long rptgPrd_frDtToDt_frDt;

    @ProtoField(number =  6)
    public Long rptgPrd_frDtToDt_toDt;

    @ProtoField(number =  7)
    public Boolean lqdty;

    @ProtoField(number =  8)
    public BigDecimal preTradLrgInScaleThrshld_nb;

    @ProtoField(number =  9)
    public BigDecimal preTradLrgInScaleThrshld_amt_value;

    @ProtoField(number =  10)
    public String preTradLrgInScaleThrshld_amt_ccy;

    @ProtoField(number =  11)
    public BigDecimal pstTradLrgInScaleThrshld_nb;

    @ProtoField(number =  12)
    public BigDecimal pstTradLrgInScaleThrshld_nb_amt_value;

    @ProtoField(number =  13)
    public String pstTradLrgInScaleThrshld_nb_amt_ccy;

    @ProtoField(number =  14)
    public BigDecimal sttstcs_ttlNbOfTxsExctd;

    @ProtoField(number =  15)
    public BigDecimal sttstcs_ttlVolOfTxsExctd;
}
