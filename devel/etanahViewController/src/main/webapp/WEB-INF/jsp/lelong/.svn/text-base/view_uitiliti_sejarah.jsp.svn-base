<%-- 
    Document   : view_uitiliti_sejarah
    Created on : Apr 13, 2011, 11:27:21 AM
    Author     : mazurahayati.yusop
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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<s:form action="/lelong/status" >
    <div class="subtitle">
        <br>
        <br>
        <br>
        <fieldset class="aras1">
            <legend>
                Sejarah Siasatan
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiEnkuiri}" id="line">
                    <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                    <display:column title="Tarikh"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhEnkuiri}"/></display:column>
                    <display:column title="Masa"><fmt:formatDate pattern="hh:mm aaa" value="${line.tarikhEnkuiri}"/></display:column>
                    <display:column property="tempat" title="Tempat" class="nama${line_rowNum}"/>
                    <display:column property="perkara" title="Perkara" class="nama${line_rowNum}"/>
                    <display:column property="status.nama" title="Status" class=""/>
                    <display:column property="ulasanPegawai" title="Ulasan" class="nama${line_rowNum}"/>
                </display:table>
            </div>
        </fieldset>
        <br>
        <fieldset class="aras1">
            <legend>
                Sejarah Lelongan
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiLelongan}" id="line" >
                    <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                    <display:column title="IDHakmilik" property="hakmilikPermohonan.hakmilik.idHakmilik"/>
                    <display:column title="Tarikh Lelongan Awam"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhLelong}"/></display:column>
                    <display:column title="Masa"><fmt:formatDate pattern="hh:mm aaa" value="${line.tarikhLelong}"/></display:column>
                    <display:column property="tempat" title="Tempat" class="nama${line_rowNum}"/>
                    <display:column title="Harga Rizab (RM)"><div align="right">
                            <fmt:formatNumber pattern="#,##0.00" value="${line.hargaRizab}"/>
                        </div></display:column>
                    <display:column title="Deposit (RM)"><div align="right">
                            <fmt:formatNumber pattern="#,##0.00" value="${line.deposit}"/>
                        </div></display:column>
                    <display:column title="Tarikh Akhir Bayaran"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhAkhirBayaran}"/></display:column>
                    <display:column property="kodStatusLelongan.nama" title="Status" class=""/>
                    <%--<display:column title="Ulasan" class=""/>--%>
                </display:table>
            </div>
        </fieldset>
    </div>
    <p align="center">
        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
    </p>
</s:form>
