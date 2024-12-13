<%-- 
    Document   : undo_tukarganti_main
    Created on : Nov 13, 2013, 10:11:39 AM
    Author     : ei
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf"%>
<!DOCTYPE html>
<title>e-Tanah Undo Tukarganti</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
  $(document).ready(function() {
    $('#undoTukarganti').click(function() {
      doBlockUI();
    });

    $('#seterusnya').click(function() {
      doBlockUI();
    });
  });

  function appendAutoAll(intIndex) {
    // AUTO INSERT ID HAKMILIK IF BIL HAKMILIK IS CHANGED
    var val2 = $('#idHakmilikSiriDari' + intIndex).val();
    if (val2 !== '') {
      appendAuto2(val2, intIndex);
    }
  }

  function appendAuto2(val, id) {
    // AUTO INSERT ID HAKMILIK HINGGA
    var bil = $('#bilHakmilik' + id).val();
    var len = val.length;
    if (len !== 16 && len !== 17) {
      alert("Sila masukkan id hakmilik yang betul.");
      $('#idHakmilikSiriDari' + id).val('');
      $('#idHakmilikSiriHingga' + id).val('');
    } else {
      var intIndex = 0;
      var pre = "";
      if (val !== '') {
        val = val.toUpperCase();
        $('#idHakmilikSiriDari' + id).val(val);
        bil = bil - 1;
        if (parseInt(bil, 10) > 0) {
          for (intIndex = len - 1; intIndex >= 0; intIndex--) {
            var c = val.charAt(intIndex);
            if (c >= '0' && c <= '9') {
              pre = c + pre;
            } else {
              break;
            }
          }
          var h = val.substring(0, intIndex + 1); //temp  
          var val2 = parseInt(pre, 10) + parseInt(bil);
          var len = (pre.length - String(val2).length);
          if (String(val2).length < pre.length) {
            for (var i = 0; i < len; i++) {
              val2 = "0" + val2;
            }
          }
          h = h + val2;
          if (!isNaN(val2)) {
            $('#idHakmilikSiriHingga' + id).val(h);
          }
        }
      } else {
        $('#idHakmilikSiriHingga' + id).val('');
      }
    }
  }

  function cleartext1() {
    // auto clear text 
    $('#idPermohonan').val("");
  }

  function cleartext2() {
    // auto clear text 
    $('#idHakmilik0').val('');
    $('#idHakmilikSiriHingga0').val('');
    $('#idHakmilikSiriDari0').val('');
  }

  function clearForm() {
    $('#idPermohonan').val('');
    $('#idHakmilik0').val('');
    $('#idHakmilikSiriHingga0').val('');
    $('#idHakmilikSiriDari0').val('');
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
<div class="undoTukarganti">
  <s:messages />
  <s:errors />
  <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
  <s:form beanclass="etanah.view.daftar.utiliti.UndoTukarGantiActionBean" name="form1">
    <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>    
    <fieldset class="aras1">
      <legend>Utiliti Undo Tukarganti </legend>      
      <br>
      <p>
        <label>Id Permohonan : </label>
        <s:text name="idPermohonan" id="idPermohonan" size="25" onblur="cleartext2();"/>
      </p>
      <p>
        <label>&nbsp;</label>
        <em>ATAU</em>
      </p>
      <c:choose>
        <c:when test="${actionBean.kelompok eq 'true'}">
          <%--
          <p>
            <label>Jumlah Hakmilik :</label>
            <s:text name="bilHakmilik" id="bilHakmilik0" size="4" onchange="appendAutoAll('0');" onblur="javascript:nonNumber(this, this.value);"/>
          </p>
          --%>
          <p>
            <label>ID Hakmilik :</label>
            Mula -&nbsp;
            <s:text name="idHakmilikSiriDari[0]" id="idHakmilikSiriDari0" 
                    onblur="cleartext1();this.value=this.value.toUpperCase();" size="22" 
                    onchange="appendAuto2(this.value, '0');"/>
            &nbsp;Hingga -
            <s:text name="idHakmilikSiriHingga[0]" id="idHakmilikSiriHingga0" 
                    onblur="cleartext1();this.value=this.value.toUpperCase();" size="22"
                    onchange="appendAuto2(this.value, '0');"/> 
          </p>        
        </c:when>
        <c:otherwise>
          <%--
          <p>
            <label>Jumlah Hakmilik :</label>
            1 <s:hidden name="bilHakmilik" id="bilHakmilik" value="1"/>
          </p>--%>
          <p>
            <label>ID Hakmilik :</label>
            <s:text name="idHakmilik" id="idHakmilik0" size="25" onblur="cleartext1();"/>
          </p>
        </c:otherwise>
      </c:choose>
      <p>
        <br>
        <label>&nbsp;</label>      
        <c:choose>
          <c:when test="${actionBean.kelompok eq 'true'}">
            <s:submit name="seterusnyaBerkelompok" value="Cari" class="btn" id="seterusnya" />&nbsp; 
            <s:submit name="doCheckHakmilik" value="Sahkan" class="btn" id="sahkan" />&nbsp; 
          </c:when>
          <c:otherwise>
            <s:submit name="seterusnya" value="Cari" class="btn" id="seterusnya" />&nbsp; 
          </c:otherwise>
        </c:choose>     
        <s:button name="reset" value="Isi Semula" class="btn" onclick="clearForm();"/>
      </p>
      <br>
    </fieldset>
    <br>
    <c:if test="${fn:length(actionBean.listHakmilikUrusan) > 0}">      
      <fieldset  class="aras1">
        <legend>Maklumat Tukar Ganti</legend>   
        <s:hidden name="size" value="${fn:length (actionBean.listHakmilikUrusan)}" /> 
        <s:hidden name="idhm" value="${actionBean.idHakmilik}" /> 
        <s:hidden name="idm" value="${actionBean.idPermohonan}" /> 
        <br>
        <div align="center">
          <display:table class="tablecloth" style="width:90%;" id="line" cellpadding="0" cellspacing="0" pagesize="15"
                         requestURI="/daftar/utiliti/undoTukarGanti" name="${actionBean.listHakmilikUrusan}" >
            <display:column title="Bil" style="width:1%;"><div align="right">${line_rowNum}</div></display:column>
            <display:column title="Id Perserahan">${line.idPerserahan}</display:column>
            <display:column title="Id Hakmilik">${line.hakmilik.idHakmilik}</display:column>
            <display:column title="Versi Hakmilik" style="width:10%;"><div align="center">${line.hakmilik.noVersiDhde}</div></display:column>
            <display:column property="infoAudit.dimasukOleh.nama" title="Nama Pegawai" />
            <display:column property="tarikhDaftar" title="Tarikh Daftar" style="width:17%;" format="{0,date,dd/MM/yyyy hh:mm aa}"/>
          </display:table>
        </div>        
        <br>
        
        <p>
          <label><em>*</em>Sebab :</label>
          <s:textarea name="sebab" cols="75" rows="6"/>
        </p>
        <br>
        <p align="center">     
          <s:submit name="undoTukarganti" value="Undo" class="btn" id="undoTukarganti"/>&nbsp;
        </p>
        <br>
      </fieldset>
    </c:if>
  </s:form>
</div>


