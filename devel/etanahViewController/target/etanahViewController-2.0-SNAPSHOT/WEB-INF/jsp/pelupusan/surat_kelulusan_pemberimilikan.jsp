<%-- 
    Document   : surat_kelulusan  (for pemberimilikan)
    Created on : May 20, 2010, 12:02:36 PM
    Author     : nurul.izza
    modified on 18102010 by sitifariza.hanim
--%>

<%@include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pelupusan.SuratKelulusanPemberimilikanActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Syarat - Syarat Hakmilik
            </legend>
           
            <p>
                <label>Jenis Hakmilik :</label>
                <s:select name="kodHakmilik.kod" value="${actionBean.hakmilikPermohonan.kodHakmilik.kod}">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${actionBean.senaraiKodHakmilik}" label="nama" value="kod"/>
                </s:select>
            </p> 
            <p>
                <label>Tempoh Hakmilik :</label>
                <s:text id="tempoh" name="tempohHakmilik" size="3"/> &nbsp; tahun
            </p>
            <p>
                <label>Hasil :</label>     RM 144.00 bagi tiap - tiap 100 m.p. atau sebahagian daripadanya.
            </p>
            <p>
                <label>Bayaran Ukur (RM) :</label>     Mengikut P.U(A) 438
            </p>
            <p>
                <label>Jenis Penggunaan Tanah :</label>
                <s:select name="kegunaanTanah" id="kegunaanTanah">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKegunaanTanah}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
                <label>Syarat Nyata :</label>
                <s:text id="syaratNyata" name="hakmilikPermohonan.syaratNyataBaru.syarat" size="40"/>
            </p>
            <p>
                <label>Sekatan Kepentingan :</label>     Tanah ini tidak boleh dipindah milik atau dipajak kecuali dengan kebenaran Pihak Berkuasa Negeri.
            </p
           
        </fieldset>
    </div>
    <div>
        <p>
            <label>&nbsp;</label>

            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
        

        </p>
    </div>
</s:form>

         <%-- these columns are for n9--%>

             <%--<p>
                <label>Luas :</label> lebih kurang &nbsp;
                 <s:text name="hakmilikPermohonan.luasTerlibat" id="luas" size="10"/>
                 <s:select name="meterPersegi" >
                    <s:option>Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodUOMByLuas}" label="nama" value="kod" />
                 </s:select>
            </p>
            <p>
                <label>Jenis Hakmilik Sementara :</label>
                 <s:text name="hakSementara" id="hakmilikPermohonan.kodHakmilik.nama" size="40"/>
            </p>
            <p>
                <label>Jenis Hakmilik Tetap :</label>
                <s:select name="hakmilikTetap" id="hakmilikTetap">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodHakmilik}" label="nama" value="kod"/>
                </s:select>
            </p>   
            <p>
                <label>Kadar Cukai :</label>
                <s:text name="cukai" size="10"/> &nbsp; semeter persegi atau sebahagian daripadanya <br>tertakluk kepada minimum RM
                <label> &nbsp;</label><s:text id="cukai" name="hakmilikPermohonan.cukaiBaru"size="10"/> &nbsp; satu lot.
            </p>
            <p>
                <label>Kadar Premium :</label>
                RM &nbsp; <s:text name="ukurSen" size="10"/> &nbsp; semeter persegi
            </p>
            <p>
                <label>Denda Premium :</label>
                RM &nbsp; <s:text name="ukurRM" size="10"/> &nbsp; semeter persegi
            </p>
            <p>
                <label>Kadar Bayaran Upah Ukur dan Batu Sempadan :</label>   Mengikut Jadual
                <s:text name="kbUpah" size="20"/>
            </p>
            <p><br>
                <label>Kadar Bayaran Pendaftaran dan Penyediaaan Hakmilik Sementara / Tetap:</label>
                Mengikut Peraturan - Peraturan tanah Negeri
                <s:text name="kbDaftar" size="40"/>
            </p>   
            <p><br><br>
                <label>Kategori :</label>
                <s:text id="kategori" name="hakmilikPermohonan.kategoriTanahBaru.nama" size="40"/>
            </p> 
            <p>
                <label>Syarat Am :</label>
                <s:text name="syaratAm" size="40"/>
            </p>--%>
         
