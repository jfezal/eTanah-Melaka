
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

function tambahBaru(){
            window.open("${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikpengambilan?hakMilikPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
}

</script>

<s:form beanclass="etanah.view.stripes.pengambilan.aduan.TerimaAduanActionBean">
    
   <!-- <fieldset class="aras1">
                    <legend>
                        JADUAL BAYARAN CAJ DENDA LEWAT 8%
                    </legend>
        
                    <p>
                        <label>ID Permohonan :</label>
        <s:text id = "idmohon" name="report_p_id_mohon" onblur="this.value = this.value.toUpperCase();"/>&nbsp;
    </p>
    <br>
    <p align="center">
        <s:button name="" id="simpan" value="Cari" class="btn" onclick="doSubmit(this.form);"/>&nbsp;
        <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
    </p>


    testts suhaimi hmetro

</fieldset > -->
        
        <s:hidden id = "idPermohonan" name="idPermohonan" />
        
        <fieldset class="aras1">
            <legend>
                        Maklumat Pengadu
            </legend>
            
                <table >
              <tr> 
                
             </tr> 
              <tr> 
               <td style="padding: 10px;color:#0000FF">
        <table style="border-collapse: separate; 
            margin-left:10%; 
                      margin-right:10%; ">
        <tr>
            <td style ="border: 1px solid #FFFFFF;padding: 5px;background-color:#B0E0E6;border-spacing:10px;;">No Pengenalan :</td>
            <td style ="border: 1px solid #FFFFFF;padding: 5px;background-color:#B0E0E6;border-spacing:10px;"></select> <select style="background-color:#B0E0E6" name="pilih" id="pilih">
            <option value="volvo">NO SYARIKAT</option>
            <option value="saab">MAXIS</option>
            <option value="opel">DIGI</option>
            <option value="audi">UMOBILE</option>
          </select>: <s:text id="noKp" name="noKp"  size="31" /> <span style="color:#FF0000">[780104069871]</span>
                    
        </td>
              </tr>  
          <tr>
                  <td style ="border: 1px solid #FFFFFF;padding: 5px;background-color:#B0E0E6;border-spacing:10px;;">Nama :</td>
                  <td style ="border: 1px solid #FFFFFF;padding: 5px;background-color:#B0E0E6;border-spacing:10px;"><s:text id="nama" name="nama"  size="31" /></td>
              </tr>  
          <tr>
                  <td style ="border: 1px solid #FFFFFF;padding: 5px;background-color:#B0E0E6;border-spacing:10px;;">Alamat :</td>
                  <td style ="border: 1px solid #FFFFFF;padding: 5px;background-color:#B0E0E6;border-spacing:10px;"><s:text id="alamat1" name="alamat1"  size="31" /> </td>
              </tr>  
          <tr>
                  <td style ="border: 1px solid #FFFFFF;padding: 5px;background-color:#B0E0E6;border-spacing:10px;;"></td>

                  <td style ="border: 1px solid #FFFFFF;padding: 5px;background-color:#B0E0E6;border-spacing:10px;"><s:text id="alamat2" name="alamat2"  size="31" /></td>
              </tr>  
          <tr>
                  <td style ="border: 1px solid #FFFFFF;padding: 5px;background-color:#B0E0E6;border-spacing:10px;;"></td>

                  <td style ="border: 1px solid #FFFFFF;padding: 5px;background-color:#B0E0E6;border-spacing:10px;"><s:text id="alamat3" name="alamat3"  size="31" /></td>
              </tr>  
          <tr>
                  <td style ="border: 1px solid #FFFFFF;padding: 5px;background-color:#B0E0E6;border-spacing:10px;;"></td>

                  <td style ="border: 1px solid #FFFFFF;padding: 5px;background-color:#B0E0E6;border-spacing:10px;"><s:text id="alamat4" name="alamat4"  size="31" /></td>
              </tr>  
          <tr>
                  <td style ="border: 1px solid #FFFFFF;padding: 5px;background-color:#B0E0E6;border-spacing:10px;;">Poskod :</td>

                  <td style ="border: 1px solid #FFFFFF;padding: 5px;background-color:#B0E0E6;border-spacing:10px;"><s:text id="poskod" name="poskod"  size="31" /> </td>
              </tr>  
          <tr >
                  <td style ="border: 1px solid #FFFFFF;padding: 5px;background-color:#B0E0E6;border-spacing:10px;;">Negeri:</td>
                  <td style ="border: 1px solid #FFFFFF;padding: 5px;background-color:#B0E0E6;border-spacing:10px;"></select> <select style="background-color:#B0E0E6" name="kodNegeri" id="kodNegeri">
            <option value="volvo">PILIH</option>
            <option value="saab">MELAKA</option>
            <option value="opel">SELANGOR</option>
            <option value="audi">PERAK</option>
          </select>
        </td>
              </tr>  
          <tr>
                  <td style ="border: 1px solid #FFFFFF;padding: 5px;background-color:#B0E0E6;border-spacing:10px;;">No Telefon :</td>
                  <td style ="border: 1px solid #FFFFFF;padding: 5px;background-color:#B0E0E6;border-spacing:10px;"><s:text id="noTel" name="noTel"  size="31" /> </td>
              </tr>  
          <tr>
                  <td style ="border: 1px solid #FFFFFF;padding: 5px;background-color:#B0E0E6;border-spacing:10px;;">Emel :</td>

                  <td style ="border: 1px solid #FFFFFF;padding: 5px;background-color:#B0E0E6;border-spacing:10px;"><s:text id="email" name="email"  size="31" /></td>
              </tr>
            <tr>
                  <td style ="border: 1px solid #FFFFFF;padding: 5px;background-color:#B0E0E6;border-spacing:10px;;">Hubungan :</td>

                  <td style ="border: 1px solid #FFFFFF;padding: 5px;background-color:#B0E0E6;border-spacing:10px;"><s:text id="hubungan" name="hubungan"  size="31" /></td>
              </tr>  
        </table>
                </td></tr></table>
    

</fieldset>

<p></p>


<fieldset class="aras1">
            <legend>
                        Maklumat Tanah Pengadu
            </legend>
    
    <table >
      <tr> 
        <th style="padding:5px;color:#0000FF;"></th>
     </tr> 
  <td style="text-align:center;font-size: 16px;color:#0000FF;font-weight:bold;">1 rekod,Skrin:1
      <tr> 
       <td style="padding: 5px;color:#0000FF">
<table style="border-collapse: separate;padding: 5px%;width:100%; ">
<tr>
          <th style =" border: 1px solid #FFFFFF;background-color:#4682B4;color:#FFFFFF;text-align:center">No</th>
          <th style =" border: 1px solid #FFFFFF;background-color:#4682B4;color:#FFFFFF;text-align:center">ID Hakmilik</th> 
          <th style =" border: 1px solid #FFFFFF;background-color:#4682B4;color:#FFFFFF;text-align:center">No Lot/No PT</th>
          <th style =" border: 1px solid #FFFFFF;background-color:#4682B4;color:#FFFFFF;text-align:center">Luas</th>
          <th style =" border: 1px solid #FFFFFF;background-color:#4682B4;color:#FFFFFF;text-align:center">Daerah</th>
          <th style =" border: 1px solid #FFFFFF;background-color:#4682B4;color:#FFFFFF;text-align:center">Bandar/Pekan/Mukim</th>
          <th style =" border: 1px solid #FFFFFF;background-color:#4682B4;color:#FFFFFF;text-align:center">Luas Diambil</th>
          <th style =" border: 1px solid #FFFFFF;background-color:#4682B4;color:#FFFFFF;text-align:center">Baki Luas</th>
          <th style =" border: 1px solid #FFFFFF;background-color:#4682B4;color:#FFFFFF;text-align:center">Kegunaan Tanah</th>
      </tr>
<tr>
          <td style ="border: 1px solid #FFFFFF;background-color:#B0E0E6;border-spacing:10px;;">1</td>
          <td style ="border: 1px solid #FFFFFF;background-color:#B0E0E6;border-spacing:10px;">040141GRN00002399</td>
          <td style ="border: 1px solid #FFFFFF;background-color:#B0E0E6;border-spacing:10px;">Lot0000845</td>
          <td style ="border: 1px solid #FFFFFF;background-color:#B0E0E6;border-spacing:10px;">227.7908 mp</td>
          <td style ="border: 1px solid #FFFFFF;background-color:#B0E0E6;border-spacing:10px;">Melaka Tengah</td>
          <td style ="border: 1px solid #FFFFFF;background-color:#B0E0E6;border-spacing:10px;">Bandar Melaka</td>
          <td style ="border: 1px solid #FFFFFF;background-color:#B0E0E6;border-spacing:10px;"><input style="background-color:#B0E0E6" type="text" id="fname" name="fname">mpp</td>
          <td style ="border: 1px solid #FFFFFF;background-color:#B0E0E6;border-spacing:10px;">157.7908</td>
          <td style ="border: 1px solid #FFFFFF;background-color:#B0E0E6;border-spacing:10px;">RUMAH KEDIAMAN</td>
      </tr> 
   <tr> 
       <td style="padding: 10px;color:#0000FF">
        </td></tr></table>
       <table >
      <tr> <td style="text-align:center;font-size: 14px;padding: 5px;">
        <button type="button" style="background-color:#FFD700; display: inline-block; padding: 5px 25px;font-size: 14px;cursor: pointer;text-align: center;text-decoration: none; outline: none;color:#000000;border: none;border-radius: 15px; margin-left:auto;margin-right:auto;display:block;">Tambah</button>
        </td> 
      </tr> 
</table>
        </td></tr></table>
</fieldset>


<p></p>
  

<fieldset class="aras1">
            <legend>
                        Maklumat Aduan
            </legend>
    
    <table style="width: 200%; background-color:#E0E0E0;">
      
      <tr> 
       <td style="padding: 10px;color:#0000FF">
<table >
<tr>
          <td style ="border: 1px solid #FFFFFF;padding: 5px;background-color:#B0E0E6;border-spacing:10px;">Perihal Aduan :</td>
          <td style ="border: 1px solid #FFFFFF;padding: 5px;background-color:#B0E0E6;border-spacing:10px;">
              <s:textarea id="aduan" name="aduan"  rows="10" cols="50"/> 
              
             </td>
      </tr>    
</table>
        </td></tr></table>





    
</fieldset>



    <p align="center">
            <s:submit name="simpan" id="kemas" value="simpan" class="btn"/>&nbsp;
            <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
     </p>
</s:form>
