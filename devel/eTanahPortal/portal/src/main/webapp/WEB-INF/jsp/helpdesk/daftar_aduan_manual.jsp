
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
          <script type="text/javascript">
          
            function lalala(val) {
//                alert(val);
                var url = "${pageContext.request.contextPath}/helpdesk/daftar_aduan_manual?view&employeeId=" + val;
                $.get(url,
                        function (data) {
                            console.log(data);
                            if (data)
                            {
//                                alert(data.notTel);
                                $("#jabatan").text(data.jabatan);
                                 $("#unit").text(data.unit);
                                 $("#notTel").val(data.notTel);
                                 $("#emel").val(data.emel);

//                                $('#pulangSemulaTeknikal').val(data.keteranganKontraktor);




                            } else
                            {

                            }
                        });
            }
//            
//            $(document).ready(function() {
////            alert('sss');
//                    $('.datePicker').datepicker({
//            format: "dd/mm/yyyy",
//                    changeMonth: true,
//                    changeYear: true,
//                    todayHighlight: true
//            }).on('changeDate', function(ev) {
//            $('.datePicker').datepicker('hide');
//            });
//            }
        </script>
        <title>.: eTanah : Daftar Aduan :.</title> 
    </head>
    <body>

        <div class="row">
            <div class="col-md-12">
                <s:messages/>
                <s:errors/>
                <s:form beanclass="com.theta.portal.stripes.helpdesk.DaftarAduanManualActionBean" data-toggle="validator"> 
                    <s:useActionBean beanclass="com.theta.portal.stripes.util.ListUtil" var="list"/>
                    <s:hidden name="idPengguna"/>
                    <div class="welcome-text-2">
                        <br>
                        <div class="panel panel-default">
                            <!-- Toggle Heading -->
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse-3">
                                        <i class="fa fa-angle-up control-icon"></i>
                                        Daftar Aduan
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
                                                    <label class="col-sm-2 control-label">Pelapor Aduan :</label>
                                                    <div class="col-sm-6 form-group">
                                                        <s:select name="pelaporId" style="width:300px" class="form-control" data-error="Sila Pilih Nama Pelapor" required="true" onchange="lalala(this.value);">
                                                            <s:option value="" >Sila Pilih</s:option>
                                                            <s:options-collection collection="${actionBean.listPelapor}" label="employeeName" value="employeeId"/>
                                                        </s:select>                                                <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">Jabatan :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <p id="jabatan">Tiada Maklumat</p>
                                                    </div>
                                                    <label class="col-md-2 control-label" for="textinput">Unit :</label>  
                                                    <div class="col-md-3 form-group">
<p id="unit">Tiada Maklumat</p>                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">No Telefon :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:text name="notTel" id="notTel" class="form-control"/>
                                                    </div>
                                                    <label class="col-md-2 control-label" for="textinput">Emel :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:text name="emel" id="emel" class="form-control"/>
                                                    </div>
                                                </div>

                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">Jenis Masalah :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:select name="helpdeskTypeId" style="width:300px" class="form-control" data-error="Sila Pilih Masalah!" required="true">
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${list.kodJenisMasalah}" label="helpdeskType" value="helpdeskTypeId"/>
                                                        </s:select>
                                                    </div>
                                                    <label class="col-md-2 control-label" for="textinput">Sub Kategori/ Perkakasan :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:select name="hardwareTypeId" style="width:300px" class="form-control" data-error="Sila Pilih Masalah!" required="true">
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${list.hardwareType}" label="hardwareTypeName" value="hardwareTypeId"/>
                                                        </s:select>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">Sub Kategori/ Modul :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:select name="submodulTypeId" style="width:300px" class="form-control" data-error="Sila Pilih Masalah!" required="true">
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${list.subModul}" label="submodulTypeName" value="submodulTypeId"/>
                                                        </s:select>
                                                    </div>
                                                    <label class="col-md-2 control-label" for="textinput">Item :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:select name="itemTypeId" style="width:300px" class="form-control" data-error="Sila Pilih Masalah!" required="true">
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${list.item}" label="itemTypeName" value="itemTypeId"/>
                                                        </s:select>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-sm-2 control-label">Perkara/Urusan :</label>
                                                    <div class="col-sm-6 form-group">
                                                        <s:text name="perkara" class="form-control" data-error="Sila Masukkan Perkara/Urusan" required="true"/>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-sm-2 control-label">Keterangan Masalah :</label>
                                                    <div class="col-sm-6 form-group">
                                                        <s:hidden name="reportId"/>
                                                        <s:textarea name="keterangan" class="form-control" data-error="Sila Masukkan Keterangan Masalah" required="true"/>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-sm-2 control-label">Tarikh Aduan :</label>
                                                    <div class="col-md-2 input-group date form-group datepicker">
                                                        <s:text name="tarikhAduan"  class="form-control datePicker" data-provide="datepicker" id="tarikhAduan" data-date-format="dd/mm/yyyy"
                                                                oninvalid="setCustomValidity('Sila Masukkan Tarikh Aduan')" onchange="setCustomValidity('')"/>
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                    </div>

                                                </div>
                                                <c:if test="${fn:length(actionBean.listOfDocument) <= 0}">
                                                    <div class="container">
                                                        <label class="col-sm-2 control-label">Fail :</label>
                                                        <div class="col-sm-6 form-group">
                                                            <s:file name="file"  id="document"  data-error="Sila Pilih Fail!" />
                                                            <div class="help-block with-errors"></div>
                                                        </div>
                                                    </div>
                                                    <div class="container">
                                                        <label class="col-sm-2 control-label"></label>
                                                        <div class="col-sm-6 form-group">
                                                            <span style="color: red">* Nota </span>: Muat naik lampiran bersaiz besar (melebihi 2MB) akan mengambil tempoh yang lama. Muat naik lampiran tidak melebihi 25MB.
                                                            Format fail menggunakan .pdf amat digalakan.
                                                            <div class="help-block with-errors"></div>
                                                        </div>

                                                    </div>
                                                </c:if>
                                            </div>
                                        </div>

                                    </div>

                                </div>
                            </div>


                            <c:if test="${fn:length(actionBean.listOfDocument) > 0}">
                                <div class="panel panel-default">
                                    <!-- Toggle Heading -->
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse-4">
                                                <i class="fa fa-angle-up control-icon"></i>
                                                Senarai Dokumen Sokongan
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapse-4" class="panel-collapse collapse in">
                                        <div class="panel-body">
                                            <div class="form-horizontal">
                                                <div class="container">
                                                    <label class="col-sm-2 control-label">Fail :</label>
                                                    <div class="col-sm-4 form-group">
                                                        <s:file name="file2" onchange="readURL(this);" id="document"  data-error="Sila Pilih Fail!" />
                                                        <div class="help-block with-errors">
                                                            <s:submit name="saveDocuments" value="Muat Naik" class="btn btn-primary formnovalidate" formnovalidate="false"/>
                                                        </div>
                                                    </div>


                                                </div>
                                                <div class="container">
                                                    <label class="col-sm-2 control-label"></label>
                                                    <div class="col-sm-6 form-group">
                                                        <span style="color: red">* Nota </span>: Muat naik lampiran bersaiz besar (melebihi 2MB) akan mengambil tempoh yang lama. Muat naik lampiran tidak melebihi 25MB.
                                                        Format fail menggunakan .pdf amat digalakan.
                                                        <div class="help-block with-errors"></div>
                                                    </div>

                                                </div>
                                                <display:table class="table table-hover table table-striped table-bordered" name="${actionBean.listOfDocument}" style="width:90;" requestURI="/saveDocuments" id="line">
                                                    <display:column title="No.">${line_rowNum}</display:column>
                                                    <display:column title="File Name">${line.fileName}</display:column>

                                                    <display:column title="Thumbnail">
                                                        <a href="${pageContext.request.contextPath}/${line.urlDownload}" rel="prettyPhoto[gallery1]" 
                                                           title="${line.fileName}">
                                                            <img src="${pageContext.request.contextPath}/${line.urlDownload}" width="30" height="30"/>
                                                        </a>
                                                    </display:column>

                                                    <display:column title="delete">
                                                        <a href="adminUser?deleteFile&id=${line.id}" onclick="return confirm('Adakah anda pasti untuk hapus?');">
                                                            <img src="${pageContext.request.contextPath}/images/icon/gnome_edit_delete.png" alt="delete" width="20" height="20"/>
                                                        </a>
                                                    </display:column>
                                                </display:table>  

                                            </div>
                                        </div>
                                    </div>
                                </div>  </c:if>

                            </div>
                            <div class="panel-body">
                                <div class="form-horizontal">
                                    <div class="form-group">
                                        <div class="col-sm-offset-4 col-sm-12">
                                        <!--<s:submit name="simpan" value="Simpan" class="btn btn-primary formnovalidate" formnovalidate="true"/>  &nbsp;&nbsp;&nbsp;-->
                                        <s:submit name="hantar" value="Hantar" class="btn btn-success formnovalidate" formnovalidate="true"/>  
                                        <img src="${pageContext.request.contextPath}/images/loading_img.gif" style="display:none" id="loading-img"/>
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
