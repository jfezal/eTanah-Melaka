<%-- 
    Document   : laporan_pelukis_pelan2
    Created on : Oct 25, 2010, 9:36:16 AM
    Author     : afham
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.pelupusan.PelupusanActionBean">
    <p>LAPORAN PELUKIS PELAN</p>
    
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
                Maklumat Tanah Asal
            </legend>
            <p>
                <label>Daerah :</label>
                &nbsp;
            </p>
            <p>
                <label>&nbsp;</label>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="Bil."/>
                    <display:column title="Bandar/Pekan/Mukim"/>
                    <display:column title="Seksyen"/>
                    <display:column title="No. Lot"/>
                    <display:column title="Keluasan"/>
                    <display:column title="Kategori Tanah"/>
                    <display:column title="Jenis Kegunaan Tanah"/>
                </display:table>
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Tanah
            </legend>
            <p class="instr">
                Klik pada bilangan untuk masukkan maklumat tanah
            </p>
            <p>
                <label>&nbsp;</label>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="Bil."/>
                    <display:column title="No. Lot"/>
                    <display:column title="No. Lembaran Piawai"/>
                    <display:column title="Jenis Tanah"/>
                    <display:column title="Status Pemilikan Tanah"/>
                    <display:column title="Cara Milik"/>
                    <display:column title="Berimilik"/>
                </display:table>
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Warta
            </legend>
            <p>
                <label>&nbsp;</label>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="Bil."/>
                    <display:column title="No. Lembaran Piawai"/>
                    <display:column title="Jenis Tanah"/>
                    <display:column title="Kedudukan Tanah"/>
                    <display:column title="No. Warta"/>
                    <display:column title="Tarikh Warta"/>
                </display:table>
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Tambahan
            </legend>
            <p>
                <label>Ditanda untuk projek kerajaan :</label>
                <s:radio name="option" value="Ya"/>Ya
                <s:radio name="option" value="Tidak"/>Tidak
            </p>
            <p>
                <label>Susunatur Rancangan Perumahan/Pembangunan :</label>
                <s:radio name="option" value="Ada"/>Ada
                <s:radio name="option" value="Tiada"/>Tiada
            </p>
            <p>&nbsp;</p>
            <p>
                <label>LPS/Permit Dikeluarkan :</label>
                <s:radio name="option" value="Ada"/>Ada
                <s:radio name="option" value="Tiada"/>Tiada
            </p>
            <p>
                <label>Permohonan Bertindih :</label>
                <s:radio name="option" value="Ada"/>Ada
                <s:radio name="option" value="Tiada"/>Tiada
            </p>
            <p>
                <label>Permohonan Terdahulu  :</label>
                <s:radio name="option" value="Ada"/>Ada
                <s:radio name="option" value="Tiada"/>Tiada
            </p>
            <p>
                <label>No Fail/ID Permohonan :</label>
                <s:text name=" "/>
            </p>
            <p>
                <label>Keputusan :</label>
                <s:select name="">
                    <s:option value="">-- Sila Pilih --</s:option>
                </s:select>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:submit name="tambah" value="Tambah" class="btn"/>
                <s:submit name="hapus" value="Hapus" class="btn"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="Pilih"/>
                    <display:column title="Bil."/>
                    <display:column title="No Fail/ID Permohonan"/>
                    <display:column title="Keputusan"/>
                </display:table>
            </p>
            <p>
                <label>Hal-hal lain :</label>
                <s:textarea name = " " rows="5" cols="70" />
            </p>
        </fieldset>
        <p>
            <label>&nbsp;</label>
            <s:submit name="charting" value="Charting" class="btn"/>
            <s:submit name="simpan" value="Simpan" class="btn"/>
            <s:submit name="hantar" value="Hantar" class="btn"/>
            <s:reset name="reset" value="Isi Semula" class="btn"/>
            <s:submit name="keluar" value="Keluar" class="btn"/>
        </p>
    </div>

</s:form>

