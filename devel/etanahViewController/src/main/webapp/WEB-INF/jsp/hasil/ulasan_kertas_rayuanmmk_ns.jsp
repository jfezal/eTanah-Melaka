<%-- 
    Document   : ulasan_kertas_rayuanmmk_ns
    Created on : Feb 22, 2010, 9:04:19 AM
    Author     : abu.mansur
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<s:form beanclass="etanah.view.stripes.hasil.UlasanRayuanMMKNSActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="center">
                <table border="0" width="80%">
                    <%--<c:if test="${edit}">
                        <tr><td align="center"><b>(MAJLIS MESYUARAT KERAJAAN PADA <font color="red">*</font> <s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" size="10" />)</b></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td align="center"><b>(MAJLIS MESYUARAT KERAJAAN PADA ${actionBean.tarikhMesyuarat})</b></td></tr>
                    </c:if>--%>

                        <tr><td><b><font style="text-transform:uppercase">RAYUAN DARIPADA <c:forEach items="${actionBean.senaraiPemohon}" var="senaraiPemohon">${senaraiPemohon.pihak.nama}, </c:forEach> MENGIKUT SEKSYEN 133 KANUN TANAH NEGARA, 1965 UNTUK MEMBATALKAN PERAMPASAN TANAH YANG TELAH DIWARTAKAN MELALUI WARTA KERAJAAN NEGERI SEMBILAN<%-- ${actionBean.hakmilik.daerah.nama}--%> BERTARIKH <s:format formatPattern="dd/MM/yyyy" value="${actionBean.hakmilik.infoAudit.tarikhMasuk}" />.</font></b></td></tr>

                    <tr><td><b>1. TUJUAN</b></td></tr>
                    <tr><td width="100%">Kertas ini bertujuan untuk mendapatkan pertimbangan Majlis Mesyuarat Kerajaan, Nageri Sembilan Darul Khusus bagi rayuan daripada <c:forEach items="${actionBean.senaraiPemohon}" var="senaraiPemohon">${senaraiPemohon.pihak.nama}, No. Kad Pengenalan ${senaraiPemohon.pihak.noPengenalan}, </c:forEach>
                            untuk membatalkan perampasan tanah hakmilik ${actionBean.hakmilik.kodHakmilik.nama}&nbsp;${actionBean.hakmilik.noLot}&nbsp;${actionBean.hakmilik.lot.nama}&nbsp; ${actionBean.hakmilik.noLot}&nbsp;${actionBean.hakmilik.bandarPekanMukim.nama}&nbsp;,
                            ${actionBean.hakmilik.daerah.nama} yang telah diwartakan untuk perampasan tanah mengikut seksyen 100 Kanun Tanah Negara, 1965.</td></tr>
                    <tr><td><b>2. LATAR BELAKANG</b></td></tr>

                    <c:if test="${edit}">
                        <tr><td><s:textarea name="latarBelakang" rows="5" cols="130"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>${actionBean.latarBelakang}&nbsp;</td></tr>
                    </c:if>

                    <tr><td>Butir-butir Hakmilik Tanah:-</td></tr>
                    <tr><td><p>
                                <label><font color="black">No. Hakmilik :</font></label>
                                ${actionBean.hakmilik.kodHakmilik.nama}&nbsp;${actionBean.hakmilik.noHakmilik}&nbsp;
                            </p>
                            <p>
                                <label><font color="black">No. Lot :</font></label>
                                ${actionBean.hakmilik.noLot}&nbsp;
                            </p>
                            <%--<p>
                                <label><font color="black">Bahagian Dipunyai :</font></label>
                                ${actionBean.hakmilikPihakBerkepentingan.syerPembilang}/${actionBean.hakmilikPihakBerkepentingan.syerPenyebut}
                            </p>--%>
                            <p>
                                <label><font color="black">Mukim :</font></label>
                                ${actionBean.hakmilik.bandarPekanMukim.nama}&nbsp;
                            </p>
                            <p>
                                <label><font color="black">Daerah :</font></label>
                                ${actionBean.hakmilik.daerah.nama}&nbsp;
                            </p>
                            <p>
                                <label><font color="black">Luas :</font></label>
                                <fmt:formatNumber pattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp;${actionBean.hakmilik.kodUnitLuas.nama}&nbsp;
                            </p>
                            <p>
                                <label><font color="black">Lain-lain Hal :</font></label>
                                ${actionBean.hakmilik.kategoriTanah.nama}&nbsp;
                            </p>
                            <p>
                                <label><font color="black">Tuan Tanah :</font></label>
                                ${actionBean.hakmilikPihakBerkepentingan.pihak.nama}&nbsp;
                            </p>
                        </td></tr>

                    <c:if test="${edit}">
                        <tr><td><b>3. <font style="text-transform:uppercase">HURAIAN PENTADBIR TANAH  ${actionBean.hakmilik.daerah.nama}&nbsp;</font></b></td></tr>
                        <tr><td><s:textarea name="huraianPentadbir" rows="5" cols="130"/></td></tr>
                        <tr><td><b>4. <font style="text-transform:uppercase">SYOR PENTADBIR TANAH  ${actionBean.hakmilik.daerah.nama}&nbsp;</font></b></td></tr>
                        <tr><td><s:textarea name="syorPentadbir" rows="5" cols="130"/></td></tr>
                        <tr><td><b>5. HURAIAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN</b></td></tr>
                        <tr><td><s:textarea name="huraianPtg" rows="5" cols="130"/></td></tr>
                        <tr><td><b>6. SYOR PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN</b></td></tr>
                        <tr><td><s:textarea name="syorPtg" rows="5" cols="130"/></td></tr>
                    </c:if>

                    <c:if test="${!edit}">
                        <tr><td><b>3. <font style="text-transform:uppercase">HURAIAN PENTADBIR TANAH  ${actionBean.hakmilik.daerah.nama}&nbsp;</font></b></td></tr>
                        <tr><td> ${actionBean.huraianPentadbir}&nbsp;</td></tr>
                        <tr><td><b>4. <font style="text-transform:uppercase">SYOR PENTADBIR TANAH  ${actionBean.hakmilik.daerah.nama}&nbsp;</font></b></td></tr>
                        <tr><td> ${actionBean.syorPentadbir}&nbsp;</td></tr>
                        <tr><td><b>5. HURAIAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN</b></td></tr>
                        <tr><td> ${actionBean.huraianPtg}&nbsp;</td></tr>
                        <tr><td><b>6. SYOR PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN</b></td></tr>
                        <tr><td> ${actionBean.syorPtg}&nbsp;</td></tr>
                    </c:if>
                    <tr><td><b>7. KEPUTUSAN</b></td></tr>
                    <tr><td width="100%">7.1 Dibentangkan untuk pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri Sembilan Darul Khusus.</td></tr>
                </table>
            </div>
            <c:if test="${edit}">

                <p align="center">
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;<s:reset class="btn" name="reset" value="Isi Semula"/>
                </p>
            </c:if>
        </fieldset>
    </div>
</s:form>
