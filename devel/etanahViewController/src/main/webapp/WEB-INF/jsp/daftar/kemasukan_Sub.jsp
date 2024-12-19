<%--
    Document   : kemasukan_hakmilik_pihak
    Created on : 15-Oct-2009, 03:49:43
    Author     : khairil
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head><title>Carian Pihak</title>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
        <%-- <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>--%>
        <%--  <script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquery.numeric.pack.js"></script>--%>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquery.validate.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                maximizeWindow();
                $(".wideselect")
                        .mouseover(function () {
                            $(this).data("origWidth", $(this).css("width")).css("width", "auto");
                        })
                        .mouseout(function () {
                            $(this).css("width", $(this).data("origWidth"));
                        });
                $('#Cari').click(function () {
                    return validNoKp();
                });
                $("#jenisPihak").val("PM");
                $("#jenisPengenalan").change(function () {
                    dodacheck($("#noPengenalan").val());
                });
                $('#noPengenalan').keyup(function () {
                    dodacheck($('#noPengenalan').val());
                });
                $('#suratPoskod').keyup(function () {
                    validNumber($('#suratPoskod').val(), 'suratPoskod');
                });
                $('#poskod').keyup(function () {
                    validNumber($('#poskod').val(), 'poskod');
                });
                $('#cawanganPoskod').keyup(function () {
                    validNumber($('#cawanganPoskod').val(), 'cawanganPoskod');
                });
                $('#suratCawanganPoskod').keyup(function () {
                    validNumber($('#suratCawanganPoskod').val(), 'suratCawanganPoskod');
                });

                var ic = $('#ic').val(); // auto get ic no. for 'k/p baru'
                if (ic == 'B') {
                    var noIC = $('#noIC').val();
                    if (noIC != null) {
                        var jantina = noIC.substring(8, 12); // get gender from ic no.
                        if (jantina % 2 == 0) {
                            $('#jantina').val('2');
                        } else {
                            $('#jantina').val('1');
                        }
                    }
                }

            });
            <%--function checkPemohonUtkRizab(f){
            var luasAmbil = f;
            var idHakmilik = '${actionBean.idHakmilik}';
            $.post('${pageContext.request.contextPath}/pihakBerkepentingan?checkPemohon&idHakmilik='+idHakmilik+'&luasAmbil='+luasAmbil,
            function(data){
                if(data != '0'){
                    //alert('Pelan untuk no lot '+ noLot +' tiada');
                    $("#luas").val(data);

                }
            }, 'html');--%>

            function validNumber(val, id) {
                var strLength = val.length;
                var lchar = val.charAt((strLength) - 1);
                if (isNaN(lchar)) {
                    var tst = val.substring(0, (strLength) - 1);
                    $('#' + id).val(tst);
                }
            }
            function dodacheck(val) {
                //var mikExp = /[1\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\1\|]/;
                var v = $('#jenisPengenalan').val();
                if (v == 'B') {
                    //var strPass = val;
                    //var strLength = strPass.length;
                    $('#noPengenalan').attr("maxlength", "12");
                    //if(strLength > 12) {
                    // var tst = val.substring(0, (strLength) - 1);
                    // $('#noPengenalan').val(tst);
                    //}
                    validNumber(val, 'noPengenalan');
                } else {
                    $('#noPengenalan').attr("maxlength", "50");
                }
            }

            function validNoKp() {
                if ($('#jenisPengenalan').val() == '') {
                    alert('Sila Pilih Jenis Pengenalan');
                    return false;
                } else if ($('#jenisPengenalan').val() == '0') {
                    $('#kp').val('-');
                    $('#kp').hide();
                } else if ($('#jenisPengenalan').val() == 'X') {
                    $('#kp').val('-');
                    $('#kp').hide();
                } else if ($('#jenisPengenalan').val() == 'B') {
                    if (isNaN($('#noPengenalan').val()) || $('#noPengenalan').val().length != 12 || $('#noPengenalan').val() == '') {
                        alert('Format No Pengenalan Baru Tidak Sah');
                        return false;
                    }
                } else {
                    if ($('#noPengenalan').val() == '') {
                        alert('Sila Masukkan No Pengenalan');
                        return false;
                    }
                }

            }
            function copyAdd() {
                if ($('input[name=add1]').is(':checked')) {
                    $('#suratAlamat1').val($('#alamat1').val());
                    $('#suratAlamat2').val($('#alamat2').val());
                    $('#suratAlamat3').val($('#alamat3').val());
                    $('#suratAlamat4').val($('#alamat4').val());
                    $('#suratPoskod').val($('#poskod').val());
                    $('#suratNegeri').val($('#negeri').val());
                } else {
                    $('#suratAlamat1').val('');
                    $('#suratAlamat2').val('');
                    $('#suratAlamat3').val('');
                    $('#suratAlamat4').val('');
                    $('#suratPoskod').val('');
                    $('#suratNegeri').val('');
                }
            }

            function copyAddCaw() {
                if ($('input[name=add2]').is(':checked')) {
                    $('#suratCawanganAlamat1').val($('#cawanganAlamat1').val());
                    $('#suratCawanganAlamat2').val($('#cawanganAlamat2').val());
                    $('#suratCawanganAlamat3').val($('#cawanganAlamat3').val());
                    $('#suratCawanganAlamat4').val($('#cawanganAlamat4').val());
                    $('#suratCawanganPoskod').val($('#cawanganPoskod').val());
                    $('#suratCawanganNegeri').val($('#cawanganNegeri').val());
                } else {
                    $('#suratCawanganAlamat1').val('');
                    $('#suratCawanganAlamat2').val('');
                    $('#suratCawanganAlamat3').val('');
                    $('#suratCawanganAlamat4').val('');
                    $('#suratCawanganPoskod').val('');
                    $('#suratCawanganNegeri').val('');
                }
            }
            function checkPoskod(id) {
                //                if ($('#' + id).val().length != 5) {
                //                  alert('Poskod tidak sah');
                //                  return false;
                //                } else {
                //                  return true;
                //                }
                return true;
            }
            function save(event, f) {
                var check1;
                //var check2;
                check1 = checkPoskod('poskod');
                //check2 = checkPoskod('suratPoskod');
                //alert(check1);
                // alert(check2);

                if ($('#kp').val() == '') {
                    $('#kp').val('-');
                }

                if (check1 == true) {
                    var q = $(f).formSerialize();
                    var url = f.action + '?' + event;
                    $.blockUI({
                        message: $('#displayBox'),
                        css: {
                            top: ($(window).height() - 50) / 2 + 'px',
                            left: ($(window).width() - 50) / 2 + 'px',
                            width: '50px'
                        }
                    });
                    $.post(url, q,
                            function (data) {

                                if (data == '0')
                                {
                                    alert('Sila masukan data pada label yang bertanda *. ');
                                } else {
                                    //alert(data);
                                    //$('#perincianHakmilik',opener.document).html(data);
                                    //alert('Kemaskini Pihak Berjaya');
                                    $('#maklumatHakmilik', opener.document).html(data);
                                    var v = '${actionBean.idHakmilik}';
                                    $.get("${pageContext.request.contextPath}/pendaftaran/KemasukanSubMC?showFormidHakmilik=" + v,
                                            function (data) {
                                                //alert(data);
                                                $("#perincianHakmilik", opener.document).show();
                                                $("#perincianHakmilik", opener.document).html(data);
                                                $.unblockUI();

                                            });
                                    self.close();
                                }

                            }, 'html');
                }
            }


        </script>
        <style type="text/css">
            input.error { background-color: yellow; }
        </style>
    </head>
    <body>
        <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
        <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
        <div class="subtitle">
            <s:form beanclass="etanah.view.stripes.KemasukanSubMC" ><fieldset class="aras1">
                    <br>
                    <legend>Maklumat Pihak Sub MC</legend>

                    <p>
                        <label for="nama">Nama :</label>
                        <s:text name="nama" id="nama" size="60" style="text-transform:uppercase"/>
                    </p>
                    <p>
                        <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                <s:select name="jenispengenalan" id="jenispengenalan">
                                    <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <p>
                        <label for="nama">No Syarikat / Pengenalan :</label>
                        <s:text name="noPengenalan" id="noPengenalan" size="60" style="text-transform:uppercase"/>
                    </p>

                    <p>
                        <label for="alamat">Alamat Surat-Menyurat:</label>
                        <s:text name="suratAlamat1" id="suratAlamat1" size="60" style="text-transform:uppercase"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="pihak.suratAlamat2" id="suratAlamat2" size="60" style="text-transform:uppercase"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="pihak.suratAlamat3" id="suratAlamat3" size="60" style="text-transform:uppercase"/>
                    </p>
                    <p>
                        <label>Bandar :</label>
                        <s:text name="pihak.suratAlamat4" id="suratAlamat4" size="60" style="text-transform:uppercase"/>
                    </p>
                    <p>
                        <label>Poskod :</label>
                        <s:text name="poskod" id="poskod" size="40" maxlength="5"/>
                    </p>

                    <p>
                        <label for="Negeri">Negeri :</label>
                        <s:select name="negeri" id="negeri">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                        </s:select>
                    </p>


                    <p>
                        <label>&nbsp;</label>
                        <%--<s:button name="simpanSUB" id="simpanSUB" value="Simpan Sub" class="longbtn" onclick="simpanPK(this.form, this.name)"/>--%>
                        <%--<s:button name="simpanSUB" id="simpanSUB" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>--%>
                        <!--&nbsp;-->
                       <s:button name="simpanSUB" id="simpanSUB" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                        
                    </p>
                </fieldset>

            </s:form>
        </div>
    </body>
</html>