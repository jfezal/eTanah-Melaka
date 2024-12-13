<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.tools.min.js?select=full&debug=true"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript">

    $(document).ready(function () {
        $("img[title]").tooltip({
            // tweak the position
            offset: [10, 2],
            // use the "slide" effect
            effect: 'slide'
        }).dynamic({bottom: {direction: 'down', bounce: true}});
        dialogInit('Carian Hakmilik');
        $('#idHakmilik').keyup(function () {
            closeDialog();
        });
    });

    function doSearch(e, f) {
        var a = $('#idPermohonan').val();
        var b = $('#idHakmilik').val();
        if (a == '' && b == '') {
            alert('Sila masukan id Perserahan atau hakmilik.');
            return;
        }

        f.action = f.action + '?' + e;
        f.submit();
    }

    function doSearch2(idPermohonan) {
        var url = '${pageContext.request.contextPath}/daftar/cetak_semula_dokumenNew?search&idPermohonan=' + idPermohonan;
        f = document.form1;
        f.action = url;
        f.submit();
    }

    function carianSemula() {
        $('#idHakmilik').val('');
        var url = '${pageContext.request.contextPath}/daftar/cetak_semula_dokumenNew?searchGeran';
        f = document.form1;
        f.action = url;
        f.submit();
    }

    function doPrintViaApplet(docId) {
        //alert('tsttt');
        var a = document.getElementById('applet');
        //a.doPrint(docId.toString());
        a.printDocument(docId.toString());
    }

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function doSaveCapaian(v) {
        var sbb = $('#sbb_cetakan_semula').val();
        if (sbb == '') {
            alert('Sila masukan Sebab');
            return;
        }
        var url = '${pageContext.request.contextPath}/daftar/cetak_semula_dokumenNew?cetakSemula&sbb_cetakan_semula=' + sbb.trim() + '&id_dokumen=' + v;
        $.ajax({
            type: "GET",
            url: url,
            success: function (data) {
                if (data == '1') {
                    doPrintViaApplet(v);
                }
            }
        });

    }

    function toUppercase(id) {
        $('#' + id).val($('#' + id).val().toUpperCase());
    }
</script>
<style type="text/css">
    .tooltip {
        display:none;
        background:transparent url(${pageContext.request.contextPath}/pub/images/black_arrow.png);
        font-size:12px;
        height:70px;
        width:160px;
        padding:25px;
        color:#fff;
    } 
</style>
<s:messages/>
<s:errors/>
<s:form beanclass="etanah.view.daftar.utiliti.CetakSemulaDokumenNewActionBean" name="form1">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Carian Dokumen
            </legend>           
            <p>
                <label>Hakmilik :</label>
                <s:text name="idHakmilik" id="idHakmilik" onblur="toUppercase(this.id);"
                        onkeyup="this.value = this.value.toUpperCase();"
                        onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/>
            </p>
            <br/>
            <p>
                <label>&nbsp;</label>
                <s:button name="searchGeran" value="Cari" class="btn" onclick="doSearch(this.name, this.form);"/>
                <s:button name="" value="Carian Semula" class="btn" onclick="carianSemula();"/>
            </p>
        </fieldset>
    </div>
    <br/>

    <c:if test="${fn:length(actionBean.senaraiDokumen) > 0}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Keputusan Carian Dokumen
                </legend>
                <p class="instr">
                    Sila klik pada <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar" />
                    untuk semak dokumen.<br/>
                    Sila klik pada <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png" height="30" width="30" alt="papar" />
                    untuk cetak dokumen.
                </p>
                <p align="center">
                    <label></label>

                    <display:table class="tablecloth" name="${actionBean.senaraiDokumenDHDE}"
                                   cellpadding="0" cellspacing="0" id="line1">
                        <display:column title="Bil">${line1_rowNum}</display:column>
                        <display:column property="kodDokumen.kod" title="Kod Dokumen"/>
                        <display:column property="kodDokumen.nama" title="Nama Dokumen"/>
                        <display:column title="Tajuk">
                            ${line1.tajuk}
                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" title="Papar ${line1.tajuk}"
                                 onclick="doViewReport('${line1.idDokumen}');" height="20" width="20" alt="papar" onmouseover="this.style.cursor = 'pointer';"/>
                        </display:column>
                        <display:column title="Cetak">
                            <c:if test="${line1.namaFizikal != null}">
                                <%--<s:button name="cetak" value="Cetak" onclick="doPrintViaApplet('${line.idDokumen}');" class="btn"/>--%>
                            <p align="center"><img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png" title="Cetak ${line1.tajuk}"
                                                   onclick="doSaveCapaian('${line1.idDokumen}');" height="20" width="20" alt="cetak" onmouseover="this.style.cursor = 'pointer';"/></p>
                            </c:if>
                        </display:column>
                    </display:table>
                    <display:table class="tablecloth" name="${actionBean.senaraiDokumenDHKE}"
                                   cellpadding="0" cellspacing="0" id="line1">
                        <display:column title="Bil">${line1_rowNum}</display:column>
                        <display:column property="kodDokumen.kod" title="Kod Dokumen"/>
                        <display:column property="kodDokumen.nama" title="Nama Dokumen"/>
                        <display:column title="Tajuk">
                            ${line1.tajuk}
                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" title="Papar ${line1.tajuk}"
                             onclick="doViewReport('${line1.idDokumen}');" height="20" width="20" alt="papar" onmouseover="this.style.cursor = 'pointer';"/>
                    </display:column>
                    <display:column title="Cetak">
                        <c:if test="${line1.namaFizikal != null}">
                            <%--<s:button name="cetak" value="Cetak" onclick="doPrintViaApplet('${line.idDokumen}');" class="btn"/>--%>
                            <p align="center"><img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png" title="Cetak ${line1.tajuk}"
                                                   onclick="doSaveCapaian('${line1.idDokumen}');" height="20" width="20" alt="cetak" onmouseover="this.style.cursor = 'pointer';"/></p>
                            </c:if>
                        </display:column>
                    </display:table>
                    <display:table class="tablecloth" name="${actionBean.senaraiDokumen}"
                                   cellpadding="0" cellspacing="0" id="line1">
                        <display:column title="Bil">${line1_rowNum}</display:column>
                        <display:column property="kodDokumen.kod" title="Kod Dokumen"/>
                        <display:column property="kodDokumen.nama" title="Nama Dokumen"/>
                        <display:column title="Tajuk">
                            ${line1.tajuk}
                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" title="Papar ${line1.tajuk}"
                             onclick="doViewReport('${line1.idDokumen}');" height="20" width="20" alt="papar" onmouseover="this.style.cursor = 'pointer';"/>
                    </display:column>
                    <display:column title="Cetak">
                        <c:if test="${line1.namaFizikal != null}">
                            <%--<s:button name="cetak" value="Cetak" onclick="doPrintViaApplet('${line.idDokumen}');" class="btn"/>--%>
                            <p align="center"><img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png" title="Cetak ${line1.tajuk}"
                                                   onclick="doSaveCapaian('${line1.idDokumen}');" height="20" width="20" alt="cetak" onmouseover="this.style.cursor = 'pointer';"/></p>
                            </c:if>
                        </display:column>
                    </display:table>
                </p>
                <br/>
                <p>
                    <label>Sebab cetakan semula :</label>
                    <s:textarea name="sbb_cetakan_semula" id="sbb_cetakan_semula" cols="60" rows="10" onblur="toUppercase(this.id);"/>
                </p>
                <br/>
            </fieldset>
        </div>
        <applet code="etanah.dokumen.print.DocumentPrinter" ARCHIVE="${pageContext.request.contextPath}/PrintApplet.jar,
                ${pageContext.request.contextPath}/commons-httpclient-2.0.2.jar,
                ${pageContext.request.contextPath}/commons-logging.jar,
                ${pageContext.request.contextPath}/swingx-1.6.jar,
                ${pageContext.request.contextPath}/log4j-1.2.12.jar,
                ${pageContext.request.contextPath}/jpedal_trial.jar,
                ${pageContext.request.contextPath}/PDFRenderer.jar"
                codebase = "${pageContext.request.contextPath}/"
                name     = "applet"
                id       = "applet"
                width    = "1"
                height   = "1"
                align    = "middle">
            <param name="uploadAction" value="<%=request.getContextPath()%>/PrintServlet"/>
            <param name ="method" value="pdfp"/>
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
    </c:if>
</s:form>