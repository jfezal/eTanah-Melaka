<%-- 
    Document   : perlantikan_pegawai_penyiasat
    Created on : June 02, 2011, 01:58:00 PM
    Author     : latifah.iskak
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<%-- <script type="text/javascript"
       src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>--%>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/styles/styles.css" />
        <title>Perlantikan Pegawai Penyiasat</title>

    </head>
    <body>
        <script type="text/javascript">
            $(document).ready( function(){
                var kodNegeri = $('#kodNegeri').val();
                var idStage = $('#stageId').val();
                var kodUrusan = $('#kodUrusan').val();
                var keputusan = $('#keputusan').val();
                if(kodNegeri == '04' || kodUrusan == '426'){
                    if(idStage == 'keputusan_op'){
                        if(keputusan == ""){
                            alert('Sila buat keputusan terlebih dahulu.');
                            $('#status').click();
                        }else if (keputusan != "KD"){//KD = Kompaun/Pendakwaan
                            //alert("bukan kompaun/dakwa");
                            $('#agihTugasanPegawai').hide();
                        }
                    }

                }
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                doQueryTask();

            });
            
            function validate(){
                return true;
            }

            function findPegawaiPenyiasat(id){
                if(id != ""){
                    $.get('${pageContext.request.contextPath}/penguatkuasaan/perlantikan_pegawai_penyiasat?findPengguna&id='+id,
                    function(data){
                        $('#page_div').html(data);
                    }, 'html');

                    $('#perananPengguna').val() = '';

                }

            }

            function doAgih(e, f) {
                var i = $('#idPelaksanaWaran').val();
                var nota = $('#permohonanNota').val();
                
                if(nota == 'false'){
                    alert('Sila isi maklumat nota/kertas minit terlebih dahulu.');
                    return false;
                }

                if(i == ''){
                    alert('Sila pilih pengguna terlebih dahulu gg.');
                    $('#idPelaksanaWaran').focus();
                    return false;
                }

                if(confirm('Adakah anda pasti? Sila semak tab yang lain terlebih dahulu jika belum semak.')) {
                    $.blockUI({
                        message: $('#displayBox'),
                        css: {
                            top:  ($(window).height() - 50) /2 + 'px',
                            left: ($(window).width() - 50) /2 + 'px',
                            width: '50px'
                        }
                    });
                    var q = $(f).formSerialize();
                    f.action = f.action + '?' + e + '&' + q;
                    f.submit();
                }
            }

            function doQueryTask() {
                var i = $('#idPengguna').val();
                if(i != ""){
                    $('#tugasan').html('');
                    $('#loading-img').show();
                    var nama = $('#nama').val();

                    var url = '${pageContext.request.contextPath}/agihTugasan?query&idPengguna=' + i;
                    $.get(url,
                    function(data){
                        $('#loading-img').hide();
                        $('#tugasan').html( nama + ' mempunyai ' + data + ' tugasan.');
                    }
                );

                }
      
            }

            function doSimpanData(frm, event, id){
                var i = $('#idPengguna').val();
                if(i == ''){
                    alert('Sila pilih pegawai penyiasat terlebih dahulu.');
                    $('#perananPengguna').focus();
                    return false;
                }
                doBlockUI();
                var queryString = $(frm).formSerialize();
                var url = frm.action + '?' + event;
                $.ajax({
                    type:"POST",
                    url : url,
                    dataType : 'html',
                    data : queryString,
                    error : function(xhr, ajaxOptions, thrownError) {
                        //alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                        $("#" + id).html('<div class=errors>Terdapat Masalah Teknikal. Sila Hubungi Pentadbir Sistem.</div><div id=t></div><br/><div id=error class=b>' + xhr.responseText + '</div>');
                        doUnBlockUI();
                    },
                    success : function(data) {
                        alert("Dokumen untuk perlantikkan pegawai penyiasat telah berjaya di jana. Sila semak dokumen tersebut");
                        $('#folder').click();
                        doUnBlockUI();
                    }
                });
                //setTimeout($.unblockUI, 1000);
            }


        </script>
        <s:messages />
        <s:errors />

        <s:form beanclass="etanah.view.penguatkuasaan.MaklumatPerlantikanActionBean">
            <s:hidden name="permohonan.kodUrusan.kod" id="kodUrusan"/>
            <s:hidden name="stageId" id="stageId"/>
            <s:hidden name="keputusan" id="keputusan"/>
            <s:hidden name="kodNegeri" id="kodNegeri"/>
            <s:hidden name="statusNotaExist" id="permohonanNota"/>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Perlantikan Pegawai Penyiasat</legend>
                    <br>
                    <c:if test="${actionBean.kodNegeri eq '04'}">
                        <div class="instr-fieldset">
                            <font color="red">Makluman : </font>Sila pilih dan simpan maklumat pegawai penyiasat sebelum membuat agihan tugasan.
                        </div>&nbsp;<br/>
                        <s:hidden name="pegawaiSiasat.idPelaksanaWaran" id="idPelaksanaWaran"/>
                    </c:if>


                    <p>
                        <label>Sila Pilih :</label>
                        <s:select name="perananPengguna" id="perananPengguna" onchange="if(findPegawaiPenyiasat(this.value))doSubmit(this.form, this.name,'page_div')">
                            <s:option value=""> Sila Pilih </s:option>
                            <c:forEach items="${actionBean.senaraiPengguna}" var="line">
                                <s:option value="${line.idPengguna}">${line.nama} (${line.jawatan})</s:option>
                            </c:forEach>
                        </s:select>
                        &nbsp;
                    </p>
                    <c:if test="${actionBean.kodNegeri eq '04'}">
                        <p>
                            <label>Nama :</label>
                            <s:text name="nama" id="nama" size="30" style="text-transform: uppercase"/>&nbsp;
                            <img alt="Gambar" src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img"/>
                            <em id="tugasan"/>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.kodNegeri eq '05'}">
                        <p>
                            <label>Nama :</label>
                            <s:text name="nama" id="nama" size="30" readonly="true"/>&nbsp;
                        </p>
                    </c:if>



                    <p>
                        <label>No.Pengenalan :</label>
                        <s:text name="noPengenalan" maxlength="12" readonly="true" size="30"/>
                    </p>

                    <p>
                        <label>Jawatan :</label>
                        <s:text name="jawatan" id="jawatan" size="50" readonly="true"/>&nbsp;
                        <s:hidden name="idPengguna" id="idPengguna"/>

                    </p>

                    <p>
                        <label>Tarikh Lantik:</label>
                        <s:text name="tarikhLantik"  id="tarikhLantik" class="datepicker" formatPattern="dd/MM/yyyy" />&nbsp;
                        <font color="red" size="1">cth : hh / bb / tttt</font>
                    </p>
                    <br/>
                    <c:if test="${actionBean.kodNegeri eq '04'}">
                        <p align="center">
                            <s:button name="simpan" id="save" value="Simpan & Jana" class="longbtn" onclick="if(validate())doSimpanData(this.form, this.name,'page_div')"/>
                            <c:if test="${actionBean.pegawaiSiasat.idPelaksanaWaran eq null}">
                                <s:button name="agihTugasan" id="agihTugasanPegawai" value="Agih Tugasan" class="disablebtn" onclick="doAgih(this.name, this.form);" disabled="true"/>
                            </c:if>
                            <c:if test="${actionBean.pegawaiSiasat.idPelaksanaWaran ne null}">
                                <s:button name="agihTugasan" id="agihTugasanPegawai" value="Agih Tugasan" class="btn" onclick="doAgih(this.name, this.form);"/>
                            </c:if>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.kodNegeri eq '05'}">
                        <p align="center">
                            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validate())doSubmit(this.form, this.name,'page_div')"/>
                        </p>
                    </c:if>

                </fieldset>
            </div>

        </s:form>

    </body>
</html>
