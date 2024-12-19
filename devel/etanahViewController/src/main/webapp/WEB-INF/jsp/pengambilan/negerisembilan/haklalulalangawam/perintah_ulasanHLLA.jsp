<%-- 
    Document   : perintah_ulasan2
    Created on : Oct 27, 2010, 2:16:38 PM
    Author     : massita
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html;charset=windows-1252" import="java.util.*" import="java.text.SimpleDateFormat"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>

<s:form beanclass="etanah.view.stripes.pengambilan.PerintahUlasanHLLAActionBean">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Perintah</legend>
            <p align="left"><label>ulasan :</label>
                <s:textarea name="text1" id="text1" rows="4" cols="40"></s:textarea>
            </p>

            <table align="center">
                <tr><td><font style="font-family: Arial; font-size: x-small; font-weight: bold;"><s:button name="" id="" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/></font></td></tr>
            </table>
        </fieldset>

    </div>
</s:form>
