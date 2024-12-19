<%-- 
    Document   : perintah_tolak
    Created on : Oct 27, 2010, 2:15:40 PM
    Author     : massita
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

<s:form beanclass="etanah.view.stripes.pengambilan.PerintahtolakPHLLActionBean">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Endosan Pembatalan Hak Lalu Lalang</legend>
           <%-- &nbsp;<p align="center"><s:radio name="tolak" value="" /> tolak &nbsp;
            <s:radio name="batalHakLaluLalang" value="" />batal Hak Lalu Lalang</p>&nbsp;
            <p align="left"><label>ulasan</label>
                <s:textarea name="text1" id="text1" rows="4" cols="40"></s:textarea>
            </p>--%>
                                 <p>
                                    <label>&nbsp;</label>
                                <s:radio name="a" value="ya"/>&nbsp;ya &nbsp;
                                <s:radio name="a" value="tidak"/>&nbsp;batal hak lalau lalang&nbsp;
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
