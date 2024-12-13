<%-- 
    Document   : dev_laporan_param_MLK
    Created on : Sep 22, 2010, 10:47:09 PM
    Author     : nursyazwani
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
        var report = '${actionBean.reportName}';
        if((report == 'DEVSMUYB_MLK.rdf'
            || report == 'DEVSUT_MLK.rdf'
            || report == 'DEVLT_MLK.rdf'
            || report == 'DEVRJKBBPTD_MLK.rdf'
            || report == 'DEVRJKBBPTG_MLK.rdf'
            || report == 'DEVRPJKBBPTG_MLK.rdf'
            || report == 'DEVSKpsnJKBB_MLK.rdf'
            || report == 'DEVST_MLK.rdf'
            || report == 'DEVSrtLPra_MLK.rdf'
            || report == 'DEVSJPPH2_MLK.rdf'
            || report == 'DEVSLA_MLK.rdf'
            || report == 'DEVRPtbgnPTG_MLK.rdf'
            || report == 'DEVSN7G_MLK.rdf'
            || report == 'DEVB7C_TSPSS_MLK.rdf'
            || report == 'DEVSP_MLK.rdf'
            || report == 'DEVSB_MLK.rdf'
            || report == 'DEVBPU_MLK.rdf'
            || report == 'DEVSIPU_MLK.rdf'
            || report == 'DEVSMD_MLK.rdf'
            || report == 'DEVLA_MLK.rdf'
            || report == 'DEVN7G_MLK.rdf'
            || report == 'DEVS7G_MLK.rdf'
            || report == 'DEVSuratRayuanTolak_MLK.rdf'
            || report == ''
            || report == ''
            || report == '') && $("#id_mohon").val() == ""){
            alert("Sila masukkan 'ID Permohonan' terlebih dahulu.");
            $("#id_mohon").focus();
        }else{
            f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
            f.submit();
        }
    }
</script>

<s:form beanclass="etanah.view.stripes.pembangunan.LaporanPembangunanMLKActionBean">
    <s:hidden name="reportName"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Parameter</legend>
            <c:set value="${actionBean.reportName}" var="reportname"/>
            <c:if test="${reportname eq 'DEVSMUYB_MLK.rdf'
                          or reportname eq 'DEVSUT_MLK.rdf'
                          or reportname eq 'DEVLT_MLK.rdf'
                          or reportname eq 'DEVRJKBBPTD_MLK.rdf'
                          or reportname eq 'DEVRJKBBPTG_MLK.rdf'
                          or reportname eq 'DEVRPJKBBPTG_MLK.rdf'
                          or reportname eq 'DEVSKpsnJKBB_MLK.rdf'
                          or reportname eq 'DEVST_MLK.rdf'
                          or reportname eq 'DEVSrtLPra_MLK.rdf'
                          or reportname eq 'DEVSJPPH2_MLK.rdf'
                          or reportname eq 'DEVSLA_MLK.rdf'
                          or reportname eq 'DEVRPtbgnPTG_MLK.rdf'
                          or reportname eq 'DEVSN7G_MLK.rdf'
                          or reportname eq 'DEVB7C_TSPSS_MLK.rdf'
                          or reportname eq 'DEVSP_MLK.rdf'
                          or reportname eq 'DEVSB_MLK.rdf'
                          or reportname eq 'DEVBPU_MLK.rdf'
                          or reportname eq 'DEVSIPU_MLK.rdf'
                          or reportname eq 'DEVSMD_MLK.rdf'
                          or reportname eq 'DEVLA_MLK.rdf'
                          or reportname eq 'DEVN7G_MLK.rdf'
                          or reportname eq 'DEVS7G_MLK.rdf'
                          or reportname eq 'DEVSuratRayuanTolak_MLK.rdf'
                          or reportname eq ''
                          or reportname eq ''
                          or reportname eq ''}">
                  <p>
                      <label><em>*</em>ID Permohonan :</label>
                      <s:text id="id_mohon" name="report_p_id_mohon" onblur="this.value = this.value.toUpperCase();"/>
                  </p>
                  <%--<p>
                      <label>ID Hakmilik :</label>
                      <s:text name="report_p_trh_mula"></s:text>
                  </p>--%>
            </c:if>
            <br>
            <p align="center">
                <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit(this.form);"/>&nbsp;
                <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
                <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
            </p>
        </fieldset>
    </div>
</s:form>


