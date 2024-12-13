<%--
    Document   : maklumat_sediatanah
    Created on : Jul 1, 2010, 11:43:48 AM
    Author     : User. editted by Zadirul
--%>

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
    function validateForm()
    {
        var jarakDari = document.form2.jarakDari.value;
        var lokasiTanah = document.form2.lokasiTanah.value;

        if(jarakDari == ""){
            alert('Sila masukan " Jarak Dari " terlebih dahulu.');
            $("#jarakDari").focus();
            return false;
        }else if(lokasiTanah == ""){
            alert('Sila masukan " Lokasi Tanah " terlebih dahulu.');
            $("#lokasiTanah").focus();
            return false;
        }else{
            return true;
        }
    }
    function simpanPerihal(event, f){
        
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div').html(data);
                self.close();
            },'html');
      
    }
            

</script>
<%@include file="/WEB-INF/jsp/strata/pbbm/maklumatTanah.jsp" %>
<s:form name="form2" beanclass="etanah.view.strata.MaklumatTanahActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Perihal Tanah
            </legend>
            <s:messages/>
            <s:errors/>
            <br>
            <p>
                <label>Jarak Dari Pusat Bandar :</label>
                <s:text size="30"name="jarakDari" id="jarakDari" onkeyup="validateNumber(this,this.value);"/> km
            </p>
            <p>
                <label>Lokasi Tanah :</label>
                <s:textarea cols="30" rows="5" class="normal_text"name="lokasiTanah" id="lokasiTanah"/>
            </p>
            <p></p>
            <p><label>&nbsp;</label>
                <s:button name="simpanPerihalTanahRuangUdara" value="Simpan" class="btn" onclick="if(validateForm()){doSubmit(this.form, this.name, 'page_div');}"/>
            </p>

        </fieldset>
    </div>
</s:form>