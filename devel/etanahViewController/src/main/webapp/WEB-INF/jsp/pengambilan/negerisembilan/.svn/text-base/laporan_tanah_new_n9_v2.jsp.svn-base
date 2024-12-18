<%-- 
    Document   : laporanTanahPengambilan_new_n9_v2
    Created on : 4-Mac-2010, 10:32:27
    Author     : massita
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    $(document).ready(function() {

        if($('#adaBangunan').val() == 'Y')
        $('#bilanganBangunan').show();

        var v = '${actionBean.laporanTanah.kecerunanTanah.kod}';

        if(v == 'RT'){
            $('#tinggi').hide();
            $('#cerun').hide();
            $('#dalam').hide();
        }

        else if(v == 'BK'){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }

        else if(v == 'TG'){
            $('#tinggi').show();
            $('#cerun').hide();
            $('#dalam').hide();
        }

        else if(v == 'RD'){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }

        else if(v == 'CR'){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }

        else if(v == 'PY'){
            $('#tinggi').hide();
            $('#cerun').hide();
            $('#dalam').show();
        }


        $('#jenisRizab').hide();
        $('#noWarta').hide();

        $('#nyataRancangan').hide();
        $('#tanahMilik').hide();
        $('#tujuan').hide();

        $('#catatanTanahMilik').hide();
        $('#catatanPBT').hide();

    });

    function changeRancangan(value){

        if(value == "ada")
            $('#nyataRancangan').show();

        else
            $('#nyataRancangan').hide();
    }

    function changePBT(value){

        if(value == "dalam")
            $('#catatanPBT').show();

        else
            $('#catatanPBT').hide();
    }

    function changeTanahMilik(value){

        if(value == "ya"){
            $('#tanahMilik').show();
            $('#catatanTanahMilik').show();
        }

        else{
            $('#tanahMilik').hide();
            $('#catatanTanahMilik').hide();
        }
    }

    function changeKategoriTanah(value){

        if(value == "kerajaan"){
            $('#jenisRizab').hide();
            $('#noWarta').hide();
        }
        else if(value == "milik"){
            $('#jenisRizab').hide();
            $('#noWarta').hide();
        }
        else if(value == "rizab"){
            $('#jenisRizab').show();
            $('#noWarta').show();
        }
    }

    function changeTujuan(value){

        if(value == "Lain-lain")
            $('#tujuan').show();
        else
            $('#tujuan').hide();
    }

    function changeKeadaanTanah(text){

        if(text == "Rata"){
            $('#tinggi').hide();
            $('#cerun').hide();
            $('#dalam').hide();
        }

        else if(text == "Berbukit"){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }

        else if(text == "Tinggi"){
            $('#tinggi').show();
            $('#cerun').hide();
            $('#dalam').hide();
        }

        else if(text == "Rendah"){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }

        else if(text == "Curam"){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }

        else if(text == "Berpaya"){
            $('#tinggi').hide();
            $('#cerun').hide();
            $('#dalam').show();
        }
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function removeNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }
    function popup(i){

        var d = $('.x'+i).val();
            window.open("${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan?showeditTanahRizab&idTanahRizabPermohonan="+d, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
}


function refreshRizab(){
var url = '${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan?refreshpage';
$.get(url,
function(data){
$('#page_div').html(data);
},'html');
}
function removeSingle(idTanahRizabPermohonan)
{
if(confirm('Adakah anda pasti?')) {
var url = '${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan?deleteSingle&idTanahRizabPermohonan='
+idTanahRizabPermohonan;
$.get(url,
function(data){
$('#page_div').html(data);
},'html');}
}

function tambahBaru(){
window.open("${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan?hakMilikPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

function showBilBangunan() {
     $('#bilanganBangunan').show();


	<%--document.forms[0].amt.style.display = '';--%>
}
function hideBilBangunan() {
    $('#bilanganBangunan').hide();

	<%--document.forms[0].amt.style.display = 'none';--%>
}

    function checkPelan(f){
                //alert(f);
                var noLot = f
                var kodDaerah = $('#kodDaerah').val();
                var kodBPM = $('#kodBPM').val();
                var kodNegeri = $('#kodNegeri').val();
                //alert(kodDaerah);
                //var q = $(e).formSerialize();
                $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?checkPelan&noLot='+noLot+'&kodDaerah='+kodDaerah+'&kodNegeri='+kodNegeri+'&kodBPM='+kodBPM,
                function(data){
                    if(data != '1'){
                        //alert(data);
                        alert('Pelan untuk no lot '+ noLot +' tiada');
                        $("#noLot").val("");
                        $("#noLot").focus();
                    }
                }, 'html');
            }


function addImage(idHakmilik,rowNo) {
        var idDokumen = document.getElementById("imej_"+rowNo).options[document.getElementById("imej_"+rowNo).selectedIndex].value;
        $("#imej"+rowNo).val(idDokumen);

        $.post('${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan?addHakmilikImage&idDokumen='+idDokumen+'&idHakmilik='+idHakmilik,
                function(data){
                    $('#page_div').html(data);
                }, 'html');

            }

function addImageUtara(){
    var idDokumen = document.getElementById("utaraImej").options[document.getElementById("utaraImej").selectedIndex].value;
        $.post('${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan?addLaporanImage&pandanganImej=U&idDokumen='+idDokumen,
                function(data){
                    $('#page_div').html(data);
                }, 'html');
}

function addImageSelatan(){
    var idDokumen = document.getElementById("selatanImej").options[document.getElementById("selatanImej").selectedIndex].value;
        $.post('${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan?addLaporanImage&pandanganImej=S&idDokumen='+idDokumen,
                function(data){
                    $('#page_div').html(data);
                }, 'html');
}

function addImageTimur(){
    var idDokumen = document.getElementById("timurImej").options[document.getElementById("timurImej").selectedIndex].value;
        $.post('${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan?addLaporanImage&pandanganImej=T&idDokumen='+idDokumen,
                function(data){
                    $('#page_div').html(data);
                }, 'html');
}

function addImageBarat(){
    var idDokumen = document.getElementById("baratImej").options[document.getElementById("baratImej").selectedIndex].value;
        $.post('${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan?addLaporanImage&pandanganImej=B&idDokumen='+idDokumen,
                function(data){
                    $('#page_div').html(data);
                }, 'html');
}

function doViewReport(rowNo) {
        var idDokumen = document.getElementById("imej_"+rowNo).options[document.getElementById("imej_"+rowNo).selectedIndex].value;
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function doViewReportUtara() {
        var idDokumen = document.getElementById("utaraImej").options[document.getElementById("utaraImej").selectedIndex].value;
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function doViewReportSelatan() {
        var idDokumen = document.getElementById("selatanImej").options[document.getElementById("selatanImej").selectedIndex].value;
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function doViewReportTimur() {
        var idDokumen = document.getElementById("timurImej").options[document.getElementById("timurImej").selectedIndex].value;
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function doViewReportBarat() {
        var idDokumen = document.getElementById("baratImej").options[document.getElementById("baratImej").selectedIndex].value;
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function doView(idDokumen) {
       // alert(idDokumen);
       // var idDokumen = document.getElementById("imej_"+rowNo).options[document.getElementById("imej_"+rowNo).selectedIndex].value;
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function ajaxLink(link, update) {
$.get(link, function(data) {
$(update).html(data);
$(update).show();
});
return false;
}
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.LaporanTanahPengambilanActionBean">
    <s:messages/>
    <s:hidden name="permohonan.idPermohonan" />
    <div id="hakmilik_details">
  <div class="subtitle">
        <fieldset class="aras1">
            <legend>Perihal Tanah</legend>
            <br>
           
            <div class="content" align="center">
             
             <br>
             <br>
            <p align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/laporanTanahPengambilan" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column title="ID Hakmilik">
                        <s:link beanclass="etanah.view.stripes.pengambilan.LaporanTanahPengambilanActionBean"
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
            </div>
            <c:if test="${actionBean.hakmilik ne null}">
            Maklumat Asas Tanah

            <p>
                <label>Syarat Nyata Tanah :</label>
                <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama}&nbsp; </c:if>
                <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama eq null}"> Tiada Data </c:if>
            </p>
            <p>
                <label>Sekatan kepentingan :</label>
                <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama}&nbsp; </c:if>
                <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama eq null}"> Tiada Data </c:if>
            </p>
            <p>
                <label>Bebanan :</label>
                <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama}&nbsp; </c:if>
                <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama eq null}"> Tiada Data </c:if>
            </p>
            <p>
                <label>Tarikh Pindah Milik Terakhir :</label>
                <c:if test="${actionBean.permohonan.infoAudit.tarikhMasuk ne null}"><s:format formatPattern="dd/MM/yyyy" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>&nbsp;</c:if>
                <c:if test="${actionBean.permohonan.infoAudit.tarikhMasuk eq null}"> Tiada Data </c:if>

            </p>
            <p>
                <label>Pemilik Berdaftar :</label>
                <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama}&nbsp; </c:if>
                <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama eq null}"> Tiada Data </c:if>
            </p>
            <p>
                <label>Pengambilan balik yang lalu terhadap tanah ini :</label>
                <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama}&nbsp; </c:if>
                <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama eq null}"> Tiada Data </c:if>
            </p>
            <p>
                <label>Keadaan Tanah :</label>
                <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama}&nbsp; </c:if>
                <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama eq null}"> Tiada Data </c:if>
            </p>
            <p>
                <label>Jenis Kegunaan Tanah :</label>
                <s:hidden name="adaBangunan" id="adaBangunan" value="${actionBean.laporanTanah.adaBangunan}"/>
                <s:radio name="laporanTanah.adaBangunan" value="Y" onclick="showBilBangunan();"/>&nbsp;Bangunan
                <s:radio name="laporanTanah.adaBangunan" value="T" onclick="hideBilBangunan();"/>&nbsp;Pertanian
            </p><br /><br />
            
            <table align="center">
                <p align="left">
                        <s:radio name="laporanTanah.adaBangunan" value="T" onclick="hideBilBangunan();"/>&nbsp;Bangunan<br />
                        i)  Jenis Pertanian<br />
                        ii) Luas yang ditanam<br />
                        iii)Anggaran bil. Pokok<br />
                        iv) Nilaian Tanaman(RM)<br />
               </p>
           </table><br /><br />

           <table align="center">
               <p align="left">
                    <s:radio name="laporanTanah.adaBangunan" value="T" onclick="hideBilBangunan();"/>&nbsp;Pertanian<br />
                    i)  Jenis Bangunan :<br />
                    <s:radio name="laporanTanah.kekal" value="k" />&nbsp;kekal
                    <s:radio name="laporanTanah.separuhKekal" value="sk" />&nbsp;separuh kekal
                    <s:radio name="laporanTanah.sementara" value="sm" />&nbsp;sementara<br />
                    ii) ukuran bangunan<br />
                    iii)nilaian bangunan<br />
                    iv) tarikh bangunan dibina<br />
                    v) nama tuan punya bangunan<br />
             </p>
           </table><br /><br />

           <div class="subtitle">
        <fieldset class="aras1">
            <legend>Latar belakang Tanah</legend>
                <div class="content" align="center">
                </div>
                <div class="content" align="center">
                    <table class="tablecloth">
                        <tr>
                            <th>&nbsp;</th><th>Jenis Tanah</th><th>Nombor Lot</th><th>Kegunaan</th><th>Gambar</th>
                        </tr>
                        <tr>
                            <th>
                                Utara
                            </th>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanUtaraMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanUtaraMilikKerajaan eq 'T'}">Milik</c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot ne null}"> ${actionBean.laporanTanah.sempadanUtaraNoLot}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot eq null}"> Tiada Data </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanUtaraKegunaan ne null}"> ${actionBean.laporanTanah.sempadanUtaraKegunaan}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanUtaraKegunaan eq null}"> Tiada Data </c:if>
                            </td>
                            <td>
                               <c:forEach items="${actionBean.utaraImejLaporanList}" var="senarai">
                                   <c:out value="${senarai.dokumen.tajuk}" /><br/>
                               </c:forEach>
                               <s:select name="utaraImej" id="utaraImej">
                                   <s:option value="">Sila pilih imej</s:option>
                                   <s:options-collection collection="${actionBean.dokumenList}" label="tajuk" value="idDokumen"/>
                               </s:select>
                               <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                    onclick="doViewReportUtara();" height="30" width="30" alt="papar"
                                    onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                               <s:button class="btn" value="Add" name="addUtaraImej" id="addUtaraImej" onclick="addImageUtara();"/>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Selatan
                            </th>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanSelatanMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanSelatanMilikKerajaan eq 'T'}">Milik</c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanSelatanNoLot ne null}"> ${actionBean.laporanTanah.sempadanSelatanNoLot}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanSelatanNoLot eq null}"> Tiada Data </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanSelatanKegunaan ne null}"> ${actionBean.laporanTanah.sempadanSelatanKegunaan}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanSelatanKegunaan eq null}"> Tiada Data </c:if>
                            </td>
                            <td>
                               <c:forEach items="${actionBean.selatanImejLaporanList}" var="senarai">
                                   <c:out value="${senarai.dokumen.tajuk}" /><br/>
                               </c:forEach>
                               <s:select name="selatanImej" id="selatanImej">
                                   <s:option value="">Sila pilih imej</s:option>
                                   <s:options-collection collection="${actionBean.dokumenList}" label="tajuk" value="idDokumen"/>
                               </s:select>
                               <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                    onclick="doViewReportSelatan();" height="30" width="30" alt="papar"
                                    onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                               <s:button class="btn" value="Add" name="addSelatanImej" id="addSelatanImej" onclick="addImageSelatan();"/>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Timur
                            </th>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanTimurMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanTimurMilikKerajaan eq 'T'}">Milik</c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanTimurNoLot ne null}"> ${actionBean.laporanTanah.sempadanTimurNoLot}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanTimurNoLot eq null}"> Tiada Data </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanTimurKegunaan ne null}"> ${actionBean.laporanTanah.sempadanTimurKegunaan}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanTimurKegunaan eq null}"> Tiada Data </c:if>
                            </td>
                            <td>
                                <c:forEach items="${actionBean.timurImejLaporanList}" var="senarai">
                                   <c:out value="${senarai.dokumen.tajuk}" /><br/>
                               </c:forEach>
                               <s:select name="timurImej" id="timurImej">
                                   <s:option value="">Sila pilih imej</s:option>
                                   <s:options-collection collection="${actionBean.dokumenList}" label="tajuk" value="idDokumen"/>
                               </s:select>
                               <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                    onclick="doViewReportTimur();" height="30" width="30" alt="papar"
                                    onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                               <s:button class="btn" value="Add" name="addTimurImej" id="addTimurImej" onclick="addImageTimur();"/>
                            </td>
                        </tr><tr>
                            <th>
                                Barat
                            </th>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanBaratMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanBaratMilikKerajaan eq 'T'}">Milik</c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanBaratNoLot ne null}"> ${actionBean.laporanTanah.sempadanBaratNoLot}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanBaratNoLot eq null}"> Tiada Data </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanBaratKegunaan ne null}"> ${actionBean.laporanTanah.sempadanBaratKegunaan}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanBaratKegunaan eq null}"> Tiada Data </c:if>
                            </td>
                            <td>
                                <c:forEach items="${actionBean.baratImejLaporanList}" var="senarai">
                                   <c:out value="${senarai.dokumen.tajuk}" /><br/>
                               </c:forEach>
                               <s:select name="baratImej" id="baratImej">
                                   <s:option value="">Sila pilih imej</s:option>
                                   <s:options-collection collection="${actionBean.dokumenList}" label="tajuk" value="idDokumen"/>
                               </s:select>
                               <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                    onclick="doViewReportBarat();" height="30" width="30" alt="papar"
                                    onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                               <s:button class="btn" value="Add" name="addBaratImej" id="addBaratImej" onclick="addImageBarat();"/>
                            </td>
                        </tr>
                    </table>
                </div>
        </fieldset>
    </div>

            <p><label> Jika tanah dimiliki lebih daripada seorang, nyatakan pembahagian: </label></p>
            <p> <s:radio name="laporanTanah.pembahagian" value="y" />&nbsp;ya
                <s:radio name="laporanTanah.pembahagina" value="t" />&nbsp;tidak
            </p>

            <p>
                <label>Nama Tuan Tanah : </label>
                 <s:select name="laporanTanah.jenisBangunan">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:option>Lain-lain</s:option>
                    </s:select>
            </p>

            <p><label> takluk pada pecah sempadan? </label></p>
            <p> <s:radio name="a" value="y" />&nbsp;ya
                <s:radio name="b" value="t" />&nbsp;tidak
            </p>

            <p> <label>tanah empunya access : </label></p>
            <p> <s:radio name="a" value="y" />&nbsp;ya
                <s:radio name="b" value="t" />&nbsp;tidak
            </p>

            <p>
                <label>Jenis access : </label>
                 <s:select name="laporanTanah.jenisAccess">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:option>Lain-lain</s:option>
                    </s:select>
            </p>

            <p>
                <label>Ulasan pelapor dengan mereka berkepentingan:</label>
                <s:textarea name="ulasan" cols="30" rows="3" />
           </p>
        </fieldset>
    </div>
    </c:if>
    </div>
</s:form>




