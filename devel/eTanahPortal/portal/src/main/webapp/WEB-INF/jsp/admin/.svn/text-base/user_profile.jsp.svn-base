<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-select.min.css">
        <script src="${pageContext.request.contextPath}/js/util.js" type="text/javascript"></script> 
        <script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>
        <title>.: eTanah : Kemaskini Maklumat Pengguna :.</title> 
        <script>
            $(document).ready(function () {

          });

function doPopup() {
        var url = '${pageContext.request.contextPath}/helpdesk/admin/userProfile?reset';
        window.open(url, "eTanah Helpdesk","status=0,toolbar=0,location=0,menubar=0,width=890,height=600,scrollbars=yes");
        self.close();
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
                                                        <p class="form-control-static">${actionBean.userTable.userName}</p>                                                    
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label">Nama : </label>
                                                    <div class="col-sm-6 form-group">
                                                        <s:text name="employee.employeeName" id="name" class="form-control" value="${actionBean.employee.employeeName}"/> 
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label">Emel : </label>
                                                    <div class="col-sm-6 form-group">
                                                        <s:text name="employee.email" id="emel" class="form-control" value="${actionBean.employee.email}"/> 
                                                    </div>
                                                </div>
                                                                                                   
                                                <div class="container">
                                                    <label class="col-md-2 control-label">Alamat :</label>
                                                    <div class="col-sm-6 form-group">
                                                        <s:text name="employee.employeeAddress" id="alamat1" class="form-control" value="${actionBean.employee.employeeAddress}"/>                                                         
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
                                                    <label class="col-md-2 control-label" for="textinput"><em>*</em> PTD Ofis :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:select name="ptdOfficeId" id="ptdOfis" style="width:300px" class="form-control" data-error="Sila Pilih PTD Ofis!" required="true">
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${list.ptdOffice}" label="ptdOfficeName" value="ptdOfficeId"/>
                                                        </s:select>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>                                               
                                                <div class="container">
                                                <label class="col-md-2 control-label"><em>*</em> Status Pengguna :</label>
                                                    <div class="col-sm-3 form-group">
                                                        <s:select name="userTable.activeStatus" id="stat" style="width:300px" class="form-control" data-error="Sila Pilih Status!" required="true" >
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
                                                        <s:text name="employee.employeePhoneNo" id="phone" class="form-control" value="${actionBean.employee.employeePhoneNo}"/>
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
                                                    <s:submit name="updateUserProfile" value="Kemaskini" class="btn-primary" tabindex="1" id="addNew"/>&nbsp;
                                                    <%--<s:button onclick="doPopup()" name="clear" value="Tukar Kata Laluan"  class="btn btn-primary" />&nbsp;--%>                        
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
