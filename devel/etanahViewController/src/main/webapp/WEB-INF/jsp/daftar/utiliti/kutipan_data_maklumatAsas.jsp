<%-- 
    Document   : kutipan_data_maklumatAsas
    Created on : Oct 18, 2013, 12:52:36 PM
    Author     : ei
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
  $(document).ready(function() {
    $(".datepicker1").datepicker({changeYear: true});
    $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

  <c:if test="${actionBean.idHakmilik ne null}">
    showHm($('#idHakmilik2').val());
  </c:if>
    });

    function selectAll1(a) {
      // TICK ALL HAKMILIK
      var size = '${fn:length(actionBean.listSenaraiHakmilik)}';
      for (i = 0; i < size; i++) {
        var c = document.getElementById("checkbox1" + i);
        if (c === null)
          break;
        c.checked = a.checked;
      }
    }

    function simpanMaklumatAsas() {
      // DELETE CHECKED HAKMILIK
//      if (confirm('Adakah anda pasti untuk hapus hakmilik ini')) {
      var param = '';
      $('.idAsas').each(function(index) {
        var a = $('#checkbox1' + index).is(":checked");
        if (a) {
          param = param + '&idAsas=' + $('#checkbox1' + index).val();
        }
      });

      if (param === '') {
        alert('Sila Pilih Hakmilik terlebih dahulu.');
        return;
      }

      var url = '${pageContext.request.contextPath}/daftar/utiliti/kutipanData?simpanMakluamtAsas' + param;
      frm = document.maklumatAsas;
      frm.action = url;
      frm.submit();
//      }
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
<!DOCTYPE html>
<div class="a">
  <s:errors/>
  <s:messages/>
  <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
  <s:form beanclass="etanah.view.daftar.utiliti.KutipanDataActionBean" name="maklumatAsas" id="maklumatAsas">
    <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
    <div class="maklumatAsas">
      <fieldset class="aras1">
        <s:hidden name="idHakmilik" id="idHakmilik2" />
        <s:hidden name="kumpHm" id="kumpHm" />
        <lagend>Maklumat Asas Hakmilik</lagend>
        <br><br>        
        <p align="center">
          <display:table class="tablecloth" style="width:90%;" id="line2" cellpadding="0" cellspacing="0"
                         requestURI="/daftar/utiliti/kutipanData" name="${actionBean.listSenaraiHakmilik}" >
            <%--<display:column title="<input type='checkbox' name='semua' onclick='javascript:selectAll1(this);'>">
              <s:checkbox name="checkbox1" id="checkbox1${line2_rowNum-1}" value="${line2.idHakmilik}" class="idAsas"/>
            </display:column>--%>
            <display:column title="No" sortable="true">${line2_rowNum}</display:column>
            <display:column title="ID Hakmilik">    
              ${line2.idHakmilik}
              <s:hidden name="listSenaraiHakmilik[${line2_rowNum-1}].idHakmilik" id="hiddenIdHakmilik${line2_rowNum-1}" value="${line2.idHakmilik}"/>
            </display:column>  
            <display:column title="Taraf Pegangan">    
              <s:select style="text-transform:uppercase;width:130pt" name="listSenaraiHakmilik[${line2_rowNum-1}].pegangan" 
                        id="pegangan" value="${line2.pegangan}">            
                <s:option value="P">Pajakan</s:option>
                <s:option value="S">Selama - lamanya</s:option>                   
              </s:select>
            </display:column> 
            <display:column title="Tarikh Daftar ">  
              <s:text name="listSenaraiHakmilik[${line2_rowNum-1}].tarikhDaftar" class="datepicker1" id="tarikhDaftar" 
                      formatPattern="dd/MM/yyyy" formatType="date"
                      value="${line2.tarikhDaftar}" size="12"/>
            </display:column> 
            <display:column title="Tempoh">    
              <s:text name="listSenaraiHakmilik[${line2_rowNum-1}].tempohPegangan" id="tempohPegangan" onchange="doCalcEndDate('tarikhDaftarAsal');" 
                      value="${line2tempohPegangan}" size="4" maxlength="3" />Tahun   
            </display:column> 
            <display:column title="Tarikh Luput">   
              <s:text name="listSenaraiHakmilik[${line2_rowNum-1}].tarikhLuput" class="datepicker1" id="tkhTamat" 
                      formatPattern="dd/MM/yyyy" formatType="date"
                      value="${line2.tarikhLuput}" size="13"/>              
            </display:column> 
            <display:column title="No PU">   
              <input type="hidden" value="${line2.noPu}" id="hiddenNoPu" class="uppercase"/>
              <s:text name="listSenaraiHakmilik[${line2_rowNum-1}].noPu" id="noPu" value="${line2.noPu}" size="20"/>
            </display:column> 
          </display:table>
        </p>
        <br>
        <p align="center">          
          <s:submit class="longbtn" value="Simpan Maklumat" name="simpanMakluamtAsas" />
        </p>
        <br>
      </fieldset>
    </div>
    <br>       
  </s:form>        
</div>