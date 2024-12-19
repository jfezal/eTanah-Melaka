<%-- 
    Document   : menentukan_hakmilik
    Created on : Jan 5, 2010, 11:19:03 AM
    Author     : nursyazwani
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Menetukan Jenis Hakmilik</title>
    </head>
    <body>
        <stripes:form beanclass="etanah.view.stripes.pembangunan.PenyatuanTanahActionBean">
        
        <%--<div class="subtitle">
            <fieldset class ="aras1">
                <legend>Maklumat Permohonan</legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="" pagesize="4" cellpadding="0" cellspacing="0" id="line" size="100">
                            <display:column title="Bil">${line_rowNum}</display:column>
                            <display:column property="pemohon" title="Nama Pemohon"/>
                            <display:column property="pengenalan" title="Nama Pengenalan"/>
                            <display:column property="alamatTetap" title="Alamat Tetap"/>
                            <display:column property="alamatSurat" title="Alamat Surat"/>
                        </display:table>
                        <br>
                    </div>
            </fieldset>
        </div>
        <div class="subtitle">
            <fieldset class ="aras1">
                <legend>Laporan Pelukis Pelan</legend>
                <div class="content" align="center">
                    <fieldset class="aras2">
                        <legend> Maklumat Tanah</legend>
                        <div class="content" align="center">
                            <display:table class="tablecloth" name="maklumatTanah" pagesize="4" cellpadding="0" cellspacing="0" id="line" size="100">
                                <display:column title="Bil">${line_rowNum}</display:column>
                                <display:column property="noLot" title="Nombor Lot"/>
                                <display:column property="noLembaranPiawai" title="Nombor Lembaran Piawai"/>
                                <display:column property="jenisTanah" title="Jenis Tanah"/>
                                <display:column property="statusPemilikanTanah" title="Status Pemilikan Tanah"/>
                                <display:column property="caraPemilikanTanah" title="Cara Pemilikan Tanah"/>
                            </display:table>
                            <br>
                        </div>
                    </fieldset>
                </div>
            </fieldset>
        </div>
        <div class="subtitle">
            <fieldset class ="aras1">
                <legend> Maklumat Warta</legend>
                    <div class="content" align="center">
                            <display:table class="tablecloth" name="maklumatWarta" pagesize="4" cellpadding="0" cellspacing="0" id="line" size="100">
                                <display:column title="Bil">${line_rowNum}</display:column>
                                <display:column property="kedudukanTanah" title="Kedudukan Tanah"/>
                                <display:column property="noWarta" title="Nombor Warta"/>
                                <display:column property="tarikhWarta" title="Tarikh Warta"/>
                            </display:table>
                            <br>
                        </div>
            </fieldset>
        </div>--%>
        <div class="subtitle">
            <fieldset class ="aras1">
                <legend> Maklumat Lain-lain</legend>
                    <div class="content" align="center">
                        <table>
                            <tr>
                                <td>Ditanda Untuk Projek Kerajaan :</td>
                                <td><s:radio name="radio1" value="ya"/> Ya &nbsp;
                                    <s:radio name="radio1" value="tidak"/>Tidak</td>
                            </tr>
                            <tr>
                                <td>Susunatur Rancangan Perumahan/Bangunan :</td>
                                <td><s:radio name="radio1" value="ada"/>Ada &nbsp;
                                    <s:radio name="radio1" value="tiada"/>Tiada</td>
                            </tr>
                            <tr>
                                <td>LPS/Permit Dikeluarkan :</td>
                                <td><s:radio name="radio1" value="ada"/>Ada &nbsp;
                                    <s:radio name="radio1" value="tiada"/>Tiada</td>
                            </tr>
                            <tr>
                                <td>Pemohonan Terdahulu :</td>
                                <td><s:radio name="radio1" value="ada"/>Ada &nbsp;
                                    <s:radio name="radio1" value="tiada"/>Tiada</td>
                            </tr>
                            <tr>
                                <td>Hal-hal Lain :</td>
                                <td><s:text name="halLain"/></td>
                            </tr>
                        </table>
                        </div>
            </fieldset>
        </div>
        <div class="subtitle">
            <fieldset class ="aras1">
                <legend>Maklumat Tanah Asal</legend>
                    <div class="content" align="center">
                        <table>
                            <tr>
                                <td>Daerah :</td>
                                <td><s:text name="daerah"/></td>
                            </tr>
                        </table>
                                <display:table class="tablecloth" name="maklumatTanahAsal" pagesize="4" cellpadding="0" cellspacing="0" id="line" size="100">
                                <display:column title="Bil">${line_rowNum}</display:column>
                                <display:column property="idHakmilik" title="ID Hakmilik"/>
                                <display:column property="bandarPekanMukim" title="Bandar/Pekan/Mukim"/>
                                <display:column property="seksyen" title="Seksyen"/>
                                <display:column property="hakmilik" title="Hakmilik"/>
                                <display:column property="noLot" title="No Lot"/>
                                <display:column property="keluasan" title="Keluasan"/>
                                <display:column property="kategoriTanah" title="Kategori Tanah"/>
                                <display:column property="kegunaanTanah" title="Jenis Kegunaan Tanah"/>
                                <display:column property="kodSyaratNyata" title="Kod Syarat Nyata"/>
                                <display:column property="kodSekatanKepentingan" title="Kod Sekatan Kepentingan"/>
                        </display:table>
                       <br>
                        <table>
                            <tr>
                                <td>Maklumat Hakmilik Terdahulu (jika perlu) :</td>
                                <td><s:textarea name="hakmilikTerdahulu" cols="87" rows="8"/></td>
                            </tr>
                            <tr>
                                <td>Maklumat Tambahan :</td>
                                <td><s:textarea name="maklumatTmbhan" cols="87" rows="8"/></td>
                            </tr>
                        </table>
                    </div>
                </fieldset>
            </div>
        <div class="subtitle">
            <fieldset class ="aras1">
                <legend>Peruntukan Undang-Undang</legend>
                <div class="content" align="center">
                    <table>
                        <tr>
                            <td>Nyatakan Seksyen atau Nombor Pekeliling :</td>
                            <td><s:text name="noPekeliling"/></td>
                        </tr>
                    </table>
                </div>
            </fieldset>
        </div>
        <div class="subtitle">
            <fieldset class ="aras1">
                <legend>Kawalan Perancangan dan Pembangunan</legend>
                <div class="content" align="center">
                    <table>
                        <tr>
                            <td>Butiran Kawalan :</td>
                            <td><s:text name="butiranKawalan"/></td>
                        </tr>
                    </table>
                </div>
            </fieldset>
        </div>
        <div class="subtitle">
            <fieldset class ="aras1">
                <legend>Maklumat Keputusan Awal</legend>
                <div class="content" align="center">
                    <table>
                        <tr>
                            <td>Keputusan :</td>
                            <td><s:radio name="t1" value="TR"/>Tolak Ringkas &nbsp;
                            <s:radio name="t1" value="LT"/>Lanjutan ke Lawatan Tapak dan Siasatan</td>
                        </tr>
                        <tr>
                            <td>Ulasan :</td>
                            <td><s:textarea name="ulasan" cols="87" rows="8" /></td>
                        </tr>
                    </table>
                </div>
            </fieldset>
        </div>
        <div class="subtitle">
            <fieldset class ="aras1">
                <legend>Lawatan Tapak dan Siasatan</legend>
                <div class="content" align="center">
                <table>
                    <tr>
                        <td>Tarikh Lawatan :</td>
                        <td><s:text name="trkhLawatan" /></td>
                    </tr>

                    <tr>
                        <td>Lokasi Tanah :</td>
                        <td><s:textarea name="lokasi" cols="87" rows="8"/></td>
                    </tr>

                     <tr>
                        <td>Keadaan Geografi Tanah :</td>
                        <td><s:textarea name="keadaanGeografi" cols="87" rows="8"/></td>
                    </tr>

                     <tr>
                        <td>Jarak dari Bandar/Pekan :</td>
                        <td><s:textarea name="jarak" cols="87" rows="8"/></td>
                    </tr>

                    <tr>
                        <td>Mercu Tanda Terdekat :</td>
                        <td><s:textarea name="mercuTanda" cols="87" rows="8"/></td>
                    </tr>

                </table>

                <br>
                <p>Maklumat Pembangunan Atas Tanah</p>
                <br>

                <display:table class="tablecloth" name="" pagesize="4" cellpadding="0" cellspacing="0" id="line" size="100">
                    <display:column title="No">${line_rowNum}</display:column>
                    <display:column property="jenisPembangunan" title="Jenis Pembangunan Atas Tanah"/>
                    <display:column property="imbas" title="Imbas"/>
                    <display:column property="idDokumen" title="ID Dokumen"/>
                </display:table>
                <br>
                <table>
                    <tr>
                        <td>Jalan Keluar Masuk :</td>
                        <td><s:text name="jlnKeluarMasuk" /></td>
                    </tr>

                    <tr>
                        <td>Kemudahan Asas :</td>
                        <td><s:textarea name="kemudahanAsas" cols="87" rows="8"/></td>
                    </tr>

                    <tr>
                        <td>Zon Pembangunan :</td>
                        <td><s:radio name="t1" value="A"/>Ada &nbsp;
                        <s:radio name="t1" value="T"/>Tiada</td>
                    </tr>

                    <tr>
                        <td>Pembangunan Kawasan Sekeliling (Lingkungan 1.0km) :</td>
                        <td><s:textarea name="pembangunanSekeliling" cols="87" rows="8"/></td>
                    </tr>
                </table>
                <br>
                <p>Lot-Lot Bersempadan </p>
                <br>
                <p>Maklumat Lot-Lot Sempadan</p>
                <br>
                <display:table class="tablecloth" name="" pagesize="4" cellpadding="0" cellspacing="0" id="line" size="100">
                    <display:column title="No">${line_rowNum}</display:column>
                    <display:column property="arah" title="Arah"/>
                    <display:column property="noLot" title="No Lot/Plot/PT"/>
                    <display:column property="bandarPekanMukim" title="Bandar/Pekan/Mukim"/>
                    <display:column property="status" title="Status"/>
                    <display:column property="kegunaanTanah" title="Jenis Kegunaan Tanah"/>
                    <display:column property="gunaTanahSemasa" title="Guna Tanah Semasa"/>
                    <display:column property="imbas" title="Imbas"/>
                    <display:column property="idDokumen" title="ID Dokumen"/>
                </display:table>
                <br>
                <table>
                    <tr>
                        <td>Ulasan :</td>
                        <td><s:textarea name="ulasan" cols="87" rows="8"/></td>
                    </tr>
                </table>
                </div>
            </fieldset>
        </div>
        <div class="subtitle">
            <fieldset class ="aras1">
                   <legend>Senarai Dokumen Permohonan</legend>
                   <div class="content" align="center">
                       <display:table class="tablecloth" name="" pagesize="4" cellpadding="0" cellspacing="0" id="line" size="100">
                            <display:column title="No">${line_rowNum}</display:column>
                            <display:column property="dokumen" title="Dokumen"/>
                            <display:column property="catatan" title="Catatan"/>
                            <display:column property="idDokumen" title="ID Dokumen"/>
                        </display:table>
                        <br>
                   </div>
            </fieldset>
        </div>

        <div class="subtitle">
            <fieldset class ="aras1">
                <legend>Ulasan oleh Penolong Pegawai Tanah</legend>
                <div class="content" align="center">
                    <table>
                        <tr>
                            <td >Pematuhan Kepada Kehendak Undang-Undang :</td>
                            <td><s:textarea name="pematuhanUndang" cols="87" rows="8"/></td>
                        </tr>
                        <tr>
                            <td>Kesesuaian Dari Segi Perancangan, Pembangunan, Ekonomi dan Sosial :</td>
                            <td><s:textarea name="kesesuaian" cols="87" rows="8"/></td>
                        </tr>

                    </table>
                </div>
            </fieldset>
        </div>

        <div class="subtitle">
            <fieldset class ="aras1">
                <legend>Syor dan Cadangan Oleh Penolong Pegawai Tanah</legend>
                <div class="content" align="center">
                <table>
                    <tr>
                        <td>Syor :</td>
                        <td><s:radio name="rad1" value="syor"/>Syor &nbsp;
                            <s:radio name="rad1" value="tSyor"/>Tidak Syor
                        </td>
                    </tr>

                    <tr>
                        <td>Ulasan :</td>
                        <td><s:textarea name="ulasan" cols="87" rows="8"/></td>
                    </tr>
                </table>
                </div>
            </fieldset>
        </div>
        <div class="subtitle">
            <fieldset class ="aras1">
                <legend>Jenis Hakmilik dan Umpukan</legend>
                <div class="content" align="center">
                <table>
                    <tr>
                        <td>Hakmilik :</td>
                         <td>
                             <s:radio name="rad1" value="PTD"/>Hakmilik Pejabat Tanah (PTD) &nbsp;
                             <s:radio name="rad1" value="PTG"/>Hakmilik Pendaftar (PTG) &nbsp;
                         </td>
                    </tr>
                </table>
                </div>
            </fieldset>
        </div>
                         <p align="center">
                            <s:submit name="simpan" value="Simpan" class="btn"/> &nbsp;
                         </p>

    </stripes:form>
    </body>
</html>
