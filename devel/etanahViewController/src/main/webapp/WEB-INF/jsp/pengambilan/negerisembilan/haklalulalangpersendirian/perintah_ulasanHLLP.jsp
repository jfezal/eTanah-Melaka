<%-- 
    Document   : perintah_ulasan3
    Created on : Oct 27, 2010, 2:16:49 PM
    Author     : massita
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<s:form beanclass="etanah.view.stripes.pengambilan.PerintahUlasanHLLPActionBean">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Perintah</legend>
            <p align="left"><label>ulasan :</label>
                <s:textarea name="text1" id="text1" rows="4" cols="40"></s:textarea>
            </p><br />
        </fieldset><br />

         <table align="center">
            <tr>
            <td><font style="font-family: Arial; font-size: x-small; font-weight: bold;"><s:button name="" id="" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </font></td></tr>
         </table>
    </div>
</s:form>
