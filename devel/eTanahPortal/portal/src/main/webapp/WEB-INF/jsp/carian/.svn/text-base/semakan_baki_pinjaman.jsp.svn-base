<%-- 
    Document   : semakan_baki_pinjaman
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
        <title>.: Carian : Semakan Baki Pinjaman :.</title>    
        <script type="text/javascript">
            $(document).ready(function () {
                $('#line').DataTable();

                var d = new Date();

                if ($.isEmptyObject($('#tahun').val())) {
                    $('#tahun').val(d.getFullYear());
                }

            });

            function view(val) {
                $('#noRekod').val(val);
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
                <s:form beanclass="com.theta.portal.stripes.common.SemakanBakiPinjamanActionBean"> 
                    <s:useActionBean beanclass="com.theta.portal.stripes.util.ListUtil" var="list"/>
                    <s:text name="noRekod" id="noRekod" style="display:none;"/> 
                    <div class="welcome-text-2">
                        <div class="panel-group" id="accordion">
                            <div class="panel panel-default">
                                <!-- Toggle Heading -->
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse-4">
                                            <i class="fa fa-angle-up control-icon"></i>
                                            Semakan Baki Pinjaman
                                        </a>
                                    </h4>
                                </div>

                                <!-- Toggle Content -->
                                <div id="collapse-4" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <div class="form-horizontal">
                                            <br>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Tahun :</label>
                                                <div class="col-sm-6">
                                                    <input type="text" name="tahun" class="form-control" id="tahun" style="width:30%">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Jenis Pinjaman :</label>
                                                <div class="col-sm-6">
                                                    <s:select name="jenisPinjaman" id="jenisPinjaman" class="form-control" style="width:60%">
                                                        <s:option value="">Sila Pilih</s:option>
                                                        <s:options-collection collection="${list.slPinjaman}" label="perihal" value="pkid"/>
                                                    </s:select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-offset-2 col-sm-10">
                                                    <s:submit name="carian" value="Hantar" class="btn btn-primary" tabindex="1"/>&nbsp;  
                                                    <a href="${pageContext.request.contextPath}/carian/baki_pinjaman" class="btn btn-primary">Isi Semula</a>&nbsp;
                                                    <a href="${pageContext.request.contextPath}/welcome" class="btn btn-primary">Keluar</a>
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
                                        Senarai Semakan Baki Pinjaman
                                    </a>
                                </h4>
                            </div>
                            <div id="collapse-1" class="panel-collapse collapse in">
                                <br/>
                                <div class="panel-body">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <div class="table-responsive">
                                                <display:table class="table table-hover table table-striped table-bordered" name="${actionBean.bayaranBalikList}" size="10" id="line">
                                                    <display:setProperty name="basic.msg.empty_list" value="Tiada Rekod dijumpai " />
                                                    <display:column title="No." >${line_rowNum}</display:column>
                                                    <display:column title="Tahun" property="tahunGaji"/>
                                                    <display:column title="No Permohonan" property="noRekod"/>
                                                    <display:column title="Jenis Permohonan" property="samJenisurusniagaPkid.perihal"/>
                                                    <display:column title="Lihat Teperinci">
                                                        <div class="glyphicon glyphicon-eye-open" title="Lihat" onmouseover="this.style.cursor = 'pointer';" onclick="view('${line.noRekod}');">&nbsp;</div>
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


