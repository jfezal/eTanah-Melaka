<%-- 
    Document   : kutipan_data_kemaskiniPihak
    Created on : Oct 1, 2013, 1:08:16 PM
    Author     : ei
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquery.validate.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.2.custom.min.js"></script>

<script type="text/javascript">
  $(document).ready(function() {
    $(".datepicker").datepicker({dateFormat: 'dd/mm/yy',
      changeMonth: true,
      changeYear: true});
  <c:if test="${actionBean.refresh}">
//    alert("refresh");
    self.close();
    opener.refreshMaklumatDetail();
  </c:if> 

//      COPY FROM 'ALAMAT BERDAFTAR' TO 'ALAMAT SURAT'
    $('#tik').click(function() {
      if ($('#tik').is(':checked')) {
        $("#suratAlamat1").val($("#alamat1").val());
        $("#suratAlamat2").val($("#alamat2").val());
        $("#suratAlamat3").val($("#alamat3").val());
        $("#suratAlamat4").val($("#alamat4").val());
        $("#suratPoskod").val($("#poskod").val());
        $("#suratNegeri").val($("#negeri").val());
      } else if ($('#tik').is(':unchecked')) {
        $("#suratAlamat1").val("");
        $("#suratAlamat2").val("");
        $("#suratAlamat3").val("");
        $("#suratAlamat4").val("");
        $("#suratPoskod").val("");
        $("#suratNegeri").val("");
      }
    });

    $('#simpanPihak').click(function() {
      doBlockUI();
      return validNoKp();
    });

//
//      var ic = $('#ic').val(); // auto get ic no. for 'k/p baru'
//      if (ic == 'B') {
//        var noIC = $('#noIC').val();
//        if (noIC != null) {
//          var jantina = noIC.substring(8, 12); // get gender from ic no.
//          if (jantina % 2 == 0) {
//            $('#jantina').val('2');
//          } else {
//            $('#jantina').val('1');
//          }
//        }
//      }
  });

  function validNoKp() {
    if ($('#jenisPengenalan').val() === '') {
      alert('Sila Pilih Jenis Pengenalan');
      $.unblockUI();
      return false;
    }
    if ($('#noPengenalan').val() === '') {
      alert('Sila Masukkan No Pengenalan');
      $.unblockUI();
      return false;
    }
    if ($('#jenisPengenalan').val() === 'B') {
      if (isNaN($('#noPengenalan').val()) || $('#noPengenalan').val().length !== 12) {
        alert('Format No Pengenalan Baru Tidak Sah');
        $.unblockUI();
        return false;
      }
    }
  }

  function RemoveNonNumeric(strString) {
    // REMOVE NON NUMBERIC ITEM
    var strValidCharacters = "0123456789";
    var strReturn = "";
    var strBuffer = "";
    var intIndex = 0;
    // Loop through the string
    for (intIndex = 0; intIndex < strString.length; intIndex++) {
      strBuffer = strString.substr(intIndex, 1);
      // Is this a number
      if (strValidCharacters.indexOf(strBuffer) > -1) {
        strReturn += strBuffer;
      }
    }
    return strReturn;
  }

  function checkPoskod(elmnt, inputTxt) {
    var a = document.getElementById('poskod');
    if (isNaN(a.value)) {
      elmnt.value = RemoveNonNumeric(inputTxt);
      return;
    }
  }

  function doAutoCalAgeDOB(val2) {
    var val = $('#jenispengenalan').val();
    if (val === 'B' && val2 !== '') {
      removeNonNumeric(val2);
      var icNo = val2;
      var year = icNo.substring(0, 2);
      if (year > 25 && year < 99) {//fixme :
        year = "19" + year;
      } else {
        year = "20" + year;
      }
//        var age = calculateAge(icNo.substring(4, 6), icNo.substring(2, 4), year);
//        $('#umur').val(age);
      $('#trhLahir').val(icNo.substring(4, 6) + "/" + icNo.substring(2, 4) + "/" + year);
      var tLahir = val2;
      tmptLahir(tLahir.substring(6, 8));
      var gender = tLahir.substring(10, 12);
      if (gender % 2 === 0) {// pompan
        $('#jantina').val('2');
      } else {//laki
        $('#jantina').val('1');
      }
    }
  }

  function tmptLahir(val) {
    // Reference from: http://www.jpn.gov.my/kodnegeri
    if ((val === '01') || (val === '21') || (val === '22') || (val === '23') || (val === '24')) {
      var negeri = "01"; //Johor
      $('#tLahir').val(negeri);
    } else if ((val === '02') || (val === '25') || (val === '26') || (val === '27')) {
      var negeri = "02"; //Kedah
      $('#tLahir').val(negeri);
    } else if ((val === '03') || (val === '28') || (val === '29')) {
      var negeri = "03"; //Kelantan
      $('#tLahir').val(negeri);
    } else if ((val === '04') || (val === '30')) {
      var negeri = "04"; //Melaka
      $('#tLahir').val(negeri);
    } else if ((val === '05') || (val === '31') || (val === '59')) {
      var negeri = "05"; //Negeri Sembilan
      $('#tLahir').val(negeri);
    } else if ((val === '06') || (val === '32') || (val === '33')) {
      var negeri = "06"; //Pahang
      $('#tLahir').val(negeri);
    } else if ((val === '07') || (val === '34') || (val === '35')) {
      var negeri = "07"; //Pulau pinang
      $('#tLahir').val(negeri);
    } else if ((val === '08') || (val === '36') || (val === '37') || (val === '38') || (val === '39')) {
      var negeri = "08"; //Perak
      $('#tLahir').val(negeri);
    } else if ((val === '09') || (val === '40')) {
      var negeri = "09"; //Perlis
      $('#tLahir').val(negeri);
    } else if ((val === '10') || (val === '41') || (val === '42') || (val === '43') || (val === '44')) {
      var negeri = "10"; //Selangor
      $('#tLahir').val(negeri);
    } else if ((val === '11') || (val === '45') || (val === '46')) {
      var negeri = "11"; //Terengganu
      $('#tLahir').val(negeri);
    } else if ((val === '12') || (val === '47') || (val === '48') || (val === '49')) {
      var negeri = "12"; //Sabah
      $('#tLahir').val(negeri);
    } else if ((val === '13') || (val === '50') || (val === '51') || (val === '52') || (val === '53')) {
      var negeri = "13"; //Sarawak
      $('#tLahir').val(negeri);
    } else if ((val === '14') || (val === '54') || (val === '55') || (val === '56') || (val === '57')) {
      var negeri = "14"; //Wilayah Persekutuan Kuala Lumpur
      $('#tLahir').val(negeri);
    } else if ((val === '15') || (val === '58')) {
      var negeri = "15"; //Wilayah Persekutuan Labuan
      $('#tLahir').val(negeri);
    } else if (val === '16') {
      var negeri = "16"; //Putrajaya
      $('#tLahir').val(negeri);
    } else {
      var negeri = "99"; //Lain-Lain
      $('#tLahir').val(negeri);
    }
  }

  function removeNonNumeric(strString) {
    var strValidCharacters = "1234567890";
    var strReturn = "";
    var strBuffer = "";
    var intIndex = 0;
    // Loop through the string
    for (intIndex = 0; intIndex < strString.length; intIndex++) {
      strBuffer = strString.substr(intIndex, 1);
      // Is this a number
      if (strValidCharacters.indexOf(strBuffer) > -1)
      {
        strReturn += strBuffer;
      }
    }
    return strReturn;
  }

  function doBlockUI() {
    $.blockUI({
      message: $('#displayBox'),
      css: {
        top: ($(window).height() - 50) / 2 + 'px',
        left: ($(window).width() - 50) / 2 + 'px',
        width: '50px'
      }
    });
  }
</script>
<style type="text/css">
  input.error { background-color: yellow; }
  #tdLabel {
    color:#003194;
    display:block;
    font-family:Tahoma;
    font-size:13px;
    font-weight:bold;
    margin-left:100px;
    margin-right:0.5em;
    text-align:right;
    width:15em;
    vertical-align:top;
  }
  #tdDisplay {
    color:black;
    font-size:13px;
    font-weight:normal;
    width:40em;
  }
</style>
<!DOCTYPE html>
<div class="a">
  <s:messages/>
  <s:errors/>
  <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
  <s:form beanclass="etanah.view.daftar.utiliti.KemaskiniDataActionBean">
    <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
    <div class="subtitle">
      <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}" id="idHakmilik"/>    
      <s:hidden name="idHP"  value="${actionBean.hakmilikPihak.idHakmilikPihakBerkepentingan}" id="idHP"/>
      <s:hidden name="idPihak"  value="${actionBean.idPihak}" id="idPHK"/>
      <fieldset class="aras1">      
        <legend>Maklumat Kemasukan Pemilik</legend>
        <br>
        <table align="center">
          <tr>
            <td id="tdLabel">
              <font color="red">*</font>Jenis Pihak :&nbsp;
            </td>
            <td id="tdDisplay">
              <s:select name="hakmilikPihak.jenis.kod" id="jenisPihak">
                <s:option value="" > -- Sila Pilih -- </s:option>
                <s:options-collection collection="${actionBean.senaraiKodPihak}" label="nama" value="kod"/>
              </s:select>
            </td>
          </tr>
          <tr><td>&nbsp;</td></tr>
          <tr>
            <td id="tdLabel"><font color="red">*</font>Nama :&nbsp;</td>
            <td id="tdDisplay">
              <s:text name="hakmilikPihak.nama" id="nama" size="40" style="text-transform:uppercase" onblur="this.value=this.value.toUpperCase();"/>            
            </td>
          </tr>

          <tr>
            <td id="tdLabel"><font color="red">*</font>Aktif :&nbsp;</td>
            <td id="tdDisplay">
              <s:select name="aktif" id="aktif" >
                <s:option value="${actionBean.hakmilikPihak.aktif}">${actionBean.hakmilikPihak.aktif}</s:option>
                <s:option value="Y">Y</s:option>
                <s:option value="T">T</s:option>
              </s:select>          
            </td>
          </tr>           
          
          <tr>
            <td id="tdLabel"><font color="red">*</font>Jenis Pengenalan :&nbsp;</td>
            <td id="tdDisplay">
              <s:select name="hakmilikPihak.jenisPengenalan.kod" id="jenispengenalan" >
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
              </s:select>          
            </td>
          </tr>
          <tr>
            <td id="tdLabel"><font color="red">*</font>No Pengenalan :&nbsp;</td>
            <td id="tdDisplay">
              <s:text name="hakmilikPihak.noPengenalan" id="noPengenalan" size="40" onchange="doAutoCalAgeDOB(this.value);"/>          
            </td>
          </tr>
          <tr>
            <td id="tdLabel"><font color="red">*</font>No Pengenalan Lama :&nbsp;</td>
            <td id="tdDisplay">
              <s:text name="hakmilikPihak.noPengenalanLama" id="noPengenalanLama" size="40"/>          
            </td>
          </tr>
          <tr>
            <td id="tdLabel">Tempat Lahir :&nbsp;</td>
            <td id="tdDisplay">
              <s:select name="hakmilikPihak.pihak.negeriKelahiran.kod" style="width:200px" id="tLahir">
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" sort="nama"/>
              </s:select>
            </td>
          </tr> 
          <tr>
            <td id="tdLabel">Tarikh Lahir :&nbsp;</td>
            <td id="tdDisplay">
              <s:text name="hakmilikPihak.pihak.tarikhLahir" size="30" id="trhLahir" class="datepicker" formatPattern="dd/MM/yyyy"/>
            </td>
          </tr>
          <tr>
            <td id="tdLabel">Jantina :&nbsp;</td>
            <td id="tdDisplay">
              <s:select name="hakmilikPihak.pihak.kodJantina" id="jantina">
                <s:option value="">Sila Pilih</s:option>
                <s:option value="1">Lelaki</s:option>
                <s:option value="2">Perempuan</s:option>
              </s:select>    
            </td>
          </tr> 
          <tr>
            <td id="tdLabel">Warganegara :&nbsp;</td>
            <td id="tdDisplay">
              <s:select name="hakmilikPihak.wargaNegara.kod" id="warganegara">
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod" />
              </s:select>     
            </td>
          </tr>
          <tr>
              <td id="tdLabel">Penubuhan Syarikat :&nbsp;</td>
              <td id="tdDisplay">
                  <s:select name="hakmilikPihak.penubuhanSyarikat.kod" id="penubuhanSyarikat">
                      <s:option value="">Sila Pilih</s:option>
                      <s:options-collection collection="${list.senaraiKodPenubuhanSyarikat}" label="nama" value="kod" />
                  </s:select>
              </td>
          </tr>
          <tr>
            <td id="tdLabel">Bangsa/Pertubuhan /Syarikat :&nbsp;</td>
            <td id="tdDisplay">
              <s:select class="wideselect" name="hakmilikPihak.pihak.bangsa.kod" id="bangsa">
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${list.senaraiKodBangsa}" label="nama" value="kod" />
              </s:select>    
            </td>
          </tr>         
          <tr><td>&nbsp;</td></tr>
          <tr>
            <td id="tdLabel">Alamat Berdaftar :&nbsp;</td>
            <td id="tdDisplay">
              <s:text name="hakmilikPihak.alamat1" id="alamat1" size="40" style="text-transform:uppercase" onblur="this.value=this.value.toUpperCase();"/>
            </td>
          </tr>
          <tr>
            <td id="tdLabel">&nbsp;</td>
            <td id="tdDisplay">
              <s:text name="hakmilikPihak.alamat2" id="alamat2" size="40" style="text-transform:uppercase"/>
            </td>
          </tr>
          <tr>
            <td id="tdLabel">&nbsp;</td>
            <td id="tdDisplay">
              <s:text name="hakmilikPihak.alamat3" id="alamat3" size="40" style="text-transform:uppercase"/>
            </td>
          </tr>
          <tr>
            <td id="tdLabel">Bandar :&nbsp;</td>
            <td id="tdDisplay">
              <s:text name="hakmilikPihak.alamat4" id="alamat4" size="40" style="text-transform:uppercase"/>
            </td>
          </tr>
          <tr>
            <td id="tdLabel">Poskod :&nbsp;</td>
            <td id="tdDisplay">
              <s:text name="hakmilikPihak.poskod" id="poskod" size="40" maxlength="5" onblur="javascript:checkPoskod(this, this.value);"/>
            </td>
          </tr>
          <tr>
            <td id="tdLabel">Negeri :&nbsp;</td>
            <td id="tdDisplay">
              <s:select name="hakmilikPihak.negeri.kod" id="negeri">
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
              </s:select>
            </td>
          </tr>
          <tr class="tablecloth">
            <td>&nbsp;</td>
            <td id="tdDisplay">
              <s:checkbox name="tik" id="tik" onmouseover="this.style.cursor='pointer';"/>&nbsp;
              <!--<input type="checkbox" id="add1" name="add1" value="" onclick="copyAdd();" onmouseover="this.style.cursor = 'pointer';"/>-->
              <font color="red">*Klik jika alamat berdaftar adalah sama dengan alamat surat menyurat </font>
            </td>
          </tr>
          <tr>
            <td id="tdLabel">Alamat Surat-Menyurat :&nbsp;</td>
            <td id="tdDisplay">
              <s:text name="hakmilikPihak.alamatSurat.alamatSurat1" id="suratAlamat1" size="40" style="text-transform:uppercase"/>
            </td>
          </tr>
          <tr>
            <td id="tdLabel">&nbsp;</td>
            <td id="tdDisplay">
              <s:text name="hakmilikPihak.alamatSurat.alamatSurat2" id="suratAlamat2" size="40" style="text-transform:uppercase"/>
            </td>
          </tr>
          <tr>
            <td id="tdLabel">&nbsp;</td>
            <td id="tdDisplay">
              <s:text name="hakmilikPihak.alamatSurat.alamatSurat3" id="suratAlamat3" size="40" style="text-transform:uppercase"/>
            </td>
          </tr>
          <tr>
            <td id="tdLabel">Bandar :&nbsp;</td>
            <td id="tdDisplay">
              <s:text name="hakmilikPihak.alamatSurat.alamatSurat4" id="suratAlamat4" size="40" style="text-transform:uppercase"/>
            </td>
          </tr>
          <tr>
            <td id="tdLabel">Poskod :&nbsp;</td>
            <td id="tdDisplay">
              <s:text name="hakmilikPihak.alamatSurat.poskodSurat" id="suratPoskod" size="40" maxlength="5"/>
            </td>
          </tr>
          <tr>
            <td id="tdLabel">Negeri :&nbsp;</td>
            <td id="tdDisplay">
              <s:select name="hakmilikPihak.alamatSurat.negeriSurat.kod" id="suratNegeri">
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
              </s:select>
            </td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td> 
              <br>              
              <s:submit name="updatePihak" id="simpanPihak" value="Simpan" class="btn"/>&nbsp;
              <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </td>
          </tr>
        </table>      
        <br>
      </fieldset>
    </div>
  </s:form>
</div>
