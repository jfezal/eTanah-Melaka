<%-- 
    Document   : penyediaan_kertas_pertimbangan
    Created on : Jan 14, 2010, 3:42:41 PM
    Author     : zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.pelupusan.PelupusanActionBean">
    <p>PENYEDIAAN KERTAS PERTIMBANGAN PENTADBIR TANAH DAERAH</p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Tajuk Kertas
            </legend>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
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
                Draf Kertas Pertimbangan
            </legend>
            <p>
                <label>ID Permohonan :</label>
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
            <div class="content">
                <fieldset class="aras2">
                    <legend>
                        Perihal Tanah
                    </legend>
                    <p>&nbsp;</p>
                    <p>&nbsp;</p>
                    <p>&nbsp;</p>
                </fieldset>
            </div>
            <div class="content">
                <fieldset class="aras2">
                    <legend>
                        Perihal Pemohon
                    </legend>
                    <p>&nbsp;</p>
                    <p>&nbsp;</p>
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
                <label for="ID Berkaitan"> Jumlah Bayaran Pendudukan (RM) : </label>
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
    </div>
    <p>
        <label>&nbsp;</label>
        <s:submit name="" value="Simpan" class="btn"/>
        <s:submit name="" value="Hantar" class="btn"/>
        <s:submit name="" value="Keluar" class="btn"/>
    </p>
</s:form>
