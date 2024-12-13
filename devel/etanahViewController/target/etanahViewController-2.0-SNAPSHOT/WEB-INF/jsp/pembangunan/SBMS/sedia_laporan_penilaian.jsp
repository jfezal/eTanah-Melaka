<%-- 
    Document   : sedia_laporan_penilaian
    Created on : Jan 5, 2010, 2:24:44 PM
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
        <title>Penyediaan Laporan Penilaian</title>
    </head>
    <body>
        <s:form beanclass="etanah.view.stripes.pembangunan.SerahBalikBerimilikSemula">
            
           <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Laporan Penilaian
                    </legend>
                    <div class="content" align="center">
                        <s:textarea name="tajuk" rows="20" cols="87"/>
                    </div>
                </fieldset>
           </div>
                    <p align="center">
                        <s:button name="simpan" value="Simpan" class="btn"/>&nbsp;
                    </p>
        </s:form>
    </body>
</html>
