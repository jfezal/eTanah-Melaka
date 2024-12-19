<%-- 
    Document   : partial_kodgunatanah
    Created on : Apr 22, 2013, 1:01:00 PM
    Author     : afham @modified hazirah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    $(document).ready( function(){
        <%--$('#kodDun').val($('#kodDunMana').val());--%>
        $('#kodBPMMana').val();
    });
</script>
<s:form partial="true" beanclass="etanah.view.stripes.pelupusan.maklumat_tanah.MaklumatTanahV2ActionBean">
    <table class="tablecloth" align="center">
        <tr>
            <td>
                <%--${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.kod}--%>
                <s:select name="hakmilikPermohonan.bandarPekanMukimBaru.kod" value="" style="width:300px;" id="kodBPMMana" >
                    <s:option value="">-- Sila Pilih --</s:option>
                    <s:options-collection collection="${actionBean.listKodBPM}" value="kod" label="nama" />
                </s:select>
            </td>
        </tr>
    </table>
</s:form>