<%--
	Document: maklumat_akaun
	Author: Mohd Hairudin Mansur
	Version: 1.0 17 December 2009
 --%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<script type="text/javascript">
    function edit(f, id1){
        var queryString = $(f).formSerialize()
        <%--alert(id1);--%>
        window.open("${pageContext.request.contextPath}/hasil/pertanyaan_akaun?print&"+queryString+"&idHakmilik="+id1, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");

    }
</script>
<div align="center"><table style="width:99.2%" bgcolor="green">
    <tr>
        <td width="100%" height="20" >
            <div style="background-color: green;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Pertanyaan, Pengwujudan Dan Penyelenggaraan Akaun Amanah</font>
            </div>
        </td>
    </tr>
</table></div>
<s:form beanclass="etanah.view.stripes.hasil.PertanyaanAkaunActionBean" id="pertanyaan_akaun">
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pemegang Akaun</legend>
            <p>
                <label>No. Akaun :</label>
                ${actionBean.akaun.noAkaun}&nbsp;
            </p>
            <p>
                <label>ID Hakmilik :</label>
                ${actionBean.akaun.hakmilik.idHakmilik}&nbsp;
            </p>
            <p>
                <label>ID Permohonan :</label>
                <c:if test="${actionBean.akaun.permohonan.idPermohonan eq null}">-</c:if>
                <c:if test="${actionBean.akaun.permohonan.idPermohonan ne null}">
                    ${actionBean.akaun.permohonan.idPermohonan}
                </c:if>
            </p>
            <p>
                <label>Jenis Pengenalan :</label>
                ${actionBean.akaun.pemegang.jenisPengenalan.nama}&nbsp;
            </p>
            <p>
                <label>Nombor Pengenalan :</label>
                ${actionBean.akaun.pemegang.noPengenalan}&nbsp;
            </p>
            <p>
                <label>Nama :</label>
                ${actionBean.akaun.pemegang.nama}&nbsp;
            </p>
            <p>
                <label>Alamat :</label>
                ${actionBean.akaun.pemegang.suratAlamat1}&nbsp;
            </p>
            <p>
                <label>&nbsp;</label>
                ${actionBean.akaun.pemegang.suratAlamat2}&nbsp;
            </p>
            <p>
                <label>&nbsp;</label>
                ${actionBean.akaun.pemegang.suratAlamat3}&nbsp;
            </p>
            <p>
                <label>&nbsp;</label>
                ${actionBean.akaun.pemegang.suratAlamat4}&nbsp;
            </p>
            <p>
                <label>Poskod :</label>
                ${actionBean.akaun.pemegang.suratPoskod}&nbsp;
            </p>
            <p>
                <label>Negeri :</label>
                ${actionBean.akaun.pemegang.suratNegeri.nama}&nbsp;
            </p>
            <p>
                <label>Kod Jabatan (SPEKS) :</label>
                <c:if test="${actionBean.akaun.kodSpeksJabatan eq null}">-</c:if>
                <c:if test="${actionBean.akaun.kodSpeksJabatan ne null}">
                    ${actionBean.akaun.kodSpeksJabatan}
                </c:if>
                &nbsp;
            </p>
            <p>
                <label>Kod PTJ :</label>
                <c:if test="${actionBean.akaun.kodSpeksPTJ eq null}">-</c:if>
                <c:if test="${actionBean.akaun.kodSpeksPTJ ne null}">
                    ${actionBean.akaun.kodSpeksPTJ}
                </c:if>
            </p>
            <%--<p>
                <label>Jumlah Sudah Dibayar (RM) :</label>
                ${actionBean.jumSudahDibayar}&nbsp;
            </p>
            <p>
                <c:if test="${actionBean.akaun.kodAkaun.kod eq 'ACT' or actionBean.akaun.kodAkaun.kod eq 'APT'
                                      or actionBean.akaun.kodAkaun.kod eq 'AA'}">
                    <label>Baki Belum Dibayar (RM) :</label>
                    ${actionBean.bakiBelumDibayar}&nbsp;
                </c:if>
            </p>--%>
        </fieldset>
    </div>
    <br/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Transaksi</legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.listTransaksi}" id="line">
                    <c:choose>
                        <c:when test="${line.status.kod ne 'B'}">
                            <display:column title="Bil" sortable="true">${line_rowNum}.</display:column>
                            <display:column title="Jenis">
                                ${line.kodTransaksi.kod} - ${line.kodTransaksi.nama}
                            </display:column>
                            <display:column property="dokumenKewangan.idDokumenKewangan" title="No. Resit" />
                            <display:column title="Tarikh">
                                <s:format formatPattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/>
                            </display:column>
                            <display:column title="Deposit (RM)" style="text-align:right;">
                                <fmt:formatNumber value="${line.amaun}" pattern="0.00"/>
                            </display:column>
                            <display:column property="status.nama" title="Status Pembayaran" />
                        </c:when>
                        <c:otherwise>
                            <display:column title="Bil" sortable="true">${line_rowNum}.</display:column>
                            <display:column title="Jenis" style="text-decoration:line-through;">
                                ${line.kodTransaksi.kod} - ${line.kodTransaksi.nama}
                            </display:column>
                            <display:column property="dokumenKewangan.idDokumenKewangan" title="No. Resit" style="text-decoration:line-through;"/>
                            <display:column title="Tarikh" style="text-decoration:line-through;">
                                <s:format formatPattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/>
                            </display:column>
                            <display:column title="Deposit (RM)" style="text-align:right;text-decoration:line-through;">
                                <fmt:formatNumber value="${line.amaun}" pattern="0.00"/>
                            </display:column>
                            <display:column property="status.nama" title="Status Pembayaran" style="text-decoration:line-through;"/>
                        </c:otherwise>
                    </c:choose>
                    <display:footer>
                        <tr>
                            <td colspan="4"><div align="right"><b>Jumlah (RM)</b></div></td>
                            <td><div align="right"><fmt:formatNumber value="${actionBean.total}" pattern="0.00"/></div></td>
                        <tr>
                    </display:footer>
                </display:table>
            </div>
        </fieldset>
    </div>

    <div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="50%" height="20" align="right">
                <%--<s:button name="print" value="Cetak" class="btn" onclick="edit(this.form, '${actionBean.id}');" />--%>
                <s:submit name="back" value="Kembali" class="btn"/>
            </td>
        </tr>
</table></div>
</s:form>