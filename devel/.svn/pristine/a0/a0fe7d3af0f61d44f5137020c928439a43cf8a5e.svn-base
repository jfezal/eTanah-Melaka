<%-- 
    Document   : kutipan_data_maklumat_hakmilik
    Created on : Sep 24, 2013, 5:11:48 PM
    Author     : ei
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<!--<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>-->
<!--<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/date.format.js"></script>-->
<c:set var="disabled" value="disabled"/>
<script type="text/javascript">
  $(document).ready(function() {

//    $(".datepicker1").datepicker({changeYear: true});
//    $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

    filterKodBPM($('#kodDaerah').val());

//    $("#syaratnyatadetails").blur(function() {
//      var valueJenisHakmilik = $("#syaratNyata").val();
//      $("#syaratnyatadetails").val(valueJenisHakmilik);
//    });
  });

  function filterKodBPM(f) {
    var kodDaerah = f;
    $.post('${pageContext.request.contextPath}/daftar/utiliti/kutipanData?cariBPM&kodDaerah=' + kodDaerah + '&popup=true',
            function(data) {
              if (data != '') {
                $('#partialKodBPM').html(data);
              }
            }, 'html');
  }

  function popupFormSyaratNyata() {
    // GET SYARAT NYATA POPUP
    var url = "${pageContext.request.contextPath}/daftar/utiliti/kutipanData?paparPopupSyaratNyata&idHakmilik=" + $("#idHakmilik").val();
    window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
  }

  function popupFormKodSekatan() {
    // GET SEKATAN POPUP
    var url = "${pageContext.request.contextPath}/daftar/utiliti/kutipanData?paparPopupSekatan&idHakmilik=" + $("#idHakmilik").val();
    window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
  }

  function popupPihak(id) {
    // GET PIHAK POP
    var url = "${pageContext.request.contextPath}/daftar/utiliti/kutipanData?paparPopupPihak&idHakmilik=" + id;
    window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=800");
  }

  function popupKemaskiniPihak(val, val1, val2) {
    // GET POPUP KEMASKINI PIHAK
    //    doBlockUI();
    var url = "${pageContext.request.contextPath}/daftar/utiliti/kutipanData?kemaskiniPihak&phk=" + val + "&hm=" + val1 + "&hp=" + val2;
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

      var url = '${pageContext.request.contextPath}/daftar/utiliti/kutipanData?hapusPihak' + param;
      frm = document.maklumatDetail;
      frm.action = url;
      frm.submit();
    }
  }

  function samaRata(f) {
    // MAKE ALL SHARE EQUAL FOR EACH PIHAK
    var q = $(f).formSerialize();
    doBlockUI();
    var url = '${pageContext.request.contextPath}/daftar/utiliti/kutipanData?agihSamaRata';
    frm = document.maklumatDetail;
    frm.action = url;
    frm.submit();
  }

  function semakSyer(f, id) {
    var q = $(f).formSerialize();
    doBlockUI();
    // re-used code from kemasukanPerincianHakmilikActionBean
    $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?semakSyer&idHakmilik=' + id, q,
            function(data) {
              if (data != '') {
                alert(data);
                $.unblockUI();
              }
            }, 'html');
  }

  function zeroPad(num, count) {
    var numZeropad = num + '';
    while (numZeropad.length < count) {
      numZeropad = "0" + numZeropad;
    }
    return numZeropad;
  }

  function checkPelan(f) {

    var noLot = zeroPad(f, 7);
    var kodDaerah = $('#kodDaerah').val();
    var kodBPM = $('#kodBPM').val();
    var kodNegeri = $('#kodNegeri').val();
    var namaLot = $('#jenisLot :selected').text();

    var kodSeksyen = '${actionBean.hakmilik.seksyen.seksyen}';
    if (kodSeksyen === "") {
      kodSeksyen = "000";
    }
    var kodPelan = '${actionBean.kodPelan}';
//      alert("noLot " + noLot);
//      alert("kodDaerah " + kodDaerah);
//      alert("kodNegeri " + kodNegeri);
//      alert("kodBPM " + kodBPM);
    doBlockUI();

    $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?checkPelan&noLot='
            + noLot + '&kodDaerah=' + kodDaerah + '&kodNegeri=' + kodNegeri + '&kodBPM=' + kodBPM,
            function(data) {
//                alert("data pelan : " + data);
              if (data != '1') {
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
  <s:errors/>
  <s:messages/>
  <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
  <s:form beanclass="etanah.view.daftar.utiliti.KutipanDataActionBean" > 
    <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
    <div class="subtitle">
      <fieldset class="aras1">
        <s:hidden name="kumpHm" id="kumpHm" value="${actionBean.kumpHm}"/> 
        <legend>Perincian Hakmilik</legend>
        <br>
        <p>
          <label>ID Hakmilik :</label>
          ${actionBean.hakmilik.idHakmilik}
          <s:hidden name="idHakmilik" value="${actionBean.hakmilik.idHakmilik}" id="idHakmilik"/>
          <s:hidden name="kodNegeri" id="kodNegeri"/>
          &nbsp
        </p>
        <p>
          <label>Daerah :</label>
          <s:hidden name="hakmilik.daerah.kod" id="kodDaerah" value="${actionBean.hakmilik.daerah.kod}"/>
          <s:select disabled="${disabled}" name="hakmilik.daerah.kod" id="namaDaerah" onchange="filterKodBPM(this.value);" style="width:200pt">            
            <s:options-collection collection="${actionBean.senaraiKodDaerah}" label="nama" value="kod"/>
          </s:select>
        </p>

        <p>
          <label>Bandar / Pekan / Mukim :</label>
          <s:hidden name="hakmilik.bandarPekanMukim.bandarPekanMukim" id="kodBPM"/>
          <s:select  disabled="${disabled}" name="hakmilik.bandarPekanMukim.bandarPekanMukim" id="namaBPM" style="width:200pt">            
            <s:options-collection collection="${actionBean.listBandarPekanMukim}" label="nama" value="bandarPekanMukim"/>
          </s:select>          
        </p>        
        <c:if test="${fn:length(actionBean.listKodSeksyen) > 0 }">
          <p>
            <label>Seksyen :</label>  
            <s:hidden name="hakmilik.seksyen.kod" id="kodSeksyen"/>
            <s:select name="hakmilik.seksyen.kod" style="text-transform:uppercase;width:200pt" 
                      id="selectKodSeksyen" disabled="${disabledbtn}" >
              <s:option value="000">Sila Pilih</s:option>
              <s:options-collection collection="${actionBean.listKodSeksyen}" label="nama" value="kod"/>
            </s:select>
          </p>
        </c:if>
        <br>
        <p>
          <label>No Fail Hakmilik :</label>
          <s:text name="hakmilik.noFail" value="${actionBean.hakmilik.noFail}" class="uppercase" size="40" onblur="this.value=this.value.toUpperCase();"/>
        </p>
        <p>
          <label>Lokasi :</label>
          <c:if test="${disabledbtn eq 'disabled'}">
            <s:hidden name="hakmilik.lokasi" />
          </c:if>
          <s:text name="hakmilik.lokasi" class="uppercase" disabled="${disabledbtn}" size="40" onblur="this.value=this.value.toUpperCase();"/>
        </p>
        <p>
          <label>Kategori Tanah : </label>
          <s:select style="text-transform:uppercase;width:200pt" 
                    name="hakmilik.kategoriTanah.kod" id="katTanah" 
                    onchange="filterKodGunaTanah();filterKodUOM();kiraCukai(this.form,'');" >
            <s:option value="">Sila Pilih</s:option>
            <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" />
          </s:select>
        </p>
        <s:hidden name="hakmilik.kegunaanTanah.kod" id="kodGunaTanah"/>       
        <div id="partialKodGunaTanah"> 
        </div>

        <p>
          <label>Keluasan / Unit :</label>
          <s:text name="hakmilik.luas" id="luas" formatPattern="###0.0000" onblur="kiraCukai(this.form,'');"/>
          /
          <s:hidden name="hakmilik.kodUnitLuas.kod" id="kodUnitLuas"/>
          <s:select  style="text-transform:uppercase" id="kodUOM" name="kodUnitLuas" 
                     onchange="kiraCukai(this.form,'${actionBean.hakmilik.idHakmilik}');">
            <s:options-collection collection="${listUtil.senaraiKodUOMByJarak3}" label="nama" value="kod"/>
          </s:select>
        </p>
        <p>
          <label>Kod Lot / No Lot : </label>
          <s:select  style="text-transform:uppercase;width:105pt" name="hakmilik.lot.kod" id="jenisLot" value="${actionBean.hakmilik.lot.kod}">
            <s:option value="">Sila Pilih</s:option>
            <s:options-collection collection="${actionBean.senaraiKodLot}" label="nama" value="kod" />
          </s:select> /
          <s:text name="hakmilik.noLot" id="noLot" size="16" maxlength="7" 
                  value="${actionBean.hakmilik.noLot}"
                  onblur="checkNoLot(this.value);checkPelan(this.value);"/>
        </p>
        <center>
          <div id="checkLotMessages" style="font-size: 12px;display: none;margin-top: 10px;margin-bottom: 10px;width:50%;"></div>
          <div id="checkPelanMessages" style="font-size: 12px;display: none;margin-top: 10px;margin-bottom: 10px;width:50%;"></div>
        </center>
        <p>
          <label>No Pelan Diperakui :</label>
          <s:text name="hakmilik.noPelan" size="40" onblur="this.value=this.value.toUpperCase();"/>
        </p>
        <p>
          <label>Nombor Lembaran Piawai :</label>
          <s:text name="hakmilik.noLitho" onblur="this.value=this.value.toUpperCase();" size="40"/>
        </p>
        <br>
        <%--<p>
          <label>Keluasan Hakmilik Asal :</label>
          &nbsp;
        </p>--%>
        <p>
          <label>Keluasan Yang Diambil :</label>
          <s:text name="luasTerlibat" id="luasTerlibat" size="11"/>
          &nbsp;
          <s:hidden name="kodUnitLuas"/>
        </p>
        <p>
          <label>Keluasan Baru / Unit :</label>
          <s:text name="hakmilik.luas" id="hakmilik.luas" 
                  formatPattern="###0.0000" onblur="kiraCukai(this.form,'');"/>
          /
          <s:hidden name="hakmilik.kodUnitLuas.kod" id="kodUnitLuas"/>
          <s:select  style="text-transform:uppercase" id="kodUOM" name="kodUnitLuas" 
                     onchange="kiraCukai(this.form,'${actionBean.hakmilik.idHakmilik}');">
            <s:options-collection collection="${listUtil.senaraiKodUOMByJarak3}" label="nama" value="kod"/>
          </s:select>
        </p>
        <%--<p>
          <label>Cukai Lama :</label>
          RM 
        </p>--%>
        <p>
          <label>Cukai :</label>
          RM <s:text name="hakmilik.cukai"  id="cukai" formatPattern="#,###.00" size="16"/>
        </p>

        <p>
          <label>Syarat Nyata :</label>
          <s:text name="kodSyarat" id="syaratNyata" readonly="true" size="40"
                  onblur="updateSyaratNyata()"/> &nbsp;&nbsp;
          <s:button name="cariKodSyaratNyata" value="Cari" id="cariKodSyaratNyata" class="btn" 
                    onclick="popupFormSyaratNyata();" disabled="${disabledbtn}" />
        </p>
        <p>
          <label>&nbsp;</label>
          <s:textarea name="syaratnyata" rows="10" style="width:40%;" readonly="true" id="syaratnyatadetails">${actionBean.hakmilik.syaratNyata.syarat}</s:textarea>
          </p>
          <p>
            <label>Sekatan Kepentingan :</label>
          <s:text name="kodSekatan" id="sekatan" readonly="true" size="40"/> &nbsp;&nbsp;
          <s:button name="cariKodSekatan" value="Cari" id="cariKodSekatan" class="btn" 
                    onclick="popupFormKodSekatan();" disabled="${disabledbtn}"/>
        </p>
        <p>
          <label>&nbsp;</label>
          <s:textarea name="sekatan" rows="10" style="width:40%;" readonly="true" id="sekatandetails" >${actionBean.hakmilik.sekatanKepentingan.sekatan}</s:textarea>
          </p>               
          <br>       

          <p align="center">
          <s:submit name="simpanPerinci" id="simpan" value="Simpan" class="btn"/>&nbsp;
          <s:hidden name="kelompok" value="${actionBean.kelompok}"/>
          <c:if test="${actionBean.kelompok eq 'true'}">
            <s:button name="simpanPerinciKelompok" id="simpanKelompok" value="Simpan Berkelompok" class="longbtn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
          </c:if>
        </p>
        <br> 
      </fieldset>
    </div>
    <br>
    <div class="subtitle">
      <fieldset class="aras1">
        <legend>Senarai Pemilik</legend>
        <br>
        <div class="content" align="center" id="listpihak">
          <display:table class="tablecloth" style="width:90%;" 
                         cellpadding="0" cellspacing="0" id="lineHPB"
                         name="${actionBean.listHakmilikPihak}">
            <display:column title="<input type='checkbox' name='semua' onclick='javascript:selectAllPihak(this);'>">
              <s:checkbox name="checkboxpihak" id="checkboxpihak${lineHPB_rowNum-1}" 
                          value="${lineHPB.idHakmilikPihakBerkepentingan}" class="remove2"/>
            </display:column>
            <display:column title="Bil" sortable="true">${lineHPB_rowNum}</display:column>
            <display:column property="nama" title="Nama" style="text-transform:uppercase;"/>
            <display:column property="noPengenalan" title="Nombor Pengenalan" />
            <display:column property="jenis.nama" title="Jenis Pihak" />
            <display:column title="Bahagian yang diterima">
              <%--<c:if test="${actionBean.p.kodUrusan.kod eq 'HKABS'}">
                <c:set var="disabledbtn" value="disabled"/>
                <s:text name="syerPembilang[${linemohonpihak_rowNum-1}]" size="5" class="number" disabled="disabled"/>
                /
                <s:text name="syerPenyebut[${linemohonpihak_rowNum-1}]" size="5" class="number" disabled="disabled"/>
              </c:if>--%>
              <div align="center">
                <s:text name="listHakmilikPihak[${lineHPB_rowNum-1}].syerPembilang" size="5" id="syer1${lineHPB_rowNum-1}"
                        onblur="updateSyer('${lineHPB.idHakmilikPihakBerkepentingan}', '${lineHPB_rowNum-1}')"
                        onchange="updateSyer('${lineHPB.idHakmilikPihakBerkepentingan}', '${lineHPB_rowNum-1}" class="pembilang"/> /
                <s:text name="listHakmilikPihak[${lineHPB_rowNum-1}].syerPenyebut" size="5" id="syer2${lineHPB_rowNum-1}"
                        onblur="updateSyer('${lineHPB.idHakmilikPihakBerkepentingan}', '${lineHPB_rowNum-1}')"
                        onchange="updateSyer('${lineHPB.idHakmilikPihakBerkepentingan}', '${lineHPB_rowNum-1}" class="penyebut"/>
              </div>
            </display:column>
            <display:column title="Kemaskini">
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
          <s:button disabled="${disabledbtn}" class="longbtn" value="Agih Sama Rata" name="agihSamaRata" id="agihSamaRata" 
                    onclick="samaRata(this.form);"/>&nbsp;          
          <s:button class="btn" value="Hapus" name="" onclick="removePihak();" disabled="${disabledbtn}"/>&nbsp;      
        </div>  
        <br>
      </fieldset>
    </div>
  </s:form>        
</div>
