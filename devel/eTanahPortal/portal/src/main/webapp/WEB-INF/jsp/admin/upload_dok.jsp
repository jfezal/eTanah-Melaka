<%-- 
    Document   : update_info_user
    Created on : Apr 28, 2013, 7:11:47 PM
    Author     : wan.fairul
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/combodate.js"></script>
        <title>.: eTanah : Kemaskini Maklumat Pengguna :.</title> 
    </head>
    <body>

        <div class="row">
            <div class="col-md-12">
                <s:messages/>
                <s:errors/>
                <s:form beanclass="com.theta.portal.stripes.action.UserAction" data-toggle="validator"> 
                    <s:useActionBean beanclass="com.theta.portal.stripes.util.ListUtil" var="list"/>
                    <s:hidden name="pengguna.idPengguna"/>
                    <div class="welcome-text-2">
                        <br>
                        <div class="panel panel-default">
                            <!-- Toggle Heading -->
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse-3">
                                        <i class="fa fa-angle-up control-icon"></i>
                                        Dokumen Sokongan
                                    </a>
                                </h4>
                            </div>
                            <div id="collapse-3" class="panel-collapse collapse in">
                                <div class="panel-body">
                                    <div class="form-horizontal">
                                        <div class="container">
                                            <label class="col-sm-2 control-label">Nama Dokumen :</label>
                                            <div class="col-sm-6 form-group">
                                                <s:text name="namaDokumen" class="form-control" data-error="Sila Masukkan Nama Dokumen!" />
                                                <div class="help-block with-errors"></div>
                                            </div>
                                        </div>
                                        <div class="container">
                                            <label class="col-sm-2 control-label">Fail :</label>
                                            <div class="col-sm-6 form-group">
                                                <s:file name="document" onchange="readURL(this);" id="document"  data-error="Sila Pilih Fail!" />
                                                <div class="help-block with-errors"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-body">
                                <div class="form-horizontal">
                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-10">
                                            <s:submit name="saveDocuments" value="Muat Naik" class="btn btn-primary formnovalidate" formnovalidate="true"/>            
                                            <img src="${pageContext.request.contextPath}/images/loading_img.gif" style="display:none" id="loading-img"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <c:if test="${fn:length(actionBean.listOfDocument) > 0}">
                            <div class="panel panel-default">
                                <!-- Toggle Heading -->
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse-3">
                                            <i class="fa fa-angle-up control-icon"></i>
                                            Senarai Dokumen Sokongan
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapse-3" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <div class="form-horizontal">

                                            <display:table class="table table-hover table table-striped table-bordered" name="${actionBean.listOfDocument}" style="width:90;" requestURI="/saveDocuments" id="line">
                                                <display:column title="No.">${line_rowNum}</display:column>
                                                <display:column title="File Name">${line.namaDokumen}</display:column>
                                                <c:if test="${line.jenisKandungan eq actionBean.forPdf}">
                                                    <display:column title="Thumbnail">
                                                        <a href="${pageContext.request.contextPath}/dokumen/view/penggunaFile/${line.id}" title="${line.namaDokumen}">
                                                            <img src="${pageContext.request.contextPath}/images/icon/microsoft/adobe.png" width="50" height="50"/>
                                                        </a>
                                                    </display:column>
                                                </c:if>
                                                <c:if test="${line.jenisKandungan eq actionBean.forXlsx or line.contentType eq actionBean.forXls}">
                                                    <display:column title="Thumbnail">
                                                        <a href="${pageContext.request.contextPath}/dokumen/view/penggunaFile/${line.id}" title="${line.namaDokumen}">
                                                            <img src="${pageContext.request.contextPath}/images/icon/microsoft/excel.png" width="50" height="50"/>
                                                        </a>
                                                    </display:column>
                                                </c:if>
                                                <c:if test="${line.jenisKandungan eq actionBean.forDocx or line.contentType eq actionBean.forDoc}">
                                                    <display:column title="Thumbnail">
                                                        <a href="${pageContext.request.contextPath}/dokumen/view/penggunaFile/${line.id}" title="${line.namaDokumen}">
                                                            <img src="${pageContext.request.contextPath}/images/icon/microsoft/word.png" width="50" height="50"/>
                                                        </a>
                                                    </display:column>
                                                </c:if>
                                                <c:if test="${line.jenisKandungan eq actionBean.forPptx or line.contentType eq actionBean.forPpt}">
                                                    <display:column title="Thumbnail">
                                                        <a href="${pageContext.request.contextPath}/dokumen/view/penggunaFile/${line.id}" title="${line.namaDokumen}">
                                                            <img src="${pageContext.request.contextPath}/images/icon/microsoft/powerpoint.png" width="50" height="50"/>
                                                        </a>
                                                    </display:column>
                                                </c:if>
                                                <c:if test="${line.jenisKandungan eq actionBean.forVideoMov and 
                                                              line.jenisKandungan eq actionBean.forVideoMkv and 
                                                              line.jenisKandungan eq actionBean.forVideoMp4 and 
                                                              line.jenisKandungan eq actionBean.forVideoAvi and 
                                                              line.jenisKandungan eq actionBean.forVideoMpeg and 
                                                              line.jenisKandungan eq actionBean.forVideoWmv and 
                                                              line.jenisKandungan eq actionBean.forVideoAsf and 
                                                              line.jenisKandungan eq actionBean.forVideoFlv and 
                                                              line.jenisKandungan eq actionBean.forVideoRmvb and 
                                                              line.jenisKandungan eq actionBean.forVideo3gp}">
                                                    <display:column title="Thumbnail">
                                                        <a href="${pageContext.request.contextPath}/dokumen/view/penggunaFile/${line.id}" title="${line.namaDokumen}">
                                                            <img src="${pageContext.request.contextPath}/images/icon/video/video.png" width="50" height="50"/>
                                                        </a>
                                                    </display:column>
                                                </c:if>
                                                <c:if test="${line.jenisKandungan ne actionBean.forPdf and
                                                              line.jenisKandungan ne actionBean.forXlsx and
                                                              line.jenisKandungan ne actionBean.forXls and
                                                              line.jenisKandungan ne actionBean.forDocx and
                                                              line.jenisKandungan ne actionBean.forDoc and
                                                              line.jenisKandungan ne actionBean.forPptx and
                                                              line.jenisKandungan ne actionBean.forPpt and 
                                                              line.jenisKandungan ne actionBean.forVideoMov and 
                                                              line.jenisKandungan ne actionBean.forVideoMkv and 
                                                              line.jenisKandungan ne actionBean.forVideoMp4 and 
                                                              line.jenisKandungan ne actionBean.forVideoAvi and 
                                                              line.jenisKandungan ne actionBean.forVideoMpeg and 
                                                              line.jenisKandungan ne actionBean.forVideoWmv and 
                                                              line.jenisKandungan ne actionBean.forVideoAsf and 
                                                              line.jenisKandungan ne actionBean.forVideoFlv and 
                                                              line.jenisKandungan ne actionBean.forVideoRmvb and 
                                                              line.jenisKandungan ne actionBean.forVideo3gp}">
                                                    <display:column title="Thumbnail">
                                                        <a href="${pageContext.request.contextPath}/dokumen/view/penggunaFile/${line.id}" rel="prettyPhoto[gallery1]" 
                                                           title="${line.title}">
                                                            <img src="${pageContext.request.contextPath}/dokumen/view/penggunaFile/${line.id}" width="30" height="30"/>
                                                        </a>
                                                    </display:column>
                                                </c:if>
                                                <display:column title="delete">
                                                    <a href="adminUser?deleteFile&id=${line.id}" onclick="return confirm('Adakah anda pasti untuk hapus?');">
                                                        <img src="${pageContext.request.contextPath}/images/icon/gnome_edit_delete.png" alt="delete" width="20" height="20"/>
                                                    </a>
                                                </display:column>
                                            </display:table>  

                                        </div>
                                    </div>
                                </div>
                            </div>  
                        </c:if>
                    </div>
                </div>
            </s:form>
        </div>


    </body>
</html>
