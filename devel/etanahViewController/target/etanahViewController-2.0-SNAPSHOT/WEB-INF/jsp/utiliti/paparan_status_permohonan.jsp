<%-- 
    Document   : paparan_status_permohonan
    Created on : Jul 10, 2015, 11:41:08 AM
    Author     : faidzal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.utility.PertanyaanStatusPermohonanActionBean" name="carianPelan" id="carianPelan">
    <s:messages/>
    <s:errors/>
    <div class ="subtitle">
        <br><br><br><br><br><br><br>
            <div align="center"><h1> SEMAKAN STATUS PERMOHONAN</h1></div>
        <div align='right'>
            <a href="${pageContext.request.contextPath}/kiosk/main" title="LAMAN UTAMA">
                <img  src="${pageContext.request.contextPath}/images/Home.png" style="height: 30px; width: 30px" border="0" title="LAMAN UTAMA"></a>
            <a href="${pageContext.request.contextPath}/kiosk/main" title="LAMAN UTAMA">
                <font face="arial">LAMAN UTAMA</font></a>
            &nbsp;&nbsp;&nbsp;
        </div>
        <fieldset class="aras1" style="border-color: white; position:relative ; width: 977px">
            <legend style="font-family:Arial; color: white; font-weight: bold;">STATUS PERMOHONAN</legend>
            <p>
                <label>ID Permohonan :</label>
                <font face="verdana" color="black" size='3px'><b>${actionBean.idPermohonan}</b></font>&nbsp;
            </p>
            <p>
                <label>Tarikh Permohonan :</label>
                <font face="verdana" color="black" size='3px'>
                    <b>
                        <fmt:formatDate type="date" pattern="dd/MM/yyyy HH:mm:ss a" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>&nbsp;  
                    </b>
                </font>&nbsp;
            </p>
            <p>
                <label>Urusan :</label>
                <font face="verdana" color="black" size='3px'><b>${actionBean.urusan}</b></font>&nbsp;
            </p>
            <p>
                <label><em style="color: red;">*</em>&nbsp;Status :</label>
                <font face="verdana" color="black" size='3px'><b>${actionBean.keterangan_ringkas}</b></font>&nbsp;
            </p>
            <p>
                <label>&nbsp;</label>
            <p><s:submit name = "kembali" class="btn" value ="Kembali" onclick="return validateField();"/></p>
            </p>
        </fieldset>
        <br><br><br><br>
    </div>
</s:form>
