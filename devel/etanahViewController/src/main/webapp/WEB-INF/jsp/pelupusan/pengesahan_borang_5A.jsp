<%-- 
    Document   : pengesahan_borang_5A
    Created on : Jan 18, 2010, 11:16:51 AM
    Author     : zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.pelupusan.PelupusanActionBean">
    <p>SENARAI PENYEDIAAN PENGESAHAN BORANG 5A</p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Permohonan
            </legend>
            <p>
                <label>Jenis Permohonan :</label>
                &nbsp;
            </p>
            <p>
                <label>ID Permohonan :</label>
                &nbsp;
            </p>
            <p>
                <label>Nama Pemohon :</label>
                &nbsp;
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Pengesahan Borang 5A
            </legend>
            <p>

                <display:table name="" id="line" class="tablecloth">
                    <display:column title="Bil."/>
                    <display:column title="No. Lot"/>
                    <display:column title="Keluasan Tanah"/>
                    <display:column title="Cukai Tahun Pertama(RM)"/>
                    <display:column title="Premium(RM)"/>
                    <display:column title="Bayaran Ukur(RM)"/>
                    <display:column title="Bayaran Plan(RM)"/>
                    <display:column title="Bayaran Ansuran(RM)"/>
                    <display:column title="Pendaftaran Hakmilik Sementara"/>
                    <display:column title="Pendaftaran Hakmilik Tetap"/>          
                    <display:column title="Jumlah(RM)"/>
                    <display:column title="Tempoh Pajakan"/>
                </display:table>
            </p>
            <div class="content">
                <fieldset class="aras2">
                    <legend>
                        Jumlah Bayaran Yang Dikenakan
                    </legend>
                    <p>
                        <label>Jumlah Keseluruhan (RM) :</label>
                        &nbsp;
                    </p>
                </fieldset>
            </div>
            <div class="content">
                <fieldset class="aras2">
                    <legend>
                        Ulasan Pentadbir Tanah Daerah
                    </legend>
                    <p>
                        <label for="ID Berkaitan"> Ulasan : </label>
                        <s:textarea name="" rows="5" cols="70"/>
                    </p>
                    <p>&nbsp;</p>
                </fieldset>
            </div>
        </fieldset>
    </div>
    <p>
        <label>&nbsp;</label>
        <s:submit name="" value="Simpan" class="btn"/>
        <s:submit name="" value="Hantar" class="btn"/>
        <s:submit name="" value="Keluar" class="btn"/>
    </p>
</s:form>
