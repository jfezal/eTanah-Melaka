<%-- 
    Document   : mlk_buktipenyampaian
    Created on : May 19, 2010, 6:26:06 PM
    Author     : nurul.izza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<s:form beanclass="etanah.view.stripes.pelupusan.PermohonanLPSPTMlkActionBean">
    <p>
        <label>Tarikh Disampaikan :</label>
        <s:text name="tarikhSampai" size="40" id="datepicker" class="datepicker"/>
    </p>
</s:form>

