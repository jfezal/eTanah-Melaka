<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<stripes:form beanclass="etanah.view.kaunter.BayaranPerihalActionBean">
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />
            <title>Kaunter: Surat Peringatan</title>

        </head>
        <body>

            <script language="javascript">

                function doPrintViaApplet (docId) {
                    var a = document.getElementById('applet');
                    a.doPrint(docId.toString());
                    //                    a.printDocument(docId.toString());

                }

                function doPrintBelakangResit(caraByrId){
                    var a = document.getElementById('applet2');
                    a.printChequeInfo(caraByrId.toString());
                }
            </script>

            

            <stripes:messages />
            <stripes:errors /><p></p>

            <fieldset class="aras1">
                <legend><font color="green">Arahan : Sila Log Keluar untuk ke peringkat seterusnya. </font></legend>
                <%--<p><label>Jumlah Urusan Didaftar :</label>${fn:length(actionBean.listBayaran)}--%>
               
            </fieldset>
           

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
                <param name="Cookie<%= i%>" value="<%= sb2.toString()%>"><%
                    }
                %>
            </applet>
        </body>
    </html>
</stripes:form>