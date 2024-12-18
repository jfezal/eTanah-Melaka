<%-- 
    Document   : maklumat_pemohonV2ViewTanahMilikPemohon
    Created on : Jul 23, 2013, 3:30:32 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Maklumat Tanah Milik Pemohon</title>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">
    function refreshpagePemohon(type) {
        //        NoPrompt() ;
        var url = '${pageContext.request.contextPath}/pelupusan/maklumat_pemohonV2?refreshpage&type=' + type;
        window.open(url, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
</script>
<body>

    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPemohonV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="idPemohon" id="idPemohon"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Tanah Yang Dimiliki Pemohon</legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.listHakmilik}"  cellpadding="0" cellspacing="0"
                                   requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_pemohonV2" id="line2">
                        <display:column title="No" sortable="true">${line2_rowNum}</display:column>
                        <display:column property="idHakmilik" title="ID Hakmilik" class="popup hakmilik${line2_rowNum}"/>
                        <display:column property="noLot" title="No Lot" />
                        <display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line2.luas}"/>&nbsp;${line2.kodUnitLuas.nama}</display:column>
                        <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" class="bpm"/>
                    </display:table>
                </div>
                <br/>

            </fieldset>
        </div>

        <c:if test="${!empty actionBean.listHubunganSuamiIsteri}">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Maklumat Tanah Yang Dimiliki Suami/Isteri</legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.listHakmilikPasangan}"  cellpadding="0" cellspacing="0"
                                       requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_pemohonV2" id="line2">
                            <display:column title="No" sortable="true">${line2_rowNum}</display:column>
                            <display:column property="idHakmilik" title="ID Hakmilik" class="popup hakmilik${line2_rowNum}"/>
                            <display:column property="noLot" title="No Lot" />
                            <display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line2.luas}"/>&nbsp;${line2.kodUnitLuas.nama}</display:column>
                            <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" class="bpm"/>
                        </display:table>
                    </div>
                    <br/>

                </fieldset>
            </div>
        </c:if>


        <div class="subtitle">
            <fieldset class="aras1">
                <table  width="100%" border="0">
                    <tr>
                        <td align="center">
                            <c:if test="${view}">
                                <s:button name="tutup" value="Tutup" class="btn" onclick="self.close();"/>
                            </c:if>
                            <c:if test="${!view}">
                                <s:button name="tutup" value="Kembali" class="btn" onclick="refreshpagePemohon('tPemohon')"/>
                            </c:if>
                        </td>
                    </tr>
                </table>   
            </fieldset>
        </div>

    </s:form>
</body>
