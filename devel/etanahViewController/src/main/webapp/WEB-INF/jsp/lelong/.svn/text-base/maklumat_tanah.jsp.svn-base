<%-- 
    Document   : maklumat_tanah
    Created on : Mar 3, 2010, 4:44:46 PM
    Author     : mazurahayati
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
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

    function popup(id){
        window.open("${pageContext.request.contextPath}/common/maklumat_hakmilik_single?popup&idSyarat="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }
</script>

<s:form beanclass="etanah.view.stripes.lelong.MaklumatTanahActionBean">
    <s:messages/>
    <div class="subtitle displaytag">
        <fieldset class="aras1">
            <legend>Senarai Tuan Tanah</legend>
            <center>
                <display:table class="tablecloth" name="${actionBean.pihakKepentinganList}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column property="pihak.nama" title="Nama" class="nama"/>
                    <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                    <display:column title="Bahagian yang dimiliki" >${line.syerPembilang}/${line.syerPenyebut}</display:column>
                    <display:column title="Tarikh Pemilikan Tanah">
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/>
                    </display:column>
                    <display:column title="Status" class="${line_rowNum}">
                        <c:if test="${line.jenis.kod ne 'PG'}">
                            Penggadai
                        </c:if>
                        <c:if test="${line.jenis.kod eq 'PG'}">
                            ${line.jenis.nama}
                        </c:if>
                    </display:column>
                    <%-- <display:column property="jenis.nama" title="Jenis Pihak"/>--%>
                </display:table>
            </center>
        </fieldset>
    </div>
    <br>
    <br
        <div class="subtitle displaytag">

        <fieldset class="aras1">
            <legend>
                Maklumat Hakmilik
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0"
                               requestURI="/lelong/maklumat_tanah" id="line">
                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}" style="vertical-align:baseline"/>
                    <display:column property="hakmilik.noLot" title="Nombor Lot/PT" style="vertical-align:baseline"/>
                    <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah" style="vertical-align:baseline"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" style="vertical-align:baseline"/>
                    <display:column property="hakmilik.maklumatAtasTanah" title="Jenis Pengggunaan Tanah" />
                </display:table>
            </div>
        </fieldset>
    </div>
</s:form>




