<%-- 
    Document   : maklumat_terperinci_pemilik
    Created on : Jul 13, 2011, 10:26:08 AM
    Author     : zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.strata.LaporanSiasatKuatkuasaStrataActionBean" name="form1">
    <s:messages/>
    <s:errors/>
   
    <div class="subtitle">
            <fieldset class="aras1">
                <table><tr><td><b>2.1 Maklumat Terperinci Pemilik</b></td></tr></table>
                <br>
    <display:table class="tablecloth" name="${actionBean.hmPihak}" cellpadding="0" cellspacing="0" id="line"
                   requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
        <display:column  title="Nama" class="nama">
            ${line.nama}
        </display:column>
        <display:column property="noPengenalan" title="Nombor Pengenalan" />
            <display:column title="Alamat" >${line.alamat1}
                <c:if test="${line.alamat2 ne null}"> , </c:if>
                ${line.alamat2}
                <c:if test="${line.alamat3 ne null}"> , </c:if>
                ${line.alamat3}
                <c:if test="${line.alamat4 ne null}"> , </c:if>
                ${line.alamat4}</display:column>
                <display:column property="poskod" title="Poskod" >${line.poskod}</display:column>
            <display:column property="negeri.nama" title="Negeri">${line.negeri}</display:column>
    </display:table>
            </fieldset></div>


</s:form>