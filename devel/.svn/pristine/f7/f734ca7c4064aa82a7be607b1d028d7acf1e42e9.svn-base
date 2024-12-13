<%-- 
    Document   : prestasi_pegawai_tukar_ganti_main
    Created on : Dec 19, 2013, 11:57:32 AM
    Author     : azri
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf"%>
<!DOCTYPE html>
<title>e-Tanah Prestasi Pegawai Tukar Ganti</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
  $(document).ready(function() {
    $('#seterusnya').click(function() {
      doBlockUI();
    });
  });

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
<div class="prestasiPegwai">
  <s:messages />
  <s:errors />
  <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
  <s:form beanclass="etanah.view.daftar.utiliti.PrestasiPegawaiTukarGantiActionBean" name="form1">
    <fieldset class="aras1">
      <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
      <legend>Utiliti Pertanyaan Prestasi Pegawai Tukar Ganti</legend>
      <br>
      <p>
        <label>Nama Pegawai : </label>        
        <s:select name="pegawaiTukarganti">
          <s:option value="">--Sila Pilih--</s:option>
          <s:options-collection collection="${actionBean.listPegawai}" label="nama" value="idPengguna" sort="nama"/>
        </s:select>
      </p>
      <br>
      <div align="center">
        <s:submit name="seterusnya" value="Seterusnya" class="btn" id="seterusnya" />&nbsp; 
      </div>
      <br>
    </fieldset>

    <br>    
    <fieldset class="aras1">
      <legend>Keputusan Carian Prestasi Pegawai</legend>
      <br>
      <div align="center">
        <display:table class="tablecloth" style="width:95%;" cellpadding="0" cellspacing="0" id="line" pagesize="15"
                       requestURI="/daftar/utiliti/prestasiPegawai" name="${actionBean.listTukarGanti}">
          <display:column title="Bil" style="width:1%;"><p align='right'>${line_rowNum}</p></display:column>
          <display:column title="Id Hakmilik" property="idHakmilik" style="width:15%;"/>   
          <display:column title="Kod Urusan" style="width:7%;"><div align="center">${line.kodUrusan}</div></display:column>  
          <display:column title="Nama Urusan" property="namaUrusan"/>
          <display:column title="Tarikh Daftar" style="width:10%;"><div align="center">${line.tarikhDaftar}</div></display:column>
          <display:column title="Nama Pegawai" property="namaPegawai"/>
          <display:column title="Tarikh Tukar Ganti" style="width:10%;"><div align="center">${line.tarikhTukarGanti}</div></display:column>
        </display:table>
      </div>
      <br><br>
    </fieldset>          

  </s:form> 
</div>
