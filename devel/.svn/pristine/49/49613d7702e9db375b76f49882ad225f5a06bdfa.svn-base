<%-- 
    Document   : semakanHakmilik
    Created on : Jan 27, 2010, 3:02:37 PM
    Author     : massita
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%--<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />--%>

<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        text-align:right;
        width:32em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:20em;
    }


</style>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
   $(document).ready( function() {


        var len = $(".daerah").length;

        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                window.open("${pageContext.request.contextPath}/pengambilan/maklumat_asas_pengambilan_Terdahulu?hakmilikDetail&idHakmilik="+$(this).text(), "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=1100,height=600");
            });
        }
    });
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.SemakanHakmilikActionBean">
    <s:messages/>
    <s:errors/>
        <fieldset class="aras1">
            <legend>Semakan Hakmilik</legend>
            <div class="content" align="center">
                <table border="0" width="90%">
                        <tr><td><b>BUTIR-BUTIR HAKMILIK </b></td></tr>
                        <tr><td>
                                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" 
                                                requestURI="/pengambilan/maklumat_asas_pengambilan_Terdahulu" id="tbl1">
                                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${tbl1_rowNum}"/>
                                    <display:column title="Tuan Tanah" style="vertical-align:baseline">
                                        <c:forEach items="${tbl1.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                            <c:if test="${senarai.jenis.kod eq 'PM'}">
                                                <c:out value="${senarai.pihak.nama}"/><br />
                                            </c:if>
                                        </c:forEach>
                                    </display:column>
                                    <display:column title="Jenis & No Hakmilik" style="vertical-align:baseline">
                                        ${tbl1.hakmilik.lot.nama} ${tbl1.hakmilik.kodHakmilik.kod} ${tbl1.hakmilik.noHakmilik}
                                    </display:column>
                                    <display:column title="Nombor Lot/PT" style="vertical-align:baseline">
                                        ${tbl1.hakmilik.lot.nama} ${tbl1.hakmilik.noLot}
                                    </display:column>
                                    <display:column title="Daerah" property="hakmilik.daerah.nama" class="daerah" style="vertical-align:baseline"/>
                                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" style="vertical-align:baseline"/>
                                    <display:column title="Jenis Kegunaan" style="vertical-align:baseline">
                                        <c:if test="${tbl1.hakmilik.kegunaanTanah.nama ne null}">${tbl1.hakmilik.kegunaanTanah.nama}</c:if>
                                        <c:if test="${tbl1.hakmilik.kegunaanTanah.nama eq null}">Tiada</c:if>
                                    </display:column>
                                    <display:column title="Kawasan Rizab" style="vertical-align:baseline" property="hakmilik.rizab.nama">
                                            <c:if test="${tbl1.hakmilik.rizab.nama eq null}">Tiada</c:if>
                                   </display:column>
                                    <display:column title="Luas Keseluruhan" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${tbl1.hakmilik.luas}"/>&nbsp;${tbl1.hakmilik.kodUnitLuas.nama}
                                            <c:if test="${tbl1.hakmilik.luas eq null}">Tiada</c:if>
                                    </display:column>
                                        <display:column title="Luas Diambil" property="luasTerlibat">&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                                         <display:column title="Baki Luas">
                                             <c:if test="${tbl1.luasTerlibat ne null}">
                                                  <c:set value="${tbl1.luasTerlibat}" var="a"/>
                                                  <c:set value="${tbl1.hakmilik.luas}" var="b"/>
                                                  <fmt:formatNumber pattern="#,##0.00" value="${b-a}"/>
                                            </c:if>
                                            <c:if test="${tbl1.luasTerlibat eq null}">0</c:if>
                                        </display:column>
                                </display:table>
                            </td></tr><br />
                        </table><br />
            </div>
        </fieldset>
</s:form>