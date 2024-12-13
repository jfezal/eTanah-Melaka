<%-- 
    Document   : kemasukan_maklumat_permohonan_rayuan
    Created on : Jan 15, 2010, 10:50:24 AM
    Author     : zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.pelupusan.PelupusanActionBean">
    <p>KEMASUKAN MAKLUMAT PERMOHONAN RAYUAN</p>
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
                <label for="ID">ID Permohonan :</label>
                &nbsp;
            </p>
            <p>
                <label for="Nama">Tarikh Permohonan :</label>
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
                <label>&nbsp;</label>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="Bil."/>
                    <display:column title="Nama"/>
                    <display:column title="Alamat"/>
                    <display:column title="No. Telefon Pejabat"/>
                    <display:column title="Sambungan"/>
                </display:table>
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Tanah Asal
            </legend>
            <p>
                <label>Tempoh :</label>
                &nbsp;
            </p>
            <p>
                <label>&nbsp;</label>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="Bil."/>
                    <display:column title="Bandar/Pekan/Mukim"/>
                    <display:column title="No.Lot/Plot/PT"/>
                    <display:column title="Keluasan"/>
                </display:table>
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Rayuan Lanjut Tempoh
            </legend>
            <p>
                <label>Tempoh :</label>
                <s:text name=" "/>
                Bulan
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Butir-butir Rayuan
            </legend>
            <p>
                <label>Butir-butir Rayuan :</label>
                <s:textarea name=" "rows="5" cols="70"/>

            </p>
        </fieldset>
    </div>
    <p>
        <label>&nbsp;</label>
        <s:submit name="terus" value="Terus" class="btn"/>
        <s:reset name="reset" value="Isi Semula" class="btn"/>
        <s:submit name="keluar" value="Keluar" class="btn"/>
    </p>
</s:form>
