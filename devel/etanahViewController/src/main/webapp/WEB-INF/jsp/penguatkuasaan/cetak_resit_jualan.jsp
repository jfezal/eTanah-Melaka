<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<stripes:form beanclass="etanah.view.penguatkuasaan.BayaranJualanActionBean">
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />
            <title>Kaunter: Cetak Resit</title>

        </head>
        <body>

            <%--<script language="javascript">--%>
            <script type="text/javascript">

                function doPrintViaApplet (docId) {
                    var a = document.getElementById('applet');
                    a.printDocument(docId.toString());
                }

                function doViewReport(v) {
                    var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
                    window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
                }

                function bayarSemula(){
                    alert("bayar kembali");
                    var url = '${pageContext.request.contextPath}/penguatkuasaan/bayaran_jualan?Kembali';
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
                        ${actionBean.resitNo} &nbsp;&nbsp;&nbsp;
                        <button class="longbtn" name="cetakResit" onclick="doViewReport('${actionBean.idDokumen}');">Cetak Resit Pembayaran</button>&nbsp;&nbsp;&nbsp;
                        <%--<button class="btn" name="cetakResit" onclick="doPrintViaApplet('${resitId}');">Cetak Resit</button>--%>
                    </p>
                </c:if>
            </fieldset>

            <c:if test="${actionBean.statusAnsuranFlag eq false}">
                <display:table  name="${actionBean.listSahBayaran}" id="row" class="tablecloth" >
                    <display:column title="Bil" >${row_rowNum}</display:column>
                    <display:column title="Urusan">
                        ${row.kodUrusan} - ${row.namaUrusan}
                    </display:column>
                    <display:column title="ID Permohonan/Perserahan" property="idPermohonan" />
                </display:table>

                <c:if test="${fn:length(actionBean.listUnpaidKompaun) ne 0}">
                    <p style="vertical-align:top">
                        <button class="longbtn" name="cetakResit" onclick="javascript: history.go(-1)">Kembali Semula</button>
                    </p>
                </c:if>
                <c:if test="${fn:length(actionBean.listUnpaidPembelian) ne 0}">
                    <p style="vertical-align:top">
                        <button class="longbtn" name="cetakResit" onclick="javascript: history.go(-1)">Kembali Semula</button>
                    </p>
                </c:if>
            </c:if>

            <c:if test="${actionBean.statusAnsuranFlag eq true}">
                <display:table  name="${actionBean.listBayaranAnsuran}" id="row" class="tablecloth" >
                    <display:column title="Bil" >${row_rowNum}</display:column>
                    <display:column title="Urusan">
                        ${row.kodUrusan} - ${row.namaUrusan}
                    </display:column>
                    <display:column title="ID Permohonan/Perserahan" property="idPermohonan" />
                </display:table>
                <%--paid amaun : ${actionBean.paidAmaun} <br>
                amaunNeedToPay : ${actionBean.amaunNeedToPay} <br>--%>
                <c:if test="${actionBean.paidAmaun ne actionBean.amaunNeedToPay}">
                    <p style="vertical-align:top">
                        <button class="longbtn" name="cetakResit" onclick="javascript: history.go(-1)">Kembali Semula</button>
                    </p>
                </c:if>

            </c:if>


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

        </body>
    </html>
</stripes:form>