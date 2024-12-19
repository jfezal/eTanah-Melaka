<%-- 
    Document   : logEMMKN
    Created on : Mar 3, 2011, 11:35:12 PM
    Author     : Izam
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>

<div id="all">
    <s:form beanclass="etanah.view.utility.LogEMMKNActionBean" name="logForm">
    <s:messages/>
    <s:errors/>
    <div class="subtitle displaytag">
        <fieldset class="aras1">
            <legend>Carian Id Permohonan</legend>
            <p>
                <font size="1" color="Red">
                    <strong>Sila masukkan Id Permohonan untuk membuat carian log e-MMKN.</strong>
                </font>
            </p>
            <p>
                <label><font color="red">*</font>Id Permohonan:</label>
                <s:text name="idMohon" />
                <s:submit name="searchLog" id="search" value="Cari" class="btn"/>
            </p>
        </fieldset>
        <fieldset class="aras1">
            <legend>Senarai Log e-MMKN</legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.imsList}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/util/log-emmkn">
                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                    <display:column property="permohonan.idPermohonan" title="ID Permohonan" style="vertical-align:baseline" />
                    <display:column property="statusEMMKN" title="Status EMMKN" style="vertical-align:baseline"/>
                    <display:column property="logEMMKN" title="Log ID EMMKN" style="vertical-align:baseline"/>
                    <display:column property="infoAudit.tarikhMasuk" title="Tarikh Hantar" format="{0,date,dd/MM/yyyy HH:mm}" style="vertical-align:baseline"/>
                    <display:column property="infoAudit.dimasukOleh.idPengguna" title="Dihantar Oleh" style="vertical-align:baseline"/>
                </display:table>
            </div>
        </fieldset>
    </div>
    </s:form>
</div>
