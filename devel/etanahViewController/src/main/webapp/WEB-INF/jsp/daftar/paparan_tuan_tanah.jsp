<%-- 
    Document   : paparan_tuan_tanah
    Created on : Apr 14, 2010, 9:57:10 AM
    Author     : khairil
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<div class="subtitle">
    <fieldset class="aras1">
        <legend>Senarai Tuan Tanah</legend>
        <div class="content" align="center">
            <display:table class="tablecloth" name="${actionBean.pihakKepentinganList}" cellpadding="0" cellspacing="0" id="line"
                           requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                <display:column property="pihak.nama" title="Nama" class="nama"/>
                <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                <display:column title="Bahagian yang dimiliki" >${line.syerPembilang}/${line.syerPenyebut}</display:column>
                <display:column title="Tarikh Pemilikan Tanah"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/></display:column>
            </display:table>
        </div>
    </fieldset>

</div>