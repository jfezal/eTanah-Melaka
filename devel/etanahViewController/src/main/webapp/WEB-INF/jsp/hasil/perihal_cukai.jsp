<%-- 
    Document   : perihal_cukai
    Created on : Oct 23, 2009, 12:18:58 PM
    Author     : abu.mansur
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%--<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>.: eTanah - Hasil :.</title>
    </head>
    <body>--%>
        <div class="subtitle displaytag">
            <br>
            <s:form beanclass="etanah.view.stripes.hasil.PerihalCukaiActionBean">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Perihal Cukai
                    </legend>
                    <p>
                        <label>Tempoh Tunggakan : </label>
                        <s:text name="tahunTunggakan" disabled="true"/>
                    </p>
                    <p>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.listTransaksi}"  cellpadding="0" cellspacing="0" id="line" pagesize="10" requestURI="/common/perihalCukai">
                        <%--<display:table class="tablecloth" name="${actionBean.listTransaksi}" id="line">--%>
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Tarikh Transaksi"><fmt:formatDate pattern="dd/MM/yyyy H:mm:ss a" value="${line.infoAudit.tarikhMasuk}"/></display:column>
                            <display:column title="Transaksi" >${line.kodTransaksi.kod} - ${line.kodTransaksi.nama}</display:column>
                            <display:column title="Amaun (RM)"><div align="right">
                                    <%--<c:if test="${line.akaunKredit.hakmilik.idHakmilik ne null}">
                                        -<fmt:formatNumber pattern="#,##0.00" value="${line.amaun}"/>
                                    </c:if>
                                    <c:if test="${line.akaunKredit.hakmilik.idHakmilik eq null}">--%>
                                        <fmt:formatNumber pattern="#,##0.00" value="${line.amaun}"/>
                                    <%--</c:if>--%>
                            </div></display:column>
                            <display:column property="status.nama" title="Status"/>
                            <display:footer>
                                <tr>
                                    <td colspan="3"><div align="right">Jumlah (RM):</div></td>
                                    <td><div align="right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.akaunBaki.baki}"/></div></td>
                                </tr>
                            </display:footer>
                        </display:table>
                    </div>
                    </p>
                </fieldset>
                <fieldset class="aras1">
                    <legend>Notis 6A</legend>
                    <p>
                        <label>Tarikh Disampaikan :</label>
                        <s:text name="tarikhHantar" disabled="true" formatPattern="dd/MM/yyyy" />
                    </p>
                    <p>
                        <label>Tarikh Didaftarkan :</label>
                        <s:text name="tarikhTerima" disabled="true" formatPattern="dd/MM/yyyy" />
                    </p>
                </fieldset>
                <br>
            </s:form>
        </div>
<%--    </body>
</html>--%>
