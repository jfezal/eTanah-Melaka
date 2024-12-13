<%--
    Document   : maklumat_penerima_suratkuasa_wakil
    Created on : Dec 15, 2009, 3:05:01 PM
    Author     : mohd.fairul, fix by Aizuddin
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<script type="text/javascript">
  var DELIM = "__^$__";
  //    $(document).ready(function() {
  //        url = "${pageContext.request.contextPath}/daftar/suratkuasawakil/maklumatPemPen";
  //        alert('s');var s = ${aa};
  //        $("#pemberi").attr('checked', true);
  //        $(".maklumatTambahan").hide();
  //        $(".maklumatPenerima").hide();
  //        $(".maklumatPemberi").show();
  //    });

  function pilihPemberi() {

    if (document.form1.pilih.checked == true)

    {
      $('#PENERIMA').show();
    }

  }

  function miniSave(jenisPenge, idPihak, kodPenge, namaPenerima) {
    var l = encodeURIComponent(namaPenerima);
    var alamat1 = encodeURIComponent(document.form1.alamat1.value);
    var alamat2 = encodeURIComponent(document.form1.alamat2.value);
    var alamat3 = encodeURIComponent(document.form1.alamat3.value);
    var alamat4 = encodeURIComponent(document.form1.alamat4.value);
    var poskod = encodeURIComponent(document.form1.poskod.value);
    var negeri = encodeURIComponent(document.form1.negeri.value);
    if ((document.form1.pilih.checked == true) && (namaPenerima != "") && (kodPenge != ""))
    {


      var url = '${pageContext.request.contextPath}/daftar/suratkuasawakil?simpanPenerima&tmpIDpihak='
              + idPihak + '&jenisPengenalanPenerima=' + jenisPenge
              + '&namaPenerima=' + l + '&noPengenalanPenerima=' + kodPenge + '&alamat1=' + alamat1 + '&alamat2=' + alamat2 + '&alamat3=' + alamat3 +
              '&alamat4=' + alamat4 + '&poskod=' + poskod + '&negeriPenerima=' + negeri;
      $.get(url,
              function(data) {
                $('#page_div').html(data);
              }, 'html');

    }
    else
    {
    }
  }

  function cariPemberi(noKP, kodKP, namaPemberi) {


    jPihakValue = $('input:radio[name=jPihak]:checked').val();
  <%--alert(jPihakValue);--%>
      params = 'width=' + screen.width;
      params += ', height=' + screen.height;
      params += ', top=0, left=0'
      params += ', fullscreen=yes';
      params += ', directories=no';
      params += ', location=no';
      params += ', menubar=no';
      params += ', resizable=no';
      params += ', scrollbars=yes';
      params += ', status=no';
      params += ', toolbar=no';
      var url = '${pageContext.request.contextPath}/daftar/suratkuasawakil?cariPemberi&noKP=' + noKP + '&kodKP=' + kodKP + '&namaPemberi=' + namaPemberi + '&jPihak=' + jPihakValue;
  <%-- $.get(url,
   function(data){
       $('#page_div').html(data);
   },'html');--%>
       if ((jPihakValue == "penerima") || (jPihakValue == "pemberi")) {
         window.open(url, "PopUp", params);

       }
       else {
         alert("Sila Pilih Jenis Pihak!");

       }

     }

     //Added by Aizuddin to penerima
     function cariPenerima(noKP2, kodKP2, namaPenerima2) {


       jPihakValue = $('input:radio[name=jPihak]:checked').val();
  <%--alert(jPihakValue);--%>
      params = 'width=' + screen.width;
      params += ', height=' + screen.height;
      params += ', top=0, left=0'
      params += ', fullscreen=yes';
      params += ', directories=no';
      params += ', location=no';
      params += ', menubar=no';
      params += ', resizable=no';
      params += ', scrollbars=yes';
      params += ', status=no';
      params += ', toolbar=no';
      var url = '${pageContext.request.contextPath}/daftar/suratkuasawakil?cariPenerima&noKP2=' + noKP2 + '&kodKP2=' + kodKP2 + '&namaPenerima2=' + namaPenerima2 + '&jPihak=' + jPihakValue;
  <%-- $.get(url,
   function(data){
       $('#page_div').html(data);
   },'html');--%>
       if ((jPihakValue == "penerima") || (jPihakValue == "pemberi")) {
         window.open(url, "PopUp", params);

       }
       else {
         alert("Sila Pilih Jenis Pihak!");

       }

     }

     function savePihak(noKP, kodKP, namaPemberi, aMaun, sbb) {
  <%--alert(noKP+kodKP+namaPemberi);--%>
      if (kodKP == "null") {
        alert("Sila Pilih Jenis Pengenalan");
      }
      if (noKP == "") {
        alert("Sila Masukkan No.Pengenalan");
      }
      if (namaPemberi == "") {
        alert("Sila Masukkan Nama Pemberi");
      }

      if (kodKP != "null" && noKP != "" && namaPemberi != "")
      {
        jPihakValue = $('input:radio[name=jPihak]:checked').val();
        var url = '${pageContext.request.contextPath}/daftar/suratkuasawakil?savePihak&namaPemberi=' + namaPemberi + '&jenisPengenalanPemberi=' + kodKP + '&noPengenalanPemberi=' + noKP + '&jPihak=' + jPihakValue + '&syaratTambahan=' + sbb + '&amaunMaksima=' + aMaun;
        $.post(url,
                function(data) {
                  $('#page_div').html(data);
                }, 'html');
  <%--popup(url);--%>
  <%--popUPKemasukanPenerima();--%>
      }

    }


    function popup(url)
    {
      params = 'width=' + screen.width;
      params += ', height=' + screen.height;
      params += ', top=0, left=0'
      params += ', fullscreen=yes';
      params += ', directories=no';
      params += ', location=no';
      params += ', menubar=no';
      params += ', resizable=no';
      params += ', scrollbars=yes';
      params += ', status=no';
      params += ', toolbar=no';
      newwin = window.open(url, 'PopUp', params);
      if (window.focus) {
        newwin.focus()
      }
      return false;
    }
    function deletePenerima(idPenerima)
    {
  <%--alert(idPenerima);--%>
      var url = '${pageContext.request.contextPath}/daftar/suratwakilkuasa?deletePenerima2&idPenerima='
              + idPenerima;
      $.post(url,
              function(data) {
                $('#page_div').html(data);
              }, 'html');

    }

    function reload()
    {
      url = "${pageContext.request.contextPath}/daftar/suratkuasawakil/maklumatPemPen";
      $.post(url,
              function(data) {
                $('#page_div').html(data);
              }, 'html');

    }
    function deletePemberi(idPemberi)
    {
  <%--alert(id);--%>
      var url = '${pageContext.request.contextPath}/daftar/suratwakilkuasa?deletePemberi2&idPemberi='
              + idPemberi;
      $.post(url,
              function(data) {
                $('#page_div').html(data);
              }, 'html');

    }

    function validatePoskod() {
      var field = document.form1.poskod.value;
      var valid = "0123456789";

      if (field.length != 5) {
        alert("Kemasukan Poskod tidak betul!");
        return false;
      }
      for (var i = 0; i < field.length; i++) {
        temp = "" + field.substring(i, i + 1);

        if (valid.indexOf(temp) == "-1") {
          alert("Asara tidak diterima. Sila cuba lagi!");
          return false;
        }
      }

      return true;
    }

    function test() {

    }


    function save(event, f) {

      var q = $(f).formSerialize();
      var url = f.action + '?' + event;
      $.get(url, q,
              function(data) {
                $('#page_div').html(data);

              }, 'html');

    }
    function cariPihak1(event, f) {
      var ss = $('#pem').val();
      var sss = $('#pen').val();
      var jumpenerima = $('#jumPenerima').val();
      var jumpemberi = $('#jumPemberi').val();
      var kodSerah = $('#kodSerah').val();
      var nama = $('#nama').val();
      var jumPemberi = $('#jumPemberi').val();
      ;
      var jumPenerima = $('#jumPenerima').val();
      ;
      var jPihak = $('#jPihak').val();
//            if(jPihak =='pemberi'){
//                if(ss>jumpenerima){
//                    alert("Jumlah yang dimasukkan telah maksimum");
//                    return false;
//                }
//            }
//            else{
//                if(sss>jumpemberi){
//                    
//                    if(sss>jumpemberi)
//                        alert("Jumlah yang dimasukkan telah maksimum");
//                        return false;
//                    
//                }
//            }
      var q = $(f).formSerialize();
      var url = f.action + '?' + event;
      window.open(url + "&nama=" + nama + "&jPihak=" + jPihak + "&jumPemberi=" + jumPemberi + "&jumPenerima=" + jumPenerima, "eTanah",
              "status=0,toolbar=0,location=0,menubar=0,width=910,height=750");

    }

    $("#pemberi").click(function() {
      jPihakValue = $('input:radio[name=jPihak]:checked').val();
      var kodSerah = $('#kodSerah').val();
  <%--alert( jPihakValue);--%>
      $(".maklumatTambahan").hide();
      $(".maklumatPenerima").hide();
      $(".maklumatPemberi").show();

    });

    $("#penerima").click(function() {
      jPihakValue = $('input:radio[name=jPihak]:checked').val();
      var kodSerah = $('#kodSerah').val();
      $(".maklumatPemberi").hide();
      $(".maklumatPenerima").show();
  <%--alert( jPihakValue);--%>
      if (kodSerah == 'SW') {
        $(".maklumatTambahan").show();
      }
    });
    function checkBoxClick(s) {
      var kodSerah = $('#kodSerah').val();
      var v = s;
      if (v == 'pemberi') {
        //                alert(s);
        $(".maklumatTambahan").hide();
        $(".maklumatPenerima").hide();
        $(".maklumatPemberi").show();
        $(".penerimaD").hide();
        $(".pemberiD").show();
      } else {
        //                alert(s);
        $(".maklumatPemberi").hide();
        $(".maklumatPenerima").show();
        $(".penerimaD").show();
        $(".pemberiD").hide();
  <%--alert( jPihakValue);--%>
        if (kodSerah == 'SW') {
          $(".maklumatTambahan").show();
        }
      }
    }
    $(document).ready(function() {

      alert(ss);

      $("#pemberi").attr('checked', true);

      $(".maklumatTambahan").hide();
      $(".maklumatPenerima").hide();
      $(".maklumatPemberi").show();


      $("#noKP").keydown(function(event) {
        // Allow only backspace and delete
        if (event.keyCode == 46 || event.keyCode == 8) {

          // let it happen, don't do anything
        }
        else {
          // Ensure that it is a number and stop the keypress
          if (event.keyCode < 48 || event.keyCode > 57) {
            event.preventDefault();
          }
        }
      });

      $("#noKP").keypress(function()
      {
        var nokp_length;

        nokp_length = $("#noKP").val().length;
        $("#panjangWarning").empty();

        if (nokp_length > 12) {
          $("#panjangWarning").append("No Kad Pengenalan Melebihi Had Sepatutnya.");
          $("#cari").hide();
          $("#tambah").hide();

        }
        else {
          $("#cari").show();
          $("#tambah").show();

        }
      });


    });
    function dodacheck(val) {
      //var mikExp = /[1\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\1\|]/;
      var v = $('#kodKp').val();

      if (v == 'B') {
        var strPass = val;
        var strLength = strPass.length;
        //$('#kp').attr("maxlength","12");
        if (strLength > 12) {
          var tst = val.substring(0, (strLength) - 1);
          $('#noKp').val(tst);
        }
        var lchar = val.charAt((strLength) - 1);
        if (isNaN(lchar)) {
          //return false;
          var tst = val.substring(0, (strLength) - 1);
          $('#noKp').val(tst);
        }
      }//else{
      // $('#kp').attr("maxlength","30");
      // }
    }

</script>

<style type="text/css">
  img{cursor:pointer}
</style>
<s:messages/>
<s:errors/>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form name="form1" beanclass="etanah.view.daftar.SuratWakilKuasaActionBean" >
  <s:hidden name="kodSerah" id="kodSerah" value="${actionBean.mohon.kodUrusan.kodPerserahan.kod}" />
  <%-- Cannot use this -Aizuddin
      <s:messages />
      <s:errors /> --%>
  <div class="subtitle">
    <c:if test="${!actionBean.readOnly}">
      <fieldset class="aras1">
        <legend>
          Kemasukan  Pemberi & Penerima
        </legend>
        <p>
          <label>Jenis Pihak :</label>                    
          <s:select name="jPihak" id="jPihak" onchange="checkBoxClick(this.value);" style="width:200pt;">
            <s:option value="pemberi">Pemberi</s:option>
            <s:option value="penerima">Penerima</s:option>
          </s:select>
          <span style="color:red">
            &nbsp;&nbsp;*&nbsp;Sila pilih
          </span>
        </p>
        <div style="display: none" class="penerimaD">
          <p>
            <label>Jumlah Penerima :</label>
            <s:text name="jumPenerima" id="jumPenerima" size="40"/>
          </p> </div>
        <div  class="pemberiD">
          <p >
            <label>Jumlah Pemberi :</label>
            <s:text name="jumPemberi" id="jumPemberi" size="40"/>
          </p> </div>
        <p>
          <label>Nama :</label>
          <s:text name="nama" id="nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
        </p> 
        <p>
          <label>Jenis Pengenalan :</label>
          <s:select name="kodKp" id="kodKp" value="kod" style="width:200pt;">
            <s:option value="null">Pilih ...</s:option>
            <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod" />
          </s:select>
        </p>
        <p>
          <label>No Pengenalan :</label>
          <s:text name="noKp" id="noKp" size="40" onkeyup="dodacheck(this.value);this.value=this.value.toUpperCase();"/>&nbsp;<em style="font-style:normal">(cth : 800620125177)</em>
          <%--<s:text name="noKp" id="noKp"/>--%>
          <span id="panjangWarning" style="color:red"></span>
        </p>

        <br/>
        <fieldset class="maklumatTambahan" style="display: none">
          <legend>
            Maklumat Tambahan
          </legend>
          <p>
            <label>Amaun Maksima (RM) :</label><s:text name="amaunMaksima" style="width:250pt;"/>
          </p>
          <p>
            <label>Catatan / Syarat : </label>
            <s:textarea name="syaratTambahan" style="width:250pt; height:100pt"></s:textarea>
            </p>            
            <br/>
          </fieldset>
          <br>
          <div align="center">
          <s:button id="cari" name="cariPihak" value="Cari" class="btn" onclick="cariPihak1(this.name, this.form);"/>
          <s:button id="tambah" name="tambahPemberiPenerimaV2" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
        </div>
        <br>
        <br/>
      </fieldset>
    </c:if>

    <fieldset class="aras1" >
      <legend>
        Maklumat Pemberi & Penerima
      </legend>
      <div class="content" align="center">
        <%--<display:table class="tablecloth" name="${actionBean.wKuasa}" pagesize="10" cellpadding="0" cellspacing="0"
                       requestURI="${pageContext.request.contextPath}/common/pihak" id="line">

                    <c:set value="1" var="count"/>

                    <display:column title="Pemberi">
                        <c:set value="1" var="count"/>
                        <c:forEach items="${line.senaraiPemberi}" var="sPemberi" >
                           
                            <c:out value="${sPemberi.pihak.nama}"/>
                            (${sPemberi.pihak.jenisPengenalan.nama}:${sPemberi.pihak.noPengenalan})
                            <c:if test="${!actionBean.readOnly}">
                                <img alt='Klik Untuk Mansuh' title="Klik Untuk Mansuh" border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'id='hjhgh' onmouseover="this.style.cursor='pointer';" onclick="deletePemberi('${sPemberi.idPemberi}')"/>
                                
                                <c:set value="${count +1}" var="count"/><br>
                            </c:if>
                        </c:forEach>
                    </display:column>
                    <display:column title="Penerima">
                        <c:set value="1" var="count"/>
                        <c:forEach items="${line.senaraiPenerima}" var="sPenerima" >
                        
                            <c:out value="${sPenerima.nama}"/>
                            (${sPenerima.jenisPengenalan.nama}:${sPenerima.noPengenalan})
                            <c:if test="${!actionBean.readOnly}">
                                <img alt='Klik Untuk Mansuh' title="Klik Untuk Mansuh" border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'id='hjhgh' onmouseover="this.style.cursor='pointer';" onclick="deletePenerima('${sPenerima.idPenerima}')"/>
                            </c:if>
                            <c:set value="${count +1}" var="count"/><br>
                        </c:forEach>
                    </display:column>

                </display:table>--%>

        <display:table class="tablecloth" name="${actionBean.wKuasa}" cellpadding="0" cellspacing="0" id="line">

          <display:column title="Pemberi" sortable="true">
            <display:table class="tablecloth" name="${line.senaraiPemberi}" cellpadding="0" cellspacing="0" id="line2">
              <display:column title="Bil" sortable="true">${line2_rowNum}</display:column>
              <display:column title="Nama" sortable="true">${line2.pihak.nama}</display:column>
              <display:column title="Jenis Pengenalan" sortable="true">${line2.pihak.jenisPengenalan.nama}</display:column>
              <display:column title="No Pengenalan" sortable="true">${line2.pihak.noPengenalan}</display:column>
              <c:if test="${!actionBean.readOnly}"> 
                <display:column title="Hapus" sortable="true"> <img alt='Klik Untuk Mansuh' title="Klik Untuk Mansuh" border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'id='hjhgh' onmouseover="this.style.cursor = 'pointer';" onclick="deletePemberi('${line2.idPemberi}')"/>
                </display:column>
              </c:if>
              <c:set var="aa" value="${line2_rowNum}"></c:set>

            </display:table> <s:hidden name="pem" id="pem" class="pem" value="${aa}">${aa}</s:hidden>
          </display:column>
          <display:column title="Penerima" sortable="true">
            <display:table class="tablecloth" name="${line.senaraiPenerima}" cellpadding="0" cellspacing="0" id="line3">
              <display:column title="Bil" sortable="true">${line3_rowNum}</display:column>
              <display:column title="Nama" sortable="true">${line3.nama}</display:column>
              <display:column title="Jenis Pengenalan" sortable="true">${line3.jenisPengenalan.nama}</display:column>
              <display:column title="No Pengenalan" sortable="true">${line3.noPengenalan}</display:column>
              <c:if test="${!actionBean.notSW}">
                <display:column title="Amaun Maksima (RM)" sortable="true">RM ${line3.amaunMaksima}</display:column>
              </c:if>
              <c:if test="${!actionBean.readOnly}"> 
                <display:column title="Hapus" sortable="true"> <img alt='Klik Untuk Mansuh' title="Klik Untuk Mansuh" border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'id='hjhgh' onmouseover="this.style.cursor = 'pointer';" onclick="deletePenerima('${line3.idPenerima}')"/>
                </display:column>
              </c:if>
              <c:set var="aac" value="${line3_rowNum}"></c:set>
            </display:table><s:hidden name="pen" id="pen" class="pen" value="${aac}">${aac}</s:hidden></display:column>
        </display:table>
      </div>
      <br/>
      <br/>
    </fieldset>
  </div>
  <br/>
</s:form>