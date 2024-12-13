<%-- 
    Document   : kemaskini_notis_popup
    Created on : Nov 6, 2012, 3:42:29 PM
    Author     : ei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/date.format.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>

<script type="text/javascript">
  $(document).ready(function() {
    $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
  <c:if test="${actionBean.save}">
    self.close();
    opener.refreshKemaskiniNotis();
  </c:if>

      var cara = $("#caraPenghantaran").val();
      if (cara == "PN") {
        $('#tarikhSerah').show();
        $('#penerimaNotis').show();
        $('#warta').hide();
        $('#noWarta').val("");
        $('#tarikhTampal').hide();
      }
      else if (cara == "TT") {
        $('#tarikhSerah').show();
        $('#penerimaNotis').hide();
        $('#warta').show();
        $('#tarikhTampal').show();

        if ($('#noWarta').val() == "") {
          $('#tarikhTerima').val();
          $('#tarikhWarta').val("");
        } else {
          $('#tarikhTerima').val("");
          $('#tarikhWarta').val();
        }
      }
      else if (cara == "WA") {
        $('#tarikhSerah').show();
        $('#penerimaNotis').hide();
        $('#warta').show();
        $('#tarikhTampal').hide();

        if ($('#noWarta').val() == "") {
          $('#tarikhTerima').val();
          $('#tarikhWarta').val("");
        } else {
          $('#tarikhTerima').val("");
          $('#tarikhWarta').val();
        }
      }
      else if (cara == "01") {
        $('#tarikhSerah').show();
        $('#penerimaNotis').show();
        $('#warta').show();
        $('#tarikhTampal').hide();

        if ($('#noWarta').val() == "") {
          $('#tarikhTerima').val();
          $('#tarikhWarta').val("");
        } else {
          $('#tarikhTerima').val("");
          $('#tarikhWarta').val();
        }
      }
      else if (cara == "AR") {
        $('#tarikhSerah').show();
        $('#penerimaNotis').show();
        $('#warta').show();
        $('#tarikhTampal').hide();

        if ($('#noWarta').val() == "") {
          $('#tarikhTerima').val();
          $('#tarikhWarta').val("");
        } else {
          $('#tarikhTerima').val("");
          $('#tarikhWarta').val();
        }
      }
      else if (cara == "") {
        $('#tarikhTerima').val("");
        $('#tarikhWarta').val("");
        $('#tarikhLuput').val("");
        $('#tarikhTampal1').val("");
        $('#noWarta').val("");
        $('#tarikhSerah').hide();
        $('#penerimaNotis').hide();
        $('#warta').hide();
        $('#tarikhTampal').hide();
      }
      else {
        $('#tarikhSerah').show();
        $('#penerimaNotis').hide();
        $('#warta').hide();
        $('#noWarta').val("");
        $('#tarikhTampal1').val("");
        $('#tarikhTampal').hide();
      }
    });

    function tutup() {
        self.close();
        opener.refreshKemaskiniNotis();        
    }

    function changeCara(value) {
      if (value == "PN") {
        $('#tarikhSerah').show();
        $('#penerimaNotis').show();
        $('#warta').hide();
        $('#tarikhTampal').hide();
        $('#penerimaNotis2').val("");
        $('#tarikhWarta').val("");
        $('#tarikhTampal1').val("");
        $('#noWarta').val("");
        $('#tarikhLuput').val("");
        $('#tarikhHantar').val("");
      }
      else if (value == "TT") {
        $('#tarikhSerah').show();
        $('#penerimaNotis').hide();
        $('#warta').show();
        $('#tarikhTampal').show();
        $('#tarikhWarta').val("");
        $('#tarikhTampal1').val("");
        $('#noWarta').val("");
        $('#penerimaNotis2').val("");
        $('#tarikhLuput').val("");
        $('#tarikhHantar').val("");
      }
      else if (value == "WA") {
        $('#tarikhSerah').show();
        $('#penerimaNotis').hide();
        $('#warta').show();
        $('#penerimaNotis2').val("");
        $('#tarikhWarta').val("");
        $('#tarikhTampal1').val("");
        $('#tarikhTampal').hide();
        $('#noWarta').val("");
        $('#tarikhLuput').val("");
        $('#tarikhHantar').val("");
      }
      else if (value == "01") {
        $('#tarikhSerah').show();
        $('#penerimaNotis').show();
        $('#tarikhWarta').val("");
        $('#tarikhTampal1').val("");
        $('#tarikhTampal').hide();
        $('#noWarta').val("");
        $('#penerimaNotis2').val("");
        $('#warta').show();
        $('#tarikhLuput').val("");
        $('#tarikhHantar').val("");
      }
      else if (value == "AR") {
        $('#tarikhSerah').show();
        $('#penerimaNotis').show();
        $('#tarikhWarta').val("");
        $('#tarikhTampal1').val("");
        $('#tarikhTampal').hide();
        $('#penerimaNotis2').val("");
        $('#warta').show();
        $('#noWarta').val("");
        $('#tarikhLuput').val("");
        $('#tarikhHantar').val("");
      }
      else if (value == "") {
        $('#tarikhSerah').hide();
        $('#penerimaNotis').hide();
        $('#warta').hide();
        $('#tarikhTampal').hide();
        $('#tarikhTerima').val("");
        $('#penerimaNotis2').val("");
        $('#tarikhWarta').val("");
        $('#tarikhLuput').val("");
        $('#tarikhTampal1').val("");
        $('#noWarta').val("");
        $('#tarikhLuput').val("");
        $('#tarikhHantar').val("");
      }
      else {
        $('#tarikhSerah').show();
        $('#penerimaNotis').hide();
        $('#warta').hide();
        $('#penerimaNotis2').val("");
        $('#tarikhWarta').val("");
        $('#tarikhTampal1').val("");
        //            $('#tarikhLuput').val("");
        $('#noWarta').val("");
        $('#tarikhTampal').hide();
        $('#tarikhLuput').val("");
        $('#tarikhHantar').val("");
      }
      kiraLuput();
    }

    function validateForm() {
      if ($('#caraPenghantaran').val() == '') {
        alert("Sili pilih cara penghantaran");
        $('#caraPenghantaran').focus();
        return false;
      }

      if ($('#statusPenyampaian').val() == '') {
        alert("Sili pilih Status penghantaran");
        $('#statusPenyampaian').focus();
        return false;
      }

      if ($('#penghantarNotis').val() == '') {
        alert("Sili pilih penghantaran notis");
        $('#penghantarNotis').focus();
        return false;
      }
      return true;
    }

    function validateNumber(elmnt, content) {
      //if it is character, then remove it..
      if (isNaN(content)) {
        elmnt.value = removeNonNumeric(content);
        return;
      }
    }

    function removeNonNumeric(strString) {
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

    function kiraLuput(id) {
      var notis = $("#jenisNotis").val();
      //        alert (notis);

      if (notis == '19C' || notis == '19A') {
        var hari = 0;
        var bln = 2;
        var thn = 0;

      } else if (notis == '5F' || notis == '5A' || notis == '10E') {
        var hari = 0;
        var bln = 3;
        var thn = 0;
      }
      else {
        return false;
      }

      var startDate = $('#' + id).val();
      var y = parseInt(startDate.substring(6, 10), 10);
      var m = parseInt(startDate.substring(3, 5), 10);
      var d = parseInt(startDate.substring(0, 2), 10);

      if (!isNaN(hari)) {
        d = d + hari;
        d = d - 1;
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
      if (!isNaN(thn)) {
        y = y + thn;
      }

      var endDate = new Date();
      endDate.setDate(d);
      endDate.setMonth(m - 1);
      endDate.setFullYear(y);
      //        alert(endDate.format("dd/mm/yyyy"));
      $('#tarikhLuput').val(endDate.format("dd/mm/yyyy"));
    }

    function test(f) {
      $(f).clearForm();
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<s:form  beanclass="etanah.view.daftar.utiliti.KemaskiniNotisActionBean">
  <div class="subtitle">
    <s:hidden name="idHakmilik" id="idHakmilik"/>
    <fieldset class="aras1">
      <br>
      <legend>Kemaskini Notis</legend>
      <div class="content">                
        <p>
          <label for="idPermohonan">Id Permohonan/ID Perserahan :</label>                    
          ${actionBean.senaraiNotis.permohonan.idPermohonan}
          <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
          <s:hidden name="idNotis" id="idPermohonan" value="${idNotis2}"/>
        </p>
        <p>
          <label for="kodUrusan">Kod Urusan / Urusan :</label>
          ${actionBean.senaraiNotis.permohonan.kodUrusan.kod} / ${actionBean.senaraiNotis.permohonan.kodUrusan.nama}
        </p>
        <p>
          <label>Jenis Notis :</label>
          <%--<s:hidden name="jenisNotis" value="${actionBean.jenisNotis}" id="jenisNotis" style="width:180px;"/>
          ${actionBean.jenisNotis2}&nbsp; --%>
          <s:select name="jenisNotis" id="jenisNotis" style="width:180px;">
              <s:option value="" >Sila Pilih</s:option>
              <s:options-collection collection="${listUtil.senaraiKodNotis}" label="nama" value="kod" />
          </s:select>&nbsp; 
        </p><br>
        <p>
          <label>Tarikh Notis :</label>
          <s:text name="tarikhNotis"  class="datepicker" formatPattern="dd/MM/yyyy" id="tarikhNotis" style="width:180px;"/>&nbsp;                    
          <font color="red" size="1">cth : hh / bb / tttt</font>
        </p>
        <p>
          <label>Tarikh Diambil - Penghantar Notis :</label>
          <s:text name="tarikhTerima"  class="datepicker" formatPattern="dd/MM/yyyy" id="tarikhTerima" style="width:180px;"/>&nbsp;
          <font color="red" size="1">cth : hh / bb / tttt</font>
        </p>                
        <p>
          <label><em>*</em>Nama Penghantar Notis :</label>
          <s:select name="penghantarNotis" id="penghantarNotis" style="width:180px;">
            <s:option value="">Sila Pilih</s:option>
            <s:options-collection collection="${listUtil.senaraiPenghantarNotis}" label="nama" value="idPenghantarNotis"/>
          </s:select>&nbsp;
        </p>
        <p>
          <label><em>*</em>Status Penyampaian :</label>
          <%-- comment out by azri: 
          <s:select name="statusPenyampaian" id="statusPenyampaian" style="width:180px;">
            <s:option value="">Sila Pilih</s:option>
            <s:options-collection collection="${listUtil.senaraiKodStatusTerima}" label="nama" value="kod" />
          </s:select>&nbsp;--%>
          <%-- N9 USE THIS --%>
          <s:select name="statusPenyampaian" id="statusPenyampaian" style="width:180px;">
            <s:option value="">Sila Pilih</s:option>
            <s:options-collection collection="${listUtil.senaraiKodStatusTerimaN9}" label="nama" value="kod" />
          </s:select>&nbsp;
        </p>
        <br>
        <p>
          <label><em>*</em>Cara Penghantaran :</label>
          <s:select name="caraPenghantaran" id="caraPenghantaran" style="width:180px;" onchange="javaScript:changeCara(this.value)">
            <s:option value="">Sila Pilih</s:option>
            <s:options-collection collection="${listUtil.senaraiKodCaraPenghantaran}" label="nama" value="kod" />
          </s:select>&nbsp;
        </p>

        <!--                tarikh Serah -->
        <div id ="tarikhSerah">
          <p>
            <label>Tarikh Notis Diserah :</label>
            <s:text name="tarikhHantar"  class="datepicker" formatPattern="dd/MM/yyyy" id="tarikhHantar" style="width:180px;"
                    onchange="kiraLuput(this.id)" />&nbsp;
            <font color="red" size="1">cth : hh / bb / tttt</font>
          </p>
          <p>
            <label>Tarikh Luput Notis :</label>
            <s:text name="tarikhLuput" id="tarikhLuput" formatPattern="dd/MM/yyyy" style="width:180px;" 
                    class="datepicker" value="${actionBean.rujukLuar.tarikhTamat}"/>&nbsp;
            <%--<s:text name="tarikhLuput" class="datepicker" formatPattern="dd/MM/yyyy" id="tarikhLuput" style="width:180px;"/>&nbsp;--%>                    
            <font color="red" size="1">cth : hh / bb / tttt</font>                    
          </p>
        </div>

        <!------ Show Penerima Notis -->                
        <div id="penerimaNotis"> 
          <p>
            <label>Penerima Notis :</label>
            <s:text name="penerimaNotis"  id="penerimaNotis2" style="width:350px;" 
                    value="${actionBean.senaraiNotis.penerimaNotis}"
                    onkeyup="this.value=this.value.toUpperCase();"/>                       
          </p>
        </div>

        <!------ Show tampal ditempat awam-->
        <div id="tarikhTampal">                                       
          <p>
            <label>Tarikh Tampal Ditempat Awam :</label>
            <s:text name="tarikhTampal"  class="datepicker" formatPattern="dd/MM/yyyy" id="tarikhTampal1" style="width:180px;" />&nbsp;
            <font color="red" size="1">cth : hh / bb / tttt</font>
          </p>              
        </div>

        <!------ Show warta-->       
        <div id="warta">                    
          <p>
            <label>Nombor Warta :</label>
            <s:text name="noWarta"  id="noWarta" style="width:180px;" 
                    value="${actionBean.senaraiNotis.warta.noRujukan}"
                    onkeyup="this.value=this.value.toUpperCase();"/> 
            <s:hidden name="nomborWarta"  id="penerimaNotis1" style="width:180px;" 
                      value="${actionBean.senaraiNotis.warta.idRujukan}"/>                       
          </p>
          <p>
            <label>Tarikh Warta :</label>
            <s:text name="tarikhWarta"  class="datepicker" formatPattern="dd/MM/yyyy" id="tarikhWarta" style="width:180px;"
                    value="${actionBean.rujukLuar.tarikhRujukan}" onchange="kiraLuput(this.id)"/>&nbsp;
            <font color="red" size="1">cth : hh / bb / tttt</font>                      
          </p>                
        </div>  
        <br>
        <p>
          <label>&nbsp;</label>
          <s:submit name="editNotis" id="simpan" class="btn" value="Simpan" onclick="if(validateForm());"/>
          <%--<s:button name="editNotis" id="simpan" class="btn" value="Simpan" onclick="if(validateForm())save(this.name,this.form);"/>--%>          
          <%--<s:button name="reset" value="Isi Semula" class="btn" onclick="return test();"/>--%>
           <s:submit name="" id="" class="btn" value="Tutup" onclick="tutup();"/>

        </p>
        <br>
      </div>
    </fieldset>
  </div>
</s:form>
