<%-- 
    Document   : pihak_wakil
    Created on : Nov 30, 2011, 7:36:27 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
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
</script>
<s:form  beanclass="etanah.view.stripes.pelupusan.PihakwakilActionBean">
    <s:errors/>
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend> Maklumat Wakil(Jika Ada) </legend>

            <p>
                <label>Nama :</label>
                <s:text name="nama" id="nama" size="40"/>
            </p>
            <p>
                <label>No Pengenalan :</label>

                <s:text name="noPengenalan" id="noPengenalan" size="20" maxlength="12" onkeyup="validateNumber(this,this.value);"/>
                <em>[700304059873]</em>

            </p>
            <p>
                <label>Kos (RM) :</label>

                <s:text name="nilai" size="10" id="nilai" onkeyup="validateNumber(this,this.value);"/>

            </p>
            <p>
                <label>Catatan :</label>

                <s:textarea name="dalamanNilai1" id="dalamanNilai1" cols="40" rows="3" />
            </p>

            <p>
                <label>&nbsp;</label>
                <s:button name="simpanPihak" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
        </fieldset>
    </div>
</s:form>
