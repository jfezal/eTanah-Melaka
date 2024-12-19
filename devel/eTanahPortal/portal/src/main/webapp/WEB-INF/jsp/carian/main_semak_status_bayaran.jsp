<%-- 
    Document   : main_semak_status_bayaran
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
        <title>.: Carian : Semakan Status Bayaran :.</title>    
        <script type="text/javascript">
            $(document).ready(function () {
                $('#line').DataTable();


            });


            function cetak(pkid) {
                var randomnumber = Math.floor((Math.random() * 100) + 1);
                var params = 'pkid=' + pkid;
                var url = '${pageContext.request.contextPath}/carian/status_bayaran?cetak&' + params;

//                window.open(url, randomnumber, "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
                 document.getElementById("ifr2").src = url;
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
                <s:form beanclass="com.theta.portal.stripes.common.SemakanStatusBayaranActionBean"> 
                    <s:hidden name="cmBayaranPenerima.pkid"/> 
                    <div class="welcome-text-2">
                        <div class="panel-group" id="accordion">
                            <div class="panel panel-default">
                                <!-- Toggle Heading -->
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse-4">
                                            <i class="fa fa-angle-up control-icon"></i>
                                            Maklumat Terperinci Status Bayaran 
                                        </a>
                                    </h4>
                                </div>

                                <!-- Toggle Content -->
                                <div id="collapse-4" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <br>
                                        <div class="form-horizontal">
                                            <div class="container">
                                                <label class="col-sm-3 control-label">Nama : </label>
                                                <div class="col-sm-7 form-group">
                                                    <c:if test="${actionBean.cmBayaranPenerima.getAkaunSamPelangganAkaunPkid() ne null}">
                                                        <p class="form-control-static">${actionBean.cmBayaranPenerima.akaunSamPelangganAkaunPkid.samPelangganPkid.nama}</p>
                                                    </c:if>
                                                    <c:if test="${actionBean.cmBayaranPenerima.getPyFailindukPkid() ne null}">
                                                        <p class="form-control-static">${actionBean.cmBayaranPenerima.pyFailindukPkid.nama}</p>
                                                    </c:if>   
                                                </div>
                                                <label class="col-sm-3 control-label">No. KP / No.Daftar Syarikat : </label>
                                                <div class="col-sm-7 form-group">
                                                    <c:if test="${actionBean.cmBayaranPenerima.getAkaunSamPelangganAkaunPkid() ne null}">
                                                        <p class="form-control-static">${actionBean.cmBayaranPenerima.akaunSamPelangganAkaunPkid.samPelangganPkid.noDaftarniaga}</p>
                                                    </c:if>
                                                    <c:if test="${actionBean.cmBayaranPenerima.getPyFailindukPkid() ne null}">
                                                        <p class="form-control-static">${actionBean.cmBayaranPenerima.pyFailindukPkid.noPengenalan}</p>
                                                    </c:if>
                                                </div>
                                            </div>
                                            <div class="container">
                                                <label class="col-sm-3 control-label">Jabatan Pembayar : </label>
                                                <div class="col-sm-7 form-group">
                                                    <p class="form-control-static">[${actionBean.cmBayaranPenerima.cmBayaranPkid.byrSamJabatanPkid.kod}]&nbsp${actionBean.cmBayaranPenerima.cmBayaranPkid.byrSamJabatanPkid.perihal}</p>
                                                </div>
                                                <label class="col-sm-3 control-label">PTJ Pembayar : </label>
                                                <div class="col-sm-7 form-group">
                                                    <p class="form-control-static">[${actionBean.cmBayaranPenerima.cmBayaranPkid.byrSamPtjPkid.kod}]&nbsp${actionBean.cmBayaranPenerima.cmBayaranPkid.byrSamPtjPkid.perihal}</p>
                                                </div>
                                            </div>
                                            <div class="container">                                               
                                                <label class="col-md-3 control-label" for="textinput">No Baucer Bayaran :</label>  
                                                <div class="col-md-2 form-group">
                                                    <p class="form-control-static">${actionBean.cmBayaranPenerima.cmBayaranPkid.noBaucar}</p>
                                                </div>
                                                <label class="col-md-3 control-label" for="textinput">Amaun Bayaran :</label>  
                                                <div class="col-md-3 form-group">
                                                    <p class="form-control-static">RM &nbsp;<fmt:formatNumber pattern="#,###,##0.00" value="${actionBean.cmBayaranPenerima.amaun}"/></p>
                                                </div>
                                            </div>
                                            <div class="container">
                                                <label class="col-sm-3 control-label">Perihal Bayaran : </label>
                                                <div class="col-sm-7 form-group">
                                                    <p class="form-control-static">${actionBean.cmBayaranPenerima.perihal}</p>
                                                </div>
                                            </div>
                                            <div class="container">
                                                <label class="col-sm-3 control-label">No Rujukan Invois : </label>
                                                <div class="col-sm-7 form-group">
                                                    <p class="form-control-static">${actionBean.cmBayaranInvois.noInvois}</p>
                                                </div>
                                            </div>
                                            <div class="container">
                                                <label class="col-sm-3 control-label">Tarikh Invois : </label>
                                                <div class="col-sm-7 form-group">
                                                    <p class="form-control-static"><fmt:formatDate value="${actionBean.cmBayaranInvois.tkhInvois}" pattern="dd/MM/yyyy"/></p>
                                                </div>
                                            </div>
                                            <div class="container">
                                                <label class="col-sm-3 control-label">No.EFT/No.Cek : </label>
                                                <div class="col-sm-7 form-group">
                                                    <p class="form-control-static">${actionBean.cmBayaranPenerima.noCek}</p>
                                                </div>
                                            </div>
                                            <div class="container">
                                                <label class="col-sm-3 control-label">Nama Bank : </label>
                                                <div class="col-sm-7 form-group">
                                                    <p class="form-control-static">${actionBean.cmBayaranPenerima.akaunSamPelangganAkaunPkid.samBankPkid.perihal}</p>
                                                </div>
                                            </div>
                                            <div class="container">
                                                <label class="col-sm-3 control-label">No. Akaun Bank : </label>
                                                <div class="col-sm-7 form-group">
                                                    <p class="form-control-static">${actionBean.cmBayaranPenerima.akaunSamPelangganAkaunPkid.noAkaun}</p>
                                                </div>
                                            </div>
                                            <div class="container">
                                                <label class="col-sm-3 control-label">Tarikh Bank Proses : </label>
                                                <div class="col-sm-7 form-group">
                                                    <p class="form-control-static"><fmt:formatDate value="${actionBean.cmBayaranInvois.tkhInvois}" pattern="dd/MM/yyyy"/></p>
                                                </div>
                                            </div>
                                            <div class="container">
                                                <label class="col-sm-3 control-label">No. Telefon Bimbit : </label>
                                                <div class="col-sm-7 form-group">
                                                    <p class="form-control-static">${actionBean.noTelefon}</p>
                                                </div>
                                            </div>
                                            <div class="container">
                                                <label class="col-md-3 control-label" for="textinput">Alamat Emel :</label>  
                                                <div class="col-md-2 form-group">
                                                    <p class="form-control-static">${actionBean.emel}</p>
                                                </div>
                                                <label class="col-md-3 control-label" for="textinput">No. Faks :</label>  
                                                <div class="col-md-3 form-group">
                                                    <p class="form-control-static">${actionBean.faks}</p>
                                                </div>
                                            </div>
                                            <div class="container">
                                                <label class="col-sm-3 control-label">Alamat Pos : </label>
                                                <div class="col-sm-7 form-group">
                                                    <p class="form-control-static">${actionBean.alamat}</p>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-offset-2 col-sm-10">
                                                    <a onclick="cetak('${actionBean.cmBayaranPenerima.cmBayaranPkid.pkid}');" class="btn btn-primary" data-toggle="modal" data-target="#myModal">Cetak</a>&nbsp;
                                                    <a href="${pageContext.request.contextPath}/carian/status_bayaran" class="btn btn-primary">Keluar</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Modal -->
                        <div class="modal fade" id="myModal" tabindex="1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title" id="myModalLabel"><s:text name=" " id="tt" readonly="true" style="outline: none;border: none;border-color: transparent;background-color : #0d4da1; "/></h4>
                                    </div>
                                    <div class="modal-body" style="text-align: center">
                                        <iframe id="ifr2" src="" width="90%" height="1080" frameborder="0" allowtransparency="true"></iframe>  
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" data-dismiss="modal">Tutup</button>
                                    </div>
                                </div>
                                <!-- /.modal-content -->
                            </div>
                            <!-- /.modal-dialog -->
                        </div>
                        <!-- /.modal -->

                    </div>
                </s:form>
            </div>
        </div>

    </body>
</html>


