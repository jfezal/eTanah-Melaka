<%-- 
    Document   : senarai_dokumen_disertakan
    Created on : Jan 14, 2010, 1:59:18 PM
    Author     : zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.pelupusan.PelupusanActionBean">
    <p>LAPORAN TANAH</p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Dokumen Disertakan
            </legend>
            <p>
                <label>Nama Dokumen :</label>
                <s:select name="">
                    <s:option value="">-- Sila Pilih --</s:option>
                </s:select>
            </p>
            <p>
                <label>Perihal Dokumen :</label>
                <s:textarea name="" rows="5" cols="70"/>
            </p>
            <p></p>
        </fieldset>
        <p>&nbsp;</p>
        <label>&nbsp;</label>
        <s:submit name="simpantambah" value="Simpan dan Tambah" class="btn"/>
        <s:submit name="simpan" value="Simpan" class="btn"/>
        <s:reset name="reset" value="Isi Semula" class="btn"/>
        <s:submit name="keluar" value="Keluar" class="btn"/>
    </div>
</s:form>

