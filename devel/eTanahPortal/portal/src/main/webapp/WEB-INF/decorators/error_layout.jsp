<%-- 
    Document   : internal_portal
    Created on : Jul 11, 2018, 4:40:30 PM
    Author     : user
--%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ page import="com.theta.portal.model.UserTable"%>
<%@ page import="com.theta.portal.model.Employee"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Portal eTanah Melaka</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.js"></script>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/
        ➥3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/
        ➥respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <style>
            .header{}

            .footer {
                left: 0;
                bottom: 0;
                width: auto;
                height: auto;
                background-color: #604B89;
                color: white;
                text-align: center;
            }
        </style>

    </head>
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
        
<!--        <div class="container-fluid">
                <div class="page-header">
                    <div class="row">
                        <div class="col-sm-12 col-xs-12">
                            <p align="center"><img src="${pageContext.request.contextPath}/images/HeaderPortal.png" class="img-rounded" width="100%"></p>
                        </div>
                    </div>
                </div>
            </div>
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
-->            <div class="container" style="background-color:white">
                <div class="row">
                <div class="col-sm-7 col-xs-12">
                    <decorator:body />
                </div>
<!--                <div class="col-xs-5">
                    <div class="well">
                        <h4>Pautan Pantas</h4>
                        <div class="list-group">
                            <a href="${pageContext.request.contextPath}/status_permohonan" class="list-group-item"><img src="${pageContext.request.contextPath}/images/statusPermohonan.png" width="10%"> Semakan Status Permohonan</a>
                            <a href="${pageContext.request.contextPath}/tukar_ganti" class="list-group-item"><img src="${pageContext.request.contextPath}/images/statusTukarGanti.png" width="10%"> Semakan Status Tukar Ganti</a>
                            <a href="${pageContext.request.contextPath}/semak_dokumen" class="list-group-item"><img src="${pageContext.request.contextPath}/images/semakanDokumen.png" width="10%"> Senarai Semak Dokumen</a>
                            <a href="http://ebayar.melaka.gov.my" target="_blank" class="list-group-item"><img src="${pageContext.request.contextPath}/images/eBayar.png" width="10%"> eBayar</a>
                            <a href="http://etanah.melaka.gov.my/agensi/home" target="_blank" class="list-group-item"><img src="${pageContext.request.contextPath}/images/agency.png" width="10%"> Kutipan Agensi</a>
                            <a href="http://etanah.melaka.gov.my/jtek/home" target="_blank" class="list-group-item"><img src="${pageContext.request.contextPath}/images/techdepart.png" width="10%"> Jabatan Teknikal</a>
                        </div>
                    </div>
                </div>-->

                <div class="col-sm-5 col-xs-12">
                    <div class="well">
                        <h4>Pautan Pantas</h4>
                        <div class="list-group">
                            <a href="${pageContext.request.contextPath}/status_permohonan" class="list-group-item"><img src="${pageContext.request.contextPath}/images/iconsPortal/status_Permohonan.png" width="10%"> Semakan Status Permohonan</a>
                            <a href="${pageContext.request.contextPath}/tukar_ganti" class="list-group-item"><img src="${pageContext.request.contextPath}/images/iconsPortal/tukar_Ganti.png" width="10%"> Semakan Status Tukar Ganti</a>
                            <a href="${pageContext.request.contextPath}/semak_dokumen" class="list-group-item"><img src="${pageContext.request.contextPath}/images/iconsPortal/semakan_Dokumen.png" width="10%"> Senarai Semak Dokumen</a>
                            <a href="https://melakapay.melaka.gov.my" target="_blank" class="list-group-item"><img src="${pageContext.request.contextPath}/images/iconsPortal/eBayar.png" width="10%"> MelakaPay</a>
                            <a href="http://etanah.melaka.gov.my/agensi/home" target="_blank" class="list-group-item"><img src="${pageContext.request.contextPath}/images/iconsPortal/agensi.png" width="10%"> Kutipan Agensi</a>
                            <a href="http://etanah.melaka.gov.my/jtek/home" target="_blank" class="list-group-item"><img src="${pageContext.request.contextPath}/images/iconsPortal/teknikal.png" width="10%"> Jabatan Teknikal</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="container" style="background-color: black; color: white">
            <!--<div class="footer">-->
                <div class="row">
                    <div class="col-sm-3 col-xs-12">
                        <br><span class="glyphicon glyphicon-home"></span> 
                        Pejabat Tanah & Galian Melaka, <br>Aras 1-2, Wisma Negeri, <br>Jalan Wisma Negeri, Hang Tuah Jaya, <br>75450 Ayer Keroh, Melaka Bandaraya Bersejarah </br>
                                        <br><span class="glyphicon glyphicon-phone-alt"></span> 
					Tel : 06-333 3333 ext 5048
                                        <br><span class="glyphicon glyphicon-phone-alt"></span> 
					Fax : 06-232 5802
                                        <br><span class="glyphicon glyphicon-envelope"></span> 
					Emel : ptg@melaka.gov.my
                                        <br>
                    </div>

                        <div class="col-sm-3 hidden-xs">
                                <br><span class="glyphicon glyphicon-home"></span> 
                                        Pejabat Daerah dan Tanah Jasin, Melaka <br>77000 Jasin, Melaka Bandaraya Bersejarah</br>
                                        <br><span class="glyphicon glyphicon-phone-alt"></span> 
					Tel : 06-333 3333
                                        <br><span class="glyphicon glyphicon-phone-alt"></span> 
					Fax : 06-529 3396
                                        <br><span class="glyphicon glyphicon-envelope"></span> 
					Emel : infopdtjasin@melaka.gov.my
                                        <br>	
			</div>
                    
                        <div class="col-sm-3 hidden-xs">
                                <br><span class="glyphicon glyphicon-home"></span> 
					Pejabat Daerah dan Tanah Alor Gajah,  <br>78000 Alor Gajah, Melaka Bandaraya Bersejarah</br>
                                        <br><span class="glyphicon glyphicon-phone-alt"></span> 
					Tel : 06-559 1203 ext 1204
                                        <br><span class="glyphicon glyphicon-phone-alt"></span> 
					Fax : 06-556 3488
                                        <br><span class="glyphicon glyphicon-envelope"></span> 
					Emel : infopdtag@melaka.gov.my
                                        <br>
                        </div>

                        <div class="col-sm-3 hidden-xs">
				<br><span class="glyphicon glyphicon-home"></span> 
                                        Pejabat Daerah dan Tanah Melaka Tengah <br>Aras 1, Bangunan Wisma Negeri, <br>Jln MITC-Ayer Keroh, Hang Tuah Jaya, <br>75450 Ayer Keroh, Melaka Bandaraya Bersejarah</br>
                                        <br><span class="glyphicon glyphicon-phone-alt"></span> 
					Tel : 06-333 3333 ext 5101
                                        <br><span class="glyphicon glyphicon-phone-alt"></span> 
					Fax :  06-232 6721 / 232 6582
                                        <br><span class="glyphicon glyphicon-envelope"></span> 
					Emel : pdtmt@melaka.gov.my
                                        <br>
			</div>
                </div>
            </div>
    </body>
</html>