<%-- 
    Document   : view_laporan_polis_detail
    Created on : Aug 15, 2012, 5:52:59 PM
    Author     : sitifariza.hanim
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>

<s:form  beanclass="etanah.view.penguatkuasaan.MaklumatLaporanPolisActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Laporan Polis
            </legend>

            <div class="content">
                <p>
                    <label>Nombor Laporan Polis dan Balai :</label>&nbsp;
                    ${actionBean.operasiAgensi.noRujukan}&nbsp;
                </p>
                <p>
                    <label>Tarikh Laporan :</label>&nbsp;
                    <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.operasiAgensi.tarikhRujukan}"/>&nbsp;
                </p>
                <p>
                    <label>Masa Laporan: :</label>&nbsp;
                    <fmt:formatDate pattern="hh:mm" value="${actionBean.operasiAgensi.tarikhRujukan}"/>
                    <fmt:formatDate pattern="aaa" value="${actionBean.operasiAgensi.tarikhRujukan}" var="time"/>
                    <c:if test="${time eq 'AM'}">PAGI</c:if>
                    <c:if test="${time eq 'PM'}">PETANG</c:if>
                </p>
                <p>
                    <label>Lokasi Balai Polis :</label>&nbsp;
                    ${actionBean.operasiAgensi.alamatAgensi}&nbsp;
                </p>

                <p><label>&nbsp;</label>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
                <br>
            </div>
        </fieldset>
    </div>
</s:form>

