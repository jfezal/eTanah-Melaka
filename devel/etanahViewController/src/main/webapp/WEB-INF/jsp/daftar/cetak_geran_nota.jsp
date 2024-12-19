<%-- 
    Document   : bypass_keutamaan
    Created on : Apr 6, 2010, 9:39:45 AM
    Author     : fikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%--<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    function doSubmit(frm) {
        var msg;
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });
        $.ajax({
            type: "GET",
            url: '${pageContext.request.contextPath}/stage?nextStage&bypass=true',
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                //alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                msg = xhr.statusText + '<br/>Tugasan tidak dapat dihantar ke peringkat seterusnya. Sila hubungi Pentadbir Sistem';
                frm.action = '${pageContext.request.contextPath}/senaraiTugasan?error=' + msg;
                frm.submit();
            },
            success: function(data) {
                if (data == '1') {
                    msg = 'Tugasan tidak dapat dihantar ke peringkat seterusnya. Sila hubungi Pentadbir Sistem';
                    frm.action = '${pageContext.request.contextPath}/senaraiTugasan?error=' + msg;
                    frm.submit();
                } else if (data == '2') {
                    alert('Sila buat keputusan terlebih dahulu.');
                    $('#status').click();
                } else if (data == '3') {
                    msg = 'Terdapat urusan sebelum yang masih belum selesai.';
                    frm.action = '${pageContext.request.contextPath}/senaraiTugasan?error=' + msg;
                    frm.submit();
                } else {
                    frm.action = '${pageContext.request.contextPath}/' + data;
                    frm.submit();
                }
            }
        });
    }
    function goToSenaraiTugasan(frm) {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });
        frm.action = '${pageContext.request.contextPath}/senaraiTugasan';
        frm.submit();
    }

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
            if ($('#surat' + index).is(':checked')) {
                if (FILE_TO_PRINT == '') {
                    FILE_TO_PRINT = $('#surat' + index).val();
                } else {
                    FILE_TO_PRINT = FILE_TO_PRINT + DELIM + $('#surat' + index).val();
                }
            }
            if ($('#notis19F' + index).is(':checked')) {
                if (FILE_TO_PRINT == '') {
                    FILE_TO_PRINT = $('#notis19F' + index).val();
                } else {
                    FILE_TO_PRINT = FILE_TO_PRINT + DELIM + $('#notis19F' + index).val();
                }
            }
            if ($('#notis19A' + index).is(':checked')) {
                if (FILE_TO_PRINT == '') {
                    FILE_TO_PRINT = $('#notis19A' + index).val();
                } else {
                    FILE_TO_PRINT = FILE_TO_PRINT + DELIM + $('#notis19A' + index).val();
                }
            }
            if ($('#notis19C' + index).is(':checked')) {
                if (FILE_TO_PRINT == '') {
                    FILE_TO_PRINT = $('#notis19C' + index).val();
                } else {
                    FILE_TO_PRINT = FILE_TO_PRINT + DELIM + $('#notis19C' + index).val();
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

    function selectAll(a) {
        var id = a.id;
        var len = $('.sign').length;
        //alert(len);
        //alert(id);
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
            } else if (id == 'semuaSurat') {
                var c = document.getElementById("surat" + i);
                c.checked = a.checked;
            }
            else if (id == 'semuaNotis19F') {
                var c = document.getElementById("notis19F" + i);
                c.checked = a.checked;
            }
            else if (id == 'semuaNotis19A') {
                var c = document.getElementById("notis19A" + i);
                c.checked = a.checked;
            }
            else if (id == 'semuaNotis19C') {
                var c = document.getElementById("notis19C" + i);
                c.checked = a.checked;
            }
            
        }
    }

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:messages/>
<s:form action="/senaraiTugasan">
    <div class="content">
        <fieldset class="aras1">
            <legend>Cetak Dokumen</legend> 
            <p align="center"> <label>&nbsp;</label>
                <display:table style="width:80%;" class="tablecloth" name="${actionBean.senaraiHakmilikTerlibat}" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="No" >
                        <c:out value="${line_rowNum}"/>
                    </display:column>
                    <display:column property="hakmilik.idHakmilik" title="Hakmilik" class="sign"/>

                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'HT' && actionBean.permohonan.kodUrusan.kod ne 'HTSC' && actionBean.permohonan.kodUrusan.kod ne 'HTSPB'}">


                        <display:column title="<input type='checkbox' id='semuaVdoc' name='semua' onclick='javascript:selectAll(this);' />VDOC / Draf DHDe">
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
                        </display:column>

                        <c:if test="${actionBean.groupFlag eq '1'
                                                  or actionBean.groupFlag eq '2'}">

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
                            <c:if test="${actionBean.groupFlag eq '2'}">
                                <display:column title="<input type='checkbox' id='semuaDHKe' name='semua' onclick='javascript:selectAll(this);' />DHKe " style="width:10%">
                                    <c:if test="${line.dokumen2.namaFizikal != null}">
                                        <a href="#" id="p" onclick="doViewReport('${line.dokumen2.idDokumen}');
        return false;">Papar</a>
                                        <input type="checkbox" id="dhke${line_rowNum}"
                                               title="Sila klik untuk tandatangan ${line.dokumen2.kodDokumen.nama}"
                                               value="${line.dokumen2.idDokumen}"/>
                                        <c:set var="path"/>
                                        <c:forTokens delims="/" items="${line.dokumen2.namaFizikal}" var="items" begin="0" end="3">
                                            <c:set var="path" value="${path}/${items}"/>
                                        </c:forTokens>
                                        <input type="hidden" id="dhke_path${line_rowNum}" value="${path}"/>
                                    </c:if>
                                </display:column>
                            </c:if>
                        </c:if>
                    </c:if>

                    <c:if test="${!(actionBean.permohonan.kodUrusan.kod eq 'HKTHK' || actionBean.permohonan.kodUrusan.kod eq 'HSTHK') }">
                        <display:column title="<input type='checkbox' id='semuaSurat' name='semua' onclick='javascript:selectAll(this);' />Surat Daftar/ Tolak / Gantung / Notis 5F" style="width:20%">
                            <c:if test="${line.dokumen4.namaFizikal != null}">
                                <a href="#" id="p" onclick="doViewReport('${line.dokumen4.idDokumen}');
        return false;">Papar</a>
                                <c:if test="${line_rowNum eq 1}">
                                    <input type="checkbox" id="surat${line_rowNum}"
                                           title="Sila klik untuk tandatangan ${line.dokumen4.kodDokumen.nama}"
                                           value="${line.dokumen4.idDokumen}"/>
                                </c:if>
                                
                                <c:forTokens delims="/" items="${line.dokumen4.namaFizikal}" var="items" begin="0" end="3">
                                    <c:set var="path" value="${path}/${items}"/>
                                </c:forTokens>
                                <input type="hidden" id="surat_path${line_rowNum}" value="${path}"/>
                            </c:if>
                        </display:column>
                    </c:if>
                    <!--apool-->
                    <!--Start: Notis 19A/19F/19C-->
                    <c:if test="${(line.dokumen5 != null) && fn:contains(line.dokumen5.kodDokumen.kod, '19F')}">
                        <display:column title="<input type='checkbox' id='semuaNotis19F' name='semua' onclick='javascript:selectAll(this);' />Notis 19F" style="width:10%">
                            <c:if test="${line.dokumen5.namaFizikal != null}">
                                <a href="#" id="p" onclick="doViewReport('${line.dokumen5.idDokumen}');
        return false;">Papar</a>
                                <c:if test="${line_rowNum eq 1}">
                                    <input type="checkbox" id="notis19F${line_rowNum}"
                                           title="Sila klik untuk tandatangan ${line.dokumen5.kodDokumen.nama}"
                                           value="${line.dokumen5.idDokumen}"/>
                                </c:if>
                                <c:forTokens delims="/" items="${line.dokumen5.namaFizikal}" var="items" begin="0" end="3">
                                    <c:set var="path" value="${path}/${items}"/>
                                </c:forTokens>
                                <input type="hidden" id="notis19F_path${line_rowNum}" value="${path}"/>
                            </c:if>
                        </display:column>
                    </c:if>
                    <c:if test="${(line.dokumen6 != null) && fn:contains(line.dokumen6.kodDokumen.kod,'19A')}">
                        <display:column title="<input type='checkbox' id='semuaNotis19A' name='semua' onclick='javascript:selectAll(this);' />Notis 19A" style="width:10%">
                            <c:if test="${line.dokumen6.namaFizikal != null}">
                                <a href="#" id="p" onclick="doViewReport('${line.dokumen6.idDokumen}');
        return false;">Papar</a>
                                <c:if test="${line_rowNum eq 1}">
                                    <input type="checkbox" id="notis19A${line_rowNum}"
                                           title="Sila klik untuk tandatangan ${line.dokumen6.kodDokumen.nama}"
                                           value="${line.dokumen6.idDokumen}"/>
                                </c:if>
                                <c:forTokens delims="/" items="${line.dokumen6.namaFizikal}" var="items" begin="0" end="3">
                                    <c:set var="path" value="${path}/${items}"/>
                                </c:forTokens>
                                <input type="hidden" id="notis19A_path${line_rowNum}" value="${path}"/>
                            </c:if>
                        </display:column>
                    </c:if>
                    <c:if test="${(line.dokumen6 != null) && fn:contains(line.dokumen6.kodDokumen.kod,'19C')}">
                        <display:column title="<input type='checkbox' id='semuaNotis19C' name='semua' onclick='javascript:selectAll(this);' />Notis 19C" style="width:10%">
                            <c:if test="${line.dokumen6.namaFizikal != null}">
                                <a href="#" id="p" onclick="doViewReport('${line.dokumen6.idDokumen}');return false;">Papar</a>
                                <c:if test="${line_rowNum eq 1}">
                                    <input type="checkbox" id="notis19C${line_rowNum}"
                                           title="Sila klik untuk tandatangan ${line.dokumen6.kodDokumen.nama}"
                                           value="${line.dokumen6.idDokumen}"/>
                                </c:if>
                                <c:forTokens delims="/" items="${line.dokumen6.namaFizikal}" var="items" begin="0" end="3">
                                    <c:set var="path" value="${path}/${items}"/>
                                </c:forTokens>
                                <input type="hidden" id="notis19C_path${line_rowNum}" value="${path}"/>
                            </c:if>
                        </display:column>
                    </c:if>
                    <!--End: Notis 19A/19F/19C-->
                    <c:if test="${(actionBean.permohonan.kodUrusan.kod ne 'HT' || actionBean.permohonan.kodUrusan.kod ne 'HTSC' || actionBean.permohonan.kodUrusan.kod ne 'HTSPB') && actionBean.permohonan.kodUrusan.kodPerserahan.kod eq 'MH'}">
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

                    </c:if>
                </display:table>
            </p>
            <p>
                <label>&nbsp;</label>&nbsp;
            </p>

            <p>
                <label>&nbsp;</label>
                <c:if test="${fn:length(actionBean.senaraiHakmilikTerlibat) > 0}">           
                    <s:button name="" value="Cetak" class="btn" onclick="doPrintViaApplet();"/>
                </c:if>
                <s:submit name="" value="Senarai Tugasan" class="btn"/>
            </p>
        </fieldset>
    </div>    
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

