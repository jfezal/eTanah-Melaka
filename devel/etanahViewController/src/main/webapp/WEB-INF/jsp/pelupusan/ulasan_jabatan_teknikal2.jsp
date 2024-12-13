<%-- 
    Document   : ulasan_jabatan_teknikal2
    Created on : Jan 14, 2010, 9:54:21 AM
    Author     : zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.pelupusan.PelupusanActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Tanah Yang Terlibat
            </legend>
            <p class="instr">Klik pada pautan dibawah untuk mendapatkan maklumat tanah berkaitan.</p>
            <p>
                <label>Nombor Lot/Plot :</label>
                &nbsp;
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Tanah Yang Terlibat
            </legend>
            <p>
                <label>ID Hakmilik :</label>
                <s:text name=" "/>
            </p>
            <p>
                <label>Keluasan :</label>
                &nbsp;
                Hektar
            </p>
            <p>
                <label>Kadar Nilaian (RM) :</label>
                <s:text name=" "/>
                per Hektar
            </p>
        </fieldset>
    </div>
    <p>
        <label>&nbsp;</label>
        <s:submit name="simpanhantar" value="Simpan dan Hantar" class="btn"/>
        <s:submit name="simpan" value="Simpan" class="btn"/>
        <s:reset name="reset" value="Isi Semula" class="btn"/>
        <s:submit name="keluar" value="Keluar" class="btn"/>
    </p>
</s:form>
