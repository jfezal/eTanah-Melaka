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
  <c:if test="${actionBean.flag}">
    doBlockUI();
    viewUrusan($('#idhu').val(), $('#jenisUrusan').val());
  </c:if> 
  <c:if test="${actionBean.refresh}">
//    alert("refresh");
    self.close();
    opener.refreshkutipanUrusan();
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

    var val = $('#pjanjianAmaun').val();
    if (val != '') {
//      convert(val, 'pjanjianAmaun');
      val = $('#pjanjianAmaun').val();
      doCalculateDutiStem('pjanjianDutiSetam', val);
    }

//      var val = $('#pjanjianAmaun').val();
//      if (val != '') {
////      convert(val, 'pjanjianAmaun');
//        val = $('#pjanjianAmaun').val();
//        doCalculateDutiStem('pjanjianDutiSetam', val);
//      }
  });

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

  function selectAllUrusan(a) {
    // TICK ALL URUSAN
    var size = '${fn:length(actionBean.listSenaraiHakmilik)}';
    for (i = 0; i < size; i++) {
      var c = document.getElementById("checkboxurusan" + i);
      if (c === null)
        break;
      c.checked = a.checked;
    }
  }

  function simpanUrusan() {
    // SAVE URUSAN SINGLE HAKMILIK  
    var param = '';
    var ku = $("#kodUrusan").val();
    var trhdaftar = $("#tarikhDaftar").val();
    var j = $('#jam').val();
    var m = $('#minit').val();
    var r = $('#minit').val();
    if (ku === '') {
      alert('Sila Pilih Urusan.');
      return;
    }
    if (trhdaftar === '') {
      alert('Sila Masukkan Tarikh Daftar.');
      return;
    }
    //if (j === "" || m === "") {
      //alert('Sila masukkan jam dan minit.');
      //return;
    //}
    doBlockUI();
    param = param + '&idHM=' + $('#idHakmilik2').val();
    var url = '${pageContext.request.contextPath}/daftar/utiliti/kemaskiniData?simpanUrusan' + param;
    frm = document.tambahUrusan;
    frm.action = url;
    frm.submit();
  }

  function simpanUrusan2() {
    // SAVE URUSAN MANY HAKMILIK 
    var param = '';
    var ku = $("#kodUrusan").val();
    var trhdaftar = $("#tarikhDaftar").val();
    var j = $('#jam').val();
    var m = $('#minit').val();
    if (ku === '') {
      alert('Sila Pilih Urusan.');
      return;
    }
    if (j === "" || m === "") {
      alert('Sila masukkan jam dan minit.');
      return;
    }
    if (trhdaftar === '') {
      alert('Sila Masukkan Tarikh Daftar.');
      return;
    }
    $('.pilih').each(function(index) {
      var a = $('#checkbox' + index).is(":checked");
      if (a) {
        param = param + '&idHM=' + $('#checkbox' + index).val();
      }
    });
    if (param === '') {
      alert('Sila Pilih Pihak terlebih dahulu.');
      return;
    }
    doBlockUI();
    var url = '${pageContext.request.contextPath}/daftar/utiliti/kemaskiniData?simpanUrusan' + param;
    frm = document.tambahUrusan;
    frm.action = url;
    frm.submit();
  }

  function updateUrusan() {
    // UPDATE URUSAN  
    doBlockUI();
//    alert($("#kodSerah").val());
    var url = '${pageContext.request.contextPath}/daftar/utiliti/kemaskiniData?updateUrusan';
    frm = document.tambahUrusan;
    frm.action = url;
    frm.submit();
  }

  function updateUrusan2() {
    // UPDATE URUSAN  
    doBlockUI();
    var param = '';
    $('.huSblm').each(function(index) {
      var a = $('#checkboxurusan' + index).is(":checked");
      if (a) {
        param = param + '&iu=' + $('#checkboxurusan' + index).val();
//        alert(param);
      }
    });
    if (param === '') {
      $.unblockUI();
      alert('Sila Pilih Urusan terlebih dahulu.');
      return;
    }
    var url = '${pageContext.request.contextPath}/daftar/utiliti/kemaskiniData?updateUrusan' + param;
    frm = document.tambahUrusan;
    frm.action = url;
    frm.submit();
  }

  function hapusMohonHbgn(val) {
    // DELETE URUSAN TERLIBAT
    doBlockUI();
    if (confirm('Adakah anda pasti untuk hapus maklumat urusan terlibat?')) {
//      alert("id urusan : " + val);
      var url = '${pageContext.request.contextPath}/daftar/utiliti/kemaskiniData?hapusMohonHbgn&idP=' + val;
      frm = document.tambahUrusan;
      frm.action = url;
      frm.submit();
    }
  }

  function viewUrusan(val, val1) {
    var url = "${pageContext.request.contextPath}/daftar/utiliti/kemaskiniData?tambahUrusan&jenisUrusan=" + val1
            + "&kumpHm=" + $("#kumpHm").val() + "&id=" + val;
    window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=800");
  }

  function numJam(val) {
    var strValidCharacters = "1234567890";
    var strReturn = "";
    var strBuffer = "";
//    var intIndex = 0;
    for (var intIndex = 0; intIndex < val.length; intIndex++) {
      strBuffer = val.substr(intIndex, 1);
      if (strValidCharacters.indexOf(strBuffer) > -1) {
        // num
        strReturn += strBuffer;
      }
    }
    // check hour format
    if (strReturn > 12) {
      alert("Maaf! Format Jam Tidak Betul.");
      strReturn = "";
    }
    $('#jam').val(strReturn);
  }

  function numMinit(val) {
    var strValidCharacters = "1234567890";
    var strReturn = "";
    var strBuffer = "";
//    var intIndex = 0;
    for (var intIndex = 0; intIndex < val.length; intIndex++) {
      strBuffer = val.substr(intIndex, 1);
      if (strValidCharacters.indexOf(strBuffer) > -1) {
        // num
        strReturn += strBuffer;
      }
    }
    // check minute format
    if (strReturn > 59) {
      alert("Maaf! Format Minit Tidak Betul.");
      strReturn = "";
    }
    $('#minit').val(strReturn);
  }

  function changeVal(value) {
    if (value === "y") {
      $('#id_serah').show();
    }
    if (value === "t") {
      $('#id_serah').hide();
    }
  }
function doEdit(v) {
                window.open("${pageContext.request.contextPath}/daftar/utiliti/kemaskiniData?maklumatDetail&idHakmilik="+ v, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
            }
  function doCalcEndDate(id) {
    var hari = parseInt($('#tempohHari').val(), 10);
    var bln = parseInt($('#tempohBulan').val(), 10);
    var thn = parseInt($('#tempohTahun').val(), 10);
    var kodUrusan = $('#kodUrusan').val();
    if ($('#' + id).val() === '') {
      return;
    }

    if (isNaN(hari) && isNaN(bln) && isNaN(thn) && kodUrusan != 'PJLT') {
      alert('Sila Masukan Tempoh.');
      $('#' + id).val('');
      return;
    }
    if (hari === '0' && bln === '0' && thn === '0')
      return;
    var startDate = $('#' + id).val();
    //manual process :: should find conclusion on this
    var y = parseInt(startDate.substring(6, 10), 10);
    var m = parseInt(startDate.substring(3, 5), 10);
    var d = parseInt(startDate.substring(0, 2), 10);
    if (!isNaN(hari)) {
      d = d + hari;
      if (d === 0) {
        m = m - 1;
      }
    }

    if (!isNaN(bln)) {
      m = m + bln;
      if (m > 12) {
        y = y + 1;
        m = m - 12;
        if (m === 2) {
          var isleap = (y % 4 === 0 && (y % 100 !== 0 || y % 400 === 0));
          if (d >= 30) {
            if (isleap) {
              d = 28;
            } else {
              d = 27;
            }
          } else if (d === 0) {
            if (isleap) {
              d = 29;
            } else {
              d = 28;
            }
          }
        }
      }
    }

    if (!isNaN(thn)) {
      y = y + thn;
      if (d === 0) {
        m = m - 1;
      }
    }

    if (d === 0 && m === 0) {
      y = y - 1;
    }
    //y = y + thn;
    var endDate = new Date();
    var s = 1;
    endDate.setDate(d);
    endDate.setMonth(m - 1);
    endDate.setFullYear(y);
    endDate.setDate(endDate.getDate() - s);
    $('#tarikhTamat').val(endDate.format("dd/mm/yyyy"));
  }

  function doCalculateDutiStem(id, amt) {
    var _q = amt.indexOf(",");
    if (_q > 0) {
      amt = amt.replace(/,/g, "");
    }
//    alert(amt);
    var tmp = parseFloat(amt);
    if (tmp > 100000) {
      tmp = tmp * 0.02;
    } else {
      tmp = tmp * 0.01;
    }
    //convert(tmp, id);
    //$('#'+id).val(tmp);
    var amaun = cf(tmp);
    amaun = cf2(amaun);
    $('#' + id).val(amaun);
  }

  function cf(amount) {
    var t = amount;
    var i = parseFloat(amount);
    if (isNaN(i)) {
      i = 0.00;
    }
    var minus = '';
    if (i < 0) {
      minus = '-';
    }
    i = Math.abs(i);
    i = parseInt((i + .005) * 100);
    i = i / 100;
    s = new String(i);
    if (s.indexOf('.') < 0) {
      s += '.00';
    }
    if (s.indexOf('.') == (s.length - 2)) {
      s += '0';
    }
    s = minus + s;
    return s;
  }

  function cf2(amount) {
    var delimiter = ","; // replace comma if desired
    var a = amount.split('.', 2)
    var d = a[1];
    var i = parseInt(a[0]);
    if (isNaN(i)) {
      return '';
    }
    var minus = '';
    if (i < 0) {
      minus = '-';
    }
    i = Math.abs(i);
    var n = new String(i);
    var a = [];
    while (n.length > 3) {
      var nn = n.substr(n.length - 3);
      a.unshift(nn);
      n = n.substr(0, n.length - 3);
    }
    if (n.length > 0) {
      a.unshift(n);
    }
    n = a.join(delimiter);
    if (d.length < 1) {
      amount = n;
    } else {
      amount = n + '.' + d;
    }
    amount = minus + amount;
    return amount;
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
  <s:form beanclass="etanah.view.daftar.utiliti.KemaskiniDataActionBean" name="tambahUrusan" id="tambahUrusan">
    <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
    <div class="subtitle">
      <s:hidden name="jenisUrusan" id="jenisUrusan" value="${actionBean.jenisUrusan}"/>    
      <s:hidden name="kumpHm" id="kumpHm" value="${actionBean.kumpHm}"/> 
      <s:hidden name="idHakmilik" id="idHakmilik" value="${actionBean.idHakmilik}"/> 
      <fieldset class="aras1">
        <lagend>Maklumat Urusan</lagend><br><br>

        <c:if test="${actionBean.hakmilikUrusan eq null}">
          <!----------------- Add new urusan --------------------------->
          <div align="center">
            <table>            
              <tr>
                <td id="tdLabel"><font color="red">*</font>Urusan :&nbsp</td>
                <td id="tdDisplay">     
                  <s:hidden name="kodUrusan" id="hiddenkodUrusan" />
                  <s:text name="hakmilikUrusan.kodUrusan.kod" id="kodUrusan" size="4" maxlength="5"
                          onblur="this.value=this.value.toUpperCase();"/>&nbsp;-&nbsp; 
                  <s:select name="hakmilikUrusan.kodUrusan.kod" id="namaUrusan" style="width:400px;">  
                    <s:option value="">-- Sila Pilih --</s:option>   
                    <c:choose>  
                      <c:when test="${actionBean.jenisUrusan eq 'B'}">
                        <s:options-collection collection="${actionBean.listkodUrusanB}" label="nama" value="kod" />
                      </c:when>
                      <c:when test="${actionBean.jenisUrusan eq 'N'}">
                        <s:options-collection collection="${actionBean.listkodUrusanN}" label="nama" value="kod" />
                      </c:when>
                      <c:when test="${actionBean.jenisUrusan eq 'SC'}">
                        <s:options-collection collection="${actionBean.listkodUrusanSC}" label="nama" value="kod" />
                      </c:when>
                      <c:when test="${actionBean.jenisUrusan eq 'NB'}">
                        <s:options-collection collection="${actionBean.listkodUrusanNB}" label="nama" value="kod" />
                      </c:when> 
                      <c:otherwise>
                        <s:options-collection collection="${actionBean.listkodUrusan}" label="nama" value="kod" />
                      </c:otherwise>
                    </c:choose>  
                  </s:select>
                </td>
              </tr>
              <tr>
                <td id="tdLabel"><font color="red">*</font>No Perserahan :&nbsp</td>
                <td id="tdDisplay"> 
                  <input type="radio" name="radio" value="y" onchange="javaScript:changeVal(this.value)" /> Ada
                  <input type="radio" name="radio" value="t" onchange="javaScript:changeVal(this.value)" checked="true"/> Tiada
                  <div id="id_serah" class="subtitle">
                    <s:text name="noPerserahan" size="7" maxlength="6" title="no perserahan"/>&nbsp;/&nbsp;
                    <s:text name="tahunPerserahan" size="5" maxlength="4" title="tahun perserahan"/>
                    <em>contoh: 222 / 2005</em>
                  </div>
                </td>
              </tr>
              <tr>
                <td id="tdLabel"><font color="red">*</font>Tarikh Daftar :&nbsp</td>
                <td id="tdDisplay">       
                  <s:text name="tarikhDaftar" class="datepicker" id="tarikhDaftar" 
                          size="30" formatType="date" formatPattern="dd/MM/yyyy" 
                          value="${actionBean.hakmilikUrusan.tarikhDaftar}"/>
                </td>
              </tr> 
              <tr><td id="tdLabel"><font color="red">*</font>Masa :&nbsp</td>
                <td id="tdDisplay"> 
                  <s:text name="jam" size="3" maxlength="2" id="jam" onchange="numJam(this.value);"/>&nbsp&nbsp:&nbsp
                  <s:text name="minit" size="3" maxlength="2" id="minit" onchange="numMinit(this.value);"/>&nbsp&nbsp                               
                  <s:select name="ampm" style="width:80px">
                    <s:option value="AM">Pagi</s:option>
                    <s:option value="PM">Petang</s:option>
                  </s:select> 
                </td>
              </tr> 
              <tr>
                <td id="tdLabel">No Rujukan Fail :&nbsp</td>
                <td id="tdDisplay">     
                  <s:text name="hakmilikUrusan.noFail" id="noFail" size="30" 
                          onblur="this.value=this.value.toUpperCase();"
                          value="${actionBean.hakmilikUrusan.noFail}" />
                </td>
              </tr> 
              <tr>
                <td id="tdLabel">No Jilid :&nbsp</td>
                <td id="tdDisplay">     
                  <s:text name="noJilid" id="noJilid" size="30" maxlength="10"
                          onblur="this.value=this.value.toUpperCase();"
                          value="${actionBean.hakmilikUrusan.folderDokumen.noJilid}" />
                </td>
              </tr>
              <tr>
                <td id="tdLabel">No Folio :&nbsp</td>
                <td id="tdDisplay">     
                  <s:text name="noFolio" id="noFolio" size="30" maxlength="10"
                          onblur="this.value=this.value.toUpperCase();"
                          value="${actionBean.hakmilikUrusan.folderDokumen.noFolio}" />
                </td>
              </tr>
              <c:if test="${fn:length(actionBean.listSenaraiHakmilik) eq 1}">
                <tr>
                  <td id="tdLabel">ID Hakmilik :&nbsp</td>
                  <td id="tdDisplay">     
                    <s:select name="idHakmilik2" id="idHakmilik2" style="width:197px;">                      
                      <s:options-collection collection="${actionBean.listSenaraiHakmilik}" label="idHakmilik" value="idHakmilik" />  
                    </s:select>
                  </td>
                </tr>
              </c:if>
            </table>
          </div>
          <br>
          <c:if test="${fn:length(actionBean.listSenaraiHakmilik) > 1}">
            <font size="2" color="black">Sila pilih hakmilik yang terlibat</font>
            <div class="content" align="center">
              <display:table class="tablecloth" style="width:90%;" id="line" cellpadding="0" cellspacing="0"
                             requestURI="/daftar/utiliti/kemaskiniData" name="${actionBean.listSenaraiHakmilik}" >
                <display:column title="<input type='checkbox' name='semua' onclick='javascript:selectAll(this);'>" style="width:10px">
                  <s:checkbox name="checkbox${line_rowNum-1}" id="checkbox${line_rowNum-1}" value="${line.idHakmilik}" class="pilih"/>
                </display:column>
                <display:column title="Bil" style="width:5%"><div align="right">${line_rowNum}</div></display:column>
                <display:column title="ID Hakmilik" property="idHakmilik" /> 
              </display:table>
              <br>
            </div>
          </c:if>
        </c:if>

        <c:if test="${actionBean.hakmilikUrusan ne null}">
          <!----------------- Update urusan --------------------------->
          <div align="center">
            <s:hidden name="kodSerah" value="${actionBean.hakmilikUrusan.kodUrusan.kodPerserahan.kod}" id="kodSerah"/>
            <s:hidden name="idUrusan" value="${actionBean.mohonUrusan.idUrusan}"/>
            <s:hidden name="idhu" id="idhu" value="${actionBean.hakmilikUrusan.idUrusan}"/>
            <s:hidden name="idMrl" value="${actionBean.mohonRujLuar.idRujukan}"/>
            <table class="tablecloth" width="90%" style="margin-left: auto; margin-right: auto;">
              <tr><th colspan="2">Butiran Urusan</th></tr>
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
              <tr>
                <td id="tdLabel">Tarikh Perserahan :&nbsp</td>
                <td id="tdDisplay" > 
                  <fmt:formatDate type="date" pattern="dd/MM/yyyy hh:mm:ss a" value="${actionBean.hakmilikUrusan.tarikhDaftar}"/>&nbsp;
                </td>
              </tr> 
              <tr>
                  <td id="tdLabel">Status :&nbsp (<c:if test="${actionBean.hakmilikUrusan.aktif == 'Y'}">
                                            Aktif
                                        </c:if>
                                        <c:if test="${actionBean.hakmilikUrusan.aktif == 'T'}">
                                            Batal
                                        </c:if>)</td>
                    <td >
                            <s:radio name="aktif" value="Y"></s:radio> Aktif
                            <s:radio name="aktif" value="T" ></s:radio> Batal
                    </td>
                </tr>
              <tr><th colspan="2">Maklumat Kemaskini</th></tr>   

              <!----------------  UPDATE URUSAN SURATCARA   -->
              <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kodPerserahan.kod eq 'SC'}">
                <%--<tr>
                  <td id="tdLabel">Amaun(Rm) :&nbsp</td>
                  <td id="tdDisplay"> 
                    <s:text name="mohonUrusan.perjanjianAmaun" id="pjanjianAmaun"
                            onchange="doCalculateDutiStem('pjanjianDutiSetam', this.value);"
                            formatPattern="#,###.00"/>&nbsp;
                  </td>
                </tr>
                <tr>
                  <td id="tdLabel">Duit Setem(RM) :&nbsp</td>                  
                  <td id="tdDisplay"> 
                    <s:text name="mohonUrusan.perjanjianDutiSetem" id="pjanjianDutiSetam"
                            onblur="this.value=this.value.toUpperCase();" formatPattern="#,###.00"/>
                  </td>
                </tr>
                <tr>
                  <td id="tdLabel">No Makhamah :&nbsp</td>
                  <td id="tdDisplay"> 
                    <s:text name="mohonUrusan.perjanjianNoRujukan" size="30" onblur="this.value=this.value.toUpperCase();"/>&nbsp;
                  </td>
                </tr>
                <tr>
                  <td id="tdLabel">Tarikh Penyaksian :&nbsp</td>
                  <td id="tdDisplay"> 
                    <s:text name="mohonUrusan.tarikhSaksi" class="datepicker" id="tarikhSaksi" 
                            size="30" formatType="date" formatPattern="dd/MM/yyyy"/>
                  </td>
                </tr>--%>                
                <tr>
                  <td id="tdLabel">Tempoh :&nbsp</td>
                  <td id="tdDisplay">    
                    <s:text name="mohonRujLuar.tempohTahun" id="tempohTahun" size="5" onchange="doCalcEndDate('tarikhMula');" 
                            onblur="this.value=this.value.toUpperCase();"/> Tahun &nbsp;
                    <s:text name="mohonRujLuar.tempohBulan" id="tempohBulan" size="5" onchange="doCalcEndDate('tarikhMula');" 
                            onblur="this.value=this.value.toUpperCase();"/> Bulan &nbsp;                    
                  </td>
                </tr>
                <tr>
                  <td id="tdLabel">Tarikh Mula :&nbsp</td>
                  <td id="tdDisplay"> 
                    <s:text name="mohonRujLuar.tarikhKuatkuasa" class="datepicker" id="tarikhMula" 
                            size="30" formatType="date" formatPattern="dd/MM/yyyy" onchange="doCalcEndDate(this.id);"/>
                  </td>
                </tr>
                <tr>
                  <td id="tdLabel">Tarkih Tamat :&nbsp</td>
                  <td id="tdDisplay"> 
                    <s:text name="mohonRujLuar.tarikhTamat" class="datepicker" id="tarikhTamat" 
                            size="30" formatType="date" formatPattern="dd/MM/yyyy"/>
                  </td>
                </tr>
                <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'PJBT'
                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PJKBT'
                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PJKT'}">
                      <tr>
                        <td id="tdLabel">Luas / Unit :&nbsp</td>
                        <td id="tdDisplay"> 
                          <s:text name="hakmilikUrusan.luasTerlibat" id="luasTerlibat" size="5" onblur="this.value=this.value.toUpperCase();"/> &nbsp;/  
                          <s:select name="hakmilikUrusan.kodUnitLuas.kod" id="kodUOM" style="text-transform:uppercase" >
                            <s:options-collection collection="${actionBean.senaraiKodUOMByLuas2}" label="nama" value="kod"/>
                          </s:select>
                        </td>
                      </tr>                                         
                </c:if>                
              </c:if>
              <!----------------  UPDATE URUSAN BORANG   -->
              <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kodPerserahan.kod eq 'B'}">
                <tr>
                  <td id="tdLabel">No Rujukan Fail :&nbsp</td>
                  <td id="tdDisplay"> 
                    <s:text name="hakmilikUrusan.noFail" id="noFail" size="30" onblur="this.value=this.value.toUpperCase();"/>&nbsp;
                  </td>
                </tr>
                <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'KVPT'}">
                    <tr>
                        <td id="tdLabel">Sebab Kaveat :&nbsp</td>
                        <td id="tdDisplay">
                          <s:text name="mohonRujLuar.ulasan" id="ulasan" size="30" onblur="this.value=this.value.toUpperCase();"/> &nbsp;
                        </td>
                      </tr>
                </c:if>
                <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'PLK'
                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PLS'
                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PLT'}">
                      <tr>
                        <td id="tdLabel">Tempoh :&nbsp</td>
                        <td id="tdDisplay">    
                          <s:text name="mohonRujLuar.tempohTahun" id="tempohTahun" size="5"
                                  onblur="this.value=this.value.toUpperCase();"/> Tahun &nbsp;
                          <s:text name="mohonRujLuar.tempohBulan" id="tempohBulan" size="5"
                                  onblur="this.value=this.value.toUpperCase();"/> Bulan &nbsp;                    
                        </td>
                      </tr>
                      <tr>
                        <td id="tdLabel">Tarikh Mula :&nbsp</td>
                        <td id="tdDisplay"> 
                          <s:text name="mohonRujLuar.tarikhKuatkuasa" class="datepicker" id="tarikhMula" 
                                  size="30" formatType="date" formatPattern="dd/MM/yyyy" onchange="doCalcEndDate(this.id);"/>
                        </td>
                      </tr>
                      <tr>
                        <td id="tdLabel">No Perintah :&nbsp</td>
                        <td id="tdDisplay">
                          <s:text name="mohonRujLuar.noSidang" id="noSidang" size="30" onblur="this.value=this.value.toUpperCase();"/> &nbsp;
                        </td>
                      </tr>
                      <tr>
                        <td id="tdLabel">Tarikh Perintah :&nbsp</td>
                        <td id="tdDisplay">
                          <s:text name="mohonRujLuar.tarikhSidang" class="datepicker" id="tarikhTamat" 
                                  size="30" formatType="date" formatPattern="dd/MM/yyyy"/>
                        </td>
                      </tr>
                </c:if>
                <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod ne 'PLK'
                              and actionBean.hakmilikUrusan.kodUrusan.kod ne 'PLS'
                              and actionBean.hakmilikUrusan.kodUrusan.kod ne 'PLT'
                              and actionBean.hakmilikUrusan.kodUrusan.kod ne 'KVSK'
                              and actionBean.hakmilikUrusan.kodUrusan.kod ne 'KVAK'
                              and actionBean.hakmilikUrusan.kodUrusan.kod ne 'KVPT'
                              and actionBean.hakmilikUrusan.kodUrusan.kod ne 'KVPK'
                              and actionBean.hakmilikUrusan.kodUrusan.kod ne 'KVSPC'}">
                      <tr>
                        <td id="tdLabel">Jenis Perintah :&nbsp</td>
                        <td id="tdDisplay">
                          <s:select name="mohonRujLuar.kodPerintah.kod" id="order" onchange="doselectOrder();" style="width:200px">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodPerintah}" value="kod" label="nama"/>
                          </s:select>
                        </td>
                      </tr>               
                      <tr>
                        <td id="tdLabel">No Surat Amanah/No Perintah/Saman Pemula :&nbsp</td>
                        <td id="tdDisplay">
                          <s:text name="mohonRujLuar.noRujukan" id="noRujukan" size="30" onblur="this.value=this.value.toUpperCase();"/> &nbsp;
                        </td>
                      </tr>
                      <tr>
                        <td id="tdLabel">No Mahkamah :&nbsp</td>
                        <td id="tdDisplay">
                          <s:text name="mohonRujLuar.noSidang" id="noSidang" size="30" onblur="this.value=this.value.toUpperCase();"/> &nbsp;
                        </td>
                      </tr>
                      <tr>
                        <td id="tdLabel">Mahkamah/Pejabat Tanah Daerah :&nbsp</td>
                        <td id="tdDisplay">
                          <s:text name="mohonRujLuar.namaSidang" id="namaSidang" size="60" onblur="this.value=this.value.toUpperCase();"/> &nbsp;
                        </td>
                      </tr>
                      <tr>
                        <td id="tdLabel">Tarikh Perintah :&nbsp</td>
                        <td id="tdDisplay">
                          <s:text name="mohonRujLuar.tarikhSidang" class="datepicker" id="tarikhTamat" 
                                  size="30" formatType="date" formatPattern="dd/MM/yyyy"/>
                        </td>
                      </tr>
                      <tr>
                        <td id="tdLabel">Sebab Perintah :&nbsp</td>
                        <td id="tdDisplay">
                          <s:text name="mohonRujLuar.ulasan" id="ulasan" size="30" onblur="this.value=this.value.toUpperCase();"/> &nbsp;
                        </td>
                      </tr>
                      <tr>
                        <td id="tdLabel">Tempoh :&nbsp</td>
                        <td id="tdDisplay">    
                          <s:text name="mohonRujLuar.tempohTahun" id="tempohTahun" size="5"
                                  onblur="this.value=this.value.toUpperCase();"/> Tahun &nbsp;
                          <s:text name="mohonRujLuar.tempohBulan" id="tempohBulan" size="5"
                                  onblur="this.value=this.value.toUpperCase();"/> Bulan &nbsp;                    
                        </td>
                      </tr>
                      <tr>
                        <td id="tdLabel">Tarkih Tamat :&nbsp</td>
                        <td id="tdDisplay"> 
                          <s:text name="mohonRujLuar.tarikhTamat" class="datepicker" 
                                  size="30" formatType="date" formatPattern="dd/MM/yyyy"/>
                        </td>
                      </tr>
                </c:if>
              </c:if>
              <!----------------  UPDATE URUSAN NOTA   -->
              <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kodPerserahan.kod eq 'N'}">   
                <tr>
                  <td id="tdLabel">No Rujukan Fail :&nbsp</td>
                  <td id="tdDisplay"> 
                    <s:text name="hakmilikUrusan.noFail" id="noFail" size="30" onblur="this.value=this.value.toUpperCase();"/>&nbsp;
                  </td>
                </tr>
                <c:if test ="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'SBKSL'}">
                      <tr>
                        <td id="tdLabel">Nombor Sidang :&nbsp</td>
                        <td id="tdDisplay"> 
                          <s:text name="mohonRujLuar.noSidang" id="noSidang" size="50" onblur="this.value=this.value.toUpperCase();"/>&nbsp;
                        </td>
                      </tr>
                      <tr>
                        <td id="tdLabel">Tarikh Sidang :&nbsp</td>
                        <td id="tdDisplay"> 
                          <s:text name="mohonRujLuar.tarikhSidang" class="datepicker" id="tarikhSidang" 
                                  size="30" formatType="date" formatPattern="dd/MM/yyyy"/>
                        </td>
                      </tr>
                </c:if>
                <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'IGSA'
                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'IGSA5'
                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'IGSA6'
                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'IPM'
                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'IRM'
                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'IROA'
                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'IRTB'
                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'ITB'
                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'ITP'
                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KB'
                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KRM'
                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PTB'
                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PTP'}">
                      <tr>
                        <td id="tdLabel">Nombor Warta :&nbsp</td>
                        <td id="tdDisplay"> 
                          <s:text name="mohonRujLuar.noRujukan" id="noRujukan" size="30" onblur="this.value=this.value.toUpperCase();"/>&nbsp;
                        </td>
                      </tr>
                      <tr>
                        <td id="tdLabel">Tarikh Warta :&nbsp</td>
                        <td id="tdDisplay"> 
                          <s:text name="mohonRujLuar.tarikhRujukan" class="datepicker" id="tarikhRujukan" 
                                  size="30" formatType="date" formatPattern="dd/MM/yyyy"/>
                        </td>
                      </tr>
                      <tr>
                        <td id="tdLabel">Kawasan :&nbsp</td>
                        <td id="tdDisplay">
                          <s:text name="mohonRujLuar.item" id="item" size="60" onblur="this.value=this.value.toUpperCase();"/> &nbsp;
                        </td>
                      </tr>
                </c:if>
                <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'IKOA'
                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'N6A'
                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PHKK'
                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PHSK'}">
                      <tr>
                        <td id="tdLabel">Nombor Warta :&nbsp</td>
                        <td id="tdDisplay"> 
                          <s:text name="mohonRujLuar.noRujukan" id="noRujukan" size="30" onblur="this.value=this.value.toUpperCase();"/>&nbsp;
                        </td>
                      </tr>
                      <tr>
                        <td id="tdLabel">Tarikh Warta :&nbsp</td>
                        <td id="tdDisplay"> 
                          <s:text name="mohonRujLuar.tarikhRujukan" class="datepicker" id="tarikhRujukan" 
                                  size="30" formatType="date" formatPattern="dd/MM/yyyy"/>
                        </td>
                      </tr>
                </c:if>                      
                <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'MAJB'
                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'MAJD'
                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'RKSR'}">
                      <tr>
                        <td id="tdLabel">Kawasan :&nbsp</td>
                        <td id="tdDisplay">
                          <s:text name="mohonRujLuar.item" id="item" size="60" onblur="this.value=this.value.toUpperCase();"/> &nbsp;
                        </td>
                      </tr>
                </c:if>
                <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'LMTP'}">
                  <tr>
                    <td id="tdLabel">Tarikh Mula :&nbsp</td>
                    <td id="tdDisplay"> 
                      <s:text name="mohonRujLuar.tarikhKuatkuasa" class="datepicker" id="tarikhMula" 
                              size="30" formatType="date" formatPattern="dd/MM/yyyy" />
                    </td>
                  </tr>
                  <tr>
                    <td id="tdLabel">Tarkih Tamat :&nbsp</td>
                    <td id="tdDisplay"> 
                      <s:text name="mohonRujLuar.tarikhTamat" class="datepicker" id="tarikhTamat" 
                              size="30" formatType="date" formatPattern="dd/MM/yyyy"/>
                    </td>
                  </tr>
                </c:if>
                <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'HLTE'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'ABTKB'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'ABT-D'}">
                  <tr>
                    <td id="tdLabel">Luas / Unit :&nbsp</td>
                    <td id="tdDisplay"> 
                      <s:text name="hakmilikUrusan.luasTerlibat" id="luasTerlibat" size="5" onblur="this.value=this.value.toUpperCase();"/> &nbsp;/  
                      <s:select name="hakmilikUrusan.kodUnitLuas.kod" id="kodUOM" style="text-transform:uppercase" >
                        <s:options-collection collection="${actionBean.senaraiKodUOMByLuas2}" label="nama" value="kod"/>
                      </s:select>
                    </td>
                  </tr>                  
                </c:if>
                  <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'ABTKB'}">
                  <tr>
                    <td id="tdLabel">Cukai:&nbsp</td>
                    <td id="tdDisplay"> 
                      <s:text name="hakmilikUrusan.cukaiBaru" id="cukaiBaru" size="5"/> &nbsp;
                    </td>
                  </tr>                  
                </c:if>
                <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'N7A'}">
                  <tr>
                    <td id="tdLabel">Nombor Warta :&nbsp</td>
                    <td id="tdDisplay"> 
                      <s:text name="mohonRujLuar.noRujukan" id="noRujukan" size="30" onblur="this.value=this.value.toUpperCase();"/>&nbsp;
                    </td>
                  </tr>
                  <tr>
                    <td id="tdLabel">Tarikh Warta :&nbsp</td>
                    <td id="tdDisplay"> 
                      <s:text name="mohonRujLuar.tarikhRujukan" class="datepicker" id="tarikhRujukan" 
                              size="30" formatType="date" formatPattern="dd/MM/yyyy"/>
                    </td>
                  </tr>
                  <tr>
                    <td id="tdLabel">Tarikh Tampal :&nbsp</td>
                    <td id="tdDisplay"> 
                      <s:text name="mohonRujLuar.tarikhKuatkuasa" class="datepicker" id="tarikhKuatkuasa" 
                              size="30" formatType="date" formatPattern="dd/MM/yyyy"/>
                    </td>
                  </tr>
                  <tr>
                    <td id="tdLabel">Tarikh Disampaikan :&nbsp</td>
                    <td id="tdDisplay"> 
                      <s:text name="mohonRujLuar.tarikhDisampai" class="datepicker" id="tarikhDisampai" 
                              size="30" formatType="date" formatPattern="dd/MM/yyyy"/>
                    </td>
                  </tr>
                </c:if>
              </c:if>
            </table>
          </div>
        </c:if>
        <br> 
        <%--------------------------------------------------------------------------------------
         ** NOTES: For urusan that need to have 'URUSAN TERLIBAT', please add urusan named HERE 
        ---------------------------------------------------------------------------------------%>
        <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'GDPJ'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'GDPJK'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PJKT'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PJKBT'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KVAK'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KVSK'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KVPK'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PLK'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KVSPC'
                      or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KVLK' }">
              <legend>Senarai Urusan</legend> 
              <p style="color:red">
                *Sila pilih urusan yang terlibat dan tekan butang simpan.
              </p>
              <div align="center">
                <display:table class="tablecloth" style="width:90%;" cellpadding="0" cellspacing="0" id="line"
                               name="${actionBean.listHakmilikUrusanSebelum}">
                  <display:column title="<input type='checkbox' name='semua' onclick='javascript:selectAllUrusan(this);'>" style="width:1%;">                       
                    <s:checkbox name="checkboxurusan" id="checkboxurusan${line_rowNum-1}" 
                                value="${line.idUrusan}" class="huSblm"/>  
                  </display:column>
                  <display:column title="Bil" style="width:1%;"><div align="right">${line_rowNum}</div></display:column>
                  <display:column property="idPerserahan" title="Id Perserahan" style="width:17%;"/>
                  <display:column title="Kod Urusan" style="text-transform:uppercase;width:8%;">
                    <div align="center">${line.kodUrusan.kod}</div>
                  </display:column>
                  <display:column property="kodUrusan.nama" title="Nama Urusan" />                  
                  <display:column property="tarikhDaftar" title="Tarikh Daftar" style="width:15%;" format="{0,date,dd/MM/yyyy hh:mm aa}"/>
                </display:table>
              </div>    
              <p align="center">
                <s:button name="" value="Simpan" class="btn" onclick="updateUrusan2();"/>&nbsp;
                <c:if test="${fn:length(actionBean.listMohonHbgn) eq 0}">
                  <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </c:if>
              </p>
              <br>
              <c:if test="${fn:length(actionBean.listMohonHbgn) > 0}">
                <legend>Senarai Urusan Terlibat</legend> 
                <div align="center">
                  <display:table class="tablecloth" style="width:90%;" cellpadding="0" cellspacing="0" id="line"
                                 name="${actionBean.listMohonHbgn}">                    
                    <display:column title="Bil" style="width:1%;"><p align="right">${line_rowNum}</p></display:column>
                    <display:column property="permohonanSasaran.idPermohonan" title="Id Perserahan" style="width:17%;"/>
                    <display:column title="Urusan" >
                      ${line.permohonanSasaran.kodUrusan.kod} - ${line.permohonanSasaran.kodUrusan.nama}
                    </display:column>
                    <display:column title="Hapus" style="width:5%;">  
                      <div align="center">
                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                             id='rem${line_rowNum}' onclick="hapusMohonHbgn('${line.idPermohonanHubungan}')" onmouseover="this.style.cursor = 'pointer';" >
                      </div>
                    </display:column>  
                  </display:table>
                </div> 
                <br>
                <p align="center">
                  <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
              </c:if>
        </c:if>

        <p align="center">
          <c:if test="${actionBean.hakmilikUrusan eq null}">
            <c:if test="${fn:length(actionBean.listSenaraiHakmilik) eq 1}"></c:if>
            <c:choose>
              <c:when test="${fn:length(actionBean.listSenaraiHakmilik) eq 1}">                
                <s:button name="" value="Simpan" class="btn" onclick="simpanUrusan();"/>&nbsp;<!--  per hakmilik  -->
              </c:when>
              <c:otherwise>                
                <s:button name="" value="Simpan" class="btn" onclick="simpanUrusan2();"/>&nbsp;<!--  berkelompok  -->
              </c:otherwise>
            </c:choose>
            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="doEdit('${actionBean.idHakmilik}');"/>
          </c:if>
          <c:if test="${actionBean.hakmilikUrusan ne null}">
            <%-------------------------------------------------------------------------------
              ** NOTES: if urusan need to add 'Urusan terlibat' table, please add urusan's 
                        kod here to prevent duplication of button 'Simpan' and 'Tutup' 
            --------------------------------------------------------------------------------%>
            <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod ne 'GDPJ'
                          and actionBean.hakmilikUrusan.kodUrusan.kod ne 'GDPJK'
                          and actionBean.hakmilikUrusan.kodUrusan.kod ne 'PJKT'
                          and actionBean.hakmilikUrusan.kodUrusan.kod ne 'PJKBT'
                          and actionBean.hakmilikUrusan.kodUrusan.kod ne 'KVAK'
                          and actionBean.hakmilikUrusan.kodUrusan.kod ne 'KVSK'
                          and actionBean.hakmilikUrusan.kodUrusan.kod ne 'KVPK'
                          and actionBean.hakmilikUrusan.kodUrusan.kod ne 'PLK'
                          and actionBean.hakmilikUrusan.kodUrusan.kod ne 'KVSPC'
                          and actionBean.hakmilikUrusan.kodUrusan.kod ne 'KVLK'}">
              <s:button name="" value="Simpan" class="btn" onclick="updateUrusan();"/>&nbsp; 
              <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </c:if> 
          </c:if>
        </p>
        <br>     
      </fieldset>
    </div>
  </s:form>
</div>