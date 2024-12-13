
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ page import="com.theta.portal.model.UserTable"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<html>
    <head>
        <title>eTanah - Portal eTanah Negeri Melaka</title>
        <link rel="icon" href="${pageContext.request.contextPath}/images/icon_logo.png">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>

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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/colors/light-red.css" title="light-red">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/colors/blue.css" title="blue">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/colors/black.css" title="black">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/colors/dark-blue.css" title="dark-blue">

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
        <script src="${pageContext.request.contextPath}/js/dataTables.bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/formatCurrency.js"></script>
        <script src="${pageContext.request.contextPath}/js/clear-text.js"></script>
        <script src="${pageContext.request.contextPath}/js/exporting.js"></script>
        <script src="${pageContext.request.contextPath}/js/highcharts.js"></script>

        <script type="text/javascript">
            $(function () {
                $("#clock").clock();
            });

 

            $(document).ready(function () {
                $("ul#nav li").attr("onclick", "return true");
                $('form').submit(function () {
                });

            });


        </script>
        <decorator:head /> 
    </head>
    <body>
        <img src="${pageContext.request.contextPath}/images/ajax-loader.gif" id="displayBox" style="display: none"/>
        <%            UserTable p = (UserTable) request.getSession().getAttribute("_KEY_USER");

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
             
            <div class="container">  
                <!-- Start Header Section -->
                <div class="header-section">
                    <c:set var="state" value="<%=c%>"/>
                    
                        <div class="hidden-xs">
                            <a href="#"><img src="${pageContext.request.contextPath}/images/banner/" class="img-responsive" alt=""></a>
                        </div>
                        <div class="hidden-sm hidden-md hidden-lg hidden-xl">
                            <a href="#"><img src="${pageContext.request.contextPath}/images/" class="img-responsive" alt=""></a>
                        </div>
                </div>
                <!-- End Navigation Section -->             

                <decorator:body />

                <div class="row">
                    <div class="col-md-12">
                        <div class="copyright-section">
                            <div class="row">
                                <div class="col-md-6 col-sm-6">
                                    <div class="copy">&copy;  </div>
                                </div>
                                <div class="col-md-6 col-sm-6">
                                    <div class="design">eTanah Portal</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                
            </div><!-- /.container -->
            <!-- End Copyright Section -->
            <a href="#" class="back-to-top"><i class="fa fa-angle-up"></i></a>
    </body>

</html>

