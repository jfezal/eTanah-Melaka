<%-- 
    Document   : pru_kemasukan_ulasan_JPPH
    Created on : Dec 17, 2009, 11:07:31 AM
    Author     : zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.pelupusan.PelupusanActionBean">
    <div class="subtitle">
        <p>KEMASUKKAN ULASAN JPPH</p>
        <fieldset class="aras1">
            <legend>
                Maklumat Permohonan
            </legend>
            <p>
                <label for="ID Berkaitan"> ID Permohonan : </label>
                <s:text disabled="true"name=""/>
            </p>
            <p>
                <label for="ID Berkaitan"> Tarikh Permohonan : </label>
                <s:text disabled="true"name=""/>
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
                    <display:column title="Alamat Tetap"/>
                    <display:column title="No. Telefon Pejabat"/>
                    <display:column title="Samb."/>
                    <display:column title="Emel"/>
                </display:table>


            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Tanah
            </legend>
            <p>
                <label>Bandar/Pekan/Mukim :</label>
                <s:text disabled="true" name=""/>
            </p>
            <p>
                <label>Nombor Lot/Plot :</label>
                <s:text disabled="true" name=""/>
            </p>
            <p>
                <label>Keluasan :</label>
                <s:text disabled="true" name=""/>
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Dokumen yang Disertakan
            </legend>
            <p>
                <label>&nbsp;</label>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="No."/>
                    <display:column title="Dokumen"/>
                    <display:column title="ID Dokumen"/>
                </display:table>
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Penyediaan Laporan
            </legend>
            <p>
                <label>Jabatan :</label>
                <s:text disabled="true" name=""/>
            </p>
            <p>
                <label>Tarikh Penyediaan :</label>
                <s:text disabled="true" name=""/>
            </p>
            <p>
                <label>Nama Penyedia :</label>
                <s:text disabled="true" name=""/>
            </p>
            <p>
                <label>No. Rujukan :</label>
                <s:text disabled="true" name=""/>
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Ulasan Jabatan Teknikal
            </legend>
            <p>
                <label>Syor :</label>
                <s:select name="">
                    <s:option value="">-- Sila Pilih --</s:option>
                    <s:option value="">Boleh Diluluskan</s:option>
                    <s:option value="">Perlu Ditolak</s:option>
                    <s:option value="">Tangguh</s:option>
                </s:select>
            </p>
            <p>
                <label>Ulasan :</label>
                <s:textarea value="diluluskan" name="ulasan" rows="10" cols="70" />
            </p>
            <p>&nbsp;</p>

            <p>
                <label>&nbsp;</label>
                <s:submit name="" value="Hapus" class="btn"/>
                <s:submit name="" value="Tambah" class="btn"/>
            </p>
            <p></p>
            <label>&nbsp;</label>
            <display:table name="" id="line" class="tablecloth">
                <display:column title="Bilangan"/>
                <display:column title="Tempoh Pajakan (Tahun)"/>
                <display:column title="ID Hakmilik"/>
                <display:column title="Keluasan Tanah"/>
                <display:column title="Kadar Nilaian (RM)"/>
                <display:column title="Anggaran Nilaian (RM)"/>
                <display:column title="Anggaran Bundar (RM)"/>
            </display:table>


        </fieldset>   
        <p>
            <label>&nbsp;</label>
            <s:submit name="keluar" value="Keluar" class="btn"/>
            <s:reset name="reset" value="Isi Semula" class="btn"/>
            <s:submit name="hantar" value="Hantar" class="btn"/>
            <s:submit name="simpan" value="Simpan" class="btn"/>
        </p>
    </div>
</s:form>

