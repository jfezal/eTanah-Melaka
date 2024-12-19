<%--
    Document   : RekodPembayaranCek
    Created on : Sep 24, 2010, 12:20:34 PM
    Author     : Rajesh Reddy
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
        var url = '${pageContext.request.contextPath}/pengambilan/RekodPembayaranCek?showAmbilPampasanList&idPihak='+idPihak+'&idHakmilik='+idHakmilik;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=800,height=500");
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div id="hakmilik_details">
    <s:form beanclass="etanah.view.stripes.pengambilan.RekodPembayaranCekActionBean">
        <s:messages />
        <s:errors/>
        <c:if test="${form1}">
            <fieldset class="aras1">
                <legend align="center">
                    REKOD PEMBAYARAN CEK
                </legend>
                <br/>
                <div align="center">

                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/RekodPembayaranCek" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column property="hakmilik.noHakmilik" title="No Hakmilik"/>
                        <%--<display:column property="hakmilik.noLot" title="No Lot/No PT" />--%>
                        <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}&nbsp;${line.hakmilik.noLot}</display:column>
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                        <display:column title="Tuan Tanah" >
                            <%--<s:param name="idPihak" value="${line.pihak.idPihak}"/>${line.pihak.nama}--%>
                            <c:forEach items="${actionBean.permohonanPihakList}" var="senarai">
                                <c:if test="${senarai.hakmilik.idHakmilik == line.hakmilik.idHakmilik}">
                                    ${senarai.pihak.nama}
                                <br/>
                                </c:if>
                            </c:forEach>
                        </display:column>
                        <display:column title="Bil Bayaran">
                            <c:forEach items="${actionBean.permohonanPihakList}" var="senarai">
                                <c:if test="${senarai.hakmilik.idHakmilik == line.hakmilik.idHakmilik}">
                                    <c:set var="count" value="0"/>
                                    <c:forEach items="${actionBean.ambilPampasanList}" var="list">
                                        <c:if test="${senarai.pihak.idPihak == list.perbicaraanKehadiran.pihak.pihak.idPihak}">
                                            <c:set value="${count + 1}" var="count"/>
                                        </c:if>
                                    </c:forEach>
                                    <a href="javascript:popupList('${senarai.pihak.idPihak}','${line.hakmilik.idHakmilik}');">
                                        <%--<c:out value="${count}" />--%>
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                             onclick="javascript:popupList('${senarai.pihak.idPihak}','${line.hakmilik.idHakmilik}');" height="30" width="30" alt="papar"
                                             onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                    </a><br/>
                                </c:if>
                            </c:forEach>
                        </display:column>
                    </display:table>

                </div>
            </fieldset>
        </fieldset>
    </div>
</c:if>
</s:form>
