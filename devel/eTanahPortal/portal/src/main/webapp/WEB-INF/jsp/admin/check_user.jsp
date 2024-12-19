<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="${pageContext.request.contextPath}/js/util.js" type="text/javascript"></script>       
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/combodate.js"></script>
        <title>.: eTanah : Kemaskini Pengguna :.</title> 
        <script>
            $(document).ready(function () {

            });

                function check() {
                    var id = ("#userName").val();
                    
                    if(id === ''){
                        alert("Sila Masukkan ID Pengguna.");
                    }else{
                        
                    }
                }


        </script>

    </head>
    <body>

        <div class="row">
            <div class="col-md-12">
                <s:messages/>
                <s:errors/>
                <s:form beanclass="com.theta.portal.stripes.action.UserProfileActionBean" data-toggle="validator"> 
                    <s:useActionBean beanclass="com.theta.portal.stripes.util.ListUtil" var="list"/>
                    <s:hidden name="t" id="t" />
                    <div class="welcome-text-2">

                        <ul class="nav nav-tabs">
                            <li><a href="#infoPengguna">Maklumat Pengguna</a></li>
                        </ul>

                        <div class="tab-content">
                            <div id="infoPengguna" class="tab-pane fade in active">
                                <div class="panel panel-default">
                                    <!-- Toggle Heading -->
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse-1">
                                                <i class="fa fa-angle-up control-icon"></i>
                                                Carian Pengguna
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapse-1" class="panel-collapse collapse in">
                                        <div class="panel-body">
                                            <div class="form-horizontal">
                                                <div class="container">
                                                    <label class="col-md-2 control-label">ID Pengguna : </label>
                                                    <div class="col-sm-6 form-group">
                                                        <s:text name="userName" id="userName" class="form-control"/>                                                     
                                                    </div>
                                                </div>
                                                
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel-body">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <div style="text-align: right">
                                                <div class="col-sm-offset-2 col-sm-10">
                                                    <s:submit name="checkUser" value="Cari" class="btn-primary" tabindex="1" onclick="check();"/>&nbsp;                    
                                                    <a href="${pageContext.request.contextPath}/helpdesk/main" class="btn-primary">Keluar</a>
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


    </body>
</html>
