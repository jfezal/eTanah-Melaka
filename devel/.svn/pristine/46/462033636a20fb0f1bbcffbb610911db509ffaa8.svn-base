<%-- 
    Document   : lupaKataLaluan
    Created on : Jul 20, 2011, 12:13:51 PM
    Author     : amir.muhaimin
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/pub/styles/jquery.tooltip.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.tools.min.js?select=full&debug=true"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    function validateForm(f) {

        if(f.elements['pguna.idPengguna'].value == '') {
            alert('Sila masukkan Id Pengguna.');
            f.elements['pguna.idPengguna'].focus();
            return false;
        }
        else if(f.elements['pguna.noPengenalan'].value == '') {
            alert('Sila masukkan Kad Pengenalan.');
            f.elements['pguna.noPengenalan'].focus();
            return false;
        }
        else return true;
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
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.uam.LupaKataLaluanBean">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>

    <div class="subtitle" style="">
        <fieldset class="aras1">
            <legend>Terlupa KataLaluan</legend>

            <font size="1" color="Red"><em>Sila masukkan ID Pengguna dan No Kad Pengenalan</em></font>

            <p>
                <label><font color="red">*</font>Id Pengguna :</label>
                    <s:text name="pguna.idPengguna"/>
            </p>
            <p>
                <label><font color="red">*</font>No Kad Pengenalan :</label>
                <s:text name="pguna.noPengenalan" id="kp" onkeyup="validateNumber(this,this.value);" maxlength="12"/>
                <font size="1" color="red">(cth: 840706045494)</font>
            </p>

            <br>
            <p>
                <label>&nbsp;</label>

                <s:submit name="forgotPassword" id="search" value="Hantar ke Email" class="btn" onclick="return validateForm(this.form)"/>

                <s:submit name="kembali1" value="Kembali" class="btn"/>
            </p>
        </fieldset>
    </div>

</s:form>