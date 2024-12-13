<%-- 
    Document   : senaraiSemakDokumen
    Created on : Aug 8, 2018, 10:28:57 AM
    Author     : user
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Portal eTanah Melaka</title>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">

        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/
        ➥3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/
        ➥respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <script type="text/javascript">


            function s() {
                var val;

            }



        </script>
        <style>
            .header{}

            .footer {
                left: 0;
                bottom: 0;
                width: 100%;
                height: 100pt;
                background-color: #604B89;
                color: white;
                text-align: left;
            }
        </style>

    </head>
    <body>
        <s:messages/>
        <s:errors/>
        <s:form beanclass="com.theta.portal.stripes.portal.SemakanDokumenActionBean" data-toggle="validator">
            <div class="container">
                <div class="row">
                    <div class="col-sm-7 col-xs-12">
                        <div class="panel-group">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4><b>SENARAI SEMAK DOKUMEN</b></h4>
                                </div>
                                <div class="panel-body">
                                    <form>
                                        <div class="form-group">
                                            <label for="formSemakDokumen">Sila masukkan kata kunci untuk tujuan carian</label>
                                            <s:text name="key" class="form-control" id="formSemakDokumen"  placeholder="Kata kunci"/>
                                        </div>
                                        <p align="right"><s:submit name="cari" value="Cari" class="btn btn-primary formnovalidate" formnovalidate="true"/>  
                                            <button type="reset" class="btn btn-primary mb-2">Isi Semula</button></p>
                                    </form>
                                </div>
                            </div>
                        </div>

                        <div class="panel-group">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4><b>SENARAI URUSAN</b></h4>
                                </div>
                                <div class="panel-body">
                                    <div class="table-responsive" class="table table-hover">     
                                        <display:table class="table table-hover table table-striped table-bordered" name="${actionBean.listKodUrusan}" style="width:90;" id="line">
                                            <display:column title="Bil"></display:column>
                                            <display:column title="Kod Urusan">${line.kod}</display:column>
                                            <display:column title="Nama Urusan">${line.namaUrusan}</display:column>
                                            <display:column title="Perincian Dokumen"><button type="button" class="btn btn-info" onclick="papar222();" data-toggle="modal" data-target="#myModal">Papar</button></display:column>
                                        </display:table>
                                    </div>
                                </div>
                                <div class="panel-body">
                                    <div class="container">

                                        <!-- Modal -->
                                        <div class="modal fade" id="myModal" role="dialog">
                                            <div class="modal-dialog">

                                                <!-- Modal content-->
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <!--<button type="button" class="close" data-dismiss="modal">&times;</button>-->
                                                        <h5 class="modal-title"><font color="white"><strong>Dokumen Berkaitan Urusan</strong></font></h5>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="row">
                                                            <div class="col-xs-10">
                                                                <p>Dokumen Untuk Di Muat Turun</p>
                                                            </div>
                                                            <div class="col-xs-2">
                                                                <!--<a href="${pageContext.request.contextPath}/images/ptgMelaka.png" download="Logo">-->
                                                                <span class="glyphicon glyphicon-floppy-disk"></span>
                                                            </div>
                                                        </div> 

                                                        <div class="table-responsive" class="table table-hover">     
                                                            <display:table class="table table-hover table table-striped table-bordered"  style="width:90;" id="line">
                                                                <display:column title="Bil"></display:column>
                                                                <display:column title="Nama Dokumen">${line.namaDokumen}</display:column>
                                                                <display:column title="Mandatori">${line.mandatori}</display:column>
                                                                <display:column title="Perlu Pengesahan">${line.sah}</display:column>
                                                                <display:column title="Muat Turun">${line.mandatori}</display:column>

                                                            </display:table>
                                                        </div>
<!--                                                        <table id="example" class="display" style="width:100%">
                                                            <thead>
                                                                <tr>
                                                                    <th>First name</th>
                                                                    <th>Last name</th>
                                                                    <th>Position</th>
                                                                    <th>Office</th>
                                                                    <th>Start date</th>
                                                                    <th>Salary</th>
                                                                </tr>
                                                            </thead>
                                                            <tfoot>
                                                                <tr>
                                                                    <th>First name</th>
                                                                    <th>Last name</th>
                                                                    <th>Position</th>
                                                                    <th>Office</th>
                                                                    <th>Start date</th>
                                                                    <th>Salary</th>
                                                                </tr>
                                                            </tfoot>
                                                        </table>-->
                                                    </div>
                                                    <!--                            <div class="modal-footer">
                                                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                                </div>-->
                                                </div>

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
        <script>
            $(document).ready(function () {
        //  alert('a');
            });
            function papar222() {
                var val = 'SBMS';
        var url = "${pageContext.request.contextPath}/semak_dokumen?viewDokumen&kodUrusan=" + val;
                $.get(url,
                        function (data) {
                            console.log(data);
                            $('#example').DataTable({
                                "ajax": data
                            });
                            if (data)
                            {
            ${actionBean.listDokumen} = data;
                            } else
                            {
        //                                $('#amaunHadTahunan' + row).val('');
                            }
                        });
            }
        </script>

    </body>
</html>
