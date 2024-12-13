<%-- 
    Document   : kemaskini_notis
    Created on : Nov 6, 2012, 12:08:44 AM
    Author     : ei \m/
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">        
    <title>Kemaskini Notis</title>        
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
    <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
    <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
    <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
    <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
    <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
    <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
    <script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>        
    <script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>        
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
    <script type="text/javascript">
      function editNotis(idNotis, idPermohonan, warta, idHakmilik) {
        doBlockUI();
        var left = (screen.width / 2) - (1000 / 2);
        var top = (screen.height / 6) - (150 / 6);
        var url = '${pageContext.request.contextPath}/utiliti/kemaskini_Notis?editNotisPopup&idNotis=' + idNotis + '&idPermohonan=' + idPermohonan + '&warta=' + warta + '&idHakmilik=' + idHakmilik;
        window.open(url, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500, left=" + left + ",top=" + top);
//        $.unblockUI();
      }

      function refreshKemaskiniNotis() {
        doBlockUI();
        var frm = document.forms.kemaskiniNotis;
        var url = '${pageContext.request.contextPath}/utiliti/kemaskini_Notis?checkPermohonan';
        frm.action = url;
        frm.submit();
        undoBlockUI();
      }
      
      function TambahPenghantarNotis() {        
              
        window.open("${pageContext.request.contextPath}/penghantarnotis", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
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
  </head>
  <body>
    <s:messages />
    <s:errors />
    <s:useActionBean beanclass="etanah.view.daftar.utiliti.KemaskiniNotisActionBean" var="kemasNotis" />
    <s:form action="/utiliti/kemaskini_Notis" name="kemaskiniNotis" id="kemaskiniNotis">
      <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none" alt=""/>
      <div class="kemaskiniNotis">
        <c:if test="${actionBean.idMohonNotis ne null}">
          <fieldset class="aras1">
            <legend>Maklumat Notis : ${actionBean.permohonan.idPermohonan}</legend><br>            
            <p>
              <label for="idPermohonan">Id Permohonan/ID Perserahan :</label>
              ${actionBean.permohonan.idPermohonan}
              <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
            </p>
            <p>
              <label for="kodUrusan">Kod Urusan / Urusan :</label>
              ${actionBean.permohonan.kodUrusan.kod} / ${actionBean.permohonan.kodUrusan.nama}
            </p>
            <p>
              <label for="penyerah">Nama Penyerah :</label>
              ${actionBean.permohonan.penyerahNama}
            </p><br>
          </fieldset>
        </c:if>

        <c:if test="${actionBean.idMohonNotis eq null}">
          <fieldset class="aras1">
            <legend>Maklumat Notis</legend><br>            
            <p>
              <label for="namaNotis">Jenis Notis :</label>
              ${actionBean.senaraiNotis.kodNotis.nama}
              <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
            </p>                
            <p>
              <label for="kodNotis">Kod Notis :</label>
              ${actionBean.kodNotis}
            </p>
            <br>
          </fieldset>                
        </c:if>

        <fieldset class="aras1">            
          <legend>Carian Maklumat Notis</legend><br>
          <div align="center">                
            <p>
              <display:table class="tablecloth" name="${actionBean.listNotis}" 
                             cellpadding="0" cellspacing="0" pagesize="10" 
                             requestURI="/utiliti/kemaskini_Notis" id="line">                            
                <display:column title="Bil" value="${line_rowNum}"/>
                <c:if test="${actionBean.idMohonNotis eq null}">
                  <display:column property="permohonan.idPermohonan" title="Id Permohonan" class="${line_rowNum}"/>
                </c:if>
                <display:column property="kodNotis.nama" title="Jenis Notis" class="rowCount" style="width:100px; text-align:center;"/>                                  
                <display:column property="warta.hakmilik.idHakmilik" title="ID Hakmilik" class="rowCount" style="width:100px; text-align:center;"/>
                <display:column property="warta.hakmilik.noLot" title="No Lot" class="rowCount" style="width:100px; text-align:center;"/>
                <display:column property="warta.hakmilik.lot.nama" title="Jenis Lot" class="rowCount" style="width:100px; text-align:center;"/>
                <display:column title="Tarikh Notis" class="${line_rowNum}" sortable="true" style="width:100px; text-align:center;">
                  <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhNotis}"/>
                </display:column>
                <%--<display:column title="id warta" property="warta.idRujukan"/> --%>
                <display:column title="Nama Penghantar Notis" property="penghantarNotis.nama"/>   
                <display:column property="kodCaraPenghantaran.nama" title="Cara Penghantaran" class="${line_rowNum}"/>                            
                <display:column title="Kemaskini" style="width:100px; text-align:center;">
                  <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' 
                       class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';" 
                       onclick="editNotis('${line.idNotis}', '${line.permohonan.idPermohonan}', '${line.warta.idRujukan}', '${line.warta.hakmilik.idHakmilik}')"/>     
                  <img alt='Tambah Penghantar Notis Baru' border='0' src='${pageContext.request.contextPath}/pub/images/tambah.png' 
                       class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';" 
                       onclick="TambahPenghantarNotis()"/>  
                </display:column>                          
              </display:table>
            </p>
            <p align="center"><br>
              <s:hidden name="permohonan.idPermohonan" id="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
              <s:hidden name="jenisNotis" value="${actionBean.kodNotis}" id="jenisNotis"/>
              <s:submit name="checkPermohonan" value="Papar Semula" class="btn" />
            </p>      
          </div><br>
        </fieldset>  
      </div>
    </s:form>
  </body>
</html>
