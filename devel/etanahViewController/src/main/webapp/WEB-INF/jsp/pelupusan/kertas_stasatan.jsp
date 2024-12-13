<%--
    Document   : kertas_stasatan
    Created on : Nov 18, 2011, 2:28:49 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"

    "http://www.w3.org/TR/html4/loose.dtd">


<s:form beanclass="etanah.view.stripes.pelupusan.KertasStasatanActionBean">
    <s:messages/>
    <fieldset class="aras1">
        <table width="100%" border="0">
            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="4" align="left">
                    <b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">Butiran mengenai pemohon dan tuan tanah adalah seperti </b> </td>
            </tr>
            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>
            <tr>
                <td align="left" colspan="4"><label>Pemohon:</label>${actionBean.pemohon.pihak.nama}</td>

            </tr>
            <tr>
                <td align="left" colspan="4"><label>Alamat:</label>
                    ${actionBean.pemohon.pihak.alamat1}<br>
                    <label>&nbsp;</label>${actionBean.pemohon.pihak.alamat2}<br>
                    <label>&nbsp;</label>${actionBean.pemohon.pihak.alamat3}<br>
                    <label>&nbsp;</label>${actionBean.pemohon.pihak.alamat4}<br>
                    <label>&nbsp;</label>${actionBean.pemohon.pihak.poskod}&nbsp;${actionBean.pemohon.pihak.negeri.nama}<br>


                </td>
            </tr>
            <tr>

                <td align="left" colspan="4"><label>No. Tel:</label>
                    ${actionBean.pemohon.pihak.noTelefon1}<c:if test="${actionBean.pemohon.pihak.noTelefon1 ne null}">/</c:if>${actionBean.pemohon.pihak.noTelefon2}<c:if test="${actionBean.pemohon.pihak.noTelefon2 ne null}">/</c:if>${actionBean.pemohon.pihak.noTelefonBimbit}
                </td>
            </tr>
            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="4">
                    <fieldset class="aras1">
                        <div class="content" align="center">
                            <display:table class="tablecloth" name="${actionBean.mohanPihakList}" pagesize="5"
                                           requestURI="/pelupusan/Kertas_stasatan"  id="line">
                                <display:column title="Tuan Tanah" style="width:300px" property="pihak.nama"></display:column>
                                <display:column title="Alamat" style="width:300px"  >${line.pihak.alamat1}&nbsp;${line.pihak.alamat1}&nbsp;${line.pihak.alamat2}&nbsp;${line.pihak.alamat3}&nbsp;${line.pihak.alamat4}&nbsp;${line.pihak.poskod}&nbsp;${line.pihak.negeri.nama}</display:column>
                                <display:column title="No. Tel"  style="width:300px"  >${line.pihak.noTelefon1}&nbsp;${line.pihak.noTelefon2}&nbsp;${line.pihak.noTelefonBimbit}</display:column>
                            </display:table>
                        </div>
                    </fieldset>
                </td>
            </tr>
            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="4" align="left">
                    <b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">Butiran mengenai tanah</b>
                </td>
            </tr>
            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="4"><label>Hakmilik:</label>
                    ${actionBean.hakmilikPermohonan.hakmilik.kodHakmilik.kod}&nbsp;${actionBean.hakmilikPermohonan.hakmilik.noHakmilik}
                </td>
            </tr>
            <tr>
                <td align="left" colspan="4" ><label>Mukim/Daerah:</label>
                    ${actionBean.hakmilikPermohonan.hakmilik.bandarPekanMukim.nama}&nbsp; ${actionBean.hakmilikPermohonan.hakmilik.daerah.nama}
                </td>
            </tr>
            <tr>
                <td align="left" colspan="4"><label>Keluasan:</label>
                    ${actionBean.hakmilikPermohonan.hakmilik.luas}&nbsp;${actionBean.hakmilikPermohonan.kodUnitLuas.kod}
                </td>
            </tr>
            <tr>

                <td align="left" colspan="4">
                    <label>Luas terlibat Hak Lalu Lalang:</label>
                    <s:text name="luasT" id="luasT" formatPattern="#,###,##0.0000"/>
                    <s:select name="kodUnitLuas" id="kodUnitLuas">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="H">Hektar</s:option>
                        <s:option value="M">Meter Persegi</s:option>
                    </s:select>
                </td>

            </tr>
            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="4" align="left">
                    <b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">Berikut ini adalah merupakan soalan-soalan yang akan ditanyakan </b>
                </td>
            </tr>
            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="4" align="left" ><label>Bila dan bagaimana caranya tuan tanah memiliki tanah ini?</label>
                    <s:textarea name="kand0" id="kand0"  rows="7" cols="69" class="normal_text"></s:textarea>
                </td>
            </tr>
            <tr>
                <td colspan="4" align="left"><label>Apakah jenis bangunan / tanaman yang ada di atas tanah ini?</label>
                    <s:textarea name="kand1" id="kand1"  rows="7" cols="69" class="normal_text"></s:textarea>
                </td>
            </tr>
            <tr>
                <td colspan="4" align="left"><label>Pernah memohon untuk pecah sempadan / tukar syarat / kedua-duanya serentak?</label>
                    <s:textarea name="kand2" id="kand2"  rows="7" cols="69" class="normal_text"></s:textarea>
                </td>
            </tr>
            <tr>
                <td colspan="4" align="left"><label>Apakah terdapat apa-apa bebanan seperti gadaian, pajakan, atau perjanjian jualbeli dengan pihak ketiga?</label>
                    <s:textarea name="kand3" id="kand3"  rows="7" cols="69" class="normal_text"></s:textarea>
                </td>
            </tr>
            <tr>
                <td colspan="4" align="left"><label>Berapakah pampasan yang dituntut dan apakah alasan-alasannya?</label>
                    <s:textarea name="kand4" id="kand4"  rows="7" cols="69" class="normal_text"></s:textarea>
                </td>
            </tr>
            <tr>
                <td colspan="4" align="left"><label>Laporan Nilaian Tanah/Kerosakan(pagar/Bagunan)oleh Jabatan Penilaian dan Perkhimatan Harta Melaka melalui suratnya bertarikh :</label>
                    <s:format formatPattern="MM/dd/yyyy" value="${actionBean.permohonanRujukanLuar.tarikhRujukan}"/>
                </td>
            </tr>
            <tr>
                <td align="left" colspan="4" ><label>Nilaian Tanah:</label>
                    RM <s:format formatPattern="###,###,##0" value="${actionBean.permohonanRujukanLuar.nilai}"/>
                </td>
            </tr>
            <tr>
                <td align="left" colspan="4"><label>Kerosakan:</label>
                    <s:text name="kand5" id="kand5" size="20" />
                </td>
            </tr>
            <tr>
                <td align="left" colspan="4"><label>Lain-lain:</label>
                    <s:text name="kand6" id="kand6" size="20" />
                </td>
            </tr>
            <tr>
                <td colspan="4" align="left">
                    <label>Keterangan Pemohon:</label>
                    <s:textarea name="kand7" id="kand7"  rows="7" cols="69" class="normal_text"></s:textarea>
                </td>
            </tr>
            <tr>
                <td colspan="4" align="left">
                    <label>Keterangan Tuan Tanah:</label>
                    <s:textarea name="kand8" id="kand8"  rows="7" cols="69" class="normal_text"></s:textarea>
                </td>
            </tr>
            <tr>
                <td colspan="4" align="left">
                    <label> Pertimbangan PTD:</label>
                    <s:textarea name="kand9" id="kand9"  rows="7" cols="69" class="normal_text"></s:textarea>
                </td>
            </tr>
            <tr>
                <td colspan="4" align="left">
                    <label>Keputusan / Perintah PTD:</label>
                    <s:textarea name="kand10" id="kand10"  rows="7" cols="69" class="normal_text"></s:textarea>
                </td>
            </tr>

            <tr>

                <td colspan="4" align="center">
                    <s:button name="kertasStasatanSimpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </td>
            </tr>
        </table>

    </fieldset>
</s:form>

