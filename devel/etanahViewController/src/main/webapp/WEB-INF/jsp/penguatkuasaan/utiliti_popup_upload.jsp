<%-- 
    Document   : utiliti_popup_upload
    Created on : Nov 10, 2011, 10:12:50 AM
    Author     : latifah.iskak
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
        var idPermohonan = $('#idPermohonan').val();
        self.opener.refreshImejDiv(idPermohonan);
    });
   


    function simpan(){
        var idPermohonan = $('#idPermohonan').val();
        self.opener.refreshImejDiv(idPermohonan);
    }
        


</script>

<s:form beanclass="etanah.view.penguatkuasaan.UtilitiSenaraiDokumenActionBean">
    <s:errors/>
    <s:messages/>
    <s:hidden name="folderId"/>
    <s:hidden id="idPermohonan" name="idPermohonan"/>
    <s:hidden id="dokumenKod" name="dokumenKod"/>
    <s:hidden id="idDokumen" name="idDokumen"/>


    <fieldset class="aras1">
        <legend>Muat Naik</legend>
        <p>
            <label> Fail :</label>&nbsp;
            <s:file id="file" name="fileToBeUpload"/>

        </p>
        <p>
            <label>&nbsp;</label>&nbsp;
            <s:submit name="processUploadDoc" value="Simpan" class="btn" onclick="simpan();"/>
            <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
        </p>
    </fieldset>
</s:form>
