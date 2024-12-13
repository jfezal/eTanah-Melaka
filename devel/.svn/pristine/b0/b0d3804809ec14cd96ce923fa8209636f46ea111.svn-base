<%-- 
    Document   : user_matrix
    Created on : May 23, 2016, 11:34:48 AM
    Author     : haqqiem
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.: Admin : User Matrix :.</title>    
        <script type="text/javascript">
            $(document).ready(function () {
                $('#line').DataTable();
            });

            function selectAll(a) {
                for (i = 0; i < 100; i++) {
                    var c = document.getElementById("pengguna" + i);
                    if (c == null)
                        break;
                    c.checked = a.checked;
                }
            }

            function checkingData(id, row) {
                var c = document.getElementById("pengguna" + row);
                var url = "${pageContext.request.contextPath}/userMatrix?checkingId&id=" + id;
                $.get(url,
                        function (data) {
                            if (data == '1') {
                                alert("ID Pengguna " + id + " telah didaftarkan sebagai Pengesah.");
//                                c.checked = row.checked;
                            }
                        }, 'html');
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
                <ol class="breadcrumb">
                    <li><a href="welcome">Laman Utama</a></li>
                    <li class="active">Daftar Peranan Pengguna</li>
                </ol>
                <s:messages/>
                <s:errors/>
                <s:form beanclass="com.theta.portal.stripes.action.UserMatrixActionBean" name="form1"> 
                    <div class="welcome-text-2">
                        <div class="panel panel-default">
                            <!-- Toggle Heading -->
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse-2">
                                        <i class="fa fa-angle-up control-icon"></i>
                                        Senarai Pengguna Aktif
                                    </a>
                                </h4>
                            </div>
                            <div id="collapse-2" class="panel-collapse collapse in">
                                <div class="panel-body">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <div class="table-responsive">
                                                <display:table class="table table-hover table table-striped table-bordered" name="${actionBean.senaraiPengguna}" size="10" requestURI="/userMatrix" id="line">
                                                    <display:column title="No." >${line_rowNum}</display:column>
                                                    <display:column title="ID Pengguna" property="ptPengguna.idPengguna"/>
                                                    <display:column title="Nama" property="ptPengguna.nama"/>
                                                    <display:column title="Jawatan" property="ptPengguna.jawatan"/>
                                                    <display:column title="Pengesah" >
                                                        ${line.pengesahFl == 1 ? 'Ya' : 'Tidak'}
                                                    </display:column>
                                                    <display:column title="Kemaskini Pengesah <br><div align='center'><input type='checkbox' id='semua' name='semua' onclick='javascript:selectAll(this); ' /></div>" style="text-align:center;width:10%" >
                                                        <stripes:checkbox name="chbxPengguna[${line_rowNum - 1}]" value="${line.ptPengguna.idPengguna}" id="pengguna${line_rowNum - 1}" onclick="checkingData(this.value,${line_rowNum - 1})"/>
                                                    </display:column>
                                                </display:table>
                                            </div>
                                        </div>  

                                    </div>
                                </div>
                            </div>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <div class="col-sm-offset-10 col-sm-10">
                                        <s:submit name="save" value="Simpan" class="btn btn-primary" tabindex="4"/>&nbsp;
                                        <a href="${pageContext.request.contextPath}/welcome" class="btn btn-primary">Keluar</a>
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

