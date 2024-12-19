<%--
    Document   : editHakmilik
    Created on : Feb 23, 2010, 2:05:49 PM
    Author     : wan.fairul
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<style type="text/css">
  td.s{
    font-weight:normal;
    color:black;
    text-align: left;
  }
</style>
<script type="text/javascript">
  $(document).ready(function() {
    dialogInit('Carian Hakmilik');
    $('input:text').each(function() {
      $(this).focus(function() {
        $(this).addClass('focus')
      });
      $(this).blur(function() {
        $(this).removeClass('focus')
      });
    });

    $('form').submit(function() {
      $.blockUI({
        message: $('#displayBox'),
        css: {
          top: ($(window).height() - 50) / 2 + 'px',
          left: ($(window).width() - 50) / 2 + 'px',
          width: '50px'
        }
      });
    });
  });

  function validateHakmilik() {
    var val = $("#idHakmilikBaru").val();
    frm = this.form;
    if (val == null || val == "")
      return;
    val = val.toUpperCase();
    $("#idHakmilikBaru").val(val);

    $.get("${pageContext.request.contextPath}/daftar/check_idhakmilik?doCheckHakmilik&idHakmilikBaru=" + val,
            function(data) {
              if (data == '1') {
                //$("#msg" + idxHakmilik).html("OK");
              } else if (data == '0') {
                // disable if invalid Hakmilik
                // $("#collectPayment").attr("disabled", "true");
                $("#idHakmilikBaru").val("");
                alert("ID Hakmilik " + val + " tidak wujud!");
                // $("#msg" + idxHakmilik).html("<font color=red>ID Hakmilik " + val + " tidak wujud!</font>");
              } else if (data == '2') {
                // disable if invalid Hakmilik
                // $("#collectPayment").attr("disabled", "true");
                //$("#hakmilik" + idxHakmilik).val("");
                alert("Cukai untuk Hakmilik " + val + " masih belum dijelaskan!");
              } else if (data == '3') {
                $("#idHakmilikBaru").val("");
                alert("Status hakmilik Provisional. Tiada Urusniaga / Kaveat Persendirian / Lien dibenarkan.");
              } else {
                alert(data);
              }
            });
  }
</script>
<script language="javascript">
  function save(event, f, idUrusan) {
    doBlockUI();
    var q = $(f).formSerialize();
    var url = f.action + '?' + event + '&idUrusan=' + idUrusan;
    $.post(url, q,
            function(data) {
              $('#page_div', opener.document).html(data);
              self.close();
            }, 'html');
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
<script>
  function RemoveNonNumeric(strString) {
    var strValidCharacters = "123456789.";
    var strReturn = "0";
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
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none" alt=""/>
<s:form name="form1" beanclass="etanah.view.stripes.nota.pembetulanActionBean">  
  <s:hidden name="idUrusan" value="${actionBean.idUrusan}"/>  <%-- use this get idUrusan --%>
  <s:hidden name="idMohon" value="${actionBean.idMohon}"/>  
  <s:hidden name="idHakmilikBaruTemp" value="${actionBean.idHakmilikBaru}"/>  <%-- use this to store temp id_hakmilik incase user try to change id_hakmilik --%>
  <s:messages />
  <s:errors />
  <div class="subtitle">
    <fieldset class="aras1">
      <legend>
        Maklumat Hakmilik Baru
      </legend>
      <p style="color:red">
        *Isi ruang pembetulan kemudian tekan butang simpan.<br/>
      </p>
      <br>
      <p>
        <label>ID Hakmilik Baru:</label>
        <s:text id="idHakmilikBaru" name="idHakmilikBaru" onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"  onkeyup="this.value=this.value.toUpperCase();"/>
        <%--<input type="button" value="Sahkan" onclick="validateHakmilik()" class="btn" />--%>
      </p>
      <table style="margin-left: auto; margin-right: auto;">
        <tr>
          <td>&nbsp;</td>
          <td>
            <div>
              <br>
              <s:button name="betulSilapEndosan" value="Simpan" class="btn" onclick="save(this.name, this.form, '${actionBean.idUrusan}')"/>
              <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:self.close()"/>
            </div>
          </td>
        </tr>
      </table>
      <br/>
    </fieldset>
  </div>
</s:form>