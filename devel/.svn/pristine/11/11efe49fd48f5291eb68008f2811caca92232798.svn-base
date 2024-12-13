<%-- 
    Document   : Bayaran_Pampasan_Amanah_Raya
    Created on : Jul 25, 2011, 4:22:07 PM
    Author     : Rajesh
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252" import="java.util.*" import="java.text.SimpleDateFormat"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">
$(document).ready( function(){


$(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
$(".datepicker1").datepicker({dateFormat: 'yy'});
});
function ajaxLink(link, update) {
$.get(link, function(data) {
$(update).html(data);
$(update).show();
});
return false;
}

</script>

<s:form beanclass="etanah.view.stripes.pengambilan.BayaranPampasanAmanahRayaActionBean">
    <s:messages/>
    <s:errors/>
    <div  id="hakmilik_details">
        <p align="center"><font size="3"><b> REKOD PEMBERIAN BAYARAN PAMPASAN AMANAH RAYA</b></font></p>
        <br/><br/>
        <fieldset class="aras1">
            <legend >
                <b>Maklumat Hakmilik Permohonan</b>
            </legend><br/>
            <p align="center">
                <display:table class="tablecloth" name="${actionBean.permohonan.senaraiHakmilik}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/bayaran_pampasan_amanah_raya" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column title="ID Hakmilik">
                        <s:link beanclass="etanah.view.stripes.pengambilan.BayaranPampasanAmanahRayaActionBean"
                            event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                        <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}
                        </s:link>
                    </display:column>
                    <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                </display:table>
            </p>
            <br/><br/><br/>
            <c:if test="${actionBean.hakmilik ne null}">
                <fieldset class="aras1">
                    <legend>Tuan Tanah</legend><br />
                    <div align="center">
                        <p align="center">
                            <display:table class="tablecloth" name="${actionBean.senaraiPerbicaraanKehadiran}" cellpadding="0" cellspacing="0"
                                           requestURI="/pengambilan/bayaran_pampasan_amanah_raya" id="line" pagesize="5">
                                <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                <display:column title="Orang Berkepentingan" >
                                    ${line.pihak.pihak.nama}<br>
                                    No KP ${line.pihak.pihak.noPengenalan}
                                </display:column>
                                <display:column title="Pemegang Amanah/Waris" >
                                    <c:forEach items="${actionBean.hakmilik.senaraiPihakBerkepentingan}" var="list">
                                        <c:if test="${line.pihak.pihak.idPihak == list.pihak.idPihak}">
                                    <c:set value="1" var="count"/>
                                    <c:forEach items="${list.senaraiWaris}" var="senarai">
                                    <table border="0">
                                        <tr>
                                            <td width="5"><c:out value="${count}"/>)</td>
                                            <td><c:out value="${senarai.nama}"/></td>
                                        </tr>
                                    </table>
                                            <c:set value="${count + 1}" var="count"/>
                                    </c:forEach>
                                        </c:if>
                                    </c:forEach>
                                </display:column>
                                <display:column title="Pampasan Amanah Raya RM">
                                    <c:forEach items="${actionBean.hakmilik.senaraiPihakBerkepentingan}" var="list">
                                        <c:if test="${line.pihak.pihak.idPihak == list.pihak.idPihak}">
                                            <c:set value="${list.syerPembilang}" var="a"/>
                                            <c:set value="${list.syerPenyebut}" var="b"/>
                                        </c:if>
                                    </c:forEach>
                                    <c:set value="${actionBean.hakmilikPermohonan.luasTerlibat}" var="c"/>
                                    <c:set value="${(actionBean.hakmilikPerbicaraan.keputusanNilai)/(actionBean.hakmilikPermohonan.luasTerlibat)}" var="d"/>
                                    <c:set value="${a/b*c}" var="e"/>
                                    <c:set value="${e*d}" var="f"/>
                                    <c:forEach items="${actionBean.hakmilik.senaraiPihakBerkepentingan}" var="list">
                                        <c:if test="${line.pihak.pihak.idPihak == list.pihak.idPihak}">
                                            <c:set var="B" value="0"/>
                                            <c:forEach items="${line.senaraiPenilaian}" var="list1">
                                                <c:set value="${B + list1.amaun}" var="B"/>
                                            </c:forEach>
                                            <c:set value="${B}" var="g"/>
                                        </c:if>
                                    </c:forEach>
                                    <fmt:formatNumber pattern="#,##0.00" value="${f+g}"/>
                                </display:column>
                            </display:table>
                        </p>
                    </div>
                    <br /><br />
                </fieldset><br />
            </c:if>
                
        </fieldset>
    </div>

</s:form>
