<%-- 
    Document   : hakmilik_tetap
    Created on : Dec 21, 2009, 9:36:01 AM
    Author     : zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.pelupusan.PelupusanActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Hakmilik Tetap
            </legend>
            <p>
                <label>No. Fail :</label>
                <s:text  disabled="true" name=""/>
            </p>
            <p>
                <label>Negeri :</label>
                <s:text  disabled="true" name=""/>
            </p>
            <p>
                <label>Daerah :</label>
                <s:text  disabled="true" name=""/>
            </p>
            <p>
                <label>Tarikh Didaftarkan :</label>
                <s:text  class="datepicker" name="datepicker"/>
            </p>
        </fieldset>
    </div>
</s:form>
