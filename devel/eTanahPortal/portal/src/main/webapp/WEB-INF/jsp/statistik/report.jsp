
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

            function statistik() {
//                alert('a');
                var ptdId = $('[name=ptdOfficeId]').val();
                var tarikhMula = $('[name=tarikhMulaAduan]').val();
                var tarikhAhir = $('[name=tarikhAkhirAduan]').val();
                window.open("${pageContext.request.contextPath}/helpdesk/statistik/report?reportStatistik&pejabat=" + ptdId + "&tarikhMula=" + tarikhMula + "&tarikhAkhir=" + tarikhAhir, "Statistik",
                        "status=0,toolbar=0,location=0,menubar=0,width=1400px,height=800px,scrollbars=yes");
            }

            function reportStatistikEtanah() {
//                alert('a');
                var ptdId = $('[name=ptdOfficeId]').val();
                var tarikhMula = $('[name=tarikhMulaAduan]').val();
                var tarikhAhir = $('[name=tarikhAkhirAduan]').val();
                window.open("${pageContext.request.contextPath}/helpdesk/statistik/report?reportStatistikEtanah&pejabat=" + ptdId + "&tarikhMula=" + tarikhMula + "&tarikhAkhir=" + tarikhAhir, "Statistik",
                        "status=0,toolbar=0,location=0,menubar=0,width=1400px,height=800px,scrollbars=yes");
            }
            function reportKeseluruhan() {
//                alert('a');
                var ptdId = $('[name=ptdOfficeId]').val();
                var tarikhMula = $('[name=tarikhMulaAduan]').val();
                var tarikhAhir = $('[name=tarikhAkhirAduan]').val();
                window.open("${pageContext.request.contextPath}/helpdesk/statistik/report?reportKeseluruhan&pejabat=" + ptdId + "&tarikhMula=" + tarikhMula + "&tarikhAkhir=" + tarikhAhir, "Statistik",
                        "status=0,toolbar=0,location=0,menubar=0,width=1400px,height=800px,scrollbars=yes");
            }
            function reportLaporanTempoh() {
//                alert('a');
                var ptdId = $('[name=ptdOfficeId]').val();
                var tarikhMula = $('[name=tarikhMulaAduan]').val();
                var tarikhAhir = $('[name=tarikhAkhirAduan]').val();
                window.open("${pageContext.request.contextPath}/helpdesk/statistik/report?reportLaporanTempoh&pejabat=" + ptdId + "&tarikhMula=" + tarikhMula + "&tarikhAkhir=" + tarikhAhir, "Statistik",
                        "status=0,toolbar=0,location=0,menubar=0,width=1400px,height=800px,scrollbars=yes");
            }
            function papar() {
                alert('s');
                var ptdId = $('[name=ptdOfficeId]').val();
                var tarikhMula = $('[name=tarikhMulaAduan]').val();
                var tarikhAhir = $('[name=tarikhAkhirAduan]').val();
                alert('ss');
                var url = "${pageContext.request.contextPath}/helpdesk/statistik/report?byteString&pejabat=" + ptdId + "&tarikhMula=" + tarikhMula + "&tarikhAkhir=" + tarikhAhir;
                $.get(url,
                        function (data) {
                            console.log(data);
//                            if (data)
//                            {
//                                alert(data);
//                                var base64EncodedPDF = System.Convert.ToBase64String(pdfByteArray);
//                                window.open("data:application/pdf;base64, " + base64EncodedPDF);
//                            } 
alert(data);
    var arrrayBuffer = base64ToArrayBuffer(data); //data is the base64 encoded string
                            function base64ToArrayBuffer(base64) {
                                var binaryString = window.atob(base64);
                                var binaryLen = binaryString.length;
                                var bytes = new Uint8Array(binaryLen);
                                for (var i = 0; i < binaryLen; i++) {
                                    var ascii = binaryString.charCodeAt(i);
                                    bytes[i] = ascii;
                                }
                                return bytes;
                            }
                            var blob = new Blob([arrrayBuffer], {type: "application/pdf"});
                            var link = window.URL.createObjectURL(blob);
                            window.open(link,'', 'height=650,width=840');                    
    
    });
            }
            

            $(document).ready(function () {

                $('#datePicker')
                        .datepicker({
                            format: "dd/mm/yyyy",
                            changeMonth: true,
                            changeYear: true
                        });


            });
        </script>
        <title>.: eTanah : Statistik Aduan :.</title> 
    </head>
    <body>

        <div class="row">
            <div class="col-md-12">
                <s:messages/>
                <s:errors/>
                <s:form beanclass="com.theta.portal.stripes.statistik.StatistikReportActionBean" data-toggle="validator"> 
                    <s:useActionBean beanclass="com.theta.portal.stripes.util.ListUtil" var="list"/>
                    <s:hidden name="idPengguna"/>
                    <s:hidden name="technicalId"/>
                    <s:hidden name="reportId"/>
                    <div class="welcome-text-2">
                        <br>

                        <div class="panel panel-default">
                            <!-- Toggle Heading -->
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse-3">
                                        <i class="fa fa-angle-up control-icon"></i>
                                        Laporan Helpdesk ICT
                                    </a>
                                </h4>
                            </div>
                            <div id="collapse-3" class="panel-collapse collapse in">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="panel-body">
                                            <div class="form-horizontal">
                                                <br>
                                                <!--                                                <div class="container">
                                                                                                    <label class="col-md-2 control-label" for="textinput">No Aduan :</label>  
                                                                                                    <div class="col-md-3 form-group">
                                                <s:text name="noAduan" class="form-control"/>
                                            </div>
                                        </div>-->
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">Pejabat :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:select name="ptdOfficeId" style="width:300px" class="form-control" data-error="Sila Pilih Masalah!" required="true">
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${list.ptdOffice}" label="ptdOfficeName" value="ptdOfficeId"/>
                                                        </s:select>
                                                    </div>
                                                </div>
                                                <!--                                                <div class="container">
                                                                                                    <label class="col-md-2 control-label" for="textinput">Status :</label>  
                                                                                                    <div class="col-md-3 form-group">
                                                <s:select name="helpdeskTypeId" style="width:300px" class="form-control" data-error="Sila Pilih Masalah!" required="true">
                                                    <s:option value="">Sila Pilih</s:option>
                                                    <s:options-collection collection="${list.kodJenisMasalah}" label="helpdeskType" value="helpdeskTypeId"/>
                                                </s:select>
                                            </div>
                                        </div>-->
                                                <!--                                                <div class="container">
                                                
                                                                                                    <label class="col-md-2 control-label" for="textinput">Jenis Masalah :</label>  
                                                                                                    <div class="col-md-3 form-group">
                                                <s:select name="helpdeskTypeId" style="width:300px" class="form-control" data-error="Sila Pilih Masalah!" required="true">
                                                    <s:option value="">Sila Pilih</s:option>
                                                    <s:options-collection collection="${list.kodJenisMasalah}" label="helpdeskType" value="helpdeskTypeId"/>
                                                </s:select>
                                            </div>
                                        </div>-->
                                                <div class="container">
                                                    <label class="col-sm-2 control-label">Tarikh Mula :</label>
                                                    <div class="col-md-2 input-group date form-group datepicker">
                                                        <s:text name="tarikhMulaAduan" data-provide="datepicker" class="form-control datePicker" id="tarikhAduan" data-date-format="dd/mm/yyyy"  
                                                                oninvalid="setCustomValidity('Sila Masukkan Tarikh Aduan')" onchange="setCustomValidity('')"/>
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-sm-2 control-label">Tarikh Akhir :</label>
                                                    <div class="col-md-2 input-group date form-group datepicker">
                                                        <s:text name="tarikhAkhirAduan" data-provide="datepicker"  class="form-control datePicker" id="tarikhAduan" data-date-format="dd/mm/yyyy" 
                                                                oninvalid="setCustomValidity('Sila Masukkan Tarikh Aduan')" onchange="setCustomValidity('')"/>
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="table-responsive">
                                <div class="table-responsive" align="center"> 

                                    <table class="table table-bordered" border="0" id="tbl2" style="width:80%">
                                        <thead>
                                            <tr>
                                                <th style="width: 10%">Bil.</th>
                                                <th style="width: 40%">Jenis Laporan</th>
                                                <th>&nbsp;</th>
                                            </tr>
                                        </thead>

                                        <tr class='bankDetails bank' style="text-align: left;" data-toggle="modal"  data-target="#orderModal">
                                            <td scope="col" >
                                                1
                                            </td>
                                            <td scope="col" style='text-align: left;' class="text-uppercase">
                                                Statistik
                                            </td>

                                            <td scope="col">
                                                <s:button name="" value="Papar" onclick="statistik();" class="btn btn-success formnovalidate"/>
                                            </td>
                                        </tr>
                                        <tr class='bankDetails bank' style="text-align: left;" data-toggle="modal"  data-target="#orderModal">
                                            <td scope="col" >
                                                2
                                            </td>
                                            <td scope="col" style='text-align: left;' class="text-uppercase">
                                                Statistik eTanah
                                            </td>

                                            <td scope="col">
                                                <s:button name="" value="Papar" class="btn btn-success formnovalidate" onclick="reportStatistikEtanah();"/>
                                            </td>
                                        </tr>
                                        <tr class='bankDetails bank' style="text-align: left;" data-toggle="modal"  data-target="#orderModal">
                                            <td scope="col" >
                                                3
                                            </td>
                                            <td scope="col" style='text-align: left;' class="text-uppercase">
                                                Laporan Keseluruhan
                                            </td>

                                            <td scope="col">
                                                <s:button name="" value="Papar" class="btn btn-success formnovalidate" onclick="reportKeseluruhan();"/>
                                            </td>
                                        </tr>
                                        <tr class='bankDetails bank' style="text-align: left;" data-toggle="modal"  data-target="#orderModal">
                                            <td scope="col" >
                                                4
                                            </td>
                                            <td scope="col" style='text-align: left;' class="text-uppercase">
                                                Laporan Tempoh Aduan
                                            </td>

                                            <td scope="col">
                                                <s:button name="" value="Papar" class="btn btn-success formnovalidate" onclick="reportLaporanTempoh();"/>
                                            </td>
                                        </tr>

                                    </table>
                                </div></div>
                        </div>


                    </s:form>               
                </div>

            </div>
        </div>

    </body>
</html>
