<%-- 
    Document   : auditDokumenDetails
    Created on : Jul 28, 2011, 4:21:38 PM
    Author     : Aziz
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<div id="all">
<s:form beanclass="etanah.view.uam.viewAuditDokDetails" name ="viewAuditDok" id ="viewAuditDokDetails">
    <s:messages/>
    <s:errors/>
        <div class="subtitle displaytag">

        <fieldset class="aras1">
            <legend>Butiran Data</legend>

            <p><label>ID Capaian :</label>&nbsp;${actionBean.capaiDok.idCapaian}</p>
            <p><label>ID Dokumen :</label>&nbsp;<a href="dok_Details?getDokumenDetails&idDok=${actionBean.capaiDok.dokumen.idDokumen}">${actionBean.capaiDok.dokumen.idDokumen}</a></p>
            <p><label>Pengguna :</label>&nbsp;<a href="pengguna_Details?viewPenggunaDetails&idPengguna=${actionBean.capaiDok.infoAudit.dimasukOleh.idPengguna}">${actionBean.capaiDok.infoAudit.dimasukOleh.idPengguna}</a></p>
            <p><label>Alasan :</label>&nbsp;${actionBean.capaiDok.alasan}</p>                     
           <p><label>Tarikh/Masa :</label>&nbsp;<fmt:formatDate value="${actionBean.capaiDok.infoAudit.tarikhMasuk}" pattern = "dd/MM/yyyy hh:mm:ss"/></p>                                 
          
          <div align ="center">
              <s:button name = "kembali" class="btn" value="Kembali" onclick="history.go(-2)"/>
          </div>
        </fieldset>
        </div>
    </s:form>
</div>
                     