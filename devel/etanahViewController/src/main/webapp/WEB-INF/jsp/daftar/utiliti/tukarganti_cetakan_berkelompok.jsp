<%-- 
    Document   : tukarganti_cetakan_berkelompok
    Created on : Oct 14, 2013, 10:28:29 AM
    Author     : ei
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf"%>
<!DOCTYPE html>
<title>e-Tanah Tukar Ganti</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.tools.min.js?select=full&debug=true"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript">
  $(document).ready(function() {
    $("img[title]").tooltip({
      // tweak the position
      offset: [10, 2],
      // use the "slide" effect
      effect: 'slide'
    }).dynamic({bottom: {direction: 'down', bounce: true}});

    $('#isiSemula').click(function() {
      $('#idHakmilik').val('');
      $('#idMohon').val('');
//      $('#bilHakmilik0').val('1');
//      $('#idHakmilikSiriDari0').val('');
//      $('#idHakmilikSiriHingga0').val('');
    });

//    $('#idMohon').blur(function() {
//      $('#idHakmilikSiriDari0').val("");
//      $('#idHakmilikSiriHingga0').val("");
//    });
  });

//  function appendAutoAll(intIndex) {
//    // AUTO INSERT NO HAKMILIK IF BIL HAKMILIK IS CHANGED    
//    var val = $('#idHakmilikSiriDari' + intIndex).val();
//    if (val != '') {
//      appendAuto(val, intIndex);
//    }
//  }

//  function appendAuto(val, id) {
//    // AUTO INSERT NO HAKMILIK HINGGA
//    $('#idMohon').val("");
//    var bil = $('#bilHakmilik' + id).val();
//    var len = val.length;
//    var intIndex = 0;
//    var pre = "";
//    if (val !== '') {
//      val = val.toUpperCase();
//      $('#idHakmilikSiriDari' + id).val(val);
//      bil = bil - 1;
//      if (parseInt(bil, 10) > 0) {
//        for (intIndex = len - 1; intIndex >= 0; intIndex--) {
//          var c = val.charAt(intIndex);
//          if (c >= '0' && c <= '9') {
//            pre = c + pre;
//          } else {
//            break;
//          }
//        }
//        var h = val.substring(0, intIndex + 1); //temp  
//        var val2 = parseInt(pre, 10) + parseInt(bil);
//        var len = (pre.length - String(val2).length);
//        if (String(val2).length < pre.length) {
//          for (var i = 0; i < len; i++) {
//            val2 = "0" + val2;
//          }
//        }
//        h = h + val2;
//        if (!isNaN(val2)) {
//          $('#idHakmilikSiriHingga' + id).val(h);
//        }
//      }
//    } else {
//      $('#idHakmilikSiriHingga' + id).val('');
//    }
//  }

//  function nonNumber(elmnt, inputTxt) {
//    var a = document.getElementById('bilHakmilik0');
//    if (isNaN(a.value)) {
//      elmnt.value = RemoveNonNumeric(inputTxt);
//      return;
//    }
//  }

//  function RemoveNonNumeric(strString) {
//    var strValidCharacters = "123456789";
//    var strReturn = "";
//    var strBuffer = "";
//    var intIndex = 0;
//    // Loop through the string
//    for (intIndex = 0; intIndex < strString.length; intIndex++) {
//      strBuffer = strString.substr(intIndex, 1);
//      // Is this a number
//      if (strValidCharacters.indexOf(strBuffer) > -1) {
//        strReturn += strBuffer;
//      }
//    }
//    return strReturn;
//  }
  function doSearch(f, g) {
//    alert(f);
//    alert(g);
    doBlockUI();
    var url = '${pageContext.request.contextPath}/daftar/tukar_ganti_hakmilik/cetakan?search&id=' + f + '&id2=' + g;
    f = document.form1;
    f.action = url;
    f.submit();
  }

  function doPrintViaApplet(docId) {
    alert(docId);
    var a = document.getElementById('applet');
    a.printDocument(docId.toString());
  }

  function doSaveCapaian(v) {
    var sbb = $('#sbb_cetakan_semula').val();
    if (sbb === '') {
      alert('Sila masukan Sebab');
      return;
    }
    var url = '${pageContext.request.contextPath}/daftar/tukar_ganti_hakmilik/cetakan?cetakSemula&sbb=' + sbb.trim() + '&id=' + v;
    $.ajax({
      type: "GET",
      url: url,
      success: function(data) {
        alert(v);
        if (data === '1') {
          doPrintViaApplet(v);
        }
      }
    });
  }

  function doViewReport(v) {
    var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
    window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
  }

  function selectAll(a) {
    // TICK ALL
    var size = '${fn:length(actionBean.senaraiDokumen)}';
    for (i = 0; i < size; i++) {
      var c = document.getElementById("checkbox" + i);
      if (c === null)
        break;
      c.checked = a.checked;
    }
  }

  function cetakBerkelompok() {
    // PRINT MANY
//    doBlockUI();
    var param = '';
    var sbb = $('#sbb_cetakan_semula').val();
    if (sbb === '') {
      alert('Sila masukan Sebab');
      return;
    }

    $('.cetakMany').each(function(index) {
      var a = $('#checkbox' + index).is(":checked");
      if (a) {
        param = param + '&idCetak=' + $('#checkbox' + index).val();
      }
    });

    if (param === '') {
      alert('Sila tik dokumen yang ingin dicetak terlebih dahulu.');
      $.unblockUI();
      return;
    }
//    alert(param);

    var url = '${pageContext.request.contextPath}/daftar/tukar_ganti_hakmilik/cetakan?cetakBerkelompok' + param + '&sbb=' + sbb.trim();
    $.ajax({
      type: "GET",
      url: url,
      success: function(data) {
//        alert(v);
        if (data === '1') {
          $('.cetakMany').each(function(index) {
            var a = $('#checkbox' + index).is(":checked");
            if (a) {
              param = param + '&idCetak=' + $('#checkbox' + index).val();
            }
            doPrintViaApplet($('#checkbox' + index).val());
          });
        }
      }
    });
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
  .tooltip {
    display:none;
    background:transparent url(${pageContext.request.contextPath}/pub/images/black_arrow.png);
    font-size:12px;
    height:70px;
    width:160px;
    padding:25px;
    color:#fff;
  } 
</style>
<div class="tukarGanti">
  <s:messages />
  <s:errors />
  <s:form beanclass="etanah.view.daftar.utiliti.CetakanTukarGantiHakmilikActionBean" name="form1">
    <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
    <fieldset class="aras1">
      <s:hidden name="kelompok" id="kelompok" />
      <legend>Utiliti Cetakan Dhde / Dhke</legend><br>
      <p>
        <label>Id Permohonan : </label>
        <s:text name="idMohon" id="idMohon" onblur="this.value=this.value.toUpperCase();" size="30"/> 
      </p>
      <p>
        <label>ID Hakmilik : </label>
        <s:text name="idHakmilik" id="idHakmilik" onblur="this.value=this.value.toUpperCase();" size="30"/>
      </p>
      <%--
      <p>
        <label>&nbsp;</label>
        <em>ATAU</em>
      </p>
      <p>
        <label>Jumlah Hakmilik : </label>
        <s:text name="bilHakmilik" id="bilHakmilik0" size="4" 
                onchange="appendAutoAll('0');" 
                onblur="javascript:nonNumber(this, this.value);"/>
      </p>
      <p>
        <label>Id Hakmilik Mula : </label>
        <s:text name="idHakmilikSiriDari[0]" id="idHakmilikSiriDari0"
                onblur="this.value=this.value.toUpperCase();"
                onchange="appendAuto(this.value, '0');" size="30"/>        
      </p>
      <p>
        <label>Id Hakmilik Akhir : </label>
        <s:text name="idHakmilikSiriHingga[0]" id="idHakmilikSiriHingga0" 
                onblur="this.value=this.value.toUpperCase();"
                onchange="appendAuto(this.value, '0');" size="30"/>
      </p>
      --%>
      <br>
      <p>
        <label>&nbsp;</label>
        <s:submit name="searchPartial" value="Cari" class="btn" id="search"/>&nbsp;
        <s:button name="" value="Isi Semula" class="btn" id="isiSemula"/>
      </p>
      <br>
    </fieldset>
    <br>
    <c:if test="${fn:length(actionBean.senaraiPermohonan) > 0}">
      <div class="subtitle">
        <fieldset class="aras1">
          <legend>Senarai Perserahan</legend>
          <p align="center">
            <label></label>
            <c:set var="row_num" value="${actionBean.__pg_start}"/>
            <display:table class="tablecloth" name="${actionBean.senaraiPermohonan}"
                           cellpadding="0" cellspacing="0" id="line" pagesize="10" style="width:95%;" 
                           requestURI="/daftar/tukar_ganti_hakmilik/cetakan"                                                            
                           sort="external" size="${actionBean.__pg_total_records}" partialList="true">
              <c:set var="row_num" value="${row_num+1}"/>
              <display:column title="Bil" style="width:1%;"><div align="right">${row_num}</div></display:column>
            <display:column title="ID Perserahan">
              <a href="#" onclick="doSearch('${line.permohonan.idPermohonan}', '${line.hakmilik.idHakmilik}');">
                ${line.permohonan.idPermohonan}
              </a>
            </display:column>
            <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
            <display:column property="permohonan.kodUrusan.kod" title="Kod Urusan"/>
            <display:column property="permohonan.kodUrusan.nama" title="Urusan"/>
            <display:column title="Tarikh Perserahan">
              <fmt:formatDate value="${line.permohonan.infoAudit.tarikhMasuk}" pattern="d/MM/yyyy hh:mm:ss aa"/>
            </display:column>
          </display:table>
          </p>
          <br/>                
        </fieldset>
      </div>
    </c:if>

    <c:if test="${fn:length(actionBean.senaraiDokumen) > 0}">        
      <div class="subtitle">
        <fieldset class="aras1">
          <legend>Keputusan Carian Dokumen</legend>
          <p class="instr">
            Sila klik pada <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png" height="30" width="30" alt="papar" />
            untuk cetak dokumen.
          </p>
          <p>
          <div align="center">
            <display:table class="tablecloth" name="${actionBean.senaraiDokumen}" style="width:95%;"
                           cellpadding="0" cellspacing="0" id="line1">
              <display:column title="Bil">${line1_rowNum}</display:column>
              <display:column property="kodDokumen.kod" title="Kod Dokumen"/>
              <display:column property="kodDokumen.nama" title="Nama Dokumen"/>
              <display:column title="Tajuk">${line1.tajuk}</display:column>
              <display:column title="Cetak">                            
                <c:if test="${line1.namaFizikal != null}">
                  <p align="center">
                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png" title="Cetak ${line1.tajuk}"
                         onclick="doSaveCapaian('${line1.idDokumen}');" height="20" width="20" alt="cetak" onmouseover="this.style.cursor = 'pointer';"/>
                  </p>
                </c:if>
              </display:column>
              <display:column title="<input type='checkbox' name='semua' onclick='javascript:selectAll(this);'>" style="width:1%;">
                <s:checkbox name="checkbox" id="checkbox${line1_rowNum-1}" value="${line1.idDokumen}" class="cetakMany"/>
              </display:column>
            </display:table>
          </div>
          </p>
          <br/>
          <p>
            <label>Sebab cetakan semula :</label>
            <s:textarea name="sbb_cetakan_semula" id="sbb_cetakan_semula" cols="60" rows="10" onblur="toUppercase(this.id);"/>
          </p>
          <br/>
          <p align="center">          
            <s:button class="btn" value="Cetak Berkelompok" name="" onclick="cetakBerkelompok();"/>&nbsp;         
          </p>
          <br/>
        </fieldset>
      </div>
      <applet code="etanah.dokumen.print.DocumentPrinter" 
              ARCHIVE="${pageContext.request.contextPath}/PrintApplet.jar,
              ${pageContext.request.contextPath}/commons-httpclient-2.0.2.jar,
              ${pageContext.request.contextPath}/commons-logging.jar,
              ${pageContext.request.contextPath}/swingx-1.6.jar,
              ${pageContext.request.contextPath}/log4j-1.2.12.jar,
              ${pageContext.request.contextPath}/jpedal_trial.jar,
              ${pageContext.request.contextPath}/PDFRenderer.jar"
              codebase = "${pageContext.request.contextPath}/"
              name     = "applet"
              id       = "applet"
              width    = "1"
              height   = "1"
              align    = "middle">
        <param name="uploadAction" value="<%=request.getContextPath()%>/PrintServlet"/>
        <param name ="method" value="pdfp"/>
        <%
          Cookie[] cookies = request.getCookies();
          StringBuffer sb = new StringBuffer();
          for (int i = 0; i < cookies.length; i++) {
            sb.setLength(0);
            sb.append(cookies[i].getName());
            sb.append("=");
            sb.append(cookies[i].getValue());
        %>
        <param name="Cookie<%= i%>" value="<%= sb.toString()%>"><%
          }
        %>
      </applet>
    </c:if>

    <%--
    <c:if test="${fn:length(actionBean.senaraiCetakan) > 0}">
      <fieldset class="aras1">
        <legend>Keputusan Carian Dokumen</legend>
        <p class="instr">    
          Sila klik pada <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar" />
          untuk semak dokumen.
          Sila klik pada <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png" height="30" width="30" alt="papar" />
          untuk cetak dokumen.
        </p>
        <br>
        <div align="center">
          <display:table class="tablecloth" name="${actionBean.senaraiCetakan}" cellpadding="0" cellspacing="0" id="line" style="width:95%;">
            <display:column title="Bil" style="width:1%;"><div align="right">${line_rowNum}</div></display:column>
            <display:column property="idHakmilik" title="Id Hakmilik" style="width:17%;"/>    
            <display:column title="Kod Dokumen" style="width:5%;"><div align="center">${line.kodDokumen}</div></display:column>              
            <display:column property="namaDokumen" title="Nama Dokumen"/>
            <display:column title="Tajuk">
              ${line.tajuk}
            </display:column>
            <display:column title="Papar" style="width:5%;">
              <div align="center">
                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" 
                     title="Papar ${line.kodDokumen}-${line.idHakmilik}" height="20" width="20" alt="papar" 
                     onclick="doViewReport('${line.idDokumen}');" 
                     onmouseover="this.style.cursor = 'pointer';"/>
              </div>
            </display:column>
            <display:column title="Cetak" style="width:5%;">
              <c:if test="${line.namaFizikal != null}">
                <div align="center">
                  <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png" 
                       title="Cetak ${line.kodDokumen}-${line.idHakmilik}" height="20" width="20" alt="cetak" 
                       onclick="doSaveCapaian('${line.idDokumen}');" 
                       onmouseover="this.style.cursor = 'pointer';"/>
                </div>
              </c:if>
            </display:column>
          </display:table>
        </div>
        <br>
        <p>
          <label>Sebab cetakan semula :</label>
          <s:textarea name="sbb_cetakan_semula" id="sbb_cetakan_semula" cols="60" rows="10" onblur="toUppercase(this.id);"/>
        </p>
        <br>
      </fieldset>

      <applet code="etanah.dokumen.print.DocumentPrinter" 
              ARCHIVE="${pageContext.request.contextPath}/PrintApplet.jar,
              ${pageContext.request.contextPath}/commons-httpclient-2.0.2.jar,
              ${pageContext.request.contextPath}/commons-logging.jar,
              ${pageContext.request.contextPath}/swingx-1.6.jar,
              ${pageContext.request.contextPath}/log4j-1.2.12.jar,
              ${pageContext.request.contextPath}/jpedal_trial.jar,
              ${pageContext.request.contextPath}/PDFRenderer.jar"
              codebase = "${pageContext.request.contextPath}/"
              name     = "applet"
              id       = "applet"
              width    = "1"
              height   = "1"
              align    = "middle">
        <param name="uploadAction" value="<%=request.getContextPath()%>/PrintServlet"/>
        <param name ="method" value="pdfp"/>
        <%
          Cookie[] cookies = request.getCookies();
          StringBuffer sb = new StringBuffer();
          for (int i = 0; i < cookies.length; i++) {
            sb.setLength(0);
            sb.append(cookies[i].getName());
            sb.append("=");
            sb.append(cookies[i].getValue());
        %>
        <param name="Cookie<%= i%>" value="<%= sb.toString()%>"><%
          }
        %>
      </applet>
    </c:if>
    --%>
  </s:form>
</div>
