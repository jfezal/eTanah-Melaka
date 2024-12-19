<%-- 
    Document   : paparan_maklumat_perserahan
    Created on : 21 Oktober 2009, 3:17:25 PM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%--<c-rt:set var="now2" value="<%=new java.util.Date()%>"/>--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
    $(document).ready(function(){
        var url = '${pageContext.request.contextPath}/daftar/check_pihak?doCheckPihak2&idHakmilik=${actionBean.idHakmilik}';
        $.get(url,
            function(data){
                if(data == '1'){
                    alert('Hakmilik ada sekatan. Sila semak surat kebenaran.')
                }
            });
    });
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.common.MaklumatPerserahanActionBean">
    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Perserahan</legend>
            <p>
                <label>ID Perserahan :</label>
                ${actionBean.permohonan.idPermohonan}
            </p>

            <p>
                <label>Kod Urusan :</label>
                ${actionBean.permohonan.kodUrusan.kod}
            </p>

            <p>
                <label>Urusan :</label>
                ${actionBean.permohonan.kodUrusan.nama}
            </p>

            <p>
                <label>Tarikh dan Masa :</label>
                <fmt:formatDate type="both"
                                pattern="dd/MM/yyyy h:mm"
                                value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>
                <fmt:formatDate type="time"
                                pattern="aaa"
                                value="${actionBean.permohonan.infoAudit.tarikhMasuk}" var="timeformat"/>
                <c:if test="${timeformat eq 'AM'}"> Pagi</c:if>
                <c:if test="${timeformat eq 'PM'}"> Petang</c:if>
            </p>
        </fieldset>
    </div>
    <%--<div class="subtitle">
        <fieldset class="aras1">
            <legend>Senarai Hakmilik yang Terlibat</legend>
            <display:table name="${actionBean.hakmilikPermohonanList}" class="tablecloth" cellpadding="0" cellspacing="0" >
                <display:column title="No" sortable="true">${line_rowNum}</display:column>
                <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="hakmilik${line_rowNum}" style="text-decoration:underline;cursor:hand;" />
                <display:column property="hakmilik.noLot" title="No Lot" />
                <display:column property="hakmilik.luas" title="Luas" />
                <display:column property="hakmilik.cawangan.name" title="Daerah" />
                <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
            </display:table>
        </fieldset>
    </div>--%>

   <%-- <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'HSBM'}">
    <%@ include file="/WEB-INF/jsp/daftar/maklumat_fail.jsp" %>
    </c:if>--%>

</s:form>