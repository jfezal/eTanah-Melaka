<%-- 
    Document   : Borang B
    Created on : 05-Jan-2010, 10:06:38
    Author     : nordiyana
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


        <s:form beanclass="etanah.view.stripes.pengambilan.PengambilanBorang">
            KEBENARAN UNTUK MASUK MENGUKUR
<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Maklumat Permohonan
        </legend>
            <p>
                <label for="Maklumat Permohonan">ID Permohonan :</label>
                1234567890
            </p>
            <p>
                <label for="Maklumat Permohonan">Tarikh Permohonan :</label>
                23/06/2009
            </p>
            <br>
    </fieldset>
</div>
<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Maklumat Permohon
        </legend>
           <p>
                <display:table class="tablecloth" name="" pagesize="4" cellpadding="0" cellspacing="0" id="line">
                <display:column property="bil" title="Bil"/>
                <display:column property="nama" title="Nama"/>
                <display:column property="pengenalan" title="Jenis Pengenalan"/>
                <display:column property="no" title="Nombor"/>
                <display:column property="alamat" title="Alamat"/>
                <display:column property="telrumah" title="No. Telefon Rumah"/>
                <display:column property="telpejabat" title="No. Telefon Pejabat"/>
                <display:column property="extension" title="Sambungan"/>
                <display:column property="telbimbit" title="No. Telefon Bimbit"/>
                 <display:column property="emel" title="Emel"/>
            </display:table>
        </p>
            <br>
    </fieldset>
</div>
<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Jadual Tanah-Tanah Yang Terlibat Dengan Pengambilan
        </legend>
<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Maklumat Tanah
        </legend>
        <p>
                <display:table class="tablecloth" name="" pagesize="4" cellpadding="0" cellspacing="0" id="line">
                <display:column property="bil" title="Bil"/>
                <display:column property="idhakmilik" title="ID Hakmilik" href="#"/>
                <display:column property="nolot" title="Nombor Lot"/>
                <display:column property="HakmilikPendudukan" title="Hakmilik atau Pendudukan"/>
                <display:column property="LuasLot" title="Luas Lot"/>
                <display:column property="anggaran" title="Anggaran Keluasan Lot Yang Akan Diambil"/>
            </display:table>
        </p>

    </fieldset>

</div>
        </fieldset>
        </div>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Penerima
                    </legend>
                    <div class="content" align="left">
                <display:table class="tablecloth" name="" pagesize="4" cellpadding="0" cellspacing="0" id="line">
                <display:column property="bil" title="Bil"/>
                <display:column property="nama" title="Nama Penerima" href="#"/>
                <display:column property="alamat" title="Alamat Penerima"/>
                <display:column property="poskod" title="Poskod"/>
                <display:column property="bandar" title="Bandar"/>
                </display:table><br>
                    </div>
                </fieldset>
                </div>
       <div class="subtitle">
        <fieldset class="aras1">
            <legend>Tindakan yang diambil:</legend>

            <p>
                <label>&nbsp;</label>
                <s:checkbox name="check" value=""/>&nbsp; Mengukur dan mengambil aras tanah<br>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:checkbox name="check" value=""/>&nbsp; Menggali atau mengorek tanah bawah
            </p>
            <p>
                <label>&nbsp;</label>
                <s:checkbox name="check" value=""/>&nbsp; Melakukan kesemua tindakan lain yang perlu bagi menentukan samada tanah itu sesuai
                                                   &nbsp; dengan tujuan yang ianya hendak diambil.
            </p>
            <p>
                <label>&nbsp;</label>
                <s:checkbox name="check" value=""/>&nbsp; Menetapkan sempadan-sempadan tanah yang dicadang hendak diambil dan cadangan jenis kerja,jika ada
                                                   &nbsp; yang dicadangkan hendak dibuat di atas tanah itu.<br>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:checkbox name="check" value=""/>&nbsp; Menandakan aras,sempadan dan garisan itu dengan meletak tanda-tanda dan menggali parit-parit.
            </p>
            <p>
                <label>&nbsp;</label>
                <s:checkbox name="check" value=""/>&nbsp; Menebang,meruntuh dan membersihkan apa-apa tanaman yang sedia ada,papan atau hutan,jika oleh kesemuanya
                                                   &nbsp; pengukuran itu tidak dapat disiapkan atau aras tanah itu dapat diambil,atau garis kerja itu tidak dapat ditandakan.
            </p>

                </fieldset>
            </div>

           <%--<table width="100%" bgcolor="grey">
                <tr>
                    <td width="10%"align="right">
                        <s:submit name="isisemula" value="Isi Semula" class="btn"/>
                        <s:submit name="hantar" value="Hantar" class="btn"/>
                        <s:submit name="sah" value="Sah" class="btn"/>
                        <s:submit name="simpan" value="Simpan" class="btn"/>
                        <s:submit name="borang" value="Papar Borang B" class="btn"/>
                        <s:submit name="terus" value="Keluar" class="btn"/>

                    </td>
                </tr>
           </table>--%>
        </s:form>
  