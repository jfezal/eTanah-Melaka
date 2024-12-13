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
                                Kegunaan :
                            </td>
                            <td>
                                ${actionBean.permohonanPermitItem.kodItemPermit.nama}&nbsp;                                        
                            </td>
                        </tr>
                        <c:if test="${actionBean.permohonanPermitItem.kodItemPermit.kod eq 'LN' or actionBean.permohonanPermitItem.kodItemPermit.kod eq 'LL'}">
                            <tr>
                                <td>
                                    Catatan :
                                </td>
                                <td>
                                    ${actionBean.catatan}&nbsp;                                
                                </td>
                            </tr>
                        </c:if>
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
                                Keluasan :
                            </td>
                            <td>
                                <c:if test="${actionBean.hakmilikPermohonan.statusLuasDiluluskan eq 'P'}">
                                    Keluasan Penuh
                                </c:if>
                                <c:if test="${actionBean.hakmilikPermohonan.statusLuasDiluluskan eq 'S'}">
                                    Keluasan Sebahagian
                                </c:if>                               
                            </td>
                        </tr>
                        <c:if test="${actionBean.hakmilikPermohonan.statusLuasDiluluskan eq 'S'}">
                            <tr>
                                <td>
                                    Luas Diluluskan:
                                </td>
                                <td>
                                    ${actionBean.hakmilikPermohonan.luasDiluluskan}&nbsp; ${actionBean.hakmilikPermohonan.luasLulusUom.nama}&nbsp;
                                </td>
                            </tr>
                        </c:if>
                        <tr>
                            <td>
                                Syarat Tambahan :
                            </td>
                            <td>
                                ${actionBean.ulsn}&nbsp;
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
                                Kegunaan :
                            </td>
                            <td>
                                ${actionBean.permohonanPermitItem.kodItemPermit.nama}&nbsp;                                        
                            </td>
                        </tr>
                        <c:if test="${actionBean.permohonanPermitItem.kodItemPermit.kod eq 'LN' or actionBean.permohonanPermitItem.kodItemPermit.kod eq 'LL'}">
                            <tr>
                                <td>
                                    Catatan :
                                </td>
                                <td>
                                    ${actionBean.catatan}&nbsp;                                
                                </td>
                            </tr>
                        </c:if>
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
                                Keluasan :
                            </td>
                            <td>
                                <c:if test="${actionBean.hakmilikPermohonan.statusLuasDiluluskan eq 'P'}">
                                    Keluasan Penuh
                                </c:if>
                                <c:if test="${actionBean.hakmilikPermohonan.statusLuasDiluluskan eq 'S'}">
                                    Keluasan Sebahagian
                                </c:if>                               
                            </td>
                        </tr>
                        <c:if test="${actionBean.hakmilikPermohonan.statusLuasDiluluskan eq 'S'}">
                            <tr>
                                <td>
                                    Luas Diluluskan:
                                </td>
                                <td>
                                    ${actionBean.hakmilikPermohonan.luasDiluluskan}&nbsp; ${actionBean.hakmilikPermohonan.luasLulusUom.nama}&nbsp;
                                </td>
                            </tr>
                        </c:if>
                        <tr>
                            <td>
                                Syarat Tambahan :
                            </td>
                            <td>
                                ${actionBean.ulsn}&nbsp;
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
            <c:otherwise>
            </c:otherwise>
        </c:choose>
    </table>
</div>