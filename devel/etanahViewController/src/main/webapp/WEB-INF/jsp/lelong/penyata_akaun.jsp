<%-- 
    Document   : penyata_akaun
    Created on : Feb 24, 2010, 10:34:41 AM
    Author     : mazurahayati
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:form action="">
  <%--  <div class="subtitle">
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
    </div
    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Permohonan
            </legend>
            <div class="content">
             <p>
                    <label> ID Permohonan :</label>&nbsp;
             </p>
             <p>
                    <label> Tarikh Permohonan :</label>&nbsp;
             </p>
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
    </div>
       <br>

       <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Penggadai
            </legend>
            <div class="content"  align="center">
                <display:table class="tablecloth" name="" cellpadding="0" cellspacing="0" id="lineMP">
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
                        <p>
                            <label> Daerah :</label>&nbsp;
                        </p>

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
                Senarai Maklumat Pemegang Gadaian
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
                Maklumat Perintah Jualan
            </legend>
            <div class="content"  align="center">
                <display:table class="tablecloth" name="" cellpadding="0" cellspacing="0" id="lineMP">
                    <display:column title="Bil" sortable="true"></display:column>
                    <display:column title="Tempat Perintah Jualan"/>
                    <display:column title="Tarikh Perintah Jualan" />
                    <display:column title="Masa Perintah Jualan"/>
                    <display:column title="Ulasan"/>
                </display:table>
            </div>
        </fieldset>
    </div><br>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
               Keputusan Bidaan
            </legend>
            <div class="content">
                <p>
                    <label>Keputusan :</label>&nbsp;
                        <s:radio name="radio_" id="radio_" value=""/> Berjaya
                        <s:radio name="radio_" id="radio_" value=""/> Tangguh
                </p>

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
                    <display:column title="Nama" />
                    <display:column title="Alamat" />
                    <display:column title="No Telefon Rumah" />
                    <display:column title="No Telefon Pejabat"/>
                    <display:column title="No Telefon Bimbit" />
                    </display:table>
            </div>
       </fieldset>
    </div><br>
--%>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Penyata Akaun
            </legend>
             <div class="content"  align="center">

                    <display:table class="tablecloth" name="" cellpadding="0" cellspacing="0" id="lineMP">
                    <display:column title="Bil" sortable="true">{lineMP_rowNum}</display:column>
                    <display:column title="Terima Daripada" />
                    <display:column title="Tarikh Terima" />
                    <display:column title="Tindakan" />
                    <display:column title="ID Dokumen"/>
                    </display:table>
            </div>
       </fieldset>
    </div><br>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
               Ulasan Ke Atas Maklumat Penyata Akaun
            </legend>
            <div class="content">
                  <p>
                      <label>Ulasan :</label>
                      <s:textarea name="" rows="10" cols="50" />&nbsp;
                  </p>
            </div>
       </fieldset>
   </div><br>

       <div class="content" align="right">
              <p>
                    <%--<s:button name="" value="Cetak Perjanjian Pembelian" class="longbtn" />--%>
                    <%--<s:button name="" value="Cetak Kontrak Lelongan" class="longbtn" />--%>
                    <s:button name="" value="Isi Semula" class="btn" />
                    <s:button name="" value="Simpan" class="btn" />
                    <%--<s:button name="" value="Hantar" class="btn" />--%>
                    <%--<s:button name="" value="Keluar" class="btn" /></td>--%>
            </p>

    </div><br>
</s:form>
