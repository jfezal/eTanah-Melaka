<%--
    Document   : carian_laporan_tanah
    Created on :Aug 23, 2011, 12:23:22 PM
    Author     : latifah.iskak
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/src/main/webapp/pub/scripts/datepicker.js"></script>
<script language="javascript" type="text/javascript"src="${pageContext.request.contextPath}/src/main/webapp/pub/js/datetimepicker.js"></script>
<script language="javascript" type="text/javascript"src="${pageContext.request.contextPath}/JavaScript/datetimepicker.js"></script>



<script language="javascript" type="text/javascript">

</script>

<s:form beanclass="etanah.view.penguatkuasaan.UtilitiLaporanTanahActionBean" name="form1">
    <s:messages/>
    <s:errors/>
    <s:hidden name="permohonan.idPermohonan" />

    <div class="subtitle">
        <fieldset class="aras1">

            <legend>Carian Laporan Tanah</legend>
            <br>
            <p><label for="idPermohonan">Id Permohonan/ID Perserahan :</label>
                <input type="text" name="idPermohonan" id="idMohon" onkeyup="this.value=this.value.toUpperCase();"/>
                <s:submit name="searchLaporanTanah" value="Cari" class="btn"/>
            </p>

        </fieldset>
    </div>

</s:form>

