<%-- 
    Document   : nota_siasatan_pembatalan
    Created on : Oct 26, 2010, 10:39:10 AM
    Author     : Rajesh
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252" import="java.util.*" import="java.text.SimpleDateFormat"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">
$(document).ready( function(){


$(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
$(".datepicker1").datepicker({dateFormat: 'yy'});
});
function ajaxLink(link, update) {
$.get(link, function(data) {
$(update).html(data);
$(update).show();
});
return false;
}

function tambah(){
window.open("${pageContext.request.contextPath}/pengambilan/nota_siasatan?showTuanTanahPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }
</script>

<s:form name="notaForm" beanclass="etanah.view.stripes.pengambilan.NotaSiasatanPembatalanActionBean">
    <s:messages/>
    <s:errors/>

    <div  id="hakmilik_details">
        <div align="center">
            <table style="width:99.2%" bgcolor="purple">
                <tr><td width="100%" height="20" ><div style="background-color: purple;" align="center">
                            <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;"><font color="white">NOTA SIASATAN PEMBATALAN HAK LALU LALANG </font></font>
                        </div></td></tr>
            </table>
        </div>
        <s:hidden name="hakmilikPerbicaraan.idPerbicaraan" />
        <fieldset class="aras1">
            <legend >
                <b>Maklumat Hakmilik Permohonan</b>
            </legend><br/>
            <p align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/nota_siasatan_pembatalan" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column title="ID Hakmilik">
                        <s:link beanclass="etanah.view.stripes.pengambilan.NotaSiasatanPembatalanActionBean"
                            event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                        <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}
                        </s:link>
                    </display:column>
                    <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column title="Luas Sebenar"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column title="Luas Yang Diperlukan"><fmt:formatNumber pattern="#,##0.00" value="${line.luasTerlibat}"/>&nbsp;${line.kodUnitLuas.nama}</display:column>
                </display:table>
            </p>
            <br/><br/><br/>
             <c:if test="${actionBean.hakmilik ne null}">
            
          <legend>Kehadiran</legend>
           <br/><br/>
            <p align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilik.senaraiPihakBerkepentingan}"  cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/nota_siasatan_pembatalan" id="line1">
                    <display:column title="No" sortable="true">${line1_rowNum}</display:column>
                    <display:column property="pihak.nama" title="Nama" />
                    <display:column property="pihak.noPengenalan" title="No KP" />
                    <display:column title="Alamat" style="vertical-align:baseline">
                        ${line1.pihak.alamat1}
                        <c:if test="${line1.pihak.alamat2 ne null}"> , ${line1.pihak.alamat2} </c:if>
                        <c:if test="${line1.pihak.alamat3 ne null}"> , ${line1.pihak.alamat3} </c:if>
                        <c:if test="${line1.pihak.alamat4 ne null}"> , ${line1.pihak.alamat4} </c:if>
                    </display:column>
                    <display:column property="pihak.poskod" title="Poskod"/>
                    <display:column property="pihak.negeri.nama" title="Negeri"/>
                    <display:column title="No Tel" style="vertical-align:baseline">
                        ${line1.pihak.noTelefon1}
                        <c:if test="${line1.pihak.noTelefon2 ne null}">, ${line1.pihak.noTelefon2} </c:if>
                    </display:column>
                    <%--<display:column title="Jawatan"/>--%>
                    <display:column title="Status Kehadiran">
                         <s:radio name="hadir[${line1_rowNum-1}]" value="1"/>Hadir
                         <s:radio name="hadir[${line1_rowNum-1}]" value="0"/>Tidak Hadir
                    </display:column>>
                </display:table>
                        <%--<s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahKehadiran('line1');"/>&nbsp;
                        <s:button class="btn" value="Hapus" name="remove" id="remove" onclick="deleteKehadiran('line1');"/>&nbsp;--%>
            </p>
            <br/><br/>
                <table>
                    <tr><td width="30%"><b>1) Penyerahan DHKE : <s:radio name="penjalasan" value="Y" onclick="showPenjalasan();"/>&nbsp;Ya
                                &nbsp;<s:radio name="penjalasan" value="T" onclick="hidePenjalasan();"/>&nbsp;Tidak</b></td>
                    </tr>
                    <br/>
                    <tr>
                        <td width="40%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ulasan : <s:textarea name="hakmilikPerbicaraan.catatan" rows="3" cols="50" id="catatan"/></b></td>
                    </tr>
                    <br/>
                    <tr><td width="40%"><b>2) Endosan Pembatalan Hak Lalu Lalang  : <s:radio name="penjalasan" value="Y" onclick="showPenjalasan();"/>&nbsp;Ya
                                &nbsp;<s:radio name="penjalasan" value="T" onclick="hidePenjalasan();"/>&nbsp;Tidak</b></td>
                    </tr>
                    <br/>
                    <tr>
                        <td width="40%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ulasan : <s:textarea name="hakmilikPerbicaraan.catatan" rows="3" cols="50" id="catatan"/></b></td>
                    </tr>
                </table>
                    <br/>
                    <p align="center">
                        <s:hidden name="idHakmilik"/>
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        <c:if test="${showPerintah}">
                            <s:button name="perintah" value="Perintah" class="btn" onclick="popupPerintah('${actionBean.idHakmilik}');"/>
                        </c:if>
                    </p>
                    </c:if>

                     </fieldset>
    </div>

</s:form>

