<%-- 
    Document   : user
    Created on : Apr 28, 2013, 7:11:47 PM
    Author     : wan.fairul
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.: e : login :.</title>    
        <script type="text/javascript">

        </script>
        <style>           
            a:visited {
                color: black;
            }
        </style>
    </head>
    <body>


        
        <!-- Start About Us Section -->
   
            
<div class="row">
  <div class="col-md-12"> 
                <s:form beanclass="com.theta.portal.stripes.LoginActionBean" name="form1">
                    <div class="home-about-us">
                        <h3 class="section-title">Log Masuk</h3>
                        <div class="row">
                            <div class="col-md-4 col-md-offset-4">
                            <s:messages/>
                            <s:errors/>
                            <div class="col-md-11 col-sm-11" style="display: <c:if test='${!empty msg}'>none</c:if>">
                                <c:if test="${empty actionBean.pengguna.userName}">    
                                    <div class="input-group">
                                        <span class="input-group-addon" id="basic-addon1">ID Pengguna</span>
                                        <s:text name="userLogin" class="form-control" maxlength="20"  id="userLogin"/>
                                    </div>
                                </c:if>
                                <c:if test="${!empty actionBean.pengguna.userName}">
                                    <s:hidden name="pengguna.userName"/>
                                    <div class="input-group" style="padding-left: 70px">
                                       
                                    </div>
                                  <div class="input-group">
                                        <span class="input-group-addon" id="basic-addon1">ID Pengguna</span>
                                        <s:text name="pengguna.userName" class="form-control" id="userLogin" readonly="true"/>
                                        <s:hidden name="userLogin" value="pengguna.userName"/>
                                    </div>
                                    <br>
                                    <div class="input-group">
                                        <span class="input-group-addon" id="basic-addon1">Kata Laluan</span>
                                        <s:password name="userPassword" id="password"  class="form-control" />
                                    </div>
                                </c:if>
                                <br>
                                <p class="clearfix"> 
                                    <c:if test="${empty actionBean.pengguna.userName}">
                                        <s:submit name="prelogin" value="Seterusnya" class="btn btn-primary" tabindex="1"/> &nbsp;
                                    </c:if>

                                    <c:if test="${!empty actionBean.pengguna.userName}">
                                        <s:submit name="login" value="Login" class="btn btn-primary" tabindex="1"/>&nbsp;
                                        <a href="${pageContext.request.contextPath}/helpdesk/login"><input type="button" class="btn btn-primary"  value="Keluar"/></a>
                                        </c:if>   
                                        <c:if test="${front}">
                                        <!--s:submit name="register" value="Pendaftaran Baru" class="btn btn-primary" tabindex="3"/-->&nbsp;
                                    </p>
<!--                                    <p>
                                        <s:submit name="forgotPwd" value="Lupa Kata Laluan" class="btn btn-primary" tabindex="5"/>
                                    </p>-->
                                </c:if>
                            </div>
                        </div>
                    </div>
                    
                </s:form>
            </div></div>

            

        </div>
        <!-- End About Us Section -->     
        <!--<br>-->
        <!-- Start Copyright Section -->
        <div class="row">
            <!-- Start Footer Section -->

            <div class="col-md-12">
                <div class="footer-section">

                    <div class="row">

                        <div class="col-md-5 col-sm-7">
                            <div class="footer-address">
                                
                            </div>
                        </div>

                        <div class="col-md-3 col-sm-5">
                            <div class="footer-address">
                                
                            </div>
                        </div>
                        <div class="col-md-3 col-sm-5">
                            <div class="footer-address">
                                
                            </div>
                        </div>
                    </div>

                </div>
            </div>  
        </div>
    </body>
</html>
