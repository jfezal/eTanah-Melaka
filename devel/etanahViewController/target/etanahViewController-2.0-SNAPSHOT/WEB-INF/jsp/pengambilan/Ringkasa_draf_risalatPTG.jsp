<%--
    Document   : Kertas_Rencana_MMKN
    Created on : June 24, 2011, 4:59:55 PM
    Author     : Rajesh
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<!--<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>-->
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />



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
<script language="javascript" type="text/javascript">


    function filterHakmilik(f) {
        var idMohonHakmilik = f
        alert(idMohonHakmilik);
        //var q = $(f).formSerialize();
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });
        $.post('${pageContext.request.contextPath}/pengambilan/RingkasanDrafRisalatPTG?searchDetail&idMohonHakmilik=' + idMohonHakmilik,
                function(data) {
                    if (data != '') {
                        $('#partialKodBPM').html(data);
                        $.unblockUI();
                    }
                }, 'html');
//                 location.reload(true);
    }

    function kemaskiniTajuk(val, val2) {

        var idHakmilik = $('#hakmilik').val();
//        alert("id kandungan = " + val);
        var url = '${pageContext.request.contextPath}/pengambilan/RingkasanDrafRisalatPTG?kemaskiniMMKN&idKertas=' + val + '&parameter=' + val2;
        window.open(url, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes");
    }

    function save(event, f) {
//        alert("woi");
//        if (doValidation()) {
//            doBlockUI();
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.ajax({
            type: "POST",
            url: url,
            dataType: 'html',
            data: q,
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
            },
            success: function(data) {
                $('#page_div', opener.document).html(data);
//                    $.unblockUI();
//                    self.close();
            }
        });
    }
</script>


<s:form beanclass="etanah.view.stripes.pengambilan.RingkasanDrafRisalatPTG" id="RingkasanDrafRisalatPTG">
    <s:messages/>
    <s:errors/>

    <br/>


    <style type="text/css">
        #vertical-2 thead,#vertical-2 tbody{
            display:inline-block;
        }

    </style>
    <font size="5"><center><b><Strong><u>Ringkasan Risalat PTG</u></Strong></b></center></font>
    <table  style="width:100%;" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
        <br>
   
            <tr>
                <th colspan="100" width ="150">Tajuk </th>
                <td width="1000">${actionBean.risalat.tajuk}</td>
                <td>
                    <img alt='Kemaskini' border='1' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                         id='rem_${line_rowNum}' onclick="kemaskiniTajuk('${actionBean.risalat.idKertas}', 'Tajuk')" onmouseover="this.style.cursor = 'pointer';">
                </td>
            </tr>
            <tr>
                <th colspan="100" width ="150">Risalat MMKN</th>
                <td width="1000">${actionBean.risalat.item}</td>
                <td>
                    <img alt='Kemaskini' border='1' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                         id='rem_${line_rowNum}' onclick="kemaskiniTajuk('${actionBean.risalat.idKertas}', 'Risalat MMKN')" onmouseover="this.style.cursor = 'pointer';">
                </td>
            </tr>
            <tr>
                <th colspan="100" width ="150">No. Ruj. PTG </th>
                <td width="1000">${actionBean.risalat.noRujukan}</td>
                <td>
                    <img alt='Kemaskini' border='1' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                         id='rem_${line_rowNum}' onclick="kemaskiniTajuk('${actionBean.risalat.idKertas}', 'No. Ruj. PTG')" onmouseover="this.style.cursor = 'pointer';">
                </td>
            </tr>
    
    </table>
    <br>
    <table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
        <tfoot>
            <tr>    
                <td colspan="4"><center><b>Ringkasan Permohonan</b></center></td>  
            </tr>
        </tfoot>
    </table>
    <br>

    <table  style="width:100%;" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
        <thead>
            <tr>
                <th colspan="100" width ="150" height="45">Pemohon : </th> 
            </tr>
        </thead>
        <c:forEach items="${actionBean.listPemohon}" var="line">
            <td>${line.pihak.nama} 
                <c:if test="${not empty line.pihak.noPengenalan}">
                <li>( ${line.pihak.jenisPengenalan.nama} : ${line.pihak.noPengenalan})</li>
                </c:if>
            </c:forEach>
    </table> 


    <table  style="width:100%;" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
        <thead>
            <tr>
                <th colspan="100" width ="150" height="30">Perihal Tanah <br> Lot/Pt : </th> 
                <td width="1000">${actionBean.hakmilik.idHakmilik}
        <li> Luas : ${actionBean.hakmilik.luas} ${actionBean.hakmilik.kodUnitLuas.nama}</li></td>
</tr>
</thead>
</table>

<table  style="width:100%;" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
    <thead>
        <tr>
            <th colspan="100" width ="150" height="30">Mukim : </th> 
            <td width="1000">${actionBean.hakmilik.idHakmilik} 
    <li>(${actionBean.hakmilik.bandarPekanMukim.nama})</li></td>
</tr>
</thead>
</table> 
<table  style="width:100%;" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
    <thead>
        <tr>
            <th colspan="100" width ="150" height="30">Sekatan Kepentingan : </th> 
            <td width="1000">${actionBean.hakmilik.sekatanKepentingan.sekatan} 
            </td>
        </tr>
    </thead>
</table> 
<table  style="width:100%;" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
    <thead>
        <tr>
            <th colspan="100" width ="150" height="30">Syarat Nyata : </th> 
            <td width="1000">${actionBean.hakmilik.syaratNyata.syarat} 
            </td>
        </tr>
    </thead>
</table> 

<table  style="width:100%;" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">

    <th colspan="100" width ="150" height="30">Lokasi : </th> 
    <td width="1000">
        ${actionBean.risalat.lokasi}
    </td>
<td>
    <img alt='Kemaskini' border='1' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
         id='rem_${line_rowNum}' onclick="kemaskiniTajuk('${actionBean.risalat.idKertas}', 'lokasi')" onmouseover="this.style.cursor = 'pointer';">
</td>
</table> 

<table  style="width:100%;" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
    <th colspan="100" width ="150" height="30">Keadaan Tanah : </th> 
    <td width="1000">
        ${actionBean.risalat.keadaanTanah} 
    </td>
    <td>
        <img alt='Kemaskini' border='1' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
             id='rem_${line_rowNum}' onclick="kemaskiniTajuk('${actionBean.risalat.idKertas}', 'Keadaan Tanah')" onmouseover="this.style.cursor = 'pointer';">
    </td>
</table>

<table  style="width:100%;" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
    <th colspan="100" width ="150" height="30">Ulasan Adun : </th> 
    <td width="1000">
   
<c:if test="${actionBean.risalat.agensi.kategoriAgensi.kod eq 'ADN'}">
     ${actionBean.risalat.agensi.nama} 
    <li>${actionBean.risalat.ulasanAdun}</li> 
</c:if>
</td>
<td>
    <img alt='Kemaskini' border='1' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
         id='rem_${line_rowNum}' onclick="kemaskiniTajuk('${actionBean.risalat.idKertas}', 'Ulasan Adun')" onmouseover="this.style.cursor = 'pointer';">
</td>
</table>

<br>
<table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
    <tfoot>
        <tr>    
            <td colspan="10"><center><b>Ulasan Teknikal</b></center></td>  
        </tr> 
    </tfoot>
</table>
<br>

<display:table style="width:100%;" class="tablecloth" name="${actionBean.mohonRujLuarList}"
               cellpadding="0" cellspacing="0" id="line" >
    <center>
        <display:column title="Bil." sortable="true"><center>${line_rowNum}.</center></display:column>
            <display:column property="agensi.nama" title="Ulasan Daripada" class="kandungan" />
            <display:column property="diSokong" title="Tindakan" class="diSokong" />
            <display:column property="ulasan" title="Ulasan" class="ulasan" />

    </center>
</display:table>

<br>

<table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
    <tfoot>
        <tr>    
            <td colspan="10"><center><b>Perakuan PTG Melaka</b></center></td>  
        </tr> 
    </tfoot>
</table>
<br>

<c:if test="${actionBean.risalat.keputusan ne null}">
   <table  style="width:100%;" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
    <th colspan="100" width ="150" height="30">Keputusan PTG : </th> 
    <td width="1000">
        ${actionBean.risalat.keputusan} 
    <li>${actionBean.risalat.perakuanPTG}</li> 

</td>
<td>
    <img alt='Kemaskini' border='1' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
         id='rem_${line_rowNum}' onclick="kemaskiniTajuk('${actionBean.risalat.idKertas}', 'Perakuan PTG')" onmouseover="this.style.cursor = 'pointer';">
</td>
</table>
</c:if>

<br>
<c:if test="${actionBean.risalat.keputusan eq null}">
    <center><s:button name="Perakuan PTG" class="btn" value="Tambah" onclick="kemaskiniTajuk('${actionBean.risalat.idKertas}','Perakuan PTG');"/></center>
</c:if>
&nbsp;&nbsp;
<br>

<table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
    <tfoot>
        <tr>    
            <td colspan="10"><center><b>Permohonan terdahulu</b></center></td>  
        </tr> 
    </tfoot>
</table>
<br>
 <display:table style="width:100%;" class="tablecloth" name="${actionBean.senaraiFasaPermohonan}"
               cellpadding="0" cellspacing="0" id="line" >
    <center>
        <display:column title="Bil." sortable="true"><center>${line_rowNum}.</center></display:column>
            <display:column property="permohonan.idPermohonan" title="Ulasan Daripada" class="kandungan" />
            <display:column property="keputusan.nama" title="Keputusan" class="diSokong" />
            <display:column property="ulasan" title="Ulasan" class="ulasan" />

    </center>
</display:table>





</s:form>
