<%-- 
    Document   : tamat
    Created on : Jun 3, 2013, 3:23:43 PM
    Author     : Admin
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">

    function doPrintViaApplet (docId) {
        //alert('tsttt');
        var a = document.getElementById('applet');
        a.doPrint(docId.toString());
        //a.printDocument(docId.toString());
    }

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }


</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.stripes.pengambilan.AduanActionBean">
    <s:messages />
    <s:errors />
    <div class="subtitle" align="center">
        <fieldset class="aras1">
            <legend>Maklumat Urusan</legend>
            <p>
                <c:if test="${fn:length(actionBean.listMohon) == 0}">
                    <display:table  name="${actionBean.senaraiPermohonan}" id="row" class="tablecloth" >
                        <display:column title="Bil" >${row_rowNum}</display:column>
                        <display:column title="ID Aduan">
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SEK4A'}">
                                <a href="${pageContext.request.contextPath}/pengambilan/carian_aduan?peruntukanSeksyen&idPermohonan=${row.idPermohonan}">${row.idPermohonan}</a></c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'SEK4A'}">${actionBean.permohonan.idPermohonan}</c:if>
                        </display:column>
                        <display:column title="Urusan">
                            <c:choose>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'SEK4A'}">
                                    Permohonan Pengambilan Tanah Di bawah Seksyen 4 - Aduan Kerosakan
                                </c:when>
                                <c:otherwise>
                                    ${actionBean.permohonan.kodUrusan.nama}
                                </c:otherwise>
                            </c:choose>
                        </display:column>
                    </display:table>
                </c:if>
                <c:if test="${fn:length(actionBean.listMohon) > 0}">
                    <display:table  name="${actionBean.listMohon}" id="row" class="tablecloth" >
                        <display:column title="Bil" >${row_rowNum}</display:column>
                        <display:column title="ID Aduan">
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SEK4A'}">
                                <a href="${pageContext.request.contextPath}/penguatkuasaan/senarai_aduan?peruntukanSeksyen&idPermohonan=${row.idPermohonan}">${row.idPermohonan}</a></c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'SEK4A'}">${actionBean.permohonan.idPermohonan}</c:if>
                        </display:column>
                        <display:column title="Urusan">${actionBean.permohonan.kodUrusan.nama}</display:column>
                    </display:table>
                </c:if>
            </p>
            <p align="right">
                <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                <%--<s:submit name="genReport" id="report" value="Jana Terimaan Aduan" class="longbtn"/>&nbsp;
                <s:button name="" value="Papar Aduan" class="longbtn" onclick="doViewReport('${actionBean.idDokumen}');"/>
                --%>


                <s:button class="longbtn" name="cetakResit" onclick="doViewReport('${actionBean.idDokumen}');">Papar Aduan</s:button>&nbsp;
                <s:button class="btn" name="cetakResit" onclick="doPrintViaApplet('${actionBean.idDokumen}');">Cetak Aduan</s:button>&nbsp;
                <s:submit name="kembali" value="Tambah Aduan" class="btn" /><br><br>
            </p>
        </fieldset>
    </div>


    <applet code="PrintApplet.class" ARCHIVE="${pageContext.request.contextPath}/PrintApplet.jar,
            ${pageContext.request.contextPath}/commons-httpclient-2.0.2.jar,
            ${pageContext.request.contextPath}/commons-logging.jar,
            ${pageContext.request.contextPath}/swingx-1.6.jar,
            ${pageContext.request.contextPath}/log4j-1.2.12.jar,
            ${pageContext.request.contextPath}/jpedal_demo.jar"
            codebase = "."
            name     = "applet"
            id       = "applet"
            width    = "1"
            height   = "1"
            align    = "middle">
        <param name="uploadAction" value="<%=request.getContextPath()%>/PrintServlet"/>
        <param name ="method" value="pdfp">
        <%
            Cookie[] cookies = request.getCookies();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < cookies.length; i++) {
                sb.setLength(0);
                sb.append(cookies[i].getName());
                sb.append("=");
                sb.append(cookies[i].getValue());
        %>
        <param name="Cookie<%= i%>" value="<%= sb.toString()%>"><%
            }
        %>
    </applet>
</s:form>

