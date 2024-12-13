<%-- 
    Document   : surat_baki
    Created on : Jan 26, 2010, 9:23:34 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%--<H4>PENYEDIAAN SURAT BAYARAN BAKI</H4>--%>
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
                Maklumat Pembida
            </legend>
            <div class="content"  align="center">
                <display:table class="tablecloth" name="" cellpadding="0" cellspacing="0" id="lineMP">
                    <display:column title="Bil" sortable="true">{lineMP_rowNum}</display:column>
                    <display:column title="Nama"/>
                    <display:column title="Alamat" />
                    <display:column title="Nombor Telefon Rumah"/>
                    <display:column title="Nombor Telefon Pejabat" />
                    <display:column title="Nombor Telefon Bimbit" />
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
                Maklumat Pihak Berkepentingan
            </legend>
             <div class="subtitle">
                    Sila Klik Pada Butang Pilih Untuk Memasukkan Maklumat Pihak Berkepentingan
               <div class="content"  align="left">
                    <table>
                        <tr>
                            <td align="right">
                                <s:submit name="" value="Pilih" class="btn"/>
                            </td>
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
                   <display:column title="ID Hakmilik" />
                   <display:column title="Jenis Pihak Berkepentingan" />
                </display:table>
             </div>
             </div>
        </fieldset>
        </div>

                                <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Amaun Kena Dibayar
            </legend>
             <div class="subtitle">
                    Sila Klik Pada Butang Hapus Butang Untuk Mengosongkan Maklumat Pihak Berkepentingan
               <div class="content"  align="center">
                     <display:table class="tablecloth" name="" cellpadding="0" cellspacing="0" id="lineMP">
                   <display:column title="Pilih">
                        <s:checkbox name="" id="" class="checkbox" />
                   </display:column>
                   <display:column title="Bil" sortable="true"></display:column>
                   <display:column title="Nama"/>
                   <display:column title="Amaun (RM)" />
                </display:table>
               </div>
           </div>
        </fieldset>
    </div><br>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pembayaran Baki
            </legend>

            <div class="subtitle">
        <div class="content" align="center">
               <table>

                 <tr>
                    <td class="rowlabel1"> Harga Bidaan (RM): </td>
                    <td>   (harga bidaan yang tertinggi)</td>
                </tr>
                 <tr>
                        <td class="rowlabel1"> Jumlah Harga Bidaan Dalam Perkataan :</td>
                        <td> Ringgit</td>
                 </tr>
                <tr>
                    <td class="rowlabel1"> Jumlah Bayaran Deposit (RM): </td>
                    <td> (10% daripada harga jualan)</td>
                </tr>
               
                 <tr>
                    <td class="rowlabel1"> Jumlah Bayaran Baki (RM): </td>
                   
                </tr>
                <tr>
                    <td class="rowlabel1"> Tarikh Bidaan: </td>

                </tr>
                <tr>
                    <td class="rowlabel1"> Tarikh Tamat Bayaran Baki: </td>

                </tr>
               </table>
            </div>
       </div>
        </fieldset>
    </div>
    <div class="content" align="right">
        <table >
            <tr>
                <td align="right">
                    <s:button name="" value="Cetak Surat Bayaran Baki" class="longbtn" />
                    <s:button name="" value="Simpan" class="btn" />
                    <%--<s:button name="" value="Hantar" class="btn" />--%>
                    <%--<s:button name="" value="Keluar" class="btn" /></td>--%>
            </tr>
        </table>
    </div><br>
</s:form>