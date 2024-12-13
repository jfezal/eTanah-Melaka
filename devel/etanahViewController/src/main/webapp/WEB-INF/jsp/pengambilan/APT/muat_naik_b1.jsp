<%-- 
    Document   : muat_naik
    Created on : Feb 12, 2010, 8:19:28 AM
    Author     : fikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<s:errors/>
<s:messages/>
<div id="error"/>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:useActionBean beanclass="etanah.view.ListUtil" var="listUtil" />
<s:form beanclass="etanah.view.stripes.pengambilan.sek8.KemasukanPelanPAActionBean">
    <fieldset class="aras1">
        <legend>Muat Naik</legend>

        <p><label><font color="red">*</font>Lot:</label>
                    <s:text id="idPermohonan" name="noLot"></s:text>
                    <s:hidden id="idPermohonan" name="id"></s:hidden>
                    <s:hidden id="idPermohonan" name="kodDaerah"></s:hidden>
                    <s:hidden id="idPermohonan" name="kodMukim"></s:hidden>
                    <s:hidden id="idPermohonan" name="kodSeksyen"></s:hidden>

            </p>

            <p><label><font color="red">*</font>Luas:</label>
                    <s:text id="idPermohonan" name="luas"></s:text>
            </p>
            <p><label><font color="red">*</font>No Pelan Akui:</label>
                    <s:text id="idPermohonan" name="noPa"></s:text>
            </p>

            <p><label><font color="red">*</font>Unit Ukur :</label>

         
            <s:select name="kodUOM" value="${actionBean.kodUOM}">
                <s:option value="">Sila Pilih</s:option>
                <s:option value="H">Hektar</s:option>
                <s:option value="M">Meter Persegi</s:option>
            </s:select>
            </p>
            <p><label><font color="red">*</font>Pelan :</label>
                    <s:file name="file"/>
        </p>
        <label>&nbsp;</label>&nbsp;
        <s:submit name="simpanPopup" value="Simpan" class="btn"/>
        <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
    </p>
</fieldset>
</s:form>
