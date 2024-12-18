<%--
    Document   :  syorPPRU.jsp (laporan_tanahView)
    Created on :  March 22, 2012, 10:35:00 AM
    Author     :  Shazwan
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<div id="syorPPT" align="center">
    <table class="tablecloth" align="center">
        <c:choose>
            <c:when test="${actionBean.kodNegeri eq '04'}">
                <tr>
                    <td>
                        Syor : 
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL'}">Sokong</c:when>
                            <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'ST'}">Tidak Sokong</c:when>
                            <c:otherwise>
                                ${actionBean.fasaPermohonan.keputusan.nama}&nbsp;
                            </c:otherwise>                                                    
                        </c:choose>                                             
                    </td>
                </tr>
                <c:choose>
                    <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL' }">
                        <tr>
                            <td>
                                Bayaran (RM) :
                            </td>
                            <td>
                                ${actionBean.permohonanTuntutanKos.amaunTuntutan}&nbsp;                                
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Keluasan Diluluskan (Isipadu) :
                            </td>
                            <td>
                                ${actionBean.hakmilikPermohonan.luasDiluluskan}&nbsp; ${actionBean.hakmilikPermohonan.luasLulusUom.nama}&nbsp;    
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Tempoh :
                            </td>
                            <td>
                                ${actionBean.hakmilikPermohonan.tempohPajakan}&nbsp; Tahun.
                            </td>
                        </tr>
                    </c:when>
                    <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'ST' }">
                        <tr>
                            <td>
                                Sebab :
                            </td>
                            <td>
                                <c:if test="${actionBean.fasaPermohonan.ulasan eq null}">
                                    - &nbsp;
                                </c:if>
                                <c:if test="${actionBean.fasaPermohonan.ulasan ne null}">
                                    ${actionBean.fasaPermohonan.ulasan}&nbsp;
                                </c:if>
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:when test="${actionBean.kodNegeri eq '05'}">
                <tr>
                    <td>
                        Syor : 
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL'}">Sokong</c:when>
                            <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'ST'}">Tidak Sokong</c:when>
                            <c:otherwise>
                                ${actionBean.fasaPermohonan.keputusan.nama}&nbsp;
                            </c:otherwise>                                                    
                        </c:choose>                                             
                    </td>
                </tr>
                <c:choose>
                    <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL' }">
                        <tr>
                            <td>
                                Keluasan Diluluskan :
                            </td>
                            <td>
                                ${actionBean.hakmilikPermohonan.luasDiluluskan} ${actionBean.hakmilikPermohonan.luasLulusUom.nama}&nbsp; <%--${actionBean.hakmilikPermohonan.luasLulusUom.nama}&nbsp;--%>    
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Tempoh :
                            </td>
                            <td>
                                ${actionBean.hakmilikPermohonan.tempohPajakan}&nbsp; Tahun
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Bayaran (RM) :
                            </td>
                            <td><s:format formatPattern="#,##0.00" value="${actionBean.permohonanTuntutanKos.amaunTuntutan}"/></td>                             
                        </tr>
                    </c:when>
                    <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'ST' }">
                        <tr>
                            <td>
                                Sebab :
                            </td>
                            <td>
                                <c:if test="${actionBean.fasaPermohonan.ulasan eq null}">
                                    - &nbsp;
                                </c:if>
                                <c:if test="${actionBean.fasaPermohonan.ulasan ne null}">
                                    ${actionBean.fasaPermohonan.ulasan}&nbsp;
                                </c:if>
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
            </c:otherwise>
        </c:choose>
    </table>
</div>