<%-- 
    Document   : semak_keputusan_enkuiri
    Created on : Jan 20, 2010, 5:33:27 PM
    Author     : zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.pelupusan.PelupusanActionBean">
    <p>SEMAK KEPUTUSAN ENKUIRI</p>
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

                <display:table name="" id="line" class="tablecloth">
                    <display:column title="Nama"/>
                    <display:column title="Jenis Pengenalan"/>
                    <display:column title="Nombor"/>
                    <display:column title="Alamat"/>
                    <display:column title="No. Telefon Rumah"/>
                    <display:column title="No. Telefon Pejabat"/>
                    <display:column title="Samb."/>
                    <display:column title="No. Telefon Bimbit"/>
                    <display:column title="Emel"/>
                </display:table>
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Tanah/Pajak/Pajakan Kecil
            </legend>
            <p>
                <label>Daerah :</label>
                &nbsp;
            </p>
            <p>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="Bil."/>
                    <display:column title="ID Hakmilik"/>
                    <display:column title="Bandar/Pekan/Mukim"/>
                    <display:column title="Hakmilik"/>
                    <display:column title="Nombor Lot"/>
                    <display:column title="Keluasan"/>
                    <display:column title="Kategori Tanah"/>
                    <display:column title="Kod Syarat Nyata"/>
                    <display:column title="Kod Sekatan Kepentingan"/>
                </display:table>
            </p>
            <p>&nbsp;</p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pemohon Enkuiri
            </legend>
            <p>
                <label>Nama :</label>
                &nbsp;
            </p>
            <p>
                <label>Alamat :</label>
                &nbsp;
            </p>
            <p>
                <label>Poskod :</label>
                &nbsp;
            </p>
            <p>
                <label>Bandar :</label>
                &nbsp;
            </p>
            <p>
                <label>Negeri :</label>
                &nbsp;
            </p>
            <p>
                <label>Nombor Telefon :</label>
                &nbsp;
            </p>
            <p>
                <label>Kes atau Isu :</label>
                &nbsp;
            </p>
            <p>
                <label>Senarai Seksyen (KTN):</label>
                &nbsp;
            </p>
            <p>
                <label>Perihal Seksyen :</label>
                &nbsp;
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Bayaran
            </legend>
            <p>
                <label>Jumlah Bayaran Pampasan (RM) :</label>
                &nbsp;
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pihak Berkepentingan
            </legend>
            <p>
                <label>&nbsp;</label>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="Pilih"/>
                    <display:column title="Bil."/>
                    <display:column title="Nama"/>
                    <display:column title="No Penganalan"/>
                    <display:column title="ID Hakmilik"/>
                    <display:column title="Jenis Pihak Berkepentingan"/>
                </display:table>
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Arahan Kena Bayar
            </legend>
            <p>
                <label>&nbsp;</label>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="Pilih"/>
                    <display:column title="Bil."/>
                    <display:column title="Nama"/>
                    <display:column title="Amaun (RM)"/>
                </display:table>
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Keputusan Enkuiri
            </legend>
            <p>
                <label for="ID Berkaitan"> Keputusan : </label>
                <s:radio name="option" value="KK"/>Terima Permohonan
                <s:radio name="option" value="SS"/>Tolak Permohonan
                <s:radio name="option" value="TP"/>Tangguh Enkuiri
            </p>
            <p>
                <label for="ID Berkaitan"> Ulasan : </label>
                <s:textarea name="" rows="5" cols="70"/>
            </p>
            <p>
                <label for="ID Berkaitan"> Syarat-syarat : </label>
                <s:textarea name="" rows="5" cols="70"/>
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Dokumen
            </legend>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
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

