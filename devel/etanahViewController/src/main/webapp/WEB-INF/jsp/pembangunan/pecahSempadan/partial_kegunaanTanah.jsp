<%-- 
    Document   : partial_kegunaanTanah
    Created on : Jul 1, 2010, 3:56:09 PM
    Author     : nursyazwani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    $(document).ready( function(){
        $('#kodTanah').val($('#kodGunaTanah').val());
    });
</script>

<s:form partial="true" beanclass="etanah.view.stripes.pembangunan.MaklumatPecahSempadanActionBean">

    <p><label>Kegunaan Tanah : </label>
        <s:select name="kodGunaTanah" id="kodTanah" style="width:300px;">
            <s:option value="">Sila Pilih</s:option>
            <s:options-collection collection="${actionBean.listGT}" label="nama" value="kod" />
        </s:select>
    </p>
</s:form>
