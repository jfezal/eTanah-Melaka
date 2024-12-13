<%-- 
    Document   : pru_penyediaan_kertas_JKTN
    Created on : Dec 17, 2009, 11:31:39 AM
    Author     : zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.pelupusan.PelupusanActionBean">
    <p>PENYEDIAAN KERTAS KERJA JKTN</p>
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
                    <display:column title="Bil."/>
                    <display:column title="Nama"/>
                    <display:column title="Jenis Pengenalan"/>
                    <display:column title="Nombor"/>
                    <display:column title="Alamat"/>
                    <display:column title="No. Telefon Rumah"/>
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
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Kertas Ringkasan
            </legend>
            <p>
                <label>ID Permohonan :</label>
                <s:text  disabled="true" name=""/>
            </p>
            <p>
                <label>Peruntukan Undang - Undang :</label>
                <s:text disabled="true" name=""/>
            </p>
            <p>
                <label>Perihal Permohonan :</label>
                <s:text disabled="true" name=""/>
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
                        Perihal Permohonan
                    </legend>
                    <p>Tarikh-tarikh permohonan diproses :</p>
                    <p>Permohonan diterima oleh Pentadbir Tanah :</p>
                    <p>Rujuk kepada Jabatan Teknikal :</p>
                    <p>Surat-surat ulasan yang diterima :</p>
                    <%--<af:outputText value="Tarikh-tarikh permohonan diproses :" />
                    <af:outputText value="Permohonan diterima oleh Pentadbir Tanah : " />
                    <af:outputText value="Rujuk kepada Jabatan Teknikal :" />
                    <af:outputText value="Surat-surat ulasan yang diterima :" />--%>
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
                    <p>
                        Tanah ini terletak di ... .
                        Keluasan yang terlibat ialah seluas 1.00 Meter Persegi untuk Lot 1
                    </p>
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
                            <display:column title="Jabatan"/>
                            <display:column title="Alamat"/>
                        </display:table>
                    </p>
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
                &nbsp;
            </p>
            <p>
                <label>Peruntukan Undang - Undang :</label>
                &nbsp;
            </p>
            <p>
                <label>Tujuan :</label>
                <s:textarea name="" rows="5" cols="70"/>
            </p>
            <p>
                <label>Latarbelakang Permohonan :</label>
                <s:textarea name="" rows="5" cols="70"/>
            </p>
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

            <div class="content">
                <fieldset class="aras2">
                    <legend>
                        Ulasan Jabatan-Jabatan Teknikal
                    </legend>
                    <p>Permohonan ini diterima pada 05/07/2008 dan telah dirujuk kepada Jabatan-jabatan Teknikal pada
                        01/07/2008. Ulasan Jabatan-jabatan berkenaan adalah seperti berikut :</p>
                    <p>
                        <label>&nbsp;</label>
                        <display:table name="" id="line" class="tablecloth">
                            <display:column title="Jabatan"/>
                            <display:column title="Alamat"/>
                        </display:table>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <display:table name="" id="line" class="tablecloth">
                            <display:column title="Jabatan"/>
                            <display:column title="Ulasan"/>
                        </display:table>
                    </p>
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
                </fieldset>
            </div>
            <p>&nbsp;</p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Draf Ulasan Pentadbir Tanah Daerah
            </legend>
            <p>
                <label for="ID Berkaitan"> Ulasan : </label>
                <s:textarea name="" rows="5" cols="70"/>
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Draf Perakuan Pentadbir Tanah Daerah
            </legend>
            <p>
                <label for="ID Berkaitan"> Perakuan : </label>
                <s:textarea name="" rows="5" cols="70"/>
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Kemaskini Maklumat Bayaran
            </legend>
            <p>
                <label for="ID Berkaitan"> Nombor Lot : </label>
                &nbsp;
            </p>
            <p>
                <label for="ID Berkaitan"> Kegunaan Lesen : </label>
                <s:select name="">
                    <s:option value="">-- Sila Pilih --</s:option>
                </s:select>
            </p>
            <p>
                <label for="ID Berkaitan"> Bayaran Pendudukan (RM) : </label>
                <s:text name=""/>
            </p>
            <p>
                <label for="ID Berkaitan"> Jumlah Bayaran (RM) : </label>
                <s:text name=""/>
            </p>
            <p>
                <label for="ID Berkaitan"> Keterangan Syarat : </label>
                <s:textarea name="" rows="5" cols="70"/>
            </p>
        </fieldset>
        <p>
            <label>&nbsp;</label>
            <s:submit name="" value="Simpan" class="btn"/>
            <s:submit name="" value="Hantar" class="btn"/>
            <s:reset name="" value="Isi Semula" class="btn"/>
            <s:submit name="" value="Keluar" class="btn"/>
        </p>
    </div>
</s:form>

