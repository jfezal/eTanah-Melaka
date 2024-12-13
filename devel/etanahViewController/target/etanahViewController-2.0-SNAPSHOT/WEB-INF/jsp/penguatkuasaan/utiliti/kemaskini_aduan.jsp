<%-- 
    Document   : kemaskini_aduan
    Created on : Apr 11, 2014, 9:12:09 AM
    Author     : Tengku.Fauzan
--%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--%@ page import="etanah.model.Pengguna"%--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>

<script type="text/javascript">
 
 function validateForm(){

        if(document.form1.cara.value=="")
        {
            alert("Sila Pilih Cara Aduan");
            $("#cara").focus();
            return false;
        }
        if(document.form1.sebab.value=="")
        {
            alert("Sila isi Ringkasan Aduan");
            $("#sebab").focus();
            return false;
        }
        if(document.form1.kodUrusan.value=="")
        {
            alert("Sila pilih Peruntukan Seksyen");
            $("#kodUrusan").focus();
            return false;
        }
        if(document.form1.penyerahNama.value=="")
        {
            alert("Sila isi Nama Pengadu");
            $("#penyerahNama").focus();
            return false;
        }

        if(document.form1.bpm.value=="" && document.form1.kodUrusan.value != "429" && $("#kodNegeriAduan").val() == "05")
        {
            alert("Sila Pilih Bandar/Pekan/Mukim");
            $("#bpm").focus();
            return false;
        }
        if(document.form1.message.value=="")
        {
            alert("Sila isi Lokasi");
            $("#message").focus();
            return false;
        }

        return true;
    }
    
       function validateTelLength(value){
        var plength = value.length;
        if(plength < 7){
            alert('No. Telefon yang dimasukkan salah.');
            $('#telefon').val("");
            $('#telefon').focus();
        }
    }
    
        function validatePoskodLength(value){
        var plength = value.length;
        if(plength != 5){
            alert('Poskod yang dimasukkan salah.');
            $('#poskod').val("");
            $('#poskod').focus();
        }
    }
    
        function validateKPLength(value){
        var plength = value.length;
        if(plength != 12){
            alert('Kad Pengenalan Baru yang dimasukkan salah.');
            $('#noPengenalanBaru').val("");
            $('#noPengenalanBaru').focus();
        }
    }
    
            function ValidateEmail(){
            var emailID= $("#email").val();

            if ((emailID==null)||(emailID=="")){
                return true;
            }
            if ((emailID!=null)||(emailID!="")){
                if(emailcheck(emailID)==false){
                    $("#email").val("");
                    $("#email").focus();
                    return false;
                }
            }
            return true;
        }
        
               function emailcheck(str) {

            var at="@";
            var dot=".";
            var lat=str.indexOf(at);
            var lstr=str.length;
            var ldot=str.indexOf(dot);
            if (str.indexOf(at)==-1){
                alert('"Alamat Email" salah');
                return false;
            }

            if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
                alert('"Alamat Email" salah');
                return false;
            }

            if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
                alert('"Alamat Email" salah');
                return false;
            }

            if (str.indexOf(at,(lat+1))!=-1){
                alert('"Alamat Email" salah');
                return false;
            }

            if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
                alert('"Alamat Email" salah');
                return false;
            }

            if (str.indexOf(dot,(lat+2))==-1){
                alert('"Alamat Email" salah');
                return false;
            }

            if (str.indexOf(" ")!=-1){
                alert('"Alamat Email" salah');
                return false;
            }

            return true;
        }
        
            function limitText(limitField, limitNum) {
            if (limitField.value.length > limitNum) {
                limitField.value = limitField.value.substring(0, limitNum);
            }
        }
        
</script>

<table width="100%" bgcolor="yellow">
    <tr>
        <td width="100%" height="20" >
            <div style="background-color: yellow;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">PENGUATKUASAAN : Kemaskini Aduan</font>
            </div>
        </td>
    </tr>
</table>
<br>
<s:form name="form1" id="form1" beanclass="etanah.view.penguatkuasaan.utiliti.UtilitiKemaskiniAduanActionBean">
     <s:errors/>
     <s:messages/>
    <div class="subtitle">

       <div id="aduanDiv">
        <fieldset class="aras1">
            <legend>Carian Aduan</legend>
            <font size="1" color="red">Sila masukkan ID Aduan untuk carian aduan</font>
            
             <p>
                <label><font color="red">*</font>ID Aduan :</label>
                <input type="text" name="idPermohonan" onkeyup="this.value=this.value.toUpperCase();"/>
                <s:submit name="trackAduan" value="Cari" class="btn"/>
            </p>
            <br>
            <s:hidden name="permohonan.idPermohonan"/>
              <p>
                      <label for="idPermohonan">ID Aduan:</label>
                        ${actionBean.permohonan.idPermohonan}
                  
             </p>
            
            
         </fieldset>
       </div>  
       <br>
        <fieldset class="aras1">
            <legend>Tempat Aduan</legend>
            <s:hidden name="kodNegeri" id="kodNegeriAduan"/>
            <div class="instr-fieldset">
                <font size="2" color="red">PERINGATAN:Sila Masukkan Maklumat Berikut</font>
            </div>&nbsp;
            
            <p>
                <label>Tarikh :</label>
                <s:text name="tarikhMasuk" id="tarikhMasuk"  style="width:120px;" readonly="readonly" />
                &nbsp;</p>
            <p>
                <label>Daerah :</label>
                ${actionBean.permohonan.cawangan.daerah.kod} -  ${actionBean.permohonan.cawangan.daerah.nama} &nbsp;
            </p>
            <p>
                <label>Cara Aduan :</label>
               <s:text name="caraPermohonan.nama" id="caraPermohonan"  style="width:120px;" readonly="readonly" />&nbsp;
            </p>
        
            <p>
                <label>Ringkasan Aduan :</label>
                <s:textarea name="sebab" id="sebab" rows="5" cols="50" onkeydown="limitText(this,2500);" onkeyup="limitText(this,2500);" readonly="readonly"/>
            </p>
      
                        <p>
                <label>Peruntukan Seksyen :</label>
                ${actionBean.permohonan.kodUrusan.nama}
               &nbsp;
                <s:hidden name="permohonan.idPermohonan"/>
            </p>

        </fieldset >                
        <br>
        <fieldset class="aras1">
            <legend>Maklumat Pengadu</legend>
            <p>
                <label>Nama :</label>
                <s:text name="penyerahNama" id="penyerahNama" size="42" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Jenis Pengenalan :</label>
                <s:select name="penyerahJenisPengenalan.kod" style="width:139px;" id="pengenalan">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" sort="nama" />
                </s:select>
                &nbsp;
            </p>
            <p id="noPengenalanLain">
                <label>No.Pengenalan :</label>
                <s:text name="penyerahNoPengenalanLain" id="penyerahNoPengenalanLain" maxlength="20" onkeyup="this.value=this.value.toUpperCase();" />
                <font color="red" size="1">Contoh : 850510075342 </font>
                &nbsp;
            </p>
            <%--p id="noPengenalanBaru" style="visibility: hidden; display: none">
                <label>No.Pengenalan :</label>
                <s:text name="penyerahNoPengenalanLain" id="penyerahNoPengenalanLain" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateKPLength(this.value);"/>
                <font color="red" size="1">cth : 850510075342 </font>
                &nbsp;
            </p--%>
            <p>
                <label>Alamat :</label>
                <s:text name="penyerahAlamat1" size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="penyerahAlamat2" size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>

            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="penyerahAlamat3" size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>

            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="penyerahAlamat4" size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>

                &nbsp;
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name="penyerahPoskod" id="poskod" size="19" maxlength="5" onkeyup="validateNumber(this,this.value);" onchange="validatePoskodLength(this.value);"/>

            </p>
            <p>
                <label>Negeri :</label>
                <s:select name="penyerahNegeri.kod"  style="width:139px;">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" sort="nama" />
                </s:select>
            </p>
            <p>
                <label>No.Telefon :</label>
                <s:text name="penyerahNoTelefon1" id="telefon" value="${actionBean.permohonan.penyerahNoTelefon1}" size="19" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);"/>

            </p>
            <p>
                <label>Email :</label>
                <s:text name="penyerahEmail" id="email" value="${actionBean.permohonan.penyerahEmail}" size="40" maxlength="100" onblur="return ValidateEmail()"/>

            </p>
        </fieldset>

           <div id="othersSeksyen">
            <fieldset class="aras1">
                <legend>Lokasi Kejadian</legend>
                <div class="instr-fieldset">
                    Sila masukkan maklumat lokasi.
                </div>
                <div id="lokasiKejadianDiv">
                <p id="lokasikejadian1">
                    <label><em>*</em>Bandar/Pekan/Mukim :</label>
                    <s:select name="bandarPekanMukim.kod" id="bpm">
                        <s:option value=""> Sila Pilih </s:option>
                        <c:forEach items="${actionBean.listBandarPekanMukim}" var="line">
                            <s:option value="${line.kod}">${line.bandarPekanMukim} - ${line.nama}</s:option>
                        </c:forEach>
                    </s:select>
                    &nbsp;
                </p>
                <p id="lokasikejadian2">
                    <label>Jenis Tanah :</label>
                    <s:select name="pemilikan.kod" id="milik">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodPemilikan}" label="nama" value="kod" sort="nama" />
                    </s:select>
                    &nbsp;
                </p>
                <p id="lokasikejadian3">
                    <label>Jenis Nombor:</label>
                    <s:select name="kodLot.kod" id="kodLot">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodLot}" label="nama" value="kod" sort="nama" />
                    </s:select>
                    <s:text name="noLot" onkeyup="validateNumber(this,this.value);"/> &nbsp;
                </p>
                </div>
                <p>
                    <label><em>*</em> Lokasi :</label>
                    <s:textarea name="lokasi" id="message" rows="5" cols="50" onkeydown="limitText(this,499);" onkeyup="limitText(this,499);" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                </p>
            </fieldset>
        </div>

        


        <p align="right">
            <s:submit class="btn" name="updateAduan" value="Simpan" onclick="return validateForm();"/>
        </p>
    </div>
        
        
        
        
    </div>
    

</s:form>