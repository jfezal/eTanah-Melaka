<%-- 
    Document   : maklumat_urusan
    Created on : Apr 9, 2010, 5:19:30 PM
    Author     : khairil
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    var idHakmilik = $('#idHakmilik').val();
    $(document).ready( function() {
        var len = $(".hakmilik").length;       
        for (var i=0; i<=len; i++){
            <%--$('.perserahan'+i).click( function() {
                var url = "${pageContext.request.contextPath}/common/maklumat_permohonan?view&idPermohonan="+$(this).text()+"&idHakmilik="+idHakmilik;
                alert(url);
                window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });--%>
        }
    });
    function doPopupDetails(val) {
        var url = '${pageContext.request.contextPath}/common/maklumat_permohonan?view&idPermohonan=' + val +'&idHakmilik='+idHakmilik;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=768,scrollbars=yes");
    }
</script>
<div class="subtitle">
    <fieldset class="aras1">
        <legend>Maklumat Urusan Yang Berkuatkuasa</legend>
        <div class="content" align="center" id="listpihak">
            <display:table class="tablecloth" style="width:90%;" name="${actionBean.hakmilikUrusanList}"
                           requestURI="/common/carian_hakmilik?&selectedTab=maklumat_urusan" excludedParams="selectedTab" pagesize="10" cellpadding="0" cellspacing="0" id="linemohonpihak">
                <display:column title="Bil" sortable="true">${linemohonpihak_rowNum}</display:column>
                <%--<display:column property="idPerserahan" title="ID Perserahan" class="popup perserahan${linemohonpihak_rowNum}"/>--%>
                <display:column title="ID Perserahan">
                    <a href="#" onClick="doPopupDetails('${linemohonpihak.idPerserahan}');">${linemohonpihak.idPerserahan}</a>
                </display:column>                
                <display:column property="kodUrusan.kod" title="Kod Urusan" class="urusan" />
                 <display:column property="kodUrusan.nama" title="Nama Urusan" class="urusan" />
                 <display:column property="tarikhDaftar" title="Tarikh Daftar" format="{0,date,dd/MM/yyyy   hh:mm aa}" />
               <%-- <display:column title="Status">
                    <c:if test="${linemohonpihak.aktif eq 'Y'}">
                        Daftar
                    </c:if>
                    <c:if test="${linemohonpihak.aktif ne 'Y'}">
                        Tidak Berkuatkuasa
                    </c:if>
                    <s:hidden name="idHakmilik" value="${linemohonpihak.hakmilik.idHakmilik}" class="hakmilik"/>
                </display:column>--%>
            </display:table>
        </div>

    </fieldset>
    <%-- <br>
    <fieldset class="aras1">
        <legend>Maklumat Urusan Yang Tidak Berkuatkuasa</legend>
        <div class="content" align="center" id="listpihak">
            <display:table class="tablecloth" style="width:90%;" name="${actionBean.hakmilikUrusanListTaktif}"
                           requestURI="/common/carian_hakmilik?&selectedTab=maklumat_urusan" excludedParams="selectedTab" pagesize="10" cellpadding="0" cellspacing="0" id="linemohonpihak">
                <display:column title="Bil" sortable="true">${linemohonpihak_rowNum}</display:column>
                <display:column property="idPerserahan" title="ID Perserahan" class="popup perserahan${linemohonpihak_rowNum}"/>
                <display:column title="ID Perserahan">
                    <a href="#" onClick="doPopupDetails('${linemohonpihak.idPerserahan}');">${linemohonpihak.idPerserahan}</a>
                </display:column>    
                <display:column property="kodUrusan.kod" title="Kod Urusan" class="urusan" />
                <display:column property="kodUrusan.nama" title="Nama Urusan" class="urusan" />
                <display:column property="tarikhDaftar" title="Tarikh Daftar" format="{0,date,dd/MM/yyyy   HH:mm}" />--%>
                <%--<display:column title="Status">
                    <c:if test="${linemohonpihak.aktif eq 'Y'}">
                        Daftar
                    </c:if>
                    <c:if test="${linemohonpihak.aktif ne 'Y'}">
                        Tidak Berkuatkuasa
                    </c:if>
                    <s:hidden name="idHakmilik" value="${linemohonpihak.hakmilik.idHakmilik}" class="hakmilik"/>
                </display:column>--%>
            <%--</display:table>
        </div>

    </fieldset>--%>
</div>
