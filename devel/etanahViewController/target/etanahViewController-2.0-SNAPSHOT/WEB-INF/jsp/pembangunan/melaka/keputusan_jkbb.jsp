<%-- 
    Document   : keputusan_jkbb
    Created on : Dec 8, 2010, 10:37:52 AM
    Author     : nursyazwani
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>

<s:form beanclass="etanah.view.stripes.pembangunan.KeputusanJKBBActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Keputusan JKBB</legend>
            <div class="content" align="center">
                <p>
                    <label>Keputusan :</label>
                    ${actionBean.fasaPermohonan.keputusan.nama}
                </p><br>
                <p>
                    <label>Ulasan :</label>
                    ${actionBean.fasaPermohonan.ulasan}
                </p>
            </div>
        </fieldset>
    </div>
</s:form>

