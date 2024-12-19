<%-- 
    Document   : syaratPPTR
    Created on : Oct 3, 2013, 2:11:46 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pelupusan.KertasMMKV2ActionBean" name="form">
    <c:choose>
        <c:when test="${actionBean.kodNegeri eq '04'}">
            <table class="tablecloth" border="0">
                <tr>
                    <td>
                        Luas Disyorkan :
                    </td>
                    <td>
                        <s:format value="${actionBean.hakmilikPermohonan.luasTerlibat}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                    </td>
                </tr>
                <tr>
                    <td>
                        Kegunaan :
                    </td>
                    <td>
                        ${actionBean.permohonanPermitItem.kodItemPermit.nama}
                    </td>
                </tr>
                <c:if test="${actionBean.keg == 'LL' || actionBean.keg == 'LN'}">
                    <tr>
                        <td>
                            Catatan :
                        </td>
                        <td>
                            ${actionBean.permohonan.sebab}
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td>
                        Status Keluasan :
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${actionBean.hakmilikPermohonan.statusLuasDiluluskan eq 'P'}">Keluasan Penuh</c:when>
                            <c:when test="${actionBean.hakmilikPermohonan.statusLuasDiluluskan eq 'S'}">Keluasan Sebahagian</c:when>
                            <c:otherwise>
                            </c:otherwise> 
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td>
                        Luas Diluluskan :
                    </td>
                    <td>
                        <s:format value="${actionBean.hakmilikPermohonan.luasDiluluskan}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}

                    </td>
                </tr>                      
                <tr>
                    <td>Bayaran : </td>
                    <td>
                        RM <s:format value="${actionBean.amnt}" formatPattern="#,###,##0.00"/>
                    </td>
                </tr>
            </table>            
        </c:when>
        <c:when test="${actionBean.kodNegeri eq '05'}">

        </c:when>
        <c:otherwise></c:otherwise>
    </c:choose>


</s:form>
