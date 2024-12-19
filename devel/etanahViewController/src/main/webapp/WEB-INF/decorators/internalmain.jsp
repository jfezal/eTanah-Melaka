<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
           prefix="decorator"%>
<%@ page contentType="text/html" import="java.util.*"%>
<%@ page import="etanah.model.Pengguna" %>
<%@ page import="etanah.view.etanahActionBeanContext" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.TimeZone" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
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
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/mainstyle.css"/>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/eTanahSkin.css"/>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/menu.css"/>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/qt.css"/>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/sm.css"/>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/tablecloth.css"/>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui.theme.css"/>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/notification.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.tools.min.js?select=full&debug=true"></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquery.innerfade.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquery.li-scroller.1.0.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquerycornerv1.99.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/ui.core.js"></script>
        <%--<script src="${pageContext.request.contextPath}/pub/scripts/jquery.tooltip.js" type="text/javascript"></script>--%>
        <script src="${pageContext.request.contextPath}/pub/scripts/calendarHijrah.js" type="text/javascript"></script>
        <script language="JavaScript" src="${pageContext.request.contextPath}/pub/js/smartDate.js" type="text/javascript"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.7.3.custom.min.js"></script>
        <script src="${pageContext.request.contextPath}/pub/js/jquery.jclock-1.2.0.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/pub/js/ttw-notification-menu.min.js" type="text/javascript"></script>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>


        <script type="text/javascript">
            /*$(function(){
             $("ul#news").liScroll();
             });*/

            $(function () {

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


                $.ajax({
                    type: "GET",
                    url: '${pageContext.request.contextPath}/main?getTugasan',
                    success: function (data) {
                        $('#totaltask').show();
                        $('#size_tugasan').text(data);
                        $('#loading-img').hide();
                    }

                });
                $.ajax({
                    type: "GET",
                    url: '${pageContext.request.contextPath}/main?getTugasan2',
                    success: function (data) {
                        if (data == '0') {
                            $('#hideMenu').hide();
                        } else {
                            $('#hideMenu').show();
                        }

                        $('#size_tugasan2').text(data);
                        $('#loading-img2').hide();
                    }

                });

                $.ajax({
                    type: "GET",
                    url: '${pageContext.request.contextPath}/main?getTugasan3',
                    success: function (data) {
                        if (data == '0') {
                            $('#hideAduan').hide();
                        } else {
                            $('#hideAduan').show();
                        }
                        $('#size_tugasan3').text(data);
                        $('#loading-img3').hide();

                    }
                });

                var options = {
                    timeNotation: '12h'
                };

                $("a[title]").tooltip({
                    // tweak the position
                    offset: [10, 2],
                    // use the "slide" effect
                    effect: 'slide'

                            // add dynamic plugin with optional configuration for bottom edge
                }).dynamic({bottom: {direction: 'down', bounce: true}});

                $('.jclock').jclock(options);

                $('.fade').innerfade({
                    animationtype: 'fade',
                    speed: 'slow',
                    timeout: 3000,
                    type: 'sequence',
                    containerheight: '0.5em'

                });
            }
            );
            
             
            function cariKosong(){
             
             var cari2 = document.getElementById("cari2").value;
        
             if ( cari2 == "" ){ 
                 //alert("Sila masukkan maklumat carian");
                window.location.href = "${pageContext.request.contextPath}/senaraiTugasan";
               return false;
            
             } else {
              return true;
             }
             
            }
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



            var d = new Date();

            var weekday = new Array(7);
            weekday[0] = "Ahad";
            weekday[1] = "Isnin";
            weekday[2] = "Selasa";
            weekday[3] = "Rabu";
            weekday[4] = "Khamis";
            weekday[5] = "Jumaat";
            weekday[6] = "Sabtu";
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


    <c:set var="now" value="<%=new java.util.Date()%>"/>
    <% Pengguna p = (Pengguna) request.getSession().getAttribute(etanah.view.etanahActionBeanContext.KEY_USER);%>    
    <c:set var="lastlogin" value="<%=p.getTarikhAkhirLogin()%>"/>
    <%
        SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("aaa");
        SimpleDateFormat sdf2 = new SimpleDateFormat("H:mm");
        String timeformat = sdf1.format(p.getTarikhAkhirLogin());
        String date = sdf.format(p.getTarikhAkhirLogin());
        String time = sdf2.format(p.getTarikhAkhirLogin());
        String timeformat_ms = "";
        if (timeformat.equalsIgnoreCase("AM")) {
            timeformat_ms = "Pagi";
        } else {
            timeformat_ms = "Petang";
        }
        
        etanah.Configuration conf = new etanah.Configuration();
        String kodNegeri = conf.getProperty("kodNegeri");
        
    %>
    <script type="text/javascript">window.status = "Loading: Document body...";</script>
    <body>

        <div id="wrap">
            <div id="mainContainer">

                <div id="header">
                    <img src="${pageContext.request.contextPath}/pub/images/banner_etanah.jpg" width="1024" height="100">

                </div>
                <div id="topwelcomepanel" >
                </div>
                <div id="welcomepanel">
                    <c:set var="pencetak" value="<%=p.getNamaPencetak1()%>" />
                    <div align="left" style="margin-left:10px;float:left;margin-top:8px;">
                        <%= p.getNama()%> | <%= p.getJawatan()%> | <%=p.getKodCawangan().getName()%> <c:if test="${pencetak ne null}">| <a href="${pageContext.request.contextPath}/pencetak"> Nama Pencetak : <%= p.getNamaPencetak1()%></a>  </c:if>       </div>

                    <div align="right" style="margin-right:10px;float:right;margin-top:8px">
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
                            var clockTime = getClockTime();
                            document.write(clockTime);


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
                        <a href="<%= request.getContextPath()%>/logout" Title="Log Keluar" onclick="return confirm('Log Keluar?');"><img src="${pageContext.request.contextPath}/pub/images/icons/system-log-out.png"></a>
                    </div>
                </div>

                <div id="topMenu2">
                    <div class="panel2" style="margin-left:33%">
                        <div class="fade">
                            <p>Selamat Datang <%= p.getNama()%>  ke Sistem e-Tanah</p>
                            <p>Kunjungan terakhir anda adalah pada <b><%=date%>,&nbsp;<%=time%>&nbsp;<%=timeformat_ms%></b></p>
                            <p>Sila klik urusan yang ingin dipilih pada menu yang disediakan</p>

                        </div>


                    </div>
                </div>
                <!--            <div id="wrapper">-->

                <table width="100%">
                    <tr>
                        <td width="70%" align="right" valign="right"><div id="">

                                <right> <img src="pub/images/mainbg.png" width="580" align="top"/></right>

                            </div>
                        </td>
                        <td width="10%"></td>
                        <td width="20%" style="horizontal-align:right;vertical-align:top;background-image:url('${pageContext.request.contextPath}/pub/images/dbbg.gif');background-repeat:no-repeat;background-position:top left;" >

<!--                            <div style="width:160px;white-space:normal;text-align:left;" >
                                <h4>
                                    <img src="${pageContext.request.contextPath}/pub/images/Envelope_opens_2.gif" width="18" padding="0">&nbsp; <u><font color="red" style="white-space: nowrap;">Senarai Tugasan</font></u> 
                                </h4>
                                <br>
                                <div id="totaltask">
                                    <p>Anda mempunyai sebanyak <a href="<%=request.getContextPath()%>/senaraiTugasan" id="size_tugasan" class="busy">0</a>. Sila klik untuk melihat senarai tugasan. 
                                    <center><div id="loadingbar" ><img src="${pageContext.request.contextPath}/pub/images/loading_img.gif" id="loading-img"/></div>
                                    </center>
                                    </p>
                                </div>
                            </div>-->

                            <div id="hideMenu">
                                <div style="horizontal-align:right;vertical-align:top;background-image:url('${pageContext.request.contextPath}/pub/images/dbbg.gif');background-repeat:no-repeat;background-position:top left;width:170px;white-space:normal;text-align:left;" >
                                    <h4>
                                       <!-- <img src="${pageContext.request.contextPath}/pub/images/Envelope_opens_2.gif" width="18" padding="0">&nbsp; <u><font color="red" style="white-space: nowrap;">Senarai Tugasan</font></u> -->
                                    </h4>
                                    <br>
                                    <div id="totaltask2">
                                        <p>Anda mempunyai sebanyak <a href="<%=request.getContextPath()%>/uam/verify_user" id="size_tugasan2" class="busy">0</a> permohonan baru untuk disahkan. </p> </div>
                                    <center><div id="loadingbar2" ><img src="${pageContext.request.contextPath}/pub/images/loading_img.gif" id="loading-img2"/></div></center>
                                </div>
                            </div>

                            <div id="hideAduan">
                                <div style="horizontal-align:right;vertical-align:top;background-image:url('${pageContext.request.contextPath}/pub/images/dbbg.gif');background-repeat:no-repeat;background-position:top left;width:170px;white-space:normal;text-align:left;" >
                                    <h4>
                                       <!-- <img src="${pageContext.request.contextPath}/pub/images/Envelope_opens_2.gif" width="18" padding="0">&nbsp; <u><font color="red" style="white-space: nowrap;">Senarai Tugasan</font></u> -->
                                    </h4>
                                    <br>
                                    <div id="totaltask3"><p>Anda mempunyai sebanyak <a href="<%=request.getContextPath()%>/uam/get_new_aduan" id="size_tugasan3"  class="busy">0</a> aduan baru dari portal. </p> </div>
                                    <center><div id="loadingbar2" ><img src="${pageContext.request.contextPath}/pub/images/loading_img.gif" id="loading-img3"/></div></center>
                                </div>
                            </div>

                        </td>
                    </tr>
                </table>

                <s:form id="senarai_tugasan" beanclass="etanah.view.stripes.SenaraiTugasanAction" name="form1" onsubmit="return cariKosong();">
                    <div class="subtitle">
                        <fieldset class="aras1">
                            <div class="content" align="center">
                                <table>
                                    <tr>
                                        <td class="rowlabel1">ID Permohonan :</td>
                                        <td class="input1"><s:text name="idInsert" id="cari2" /> </td>
                                    </tr>             
                                    <tr>
                                        <td></td>
                                        <td>
                                    <s:submit name="searchAllPermohonan" value="Cari" class="btn"/>
                                    </td>
                                    </tr>
                                </table>
                            </div>
                        </fieldset>
                    </div>
                    <br>
                </s:form>

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
