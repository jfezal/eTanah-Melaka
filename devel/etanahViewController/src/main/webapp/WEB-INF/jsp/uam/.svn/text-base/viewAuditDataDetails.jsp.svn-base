<%-- 
    Document   : viewAuditDataDetails
    Created on : Jul 6, 2011, 1:25:04 AM
    Author     : zahidaziz
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<div id="all">
<s:form beanclass="etanah.view.uam.viewAuditDataDetailsBean" name ="viewAuditMedan" id ="viewAuditMedanDetails">
    <s:messages/>
    <s:errors/>
        <div class="subtitle displaytag">

        <fieldset class="aras1">
            <legend>Butiran Data</legend>

            <p><label>ID Audit :</label>&nbsp;${actionBean.ad.auditDataId.idAudit}</p>
            <p><label>Primary Key :</label>&nbsp;${actionBean.ad.primaryKey}</p>
            <p><label>Nama Jadual :</label>&nbsp;${actionBean.ad.namaTable}</p>
            <p><label>Nama Medan :</label>&nbsp;${actionBean.ad.auditDataId.namaMedan}</p>                     
           <p><label>Pengguna :</label>&nbsp; ${actionBean.auditUser.dataBaru}</p>                      
            <p><label>Nama Mesin :</label>&nbsp; ${actionBean.auditUser.machine}</p>                  
          <p><label>Data Lama :</label>&nbsp;${actionBean.ad.dataLama}</p>
          <p><label>Data Baru :</label>&nbsp;${actionBean.ad.dataBaru}</p>
          <p><label>Tarikh/Masa :</label>&nbsp;<fmt:formatDate value="${actionBean.ad.masa}" pattern = "dd/MM/yyyy hh:mm:ss"/></p>                   
          <p><label>Aktviti :</label> &nbsp;${actionBean.ad.aktiviti}</p>
          <div align ="center">
              <%--<s:button name = "kembali" class="btn" value="Kembali" onclick="history.go(-2)"/>
              <s:submit name="kembali" value="Kembali" class="btn"/>--%>
          </div>
        </fieldset>
        </div>
    </s:form>
</div>
                     