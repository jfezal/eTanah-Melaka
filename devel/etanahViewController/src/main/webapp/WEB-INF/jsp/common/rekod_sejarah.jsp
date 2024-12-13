<%-- 
    Document   : rekod_sejarah
    Created on : Apr 9, 2010, 5:19:30 PM
    Author     : wan.fairul
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(document).ready( function() {
        var len = $(".hakmilik").length;

        for (var i=0; i<=len; i++){
            $('.perserahan'+i).click( function() {
                window.open("${pageContext.request.contextPath}/common/maklumat_permohonan?view&idPermohonan="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600,scrollbars=yes");
            });
        }
    });
    <%--    function doPopup(val) {
            var url = '${pageContext.request.contextPath}/common/maklumat_permohonan?view&idPermohonan=' + val;
            window.open(url, "eTanah", strWindowFeatures);
        }--%>

            function doPopup(val) {
                var url = '${pageContext.request.contextPath}/common/maklumat_permohonan?view&idPermohonan=' + val +'&idHakmilik='+idHakmilik;
                window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=890,height=600,scrollbars=yes");
            }
</script>
<div class="subtitle">
    <fieldset class="aras1">
        <legend>Rekod Sejarah</legend>
        <br>
        <legend>Maklumat Urusan Yang Tidak Berkuatkuasa</legend>

        <div class="content" align="center" id="listpihak">
            <display:table class="tablecloth" style="width:90%;" name="${actionBean.hakmilikUrusanListTaktif}"
                           requestURI="/common/carian_hakmilik?&selectedTab=7" excludedParams="selectedTab" pagesize="10" cellpadding="0" cellspacing="0" id="linemohonpihak">
                <display:column title="Bil" sortable="true">${linemohonpihak_rowNum}</display:column>
                <%--<display:column property="idPerserahan" title="ID Perserahan" class="popup perserahan${linemohonpihak_rowNum}"/>--%>
                <display:column title="ID Perserahan">
                    <a href="#" onClick="doPopup('${linemohonpihak.idPerserahan}');">${linemohonpihak.idPerserahan}</a>
                </display:column> 
                <display:column property="kodUrusan.kod" title="Urusan" class="urusan" />
                <display:column property="kodUrusan.nama" title="Nama Urusan" class="urusan" />
                <display:column property="tarikhDaftar" title="Tarikh Daftar" format="{0,date,dd/MM/yyyy    hh:mm:ss a}" />
                <%-- <display:column title="Status">
                     <c:if test="${linemohonpihak.aktif eq 'Y'}">
                         Daftar
                     </c:if>
                     <c:if test="${linemohonpihak.aktif ne 'Y'}">
                         Tidak Berkuatkuasa
                     </c:if>
                 </display:column>--%>
            </display:table>
        </div>
    </fieldset>
    <br>
    <fieldset class="aras1">
        <legend>Maklumat Urusan Yang Di Tolak/Gantung</legend>
        <div class="content" align="center" >
            <display:table class="tablecloth" style="width:90%;" name="${actionBean.mohonTolakGantung}"
                           requestURI="/common/carian_hakmilik?&selectedTab=7" excludedParams="selectedTab" pagesize="10" cellpadding="0" cellspacing="0" id="linemohon">
                <display:column title="Bil" sortable="true">${linemohon_rowNum}</display:column>
                <display:column title="ID Perserahan">
                    <a href="#" onClick="doPopup('${linemohon.idPermohonan}');">${linemohon.idPermohonan}</a>
                </display:column>
                <display:column title="Kod Urusan">
                    ${linemohon.kodUrusan.kod}
                </display:column>
                <display:column title="Nama Urusan">
                    ${linemohon.kodUrusan.nama}
                </display:column>
                <display:column title="Tarikh Perserahan">
                    <fmt:formatDate value="${linemohon.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm:ss"/>
                </display:column>
                <display:column title="Status">
                    <c:if test="${linemohon.keputusan.kod eq 'G'}">
                        Gantung
                    </c:if>
                    <c:if test="${linemohon.keputusan.kod eq 'T'}">
                        Tolak
                    </c:if>
                </display:column>
            </display:table>
        </div>
    </fieldset>
</div>
