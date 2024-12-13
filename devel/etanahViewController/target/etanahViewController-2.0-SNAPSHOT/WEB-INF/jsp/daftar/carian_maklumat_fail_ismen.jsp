<%-- 
    Document   : carian_maklumat_fail_ismen
    Created on : 22 Oktober 2009, 11:52:51 AM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.stripes.common.MaklumatPermohonanActionBean">
<div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Fail</legend>
            <p>
                <label>No. Berdaftar Ismen :</label>
                <s:text name=""/>
            </p>

            <p>
                <label>&nbsp;</label>
                <s:submit name="cari" value="cari" class="btn"/>
            </p>

        </fieldset>
    </div>
</s:form>
