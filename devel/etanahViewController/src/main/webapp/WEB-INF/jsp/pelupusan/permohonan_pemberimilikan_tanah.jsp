<%-- 
    Document   : permohonan_pemberimilikan_tanah
    Created on : Dec 10, 2009, 9:10:51 AM
    Author     : zadhirul.farihim
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.pelupusan.PelupusanActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Permohonan
            </legend>

            <p>
                <label for="Jenis">Jenis Permohonan :</label>
                Permohonan Pemberimilikan Tanah
            </p>
            <p>
                <label for="ID">ID Permohonan :</label>
                &nbsp;
            </p>
            <p>
                <label for="Nama">Nama Permohonan :</label>
                &nbsp;
            </p>

        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pemberimilikan
            </legend>

            <p>
                <label>Terancang : </label>
                <s:radio name="option" value="ya"/>Ya
                <s:radio name="option" value="tidak"/>Tidak
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Tanah Dipohon
            </legend>
            <p class="instr">PERHATIAN: Sila klik pada BIL yang terdapat dalam jadual untuk memasukkan maklumat.</p>
            <p>
                <label>Bil Plot/Lot Yang Dipohon : </label>
                1
            </p>
            <p>
            <label>&nbsp;</label>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="BIL."/>
                    <display:column title="Daerah"/>
                    <display:column title="Bandar/Pekan/Mukim"/>
                    <display:column title="Seksyen"/>
                    <display:column title="Lokasi"/>
                    <display:column title="No. Lot(Jika Ada)"/>
                    <display:column title="Tujuan"/>
                    <display:column title="Keluasan Tanah"/>
                </display:table>
            <%--</center>--%>
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Pemberimilikan Rumah Murah
            </legend>
            <p>
                <label>Permohonan Rumah Murah : </label>
                <s:radio name="option" value="ya"/>Ya
                <s:radio name="option" value="tidak"/>Tidak
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Kategori Pemohon
            </legend>
            <p>
                <label>Kategori Pemohon : </label>
                Perbadanan/Syarikat
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pemohon Perbadanan/Syarikat
            </legend>
            <p class="instr">PERHATIAN: Medan bertanda <font color="red">*</font> wajib diisi.</p>
            <p>
                <label><font color="red">*</font>Nama Perbadanan : </label>
                <s:text name=""/>
            </p>
            <p>
                <label><font color="red">*</font>Jenis Pengenalan : </label>
                <s:select name="">
                    <s:option value="">-- Sila Pilih --</s:option>
                </s:select>
            </p>
            <p>
                <label><font color="red">*</font>Nombor Pendaftaran : </label>
                <s:text name=""/>
            </p>
            <p>
                <label>Tarikh Perbadanan : </label>
                <s:text name="" class="datepicker"/>
            </p>
            <p>
                <label>Alamat : </label>
                <s:textarea name="" rows="5" cols="70"/>
            </p>
            <p>
                <label>Poskod : </label>
                <s:text name=""/>
            </p>
            <p>
                <label><font color="red">*</font>Negeri: </label>
                <s:text name=""/>
            </p>
            <p>
                <label><font color="red">*</font>Bandar : </label>
                <s:select name="">
                    <s:option value="">-- Sila Pilih --</s:option>
                </s:select>
            </p>
            <p>
                <label>Nombor Telefon Pejabat : </label>
                <s:text name=""/>
            </p>
            <p>
                <label>Samb. : </label>
                <s:text name=""/>
            </p>
            <p>
                <label>Modal Dibenarkan (RM) : </label>
                <s:text name=""/>
            </p>
            <p>
                <label><font color="red">*</font>Modal Berbayar (RM) : </label>
                <s:text name=""/>
            </p>
            <p>
                <label>Aktiviti : </label>
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
                Tambah Maklumat Pemohon Perbandanan
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
                    <display:column title="Nama Perbadanan"/>
                    <display:column title="Nombor Pendafataran"/>
                </display:table>
            </p>
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
                    <display:column title="Seksyen"/>
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
                Syor Tolak Ringkas
            </legend>
            <p>
                <label>Tindakan : </label>
                <s:radio name="option" value="ya"/>Ya
                <s:radio name="option" value="tidak"/>Tidak
            </p>
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
