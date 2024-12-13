<%-- 
    Document   : minitDPP_upload
    Created on : September 22, 2012, 3:22:15 AM
    Author     : sitifariza.hanim
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        self.opener.refreshPage();
    });
    function simpanDoc(){
        self.opener.refreshPageMinit();
    }
</script>

<s:form beanclass="etanah.view.penguatkuasaan.MinitDPPActionBean">
    <s:errors/>
    <s:messages/>
    <s:hidden name="idDokumen"/>
    <s:hidden name="idPermohonan" value="${actionBean.idPermohonan}"/>
    <fieldset class="aras1">
        <legend>Muat Naik</legend>
        <p>
            <label> Fail :</label>&nbsp;
            <s:file name="fileToBeUploaded"/>
        </p>
        <br/>
        <p>
            <label>&nbsp;</label>&nbsp;
            <s:submit name="processUploadDoc" value="Simpan" class="btn" onclick="simpanDoc();"/>
            <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
        </p>
    </fieldset>
</s:form>