<%-- 
    Document   : drafWartaPenarikanBalik
    Created on : November 8, 2010, 9:59:27 AM
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

<s:form beanclass="etanah.view.stripes.pengambilan.drafWartaPenarikanBalikActionBean">
    <div class="subtitle">

        <div align="center">
        <table style="width:99.2%" bgcolor="purple">
            <tr><td width="100%" height="20" ><div style="background-color: purple;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;"><font color="white">PENGAMBILAN: WARTA PENARIKAN BALIK</font></font>
            </div></td></tr>
        </table>
        </div><br />

        <fieldset class="aras1">
            <legend></legend><br />
            <div class="content" align="center">
                <table border="0" width="80%" align="center">
                    <tr><td align="center" style=" padding: 1em 9em"><font color="black"><b>PEMBERITAHUAN PEMBATALAN PENGAMBILAN TANAH</b></font></td></tr>
                    <tr><td align="center" style=" padding: 1em 9em"><font color="black"><b>(warta pengisytiharan penarikan balik tanah)</b></font></td></tr>
                    <tr><td align="center"><font color="black">Siaran Warta Kerajaan Bil <b>${actionBean.noWartaTerdahulu}</b> yang telah disiarkan dalam Warta Kerajaan bertarikh <b><fmt:formatDate pattern="dd MMM yyyy" value="${actionBean.tarikhWartaTerdahulu}"/></b> adalah dengan ini dibatalkan</font><br><br></td></tr>
                </table>
            </div>
        </fieldset>
    </div>
</s:form>
