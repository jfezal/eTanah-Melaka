<%-- 
    Document   : rekod_enkuiri
    Created on : Jan 22, 2010, 2:49:54 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%--<H4>REKOD KEPUTUSAN ENKUIRI</H4>--%>
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
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Penggadai
            </legend>
            <div class="content"  align="center">
                <display:table class="tablecloth" name="" cellpadding="0" cellspacing="0" id="lineMP">
                    <display:column title="Bil" sortable="true">{lineMP_rowNum}</display:column>
                    <display:column title="Nama"/>
                    <display:column title="Jenis Pengenalan" />
                    <display:column title="No Pengenalan" />
                    <display:column title="ID Hakmilik" />
                    <display:column title="Jenis Pihak Berkepentingan"/>
                </display:table>
            </div>
        </fieldset>
    </div>
    <br>

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
               Maklumat Borang 16D Notis Keingkaran Mengenai Suatu Gadaian
            </legend>
            <div class="content" align="center">
                <table>
                  <tr>
                      <td class="rowlabel1">Sebab-sebab Berlaku Perlanggaran :</td>
                        <td>
                               <s:textarea name="" rows="5" cols="50" />
                             </td>
                    </tr>
                  <tr>
                        <td class="rowlabel1"> Tempoh Pelanggaran Peruntukan :</td>
                           <td>
                               <s:text name=""/> Bulan
                             </td>
                     </tr>
                     <tr>
                      <td class="rowlabel1">Tempoh Remedi :</td>
                        <td>
                               <s:text name="" /> Bulan
                             </td>
                    </tr>
                  <tr>
                        <td class="rowlabel1"> Tarikh Penyediaan Notis :</td>
                            <td class="input1">
                            <s:text name="tkhMula" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="tkhMula"/>
                        </td>
                     </tr>
                      <tr>
                        <td class="rowlabel1"> </td>
                        <td class="input1"><font color="green" size="2">CTH : 31/01/2007</font>
                        </td>
                     </tr>
                </table>
            </div>
                     </fieldset>
    </div>
    <br>
   <div class="subtitle">
        <fieldset class="aras1">
            <legend>
               Maklumat Perihal Tanah
            </legend>
            <div class="content" align="center">
                <table>
                  <tr>
                      <td class="rowlabel1">Perihal Tanah :</td>
                        <td>
                               <s:textarea name="" rows="10" cols="50" />
                             </td>
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
            </div>
            <div class="content" align="center">
                <display:table class="tablecloth" name="" cellpadding="0" cellspacing="0" id="lineMP">
                   <display:column title="Bil"/>
                    <display:column title="Tempat Perintah Jualan" />
                    <display:column title="Tarikh Perintah Jualan" />
                    <display:column title="Masa Perintah Jualan" />
                    <display:column title="Ulasan" />
                </display:table>
            </div>
        </fieldset>
    </div>
       <br>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Kehadiran
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
                    <display:column title="Nama" />
                    <display:column title="No Pengenalan" />
                    <display:column title="Jenis Pihak Berkepentingan" />
                    <display:column title="Kehadiran"/>
                                     </display:table>
            </div>
       </fieldset>
    </div>
   <br>
   <div class="subtitle">
        <fieldset class="aras1">
            <legend>
               Maklumat Permohonan Enkuiri
            </legend>
            <div class="content" align="center">
                <table>
                  <tr>
                      <td class="rowlabel1">Kes atau Isu :</td>
                        <td> </td>
                    </tr>
                  <tr>
                        <td class="rowlabel1"> Seksyen KTN :</td>
                           <td> </td>
                     </tr>
                     <tr>
                      <td class="rowlabel1">Perihal Seksyen :</td>
                        <td> </td>
                    </tr>

                </table>
            </div>
        </fieldset>
    </div>
   <br>
   <div class="subtitle">
        <fieldset class="aras1">
            <legend>
               Maklumat Laporan Siasatan
            </legend>
            <div class="content" align="center">
                <table>
                  <tr>
                      <td class="rowlabel1">Laporan Siasatan :</td>
                        <td>
                               <s:textarea name="" rows="10" cols="50" />
                        </td>
                  </tr>
                  <tr>
                       <td class="rowlabel1">Ulasan :</td>
                        <td>
                               <s:textarea name="" rows="5" cols="30" />
                        </td>
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
    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Amaun Kena Bayar (Jumlah Keseluruhan Tertunggak)
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
               Maklumat Perintah Jualan
            </legend>
            <div class="content" align="center">
    <div class="subtitle">
        <div class="content" align="center">
               <table>
                <tr>
                   <td class="rowlabel1">Jenis Perintah Jualan: </td>
                    <td class="input1">
                    <s:select name="" lang="4"></s:select></td>
                </tr>
                <tr>
                    <td class="rowlabel1">Tarikh Lelongan Awam:</td>
                    <td class="input1"><s:text name="" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id=""/>
                        </td>
                </tr>
                <tr>
                        <td class="rowlabel1"> </td>
                        <td class="input1"><font color="green" size="2">CTH : 31/01/2007</font>
                        </td>
                     </tr>
                <tr>
                    <td class="rowlabel1">Masa Lelongan Awam: </td>
                    <td class="input1"><s:text name="" size="2"/> :
                    <s:text name="" size="2" /> :
                    <s:select name="" lang="4">
                        <s:option >A.M</s:option>
                                 <s:option>P.M</s:option>
                            </s:select></td>
                </tr>
                <tr>
                        <td class="rowlabel1"> </td>
                        <td class="input1"><font color="green" size="2">CTH :09:00:A.M</font>
                        </td>
                </tr>
                <tr>
                    <td class="rowlabel1"> Tempat Lelongan Awam Diadakan: </td>
                    <td><s:text name=""/></td>
                </tr>
                <tr>
                        <td class="rowlabel1"> Perkara :</td>
                        <td><s:textarea name="" cols="50" rows="5" /></td>
                </tr>
                <tr>
                        <td class="rowlabel1">Harga Rizab (RM): </td>
                            <td>
                               <s:text name="" />
                         </td>
                </tr>
                <tr>
                        <td class="rowlabel1">Dalam Perkataan:</td>
                        <td><s:textarea name="" cols="50" rows="5" /></td>
                        <td>Ringgit</td>
                </tr>
                 <tr>
                        <td class="rowlabel1">Bayaran Deposit Harga Rizab (RM): </td>
                            <td>
                               <s:text name="" /> 10% daripada harga rizab
                         </td>
                </tr>
               </table>
            </div>
       </div>
   </div>
        </fieldset>
    </div><br>

<div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Dokumen
            </legend>
            <div class="content"  align="center">
                <display:table class="tablecloth" name="" cellpadding="0" cellspacing="0" id="lineMP">
                    <display:column title="Bil" sortable="true"></display:column>
                    <display:column title="Dokumen"/>
                    <display:column title="Catatan" />
                    <display:column title="ID Dokumen" />
                </display:table>
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
                    <td class="rowlabel1">Keputusan :</td>
                    <td class="input1">
                      <%--  <s:radio name="radio_" id="radio_" value=""/> Terima Permohonan--%>
                        <s:radio name="radio_" id="radio_" value=""/> Tolak Permohonan
                        <s:radio name="radio_" id="radio_" value=""/> Tangguh Siasatan
                        <s:radio name="radio_" id="radio_" value=""/> Perintah Lelong
                    </td>
                </tr>
                </table>
                
            </div>
        </fieldset>
    </div>
    <div class="content" align="right">
        <table >
            <tr>
                <td align="right">
                    <s:button name="" value="Isi Semula" class="btn" />
                    <s:button name="" value="Simpan" class="btn" />
                    <%--<s:button name="" value="Hantar" class="btn" />--%>
                   </td>
            </tr>
        </table>
    </div><br>

</s:form>