<%-- 
    Document   : senaraiPihak
    Created on : Jan 31, 2011, 2:57:54 PM
    Author     : faidzal
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.strata.PermohonanStrataPKKRActionBean" name="form1">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Pemilik Tanah</legend>
                <br>
    <display:table class="tablecloth" name="${actionBean.listPihak}" cellpadding="0" cellspacing="0" id="line"
                   requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
        <display:column  title="Nama" class="nama">
            ${line.pihak.nama}
        </display:column>
        <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
       <%-- <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
        <display:column property="jenis.nama" title="Jenis Pihak"/>--%>
            <display:column title="Alamat" >${line.pihak.suratAlamat1}
                <c:if test="${line.pihak.suratAlamat2 ne null}"> , </c:if>
                ${line.pihak.suratAlamat2}
                <c:if test="${line.pihak.suratAlamat3 ne null}"> , </c:if>
                ${line.pihak.suratAlamat3}
                <c:if test="${line.pihak.suratAlamat4 ne null}"> , </c:if>
                ${line.pihak.suratAlamat4}</display:column>
            <display:column property="pihak.suratPoskod" title="Poskod" />
            <display:column property="pihak.suratNegeri.nama" title="Negeri" />
    </display:table>
            </fieldset></div>


</s:form>