<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    $(document).ready(function() {
        if($('#adaBangunan').val() == 'Y'){
            $('#bilanganBangunan').show();
        }
        if($('#namaSempadanLaut').val() == 'Y'){
            $('#bilanganTanaman').show();
        }
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
        var klasifikasi = '${actionBean.strukturTanahString}';        
        if(klasifikasi == 'LL'){
            $('#klasifikasiLain').show();
        }else{
            $('#klasifikasiLain').hide();
        }

        $('#jenisRizab').hide();
        $('#noWarta').hide();
        $('#nyataRancangan').hide();
        $('#tanahMilik').hide();
        $('#tujuan').hide();
        $('#catatanTanahMilik').hide();
        $('#catatanPBT').hide();
        $('#page_effect').fadeIn(2000);
        $('#page_effect2').fadeIn(4000);
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
    function removeNonNumeric( strString ){
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
        window.open("${pageContext.request.contextPath}/pembangunan/melaka/laporanTanah?showeditTanahRizab&idTanahRizabPermohonan="+d, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
    }
    function refreshRizab(){
        var url = '${pageContext.request.contextPath}/pembangunan/melaka/laporanTanah?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
    function removeSingle(idTanahRizabPermohonan){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pembangunan/melaka/laporanTanah?deleteSingle&idTanahRizabPermohonan='
                +idTanahRizabPermohonan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
    }
    function tambahBaru(){
        window.open("${pageContext.request.contextPath}/pembangunan/melaka/laporanTanah?hakMilikPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }

    function showKlasifikasiLain(value){
        if(value == "LL"){
            $('#klasifikasiLain').show();
        }else{
            $('#klasifikasiLain').hide();
        }
    }

    function showBilBangunan(value) {
        if(value == "Y")
        {
            $('#bilanganBangunan').show();
        }
        else if(value == "T")
        {
            $('#bilanganBangunan').hide();
        }
    }
    function showBilTanaman(value) {
        if(value == "Y")
        {
            $('#bilanganTanaman').show();
        }
        else if(value == "T")
        {
            $('#bilanganTanaman').hide();
        }
    }
    function showUlasan(value) {
        if(value == "1")
        {
            $('#perakuanSyor').show();
            $('#perakuanSyorUlasan').hide();
        }
        else if(value == "0")
        {
            $('#perakuanSyor').hide();
            $('#perakuanSyorUlasan').show();
        }
    }
    function hideBilBangunan() {
        $('#bilanganBangunan').hide();
        $('#bilanganBangunan2').hide();
    }
    function checkPelan(f){
        var noLot = f
        var kodDaerah = $('#kodDaerah').val();
        var kodBPM = $('#kodBPM').val();
        var kodNegeri = $('#kodNegeri').val();
        $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?checkPelan&noLot='+noLot+'&kodDaerah='+kodDaerah+'&kodNegeri='+kodNegeri+'&kodBPM='+kodBPM,
        function(data){
            if(data != '1'){
                alert('Pelan untuk no lot '+ noLot +' tiada');
                $("#noLot").val("");
                $("#noLot").focus();
            }
        }, 'html');
    }
    function addImage(idHakmilik,rowNo) {
        var idDokumen = document.getElementById("imej_"+rowNo).options[document.getElementById("imej_"+rowNo).selectedIndex].value;
        $("#imej"+rowNo).val(idDokumen);
        $.post('${pageContext.request.contextPath}/pembangunan/melaka/laporanTanah?addHakmilikImage&idDokumen='+idDokumen+'&idHakmilik='+idHakmilik,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    function addImageUtara(idHakmilik){
        var idDokumen = document.getElementById("utaraImej").options[document.getElementById("utaraImej").selectedIndex].value;
        $.post('${pageContext.request.contextPath}/pembangunan/melaka/laporanTanah?addLaporanImage&pandanganImej=U&idDokumen='+idDokumen+'&idHakmilik='+idHakmilik,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    function addImageSelatan(idHakmilik){
        var idDokumen = document.getElementById("selatanImej").options[document.getElementById("selatanImej").selectedIndex].value;
        $.post('${pageContext.request.contextPath}/pembangunan/melaka/laporanTanah?addLaporanImage&pandanganImej=S&idDokumen='+idDokumen+'&idHakmilik='+idHakmilik,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    function addImageTimur(idHakmilik){
        var idDokumen = document.getElementById("timurImej").options[document.getElementById("timurImej").selectedIndex].value;
        $.post('${pageContext.request.contextPath}/pembangunan/melaka/laporanTanah?addLaporanImage&pandanganImej=T&idDokumen='+idDokumen+'&idHakmilik='+idHakmilik,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    function addImageBarat(idHakmilik){
        var idDokumen = document.getElementById("baratImej").options[document.getElementById("baratImej").selectedIndex].value;
        $.post('${pageContext.request.contextPath}/pembangunan/melaka/laporanTanah?addLaporanImage&pandanganImej=B&idDokumen='+idDokumen+'&idHakmilik='+idHakmilik,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    function addImageTimurLaut(idHakmilik){
        var idDokumen = document.getElementById("timurLautImej").options[document.getElementById("timurLautImej").selectedIndex].value;
        $.post('${pageContext.request.contextPath}/pembangunan/melaka/laporanTanah?addLaporanImage&pandanganImej=1&idDokumen='+idDokumen+'&idHakmilik='+idHakmilik,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    function addImageTenggara(idHakmilik){
        var idDokumen = document.getElementById("tenggaraImej").options[document.getElementById("tenggaraImej").selectedIndex].value;
        $.post('${pageContext.request.contextPath}/pembangunan/melaka/laporanTanah?addLaporanImage&pandanganImej=2&idDokumen='+idDokumen+'&idHakmilik='+idHakmilik,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    function addImageBaratDaya(idHakmilik){
        var idDokumen = document.getElementById("baratDayaImej").options[document.getElementById("baratDayaImej").selectedIndex].value;
        $.post('${pageContext.request.contextPath}/pembangunan/melaka/laporanTanah?addLaporanImage&pandanganImej=3&idDokumen='+idDokumen+'&idHakmilik='+idHakmilik,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    function addImageBaratLaut(idHakmilik){
        var idDokumen = document.getElementById("baratLautImej").options[document.getElementById("baratLautImej").selectedIndex].value;
        $.post('${pageContext.request.contextPath}/pembangunan/melaka/laporanTanah?addLaporanImage&pandanganImej=4&idDokumen='+idDokumen+'&idHakmilik='+idHakmilik,
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
    function doViewReportTimurLaut() {
        var idDokumen = document.getElementById("timurLautImej").options[document.getElementById("timurLautImej").selectedIndex].value;
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function doViewReportTenggara() {
        var idDokumen = document.getElementById("tenggaraImej").options[document.getElementById("tenggaraImej").selectedIndex].value;
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function doViewReportBaratDaya() {
        var idDokumen = document.getElementById("baratDayaImej").options[document.getElementById("baratDayaImej").selectedIndex].value;
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function doViewReportBaratLaut() {
        var idDokumen = document.getElementById("baratLautImej").options[document.getElementById("baratLautImej").selectedIndex].value;
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function doView(idDokumen) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function restoreOld(sObj){
        alert("Boleh " + sObj);
        sObj.selectedIndex=document.getElementById("s1_old").value;
    }
    function tambahBangunan(TB,f){
        var id = '${actionBean.permohonan.idPermohonan}';
        var q = $(f).formSerialize();
        var url = f.action + '?simpanLaporanTanah';
        $.post(url,q,
        function(data){
            $('#page_div',document).html(data);
            // self.close();
        },'html');
        window.open("${pageContext.request.contextPath}/pembangunan/melaka/laporanTanah?tambahBangunanPopup&idPermohonan="+id+"&TB="+TB, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=350");
    }
    function tambahTanaman(){
        var id = '${actionBean.permohonan.idPermohonan}';
        window.open("${pageContext.request.contextPath}/pembangunan/melaka/laporanTanah?tambahBangunanPopup&idPermohonan="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=250");
    }
    function rekodUlasan(f){
        var id = '${actionBean.permohonan.idPermohonan}';
        var q = $(f).formSerialize();
        var url = f.action + '?simpanLaporanTanah';
        $.post(url,q,
        function(data){
            $('#page_div',document).html(data);
            //            self.close();
        },'html');
        window.open("${pageContext.request.contextPath}/pembangunan/melaka/laporanTanah?rekodUlasanPopup&idPermohonan="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=200");
    }
    
    function editPermohonanNota(idPermohonanNota){
        var url = "${pageContext.request.contextPath}/pembangunan/melaka/laporanTanah?editRekodUlasanPopup&idPermohonanNota="+idPermohonanNota;
        window.open(url, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=200");
    }
    
    function removePermohonanNota(idMohonNota){
        if(confirm('Adakah anda pasti?')) {   
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
             
            var url = '${pageContext.request.contextPath}/pembangunan/melaka/laporanTanah?deleteRekodUlasanPopup&idPermohonanNota='+idMohonNota;                                                                                   
            $.get(url,
            function(data){
                $('#page_div').html(data);
                $.unblockUI();
            },'html');
        }
    }
    
    function editLaporanBangunan(idLaporBangunan,TB){
        var idHakmilik = '${actionBean.hakmilik.idHakmilik}';
        window.open("${pageContext.request.contextPath}/pembangunan/melaka/laporanTanah?editBangunanPopup&idLaporBangunan="+idLaporBangunan+"&TB="+TB, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=300");
    }
        
    function deleteLaporanBangunan(idLaporBangunan){
        var url = '${pageContext.request.contextPath}/pembangunan/melaka/laporanTanah?deleteBangunanPopup&idLaporBangunan='+idLaporBangunan;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
        
    function removeCerunTanah(idLaporCerun)
    {
        var url = '${pageContext.request.contextPath}/pembangunan/melaka/laporanTanah?deleteLaporCerun&idLaporCerun='
            +idLaporCerun;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
    function addImageBngn(idHakmilik,idLapor){
        var idDokumen = document.getElementById("ImageBngn").options[document.getElementById("ImageBngn").selectedIndex].value;
        $.post('${pageContext.request.contextPath}/pembangunan/melaka/laporanTanah?addLaporanImage&idDokumen='+idDokumen+'&idHakmilik='+idHakmilik,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    function showSekHakmilik() {
        $('#sekatanHakmilik').show();
    }
    function hideSekHakmilik() {
        $('#sekatanHakmilik').hide();
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
    function simpanCeruntanah(f){
        var q = $(f).formSerialize();
        var url = f.action + '?simpanLaporanTanah';
        $.post(url,q,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function searchKodSyaratNyata(index){        
        var url = '${pageContext.request.contextPath}/pembangunan/carianSyaratSekatan?showFormCariKodSyaratNyata2&index='+index;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900px,height=700px,scrollbars=yes");
    }

    function searchKodSekatan(index){        
        var url = '${pageContext.request.contextPath}/pembangunan/carianSyaratSekatan?showFormCariKodSekatan&index='+index;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900px,height=700px,scrollbars=yes");
    }

    function simpanLT(f){         
        if($("#tanaman").val() == ""){
            alert('Sila Pilih Status Tanaman terlebih dahulu.');
            $("#tanaman").focus();
            return true;
        }
        if($("#bangunan").val() == ""){
            alert('Sila Pilih Status Bangunan terlebih dahulu.');
            $("#bangunan").focus();
            return true;
        }
            
        if($("#bangunan").val() != "" && $("#tanaman").val() != ""){
            var q = $(f).formSerialize();
            var url = f.action + '?simpanLaporanTanah';
            $.post(url,q,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

    }
    
    function ReplaceAll(Source,stringToFind,stringToReplace){
        var temp = Source;
        var index = temp.indexOf(stringToFind);
        while(index != -1){
            temp = temp.replace(stringToFind,stringToReplace);
            index = temp.indexOf(stringToFind);

        }
        return temp;
    }

    function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {
        var strIDStage = "g_laporan_tanah";
        alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);      
        strNama = ReplaceAll(strNama," ","_");
        strJawatan = ReplaceAll(strJawatan," ","_");
        strStageID = ReplaceAll(strStageID," ","_");
                     
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + strIDStage);
    }
    
    //SBMS & TSPSS
    function validate() {

        var syorYa = document.getElementById("syorYa").checked;
        var syorTidak = document.getElementById("syorTidak").checked;

        if (!syorYa && !syorTidak) {
            alert("Sila masukkan maklumat Jika diperakukan.");
            return false;
        }
        return true;
    }
                                 
    function showUlasan1(value) {
        if(value == "Y")
        {
            $('#perakuanSyor1').show();
            $('#perakuanSyorUlasan1').hide();
        }
        else if(value == "T")
        {
            $('#perakuanSyor1').hide();
            $('#perakuanSyorUlasan1').show();
        }
    }
            
    function dopopup(i){  
        var d=i;
        window.open("${pageContext.request.contextPath}/pembangunan/melaka/laporanTanah?editPopup&idPlot="+d, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=800,height=500");
    }
    
    function doNyata(i,k){  
        var d=i;
        window.open("${pageContext.request.contextPath}/pembangunan/melaka/laporanTanah?editNyata&idPlot="+d+"&forEdit="+k, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1200,height=500");
    }
    
    function doSekatan(i,k){  
        var d=i;
        window.open("${pageContext.request.contextPath}/pembangunan/melaka/laporanTanah?editSekatan&idPlot="+d+"&forEdit="+k, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1200,height=900");
    }
            
    function refreshPopup(){
        var id = '${actionBean.idPermohonan}';
        var url = '${pageContext.request.contextPath}/pembangunan/melaka/laporanTanah?rehydrate&idPermohonan='+id ;
        $.get(url,
        function(data){
            $("#popupDiv").replaceWith($('#popupDiv', $(data)));
        }
        ,'html');
    }
</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>                     
<s:form beanclass="etanah.view.stripes.pembangunan.LaporanTanahMelakaActionBean">
    <s:messages/>
    <s:hidden name="permohonan.idPermohonan" />
    <div class="subtitle" id="page_effect">
        <fieldset class="aras1">

            <legend>Laporan Tanah</legend>
            <c:set scope="request" var="senaraiPBT"  value="${actionBean.pihakKepentinganList}"/>

            <p>
                <label>Tarikh Permohonan Diterima :</label>
                <c:if test="${actionBean.permohonan.infoAudit.tarikhMasuk ne null}"><s:format formatPattern="dd/MM/yyyy" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>&nbsp;</c:if>
                <c:if test="${actionBean.permohonan.infoAudit.tarikhMasuk eq null}"> Tiada Data </c:if>
            </p>
            <p>
                <label>Keluasan :</label>
                <fmt:formatNumber  pattern="#,##0.0000" value="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.luas}"/>
                ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodUnitLuas.nama}&nbsp;
            </p>
            <br>
        </fieldset>
    </div>
    <br>
    <div class="subtitle" id="page_effect2">
        <fieldset class="aras1">
            <legend>Perihal Tanah</legend>
                <c:if test="${actionBean.stageId eq 'laporantanah' || actionBean.stageId eq 'laporan_tanah'}">
                <p>
                    <label>Senarai Pihak Berkepentingan :&nbsp;&nbsp </label>
                    &nbsp;&nbsp;
                </p>
                <div>
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" id="line" >
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="No. Hakmilik" >${line.hakmilik.kodHakmilik.kod} <fmt:formatNumber  pattern="00" value="${line.hakmilik.noHakmilik}"/></display:column>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                        <display:column title="Tuan Tanah">
                            <table>
                                <tr>
                                    <th>Nama&nbsp;</th><th>Syer</th>
                                </tr>
                                <c:set value="1" var="count"/>
                                <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                    <c:if test="${senarai.jenis.kod eq 'PM' && senarai.aktif eq 'Y'}">
                                        <tr>
                                            <td>
                                                <c:out value="${count}"/>)&nbsp;
                                                <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;<br>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.KP/Co :&nbsp;<c:out value="${senarai.pihak.noPengenalan}"/>

                                            </td>
                                            <td><c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/>
                                            </td>
                                        </tr>
                                        <c:set value="${count + 1}" var="count"/>
                                    </c:if>
                                </c:forEach>
                            </table>                          
                        </display:column>                      
                        <display:column title="Pihak Berkepentingan">
                            <c:set value="1" var="count"/>
                            <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                <c:if test="${senarai.jenis.kod ne 'PM'}">
                                    <c:if test="${senarai.aktif eq 'Y'}">
                                        <br>
                                        <c:out value="${count}"/>)&nbsp;
                                        <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;
                                        <c:set value="${count + 1}" var="count"/><br>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.KP/Co:<c:out value="${senarai.pihak.noPengenalan}"/>
                                    </c:if>
                                </c:if>
                            </c:forEach>
                        </display:column>
                        <display:column title="Jenis Pihak Berkepentingan">
                            <c:set value="1" var="count"/>
                            <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                <c:if test="${senarai.jenis.kod ne 'PM'}">
                                    <c:if test="${senarai.aktif eq 'Y'}">
                                        <c:out value="${count}"/>)&nbsp;
                                        <c:out value="${senarai.jenis.nama}"/><br>
                                        <c:set value="${count + 1}" var="count"/><br>
                                    </c:if>
                                </c:if>
                            </c:forEach>
                        </display:column>
                        <display:column title="Tarikh Pemilikan Didaftar">
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/>
                        </display:column>
                        <display:column title="Hasil (RM)">RM <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukaiSebenar}"/></display:column>                        
                    </display:table>
                </div>
                <br>
                <p>
                <div class="content" align="center">                                      
                    <table border="0" width="100%">
                        <tr>
                            <td><label>Nombor Warta Kerajaan</label></td>
                            <td>:</td>
                            <td><s:text name="permohonanRujukanLuar.noRujukan" disabled="${actionBean.disabled}"/><br></td>
                        </tr>
                        <tr>
                            <td colspan="3"></td>
                        </tr>
                        <tr>
                            <td><label>Jenis Tanah</label></td>
                            <td>:</td>
                            <td> <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama}&nbsp; </c:if>
                                <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama eq null}"> Tiada Data </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3"></td>
                        </tr>
                        <tr>
                            <td><label>Lokaliti</label></td>
                            <td>:</td>
                            <td class="normal_text"><c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi}&nbsp; </c:if>
                                <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi eq null}"> Tiada Data </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3"></td>
                        </tr>
                        <tr>
                            <td><label>Kawasan PBT</label></td>
                            <td>:</td>
                            <td class="normal_text"><c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama}&nbsp; </c:if>
                                <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama eq null}"> Tiada Data </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3"></td>
                        </tr>
                        <tr>
                            <td valign="top"><label>Tandatangan</label></td>
                            <td valign="top">:</td>
                            <td>Semua pemilik <s:radio name="laporanTanah.dokDitandatangan" value="Y"/>&nbsp;<b>Telah</b><s:radio name="laporanTanah.dokDitandatangan" value="T"/>&nbsp;<b>Belum</b> menandatangani <s:radio name="laporanTanah.jenisDokDitandatangan" value="S"/>&nbsp;<b>Surat</b><s:radio name="laporanTanah.jenisDokDitandatangan" value="B"/>&nbsp;<b>Borang</b>  <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PYTN'}"> penyatuan tanah </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK'}"> menukar syarat </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPCB' || actionBean.permohonan.kodUrusan.kod eq 'PPCS'}"> pecah bahagian/sempadan </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SBMS'}"> penyerahan dan pemberimilikan semula</c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSBSN'}"> tukar syarat kategori pembatalan syarat nyata </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PSMT' || actionBean.permohonan.kodUrusan.kod eq 'PSBT' || actionBean.permohonan.kodUrusan.kod eq 'PBSK'}"> ${actionBean.permohonan.kodUrusan.nama}</c:if> tersebut.
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3"></td>
                        </tr>
                        <tr>
                            <td valign="top"><label>Pengesahan Kepentingan</label></td>
                            <td valign="top">:</td>
                            <td>Semua mereka yang ada kepentingan berdaftar <s:radio name="laporanTanah.pengesahanKepentingan" value="Y"/>&nbsp;<b>Telah</b><s:radio name="laporanTanah.pengesahanKepentingan" value="T"/>&nbsp;<b>Belum</b> memberi kebenaran bertulis untuk <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PYTN'}"> penyatuan tanah </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK'}"> menukar syarat </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPCB' || actionBean.permohonan.kodUrusan.kod eq 'PPCS'}"> pecah bahagian/sempadan </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SBMS'}"> penyerahan dan pemberimilikan semula</c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSBSN'}"> tukar syarat kategori pembatalan syarat nyata </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PSMT' || actionBean.permohonan.kodUrusan.kod eq 'PSBT' || actionBean.permohonan.kodUrusan.kod eq 'PBSK'}"> ${actionBean.permohonan.kodUrusan.nama}</c:if> tersebut.
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3"></td>
                        </tr>
                        <tr>
                            <td><label>Sekatan Hakmilik (Jika Ada)</label></td>
                            <td>:</td>
                            <td><c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.sekatanKepentingan ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.sekatanKepentingan.sekatan}&nbsp; </c:if>
                                <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.sekatanKepentingan eq null}"> Tiada Data </c:if></td>
                        </tr>
                        <tr>
                            <td colspan="3"></td>
                        </tr>
                        <tr>
                            <td><label>Syarat Nyata (Jika Ada)</label></td>
                            <td>:</td>
                            <td><c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.syaratNyata ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.syaratNyata.syarat}.&nbsp; </c:if>
                                <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.syaratNyata eq null}"> Tiada Data </c:if></td>
                        </tr>
                        <tr>
                            <td colspan="3"></td>
                        </tr>
                        <tr>
                            <td><label>Projek Kerajaan(Pengambilan)</label></td>
                            <td>:</td>
                            <td><s:radio name="laporanTanah.nilaiBangunan" value="0"/>&nbsp;<b>Ada</b><s:radio name="laporanTanah.nilaiBangunan" value="1"/>&nbsp;<b>Tidak</b> terlibat dengan projek Kerajaan (Pengambilan).Jika ada rujukan fail : <s:text name="rujukanFail" disabled="${actionBean.disabled}"/>
                            </td>
                        </tr>
                    </table></div>
                </p>                
                <br>
            </c:if>
            <c:if test="${actionBean.stageId eq 'semaklaporantanah' || actionBean.stageId eq 'semakderafperakuan' || actionBean.stageId eq 'perakuanmmknptd'
                          || actionBean.stageId eq 'ulasanadunteksediajkbb' || actionBean.stageId eq 'derafperakuanjkbbptd' || actionBean.stageId eq 'perakuanjkbbptd'}">
                  <div>
                      <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" id="line">
                          <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                          <display:column title="No. Hakmilik">${line.hakmilik.kodHakmilik.kod} <fmt:formatNumber  pattern="00" value="${line.hakmilik.noHakmilik}"/></display:column>
                          <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                          <display:column title="Tuan Tanah">
                              <table>
                                  <tr>
                                      <th>Nama&nbsp;</th><th>Syer</th>
                                  </tr>
                                  <c:set value="1" var="count"/>
                                  <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                      <c:if test="${senarai.jenis.kod eq 'PM' && senarai.aktif eq 'Y'}">
                                          <tr>
                                              <td>
                                                  <c:out value="${count}"/>)&nbsp;
                                                  <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;<br>
                                                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.KP/Co :&nbsp;<c:out value="${senarai.pihak.noPengenalan}"/>

                                              </td>
                                              <td><c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/>
                                              </td>
                                          </tr>
                                          <c:set value="${count + 1}" var="count"/>
                                      </c:if>
                                  </c:forEach>
                              </table>                             
                          </display:column>

                          <display:column title="Pihak Berkepentingan">
                              <c:set value="1" var="count"/>
                              <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                  <c:if test="${senarai.jenis.kod ne 'PM'}">
                                      <c:if test="${senarai.aktif eq 'Y'}">
                                          <br>
                                          <c:out value="${count}"/>)&nbsp;
                                          <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;
                                          <c:set value="${count + 1}" var="count"/><br>
                                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.KP/Co:<c:out value="${senarai.pihak.noPengenalan}"/>
                                      </c:if>
                                  </c:if>
                              </c:forEach>
                          </display:column>
                          <display:column title="Jenis Pihak Berkepentingan">
                              <c:set value="1" var="count"/>
                              <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                  <c:if test="${senarai.jenis.kod ne 'PM'}">
                                      <c:if test="${senarai.aktif eq 'Y'}">
                                          <c:out value="${count}"/>)&nbsp;
                                          <c:out value="${senarai.jenis.nama}"/><br>
                                          <c:set value="${count + 1}" var="count"/><br>
                                      </c:if>
                                  </c:if>
                              </c:forEach>
                          </display:column>
                          <display:column title="Tarikh Pemilikan Didaftar">
                              <fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/>
                          </display:column>
                          <display:column title="Hasil (RM)">RM <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukaiSebenar}"/></display:column>

                      </display:table>
                  </div>
                  <br>
                  <div class="content">
                      <p>
                          <label>Nombor Warta Kerajaan :</label>
                          <c:if test="${actionBean.noWartaKonon ne null}"> ${actionBean.noWartaKonon}&nbsp; </c:if>
                          <c:if test="${actionBean.noWartaKonon eq null}"> Tiada Data </c:if>
                      </p>
                      <p>
                          <label>Jenis Tanah :</label>
                          <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama}&nbsp; </c:if>
                          <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama eq null}"> Tiada Data </c:if>
                      </p>
                      <p>
                          <label>Lokaliti :</label>
                          <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi}&nbsp; </c:if>
                          <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi eq null}"> Tiada Data </c:if>
                      </p>
                      <p>
                          <label>Kawasan PBT :</label>
                          <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama}&nbsp; </c:if>
                          <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama eq null}"> Tiada Data </c:if>
                      </p>
                      <p>
                          <label>Tandatangan :</label>
                          Semua pemilik <c:if test="${actionBean.laporanTanah.dokDitandatangan eq 'Y'}"><b>Telah</b></c:if><c:if test="${actionBean.laporanTanah.dokDitandatangan eq 'T'}"><b>Belum</b></c:if> menandatangani <c:if test="${actionBean.laporanTanah.jenisDokDitandatangan eq 'S'}"><b>Surat</b></c:if><c:if test="${actionBean.laporanTanah.jenisDokDitandatangan eq 'B'}"><b>Borang</b></c:if>  <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PYTN'}"> penyatuan tanah </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK'}"> menukar syarat </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPCB' || actionBean.permohonan.kodUrusan.kod eq 'PPCS'}"> pecah bahagian/sempadan </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SBMS'}"> penyerahan dan pemberimilikan semula</c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSBSN'}"> tukar syarat kategori pembatalan syarat nyata </c:if><c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PSMT' || actionBean.permohonan.kodUrusan.kod eq 'PSBT' || actionBean.permohonan.kodUrusan.kod eq 'PBSK'}"> ${actionBean.permohonan.kodUrusan.nama}</c:if> tersebut.
                      </p>
                      <p>
                          <label>Pengesahan Kepentingan :</label>
                          Semua mereka yang ada kepentingan berdaftar <c:if test="${actionBean.laporanTanah.pengesahanKepentingan eq 'Y'}"><b>Telah</b></c:if><c:if test="${actionBean.laporanTanah.pengesahanKepentingan eq 'T'}"><b>Belum</b></c:if> memberi kebenaran bertulis untuk <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PYTN'}"> penyatuan tanah </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK'}"> menukar syarat </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPCB' || actionBean.permohonan.kodUrusan.kod eq 'PPCS'}"> pecah bahagian/sempadan </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SBMS'}"> penyerahan dan pemberimilikan semula</c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSBSN'}"> tukar syarat kategori pembatalan syarat nyata </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PSMT' || actionBean.permohonan.kodUrusan.kod eq 'PSBT' || actionBean.permohonan.kodUrusan.kod eq 'PBSK'}"> ${actionBean.permohonan.kodUrusan.nama}</c:if> tersebut.
                      </p>
                      <p>
                          <label>Sekatan Hakmilik (Jika Ada) :</label>
                          <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.sekatanKepentingan ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.sekatanKepentingan.sekatan}&nbsp; </c:if>
                          <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.sekatanKepentingan eq null}"> Tiada Data </c:if>
                      </p>
                      <p>
                          <label>Syarat Nyata (Jika Ada) :</label>
                          <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.syaratNyata ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.syaratNyata.syarat}.&nbsp; </c:if>
                          <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.syaratNyata eq null}"> Tiada Data </c:if>
                      </p>
                      <p>
                          <label>Catatan :</label>
                          <c:out value="${actionBean.laporanTanah.strukturTambahanJenis}"></c:out> &nbsp;
                      </p>
                      <p>
                          <label>Projek Kerajaan(Pengambilan) :</label>
                          <c:if test="${actionBean.laporanTanah.nilaiBangunan eq '0'}"><b>Ada</b></c:if><c:if test="${actionBean.laporanTanah.nilaiBangunan eq '1'}"><b>Tiada</b></c:if> terlibat dengan projek Kerajaan (Pengambilan).Jika ada rujukan fail : ${actionBean.laporanTanah.rancanganKerajaan}
                      </p>

                  </div>
                  <div  align="center" style="display: none" >
                      Bersempadan
                      <table  style="display: none">
                          <tr>
                              <th>Bersempadan</th><th>Nama</th><th width="120">Jarak (KM)</th>
                          </tr>
                          <tr>
                              <td>
                                  Jalan Raya
                              </td>
                              <td>
                                  <c:if test="${actionBean.laporanTanah.namaSempadanJalanraya ne null}"> ${actionBean.laporanTanah.namaSempadanJalanraya}&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.namaSempadanJalanraya eq null}"> Tiada Data </c:if>
                              </td>
                              <td>
                                  <c:if test="${actionBean.laporanTanah.jarakSempadanJalanraya ne null}"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.laporanTanah.jarakSempadanJalanraya}"/></c:if>
                                  <c:if test="${actionBean.laporanTanah.jarakSempadanJalanraya eq null}"> Tiada Data </c:if>
                              </td>
                          </tr>
                          <tr>
                              <td>
                                  Landasan Keretapi
                              </td>
                              <td>
                                  <c:if test="${actionBean.laporanTanah.namaSempadanKeretapi ne null}"> ${actionBean.laporanTanah.namaSempadanKeretapi}&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.namaSempadanKeretapi eq null}"> Tiada Data </c:if>
                              </td>
                              <td>
                                  <c:if test="${actionBean.laporanTanah.jarakSempadanKeretapi ne null}">  <fmt:formatNumber pattern="#,##0.00" value="${actionBean.laporanTanah.jarakSempadanKeretapi}"/>&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.jarakSempadanKeretapi eq null}"> Tiada Data </c:if>
                              </td>
                          </tr>
                          <tr>
                              <td>
                                  Laut
                              </td>
                              <td>
                                  <c:if test="${actionBean.laporanTanah.namaSempadanLaut ne null}"> ${actionBean.laporanTanah.namaSempadanLaut}&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.namaSempadanLaut eq null}"> Tiada Data </c:if>
                              </td>
                              <td>
                                  <c:if test="${actionBean.laporanTanah.jarakSempadanLaut ne null}"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.laporanTanah.jarakSempadanLaut}"/>&nbsp;</c:if>
                                  <c:if test="${actionBean.laporanTanah.jarakSempadanLaut eq null}"> Tiada Data </c:if>
                              </td>
                          </tr>
                          <tr>
                              <td>
                                  Sungai
                              </td>
                              <td>
                                  <c:if test="${actionBean.laporanTanah.namaSempadanSungai ne null}"> ${actionBean.laporanTanah.namaSempadanSungai}&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.namaSempadanSungai eq null}"> Tiada Data </c:if>
                              </td>
                              <td>
                                  <c:if test="${actionBean.laporanTanah.jarakSempadanSungai ne null}">   <fmt:formatNumber pattern="#,##0.00" value="${actionBean.laporanTanah.jarakSempadanSungai}"/>&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.jarakSempadanSungai eq null}"> Tiada Data </c:if>
                              </td>
                          </tr>
                      </table>
                  </div>
            </c:if>
            <br>
        </fieldset>
    </div>
    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <s:hidden name="laporanTanah.idLaporan"/>
            <s:hidden name="laporanTanah.permohonan.idPermohonan"/>
            <legend>Keadaan Tanah</legend><br>
            <c:if test="${actionBean.stageId eq 'laporantanah'}">
                <p>
                    <label>Keadaan Tanah :</label>
                <table border="0" class="tablecloth">
                    <tr style="display : none">
                        <th colspan="2">Keadaan Tanah</th>
                    </tr>
                    <c:forEach items="${actionBean.senaraiKodKecerunanTanah}" var="line">                     
                        <c:if test="${line.kod ne 'LL'}">
                            <tr>
                                <td><s:checkbox name="${line.nama}" value="${line.kod}" id="cerun${line.kod}"/> </td>
                                <td>${line.nama}</td>
                            </tr>
                        </c:if>
                    </c:forEach>                     
                    <c:forEach items="${actionBean.findListlaporCerun}" var="line3">
                        <tr>
                            <td>
                                <div align="center">
                                    <input type="checkbox" name="${line3_rowNum}" value="${line3_rowNum}" checked="checked" onclick="removeCerunTanah('${line3.idLaporCerun}');"/>
                                    <img style="display: none" alt='Klik Untuk Hapus' border='0'
                                         src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"
                                         onclick="removeCerunTanah('${line3.idLaporCerun}');"/>
                                </div>
                            </td>
                            <td>
                                ${line3.kodCerunanTanah.nama}
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td></td>
                        <td>Lain-lain <br/><s:textarea name="laporanTanah.catatanSempadanUtara" class="normal_text" cols="50"/></td>
                    </tr>
                </table>
                </p>
                <br><br>
                <p>
                    <label>Klasifikasi Tanah :</label>
                    <s:select name="strukturTanahString" disabled="${actionBean.disabled}" onchange="javaScript:showKlasifikasiLain(this.value);">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodStrukturTanah}" label="nama" value="kod" />
                    </s:select>
                </p>

                <div id="klasifikasiLain">
                    <p>
                        <label>Lain-lain : </label><s:textarea name="laporanTanah.catatanSempadanSelatan" class="normal_text" cols="50"/>
                    </p>
                </div>
                <br><br>
                <p>
                    <label>Dilintasi Oleh :</label>
                <table class="tablecloth">
                    <tr>
                        <td><s:checkbox name="laporanTanah.dilintasTiangElektrik" value="Y" /></td>
                        <td>Talian Elektrik</td>
                    </tr>
                    <tr>
                        <td><s:checkbox name="laporanTanah.dilintasTiangTelefon" value="Y" /></td>
                        <td>Talian Telefon</td>
                    </tr>
                    <tr>
                        <td><s:checkbox name="laporanTanah.dilintasLaluanGas" value="Y" /></td>
                        <td>Laluan Gas</td>
                    </tr>
                    <tr>
                        <td><s:checkbox name="laporanTanah.dilintasPaip" value="Y" /></td>
                        <td>Paip Air</td>
                    </tr>
                    <tr>
                        <td><s:checkbox name="laporanTanah.dilintasTaliar" value="Y" /></td>
                        <td>Tali Air</td>
                    </tr>
                    <tr>
                        <td><s:checkbox name="laporanTanah.dilintasSungai" value="Y" /></td>
                        <td>Sungai</td>
                    </tr>
                    <tr>
                        <td><s:checkbox name="laporanTanah.dilintasParit" value="Y" /></td>
                        <td>Parit</td>
                    </tr>
                    <tr>
                        <td>Lain-lain</td>
                        <td><s:textarea name="laporanTanah.dilintasiLain" class="normal_text" cols="50"/></td>
                    </tr>
                </table>

                <p>
                    <label>Kedudukan Tanah :</label>
                    <s:textarea name="laporanTanah.kedudukanTanah" cols="50" class="normal_text"/>
                </p>
                <br>
                <p>
                    <label>Jauh Dari Pekan/Bandar :</label>
                    <s:textarea name="laporanTanah.namaSempadanJalanraya" cols="50" class="normal_text" disabled="${actionBean.disabled}"/>
                </p>
                <br>
                <p>
                    <label>Mercu Tanda(LandMark) :</label>
                    <s:textarea name="laporanTanah.mercuTanda" cols="50" class="normal_text" disabled="${actionBean.disabled}"/>
                </p>
                <br>
                <p>
                    <label>Lot Tanah Ini Mempunyai Laluan Sah(Access Reserve) :</label>
                    <s:radio name="laporanTanah.adaJalanMasuk" value="Y" disabled="${actionBean.disabled}"/>&nbsp;Ada
                    <s:radio name="laporanTanah.adaJalanMasuk" value="T" disabled="${actionBean.disabled}"/>&nbsp;Tiada
                    <br></p><p>
                    <label>Catatan :</label>
                    <s:textarea name="laporanTanah.catatanJalanMasuk" class="normal_text" cols="50" disabled="${actionBean.disabled}"/>
                </p>
                <br>
            </c:if>            
            <c:if test="${actionBean.stageId eq 'semaklaporantanah' || actionBean.stageId eq 'semakderafperakuan' || actionBean.stageId eq 'perakuanmmknptd'
                          || actionBean.stageId eq 'ulasanadunteksediajkbb' || actionBean.stageId eq 'derafperakuanjkbbptd' || actionBean.stageId eq 'perakuanjkbbptd'}">
                  <p>
                      <label>Keadaan Tanah :</label>
                      <c:forEach items="${actionBean.findListlaporCerun}" var="line">
                          ${line.kodCerunanTanah.nama}, 
                      </c:forEach>
                  </p>
                  <br>
                  <p>
                      <label>Lain-lain :</label>                    
                      ${actionBean.laporanTanah.catatanSempadanUtara}
                  </p>

                  <p>
                      <label>Klasifikasi Tanah :</label>
                      <c:if test="${actionBean.laporanTanah.strukturTanah.nama ne null}"> ${actionBean.laporanTanah.strukturTanah.nama}&nbsp; </c:if>
                      <c:if test="${actionBean.laporanTanah.strukturTanah.nama eq null}"> Tiada Data </c:if>
                  </p>
                  <c:if test="${actionBean.laporanTanah.strukturTanah.kod eq 'LL'}">
                      <p>
                          <label>Lain-lain :</label>
                          ${actionBean.laporanTanah.catatanSempadanSelatan}
                      </p>
                  </c:if>
                  <p>
                      <label>Dilintasi Oleh :</label>
                      <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik ne null or
                                    actionBean.laporanTanah.dilintasTiangTelefon ne null or
                                    actionBean.laporanTanah.dilintasLaluanGas ne null or
                                    actionBean.laporanTanah.dilintasPaip ne null or
                                    actionBean.laporanTanah.dilintasTaliar ne null or
                                    actionBean.laporanTanah.dilintasSungai ne null or
                                    actionBean.laporanTanah.dilintasParit ne null or
                                    actionBean.laporanTanah.dilintasiLain ne null}">
                          <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik eq 'Y'}"> Talian Elektrik ,</c:if>
                          <c:if test="${actionBean.laporanTanah.dilintasTiangTelefon eq 'Y'}">Talian Telefon ,</c:if>
                          <c:if test="${actionBean.laporanTanah.dilintasLaluanGas eq 'Y'}">Laluan Gas ,</c:if>
                          <c:if test="${actionBean.laporanTanah.dilintasPaip eq 'Y'}">Paip Air ,</c:if>
                          <c:if test="${actionBean.laporanTanah.dilintasTaliar eq 'Y'}">Tali Air ,</c:if>
                          <c:if test="${actionBean.laporanTanah.dilintasSungai eq 'Y'}">Sungai ,</c:if>
                          <c:if test="${actionBean.laporanTanah.dilintasParit eq 'Y'}">Parit ,</c:if>
                          <c:if test="${actionBean.laporanTanah.dilintasiLain ne null}">${actionBean.laporanTanah.dilintasiLain}.</c:if>
                      </c:if>

                      <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik eq null and
                                    actionBean.laporanTanah.dilintasTiangTelefon eq null and
                                    actionBean.laporanTanah.dilintasLaluanGas eq null and
                                    actionBean.laporanTanah.dilintasPaip eq null and
                                    actionBean.laporanTanah.dilintasTaliar eq null and
                                    actionBean.laporanTanah.dilintasSungai eq null and
                                    actionBean.laporanTanah.dilintasParit eq null and
                                    actionBean.laporanTanah.dilintasiLain eq null}"> Tiada Data </c:if>
                      <br>
                  <p>
                      <label>Kedudukan Tanah :</label>
                      <c:if test="${actionBean.laporanTanah.kedudukanTanah ne null}"> ${actionBean.laporanTanah.kedudukanTanah}&nbsp; </c:if>
                      <c:if test="${actionBean.laporanTanah.kedudukanTanah eq null}"> Tiada Data </c:if>
                  </p>
                  <p>
                      <label>Jauh Dari Pekan/Bandar :</label>
                      <c:if test="${actionBean.laporanTanah.namaSempadanJalanraya ne null}"> ${actionBean.laporanTanah.namaSempadanJalanraya}&nbsp; </c:if>
                      <c:if test="${actionBean.laporanTanah.namaSempadanJalanraya eq null}"> Tiada Data </c:if>
                  </p>
                  <p>
                      <label>Mercu Tanda(LandMark) :</label>
                      <c:if test="${actionBean.laporanTanah.mercuTanda ne null}"> ${actionBean.laporanTanah.mercuTanda}&nbsp; </c:if>
                      <c:if test="${actionBean.laporanTanah.mercuTanda eq null}"> Tiada Data </c:if>
                  </p>
                  <p>
                      <label>Lot Tanah Ini Mempunyai Laluan Sah(Access Reserve) :</label>
                      <c:if test="${actionBean.laporanTanah.adaJalanMasuk eq 'Y'}">Ada </c:if>
                      <c:if test="${actionBean.laporanTanah.adaJalanMasuk ne 'Y'}">Tiada </c:if>
                  </p>
                  <p><label>Catatan :</label>
                      <c:if test="${actionBean.laporanTanah.catatanJalanMasuk ne null}"> ${actionBean.laporanTanah.catatanJalanMasuk}&nbsp; </c:if>
                      <c:if test="${actionBean.laporanTanah.catatanJalanMasuk eq null}"> Tiada Data </c:if>
                  </p>
            </c:if>
            <br>
        </fieldset>
    </div>
    <br>
    <div class="subtitle displaytag">
        <fieldset class="aras1">
            <legend>Latar belakang Tanah</legend>
            <br>
            <c:if test="${actionBean.stageId eq 'laporantanah'}">
                <p>
                    <label>Diusahakan :</label>
                    <s:radio name="laporanTanah.usaha" value="Y" disabled="${actionBean.disabled}"/>&nbsp;Tuan Tanah
                    <s:radio name="laporanTanah.usaha" value="T" disabled="${actionBean.disabled}"/>&nbsp;Penyewa
                </p>
                <br>
                <p>
                    <label>(Jika Ada)Tanaman :</label>
                    <s:radio name="laporanTanah.namaSempadanLaut" id="tanaman" value="Y" onclick="javaScript:showBilTanaman(this.value);doSubmit(this.form, simpanLaporanTanah, 'page_div')" />&nbsp;Ada
                    <s:radio name="laporanTanah.namaSempadanLaut" id="tanaman" value="T" onclick="javaScript:showBilTanaman(this.value);doSubmit(this.form, simpanLaporanTanah, 'page_div')" />&nbsp;Tiada
                    <c:if test="${actionBean.laporanTanah.namaSempadanLaut eq 'Y'}">
                    <div class="content" align="center" id="bilanganTanaman">
                        <display:table class="tablecloth" name="${actionBean.permohonanLaporanTanamanList}" pagesize="10" cellpadding="0" cellspacing="0"
                                       requestURI="/pembangunan/melaka/laporanTanah" id="line">
                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                            <display:column property="namaPemunya" title="Pemilik" />
                            <display:column property="namaPenyewa" title="Penyewa" />
                            <display:column property="namaKetua" title="Jenis Tanaman" />
                            <display:column title="Keadaan Tanaman">
                                <c:if test="${line.jenisBangunan eq 'KK'}">Kekal</c:if>
                                <c:if test="${line.jenisBangunan eq 'SM'}">Sementara</c:if>
                            </display:column>
                            <display:column property="keteranganTahunBinaan" title="Tahun Dibina" />
                            <display:column  title="Kadar Sewa" >RM <fmt:formatNumber pattern="#,##0.00" value="${line.nilai}"/></display:column>
                            <display:column title="Kemaskini">
                                <div align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editLaporanBangunan('${line.idLaporBangunan}','T');"/>
                                </div>
                            </display:column>
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' src='${pageContext.request.contextPath}/images/not_ok.gif'
                                         onclick="deleteLaporanBangunan('${line.idLaporBangunan}');"/>
                                </div>
                            </display:column>
                        </display:table>
                        <s:button class="btn" value="Tambah" name="new" id="bilanganTanaman2" onclick="tambahBangunan('T',this.form);" />
                    </div></c:if>
                    <p>
                        <label>(Jika Ada)Bangunan :</label>
                    <s:radio name="laporanTanah.adaBangunan" id="bangunan" value="Y" onclick="javaScript:showBilBangunan(this.value);doSubmit(this.form, simpanLaporanTanah, 'page_div')" />&nbsp;Ada
                    <s:radio name="laporanTanah.adaBangunan" id="bangunan" value="T" onclick="javaScript:showBilBangunan(this.value);doSubmit(this.form, simpanLaporanTanah, 'page_div')" />&nbsp;Tiada
                    <c:if test="${actionBean.stageId eq 'semaklaporantanah' || actionBean.stageId eq 'semakderafperakuan' || actionBean.stageId eq 'perakuanmmknptd'
                                  || actionBean.stageId eq 'ulasanadunteksediajkbb' || actionBean.stageId eq 'derafperakuanjkbbptd' || actionBean.stageId eq 'perakuanjkbbptd'}">
                        <s:hidden name="laporanTanah.bilanganBangunan"></s:hidden>
                    </c:if>
                    <c:if test="${actionBean.laporanTanah.adaBangunan eq 'Y'}">
                    <div class="content" align="center" id="bilanganBangunan">
                        <p>
                            <s:select name="bngn_idHakmilik" id="bngn_idHakmilik" disabled="disabled" style="display : none">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${actionBean.hakmilikPermohonanList}" label="hakmilik.idHakmilik" value="hakmilik.idHakmilik" />
                            </s:select>
                            <s:hidden name="permohonan.idPermohonan" id="bngn_idHakmilik" />
                        </p>
                        <display:table class="tablecloth" name="${actionBean.permohonanLaporanBangunanList}" pagesize="10" cellpadding="0" cellspacing="0"
                                       requestURI="/pembangunan/melaka/laporanTanah"  id="line">
                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                            <display:column property="namaPemunya" title="Pemilik" />
                            <display:column property="namaPenyewa" title="Penyewa" />
                            <display:column property="namaKetua" title="Kegunaan Bangunan" />
                            <display:column title="Keadaan Bangunan">
                                <c:if test="${line.jenisBangunan eq 'KK'}">Kekal</c:if>
                                <c:if test="${line.jenisBangunan eq 'SK'}">Separuh Kekal</c:if>
                                <c:if test="${line.jenisBangunan eq 'SM'}">Sementara</c:if>
                            </display:column>
                            <display:column property="keteranganTahunBinaan" title="Tahun Dibina" />
                            <display:column  title="Kadar Sewa" >RM <fmt:formatNumber pattern="#,##0.00" value="${line.nilai}"/></display:column>
                            <display:column title="Kemaskini">
                                <div align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editLaporanBangunan('${line.idLaporBangunan}','B');"/>
                                </div>
                            </display:column>
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' src='${pageContext.request.contextPath}/images/not_ok.gif'
                                         onclick="deleteLaporanBangunan('${line.idLaporBangunan}');"/>
                                </div>
                            </display:column>
                        </display:table>
                        <s:button class="btn" value="Tambah" name="newbangun" id="bilanganBangunan2" onclick="tambahBangunan('B',this.form);" disabled="${actionBean.disabled}"/>
                    </div></c:if>
                    <div class="content" align="center">
                        Tanah Milik
                    <display:table  name="${actionBean.permohonan.senaraiHakmilik}" id="line" class="tablecloth">
                        <display:column title="Jenis Hakmilik">
                            <c:if test="${line.hakmilik.kodHakmilik.nama ne null}"> ${line.hakmilik.kodHakmilik.nama}&nbsp; </c:if>
                            <c:if test="${line.hakmilik.kodHakmilik.nama eq null}"> Tiada Data </c:if>
                        </display:column>
                        <display:column title="Nombor Hakmilik">
                            <c:if test="${line.hakmilik.idHakmilik ne null}"> ${line.hakmilik.idHakmilik}&nbsp; </c:if>
                            <c:if test="${line.hakmilik.idHakmilik eq null}"> Tiada Data </c:if>
                        </display:column>
                        <display:column title="Nombor Lot/PT" >
                            <c:if test="${line.hakmilik.noLot ne null}">  ${line.hakmilik.lot.nama} <fmt:formatNumber  pattern="00" value="${line.hakmilik.noLot}"/> &nbsp; </c:if>
                            <c:if test="${line.hakmilik.noLot eq null}"> Tiada Data </c:if>
                        </display:column>
                        <display:column title="Luas">
                            <c:if test="${line.hakmilik.luas ne null}"> <fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</c:if>
                            <c:if test="${line.hakmilik.luas eq null}"> Tiada Data </c:if>
                        </display:column>
                        <display:column property="hakmilik.kategoriTanah.nama" title="Kegunaan" >
                            <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot ne null}"> ${actionBean.laporanTanah.sempadanUtaraNoLot}&nbsp; </c:if>
                            <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot eq null}"> Tiada Data </c:if>
                        </display:column>
                        <display:column title="Cukai">
                            <c:if test="${line.hakmilik.cukai ne null}"> RM <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukai}"/>&nbsp; </c:if>
                            <c:if test="${line.hakmilik.cukai eq null}"> Tiada Data </c:if>
                        </display:column>
                    </display:table>
                </div>
                <br>
            </c:if>
            <c:if test="${actionBean.stageId eq 'semaklaporantanah' || actionBean.stageId eq 'semakderafperakuan' || actionBean.stageId eq 'perakuanmmknptd'
                          || actionBean.stageId eq 'ulasanadunteksediajkbb' || actionBean.stageId eq 'derafperakuanjkbbptd' || actionBean.stageId eq 'perakuanjkbbptd'}">
                  <p>
                      <label>Diusahakan :</label>
                      <c:if test="${actionBean.laporanTanah.usaha eq 'Y'}">Tuan Tanah</c:if>
                      <c:if test="${actionBean.laporanTanah.usaha ne 'Y'}">Penyewa</c:if>
                  </p>
                  <p>
                      <label>Tanaman :</label>
                      <c:if test="${actionBean.laporanTanah.namaSempadanLaut eq 'Y'}">Ada</c:if>
                      <c:if test="${actionBean.laporanTanah.namaSempadanLaut ne 'Y'}">Tiada</c:if>
                  </p>
                  <c:if test="${actionBean.laporanTanah.namaSempadanLaut eq 'Y'}">
                      <div class="content" align="center" id="bilanganTanaman">
                          <display:table class="tablecloth" name="${actionBean.permohonanLaporanTanamanList}" pagesize="10" cellpadding="0" cellspacing="0"
                                         requestURI="/pembangunan/melaka/laporanTanah"  id="line">
                              <display:column title="No" sortable="true">${line_rowNum}</display:column>
                              <display:column property="namaPemunya" title="Pemilik" />
                              <display:column property="namaPenyewa" title="Penyewa" />
                              <display:column property="namaKetua" title="Jenis Tanaman" />
                              <display:column title="Keadaan Tanaman">
                                  <c:if test="${line.jenisBangunan eq 'KK'}">Kekal</c:if>
                                  <c:if test="${line.jenisBangunan eq 'SM'}">Sementara</c:if>
                              </display:column>
                              <display:column property="keteranganTahunBinaan" title="Tahun Dibina" />
                              <display:column  title="Kadar Sewa" >RM <fmt:formatNumber pattern="#,##0.00" value="${line.nilai}"/></display:column>
                          </display:table>
                      </div>
                  </c:if>
                  <p>
                      <label>Bangunan :</label>
                      <c:if test="${actionBean.laporanTanah.adaBangunan eq 'Y'}">Ada</c:if>
                      <c:if test="${actionBean.laporanTanah.adaBangunan ne 'Y'}">Tiada</c:if>
                  </p>
                  <c:if test="${actionBean.laporanTanah.adaBangunan eq 'Y'}">
                      <div class="content" align="center" id="bilanganBangunan">
                          <p>
                              <s:select name="bngn_idHakmilik" id="bngn_idHakmilik" disabled="disabled" style="display : none">
                                  <s:option value="">--Sila Pilih--</s:option>
                                  <s:options-collection collection="${actionBean.hakmilikPermohonanList}" label="hakmilik.idHakmilik" value="hakmilik.idHakmilik" />
                              </s:select>
                              <s:hidden name="permohonan.idPermohonan" id="bngn_idHakmilik" />
                          </p>
                          <display:table class="tablecloth" name="${actionBean.permohonanLaporanBangunanList}" pagesize="10" cellpadding="0" cellspacing="0"
                                         requestURI="/pembangunan/melaka/laporanTanah"  id="line">
                              <display:column title="No" sortable="true">${line_rowNum}</display:column>
                              <display:column property="namaPemunya" title="Pemilik" />
                              <display:column property="namaPenyewa" title="Penyewa" />
                              <display:column property="namaKetua" title="Kegunaan Bangunan" />
                              <display:column title="Keadaan Bangunan">
                                  <c:if test="${line.jenisBangunan eq 'KK'}">Kekal</c:if>
                                  <c:if test="${line.jenisBangunan eq 'SK'}">Separuh Kekal</c:if>
                                  <c:if test="${line.jenisBangunan eq 'SM'}">Sementara</c:if>
                              </display:column>
                              <display:column property="keteranganTahunBinaan" title="Tahun Dibina" />
                              <display:column title="Kadar Sewa"><c:if test="${line.nilai ne ''}">RM <fmt:formatNumber pattern="#,##0.00" value="${line.nilai}"/></c:if></display:column>
                          </display:table>
                      </div>
                  </c:if>
                  <div class="content" align="center">
                      Tanah Milik
                      <display:table  name="${actionBean.permohonan.senaraiHakmilik}" id="line" class="tablecloth">
                          <display:column title="Jenis Hakmilik">
                              <c:if test="${line.hakmilik.kodHakmilik.nama ne null}"> ${line.hakmilik.kodHakmilik.nama}&nbsp; </c:if>
                              <c:if test="${line.hakmilik.kodHakmilik.nama eq null}"> Tiada Data </c:if>
                          </display:column>
                          <display:column title="Nombor Hakmilik">
                              <c:if test="${line.hakmilik.idHakmilik ne null}"> ${line.hakmilik.idHakmilik}&nbsp; </c:if>
                              <c:if test="${line.hakmilik.idHakmilik eq null}"> Tiada Data </c:if>
                          </display:column>
                          <display:column title="Nombor Lot/PT" >
                              <c:if test="${line.hakmilik.noLot ne null}"> ${line.hakmilik.lot.nama} <fmt:formatNumber  pattern="00" value="${line.hakmilik.noLot}"/>&nbsp; </c:if>
                              <c:if test="${line.hakmilik.noLot eq null}"> Tiada Data </c:if>
                          </display:column>
                          <display:column title="Luas">
                              <c:if test="${line.hakmilik.luas ne null}"> <fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</c:if>
                              <c:if test="${line.hakmilik.luas eq null}"> Tiada Data </c:if>
                          </display:column>
                          <display:column property="hakmilik.kategoriTanah.nama" title="Kegunaan" >
                              <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot ne null}"> ${actionBean.laporanTanah.sempadanUtaraNoLot}&nbsp; </c:if>
                              <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot eq null}"> Tiada Data </c:if>
                          </display:column>
                          <display:column title="Cukai">
                              <c:if test="${line.hakmilik.cukai ne null}">RM <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukai}"/>&nbsp; </c:if>
                              <c:if test="${line.hakmilik.cukai eq null}"> Tiada Data </c:if>
                          </display:column>
                      </display:table>
                  </div>
            </c:if>
            <br>
        </fieldset>
    </div>
    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Perihal Lot-Lot Bersempadan</legend>
            <c:if test="${actionBean.stageId eq 'laporantanah'}">
                <div class="content" align="center">
                    <table class="tablecloth">
                        <tr>
                            <th>&nbsp;</th><th>Status Tanah</th><th>Nombor Lot/PT</th><th>Kategori Tanah</th><th>Catatan</th><th style="display : none">Gambar</th>
                        </tr>
                        <tr>
                            <th>
                                Utara
                            </th>
                            <td>
                                <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="Y" disabled="${actionBean.disabled}"/>&nbsp;Kerajaan
                                <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="T" disabled="${actionBean.disabled}"/>&nbsp;Milik
                            </td>
                            <td>
                                <s:text name="laporanTanah.sempadanUtaraNoLot" disabled="${actionBean.disabled}"/>
                            </td>
                            <td>
                                <s:select name="laporanTanah.sempadanUtaraJenis" disabled="${actionBean.disabled}">
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:option value="1">Industri</s:option>
                                    <s:option value="2">Bangunan</s:option>
                                    <s:option value="3">Pertanian</s:option>
                                    <s:option value="4">Rizab</s:option>
                                </s:select>
                            </td>
                            <td>
                                <s:textarea rows="2" cols="100" name="laporanTanah.sempadanUtaraKegunaan" class="normal_text" disabled="${actionBean.disabled}"/>
                            </td>
                            <td style="display: none">
                                <c:forEach items="${actionBean.utaraImejLaporanList}" var="senarai" >
                                    <c:out value="${senarai.dokumen.tajuk}"/><br/>
                                </c:forEach>
                                <s:select name="utaraImej" id="utaraImej" disabled="${actionBean.disabled}">
                                    <s:option value="">Sila pilih imej</s:option>
                                    <s:options-collection collection="${actionBean.dokumenList}" label="tajuk" value="idDokumen"/>
                                </s:select>
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReportUtara();" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                <s:button class="btn" value="Tambah" name="addUtaraImej" id="addUtaraImej" onclick="addImageUtara('${actionBean.permohonan.senaraiHakmilik[0].hakmilik.idHakmilik}');" disabled="${actionBean.disabled}"/>
                            </td>
                        </tr>

                        <tr>
                            <th>
                                Timur Laut
                            </th>
                            <td>
                                <s:radio name="laporanTanah.sempadanTimurLautMilikKerajaan" value="Y" disabled="${actionBean.disabled}"/>&nbsp;Kerajaan
                                <s:radio name="laporanTanah.sempadanTimurLautMilikKerajaan" value="T" disabled="${actionBean.disabled}"/>&nbsp;Milik
                            </td>
                            <td>
                                <s:text name="laporanTanah.sempadanTimurLautNoLot" disabled="${actionBean.disabled}"/>
                            </td>
                            <td>
                                <s:select name="laporanTanah.sempadanTimurlautJenis" disabled="${actionBean.disabled}">
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:option value="1">Industri</s:option>
                                    <s:option value="2">Bangunan</s:option>
                                    <s:option value="3">Pertanian</s:option>
                                    <s:option value="4">Rizab</s:option>
                                </s:select>
                            </td>
                            <td>
                                <s:textarea rows="2" cols="100" name="laporanTanah.sempadanTimurlautKegunaan" class="normal_text" disabled="${actionBean.disabled}"/>
                            </td>
                            <td style="display: none">
                                <c:forEach items="${actionBean.timurLautImejLaporanList}" var="senarai" >
                                    <c:out value="${senarai.dokumen.tajuk}"/><br/>
                                </c:forEach>
                                <s:select name="timurLautImej" id="timurLautImej" disabled="${actionBean.disabled}">
                                    <s:option value="">Sila pilih imej</s:option>
                                    <s:options-collection collection="${actionBean.dokumenList}" label="tajuk" value="idDokumen"/>
                                </s:select>
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReportTimurLaut();" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                <s:button class="btn" value="Tambah" name="addTimurLautImej" id="addTimurLautImej" onclick="addImageTimurLaut('${actionBean.permohonan.senaraiHakmilik[0].hakmilik.idHakmilik}');" disabled="${actionBean.disabled}"/>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Timur
                            </th>
                            <td>
                                <s:radio name="laporanTanah.sempadanTimurMilikKerajaan" value="Y" disabled="${actionBean.disabled}"/>&nbsp;Kerajaan
                                <s:radio name="laporanTanah.sempadanTimurMilikKerajaan" value="T" disabled="${actionBean.disabled}"/>&nbsp;Milik
                            </td>
                            <td>
                                <s:text name="laporanTanah.sempadanTimurNoLot" disabled="${actionBean.disabled}"/>
                            </td>
                            <td>
                                <s:select name="laporanTanah.sempadanTimurJenis" disabled="${actionBean.disabled}">
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:option value="1">Industri</s:option>
                                    <s:option value="2">Bangunan</s:option>
                                    <s:option value="3">Pertanian</s:option>
                                    <s:option value="4">Rizab</s:option>
                                </s:select>
                            </td>
                            <td>
                                <s:textarea rows="2" cols="100" name="laporanTanah.sempadanTimurKegunaan" class="normal_text" disabled="${actionBean.disabled}"/>
                            </td>
                            <td style="display: none">
                                <c:forEach items="${actionBean.timurImejLaporanList}" var="senarai">
                                    <c:out value="${senarai.dokumen.tajuk}" /><br/>
                                </c:forEach>
                                <s:select name="timurImej" id="timurImej" disabled="${actionBean.disabled}">
                                    <s:option value="">Sila pilih imej</s:option>
                                    <s:options-collection collection="${actionBean.dokumenList}" label="tajuk" value="idDokumen"/>
                                </s:select>
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReportTimur();" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                <s:button class="btn" value="Tambah" name="addTimurImej" id="addTimurImej" onclick="addImageTimur('${actionBean.permohonan.senaraiHakmilik[0].hakmilik.idHakmilik}');" disabled="${actionBean.disabled}"/>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Tenggara
                            </th>
                            <td>
                                <s:radio name="laporanTanah.sempadanTenggaraMilikKerajaan" value="Y" disabled="${actionBean.disabled}"/>&nbsp;Kerajaan
                                <s:radio name="laporanTanah.sempadanTenggaraMilikKerajaan" value="T" disabled="${actionBean.disabled}"/>&nbsp;Milik
                            </td>
                            <td>
                                <s:text name="laporanTanah.sempadanTenggaraNoLot" disabled="${actionBean.disabled}"/>
                            </td>
                            <td>
                                <s:select name="laporanTanah.sempadanTenggaraJenis" disabled="${actionBean.disabled}">
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:option value="1">Industri</s:option>
                                    <s:option value="2">Bangunan</s:option>
                                    <s:option value="3">Pertanian</s:option>
                                    <s:option value="4">Rizab</s:option>
                                </s:select>
                            </td>
                            <td>
                                <s:textarea rows="2" cols="100" name="laporanTanah.sempadanTenggaraKegunaan" class="normal_text" disabled="${actionBean.disabled}"/>
                            </td>
                            <td style="display: none">
                                <c:forEach items="${actionBean.tenggaraImejLaporanList}" var="senarai" >
                                    <c:out value="${senarai.dokumen.tajuk}"/><br/>
                                </c:forEach>
                                <s:select name="tenggaraImej" id="tenggaraImej" disabled="${actionBean.disabled}">
                                    <s:option value="">Sila pilih imej</s:option>
                                    <s:options-collection collection="${actionBean.dokumenList}" label="tajuk" value="idDokumen"/>
                                </s:select>
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReportTenggara();" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                <s:button class="btn" value="Tambah" name="addTenggaraImej" id="addTenggaraImej" onclick="addImageTenggara('${actionBean.permohonan.senaraiHakmilik[0].hakmilik.idHakmilik}');" disabled="${actionBean.disabled}"/>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Selatan
                            </th>
                            <td>
                                <s:radio name="laporanTanah.sempadanSelatanMilikKerajaan" value="Y" disabled="${actionBean.disabled}"/>&nbsp;Kerajaan
                                <s:radio name="laporanTanah.sempadanSelatanMilikKerajaan" value="T" disabled="${actionBean.disabled}"/>&nbsp;Milik
                            </td>
                            <td>
                                <s:text name="laporanTanah.sempadanSelatanNoLot" disabled="${actionBean.disabled}"/>
                            </td>
                            <td>
                                <s:select name="laporanTanah.sempadanSelatanJenis" disabled="${actionBean.disabled}">
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:option value="1">Industri</s:option>
                                    <s:option value="2">Bangunan</s:option>
                                    <s:option value="3">Pertanian</s:option>
                                    <s:option value="4">Rizab</s:option>
                                </s:select>
                            </td>
                            <td>
                                <s:textarea rows="2" cols="100" name="laporanTanah.sempadanSelatanKegunaan" class="normal_text" disabled="${actionBean.disabled}"/>
                            </td>
                            <td style="display: none">
                                <c:forEach items="${actionBean.selatanImejLaporanList}" var="senarai">
                                    <c:out value="${senarai.dokumen.tajuk}" /><br/>
                                </c:forEach>
                                <s:select name="selatanImej" id="selatanImej" disabled="${actionBean.disabled}">
                                    <s:option value="">Sila pilih imej</s:option>
                                    <s:options-collection collection="${actionBean.dokumenList}" label="tajuk" value="idDokumen"/>
                                </s:select>
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReportSelatan();" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                <s:button class="btn" value="Tambah" name="addSelatanImej" id="addSelatanImej" onclick="addImageSelatan('${actionBean.permohonan.senaraiHakmilik[0].hakmilik.idHakmilik}');" disabled="${actionBean.disabled}"/>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Barat Daya
                            </th>
                            <td>
                                <s:radio name="laporanTanah.sempadanBaratdayaMilikKerajaan" value="Y" disabled="${actionBean.disabled}"/>&nbsp;Kerajaan
                                <s:radio name="laporanTanah.sempadanBaratdayaMilikKerajaan" value="T" disabled="${actionBean.disabled}"/>&nbsp;Milik
                            </td>
                            <td>
                                <s:text name="laporanTanah.sempadanBaratdayaNoLot" disabled="${actionBean.disabled}"/>
                            </td>
                            <td>
                                <s:select name="laporanTanah.sempadanBaratdayaJenis" disabled="${actionBean.disabled}">
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:option value="1">Industri</s:option>
                                    <s:option value="2">Bangunan</s:option>
                                    <s:option value="3">Pertanian</s:option>
                                    <s:option value="4">Rizab</s:option>
                                </s:select>
                            </td>
                            <td>
                                <s:textarea rows="2" cols="100" name="laporanTanah.sempadanBaratdayaKegunaan" class="normal_text" disabled="${actionBean.disabled}"/>
                            </td>
                            <td style="display: none">
                                <c:forEach items="${actionBean.baratDayaImejLaporanList}" var="senarai" >
                                    <c:out value="${senarai.dokumen.tajuk}"/><br/>
                                </c:forEach>
                                <s:select name="baratDayaImej" id="baratDayaImej" disabled="${actionBean.disabled}">
                                    <s:option value="">Sila pilih imej</s:option>
                                    <s:options-collection collection="${actionBean.dokumenList}" label="tajuk" value="idDokumen"/>
                                </s:select>
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReportBaratDaya();" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                <s:button class="btn" value="Tambah" name="addBaratDayaImej" id="addBaratDayaImej" onclick="addImageBaratDaya('${actionBean.permohonan.senaraiHakmilik[0].hakmilik.idHakmilik}');" disabled="${actionBean.disabled}"/>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Barat
                            </th>
                            <td>
                                <s:radio name="laporanTanah.sempadanBaratMilikKerajaan" value="Y" disabled="${actionBean.disabled}"/>&nbsp;Kerajaan
                                <s:radio name="laporanTanah.sempadanBaratMilikKerajaan" value="T" disabled="${actionBean.disabled}"/>&nbsp;Milik
                            </td>
                            <td>
                                <s:text name="laporanTanah.sempadanBaratNoLot" disabled="${actionBean.disabled}"/>
                            </td>
                            <td>
                                <s:select name="laporanTanah.sempadanBaratJenis" disabled="${actionBean.disabled}">
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:option value="1">Industri</s:option>
                                    <s:option value="2">Bangunan</s:option>
                                    <s:option value="3">Pertanian</s:option>
                                    <s:option value="4">Rizab</s:option>
                                </s:select>
                            </td>
                            <td>
                                <s:textarea rows="2" cols="100" name="laporanTanah.sempadanBaratKegunaan" class="normal_text" disabled="${actionBean.disabled}"/>
                            </td>
                            <td style="display: none">
                                <c:forEach items="${actionBean.baratImejLaporanList}" var="senarai">
                                    <c:out value="${senarai.dokumen.tajuk}" /><br/>
                                </c:forEach>
                                <s:select name="baratImej" id="baratImej" disabled="${actionBean.disabled}">
                                    <s:option value="">Sila pilih imej</s:option>
                                    <s:options-collection collection="${actionBean.dokumenList}" label="tajuk" value="idDokumen"/>
                                </s:select>
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReportBarat();" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                <s:button class="btn" value="Tambah" name="addBaratImej" id="addBaratImej" onclick="addImageBarat('${actionBean.permohonan.senaraiHakmilik[0].hakmilik.idHakmilik}');" disabled="${actionBean.disabled}"/>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Barat Laut
                            </th>
                            <td>
                                <s:radio name="laporanTanah.sempadanBaratLautMilikKerajaan" value="Y" disabled="${actionBean.disabled}"/>&nbsp;Kerajaan
                                <s:radio name="laporanTanah.sempadanBaratLautMilikKerajaan" value="T" disabled="${actionBean.disabled}"/>&nbsp;Milik
                            </td>
                            <td>
                                <s:text name="laporanTanah.sempadanBaratlautNoLot" disabled="${actionBean.disabled}"/>
                            </td>
                            <td>
                                <s:select name="laporanTanah.sempadanBaratlautJenis" disabled="${actionBean.disabled}">
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:option value="1">Industri</s:option>
                                    <s:option value="2">Bangunan</s:option>
                                    <s:option value="3">Pertanian</s:option>
                                    <s:option value="4">Rizab</s:option>
                                </s:select>
                            </td>
                            <td>
                                <s:textarea rows="2" cols="100" name="laporanTanah.sempadanBaratlautKegunaan" class="normal_text" disabled="${actionBean.disabled}"/>
                            </td>
                            <td style="display: none">
                                <c:forEach items="${actionBean.baratLautImejLaporanList}" var="senarai" >
                                    <c:out value="${senarai.dokumen.tajuk}"/><br/>
                                </c:forEach>
                                <s:select name="baratLautImej" id="baratLautImej" disabled="${actionBean.disabled}">
                                    <s:option value="">Sila pilih imej</s:option>
                                    <s:options-collection collection="${actionBean.dokumenList}" label="tajuk" value="idDokumen"/>
                                </s:select>
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReportBaratLaut();" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                <s:button class="btn" value="Tambah" name="addBaratLautImej" id="addBaratLautImej" onclick="addImageBaratLaut('${actionBean.permohonan.senaraiHakmilik[0].hakmilik.idHakmilik}');" disabled="${actionBean.disabled}"/>
                            </td>
                        </tr>
                    </table>
                </div>
                <br>
            </c:if>
            <c:if test="${actionBean.stageId eq 'semaklaporantanah' || actionBean.stageId eq 'semakderafperakuan' || actionBean.stageId eq 'perakuanmmknptd'
                          || actionBean.stageId eq 'ulasanadunteksediajkbb' || actionBean.stageId eq 'derafperakuanjkbbptd' || actionBean.stageId eq 'perakuanjkbbptd'}">
                  <div class="content" align="center">
                      <table class="tablecloth">
                          <tr>
                              <th>&nbsp;</th><th>Status Tanah</th><th>Nombor Lot/PT</th><th>Kategori Tanah</th><th>Catatan</th>
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
                                  <c:if test="${actionBean.laporanTanah.sempadanUtaraJenis eq '1'}">Industri&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanUtaraJenis eq '2'}">Bangunan&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanUtaraJenis eq '3'}">Pertanian&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanUtaraJenis eq '4'}">Rizab&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanUtaraJenis eq null}"> Tiada Data </c:if>
                              </td>
                              <td>
                                  <c:if test="${actionBean.laporanTanah.sempadanUtaraKegunaan ne null}"> ${actionBean.laporanTanah.sempadanUtaraKegunaan}&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanUtaraKegunaan eq null}"> Tiada Data </c:if>
                              </td>
                          </tr>
                          <tr>
                              <th>
                                  Timur Laut
                              </th>
                              <td>
                                  <c:if test="${actionBean.laporanTanah.sempadanTimurLautMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanTimurLautMilikKerajaan eq 'T'}">Milik</c:if>
                              </td>
                              <td>
                                  <c:if test="${actionBean.laporanTanah.sempadanTimurLautNoLot ne null}"> ${actionBean.laporanTanah.sempadanTimurLautNoLot}&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanTimurLautNoLot eq null}"> Tiada Data </c:if>
                              </td>
                              <td>
                                  <c:if test="${actionBean.laporanTanah.sempadanTimurlautJenis eq '1'}">Industri&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanTimurlautJenis eq '2'}">Bangunan&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanTimurlautJenis eq '3'}">Pertanian&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanTimurlautJenis eq '4'}">Rizab&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanTimurlautJenis eq null}"> Tiada Data </c:if>
                              </td>
                              <td>
                                  <c:if test="${actionBean.laporanTanah.sempadanTimurlautKegunaan ne null}"> ${actionBean.laporanTanah.sempadanTimurlautKegunaan}&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanTimurlautKegunaan eq null}"> Tiada Data </c:if>
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
                                  <c:if test="${actionBean.laporanTanah.sempadanTimurJenis eq '1'}">Industri&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanTimurJenis eq '2'}">Bangunan&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanTimurJenis eq '3'}">Pertanian&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanTimurJenis eq '4'}">Rizab&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanTimurJenis eq null}"> Tiada Data </c:if>
                              </td>
                              <td>
                                  <c:if test="${actionBean.laporanTanah.sempadanTimurKegunaan ne null}"> ${actionBean.laporanTanah.sempadanTimurKegunaan}&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanTimurKegunaan eq null}"> Tiada Data </c:if>
                              </td>
                          </tr>
                          <tr>
                              <th>
                                  Tenggara
                              </th>
                              <td>
                                  <c:if test="${actionBean.laporanTanah.sempadanTenggaraMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanTenggaraMilikKerajaan eq 'T'}">Milik</c:if>
                              </td>
                              <td>
                                  <c:if test="${actionBean.laporanTanah.sempadanTenggaraNoLot ne null}"> ${actionBean.laporanTanah.sempadanTenggaraNoLot}&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanTenggaraNoLot eq null}"> Tiada Data </c:if>
                              </td>
                              <td>
                                  <c:if test="${actionBean.laporanTanah.sempadanTenggaraJenis eq '1'}">Industri&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanTenggaraJenis eq '2'}">Bangunan&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanTenggaraJenis eq '3'}">Pertanian&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanTenggaraJenis eq '4'}">Rizab&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanTenggaraJenis eq null}"> Tiada Data </c:if>
                              </td>
                              <td>
                                  <c:if test="${actionBean.laporanTanah.sempadanTenggaraKegunaan ne null}"> ${actionBean.laporanTanah.sempadanTenggaraKegunaan}&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanTenggaraKegunaan eq null}"> Tiada Data </c:if>
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
                                  <c:if test="${actionBean.laporanTanah.sempadanSelatanJenis eq '1'}">Industri&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanSelatanJenis eq '2'}">Bangunan&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanSelatanJenis eq '3'}">Pertanian&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanSelatanJenis eq '4'}">Rizab&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanSelatanJenis eq null}"> Tiada Data </c:if>
                              </td>
                              <td>
                                  <c:if test="${actionBean.laporanTanah.sempadanSelatanKegunaan ne null}"> ${actionBean.laporanTanah.sempadanSelatanKegunaan}&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanSelatanKegunaan eq null}"> Tiada Data </c:if>
                              </td>
                          </tr>
                          <tr>
                              <th>
                                  Barat Daya
                              </th>
                              <td>
                                  <c:if test="${actionBean.laporanTanah.sempadanBaratdayaMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanBaratdayaMilikKerajaan eq 'T'}">Milik</c:if>
                              </td>
                              <td>
                                  <c:if test="${actionBean.laporanTanah.sempadanBaratdayaNoLot ne null}"> ${actionBean.laporanTanah.sempadanBaratdayaNoLot}&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanBaratdayaNoLot eq null}"> Tiada Data </c:if>
                              </td>
                              <td>
                                  <c:if test="${actionBean.laporanTanah.sempadanBaratdayaJenis eq '1'}">Industri&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanBaratdayaJenis eq '2'}">Bangunan&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanBaratdayaJenis eq '3'}">Pertanian&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanBaratdayaJenis eq '4'}">Rizab&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanBaratdayaJenis eq null}"> Tiada Data </c:if>
                              </td>
                              <td>
                                  <c:if test="${actionBean.laporanTanah.sempadanBaratdayaKegunaan ne null}"> ${actionBean.laporanTanah.sempadanBaratdayaKegunaan}&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanBaratdayaKegunaan eq null}"> Tiada Data </c:if>
                              </td>
                          </tr>

                          <tr>
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
                                  <c:if test="${actionBean.laporanTanah.sempadanBaratJenis eq '1'}">Industri&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanBaratJenis eq '2'}">Bangunan&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanBaratJenis eq '3'}">Pertanian&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanBaratJenis eq '4'}">Rizab&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanBaratJenis eq null}"> Tiada Data </c:if>
                              </td>
                              <td>
                                  <c:if test="${actionBean.laporanTanah.sempadanBaratKegunaan ne null}"> ${actionBean.laporanTanah.sempadanBaratKegunaan}&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanBaratKegunaan eq null}"> Tiada Data </c:if>
                              </td>
                          </tr>
                          <tr>
                              <th>
                                  Barat Laut
                              </th>
                              <td>
                                  <c:if test="${actionBean.laporanTanah.sempadanBaratLautMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanBaratLautMilikKerajaan eq 'T'}">Milik</c:if>
                              </td>
                              <td>
                                  <c:if test="${actionBean.laporanTanah.sempadanBaratlautNoLot ne null}"> ${actionBean.laporanTanah.sempadanBaratlautNoLot}&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanBaratlautNoLot eq null}"> Tiada Data </c:if>
                              </td>
                              <td>
                                  <c:if test="${actionBean.laporanTanah.sempadanBaratlautJenis eq '1'}">Industri&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanBaratlautJenis eq '2'}">Bangunan&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanBaratlautJenis eq '3'}">Pertanian&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanBaratlautJenis eq '4'}">Rizab&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanBaratlautJenis eq null}"> Tiada Data </c:if>
                              </td>
                              <td>
                                  <c:if test="${actionBean.laporanTanah.sempadanBaratlautKegunaan ne null}"> ${actionBean.laporanTanah.sempadanBaratlautKegunaan}&nbsp; </c:if>
                                  <c:if test="${actionBean.laporanTanah.sempadanBaratlautKegunaan eq null}"> Tiada Data </c:if>
                              </td>
                          </tr>
                      </table>
                  </div>
                  <br>
            </c:if>
        </fieldset>
    </div>
    <br>

    <c:if test = "${!(actionBean.permohonan.kodUrusan.kod eq 'SBMS' || actionBean.permohonan.kodUrusan.kod eq 'TSPSS')}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Perakuan/Syor</legend><br>
                <c:if test="${actionBean.stageId eq 'laporantanah'}">
                    <p>
                        <label>Jika diperakukan :-</label>
                        <s:radio name="laporanTanah.syor" value="Y" onclick="javaScript:showUlasan(this.value);doSubmit(this.form, simpanLaporanTanah, 'page_div')"/>&nbsp;Ya
                        <s:radio name="laporanTanah.syor" value="T" onclick="javaScript:showUlasan(this.value);doSubmit(this.form, simpanLaporanTanah, 'page_div')" />&nbsp;Tidak
                    </p>
                    <c:if test="${actionBean.laporanTanah.syor eq 'Y'}">
                        <div class="content" id="perakuanSyor">
                            <table border="0" cellspacing="2">                            
                                <tr>
                                    <td><label>Premium tambahan :</label>
                                    <td><s:textarea name="hakmilikPermohonan.keteranganKadarPremium" rows="3" cols="30" disabled="${actionBean.disabled}"/>                                        
                                </tr>                          
                                <tr>
                                    <td><label>Premium denda(jika ada):</label></td>
                                    <td><s:radio name="hakmilikPermohonan.dendaPremium" value="0"/> Tiada
                                        <s:radio name="hakmilikPermohonan.dendaPremium" value="1"/> 1x Nilai Premium
                                        <s:radio name="hakmilikPermohonan.dendaPremium" value="2"/> 2x Nilai Premium
                                    </td>                              
                                </tr>

                                <c:if test = "${actionBean.hakmilikPermohonan.kategoriTanahBaru.kod ne null}">
                                    <c:choose>
                                        <c:when test = "${actionBean.hakmilikPermohonan.kategoriTanahBaru.kod eq '1'}">                          
                                            <tr>
                                                <td><label>Hasil :</label></td>
                                                <td align="left"><s:text name="hakmilikPermohonan.keteranganCukaiBaru"  disabled="${actionBean.disabled}"/> * bagi setiap 1 ha
                                            </tr>
                                        </c:when> 
                                        <c:when test = "${actionBean.hakmilikPermohonan.kategoriTanahBaru.kod eq '0'}">                          
                                            <tr>
                                                <td><label>Hasil :</label></td>
                                                <td align="left"><s:text name="hakmilikPermohonan.keteranganCukaiBaru"  disabled="${actionBean.disabled}"/>
                                            </tr>
                                        </c:when> 
                                        <c:otherwise>
                                            <tr>
                                                <td><label>Hasil :</label></td> 
                                                <td align="left"><s:text name="hakmilikPermohonan.keteranganCukaiBaru"  disabled="${actionBean.disabled}"/> * bagi setiap 100 mp
                                            </tr>  
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>

                                <c:if test = "${actionBean.hakmilikPermohonan.kategoriTanahBaru.kod eq null}">
                                    <c:choose>
                                        <c:when test = "${actionBean.hakmilik.kategoriTanah.kod eq '1'}">
                                            <tr>
                                                <td><label>Hasil :</label></td> 
                                                <td align="left"><s:text name="hakmilikPermohonan.keteranganCukaiBaru"  disabled="${actionBean.disabled}"/> * bagi setiap 1 ha
                                            </tr>  
                                        </c:when>
                                        <c:otherwise>
                                            <tr>
                                                <td><label>Hasil :</label></td> 
                                                <td align="left"><s:text name="hakmilikPermohonan.keteranganCukaiBaru"  disabled="${actionBean.disabled}"/> * bagi setiap 100 mp
                                            </tr>  
                                        </c:otherwise>                          
                                    </c:choose>
                                </c:if>                            
                                <c:choose>
                                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'TSPSS' || actionBean.permohonan.kodUrusan.kod eq 'SBMS' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK'|| actionBean.permohonan.kodUrusan.kod eq 'TSKSN'|| actionBean.permohonan.kodUrusan.kod eq 'TSPSN'|| actionBean.permohonan.kodUrusan.kod eq 'TSKKT'}">
                                        <tr>
                                            <td valign="top"><label>Syarat Lama/Sekatan Milik dihapuskan dan digantikan dengan Syarat Nyata :</label>
                                            <td><s:textarea name="kodSyaratNyataBaru1" id="syaratNyata1" readonly="readonly" rows="5" cols="80" value="${actionBean.hakmilikPermohonan.syaratNyataBaru.kod} - ${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}"  />
                                                <s:hidden name="syaratBaru1" id="kod1" value="${actionBean.hakmilikPermohonan.syaratNyataBaru.kod}" />
                                        </tr>
                                        <tr>
                                            <td><label>&nbsp;</label>
                                            <td><s:button class="btn" value="Cari" name="add" onclick="javascript:searchKodSyaratNyata(1)" />
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td valign="top"><label>Syarat Lama/Sekatan Milik dihapuskan dan digantikan dengan Syarat Nyata :</label></td>
                                            <td><c:if test="${actionBean.hakmilik.syaratNyata.syarat ne null}">${actionBean.hakmilik.syaratNyata.syarat}</c:if>
                                                <c:if test="${actionBean.hakmilik.syaratNyata.syarat eq null}"> Tiada Data </c:if> &nbsp;
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                                <tr>
                                    <td valign="top"><label>Sekatan Milik :</label></td>
                                    <td><c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan ne null}">${actionBean.hakmilik.sekatanKepentingan.sekatan}</c:if>
                                        <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan eq null}"> Tiada Data </c:if> &nbsp;</td>
                                </tr>                         

                                <c:choose>
                                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'TSPSS' || actionBean.permohonan.kodUrusan.kod eq 'SBMS' || actionBean.permohonan.kodUrusan.kod eq 'TSKSN'}">
                                        <tr>
                                            <td><label>Dikenakan Penjenisan :</label></td>
                                            <td><s:select name="kategoriTanahBaruString" disabled="${actionBean.disabled}">
                                                    <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" />
                                                </s:select></td>
                                        </tr>  
                                    </c:when>                           
                                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'TSKKT'}">
                                        <tr>
                                            <td><label>Dikenakan Penjenisan :</label></td>
                                            <td><c:if test="${actionBean.hakmilikPermohonan.kategoriTanahBaru.nama ne null}">${actionBean.hakmilikPermohonan.kategoriTanahBaru.nama}</c:if>
                                                <c:if test="${actionBean.hakmilikPermohonan.kategoriTanahBaru.nama eq null}"> Tiada Data </c:if> &nbsp;
                                        </tr>                               
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td><label>Dikenakan Penjenisan :</label></td>
                                            <td><c:if test="${actionBean.hakmilik.kategoriTanah.nama ne null}">${actionBean.hakmilik.kategoriTanah.nama}</c:if>
                                                <c:if test="${actionBean.hakmilik.kategoriTanah.nama eq null}">${actionBean.hakmilik.kategoriTanah.nama}</c:if> &nbsp;
                                        </tr>   
                                    </c:otherwise>
                                </c:choose>

                                <c:if test="${actionBean.stageId eq 'semaklaporantanah' || actionBean.stageId eq 'semakderafperakuan' || actionBean.stageId eq 'perakuanmmknptd'
                                              || actionBean.stageId eq 'ulasanadunteksediajkbb' || actionBean.stageId eq 'derafperakuanjkbbptd' || actionBean.stageId eq 'perakuanjkbbptd'}">
                                    <s:hidden name="hakmilikPermohonan.kategoriTanahBaru.kod"></s:hidden>
                                </c:if>
                                <tr>                    
                                    <td><label>Sumbangan Saliran:</label>
                                    <td><s:text name="hakmilikPermohonan.keteranganInfra"  disabled="${actionBean.disabled}"/>                      
                                </tr>
                                <tr>
                                    <td><label>Semua bayaran dijelaskan dalam tempoh:</label>
                                    <td><b>3 bulan selepas tarikh kelulusan.</b></td>
                                </tr>                           
                            </table>
                        </div>
                    </c:if>
                    <c:if test="${actionBean.laporanTanah.syor eq 'T'}">
                        <div id="perakuanSyorUlasan">
                            <p>
                                <label>Ulasan :</label>
                                <s:textarea name="permohonanLaporanUlasan.ulasan" cols="50" disabled="${actionBean.disabled}"/>
                            </p>
                        </div>
                    </c:if>                  
                    <br>  
                </c:if>

                <c:if test="${actionBean.stageId eq 'semaklaporantanah'}">
                    <p>
                        <label>Jika diperakukan :-</label>
                        <c:if test="${actionBean.laporanTanah.syor eq 'Y'}"><b>Ya</b></c:if>
                        <c:if test="${actionBean.laporanTanah.syor eq 'T'}"><b>Tidak</b></c:if>
                    </p>
                    <c:if test="${actionBean.laporanTanah.syor eq 'Y'}">
                        <div class="content" id="perakuanSyor">
                            <table border="0" cellspacing="2">                             
                                <tr>
                                    <td><label>Premium tambahan :</label>
                                    <td><c:if test="${actionBean.hakmilikPermohonan.keteranganKadarPremium ne null}"> ${actionBean.hakmilikPermohonan.keteranganKadarPremium}&nbsp; </c:if>
                                        <c:if test="${actionBean.hakmilikPermohonan.keteranganKadarPremium eq null}"> Tiada Data </c:if>
                                </tr>
                                <tr>
                                    <td><label>Premium denda(jika ada) :</label>
                                    <td><c:if test="${actionBean.hakmilikPermohonan.dendaPremium eq '0'}"> Tiada </c:if>
                                        <c:if test="${actionBean.hakmilikPermohonan.dendaPremium eq '1'}"> 1x Nilai Premium </c:if>
                                        <c:if test="${actionBean.hakmilikPermohonan.dendaPremium eq '2'}"> 2x Nilai Premium </c:if>
                                        <c:if test="${actionBean.hakmilikPermohonan.dendaPremium eq null}"> Tiada Data </c:if>
                                </tr>                          

                                <c:if test = "${actionBean.hakmilikPermohonan.kategoriTanahBaru.kod ne null}">
                                    <c:choose>
                                        <c:when test = "${actionBean.hakmilikPermohonan.kategoriTanahBaru.kod eq '1'}"> 
                                            <tr>
                                                <td><label>Hasil :</label>
                                                <td><c:if test="${actionBean.hakmilikPermohonan.keteranganCukaiBaru ne null}"> ${actionBean.hakmilikPermohonan.keteranganCukaiBaru}&nbsp; * bagi setiap 1 ha </c:if>
                                                    <c:if test="${actionBean.hakmilikPermohonan.keteranganCukaiBaru eq null}"> Tiada Data </c:if>
                                            </tr>
                                        </c:when>   
                                        <c:when test = "${actionBean.hakmilikPermohonan.kategoriTanahBaru.kod eq '0'}"> 
                                            <tr>
                                                <td><label>Hasil :</label>
                                                <td><c:if test="${actionBean.hakmilikPermohonan.keteranganCukaiBaru ne null}"> ${actionBean.hakmilikPermohonan.keteranganCukaiBaru}</c:if>
                                                    <c:if test="${actionBean.hakmilikPermohonan.keteranganCukaiBaru eq null}"> Tiada Data </c:if>
                                            </tr>
                                        </c:when> 
                                        <c:otherwise>
                                            <tr>
                                                <td><label>Hasil :</label>
                                                <td><c:if test="${actionBean.hakmilikPermohonan.keteranganCukaiBaru ne null}"> ${actionBean.hakmilikPermohonan.keteranganCukaiBaru}&nbsp; * bagi setiap 100 mp</c:if>
                                                    <c:if test="${actionBean.hakmilikPermohonan.keteranganCukaiBaru eq null}"> Tiada Data </c:if>
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>

                                <c:if test = "${actionBean.hakmilikPermohonan.kategoriTanahBaru.kod eq null}">
                                    <c:choose>
                                        <c:when test = "${actionBean.hakmilik.kategoriTanah.kod eq '1'}"> 
                                            <tr>
                                                <td><label>Hasil :</label>
                                                <td><c:if test="${actionBean.hakmilikPermohonan.keteranganCukaiBaru ne null}"> ${actionBean.hakmilikPermohonan.keteranganCukaiBaru}&nbsp; * bagi setiap 1 ha </c:if>
                                                    <c:if test="${actionBean.hakmilikPermohonan.keteranganCukaiBaru eq null}"> Tiada Data </c:if>
                                            </tr>
                                        </c:when>                                                
                                        <c:otherwise>
                                            <tr>
                                                <td><label>Hasil :</label>
                                                <td><c:if test="${actionBean.hakmilikPermohonan.keteranganCukaiBaru ne null}"> ${actionBean.hakmilikPermohonan.keteranganCukaiBaru}&nbsp; * bagi setiap 100 mp</c:if>
                                                    <c:if test="${actionBean.hakmilikPermohonan.keteranganCukaiBaru eq null}"> Tiada Data </c:if>
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>

                                <c:choose>
                                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'TSPSS' || actionBean.permohonan.kodUrusan.kod eq 'SBMS'|| actionBean.permohonan.kodUrusan.kod eq 'TSKSN'|| actionBean.permohonan.kodUrusan.kod eq 'TSPSN'}">                            
                                        <tr>
                                            <td><label>Syarat Lama/Sekatan Milik Lama dihapuskan dan digantikan dengan Syarat Nyata :</label>
                                            <td><c:if test="${actionBean.hakmilikPermohonan.syaratNyataBaru ne null}">${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}</c:if>
                                                <c:if test="${actionBean.hakmilikPermohonan.syaratNyataBaru eq null}"> Tiada Data </c:if>
                                                &nbsp;
                                        </tr>                            
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td ><label>Syarat Lama/Sekatan Milik Lama dihapuskan dan digantikan dengan Syarat Nyata :</label>
                                            <td><c:if test="${actionBean.hakmilik.syaratNyata.syarat ne null}">${actionBean.hakmilik.syaratNyata.syarat}</c:if>
                                                <c:if test="${actionBean.hakmilik.syaratNyata.syarat eq null}"> Tiada Data </c:if> &nbsp;
                                        </tr>                            
                                    </c:otherwise>
                                </c:choose>                        
                                <tr>
                                    <td valign="top"><label>Sekatan Milik :</label>
                                    <td><c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan ne null}">${actionBean.hakmilik.sekatanKepentingan.sekatan}</c:if>
                                        <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan eq null}"> Tiada Data </c:if>
                                </tr>                              
                                <tr>
                                    <td><label>Dikenakan Penjenisan :</label>
                                    <td><c:if test="${actionBean.hakmilikPermohonan.kategoriTanahBaru.nama ne null}">${actionBean.hakmilikPermohonan.kategoriTanahBaru.nama}</c:if>
                                        <c:if test="${actionBean.hakmilikPermohonan.kategoriTanahBaru.nama eq null}"> ${actionBean.hakmilik.kategoriTanah.nama} </c:if>                   
                                </tr>
                                <tr>
                                    <td><label>Sumbangan Saliran :</label>
                                    <td><c:if test="${actionBean.hakmilikPermohonan.keteranganInfra ne null}"> ${actionBean.hakmilikPermohonan.keteranganInfra}&nbsp; </c:if>
                                        <c:if test="${actionBean.hakmilikPermohonan.keteranganInfra eq null}"> Tiada Data </c:if>
                                </tr>
                                <tr>
                                    <td><label>Semua bayaran dijelaskan dalam tempoh :</label></td>
                                    <td><b>3 bulan selepas tarikh kelulusan.</b> </td>
                                </tr>                              
                                &nbsp;

                            </table>
                        </div>
                    </c:if>
                    <c:if test="${actionBean.laporanTanah.syor eq 'T'}">
                        <div class="content" id="perakuanSyorUlasan">                          
                            <p>
                                <label>Ulasan :</label>
                                <c:if test="${actionBean.permohonanLaporanUlasan.ulasan ne null}"> ${actionBean.permohonanLaporanUlasan.ulasan}&nbsp; </c:if>
                                <c:if test="${actionBean.permohonanLaporanUlasan.ulasan eq null}"> Tiada Data </c:if>
                            </p>
                        </div>
                    </c:if>
                    <br>
                </c:if>
            </fieldset>
        </div>
    </c:if>
    <br/>

    <%-- added for SBMS & TSPSS ONLY--%>
    <c:if test = "${actionBean.permohonan.kodUrusan.kod eq 'SBMS' || actionBean.permohonan.kodUrusan.kod eq 'TSPSS'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Perakuan/Syor           
                </legend>

                <c:if test = "${actionBean.stageId eq 'laporantanah'}">
                    <p>
                        <label>Jika diperakukan :-</label>
                        <s:radio name="laporanTanah.syor" value="Y" onclick="javaScript:showUlasan1(this.value)" id="syorYa"/>&nbsp;Ya
                        <s:radio name="laporanTanah.syor" value="T" onclick="javaScript:showUlasan1(this.value)" id="syorTidak"/>&nbsp;Tidak

                    </p>
                </c:if>
                <c:if test = "${actionBean.stageId ne 'laporantanah'}">
                    <p>
                        <label>Jika diperakukan :</label>
                        <c:if test = "${actionBean.laporanTanah.syor eq 'Y'}"> Ya </c:if>
                        <c:if test = "${actionBean.laporanTanah.syor eq 'T'}"> Tidak </c:if>

                    </p>
                </c:if>
                <br/>
                
                <div class="content" id="perakuanSyor1">               
                    <div align ="center">
                        <div id="popupDiv">
                            <display:table class="tablecloth" name="${actionBean.listHakmilik}" cellpadding="0" requestURI="/pembangunan/melaka/laporanTanah"
                                           cellspacing="0" id="line">
                                <display:column title="Bil" style="vertical-align:baseline">${line_rowNum}
                                    <s:hidden name="x" class="x${line_rowNum -1}" value="${line.idPlot}"/>
                                </display:column>                                                                  
                                <display:column title="Kegunaan Tanah" style="vertical-align:baseline">
                                    <c:if test = "${line.kegunaanTanah eq null}"> - &nbsp; </c:if>
                                    <c:if test = "${line.kegunaanTanah ne null}"> ${line.kegunaanTanah.nama}&nbsp; </c:if>                         
                                </display:column>
                                <display:column title="Butir Pembangunan" style="vertical-align:baseline">
                                    <c:if test = "${line.catatan eq null && line.kegunaanTanahLain eq null}"> - &nbsp; </c:if>
                                    <c:if test = "${line.catatan ne null && line.kegunaanTanahLain eq null}"> ${line.catatan}&nbsp; </c:if> 
                                    <c:if test = "${line.catatan eq null && line.kegunaanTanahLain ne null}"> ${line.kegunaanTanahLain}&nbsp; </c:if>
                                </display:column>
                                <display:column title="Premium Tambahan" style="vertical-align:baseline">
                                    <c:if test = "${line.keteranganKadarPremium eq null}"> - &nbsp;</c:if>
                                    <c:if test = "${line.keteranganKadarPremium ne null}"> ${line.keteranganKadarPremium} &nbsp;</c:if>    
                                </display:column>
                                <display:column title="Premium Denda" style="vertical-align:baseline">
                                    <c:if test = "${line.keteranganKadarDaftar eq null}"> - &nbsp;</c:if>
                                    <c:if test = "${line.keteranganKadarDaftar eq '0'}"> Tiada &nbsp;</c:if> 
                                    <c:if test = "${line.keteranganKadarDaftar eq '1'}"> 1x Nilai Premium &nbsp;</c:if>
                                    <c:if test = "${line.keteranganKadarDaftar eq '2'}"> 2x Nilai Premium &nbsp;</c:if> 
                                </display:column> 
                                <display:column title="Hasil" style="vertical-align:baseline">
                                    <c:if test = "${line.keteranganCukaiBaru eq null}"> - &nbsp;</c:if>
                                    <c:if test = "${line.keteranganCukaiBaru ne null}"> ${line.keteranganCukaiBaru} &nbsp;</c:if>   
                                </display:column> 
                                <display:column title="Dikenakan Penjenisan" style="vertical-align:baseline">
                                    <c:if test = "${line.kategoriTanah eq null}"> - &nbsp;</c:if>
                                    <c:if test = "${line.kategoriTanah ne null}"> ${line.kategoriTanah.nama} &nbsp;</c:if>   
                                </display:column>
                                <display:column title="Sumbangan Saliran" style="vertical-align:baseline">
                                    <c:if test = "${line.kosInfra eq null}"> - &nbsp;</c:if>
                                    <c:if test = "${line.kosInfra ne null}"> ${line.kosInfra} &nbsp;</c:if>   
                                </display:column>

                                <c:if test = "${actionBean.stageId ne 'laporantanah'}">
                                    <display:column title="Syarat Nyata" style="vertical-align:baseline">
                                        <c:if test = "${line.kodSyaratNyata eq null}"> - &nbsp;</c:if>
                                        <c:if test = "${line.kodSyaratNyata ne null}"> ${line.kodSyaratNyata.syarat} &nbsp;</c:if>  
                                    </display:column>   
                                    <display:column title="Sekatan Milik" style="vertical-align:baseline">
                                        <c:if test = "${line.kodSekatanKepentingan eq null}"> - &nbsp;</c:if>
                                        <c:if test = "${line.kodSekatanKepentingan ne null}"> ${line.kodSekatanKepentingan.sekatan} &nbsp;</c:if>  
                                    </display:column>   
                                </c:if>

                                <c:if test = "${actionBean.stageId eq 'laporantanah'}">
                                    <display:column title="Kemaskini Nilai">
                                        <div align="center">                                      
                                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="dopopup('${line.idPlot}');return false;"  onmouseover="this.style.cursor='pointer';">                                                                                    
                                        </div>
                                    </display:column>                                                                                                     
                                    <display:column title="Kemaskini Syarat Nyata">
                                        <div align="center">                                          
                                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="doNyata('${line.idPlot}','NY');return false;"  onmouseover="this.style.cursor='pointer';">                                                                                 
                                        </div>
                                    </display:column>  
                                    <display:column title="Kemaskini Sekatan Milik">
                                        <div align="center">                                           
                                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="doSekatan('${line.idPlot}','SK');return false;"  onmouseover="this.style.cursor='pointer';">                                                                              
                                        </div>
                                    </display:column> 
                                </c:if>
                            </display:table>  
                        </div>
                    </div>
                    <br/>
                    <div align="center">
                        <td>Semua bayaran dijelaskan dalam tempoh: 3 bulan selepas tarikh kelulusan.</td>                                                
                    </div>
                </div>                

                <c:if test = "${actionBean.stageId eq 'laporantanah'}">
                    <div id="perakuanSyorUlasan1">
                        <p>
                            <label>Ulasan :</label>
                            <s:textarea name="permohonanLaporanUlasan.ulasan" cols="50"/>
                        </p>
                    </div> 
                </c:if>
            </fieldset>
        </div>                            
    </c:if>
    <br/>

    <c:if test="${actionBean.kodNegeri eq '05'}">
        <div class="subtitle displaytag content" align="center">

            <c:if test="${actionBean.stageId eq 'laporantanah'}">
                <fieldset class="aras1">
                    <legend>Ulasan Penolong Pegawai Tanah</legend>
                    <br>
                    <c:if test="${edit}">
                        <p><s:hidden name="fasaPermohonan.idFasa"/>
                        <table border="0" width="50%" align="center">
                            <tr>
                                <td>:</td>
                                <td><s:textarea name="fasaPermohonan.ulasan" cols="70" rows="5"/></td>
                            </tr>
                        </table>
                        </p><br>
                    </c:if>
                </fieldset>
                <br>
                <c:if test="${actionBean.fasaPermohonan2.ulasan ne null}">
                    <fieldset class="aras1">
                        <legend>Ulasan Penolong Pegawai Tanah Kanan</legend>
                        <p>
                        <table border="0" width="50%" align="center">
                            <tr>
                                <td>:</td>
                                <td>${actionBean.fasaPermohonan2.ulasan}</td>
                            </tr>
                        </table>
                        </p><br>
                    </fieldset>
                </c:if>
            </c:if>

            <c:if test="${actionBean.stageId eq 'semaklaporantanah' || actionBean.stageId eq 'semakderafperakuan' || actionBean.stageId eq 'perakuanmmknptd'
                          || actionBean.stageId eq 'ulasanadunteksediajkbb' || actionBean.stageId eq 'derafperakuanjkbbptd' || actionBean.stageId eq 'perakuanjkbbptd'}">
                  <fieldset class="aras1">
                      <legend>Ulasan Penolong Pegawai Tanah</legend>
                      <br><p>
                      <table border="0" width="50%" align="center">
                          <tr>
                              <td>:</td>
                              <td>${actionBean.fasaPermohonan.ulasan}</td>
                          </tr>
                      </table>                       
                      </p>
                  </fieldset>
                  <br>
                  <fieldset class="aras1">
                      <legend>Ulasan Penolong Pegawai Tanah Kanan</legend>
                      <c:if test="${edit}">
                          <p><s:hidden name="fasaPermohonan2.idFasa"/>
                          <table border="0" width="50%" align="center">
                              <tr>
                                  <td>:</td>
                                  <td><s:textarea name="fasaPermohonan2.ulasan" cols="70" rows="5"/></td>
                              </tr>
                          </table>
                          </p>
                      </c:if>
                  </fieldset>
            </c:if>

        </div>
        <br>
    </c:if>
    <c:if test="${actionBean.kodNegeri eq '04'}">
        <div class="subtitle displaytag" align="center">
            <fieldset class="aras1">
                <legend>Log Ulasan</legend>
                <display:table class="tablecloth" name="${actionBean.listNota}" pagesize="10" cellpadding="0" cellspacing="0"
                               requestURI="/pembangunan/melaka/laporanTanah"  id="line">

                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column title="Peringkat">
                        <c:if test="${line.idAliran eq 'laporantanah'}">Sedia Laporan Tanah</c:if>
                        <c:if test="${line.idAliran eq 'semaklaporantanah'}">Semak Laporan Tanah</c:if>
                        <c:if test="${line.idAliran eq 'semakderafperakuan'}">Semak Deraf Perakuan</c:if>
                        <c:if test="${line.idAliran eq 'perakuanmmknptd'}">Perakuan MMKN PTD</c:if>
                        <c:if test="${line.idAliran eq 'ulasanadunteksediajkbb'}">Sedia Deraf Rencana JKBB</c:if>
                        <c:if test="${line.idAliran eq 'derafperakuanjkbbptd'}">Semak Deraf Rencana JKBB</c:if>
                        <c:if test="${line.idAliran eq 'perakuanjkbbptd'}">Perakuan PTD</c:if>
                    </display:column>
                    <display:column title="Ulasan" property="nota"></display:column>
                    <display:column title="Tarikh Dihantar" property="infoAudit.tarikhMasuk" ></display:column>

                    <display:column title="Kemaskini">
                        <c:if test="${(actionBean.stageId eq 'semaklaporantanah' && line.idAliran eq 'semaklaporantanah')  || (actionBean.stageId eq 'laporantanah')}">     
                            <div align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                     id='rem_${line_rowNum}' onclick="editPermohonanNota('${line.idMohonNota}');return false;">
                            </div>
                        </c:if>                                
                    </display:column>
                    <display:column title="Hapus">
                        <c:if test="${(actionBean.stageId eq 'semaklaporantanah' && line.idAliran eq 'semaklaporantanah')  || (actionBean.stageId eq 'laporantanah')}">     
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onclick="removePermohonanNota('${line.idMohonNota}');return false;">
                            </div>
                        </c:if>                            
                    </display:column>

                </display:table>
                <c:if test="${actionBean.stageId eq 'semaklaporantanah' || actionBean.stageId eq 'laporantanah'}">
                    <s:button class="btn" value="Rekod Ulasan" name="simpanLaporanTanah" id="" onclick="rekodUlasan(this.form);"/>
                </c:if>
                <br>
                <p>
            </fieldset>
        </div>
        <br>
    </c:if>
    <br>
    <div class="subtitle" align="center">
        <fieldset class="aras1">
            <c:if test="${actionBean.stageId eq 'laporantanah'}">
                <p>
                    <s:button name="simpanLaporanTanah" id="save2" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    <s:button name="limGis" id="lim" value="LIM" class="btn" onclick="RunProgram('${actionBean.pengguna.idPengguna}','${actionBean.pengguna.nama}','${actionBean.pengguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>
                </p>
            </c:if>
            <c:if test="${actionBean.stageId eq 'semaklaporantanah' && actionBean.kodNegeri eq '05'}">
                <p>
                    <s:button name="simpanLaporanTanah2" id="save3" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </c:if>
            <c:if test="${actionBean.stageId eq 'semakderafperakuan' || actionBean.stageId eq 'perakuanmmknptd'}">
                <p>
                    <s:button name="simpanLaporanTanah2" id="save3" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </c:if>
        </fieldset>
    </div>
</s:form>

