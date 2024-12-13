<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.: Admin : Tukar Kata Laluan :.</title>    
        <script type="text/javascript">
            $(document).ready(function () {

                //minimum 8 characters
                var bad = /(?=.{8,}).*/;
                //Alpha Numeric plus minimum 8
                var good = /^(?=\S*?[a-z])(?=\S*?[0-9])\S{8,}$/;
                //Must contain at least one upper case letter, one lower case letter and (one number OR one special char).
                var better = /^(?=\S*?[A-Z])(?=\S*?[a-z])((?=\S*?[0-9])|(?=\S*?[^\w\*]))\S{8,}$/;
                //Must contain at least one upper case letter, one lower case letter and (one number AND one special char).
                var best = /^(?=\S*?[A-Z])(?=\S*?[a-z])(?=\S*?[0-9])(?=\S*?[^\w\*])\S{8,}$/;

                $('#inputPassword').on('keyup', function () {
                    var password = $(this);
                    var pass = password.val();
                    var passLabel = $('[for="password"]');
                    var stength = 'Lemah';
                    var pclass = 'danger';
                    if (best.test(pass) == true) {
                        stength = 'Sangat Bagus';
                        pclass = 'success';
                    } else if (better.test(pass) == true) {
                        stength = 'Bagus';
                        pclass = 'warning';
                    } else if (good.test(pass) == true) {
                        stength = 'Hampir Bagus';
                        pclass = 'warning';
                    } else if (bad.test(pass) == true) {
                        stength = 'Lemah';
                    } else {
                        stength = 'Sangat Lemah';
                    }

                    var popover = password.attr('data-content', stength).data('bs.popover');
                    popover.setContent();
                    popover.$tip.addClass(popover.options.placement).removeClass('danger success info warning primary').addClass(pclass);

                });

                $('input[data-toggle="popover"]').popover({
                    placement: 'top',
                    trigger: 'focus'
                });

            })

        </script>
        <style>
            .popover.primary {
                border-color:#337ab7;
            }
            .popover.primary>.arrow {
                border-top-color:#337ab7;
            }
            .popover.primary>.popover-title {
                color:#fff;
                background-color:#337ab7;
                border-color:#337ab7;
            }
            .popover.success {
                border-color:#d6e9c6;
            }
            .popover.success>.arrow {
                border-top-color:#d6e9c6;
            }
            .popover.success>.popover-title {
                color:#3c763d;
                background-color:#dff0d8;
                border-color:#d6e9c6;
            }
            .popover.info {
                border-color:#bce8f1;
            }
            .popover.info>.arrow {
                border-top-color:#bce8f1;
            }
            .popover.info>.popover-title {
                color:#31708f;
                background-color:#d9edf7;
                border-color:#bce8f1;
            }
            .popover.warning {
                border-color:#faebcc;
            }
            .popover.warning>.arrow {
                border-top-color:#faebcc;
            }
            .popover.warning>.popover-title {
                color:#8a6d3b;
                background-color:#fcf8e3;
                border-color:#faebcc;
            }
            .popover.danger {
                border-color:#ebccd1;
            }
            .popover.danger>.arrow {
                border-top-color:#ebccd1;
            }
            .popover.danger>.popover-title {
                color:#a94442;
                background-color:#f2dede;
                border-color:#ebccd1;
            }
        </style>
    </head>
    <body>

        <div class="row">
            <div class="col-md-12">
                <s:messages/>
                <s:errors/>
                <s:form beanclass="com.theta.portal.stripes.action.UserProfileActionBean" data-toggle="validator"> 
                    <div class="welcome-text-2">
                        <br>
                        <div class="panel-group" id="accordion">
                            <div class="panel panel-default">
                                <!-- Toggle Heading -->
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse-3">
                                            <i class="fa fa-angle-up control-icon"></i>
                                            Tukar Kata Laluan
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapse-3" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <div class="form-horizontal">
                                            <div class="form-horizontal">
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">Nama : </label>
                                                    <div class="col-sm-10">
                                                        <p class="form-control-static">${actionBean.userTable.userName}</label>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">Kata Laluan Lama :</label>
                                                    <div class="col-sm-6">
                                                        <s:password name="oldPassword" class="form-control" data-minlength="6" data-error="Sila Masukkan Kata Laluan Lama!" required="true"/> 
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">Kata Laluan :</label>
                                                    <!--div class="col-sm-6 input-group"><span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                                        <s:password name="password" data-minlength="6" class="form-control" id="inputPassword" placeholder="Kata Laluan" required="true"/>
                                                    </div>
                                                        <div class="help-block">Sekurang-kurangnya 6 aksara</div>
                                                    <label>Password</label-->
                                                    <div class="col-sm-6"> 
                                                        <s:password  class="form-control" name="password" data-minlength="6" id="inputPassword" placeholder="Kata Laluan" required="true" data-toggle="popover" title="Password Strength" data-content="Masukkan Kata Laluan..."/>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">Sah Kata Laluan</label>
                                                    <div class="col-sm-6">
                                                        <s:password name="sahkatalaluan" data-minlength="6" class="form-control" id="inputPasswordConfirm" data-match="#inputPassword" data-match-error="Kata Laluan tidak Sama" placeholder="Sah Kata Laluan" required="true"/>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-sm-offset-2 col-sm-10">
                                                        <s:submit name="updatePasswdProfile" value="Simpan" class="btn btn-primary" tabindex="1"/>&nbsp;  
                                                        <a href="${pageContext.request.contextPath}/helpdesk/admin/userProfile?reset" class="btn btn-primary">Isi Semula</a>&nbsp;
                                                        <s:button onclick="javascript:window.close();" name="tutup" value="Tutup"  class="btn btn-primary" />
                                                        
                                                    </div>
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
