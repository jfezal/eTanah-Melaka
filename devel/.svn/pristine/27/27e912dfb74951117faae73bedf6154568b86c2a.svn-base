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
   
     <!--<title>eTanah - Portal eTanah Negeri Melaka</title>
        <link rel="icon" href="${pageContext.request.contextPath}/images/icon_logo.png">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/> -->

<!-- CSS -->
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
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
<script src="${pageContext.request.contextPath}/js/lightbox.min.js.map"></script>
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
<!--<script src="${pageContext.request.contextPath}/js/exporting.js"></script>-->
<!--<script src="${pageContext.request.contextPath}/js/highcharts.js"></script>-->

<script type="text/javascript">


    $(function() {
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

    $(document).ready(function() {
        $("ul#nav li").attr("onclick", "return true");
        $('form').submit(function() {
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

<body background="${pageContext.request.contextPath}/images/GreyStripes.png">
    <div class="container" style="background-color:white">
	<!--<div class="page-header">-->
            <div class="row">
                <div class="col-sm-12 hidden-xs" style="padding:0px">
                    <img src="${pageContext.request.contextPath}/images/Header.jpg" width="100%">
                </div>
                <div class="col-xs-12 hidden-sm hidden-lg" style="padding:0px; background-color: brown; color: white">
                    <h1><center>Portal Awam eTanah</center></h1>
                </div>
            </div>
            <div class="row">
                     <nav class="navbar navbar-inverse">
                         <div class="container-fluid" >
                        <div class="navbar-header"></div>
                            <ul class="nav navbar-nav">
                                <li><a href="${pageContext.request.contextPath}/index"><center>Utama</center></a></li>
                                <li><a href="${pageContext.request.contextPath}/tentangEtanah"><center>Tentang Sistem e-Tanah</center></a></li>
                                <li><a href="${pageContext.request.contextPath}/soalanlazim"><center>Soalan Lazim</center></a></li>
                            </ul>
   		
                            <ul class="nav navbar-nav navbar-right">
                                <li><a href="${pageContext.request.contextPath}/helpdesk/main"><center><span class="glyphicon glyphicon-user"></span> HelpDesk</center></a></li>
                                <li><a href="https://ptg.melaka.gov.my/my"><center><span class="glyphicon glyphicon-log-in"></span> Portal PTG</center></a></li>
                            </ul>
                    </div>
                    </nav>
            </div>
	</div>
    <!--</div>-->
    <!--<div class="container">-->
<!--        <div class="row">
        <div class="container">
            <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header"></div>
   		<ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/index">Utama</a></li>
                    <li><a href="${pageContext.request.contextPath}/tentangEtanah">Tentang Sistem e-Tanah</a></li>
                    <li><a href="${pageContext.request.contextPath}/soalanlazim">Soalan Lazim</a></li>
                </ul>
   		
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="${pageContext.request.contextPath}/helpdesk/main"><span class="glyphicon glyphicon-user"></span> HelpDesk</a></li>
                    <li><a href="https://ptg.melaka.gov.my/1/index.php"><span class="glyphicon glyphicon-log-in"></span> Portal PTG</a></li>
   		</ul>
            </div>
	</nav>
        </div>
    </div>-->
    <!--</div>-->
    </div>
    <decorator:body />



</body>



