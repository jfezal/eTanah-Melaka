<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"    prefix="c" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<c:set var="action" value="/pembangunan/terima_bayaran"/>
<c:set var="nextStep" value="Step7"/>
<c:set var="prevStep" value="Step5"/>
<c:set var="word" value="Langkah 5"/>
<c:set var="tmbUrusan" value="Step6a"/>
<c:set var="tmbUrusanVal"/>
<c:if test="${!empty carian}">
  <c:set var="action" value="/daftar/carian"/>
  <c:set var="prevStep" value="Step2"/>
  <c:set var="nextStep" value="Step4"/>
  <c:set var="word" value="Langkah 3"/>
  <c:set var="tmbUrusan" value="Step1"/>
  <c:set var="tmbUrusanVal" value="Y"/>
</c:if>
<%--Added by Aizuddin for SSHMA--%>
<c:if test="${!empty SSHMA}">
  <c:set var="action" value="/daftar/carian_tanpa_bayaran"/>
  <c:set var="prevStep" value="Step2"/>
  <c:set var="nextStep" value="Step3"/>
  <c:set var="word" value="Langkah 3"/>
  <c:set var="tmbUrusan" value="Step1"/>
  <c:set var="tmbUrusanVal" value="Y"/>
</c:if>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script>

  var DELIM = "__^$__";

  function populatePenyerah(btn) {
    var url;
    if (btn.id == "carianPenyerah") {
      $('#kod').val('1');
      var idx = $("#idPenyerah").val();
      var jenis = $('#penyerahKod').val();
      var SSHMA = $('#SSHMA').val();
      if (idx == null || idx.length == 0) {
        //alert("Sila masukkan ID Penyerah.");
        if (SSHMA) {
          window.open("${pageContext.request.contextPath}/common/req_penyerah_info?showForm&SSHMA=true&popup=true&jenisPenyerah=" + jenis, "eTanah",
                  "status=0,toolbar=0,location=0,menubar=0,width=900,height=1024");
        } else {
          window.open("${pageContext.request.contextPath}/common/req_penyerah_info?showForm&popup=true&jenisPenyerah=" + jenis, "eTanah",
                  "status=0,toolbar=0,location=0,menubar=0,width=900,height=1024");
        }
        return;
      }
      if (jenis == '0') {
        alert('Sila pilih Jenis Penyerah');
        return;
      } else if (jenis == '01') { // PEGUAM
        url = "${pageContext.request.contextPath}/common/req_peguam_info?idPeguam=" + idx;
      } else if (jenis == '02') { // JUBL
        url = "${pageContext.request.contextPath}/common/req_jubl_info?idJUBL=" + idx;
      } else if (jenis == '00') { //Unit dalaman
        url = "${pageContext.request.contextPath}/common/req_unit_info?idUnit=" + idx;
      } else if (jenis == '05') { //Perbadanan Pengurusan
        url = "${pageContext.request.contextPath}/common/req_agensi_info?idAgensi=" + idx;
      } else if (jenis == '06') { //Jabatan Kerajaan
        url = "${pageContext.request.contextPath}/common/req_agensi_info?idAgensi=" + idx;
      } else if (jenis == '07') { //Badan Berkanun
        url = "${pageContext.request.contextPath}/common/req_agensi_info?idAgensi=" + idx;
      } else if (jenis == '03') { //Jururancang Berlesen
        url = "${pageContext.request.contextPath}/common/req_agensi_info?idAgensi=" + idx;
      } else if (jenis == '04') { //Jurulelong Berlesen
        url = "${pageContext.request.contextPath}/common/req_lelong_info?idAgensi=" + idx;
      }
    } else if (btn.id == "carianPihak") {
      $('#kod').val('2');
      var jP = $("#penyerahJenisPengenalan").val();
      var noP = $("#penyerahNoPengenalan").val();
      if (jP == null || jP.length == 0 || noP == null || noP.length == 0) {
        alert("Sila masukkan Jenis Pengenalan/No.Pengenalan.");
        return;
      }
      url = "${pageContext.request.contextPath}/common/req_pihak_info?jenisPengenalan=" + jP
              + "&noPengenalan=" + noP;
    }

    $.get(url,
            function(data) {
              if (data == null || data.length == 0) {
                alert("Maklumat tidak dijumpai");
                return;
              }
              var p = data.split(DELIM);
              $('#penyerahJenisPengenalan').val(p[0]);
              $('#penyerahNoPengenalan').val(p[1]);
              $("#penyerahNama").val(p[2]);
              $("#penyerahAlamat1").val(p[3]);
              $("#penyerahAlamat2").val(p[4]);
              $("#penyerahAlamat3").val(p[5]);
              $("#penyerahAlamat4").val(p[6]);
              $("#penyerahPoskod").val(p[7]);
              $("#penyerahNegeri").val(p[8]);
              $("#penyerahNoTelefon").val(p[9]);
              $("#penyerahEmail").val(p[10]);
            });
  }


  function dodacheck(val) {
    //var mikExp = /[1\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\1\|]/;
    var v = $('#penyerahJenisPengenalan').val();
    if (v == 'B') {
      var strPass = val;
      var strLength = strPass.length;
      if (strLength > 12) {
        var tst = val.substring(0, (strLength) - 1);
        $('#penyerahNoPengenalan').val(tst);
      }
      var lchar = val.charAt((strLength) - 1);
      if (isNaN(lchar)) {
        //return false;
        var tst = val.substring(0, (strLength) - 1);
        $('#penyerahNoPengenalan').val(tst);
      }
    }
  }

  function clearNoPengenalan() {
    $('#penyerahNoPengenalan').val('');
  }

  function test(f) {
    $(f).clearForm();
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

  function updateKodPenyerahInputs() {
    //alert($("#penyerahKod").val());
    if ($("#penyerahKod").val() == '0') {
      // disable button for kod penyerah
      $("#idPenyerah").attr("disabled", "disable");
      $("#carianPenyerah").attr("disabled", "disabled");
      // enable carian pihak
      $("#penyerahJenisPengenalan").attr("disabled", "");
      $("#penyerahJenisPengenalan").focus();
      $("#penyerahNoPengenalan").attr("disabled", "");
      $("#carianPihak").attr("disabled", "");
    } else {
      // disable button for kod penyerah
      $("#idPenyerah").attr("disabled", "");
      $("#idPenyerah").focus();
      $("#carianPenyerah").attr("disabled", "");
      // enable carian pihak
      $("#penyerahJenisPengenalan").attr("disabled", "disabled");
      $("#penyerahNoPengenalan").attr("disabled", "disabled");
      $("#carianPihak").attr("disabled", "disabled");
      // code to clear data
      $('#penyerahJenisPengenalan').val("0");
      $('#penyerahNoPengenalan').val("");
      $("#penyerahNama").val("");
      $("#penyerahAlamat1").val("");
      $("#penyerahAlamat2").val("");
      $("#penyerahAlamat3").val("");
      $("#penyerahAlamat4").val("");
      $("#penyerahPoskod").val("");
      $("#penyerahNegeri").val("0");
      $("#penyerahNoTelefon").val("");
      $("#penyerahEmail").val("");
    }
  }
  
  function validatePenyerah() {
      var success = true;
      $('.required').each(function() {
          var val = $(this).val();
          if (val === ''){
              success = false;              
              return;
          }
      });
      if (!success) alert('Sila isi semua yang bertanda *.');
      return success;
  }


</script>

<p class=title>Terima Bayaran: Maklumat Penyerah/Pembayar</p>

<s:messages />
<s:errors />

<span class=instr>Masukkan maklumat penyerah Permohonan/Perserahan. Jika Penyerah adalah Peguam yang berdaftar,
  masukkan Kod Peguam untuk mencari maklumat dari Rekod. Medan bertanda <em>*</em> adalah mandatori.</span><br/>

<s:form action="${action}" id="penyerah">
  <s:hidden name="kod" id="kod"/>
  <s:hidden name="SSHMA" id="SSHMA" value="${SSHMA}"/>
  <s:hidden name="tmbhUrusanVal" value="${tmbUrusanVal}"/>
  <c:if test="${bilMohon ne 0}">    
    <!-- if you found error here, Please fix this code! -->
    <s:hidden name="bilMohon" value="${bilMohon}"/> 
  </c:if>
  <fieldset class="aras1">
    <legend>Maklumat Penyerah </legend>

    <p>
      <label>Carian Penyerah: </label>
      <c:if test="${empty SSHMA}">       
          <c:if test="${actionBean.kodNeg eq 'melaka'}">
              <s:select name="penyerahKod.kod" id="penyerahKod" onchange="javascript:updateKodPenyerahInputs();">
                  <s:option value="0">INDIVIDU / SYARIKAT</s:option>
                  <s:options-collection collection="${listUtil.senaraiKodPenyerah}" label="nama" value="kod"/>
              </s:select>
          </c:if>
          <c:if test="${actionBean.kodNeg eq 'n9'}">    
              <s:select name="penyerahKod.kod" id="penyerahKod" onchange="javascript:updateKodPenyerahInputs();">
                  <s:option value="0">INDIVIDU / SYARIKAT</s:option>
                  <s:options-collection collection="${listUtil.senaraiKodPenyerah}" label="nama" value="kod"/>
              </s:select>
          </c:if> 
      </c:if>
      <c:if test="${!empty SSHMA}">
        <s:select name="penyerahKod.kod" id="penyerahKod" onchange="javascript:updateKodPenyerahInputs();">
          <s:option value="06">Jabatan Kerajaan</s:option>
          <s:option value="07">Badan Berkanun</s:option>
          <s:option value="00">Unit Dalaman</s:option>
        </s:select>
      </c:if>
      <s:text name="idPenyerah" size="5" id="idPenyerah" maxlength="5" />
      <input type="button" id="carianPenyerah"
             value="Cari" class="btn" onclick="javascript:populatePenyerah(this);" />
      (Biarkan kosong dan klik "Cari" untuk membuat rujukan)
    </p>

    <p>
      <label for="Jenis Pengenalan">Carian: No. Pengenalan :</label>
      <s:select name="penyerahJenisPengenalan.kod" id="penyerahJenisPengenalan" onchange="clearNoPengenalan();">
        <s:option value="0">Pilih Jenis...</s:option>
        <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
      </s:select>
      <em>*</em><s:text name="penyerahNoPengenalan" id="penyerahNoPengenalan" onkeyup="dodacheck(this.value);"
              onblur="doUpperCase(this.id)"/> <font title="No KP Baru: 780901057893, No KP Lama: A2977884, No Syarikat: 127776-V, No Pertubuhan: 432483-U"><em>[cth: 780901057893]</em></font>
      <input type=button name="searchPenyerah" value="Cari" class="btn" id="carianPihak"
             onclick="javascript:populatePenyerah(this);" />
    </p>
    <c:if test="${!empty SSHMA}">
      <p>
          <label><em>*</em>Jabatan / Unit</label><s:text name="penyerahNama" id="penyerahNama" size="42" onblur="doUpperCase(this.id)" class="required"/>
      </p>  
    </c:if>
      
      <c:if test="${empty SSHMA}">
    <p>
      <label><em>*</em>Nama</label><s:text name="penyerahNama" id="penyerahNama" size="42" onblur="doUpperCase(this.id)"/>
    </p>
      </c:if>

    <p>
      <label><em>*</em>Alamat</label>
      <s:text name="penyerahAlamat1" id="penyerahAlamat1" size="30" onblur="doUpperCase(this.id)" class="required"/>
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
      <label><em>*</em>Bandar</label>
      <s:text name="penyerahAlamat4" id="penyerahAlamat4" size="30" onblur="doUpperCase(this.id)"/>
    </p>

    <p>
      <label><em>*</em>Poskod</label>
      <s:text name="penyerahPoskod" maxlength="5" id="penyerahPoskod" size="17" onkeyup="validateNumber(this,this.value);" />
      <em>5 Digit [cth : 12000]</em>
    </p>

    <p>
      <label><em>*</em>Negeri</label>
      <s:select name="penyerahNegeri.kod" id="penyerahNegeri" class="required">
        <s:option value="0">Pilih ...</s:option>
        <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
      </s:select> <s:submit  name="updatePenyerah" value="Kemaskini Penyerah" class="longbtn"/>
    </p>
    <p>
      <label>No.Telefon</label>
      <s:text name="penyerahNoTelefon" id="penyerahNoTelefon" size="15"/>
    </p>
    <c:if test="${empty SSHMA}">
      <p>
        <label>Email</label>
        <s:text name="penyerahEmail" id="penyerahEmail" size="50"/>
      </p>
    </c:if>
      
      <c:if test="${!empty SSHMA}">
      <p>
        <label><em>*</em>Nama Pemohon</label><s:text name="namaPemohon" id="namaPemohon" size="42" onblur="doUpperCase(this.id)" class="required"/>
      </p>  
    </c:if>
    <br/>
    <p align="center">
      <s:submit name="CancelAll" value="Batal" class="btn" />
      <%--<s:submit name="${prevStep}" value="Kembali" class="btn" />--%>
      <%--<s:submit name="tambahUrusan" value="Tambah Urusan" class="btn" />--%>
      <%--<s:submit name="setPenyerah" value="Isi Semula" class="btn" />--%>
      <s:button  name="reset" value="Isi Semula" class="btn" onclick="test(this.form);"/>
      <%--<s:button  name="reset1" value="Isi Semula" class="btn" onclick="test(this.form);"/>--%>
      <s:submit name="${nextStep}" value="Seterusnya" class="btn" onclick="return validatePenyerah();"/>
    </p>

  </fieldset>

</s:form>

<script languange="javascript">

  $(document).ready(function() {
    updateKodPenyerahInputs();
    var id = document.getElementById('idPenyerah');
    if (id.value != '') {
      $('#carianPenyerah').click();
    }
  });

</script>