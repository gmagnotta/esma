package com.magnotta.model;

import java.math.BigDecimal;
import java.util.Date;

public class TransparencyDataReport {
    
    public String techRcrdId;

    public String id;

    public String finInstrmClssfctn;

    public String fullNm;

    public Long rptgPrd_frDtToDt_frDt;

    public Long rptgPrd_frDtToDt_toDt;

    public Boolean lqdty;

    public BigDecimal preTradLrgInScaleThrshld_nb;

    public BigDecimal preTradLrgInScaleThrshld_amt_value;

    public String preTradLrgInScaleThrshld_amt_ccy;

    public BigDecimal pstTradLrgInScaleThrshld_nb;

    public BigDecimal pstTradLrgInScaleThrshld_nb_amt_value;

    public String pstTradLrgInScaleThrshld_nb_amt_ccy;

    public BigDecimal sttstcs_ttlNbOfTxsExctd;

    public BigDecimal sttstcs_ttlVolOfTxsExctd;

    public String getTechRcrdId() {
        return techRcrdId;
    }

    public void setTechRcrdId(String techRcrdId) {
        this.techRcrdId = techRcrdId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFinInstrmClssfctn() {
        return finInstrmClssfctn;
    }

    public void setFinInstrmClssfctn(String finInstrmClssfctn) {
        this.finInstrmClssfctn = finInstrmClssfctn;
    }

    public String getFullNm() {
        return fullNm;
    }

    public void setFullNm(String fullNm) {
        this.fullNm = fullNm;
    }

    public Date getRptgPrd_frDtToDt_frDt() {
        return new Date(rptgPrd_frDtToDt_frDt);
    }

    public void setRptgPrd_frDtToDt_frDt(Long rptgPrd_frDtToDt_frDt) {
        this.rptgPrd_frDtToDt_frDt = rptgPrd_frDtToDt_frDt;
    }

    public Date getRptgPrd_frDtToDt_toDt() {
        return new Date(rptgPrd_frDtToDt_toDt);
    }

    public void setRptgPrd_frDtToDt_toDt(Long rptgPrd_frDtToDt_toDt) {
        this.rptgPrd_frDtToDt_toDt = rptgPrd_frDtToDt_toDt;
    }

    public Boolean getLqdty() {
        return lqdty;
    }

    public void setLqdty(Boolean lqdty) {
        this.lqdty = lqdty;
    }

    public BigDecimal getPreTradLrgInScaleThrshld_nb() {
        return preTradLrgInScaleThrshld_nb;
    }

    public void setPreTradLrgInScaleThrshld_nb(BigDecimal preTradLrgInScaleThrshld_nb) {
        this.preTradLrgInScaleThrshld_nb = preTradLrgInScaleThrshld_nb;
    }

    public BigDecimal getPreTradLrgInScaleThrshld_amt_value() {
        return preTradLrgInScaleThrshld_amt_value;
    }

    public void setPreTradLrgInScaleThrshld_amt_value(BigDecimal preTradLrgInScaleThrshld_amt_value) {
        this.preTradLrgInScaleThrshld_amt_value = preTradLrgInScaleThrshld_amt_value;
    }

    public String getPreTradLrgInScaleThrshld_amt_ccy() {
        return preTradLrgInScaleThrshld_amt_ccy;
    }

    public void setPreTradLrgInScaleThrshld_amt_ccy(String preTradLrgInScaleThrshld_amt_ccy) {
        this.preTradLrgInScaleThrshld_amt_ccy = preTradLrgInScaleThrshld_amt_ccy;
    }

    public BigDecimal getPstTradLrgInScaleThrshld_nb() {
        return pstTradLrgInScaleThrshld_nb;
    }

    public void setPstTradLrgInScaleThrshld_nb(BigDecimal pstTradLrgInScaleThrshld_nb) {
        this.pstTradLrgInScaleThrshld_nb = pstTradLrgInScaleThrshld_nb;
    }

    public BigDecimal getPstTradLrgInScaleThrshld_nb_amt_value() {
        return pstTradLrgInScaleThrshld_nb_amt_value;
    }

    public void setPstTradLrgInScaleThrshld_nb_amt_value(BigDecimal pstTradLrgInScaleThrshld_nb_amt_value) {
        this.pstTradLrgInScaleThrshld_nb_amt_value = pstTradLrgInScaleThrshld_nb_amt_value;
    }

    public String getPstTradLrgInScaleThrshld_nb_amt_ccy() {
        return pstTradLrgInScaleThrshld_nb_amt_ccy;
    }

    public void setPstTradLrgInScaleThrshld_nb_amt_ccy(String pstTradLrgInScaleThrshld_nb_amt_ccy) {
        this.pstTradLrgInScaleThrshld_nb_amt_ccy = pstTradLrgInScaleThrshld_nb_amt_ccy;
    }

    public BigDecimal getSttstcs_ttlNbOfTxsExctd() {
        return sttstcs_ttlNbOfTxsExctd;
    }

    public void setSttstcs_ttlNbOfTxsExctd(BigDecimal sttstcs_ttlNbOfTxsExctd) {
        this.sttstcs_ttlNbOfTxsExctd = sttstcs_ttlNbOfTxsExctd;
    }

    public BigDecimal getSttstcs_ttlVolOfTxsExctd() {
        return sttstcs_ttlVolOfTxsExctd;
    }

    public void setSttstcs_ttlVolOfTxsExctd(BigDecimal sttstcs_ttlVolOfTxsExctd) {
        this.sttstcs_ttlVolOfTxsExctd = sttstcs_ttlVolOfTxsExctd;
    }

    
}
