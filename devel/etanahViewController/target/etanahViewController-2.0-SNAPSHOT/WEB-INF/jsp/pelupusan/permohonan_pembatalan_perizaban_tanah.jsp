<%-- 
    Document   : permohonan_pembatalan_perizaban_tanah
    Created on : Jan 13, 2010, 10:44:40 AM
    Author     : zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.pelupusan.PelupusanActionBean">
    <p>PERMOHONAN PEMBATALAN PERIZABAN TANAH</p>
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
                Maklumat Pembatalan Perizaban
            </legend>
            <p>
                <label><font color="Red">*</font>Nombor Rujukan Surat :</label>
                <s:text name=" "/>
            </p>
            <p>
                <label><font color="Red">*</font>Tarikh Surat Memohon :</label>
                <s:text name=" " class="datepicker"/>
            </p>
            <p>
                <label><font color="Red">*</font>Pegawai Pengawal :</label>
                <s:text name=" "/>
            </p>
            <p>
                <label><font color="Red">*</font>Pegawai Penyelenggara :</label>
                <s:text name=" "/>
            </p>
            <p>
                <label><font color="Red">*</font>Tujuan :</label>
                <s:text name=" "/>
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Tanah Dimohon
            </legend>
            <p class="instr">
                <font color="Red">Sila Klik Pada Bil. Untuk Kemasukan Tanah Yang Dimohon</font>
            </p>
            <p>
                <label>Bil Lot/Plot Yang Dimohon :</label>
                &nbsp;
            </p>
            <p>
                <label>&nbsp;</label>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="Bil."/>
                    <display:column title="Daerah"/>
                    <display:column title="Bandar/Pekan/Mukim"/>
                    <display:column title="Lokasi"/>
                    <display:column title="No Lot (Jika Ada)"/>
                    <display:column title="Tujuan"/>
                    <display:column title="Keluasan Tanah"/>
                </display:table>
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Kategori Pemohon
            </legend>
            <p>
                <label>Kategori Pemohon :</label>
                &nbsp;
            <p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pemohon Perbadanan
            </legend>
            <p>
                <label><font color="Red">*</font>Nama Perbadanan :</label>
                <s:text name=" "/>
            </p>
            <p>
                <label><font color="Red">*</font>Jenis Pengenalan :</label>
                <s:select name="">
                    <s:option value="">-- Sila Pilih --</s:option>
                </s:select>
            </p>
            <p>
                <label><font color="Red">*</font>Nombor Pendaftaran :</label>
                <s:text name=" "/>
            </p>
            <p>
                <label>Tarikh Pendaftaran :</label>
                <s:text name="" class="datepicker"/>
            </p>
            <p>
                <label>Alamat :</label>
                <s:textarea name=" "rows="5" cols="70"/>
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Negeri :</label>
                <s:select name="">
                    <s:option value="">-- Sila Pilih --</s:option>
                </s:select>
            </p>
            <p>
                <label>Bandar :</label>
                <s:select name="">
                    <s:option value="">-- Sila Pilih --</s:option>
                </s:select>
            </p>
            <p>
                <label>Nombor Telefon Pejabat :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Sambungan :</label>
                <s:text name=""/>
            </p>
            <p>
                <label><font color="Red">*</font>Modal Dibenarkan (RM) :</label>
                <s:text name=""/>
            </p>
            <p>
                <label><font color="Red">*</font>Modal Berbayar (RM) :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Aktiviti :</label>
                <s:text name=""/>
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pengarah/Pemegang Saham
            </legend>
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
                    <display:column title="Nombor Pengenalan"/>
                    <display:column title="Warganegara"/>
                    <display:column title="Jawatan"/>
                </display:table>
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pekerja
            </legend>
            <p><label>&nbsp;</label>

                <s:submit name="tambah" value="Tambah" class="btn"/>
                <s:submit name="hapus" value="Hapus" class="btn"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="Pilih"/>
                    <display:column title="Bil."/>
                    <display:column title="Pekerjaan"/>
                    <display:column title="Bilangan"/>
                </display:table>
            </p>
            <p>
                <label>Bilangan Pekerja : </label>0 Orang
            </p>
            <p>&nbsp;</p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Tanah Yang Dimiliki
            </legend>
            <p>
                <label>&nbsp;</label>
                <s:submit name="cari" value="Cari" class="btn"/>
                <s:submit name="tambah" value="Tambah" class="btn"/>
                <s:submit name="hapus" value="Hapus" class="btn"/>
            </p>
            <p></p>
            <label>&nbsp;</label>
            <display:table name="" id="line" class="tablecloth">
                <display:column title="Pilih"/>
                <display:column title="Bil."/>
                <display:column title="ID Hakmilik"/>
                <display:column title="Bandar/Pekan/Mukim"/>
                <display:column title="Daerah"/>
                <display:column title="Negeri"/>
                <display:column title="Lokasi"/>
                <display:column title="No. Hakmilik/TOL"/>
                <display:column title="Keluasan"/>
                <display:column title="Jenis Penggunaan Tanah"/>
            </display:table>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Tajuk Permohonan
            </legend>
            <p>
                <label>&nbsp;</label>
                <s:submit name="jana" value="Jana Tajuk" class="btn"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:textarea name="" rows="5" cols="70"/>
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