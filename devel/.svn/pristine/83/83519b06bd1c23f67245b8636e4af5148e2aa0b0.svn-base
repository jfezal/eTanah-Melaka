<%-- 
    Document   : maklumat_perintah_popup
    Created on : May 23, 2012, 3:41:22 PM
    Author     : muhammad.amin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.2.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ageCalculator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $('.alphanumeric').alphanumeric();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy', changeMonth:true, changeYear: true});
        doBlockUI();

        var jenisKp = $('#jenisKp').val();
        if(jenisKp == "B"){
            var icNo = $('#kp').val();

            if(icNo.length == 12){
                var year = icNo.substring(0,2);

                if(year > 25 && year < 99){//fixme :
                    year = "19" + year;
                }else {
                    year = "20" + year;
                }

                if( $('#kp').val() != ''){
                    var age = calculateAge(icNo.substring(4,6), icNo.substring(2,4), year);
                    $('#umur').val(age);
                }
            }
        }
        $.unblockUI();
    });

    function save(event, f){
        if(doValidation()){
            doBlockUI();
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                $.unblockUI();
                self.close();
            },'html');
        }
    }

    function doValidation(){
  <%--      var noKp = $('#kp').val();
        var jenisKp = $('#jenisKp').val();
        var nama = $('#nama').val();
        var waris = $('#waris').val();
        var umur = $('#umur').val();
        var alamat = $('#alamat1').val();

        if(nama == ''){
            alert('Sila Masukkan Nama.');
            return false;
        }else if(jenisKp == ''){
            alert('Sila Masukkan Jenis Pengenalan.');
            return false;
        }else if(noKp == ''){
            alert('Sila Masukkan Nombor Pengenalan.');
            return false;
        } else if (waris == ''){
            alert('Sila Masukkan Jenis Waris.');
            return false;
        }
        else if (umur == ''){
            alert('Sila Masukkan Umur.');
            return false;
        }
        else if (alamat == ''){
            alert('Sila Masukkan Alamat.');
            return false;
        }
--%>
        return true;
    }

    function selectName(val){
        var url = '${pageContext.request.contextPath}/consent/kemasukan_waris?selectName&idPihak='+val;
        $.get(url,
        function(data){
            $('#caw').html(data);
        },'html');
    }

    function changeIcNumber(icNo){

        var jenisKp = $('#jenisKp').val();

        if(jenisKp == 'B'){
            if(icNo.length == 12){
                var year = icNo.substring(0,2);

                if(year > 25 && year < 99){//fixme :
                    year = "19" + year;
                }else {
                    year = "20" + year;
                }

                if( $('#kp').val() != ''){
                    var age = calculateAge(icNo.substring(4,6), icNo.substring(2,4), year);
                    $('#umur').val(age);
                }
            }
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
        }
    }

    function doBlockUI () {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
    }

</script>
<style type="text/css">
    input.error { background-color: yellow; }
</style>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none" alt=""/>
<div class="subtitle" id="caw">
    <s:form beanclass="etanah.view.stripes.consent.MaklumatPerintahActionBean" >
        <s:hidden name="checkKPSHPopup"/>
        <%--<s:hidden name="checkKKPPopup"/>--%>
        <s:hidden name="checkPPSHPopup"/>
        <s:hidden name="checkPKPPopup"/>
        <s:hidden name="checkPPAPopup"/>
        <s:hidden name="mohonLampiranPerintah.idPermohonanPerintah"/>
        <s:errors/>
        <s:messages/>
        <fieldset class="aras1">
            <legend>Kemasukan Maklumat Perintah</legend>
            <c:if test="${actionBean.checkKPSHPopup eq 'Y'}">

                <p>
                    <label><font color="red">*</font>Nama Penghuni :</label>
                    <s:text name="namaKPSH" size="40" id="nama" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label><font color="red">*</font>Nombor Pengenalan :</label>
                    <s:text name="pengenalanKPSH" size="40" id="nama" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label><font color="red">*</font>Syer :</label>
                    <s:text name="syerKPSH" size="40" id="nama" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
            </c:if>
          <%--  <c:if test="${actionBean.checkKKPPopup eq 'Y'}">
                <p>
                    <label><font color="red">*</font>Kaveat Kepada :</label>
                    <s:text name="namaKKP" size="40" id="nama2" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label><font color="red">*</font>Nombor Pengenalan :</label>
                    <s:text name="pengenalanKKP" size="40" id="abc" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
            </c:if>--%>
            <c:if test="${actionBean.checkPPSHPopup eq 'Y'}">
                <p>
                    <label><font color="red">*</font>Nama Penghuni :</label>
                    <s:text name="namaPPSH" size="40" id="c" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label><font color="red">*</font>Nombor Perserahan :</label>
                    <s:text name="noSerahanPPSH" size="40" id="d" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label><font color="red">*</font>Tarikh Perserahan :</label>
                    <s:text name="tarikhPPSH"  id="datepicker" size="40" class="datepicker" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
               <%-- <p>
                    <label><font color="red">*</font>Syer :</label>
                    <s:text name="syerPPSH" size="40" id="e" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>--%>
            </c:if>
            <c:if test="${actionBean.checkPKPPopup eq 'Y'}">
                <p>
                    <label><font color="red">*</font>Nama Pemegang Amanah (Dibatalkan) :</label>
                    <s:text name="namaPKP" size="40" id="c" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label><font color="red">*</font>Nombor Perserahan :</label>
                    <s:text name="noSerahanPKP" size="40" id="d" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label><font color="red">*</font>Tarikh Perserahan :</label>
                    <s:text name="tarikhPKP"  id="datepicker" size="40" class="datepicker" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>

            </c:if>
            <c:if test="${actionBean.checkPPAPopup eq 'Y'}">
                <p>
                    <label><font color="red">*</font>Nama Pemegang Amanah (Dibatalkan) :</label>
                    <s:text name="namaPPA" size="40" id="c" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label><font color="red">*</font>Nombor Perserahan :</label>
                    <s:text name="noSerahanPPA" size="40" id="d" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label><font color="red">*</font>Tarikh Perserahan :</label>
                    <s:text name="tarikhPPA"  id="datepicker" size="40" class="datepicker" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
            </c:if>
            <div align="center">
                <s:button name="simpanEdit" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </div>
            <br>
        </fieldset>

    </s:form>
</div>