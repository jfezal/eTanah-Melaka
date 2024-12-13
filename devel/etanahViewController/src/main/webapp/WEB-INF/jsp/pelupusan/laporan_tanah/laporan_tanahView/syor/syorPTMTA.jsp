<%-- 
    Document   : syorPTMTA (laporan_tanahView)
    Created on : May 6, 2013, 9:28:03 PM
    Author     : User
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<div id="syorPPT" align="center">
    <c:choose>
        <c:when test="${actionBean.kodNegeri eq '05'}">
            <table class="tablecloth" align="center">
                <tr>
                    <td><!--<font color="red" size="4">*</font>-->Syor : </td>
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
             <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTMTA'}">
                 <c:choose>
                     <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL' }"> 
                         <tr>
                             <td><font color="red" size="4">*</font>Bayaran (RM) :</td>
                             <td>${actionBean.amnt}</td>
                         </tr>
                         <tr>
                             <td>Keluasan diluluskan :</td>
                             <td>${actionBean.hakmilikPermohonan.luasTerlibat}&nbsp; ${actionBean.hakmilikPermohonan.luasLulusUom.nama}</td>
                         </tr>
                     </c:when>
                     <c:otherwise>
                     </c:otherwise>
                 </c:choose>                            
              </c:if>
            </table>
        <div id="pprutidaksokong">
            <table class="tablecloth" align="center">
                <tr>
                    <td><font color="red" size="4">*</font>Sebab :</td>
                    <td><s:textarea name="ulasan" rows="5" cols="80" id="ulasan" value="${actionBean.fasaPermohonan.ulasan}" /></td>
                </tr>
            </table>
        </div>
       </c:when>
     </c:choose>
    </div>
</body>


