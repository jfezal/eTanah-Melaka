<%-- 
    Document   : remisyen_tanah_1
    Created on : 05 Nov 2012, 15:23:09
    Author     : haqqiem
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<%--<script type="text/javascript">
    $(document).ready(function() {
        alert('hahaha');
        var queryString = $(f).formSerialize();
                var d = document.getElementById('remisyen_tanah');
                alert(d.value);
        var url = "${pageContext.request.contextPath}/hasil/remisyenTanah?filterByJabatan&search";
        $.get(url,
        function(data){
            alert(data);
            $('#setup_rem').html(data);
        },'html');
    });
</script>--%>

<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:10px;
        margin-right:0.5em;
        text-align:right;
        width:8em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:10em;
    }
</style>
<div id="setup_rem" >
    <body onload="testing123()">
<s:form name="rt1" beanclass="etanah.view.stripes.hasil.ViewRemisyenActionBean" id="remisyen_tanah">
    <s:errors/>
    <s:messages/>
    <div class="content">
        <fieldset class="aras1">
            <legend>Maklumat Hakmilik</legend>
            <center>
                <display:table name="${actionBean.senaraiAkaun}" class="tablecloth" id="row" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/hasil/remisyenTanah">
                    <display:column title="Bil.">${row_rowNum}</display:column>
                    <c:if test="${actionBean.negeri eq 'melaka'}">
                        <display:column title="No. Akaun" property="noAkaun"/>
                    </c:if>
                    <display:column title="ID Hakmilik" property="hakmilik.idHakmilik"/>
                    <c:set var="tanah" value="0"/>
                    <c:set var="tunggak" value="0"/>
                    <c:set var="denda" value="0"/>
                    <c:set var="tunggak_denda" value="0"/>
                    <c:set var="n6a_ns" value="0"/>
                    <c:set var="n6a_ml" value="0"/>
                    <!-- for transaksi batal-->
                    <c:set var="tanah_b" value="0"/>
                    <c:set var="tunggak_b" value="0"/>
                    <c:set var="denda_b" value="0"/>
                    <c:set var="tunggak_denda_b" value="0"/>
                    <c:set var="n6a_ns_b" value="0"/>
                    <c:set var="n6a_ml_b" value="0"/>
                    <c:set var="tahunSemasa" value="${actionBean.tahunSkrg}"/>
                    <c:forEach var="trans" items="${row.semuaTransaksi}">
                        <c:if test="${trans.kodTransaksi.kod eq '61401' and trans.status.kod eq 'A'}"><c:set var="tanah" value="${tanah + trans.amaun}"/></c:if>
                        <c:if test="${trans.kodTransaksi.kod eq '61402' and trans.status.kod eq 'A'}"><c:set var="tunggak" value="${tunggak + trans.amaun}"/></c:if>
                        <c:if test="${trans.kodTransaksi.kod eq '76152' and trans.status.kod eq 'A' and trans.untukTahun eq tahunSemasa}"><c:set var="denda" value="${denda + trans.amaun}"/></c:if>
                        <c:if test="${trans.kodTransaksi.kod eq '76152' and trans.status.kod eq 'A' and trans.untukTahun ne tahunSemasa}"><c:set var="tunggak_denda" value="${tunggak_denda + trans.amaun}"/></c:if>
                        <c:if test="${trans.kodTransaksi.kod eq '99011' and trans.status.kod eq 'A'}"><c:set var="n6a_ns" value="${n6a_ns + trans.amaun}"/></c:if>
                        <c:if test="${trans.kodTransaksi.kod eq '72457' and trans.status.kod eq 'A'}"><c:set var="n6a_ml" value="${n6a_ml + trans.amaun}"/></c:if>
                        <!-- for transaksi batal-->
                        <c:if test="${trans.kodTransaksi.kod eq '61401' and trans.status.kod eq 'B'}"><c:set var="tanah_b" value="${tanah_b + trans.amaun}"/></c:if>
                        <c:if test="${trans.kodTransaksi.kod eq '61402' and trans.status.kod eq 'B'}"><c:set var="tunggak_b" value="${tunggak_b + trans.amaun}"/></c:if>
                        <c:if test="${trans.kodTransaksi.kod eq '76152' and trans.status.kod eq 'B' and trans.untukTahun eq tahunSemasa}"><c:set var="denda_b" value="${denda_b + trans.amaun}"/></c:if>
                        <c:if test="${trans.kodTransaksi.kod eq '76152' and trans.status.kod eq 'B' and trans.untukTahun ne tahunSemasa}"><c:set var="tunggak_denda_b" value="${tunggak_denda_b + trans.amaun}"/></c:if>
                        <c:if test="${trans.kodTransaksi.kod eq '99011' and trans.status.kod eq 'B'}"><c:set var="n6a_ns_b" value="${n6a_ns_b + trans.amaun}"/></c:if>
                        <c:if test="${trans.kodTransaksi.kod eq '72457' and trans.status.kod eq 'B'}"><c:set var="n6a_ml_b" value="${n6a_ml_b + trans.amaun}"/></c:if>
                    </c:forEach>
                    <%--Cukai Semasa : RM<fmt:formatNumber value="${tanah}" pattern="#,##0.00"/><br>
                    Tunggakan Cukai : RM<fmt:formatNumber value="${tunggak}" pattern="#,##0.00"/><br>
                    Denda Lewat : RM<fmt:formatNumber value="${denda}" pattern="#,##0.00"/>--%>
                    <display:column title="Cukai Tanah" style="text-align:right">
                        <%--<fmt:formatNumber value="${row.baki}" pattern="#,##0.00"/>--%>
                        RM<fmt:formatNumber value="${tanah - tanah_b}" pattern="#,##0.00"/>
                    </display:column>
                    <display:column title="Tunggakan Cukai Tanah" style="text-align:right">
                        RM<fmt:formatNumber value="${tunggak - tunggak_b}" pattern="#,##0.00"/>
                    </display:column>
                    <display:column title="Denda Lewat" style="text-align:right">
                        RM<fmt:formatNumber value="${denda - denda_b}" pattern="#,##0.00"/>
                    </display:column>
                    <display:column title="Tunggakan Denda Lewat" style="text-align:right">
                        RM<fmt:formatNumber value="${tunggak_denda - tunggak_denda_b}" pattern="#,##0.00"/>
                    </display:column>
                    <c:if test="${actionBean.negeri eq 'sembilan'}">
                        <display:column title="Notis 6A" style="text-align:right">
                            RM<fmt:formatNumber value="${n6a_ns - n6a_ns_b}" pattern="#,##0.00"/>
                        </display:column>
                    </c:if>
                    <c:if test="${actionBean.negeri eq 'melaka'}">
                        <display:column title="Notis 6A" style="text-align:right">
                            RM<fmt:formatNumber value="${n6a_ml - n6a_ml_b}" pattern="#,##0.00"/>
                        </display:column>
                    </c:if>
                    <c:set value="0" var="minTahun"/>
                    <%--<c:set value="0" var="nilaiRemisyen"/>--%>
                    <c:set var="rTanah" value="0"/>
                    <c:set var="rTunggak" value="0"/>
                    <c:set var="rDenda" value="0"/>
                    <c:set var="rTunggakDenda" value="0"/>
                    <!-- for transaksi batal-->
                    <c:set var="rTanah_b" value="0"/>
                    <c:set var="rTunggak_b" value="0"/>
                    <c:set var="rDenda_b" value="0"/>
                    <c:set var="rTunggakDenda_b" value="0"/>
                    <c:forEach items="${row.semuaTransaksi}" var="transaksi">
                        <c:if test="${(transaksi.kodTransaksi.kod eq '99030' or transaksi.kodTransaksi.kod eq '99051' or transaksi.kodTransaksi.kod eq '99050' or transaksi.kodTransaksi.kod eq '99052') and transaksi.untukTahun eq actionBean.tahunSkrg}">
                            <%--<c:set value="${nilaiRemisyen + transaksi.amaun}" var="nilaiRemisyen"/>--%>
                            <c:if test="${transaksi.kodTransaksi.kod eq '99030' and transaksi.status.kod eq 'A'}"><c:set var="rTanah" value="${rTanah + transaksi.amaun}"/></c:if>
                            <c:if test="${transaksi.kodTransaksi.kod eq '99051' and transaksi.status.kod eq 'A'}"><c:set var="rTunggak" value="${rTunggak + transaksi.amaun}"/></c:if>
                            <c:if test="${transaksi.kodTransaksi.kod eq '99050' and transaksi.status.kod eq 'A'}"><c:set var="rDenda" value="${rDenda + transaksi.amaun}"/></c:if>
                            <c:if test="${transaksi.kodTransaksi.kod eq '99052' and transaksi.status.kod eq 'A'}"><c:set var="rTunggakDenda" value="${rTunggakDenda + transaksi.amaun}"/></c:if>
                            <!-- for transaksi batal-->
                            <c:if test="${transaksi.kodTransaksi.kod eq '99030' and transaksi.status.kod eq 'B'}"><c:set var="rTanah_b" value="${rTanah_b + transaksi.amaun}"/></c:if>
                            <c:if test="${transaksi.kodTransaksi.kod eq '99051' and transaksi.status.kod eq 'B'}"><c:set var="rTunggak_b" value="${rTunggak_b + transaksi.amaun}"/></c:if>
                            <c:if test="${transaksi.kodTransaksi.kod eq '99050' and transaksi.status.kod eq 'B'}"><c:set var="rDenda_b" value="${rDenda_b + transaksi.amaun}"/></c:if>
                            <c:if test="${transaksi.kodTransaksi.kod eq '99052' and transaksi.status.kod eq 'B'}"><c:set var="rTunggakDenda_b" value="${rTunggakDenda_b + transaksi.amaun}"/></c:if>
                            <c:set value="${transaksi.untukTahun}" var="minTahun"/>
                        </c:if>
                    </c:forEach>
                    <%--Cukai Semasa : RM<fmt:formatNumber value="${rTanah}" pattern="#,##0.00"/><br>
                    Tunggakan Cukai : RM<fmt:formatNumber value="${rTunggak}" pattern="#,##0.00"/><br>
                    Denda Lewat : RM<fmt:formatNumber value="${rDenda}" pattern="#,##0.00"/>--%>
                    <display:column title="% Remisyen Cukai Tanah" style="text-align:right">
                        <%--<fmt:formatNumber value="${nilaiRemisyen}" pattern="#,##0.00"/>--%>
                        <c:if test="${actionBean.peratusRemisyenTanah eq ''}">-</c:if>
                        <c:if test="${actionBean.peratusRemisyenTanah ne ''}"><fmt:formatNumber value="${actionBean.peratusRemisyenTanah}"/>&nbsp;%</c:if>
                    </display:column>
                    <display:column title="Remisyen Cukai Tanah" style="text-align:right">
                        <%--<fmt:formatNumber value="${nilaiRemisyen}" pattern="#,##0.00"/>--%>
                        RM<fmt:formatNumber value="${rTanah - rTanah_b}" pattern="#,##0.00"/>
                    </display:column>
                    <display:column title="% Remisyen Tunggakan Cukai Tanah" style="text-align:right">
                        <%--<fmt:formatNumber value="${nilaiRemisyen}" pattern="#,##0.00"/>--%>
                        <c:if test="${actionBean.peratusRemisyenTunggak eq ''}">-</c:if>
                        <c:if test="${actionBean.peratusRemisyenTunggak ne ''}"><fmt:formatNumber value="${actionBean.peratusRemisyenTunggak}"/>&nbsp;%</c:if>
                    </display:column>
                    <display:column title="Remisyen Tunggakan Cukai Tanah" style="text-align:right">
                        RM<fmt:formatNumber value="${rTunggak - rTunggak_b}" pattern="#,##0.00"/>
                    </display:column>
                    <display:column title="% Remisyen Denda Lewat" style="text-align:right">
                        <%--<fmt:formatNumber value="${nilaiRemisyen}" pattern="#,##0.00"/>--%>
                        <c:if test="${actionBean.peratusRemisyenDenda eq ''}">-</c:if>
                        <c:if test="${actionBean.peratusRemisyenDenda ne ''}"><fmt:formatNumber value="${actionBean.peratusRemisyenDenda}"/>&nbsp;%</c:if>
                    </display:column>
                    <display:column title="Remisyen Denda Lewat" style="text-align:right">
                        RM<fmt:formatNumber value="${rDenda - rDenda_b}" pattern="#,##0.00"/>
                    </display:column>
                    <display:column title="% Remisyen Tunggakan Denda Lewat" style="text-align:right">
                        <%--<fmt:formatNumber value="${nilaiRemisyen}" pattern="#,##0.00"/>--%>
                        <c:if test="${actionBean.peratusRemisyenTunggakDenda eq ''}">-</c:if>
                        <c:if test="${actionBean.peratusRemisyenTunggakDenda ne ''}"><fmt:formatNumber value="${actionBean.peratusRemisyenTunggakDenda}"/>&nbsp;%</c:if>
                    </display:column>
                    <display:column title="Remisyen Tunggakan Denda Lewat" style="text-align:right">
                        RM<fmt:formatNumber value="${rTunggakDenda - rTunggakDenda_b}" pattern="#,##0.00"/>
                    </display:column>
                    <display:column title="Jumlah Perlu Dibayar" style="text-align:right">
                        RM<fmt:formatNumber value="${row.baki}" pattern="#,##0.00"/>
                    </display:column>
                    <c:set value="0" var="bilTahun"/>
                    <c:forEach items="${row.senaraiTransaksiKreditHadapan}" var="transaksiHdpn">
                            <%--<c:if test="${bilTahun > 0}">--%>
                                <c:set value="${transaksiHdpn.untukTahun}" var="maxTahun"/>
                            <%--</c:if>--%>
                            <c:set value="${bilTahun + 1}" var="bilTahun"/>
                    </c:forEach>
                    <display:column title="Tahun Remisyen" style="text-align:center">
                        <c:out value="${minTahun}"/><c:if test="${bilTahun >= 1}"> - <c:out value="${maxTahun}"/></c:if>
                    </display:column>
                </display:table>
            </center>
        </fieldset>
    </div>

</s:form>
    </body>
</div>