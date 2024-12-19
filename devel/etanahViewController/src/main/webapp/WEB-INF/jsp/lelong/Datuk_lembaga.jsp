<%-- 
    Document   : Datuk_lembaga
    Created on : May 10, 2011, 4:49:47 PM
    Author     : Administrator
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

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

    function getDatokLembaga(kodSuku){
        if(kodSuku != ""){
            var url = '${pageContext.request.contextPath}/lelong/datuk_lembaga?getDatokLembaga&kodSuku='+kodSuku;
            $.get(url,
            function(data){
                $('#display').html(data);
            });
        }
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.lelong.DatukLembagaActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle" id="display">
        <s:hidden name="senaraiRujukan.kod"/>

        <fieldset class="aras1">
            <legend>Maklumat Datok Lembaga</legend>
                <p id="suku">
                    <label for="Suku"><font color="red">*</font>Nama Suku :</label>
                    <s:select name="senaraiRujukan.senarai.kod" style="width:200px" onchange="getDatokLembaga(this.value);">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.kodSenaraiList}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p>
                    <label><font color="red">*</font>Nama Gelaran :</label>
                    <s:text name="senaraiRujukan.perihal"  size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label><font color="red">*</font>Nama Datok Lembaga :</label>
                    <s:text name="senaraiRujukan.nama"  size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label for="alamat"><font color="red">*</font>Alamat :</label>
                    <s:text name="senaraiRujukan.alamat.alamat1" id="suratAlamat1" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="senaraiRujukan.alamat.alamat2" id="suratAlamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="senaraiRujukan.alamat.alamat3" id="suratAlamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> Bandar :</label>
                    <s:text name="senaraiRujukan.alamat.alamat4" id="suratAlamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label><font color="red">*</font>Poskod :</label>
                    <s:text name="senaraiRujukan.alamat.poskod" id="suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                </p>

                <p>
                    <label for="Negeri"><font color="red">*</font>Negeri :</label>
                    <s:select name="negeri" id="suratNegeri">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p>
                    <label>Nombor Telefon :</label>
                    <s:text name="senaraiRujukan.noTel1" id="noTelefon" size="40" maxlength="15" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </fieldset>
    </div>
</s:form>
