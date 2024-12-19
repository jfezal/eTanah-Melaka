<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
           prefix="decorator"%>
<%@ page contentType="text/html" import="java.util.*"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c-rt"%>
<%@ page import="etanah.model.Pengguna" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
    <head>
        <title>
            <decorator:title default="e-Tanah"/>
        </title>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/menu.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/qt.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/sm.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/notification.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.tools.min.js?select=full&debug=true"></script>        
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/jquerycornerv1.99.js"></script>
        <%--<script type="text/javascript"
        src="<%= request.getContextPath()%>/scripts/jquery.tooltip.js"></script>--%>        
        <%-- <script type="text/javascript"
         src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>--%>
        <%--<script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>--%>

        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
        <script src="<%= request.getContextPath()%>/pub/scripts/calendarHijrah.js" type="text/javascript"></script>
        <script language="JavaScript" src="<%= request.getContextPath()%>/pub/js/smartDate.js" type="text/javascript"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.7.3.custom.min.js"></script>
        <script src="${pageContext.request.contextPath}/pub/js/jquery.jclock-1.2.0.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/pub/js/ttw-notification-menu.min.js" type="text/javascript"></script>
        <%--<link href="${pageContext.request.contextPath}/pub/style/msg-alert.css" rel="stylesheet" type="text/css" />--%>
        <%--<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.4.2.min.js"></script>--%>
        <%--<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.7.2.custom.min.js"></script>--%>        
        <%--<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.timers-1.2.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/ckeditor/ckeditor.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/ckeditor/adapters/jquery.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/typeface-0.14.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/museo_300.typeface.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/toogleToogle.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/msgalert.jquery.js"></script>--%>


        <script type="text/javascript">           
            $(function() {
                var options = {
                    timeNotation: '12h'
                };
                $('.jclock').jclock(options);
                //$('a[title]').tooltip({offset: [10, 2],effect: 'slide'});
                $("a[title]").tooltip({

                    // tweak the position
                    offset: [10, 2],

                    // use the "slide" effect
                    effect: 'slide'

                    // add dynamic plugin with optional configuration for bottom edge
                }).dynamic({ bottom: { direction: 'down', bounce: true } });
               var notifications = new $.ttwNotificationMenu({
                    notificationList: {
                        anchor: 'item',
                        offset: '0 15'
                    }
                });

                notifications.initMenu({
                    messages: '#messages'
                });
                
                 var notifications2 = new $.ttwNotificationMenu({
                    notificationList: {
                        anchor: 'item',
                        offset: '0 15'
                    }
                });

                notifications2.initMenu({
                    messages: '#taskutil'
                });




                $('#size_tugasan').click(function () {
                    $('#loading-img').show();
                    $('#totaltask').hide();
                });

                $.ajax({
                    type: "GET",
                    url: '${pageContext.request.contextPath}/mesej?viewUnreadMessage',
                    success: function (data) {

                        for (var i = 0; i < data; ++i) {
                            var options = {
                                category: 'messages',
                                message: 'unread message'
                            };
                            notifications.createNotification(options);
                        }
                    }
                });
                
                $.ajax({
                    type: "GET",
                    url: '${pageContext.request.contextPath}/utiliti/tugasan?countTugasan',
                    success: function (data) {

                        for (var i = 0; i < data; ++i) {
                            var options = {
                                category: 'messages',
                                message: 'unread message'
                            };
                            notifications2.createNotification(options);
                        }
                    }
                });


        </script>

        <script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>

        <SCRIPT LANGUAGE = JAVASCRIPT>


            if (document.images)
            {
                img1 = new Image();
                img2 = new Image();
                img3 = new Image();
                img4 = new Image();
                img1.src = "../pub/images/headerbg.gif";
                img2.src = "../pub/images/hoverbg5.gif"
                img3.src = "../pub/images/topleft.gif";
                img4.src = "../pub/images/topright.gif"
            }
        </SCRIPT>
        <SCRIPT LANGUAGE = JAVASCRIPT>
            function getClockTime()
            {
                var now    = new Date();
                var hour   = now.getHours();
                var minute = now.getMinutes();
                var second = now.getSeconds();
                var ap = "Pagi";
                if (hour   > 11) { ap = "Petang";             }
                if (hour   > 12) { hour = hour - 12;      }
                if (hour   == 0) { hour = 12;             }
                if (hour   < 10) { hour   = "0" + hour;   }
                if (minute < 10) { minute = "0" + minute; }
                if (second < 10) { second = "0" + second; }
                var timeString = ap;
                return timeString;
            }
        </script>
        <script type="text/javascript">

            var d=new Date();

            var weekday=new Array(7);
            weekday[0]="Ahad";
            weekday[1]="Isnin";
            weekday[2]="Selasa";
            weekday[3]="Rabu";
            weekday[4]="Khamis";
            weekday[5]="Jumaat";
            weekday[6]="Sabtu";
        </script>


        <decorator:head/>
        <!--[if !IE 7]>
	<style type="text/css">
		#wrap {display:table;height:100%}
	</style>
<![endif]-->
        <style type="text/css">
        #ui-datepicker-div { display: none; } 
        </style>
    </head>
    <body>
        <% 
            Pengguna p = (Pengguna) request.getSession().getAttribute("_KEY_USER");
            etanah.Configuration conf = able.guice.AbleContextListener.newInstance(etanah.Configuration.class);
            String kodNegeri = conf.getProperty("kodNegeri");
        %>
        <c-rt:set var="now" value="<%=new java.util.Date()%>"/>
        <c-rt:set var="lastlogin" value=""/>
        <c:set var="pencetak" value="<%=p.getNamaPencetak1()%>" />
        <script type="text/javascript">window.status = "Loading: Document body...";</script>
        <div id="wrap">
            <div id="mainContainer">
                <div id="header">
<!--                    <object width="1024" height="100">
                        <param name="movie" value="<%= request.getContextPath()%>/pub/images/banner.swf">
                        <embed src="<%= request.getContextPath()%>/pub/images/banner.swf" width="1024" height="100">
                        </embed>
                    </object>-->
<img src="${pageContext.request.contextPath}/pub/images/banner_etanah.jpg" width="1024" height="100">
                </div>
                <div id="topwelcomepanel" >
                </div>
                <div id="welcomepanel">
                    <div align="left" style="margin-top:8px;margin-left:12px;float:left;">
                        <%= p.getNama()%> | <%= p.getJawatan()%> | <%=p.getKodCawangan().getName()%> <c:if test="${pencetak ne null}">| <a href="<%= request.getContextPath()%>/pencetak"> Nama Pencetak : <%= p.getNamaPencetak1()%></a>  </c:if>
                    </div>
                    <div align="right" style="margin-top:8px;margin-right:12px;float:right;">
                        <fmt:formatDate type="time"
                                        pattern="dd/MM/yyyy ,"
                                        value="${now}"/>

                        <script type="text/javascript">
                            document.write(" " + h.toString() + " H , ");
                            document.write(weekday[d.getDay()]);
                        </script>
                        <!--fmt:formatDate type="time"
                                        pattern=" | h:mm "
                                        value="${now}" timeZone="GMT+8"/-->
                        | <font class="jclock"></font>
                        <script type="text/javascript">
                            var clockTime = getClockTime();document.write(clockTime);


                            <%-- var islamicDate = today_date();--%>
                        </script>
                    </div>

                </div>
                <div id="topMenu">
                    <div id="leftTopPanel">

                        <jsp:include page="/WEB-INF/jsp/menu.jsp"/>

                    </div>
                    <div id="rightTopPanel">
                        <a href="${pageContext.request.contextPath}/utiliti/tugasan" class="notification-menu-item last-item" id="taskutil" Title="Tugasan"><img src="${pageContext.request.contextPath}/pub/images/task-list.png"></a>
                        <a href="${pageContext.request.contextPath}/mesej" class="notification-menu-item last-item" id="messages" Title="Mesej"><img src="${pageContext.request.contextPath}/pub/images/message.png"></a>
                         <%
                                if (kodNegeri.equals("05")) {
                            %>
                                <a href="http://10.66.128.20:7008/AssetManagement/login" Title="Bantuan"><img src="${pageContext.request.contextPath}/pub/images/icons/help-browser.png"></a>
                            <%} else {%>                                          
                                <a href="http://etml-aduan.melaka.gov.my/AssetManagement/login" Title="Bantuan"><img src="${pageContext.request.contextPath}/pub/images/icons/help-browser.png"></a>
                            <%}%>
                        <a href="<%= request.getContextPath()%>/logout" class="tooltips" Title="Log Keluar" onclick="return confirm('Log Keluar?')"><img src="<%= request.getContextPath()%>/pub/images/icons/system-log-out.png"></a>  <%--<span class="minfont"><a href="javascript:decreaseFontSize();">A</a></span>  - <span class="maxfont"><a href="javascript:increaseFontSize();">A</a></span>--%>
                    </div>
                </div>
                <div id="topMenu2">
                </div>
                <!-- <div id="topMenu3"></div> -->
                <div id="mainTab">
                    <c:if test="${actionBean.mainTab}">
                        <%@ include file="/WEB-INF/jsp/tab/main_tab_jquery.jsp"%>
                    </c:if>
                </div>

                <decorator:body/>




                <div align="right">
                    <c:if test="${actionBean.mainTab}">
                        <%@ include file="/WEB-INF/jsp/submitForm.jsp"%>
                    </c:if>
                </div>
            </div>
        </div>
        <div id="footer">
            Version 1.0<br/>
        </div>
    </body>
</html>
