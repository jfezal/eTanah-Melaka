<%-- 
    Document   : penyediaan_notis_surat
    Created on : Jan 20, 2010, 3:31:01 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<H4>PENYEDIAAN NOTIS/SURAT</H4>
<s:form action="">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pihak Berkepentingan
            </legend>
             <div class="subtitle">

            <fieldset class="aras2">
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
             </fieldset>
             </div>
            
         </fieldset>

        </div>
    <br>
    
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Penerima Surat/Notis
            </legend>
             <div class="subtitle">

            <fieldset class="aras2">
                    Sila Klik Pada Butang Hapus Butang Untuk Mengosongkan MaklumatPihak Berkepentingan
                    <br>
                    Sila Klik Pada Butang Tambah Untuk Menambah Maklumat Pihak Berkepentingan
                    
               <div class="content"  align="right">
                    <table>
                        <tr>
                            <td align="left">
                                <s:submit name="" value="Hapus" class="btn"/>
                                <s:submit name="" value="Tambah" class="btn"/>
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
                   <display:column title="Alamat" />
                   <display:column title="Cara Serahan" />
                </display:table>
             </div>
               
            </fieldset>
             </div>

         </fieldset>

        </div>
     <div class="content" align="right">
           <table >
                 <tr>
                     <td align="right">
                     <s:button name="Simpan" value="Simpan" class="btn" />
                     <s:button name="Cetak Notis/Surat" value="Cetak Notis/Surat" class="btn" />
                     <s:button name="Kembali" value="Kembali" class="btn" /></td>
                 </tr>
            </table>
      </div>
</s:form>