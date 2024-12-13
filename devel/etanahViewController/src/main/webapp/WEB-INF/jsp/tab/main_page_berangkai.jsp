<%--
    Document   : main_page_berangkai
    Created on : Jan 26, 2010, 4:19:43 PM
    Author     : fikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/jquery.tooltip.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.tools.min.js?select=full&debug=true"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.7.3.custom.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#hantar2").attr("disabled", "false");
        var fr = document.form1;
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
        //$('#semakSemula').attr("disabled","true");
    <c:if test="${!actionBean.documentGenerated}">
        $('.hantar').attr("disabled", "true");
    </c:if>

    <c:if test="${actionBean.documentGenerated}">
            $('.hantar').removeClass('disablebtn');
            $('.hantar').addClass('btn');
    </c:if>

            $("#dialog-form").dialog({
                autoOpen: false,
                height: 250,
                width: 350,
                modal: true,
                buttons: {
                    'Ok': function() {
                        var f = document.form1;
                        var eve = $('#event').val();
                        doSend('', fr, 'PUSHBACK');
                        $(this).dialog('close');
                    },
                    'Tutup': function() {
                        $(this).dialog('close');
                    }
                }
            });

            $("#dialog-form2").dialog({
                autoOpen: false,
                height: 150,
                width: 250,
                modal: true,
                draggable: false,
                open: function(event, ui) {
                    setTimeout(function() {
                        $('.ui-state-focus').each(function() {
                            $(this).blur();
                        });
                    }, 1);
                },
                buttons: {
                    'Tutup': function() {
                        $(this).dialog('close');
                    },
                    'Ok': function() {
                        doSend('', fr, 'APPROVE');
                        $(this).dialog('close');
                    }

                }
            });

            $('#semakSemula')
                    .click(function() {
                $("#event").val("PUSHBACK");
                $('#dialog-form').dialog('open');
            });


            $('#hantar2')
                    .click(function() {
                $("#event").val("APPROVE");
                $('#dialog-form2').dialog('open');
            });

        });
        function doViewReport(v) {
            var randomnumber = Math.floor((Math.random() * 100) + 1);
            var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
            window.open(url, randomnumber, "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }

        function  doGenReport(f) {
            var url = '${pageContext.request.contextPath}/stage?genReportBerkumpulan';
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });
            f.action = url;
            f.submit();
        }

        function toSenaraiTugasan(f) {
            var url = '${pageContext.request.contextPath}/senaraiTugasan';
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });
            f.action = url;
            f.submit();
        }

        function doSend(event, frm, e) {
            var msg;
            //frm.action = frm.action + '?' + event;
            if (confirm('Adakah anda pasti? Tugasan akan dihantar ke peringkat seterusnya.')) {
                $.blockUI({
                    message: $('#displayBox'),
                    css: {
                        top: ($(window).height() - 50) / 2 + 'px',
                        left: ($(window).width() - 50) / 2 + 'px',
                        width: '50px'
                    }
                });
                var url = '${pageContext.request.contextPath}/stage?nextStage&event=' + e;
                if (e === 'PUSHBACK') {
                    var u = $('#mesej').val();
                    url = url + '&mesej=' + u;
                }
                $.get(url,
                        function(data) {
                            if (data == '1') {
                                msg = 'Tugasan tidak dapat dihantar ke peringkat seterusnya. Sila hubungi Pentadbir Sistem';
                                frm.action = '${pageContext.request.contextPath}/senaraiTugasan?error=' + msg;
                                frm.submit();
                            } else if (data == '2') {
                                alert('Sila buat keputusan terlebih dahulu.');
                                //$('#status').click();
                            } else if (data == '3') {
                                msg = 'Terdapat urusan sebelum yang masih belum selesai.';
                                frm.action = '${pageContext.request.contextPath}/senaraiTugasan?error=' + msg;
                                frm.submit();
                            } else {
                                frm.action = '${pageContext.request.contextPath}/' + data;
                                frm.submit();
                            }
                            $.unblockUI();
                        });
            }
        }

        function doPrintViaApplet(docId) {
            //alert('tsttt');
            var a = document.getElementById('applet');
            //a.printDocument(docId.toString());
            a.doPrint(docId.toString());
        }

        function doPrintMultiple() {
            var FILE_TO_PRINT = '';
            var DELIM = ',';

            $('.sign').each(function(index) {
                index = index + 1;
                if ($('#VDOC' + index).is(':checked')) {
                    if (FILE_TO_PRINT == '') {
                        FILE_TO_PRINT = $('#VDOC' + index).val();
                    } else {
                        FILE_TO_PRINT = FILE_TO_PRINT + DELIM + $('#VDOC' + index).val();
                    }
                }
                if ($('#DHKE' + index).is(':checked')) {
                    if (FILE_TO_PRINT == '') {
                        FILE_TO_PRINT = $('#DHKE' + index).val();
                    } else {
                        FILE_TO_PRINT = FILE_TO_PRINT + DELIM + $('#DHKE' + index).val();
                    }
                }

                if ($('#DHDE' + index).is(':checked')) {
                    if (FILE_TO_PRINT == '') {
                        FILE_TO_PRINT = $('#DHDE' + index).val();
                    } else {
                        FILE_TO_PRINT = FILE_TO_PRINT + DELIM + $('#DHDE' + index).val();
                    }
                }
                if ($('#PELAN' + index).is(':checked')) {
                    if (FILE_TO_PRINT == '') {
                        FILE_TO_PRINT = $('#PELAN' + index).val();
                    } else {
                        FILE_TO_PRINT = FILE_TO_PRINT + DELIM + $('#PELAN' + index).val();
                    }
                }
                if ($('#PELAN_2_' + index).is(':checked')) {
                    if (FILE_TO_PRINT == '') {
                        FILE_TO_PRINT = $('#PELAN_2_' + index).val();
                    } else {
                        FILE_TO_PRINT = FILE_TO_PRINT + DELIM + $('#PELAN_2_' + index).val();
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

            if (FILE_TO_PRINT !== '') {
                var a = document.getElementById('applet');
                a.doPrint(FILE_TO_PRINT);
                //a.printDocument(FILE_TO_PRINT);
            } else {
                alert('Sila Pilih Dokumen untuk dicetak.');
            }
        }

        function doSignFile(date) {

            //alert("flag:"+flag);
            //var today = new Date();
            //alert(today.getDate() + '/' + (today.getMonth() + 1) + '/' + today.getYear());

            var today = new Date();
            var DELIM = "|";
            var parameterToSign = '';
            var v_file = '';
            var dhde_file = '';
            var dhke_file = '';
            var b_file = '';
            var batal_file = '';

            $('.sign').each(function(index) {
                index = index + 1;
                if ($('#VDOC' + index).is(':checked')) {
                    v_file = $('#VDOC' + index).val() + '#' + $('#VDOC_path' + index).val() + '#vdoc#N';
                    if (parameterToSign != '') {
                        parameterToSign = parameterToSign + DELIM + v_file;
                    } else {
                        parameterToSign = v_file;
                    }
                }

                if ($('#DHKE' + index).is(':checked')) {
                    dhke_file = $('#DHKE' + index).val() + '#' + $('#DHKE_path' + index).val() + '#dhke#N';
                    if (parameterToSign != '') {
                        parameterToSign = parameterToSign + DELIM + dhke_file;
                    } else {
                        parameterToSign = dhke_file;
                    }
                }

                if ($('#DHDE' + index).is(':checked')) {
                    dhde_file = $('#DHDE' + index).val() + '#' + $('#DHDE_path' + index).val() + '#dhde#N';
                    if (parameterToSign != '') {
                        parameterToSign = parameterToSign + DELIM + dhde_file;
                    } else {
                        parameterToSign = dhde_file;
                    }
                }

                if ($('#PELAN' + index).is(':checked')) {
                    b_file = $('#PELAN' + index).val() + '#' + $('#PELAN_path' + index).val() + '#b1#N';
                    if (parameterToSign != '') {
                        parameterToSign = parameterToSign + DELIM + b_file;
                    } else {
                        parameterToSign = b_file;
                    }
                }

                if ($('#PELAN_2_' + index).is(':checked')) {
                    b_file = $('#PELAN_2_' + index).val() + '#' + $('#PELAN_path_2_' + index).val() + '#b1#N';
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
                var signer = new ActiveXObject("SAPDFSigner.Form1");
                signer.SigningPDFFile(parameterToSign, today);
                if (signer.getValue() != "") {
                    document.eload.message.value = signer.getValue();
                }
            } else {
                alert('Sila Pilih Dokumen untuk ditandatangan.');
            }
        }

        function signMultipleFiles(txtJawatan) {
            var DELIM = "|";
            var docToSign = '';
            var folderToSign = '';
            var flag = false;
            $('.sign').each(function(index) {
                index = index + 1;
                if ($('#VDOC' + index).is(':checked')) {
                    if (docToSign != '') {
                        docToSign = docToSign + DELIM + $('#VDOC' + index).val();
                    } else {
                        docToSign = $('#VDOC' + index).val();
                    }

                    if (folderToSign != '') {
                        //folderToSign = folderToSign + DELIM + folderId;
                        folderToSign = folderToSign + DELIM + $('#VDOC_path' + index).val();
                    } else {
                        folderToSign = $('#VDOC_path' + index).val();
                    }
                    flag = true;
                }
                else {
                    docToSign = docToSign + DELIM;
                    folderToSign = folderToSign + DELIM;
                }

                if ($('#PELAN' + index).is(':checked')) {
                    if (docToSign != '') {
                        docToSign = docToSign + DELIM + $('#PELAN' + index).val();
                    } else {
                        docToSign = $('#PELAN' + index).val();
                    }

                    if (folderToSign != '') {
                        folderToSign = folderToSign + DELIM + $('#PELAN_path' + index).val();
                    } else {
                        folderToSign = $('#PELAN_path' + index).val();
                        ;
                    }
                    flag = true;
                } else {
                    docToSign = docToSign + DELIM;
                    folderToSign = folderToSign + DELIM
                }

                if ($('#DHKE' + index).is(':checked')) {
                    if (docToSign != '') {
                        docToSign = docToSign + DELIM + $('#DHKE' + index).val();
                    } else {
                        docToSign = $('#DHKE' + index).val();
                    }

                    if (folderToSign != '') {
                        folderToSign = folderToSign + DELIM + $('#DHKE_path' + index).val();
                        ;
                    } else {
                        folderToSign = $('#DHKE_path' + index).val();
                        ;
                    }
                    flag = true;
                } else {
                    docToSign = docToSign + DELIM;
                    folderToSign = folderToSign + DELIM
                }


                if ($('#DHDE' + index).is(':checked')) {
                    if (docToSign != '') {
                        docToSign = docToSign + DELIM + $('#DHDE' + index).val();
                    } else {
                        docToSign = $('#DHDE' + index).val();
                    }

                    if (folderToSign != '') {
                        folderToSign = folderToSign + DELIM + $('#DHDE_path' + index).val();
                        ;
                    } else {
                        folderToSign = $('#DHDE_path' + index).val();
                        ;
                    }
                    flag = true;
                } else {
                    docToSign = docToSign + DELIM;
                    folderToSign = folderToSign + DELIM
                }
            });
            //alert(docToSign);
            //alert(folderToSign);
            if (flag) {
                var signer = new ActiveXObject("SAPDFSigner.Form1");
                signer.SigningPDFFile(docToSign, folderToSign, txtJawatan);
                if (signer.getValue() != "") {
                    document.eload.message.value = signer.getValue();
                }
            } else {
                alert('Sila Pilih Dokumen untuk ditandatangan.');
            }
        }

        function doQueryTask(val) {

            $('#tugasan').html('');
            $('#loading-img').show();
            var nama = $('#id_pguna option:selected').text();

            var url = '${pageContext.request.contextPath}/agihTugasan?query&idPengguna=' + val;
            $.get(url,
                    function(data) {
                        $('#loading-img').hide();
                        $('#tugasan').html(nama + ' mempunyai ' + data + ' tugasan.');
                    }
            );
        }

        /*function selectAll(a){
         var len = $('.cetakSemua').length;        
         for (var i=0; i<len; i++) {            
         //var c = document.getElementById("sainSemua" + i);                        
         //c.checked = a.checked;            
         if (a.checked) {
         $('#sainSemua' + i).attr('checked','checked');
         }else {
         $('#sainSemua' + i).removeAttr('checked');
         }
         }
         }*/

        function selectAll(a) {
            var len = $('.sign').length + 1;
            for (var i = 0; i < len; i++) {
                //var c = document.getElementById("sainSemua" + i);                        
                //c.checked = a.checked;            
                if (a.checked) {
                    $('#DHDE' + i).attr('checked', 'checked');
                    $('#DHKE' + i).attr('checked', 'checked');
                    $('#VDOC' + i).attr('checked', 'checked');
                } else {
                    $('#DHDE' + i).removeAttr('checked');
                    $('#DHKE' + i).removeAttr('checked');
                    $('#VDOC' + i).removeAttr('checked');

                }
            }
        }

        function nextFunc(caw) {

            if ($('#tik').is(':unchecked')) {
                $('#hantar2').removeAttr("disabled");
            }
            else if ($('#tik').is(':checked')) {
                $('#hantar2').removeAttr("false");
            }
        }
</script>
<s:messages/>
<s:errors/>

<div id="dialog-form" title="Sila masukkan mesej" style="display: none" class="subtitle">
    <s:form beanclass="etanah.view.stripes.StageActionBean">            
        <s:textarea name="mesej" cols="38" rows="10" id="mesej"/>
        <s:hidden name="id" id="event"/>
    </s:form>
</div>

<div id="dialog-form2" title="Amaran" style="display: none" class="subtitle">
    <s:form beanclass="etanah.view.stripes.StageActionBean">
        <s:hidden name="id" id="event"/>        
        <span>
            Buat Keputusan Dengan Bijak!! <br/>Anda yakin dengan keputusan ini?
        </span>

    </s:form>
</div>    

<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display:none"/>
<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Senarai Perserahan Berkumpulan
        </legend>
        <br/>
        <p><em>
                <c:choose>
                    <c:when test="${distribute}">
                        Sila agih tugasan ke peringkat seterusnya.
                    </c:when>
                    <c:otherwise>
                        Sila tekan pada urusan untuk kemasukan data dan keputusan.
                        Sila pastikan semua urusan telah dikemaskini sebelum hantar.
                        <br/>Butang hantar akan menghantar semua tugasan ke peringkat seterusnya.     

                    </c:otherwise>
                </c:choose>
            </em>
        </p>
        <div class="content" align="center">
            <display:table class="tablecloth" name="${actionBean.permohonanBerangkai}"
                           cellpadding="0" cellspacing="0" id="line">
                <display:column title="No" sortable="true">${line_rowNum}</display:column>
                <display:column property="idPermohonan" title="ID Perserahan" class="popup"/>
                <c:choose>
                    <c:when test="${!distribute}" >
                        <display:column title="Urusan" class="popup">
                            <a href="${pageContext.request.contextPath}/workflow/kernal_action?detail=true&idPermohonan=${line.idPermohonan}&size_berangkai=${fn:length(actionBean.senaraiKandungan)}">${line.kodUrusan.nama}</a>
                        </display:column>
                    </c:when>
                    <c:otherwise>
                        <display:column property="kodUrusan.nama" title="Urusan"/>
                    </c:otherwise>
                </c:choose>
                <display:column title="Tarikh Perserahan">
                    <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm:ss"/>
                </display:column>
                <display:column title="Telah Dikemaskini?">
                    <c:forEach items="${actionBean.isUrusanBerangkaiDone}" var="item" varStatus="line2">
                        <c:if test="${line2.count eq line_rowNum}">
                            <c:if test="${item}">
                                <p align="center">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/KnobValidGreen.png" width="20" height="20"/>
                                </p>
                            </c:if>
                        </c:if>
                    </c:forEach>
                </display:column>
                <!--display:column property="keputusan.nama" title="Keputusan"/-->
            </display:table>
        </div>
    </fieldset>
</div>
<br/>
<c:if test="${fn:length(actionBean.senaraiKandungan) > 0}">
    <fieldset class="aras1">
        <legend>
            Senarai Dokumen Bagi Perserahan Berkumpulan
        </legend>
        <div class="content" align="center">
            <c:set var="sign" value="true"/>
            <display:table name="${actionBean.senaraiKandungan}" class="tablecloth" id="row">
                <c:if test="${row.dokumen.kodDokumen.kod eq 'DHKK' || row.dokumen.kodDokumen.kod eq 'DHDK'}">
                    <c:set var="sign" value="false"/>
                </c:if>
                <%--c:if test="${finalStage}"--%>
                <c:if test="${
                      row.dokumen.kodDokumen.kod eq 'VDOC'
                          || row.dokumen.kodDokumen.kod eq 'DHKE'
                          || row.dokumen.kodDokumen.kod eq 'DHDE'
                      }">
                    <display:column title="<input type='checkbox'  name='semua' onclick='javascript:selectAll(this);' />" class="sign">
                        <input type="checkbox" id="${row.dokumen.kodDokumen.kod}${row_rowNum}"
                               title="Sila klik untuk tandatangan ${row.dokumen.kodDokumen.nama}"
                               value="${row.dokumen.idDokumen}"/>
                        <c:set var="path"/>
                        <c:forTokens delims="/" items="${row.dokumen.namaFizikal}" var="items" begin="0" end="3">
                            <c:set var="path" value="${path}/${items}"/>
                        </c:forTokens>
                        <input type="hidden" id="${row.dokumen.kodDokumen.kod}_path${row_rowNum}" value="${path}"/>                      
                    </display:column>
                </c:if>
                <c:if test="${
                      row.dokumen.kodDokumen.kod ne 'VDOC'
                          && row.dokumen.kodDokumen.kod ne 'DHKE'
                          && row.dokumen.kodDokumen.kod ne 'DHDE'
                      }">
                    <display:column>&nbsp;</display:column>
                </c:if>
                <%--/c:if--%>
                <display:column title="Kod Dokumen" property="dokumen.kodDokumen.kod" />
                <display:column title="Nama Dokumen" property="dokumen.kodDokumen.nama" />
                <display:column title="Klasifikasi" property="dokumen.klasifikasi.nama" />
                <display:column title="Dimasuk Oleh" property="dokumen.infoAudit.dimasukOleh.nama" />
                <display:column title="Catatan" property="dokumen.tajuk" />
                <display:column title="Tarikh" property="dokumen.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                <display:column title="Tindakan">
                    <c:if test="${row.dokumen.namaFizikal != null}">
                        <%--<a href="#" onclick="doViewReport('${row.dokumen.idDokumen}');">Papar</a>--%>
                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                             onclick="doViewReport('${row.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                             onmouseover="this.style.cursor = 'pointer';" title="Papar ${row.dokumen.kodDokumen.nama}"/>
                        <c:if test="${fn:length(row.dokumen.senaraiCapaian) == 0}">
                            <img src="${pageContext.request.contextPath}/pub/images/baharu.gif"/>
                        </c:if>
                    </c:if>
                </display:column>
                <display:column title="Cetak">
                    <c:if test="${row.dokumen.namaFizikal != null}">
                        <%--<input type="button" name="cetak" value="Cetak" onclick="doPrintViaApplet('${row.dokumen.idDokumen}');" class="btn"/>--%>
                        <c:if test="${cetak}">
                            <p align="center">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                     onclick="doPrintViaApplet('${row.dokumen.idDokumen}');" height="30" width="30" alt="cetak"
                                     onmouseover="this.style.cursor = 'pointer';" title="Cetak ${row.dokumen.kodDokumen.nama}"/>
                            </p>
                        </c:if>
                        <c:if test="${empty cetak && row.dokumen.kodDokumen.kod ne 'DHKE'
                                      && row.dokumen.kodDokumen.kod ne 'DHDE'}">
                              <p align="center">
                                  <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                       onclick="doPrintViaApplet('${row.dokumen.idDokumen}');" height="30" width="30" alt="cetak"
                                       onmouseover="this.style.cursor = 'pointer';" title="Cetak ${row.dokumen.kodDokumen.nama}"/>
                              </p>
                        </c:if>

                    </c:if>
                </display:column>
            </display:table>
            <c:if test="${finalStage && sign eq 'true'}">
                <p>&nbsp;</p> 
                <input type="button" name="" id="" value="T/tangan" class="btn" onclick="doSignFile('${now}');"/>

                <%--<s:button name="" id="" value="T/tangan" class="btn" onclick="doSignFile();"/>--%>
            </c:if>
            <c:if test="${cetak}">
                <input type="button" name="" value="Cetak" class="btn" onclick="doPrintMultiple();"/>
            </c:if>
        </div>      
    </fieldset>

    <c:if test="${finalStage}">
        <div align="right" >
            <br> <input type="radio" name="tik" id="tik" onchange="nextFunc()"/>
            &nbsp;Saya memperakui bahawa saya telah menyemak kesemua dokumen-dokumen yang telah disediakan<br> bagi perserahan ini sebelum saya membuat keputusan bagi urusan-urusan yang terlibat. 
            </p>
        </div>
    </c:if>



    <applet code="PrintApplet.class" ARCHIVE="${pageContext.request.contextPath}/PrintApplet.jar,
            ${pageContext.request.contextPath}/commons-httpclient-2.0.2.jar,
            ${pageContext.request.contextPath}/commons-logging.jar,
            ${pageContext.request.contextPath}/swingx-1.6.jar,
            ${pageContext.request.contextPath}/log4j-1.2.12.jar,
            ${pageContext.request.contextPath}/jpedal_demo.jar"
            codebase = "."
            name     = "applet"
            id       = "applet"
            width    = "1"
            height   = "1"
            align    = "middle">
        <param name="uploadAction" value="<%=request.getContextPath()%>/PrintServlet"/>
        <param name ="kodNegeri" value="05">
        <param name ="method" value="pdfp">
        <param name ="disabledWatermark" value="true"/>
        <param name ="idPengguna" value="${idPengguna}"/>
        <%
       Cookie[] cookies = request.getCookies();
       StringBuffer sb = new StringBuffer();
       for (int i =0; i < cookies.length; i++) {
          sb.setLength(0);
          sb.append(cookies[i].getName());
          sb.append("=");
          sb.append(cookies[i].getValue());
        %>
        <param name="Cookie<%= i %>" value="<%= sb.toString() %>"><%
      }
        %>
    </applet>
</c:if>
<br/>
<c:choose>
    <c:when test="${distribute}" >
        <s:form beanclass="etanah.view.stripes.AgihTugasanActionBean"  name="form1">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Agihan Tugas
                    </legend>
                    <p><label>Hantar Kepada :</label>
                        <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna" onchange="doQueryTask(this.value);">
                            <s:option value="">-- Sila Pilih --</s:option>
                            <%--<s:options-collection collection="${actionBean.listPp}" value="idPengguna" label="nama" />--%>
                            <c:forEach items="${actionBean.listPp}" var="item">
                                <s:option value="${item.idPengguna}">${item.idPengguna} - ${item.nama}</s:option>
                            </c:forEach>
                        </s:select>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <img src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img"/>
                        <em id="tugasan"/>
                    </p>
                    <br/>
                    <p>
                        <label>&nbsp;</label>
                        <s:button name="nextStage" value="Senarai Tugasan" class="longbtn" onclick="toSenaraiTugasan(this.form);"/>&nbsp;
                        <s:submit name="agihPT" value="Agih" class="btn"/>
                    </p>
                </fieldset>
            </div>
        </s:form>
    </c:when>
    <c:otherwise>
        <c:if test="${empty actionBean.perluConvert}">
            <s:form beanclass="etanah.view.stripes.StageActionBean" name="form1">
                <s:hidden name="stageId"/>
                <div align="right" style="background-color:${actionBean.warnaModul}">
                    <s:button name="nextStage" value="Senarai Tugasan" class="longbtn" onclick="toSenaraiTugasan(this.form);"/>&nbsp;
                    <c:if test="${actionBean.report && finalStage}">
                        <s:button name="genReport" value="Jana Dokumen" class="longbtn" onclick="doGenReport(this.form);"/>&nbsp;
                    </c:if>

                    <c:set value="Hantar Pendaftar" var="selesaiTitle"/>

                    <c:if test="${finalStage}">
                        <c:set value="DAFTAR" var="selesaiTitle"/>
                    </c:if>

                    <c:choose>
                        <c:when test="${finalStage}">
                            <s:button name="nextStage" id="hantar2" value="${selesaiTitle}" class="disablebtn hantar"/>&nbsp;
                        </c:when>
                        <c:otherwise>
                            <s:button name="nextStage" id="hantar" value="${selesaiTitle}" class="disablebtn hantar" onclick="doSend(this.name, this.form,'APPROVE');"/>&nbsp;
                        </c:otherwise>
                    </c:choose>
                    <%--s:button name="nextStage" id="hantar" value="${selesaiTitle}" class="longbtn" onclick="doSend(this.name, this.form, 'APPROVE');"/--%>&nbsp;
                    <c:if test="${actionBean.isPushBack}">
                        <%--s:button name="prevStage" id="semakSemula" value="Semak Semula" class="btn" onclick="doSend(this.name, this.form, 'PUSHBACK');"/>&nbsp;&nbsp;&nbsp --%>
                        <s:button name="prevStage" id="semakSemula" value="Semak Semula" class="btn" /> &nbsp;&nbsp;&nbsp;
                    </c:if>
                </div>
                <br>
            </s:form>
        </c:if> 
    </c:otherwise>
</c:choose>
