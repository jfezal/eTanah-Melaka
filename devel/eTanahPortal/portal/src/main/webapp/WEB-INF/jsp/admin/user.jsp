<%-- 
    Document   : user
    Created on : Apr 28, 2013, 7:11:47 PM
    Author     : wan.fairul
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.: Admin : Add User :.</title>    
        <script type="text/javascript">


            function deleteData(id, nama) {
                var msg = 'Adakah anda pasti untuk hapus ' + nama.toUpperCase() + ' dari senarai?';
                $('#idHapus').val(msg);
                $('#idDelete').val(id);
                $('#namaDelete').val(nama);
            }

            function deleteDatabase() {
                $('#saveDelete').click();
                $('#deleteModal').modal('toggle');
            }

            function editData(id, nama, emel, sts) {
                $('#idPguna').val(id);
                $('#nama').val(nama);
                $('#emel').val(emel);
                $('#stat').val(sts);
            }

            function editDatabase() {
                var id = document.getElementById('idPguna').value;
                var nama = document.getElementById('nama').value;
                var emel = document.getElementById('emel').value;
                var status = document.getElementById('stat').value;
                $('#a').val(id);
                $('#b').val(nama);
                $('#c').val(emel);
                $('#d').val(status);
                $('#saveEdit').click();
                $('#editModal').modal('toggle');
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
                <s:form beanclass="com.theta.portal.stripes.action.UserAction" id="usr" name="form1"> 
                    <div class="welcome-text-2">
                        <br>
                        <div class="panel-group" id="accordion">
                            <div class="panel panel-default">
                                <!-- Toggle Heading -->
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse-4">
                                            <i class="fa fa-angle-up control-icon"></i>
                                            Carian Pengguna
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapse-4" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <div class="form-horizontal"><br>
                                            <div class="container">
                                                <label class="col-sm-3 control-label"><em>*</em> Pilih Kategori Pengguna :</label>
                                                <div class="col-sm-2 form-group">
                                                    <s:select name="kategori" id="categoryUser" class="form-control text-uppercase selectwidthauto" 
                                                              oninvalid="setCustomValidity('Sila Pilih Kategori Pengguna!')" oninput="setCustomValidity('')" required="true">
                                                        <s:option value="">Sila Pilih</s:option>
                                                        <s:option value="AD">Admin</s:option>
                                                        <s:option value="ST">Kakitangan</s:option>
                                                        <s:option value="VN">Pelanggan</s:option>
                                                    </s:select>
                                                </div>
                                            </div>

                                            <div class="container">
                                                <label class="col-sm-3 control-label"> ID Pengguna :</label>
                                                <div class="col-sm-4 form-group">
                                                    <s:text name="idPengguna" class="form-control " id="idUsr"/>
                                                </div>
                                            </div>

                                            <div class="container">
                                                <label class="col-sm-3 control-label"> Nama Pengguna :</label>
                                                <div class="col-sm-6 form-group">
                                                    <s:text name="nama" class="form-control " id="namaUsr"/>
                                                </div>
                                            </div>

                                            <div class="container">
                                                <label class="col-sm-3 control-label"><em>*</em> Status Pengguna :</label>
                                                <div class="col-sm-2 form-group">
                                                    <s:select name="status" id="statusUser" class="form-control text-uppercase selectwidthauto" >
                                                        <s:option value="">Sila Pilih</s:option>
                                                        <s:option value="Y">AKTIF</s:option>
                                                        <s:option value="N">TIDAK AKTIF</s:option>
                                                    </s:select>
                                                </div>
                                            </div>

                                            <div class="container">
                                                <div class="form-group">
                                                    <div class="col-sm-offset-3"> 
                                                        &nbsp;&nbsp;
                                                        <s:submit name="search" value="Cari" onclick="" class="btn btn-primary formnovalidate" id="search"/>       
                                                        &nbsp;
                                                        <s:button name=" " value="Isi Semula" onclick="clearText('usr');" class="btn btn-primary formnovalidate" />         
                                                    </div>
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
                                    <a data-toggle="collapse" data-parent="#accordion1" href="#collapse-5">
                                        <i class="fa fa-angle-up control-icon"></i>
                                        Hasil Carian Pengguna
                                    </a>
                                </h4>
                            </div>
                            <div id="collapse-5" class="panel-collapse collapse in">
                                <div class="panel-body">
                                    <div class="form-horizontal">
                                        <div class="col-sm-12">
                                            <br>
                                            <input id="a" name="idEdit"   style="display: none"/>
                                            <input id="b" name="namaEdit" style="display: none"/>
                                            <input id="c" name="emelEdit" style="display: none"/>
                                            <input id="d" name="statEdit" style="display: none"/>
                                            <display:table class="table tablecloth table-bordered" cellpadding="0" cellspacing="0" pagesize="10" 
                                                           name="${actionBean.listOfStaff}" requestURI="/adminUser" id="line" 
                                                           sort="external" size="${actionBean.__pg_total_records}" partialList="true">
                                                <c:set var="row_num" value="${actionBean.bilangan}"/>
                                                <display:column title="No.">${line_rowNum+row_num}</display:column> 
                                                <display:column title="ID Pengguna" property="idPengguna"/>
                                                <display:column title="Nama" property="nama"/>
                                                <display:column title="Email" property="emel"/>
                                                <display:column title="Status" >
                                                    ${line.aktifFl == 'Y'.charAt(0) ? 'Aktif' : 'Tidak Aktif'}
                                                </display:column>
                                                <display:column title="Action">
                                                    <div align="center">
                                                        <img alt='Click for Edit' border='0' src='${pageContext.request.contextPath}/images/icon/edit.png' 
                                                             onmouseover="this.style.cursor = 'pointer';" data-target="#editModal" data-toggle="modal" 
                                                             onclick="editData('${line.idPengguna}', '${line.nama}', '${line.emel}', '${line.aktifFl}');">&nbsp;&nbsp;
                                                        <!--img alt='Click for Delete' border='0' src='${pageContext.request.contextPath}/images/icon/gnome_edit_delete.png' 
                                                             width="20" height="20" onclick="deleteData('${line.idPengguna}', '${line.nama}')" onmouseover="this.style.cursor = 'pointer';"
                                                             data-target="#deleteModal" data-toggle="modal"-->
                                                    </div>
                                                </display:column>
                                            </display:table>
                                        </div>  

                                    </div>
                                </div>
                                <s:submit name="deletePengguna" value="Simpan" id="saveDelete" style="display:none"/>
                                <s:submit name="editPengguna" value="Simpan" id="saveEdit" style="display:none"/>
                            </div>
                        </div>
                    </div>

                    <div class="modal modal-cfrm " id="deleteModal">
                        <form data-toggle="validator" role="form">
                            <div class="modal-dialog" style="border: 1px">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title">
                                            <div>Pengesahan </div>
                                        </h4>
                                    </div>
                                    <input name="idDelete" id="idDelete" style="display: none"/>
                                    <input name="" id="namaDelete" style="display: none"/>
                                    <div class="modal-body">
                                        <textarea name="" id="idHapus" cols="70" rows="3" style="border:none; resize:none;" readonly="true"></textarea>
                                    </div>
                                    <div class="modal-footer">
                                        <s:button name=" " class="btn btn-primary" value="Ya" id="yesBtn" style="width:100px;" onclick="deleteDatabase();"/>
                                        <s:button name=" " class="btn btn-warning" data-dismiss="modal" value="Tidak" id="cancelBtn" style="width:100px;"/>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal-dialog -->
                        </form>
                    </div><!-- /.modal -->

                    <div class="modal fade modal-cfrm " id="editModal">
                        <form data-toggle="validator" role="form">
                            <div class="modal-dialog" style="border: 1px">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title">
                                            <div>Kemaskini Pengguna</div>
                                        </h4>
                                    </div>
                                    <br>
                                    <div class="container">
                                        <label class="col-md-3 control-label" style="text-align: right">ID Pengguna :</label>
                                        <div class="col-md-7 form-group">
                                            <input type="text" name="idEdit" id="idPguna" style="border:none;border-color: transparent;outline: none" size="15"/>
                                        </div>
                                    </div>
                                    <div class="container">
                                        <label class="col-md-3 control-label" style="text-align: right">Nama :</label>
                                        <div class="col-md-7 form-group">
                                            <input type="text" name="namaEdit" id="nama" class="form-control text-uppercase txtfield" data-error="Sila Masukkan Nama!" required="true" />
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="container">
                                        <label class="col-md-3 control-label" style="text-align: right">Emel :</label>
                                        <div class="col-md-7 form-group">
                                            <input type="email" name="emelEdit" id="emel" class="form-control txtfield" data-error="Sila Masukkan Alamat Emel!" required="true" />
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="container">
                                        <label class="col-md-3 control-label" style="text-align: right">Status : </label>
                                        <div class="col-md-3 form-group">
                                            <s:select name="statEdit" id="stat" class="form-control text-uppercase txtfield" data-error="Sila Pilih Status!" required="true" style="width:300px">
                                                <s:option value=''>Sila Pilih</s:option>
                                                <s:option value='Y'>Aktif</s:option>
                                                <s:option value='N'>Tidak aktif</s:option>
                                            </s:select> 
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <s:button name=" " class="btn btn-primary" value="Simpan" id="yesBtn" style="width:100px;" onclick="editDatabase()"/>
                                        <s:button name=" " class="btn btn-warning" data-dismiss="modal" value="Tutup" id="cancelBtn" style="width:100px;"/>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal-dialog -->
                        </form>
                    </div><!-- /.modal -->

                </s:form>
            </div>
        </div>

    </body>
</html>
