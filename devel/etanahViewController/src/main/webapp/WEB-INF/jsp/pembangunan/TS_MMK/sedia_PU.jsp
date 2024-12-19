<%-- 
    Document   : sedia_PU
    Created on : Dec 30, 2009, 1:57:31 PM
    Author     : nursyazwani
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Penyediaan PU</title>
    </head>
    <body>
        <stripes:form beanclass="etanah.view.stripes.pembangunan.TukarSyaratMMKActionBean">
           <div class="subtitle">
               <fieldset class="aras1">
                   <legend>Penyediaan PU (Pemohonan Ukur)</legend>
                   <div class="content" align="center">
                       <c:if test="${edit}">
                           <table border="0" width="80%">
                               <tr>
                                   <td><p><label><font color="black">Penyediaan PU :</font></label>
                                    <s:text name="PU" size="30"/></p></td>
                               </tr>
                           </table>
                       </c:if>
                       <c:if test="${!edit}">
                           <table border="0" width="80%">
                               <tr>
                                   <td><p><label><font color="black">Penyediaan PU :</font></label></p></td>
                                   <td>&nbsp;</td>
                               </tr>
                           </table>
                       </c:if>
                   </div>
               </fieldset>
           </div>
           <p align="center">
                <s:button name="simpan" value="Simpan" class="btn"/>&nbsp;
           </p>
        </stripes:form>
    </body>
</html>
