<%-- 
    Document   : semak_PU
    Created on : Dec 30, 2009, 1:58:46 PM
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
        <title>JSP Page</title>
    </head>
    <body>
        <stripes:form beanclass="etanah.view.stripes.pembangunan.TukarSyaratMMKActionBean">
           
           <div class="subtitle">
               <fieldset class="aras1">
                   <legend>Resit Bayaran</legend>
                   <div class="content" align="center">
                       <br>
                       <p>Klik butang 'Resit Bayaran' untuk memaparkan resit bayaran</p>
                       <br>
                       <table>
                           <tr>
                               <td>Bayaran Upah Ukur :</td>
                               <td><s:text name="upahUkur" size="40"/></td>
                           </tr>
                           <tr>
                               <td>Bayaran Pelan :</td>
                               <td><s:text name="bayaranPelan" size="40"/></td>
                           </tr>
                           <tr>
                               <td>Bayaran :</td>
                               <td><s:text name="bayaran" size="40"/></td>
                           </tr>
                           <tr>
                               <td> &nbsp;</td>
                               <td><s:button name="resitByran" class="longbtn" value="Resit Bayaran"/></td>
                           </tr>
                       </table>
                   </div>
               </fieldset>
           </div>
           <div class="subtitle">
               <fieldset class="aras1">
                   <legend>Penyediaan PU (Pemohonan Ukur)</legend>
                   <div class="content" align="center">
                       <br>
                       <p>Klik butang 'Simpan' untuk menyimpan maklumat PU</p>
                       <br>
                       <table>
                           <tr>
                               <td>Penyediaan PU :</td>
                               <td><s:text name="PU" size="30"/></td>
                           </tr>
                           <tr>
                               <td>&nbsp;</td>
                               <td><s:button name="simpan" class="btn" value="Simpan"/></td>
                           </tr>
                       </table>
                   </div>
               </fieldset>
           </div>
                           <p align="center">
                               <s:button name="simpan" value="Simpan" class="btn"/>&nbsp;
                           </p>
        </stripes:form>
    </body>
</html>
