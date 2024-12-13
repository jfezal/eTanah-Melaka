<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
KEMASUKAN MAKLUMAT PERMOHONAN KLASIFIKASI BANGUNAN KOS RENDAH (Seksyen 58 Akta Hakmilik Strata)
<s:form beanclass="etanah.view.strata.urusan">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pemilik</legend>
            <p>
                 <div class="content" align="center">
            <display:table class="tablecloth" name=""  id="line">
                  <display:column title="Pilih" class="bil" group=""></display:column>
                <display:column title="Bil" class="bil" group=""></display:column>
                <display:column property="nama" title="Nama" class="nama"/>
                <display:column property="no_pengenalam" title="No Pengenalan"/>
                <display:column property="idhakmilik" title="Id Hakmilik"/>
             
            </display:table>  </p> 
                 </fieldset>
        </div>

<div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pemegang Surat Kuasa Wakil</legend>
            <p>
                 <div class="content" align="center">
            <display:table class="tablecloth" name=""  id="line">
                  <display:column title="Pilih" class="bil" group=""></display:column>
                <display:column title="Bil" class="bil" group=""></display:column>
                <display:column property="idsuratkuasawakil" title="Id Surat Kuasa Wakil" class="idsuratkuasawakil"/>
                <display:column property="pemberi" title="Pemberi"/>
                <display:column property="penerima" title="Penerima"/>
                  <display:column property="idhakmlik" title="Id Hakmilik"/>

            </display:table></fieldset>
        </div>


            <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pemohon</legend>
            <p>
                 <div class="content" align="center">
            <display:table class="tablecloth" name=""  id="line">
                  <display:column title="Pilih" class="bil" group=""></display:column>
                <display:column title="Bil" class="bil" group=""></display:column>
                <display:column property="jenis_pengenalan" title="jenis_pengenalan" class="jenis_pengenalan"/>
                <display:column property="no_pengenalan/syarikat" title="No pengenalan/ No syarikat"/>
              
            </display:table>

        </fieldset>
        </div>
             <p><br>
                <label>&nbsp;</label>
              <a href="urusan?showForm6"<s:submit name="Terus" value="Terus" class="btn"/></a>
                <a href="urusan?showForm4"<s:submit name="kembali" value="Kembali" class="btn"/></a>

            </p>
    </s:form>