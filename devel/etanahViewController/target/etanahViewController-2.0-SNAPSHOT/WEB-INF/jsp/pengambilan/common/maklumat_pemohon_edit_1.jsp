
<%-- 
    Document   : maklumat_pemohon_edit_1
    Created on : 19-Jan-2011, 15:44:32
    Author     : nordiyana @modified hazirah
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
        }

        else{
            $('#suku').show();
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
            $('#datepicker').val(icNo.substring(4,6)+"/"+icNo.substring(2,4)+"/"+19 + icNo.substring(0,2));
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

    });

    function setDOB(value){

        if($('#jenisPengenalan').val() == "B"){

            var icNo = value;
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
    }

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
        }else{
            $('#suku').show();
        }
    }

    function changePgAmanah(value){
        if(value == "Pemegang Amanah"){
            $('#pgAmanah').show();
        }else{
            $('#pgAmanah').hide();
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
        </c:otherwise>
    </c:choose>
                self.close();
            },'html');
        }

        function changeHideSuku(value){
            if(value == "S"){
                $('#suku').hide();
            }
            else{
                $('#suku').show();
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
                var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon1?deleteCawangan&idCawangan='+val;
                $.get(url,
                function(data){
                    $('#caw').html(data);
                },'html');
                location.reload(true);
            }
        }

        function removeCawanganPemohon(val){
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon1?deleteCawanganPemohon&idCawangan='+val;
                $.get(url,
                function(data){
                    $('#caw').html(data);
                },'html');
                location.reload(true);
            }
        }

        function removePengarah(val){
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon1?/pengambilan/pengambilan_pemohon&idPengarah='+val;
                $.get(url,
                function(data){
                    $('#caw').html(data);
                },'html');
                location.reload(true);
            }
        }

        function removePengarahPemohon(val){
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon1?/pengambilan/pengambilan_pemohonPemohon&idPengarah='+val;
                $.get(url,
                function(data){
                    $('#caw').html(data);
                },'html');
                location.reload(true);
            }
        }


        function editCawangan(val, val2){
            var jenisPB = $('#jenis_pihak').val();
            var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon1?editCawanganEdit&idCawangan='+val+'&jenisPB='+jenisPB+'&idPermPihak='+val2;
            $.get(url,
            function(data){
                $('#caw').html(data);
            },'html');
        }

        function editCawanganPemohon(val,val2){
            var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon1?editCawanganPemohonEdit&idCawangan='+val+'&idPemohon='+val2;
            $.get(url,
            function(data){
                $('#caw').html(data);
            },'html');
        }

        function editPengarah(val, val2){
            var jenisPB = $('#jenis_pihak').val();
            var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon1?editPengarahEdit&idPengarah='+val+'&jenisPB='+jenisPB+'&idPermPihak='+val2;
            $.get(url,
            function(data){
                $('#caw').html(data);
            },'html');
        }

        function editPengarahPemohon(val, val2){
            var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon1?editPengarahPemohon&idPengarah='+val+'&idPemohon='+val2;
            $.get(url,
            function(data){
                $('#caw').html(data);
            },'html');
        }

        function selectName(val){
            var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon1?selectName&idPihak='+val;
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
    <s:form beanclass="etanah.view.stripes.pengambilan.pActionBean_1" >
        <s:hidden name="jenisPihak"/>
        <%--<s:hidden name="pihak.nama"/>--%>
        <s:hidden name="pihak.idPihak"/>
        <s:hidden name="pemohon.idPemohon"/>
        <s:hidden name="permohonanPihak.idPermohonanPihak"/>
        <s:hidden name="pihakCawangan.idPihakCawangan"/>
        <s:hidden name="jenisPihak" value="${jenis}"/>
        <s:hidden name="hakmilik" value="${hakmilik}"/>

        <c:if test="${tuanTanah}">
            <s:text name="tuanTanah" value="${tuanTanah}"/>
        </c:if>
        <s:errors/>
        <s:messages/>
        <c:if test="${!actionBean.tambahCawFlag && !actionBean.tambahPengarahFlag}">

            <fieldset class="aras1">
                <legend>
                    <c:if test="${!penerima}">
                        Kemaskini Maklumat Pemohon
                    </c:if>
                    <c:if test="${penerima}">
                        Kemaskini Maklumat Penerima
                    </c:if>
                </legend>
                <br/>
                <p>
                    <label for="nama">Nama :</label>
                    <s:text name="pihak.nama" size="50" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <%--                <p>
                                    <label for="Jenis Pengenalan">Jenis Pengenalan :</label>
                                    ${actionBean.pihak.jenisPengenalan.nama}&nbsp;
                                </p>
                                <p>
                                    <label for="No Pengenalan">Nombor Pengenalan :</label>
                                    ${actionBean.pihak.noPengenalan}&nbsp;
                                </p>--%>

                <p>
                    <label for="Jenis Pengenalan">Jenis Pengenalan :</label>
                    <s:select name="pihak.jenisPengenalan.kod" id="jenisPengenalan">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                    </s:select>
                </p>

                <p>
                    <label for="No Pengenalan">Nombor Pengenalan :</label>
                    <s:text name="pihak.noPengenalan" id="kp" size="40" onkeyup="dodacheck(this.value);this.value=this.value.toUpperCase();" onblur="setDOB(this.value);"/>
                </p>
                <p>
                    <label>Nombor Pengenalan Lain :</label>
                    <s:text name="pihak.noPengenalanLain" id="kpl" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>Bangsa / Jenis Syarikat :</label>

                    <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                        <s:select name="pihak.bangsa.kod" style="width:400px" id="kodBangsaOrang">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${actionBean.senaraiKodBangsaOrang}" label="nama" value="kod"/>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.pihak.jenisPengenalan.kod ne 'B' && actionBean.pihak.jenisPengenalan.kod ne 'L' && actionBean.pihak.jenisPengenalan.kod ne 'P' && actionBean.pihak.jenisPengenalan.kod ne 'T' && actionBean.pihak.jenisPengenalan.kod ne 'I'}">

                        <s:select name="pihak.bangsa.kod" style="width:400px" id="kodBangsaSyarikat">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${actionBean.senaraiKodBangsaSyarikat}" label="nama" value="kod"/>
                        </s:select>
                    </c:if>
                </p>
                <c:if test="${actionBean.kodNegeri eq '05'}">
                    <p id="suku">
                        <label for="Suku">Jenis Suku :</label>
                        <s:select name="pihak.suku.kod" style="width:200px">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodSuku}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                </c:if>

                <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                    <p>
                        <label>Tarikh Lahir :</label>
                        <s:text name="tarikhLahir" size="40" id="datepicker" class="datepicker" onchange="calAgeByDOB(this.value);" formatPattern="dd/mm/yyyy"/>&nbsp;<em style="font-style:normal">(cth : hh / bb / tttt)</em>
                    </p>
                    <%--//---------------for penerima---------------%>
                    <c:if test="${penerima}">
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
                            <label>Kewarganegaraan :</label>
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
                    <%--//---------------for pemohon---------------%>
                    <c:if test="${!penerima}">
                        <p>
                            <label>Umur :</label>
                            <s:text name="pemohon.umur" size="10" maxlength="3" id="umur" onkeyup="validateNumber(this,this.value);"/>
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
                            <label>Kewarganegaraan :</label>
                            <s:hidden name="pihak.wargaNegara.kod" id=""/>
                            <s:select name="pihak.wargaNegara.kod" style="width:200px" id="kod_warganegara">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <p>
                            <label>Status Perkahwinan :</label>
                            <s:select name="pemohon.statusKahwin">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option>Berkahwin</s:option>
                                <s:option>Bujang</s:option>
                                <s:option>Duda</s:option>
                                <s:option>Ibu Tunggal</s:option>
                            </s:select>
                        </p>
                        <p>
                            <label>Pekerjaan :</label>
                            <s:text name="pemohon.pekerjaan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Pendapatan Bulanan (RM) :</label>
                            <s:text name="pemohon.pendapatan" size="40" maxlength="12" onkeyup="validateNumber(this,this.value);"/>
                        </p>
                        <p>
                            <label>Tanggungan :</label>
                            <s:text name="pemohon.tanggungan" size="40" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                        </p>
                    </c:if>

                </c:if>
                <c:if test="${penerima}">
                    <p>
                        <label>Hubungan Dengan Pemohon :</label>
                        <s:select name="permohonanPihak.kaitan" id="hubungan" onchange="javaScript:changeHubungan(this.options[selectedIndex].text);">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option>Ibu Bapa Kepada Anak</s:option>
                            <s:option>Anak Kepada Ibu Bapa</s:option>
                            <s:option>Suami Kepada Isteri</s:option>
                            <s:option>Isteri Kepada Suami</s:option>
                            <s:option>Saudara-mara</s:option>
                            <s:option>Penjual / Pembeli</s:option>
                            <s:option>LAIN-LAIN</s:option>
                        </s:select>
                    </p>
                    <p id="hubunganLain">
                        <label>Lain-lain Hubungan :</label>
                        <s:text name="hubunganLain" size="40" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label for="nama"><font color="red">*</font>Jenis Pihak Berkepentingan :</label>
                        <s:select name="jenisPihak" style="width:400px" id="jenis_pihak" onchange="javaScript:changePgAmanah(this.options[selectedIndex].text);">
                            <s:option value="" >Sila Pilih</s:option>
                            <s:options-collection collection="${actionBean.senaraiKodPenerima}" label="nama" value="kod" />
                        </s:select>
                    </p>
                </c:if>
                <c:if test="${!penerima}">
                    <p>
                        <label>Hubungan Dengan Penerima :</label>
                        <s:select name="pemohon.kaitan" id="hubungan" onchange="javaScript:changeHubungan(this.options[selectedIndex].text);">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option>Ibu Bapa Kepada Anak</s:option>
                            <s:option>Anak Kepada Ibu Bapa</s:option>
                            <s:option>Suami Kepada Isteri</s:option>
                            <s:option>Isteri Kepada Suami</s:option>
                            <s:option>Saudara-mara</s:option>
                            <s:option>Penjual / Pembeli</s:option>
                            <s:option>LAIN-LAIN</s:option>
                        </s:select>
                    </p>
                    <p id="hubunganLain">
                        <label>Lain-lain Hubungan :</label>
                        <s:text name="hubunganLain" size="40" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                </c:if>
                <p>
                    <label for="alamat"><font color="red">*</font>Alamat Berdaftar :</label>
                    <s:text name="pihak.alamat1" id="alamat1" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihak.alamat2" id="alamat2" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihak.alamat3" id="alamat3" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> Bandar :</label>
                    <s:text name="pihak.alamat4" id="alamat4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
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
                            <label>No.Telefon :</label>
                            <s:text name="pihak.noTelefon1" maxlength="11" id="notelefon"  /><font color="red">*</font>

                        </p>
                        <p>
                            <label>No.Fax :</label>
                            <s:text name="pihak.noTelefon2" maxlength="11" id="notelefon"  />

                        </p>
                        <p>
                            <label>Email :</label>
                            <s:text name="pihak.email" size="40" maxlength="100" />

                        </p>
                <p>
                    <label>&nbsp;</label>
                    <input type="checkbox" id="add1" name="add1" value="" onclick="copyAdd();"/>
                    <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                </p>
                <p>
                    <label for="alamat">Alamat Surat-Menyurat :</label>
                    <s:text name="pihak.suratAlamat1" id="suratAlamat1" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihak.suratAlamat2" id="suratAlamat2" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihak.suratAlamat3" id="suratAlamat3" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> Bandar :</label>
                    <s:text name="pihak.suratAlamat4" id="suratAlamat4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
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
                                    <font style="text-transform:uppercase;"> ${line.suratNegeri.nama}</font>
                                </display:column>
                                <c:if test="${!penerima}">
                                    <display:column title="Kemaskini">
                                        <p align="center">
                                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                 onclick="editCawanganPemohon('${line.idPihakCawangan}','${actionBean.pemohon.idPemohon}')" onmouseover="this.style.cursor='pointer';">
                                        </p>
                                    </display:column>
                                    <display:column title="Hapus">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="removeCawanganPemohon('${line.idPihakCawangan}')" onmouseover="this.style.cursor='pointer';">
                                        </div>
                                    </display:column>
                                </c:if>

                                <c:if test="${penerima}">
                                    <display:column title="Kemaskini">
                                        <p align="center">
                                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                 onclick="editCawangan('${line.idPihakCawangan}','${actionBean.permohonanPihak.idPermohonanPihak}')" onmouseover="this.style.cursor='pointer';">
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

                                <c:if test="${!penerima}">
                                    <display:column title="Kemaskini">
                                        <p align="center">
                                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                 onclick="editPengarahPemohon('${line2.idPengarah}','${actionBean.pemohon.idPemohon}')" onmouseover="this.style.cursor='pointer';">
                                        </p>
                                    </display:column>
                                    <display:column title="Hapus">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id='rem_${line2_rowNum}' onclick="removePengarahPemohon('${line2.idPengarah}')" onmouseover="this.style.cursor='pointer';">
                                        </div>
                                    </display:column>
                                </c:if>

                                <c:if test="${penerima}">
                                    <display:column title="Kemaskini">
                                        <p align="center">
                                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                 onclick="editPengarah('${line2.idPengarah}','${actionBean.permohonanPihak.idPermohonanPihak}')" onmouseover="this.style.cursor='pointer';">
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
                        Maklumat Pihak Yang Dipegang Amanah
                    </div>
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
                        <s:text name="permohonanPihakHubungan.noPengenalan" id="nokpAmanah" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label for="alamat"><font color="red">*</font>Alamat :</label>
                        <s:text name="permohonanPihakHubungan.alamat1" size="40" id="alamatAmanah1" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>

                    <p>
                        <label> &nbsp;</label>
                        <s:text name="permohonanPihakHubungan.alamat2" size="40" id="alamatAmanah2" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>

                    <p>
                        <label> &nbsp;</label>
                        <s:text name="permohonanPihakHubungan.alamat3" size="40" id="alamatAmanah3" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> Bandar :</label>
                        <s:text name="permohonanPihakHubungan.alamat4" size="40" id="alamatAmanah4" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
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
                </div>
                <c:if test="${penerima}">
                    <c:if test="${fn:length(actionBean.p.senaraiHakmilik) > 1}">
                        <p>
                            <label>&nbsp;</label>
                            <s:checkbox name="semuaIdHakmilik" value="Y"/><font color="red">&nbsp;Penerima ini adalah penerima untuk semua hakmilik.</font>
                        </p>
                    </c:if>
                </c:if>
                <br/>
                <p align="center">
                    <c:if test="${!penerima}">
                        <s:button name="simpanPemohonEdit" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod ne 'B' && actionBean.pihak.jenisPengenalan.kod ne 'L' && actionBean.pihak.jenisPengenalan.kod ne 'P' && actionBean.pihak.jenisPengenalan.kod ne 'T' && actionBean.pihak.jenisPengenalan.kod ne 'I'}">
                            <s:submit name="tambahCawanganPemohon" value="Tambah Cawangan" class="longbtn"/>
                            <s:submit name="tambahPengarahPemohon" value="Tambah Ahli Pengarah" class="longbtn" id="btnAhli" onclick="return validationPB();"/>
                        </c:if>
                    </c:if>
                    <c:if test="${penerima}">
                        <s:button name="simpanPenerimaEdit" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod ne 'B' && actionBean.pihak.jenisPengenalan.kod ne 'L' && actionBean.pihak.jenisPengenalan.kod ne 'P' && actionBean.pihak.jenisPengenalan.kod ne 'T' && actionBean.pihak.jenisPengenalan.kod ne 'I'}">
                            <s:submit name="tambahCawanganEdit" value="Tambah Cawangan" class="longbtn" id="btnCaw" onclick="return validationPB();"/>
                            <s:submit name="tambahPengarahEdit" value="Tambah Ahli Pengarah" class="longbtn" id="btnAhli" onclick="return validationPB();"/>
                        </c:if>
                    </c:if>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
                <br>
            </fieldset>
        </c:if>

        <%--add cawangan--%>

        <c:if test="${actionBean.tambahCawFlag}">
            <s:hidden name="pihakCawangan.negeri.kod"/>

            <fieldset class="aras1">
                <legend>
                    <c:if test="${!penerima}">
                        Kemasukan Cawangan Pemohon
                    </c:if>

                    <c:if test="${penerima}">
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
                    <c:if test="${!penerima}">
                        <s:submit name="simpanCawanganPemohon" value="Simpan" class="btn"/>
                        <s:submit name="backCawanganPemohon" value="Tutup" class="btn"/>
                    </c:if>

                    <c:if test="${penerima}">
                        <s:submit name="simpanCawanganEdit" value="Simpan" class="btn"/>
                        <s:submit name="backCawanganEdit" value="Tutup" class="btn"/>
                    </c:if>
                </p>
                <br>
            </fieldset>
        </c:if>

        <%--Add Lembaga Pengarah--%>

        <c:if test="${actionBean.tambahPengarahFlag}">
            <fieldset class="aras1">
                <legend>
                    <c:if test="${!penerima}">
                        Kemasukan Lembaga Pengarah Pemohon
                    </c:if>

                    <c:if test="${penerima}">
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
                            onblur="doCheckUmur(this.value, this.id,'${jenis}')"/>
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
                    <c:if test="${!penerima}">
                        <s:submit name="simpanPengarahPemohon" value="Simpan" class="btn"/>
                        <s:submit name="backCawanganPemohon" value="Tutup" class="btn"/>
                    </c:if>

                    <c:if test="${penerima}">
                        <s:submit name="simpanPengarahEdit" value="Simpan" class="btn"/>
                        <s:submit name="backCawanganEdit" value="Tutup" class="btn"/>
                    </c:if>
                </p>
                <br>
            </fieldset>
        </c:if>
    </s:form>
</div>
