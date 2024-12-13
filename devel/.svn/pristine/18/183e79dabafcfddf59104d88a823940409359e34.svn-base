<%-- 
    Document   : userPassword
    Created on : Nov 1, 2010, 13:49:17 PM
    Author     : izam
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
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

    function test(f) {
        $(f).clearForm();
    }

</script>
<s:form beanclass="etanah.view.uam.UserPasswordActionBean">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Tukar Kata Laluan Pengguna</legend>
            <p>
                <label><font color="red">*</font>Id Pengguna :</label>
                ${actionBean.idPengguna}
                <input type="hidden" name="pguna.idPengguna" value="${actionBean.idPengguna}"/>
            </p>
            <br />
            <p>
                <label><font color="red">*</font>Kata Laluan Baru :</label>
                <s:password name="pguna.password" value="${actionBean.pguna.password}"/>
            </p>
            <p>
                <label><font color="red">*</font>Pengesahan Kata Laluan Baru :</label>
                <s:password name="sahKataLaluan" value="${actionBean.sahKataLaluan}"/>
            </p>
        </fieldset>
            <p>
            <label>&nbsp;</label>
            <s:submit name="change" id="save" value="Tukar" class="btn"/>
            <s:submit name="showForm" value="Isi Semula" class="btn" onclick="test(this.form);"/>
            <s:submit name="kembali" value="Kembali" class="btn"/>
        </p>
    </div>
</s:form>
