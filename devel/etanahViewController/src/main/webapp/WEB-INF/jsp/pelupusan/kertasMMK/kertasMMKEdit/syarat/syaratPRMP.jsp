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

<script type="text/javascript">
    function deleteRow(idKandungan, f, tName)
    {
        NoPrompt();
        if (confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/kertas_MMKV2?deleteRow&idKandungan=' + idKandungan + '&tName=' + tName + '&typeName=kPerakuanPtd', q,
                    function(data) {
                        $('#page_div').html(data);

                    }, 'html');
            self.refreshpage2('kPerakuanPtd');
        }
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:hidden name="idMh" id="idMh" value="${actionBean.hakmilikPermohonan.id}"/>
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
                    <s:hidden value="${actionBean.permohonanPermitItem.kodItemPermit.royaltiTanahKerajaan}" name="kadarBayarPRMP" id="kadarBayarPRMP"/>
                </td>
            </tr>
            <tr>
                <td>
                    Luas Disyorkan :
                </td>
                <td>
                    <s:text name="luasDiluluskan" id="luasLulus" onkeyup="calculateBayaranPRMP()"/> 
                    <%--${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                    <s:hidden name="kodU" id="kodU" value="${actionBean.hakmilikPermohonan.kodUnitLuas.kod}"/>--%>
                    <s:select name="kodU" style="width:150px" value="${actionBean.hakmilikPermohonan.luasLulusUom.kod}" id="koduom" >
                        <s:option value="0">Sila Pilih</s:option>
                        <s:option value="M">Meter Persegi</s:option>
                        <s:option value="H">Hektar</s:option>
                    </s:select>

                </td>
            </tr>                      
            <tr>
                <td>Sewa Tahunan : </td>
                <td>
                    RM <s:text name="amnt" size="10" id="amaun" readonly="true"/> Setahun
                </td>
            </tr>

        </table>           
    </c:when>
    <c:when test="${actionBean.kodNegeri eq '05'}">

    </c:when>
    <c:otherwise></c:otherwise>
</c:choose>
