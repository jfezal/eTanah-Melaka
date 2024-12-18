<%-- 
    Document   : partial_kodseksyen
    Created on : May 30, 2013, 2:25:36 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    $(document).ready( function(){
        $('#kodSeksyen').val($('#seksyen').val());
    });
</script>
<s:form partial="true" beanclass="etanah.view.stripes.pelupusan.maklumat_tanah.MaklumatTanahV2ActionBean">
    <table class="tablecloth" align="center">
        <tr>
            <td>
                <s:select name="seksyen" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.kodSeksyen.kod}" style="width:300px;" id="kodSeksyen">
                    <s:option value="">-- Sila Pilih --</s:option>
                    <s:options-collection collection="${actionBean.listKodSeksyen}" value="kod" label="nama" />
                </s:select>
            </td>
        </tr>
    </table>
</s:form>
