<%-- 
    Document   : kutipan_data_syaratNyata
    Created on : Sep 26, 2013, 10:13:29 PM
    Author     : ei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Carian Kod Syarat Nyata</title>
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
//        maximizeWindow();
      <c:if test="${actionBean.refresh}">
        self.close();
        opener.refreshMaklumatDetail();
      </c:if>
          $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
          $("#simpanKodSyaratNyata").click(function() {
            var syaratNyata = $('input:radio[name=radio_]:checked').val();
            var idHakmilik = $('#idHakmilik').val();
            var url = '${pageContext.request.contextPath}/daftar/utiliti/kutipanData?simpanKodSyaratNyata&syaratNyata=' + syaratNyata + '&idHakmilik=' + idHakmilik;
            frm = document.form1;
            frm.action = url;
            frm.submit();
          });

          $("#simpanKodSyaratNyataBerkelompok").click(function() {
            var syaratNyata = $('input:radio[name=radio_]:checked').val();
            var idHakmilik = $('#idHakmilik').val();
            var url = '${pageContext.request.contextPath}/daftar/utiliti/kutipanData?simpanKodSyaratNyataBerkelompok&syaratNyata=' + syaratNyata + '&idHakmilik=' + idHakmilik;
            frm = document.form1;
            frm.action = url;
            frm.submit();
          });
        });
    </script>
  </head>
  <body>
    <s:errors/>
    <s:messages/>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>  
    <div class="subtitle">
      <s:form beanclass="etanah.view.daftar.utiliti.KutipanDataActionBean" name="form1">
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <fieldset class="aras1">
          <legend>Carian Kod Syarat Nyata</legend>
          <s:hidden name="kumpHm" id="kumpHm" value="${actionBean.kumpHm}"/> 
          <table align="center">
            <tr><td>&nbsp;</td></tr>
            <tr>
              <td id="tdLabel">Kod Syarat Nyata :&nbsp;</td>
              <td id="tdDisplay"><s:text name="kodSyarat" id="kodSyaratNyata" size="32"/></td>
            </tr>     
            <tr>
              <td id="tdLabel">Kategori Tanah :&nbsp;</td>
              <td id="tdDisplay">
                <s:select style="text-transform:uppercase" name="kodKatTanah">
                  <s:option value="">Sila Pilih</s:option>
                  <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" />
                </s:select>
              </td>
            </tr> 
            <%--<tr>
              <td id="tdLabel">Syarat Nyata :&nbsp;</td>
              <td id="tdDisplay"><s:text name="syaratNyata" id="syaratNyata"/>  </td>
            </tr>--%>            
            <tr>
              <td>&nbsp;</td>
              <td id="tdDisplay">
                <s:submit name="paparPopupSyaratNyata" value="Cari" class="btn"/>
              </td>
            </tr>
          </table>
          <br>
        </fieldset>
      </div>
      <br>
      <div class="subtitle">
        <fieldset class="aras1">
          <legend>Maklumat Kod Syarat Nyata</legend><br>      
          <div align="center">
            <display:table style="width:90%;" class="tablecloth" id="line"                             
                           pagesize="15" cellpadding="0" cellspacing="0" 
                           name="${actionBean.lisSyaratNyata}" 
                           requestURI="/daftar/utiliti/kutipanData">
              <display:column> 
                <s:radio name="radio_" id="radio_" value="${line.kod}"/>                
              </display:column>
              <display:column title="Kod" property="kodSyarat"/>
              <display:column title="Syarat" property="syarat" style="text-transform:uppercase;"/>
            </display:table>   
          </div>
          <c:if test="${fn:length(actionBean.lisSyaratNyata) > 0}" >   
            <br>            
            <p align="center">
              <s:button name="simpanKodSyaratNyata" value="Simpan" id="simpanKodSyaratNyata" class="btn" onclick="save(this.form,this.name)"/>&nbsp;              
              <c:if test="${fn:length(actionBean.listSenaraiHakmilik) > 1}" >
                <s:button name="simpanKodSyaratNyataBerkelompok" value="Simpan Bekelompok" id="simpanKodSyaratNyataBerkelompok" class="longbtn"/>&nbsp;
              </c:if>
              &nbsp;&nbsp;&nbsp;&nbsp;
              <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:window.close();"/>
            </p>
          </c:if>
          <br>
        </fieldset>
      </s:form>
    </div>
  </body>
</html>
