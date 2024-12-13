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
        
        <!--<jsp:include page="/hasil/common/incMaklumatPermohonan.jsp"/>
        --><fieldset>
            <legend>Carian</legend>
                <table border="0" width="100%">
                   
                     <tr>
                        <td width="25%" align="right">ID Hakmilik</td>
                        <td width="1%" align="center">:</td>
                        <td><s:text name=""/></td>
                         <td>
                        <s:submit name="keluar" value="Cari"/>
                        <s:submit name="keluar" value="Isi Semula"/>
                    </td>
                    </tr>
                </table>
        </fieldset>
        
      <fieldset>
      <legend>Keputusan Carian</legend>
      
      <fieldset>
            <legend>Senarai Maklumat Pemohon</legend>
            <br>
            &nbsp;&nbsp;&nbsp; 
            Sila Klik Pada Butang Pilih Untuk Memasukkan Maklumat Pemohon.
                <table border="1" width="95%" align="center">
               
                <s:submit name="terus" value="Pilih" />
                    <tr>
                        <th>Pilih                </th>
                        <th>Bil        </th>
                        <th>Nama              </th>
                        <th>No pengenalan  </th>
                        <th>ID Hakmilik             </th>
                        <th>Jenis Pihak Berkepentingan  </th>
                        
                    </tr>
                    <tr>
                       <td><s:checkbox name="chx1"/></td>
                        <td>070522GM00002094    </td>
                        <td>Barat Daya          </td>
                        <td>Banadar Tanjong Pinang Sek. 1</td>
                        <td>&nbsp;              </td>
                        <td>Lot 0000089         </td>
                       
                    </tr>
                    
                </table>
        </fieldset>
        <fieldset>
            <legend>Senarai Maklumat Tanah</legend>
            <br>
             
            &nbsp;&nbsp;&nbsp; 
            Sila Klik Pada Butang Pilih Untuk Memasukkan Maklumat Tanah.
                <table border="1" width="95%" align="center" datapagesize="2" >
               
                    <tr>
                        <th>Pilih               </th>
                        <th>Bil.                </th>
                        <th>ID Hakmililk                </th>
                        <th>Bandar/Pekan/Mukim    </th>
                        <th>Hakmilik   </th>
                        <th>Nombor Lot             </th>
                        <th>Keluasan    </th>
                        <th>Jenis Penggunaan Tanah  </th>
                        <th>Kod Syarat Nyata             </th>
                        <th>Kod Sekatan Kepentingan             </th>
                    </tr>
                    <tr>
                        <td><s:checkbox name="chx1"/></td>
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
                <br>
        </fieldset>
      </fieldset>
      
        
         <fieldset>
            <legend>Maklumat Pemohon</legend>
            <br>
            &nbsp;&nbsp;&nbsp; 
            Sila Klik Pada Butang Hapus Untuk Mengosongkan Maklumat Pemohon.
                <table border="1" width="95%" align="center">
               
                <s:submit name="terus" value="Hapus" />
                    <tr>
                        <th>Pilih                </th>
                        <th>Bil        </th>
                        <th>Nama              </th>
                        <th>Jenis Pengenalan  </th>
                        <th>No Pengenalan/No Syarikat             </th>
                        
                        
                    </tr>
                    <tr>
                       <td><s:checkbox name="chx1"/></td>
                        <td>070522GM00002094    </td>
                        <td>Barat Daya          </td>
                        <td>Banadar Tanjong Pinang Sek. 1</td>
                        <td>&nbsp;              </td>
                        
                       
                    </tr>
                    
                </table>
        </fieldset>
        <fieldset>
            <legend> Maklumat Tanah</legend>
            <br>
             
            &nbsp;&nbsp;&nbsp; 
          Sila Klik Pada Butang Hapus Untuk Mengosongkan Maklumat Tanah.
                <table border="1" width="95%" align="center" datapagesize="2" >
                  <s:submit name="terus" value="Hapus" />
                    <tr>
                        <th>Pilih               </th>
                        <th>Bil.                </th>
                        <th>ID Hakmililk                </th>
                        <th>Bandar/Pekan/Mukim    </th>
                        <th>Hakmilik   </th>
                        <th>Nombor Lot             </th>
                        <th>Keluasan    </th>
                        <th>Jenis Penggunaan Tanah  </th>
                        
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
                       
                    </tr>
                </table>
                <br>
        </fieldset>
        <fieldset>
            <legend>Maklumat Borang 16D</legend>
                <table border="0" width="100%">
                  <tr >
                        <td width="25%" align="right">Sebab-sebab Berlaku Pelanggaran</td>
                        <td width="1%" align="center">:</td>
                        <td><s:textarea name="" cols="70" rows="5"/></td>
                    </tr>
                       <tr>
                        <td width="25%" align="right">Tempoh Perlanggaran Pruntukan (Sekurang kurangnya)</td>
                        <td width="1%" align="center">:</td>
                        <td><s:text name=""/>Bulan</td>
                    </tr>
                       <tr>
                        <td width="25%" align="right">Tempoh Remedi</td>
                        <td width="1%" align="center">:</td>
                        <td><s:text name=""/>Bulan</td>
                    </tr>
                       <tr>
                        <td width="25%" align="right">Tempoh Penyediaan Notis</td>
                        <td width="1%" align="center">:</td>
                        <td><s:text name=""/></td>
                    </tr>
                   
                </table>
        </fieldset>
        <fieldset>
            <legend>Maklumat Borang 16G</legend>
                <table border="0" width="100%">
                <tr>
                        <td width="25%" align="right">Jenis Gadaian</td>
                        <td width="1%" align="center">:</td>
                        <td>
                            <s:select name="">
                                <s:option value=" "> </s:option>
                                <s:option value=" "> </s:option>
                            </s:select>
                        </td>
                        </tr>
                           
                       <tr>
                        <td width="25%" align="right">Tempoh Notis</td>
                        <td width="1%" align="center">:</td>
                        <td><s:text name=""/>Bulan</td>
                    </tr>
                    
                       <tr>
                        <td width="25%" align="right">Tarikh Penyediaan Borang</td>
                        <td width="1%" align="center">:</td>
                        <td><s:text name=""/></td>
                    </tr>
               
                </table>
        </fieldset>
        <fieldset>
            <legend>Maklumat Perihal Tanah</legend>
                <table align="center" border="0" width="95%">
                   
                    <tr><td width="25%" align="right">Perihal Tanah</td>
                    <td width="1%" align="center">:</td>
                        <td><s:textarea name="" cols="80" rows="5"/></td>
                    </tr>
                </table>
                <br>
        </fieldset>
        
      
        
      
         <fieldset>
            <legend>Senarai Semakan Tambahan</legend>
            <br>
            
                <table border="1" width="95%" align="center">
               
                <s:submit name="terus" value="Hapus" />
                    <tr>
                        <th>Pilih                </th>
                        <th>Dokumen              </th>
                        <th>Catatan              </th>                        
                    </tr>
                    <tr>
                       <td><s:checkbox name="chx1"/></td>
                        <td>070522GM00002094    </td>
                        <td>Barat Daya          </td>
                       
                    </tr>
                    
                </table>
        </fieldset>
        <fieldset>
            <legend>Tambah Dokumen</legend>
                <table border="0" width="100%">
               
                       <tr>
                        <td width="25%" align="right">Dokumen</td>
                        <td width="1%" align="center">:</td>
                        <td><s:text name=""/></td>
                    </tr>
                    
                       <tr>
                        <td width="25%" align="right">Catatan</td>
                        <td width="1%" align="center">:</td>
                        <td><s:text name=""/></td>
                        <td> <s:submit name="terus" value="Tambah" /></td>
                    </tr>
               
                </table>
        </fieldset>
          <fieldset>
            <legend>Tajuk Permohonan</legend>
                <table align="center" border="0" width="95%">
                    <s:button name="" value="Jana Tajuk"/>
                    <tr><td width="25%" align="right">Tajuk</td>
                    <td width="1%" align="center">:</td>
                        <td><s:textarea name="" cols="80" rows="5"/></td>
                    </tr>
                </table>
                <br>
        </fieldset>
        <fieldset>
            <table border="0" align="right">
                <tr>
                    <td>
                        
                        <s:reset name="" value="Isi Semula"/>
                        <s:submit name="terus" value="Terus"/>
                        <s:submit name="keluar" value="Keluar"/>
                    </td>
                </tr>
            </table>
        </fieldset>
    </s:form>
  </body>
</html>