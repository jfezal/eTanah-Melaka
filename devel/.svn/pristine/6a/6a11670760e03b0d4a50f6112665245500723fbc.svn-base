<%-- 
    Document   : partial_kodBPM
    Created on : Oct 03, 2013, 1:01:00 PM
    Author     : Shazwan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    $(document).ready( function(){
        $('#mpb').val($('#kodBPM').val());
    });
</script>
<s:form partial="true" beanclass="etanah.view.stripes.pelupusan.utility.SemakStatusPTActionBean">
        <s:select name="kodBPM" value="${actionBean.kodBPM}" id="mpb" >
            <s:option value="">Sila Pilih</s:option>
            <s:options-collection collection="${actionBean.senaraiKodBPM}" label="nama" value="kod" />
        </s:select>
</s:form>