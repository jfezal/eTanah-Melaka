<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.strata.PermohonanStrataActionBean">
    <s:messages/>
    <s:errors/>
   
    <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Pemilik</legend>
                <br>
    <display:table class="tablecloth" name="${actionBean.list}" cellpadding="0" cellspacing="0" id="line"
                   requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
        <display:column  title="Nama" class="nama">
            ${line.pihak.nama}
        </display:column>
        <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
       <%-- <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
        <display:column property="jenis.nama" title="Jenis Pihak"/>--%>

         <%--Commented @NS 17-07-2012--%>
            <%--<display:column title="Alamat" >${line.pihak.suratAlamat1}
                <c:if test="${line.pihak.suratAlamat2 ne null}"> , </c:if>
                ${line.pihak.suratAlamat2}
                <c:if test="${line.pihak.suratAlamat3 ne null}"> , </c:if>
                ${line.pihak.suratAlamat3}
                <c:if test="${line.pihak.suratAlamat4 ne null}"> , </c:if>
                ${line.pihak.suratAlamat4}</display:column>
            <display:column property="pihak.suratPoskod" title="Poskod" />
            <display:column property="pihak.suratNegeri.nama" title="Negeri" />--%>

            <%--added @NS 17-07-2012--%>
            <%--display:column title="Alamat" >${line.pihak.alamat1}
                <c:if test="${line.pihak.alamat2 ne null}"> , </c:if>
                ${line.pihak.alamat2}
                <c:if test="${line.pihak.alamat3 ne null}"> , </c:if>
                ${line.pihak.alamat3}
                <c:if test="${line.pihak.alamat4 ne null}"> , </c:if>
                ${line.pihak.alamat4}</display:column>
            <display:column property="pihak.poskod" title="Poskod" />
            <display:column property="pihak.negeri.nama" title="Negeri" /--%>
  
            
            <%-- ida update --%>       
            <display:column title="Alamat" >${line.alamat1}
                <c:if test="${line.alamat2 ne null}"> , </c:if>
                ${line.alamat2}
                <c:if test="${line.alamat3 ne null}"> , </c:if>
                ${line.alamat3}
                <c:if test="${line.alamat4 ne null}"> , </c:if>
                ${line.alamat4}</display:column>
            <display:column property="poskod" title="Poskod" />
            <display:column property="negeri.nama" title="Negeri" />
            
            
    </display:table>
                <br/>
            </fieldset></div>


</s:form>