<%--
    Document   : pihak_berkepentingan_popup
    Created on : Jul 19, 2010, 11:53:02 AM
    Author     : muhammad.amin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ageCalculator.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $('.alphanumeric').alphanumeric();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $('#jenisPengenalan').change(function(){
            dodacheck($("#kp").val());
        });
        var v = '${actionBean.pihak.jenisPengenalan.kod}';

        if(v == "B" || v == "L" || v == "P" || v == "T" || v == "I" || v == "F"){
            $('#kodBangsa').hide();
            $('#kodBangsaOrang').show();
            $('#kodBangsaAdat').show();
            $('#kodBangsaSyarikat').hide();
            $('#tableCaw').hide();
            $('#btnCaw').hide();
            $('#btnAhli').hide();
        }

        else{
            $('#kodBangsa').hide();
            $('#kodBangsaOrang').hide();
            $('#kodBangsaAdat').hide();
            $('#kodBangsaSyarikat').show();
            $('#tableCaw').show();
            $('#btnCaw').show();
            $('#btnAhli').show();
        }

        if(v == ''){
            $('#kodBangsa').show();
            $('#kodBangsaOrang').hide();
            $('#kodBangsaAdat').hide();
            $('#kodBangsaSyarikat').hide();
            $('#tableCaw').hide();
            $('#btnCaw').hide();
            $('#btnAhli').hide();
        }

        if(v == "S"){
            $('#suku').hide();
            $('#subSuku').hide();
            $('#perut').hide();
            $('#luak').hide();
        }

        else{
            $('#suku').show();
            $('#subSuku').show();
            $('#perut').show();
            $('#luak').show();
        }

        $('form').submit(function(){
            var val = $('#kp').val();
            var jenis = '${jenis}';
            return doCheckUmur(val, 'kp',jenis);
        });

        if(v == "B"){
            var icNo = '${actionBean.pihak.noPengenalan}';

            var year = icNo.substring(0,2);

            if(year > 25 && year < 99){//fixme :
                year = "19" + year;
            }else {
                year = "20" + year;
            }

            if( $('#kp').val() != ''){
                var age = calculateAge(icNo.substring(4,6), icNo.substring(2,4), year);
                $('#umur').val(age);
                $('#datepicker').val(icNo.substring(4,6)+"/"+icNo.substring(2,4)+"/"+year);
            }
        }

        $('#kod_warganegara').val('MAL');
        $('#kod_warga_pengarah').val('MAL');

        var jenisPihak = $('#jenis_pihak').val();

        if(jenisPihak == "Pemegang Amanah" || jenisPihak == "PA"){
            $('#pgAmanah').show();
        }else{
            $('#pgAmanah').hide();
        }

        var hubungan = $('#hubungan').val();
        hubungan = hubungan.toUpperCase();
        if(hubungan == "LAIN-LAIN"){
            $('#hubunganLain').show();
        }else{
            $('#hubunganLain').hide();
        }

        var tempatLahir = $('#tempatLahir').val();
        tempatLahir = tempatLahir.toUpperCase();
        if(tempatLahir == "LAIN-LAIN"){
            $('#tempatLahirLain').show();
        }else{
            $('#tempatLahirLain').hide();
        }

        var amanah2 = $('#namaAmanah2').val();
        var amanah3 = $('#namaAmanah3').val();
        var amanah4 = $('#namaAmanah4').val();
        var amanah5 = $('#namaAmanah5').val();
        var amanah6 = $('#namaAmanah6').val();
        var amanah7 = $('#namaAmanah7').val();
        var amanah8 = $('#namaAmanah8').val();
        var amanah9 = $('#namaAmanah9').val();
        var amanah10 = $('#namaAmanah10').val();

        if(amanah2 == ''){
            $('#divAmanah2').hide();
        }
        if(amanah3 == ''){
            $('#divAmanah3').hide();
        }
        if(amanah4 == ''){
            $('#divAmanah4').hide();
        }
        if(amanah5 == ''){
            $('#divAmanah5').hide();
        }
        if(amanah6 == ''){
            $('#divAmanah6').hide();
        }
        if(amanah7 == ''){
            $('#divAmanah7').hide();
        }
        if(amanah8 == ''){
            $('#divAmanah8').hide();
        }
        if(amanah9 == ''){
            $('#divAmanah9').hide();
        }
        if(amanah10 == ''){
            $('#divAmanah10').hide();
        }

    });

    function calAgeByDOB(value){

        var year = value.substring(8,10);
        
        if(year > 25 && year < 99){//fixme :
            year = "19" + year;
        }else {
            year = "20" + year;
        }

        var age = calculateAge(value.substring(0,2), value.substring(3,5), year);
        $('#umur').val(age);
    }

    function changeHideSuku(value){
        if(value == "S"){
            $('#suku').hide();
            $('#subSuku').hide();
            $('#perut').hide();
            $('#luak').hide();
        }else{
            $('#suku').show();
            $('#subSuku').show();
            $('#perut').show();
            $('#luak').show();
        }
    }

    function changePgAmanah(value){
        if(value == "Pemegang Amanah"){
            $('#pgAmanah').show();
        }else{
            $('#pgAmanah').hide();
        }
    }

    function tambahAmanah(value){
        if(value == "1"){
            $('#divAmanah1').show();
            $('#divAmanah2').show();
            $('#divAmanah3').hide();
            $('#divAmanah4').hide();
            $('#divAmanah5').hide();
            $('#divAmanah6').hide();
            $('#divAmanah7').hide();
            $('#divAmanah8').hide();
            $('#divAmanah9').hide();
            $('#divAmanah10').hide();
            $('#btnTambah').hide();
            btnTambah
        }
        if(value == "2"){
            $('#divAmanah1').show();
            $('#divAmanah2').show();
            $('#divAmanah3').show();
            $('#divAmanah4').hide();
            $('#divAmanah5').hide();
            $('#divAmanah6').hide();
            $('#divAmanah7').hide();
            $('#divAmanah8').hide();
            $('#divAmanah9').hide();
            $('#divAmanah10').hide();
            $('#btnTambah2').hide();
        }
        if(value == "3"){
            $('#divAmanah1').show();
            $('#divAmanah2').show();
            $('#divAmanah3').show();
            $('#divAmanah4').show();
            $('#divAmanah5').hide();
            $('#divAmanah6').hide();
            $('#divAmanah7').hide();
            $('#divAmanah8').hide();
            $('#divAmanah9').hide();
            $('#divAmanah10').hide();
            $('#btnTambah3').hide();
        }
        if(value == "4"){
            $('#divAmanah1').show();
            $('#divAmanah2').show();
            $('#divAmanah3').show();
            $('#divAmanah4').show();
            $('#divAmanah5').show();
            $('#divAmanah6').hide();
            $('#divAmanah7').hide();
            $('#divAmanah8').hide();
            $('#divAmanah9').hide();
            $('#divAmanah10').hide();
            $('#btnTambah4').hide();
        }
        if(value == "5"){
            $('#divAmanah1').show();
            $('#divAmanah2').show();
            $('#divAmanah3').show();
            $('#divAmanah4').show();
            $('#divAmanah5').show();
            $('#divAmanah6').show();
            $('#divAmanah7').hide();
            $('#divAmanah8').hide();
            $('#divAmanah9').hide();
            $('#divAmanah10').hide();
            $('#btnTambah5').hide();
        }
        if(value == "6"){
            $('#divAmanah1').show();
            $('#divAmanah2').show();
            $('#divAmanah3').show();
            $('#divAmanah4').show();
            $('#divAmanah5').show();
            $('#divAmanah6').show();
            $('#divAmanah7').show();
            $('#divAmanah8').hide();
            $('#divAmanah9').hide();
            $('#divAmanah10').hide();
            $('#btnTambah6').hide();
        }
        if(value == "7"){
            $('#divAmanah1').show();
            $('#divAmanah2').show();
            $('#divAmanah3').show();
            $('#divAmanah4').show();
            $('#divAmanah5').show();
            $('#divAmanah6').show();
            $('#divAmanah7').show();
            $('#divAmanah8').show();
            $('#divAmanah9').hide();
            $('#divAmanah10').hide();
            $('#btnTambah7').hide();
        }
        if(value == "8"){
            $('#divAmanah1').show();
            $('#divAmanah2').show();
            $('#divAmanah3').show();
            $('#divAmanah4').show();
            $('#divAmanah5').show();
            $('#divAmanah6').show();
            $('#divAmanah7').show();
            $('#divAmanah8').show();
            $('#divAmanah9').show();
            $('#divAmanah10').hide();
            $('#btnTambah8').hide();
        }
        if(value == "9"){
            $('#divAmanah1').show();
            $('#divAmanah2').show();
            $('#divAmanah3').show();
            $('#divAmanah4').show();
            $('#divAmanah5').show();
            $('#divAmanah6').show();
            $('#divAmanah7').show();
            $('#divAmanah8').show();
            $('#divAmanah9').show();
            $('#divAmanah10').show();
            $('#btnTambah9').hide();
        }
    }

    function hapusAmanah(value){

        if(value == "2"){
            $('#divAmanah2').hide();
            $('#btnTambah').show();
            $('#namaAmanah2').val('');
            $('#kpAmanah2').val('');
            $('#nokpAmanah2').val('');
            $('#alamatAmanah12').val('');
            $('#alamatAmanah22').val('');
            $('#alamatAmanah32').val('');
            $('#alamatAmanah42').val('');
            $('#poskodAmanah2').val('');
            $('#negeriAmanah2').val('');
        }
        if(value == "3"){
            $('#divAmanah3').hide();
            $('#btnTambah2').show();
            $('#namaAmanah3').val('');
            $('#kpAmanah3').val('');
            $('#nokpAmanah3').val('');
            $('#alamatAmanah13').val('');
            $('#alamatAmanah23').val('');
            $('#alamatAmanah33').val('');
            $('#alamatAmanah43').val('');
            $('#poskodAmanah3').val('');
            $('#negeriAmanah3').val('');
        }
        if(value == "4"){
            $('#divAmanah4').hide();
            $('#btnTambah3').show();
            $('#namaAmanah4').val('');
            $('#kpAmanah4').val('');
            $('#nokpAmanah4').val('');
            $('#alamatAmanah14').val('');
            $('#alamatAmanah24').val('');
            $('#alamatAmanah34').val('');
            $('#alamatAmanah44').val('');
            $('#poskodAmanah4').val('');
            $('#negeriAmanah4').val('');
        }
        if(value == "5"){
            $('#divAmanah5').hide();
            $('#btnTambah4').show();
            $('#namaAmanah5').val('');
            $('#kpAmanah5').val('');
            $('#nokpAmanah5').val('');
            $('#alamatAmanah15').val('');
            $('#alamatAmanah25').val('');
            $('#alamatAmanah35').val('');
            $('#alamatAmanah45').val('');
            $('#poskodAmanah5').val('');
            $('#negeriAmanah5').val('');
        }
        if(value == "6"){
            $('#divAmanah6').hide();
            $('#btnTambah5').show();
            $('#namaAmanah6').val('');
            $('#kpAmanah6').val('');
            $('#nokpAmanah6').val('');
            $('#alamatAmanah16').val('');
            $('#alamatAmanah26').val('');
            $('#alamatAmanah36').val('');
            $('#alamatAmanah46').val('');
            $('#poskodAmanah6').val('');
            $('#negeriAmanah6').val('');
        }
        if(value == "7"){
            $('#divAmanah7').hide();
            $('#btnTambah6').show();
            $('#namaAmanah').val('');
            $('#kpAmanah7').val('');
            $('#nokpAmanah7').val('');
            $('#alamatAmanah17').val('');
            $('#alamatAmanah27').val('');
            $('#alamatAmanah37').val('');
            $('#alamatAmanah47').val('');
            $('#poskodAmanah7').val('');
            $('#negeriAmanah7').val('');
        }
        if(value == "8"){
            $('#divAmanah8').hide();
            $('#btnTambah7').show();
            $('#namaAmanah8').val('');
            $('#kpAmanah8').val('');
            $('#nokpAmanah8').val('');
            $('#alamatAmanah18').val('');
            $('#alamatAmanah28').val('');
            $('#alamatAmanah38').val('');
            $('#alamatAmanah48').val('');
            $('#poskodAmanah8').val('');
            $('#negeriAmanah8').val('');
        }
        if(value == "9"){
            $('#divAmanah9').hide();
            $('#btnTambah8').show();
            $('#namaAmanah9').val('');
            $('#kpAmanah9').val('');
            $('#nokpAmanah9').val('');
            $('#alamatAmanah19').val('');
            $('#alamatAmanah29').val('');
            $('#alamatAmanah39').val('');
            $('#alamatAmanah49').val('');
            $('#poskodAmanah9').val('');
            $('#negeriAmanah9').val('');
        }
        if(value == "10"){
            $('#divAmanah10').hide();
            $('#btnTambah9').show();
            $('#namaAmanah10').val('');
            $('#kpAmanah10').val('');
            $('#nokpAmanah10').val('');
            $('#alamatAmanah110').val('');
            $('#alamatAmanah210').val('');
            $('#alamatAmanah310').val('');
            $('#alamatAmanah410').val('');
            $('#poskodAmanah10').val('');
            $('#negeriAmanah10').val('');
        }
    }

    function doValidation(){
        var val = $('#kod_warganegara').val();
        var val2 = $('#alamat1').val();
        var val3 = $('#nama_pemohon').val();
        var val4 = $('#jenis_pihak').val();
        var valueJenisKP = $('#jenisKP').val();
        var valueNoKP = $('#kp').val();
        if(val3 == ''){
            alert('Sila masukan nama pihak.');
            return false;
        }
        else if(valueJenisKP == ''){
            alert('Sila masukkan Jenis pengenalan.');
            return false;
        }
        else if(valueNoKP == ''){
            alert('Sila masukkan nombor pengenalan.');
            return false;
        }
        else if(val == ''){
            alert('Sila pilih warganegara.');
            return false;
        }else if(val2 == ''){
            alert('Sila masukan alamat berdaftar.');
            return false;
        }else if (val4 == ''){
            alert('Sila Masukan Jenis Pihak Berkepentingan.');
            return false;
        }

        if(val4 == "PA"){
            var val5 = $('#namaAmanah').val();
            var val6 = $('#kpAmanah').val();
            var val7 = $('#nokpAmanah').val();
            var val8 = $('#alamatAmanah1').val();

            if(val5 == '' || val6 == '' || val7 == '' || val8 == ''){
                alert('Sila masukan maklumat penerima amanah dengan lengkap.');
                return false;
            }
        }
        return true;
    }

    function changeHubungan(value){
        value = value.toUpperCase();
        if(value == "LAIN-LAIN"){
            $('#hubunganLain').show();
        }
        else{
            $('#hubunganLain').hide();
        }
    }

    function changeTempatLahir(value){
        value = value.toUpperCase();
        if(value == "LAIN-LAIN"){
            $('#tempatLahirLain').show();
        }
        else{
            $('#tempatLahirLain').hide();
        }
    }

    function validationPB(){
        if ($('#jenis_pihak').val() == ''){
            alert('Sila Masukan Jenis Pihak Berkepentingan.');
            return false;
        }

        else  if ($('#alamat1').val() == ''){
            alert('Sila Masukan Alamat Berdaftar.');
            return false;
        }
        return true;
    }

    function save(event, f){
        if(doValidation()){
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
    <c:choose>
        <c:when test="${multiple}">
                        $('#div_content',opener.document).html(data);
        </c:when>
        <c:otherwise>
                        $('#page_div',opener.document).html(data);
                        if (data == '1') {
                            $('#page_div',opener.document).html('Terdapat masalah berlaku.');
                        }else {
                            $('#page_div',opener.document).html(data);
                        }
        </c:otherwise>
    </c:choose>
                    self.close();
                },'html');
            }

        }

        function savePemohon(event, f){
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
    <c:choose>
        <c:when test="${multiple}">
                    $('#div_content',opener.document).html(data);
        </c:when>
        <c:otherwise>
                    if (data == '1') {
                        $('#page_div',opener.document).html('Terdapat masalah berlaku.');
                    }else {
                        $('#page_div',opener.document).html(data);
                    }
        </c:otherwise>
    </c:choose>
                self.close();
            },'html');
        }

        function changeHideSuku(value){

            if(value == "S"){
                $('#suku').hide();
                $('#subSuku').hide();
                $('#perut').hide();
                $('#luak').hide();
            }

            else{
                $('#suku').show();
                $('#subSuku').show();
                $('#perut').show();
                $('#luak').show();
            }
        }

        function changeJenisKP(value){
            if(value == "B" || value == "L" || value == "P" || value == "T" || value == "I" || value == "F"){
                $('#kodBangsa').hide();
                $('#kodBangsaOrang').show();
                $('#kodBangsaAdat').show();
                $('#kodBangsaSyarikat').hide();
                $('#tableCaw').hide();
                $('#btnCaw').hide();
                $('#btnAhli').hide();
            }

            else{
                $('#kodBangsa').hide();
                $('#kodBangsaOrang').hide();
                $('#kodBangsaAdat').hide();
                $('#kodBangsaSyarikat').show();
                $('#tableCaw').show();
                $('#btnCaw').show();
                $('#btnAhli').show();
            }
        }

        function copyAddCaw(){
            if($('input[name=addCaw]').is(':checked')){
                $('#suratAlamatCaw1').val($('#alamatCaw1').val());
                $('#suratAlamatCaw2').val($('#alamatCaw2').val());
                $('#suratAlamatCaw3').val($('#alamatCaw3').val());
                $('#suratAlamatCaw4').val($('#alamatCaw4').val());
                $('#suratPoskodCaw').val($('#poskodCaw').val());
                $('#suratNegeriCaw').val($('#negeriCaw').val());
            }else{
                $('#suratAlamatCaw1').val('');
                $('#suratAlamatCaw2').val('');
                $('#suratAlamatCaw3').val('');
                $('#suratAlamatCaw4').val('');
                $('#suratPoskodCaw').val('');
                $('#suratNegeriCaw').val('');

            }
        }

        function copyAdd(){
            if($('input[name=add1]').is(':checked')){
                $('#suratAlamat1').val($('#alamat1').val());
                $('#suratAlamat2').val($('#alamat2').val());
                $('#suratAlamat3').val($('#alamat3').val());
                $('#suratAlamat4').val($('#alamat4').val());
                $('#suratPoskod').val($('#poskod').val());
                $('#suratNegeri').val($('#negeri').val());
            }else{
                $('#suratAlamat1').val('');
                $('#suratAlamat2').val('');
                $('#suratAlamat3').val('');
                $('#suratAlamat4').val('');
                $('#suratPoskod').val('');
                $('#suratNegeri').val('');

            }
        }

        function removeCawangan(val){
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/consent/pihak_consent?deleteCawangan&idCawangan='+val;
                $.get(url,
                function(data){
                    $('#caw').html(data);
                },'html');
                location.reload(true);
            }
        }

        function removeCawanganPemohon(val){
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/consent/pihak_consent?deleteCawanganPemohon&idCawangan='+val;
                $.get(url,
                function(data){
                    $('#caw').html(data);
                },'html');
                location.reload(true);
            }
        }

        function removePengarah(val){
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/consent/pihak_consent?deletePengarah&idPengarah='+val;
                $.get(url,
                function(data){
                    $('#caw').html(data);
                },'html');
                location.reload(true);
            }
        }

        function removePengarahPemohon(val){
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/consent/pihak_consent?deletePengarahPemohon&idPengarah='+val;
                $.get(url,
                function(data){
                    $('#caw').html(data);
                },'html');
                location.reload(true);
            }
        }


        function editCawangan(val){
            var jenisPB = $('#jenis_pihak').val();
            var url = '${pageContext.request.contextPath}/consent/pihak_consent?editCawangan&idCawangan='+val+'&jenisPB='+jenisPB;
            $.get(url,
            function(data){
                $('#caw').html(data);
            },'html');
        }

        function editCawanganPemohon(val){
            var url = '${pageContext.request.contextPath}/consent/pihak_consent?editCawanganPemohon&idCawangan='+val;
            $.get(url,
            function(data){
                $('#caw').html(data);
            },'html');
        }

        function editPengarah(val){
            var jenisPB = $('#jenis_pihak').val();
            var url = '${pageContext.request.contextPath}/consent/pihak_consent?editPengarah&idPengarah='+val+'&jenisPB='+jenisPB;
            $.get(url,
            function(data){
                $('#caw').html(data);
            },'html');
        }

        function editPengarahPemohon(val){
            var url = '${pageContext.request.contextPath}/consent/pihak_consent?editPengarahPemohon&idCawangan='+val;
            $.get(url,
            function(data){
                $('#caw').html(data);
            },'html');
        }

        function selectName(val){
            var url = '${pageContext.request.contextPath}/consent/pihak_consent?selectName&idPihak='+val+'&hakmilik='+ $('#hakmilik').val();
            $.get(url,
            function(data){
                $('#caw').html(data);
            },'html');
        }

        function selectNamePemohon(val){
            var url = '${pageContext.request.contextPath}/consent/pihak_consent?selectNamePemohon&idPihak='+val;
            $.get(url,
            function(data){
                $('#caw').html(data);
            },'html');
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

        function dodacheck(val) {
            //var mikExp = /[1\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\1\|]/;
            var v = $('#jenisPengenalan').val();

            if(v == 'B') {
                var strPass = val;
                var strLength = strPass.length;
                //$('#kp').attr("maxlength","12");
                if(strLength > 12) {
                    var tst = val.substring(0, (strLength) - 1);
                    $('#kp').val(tst);
                }
                var lchar = val.charAt((strLength) - 1);
                if(isNaN(lchar)) {
                    //return false;
                    var tst = val.substring(0, (strLength) - 1);
                    $('#kp').val(tst);
                }
            }//else{
            // $('#kp').attr("maxlength","30");
            // }
        }

        function doCheckUmur(v,id,type){
            var va = $('#jenisPengenalan').val();
            if(va == 'B'){
                return doValidateAge(v, id, 'jenisPengenalan',type);
            }else {
                return true;
            }
        }

</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<div class="subtitle" id="caw">
    <s:form beanclass="etanah.view.stripes.consent.PihakConsentActionBean" >
        <s:hidden name="jenisPihak"/>
        <s:hidden name="pihak.nama"/>
        <s:hidden name="pihak.idPihak"/>
        <%--<s:hidden name="pihak.jenisPengenalan.kod"/>--%>
        <%--<s:hidden name="pihak.jenisPengenalan.nama"/>--%>
        <%--<s:hidden name="pihak.noPengenalan"/>--%>
        <%--    <s:hidden name="pihak.bangsa.kod"/>
            <s:hidden name="pihak.suku.kod"/>--%>
        <s:hidden name="permohonanPihak.jenis.kod"/>
        <s:hidden name="pihakCawangan.idPihakCawangan"/>
        <s:hidden name="jenisPihak" value="${jenis}"/>
        <s:hidden name="hakmilik" value="${hakmilik}" id="hakmilik"/>

        <c:if test="${tuanTanah}">
            <s:text name="tuanTanah" value="${tuanTanah}"/>
        </c:if>
        <s:errors/>
        <s:messages/>
        <c:if test="${!actionBean.tambahCawFlag && !actionBean.tambahPengarahFlag}">

            <fieldset class="aras1">
                <legend>
                    <c:if test="${pemohon}">
                        Kemasukan Maklumat Pemohon
                    </c:if>

                    <c:if test="${!pemohon}">
                        <c:choose>
                            <c:when test="${actionBean.p.kodUrusan.kod eq 'PMMAT' || actionBean.p.kodUrusan.kod eq 'PMPTD' || actionBean.p.kodUrusan.kod eq 'PJKTL' || actionBean.p.kodUrusan.kod eq 'PMJTL'}">
                                Kemasukan Maklumat Penerima Pindah Milik
                            </c:when>
                            <c:when test="${actionBean.p.kodUrusan.kod eq 'PJPTD' || actionBean.p.kodUrusan.kod eq 'PJMMK'}">
                                Kemasukan Maklumat Penerima Pajakan
                            </c:when>
                            <c:when  test="${actionBean.p.kodUrusan.kod eq 'MCPTD' || actionBean.p.kodUrusan.kod eq 'MCGDMB' || actionBean.p.kodUrusan.kod eq 'MCMMK'}">
                                Kemasukan Maklumat Penerima Gadaian
                            </c:when>
                            <c:when test="${actionBean.p.kodUrusan.kod eq 'BTADT'}">
                                Kemasukan Maklumat Pihak Yang Menuntut
                            </c:when>
                            <c:when test="${actionBean.p.kodUrusan.kod eq 'PAADT'}">
                                Kemasukan Maklumat Pemegang Amanah
                            </c:when>
                            <c:otherwise>
                                Kemasukan Maklumat Penerima
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </legend>
                <br/>

                <c:if test="${!actionBean.cariFlag}">
                    <c:if test="${actionBean.kodNegeri eq '05'}">
                        <p>
                            <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                            <s:select name="pihak.jenisPengenalan.kod" id="jenisPengenalan"  onchange="javaScript:changeHideSuku(this.options[selectedIndex].text);">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                    </c:if>

                    <c:if test="${actionBean.kodNegeri eq '04'}">
                        <p>
                            <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                            <s:select name="pihak.jenisPengenalan.kod" id="jenisPengenalan">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                    </c:if>

                    <p>
                        <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                        <s:text name="pihak.noPengenalan" id="kp" size="40" onkeyup="dodacheck(this.value);this.value=this.value.toUpperCase();"
                                onblur="doCheckUmur(this.value, this.id,'${jenis}')"/>&nbsp;<em style="font-style:normal">(cth : 800620125177)</em>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        atau
                    </p>
                    <p>
                        <label for="nama"><font color="red">*</font>Nama :</label>
                        <s:text name="pihak.nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <c:if test="${fn:length(actionBean.pihakByNameList) > 0}">
                        <div align="center">
                            <display:table class="tablecloth" name="${actionBean.pihakByNameList}" cellpadding="0" cellspacing="0" id="line" pagesize="10"
                                           requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                <display:column  title="Nama" class="nama">
                                    <c:if test="${!pemohon}">
                                        <a href="#" onclick="selectName('${line.idPihak}');return false;">${line.nama}</a>
                                    </c:if>
                                    <c:if test="${pemohon}">
                                        <a href="#" onclick="selectNamePemohon('${line.idPihak}');return false;">${line.nama}</a>
                                    </c:if>
                                </display:column>
                                <display:column property="jenisPengenalan.nama" title="Jenis Pengenalan" />
                                <display:column property="noPengenalan" title="Nombor Pengenalan" />
                            </display:table>
                        </div>
                    </c:if>
                </c:if>

                <c:if test="${actionBean.cariFlag}">

                    <c:if test="${!actionBean.tiadaDataFlag}">
                        <p>
                            <label for="nama">Nama :</label>
                            ${actionBean.pihak.nama}&nbsp;
                        </p>
                        <c:if test="${actionBean.kodNegeri eq '05'}">
                            <p>
                                <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                <s:select name="pihak.jenisPengenalan.kod" onchange="javaScript:changeHideSuku(this.options[selectedIndex].text)">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                        </c:if>

                        <c:if test="${actionBean.kodNegeri eq '04'}">
                            <p>
                                <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                <s:select name="pihak.jenisPengenalan.kod" id="jenisKP" onchange="javaScript:changeJenisKP(this.options[selectedIndex].value)">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                        </c:if>

                        <p>
                            <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                            <s:text name="pihak.noPengenalan" id="kp" size="40" onkeyup="dodacheck(this.value);this.value=this.value.toUpperCase();"/>&nbsp;<em style="font-style:normal">(cth : 800620125177)</em>
                        </p>
                        <p>
                            <label>Nombor Pengenalan Lain :</label>
                            <s:text name="pihak.noPengenalanLain" id="kpl" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Bangsa/Jenis Syarikat :</label>
                            <c:if test="${actionBean.p.kodUrusan.kod ne 'PMADT' && actionBean.p.kodUrusan.kod ne 'CGADT' && actionBean.p.kodUrusan.kod ne 'PAADT' && actionBean.p.kodUrusan.kod ne 'TMADT' && actionBean.p.kodUrusan.kod ne 'BTADT'}">
                                <s:select name="pihak.bangsa.kod" style="width:200px" id="kodBangsaOrang">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${actionBean.senaraiKodBangsaOrang}" label="nama" value="kod"/>
                                </s:select>
                            </c:if>
                            <c:if test="${actionBean.p.kodUrusan.kod eq 'PMADT' || actionBean.p.kodUrusan.kod eq 'CGADT' || actionBean.p.kodUrusan.kod eq 'PAADT' || actionBean.p.kodUrusan.kod eq 'TMADT' || actionBean.p.kodUrusan.kod eq 'BTADT'}">
                                <s:select name="pihak.bangsa.kod" style="width:200px" id="kodBangsaAdat">
                                    <s:option value="MEL">MELAYU</s:option>
                                </s:select>
                            </c:if>
                            <s:select name="pihak.bangsa.kod" style="width:400px" id="kodBangsaSyarikat">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraiKodBangsaSyarikat}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <c:if test="${actionBean.kodNegeri eq '05'}">
                            <p id="suku">
                                <label for="Suku">Jenis Suku :</label>
                                <s:select name="pihak.suku.kod" style="width:200px">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodSuku}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                            <p id="subSuku">
                                <label for="subSuku">Pecahan Suku/Lengkongan :</label>
                                <s:text name="pihak.subSuku" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p id="perut">
                                <label for="perut">Perut :</label>
                                <s:text name="pihak.keturunan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p id="luak">
                                <label for="luak">Luak :</label>
                                <s:text name="pihak.tempatSuku" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                        </c:if>

                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                            <p>
                                <label>Tarikh Lahir :</label>
                                <s:text name="tarikhLahir" size="40" id="datepicker" class="datepicker" onchange="calAgeByDOB(this.value);"/>&nbsp;<em style="font-style:normal">(cth : hh / bb / tttt)</em>
                            </p>
                            <p>
                                <label>Umur :</label>
                                <s:text name="permohonanPihak.umur" size="10" maxlength="3" id="umur" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Tempat Lahir :</label>
                                <s:select name="pihak.tempatLahir" id="tempatLahir" onchange="javaScript:changeTempatLahir(this.options[selectedIndex].text);">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="nama"/>
                                    <s:option value="LAIN-LAIN">Lain-lain</s:option>
                                </s:select>
                            </p>
                            <p id="tempatLahirLain">
                                <label>Lain-lain Tempat Lahir :</label>
                                <s:text name="tempatLahirLain" size="40" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label> <font color="red">*</font>Kewarganegaraan :</label>
                                <s:hidden name="pihak.wargaNegara.kod" id=""/>
                                <s:select name="pihak.wargaNegara.kod" style="width:200px" id="kod_warganegara">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                            <p>
                                <label>Status Perkahwinan :</label>
                                <s:select name="permohonanPihak.statusKahwin">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option>Berkahwin</s:option>
                                    <s:option>Bujang</s:option>
                                    <s:option>Duda</s:option>
                                    <s:option>Ibu Tunggal</s:option>
                                </s:select>
                            </p>
                            <c:if test="${actionBean.p.kodUrusan.kod ne 'PMADT' && actionBean.p.kodUrusan.kod ne 'CGADT' && actionBean.p.kodUrusan.kod ne 'PAADT' && actionBean.p.kodUrusan.kod ne 'TMADT' && actionBean.p.kodUrusan.kod ne 'BTADT'}">
                                <p>
                                    <label>Pekerjaan :</label>
                                    <s:text name="permohonanPihak.pekerjaan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                                </p>
                                <p>
                                    <label>Pendapatan Bulanan (RM) :</label>
                                    <s:text name="permohonanPihak.pendapatan" size="40" maxlength="12" onkeyup="validateNumber(this,this.value);"/>
                                </p>
                                <p>
                                    <label>Tanggungan :</label>
                                    <s:text name="permohonanPihak.tangungan" size="40" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                                </p>

                            </c:if>
                            <c:if test="${pemohon}">
                                <c:if test="${actionBean.kodNegeri eq '04' && actionBean.p.kodUrusan.kod eq 'PMMMK'}">
                                    <p>
                                        <label><font color="red">*</font>Jantina :</label>
                                        <s:select name="pihak.kodJantina" style="width:200px">
                                            <s:option value="">Sila Pilih</s:option>
                                            <s:options-collection collection="${list.senaraiKodJantina}" label="nama" value="kod"/>
                                        </s:select>
                                    </p>
                                    <p>
                                        <label><font color="red">*</font>Tempoh Mastautin (Tahun) :</label>
                                        <s:text name="pemohon.tempohMastautin" size="40" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                                    </p>

                                </c:if>
                            </c:if>
                        </c:if>
                        <p>
                            <label>
                                <c:if test="${pemohon}">
                                    <c:if test="${actionBean.p.kodUrusan.kod ne 'PMADT' && actionBean.p.kodUrusan.kod ne 'CGADT' && actionBean.p.kodUrusan.kod ne 'PAADT' && actionBean.p.kodUrusan.kod ne 'TMADT' && actionBean.p.kodUrusan.kod ne 'BTADT'}">
                                        Hubungan Dengan Penerima :
                                    </c:if>

                                    <c:if test="${actionBean.p.kodUrusan.kod eq 'PMADT' || actionBean.p.kodUrusan.kod eq 'CGADT' || actionBean.p.kodUrusan.kod eq 'PAADT' || actionBean.p.kodUrusan.kod eq 'TMADT' || actionBean.p.kodUrusan.kod eq 'BTADT'}">
                                        <c:if test="${actionBean.p.kodUrusan.kod eq 'TMADT'}">
                                            Hubungan Dengan Simati :
                                        </c:if>
                                        <c:if test="${actionBean.p.kodUrusan.kod ne 'TMADT'}">
                                            Hubungan Dengan Tuan Tanah :
                                        </c:if>
                                    </c:if>

                                </c:if>
                                <c:if test="${!pemohon}">
                                    <c:if test="${actionBean.p.kodUrusan.kod ne 'PMADT' && actionBean.p.kodUrusan.kod ne 'CGADT' && actionBean.p.kodUrusan.kod ne 'PAADT' && actionBean.p.kodUrusan.kod ne 'TMADT' && actionBean.p.kodUrusan.kod ne 'BTADT'}">
                                        Hubungan Dengan Pemohon :
                                    </c:if>
                                    <c:if test="${actionBean.p.kodUrusan.kod eq 'PMADT' || actionBean.p.kodUrusan.kod eq 'CGADT' || actionBean.p.kodUrusan.kod eq 'PAADT' || actionBean.p.kodUrusan.kod eq 'TMADT' || actionBean.p.kodUrusan.kod eq 'BTADT'}">

                                        <c:if test="${actionBean.p.kodUrusan.kod eq 'TMADT'}">
                                            Hubungan Dengan Simati :
                                        </c:if>
                                        <c:if test="${actionBean.p.kodUrusan.kod ne 'TMADT'}">
                                            Hubungan Dengan Tuan Tanah :
                                        </c:if>
                                    </c:if>
                                </c:if>
                            </label>
                            <c:if test="${actionBean.p.kodUrusan.kod ne 'PMADT' && actionBean.p.kodUrusan.kod ne 'CGADT' && actionBean.p.kodUrusan.kod ne 'PAADT' && actionBean.p.kodUrusan.kod ne 'TMADT' && actionBean.p.kodUrusan.kod ne 'BTADT'}">
                                <s:select name="permohonanPihak.kaitan" id="hubungan" onchange="javaScript:changeHubungan(this.options[selectedIndex].text);">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option>Ibu Bapa Kepada Anak</s:option>
                                    <s:option>Anak Kepada Ibu Bapa</s:option>
                                    <s:option>Suami Kepada Isteri</s:option>
                                    <s:option>Isteri Kepada Suami</s:option>
                                    <s:option>Saudara-mara</s:option>
                                    <s:option>Penjual / Pembeli</s:option>
                                    <s:option>Lain-lain</s:option>
                                </s:select>
                            </c:if>
                            <c:if test="${actionBean.p.kodUrusan.kod eq 'PMADT' || actionBean.p.kodUrusan.kod eq 'CGADT' || actionBean.p.kodUrusan.kod eq 'PAADT' || actionBean.p.kodUrusan.kod eq 'TMADT' || actionBean.p.kodUrusan.kod eq 'BTADT'}">
                                <s:select name="permohonanPihak.kaitan" id="hubungan" onchange="javaScript:changeHubungan(this.options[selectedIndex].text);">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option>Anak</s:option>
                                    <s:option>Adik Beradik Sesuku</s:option>
                                    <s:option>Cucu</s:option>
                                    <s:option>Ibu Kandung</s:option>
                                    <s:option>Ibu Saudara</s:option>
                                    <s:option>Moyang</s:option>
                                    <s:option>Nenek</s:option>
                                    <s:option>Sepupu</s:option>
                                    <s:option>Lain-lain</s:option>
                                </s:select>
                            </c:if>
                        </p>
                        <p id="hubunganLain">
                            <label>Lain-lain Hubungan :</label>
                            <s:text name="hubunganLain" size="40" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <c:if test="${!pemohon}">
                            <p>
                                <label for="nama"><font color="red">*</font>Jenis Pihak Berkepentingan :</label>
                                <s:select name="jenisPihak" style="width:400px" id="jenis_pihak" onchange="javaScript:changePgAmanah(this.options[selectedIndex].text);">
                                    <s:option value="" >Sila Pilih</s:option>
                                    <s:options-collection collection="${actionBean.senaraiKodPenerima}" label="nama" value="kod" />
                                </s:select>
                            </p>
                        </c:if>

                        <p>
                            <label for="alamat"><font color="red">*</font>Alamat Berdaftar :</label>
                            <s:text name="pihak.alamat1" id="alamat1" size="100" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.alamat2" id="alamat2" size="100" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.alamat3" id="alamat3" size="100" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> Bandar :</label>
                            <s:text name="pihak.alamat4" id="alamat4" size="100" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="pihak.poskod" id="poskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select name="pihak.negeri.kod" id="negeri">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <p>
                            <label>&nbsp;</label>
                            <input type="checkbox" id="add1" name="add1" value="" onclick="copyAdd();"/>
                            <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                        </p>
                        <p>
                            <label for="alamat">Alamat Surat-Menyurat :</label>
                            <s:text name="pihak.suratAlamat1" id="suratAlamat1" size="100" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat2" id="suratAlamat2" size="100" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat3" id="suratAlamat3" size="100" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> Bandar :</label>
                            <s:text name="pihak.suratAlamat4" id="suratAlamat4" size="100" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="pihak.suratPoskod" id="suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select name="pihak.suratNegeri.kod" id="suratNegeri">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                    </c:if>
                    <%--tiada data--%>
                    <c:if test="${actionBean.tiadaDataFlag}">
                        <p>
                            <label for="nama"><font color="red">*</font>Nama :</label>
                            <s:text name="pihak.nama" id="nama_pemohon" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>

                        <c:if test="${actionBean.kodNegeri eq '05'}">
                            <p>
                                <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                <s:select name="pihak.jenisPengenalan.kod" id="jenisKP" onchange="javaScript:changeHideSuku(this.options[selectedIndex].text)">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                        </c:if>

                        <c:if test="${actionBean.kodNegeri eq '04'}">
                            <p>
                                <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                <s:select name="pihak.jenisPengenalan.kod" id="jenisKP" onchange="javaScript:changeJenisKP(this.options[selectedIndex].value)">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                        </c:if>

                        <p>
                            <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                            <s:text name="pihak.noPengenalan" id="kp" size="40" onkeyup="dodacheck(this.value);this.value=this.value.toUpperCase();"/>&nbsp;<em style="font-style:normal">(cth : 800620125177)</em>
                        </p>
                        <p>
                            <label>Nombor Pengenalan Lain :</label>
                            <s:text name="pihak.noPengenalanLain" id="kpl" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L'
                                      || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T'
                                      || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                              <p>
                                  <label><font color="red">*</font>Kewarganegaraan :</label>
                                  <s:select name="pihak.wargaNegara.kod" style="width:200px" id="kod_warganegara">
                                      <s:option value="">Sila Pilih</s:option>
                                      <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                                  </s:select>
                              </p>
                        </c:if>
                        <c:if test="${!pemohon}">
                            <p>
                                <label for="nama"><font color="red">*</font>Jenis Pihak Berkepentingan :</label>
                                <s:select name="jenisPihak" style="width:400px" id="jenis_pihak" onchange="javaScript:changePgAmanah(this.options[selectedIndex].text);">
                                    <s:option value="" >Sila Pilih</s:option>
                                    <s:options-collection collection="${actionBean.senaraiKodPenerima}" label="nama" value="kod" />
                                </s:select>
                            </p>
                        </c:if>
                        <p>
                            <label for="alamat"><font color="red">*</font>Alamat Berdaftar :</label>
                            <s:text name="pihak.alamat1" size="40" id="alamat1" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>

                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.alamat2" size="40" id="alamat2" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>

                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.alamat3" size="40" id="alamat3" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> Bandar :</label>
                            <s:text name="pihak.alamat4" size="40" id="alamat4" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="pihak.poskod" size="40" maxlength="5" id="poskod" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select name="pihak.negeri.kod" id="negeri">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <p>
                            <label>Bangsa/Jenis Syarikat :</label>
                            <s:select name="pihak.bangsa.kod" style="width:400px" id="kodBangsa">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraiKodBangsa}" label="nama" value="kod"/>
                            </s:select>
                            <c:if test="${actionBean.p.kodUrusan.kod ne 'PMADT' && actionBean.p.kodUrusan.kod ne 'CGADT' && actionBean.p.kodUrusan.kod ne 'PAADT' && actionBean.p.kodUrusan.kod ne 'TMADT' && actionBean.p.kodUrusan.kod ne 'BTADT'}">
                                <s:select name="pihak.bangsa.kod" style="width:400px" id="kodBangsaOrang">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${actionBean.senaraiKodBangsaOrang}" label="nama" value="kod"/>
                                </s:select>
                            </c:if>
                            <c:if test="${actionBean.p.kodUrusan.kod eq 'PMADT' || actionBean.p.kodUrusan.kod eq 'CGADT' || actionBean.p.kodUrusan.kod eq 'PAADT' || actionBean.p.kodUrusan.kod eq 'TMADT' || actionBean.p.kodUrusan.kod eq 'BTADT'}">
                                <s:select name="pihak.bangsa.kod" style="width:200px" id="kodBangsaAdat">
                                    <s:option value="MEL">MELAYU</s:option>
                                </s:select>
                            </c:if>
                            <s:select name="pihak.bangsa.kod" style="width:400px" id="kodBangsaSyarikat">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraiKodBangsaSyarikat}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <c:if test="${actionBean.kodNegeri eq '05'}">
                            <p id="suku">
                                <label for="Suku">Jenis Suku :</label>
                                <s:select name="pihak.suku.kod" style="width:200px">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodSuku}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                            <p id="subSuku">
                                <label for="subSuku">Pecahan Suku/Lengkongan :</label>
                                <s:text name="pihak.subSuku" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p id="perut">
                                <label for="perut">Perut :</label>
                                <s:text name="pihak.keturunan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p id="luak">
                                <label for="luak">Luak :</label>
                                <s:text name="pihak.tempatSuku" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                        </c:if>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                            <p>
                                <label>Tarikh Lahir :</label>
                                <s:text name="tarikhLahir" size="40" id="datepicker" class="datepicker" onchange="calAgeByDOB(this.value);"/>&nbsp;<em style="font-style:normal">(cth : hh / bb / tttt)</em>
                            </p>
                            <p>
                                <label>Umur :</label>
                                <s:text name="permohonanPihak.umur" size="10" maxlength="3" id="umur" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Tempat Lahir :</label>
                                <s:select name="pihak.tempatLahir" id="tempatLahir" onchange="javaScript:changeTempatLahir(this.options[selectedIndex].text);">>
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="nama"/>
                                    <s:option value="LAIN-LAIN">Lain-lain</s:option>
                                </s:select>
                            </p>
                            <p id="tempatLahirLain">
                                <label>Lain-lain Tempat Lahir :</label>
                                <s:text name="tempatLahirLain" size="40" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <p>
                                <label>Status Perkahwinan :</label>
                                <s:select name="permohonanPihak.statusKahwin">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option>Berkahwin</s:option>
                                    <s:option>Bujang</s:option>
                                    <s:option>Duda</s:option>
                                    <s:option>Ibu Tunggal</s:option>
                                </s:select>
                            </p>
                            <c:if test="${actionBean.p.kodUrusan.kod ne 'PMADT' && actionBean.p.kodUrusan.kod ne 'CGADT' && actionBean.p.kodUrusan.kod ne 'PAADT' && actionBean.p.kodUrusan.kod ne 'TMADT' && actionBean.p.kodUrusan.kod ne 'BTADT'}">
                                <p>
                                    <label>Pekerjaan :</label>
                                    <s:text name="permohonanPihak.pekerjaan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                                </p>
                                <p>
                                    <label>Pendapatan Bulanan (RM) :</label>
                                    <s:text name="permohonanPihak.pendapatan" size="40" maxlength="12" onkeyup="validateNumber(this,this.value);"/>
                                </p>
                                <p>
                                    <label>Tanggungan :</label>
                                    <s:text name="permohonanPihak.tangungan" size="40" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                                </p>
                            </c:if>
                        </c:if>
                        <p>
                            <label>
                                <c:if test="${pemohon}">
                                    <c:if test="${actionBean.p.kodUrusan.kod ne 'PMADT' && actionBean.p.kodUrusan.kod ne 'CGADT' && actionBean.p.kodUrusan.kod ne 'PAADT' && actionBean.p.kodUrusan.kod ne 'TMADT' && actionBean.p.kodUrusan.kod ne 'BTADT'}">
                                        Hubungan Dengan Penerima :
                                    </c:if>

                                    <c:if test="${actionBean.p.kodUrusan.kod eq 'PMADT' || actionBean.p.kodUrusan.kod eq 'CGADT' || actionBean.p.kodUrusan.kod eq 'PAADT' || actionBean.p.kodUrusan.kod eq 'TMADT' || actionBean.p.kodUrusan.kod eq 'BTADT'}">

                                        <c:if test="${actionBean.p.kodUrusan.kod eq 'TMADT'}">
                                            Hubungan Dengan Simati :
                                        </c:if>
                                        <c:if test="${actionBean.p.kodUrusan.kod ne 'TMADT'}">
                                            Hubungan Dengan Tuan Tanah :
                                        </c:if>
                                    </c:if>

                                </c:if>
                                <c:if test="${!pemohon}">
                                    <c:if test="${actionBean.p.kodUrusan.kod ne 'PMADT' && actionBean.p.kodUrusan.kod ne 'CGADT' && actionBean.p.kodUrusan.kod ne 'PAADT' && actionBean.p.kodUrusan.kod ne 'TMADT' && actionBean.p.kodUrusan.kod ne 'BTADT'}">
                                        Hubungan Dengan Pemohon :
                                    </c:if>
                                    <c:if test="${actionBean.p.kodUrusan.kod eq 'PMADT' || actionBean.p.kodUrusan.kod eq 'CGADT' || actionBean.p.kodUrusan.kod eq 'PAADT' || actionBean.p.kodUrusan.kod eq 'TMADT' || actionBean.p.kodUrusan.kod eq 'BTADT'}">
                                        <c:if test="${actionBean.p.kodUrusan.kod eq 'TMADT'}">
                                            Hubungan Dengan Simati :
                                        </c:if>
                                        <c:if test="${actionBean.p.kodUrusan.kod ne 'TMADT'}">
                                            Hubungan Dengan Tuan Tanah :
                                        </c:if>
                                    </c:if>
                                </c:if>
                            </label>
                            <c:if test="${actionBean.p.kodUrusan.kod ne 'PMADT' && actionBean.p.kodUrusan.kod ne 'CGADT' && actionBean.p.kodUrusan.kod ne 'PAADT' && actionBean.p.kodUrusan.kod ne 'TMADT' && actionBean.p.kodUrusan.kod ne 'BTADT'}">
                                <s:select name="permohonanPihak.kaitan" id="hubungan" onchange="javaScript:changeHubungan(this.options[selectedIndex].text);">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option>Ibu Bapa Kepada Anak</s:option>
                                    <s:option>Anak Kepada Ibu Bapa</s:option>
                                    <s:option>Suami Kepada Isteri</s:option>
                                    <s:option>Isteri Kepada Suami</s:option>
                                    <s:option>Saudara-mara</s:option>
                                    <s:option>Penjual / Pembeli</s:option>
                                    <s:option>Lain-lain</s:option>
                                </s:select>
                            </c:if>
                            <c:if test="${actionBean.p.kodUrusan.kod eq 'PMADT' || actionBean.p.kodUrusan.kod eq 'CGADT' || actionBean.p.kodUrusan.kod eq 'PAADT' || actionBean.p.kodUrusan.kod eq 'TMADT' || actionBean.p.kodUrusan.kod eq 'BTADT'}">
                                <s:select name="permohonanPihak.kaitan" id="hubungan" onchange="javaScript:changeHubungan(this.options[selectedIndex].text);">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option>Anak</s:option>
                                    <s:option>Adik Beradik Sesuku</s:option>
                                    <s:option>Cucu</s:option>
                                    <s:option>Ibu Kandung</s:option>
                                    <s:option>Ibu Saudara</s:option>
                                    <s:option>Moyang</s:option>
                                    <s:option>Nenek</s:option>
                                    <s:option>Sepupu</s:option>
                                    <s:option>Lain-lain</s:option>
                                </s:select>
                            </c:if>
                        </p>

                        <p id="hubunganLain">
                            <label>Lain-lain Hubungan :</label>
                            <s:text name="hubunganLain" size="40" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>

                        <p>
                            <label>&nbsp;</label>
                            <input type="checkbox" id="add1" name="add1" value="" onclick="copyAdd();"/>
                            <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                        </p>

                        <p>
                            <label for="alamat">Alamat Surat-Menyurat :</label>
                            <s:text name="pihak.suratAlamat1" id="suratAlamat1" size="100" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat2" id="suratAlamat2" size="100" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat3" id="suratAlamat3" size="100" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> Bandar :</label>
                            <s:text name="pihak.suratAlamat4" id="suratAlamat4" size="100" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="pihak.suratPoskod" id="suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select name="pihak.suratNegeri.kod" id="suratNegeri">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>

                    </c:if>
                    <c:if test="${actionBean.pihak.jenisPengenalan.kod ne 'B' && actionBean.pihak.jenisPengenalan.kod ne 'L' && actionBean.pihak.jenisPengenalan.kod ne 'P' && actionBean.pihak.jenisPengenalan.kod ne 'T' && actionBean.pihak.jenisPengenalan.kod ne 'I'}">
                        <div class="content" align="center" id="tableCaw">

                            <c:if test="${fn:length(actionBean.cawanganList) > 0}">
                                <br>
                                Maklumat Cawangan

                                <display:table name="${actionBean.cawanganList}" id="line" class="tablecloth" >

                                    <display:column title="Pilih"><s:radio name="idCawangan" value="${line.idPihakCawangan}"/></display:column>
                                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                    <display:column property="namaCawangan" title="Nama"/>
                                    <display:column title="Alamat" >${line.suratAlamat1}
                                        <c:if test="${line.suratAlamat2 ne null}"> , </c:if>
                                        ${line.suratAlamat2}
                                        <c:if test="${line.suratAlamat3 ne null}"> , </c:if>
                                        ${line.suratAlamat3}
                                        <c:if test="${line.suratAlamat4 ne null}"> , </c:if>
                                        ${line.suratAlamat4}
                                        <c:if test="${line.suratPoskod ne null}"> , </c:if>
                                        ${line.suratPoskod}
                                        <c:if test="${line.suratNegeri ne null}"> , </c:if>
                                        ${line.suratNegeri.nama}
                                    </display:column>
                                    <c:if test="${pemohon}">
                                        <display:column title="Kemaskini">
                                            <p align="center">
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                     onclick="editCawanganPemohon('${line.idPihakCawangan}')" onmouseover="this.style.cursor='pointer';">
                                            </p>
                                        </display:column>
                                        <display:column title="Hapus">
                                            <div align="center">
                                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                     id='rem_${line_rowNum}' onclick="removeCawanganPemohon('${line.idPihakCawangan}')" onmouseover="this.style.cursor='pointer';">
                                            </div>
                                        </display:column>
                                    </c:if>

                                    <c:if test="${!pemohon}">
                                        <display:column title="Kemaskini">
                                            <p align="center">
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                     onclick="editCawangan('${line.idPihakCawangan}')" onmouseover="this.style.cursor='pointer';">
                                            </p>
                                        </display:column>
                                        <display:column title="Hapus">
                                            <div align="center">
                                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                     id='rem_${line_rowNum}' onclick="removeCawangan('${line.idPihakCawangan}')" onmouseover="this.style.cursor='pointer';">
                                            </div>
                                        </display:column>
                                    </c:if>
                                </display:table>
                            </c:if>
                            <c:if test="${fn:length(actionBean.pengarahList) > 0}">
                                <br>
                                Maklumat Ahli Lembaga Pengarah

                                <display:table name="${actionBean.pengarahList}" id="line2" class="tablecloth" >
                                    <display:column title="Bil" sortable="true">${line2_rowNum}</display:column>
                                    <display:column property="nama" title="Nama"/>
                                    <display:column property="noPengenalan" title="Nombor Pengenalan"/>

                                    <c:if test="${pemohon}">
                                        <display:column title="Kemaskini">
                                            <p align="center">
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                     onclick="editPengarahPemohon('${line2.idPengarah}')" onmouseover="this.style.cursor='pointer';">
                                            </p>
                                        </display:column>
                                        <display:column title="Hapus">
                                            <div align="center">
                                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                     id='rem_${line2_rowNum}' onclick="removePengarahPemohon('${line2.idPengarah}')" onmouseover="this.style.cursor='pointer';">
                                            </div>
                                        </display:column>
                                    </c:if>

                                    <c:if test="${!pemohon}">
                                        <display:column title="Kemaskini">
                                            <p align="center">
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                     onclick="editPengarah('${line2.idPengarah}')" onmouseover="this.style.cursor='pointer';">
                                            </p>
                                        </display:column>
                                        <display:column title="Hapus">
                                            <div align="center">
                                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                     id='rem_${line2_rowNum}' onclick="removePengarah('${line2.idPengarah}')" onmouseover="this.style.cursor='pointer';">
                                            </div>
                                        </display:column>
                                    </c:if>

                                </display:table>
                            </c:if>
                        </div>
                    </c:if>

                    <%--Add Pihak Dipegang Amanah--%>
                    <div id="pgAmanah">
                        <div class="content" align="center">
                            Maklumat Penerima Amanah
                        </div>
                        <div id="divAmanah1">
                            <p>
                                <label for="nama"><font color="red">*</font>Nama :</label>
                                <s:text name="permohonanPihakHubungan.nama" id="namaAmanah" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <c:if test="${actionBean.kodNegeri eq '05'}">
                                <p>
                                    <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                    <s:select name="permohonanPihakHubungan.jenisPengenalan.kod" id="kpAmanah">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                    </s:select>
                                </p>
                            </c:if>

                            <c:if test="${actionBean.kodNegeri eq '04'}">
                                <p>
                                    <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                    <s:select name="permohonanPihakHubungan.jenisPengenalan.kod" id="kpAmanah">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                    </s:select>
                                </p>
                            </c:if>
                            <p>
                                <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                                <s:text name="permohonanPihakHubungan.noPengenalan" id="nokpAmanah" size="40" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;<em style="font-style:normal">(cth : 800620125177)</em>
                            </p>
                            <p>
                                <label for="alamat"><font color="red">*</font>Alamat :</label>
                                <s:text name="permohonanPihakHubungan.alamat1" size="100" id="alamatAmanah1" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <p>
                                <label> &nbsp;</label>
                                <s:text name="permohonanPihakHubungan.alamat2" size="100" id="alamatAmanah2" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <p>
                                <label> &nbsp;</label>
                                <s:text name="permohonanPihakHubungan.alamat3" size="100" id="alamatAmanah3" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label> Bandar :</label>
                                <s:text name="permohonanPihakHubungan.alamat4" size="100" id="alamatAmanah4" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label>Poskod :</label>
                                <s:text name="permohonanPihakHubungan.poskod" size="40" maxlength="5" id="poskodAmanah" onkeyup="validateNumber(this,this.value);"/>
                            </p>

                            <p>
                                <label for="Negeri">Negeri :</label>
                                <s:select name="permohonanPihakHubungan.negeri.kod" id="negeriAmanah">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                            <p align="center">
                                <s:button name="" id="btnTambah" value="Tambah" class="btn" onclick="tambahAmanah('1')"/>
                            </p>
                        </div>
                        <%----------------2-----------------------%>
                        <div id="divAmanah2">
                            <br/>
                            <p>
                                <label for="nama"><font color="red">*</font>Nama :</label>
                                <s:text name="permohonanPihakHubungan2.nama" id="namaAmanah2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <c:if test="${actionBean.kodNegeri eq '05'}">
                                <p>
                                    <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                    <s:select name="permohonanPihakHubungan2.jenisPengenalan.kod" id="kpAmanah2">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                    </s:select>
                                </p>
                            </c:if>

                            <c:if test="${actionBean.kodNegeri eq '04'}">
                                <p>
                                    <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                    <s:select name="permohonanPihakHubungan2.jenisPengenalan.kod" id="kpAmanah2">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                    </s:select>
                                </p>
                            </c:if>
                            <p>
                                <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                                <s:text name="permohonanPihakHubungan2.noPengenalan" id="nokpAmanah2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;<em style="font-style:normal">(cth : 800620125177)</em>
                            </p>
                            <p>
                                <label for="alamat"><font color="red">*</font>Alamat :</label>
                                <s:text name="permohonanPihakHubungan2.alamat1" size="100" id="alamatAmanah12" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <p>
                                <label> &nbsp;</label>
                                <s:text name="permohonanPihakHubungan2.alamat2" size="100" id="alamatAmanah22" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <p>
                                <label> &nbsp;</label>
                                <s:text name="permohonanPihakHubungan2.alamat3" size="100" id="alamatAmanah32" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label> Bandar :</label>
                                <s:text name="permohonanPihakHubungan2.alamat4" size="100" id="alamatAmanah42" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label>Poskod :</label>
                                <s:text name="permohonanPihakHubungan2.poskod" size="40" maxlength="5" id="poskodAmanah2" onkeyup="validateNumber(this,this.value);"/>
                            </p>

                            <p>
                                <label for="Negeri">Negeri :</label>
                                <s:select name="permohonanPihakHubungan2.negeri.kod" id="negeriAmanah2">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                            <p align="center">
                                <s:button name="" id="btnTambah2" value="Tambah" class="btn" onclick="tambahAmanah('2')"/>
                                <s:button name="" id="btnHapus2" value="Hapus" class="btn" onclick="hapusAmanah('2')"/>
                            </p>
                        </div>
                        <%------------------------3-------------------------%>
                        <div id="divAmanah3">
                            <br/>
                            <p>
                                <label for="nama"><font color="red">*</font>Nama :</label>
                                <s:text name="permohonanPihakHubungan3.nama" id="namaAmanah3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <c:if test="${actionBean.kodNegeri eq '05'}">
                                <p>
                                    <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                    <s:select name="permohonanPihakHubungan3.jenisPengenalan.kod" id="kpAmanah3">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                    </s:select>
                                </p>
                            </c:if>

                            <c:if test="${actionBean.kodNegeri eq '04'}">
                                <p>
                                    <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                    <s:select name="permohonanPihakHubungan3.jenisPengenalan.kod" id="kpAmanah3">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                    </s:select>
                                </p>
                            </c:if>
                            <p>
                                <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                                <s:text name="permohonanPihakHubungan3.noPengenalan" id="nokpAmanah3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;<em style="font-style:normal">(cth : 800620125177)</em>
                            </p>
                            <p>
                                <label for="alamat"><font color="red">*</font>Alamat :</label>
                                <s:text name="permohonanPihakHubungan3.alamat1" size="40" id="alamatAmanah13" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <p>
                                <label> &nbsp;</label>
                                <s:text name="permohonanPihakHubungan3.alamat2" size="40" id="alamatAmanah23" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <p>
                                <label> &nbsp;</label>
                                <s:text name="permohonanPihakHubungan3.alamat3" size="40" id="alamatAmanah33" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label> Bandar :</label>
                                <s:text name="permohonanPihakHubungan3.alamat4" size="40" id="alamatAmanah43" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label>Poskod :</label>
                                <s:text name="permohonanPihakHubungan3.poskod" size="40" maxlength="5" id="poskodAmanah3" onkeyup="validateNumber(this,this.value);"/>
                            </p>

                            <p>
                                <label for="Negeri">Negeri :</label>
                                <s:select name="permohonanPihakHubungan3.negeri.kod" id="negeriAmanah3">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                            <p align="center">
                                <s:button name="" id="btnTambah3" value="Tambah" class="btn" onclick="tambahAmanah('3')"/>
                                <s:button name="" id="btnHapus3" value="Hapus" class="btn" onclick="hapusAmanah('3')"/>
                            </p>
                        </div>
                        <%-------------------4---------------------------------%>
                        <div id="divAmanah4">
                            <br/>
                            <p>
                                <label for="nama"><font color="red">*</font>Nama :</label>
                                <s:text name="permohonanPihakHubungan4.nama" id="namaAmanah4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <c:if test="${actionBean.kodNegeri eq '05'}">
                                <p>
                                    <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                    <s:select name="permohonanPihakHubungan4.jenisPengenalan.kod" id="kpAmanah4">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                    </s:select>
                                </p>
                            </c:if>

                            <c:if test="${actionBean.kodNegeri eq '04'}">
                                <p>
                                    <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                    <s:select name="permohonanPihakHubungan4.jenisPengenalan.kod" id="kpAmanah4">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                    </s:select>
                                </p>
                            </c:if>
                            <p>
                                <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                                <s:text name="permohonanPihakHubungan4.noPengenalan" id="nokpAmanah4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;<em style="font-style:normal">(cth : 800620125177)</em>
                            </p>
                            <p>
                                <label for="alamat"><font color="red">*</font>Alamat :</label>
                                <s:text name="permohonanPihakHubungan4.alamat1" size="40" id="alamatAmanah14" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <p>
                                <label> &nbsp;</label>
                                <s:text name="permohonanPihakHubungan4.alamat2" size="40" id="alamatAmanah24" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <p>
                                <label> &nbsp;</label>
                                <s:text name="permohonanPihakHubungan4.alamat3" size="40" id="alamatAmanah34" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label> Bandar :</label>
                                <s:text name="permohonanPihakHubungan4.alamat4" size="40" id="alamatAmanah44" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label>Poskod :</label>
                                <s:text name="permohonanPihakHubungan4.poskod" size="40" maxlength="5" id="poskodAmanah4" onkeyup="validateNumber(this,this.value);"/>
                            </p>

                            <p>
                                <label for="Negeri">Negeri :</label>
                                <s:select name="permohonanPihakHubungan4.negeri.kod" id="negeriAmanah4">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                            <p align="center">
                                <s:button name="" id="btnTambah4" value="Tambah" class="btn" onclick="tambahAmanah('4')"/>
                                <s:button name="" id="btnHapus4" value="Hapus" class="btn" onclick="hapusAmanah('4')"/>
                            </p>
                        </div>
                        <%----------------------------------5---------------------------------%>
                        <div id="divAmanah5">
                            <br/>
                            <p>
                                <label for="nama"><font color="red">*</font>Nama :</label>
                                <s:text name="permohonanPihakHubungan5.nama" id="namaAmanah5" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <c:if test="${actionBean.kodNegeri eq '05'}">
                                <p>
                                    <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                    <s:select name="permohonanPihakHubungan5.jenisPengenalan.kod" id="kpAmanah5">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                    </s:select>
                                </p>
                            </c:if>

                            <c:if test="${actionBean.kodNegeri eq '04'}">
                                <p>
                                    <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                    <s:select name="permohonanPihakHubungan5.jenisPengenalan.kod" id="kpAmanah5">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                    </s:select>
                                </p>
                            </c:if>
                            <p>
                                <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                                <s:text name="permohonanPihakHubungan5.noPengenalan" id="nokpAmanah5" size="40" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;<em style="font-style:normal">(cth : 800620125177)</em>
                            </p>
                            <p>
                                <label for="alamat"><font color="red">*</font>Alamat :</label>
                                <s:text name="permohonanPihakHubungan5.alamat1" size="40" id="alamatAmanah15" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <p>
                                <label> &nbsp;</label>
                                <s:text name="permohonanPihakHubungan5.alamat2" size="40" id="alamatAmanah25" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <p>
                                <label> &nbsp;</label>
                                <s:text name="permohonanPihakHubungan5.alamat3" size="40" id="alamatAmanah35" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label> Bandar :</label>
                                <s:text name="permohonanPihakHubungan5.alamat4" size="40" id="alamatAmanah45" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label>Poskod :</label>
                                <s:text name="permohonanPihakHubungan5.poskod" size="40" maxlength="5" id="poskodAmanah5" onkeyup="validateNumber(this,this.value);"/>
                            </p>

                            <p>
                                <label for="Negeri">Negeri :</label>
                                <s:select name="permohonanPihakHubungan5.negeri.kod" id="negeriAmanah5">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                            <p align="center">
                                <s:button name="" id="btnTambah5" value="Tambah" class="btn" onclick="tambahAmanah('5')"/>
                                <s:button name="" id="btnHapus5" value="Hapus" class="btn" onclick="hapusAmanah('5')"/>
                            </p>
                        </div>
                        <%--------------------------6---------------------------------%>
                        <div id="divAmanah6">
                            <br/>
                            <p>
                                <label for="nama"><font color="red">*</font>Nama :</label>
                                <s:text name="permohonanPihakHubungan6.nama" id="namaAmanah6" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <c:if test="${actionBean.kodNegeri eq '05'}">
                                <p>
                                    <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                    <s:select name="permohonanPihakHubungan6.jenisPengenalan.kod" id="kpAmanah6">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                    </s:select>
                                </p>
                            </c:if>

                            <c:if test="${actionBean.kodNegeri eq '04'}">
                                <p>
                                    <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                    <s:select name="permohonanPihakHubungan6.jenisPengenalan.kod" id="kpAmanah6">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                    </s:select>
                                </p>
                            </c:if>
                            <p>
                                <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                                <s:text name="permohonanPihakHubungan6.noPengenalan" id="nokpAmanah6" size="40" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;<em style="font-style:normal">(cth : 800620125177)</em>
                            </p>
                            <p>
                                <label for="alamat"><font color="red">*</font>Alamat :</label>
                                <s:text name="permohonanPihakHubungan6.alamat1" size="40" id="alamatAmanah16" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <p>
                                <label> &nbsp;</label>
                                <s:text name="permohonanPihakHubungan6.alamat2" size="40" id="alamatAmanah26" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <p>
                                <label> &nbsp;</label>
                                <s:text name="permohonanPihakHubungan6.alamat3" size="40" id="alamatAmanah36" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label> Bandar :</label>
                                <s:text name="permohonanPihakHubungan6.alamat4" size="40" id="alamatAmanah46" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label>Poskod :</label>
                                <s:text name="permohonanPihakHubungan6.poskod" size="40" maxlength="5" id="poskodAmanah6" onkeyup="validateNumber(this,this.value);"/>
                            </p>

                            <p>
                                <label for="Negeri">Negeri :</label>
                                <s:select name="permohonanPihakHubungan6.negeri.kod" id="negeriAmanah6">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                            <p align="center">
                                <s:button name="" id="btnTambah6" value="Tambah" class="btn" onclick="tambahAmanah('6')"/>
                                <s:button name="" id="btnHapus6" value="Hapus" class="btn" onclick="hapusAmanah('6')"/>
                            </p>
                        </div>
                        <%----------------------------------7-------------------------------%>
                        <div id="divAmanah7">
                            <br/>
                            <p>
                                <label for="nama"><font color="red">*</font>Nama :</label>
                                <s:text name="permohonanPihakHubungan7.nama" id="namaAmanah7" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <c:if test="${actionBean.kodNegeri eq '05'}">
                                <p>
                                    <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                    <s:select name="permohonanPihakHubungan7.jenisPengenalan.kod" id="kpAmanah7">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                    </s:select>
                                </p>
                            </c:if>

                            <c:if test="${actionBean.kodNegeri eq '04'}">
                                <p>
                                    <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                    <s:select name="permohonanPihakHubungan7.jenisPengenalan.kod" id="kpAmanah7">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                    </s:select>
                                </p>
                            </c:if>
                            <p>
                                <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                                <s:text name="permohonanPihakHubungan7.noPengenalan" id="nokpAmanah7" size="40" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;<em style="font-style:normal">(cth : 800620125177)</em>
                            </p>
                            <p>
                                <label for="alamat"><font color="red">*</font>Alamat :</label>
                                <s:text name="permohonanPihakHubungan7.alamat1" size="40" id="alamatAmanah17" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <p>
                                <label> &nbsp;</label>
                                <s:text name="permohonanPihakHubungan7.alamat2" size="40" id="alamatAmanah27" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <p>
                                <label> &nbsp;</label>
                                <s:text name="permohonanPihakHubungan7.alamat3" size="40" id="alamatAmanah37" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label> Bandar :</label>
                                <s:text name="permohonanPihakHubungan7.alamat4" size="40" id="alamatAmanah47" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label>Poskod :</label>
                                <s:text name="permohonanPihakHubungan7.poskod" size="40" maxlength="5" id="poskodAmanah7" onkeyup="validateNumber(this,this.value);"/>
                            </p>

                            <p>
                                <label for="Negeri">Negeri :</label>
                                <s:select name="permohonanPihakHubungan7.negeri.kod" id="negeriAmanah7">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                            <p align="center">
                                <s:button name="" id="btnTambah7" value="Tambah" class="btn" onclick="tambahAmanah('7')"/>
                                <s:button name="" id="btnHapus7" value="Hapus" class="btn" onclick="hapusAmanah('7')"/>
                            </p>
                        </div>
                        <%--------------------------------8-------------------------------%>
                        <div id="divAmanah8">
                            <br/>
                            <p>
                                <label for="nama"><font color="red">*</font>Nama :</label>
                                <s:text name="permohonanPihakHubungan8.nama" id="namaAmanah8" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <c:if test="${actionBean.kodNegeri eq '05'}">
                                <p>
                                    <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                    <s:select name="permohonanPihakHubungan8.jenisPengenalan.kod" id="kpAmanah8">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                    </s:select>
                                </p>
                            </c:if>

                            <c:if test="${actionBean.kodNegeri eq '04'}">
                                <p>
                                    <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                    <s:select name="permohonanPihakHubungan8.jenisPengenalan.kod" id="kpAmanah8">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                    </s:select>
                                </p>
                            </c:if>
                            <p>
                                <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                                <s:text name="permohonanPihakHubungan8.noPengenalan" id="nokpAmanah8" size="40" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;<em style="font-style:normal">(cth : 800620125177)</em>
                            </p>
                            <p>
                                <label for="alamat"><font color="red">*</font>Alamat :</label>
                                <s:text name="permohonanPihakHubungan8.alamat1" size="40" id="alamatAmanah18" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <p>
                                <label> &nbsp;</label>
                                <s:text name="permohonanPihakHubungan8.alamat2" size="40" id="alamatAmanah28" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <p>
                                <label> &nbsp;</label>
                                <s:text name="permohonanPihakHubungan8.alamat3" size="40" id="alamatAmanah38" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label> Bandar :</label>
                                <s:text name="permohonanPihakHubungan8.alamat4" size="40" id="alamatAmanah48" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label>Poskod :</label>
                                <s:text name="permohonanPihakHubungan8.poskod" size="40" maxlength="5" id="poskodAmanah8" onkeyup="validateNumber(this,this.value);"/>
                            </p>

                            <p>
                                <label for="Negeri">Negeri :</label>
                                <s:select name="permohonanPihakHubungan8.negeri.kod" id="negeriAmanah8">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                            <p align="center">
                                <s:button name="" id="btnTambah8" value="Tambah" class="btn" onclick="tambahAmanah('8')"/>
                                <s:button name="" id="btnHapus8" value="Hapus" class="btn" onclick="hapusAmanah('8')"/>
                            </p>
                        </div>
                        <%-----------------------------9------------------------%>
                        <div id="divAmanah9">
                            <br/>
                            <p>
                                <label for="nama"><font color="red">*</font>Nama :</label>
                                <s:text name="permohonanPihakHubungan9.nama" id="namaAmanah9" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <c:if test="${actionBean.kodNegeri eq '05'}">
                                <p>
                                    <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                    <s:select name="permohonanPihakHubungan9.jenisPengenalan.kod" id="kpAmanah9">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                    </s:select>
                                </p>
                            </c:if>

                            <c:if test="${actionBean.kodNegeri eq '04'}">
                                <p>
                                    <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                    <s:select name="permohonanPihakHubungan9.jenisPengenalan.kod" id="kpAmanah9">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                    </s:select>
                                </p>
                            </c:if>
                            <p>
                                <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                                <s:text name="permohonanPihakHubungan9.noPengenalan" id="nokpAmanah9" size="40" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;<em style="font-style:normal">(cth : 800620125177)</em>
                            </p>
                            <p>
                                <label for="alamat"><font color="red">*</font>Alamat :</label>
                                <s:text name="permohonanPihakHubungan9.alamat1" size="40" id="alamatAmanah19" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <p>
                                <label> &nbsp;</label>
                                <s:text name="permohonanPihakHubungan9.alamat2" size="40" id="alamatAmanah29" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <p>
                                <label> &nbsp;</label>
                                <s:text name="permohonanPihakHubungan9.alamat3" size="40" id="alamatAmanah39" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label> Bandar :</label>
                                <s:text name="permohonanPihakHubungan9.alamat4" size="40" id="alamatAmanah49" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label>Poskod :</label>
                                <s:text name="permohonanPihakHubungan9.poskod" size="40" maxlength="5" id="poskodAmanah9" onkeyup="validateNumber(this,this.value);"/>
                            </p>

                            <p>
                                <label for="Negeri">Negeri :</label>
                                <s:select name="permohonanPihakHubungan9.negeri.kod" id="negeriAmanah9">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                            <p align="center">
                                <s:button name="" id="btnTambah9" value="Tambah" class="btn" onclick="tambahAmanah('9')"/>
                                <s:button name="" id="btnHapus9" value="Hapus" class="btn" onclick="hapusAmanah('9')"/>
                            </p>
                        </div>
                        <%-----------------------------------10---------------------------%>
                        <div id="divAmanah10">
                            <br/>
                            <p>
                                <label for="nama"><font color="red">*</font>Nama :</label>
                                <s:text name="permohonanPihakHubungan10.nama" id="namaAmanah10" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <c:if test="${actionBean.kodNegeri eq '05'}">
                                <p>
                                    <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                    <s:select name="permohonanPihakHubungan10.jenisPengenalan.kod" id="kpAmanah10">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                    </s:select>
                                </p>
                            </c:if>

                            <c:if test="${actionBean.kodNegeri eq '04'}">
                                <p>
                                    <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                    <s:select name="permohonanPihakHubungan10.jenisPengenalan.kod" id="kpAmanah10">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                    </s:select>
                                </p>
                            </c:if>
                            <p>
                                <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                                <s:text name="permohonanPihakHubungan10.noPengenalan" id="nokpAmanah10" size="40" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;<em style="font-style:normal">(cth : 800620125177)</em>
                            </p>
                            <p>
                                <label for="alamat"><font color="red">*</font>Alamat :</label>
                                <s:text name="permohonanPihakHubungan10.alamat1" size="40" id="alamatAmanah110" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <p>
                                <label> &nbsp;</label>
                                <s:text name="permohonanPihakHubungan10.alamat2" size="40" id="alamatAmanah210" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <p>
                                <label> &nbsp;</label>
                                <s:text name="permohonanPihakHubungan10.alamat3" size="40" id="alamatAmanah310" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label> Bandar :</label>
                                <s:text name="permohonanPihakHubungan10.alamat4" size="40" id="alamatAmanah410" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label>Poskod :</label>
                                <s:text name="permohonanPihakHubungan10.poskod" size="40" maxlength="5" id="poskodAmanah10" onkeyup="validateNumber(this,this.value);"/>
                            </p>

                            <p>
                                <label for="Negeri">Negeri :</label>
                                <s:select name="permohonanPihakHubungan10.negeri.kod" id="negeriAmanah10">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                            <p align="center">
                                <s:button name="" id="btnHapus10" value="Hapus" class="btn" onclick="hapusAmanah('10')"/>
                            </p>
                        </div>

                    </div>
                </c:if>

                <c:if test="${!actionBean.cariFlag}">
                    <p align="center">
                        <c:if test="${pemohon}">
                            <s:submit name="cariPihakPemohon" value="Cari" class="btn"/>
                            <s:submit name="cariSemulaPemohon" value="Isi Semula" class="btn"/>&nbsp;
                        </c:if>
                        <c:if test="${!pemohon}">
                            <s:submit name="cariPihak" value="Cari" class="btn"/>
                            <s:submit name="cariSemula" value="Isi Semula" class="btn"/>
                        </c:if>

                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    </p>
                </c:if>

                <c:if test="${actionBean.cariFlag}">
                    <c:if test="${!pemohon}">
                        <c:if test="${fn:length(actionBean.p.senaraiHakmilik) > 1}">
                            <p>
                                <label>&nbsp;</label>
                                <s:checkbox name="semuaIdHakmilik" value="Y"/><font color="red">&nbsp;Penerima ini adalah penerima untuk semua hakmilik.</font>
                            </p>
                        </c:if>
                    </c:if>

                    <br/>
                    <p align="center">
                        <c:if test="${pemohon}">
                            <s:button name="simpanPemohonPopup" id="simpan" value="Simpan" class="btn" onclick="savePemohon(this.name, this.form);"/>
                            <c:if test="${actionBean.pihak.jenisPengenalan.kod ne 'B' && actionBean.pihak.jenisPengenalan.kod ne 'L' && actionBean.pihak.jenisPengenalan.kod ne 'P' && actionBean.pihak.jenisPengenalan.kod ne 'T' && actionBean.pihak.jenisPengenalan.kod ne 'I'}">
                                <s:submit name="tambahCawanganPemohon" value="Tambah Cawangan" class="longbtn"/>
                            </c:if>
                            <s:submit name="cariSemulaPemohon" value="Cari Semula" class="longbtn"/>
                        </c:if>
                        <c:if test="${!pemohon}">
                            <s:button name="simpanPihakKepentinganPopup" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>

                            <c:if test="${actionBean.pihak.jenisPengenalan.kod ne 'B' && actionBean.pihak.jenisPengenalan.kod ne 'L' && actionBean.pihak.jenisPengenalan.kod ne 'P' && actionBean.pihak.jenisPengenalan.kod ne 'T' && actionBean.pihak.jenisPengenalan.kod ne 'I'}">
                                <s:submit name="tambahCawangan" value="Tambah Cawangan" class="longbtn" id="btnCaw" onclick="return validationPB();"/>
                                <s:submit name="tambahPengarah" value="Tambah Ahli Pengarah" class="longbtn" id="btnAhli" onclick="return validationPB();"/>
                            </c:if>
                            <s:submit name="cariSemula" value="Cari Semula" class="longbtn"/>
                        </c:if>

                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    </p>
                </c:if>
                <br>
            </fieldset>
        </c:if>

        <%--add cawangan--%>

        <c:if test="${actionBean.tambahCawFlag}">
            <s:hidden name="pihakCawangan.negeri.kod"/>

            <fieldset class="aras1">
                <legend>
                    <c:if test="${pemohon}">
                        Kemasukan Cawangan Pemohon
                    </c:if>

                    <c:if test="${!pemohon}">
                        Kemasukan Cawangan Penerima
                    </c:if>
                </legend>
                <p>
                    <label for="nama"><font color="red">*</font>Nama :</label>
                    <s:text name="pihakCawangan.namaCawangan" id="namaCaw" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label for="alamat"><font color="red">*</font>Alamat Berdaftar :</label>
                    <s:text name="pihakCawangan.alamat1" id="alamatCaw1" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>

                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.alamat2" id="alamatCaw2" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>

                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.alamat3" id="alamatCaw3" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> Bandar :</label>
                    <s:text name="pihakCawangan.alamat4" id="alamatCaw4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>Poskod :</label>
                    <s:text name="pihakCawangan.poskod" size="40" id="poskodCaw" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                    <label for="Negeri">Negeri :</label>
                    <s:select name="pihakCawangan.negeri.kod" id="negeriCaw">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                    </s:select>

                </p>
                <p>
                    <label>&nbsp;</label>
                    <input type="checkbox" id="addCaw" name="addCaw" value="" onclick="copyAddCaw();"/>
                    <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                </p>
                <p>
                    <label for="alamat">Alamat Surat-Menyurat :</label>
                    <s:text name="pihakCawangan.suratAlamat1" id="suratAlamatCaw1" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.suratAlamat2" id="suratAlamatCaw2" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.suratAlamat3" id="suratAlamatCaw3" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> Bandar :</label>
                    <s:text name="pihakCawangan.suratAlamat4" id="suratAlamatCaw4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>Poskod :</label>
                    <s:text name="pihakCawangan.suratPoskod" id="suratPoskodCaw" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                </p>

                <p>
                    <label for="Negeri">Negeri :</label>
                    <s:select name="pihakCawangan.suratNegeri.kod" id="suratNegeriCaw">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                    </s:select>
                </p>

                <p>
                    <label for="alamat">Nombor Telefon :</label>
                    <s:text name="pihakCawangan.noTelefon1" size="40" maxlength="12" onkeyup="validateNumber(this,this.value);"/>
                </p>

                <p>
                    <label>&nbsp;</label>
                    <c:if test="${pemohon}">
                        <s:submit name="simpanCawanganPemohon" value="Simpan" class="btn"/>
                        <s:submit name="backCawanganPemohon" value="Tutup" class="btn"/>
                    </c:if>

                    <c:if test="${!pemohon}">
                        <s:submit name="simpanCawangan" value="Simpan" class="btn"/>
                        <s:submit name="backCawangan" value="Tutup" class="btn"/>
                    </c:if>
                </p>
                <br>
            </fieldset>
        </c:if>

        <%--Add Lembaga Pengarah--%>

        <c:if test="${actionBean.tambahPengarahFlag}">
            <fieldset class="aras1">
                <legend>
                    <c:if test="${pemohon}">
                        Kemasukan Lembaga Pengarah Pemohon
                    </c:if>

                    <c:if test="${!pemohon}">
                        Kemasukan Lembaga Pengarah Penerima
                    </c:if>
                </legend>
                <s:hidden name="pihakPengarah.idPengarah"/>
                <p>
                    <label for="nama"><font color="red">*</font>Nama :</label>
                    <s:text name="pihakPengarah.nama" id="namaPengarah" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <c:if test="${actionBean.kodNegeri eq '05'}">
                    <p>
                        <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                        <s:select name="pihakPengarah.jenisPengenalan.kod" id="jenisPengenalan"  onchange="javaScript:changeHideSuku(this.options[selectedIndex].text);">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                </c:if>

                <c:if test="${actionBean.kodNegeri eq '04'}">
                    <p>
                        <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                        <s:select name="pihakPengarah.jenisPengenalan.kod" id="jenisPengenalan">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                </c:if>

                <p>
                    <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                    <s:text name="pihakPengarah.noPengenalan" id="kp" size="40" onkeyup="dodacheck(this.value);this.value=this.value.toUpperCase();"
                            onblur="doCheckUmur(this.value, this.id,'${jenis}')"/>&nbsp;<em style="font-style:normal">(cth : 800620125177)</em>
                </p>
                <p>
                    <label><font color="red">*</font>Kewarganegaraan :</label>
                    <s:select name="pihakPengarah.wargaNegara.kod" style="width:200px" id="kod_warga_pengarah">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <c:if test="${pemohon}">
                        <s:submit name="simpanCawanganPemohon" value="Simpan" class="btn"/>
                        <s:submit name="backCawanganPemohon" value="Tutup" class="btn"/>
                    </c:if>

                    <c:if test="${!pemohon}">
                        <s:submit name="simpanPengarah" value="Simpan" class="btn"/>
                        <s:submit name="backCawangan" value="Tutup" class="btn"/>
                    </c:if>
                </p>
                <br>
            </fieldset>
        </c:if>
    </s:form>
</div>