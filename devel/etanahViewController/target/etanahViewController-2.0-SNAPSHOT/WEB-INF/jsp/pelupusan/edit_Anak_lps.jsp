<%--
    Document   : edit_Anak
    Created on : Aug 09, 2010, 12:30:15 PM
    Author     : Rohan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
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
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">


    $(document).ready( function(){
        maximizeWindow();
    <c:if test="${!flag}">
                   opener.refreshMaklumatPemohon();
                   //alert("self1");
                   self.close();
    </c:if>

             });

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

<s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPemohonLPSActionBean">
    <div class="subtitle" id="caw" >
        <fieldset class="aras1">
            <s:hidden name="pemohonHbgn.idHubungan"/>
            <div align="center">
                <s:errors/>
                <s:messages/>
            </div>
            <legend>
                Kemaskini  Maklumat Anak Pemohon  
            </legend>
            <p>
                <label><font color="red">*</font>Nama :</label>
                <s:text name="pemohonHbgn.nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label><font color="red">*</font>Jantina :</label>
                <s:select name="pemohonHbgn.kodJantina" id="jantina" value="kod">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:option value="1">Lelaki</s:option>
                    <s:option value="2">Perempuan</s:option>
                </s:select>
            </p>
            <p>
                <label><font color="red">*</font>Umur :</label>
                <s:text name="pemohonHbgn.umur" size="10" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label>Nama Sekolah :</label>
                <s:text name="pemohonHbgn.institusi" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label>&nbsp;</label>
                <s:submit name="simpanEditAnak" id="simpanEdit" value="Simpan" class="btn" />
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>
    </div>
</s:form>
