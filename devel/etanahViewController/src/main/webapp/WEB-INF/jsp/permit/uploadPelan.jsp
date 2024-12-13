<%-- 
    Document   : uploadPelan
    Created on : Sep 20, 2015, 10:03:29 AM
    Author     : Hazwan
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">

    function rImage(){       
            self.opener.refreshImej();
            self.close()
    }
    
</script>
<s:errors/>
<s:messages/>
<div id="error"/>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form beanclass="etanah.view.stripes.permit.PermitActionBean">
    <s:hidden name="noFail" id="noFail" value="${actionBean.noFail}"/>
    <s:hidden name="jenisBorang" id="jenisBorang"  value="${actionBean.jenisBorang}" />

    <fieldset class="aras1">
        <legend>Muat Naik</legend>
        <table border="0" width="10%" size="5"><tr><em>* Klik Choose File Untuk Muat Naik Pelan (Format *.tif File Sahaja)</em></tr></table>

        <label>&nbsp;</label>&nbsp;
        <s:file name="fileToBeUpload" id="fileToBeUpload"/>

        <c:if test="${actionBean.fileToBeUpload ne null}">
            ${actionBean.fileToBeUpload.fileName}
        </c:if>
        <br/>
        <p>
            <label>&nbsp;</label>&nbsp;            
            <s:submit name="muatnaik" value="Simpan" class="btn"/>
            <s:button name="close" value="Tutup" onclick="return rImage();" class="btn"/>
        </p>
    </fieldset>
</s:form>
