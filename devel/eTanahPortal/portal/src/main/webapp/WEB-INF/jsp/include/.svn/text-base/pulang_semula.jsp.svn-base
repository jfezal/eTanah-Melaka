
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<div class="panel panel-default">
                            <!-- Toggle Heading -->
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse-13">
                                        <i class="fa fa-angle-up control-icon"></i>
                                        Maklumat Aduan
                                    </a>
                                </h4>
                            </div>
                            <div id="collapse-13" class="panel-collapse collapse in">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="panel-body">
                                            <div class="form-horizontal">
                                                <br>
                                                <table class="table table-hover table table-striped table-bordered">
                                                    <thead>
                                                        <tr>
                                                             <td>sdss</td><td>dsds</td>
                                                        </tr>
                                                    </thead>
                                                    
                                                </table>
                                                <display:table class="table table-hover table table-striped table-bordered" name="${actionBean.listTeknikal}" style="width:90;" requestURI="/saveDocuments" id="line">
                                                    <display:column title="No.">${line_rowNum}</display:column>
                                                    <display:column title="Maklumat Pegawai Teknikal"><div class="col-sm-12">
                                                            <div class="row">
                                                                <div class="col-sm-2 "><span class="pull-left" ><p class="font-weight-bold">Nama</p></span></div><div class="col-sm-10"><span class="pull-left">${line.namaPegTeknikal}</span></div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-sm-2 "><span class="pull-left" ><p class="font-weight-bold">Unit</p></span></div><div class="col-sm-10"><span class="pull-left">${line.unit}</span></div>
                                                            </div>
                                                        </div>
                                                    </display:column>

                                                    <display:column title="Keterangan">
                                                        ${line.catatanPegawaiTeknikal}
                                                    </display:column>

                                                    <display:column title="Dokumen">

                                                        <c:set value="0" var="count"/>
                                                        <c:forEach items=" ${line.listOfDocument}" var="i" >
                                                            ${line.listOfDocument[count].fileName}

                                                        </c:forEach>
                                                        <c:set value="${count +1}" var="count"/>
                                                    </display:column>
                                                </display:table>  
                                            </div>
                                        </div>

                                    </div>

                                </div>
                            </div>



                        </div>