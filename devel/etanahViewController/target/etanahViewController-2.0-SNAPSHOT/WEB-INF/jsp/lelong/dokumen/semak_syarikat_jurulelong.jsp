<%-- 
    Document   : semak_syarikat_jurulelong
    Created on : Jul 3, 2013, 10:34:09 PM
    Author     : nur.aqilah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.lelong.dokumen.FolderAction">
    <div class="subtitle">
        <br/>
        <fieldset class="aras1">
            <legend>
                Maklumat Syarikat Jurulelong
            </legend>
        </fieldset>

        <div class="content">
            <br/>
            <p>
                <label><font color="red">*</font>Nama Syarikat : </label>
                    <s:text id="nama" name="sytJuruLelong.nama" readonly="true" value="${actionBean.sytJuruLelong.nama}"onchange="this.value=this.value.toUpperCase();" style="width:260px;"/>
                    <s:hidden id="idSyarikat" name="sytJuruLelong.idSytJlb"/>
            </p>
            <p>
                <label><font color="red">*</font>Jenis Pengenalan : </label>
                    <s:text id="jenis" name="sytJuruLelong.jenisPengenalan.nama" readonly="true"/>
            </p>
            <p>
                <label> <font color="red">*</font>Nombor Pengenalan : </label>
                    <s:text id="nokp1" name="sytJuruLelong.noPengenalan" style="width:150px;" readonly="true"/>
            </p>

            <p>
                <label><font color="red">*</font>Alamat : </label>
                    <s:text id="alamat1" name="sytJuruLelong.alamat1" size="40" maxlength="40" onchange="this.value=this.value.toUpperCase();" readonly="true"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="sytJuruLelong.alamat2" size="40" maxlength="40" onchange="this.value=this.value.toUpperCase();" readonly="true"/>&nbsp;
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="sytJuruLelong.alamat3" size="40" maxlength="40" onchange="this.value=this.value.toUpperCase();" readonly="true"/>&nbsp;
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="sytJuruLelong.alamat4" size="40" maxlength="40" onchange="this.value=this.value.toUpperCase();" readonly="true"/>&nbsp;
            </p>
            <p>
                <label><font color="red">*</font>Poskod : </label>
                <s:text id="poskod" name="sytJuruLelong.poskod" size="19" maxlength="5" readonly="true" style="width:150px;"/>&nbsp;
            </p>
            <p>
                <label><font color="red">*</font>Negeri : </label>
                <s:text id="poskod" name="sytJuruLelong.negeri.nama" size="19" maxlength="5" readonly="true" style="width:150px;"/>&nbsp;

            </p>
            <p>
                <label>Nombor Telefon Pejabat : </label>
                <s:text id="telefon" name="sytJuruLelong.noTelefon1" readonly="true" style="width:150px;"/>&nbsp;
            </p>
            <p>
                <label>Nombor Telefon Bimbit : </label>
                <s:text name="sytJuruLelong.noTelefon2" readonly="true" style="width:150px;"/>&nbsp;
            </p>
            <br/>
            <p align="center"><s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/></p>


        </div>
    </div>
</s:form>
