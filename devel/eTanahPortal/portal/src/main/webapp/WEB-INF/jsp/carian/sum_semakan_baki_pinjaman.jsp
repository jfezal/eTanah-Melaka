<%-- 
    Document   : sum_semakan_baki_pinjaman
    Created on : Jun 6, 2016, 12:29:39 AM
    Author     : fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <script type="text/javascript">

        </script>

    </head>
    <body>
        <div class="row">
            <div class="col-md-12">
                <s:messages/>
                <s:errors/>
                <s:form beanclass="com.theta.portal.stripes.common.SemakanBakiPinjamanActionBean">
                    <div class="welcome-text-2">
                        <div class="panel panel-default">                            
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse-3">
                                        <i class="fa fa-angle-up control-icon"></i>
                                        Rumusan Semakan Baki Pinjaman
                                    </a>
                                </h4>
                            </div>
                            <div id="collapse-3" class="panel-collapse collapse in"><br>

                                <div class="panel-body">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">Jenis Pinjaman : </label>
                                            <div class="col-sm-10">
                                                <p class="form-control-static">${actionBean.slPinjaman.samJenisurusniagaPkid.perihal}</p>
                                            </div>
                                            <label class="col-sm-2 control-label">No Permohonan : </label>
                                            <div class="col-sm-10">
                                                <p class="form-control-static">${actionBean.slPinjaman.noRekod}</p>
                                            </div>
                                            <label class="col-sm-2 control-label">Baki Pinjaman (RM): </label>
                                            <div class="col-sm-10">
                                                <p class="form-control-static"><fmt:formatNumber pattern="#,###,##0.00" value="${actionBean.bakiBayaran}"/></p>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="table-responsive" style="text-align: center">
                                                <display:table class="table table-hover table table-striped table-bordered" name="${actionBean.slBayaranBalikList}" size="10" id="line">
                                                    <display:setProperty name="basic.msg.empty_list" value="Tiada Rekod dijumpai " />
                                                    <display:column title="Bil. Bayaran" property="bilBayaran"/>
                                                    <display:column title="Amaun Dibayar (RM)">
                                                        <fmt:formatNumber pattern="#,###,##0.00" value="${line.amaunDibayar}"/>
                                                    </display:column>
                                                    <display:column title="Bayaran Akhir (RM)">
                                                        <fmt:formatNumber pattern="#,###,##0.00" value="${line.bayaranAkhir}"/>
                                                    </display:column>
                                                </display:table>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-offset-2 col-sm-10">
                                                <a href="${pageContext.request.contextPath}/carian/baki_pinjaman" class="btn btn-primary">Kembali</a>&nbsp;
                                                <a href="${pageContext.request.contextPath}/welcome" class="btn btn-primary">Keluar</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </s:form>
            </div>
        </div>

    </body>
</html>


