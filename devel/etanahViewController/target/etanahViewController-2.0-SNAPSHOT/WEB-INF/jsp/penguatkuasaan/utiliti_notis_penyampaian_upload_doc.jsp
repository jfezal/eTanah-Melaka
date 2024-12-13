<%--
    Document   : utiliti_notis_penyampaian_upload_doc
    Created on : Sept 8, 2011, 11:58:15 AM
    Author     : ctzainal
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript">
    function simpanDoc(){
        //alert("simpanDoc");
        var idPermohonan = $('#idPermohonan').val();
    <%--self.opener.refreshPageNotis1(idPermohonan);--%>
            //        self.opener.refreshImejDiv();
            self.opener.refreshPageNotis(idPermohonan);
        }
</script>

<s:form beanclass="etanah.view.penguatkuasaan.UtilitiNotisPenyampaianActionBean">
    <s:errors/>
    <s:messages/>
    <s:hidden name="idNotis"/>
    <s:text name="idPermohonan" id="idPermohonan"/>
    <fieldset class="aras1">
        <legend>Muat Naik</legend>
        <p>
            <label><%--idNotis : ${actionBean.idNotis}--%>&nbsp;</label>&nbsp;
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