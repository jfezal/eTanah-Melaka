<%-- 
    Document   : semak_komisyen
    Created on : Jan 25, 2010, 5:10:29 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<H4>SEMAKAN SURAT BAYARAN KOMISYEN</H4>
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
    </div>
    <br>
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
    </div>
    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pemohon
            </legend>
            <div class="content"  align="center">
                <display:table class="tablecloth" name="" cellpadding="0" cellspacing="0" id="lineMP">
                    <display:column title="Bil" sortable="true">{lineMP_rowNum}</display:column>
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
               Penggunaan Wang Belian (Seksyen 268)
            </legend>
             <legend>
             Mengikut Seksyen 268, wang belian yang diperolehi dalam apa-apa
             jualan mestilah digunakan seperti berikut:
            </legend>
            <div class="content" align="center">
                <table>

                  <tr>
                        <td class="rowlabel1">(a.i) Bayaran kepada Pihak Berkuasa Negeri (Cukai Tanah) (RM): </td>
                          <td></td>
                  </tr>
                  <tr>
                        <td class="rowlabel1">(a.ii) Bayaran kepada Pihak Berkuasa Tempatan (Cukai Pintu) (RM): </td>
                  </tr>
                  <tr>
                        <td class="rowlabel1">(a.iii) Bayaran kepada Pemberi Pajak untuk Lelongan Pajakan (RM): </td>
                  </tr>
                  <tr>
                        <td class="rowlabel1">(b) Perbelanjaan Pengurusan Perintah Jualan (RM): </td>
                  </tr>
                  <tr>
                        <td class="rowlabel1">(c) Bayaran kepada Pemegang Gadai (RM): </td>
                  </tr>
                  <tr>
                        <td class="rowlabel1">(d) Pembayaran Berkala atau Anuiti (RM): </td>
                  </tr>
                  <tr>
                        <td class="rowlabel1">(e) Pembayaran Gadaian Terkemudian (RM): </td>
                  </tr>

                </table>
            </div>
         </fieldset>
    </div><br>
<div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Maklumat Pemegang Gadai
            </legend>
            <div class="content"  align="center">
                <display:table class="tablecloth" name="" cellpadding="0" cellspacing="0" id="lineMP">
                    <display:column title="Bil" sortable="true"></display:column>
                    <display:column title="Nama"/>
                    <display:column title="Amaun (RM)" />
                </display:table>
            </div>
        </fieldset>
    </div><br>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Semakan Dokumen Wajib
            </legend>
            <div class="content"  align="center">
                <display:table class="tablecloth" name="" cellpadding="0" cellspacing="0" id="lineMP">
                    <display:column title="Bil" sortable="true"></display:column>
                    <display:column title="Dokumen"/>
                    <display:column title="Catatan" />
                    <display:column title="ID Dokumen"/>
                </display:table>
            </div>
        </fieldset>
    </div><br>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Semakan Dokumen Tambahan
            </legend>
            <div class="content"  align="center">
                <display:table class="tablecloth" name="" cellpadding="0" cellspacing="0" id="lineMP">
                    <display:column title="Bil" sortable="true"></display:column>
                    <display:column title="Dokumen"/>
                    <display:column title="Catatan" />
                    <display:column title="ID Dokumen"/>
                </display:table>
            </div>
        </fieldset>
    </div><br>

        <div class="subtitle">
        <fieldset class="aras1">
            <legend>
               Semakan PTD
            </legend>
            <div class="content" align="center">
               <table>
                <tr>
                    <td class="rowlabel1">Pengesahan :</td>
                    <td class="input1">
                        <s:radio name="radio_" id="radio_" value=""/> Sokong
                        <s:radio name="radio_" id="radio_" value=""/> Tidak Disokong
                    </td>
                </tr>
                    <tr>
                        <td class="rowlabel1">Ulasan :</td>
                        <td class="input1"><s:textarea name="" rows="5" cols="50"/></td>
                    </tr>

                </table>
            </div>
        </fieldset>
    </div>
    
    <div class="content" align="right">
        <table >
            <tr>
                <td align="right">
                     <s:button name="" value="Papar Surat" class="btn" />
                    <s:button name="" value="Isi Semula" class="btn" />
                     <s:button name="" value="Simpan" class="btn" />
                    <s:button name="" value="Hantar" class="btn" />
                    <s:button name="" value="Keluar" class="btn" /></td>
            </tr>
        </table>
    </div><br>
</s:form>