
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


            function pulangsemulapsUserSelesai() {

//                alert('d');
                var notaUser = $('#notaUser').val();
                if ((notaUser == '')) {


                } else {
//                    alert(notaUser);
                    var noteKontraktor = $('[name=notaUser]').val();
                    var reportId = $('[name=reportId]').val();
//                    alert(reportId);
                    var url = "${pageContext.request.contextPath}/helpdesk/kontraktor?pulangSemulaUser&reportId=" + reportId + "&notaUser=" + notaUser;
                    $.post(url,
                            function (data) {
//                                alert('s');
                                window.location = "${pageContext.request.contextPath}/welcome";
                            }, 'html');

                }
            }
        </script>
        <title>.: eTanah : Daftar Aduan :.</title> 
    </head>
    <body>

        <div class="row">
            <div class="col-md-12">
                <s:messages/>
                <s:errors/>
                <s:form beanclass="com.theta.portal.stripes.helpdesk.HelpdeskUserActionBean" data-toggle="validator"> 
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
                                        Maklumat Aduan
                                    </a>
                                </h4>
                            </div>
                            <div id="collapse-3" class="panel-collapse collapse in">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="panel-body">
                                            <div class="form-horizontal">
                                                <br>
                                                <display:table class="table table-hover table table-striped table-bordered" name="${actionBean.listAduan}" style="width:90;" requestURI="/saveDocuments" id="line">
                                                    <display:column title="No.">${line_rowNum}</display:column>
                                                    <display:column title="Maklumat Pelapor"><div class="col-sm-12">
                                                            <div class="row">
                                                                <div class="col-sm-2 "><span class="pull-left" ><p class="text">Nama</p></span></div><div class="col-sm-10"><span class="pull-left"><p class="text-info">${line.namaPelapor}</p></span></div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-sm-2 "><span class="pull-left" ><p class="text">Unit</p></span></div><div class="col-sm-10"><span class="pull-left"><p class="text-info">${line.unit}</p></span></div>
                                                            </div>
                                                        </div>
                                                    </display:column>

                                                    <display:column title="Keterangan">
                                                        <p class="text-info">${line.keterangan}</p>
                                                    </display:column>

                                                    <display:column title="Dokumen">

                                                        <c:set value="0" var="count"/>
                                                        <c:forEach items=" ${line.listOfDocument}" var="i" >
                                                            <p>      <a href="${line.listOfDocument[count].urlDownload}" target="_blank">  ${line.listOfDocument[count].fileName}</a></p>
                                                            <c:set value="${count +1}" var="count"/>
                                                        </c:forEach>

                                                    </display:column>
                                                </display:table>  
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
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse-2">
                                        <i class="fa fa-angle-up control-icon"></i>
                                        Maklumat Pegawai Teknikal
                                    </a>
                                </h4>
                            </div>
                            <div id="collapse-2" class="panel-collapse collapse in">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="panel-body">
                                            <div class="form-horizontal">
                                                <br>
                                                <display:table class="table table-hover table table-striped table-bordered" name="${actionBean.listTeknikal}" style="width:90;" requestURI="/saveDocuments" id="line">
                                                    <display:column title="No.">${line_rowNum}</display:column>
                                                    <display:column title="Maklumat Pegawai"><div class="col-sm-12">
                                                            <div class="row">
                                                                <div class="col-sm-2 "><span class="pull-left" ><p class="text">Nama</p></span></div><div class="col-sm-10"><span class="pull-left"><p class="text-info">${line.namaPegTeknikal}</p></span></div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-sm-2 "><span class="pull-left" ><p class="text">Unit</p></span></div><div class="col-sm-10"><span class="pull-left"><p class="text-info">${line.unit}</p></span></div>
                                                            </div>
                                                        </div>
                                                    </display:column>

                                                    <display:column title="Keterangan">
                                                        <p class="text-info"> ${line.catatanPegawaiTeknikal}</p>
                                                    </display:column>

                                                    <display:column title="Dokumen">

                                                        <c:set value="0" var="count"/>
                                                        <c:forEach items=" ${line.listOfDocument}" var="i" >
                                                            <p>      <a href="${pageContext.request.contextPath}${line.listOfDocument[count].urlDownload}" target="_blank">  ${line.listOfDocument[count].fileName}</a></p>
                                                            <c:set value="${count +1}" var="count"/>
                                                        </c:forEach>

                                                    </display:column>
                                                </display:table>  
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
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse-2">
                                        <i class="fa fa-angle-up control-icon"></i>
                                        Maklumat Kontraktor
                                    </a>
                                </h4>
                            </div>
                            <div id="collapse-2" class="panel-collapse collapse in">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="panel-body">
                                            <div class="form-horizontal">
                                                <br>
                                                <display:table class="table table-hover table table-striped table-bordered" name="${actionBean.listKontraktor}" style="width:90;" requestURI="/saveDocuments" id="line">
                                                    <display:column title="No.">${line_rowNum}</display:column>
                                                    <display:column title="Maklumat Kontraktor"><div class="col-sm-12">
                                                            <div class="row">
                                                                <div class="col-sm-2 "><span class="pull-left" ><p class="font-weight-bold">Nama</p></span></div><div class="col-sm-10"><span class="pull-left">${line.namaPegKontraktor}</span></div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-sm-2 "><span class="pull-left" ><p class="font-weight-bold">Unit</p></span></div><div class="col-sm-10"><span class="pull-left">${line.unit}</span></div>
                                                            </div>
                                                        </div>
                                                    </display:column>

                                                    <display:column title="Keterangan">
                                                        ${line.catatanKontraktor}
                                                    </display:column>

                                                    <display:column title="Dokumen">

                                                        <c:set value="0" var="count"/>
                                                        <c:forEach items=" ${line.listOfDocument}" var="i" >
                                                            <p>      <a href="${pageContext.request.contextPath}${line.listOfDocument[count].urlDownload}" target="_blank">  ${line.listOfDocument[count].fileName}</a></p>
                                                            <c:set value="${count +1}" var="count"/>
                                                        </c:forEach>

                                                    </display:column>
                                                </display:table>  
                                            </div>
                                        </div>

                                    </div>

                                </div>
                            </div>



                        </div>
                        <div class="panel panel-default">
                            <!-- Toggle Heading -->

                            <div class="panel panel-default">
                                <!-- Toggle Heading -->
                                <div class="panel panel-default">
                                    <!-- Toggle Heading -->
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse-4">
                                                <i class="fa fa-angle-up control-icon"></i>
                                                Maklumbalas
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapse-4" class="panel-collapse collapse in">
                                        <div class="panel-body">
                                            <div class="form-horizontal">
                                                <div class="container">
                                                    <label class="col-sm-2 control-label">Maklumbalas :</label>
                                                    <div class="col-sm-6 form-group">
                                                        <s:hidden name="reportId"/>
                                                        <s:textarea name="nota" class="form-control" data-error="Sila Masukkan maklumbalas" required="true"/>
                                                        <div class="help-block with-errors"></div>
                                                    </div>


                                                </div>
                                                 

                                            </div>
                                        </div>
                                    </div>
                                </div>  

                            </div>
                            <!--                            <div class="panel-body">
                                                            <div class="form-horizontal">
                                                                <div class="form-group">
                                                                    <div class="col-sm-offset-4 col-sm-12">
                            <s:submit name="simpan" value="Simpan" class="btn btn-primary formnovalidate" formnovalidate="true"/>  &nbsp;&nbsp;&nbsp;
                            <s:submit name="hantar" value="Hantar" class="btn btn-success formnovalidate" formnovalidate="true"/>  
                            <img src="${pageContext.request.contextPath}/images/loading_img.gif" style="display:none" id="loading-img"/>
                        </div>
                    </div>
                </div>
            </div>-->
                            <jsp:include page="/WEB-INF/jsp/include/action.jsp" />

                        </div>

                    </s:form>               
                </div>

            </div>
        </div>

    </body>
</html>
