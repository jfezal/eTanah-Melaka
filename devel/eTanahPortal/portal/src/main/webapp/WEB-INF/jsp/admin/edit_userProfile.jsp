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
            $(document).ready(function() {
//minimum 8 characters
                var bad = /(?=.{8,}).*/;
                //Alpha Numeric plus minimum 8
                var good = /^(?=\S*?[a-z])(?=\S*?[0-9])\S{8,}$/;
                //Must contain at least one upper case letter, one lower case letter and (one number OR one special char).
                var better = /^(?=\S*?[A-Z])(?=\S*?[a-z])((?=\S*?[0-9])|(?=\S*?[^\w\*]))\S{8,}$/;
                //Must contain at least one upper case letter, one lower case letter and (one number AND one special char).
                var best = /^(?=\S*?[A-Z])(?=\S*?[a-z])(?=\S*?[0-9])(?=\S*?[^\w\*])\S{8,}$/;
                $('#inputPassword').on('keyup', function() {
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


            });


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
                            <li><a href="#infoPengguna">Kemaskini Maklumat Pengguna</a></li>
                        </ul>

                        <div class="tab-content">
                            <div id="infoPengguna" class="tab-pane fade in active">
                                <div class="panel panel-default">
                                    <!-- Toggle Heading -->
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse-1">
                                                <i class="fa fa-angle-up control-icon"></i>
                                                Maklumat Peribadi
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapse-1" class="panel-collapse collapse in">
                                        <div class="panel-body">
                                            <div class="form-horizontal">
                                                <div class="container">
                                                    <label class="col-md-2 control-label">ID Pengguna : </label>
                                                    <div class="col-sm-6 form-group">
                                                        <s:text name="userTable.userName" class="form-control"/>&nbsp;&nbsp;
                                                        <a href="${pageContext.request.contextPath}/helpdesk/admin/userProfile?resetPassword&userName=${actionBean.userTable.userName}" class="btn-primary">Tukar Kata Laluan</a>

                                                    </div>
                                                </div>                                           
                                                <div class="container">
                                                    <label class="col-md-2 control-label">Nama : </label>
                                                    <div class="col-sm-6 form-group">
                                                        <s:text name="employee.employeeName" id="emel" class="form-control"/> 
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label">Emel : </label>
                                                    <div class="col-sm-6 form-group">
                                                        <s:text name="employee.email" id="emel" class="form-control"/> 
                                                    </div>
                                                </div>

                                                <div class="container">
                                                    <label class="col-md-2 control-label">Alamat :</label>
                                                    <div class="col-sm-6 form-group">
                                                        <s:text name="employee.employeeAddress" id="alamat1" class="form-control"/>                                                         
                                                    </div>
                                                </div>

                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput"><em>*</em> Jabatan :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:select name="departmentId" id="kodJabatan" style="width:300px" class="form-control" data-error="Sila Pilih Jabatan!" required="true">
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${list.department}" label="departmentName" value="departmentId"/>
                                                        </s:select>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>

                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput"><em>*</em> PTD :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:select name="ptdOfficeId" id="ptdOfis" style="width:300px" class="form-control" data-error="Sila Pilih PTD!" required="true">
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${list.ptdOffice}" label="ptdOfficeName" value="ptdOfficeId"/>
                                                        </s:select>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">Senarai Peranan Pengguna :</label>  
                                                    <div class="col-md-6 form-group">
                                                        <c:forEach items="${actionBean.ur}" varStatus="loop" var="item">                                                            
                                                            <span>
                                                                ${item.typeId.userType}&nbsp;&nbsp;                                                              
                                                                 <s:checkbox name="perananId" id="typeId_${item.typeId}" value="${item.typeId.typeId}"/> &nbsp;&nbsp;                                                  
                                                            </span>
                                                            <div class="help-block with-errors"></div>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput"><em>*</em> Peranan Pengguna :</label>  
                                                    <div class="col-md-6 form-group">
                                                        <c:forEach items="${actionBean.items}" varStatus="loop" var="item">                                                  
                                                            <s:checkbox name="peranan" id="cb_${item.typeId}" 
                                                                        value="${item.typeId}"/> &nbsp;&nbsp;    
                                                            <span>${item.userType}</span> 
                                                            <div class="help-block with-errors"></div>

                                                        </c:forEach>
                                                    </div>

                                                </div>

                                                <div class="container">
                                                    <label class="col-md-2 control-label"><em>*</em> Status Pengguna :</label>
                                                    <div class="col-sm-3 form-group">
                                                        <s:select name="employee.employeeActiveStatus" id="stat" style="width:300px" class="form-control" data-error="Sila Pilih Status!" required="true" >
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:option value="1">Aktif</s:option>
                                                            <s:option value="0">Tidak Aktif</s:option>
                                                        </s:select>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">No Telefon :</label>  
                                                    <div class="col-sm-3 form-group">
                                                        <s:text name="employee.employeePhoneNo" id="phone" class="form-control"/>
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
                                                    <s:submit name="editUserProfile" value="Simpan" class="btn-primary" tabindex="1" id="addNew"/>&nbsp;                    
                                                    <a href="${pageContext.request.contextPath}/helpdesk/admin/userProfile?edit" class="btn-primary">Keluar</a>
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
