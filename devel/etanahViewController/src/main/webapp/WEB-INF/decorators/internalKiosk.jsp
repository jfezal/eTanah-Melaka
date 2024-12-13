<%-- 
    Document   : internalKiosk
    Created on : Jul 10, 2015, 10:19:36 AM
    Author     : faidzal
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
        <style type="text/css" >
            body, html {

                width: 1024px;
                height: 768px;
            }
            p {
                font-size: 12px;
            }
            h1 {
                font-family: Arial;
                color: #333;
            }

            button {
                border: none;
                width: 150px;
                height: 40px;

                background: #00aeff;
                font-size: 20px;
            }

            button:active {
                background: black;
                color: white;
            }

            .delayed {
                transition: all 0.2s;
                transition-delay: 300ms;
            }

            .delayed:active {
                transition: none;
            }
.select-style {
  padding: 0;
  margin: 0;
  border: 1px solid #ccc;

  border-radius: 3px;
  overflow: hidden;
  background-color: #ccc;
  font-size: 12px;
}

input[type=text]:focus, textarea:focus, select:focus {
  box-shadow: 0 0 10px orangered;
  padding: 3px 0px 3px 3px;
  margin: 5px 1px 3px 0px;
  border: 1px solid orangered;
}

/*BUTTON*/
.btn {
    background: transparent url(../images/orangebutton2.gif) no-repeat left top;
    /*border: 1px solid #000;*/
    border:0px;
    font-weight: bold;
    height:28px;
    width:110px;
    font-size:11px;
}
.btn:hover {
    /* background:#FFC url(../images/orangehover.gif) repeat-x;
     background-position: 0 -50px;
     height:25px;*/
    background: transparent url(../images/orangebuttonhover.gif) no-repeat left top;
    border:0px;
    font-weight: bold;
    height:28px;
    width:110px;
    cursor:hand;
}

.select-style select {
  padding: 5px 8px;

  border: none;
  box-shadow: none;
  background-color: transparent;
  background-image: none;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  font-size: 12px;
}

Label {
    width: 17em;
    float: left;
    text-align: right;
    margin-right: 0.5em;
    display: block;
    /*color: #666666;*/
    color:whitesmoke;
    font-weight: normal;
    font-family:Arial;
    /*font-family: Geneva, Arial, Helvetica, sans-serif;*/
    font-size: 17px;
    margin-left: 100px;
}

.select-style select:focus {
  outline: none;
}
.myButton {
    background:url(../images/2-search-icon-red-glass.png) no-repeat;
    cursor:pointer;
    width: 200px;
    height: 100px;
    border: none;
}
        </style>
        <!--        <link type="text/css" rel="stylesheet"
                      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
                <link type="text/css" rel="stylesheet"
                      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>-->

        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/jquerycornerv1.99.js"></script>

    </head>
    <body style="overflow: hidden" background="../images/bg.png">
<br><br><br>
        <c-rt:set var="now" value="<%=new java.util.Date()%>"/>
        <script type="text/javascript">window.status = "Loading: Document body...";</script>
        <div id="wrap">
            <div id="mainContainer">

                <decorator:body/>

            </div>
        </div><br><br><br><br><br><br>

    </body>
</html>
