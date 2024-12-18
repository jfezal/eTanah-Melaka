
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
            function papar(val) {
                var url = "${pageContext.request.contextPath}/helpdesk/utiliti/pengawasan_tugas?view&idReport=" + val;
                $.get(url,
                        function (data) {
                            console.log(data);
                            if (data)
                            {
//                                noAduan;
                                $('#reportId').val(data.noAduan);

                                $('#keterangan').val(data.keterangan);
                                $("#jenisAduan").text(data.jenisAduan);
                                $("#namaPengadu").text(data.namaPengadu);
                                $("#pejabatPengadu").text(data.pejabatPengadu);
                                $("#perkara").text(data.perkara);
                                $("#item").text(data.item);
                                $("#submodulType").text(data.submodulType);
                                $("#helpdeskType").text(data.helpdeskType);
                                $("#hardwareType").text(data.hardwareType);
                                $("#tarikhAduan").text(data.tarikhAduan);

                                $('#keteranganTeknikal').val(data.keteranganTeknikal);
                                $("#namaPegawaiTeknikal").text(data.namaPegawaiTeknikal);
                                $("#tarikhTeknikal").text(data.tarikhTeknikal);
                                $("#pejabatTeknikal").text(data.pejabatTeknikal);

                                $("#namaKontraktor").text(data.namaKontraktor);
                                $("#namaSyarikat").text(data.namaSyarikat);
                                $("#tarikhKontraktor").text(data.tarikhKontraktor);
                                $('#keteranganKontraktor').val(data.keteranganKontraktor);
                                $("#tutupaduan").prop("disabled", data.userSemakButton);
                                $("#tolaktugasan").prop("disabled", data.tolakTugasanButton);

//                                $('#pulangSemulaTeknikal').val(data.keteranganKontraktor);




                            } else
                            {

                            }
                        });
            }



            function tutupaduan1() {
//                alert('tutupaduan');
                var reportId = $('[name=reportId]').val();
//                alert(reportId);
                var url = "${pageContext.request.contextPath}/helpdesk/utiliti/pengawasan_tugas?tutupAduan&reportId=" + reportId;
                $.post(url,
                        function (data) {
//                            alert('s');
                            window.location = "${pageContext.request.contextPath}/helpdesk/utiliti/pengawasan_tugas";
                        }, 'html');
            }

            function tolaktugasan_button() {
//                alert('tolaktugasan');
                var reportId = $('[name=reportId]').val();
//                alert(reportId);
                var url = "${pageContext.request.contextPath}/helpdesk/utiliti/pengawasan_tugas?agihSemula&reportId=" + reportId;
                $.post(url,
                        function (data) {
//                            alert('s');
                            window.location = "${pageContext.request.contextPath}/helpdesk/utiliti/pengawasan_tugas";
                        }, 'html');
            }

        </script>
        <title>.: eTanah : Statistik Aduan :.</title> 
    </head>
    <body>

        <div class="row">
            <div class="col-md-12">
                <s:messages/>
                <s:errors/>
                <s:form beanclass="com.theta.portal.stripes.utiliti.PengawasanTugasActionBean" data-toggle="validator"> 
                    <s:useActionBean beanclass="com.theta.portal.stripes.util.ListUtil" var="list"/>
                    <s:hidden name="idPengguna"/>
                    <s:hidden name="technicalId"/>
                    <s:hidden name="reportId" id="reportId"/>
                    <div class="welcome-text-2">
                        <br>

                        <div class="panel panel-default">
                            <!-- Toggle Heading -->
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse-3">
                                        <i class="fa fa-angle-up control-icon"></i>
                                        Pengawasan Tugasan
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
                                                    <label class="col-md-2 control-label" for="textinput">No Aduan :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:text name="noAduan" class="form-control"/>
                                                    </div>
                                                </div>
                                                    <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">Status :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:select name="status" style="width:300px" class="form-control" data-error="Sila Pilih Masalah!" >
                                                            <s:option value="O">Dalam Proses</s:option>
                                                             <s:option value="T">Tutup</s:option>
                                                              <s:option value="A">Semua</s:option>
                                                        </s:select>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">Pejabat :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:select name="pejabat" style="width:300px" class="form-control" data-error="Sila Pilih Masalah!" >
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${list.ptdOffice}" label="ptdOfficeName" value="ptdOfficeId"/>
                                                        </s:select>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">Peringkat :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:select name="peringkat" style="width:300px" class="form-control" data-error="Sila Pilih Peringkat!" >
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${list.kodStatus}" label="name" value="kod"/>
                                                        </s:select>
                                                    </div>
                                                </div>
                                                    <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">Pegawai Teknikal :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:select name="techId" style="width:300px" class="form-control" data-error="Sila Pilih Peringkat!" >
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${actionBean.listTechId}" label="name" value="userId"/>
                                                        </s:select>
                                                    </div>
                                                </div>
                                                <div class="container">

                                                    <label class="col-md-2 control-label" for="textinput">Jenis Masalah :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:select name="jenismasalah" style="width:300px" class="form-control" data-error="Sila Pilih Masalah!" >
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${list.kodJenisMasalah}" label="helpdeskType" value="helpdeskTypeId"/>
                                                        </s:select>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-sm-2 control-label">Tarikh Mula :</label>
                                                    <div class="col-md-2 input-group date form-group datepicker">
                                                        <s:text name="tarikhMula" data-provide="datepicker" class="form-control datePicker" id="tarikhAduan" data-date-format="dd/mm/yyyy" 
                                                                oninvalid="setCustomValidity('Sila Masukkan Tarikh Aduan')" onchange="setCustomValidity('')"/>
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-sm-2 control-label">Tarikh Akhir :</label>
                                                    <div class="col-md-2 input-group date form-group datepicker">
                                                        <s:text name="tarikhAkhir" data-provide="datepicker" class="form-control datePicker" id="tarikhAduan" data-date-format="dd/mm/yyyy" 
                                                                oninvalid="setCustomValidity('Sila Masukkan Tarikh Aduan')" onchange="setCustomValidity('')"/>
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-sm-2 control-label"></label>
                                                    <div class="col-md-2 input-group date form-group datepicker">
                                                        <s:submit name="cari" value="Cari" class="btn btn-success formnovalidate"/>
                                                    </div>
                                                </div> 
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--<small>-->
                            <div class="table-responsive">
                                <div class="table-responsive" align="center"> 

                                    <display:table class="table table-hover table table-striped table-bordered" name="${actionBean.listPengawasan}" style="width:90;" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/helpdesk/utiliti/pengawasan_tugas" id="line" sort="external" size="${actionBean.__pg_total_records}" partialList="true">
                                        <display:column title="No.">${line_rowNum}</display:column>
                                        <display:column title="No Aduan" property="noAduan"/>
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
                                        <display:column title="Peringkat" property="status"/>
                                        <display:column title="Nama Pegawai" property="namaPegawai"/>
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
                                        <display:column title="Tindakan">
                                            <p>                                                   <!--  <s:button name="psTeknikal" value="Tutup Aduan"  class="btn btn-success formnovalidate" formnovalidate="true" data-target="#pulangSemulaTeknikalModal" onclick="papar(${line.noAduan});" data-toggle="modal" style="cursor: pointer;"/> --!>
                                            </p><p>                                                    <s:button name="psTeknikal" value="Urus Aduan " class="btn btn-success formnovalidate" formnovalidate="true" data-target="#pulangSemulaTeknikalModal" onclick="papar(${line.noAduan});" data-toggle="modal" style="cursor: pointer;"/>
                                            </p>
                                            </display:column>
    
    
                                        </display:table>  
                                    </div></div>
                                        <!--                            </small>-->
                                </div>
                                <div class="modal bd-example-modal-lg" id="pulangSemulaTeknikalModal" role="dialog"><!-- /.modal Selepas Penjadualan -->
                                    <form role="form">
                                        <div class="modal-dialog modal-lg">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                    <h4 class="modal-title">
                                                        <div>Urus Aduan</div>
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
                                                                        <label class="col-md-3 control-label" for="textinput">Pejabat :</label>  
                                                                        <div class="col-md-3 form-group">
                                                                            <p id="pejabatPengadu">Tiada Maklumat</p>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-sm-12">
                                                                        <label class="col-md-3 control-label" for="textinput">Jenis Masalah :</label>  
                                                                        <div class="col-md-3 form-group">
                                                                            <p id="helpdeskType">Tiada Maklumat</p>
                                                                        </div>
                                                                        <label class="col-md-3 control-label" for="textinput">Sub Kategori/ Perkakasan :</label>  
                                                                        <div class="col-md-3 form-group">
                                                                            <p id="hardwareType">Tiada Maklumat</p>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-sm-12">
                                                                        <label class="col-md-3 control-label" for="textinput">Sub Kategori/ Modul :</label>  
                                                                        <div class="col-md-3 form-group">
                                                                            <p id="submodulType">Tiada Maklumat</p>
                                                                        </div>
                                                                        <label class="col-md-3 control-label" for="textinput">Item :</label>  
                                                                        <div class="col-md-3 form-group">
                                                                            <p id="item">Tiada Maklumat</p>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-sm-12">
                                                                        <label class="col-sm-3 control-label">Perkara :</label>
                                                                        <div class="col-sm-6 form-group">
                                                                            <p id="perkara">Tiada Maklumat</p>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-sm-12">
                                                                        <label class="col-sm-3 control-label">Tarikh Aduan :</label>
                                                                        <div class="col-sm-4 form-group">
                                                                            <p id="tarikhAduan">Tiada Maklumat</p>
                                                                        </div>

                                                                    </div>
                                                                    <div class="col-sm-12">
                                                                        <label class="col-sm-3 control-label">Keterangan Masalah :</label>
                                                                        <div class="col-sm-6 form-group">
                                                                            <s:hidden name="reportId"/>
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
                                                                        <label class="col-md-3 control-label" for="textinput">Pejabat :</label>  
                                                                        <div class="col-md-3 form-group">
                                                                            <p id="pejabatTeknikal">Tiada Maklumat</p>
                                                                            <span id="namaPegawaiTeknikal"/>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-sm-12">
                                                                        <label class="col-sm-3 control-label">Tarikh Terima/Terima Semula:</label>
                                                                        <div class="col-sm-4 form-group">
                                                                            <p id="tarikhTeknikalTerima">Tiada Maklumat</p>
                                                                        </div>

                                                                    </div>
                                                                    <div class="col-sm-12">
                                                                        <label class="col-sm-3 control-label">Tarikh Agih Kontraktor:</label>
                                                                        <div class="col-sm-4 form-group">
                                                                            <p id="tarikhTeknikalAgih">Tiada Maklumat</p>
                                                                        </div>

                                                                    </div>
                                                                    <div class="col-sm-12">
                                                                        <label class="col-sm-3 control-label">Tarikh Selesai:</label>
                                                                        <div class="col-sm-4 form-group">
                                                                            <p id="tarikhTeknikalTutup">Tiada Maklumat</p>
                                                                        </div>

                                                                    </div>
                                                                    <div class="col-sm-12">
                                                                        <label class="col-sm-3 control-label">Keterangan Masalah :</label>
                                                                        <div class="col-sm-6 form-group">
                                                                            <s:hidden name="reportId"/>
                                                                            <s:textarea name="keteranganTeknikal" id="keteranganTeknikal" class="form-control" />
                                                                            <div class="help-block with-errors"></div>
                                                                        </div>
                                                                    </div>

                                                                </div>
                                                            </div>
                                                        </div>
                                                        <br>
                                                        <div class="row">
                                                            <div class="col-sm-12">
                                                                Maklumat Kontraktor
                                                                <br>
                                                                <br>
                                                                <div class="row">
                                                                    <div class="col-sm-12">
                                                                        <label class="col-md-3 control-label" for="textinput">Nama :</label>  
                                                                        <div class="col-md-3 form-group">
                                                                            <p id="namaKontraktor">Tiada Maklumat</p>
                                                                        </div>
                                                                        <label class="col-md-3 control-label" for="textinput">Syarikat :</label>  
                                                                        <div class="col-md-3 form-group">
                                                                            <p id="namaSyarikat">Tiada Maklumat</p>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-sm-12">
                                                                        <label class="col-sm-3 control-label">Tarikh Terima:</label>
                                                                        <div class="col-sm-4 form-group">
                                                                            <p id="tarikhKontraktorTerima"></p>
                                                                        </div>

                                                                    </div>
                                                                    <div class="col-sm-12">
                                                                        <label class="col-sm-3 control-label">Tarikh Se:</label>
                                                                        <div class="col-sm-4 form-group">
                                                                            <p id="tarikhKontraktorTerima"></p>
                                                                        </div>

                                                                    </div>
                                                                    <div class="col-sm-12">
                                                                        <label class="col-sm-3 control-label">Keterangan Masalah :</label>
                                                                        <div class="col-sm-6 form-group">
                                                                            <s:hidden name="reportId"/>
                                                                            <s:textarea name="keteranganKontraktor" id="keteranganKontraktor" class="form-control" />
                                                                            <div class="help-block with-errors"></div>
                                                                        </div>
                                                                    </div>

                                                                </div>
                                                            </div>
                                                        </div>
                                                        <br>
                                                        <br>
                                                    </div>


                                                    <div class="modal-footer">

                                                        <s:button name=" " class="btn btn-primary"  value="Tutup Aduan" id="tutupaduan" onClick="tutupaduan1();"/>
                                                        <s:button name=" " class="btn btn-primary" value="Tolak Tugasan" id="tolaktugasan" onClick="tolaktugasan_button();"/>
                                                        <s:button name=" " class="btn btn-warning" data-dismiss="modal" value="Tutup" id="tutupDetails" style="width:100px;"/>
                                                    </div>
                                                </div><!-- /.modal-content -->
                                            </div><!-- /.modal-dialog -->
                                    </form>
                                </div>

                            </s:form>               
                        </div>

                    </div>
                </div>

                </body>
                </html>
