<%-- 
    Document   : kemasukan_hakmilik_sebelum
    Created on : 03 November 2009, 11:08:02 AM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<s:form beanclass="etanah.view.stripes.KemasukanPerincianHakmilikActionBean">
<div class="subtitle">
    <fieldset class="aras1">
        <legend>Hakmilik Sebelum</legend>
        <p><label>ID Hakmilik Sebelum : </label>
        <s:text name="" />
        </p>
        <p><label>&nbsp;</label>
             <s:button name="popup" id="popup" value="Simpan" class="btn"/>
        </p>

    </fieldset>
</div>
</s:form>
