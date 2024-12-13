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
                        ${actionBean.fasaPermohonan.keputusan.nama}&nbsp;
                    </td>
                </tr>
                <c:choose>
                    <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL' }">
                        <tr>
                            <td>
                                Bayaran (RM) :
                            </td>
                            <td>
                                ${actionBean.permohonanTuntutanKos.amaunTuntutan}&nbsp; Setahun                            
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Tempoh:
                            </td>
                            <td>
                               ${actionBean.hakmilikPermohonan.tempohPajakan}&nbsp; Tahun.               
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Isipadu Disyorkan:
                            </td>
                            <td>
                               <%--${actionBean.hakmilikPermohonan.luasDiluluskan}&nbsp; ${actionBean.hakmilikPermohonan.luasLulusUom.nama}&nbsp;--%>
                            ${actionBean.hakmilikPermohonan.luasDiluluskan}&nbsp;Meter Padu&nbsp;
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
                        ${actionBean.fasaPermohonan.keputusan.nama}&nbsp;
                    </td>
                </tr>
            </c:when>
            <c:otherwise>
            </c:otherwise>
        </c:choose>

    </table>
</div>