<%-- 
    Document   : maklumat_warta
    Created on : Jun 4, 2010, 5:23:59 PM
    Author     : sitifariza.hanim
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>

<s:form beanclass="etanah.view.stripes.pelupusan.DrafWartaTanahRizabActionBean">

 <s:messages/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Warta</legend>
            <p>
                <label>Tarikh :</label>
                <s:text name="tarikh"  class="datepicker" id="tarikh" size="20"/>
            </p>
            <p>
                <label>No.Warta :</label>
                <s:text name="noWarta" size="30"/>
            </p><br>
            <br>
            <p>
                <label>&nbsp;</label>
                <s:button name="simpanMaklumatWarta" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
        </fieldset >
    </div>

</s:form>




