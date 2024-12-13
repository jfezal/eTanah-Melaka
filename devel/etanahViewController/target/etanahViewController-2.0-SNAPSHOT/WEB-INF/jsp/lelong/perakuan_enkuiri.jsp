<%-- 
    Document   : perakuan_enkuiri
    Created on : Jan 25, 2010, 9:15:46 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<H4>SEMAK DAN PERAKUAN KEPUTUSAN ENKUIRI</H4>
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
               Maklumat Perintah Jualan
            </legend>
            <div class="content" align="center">
    <div class="subtitle">
        <div class="content" align="center">
               <table>
                <tr>
                   <td class="rowlabel1">Jenis Perintah Jualan: </td>
                    
                </tr>
                <tr>
                    <td class="rowlabel1">Tarikh Perintah Jualan:</td>
                  
                </tr>
                <tr>
                    <td class="rowlabel1">Masa Perintah Jualan: </td>
                </tr>
                <tr>
                    <td class="rowlabel1"> Tempat Perintah Jualan Diadakan: </td>
                </tr>
                <tr>
                        <td class="rowlabel1">Harga Rizab (RM): </td>
                </tr>
                <tr>
                        <td class="rowlabel1">Dalam Perkataan:</td>
                        <td>Ringgit</td>
                </tr>
               
                        <td class="rowlabel1">Harga Deposit Rizab Dalam Perkataan : </td>
                        <td>Ringgit</td>
               </table>
            </div>
       </div>
   </div>
        </fieldset>
    </div><br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Amaun Kena Dibayar
            </legend>
            <div class="content"  align="center">
                <display:table class="tablecloth" name="" cellpadding="0" cellspacing="0" id="lineMP">
                    <display:column title="Bil" sortable="true"></display:column>
                    <display:column title="Nama"/>
                    <display:column title="Amoun (RM)" />
                </display:table>
            </div>
        </fieldset>
    </div><br>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Permohonan Enkuiri
            </legend>
            <div class="content" align="center">
            <table>
             <tr>
                    <td class="rowlabel1"> Kes atau Isu :</td>
                    <td></td>
             </tr>
                 <tr>
                        <td class="rowlabel1"> Seksyen KTN :</td>
                        <td></td>
                 </tr>
                 <tr>
                        <td class="rowlabel1"> Perihal Seksyen :</td>
                        <td></td>
                 </tr>
            </table>
            </div>
       </fieldset>
    </div><br>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Laporan Enkuri
            </legend>
            <div class="content" align="center">
            <table>
             <tr>
                    <td class="rowlabel1"> Laporan Enkuiri :</td>
                    <td></td>
             </tr>
                 <tr>
                        <td class="rowlabel1"> Ulasan :</td>
                        <td></td>
                 </tr>
            </table>
            </div>
       </fieldset>
    </div><br>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Keputusan Enkuiri
            </legend>
            <div class="content" align="center">
            <table>
             <tr>
                    <td class="rowlabel1"> Pengesahan :</td>
                    <td></td>
             </tr>
            </table>
            </div>
       </fieldset>
    </div><br>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
               Semakan Penolong Pentadbir Tanah
            </legend>
            <div class="content" align="center">
               <table>
                <tr>
                    <td class="rowlabel1">Semakan :</td>
                    <td class="input1">
                        <s:radio name="radio_" id="radio_" value=""/> Mengesahkan Keputusan
                        <s:radio name="radio_" id="radio_" value=""/> Semak Semula
                    </td>
                </tr>
                </table>

            </div>
        </fieldset>
    </div><br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
               Ulasan Pengesahan Keputusan
            </legend>
            <div class="content" align="center">
                <table>
                  <tr>
                      <td class="rowlabel1">Ulasan :</td>
                        <td>
                               <s:textarea name="" rows="6" cols="50" />
                             </td>
                    </tr>

                </table>
            </div>
       </fieldset>
   </div><br>
   <div class="content" align="right">
        <table >
            <tr>
                <td align="right">
                    <s:button name="" value="Isi Semula" class="btn" />
                    <s:button name="" value="Simpan" class="btn" />
                    <s:button name="" value="Hantar" class="btn" />
                    <s:button name="" value="Keluar" class="btn" /></td>
            </tr>
        </table>
    </div><br>


</s:form>