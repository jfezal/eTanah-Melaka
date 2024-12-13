<%-- 
    Document   : Jana_TajukKemasukan
    Created on : 13-Jan-2010, 11:51:08
    Author     : nordiyana
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.stripes.pengambilan.MaklumatPengambilanActionBean">
    <div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Tajuk Permohonan
        </legend>


        <p>
            <label for="Maksud_Pengambilan">&nbsp;</label>
           <s:textarea name="tajuk_pengambilan" rows="8" cols="50">  </s:textarea>
        </p>
        <p>
        <s:submit name="searchAllPermohonan" value="Jana Tajuk" class="btn"/>
        </p>
            <br/>
    </fieldset>

</div>

    </s:form>
