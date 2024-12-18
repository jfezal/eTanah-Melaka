<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>


<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />
<title>Kaunter: Cetak Resit</title>

</head>
<body>

<script language="javascript">

function doPrintViaApplet (docId) {
    var a = document.getElementById('applet');
    //a.printDocument(docId.toString());
    a.doPrint(docId.toString());
}

function doPrintBelakangResit(caraByrId){
        var a = document.getElementById('applet2');
        a.printChequeInfo(caraByrId.toString());
    }

</script>

<p class=title>Cetak Resit dan Lain-lain Dokumen</p>

<stripes:messages />
<stripes:errors />
<%-- 
<fieldset class="aras1">
    <legend>Maklumat Urusan</legend>
    <p><label>Jumlah Urusan Didaftar :</label>${fn:length(actionBean.senaraiUrusan)}
    <c:if test="${!empty actionBean.resitNo}">
        <p style="vertical-align:top"><label for="noResit">No. Resit :</label>
            ${actionBean.resitNo} &nbsp;&nbsp;&nbsp;<button class="btn" name="cetakResit"
                onclick="doPrintViaApplet('${resitId}');">Cetak Resit</button>
        </p>
    </c:if>
</fieldset>
   
    <stripes:form beanclass="etanah.view.kaunter.PermohonanKaunter2">
<display:table class="tablecloth" name="${actionBean.caraBayaranList}" requestURI="/kaunter/permohonan" id="line">
    <display:column title="Bil." ><div align="center">${line_rowNum}</div></display:column>
    <display:column property="kodCaraBayaran.nama" title="Cara Bayaran"/>
    <display:column title="Bank / Agensi Pembayaran">
        <c:if test="${line.bank.kod eq null}">-</c:if>
        <c:if test="${line.bank.kod ne null}">${line.bank.kod} - ${line.bank.nama}</c:if>
    </display:column>
    <display:column title="Cawangan">
        <c:if test="${line.bankCawangan eq null}">-</c:if>
        <c:if test="${line.bankCawangan ne null}">${line.bankCawangan}</c:if>
    </display:column>
    <display:column title="Nombor Rujukan">
        <c:if test="${line.noRujukan eq null}">-</c:if>
        <c:if test="${line.noRujukan ne null}">${line.noRujukan}</c:if>
    </display:column>
    <display:column property="amaun" title="Amaun (RM)" style="text-align:right" format="{0,number, 0.00}"/>
    <display:column title="Cetak Pengesahan Cek/WP/KW" style="text-align:center">
        <c:choose >
            <c:when test="${line.kodCaraBayaran.kod eq 'T'
                            or line.kodCaraBayaran.kod eq 'KK'
                            or line.kodCaraBayaran.kod eq 'VS'
                            or line.kodCaraBayaran.kod eq 'DK'}">
                    <s:button name=" " disabled="true" value="Cetak" class="btn"/>
            </c:when>
            <c:otherwise>
                
                <stripes:button name=" " value="Cetak" class="btn" onclick="doPrintBelakangResit('${line.idCaraBayaran}');"/>
            </c:otherwise>
        </c:choose>
    </display:column>
</display:table>
    </stripes:form>
--%>
<p></p>
<display:table  name="${actionBean.senaraiUrusan}" id="row" class="tablecloth" >
    <display:column title="Bil" >${row_rowNum}</display:column>
    <display:column title="Urusan">
        ${row.kodUrusan} - ${row.namaUrusan}
    </display:column>
    <display:column title="ID Permohonan/Perserahan" property="noRujukan" />
    <%-- 
    <display:column title="Cetak">
        <c:if test="${!empty row.cetakan}">
            <button name="cetakAP${row_rowNum}" 
                onclick="doPrintViaApplet('${actionBean.senaraiUrusan[(row_rowNum - 1)].cetakan.idDokumen}');" 
                class="btn">${actionBean.senaraiUrusan[(row_rowNum - 1)].cetakan.kodDokumen.nama}</button>
        </c:if>
    </display:column>
    --%>
</display:table>


<stripes:form beanclass="etanah.view.kaunter.PemohonanPembangunanKaunter">
       <stripes:submit name="step1" value="Kembali ke Utama" class="longbtn"/>
</stripes:form>

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
        for (int i =0; i < cookies.length; i++) {
           sb.setLength(0);
           sb.append(cookies[i].getName());
           sb.append("=");
           sb.append(cookies[i].getValue());
          %>
          <param name="Cookie<%= i %>" value="<%= sb.toString() %>"><%
        }
        %>
</applet>
<applet code="etanah.dokumen.print.PrinterHasil" ARCHIVE="${pageContext.request.contextPath}/PrintApplet.jar,
        ${pageContext.request.contextPath}/commons-httpclient-2.0.2.jar,
        ${pageContext.request.contextPath}/commons-logging.jar,
        ${pageContext.request.contextPath}/swingx-1.6.jar,
        ${pageContext.request.contextPath}/log4j-1.2.12.jar,
        ${pageContext.request.contextPath}/jpedal_demo.jar"
            codebase = "${pageContext.request.contextPath}/"
            name     = "applet2"
            id       = "applet2"
            width    = "1"
            height   = "1"
            align    = "middle">
        <param name="uploadAction" value="<%=request.getContextPath()%>/PrintServlet"/>
        <param name="kod_negeri" value="${negeri}"/>
        <param name ="method" value="pdfp">
         <%
        Cookie[] cookies2 = request.getCookies();
        StringBuffer sb2 = new StringBuffer();
        for (int i = 0; i < cookies2.length; i++) {
           sb2.setLength(0);
           sb2.append(cookies2[i].getName());
           sb2.append("=");
           sb2.append(cookies2[i].getValue());
          %>
          <param name="Cookie<%= i %>" value="<%= sb2.toString() %>"><%
        }
        %>
</applet>
<%--<applet code="etanah.dokumen.print.DocumentPrinter" ARCHIVE="${pageContext.request.contextPath}/PrintApplet.jar,
            ${pageContext.request.contextPath}/commons-httpclient-2.0.2.jar,
            ${pageContext.request.contextPath}/commons-logging.jar,
            ${pageContext.request.contextPath}/swingx-1.6.jar,
            ${pageContext.request.contextPath}/log4j-1.2.12.jar,
            ${pageContext.request.contextPath}/jpedal_trial.jar,
            ${pageContext.request.contextPath}/PDFRenderer.jar"
                codebase = "${pageContext.request.contextPath}/"
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
            for (int i =0; i < cookies.length; i++) {
               sb.setLength(0);
               sb.append(cookies[i].getName());
               sb.append("=");
               sb.append(cookies[i].getValue());
              %>
              <param name="Cookie<%= i %>" value="<%= sb.toString() %>"><%
            }
            %>
    </applet>--%>

</body>
</html>