

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
a.disabled {
  pointer-events: none;
  cursor: default;
}
a.enabled {
}
</style>
        <script type="text/javascript">
            function papar(val, val2) {
                var url = "${pageContext.request.contextPath}/helpdesk/main?view&idReport=" + val;
                $.get(url,
                        function (data) {
                            console.log(data);
                            if (data)
                            {
//                                noAduan;

//                                $("#action").text(val2);
                                 $('#namaPengadu').text(data.namaPengadu);
                                $('#tarikhAduan').text(data.tarikhAduan);
                                $("#keterangan").val(data.keterangan);
                                
                                $("#namaPegawaiTeknikal").text(data.namaPegawaiTeknikal);
                                $("#tarikhTeknikal").text(data.tarikhTeknikal);
                                $("#keteranganTeknikal").val(data.keteranganTeknikal);
                                $("#action").val(val2);
                                $("#tutupaduan").prop("disabled", data.userSemakButton);
                                $("#tolaktugasan").prop("disabled", data.tolakTugasanButton);

//                                $('#pulangSemulaTeknikal').val(data.keteranganKontraktor);




                            } else
                            {

                            }
                        });
            }
            

            $(document).ready(function () {
//            alert('s');
                $('#listLn').hide();
                $('#listPr').hide();
                $('#listPy').hide();
                $('#listSl').hide();
//                $('#listAc').hide();
                $('#listAi').hide();
                $('#line').DataTable();
                $('#linePr').DataTable();
                $('#linePy').DataTable();
                $('#lineSl').DataTable();
                $('#lineAc').DataTable();
                $('#lineAi').DataTable();
            });

            function testLn() {
                $('#listLn').show('slide');
                $('#accordion1').hide();
                $('#listPr').hide();
                $('#listPy').hide();
                $('#listSl').hide();
                $('#listAc').hide();
                $('#listAi').hide();
            }
            function testPr() {
                $('#listPr').show('slide');
                $('#accordion1').hide();
                $('#listLn').hide();
                $('#listPy').hide();
                $('#listSl').hide();
                $('#listAc').hide();
                $('#listAi').hide();
            }
            function testPy() {
                $('#listPy').show('slide');
                $('#accordion1').hide();
                $('#listLn').hide();
                $('#listPr').hide();
                $('#listSl').hide();
                $('#listAc').hide();
                $('#listAi').hide();
            }
            function testSl() {
                $('#listSl').show('slide');
                $('#accordion1').hide();
                $('#listLn').hide();
                $('#listPr').hide();
                $('#listPy').hide();
                $('#listAc').hide();
                $('#listAi').hide();
            }
            function testAc() {
                $('#listAc').show('slide');
                $('#accordion1').hide();
                $('#listLn').hide();
                $('#listPr').hide();
                $('#listSl').hide();
                $('#listPy').hide();
                $('#listAi').hide();
            }
            function testAi() {
                $('#listAi').show('slide');
                $('#accordion1').hide();
                $('#listLn').hide();
                $('#listPr').hide();
                $('#listSl').hide();
                $('#listAc').hide();
                $('#listPy').hide();
            }
        </script>
    </head>

    <body>  
        <div class="row">
            <div class="col-md-12">
                <s:form  beanclass="com.theta.portal.stripes.HelpdeskMainActionBean" data-toggle="validator">
                    <div class="welcome-text-2">
                        <br>

                        <div class="panel-group" id="accordion">
                            <div class="panel panel-default">
                                <!-- Toggle Heading -->
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse-1">
                                            <i class="fa fa-angle-up control-icon"></i>
                                            Skrin Carian Utama
                                        </a>
                                    </h4>
                                </div>
                                <!-- Toggle Content -->
                                <br/>
                                <div id="collapse-1" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-md-6">
                                                Carian
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="col-md-12 form-group">
                                                            <label class="control-label">No Aduan :</label>
                                                            <s:text name="idReport" class="form-control" data-error="Sila Masukkan Perkara/Urusan" />
                                                            <div class="help-block with-errors"></div></div>
                                                        <div class="col-md-6 form-group"><label class="control-label">Tarikh Mula :</label>
                                                            <s:text name="tarikhMula" data-provide="datepicker" data-date-format="dd/mm/yyyy" class="form-control" data-error="Sila Masukkan Perkara/Urusan"/>
                                                            <div class="help-block with-errors"></div></div>
                                                        <div class="col-md-6 form-group"><label class="control-label">Tarikh Hingga :</label>
                                                            <s:text name="tarikhAkhir" data-provide="datepicker" data-date-format="dd/mm/yyyy" class="form-control" data-error="Sila Masukkan Perkara/Urusan"/>
                                                            <div class="help-block with-errors"></div></div>
                                                            <s:submit name="cari" value = "Cari" class="btn btn-primary formnovalidate"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-horizontal">
                                                    <div class="row">

                                                        <div class="col-lg-6 col-md-6">
                                                            <div class="panel panel-success">
                                                                <div class="panel-heading">
                                                                    <div class="row">
                                                                        <div class="col-xs-3">
                                                                            <i class="fa fa-tags fa-5x"></i>
                                                                        </div>
                                                                        <div class="col-xs-9 text-right">
                                                                            <div class="huge">${actionBean.ac} &nbsp;</div>
                                                                            <div>Senarai Tugasan &nbsp;</div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <a href="#" onclick="testAc();">
                                                                    <div class="panel-footer">
                                                                        <span class="pull-left">Lihat Terperinci</span>
                                                                        <span class="pull-right"><i class="fa fa-arrow-circle-right" style="color: #0088cc"></i></span>
                                                                        <div class="clearfix"></div>
                                                                    </div>
                                                                </a>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6 col-md-6">
                                                            <div class="panel panel-warning">
                                                                <div class="panel-heading">
                                                                    <div class="row">
                                                                        <div class="col-xs-3">
                                                                            <i class="fa fa-plane fa-5x"></i>
                                                                        </div>
                                                                        <div class="col-xs-9 text-right">
                                                                            <div class="huge">${actionBean.pr} &nbsp;</div>
                                                                            <div>Pulang Semula &nbsp;</div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <a href="#" onclick="testPr();">
                                                                    <div class="panel-footer">
                                                                        <span class="pull-left">Lihat Terperinci</span>
                                                                        <span class="pull-right"><i class="fa fa-arrow-circle-right" style="color: #0088cc"></i></span>
                                                                        <div class="clearfix"></div>
                                                                    </div>
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div> <br/>

                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>




                        <div class="panel-group" id="listAc">
                            <div class="panel panel-default">
                                <!-- Toggle Heading -->
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#listAc" href="#collapse-7">
                                            <i class="fa fa-angle-up control-icon"></i>
                                            Senarai Tugasan
                                        </a>
                                    </h4>
                                </div>
                                <!-- Toggle Content -->
                                <br/>
                                <div id="collapse-7" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <div class="table-responsive" style="font-weight: normal">


                                                    <display:table class="table table-hover table table-striped table-bordered" name="${actionBean.list}" style="width:90;" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/helpdesk/main" id="line" sort="external" size="${actionBean.__pg_total_records}" partialList="true">
                                                        <display:column title="No."><a href="#" data-target="#claimtugasan" data-toggle="modal" style="cursor: pointer;" onclick="papar('${line.noAduan}','${line.url}');" class="${line.view}">${line_rowNum}</a></display:column>
                                                        <display:column title="No Aduan" ><a href="${pageContext.request.contextPath}/${line.url}" class="${line.view}">${line.noAduan}</a></display:column>
                                                        <%--<display:column title="Tarikh Aduan"property="tarikhAduan"/>--%>
                                                        <display:column title="Maklumat Pelapor">
                                                            <div class="col-sm-12">
                                                                <div class="row">
                                                                    ${line.namaPelapor} &nbsp;<b>|</b>&nbsp; ${line.jabatan} &nbsp;<b>|</b>&nbsp; ${line.unit}
                                                                </div>

                                                            </div>
                                                        </display:column>
                                                        <display:column title="Tarikh Aduan | Maklumat Aduan">
                                                            <div class="col-sm-12">
                                                                <div class="row">
                                                                    ${line.tarikhAduan}&nbsp;<b>|</b>&nbsp; ${line.jenisLaporan} &nbsp;<b>|</b>&nbsp; ${line.modul} &nbsp;<b>|</b>&nbsp; ${line.item}
                                                                </div>

                                                            </div>
                                                        </display:column>
                                                        <%--<display:column title="Nama Fail"property="jenisLapora"/>--%>
                                                        <display:column title="Peringkat" property="peringkat"/>
                                                        <%--<display:column title="Nama Fail"property="jabatan"/>--%>
                                                        <display:column title="Tarikh Terima | Bil Hari">
                                                            <div class="row">
                                                                ${line.tarikhTerima}&nbsp;<b>|</b>&nbsp; ${line.bilHari}</div>
                                                            </display:column>
                                                            <display:column title="Laporan">
                                                            <div class="row">
                                                                <a href="${pageContext.request.contextPath}/${line.urlReport}" target="_blank"> <img src="${pageContext.request.contextPath}/images/semakanDokumen.png"></a>
                                                            </div>
                                                        </display:column>



                                                    </display:table>
                                                </div>
                                            </div>  
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
<div class="panel-group" id="listPr">
                            <div class="panel panel-default">
                                <!-- Toggle Heading -->
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#listPr" href="#collapse-9">
                                            <i class="fa fa-angle-up control-icon"></i>
                                            Senarai Tugasan Pulang Semula
                                        </a>
                                    </h4>
                                </div>
                                <!-- Toggle Content -->
                                <br/>
                                <div id="collapse-9" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <div class="table-responsive" style="font-weight: normal">


                                                    <display:table class="table table-hover table table-striped table-bordered" name="${actionBean.listPulangSemula2}" style="width:90;" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/helpdesk/utiliti/pengawasan_tugas" id="line" sort="external" size="${actionBean.__pg_total_records}" partialList="true">
                                                        <display:column title="No."><a href="#" data-target="#claimtugasan" data-toggle="modal" style="cursor: pointer;" onclick="papar('${line.noAduan}','${line.url}');">${line_rowNum}</a></display:column>
                                                        <display:column title="No Aduan" ><a href="${pageContext.request.contextPath}/${line.url}">${line.noAduan}</a></display:column>
                                                        <%--<display:column title="Tarikh Aduan"property="tarikhAduan"/>--%>
                                                        <display:column title="Maklumat Pelapor">
                                                            <div class="col-sm-12">
                                                                <div class="row">
                                                                    ${line.namaPelapor} &nbsp;<b>|</b>&nbsp; ${line.jabatan} &nbsp;<b>|</b>&nbsp; ${line.unit}
                                                                </div>

                                                            </div>
                                                        </display:column>
                                                        <display:column title="Tarikh Aduan | Maklumat Aduan">
                                                            <div class="col-sm-12">
                                                                <div class="row">
                                                                    ${line.tarikhAduan}&nbsp;<b>|</b>&nbsp; ${line.jenisLaporan} &nbsp;<b>|</b>&nbsp; ${line.modul} &nbsp;<b>|</b>&nbsp; ${line.item}
                                                                </div>

                                                            </div>
                                                        </display:column>
                                                        <%--<display:column title="Nama Fail"property="jenisLapora"/>--%>
                                                        <display:column title="Peringkat" property="peringkat"/>
                                                        <%--<display:column title="Nama Fail"property="jabatan"/>--%>
                                                        <display:column title="Tarikh Terima | Bil Hari">
                                                            <div class="row">
                                                                ${line.tarikhTerima}&nbsp;<b>|</b>&nbsp; ${line.bilHari}</div>
                                                            </display:column>
                                                            <display:column title="Laporan">
                                                            <div class="row">
                                                                <a href="${pageContext.request.contextPath}/${line.urlReport}" target="_blank"> <img src="${pageContext.request.contextPath}/images/semakanDokumen.png"></a>
                                                            </div>
                                                        </display:column>



                                                    </display:table>
                                                </div>
                                            </div>  
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>


                    </div>

                    <div class="modal bd-example-modal-lg" id="claimtugasan" role="dialog"><!-- /.modal Selepas Penjadualan -->
                        <!--<form role="form">-->
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title">
                                        <div>Maklumat Tugasan</div>
                                    </h4>
                                </div>
                                <div class="modal-body">
                                    <div class="container-fluid">
                                         <div class="row">
                                                            <div class="col-sm-12">
                                                                Maklumat Aduan
                                                                <div class="row">
                                                                    <div class="col-sm-12">
                                                                        <label class="col-md-3 control-label" for="textinput">Nama Pengadu :</label>  
                                                                        <div class="col-md-3 form-group">
                                                                            <p id="namaPengadu">Tiada Maklumat</p>
                                                                        </div>
                                                                        <label class="col-sm-3 control-label">Tarikh Aduan :</label>
                                                                        <div class="col-sm-3 form-group">
                                                                            <p id="tarikhAduan">Tiada Maklumat</p>
                                                                        </div>
                                                                    </div>
                                                                    
                                                                   
                                                                    <div class="col-sm-12">
                                                                        <label class="col-sm-3 control-label">Keterangan Masalah :</label>
                                                                        <div class="col-sm-6 form-group">
                                                                           
                                                                            <s:textarea name="keterangan" id="keterangan" class="form-control" />
                                                                            <div class="help-block with-errors"></div>
                                                                        </div>
                                                                    </div>

                                                                </div>
                                                            </div>
                                                        </div>
                                                        <br>
                                                        <div class="row">
                                                            <div class="col-sm-12">
                                                                Maklumat Teknikal
                                                                <div class="row">


                                                                    <div class="col-sm-12">
                                                                        <label class="col-md-3 control-label" for="textinput">Nama Pegawai :</label>  
                                                                        <div class="col-md-3 form-group">
                                                                            <p id="namaPegawaiTeknikal" >Tiada Maklumat</p>
                                                                        </div>
                                                                        <label class="col-sm-3 control-label">Tarikh :</label>
                                                                        <div class="col-sm-3 form-group">
                                                                            <p id="tarikhTeknikal">Tiada Maklumat</p>
                                                                        </div>
                                                                    </div>
                                                                    
                                                                    <div class="col-sm-12">
                                                                        <label class="col-sm-3 control-label">Keterangan Masalah :</label>
                                                                        <div class="col-sm-6 form-group">
                                                                            <s:hidden name="action" id="action"/>
                                                                            <s:textarea name="keteranganTeknikal" id="keteranganTeknikal" class="form-control" />
                                                                            <div class="help-block with-errors"></div>
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

                                        <s:submit name="claimTask" class="btn btn-primary"  value="Ambil Tugasan" />
                                        <s:button name=" " class="btn btn-warning" data-dismiss="modal" value="Tutup" id="tutupDetails" style="width:100px;"/>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal-dialog -->
                            <!--</form>-->
                        </div>  </div> 




                </s:form>
            </div>

        </div>
    </body>
</html>
