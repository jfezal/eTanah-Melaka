<%-- 
    Document   : maklumat_pengarah_pemegang_saham
    Created on : Jan 13, 2010, 11:15:36 AM
    Author     : zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.pelupusan.PelupusanActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pengarah/Pemegang Saham
            </legend>
            <p>
                <label>Nama : </label>
                <s:text name=""/>
            </p>
            <p>
                <label>Jenis Pengenalan : </label>
                <s:select name="">
                    <s:option value="">-- Sila Pilih --</s:option>
                </s:select>
            </p>
            <p>
                <label>Nombor Pengenalan : </label>
                <s:text name=""/>
            </p>
            <p>
                <label>Warganegara : </label>
                <s:select name="">
                    <s:option value="">-- Sila Pilih --</s:option>
                </s:select>
            </p>
            <p>
                <label>Jawatan : </label>
                <s:text name=""/>
            </p>
        </fieldset>
    </div>
    <p>
        <label>&nbsp;</label>
        <s:submit name="simpantambah" value="Simpan dan Tambah" class="btn"/>
        <s:submit name="simpan" value="Simpan" class="btn"/>
        <s:reset name="reset" value="Isi Semula" class="btn"/>
        <s:submit name="keluar" value="Keluar" class="btn"/>
    </p>
</s:form>
