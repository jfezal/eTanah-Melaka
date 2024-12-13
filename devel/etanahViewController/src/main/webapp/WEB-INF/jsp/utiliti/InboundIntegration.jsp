<%-- 
    Document   : InboundIntegration
    Created on : Oct 28, 2010, 8:53:04 PM
    Author     : faidzal
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">

    function test(f) {
        $(f).clearForm();
    }

</script>
<s:form beanclass="etanah.view.utility.InIntegrationActionBean">
    <s:hidden name="mohon.idPermohonan"/>
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Inbound Integration Jupem</legend>
            <font size="1" color="Red">Sila masukkan ID Permohonan dan ID Aliran.</font>
            <p>
                <label><font color="red">*</font>ID Permohonan :</label>
                <s:text name="idPermohonan"/>
            </p><p>
                <label><font color="red">*</font>ID Aliran :</label>
                <s:text name="idAliran"/>
</p><p>
                
                  <label>&nbsp;</label> <s:submit name="getInboundGIS" id="search" value="Terus" class="btn"/>

            </p>
        </fieldset>
 <fieldset class="aras1">
            <legend>WorkList Integration</legend>
            <font size="1" color="Red">Sila masukkan Maklumat yang diperlukan.</font>
            <p>
                <label><font color="red">*</font>ID Permohonan :</label>
                <s:text name="idPermohonanL"/></p>
            <p>
                <label><font color="red">*</font>Outcome :</label>
                <s:text name="outCome"/></p>
            <p>
                 <label><font color="red">*</font>Pengguna :</label>
                <s:text name="pengguna" />
            </p>
               
                   <label>&nbsp;</label>  <s:submit name="initiateTask" id="search" value="Terus" class="btn"/>
               
           
        </fieldset>
    </div>
</s:form>
