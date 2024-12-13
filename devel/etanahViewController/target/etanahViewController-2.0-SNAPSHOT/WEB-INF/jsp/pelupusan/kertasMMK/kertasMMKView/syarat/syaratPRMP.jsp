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
                        Luas Dipohon :
                    </td>
                    <td>
                        <s:format value="${actionBean.hakmilikPermohonan.luasTerlibat}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                    </td>
                </tr>
                <tr>
                    <td>Kadar Bayaran (RM):</td>
                    <td>
                        ${actionBean.permohonanPermitItem.kodItemPermit.royaltiTanahKerajaan}&nbsp;se ${actionBean.permohonanPermitItem.kodItemPermit.royaltiTanahKerajaanUom.nama}
                    </td>
                </tr>
                <tr>
                    <td>
                        Luas Disyorkan :
                    </td>
                    <td>
                        <s:format value="${actionBean.hakmilikPermohonan.luasDiluluskan}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}

                    </td>
                </tr>           
                <tr>
                    <td>Sewa Tahunan : </td>
                    <td>
                        RM <s:format value="${actionBean.amnt}" formatPattern="#,###,##0.00"/>
                    </td>
                </tr>

                <tr>
                    <td colspan="1">Syarat Tambahan :</td>
                    <td>&nbsp;</td>
                </tr>
                <c:if test="${!empty actionBean.senaraiSyarat}">
                    <c:set value="1" var="i"/>
                    <c:forEach items="${actionBean.senaraiSyarat}" var="line">
                        <tr>
                            <td style="text-align: right">${i}</td>
                            <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
                        </tr>
                        <c:set value="${i+1}" var="i"/>
                    </c:forEach>
                </c:if>
            </table>            
        </c:when>
        <c:when test="${actionBean.kodNegeri eq '05'}">
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
                    <td>Kadar Bayaran (RM):</td>
                    <td>
                        ${actionBean.permohonanPermitItem.kodItemPermit.royaltiTanahKerajaan}&nbsp;se ${actionBean.permohonanPermitItem.kodItemPermit.royaltiTanahKerajaanUom.nama}
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
        <c:otherwise></c:otherwise>
    </c:choose>


</s:form>
