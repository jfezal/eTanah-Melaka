<!-- 
    Document   : main_layout
    Created on : May 16, 2016, 10:32:09 AM
    Author     : fairul
    Modified by: haqqiem
--!>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ page import="com.theta.portal.model.UserTable"%>
<%@ page import="com.theta.portal.model.Employee"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE html>

<%

    boolean front = (request.getRequestURI().indexOf("/login.jsp") > 0); //Hack! main page!!            
    pageContext.setAttribute("front", front, pageContext.PAGE_SCOPE);

    boolean register = (request.getRequestURI().indexOf("/register.jsp") > 0); //Hack! main page!!            
    pageContext.setAttribute("register", register, pageContext.PAGE_SCOPE);

    boolean forgot = (request.getRequestURI().indexOf("/forgot_pwd.jsp") > 0); //Hack! main page!!            
    pageContext.setAttribute("forgot", forgot, pageContext.PAGE_SCOPE);

    boolean active = (request.getRequestURI().indexOf("/active_login.jsp") > 0); //Hack! main page!!            
    pageContext.setAttribute("active", active, pageContext.PAGE_SCOPE);

    boolean status_bayaran = (request.getRequestURI().indexOf("/carian/pertanyaan_status_bayaran.jsp") > 0); //Hack! main page!!            
    pageContext.setAttribute("status_bayaran", status_bayaran, pageContext.PAGE_SCOPE);

    boolean main_status_bayaran = (request.getRequestURI().indexOf("/carian/main_status_bayaran.jsp") > 0); //Hack! main page!!            
    pageContext.setAttribute("main_status_bayaran", main_status_bayaran, pageContext.PAGE_SCOPE);

%>

    
        <title>eTanah - Portal eTanah Negeri Melaka</title>
        

<!-- CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" media="screen" />
<!-- Bootstrap CSS  -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css" media="screen" />

<!-- Font Awesome CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" type="text/css">

<!-- Owl Carousel CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.carousel.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.theme.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.transitions.css" type="text/css">

<!-- Light Box CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/lightbox.css" type="text/css">

<!-- CSS Styles  -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">

<!-- Default Color -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/colors/dark-blue.css">

<!-- Colors CSS -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/colors/dark-blue.css" title="dark-blue">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/colors/light-red.css" title="light-red">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/colors/blue.css" title="blue">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/colors/black.css" title="black">

<!-- Responsive CSS Style -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/responsive.css">

<!-- Css3 Transitions Styles  -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animate.css">

<!-- JS File -->

<script src="${pageContext.request.contextPath}/js/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/modernizr.custom.js"></script>
<script src="${pageContext.request.contextPath}/js/owl.carousel.min.js"></script>
<script src="${pageContext.request.contextPath}/js/lightbox.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.appear.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.fitvids.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.nicescroll.min.js"></script>
<script src="${pageContext.request.contextPath}/js/superfish.min.js"></script>
<script src="${pageContext.request.contextPath}/js/supersubs.js"></script>
<script src="${pageContext.request.contextPath}/js/styleswitcher.js"></script>
<script src="${pageContext.request.contextPath}/js/script.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/datepicker.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/datepicker3.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/datetimepicker.css" />
<script src="${pageContext.request.contextPath}/js/bootstrap-datepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/js/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/clock.min.js"></script>
<script src="${pageContext.request.contextPath}/js/validate.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/dataTables.css" />
<!--<script src="${pageContext.request.contextPath}/js/dataTables.bootstrap.min.js"></script>-->
<script src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/js/formatCurrency.js"></script>
<script src="${pageContext.request.contextPath}/js/clear-text.js"></script>
<!--<script src="${pageContext.request.contextPath}/js/exporting.js"></script>-->
<!--<script src="${pageContext.request.contextPath}/js/highcharts.js"></script>-->

<script type="text/javascript">


    $(function () {
        $("#clock").clock();
    });
    function validateNumber(elmnt, content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function convert(val) {
        var amaun = CurrencyFormatted(val);
        amaun = CommaFormatted(amaun);
        return amaun;
    }

    function removeNonNumeric(strString)
    {
        var strValidCharacters = "1234567890+-";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for (intIndex = 0; intIndex < strString.length; intIndex++)
        {
            strBuffer = strString.substr(intIndex, 1);
            // Is this a number
            if (strValidCharacters.indexOf(strBuffer) > -1)
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }

    $(document).ready(function () {
        $("ul#nav li").attr("onclick", "return true");
        $('form').submit(function () {
        });
    });</script>
<style type="text/css">
    /* Some custom styles to beautify this example */
    .alert{
        margin-top: 20px;
    }
    .navbar{
        border-radius: 0;
    }
    .navbar.affix{
        top:  0px; /* Set the top position of pinned element */
        z-index: 2;
        width: 1140px;
    }

    .dropdown-submenu {
        position: relative;
    }

    .dropdown-submenu>.dropdown-menu {
        top: 0;
        left: 100%;
        margin-top: -6px;
        margin-left: -1px;
        -webkit-border-radius: 0 6px 6px 6px;
        -moz-border-radius: 0 6px 6px;
        border-radius: 0 6px 6px 6px;
    }

    .dropdown-submenu:hover>.dropdown-menu {
        display: block;
    }

    .dropdown-submenu>a:after {
        display: block;
        content: " ";
        float: right;
        width: 0;
        height: 0;
        border-color: transparent;
        border-style: solid;
        border-width: 5px 0 5px 5px;
        border-left-color: #ccc;
        margin-top: 5px;
        margin-right: -10px;
    }

    .dropdown-submenu:hover>a:after {
        border-left-color: #fff;
    }

    .dropdown-submenu.pull-left {
        float: none;
    }

    .dropdown-submenu.pull-left>.dropdown-menu {
        left: -100%;
        margin-left: 10px;
        -webkit-border-radius: 6px 0 6px 6px;
        -moz-border-radius: 6px 0 6px 6px;
        border-radius: 6px 0 6px 6px;
    }
</style>
<decorator:head /> 

<body>
    <img src="${pageContext.request.contextPath}/images/ajax-loader.gif" id="displayBox" style="display: none"/>
    <%            UserTable p = (UserTable) request.getSession().getAttribute("_KEY_USER");
    String access = (String) request.getSession().getAttribute("_ACCESS_LEVEL");
    Employee e = (Employee)request.getSession().getAttribute("_KEY_DETAIL");

        com.theta.portal.service.session.SessionManager sm = com.theta.portal.service.session.SessionManager.getInstance();
        com.theta.portal.service.session.LoginSession ls = sm.getSession(session.getId());
        if (ls == null || (ls != null
                && ls.getDirective() == com.theta.portal.service.session.LoginSession.LOGIN_DIRECTIVE.ADMIN_LOGOUT)) {
            session.invalidate();
            sm.removeSession(session.getId());
            request.setAttribute("mesej", "Session Timeout. Please Login.");
        }

        String c = com.theta.portal.service.session.SessionManager.getConfState();

    %>
    <div clas
         <div class="colors-switcher hidden-sm hidden-xs">
            <div class="hidden-sm hidden-xs">
                <a id="show-panel" class="hide-panel"><i class="fa fa-tint"></i></a>        
                <ul class="colors-list">
                    <li><a title="Dark Blue" class="dark-blue" onClick="setActiveStyleSheet('dark-blue'); return false;"></a></li>
                    <li><a title="Light Red" onClick="setActiveStyleSheet('light-red'); return false;" class="light-red"></a></li>
                    <li><a title="Blue" class="blue" onClick="setActiveStyleSheet('blue'); return false;"></a></li>
                    <li><a title="Black" class="black" onClick="setActiveStyleSheet('black'); return false;"></a></li>
                    <!--                        <li class="no-margin"><a title="Green" class="green" onClick="setActiveStyleSheet('green');
                                                    return false;"></a></li>
                                            <li class="no-margin"><a title="Yellow" class="yellow" onClick="setActiveStyleSheet('yellow');
                                                    return false;"></a></li>
                                            <li class="no-margin"><a title="Light Blue" class="light-blue" onClick="setActiveStyleSheet('light-blue');
                                                    return false;"></a></li>-->
                </ul>
            </div>
        </div>  
        <div id="loader">
            <div class="spinner">
                <div class="dot1"></div>
                <div class="dot2"></div>
            </div>
        </div>
        <div class="container">  
            <!-- Start Header Section -->
            <div class="header-section">
                <c:set var="state" value="<%=c%>"/>


                <div class="hidden-xs">
                    <a href="#"><img src="${pageContext.request.contextPath}/images/banner/header_melaka.jpg" class="img-responsive" alt=""></a>
                </div>
                <div class="hidden-sm hidden-md hidden-lg hidden-xl">
                    <a href="#"><img src="${pageContext.request.contextPath}/images/header_melaka.jpg" class="img-responsive" alt=""></a>
                </div>

            </div>

            <c:if test="${!front && !register && !forgot && !active && !status_bayaran && !main_status_bayaran}">                        
                <!-- Start Navigation Section -->
                <div class="navigation">
                    <div class="nav navbar affix" data-spy="affix" data-offset-top="120">

                        <div class="navbar navbar-default navbar-top">
                            <div class="navbar-header">
                                <!-- Stat Toggle Nav Link For Mobiles -->
                                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                                    <i class="fa fa-bars"></i>
                                </button>

                            </div>
                            <div class="navbar-collapse collapse">
                                <!-- Start Navigation List -->
                                <ul class="nav navbar-nav">
                                    <li><a href="${pageContext.request.contextPath}/helpdesk/main"><span class="glyphicon glyphicon-home">&nbsp;</span>Laman Utama</a></li>
                                    <!--<c:if test="${!empty actionBean.context.currentUser}">-->
                                    <c:set var="kod" value="<%=p.getIdType()%>"/>
                                    <c:set var="access" value="<%=access%>"/>
                                    <li> 
                                        <a href="#"><span class="fa fa-book">&nbsp;</span>Aduan</a>
                                        <ul class="dropdown">
                                            <li><a href="${pageContext.request.contextPath}/helpdesk/daftar_aduan"><span>Daftar Aduan</span></a></li>
                                            <c:if test="${access eq 'true'}">
                                                <li><a href="${pageContext.request.contextPath}/helpdesk/daftar_aduan_manual"><span>Daftar Aduan Manual</span></a></li>
                                            </c:if>
                                        </ul>
                                    </li>
                                    <li>
                                        <a href="#"><span class="fa fa-book">&nbsp;&nbsp;&nbsp;</span>Utiliti</a>
                                        <ul class="dropdown multi-level">
                                            <c:if test="${access eq 'true'}">
                                                <li><a href="${pageContext.request.contextPath}/helpdesk/statistik/report"><span class="glyphicon glyphicon-info-sign">&nbsp;</span>Laporan</a></li>
                                                <li>
                                                    <a href="${pageContext.request.contextPath}/helpdesk/admin/userProfile?addUser&add=true"><span class="glyphicon glyphicon-plus">&nbsp;</span>Tambah Maklumat </br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Pengguna</a>
                                                </li>
                                                <li>
                                                   <a href="${pageContext.request.contextPath}/helpdesk/admin/senarai_pengguna"><span class="glyphicon glyphicon-plus">&nbsp;</span>Senarai Pengguna </br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Pengguna</a>
                                              
                                                </li>
                                                <li>
                                                    <a href="${pageContext.request.contextPath}/helpdesk/admin/userProfile?edit"><span class="glyphicon glyphicon-lock">&nbsp;</span>Kemaskini Pengguna</a>
                                                </li>
                                                <li><a href="${pageContext.request.contextPath}/sessionManager"><span class="glyphicon glyphicon-list-alt">&nbsp;</span>Senarai Sesi</a></li>
                                                
                                            </c:if>
                                            <li><a href="${pageContext.request.contextPath}/helpdesk/utiliti/pengawasan_tugas"><span class="glyphicon glyphicon-eye-open">&nbsp;</span>Pengawasan Tugas</a></li>
                                        </ul>                                         </li>



                                    <!--                                    </c:if>-->
                                </ul>
                                <ul class="nav pull-right">
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                            <img src="${pageContext.request.contextPath}/images/profile.png" class="profile-image img-circle"> <b class="caret"></b></a>                                    
                                        <ul class="dropdown-menu nav pull-right" style="background-color: #0d4da1">
                                            <c:if test="${access eq 'true'}">
                                                <li>
                                                    <a href="${pageContext.request.contextPath}/helpdesk/admin/userProfile?addUser&add=true"><span class="glyphicon glyphicon-cog">&nbsp;</span>Tambah Maklumat </br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Pengguna</a>
                                                </li> </c:if>
                                            <li>
                                                <a href="${pageContext.request.contextPath}/helpdesk/admin/userProfile?add=false"><span class="glyphicon glyphicon-cog">&nbsp;</span>Kemaskini Maklumat </br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Pengguna</a>
                                            </li> 
                                            <li>
                                                <a href="${pageContext.request.contextPath}/helpdesk/admin/userProfile?reset"><span class="glyphicon glyphicon-lock">&nbsp;</span>Tukar Kata Laluan</a>
                                            </li>
                                            <c:if test="${access eq 'true'}">
                                                <li>
                                                    <a href="${pageContext.request.contextPath}/helpdesk/admin/userProfile?edit"><span class="glyphicon glyphicon-lock">&nbsp;</span>Kemaskini Pengguna</a>
                                                </li></c:if>
                                            <li>
                                                <a href="#" onclick="$('#logoutModal').modal('toggle');"><span class="glyphicon glyphicon-log-out">&nbsp;</span>Log Keluar</a>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                                <!-- End Navigation List -->

                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <!-- Toggle Heading -->
                    <div class="panel-heading">
                        <h4 class="panel-title" style="text-align: right">
                            <a data-toggle="collapse" data-parent="#accordion" >
                                <div class="row">

                                    <c:set var="jbtn" value="<%= p.getUserName()%>"/>
                                    <div class="col-md-12" style="text-align: left">
                                        <table border="0" width='100%'>
                                            <tr>
                                                <td><font size='4'><b><%= e.getEmployeeName()%> </b></font> <br/></td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                            <c:if test="${!empty jbtn}">
                                                <td><font size='2'><%= e.getEmployeeDepartmentId().getDepartmentName()%> | <%= e.getEmployeePtdOfficeId().getPtdOfficeName()%></font></td>
                                            </c:if>
                                            <c:if test="${empty jbtn}">
                                                <td><font size='2'></font></td>
                                            </c:if>
                                            <td style="text-align: right"><span id="clock" class="text-uppercase"></span>&nbsp;</td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>

                            </a>
                        </h4>
                    </div>
                </div>
            </c:if> 
            <!-- End Navigation Section -->             

            <decorator:body />

            <div class="row">
                <div class="col-md-12">
                    <div class="copyright-section">
                        <div class="row">
                            <div class="col-md-6 col-sm-6">
                                <div class="copy">&copy; Hak Cipta Terpelihara  | Pejabat Tanah dan Galian Negeri Melaka | e-Tanah | <br> Theta Technologies Sdn Bhd </div>
                            </div>
                            <div class="col-md-6 col-sm-6">
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal modal-cfrm " id="logoutModal">
                <form data-toggle="validator" role="form">
                    <div class="modal-dialog" style="border: 1px">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">
                                    <div>Pengesahan </div>
                                </h4>
                            </div>
                            <div class="modal-body">
                                Adakah anda pasti untuk Log Keluar?
                            </div>
                            <div class="modal-footer">
                                <a href="${pageContext.request.contextPath}/logout">
                                    <input type="button" name=" " class="btn btn-primary" value="Ya" id="yesBtn" style="width:100px;"/>
                                </a>
                                <input type="button" class="btn btn-warning" data-dismiss="modal" value="Tidak" id="cancelBtn" style="width:100px;"/>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </form>
            </div><!-- /.modal -->
        </div><!-- /.container -->
        <!-- End Copyright Section -->
        <a href="#" class=dri yg kau bagi main_layout tu
           aku ta"back-to-top"><i class="fa fa-angle-up"></i></a>
</body>



