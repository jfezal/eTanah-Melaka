<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ page contentType="text/html" import="java.util.*"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c-rt"%>
<%@ page import="etanah.model.Pengguna"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<html>
    <head>
        <title><decorator:title default="e-Tanah" /></title>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/styles.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/eTanahSkin.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/menu.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/qt.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/sm.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/tablecloth.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/datepicker.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui.theme.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/notification.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquerycornerv1.99.js"></script>
        <%--<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery.tooltip.js"></script>--%>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/tablecloth.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/ui.datepicker.js"></script>
        <script src="${pageContext.request.contextPath}/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/ui.core.js"></script>
        <script src="${pageContext.request.contextPath}/pub/scripts/calendarHijrah.js" type="text/javascript"></script>
        <script language="JavaScript" src="${pageContext.request.contextPath}/pub/js/smartDate.js" type="text/javascript"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.7.3.custom.min.js"></script>
        <script src="${pageContext.request.contextPath}/pub/js/jquery.jclock-1.2.0.js" type="text/javascript"></script>      
        <script src="${pageContext.request.contextPath}/pub/js/ttw-notification-menu.min.js" type="text/javascript"></script>

        <%--<script src="<%= request.getContextPath()%>/scripts/jquery.metadata.js" type="text/javascript"></script>--%>
        <%--<script src="<%= request.getContextPath()%>/scripts/jquery.validation.js" type="text/javascript"></script>
        <script src="<%= request.getContextPath()%>/scripts/stripes.jquery.validation.js" type="text/javascript"></script>--%>

        <script type="text/javascript">
            $(function() {
                var options = {
                    timeNotation: '12h'
                };
                $('.jclock').jclock(options);
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $(".wideselect")
                        .mouseover(function() {
                    $(this).data("origWidth", $(this).css("width")).css("width", "auto");
                })
                        .mouseout(function() {
                    $(this).css("width", $(this).data("origWidth"));
                });                
                
                var notifications = new $.ttwNotificationMenu({
                    notificationList:{
                        anchor:'item',
                        offset:'0 15'
                    }
                });

                notifications.initMenu({
                    messages:'#messages'
                });
                
                $.ajax({
                    type: "GET",
                    url: '${pageContext.request.contextPath}/mesej?viewUnreadMessage',
                    success: function(data) {
                        
                        for (var i = 0; i< data ; ++i) {                           
                            var options = {
                                category:'messages',
                                message: 'unread message'
                            };
                            notifications.createNotification(options);
                        }
                    }
                });
                
            });
        </script>


        <SCRIPT LANGUAGE=JAVASCRIPT>


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
        <SCRIPT LANGUAGE=JAVASCRIPT>
            function getClockTime()
            {
                var now = new Date();
                var hour = now.getHours();
                var minute = now.getMinutes();
                var second = now.getSeconds();
                var ap = "Pagi";
                if (hour > 11) {
                    ap = "Petang";
                }
                if (hour > 12) {
                    hour = hour - 12;
                }
                if (hour == 0) {
                    hour = 12;
                }
                if (hour < 10) {
                    hour = "0" + hour;
                }
                if (minute < 10) {
                    minute = "0" + minute;
                }
                if (second < 10) {
                    second = "0" + second;
                }
                var timeString = ap;
                return timeString;
            }
        </SCRIPT>
        <script type="text/javascript">
            function onLoadPage() {
                var pguna = document.getElementById('role');
                var jam = new Date().getHours();
                if ((pguna.value == '2') || (pguna.value == '5')) {
                    $.get("${pageContext.request.contextPath}/hasil/mod_kutip?checkSession",
                            function(data) {
                                var ses = data.charAt(0);
                                var m = data.charAt(1);
                                if (ses == 'F') {
                                    if(jam>=14){
                                        $('#mod').val('Lewat');
                                        m = 'Lewat';
                                        $('#mod').attr("disabled", "true;");
                                    }else{
                                        alert('Sila pilih Mod Bayaran sama ada Mod Lewat atau Mod Biasa.');
                                        $('#mod').val('Biasa');
                                        m = 'Biasa';
                                    }
                                    $.get("${pageContext.request.contextPath}/hasil/mod_kutip?saveModToSession&mode=" + m,
                                            function(data) {
                                            });
                                }
                                if (ses == 'T') {
                                    $('#mod').attr("disabled", "true;");
                                    if (m == 'B') {
                                         if(jam>=14){
                                            $('#mod').val('Lewat');
                                            m = 'Lewat';
                                            $.get("${pageContext.request.contextPath}/hasil/mod_kutip?saveModToSession&mode=" + m,
                                                    function(data) {
                                                    });
                                         }else{
                                            $('#mod').val('Biasa');
                                        }
                                    }
                                    if (m == 'L') {
                                        $('#mod').val('Lewat');
                                    }
                                }
                            });
                }
            }

            function changeMode(x) {
                $('#mod').attr("disabled", "true;");
                if (x == 'Biasa') {
                    document.cookie = 'lewat';
                    $('#mod').val("Lewat");
                }
                if (x == 'Lewat') {
                    document.cookie = 'biasa';
                    $('#mod').val("Biasa");
                }
                var m = $('#mod').val();
                $.get("${pageContext.request.contextPath}/hasil/mod_kutip?saveModToSession&mode=" + m,
                        function(data) {
                        });
            }

            var d = new Date();

            var weekday = new Array(7);
            weekday[0] = "Ahad";
            weekday[1] = "Isnin";
            weekday[2] = "Selasa";
            weekday[3] = "Rabu";
            weekday[4] = "Khamis";
            weekday[5] = "Jumaat";
            weekday[6] = "Sabtu";

            function logout() {
                if (confirm('Log Keluar')) {
                    $.get("${pageContext.request.contextPath}/hasil/mod_kutip?saveModToSession1",
                            function(data) {
                                return true;
                            });
                } else
                    return false;
            }
        </script>
        <!--[if !IE 7]>
        <style type="text/css">
                #wrap {display:table;height:100%}
        </style>
<![endif]-->
        <style type="text/css">
            #ui-datepicker-div { display: none; } 
        </style>
        <decorator:head />
    </head>
    <body onload="onLoadPage();">

        <%
            Pengguna p = (Pengguna) request.getSession().getAttribute("_KEY_USER");

            etanah.service.session.SessionManager sm = etanah.service.session.SessionManager.getInstance();
            etanah.service.session.LoginSession ls = sm.getSession(session.getId());
            if (ls == null || (ls != null
                    && ls.getDirective() == etanah.service.session.LoginSession.LOGIN_DIRECTIVE.ADMIN_LOGOUT)) {
                session.invalidate();
                sm.removeSession(session.getId());
                request.setAttribute("mesej", "Pengguna telah dilog keluar");
                //throw new Exception("Pengguna is null");
                //out.println("Anda telah di log keluar. <a href=\"" + request.getContextPath() + "/login\">Klik di sini untuk log masuk semula.</a>");
                //response.sendRedirect(request.getContextPath() + "/error.jsp");
                //out.println("<script type=\"text/javascript\">document.location=\'" + request.getContextPath() + "/login\'</script>");
                //return;
            }
            
            etanah.Configuration conf = able.guice.AbleContextListener.newInstance(etanah.Configuration.class);
            String kodNegeri = conf.getProperty("kodNegeri");


            etanah.service.NotifikasiService ns = able.guice.AbleContextListener.newInstance(
                    etanah.service.NotifikasiService.class);
            // TODO: cache this
            //long msg = ns.findTotalNotifikasiForPengguna(p);
        %>

        <c-rt:set var="now" value="<%=new java.util.Date()%>" />
        <c-rt:set var="lastlogin" value="" />
        <c:set var="pencetak" value="<%=p.getNamaPencetak1()%>" />
        <c:set var="peranan" value="<%=p.getPerananUtama().getKod()%>" />
        <script type="text/javascript">window.status = "Loading: Document body...";</script>
        <div id="wrap">
            <div id="mainContainer">
                <div id="header">
                    <img src="${pageContext.request.contextPath}/pub/images/banner_etanah.jpg" width="1024" height="100">
                </div>
                <div id="topwelcomepanel"></div>
                <div id="welcomepanel">
                    <div align="left"
                         style="margin-top: 8px; margin-left: 12px; float: left;">
                        <%= p.getNama()%> | <%= p.getJawatan()%> | <%=p.getKodCawangan().getName()%> <c:if test="${pencetak ne null}">| <a href="<%= request.getContextPath()%>/pencetak"> Nama Pencetak : <%= p.getNamaPencetak1()%></a>  </c:if>
                        </div>
                        <div align="right"
                             style="margin-top: 8px; margin-right: 12px; float: right;"><fmt:formatDate
                            type="time" pattern="dd/MM/yyyy ," value="${now}" />
                        <script
                            type="text/javascript">
                                document.write(" " + h.toString() + " H , ");
                                document.write(weekday[d.getDay()]);
                        </script>                    
                        | <font class="jclock">
                            <fmt:formatDate type="time" pattern=" h:mm "
                                            value="${now}" timeZone="GMT+8" /></font>
                        <script type="text/javascript">
                            var clockTime = getClockTime();
                            document.write(clockTime);


                            <%-- var islamicDate = today_date();--%>
                        </script>                    
                    </div>


                </div>
                <div id="topMenu">
                    <div id="leftTopPanel"><jsp:include page="/WEB-INF/jsp/menu.jsp" />

                    </div><s:form beanclass="etanah.view.stripes.hasil.ModKutipService"><s:text name="" id="role" value="${peranan}" style="display:none;"/>
                        <div id="rightTopPanel">
                            <c:if test="${peranan eq '2' || peranan eq '5'}">
                                Mod Bayaran :
                                <s:button name="" id="mod" onclick="changeMode(this.value)" size="5" style="background:red;color:white;border:0 px;text-decoration:underline;font-weight:bold;font-size:50px"/>
                            </c:if>
                            <a href="${pageContext.request.contextPath}/mesej" class="notification-menu-item last-item" id="messages" Title="Mesej"><img src="${pageContext.request.contextPath}/pub/images/message.png"></a>
                            <%
                                if (kodNegeri.equals("05")) {
                            %>
                                <a href="http://10.66.128.20:7008/AssetManagement/login" Title="Bantuan"><img src="${pageContext.request.contextPath}/pub/images/icons/help-browser.png"></a>
                            <%} else {%>                                          
                                <a href="http://etml-aduan.melaka.gov.my/AssetManagement/login" Title="Bantuan"><img src="${pageContext.request.contextPath}/pub/images/icons/help-browser.png"></a>
                            <%}%>
                            <a href="<%= request.getContextPath()%>/logout" class="tooltips" Title="Log Keluar"
                               <%--onclick="return confirm('Log Keluar?')">--%>
                               onclick="return logout()">
                                <img src="<%= request.getContextPath()%>/pub/images/icons/system-log-out.png"></a>
                                <%--<span class="minfont"><a href="javascript:decreaseFontSize();">A</a></span>  - <span class="maxfont"><a href="javascript:increaseFontSize();">A</a></span>--%>
                        </div></s:form>
                    </div>
                    <div id="topMenu2"></div>

                    <div id="body">
                    <decorator:body />
                </div>




            </div>
        </div>
        <div id="footer">Version 1.0<br />
            <%--Hakcipta &copy; Sistem e-Tanah 2009<br/>
                        Paparan terbaik resolusi 1024 x 768 ke atas dengan menggunakan
                        IE 7,Firefox 2 dan ke atas--%></div>
        <script type="text/javascript">window.status = "Done";</script>
    </body>
</html>
