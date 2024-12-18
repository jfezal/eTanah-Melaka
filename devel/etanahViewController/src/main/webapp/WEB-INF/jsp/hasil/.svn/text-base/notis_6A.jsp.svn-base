<%-- 
    Document   : notis_6A
    Created on : Jan 12, 2010, 3:28:12 PM
    Author     : abu.mansur
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<script type="text/javascript">
    function popup(id){
        $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
        window.open("${pageContext.request.contextPath}/hasil/papar_hakmilik_pihak?showForm&idHakmilik="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        $.unblockUI();
    }
    function showReport(idMohon){
        $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
        var url = "${pageContext.request.contextPath}/reportGeneratorServlet?reportName=hasilNotis6A.rdf&report_p_id_mohon="+idMohon;
        <%--window.open("${pageContext.request.contextPath}/hasil/notis_6a?cetak6A", "eTanah",--%>
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
        $.unblockUI();
    }
</script>


<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form beanclass="etanah.view.stripes.hasil.Notis6AActionBean">


    <div class="subtitle displaytag">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Senarai Hakmilik Yang Belum Membayar Tunggakan
                </legend>
                <p>
                    <label>No Rujukan Dasar :</label>
                    ${actionBean.noDasar}&nbsp;
                </p>
                <p><label>Tahun Semasa :</label>
                    ${actionBean.now}&nbsp;
                </p>
                <br>

                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiHakmilikPermohonan}"
                                   pagesize="5" cellpadding="0" cellspacing="0" requestURI="/hasil/notis_6a" id="line">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                            <display:column title="No. Akaun" property="hakmilik.akaunCukai.noAkaun" class="${line_rowNum}" />
                        </c:if>
                        <display:column title="ID Hakmilik"><a href="#" onclick="popup('${line.hakmilik.idHakmilik}');return false;">${line.hakmilik.idHakmilik}</a></display:column>
                        <display:column title="No. Rujukan">${actionBean.senaraiNoRujukan[line_rowNum - 1]}</display:column>
                        <display:column title="Jenis Dan Nombor Hakmilik">
                            ${line.hakmilik.kodHakmilik.kod}<br>
                            ${line.hakmilik.noHakmilik}<br>
                            (${line.hakmilik.kodHakmilik.nama})
                        </display:column>
                        <display:column title="No. Lot/P.T" property="hakmilik.noLot" class="${line_rowNum}" />
                        <display:column title="Hasil Tahun ini (RM)" class="${line_rowNum}"><div align="right"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukaiSebenar}"/></div></display:column>
                        <display:column title="Hutang Tahun-Tahun Lepas (RM)" class="${line_rowNum}">
                            <%--<c:set value="${line.hakmilik.akaunCukai.senaraiTransaksiDebit[0].untukTahun}" var="minTahun"/>
                            <c:set value="${fn:length(line.hakmilik.akaunCukai.senaraiTransaksiDebit)}" var="transaksiLength"/>
                            <c:set value="${line.hakmilik.akaunCukai.senaraiTransaksiDebit[transaksiLength - 2].untukTahun}" var="maxTahun"/>
                            <c:if test="${actionBean.now eq maxTahun}">
                                <c:set value="${line.hakmilik.akaunCukai.senaraiTransaksiDebit[transaksiLength - 3].untukTahun}" var="maxTahun"/>
                            </c:if>--%>
                            <c:set value="0" var="bilTahun"/>
                            <c:set value="0" var="tunggak"/>
                            <c:set value="0" var="denda"/>
                            <c:forEach items="${line.hakmilik.akaunCukai.senaraiTransaksiDebit}" var="transaksi">
                                <c:if test="${transaksi.kodTransaksi.kod eq '61402'}">                                    
                                    <c:if test="${bilTahun eq 0}">
                                        <c:set value="${transaksi.untukTahun}" var="minTahun"/>
                                    </c:if>
                                    <c:if test="${bilTahun > 0}">
                                        <c:set value="${transaksi.untukTahun}" var="maxTahun"/>
                                    </c:if>
                                    <c:set value="${bilTahun + 1}" var="bilTahun"/>
                                    <c:set value="${transaksi.amaun + tunggak}" var="tunggak"/>
                                </c:if>
                                <c:if test="${transaksi.kodTransaksi.kod eq '76152'}">
                                    <c:set value="${transaksi.amaun + denda}" var="denda"/>
                                </c:if>
                            </c:forEach>
                            <div align="center"><c:out value="${minTahun}"/><c:if test="${bilTahun > 1}"> - <c:out value="${maxTahun}"/></c:if><br>------------<br>
                                <%--<fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukaiSebenar * bilTahun}"/>--%>
                                <fmt:formatNumber pattern="#,##0.00" value="${tunggak}"/>
                            </div>
                        </display:column>
                        <display:column title="Bayaran lain yang dikenakan (RM)">
                            <div align="right">
                                <%--<fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.akaunCukai.baki - (line.hakmilik.cukaiSebenar * bilTahun)}"/>--%>
                                <fmt:formatNumber pattern="#,##0.00" value="${denda}"/>
                            </div>
                        </display:column>
                                <c:set value="10" var="caj6A"/>
                                <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                                    <c:set value="${caj6A + 10}" var="caj6A"/>
                                </c:if>
                        <display:column title="Bayaran Terhutang (RM)"><div align="right"><fmt:formatNumber pattern="#,##0.00" value="${caj6A}"/></div></display:column>
                        <display:column title="Jumlah (RM)">
                            <div align="right">
                                <%--<fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.akaunCukai.baki + caj6A}"/>--%>
                                <%--<c:if test="${actionBean.kodNegeri eq 'melaka'}">--%>
                                    <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukaiSebenar + tunggak + denda + caj6A}"/>
                                <%--</c:if>
                                <c:if test="${actionBean.kodNegeri ne 'melaka'}">
                                    <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukaiSebenar + tunggak + denda }"/>
                                </c:if>--%>
                            </div>
                        </display:column>
                    </display:table>
                </div>
                   <%--<c:if test="${actionBean.kodNegeri ne 'melaka'}">
                <p align="right">
                    <s:button class="btn" onclick="showReport('${actionBean.permohonan.idPermohonan}');" name="" value="Cetak 6A"/>
                </p>
                   </c:if>--%>
            </fieldset>
        </div>
    </div>
</s:form>