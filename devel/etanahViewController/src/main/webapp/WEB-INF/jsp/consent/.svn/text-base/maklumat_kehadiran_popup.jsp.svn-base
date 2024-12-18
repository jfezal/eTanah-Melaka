<%--
    Document   : kemasukan_waris
    Created on : Mar 17, 2010, 12:51:37 PM
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
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $('.alphanumeric').alphanumeric();

    });

    function save(event, f){
        if(doValidation()){
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.close();
            },'html');
        }
    }

    function doValidation(){
        var noKp = $('#kp').val();
        var jenisKp = $('#jenisKp').val();
        var nama = $('#nama').val();
        var jawatan = $('#jawatan').val();

        if(nama == ''){
            alert('Sila Masukkan Nama.');
            return false;
        }else if(jenisKp == ''){
            alert('Sila Masukkan Jenis Pengenalan.');
            return false;
        }else if(noKp == ''){
            alert('Sila Masukkan Nombor Pengenalan.');
            return false;
        }
        else if (jawatan == ''){
            alert('Sila Masukkan Jenis Pihak.');
            return false;
        }

        return true;
    }

    function selectName(val){
        var url = '${pageContext.request.contextPath}/consent/maklumat_kehadiran?selectName&idPihak='+val;
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

</script>
<style type="text/css">
    input.error { background-color: yellow; }
</style>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<div class="subtitle" id="caw">
    <s:form beanclass="etanah.view.stripes.consent.MaklumatKehadiranActionBean" >
        <s:errors/>
        <s:messages/>

        <fieldset class="aras1">
            <legend>Kemasukan Maklumat Kehadiran</legend>
            <p>
                <label for="nama"><font color="red">*</font>Nama :</label>
                <s:text name="perbicaraanKehadiran.nama" id="nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                <s:select name="perbicaraanKehadiran.jenisPengenalan.kod" id="jenisKp">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
                <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                <s:text name="perbicaraanKehadiran.noPengenalan" id="kp" size="40"/>&nbsp;<em style="font-style:normal">(cth : 800620125177)</em>
            </p>
            <p>
                <label for="jenis"><font color="red">*</font>Jenis Pihak :</label>
                <s:text name="perbicaraanKehadiran.jawatan" id="jawatan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <div align="center">
                <s:button name="simpanKehadiranPopup" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </div>
            <br>
        </fieldset>
    </s:form>
</div>
