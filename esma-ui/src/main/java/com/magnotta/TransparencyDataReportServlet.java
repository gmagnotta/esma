package com.magnotta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.magnotta.model.TransparencyDataReport;

import javax.inject.Inject;

public class TransparencyDataReportServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    private ServicesInterface servicesInterface;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");

        if (id == null || id.equals("")) {
            id = "%";
        }

        String size = request.getParameter("size");
        if (size == null) {
            size = "100";
        }

        List<TransparencyDataReport> res = filterValues(servicesInterface.searchId(id, Integer.valueOf(size)));

        request.setAttribute("items", res);

        response.setHeader("cache-control", "no-cache");

        getServletContext().getRequestDispatcher("/WEB-INF/report.jsp").forward(request, response);

    }

    private static final List<TransparencyDataReport> filterValues(List<TransparencyDataReport> inputList) {
        List<TransparencyDataReport> values = new ArrayList<TransparencyDataReport>();

        for (TransparencyDataReport report : inputList) {

            if (report == null ||
                report.techRcrdId == null || report.techRcrdId.isBlank() ||
                report.id == null || report.id.isBlank() ||
                report.finInstrmClssfctn == null || report.finInstrmClssfctn.isBlank() ||
                report.rptgPrd_frDtToDt_frDt == null ||
                report.rptgPrd_frDtToDt_toDt == null ||

                report.fullNm == null || report.fullNm.isBlank() ||
                report.lqdty == null ||
                report.preTradLrgInScaleThrshld_nb == null ||
                report.preTradLrgInScaleThrshld_amt_value == null ||
                report.preTradLrgInScaleThrshld_amt_ccy == null|| report.preTradLrgInScaleThrshld_amt_ccy.isBlank() ||
                report.pstTradLrgInScaleThrshld_nb == null ||
                report.pstTradLrgInScaleThrshld_nb_amt_value == null||
                report.pstTradLrgInScaleThrshld_nb_amt_ccy == null|| report.pstTradLrgInScaleThrshld_nb_amt_ccy.isBlank() ||
                report.sttstcs_ttlNbOfTxsExctd == null||
                report.sttstcs_ttlVolOfTxsExctd == null
                ) {
                continue;
            }

            values.add(report);

        }

        return values;
    }

}