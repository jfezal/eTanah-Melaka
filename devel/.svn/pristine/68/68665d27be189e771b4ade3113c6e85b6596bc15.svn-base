<%-- 
    Document   : tukarganti_main
    Created on : Jul 13, 2015, 10:25:46 AM
    Author     : haqqiem
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />
        <title>Jana Berperingkat</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
        <script language="javascript">

            $(document).ready(function () {
                $('input:text').each(function () {
                    $(this).focus(function () {
                        $(this).addClass('focus')
                    });
                    $(this).blur(function () {
                        $(this).removeClass('focus')
                    });
                });
                $('select').each(function () {
                    $(this).focus(function () {
                        $(this).addClass('focus')
                    });
                    $(this).blur(function () {
                        $(this).removeClass('focus')
                    });
                });

                $('form').submit(function () {
                    doBlockUI();
                    var valid = false;
                    var id = $('#idPermohonan').val();
                    if (id === '') {
                        $('.kodUrusan').each(function (index) {
                            updateSelect(index);
                            var val = $('#kodUrusanKod' + index).val();
                            if (val != '') {
                                valid = true;
                            }
                            if (val == '') {
                                valid = false;
                            }
                        });
                    } else {
                        valid = true;
                    }

                    if (!valid)
                        doUnBlockUI();

                    return valid;
                });
                $('#b1').hide();
                $('#b2').hide();
            });

            function updateSelect() {
                var textKodUrusanKod = document.getElementById('kodUrusanKod');
                if (textKodUrusanKod.value.length > 0) {
                    var selectKodUrusan = document.getElementById('kodUrusan');
                    selectKodUrusan.selectedIndex = 0;
                    var kod = textKodUrusanKod.value.toUpperCase();
                    for (var i = 0; i < selectKodUrusan.options.length; ++i) {
                        if (selectKodUrusan.options[i].value == kod) {
                            selectKodUrusan.selectedIndex = i;
                            updateJabatan(selectKodUrusan.options[i].parentNode.label);
                            break;
                        }
                    }
                    if (selectKodUrusan.selectedIndex == 0) {
                        $('#kodUrusanKod').val('');
                        alert('Kod Urusan ' + kod + ' tidak dijumpai.');
                        $('#kodUrusanKod').focus();
                    }
                }
            }

            function updateKod(kod) {
                var selectKodUrusan = document.getElementById('kodUrusan');
                $('#kodUrusanKod').val(kod);
                if (kod == 'HKTHK') {
                    $('#b1').show();
                    $('#b2').hide();
                } else if (kod == 'HSTHK') {
                    $('#b1').hide();
                    $('#b2').show();
                } else {
                    $('#b1').hide();
                    $('#b2').hide();
                }
                if (selectKodUrusan.selectedIndex > 0) {
                    var textKodUrusanKod = document.getElementById('kodUrusanKod');
                    textKodUrusanKod.value = selectKodUrusan.options[selectKodUrusan.selectedIndex].value;
                    updateJabatan(selectKodUrusan.options[selectKodUrusan.selectedIndex].parentNode.label);
                }
            }

            function updateJabatan(namaJabatan) {
                var selectJabatan = document.getElementById('kodJabatan');
                for (i = 0; i < selectJabatan.length; i++) {
                    if (selectJabatan.options[i].text == namaJabatan) {
                        selectJabatan.selectedIndex = i;
                        break;
                    }
                }
            }

            function doBlockUI() {
                $.blockUI({
                    message: $('#displayBox'),
                    css: {
                        top: ($(window).height() - 50) / 2 + 'px',
                        left: ($(window).width() - 50) / 2 + 'px',
                        width: '50px'
                    }
                });
            }

            function doUnBlockUI() {
                $.unblockUI();
            }

            function checkingValue(id) {
                var inpt;
                if (id == 'urusan') {
                    inpt = document.getElementById('kodUrusan');
                    if (inpt.value == '0') {
                        alert('Sila Pilih Urusan.');
                        $('#kodUrusan').focus();
                        return false;
                    }
                }
                if (id == 'idMohon') {
                    inpt = document.getElementById('idMohon');
                    if (inpt.value == '') {
                        alert('Sila masukkan ID Perserahan untuk membuat carian.');
                        $('#idMohon').focus();
                        return false;
                    }
                }
                if (id == 'idHakmilik') {
                    inpt = document.getElementById('idHakmilik');
                    if (inpt.value == '') {
                        alert('Sila masukkan ID Hakmilik untuk membuat carian.');
                        $('#idHakmilik').focus();
                        return false;
                    }
                }
                if (id == 'serah') {
                    inpt = document.getElementById('idSerah');
                    if (inpt.value == '') {
                        alert('Sila masukkan ID Perserahan untuk dibatalkan.');
                        $('#idSerah').focus();
                        return false;
                    }
                }
                return true;
            }

            function choose(idMohon) {
//                var l = idMohon.substring(6);
//                alert(l);
                $('#idMohon').val(idMohon);
                $('#step7').click();
            }
            function doViewReport(v) {
                var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
                window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
            }

            function kemaskini(idHakmilik, idPermohonan) {

                window.open("${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik_utiliti?showForm&idHakmilik=" + idHakmilik + "&idPermohonan=" + idPermohonan, "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=900,height=400");
            }

            function doSignFile(dateSvr, flag) {

                alert(dateSvr + "date");

                var DELIM = "|";
                var flag = "S";
                var parameterToSign = '';
                var v_file = '';
                var dhde_file = '';
                var dhke_file = '';
                var b_file = '';
                var batal_file = '';

                $('.sign').each(function (index) {
                    index = index + 1;
                    if ($('#vdoc' + index).is(':checked')) {
                        v_file = $('#vdoc' + index).val() + '#' + $('#vdoc_path' + index).val() + '#vdoc#N';
                        if (parameterToSign != '') {
                            parameterToSign = parameterToSign + DELIM + v_file;
                        } else {
                            parameterToSign = v_file;
                        }
                    }

                    if ($('#dhke' + index).is(':checked')) {
                        dhke_file = $('#dhke' + index).val() + '#' + $('#dhke_path' + index).val() + '#dhke#' + flag;
                        if (parameterToSign != '') {
                            parameterToSign = parameterToSign + DELIM + dhke_file;
                        } else {
                            parameterToSign = dhke_file;
                        }
                    }

                    if ($('#dhde' + index).is(':checked')) {
                        dhde_file = $('#dhde' + index).val() + '#' + $('#dhde_path' + index).val() + '#dhde#' + flag;
                        if (parameterToSign != '') {
                            parameterToSign = parameterToSign + DELIM + dhde_file;
                        } else {
                            parameterToSign = dhde_file;
                        }
                    }

                    if ($('#pelan' + index).is(':checked')) {
                        b_file = $('#pelan' + index).val() + '#' + $('#pelan_path' + index).val() + '#b1#N';
                        if (parameterToSign != '') {
                            parameterToSign = parameterToSign + DELIM + b_file;
                        } else {
                            parameterToSign = b_file;
                        }
                    }

                    if ($('#pelan_2_' + index).is(':checked')) {
                        b_file = $('#pelan_2_' + index).val() + '#' + $('#pelan_path_2_' + index).val() + '#b1#N';
                        if (parameterToSign != '') {
                            parameterToSign = parameterToSign + DELIM + b_file;
                        } else {
                            parameterToSign = b_file;
                        }
                    }

                    if ($('#hakmilikBatal' + index).is(':checked')) {
                        batal_file = $('#hakmilikBatal' + index).val() + '#' + $('#hakmilikBatal_path' + index).val() + '#dhde#N';
                        if (parameterToSign != '') {
                            parameterToSign = parameterToSign + DELIM + batal_file;
                        } else {
                            parameterToSign = batal_file;
                        }
                    }

                });
                //alert(parameterToSign);
                if (parameterToSign != '') {
                    doBlockUI();
                    var signer = new ActiveXObject("SAPDFSigner.Form1");
                    alert(dateSvr + "date1");
                    signer.SigningPDFFile(parameterToSign, dateSvr);
                    alert(dateSvr + "date3");
                    if (signer.getValue() != "") {
                        doUnBlockUI();
                        document.eload.message.value = signer.getValue();
                    }
                } else {
                    alert(dateSvr + "date2");
                    alert('Sila Pilih Dokumen untuk ditandatangan.');
                }
            }

            function p(v) {
                $.blockUI({
                    message: $('#displayBox'),
                    css: {
                        top: ($(window).height() - 50) / 2 + 'px',
                        left: ($(window).width() - 50) / 2 + 'px',
                        width: '50px'
                    }
                });

                $.get("${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?idHakmilik=" + v,
                        function (data) {
                            //alert(data);
                            $("#perincianHakmilik").show();
                            $("#perincianHakmilik").html(data);
                            $.unblockUI();
                        });

            }
            
            function selectAll(a,b){
                var size = '${fn:length(actionBean.senaraiHP)}';
                //alert(size);
                for (var i = 0; i <= size; i++){
                    if(document.getElementById(b + i)){
                	var c = document.getElementById(b + i);                  
			c.checked = a.checked;
                    }
                }
            }
            

            
        </script>
    </head>
    <body>      
        <stripes:messages />
        <stripes:errors />
        <div align="center"><table style="width:100%" bgcolor="#00FFFF">
                <tr>
                    <td width="100%" height="20" >
                        <div style="background-color: 00FFFF;" align="center">
                            <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">PENDAFTARAN: Utiliti Jana Berperingkat</font>
                        </div>
                    </td>
                </tr>
            </table></div>
        <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>



        <!--  PERMOHOHONAN/PERSERAHAN-->

        <stripes:form action="/daftar/utilitiPecahSepadan" id="main_kaunter">


            <br>
            <fieldset class="aras1">
                <p class=title>Langkah 1: Semak Perserahan</p>
                <p class=title>Carian Perserahan</p>
                <span style="font-weight:normal;color: black" class=instr>Medan bertanda <em>*</em> adalah mandatori. Sila masukkan ID Perserahan pada ruang yang disediakan.</span>


                <c:set scope="request" var="senaraiUrusanPendaftaran" value="${actionBean.senaraiUrusanTukarganti}" />

                <p><label><em>*</em>ID Perserahan : </label>
                    <stripes:text name="idMohon" id="idMohon" size="30" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>

                <p>
                    <label>&nbsp;</label>
                    <stripes:submit name="Step7a" value="Cari" class="btn" id="step7" onclick="return checkingValue('idMohon')"/>
                    <stripes:button name="" class='btn' value="Isi Semula" onclick="clearText('main_kaunter');"/>&nbsp;
                </p>

                <c:if test="${actionBean.permohonan ne null}">

                    <%--<c:if test="${actionBean.mohonFasa.idPengguna eq pguna.idPengguna}">--%>
                    <fieldset class="aras1">
                        
                            <legend>
                                Hasil Carian
                            </legend>
                            <c:if test="${actionBean.permohonan != null}">
                                <div class="content" align="center">
                                    <display:table class="tablecloth" name="${actionBean.permohonan}"
                                                   pagesize="10" cellpadding="0" cellspacing="0"
                                                   requestURI="/daftar/utilitiPecahSepadan" id="line">
                                        <c:set var="row_num" value="${row_num+1}"/>
                                        <display:column title="Bil" sortable="true" > <div align="center">${row_num}</div></display:column>
                                        <display:column property="idPermohonan" title="ID Perserahan" style="width:25%"/>
                                        <display:column property="kodUrusan.nama" title="Perserahan" style="width:25%"/>
                                        <%--<display:column property="idPermohonan" title="ID Perserahan" style="width:25%"/>--%>
                                        <display:column property="infoAudit.dimasukOleh.nama" title="Dimasuk Oleh"/>
                                        <display:column title="Tarikh Masuk">
                                            <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm aa"/>
                                        </display:column>
                                        <display:column title="status">
                                            <c:if test="${line.status eq 'SL'}">Selesai</c:if>
                                            <c:if test="${line.status eq 'TA'|| line.status eq 'AK'}">Belum Selesai</c:if>
                                            <c:if test="${line.status eq 'TK' || line.status eq 'BP' }">Telah Dibatalkan</c:if>
                                            <c:if test="${line.status eq 'SS'}">Semak Semula</c:if>
                                        </display:column>
                                    </display:table>
                                </div>
                                <c:if test="${actionBean.mohonFasa.keputusan eq null}">
                                    <c:if test="${actionBean.pguna.jawatan eq 'Pendaftar'}">
                                        <label><em>*</em>Permohonan Ini Masih Belum Dibuat keputusan. Sila Klik Button DiSebelah </label>
                                        <%--<stripes:submit name="simpanKeputusan" value="Cari" class="btn" id="step7" onclick="return checkingValue('idHakmilikFirst','idHakmilik2')"/>--%>

                                        <s:submit name="simpanKeputusan3" value="simpan Keputusan" class="btn"/>
                                        <br>
                                        <br>
                                    </c:if>
                                </c:if>
                                <br>
                               
                                    <p><label><em>*</em>ID Hakmilik : </label>
                                        <stripes:text name="idHakmilikFirst" id="idHakmilikFirst.idHakmilik" size="30" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp&nbsp&nbsp - &nbsp&nbsp&nbsp
                                        <stripes:text name="idHakmilik2" id="idHakmilik2" size="30" onkeyup="this.value=this.value.toUpperCase();"/>
                                    </p>
                              
                                <c:if test="${actionBean.mohonFasa.keputusan.kod eq 'D'}">
                                    <p><label><em>*</em>ID Hakmilik : </label>
                                        <stripes:text name="idHakmilikFirst" id="idHakmilikFirst.idHakmilik" size="30" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp&nbsp&nbsp - &nbsp&nbsp&nbsp
                                        <stripes:text name="idHakmilik2" id="idHakmilik2" size="30" onkeyup="this.value=this.value.toUpperCase();"/>
                                    </p>
                                </c:if>
                                <br>
                                <div align="center">
                                    <s:hidden name="${actionBean.permohonan}"/> 
                                    <stripes:submit name="carianHakmilikBetween" value="Cari Hakmilik" class="btn" id="step7" onclick="return checkingValue('idHakmilikFirst','idHakmilik2')"/>
                                    <stripes:button name="" class='btn' value="Isi Semula" onclick="clearText('main_kaunter');"/>&nbsp;
                                </div>

                                <%--</c:if>--%>
                            </c:if>
                       
                    </fieldset>

                </c:if>
                <BR>

            </fieldset>
            <!--<div align="center">-->
            <fieldset class="aras1">
                <c:if test="${fn:length(actionBean.senaraiHP) > 0}">
                    <div class="content" align="center">
                        <display:table style="width:80%;" class="tablecloth" name="${actionBean.senaraiHP}" cellpadding="0" cellspacing="0" id="line">
                            <display:column title="No" >
                                <c:out value="${line_rowNum}"/>
                            </display:column>
                            <display:column property="hakmilik.idHakmilik" title="Hakmilik" class="sign"/>
                            <display:column title="ID Hakmilik"><a href="#" onclick="kemaskini('${line.hakmilik.idHakmilik}', '${line.permohonan.idPermohonan}');return false;">${line.hakmilik.idHakmilik}</a>
                                <s:hidden name="hiddenIdHakmilik" id="hiddenIdHakmilik${line_rowNum-1}" value="${line.hakmilik.idHakmilik}"/>
                            </display:column>  

                            <display:column title="<input type='checkbox' id='semuaDDHDK' name='ddhdk' onclick='javascript:selectAll(this,this.name);' />DDHDK" style="width:10%">
                                <c:if test="${line.dokumen1.namaFizikal != null}">
                                    <a href="#" id="p" onclick="doViewReport('${line.dokumen1.idDokumen}');
                                            return false;">Papar</a>
                                    <input type="checkbox" id="ddhdk${line_rowNum}"
                                           title="Sila klik untuk tandatangan ${line.dokumen1.kodDokumen.nama}"
                                           value="${line.dokumen1.idDokumen}"/>
                                    <c:set var="path"/>
                                    <c:forTokens delims="/" items="${line.dokumen1.namaFizikal}" var="items" begin="0" end="3">
                                        <c:set var="path" value="${path}/${items}"/>
                                    </c:forTokens>
                                    <input type="hidden" id="ddhdk_path${line_rowNum}" value="${path}"/>
                                </c:if>
                            </display:column>                            
                            
                            <display:column title="<input type='checkbox' id='semuaDHDe' name='dhde' onclick='javascript:selectAll(this,this.name);' />DHDe" style="width:10%">
                                <c:if test="${line.dokumen3.namaFizikal != null}">
                                    <a href="#" id="p" onclick="doViewReport('${line.dokumen3.idDokumen}');
                                            return false;">Papar</a>
                                    <input type="checkbox" id="dhde${line_rowNum}"
                                           title="Sila klik untuk tandatangan ${line.dokumen3.kodDokumen.nama}"
                                           value="${line.dokumen3.idDokumen}"/>
                                    <c:set var="path"/>
                                    <c:forTokens delims="/" items="${line.dokumen3.namaFizikal}" var="items" begin="0" end="3">
                                        <c:set var="path" value="${path}/${items}"/>
                                    </c:forTokens>
                                    <input type="hidden" id="dhde_path${line_rowNum}" value="${path}"/>
                                </c:if>
                            </display:column>
                                    
                            <display:column title="<input type='checkbox' id='semuaDHKe' name='dhke' onclick='javascript:selectAll(this,this.name);' />DHKe " style="width:10%">
                                <c:if test="${line.dokumen2.namaFizikal != null}">
                                    <a href="#" id="p" onclick="doViewReport('${line.dokumen2.idDokumen}');
                                            return false;">Papar</a>
                                    <input type="checkbox" id="dhke${line_rowNum}" class="dhke"
                                           title="Sila klik untuk tandatangan ${line.dokumen2.kodDokumen.nama}"
                                           value="${line.dokumen2.idDokumen}"/>
                                    <c:set var="path"/>
                                    <c:forTokens delims="/" items="${line.dokumen2.namaFizikal}" var="items" begin="0" end="3">
                                        <c:set var="path" value="${path}/${items}"/>
                                    </c:forTokens>
                                    <input type="hidden" id="dhke_path${line_rowNum}" value="${path}"/>
                                </c:if>
                            </display:column>
                            <c:if test="${cetak}">
                                <c:if test="${!(actionBean.permohonan.kodUrusan.kod eq 'HKTHK' || actionBean.permohonan.kodUrusan.kod eq 'HSTHK') }">
                                    <display:column title="Surat Daftar / Tolak / Gantung / Notis 5F" style="width:20%">
                                        <c:if test="${line.dokumen4.namaFizikal != null}">
                                            <a href="#" id="p" onclick="doViewReport('${line.dokumen4.idDokumen}');
                                                    return false;">Papar</a>
                                        </c:if>
                                    </display:column>
                                </c:if>
                            </c:if>
                            <!--apool-->
                            <c:if test="${((actionBean.permohonan.kodUrusan.kod ne 'HT' || actionBean.permohonan.kodUrusan.kod ne 'HTSC' || actionBean.permohonan.kodUrusan.kod ne 'HTSPB') && actionBean.permohonan.kodUrusan.kodPerserahan.kod eq 'MH')||actionBean.permohonan.kodUrusan.kod eq 'BETP'||actionBean.permohonan.kodUrusan.kod eq 'BETUL'}">
                                <display:column title="<input type='checkbox' id='semuaPelan' name='pelan' onclick='javascript:selectAll(this,this.name);' />Pelan B1 DHDe / B2 DHDe">
                                    <c:if test="${line.dokumen5.namaFizikal != null}">
                                        <a href="#" id="p" onclick="doViewReport('${line.dokumen5.idDokumen}');
                                                return false;">Papar</a>
                                        <input type="checkbox" id="pelan${line_rowNum}"
                                               title="Sila klik untuk tandatangan ${line.dokumen5.kodDokumen.nama}"
                                               value="${line.dokumen5.idDokumen}"/>
                                        <c:set var="path"/>
                                        <c:forTokens delims="/" items="${line.dokumen5.namaFizikal}" var="items" begin="0" end="3">
                                            <c:set var="path" value="${path}/${items}"/>
                                        </c:forTokens>
                                        <input type="hidden" id="pelan_path${line_rowNum}" value="${path}"/>
                                    </c:if>
                                </display:column>
                                <display:column title="<input type='checkbox' id='semuaPelan2' name='pelan_2_' onclick='javascript:selectAll(this,this.name);' />Pelan B1 DHKe / B2 DHKe">
                                    <c:if test="${line.dokumen6.namaFizikal != null}">
                                        <a href="#" id="p" onclick="doViewReport('${line.dokumen6.idDokumen}');
                                                return false;">Papar</a>
                                        <input type="checkbox" id="pelan_2_${line_rowNum}"
                                               title="Sila klik untuk tandatangan ${line.dokumen6.kodDokumen.nama}"
                                               value="${line.dokumen6.idDokumen}"/>
                                        <c:set var="path"/>
                                        <c:forTokens delims="/" items="${line.dokumen6.namaFizikal}" var="items" begin="0" end="3">
                                            <c:set var="path" value="${path}/${items}"/>
                                        </c:forTokens>
                                        <input type="hidden" id="pelan_path_2_${line_rowNum}" value="${path}"/>
                                    </c:if>
                                </display:column>
                                <c:if test="${fn:length(actionBean.hakmilikPermohonan) > 1}">
                                    <display:column title="Hapus" style="text-align:center">
                                        <%--<a href="#">--%>
                                        <img alt='Klik Untuk Hapus' src='${pageContext.request.contextPath}/images/not_ok.gif'
                                             onclick="hapus('${line.id}', '${line.hakmilik.idHakmilik}');" title="Klik untuk Hapus" />
                                        <%--</a>--%>
                                    </display:column>
                                </c:if>

                            </c:if>
                        </display:table>

                        <br>
                        <p>
                            <c:set var="prm" value="N"/>
                            <c:if test="${!empty multiple_sign_kekal}">
                                <c:set var="prm" value="Y"/>
                            </c:if>
                            <c:if test="${!empty multiple_sign_sementara}">
                                <c:set var="prm" value="S"/>
                            </c:if>

                            <c:if test="${actionBean.pguna.perananUtama.nama eq 'Pendaftar'}">
                                <s:button name="" id="" value="T/tangan" class="btn" onclick="doSignFile('${actionBean.mohonFasa.infoAudit.tarikhMasuk}', '${prm}');"/>
                            </c:if>
                            <c:if test="${cetak}">
                                <s:button name="" value="Cetak" class="btn" onclick="doPrintViaApplet();"/>
                            </c:if>
                            <%--<s:submit name="Step8" value="Jana Semua" class="btn" onclick="return baki();"/>--%>
                            <c:if test="${actionBean.pguna.perananUtama.nama eq 'Pendaftar'}">
                               
                                <s:submit name="Step8b" value="Jana Geran" class="btn" onclick="validate(this.form)"/>
                            </c:if>
                            <c:if test="${actionBean.pguna.perananUtama.nama ne 'Pendaftar'}">
                                <s:submit name="Step8c" value="Jana DDHDK" class="btn" onclick="validate(this.form)"/> 
                            </c:if>
                             <s:submit name="Step8a" value="Jana Pelan" class="btn" onclick="validate(this.form);"/>                            

                        </p>
                    </div>
                    <br>
                    <td align="center">
                        <%--<s:submit name="Step9" value="Kembali" class="btn" />--%>              

                    </td>
                </c:if>
            </fieldset>
            <!--</div>-->
            <stripes:submit name="updateUrusanJabatan" style="display:none;" />

        </stripes:form>

        <br/>
    </body>
</html>