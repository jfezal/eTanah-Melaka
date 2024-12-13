<%-- 
    Document   : pembatalan_permohonan
    Created on : Oct 19, 2011, 3:02:34 PM
    Author     : mdizzat
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes-security.tld" prefix="ss"%>
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
    });

    function edit(f, id1){
        var queryString = $(f).formSerialize();

        window.open("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?hakmilikDetail&"+queryString+"&hakmilik.idHakmilik="+id1, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1035,height=200");
    }
    function edit3(f, id1){

        var queryString = $(f).formSerialize()

        window.open("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?hakmilikPindahan&"+queryString+"&dokumenKewangan.idDokumenKewangan="+id1, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1035,height=200");
    }

    function edit1(f, id1){
        var queryString = $(f).formSerialize()

        window.open("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?cetak&"+queryString+"&idHakmilik="+id1, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    }
    function editMaklumat(f, id1 ,id2,noAkaun){
        // var queryString = $(f).formSerialize()
        window.open("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?pembayarDetail&idPihak="+id1+"&idHakmilik="+id2+"&noAkaun="+noAkaun, "eTanah",
        "status=0,toolbar=0,resizable=1,scrollbars=1,location=0,menubar=0,width=1035,height=420");
    }


    function refreshPage1(f){
        var idHakmilik = $("#idHakmilik").val();
        var queryString = $(f).formSerialize();
        var url = "${pageContext.request.contextPath}/common/carian_hakmilik?papar&idHakmilik="+idHakmilik+"&popup=true&"+queryString;
        $.get(url,
        function(data){
            $('#aa').html(data);
        },'html');

    }
    
    function refreshingPagingFolder(idPermohonan){
        var url = "${pageContext.request.contextPath}/lelong/pembatalan_permohonan?refresh&idPermohonan="+idPermohonan;
        window.location = url;
    }

</script>
<div id="aa">
    <s:form beanclass="etanah.view.stripes.lelong.PembatalanPermohonanUtiliti">
        <s:messages />
        <s:errors />
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Batal Permohonan Perintah Jual 
                </legend><br>
                <p>
                    <label> ID Permohonan :</label> ${actionBean.permohonan.idPermohonan} 
                </p><br>
            </fieldset><br>
        </div>
        <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
    </s:form>

    <br>
    <div id="tab_hakmilik">
        <ul>
            <li><a href="#dokumen" id="tab1">Dokumen</a></li>
            <li><a href="#ulasan" id="tab2">Ulasan</a></li>
        </ul>

        <div id="dokumen">
            <%@ include file="/WEB-INF/jsp/lelong/dokumen_utiliti.jsp" %>
        </div>
        <div id="ulasan">
            <%@ include file="/WEB-INF/jsp/lelong/ulasan_utiliti.jsp" %>
        </div>
    </div>
</div>