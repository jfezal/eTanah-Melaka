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

        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Ismen</legend>
            <p>
                <label>Amaun (RM) :</label>
                <stripes:text name=""/>
            </p>

            <p>
                <label>Duti Setem (RM) :</label>
                <stripes:text name=""/>
            </p>

            <p>
                <label>Tujuan Ismen :</label>
                <stripes:select name="">
                    <stripes:option value="sebab 1">sebab 1</stripes:option>
                    <stripes:option value="sebab 2">sebab 2</stripes:option>
                    <stripes:option value="sebab 3">sebab 3</stripes:option>
                </stripes:select>
            </p>
            <p>
                <label>Kod Jangkamasa :</label>

                <stripes:select name="">
                    <stripes:option value="Kod 1">Kod 1 - bertempoh</stripes:option>
                    <stripes:option value="Kod  2">Kod  2 - tiada tempoh</stripes:option>
                    <stripes:option value="Lain-Lain">Lain-Lain</stripes:option>
                </stripes:select>
            </p>

            <p>
                <label>Tarikh Berkuatkuasa :</label>
                <stripes:text name=""/>
            </p>

            <p>
                <label>Tarikh Tamat Tempoh :</label>
                <stripes:text name=""/>
            </p>

        </fieldset>
    </div>

    <%--<fieldset class="aras1">
        <legend>Senarai Hakmilik yang Terlibat</legend>

        <table border="1" width="95%" align="center" datapagesize="2" >
            <tr>
                <th>Bil.                </th>
                <th>Kod Tanggungkuasa Ismen      </th>
                <th>ID Hakmilik   </th>
            </tr>
            <tr>
                <td>1       </td>
                <td>Tiada Data       </td>
                <td>Tiada Data       </td>
            </tr>
        </table>
</fieldset>--%>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Senarai Pihak Berkepentingan</legend>
            <table  class="tablecloth" width="95%" align="center" datapagesize="2" >
                <stripes:submit name="hapus" value="Hapus" class="btn"/>
                <tr>
                    <th>Pilih               </th>
                    <th>Bil.                </th>
                    <th>Nama                </th>
                    <th>Nombor Pengenalan   </th>
                    <th>Kod Jenis PB        </th>
                    <th>Syer                </th>
                </tr>
                <tr>
                    <td align="center"><stripes:checkbox name="chx1"/></td>
                    <td>Tiada Data       </td>
                    <td>Tiada Data       </td>
                    <td>Tiada Data       </td>
                    <td>Tiada Data       </td>
                    <td>Tiada Data       </td>
                </tr>
            </table>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
           
            <legend>Maklumat Pihak Berkepentingan Baru</legend>
            <p>
                <label>Nama : </label>
                <stripes:text name=""/>
            </p>

            <p>
                <label>Jenis Pihak Berkepentingan :</label>
                <stripes:select name="">
                    <stripes:option value="pemajak">pemajak</stripes:option>
                    <stripes:option value="penyewa">penyewa</stripes:option>
                    <stripes:option value="pemegang gadaian">pemegang gadaian</stripes:option>
                </stripes:select>

            </p>

            <p>
                <label>Jenis Pengenalan :</label>

                <stripes:select name="">
                    <stripes:option value="IC Baru">IC Baru</stripes:option>
                    <stripes:option value="IC Lama">No Syarikat</stripes:option>
                    <stripes:option value="Passport">Passport</stripes:option>
                    <stripes:option value="Lain-Lain">Lain-Lain</stripes:option>
                </stripes:select>

            </p>

            <p>
                <label>Nombor Pengenalan :</label>

                <stripes:text name=""/>

            </p>

            <p>
                <label>Alamat : </label>

                <stripes:text name=""/>
            </p>

            <p>
                <label>:</label>
                <stripes:text name=""/>
            </p>

            <p>
                <label>:</label>
                <stripes:text name=""/>
            </p>

            <p>
                <label>Negeri :</label>

                <stripes:select name="">
                    <stripes:option value=" ">database</stripes:option>
                    <stripes:option value=" ">database</stripes:option>
                </stripes:select>

            </p>

            <p>
                <label>Bandar : </label>
                <stripes:select name="">
                    <stripes:option value=" ">database</stripes:option>
                    <stripes:option value=" ">database</stripes:option>
                </stripes:select>
            </p>

            <p>
                <label>Poskod :</label>
                <stripes:text name=""/>
            </p>

            <stripes:submit name="tambah" value="Tambah" class="btn"/>
           
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Syor Cadangan Pembantu Tadbir</legend>
            <p><label>Syor Cadangan : </label>
            <stripes:radio name="a1" value="Lulus"/>Daftar
            <stripes:radio name="a1" value="Tolak"/>Tolak
            <stripes:radio name="a1" value="Gantung"/>Gantung
            </p>
            <p><label>Ulasan : </label>
                <stripes:textarea name="" cols="70" rows="5"/>
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <p><label>&nbsp;</label>
                <stripes:submit name="simpan" value="Simpan" class="btn"/>
                <stripes:submit name="sah" value="Sah" class="btn"/>
                <stripes:submit name="hantar" value="Hantar" class="btn"/>
            </p>
        </fieldset>

    </div>
</stripes:form>
