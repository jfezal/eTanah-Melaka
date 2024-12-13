<%-- 
    Document   : dev_param_LT
    Created on : Nov 6, 2013, 11:40:01 AM
    Author     : khairul.hazwan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

    function doSubmit(f){
        var form = $(f).formSerialize();
        var id = '${actionBean.idPermohonan}';
        //alert(id);
        var report = '${actionBean.reportName}';
        //if((report == 'DEVLT_MLK.rdf') && $("#id_mohon").val() == ""){
        if((report == 'DEVLT_MLK.rdf') && id == ""){
            alert("Sila masukkan 'ID Permohonan' terlebih dahulu." + id);
            //$("#idPermohonanhidden").focus();
        }else{
            f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
            f.submit();
        }
    }
</script>

<s:form beanclass="etanah.view.stripes.pembangunan.utiliti.UtilitiLtActionBean">
    <s:hidden name="reportName"/>  
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Sila klik butang 'Papar' untuk paparan Laporan Tanah</legend>
            <c:set value="${actionBean.reportName}" var="reportname"/>
            
            <s:hidden id="id_mohon" name="report_p_id_mohon" value= "${actionBean.idPermohonan}" onblur="this.value = this.value.toUpperCase();"/>
            
            <br>
            <p align="center">
                <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit(this.form);"/>&nbsp;             
                <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
            </p>
        </fieldset>
    </div>
</s:form>


