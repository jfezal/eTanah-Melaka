<%-- 
    Document   : semakan_dokumen
    Created on : Jan 29, 2015, 2:48:45 PM
    Author     : muhammad.amin
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<script type="text/javascript">

    $(document).ready(function() {
        $('form').submit(function() {
            doBlockUI();
        });
    });


    function noenter(e) {
        e = e || window.event;
        var key = e.keyCode || e.charCode;
        return key !== 13;
    }

    function clearForm() {
        $("#idMohon").val('');
    }

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
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
</script>

<img alt="" id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form beanclass="etanah.view.stripes.consent.SemakDokumenActionBean" id="semak_dokumen">
    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Semakan Dokumen</legend>
            <br/>
            <p>
                <label> ID Permohonan :</label>
                <s:text name="idPermohonan" size="30" maxlength="20" id="idMohon" onkeyup="this.value=this.value.toUpperCase();" onkeypress="return noenter(event);"/>
            </p>
            <br/>
            <p align="center">
                <s:submit name="find" value="Cari Dokumen" class="btn" onclick=""/>
                <s:submit name="reset" value="Isi Semula" class="btn" onclick="clearForm();"/>
            </p>
            <br/>
        </fieldset>
    </div>
    <c:if test="${fn:length(actionBean.permohonan.folderDokumen.senaraiKandungan) > 0}">
        <fieldset class="aras1" id="row1">
            <c:set var="rowNum" value="0"/><p align="center">
                <display:table name="${actionBean.permohonan.folderDokumen.senaraiKandungan}" class="tablecloth" id="row" style="width:80%">
                    <display:column title="No" class="noUrusan">
                        ${row_rowNum}
                    </display:column>
                    <display:column title="Kod Dokumen">
                    <div id="t" title="${row.dokumen.kodDokumen.nama}">${row.dokumen.kodDokumen.kod}</div>
                </display:column>
                <display:column title="Dokumen">
                    <c:if test="${row.dokumen.kodDokumen.kod eq '0L'}">
                        <div id="${row_rowNum-1}" class="editable">${row.catatan}</div>
                        <s:hidden name="x" id="old_${row_rowNum-1}" value="${row.catatan}"/>
                    </c:if>
                    <c:if test="${row.dokumen.kodDokumen.kod ne '0L'}">
                        <div id="${row_rowNum-1}" class="editable">${row.dokumen.tajuk}</div>
                        <s:hidden name="x" id="old_${row_rowNum-1}" value="${row.dokumen.tajuk}"/>
                    </c:if>
                    <br/>  <c:if test="${row.dokumen.noRujukan ne '' && row.dokumen.noRujukan ne null}">(No rujukan : ${row.dokumen.noRujukan})</c:if>
                </display:column>
                <display:column title="Tarikh" property="dokumen.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                <display:column title="Papar">
                    <p align="center">
                        <c:if test="${row.dokumen.namaFizikal != null}">
                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                 onclick="doViewReport('${row.dokumen.idDokumen}');
                                         return false;" height="30" width="30" alt="papar"
                                 onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen ${row.dokumen.kodDokumen.nama}"/>
                            <c:if test="${row.dokumen.baru eq 'Y' || row.dokumen.baru eq ''}">
                                <img src="${pageContext.request.contextPath}/pub/images/baharu.gif" alt="baru"/>
                            </c:if>
                        </c:if>
                    </p>
                </display:column>
            </display:table>
            <br/>
        </fieldset>
    </c:if>
    <br/>
</s:form>
