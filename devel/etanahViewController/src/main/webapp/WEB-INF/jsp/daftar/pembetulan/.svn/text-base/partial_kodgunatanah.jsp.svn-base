<%-- 
    Document   : partial_kodgunatanah
    Created on : Jan 15, 2010, 3:17:33 PM
    Author     : wan.fairul
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
<s:form partial="true" beanclass="etanah.view.stripes.nota.pembetulanActionBean">
    <p>
        <s:select name="permohonanPembetulanHakmilik.kegunaanTanah.kod" id="kodTanah">
            <s:options-collection collection="${actionBean.listKodGunaTanahByKatTanah}" label="nama" value="kod" />
        </s:select>
   </p>
</s:form>