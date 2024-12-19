<%-- 
    Document   : terima_keputusan_tangguh
    Created on : Jan 4, 2010, 6:20:20 PM
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
        <title>Keputusan Tangguh</title>
    </head>
    <body>
         <stripes:form beanclass="etanah.view.stripes.pembangunan.PecahSempadanActionBean">
           <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Keputusan Majlis Mesyuarat Negeri Sembilan
                    </legend>
                    <div class="content" align="center">
                        <table>
                             <tr>
                                <td></td>
                                <td>Tangguh &nbsp;</td>
                            </tr>
                            <tr>
                                <td>Keputusan MMK  :</td>
                                <td><s:textarea cols="87" rows="8" name="keputusan"/></td>
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
