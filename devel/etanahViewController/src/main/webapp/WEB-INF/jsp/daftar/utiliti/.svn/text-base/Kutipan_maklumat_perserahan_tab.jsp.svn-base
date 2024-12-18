<%-- 
    Document   : kutipan_data_tab
    Created on : Sep 24, 2013, 5:06:01 PM
    Author     : ei
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes-security.tld" prefix="ss"%>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<!--<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>-->
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.tabs.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/date.format.js"></script>
<link type="text/css" href="pub/styles/tabNavList.css" rel="stylesheet"/>

<!DOCTYPE html>
<script type="text/javascript">
  /*THIS JAVASCRIPT FIX DISPLAY TAG BUG TO OPEN PROPER TAB AFTER CLICK PAGE NUMEBR ON DISPLAY TAG*/
  $(document).ready(function() {
    $("#tab_dokumen").tabs();
    $("#tab_dokumen").tabs('select', '#' + '${actionBean.selectedTab}');
    $(".datepicker1").datepicker({changeYear: true});
    $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    $('#simpanKelompok').click(function() {
      doBlockUI();
    });
    $('#janaDokumen').click(function() {
      doBlockUI();
    });
    $("#tab_dokumen").tooltip({
      offset: [10, 2],
      effect: 'slide'
    }).dynamic({bottom: {direction: 'down', bounce: true}});
    filterPegangan();


  });

  function refreshMaklumatDetail() {
//    alert("refreshMaklumatDetail");
    var frm = document.forms.kutipanUrusan;
    var url = '${pageContext.request.contextPath}/daftar/utiliti/KemaskiniPerserahanHakmilik?refreshPage&idHakmilik=' + $('#idHakmilik').val();
    frm.action = url;
    frm.submit();
  }

  function refreshkutipanUrusan() {
//      alert("refreshkutipanUrusan");
    var idTab = "maklumat_urusan";
    var frm = document.forms.kutipanUrusan;
    var url = '${pageContext.request.contextPath}/daftar/utiliti/KemaskiniPerserahanHakmilik?refreshPage&selectedTab=' + idTab;
    frm.action = url;
    frm.submit();
  }

  function refreshPemohon() {
//      alert("refreshkutipanUrusan");
    var idTab = "maklumat_urusan";
    var frm = document.forms.kutipanUrusan;
    var url = '${pageContext.request.contextPath}/daftar/utiliti/KemaskiniPerserahanHakmilik?kemaskiniPemohon&selectedTab=' + idTab;
    frm.action = url;
    frm.submit();
  }
</script>
<style type="text/css">
  .cursor_pointer {
    cursor:pointer;
  }
  a.tooltip {
    display:none;
    background:transparent url(${pageContext.request.contextPath}/pub/images/black_arrow.png);
    font-size:12px;
    height:70px;
    width:160px;
    padding:25px;
    color:#fff;
  } 
  /*
  
    a.tooltip2 {outline:none;}
    a.tooltip2 strong {line-height:30px;}
    a.tooltip2:hover {text-decoration:none;} 
    a.tooltip2 span {
      z-index:10;
      display:none; 
      padding:14px 20px;
      margin-top:-70px; 
      margin-left:-70px;
      width:100px; 
      line-height:16px;
    }  
    a.tooltip2:hover span{
      display:inline; 
      position:absolute; 
      color:#111;
      border:1px solid #DCA; 
      background:#fffAF0;
  
      display:none;
      background:transparent url(${pageContext.request.contextPath}/pub/images/black_arrow.png);
      font-size:12px;
      height:70px;
      width:160px;
      padding:25px;
      color:#fff;
  
    }
    .callout {
      z-index:20;
      position:absolute;
      top:30px;
      border:0;
      left:-12px;
    }
  
    CSS3 extras
    a.tooltip2 span {
      border-radius:0px;
      -moz-border-radius: 0px;
      -webkit-border-radius: 0px;
  
      -moz-box-shadow:0 0 10px #000;
      -webkit-box-shadow:0 0 10px #000;
      box-shadow: 0 0 10px #000;
    }*/
</style>
<div id="aa">
  <div class="subtitle">
    <%--<s:errors/>
    <s:messages/>--%>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.daftar.utiliti.KemaskiniPerserahanHakmilikActionBean">
      <fieldset class="aras1">
        <legend>Utiliti Kemaskini Maklumat Perserahan Hakmilik </legend><br>
        <s:hidden name="kumpHm" id="kumpHm" value="${actionBean.kumpHm}"/> 
        <s:hidden name="idHakmilik" id="idHakmilik" value="${actionBean.idHakmilik}"/> 
      </fieldset>  
    </div>   

    <!--        TAB VIEW... -->
    <div id="tab_dokumen" class="tab_dokumen">
      <ul>
        <li><a href="#maklumat_hakmilik" id="tab1" title="Maklumat Hakmilik">Maklumat Hakmilik</a></li>
        <li><a href="#maklumat_cukai" id="tab3" title="Maklumat Cukai">Maklumat Cukai</a></li>  
        <li><a href="#maklumat_urusan" id="tab3" class="tooltip2" title="Maklumat Urusan">Maklumat Urusan</a></li>
        <li><a href="#dokumen_hakmilik" id="tab3" class="tooltip2" title="Maklumat Dokumen Hakmilik">Dokumen Hakmilik
            <!--<span>Maklumat Dokumen Hakmilik</span>-->
          </a>
        </li>

      </ul>
      <div id="maklumat_hakmilik" >        
        <%@ include file="/WEB-INF/jsp/daftar/utiliti/kutipan_maklumat_hakmilik.jsp" %>
      </div>  
      <div id="maklumat_cukai">
        <%@ include file="/WEB-INF/jsp/daftar/utiliti/kutipan_data_cukai.jsp" %>
      </div>
      <div id="maklumat_urusan">
        <%@ include file="/WEB-INF/jsp/daftar/utiliti/kutipan_data_urusan.jsp" %>
      </div>       
      <div id="dokumen_hakmilik">
        <%@ include file="/WEB-INF/jsp/daftar/utiliti/kutipan_data_dokumen.jsp" %>
      </div>
    </div>
  </div>  
</s:form>
</div>
