<%-- 
    Document   : kertas_pertimbangan
    Created on : Mar 1, 2010, 3:44:42 PM
    Author     : nursyazwani
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>

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
        width:20em;
    }
</style>

<script type="text/javascript">

    function validation(){

        if($("#keputusan").val() == ""){
            alert('Sila masukkan " Keputusan Permohonan" terlebih dahulu.');
            $("#keputusan").focus();
            return true;
        }
    }

</script>
<s:form beanclass="etanah.view.stripes.pembangunan.KertasPertimbanganActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="center">
                <table border="0" width="80%">
                    <tr><td align="center"><b>KERTAS UNTUK PERTIMBANGAN PENTADBIR TANAH </b></td></tr><br>
                    <c:if test="${edit}">
                        <tr><td><b><font style="text-transform: uppercase">KERTAS UNTUK PERTIMBANGAN PENTADBIR TANAH BAGI PERMOHONAN UNTUK ${actionBean.permohonan.kodUrusan.nama} BAGI ${actionBean.hakmilik.noLot} ${actionBean.hakmilik.bandarPekanMukim.nama}, ${actionBean.hakmilik.daerah.nama} MENGIKUT <s:text name="kanunTanah" size="20"/> KANUN TANAH NEGARA.</font></b></td></tr><br>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><b><font style="text-transform: uppercase">KERTAS UNTUK PERTIMBANGAN PENTADBIR TANAH BAGI PERMOHONAN UNTUK ${actionBean.permohonan.kodUrusan.nama} BAGI ${actionBean.hakmilik.noLot} ${actionBean.hakmilik.bandarPekanMukim.nama}, ${actionBean.hakmilik.daerah.nama} MENGIKUT ${actionBean.kanunTanah} KANUN TANAH NEGARA.</font></b></td></tr><br>
                    </c:if>
                    <tr><td><b>1. TUJUAN</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td><s:textarea name="tujuan" rows="5" cols="150"/></td></tr><br>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><font style="text-transform:capitalize">1.1 &nbsp; ${actionBean.tujuan}</font></td></tr>
                    </c:if>
                        
                    <tr><td><b>2. LATAR BELAKANG</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td><s:textarea name="latarBelakang" rows="5" cols="150"/></td></tr><br>
                    </c:if>

                    <c:if test="${!edit}">
                        <tr><td><font style="text-transform:capitalize">2.1 &nbsp; ${actionBean.latarBelakang}&nbsp;</font></td></tr><br>
                    </c:if>

                    <tr><td><b>3. BUTIR-BUTIR TANAH </b></td></tr>
                    <tr><td>
                            <table>
                                <tr>
                                    <td id="tdLabel"><font color="black">Daerah :</font></td>
                                    <td id="tdDisplay">${actionBean.hakmilik.daerah.nama}&nbsp;</td>
                                </tr>
                                <tr>
                                    <td id="tdLabel"><font color="black">Mukim :</font></td>
                                    <td id="tdDisplay">${actionBean.hakmilik.bandarPekanMukim.nama}&nbsp;</td>
                                </tr>
                                <tr>
                                    <td id="tdLabel"><font color="black">Jenis Hakmilik :</font></td>
                                    <td id="tdDisplay">${actionBean.hakmilik.kodHakmilik.nama}&nbsp;</td>
                                </tr>
                                <tr>
                                    <td id="tdLabel"><font color="black">No. Hakmilik :</font></td>
                                    <td id="tdDisplay">${actionBean.hakmilik.idHakmilik}&nbsp;</td>
                                </tr>
                                <tr>
                                    <td id="tdLabel"><font color="black">No. Lot :</font></td>
                                    <td id="tdDisplay">${actionBean.hakmilik.noLot}&nbsp;</td>
                                </tr>
                                <tr>
                                    <td id="tdLabel"><font color="black">Luas :</font></td>
                                    <td id="tdDisplay"><fmt:formatNumber pattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp;${actionBean.hakmilik.kodUnitLuas.nama}</td>
                                </tr>
                                <tr>
                                    <td id="tdLabel"><font color="black">Tuan Tanah :</font></td>
                                    <td id="tdDisplay">${actionBean.hakmilikPihakBerkepentingan.pihak.nama}&nbsp;</td>
                                </tr>
                                <tr>
                                    <td id="tdLabel"><font color="black">Kategori Kegunaan Tanah :</font></td>
                                    <td id="tdDisplay">${actionBean.hakmilik.kegunaanTanah.nama}&nbsp;</td>
                                </tr>
                                <tr>
                                    <td id="tdLabel"><font color="black">Syarat Nyata :</font></td>
                                    <td id="tdDisplay">${actionBean.hakmilik.syaratNyata.syarat}&nbsp;</td>
                                </tr>
                                <tr>
                                    <td id="tdLabel"><font color="black">Sekatan Kepentingan :</font></td>
                                    <td id="tdDisplay">${actionBean.hakmilik.sekatanKepentingan.sekatan}&nbsp;</td>
                                </tr>
                                <tr>
                                    <td id="tdLabel"><font color="black">Cukai Tanah :</font></td>
                                    <td id="tdDisplay"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.hakmilik.cukai}"/>&nbsp;</td>
                                </tr>
                                <tr>
                                    <td id="tdLabel"><font color="black">Kawasan Rizab :</font></td>
                                    <td id="tdLabel">${actionBean.hakmilik.rizab.nama}&nbsp;</td>
                                </tr>
                            </table>
                        </td></tr><br>
                   
                   <c:if test="${edit}">
                        <tr><td><b><font style="text-transform: uppercase">4. HURAIAN PENTADBIR TANAH  ${actionBean.hakmilik.daerah.nama}&nbsp;</font></b></td></tr>
                        <tr><td><s:textarea name="huraianPentadbir" rows="5" cols="150"/></td></tr><br>
                        <tr><td><b><font style="text-transform: uppercase">5. SYOR PENTADBIR TANAH  ${actionBean.hakmilik.daerah.nama}&nbsp;</font></b></td></tr>
                        <tr><td><s:textarea name="syorPentadbir" rows="5" cols="150"/></td></tr><br>
                    </c:if>

                    <c:if test="${!edit}">
                        <tr><td><b><font style="text-transform: uppercase">4. HURAIAN PENTADBIR TANAH  ${actionBean.hakmilik.daerah.nama}&nbsp;</font></b></td></tr>
                        <tr><td>${actionBean.huraianPentadbir}&nbsp;</td></tr><br>
                        <tr><td><b><font style="text-transform: uppercase">5. SYOR PENTADBIR TANAH  ${actionBean.hakmilik.daerah.nama}&nbsp;</font></b></td></tr>
                        <tr><td>${actionBean.syorPentadbir}&nbsp;</td></tr><br>
                    </c:if>
                    <tr><td><b><font style="text-transform: uppercase">6. KEPUTUSAN PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama} &nbsp;</font></b></td></tr>
                    <c:if test="${edit}">
                        <tr><td width="100%">6.1 Permohonan ini &nbsp; <s:radio name="keputusanPTD" value="L"/>Diluluskan / <s:radio name="keputusanPTD" value="T"/>Ditolak </td></tr><br>
                        <tr><td>6.2 Catatan : &nbsp;
                            <s:textarea name="ulasanPTD" cols="150" rows="5"/></td></tr><br>
                    </c:if>
                    <c:if test="${!edit}">
                        <c:if test="${actionBean.keputusanPTD eq 'L'}">
                            <tr><td>6.1 Permohonan ini : Diluluskan &nbsp;</td></tr>
                        </c:if>
                        <c:if test="${actionBean.keputusanPTD eq 'T'}">
                            <tr><td>6.1 Permohonan ini : Ditolak &nbsp;</td></tr>
                        </c:if>
                        <tr><td>6.2 Catatan : ${actionBean.ulasanPTD} &nbsp;</td></tr>
                    </c:if>
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
