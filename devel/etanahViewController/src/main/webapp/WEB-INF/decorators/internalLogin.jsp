<%--
    Document   : internalLogin
    Created on : Oct 5, 2009, 2:23:23 PM
    Author     : abu.mansur
--%>

<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
           prefix="decorator"%>
<%@ page contentType="text/html" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c-rt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
    <head>
        <title>
            <decorator:title default="e-Tanah"/>
        </title>
        <style type="text/css" media="screen">
            /*<![CDATA[*/
            /* @import url("../styles/styles.css");
         /*]]>*/
        </style>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>

        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/jquerycornerv1.99.js"></script>

        <decorator:head/>

        <!--[if !IE 7]>
	<style type="text/css">
		#wrap {display:table;height:100%}
	</style>
<![endif]-->

    </head>
    <body>

        <c-rt:set var="now" value="<%=new java.util.Date()%>"/>
        <script type="text/javascript">window.status = "Loading: Document body...";</script>
        <div id="wrap">
            <div id="mainContainer">

                <div id="header">
                    <img src="${pageContext.request.contextPath}/pub/images/banner_etanah.jpg" width="1024" height="100">
                </div>
                <div id="topwelcomepanel" >
                </div>

                <decorator:body/>

                <%--<div class="clearfooter"></div>--%>
            </div>
        </div>

        <div id="footer">
            Version 1.0<br/>
            Hakcipta &copy; Sistem e-Tanah 2009<br/>
            Paparan terbaik resolusi 1024 x 768 ke atas dengan menggunakan IE
            7,Firefox 2 dan ke atas
        </div>

    </body>
</html>