<%--
    Document   : ulasan_kertas_mmk_remri_mlk
    Created on : Feb 18, 2010, 12:01:43 PM
    Author     : abu.mansur

--%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        text-align:right;
        width:32em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:30em;
    }
</style>

<script type="text/javascript">
    function cetakKertasREMRIMMK(){
        window.open("${pageContext.request.contextPath}/hasil/ulasan_mmk_remri_mlk?cetakMMK", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=500");
    }
</script>

<s:form beanclass="etanah.view.stripes.hasil.UlasanMMKREMRIMlkActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="center">
                <table border="0" width="96%">
                    <%--<c:if test="${edit}">
                        <tr><td align="center"><b>(MAJLIS MESYUARAT KERAJAAN PADA <font color="red">*</font> <s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" size="10" />)</b></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td align="center"><b>(MAJLIS MESYUARAT KERAJAAN PADA ${actionBean.tarikhMesyuarat})</b></td></tr>
                    </c:if>--%>

                    <tr><td><b><font style="text-transform:uppercase">PERMOHONAN DARIPADA <c:forEach items="${actionBean.senaraiPemohon}" var="senaraiPemohon">${senaraiPemohon.pihak.nama}, </c:forEach> UNTUK REMISYEN RUMAH IBADAT BAGI HAKMILIK ${actionBean.hakmilik.kodHakmilik.kod}&nbsp;${actionBean.hakmilik.noHakmilik}&nbsp;${actionBean.hakmilik.lot.nama}&nbsp;
                                    ${actionBean.hakmilik.noLot},&nbsp;${actionBean.hakmilik.bandarPekanMukim.nama},&nbsp;DAERAH ${actionBean.hakmilik.daerah.nama}.</font></b></td></tr>
                    <tr><td align="center">&nbsp;</td></tr>

                    <tr><td><b>1. TUJUAN</b></td></tr>
                    <tr><td width="96%">Kertas ini bertujuan untuk mendapatkan pertimbangan Majlis Mesyuarat Kerajaan, Negeri Melaka di atas permohonan <c:forEach items="${actionBean.senaraiPemohon}" var="senaraiPemohon">${senaraiPemohon.pihak.nama}, No. Kad Pengenalan ${senaraiPemohon.pihak.noPengenalan}, </c:forEach>
                            untuk Remisyen Rumah Ibadat bagi hakmilik ${actionBean.hakmilik.kodHakmilik.kod}&nbsp;${actionBean.hakmilik.noHakmilik}&nbsp;${actionBean.hakmilik.lot.nama}&nbsp; ${actionBean.hakmilik.noLot},&nbsp;${actionBean.hakmilik.bandarPekanMukim.nama}&nbsp;,
                            ${actionBean.hakmilik.daerah.nama}.</td></tr>
                    <c:forEach items="${actionBean.senaraiPemohon}" var="senaraiPemohon">
                    <tr><td>
                            <p>
                                <label><font color="black">Pemohon :</font></label>
                                ${senaraiPemohon.pihak.nama}&nbsp;
                            </p>
                            <p>
                                <label><font color="black">Alamat :</font></label>
                                ${senaraiPemohon.pihak.suratAlamat1},&nbsp;${senaraiPemohon.pihak.suratAlamat2}&nbsp;
                            </p>
                            <p>
                                <label>&nbsp;</label>
                                ${senaraiPemohon.pihak.suratAlamat3}&nbsp;${senaraiPemohon.pihak.suratAlamat4}&nbsp;
                            </p>
                            <p>
                                <label>&nbsp;</label>
                                ${senaraiPemohon.pihak.suratPoskod}&nbsp;${senaraiPemohon.pihak.suratNegeri.nama}&nbsp;
                            </p>
                        </td></tr>
                    </c:forEach>
                    <tr><td><b>2. LATAR BELAKANG</b></td></tr>

                    <c:if test="${actionBean.edit}">
                        <tr><td><s:textarea name="latarBelakang" rows="5" cols="120"/></td></tr>
                    </c:if>
                    <c:if test="${!actionBean.edit}">
                        <tr><td>${actionBean.latarBelakang}&nbsp;</td></tr>
                    </c:if>

                    <tr><td>Butir-butir Hakmilik Tanah:-</td></tr>
                    <tr><td>
                            <table border="0" width="100%">
                                <tr>
                                    <td id="tdLabel"><font color="black">Jenis Hakmilik :</font></td>
                                    <td id="tdDisplay">${actionBean.hakmilik.kodHakmilik.nama}&nbsp;</td>
                                </tr>
                                <tr>
                                    <td id="tdLabel"><font color="black">No. Lot/PT :</font></td>
                                    <td id="tdDisplay">${actionBean.hakmilik.noLot}&nbsp;</td>
                                </tr>
                                <tr>
                                    <td id="tdLabel"><font color="black">Keluasan :</font></td>
                                    <td id="tdDisplay"><fmt:formatNumber pattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp;${actionBean.hakmilik.kodUnitLuas.nama}</td>
                                </tr>
                                <tr>
                                    <td id="tdLabel"><font color="black">Mukim :</font></td>
                                    <td id="tdDisplay">${actionBean.hakmilik.bandarPekanMukim.nama}&nbsp;</td>
                                </tr>
                                <tr>
                                    <td id="tdLabel"><font color="black">Daerah :</font></td>
                                    <td id="tdDisplay">${actionBean.hakmilik.daerah.nama}&nbsp;</td>
                                </tr>
                                <tr>
                                    <td id="tdLabel"><font color="black">Lain-lain Hal :</font></td>
                                    <td id="tdDisplay">${actionBean.hakmilik.kategoriTanah.nama}&nbsp;</td>
                                </tr>
                                <tr>
                                    <td id="tdLabel"><font color="black">Syarat Nyata :</font></td>
                                    <td id="tdDisplay">${actionBean.hakmilik.syaratNyata.syarat}&nbsp;</td>
                                </tr>
                                <tr>
                                    <td id="tdLabel"><font color="black">Pemilik :</font></td>
                                    <td id="tdDisplay">${actionBean.hakmilikPihakBerkepentingan.pihak.nama}&nbsp;</td>
                                </tr>
                                <tr>
                                    <td id="tdLabel"><font color="black">Cukai Semasa (RM):</font></td>
                                    <td id="tdDisplay"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.hakmilik.cukaiSebenar}"/>&nbsp;</td>
                                </tr>
                                <%--<tr>
                                    <td id="tdLabel"><font color="black">Denda Lewat (RM):</font></td>
                                    <td id="tdDisplay"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.dendaLewat}"/>&nbsp;</td>
                                </tr>
                                <tr>
                                    <td id="tdLabel"><font color="black">Jumlah (RM):</font></td>
                                    <td id="tdDisplay"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.jumlah}"/>&nbsp;</td>
                                </tr>--%>
                            </table>
                        </td></tr>

                    <c:if test="${actionBean.edit}">
                        <tr><td><b>3. <font style="text-transform:uppercase">HURAIAN PENTADBIR TANAH  ${actionBean.hakmilik.daerah.nama}&nbsp;</font></b></td></tr>
                        <c:if test="${actionBean.editPPT}"><tr><td><s:textarea name="huraianPentadbir" rows="5" cols="120"/></td></tr></c:if>
                        <c:if test="${!actionBean.editPPT}"><tr><td>${actionBean.huraianPentadbir}&nbsp;<s:hidden name="huraianPentadbir"/></td></tr></c:if>
                        <tr><td><b>4. <font style="text-transform:uppercase">SYOR PENTADBIR TANAH  ${actionBean.hakmilik.daerah.nama}&nbsp;</font></b></td></tr>
                        <c:if test="${actionBean.editPPT}"><tr><td><s:textarea name="syorPentadbir" rows="5" cols="120"/></td></tr></c:if>
                        <c:if test="${!actionBean.editPPT}"><tr><td>${actionBean.syorPentadbir}&nbsp;<s:hidden name="syorPentadbir"/></td></tr></c:if>
                        <tr><td><b>5. HURAIAN PENGARAH TANAH DAN GALIAN NEGERI MELAKA</b></td></tr>
                        <c:if test="${actionBean.editTPTG}"><tr><td><s:textarea name="huraianPtg" rows="5" cols="120"/></td></tr></c:if>
                        <c:if test="${!actionBean.editTPTG}"><tr><td>
                                <c:if test="${actionBean.huraianPtg eq ' ' || actionBean.huraianPtg eq null}">TIADA DATA.</c:if>
                                <c:if test="${actionBean.huraianPtg ne ' '}">${actionBean.huraianPtg}&nbsp;<s:hidden name="huraianPtg"/></c:if>
                            </td>
                        </tr></c:if>
                        <tr><td><b>6. PERAKUAN PENGARAH TANAH DAN GALIAN NEGERI MELAKA</b></td></tr>
                        <c:if test="${actionBean.editPTG}"><tr><td><s:textarea name="syorPtg" rows="5" cols="120"/></td></tr></c:if>
                        <c:if test="${!actionBean.editPTG}"><tr><td>
                                <c:if test="${actionBean.syorPtg eq ' ' || actionBean.syorPtg eq null}">TIADA DATA.</c:if>
                                <c:if test="${actionBean.syorPtg ne ' '}">${actionBean.syorPtg}&nbsp;<s:hidden name="syorPtg"/></c:if>
                            </td>
                        </tr></c:if>
                    </c:if>

                    <c:if test="${!actionBean.edit}">
                        <tr><td><b>3. <font style="text-transform:uppercase">HURAIAN PENTADBIR TANAH  ${actionBean.hakmilik.daerah.nama}&nbsp;</font></b></td></tr>
                        <tr><td> ${actionBean.huraianPentadbir}&nbsp;</td></tr>
                        <tr><td><b>4. <font style="text-transform:uppercase">SYOR PENTADBIR TANAH  ${actionBean.hakmilik.daerah.nama}&nbsp;</font></b></td></tr>
                        <tr><td> ${actionBean.syorPentadbir}&nbsp;</td></tr>
                        <tr><td><b>5. HURAIAN PENGARAH TANAH DAN GALIAN NEGERI MELAKA</b></td></tr>
                        <tr><td> ${actionBean.huraianPtg}&nbsp;</td></tr>
                        <tr><td><b>6. PERAKUAN PENGARAH TANAH DAN GALIAN NEGERI MELAKA</b></td></tr>
                        <tr><td> ${actionBean.syorPtg}&nbsp;</td></tr>
                    </c:if>
                    <tr><td><b>7. KEPUTUSAN</b></td></tr>
                    <tr><td width="96%">7.1 Dibentangkan untuk pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri Melaka.</td></tr>
                </table>
            </div>
            <c:if test="${actionBean.edit}">

                <p>
                    <label>&nbsp;</label>                    
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
                    <%--<s:button onclick="cetakKertasREMRIMMK();" name="" value="Cetak Kertas MMK" class="longbtn" disabled="${actionBean.cetak}"/>&nbsp;--%>
                    <%--<s:reset class="btn" name="reset" value="Isi Semula"/>--%>
                </p>
            </c:if>
        </fieldset>
    </div>
</s:form>
