<%-- 
    Document   : semak_pembida2
    Created on : Jun 13, 2013, 1:01:16 PM
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
                Maklumat Pembida Berdaftar
            </legend>

            <br/>
            <display:table class="tablecloth" name="${actionBean.checkStatusPembida}" id="line">                
                <display:column title="Bil">${line_rowNum}</display:column>
                <display:column title="Nama" class="nama" style="text-transform:uppercase;">${line.pihak.nama}</display:column>
                <display:column title="Jenis Pengenalan" style="text-transform: uppercase">${line.pihak.jenisPengenalan.nama}</display:column>
                <display:column title="Nombor Pengenalan" style="text-transform: uppercase">${line.pihak.noPengenalan}</display:column>
                <c:if test="${line.pihak.noTelefon1 ne null}">
                    <display:column title="Nombor Telefon Rumah " style="text-transform:uppercase;">${line.pihak.noTelefon1}</display:column>
                </c:if>
                <c:if test="${line.pihak.noTelefon1 eq null}">
                    <display:column title="Nombor Telefon Rumah " style="text-transform:uppercase;"></display:column>
                </c:if>
                <c:if test="${line.pihak.noTelefon2 ne null}">
                    <display:column title="Nombor Telefon Bimbit " style="text-transform:uppercase;">${line.pihak.noTelefon2}</display:column>   
                </c:if>
                <c:if test="${line.pihak.noTelefon2 eq null}">
                    <display:column title="Nombor Telefon Bimbit " style="text-transform:uppercase;"></display:column>   
                </c:if>
                <display:column title="No Rujukan" style="text-transform: uppercase">${line.noRujukan}</display:column>
                   
                <display:column title="Deposit (RM)" style="text-transform: uppercase">${line.lelong.deposit}</display:column>
                
                <c:if test="${line.dokumen.namaFizikal != null}">
                    <display:column title="Tindakan" style="text-transform:uppercase;">
                        <p align="center"><img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                               onclick="doViewReport('${line.dokumen.idDokumen}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"></p>
                            <s:hidden name="idDokumen">${line.dokumen.idDokumen}</s:hidden>
                        </display:column>    
                    </c:if>

            </display:table> 
            <br/>


            <br>



            <p align="center">
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>
        <br>
    </div>
</s:form>
