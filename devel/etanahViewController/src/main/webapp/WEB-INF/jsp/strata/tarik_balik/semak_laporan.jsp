<%-- 
    Document   : semak_laporan
    Created on : Jul 8, 2010, 9:17:14 AM
    Author     : User
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.strata.SemakLaporanPenariActionBean">
     <s:messages/>
    <s:errors/>
     <div class="subtitle">
        <fieldset class="aras1">
            <legend>Semak Laporan Penarikan Balik</legend>
            <p>
                <label>Tujuan  </label>
                <s:textarea  name="tajuan" cols="100" rows="5" class="normal_text"/>&nbsp;
            </p>
            <p>
                <label>Nama :</label>
                <s:text name="nama"/>&nbsp;
            </p>
            <p>
                <label>Pemilik Berdaftar :</label>
                <s:text name="pemilikBerdaftar"/>&nbsp;
            </p>
             <p>
                <label>No Fail :</label>
                <s:text name="noFail"/>&nbsp;
            </p>
             <p>
                <label>Mukim :</label>
                <s:text name="mukim"/>&nbsp;
            </p>
            <p>
                <label>Daerah :</label>
                <s:text name="daerah"/>&nbsp;
            </p>
            <p>
                <label>No Hakmilik :</label>
                <s:text name="noHakmilik"/>&nbsp;
            </p>
            <p>
                <label>No Lot :</label>
                <s:text name="noLot"/>&nbsp;
            </p>
            <p>
                <label>Nama Skim :</label>
                <s:text name="namaSkim"/>&nbsp;
            </p>
            <p>
                <label>Bilangan Skim :</label>
                <s:text name="bilanganSkim"/>&nbsp;
            </p>
            <p>
                <label>Bilangan Petak :</label>
                <s:text name="bilanganPetak"/>&nbsp;
            </p>
            <p>
                <label>Tarikh Diluluskan :</label>
                <s:text name="trikhDiluluskan"/>&nbsp;
            </p>
            <p>
                <label>Tarikh Bayaran Kelulusan :</label>
                <s:text name="TarikhBayaranKelulusan"/>&nbsp;
            </p>
            <p>
                <label>Masalah / Isu yang Berbangkit :</label>
                <s:textarea  name="masalah" cols="70" rows="5" class="normal_text"/>&nbsp;
            </p>
            <p>
                <label>Syor Penolong Pengarah Strata :</label>
                <s:textarea  name="syorPenolongPengarahStrata" cols="70" rows="5" class="normal_text"/>&nbsp;
            </p>
            <p>
                <label>PerakuanTimbalan Pendaftar Geran Tanah :</label>
                <s:textarea  name="pemilik.laporanLokasi" cols="70" rows="5" class="normal_text"/>&nbsp;
            </p>
            <br>
        </fieldset>
     </div>
    <p align="center">
       <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
    </p>
</s:form>