<%--
    Document   : rekodAmbilCekSatuTuanTanah
    Created on : Jun 23, 2011, 12:20:34 PM
    Author     : Massita
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
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

    function popupList(idPihak,idHakmilik){
        var url = '${pageContext.request.contextPath}/pengambilan/RekodAmbilCekSatuTuanTanah?showAmbilPampasanList&idPihak='+idPihak+'&idHakmilik='+idHakmilik;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=800,height=500");
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div id="hakmilik_details">
    <s:form beanclass="etanah.view.stripes.pengambilan.RekodAmbilCekSatuTuanTanahActionBean">
        <s:messages />
        <s:errors/>
        <fieldset class="aras1">
            <c:if test="${actionBean.hakmilik ne null && actionBean.permohonanPihak ne null}" >
                <legend align="center">
                    REKOD PENGAMBILAN CEK OLEH TUAN TANAH
                </legend>
                <br/>
                <div align="center">
                    <table class="tablecloth">
                        <tr>
                            <th>Id Hakmilik</th><th>No Lot/No PT</th><th>Daerah</th><th>Bandar/Pekan/Mukim</th><th>Tuan Tanah</th><th>Bil Bayaran</th>
                        </tr>
                        <tr>
                            <td>
                                ${actionBean.hakmilik.idHakmilik}
                            </td>
                            <td>
                                ${actionBean.hakmilik.lot.nama}${actionBean.hakmilik.noLot}
                            </td>
                            <td>
                                ${actionBean.hakmilik.daerah.nama}
                            </td>
                            <td>
                                ${actionBean.hakmilik.bandarPekanMukim.nama}
                            </td>
                            <td>
                                <c:forEach items="${actionBean.ambilPampasanList}" var="listx">
                                    <s:param name="idPihak" value="${listx.perbicaraanKehadiran.pihak.pihak.idPihak}"/>
                                    <s:param name="idHakmilik" value="${actionBean.hakmilik.idHakmilik}"/>
                                    ${listx.perbicaraanKehadiran.pihak.pihak.nama}
                                    <BR>
                                </c:forEach>
                            </td>
                            <td>
                                <c:set var="count" value="0"/>
                                <c:forEach items="${actionBean.ambilPampasanList}" var="list">
                                    <c:if test="${actionBean.permohonanPihak.pihak.idPihak == list.perbicaraanKehadiran.pihak.pihak.idPihak}">
                                        <c:set value="${count + 1}" var="count"/>
                                    </c:if>

                                    <a href="javascript:popupList('${list.perbicaraanKehadiran.pihak.pihak.idPihak}','${actionBean.hakmilik.idHakmilik}');">
                                        <%--<c:out value="${count}" />--%>
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                             onclick="javascript:popupList('${list.perbicaraanKehadiran.pihak.pihak.idPihak}','${actionBean.hakmilik.idHakmilik}');" height="30" width="30" alt="papar"
                                             onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                    </a><br/>
                                </c:forEach>
                            </td>
                        </tr>
                    </table>
                </div>
            </c:if>
        </fieldset>
    </div>
</s:form>
