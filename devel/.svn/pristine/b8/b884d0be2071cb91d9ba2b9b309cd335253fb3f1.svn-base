<%-- 
    Document   : Penyerahan_dhke
    Created on : Oct 27, 2010, 2:14:57 PM
    Author     : Programmer
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<s:form beanclass="etanah.view.stripes.pengambilan.PenyerahanDHKEActionBean">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>penyerahan DHKE</legend>
            <p>
                <label>&nbsp;</label>
            <s:radio name="a" value="Ya"/>&nbsp;Ya &nbsp;
            <s:radio name="a" value="Tidak"/>&nbsp;Tidak&nbsp;
            </p>
            <br/>
            <p>
            <label>ulasan :</label>
             <s:textarea name="text1" id="text1" rows="4" cols="40"/>
            </p>
            <p align="right"><s:button name="" id="" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/></p>
        </fieldset>
    </div>
</s:form>
