<%--
    Document   : edit_pemohon
    Created on : Nov 10, 2009, 4:23:38 PM
    Author     : muhammad.amin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ageCalculator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/formatCurrency.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

  $(document).ready(function() {

    var val = $('#gajiPemohon').val();
    if (val != '') {
      convert(val, 'gajiPemohon');
    }

    var val2 = $('#gajiPenerima').val();
    if (val2 != '') {
      convert(val2, 'gajiPenerima');
    }

    var v = '${actionBean.pihak.jenisPengenalan.kod}';
    if (v == "B") {
      var icNo = '${actionBean.pihak.noPengenalan}';
      var age = calculateAge(icNo.substring(4, 6), icNo.substring(2, 4), 19 + icNo.substring(0, 2));
      $('#umur').val(age);
    }
  });

  function convert(val, id) {
    var amaun = CurrencyFormatted(val);
    amaun = CommaFormatted(amaun);
    $('#' + id).val(amaun);
  }

  function save(event, f) {

    $.blockUI({
      message: $('#displayBox'),
      css: {
        top: ($(window).height() - 50) / 2 + 'px',
        left: ($(window).width() - 50) / 2 + 'px',
        width: '50px'
      }
    });

    var q = $(f).formSerialize();
    var url = f.action + '?' + event;
    $.post(url, q,
            function(data) {
              $('#page_div', opener.document).html(data);
              $.unblockUI();
              self.close();
            }, 'html');
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

</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form beanclass="etanah.view.daftar.PihakKepentinganAction">

  <div class="subtitle">
    <fieldset class="aras1">
      <s:hidden name="pihak.idPihak" />
      <s:hidden name="idHakmilik"/>
 
      <legend>Kemaskini Maklumat Pihak</legend>
      <p>
        <label>Nama :</label>${actionBean.pemohon.pihak.nama} <s:hidden name="pemohon.idPemohon"/>&nbsp; 
        <s:hidden name="pihak.nama"/>
      </p>
      <p>
        <label>Jenis Pengenalan :</label>
        ${actionBean.pemohon.jenisPengenalan.nama}&nbsp;
        <s:hidden name="pihak.jenisPengenalan.nama"/>
      </p>
      <p>
        <label>Nombor Pengenalan :</label>
        ${actionBean.pemohon.noPengenalan}&nbsp;
        <s:hidden name="pihak.noPengenalan"/>
      </p>
      <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B'
                    || actionBean.pihak.jenisPengenalan.kod eq 'L'
                    || actionBean.pihak.jenisPengenalan.kod eq 'P'
                    || actionBean.pihak.jenisPengenalan.kod eq 'T'
                    || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
            <p>
              <label>Umur :</label>
              <s:text name="pemohon.umur"  id="umur" size="10" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
              <label>Pekerjaan :</label>
              <s:text name="pemohon.pekerjaan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
              <label>Pendapatan Bulanan (RM) :</label>
              <s:text name="pemohon.pendapatan" size="40" onkeyup="validateNumber(this,this.value);" id="gajiPemohon"
                      onchange="convert(this.value, this.id);" formatPattern="#,###.00"/>
            </p>
            <p>
              <label>Status Perkahwinan :</label>
              <s:select name="pemohon.statusKahwin" style="width:255px">
                <s:option value="">Sila Pilih</s:option>
                <s:option>Berkahwin</s:option>
                <s:option>Bujang</s:option>
                <s:option>Duda</s:option>
                <s:option>Ibu Tunggal</s:option>
              </s:select>
            </p>
            <p>
              <label>Tanggungan :</label>
              <s:text name="pemohon.tanggungan" size="10" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
            </p>
      </c:if>
      <p>
        <label>Jenis Syarikat / Bangsa :</label>
        <s:select name="pihak.bangsa.kod" id="bangsa">
          <s:option value="">Sila Pilih</s:option>
          <s:options-collection collection="${listUtil.senaraiKodBangsa}" label="nama" value="kod"/>
        </s:select>
      </p>
      <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'D' || actionBean.pihak.jenisPengenalan.kod eq 'S'
                    || actionBean.pihak.jenisPengenalan.kod eq 'U'|| actionBean.pihak.jenisPengenalan.kod eq 'N' }">
            <p class="syarikat">
              <label>Daftar Tubuh :</label>
              <s:select name="pihak.penubuhanSyarikat.kod" id="daftarTubuh">
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${listUtil.senaraiKodPenubuhanSyarikat}" label="nama" value="kod"/>
              </s:select>
            </p>
      </c:if>
      <p>
        <label>Hubungan :</label>
        <s:select name="pemohon.kaitan" style="width:255px">
          <s:option value="">Sila Pilih</s:option>
          <s:option value="IBU BAPA KEPADA ANAK">IBU BAPA KEPADA ANAK</s:option>
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
          <s:options-collection collection="${listUtil.senaraiWarganegara}" label="nama" value="kod"/>
        </s:select>
      </p>
      <p>
        <label for="alamat">Alamat Berdaftar :</label>
        <s:text name="pemohon.alamat.alamat1" id="alamat1" disabled="${disabled}" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
        <s:hidden name="pihak.alamat1"/>
      </p>
      <p>
        <label> &nbsp;</label>
        <s:text name="pemohon.alamat.alamat2" id="alamat2" disabled="${disabled}" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
        <s:hidden name="pihak.alamat2"/>
      </p>
      <p>
        <label> &nbsp;</label>
        <s:text name="pemohon.alamat.alamat3" id="alamat3" disabled="${disabled}" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
        <s:hidden name="pihak.alamat3"/>
      </p>
      <p>
        <label>Bandar :</label>
        <s:text name="pemohon.alamat.alamat4" id="alamat4" disabled="${disabled}" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
        <s:hidden name="pihak.alamat4"/>
      </p>
      <p>
        <label>Poskod :</label>
        <s:text name="pemohon.alamat.poskod" id="poskod" disabled="${disabled}" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
        <s:hidden name="pihak.poskod"/>
      </p>

      <p>
        <label for="Negeri">Negeri :</label>
        <s:select name="pemohon.alamat.negeri.kod" id="negeri" disabled="${disabled}" style="width:255px">
          <s:option value="">Sila Pilih</s:option>
          <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
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
        <s:text name="pemohon.alamatSurat.alamatSurat1" id="suratAlamat1" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
      </p>
      <p>
        <label> &nbsp;</label>
        <s:text name="pemohon.alamatSurat.alamatSurat2" id="suratAlamat2" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
      </p>
      <p>
        <label> &nbsp;</label>
        <s:text name="pemohon.alamatSurat.alamatSurat3" id="suratAlamat3" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
      </p>
      <p>
        <label>Bandar :</label>
        <s:text name="pemohon.alamatSurat.alamatSurat4" id="suratAlamat4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
      </p>
      <p>
        <label>Poskod :</label>
        <s:text name="pemohon.alamatSurat.poskodSurat" id="suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
      </p>

      <p>
        <label for="Negeri">Negeri :</label>
        <s:select name="pemohon.alamatSurat.negeriSurat.kod" id="suratNegeri" style="width:255px">
          <s:option value="">Sila Pilih</s:option>
          <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
        </s:select>
      </p> 
      <p>
        <label>&nbsp;</label>
        <s:button name="simpanKemaskiniPemohon" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>                
        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
      </p>

    </fieldset >
  </div>

</s:form>