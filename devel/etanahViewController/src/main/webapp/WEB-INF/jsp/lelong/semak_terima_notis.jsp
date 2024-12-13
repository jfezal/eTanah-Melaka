<%-- 
    Document   : semak_terima_notis
    Created on : Jan 21, 2010, 12:03:06 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<H4>SEMAK PENERIMAAN NOTIS</H4>
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
                    <display:column title="Bil" sortable="true"></display:column>
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
                Maklumat Penerima Notis
            </legend>
            <div class="content"  align="center">
                <display:table class="tablecloth" name="" cellpadding="0" cellspacing="0" id="lineMP">
                    <display:column title="Bil" sortable="true">{lineMP_rowNum}</display:column>
                    <display:column title="Nama"/>
                    <display:column title="Alamat" />
                    <display:column title="Status Serahan" />
                    <display:column title="Cara Serahan" />
                    <display:column title="Perihal Notis" />
                    <display:column title="Tarikh Serahan" />
                    <display:column title="Ulasan" />
                    <display:column title="Notis Gantian" />
                    <display:column title="ID Dokumen" />
                </display:table>
            </div>
        </fieldset>
    </div><br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
               Perakuan PPTD
            </legend>
            <div class="content" align="center">
               <table>
                <tr>
                    <td class="rowlabel1">Keputusan :</td>
                    <td class="input1">
                        <s:radio name="radio_" id="radio_" value=""/> Diperakukan  
                        <s:radio name="radio_" id="radio_" value=""/> Semak Semula
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
                    <s:button name="" value="Isi Semula" class="btn" />
                    <s:button name="" value="Simpan" class="btn" />
                    <s:button name="" value="Hantar" class="btn" />
                    <s:button name="" value="Keluar" class="btn" /></td>
            </tr>
        </table>
    </div><br>
</s:form>