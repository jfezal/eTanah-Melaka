<%--
    Document   : nota_daftar_recreateV2
    Created on : Aug 15, 2012, 2:06:56 PM
    Author     : Aizuddin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/sprintf.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/date.format.js"></script>
<script type="text/javascript">

  $(document).ready(function() {
    var jenisNotisVal = $('input:radio[name=jenisNotis]:checked').val();
    $('.datepicker').datepicker({maxDate: 0, changeYear: true, yearRange: '1970:' + new Date().getFullYear()});
    $('.datepicker1').datepicker({changeYear: true});

    if (jenisNotisVal == "T") {
      $('#W').hide();
    }

    $('#jenisNotisG').click(function() {
      $('#D').hide();
      $('#W').show();
    });

    $('#jenisNotisD').click(function() {
      $('#W').hide();
      $('#D').show();

    });
  <%--set focus--%>
      $('input').focus(function() {
        $(this).addClass("focus");
      });

      $('input').blur(function() {
        $(this).removeClass("focus");
      });

      $('select').focus(function() {
        $(this).addClass("focus");
      });

      $('select').blur(function() {
        $(this).removeClass("focus");
      });
      $('textarea').focus(function() {
        $(this).addClass("focus");
      });

      $('textarea').blur(function() {
        $(this).removeClass("focus");
      });
    });

    function popupCarian(pathCall, boxName, flagCarian)
    {
      var valTambah = "0";
      var addPath = "";
      if (flagCarian == "sekatan") {
        var elementName = "&inputName=strKodSekatan[" + boxName + "]";

        var strKodSekatan = $('input:text[name=strKodSekatan[' + boxName + ']]').val();

        valTambah = strKodSekatan;
        addPath = elementName;
      }

      if (flagCarian == "syarat") {
        var elementName = "&inputName=strKodSyaratNyata[" + boxName + "]";
        var strKodSyaratNyata = $('input:text[name=strKodSyaratNyata[' + boxName + ']]').val();

        valTambah = strKodSyaratNyata;
        addPath = elementName;
      }


      var url = "${pageContext.request.contextPath}/daftar/nota/nota_daftar?" + pathCall + valTambah + addPath;
      window.open(url, "eTanah1", "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
    }
    function filterDaerah(kodDaerah, frm) {
      //                            var jabatan = f.elements['pguna.kodJabatan.kod'].value ;
//            alert(kodDaerah);
      var url = '${pageContext.request.contextPath}/daftar/nota/nota_daftarV2?cariBPM&daerah=' + kodDaerah;
//            alert(url);
      $.ajax({
        url: url,
        success: function(data) {
          $('#page_div').html(data);
        }
      });


    }
    function filterBPM(kodBPM, frm) {
      var daerah = $('#daerah').val();
      var url = '${pageContext.request.contextPath}/daftar/nota/nota_daftarV2?penyukatanSeksyen&bandarPekanMukim=' + kodBPM + '&daerah' + daerah;
      frm.action = url;

    }

    function add(event, f) {

      var kodSyaratNyata = $('input:text[name=kodSyaratNyata]').val();
      var kodSekatan = $('input:text[name=kodSekatan]').val();
      alert(kodSekatan);

      var kodOUM = $('#kodLuas').val();
      if (kodOUM == "")
      {
        kodOUM = "undefined"
      }
      var katTanah = $('#katTanah').val();

      var q = $(f).formSerialize();
      var url = f.action + '?' + event + '&kodSyaratNyata=' + kodSyaratNyata +
              '&kodSekatan=' + kodSekatan + '&kodLuas=' + kodOUM + '&katTanah=' + katTanah;

      $.post(url, q,
              function(data) {
                $('#page_div', opener.document).html(data);

              }, 'html');


    }




    function popup(f, h, p) {
      var url = '${pageContext.request.contextPath}/daftar/nota/nota_daftar?popupMaklumatKait&idH=' + h + '&id=' + p;
  <%--     alert(p);--%>
      window.open(url, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=1000,height=290");


    }

    function padam(f, h, p) {
  <%--window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=290");--%>

      if (confirm("Anda Pasti?"))
      {

        var url = "${pageContext.request.contextPath}/daftar/nota/nota_daftar?padam&id=" + p;
        $.post(url,
                function(data) {
                  $('#page_div', opener.document).html(data);

                }, 'html');

      }

      else

      {


      }


    }

    function savePB(idPihak) {
  <%--alert(idPihak);--%>
      var url = "${pageContext.request.contextPath}/daftar/nota/nota_daftar?saveMohonAtasPihak&idPihak=" + idPihak;
      $.post(url, q,
              function(data) {
                $('#page_div', opener.document).html(data);

              }, 'html');
    }

    function pickPut(nama, noPengenalan, salinan, kodUrusan, idPihak, idHakmilik, syerPembilang, syerPenyebut) {
  <%--var detail = $('input:radio[name=pilihPB]:checked').val();--%>
      var url = "${pageContext.request.contextPath}/daftar/nota/nota_daftar?savePemegangSalinan&idPihak=" + idPihak + "&idHakmilik=" + idHakmilik + "&syerPembilang=" + syerPembilang + "&syerPenyebut=" + syerPenyebut;
      var txt = '<p class=\"jqismooth\">Sila Pastikan Maklumat yang dipilih adalah betul</p>' +
              'Nama :<br\>' + nama + '<br\>' +
              '<br\>No Pengenalan :<br\>' + noPengenalan + '<br\>' +
              '<br\>Salinan yang akan dikeluarkan adalah salinan ke-' + salinan + '<br\>';


      var msgBox = {
        state0: {
          html: txt,
          buttons: {'Teruskan': true, 'Pilih Semula': false},
          focus: 1,
          submit: function(v, m, f) {
            if (!v)
              return true;
            else
              $.post(url,
                      function(data) {

                        $.prompt.goToState('state1');
                        $('#page_div').html(data);

                      }, 'html');


            return false;
          }
        },
        state1: {
          html: kodUrusan + ' ke-' + salinan + ' dikeluarkan kepada ' + nama,
          submit: function(v, m, f) {
            var url1 = "${pageContext.request.contextPath}/daftar/nota/nota_daftar";
            $.post(url1,
                    function(data) {
                      $('#page_div').html(data);

                    }, 'html');

  <%-- self.close();--%>
          }
        }

      };

      $.prompt(msgBox);



    }

    function deletePemohon(idMohonPihak) {
  <%--window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=290");--%>

      if (confirm("Anda Pasti?"))
      {

        var url = "${pageContext.request.contextPath}/daftar/nota/nota_daftar?deletePemohon&idMohonPihak=" + idMohonPihak;
        $.post(url,
                function(data) {
                  $('#page_div', opener.document).html(data);

                }, 'html');

      }

      else

      {


      }


    }

    function kiraCukaiKelompok(idH, i) {

      var kodUOM = $("#kodUOM" + i).val();
      var luas = $("#luasTerlibat" + i).val();
      $.post('${pageContext.request.contextPath}/daftar/nota/nota_daftar?kiraCukaiKelompok&idHakmilik=' + idH + '&luas=' + luas + '&kodUOM=' + kodUOM,
              function(data) {
                $('#cukai' + i).val(convert(data, 'cukai' + i));
              }, 'html');
    }

    function doCalcEndDate(id) {
      var hari = parseInt($('.hari').val(), 10);
      var bln = parseInt($('.bulan').val(), 10);
      var thn = parseInt($('.tahun').val(), 10);
      var kodUrusan = $('#kodUrusan').val();

      if ($('#' + id).val() == '') {
        return;
      }

      if (isNaN(hari) && isNaN(bln) && isNaN(thn) && kodUrusan != 'PJLT') {
        alert('Sila Masukan Tempoh.');
        $('#' + id).val('');
        return;
      }
      if (hari == '0' && bln == '0' && thn == '0')
        return;
      var startDate = $('#' + id).val();
      //manual process :: should find conclusion on this
      var y = parseInt(startDate.substring(6, 10), 10);
      var m = parseInt(startDate.substring(3, 5), 10);
      var d = parseInt(startDate.substring(0, 2), 10);

      if (!isNaN(hari))
      {
        d = d + hari;
        if (d == 0) {
          m = m - 1;
        }
      }

      if (!isNaN(bln)) {
        m = m + bln;
        if (m > 12) {
          y = y + 1;
          m = m - 12;
          if (m == 2) {
            var isleap = (y % 4 == 0 && (y % 100 != 0 || y % 400 == 0));
            if (d >= 30) {
              if (isleap) {
                d = 28;
              } else {
                d = 27;
              }
            } else if (d == 0) {
              if (isleap) {
                d = 29;
              } else {
                d = 28;
              }
            }
          }
        }
      }

      if (!isNaN(thn))
      {
        y = y + thn;
        if (d == 0) {
          m = m - 1;
        }
      }

      if (d == 0 && m == 0) {
        y = y - 1;
      }
      //y = y + thn;
      var endDate = new Date();
      var s = 1;
      endDate.setDate(d);
      endDate.setMonth(m - 1);
      endDate.setFullYear(y);
      endDate.setDate(endDate.getDate() - s);

      if (kodUrusan == 'TSP') {
        var moreThan1year = false;
        var tahun = 0;
        //            if (tahun > 1) {
        //                moreThan1year = true;                
        //            } else 
        if (bln != null) {
          var x = hari / 31;
          var v = (bln + x) / 12;
          tahun = tahun + v;
          if (tahun > 1)
            moreThan1year = true;
        } else if (hari >= 365) {
          var w = hari / 365;
          tahun = tahun + w;
          if (tahun > 1)
            moreThan1year = true;
        }

        if (moreThan1year) {
          alert('Melebihi 1 tahun. Urusan ini mestilah kurang dari tempoh 1 tahun!');
          $('#tarikhTamat').val("");
          return false;
        }

      }

      $('#tarikhTamat').val(endDate.format("dd/mm/yyyy"));
    }

    function validateLuas(idH, i) {

      var DELIM = "__^$__";
      var kodUOM = $("#kodUOM" + i).val();
      var luas = $("#luasTerlibat" + i).val();
      var luasDitukar = "#LuasDitukar" + i;
      var AlertLebih = "#AlertLebih" + i;
      var LuasTinggal = "#LuasTinggal" + i;


  <%--alert(luasDitukar);--%>
      $.post('${pageContext.request.contextPath}/daftar/nota/nota_daftar?validateLuas&idHakmilik=' + idH + '&luas=' + luas + '&kodUOM=' + kodUOM,
              function(data) {



  <%--alert(data);--%>
                var p = data.split(DELIM);
  <%--alert(p[0]);--%>
  <%--$("#AlertLebih").html(p[0]);--%>
  <%--var value = sprintf("%01.2f", p[1]);--%>

                $(luasDitukar).replaceWith("<div id='LuasDitukar" + i + "' style='color:green'>Luas dalam " + p[3] + " :" + p[1] + "</div>");
  <%--alert(p[3]);--%>
  <%--alert(p[1]+":"+ p[3]);--%>
                var b = p[0];
  <%--alert(b);--%>
                if (b == 'false') {

                  $(LuasTinggal).replaceWith("<div id='LuasTinggal" + i + "' style='color:red'></div>");
                  $(AlertLebih).replaceWith("<div id='AlertLebih" + i + "' style='color:red'>Luas Terlibat Melebihi Luas Asal</div>");
                  $('#buttonSimpan').hide();
                }
                else {
                  $(LuasTinggal).replaceWith("<div id='LuasTinggal" + i + "' style='color:green'>Luas selepas ditolak :" + p[2] + "</div>");
                  $(AlertLebih).replaceWith("<div id='AlertLebih" + i + "' style='color:green'></div>");
                  $('#buttonSimpan').show();
                }
                function filterDaerah1(kodDaerah) {
                  alert(kodDaerah);
                  var url = '${pageContext.request.contextPath}/daftar/nota/nota_daftarV2?penyukatanBPM&daerah=' + kodDaerah;
                  alert(url);
                  $.get(url,
                          function(data) {
//                alert(data);
                            $('#daerah4').html(data);
                          }, 'html');
//            alert(daerah1);
                }



              }, 'html');

    }
    
    function validateNumber2(elmnt,content) {    
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }
    
    function removeNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }

</script>
<style type="text/css">
  input.text{
    width: 300px !important;
  }
</style>
<s:form beanclass="etanah.view.stripes.nota.NotaDaftarV2ActionBean" name="form1">

  <s:hidden name="kodUrusan" id="kodUrusan" value="${actionBean.permohonan.kodUrusan.kod}"/>
  <s:messages />
  <s:errors />
  <%--start build new interface accroding to Vdoc--%>
  <%--List Start: --%>

  <div class="subtitle">
    <fieldset class="aras1">
      <legend>Maklumat Nota</legend>
      <p style="color:red">
        *Isi Yang Berkenaan Sahaja.
      </p>
      <!--- Added by Aizuddin ----->          
      <c:if test="${!(actionBean.permohonan.kodUrusan.kod eq 'AEROD' || actionBean.permohonan.kodUrusan.kod eq 'IRTB'
                    ||actionBean.permohonan.kodUrusan.kod eq 'PMDPT' || actionBean.permohonan.kodUrusan.kod eq 'PMHHB'
                    || actionBean.permohonan.kodUrusan.kod eq 'JPB'
                    || actionBean.permohonan.kodUrusan.kod eq 'SWDB' || actionBean.permohonan.kodUrusan.kod eq 'IDMLB')}">
            <p>
              <label for="noFail">No. Rujukan Fail:</label>
              <s:text name="permohonanRujLuar.noFail" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
            </p>
      </c:if>


      <!--add by faidzal for PAT DEVELOPMENT!! -->
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SBTM' or actionBean.permohonan.kodUrusan.kod eq 'SBSTM'}">
        <p>
          <label for="noFail">No. Rujukan Fail:</label>
          <s:text name="permohonanRujLuar.noFail" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SWDB'}">
        <p>
          <label for="noFail">Sebab Pembatalan:</label>
          <s:textarea name="permohonanRujLuar.catatan"/>
        </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'IRTB'}">
        <p>
          <label>Nombor Warta :</label>
          <s:text name="permohonanRujLuar.noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Tarikh Warta :</label>
          <s:text name="tarikhRujukan" id="datepicker" class="datepicker"  size="35"/>
        </p>
        <p>
          <label>Kawasan :</label>
          <s:text name="permohonanRujLuar.item" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'IDMLB'}">
        <p>
          <label>Daerah Baru :</label>
          <s:select name="report_p_kod_daerah" id="report_p_kod_daerah" style="width:210px;" onchange="filterDaerah(this.value,this.form);">
            <c:if test="${actionBean.permohonanRujLuar.namaSidang eq 'null'}">
              <s:option value="">-- Sila Pilih --</s:option>
            </c:if>
            <c:if test="${actionBean.permohonanRujLuar.namaSidang ne 'null'}">
              <s:option value="report_p_kod_daerah">${actionBean.permohonanRujLuar.namaSidang} </s:option>
            </c:if>
            <s:options-collection collection="${listUtil.senaraiKodDaerahBaru}" label="nama" value="kod"/>
          </s:select>
        </p>
        <p>
          <label>Mukim Baru :</label>
          <s:select name="report_p_kod_bpm" id="report_p_kod_bpm" style="width:210px;">
            <c:if test="${actionBean.permohonanRujLuar.item eq 'null'}">
              <s:option value="">-- Sila Pilih --</s:option>
            </c:if>
            <c:if test="${actionBean.permohonanRujLuar.item ne 'null'}">
              <s:option value="report_p_kod_bpm">${actionBean.permohonanRujLuar.item} </s:option>
            </c:if>
            <s:options-collection collection="${actionBean.listBPM}" label="nama" value="kod"/>
          </s:select>
        </p>
        <p>
          <label>Nombor Warta :</label>
          <s:text name="permohonanRujLuar.noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Tarikh Warta :</label>
          <s:text name="tarikhRujukan" id="datepicker" class="datepicker" size="35"/>
        </p>
        <p>
          <label for="noFail">No. Rujukan Fail:</label>
          <s:text name="permohonanRujLuar.noFail" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'ADBSB'}">
        <p>
          <label>Tarikh Notis :</label>
          <s:text name="tarikhRujukan" id="tarikhRujukan"  class="datepicker" size="35"/>
        </p>
      </c:if>        

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'ADBSS'}">
        <p>
          <label>Tarikh Notis :</label>
          <s:text name="tarikhRujukan" id="tarikhRujukan"  class="datepicker" size="35"/>
        </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'ADBSL'}">
        <p>
          <label>No. Rujukan Surat Permohonan Polis :</label>
          <s:text name="permohonanRujLuar.noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <tr>
          <td colspan="6">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="6"><div align="center">Jika Perintah Mahkamah, masukkan maklumat berikut</div></td>
        </tr>
        <tr>
          <td colspan="6">&nbsp;</td>
        </tr>
        <p>
          <label>Nama Mahkamah :</label>
          <s:text name="permohonanRujLuar.namaSidang" class="text" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p> 
        <p>
          <label>No. Perintah Mahkamah:</label>
          <s:text name="permohonanRujLuar.noSidang" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Tarikh Mahkamah :</label>
          <s:text name="tarikhSidang" id="tarikhSidang" class="datepicker" size="35"/>
        </p>                
      </c:if>

      <c:if test ="${actionBean.permohonan.kodUrusan.kod eq 'PMHHB'}">
        <p>
          <label>Nama Mahkamah :</label>
          <s:text name="permohonanRujLuar.namaSidang" class="text" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p> 
        <p>
          <label>No. Perintah Mahkamah:</label>
          <s:text name="permohonanRujLuar.noSidang" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Tarikh Perintah :</label>
          <s:text name="tarikhSidang" id="tarikhSidang" class="datepicker" size="35"/>
        </p> 
        <p>
          <label>Sebab hidupkan hakmilik :</label>
          <s:textarea name="permohonanRujLuar.catatan" />
        </p>
      </c:if>
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'AEROD'}">
        <p>
          <label>Tarikh Perintah :</label>
          <s:text name="tarikhKKuasa" id="tarikhKKuasa"  class="datepicker" size="35"/>
        </p>
        <p>
          <label>Notis :</label>
          <s:text name="permohonanRujLuar.catatan"  onkeyup="this.value=this.value.toUpperCase();" size="35"/>
          <span style="color:red">*No Notis</span>
        </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LTDBL'}">
        <p class="tempoh" id="tempoh">
          <label>Tempoh :</label>
          <s:text name="tahun" class="tahun" id="tahun" value="${thn}" onchange="doCalcEndDate('tarikhKKuasa');" /> tahun &nbsp;
          <s:text name="bulan" class="bulan" id="bulan" value="${bln}" onchange="doCalcEndDate('tarikhKKuasa');" /> bulan &nbsp;
        </p> 
        <p>
          <label>Tarikh Mula :</label>
          <s:text name="tarikhKKuasa" class="datepicker" id="tarikhKKuasa" 
                  onchange="doCalcEndDate(this.id);" size="35"/>
        </p>
        <p class="tempoh">
          <label>Tarikh Tamat :</label>
          <s:text name="tarikhTamat" class="datepicker" formatType="date" 
                  formatPattern="dd/MM/yyyy" id="tarikhTamat" size="35"/>
        </p>            
        <p>
        </c:if>

        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LTSK'}">
        <p class="tempoh" id="tempoh">
          <label>Tempoh tahun:</label>
          <s:text name="tahun" class="tahun" id="tahun" value="${thn}" 
                  onchange="doCalcEndDate('tarikhKKuasa');" size="35"/> tahun &nbsp;
        </p> 
        <p>
          <label>Tarikh Mesyuarat MMK :</label>
          <s:text name="tarikhKKuasa" class="datepicker" id="tarikhKKuasa" 
                  onchange="doCalcEndDate(this.id);" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p class="tempoh">
          <label>Tarikh akhir :</label>
          <s:text name="tarikhTamat" class="datepicker" formatType="date" 
                  formatPattern="dd/MM/yyyy" id="tarikhTamat" size="35"/>
        </p>            
        <p>
        </c:if>

        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PSPBN'}">
        <p>
          <label>Tarikh Arahan PBN :</label>
          <s:text name="tarikhKKuasa" id="tarikhKKuasa"  class="datepicker" size="35"/>
        </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMM'}">
        <p>
          <label>Jenis Perintah :</label>
          <s:select name="perintah" id="perintah">
            <c:if test="${actionBean.permohonanRujLuar.kodPerintah eq 'null'}">
              <s:option value="">-- Sila Pilih --</s:option>
            </c:if>
            <c:if test="${actionBean.permohonanRujLuar.kodPerintah ne 'null'}">
              <s:option value="${actionBean.permohonanRujLuar.kodPerintah.nama}">${actionBean.permohonanRujLuar.kodPerintah.nama} </s:option>
            </c:if>
            <s:options-collection collection="${listUtil.senaraiKodPerintah}" label="nama" value="kod"/>
          </s:select>
        </p>
        <p>
          <label>No. Mahkamah:</label>
          <s:text name="permohonanRujLuar.noSidang" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Tarikh Perintah :</label>
          <s:text name="tarikhSidang" id="tarikhSidang" class="datepicker" size="35"/>
        </p>
        <p>
          <label>Nama Mahkamah :</label>
          <s:text name="permohonanRujLuar.namaSidang" class="text" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Tarikh Kuatkuasa :</label>
          <s:text name="tarikhKKuasa" id="tarikhKKuasa" class="datepicker" size="35"/>
        </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMTP'}">
        <p>
          <label>Tarikh Mula :</label>
          <s:text name="tarikhKKuasa" id="tarikhKKuasa" class="datepicker"  size="35"/>
        </p>
        <p>
          <label>Tarikh Akhir :</label>
          <s:text name="tarikhTamat" id="tarikhTamat" class="datepicker1" size="35"/>
        </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'CPB'}">
        <p>
          <label>No. Sijil:</label>
          <s:text name="permohonanRujLuar.noSidang" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Tarikh Kelulusan :</label>
          <s:text name="tarikhKKuasa" id="tarikhKKuasa" class="datepicker" size="35"/>
        </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMTPB'}">
        <p>
          <label>Tarikh Pembatalan :</label>
          <s:text name="tarikhTamat" id="tarikhTamat"  class="datepicker" size="35"/>
        </p>
      </c:if>
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SHKB'}">
        <p>
          <label>Tarikh Dimusnahkan :</label>
          <s:text name="tarikhTamat" id="tarikhTamat"  class="datepicker" size="35"/>
        </p>
      </c:if>
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SHSB'}">
        <p>
          <label>Tarikh Dimusnahkan :</label>
          <s:text name="tarikhTamat" id="tarikhTamat"  class="datepicker" size="35"/>
        </p>
      </c:if>
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSP' 
                    || actionBean.permohonan.kodUrusan.kod eq 'KPESL'
                    || actionBean.permohonan.kodUrusan.kod eq 'KPEBL'}">
        <p>
          <label>Penggunaan :</label>
          <s:text name="permohonanRujLuar.catatan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSP'}">
        <p>
          <label>Tarikh Lulus :</label>
          <s:text name="tarikhSidang" id="tarikhKKuasa" class="datepicker" onchange="doCalcEndDate(this.id);" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <!--azmi-->
        <p class="tempoh" id="tempoh">
          <s:hidden name="thn" class="tahun" id="tahun" value="4" onchange="doCalcEndDate('tarikhKKuasa');"/>
        </p> 
        <p class="tempoh">
          <s:hidden name="tarikhTamat" class="datepicker" formatType="date" 
                    formatPattern="dd/MM/yyyy" id="tarikhTamat"/>
        </p>
        </c:if>
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'KPESL'
              || actionBean.permohonan.kodUrusan.kod eq 'KPEBL'}">
        <p>
                <label>Kedalaman Tanah:</label>
                <s:text name="permohonanRujLuar.nilai2" size="10" onkeyup="validateNumber2(this,this.value);"/> 
              </p>
               <p>
                <label>Luas Isipadu:</label>
                <s:text name="permohonanRujLuar.nilai" size="10" onkeyup="validateNumber2(this,this.value);"/> 
              </p>
              </c:if>
        <!--azmi-->
      </c:if>
      <!---End of Orogenic--->   

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'N7A'
                    or actionBean.permohonan.kodUrusan.kod eq 'N7B'
                    or actionBean.permohonan.kodUrusan.kod eq 'N6A'
                    or actionBean.permohonan.kodUrusan.kod eq 'ADMNS'}">
            <p>
              <label for="noFail">No. Rujukan Fail:</label>
              <s:text name="permohonanRujLuar.noFail" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
            </p>
            <p>
              <label>Tarikh Disampai :</label>
              <s:text name="tarikhDisampai" class="datepicker" size="35"/>
            </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'N6A'}">
        <p>
          <label for="noFail">No. Rujukan Fail:</label>
          <s:text name="permohonanRujLuar.noFail" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Tarikh Notis / Warta :</label>
          <s:text name="tarikhRujukan" id="tarikhRujukan"  class="datepicker" size="35"/>
        </p>
        <p>
          <label>Nombor Warta :</label>
          <s:text name="permohonanRujLuar.noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
          <span style="color:red">*Kosongkan Jika Notis Bukan Notis Gantian</span>
        </p>
      </c:if>
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TMAMB'}">
        <p>
          <label>Jenis Perintah :</label>
          <s:select name="perintah" id="perintah">
            <c:if test="${actionBean.permohonanRujLuar.kodPerintah eq 'null'}">
              <s:option value="">-- Sila Pilih --</s:option>
            </c:if>
            <c:if test="${actionBean.permohonanRujLuar.kodPerintah ne 'null'}">
              <s:option value="${actionBean.permohonanRujLuar.kodPerintah.nama}">${actionBean.permohonanRujLuar.kodPerintah.nama} </s:option>
            </c:if>
            <s:options-collection collection="${listUtil.senaraiKodPerintah}" label="nama" value="kod"/>
          </s:select>
        </p>
        <p>
          <label>Nama Mahkamah :</label>
          <s:text name="permohonanRujLuar.namaSidang"  class="text" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>No. Perintah Mahkamah :</label>
          <s:text name="permohonanRujLuar.noSidang" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Tarikh Mahkamah :</label>
          <s:text name="tarikhSidang" id="tarikhSidang" class="datepicker" size="35"/>
        </p>
      </c:if>
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMDPT'}">
        <p>
          <label>Jenis Perintah :</label>
          <s:select name="perintah" id="perintah">
            <c:if test="${actionBean.permohonanRujLuar.kodPerintah eq 'null'}">
              <s:option value="">-- Sila Pilih --</s:option>
            </c:if>
            <c:if test="${actionBean.permohonanRujLuar.kodPerintah ne 'null'}">
              <s:option value="${actionBean.permohonanRujLuar.kodPerintah.nama}">${actionBean.permohonanRujLuar.kodPerintah.nama} </s:option>
            </c:if>
            <s:options-collection collection="${listUtil.senaraiKodPerintah}" label="nama" value="kod"/>
          </s:select>
        </p>
        <p>
          <label>Nama Mahkamah :</label>
          <s:text name="permohonanRujLuar.namaSidang" class="text" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>No. Perintah Mahkamah :</label>
          <s:text name="permohonanRujLuar.noSidang" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Tarikh Mahkamah :</label>
          <s:text name="tarikhSidang" id="tarikhSidang" class="datepicker" size="35"/>
        </p>
      </c:if>
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'ABT-D'
                    or actionBean.permohonan.kodUrusan.kod eq 'ABTBH'
                    or actionBean.permohonan.kodUrusan.kod eq 'ADAT'
                    or actionBean.permohonan.kodUrusan.kod eq 'CL'
                    or actionBean.permohonan.kodUrusan.kod eq 'FGT1'
                    or actionBean.permohonan.kodUrusan.kod eq 'FGT2'
                    or actionBean.permohonan.kodUrusan.kod eq 'FGT3'
                    or actionBean.permohonan.kodUrusan.kod eq 'FGT4'
                    or actionBean.permohonan.kodUrusan.kod eq 'FGT5'
                    or actionBean.permohonan.kodUrusan.kod eq 'FGT6'
                    or actionBean.permohonan.kodUrusan.kod eq 'FGT7'
                    or actionBean.permohonan.kodUrusan.kod eq 'FGT8'
                    or actionBean.permohonan.kodUrusan.kod eq 'FGT9'
                    or actionBean.permohonan.kodUrusan.kod eq 'HMV'
                    or actionBean.permohonan.kodUrusan.kod eq 'IGSA'
                    or actionBean.permohonan.kodUrusan.kod eq 'IGSA5'
                    or actionBean.permohonan.kodUrusan.kod eq 'IGSA6'
                    or actionBean.permohonan.kodUrusan.kod eq 'IROAB'
                    or actionBean.permohonan.kodUrusan.kod eq 'N7A'
                    or actionBean.permohonan.kodUrusan.kod eq 'N7B'
                    or actionBean.permohonan.kodUrusan.kod eq 'N8A'
                    or actionBean.permohonan.kodUrusan.kod eq 'PHKK'
                    or actionBean.permohonan.kodUrusan.kod eq 'PHSK'
                    or actionBean.permohonan.kodUrusan.kod eq 'PCT'
                    or actionBean.permohonan.kodUrusan.kod eq 'PPKR'
                    or actionBean.permohonan.kodUrusan.kod eq 'ACT'
                    or actionBean.permohonan.kodUrusan.kod eq 'ABTS'
                    or actionBean.permohonan.kodUrusan.kod eq 'ITP'
                    or actionBean.permohonan.kodUrusan.kod eq 'PTB'
                    or actionBean.permohonan.kodUrusan.kod eq 'PTP'
                    or actionBean.permohonan.kodUrusan.kod eq 'ITB'
                    or actionBean.permohonan.kodUrusan.kod eq 'IRMB'
                    or actionBean.permohonan.kodUrusan.kod eq 'KB'
                    or actionBean.permohonan.kodUrusan.kod eq 'MAJD'
                    or actionBean.permohonan.kodUrusan.kod eq 'KOSR'
                    or actionBean.permohonan.kodUrusan.kod eq 'MAJB'
                    or actionBean.permohonan.kodUrusan.kod eq 'PK'
                    or actionBean.permohonan.kodUrusan.kod eq 'PKB'
                    or actionBean.permohonan.kodUrusan.kod eq 'ADMRL'
                    or actionBean.permohonan.kodUrusan.kod eq 'STMA'
                    or actionBean.permohonan.kodUrusan.kod eq 'TMA'
                    or actionBean.permohonan.kodUrusan.kod eq 'TBTWK'
                    or actionBean.permohonan.kodUrusan.kod eq 'AEROD'
                    or actionBean.permohonan.kodUrusan.kod eq 'IKOA'
                    or actionBean.permohonan.kodUrusan.kod eq 'IROA'
                    or actionBean.permohonan.kodUrusan.kod eq 'IRM'
                    or actionBean.permohonan.kodUrusan.kod eq 'KRM'
                    or actionBean.permohonan.kodUrusan.kod eq 'IPM'
            }">
        <div id="W">
          <%--<p>
            <label for="noFail">No. Rujukan Fail:</label>
            <s:text name="permohonanRujLuar.noFail" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
          </p>--%>
          <p>
            <label>Nombor Warta :</label>
            <s:text name="permohonanRujLuar.noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35" />
          </p>
          <p>
            <label>Tarikh Warta :</label>
            <s:text name="tarikhRujukan" id="datepicker" class="datepicker" size="35" />
          </p>
        </div>
      </c:if>      

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'ABT-K'
                    or actionBean.permohonan.kodUrusan.kod eq 'ABTKB'}">
            <div id="W">
              <p>
                <label for="noFail">No. Rujukan Fail:</label>
                <s:text name="permohonanRujLuar.noFail" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
              </p>
              <p>
                <label>Nombor Warta (Borang D):</label>
                <s:text name="permohonanRujLuar.noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
              </p>
              <p>
                <label>Tarikh Warta (Borang D):</label>
                <s:text name="tarikhRujukan" id="datepicker" class="datepicker"  size="35"/>
              </p>
            </div>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SBTL'
                    or    actionBean.permohonan.kodUrusan.kod eq 'HTBKR'
                    or actionBean.permohonan.kodUrusan.kod eq 'MCLL'
                    or actionBean.permohonan.kodUrusan.kod eq 'SBKSL'
                    or actionBean.permohonan.kodUrusan.kod eq 'TSSKL'
                    or actionBean.permohonan.kodUrusan.kod eq 'ABTS'
                    or actionBean.permohonan.kodUrusan.kod eq 'PBCTL'
                    or actionBean.permohonan.kodUrusan.kod eq 'PSL'
                    or actionBean.permohonan.kodUrusan.kod eq 'SSKPL'
                    or actionBean.permohonan.kodUrusan.kod eq 'SBSTL'
                    or actionBean.permohonan.kodUrusan.kod eq 'SBTL'
                    or actionBean.permohonan.kodUrusan.kod eq 'KCL'}">
            <p>
              <label for="noFail">No. Rujukan Fail:</label>
              <s:text name="permohonanRujLuar.noFail" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
            </p>
            <p>
              <label>No. Mesyuarat:</label>
              <s:text name="permohonanRujLuar.noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
            </p>
            <p>
              <label>Tarikh Mesyuarat :</label>
              <s:text name="tarikhRujukan" id="tarikhRujukan" class="datepicker" size="35"/>
            </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PB'}">
        <p>
          <label>Tarikh Mula :</label>
          <s:text name="tarikhKKuasa" id="tarikhKKuasa" class="datepicker" size="35" />
        </p>
      </c:if>
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'MCLL'
                    or actionBean.permohonan.kodUrusan.kod eq 'SBKSL'
                    or actionBean.permohonan.kodUrusan.kod eq 'ABT-K'
                    or actionBean.permohonan.kodUrusan.kod eq 'TSSKL'
                    or actionBean.permohonan.kodUrusan.kod eq 'ABTS'
                    or actionBean.permohonan.kodUrusan.kod eq 'PBCTL'
                    or actionBean.permohonan.kodUrusan.kod eq 'PSL'
                    or actionBean.permohonan.kodUrusan.kod eq 'SSKPL'
                    or actionBean.permohonan.kodUrusan.kod eq 'SBSTL'
                    or actionBean.permohonan.kodUrusan.kod eq 'SBTL'
                    or actionBean.permohonan.kodUrusan.kod eq 'KCL'}">
            <p>
              <label for="noFail">No. Rujukan Fail:</label>
              <s:text name="permohonanRujLuar.noFail" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
            </p>
            <p>
              <label>Yang Meluluskan :</label>
              <s:text name="permohonanRujLuar.noSidang" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
            </p>
            <p>
              <label>Tarikh Kelulusan :</label>
              <s:text name="tarikhSidang" id="tarikhSidang" class="datepicker" size="35"/>
            </p>
      </c:if>
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRPMB'
                    or actionBean.permohonan.kodUrusan.kod eq 'N8AB'
                    or actionBean.permohonan.kodUrusan.kod eq 'RPBNB'}">
            <p>
              <label for="noFail">No. Rujukan Fail:</label>
              <s:text name="permohonanRujLuar.noFail" onkeyup="this.value=this.value.toUpperCase();" size="35" />
            </p>
            <p>
              <label>No. Perintah Mahkamah:</label>
              <s:text name="permohonanRujLuar.noSidang" onkeyup="this.value=this.value.toUpperCase();" size="35" />
            </p>
            <p>
              <label>Tarikh Mahkamah :</label>
              <s:text name="tarikhSidang" id="tarikhSidang" class="datepicker" size="35"/>
            </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'JMB' 
                    or actionBean.permohonan.kodUrusan.kod eq 'JPB'}">
            <p>
              <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'JPB'}"><label>Nama Pejabat Tanah :</label></c:if>    
              <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'JMB'}"><label>Nama Mahkamah :</label></c:if>                  
              <s:text name="permohonanRujLuar.namaSidang" class="text" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
            </p>
            <p>
              <label>No. Perintah :</label>
              <s:text name="permohonanRujLuar.noSidang" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
            </p>
            <p>
              <label>Tarikh Perintah :</label>
              <s:text name="tarikhSidang" id="tarikhSidang" class="datepicker" size="35" />
            </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PCT'}">
        <p>
          <label for="noFail">No. Rujukan Fail:</label>
          <s:text name="permohonanRujLuar.noFail" onkeyup="this.value=this.value.toUpperCase();" size="35" />
        </p>
        <p>
          <label>Tarikh Mula :</label>
          <s:text name="tarikhKKuasa" id="tarikhKKuasa" class="datepicker1" size="35" />
        </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PREM'}">
        <p>
          <label for="noFail">No. Rujukan Fail:</label>
          <s:text name="permohonanRujLuar.noFail" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Nama Mahkamah :</label>
          <s:text name="permohonanRujLuar.namaSidang" class="text" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>No. Mahkamah :</label>
          <s:text name="permohonanRujLuar.noSidang" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Tarikh Mahkamah :</label>
          <s:text name="tarikhSidang" id="tarikhSidang" class="datepicker" size="35"/>
        </p>
        <p>
          <label>No. Mesyuarat/Warta/Buku Strata :</label>
          <s:text name="permohonanRujLuar.noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Tarikh Warta/Buku Strata/Notis :</label>
          <s:text name="tarikhRujukan" id="tarikhRujukan" class="datepicker" size="35" />
        </p>
        <p>
          <label>Tarikh Kelulusan Mesyuarat :</label>
          <s:text name="tarikhKelulusan" id="tarikhKelulusan" class="datepicker" size="35" />
        </p>
        <p>
          <label>Tarikh Notis Diserahkan :</label>
          <s:text name="tarikhDisampai" id="tarikhDisampai" class="datepicker" size="35" />
        </p>
        <p>
          <label>Tempoh :</label>
          <s:text name="permohonanRujLuar.tempohTahun" style="width:25px;"/>Tahun &nbsp;&nbsp;
          <s:text name="permohonanRujLuar.tempohBulan" style="width:25px;"/>Bulan &nbsp;&nbsp;
          <s:text name="permohonanRujLuar.tempohHari" style="width:25px;"/>Hari &nbsp;&nbsp;
        </p>
        <p>
          <label>Tarikh Mula :</label>
          <s:text name="tarikhKKuasa" id="tarikhKKuasa" class="datepicker" size="35" />
        </p>
        <p>
          <label>Tarikh Tamat :</label>
          <s:text name="tarikhTamat" id="tarikhTamat" class="datepicker" size="35" />
        </p>
        <p>
          <label>Kawasan :</label>
          <s:text name="permohonanRujLuar.item" class="text" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'ADMRL'}">
        <p>
          <label for="noFail">No. Rujukan Fail:</label>
          <s:text name="permohonanRujLuar.noFail" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>No. Jilid :</label>
          <s:text name="permohonanRujLuar.item" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>No. Folio :</label>
          <s:text name="permohonanRujLuar.ulasan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Sebab :</label>
          <s:textarea name="permohonanRujLuar.catatan" />
        </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'N6AB'
                    or actionBean.permohonan.kodUrusan.kod eq 'N8AB'
                    or actionBean.permohonan.kodUrusan.kod eq 'LPBB'
                    or actionBean.permohonan.kodUrusan.kod eq 'RPBNB'}">

      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'HTB'}">
        <p>
          <label>No. Buku Strata  :</label>
          <s:text name="permohonanRujLuar.noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35" readonly="${actionBean.readonly}"/>
        </p>
        <p>
          <label>Tarikh Buku :</label>
          <s:text name="tarikhRujukan" id="datepicker" class="datepicker" size="35" readonly="${actionBean.readonly}"/>
        </p>
        <p>
          <label>Sebab Penutupan :</label>
          <s:textarea name="permohonanRujLuar.catatan"style="height: 100px; width: 145px"></s:textarea>
          </p>
          <p>
            <label>Nama (Perbadanan Pengurusan) :</label>
          <s:textarea name="nama" style="height: 100px; width: 145px"></s:textarea>
          </p>
          <p>
            <label>No. Perbadanan Pengurusan :</label>
          <s:text name="noPengenalan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Alamat :</label>
          <s:text name="alamat1" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label> &nbsp;</label>
          <s:text name="alamat2" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>&nbsp;</label>
          <s:text name="alamat3" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>&nbsp;</label>
          <s:text name="alamat4" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Poskod :</label>
          <s:text name="poskod" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Negeri :</label>
          <s:select name="negeri" id="negeri">
            <c:if test="${actionBean.p.negeri.kod eq 'null'}">
              <s:option value="">-- Sila Pilih --</s:option>
            </c:if>
            <c:if test="${actionBean.p.negeri.kod ne 'null'}">
              <s:option value="${actionBean.p.negeri.nama}">${actionBean.p.negeri.nama} </s:option>
            </c:if>
            <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
          </s:select>
        </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'HTT'}">
        <p>
          <label>No. Buku Strata  :</label>
          <s:text name="permohonanRujLuar.noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35" readonly="${actionBean.readonly}"/>
        </p>
        <p>
          <label>Tarikh Buku :</label>
          <s:text name="tarikhRujukan" id="datepicker" class="datepicker" size="35" readonly="${actionBean.readonly}"/>
        </p>
        <p>
          <label>Sebab Penutupan :</label>
          <s:textarea name="permohonanRujLuar.catatan"style="height: 100px; width: 250px"></s:textarea>
          </p>
          <p>
        </c:if>
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'HTBKR'}">
        <p>
          <label>No. Buku Daftar Strata :</label>
          <s:text name="permohonanRujLuar.noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35" readonly="${actionBean.readonly}"/>
        </p>
        <p>
          <label>Tarikh Buku :</label>
          <s:text name="tarikhRujukan" id="datepicker" class="datepicker" size="35" readonly="${actionBean.readonly}"/>
        </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LTS'}">
        <p>
          <label>Tempoh :</label>
          <s:text name="tahun" id="tahun" class="tahun" value="${thn}" style="width:25px;"  />Tahun &nbsp;&nbsp;
          <s:text name="bulan" id="bulan" class="bulan" value="${bln}" style="width:25px;"  />Bulan &nbsp;&nbsp;
          <s:text name="hari" id="hari" class="hari" value="${hari}" style="width:25px;"  />Hari &nbsp;&nbsp;
        </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'IGSA'
                    or actionBean.permohonan.kodUrusan.kod eq 'IGSA5'
                    or actionBean.permohonan.kodUrusan.kod eq 'IGSA6'
                    or actionBean.permohonan.kodUrusan.kod eq 'ITP'
                    or actionBean.permohonan.kodUrusan.kod eq 'ITB'
                    or actionBean.permohonan.kodUrusan.kod eq 'IRM'
                    or actionBean.permohonan.kodUrusan.kod eq 'KRM'
                    or actionBean.permohonan.kodUrusan.kod eq 'IPM'
                    or actionBean.permohonan.kodUrusan.kod eq 'IROA'
                    or actionBean.permohonan.kodUrusan.kod eq 'RKSR'
                    or actionBean.permohonan.kodUrusan.kod eq 'PTP'
                    or actionBean.permohonan.kodUrusan.kod eq 'PTB'
                    or actionBean.permohonan.kodUrusan.kod eq 'KOSR'
                    or actionBean.permohonan.kodUrusan.kod eq 'KB'}">
            <p>
              <label>Kawasan :</label>
              <s:text name="permohonanRujLuar.item" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
            </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'IRM'}"> 
        <br>
        <div class="content" align="center" >
          <display:table class="tablecloth" requestURI="/common/maklumat_hakmilik_permohonan" 
                         name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="line">
            <display:column title="No" sortable="true">${line_rowNum}</display:column>
            <display:column title="ID Hakmilik">
              ${line.hakmilik.idHakmilik}
              <s:hidden name="hiddenIdHakmilik" id="hiddenIdHakmilik${line_rowNum-1}" value="${line.hakmilik.idHakmilik}"/>
            </display:column>                       
            <display:column title="Cukai">
              RM ${line.hakmilik.cukai}
            </display:column>
            <display:column title="Cukai Dipinda Kepada">
              RM <s:text name="hakmilikPermohonanList[${line_rowNum-1}].cukaiBaru" 
                      formatPattern="###0" value="${line.cukaiBaru}" id="cukai${line_rowNum-1}" />               
            </display:column>            
          </display:table>
        </div>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'KBB'}">
        <p>
          <label>Nombor Warta :</label>
          <s:text name="permohonanRujLuar.noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Tarikh Warta :</label>
          <s:text name="tarikhRujukan" id="datepicker" class="datepicker" size="35" />
        </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'MAJD'}">
        <p>
          <label>Kawasan Majlis Daerah :</label>
          <s:text name="permohonanRujLuar.item" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPKR'}">
        <p>
          <label>Perbadanan :</label>
          <s:textarea name="nama"></s:textarea>
          </p>
      </c:if>
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'MAJB'}">
        <p>
          <label>Kawasan Majlis Perbandaran :</label>
          <s:text name="permohonanRujLuar.item" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
      </c:if>
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'ABTB'
                    or actionBean.permohonan.kodUrusan.kod eq 'PHKK'
                    or actionBean.permohonan.kodUrusan.kod eq 'BETHM'
                    or actionBean.permohonan.kodUrusan.kod eq 'BETP'
                    or actionBean.permohonan.kodUrusan.kod eq 'BETC'
                    or actionBean.permohonan.kodUrusan.kod eq 'BETLP'
                    or actionBean.permohonan.kodUrusan.kod eq 'BETEN'
                    or actionBean.permohonan.kodUrusan.kod eq 'BETPB'
                    or actionBean.permohonan.kodUrusan.kod eq 'BETST'
                    or actionBean.permohonan.kodUrusan.kod eq 'BETSW'
                    or actionBean.permohonan.kodUrusan.kod eq 'BETUL'}">
            <p>
              <label>Sebab Pembetulan:</label>
              <s:textarea name="permohonanRujLuar.catatan" rows="5" cols="40" />
            </p>
      </c:if>
      <%--phkk--%>
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PHKK'
                    or actionBean.permohonan.kodUrusan.kod eq 'PHSK'}">
            <p>
              <label>Sebab :</label>
              <s:select name="permohonanRujLuar.catatan" >
                <s:option value="HAKMILIK ROSAK">HAKMILIK ROSAK</s:option>
                <s:option value="HAKMILIK HILANG">HAKMILIK HILANG</s:option>
              </s:select>
            </p>
      </c:if>
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'HLLS'}">
        <p>
          <label>Bagi Kegunaan:</label>
          <s:text name="permohonanRujLuar.catatan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
      </c:if>
      <br/>
      <div align="center">
        <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="simpanMaklumatluascukaisyarat" value="Simpan"/>
      </div>
      <br/>
    </fieldset>
  </div>
</s:form>