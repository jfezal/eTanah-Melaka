<%-- 
    Document   : kutipan_data_tambahUrusan
    Created on : Oct 7, 2013, 11:55:20 AM
    Author     : ei
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquery.validate.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/date.format.js"></script>
<script type="text/javascript">
  $(document).ready(function() {
  <c:if test="${actionBean.refresh}">
//    alert("refresh");
//    self.close();
//    opener.viewPihak($("#id").val(), $("#jenisUrusan").val());
    doBlockUI();
    viewPihak($("#id").val(), $("#jenisUrusan").val());
  </c:if> 
  <c:choose>
    <c:when test="${actionBean.pihak.idPihak ne null}">
      $("#maklumatPemohon").hide();
      $("#pemohonDetail").show();
    </c:when>
    <c:otherwise>
        $("#maklumatPemohon").show();
        $("#pemohonDetail").hide();
    </c:otherwise>
  </c:choose>
  <c:if test="${actionBean.permohonanPihak ne null }">
      $("#maklumatPemohon").hide()
  </c:if>
      $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
      $('#id_serah').hide();
      $("#kodUrusan").blur(function() {
        var valueUrusan = $("#kodUrusan").val();
        $("#namaUrusan").val(valueUrusan);
        if ($("#namaUrusan").val() === "") {
          alert("Sila Masukkan Kod Urusan Yang Betul");
          $("#kodUrusan").val("");
        }
        $("#hiddenkodUrusan").val(valueUrusan);
      });
      $("#namaUrusan").blur(function() {
        var valueUrusan = $("#namaUrusan").val();
        $("#kodUrusan").val(valueUrusan);
        $("#hiddenkodUrusan").val(valueUrusan);
      });
      $("#cariPemohon").click(function() {
        doBlockUI();
      });
      $('#simpanPemegang').click(function() {
        doBlockUI();
      });
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

      $('#tik2').click(function() {
        if ($('#tik2').is(':checked')) {
          $("#alamatSurat1Kemaskini").val($("#alamat1Kemaskini").val());
          $("#alamatSurat2Kemaskini").val($("#alamat2Kemaskini").val());
          $("#alamatSurat3Kemaskini").val($("#alamat3Kemaskini").val());
          $("#alamatSurat4Kemaskini").val($("#alamat4Kemaskini").val());
          $("#poskodSuratKemaskini").val($("#poskodKemaskini").val());
          $("#suratNegeriKemaskini").val($("#negeriKemaskini").val());
        } else if ($('#tik2').is(':unchecked')) {
          $("#alamatSurat1Kemaskini").val("");
          $("#alamatSurat2Kemaskini").val("");
          $("#alamatSurat3Kemaskini").val("");
          $("#alamatSurat4Kemaskini").val("");
          $("#poskodSuratKemaskini").val("");
          $("#suratNegeriKemaskini").val("");
        }
      });

      $('#tambahPemegang').click(function() {
        $("#maklumatPemohon").hide();
        $("#pemohonDetail").show();
        $("#kemaskiniPemohon").hide();
      });


    });

    function selectAllPihak(a) {

      var size = '${fn:length(actionBean.listHakmilikPihak)}';
      for (i = 0; i < size; i++) {
        var c = document.getElementById("checkboxpihak" + i);
        if (c === null)
          break;
        c.checked = a.checked;
      }
    }

    function pilihPihak(val) {
      doBlockUI();
      var idPihak = val;
      var url = '${pageContext.request.contextPath}/daftar/utiliti/kutipanData?cariPemohon&idPihak=' + idPihak;
      frm = document.tambahUrusan;
      frm.action = url;
      frm.submit();
    }

    function addNew() {
      doBlockUI();
      var param = '';
      $('.pemilikTerlibat').each(function(index) {
        var a = $('#checkboxpihak' + index).is(":checked");
        if (a) {
          param = param + '&idP=' + $('#checkboxpihak' + index).val();
//          alert(param);
        }
      });

      if (param === '') {
        alert('Sila pilih pihak terlebih dahulu.');
        return;
      }
      var url = '${pageContext.request.contextPath}/daftar/utiliti/kutipanData?simpanPemberi' + param + '&p="0"';
      frm = document.tambahUrusan;
      frm.action = url;
      frm.submit();
    }

    function hapusPemberi(val) {
      doBlockUI();
//      alert("id urusan : " + val);
      var url = '${pageContext.request.contextPath}/daftar/utiliti/kutipanData?hapusPemberi&idP=' + val;
      frm = document.tambahUrusan;
      frm.action = url;
      frm.submit();
    }

    function hapusPemohon(val) {
      doBlockUI();
//      alert("id urusan : " + val);
      var url = '${pageContext.request.contextPath}/daftar/utiliti/kutipanData?hapusPemohon&idP=' + val;
      frm = document.tambahUrusan;
      frm.action = url;
      frm.submit();
    }

    function kemaskiniPemohon(val) {
      doBlockUI();
      var idPihak = val;
      var url = '${pageContext.request.contextPath}/daftar/utiliti/kutipanData?kemaskiniPemohon&mp=' + idPihak;
      frm = document.tambahUrusan;
      frm.action = url;
      frm.submit();
    }

    function viewPihak(val, val1) {
      var url = "${pageContext.request.contextPath}/daftar/utiliti/kutipanData?kemaskiniPihakBerkepentingan&jenisUrusan=" + val1
              + "&kumpHm=" + $("#kumpHm").val() + "&id=" + val;
      window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=800");
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
      $('#warganegara').val("MAL");
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
        $('#warganegara').val("");
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
        if (strValidCharacters.indexOf(strBuffer) > -1) {
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
  <s:errors />
  <s:messages/>
  <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
  <s:form beanclass="etanah.view.daftar.utiliti.KutipanDataActionBean" name="tambahUrusan" id="tambahUrusan">
    <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
    <div class="subtitle">
      <s:hidden name="kumpHm" id="kumpHm" value="${actionBean.kumpHm}"/> 
      <s:hidden name="jenisUrusan" id="jenisUrusan" value="${actionBean.hakmilikUrusan.kodUrusan.kod}"/>
      <s:hidden name="id" id="id" value="${actionBean.hakmilikUrusan.idUrusan}"/>      
      <s:hidden name="idPerserahan" value="${actionBean.hakmilikUrusan.idPerserahan }"/>
      <s:hidden name="idhm" value="${actionBean.hakmilikUrusan.hakmilik.idHakmilik}"/>
      <s:hidden name="pihak.idPihak" value="${actionBean.pihak.idPihak}"/>

      <fieldset class="aras1">
        <lagend>Maklumat Pihak Berkepentingan</lagend><br><br> 
        <table class="tablecloth" width="90%" style="margin-left: auto; margin-right: auto;">          
          <tr>
            <td id="tdLabel"> Urusan :&nbsp</td>
            <td id="tdDisplay" > 
              ${actionBean.hakmilikUrusan.kodUrusan.kod} - ${actionBean.hakmilikUrusan.kodUrusan.nama}
            </td>
          </tr> 
          <tr>
            <td id="tdLabel"> Id Urusan :&nbsp</td>
            <td id="tdDisplay" > 
              <s:hidden name="hakmilikUrusan.idPerserahan" />
              ${actionBean.hakmilikUrusan.idPerserahan}
            </td>
          </tr>
          <tr>
            <td id="tdLabel"> Id Hakmilik :&nbsp</td>
            <td id="tdDisplay" > 
              <s:hidden name="hakmilikUrusan.hakmilik.idHakmilik" />
              ${actionBean.hakmilikUrusan.hakmilik.idHakmilik}
            </td>
          </tr>
        </table>
        <br>
        <!----------------  MAKLUMAT PEMOHON   -->
        <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'GD' 
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'GDWM'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'GDCE'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KVST' 
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KVAT'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KVLT'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KVPT'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KVSPC'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KVSS'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KVAS'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KVLS'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KVPS'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PLS'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KVSK'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KVAK'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KVPK'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PMT'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'TMAME'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'TMAMF'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'TMAMG'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'TMAMT'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'TMAML'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'TMAMW'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'TMAMD'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'JMGD'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'JPGD'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'TN'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PHMM'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PHMMS'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PNPHB'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PH30A'}">
              <table class="tablecloth" width="98%" style="margin-left: auto; margin-right: auto;">
                <tr><th><span class="arrow">Maklumat Rekod Ketuanpunyaan</span></th> 
                <tr>
                  <td>
                    <fieldset class="aras1">
                      <legend>Senarai Pemilik : </legend>
                      <p style="color:black">
                        *Sila pilih pemilik yang terlibat.
                      </p>
                      <div align="center">
                        <display:table class="tablecloth" style="width:90%;" 
                                       cellpadding="0" cellspacing="0" id="lineHPB"
                                       name="${actionBean.listHakmilikPihak}">
                          <display:column title="<input type='checkbox' name='semua' onclick='javascript:selectAllPihak(this);'>" style="width:1%;">                       
                            <s:checkbox name="checkboxpihak" id="checkboxpihak${lineHPB_rowNum-1}" 
                                        value="${lineHPB.idHakmilikPihakBerkepentingan}" class="pemilikTerlibat"/>                        
                          </display:column>
                          <display:column title="Bil" style="width:1%;"><p align="right">${lineHPB_rowNum}</p></display:column>
                          <display:column property="nama" title="Nama" style="text-transform:uppercase;"/>
                          <display:column property="jenisPengenalan.nama" title="Jenis Pengenalan" style="width:20%;"/>
                          <display:column property="noPengenalan" title="Nombor Pengenalan" style="width:20%;"/>
                          <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod ne 'KVSK'
                                        and actionBean.hakmilikUrusan.kodUrusan.kod ne 'KVAK'
                                        and actionBean.hakmilikUrusan.kodUrusan.kod ne 'KVPK'}">
                            <display:column title="Syer" style="width:7%;"><div align="center">${lineHPB.syerPembilang}/${lineHPB.syerPenyebut}</div></display:column>
                            <display:column property="jenis.nama" title="Jenis Pihak" style="width:15%;"/>                            
                          </c:if>   
                          <display:column title="ID Pihak" property="pihak.idPihak" class="hidden" headerClass="hidden" media="html"/>
                        </display:table>
                        <s:button name="simpanPemohon" onclick="addNew();" value="Simpan" class="btn"/>&nbsp;
                        <br>
                      </div>
                      <c:if test="${fn:length(actionBean.listPemohon)>0}">
                        <br>
                        <legend>Senarai Pihak Berkepentingan Terlibat : </legend>                    
                        <div align="center">            
                          <display:table class="tablecloth" style="width:90%;" cellpadding="0" cellspacing="0" id="linePemohon"
                                         name="${actionBean.listPemohon}">
                            <display:column title="Bil" style="width:1%;"><p aling="right">${linePemohon_rowNum}</p></display:column>
                            <display:column property="nama" title="Nama" style="text-transform:uppercase;"/>
                            <display:column property="jenisPengenalan.nama" title="Jenis Pengenalan" style="width:20%;"/>
                            <display:column property="noPengenalan" title="Nombor Pengenalan" style="width:20%;"/>
                            <display:column title="Jenis Pihak" style="width:15%;"><p align="center">${linePemohon.jenis.nama}</p></display:column>
                            <display:column title="ID Pihak" property="pihak.idPihak" class="hidden" headerClass="hidden" media="html"/>                
                            <display:column title="Hapus" style="width:5%;">
                              <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem${line_rowNum}' onclick="hapusPemberi('${linePemohon.idPemohon}')" onmouseover="this.style.cursor = 'pointer';" >
                              </div>
                            </display:column>
                          </display:table>
                        </div>
                        <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'PLS'}">
                          <p align="center">
                            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                          </p>
                          <br>
                        </c:if>
                      </c:if>
                    </fieldset>
                  </td>
                </tr>
              </table>
              <br>
        </c:if>


        <!----------------  MAKLUMAT MOHON_PIHAK   -->    
        <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod ne 'PLS'}">
          <table class="tablecloth" width="98%" style="margin-left: auto; margin-right: auto;">                
            <tr>
              <th>
                <span class="arrow">
                  <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'PJT' 
                                or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PJBT'}">
                        Maklumat Pemegang Pajakan
                  </c:if>
                  <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'GD'
                                or actionBean.hakmilikUrusan.kodUrusan.kod eq 'GDWM'
                                or actionBean.hakmilikUrusan.kodUrusan.kod eq 'GDCE'}">
                        Maklumat Pemegang Gadaian
                  </c:if>
                  <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'GDPJ'
                                or actionBean.hakmilikUrusan.kodUrusan.kod eq 'GDPJK'
                                or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PJKT'
                                or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PJKBT'}">
                        Maklumat Pemegang Pajakan Baru
                  </c:if>
                  <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'TEN'
                                or actionBean.hakmilikUrusan.kodUrusan.kod eq 'TENBT'}">
                        Maklumat Penyewa
                  </c:if>
                  <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'KVST' 
                                or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KVAT'
                                or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KVLT'
                                or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KVPT'
                                or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KVSPC'
                                or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KVSS'
                                or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KVAS'
                                or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KVLS'
                                or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KVPS'}">
                        Maklumat Pemegang Kaveat
                  </c:if>
                  <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'PMT'}">
                        Maklumat Pemilikan Baru
                  </c:if>
                </span>
              </th>
            </tr>
            <c:if test="${fn:length(actionBean.listMohonPihak)>0}">
              <tr>
                <td>
                  <fieldset class="aras1">    
                    <p style="color:black">
                      *Sila klik(<img alt='Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'>) untuk kemaskini maklumat
                      atau (<img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif'>) untuk hapus maklumat.
                    </p>
                    <div align="center">
                      <display:table class="tablecloth" style="width:90%;" cellpadding="0" cellspacing="0" id="line" 
                                     name="${actionBean.listMohonPihak}" requestURI="/daftar/utiliti/kutipanData">
                        <display:column title="Bil" style="width:1%;"><p align='right'>${line_rowNum}</p></display:column>
                        <display:column title="Nama">${line.nama}</display:column>
                        <display:column title="Jenis Pengenalan" property="jenisPengenalan.nama" style="width:20%;"/>
                        <display:column title="No Kad Pengenalan" property="noPengenalan" style="width:20%;"/>
                        <display:column title="Kemaskini" style="width:5%;">
                          <div align="center">
                            <img alt='Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                 id='rem${line_rowNum}' onclick="kemaskiniPemohon('${line.idPermohonanPihak}')" onmouseover="this.style.cursor = 'pointer';" >
                          </div>
                        </display:column>
                        <display:column title="Hapus" style="width:5%;">
                          <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem${line_rowNum}' onclick="hapusPemohon('${line.idPermohonanPihak}')" onmouseover="this.style.cursor = 'pointer';" >
                          </div>
                        </display:column>
                      </display:table>
                      <br>
                    </div>
                  </fieldset>
                </td>
              </tr>
            </c:if>
          </table>          

          <!------- SEARCH MOHON_PIHAK -->
          <c:if test="${actionBean.pihak.idPihak eq null}">                
            <div id="maklumatPemohon" >
              <legend>Carian </legend>
              <table width="90%" cellpadding="3" cellspacing="3" >                             
                <tr>
                  <td id="tdLabel">Nama Pihak :&nbsp;</td>
                  <td >
                    <s:text name="pihakNama" id="pihakNama" size="40" onblur="this.value=this.value.toUpperCase();"/>
                  </td>
                </tr>
                <tr>
                  <td id="tdLabel">Jenis Pengenalan :&nbsp;</td>
                  <td id="tdDisplay">
                    <s:select name="pihakKodPengenalan" id="pihakKodPengenalan" style="width:200">
                      <s:option value="">Sila Pilih</s:option>
                      <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                    </s:select>
                  </td>
                </tr>
                <tr>
                  <td id="tdLabel">No. Pengenalan :&nbsp;</td>
                  <td id="tdDisplay">
                    <s:text name="pihakNoIC" id="pihakNoIC" size="40" />
                  </td>
                </tr>                             
              </table>
              <p align="center">
                <s:submit name="cariPemohon" value="Cari Pemohon" id="cariPemohon" class="btn"/>&nbsp;
                <s:button name="tambahPemegang" value="Tambah" id="tambahPemegang" class="btn"/>&nbsp;
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
              </p>
              <br>
            </div>                   
            <c:if test="${fn:length(actionBean.listPihak)>0}">
              <font size="2" color="black">&nbsp;&nbsp;Sila klik pada nama pihak untuk memilih pihak.</font>
              <div align="center">
                <display:table class="tablecloth" style="width:90%;" pagesize="100"
                               cellpadding="0" cellspacing="0" id="linePihak" 
                               name="${actionBean.listPihak}" requestURI="/daftar/utiliti/kutipanData">
                  <display:column title="Bil" style="width:1%;"><p align='right'>${linePihak_rowNum}</p></display:column>
                  <display:column title="Nama">
                    <a href="#" onclick="pilihPihak('${linePihak.idPihak}');
      return false;">
                      ${linePihak.nama}
                    </a> 
                  </display:column>
                  <display:column title="Jenis Pengenalan" property="jenisPengenalan.nama" style="width:20%;"/>
                  <display:column title="No Kad Pengenalan" property="noPengenalan" style="width:20%;"/>
                  <display:column title="Alamat" property="alamat1" style="width:20%;"/>
                  <display:column title="Alamat Surat" property="suratAlamat1" style="width:20%;"/>
                </display:table>
              </div>
            </c:if>
          </c:if>

          <!------- ADD MOHON_PIHAK -->
          <div id="pemohonDetail" > 
            <fieldset class="aras1"> 
              <legend>Maklumat Baru :</legend>
              <table align="center">
                <tr>
                  <td id="tdLabel">
                    <font color="red">*</font>Jenis Pihak :&nbsp;</td>
                  <td id="tdDisplay">
                    <s:select name="jenisPihak" id="jenisPihak">
                      <s:option value="" > -- Sila Pilih -- </s:option>
                      <s:options-collection collection="${actionBean.senaraiKodPihak}" label="nama" value="kod"/>
                    </s:select>                      
                  </td>
                </tr>
                <tr><td>&nbsp;</td></tr>
                <tr>
                  <td id="tdLabel"><font color="red">*</font>Jumlah syer :&nbsp;</td>
                  <td id="tdDisplay">
                    <s:text name="syerPembilang" id="syerPembilang" size="10" style="text-transform:uppercase"/> / <s:text name="syerPenyebut" id="syerPenyebut" size="10" style="text-transform:uppercase"/>
                  </td>
                </tr>
                <tr>
                  <td id="tdLabel"><font color="red">*</font>Nama :&nbsp;</td>
                  <td id="tdDisplay">
                    <s:text name="pihak.nama" id="nama" size="40" style="text-transform:uppercase" onblur="this.value=this.value.toUpperCase();"/>            
                  </td>
                </tr>
                <tr>
                  <td id="tdLabel"><font color="red">*</font>Jenis Pengenalan :&nbsp;</td>
                  <td id="tdDisplay">
                    <s:select name="pihak.jenisPengenalan.kod" id="jenispengenalan" style="width:200px"> 
                      <s:option value="0">Sila Pilih</s:option>
                      <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                    </s:select>          
                  </td>
                </tr>
                <tr>
                  <td id="tdLabel"><font color="red">*</font>No Pengenalan :&nbsp;</td>
                  <td id="tdDisplay">
                    <s:text name="pihak.noPengenalan" id="kp" size="30" onchange="doAutoCalAgeDOB(this.value);"/>
                  </td>
                </tr>
                <tr>
                    <td id="tdLabel">No Pengenalan Lama:&nbsp;</td>
                    <td id="tdDisplay">
                        <s:text name="noPengenalanLama" id="noPengenalanLama" size="30"/><em>*Jika Pihak memaparkan No Kp Lama & No Kp Baru</em>
                    </td>
                </tr>
                <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod ne 'GD'
                              and actionBean.hakmilikUrusan.kodUrusan.kod ne 'GDWM'
                              and actionBean.hakmilikUrusan.kodUrusan.kod ne 'GDCE'}">
                      <tr>
                        <td id="tdLabel">Tempat Lahir :&nbsp;</td>
                        <td id="tdDisplay">
                          <s:select name="pihak.negeriKelahiran.kod" style="width:200px" id="tLahir">
                            <s:option value="99">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" sort="nama"/>
                          </s:select>
                        </td>
                      </tr> 
                      <tr>
                        <td id="tdLabel">Tarikh Lahir :&nbsp;</td>
                        <td id="tdDisplay">
                          <s:text name="pihak.tarikhLahir" size="30" id="trhLahir" class="datepicker" formatPattern="dd/MM/yyyy"/>
                        </td>
                      </tr>
                      <tr>
                        <td id="tdLabel">Jantina :&nbsp;</td>
                        <td id="tdDisplay">
                          <s:select name="pihak.kodJantina" id="jantina" style="width:200px">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:option value="1">Lelaki</s:option>
                            <s:option value="2">Perempuan</s:option>
                          </s:select>    
                        </td>
                      </tr> 
                      <tr>
                        <td id="tdLabel">Warganegara :&nbsp;</td>
                        <td id="tdDisplay">
                          <s:select name="pihak.wargaNegara.kod" id="warganegara" style="width:200px">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiWarganegara}" label="nama" value="kod" />
                          </s:select>     
                        </td>
                      </tr>
                </c:if>
                <%--<tr>
                  <td id="tdLabel">Bangsa/Pertubuhan/Syarikat :&nbsp;</td>
                  <td id="tdDisplay">
                    <s:select class="wideselect" name="pihak.bangsa.kod" id="bangsa">
                      <s:option value="T">Sila Pilih</s:option>
                      <s:options-collection collection="${listUtil.senaraiKodBangsa}" label="nama" value="kod" />
                    </s:select>    
                  </td>
                </tr>--%>        
                <tr><td>&nbsp;</td></tr>
                <tr>
                  <td id="tdLabel">Alamat Berdaftar :&nbsp;</td>
                  <td id="tdDisplay">
                    <s:text name="pihak.alamat1" id="alamat1" size="40" style="text-transform:uppercase" onblur="this.value=this.value.toUpperCase();"/>
                  </td>
                </tr>
                <tr>
                  <td id="tdLabel">&nbsp;</td>
                  <td id="tdDisplay">
                    <s:text name="pihak.alamat2" id="alamat2" size="40" style="text-transform:uppercase"/>
                  </td>
                </tr>
                <tr>
                  <td id="tdLabel">&nbsp;</td>
                  <td id="tdDisplay">
                    <s:text name="pihak.alamat3" id="alamat3" size="40" style="text-transform:uppercase"/>
                  </td>
                </tr>
                <tr>
                  <td id="tdLabel">Bandar :&nbsp;</td>
                  <td id="tdDisplay">
                    <s:text name="pihak.alamat4" id="alamat4" size="40" style="text-transform:uppercase"/>
                  </td>
                </tr>
                <tr>
                  <td id="tdLabel">Poskod :&nbsp;</td>
                  <td id="tdDisplay">
                    <s:text name="pihak.poskod" id="poskod" size="40" maxlength="5"/>
                  </td>
                </tr>
                <tr>
                  <td id="tdLabel">Negeri :&nbsp;</td>
                  <td id="tdDisplay">
                    <s:select name="pihak.negeri.kod" id="negeri">
                      <s:option value="">Sila Pilih</s:option>
                      <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
                    </s:select>
                  </td>
                </tr>
                <tr class="tablecloth">
                  <td>&nbsp;</td>
                  <td id="tdDisplay">
                    <s:checkbox name="tik" id="tik" onmouseover="this.style.cursor='pointer';"/>&nbsp;
                    <font color="red">*Klik jika alamat berdaftar adalah sama dengan alamat surat menyurat </font>
                  </td>
                </tr>
                <tr>
                  <td id="tdLabel">Alamat Surat-Menyurat :&nbsp;</td>
                  <td id="tdDisplay">
                    <s:text name="pihak.suratAlamat1" id="suratAlamat1" size="40" style="text-transform:uppercase"/>
                  </td>
                </tr>
                <tr>
                  <td id="tdLabel">&nbsp;</td>
                  <td id="tdDisplay">
                    <s:text name="pihak.suratAlamat2" id="suratAlamat2" size="40" style="text-transform:uppercase"/>
                  </td>
                </tr>
                <tr>
                  <td id="tdLabel">&nbsp;</td>
                  <td id="tdDisplay">
                    <s:text name="pihak.suratAlamat3" id="suratAlamat3" size="40" style="text-transform:uppercase"/>
                  </td>
                </tr>
                <tr>
                  <td id="tdLabel">Bandar :&nbsp;</td>
                  <td id="tdDisplay">
                    <s:text name="pihak.suratAlamat4" id="suratAlamat4" size="40" style="text-transform:uppercase"/>
                  </td>
                </tr>
                <tr>
                  <td id="tdLabel">Poskod :&nbsp;</td>
                  <td id="tdDisplay">
                    <s:text name="pihak.suratPoskod" id="suratPoskod" size="40" maxlength="5"/>
                  </td>
                </tr>
                <tr>
                  <td id="tdLabel">Negeri :&nbsp;</td>
                  <td id="tdDisplay">
                    <s:select name="pihak.suratNegeri.kod" id="suratNegeri">
                      <s:option value="">Sila Pilih</s:option>
                      <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
                    </s:select>
                  </td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td> 
                    <br>
                    <s:hidden name="pemegang" id="pemegang" value="${actionBean.pemegang}"/> 
                    <s:submit name="simpanPemohon" id="simpanPemegang" value="Simpan" class="longbtn"/>&nbsp;   
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                  </td>
                </tr>
              </table>      
              <br>
            </fieldset>
          </div>   

          <!-------- UPDATE MOHON_PIHAK (KEPADA)-->
          <c:if test="${actionBean.permohonanPihak ne null }">
            <s:hidden name="idPermohonanPihak" value="${actionBean.permohonanPihak.idPermohonanPihak}"/>
            <div id="kemaskiniPemohon">
              <legend>Maklumat Kemaskini :</legend>
              <fieldset class="aras1">
                <s:hidden name="pemegang" id="pemegang" value="${actionBean.pemegang}"/> 
                <table align="center">
                  <tr>
                    <td id="tdLabel">
                      <font color="red">*</font>Jenis Pihak :&nbsp;</td>
                    <td id="tdDisplay">
                      <s:select name="jenisPihakKemaskini" id="jenisPihak" value="${actionBean.permohonanPihak.jenis.kod}">
                        <s:options-collection collection="${actionBean.senaraiKodPihak}" label="nama" value="kod"/>
                      </s:select>                      
                    </td>
                  </tr>
                  <tr><td>&nbsp;</td></tr>
                  <tr>
                  <td id="tdLabel"><font color="red">*</font>Jumlah syer :&nbsp;</td>
                  <td id="tdDisplay">
                    <s:text name="permohonanPihak.syerPembilang" id="syerPembilang" size="10" style="text-transform:uppercase"/> / <s:text name="permohonanPihak.syerPenyebut" id="syerPenyebut" size="10" style="text-transform:uppercase"/>
                  </td>
                </tr>
                  <tr>
                    <td id="tdLabel"><font color="red">*</font>Nama :&nbsp;</td>
                    <td id="tdDisplay">
                      <s:text name="permohonanPihak.nama" id="nama" size="40" style="text-transform:uppercase" onblur="this.value=this.value.toUpperCase();"/>            
                    </td>
                  </tr>
                  <tr>
                    <td id="tdLabel"><font color="red">*</font>Jenis Pengenalan :&nbsp;</td>
                    <td id="tdDisplay">
                      <s:select name="permohonanPihak.jenisPengenalan.kod" id="jenispengenalan" style="width:200px"> 
                        <s:option value="0">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                      </s:select>          
                    </td>
                  </tr>
                  <tr>
                    <td id="tdLabel"><font color="red">*</font>No Pengenalan :&nbsp;</td>
                    <td id="tdDisplay">
                      <s:text name="permohonanPihak.noPengenalan" id="kp" size="30" onchange="doAutoCalAgeDOB(this.value);"/>          
                    </td>
                  </tr>
                  <tr>
                      <td id="tdLabel">No Pengenalan Lama:&nbsp;</td>
                      <td id="tdDisplay">
                          <s:text name="noPengenalanLama" id="kplama" size="30"/><em>*Jika Pihak memaparkan No Kp Lama & No Kp Baru</em>
                      </td>
                  </tr>
                  <tr>
                    <td id="tdLabel">Warganegara :&nbsp;</td>
                    <td id="tdDisplay">
                      <s:select name="permohonanPihak.wargaNegara.kod" id="warganegara" style="width:200px">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiWarganegara}" label="nama" value="kod" />
                      </s:select>     
                    </td>
                  </tr>
                  <tr><td>&nbsp;</td></tr>
                  <tr>
                    <td id="tdLabel">Alamat Berdaftar :&nbsp;</td>
                    <td id="tdDisplay">
                      <s:text name="permohonanPihak.alamat.alamat1" id="alamat1Kemaskini" size="40" style="text-transform:uppercase" onblur="this.value=this.value.toUpperCase();"/>
                    </td>
                  </tr>
                  <tr>
                    <td id="tdLabel">&nbsp;</td>
                    <td id="tdDisplay">
                      <s:text name="permohonanPihak.alamat.alamat2" id="alamat2Kemaskini" size="40" style="text-transform:uppercase"/>
                    </td>
                  </tr>
                  <tr>
                    <td id="tdLabel">&nbsp;</td>
                    <td id="tdDisplay">
                      <s:text name="permohonanPihak.alamat.alamat3" id="alamat3Kemaskini" size="40" style="text-transform:uppercase"/>
                    </td>
                  </tr>
                  <tr>
                    <td id="tdLabel">Bandar :&nbsp;</td>
                    <td id="tdDisplay">
                      <s:text name="permohonanPihak.alamat.alamat4" id="alamat4Kemaskini" size="40" style="text-transform:uppercase"/>
                    </td>
                  </tr>
                  <tr>
                    <td id="tdLabel">Poskod :&nbsp;</td>
                    <td id="tdDisplay">
                      <s:text name="permohonanPihak.alamat.poskod" id="poskodKemaskini" size="40" maxlength="5"/>
                    </td>
                  </tr>
                  <tr>
                    <td id="tdLabel">Negeri :&nbsp;</td>
                    <td id="tdDisplay">
                      <s:select name="permohonanPihak.alamat.negeri.kod" id="negeriKemaskini">
                        <s:option value="99">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
                      </s:select>
                    </td>
                  </tr>
                  <tr class="tablecloth">
                    <td>&nbsp;</td>
                    <td id="tdDisplay">
                      <s:checkbox name="tik2" id="tik2" onmouseover="this.style.cursor='pointer';"/>&nbsp;
                      <font color="red">*Klik jika alamat berdaftar adalah sama dengan alamat surat menyurat </font>
                    </td>
                  </tr>
                  <tr>
                    <td id="tdLabel">Alamat Surat-Menyurat :&nbsp;</td>
                    <td id="tdDisplay">
                      <s:text name="permohonanPihak.alamatSurat.alamatSurat1" id="alamatSurat1Kemaskini" size="40" style="text-transform:uppercase"/>
                    </td>
                  </tr>
                  <tr>
                    <td id="tdLabel">&nbsp;</td>
                    <td id="tdDisplay">
                      <s:text name="permohonanPihak.alamatSurat.alamatSurat2" id="alamatSurat2Kemaskini" size="40" style="text-transform:uppercase"/>
                    </td>
                  </tr>
                  <tr>
                    <td id="tdLabel">&nbsp;</td>
                    <td id="tdDisplay">
                      <s:text name="permohonanPihak.alamatSurat.alamatSurat3" id="alamatSurat3Kemaskini" size="40" style="text-transform:uppercase"/>
                    </td>
                  </tr>
                  <tr>
                    <td id="tdLabel">Bandar :&nbsp;</td>
                    <td id="tdDisplay">
                      <s:text name="permohonanPihak.alamatSurat.alamatSurat4" id="alamatSurat4Kemaskini" size="40" style="text-transform:uppercase"/>
                    </td>
                  </tr>
                  <tr>
                    <td id="tdLabel">Poskod :&nbsp;</td>
                    <td id="tdDisplay">
                      <s:text name="permohonanPihak.alamatSurat.poskodSurat" id="poskodSuratKemaskini" size="40" maxlength="5"/>
                    </td>
                  </tr>
                  <tr>
                    <td id="tdLabel">Negeri :&nbsp;</td>
                    <td id="tdDisplay">
                      <s:select name="permohonanPihak.alamatSurat.negeriSurat.kod" id="suratNegeriKemaskini">
                        <s:option value="99">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
                      </s:select>
                    </td>
                  </tr>                 
                </table> 
                <p align ="center">
                  <br>
                  <s:submit name="simpanPemohon" id="simpanPemegang" value="Simpan" class="longbtn"/>&nbsp; 
                  <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>  
                <br>
              </fieldset> 
            </div>
          </c:if>
        </c:if>
        <br> 
      </fieldset>
    </div>
  </s:form>
</div>