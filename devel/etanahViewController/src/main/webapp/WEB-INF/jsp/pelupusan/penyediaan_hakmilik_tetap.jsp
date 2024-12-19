<%-- 
    Document   : penyediaan_hakmilik_tetap
    Created on : Dec 10, 2009, 10:11:00 AM
    Author     : zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.pelupusan.PelupusanActionBean">
    <div class="subtitle">
        <p>PERMOHONAN PEMBERIMILIKAN TANAH</p>
        <fieldset class="aras1">
            <legend>
                Maklumat Bayaran
            </legend>
            <p>

                <display:table name="" id="line" class="tablecloth">
                    <display:column title="Bil."/>
                    <display:column title="No. Lot"/>
                    <display:column title="Jenis Hakmilik"/>
                    <display:column title="Tempoh (Tahun)"/>
                    <display:column title="Jenis Kegunaan Tanah"/>
                    <display:column title="Keluasan Tanah"/>
                    <display:column title="Syarat Nyata"/>
                    <display:column title="Sekatan Kepentingan"/>
                    <display:column title="Cukai Tahunan (RM)"/>
                </display:table>
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pelan JUPEM
            </legend>
            <p>
                <label for="ID Berkaitan"> Bilangan Lot : </label>
                <s:text name=""/>
            </p>

            <p>
                <label>&nbsp;</label>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="No."/>
                    <display:column title="No. Lot"/>
                    <display:column title="Keluasan"/>
                    <display:column title="No. Pelan Akui"/>
                    <display:column title="ID Permohonan"/>
                </display:table>
            </p>
        </fieldset>
        <p>
            <label>&nbsp;</label>
            <s:submit name="" value="Keluar" class="btn"/>
            <s:reset name="" value="Isi Semula" class="btn"/>
            <s:submit name="" value="Simpan" class="btn"/>
        </p>
    </div>

</s:form>
