<%-- 
    Document   : dev_paparan_ulasan_jabatan_teknikal
    Created on : Jan 18, 2010, 11:49:43 AM
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

<stripes:form beanclass="etanah.view.stripes.pembangunan.UlasanJabatanTeknikalActionBean">
           <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Penyediaan Laporan
                    </legend>
                    <div class="content" align="center">
                        <table>
                            <tr>
                                <td>Jabatan :</td>
                                <td><s:text name="jabatan" size="40"/></td>
                            </tr>
                            <tr>
                                <td>Tarikh Penyediaan :</td>
                                <td><s:text name="tarikhSedia" size="25" disabled="true"/></td>
                            </tr>
                            <tr>
                                <td>Nama Penyedia :</td>
                                <td><s:text name="namaPenyedia" size="40"/></td>
                            </tr>
                            <tr>
                                <td>No. Rujukan :</td>
                                <td><s:text name="noRujukan" size="40"/></td>
                            </tr>
                        </table>
                    </div>
                </fieldset>
           </div>
           <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                      Ulasan Jabatan Teknikal
                    </legend>
                    <div class="content" align="center">
                        <table>
                             <tr>
                                <td>Syor :</td>
                                <td><s:select name="syor">
                                        <s:option>Pilih..</s:option>
                                        <s:option>Boleh Diluluskan</s:option>
                                        <s:option>Tidak Boleh Diluluskan</s:option>
                                    </s:select></td>
                            </tr>
                            <tr>
                                <td>Ulasan  :</td>
                                <td><s:textarea cols="87" rows="8" name="ulasan"/></td>
                            </tr>
                        </table>
                        <br>
                        <p>Klik butang 'Tambah' untuk masukkan kadar nilaian tanah</p>
                        <p>Klik butang 'Hapus' untuk hapuskan kadar nilaian tanah</p>
                        <br>
                        <table>
                            <tr>
                                <td align="center">
                                    <s:submit name="tambah" value="Tambah" class="btn"/> &nbsp;
                                    <s:submit name="hapus" value="Hapus" class="btn"/> &nbsp;
                                </td>
                            </tr>
                        </table>
                        <br>
                        <display:table class="tablecloth" name="nilaiTanah" pagesize="4" cellpadding="0" cellspacing="0" id="line" size="100">
                            <display:column property="pilih" title="Pilih">
                                <s:checkbox name="pilih"/>
                            </display:column>
                            <display:column title="Bil">${line_rowNum}</display:column>
                            <display:column property="idHakmilik" title="ID Hakmilik"/>
                            <display:column property="keluasanTnh" title="Keluasan Tanah"/>
                            <display:column property="kadarNilaian" title="Kadar Nilaian (RM)"/>
                            <display:column property="anggaranNilaian" title="Anggaran Nilaian (RM)"/>
                           <display:column property="anggaranBundar" title="Anggaran Bundar (RM)"/>
                        </display:table>
                        <br>
                    </div>
                </fieldset>
           </div>
                               
        </stripes:form>