<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ page contentType="text/html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<html>
    <head>
        <title><decorator:title default="e-Tanah" /></title>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/styles.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/eTanahSkin.css" />        
        <decorator:head />
    </head>
    <body>

        <div id="mainContainer">
            <div id="header"><object width="1024" height="100">
                    <param name="movie"
                           value="<%= request.getContextPath()%>/pub/images/banner.swf">
                    <embed src="<%= request.getContextPath()%>/pub/images/banner.swf"
                           width="1024" height="100">
                    </embed> </object></div>
            <div id="topwelcomepanel"></div>

            <div id="topMenu">
                <div id="leftTopPanel"></div>
                <div id="rightTopPanel"></div>
            </div>
            <div id="topMenu2"></div>
            <div class="contentWrap"><decorator:body /></div>
            <div class="clearfooter"></div>
        </div>

        <div id="footer">Version <%=request.getSession().getAttribute("version")%><br />
            Hakcipta &copy; Sistem e-Tanah 2009<br/>
            Paparan terbaik resolusi 1024 x 768 ke atas dengan menggunakan
            IE 7,Firefox 3 dan ke atas</div>
    </body>
</html>
