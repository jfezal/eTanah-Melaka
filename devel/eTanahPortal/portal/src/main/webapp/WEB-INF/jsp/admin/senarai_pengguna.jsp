
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-timepicker.css" />
        <script src="${pageContext.request.contextPath}/js/bootstrap-timepicker.js"></script>
        <script type="text/javascript">

        </script>
        <title>.: eTanah : Statistik Aduan :.</title> 
        <script type="text/javascript">
            function getUnit(val, _Val) {
                var data = {};
                alert(val);
                var deptC = $('#deptC');
                if (deptC.length > 1)
                    deptC.empty();

                var url = "${pageContext.request.contextPath}//helpdesk/admin/senarai_pengguna?viewUnit";
                data['kod'] = val;


                getInfoWAjax(url, data).done(function (data) {
                    var list = "";
                    if (data.length > 1)
                        list = "<option value=''>Sila Pilih</option>";
                    $.each(data, function () {
                        var selected = "";
                        if (this.id == _Val)
                            selected = "selected";
                        list += "<option value ='" + this.departmentId + "' " + selected + ">"
                                + this.departmentName + "</option>";

                    });
                    console.log(list);
                    deptC.html(list);

                });
            }
            function papar(val) {
//                alert(val);
                var url = "${pageContext.request.contextPath}/helpdesk/admin/senarai_pengguna?view&userId=" + val;
                $.get(url,
                        function (data) {
                            console.log(data);
                            if (data)
                            {
//                                noAduan;

                                $("#idPengguna").text(data.idPengguna);
                                 $('#userIdPengguna').val(data.userId);
                                $('#namaPengguna').val(data.namaPengguna);
                                $("#noTel").val(data.noTel);
                                $("#emel").val(data.emel);
                                $("#alamat").val(data.alamat);

                                $("#userPTDOffice").val(data.userPTDOffice);
                                $("#departId").val(data.departId);
                                $("#status").val(data.status);
                                $("#tutupaduan").prop("disabled", data.userSemakButton);
                                $("#tolaktugasan").prop("disabled", data.tolakTugasanButton);

//                                $('#pulangSemulaTeknikal').val(data.keteranganKontraktor);




                            } else
                            {

                            }
                        });
            }
        </script>
    </head>
    <body>

        <div class="row">
            <div class="col-md-12">
                <s:messages/>
                <s:errors/>
                <s:form beanclass="com.theta.portal.stripes.action.SenaraiPenggunaActionBean" data-toggle="validator"> 
                    <s:useActionBean beanclass="com.theta.portal.stripes.util.ListUtil" var="list"/>

                    <div class="welcome-text-2">
                        <br>

                        <div class="panel panel-default">
                            <!-- Toggle Heading -->
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse-3">
                                        <i class="fa fa-angle-up control-icon"></i>
                                        Senarai Pengguna
                                    </a>
                                </h4>
                            </div>
                            <div id="collapse-3" class="panel-collapse collapse in">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="panel-body">
                                            <div class="form-horizontal">
                                                <br>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">Pejabat :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:select name="ptdOfficeId" style="width:300px" class="form-control" onchange="getUnit(this.value,'');" data-error="Sila Pilih Masalah!" >
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${list.ptdOffice}" label="ptdOfficeName" value="ptdOfficeId"/>
                                                        </s:select>
                                                    </div>
                                                </div>

                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">Unit :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:select name="deptC" id="deptC" style="width:300px" class="form-control" data-error="Sila Pilih Masalah!" >
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${list.department}" label="departmentName" value="departmentId"/>
                                                        </s:select>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">Peranan :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:select name="userType" id="userType"  class="form-control" data-error="Sila Pilih Status!"  >
                                                                    <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${list.userType}" label="userType" value="typeId"/>
                                                        
                                                                </s:select>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">Status :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:select name="statusC" id="statusC"  class="form-control" data-error="Sila Pilih Status!" >
                                                                    <s:option value="">Sila Pilih</s:option>
                                                                    <s:option value="1">Aktif</s:option>
                                                                    <s:option value="0">Tidak Aktif</s:option>
                                                                </s:select>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput"> &nbsp;</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:submit name="cari" value = "Cari" class="btn btn-primary formnovalidate"/>

                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="collapse-7" class="panel-collapse collapse in">
                                <div class="panel-body">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <div class="table-responsive" style="font-weight: normal">


                                                <display:table class="table table-hover table table-striped table-bordered" name="${actionBean.listSenaraiPengguna}" style="width:90;" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/helpdesk/admin/senarai_pengguna" id="line" sort="external" size="${actionBean.__pg_total_records}" partialList="true">
                                                    <display:column title="No."><a href="">${line_rowNum}</a></display:column>
                                                    <display:column title="Id Pengguna" property="idPengguna"/>
                                                    <display:column title="Maklumat Pengguna">
                                                        <div class="col-sm-12">
                                                            <div class="row">
                                                                ${line.nama} &nbsp;<b>|</b>&nbsp; ${line.jabatan} &nbsp;<b>|</b>&nbsp; ${line.unit}
                                                            </div>
                                                        </div>
                                                    </display:column>
                                                    <display:column title="Status">
                                                        ${line.status} &nbsp;<b>|</b>&nbsp; ${line.online} &nbsp;
                                                    </display:column>
                                                    <display:column title="Peranan" property="peranan"/>
                                                    <display:column title="Tindakan">
                                                        <div class="row">
                                                            <img src="${pageContext.request.contextPath}/images/semakanDokumen.png" data-target="#pulangSemulaTeknikalModal" onclick="papar(${line.userId});" data-toggle="modal" style="cursor: pointer;">
                                                        </div>
                                                    </display:column>
                                                </display:table>
                                            </div>
                                        </div>  
                                    </div>
                                </div>
                            </div>
                        </div>



                        <div class="modal bd-example-modal-lg" id="pulangSemulaTeknikalModal" role="dialog"><!-- /.modal Selepas Penjadualan -->
                            <!--<form role="form">-->
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title">
                                            <div>Maklumat Pengguna</div>
                                        </h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="container-fluid">
                                            <div class="row">
                                                <div class="col-sm-12">
                                                    Maklumat Pengguna
                                                    <div class="row">
                                                        <div class="col-sm-12">
                                                            <label class="col-md-3 control-label" for="textinput">ID Pengguna :</label>  
                                                            <div class="col-md-3 form-group">
                                                                <p id="idPengguna">Tiada Maklumat</p><s:hidden name="userIdPengguna" id="userIdPengguna" class="form-control"/>
                                                            </div>
                                                            <label class="col-md-3 control-label" for="textinput">Status :</label>  
                                                            <div class="col-md-3 form-group">
                                                                <s:select name="status" id="status"  class="form-control" data-error="Sila Pilih Status!" required="true" >
                                                                    <s:option value="">Sila Pilih</s:option>
                                                                    <s:option value="1">Aktif</s:option>
                                                                    <s:option value="0">Tidak Aktif</s:option>
                                                                </s:select>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-12">
                                                            <label class="col-md-3 control-label" for="textinput">Nama Pengguna :</label>  
                                                            <div class="col-md-9 form-group">
                                                                <s:text name="namaPengguna" id="namaPengguna" class="form-control"/>
                                                            </div>
                                                            
                                                        </div>
                                                            <div class="col-sm-12">
                                                            <label class="col-md-3 control-label" for="textinput">No Telefon :</label>  
                                                            <div class="col-md-3 form-group">
                                                                <s:text name="noTel" id="noTel" class="form-control"/>

                                                            </div>
                                                            </div>
                                                        <div class="col-sm-12">
                                                            <label class="col-md-3 control-label" for="textinput">Emel :</label>  
                                                            <div class="col-md-6 form-group">
                                                                <s:text name="emel" id="emel" class="form-control"/>
                                                            </div>
                                                            
                                                        </div>
                                                        <div class="col-sm-12">
                                                            <label class="col-sm-3 control-label">Alamat :</label>
                                                            <div class="col-sm-9 form-group">
                                                                <s:text name="alamat" id="alamat" class="form-control"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-12">
                                                            <label class="col-sm-3 control-label">Unit :</label>
                                                            <div class="col-sm-4 form-group">
                                                                <s:select name="departId" id="departId" style="width:300px" class="form-control" data-error="Sila Pilih Jabatan!" required="true">
                                                                    <s:option value="">Sila Pilih</s:option>
                                                                    <s:options-collection collection="${list.department}" label="departmentName" value="departmentId"/>
                                                                </s:select>
                                                            </div>

                                                        </div>
                                                        <div class="col-sm-12">
                                                            <label class="col-sm-3 control-label">Jabatan :</label>
                                                            <div class="col-sm-6 form-group">
                                                                <s:select name="userPTDOffice" id="userPTDOffice" style="width:300px" class="form-control" data-error="Sila Pilih PTD Ofis!" required="true">
                                                                    <s:option value="">Sila Pilih</s:option>
                                                                    <s:options-collection collection="${list.ptdOffice}" label="ptdOfficeName" value="ptdOfficeId"/>
                                                                </s:select>
                                                                <div class="help-block with-errors"></div>
                                                            </div>
                                                        </div>
                                                            <div class="col-sm-12">
                                                            <label class="col-sm-3 control-label">Katalaluan Baru :</label>
                                                            <div class="col-sm-3 form-group">
                                                                <s:text name="katalaluan" id="katalaluan" class="form-control"/>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                            <br>


                                            <br>
                                            <br>
                                        </div>


                                        <div class="modal-footer">

                                            <s:submit name="kemaskini" class="btn btn-primary"  value="Kemaskini" />
                                            <s:button name=" " class="btn btn-warning" data-dismiss="modal" value="Tutup" id="tutupDetails" style="width:100px;"/>
                                        </div>
                                    </div><!-- /.modal-content -->
                                </div><!-- /.modal-dialog -->
                                <!--</form>-->
                            </div>  </div>                             
                        </s:form>               
                </div>

            </div>
        </div>

    </body>
</html>
