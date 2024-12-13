<%-- 
    Document   : rekod_cek_pampasan
    Created on : Jan 20, 2010, 10:17:44 AM
    Author     : zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.pelupusan.PelupusanActionBean">
    <p>REKOD CEK PAMPASAN</p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Tajuk Kertas
            </legend>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Permohonan
            </legend>
            <p>
                <label>ID Permohonan :</label>
                &nbsp;
            </p>
            <p>
                <label>Tarikh Permohonan :</label>
                &nbsp;
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pemohon
            </legend>
            <p>
                <label>Nama :</label>
                &nbsp;
            </p>
            <p>
                <label>Alamat :</label>
                &nbsp;
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Pemilik/Pihak Berkepentingan
            </legend>
            <p>
                <display:table name="" id="line" class="tablecloth">          
                    <display:column title="Bil."/>
                    <display:column title="Nama"/>
                    <display:column title="No Penganalan"/>
                    <display:column title="Alamat"/>
                    <display:column title="ID Hakmilik"/>
                    <display:column title="No Lot"/>
                    <display:column title="Jenis Pihak Berkepentingan"/>
                    <display:column title="Cara Bayaran"/>
                    <display:column title="Tarikh Dokumen"/>
                    <display:column title="Amaun (RM)"/>
                    <display:column title="No Rujukan"/>
                    <display:column title="Catatan"/>
                </display:table>
            </p>
        </fieldset>
    </div>
    
    <p>
        <label>&nbsp;</label>
        <s:reset name="" value="Isi Semula" class="btn"/>
        <s:submit name="" value="Simpan" class="btn"/>
        <s:submit name="" value="Hantar" class="btn"/>
        <s:submit name="" value="Keluar" class="btn"/>
    </p>
</s:form>