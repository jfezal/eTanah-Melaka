<%-- 
    Document   : kemaskini_kertas_MMK
    Created on : Jan 13, 2010, 10:04:35 AM
    Author     : zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.pelupusan.PelupusanActionBean">
    <p>KEMASKINI KERTAS MAJLIS MESYUARAT KERAJAAN</p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Tajuk Kertas
            </legend>
            &nbsp;
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
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="Bil."/>
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
            <p>&nbsp;</p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Tanah Dimiliki
            </legend>
            <p>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="Bil."/>
                    <display:column title="ID Hakmilik"/>
                    <display:column title="Bandar/Pekan/Mukim"/>
                    <display:column title="Hakmilik"/>
                    <display:column title="Alamat"/>
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
                Ringkasan Kertas Kerja Jawatankuasa Tanah Negeri
            </legend>
            <p>
                <label>ID Permohonan :</label>
                &nbsp;
            </p>
            <p>
                <label>Peruntukan Undang - Undang :</label>
                &nbsp;
            </p>
            <p>
                <label>Perihal Permohonan :</label>
                &nbsp;
            </p>
            <div class="content">
                <label>Pemohon :</label>
                <p>
                    <display:table name="" id="line" class="tablecloth">
                        <display:column title="Nama"/>
                        <display:column title="Alamat"/>
                    </display:table>
                </p>
            </div>
            <div class="content">
                <fieldset class="aras2">
                    <legend>
                        Perihal Permohonan :
                    </legend>
                    <p>Tarikh-tarikh permohonan diproses :</p>
                    <p>Permohonan diterima oleh Pentadbir Tanah :</p>
                    <p>Rujuk kepada Jabatan Teknikal :</p>
                    <p>Surat-surat ulasan yang diterima :</p>
                    <p>
                        <label>&nbsp;</label>
                        <display:table name="" id="line" class="tablecloth">
                            <display:column title="Bil."/>
                            <display:column title="Proses"/>
                            <display:column title="Tarikh"/>
                        </display:table>
                    </p>
                </fieldset>
            </div>

            <div class="content">
                <fieldset class="aras2">
                    <legend>
                        Perihal Tanah
                    </legend>
                    <p>Tanah ini terletak di kawasan pesisiran pantai .
                        Keluasan yang terlibat ialah seluas 1.00 Meter Persegi untuk Lot 1</p>
                    <p>Kegunaan lot-lot yang bersempadan dengannya adalah seperti berikut :</p>
                    <p>
                        <label>&nbsp;</label>
                        <display:table name="" id="line" class="tablecloth">
                            <display:column title="Arah"/>
                            <display:column title="Status Tanah Sekeliling"/>
                            <display:column title="No. Lot/PT"/>
                            <display:column title="Kategori"/>
                            <display:column title="Kegunaan Semasa"/>
                        </display:table>
                    </p>
                    <p>&nbsp;</p>
                </fieldset>
            </div>
            <div class="content">
                <fieldset class="aras2">
                    <legend>
                        Asas-asas Pertimbangan
                    </legend>
                    <p> Permohonan ini telah dirujuk kepada Jabatan-jabatan Teknikal.
                        Ulasan jabatan-jabatan berkenaan adalah seperti berikut :</p>
                    <p>
                        <label>&nbsp;</label>
                        <display:table name="" id="line" class="tablecloth">
                            <display:column title="Arah"/>
                            <display:column title="Status Tanah Sekeliling"/>
                            <display:column title="No. Lot/PT"/>
                            <display:column title="Kategori"/>
                            <display:column title="Kegunaan Semasa"/>
                        </display:table>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <display:table name="" id="line" class="tablecloth">
                            <display:column title="Jabatan"/>
                            <display:column title="Alamat"/>
                        </display:table>
                    </p>
                    <p>&nbsp;</p>
                </fieldset>
            </div>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Draf Kertas Kerja
            </legend>
            <p>
                <label>ID Permohonan :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Peruntukan Undang - Undang :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Tujuan :</label>
                <s:textarea name="" rows="5" cols="70"/>
            </p>
            <p>
                <label>Latarbelakang Permohonan :</label>
                <s:textarea name="" rows="5" cols="70"/>
            </p>

            <div class="content">
                <fieldset class="aras2">
                    <legend>
                        Perihal Tanah
                    </legend>
                    <p>
                        <label>Perihal Tanah :</label>
                        <s:textarea name="" rows="5" cols="70"/>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        Kegunaan lot-lot yang bersempadan dengannya adalah seperti berikut :</p>
                    <p>
                        <label>&nbsp;</label>
                        <display:table name="" id="line" class="tablecloth">
                            <display:column title="Arah"/>
                            <display:column title="Status Tanah Sekeliling"/>
                            <display:column title="No. Lot/PT"/>
                            <display:column title="Kategori"/>
                            <display:column title="Kegunaan Semasa"/>
                        </display:table>
                    </p>
                </fieldset>
            </div>

            <div class="content">
                <fieldset class="aras2">
                    <legend>
                        Ulasan Jabatan-Jabatan Teknikal
                    </legend>
                    <p>Permohonan ini diterima pada 05/07/2008 dan telah dirujuk kepada
                        Jabatan-jabatan Teknikal pada 01/07/2008.
                        Ulasan Jabatan-jabatan berkenaan adalah seperti berikut :
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <display:table name="" id="line" class="tablecloth">
                            <display:column title="Jabatan"/>
                            <display:column title="Ulasan"/>
                        </display:table>
                    </p>
                    <p>&nbsp;</p>
                </fieldset>
            </div>
            <div class="content">
                <fieldset class="aras2">
                    <legend>
                        Senarai Dokumen
                    </legend>
                    <p>
                        <label>&nbsp;</label>
                        <display:table name="" id="line" class="tablecloth">
                            <display:column title="Bil."/>
                            <display:column title="ID Dokumen"/>
                            <display:column title="Perihal Dokumen"/>
                            <display:column title="Catatan"/>
                        </display:table>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:submit name="" value="Hapus" class="btn"/>
                        <s:submit name="" value="Tambah" class="btn"/>
                    </p>
                    <p></p>
                    <p>
                        <label>&nbsp;</label>
                        <display:table name="" id="line" class="tablecloth">
                            <display:column title="Bil."/>
                            <display:column title="Perihal Dokumen Tambahan"/>
                            <display:column title="Catatan Dokumen Tambahan"/>
                            <display:column title="Imbas Dokumen Tambahan"/>
                            <display:column title="ID Dokumen Tambahan"/>
                        </display:table>
                    </p>
                    <p>&nbsp;</p>
                </fieldset>
            </div>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Ulasan Pentadbir Tanah Daerah
            </legend>
            <p>
                <label> Ulasan : </label>
                &nbsp;
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Perakuan Pentadbir Tanah Daerah
            </legend>
            <p>
                <label> Perakuan : </label>
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
                <label> Nombor Lot : </label>
                &nbsp;
            </p>
            <p>
                <label> Kegunaan Lesen : </label>
                &nbsp;
            </p>
            <p>
                <label> Bayaran Pendudukan (RM) : </label>
                &nbsp;
            </p>
            <p>
                <label> Jumlah Bayaran (RM) : </label>
                &nbsp;
            </p>
            <p>
                <label> Keterangan Syarat : </label>
                <s:textarea name="" rows="5" cols="70"/>
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Keputusan Mesyuarat Jawatankuasa Tanah Negeri
            </legend>
            <p>
                <label> Bilangan Mesyuarat Yang ke- : </label>
                &nbsp;
            </p>
            <p>
                <label> Tarikh Mesyuarat : </label>
                &nbsp;
            </p>
            <p>
                <label> Nombor Kertas Mesyuarat : </label>
                &nbsp;
            </p>
            <p>
                <label> Keputusan : </label>
                &nbsp;
            </p>
            <p>
                <label> Minit/Perakuan : </label>
                <s:textarea name="" rows="5" cols="70"/>
            </p>
            <p>&nbsp;</p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Keputusan Majlis
            </legend>
            <p>
                <label> Perakuan Untuk Majlis Mesyuarat Kerajaan : </label>
                <s:textarea name="" rows="5" cols="70"/>
            </p>
            <p>&nbsp;</p>
        </fieldset>
    </div>


    <p>&nbsp;</p>
    <label>&nbsp;</label>
    <s:submit name="simpan" value="Simpan" class="btn"/>
    <s:submit name="hantar" value="Hantar" class="btn"/>
    <s:submit name="keluar" value="Keluar" class="btn"/>
</s:form>

