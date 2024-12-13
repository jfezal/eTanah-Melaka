<%--
    Document   : ulasan_mmk
    Created on : Feb 1, 2010, 12:01:43 PM
    Author     : muhammad.amin
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>

<style type="text/css">
    #tdLabel {
        color:black;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:250px;
        margin-right:0.5em;
        text-align:right;
        width:15em;
        vertical-align:top;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:40em;
    }
</style>

<s:form beanclass="etanah.view.strata.UlasanMmkActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="center">
                <table border="0" width="80%">
                    <c:if test="${edit}">
                        <tr><td align="center"><b>(MAJLIS MESYUARAT KERAJAAN)</b></td></tr>
                    </c:if>
                    <c:if test="${display}">
                        <tr><td align="center"><b>(MAJLIS MESYUARAT KERAJAAN)</b></td></tr>
                    </c:if>
                    <c:if test="${edit}">
                        <tr><td align="center"><b>(MAJLIS MESYUARAT KERAJAAN PADA <font color="red">*</font> <s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" size="12" />)</b></td></tr>
                    </c:if>

                    <c:if test="${display}">
                        <tr><td align="center"><b>(MAJLIS MESYUARAT KERAJAAN PADA ${actionBean.tarikhMesyuarat})</b></td></tr>
                    </c:if>

                    <tr><td><b><font style="text-transform: uppercase">MAJLIS MESYUARAT KERAJAAN KE ATAS PERMOHONAN DARIPADA ${actionBean.pemohon.pihak.nama} TERHADAP PERINTAH PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}.</font></b></td></tr>

                    <tr><td><b>1. TUJUAN</b></td></tr>
                    <tr><td width="100%" id="tdDisplay">Kertas ini bertujuan untuk pertimbangan oleh Majlis Mesyuarat Kerajaan, Negeri Sembilan bagi permohonan daripada ${actionBean.pemohon.pihak.nama}, No. Kad Pengenalan ${actionBean.pemohon.pihak.noPengenalan} yang beralamat ${actionBean.pemohon.pihak.alamat1}
                            <c:if test="${actionBean.pemohon.pihak.alamat2 ne null}">, </c:if>
                            ${actionBean.pemohon.pihak.alamat2}
                            <c:if test="${actionBean.pemohon.pihak.alamat3 ne null}">, </c:if>
                            ${actionBean.pemohon.pihak.alamat3}
                            <c:if test="${actionBean.pemohon.pihak.alamat4 ne null}">, </c:if>
                            ${actionBean.pemohon.pihak.alamat4} 
                            ${actionBean.pemohon.pihak.poskod} ${actionBean.pemohon.pihak.negeri.nama}
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PKKR'}">
                                mengenai Permohonan Klasifikasi Bangunan Kos Rendah
                            </c:if>
                        </td></tr>
                    <tr><td><b>2. LATAR BELAKANG</b></td></tr>

                    <c:if test="${edit}">
                        <tr><td><s:textarea name="latarBelakang" rows="5" cols="150"/></td></tr>
                    </c:if>
                    <c:if test="${display}">
                        <tr><td id="tdDisplay">${actionBean.latarBelakang}&nbsp;</td></tr>
                    </c:if>

                    <tr><td id="tdDisplay">Butir-butir Hakmilik Tanah:-</td></tr>
                    <tr><td>
                            <table border="0" width="100%">
                                <tr><td id="tdLabel">Jenis Hakmilik :</td>
                                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.kodHakmilik.nama ne null}"> ${actionBean.hakmilik.kodHakmilik.nama}&nbsp; </c:if>
                                        <c:if test="${actionBean.hakmilik.kodHakmilik.nama eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>

                                <tr><td id="tdLabel">Lot :</td>
                                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.noLot ne null}"> ${actionBean.hakmilik.noLot}&nbsp; </c:if>
                                        <c:if test="${actionBean.hakmilik.noLot eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr><td id="tdLabel">Luas :</td>
                                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.luas ne null}"><fmt:formatNumber pattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp;${actionBean.hakmilik.kodUnitLuas.nama}</c:if>
                                        <c:if test="${actionBean.hakmilik.luas eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr><td id="tdLabel">Bahagian Dipunyai :</td>
                                    <td id="tdDisplay"><c:if test="${actionBean.hakmilikPihakBerkepentingan.syerPembilang ne null}"> ${actionBean.hakmilikPihakBerkepentingan.syerPembilang}/${actionBean.hakmilikPihakBerkepentingan.syerPenyebut}</c:if>
                                        <c:if test="${actionBean.hakmilikPihakBerkepentingan.syerPembilang eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr><td id="tdLabel">Mukim :</td>
                                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.bandarPekanMukim.nama ne null}">  ${actionBean.hakmilik.bandarPekanMukim.nama}&nbsp;</c:if>
                                        <c:if test="${actionBean.hakmilik.bandarPekanMukim.nama eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>

                                <tr><td id="tdLabel">Daerah :</td>
                                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.daerah.nama ne null}">  ${actionBean.hakmilik.daerah.nama}&nbsp;</c:if>
                                        <c:if test="${actionBean.hakmilik.daerah.nama eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr><td id="tdLabel">Lain-lain Hal :</td>
                                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.kategoriTanah.nama ne null}">  ${actionBean.hakmilik.kategoriTanah.nama}&nbsp;</c:if>
                                        <c:if test="${actionBean.hakmilik.kategoriTanah.nama eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr><td id="tdLabel">Tuan Tanah :</td>
                                    <td id="tdDisplay"><c:if test="${actionBean.hakmilikPihakBerkepentingan.pihak.nama ne null}">  ${actionBean.hakmilikPihakBerkepentingan.pihak.nama}&nbsp;</c:if>
                                        <c:if test="${actionBean.hakmilikPihakBerkepentingan.pihak.nama eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                            </table>
                        </td></tr>


                    <tr><td><b>3. HURAIAN PENTADBIR TANAH  <font style="text-transform: uppercase">${actionBean.hakmilik.daerah.nama}&nbsp;</font></b></td></tr>
                    <c:if test="${edit}">
                        <tr><td><s:textarea name="huraianPentadbir" rows="5" cols="150" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${display}">
                        <tr><td><s:textarea name="huraianPentadbir" rows="5" cols="150" disabled="true" class="normal_text"/></td></tr>
                    </c:if>

                    <tr><td><b>4. SYOR PENTADBIR TANAH <font style="text-transform: uppercase"> ${actionBean.hakmilik.daerah.nama}&nbsp;</font></b></td></tr>
                    <c:if test="${edit}">
                        <tr><td><s:textarea name="syorPentadbir" rows="5" cols="150" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${display}">
                        <tr><td><s:textarea name="syorPentadbir" rows="5" cols="150" disabled="true" class="normal_text"/></td></tr>
                    </c:if>
                    <tr><td><b>5. HURAIAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td><s:textarea name="huraianPtg" rows="5" cols="150" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${display}">
                        <tr><td><s:textarea name="huraianPtg" rows="5" cols="150" disabled="true" class="normal_text"/></td></tr>
                    </c:if>
                    <tr><td><b>6. SYOR PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td><s:textarea name="syorPtg" rows="5" cols="150" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${display}">
                        <tr><td><s:textarea name="syorPtg" rows="5" cols="150" disabled="true" class="normal_text"/></td></tr>
                    </c:if>

                   
                    <tr><td><b>7. KEPUTUSAN</b></td></tr>
                    <tr><td width="100%" id="tdDisplay">7.1 Dibentangkan untuk pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri Sembilan Darul Khusus.</td></tr>
                </table>
            </div>
            <c:if test="${edit}">
                <br>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>

                </p>
            </c:if>
        </fieldset>
    </div>
</s:form>
