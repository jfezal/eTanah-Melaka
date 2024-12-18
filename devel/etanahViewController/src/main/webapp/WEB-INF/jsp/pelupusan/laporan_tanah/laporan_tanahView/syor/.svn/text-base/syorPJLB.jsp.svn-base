<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<div id="syorPPT" align="center">
    <table class="tablecloth" align="center">
        <c:choose>
            <c:when test="${actionBean.kodNegeri eq '04'}">
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
                        Tempoh :
                    </td>
                    <td>
                        ${actionBean.hakmilikPermohonan.tempohPajakan}&nbsp; Tahun.                        
                    </td>
                </tr>
                <tr>
                    <td>
                        Luas Disyorkan
                    </td>
                    <td>
                        ${actionBean.hakmilikPermohonan.luasDiluluskan}&nbsp; ${actionBean.hakmilikPermohonan.luasLulusUom.nama}&nbsp;                 
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
            <c:when test="${actionBean.kodNegeri eq '05'}">
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
                        Tempoh :
                    </td>
                    <td>
                        ${actionBean.hakmilikPermohonan.tempohPajakan}&nbsp; Tahun.                        
                    </td>
                </tr>
                <tr>
                    <td>
                        Luas Disyorkan
                    </td>
                    <td>
                        ${actionBean.hakmilikPermohonan.luasDiluluskan}&nbsp; ${actionBean.hakmilikPermohonan.luasLulusUom.nama}&nbsp;                 
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
    </table>
</div>