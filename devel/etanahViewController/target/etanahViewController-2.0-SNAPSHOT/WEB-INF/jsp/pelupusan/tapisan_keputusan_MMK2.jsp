<%-- 
    Document   : tapisan_keputusan_MMK2
    Created on : Jan 18, 2010, 10:33:01 AM
    Author     : zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.pelupusan.PelupusanActionBean">
    <p>MAKLUMAN TAPISAN KEPUTUSAN MAJLIS MESYUARAT KERAJAAN</p>
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
                    <display:column title="No. Telefon Bimbit"/>
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
                &nbsp;
            </p>
            <p>
                <label>Peruntukan Undang - Undang :</label>
                &nbsp;
            </p>
            <p>
                <label>Tujuan :</label>
                &nbsp;
            </p>
            <p>
                <label>Latarbelakang Permohonan :</label>
                &nbsp;
            </p>

            <div class="content">
                <fieldset class="aras2">
                    <legend>
                        Perihal Tanah
                    </legend>
                    <p>
                        <label>Perihal Tanah :</label>
                        &nbsp;
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        Kegunaan lot-lot yang bersempadan dengannya adalah seperti berikut :</p>
                </fieldset>
            </div>
            <div class="content">
                <fieldset class="aras2">
                    <legend>
                        Perihal Pemohon
                    </legend>
                    <div class="content">
                        <fieldset class="aras3">
                            <legend>
                                Maklumat Pemohon Individu
                            </legend>
                            <p>
                                <label>Nama :</label>
                                &nbsp;
                            </p>
                            <p>
                                <label>Jenis Pengenalan :</label>
                                &nbsp;
                            </p>
                            <p>
                                <label>Nombor Pengenalan :</label>
                                &nbsp;
                            </p>
                            <p>
                                <label>Nombor Pengenalan Lama :</label>
                                &nbsp;
                            </p>
                            <p>
                                <label>Warna Kad Pengenalan :</label>
                                &nbsp;
                            </p>
                            <p>
                                <label>Umur :</label>
                                &nbsp;
                            </p>
                            <p>
                                <label>Bangsa :</label>
                                &nbsp;
                            </p>
                            <p>
                                <label>Warganegara :</label>
                                &nbsp;
                            </p>
                            <p>
                                <label>Nombor Sijil :</label>
                                &nbsp;
                            </p>
                            <p>
                                <label>Taraf Pengenalan :</label>
                                &nbsp;
                            </p>
                            <p>
                                <label>Tanggungan :</label>
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
                                <label>Nombor Telefon Rumah :</label>
                                &nbsp;
                            </p>
                            <p>
                                <label>Nombor Telefon Pejabat :</label>
                                &nbsp;
                            </p>
                            <p>
                                <label>Nombor Telefon Bimbit :</label>
                                &nbsp;
                            </p>
                            <p>
                                <label>Pekerjaan :</label>
                                &nbsp;
                            </p>
                            <p>
                                <label>Pendapatan Sebulan (RM) :</label>
                                &nbsp;
                            </p>
                            <p>
                                <label>Lain-lain :</label>
                                &nbsp;
                            </p>
                            <p>&nbsp;</p>
                        </fieldset>
                    </div>
                </fieldset>
            </div>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Butir-butir Ansuran
            </legend>
            <p>
                <label>Butir-butir Ansuran :</label>
                <s:textarea name="" rows="5" cols="70"/>
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Ulasan Pentadbir Tanah Daerah
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
                Perakuan Pentadbir Tanah Daerah
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
                Keputusan Majlis Mesyuarat Kerajaan
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
                Maklumat Bayaran
            </legend>
            <p>
                Maklumat A
            </p>
            <p>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="Bil."/>
                    <display:column title="No. Lot"/>
                    <display:column title="Jenis Hakmilik"/>
                    <display:column title="Tempoh"/>
                    <display:column title="Jenis Kegunaan Tanah"/>
                    <display:column title="Keluasan Tanah"/>
                    <display:column title="Syarat Nyata"/>
                    <display:column title="Sekatan Kepentingan"/>
                </display:table>
            </p>
            <p>&nbsp;</p>
            <p>
                Maklumat B
            </p>
            <p>

                <display:table name="" id="line" class="tablecloth">
                    <display:column title="Bil."/>
                    <display:column title="No. Lot"/>
                    <display:column title="Premium (RM)"/>
                    <display:column title="Cukai Tahunan (RM)"/>
                    <display:column title="Bayaran Ukur (RM)"/>
                    <display:column title="Bayaran Plan (RM)"/>
                    <display:column title="Pendaftaran Hakmilik Sementara"/>
                    <display:column title="Pendaftaran Hakmilik Tetap"/>
                </display:table>
            </p>
        </fieldset>
    </div>
    <p>&nbsp;</p>
    <label>&nbsp;</label>
    <s:submit name="" value="Kertas Kerja Terdahulu" class="btn"/>
    <s:submit name="simpan" value="Simpan" class="btn"/>
    <s:submit name="hantar" value="Hantar" class="btn"/>
    <s:submit name="keluar" value="Keluar" class="btn"/>

</s:form>
