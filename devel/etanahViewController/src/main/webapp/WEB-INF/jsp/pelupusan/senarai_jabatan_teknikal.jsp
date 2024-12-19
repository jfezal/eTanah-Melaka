<%-- 
    Document   : senarai_jabatan_teknikal
    Created on : Jan 5, 2010, 2:36:33 PM
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
                Jabatan Teknikal Yang Perlu Membuat Ulasan
            </legend>
            <p>
                <label>&nbsp;</label>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="Pilih."/>
                    <display:column title="Bil."/>
                    <display:column title="Nama Jabatan"/>
                    <display:column title="Jangkamasa ( Hari)"/>
                </display:table>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:submit name="tambah" value="Tambah" class="btn"/>
                <s:submit name="hapus" value="Hapus" class="btn"/>
            </p>
            <p>
                <label>Tambah Jabatan Teknikal Lain : </label>
                <s:radio name="option" value="ya"/>Ya
                <s:radio name="option" value="tidak"/>Tidak
            </p>
        </fieldset>
    </div>
    <div class="subtitle"> 
        <fieldset class="aras1">
            <legend>
                Senarai Jabatan Teknikal Yang Telah Ditambah
            </legend>
            <p>
                <label>&nbsp;</label>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="Pilih."/>
                    <display:column title="Bil."/>
                    <display:column title="Nama Jabatan"/>
                    <display:column title="Tarikh Hantar"/>
                    <display:column title="Jangkamasa ( Hari)"/>
                    <display:column title="Tarikh Jangka Terima"/>
                </display:table>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:submit name="hapus" value="Hapus" class="btn"/>
            </p>
            <p>&nbsp;</p>
        </fieldset>
        <p>
            <label>&nbsp;</label>
            <s:submit name="terus" value="Terus" class="btn"/>
        </p>
    </div>
</s:form>
