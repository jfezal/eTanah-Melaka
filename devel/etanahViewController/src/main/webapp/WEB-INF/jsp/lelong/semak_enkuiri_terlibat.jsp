<%-- 
    Document   : semak_enkuiri_terlibat
    Created on : Jan 21, 2010, 2:03:57 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<H4>SEMAK KEHADIRAN ENKUIRI PIHAK TERLIBAT</H4>
<s:form action="">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Tajuk Kertas
            </legend>
            <div class="content" align="LEFT">
                <FONT color="BLACK">PERMOHONAN PERINTAH JUALAN (SEKSYEN 260 KTN, 1965) KE ATAS LOT 666,
                    DI MUKIM 4 JENIS HAKMILIK GM 286 DAERAH BARAT DAYA DARIPADA SOUTHERN
                    BANK BERHAD</FONT>
            </div>
        </fieldset>
    </div><BR>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Permohonan
            </legend>
            <div class="content" align="center">
            <table>
             <tr>
                    <td class="rowlabel1"> ID Permohonan :</td>
                    <td></td>
             </tr>
                 <tr>
                        <td class="rowlabel1"> Tarikh Permohonan :</td>
                        <td></td>
                 </tr>

            </table>
            </div>
       </fieldset>
    </div><br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pemohon
            </legend>
            <div class="content"  align="center">
                <display:table class="tablecloth" name="" cellpadding="0" cellspacing="0" id="lineMP">
                    <display:column title="Nama"/>
                    <display:column title="Jenis Pengenalan" />
                    <display:column title="Nombor" />
                    <display:column title="Alamat" />
                    <display:column title="Nombor Telefon Rumah"/>
                    <display:column title="Nombor Telefon Pejabat" />
                    <display:column title="Samb" />
                    <display:column title="Nombor Telefon Bimbit" />
                    <display:column title="Emel" />
                </display:table>
            </div>
        </fieldset>
    </div><br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Tanah/ Pajak/ Pajakan Kecil
            </legend>

              <div class="content"  align="center">
                  <table>
                        <tr>
                            <td class="rowlabel1"> Daerah :</td>
                            <td class="input1">
                        </td>
                        </tr>
                    </table>

                 <display:table class="tablecloth" name="" cellpadding="0" cellspacing="0" id="lineMP">
                   <display:column title="Bil" sortable="true">{lineMP_rowNum}</display:column>
                   <display:column title="Bandar/Pekan/Mukim" />
                   <display:column title="Nombor Lot" />
                   <display:column title="Hakmilik" />
                   <display:column title="Keluasan"/>
                   <display:column title="Jenis Pengggunaan Tanah" />
                   <display:column title="Kod Syarat Nyata" />
                   <display:column title="Kod Sekatan Kepentingan" />
                 </display:table>
            </div>
       </fieldset>
    </div><br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Ketetapan Enkuiri
            </legend>
            <div class="content" align="center">
                <table>
                    <tr>
                        <td class="rowlabel1">Tarikh :</td>
                        <td class="input1"></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Masa :</td>
                        <td class="input1"></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1"> Tempat :</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1"> Perkara :</td>
                        <td></td>
                    </tr>
                </table>
            </div>
        </fieldset>
    </div><br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Kehadiran
            </legend>
             
            <div class="subtitle">
               <div class="content"  align="right">
                    <table>
                        <tr>
                                <s:submit name="" value="Hapus" class="btn"/>
                                <s:submit name="" value="Tambah" class="btn"/>
                        </tr>
                    </table>
                </div>

                 <div class="content"  align="center">
                    <a href="">Pilih Semua</a> | <a href="">Kosongkan Pilihan</a><br>

                <display:table class="tablecloth" name="" cellpadding="0" cellspacing="0" id="lineMP">
                   <display:column title="Pilih">
                        <s:checkbox name="" id="" class="checkbox" />
                   </display:column>
                   <display:column title="Bil" sortable="true"></display:column>
                   <display:column title="Nama"/>
                   <display:column title="Nombor Pengenalan" />
                   <display:column title="Kehadiran" />
                        <s:select name="" lang="4">
                             <s:option >Hadir</s:option>
                                 <s:option>Tidak Hadir</s:option>
                        </s:select>
                </display:table>
             </div>
             </div>
        </fieldset>
        </div> <br>

       <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Semakan Tambahan
            </legend>

            <div class="subtitle">
               <div class="content"  align="left">
                    <table>
                          <s:submit name="" value="Hapus" class="btn"/>
                    </table>
                </div>

                 <div class="content"  align="center">
                    <a href="">Pilih Semua</a> | <a href="">Kosongkan Pilihan</a><br>

                <display:table class="tablecloth" name="" cellpadding="0" cellspacing="0" id="lineMP">
                    <display:column title="Pilih">
                    <s:checkbox name="" id="" class="checkbox" />
                    </display:column>

                    <display:column title="Dokumen"/>
                    <display:column title="Catatan" />
                </display:table>
             </div>
           </div>
         </fieldset>
        </div><br>
     <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Tambah Dokumen
            </legend>
           <div class="content" align="center">
            <table>
             <tr>
                    <td class="rowlabel1"><FONT color="red">*</FONT>Dokumen :</td>
                    <td><s:text name="" /></td>
             </tr>
             <tr>
                    <td class="rowlabel1"><FONT color="red">*</FONT>Catatan :</td>
                    <td><s:text name="" /></td>
             </tr>
             <table>
                    <div class="content"  align="left">
                    <s:submit name="" value="Tambah" class="btn"/>
                    </div>
             </table>
            </table>
           </div>
       </fieldset>
    </div>
   <div class="content" align="right">
        <table >
            <tr>
                <td align="right">
                    <s:button name="" value="Isi Semula" class="btn" />
                    <s:button name="" value="Terus" class="btn" />
                    <s:button name="" value="Keluar" class="btn" /></td>
            </tr>
        </table>
    </div><br>
</s:form>