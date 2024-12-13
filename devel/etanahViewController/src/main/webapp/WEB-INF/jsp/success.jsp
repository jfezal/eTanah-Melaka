<%-- 
    Document   : success
    Created on : Oct 15, 2009, 9:47:52 PM
    Author     : solahuddin
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib prefix="s"
           uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body>
        <s:form action="/successPage">
            Permohonan Berjaya Di Hantar<br><br><br>
            <s:submit name="goPage" value="Keluar"/>
        </s:form>
    </body>
</html>
