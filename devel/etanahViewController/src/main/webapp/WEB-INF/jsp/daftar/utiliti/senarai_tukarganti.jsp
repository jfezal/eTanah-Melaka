<%-- 
    Document   : senarai_tukarganti
    Created on : Nov 15, 2013, 11:01:54 AM
    Author     : azri
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf"%>
<!DOCTYPE html>
<title>e-Tanah Senarai Tukarganti</title>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">
  $(document).ready(function() {
    $(".datepicker1").datepicker({changeYear: true});
    $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

    $('#seterusnya').click(function() {
      doBlockUI();
    });
  });

  function doCetak(f) {
    // POP-UP PDF REPORT   
    var form = $(f).formSerialize();
    var url = "${pageContext.request.contextPath}/reportGeneratorServlet?" + form;
    window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=800");
  }

  function clearForm() {
    $('#trhMula').val("");
    $('#trhTamat').val("");
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
<div class="senaraiTukarganti">
  <s:messages />
  <s:errors />
  <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
  <s:form beanclass="etanah.view.daftar.utiliti.SenaraiTukarGantiActionBean" name="form1">
    <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/> 
    <fieldset class="aras1">
      <legend>Utiliti Senarai Tukarganti </legend>      
      <br>
      <p>
        <label>Tarikh Mula : </label>
        <s:text name="trhMula" class="datepicker" id="trhMula" formatPattern="dd/MM/yyyy" formatType="date" size="20"/>
      </p>
      <p>
        <label>Tarikh Tamat : </label>
        <s:text name="trhTamat" class="datepicker" id="trhTamat" formatPattern="dd/MM/yyyy" formatType="date" size="20"/>
      </p>
      <br>
      <p align="center">
        <s:submit name="seterusnya" value="Cari" class="btn" id="seterusnya" />&nbsp; 
        <s:button name="reset" value="Isi Semula" class="btn" onclick="clearForm();"/>
      </p>
      <br>
    </fieldset>

    <c:if test="${fn:length (actionBean.listTukarganti) > 0}">
      <fieldset class="aras1">
        <legend>Senarai Maklumat Urusan Tukarganti </legend>  
        <br>
        <div align="center">
          <display:table class="tablecloth" style="width:95%;" id="line" cellpadding="0" cellspacing="0" pagesize="20"
                         requestURI="/daftar/utiliti/senaraiTukarGanti" name="${actionBean.listTukarganti}" >
            <display:column title="Bil" style="width:1%;"><div align="right">${line_rowNum}</div></display:column>
            <display:column title="Id Perserahan" property="idPermohonan" style="width:15%;"/>
            <display:column title="Nama Urusan" property="namaUrusan"/>
            <display:column title="Id Hakmilik" property="idHakmilik" style="width:16%;"/>
            <display:column title="Nama Pegawai" property="pegawai"/>
            <display:column title="Tarikh" style="width:10%;">
              <div align="center">${line.tarikhMasuk}</div>
            </display:column>
          </display:table>
        </div>        
        <br>
        <p align="center">
          <s:hidden name="report_p_trh_mula" id="tarikh_Mula" value="${actionBean.trhMula}"/>
          <s:hidden name="report_p_trh_tamat" id="tarikh_Tamat" value="${actionBean.trhTamat}"/>
          <s:hidden name="reportName" id="reportName" />
          <s:button name="" id="cetak" value="Cetak" class="btn" onclick="doCetak(this.form);"/>
        </p>
        <br>
      </fieldset>
    </c:if>

  </s:form>
</div>
