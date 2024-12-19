<%-- 
    Document   : penyediaan_borang_4A
    Created on : Jan 11, 2010, 10:21:58 AM
    Author     : zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.pelupusan.PelupusanActionBean">
    <div class="subtitle">
        <p>PERMOHONAN PENYEDIAAN BORANG 4A</p>
        <fieldset class="aras1">
            <legend>
                Maklumat Bayaran
            </legend>
            <p>
                <label>No. Lesen :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Bayaran (RM) :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Tempoh Berakhir :</label>
                <s:text class="datepicker" name=""/>
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pemohon
            </legend>
            <p>
                <label>Nama :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Jenis Pengenalan :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Nombor Kad Pengenalan :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Alamat :</label>
                <s:textarea name="" rows="5" cols="70"/>
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Bandar :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Negeri :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Tarikh Dikeluarkan :</label>
                <s:text class="datepicker" name=""/>
            </p>

        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Perihal Tanah
            </legend>
            <p>
                <label>Bandar/Pekan/Mukim :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Penggunaan Tanah :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Tempat (No. Lot/PT jika ada) :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Luas Tanah :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Peruntukan Tambahan :</label>
                <s:text name=""/>
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Pengesahan Pentadbir Tanah
            </legend>
            <p>
                <label>Syarat :</label>
                <s:textarea name="" rows="5" cols="70"/>
            </p>
        </fieldset>
    </div>
    <div>
        <p>
            <label>&nbsp;</label>
            <s:submit name="" value="Hantar" class="btn"/>
            <s:submit name="" value="Simpan" class="btn"/>
            <s:reset name="" value="Isi Semula" class="btn"/>
            <s:submit name="" value="Keluar" class="btn"/>
        </p>
    </div>
</s:form>