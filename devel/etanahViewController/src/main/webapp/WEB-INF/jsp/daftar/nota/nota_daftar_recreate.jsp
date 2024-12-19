<%--
    Document   : nota_daftar_luascukaisyarat
    Created on : Dec 31, 2009, 2:06:56 PM
    Author     : mohd.fairul
--%>
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

    function popupCarian(pathCall, boxName, flagCarian) {
      var valTambah = "0";
      var addPath = "";
      if (flagCarian == "sekatan") {
        var elementName = "&inputName=strKodSekatan[" + boxName + "]";
        var strKodSekatan = $('input:text[name=strKodSekatan[' + boxName + ']]').val();

        valTambah = strKodSekatan;
        addPath = elementName;
        var idhakmilik = $('#hiddenIdHakmilik' + boxName).val();
        addPath = addPath + '&idHakmilik=' + idhakmilik;
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

    function add(event, f) {
      var kodSyaratNyata = $('input:text[name=kodSyaratNyata]').val();
      var kodSekatan = $('input:text[name=kodSekatan]').val();

      alert(kodSekatan);

      var kodOUM = $('#kodLuas').val();
      if (kodOUM == "") {
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
      if (confirm("Anda Pasti?")) {
        var url = "${pageContext.request.contextPath}/daftar/nota/nota_daftar?padam&id=" + p;
        $.post(url,
                function(data) {
                  $('#page_div', opener.document).html(data);
                }, 'html');
      }
      else {
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
    function pickPut2(nama, noPengenalan, salinan, kodUrusan, idPihak, idHakmilik, syerPembilang, syerPenyebut)
    {

      var url = "${pageContext.request.contextPath}/daftar/nota/nota_daftar?savePemegangSalinan&idPihak=" + idPihak + "&idHakmilik=" + idHakmilik + "&syerPembilang=" + syerPembilang + "&syerPenyebut=" + syerPenyebut;
      doBlockUI();

      $.ajax({
        type: "GET",
        url: url,
        dataType: 'html',
        error: function(xhr, ajaxOptions, thrownError) {
          alert("error=" + xhr.responseText);
          doUnBlockUI();
        },
        success: function(data) {
          $('#page_div').html(data);
          doUnBlockUI();
        }
      });
    }

    function deletePemohon(idMohonPihak) {

      if (confirm("Anda Pasti?")) {
        var url = "${pageContext.request.contextPath}/daftar/nota/nota_daftar?deletePemohon&idMohonPihak=" + idMohonPihak;
        doBlockUI();

        $.ajax({
          type: "GET",
          url: url,
          dataType: 'html',
          error: function(xhr, ajaxOptions, thrownError) {
            alert("error=" + xhr.responseText);
            doUnBlockUI();
          },
          success: function(data) {
            $('#page_div').html(data);
            doUnBlockUI();
          }
        });
      }
    }

    function deleteSekatanBaru(id) {
      if (confirm("Anda Pasti?")) {
        var url = '${pageContext.request.contextPath}/daftar/nota/nota_daftar?deleteKodSekatanBaru&idHp=' + id;
        doBlockUI();

        $.ajax({
          type: "GET",
          url: url,
          dataType: 'html',
          error: function(xhr, ajaxOptions, thrownError) {
            alert("error=" + xhr.responseText);
            doUnBlockUI();
          },
          success: function(data) {
            $('#page_div').html(data);
            doUnBlockUI();
          }
        });
      }
    }

    function deleteKodSyaratNyataBaru(id) {
      if (confirm("Anda Pasti?")) {
        var url = '${pageContext.request.contextPath}/daftar/nota/nota_daftar?deleteKodSyaratNyata&idHp=' + id;
        doBlockUI();

        $.ajax({
          type: "GET",
          url: url,
          dataType: 'html',
          error: function(xhr, ajaxOptions, thrownError) {
            alert("error=" + xhr.responseText);
            doUnBlockUI();
          },
          success: function(data) {
            $('#page_div').html(data);
            doUnBlockUI();
          }
        });
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

    function validateLuas(idH, i) {
      var DELIM = "__^$__";
      var kodUOM = $("#kodUOM" + i).val();
      var luas = $("#luasTerlibat" + i).val();
      var luasDitukar = "#LuasDitukar" + i;
      var AlertLebih = "#AlertLebih" + i;
      var LuasTinggal = "#LuasTinggal" + i;
      $('#LuasDitukar1').hide();
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
              }, 'html');
    }

    function reload(val) {
      doBlockUI();
      var url = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?reload&idHakmilik=' + val;
      $.ajax({
        type: "GET",
        url: url,
        dataType: 'html',
        error: function(xhr, ajaxOptions, thrownError) {
          alert("error=" + xhr.responseText);
          doUnBlockUI();
        },
        success: function(data) {
          $('#page_div').html(data);
          doUnBlockUI();
        }
      });
    }

    function doCalcEndDate(id) {
      var hari = parseInt($('.hari1').val(), 10);
      var bln = parseInt($('.bulan1').val(), 10);
      var thn = parseInt($('.tahun1').val(), 10);
      var kodUrusan = $('#kodUrusan').val();
      if ($('#' + id).val() == '') {
        return;
      }
      if (isNaN(thn)) {
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
      if (!isNaN(thn)) {
        y = y + thn;
      }
      var endDate = new Date();
      endDate.setDate(d);
      endDate.setMonth(m);
      endDate.setFullYear(y);
      endDate.setDate(endDate.getDate() - 1);
      $('#tkhTamat').val(endDate.getDate() + '/' + endDate.getMonth() + '/' + endDate.getFullYear());
//      $('#tkhTamat').val(endDate.format("dd/mm/yyyy"));
    }
</script>      
<style type="text/css">
  input.text{
    width: 300px !important;
  }
</style>
<s:form beanclass="etanah.view.stripes.nota.NotaDaftarActionBean" name="form1">
  <s:messages />
  <s:errors />
  <%--start build new interface accroding to Vdoc--%>
  <%--List Start: --%>

  <div class="subtitle">
    <fieldset class="aras1">
      <c:choose >
        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'KVSPT'}">
          <legend>Maklumat Rujukan Fail</legend><br>
        </c:when>
        <c:otherwise>
          <legend>Maklumat Nota</legend>
          <p style="color:red">
            *Isi Yang Berkenaan Sahaja.
          </p>
        </c:otherwise>
      </c:choose>
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'KVSPT'}">
        <p>
          <label>No. Rujukan Fail :</label>
          <s:text name="permohonanRujLuar.noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35" />
        </p>
      </c:if>

      <!--- Added by Aizuddin ----->          
      <c:if test="${!(actionBean.permohonan.kodUrusan.kod eq 'AEROD' || actionBean.permohonan.kodUrusan.kod eq 'IRTB')}">
        <c:choose>
          <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'KVSPT'}">
            <p>
              <label for="noFail">No. Fail :</label>
              <s:text name="permohonanRujLuar.noFail" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
            </p>
          </c:when>
          <c:otherwise>
            <p>
              <label for="noFail">No. Rujukan Fail :</label>
              <s:text name="permohonanRujLuar.noFail" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
            <text style="color:red"> * </text>
            </p>
          </c:otherwise>
        </c:choose>
      </c:if>  

      <!--add by azri: change according to Pembangguan integration request-->
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'CB'}">
        <p>
          <label>No. Mesyuarat:</label>
          <s:text name="permohonanRujLuar.noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Tarikh Mesyuarat :</label>
          <s:text name="tarikhRujukan" id="tarikhRujukan" class="datepicker" size="35"/>
        </p>
        <p>
          <label>Yang Menolak :</label>
          <s:text name="permohonanRujLuar.noSidang" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Tarikh Tolak :</label>
          <s:text name="tarikhSidang" id="tarikhSidang" class="datepicker" size="35"/>
        </p>
      </c:if><!--END-->

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'IRTB'}">
        <!--add by faidzal for PAT DEVELOPMENT!! -->
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SBTM' or actionBean.permohonan.kodUrusan.kod eq 'SBSTM'}">
          <p>
            <label for="noFail">No. Rujukan Fail :</label>
            <s:text name="permohonanRujLuar.noFail" onkeyup="this.value=this.value.toUpperCase();" size="35"></s:text>
            </p>
        </c:if>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'IRTB'}">
        <p>
          <label>Nombor Warta :</label>
          <s:text name="permohonanRujLuar.noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35" />
        </p>
        <p>
          <label>Tarikh Warta :</label>
          <s:text name="tarikhRujukan" id="datepicker" class="datepicker" size="35"  />
        </p>
        <p>
          <label>Kawasan :</label>
          <s:text name="permohonanRujLuar.item" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'N7A'
                    or actionBean.permohonan.kodUrusan.kod eq 'N7B'
                    or actionBean.permohonan.kodUrusan.kod eq 'N6A'
                    or actionBean.permohonan.kodUrusan.kod eq 'ADMNS'}">
            <p>
              <label>Tarikh Disampai :</label>
              <s:text name="tarikhDisampai" class="datepicker" size="35"/>
            </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'N6A'}">
        <p>
          <label>Tarikh Notis / Warta :</label>
          <s:text name="tarikhRujukan" id="tarikhRujukan"  class="datepicker" size="35"/>
        </p>
        <p>
          <label>Nombor Warta :</label>
          <s:text name="permohonanRujLuar.noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
          <span style="color:red"> * Kosongkan Jika Notis Bukan Notis Gantian</span>
        </p>
      </c:if>

      <!---Add by Aizuddin Orogenic--->
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'AEROD'}">
        <p>
          <label>Tarikh Perintah :</label>
          <s:text name="tarikhKKuasa" id="tarikhKKuasa"  class="datepicker" size="35"/>
        </p>
        <p>
          <label>Notis :</label>
          <s:text name="permohonanRujLuar.catatan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
          <span style="color:red">*No Notis</span>
        </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'ABT-D'
                    or actionBean.permohonan.kodUrusan.kod eq 'ABTBH'
                    or actionBean.permohonan.kodUrusan.kod eq 'ADAT'
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
                    or actionBean.permohonan.kodUrusan.kod eq 'PHSK'
                    or actionBean.permohonan.kodUrusan.kod eq 'PCT'
                    or actionBean.permohonan.kodUrusan.kod eq 'PPKR'
                    or actionBean.permohonan.kodUrusan.kod eq 'ACT'                          
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
                    or actionBean.permohonan.kodUrusan.kod eq 'STMA'
                    or actionBean.permohonan.kodUrusan.kod eq 'TMA'
                    or actionBean.permohonan.kodUrusan.kod eq 'TBTWK'
                    or actionBean.permohonan.kodUrusan.kod eq 'AEROD'
                    or actionBean.permohonan.kodUrusan.kod eq 'IKOA'
                    or actionBean.permohonan.kodUrusan.kod eq 'IROA'
                    or actionBean.permohonan.kodUrusan.kod eq 'IRM'
                    or actionBean.permohonan.kodUrusan.kod eq 'KRM'
                    or actionBean.permohonan.kodUrusan.kod eq 'IPM'}">
            <div id="W">
              <p>
                <label>Nombor Warta :</label>
                <s:text name="permohonanRujLuar.noRujukan" size="35"/>
              </p>
              <p>
                <label>Tarikh Warta :</label>
                <s:text name="tarikhRujukan" id="datepicker" class="datepicker" size="35" />
              </p>
            </div>
      </c:if>
        
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'ECPI'}">
          <div id="W">
              <p>
                <label>Nombor Warta : No. M. P.U.</label>
                <s:text name="permohonanRujLuar.noRujukan" size="35"/>
                <text style="color:red"> * </text>
              </p>
              <p>
                <label>Tarikh Warta :</label>
                <s:text name="tarikhRujukan" id="datepicker" class="datepicker" size="35" />
                <text style="color:red"> * </text>
              </p>
            </div>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'N7A' 
                    || actionBean.permohonan.kodUrusan.kod eq 'N7B' 
                    || actionBean.permohonan.kodUrusan.kod eq 'N8A'}">
            <p>
              <label>Nombor Warta :</label>
              <s:text name="permohonanRujLuar.noRujukan" size="35"/>
            </p>
            <p>
              <label>Tarikh Warta :</label>
              <s:text name="tarikhRujukan" id="datepicker" class="datepicker" size="35" />
            </p> 
            <p>
              <label>Tarikh Tampal :</label>
              <s:text name="tarikhKKuasa" id="tarikhKKuasa" class="datepicker1" size="35" />
            </p> 
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PHKK'}">
        <div id="W">
          <p>
            <label>Nombor Warta :</label>
            <s:text name="permohonanRujLuar.noRujukan" size="35"/>
          <text style="color:red"> * </text>
          <p>
            <label>Tarikh Warta :</label>
            <s:text name="tarikhRujukan" id="datepicker" class="datepicker" size="35" />
          <text style="color:red"> * </text>
          </p>
        </div>
      </c:if>
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'ABT-K'
                    or actionBean.permohonan.kodUrusan.kod eq 'ABTKB'}">
            <div id="W">              
              <p>
                <label>Nombor Warta (Borang K):</label>
                <s:text name="permohonanRujLuar.noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
              </p>
              <p>
                <label>Tarikh Warta (Borang K):</label>
                <s:text name="tarikhRujukan" id="datepicker" class="datepicker" size="35" />
              </p>
            </div>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'MCLL'}">
        <p>
          <label>No. Mesyuarat:</label>
          <s:text name="permohonanRujLuar.noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Tarikh Mesyuarat :</label>
          <s:text name="tarikhRujukan" id="tarikhRujukan" class="datepicker" size="23"/>
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

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SSKPL'}">
        <p>
          <label>No. Mesyuarat:</label>
          <s:text name="permohonanRujLuar.noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Tarikh Mesyuarat :</label>
          <s:text name="tarikhRujukan" id="tarikhRujukan" class="datepicker" size="35"/>
        </p>
        <p>
          <label>Yang Meluluskan :</label>
          <s:text name="permohonanRujLuar.noSidang" onkeyup="this.value=this.value.toUpperCase();" size="55"/>
        </p>
        <p>
          <label>Tarikh Kelulusan :</label>
          <s:text name="tarikhSidang" id="tarikhSidang" class="datepicker" size="35"/>
        </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SBTL'
                    or actionBean.permohonan.kodUrusan.kod eq 'HTBKR'
                    or actionBean.permohonan.kodUrusan.kod eq 'SBKSL'
                    or actionBean.permohonan.kodUrusan.kod eq 'TSSKL'
                    or actionBean.permohonan.kodUrusan.kod eq 'CL'
                    or actionBean.permohonan.kodUrusan.kod eq 'PSL'
                    or actionBean.permohonan.kodUrusan.kod eq 'SBSTL'
                    or actionBean.permohonan.kodUrusan.kod eq 'SBTL'}">
            <p>
              <label>No. Mesyuarat:</label>
              <s:text name="permohonanRujLuar.noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
            </p>
            <p>
              <label>Tarikh Mesyuarat :</label>
              <s:text name="tarikhRujukan" id="tarikhRujukan" class="datepicker" size="35"/>
            </p>
      </c:if>

      <!--                  Kelulusan Pengurangan/Pindaan Cukai        -->
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'KCL'}">
        <p>
          <label>No. Mesyuarat:</label>
          <s:text name="permohonanRujLuar.noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Tarikh Mesyuarat :</label>
          <s:text name="tarikhRujukan" id="tarikhRujukan" class="datepicker" size="35"/>
        </p>
        <p>
          <label>Yang Meluluskan :</label>
          <s:text name="permohonanRujLuar.noSidang" onkeyup="this.value=this.value.toUpperCase();" size="60"/>
        </p>
        <p>
          <label>Tarikh Kelulusan :</label>
          <s:text name="tarikhSidang" id="tarikhSidang" class="datepicker" size="35"/>
        </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBCTL'}">
        <p>
          <label>Yang Meluluskan :</label>
          <s:text name="permohonanRujLuar.noSidang" onkeyup="this.value=this.value.toUpperCase();" size="60"/>
        </p>
        <p>
          <label>Tarikh Kelulusan :</label>
          <s:text name="tarikhSidang" id="tarikhSidang" class="datepicker" size="35"/>
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

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'ITD'}">
        <p>
          <label>Nombor Warta :</label>
          <s:text name="permohonanRujLuar.noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Tarikh Warta :</label>
          <s:text name="tarikhRujukan" id="datepicker" class="datepicker" size="35"/>
        </p>
        <p>
          <label>Kawasan :</label>
          <s:text name="permohonanRujLuar.item" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
      </c:if> 

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'ABTS'}">
        <p>
          <label>Nombor Warta :</label>
          <s:text name="permohonanRujLuar.noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Tarikh Warta :</label>
          <s:text name="tarikhRujukan" id="datepicker" class="datepicker" size="35"/>
        </p>
        <p>
          <label>No. Mesyuarat:</label>
          <s:text name="permohonanRujLuar.noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Tarikh Mesyuarat :</label>
          <s:text name="tarikhRujukan" id="tarikhRujukan" class="datepicker" size="35"/>
        </p>
        <p>
          <label>Yang Meluluskan :</label>
          <s:text name="permohonanRujLuar.noSidang" onkeyup="this.value=this.value.toUpperCase();" size="60"/>
        </p>
        <p>
          <label>Tarikh Kelulusan :</label>
          <s:text name="tarikhSidang" id="tarikhSidang" class="datepicker" size="35"/>
        </p>
      </c:if>  

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SBKSL'
                    or actionBean.permohonan.kodUrusan.kod eq 'ABT-K'
                    or actionBean.permohonan.kodUrusan.kod eq 'TSSKL'                          
                    or actionBean.permohonan.kodUrusan.kod eq 'PSL'
                    or actionBean.permohonan.kodUrusan.kod eq 'SBSTL'
                    or actionBean.permohonan.kodUrusan.kod eq 'SBTL'
                    or actionBean.permohonan.kodUrusan.kod eq 'CL'}">
            <p>
              <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSSKL'}">
                <label>Sebab Kelulusan :</label>
              </c:if>
              <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'TSSKL'}">
                <label>Yang Meluluskan :</label>
              </c:if>
              <s:text name="permohonanRujLuar.noSidang" onkeyup="this.value=this.value.toUpperCase();" size="60"/>
            </p>
            <p>
              <label>Tarikh Kelulusan :</label>
              <s:text name="tarikhSidang" id="tarikhSidang" class="datepicker" size="35"/>
            </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRPMB'}">
        <p>
          <label>No. Perintah Mahkamah:</label>
          <s:text name="permohonanRujLuar.noSidang" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Tarikh Mahkamah :</label>
          <s:text name="tarikhSidang" id="tarikhSidang" class="datepicker" size="35"/>
        </p>
      </c:if>
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RPBNB'}">
        <p>
          <label>No. Warta Kerajaan Negeri :</label>
          <s:text name="permohonanRujLuar.noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Tarikh Warta :</label>
          <s:text name="tarikhRujukan" id="datepicker" class="datepicker" size="35"/>
        </p>
      </c:if>
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PCT'}">
        <p>
          <label>Tarikh Mula :</label>
          <s:text name="tarikhKKuasa" id="tarikhKKuasa" class="datepicker1" size="35" />
        </p>
      </c:if>
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'ECPI'}">
        <p>
          <label>Tarikh Mula :</label>
          <s:text name="tarikhKKuasa" id="tarikhKKuasa" class="datepicker1" size="35" />
          <text style="color:red"> * (Tahun semasa: 2020, Tarikh berkuatkuasa : 01/01/2021) </text>
        </p>
      </c:if>
      <!--start azmi:ADMRL-->
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'ADMRL'}">
        <p>
          <label>Nama Mahkamah :</label>
          <s:text name="permohonanRujLuar.namaSidang" class="text" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>No. Perintah Mahkamah :</label>
          <s:text name="permohonanRujLuar.noSidang" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Tarikh Perintah :</label>
          <s:text name="tarikhSidang" id="tarikhSidang" class="datepicker" size="35"/>
        </p>
        <p>
          <label>No. Rujukan Surat Permohonan Polis :</label>
          <s:text name="permohonanRujLuar.noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
      </c:if>
      <!--Start: 13/09/2013-->
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'N8AB'}">
        <p>
          <label>Nama Mahkamah :</label>
          <s:text name="permohonanRujLuar.namaSidang" class="text" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>No. Mahkamah :</label>
          <s:text name="permohonanRujLuar.noSidang" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Tarikh Perintah :</label>
          <s:text name="tarikhSidang" id="tarikhSidang" class="datepicker" size="35"/>
        </p>
      </c:if>
      <!--End : 13/09/2013-->
      <%--
    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PREM'}">
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
        <s:text name="tarikhKelulusan" id="tarikhKelulusan" class="datepicker" size="35"/>
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
        <s:text name="tarikhKKuasa" id="tarikhKKuasa" class="datepicker" size="35"  />
      </p>
      <p>
        <label>Tarikh Tamat :</label>
        <s:text name="tarikhTamat" id="tarikhTamat" class="datepicker" size="35"/>
      </p>
      <p>
        <label>Kawasan :</label>
        <s:text name="permohonanRujLuar.item" class="text" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
      </p>
    </c:if>--%>
      <!--hide by azmi-->
      <%--<c:if test="${actionBean.permohonan.kodUrusan.kod eq 'ADMRL'}">
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
    </c:if>--%><!--hide by azmi-->
      <%--n6ab--%>
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'N6AB'
                    or actionBean.permohonan.kodUrusan.kod eq 'N8AB'
                    or actionBean.permohonan.kodUrusan.kod eq 'LPBB'
                    or actionBean.permohonan.kodUrusan.kod eq 'RPBNB'}">
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'HTB'
                    || actionBean.permohonan.kodUrusan.kod eq 'HTT'
                    || actionBean.permohonan.kodUrusan.kod eq 'HTBT'}">        
            <p>
              <label>No. Buku Daftar :</label>
              <s:text name="permohonanRujLuar.noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
            </p>
            <p>
              <label>Tarikh Buku :</label>
              <s:text name="tarikhRujukan" id="datepicker" class="datepicker" size="35" readonly="true"/>
            </p>
            <s:hidden name="p.idPihak"/>
            <p>
              <label>Nama (Perbadanan Pengurusan) :</label>              
              <s:textarea name="p.nama" rows="3" cols="32" onkeyup="this.value=this.value.toUpperCase();" />
            </p>
            <p>
                <label>Jenis Pihak Berkepentingan</label>
                <s:select name="mp.jenis.kod" style="width:400px" id="jenis_pihak" >
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJenisPihakBerkepentingan}" label="nama" value="kod"/> 
                </s:select>
            </p>
            <p>
              <label>No. Perbadanan Pengurusan :</label>
              <c:if test="${actionBean.kodNegeri ne '05'}">
                <s:text name="p.noPengenalan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
              </c:if>
              <c:if test="${actionBean.kodNegeri eq '05' && actionBean.permohonan.kodUrusan.kod eq 'HTB'}">
                <s:text name="noPengenalan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
              </c:if>
              <c:if test="${actionBean.kodNegeri eq '05' && actionBean.permohonan.kodUrusan.kod ne 'HTB'}">
                <s:text name="p.noPengenalan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
              </c:if>
            </p>
            <p>
              <label>Alamat :</label>
              <s:text name="p.alamat1" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
            </p>
            <p>
              <label> &nbsp;</label>
              <s:text name="p.alamat2" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
            </p>
            <p>
              <label>&nbsp;</label>
              <s:text name="p.alamat3" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
            </p>
            <p>
              <label>Bandar :</label>
              <s:text name="p.alamat4" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
            </p>
            <p>
              <label>Poskod :</label>
              <s:text name="p.poskod" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
            </p>
            <p>
              <label for="Negeri">Negeri :</label>
              <s:select name="p.negeri.kod" id="negeri">
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
              </s:select>
            </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'HTBKR'}">
        <p>
          <label>No. Buku Daftar Strata :</label>
          <s:text name="permohonanRujLuar.noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>Tarikh Buku :</label>
          <s:text name="tarikhRujukan" id="datepicker" class="datepicker" size="35"/>
        </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LTS'}">
        <!--                add by ameer 13/06/2013  follow LTSK request EN.Suhairi-->
        <p>
          <label>Tempoh Tahun :</label>
          <s:hidden name="kodUrusan" id="kodUrusan" value="${actionBean.permohonan.kodUrusan.kod}"/>
          <s:hidden name="hari1" class="hari1" id = "hari1" value="0"/>
          <s:hidden name="bulan1" class="bulan1" id = "bulan1" value="0"/>
          <s:text name="permohonanRujLuar.tempohTahun" class="tahun1" id = "tahun1" style="width:100px"  
                  onchange="doCalcEndDate('tarikhKKuasa');"/>&nbsp; Tahun &nbsp;
        </p> 
        <p>
          <label>Tarikh Mesyuarat MMK :</label>
          <s:text name="tarikhKKuasa" id="tarikhKKuasa" class="datepicker" size="25" onchange="doCalcEndDate(this.id);"/> 
        </p>
        <p>
          <label>Tarikh Akhir :</label>
          <s:text name="tarikhTamat" id="tkhTamat" class="datepicker1" size="25" formatType="date" formatPattern="dd/MM/yyyy"/>   
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
              <s:text name="permohonanRujLuar.item" onkeyup="this.value=this.value.toUpperCase();" size="35"></s:text>
              </p>
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
          <s:textarea name="nama" onkeyup="this.value=this.value.toUpperCase();"/>
        </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'MAJB'}">
        <p>
          <label>Kawasan Majlis Perbandaran :</label>
          <s:text name="permohonanRujLuar.item" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
      </c:if>

      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BETHM'
                    or actionBean.permohonan.kodUrusan.kod eq 'BETP'
                    or actionBean.permohonan.kodUrusan.kod eq 'BETC'
                    or actionBean.permohonan.kodUrusan.kod eq 'BETLP'
                    or actionBean.permohonan.kodUrusan.kod eq 'BETEN'
                    or actionBean.permohonan.kodUrusan.kod eq 'BETPB'
                    or actionBean.permohonan.kodUrusan.kod eq 'BETST'
                    or actionBean.permohonan.kodUrusan.kod eq 'BETSW'
                    or actionBean.permohonan.kodUrusan.kod eq 'BETUL'
                    or actionBean.permohonan.kodUrusan.kod eq 'BETUR'}">
            <p>
              <label>Sebab Pembetulan:</label>
              <s:textarea name="permohonanRujLuar.catatan" rows="5" cols="40" />
            </p>
      </c:if>
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'ABTB'}">
        <p>
          <label>Sebab Pembatalan:</label>
          <s:textarea name="permohonanRujLuar.catatan" rows="5" cols="40" />
        </p>
      </c:if>
      <%--phkk--%>
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PHKK'
                    or actionBean.permohonan.kodUrusan.kod eq 'PHSK'}">
            <p>
              <label>Sebab :</label>
              <s:select name="permohonanRujLuar.catatan" style="width:250px">
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

    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'ABT-D'
                  or actionBean.permohonan.kodUrusan.kod eq 'ABT'
                  or actionBean.permohonan.kodUrusan.kod eq 'ABT-A'
                  or actionBean.permohonan.kodUrusan.kod eq 'ABT-K'
                  or actionBean.permohonan.kodUrusan.kod eq 'ABTS'
                  or actionBean.permohonan.kodUrusan.kod eq 'ABTKB'
                  or actionBean.permohonan.kodUrusan.kod eq 'ABTBH'
                  or actionBean.permohonan.kodUrusan.kod eq 'EUBS'
                  or actionBean.permohonan.kodUrusan.kod eq 'HLTE'
                  or actionBean.permohonan.kodUrusan.kod eq 'MCLL'
                  or actionBean.permohonan.kodUrusan.kod eq 'PCT'
                  or actionBean.permohonan.kodUrusan.kod eq 'SBSTL'
                  or actionBean.permohonan.kodUrusan.kod eq 'TSSKL'
                  or actionBean.permohonan.kodUrusan.kod eq 'KCL'
                  or actionBean.permohonan.kodUrusan.kod eq 'ADMNB'
                  or actionBean.permohonan.kodUrusan.kod eq 'STMA'
                  or actionBean.permohonan.kodUrusan.kod eq 'IRM' }">
          <!--or actionBean.permohonan.kodUrusan.kod eq 'SSKPL'}"> comment out on 6/9/2013 for FAT integration N9 -->            
          <fieldset class="aras1">
            <legend>
              Kemasukan Berkaitan Dengan Hakmilik
            </legend>
            <%-- <p style="color:red">
                 *Klik Icon (<img alt='Klik Untuk Kemsakini' border='0' src='${pageContext.request.contextPath}/pub/images/semak.gif' class='rem' />) untuk semak.
             </p>--%>
            <div class="content" align="center" >
              <%-- Luas and Cukai--%>
              <c:if test="${ actionBean.permohonan.kodUrusan.kod eq 'ABT'
                             or actionBean.permohonan.kodUrusan.kod eq 'ABT-A'
                             or actionBean.permohonan.kodUrusan.kod eq 'ABTKB'
                             or actionBean.permohonan.kodUrusan.kod eq 'ABTBH'
                             or actionBean.permohonan.kodUrusan.kod eq 'SBSTL'
                             or actionBean.permohonan.kodUrusan.kod eq 'STMA'
                             or actionBean.permohonan.kodUrusan.kod eq 'IRM'}">
                <s:hidden name="sizeHakmilikPermohonan" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="sizeHakmilikPermohonan"/>
                <display:table class="tablecloth" style="width:90%;" requestURI="/common/maklumat_hakmilik_permohonan" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="line">
                  <display:column title="No" sortable="true">${line_rowNum}</display:column>
                  <display:column title="ID Hakmilik"><a href="#" onclick="p('${line.hakmilik.idHakmilik}');
      return false;">${line.hakmilik.idHakmilik}</a>
                    <s:hidden name="hiddenIdHakmilik" id="hiddenIdHakmilik${line_rowNum-1}" value="${line.hakmilik.idHakmilik}"/>
                  </display:column>
                  <display:column title="Luas / Unit Asal">                    
                    ${line.hakmilik.luas}
                    ${line.hakmilik.kodUnitLuas.nama}
                    <br/>                                   
                    <div id="LuasDitukar${line_rowNum-1}">
                      <c:if test="${line.hakmilik.luas ne null}">
                        <font style='color:green'>Luas dalam ${line.kodUnitLuas.nama} :${line.hakmilik.luas}</font>
                        </c:if>
                    </div>
                  </display:column>
                  <display:column title="Luas / Unit Terlibat" >
                    <s:text name="hakmilikPermohonanList[${line_rowNum-1}].luasTerlibat" formatPattern="###0.0000" size="10" id="luasTerlibat${line_rowNum-1}" value="${line.luasTerlibat}" onchange="validateLuas('${line.hakmilik.idHakmilik}','${line_rowNum-1}');"/><%--id="luasTerlibat${line_rowNum-1}" value="${line.luasTerlibat}"  onchange="kiraCukaiKelompok('${line.hakmilik.idHakmilik}','${line_rowNum-1}');"--%>
                    <s:select  style="text-transform:uppercase" id="kodUOM${line_rowNum-1}" name="strKodUOM[${line_rowNum-1}]" onchange="validateLuas('${line.hakmilik.idHakmilik}','${line_rowNum-1}');" ><%--onchange="kiraCukaiKelompok('${line.hakmilik.idHakmilik}','${line_rowNum-1}');">--%>
                      <s:option value=" ${line.kodUnitLuas.kod}"> ${line.kodUnitLuas.nama}</s:option>
                      <s:options-collection collection="${listUtil.senaraiKodUOMByJarak3}" label="nama" value="kod" />
                    </s:select>
                    <br/>
                    <div id="LuasTinggal${line_rowNum-1}">
                      <c:if test="${line.luasTerlibat ne null && line.hakmilik.luas ne null}">
                        <font style='color:green'>Luas selepas ditolak : ${line.hakmilik.luas -line.luasTerlibat}</font> 
                        </c:if>
                    </div>

                    <div id="AlertLebih${line_rowNum-1}">

                    </div>
                  </display:column>
                  <display:column title="Cukai">
                    RM ${line.hakmilik.cukai}
                  </display:column>
                  <display:column title="Cukai Baru">
                    RM <s:text name="hakmilikPermohonanList[${line_rowNum-1}].cukaiBaru" formatPattern="###0" value="${line.cukaiBaru}" id="cukai${line_rowNum-1}" /> <%--${line_rowNum-1}--%>
                    <%--<s:text name="non" id="cukai" onchange="kiraCukaiKelompok('${line.hakmilik.idHakmilik}')" />--%>
                  </display:column>
                  <display:column title="&nbsp;">
                    <div align="center">
                      <img alt="Mesin Kira" onmouseover="this.style.cursor = 'pointer';"  src='${pageContext.request.contextPath}/pub/images/icons/calc.png'  onclick="kiraCukaiKelompok('${line.hakmilik.idHakmilik}', '${line_rowNum-1}');"/>
                    </div>
                  </display:column>
                </display:table>
                <br/>
                <p id="buttonSimpan">
                  <c:if test="${fn:length(actionBean.hakmilikPermohonanList) > 0}">
                    <s:button class="longbtn" value="Simpan Berkelompok" name="simpanBerkelompok" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    &nbsp;
                  </c:if>
                </p>
                <br>

              </c:if>
              <%-- Luas and Cukai abt-d--%>
              <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'ABT-D'}">
                <s:hidden name="sizeHakmilikPermohonan" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="sizeHakmilikPermohonan"/>
                <display:table class="tablecloth" style="width:90%;" requestURI="/common/maklumat_hakmilik_permohonan" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="line">
                  <%--   <display:column title="<input type='checkbox' name='semua' onclick='javascript:selectAll(this);'>">
                         <s:checkbox name="checkbox" id="checkbox${line_rowNum-1}" value="${line.id}" class="remove2"/>
                     </display:column>
                  --%>   <display:column title="No" sortable="true">${line_rowNum}</display:column>
                  <display:column title="ID Hakmilik">${line.hakmilik.idHakmilik}
                    <s:hidden name="hiddenIdHakmilik" id="hiddenIdHakmilik${line_rowNum-1}" value="${line.hakmilik.idHakmilik}"/>
                  </display:column>
                  <display:column title="Luas / Unit Asal">
                    ${line.hakmilik.luas}
                    ${line.hakmilik.kodUnitLuas.nama}
                    <br/>
                    <div id="LuasDitukar${line_rowNum-1}"></div>
                  </display:column>
                  <display:column title="Luas / Unit Terlibat" >
                    <s:text name="hakmilikPermohonanList[${line_rowNum-1}].luasTerlibat" formatPattern="###0.0000" size="10" id="luasTerlibat${line_rowNum-1}" value="${line.luasTerlibat}" onchange="validateLuas('${line.hakmilik.idHakmilik}','${line_rowNum-1}');"/><%--id="luasTerlibat${line_rowNum-1}" value="${line.luasTerlibat}"  onchange="kiraCukaiKelompok('${line.hakmilik.idHakmilik}','${line_rowNum-1}');"--%>
                    <s:select  style="text-transform:uppercase" id="kodUOM${line_rowNum-1}" name="strKodUOM[${line_rowNum-1}]" onchange="validateLuas('${line.hakmilik.idHakmilik}','${line_rowNum-1}');" ><%--onchange="kiraCukaiKelompok('${line.hakmilik.idHakmilik}','${line_rowNum-1}');">--%>
                      <s:option value=" ${line.kodUnitLuas.kod}"> ${line.kodUnitLuas.nama}</s:option>
                      <s:options-collection collection="${listUtil.senaraiKodUOMByJarak}" label="nama" value="kod" />
                    </s:select>
                    <br/>
                    <div id="LuasTinggal${line_rowNum-1}">

                    </div>

                    <div id="AlertLebih${line_rowNum-1}">

                    </div>
                  </display:column>
                  <display:column title="Cukai Asal">
                    RM ${line.hakmilik.cukai}
                  </display:column>
                  <display:column title="Cukai Baru">
                    RM <s:text name="hakmilikPermohonanList[${line_rowNum-1}].cukaiBaru" formatPattern="###0" value="${line.cukaiBaru}" id="cukai${line_rowNum-1}"/>
                  </display:column>   
                  <display:column title="&nbsp;">
                    <div align="center">
                      <img alt="Mesin Kira" onmouseover="this.style.cursor = 'pointer';"  src='${pageContext.request.contextPath}/pub/images/icons/calc.png'  onclick="kiraCukaiKelompok('${line.hakmilik.idHakmilik}', '${line_rowNum-1}');"/>
                    </div>
                  </display:column>
                </display:table>
                <br/>
                <p id="buttonSimpan">
                  <c:if test="${fn:length(actionBean.hakmilikPermohonanList) > 0}">
                    <s:button class="longbtn" value="Simpan Berkelompok" name="simpanBerkelompok" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    &nbsp;
                  </c:if>
                </p>
                <br>
              </c:if>
              <%--papar sahaja--%>
              <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'ABT-K'
                            or actionBean.permohonan.kodUrusan.kod eq 'ABTS'}">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="10" cellpadding="0" cellspacing="0" id="line">
                  <display:column title="No." sortable="true">${line_rowNum}</display:column>
                  <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                  <display:column property="hakmilik.luas" title="Luas Keseluruhan"/>
                  <display:column property="hakmilik.kodUnitLuas.nama" title="Unit Luas Keseluruhan"/>
                  <display:column property="hakmilik.cukai" title="Cukai Asal (RM)"  />
                </display:table>
              </c:if>

              <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'EUBS'}">
                <%--EUBS--%>
                <%-- <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="10" cellpadding="0" cellspacing="0" id="line">
                     <display:column title="No." sortable="true">${line_rowNum}</display:column>
                     <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                     <display:column property="hakmilik.syaratNyata.kod" title="Syarat Lama"></display:column>
                     <display:column property="syaratNyataBaru.kod" title="Syarat Baru"></display:column>
                     <display:column property="hakmilik.sekatanKepentingan.kod" title="Sekatan Lama"></display:column>
                     <display:column property="sekatanKepentinganBaru.kod" title="Sekatan Baru"/>
                     <display:column title="Kemaskini">
                         <div align="center">
                             <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                  id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('this.form','${line.hakmilik.idHakmilik}','${line.id}')"/>
                         </div>
                     </display:column>
                 </display:table>--%>
                <s:hidden name="sizeHakmilikPermohonan" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="sizeHakmilikPermohonan"/>
                <display:table class="tablecloth" style="width:90%;" requestURI="/common/maklumat_hakmilik_permohonan" 
                               name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="line">
                  <display:column title="No" sortable="true">${line_rowNum}</display:column>
                  <display:column title="ID Hakmilik"><a href="#" onclick="p('${line.hakmilik.idHakmilik}');
      return false;">${line.hakmilik.idHakmilik}</a>
                    <s:hidden name="hiddenIdHakmilik" id="hiddenIdHakmilik${line_rowNum-1}" value="${line.hakmilik.idHakmilik}"/>
                  </display:column>
                  <display:column title="Kod Syarat Nyata" class="tooltips">&nbsp;<img  alt='Klik Untuk Bantuan' border='0' src='${pageContext.request.contextPath}/pub/images/semak.gif'
                              id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';" onclick="popupCarian('cariKodSyaratNyata&kodSyaratNyata=', '${line_rowNum-1}', 'syarat')"/>
                    <s:text name="strKodSyaratNyata[${line_rowNum-1}]" id="strKodSyaratNyata[${line_rowNum-1}]"  maxlength="7"/> &nbsp;&nbsp;&nbsp; Kod Disimpan : ${line.syaratNyataBaru.kodSyarat}
                    <c:if test="${!empty line.syaratNyataBaru.kodSyarat}">
                      <img  alt='Klik Untuk Bantuan' border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' onmouseover="this.style.cursor = 'pointer';" onclick="deleteKodSyaratNyataBaru('${line.id}');"/>
                    </c:if>
                  </display:column>
                  <display:column title="Kod Sekatan">&nbsp;<img  alt='Klik Untuk Bantuan' border='0' src='${pageContext.request.contextPath}/pub/images/semak.gif'
                              id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';" onclick="popupCarian('cariKodSekatan&kodSekatan=', '${line_rowNum-1}', 'sekatan')"/>
                    <s:text name="strKodSekatan[${line_rowNum-1}]" id="strKodSekatan[${line_rowNum-1}]" maxlength="7" />&nbsp;&nbsp;&nbsp; Kod Disimpan : ${line.sekatanKepentinganBaru.kodSekatan}
                    <c:if test="${!empty line.sekatanKepentinganBaru.kodSekatan}">
                      <img  alt='Klik Untuk Bantuan' border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' onmouseover="this.style.cursor = 'pointer';" onclick="deleteSekatanBaru('${line.id}');"/>
                    </c:if>
                  </display:column>
                </display:table>
                <br/>
                <p>
                  <c:if test="${fn:length(actionBean.hakmilikPermohonanList) > 0}">
                    <s:button class="longbtn" value="Simpan Berkelompok" name="simpanBerkelompok" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    &nbsp;
                  </c:if>
                </p>
                <br>
              </c:if>
              <%--akan tukar PBBM to SHSK--%>
              <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'ADMNB'}">
                <%--DADAH(ADMRL)--%>
                <display:table class="tablecloth" name="${actionBean.pbHakmilikList}" pagesize="10" cellpadding="0" cellspacing="0" id="line">
                  <display:column title="No." sortable="true">${line_rowNum}</display:column>
                  <display:column property="pihak.nama" title="Nama"/>
                  <display:column property="pihak.noPengenalan" title="No Pengenalan"></display:column>
                  <display:column title="Syer">
                    ${line.syerPembilang} / ${line.syerPenyebut}
                  </display:column>
                  <display:column title="Salinan Dipegang">
                    <div align="center">
                      ${line.hakmilik.noVersiDhke}
                    </div>
                  </display:column>
                  <display:column title="Pilih">
                    <c:if test="${actionBean.mpSalinan.pihak.idPihak ne line.pihak.idPihak}">
                      <div align="center">
                        <s:checkbox name="pilihPB" onclick="pickPut('${line.pihak.nama}','${line.pihak.noPengenalan}','${line.hakmilik.noVersiDhke + 1}', '${actionBean.permohonan.kodUrusan.nama}','${line.pihak.idPihak}','${line.hakmilik.idHakmilik}',' ${line.syerPembilang}','${line.syerPenyebut}')"/>
                      </div>
                    </c:if>
                  </display:column>
                </display:table>
              </c:if>

              <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'HLTE'
                            or actionBean.permohonan.kodUrusan.kod eq 'ADMRL'}">
                <%--HLTE Luas--%>
                <%-- <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="10" cellpadding="0" cellspacing="0" id="line">
                     <display:column title="No." sortable="true">${line_rowNum}</display:column>
                     <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                     <display:column property="luasTerlibat" title="Luas Terlibat"/>
                     <display:column property="kodUnitLuas.kod" title="Unit Luas" />
                     <display:column title="Kemaskini">
                         <div align="center">
                             <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                  id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('this.form','${line.hakmilik.idHakmilik}','${line.id}')"/>
                         </div>
                     </display:column>
                 </display:table>--%>
                <s:hidden name="sizeHakmilikPermohonan" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="sizeHakmilikPermohonan"/>
                <display:table class="tablecloth" style="width:90%;" requestURI="/common/maklumat_hakmilik_permohonan" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="line">
                  <%--   <display:column title="<input type='checkbox' name='semua' onclick='javascript:selectAll(this);'>">
                         <s:checkbox name="checkbox" id="checkbox${line_rowNum-1}" value="${line.id}" class="remove2"/>
                     </display:column>
                  --%>   <display:column title="No" sortable="true">${line_rowNum}</display:column>
                  <display:column title="ID Hakmilik">${line.hakmilik.idHakmilik}
                    <s:hidden name="hiddenIdHakmilik" id="hiddenIdHakmilik${line_rowNum-1}" value="${line.hakmilik.idHakmilik}"/>
                  </display:column>
                  <display:column title="Luas / Unit Asal">
                    ${line.hakmilik.luas}
                    ${line.hakmilik.kodUnitLuas.nama}
                    <br/>
                    <div id="LuasDitukar"></div>
                  </display:column>
                  <display:column title="Luas / Unit Terlibat" ><%--strKodUOM[${line_rowNum-1}]"--%>
                    <s:text name="hakmilikPermohonanList[${line_rowNum-1}].luasTerlibat" formatPattern="###0.0000" size="10"/><%--id="luasTerlibat${line_rowNum-1}" value="${line.luasTerlibat}"  onchange="kiraCukaiKelompok('${line.hakmilik.idHakmilik}','${line_rowNum-1}');"--%>
                    <s:select  style="text-transform:uppercase"  name="strKodUOM[${line_rowNum-1}]"> <%--onchange="kiraCukaiKelompok('${line.hakmilik.idHakmilik}','${line_rowNum-1}');"--%>
                      <s:option value=" ${line.kodUnitLuas.kod}"> ${line.kodUnitLuas.nama}</s:option>
                      <s:options-collection collection="${listUtil.senaraiKodUOMByJarak3}" label="nama" value="kod"/>
                    </s:select>
                    <br/>
                    <div id="LuasTinggal"></div>
                    <div id="AlertLebih"></div>
                  </display:column>
                </display:table>
                <br/>
                <p>
                  <c:if test="${fn:length(actionBean.hakmilikPermohonanList) > 0}">
                    <s:button class="longbtn" value="Simpan Berkelompok" name="simpanBerkelompok" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    &nbsp;
                  </c:if>
                </p>
                <br>
              </c:if>

              <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PCT'
                            or actionBean.permohonan.kodUrusan.kod eq 'KCL'}">
                <%--PCT/KCL Cukai Sahaj--%>
                <%--                       <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="10" cellpadding="0" cellspacing="0" id="line">
                                           <display:column title="No." sortable="true">${line_rowNum}</display:column>
                                           <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                                           <display:column property="hakmilik.cukai" title="Cukai Lama (RM)"></display:column>
                                           <display:column property="cukaiBaru" title="Cukai Dipinda (RM)"/>
                                           <c:if test="${actionBean.permohonan.kodUrusan.jabatan eq '17'}">
                                           <display:column title="Kemaskini">
                                               <div align="center">
                                                   <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                                        id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('this.form','${line.hakmilik.idHakmilik}','${line.id}')"/>
                                               </div>
                                           </display:column>
                                           </c:if>
                                       </display:table>--%>
                <s:hidden name="sizeHakmilikPermohonan" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="sizeHakmilikPermohonan"/>
                <display:table class="tablecloth" style="width:90%;" requestURI="/common/maklumat_hakmilik_permohonan" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="line">
                  <%--   <display:column title="<input type='checkbox' name='semua' onclick='javascript:selectAll(this);'>">
                         <s:checkbox name="checkbox" id="checkbox${line_rowNum-1}" value="${line.id}" class="remove2"/>
                     </display:column>
                  --%>   <display:column title="No" sortable="true">${line_rowNum}</display:column>
                  <display:column title="ID Hakmilik"><a href="#" onclick="p('${line.hakmilik.idHakmilik}');
      return false;">${line.hakmilik.idHakmilik}</a>
                    <s:hidden name="hiddenIdHakmilik" id="hiddenIdHakmilik${line_rowNum-1}" value="${line.hakmilik.idHakmilik}"/>
                  </display:column>
                  <display:column title="Cukai" style="width:20%">
                    RM ${line.hakmilik.cukai}
                  </display:column>
                  <display:column title="Cukai Baru">
                    RM <s:text name="hakmilikPermohonanList[${line_rowNum-1}].cukaiBaru" formatPattern="###0" value="${line.cukaiBaru}" id="cukai${line_rowNum-1}"/>
                  </display:column>
                    <display:column title="&nbsp;">
                    <div align="center">
                      <img alt="Mesin Kira" onmouseover="this.style.cursor = 'pointer';"  src='${pageContext.request.contextPath}/pub/images/icons/calc.png'  onclick="kiraCukaiKelompok('${line.hakmilik.idHakmilik}', '${line_rowNum-1}');"/>
                    </div>
                  </display:column>
                  <%--   <display:column title="Kira Cukai">

                           </display:column>--%>
                </display:table>
                <br/>
                <p>
                  <c:if test="${fn:length(actionBean.hakmilikPermohonanList) > 0}">
                    <s:button class="longbtn" value="Simpan Berkelompok" name="simpanBerkelompok" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    &nbsp;
                  </c:if>
                </p>
                <br>
              </c:if>

              <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'MCLL'}">
                <%--MCLL Cukai Sahaj--%>
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="10" cellpadding="0" cellspacing="0" id="line">
                  <display:column title="No." sortable="true">${line_rowNum}</display:column>
                  <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                  <display:column property="cukaiBaru" title="Cukai Dipinda (RM)"/>
                  <%--<c:if test="${actionBean.permohonan.kodUrusan.jabatan eq '17'}">--%>
                  <%-- <display:column title="Kemaskini">
                       <div align="center">
                           <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('this.form','${line.hakmilik.idHakmilik}','${line.id}')"/>
                       </div>
                   </display:column>--%>
                  <%--</c:if>--%>
                </display:table>
              </c:if>
              <%--TSSKL and SSKPL--%>
              <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSSKL'
                            or actionBean.permohonan.kodUrusan.kod eq 'SSKPL'}">
                    <p align="left">
                      Sila klik pada ikon <img  border='0' src='${pageContext.request.contextPath}/pub/images/semak.gif'/> untuk mengemaskini kod syarat nyata / kod sekatan.
                    </p>
                    <br>
                    <s:hidden name="sizeHakmilikPermohonan" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="sizeHakmilikPermohonan"/>
                    <display:table class="tablecloth" style="width:95%;" 
                                   requestURI="/common/maklumat_hakmilik_permohonan" 
                                   name="${actionBean.hakmilikPermohonanList}" 
                                   cellpadding="0" cellspacing="0" id="line">
                      <display:column title="No" sortable="true">
                        ${line_rowNum}
                      </display:column>
                      <display:column title="ID Hakmilik">
                        <a href="#" onclick="p('${line.hakmilik.idHakmilik}');
      return false;">${line.hakmilik.idHakmilik}</a>
                        <s:hidden name="hiddenIdHakmilik" id="hiddenIdHakmilik${line_rowNum-1}" value="${line.hakmilik.idHakmilik}"/>
                      </display:column>
                      <display:column title="Kategori Tanah Baru">                        
                        <s:select  style="text-transform:uppercase"  name="strKodKategori[${line_rowNum-1}]">
                          <s:option value=" ${line.kategoriTanahBaru.kod}"> ${line.kategoriTanahBaru.nama}</s:option>
                          <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod"/>
                        </s:select>
                      </display:column>
                      <display:column title="Kegunaan Tanah Baru (Untuk Rujukan Hasil)">                        
                        <s:select  style="text-transform:uppercase"  name="strKodGunaTanah[${line_rowNum-1}]">
                          <s:option value=" ${line.kodKegunaanTanah.kod}"> ${line.kodKegunaanTanah.nama}</s:option>
                          <s:options-collection collection="${listUtil.senaraiKegunaanTanah}" label="nama" value="kod"/>
                        </s:select>
                      </display:column>
                      <display:column title="Kod Syarat Nyata Baru" class="tooltips">&nbsp;
                        <img  alt='Klik Untuk Bantuan' border='0' src='${pageContext.request.contextPath}/pub/images/semak.gif' title="Klik Untuk Kemaskini"
                              id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';" onclick="popupCarian('cariKodSyaratNyata&kodSyaratNyata=', '${line_rowNum-1}', 'syarat')"/>
                        <s:text name="strKodSyaratNyata[${line_rowNum-1}]" id="strKodSyaratNyata[${line_rowNum-1}]"  maxlength="9" class="ksn" /> <br/>
                        Kod Disimpan : ${line.syaratNyataBaru.kodSyarat}
                        <c:if test="${line.syaratNyataBaru.kodSyarat eq null}">
                          Tiada
                        </c:if>
                      </display:column>
                      <display:column title="Kod Sekatan Baru">&nbsp;
                        <img  alt='Klik Untuk Bantuan' border='0' src='${pageContext.request.contextPath}/pub/images/semak.gif' title="Klik Untuk Kemaskini"
                              id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';" onclick="popupCarian('cariKodSekatan&kodSekatan=', '${line_rowNum-1}', 'sekatan')"/>
                        <s:text name="strKodSekatan[${line_rowNum-1}]" id="strKodSekatan[${line_rowNum-1}]" maxlength="9" /><br/> 
                        Kod Disimpan : ${line.sekatanKepentinganBaru.kodSekatan}
                        <c:if test="${line.sekatanKepentinganBaru.kodSekatan eq null}">
                          Tiada
                        </c:if>
                      </display:column>
                      <display:column title="Cukai Baru">
                        RM <s:text name="hakmilikPermohonanList[${line_rowNum-1}].cukaiBaru" formatPattern="###0" value="${line.cukaiBaru}" id="cukai${line_rowNum-1}"/>
                      </display:column>                      
                    </display:table>
                    <br/>
                    <p>
                      <c:if test="${fn:length(actionBean.hakmilikPermohonanList) > 0}">
                        <s:button class="longbtn" value="Simpan Berkelompok" name="simpanBerkelompok" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        &nbsp;
                      </c:if>
                    </p>
                    <br>
              </c:if>
            </div>
          </fieldset>
    </c:if>
    <br/>
    <c:if test="${ actionBean.permohonan.kodUrusan.kod eq 'SHSK'
                   or actionBean.permohonan.kodUrusan.kod eq 'SHKK'}" >
          <fieldset class="aras1">
            <legend>
              Senarai Pemilik
            </legend>
            <p style="color:red">
              *Klick ikon (<img alt="Telah Dipilih" onmouseover="this.style.cursor = 'pointer';"  src='${pageContext.request.contextPath}/pub/images/icons/KnobValidGreen.png'/>) untuk pilih.
            </p>
            <div class="content" align="center" >
              <display:table class="tablecloth" name="${actionBean.pbHakmilikTempList}" pagesize="0" cellpadding="0" cellspacing="0" id="line" requestURI="/daftar/nota/nota_daftar">
                <display:column title="No." sortable="true">${line_rowNum}</display:column>
                <display:column property="hakmilik.idHakmilik" title="Id Hakmilik"/>
                <display:column property="pihak.nama" title="Nama"/>
                <display:column property="pihak.noPengenalan" title="No Pengenalan"></display:column>
                <display:column title="Syer">
                  ${line.syerPembilang} / ${line.syerPenyebut}
                </display:column>
                <display:column title="Salinan Yang Akan Dikeluarkan">
                  <div align="center">
                    Salinan Keluaran : ${line.noSalinan + 1}
                  </div>
                </display:column>
                <display:column title="Pilih">
                  <c:if test="${actionBean.mpSalinan.pihak.idPihak ne line.pihak.idPihak}">
                    <%--<div align="center">--%>
                    <%--<s:checkbox name="pilihPB" onclick="pickPut('${line.pihak.nama}','${line.pihak.noPengenalan}','${line.hakmilik.noVersiDhke + 1}', '${actionBean.permohonan.kodUrusan.nama}','${line.pihak.idPihak}','${line.hakmilik.idHakmilik}',' ${line.syerPembilang}','${line.syerPenyebut}')"/>--%>
                    <div align="center">
                      <img alt="Telah Dipilih" onmouseover="this.style.cursor = 'pointer';"  src='${pageContext.request.contextPath}/pub/images/icons/KnobValidGreen.png' onclick="pickPut2('${line.pihak.nama}', '${line.pihak.noPengenalan}', '${line.noSalinan + 1}', '${actionBean.permohonan.kodUrusan.nama}', '${line.pihak.idPihak}', '${line.hakmilik.idHakmilik}', ' ${line.syerPembilang}', '${line.syerPenyebut}')"/>
                    </div>
                    <%--</div>--%>
                  </c:if>
                </display:column>
              </display:table>
            </div>
          </fieldset>

          <fieldset class="aras1">
            <legend>
              Senarai Pemilik Yang Dipilih
            </legend>
            <p style="color:red">
              *Klick ikon (<img alt="Telah Dipilih" onmouseover="this.style.cursor = 'pointer';"  src='${pageContext.request.contextPath}/pub/images/icons/KnobCancel.png'/>) untuk mansuh.
            </p>
            <div class="content" align="center" >
              <display:table class="tablecloth" name="${actionBean.mohonPihakList}" pagesize="0" cellpadding="0" cellspacing="0" id="line">
                <display:column title="No." sortable="true">${line_rowNum}</display:column>
                <display:column property="hakmilik.idHakmilik" title="Id Hakmilik"/>
                <display:column property="pihak.nama" title="Nama"/>
                <display:column property="pihak.noPengenalan" title="No Pengenalan"></display:column>
                <display:column title="Syer">
                  ${line.syerPembilang} / ${line.syerPenyebut}
                </display:column>
                <display:column title="Mansuh">
                  <div align="center">
                    <img alt="Telah Dipilih" onmouseover="this.style.cursor = 'pointer';"  src='${pageContext.request.contextPath}/pub/images/icons/KnobCancel.png' onclick="deletePemohon('${line.idPermohonanPihak}')"/>
                  </div>
                </display:column>
              </display:table>
            </div>
          </fieldset>
    </c:if>
    <br/>
  </div>
</s:form>
