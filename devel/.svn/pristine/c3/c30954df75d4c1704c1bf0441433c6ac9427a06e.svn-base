
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<div class="panel-body">
    <div class="form-horizontal">
        <div class="form-group">
            <div class="col-sm-offset-6 col-sm-12">
                <c:if test="${ actionBean.pTeknikal}">
                    <s:submit name="tolakAduan" value="Tolak Aduan " class="btn btn-danger formnovalidate" formnovalidate="true" style="cursor: pointer;"/>&nbsp;
                </c:if>
                    <c:if test="${actionBean.userSelesai}">
                    <s:submit name="simpan" value="Simpan" class="btn btn-primary formnovalidate" formnovalidate="true">Simpan</s:submit>  &nbsp;</c:if>
                   
                <c:if test="${actionBean.agih}">
                    <s:submit name="simpan" value="Agih" class="btn btn-primary formnovalidate" formnovalidate="true">Agih</s:submit>  &nbsp;</c:if>
                    <c:if test="${actionBean.user || actionBean.vendor || actionBean.pTeknikal }">
                    <s:submit name="simpan" value="Simpan" class="btn btn-primary formnovalidate" formnovalidate="true">Simpan</s:submit>  &nbsp;</c:if>
                    <c:if test="${actionBean.user || actionBean.vendor}">
                    <s:submit name="hantar" value="Hantar" class="btn btn-primary formnovalidate" formnovalidate="true">Hantar</s:submit>  &nbsp;</c:if>
                    <c:if test="${actionBean.userSelesai  || actionBean.pTeknikal || actionBean.pTeknikalSemak}">
                    <s:submit name="selesai" value="Selesai" class="btn btn-success formnovalidate" formnovalidate="true">Selesai</s:submit>  &nbsp;</c:if>
                    <c:if test="${actionBean.pTeknikal}">
                    <s:button name="agih" value="Agih Kontraktor " class="btn btn-info formnovalidate" formnovalidate="true" data-target="#agihKontraktorModal" data-toggle="modal" style="cursor: pointer;"/>&nbsp;
                </c:if>
                <c:if test="${actionBean.vendor }">
                    <s:button name="psKontraktor" value="Pulang Semula " class="btn btn-warning formnovalidate" formnovalidate="true" data-target="#pulangSemulaKontraktorModal" data-toggle="modal" style="cursor: pointer;"/>&nbsp;
                </c:if>
                <c:if test="${ actionBean.pTeknikal}">
                    <s:button name="psTeknikal" value="Pulang Semula " class="btn btn-warning formnovalidate" formnovalidate="true" data-target="#pulangSemulaTeknikalModal" data-toggle="modal" style="cursor: pointer;"/>
                </c:if> 
                <c:if test="${actionBean.agih}">
                    <s:button name="psAgih" value="Pulang Semula " class="btn btn-warning formnovalidate" formnovalidate="true" data-target="#pulangSemulAgihModal" data-toggle="modal" style="cursor: pointer;"/>
                </c:if> 
                <c:if test="${actionBean.pTeknikalSemak}">
                    <s:button name="psKontraktorSemak" value="Pulang Semula " class="btn btn-warning formnovalidate" formnovalidate="true" data-target="#pulangSemulaKontraktorSemakModal" data-toggle="modal" style="cursor: pointer;"/>
                </c:if>
                     
                <c:if test="${actionBean.userSelesai}">
                    <s:button name="psUserSelesai" value="Pulang Semula " class="btn btn-warning formnovalidate" formnovalidate="true" data-target="#pulangSemulapsUserSelesaiModal" data-toggle="modal" style="cursor: pointer;"/>
                </c:if>

            </div>
        </div>
    </div>
</div>


<div class="modal bd-example-modal-lg" id="agihKontraktorModal" role="dialog"><!-- /.modal Selepas Penjadualan -->
    <form role="form">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">
                        <div>Agihan Kontraktor</div>
                    </h4>
                </div>
                <div class="modal-body">
                    <s:text name="" id="row" style="display:none"/>
                    <div class="container">
                        <label class="col-md-2 control-label" for="textinput">Catatan :</label>  
                        <div class="col-md-3 form-group">
                            <s:textarea name="catatanKeKontraktor"/>
                        </div>

                    </div>
                    <div class="container">
                        <label class="col-md-2 control-label" for="textinput">Kontraktor :</label>  
                        <div class="col-md-3 form-group">
                            <s:select name="vendorId" style="width:300px" class="form-control" data-error="Sila Pilih Pegawai" required="true">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.listAgihanKontraktor}" label="name" value="userId"/>
                            </s:select>
                        </div>

                    </div>
                </div>
                <div class="modal-footer">
                    <%--<s:button name=" " class="btn btn-primary" value="Agih" id="agih" />--%>
                    <%--                               <s:submit name="save" class="btn btn-primary" value="Tambah" id="addDetails1" /> --%>
                    <s:submit name="agihKontraktor" class="btn btn-primary" value="Agih" id="testDestination" />
                    <s:button name=" " class="btn btn-warning" data-dismiss="modal" value="Tutup" id="tutupDetails" style="width:100px;"/>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </form>
</div>

<div class="modal bd-example-modal-lg" id="pulangSemulaKontraktorModal" role="dialog"><!-- /.modal Selepas Penjadualan -->
    <form role="form">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">
                        <div>Pulang Semula</div>
                    </h4>
                </div>
                <div class="modal-body">
                    <s:text name="" id="row" style="display:none"/>
                    <div class="container">
                        <label class="col-md-2 control-label" for="textinput">Catatan :</label>  
                        <div class="col-md-3 form-group">
                            <s:textarea name="noteKontraktor" data-error="Sila Masukkan Catatan"  id="notaKontraktor"/>
                        </div>

                    </div>
                </div>
                <div class="modal-footer">
                    <s:button name=" " class="btn btn-primary" value="Hantar"  onClick="pulangsemulaKontraktor();"/>
                    <%--                               <s:submit name="save" class="btn btn-primary" value="Tambah" id="addDetails1" /> --%>
                    <s:submit name="pulangSemulaKontraktor" class="btn btn-primary" value="Hantar" id="pulangSemulaKontraktor" style="display:none;" />
                    <s:button name=" " class="btn btn-warning" data-dismiss="modal" value="Tutup" id="tutupDetails" style="width:100px;"/>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </form>
</div>
<div class="modal bd-example-modal-lg" id="pulangSemulaKontraktorSemakModal" role="dialog"><!-- /.modal Selepas Penjadualan -->
    <form role="form">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">
                        <div>Pulang Semula</div>
                    </h4>
                </div>
                <div class="modal-body">
                    <s:text name="" id="row" style="display:none"/>
                    <div class="container">
                        <label class="col-md-2 control-label" for="textinput">Catatan :</label>  
                        <div class="col-md-3 form-group">
                            <s:textarea name="noteKontraktor" data-error="Sila Masukkan Catatan"  id="noteKontraktor"/>
                        </div>

                    </div>
                </div>
                <div class="modal-footer">
                    <s:button name=" " class="btn btn-primary" value="Hantar"  onClick="pulangsemulaKontraktorSemak();"/>
                    <%--                               <s:submit name="save" class="btn btn-primary" value="Tambah" id="addDetails1" /> --%>
                    <s:submit name="pulangSemulaKontraktorSemak" class="btn btn-primary" value="Hantar" id="pulangSemulaKontraktorSemak" style="display:none;" />
                    <s:button name=" " class="btn btn-warning" data-dismiss="modal" value="Tutup" id="tutupDetails" style="width:100px;"/>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </form>
</div>

<div class="modal bd-example-modal-lg" id="pulangSemulaTeknikalModal" role="dialog"><!-- /.modal Selepas Penjadualan -->
    <form role="form">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">
                        <div>Pulang Semula</div>
                    </h4>
                </div>
                <div class="modal-body">
                    <s:text name="" id="row" style="display:none"/>
                    <div class="container">
                        <label class="col-md-2 control-label" for="textinput">Catatan  :</label>  
                        <div class="col-md-3 form-group">
                            <s:textarea name="noteTeknikal" data-error="Sila Masukkan Catatan"  id="notaTeknikal"/>
                        </div>

                    </div>
                </div>
                <div class="modal-footer">
                    <s:button name=" " class="btn btn-primary" value="Hantar" id="pulangSemulaTeknikal" onClick="pulangsemulaTeknikal();"/>
                    <%--                               <s:submit name="save" class="btn btn-primary" value="Tambah" id="addDetails1" /> --%>
                    <%--<s:submit name="pulangSemulaTeknikal" class="btn btn-primary" value="Tambah" id="testDestination" style="display:none;" />--%>
                    <s:button name=" " class="btn btn-warning" data-dismiss="modal" value="Tutup" id="tutupDetails" style="width:100px;"/>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </form>
</div>
<div class="modal bd-example-modal-lg" id="pulangSemulAgihModal" role="dialog"><!-- /.modal Selepas Penjadualan -->
    <form role="form">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">
                        <div>Pulang Semula</div>
                    </h4>
                </div>
                <div class="modal-body">
                    <s:text name="" id="row" style="display:none"/>
                    <div class="container">
                        <label class="col-md-2 control-label" for="textinput">Catatan  :</label>  
                        <div class="col-md-3 form-group">
                            <s:textarea name="notaAgih" data-error="Sila Masukkan Catatan"  id="notaAgih"/>
                        </div>

                    </div>
                </div>
                <div class="modal-footer">
                    <s:button name=" " class="btn btn-primary" value="Hantar A" id="pulangSemulaAgih" onClick="pulangsemulaAgih();"/>
                    <%--                               <s:submit name="save" class="btn btn-primary" value="Tambah" id="addDetails1" /> --%>
                    <%--<s:submit name="pulangSemulaTeknikal" class="btn btn-primary" value="Tambah" id="testDestination" style="display:none;" />--%>
                    <s:button name=" " class="btn btn-warning" data-dismiss="modal" value="Tutup" id="tutupDetails" style="width:100px;"/>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </form>
</div>
<div class="modal bd-example-modal-lg" id="pulangSemulapsUserSelesaiModal" role="dialog"><!-- /.modal Selepas Penjadualan -->
    <form role="form">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">
                        <div>Pulang Semula</div>
                    </h4>
                </div>
                <div class="modal-body">
                    <s:text name="" id="row" style="display:none"/>
                    <div class="container">
                        <label class="col-md-2 control-label" for="textinput">Catatan  :</label>  
                        <div class="col-md-3 form-group">
                            <s:textarea name="notaUser" data-error="Sila Masukkan Catatan"  id="notaUser"/>
                        </div>

                    </div>
                </div>
                <div class="modal-footer">
                    <s:button name=" " class="btn btn-primary" value="Hantar" id="pulangSemulapsUserSelesai" onClick="pulangsemulapsUserSelesai();"/>
                    <%--                               <s:submit name="save" class="btn btn-primary" value="Tambah" id="addDetails1" /> --%>
                    <%--<s:submit name="pulangSemulaTeknikal" class="btn btn-primary" value="Tambah" id="testDestination" style="display:none;" />--%>
                    <s:button name=" " class="btn btn-warning" data-dismiss="modal" value="Tutup" id="tutupDetails" style="width:100px;"/>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </form>
</div>          
