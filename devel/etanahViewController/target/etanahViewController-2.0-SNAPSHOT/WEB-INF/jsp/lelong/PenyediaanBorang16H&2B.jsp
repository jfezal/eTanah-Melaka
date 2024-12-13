<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252" language="java"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>PenyediaanBorang16H&2B</title>
  </head>
  <body>
    <stripes:form action="/Test.action">
        
        <!--<jsp:include page="/hasil/common/incMaklumatPermohonan.jsp"/>
       
     --><fieldset>
            <legend>Maklumat Pemohon</legend>
            <br>
           
                <table border="1" width="95%" align="center">
           
                    <tr>
                        
                        <th>Bil        </th>
                        <th>Nama              </th>
                        <th>Jenis Pengenalan  </th>
                        <th>Nombor</th>
                        <th>Alamat</th>
                        <th>Nombor Telefon Rumah</th>
                        <th>Nombor Telefon Pejabat</th>
                        <th>Smbg</th>
                        <th>Nombor Telefon Bimbit</th>
                        <th>Email</th>
                        
                    </tr>
                    <tr>
                       
                        <td>1   </td>
                        <td>SOUTHERN BANK BERHAD         </td>
                        <td>Banadar Tanjong Pinang Sek. 1</td>
                        <td>&nbsp;              </td>
                        <td>&nbsp;              </td>
                        <td>&nbsp;              </td>
                        <td>&nbsp;              </td>
                        <td>&nbsp;              </td>
                        <td>&nbsp;              </td>
                        
                       
                    </tr>
                    
                </table>
        </fieldset>
      <fieldset>
            <legend>Maklumat Tanah/Pajak/Pajakan Kecil</legend>
            <br>
            <tr>
                     <td width="25%" align="center">Daerah</td>
                        <td width="1%" align="center">:</td>
                       <td><stripes:text name="Barat Daya"/></td>
                       </tr>
                <table border="1" width="95%" align="center" datapagesize="2" >
                
                    <tr>
                       
                        <th>Bil.                    </th>
                        <th>Bandar/Pekan/Mukim      </th>
                        <th>Nombor Lot              </th>
                        <th>Hakmilik               </th>
                        <th>Keluasan                </th>
                        <th>Jenis Penggunaan Tanah  </th>
                        <th>Kod Syarat Nyata        </th>
                        <th>Kod Sekatan Kepentingan </th>
                    </tr>
                    <tr>
                        <td><stripes:checkbox name="chx1"/></td>
                        <td>Tiada Data          </td>
                        <td>&nbsp;              </td>
                        <td>&nbsp;              </td>
                        <td>&nbsp;              </td>
                        <td>&nbsp;              </td>
                        <td>&nbsp;              </td>
                        <td>&nbsp;              </td>
                        <td>&nbsp;              </td>
                        <td>&nbsp;              </td>
                    </tr>
                </table>
              
            </fieldset>
        
       <fieldset>
            <legend>Maklumat Perihal Tanah</legend>
            <br>
            
                <table border="1" width="95%" align="center">
               
               
                   <tr>
                        <td width="25%" align="right">Perihal Tanah</td>
                        <td width="1%" align="center">:</td>
                        <td><stripes:text name="6000.00"/></td>
                    </tr> 
                    
                </table>
        </fieldset>
     <fieldset>
            <legend>Penggunaan Wang Belian (Seksyen 268)</legend>
             <br>
             
            &nbsp;&nbsp;&nbsp; 
            Mengikut Seksyen 268, Wang Belian Yang Diperoleh Dalam Apa-apa Jualan Mestilah Digunakan Seperti Berikut:
                <table border="0" width="100%">
                
                       <tr>
                        <td width="25%" align="right">(a.i)Bayaran Kepada Pihak Berkuasa Negeri(Cukai Tanah) (RM)</td>
                        <td width="1%" align="center">:</td>
                        <td><stripes:text name="100.00"/></td>
                    </tr>
                </tr>
                       <tr>
                        <td width="25%" align="right">(a.ii)Bayaran Kepada Pihak Berkuasa Tempatan(Cukai Pintu) (RM)</td>
                        <td width="1%" align="center">:</td>
                        <td><stripes:text name="100.00"/></td>
                    </tr>
                           
                       </tr>
                       <tr>
                        <td width="25%" align="right">(a.iii)Bayaran Kepada Pemberi Pajak Untuk Lelongan Pajakan (RM)</td>
                        <td width="1%" align="center">:</td>
                        <td><stripes:text name="100.00"/></td>
                    </tr>
                      </tr>
                       <tr>
                        <td width="25%" align="right">(b)Perbelanjaan Pengurusan Perintah Jualan (RM)</td>
                        <td width="1%" align="center">:</td>
                        <td><stripes:text name="100.00"/></td>
                    </tr>
                      
                       <tr>
                        <td width="25%" align="right">(c)Bayaran Kepada Pemegang Gadai (RM)</td>
                        <td width="1%" align="center">:</td>
                        <td><stripes:text name="100.00"/></td>
                    </tr>
                   </tr>
                       <tr>
                        <td width="25%" align="right">(d)Pembayaran Berkala Atau Anuiti (RM)</td>
                        <td width="1%" align="center">:</td>
                        <td><stripes:text name="100.00"/></td>
                    </tr>
                        </tr>
                       <tr>
                        <td width="25%" align="right">(e)Pembayaran Berkala (RM)</td>
                        <td width="1%" align="center">:</td>
                        <td><stripes:text name="100.00"/></td>
                    </tr>
                      
                       
                </table>
        </fieldset>
        
        
        
        <fieldset>
            <legend>Senarai Maklumat Pemegang Gadai</legend>
            <br>
                  <table border="1" width="95%" align="center">
                    <tr>
                        <th>Bil               </th>
                        <th>Nama              </th>
                        <th>Amaun (RM)              </th>                        
                    </tr>
                    <tr>
                        <td>1    </td>
                        <td>SOUTHERN BANK    </td>
                        <td>500.00          </td>
                       
                    </tr> 
                </table>
        </fieldset>
        
       
         <fieldset>
            <legend>Arahan Ulasan Jurulelong</legend>
                <table border="0" width="100%">
                    <tr>
                        <td width="25%" align="right">Diuruskan Oleh</td>
                        <td width="1%" align="center">:</td>
                        <td align="center" width="5%"><stripes:radio name="a1" value="Ya"/>Jurulelong Berlesen</td>
                        <td align="left"><stripes:radio name="a1" value="Tidak"/>Pentadbir Tanah</td>
                         <td align="left"><stripes:radio name="a1" value="Tidak"/>PTD Lantik Jurulelong</td>
                    </tr>
                    <tr >
                        <td width="25%" align="right">Ulasan</td>
                        <td width="1%" align="center">:</td>
                        <td><stripes:textarea name="" cols="70" rows="5"/></td>
                    </tr>
                </table>
        </fieldset>
       
        <fieldset>
            <table border="0" align="right">
                <tr>
                    <td>
                        <stripes:submit name="terus" value="Cetak Surat Dan Borang 2B"/>
                         <stripes:submit name="terus" value="Cetak Surat Dan Borang 16H"/>
                        <stripes:submit name="terus" value="Cetak Surat Cadangan"/>
                        <stripes:submit name="terus" value="Cetak Surat Bayaran"/>
                        <stripes:submit name="terus" value="Simpan"/>
                        <stripes:submit name="terus" value="Hantar"/>
                        <stripes:submit name="keluar" value="Keluar"/>
                    </td>
                </tr>
            </table>
        </fieldset>
    </stripes:form>
  </body>
</html>