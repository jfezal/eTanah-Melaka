<%--
    Document   : login
    Created on : Oct 5, 2009, 2:28:39 PM
    Author     : abu.mansur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=windows-1252" language="java"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>
<html>
     <head>
          <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
          <title>.: e-Tanah :.</title>
          <link type="text/css" rel="stylesheet"
                href="<%= request.getContextPath()%>/styles/styles.css"/>
          <link type="text/css" rel="stylesheet"
                href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
          <script type="text/javascript"
          src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
          <script type="text/javascript"
          src="<%= request.getContextPath()%>/scripts/jquery-1.3.2.min.js"></script>
          <script type="text/javascript"
          src="<%= request.getContextPath()%>/scripts/jquerycornerv1.99.js"></script>
          <script type="text/javascript">
               $(function() {
                    $('#login').corner("20px");
                    $('#login2').corner("10px");
                    $('#IDPengguna').focus().select();
               });

          </script>
          <style type="text/css">
               input.error, textarea.error {
                    color: red;
                    background-color: yellow;
               }
          </style>

     </head>
     <body>
     <center>
          <br><br><br><br><br><br><br><br>
          <div class="subtitle" style="width:50%;">
               <stripes:errors/>
               <stripes:messages/>
               <br>
               <div id="login" style="background-image:url('<%= request.getContextPath()%>/pub/images/bgfront.gif');background-position: center">

                    <stripes:form beanclass="etanah.view.stripes.LoginActionBean" id="loginform">
                         <table border="0" align="center">

                              <%--<tr align="center">
                                  <td colspan="2"><p></p></td>
                              </tr>--%>
                              <tr align="center">
                                   <td colspan="2"><p>Selamat Datang ke Sistem e-Tanah.<br>Sila masukkan ID Pengguna dan Kata Laluan <br>di dalam ruangan yang disediakan dengan betul.</td>
                              </tr>
                              <tr>
                                   <td align="right">ID Pengguna :</td>
                                   <td class="input1">
                                        <stripes:text name="IDPengguna" size="30" id="IDPengguna"/>

                                   </td>
                              </tr>
                              <tr>
                                   <td align="right">Kata Laluan :</td>
                                   <td class="input1"><stripes:password size="30" name="kataLaluan" id="password"/></td>
                              </tr>
                              <c:if test="${actionBean.flag}">
                                   <tr>
                                        <td align="right">No Kad Pengenalan :</td>
                                        <td class="input1"><stripes:password size="30" name="kadPengenalan" id="kadPengenalan"/></td>
                                   </tr>
                              </c:if>
                              <tr align="center">
                                   <td colspan="2"><p><stripes:submit name="login" value="Log Masuk" class="btn"/>&nbsp;&nbsp;<stripes:button id="reset" value="Isi Semula" name="" class="btn" onclick="clearText('loginform');"/>&nbsp;&nbsp;</td>
                              </tr>
                              <%--<tr align="center">
                                  <td colspan="2"><stripes:button name="daftarPengguna" onclick="location.href='daftar_pengguna';" value="Pengguna Baru" class="btn"/>&nbsp;&nbsp;</td>
                              </tr>
                              <c:if test="${actionBean.flag}">
                                     <stripes:hidden name="IDPengguna" value="${actionBean.IDPengguna}"/>
                                  <tr><td colspan="2" align ="center"><stripes:submit name="logoutSession" value="Log Keluar" class="btn"/></td></tr>
                              </c:if>--%>
                              <tr><td>&nbsp;</td></tr>
                              <tr align="center">
                                   <td colspan="2">
                                        <p style="font-size: 11px">Daftar sebagai pengguna baru. Sila<a style="font-size: 11px" href="daftar_pengguna"> klik </a>disini</p>
                                   </td>
                              </tr>
                              <tr align="center">
                                   <td colspan="2">
                                        <p style="font-size: 11px">Log masuk untuk pertama kali. Sila<a style="font-size: 11px" href="first_login"> klik </a>disini</p>
                                   </td>
                              </tr>
                              <tr align="center">
                                   <td colspan="2">
                                        <p style="font-size: 11px">Semakan Permohonan Pengguna. Sila<a style="font-size: 11px" href="semakan_permohonan"> klik </a>disini</p>
                                   </td>
                              </tr>
                              <tr align="center">
                                   <td colspan="2">
                                        <p style="font-size: 11px">Terlupa Katalaluan. Sila<a style="font-size: 11px" href="katalaluan"> klik </a>disini</p>
                                   </td>
                              </tr>
                              <tr align="center">
                                   <td colspan="2">

                                        <c:choose>
                                             <c:when test="${kodNegeri eq '05'}">
                                                  <p style="font-size: 11px">Bantuan ? Klik <a href="http://10.66.128.20:7008/AssetManagement/login" Title="Bantuan"><img src="${pageContext.request.contextPath}/pub/images/icons/help-browser.png"></a></p>
                                             </c:when>
                                             <c:otherwise>
                                                  <p style="font-size: 11px">Bantuan ? Klik <a href="http://172.17.250.240:7019/portal/helpdesk/login" Title="Bantuan"><img src="${pageContext.request.contextPath}/pub/images/icons/help-browser.png"></a></p>
                                                  </c:otherwise>
                                             </c:choose>
                                   </td>
                              </tr>
                         </table>
                         <%--<table>
                             <tr>
                                 <td colspan="2">
                                     <p><a style="font-size: 11px" href="daftar_pengguna">Daftar pengguna</a></p>
                                 </td>
                                 <td colspan="2">
                                     <p><a style="font-size: 11px" href="first_login">Masuk pertama kali</a></p>
                                 </td>
                             </tr>
                         </table>--%>
                         <br>
                    </stripes:form>

               </div>
               <br>
               <div id="login2" class="aras1">
                    <font color="red">Perhatian :</font>
                    <h5>Penggunaan tanpa kebenaran sistem ini adalah dilarang dan boleh <br>mengakibatkan anda didakwa di atas pendakwaan awam dan jenayah.</h5>
               </div>


          </div>
     </center>              
     <applet code="etanah.dokumen.print.DocumentPrinter" 
             ARCHIVE="${pageContext.request.contextPath}/PrintApplet.jar,
             ${pageContext.request.contextPath}/commons-httpclient-2.0.2.jar,
             ${pageContext.request.contextPath}/commons-logging.jar,
             ${pageContext.request.contextPath}/swingx-1.6.jar,
             ${pageContext.request.contextPath}/log4j-1.2.12.jar,
             ${pageContext.request.contextPath}/jpedal_trial.jar,
             ${pageContext.request.contextPath}/PDFRenderer.jar"
             codebase = "${pageContext.request.contextPath}/"
             name     = "applet"
             id       = "applet"
             width    = "1"
             height   = "1"
             align    = "middle">        
     </applet>       
     
</body>
</html>