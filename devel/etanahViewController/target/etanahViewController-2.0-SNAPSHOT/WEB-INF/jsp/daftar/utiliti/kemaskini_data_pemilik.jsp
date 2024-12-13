<%-- 
    Document   : kutipan_data_maklumat_hakmilik
    Created on : Sep 24, 2013, 5:11:48 PM
    Author     : ei
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>-->
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<c:set var="disabled" value="disabled"/>
<script type="text/javascript">
  function filterKodBPM(f) {
    // GET BANDAR PEKAN MUKIN FROM PARTIAL JSP 
    var kodDaerah = f;
    $.post('${pageContext.request.contextPath}/daftar/utiliti/kemaskiniData?cariBPM&kodDaerah=' + kodDaerah + '&popup=true',
            function(data) {
              if (data != '') {
                $('#partialKodBPM').html(data);
              }
            }, 'html');
  }

  function filterKodGunaTanah(val, val2) {
    var url = '${pageContext.request.contextPath}/daftar/utiliti/kemaskiniData?senaraiKodGunaTanahByKatTanah&kt=' + val + '&hm=' + val2;
    frm = document.kutipanUrusan;
    frm.action = url;
    frm.submit();
  }
</script>
<script type="text/javascript">
  $(document).ready(function() {
    $(".datepicker1").datepicker({changeYear: true});
    $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    filterKodBPM($('#kodDaerah').val());
    filterKodGunaTanah($('#katTanah').val());
//    alert($('#pegangan').val());

  <c:if test="${actionBean.idHakmilik ne null}">
    showHm1($('#idHa kmilik').val());
  </c:if>

  <c:if test="${actionBean.kodKatTanah ne null}">
      alert(${actionBean.kodKatTanah});
  </c:if>

      $('#simpanPerinci').click(function() {
        doBlockUI();
      });
    });
    function showHm1(v) {
      // SHOW ID HAKMILIK DETAILS   
      doBlockUI();
      var url = '${pageContext.request.contextPath}/daftar/utiliti/kemaskiniData?maklumatDetail&idHakmilik=' + v;
      frm = document.kutipanUrusan;
      frm.action = url;
      frm.submit();
    }

    function removeMultipleMohonHakmilik() {
      // DELETE CHECKED HAKMILIK
      doBlockUI();
      if (confirm('Adakah anda pasti untuk hapus hakmilik ini')) {
        var param = '';
        $('.remove2').each(function(index) {
          var a = $('#checkbox' + index).is(":checked");
          if (a) {
            param = param + '&idHapus=' + $('#checkbox' + index).val();
          }
        });
        if (param === '') {
          alert('Sila Pilih Hakmilik terlebih dahulu.');
          $.unblockUI();
          return;
        }

        var url = '${pageContext.request.contextPath}/daftar/utiliti/kemaskiniData?hapusHakmilik' + param;
        frm = document.kutipanUrusan;
        frm.action = url;
        frm.submit();
      }
    }

    function selectAll(a) {
      // TICK ALL HAKMILIK
      var size = '${fn:length(actionBean.listSenaraiHakmilik)}';
      for (i = 0; i < size; i++) {
        var c = document.getElementById("checkbox" + i);
        if (c === null)
          break;
        c.checked = a.checked;
      }
    }

    function popupFormSyaratNyata() {
      // GET SYARAT NYATA POPUP
      var url = "${pageContext.request.contextPath}/daftar/utiliti/kemaskiniData?paparPopupSyaratNyata&idHakmilik=" + $("#idHakmilik").val() + "&kumpHm=" + $("#kumpHm").val();
      window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
    }

    function popupFormKodSekatan() {
      // GET SEKATAN POPUP
      var url = "${pageContext.request.contextPath}/daftar/utiliti/kemaskiniData?paparPopupSekatan&idHakmilik=" + $("#idHakmilik").val() + "&kumpHm=" + $("#kumpHm").val();
      window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
    }

    function popupHakmilikSblm(id) {
      // GET HAKMILIK SEBELUM POP-UP
      var url = "${pageContext.request.contextPath}/daftar/utiliti/kemaskiniData?paparPopupHakmilikSebelum&idHakmilik=" + id + "&kumpHm=" + $("#kumpHm").val();
      window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=500");
    }

    function popupHakmilikAsal(id) {
      // GET HAKMILIK ASAL POP-UP
      var url = "${pageContext.request.contextPath}/daftar/utiliti/kemaskiniData?paparPopupHakmilikAsal&idHakmilik=" + id + "&kumpHm=" + $("#kumpHm").val();
      window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=500");
    }

    function popupPihak(id) {
      // GET PIHAK POP
      var url = "${pageContext.request.contextPath}/daftar/utiliti/kemaskiniData?paparPopupPihak&idHakmilik=" + id + "&kumpHm=" + $("#kumpHm").val();
      window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=800");
    }

    function popupKemaskiniPihak(val, val1, val2) {
      // GET POPUP KEMASKINI PIHAK
      var url = "${pageContext.request.contextPath}/daftar/utiliti/kemaskiniData?kemaskiniPihak&phk=" + val + "&hm=" + val1 + "&hp=" + val2;
      window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=800");
    }

    function selectAllPihak(a) {
      // TICK ALL HAKMILIK
      var size = '${fn:length(actionBean.listHakmilikPihak)}';
      for (i = 0; i < size; i++) {
        var c = document.getElementById("checkboxpihak" + i);
        if (c === null)
          break;
        c.checked = a.checked;
      }
    }

    function removePihak() {
      // DELETE CHECKED PIHAK
      if (confirm('Adakah anda pasti untuk hapus pihak ini')) {
        doBlockUI();
        var param = '';
        $('.remove2').each(function(index) {
          var a = $('#checkboxpihak' + index).is(":checked");
          if (a) {
            param = param + '&idHapus=' + $('#checkboxpihak' + index).val();
//          alert(param);
          }
        });
        if (param === '') {
          alert('Sila Pilih Pihak terlebih dahulu.');
          return;
        }

        var url = '${pageContext.request.contextPath}/daftar/utiliti/kemaskiniData?hapusPihak' + param;
        //        frm = document.maklumatDetail;
        frm = document.kutipanUrusan;
        frm.action = url;
        frm.submit();
      }
    }

    function samaRata(f) {       // MAKE ALL SHARE EQUAL FOR EACH PIHAK
      var q = $(f).formSerialize();
      doBlockUI();
      var url = '${pageContext.request.contextPath}/daftar/utiliti/kemaskiniData?agihSamaRata';
      //      frm = document.maklumatDetail;
      frm = document.kutipanUrusan;
      frm.action = url;
      frm.submit();
    }

    function zeroPad(num, count) {
      var numZeropad = num + '';
      while (numZeropad.length < count) {
        numZeropad = "0" + numZeropad;
      }
      return numZeropad;
    }

    function checkPelan(f) {
      // GET GIS PLAN
      var noLot = zeroPad(f, 7);
      var kodDaerah = $('#kodDaerah').val();
      var kodBPM2 = $('#kodBPM').val();
      var kodBPM = zeroPad(kodBPM2, 2);
      var kodNegeri = $('#kodNegeri').val();
      var namaLot = $('#jenisLot :selected').text();
      var kodSeksyen = '${actionBean.hakmilik.seksyen.seksyen}';
      if (kodSeksyen === "") {
        kodSeksyen = "000";
      }

      var kodPelan;
      var jenisPelan;
      var jenisHm = $('#pegangan').val();

      if (jenisHm === 'S') {
        jenisPelan = "B1";
        kodPelan = "1";
      } else {
        jenisPelan = "B2";
        kodPelan = "3";
      }
      //      alert("noLot " + noLot);
      //      alert("kodDaerah " + kodDaerah);
      //      alert("kodNegeri " + kodNegeri);
      //      alert("kodBPM " + kodBPM);
      doBlockUI();

      $.post('${pageContext.request.contextPath}/daftar/utiliti/kemaskiniData?checkPelan&noLot='
              + noLot + '&kodDaerah=' + kodDaerah + '&kodNegeri=' + kodNegeri + '&kodBPM=' + kodBPM + '&kodSeksyen='+ kodSeksyen +'&jenisPelan=' + jenisPelan,
              function(data) {
//                        alert("data pelan : " + data);
                if (data !== '1') {
                  $('#checkPelanMessages').show();
                  $('#checkPelanMessages').attr('class', 'errors');
                  $('#checkPelanMessages').html('Pelan untuk No ' + namaLot + ' ' + noLot + ' tiada');
                  $.unblockUI();
                } else {
                  $('#checkPelanMessages').show();
                  $('#checkPelanMessages').attr('class', 'messages');
                  $('#checkPelanMessages').html('Pelan untuk No ' + namaLot + ' ' + noLot + ' wujud. '
                          + '<a href=${pageContext.request.contextPath}/pelan/view/' + kodNegeri + kodDaerah + kodBPM + kodSeksyen + kodPelan + noLot + ' target=parent>' + ' Klik Untuk Semak Pelan </a>');
                  $.unblockUI();
                }
              }, 'html');
    }

    function checkNoLot(f) {
      // CHECK NO LOT IN TABLE HAKMILIK
      var noLot = zeroPad(f, 7);
      var kodDaerah = $('#kodDaerah').val();
      var kodBPM = $('#kodBPM').val();
      var kodNegeri = $('#kodNegeri').val();
      var jenisLot = $('#jenisLot').val();
      $('#noLot').val(noLot);
//      alert("no lot : " + noLot);
//      alert("kodDaerah : " + kodDaerah);
//      alert("kodBPM : " + kodBPM);
//      alert("kodNegeri : " + kodNegeri);
      $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?checkNoLot&noLot='
              + noLot + '&kodDaerah=' + kodDaerah + '&kodNegeri=' + kodNegeri + '&kodBPM=' + kodBPM
              + '&jenisLot=' + jenisLot,
              function(data) {
                //                alert("data LOT : " + data);
                var namaLot = $('#jenisLot :selected').text();
                if (data != '1') {
                  $('#checkLotMessages').attr('class', 'errors');
                  $('#checkLotMessages').html('No ' + namaLot + ' ' + noLot + ' telah digunakan');
                  $('#checkLotMessages').show();
                } else {
                  $('#checkLotMessages').attr('class', 'messages');
                  $('#checkLotMessages').html('No ' + namaLot + ' ' + noLot + ' belum pernah digunakan');
                  $('#checkLotMessages').show();
                }
              }, 'html');
    }

    function checkLuas(f) {
      // CHECK LUAS 
//      var noLot = zeroPad(f, 7);
      var kodUOM = $('#kodUOM').val();
      var luas = $('#luas').val();
//      alert(luas);
      if (kodUOM === 'E' && luas !== "") {
        // CONVERT ekar rood pool  
        $.post('${pageContext.request.contextPath}/daftar/utiliti/kemaskiniData?checkLuas&luas=' + luas + '&kodUOM=' + kodUOM,
                function(data) {
                  var ekar = parseFloat(data);
                  var m = ekar * 4046.8564;
                  m = parseFloat(Math.round(m * 10000) / 10000).toFixed(4); // round up to 0.0000
                  var h = ekar * 0.404686;
                  h = parseFloat(Math.round(h * 10000) / 10000).toFixed(4); // round up to 0.0000

                  if (!isNaN(m) && !isNaN(h)) {
                    $('#luasMeter').val(m); // pass to hidden text input
                    $('#luasHektar').val(h);// pass to hidden text input
                    // show massage
                    $('#checkLuasMessages').attr('class', 'messages');
                    $('#checkLuasMessages').html('Luas Metrik = ' + m + ' Meter Persegi; ' + h + ' Hektar.');
                    $('#checkLuasMessages').show();
                  } else {
                    $('#luasMeter').val("");
                    $('#luasHektar').val("");
                  }
                }, 'html');
      } else if (kodUOM === 'P' && luas !== "") {
        // CONVERT ekar perpuluhan
        var ekar = parseFloat(luas);
        var m = ekar * 4046.8564;
        m = parseFloat(Math.round(m * 10000) / 10000).toFixed(4); // round up to 0.0000
        var h = ekar * 0.404686;
        h = parseFloat(Math.round(h * 10000) / 10000).toFixed(4); // round up to 0.0000

        if (!isNaN(m) && !isNaN(h)) {
          $('#luasMeter').val(m); // pass to hidden text input
          $('#luasHektar').val(h);// pass to hidden text input
          // show massege
          $('#checkLuasMessages').attr('class', 'messages');
          $('#checkLuasMessages').html('Luas Metrik = ' + m + ' Meter Persegi; ' + h + ' Hektar.');
          $('#checkLuasMessages').show();
        } else {
          $('#luasMeter').val("");
          $('#luasHektar').val("");
        }
      } else {
        $('#luasMeter').val("");
        $('#luasHektar').val("");
      }
    }

    function filterPegangan() {
      var pegangan = $('#pegangan').val();
      if (pegangan === 'P') {
        $("#p").show();
      } else if (pegangan === 'S') {
        $("#p").hide();
        $("#tempohPegangan").val("");
        $("#tkhTamat").val("");
      }
    }

    function doCalcEndDate(id) {
      var thn = parseInt($('#tempohPegangan').val(), 10);
      if ($('#' + id).val() === '') {
        return;
      }
      if (isNaN(thn)) {
        alert('Sila Masukan Tempoh.');
        $('#tkhTamat').val('');
        return;
      }
      if (thn === '0')
        return;
      var startDate = $('#' + id).val();
      //manual process :: should find conclusion on this
      var y = parseInt(startDate.substring(6, 10), 10);
      var m = parseInt(startDate.substring(3, 5), 10);
      var d = parseInt(startDate.substring(0, 2), 10);
      if (!isNaN(thn)) {
        y = y + thn;
      }

      var endDate = new Date();
      var s = 1;
      endDate.setDate(d);
      endDate.setMonth(m - 1);
      endDate.setFullYear(y);
      endDate.setDate(endDate.getDate() - s);
      $('#tkhTamat').val(endDate.format("dd/mm/yyyy"));
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
<div class="a">
  <s:errors/>
  <s:messages/>
  <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
  <s:form beanclass="etanah.view.daftar.utiliti.KemaskiniDataActionBean" name="maklumatHakmilik" id="maklumatHakmilik">
    <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
    <div class="subtitle">
        <!--<div class="subtitle">-->
        <fieldset class="aras1">
          <legend>Senarai Pemilik</legend>
          <br>
          <div class="content" align="center" id="listpihak">
            <display:table class="tablecloth" style="width:95%;" 
                           cellpadding="0" cellspacing="0" id="lineHPB"
                           name="${actionBean.listHakmilikPihak}">
              <display:column title="<input type='checkbox' name='semua' onclick='javascript:selectAllPihak(this);'>" style="width:1%;">
                <s:checkbox name="checkboxpihak" id="checkboxpihak${lineHPB_rowNum-1}" 
                            value="${lineHPB.idHakmilikPihakBerkepentingan}" class="remove2"/>
              </display:column>
              <display:column title="Bil" sortable="true" style="width:1%;"><p align="right">${lineHPB_rowNum}</p></display:column>
              <display:column property="nama" title="Nama" style="text-transform:uppercase;"/>
              <display:column title="Nombor Pengenalan" style="width:12%;">
                <div align="center">${lineHPB.noPengenalan}</div>
              </display:column>
              <display:column title="Jenis Pihak" style="width:12%;">
                <div align="center">${lineHPB.jenis.nama}</div>
              </display:column>
                
              <display:column title="Aktif" style="width:5%;">
                <div align="center">${lineHPB.aktif}</div>
              </display:column>                  
                
              <display:column title="Bahagian yang diterima" style="width:20%;">
                <div align="center">
                  <s:hidden name="listHakmilikPihak[${lineHPB_rowNum-1}].idHakmilikPihakBerkepentingan"/>
                  <s:text name="listHakmilikPihak[${lineHPB_rowNum-1}].syerPembilang" size="5" id="syer1${lineHPB_rowNum-1}"
                          onblur="updateSyer('${lineHPB.idHakmilikPihakBerkepentingan}', '${lineHPB_rowNum-1}')"
                          onchange="updateSyer('${lineHPB.idHakmilikPihakBerkepentingan}', '${lineHPB_rowNum-1}" class="pembilang"/> /
                  <s:text name="listHakmilikPihak[${lineHPB_rowNum-1}].syerPenyebut" size="5" id="syer2${lineHPB_rowNum-1}"
                          onblur="updateSyer('${lineHPB.idHakmilikPihakBerkepentingan}', '${lineHPB_rowNum-1}')"
                          onchange="updateSyer('${lineHPB.idHakmilikPihakBerkepentingan}', '${lineHPB_rowNum-1}" class="penyebut"/>
                </div>
              </display:column>
              <display:column title="Kemaskini" style="width:5%;">
                <div align="center">
                  <a href="#" onclick="popupKemaskiniPihak('${lineHPB.pihak.idPihak}', '${actionBean.hakmilik.idHakmilik}', '${lineHPB.idHakmilikPihakBerkepentingan}')">
                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif'/>
                  </a>
                </div>
              </display:column>
              <display:column title="ID Pihak" property="pihak.idPihak" class="hidden" headerClass="hidden" media="html"/>
            </display:table>
          </div>
          <div align="center">
            <br>
            <s:button name="btnpopupPihak"  id="btnpopupPihak" disabled="${disabledbtn}"  value="Tambah" class="btn" 
                      onclick="popupPihak('${actionBean.hakmilik.idHakmilik}');" />&nbsp;
            <c:if test="${fn:length(actionBean.listHakmilikPihak) > 1}">  
              <s:button disabled="${disabledbtn}" class="longbtn" value="Agih Sama Rata" name="agihSamaRata" id="agihSamaRata" 
                        onclick="samaRata(this.form);"/>&nbsp; 
            </c:if>
            <c:if test="${fn:length(actionBean.listHakmilikPihak) > 0}">               
              <s:submit disabled="${disabledbtn}" class="longbtn" value="Simpan Syer" name="simpanSyer" id="simpanSyer" />&nbsp; 
              <s:button class="btn" value="Hapus" name="" onclick="removePihak();" disabled="${disabledbtn}"/>&nbsp;      
            </c:if>
          </div>  
          <br>
        </fieldset>
    </div>
  </s:form>        
</div>
