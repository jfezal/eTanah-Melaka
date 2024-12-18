<%-- 
    Document   : tukarganti_daftar
    Created on : Jul 14, 2015, 3:45:05 PM
    Author     : haqqiem
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquery.progressbar.min.js"></script>
<style type="text/css">
    input.error { background-color: yellow; }

    body { font-family: sans-serif;}
    #confirmBox
    {
        display: none;
        background-color: #eee;
        border-radius: 5px;
        border: 3px solid red;
        position: absolute;
        width: 540px;
        left: 20%;
        margin-left: 100px;
        margin-top: 30px;
        padding: 10px 8px 8px;
        box-sizing: border-box;
        text-align: left;
    }
    #button {
        background-color: #aaa;
        display: inline-block;
        border-radius: 10px;
        padding: 2px;
        padding: 10px 8px 8px;
        text-align: center;
        width: 100px;
        cursor: pointer;
        border: 3px solid #aaa;
        position: center;
    }
    #button:hover
    {
        text-decoration-style: bold;
        background-color: #ddd;
        border: 13px solid #black;
    }
    #message
    {
        text-decoration-color: red;
        text-align: center;
        margin-bottom: 8px;
    }
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $('form').submit(function() {
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });
        });
        $("input:checkbox").attr('checked', 'checked');
    });
</script>
<script type="text/javascript">
    function doPrintViaApplet() {
        var FILE_TO_PRINT = '';
        var DELIM = ',';

        $('.sign').each(function(index) {
            index = index + 1;
            if ($('#vdoc' + index).is(':checked')) {
                if (FILE_TO_PRINT == '') {
                    FILE_TO_PRINT = $('#vdoc' + index).val();
                } else {
                    FILE_TO_PRINT = FILE_TO_PRINT + DELIM + $('#vdoc' + index).val();
                }
            }
            if ($('#dhke' + index).is(':checked')) {
                if (FILE_TO_PRINT == '') {
                    FILE_TO_PRINT = $('#dhke' + index).val();
                } else {
                    FILE_TO_PRINT = FILE_TO_PRINT + DELIM + $('#dhke' + index).val();
                }
            }

            if ($('#dhde' + index).is(':checked')) {
                if (FILE_TO_PRINT == '') {
                    FILE_TO_PRINT = $('#dhde' + index).val();
                } else {
                    FILE_TO_PRINT = FILE_TO_PRINT + DELIM + $('#dhde' + index).val();
                }
            }
            if ($('#pelan' + index).is(':checked')) {
                if (FILE_TO_PRINT == '') {
                    FILE_TO_PRINT = $('#pelan' + index).val();
                } else {
                    FILE_TO_PRINT = FILE_TO_PRINT + DELIM + $('#pelan' + index).val();
                }
            }
            if ($('#pelan_2_' + index).is(':checked')) {
                if (FILE_TO_PRINT == '') {
                    FILE_TO_PRINT = $('#pelan_2_' + index).val();
                } else {
                    FILE_TO_PRINT = FILE_TO_PRINT + DELIM + $('#pelan_2_' + index).val();
                }
            }
            if ($('#hakmilikBatal' + index).is(':checked')) {
                if (FILE_TO_PRINT == '') {
                    FILE_TO_PRINT = $('#hakmilikBatal' + index).val();
                } else {
                    FILE_TO_PRINT = FILE_TO_PRINT + DELIM + $('#hakmilikBatal' + index).val();
                }
            }

        });

        if (FILE_TO_PRINT != '') {
            var a = document.getElementById('applet2');
            a.doPrint(FILE_TO_PRINT);
            //a.printDocument(FILE_TO_PRINT);
        }
    }

    function doSignFile(dateSvr, flag) {
        var DELIM = "|";
        var parameterToSign = '';
        var v_file = '';
        var dhde_file = '';
        var dhke_file = '';
        var b_file = '';
        var batal_file = '';

        $('.sign').each(function(index) {
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
            signer.SigningPDFFile(parameterToSign, dateSvr);
            if (signer.getValue() != "") {
                doUnBlockUI();
                document.eload.message.value = signer.getValue();
            }
        } else {
            alert('Sila Pilih Dokumen untuk ditandatangan.');
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

    function signMultipleFiles(folderId, txtJawatan) {
        var DELIM = "|";
        var docToSign = '';
        var folderToSign = '';

        $('.sign').each(function(index) {
            index = index + 1;
            if ($('#vdoc' + index).is(':checked')) {
                if (docToSign != '') {
                    docToSign = docToSign + DELIM + $('#vdoc' + index).val();
                } else {
                    docToSign = $('#vdoc' + index).val();
                }

                if (folderToSign != '') {
                    //folderToSign = folderToSign + DELIM + folderId;
                    folderToSign = folderToSign + DELIM + $('#vdoc_path' + index).val();
                } else {
                    folderToSign = $('#vdoc_path' + index).val();
                }
            } else if ($('#vdoc' + index).val() == 'undefined') {
                docToSign = docToSign + DELIM;
                folderToSign = folderToSign + DELIM;
            }
            else {
                docToSign = docToSign + DELIM;
                folderToSign = folderToSign + DELIM;
            }

            if ($('#dhke' + index).is(':checked')) {
                if (docToSign != '') {
                    docToSign = docToSign + DELIM + $('#dhke' + index).val();
                } else {
                    docToSign = $('#dhke' + index).val();
                }

                if (folderToSign != '') {
                    folderToSign = folderToSign + DELIM + $('#dhke_path' + index).val();
                } else {
                    folderToSign = $('#dhke_path' + index).val();
                    ;
                }
            } else {
                docToSign = docToSign + DELIM;
                folderToSign = folderToSign + DELIM
            }

            if ($('#dhde' + index).is(':checked')) {
                if (docToSign != '') {
                    docToSign = docToSign + DELIM + $('#dhde' + index).val();
                } else {
                    docToSign = $('#dhde' + index).val();
                }

                if (folderToSign != '') {
                    folderToSign = folderToSign + DELIM + $('#dhde_path' + index).val();
                } else {
                    folderToSign = $('#dhde_path' + index).val();
                    ;
                }
            } else {
                docToSign = docToSign + DELIM;
                folderToSign = folderToSign + DELIM
            }

            if ($('#pelan' + index).is(':checked')) {
                if (docToSign != '') {
                    docToSign = docToSign + DELIM + $('#pelan' + index).val();
                } else {
                    docToSign = $('#pelan' + index).val();
                }

                if (folderToSign != '') {
                    folderToSign = folderToSign + DELIM + $('#pelan_path' + index).val();
                } else {
                    folderToSign = $('#pelan_path' + index).val();
                    ;
                }
            } else {
                docToSign = docToSign + DELIM;
                folderToSign = folderToSign + DELIM
            }
        });

        if (docToSign != '||||' && folderToSign != '||||') {
            var signer = new ActiveXObject("SAPDFSigner.Form1");
            signer.SigningPDFFile(docToSign, folderToSign, txtJawatan);
            if (signer.getValue() != "") {
                document.eload.message.value = signer.getValue();
            }
        } else {
            alert('Sila Pilih Dokumen untuk ditandatangan.');
        }

    }

    function signFilesVDoc(fileNme, fldrNme, txtJawatan, template, signature, signer) {
        signer.SigningPDFFile(fileNme, fldrNme, txtJawatan, 340, 590, 430, 620, template, 1, signature)
        if (signer.getValue() != "") {
            document.eload.message.value = signer.getValue();
        }

        if (document.eload.message.value != "undefined") {
            var urls = '${pageContext.request.contextPath}/dokumen/view/' + fileNme + '?signForm&afterSign=true&vdoc=true';
            document.eload.action = urls;
            document.eload.submit();
        }
    }

    function signFilesB1(fileNme, fldrNme, txtJawatan, template, signature) {
        var signer = new ActiveXObject("SAPDFSigner.Form1");

        signer.SigningPDFFile(fileNme, fldrNme, txtJawatan, 420, 80, 540, 140, template, 1, signature)
        if (signer.getValue() != "") {
            document.eload.message.value = signer.getValue();
        }

        if (document.eload.message.value != "undefined") {
            var urls = '${pageContext.request.contextPath}/dokumen/view/' + fileNme + '?signForm&afterSign=true';
            document.eload.action = urls;
            document.eload.submit();
        }
    }


    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function doViewGeranSemasa(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v + '?watermark=true';
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function selectAll(a) {
        var id = a.id;
        var len = $('.sign').length;
        for (i = 1; i <= len; i++) {
            if (id == 'semuaVdoc') {
                var c = document.getElementById("vdoc" + i);
                c.checked = a.checked;
            } else if (id == 'semuaDHDe') {
                var c = document.getElementById("dhde" + i);
                c.checked = a.checked;
            } else if (id == 'semuaDHKe') {
                var c = document.getElementById("dhke" + i);
                c.checked = a.checked;
            } else if (id == 'semuaPelan') {
                var c = document.getElementById("pelan" + i);
                c.checked = a.checked;
            } else if (id == 'semuaHakmilikBatal') {
                var c = document.getElementById("hakmilikBatal" + i);
                c.checked = a.checked;
            } else if (id == 'semuaPelan2') {
                var c = document.getElementById("pelan_2_" + i);
                c.checked = a.checked;
            }
        }
    }

    function doViewSptb(v) {
        var url = '${pageContext.request.contextPath}/dokumen/viewsptb/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function compare(f, id, rowNum) {
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/common/view_and_compare_title?convertAndCompare&idHakmilik=' + id, q,
                function(data) {
                    if (data != '') {
                        if (data.length == 4) {
                            $("#progressbar" + rowNum).progressBar(data.substring(0, 3), {boxImage: '${pageContext.request.contextPath}/pub/images/progressbar.gif', barImage: {0: '${pageContext.request.contextPath}/pub/images/progressbg_red.gif', 30: '${pageContext.request.contextPath}/pub/images/progressbg_orange.gif', 70: '${pageContext.request.contextPath}/pub/images/progressbg_green.gif'}});
                        }

                        else if (data.length == 2) {
                            $("#progressbar" + rowNum).progressBar(data.substring(0, 1), {boxImage: '${pageContext.request.contextPath}/pub/images/progressbar.gif', barImage: {0: '${pageContext.request.contextPath}/pub/images/progressbg_red.gif', 30: '${pageContext.request.contextPath}/pub/images/progressbg_orange.gif', 70: '${pageContext.request.contextPath}/pub/images/progressbg_green.gif'}});
                        }
                        else {
                            $("#progressbar" + rowNum).progressBar(data.substring(0, 2), {boxImage: '${pageContext.request.contextPath}/pub/images/progressbar.gif', barImage: {0: '${pageContext.request.contextPath}/pub/images/progressbg_red.gif', 30: '${pageContext.request.contextPath}/pub/images/progressbg_orange.gif', 70: '${pageContext.request.contextPath}/pub/images/progressbg_green.gif'}});
                        }
                        $("#semakbtn" + rowNum).hide();
                    } else {
                        alert("opsie");
                    }
                }, 'html');
    }
    
    function hapus(id,idHm){
        var mh = document.getElementById('mohon'); 
        alert("Hapus ID Hakmilik : "+idHm+"?");
        doBlockUI();
        var url = "${pageContext.request.contextPath}/daftar/utiliti_tukarganti?delete&id="+id+"&idMohon="+mh.value;
        $.get(url,
        function(data){
            $('#ak').html(data);
            $.unblockUI();
        },'html');
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
    
    function doConfirm(msg, geranFn, pelanFn, bothFn, canFn) {
        var confirmBox = $("#confirmBox");
        confirmBox.find(".message").text(msg);
        confirmBox.find(".geran,.pelan,.both,.cancel").unbind().click(function() {
            confirmBox.hide();
        });
        confirmBox.find(".geran").click(geranFn);
        confirmBox.find(".pelan").click(pelanFn);
        confirmBox.find(".both").click(bothFn);
        confirmBox.find(".cancel").click(canFn);
        confirmBox.show();
    }
    
    function baki (){
        var form = this;
        var msg = "Sila pilih dokumen hakmilik yang ingin dijana semula?";
        doConfirm(msg, function geran() {
            $("#geran").click();
            doBlockUI();
        }, function pelan() {
            $("#pelan").click();
            doBlockUI();
        }, function both() {
            $("#semua").click();
            doBlockUI();
        }, function cancel() {
            return false;
        });
    }

</script>
<div class="subtitle displaytag" id="ak">
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:messages/>
<s:errors/>
<div align="center"><table style="width:100%" bgcolor="#00FFFF">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: 00FFFF;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">PENDAFTARAN: Utiliti Tukar Ganti</font>
                </div>
            </td>
        </tr>
    </table></div>
<c:if test="${!empty warning}">
    <div class="warning">
        <p>
            ${warning}<br/>
        </p>
    </div>        
</c:if>

    <s:form action="/daftar/utiliti_tukarganti" name="eload">
        <div id="confirmBox">
            <table border="0">
                <tr>
                    <td><img src='${pageContext.request.contextPath}/images/url.png'/></td>
                    <td><div class="message"><b>Sila pilih dokumen hakmilik yang ingin dijana semula?</b></div><br><br></td>
                </tr>
            </table>        
            &nbsp;&nbsp;&nbsp;
            <span class="geran" id="button">Geran Hakmilik</span>
            <span class="pelan" id="button">Pelan   Hakmilik</span>
            <span class="both" id="button">Geran dan Pelan</span>
            <span class="cancel" id="button">Batal</span>
        </div>
        
        <s:hidden name="permohonan.idPermohonan"/>       
        <s:hidden name="${actionBean.ku}"/>
        <s:hidden name="${actionBean.kodUrusan}"/> 
        <s:hidden name="${actionBean.hakmilikPermohonan}"/> 
        <s:text name="permohonan.idPermohonan" value="${actionBean.permohonan.idPermohonan}" style="display:none;" id="mohon"/> 

        <fieldset class="aras1">
            <p class="title">Urusan        : ${actionBean.ku.nama}</p>
            <p class="title">ID Permohonan : ${actionBean.permohonan.idPermohonan}</p>

            <p class=title>Langkah 4: Tandatangan Dokumen</p>
            <span style="font-weight:normal;color: black" class=instr>
                Sila semak terlebih dahulu sebelum tandatangan. Sila klik pada kotak untuk pilih dokumen untuk ditandatangani.
            </span>
            <div align="center">
                <display:table style="width:80%;" class="tablecloth" name="${actionBean.hakmilikPermohonan}" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="No" >
                        <c:out value="${line_rowNum}"/>
                    </display:column>
                    <display:column property="hakmilik.idHakmilik" title="Hakmilik" class="sign"/>
                    <%--display:column title="<input type='checkbox' id='semuaVdoc' name='semua' onclick='javascript:selectAll(this);' />VDOC / Draf DHDe">
                        <c:if test="${line.dokumen1.namaFizikal != null}">
                            <a href="#" id="p" onclick="doViewReport('${line.dokumen1.idDokumen}');
                            return false;">Papar</a>
                            <c:if test="${line.dokumen1.kodDokumen.kod eq 'VDOC'}">
                                <c:if test="${line_rowNum eq 1}">
                                    <input type="checkbox" id="vdoc${line_rowNum}"
                                           title="Sila klik untuk tandatangan ${line.dokumen1.kodDokumen.nama}"
                                           value="${line.dokumen1.idDokumen}"/>
                                </c:if>
                                <c:set var="path"/>
                                <c:forTokens delims="/" items="${line.dokumen1.namaFizikal}" var="items" begin="0" end="3">
                                    <c:set var="path" value="${path}/${items}"/>
                                </c:forTokens>
                                <input type="hidden" id="vdoc_path${line_rowNum}" value="${path}"/>
                            </c:if>
                        </c:if>
                    </display:column--%>
                    <display:column title="<input type='checkbox' id='semuaDHDe' name='semua' onclick='javascript:selectAll(this);' />DHDe" style="width:10%">
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
                    <display:column title="<input type='checkbox' id='semuaDHKe' name='semua' onclick='javascript:selectAll(this);' />DHKe " style="width:10%">
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
                    <%--c:if test="${actionBean.permohonan.kodUrusan.kod eq 'HKTHK' || actionBean.permohonan.kodUrusan.kod eq 'HSTHK' }">
                        <display:column title="DHDK SPTB" style="width:10%">
                            <c:if test="${line.dokumen4.namaFizikal != null}">

                                <a href="#" id="p" onclick="doViewReport('${line.dokumen4.idDokumen}');
                                        return false;">Papar</a>
                                <c:set var="path"/>
                                <c:forTokens delims="/" items="${line.dokumen4.namaFizikal}" var="items" begin="0" end="3">
                                    <c:set var="path" value="${path}/${items}"/>
                                </c:forTokens>
                                <input type="hidden" id="dhdk_path${line_rowNum}" value="${path}"/>
                            </c:if>
                        </display:column>
                    </c:if--%>
                    <!--N9 Only-->
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
                        <display:column title="<input type='checkbox' id='semuaPelan' name='semua' onclick='javascript:selectAll(this);' />Pelan B1 DHDe / B2 DHDe">
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
                        <display:column title="<input type='checkbox' id='semuaPelan2' name='semua' onclick='javascript:selectAll(this);' />Pelan B1 DHKe / B2 DHKe">
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
                <br/>

                <%-- xtau untuk apa.comment dlu kejap
                <c:if test="${fn:length(actionBean.listHakmilikBatal) > 0}" >
                    <fieldset class="aras1">
                        <br>
                        <legend>Senarai Hakmilik Yang Akan<em>DIBATALKAN</em></legend><br/>
                        <display:table style="width:50%;" class="tablecloth" 
                                       name="${actionBean.listHakmilikBatal}" 
                                       cellpadding="0" cellspacing="0" id="line2">
                            <display:column title="No" >
                                <c:out value="${line2_rowNum}"/>
                            </display:column>
                            <display:column property="idHakmilik" title="Hakmilik" class="sign"/>
                            <display:column title="DHDe <br/><input type='checkbox' id='semuaHakmilikBatal' 
                                            name='semua' onclick='javascript:selectAll(this);' />">           
                                <c:if test="${line2.dhde != null}">
                                    <a href="#" id="p" onclick="doViewReport('${line2.dhde.idDokumen}');
                                            return false;">Papar</a>
                                    <input type="checkbox" id="hakmilikBatal${line2_rowNum}"
                                           title="Sila klik untuk tandatangan ${line2.dhde.kodDokumen.nama}"
                                           value="${line2.dhde.idDokumen}"/>
                                    <c:set var="path"/>
                                    <c:forTokens delims="/" items="${line2.dhde.namaFizikal}" var="items" begin="0" end="3">
                                        <c:set var="path" value="${path}/${items}"/>
                                    </c:forTokens>
                                    <input type="hidden" id="hakmilikBatal_path${line2_rowNum}" value="${path}"/>
                                </c:if>
                            </display:column>
                        </display:table>      
                        <br/>
                    </fieldset>
                </c:if>--%>

                <c:if test="${fn:length(actionBean.hakmilikPermohonan) > 0 || fn:length(actionBean.senaraiHakmilikBatalUnique) > 0}">

                    <c:set var="prm" value="N"/>
                    <c:if test="${!empty multiple_sign_kekal}">
                        <c:set var="prm" value="Y"/>
                    </c:if>
                    <c:if test="${!empty multiple_sign_sementara}">
                        <c:set var="prm" value="S"/>
                    </c:if>

                    <c:forEach items="${actionBean.pguna.senaraiPeranan}" var="senarai">
                        <c:if test="${senarai.peranan.kod eq '4'}">
                            <s:button name="" id="" value="T/tangan" class="btn" onclick="doSignFile('${today}', '${prm}');"/>
                        </c:if>
                    </c:forEach>
                    <c:if test="${cetak}">
                        <s:button name="" value="Cetak" class="btn" onclick="doPrintViaApplet();"/>
                    </c:if>
                </c:if>
            </div>
        </fieldset>
        <div align="center"><table style="width:100%" bgcolor="#00FFFF">
                <tr>
                    <td align="right">
                        <s:submit name="Step9" value="Kembali" class="btn" />              
                        <s:button name="" value="Jana Semula" class="btn" onclick="return baki();"/>
                        <s:submit name="Step8" value="Jana Semua" class="btn" id="semua" style="display:none;"/>
                        <s:submit name="Step8a" value="Jana Pelan" class="btn" id="pelan" style="display:none;"/>
                        <s:submit name="Step8b" value="Jana Geran" class="btn" id="geran" style="display:none;"/>
                        <c:forEach items="${actionBean.pguna.senaraiPeranan}" var="senarai">
                            <c:if test="${senarai.peranan.kod eq '4'}">
                                <s:submit name="Step6" value="Daftar" class="btn" onclick="return validate(this.form);"/>
                            </c:if>
                        </c:forEach>
                    </td>
                </tr>
            </table></div>
        </s:form>

    <applet code="PrintApplet.class" ARCHIVE="${pageContext.request.contextPath}/PrintApplet.jar,
            ${pageContext.request.contextPath}/commons-httpclient-2.0.2.jar,
            ${pageContext.request.contextPath}/commons-logging.jar,
            ${pageContext.request.contextPath}/swingx-1.6.jar,
            ${pageContext.request.contextPath}/log4j-1.2.12.jar,
            ${pageContext.request.contextPath}/jpedal_trial.jar"
            codebase = "."
            name     = "applet2"
            id       = "applet2"
            width    = "1"
            height   = "1"
            align    = "middle">
        <param name="uploadAction" value="<%=request.getContextPath()%>/PrintServlet"/>
        <param name ="disabledWatermark" value="true"/>
        <param name ="method" value="pdfp">
        <%
            Cookie[] cookies = request.getCookies();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < cookies.length; i++) {
                sb.setLength(0);
                sb.append(cookies[i].getName());
                sb.append("=");
                sb.append(cookies[i].getValue());
        %>
        <param name="Cookie<%= i%>" value="<%= sb.toString()%>"><%
            }
        %>
    </applet>

</div>

