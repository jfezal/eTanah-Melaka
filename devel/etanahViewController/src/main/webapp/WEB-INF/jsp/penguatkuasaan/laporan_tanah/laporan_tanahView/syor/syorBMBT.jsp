<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<div id="syorPPT" align="center">
    <table class="tablecloth" align="center">
        <%--BMBT CASE--%>
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

                        <tr>
                            <td>
                                Jenis Hakmilik Disyorkan :
                            </td>
                            <td>
                                ${actionBean.hakmilikPermohonan.kodHakmilik.nama}
                            </td>
                        </tr>
                        <c:if test="${actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'PN'}">
                            <tr>
                                <td>
                                    Tempoh Pajakan :
                                </td>
                                <td>
                                    ${actionBean.hakmilikPermohonan.tempohPajakan} Tahun
                                </td>
                            </tr>
                        </c:if>
                        <tr>
                            <td>
                                Isipadu Disyorkan :
                            </td>
                            <td>
                                ${actionBean.hakmilikPermohonan.luasTerlibat} Meterpadu
                            </td>
                        </tr>    
                        <tr>
                            <td>
                                Keluasan Tanah :
                            </td>
                            <td>
                                ${actionBean.hakmilikPermohonan.hakmilik.luas} ${actionBean.hakmilikPermohonan.hakmilik.kodUnitLuas.nama}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Syarat Nyata :
                            </td>
                            <td>
                                <s:textarea name="sekatan" value="${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}" class="normal_text" readonly="true" cols="80" rows="5"/>&nbsp;           
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Sekatan Kepentingan :
                            </td>
                            <td>
                                <s:textarea name="sekatan" value="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}" class="normal_text" readonly="true" cols="80" rows="5"/>&nbsp;
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>
                <br/>
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
            </c:when>
            <c:otherwise>
            </c:otherwise>
        </c:choose>

    </table>
</div>