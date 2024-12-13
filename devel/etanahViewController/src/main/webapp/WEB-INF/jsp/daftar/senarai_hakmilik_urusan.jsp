<%-- 
    Document   : senarai_hakmilik_urusan
    Created on : Apr 14, 2010, 2:22:28 PM
    Author     : khairil
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Maklumat Urusan Hakmilik
        </legend>
        <div align="center" class="content">
            <display:table class="tablecloth" name="${actionBean.urusanList}"  cellpadding="0" cellspacing="0"
                           requestURI="/common/maklumat_hakmilik_permohonan" id="line2" pagesize="10">
                <display:column title="Bil">${line2_rowNum}</display:column>
                <display:column property="kodUrusan.nama" title="Urusan"/>
                <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                <display:column property="idPerserahan" title="ID Perserahan"/>
                <display:column title="Status">
                    <c:if test="${line2.aktif eq 'Y'}">Daftar</c:if>
                    <c:if test="${line2.aktif eq 'T'}">Batal</c:if>
                </display:column>
                <display:column title="Tarikh Transaksi">
                    <c:if test="${line2.aktif eq 'Y'}">
                        <fmt:formatDate value="${line2.tarikhDaftar}" pattern="dd/MM/yyyy hh:mm:ss"/>
                    </c:if>
                    <c:if test="${line2.aktif eq 'T'}">
                        <fmt:formatDate value="${line2.tarikhBatal}" pattern="dd/MM/yyyy hh:mm:ss"/>
                    </c:if>
                </display:column>
            </display:table>
        </div>
    </fieldset>
</div>