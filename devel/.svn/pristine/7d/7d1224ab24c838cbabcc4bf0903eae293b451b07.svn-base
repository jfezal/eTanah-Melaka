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
            <display:table class="tablecloth" name="${actionBean.lelong}" id="line">                
                <display:column title="Bil">${line_rowNum}</display:column>
                <display:column title="Nama" class="nama" style="text-transform:uppercase;">${line.pembida.nama}</display:column>
                <display:column title="Jenis Pengenalan" style="text-transform: uppercase">${line.pembida.jenisPengenalan.nama}</display:column>
                <display:column title="Nombor Pengenalan" style="text-transform: uppercase">${line.pembida.noPengenalan}</display:column>
                <display:column title="Alamat" style="text-transform: uppercase">
                    ${line.pembida.alamat1}<c:if test="${line.pembida.alamat2 ne null}">,</c:if>
                    ${line.pembida.alamat2}<c:if test="${line.pembida.alamat3 ne null}">,</c:if>
                    ${line.pembida.alamat3}<c:if test="${line.pembida.alamat4 ne null}">,</c:if>
                    ${line.pembida.alamat4}<c:if test="${line.pembida.poskod ne null}">,</c:if>
                    ${line.pembida.poskod}<c:if test="${line.pembida.negeri.kod ne null}">,</c:if>
                    ${line.pembida.negeri.nama}&nbsp;
                </display:column>  
                <display:column title="Deposit (RM)"><s:format formatPattern="#,##0.00" value="${actionBean.lelong.deposit}"/></display:column>   
                <display:column title="Nombor Telefon Rumah " style="text-transform:uppercase;">${line.pembida.noTelefon1}</display:column>
                <display:column title="Nombor Telefon Bimbit " style="text-transform:uppercase;">${line.pembida.noTelefonBimbit}</display:column>
            </display:table> 
            <br/>
            <p align="center">
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>
        <br>
    </div>
</s:form>
