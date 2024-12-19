<%-- 
    Document   : semak_pembida
    Created on : Jun 6, 2013, 12:47:48 PM
    Author     : nur.aqilah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function doViewReport(d) {
        window.open("${pageContext.request.contextPath}/lelong/dokumen/view?view&idDokumen=" + d, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }
    
</script>

<s:form action="/lelong/dokumen/folder" beanclass="etanah.view.lelong.dokumen.FolderAction">

    <div class="subtitle">
        <br/><br/>
        <fieldset class="aras1">
            <legend>
                Maklumat Pembeli
            </legend>

            <br/>
            <display:table class="tablecloth" name="${actionBean.senaraiPembida}" id="line">                
                <display:column title="Bil">${line_rowNum}</display:column>
                <display:column title="Nama" class="nama" style="text-transform:uppercase;">${line.pihak.nama}</display:column>
                <display:column title="Jenis Pengenalan" style="text-transform: uppercase">${line.pihak.jenisPengenalan.nama}</display:column>
                <display:column title="Nombor Pengenalan" style="text-transform: uppercase">${line.pihak.noPengenalan}</display:column>
                <display:column title="Alamat" style="text-transform: uppercase">
                    ${line.pihak.alamat1}<c:if test="${line.pihak.alamat2 ne null}">,</c:if>
                    ${line.pihak.alamat2}<c:if test="${line.pihak.alamat3 ne null}">,</c:if>
                    ${line.pihak.alamat3}<c:if test="${line.pihak.alamat4 ne null}">,</c:if>
                    ${line.pihak.alamat4}<c:if test="${line.pihak.poskod ne null}">,</c:if>
                    ${line.pihak.poskod}<c:if test="${line.pihak.negeri.kod ne null}">,</c:if>
                    ${line.pihak.negeri.nama}&nbsp;
                </display:column>  
                <display:column title="Deposit (RM)"><s:format formatPattern="#,##0.00" value="${actionBean.lelong.deposit}"/></display:column>   
                <display:column title="Nombor Telefon Rumah " style="text-transform:uppercase;">${line.pihak.noTelefon1}</display:column>
                <display:column title="Nombor Telefon Bimbit " style="text-transform:uppercase;">${line.pihak.noTelefon2}</display:column>
                <c:if test="${line.dokumen.namaFizikal != null}">
                    <display:column title="Tindakan" style="text-transform:uppercase;">
                        <p align="center"><img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                               onclick="doViewReport('${line.dokumen.idDokumen}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"></p>
                            <s:hidden name="idDokumen">${line.dokumen.idDokumen}</s:hidden>
                        </display:column>    
                    </c:if>

            </display:table> 
            <br/>

            <legend>
                Maklumat Bidaan
            </legend>

            <display:table class="tablecloth" name="${actionBean.listATD}" id="line2">
                <display:column title="Deposit Awal (RM)"><s:format formatPattern="#,##0.00" value="${line2.amaun}" /></display:column>   
                <display:column title="No Rujukan" style="text-transform:uppercase;">${line2.noDokumenBayar}</display:column>
                <c:forEach items="${actionBean.listATD2}" var="line3" >       
                    <c:if test="${line3.jenisDeposit eq 'T'}">
                        <display:column title="Deposit Tambahan(RM)"><s:format formatPattern="#,##0.00" value="${line3.amaun}" /></display:column>
                        <display:column title="No Rujukan Tambahan" style="text-transform:uppercase;">${line3.noDokumenBayar}</display:column>
                    </c:if>
                </c:forEach>
            </display:table>

            <p align="center">
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>
        <br>
    </div>
</s:form>
