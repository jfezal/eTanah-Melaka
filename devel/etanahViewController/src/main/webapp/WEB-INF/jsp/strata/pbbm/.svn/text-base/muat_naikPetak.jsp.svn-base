<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<s:form beanclass="etanah.view.strata.MuatNaikPetakActionBean">



    <fieldset class="aras1" id="row2">

        <legend>Muat Naik Jadual Petak</legend>
        <br>
        <s:file name="muatNaik"/>
        <br>
        <br>
        <s:button name="simpanJadual" value="Simpan" class="btn"/>
        <br>
        <br>
        <display:table name="" class="tablecloth" >
            <display:column title="Kod Dokumen" property="kodDokumen.kod" />
            <display:column title="Nama Dokumen" property="kodDokumen.nama" />
            <display:column title="Klasifikasi" property="klasifikasi.nama" />
            <display:column title="Dimasuk Oleh" property="infoAudit.dimasukOleh.nama" />
            <display:column title="Catatan" property="catatan" />
            <display:column title="Tarikh" property="infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
            <display:column title="Ekstrak" property="" />
        </display:table>
    </fieldset>

</s:form>