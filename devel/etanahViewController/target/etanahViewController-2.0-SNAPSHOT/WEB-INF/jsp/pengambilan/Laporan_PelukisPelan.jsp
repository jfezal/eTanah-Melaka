<%-- 
    Document   : Laporan_PelukisPelan
    Created on : 13-Jan-2010, 14:37:40
    Author     : nordiyana
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%--<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>--%>
<script type="text/javascript">

function validation() {
        if($("#halhalLain").val() == ""){
            alert('Sila pilih " Di tanda untuk projek Kerajaan " terlebih dahulu.');
            $("#halhalLain").focus();
            return true;
        }
        
    }

        function save1(event, f){
            if(validation()){

            }
            else{
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    self.close();
                },'html');
            }
        }
        </script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pengambilan.LaporanPelukisPelanActionBean">
<s:messages/>
<s:errors/>
    <div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Status Tanah :
        </legend>
        <div class="content" align="center">
            <table>
                    <tr>
                        <td class="rowlabel1">Tanah Kerajaan :</td>
                        <td class="input1">
                            <s:checkbox name="laporanTanah.dilintasLaluanGas" value="Y"/>&nbsp; Tanah Milik<br>
                            <%--<s:select name="bandarPekanMukim.kod" id="bpm"  >
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="kod" sort="nama" />
                            </s:select>--%>
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                        </table>
                        <div class="content" align="center">
                    Tanah Milik
                    <display:table  name="${actionBean.permohonan.senaraiHakmilik}" id="line" class="tablecloth">
                        <display:column title="Nombor Hakmilik">
                            <c:if test="${line.hakmilik.idHakmilik ne null}"> ${line.hakmilik.idHakmilik}&nbsp; </c:if>
                            <c:if test="${line.hakmilik.idHakmilik eq null}"> Tiada Data </c:if>
                        </display:column>
                        <display:column title="Nombor Lot/PT" >
                            <c:if test="${line.hakmilik.noLot ne null}"> ${line.hakmilik.noLot}&nbsp; </c:if>
                            <c:if test="${line.hakmilik.noLot eq null}"> Tiada Data </c:if>
                        </display:column>
                    <display:column title="Kemaskini">
                    <div align="center">
                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                    id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${actionBean.tanahRizabList[line_rowNum-1].idTanahRizabPermohonan}');"/>
                    </div>
                    </display:column>
                    <display:column title="Hapus">
                   <div align="center">
                   <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                    id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.tanahRizabList[line_rowNum-1].idTanahRizabPermohonan}');" />
                   </div>
                    </display:column>
                    </display:table>
                </div>
        </div>
    </fieldset>
    </div>
        <br>
        <div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Status Tanah :
        </legend>
        <div class="content" align="center">
            <table>
                    <tr>

                        <td class="rowlabel1">Status Tanah :</td>
                        <td class="input1">
                             <s:checkbox name="laporanTanah.dilintasLaluanGas" value="Y"/>&nbsp; Tanah Kerajaan<br>
                             <s:checkbox name="laporanTanah.dilintasLaluanGas" value="Y"/>&nbsp; Tanah Milik<br>
                             <s:checkbox name="laporanTanah.dilintasLaluanGas" value="Y"/>&nbsp; Tanah Rizab<br>
                             </td>
                             <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">&nbsp;</td>
                        <td class="input1">&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Kedudukan Tanah :</td>
                        <td class="input1">
                             <s:checkbox name="laporanTanah.dilintasLaluanGas" value="Y"/>&nbsp; Tanah Desa<br>
                             <s:checkbox name="laporanTanah.dilintasLaluanGas" value="Y"/>&nbsp; Tanah Pekan<br>
                             <s:checkbox name="laporanTanah.dilintasLaluanGas" value="Y"/>&nbsp; Bandar<br>
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">&nbsp;</td>
                        <td class="input1">&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Kawasan Pihak Berkuasa Tempatan :</td>
                        <td class="input1">
                             <s:radio name="projekKerajaan" value="Ya" id="projekKerajaan"/>&nbsp;Dalam
                             <s:radio name="projekKerajaan" value="Tidak" id="projekKerajaan"/>&nbsp;Luar
                        </td>
                        <td>&nbsp;</td>
                    </tr
                    <tr>
                        <td class="rowlabel1">&nbsp;</td>
                        <td class="input1">No Warta/Tarikh Warta<s:text name="catatan" size="20" id="halhalLain" />/<s:text name="catatan1" size="20" id="halhalLain" />&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                    
                    <tr>
                        <td class="rowlabel1">Tanah Rizab Melayu :</td>
                        <td class="input1">
                             <s:radio name="rizabMelayu" value="Ya" id="tSimpananMelayu"/>&nbsp;Dalam
                             <s:radio name="rizabMelayu" value="Tidak" id="tSimpananMelayu"/>&nbsp;Luar</td>
                        <td>&nbsp;</td>
                    </tr>
                     <tr>
                        <td class="rowlabel1">&nbsp;</td>
                        <td class="input1">No Warta/Tarikh Warta<s:text name="catatan" size="20" id="halhalLain" />/<s:text name="catatan1" size="20" id="halhalLain" />&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Tanah Rizab Hutan Simpan :</td>
                        <td class="input1">
                             <s:radio name="rizabHutan" value="Ya" id="rizabhutan"/>&nbsp;Dalam
                             <s:radio name="rizabHutan" value="Tidak" id="rizabhutan"/>&nbsp;Luar</td>
                        <td>&nbsp;</td>
                    </tr>
                     <tr>
                        <td class="rowlabel1">&nbsp;</td>
                        <td class="input1">No Warta/Tarikh Warta<s:text name="catatan" size="20" id="halhalLain" />/<s:text name="catatan1" size="20" id="halhalLain" />&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Kawasan Penempatan Berkelompok(GSA) :</td>
                        <td class="input1">
                             <s:radio name="tanahGSA" value="Ya" id="tanahgsa"/>&nbsp;Dalam
                             <s:radio name="tanahGSA" value="Tidak" id="tanahgsa"/>&nbsp;Luar</td>
                        <td>&nbsp;</td>
                    </tr>
                     <tr>
                        <td class="rowlabel1">&nbsp;</td>
                        <td class="input1">No Warta/Tarikh Warta<s:text name="catatan" size="20" id="halhalLain" />/<s:text name="catatan1" size="20" id="halhalLain" />&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Permohonan Terdahulu :</td>
                        <td class="input1">
                             <s:radio name="permohonanTerdahulu" value="Ada" id="pterdahulu"/>&nbsp;Dalam
                             <s:radio name="permohonanTerdahulu" value="Tiada" id="pterdahulu"/>&nbsp;Luar</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                         <td class="rowlabel1">Laporan :</td>
                         <td class="input1"><s:textarea name="catatan" rows="4" cols="20" id="halhalLain" />
                            </td>
                         <td>&nbsp;</td>
                    </tr>

                    <tr>
                        <td>&nbsp;</td>
                        <td>
                            <s:submit name="charting" value="Charting" class="btn"/>&nbsp;
                            <s:button name="savePelukispelanInfo" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>
                            <%--doSubmit(this.form, this.name, 'page_div');--%>
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                </table>
        </div>
    </fieldset>

</div>
    </s:form>
