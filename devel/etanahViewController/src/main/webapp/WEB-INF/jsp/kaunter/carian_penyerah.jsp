
<%-- 
    Document   : carian_penyerah
    Created on : Apr 8, 2010, 11:04:34 AM
    Author     : fikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
  $(document).ready(function() {
    maximizeWindow();

  });

  //function before edit

//    function deletePenyerah(f){   
//        if($('#penyerah').is(':checked')){
//            var kodPenyerah = $('#penyerahKod').val();
//            var idPenyerah = $('input:radio[name=pilihPenyerah]:checked').val();
//            f.action = f.action + '?hapus&idPenyerah=' + idPenyerah + '&kodPenyerah=' +kodPenyerah;
//            f.submit();
//        }else{
//            alert('Sila pilih penyerah untuk tidak aktifkan');
//        }   
//    }


  //function edited, check radio selected value
  function deletePenyerah(f) {

    var x = document.getElementsByTagName("input");
    var radChecked = false;

    for (i = 0; i < x.length; i++) {
      if (x[i].name == "pilihPenyerah" && x[i].checked)
      {
        radChecked = true;
        var kodPenyerah = $('#penyerahKod').val();
        var idPenyerah = $('input:radio[name=pilihPenyerah]:checked').val();
        f.action = f.action + '?hapus&idPenyerah=' + idPenyerah + '&kodPenyerah=' + kodPenyerah;
        f.submit();
      }


      if (radChecked == false) {
        if (x[i].name == "hapus")
        {
          alert('Sila pilih penyerah untuk tidak aktifkan');
          break;
        }
      }

    }
  }

  //function before edit

//    function aktifPenyerah(f){
//        if($('#penyerah').is(':checked')){
//            var kodPenyerah = $('#penyerahKod').val();
//            var idPenyerah = $('input:radio[name=pilihPenyerah]:checked').val();
//            f.action = f.action + '?aktif&idPenyerah=' + idPenyerah + '&kodPenyerah=' +kodPenyerah;
//            f.submit();
//        }else{
//            alert('Sila pilih penyerah untuk diaktifkan');
//        }
//            
//    }

  //function edited, check radio selected value
  function aktifPenyerah(f) {
    var x = document.getElementsByTagName("input");
    var radChecked = false;

    for (i = 0; i < x.length; i++) {
      if (x[i].name == "pilihPenyerah" && x[i].checked)
      {
        radChecked = true;
        var kodPenyerah = $('#penyerahKod').val();
        var idPenyerah = $('input:radio[name=pilihPenyerah]:checked').val();
        f.action = f.action + '?aktif&idPenyerah=' + idPenyerah + '&kodPenyerah=' + kodPenyerah;
        f.submit();
      }

      if (radChecked == false) {
        if (x[i].name == "aktif")
        {
          alert('Sila pilih penyerah untuk diaktifkan');
          break;
        }
      }

    }
  }

  function tambahPenyerah(f) {

    if ($('#penyerahNama').val() == "") {
      alert('Sila Masukkan Nama Penyerah');
    } else if ($('#idPenyerah').val() == "") {
      alert('Sila Masukkan Kod Penyerah');
    } else if ($('#alamatPenyerah1').val() == "") {
      alert('Sila Masukkan Alamat Penyerah');
    } else if ($('#penyerahPoskod').val() == "") {
      alert('Sila Masukkan Poskod Penyerah');
    }
    else if ($('#penyerahNegeri').val() == "") {
      alert('Sila Masukkan Negeri Penyerah');
    }
    else {
      var kodPenyerah = $('#penyerahKod').val();
      var idPenyerah = $('#idPenyerah').val();
      f.action = f.action + '?tambah&idPenyerah=' + idPenyerah + '&kodPenyerah=' + kodPenyerah;
      f.submit();
    }
  }

  var DELIM = "__^$__";

  function cariPenyerah(e, f) {
    var k = $('#penyerahKod').val();
    if (k == '0') {
      alert('Sila Pilih Penyerah');
      $('#penyerahKod').focus();
      return;
    }
    if ($('#penyerahKod').val() == '03') {
      alert('Table Tiada');
      return;
    }
  <%--var i = $('#namaPenyerah').val();
  var l = $('#idPenyerah').val();
  if(i == '' && l == ''){
      alert('Sila Isi nama Penyerah atau Id Penyerah');
      //$('#penyerahKod').focus();
      return;
  }--%>
      f.action = f.action + '?' + e;
      f.submit();
    }

    function cariPenyerahPopup(e, f) {

      var k = $('#penyerahKod').val();
      if (k == '0') {
        alert('Sila Pilih Penyerah');
        $('#penyerahKod').focus();
        return;
      }
      if ($('#penyerahKod').val() == '03') {
        alert('Maklumat Tiada.');
        return;
      }
      var i = $('#namaPenyerah').val();
      var l = $('#idPenyerah').val();
      if (i == '' && l == '') {
        alert('Sila Isi nama Penyerah atau Id Penyerah');
        //$('#penyerahKod').focus();
        return;
      }

      f.action = f.action + '?searchPenyerahPopup';
      doBlockUI();
      f.submit();
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

    function pilihPenyerah() {
      var jenis = $('#penyerahKod').val();
      var url;
      $('.penyerah').each(function() {
        if ($(this).is(':checked')) {
          var va = $(this).val();

          if (jenis == '0') {
            alert('Sila pilih Jenis Penyerah');
            return;
          } else
          if (jenis == '01') { // PEGUAM
            url = "${pageContext.request.contextPath}/common/req_peguam_info?idPeguam=" + va;
          } else if (jenis == '02') { // JUBL
            url = "${pageContext.request.contextPath}/common/req_jubl_info?idJUBL=" + va;
          } else if (jenis == '00') {
            url = "${pageContext.request.contextPath}/common/req_unit_info?idUnit=" + va;
          } else if (jenis == '05') {
            url = "${pageContext.request.contextPath}/common/req_agensi_info?idAgensi=" + va;
          } else if (jenis == '06') { //Jabatan Kerajaan
            url = "${pageContext.request.contextPath}/common/req_agensi_info?idAgensi=" + va;
          } else if (jenis == '07') { //Badan Berkanun
            url = "${pageContext.request.contextPath}/common/req_agensi_info?idAgensi=" + va;
          }
          else if (jenis == '04') { //Jurulelong Berlesen
            url = "${pageContext.request.contextPath}/common/req_lelong_info?idAgensi=" + va;
          }

          $.get(url,
                  function(data) {
                    if (data == null || data.length == 0) {
                      alert("Maklumat tidak dijumpai");
                      return;
                    }
                    var p = data.split(DELIM);
                    $('#penyerahKod', window.opener.document).val(jenis);
                    $('#idPenyerah', window.opener.document).val(va);
                    $('#penyerahJenisPengenalan', window.opener.document).val(p[0]);
                    $('#penyerahNoPengenalan', opener.document).val(p[1]);
                    $("#penyerahNama", opener.document).val(p[2]);
                    $("#penyerahAlamat1", opener.document).val(p[3]);
                    $("#penyerahAlamat2", opener.document).val(p[4]);
                    $("#penyerahAlamat3", opener.document).val(p[5]);
                    $("#penyerahAlamat4", opener.document).val(p[6]);
                    $("#penyerahPoskod", opener.document).val(p[7]);
                    $("#penyerahNegeri", opener.document).val(p[8]);
                    $("#penyerahNoTelefon", opener.document).val(p[9]);
                    self.close();
                  });
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

    function doUpperCase(id) {
      var val = $('#' + id).val().toUpperCase();
      $('#' + id).val(val);
    }





</script> 
<img alt="" id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.kaunter.RequestPenyerahInfoForm">
  <s:messages/>
  <div class="subtitle">
    <fieldset class="aras1">
      <legend>
        Carian Penyerah
      </legend>
      <p>
        <label>Jenis Penyerah :</label>
        <c:choose>
          <c:when test="${!empty SSHMA}">
            <s:select name="jenisPenyerah" id="penyerahKod" onchange="javascript:updateKodPenyerahInputs();">
              <s:option value="06">Jabatan Kerajaan</s:option>
              <s:option value="07">Badan Berkanun</s:option>
              <s:option value="00">Unit Dalaman</s:option>
            </s:select>
          </c:when>
          <c:otherwise>
            <s:select name="jenisPenyerah" id="penyerahKod" onchange="">
              <s:option value="0">Pilih Jenis ...</s:option>
              <s:options-collection collection="${listUtil.senaraiKodPenyerah}" label="nama" value="kod"/>
            </s:select>
          </c:otherwise>
        </c:choose>    
      </p>
      <p>
        <label>Nama Penyerah :</label>
        <s:text name="namaPenyerah" size="20" id="namaPenyerah" /><em>Atau</em>
      </p>
      <p>
        <label>Id Penyerah :</label>
        <s:text name="idPenyerah" size="20" id="idPenyerah" maxlength="7"/>
      </p>
      <p>
        <label>&nbsp;</label>
        <c:if test="${empty popup}">
          <s:button name="searchPenyerah" value="Cari" class="btn" onclick="cariPenyerah(this.name, this.form);"/>
        </c:if>
        <c:if test="${!empty popup}">
          <s:button name="searchPenyerah" value="Cari" class="btn" onclick="cariPenyerahPopup(this.name, this.form);"/>
          <s:button name="" value="Tutup" class="btn" onclick="self.close();"/>
        </c:if>
      </p>
    </fieldset>
  </div>
  <br/>
  <div class="subtitle">
    <fieldset class="aras1">
      <legend>
        Keputusan Carian Penyerah
      </legend>
      <%--${actionBean.isPopup}--%>

      <div class="content" align="center">
        <c:if test="${!empty popup}">
          <c:set var="url" value="searchPenyerahPopup"/>
        </c:if>
        <c:if test="${empty popup}">
          <c:set var="url" value="searchPenyerah"/>
        </c:if>
        <display:table class="tablecloth" name="${actionBean.senaraiPenyerah}"
                       cellpadding="0" cellspacing="0" id="line" pagesize="10"
                       requestURI="${pageContext.request.contextPath}/common/req_penyerah_info?${url}" excludedParams="${url}">
          <!-- Filter by status (active or not)-->
          <c:if test="${!empty popup}">
            <display:column>
              <c:if test="${line.aktif eq 'Y'}">
                <s:radio name="__pilihPenyerah" class="penyerah" id="pilihPenyerah${line_rowNum}" value="${line.idPenyerah}"/>
              </c:if>
              <c:if test="${line.aktif eq 'T'}">
                <s:radio name="__pilihPenyerah" class="penyerah" id="pilihPenyerah${line_rowNum}" value="${line.idPenyerah}" disabled="true"/>
              </c:if>
            </display:column>
          </c:if>
          <c:if test="${empty popup}">
            <display:column>          

              <%-- before edit--%>
              <%-- <s:radio name="pilihPenyerah" class="penyerah" id="penyerah" value="${line.idPenyerah}"/>--%>

              <%-- edited onclick event to set text--%>
              <s:radio name="pilihPenyerah" class="penyerah" onclick="document.getElementById('idPenyerah').value=this.value;" id="penyerah" value="${line.idPenyerah}"/>
            </display:column>
          </c:if>    
          <display:column property="idPenyerah" title="ID Penyerah"/>
          <display:column property="nama" title="Nama Penyerah"/>
          <display:column title="Alamat">
            ${line.alamat1}
            ${line.alamat2}
            ${line.alamat3}
            ${line.alamat4}
            ${line.poskod}
            ${line.negeri.nama}
          </display:column>
          <display:column title="No Telefon" property="noTelefon1"/>
          <display:column title="Status">
            <c:if test="${line.aktif eq 'Y'}">
              Aktif
            </c:if>
            <c:if test="${line.aktif eq 'T'}">
              Tidak Aktif
            </c:if>
          </display:column>
        </display:table>
      </div>
      <br/>
      <c:if test="${!empty popup}">
        <c:if test="${fn:length(actionBean.senaraiPenyerah)>0}">
          <p>
            <label>&nbsp;</label><s:button name="_pilih" value="Pilih" class="btn" onclick="pilihPenyerah();"/>
          </p>
        </c:if>
      </c:if>
      <c:if test="${empty popup}">
        <c:if test="${fn:length(actionBean.senaraiPenyerah)>0 && (actionBean.pengguna.perananUtama.kod eq '4' || actionBean.pengguna.perananUtama.kod eq '6')}">
          <p>
            <label>&nbsp;</label>
            <s:button name="hapus" value="Tak Aktif" id="hapus" class="longbtn" onclick="deletePenyerah(this.form);"/>
            <s:button name="aktif" value="Aktif" id="aktif" class="longbtn" onclick="aktifPenyerah(this.form);"/>
          </p>
        </c:if>
      </c:if>

      <c:if test="${empty popup}">

        <c:if test="${actionBean.form}">
          <c:if test="${fn:length(actionBean.senaraiPenyerah)<1}">
            <p>
              <label>Nama : </label>
              <s:text name="penyerahNama" id="penyerahNama" size="42" onblur="doUpperCase(this.id)"/><em>*</em>
            </p>
            <c:if test="${actionBean.jenisPenyerah eq '05' || actionBean.jenisPenyerah eq '06' || actionBean.jenisPenyerah eq '07'}">
              <p>
                <label>Kementerian : </label>
                <s:select name="kodKementerian" id="kodKementerian" >
                  <s:option value="0">Pilih ...</s:option>
                  <s:options-collection collection="${listUtil.senaraiKodKementerian}" label="nama" value="kod" />
                </s:select><em>*</em>
              </p>              
            </c:if>
            <p>
              <label>Alamat : </label>
              <s:text name="penyerahAlamat1" id="penyerahAlamat1" size="30" onblur="doUpperCase(this.id)"/><em>*</em>
            </p>

            <p>
              <label>&nbsp;</label>
              <s:text name="penyerahAlamat2" id="penyerahAlamat2" size="30" onblur="doUpperCase(this.id)"/>
            </p>

            <p>
              <label>&nbsp;</label>
              <s:text name="penyerahAlamat3" id="penyerahAlamat3" size="30" onblur="doUpperCase(this.id)"/>
            </p>

            <p>
              <label>Bandar : </label>
              <s:text name="penyerahAlamat4" id="penyerahAlamat4" size="30" onblur="doUpperCase(this.id)"/>
            </p>

            <p>
              <label>Poskod : </label>
              <s:text name="penyerahPoskod" maxlength="5" id="penyerahPoskod" size="17" onkeyup="validateNumber(this,this.value);" /><em>*</em>
            </p>

            <p>
              <label>Negeri : </label>
              <s:select name="penyerahNegeri.kod" id="penyerahNegeri" >
                <s:option value="0">Pilih ...</s:option>
                <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
              </s:select><em>*</em>
            </p>
            <p>
              <label>No.Telefon : </label>
              <s:text name="penyerahNoTelefon" id="penyerahNoTelefon" size="15"/>
            </p>
            <p>
              <label>&nbsp;</label>
              <s:button name="tambah" value="Tambah" id="tambah" class="btn" onclick="tambahPenyerah(this.form);"/>
            </p>
          </c:if>
        </c:if>
      </c:if>

    </fieldset>
  </div>

</s:form>
