<%-- 
    Document   : maklumat_asas_AL
    Created on : Feb 1, 2017, 11:36:02 AM
    Author     : logesvaran
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="${pageContext.request.contextPath}/js/util.js" type="text/javascript"></script>     
        <script type="text/javascript">
            
            $(document).ready(function(){
                alert("actionBean.pyFailinduk.nama");
            });
            
        </script>
    </head>

<div class="row">
    <div class="col-md-12">
        <s:messages/>
        <s:errors/>
        <s:form beanclass="com.theta.portal1speks.stripes.common.MaklumatAsasALAction" name="form1" data-toggle="validator"> 

            <div class="welcome-text-2">
                <br>
                <div class="panel panel-default">                            
                    <!-- Toggle Heading -->
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse-1">
                                <i class="fa fa-angle-up control-icon"></i>Maklumat Pemohon
                            </a>
                        </h4>
                    </div>
                    <div id="collapse-1" class="panel-collapse collapse in">
                        <div class="panel-body"><br>
                            <div class="form-horizontal">

                                <div class="container">
                                    <label class="col-md-2 control-label">Nama : </label>
                                    <div class="col-md-3 form-group">
                                        <p class="form-control-static">${actionBean.pyFailinduk.nama}</p>
                                    </div>
                                </div>
                                <div class="container">
                                    <label class="col-md-2 control-label">No Kad Pengenalan : </label>
                                    <div class="col-md-3 form-group">
                                        <p class="form-control-static">${actionBean.pyFailinduk.noPengenalan}</p>
                                    </div>
                                </div>

                                <div class="container">
                                    <label class="col-md-2 control-label">Jawatan : </label>
                                    <div class="col-md-3 form-group">
                                        <p class="form-control-static">${actionBean.pyFailinduk.kodGelaranJawatanPkid.perihal}</p>
                                    </div>
                                    <label class="col-md-2 control-label">Gred Jawatan : </label>
                                    <div class="col-md-3 form-group">
                                        <p class="form-control-static">${actionBean.pyFailinduk.samGredPkid.kod}</p>
                                    </div>
                                </div>

                                <div class="container">
                                    <label class="col-md-2 control-label">Jabatan : </label>
                                    <div class="col-md-3 form-group">
                                        <p class="form-control-static"></p>
                                    </div>
                                </div>
                                <div class="container">
                                    <label class="col-md-2 control-label">Alamat Pejabat : </label>
                                    <div class="col-md-3 form-group">
                                        <p class="form-control-static"></p>
                                    </div>
                                </div>
                                <div class="container">
                                    <label class="col-md-2 control-label">&nbsp;</label>
                                    <div class="col-md-3 form-group">
                                        <p class="form-control-static"></p>
                                    </div>
                                </div>
                                <div class="container">
                                    <label class="col-md-2 control-label">&nbsp;</label>
                                    <div class="col-md-3 form-group">
                                        <p class="form-control-static"></p>
                                    </div>
                                </div>
                                <div class="container">
                                    <label class="col-md-2 control-label">Poskod : </label>
                                    <div class="col-md-3 form-group">
                                        <p class="form-control-static"></p>
                                    </div>
                                </div>
                                <div class="container">
                                    <label class="col-md-2 control-label">Daerah : </label>
                                    <div class="col-md-3 form-group">
                                        <p class="form-control-static"></p>
                                    </div>
                                </div>
                                <div class="container">
                                    <label class="col-md-2 control-label">Negeri : </label>
                                    <div class="col-md-3 form-group">
                                        <p class="form-control-static"></p>
                                    </div>
                                </div>
                                <div class="container">
                                    <label class="col-md-2 control-label">No. Telefon : </label>
                                    <div class="col-md-3 form-group">
                                        <p class="form-control-static">${actionBean.pyFailinduk.noTelPejabat}</p>
                                    </div>
                                    <label class="col-md-2 control-label">No. Telefon Bimbit : </label>
                                    <div class="col-md-3 form-group">
                                        <p class="form-control-static">${actionBean.pyFailinduk.noTelBimbit}</p>
                                    </div>
                                </div>
                                <div class="container">
                                    <label class="col-md-2 control-label">Emel : </label>
                                    <div class="col-md-3 form-group">
                                        <p class="form-control-static">${actionBean.pyFailinduk.emel}</p>
                                    </div>
                                </div>
                            </div>

                            <br>


                        </div>

                    </div>
                </div>


            </div>


            <div class="welcome-text-2">
                <div class="panel panel-default">
                    <!-- Toggle Heading -->
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse-2">
                                <i class="fa fa-angle-up control-icon"></i>
                                Maklumat Pendapatan
                            </a>
                        </h4>
                    </div> <br/>

                    <div class="container">
                        <label class="col-md-2 control-label">Gaji Pokok (RM) : </label>
                        <div class="col-md-3 form-group">
                            <p class="form-control-static"></p>
                        </div>
                    </div>



                    <div class="container">
                        <label class="col-md-2 control-label">Elaun (RM) : </label>
                        <div class="col-md-3 form-group">
                            <p class="form-control-static"></p>
                        </div>
                    </div> 

                </div>
            </div>

            <div class="welcome-text-2">
                <div class="panel panel-default">
                    <!-- Toggle Heading -->
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse-2">
                                <i class="fa fa-angle-up control-icon"></i>
                                Maklumat Akaun Bank
                            </a>
                        </h4>
                    </div> <br/>

                    <div class="container">
                        <label class="col-md-2 control-label">Nama Bank : </label>
                        <div class="col-md-3 form-group">
                            <p class="form-control-static"></p>
                        </div>
                    </div>

                    <div class="container">
                        <label class="col-md-2 control-label">No.Akaun Bank : </label>
                        <div class="col-md-3 form-group">
                            <p class="form-control-static"></p>
                        </div>
                    </div> 

                </div>
            </div>

        </s:form>
    </div>
</div>
</html>




