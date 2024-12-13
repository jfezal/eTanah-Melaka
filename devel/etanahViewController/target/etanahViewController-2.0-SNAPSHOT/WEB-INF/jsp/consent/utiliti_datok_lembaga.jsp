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

    function isiSemula(){
        $('#suku').val('');
        $('#gelaran').val('');
        $('#nama').val('');
        $('#alamat1').val('');
        $('#alamat2').val('');
        $('#alamat3').val('');
        $('#alamat4').val('');
        $('#poskod').val('');
        $('#negeri').val('');
        $('#telefon').val('');
    }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.consent.UtilitiDatokLembagaActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle" id="display">
        <s:hidden name="senaraiRujukan.kod"/>
        <fieldset class="aras1">
            <legend>Maklumat Datok Lembaga</legend>
            <p>
                <label for="Suku"><font color="red">*</font>Jenis Suku :</label>
                <s:text name="kodSenarai.nama"  id="suku" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label><font color="red">*</font>Nama Gelaran :</label>
                <s:text name="senaraiRujukan.perihal" id="gelaran" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label><font color="red">*</font>Nama Datok Lembaga :</label>
                <s:text name="senaraiRujukan.nama"  id="nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label for="alamat"><font color="red">*</font>Alamat :</label>
                <s:text name="senaraiRujukan.alamat.alamat1" id="alamat1" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="senaraiRujukan.alamat.alamat2" id="alamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="senaraiRujukan.alamat.alamat3" id="alamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> Bandar :</label>
                <s:text name="senaraiRujukan.alamat.alamat4" id="alamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name="senaraiRujukan.alamat.poskod" id="poskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label for="Negeri">Negeri :</label>
                <s:select name="senaraiRujukan.alamat.negeri.kod" id="negeri">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
                <label>Nombor Telefon :</label>
                <s:text name="senaraiRujukan.noTel1" id="telefon" size="40" maxlength="15" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:submit name="simpan" value="Simpan" class="btn" onclick=""/>
                <s:button name="" value="Isi Semula" class="btn" onclick="isiSemula()"/>
            </p>
            <br/>
        </fieldset>
    </div>
</s:form>

