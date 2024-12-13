<%-- 
    Document   : kutipan_data_hm_asalSebelum
    Created on : Dec 18, 2013, 12:11:25 AM
    Author     : azri
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.2.custom.min.js"></script>

<script type="text/javascript">
  $(document).ready(function() {
    $(".datepicker").datepicker({dateFormat: 'dd/mm/yy',
      changeMonth: true,
      changeYear: true});
  <c:if test="${actionBean.refresh}">
//    alert("refresh");
    self.close();
    opener.refreshMaklumatDetail();
  </c:if> 
    $("#namaJenisHakmilik").change(function() {
      var valueJenisHakmilik = $("#namaJenisHakmilik").val();
      $("#kodJenisHakmilik").val(valueJenisHakmilik);
    });
    $("#kodJenisHakmilik").blur(function() {
      var valueJenisHakmilik = $("#kodJenisHakmilik").val();
      $("#namaJenisHakmilik").val(valueJenisHakmilik);
    });
  });

  function checkNoHakmilik2(f) {
    // auto insert 8 no in no hakmilik
//    $('#idHakmilik0').val("");
    var noHm = zeroPad(f, 8);
    $('#noHakmilik').val(noHm);
  }
  
  function zeroPad(num, count) {
    // auto insert '0000' in no hakmilik
    var numZeropad = num + '';
    while (numZeropad.length < count) {
      numZeropad = "0" + numZeropad;
    }
    return numZeropad;
  }

  function RemoveAlphabet(strString) {
    // REMOVE OTHER THEN NUMBER
    var strValidCharacters = "0123456789";
    var strReturn = "";
    var strBuffer = "";
    var intIndex = 0;
    // Loop through the string
    for (intIndex = 0; intIndex < strString.length; intIndex++) {
      strBuffer = strString.substr(intIndex, 1);
      // Is this a number      
      if (strValidCharacters.indexOf(strBuffer) > -1) {
        strReturn += strBuffer;
      }
    }
    return strReturn;
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
    width:30em;
    vertical-align:top;
  }
  #tdDisplay {
    color:black;
    font-size:13px;
    font-weight:normal;
    width:40em;
  }
</style>
<s:errors/>
<s:messages/>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/> 
<div class="subtitle">
  <s:form beanclass="etanah.view.daftar.utiliti.KutipanDataActionBean" name="form1">
    <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
    <s:hidden name="idHakmilik" id="idHakmilik"/>
    <s:hidden name="flag" id="flag"/>
    <fieldset class="aras1">
      <c:choose>
        <c:when test="${actionBean.flag eq true}">
          <legend>Maklumat Hakmilik Asal</legend>
        </c:when>
        <c:otherwise>
          <legend>Maklumat Hakmilik Sebelum</legend>
        </c:otherwise>
      </c:choose>
      <br>
      <table align="center">
        <tr>
          <td id="tdLabel">Jenis Hakmilik :&nbsp;</td>
          <td id="tdDisplay">
            <s:text name="kodJenisHakmilik" size="4" id="kodJenisHakmilik" onblur="this.value=this.value.toUpperCase();"/>&nbsp;-&nbsp;
            <s:select name="jenisHakmilik" id="namaJenisHakmilik" style="width:200">
              <s:option value="">-- Sila Pilih --</s:option>
              <c:choose>
                <c:when test="${actionBean.pguna.kodCawangan.kod ne '00' && actionBean.kodNegeri eq '05'}">                  
                  <s:options-collection collection="${actionBean.senaraiKodHakmilikPTD}" label="nama" value="kod" />
                </c:when>   
                <c:when test="${actionBean.pguna.kodCawangan.kod ne '00' && actionBean.kodNegeri eq '04'}">                  
                  <%--<s:options-collection collection="${actionBean.senaraiKodHakmilikPTDmlk}" label="nama" value="kod" />--%>
                  <s:options-collection collection="${actionBean.senaraiKodHakmilikAll}" label="nama" value="kod" />
                </c:when> 
                <c:otherwise>
                  <s:options-collection collection="${actionBean.senaraiKodHakmilikAll}" label="nama" value="kod" />
                </c:otherwise>
              </c:choose>
            </s:select>
          </td>
        </tr>
        <tr>
          <td id="tdLabel">No Hakmilik :&nbsp;</td>
          <td id="tdDisplay">
            <s:text name="noHakmilik" id="noHakmilik" size="10" 
                    onblur="checkNoHakmilik2(this.value);this.value=this.value.toUpperCase();" />&nbsp; 
          </td>
        </tr>
        <c:if test="${actionBean.flag eq true}">
          <tr>
            <td id="tdLabel">Tarikh Mula-mula Diberi milik :&nbsp;</td>
            <td id="tdDisplay">
              <s:text name="trhDaftarAsal" class="datepicker" id="trhDaftarAsal" 
                      value="${actionBean.hakmilik.tarikhDaftarAsal}"
                      formatPattern="dd/MM/yyyy" formatType="date" />&nbsp; 
            </td>
          </tr>
        </c:if>
      </table>
      <br>
      <div align="center">
        <c:choose>
          <c:when test="${actionBean.flag eq true}">
            <s:submit name="simpanHakmilikAsal" id="simpanPihak" value="Simpan" class="btn"/>&nbsp; 
          </c:when>
          <c:otherwise>
            <s:submit name="simpanHakmilikSebelum" id="simpanPihak" value="Simpan" class="btn"/>&nbsp; 
          </c:otherwise>
        </c:choose>
        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
      </div>
      <br>
    </fieldset>
  </s:form>
</div>
