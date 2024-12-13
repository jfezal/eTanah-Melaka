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
  $(document).ready(function() {
    url = "${pageContext.request.contextPath}/daftar/suratkuasawakil/maklumatPemPen";

    $("#pemberi").attr('checked', true);
    $(".maklumatTambahan").hide();
    $(".maklumatPenerima").hide();
    $(".maklumatPemberi").show();
  });

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
      var nokp_length = $("#noKP").val().length;
      if (kodKP == "null") {
        alert("Sila Pilih Jenis Pengenalan");
      }
      if(kodKP != "X" && kodKP != "0"){
      if (noKP == "") {
        alert("Sila Masukkan No.Pengenalan");
      }
      }
      if (namaPemberi == "") {
        alert("Sila Masukkan Nama Pemberi");
      }else{
          namaPemberi = namaPemberi.replace(/[&]/g,'_');
          namaPemberi = namaPemberi.replace(/[']/g,'..');
      }

      if (kodKP != "null" && namaPemberi != ""){
          if(kodKP == "B"){
               if(nokp_length != 12) 
                   alert("No Kad Pengenalan Tidak Mengikut Had Sepatutnya");
               
               else {
                jPihakValue = $('input:radio[name=jPihak]:checked').val();
                var url = '${pageContext.request.contextPath}/daftar/suratkuasawakil?savePihak&namaPemberi=' + namaPemberi + '&jenisPengenalanPemberi=' + kodKP + '&noPengenalanPemberi=' + noKP + '&jPihak=' + jPihakValue + '&syaratTambahan=' + sbb + '&amaunMaksima=' + aMaun;
                $.post(url,
                        function(data) {
                          $('#page_div').html(data);
                        }, 'html');
               }    
          }else if(kodKP != "B"){
                jPihakValue = $('input:radio[name=jPihak]:checked').val();
                var url = '${pageContext.request.contextPath}/daftar/suratkuasawakil?savePihak&namaPemberi=' + namaPemberi + '&jenisPengenalanPemberi=' + kodKP + '&noPengenalanPemberi=' + noKP + '&jPihak=' + jPihakValue + '&syaratTambahan=' + sbb + '&amaunMaksima=' + aMaun;
                $.post(url,
                        function(data) {
                          $('#page_div').html(data);
                        }, 'html');
                }
  <%--popup(url);--%>
  <%--popUPKemasukanPenerima();--%>
      }

    }

     function savePihak2(noKP, kodKP, namaPemberi, aMaun, sbb) {
  <%--alert(noKP+kodKP+namaPemberi);--%>
      var nokp_length = $("#noKP2").val().length;
      if (kodKP == "null") {
        alert("Sila Pilih Jenis Pengenalan");
      }
      if(kodKP != "X" && kodKP != "0"){
      if (noKP == "") {
        alert("Sila Masukkan No.Pengenalan");
      }
      }
      if (namaPemberi == "") {
        alert("Sila Masukkan Nama Penerima");
      }else{
          namaPemberi = namaPemberi.replace(/[&]/g,'_');
          namaPemberi = namaPemberi.replace(/[']/g,'..');
      }      
    

      if (kodKP != "null" && namaPemberi != ""){
          if(kodKP == "B"){
               if(nokp_length != 12) 
                   alert("No Kad Pengenalan Tidak Mengikut Had Sepatutnya");
               
               else {
                jPihakValue = $('input:radio[name=jPihak]:checked').val();
                var url = '${pageContext.request.contextPath}/daftar/suratkuasawakil?savePihak&namaPemberi=' + namaPemberi + '&jenisPengenalanPemberi=' + kodKP + '&noPengenalanPemberi=' + noKP + '&jPihak=' + jPihakValue + '&syaratTambahan=' + sbb + '&amaunMaksima=' + aMaun;
                $.post(url,
                        function(data) {
                          $('#page_div').html(data);
                        }, 'html');
               }    
          }else if(kodKP != "B"){
                jPihakValue = $('input:radio[name=jPihak]:checked').val();
                var url = '${pageContext.request.contextPath}/daftar/suratkuasawakil?savePihak&namaPemberi=' + namaPemberi + '&jenisPengenalanPemberi=' + kodKP + '&noPengenalanPemberi=' + noKP + '&jPihak=' + jPihakValue + '&syaratTambahan=' + sbb + '&amaunMaksima=' + aMaun;
                $.post(url,
                        function(data) {
                          $('#page_div').html(data);
                        }, 'html');
                }
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
      var url = '${pageContext.request.contextPath}/daftar/suratkuasawakil?deletePenerima&idPenerima='
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
      var url = '${pageContext.request.contextPath}/daftar/suratkuasawakil?deletePemberi&idPemberi='
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

//    $(document).ready(function() {
//      $("#pemberi").attr('checked', true);
//
//      $(".maklumatTambahan").hide();
//      $(".maklumatPenerima").hide();
//      $(".maklumatPemberi").show();
//
//
//      $("#noKP").keydown(function(event) {
//        // Allow only backspace and delete
//        if (event.keyCode == 46 || event.keyCode == 8) {
//
//          // let it happen, don't do anything
//        }
//        else {
//          // Ensure that it is a number and stop the keypress
//          if (event.keyCode < 48 || event.keyCode > 57) {
//            event.preventDefault();
//          }
//        }
//      });
//
//      $("#noKP").keypress(function()
//      {
//        var nokp_length;
//
//        nokp_length = $("#noKP").val().length;
//        $("#panjangWarning").empty();
//
//        if (nokp_length > 12) {
//          $("#panjangWarning").append("No Kad Pengenalan Melebihi Had Sepatutnya.");
//          $("#cari").hide();
//          $("#tambah").hide();
//
//        }
//        else {
//          $("#cari").show();
//          $("#tambah").show();
//
//        }
//      });
//
//
//    });

</script>

<style type="text/css">
  img{cursor:pointer}
</style>
<s:messages/>
<s:errors/>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form name="form1" beanclass="etanah.view.daftar.SuratKuasaWakilActionBean">
  <s:hidden name="kodSerah" id="kodSerah" value="${actionBean.permohonan.kodUrusan.kodPerserahan.kod}"/>
  <%-- Cannot use this -Aizuddin
      <s:messages />
      <s:errors /> --%>
  <div class="subtitle">
    <c:if test="${noEditable ne 'true'}">
      <fieldset class="aras1">
        <legend>
          Kemasukan Pemberi 
        </legend>
        <p>
          <label>Jenis Pihak:</label>
          Pemberi<s:radio name="jPihak" value="pemberi" checked="checked" id="pemberi" /> &nbsp;&nbsp;
          Penerima<s:radio name="jPihak" value="penerima" id='penerima' />
          <span style="color:red">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*Sila pilih
          </span>
        </p>
        <div class="maklumatPemberi">    
          <p>
            <label>Nama:</label>
            <s:text name="namaPemberi" id="namaPemberi" size="30" onkeyup="this.value=this.value.toUpperCase();"/>
          </p> 
          <p>
            <label>Jenis Pengenalan:</label>
            <s:select name="kodKP" id="kodKP" value="kod" >
              <s:option value="null">Pilih ...</s:option>
              <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod" />
            </s:select>
          </p>
          <p>
            <label>No Pengenalan:</label>
            <s:text name="noKP" id="noKP"/>
            <span id="panjangWarning" style="color:red"></span>
          </p>
        </div>
        <div class="maklumatPenerima">    
          <p>
            <label>Nama:</label>
            <s:text name="namaPenerima2" id="namaPenerima2" size="30" onkeyup="this.value=this.value.toUpperCase();"/>
          </p>
          <p>
            <label>Jenis Pengenalan:</label>
            <s:select name="kodKP2" id="kodKP2" value="kod" >
              <s:option value="null">Pilih ...</s:option>
              <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod" />
            </s:select>
          </p>
          <p>
            <label>No Pengenalan:</label>
            <s:text name="noKP2" id="noKP2"/>
            <span id="panjangWarning" style="color:red"></span>
          </p>
        </div>
        <br/>
        <fieldset class="maklumatTambahan">
          <legend>
            Maklumat Tambahan
          </legend>
          <p>
            <label>Amaun Maksima (RM):</label><s:text name="amaunMaksima" style="width:250pt;"/>
          </p>
          <p>
            <label>Catatan / Syarat : </label>
            <s:textarea name="syaratTambahan" style="width:250pt; height:100pt"></s:textarea>
            </p>
            <br/>
            <br/>
          </fieldset>
          <br/>
          <div align="center" class="maklumatPemberi">
          <s:button id="cari" name="cari" value="Cari" class="btn" onclick="cariPemberi(noKP.value,kodKP.value, namaPemberi.value)"/>
          <s:button id="tambah" name="tambah" value="Tambah Baru" class="btn" onclick="savePihak(noKP.value,kodKP.value, namaPemberi.value,amaunMaksima.value,syaratTambahan.value )"/>
        </div>        
        <div align="center" class="maklumatPenerima">        
          <s:button id="cari" name="cari" value="Cari" class="btn" onclick="cariPenerima(noKP2.value,kodKP2.value, namaPenerima2.value)"/>
          <s:button id="tambah" name="tambah" value="Tambah Baru" class="btn" onclick="savePihak2(noKP2.value,kodKP2.value, namaPenerima2.value,amaunMaksima.value,syaratTambahan.value )"/>
        </div>
        <br/>
      </fieldset>
    </c:if>

    <fieldset class="aras1" id="PENERIMA">
      <legend>
        Maklumat Pemberi & Penerima
      </legend>
      <div class="content" align="center">
        <display:table class="tablecloth" name="${actionBean.wKuasa}" pagesize="10" cellpadding="100" cellspacing="0" style="width:90%;"
                       requestURI="${pageContext.request.contextPath}/common/pihak" id="line">

          <c:set value="1" var="count"/>

          <display:column title="Pemberi">
            <c:set value="1" var="count"/>
            <c:forEach items="${line.senaraiPemberi}" var="sPemberi" >
              <%--<c:out value="${count}"/>--%>
              ${count}.
              <c:out value="${sPemberi.nama}"/>
              <c:if test="${sPemberi.noPengenalan ne null}">
                (${sPemberi.noPengenalan})
              </c:if>
              <c:if test="${noEditable ne 'true'}">
                <img alt='Klik Untuk Mansuh' title="Klik Untuk Mansuh" border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'id='hjhgh' onmouseover="this.style.cursor = 'pointer';" onclick="deletePemberi('${sPemberi.idPemberi}')"/>
                <%--<img alt='Klik Untuk Tambah Penerima' title="Klik Untuk Tambah Penerima" border='0' src='${pageContext.request.contextPath}/pub/images/tambah.png' class='rem'id='hjhgh' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/suratkuasawakil/popUpKemasukanPenerimaWakil?idPihakPemberi=${sPemberi.pihak.idPihak}')"/>--%>
                <c:set value="${count +1}" var="count"/><br>
              </c:if>
            </c:forEach>
          </display:column>
          <display:column title="Penerima">
            <c:set value="1" var="count"/>
            <c:forEach items="${line.senaraiPenerima}" var="sPenerima" >
              <%--<c:out value="${count}"/>--%>
              ${count}.
              <c:out value="${sPenerima.nama}"/>
              <c:if test="${sPenerima.jenisPengenalan.nama ne null && sPenerima.noPengenalan ne null}">
                (${sPenerima.jenisPengenalan.nama}:${sPenerima.noPengenalan})
              </c:if>
              <c:if test="${noEditable ne 'true'}">
                <img alt='Klik Untuk Mansuh' title="Klik Untuk Mansuh" border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'id='hjhgh' onmouseover="this.style.cursor = 'pointer';" onclick="deletePenerima('${sPenerima.idPenerima}')"/>
              </c:if>
              <c:set value="${count +1}" var="count"/><br>
            </c:forEach>
          </display:column>
          <c:if test="${noEditable ne 'true'}">
            <display:column title="<img align='middle' alt='Klik Untuk refresh' title='Klik Untuk Refresh' border='0' src='${pageContext.request.contextPath}/pub/images/refresh.png' onclick='reload()'/>" style="cursor:pointer">
              
            </display:column>
          </c:if>
        </display:table>
      </div>
      <br/>
      <br/>
    </fieldset>
  </div>
  <br/>
</s:form>