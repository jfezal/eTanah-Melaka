<%-- 
    Document   : pertanyaan_status_bayaran
    Created on : Jun 6, 2016, 12:29:39 AM
    Author     : fairul
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.: Carian : Pertanyaan Status Bayaran :.</title>    
        <script type="text/javascript">
            $(document).ready(function () {
                $('#line').DataTable();

                $('#tarikhMula')
                        .datepicker({
                            format: "dd/mm/yyyy",
                            changeMonth: true,
                            controlType: 'select',
                            changeYear: true,
                            maxDateTime: function (date) {
                                var selectedDate = $(this).datepicker('getDate');
                                var msecsInADay = 86400000;
                                var endDate = new Date(selectedDate.getTime() + msecsInADay);
                                $("#tarikhAkhir").datepicker("option", "minDate", endDate);
                            }
                        }).on('changeDate', function (ev) {
                    $('#tarikhMula').datepicker('hide');
                });


                $('#tarikhAkhir')
                        .datepicker({
                            format: "dd/mm/yyyy",
                            controlType: 'select',
                            changeMonth: true,
                            changeYear: true
                        }).on('changeDate', function (ev) {
                    $('#tarikhAkhir').datepicker('hide');
                });

                $('#nama').keyup(function () {
                    this.value = this.value.toUpperCase();
                });
                
                 $('#noPengenalan').keyup(function () {
                    this.value = this.value.toUpperCase();
                });



            });

            function view(val) {
                $('#id').val(val);
                $('#view').click();

            }




        </script>
        <style>
            .dv-table td{
                border:0;
            }
            .dv-table input{
                border:1px solid #ccc;
            }
        </style>

    </head>
    <body>
        <div class="row">
            <div class="col-md-12">
                <s:messages/>
                <s:errors/>
                <s:form beanclass="com.theta.portal.stripes.LoginActionBean" name="form1">
                    <s:text name="cmBayaranPenerima.pkid" id="id" value="${actionBean.cmBayaranPenerima.pkid}" style="display:none;"/> 
                    <div class="welcome-text-2">
                        <div class="panel-group" id="accordion">
                            <div class="panel panel-default">
                                <!-- Toggle Heading -->
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse-4">
                                            <i class="fa fa-angle-up control-icon"></i>
                                            Pertanyaan Status Bayaran
                                        </a>
                                    </h4>
                                </div>

                                <!-- Toggle Content -->
                                <div id="collapse-4" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <br>
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <label class="col-sm-4 control-label" >No. Kad Pengenalan / No.Daftar Syarikat</label>
                                                <div class="col-sm-6">
                                                    <s:text name="noPengenalan" class="form-control" id="noPengenalan"/> 
                                                </div>
                                                <div class="help-block with-errors"></div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-offset-2 col-sm-10 control-label">
                                                    <label class="col-sm-4 control-label" >atau</label>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-4 control-label" >Nama Syarikat / Organisasi / Individu</label>
                                                <div class="col-sm-6">
                                                    <s:text name="nama" class="form-control" id="nama"/> 
                                                </div>
                                                <div class="help-block with-errors"></div>
                                            </div>
                                            <br>
                                            <br>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">Tarikh Mula &nbsp;&nbsp;&nbsp;&nbsp;</label>
                                                <div class="col-sm-3 input-group input-append date form-group" id="datepicker">
                                                    <s:text name="tarikhMula" class="form-control" id="tarikhMula" formatPattern="dd/MM/yyyy" /> 
                                                    <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">Tarikh Akhir &nbsp;&nbsp;&nbsp;&nbsp;</label>
                                                <div class="col-sm-3 input-group input-append date form-group" id="datepicker2">
                                                    <s:text name="tarikhAkhir" class="form-control" id="tarikhAkhir" formatPattern="dd/MM/yyyy"/> 
                                                    <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-offset-2 col-sm-10">
                                                    <s:submit name="carian" value="Semak" class="btn btn-primary" tabindex="1"/>&nbsp;  
                                                    <a href="${pageContext.request.contextPath}/main/status_bayaran" class="btn btn-primary">Isi Semula</a>&nbsp;
                                                    <a href="${pageContext.request.contextPath}/main" class="btn btn-primary">Keluar</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="panel panel-default">
                            <!-- Toggle Heading -->
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse-1">
                                        <i class="fa fa-angle-up control-icon"></i>
                                        Senarai Pertanyaan Status Bayaran
                                    </a>
                                </h4>
                            </div>
                            <div id="collapse-1" class="panel-collapse collapse in">
                                <br/>
                                <div class="panel-body">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <div class="table-responsive">
                                                <display:table class="table table-hover table table-striped table-bordered"  name="${actionBean.cmBayaranPenerimaList}" size="10" id="line">
                                                    <display:setProperty name="basic.msg.empty_list" value="Tiada Rekod dijumpai " />
                                                    <display:column title="No." >${line_rowNum}</display:column>
                                                    <display:column title="Jabatan Pembayar" property="cmBayaranPkid.byrSamJabatanPkid.kod"/>
                                                    <display:column title="PTJ Pembayar" property="cmBayaranPkid.byrSamPtjPkid.kod"/>
                                                    <display:column title="No Baucar" property="cmBayaranPkid.noBaucar"/>
                                                    <display:column title="Tarikh Cek/Eft">
                                                        <fmt:formatDate value="${line.tkhCek}" pattern="dd/MM/yyyy"/>
                                                    </display:column>
                                                    <display:column title="No Cek/EFT" property="noCek"/>
                                                    <display:column title="Perihal" property="perihal"/>                                                  
                                                    <display:column title="Amaun (RM)" style="text-align:right">
                                                        <fmt:formatNumber pattern="#,###,##0.00" value="${line.amaun}"/>
                                                    </display:column>
                                                    <display:column title="Status" property="cmBayaranPkid.kodStatusTransaksiPkid.perihal"/>
                                                    <display:column title="Lihat Terperinci">
                                                        <div class="glyphicon glyphicon-eye-open" title="Lihat" onmouseover="this.style.cursor = 'pointer';" onclick="view('${line.pkid}');">&nbsp;</div>
                                                    </display:column>
                                                </display:table>
                                            </div>
                                        </div>  
                                    </div>
                                </div>
                                <div class="panel panel-default" style="display:none;">                            
                                    <div style="text-align: right">
                                        <s:submit name="viewSemakan" class="btn btn-primary" value="Lihat" style="width:100px;" id="view" />&nbsp;
                                    </div>
                                </div>
                            </div><br/>

                        </div>

                    </div>
                </s:form>
            </div>
        </div>

    </body>
</html>


