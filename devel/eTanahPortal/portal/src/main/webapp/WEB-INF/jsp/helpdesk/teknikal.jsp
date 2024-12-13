
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


            function pulangsemulaTeknikal() {

//                alert('d');
                var notaTeknikal = $('#notaTeknikal').val();
                if ((notaKontraktor == '')) {


                } else {
//                    alert(notaTeknikal);
                    var notaTeknikal = $('[name=notaTeknikal]').val();
                    var reportId = $('[name=reportId]').val();
//                    alert(reportId);
                    var url = "${pageContext.request.contextPath}/helpdesk/kontraktor?pulangSemulaTeknikal&reportId=" + reportId + "&noteTeknikal=" + notaTeknikal;
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
                <s:form beanclass="com.theta.portal.stripes.helpdesk.PTeknikalActionBean" data-toggle="validator"> 
                    <s:useActionBean beanclass="com.theta.portal.stripes.util.ListUtil" var="list"/>
                    <s:hidden name="idPengguna"/>
                    <s:hidden name="technicalId"/>
                    <s:hidden name="reportId"/>
                    <div class="welcome-text-2">
                        <br>
                         <c:if test="${actionBean.pulangsemula}">
                            <div class="panel panel-default">
                                <!-- Toggle Heading -->
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse-31">
                                            <i class="fa fa-angle-up control-icon"></i>
                                            Pulang Semula
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapse-31" class="panel-collapse collapse in">
                                    <div class="row" >
                                        <div class="col-md-12">
                                            <div class="panel-body">
                                                <div class="form-horizontal" style="overflow: scroll;height: 40%;">
                                                    <br>    
                                                    <display:table class="table table-hover table table-striped table-bordered" name="${actionBean.listPulangSemula}" style="width:80;" requestURI="/saveDocuments" id="line">
                                                        <display:column title="No.">${line_rowNum}</display:column>
                                                        <display:column title="Nama">
                                                            ${line.nama}
                                                        </display:column>
                                                        <display:column title="Keterangan"  style="width:50%;">
                                                            <p class="text-left">${line.catatan}</p>
                                                        </display:column>
                                                        <display:column title="Tarikh">
                                                            ${line.tarikhStage}
                                                        </display:column>
                                                    </display:table> 
                                                </div>
                                            </div>

                                        </div>

                                    </div>
                                </div>
                            </div>
                        </c:if>
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

                                                    <display:column title="Keterangan Aduan" style="width:50%;">
                                                        <table style="width:100%;">
                                                            <tr>
                                                                <td align="left" style="width:20%;"><p class="text">Perkara</p></td><td align="left"><p class="text-info">${line.perkara}</p></td>
                                                            </tr>
                                                            <tr>
                                                                <td align="left" style="width:20%;"><p class="text">Keterangan</p></td><td align="left"><p class="text-info">${line.keterangan}</p></td>
                                                            </tr>
                                                            </table>
                                                        
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
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse-3">
                                        <i class="fa fa-angle-up control-icon"></i>
                                        Maklumat Agihan 
                                    </a>
                                </h4>
                            </div>
                            <div id="collapse-3" class="panel-collapse collapse in">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="panel-body">
                                            <div class="form-horizontal">
                                                <br>
                                                <display:table class="table table-hover table table-striped table-bordered" name="${actionBean.listAgihan}" style="width:90;" requestURI="/saveDocuments" id="line">
                                                    <display:column title="No.">${line_rowNum}</display:column>
                                                    <display:column title="Pegawai Agihan">${line.pegawaiAgihan}</display:column>
                                                    <display:column title="Tarikh Agihan">
                                                        ${line.tarikhAgih} 
                                                    </display:column>
                                                    <display:column title="Catatan"  style="width:50%;">
                                                        <p class="text-left">${line.catatanPegawaiAgihan}</p>
                                                    </display:column>

                                                </display:table> 

                                            </div>
                                        </div>

                                    </div>

                                </div>
                            </div>
                            <div class="panel panel-default">
                                <!-- Toggle Heading -->
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse-3">
                                            <i class="fa fa-angle-up control-icon"></i>
                                            Maklumat Pegawai Teknikal 
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
                                                        <label class="col-sm-2 control-label">Keterangan / Maklumbalas :</label>
                                                        <div class="col-sm-6 form-group">
                                                            <s:hidden name="reportId"/>
                                                            <s:textarea name="keterangan" class="form-control" data-error="Sila Masukkan Keterangan Masalah" />
                                                            <div class="help-block with-errors"></div>
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
                                                        <s:file name="file"  id="document"  data-error="Sila Pilih Fail!" />
                                                        <div class="help-block with-errors">
                                                            <s:submit name="saveDocuments" value="Muat Naik" class="btn btn-primary formnovalidate" formnovalidate="false"/>
                                                        </div>
                                                    </div>


                                                </div>
                                                <display:table class="table table-hover table table-striped table-bordered" name="${actionBean.listOfDocument}" style="width:90;" requestURI="/saveDocuments" id="line">
                                                    <display:column title="No.">${line_rowNum}</display:column>
                                                    <display:column title="Nama Fail">
                                                        <a href="${pageContext.request.contextPath}${line.urlDownload}" target="_blank"> ${line.fileName}</a>
                                                    </display:column>

                                                    <display:column title="Hapus">
                                                        <a href="adminUser?deleteFile&id=${line.id}" onclick="return confirm('Adakah anda pasti untuk hapus?');">
                                                            <img src="${pageContext.request.contextPath}/images/icon/gnome_edit_delete.png" alt="delete" width="20" height="20"/>
                                                        </a>
                                                    </display:column>
                                                </display:table>  

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
