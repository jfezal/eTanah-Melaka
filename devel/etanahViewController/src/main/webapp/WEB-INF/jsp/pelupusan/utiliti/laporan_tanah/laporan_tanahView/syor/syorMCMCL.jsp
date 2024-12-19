<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<div id="syorPPT" align="center">
    <table class="tablecloth" align="center">
        <%--MCMCL CASE--%>
        <c:choose>
            <c:when test="${actionBean.kodNegeri eq '04'}">
                <tr>
                    <td>
                        Syor :
                    </td>
                    <td>
                        ${actionBean.fasaPermohonan.keputusan.nama}&nbsp;
                    </td>
                </tr>
            </c:when>
            <c:when test="${actionBean.kodNegeri eq '05'}">
                <tr>
                    <td>
                        Syor :
                    </td>
                    <td>
                        ${actionBean.fasaPermohonan.keputusan.nama}&nbsp;
                    </td>
                </tr>
            </c:when>
            <c:otherwise>
            </c:otherwise>
        </c:choose>
        <c:set var="i" value="1" />
        <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line">
            <tr><td style="text-align: right">${i}</td>
                <td><s:textarea value="${line.ulasan}" cols="80" readonly="true" name="syorUlasan" rows="5"/></td>
            </tr>
            <c:set var="i" value="${i+1}" />
        </c:forEach>
    </table>
</div>