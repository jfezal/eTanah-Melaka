<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<stripes:form beanclass="etanah.view.kaunter.BayaranPerihalPengambilanActionBean">
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
}

function kembali() {
            var url = '${pageContext.request.contextPath}/kaunter/BayaranPerihalPengambilan?setKembali';

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');

    }

</script>

<p class=title>Cetak Resit dan Lain-lain</p>

<stripes:messages />
<stripes:errors />

<fieldset class="aras1">
    <legend>Maklumat Urusan</legend>
    <%--<p><label>Jumlah Urusan Didaftar :</label>${fn:length(actionBean.listBayaran)}--%>
    
    <c:if test="${!empty actionBean.resitNo}">
        
        <p style="vertical-align:top"><label for="noResit">No. Resit :</label>
            ${actionBean.resitNo} <%--&nbsp;&nbsp;&nbsp;<stripes:button class="btn" name="setKembali" value="Kembali" onclick="kembali();"/>--%>&nbsp;&nbsp;&nbsp;<button class="btn" name="cetakResit"
                onclick="doPrintViaApplet('${resitId}');">Cetak Resit</button>
        </p>
    </c:if>
</fieldset>

<display:table  name="${actionBean.listSahBayaran}" id="row" class="tablecloth" >
    <display:column title="Bil" >${row_rowNum}</display:column>
    <display:column title="Urusan">
        ${row.kodUrusan} - ${row.namaUrusan}
    </display:column>
    <display:column title="ID Permohonan/Perserahan" property="idPermohonan" />
<%--    <display:column title="Akuan Penerimaan">
            <button name="cetakAP${row_rowNum}" value="Cetak"
                onclick="doPrintViaApplet('${actionBean.listBayaran[(row_rowNum - 1)].listBayaran.idDokumen}');"
                class="btn">Cetak</button>
    </display:column>--%>
</display:table>


<%--<stripes:form beanclass="etanah.view.kaunter.PermohonanKaunter">
       <stripes:submit name="step1" value="Kembali ke Utama" class="longbtn"/>
</stripes:form>--%>

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

</body>
</html>
</stripes:form>