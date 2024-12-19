<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes-security.tld" prefix="ss"%>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<!--<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.tabs.js"></script>-->
<!--<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>-->
<!--<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>-->
<!--<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>-->
<script type="text/javascript">
    function detail(val) {
        var idHakmilikInduk = '${actionBean.hakmilik.idHakmilikInduk}';
        var url = '${pageContext.request.contextPath}/common/maklumat_permohonan?view&idPermohonan=' + val +'&idHakmilik='+idHakmilikInduk;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=768,scrollbars=yes");
    }
</script>
<div class="subtitle">
    <fieldset class="aras1">
        <legend>Maklumat Urusan Yang Berkuatkuasa Di Atas Hakmilik Indukss (${actionBean.hakmilik.idHakmilikInduk})</legend>
        <div class="content" align="center" id="listpihak">
            <display:table class="tablecloth" style="width:90%;" name="${actionBean.hakmilikUrusanIndukList}"
                           requestURI="/common/carian_hakmilik?&selectedTab=maklumat_urusan" excludedParams="selectedTab" pagesize="10" cellpadding="0" cellspacing="0" id="linemohonpihak">
                <display:column title="Bil" sortable="true">${linemohonpihak_rowNum}</display:column>
                <display:column title="ID Perserahan">
                    <a href="#" onClick="detail('${linemohonpihak.idPerserahan}');">${linemohonpihak.idPerserahan}</a>
                </display:column>                
                <display:column property="kodUrusan.kod" title="Kod Urusan" class="urusan" />
                <display:column property="kodUrusan.nama" title="Nama Urusan" class="urusan" />
                <display:column property="tarikhDaftar" title="Tarikh Daftar" format="{0,date,dd/MM/yyyy   hh:mm aa}" />
            </display:table>
        </div>

    </fieldset>
</div>
