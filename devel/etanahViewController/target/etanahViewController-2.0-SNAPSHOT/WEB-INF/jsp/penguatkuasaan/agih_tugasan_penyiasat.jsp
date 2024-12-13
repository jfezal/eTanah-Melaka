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
<style type="text/css">

    .tablecloth{
        padding:0px;
        width:70%;
    }

    .infoLP{
        float: right;
        font-weight: bold;
        color:#003194;
        font-family:Tahoma;
        font-size: 13px;

    }
</style>

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
                //alert(keputusan);
                if(kodNegeri == '04' && kodUrusan == '426'){
                    if(idStage == 'keputusan_op'){
                        if(keputusan == ""){
                            alert('Sila buat keputusan terlebih dahulu.');
                            $('#status').click();
                        }else if (keputusan != "KD" && keputusan != "PE" && keputusan != "BR"){//KD = Kompaun/Pendakwaan
//                            alert("bukan kompaun/dakwa");
                            $('#agihTugasanPegawai').hide();
                        }
                    }

                }
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                
                var bilPermohonan =  ${fn:length(actionBean.senaraiPermohonanBaru)}; //list senarai baru
                for (var p = 0; p < bilPermohonan; p++){
                    var idPenggunaKetua = document.getElementById('idPenggunaKetua'+p).value;
                    doQueryTask(idPenggunaKetua,p);
                }
                

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

                if(nota == ''){
                    alert('Sila isi maklumat nota/kertas minit terlebih dahulu.');
                    //$('#page_id_8').click();
                    //$('a.Nota').closest('a').click();
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

            function doQueryTask(i,index) {
                if(i != ""){
                    $('#tugasan').html('');
                    $('#loading-img').show();
                    var nama = $('#namaKetua'+index).val();

                    var url = '${pageContext.request.contextPath}/agihTugasan?query&idPengguna=' + i;
                    $.get(url,
                    function(data){
                        $('#loading-img'+index).hide();
                        $('#tugasan'+index).html( nama + ' mempunyai ' + data + ' tugasan.');
                    }
                );

                }
      
            }

            function doSimpanData(frm, event, id){
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
                        //$('#folder').click();
                        doUnBlockUI();
                    }
                });
                //setTimeout($.unblockUI, 1000);
            }
            
            function doViewReport(v) {
                var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
                window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
            }
            
            //            function removeImej(idImej) {
            //                if(confirm('Adakah anda pasti untuk hapus?')) {
            //                    var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?deleteSelected&idImej='+idImej;
            //                    $.get(url,
            //                    function(data){
            //                        $('#page_div').html(data);
            //                        //refreshPage();
            //                    },'html');
            //                }
            //            }


        </script>
        <s:messages />
        <s:errors />

        <s:form beanclass="etanah.view.penguatkuasaan.AgihTugasanPenyiasatActionBean">
            <s:hidden name="permohonan.kodUrusan.kod" id="kodUrusan"/>
            <s:hidden name="stageId" id="stageId"/>
            <s:hidden name="keputusan" id="keputusan"/>
            <s:hidden name="kodNegeri" id="kodNegeri"/>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Agihan Tugasan
                    </legend>
                    <div class="instr-fieldset"><br>
                        Klik butang Agih Tugasan untuk membuat agihan tugasan kepada ketua penyiasat
                    </div>
                    <br>
                    <div class="content" align="center">
                        <display:table name="${actionBean.senaraiPermohonanBaru}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                            <display:column title="Bil" sortable="true" style="width:3%">${line_rowNum}</display:column>
                            <display:column title="ID Kertas Siasatan" property="idPermohonan" style="width:20%"/>
                            <display:column title="Pegawai Penyiasat" style="width:70%">
                                <c:forEach items="${actionBean.senaraiPegawaiPenyiasat}" var="p">
                                    <c:if test="${p.permohonan.idPermohonan eq line.idPermohonan && p.statusPeranan eq 'K'}">
                                        <table width="20%" cellpadding="1">
                                            <tr>
                                                <td width="20%"><font class="infoLP">Nama Ketua Penyiasat:</font></td>
                                                <td width="20%"> 
                                                    ${p.nama}&nbsp;
                                                    <input type="hidden" name="namaKetua${line_rowNum-1}" id="namaKetua${line_rowNum-1}" value="${p.nama}">
                                                    <input type="hidden" name="idPenggunaKetua${line_rowNum-1}" id="idPenggunaKetua${line_rowNum-1}" value="${p.namaJabatan}">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td width="20%"><font class="infoLP">&nbsp;</font></td>
                                                <td style="font-size: 13px;">
                                                    <img alt="Gambar" src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img${line_rowNum-1}"/>
                                                    <em id="tugasan${line_rowNum-1}"/> 
                                                </td>
                                            </tr>
                                        </table>
                                    </c:if>
                                </c:forEach>
                                <table width="20%" cellpadding="1">
                                    <tr>
                                        <td width="20%"><font class="infoLP">Nama Pembantu Penyiasat:</font></td>
                                        <td width="20%">&nbsp; </td>
                                    </tr>
                                    <c:set value="1" var="i"/>
                                    <c:forEach items="${actionBean.senaraiPegawaiPenyiasat}" var="p">
                                        <c:if test="${p.permohonan.idPermohonan eq line.idPermohonan && p.statusPeranan eq 'P'}">
                                            <tr>
                                                <td width="20%">&nbsp; </td>
                                                <td width="20%" colspan="2">${i}) ${p.nama}&nbsp; </td>
                                            </tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:if>
                                    </c:forEach>


                                </table>
                            </display:column>
                            <display:column title="Surat Perlantikan">
                                <p align="center">
                                    <c:set value="1" var="count"/>
                                    <c:forEach items="${line.folderDokumen.senaraiKandungan}" var="senarai">
                                        <c:if test="${senarai.dokumen.kodDokumen.kod eq 'SPPP'}">
                                            <c:if test="${senarai.dokumen.namaFizikal != null}">
                                                <br>
                                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                     onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                                <c:set value="${count+1}" var="count"/>
                                            </c:if>
                                        </c:if>

                                    </c:forEach>
                                </p>
                            </display:column>
                        </display:table>
                        <br>
                        <s:button name="janaDokumen" id="save" value="Jana Dokumen" class="longbtn" onclick="doSubmit(this.form, this.name,'page_div')"/>
                        <c:if test="${actionBean.reportSPPP}">
                            <s:button name="agihTugasan" id="agihTugasanPegawai" value="Agih Tugasan" class="btn" onclick="doAgih(this.name, this.form);"/>
                        </c:if>
                        <c:if test="${!actionBean.reportSPPP}">
                            <s:button name="agihTugasan" id="agihTugasanPegawai" disabled="true"  value="Agih Tugasan" class="btn" onclick="doAgih(this.name, this.form);"/>
                        </c:if>
                    </div>

                    <c:if test="${view}">
                        <br>

                    </c:if>


                </fieldset>
            </div>

        </s:form>

    </body>
</html>
