<%-- 
    Document   : tab_kemaskini_data
    Created on : May 5, 2011, 2:46:37 PM
    Author     : abdul.hakim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.tabs.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet"
      href="pub/styles/tabNavList.css"/>
<style type="text/css">
    .cursor_pointer {
        cursor:pointer;
    }
</style>
<script type="text/javascript">
    /*THIS JAVASCRIPT FIX DISPLAY TAG BUG TO OPEN PROPER TAB AFTER CLICK PAGE NUMEBR ON DISPLAY TAG*/
    $(document).ready(function() {
        $("#tab_hakmilik").tabs();
        $("#tab_hakmilik").tabs('select','#'+'${actionBean.selectedTab}');
        var boo = ${actionBean.checkingBaki};
        if(boo){
            alert("Baki akaun tidak sama dengan nilai transaksi. Sila klik butang Kemaskini untuk mengemaskini baki akaun.");
        }
    });

    function refreshRekod(noAkaun){
        var url = '${pageContext.request.contextPath}/hasil/kemaskini_data?reloadPage&idTrans='+noAkaun;
        $.get(url,
        function(data){
            $('#aa').html(data);
        }, 'html');
    }
</script>
<div id="aa">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
        <s:form beanclass="etanah.view.stripes.hasil.KemaskiniMaklumatAkaunActionBean" id="kemaskini_rekod">
            <s:hidden name="kodDaerah" value="${actionBean.kodDaerah}"/>
            <s:hidden name="kodHakmilik" value="${actionBean.kodHakmilik}"/>
            <s:hidden name="caw" value="${actionBean.caw}"/>
        </s:form>
    </div>
    <br>
    <div id="tab_hakmilik">
        <ul>
            <li><a href="#maklumat_asas" id="tab1">Kemaskini Maklumat</a></li>
            <li><a href="#sej_kemaskini" id="tab2">Sejarah Kemaskini</a></li>
        </ul>
        <div id="maklumat_asas">
            <%@ include file="/WEB-INF/jsp/hasil/kemaskini_maklumat_akaun_1.jsp" %>
        </div>
        <div id="sej_kemaskini">
            <%@ include file="/WEB-INF/jsp/hasil/sejarah_kemaskini_maklumat_akaun.jsp" %>
        </div>
    </div>
</div>


