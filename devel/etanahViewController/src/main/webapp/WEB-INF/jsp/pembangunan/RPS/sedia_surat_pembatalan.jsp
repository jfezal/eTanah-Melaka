<%-- 
    Document   : sedia_surat_pembatalan
    Created on : Dec 31, 2009, 12:11:32 PM
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
        <title>Sedia Surat Makluman Pembatalan</title>
    </head>
    <body>
       <stripes:form beanclass="etanah.view.stripes.pembangunan.RayuanPertimbanganSemulaActionBean">
          <%-- <jsp:include page="/WEB-INF/jsp/pembangunan/common/incMaklumat_permohonan.jsp"/>
           <jsp:include page="/WEB-INF/jsp/pembangunan/common/incMaklumat_pemohon.jsp"/>
           <jsp:include page="/WEB-INF/jsp/pembangunan/common/incKertasMMK.jsp"/>--%>
         
           <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Makluman Pembatalan
                    </legend>
                    <div class="content" align="center">
                        <table>
                             <tr>
                                <td>Status :</td>
                                <td><s:text name="status" disabled="true" size="25"/> &nbsp; &nbsp;
                                <s:button name="batal" value="Batal" class="btn"/></td>
                            </tr>
                            <tr>
                                <td>Ulasan :</td>
                                <td><s:textarea cols="87" rows="8" name="ulasan"/></td>
                            </tr>
                        </table>
                    </div>
                </fieldset>
           </div>
          <%-- <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Pihak Berkepentingan
                    </legend>
                    <div class="content" align="center">
                        <p> Sila klik pada butang 'Pilih' untuk memasukkan maklumat pihak berkepentingan</p>
                        <p> Sila klik pada butang 'Hapus' untuk mengosongkan maklumat pihak berkepentingan</p>
                        <p> Sila klik pada butang 'Tambah' untuk menambah maklumat pihak berkepentingan</p>&nbsp;
                        <p><s:button name="pilihSemua" value="Pilih Semua"/> | <s:button name="kosongkan" value="Kosongkan Pilihan"/></p>
                        <display:table class="tablecloth" name="" pagesize="4" cellpadding="0" cellspacing="0" id="line" size="100">
                            <display:column property="pilih" title="Pilih">
                                <s:checkbox name="pilih"/>
                            </display:column>
                            <display:column property="nama" title="Nama"/>
                            <display:column property="noPengenalan" title="No. Pengenalan"/>
                            <display:column property="idHakmilik" title="ID Hakmilik"/>
                            <display:column property="pihakBerkepentingan" title="Jenis Pihak Berkepentingan"/>
                        </display:table>
                        <br>
                        <table>
                            <tr>
                                <td><s:button name="pilih" value="Pilih" class="btn"/>&nbsp;</td>
                                <td><s:button name="hapus" value="Hapus" class="btn"/>&nbsp;</td>
                                <td><s:button name="tambah" value="Tambah" class="btn"/>&nbsp;</td>
                            </tr>
                        </table>
                    </div>
                </fieldset>
           </div>--%>
           <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Penerima Surat
                    </legend>
                    <div class="content" align="center">
                        <p> Sila klik pada butang 'Hapus' untuk mengosongkan maklumat penerima surat</p>&nbsp;
                        <p><s:button name="pilihSemua" value="Pilih Semua"/> | <s:button name="kosongkan" value="Kosongkan Pilihan"/></p>
                        <display:table class="tablecloth" name="" pagesize="4" cellpadding="0" cellspacing="0" id="line" size="100">
                            <display:column property="pilih" title="Pilih">
                                <s:checkbox name="pilih"/>
                            </display:column>
                            <display:column property="nama" title="Nama"/>
                            <display:column property="alamat" title="Alamat"/>
                            <display:column property="caraSerahan" title="Cara Serahan">
                                <s:select name="jabatan">
                                        <s:option>Pilih..</s:option>
                                        <s:option>Serah Tangan</s:option>
                                        <s:option>Pos</s:option>
                                        <%--<s:options-collection collection="" value="" label=""/>--%>
                                </s:select>
                            </display:column>
                        </display:table>
                      <br>
                      <table>
                            <tr>
                                <td><s:button name="hapus" value="Hapus" class="btn"/>&nbsp;</td>
                            </tr>
                     </table>
                    </div>
                </fieldset>
           </div>
           <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Tandatangan Surat Keputusan
                    </legend>
                    <div class="content" align="center">
                        <table>
                            <tr>
                                <td>Ditandatangani oleh :</td>
                                <td> <s:select name="jawatan">
                                                <s:option>Pilih..</s:option>
                                                <s:option>Timbalan Pentadbir Tanah dan Daerah</s:option>
                                                <s:option>Pentadbir Tanah dan Daerah</s:option>
                                                <%--<s:options-collection collection="" value="" label=""/>--%>
                                                </s:select></td>&nbsp;
                            </tr>
                            <tr>
                                <td>Nama : </td>
                                <td><s:text name="ttPTD"/></td>&nbsp;
                            </tr>
                        </table>
                    </div>
                </fieldset>
           </div>
                            <p align="center">
                                <s:button name="cetakSuratBatal" value="Surat Pembatalan" class="longbtn"/>&nbsp;
                                <s:button name="simpan" value="Simpan" class="btn"/>&nbsp;
                            </p>
       </stripes:form>
    </body>
</html>