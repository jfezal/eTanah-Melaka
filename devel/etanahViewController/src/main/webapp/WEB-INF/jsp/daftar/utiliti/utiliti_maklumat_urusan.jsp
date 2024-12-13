<%-- 
    Document   : maklumat_urusan
    Created on : Apr 9, 2010, 5:19:30 PM
    Author     : wan.firul
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    var idHakmilik = $('#idHakmilik').val();
    $(document).ready( function() {
        var len = $(".hakmilik").length;       
        for (var i=0; i<=len; i++){
        }
    });
    function viewUrusan(id){
        window.open("${pageContext.request.contextPath}/daftar/kesinambungan?viewUrusanDetail&idPermohonan="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=700,scrollbars=yes");
    }
</script>
<div class="subtitle">
    <br>
    <p><label>ID Hakmilik :</label>
            ${actionBean.idHakmilik}
    </p>
    <fieldset class="aras1"><br>
        <legend>Maklumat Urusan Yang Berkuatkuasa</legend>
        <div class="content" align="center" id="listpihak">
            <display:table class="tablecloth" style="width:90%;" name="${actionBean.hakmilikUrusanList}"
                           requestURI="/common/carian_hakmilik?&selectedTab=maklumat_urusan" excludedParams="selectedTab" pagesize="10" cellpadding="0" cellspacing="0" id="linemohonpihak">
                <display:column title="Bil" sortable="true">${linemohonpihak_rowNum}</display:column>
                <display:column title="ID Perserahan">
                    <a href="#" onClick="viewUrusan('${linemohonpihak.idPerserahan}');">${linemohonpihak.idPerserahan}</a>
                </display:column>                
                <display:column property="kodUrusan.kod" title="Kod Urusan" class="urusan" />
                <display:column property="kodUrusan.nama" title="Nama Urusan" class="urusan" />
                <display:column property="tarikhDaftar" title="Tarikh Perserahan"  format="{0,date,dd/MM/yyyy   HH:mm:ss}" />
                <display:column property="infoAudit.tarikhMasuk" title="Tarikh Daftar"  format="{0,date,dd/MM/yyyy   HH:mm:ss}" />
            </display:table><br>
        </div>
    </fieldset>
    <fieldset class="aras1">
        <legend>Maklumat Urusan Yang Tidak Berkuatkuasa</legend>
        <div class="content" align="center" id="listpihak">
            <display:table class="tablecloth" style="width:90%;" name="${actionBean.hakmilikUrusanListTaktif}"
                           requestURI="/common/carian_hakmilik?&selectedTab=maklumat_urusan" excludedParams="selectedTab" pagesize="10" cellpadding="0" cellspacing="0" id="linemohonpihak">
                <display:column title="Bil" sortable="true">${linemohonpihak_rowNum}</display:column>
                <display:column title="ID Perserahan">
                    <a href="#" onClick="viewUrusan('${linemohonpihak.idPerserahan}');">${linemohonpihak.idPerserahan}</a>
                </display:column>
                <display:column property="kodUrusan.kod" title="Kod Urusan" class="urusan" />   
                <display:column property="kodUrusan.nama" title="Nama Urusan" class="urusan" />
                <display:column property="tarikhDaftar" title="Tarikh Perserahan"  format="{0,date,dd/MM/yyyy   HH:mm:ss}" />
                <display:column property="infoAudit.tarikhMasuk" title="Tarikh Daftar"  format="{0,date,dd/MM/yyyy   HH:mm:ss}" />
                 
                
            </display:table><br>
        </div>
    </fieldset>
    <fieldset class="aras1">
        <legend>Maklumat Urusan Dalam Proses</legend>
        <div class="content" align="center" id="listpihak">
            <display:table class="tablecloth" name="${actionBean.senaraiUrusanProsesDistinct}" requestURI="/common/carian_hakmilik?&selectedTab=maklumat_urusan_proses" excludedParams="selectedTab"
                           cellpadding="0" cellspacing="0" id="line3" style="width:90%" pagesize="10">
                <display:column title="Bil">${line3_rowNum}</display:column>
                <display:column property="idPermohonan" title="ID Perserahan"/>
                <display:column property="kodUrusan" title="Kod Urusan"/>
                <display:column property="urusan" title="Urusan" class="urusan"/>
                <display:column property="tarikhMula" title="Tarikh Perserahan" format="{0,date,dd/MM/yyyy    hh:ss}" />
                <display:column property="stage" title="Status"/>
            </display:table><br>
        </div>
    </fieldset><br>    
</div>
