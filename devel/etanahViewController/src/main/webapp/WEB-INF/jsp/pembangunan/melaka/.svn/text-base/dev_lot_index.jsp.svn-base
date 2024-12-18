<%-- 
    Document   : dev_lot_index
    Created on : Sep 12, 2011, 6:52:42 PM
    Author     : NageswaraRao
--%>

<%--
    Document   : lot_index
    Created on : Feb 23, 2010, 2:05:49 PM
    Author     : wan.fairul
--%>


<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<style type="text/css">
    td.s{
        font-weight:bold;
        color:blue;
        text-align: right;
    }
</style>
<script type="text/javascript">

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form name="form1" beanclass="etanah.view.stripes.pembangunan.IndexActionBean">

    <s:messages />
    <s:errors />  
        <div class="subtitle" align="center">
        <fieldset class="aras1">
            <legend>
                Fahrasat Penyelesaian Tanah
            </legend>
            <br/>
            <table border="0">
             <tr>
                <td>Nombor Lot</td>
                <td>&nbsp;:&nbsp;</td>
                <td><c:if test="${actionBean.noLot ne null}">
                <fmt:formatNumber  pattern="00" value="${actionBean.noLot}"/>               
                </c:if>
                <c:if test="${actionBean.noLot eq null}">
                -            
                </c:if>
                </td>
            </tr>
            
            <tr>
                <td>Nombor Pelan </td>
                <td>&nbsp;:&nbsp;</td>
                <td><c:if test="${actionBean.hakmilikPermohonan.hakmilik.noPelan ne null}">
                ${actionBean.hakmilikPermohonan.hakmilik.noPelan}
                </c:if>
                <c:if test="${actionBean.hakmilikPermohonan.hakmilik.noPelan eq null}">
                -
                </c:if>
                </td>
            </tr>

            <tr>
                <td>Nombor Permohonan / Hakmilik Terdahulu </td>
                <td>&nbsp;:&nbsp;</td>
                <td><c:if test="${actionBean.permohonanSebelum.idPermohonan ne null}">
                ${actionBean.permohonanSebelum.idPermohonan}
                </c:if>            
                <c:if test="${actionBean.permohonanSebelum.idPermohonan eq null}">
                -
                </c:if>
                </td>
            </tr>
            
            <tr>
                <td>Tarikh Penerimaan Pelan </td>
                <td>&nbsp;:&nbsp;</td>
                <td>-</td>
            </tr>
            
            <tr>
                <td>Pendaftaran Hakmilik </td>
                <td>&nbsp;:&nbsp;</td>
                <td><c:if test="${actionBean.hakmilikPermohonan.hakmilik.tarikhDaftar ne null}">
                <s:format value="${actionBean.hakmilikPermohonan.hakmilik.tarikhDaftar}" formatPattern="dd/MM/yyyy"/>
                </c:if>                
                <c:if test="${actionBean.hakmilikPermohonan.hakmilik.tarikhDaftar eq null}">
                -
                </c:if>
                </td>
            </tr>

            <tr>
                <td>Pengeluaran Suratan Hakmilik</td>
                <td>&nbsp;:&nbsp;</td>
                <td>-</td>
            </tr>
            
            <tr>              
                 <td>Jenis / No. Hakmilik yang didaftarkan </td>
                 <td>&nbsp;:&nbsp;</td>
                 <td><c:if test="${actionBean.hakmilikPermohonan.hakmilik.kodHakmilik.kod ne null}">
                 ${actionBean.hakmilikPermohonan.hakmilik.kodHakmilik.kod} - ${actionBean.hakmilikPermohonan.hakmilik.kodHakmilik.nama} / <fmt:formatNumber  pattern="00" value="${actionBean.noHakmilik}"/>   
                 </c:if> 
                 <c:if test="${actionBean.hakmilikPermohonan.hakmilik.kodHakmilik.kod eq null}">
                 -
                 </c:if>
                 </td>
            </tr>
            </table>
        <br/>
        <br/>

        </fieldset>
    </div>
</s:form>
