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
        <script src="${pageContext.request.contextPath}/js/util.js" type="text/javascript"></script>       
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/combodate.js"></script>
        <title>.: eTanah : Kemaskini Maklumat Pengguna :.</title> 
        <script>
            $(document).ready(function () {

                if ($('#kodNegeri').val())
                {
                    getDaerah($('#kodNegeri').val(), '${actionBean.pengguna.daerah}');

                }

                if ($('#kodNegeriSurat').val())
                {
                    getSuratDaerah($('#kodNegeriSurat').val(), '${actionBean.pengguna.kodDaerahSurat}');

                }

                if ($('#negeriMjkn').val())
                {
                    getMajikanDaerah($('#negeriMjkn').val(), '${actionBean.pengguna.daerahMjkn}');

                }
                $(".nav-tabs a").click(function () {
                    $(this).tab('show');
                });

                var tab = $('#t').val();
                if (tab)
                {
                    $('.nav-tabs a[href="#' + tab + '"]').tab('show');
                }

                var flg = $('#kodKategoriPengguna').val();
                if (flg == 'ST') {
                    $('#infoPengguna :input').attr('disabled', false);
                    $('#muatNaik :input').attr('disabled', false);
                }





                $('#datePicker')
                        .datepicker({
                            format: "dd/mm/yyyy",
                            changeMonth: true,
                            changeYear: true
                        });


            });


            function getDaerah(val, _Val) {
                var data = {};
                var daerah = $('#daerah');
                if (daerah.length > 1)
                    daerah.empty();

                var url = "${pageContext.request.contextPath}/auto_complete?viewDaerah";
                data['kod'] = val;

                getInfoWAjax(url, data).done(function (data) {
                    var list = "";
                    if (data.length > 1)
                        list = "<option value=''>Sila Pilih</option>";
                    $.each(data, function () {
                        var selected = "";
                        if (this.id == _Val)
                            selected = "selected";
                        list += "<option value ='" + this.id + "' " + selected + ">"
                                + this.perihal + "</option>";

                    });
                    console.log(list);
                    daerah.html(list);

                });
            }

            function getSuratDaerah(val, _Val) {
                var data = {};
                var kodDaerahSurat = $('#kodDaerahSurat');
                if (kodDaerahSurat.length > 1)
                    kodDaerahSurat.empty();

                var url = "${pageContext.request.contextPath}/auto_complete?viewDaerah";
                data['kod'] = val;


                getInfoWAjax(url, data).done(function (data) {
                    var list = "";
                    if (data.length > 1)
                        list = "<option value=''>Sila Pilih</option>";
                    $.each(data, function () {
                        var selected = "";
                        if (this.id == _Val)
                            selected = "selected";
                        list += "<option value ='" + this.id + "' " + selected + ">"
                                + this.perihal + "</option>";

                    });
                    console.log(list);
                    kodDaerahSurat.html(list);

                });
            }


            function getMajikanDaerah(val, _Val) {
                var data = {};
                var daerahMjkn = $('#daerahMjkn');
                if (daerahMjkn.length > 1)
                    daerahMjkn.empty();

                var url = "${pageContext.request.contextPath}/auto_complete?viewDaerah";
                data['kod'] = val;


                getInfoWAjax(url, data).done(function (data) {
                    var list = "";
                    if (data.length > 1)
                        list = "<option value=''>Sila Pilih</option>";
                    $.each(data, function () {
                        var selected = "";
                        if (this.id == _Val)
                            selected = "selected";
                        list += "<option value ='" + this.id + "' " + selected + ">"
                                + this.perihal + "</option>";

                    });
                    console.log(list);
                    daerahMjkn.html(list);

                });
            }


            function copyAddress()
            {
                var alamat1 = $('#alamat1').val();
                var alamat2 = $('#alamat2').val();
                var alamat3 = $('#alamat3').val();
                var poskod = $('#poskod').val();
                var daerah = $('#daerah').val();
                var kodNegeri = $('#kodNegeri').val();

                if (alamat1)
                {
                    $('#alamatSurat1').val(alamat1);
                }
                if (alamat2)
                {
                    $('#alamatSurat2').val(alamat2);
                }
                if (alamat3)
                {
                    $('#alamatSurat3').val(alamat3);
                }
                if (poskod)
                {
                    $('#poskodSurat').val(poskod);
                }
                if (kodNegeri)
                {
                    $('#kodNegeriSurat').val(kodNegeri);
                    getSuratDaerah($('#kodNegeriSurat').val(), $('#daerah').val());
                }


            }

        </script>

    </head>
    <body>

        <div class="row">
            <div class="col-md-12">
                <s:messages/>
                <s:errors/>
                <s:form beanclass="com.theta.portal.stripes.action.UserAction" data-toggle="validator"> 
                    <s:useActionBean beanclass="com.theta.portal.stripes.util.ListUtil" var="list"/>
                    <s:hidden name="pengguna.idPengguna"/>
                    <s:hidden name="pengguna.kodKategoriPengguna" id="kodKategoriPengguna"/>
                    <s:hidden name="t" id="t" />
                    <div class="welcome-text-2">

                        <ul class="nav nav-tabs">
                            <li><a href="#infoPengguna">Maklumat Pengguna</a></li>
                            <li><a href="#muatNaik">Muat Naik Dokumen</a></li>
                        </ul>

                        <div class="tab-content">
                            <div id="infoPengguna" class="tab-pane fade in active">
                                <div class="panel panel-default">
                                    <!-- Toggle Heading -->
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse-1">
                                                <i class="fa fa-angle-up control-icon"></i>
                                                Maklumat Peribadi
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapse-1" class="panel-collapse collapse in">
                                        <div class="panel-body">
                                            <div class="form-horizontal">
                                                <div class="container">
                                                    <label class="col-sm-2 control-label">ID Pengguna : </label>
                                                    <div class="col-sm-10 form-group">
                                                        <p class="form-control-static">${actionBean.pengguna.idPengguna}</p>
                                                    </div>
                                                    <label class="col-sm-2 control-label">Nama : </label>
                                                    <div class="col-sm-10 form-group">
                                                        <p class="form-control-static">${actionBean.pengguna.nama}</label>
                                                    </div>
                                                    <label class="col-sm-2 control-label">Emel : </label>
                                                    <div class="col-sm-10 form-group">
                                                        <p class="form-control-static">${actionBean.pengguna.emel}</label>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">Tarikh Lahir :</label>  
                                                    <div class="col-md-3 input-group input-append date form-group">
                                                        <s:text name="pengguna.tarikhLahir" class="form-control datePicker" id="tarikhLahir" formatPattern="dd/MM/yyyy"/> 
                                                        <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                                                        <div class="help-block with-errors"></div>
                                                    </div>   
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">Jantina :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:select name="pengguna.kodJantina" style="width:300px" class="form-control" data-error="Sila Pilih Jantina!" required="true">
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${list.kodJantina}" label="perihal" value="id"/>
                                                        </s:select>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">Bangsa :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:select name="pengguna.kodBangsa" id="kodBangsa" style="width:300px" class="form-control" data-error="Sila Pilih Bangsa!" required="true">
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${list.kodBangsa}" label="perihal" value="id"/>
                                                        </s:select>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                    <label class="col-md-2 control-label" for="textinput">Agama :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:select name="pengguna.kodAgama" style="width:300px" class="form-control" data-error="Sila Pilih Agama!" required="true">
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${list.kodAgama}" label="perihal" value="id"/>
                                                        </s:select>
                                                        <div class="help-block with-errors"></div></div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-sm-2 control-label">Negeri Kelahiran :</label>
                                                    <div class="col-sm-6 form-group">
                                                        <s:select name="pengguna.lahirKodNegeri" style="width:300px" class="form-control" data-error="Sila Pilih Negeri Kelahiran!" required="true">
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${list.kodNegeri}" label="perihal" value="id"/>
                                                        </s:select>
                                                        <div class="help-block with-errors"></div></div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-sm-2 control-label">Status Perkahwinan :</label>
                                                    <div class="col-sm-6 form-group">
                                                        <s:select name="pengguna.kodTarafPerkahwinan" style="width:300px" class="form-control" data-error="Sila Pilih Taraf Perkahwninan!" required="true">
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${list.tarafKahwin}" label="perihal" value="id"/>
                                                        </s:select>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-sm-2 control-label">Alamat :</label>
                                                    <div class="col-sm-6 form-group">
                                                        <s:text name="pengguna.alamat1" id="alamat1" class="form-control text-uppercase" data-error="Sila Masukkan Alamat!" required="true"/> 
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-sm-2 control-label">&nbsp;</label>
                                                    <div class="col-sm-6 form-group">
                                                        <s:text name="pengguna.alamat2" id="alamat2" class="form-control text-uppercase"/> 
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-sm-2 control-label">&nbsp;</label>
                                                    <div class="col-sm-6 form-group">
                                                        <s:text name="pengguna.alamat3" id="alamat3" class="form-control text-uppercase"/>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">Poskod :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:text name="pengguna.poskod" id="poskod" maxlength="5" class="form-control" data-error="Sila Masukkan Poskod!" required="true" onkeyup="validateNumber(this,this.value);"/>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                    <label class="col-md-2 control-label" for="textinput">Negeri :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:select name="pengguna.kodNegeri" id="kodNegeri" style="width:300px" onchange="getDaerah(this.value,'');" class="form-control" data-error="Sila Pilih Negeri!" required="true">
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${list.kodNegeri}" label="perihal" value="id"/>
                                                        </s:select>
                                                        <div class="help-block with-errors">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">Daerah :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:select name="pengguna.daerah" style="width:300px" id="daerah" class="form-control" data-error="Sila Pilih Daerah!" required="true">
                                                        </s:select>
                                                        <div class="help-block with-errors">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-sm-2 control-label">Alamat Surat Menyurat:</label>
                                                    <div class="col-sm-8 form-group">
                                                        <input type="checkbox" onclick="copyAddress();">&nbsp;&nbsp;<label style="color: black">Sama Seperti Alamat Tetap</label>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-sm-2 control-label">&nbsp;</label>
                                                    <div class="col-sm-6 form-group">
                                                        <s:text name="pengguna.alamatSurat1" id="alamatSurat1" class="form-control" data-error="Sila Masukkan Alamat Surat Menyurat!" required="true"/>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-sm-2 control-label">&nbsp;</label>
                                                    <div class="col-sm-6 form-group">
                                                        <s:text name="pengguna.alamatSurat2" id="alamatSurat2" class="form-control"/>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-sm-2 control-label">&nbsp;</label>
                                                    <div class="col-sm-6 form-group">
                                                        <s:text name="pengguna.alamatSurat3" id="alamatSurat3" class="form-control"/>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">Poskod :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:text name="pengguna.poskodSurat" id="poskodSurat" maxlength="5" class="form-control" data-error="Sila Masukkan Poskod Surat Menyurat!" required="true" onkeyup="validateNumber(this,this.value);"/>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                    <label class="col-md-2 control-label" for="textinput">Negeri :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:select name="pengguna.kodNegeriSurat" id="kodNegeriSurat" onchange="getSuratDaerah(this.value,'');" style="width:300px" class="form-control" data-error="Sila Pilih Negeri!" required="true">
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${list.kodNegeri}" label="perihal" value="id"/>
                                                        </s:select>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">Daerah :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:select name="pengguna.kodDaerahSurat" style="width:300px" id="kodDaerahSurat" class="form-control" data-error="Sila Pilih Daerah Surat Menyurat!" required="true">
                                                        </s:select>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">No Telefon :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:text name="pengguna.telefon" class="form-control" onkeyup="validateNumber(this,this.value);"/>
                                                    </div>
                                                    <label class="col-md-2 control-label" for="textinput">No Fax :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:text name="pengguna.faks" class="form-control" onkeyup="validateNumber(this,this.value);"/>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">No Telefon Bimbit :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:text name="pengguna.telBimbit" class="form-control" onkeyup="validateNumber(this,this.value);"/>
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
                                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse-2">
                                                <i class="fa fa-angle-up control-icon"></i>
                                                Maklumat Pekerjaan
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapse-2" class="panel-collapse collapse in">
                                        <div class="panel-body">
                                            <div class="form-horizontal">
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">Jawatan :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:text name="pengguna.jawatan" class="form-control"/>
                                                    </div>
                                                    <label class="col-md-2 control-label" for="textinput">Gred Jawatan :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:text name="pengguna.gredJawatan" class="form-control"/>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-sm-2 control-label">Nama Majikan :</label>
                                                    <div class="col-sm-6 form-group">
                                                        <s:text name="pengguna.namaSyrkt" class="form-control" data-error="Sila Masukkan Nama Majikan!" required="true"/>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">Jabatan :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:select name="pengguna.samJabatanPkid" style="width:300px" class="form-control" data-error="Sila Pilih Jabatan!" required="true">
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${list.jabatan}" label="perihal" value="id"/>
                                                        </s:select>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">Agensi/Badan Berkanun :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:select name="pengguna.samAgensiPkid" style="width:300px" class="form-control" data-error="Sila Pilih Jabatan!" required="true">
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${list.agensi}" label="namaAgensi" value="id"/>
                                                        </s:select>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-sm-2 control-label">Alamat Tetap :</label>
                                                    <div class="col-sm-6 form-group">
                                                        <s:text name="pengguna.alamatMjkn1" class="form-control" data-error="Sila Masukkan Alamat Tetap!" required="true"/>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-sm-2 control-label">&nbsp;</label>
                                                    <div class="col-sm-6 form-group">
                                                        <s:text name="pengguna.alamatMjkn2" class="form-control"/>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-sm-2 control-label">&nbsp;</label>
                                                    <div class="col-sm-6 form-group">
                                                        <s:text name="pengguna.alamatMjkn3" class="form-control"/>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">Poskod :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:text name="pengguna.poskodMjkn" class="form-control" maxlength="5" data-error="Sila Masukkan Poskod!" required="true" onkeyup="validateNumber(this,this.value);"/>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                    <label class="col-md-2 control-label" for="textinput">Negeri :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:select name="pengguna.negeriMjkn" style="width:300px" id="negeriMjkn" onchange="getMajikanDaerah(this.value,'');" class="form-control" data-error="Sila Pilih Negeri!" required="true">
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${list.kodNegeri}" label="perihal" value="id"/>
                                                        </s:select>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">Daerah :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:select name="pengguna.daerahMjkn" style="width:300px" id="daerahMjkn" class="form-control" data-error="Sila Pilih Daerah!" required="true">
                                                        </s:select>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">No Telefon :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:text name="pengguna.telMajikan" class="form-control" onkeyup="validateNumber(this,this.value);"/>
                                                    </div>
                                                    <label class="col-md-2 control-label" for="textinput">No Fax :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:text name="pengguna.faksMajikan" class="form-control" onkeyup="validateNumber(this,this.value);"/>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">Emel :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:text name="pengguna.emelMajikan" class="form-control" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" data-error="Alamat e-mel tidak sah!" type="email"/>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">Laman Web :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:text name="pengguna.lamanWeb" class="form-control" pattern="https?://.+" title="Include http://" data-error="Laman Web tidak sah!"/>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="panel-body">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <div style="text-align: right">
                                                <div class="col-sm-offset-2 col-sm-10">
                                                    <s:submit name="updateUserProfile" value="Kemaskini" class="btn btn-primary" tabindex="1" id="addNew"/>&nbsp;
                                                    <a href="${pageContext.request.contextPath}/adminUser?updateInfo" class="btn btn-primary">Isi Semula</a>&nbsp;
                                                    <a href="${pageContext.request.contextPath}/welcome" class="btn btn-primary">Keluar</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                            </div>
                            <div id="muatNaik" class="tab-pane fade">
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
                                    <%--<c:if test="${actionBean.pengguna.kodKategoriPengguna ne 'ST'}">--%>
                                        <div class="panel-body">
                                            <div class="form-horizontal">
                                                <div class="form-group">
                                                    <div class="col-sm-offset-2 col-sm-10">
                                                        <s:submit name="saveDocuments" value="Muat Naik" class="btn btn-primary formnovalidate" formnovalidate="true" id="upload"/>            
                                                        <img src="${pageContext.request.contextPath}/images/loading_img.gif" style="display:none" id="loading-img"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    <%--</c:if>--%>
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
                                                <div class="form-horizontal" style="align-content: center">

                                                    <display:table class="table table-hover table table-striped table-bordered" name="${actionBean.listOfDocument}" style="width:80%;text-align:center;" requestURI="/saveDocuments" id="line">
                                                        <display:column title="No." style="text-align:center">${line_rowNum}</display:column>
                                                        <display:column title="File Name" style="text-align:center">${line.namaDokumen}</display:column>
                                                        <c:if test="${line.jenisKandungan eq actionBean.forPdf}">
                                                            <display:column title="Thumbnail" style="text-align:center">
                                                                <a href="${pageContext.request.contextPath}/dokumen/view/penggunaFile/${line.id}" title="${line.namaDokumen}">
                                                                    <img src="${pageContext.request.contextPath}/images/icon/microsoft/adobe.png" width="50" height="50"/>
                                                                </a>
                                                            </display:column>
                                                        </c:if>
                                                        <c:if test="${line.jenisKandungan eq actionBean.forXlsx or line.jenisKandungan eq actionBean.forXls}">
                                                            <display:column title="Thumbnail" style="text-align:center">
                                                                <a href="${pageContext.request.contextPath}/dokumen/view/penggunaFile/${line.id}" title="${line.namaDokumen}">
                                                                    <img src="${pageContext.request.contextPath}/images/icon/microsoft/excel.png" width="50" height="50"/>
                                                                </a>
                                                            </display:column>
                                                        </c:if>
                                                        <c:if test="${line.jenisKandungan eq actionBean.forDocx or line.jenisKandungan eq actionBean.forDoc}">
                                                            <display:column title="Thumbnail" style="text-align:center">
                                                                <a href="${pageContext.request.contextPath}/dokumen/view/penggunaFile/${line.id}" title="${line.namaDokumen}">
                                                                    <img src="${pageContext.request.contextPath}/images/icon/microsoft/word.png" width="50" height="50"/>
                                                                </a>
                                                            </display:column>
                                                        </c:if>
                                                        <c:if test="${line.jenisKandungan eq actionBean.forPptx or line.jenisKandungan eq actionBean.forPpt}">
                                                            <display:column title="Thumbnail" style="text-align:center">
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
                                                            <display:column title="Thumbnail" style="text-align:center">
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
                                                            <display:column title="Thumbnail" style="text-align:center">
                                                                <a href="${pageContext.request.contextPath}/dokumen/view/penggunaFile/${line.id}" rel="prettyPhoto[gallery1]" 
                                                                   title="${line.namaDokumen}">
                                                                    <img src="${pageContext.request.contextPath}/dokumen/view/penggunaFile/${line.id}" width="30" height="30"/>
                                                                </a>
                                                            </display:column>
                                                        </c:if>
                                                        <display:column title="Hapus" style="text-align:center">
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
                    </div>

                </s:form>
            </div>


    </body>
</html>
