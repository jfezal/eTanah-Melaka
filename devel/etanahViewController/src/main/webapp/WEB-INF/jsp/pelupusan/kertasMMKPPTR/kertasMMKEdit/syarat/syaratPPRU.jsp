<%-- 
    Document   : syaratPPTR
    Created on : Oct 3, 2013, 2:11:28 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
        <s:hidden name="idMh" id="idMh" value="${actionBean.hakmilikPermohonan.id}"/>
        <c:choose>
            <c:when test="${actionBean.kodNegeri eq '04'}">
                <table class="tablecloth" border="0">
                    <tr>
                        <td>
                            Isipadu Disyorkan :
                        </td>
                        <td>
                            <s:format value="${actionBean.hakmilikPermohonan.luasTerlibat}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Isipadu Diluluskan :
                        </td>
                        <td>
                            <s:text name="hakmilikPermohonan.luasDiluluskan" id="luasLulus" formatPattern="#,###,##0.0000"/> 
                            ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                            <s:hidden name="kodU" id="kodU" value="${actionBean.hakmilikPermohonan.kodUnitLuas.kod}"/>

                        </td>
                    </tr>
                    <tr>
                        <td>Tempoh Pajakan:</td>
                        <td>
                            <s:text name="hakmilikPermohonan.tempohPajakan" id="tempohPajakan" size="10"/> Tahun
                        </td>
                    </tr>                              
                    <tr>
                        <td>Bayaran : </td>
                        <td>
                            RM <s:text name="amnt" size="10" onchange="CurrencyFormatted(this.value)"/>
                        </td>
                    </tr>
                </table>           
        </c:when>
        <c:when test="${actionBean.kodNegeri eq '05'}">
           
        </c:when>
        <c:otherwise></c:otherwise>
    </c:choose>
