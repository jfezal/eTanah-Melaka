<%-- 
    Document   : pilih_jabatan_teknikal
    Created on : Dec 17, 2009, 10:39:22 AM
    Author     : zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.pelupusan.PelupusanActionBean">
    <div class="subtitle">
        <p>PILIH JABATAN TEKNIKAL</p>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Permohonan
                </legend>
                <p>
                    <label>ID Permohonan :</label>
                    00008989
                </p>
                <p>
                    <label>Tarikh Permohonan :</label>
                    29/12/2009
                </p>
            </fieldset>
        </div>
        <fieldset class="aras1">
            <legend>
                Maklumat Tanah Asal
            </legend>
            <p>
                <label for=""> Daerah: </label>
                qwqwqeqe
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
                Maklumat Pemohon
            </legend>
            <p>
                <label>&nbsp;</label>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="Bil."/>
                    <display:column title="Nama"/>
                    <display:column title="Jenis Pengenalan"/>
                    <display:column title="Nombor"/>
                    <display:column title="Alamat"/>
                    <display:column title="Nombor Telefon"/>
                    <display:column title="Nombor Telefon Pejabat"/>
                    <display:column title="Samb."/>
                    <display:column title="Nombor Telefon Bimbit"/>
                    <display:column title="Emel"/>
                </display:table>
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Jabatan Teknikal yang Terlibat
            </legend>

            <p>Klik butang Tambah untuk memilih Jabatan Teknikal yang terlibat.</p>
            <p>
                <label>&nbsp;</label>
                <s:submit name="" value="Tambah" class="btn"/>
                <s:submit name="" value="Hapus" class="btn"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="Pilih"/>
                    <display:column title="Bil."/>
                    <display:column title="Nama Jabatan"/>
                    <display:column title="Tarikh Hantar"/>
                    <display:column title="Jangkamasa (Hari)"/>
                    <display:column title="Tarikh Jangka Terima"/>
                </display:table>
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
                <s:submit name="" value="Tambah" class="btn"/>
                <s:submit name="" value="Hapus" class="btn"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="No."/>
                    <display:column title="Dokumen"/>
                    <display:column title="ID Dokumen"/>
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