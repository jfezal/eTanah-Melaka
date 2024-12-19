<%-- 
    Document   : kemasukanLMCRG
    Created on : Feb 21, 2013, 11:14:35 AM
    Author     : Shazwan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<div class="subtitle">
    <fieldset class="aras1">
        <c:choose>
            <c:when test="${actionBean.negeri eq '04'}">                        
                <table class="tablecloth" align="center">
                    <c:if test="${fn:length(actionBean.senaraiKelompok) ne 0}">
                        <display:table class="tablecloth" name="${actionBean.mohonHakmilikKelompok}"
                                       id="line" pagesize="10" style="width:60%"
                                       requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_tanahV3">
                            <c:set var="row_num" value="${row_num+1}"/>
                            <c:set var="bcolor" value=""/>
                            <c:set var="title" value="ID Permohonan"/>

                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG'}">
                                <display:column title="ID Permohonan" style="width:10%">
                                    ${line.permohonan.idPermohonan}
                                </display:column>
                                <display:column title="Logam/Mineral yang dicari">
                                    ${line.permohonan.catatan}
                                </display:column>
                                <display:column title="Tujuan">
                                    ${line.permohonan.sebab}
                                </display:column>
                            </c:if>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'LMCRG'}">
                                    <display:column title="ID Permohonan" style="width:10%">
                                        ${line.permohonan.idPermohonan}
                                    </display:column>
                                    <display:column title="Tujuan">
                                        ${line.permohonan.sebab}
                                    </display:column>
                                </c:if>
                        </display:table>
                    </c:if>

                    <c:if test="${fn:length(actionBean.senaraiKelompok) eq 0}">
                        <c:choose>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG'}">
                                <tr>
                                    <td><font color="red" size="4">*</font>Logam/Mineral yang dicari :</td>
                                    <td>
                                        ${actionBean.permohonan.catatan}
                                    </td>
                                </tr>
                                <tr>
                                    <td>Tujuan:</td>
                                    <td>
                                        ${actionBean.permohonan.sebab}
                                    </td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td><font color="red" size="5">*</font></td>
                                    <td>Tujuan:</td>
                                    <td>
                                        ${actionBean.permohonan.sebab}
                                    </td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </table>       
                <br/>
            </c:when>
            <c:when test="${actionBean.negeri eq '05'}">                        
                <table class="tablecloth" align="center">  
                    <c:choose>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'RLPS'}">
                            <tr>
                                <td>Tujuan:</td>
                                <td>
                                    ${actionBean.permohonan.permohonanSebelum.sebab}
                                </td>
                            </tr>
                        </c:when>
                        <c:otherwise>   
                            <tr>
                                <td>Tujuan:</td>
                                <td>
                                    ${actionBean.permohonan.sebab}
                                </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>

                </table>       
                <br/>
            </c:when>    
        </c:choose>
    </fieldset>
</div>