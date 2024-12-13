<%-- 
    Document   : maklumat_urusan_proses
    Created on : Apr 9, 2010, 5:37:06 PM
    Author     : khairil
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    $(document).ready( function() {
        var len = $(".urusan").length;

        for (var i=0; i<=len; i++){
            $('.perserahan'+i).click( function() {
                window.open("${pageContext.request.contextPath}/common/maklumat_permohonan?view&idPermohonan="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }
    });
    
    function doPopup(val, idHakmilik) {
        var url = '${pageContext.request.contextPath}/common/maklumat_permohonan?view&idPermohonan=' + val +'&idHakmilik='+idHakmilik;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=890,height=600,scrollbars=yes");
    }
//    function doPopup(val) {
//        var url = '${pageContext.request.contextPath}/common/maklumat_permohonan?view&idPermohonan=' + val;
//        window.open(url, "eTanah", strWindowFeatures);
//    }
</script>
<div class="subtitle">
    <fieldset class="aras1">
        <legend>Maklumat Urusan dalam Proses</legend>

        <div class="content" align="center" id="listpihak">
            <%-- <display:table class="tablecloth" style="width:90%;" name="${actionBean.hakmilikUrusanProsesList}" cellpadding="0" cellspacing="0" id="linemohonpihak">
                 <display:column title="Bil" sortable="true">${linemohonpihak_rowNum}</display:column>
                 <display:column property="idPerserahan" title="ID Perserahan"/>
                 <display:column property="tarikhDaftar" format="{0,date,dd-MM-yyyy hh:ss}" title="Tarikh Daftar" />
                 <display:column property="kodUrusan.kod" title="Urusan" />
                 <display:column title="Status">
                     <c:if test="${linemohonpihak.aktif eq 'Y'}">
                         Aktif
                     </c:if>
                     <c:if test="${linemohonpihak.aktif ne 'Y'}">
                         Tidak Aktif
                     </c:if>
                 </display:column>
             </display:table>--%>

            <display:table class="tablecloth" name="${actionBean.senaraiUrusanProsesDistinct}" requestURI="/common/carian_hakmilik?&selectedTab=maklumat_urusan_proses" excludedParams="selectedTab"
                           cellpadding="0" cellspacing="0" id="line3" style="width:90%" pagesize="10">
                <display:column title="Bil">${line3_rowNum}</display:column>
                 <display:column title="ID Perserahan">
                     <a href="#" onClick="doPopup('${line3.idPermohonan}','${actionBean.idHakmilik}');">${line3.idPermohonan}</a>
                 </display:column>
                <display:column property="kodUrusan" title="Kod Urusan"/>
                <display:column property="hakmilik.idHakmilik" title="Id Hakmilik"/>
                <display:column property="urusan" title="Urusan" class="urusan"/>
                <display:column property="tarikhMula" title="Tarikh Perserahan" format="{0,date,dd/MM/yyyy    hh:ss}" />
                <display:column property="stage" title="Status"/>
            </display:table>
        </div>

    </fieldset>
</div>