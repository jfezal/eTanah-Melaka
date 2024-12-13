<%-- 
    Document   : carian_laporan
    Created on : Nov 1, 2013, 11:44:23 AM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf"%>
<!DOCTYPE html>
<title>e-Tanah Laporan Tanah</title>
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
  
    function clearForm(f) {

      }
</script>
  <s:messages />
  <s:errors />
  <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
  <s:form beanclass="etanah.view.stripes.pelupusan.utility.UtilitiLaporanTanahActionBean" name="form1">    
    <fieldset class="aras1">
      <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
      <legend>Utiliti Laporan Tanah </legend>
      <br> 
      <p>
        <label>ID Permohonan :</label>
        <s:text name="idPermohonan" size="20" id="idPermohonan" onkeyup="this.value=this.value.toUpperCase();"/>
      </p>
      <p>
        <br>
        <label>&nbsp;</label>      
            <s:submit name="seterusnya" value="Seterusnya" class="btn" id="seterusnya" />&nbsp; 
        <s:button name="reset" value="Isi Semula" class="btn" onclick="clearForm(this.form);"/>
      </p>
      <br>
    </fieldset>
  </s:form>
