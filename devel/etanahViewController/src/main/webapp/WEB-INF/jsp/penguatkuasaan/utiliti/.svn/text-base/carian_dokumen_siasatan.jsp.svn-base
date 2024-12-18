<%-- 
    Document   : carian_dokumen_siasatan
    Created on : Mar 12, 2014, 10:00:51 AM
    Author     : MohammadHafifi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<s:messages />
<s:errors />
<s:form beanclass="etanah.view.penguatkuasaan.utiliti.PembetulanLaporanTanahActionBean" name="form1">
    <fieldset class="aras1">

        <legend>Carian Laporan Tanah</legend>
        <br>
        <p>
            <label for="idPermohonan">ID Permohonan/ID Perserahan :</label>
            <s:text name="idPermohonan" id="idPermohonan" onkeyup="this.value=this.value.toUpperCase();" />
        </p>
        <p>
            <label>&nbsp;</label>
            <s:submit name="carianLaporanTanah" value="Cari" class="btn" />
        </p>
        <br>
    </fieldset>
</s:form>

