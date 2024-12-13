<%--
    Document   : maklumat_pemohon_popup
    Created on : 19-Jan-2011, 15:08:36
    Author     : nordiyana
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
            $('#kodBangsaSyarikat').hide();
            $('#tableCaw').hide();
            $('#btnCaw').hide();
            $('#btnAhli').hide();
        }

        else{
            $('#kodBangsa').hide();
            $('#kodBangsaOrang').hide();
            $('#kodBangsaSyarikat').show();
            $('#tableCaw').show();
            $('#btnCaw').show();
            $('#btnAhli').show();
        }

        if(v == ''){
            $('#kodBangsa').show();
            $('#kodBangsaOrang').hide();
            $('#kodBangsaSyarikat').hide();
            $('#tableCaw').hide();
            $('#btnCaw').hide();
            $('#btnAhli').hide();
        }

        if(v == "S"){
            $('#suku').hide();
            $('#perut').hide();
            $('#luak').hide();
        }

        else{
            $('#suku').show();
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

            var age = calculateAge(icNo.substring(4,6), icNo.substring(2,4), year);
            $('#umur').val(age);
            $('#datepicker').val(icNo.substring(4,6)+"/"+icNo.substring(2,4)+"/"+year);
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
            $('#perut').hide();
            $('#luak').hide();
        }else{
            $('#suku').show();
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
        }
        if(value == "3"){
            $('#divAmanah3').hide();
            $('#btnTambah2').show();
        }
        if(value == "4"){
            $('#divAmanah4').hide();
            $('#btnTambah3').show();
        }
        if(value == "5"){
            $('#divAmanah5').hide();
            $('#btnTambah4').show();
        }
        if(value == "6"){
            $('#divAmanah6').hide();
            $('#btnTambah5').show();
        }
        if(value == "7"){
            $('#divAmanah7').hide();
            $('#btnTambah6').show();
        }
        if(value == "8"){
            $('#divAmanah8').hide();
            $('#btnTambah7').show();
        }
        if(value == "9"){
            $('#divAmanah9').hide();
            $('#btnTambah8').show();
        }
        if(value == "10"){
            $('#divAmanah10').hide();
            $('#btnTambah9').show();
        }
    }

    function doValidation(){
        var val = $('#kod_warganegara').val();
        var val2 = $('#alamat1').val();
        var val3 = $('#nama_pemohon').val();
        var val4 = $('#jenis_pihak').val();
        if(val3 == ''){
            alert('Sila masukan nama pihak.');
            return false;
        } else if(val == ''){
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
                alert('Sila masukan maklumat pihak dipegang amanah dengan lengkap.');
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
                $('#perut').hide();
                $('#luak').hide();
            }

            else{
                $('#suku').show();
                $('#perut').show();
                $('#luak').show();
            }
        }

        function changeJenisKP(value){
            if(value == "B" || value == "L" || value == "P" || value == "T" || value == "I" || value == "F"){
                $('#kodBangsa').hide();
                $('#kodBangsaOrang').show();
                $('#kodBangsaSyarikat').hide();
                $('#tableCaw').hide();
                $('#btnCaw').hide();
                $('#btnAhli').hide();
            }

            else{
                $('#kodBangsa').hide();
                $('#kodBangsaOrang').hide();
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
                var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?deleteCawangan&idCawangan='+val;
                $.get(url,
                function(data){
                    $('#caw').html(data);
                },'html');
                location.reload(true);
            }
        }

        function removeCawanganPemohon(val){
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?deleteCawanganPemohon&idCawangan='+val;
                $.get(url,
                function(data){
                    $('#caw').html(data);
                },'html');
                location.reload(true);
            }
        }

        function removePengarah(val){
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?deletePengarah&idPengarah='+val;
                $.get(url,
                function(data){
                    $('#caw').html(data);
                },'html');
                location.reload(true);
            }
        }

        function removePengarahPemohon(val){
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?deletePengarahPemohon&idPengarah='+val;
                $.get(url,
                function(data){
                    $('#caw').html(data);
                },'html');
                location.reload(true);
            }
        }


        function editCawangan(val){
            var jenisPB = $('#jenis_pihak').val();
            var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?editCawangan&idCawangan='+val+'&jenisPB='+jenisPB;
            $.get(url,
            function(data){
                $('#caw').html(data);
            },'html');
        }

        function editCawanganPemohon(val){
            var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?editCawanganPemohon&idCawangan='+val;
            $.get(url,
            function(data){
                $('#caw').html(data);
            },'html');
        }

        function editPengarah(val){
            var jenisPB = $('#jenis_pihak').val();
            var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?editPengarah&idPengarah='+val+'&jenisPB='+jenisPB;
            $.get(url,
            function(data){
                $('#caw').html(data);
            },'html');
        }

        function editPengarahPemohon(val){
            var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?editPengarahPemohon&idCawangan='+val;
            $.get(url,
            function(data){
                $('#caw').html(data);
            },'html');
        }

        function selectName(val){
            var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?selectName&idPihak='+val;
            $.get(url,
            function(data){
                $('#caw').html(data);
            },'html');
        }

        function selectNamePemohon(val){
            var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?selectNamePemohon&idPihak='+val;
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
    <s:form beanclass="etanah.view.stripes.pengambilan.pActionBean" >
        <s:hidden name="jenisPihak"/>
        <s:hidden name="pihak.nama"/>
        <s:hidden name="pihak.idPihak"/>
        <s:hidden name="permohonanPihak.jenis.kod"/>
        <s:hidden name="pihakCawangan.idPihakCawangan"/>
        <s:hidden name="jenisPihak" value="${jenis}"/>
        <s:hidden name="hakmilik" value="${hakmilik}"/>
        <s:errors/>
        <s:messages/>


            <fieldset class="aras1">
                <legend>
                        BORANG ADUAN PENGAMBILAN SEKSYEN 4

                </legend>
                <br/>

                <c:if test="${!actionBean.cariFlag}">
                    <p>
                        <label for="nama"><font color="red">*</font>Id Hakmilik :</label>
                        <s:text name="hakmilikUrusan.hakmilik.idHakmilik" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <c:if test="${fn:length(actionBean.hakmilikByList) > 0}">
                        <div align="center">
                            <display:table class="tablecloth" name="${actionBean.hakmilikByList}" cellpadding="0" cellspacing="0" id="line" pagesize="10"
                                           requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                <display:column  title="No Hakmilik" class="nama">
                                        <a href="#" onclick="selectName('${line.hakmilik.idhakmilik}');return false;">${line.hakmilik.idhakmilik}</a>

                                </display:column>
                                <display:column title="Urusan" />
                                <display:column title="Tarikh Permohonan" />
                            </display:table>
                        </div>
                    </c:if>
                    <s:submit name="cariHakmilikUrusan" value="Cari" class="btn"/>
                    <s:submit name="cariSemulaPemohon" value="Isi Semula" class="btn"/>&nbsp;


                </c:if>

                <c:if test="${actionBean.cariFlag}">

                    <c:if test="${!actionBean.tiadaDataFlag}">
                     <p>
                        <label for="nama"><font color="red">*</font>Id Hakmilik :</label>
                        <s:text name="hakmilikUrusan.hakmilik.idHakmilik" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <c:if test="${fn:length(actionBean.hakmilikByList) > 0}">
                        <div align="center">
                            <display:table class="tablecloth" name="${actionBean.hakmilikByList}" cellpadding="0" cellspacing="0" id="line" pagesize="10"
                                           requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                <display:column  title="No Hakmilik" class="nama">
                                        <a href="#" onclick="selectName('${line.hakmilik.idhakmilik}');return false;">${line.hakmilik.idhakmilik}</a>

                                </display:column>
                                <display:column title="Urusan" />
                                <display:column title="Tarikh Permohonan" />
                            </display:table>
                        </div>
                    </c:if>
                    <s:submit name="cariHakmilikUrusan" value="Cari" class="btn"/>
                    <s:submit name="cariSemulaPemohon" value="Isi Semula" class="btn"/>&nbsp;
            
                    </c:if>
                    <%--tiada data--%>
                    <c:if test="${actionBean.tiadaDataFlag}">






                    </c:if>






                    </c:if>
</fieldset>


    </s:form>
</div>