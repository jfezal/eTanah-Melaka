<%--
    Document   : utilityCetakSemula
    Created on : Nov 09, 2012, 4:31:32 PM
    Author     : Murali
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function doSaveCapaian(v, b) {
        var sbb = $('#sbb_cetakan_semula').val();
        var idHakmilik = b;
        if (sbb == '') {
            alert('Sila masukan Sebab');
            return;
        }
        var url = '${pageContext.request.contextPath}/utility/strata/cetak_semula?cetakSemula&sbb_cetakan_semula=' + sbb + '&id_dokumen=' + v;
        $.ajax({
            type: "GET",
            url: url,
            success: function (data) {
                if (data == '1') {
                    doPrintViaApplet(v);
                } else {
                    alert("Sila Masukkan Sebab Yang Berlainan.");
                }
            }
        });

    }

    function doPrintViaApplet(docId) {
        var a = document.getElementById('applet');
        a.doPrint(docId.toString());
    }
    function resetValue() {
        $('#idMohon').val('');
    }

    function selectAll1(a) {
        var len = $('.cetakSemua1').length;
        for (var i = 0; i < len; i++) {
            var c = document.getElementById("cetaksemua1" + i);
            c.checked = a.checked;
        }
    }

    function printSelectedDocuments1() {
        var sbb = $('#sbb_cetakan_semula').val();
        if (sbb == '') {
            alert('Sila masukan Sebab');
            return;
        }

        var documentsList = '';
        var len = $('.cetakSemua1').length;
        for (var i = 0; i < len; i++) {
            var c = document.getElementById("cetaksemua1" + i);
            if (c.checked) {
                documentsList = documentsList + ',' + c.value;
                var url = '${pageContext.request.contextPath}/utility/strata/cetak_semula?cetakSemula&sbb_cetakan_semula=' + sbb
                        + '&id_dokumen=' + c.value;
                $.ajax({
                    type: "GET",
                    url: url,
                    success: function (data) {
                        if (data == '0') {
                            alert("Sila Masukkan Sebab Yang Berlainan");
                            return;
                        }
                    }
                });
            }
        }
        if (documentsList == '') {
            alert('Sila Pilih Dokumen untuk dicetak.');
            return;
        } else {
            doPrintViaApplet(documentsList);
        }
    }
    function selectAll2(a) {
        var len = $('.cetakSemua2').length;
        for (var i = 0; i < len; i++) {
            var c = document.getElementById("cetaksemua2" + i);
            c.checked = a.checked;
        }
    }

    function printSelectedDocuments2() {
        var sbb = $('#sbb_cetakan_semula').val();
        if (sbb == '') {
            alert('Sila masukan Sebab');
            return;
        }

        var documentsList = '';
        var len = $('.cetakSemua2').length;
        for (var i = 0; i < len; i++) {
            var c = document.getElementById("cetaksemua2" + i);
            if (c.checked) {
                documentsList = documentsList + ',' + c.value;
                var url = '${pageContext.request.contextPath}/utility/strata/cetak_semula?cetakSemula&sbb_cetakan_semula=' + sbb
                        + '&id_dokumen=' + c.value;
                $.ajax({
                    type: "GET",
                    url: url,
                    success: function (data) {
                        if (data == '0') {
                            alert("Sila Masukkan Sebab Yang Berlainan");
                            return;
                        }
                    }
                });
            }
        }
        if (documentsList == '') {
            alert('Sila Pilih Dokumen untuk dicetak.');
            return;
        } else {
            doPrintViaApplet(documentsList);
        }
    }
    function selectAll3(a) {
        var len = $('.cetakSemua3').length;
        for (var i = 0; i < len; i++) {
            var c = document.getElementById("cetaksemua3" + i);
            c.checked = a.checked;
        }
    }

    function printSelectedDocuments3() {
        var sbb = $('#sbb_cetakan_semula').val();
        if (sbb == '') {
            alert('Sila masukan Sebab');
            return;
        }

        var documentsList = '';
        var len = $('.cetakSemua3').length;
        for (var i = 0; i < len; i++) {
            var c = document.getElementById("cetaksemua3" + i);
            if (c.checked) {
                documentsList = documentsList + ',' + c.value;
                var url = '${pageContext.request.contextPath}/utility/strata/cetak_semula?cetakSemula&sbb_cetakan_semula=' + sbb
                        + '&id_dokumen=' + c.value;
                $.ajax({
                    type: "GET",
                    url: url,
                    success: function (data) {
                        if (data == '0') {
                            alert("Sila Masukkan Sebab Yang Berlainan");
                            return;
                        }
                    }
                });
            }
        }
        if (documentsList == '') {
            alert('Sila Pilih Dokumen untuk dicetak.');
            return;
        } else {
            doPrintViaApplet(documentsList);
        }
    }
    function selectAll4(a) {
        var len = $('.cetakSemua4').length;
        for (var i = 0; i < len; i++) {
            var c = document.getElementById("cetaksemua4" + i);
            c.checked = a.checked;
        }
    }

    function printSelectedDocuments4() {
        var sbb = $('#sbb_cetakan_semula').val();
        if (sbb == '') {
            alert('Sila masukan Sebab');
            return;
        }

        var documentsList = '';
        var len = $('.cetakSemua4').length;
        for (var i = 0; i < len; i++) {
            var c = document.getElementById("cetaksemua4" + i);
            if (c.checked) {
                documentsList = documentsList + ',' + c.value;
                var url = '${pageContext.request.contextPath}/utility/strata/cetak_semula?cetakSemula&sbb_cetakan_semula=' + sbb
                        + '&id_dokumen=' + c.value;
                $.ajax({
                    type: "GET",
                    url: url,
                    success: function (data) {
                        if (data == '0') {
                            alert("Sila Masukkan Sebab Yang Berlainan");
                            return;
                        }
                    }
                });
            }
        }
        if (documentsList == '') {
            alert('Sila Pilih Dokumen untuk dicetak.');
            return;
        } else {
            doPrintViaApplet(documentsList);
        }
    }
    function selectAll5(a) {
        var len = $('.cetakSemua5').length;
        for (var i = 0; i < len; i++) {
            var c = document.getElementById("cetaksemua5" + i);
            c.checked = a.checked;
        }
    }

    function printSelectedDocuments5() {
        var sbb = $('#sbb_cetakan_semula').val();
        if (sbb == '') {
            alert('Sila masukan Sebab');
            return;
        }

        var documentsList = '';
        var len = $('.cetakSemua5').length;
        for (var i = 0; i < len; i++) {
            var c = document.getElementById("cetaksemua5" + i);
            if (c.checked) {
                documentsList = documentsList + ',' + c.value;
                var url = '${pageContext.request.contextPath}/utility/strata/cetak_semula?cetakSemula&sbb_cetakan_semula=' + sbb
                        + '&id_dokumen=' + c.value;
                $.ajax({
                    type: "GET",
                    url: url,
                    success: function (data) {
                        if (data == '0') {
                            alert("Sila Masukkan Sebab Yang Berlainan");
                            return;
                        }
                    }
                });
            }
        }
        if (documentsList == '') {
            alert('Sila Pilih Dokumen untuk dicetak.');
            return;
        } else {
            doPrintViaApplet(documentsList);
        }
    }

</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.strata.UtilityCetakSemulaActionBean" id="cetak_semula">
    <s:messages/>
    <s:errors/>
    <div class="subtitle" id="dokumen">

        <fieldset class="aras1">
            <legend>Cetak Semula</legend>

            <p>
                <label><em>*</em>ID Permohonan :</label>
                <s:text name="permohonan.idPermohonan" size="34" maxlength="20" id="idMohon" onblur="this.value=this.value.toUpperCase();"/>
            </p> <br />
            <p>
                <label>&nbsp;</label>
                <s:submit name="search" value="Cari" class="btn" onclick=""/>
                <s:submit name="reset" value="Isi Semula" class="btn" onclick=""/>&nbsp;
            </p>

            <br/>
            <c:if test="${fn:length(actionBean.senaraiKandungan) > 0}">
                <p>
                    <label>Sebab Cetakan Semula :</label>
                    <s:textarea name="sbb_cetakan_semula" id="sbb_cetakan_semula" cols="60" rows="5" onblur="toUppercase(this.id);"/>
                </p>
            </c:if>
            <br>
            <br/>
            <c:if test="${fn:length(actionBean.senaraiKandungan) > 0}">
                <c:if test="${fn:length(actionBean.senaraiKandungan4KDHKK) > 0}">
                    <c:set var="rowNum" value="0"/>
                    <display:table name="${actionBean.senaraiKandungan4KDHKK}" class="tablecloth" id="row" style="width:100%">
                        <display:column title="Bil" sortable="true">${row_rowNum}</display:column>
                        <display:column title="Kod Dokumen" property="dokumen.kodDokumen.kod" />
                        <display:column title="Nama Dokumen" property="dokumen.tajuk" />
                        <display:column title="Klasifikasi" property="dokumen.klasifikasi.nama" />
                        <display:column title="Dimasuk Oleh" property="dokumen.infoAudit.dimasukOleh.nama" />
                        <display:column title="Catatan" property="catatan" />
                        <display:column title="Tarikh" property="dokumen.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                        <display:column title="Papar">
                            <c:if test="${row.dokumen.namaFizikal != null}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReport('${row.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor = 'pointer';" title="Papar ${row.dokumen.kodDokumen.nama}"/>
                            </c:if>
                        </display:column>
                        <display:column title="<input type='checkbox' id='semua1' name='semua1' onclick='javascript:selectAll1(this);' /> Cetak">
                            <p align="center">
                                <input type="checkbox" name="cetaksemua1" id="cetaksemua1${rowNum}" value="${row.dokumen.idDokumen}" 
                                       class="cetakSemua1"/>
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                     onclick="doSaveCapaian('${row.dokumen.idDokumen}');" height="30" width="30" alt="cetak"
                                     onmouseover="this.style.cursor = 'pointer';" title="Cetak Dokumen "/>
                            </p>
                            <c:set var="rowNum" value="${rowNum +1}"/>
                        </display:column>
                    </display:table>
                    <center>
                        <s:button name="cetakSemua" value="Cetak Borang 4K(DHKK)" class="longbtn" onclick="printSelectedDocuments1();"/>
                    </center>
                </c:if>
                <br />
                <c:if test="${fn:length(actionBean.senaraiKandungan4KDHDK) > 0}">
                    <c:set var="rowNum" value="0"/>
                    <display:table name="${actionBean.senaraiKandungan4KDHDK}" class="tablecloth" id="row" style="width:100%">
                        <display:column title="Bil" sortable="true">${row_rowNum}</display:column>
                        <display:column title="Kod Dokumen" property="dokumen.kodDokumen.kod" />
                        <display:column title="Nama Dokumen" property="dokumen.tajuk" />
                        <display:column title="Klasifikasi" property="dokumen.klasifikasi.nama" />
                        <display:column title="Dimasuk Oleh" property="dokumen.infoAudit.dimasukOleh.nama" />
                        <display:column title="Catatan" property="catatan" />
                        <display:column title="Tarikh" property="dokumen.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                        <display:column title="Papar">
                            <c:if test="${row.dokumen.namaFizikal != null}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReport('${row.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor = 'pointer';" title="Papar ${row.dokumen.kodDokumen.nama}"/>
                            </c:if>
                        </display:column>
                        <display:column title="<input type='checkbox' id='semua2' name='semua2' onclick='javascript:selectAll2(this);' /> Cetak">
                            <p align="center">
                                <input type="checkbox" name="cetaksemua2" id="cetaksemua2${rowNum}" value="${row.dokumen.idDokumen}" 
                                       class="cetakSemua2"/>
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                     onclick="doSaveCapaian('${row.dokumen.idDokumen}');" height="30" width="30" alt="cetak"
                                     onmouseover="this.style.cursor = 'pointer';" title="Cetak Dokumen "/>
                            </p>
                            <c:set var="rowNum" value="${rowNum +1}"/>
                        </display:column>
                    </display:table>
                    <center>
                        <s:button name="cetakSemua" value="Cetak Borang 4K(DHDK)" class="longbtn" onclick="printSelectedDocuments2();"/>
                    </center>
                </c:if>
                <br />
                <c:if test="${fn:length(actionBean.senaraiKandunganBSDHDK) > 0}">
                    <c:set var="rowNum" value="0"/>
                    <display:table name="${actionBean.senaraiKandunganBSDHDK}" class="tablecloth" id="row" style="width:100%">
                        <display:column title="Bil" sortable="true">${row_rowNum}</display:column>
                        <display:column title="Kod Dokumen" property="dokumen.kodDokumen.kod" />
                        <display:column title="Nama Dokumen" property="dokumen.tajuk" />
                        <display:column title="Klasifikasi" property="dokumen.klasifikasi.nama" />
                        <display:column title="Dimasuk Oleh" property="dokumen.infoAudit.dimasukOleh.nama" />
                        <display:column title="Catatan" property="catatan" />
                        <display:column title="Tarikh" property="dokumen.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                        <display:column title="Papar">
                            <c:if test="${row.dokumen.namaFizikal != null}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReport('${row.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor = 'pointer';" title="Papar ${row.dokumen.kodDokumen.nama}"/>
                            </c:if>
                        </display:column>
                        <display:column title="<input type='checkbox' id='semua3' name='semua3' onclick='javascript:selectAll3(this);' /> Cetak">
                            <p align="center">
                                <input type="checkbox" name="cetaksemua3" id="cetaksemua3${rowNum}" value="${row.dokumen.idDokumen}" 
                                       class="cetakSemua3"/>
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                     onclick="doSaveCapaian('${row.dokumen.idDokumen}');" height="30" width="30" alt="cetak"
                                     onmouseover="this.style.cursor = 'pointer';" title="Cetak Dokumen "/>
                            </p>
                            <c:set var="rowNum" value="${rowNum +1}"/>
                        </display:column>
                    </display:table>
                    <center>
                        <s:button name="cetakSemua" value="Cetak Borang BSK(DHDK)" class="longbtn" onclick="printSelectedDocuments3();"/>
                    </center>
                </c:if>
                <br />
                <c:if test="${fn:length(actionBean.senaraiKandunganBSDHKK) > 0}">
                    <c:set var="rowNum" value="0"/>
                    <display:table name="${actionBean.senaraiKandunganBSDHKK}" class="tablecloth" id="row" style="width:100%">
                        <display:column title="Bil" sortable="true">${row_rowNum}</display:column>
                        <display:column title="Kod Dokumen" property="dokumen.kodDokumen.kod" />
                        <display:column title="Nama Dokumen" property="dokumen.tajuk" />
                        <display:column title="Klasifikasi" property="dokumen.klasifikasi.nama" />
                        <display:column title="Dimasuk Oleh" property="dokumen.infoAudit.dimasukOleh.nama" />
                        <display:column title="Catatan" property="catatan" />
                        <display:column title="Tarikh" property="dokumen.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                        <display:column title="Papar">
                            <c:if test="${row.dokumen.namaFizikal != null}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReport('${row.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor = 'pointer';" title="Papar ${row.dokumen.kodDokumen.nama}"/>
                            </c:if>
                        </display:column>
                        <display:column title="<input type='checkbox' id='semua4' name='semua4' onclick='javascript:selectAll4(this);' /> Cetak">
                            <p align="center">
                                <input type="checkbox" name="cetaksemua4" id="cetaksemua4${rowNum}" value="${row.dokumen.idDokumen}" 
                                       class="cetakSemua4"/>
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                     onclick="doSaveCapaian('${row.dokumen.idDokumen}');" height="30" width="30" alt="cetak"
                                     onmouseover="this.style.cursor = 'pointer';" title="Cetak Dokumen "/>
                            </p>
                            <c:set var="rowNum" value="${rowNum +1}"/>
                        </display:column>
                    </display:table>
                    <center>
                        <s:button name="cetakSemua" value="Cetak Borang BSK(DHKK)" class="longbtn" onclick="printSelectedDocuments4();"/>
                    </center>
                </c:if>
                <br />
                <c:if test="${fn:length(actionBean.senaraiKandunganPSK) > 0}">
                    <c:set var="rowNum" value="0"/>
                    <display:table name="${actionBean.senaraiKandunganPSK}" class="tablecloth" id="row" style="width:100%">
                        <display:column title="Bil" sortable="true">${row_rowNum}</display:column>
                        <display:column title="Kod Dokumen" property="dokumen.kodDokumen.kod" />
                        <display:column title="Nama Dokumen" property="dokumen.tajuk" />
                        <display:column title="Klasifikasi" property="dokumen.klasifikasi.nama" />
                        <display:column title="Dimasuk Oleh" property="dokumen.infoAudit.dimasukOleh.nama" />
                        <display:column title="Catatan" property="catatan" />
                        <display:column title="Tarikh" property="dokumen.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                        <display:column title="Papar">
                            <c:if test="${row.dokumen.namaFizikal != null}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReport('${row.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor = 'pointer';" title="Papar ${row.dokumen.kodDokumen.nama}"/>
                            </c:if>
                        </display:column>
                        <display:column title="<input type='checkbox' id='semua5' name='semua5' onclick='javascript:selectAll5(this);' /> Cetak">
                            <p align="center">
                                <input type="checkbox" name="cetaksemua5" id="cetaksemua5${rowNum}" value="${row.dokumen.idDokumen}" 
                                       class="cetakSemua5"/>
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                     onclick="doSaveCapaian('${row.dokumen.idDokumen}');" height="30" width="30" alt="cetak"
                                     onmouseover="this.style.cursor = 'pointer';" title="Cetak Dokumen "/>
                            </p>
                            <c:set var="rowNum" value="${rowNum +1}"/>
                        </display:column>
                    </display:table>
                    <center>
                        <s:button name="cetakSemua" value="Cetak Borang PSK" class="longbtn" onclick="printSelectedDocuments5();"/>
                    </center>
                </c:if>
                <br />
                <display:table name="${actionBean.senaraiKandungan}" class="tablecloth" id="row" style="width:100%">
                    <display:column title="Bil" sortable="true">${row_rowNum}</display:column>
                    <display:column title="Kod Dokumen" property="dokumen.kodDokumen.kod" />
                    <display:column title="Nama Dokumen" property="dokumen.tajuk" />
                    <display:column title="Klasifikasi" property="dokumen.klasifikasi.nama" />
                    <display:column title="Dimasuk Oleh" property="dokumen.infoAudit.dimasukOleh.nama" />
                    <display:column title="Catatan" property="catatan" />
                    <display:column title="Tarikh" property="dokumen.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                    <display:column title="Papar">
                        <c:if test="${row.dokumen.namaFizikal != null}">
                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                 onclick="doViewReport('${row.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                 onmouseover="this.style.cursor = 'pointer';" title="Papar ${row.dokumen.kodDokumen.nama}"/>
                        </c:if>
                    </display:column>
                </display:table>
            </c:if>
            <br/>
        </fieldset>

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
            <param name ="uploadAction" value="<%=request.getContextPath()%>/PrintServlet"/>
            <param name ="disabledWatermark" value="true"/>
            <param name ="withoutSignature" value="true"/>
            <param name ="method" value="pdfp">
            <param name ="idPengguna" value="${idPengguna}"/>
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
</s:form>


