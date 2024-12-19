<%-- 
    Document   : arahan_notis_6a
    Created on : 20 Jun 2010, 7:37:49 PM
    Author     : abu.mansur
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<script type="text/javascript">
    function popup(id){
        window.open("${pageContext.request.contextPath}/hasil/papar_hakmilik_pihak?showForm&idHakmilik="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
    function showReport(){
        window.open("${pageContext.request.contextPath}/hasil/notis_6a?cetak6A", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }

    function edit(f, id){
        var queryString = $(f).formSerialize();
        window.open("${pageContext.request.contextPath}/hasil/laporan_tanah?sediaLaporanTanah&"+queryString+"&idHakmilik="+id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1200,height=650,scrollbars=1");

    }
 </script>


<s:form beanclass="etanah.view.stripes.hasil.ArahanNotis6AActionBean">
    <div class="subtitle displaytag">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Senarai Hakmilik Yang Belum Membayar Tunggakan
                </legend>
                  <p><label>No Rujukan Dasar :</label>
                    ${actionBean.noDasar}&nbsp;
                </p>
                <p><label>Tahun Semasa :</label>
                    ${actionBean.now}&nbsp;
                </p>
                <br>

                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiHakmilikPermohonan}"
                                   pagesize="5" cellpadding="0" cellspacing="0" requestURI="/hasil/laporan_tanah" id="line">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <c:if test="${actionBean.negeri eq 'melaka'}">
                            <display:column title="No. Akaun" property="hakmilik.akaunCukai.noAkaun" class="${line_rowNum}" />
                        </c:if>
                        <display:column title="ID Hakmilik"><a href="#" onclick="popup('${line.hakmilik.idHakmilik}');return false;">${line.hakmilik.idHakmilik}</a></display:column>
                        <display:column title="Jenis Dan Nombor Hakmilik">
                            ${line.hakmilik.kodHakmilik.kod}<br>
                            ${line.hakmilik.noHakmilik}<br>
                            (${line.hakmilik.kodHakmilik.nama})
                        </display:column>
                        <display:column title="No. Lot/P.T" property="hakmilik.noLot" class="${line_rowNum}" />
                        <display:column title="Hasil Tahun ini (RM)" class="${line_rowNum}"><div align="right"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukaiSebenar}"/></div></display:column>
                        <display:column title="Hutang Tahun-Tahun Lepas (RM)" class="${line_rowNum}">
                            <c:set value="${line.hakmilik.akaunCukai.senaraiTransaksiDebit[0].untukTahun}" var="minTahun"/>
                            <c:set value="${fn:length(line.hakmilik.akaunCukai.senaraiTransaksiDebit)}" var="transaksiLength"/>
                            <c:set value="${line.hakmilik.akaunCukai.senaraiTransaksiDebit[transaksiLength - 2].untukTahun}" var="maxTahun"/>
                            <c:if test="${actionBean.now eq maxTahun}">
                                <c:set value="${line.hakmilik.akaunCukai.senaraiTransaksiDebit[transaksiLength - 3].untukTahun}" var="maxTahun"/>
                            </c:if>
                            <c:set value="0" var="bilTahun"/>
                            <c:forEach items="${line.hakmilik.akaunCukai.senaraiTransaksiDebit}" var="transaksi">
                                <c:if test="${transaksi.kodTransaksi.kod eq '61401' or transaksi.kodTransaksi.kod eq '61403'}">
                                    <c:set value="${bilTahun + 1}" var="bilTahun"/>
                                </c:if>
                                <%--<c:if test="${transaksi.kodTransaksi.kod eq '61018'}">
                                    <c:set value="${transaksi.amaun}" var="caj6A"/>
                                </c:if>--%>
                            </c:forEach>
                            <c:set value="10" var="caj6A"/>
                            <c:if test="${actionBean.negeri eq 'melaka'}">
                                <c:set value="${caj6A + 10}" var="caj6A"/>
                            </c:if>
                            <div align="center"><c:out value="${minTahun}"/><c:if test="${(transaksiLength - 2) > 0}"> - <c:out value="${maxTahun}"/></c:if><br>------------<br>
                                <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukaiSebenar * (bilTahun - 1)}"/>
                            </div>
                        </display:column>
                        <display:column title="Bayaran lain yang dikenakan (RM)">
                            <div align="right">
                                <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.akaunCukai.baki - (line.hakmilik.cukaiSebenar * bilTahun)}"/>
                            </div>
                        </display:column>
                        <display:column title="Bayaran Terhutang (RM)"><div align="right"><fmt:formatNumber pattern="#,##0.00" value="${caj6A}"/></div></display:column>
                        <display:column title="Jumlah (RM)"><div align="right"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.akaunCukai.baki + caj6A}"/></div></display:column>
                        <display:column title="Penyediaan Laporan Tanah">
                                <%--<s:button name=" " onclick="edit(this.form, '${line.akaunKredit.hakmilik.idHakmilik}','${line.dokumenKewangan.idDokumenKewangan}');" value="Cetak" class="btn"/>--%>
                                <s:button name=""  onclick="edit('this.form','${line.hakmilik.idHakmilik}');" value="Laporan Tanah" class="btn" />
                        </display:column>
                            <%--display:column title="Penyediaan Laporan Tanah">
                                <s:button name=""  onclick="edit('this.form','${line.hakmilik.idHakmilik}', '${line.id}','${actionBean.permohonan.idPermohonan}');" value="Laporan Tanah" class="btn" />
                        </display:column--%>

                    </display:table>
                </div>
                  <%--<c:if test="${actionBean.flag}">
                <p align="right">
                    <s:button class="btn" onclick="showReport();" name="" value="Cetak 6A"/>
                </p>
                  </c:if>--%>
            </fieldset>
        </div>
    </div>
</s:form>
