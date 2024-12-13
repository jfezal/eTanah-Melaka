<%-- 
    Document   : kemasukan_pihak_popup
    Created on : Nov 5, 2010, 3:26:36 AM
    Author     : fikri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquery-1.3.2.min.js"></script>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.core.js"></script>--%>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.2.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ageCalculator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<script type="text/javascript">
  $(document).ready(function() {
    $('.empty').remove();

    $(".datepicker").datepicker({dateFormat: 'dd/mm/yy',
      changeMonth: true,
      changeYear: true});

    var val = $('#jenisPengenalan').val();

    if (val == "B" && $('#trhLahir').val() == '') {
      var icNo = '${actionBean.pihak.noPengenalan}';

      var year = icNo.substring(0, 2);

      if (year > 25 && year < 99) {//fixme :
        year = "19" + year;
      } else {
        year = "20" + year;
      }

      var age = calculateAge(icNo.substring(4, 6), icNo.substring(2, 4), year);
      $('#umur').val(age);
      $('#trhLahir').val(icNo.substring(4, 6) + "/" + icNo.substring(2, 4) + "/" + year);
    }
    
    if((val == 'X')||(val == '0'))
    {
      $('#noPengenalan').hide();
      $('#labelNoPengenalan').hide();
    }

    $('#jenisPengenalan').change(function() {
      var val = $(this).val();

      if (val == 'B' || val == 'L' || val == 'P'
              || val == 'T' || val == 'I') {
        $('.individu').show();
        $('.noPengenalan').show();
        $('.syarikat').hide();
      } else if (val == '0') {
        $('.noPengenalan').hide();
      } else {
        if (val == 'U' || val == 'S' || val == 'N' || val == 'D') {
          $('.syarikat').show();
          $('.individu').hide();
        } else {
          $('.individu').hide();
          $('.noPengenalan').show();
        }
      }
    });

    $('#noPengenalan').change(function() {
      dodacheck($(this).val(), 'jenisPengenalan', 'noPengenalan');
    });

    $("input:text").each(function(el) {
      $(this).focus(function() {
        $(this).addClass('focus');
      });
      $(this).blur(function() {
        $(this).removeClass('focus');
      });
    });

    var disabled = '${disabledWaris}';

    visibleSyer($('#jenis_pihak').val());
    visibleSyerBersama($('#jenis_pihak').val());

    if (disabled == 'false') {
      $('#jenis_pihak').change(function() {
        var val = $(this).val();
        if (val == 'PA' || val == 'PP') {
          $('#pgAmanah').show();
        } else {
          $('#pgAmanah').hide();
        }
      });
    }

    $('#addPA').click(function() {
      $('#pgAmanahDialog').dialog('open');
    });
    $('#addCaw').click(function() {
      $('#cawDialog').dialog('open');
    });

    $("#pgAmanahDialog").dialog({
      autoOpen: false,
      height: 360,
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
          //bValid = bValid && ($('#alamatAmanah1').val() != '');
          //bValid = bValid && ($('#negeriAmanah').val() != '');
          //bValid = bValid && ($('#syerPembilangAmanah').val() != '');
          //bValid = bValid && ($('#syerPenyebutAmanah').val() != '');

          if (bValid) {
            var id = $('#idWaris').val();
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
                    "<td><input type=hidden name='syerPembilangAmanah' value='" + syerPembilang + "'/><input type=hidden name='syerPenyebutAmanah' value='"
                    + syerPenyebut + "'/>" + syerPembilang + '/' + syerPenyebut + '</td>' +
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

    $("#cawDialog").dialog({
      autoOpen: false,
      height: 500,
      width: 800,
      modal: true,
      buttons: {
        'Tutup': function() {
          $(this).dialog('close');
        },
        'Simpan': function() {
          var bValid = true;
          bValid = bValid && ($('#namaCaw').val() != '');
          bValid = bValid && ($('#alamatCaw1').val() != '');
          bValid = bValid && ($('#negeriCaw').val() != '');

          if (bValid) {
            var id = $('#idCaw').val();
            var row = $('#row').val();
            var nama = $('#namaCaw').val();
            var add1 = $('#alamatCaw1').val();
            var add2 = $('#alamatCaw2').val();
            var add3 = $('#alamatCaw3').val();
            var add4 = $('#alamatCaw4').val();
            var poskod = $('#poskodCaw').val();
            var negeri = $('#negeriCaw').val();
            var surat1 = $('#suratAlamatCaw1').val();
            var surat2 = $('#suratAlamatCaw2').val();
            var surat3 = $('#suratAlamatCaw3').val();
            var surat4 = $('#suratAlamatCaw4').val();
            var poskodSurat = $('#suratPoskodCaw').val();
            var negeriSurat = $('#suratNegeriCaw').val();
            var telCaw = $('#telCaw').val();

            var rowNo;
            if (row != '') {
              rowNo = row;
            } else {
              rowNo = $('table#cawTable tr').length;
            }

            var no = $('.nw_pc').length;

            doBlockUI();
            var url = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?saveOrUpdatePihakCaw&idPihakCaw='
                    + id + '&idPihak=' + $('#idPihak').val() + '&namaCaw=' + nama
                    + '&add1=' + add1 + '&add2=' + add2 + '&add3=' + add3 + '&add4=' + add4
                    + '&poskod=' + poskod + '&negeri=' + negeri + '&surat1=' + surat1 + '&surat2=' + surat2
                    + '&surat3=' + surat3 + '&surat4=' + surat4 + '&poskodSurat=' + poskodSurat + '&negeriSurat=' + negeriSurat
                    + '&telCaw=' + telCaw;
            frm = document.form1;
            frm.action = url;
            $(this).dialog('close');
            frm.submit();

          } else {
            alert('Sila masukan maklumat cawangan dengan lengkap. ');
          }
        }
      },
      close: function() {
        $('#namaCaw').val('');
        $('#alamatCaw1').val('');
        $('#alamatCaw2').val('');
        $('#alamatCaw3').val('');
        $('#alamatCaw4').val('');
        $('#poskodCaw').val('');
        $('#negeriCaw').val('');
        $('#suratAlamatCaw1').val('');
        $('#suratAlamatCaw2').val('');
        $('#suratAlamatCaw3').val('');
        $('#suratAlamatCaw4').val('');
        $('#suratPoskodCaw').val('');
        $('#suratNegeriCaw').val('');
        $('#telCaw').val('');
      }
    });

  });

  function editCawanganPemohon(idCaw, id, row) {
    $('#namaCaw').val($('#namaCaw_' + row).val());
    $('#idCaw').val($('#idCaw_' + row).val());
    $('#row').val(row);
    $('#suratAlamatCaw1').val($('#addSurat1Caw_' + row).val());
    $('#suratAlamatCaw2').val($('#addSurat2Caw_' + row).val());
    $('#suratAlamatCaw3').val($('#addSurat3Caw_' + row).val());
    $('#suratAlamatCaw4').val($('#addSurat4Caw_' + row).val());
    $('#suratPoskodCaw').val($('#addSuratPoskodCaw_' + row).val());
    $('#suratNegeriCaw').val($('#addSuratNegeriCaw_' + row).val());

    $('#alamatCaw1').val($('#add1Caw_' + row).val());
    $('#alamatCaw2').val($('#add2Caw_' + row).val());
    $('#alamatCaw3').val($('#add3Caw_' + row).val());
    $('#alamatCaw4').val($('#add4Caw_' + row).val());
    $('#poskodCaw').val($('#addPoskodCaw_' + row).val());
    $('#negeriCaw').val($('#addNegeriCaw_' + row).val());
    $('#cawDialog').dialog('open');
  }

  function editWaris(row) {
    $('#idWaris').val($('#idWaris_' + row).val());
    $('#namaAmanah').val($('#namaWaris_' + row).val());
    $('#kpAmanah').val($('#jenisPengenalanWaris_' + row).val());
    $('#nokpAmanah').val($('#noPengenalanWaris_' + row).val());
    $('#warganegaraAmanah').val($('#wargaWaris_' + row).val());
    $('#alamatAmanah1').val($('#add1Waris_' + row).val());
    $('#alamatAmanah2').val($('#add2Waris_' + row).val());
    $('#alamatAmanah3').val($('#add3Waris_' + row).val());
    $('#alamatAmanah4').val($('#add4Waris_' + row).val());
    $('#poskodAmanah').val($('#pokodWaris_' + row).val());
    $('#negeriAmanah').val($('#negeriWaris_' + row).val());
    $('#syerPembilangAmanah').val($('#syer1Waris_' + row).val());
    $('#syerPenyebutAmanah').val($('#syer2Waris_' + row).val());
    $('#pgAmanahDialog').dialog('open');
  }


  function save(event, f) {

    if (doValidation()) {
      doBlockUI();
      var q = $(f).formSerialize();
      var url = f.action + '?' + event;
      $.ajax({
        type: "POST",
        url: url,
        dataType: 'html',
        data: q,
        error: function(xhr, ajaxOptions, thrownError) {
          alert("error=" + xhr.responseText);
        },
        success: function(data) {
          $('#page_div', opener.document).html(data);
          $.unblockUI();
          self.close();
        }
      });
  <%--$.post(url,q,
  function(data){
      $('#page_div',opener.document).html(data);
      $.unblockUI();
      self.close();
  },'html');--%>
      }
    }

    function doValidation() {

      var val = $('#kod_warganegara').val();
      var val2 = $('#alamat1').val();
      var val3 = $('#nama_pemohon').val();
      var val4 = $('#jenis_pihak').val();
      var val7 = $('#jenisPengenalan').val();
      var val8 = $('#noPengenalan').val();
      var val9 = $('#jenisPengenalan').val();


      if (val3 == '') {
        alert('Sila masukan nama pihak.');
        return false;
      } else if (val == '' && (val9 == 'B' || val9 == 'L' || val9 == 'P' || val9 == 'T' || val9 == 'I')) {
        alert('Sila pilih warganegara.');
        return false;
      } else if (val2 == '') {
        alert('Sila masukan alamat berdaftar.');
        return false;
      } else if (val4 == '') {
        alert('Sila Masukan Jenis Pihak Berkepentingan.');
        return false;
      }else if ((val7 == '')||(val7 == 'X')||(val7 == '0')) {
          if(val7 == ''){
            alert('Sila masukan jenis pengenalan');
            return false;
          }
          else if((val7 == 'X')||(val7 == '0')){
            return true;
          }
      } else if (val8 == '' && val9 != '0') {
        alert('Sila masukan no pengenalan');
        return false;
      } 
//      else if ($('#syerTerlibat').hasClass('show')) {
//
//        var val5 = $('#syerPembilang').val();
//        var val6 = $('#syerPenyebut').val();
//        if (val5 == '' || val6 == '') {
//          alert('Sila semak syer.');
//          return false;
//        }
//      }

      if ($('#syerBersama').hasClass('show')) {
        var val10 = $('#syerKongsi').val();

        if (val10 == '') {
          alert('Sila pilih Ya jika syer dipegang bersama.');
          return false;
        }
      }
      
      
      var kod = $('#jenis_pihak').val();
      var jenisKp = $('#jenisPengenalan').val();
        if (kod === 'CP' || kod === 'WAR' || kod === 'ASL' 
                || kod === 'JA' || kod === 'KL' || kod === 'PA'
                || kod === 'PDP'|| kod === 'PK' || kod === 'PLK' || kod === 'PM'
                || kod === 'PP' || kod === 'RP' || kod === 'WKL' || kod === 'WPA' 
                || kod === 'WS' || kod === 'JK' ) {
            var umur = $('#umur').val();
            if (jenisKp == 'B' && umur < 18) {
                alert('Umur mestilah lebih daripada 18 tahun.');
                return false;
            }
            
        }
      
      
      return true;
    }

    function validateSyer(id) {
      var val = $('#' + id).val();
      if (isNaN(val)) {
        alert('Sila masukan no sahaja!');
        $('#' + id).focus();
        $('#' + id).val('');
      }
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

    function copyAddCaw() {
      if ($('input[name=addCaw]').is(':checked')) {
        $('#suratAlamatCaw1').val($('#alamatCaw1').val());
        $('#suratAlamatCaw2').val($('#alamatCaw2').val());
        $('#suratAlamatCaw3').val($('#alamatCaw3').val());
        $('#suratAlamatCaw4').val($('#alamatCaw4').val());
        $('#suratPoskodCaw').val($('#poskodCaw').val());
        $('#suratNegeriCaw').val($('#negeriCaw').val());
      } else {
        $('#suratAlamatCaw1').val('');
        $('#suratAlamatCaw2').val('');
        $('#suratAlamatCaw3').val('');
        $('#suratAlamatCaw4').val('');
        $('#suratPoskodCaw').val('');
        $('#suratNegeriCaw').val('');

      }
    }

    function deleteWaris2(id) {
      $('#' + id).remove();
    }

    function removeCawanganPemohon(val, id) {
      if (confirm('Adakah anda pasti?')) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?deletePihakCaw&idPihakCaw=' + val + '&idPihak=' + $('#idPihak').val();
        frm = document.form1;
        frm.action = url;
        frm.submit();
      }
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

    function calAgeByDOB(value, id) {

      var year = value.substring(8, 10);

      if (year > 25 && year < 99) {//fixme :
        year = "19" + year;
      } else {
        year = "20" + year;
      }

      var age = calculateAge(value.substring(0, 2), value.substring(3, 5), year);
      $('#umur').val(age);
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
    
    function doAutoCalAgeDOB(val2) {
      var val = $('#jenisPengenalan').val();
      var val3 = $('#kod_warganegara').val();
      
      if (val == 'B' && val2 != '' && val3 == 'MAL') {
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

        var gender = tLahir.substring(10, 12);
        if (gender % 2 === 0) {// pompan
          $('#gender').val('2');
        } else {//laki
          $('#gender').val('1');
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

    function dodacheck(val, id, id2) {
      //var mikExp = /[1\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\1\|]/;
      var v = $('#' + id).val();

      if (v == 'B') {
        var strPass = val;
        var strLength = strPass.length;
        //$('#kp').attr("maxlength","12");
        if (strLength > 12) {
          var tst = val.substring(0, (strLength) - 1);
          $('#' + id2).val(tst);
        }
        var lchar = val.charAt((strLength) - 1);
        if (isNaN(lchar)) {
          //return false;
          var tst = val.substring(0, (strLength) - 1);
          $('#' + id2).val(tst);
        }
      }
    }

    function validateWarganegara(val) {
      if (val != 'MAL') {
        alert('Bukan Warganegara! Sila dapatkan surat kebenaran daripada pihak berkuasa negeri!');
      }
    }

    function visibleSyer(val) {
      var kodUrusan = $('#kodUrusan').val();
      if (val == 'PM'
              || val == 'PA' || val == 'PP' || val == 'CP'
              || val == 'PK' || val == 'RP'
              || val == 'WPA' || val == 'KL'
              || val == 'WKL' || val == 'WS'
              || val == 'JA' || val == 'PAS'
              || val == 'ROS' || val == 'JK'
              || val == 'PB' || val == 'WAR' || val == 'ASL'
              || val == 'PDP' || val == 'PLK') {
        //Add by Aizuddin to remove syer for urusan dadah
//        if (kodUrusan != 'PHADS' && kodUrusan != 'ADBSS') {
//          $('#syerTerlibat').show();
//          $('#syerTerlibat').addClass('show');
//        } else {
//          $('#syerTerlibat').hide();
//          $('#syerTerlibat').removeClass('show');
//        }
      } else {
//        $('#syerTerlibat').hide();
//        $('#syerTerlibat').removeClass('show');
      }
    }

    function visibleSyerBersama(val) {
      var kodUrusan = $('#kodUrusan').val();
      if (val == 'PA' || val == 'PP'
              || val == 'PK'
              || val == 'WS' || val == 'PM' || val == 'WKL'
              || val == 'JA' || val == 'WPA') {
        //Add by Aizuddin to remove syer for urusan dadah
        if (kodUrusan != 'PHADS' && kodUrusan != 'ADBSS') {
          $('#syerBersama').show();
          $('#syerBersama').addClass('show');
        } else {
          $('#syerBersama').hide();
          $('#syerBersama').removeClass('show');
        }
      } else {
        $('#syerBersama').hide();
        $('#syerBersama').removeClass('show');
      }
    }

</script>
<style type="text/css">
  #tdLabel {
    color:#003194;
    display:block;
    float:left;
    font-family:Tahoma;
    font-size:13px;
    font-weight:bold;
    margin-left:10px;
    margin-right:0.5em;
    text-align:right;
    width:15em;
    vertical-align:top;
  }

  #tdDisplay {
    color:black;
    font-size:13px;
    font-weight:normal;
    float:left;
    width:40em;
  }
</style>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none" alt=""/>
<div class="subtitle">
  <s:form beanclass="etanah.view.daftar.PihakKepentinganAction" name="form1">
    <s:hidden name="kodUrusan" id="kodUrusan" value="${actionBean.permohonan.kodUrusan.kod}"/>
    <s:hidden name="pihak.idPihak" id="idPihak"/>
    <s:hidden name="idHakmilik"/>
    <fieldset class="aras1">
      <legend>Kemasukan Pihak Berkepentingan</legend>
      <em>Semua yang bertanda * adalah wajib dimasukkan.</em>
      <c:if test="${moreThanOneHakmilik}">
        <p>
          <label>&nbsp;</label>
          <font color="red" size="2">
            <input type="checkbox" name="copyToAll" value="1"/>
            <em>Sila klik jika sama untuk semua hakmilik.</em>
          </font>
        </p>
      </c:if>
      <p>
        <label>Nama :</label> <s:text name="pihak.nama" onkeyup="this.value = this.value.toUpperCase();" size="40"/>
      </p>
      <p>
        <label for="nama"><font color="red">*</font>Jenis Pihak Berkepentingan :</label>
            <s:select name="jenisPihak" style="width:270px" id="jenis_pihak" 
                      onchange="visibleSyer(this.value);visibleSyerBersama(this.value);">
              <c:if test="${fn:length(actionBean.senaraiKodPenerima) > 1}">
                <s:option value="">Sila Pilih</s:option>
          </c:if>
          <s:options-collection collection="${actionBean.senaraiKodPenerima}" label="nama" value="kod" />
        </s:select>
      </p>

      <%--<c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMT'
                    || actionBean.permohonan.kodUrusan.kod eq 'PMP'
                    || actionBean.permohonan.kodUrusan.kod eq 'PMTM'
                    || actionBean.permohonan.kodUrusan.kod eq 'PNPA'
                    || actionBean.permohonan.kodUrusan.kod eq 'PNPAB'
                    || actionBean.permohonan.kodUrusan.kod eq 'PJT'
                    || actionBean.permohonan.kodUrusan.kod eq 'PJTM'
                    || actionBean.permohonan.kodUrusan.kod eq 'TA'
                    || actionBean.permohonan.kodUrusan.kod eq 'TENBT'
                    || actionBean.permohonan.kodUrusan.kod eq 'IS'
                    || actionBean.permohonan.kodUrusan.kod eq 'ISBLS'
                    || actionBean.permohonan.kodUrusan.kod eq 'KVAB'
                    || actionBean.permohonan.kodUrusan.kod eq 'KVAS'
                    || actionBean.permohonan.kodUrusan.kod eq 'KVAT'
                    || actionBean.permohonan.kodUrusan.kod eq 'KVLS'
                    || actionBean.permohonan.kodUrusan.kod eq 'KVLT'
                    || actionBean.permohonan.kodUrusan.kod eq 'KVLTB'
                    || actionBean.permohonan.kodUrusan.kod eq 'KVPB'
                    || actionBean.permohonan.kodUrusan.kod eq 'KVPK'
                    || actionBean.permohonan.kodUrusan.kod eq 'KVPPT'
                    || actionBean.permohonan.kodUrusan.kod eq 'KVPS'
                    || actionBean.permohonan.kodUrusan.kod eq 'KVPT'
                    || actionBean.permohonan.kodUrusan.kod eq 'JAGAB'
                    || actionBean.permohonan.kodUrusan.kod eq 'JDGD'
                    || actionBean.permohonan.kodUrusan.kod eq 'JDS'
                    || actionBean.permohonan.kodUrusan.kod eq 'JMGD'
                    || actionBean.permohonan.kodUrusan.kod eq 'JPGD'
                    || actionBean.permohonan.kodUrusan.kod eq 'JPGPJ'
                    || actionBean.permohonan.kodUrusan.kod eq 'KVSS'
                    || actionBean.permohonan.kodUrusan.kod eq 'KVST'
                    || actionBean.permohonan.kodUrusan.kod eq 'PMHUK'
                    || actionBean.permohonan.kodUrusan.kod eq 'PMHUN'
                    || actionBean.permohonan.kodUrusan.kod eq 'PMKMH'
                    || actionBean.permohonan.kodUrusan.kod eq 'GD'
                    || actionBean.permohonan.kodUrusan.kod eq 'GDCE'
                    || actionBean.permohonan.kodUrusan.kod eq 'PPBM'
                    || actionBean.permohonan.kodUrusan.kod eq 'PPUH'
                    || actionBean.permohonan.kodUrusan.kod eq 'PPUHB'
                    || actionBean.permohonan.kodUrusan.kod eq 'PHMMT'
                    || actionBean.permohonan.kodUrusan.kod eq 'PJBT'
                    || actionBean.permohonan.kodUrusan.kod eq 'PJKBT'
                    || actionBean.permohonan.kodUrusan.kod eq 'TMAMD'
                    || actionBean.permohonan.kodUrusan.kod eq 'TMAME'
                    || actionBean.permohonan.kodUrusan.kod eq 'TMAMF'
                    || actionBean.permohonan.kodUrusan.kod eq 'TMAMG'
                    || actionBean.permohonan.kodUrusan.kod eq 'TMAML'
                    || actionBean.permohonan.kodUrusan.kod eq 'TMAMT'
                    || actionBean.permohonan.kodUrusan.kod eq 'TMAMW'
                    || actionBean.permohonan.kodUrusan.kod eq 'TN'
                    || actionBean.permohonan.kodUrusan.kod eq 'TRPA'
            }">
          
      </c:if>--%>
      <p id="syerTerlibat" style="display:none">
        <label><font color="red">*</font>Syer Terlibat :</label>
        <s:text name="syerPembilang" id="syerPembilang" onchange="validateSyer(this.id);" value="${bakiSyerPembilang}"/> /
        <s:text name="syerPenyebut" id="syerPenyebut" onchange="validateSyer(this.id);" value="${bakiSyerPenyebut}"/>
      </p>
      <p id="syerBersama" style="display:none">
        <label><font color="red">*</font>Syer Bersama :</label>
            <s:select name="syerKongsi" id="syerKongsi">
              <s:option value="">Sila Pilih</s:option>
          <s:option value="Y">Ya</s:option>
          <s:option value="T">Tidak</s:option>
        </s:select>
      </p>
      <p>
        <label><font color="red">*</font>Jenis Pengenalan :</label>
            <s:select name="pihak.jenisPengenalan.kod" id="jenisPengenalan" style="width:200px" onchange="validateForTiada()">
              <s:option value="">Sila Pilih</s:option>
          <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
        </s:select>
      </p> 
      <c:if test="${newPihak || actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L'
                    || actionBean.pihak.jenisPengenalan.kod eq 'P'
                    || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">

          <p class="individu">
              <label><em>*</em>Kewarganegaraan :</label>
              <s:select name="pihak.wargaNegara.kod" style="width:200px" 
                        id="kod_warganegara" onchange="validateWarganegara(this.value);">
                  <s:option value="">Sila Pilih</s:option>
                  <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
              </s:select>
          </p>


      </c:if>
      <p>
          <label id="labelNoPengenalan"><font color="red" class="noPengenalan">*</font>No Pengenalan :</label> 
            <s:text name="pihak.noPengenalan" id="noPengenalan" size="30"                        
                    onchange="doAutoCalAgeDOB(this.value);"/>
      </p>

      <c:if test="${newPihak || actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L'
                    || actionBean.pihak.jenisPengenalan.kod eq 'P'
                    || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">

            <p class="individu">
              <label>Tarikh Lahir :</label>
              <s:text name="tarikhLahir" size="10" id="trhLahir" class="datepicker" onchange="calAgeByDOB(this.value);"/>
            </p>
            <p class="individu">
              <label>Umur :</label>
              <s:text name="permohonanPihak.umur" size="10" maxlength="3" id="umur" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p class="individu">
              <label>Jantina :</label>
              <s:select name="pihak.kodJantina" style="width:200px" id="gender">
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${list.senaraiKodJantina}" label="nama" value="kod"/>
              </s:select>
            </p>            
            <p class="individu">
              <label>Tempat Lahir :</label>
              <s:select name="pihak.negeriKelahiran.kod" style="width:200px" id="tLahir">
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${list.senaraiKodNegeriAktif}" label="nama" value="kod" sort="nama"/>
              </s:select>
            </p>            
            <p class="individu">
              <label>Status Perkahwinan :</label>
              <s:select name="permohonanPihak.statusKahwin" style="width:200px">
                <s:option value="">Sila Pilih</s:option>
                <s:option>Berkahwin</s:option>
                <s:option>Bujang</s:option>
                <s:option>Duda</s:option>
                <s:option>Ibu Tunggal</s:option>
              </s:select>
            </p>
            <p class="individu">
              <label>Pekerjaan :</label>
              <s:text name="permohonanPihak.pekerjaan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p class="individu">
              <label>Pendapatan Bulanan (RM) :</label>
              <s:text name="permohonanPihak.pendapatan" size="40" maxlength="12" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p class="individu">
              <label>Tanggungan :</label>
              <s:text name="permohonanPihak.tangungan" size="40" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
            </p>                     
      </c:if>
      <c:if test="${newPihak || actionBean.pihak.jenisPengenalan.kod eq 'D' || actionBean.pihak.jenisPengenalan.kod eq 'S'
                    || actionBean.pihak.jenisPengenalan.kod eq 'U'|| actionBean.pihak.jenisPengenalan.kod eq 'N' }">
            <p class="syarikat">
              <label>Daftar Pertubuhan :</label>
              <s:select name="pihak.penubuhanSyarikat.kod" id="daftarTubuh">
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${list.senaraiKodPenubuhanSyarikat}" label="nama" value="kod"/>
              </s:select>
            </p>

      </c:if>
      <p>
        <label>Jenis Syarikat / Bangsa :</label>
        <s:select name="pihak.bangsa.kod" id="bangsa">
          <s:option value="">Sila Pilih</s:option>
          <s:options-collection collection="${list.senaraiKodBangsa}" label="nama" value="kod"/>
        </s:select>
      </p>

      <p>
        <label>Hubungan :</label>
        <s:select name="permohonanPihak.kaitan" style="width:200px">
          <s:option value="LAIN-LAIN" selected="true">LAIN-LAIN</s:option>
          <s:option value="IBU BAPA KEPADA ANAK">IBU BAPA KEPADA ANAK</s:option>
          <s:option value="ANAK KEPADA IBU BAPA">ANAK KEPADA IBU BAPA</s:option>
          <s:option value="SUAMI KEPADA ISTERI">SUAMI KEPADA ISTERI</s:option>
          <s:option value="ISTERI KEPADA SUAMI">ISTERI KEPADA SUAMI</s:option>
          <s:option value="SAUDARA-MARA">SAUDARA-MARA</s:option>
          <s:option value="PENJUAL / PEMBELI">PENJUAL / PEMBELI</s:option>                    
        </s:select>
      </p>
      <c:if test="${!empty SAB}">
        <p>
          <label>No Surat Amanah :</label>
          <s:text name="permohonanPihak.dalamanNilai1" size="40"/>
        </p>
      </c:if>
      <%--<c:set var="disabled" value="disabled"/>--%>
      <%--<c:if test="${newPihak}">--%>
      <c:set var="disabled" />
      <%--</c:if>--%>
      <p><label> &nbsp;</label>&nbsp;</p>
      <p>
        <label for="alamat"><font color="red">*</font>Alamat Berdaftar :</label>
            <s:text name="pihak.alamat1" id="alamat1" disabled="${disabled}" size="80" maxlength="80" onkeyup="this.value=this.value.toUpperCase();"/>
      </p>
      <p>
        <label> &nbsp;</label>
        <s:text name="pihak.alamat2" id="alamat2" disabled="${disabled}" size="80" maxlength="80" onkeyup="this.value=this.value.toUpperCase();"/>
      </p>
      <p>
        <label> &nbsp;</label>
        <s:text name="pihak.alamat3" id="alamat3" disabled="${disabled}" size="80" maxlength="80" onkeyup="this.value=this.value.toUpperCase();"/>
      </p>
      <p>
        <label>Bandar :</label>
        <s:text name="pihak.alamat4" id="alamat4" disabled="${disabled}" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
      </p>
      <p>
        <label>Poskod :</label>
        <s:text name="pihak.poskod" id="poskod" disabled="${disabled}" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
      </p>

      <p>
        <label for="Negeri">Negeri :</label>
        <s:select name="pihak.negeri.kod" id="negeri" disabled="${disabled}" style="width:200px">
          <s:option value="">Sila Pilih</s:option>
          <s:options-collection collection="${list.senaraiKodNegeriAktif}" label="nama" value="kod"/>
        </s:select>
      </p>
      <p><label> &nbsp;</label>&nbsp;</p>
      <p>
        <label>&nbsp;</label>
        <input type="checkbox" id="add1" name="add1" value="" onclick="copyAdd();"/>
        <em>Alamat surat-menyurat sama dengan alamat berdaftar.</em>
      </p>
      <p>
        <label for="alamat">Alamat Surat-Menyurat :</label>
        <s:text name="pihakAlamatTamb.alamatKetiga1" id="suratAlamat1" size="80" maxlength="80" onkeyup="this.value=this.value.toUpperCase();"/>
      </p>
      <p>
        <label> &nbsp;</label>
        <s:text name="pihakAlamatTamb.alamatKetiga2" id="suratAlamat2" size="80" maxlength="80" onkeyup="this.value=this.value.toUpperCase();"/>
      </p>
      <p>
        <label> &nbsp;</label>
        <s:text name="pihakAlamatTamb.alamatKetiga3" id="suratAlamat3" size="80" maxlength="80" onkeyup="this.value=this.value.toUpperCase();"/>
      </p>
      <p>
        <label>Bandar :</label>
        <s:text name="pihakAlamatTamb.alamatKetiga4" id="suratAlamat4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
      </p>
      <p>
        <label>Poskod :</label>
        <s:text name="pihakAlamatTamb.alamatKetigaPoskod" id="suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
      </p>
      <p>
        <label for="Negeri">Negeri :</label>
        <s:select name="pihakAlamatTamb.alamatKetigaNegeri.kod" id="suratNegeri" style="width:200px">
          <s:option value="">Sila Pilih</s:option>
          <s:options-collection collection="${list.senaraiKodNegeriAktif}" label="nama" value="kod"/>
        </s:select>
      </p>            
      <br/>

      <c:if test="${actionBean.pihak.jenisPengenalan.kod ne 'B' && actionBean.pihak.jenisPengenalan.kod ne 'L'
                    && actionBean.pihak.jenisPengenalan.kod ne 'P'
                    && actionBean.pihak.jenisPengenalan.kod ne 'T' && actionBean.pihak.jenisPengenalan.kod ne 'I'}">

            <div class="content" align="center" id="tableCaw">
              <span class=instr><em>Sila klik pada imej <img src='${pageContext.request.contextPath}/images/tambah.png'/> untuk menambah cawangan baru.</em></span><br/>
              <br>
              Maklumat Cawangan                      
              <display:table class="tablecloth" name="${actionBean.senaraiCawangan}" cellpadding="0" cellspacing="0"
                             id="cawTable" pagesize="10"
                             requestURI="${pageContext.request.contextPath}/daftar/pihak_kepentingan" style="width:90%">
                <display:column title="Pilih">
                  <s:radio name="pilih" value="${cawTable.idPihakCawangan}"/>
                  <input type='hidden' name="" id="idCaw_${cawTable_rowNum-1}" value="${cawTable.idPihakCawangan}"/>
                  <input type="hidden" id="row"/>
                </display:column>
                <display:column title="Nama">
                  <input type='hidden' name="" id="namaCaw_${cawTable_rowNum-1}" value="${cawTable.namaCawangan}"/> ${cawTable.namaCawangan}
                </display:column>
                <display:column title="Alamat1">
                  <input type='hidden' name="" id="addSurat1Caw_${cawTable_rowNum-1}" value="${cawTable.suratAlamat1}"/>
                  <input type='hidden' name="" id="add1Caw_${cawTable_rowNum-1}" value="${cawTable.alamat1}"/>
                  ${cawTable.suratAlamat1}
                </display:column>
                <display:column title="Alamat2">
                  ${cawTable.suratAlamat2}
                  <input type='hidden' name="" id="addSurat2Caw_${cawTable_rowNum-1}" value="${cawTable.suratAlamat2}"/>
                  <input type='hidden' name="" id="add2Caw_${cawTable_rowNum-1}" value="${cawTable.alamat2}"/>
                </display:column>
                <display:column title="Alamat3">
                  ${cawTable.suratAlamat3}
                  <input type='hidden' name="" id="addSurat3Caw_${cawTable_rowNum-1}" value="${cawTable.suratAlamat3}"/>
                  <input type='hidden' name="" id="add3Caw_${cawTable_rowNum-1}" value="${cawTable.alamat3}"/>
                </display:column>
                <display:column title="Alamat4">
                  ${cawTable.suratAlamat4}
                  <input type='hidden' name="" id="addSurat4Caw_${cawTable_rowNum-1}" value="${cawTable.suratAlamat4}"/>
                  <input type='hidden' name="" id="add4Caw_${cawTable_rowNum-1}" value="${cawTable.alamat4}"/>
                </display:column>
                <display:column title="Poskod">
                  ${cawTable.suratPoskod}
                  <input type='hidden' name="" id="addSuratPoskodCaw_${cawTable_rowNum-1}" value="${cawTable.suratPoskod}"/>
                  <input type='hidden' name="" id="addPoskodCaw_${cawTable_rowNum-1}" value="${cawTable.poskod}"/>
                </display:column>
                <display:column title="Negeri">
                  ${cawTable.suratNegeri.nama}
                  <input type='hidden' name="" id="addSuratNegeriCaw_${cawTable_rowNum-1}" value="${cawTable.suratNegeri.kod}"/>
                  <input type='hidden' name="" id="addNegeriCaw_${cawTable_rowNum-1}" value="${cawTable.negeri.kod}"/>
                </display:column>
                <display:column title="Kemaskini">
                  <div align="center">
                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                         id='edit_caw_${line_rowNum}' onclick="editCawanganPemohon('${cawTable.idPihakCawangan}', this.id, '${cawTable_rowNum-1}');"
                         onmouseover="this.style.cursor = 'pointer';">
                  </div>
                </display:column>
                <display:column title="Hapus 1">
                  <div align="center">
                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                         id='rem_caw_${line_rowNum}' onclick="removeCawanganPemohon('${cawTable.idPihakCawangan}', this.id)"
                         onmouseover="this.style.cursor = 'pointer';">
                  </div>
                </display:column>
                <display:column title="<img alt='Tambah Cawangan' id='addCaw'
                                src='${pageContext.request.contextPath}/images/tambah.png' onmouseover=\"this.style.cursor='pointer';\"/>"/>
              </display:table>
              <br>
            </div>
      </c:if>            
      <p>
      <div id="pgAmanah" style="display: none" align="center">
        <span class=instr><em>Sila klik pada imej  <img src='${pageContext.request.contextPath}/images/tambah.png'/> untuk menambah waris.</em></span><br/>
        <div align="center">
          <display:table class="tablecloth" name="${actionBean.senaraiPihakHubungan}" cellpadding="0" cellspacing="0"
                         id="pgAmanahTable" pagesize="10"
                         requestURI="${pageContext.request.contextPath}/daftar/pihak_kepentingan">
            <%--<display:column title="Bil" sortable="true">${line_rowNum}</display:column>--%>
            <display:column title="Nama" class="nama">
              <input type='hidden' name="" id="idWaris_${pgAmanahTable_rowNum-1}" value="${pgAmanahTable.idHubungan}"/>
              <input type='hidden' name="" id="namaWaris_${pgAmanahTable_rowNum-1}" value="${pgAmanahTable.nama}"/>
              ${pgAmanahTable.nama}
            </display:column>
            <display:column title="Jenis Pengenalan">
              <input type='hidden' name="" id="jenisPengenalanWaris_${pgAmanahTable_rowNum-1}" value="${pgAmanahTable.jenisPengenalan.kod}"/>
              ${pgAmanahTable.jenisPengenalan.nama}
            </display:column>
            <display:column title="Nombor Pengenalan">
              <input type='hidden' name="" id="noPengenalanWaris_${pgAmanahTable_rowNum-1}" value="${pgAmanahTable.noPengenalan}"/>
              ${pgAmanahTable.noPengenalan}
            </display:column>
            <display:column title="Warganegara">
              <input type='hidden' name="" id="wargaWaris_${pgAmanahTable_rowNum-1}" value="${pgAmanahTable.wargaNegara.kod}"/>
              ${pgAmanahTable.wargaNegara.nama}
            </display:column>
            <display:column title="Alamat 1">
              <input type='hidden' name="" id="add1Waris_${pgAmanahTable_rowNum-1}" value="${pgAmanahTable.alamat1}"/>
              ${pgAmanahTable.alamat1}
            </display:column>
            <display:column title="Alamat 2">
              <input type='hidden' name="" id="add2Waris_${pgAmanahTable_rowNum-1}" value="${pgAmanahTable.alamat2}"/>
              ${pgAmanahTable.alamat2}
            </display:column>
            <display:column title="Alamat 3">
              <input type='hidden' name="" id="add3Waris_${pgAmanahTable_rowNum-1}" value="${pgAmanahTable.alamat3}"/>
              ${pgAmanahTable.alamat3}
            </display:column>
            <display:column title="Bandar">
              <input type='hidden' name="" id="add4Waris_${pgAmanahTable_rowNum-1}" value="${pgAmanahTable.alamat4}"/>
              ${pgAmanahTable.alamat4}
            </display:column>
            <display:column title="Poskod">
              <input type='hidden' name="" id="poskodWaris_${pgAmanahTable_rowNum-1}" value="${pgAmanahTable.poskod}"/>
              ${pgAmanahTable.poskod}
            </display:column>
            <display:column title="Negeri">
              <input type='hidden' name="" id="negeriWaris_${pgAmanahTable_rowNum-1}" value="${pgAmanahTable.negeri.kod}"/>
              ${pgAmanahTable.negeri.nama}
            </display:column>
            <display:column title="Syer">
              <input type='hidden' name="" id="syer1Waris_${pgAmanahTable_rowNum-1}" value="${pgAmanahTable.syerPembilang}"/>
              <input type='hidden' name="" id="syer2Waris_${pgAmanahTable_rowNum-1}" value="${pgAmanahTable.syerPenyebut}"/>
              ${pgAmanahTable.syerPembilang} / ${pgAmanahTable.syerPenyebut}
            </display:column>
            <%--  <display:column title="Kemaskini">
                   <div align="center">
                       <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                            id='edit_caw_${line_rowNum}' onclick="editWaris('${pgAmanahTable_rowNum-1}');"
                            onmouseover="this.style.cursor='pointer';">
                   </div>
               </display:column>--%>
            <display:column title="Hapus"></display:column>
            <display:column title="<img alt='Tambah Waris' id='addPA'
                            src='${pageContext.request.contextPath}/images/tambah.png' onmouseover=\"this.style.cursor='pointer';\"/>"/>
          </display:table>
        </div>
        <br/>
        <%--<p><label>&nbsp;</label><s:button name="" value="Tambah" class="btn" id="addPA"/></p>--%>
      </div>
      </p>
      <div id="pgAmanahDialog" style="display: none" align="left" title="Tambah Waris">

        <!--                :: EDIT VIEW ONLY ::   -->
        <table border="0">
          <tr><td>&nbsp;</td></tr>
          <tr>
            <td id="tdLabel"><font color="red">*</font>Nama :</td>
            <td id="tdDisplay">
              <s:hidden name="permohonanPihakHubungan.idHubungan" id="idWaris"/>
              <s:text name="permohonanPihakHubungan.nama" id="namaAmanah" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </td>
          </tr>
          <tr>
            <td id="tdLabel"<font color="red">*</font>Jenis Pengenalan :</td>
            <td id="tdDisplay">
              <s:select name="permohonanPihakHubungan.jenisPengenalan.kod" id="kpAmanah">
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
              </s:select>
            </td>
          </tr>
          <tr>
            <td id="tdLabel"><font color="red">*</font>Nombor Pengenalan :</td>
            <td id="tdDisplay">
              <s:text name="permohonanPihakHubungan.noPengenalan" id="nokpAmanah" size="40" 
                      onkeyup="dodacheck(this.value,'kpAmanah', this.id);this.value=this.value.toUpperCase();"/>
            </td>
          </tr>
          <tr>
            <td id="tdLabel">Warganegara :</td>
            <td id="tdDisplay">
              <s:select name="permohonanPihakHubungan.wargaNegara.kod" style="width:200px" id="warganegaraAmanah">
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
              </s:select>
            </td>
          </tr>
          <tr>
            <td id="tdLabel">Alamat :</td>
            <td id="tdDisplay">
              <s:text name="permohonanPihakHubungan.alamat1" size="80" id="alamatAmanah1" 
                      maxlength="80" onkeyup="this.value=this.value.toUpperCase();"/>
            </td>
          </tr>
          <tr>
            <td id="tdLabel"> &nbsp;</td>
            <td id="tdDisplay">
              <s:text name="permohonanPihakHubungan.alamat2" size="80" id="alamatAmanah2" 
                      maxlength="80" onkeyup="this.value=this.value.toUpperCase();"/>
            </td>
          </tr>
          <tr>
            <td id="tdLabel">&nbsp;</td>
            <td id="tdDisplay">
              <s:text name="permohonanPihakHubungan.alamat3" size="80" id="alamatAmanah3" 
                      maxlength="80" onkeyup="this.value=this.value.toUpperCase();"/>
            </td>
          </tr>          
          <tr>
            <td id="tdLabel">Bandar :</td>
            <td id="tdDisplay">
              <s:text name="permohonanPihakHubungan.alamat4" size="40" id="alamatAmanah4" 
                      maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </td>
          </tr> 
          <tr>
            <td id="tdLabel">Poskod :</td>
            <td id="tdDisplay">
              <s:text name="permohonanPihakHubungan.poskod" size="40" maxlength="5" 
                      id="poskodAmanah" onkeyup="validateNumber(this,this.value);"/>
            </td>
          </tr> 
          <tr>
            <td id="tdLabel">Negeri :</td>
            <td id="tdDisplay">
              <s:select name="permohonanPihakHubungan.negeri.kod" id="negeriAmanah">
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${list.senaraiKodNegeriAktif}" label="nama" value="kod"/>
              </s:select>
            </td>
          </tr> 
          <tr>
            <td id="tdLabel">Syer :</td>
            <td id="tdDisplay">
              <s:text name="syerPembilang" size="5" id="syerPembilangAmanah"/> /
              <s:text name="syerPenyebut" size="5" id="syerPenyebutAmanah"/>
            </td>
          </tr> 
        </table>
        <!--                :: EDIT VIEW END ::    -->
        <%--
        <p align="left">
          <label for="nama"><font color="red">*</font>Nama :</label>
              <s:hidden name="permohonanPihakHubungan.idHubungan" id="idWaris"/>
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
              <s:text name="permohonanPihakHubungan.noPengenalan" id="nokpAmanah" size="40" 
                      onkeyup="dodacheck(this.value,'kpAmanah', this.id);this.value=this.value.toUpperCase();"/>
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
          <label>Poskod :</label>
          <s:text name="permohonanPihakHubungan.poskod" size="40" maxlength="5" id="poskodAmanah" onkeyup="validateNumber(this,this.value);"/>
        </p>
        <p>
          <label>Bandar :</label>
          <s:text name="permohonanPihakHubungan.alamat4" size="40" id="alamatAmanah4" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
        </p>                
        <p>
          <label for="Negeri">Negeri :</label>
          <s:select name="permohonanPihakHubungan.negeri.kod" id="negeriAmanah">
            <s:option value="">Sila Pilih</s:option>
            <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
          </s:select>
        </p>
        <p>
          <label for="Negeri">Syer :</label>
          <s:text name="syerPembilang" size="5" id="syerPembilangAmanah"/> /
          <s:text name="syerPenyebut" size="5" id="syerPenyebutAmanah"/>
        </p>--%>
        <br>
      </div>

      <div id="cawDialog" style="display: none" align="left" title="Tambah Cawangan">

        <!--============ Edit view only ========-->
        <table border ="0" >
          <tr><td>&nbsp;</td></tr>
          <tr>
            <td id="tdLabel"><font color="red">*</font> Nama : </td>    
            <td id="tdDisplay">
              <s:hidden name="pihakCawangan.idPihakCawangan" id="idCaw"/>
              <s:text name="pihakCawangan.namaCawangan" id="namaCaw" size="40" 
                      onkeyup="this.value=this.value.toUpperCase();"/>
            </td>
          </tr>
          <tr>
            <td id="tdLabel"><font color="red">*</font>Alamat Berdaftar :</label></td>
            <td id="tdDisplay">
              <s:text name="pihakCawangan.alamat1" id="alamatCaw1" size="80" maxlength="80" 
                      onkeyup="this.value=this.value.toUpperCase();"/>
            </td>
          </tr>
          <tr>
            <td id="tdLabel">&nbsp;</td>
            <td id="tdDisplay">
              <s:text name="pihakCawangan.alamat2" id="alamatCaw2" size="80" maxlength="80" onkeyup="this.value=this.value.toUpperCase();"/>
            </td>
          </tr>
          <tr>
            <td id="tdLabel">&nbsp;</td>
            <td id="tdDisplay">
              <s:text name="pihakCawangan.alamat3" id="alamatCaw3" size="80" maxlength="80" onkeyup="this.value=this.value.toUpperCase();"/>
            </td>
          </tr>
          <tr>
            <td id="tdLabel">Bandar :</td>
            <td id="tdDisplay">
              <s:text name="pihakCawangan.alamat4" id="alamatCaw4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </td>
          </tr> 
          <tr>
            <td id="tdLabel">Poskod :</td>
            <td id="tdDisplay">
              <s:text name="pihakCawangan.poskod" size="40" id="poskodCaw" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
            </td>
          </tr>
          <tr>
            <td id="tdLabel"><font color="red">*</font>Negeri :</label></td>
            <td id="tdDisplay">
              <s:select name="pihakCawangan.negeri.kod" id="negeriCaw">
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${list.senaraiKodNegeriAktif}" label="nama" value="kod"/>
              </s:select>
            </td>
          </tr> 
          <tr>
            <td id="tdLabel">&nbsp;</td>
            <td id="tdDisplay">
              <input type="checkbox" id="addCaw" name="addCaw" value="" onclick="copyAddCaw();"/>
              <em>Alamat surat-menyurat sama dengan alamat berdaftar.</em>
            </td>
          </tr>
          <tr>
            <td id="tdLabel">Alamat Surat-Menyurat :</td>
            <td id="tdDisplay">
              <s:text name="pihakCawangan.suratAlamat1" id="suratAlamatCaw1" size="80" maxlength="80" onkeyup="this.value=this.value.toUpperCase();"/>
            </td>
          </tr>    
          <tr>
            <td id="tdLabel">&nbsp;</td>
            <td id="tdDisplay">
              <s:text name="pihakCawangan.suratAlamat2" id="suratAlamatCaw2" size="80" maxlength="80" onkeyup="this.value=this.value.toUpperCase();"/>
            </td>
          </tr> 
          <tr>
            <td id="tdLabel">&nbsp;</td>
            <td id="tdDisplay">
              <s:text name="pihakCawangan.suratAlamat3" id="suratAlamatCaw3" size="80" maxlength="80" onkeyup="this.value=this.value.toUpperCase();"/>
            </td>
          </tr>
          <tr>
            <td id="tdLabel">Bandar :</td>
            <td id="tdDisplay">
              <s:text name="pihakCawangan.suratAlamat4" id="suratAlamatCaw4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </td>
          </tr>
          <tr>
            <td id="tdLabel">Poskod :</td>
            <td id="tdDisplay">
              <s:text name="pihakCawangan.suratPoskod" id="suratPoskodCaw" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
            </td>
          </tr>
          <tr>
            <td id="tdLabel">Negeri :</td>
            <td id="tdDisplay">
              <s:select name="pihakCawangan.suratNegeri.kod" id="suratNegeriCaw">
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${list.senaraiKodNegeriAktif}" label="nama" value="kod"/>
              </s:select>
            </td>
          </tr>
          <tr>
            <td id="tdLabel">Nombor Telefon :</td>
            <td id="tdDisplay">
              <s:text name="pihakCawangan.noTelefon1" id="telCaw" size="40" maxlength="12" onkeyup="validateNumber(this,this.value);"/>
            </td>
          </tr>                    
        </table>
        <!--edit END-->
        <%--
        <p>
            <label for="nama"><font color="red">*</font>Nama :</label>
            <s:hidden name="pihakCawangan.idPihakCawangan" id="idCaw"/>
            <s:text name="pihakCawangan.namaCawangan" id="namaCaw" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
        </p>
        <p>
            <label for="alamat"><font color="red">*</font>Alamat Berdaftar :</label>
            <s:text name="pihakCawangan.alamat1" id="alamatCaw1" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
        </p>
        <p>
            <label> &nbsp;</label>
            <s:text name="pihakCawangan.alamat2" id="alamatCaw2" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
        </p>
        <p>
            <label> &nbsp;</label>
            <s:text name="pihakCawangan.alamat3" id="alamatCaw3" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
        </p>
        <p>
            <label>Bandar :</label>
            <s:text name="pihakCawangan.alamat4" id="alamatCaw4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
        </p>
        <p>
            <label>Poskod :</label>
            <s:text name="pihakCawangan.poskod" size="40" id="poskodCaw" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
        </p>
        <p>
            <label for="Negeri"><font color="red">*</font>Negeri :</label>
            <s:select name="pihakCawangan.negeri.kod" id="negeriCaw">
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
            </s:select>

                </p>
                <p>
                    <label>&nbsp;</label>
                    <input type="checkbox" id="addCaw" name="addCaw" value="" onclick="copyAddCaw();"/>
                    <em>Alamat surat-menyurat sama dengan alamat berdaftar.</em>
                </p>
                <p>
                    <label for="alamat">Alamat Surat-Menyurat :</label>
                    <s:text name="pihakCawangan.suratAlamat1" id="suratAlamatCaw1" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.suratAlamat2" id="suratAlamatCaw2" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.suratAlamat3" id="suratAlamatCaw3" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>Bandar :</label>
                    <s:text name="pihakCawangan.suratAlamat4" id="suratAlamatCaw4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>Poskod :</label>
                    <s:text name="pihakCawangan.suratPoskod" id="suratPoskodCaw" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                    <label for="Negeri">Negeri :</label>
                    <s:select name="pihakCawangan.suratNegeri.kod" id="suratNegeriCaw">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p>
                    <label for="alamat">Nombor Telefon :</label>
                    <s:text name="pihakCawangan.noTelefon1" id="telCaw" size="40" maxlength="12" onkeyup="validateNumber(this,this.value);"/>
                </p>                         
        --%>    
      </div>
      <p>
        <!--<label><s:submit name="showSearchForm" class="btn" value="Carian Semula"/>&nbsp;</label>-->
      <center>
        <s:button name="savePihakPopup" class="btn" value="Simpan" onclick="save(this.name, this.form);"/>&nbsp;
        <s:button name="close" id="tutup" value="Tutup" class="btn" onclick="self.close()"/> &nbsp;
        </center>
      </p>
      <br/>
    </fieldset>        
  </s:form>
</div>