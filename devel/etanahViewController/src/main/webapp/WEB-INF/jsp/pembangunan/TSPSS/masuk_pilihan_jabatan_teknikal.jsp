<%-- 
    Document   : masuk_pilihan_jabatan_teknikal
    Created on : Dec 23, 2009, 10:50:33 AM
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
        <title>Membuat Pilihan</title>
    </head>
    <body>
        <stripes:form beanclass="etanah.view.stripes.pembangunan.TSPchSempadanActionBean">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                       Jabatan Teknikal Yang Perlu Membuat Ulasan
                    </legend>
                    <div class="content" align="center">
                        <p>Isikan jangkamasa bagi setiap jabatan. Tandakan ruangan pilih dan klik butang 'Pilih' untuk masukkan ke dalam jadual
                           Senarai Jabatan Teknikal Yang Telah Dipilih. Tandakan ruangan pilih dan klik butang 'Hapus' untuk mengeluarkan
                           jabatan teknikal daripada senarai jabatan yang perlu membuat ulasan.</p>
                        <br>
                        <p><s:button name="pilihSemua" value="Pilih Semua"/> | <s:button name="kosongkan" value="Kosongkan Pilihan"/></p>
                        <display:table class="tablecloth" name="" pagesize="4" cellpadding="0" cellspacing="0" id="line" size="100">
                            <display:column property="pilih" title="Pilih">
                                <s:checkbox name="pilih"/>
                            </display:column>
                            <display:column property="bilangan" title="Bil."/>
                            <display:column property="namaJabatan" title="Nama Jabatan"/>
                            <display:column property="jangkamasa" title="Jangkamasa(Hari)">
                                <s:text name="jangkamasa"/>
                            </display:column>
                        </display:table>
                        <br>
                        <table>
                            <tr>
                                <td><s:button name="tambah" value="Tambah" class="btn"/></td>
                                <td><s:button name="hapus" value="Hapus" class="btn"/></td>
                        </table>
                        <br>
                    </div>
                </fieldset>
           </div>
           <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                       Senarai Jabatan Teknikal Yang Terlibat
                    </legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="" pagesize="4" cellpadding="0" cellspacing="0" id="line" size="100">
                            <display:column property="pilih" title="Pilih">
                                <s:checkbox name="pilih"/>
                            </display:column>
                            <display:column property="bilangan" title="Bil."/>
                            <display:column property="namaJabatan" title="Nama Jabatan"/>
                            <display:column property="tarikhHantar" title="Tarikh Hantar"/>
                            <display:column property="jangkamasa" title="Jangkamasa(Hari)"/>
                            <display:column property="tarikhJTerima" title="Tarikh Jangka Terima"/>
                        </display:table>
                        <br>
                        <table>
                            <tr>
                                <td><s:button name="hapus" value="Hapus" class="btn"/></td>
                        </table>
                        <br>
                    </div>
                </fieldset>
           </div>
                        <p align="center">
                            <s:button name="terus" value="Terus" class="btn"/>&nbsp;
                            <s:button name="kembali" value="Kembali" class="btn"/>&nbsp;
                        </p>
        </stripes:form>
    </body>
</html>
