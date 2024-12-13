<%-- 
    Document   : partial_kodgunatanah
    Created on : Apr 22, 2013, 1:01:00 PM
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
        $('#kodDun').val($('#kodDunMana').val());
    });
</script>
<s:form partial="true" beanclass="etanah.view.stripes.pelupusan.utility.UtilitiLaporanTanahActionBean">
    <table class="tablecloth" align="center">
        <tr>
            <td>
                <s:select name="kodDun" style="width:300px;" id="kodDunMana" onchange="changeVal(this.val);">
                    <s:option value="">-- Sila Pilih --</s:option>
                    <s:options-collection collection="${actionBean.listKodDun}" value="kod" label="nama" />
                </s:select>
            </td>
        </tr>
    </table>
</s:form>