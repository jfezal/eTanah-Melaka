<%-- 
    Document   : penyediaan_8A_pt
    Created on : Dec 26, 2009, 2:00:17 AM
    Author     : nurfaizati
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

 <script type="text/javascript">
    function show(id){
        window.open("${pageContext.request.contextPath}/hasil/papar_hakmilik_pihak?showForm&idHakmilik="+id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }

    function showReport(){
        window.open("${pageContext.request.contextPath}/hasil/penyediaan8Apt?cetak8A", "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }
</script>

<s:form beanclass="etanah.view.stripes.hasil.Penyediaan8AptActionBean">
<div class="subtitle">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Hakmilik Yang Belum Membayar Tunggakan
            </legend>

            <div class="content" align="center">

                <display:table class="tablecloth" name="${actionBean.senaraiHakmilikPermohonan}"
                               pagesize="5" cellpadding="0" cellspacing="0" requestURI="/hasil/penyediaan8Apt" id="line">
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <c:if test="${actionBean.negeri eq 'melaka'}">
                        <display:column title="No. Akaun" property="hakmilik.akaunCukai.noAkaun" class="${line_rowNum}" />
                    </c:if>
                    <display:column title="ID Hakmilik"><a href="#" onclick="show('${line.hakmilik.idHakmilik}');return false;">${line.hakmilik.idHakmilik}</a></display:column>
                    <display:column title="No. Rujukan">${actionBean.senaraiNoRujukan[line_rowNum - 1]}</display:column>
                    <display:column title="Jenis dan Nombor Hakmilik" class="${line_rowNum}">
                        ${line.hakmilik.kodHakmilik.kod}&nbsp;&nbsp;
                        ${line.hakmilik.noHakmilik}<br>
                    </display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="${line_rowNum}"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" class="${line_rowNum}"/>
                    <display:column property="hakmilik.noLot" title="No. Lot/P.T" class="${line_rowNum}"/>
                    <display:column title="Luas"><div align="right"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>${line.hakmilik.kodUnitLuas.kod}</div></display:column>
                    <display:column title="Jumlah Tunggakan(RM)"><div align="right"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.akaunCukai.baki}"/></div></display:column>
                </display:table>
            </div>
            <%--<p align="right">
                <s:button class="btn" onclick="showReport();" name="" value="Cetak 8A"/>
            </p>--%>
        </fieldset>
    </div>
</div>
</s:form>