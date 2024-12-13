<%--
    Document   : edit_pemohon
    Created on : Nov 10, 2009, 4:23:38 PM
    Author     : muhammad.amin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="list" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ageCalculator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/formatCurrency.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

  $(document).ready(function() {
    $('.empty').remove();

    var jen = $('#jenis_pihak').val();
    if (jen == 'PA' || jen === 'PP') {
      $('#pgAmanah').show();
    }
    
   var jp = $('#jenisPengenalan').val();
    
    if((jp == 'X')||(jp == '0'))
    {
      $('#noPengenalan').hide();
      $('#labelNoPengenalan').hide();
    }

    $('#jenis_pihak').change(function() {
      var val = $(this).val();
      if (val == 'PA') {
        $('#pgAmanah').show();
      } else {
        $('#pgAmanah').hide();
      }
    });


    $('#addPA').click(function() {
      $('#pgAmanahDialog').dialog('open');
    });

    $("#pgAmanahDialog").dialog({
      autoOpen: false,
      height: 350,
      width: 800,
      modal: true,
      buttons: {
        'Tutup': function() {
          $(this).dialog('close');
        },
        'Simpan': function() {
          var bValid = true;
          bValid = bValid && ($('#namaAmanah').val() != '');
          bValid = bValid && ($('#kpAmanah').val() != '');
          bValid = bValid && ($('#nokpAmanah').val() != '');
//          bValid = bValid && ($('#alamatAmanah1').val() != '');
//          bValid = bValid && ($('#negeriAmanah').val() != '');
//          bValid = bValid && ($('#syerPembilangAmanah').val() != '');
//          bValid = bValid && ($('#syerPenyebutAmanah').val() != '');
            var kpAmanah = $('#kpAmanah').val();      
            if((kpAmanah == 'X')||(kpAmanah == '0')){
                bValid = true;
                 $('#nokpAmanah').val("-");
                 document.getElementById('nokpAmanah').value='-';
            } 

          if (bValid) {
            var nama = $('#namaAmanah').val();
            var kp = $('#kpAmanah').val();
            var noKp = $('#nokpAmanah').val();
            var wargaPA = $('#warganegaraAmanah').val();
            var add1 = $('#alamatAmanah1').val();
            var add2 = $('#alamatAmanah2').val();
            var add3 = $('#alamatAmanah3').val();
            var add4 = $('#alamatAmanah4').val();
            var poskod = $('#poskodAmanah').val();
            var negeri = $('#negeriAmanah').val();
            var syerPembilang = $('#syerPembilangAmanah').val();
            var syerPenyebut = $('#syerPenyebutAmanah').val();

            var rowNo = $('table#pgAmanahTable tr').length;
            var _nama = nama.replace(/'/g, "__&&");

            $('table#pgAmanahTable tbody').append("<tr id='x" + rowNo + "' class='x'>" +
                    "<td><input type=hidden name='namaPA' value='" + _nama + "'/>" + nama + '</td>' +
                    "<td><input type=hidden name='kpPA' value='" + kp + "'/>" + $('#kpAmanah option:selected').text() + '</td>' +
                    "<td><input type=hidden name='noKpPA' value='" + noKp + "'/>" + noKp + '</td>' +
                    "<td><input type=hidden name='wargaPA' value='" + wargaPA + "'/>" + $('#warganegaraAmanah option:selected').text() + '</td>' +
                    "<td><input type=hidden name='add1PA' value='" + add1 + "'/>" + add1 + '</td>' +
                    "<td><input type=hidden name='add2PA' value='" + add2 + "'/>" + add2 + '</td>' +
                    "<td><input type=hidden name='add3PA' value='" + add3 + "'/>" + add3 + '</td>' +
                    "<td><input type=hidden name='add4PA' value='" + add4 + "'/>" + add4 + '</td>' +
                    "<td><input type=hidden name='poskodPA' value='" + poskod + "'/>" + poskod + '</td>' +
                    "<td><input type=hidden name='negeriPA' value='" + negeri + "'/>" + $('#negeriAmanah option:selected').text() + '</td>' +
                    "<td><input type=hidden name='syerPembilangAmanah' value='" + syerPembilang + "'/><input type=hidden name='syerPenyebutAmanah' value='" + syerPenyebut + "'/>" + syerPembilang + '/' + syerPenyebut + '</td>' +
                    "<td><img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' \n\
                                onmouseover='this.style.cursor=\"pointer\";' onclick='deleteWaris2(\"x" + rowNo + "\");'></td>" +
                    '</tr>');
            $(this).dialog('close');
          } else {
            alert('Sila masukan maklumat pihak dipegang amanah dengan lengkap. ');
          }
        }
      },
      close: function() {
        $('#namaAmanah').val('');
        $('#kpAmanah').val('');
        $('#nokpAmanah').val('');
        $('#alamatAmanah1').val('');
        $('#alamatAmanah2').val('');
        $('#alamatAmanah3').val('');
        $('#alamatAmanah4').val('');
        $('#poskodAmanah').val('');
        $('#negeriAmanah').val('');
      }
    });
  });

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
  
  function convert(val, id) {
    var amaun = CurrencyFormatted(val);
    amaun = CommaFormatted(amaun);
    $('#' + id).val(amaun);
  }
  
  function validateForTiada(){
        var jenisPengenalan = $('#jenisPengenalan').val();      
        if((jenisPengenalan == 'X')||(jenisPengenalan == '0')){            
            $('#noPengenalan').hide();
            $('#labelNoPengenalan').hide();
        }         
        else{            
            $('#noPengenalan').show();
            $('#labelNoPengenalan').show();
        } 
    }

  function save(event, f) {
  
   if (validate()) {
       doBlockUI();

    var q = $(f).formSerialize();
    var url = f.action + '?' + event;
    $.post(url, q,
            function(data) {               
              $('#page_div', opener.document).html(data);
              $.unblockUI();
              self.close();
            }, 'html');
   }
  }
  
  function validate() {
      var kod = $('#jenis_pihak').val();
        if (kod === 'CP' || kod === 'WAR' || kod === 'ASL' 
                || kod === 'JA' || kod === 'KL' || kod === 'PA'
                || kod === 'PDP'|| kod === 'PK' || kod === 'PLK' || kod === 'PM'
                || kod === 'PP' || kod === 'RP' || kod === 'WKL' || kod === 'WPA' 
                || kod === 'WS' || kod === 'JK' ) {
            var umur = $('#umur').val();
            if (umur < 18) {
                alert('Umur mestilah lebih daripada 18 tahun.');
                return false;
            }
        }
        return true;
  }

  function validateNumber(elmnt, content) {
    //if it is character, then remove it..
    if (isNaN(content)) {
      elmnt.value = removeNonNumeric(content);
      return;
    }
  }

  function removeNonNumeric(strString)
  {
    var strValidCharacters = "1234567890";
    var strReturn = "";
    var strBuffer = "";
    var intIndex = 0;
    // Loop through the string
    for (intIndex = 0; intIndex < strString.length; intIndex++)
    {
      strBuffer = strString.substr(intIndex, 1);
      // Is this a number
      if (strValidCharacters.indexOf(strBuffer) > -1)
      {
        strReturn += strBuffer;
      }
    }
    return strReturn;
  }

  function deleteWaris(id) {
    doBlockUI();
    frm = document.form1;
    var url = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?deletePihakHubungan&source=kemaskiniPenerima&id='
            + id + '&idPihak=' + $('#idPihak').val() + '&idPermohonPihak=' + $('#idPermohonanPihak').val();

    frm.action = url;
    frm.submit();
  }

  function deleteWaris2(id) {
    $('#' + id).remove();
  }

  function copyAdd() {
    if ($('input[name=add1]').is(':checked')) {
      $('#suratAlamat1').val($('#alamat1').val());
      $('#suratAlamat2').val($('#alamat2').val());
      $('#suratAlamat3').val($('#alamat3').val());
      $('#suratAlamat4').val($('#alamat4').val());
      $('#suratPoskod').val($('#poskod').val());
      $('#suratNegeri').val($('#negeri').val());
    } else {
      $('#suratAlamat1').val('');
      $('#suratAlamat2').val('');
      $('#suratAlamat3').val('');
      $('#suratAlamat4').val('');
      $('#suratPoskod').val('');
      $('#suratNegeri').val('');

    }
  }
  
  
    function dodacheck(val) {
            //var mikExp = /[1\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\1\|]/;
            var v = $('#jenisPengenalan').val();

            if(v == 'B') {
                var strPass = val;
                var strLength = strPass.length;
                //$('#kp').attr("maxlength","12");
                if(strLength > 12) {
                    var tst = val.substring(0, (strLength) - 1);
                    $('#kp').val(tst);
                }
                var lchar = val.charAt((strLength) - 1);
                if(isNaN(lchar)) {
                    //return false;
                    var tst = val.substring(0, (strLength) - 1);
                    $('#kp').val(tst);
                }
            }//else{
            // $('#kp').attr("maxlength","30");
            // }
        }

        function doCheckUmur(v,id,type){
            var va = $('#jenisPengenalan').val();
            if(va == 'B'){
                return doValidateAge(v, id, 'jenisPengenalan',type);
            }else {
                return true;
            }
        }
        
        function calAgeByDOB(value){

        var year = value.substring(8,10);

        if(year > 25 && year < 99){//fixme :
            year = "19" + year;
        }else {
            year = "20" + year;
        }

        var age = calculateAge(value.substring(0,2), value.substring(3,5), year);
        $('#umur').val(age);
    }
    function doAutoCalAgeDOB(val2) {
      var val = $('#jenisPengenalan').val();

      if (val == 'B' && val2 != '') {
        removeNonNumeric(val2);
//              alert (val);
        var icNo = val2;
        var year = icNo.substring(0, 2);
        if (year > 25 && year < 99) {//fixme :
          year = "19" + year;
        } else {
          year = "20" + year;
        }

        var age = calculateAge(icNo.substring(4, 6), icNo.substring(2, 4), year);
        $('#umur').val(age);
        $('#trhLahir').val(icNo.substring(4, 6) + "/" + icNo.substring(2, 4) + "/" + year);

        var tLahir = val2;
// check place of birth from noPengenalan Baru        
        tmptLahir(tLahir.substring(6, 8));

        var gender = tLahir.substring(10,12);
        if (gender % 2 === 0) {// pompan
          $('#gender').val('1');
        } else {//laki
          $('#gender').val('2');
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
      //send noPengenalan value to check place of birth
//      var url = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?selectTmptKelahiran&tempatLahir=' + val;
//      frm = document.form1;
//      frm.action = url;
//      frm.submit();
    }
    
  

</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form beanclass="etanah.view.daftar.PihakKepentinganAction" name="form1">
  <div class="subtitle">
    <fieldset class="aras1">
      <s:hidden name="pihak.idPihak" id="idPihak"/>
      <s:hidden name="idHakmilik"/>      
      <s:hidden name="permohonanPihak.idPermohonanPihak" id="idPermohonanPihak"/>
      <s:hidden name="pihak.noPengenalan"/>
      <legend>Kemaskini Maklumat Pihak :</legend>
      <%--
      <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PH30A'}">
          <p>
              <label>Nama :</label>${actionBean.permohonanPihak.nama} 
              <s:hidden name="permohonanPihak.nama"/>
          </p>
          <p>
              <label>Jenis Pengenalan :</label>
              ${actionBean.permohonanPihak.jenisPengenalan.nama}&nbsp;<s:hidden name="permohonanPihak.jenisPengenalan.kod"/>
          </p>
          <p>
              <label>Nombor Pengenalan :</label>
              ${actionBean.permohonanPihak.noPengenalan}&nbsp;
              <s:hidden name="permohonanPihak.noPengenalan"/>
          </p>
      </c:if>
      --%>
      <!--azmi-->
      <%--Boleh Edit Nama, IC , Jenis IC Untuk Urusan PH30A Sahaja 
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PH30A'}">--%>
          <p>
              <label>Nama :</label>
              <s:text name="permohonanPihak.nama"  size="70" onkeyup="this.value=this.value.toUpperCase();"/>
          </p>
          <p>
              <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
              <s:select name="permohonanPihak.jenisPengenalan.kod" id="jenisPengenalan" onchange="validateForTiada()">
                      <s:option value="">Sila Pilih</s:option>
                  <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
              </s:select>
          </p>
          <p>
              <label id="labelNoPengenalan">Nombor Pengenalan :</label>
              <s:text id="noPengenalan" name="permohonanPihak.noPengenalan"  size="30" onchange="doAutoCalAgeDOB(this.value);"/>
          </p>
      <%--</c:if>--%>
      <!--azmi-->
      <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B'
                    || actionBean.pihak.jenisPengenalan.kod eq 'L'
                    || actionBean.pihak.jenisPengenalan.kod eq 'P'
                    || actionBean.pihak.jenisPengenalan.kod eq 'T'
                    || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
            
            <s:hidden name="tarikhLahir" id="trhLahir"  onchange="calAgeByDOB(this.value);"/>
            <s:hidden name="pihak.kodJantina" id="gender" />
            <s:hidden name="pihak.negeriKelahiran.kod" id="tLahir" />
            <p class="individu">
              <label>Umur :</label>
              <s:text name="permohonanPihak.umur" size="10" maxlength="3" id="umur" onkeyup="validateNumber(this,this.value);"/>
            </p>            
            <p>
              <label>Pekerjaan :</label>
              <s:text name="permohonanPihak.pekerjaan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
              <label>Pendapatan Bulanan (RM) :</label>
              <s:text name="permohonanPihak.pendapatan" size="40" onkeyup="validateNumber(this,this.value);" id="gajiPemohon"
                      onchange="convert(this.value, this.id);" formatPattern="#,###.00"/>
            </p>
            <p>
              <label>Status Perkahwinan :</label>
              <s:select name="permohonanPihak.statusKahwin" style="width:255px">
                <s:option value="">Sila Pilih</s:option>
                <s:option>Berkahwin</s:option>
                <s:option>Bujang</s:option>
                <s:option>Duda</s:option>
                <s:option>Ibu Tunggal</s:option>
              </s:select>
            </p>
            <p>
              <label>Tanggungan :</label>
              <s:text name="permohonanPihak.tangungan" size="10" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
            </p>
      </c:if>
      <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'D' || actionBean.pihak.jenisPengenalan.kod eq 'S'
                    || actionBean.pihak.jenisPengenalan.kod eq 'U'|| actionBean.pihak.jenisPengenalan.kod eq 'N' }">
            <p class="syarikat">
              <label>Daftar Tubuh :</label>
              <s:select name="pihak.penubuhanSyarikat.kod" id="daftarTubuh">
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${list.senaraiKodPenubuhanSyarikat}" label="nama" value="kod"/>
              </s:select>
            </p>

      </c:if>
      <p>
        <label>Jenis Pihak Berkepentingan</label>
        <s:select name="permohonanPihak.jenis.kod" style="width:300px" id="jenis_pihak">
          <s:options-collection collection="${actionBean.senaraiKodPenerima}" label="nama" value="kod" />
        </s:select>
      </p>
      <p>
        <label>Jenis Syarikat / Bangsa :</label>
        <s:select name="pihak.bangsa.kod" id="bangsa">
          <s:option value="">Sila Pilih</s:option>
          <s:options-collection collection="${list.senaraiKodBangsa}" label="nama" value="kod"/>
        </s:select>
      </p>
      <p>
        <label>Hubungan :</label>
        <s:select name="permohonanPihak.kaitan" style="width:255px">                    
          <s:option value="IBU BAPA KEPADA ANAK" selected="true">IBU BAPA KEPADA ANAK</s:option>
          <s:option value="ANAK KEPADA IBU BAPA">ANAK KEPADA IBU BAPA</s:option>
          <s:option value="SUAMI KEPADA ISTERI">SUAMI KEPADA ISTERI</s:option>
          <s:option value="ISTERI KEPADA SUAMI">ISTERI KEPADA SUAMI</s:option>
          <s:option value="SAUDARA-MARA">SAUDARA-MARA</s:option>
          <s:option value="PENJUAL / PEMBELI">PENJUAL / PEMBELI</s:option>
          <s:option value="LAIN-LAIN">LAIN-LAIN</s:option>
        </s:select>
      </p>
      <p>
        <label>Warganegara :</label>
        <s:select name="pihak.wargaNegara.kod" style="width:255px" id="kod_warganegara">
          <s:option value="">Sila Pilih</s:option>
          <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
        </s:select>
      </p>
      <p>
        <label for="alamat">Alamat Berdaftar :</label>
        <s:text name="permohonanPihak.alamat.alamat1" id="alamat1" disabled="${disabled}" size="70" maxlength="70" onkeyup="this.value=this.value.toUpperCase();"/>
        <s:hidden name="pihak.alamat1"/>
      </p>
      <p>
        <label> &nbsp;</label>
        <s:text name="permohonanPihak.alamat.alamat2" id="alamat2" disabled="${disabled}" size="70" maxlength="70" onkeyup="this.value=this.value.toUpperCase();"/>
        <s:hidden name="pihak.alamat2"/>
      </p>
      <p>
        <label> &nbsp;</label>
        <s:text name="permohonanPihak.alamat.alamat3" id="alamat3" disabled="${disabled}" size="70" maxlength="70" onkeyup="this.value=this.value.toUpperCase();"/>
        <s:hidden name="pihak.alamat3"/>
      </p>
      <p>
        <label>Bandar :</label>
        <s:text name="permohonanPihak.alamat.alamat4" id="alamat4" disabled="${disabled}" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
        <s:hidden name="pihak.alamat4"/>
      </p>
      <p>
        <label>Poskod :</label>
        <s:text name="permohonanPihak.alamat.poskod" id="poskod" disabled="${disabled}" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
        <s:hidden name="pihak.poskod"/>
      </p>

      <p>
        <label for="Negeri">Negeri :</label>
        <s:select name="permohonanPihak.alamat.negeri.kod" id="negeri" disabled="${disabled}" style="width:255px">
          <s:option value="">Sila Pilih</s:option>
          <s:options-collection collection="${list.senaraiKodNegeriAktif}" label="nama" value="kod"/>
        </s:select>
        <s:hidden name="pihak.negeri.kod"/>
      </p>
      <p>
        <label>&nbsp;</label>
        <input type="checkbox" id="add1" name="add1" value="" onclick="copyAdd();"/>
        <em>Alamat surat-menyurat sama dengan alamat berdaftar.</em>
      </p>
      <p>
        <label for="alamat">Alamat Surat-Menyurat :</label>
        <s:text name="permohonanPihak.alamatSurat.alamatSurat1" id="suratAlamat1" size="70" maxlength="70" onkeyup="this.value=this.value.toUpperCase();"/>
      </p>
      <p>
        <label> &nbsp;</label>
        <s:text name="permohonanPihak.alamatSurat.alamatSurat2" id="suratAlamat2" size="70" maxlength="70" onkeyup="this.value=this.value.toUpperCase();"/>
      </p>
      <p>
        <label> &nbsp;</label>
        <s:text name="permohonanPihak.alamatSurat.alamatSurat3" id="suratAlamat3" size="70" maxlength="70" onkeyup="this.value=this.value.toUpperCase();"/>
      </p>
      <p>
        <label>Bandar :</label>
        <s:text name="permohonanPihak.alamatSurat.alamatSurat4" id="suratAlamat4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
      </p>
      <p>
        <label>Poskod :</label>
        <s:text name="permohonanPihak.alamatSurat.poskodSurat" id="suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
      </p>

      <p>
        <label for="Negeri">Negeri :</label>
        <s:select name="permohonanPihak.alamatSurat.negeriSurat.kod" id="suratNegeri" style="width:255px">
          <s:option value="">Sila Pilih</s:option>
          <s:options-collection collection="${list.senaraiKodNegeriAktif}" label="nama" value="kod"/>
        </s:select>
      </p> 
      <br/>           

      <c:if test="${disabledWaris eq 'false'}">
        <p>
        <div id="pgAmanah" style="display: none">
          <span class=instr><em>Sila klik pada imej <img src='${pageContext.request.contextPath}/images/tambah.png'/> untuk menambah waris
            </em></span><br/>
          <div align="center">Senarai Waris</div>
          <div align="center">
            <display:table class="tablecloth" name="${actionBean.senaraiPihakHubungan}" cellpadding="0" cellspacing="0"
                           id="pgAmanahTable" pagesize="10"
                           requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
              <%--<display:column title="Bil" sortable="true">${pgAmanahTable_rowNum}</display:column>--%>
              <display:column  title="Nama" class="nama">
                ${pgAmanahTable.nama}
              </display:column>
              <display:column title="Jenis Pengenalan">
                ${pgAmanahTable.jenisPengenalan.nama}
              </display:column>
              <display:column title="Nombor Pengenalan">
                ${pgAmanahTable.noPengenalan}
              </display:column>
              <display:column title="Warganegara">
                ${pgAmanahTable.wargaNegara.nama}
              </display:column>
              <display:column title="Alamat 1">
                ${pgAmanahTable.alamat1}
              </display:column>
              <display:column title="Alamat 2">
                ${pgAmanahTable.alamat2}
              </display:column>
              <display:column title="Alamat 3">
                ${pgAmanahTable.alamat3}
              </display:column>
              <display:column title="Bandar">
                ${pgAmanahTable.alamat4}
              </display:column>
              <display:column title="Poskod">
                ${pgAmanahTable.poskod}
              </display:column>
              <display:column title="Negeri">
                ${pgAmanahTable.negeri.nama}
              </display:column>
              <display:column title="Syer">
                ${pgAmanahTable.syerPembilang} / ${pgAmanahTable.syerPenyebut}
              </display:column>
              <display:column title="Hapus">
                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                     id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';"
                     onclick="deleteWaris(${pgAmanahTable.idHubungan});" />
              </display:column>
              <display:column title="<img alt='Tambah Waris' id='addPA'
                              src='${pageContext.request.contextPath}/images/tambah.png' onmouseover=\"this.style.cursor='pointer';\"/>"/>
            </display:table>
          </div>
          <br/>
          <%--<p><label>&nbsp;</label></p><s:button name="" value="Tambah Waris" class="longbtn" id="addPA"/>--%>
        </div>
        </p>
      </c:if>

      <div id="pgAmanahDialog" style="display: none" align="left" title="Tambah Waris">

        <!--                        <div>
                                    Maklumat Pihak Yang Dipegang Amanah
                                </div>-->
        <p align="left">
          <label for="nama"><font color="red">*</font>Nama :</label>
          <!--                            <font color="red">*</font>Nama :-->
          <s:text name="permohonanPihakHubungan.nama" id="namaAmanah" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
        </p>

        <p>
          <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>

          <s:select name="permohonanPihakHubungan.jenisPengenalan.kod" id="kpAmanah">
            <s:option value="">Sila Pilih</s:option>
            <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
          </s:select>
        </p>
        <p>
          <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>

          <s:text name="permohonanPihakHubungan.noPengenalan" id="nokpAmanah" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
        </p>
        <p>
          <label>Warganegara :</label>
          <s:select name="permohonanPihakHubungan.wargaNegara.kod" style="width:200px" id="warganegaraAmanah">
            <s:option value="">Sila Pilih</s:option>
            <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
          </s:select>
        </p>
        <p>
          <label for="alamat">Alamat :</label>

          <s:text name="permohonanPihakHubungan.alamat1" size="40" id="alamatAmanah1" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
        </p>

        <p>
          <label> &nbsp;</label>

          <s:text name="permohonanPihakHubungan.alamat2" size="40" id="alamatAmanah2" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
        </p>

        <p>
          <label> &nbsp;</label>

          <s:text name="permohonanPihakHubungan.alamat3" size="40" id="alamatAmanah3" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
        </p>
        <p>
          <label>Bandar :</label>

          <s:text name="permohonanPihakHubungan.alamat4" size="40" id="alamatAmanah4" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
        </p>
        <p>
          <label>Poskod :</label>

          <s:text name="permohonanPihakHubungan.poskod" size="40" maxlength="5" id="poskodAmanah" onkeyup="validateNumber(this,this.value);"/>
        </p>

        <p>
          <label for="Negeri">Negeri :</label>

          <s:select name="permohonanPihakHubungan.negeri.kod" id="negeriAmanah" style="width:200px">
            <s:option value="">Sila Pilih</s:option>
            <s:options-collection collection="${list.senaraiKodNegeriAktif}" label="nama" value="kod"/>
          </s:select>
        </p>

        <p>
          <label for="Negeri">Syer :</label>
          <s:text name="syerPembilang" size="5" id="syerPembilangAmanah"/> /
          <s:text name="syerPenyebut" size="5" id="syerPenyebutAmanah"/>
        </p>

        <br/>
        <br/>
      </div>            
      <p>
        <label>&nbsp;</label>
        <s:button name="simpanKemaskiniPenerima" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>                
        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
      </p>
    </fieldset >
  </div>

</s:form>
