<%-- 
    Document   : summaryPage
    Created on : Jan 16, 2020, 3:56:32 PM
    Author     : zipzap
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    $(document).ready(function() {
    });
     function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }

    function muatNaikForm(folderDokumenId, dokumenId, idPermohonan, kodDokumen) {
//           alert (kodDokumen);
        var left = (screen.width / 2) - (1000 / 2);
        var top = (screen.height / 2) - (150 / 2);
        var url = '${pageContext.request.contextPath}/pengambilan/permohonan_ukur?muatNaikForm&folderDokumenId=' + folderDokumenId + '&dokumenId='
                + dokumenId + '&idPermohonan=' + idPermohonan + '&kodDokumen=' + kodDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form  beanclass="etanah.view.stripes.pengambilan.sek8.SediaPermohonanUkurActionBean">
    <s:errors/>
    <s:messages/>
    <div align="center"> 
        <fieldset class="aras1">
            <legend>Dokumen Permohonan Ukur</legend>
            <display:table class="tablecloth" name="${actionBean.listPermohonanUkurForm}" pagesize="30" cellpadding="0" cellspacing="0"
                           requestURI="" id="line">
                <display:column title="No" sortable="true">${line_rowNum}</display:column>
                <display:column title="Nama Dokumen" sortable="true">${line.kodDokumen.nama}</display:column>
                <display:column title="Dokumen" sortable="true">
                    <p align="center">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                     onclick="muatNaikForm('${line.folderDokumenId}', '${line.dokumen.idDokumen}'
                                                     , '${actionBean.idPermohonan}', '${line.kodDokumen.kod}');
                                             return false;" height="30" width="30" alt="Muat Naik"
                                     onmouseover="this.style.cursor = 'pointer';" title="Muat Naik untuk Dokumen ${line.dokumen.kodDokumen.nama}"/> <b>|</b>
                                <c:if test="${line.dokumen.namaFizikal != null}">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                         onclick="doViewReport('${line.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                         onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen ${line.dokumen.kodDokumen.nama}"/>
                                    <c:if test="${line.dokumen.baru eq 'Y' || line.dokumen.baru eq ''}">
                                        <img src="${pageContext.request.contextPath}/pub/images/baharu.gif" alt="baru"/>
                                    </c:if>
                                </c:if>
                            </p>
                    </display:column>
                <%-- <display:column title="kemaskini">${line.folderDokumenId}</display:column>--%>

            </display:table>
            <br>

        </fieldset>
    </div>
</s:form>
