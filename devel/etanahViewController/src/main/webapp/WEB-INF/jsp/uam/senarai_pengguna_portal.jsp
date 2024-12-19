<%-- 
    Document   : senarai_pengguna_portal
    Created on : Jun 5, 2013, 12:25:54 PM
    Author     : faidzal
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

<div id="all">
    <s:form beanclass="etanah.view.uam.PenggunaPortalActionBean">
        <s:messages/>
        <s:errors/>
        <div class="subtitle displaytag">
            <fieldset class="aras1">
                <legend>Senarai Pengguna Portal</legend>
               
            </fieldset>
            <fieldset class="aras1">
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.listPenggunaPortal}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="${pageContext.request.contextPath}/uam/pengguna_portal">
                        <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                        <display:column property="idPguna" title="ID Pengguna" style="vertical-align:baseline"/>
                        <display:column property="nama" title="Nama Pengguna" style="vertical-align:baseline"/>
                       
                        <display:column  title="Agensi" style="vertical-align:baseline"> <c:if test="${line.kodAgensi.nama eq null}">${line.kodAgensiKutipan.nama}</c:if> <c:if test="${line.kodAgensiKutipan.nama eq null}">${line.kodAgensi.nama}</c:if>
                        </display:column>
                        <%--    <display:column property="noKp" title="No Kad Pengenalan" style="vertical-align:baseline"/>
<display:column property="email" title="Email" style="vertical-align:baseline"/> --%>
                        <display:column property="noTel" title="No Telefon" style="vertical-align:baseline"/>
                        <display:column property="kodSts" title="Status" style="vertical-align:baseline"/>
                        <display:column property="infoAudit.tarikhMasuk" title="Tarikh Masuk" format="{0,date,dd/MM/yyyy}" style="vertical-align:baseline"/>
                        <display:column title="Kemaskini" style="vertical-align:baseline">
                            <s:link beanclass="etanah.view.uam.PenggunaPortalActionBean" event="update">
                                    <s:param name="idPengguna" value="${line.idPguna}" />Kemaskini
                                </s:link>
                        </display:column>
                                    <display:column title="Reset Katalaluan" style="vertical-align:baseline">
                            <s:link beanclass="etanah.view.uam.PenggunaPortalActionBean" event="resetPsswrd">
                                    <s:param name="idPengguna" value="${line.idPguna}" />Reset
                                </s:link>
                        </display:column>
                    </display:table>
                </div>
            </fieldset>
        </div>
    </s:form>
</div>