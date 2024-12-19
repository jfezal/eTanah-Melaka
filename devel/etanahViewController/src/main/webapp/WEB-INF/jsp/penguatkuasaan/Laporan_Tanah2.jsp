<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    $(document).ready(function() {
        var len = $(".daerah").length;

        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                window.open("${pageContext.request.contextPath}/hakmilik?hakmilikDetail&idHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }

        if($('#adaBangunan').val() == 'Y'){
            $('#bilanganBangunan').show();
            $('#bilanganBangunan2').show();
            $('#bilanganBangunan3').show();
            $('#bilanganBangunan4').show();
        }

        if($('#diUsahakan').val() == 'Y'){
            $('#usaha1').show();
            $('#usaha2').show();
            $('#usaha3').show();
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
        else {
            $('#tinggi').hide();
            $('#cerun').hide();
            $('#dalam').hide();
        }

        $('#jenisRizab').hide();
        $('#noWarta').hide();

        $('#nyataRancangan').hide();
        $('#tanahMilik').hide();
        $('#tujuan').hide();

        $('#catatanTanahMilik').hide();
        $('#catatanPBT').hide();
        
    <c:if test="${actionBean.maklumatTanahSek49 eq false}">
            var status = $('#statusTanah').val();
            //        alert("status :"+status);
            if(status == "H"){
                document.getElementById("tanahHakmilik").checked = true;
            }else if(status == "K"){
                document.getElementById("tanahKerajaan").checked = true;
            }else{
                status = "H";
                document.getElementById("tanahHakmilik").checked = true;
            }
        
            changeStatusTanah(status);
    </c:if>
        


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

            else if(text == "Lain-lain"){
                $('#tinggi').hide();
                $('#cerun').hide();
                $('#dalam').hide();
            }

        }

        function validateForm(){
            
    <c:if test="${actionBean.maklumatTanahSek49 eq false}">
            if(document.getElementById("tanahHakmilik").checked == true){
                if($('#jumlahPermohonanHakmilik').val() == '0'){
                    alert("Sila masukkan id hakmilik");
                    $('#idHakmilik').focus();
                    return false; 
                }
            }
    </c:if>
        
            if(document.form.tarikhLawatan.value!="")
            {
                if(document.form.jam.value==""){
                    alert("Sila masukkan jam");
                    $('#jam').focus();
                    return false;
                }
                if(document.form.minit.value==""){
                    alert("Sila masukkan minit");
                    $('#minit').focus();
                    return false;
                }
                if(document.form.ampm.value==""){
                    alert("Sila masukkan pagi/petang");
                    $('#ampm').focus();
                    return false;
                }

            }

            if(document.form.jam.value!="" && document.form.tarikhLawatan.value==""){
                alert("Sila masukkan tarikh lawatan");
                $('#tarikhLawatan').focus();
                return false;
            }

            if(document.form.jam.value!="" && document.form.jam.value >12){
                alert("Sila masukkan nilai dari 1-12 sahaja");
                $('#jam').focus();
                return false;
            }

            if(document.form.minit.value!="" && document.form.minit.value >59){
                alert("Sila masukkan nilai dari 00-59 sahaja");
                $('#minit').focus();
                return false;
            }
            
    <c:if test="${actionBean.statusUlasan eq false}">
            if(document.form.ulasanPPT.value==""){
                alert("Sila masukkan ulasan terlebih dahulu");
                $('#ulasanPPT').focus();
                return false;
            }
    </c:if>
           
            
            
            return true;
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
        function refreshRizab(){
            var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah2?refreshpage';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
        function removeSingle(idTanahRizabPermohonan)
        {
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah2?deleteSingle&idTanahRizabPermohonan='
                    +idTanahRizabPermohonan;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');}
        }

        function tambahBaru(){
            window.open("${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah2?hakMilikPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

        function showBilBangunan() {
            $('#bilanganBangunan').show();
            $('#bilanganBangunan2').show();
            $('#bilanganBangunan3').show();
            $('#bilanganBangunan4').show();
        }
        function hideBilBangunan() {
            $('#bilanganBangunan').hide();
            $('#bilanganBangunan2').hide();
            $('#bilanganBangunan3').hide();
            $('#bilanganBangunan4').hide();
            $('#bangunanTahunDibina').val("");
        }

        function showUsaha() {
            $('#usaha1').show();
            $('#usaha2').show();
            $('#usaha3').show();
        }

        function hideUsaha() {
            $('#usaha1').hide();
            $('#usaha2').hide();
            $('#usaha3').hide();
    <%--   $('#diusaha').val("");
       $('#datepicker').val("");
       $('#usahaTanam').val("");--%>
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

           function refreshPage(){
               var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah2?refreshpage';
               $.get(url,
               function(data){
                   $('#page_div').html(data);
               },'html');
           }

           function muatNaikForm1(folderId, dokumenId, idPermohonan, dokumenKod, idLaporan, idHakmilik) {
               var left = (screen.width/2)-(1000/2);
               var top = (screen.height/2)-(150/2);
               var url = '${pageContext.request.contextPath}/penguatkuasaan/upload?muatNaikForm1&folderId=' + folderId + '&dokumenId='
                   + dokumenId + '&idPermohonan=' + idPermohonan + '&dokumenKod=' + dokumenKod + '&idLaporan=' + idLaporan + '&idHakmilik=' + idHakmilik;
               window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
           }

           function muatNaikForm(folderId, dokumenId, idPermohonan, dokumenKod, idLaporan, idHakmilik, idRujukan) {
               var left = (screen.width/2)-(1000/2);
               var top = (screen.height/2)-(150/2);
               var url = '${pageContext.request.contextPath}/penguatkuasaan/upload_action?popupUpload&folderId=' + folderId + '&dokumenId='
                   + dokumenId + '&idPermohonan=' + idPermohonan + '&dokumenKod=' + dokumenKod + '&idLaporan=' + idLaporan + '&idHakmilik=' + idHakmilik + '&idRujukan=' + idRujukan;
               window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
           }

           function doViewReport(v) {
               var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
               window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
           }

           function removeImej(idImej, idDokumen) {
               if(confirm('Adakah anda pasti untuk hapus?')) {
                   var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah2?deleteSelected&idImej='+idImej+'&idDokumen='+idDokumen;
                   $.get(url,
                   function(data){
                       $("#DocHakmilikDiv").replaceWith($('#DocHakmilikDiv', $(data)));
                       $("#tanahMilikDiv").replaceWith($('#tanahMilikDiv', $(data)));
                       $("#TanahHakmilikDiv").replaceWith($('#TanahHakmilikDiv', $(data)));
                   },'html');
               }
           }

           function removeImejTanahSekeliling(idImej, idDokumen, i) {
               if(confirm('Adakah anda pasti untuk hapus?')) {
                   var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah2?deleteSelected&idImej='+idImej+'&idDokumen='+idDokumen;
                   $.get(url,
                   function(data){
                       if(i == 'IHU'){
                           $("#IHUDiv").replaceWith($('#IHUDiv', $(data)));
                       }else if(i == 'IHS'){
                           $("#IHSDiv").replaceWith($('#IHSDiv', $(data)));
                       }else if(i == 'IHT'){
                           $("#IHTDiv").replaceWith($('#IHTDiv', $(data)));
                       }else if(i == 'IHB'){
                           $("#IHBDiv").replaceWith($('#IHBDiv', $(data)));
                       }
                   },'html');
               }
           }

           function removeImejTanahSekelilingList(idImej, idDokumen, i) {
               if(confirm('Adakah anda pasti untuk hapus?')) {
                   var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah2?deleteSelected&idImej='+idImej+'&idDokumen='+idDokumen;
                   $.get(url,
                   function(data){
                       $("#senaraiSempadan").replaceWith($('#senaraiSempadan', $(data)));
                   },'html');
               }
           }

           function limitText(limitField, limitNum) {
               if (limitField.value.length > limitNum) {
                   limitField.value = limitField.value.substring(0, limitNum);
               }
           }

           function refreshListHakmilik(){
               var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah2?refreshpage';
               $.get(url,
               function(data){
                   $("#DocHakmilikDiv").replaceWith($('#DocHakmilikDiv', $(data)));
                   $("#infoLaporanTanahDiv").replaceWith($('#infoLaporanTanahDiv', $(data)));
                   $("#tanahMilikDiv").replaceWith($('#tanahMilikDiv', $(data)));
                   $("#TanahHakmilikDiv").replaceWith($('#TanahHakmilikDiv', $(data)));
               },'html');
           }

           function refreshListBangunan(){
               var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah2?refreshpage';
               $.get(url,
               function(data){
                   $("#senaraiBangunan").replaceWith($('#senaraiBangunan', $(data)));
               },'html');

           }

           function refreshListSempadan(){
               var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah2?refreshpage';
               $.get(url,
               function(data){
                   $("#senaraiSempadan").replaceWith($('#senaraiSempadan', $(data)));
                   $("#infoLaporanTanahDiv").replaceWith($('#infoLaporanTanahDiv', $(data)));
               },'html');

           }

           function refreshTanahSekeliling(i){
               var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah2?refreshpage';
               $.get(url,
               function(data){
                   if(i == 'IHU'){
                       $("#IHUDiv").replaceWith($('#IHUDiv', $(data)));
                   }else if(i == 'IHS'){
                       $("#IHSDiv").replaceWith($('#IHSDiv', $(data)));
                   }else if(i == 'IHT'){
                       $("#IHTDiv").replaceWith($('#IHTDiv', $(data)));
                   }else if(i == 'IHB'){
                       $("#IHBDiv").replaceWith($('#IHBDiv', $(data)));
                   }
               },'html');
           }

           function simpan(){
               var idHakmilik = $('#idHakmilik').val();
               if(idHakmilik == ""){
                   alert("Sila masukkan id hakmilik");
                   $('#idHakmilik').focus();
               }else{
                   var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah2?carianHakmilik&idHakmilik='+idHakmilik;
                   $.get(url,
                   function(data){
                       //alert(data);
                       $("#maklumatHakmilikDiv").replaceWith($('#maklumatHakmilikDiv', $(data)));
                       $("#TanahHakmilikDiv").replaceWith($('#TanahHakmilikDiv', $(data)));
                       $("#tanahMilikDiv").replaceWith($('#tanahMilikDiv', $(data)));
                       $("#otherInfoHakmilik").replaceWith($('#otherInfoHakmilik', $(data)));
                       $("#kodDunHakmilikPermohonanDiv").replaceWith($('#kodDunHakmilikPermohonanDiv', $(data)));
                       $("#infoLaporanTanahDiv").replaceWith($('#infoLaporanTanahDiv', $(data)));
                       $('#tanahMilikDiv').hide();
                       

                       //$('#page_div').html(data);
                   },'html');
               }
           }
               
           function tambahBaruBangunan(idLapor){
               var left = (screen.width/2)-(1000/2);
               var top = (screen.height/6)-(150/6);
               window.open("${pageContext.request.contextPath}/penguatkuasaan/jenis_bangunan?bangunanPopup&idLapor=" +idLapor, "eTanah",
               "status=0,toolbar=0,location=0,menubar=0,width=900,height=400,left=" + left + ",top=" + top);
           }
           function popupBangunan(h){
               var left = (screen.width/2)-(1000/2);
               var top = (screen.height/6)-(150/6);
               var url = '${pageContext.request.contextPath}/penguatkuasaan/jenis_bangunan?editBangunan&idLaporBangunan='+h;
               window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=400,left=" + left + ",top=" + top);
           }

           function viewJenisBangunan(id){
               var url = '${pageContext.request.contextPath}/penguatkuasaan/jenis_bangunan?jenisBangunanDetails&idLaporBangunan='+id;
               window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=400,scrollbars=yes");
           }

           function viewTanahSekeliling(id){
               var url = '${pageContext.request.contextPath}/penguatkuasaan/jenis_sempadan?tanahSekelilingDetails&idLaporTanahSpdn='+id;
               window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=400,scrollbars=yes");
           }

           function removeBangunan(idLaporBangunan){
               if(confirm('Adakah anda pasti untuk hapus?')) {
                   var url = '${pageContext.request.contextPath}/penguatkuasaan/jenis_bangunan?deleteBangunan&idLaporBangunan='+idLaporBangunan;
                   $.get(url,
                   function(data){
                       $("#senaraiBangunan").replaceWith($('#senaraiBangunan', $(data)));
                   },'html');}
           }

           function tambahBaruSempadan(idLapor){
               var left = (screen.width/2)-(1000/2);
               var top = (screen.height/6)-(150/6);
               //var idLapor = $('#idLaporanTanah').val();
               window.open("${pageContext.request.contextPath}/penguatkuasaan/jenis_sempadan?sempadanPopup&idLapor="+idLapor+"&jenisLaporan=L2TH", "eTanah",
               "status=0,toolbar=0,location=0,menubar=0,width=900,height=500,left=" + left + ",top=" + top);
           }

           function popupSempadan(h){
               var left = (screen.width/2)-(1000/2);
               var top = (screen.height/6)-(150/6);
               var url = '${pageContext.request.contextPath}/penguatkuasaan/jenis_sempadan?editSempadan&idLaporTanahSpdn='+h;
               window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=500,left=" + left + ",top=" + top);
           }

           function removeSempadan(idLaporTanahSpdn){
               if(confirm('Adakah anda pasti untuk hapus?')) {
                   var url = '${pageContext.request.contextPath}/penguatkuasaan/jenis_sempadan?deleteSempadan&idLaporTanahSpdn='+idLaporTanahSpdn;
                   $.get(url,
                   function(data){
                       $("#senaraiSempadan").replaceWith($('#senaraiSempadan', $(data)));
                   },'html');}
           }

           function addRecord(){
               var left = (screen.width/2)-(1000/2);
               var top = (screen.height/4)-(150/4);
               var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah2?addRecordHakmilik';
               window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=900,height=500, left=" + left + ",top=" + top);
           }

           function removeRecord(id){
               if(confirm('Adakah anda pasti untuk hapus?')) {
                   var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah2?deleteRecordHakmilik&id='+id;
                   $.get(url,
                   function(data){
                       $("#tanahMilikDiv").replaceWith($('#tanahMilikDiv', $(data)));
                   },'html');}
           }

           function editRecord(id){
               var left = (screen.width/2)-(1000/2);
               var top = (screen.height/4)-(150/2);
               var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah2?editRecordHakmilik&id='+id;
               window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=900,height=500, left=" + left + ",top=" + top);
           }

           function refreshTanahMilik(){
               var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah2?refreshpage';
               $.get(url,
               function(data){
                   $("#tanahMilikDiv").replaceWith($('#tanahMilikDiv', $(data)));
                   $("#infoLaporanTanahDiv").replaceWith($('#infoLaporanTanahDiv', $(data)));
                   $("#maklumatHakmilikDiv").replaceWith($('#maklumatHakmilikDiv', $(data)));
                   $('#maklumatHakmilikDiv').hide();
               },'html');
           }
           
           
           function changeStatusTanah(value){
               //alert("val :"+value);
               if(value == "H"){
                   //if have id hakmilik
                   $('#tanahMilikDiv').hide();
                   $('#maklumatHakmilikDiv').show();
               }else if(value == "K"){
                   //if dont have id hakmilik
                   $('#tanahMilikDiv').show();
                   $('#maklumatHakmilikDiv').hide();
                   $("#kodDunHakmilikPermohonanDiv").hide();
               }
               
           }
           
           function addJenisHakisan(){
               var left = (screen.width/2)-(1000/2);
               var top = (screen.height/4)-(150/4);
               var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah2?addRecordJenisHakisan';
               window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=900,height=500, left=" + left + ",top=" + top);
           }
    
           function editJenisHakisan(id){
               var left = (screen.width/2)-(1000/2);
               var top = (screen.height/4)-(150/4);
               var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah2?editRecordJenisHakisan&id='+id;
               window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=900,height=500, left=" + left + ",top=" + top);
           }
    
           function removeJenisHakisan(id){
               if(confirm('Adakah anda pasti untuk hapus?')) {
                   var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah2?deleteJenisHakisan&id='+id;
                   $.get(url,
                   function(data){
                       $("#maklumatTanah49Div").replaceWith($('#maklumatTanah49Div', $(data)));
                   },'html');}
           }
           
           function refreshPageHakisan(){
               var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah2?refreshpage';
               $.get(url,
               function(data){
                   $("#maklumatTanah49Div").replaceWith($('#maklumatTanah49Div', $(data)));
               },'html');
           }
          
           function viewJenisHakisan(id){
               var left = (screen.width/2)-(1000/2);
               var top = (screen.height/4)-(150/4);
               var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah2?viewRecordJenisHakisan&idMH='+id;
               window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=900,height=500, left=" + left + ",top=" + top);
           }
           
           //           function addMultipleHakmilik(){
           //               alert("addMultipleHakmilik");
           //               var left = (screen.width/2)-(1000/2);
           //               var top = (screen.height/4)-(150/4);
           //               var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah2?addHakmilik';
           //               window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=900,height=500, left=" + left + ",top=" + top);
           //           }

           
</script>
<script type="text/javascript">
    function addInfoHakmilik(){
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/4)-(150/4);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah2?addHakmilik';
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=900,height=500, left=" + left + ",top=" + top);
    }
    
    function removeHakmilik(id){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah2?deleteJenisHakisan&id='+id;
            $.get(url,
            function(data){
                $("#multipleHakmilikDiv").replaceWith($('#multipleHakmilikDiv', $(data)));
                $("#TanahHakmilikDiv").replaceWith($('#TanahHakmilikDiv', $(data)));
                $("#infoLaporanTanahDiv").replaceWith($('#infoLaporanTanahDiv', $(data)));
            },'html');}
    }
    
    function refreshPageAddHakmilik(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah2?refreshpage';
        $.get(url,
        function(data){
            $("#multipleHakmilikDiv").replaceWith($('#multipleHakmilikDiv', $(data)));
            $("#TanahHakmilikDiv").replaceWith($('#TanahHakmilikDiv', $(data)));
//            $("#tanahMilikDiv").replaceWith($('#tanahMilikDiv', $(data)));
            $("#infoLaporanTanahDiv").replaceWith($('#infoLaporanTanahDiv', $(data)));
        },'html');
    }
</script>

<s:form beanclass="etanah.view.penguatkuasaan.LaporanTanah2ActionBean" name="form">
    <s:hidden name="permohonan.idPermohonan" />
    <s:hidden name="statusTanah" id="statusTanah"/>
    <s:hidden name="jenisLaporan" value="${actionBean.jenisLaporan}"/>
    <div id="infoLaporanTanahDiv">
        <s:hidden name="laporanTanah.idLaporan" id="idLaporanTanah"/>
        <s:hidden name="laporanTanah.permohonan.idPermohonan"/>
        <input type="hidden" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="jumlahPermohonanHakmilik">
        <s:messages/>
        <s:errors/>
    </div>
    <c:if test="${!edit && actionBean.maklumatTanahSek49 eq false}">
        <p>
            <label>Status Tanah : </label>
            <input type="radio" name="statusTanah" id="tanahKerajaan" value="K" onclick="changeStatusTanah('K');" disabled="true" />Tanah Kerajaan/Rezab
            <input type="radio" name="statusTanah" id="tanahHakmilik" value="H" onclick="changeStatusTanah('H');" disabled="true" />Tanah Milik
        </p>
    </c:if>
    <c:if test="${edit && actionBean.maklumatTanahSek49 eq false}">
        <p> 
            <label>Status Tanah : </label>
            <input type="radio" name="statusTanah" id="tanahKerajaan" value="K" onclick="changeStatusTanah('K');" />Tanah Kerajaan/Rezab
            <input type="radio" name="statusTanah" id="tanahHakmilik" value="H" onclick="changeStatusTanah('H');"/>Tanah Milik
        </p>
    </c:if>


    <c:if test="${actionBean.maklumatTanahSek49 eq false}">
        <div id="maklumatHakmilikDiv">
            <c:if test="${edit && actionBean.multipleHakmilik eq false}">
                <div class="subtitle">
                    <fieldset class="aras1">
                        <legend>Carian Hakmilik</legend>
                        <p>
                            <label for="ID Hakmilik">ID Hakmilik :</label>
                            <s:text name="idHakmilik" id="idHakmilik" size="25"/>
                            <s:button class="btn" value="Cari" name="new" id="new" onclick="simpan();"/>
                        </p>
                    </fieldset>
                </div>
            </c:if>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Hakmilik
                    </legend>
                    <div id="multipleHakmilikDiv">
                        <div class="content" align="center">
                            <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0"
                                           id="line">
                                <display:column title="Bil" sortable="true" style="vertical-align:baseline">
                                    <c:if test="${line.hakmilik.idHakmilik eq null}">
                                        Tiada rekod
                                    </c:if>
                                    <c:if test="${line.hakmilik.idHakmilik ne null}">
                                        ${line_rowNum}
                                    </c:if>
                                </display:column>
                                <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}" style="vertical-align:baseline"/>
                                <display:column property="hakmilik.noLot" title="Nombor Lot/PT" style="vertical-align:baseline"/>
                                <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                                <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah" style="vertical-align:baseline"/>
                                <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" style="vertical-align:baseline"/>
                                <display:column property="hakmilik.maklumatAtasTanah" title="Jenis Penggunaan Tanah" />
                                <c:if test="${edit && actionBean.multipleHakmilik eq true}">
                                    <display:column title="Hapus">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeHakmilik('${line.id}');"/>
                                        </div>
                                    </display:column>  
                                </c:if>
                            </display:table>
                            <c:if test="${edit && actionBean.multipleHakmilik eq true}">
                                <p align="center">
                                    <s:button name="addHakmilik" value="Tambah" class="btn" onclick="addInfoHakmilik();"/>
                                </p>
                            </c:if>
                            <br>

                            Senarai Pemilik  <br>
                            <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" id="line">
                                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                <display:column title="No. Hakmilik" >${line.hakmilik.kodHakmilik.kod}${line.hakmilik.noHakmilik}</display:column>
                                <display:column title="Nama">
                                    <c:set value="1" var="count"/>
                                    <c:forEach items="${actionBean.pihakBerkepentinganList}" var="senarai">
                                        <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                            <c:out value="${count}"/>)&nbsp;
                                            <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;<br>
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.KP/Co :&nbsp;<c:out value="${senarai.pihak.noPengenalan}"/>
                                            <c:set value="${count + 1}" var="count"/>
                                            <br>
                                        </c:if>
                                    </c:forEach>
                                </display:column>
                                <display:column title="Jenis Pihak Berkepentingan">
                                    <c:set value="1" var="count"/>
                                    <c:forEach items="${actionBean.pihakBerkepentinganList}" var="senarai">
                                        <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                            <c:out value="${count}"/>)&nbsp;
                                            <c:out value="${senarai.jenis.nama}"/><br>
                                            <c:set value="${count + 1}" var="count"/><br>
                                        </c:if>
                                    </c:forEach>
                                </display:column>
                                <display:column title="Syer yang dimiliki">
                                    <c:set value="1" var="count"/>
                                    <c:forEach items="${actionBean.pihakBerkepentinganList}" var="senarai">
                                        <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                            <c:out value="${count}"/>)&nbsp;
                                            <c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/><br>
                                            <c:set value="${count + 1}" var="count"/><br>
                                        </c:if>
                                    </c:forEach>
                                </display:column>
                                <display:column title="Tarikh Pemilikan Didaftar">
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${line.hakmilik.tarikhDaftar}"/>
                                </display:column>
                            </display:table>
                            <br>

                            Senarai Pihak Berkepentingan <br>
                            <div id="DocHakmilikDiv">
                                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" id="line">
                                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                    <display:column title="No. Hakmilik" >${line.hakmilik.kodHakmilik.kod}${line.hakmilik.noHakmilik}</display:column>
                                    <display:column title="Nama">
                                        <c:set value="1" var="count"/>
                                        <c:forEach items="${actionBean.pihakKepentinganList}" var="senarai">
                                            <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                                <c:out value="${count}"/>)&nbsp;
                                                <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;<br>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.KP/Co :&nbsp;<c:out value="${senarai.pihak.noPengenalan}"/>
                                                <c:set value="${count + 1}" var="count"/>
                                                <br>
                                            </c:if>
                                        </c:forEach>
                                    </display:column>
                                    <display:column title="Jenis Pihak Berkepentingan">
                                        <c:set value="1" var="count"/>
                                        <c:forEach items="${actionBean.pihakKepentinganList}" var="senarai">
                                            <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                                <c:out value="${count}"/>)&nbsp;
                                                <c:out value="${senarai.jenis.nama}"/><br>
                                                <c:set value="${count + 1}" var="count"/><br>
                                            </c:if>
                                        </c:forEach>
                                    </display:column>
                                    <display:column title="Syer yang dimiliki">
                                        <c:set value="1" var="count"/>
                                        <c:forEach items="${actionBean.pihakKepentinganList}" var="senarai">
                                            <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                                <c:out value="${count}"/>)&nbsp;
                                                <c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/><br>
                                                <c:set value="${count + 1}" var="count"/><br>
                                            </c:if>
                                        </c:forEach>
                                    </display:column>
                                    <display:column title="Tarikh Pemilikan Didaftar">
                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.hakmilik.tarikhDaftar}"/>
                                    </display:column>
                                </display:table>
                            </div>
                            <br>

                            Senarai Waris <br>

                            <display:table name="${actionBean.hakmilikWarisList}" id="line2" class="tablecloth">
                                <display:column title="Bil">
                                    ${line2_rowNum}
                                    <s:hidden name="x" class="x${line2_rowNum -1}" value=""/>
                                </display:column>
                                <display:column property="nama" title="Nama" />
                                <display:column property="noPengenalan" title="No. Pengenalan" />
                                <display:column title="Syer"> ${line2.syerPembilang }/${line2.syerPenyebut} </display:column>
                            </display:table>

                        </div>
                    </div>

                    <div id="TanahHakmilikDiv">
                        <div class="subtitle">
                            <fieldset class="aras1">
                                <c:if test="${actionBean.hakmilikPermohonan ne null}">
                                    <legend>Tanah Milik</legend>
                                    <div class="content" align="center">
                                        <display:table  name="${actionBean.permohonan.senaraiHakmilik}" id="line" class="tablecloth">
                                            <display:column title="Jenis Hakmilik">
                                                <c:if test="${line.hakmilik.kodHakmilik.nama ne null}"> ${line.hakmilik.kodHakmilik.nama}&nbsp; </c:if>
                                                <c:if test="${line.hakmilik.kodHakmilik.nama eq null}"> Tiada </c:if>
                                            </display:column>

                                            <display:column title="Nombor Hakmilik">
                                                <c:if test="${line.hakmilik.idHakmilik ne null}"> ${line.hakmilik.idHakmilik}&nbsp; </c:if>
                                                <c:if test="${line.hakmilik.idHakmilik eq null}"> Tiada </c:if>
                                            </display:column>

                                            <display:column title="Nombor Lot/PT" >
                                                <c:if test="${line.hakmilik.noLot ne null}"> ${line.hakmilik.noLot}&nbsp; </c:if>
                                                <c:if test="${line.hakmilik.noLot eq null}"> Tiada </c:if>
                                            </display:column>
                                            <display:column title="Luas">
                                                <c:if test="${line.hakmilik.luas ne null}"> <fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</c:if>
                                                <c:if test="${line.hakmilik.luas eq null}"> Tiada </c:if>
                                            </display:column>
                                            <display:column property="hakmilik.kategoriTanah.nama" title="Kegunaan" >
                                                <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot ne null}"> ${actionBean.laporanTanah.sempadanUtaraNoLot}&nbsp; </c:if>
                                                <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot eq null}"> Tiada </c:if>
                                            </display:column>
                                            <display:column title="Syarat Nyata">
                                                <c:if test="${line.hakmilik.syaratNyata.syarat ne null}"> ${line.hakmilik.syaratNyata.syarat}&nbsp; </c:if>
                                                <c:if test="${line.hakmilik.syaratNyata.syarat eq null}"> Tiada </c:if>
                                            </display:column>
                                            <display:column property="hakmilik.rizab.nama" title="Rizab" >
                                                <c:if test="${actionBean.hakmilik.rizab.nama ne null}"> ${actionBean.hakmilik.rizab.nama}&nbsp; </c:if>
                                                <c:if test="${actionBean.hakmilik.rizab.nama eq null}"> Tiada </c:if>
                                            </display:column>

                                            <display:column title="Cukai (RM)">
                                                <c:if test="${line.hakmilik.cukai ne null}"> <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukai}"/>&nbsp; </c:if>
                                                <c:if test="${line.hakmilik.cukai eq null}"> Tiada </c:if>
                                            </display:column>
                                            <display:column title="Imej Di Atas Tanah ">
                                                <c:if test="${edit}">
                                                    <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                                         onclick="muatNaikForm('${actionBean.permohonan.folderDokumen.folderId}','',
                                                             '${actionBean.permohonan.idPermohonan}','IH','${actionBean.laporanTanah.idLaporan}','${line.hakmilik.idHakmilik}');return false;" height="30" width="30" alt="Muat Naik"
                                                         onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen Gambar Hakmilik"/>
                                                    <br>
                                                </c:if>
                                                <c:set value="1" var="count"/>
                                                <c:forEach items="${actionBean.imejLaporanList}" var="senarai">
                                                    <c:if test="${senarai.hakmilik.idHakmilik eq line.hakmilik.idHakmilik}">
                                                        <%--<c:if test="${senarai.dokumen.namaFizikal ne null}">--%>
                                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                             onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                             onmouseover="this.style.cursor='pointer';" title="Papar Dokumen Gambar Hakmilik"/> <font size="1">${count}-${senarai.dokumen.tajuk}</font>

                                                        <c:if test="${edit}">
                                                            /
                                                            <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                                 onclick="removeImej('${senarai.idImej}','${senarai.dokumen.idDokumen}');" height="15" width="15" alt="Hapus"
                                                                 onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                                        </c:if>

                                                        <br/>
                                                        <c:set value="${count+1}" var="count"/>
                                                        <%--</c:if>--%>
                                                    </c:if>
                                                </c:forEach>
                                            </display:column>

                                        </display:table>
                                    </div>
                                </c:if>
                            </fieldset>
                        </div>
                    </div>
                </fieldset>
            </div>
        </div>

        <div id="tanahMilikDiv">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Tanah Kerajaan/Rizab</legend>
                    <div class="content" align="center">
                        <display:table  name="${actionBean.senaraiTanahMilik}" id="line" class="tablecloth">
                            <display:column title="Nombor Lot/PT" >
                                <c:if test="${line.noLot ne null}"> ${line.noLot}&nbsp; </c:if>
                                <c:if test="${line.noLot eq null}"> Tiada </c:if>
                            </display:column>

                            <display:column title="Kod Lot" >
                                <c:if test="${line.lot.nama ne null}"> ${line.lot.nama}&nbsp; </c:if>
                                <c:if test="${line.lot.nama eq null}"> Tiada </c:if>
                            </display:column>

                            <display:column title="Kategori Tanah" >
                                <c:if test="${line.kategoriTanahBaru.nama ne null}"> ${line.kategoriTanahBaru.nama}&nbsp; </c:if>
                                <c:if test="${line.kategoriTanahBaru.nama eq null}"> Tiada </c:if>
                            </display:column>

                            <display:column title="Luas">
                                <c:if test="${line.luasTerlibat ne null}"> ${line.luasTerlibat}&nbsp; ${line.kodUnitLuas.nama}</c:if>
                                <c:if test="${line.luasTerlibat eq null}"> Tiada </c:if>
                            </display:column>

                            <display:column title="Kod Dun">
                                <c:if test="${line.kodDUN.nama ne null}"> ${line.kodDUN.nama}</c:if>
                                <c:if test="${line.kodDUN.nama eq null}"> Tiada </c:if>
                            </display:column>

                            <display:column title="Catatan">
                                <c:if test="${line.catatan ne null}"> ${line.catatan}&nbsp; </c:if>
                                <c:if test="${line.catatan eq null}"> Tiada </c:if>
                            </display:column>
                            <display:column title="Imej Di Atas Tanah">
                                <c:if test="${edit}">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                         onclick="muatNaikForm('${actionBean.permohonan.folderDokumen.folderId}','',
                                             '${actionBean.permohonan.idPermohonan}','IH','${actionBean.laporanTanah.idLaporan}','${line.hakmilik.idHakmilik}','${line.id}');return false;" height="30" width="30" alt="Muat Naik"
                                         onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen Gambar Hakmilik"/>
                                    <br>
                                </c:if>
                                <c:set value="1" var="count"/>
                                <c:forEach items="${actionBean.imejLaporanList}" var="i">
                                </c:forEach>
                                <c:forEach items="${actionBean.imejLaporanList}" var="senarai">
                                    <c:if test="${senarai.dokumen.kodDokumen.kod eq 'IH' && senarai.dokumen.perihal eq line.id}">
                                        <%--<c:if test="${senarai.dokumen.namaFizikal ne null}">--%>
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                             onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                             onmouseover="this.style.cursor='pointer';" title="Papar Dokumen Gambar Hakmilik"/> <font size="1">${count}-${senarai.dokumen.tajuk}</font>

                                        <c:if test="${edit}">
                                            /
                                            <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                 onclick="removeImej('${senarai.idImej}','${senarai.dokumen.idDokumen}');" height="15" width="15" alt="Hapus"
                                                 onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                        </c:if>

                                        <br/>
                                        <c:set value="${count+1}" var="count"/>
                                        <%--</c:if>--%>
                                    </c:if>
                                </c:forEach>
                            </display:column>
                            <c:if test="${edit}">
                                <display:column title="Kemaskini">
                                    <div align="center">
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editRecord('${line.id}')"/>
                                    </div>
                                </display:column>

                                <display:column title="Hapus" >
                                    <div align="center">
                                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeRecord('${line.id}');"/>
                                    </div>
                                </display:column>

                            </c:if>

                        </display:table>
                    </div>

                    <c:if test="${edit}">
                        <p align="center">
                            <s:button name="addRecordHakmilik" id="addRecordHakmilik" value="Tambah" class="btn" onclick="addRecord();"/>
                        </p>
                    </c:if>

                </fieldset>
            </div>
        </div>
    </c:if>
    <c:if test="${actionBean.maklumatTanahSek49 eq true}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Tanah Kerajaan/Rizab/Tanah Milik
                </legend>
                <div id="maklumatTanah49Div" class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiHakmilikPermohonan}" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="/penguatkuasaan/maklumat_jenis_hakisan">
                        <display:column title="Bil"><u><a class="popup" onclick="viewJenisHakisan(${line.id})">${line_rowNum}</a></u></display:column>
                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}" style="vertical-align:baseline"/>
                        <display:column title="No.Lot" property="noLot" class="daerah"></display:column>
                        <display:column title="Kod Lot" property="lot.nama"></display:column>
                        <display:column title="Kategori Tanah" property="jenisPenggunaanTanah.nama"></display:column>
                        <display:column title="Luas" property="luasTerlibat"></display:column>
                        <display:column title="Kod Luas" property="kodUnitLuas.nama"></display:column>
                        <display:column title="Jenis Hakisan">
                            <c:if test="${line.jenisHakisan eq 'S'}">
                                Hakisan Sebahagian
                            </c:if>
                            <c:if test="${line.jenisHakisan eq 'P'}">
                                Hakisan Penuh
                            </c:if>
                        </display:column>
                        <c:if test="${edit}">
                            <display:column title="Kemaskini">
                                <div align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editJenisHakisan('${line.id}')"/>
                                </div>
                            </display:column>
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeJenisHakisan('${line.id}');"/>
                                </div>
                            </display:column>
                        </c:if>

                    </display:table>
                    <c:if test="${edit}">
                        <p align="center">
                            <s:button name="addRecord" id="addRecord" value="Tambah" class="btn" onclick="addJenisHakisan();"/>
                        </p>
                    </c:if>

                </div>

                <c:if test="${actionBean.statusUlasan eq false}">
                    <legend>
                        Pihak Terlibat
                    </legend>
                    <div id="pihakTerlibatDiv" class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.senaraiHakmilikPermohonanForPihakSek49}" id="line">
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Id. Hakmilik" >${line.hakmilik.idHakmilik}</display:column>
                            <display:column title="Nama">
                                <c:set value="1" var="count"/>
                                <c:forEach items="${actionBean.pihakBerkepentinganListSek49}" var="senarai">
                                    <c:if test="${senarai.hakmilik.idHakmilik eq line.hakmilik.idHakmilik && senarai.jenis.kod eq 'PM'}">
                                        <c:out value="${count}"/>)&nbsp;
                                        <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;<br>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.KP/Co :&nbsp;<c:out value="${senarai.pihak.noPengenalan}"/>
                                        <c:set value="${count + 1}" var="count"/>
                                        <br> 
                                    </c:if>
                                </c:forEach>
                                <c:forEach items="${actionBean.pihakBerkepentinganListSek49}" var="senarai">
                                    <c:if test="${senarai.hakmilik.idHakmilik eq line.hakmilik.idHakmilik && senarai.jenis.kod ne 'PM'}">
                                        <c:out value="${count}"/>)&nbsp;
                                        <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;<br>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.KP/Co :&nbsp;<c:out value="${senarai.pihak.noPengenalan}"/>
                                        <c:set value="${count + 1}" var="count"/>
                                        <br> 
                                    </c:if>
                                </c:forEach>

                            </display:column>
                            <display:column title="Jenis Pihak Berkepentingan">
                                <c:set value="1" var="count"/>
                                <c:forEach items="${actionBean.pihakBerkepentinganListSek49}" var="senarai">
                                    <c:if test="${senarai.hakmilik.idHakmilik eq line.hakmilik.idHakmilik && senarai.jenis.kod eq 'PM'}">
                                        <c:out value="${count}"/>)&nbsp;
                                        <c:out value="${senarai.jenis.nama}"/><br>
                                        <c:set value="${count + 1}" var="count"/><br>
                                    </c:if>
                                </c:forEach>
                                <c:forEach items="${actionBean.pihakBerkepentinganListSek49}" var="senarai">
                                    <c:if test="${senarai.hakmilik.idHakmilik eq line.hakmilik.idHakmilik && senarai.jenis.kod ne 'PM'}">
                                        <c:out value="${count}"/>)&nbsp;
                                        <c:out value="${senarai.jenis.nama}"/><br>
                                        <c:set value="${count + 1}" var="count"/><br>
                                    </c:if>
                                </c:forEach>
                            </display:column>
                            <display:column title="Syer yang dimiliki">
                                <c:set value="1" var="count"/>
                                <c:forEach items="${actionBean.pihakBerkepentinganListSek49}" var="senarai">
                                    <c:if test="${senarai.hakmilik.idHakmilik eq line.hakmilik.idHakmilik && senarai.jenis.kod eq 'PM'}">
                                        <c:out value="${count}"/>)&nbsp;
                                        <c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/><br>
                                        <c:set value="${count + 1}" var="count"/><br>
                                    </c:if>
                                </c:forEach>
                                <c:forEach items="${actionBean.pihakBerkepentinganListSek49}" var="senarai">
                                    <c:if test="${senarai.hakmilik.idHakmilik eq line.hakmilik.idHakmilik && senarai.jenis.kod ne 'PM'}">
                                        <c:out value="${count}"/>)&nbsp;
                                        <c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/><br>
                                        <c:set value="${count + 1}" var="count"/><br>
                                    </c:if>
                                </c:forEach>
                            </display:column>
                            <display:column title="Tarikh Pemilikan Didaftar">
                                <fmt:formatDate pattern="dd/MM/yyyy" value="${line.hakmilik.tarikhDaftar}"/>
                            </display:column>
                        </display:table>
                    </div>
                </c:if>

            </fieldset>
        </div>
    </c:if>

    <div class="subtitle">
        <c:if test="${actionBean.permohonan.kodUrusan.kod ne '127' && actionBean.statusUlasan eq false}">
            <fieldset class="aras1">
                <legend>Laporan Tanah</legend>
                <c:set scope="request" var="senaraiPBT"  value="${actionBean.pihakKepentinganList}"/>
                <p>
                    <label>Tarikh Aduan Diterima :</label>
                    <c:if test="${actionBean.permohonan.infoAudit.tarikhMasuk ne null}"><s:format formatPattern="dd/MM/yyyy" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>&nbsp;</c:if>
                    <c:if test="${actionBean.permohonan.infoAudit.tarikhMasuk eq null}"> Tiada </c:if>

                    </p>
                </fieldset>
        </c:if>
    </div>

    <c:if test="${actionBean.statusUlasan eq false}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Perihal Tanah</legend>
                <br>
                <c:if test="${edit}">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq '127' && actionBean.stageId eq 'maklum_milik_sementara'}">
                        <p>
                            <label>Tarikh Arahan PBN:</label>
                            <s:text name="tarikhSidang" id="tarikhSidang" class="datepicker" formatPattern="dd/MM/yyyy"/>&nbsp;
                            <font color="red" size="1">cth : hh / bb / tttt</font>
                        </p>
                    </c:if>

                    <p>
                        <label>Nombor Warta Kerajaan:</label>
                        <s:text name="noWartaKerajaan" maxlength="50" id="noWartaKerajaan"/>&nbsp;
                    </p>

                    <p>
                        <label>Tarikh Lawatan:</label>
                        <s:text name="tarikhLawatan" id="tarikhLawatan" class="datepicker" formatPattern="dd/MM/yyyy"/>&nbsp;

                        <font color="red" size="1">cth : hh / bb / tttt</font>
                    </p>
                    <p>
                        <label>Masa Lawatan:</label>
                        <s:select name="jam" id="jam">
                            <s:option value=""> Jam </s:option>
                            <c:forEach var="jam" begin="1" end="12">
                                <s:option value="${jam}">${jam}</s:option>
                            </c:forEach>
                        </s:select>
                        <s:select name="minit" id="minit">
                            <s:option value=""> Minit </s:option>
                            <c:forEach var="minit" begin="00" end="59" >
                                <c:choose>
                                    <c:when test="${minit > 9}"><s:option value="${minit}">${minit}</s:option></c:when>
                                    <c:otherwise><s:option value="0${minit}">0${minit}</s:option></c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </s:select>
                        <s:select name="ampm" id="ampm">
                            <s:option value="">Pilih</s:option>
                            <s:option value="AM">PAGI</s:option>
                            <s:option value="PM">PETANG</s:option>
                        </s:select>
                    </p>
                    <c:if test = "${actionBean.kodNegeri eq '04'}">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne '49'}">
                            <p>
                                <label>Anggaran Luas Terhakis/Ceroboh/Dilanggar Syarat :</label>
                                <s:text name="laporanTanah.usahaLuas" onkeyup="validateNumber(this,this.value);"/>
                                <s:select name="anggaranLuasUOM">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodUOMByLuas}" label="nama" value="kod" sort="nama" />
                                </s:select>
                            </p>
                        </c:if>
                    </c:if>
                    <div id="otherInfoHakmilik">
                        <p>
                            <label>Jenis Tanah :</label>
                            <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama}&nbsp; </c:if>
                            <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama eq null}"> Tiada </c:if>
                            </p>
                            <p>
                                <label>Lokaliti :</label>
                            <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi}&nbsp; </c:if>
                            <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi eq null}"> Tiada </c:if>
                            </p>
                            <p>
                                <label>Kawasan PBT :</label>
                            <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama}&nbsp; </c:if>
                            <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama eq null}"> Tiada </c:if>
                            </p>
                        </div>
                        <div id="kodDunHakmilikPermohonanDiv">
                        <c:if test = "${actionBean.kodNegeri eq '04' && actionBean.permohonan.kodUrusan.kod ne '49'}">
                            <c:if test="${fn:length(actionBean.hakmilikPermohonanList) ne 0}">
                                <p>
                                    <label>Kod Dun :</label>
                                    <s:select name="kodDunLaporanTanah">
                                        <s:option value="">--Sila Pilih--</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodDUN}" label="nama" value="kod" />
                                    </s:select>
                                </p>
                            </c:if>
                        </c:if>
                    </div>


                    <br>
                    <div class="content" align="center">
                        <c:set var="idAduan" value="${actionBean.permohonan.idPermohonan}" />
                        <c:set var="kodNegeri" value="${fn:substring(idAduan, 0, 2)}" />
                        <c:if test = "${kodNegeri eq '05'}">Bersempadan</c:if>
                        <c:if test = "${kodNegeri eq '04'}">Mercu Tanda</c:if>
                        <%--Bersempadan--%>
                        <table class="tablecloth">
                            <tr>
                                <th>Bersempadan</th><th>Nama</th><th>Jarak</th>
                            </tr>
                            <tr>
                                <td>
                                    Jalan Raya
                                </td>
                                <td>
                                    <s:text name="laporanTanah.namaSempadanJalanraya"/>
                                </td>
                                <td>
                                    <s:text name="laporanTanah.jarakSempadanJalanraya"  maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                                    <s:select name="jarakJalanUOM"  id="jenisWarta">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.kodUOMByJarak}" label="nama" value="kod" sort="nama" />
                                    </s:select>
                                </td>

                            </tr>
                            <tr>
                                <td>
                                    Landasan Keretapi
                                </td>
                                <td>
                                    <s:text name="laporanTanah.namaSempadanKeretapi" />
                                </td>
                                <td>
                                    <s:text name="laporanTanah.jarakSempadanKeretapi" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                                    <s:select name="jarakKeretapiUOM"  id="jenisWarta">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.kodUOMByJarak}" label="nama" value="kod" sort="nama" />
                                    </s:select>
                                </td>

                            </tr>
                            <tr>
                                <td>
                                    Laut
                                </td>
                                <td>
                                    <s:text name="laporanTanah.namaSempadanLaut" />
                                </td>
                                <td>
                                    <s:text name="laporanTanah.jarakSempadanLaut"  maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                                    <s:select name="jarakLautUOM"  id="jenisWarta">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.kodUOMByJarak}" label="nama" value="kod" sort="nama" />
                                    </s:select>
                                </td>
                            </tr><tr>
                                <td>
                                    Sungai
                                </td>
                                <td>
                                    <s:text name="laporanTanah.namaSempadanSungai" />
                                </td>
                                <td>
                                    <s:text name="laporanTanah.jarakSempadanSungai"  maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                                    <s:select name="jarakSungaiUOM"  id="jenisWarta">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.kodUOMByJarak}" label="nama" value="kod" sort="nama" />
                                    </s:select>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <p>
                        <label>Jenis Jalan :</label>
                        <s:select name="laporanTanah.jenisJalan" style="width:200px" id="jabatan">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="JB">Jalan Berturap</s:option>
                            <s:option value="JL">Jalan Leterite</s:option>
                            <s:option value="JTM">Jalan Tanah Merah</s:option>
                            <s:option value="JT">Jalan Tanah</s:option>
                        </s:select>
                    </p>
                    <p>
                        <label>Jalan Masuk :</label>
                        <s:radio name="laporanTanah.adaJalanMasuk" value="Y"/>&nbsp;Ada
                        <s:radio name="laporanTanah.adaJalanMasuk" value="T"/>&nbsp;Tiada
                    </p>
                    <p>
                        <label>Catatan :</label>
                        <s:textarea name="laporanTanah.catatanJalanMasuk" class="normal_text" cols="50" rows="5"/>
                    </p>
                </c:if>
                <c:if test="${!edit}">
                    <p>
                        <label>Keluasan :</label>
                        <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.luas ne null}"><fmt:formatNumber  pattern="#,##0.0000" value="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.luas}"/>
                            ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodUnitLuas.nama}&nbsp;</c:if>
                        <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.luas eq null}"> Tiada </c:if>


                        </p>
                        <p>
                            <label>Status Tanah :</label>
                        <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.rizab.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.rizab.nama}&nbsp; </c:if>
                        <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.rizab.nama eq null}"> Tiada </c:if>
                        </p>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq '127' && actionBean.stageId eq 'maklum_milik_sementara'}">
                        <p>
                            <label>Tarikh Arahan PBN :</label>
                            <c:if test="${actionBean.permohonanRujukanLuar.tarikhSidang ne null}"><s:format formatPattern="dd/MM/yyyy" value="${actionBean.permohonanRujukanLuar.tarikhSidang}" />&nbsp; </c:if>
                            <c:if test="${actionBean.permohonanRujukanLuar.tarikhSidang eq null}"> Tiada </c:if>
                            </p>
                    </c:if>
                    <p>
                        <label>Nombor Warta Kerajaan :</label>
                        <c:if test="${actionBean.permohonanRujukanLuar.noRujukan ne null}"> ${actionBean.permohonanRujukanLuar.noRujukan}&nbsp; </c:if>
                        <c:if test="${actionBean.permohonanRujukanLuar.noRujukan eq null}"> Tiada </c:if>
                        </p>
                        <p>
                            <label>Tarikh Lawatan :</label>
                        <c:if test="${actionBean.tarikhLawatan ne null}"> ${actionBean.tarikhLawatan}&nbsp; </c:if>
                        <c:if test="${actionBean.tarikhLawatan eq null}"> Tiada </c:if>

                        </p>
                        <p>
                            <label>Masa Lawatan :</label>
                        <c:set var="valueAmPm" value="${actionBean.ampm}"/>
                        <c:if test="${valueAmPm eq 'AM'}"> <c:set var="valueAmPm" value="PAGI"/></c:if>
                        <c:if test="${valueAmPm eq 'PM'}"> <c:set var="valueAmPm" value="PETANG"/></c:if>
                        <c:if test="${actionBean.tarikhLawatan ne null}"> ${actionBean.jam}:${actionBean.minit} ${valueAmPm}&nbsp; </c:if>
                        <c:if test="${actionBean.tarikhLawatan eq null}"> Tiada </c:if>

                        </p>
                    <c:if test = "${actionBean.kodNegeri eq '04'}">
                        <p>
                            <label>Anggaran Luas Terhakis/Ceroboh/Dilanggar Syarat :</label>
                            <c:if test="${actionBean.laporanTanah.usahaLuas ne null}">${actionBean.laporanTanah.usahaLuas}&nbsp; ${actionBean.laporanTanah.usahaLuasUom.nama}</c:if>
                            <c:if test="${actionBean.laporanTanah.usahaLuas eq null}"> Tiada </c:if>
                            </p>
                    </c:if>
                    <p>
                        <label>Jenis Tanah :</label>
                        <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama}&nbsp; </c:if>
                        <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama eq null}"> Tiada </c:if>
                        </p>
                        <p>
                            <label>Lokaliti :</label>
                        <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi}&nbsp; </c:if>
                        <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi eq null}"> Tiada </c:if>
                        </p>
                        <p>
                            <label>Kawasan PBT :</label>
                        <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama}&nbsp; </c:if>
                        <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama eq null}"> Tiada </c:if>
                        </p>
                    <c:if test = "${actionBean.kodNegeri eq '04' && actionBean.permohonan.kodUrusan.kod ne '49'}">
                        <p>
                            <label>Kod Dun :</label>
                            <c:if test="${actionBean.paparKodDun ne null}"> ${actionBean.paparKodDun}&nbsp; </c:if>
                            <c:if test="${actionBean.paparKodDun eq null}"> Tiada </c:if>
                            </p>
                    </c:if>
                    <br>
                    <div class="content" align="center">
                        <%--Bersempadan--%>
                        <c:set var="idAduan" value="${actionBean.permohonan.idPermohonan}" />
                        <c:set var="kodNegeri" value="${fn:substring(idAduan, 0, 2)}" />
                        <c:if test = "${kodNegeri eq '05'}">Bersempadan</c:if>
                        <c:if test = "${kodNegeri eq '04'}">Mercu Tanda</c:if>
                            <table class="tablecloth">
                                <tr>
                                    <th>Bersempadan</th><th>Nama</th><th width="120">Jarak</th>
                                </tr>
                                <tr>
                                    <td>
                                        Jalan Raya
                                    </td>
                                    <td>
                                    <c:if test="${actionBean.laporanTanah.namaSempadanJalanraya ne null}"> ${actionBean.laporanTanah.namaSempadanJalanraya}&nbsp; </c:if>
                                    <c:if test="${actionBean.laporanTanah.namaSempadanJalanraya eq null}"> Tiada </c:if>
                                    </td>
                                    <td>
                                    <c:if test="${actionBean.laporanTanah.jarakSempadanJalanraya ne null}"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.laporanTanah.jarakSempadanJalanraya}"/> ${actionBean.laporanTanah.jarakSempadanJalanrayaUOM.nama}</c:if>
                                    <c:if test="${actionBean.laporanTanah.jarakSempadanJalanraya eq null}"> Tiada </c:if>
                                    </td>

                                </tr>
                                <tr>
                                    <td>
                                        Landasan Keretapi
                                    </td>
                                    <td>
                                    <c:if test="${actionBean.laporanTanah.namaSempadanKeretapi ne null}"> ${actionBean.laporanTanah.namaSempadanKeretapi}&nbsp; </c:if>
                                    <c:if test="${actionBean.laporanTanah.namaSempadanKeretapi eq null}"> Tiada </c:if>
                                    </td>
                                    <td>
                                    <c:if test="${actionBean.laporanTanah.jarakSempadanKeretapi ne null}">  <fmt:formatNumber pattern="#,##0.00" value="${actionBean.laporanTanah.jarakSempadanKeretapi}"/> ${actionBean.laporanTanah.jarakSempadanKeretapiUOM.nama}&nbsp; </c:if>
                                    <c:if test="${actionBean.laporanTanah.jarakSempadanKeretapi eq null}"> Tiada </c:if>
                                    </td>

                                </tr>
                                <tr>
                                    <td>
                                        Laut
                                    </td>
                                    <td>
                                    <c:if test="${actionBean.laporanTanah.namaSempadanLaut ne null}"> ${actionBean.laporanTanah.namaSempadanLaut}&nbsp; </c:if>
                                    <c:if test="${actionBean.laporanTanah.namaSempadanLaut eq null}"> Tiada </c:if>
                                    </td>
                                    <td>
                                    <c:if test="${actionBean.laporanTanah.jarakSempadanLaut ne null}"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.laporanTanah.jarakSempadanLaut}"/> ${actionBean.laporanTanah.jarakSempadanLautUOM.nama}&nbsp;</c:if>
                                    <c:if test="${actionBean.laporanTanah.jarakSempadanLaut eq null}"> Tiada </c:if>

                                    </td>
                                </tr><tr>
                                    <td>
                                        Sungai
                                    </td>
                                    <td>
                                    <c:if test="${actionBean.laporanTanah.namaSempadanSungai ne null}"> ${actionBean.laporanTanah.namaSempadanSungai}&nbsp; </c:if>
                                    <c:if test="${actionBean.laporanTanah.namaSempadanSungai eq null}"> Tiada </c:if>
                                    </td>
                                    <td>
                                    <c:if test="${actionBean.laporanTanah.jarakSempadanSungai ne null}">   <fmt:formatNumber pattern="#,##0.00" value="${actionBean.laporanTanah.jarakSempadanSungai}"/> ${actionBean.laporanTanah.jarakSempadanSungaiUOM.nama}&nbsp; </c:if>
                                    <c:if test="${actionBean.laporanTanah.jarakSempadanSungai eq null}"> Tiada </c:if>

                                    </td>
                                </tr>
                            </table>
                        </div>
                        <p>
                            <label>Jenis Jalan :</label>
                        <c:if test="${actionBean.laporanTanah.jenisJalan ne null}">
                            <c:choose>
                                <c:when test="${actionBean.laporanTanah.jenisJalan eq 'JB'}">
                                    Jalan Berturap&nbsp;
                                </c:when>
                                <c:when test="${actionBean.laporanTanah.jenisJalan eq 'JL'}">
                                    Jalan Leterite&nbsp;
                                </c:when>
                                <c:when test="${actionBean.laporanTanah.jenisJalan eq 'JTM'}">
                                    Jalan Tanah Merah&nbsp;
                                </c:when>
                                <c:when test="${actionBean.laporanTanah.jenisJalan eq 'JT'}">
                                    Jalan Tanah&nbsp;
                                </c:when>
                            </c:choose>
                        </c:if>
                        <c:if test="${actionBean.laporanTanah.jenisJalan eq null}"> Tiada </c:if>
                        </p>
                        <p>
                            <label>Jalan Masuk :</label>
                        <c:if test="${actionBean.laporanTanah.adaJalanMasuk eq 'Y'}">Ada</c:if>
                        <c:if test="${actionBean.laporanTanah.adaJalanMasuk ne 'Y'}">Tiada</c:if>
                        </p>
                        <p>
                            <label>Catatan :</label>
                        </p>
                    <%--<c:if test="${actionBean.laporanTanah.catatanJalanMasuk ne null}"> ${actionBean.laporanTanah.catatanJalanMasuk}&nbsp; </c:if>--%>
                    <c:if test="${actionBean.laporanTanah.catatanJalanMasuk ne null}">
                        <%--<s:textarea name="laporanTanah.catatanJalanMasuk" id="catatanJalanMasuk" rows="7" cols="80" readonly="true" onchange="this.value=this.value.toUpperCase();" disabled="false" />&nbsp;--%>
                        <table>
                            <tr>
                                <td valign="top">
                                    <font size="2px;">${actionBean.laporanTanah.catatanJalanMasuk}&nbsp;</font></td>
                            </tr>
                        </table>
                    </c:if>
                    <p>
                        <c:if test="${actionBean.laporanTanah.catatanJalanMasuk eq null}"> Tiada </c:if>
                        </p>
                </c:if>
            </fieldset>
        </div>

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Keadaan Tanah</legend>
                <c:if test="${edit}">
                    <p id="kodKecerunanTanahDiv">
                        <label>Keadaan Tanah :</label>

                        <s:select name="kodKecerunanTanah" id="keadaanTanah" onchange="javaScript:changeKeadaanTanah(this.options[selectedIndex].text)">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodKecerunanTanah}" label="nama" value="kod" />
                        </s:select>
                    </p>
                    <p id="tinggi">
                        <label>Ketinggian Dari Paras Jalan (m) :</label>
                        <s:text name="laporanTanah.ketinggianDariJalan" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                    <p id="cerun">
                        <label>Darjah Kecerunan (&deg;):</label>
                        <s:text name="laporanTanah.kecerunanBukit"  maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                    <p id="dalam">
                        <label>Dalam Paras Air (m) :</label>
                        <s:text name="laporanTanah.parasAir" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                    <p>
                        <label>Klasifikasi Tanah :</label>
                        <s:select name="kodStrukturTanah">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodStrukturTanah}" label="nama" value="kod" />
                        </s:select>
                    </p>
                    <br>
                    <p>
                        <label>Keadaan Atas Tanah :</label>
                        <s:textarea name="laporanTanah.keadaanTanah" class="normal_text" cols="50" rows="5" onkeydown="limitText(this,500);" onkeyup="limitText(this,500);"/>
                    </p>
                    <p>
                        <label>Dilintasi Oleh :</label>
                        <s:checkbox name="laporanTanah.dilintasTiangElektrik" value="Y"/>&nbsp; Talian Elektrik<br>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:checkbox name="laporanTanah.dilintasTiangTelefon" value="Y"/>&nbsp; Talian Telefon<br>

                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:checkbox name="laporanTanah.dilintasLaluanGas" value="Y"/>&nbsp; Laluan Gas<br>

                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:checkbox name="laporanTanah.dilintasPaip" value="Y"/>&nbsp; Paip Air<br>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:checkbox name="laporanTanah.dilintasTaliar" value="Y"/>&nbsp; Tali Air<br>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:checkbox name="laporanTanah.dilintasSungai" value="Y"/>&nbsp; Sungai<br>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:checkbox name="laporanTanah.dilintasParit" value="Y"/>&nbsp; Parit<br>
                    </p>
                </c:if>
                <c:if test="${!edit}">
                    <p>
                        <label>Keadaan Tanah :</label>
                        <c:if test="${actionBean.laporanTanah.kecerunanTanah.nama ne null}"> ${actionBean.laporanTanah.kecerunanTanah.nama}&nbsp; </c:if>
                        <c:if test="${actionBean.laporanTanah.kecerunanTanah.nama eq null}"> Tiada </c:if>
                        </p>
                        <p id="tinggi">
                            <label>Ketinggian Dari Paras Jalan (m) :</label>
                        <c:if test="${actionBean.laporanTanah.ketinggianDariJalan ne null}"> ${actionBean.laporanTanah.ketinggianDariJalan}&nbsp; </c:if>
                        <c:if test="${actionBean.laporanTanah.ketinggianDariJalan eq null}"> Tiada </c:if>
                        </p>
                        <p id="cerun">
                            <label>Darjah Kecerunan :</label>
                        <c:if test="${actionBean.laporanTanah.kecerunanBukit ne null}"> ${actionBean.laporanTanah.kecerunanBukit}&nbsp; </c:if>
                        <c:if test="${actionBean.laporanTanah.kecerunanBukit eq null}"> Tiada </c:if>
                        </p>
                        <p id="dalam">
                            <label>Dalam Paras Air (m) :</label>
                        <c:if test="${actionBean.laporanTanah.parasAir ne null}"> ${actionBean.laporanTanah.parasAir}&nbsp; </c:if>
                        <c:if test="${actionBean.laporanTanah.parasAir eq null}"> Tiada </c:if>
                        </p>
                        <p>
                            <label>Klasifikasi Tanah :</label>
                        <c:if test="${actionBean.laporanTanah.strukturTanah.nama ne null}"> ${actionBean.laporanTanah.strukturTanah.nama}&nbsp; </c:if>
                        <c:if test="${actionBean.laporanTanah.strukturTanah.nama eq null}"> Tiada </c:if>
                        </p>
                        <p>
                            <label>Keadaan Atas Tanah :</label>
                        </p>
                    <%--<c:if test="${actionBean.laporanTanah.keadaanTanah ne null}"> ${actionBean.laporanTanah.keadaanTanah}&nbsp; </c:if>--%>
                    <c:if test="${actionBean.laporanTanah.keadaanTanah ne null}">
                        <table>
                            <tr>
                                <td valign="top">
                                    <font size="2px;">${actionBean.laporanTanah.keadaanTanah}&nbsp;</font></td>
                            </tr>
                        </table>
                        <%--<s:textarea name="laporanTanah.keadaanTanah" id="keadaanTanah" rows="7" cols="80" readonly="true" onchange="this.value=this.value.toUpperCase();" disabled="false" />&nbsp;--%>
                    </c:if><p>
                        <c:if test="${actionBean.laporanTanah.keadaanTanah eq null}"> Tiada </c:if>
                        </p>
                        <br>

                        <p>
                            <label>Dilintasi Oleh :</label>

                        <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik ne null or
                                      actionBean.laporanTanah.dilintasTiangTelefon ne null or
                                      actionBean.laporanTanah.dilintasLaluanGas ne null or
                                      actionBean.laporanTanah.dilintasPaip ne null or
                                      actionBean.laporanTanah.dilintasTaliar ne null or
                                      actionBean.laporanTanah.dilintasSungai ne null or
                                      actionBean.laporanTanah.dilintasParit ne null}">
                        <p>
                            <label>&nbsp;</label>
                            &nbsp;
                        </p>
                        <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik eq 'Y'}">
                            <p><label>&nbsp;</label>Talian Elektrik</p>
                        </c:if>


                        <p><c:if test="${actionBean.laporanTanah.dilintasTiangTelefon eq 'Y'}">
                                <label>&nbsp;</label>
                                Talian Telefon</c:if>
                            </p>
                            <p><c:if test="${actionBean.laporanTanah.dilintasLaluanGas eq 'Y'}">
                                <label>&nbsp;</label>
                                Laluan Gas</c:if>
                            </p>
                            <p><c:if test="${actionBean.laporanTanah.dilintasPaip eq 'Y'}">
                                <label>&nbsp;</label>
                                Paip Air</c:if>
                            </p>
                            <p><c:if test="${actionBean.laporanTanah.dilintasTaliar eq 'Y'}">
                                <label>&nbsp;</label>
                                Tali Air</c:if>
                            </p>
                            <p><c:if test="${actionBean.laporanTanah.dilintasSungai eq 'Y'}">
                                <label>&nbsp;</label>
                                Sungai</c:if>
                            </p>
                            <p><c:if test="${actionBean.laporanTanah.dilintasParit eq 'Y'}">
                                <label>&nbsp;</label>
                                Parit</c:if>
                            </p>
                    </c:if>


                    <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik eq null and
                                  actionBean.laporanTanah.dilintasTiangTelefon eq null and
                                  actionBean.laporanTanah.dilintasLaluanGas eq null and
                                  actionBean.laporanTanah.dilintasPaip eq null and
                                  actionBean.laporanTanah.dilintasTaliar eq null and
                                  actionBean.laporanTanah.dilintasSungai eq null and
                                  actionBean.laporanTanah.dilintasParit eq null}"> Tiada </c:if>
                </c:if>

            </fieldset>
        </div>

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Latar belakang Tanah</legend>
                <c:if test="${edit}">
                    <p>
                        <label>Diusahakan :</label>
                        <input name="diUsahakan" id="diUsahakan" value="${actionBean.laporanTanah.usaha}" type="hidden">
                        <%--<s:text name="diUsahakan" id="diUsahakan" value="${actionBean.laporanTanah.usaha}"/>--%>
                        <s:radio name="laporanTanah.usaha" value="Y" onclick="showUsaha();"/>&nbsp;Ya
                        <s:radio name="laporanTanah.usaha" value="T" onclick="hideUsaha();"/>&nbsp;Tidak
                    </p>
                    <p style="display:none" id="usaha1">
                        <label>Oleh :</label>
                        <s:text name="laporanTanah.diusaha" id="diusaha" size="40"/>
                    </p>
                    <p style="display:none" id="usaha2">
                        <label>Tarikh Mula Usaha :</label>
                        <s:text name="tarikhDiusahakan" class="datepicker" formatPattern="dd/MM/yyyy"/>

                    </p>
                    <p style="display:none" id="usaha3">
                        <label>Jenis Tanaman :</label>
                        <s:text name="laporanTanah.usahaTanam" id="usahaTanam" size="40"/>
                    </p>
                    <p>
                        <label>(Jika Ada)Bangunan :</label>
                        <input name="adaBangunan" id="adaBangunan" value="${actionBean.laporanTanah.adaBangunan}" type="hidden">
                        <%--<s:hidden name="adaBangunan" id="adaBangunan" value="${actionBean.laporanTanah.adaBangunan}"/>--%>
                        <s:radio name="laporanTanah.adaBangunan" value="Y" onclick="showBilBangunan();"/>&nbsp;Ada
                        <s:radio name="laporanTanah.adaBangunan" value="T" onclick="hideBilBangunan();"/>&nbsp;Tiada
                    </p>
                    <p style="display:none" id="bilanganBangunan">
                        <%-- <table style="display:none" id="bilanganBangunan" align="center" width="80%" >
                             <tr><td width="52%" align="right"> <font color="blue" style="font-size: 10pt;"><b>
                        --%>
                        <label>
                            Bil Bangunan :
                        </label>          <%--</b></font>&nbsp;</td>
                          <td>--%>
                        <s:select title="Bil Bangunan :" name="laporanTanah.bilanganBangunan" id="bilanganBangunan">
                            <s:option value="0">--Sila Pilih--</s:option>
                            <s:option>1</s:option>
                            <s:option>2</s:option>
                            <s:option>3</s:option>
                            <s:option>4</s:option>
                            <s:option>5</s:option>
                            <s:option>6</s:option>
                            <s:option>7</s:option>
                            <s:option>8</s:option>
                            <s:option>9</s:option>
                            <s:option>10</s:option>
                        </s:select>
                        <%--</td></tr>
                </table>--%>
                    </p>
                    <p style="display:none" id="bilanganBangunan2">
                        <label>Tahun Dibina :</label>
                        <s:text name="laporanTanah.bangunanTahunDibina" id="bangunanTahunDibina" maxlength="4" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                    <p style="display:none" id="bilanganBangunan3">
                        <label>Diduduki :</label>
                        <s:radio name="laporanTanah.bangunanDidiami" id="bangunanDidiami" value="Y"/>&nbsp;Ya
                        <s:radio name="laporanTanah.bangunanDidiami" id="bangunanDidiami" value="T"/>&nbsp;Tidak
                    </p>

                    <p>
                        <label>Rancangan Kerajaan :</label>
                        <s:text name="laporanTanah.rancanganKerajaan" size="40"/>
                    </p>
                    <%--    <c:if test = "${kodNegeri eq '05'}">
                            <p style="display:none" id="bilanganBangunan4">
                                <label>Jenis Bangunan :</label>
                                <s:select name="laporanTanah.jenisBangunan" id="jenisBangunan">
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:option>Sementara</s:option>
                                    <s:option>Separuh Kekal</s:option>
                                    <s:option>Kekal</s:option>
                                    <s:option>Lain-lain</s:option>

                            </s:select>
                        </p>
                    </c:if>--%>
                    <br>

                </c:if>
                <c:if test="${!edit}">
                    <p>
                        <label>Diusahakan :</label>
                        <c:if test="${actionBean.laporanTanah.usaha eq 'Y'}">Ya</c:if>
                        <c:if test="${actionBean.laporanTanah.usaha ne 'Y'}">Tidak</c:if>
                        </p>
                        <p>
                            <label>Oleh :</label>
                        <c:if test="${actionBean.laporanTanah.diusaha ne null}"> ${actionBean.laporanTanah.diusaha}&nbsp; </c:if>
                        <c:if test="${actionBean.laporanTanah.diusaha eq null}"> Tiada </c:if>
                        </p>
                        <p>
                            <label>Tarikh Mula Usaha :</label>
                        <c:if test="${actionBean.tarikhDiusahakan ne null}"><s:format formatPattern="dd/MM/yyyy" value="${actionBean.tarikhDiusahakan}" />&nbsp;</c:if>
                        <c:if test="${actionBean.tarikhDiusahakan eq null}"> Tiada </c:if>
                        </p>
                        <p>
                            <label>Jenis Tanaman :</label>
                        <c:if test="${actionBean.laporanTanah.usahaTanam ne null}"> ${actionBean.laporanTanah.usahaTanam}&nbsp; </c:if>
                        <c:if test="${actionBean.laporanTanah.usahaTanam eq null}"> Tiada </c:if>
                        </p>
                        <p>
                            <label>Bangunan :</label>
                        <c:if test="${actionBean.laporanTanah.adaBangunan eq 'Y'}">Ada</c:if>
                        <c:if test="${actionBean.laporanTanah.adaBangunan ne 'Y'}">Tiada</c:if>

                        </p>
                        <p>
                            <label>Tahun Dibina :</label>
                        <c:if test="${actionBean.laporanTanah.bangunanTahunDibina ne null}"> ${actionBean.laporanTanah.bangunanTahunDibina}&nbsp; </c:if>
                        <c:if test="${actionBean.laporanTanah.bangunanTahunDibina eq null}"> Tiada </c:if>
                        </p>
                        <p>
                            <label>Diduduki :</label>
                        <c:if test="${actionbean.laporanTanah.bangunanDidiami eq 'Y'}">Ya</c:if>
                        <c:if test="${actionbean.laporanTanah.bangunanDidiami ne 'Y'}">Tidak</c:if>

                        </p>
                    <%-- <c:if test = "${kodNegeri eq '05'}">
                         <p>
                             <label>Jenis Bangunan :</label>
                             <c:if test="${actionBean.laporanTanah.jenisBangunan ne null}"> ${actionBean.laporanTanah.jenisBangunan}&nbsp; </c:if>
                             <c:if test="${actionBean.laporanTanah.jenisBangunan eq null}"> Tiada </c:if>
                         </p>
                     </c:if>--%>

                    <p>
                        <label>Rancangan Kerajaan :</label>

                        <c:if test="${actionBean.laporanTanah.rancanganKerajaan ne null}"> ${actionBean.laporanTanah.rancanganKerajaan}&nbsp; </c:if>
                        <c:if test="${actionBean.laporanTanah.rancanganKerajaan eq null}"> Tiada </c:if>

                        </p>
                </c:if>
            </fieldset>
        </div>

        <div class="subtitle" align="center">
            <fieldset class="aras1">
                <legend>Jenis Bangunan </legend>
                <c:if test="${edit}">
                    <div id="senaraiBangunan" align="center">
                        <display:table class="tablecloth" name="${actionBean.senaraiPermohonanLaporanBangunan}" cellpadding="0" cellspacing="0" id="line" >
                            <display:column title="Bil">${line_rowNum}</display:column>
                            <display:column title="Jenis Bangunan" >
                                <c:if test="${line.jenisBangunan eq 'KK'}"><u><a class="popup" onclick="viewJenisBangunan(${line.idLaporBangunan})">Kekal</a></u></c:if>
                                <c:if test="${line.jenisBangunan eq 'SK'}"><u><a class="popup" onclick="viewJenisBangunan(${line.idLaporBangunan})">Separuh Kekal</a></u></c:if>
                                <c:if test="${line.jenisBangunan eq 'SM'}"><u><a class="popup" onclick="viewJenisBangunan(${line.idLaporBangunan})">Sementara</a></u></c:if>
                                <c:if test="${line.jenisBangunan eq 'LL'}"><u><a class="popup" onclick="viewJenisBangunan(${line.idLaporBangunan})">Lain-lain</a></u></c:if>
                            </display:column>
                            <display:column title="Ukuran">
                                ${line.ukuran}x${line.keteranganTahunBinaan}
                            </display:column>
                            <display:column property="uomUkuran.nama" title="Unit Ukuran"></display:column>
                            <display:column title="Nilai">
                                <fmt:formatNumber pattern="#,##0.00" value="${line.nilai}"/>
                            </display:column>
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeBangunan('${line.idLaporBangunan}');"/>
                                </div>
                            </display:column>

                            <display:column title="Kemaskini">
                                <div align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popupBangunan('${line.idLaporBangunan}')"/>
                                </div>
                            </display:column>

                        </display:table>
                    </div>
                    &nbsp;
                    <table align="center">
                        <tr>
                            <td >
                                <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBaruBangunan('${actionBean.laporanTanah.idLaporan}');"/>
                            </td>
                        </tr>
                    </table>
                </c:if>

                <c:if test="${!edit}">
                    <div id="senaraiBangunan" align="center">
                        <display:table class="tablecloth" name="${actionBean.senaraiPermohonanLaporanBangunan}" cellpadding="0" cellspacing="0" id="line" >
                            <display:column title="Bil">${line_rowNum}</display:column>
                            <display:column title="Jenis Bangunan" >
                                <c:if test="${line.jenisBangunan eq 'KK'}"><u><a class="popup" onclick="viewJenisBangunan(${line.idLaporBangunan})">Kekal</a></u></c:if>
                                <c:if test="${line.jenisBangunan eq 'SK'}"><u><a class="popup" onclick="viewJenisBangunan(${line.idLaporBangunan})">Separuh Kekal</a></u></c:if>
                                <c:if test="${line.jenisBangunan eq 'SM'}"><u><a class="popup" onclick="viewJenisBangunan(${line.idLaporBangunan})">Sementara</a></u></c:if>
                                <c:if test="${line.jenisBangunan eq 'LL'}"><u><a class="popup" onclick="viewJenisBangunan(${line.idLaporBangunan})">Lain-lain</a></u></c:if>
                            </display:column>
                            <display:column title="Ukuran">
                                ${line.ukuran}x${line.keteranganTahunBinaan}
                            </display:column>
                            <display:column property="uomUkuran.nama" title="Unit Ukuran"></display:column>
                            <display:column title="Nilai">
                                <fmt:formatNumber pattern="#,##0.00" value="${line.nilai}"/>
                            </display:column>
                        </display:table>
                    </div>
                </c:if>
            </fieldset>
        </div>


        <div class="subtitle" align="center">
            <fieldset class="aras1">
                <legend>Tanah Sekeliling </legend>
                <c:if test="${edit}">
                    &nbsp;
                    <br>
                    <div id="senaraiSempadan">
                        <display:table class="tablecloth" name="${actionBean.senaraiLaporanTanahSempadan}" cellpadding="0" cellspacing="0" id="line" >
                            <display:column title="Bil">${line_rowNum}</display:column>
                            <display:column  title="Jenis Sempadan">
                                <c:if test="${line.jenisSempadan eq 'U'}"><u><a class="popup" onclick="viewTanahSekeliling(${line.idLaporTanahSpdn})">Utara</a></u></c:if>
                                <c:if test="${line.jenisSempadan eq 'S'}"><u><a class="popup" onclick="viewTanahSekeliling(${line.idLaporTanahSpdn})">Selatan</a></u></c:if>
                                <c:if test="${line.jenisSempadan eq 'T'}"><u><a class="popup" onclick="viewTanahSekeliling(${line.idLaporTanahSpdn})">Timur</a></u></c:if>
                                <c:if test="${line.jenisSempadan eq 'B'}"><u><a class="popup" onclick="viewTanahSekeliling(${line.idLaporTanahSpdn})">Barat</a></u></c:if>
                            </display:column>
                            <display:column property="kodKategoriTanah.nama" title="Jenis Tanah"></display:column>
                            <display:column property="hakmilik.idHakmilik" title="Hakmilik"></display:column>
                            <display:column property="noLot" title="No Lot"></display:column>
                            <display:column property="kodLot.nama" title="Kod Lot"></display:column>
                            <display:column property="catatan" title="Catatan"></display:column>
                            <display:column title="Imej Di Atas Tanah ">
                                <c:if test="${edit}">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                         onclick="muatNaikForm('${actionBean.permohonan.folderDokumen.folderId}','',
                                             '${actionBean.permohonan.idPermohonan}','IH${line.jenisSempadan}','${actionBean.laporanTanah.idLaporan}','',${line.idLaporTanahSpdn});return false;" height="30" width="30" alt="Muat Naik"
                                         onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen Gambar Hakmilik"/>
                                    <br>
                                    <c:set value="1" var="count"/>
                                    <c:if test="${line.jenisSempadan eq 'U'}"><c:set value="${actionBean.utaraImejLaporanList}" var="imejSempadanList"/></c:if>
                                    <c:if test="${line.jenisSempadan eq 'S'}"><c:set value="${actionBean.selatanImejLaporanList}" var="imejSempadanList"/></c:if>
                                    <c:if test="${line.jenisSempadan eq 'T'}"><c:set value="${actionBean.timurImejLaporanList}" var="imejSempadanList"/></c:if>
                                    <c:if test="${line.jenisSempadan eq 'B'}"><c:set value="${actionBean.baratImejLaporanList}" var="imejSempadanList"/></c:if>
                                    <c:forEach items="${imejSempadanList}" var="senarai">
                                        <c:if test="${line.idLaporTanahSpdn eq senarai.dokumen.perihal}">
                                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                 onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                 onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.tajuk}"/> <font size="1">${count}-${senarai.dokumen.tajuk}</font>
                                            /
                                            <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                 onclick="removeImejTanahSekelilingList('${senarai.idImej}','${senarai.dokumen.idDokumen}','IH${line.jenisSempadan}');" height="15" width="15" alt="Hapus"
                                                 onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                            <br/>
                                            <c:set value="${count+1}" var="count"/>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </display:column>
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSempadan('${line.idLaporTanahSpdn}');"/>
                                </div>
                            </display:column>

                            <display:column title="Kemaskini">
                                <div align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popupSempadan('${line.idLaporTanahSpdn}')"/>
                                </div>
                            </display:column>

                        </display:table>
                    </div>
                    &nbsp;
                    <table>
                        <tr>
                            <td align="right">
                                <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBaruSempadan('${actionBean.laporanTanah.idLaporan}');"/>
                            </td>
                        </tr>
                    </table>
                    <br><br><br>
                </c:if>
                <c:if test="${!edit}">
                    <div class="content" align="center">
                        <div id="senaraiSempadan">
                            <display:table class="tablecloth" name="${actionBean.senaraiLaporanTanahSempadan}" cellpadding="0" cellspacing="0" id="line" >
                                <display:column title="Bil">${line_rowNum}</display:column>
                                <display:column  title="Jenis Sempadan">
                                    <c:if test="${line.jenisSempadan eq 'U'}"><u><a class="popup" onclick="viewTanahSekeliling(${line.idLaporTanahSpdn})">Utara</a></u></c:if>
                                    <c:if test="${line.jenisSempadan eq 'S'}"><u><a class="popup" onclick="viewTanahSekeliling(${line.idLaporTanahSpdn})">Selatan</a></u></c:if>
                                    <c:if test="${line.jenisSempadan eq 'T'}"><u><a class="popup" onclick="viewTanahSekeliling(${line.idLaporTanahSpdn})">Timur</a></u></c:if>
                                    <c:if test="${line.jenisSempadan eq 'B'}"><u><a class="popup" onclick="viewTanahSekeliling(${line.idLaporTanahSpdn})">Barat</a></u></c:if>
                                </display:column>
                                <display:column property="kodKategoriTanah.nama" title="Jenis Tanah"></display:column>
                                <display:column property="hakmilik.idHakmilik" title="Hakmilik"></display:column>
                                <display:column property="noLot" title="No Lot"></display:column>
                                <display:column property="kodLot.nama" title="Kod Lot"></display:column>
                                <display:column property="catatan" title="Catatan"></display:column>
                                <display:column title="Imej Di Atas Tanah ">
                                    <c:if test="${!edit}">
                                        <%-- <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                              onclick="muatNaikForm('${actionBean.permohonan.folderDokumen.folderId}','',
                                                  '${actionBean.permohonan.idPermohonan}','IH${line.jenisSempadan}','${actionBean.laporanTanah.idLaporan}','',${line.idLaporTanahSpdn});return false;" height="30" width="30" alt="Muat Naik"
                                              onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen Gambar Hakmilik"/>--%>
                                        <br>
                                        <c:set value="1" var="count"/>
                                        <c:if test="${line.jenisSempadan eq 'U'}"><c:set value="${actionBean.utaraImejLaporanList}" var="imejSempadanList"/></c:if>
                                        <c:if test="${line.jenisSempadan eq 'S'}"><c:set value="${actionBean.selatanImejLaporanList}" var="imejSempadanList"/></c:if>
                                        <c:if test="${line.jenisSempadan eq 'T'}"><c:set value="${actionBean.timurImejLaporanList}" var="imejSempadanList"/></c:if>
                                        <c:if test="${line.jenisSempadan eq 'B'}"><c:set value="${actionBean.baratImejLaporanList}" var="imejSempadanList"/></c:if>
                                        <c:forEach items="${imejSempadanList}" var="senarai">
                                            <c:if test="${line.idLaporTanahSpdn eq senarai.dokumen.perihal}">
                                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                     onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.tajuk}"/> <font size="1">${count}-${senarai.dokumen.tajuk}</font>
                                                <%--/
                                                <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                     onclick="removeImejTanahSekelilingList('${senarai.idImej}','${senarai.dokumen.idDokumen}','IH${line.jenisSempadan}');" height="15" width="15" alt="Hapus"
                                                     onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen ${senarai.dokumen.kodDokumen.nama}"/>--%>
                                                <br/>
                                                <c:set value="${count+1}" var="count"/>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </display:column>
                            </display:table>
                        </div>
                    </div>
                </c:if>

            </fieldset>
        </div>
    </c:if>





    <div class="subtitle">

        <fieldset class="aras1">

            <c:if test="${edit}">
                <c:if test="${actionBean.statusUlasan eq false}">
                    <legend>Ulasan</legend>
                    <p>
                        <label><font style="text-transform: capitalize">Syor ${actionBean.pengguna.jawatan} : </font></label>
                        <s:textarea name="ulasanPPT" id="ulasanPPT" cols="70" rows="10" class="normal_text"/>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:button name="simpanLaporanTanah1" id="save" value="Simpan" class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');"/>
                    </p>
                </c:if>
            </c:if>
            <c:if test="${!edit && !syorPPTK && !syorPPTD}">
                <c:if test="${actionBean.mohonLaporUlasPPT ne null || actionBean.mohonLaporUlasPPTK ne null}"><legend>Ulasan</legend></c:if>
                    <table>
                    <c:if test="${actionBean.mohonLaporUlasPPT ne null}">
                        <tr>
                            <td><p><label>Syor ${actionBean.mohonLaporUlasPPT.kodPeranan.nama} :</label></c:if></p>
                                <table>
                                    <td><p align="justify" style="text-transform: uppercase"><c:if test="${actionBean.ulasanPPT ne null}"> ${actionBean.ulasanPPT}&nbsp;
                                            <c:if test="${actionBean.ulasanPPT eq null}"> Tiada </c:if></p></td>
                                    </table>
                                </td>
                            </tr>
                    </c:if>
                    <c:if test="${actionBean.mohonLaporUlasPPTK ne null}">
                        <tr>
                            <td>
                                <p>
                                    <label>Syor ${actionBean.mohonLaporUlasPPTK.kodPeranan.nama} :</label>
                                </p>
                                <table>
                                    <td><p align="justify" style="text-transform: uppercase"><c:if test="${actionBean.ulasanPPTK ne null}"> ${actionBean.ulasanPPTK}&nbsp; </c:if>
                                            <c:if test="${actionBean.ulasanPPTK eq null}"> Tiada </c:if></p></td>
                                    </table>


                                </td>
                            </tr>
                    </c:if>
                </table>


            </c:if>
            <c:if test="${syorPPTK}">
                <legend>Ulasan</legend>
                <c:if test="${actionBean.mohonLaporUlasPPT ne null}">
                    <p>
                        <label>Syor ${actionBean.mohonLaporUlasPPT.kodPeranan.nama} :</label>
                    </p>
                    <c:if test="${actionBean.ulasanPPT ne null}">
                        <table>
                            <tr><p align="justify" style="text-transform: uppercase"/>
                            <td valign="top">
                                <font size="2px;">${actionBean.ulasanPPT}&nbsp;</font></td>
                            </tr>
                        </table>
                    </c:if>
                    <c:if test="${actionBean.ulasanPPT eq null}"> Tiada </c:if>
                    <%--  <table><tr>
                              <td><p><label>Syor ${actionBean.mohonLaporUlasPPT.kodPeranan.nama} :</label></p>
                                  <table>
                                      <td><p align="justify" style="text-transform: uppercase"><c:if test="${actionBean.ulasanPPT ne null}"> ${actionBean.ulasanPPT}&nbsp; </c:if>
                                              <c:if test="${actionBean.ulasanPPT eq null}"> Tiada </c:if></p></td>
                                  </table>
                              </td>
                          </tr>
                      </table>--%>
                </c:if>
                <p>
                    <label><font style="text-transform: capitalize">Syor ${actionBean.pengguna.jawatan} : </font></label>
                    <s:textarea name="ulasanPPTK" cols="70" rows="10" class="normal_text"/>
                </p>

                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpanUlasanPPTK" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>

            </c:if>
            <c:if test="${syorPPTD}">
                <legend>Ulasan</legend>

                <p>
                    <label>Syor Penolong Pegawai Tanah Daerah :</label>
                    <s:textarea name="ulasanPPTK" cols="70" rows="10" class="normal_text"/>

                </p>

                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpanUlasanPPTK" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>

            </c:if>

        </fieldset>
    </div>
</s:form>



