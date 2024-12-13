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
        var url = '${pageContext.request.contextPath}/pengambilan/RekodAmbilCek?showAmbilPampasanList&idPihak='+idPihak+'&idHakmilik='+idHakmilik;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=800,height=500");
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div id="hakmilik_details">
    <s:form beanclass="etanah.view.stripes.pengambilan.RekodAmbilCekActionBean">
        <s:messages />
        <s:errors/>
        <fieldset class="aras1">

            <legend align="center">
                REKOD PENGAMBILAN CEK
            </legend>
            <br/>
            <div align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/RekodAmbilCek" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="hakmilik.noHakmilik" title="No Hakmilik"/>
                    <%--<display:column property="hakmilik.noLot" title="No Lot/No PT" />--%>
                    <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column title="Tuan Tanah" >
                        <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">

                            <c:if test="${senarai.aktif eq 'Y'}">
                                ${senarai.pihak.nama}

                                <br/>
                            </c:if>
                        </c:forEach>
                    </display:column>
                    <display:column title="Rekod Bayaran">
                        <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                            <c:set var="count" value="0"/>
                            <c:forEach items="${actionBean.ambilPampasanList}" var="list">
                                <c:if test="${senarai.pihak.idPihak == list.perbicaraanKehadiran.pihak.pihak.idPihak}">
                                    <c:set value="${count + 1}" var="count"/>
                                </c:if>
                            </c:forEach>

                            <div align="center">
                                <c:if test="${senarai.aktif eq 'Y'}">
                                    <img alt='Klik Untuk Maklumat Pampasan' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popupList('${senarai.pihak.idPihak}','${line.hakmilik.idHakmilik}');"/>
                                </c:if>
                            </div>
                        </c:forEach>
                    </display:column>
                </display:table>

        </fieldset>
    </div>
</s:form>
