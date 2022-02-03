<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style>
table, th, td {
    border: 1px solid black;
}
</style>
</head>
<body>

<h1>Results:</h1>

<table>
    <thead>
        <tr>
            <th>id</th>
            <th>fullNm</th>
            <th>techRcrdId</th>
            <th>finInstrmClssfctn</th>
            <th>rptgPrd_frDtToDt_frDt</th>
            <th>rptgPrd_frDtToDt_toDt</th>
            <th>lqdty</th>
            <th>preTradLrgInScaleThrshld_nb</th>
            <th>preTradLrgInScaleThrshld_amt_value</th>
            <th>preTradLrgInScaleThrshld_amt_ccy</th>
            <th>pstTradLrgInScaleThrshld_nb</th>
            <th>pstTradLrgInScaleThrshld_nb_amt_value</th>
            <th>pstTradLrgInScaleThrshld_nb_amt_ccy</th>
            <th>sttstcs_ttlNbOfTxsExctd</th>
            <th>sttstcs_ttlVolOfTxsExctd</th>
        </tr>
    </thead>

    <tbody>
        <c:forEach items="${items}" var="item">
            <tr>
                <td>${item.id}</td>
                <td>${item.fullNm}</td>
                <td>${item.techRcrdId}</td>
                <td>${item.finInstrmClssfctn}</td>
                <td>${item.rptgPrd_frDtToDt_frDt}</td>
                <td>${item.rptgPrd_frDtToDt_toDt}</td>
                <td>${item.lqdty}</td>
                <td>${item.preTradLrgInScaleThrshld_nb}</td>
                <td>${item.preTradLrgInScaleThrshld_amt_value}</td>
                <td>${item.preTradLrgInScaleThrshld_amt_ccy}</td>
                <td>${item.pstTradLrgInScaleThrshld_nb}</td>
                <td>${item.pstTradLrgInScaleThrshld_nb_amt_value}</td>
                <td>${item.pstTradLrgInScaleThrshld_nb_amt_ccy}</td>
                <td>${item.sttstcs_ttlNbOfTxsExctd}</td>
                <td>${item.sttstcs_ttlVolOfTxsExctd}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>

</html>