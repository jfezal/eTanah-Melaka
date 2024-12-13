<%-- 
    Document   : tandatangan_dokumen
    Created on : Jun 27, 2011, 10:12:58 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
    $(document).ready(function() {
    });

    function doViewReport(v) {
//        alert(v);
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.pengambilan.sek8.BorangCdanDActionBean">
    <s:messages/>
    <div align="center"> 
        <fieldset class="aras1">
            <legend>Borang C dan D</legend>
            <display:table class="tablecloth" name="${actionBean.listBorang}" pagesize="30" cellpadding="0" cellspacing="0"
                           requestURI="" id="line">
                <display:column title="No" sortable="true">${line_rowNum}</display:column>

                <display:column title="Nama Borang"  property="namaBorang"></display:column>
                <display:column title="DiTandatangan Oleh" property="ditandatangan">></display:column>
                <display:column title="Catatan" property="namaBorang">></display:column>
                <display:column title="Papar" >
                    <div align="center">
                        <c:if test="${line.namaBorang eq 'BORANG C'}">
                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                 onclick="doViewReport('${actionBean.kandunganFolderC.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                 onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen ${actionBean.kandunganFolderC.dokumen.kodDokumen.nama}"/>
                            <c:if test="${actionBean.kandunganFolderC.dokumen.baru eq 'Y' || actionBean.kandunganFolderC.dokumen.baru eq ''}">
                                <img src="${pageContext.request.contextPath}/pub/images/baharu.gif" alt="baru"/>
                            </c:if>
                        </c:if>
                        <c:if test="${line.namaBorang eq 'BORANG D'}">
                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                 onclick="doViewReport('${actionBean.kandunganFolderD.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                 onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen ${actionBean.kandunganFolderD.dokumen.kodDokumen.nama}"/>
                            <c:if test="${actionBean.kandunganFolderD.dokumen.baru eq 'Y' ||actionBean.kandunganFolderD.dokumen.baru eq ''}">
                                <img src="${pageContext.request.contextPath}/pub/images/baharu.gif" alt="baru"/>
                            </c:if>
                        </c:if>
                    </div>
                </display:column>
            </display:table>
            <br>

        </fieldset>
    </div>

</s:form>