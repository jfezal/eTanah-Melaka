<%-- 
    Document   : view_sejarah_cetakan
    Created on : Mei 10, 2013, 12:00:00 PM
    Author     : Azmi
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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<s:messages/>
<s:errors/>
<s:form action="/daftar/cetak_semula_dokumen" >

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Sejarah Paparan Dokumen
            </legend>
            <br>
            <div align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiDokumenCapai}" 
                               cellpadding="0" cellspacing="0" id="line1"
                               requestURI="/daftar/cetak_semula_dokumen"
                               pagesize="10">
                    <display:column title="Bil">${line1_rowNum}</display:column>
                    <display:column property="alasan" title="Sebab Paparan"/>
                    <display:column property="infoAudit.dimasukOleh.nama" title="Paparan Oleh"/>
                    <display:column title="Tarikh Paparan">
                        <fmt:formatDate value="${line1.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy, hh:mm:ss aa"/>
                    </display:column> 
                </display:table>

            </div>
            <br/>
        </fieldset>
        <br>
        <div  align="center"><p><s:button name="tutup" value="Tutup" class="btn" onclick="javascript:window.close();"/></p></div>
    </div>
</s:form>
</body>
</html>