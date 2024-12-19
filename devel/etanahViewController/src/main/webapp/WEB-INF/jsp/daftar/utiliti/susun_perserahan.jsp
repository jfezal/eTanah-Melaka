<%-- 
    Document   : susun_perserahan
    Created on : Apr 9, 2010, 11:39:21 AM
    Author     : fikri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/timepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.jtimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
  function doSearch(f, e) {
    var v = $('#idPermohonan').val();
    if (v == '') {
      alert('Sila Isi ID Perserahan.');
      $('#idPermohonan').focus();
      return;
    }
    f.action = f.action + '?' + e;
    f.submit();
  }
  function doSusun(f, e) {
    var queryString = $(f).formSerialize();
    alert(queryString);
    $.ajax({
      type: "POST",
      url: '${pageContext.request.contextPath}/daftar/susun_perserahan?' + e,
      dataType: 'html',
      data: queryString,
      success: function(data) {
        alert(data);
      }
    });

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
    for (intIndex = 0; intIndex < strString.length; intIndex++) {
      strBuffer = strString.substr(intIndex, 1);
      // Is this a number
      if (strValidCharacters.indexOf(strBuffer) > -1) {
        strReturn += strBuffer;
      }
    }
    return strReturn;
  }

  function clearForm(f) {
    $('#idPermohonan').val('');
    $('#strDate').val('');
  }

  $(function() {

  <%--   $('.datepicker').datepicker({
         duration: '',
         dateFormat: 'dd/mm/yy',
         showTime: false,
         constrainInput: false
     });
     $('.datetime').datepicker({
         duration: '',
         dateFormat: 'dd/mm/yy',
         showTime: true,
         time24h: false,
         constrainInput: false
     });--%>

         $('.susun').change(function() {
           var a = $(this).val();
           var c = $(this).attr('id');
           var f = c.substring(6);
           if (a > $('.susun').length) {
             alert('Melebihi jumlah perserahan');
             $(this).val($('#susunB' + f).val());
             return;
           }

//            $('.susun').each(function(i){               
//                var b = $('#susunB' + i).val();                
//                if ( a == b ) {                    
//                    $('#susunA' + i).val( $('#susunB' + f).val() );
//                } else if ('susunA'+i != 'susunA' + f) {
//                    $('#susunA' + i).val($('#susunB' + i).val());
//                }
//            });
         });
       });
</script>           
<div class="subtitile">

  <s:errors/>
  <s:messages/>
  <s:form beanclass="etanah.view.daftar.utiliti.SusunPerserahanActionBean">
    <span class=instr>Susun semula Perserahan hanya untuk senarai berangkai.
      Medan bertanda <em>*</em> adalah mandatori.</span><br/>
    <fieldset class="aras1">
      <legend>Carian Perserahan</legend>
      <p>
        <label><em>*</em>ID Perserahan :</label>
        <s:text name="idPermohonan" id="idPermohonan"  onkeyup="this.value=this.value.toUpperCase();"/>
      </p>
      <p>
        <label>Tarikh :</label>
        <s:text name="strDate" class="datepicker" id="strDate"/>
      </p>
      <p>
        <label>&nbsp;</label>
        <s:button name="search" value="Cari" onclick="doSearch(this.form, this.name);" class="btn"/>
        <s:button name="reset" value="Isi Semula" onclick="clearForm(this.form);" class="btn"/>
      </p>
      <br/>
    </fieldset>
    <br/>
    <fieldset class="aras1">
      <legend>Keputusan Carian</legend>
      <p align="center">
        <label></label>
        <display:table class="tablecloth" name="${actionBean.senaraiPerserahan}" requestURI="/daftar/senarai_keutamaan"
                       cellpadding="0" cellspacing="0" id="line1" style="width:90%" pagesize="10">
          <display:column title="No">${line1_rowNum}</display:column>
          <%--<display:column property="kodUrusan.kod" title="Kod Urusan"/>--%>
          <display:column title="Susun Atur">
            <s:text name="dt[${line1_rowNum-1}]" size="10" class="susun" id="susunA${line1_rowNum-1}" onkeyup="validateNumber(this,this.value);"/>
            <s:hidden name="susunAturAsal" value="${line1_rowNum}" id="susunB${line1_rowNum-1}"/>
          </display:column>
          <display:column property="idPermohonan" title="ID Permohonan"/>                    
          <%--<display:column title="Tarikh Perserahan" property="infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}"/>--%>                        
          <%--<s:text name="dt[${line1_rowNum-1}]" class="datetime"/>--%>
          <%--<s:text name="hours[${line1_rowNum-1}]" size="2" class="slider"/>:
          <s:text name="minutes[${line1_rowNum-1}]" size="2" class="slider"/>:
          <s:text name="seconds[${line1_rowNum-1}]" size="2" class="slider"/>--%>
          <%--</display:column>--%>
          <display:column title="Urusan">
            ${line1.kodUrusan.nama}(${line1.kodUrusan.kod})
            <s:hidden name="ids[${line1_rowNum-1}]"/>
          </display:column>
        </display:table>
      </p>
      <c:if test="${fn:length(actionBean.senaraiPerserahan) > 0}">
        <p>
          <label>&nbsp;</label>
          <s:submit name="susunSemula" value="Susun" onclick="susunSemula(this.form, this.name);" class="btn"/>
        </p>
      </c:if>
    </fieldset>
  </s:form>
</div>
