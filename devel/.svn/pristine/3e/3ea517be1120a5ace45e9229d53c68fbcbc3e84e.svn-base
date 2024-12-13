<%-- 
    Document   : kutipan_data_sekatanKepentingan
    Created on : Oct 1, 2013, 10:09:19 AM
    Author     : ei
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">    
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<style type="text/css">
  #tdLabel {
    color:#003194;
    display:block;
    font-family:Tahoma;
    font-size:13px;
    font-weight:bold;
    margin-left:100px;
    margin-right:0.5em;
    text-align:right;
    width:15em;
    vertical-align:top;
  }
  #tdDisplay {
    color:black;
    font-size:13px;
    font-weight:normal;
    width:40em;
  }
</style>
<script type="text/javascript">
  $(document).ready(function() {
  <c:if test="${actionBean.refresh}">
    self.close();
    opener.refreshMaklumatDetail();
  </c:if> 

    $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    $("#simpanKodSekatan").click(function() {
      var sekatan = $('input:radio[name=radio_]:checked').val();
      var idHakmilik = $('#idHakmilik').val();
      var url = '${pageContext.request.contextPath}/daftar/utiliti/kutipanData?simpanKodSekatanKepentingan&sekatan=' + sekatan + '&idHakmilik=' + idHakmilik;
      frm = document.form1;
      frm.action = url;
      frm.submit();
    });
    
     $("#simpanKodSekatanBerkelompok").click(function() {
      var sekatan = $('input:radio[name=radio_]:checked').val();
      var idHakmilik = $('#idHakmilik').val();
      var url = '${pageContext.request.contextPath}/daftar/utiliti/kutipanData?simpanKodSekatanBerkelompok&sekatan=' + sekatan + '&idHakmilik=' + idHakmilik;
      frm = document.form1;
      frm.action = url;
      frm.submit();
    });
  });
</script>
<s:errors/>
<s:messages/>
<div class="subtitle">
  <s:form beanclass="etanah.view.daftar.utiliti.KutipanDataActionBean" name="form1">
    <s:hidden name="idHakmilik" id="idHakmilik"/>
    <fieldset class="aras1">          
      <s:hidden name="kumpHm" id="kumpHm" value="${actionBean.kumpHm}"/> 
      <legend>Carian Kod Sekatan</legend>
      <table>
        <tr><td>&nbsp;</td></tr>
        <tr>
          <td id="tdLabel">Kod Sekatan :&nbsp;</td>
          <td id="tdDisplay"><s:text name="kodSekatan" id="kodSekatan"/></td>
        </tr> 
        <tr>
          <td id="tdLabel">Sekatan :&nbsp;</td>
          <td id="tdDisplay"><s:text name="namaSekatan" id="sekatan"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><s:submit name="paparPopupSekatan" value="Cari" class="btn"/></td>
        </tr>
      </table>
    </fieldset>
  </div>
  <br>
  <div class="subtitle">
    <fieldset class="aras1">
      <legend>Carian Kod Sekatan</legend>
      <div align="center">
        <display:table style="width:100%" class="tablecloth" name="${actionBean.lisSekatan}" 
                       excludedParams="popup" pagesize="15" cellpadding="0" cellspacing="0" 
                       requestURI="/daftar/utiliti/kutipanData" id="line">
          <display:column> 
            <s:radio name="radio_" id="radio_" value="${line.kod}"/>
          </display:column>
          <display:column title="kod" property="kodSekatan" />
          <display:column title="Sekatan" property="sekatan" style="text-transform:uppercase;" />
        </display:table>
      </div>
      <br>
      <p align="center">
        <s:button name="simpanKodSekatan" value="Simpan" id="simpanKodSekatan" class="btn"/>&nbsp;
        <c:if test="${fn:length(actionBean.listSenaraiHakmilik) > 1}" >          
          <s:button name="simpanKodSekatanBerkelompok" value="Simpan Bekelompok" id="simpanKodSekatanBerkelompok" class="longbtn"/>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </c:if>
        <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:window.close();"/>
      </p>
      <br>
    </fieldset>
  </s:form>
</div>

