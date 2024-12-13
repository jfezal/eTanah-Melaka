<%-- 
    Document   : popup_view_warta
    Created on : June 03, 2011, 12:53:00 AM
    Author     : latifah.iskak
--%>


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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>

<s:form  beanclass="etanah.view.penguatkuasaan.TerimaanWartaActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend> Maklumat Terimaan Warta </legend>

            <p>
                <label>Jenis Warta :</label>
                ${actionBean.mohonRujukLuar.kodRujukan.nama}
            </p>

            <p>
                <label>No. Warta :</label>
                ${actionBean.mohonRujukLuar.noRujukan}
            </p>
            <p>
                <label>Tarikh Warta :</label>
                <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.mohonRujukLuar.tarikhRujukan}"/>&nbsp;

            </p>

            <p>
                <label>Tarikh Terima Warta :</label>
                <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.mohonRujukLuar.tarikhTerima}"/>&nbsp;

            </p>
            <p>
                <label>Tarikh Disiarkan Warta :</label>
                <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.mohonRujukLuar.tarikhDisampai}"/>&nbsp;

            </p>
            <p>
                <label>Catatan :</label>
                ${actionBean.mohonRujukLuar.catatan}
            </p>&nbsp;

        </fieldset>

        <p align="center">
            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
        </p>
        <br>
    </div>
</s:form>