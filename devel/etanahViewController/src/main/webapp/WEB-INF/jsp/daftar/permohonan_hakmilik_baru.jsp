<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252" language="java"%>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>



<style type="text/css">
    input.error { background-color: yellow; }
</style>

<stripes:form action="/Calculator.action" focus="">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Permohonan</legend>
            <p>
                <label>ID Permohonan :</label>

                database

            </p>

            <p>
                <label>Kod Urusan :</label>

                database

            </p>

            <p>
                <label>Urusan :</label>

                database

            </p>

            <p>
                <label>Tarikh dan Masa :</label>

                15/10/2009 02:30pm

            </p>
            </table>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Senarai Hakmilik yang Terlibat</legend>
            <p>
                <label>ID Hakmilik :</label>

                database

            </p>

        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Fail</legend>
            <p>
                <label>No. Fail :</label>
                <stripes:text name=""/>
            </p>
            <p>
                <label>No. Mahkamah :</label>
                <stripes:text name=""/>
            </p>
            <p>
                <label>Unit Serah :</label>
                <stripes:text name=""/>
            </p>
            <p>
                <label>Tarikh Terima :</label>
                <stripes:text name=""/>>
            </p>
            <p>
                <label>Bil. Hakmilik :</label>
                <stripes:text name=""/>
            </p>
            <p>
                <label>Bil. Siap :</label>
                <stripes:text name=""/>
            </p>
            <p>
                <label>Bil. Belum Siap :</label>
                <stripes:text name=""/>
            </p>

        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <p>
                <label>&nbsp;</label>
                <stripes:submit name="simpan" value="Simpan" class="btn"/>
                <stripes:submit name="terus" value="Terus" class="btn"/>
                <stripes:submit name="keluar" value="Keluar" class="btn"/>
                </td>
            </p>

        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Perincian Hakmilik</legend>
            <p>
                <label>Unit :</label>
                <stripes:text name=""/>
            </p>
            <p>
                <label>No. Lot :</label>
                <stripes:text name=""/>
            </p>
            <p>
                <label>Unit :</label>
                <stripes:text name=""/>
            </p>
            <p>
                <label>Keluasan :</label>
                <stripes:text name=""/>
            </p>
            <p>
                <label>No. Pelan Akui :</label>
                <stripes:text name=""/>
            </p>
            <p>
                <label>Nombor Syit :</label>
                <stripes:text name=""/>
            </p>
            <p>
                <label>Lokasi :</label>
                <stripes:text name=""/>
            </p>
            <p>
                <label>Seksyen :</label>
                <stripes:text name=""/>
            </p>
            <p>
                <label>Kadar Cukai (RM) :</label>
                <stripes:text name=""/>
            </p>
            <p>
                <label>Unit :</label>
                <stripes:text name=""/>
            </p>
            <p>
                <label>Cukai Tanah (RM) :</label>
                <stripes:text name=""/>
            </p>
            <p>
                <label>Kawasan Penguatkuasa Tempatan :</label>
                <stripes:text name=""/>
            </p>
            <p>
                <label>Lot Bumiputera :</label>
                <stripes:text name=""/>
            </p>
            <p>
                <label>Lot Orang Melayu :</label>
                <stripes:text name=""/>
            </p>
            <p><label>&nbsp;</label>
                <stripes:submit name="simpan" value="Simpan"/>
                <stripes:submit name="terus" value="Terus"/>
                <stripes:submit name="keluar" value="Keluar"/>
            </p>
        </fieldset>
    </div>


    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Hakmilik Asal</legend>
            <p>
                <label>ID Hakmilik Asal :</label>
                <stripes:text name=""/>
            </p>
            <p>
                <label>Tarikh mula dimiliki :</label>
                <stripes:text name=""/>
            </p>
            <p>
                <label>&nbsp;</label>
                <stripes:submit name="tambah" value="Tambah"/>
                <stripes:submit name="hapus" value="Hapus"/>

            </p>

            <table border="1" width="95%" align="center" datapagesize="2" style="tablecloth" >
                <tr>
                    <th>Pilih               </th>
                    <th>Bil.                </th>
                    <th>ID Hakmilik Asal</th>
                    <th>Tarikh mula dimiliki</th>
                </tr>
                <tr>
                    <td align="center"><stripes:checkbox name="chx1"/></td>
                    <td>Tiada Data       </td>
                    <td>Tiada Data       </td>
                    <td>Tiada Data       </td>
                </tr>
            </table>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Hakmilik Sebelum</legend>
            <p>
                <label>ID Hakmilik Sebelum :</label>
                database
            </p>
            <p>
                <label>&nbsp;</label>
                <stripes:submit name="tambah" value="Tambah"/>
                <stripes:submit name="hapus" value="Hapus"/>
                </td>
            </p>
            </table>
            <table border="1" width="95%" align="center" datapagesize="2" >
                <tr>
                    <th>Pilih               </th>
                    <th>Bil.                </th>
                    <th>ID Hakmilik Sebelum</th>
                </tr>
                <tr>
                    <td align="center"><stripes:checkbox name="chx1"/></td>
                    <td>Tiada Data       </td>
                    <td>Tiada Data       </td>
                </tr>
            </table>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Hakmilik Pasangan (GSA)</legend>
            <p>
                <label>ID Hakmilik Pasangan (GSA) :</label>
                <stripes:text name=""/>
            </p>
            <p>
                <label>Kod Lot/No.Lot :</label>
                <stripes:text name=""/>
            </p>
            <p>
                <label>&nbsp;</label>
                <stripes:submit name="tambah" value="Tambah"/>
                <stripes:submit name="hapus" value="Hapus"/>

            </p>

            <table border="1" width="95%" align="center" datapagesize="2" >
                <tr>
                    <th>Pilih               </th>
                    <th>Bil.                </th>
                    <th>ID Hakmilik Pasangan (GSA)</th>
                    <th>Kod Lot</th>
                    <th>No. Lot</th>
                </tr>
                <tr>
                    <td align="center"><stripes:checkbox name="chx1"/></td>
                    <td>Tiada Data       </td>
                    <td>Tiada Data       </td>
                    <td>Tiada Data       </td>
                    <td>Tiada Data       </td>
                </tr>
            </table>

                    <p><label>&nbsp;</label>
                        <stripes:submit name="simpan" value="Simpan" class="btn"/>
                        <stripes:submit name="terus" value="Terus" class="btn"/>
                        <stripes:submit name="keluar" value="Keluar" class="btn"/>
                    </p>
        </fieldset>
    </div>
   
   <%-- <div class="subtitle">
        <fieldset>
            <legend>Maklumat Pihak Berkepentingan</legend>
            <p>
                    <label>Nama :</label>
                    <td width="1%" align="center">
                    <td><stripes:text name=""/></td>
                </p>
                <tr>
                    <td width="25%" align="right">Jenis Pengenalan</td>
                    <td width="1%" align="center">:</td>
                    <td>
                        <stripes:select name="">
                            <stripes:option value="IC Baru">IC Baru</stripes:option>
                            <stripes:option value="IC Lama">No Syarikat</stripes:option>
                            <stripes:option value="Passport">Passport</stripes:option>
                            <stripes:option value="Lain-Lain">Lain-Lain</stripes:option>
                        </stripes:select>
                    </td>
                </tr> 
                <tr>
                    <td width="25%" align="right">Nombor Pengenalan</td>
                    <td width="1%" align="center">:</td>
                    <td><stripes:text name=""/></td>
                </tr>
                <tr>
                    <td width="25%" align="right">Jenis Pihak Berkepentingan</td>
                    <td width="1%" align="center">:</td>
                    <td>
                        <stripes:select name="">
                            <stripes:option value="pemajak">pemajak</stripes:option>
                            <stripes:option value="penyewa">penyewa</stripes:option>
                            <stripes:option value="pemegang gadaian">pemegang gadaian</stripes:option>
                        </stripes:select>
                    </td>
                </tr>
                <tr>
                    <td width="25%" align="right">No. Kumpulan PB</td>
                    <td width="1%" align="center">:</td>
                    <td><stripes:text name=""/></td>
                </tr>
                <tr>
                    <td width="25%" align="right">Taraf/Kaum</td>
                    <td width="1%" align="center">:</td>
                    <td>
                        <stripes:select name="">
                            <stripes:option value="nilai 1">nilai 1</stripes:option>
                            <stripes:option value="nilai 2">nilai 2</stripes:option>
                            <stripes:option value="lain-lain">lain-lain</stripes:option>
                        </stripes:select>
                    </td>
                </tr>
                <tr>
                    <td width="25%" align="right">Kepentingan Negeri/Persekutuan</td>
                    <td width="1%" align="center">:</td>
                    <td>
                        <stripes:select name="">
                            <stripes:option value="nilai 1">nilai 1</stripes:option>
                            <stripes:option value="nilai 2">nilai 2</stripes:option>
                            <stripes:option value="lain-lain">lain-lain</stripes:option>
                        </stripes:select>
                    </td>
                </tr>
                <tr>
                    <td width="25%" align="right">Warganegara</td>
                    <td width="1%" align="center">:</td>
                    <td>
                        <stripes:select name="">
                            <stripes:option value="nilai 1">nilai 1</stripes:option>
                            <stripes:option value="nilai 2">nilai 2</stripes:option>
                            <stripes:option value="lain-lain">lain-lain</stripes:option>
                        </stripes:select>
                    </td>
                </tr>
                <tr>
                    <td width="25%" align="right">Syer</td>
                    <td width="1%" align="center">:</td>
                    <td><stripes:text name=""/></td>   
                </tr>
                <tr>
                    <td width="25%" align="right">Jumlah Syer</td>
                    <td width="1%" align="center">:</td>
                    <td><stripes:text name=""/></td>   
                </tr>
                <tr>
                    <td width="25%" align="right">Alamat Berdaftar</td>
                    <td width="1%" align="center">:</td>
                    <td><stripes:text name=""/></td>   
                </tr>

                <tr>
                    <td width="25%" align="right"></td>
                    <td width="1%" align="center">:</td>
                    <td><stripes:text name=""/></td>   
                </tr>

                <tr>
                    <td width="25%" align="right"></td>
                    <td width="1%" align="center">:</td>
                    <td><stripes:text name=""/></td>   
                </tr>
                <tr>
                    <td width="25%" align="right">Poskod</td>
                    <td width="1%" align="center">:</td>
                    <td><stripes:text name=""/></td>   
                </tr>   
                <tr>
                    <td width="25%" align="right">Bandar</td>
                    <td width="1%" align="center">:</td>
                    <td>
                        <stripes:select name="">
                            <stripes:option value=" ">database</stripes:option>
                            <stripes:option value=" ">database</stripes:option>
                        </stripes:select>
                    </td>
                </tr> 
                <tr>
                    <td width="25%" align="right">Negeri</td>
                    <td width="1%" align="center">:</td>
                    <td>
                        <stripes:select name="">
                            <stripes:option value=" ">database</stripes:option>
                            <stripes:option value=" ">database</stripes:option>
                        </stripes:select>
                    </td>
                </tr>

                <tr>
                    <td width="25%" align="right">Alamat Surat-Menyurat(jika berlainan)</td>
                    <td width="1%" align="center">:</td>
                    <td><stripes:text name=""/></td>   
                </tr>

                <tr>
                    <td width="25%" align="right"></td>
                    <td width="1%" align="center">:</td>
                    <td><stripes:text name=""/></td>   
                </tr>

                <tr>
                    <td width="25%" align="right"></td>
                    <td width="1%" align="center">:</td>
                    <td><stripes:text name=""/></td>   
                </tr>
                <tr>
                    <td width="25%" align="right">Poskod</td>
                    <td width="1%" align="center">:</td>
                    <td><stripes:text name=""/></td>   
                </tr>   
                <tr>
                    <td width="25%" align="right">Bandar</td>
                    <td width="1%" align="center">:</td>
                    <td>
                        <stripes:select name="">
                            <stripes:option value=" ">database</stripes:option>
                            <stripes:option value=" ">database</stripes:option>
                        </stripes:select>
                    </td>
                </tr> 
                <tr>
                    <td width="25%" align="right">Negeri</td>
                    <td width="1%" align="center">:</td>
                    <td>
                        <stripes:select name="">
                            <stripes:option value=" ">database</stripes:option>
                            <stripes:option value=" ">database</stripes:option>
                        </stripes:select>
                    </td>
                </tr>
                <tr>
                    <td width="25%" align="right">No. Telefon</td>
                    <td width="1%" align="center">:</td>
                    <td><stripes:text name=""/></td>   
                </tr> 
            </table>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <table class="rowlabel1">
                <tr>
                    <td>
                        <stripes:submit name="simpan" value="Simpan"/>
                        <stripes:submit name="terus" value="Terus"/>
                        <stripes:submit name="keluar" value="Keluar"/>
                    </td>
                </tr>
            </table>
        </fieldset>
    </div>--%>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Syor Cadangan Pembantu Tadbir</legend>
            <table border="0" width="100%">
                <tr>
                    <td width="25%" align="right">Syor Cadangan </td>
                    <td width="1%" align="right">:</td>
                    <td align="right" width="5%"><stripes:radio name="a1" value="daftar"/>Daftar</td>
                    <td align="center"><stripes:radio name="a1" value="Tolak"/>Tolak</td>
                    <td align="left"><stripes:radio name="a1" value="Gantung"/>Gantung</td>
                </tr>
            </table>
            <table border="0" width="100%">
                <tr>
                    <td width="25%" align="right">Ulasan</td>
                    <td width="1%" align="center">:</td>
                    <td><stripes:textarea name="" cols="70" rows="5"/></td>
                </tr>
            </table>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <table class="rowlabel1">
                <tr>
                    <td>
                        <stripes:submit name="simpan" value="Simpan"/>
                        <stripes:submit name="sah" value="Sah"/>
                        <stripes:submit name="hantar" value="Hantar"/>
                        <stripes:submit name="keluar" value="Keluar"/>
                    </td>
                </tr>
            </table>
        </fieldset>
    </div>
</stripes:form>
