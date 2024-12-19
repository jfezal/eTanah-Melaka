<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252" language="java"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>Lelong</title>
  </head>
  <body>
   <s:form beanclass="etanah.view.stripes.hasil.Test1ActionBean">
        
    
        <fieldset>
            <legend>Dokumen Baru</legend>
               <br>
            &nbsp;&nbsp;&nbsp; 
            Perhatian: Sila Isikan Maklumat Pada Medan Bertanda * Dibawah Dan Klik Butang "Simpan" Bagi Penambahan Fail Baru <table border="0" width="100%">
                  <tr>
                        <td width="25%" align="right">Muatnaik Dokumen</td>
                        <td width="1%" align="left">:</td>
                         <td><s:text name=""/></td>
                           <td> <s:submit name="terus" value="Browse"/></td>
                    </tr>
                 <tr>
                    <tr>
                        <td width="25%" align="right">Tajuk Fail</td>
                        <td width="1%" align="center">:</td>
                         <td><s:text name="Perintah Jual Oleh Pentadbir Tanah - 74834789123H8"/></td>
                    </tr>
                    <tr>
                        <td width="25%" align="right">Dokumen</td>
                        <td width="1%" align="center">:</td>
                         <td><s:text name="Bukti Penyampaian Borang 16D atau 16E"/></td>
                    </tr>
                    <tr >
                        <td width="25%" align="right">Tajuk Dokumen</td>
                        <td width="1%" align="center">:</td>
                         <td><s:text name=""/></td>
                    </tr> 
                    <tr>
                        <td width="25%" align="right">Klasifikasi</td>
                        <td width="1%" align="center">:</td>
                        <td>
                            <s:select name="">
                                <s:option value=" "> </s:option>
                                <s:option value=" "> </s:option>
                            </s:select>
                        </td>
                    </tr>
                     
                 <tr >
                        <td width="25%" align="right">Catatan Minit</td>
                        <td width="1%" align="center">:</td>
                        <td><s:textarea name="" cols="40" rows="5"/></td>
                    </tr>
                  
                  <tr >
                        <td width="25%" align="right">Keterangan</td>
                        <td width="1%" align="center">:</td>
                          <td><s:textarea name="" cols="40" rows="5"/></td>
                    </tr>
                
                </table>
        </fieldset>
        <fieldset>
            <table border="0" align="right">
                <tr>
                    <td>
                        <s:submit name="terus" value="Simpan"/>
                        <s:reset name="terus" value="Isi Semula"/>
                        <s:submit name="terus" value="Keluar"/>
                     
                    </td>
                </tr>
            </table>
        </fieldset>
                   
    </s:form>
  </body>
</html>