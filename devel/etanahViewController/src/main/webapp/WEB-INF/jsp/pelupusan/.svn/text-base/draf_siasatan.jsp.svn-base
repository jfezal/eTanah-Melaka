<%-- 
    Document   : draf_siasatan
    Created on : May 17, 2010, 2:33:29 PM
    Author     : sitifariza.hanim
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>

<s:form beanclass="etanah.view.stripes.pelupusan.DrafSiasatanActionBean">

 <s:messages/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Laporan Siasatan Penangguhan</legend>
            <p>
                <label>Sebab Penangguhan :</label>
                <s:textarea name="DrafSiasatanActionBean.sebabPenangguhan" rows="5" cols="70"/>
            </p>
                        <p>
                <label>&nbsp;</label>
                <s:button name="simpanPenangguhan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
        </fieldset >
    </div>

</s:form>


