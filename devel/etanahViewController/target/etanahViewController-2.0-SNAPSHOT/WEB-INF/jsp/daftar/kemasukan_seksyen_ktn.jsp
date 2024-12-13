<%-- 
    Document   : kemasukan_seksyen_ktn
    Created on : Apr 14, 2010, 1:40:26 PM
    Author     : fikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<s:form beanclass="etanah.view.daftar.MaklumatSeksyenActionBean">
    <s:errors/>
    <s:messages/>
<div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Seksyen</legend>
            <p>
                <label>Seksyen :</label>
                <s:text name="permohonan.rujukanUndang2"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:button name="save" value="Simpan" onclick="doSubmit(this.form, this.name,'page_div');" class="btn"/>
            </p>
        </fieldset>
</div>
</s:form>