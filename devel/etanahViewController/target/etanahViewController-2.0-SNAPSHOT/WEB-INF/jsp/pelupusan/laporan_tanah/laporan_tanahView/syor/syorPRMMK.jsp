<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<div id="syorPPT" align="center">
    <table class="tablecloth" align="center">
        <%--PTGSA CASE--%>
        <c:choose>
            <c:when test="${actionBean.kodNegeri eq '04'}">

                <tr>
                    <td>
                        Syor :
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL'}">
                                Sokong
                            </c:when>
                            <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'ST'}">
                                Tidak Sokong
                            </c:when>
                            <c:otherwise>
                                ${actionBean.fasaPermohonan.keputusan.nama}&nbsp;
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>

                <c:choose>
                    <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL' }">


                        <table class="tablecloth" align="center">
                            <tr>
                                <td>
                                    Luas Disyorkan:
                                </td>
                                <td>
                                    ${actionBean.hakmilikPermohonan.luasTerlibat}&nbsp; ${actionBean.hakmilikPermohonan.luasLulusUom.nama}&nbsp;
                                </td>
                            </tr>


                        </table> <br/>



                        <br/>


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
                            <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL'}">
                                Sokong
                            </c:when>
                            <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'ST'}">
                                Tidak Sokong  
                            </c:when>    
                        </c:choose>
                    </td>
                </tr>
                <c:choose>
                    <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL' }">


                        <table class="tablecloth" align="center">
                            <tr>
                                <td>
                                    Luas Disyorkan / Keluasan Sebelum perenggan :
                                </td>
                                <td>
                                    ${actionBean.hakmilikPermohonan.luasTerlibat}&nbsp; ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}&nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Keluasan Selepas perenggan :
                                </td>
                                <td>
                                    ${actionBean.hakmilikPermohonan.luasDiluluskan}&nbsp; ${actionBean.hakmilikPermohonan.luasLulusUom.nama}&nbsp;
                                </td>
                            </tr>


                        </table> <br/>



                        <br/>


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
