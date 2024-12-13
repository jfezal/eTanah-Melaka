<%-- 
    Document   : sejarah_hakmilik
    Created on : Apr 9, 2010, 5:37:06 PM
    Author     : wan.fairul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
    $(document).ready( function() {
        var len = $(".daerah").length;

        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                window.open("${pageContext.request.contextPath}/hakmilik?hakmilikDetail&idHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }
    });
</script>
<div class="subtitle">
    <fieldset class="aras1">
        <legend>Sejarah Hakmilik</legend>
        <br>
        <legend>
            Maklumat Hakmilik Asal
        </legend>
        <p align="center"><label></label>
            <display:table class="tablecloth" style="width:90%;" name="${actionBean.senaraiHakmilikAsal}"
                           requestURI="/common/carian_hakmilik?&selectedTab=6" excludedParams="selectedTab" pagesize="10" cellpadding="0" cellspacing="0" id="line6">
                <display:column title="No" sortable="true">${line6_rowNum}</display:column>
                <display:column title="ID Hakmilik">
                        ${line6.idHakmilik}
                </display:column>
                <display:column property="daerah.nama" title="Daerah" class="daerah"/>
                <display:column property="noLot" title="No Lot" />
                <display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line6.luas}"/>&nbsp;${line6.kodUnitLuas.nama}</display:column>
                <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
            </display:table>
            &nbsp;
        </p>
        <br>
        <br>
        <legend>
            Maklumat Hakmilik Sebelum
        </legend>
        <p align="center"><label></label>
            <display:table class="tablecloth" style="width:90%;" name="${actionBean.senaraiHakmilikSebelum}"
                           requestURI="/common/carian_hakmilik?&selectedTab=6" excludedParams="selectedTab" pagesize="10" cellpadding="0" cellspacing="0" id="line7">
                <display:column title="No" sortable="true">${line7_rowNum}</display:column>
                <display:column title="ID Hakmilik">
                        ${line7.idHakmilik}
                </display:column>
                <display:column property="daerah.nama" title="Daerah" class="daerah"/>
                <c:if test="${line7.kodStatusHakmilik.kod ne 'S'}">
                <display:column property="noLot" title="No Lot" />
                <display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line7.luas}"/>&nbsp;${line7.kodUnitLuas.nama}</display:column>
                <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                </c:if>
            </display:table>
            &nbsp;
        </p>
        
    </fieldset>
</div>
