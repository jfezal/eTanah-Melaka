<%-- 
    Document   : viewDokDetails
    Created on : Jul 29, 2011, 3:03:18 PM
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
<s:form beanclass="etanah.view.uam.ViewDokDetailsBean" name ="viewDokDetails" id ="viewDokDetails">
    <s:messages/>
    <s:errors/>
        <div class="subtitle displaytag">

        <fieldset class="aras1">
            <legend>Butiran Data</legend>

            <p><label>ID Dokumen :</label>&nbsp;${actionBean.dok.idDokumen}</p>
            <p><label>Kod Dokumen :</label>&nbsp;${actionBean.dok.kodDokumen.kod}</p>
            <p><label>Tajuk :</label>&nbsp;${actionBean.dok.tajuk}</p>
            <p><label>No Rujukan :</label>&nbsp;${actionBean.dok.noRujukan}</p>                     
           <p><label>Tarikh/Masa Masuk :</label>&nbsp;<fmt:formatDate value="${actionBean.dok.infoAudit.tarikhMasuk}" pattern = "dd/MM/yyyy hh:mm:ss"/></p>                                 
          <p><label>Versi   :</label>&nbsp;${actionBean.dok.noVersi}</p>
          <div align ="center">
              <s:button name = "kembali" class="btn" value="Kembali" onclick="history.go(-2)"/>
          </div>
        </fieldset>
        </div>
    </s:form>
</div>
                     