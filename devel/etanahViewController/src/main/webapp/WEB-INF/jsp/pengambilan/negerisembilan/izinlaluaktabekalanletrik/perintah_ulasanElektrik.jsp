<%-- 
    Document   : perintah_ulasan1
    Created on : Oct 27, 2010, 2:16:26 PM
    Author     : Programmer
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">

</script>

<s:form beanclass="etanah.view.stripes.pengambilan.PerintahUlasanElektrikActionBean">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Perintah</legend>
            <p align="left"><label>ulasan :</label>
                <s:textarea name="ulasan" rows="4" cols="40"></s:textarea>
            </p>
            <p align="right"><s:button name="" id="" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/></p>
        </fieldset>
    </div>
</s:form>

