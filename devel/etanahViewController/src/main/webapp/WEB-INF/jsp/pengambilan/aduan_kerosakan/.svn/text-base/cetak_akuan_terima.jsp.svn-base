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
    a.printDocument(docId.toString());
//    a.doPrint(docId.toString());
}

function doPrintBelakangResit(caraByrId){
        var a = document.getElementById('applet2');
        a.printChequeInfo(caraByrId.toString());
    }

function papar(id) {    
        var url = '${pageContext.request.contextPath}/dokumen/view/' + id;
        window.open(url,'etanah',"status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
}
</script>

<p class=title>Cetak Dokumen Akuan Terima </p>

<stripes:messages />
<stripes:errors />


   
<p></p>

    <s:form beanclass="etanah.view.stripes.pengambilan.aduan.TerimaAduanActionBean">
<display:table  name="${actionBean.senaraiUrusan}" id="row" class="tablecloth" >
    <display:column title="Bil" >${row_rowNum}</display:column>
    <display:column title="Urusan">
        ${row.kodUrusan.kod} - ${row.kodUrusan.nama}
    </display:column>
    <display:column title="ID Permohonan/Perserahan" >
        ${row.idPermohonan}
    </display:column>
    <display:column title="Papar">
         
            <button name="paparAP${row_rowNum}" 
                onclick="papar('${actionBean.dokumen.idDokumen}');" 
                class="btn">Papar</button>
        
    </display:column>
   
</display:table>

    </s:form>

<%--
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
</applet> --%>
<applet code="etanah.dokumen.print.DocumentPrinter" ARCHIVE="${pageContext.request.contextPath}/PrintApplet.jar,
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
</body>
</html>