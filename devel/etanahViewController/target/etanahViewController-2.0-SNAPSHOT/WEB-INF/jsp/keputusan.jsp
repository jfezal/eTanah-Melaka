<%-- 
    Document   : status
    Created on : 17-Oct-2009, 00:39:34
    Author     : md.nurfikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<c:set value="false" var="sq"/>
<c:if test="${fn:length(actionBean.tabBean.outcome) > 0 || actionBean.tabBean.isUlasanOnly}">
    <%@include file="/WEB-INF/jsp/keputusan_pendaftar.jsp" %>
    <c:if test="${pendaftar}">        
        <c:set value="true" var="sq"/>
    </c:if>
</c:if>
<%--<c:if test="${sq eq 'true' || fn:length(actionBean.tabBean.outcome) == 0}">--%>
<br/>
<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Syor Dan Ulasan
        </legend>
        <div class="content" align="center">
            <display:table class="tablecloth" name="${actionBean.listFasa}" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="No" sortable="true" style="vertical-align:top;">${line_rowNum}</display:column >
                    <display:column property="idPengguna" title="Nama" style="vertical-align:top;"/>
                    <display:column property="keputusan.nama" title="Keputusan" style="vertical-align:top;"/>
                    <display:column title="Ulasan" style="width:80px;vertical-align:top;">
                        <textarea name="" style="background: transparent; border: 0px" cols="80" rows="10" readonly="readonly" id="text_${line_rowNum}">${line.ulasan}</textarea>
                    </display:column>
                    <display:column title="Tarikh Keputusan" style="width:90px;vertical-align:top;">
                        <fmt:formatDate value="${line.tarikhKeputusan}" pattern="dd/MM/yyyy hh:mm:ss aa"/>
                    </display:column>
            </display:table>
        </div>
        </fieldset>
    <br/>
        
    <c:if test="${!empty actionBean.listFasaSebelum}">
        <fieldset class="aras1">
            <legend>
                Syor Dan Ulasan Permohonan Sebelum ( ${actionBean.permohonan.permohonanSebelum.idPermohonan} )
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.listFasaSebelum}" cellpadding="0" cellspacing="0" id="lineSblm">
                    <display:column title="No" sortable="true" style="vertical-align:top;">${lineSblm_rowNum}</display:column >
                    <display:column property="idPengguna" title="Nama" style="vertical-align:top;"/>
                    <display:column property="keputusan.nama" title="Keputusan" style="vertical-align:top;"/>
                    <display:column title="Ulasan" style="width:80px;vertical-align:top;">
                        <textarea name="" style="background: transparent; border: 0px" cols="80" rows="10" readonly="readonly" id="text_${lineSblm_rowNum}"> ${lineSblm.ulasan}
                        </textarea>
                    </display:column>
                    <display:column title="Tarikh Keputusan" style="width:90px;vertical-align:top;">
                        <fmt:formatDate value="${lineSblm.tarikhKeputusan}" pattern="dd/MM/yyyy hh:mm:ss aa"/>
                    </display:column>
                </display:table>
            </div>
        </fieldset>
    </c:if>

    
</div>
<%--</c:if>--%>

