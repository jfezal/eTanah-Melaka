<%-- 
    Document   : daftar_syarikat_jurulelong
    Created on : Feb 17, 2011, 8:10:45 PM
    Author     : mdizzat.mashrom
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#nokp2").hide();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });

    var DELIM = "__^$__";
    function simpanPihak(event, f){
        if(validation()){

        }
        else{
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.get(url,q,
            function(data){
                if (data == null || data.length == 0){
                    alert("Maklumat tidak dijumpai");
                    return;
                }
                var p = data.split(DELIM);
                $('#idSyarikat', opener.document).append('<option value="'+p[2]+'">'+p[0]+'</option>');
                $('#idSyarikat option[value='+p[2]+']', opener.document).attr('selected','selected');
                $('#noSykt',opener.document).val(p[1]);
                self.close();
            },'html');
        }
    }

    function validation() {
        if($("#nama").val() == ""){
            alert('Sila masukkan " Nama " terlebih dahulu.');
            $("#nama").focus();
            return true;
        }
        if($("#jenis").val() == ""){
            alert('Sila pilih " Jenis Kad Pengenalan " terlebih dahulu.');
            $("#jenis").focus();
            return true;
        }
        return false;
    }

    function validatePoskodLength(value){
        var plength = value.length;
        if(plength != 5){
            alert('Poskod yang dimasukkan salah.');
            $('#poskod').val("");
            $('#poskod').focus();
        }
    }
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
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

    function changeNOKP( val ){
        var no = val;
        if(no == 'B'){
            $("#nokp2").hide();
            $("#nokp1").show();
        }else{
            $("#nokp2").show();
            $("#nokp1").hide();
        }
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.lelong.PendaftaranJurulelongBerlesenActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Syarikat Jurulelong
            </legend>
            <p>
                <label><font color="red">*</font>Nama Syarikat : </label>
                <s:text id="nama" name="sytJuruLelong.nama" onchange="this.value=this.value.toUpperCase();" style="width:260px;"/>
            </p>
            <p>
                <label><font color="red">*</font>Jenis Pengenalan : </label>
                <s:select id="jenis" name="sytJuruLelong.jenisPengenalan.kod" onchange="changeNOKP(this.value);" style="width:150px;">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
                <label> <font color="red">*</font>Nombor Pengenalan : </label>
                <s:text id="nokp1" name="sytJuruLelong.noPengenalan" onkeyup="validateNumber(this,this.value);" style="width:150px;"/>
                <s:text id="nokp2" name="sytJuruLelong.noPengenalan"  onchange="this.value=this.value.toUpperCase();" style="width:150px;"/>

                <font color="red" size="1">(cth : 550201045678)</font>
            </p>

            <p>
                <label><font color="red">*</font>Alamat : </label>
                <s:text id="alamat1" name="sytJuruLelong.alamat1" size="40" maxlength="40" onchange="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="sytJuruLelong.alamat2" size="40" maxlength="40" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="sytJuruLelong.alamat3" size="40" maxlength="40" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="sytJuruLelong.alamat4" size="40" maxlength="40" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
            </p>
            <p>
                <label><font color="red">*</font>Poskod : </label>
                <s:text id="poskod" name="sytJuruLelong.poskod" size="19" maxlength="5" onkeyup="validateNumber(this,this.value);" onchange="validatePoskodLength(this.value);" style="width:150px;"/>&nbsp;
            </p>
            <p>
                <label><font color="red">*</font>Negeri : </label>
                <s:select id="negeri" name="sytJuruLelong.negeri.kod" style="width:150px;">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
                <label>Nombor Telefon Pejabat : </label>
                <s:text id="telefon" name="sytJuruLelong.noTelefon1" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);" style="width:150px;"/>&nbsp;
            </p>
            <p>
                <label>Nombor Telefon Bimbit : </label>
                <s:text name="sytJuruLelong.noTelefon2" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);" style="width:150px;"/>&nbsp;
            </p>

            <div class="content" align="right">
                <p>
                    <s:button name="simpanSyarikat" id="save" value="Simpan" class="btn" onclick="simpanPihak(this.name, this.form);"/>
                    <%--<s:button name="" value="Isi Semula" class="btn" />--%>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
            </div>
        </fieldset>
    </div>
</s:form>