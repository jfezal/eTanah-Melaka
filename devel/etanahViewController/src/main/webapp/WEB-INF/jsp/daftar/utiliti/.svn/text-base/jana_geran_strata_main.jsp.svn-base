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
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-papa.3.3.js"></script>
        <script language="javascript">

            $(document).ready(function() {
                $('input:text').each(function() {
                    $(this).focus(function() {
                        $(this).addClass('focus')
                    });
                    $(this).blur(function() {
                        $(this).removeClass('focus')
                    });
                });
                $('select').each(function() {
                    $(this).focus(function() {
                        $(this).addClass('focus')
                    });
                    $(this).blur(function() {
                        $(this).removeClass('focus')
                    });
                });

                $('form').submit(function() {
                    doBlockUI();
                    var valid = false;
                    var id = $('#idPermohonan').val();
                    if (id === '') {
                        $('.kodUrusan').each(function(index) {
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


                if (id == 'idHakmilik') {
                    inpt = document.getElementById('idHakmilik');
                    if (inpt.value == '') {
                        alert('Sila masukkan ID Hakmilik untuk membuat carian.');
                        $('#idHakmilik').focus();
                        return false;
                    }
                }

                return true;
            }

            function doViewReport(v) {
                var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
                window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
            }

            function selectAllDHKK(a) {

                var len = $('.cetaksemuaDHKK').length;
                for (var i = 1; i <= len; i++) {
                    var c = document.getElementById("cetaksemuaDHKK" + i);
                    c.checked = a.checked;
                }
            }
            function selectAllDHDK(a) {

                var len = $('.cetaksemuaDHDK').length;
                for (var i = 1; i <= len; i++) {
                    var c = document.getElementById("cetaksemuaDHDK" + i);
                    c.checked = a.checked;
                }
            }
            function selectAll3K(a) {

                var len = $('.cetaksemua3K').length;
                for (var i = 1; i <= len; i++) {
                    var c = document.getElementById("cetaksemua3K" + i);
                    c.checked = a.checked;
                }
            }
            function selectAll2K(a) {

                var len = $('.cetaksemua2K').length;
                for (var i = 1; i <= len; i++) {
                    var c = document.getElementById("cetaksemua2K" + i);
                    c.checked = a.checked;
                }
            }

            function printSelectedDocuments() {
                var documentsList = '';
                var documentsList2 = '';
                var documentsList3 = '';
                var documentsList4 = '';
                var documentsTOTAL = '';
                var len = $('.cetakSemuaDHKK').length;
                var len2 = $('.cetaksemuaDHDK').length;
                var len3 = $('.cetaksemua3K').length;
                var len4 = $('.cetaksemua2K').length;
                
                for (var i = 1; i <= len; i++) {
                    if ($('#cetaksemuaDHKK' + i).is(":checked")) {
                        documentsList = documentsList + ',' + $("#cetaksemuaDHKK" + i).val();
                    }
                }
                for (var i = 1; i <= len2; i++) {
                    if ($('#cetaksemuaDHDK' + i).is(":checked")) {
                        documentsList2 = documentsList2 + ',' + $("#cetaksemuaDHDK" + i).val();
                    }
                }
                for (var i = 1; i <= len3; i++) {
                    if ($('#cetaksemua3K' + i).is(":checked")) {
                        documentsList3 = documentsList3 + ',' + $("#cetaksemua3K" + i).val();
                    }
                }
                for (var i = 1; i <= len4; i++) {
                    if ($('#cetaksemua2K' + i).is(":checked")) {
                        documentsList4 = documentsList4 + ',' + $("#cetaksemua2K" + i).val();
                    }
                }
                documentsTOTAL = documentsList + documentsList2 + documentsList3 + documentsList4

                if (documentsTOTAL == '') {
                    alert('Sila Pilih Dokumen untuk dicetak.');
                    return;
                } else {
                    doPrintViaApplet(documentsTOTAL);

                }
            }

            function doPrintViaApplet(docId) {
//                alert (docId)
                var a = document.getElementById('applet');
                a.doPrint(docId.toString());
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
                            <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">STRATA: Utiliti Penjanaan Geran Strata</font>
                        </div>
                    </td>
                </tr>
            </table></div>
        <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>

        <stripes:form action="/daftar/utilityJanaGeranStrataActionBean" id="main_kaunter">
            <br>
            <fieldset class="aras1">
                <p class=title>Langkah 1: Semak HAKMILIK</p>
                <p class=title>Carian Dokumen Strata</p>
                <span style="font-weight:normal;color: black" class=instr>Medan bertanda <em>*</em> adalah mandatori. Sila masukkan ID HAKMILIK INDUK pada ruang yang disediakan.</span>
                <br>
                <c:set scope="request" var="senaraiUrusanPendaftaran" value="${actionBean.senaraiUrusanTukarganti}" />

                <p><label><em>*</em>ID Hakmilik Induk : </label>
                    <stripes:text name="hakmilikInduk" id="hakmilikInduk" size="30" onkeyup="this.value=this.value.toUpperCase();"/>
                    Atau
                </p>

                <p><label><em>*</em>ID Hakmilik Strata : </label>

                    <stripes:text name="hakmilikStrata" id="hakmilikStrata" size="30" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>

                <p>
                    <label>&nbsp;</label>
                <p> <stripes:submit name="Step7a" value="Cari" class="btn" id="Step7a" onclick="return checkingValue('hakmilikInduk')"/>
                    &nbsp<stripes:button name="" class='btn' value="Isi Semula" onclick="clearText('main_kaunter');"/>&nbsp;</p>


                <fieldset class="aras1">
                    <c:if test="${fn:length(actionBean.senaraiTingkat) > 0}">
                        <legend>
                            Hasil Carian
                        </legend>
                        <br>
                        <p><label><em>*</em>No Tingkat :
                                <s:select name="noTingkat" id="noTingkat">
                                    <s:option value="">Sila Pilih</s:option>
                                    <%--<s:options-collection collection="${actionBean.senaraiSeksyen}" label="nama" value="kod" />--%>
                                    <c:forEach items="${actionBean.senaraiTingkat}" var="item">
                                        <s:option value="${item}">${item}</s:option>
                                    </c:forEach>
                                </s:select>&nbsp;<em>*</em>No Bangunan : </label>
                                <s:select name="noBangunan" id="noBangunan">
                                    <s:option value="">Sila Pilih</s:option>
                                <%--<s:options-collection collection="${actionBean.senaraiSeksyen}" label="nama" value="kod" />--%>
                                <c:forEach items="${actionBean.senaraiBangunan}" var="item">
                                    <s:option value="${item}">${item}</s:option>
                                </c:forEach>
                            </s:select>
                            <s:hidden name="${actionBean.permohonan}"/> 
                            &nbsp;<stripes:submit name="carianHakmilikBetween" value="Cari Hakmilik" class="btn" id="step7" onclick="return checkingValue('idHakmilikFirst','idHakmilik2')"/>
                        </p> <br><br>
                        <br>
                    </c:if>
                </fieldset>
                <BR>

            </fieldset>
            <fieldset class="aras1">
                <c:if test="${fn:length(actionBean.senaraiHakmilikVersi4k) > 0}">
                    <div class="content" align="center">
                        <display:table style="width:80%;" class="tablecloth" name="${actionBean.senaraiHakmilikVersi4k}" cellpadding="0" cellspacing="0" id="line">
                            <display:column title="No" style="width:5%">
                                <p align="center">
                                    <c:out value="${line_rowNum}"/>
                                </p>

                            </display:column>
                            <display:column title="Hakmilik Induk" style="width:10%">
                                <p align="center">
                                    ${line.idHakmilikStrata}
                                </p>
                            </display:column>
                            <display:column title="<input type='checkbox' id='semua2k' name='semua2k' onclick='javascript:selectAll2K(this);' /> Cetak/Papar 2K" style="width:10%">
                                <p align="center">
                                    <c:if test="${line.id2k.namaFizikal != null}">
                                        Papar  <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                    onclick="doViewReport('${line.id2k.idDokumen}');" height="30" width="30" alt="papar"
                                                    onmouseover="this.style.cursor = 'pointer';" title="Papar ${line.id2k.kodDokumen.nama}"/>                                  
                                    </c:if>
                                </p>
                                <p align="center">
                                    <c:if test="${line.id2k.namaFizikal != null}">

                                        Cetak <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                                   onclick="doPrintViaApplet('${line.id2k.idDokumen}');" height="30" width="30" alt="cetak"
                                                   onmouseover="this.style.cursor = 'pointer';" title="Cetak Dokumen ${line.id2k.kodDokumen.nama}"/>
                                        &nbsp;&nbsp<input type="checkbox" name="cetaksemua2K" id="cetaksemua2K${line_rowNum}" value="${line.id2k.idDokumen}" class="cetaksemua2K"/>
                                    </c:if>
                                </p>
                                <c:set var="rowNum" value="${line_rowNum +1}"/>
                            </display:column>

                            <display:column title="<input type='checkbox' id='semua3k' name='semua3k' onclick='javascript:selectAll3K(this);' /> Cetak/Papar 3K" style="width:10%">
                                <p align="center">
                                    <c:if test="${line.id3k.namaFizikal != null}">
                                        Papar  <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                    onclick="doViewReport('${line.id3k.idDokumen}');" height="30" width="30" alt="papar"
                                                    onmouseover="this.style.cursor = 'pointer';" title="Papar ${line.id3k.kodDokumen.nama}"/>                                  
                                    </c:if>
                                </p>
                                <p align="center">
                                    <c:if test="${line.id3k.namaFizikal != null}">

                                        Cetak <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                                   onclick="doPrintViaApplet('${line.id3k.idDokumen}');" height="30" width="30" alt="cetak"
                                                   onmouseover="this.style.cursor = 'pointer';" title="Cetak Dokumen ${line.id3k.kodDokumen.nama}"/>
                                        &nbsp;&nbsp<input type="checkbox" name="cetaksemua3K" id="cetaksemua3K${line_rowNum}" value="${line.id3k.idDokumen}" class="cetaksemua3K"/>
                                    </c:if>
                                </p>
                                <c:set var="rowNum" value="${line_rowNum +1}"/>
                            </display:column>

                            <display:column title="<input type='checkbox' id='semuaDHDK' name='semuaDHDK' onclick='javascript:selectAllDHDK(this);' /> Cetak/Papar DHDK 4K" style="width:10%">
                                <p align="center">
                                    <c:if test="${line.DHDK4K.namaFizikal != null}">
                                        Papar  <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                    onclick="doViewReport('${line.DHDK4K.idDokumen}');" height="30" width="30" alt="papar"
                                                    onmouseover="this.style.cursor = 'pointer';" title="Papar ${line.DHDK4K.kodDokumen.nama}"/>                                  
                                    </c:if>
                                </p>
                                <p align="center">
                                    <c:if test="${line.DHDK4K.namaFizikal != null}">
                                        Cetak <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                                   onclick="doPrintViaApplet('${line.DHDK4K.idDokumen}');" height="30" width="30" alt="cetak"
                                                   onmouseover="this.style.cursor = 'pointer';" title="Cetak Dokumen ${line.DHDK4K.kodDokumen.nama}"/>
                                        &nbsp;&nbsp<input type="checkbox" name="cetaksemuaDHDK" id="cetaksemuaDHDK${line_rowNum}" value="${line.DHDK4K.idDokumen}" class="cetaksemuaDHDK"/>
                                    </c:if>
                                </p>
                                <c:set var="rowNum" value="${line_rowNum +1}"/>
                            </display:column>
                            <display:column title="<input type='checkbox' id='semuaDHKK' name='semuaDHKK' onclick='javascript:selectAllDHKK(this);' /> Cetak/Papar DHKK" style="width:10%">
                                <p align="center">
                                    <c:if test="${line.DHKK4K.namaFizikal != null}">
                                        Papar  <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                    onclick="doViewReport('${line.DHKK4K.idDokumen}');" height="30" width="30" alt="papar"
                                                    onmouseover="this.style.cursor = 'pointer';" title="Papar ${line.DHKK4K.kodDokumen.nama}"/>                                  
                                    </c:if>
                                </p>
                                <p align="center">
                                    <c:if test="${line.DHKK4K.namaFizikal != null}">

                                        Cetak <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                                   onclick="doPrintViaApplet('${line.DHKK4K.idDokumen}');" height="30" width="30" alt="cetak"
                                                   onmouseover="this.style.cursor = 'pointer';" title="Cetak Dokumen ${line.DHKK4K.kodDokumen.nama}"/>
                                        &nbsp;&nbsp;<input type="checkbox" name="cetaksemuaDHKK" id="cetaksemuaDHKK${line_rowNum}" value="${line.DHKK4K.idDokumen}" class="cetaksemuaDHKK"/>

                                    </c:if>
                                </p>
                                <c:set var="rowNum" value="${line_rowNum +1}"/>
                            </display:column>
                        </display:table>
                        <br>
                        <%--<s:submit name="Step8b" value="Jana Semua" class="btn" onclick="validate(this.form)"/>&nbsp;--%>
                        <s:submit name="Step8c" value="Jana Geran 4K" class="btn" onclick="validate(this.form)"/>&nbsp;
                        <s:submit name="Step8e" value="Jana Geran 3k" class="btn" onclick="validate(this.form)"/>&nbsp;
                        <s:submit name="Step8d" value="Jana Geran 2k" class="btn" onclick="validate(this.form)"/>&nbsp;
                        &nbsp;<s:button name="cetaksemua" value="Cetak" class="btn" onclick="printSelectedDocuments();"/>

                    </div>
                    <br>
                </c:if>
            </fieldset>
            <!--</div>-->

        </stripes:form>

        <applet code="PrintApplet.class" ARCHIVE="${pageContext.request.contextPath}/PrintApplet.jar,
                ${pageContext.request.contextPath}/commons-httpclient-2.0.2.jar,
                ${pageContext.request.contextPath}/commons-logging.jar,
                ${pageContext.request.contextPath}/swingx-1.6.jar,
                ${pageContext.request.contextPath}/log4j-1.2.12.jar,
                ${pageContext.request.contextPath}/jpedal_trial.jar"
                codebase = "."
                name     = "applet"
                id       = "applet"
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

        <br/>
    </body>
</html>