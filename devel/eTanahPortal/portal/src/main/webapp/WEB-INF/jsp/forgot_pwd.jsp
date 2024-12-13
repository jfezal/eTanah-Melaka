

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://www.google.com/recaptcha/api.js"></script>
        <script type="text/javascript">
        </script>
    </head>

    <body>  
        <s:messages/>
        <s:errors/>
        <div class="row">
            <div class="col-md-12">
                <s:form beanclass="com.theta.portal.stripes.LoginActionBean" data-toggle="validator">
                    <div class="welcome-text-2">
                        <br>
                        <div class="panel-group" id="accordion">
                            <div class="panel panel-default">
                                <!-- Toggle Heading -->
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse-4">
                                            <i class="fa fa-angle-up control-icon"></i>
                                            Lupa Kata Laluan
                                        </a>
                                    </h4>
                                </div>

                                <!-- Toggle Content -->
                                <div id="collapse-4" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <div class="col-sm-7">
                                                    <div class="latest-news">
                                                        <h3 class="section-title">Makluman</h3>
                                                        <div class="latest-post">
                                                            <p>No Kad Pengenalan akan digunakan sebagai No Pengenalan.</p>
                                                            <p>Contoh : 810512067890</p>
                                                            <p>Kata Laluan baru akan dihantar kepada e-mel pengguna.</p>
                                                        </div>                                         
                                                    </div>
                                                </div>
                                            </div>
                                            <br>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">No Pengenalan</label>
                                                <div class="col-sm-6">
                                                    <input type="text" name="pengguna.idPengguna" class="form-control" id="idPengguna" data-error="Sila Masukkan No Pengenalan!" required>
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">&nbsp;</label>
                                                <div class="col-sm-6">
                                                    <div class="g-recaptcha" data-sitekey="6Lff1CoUAAAAAEvlyrGNQT-PSQbfE2u6GlubbLek" required></div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-offset-2 col-sm-10">
                                                    <s:submit name="sendForgotPwd" value="Hantar" class="btn btn-primary" tabindex="1"/>&nbsp;  
                                                    <a href="${pageContext.request.contextPath}/main?forgotPwd" class="btn btn-primary">Isi Semula</a>&nbsp;
                                                    <a href="${pageContext.request.contextPath}/welcome" class="btn btn-primary">Keluar</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </s:form>
            </div>
        </div>
    </body>
</html>
