
<!-- Document   : cetak_semula_resit_1
    Created on : Mar 24, 2010, 4:32:56 PM
    Author     : abdul.hakim-->


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloths.css"/>

<script type="text/javascript">
    function cetak(f, id1){
        var form = $(f).formSerialize();
        var report = null;
        var negeri = '${actionBean.kodNegeri}';
        if(negeri == 'melaka'){
            report = 'SPOCCetakanSemulaResitPOMO_MLK.rdf';
        }else{
            report = 'SPOCCetakanSemulaResitPOMO.rdf';
        }

        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+form+"&reportName="+report+"&report_p_id_kew_dok="+id1, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700")
    }
    function doPrintBelakangResit(caraByrId){
        var a = document.getElementById('applet2');
        a.printChequeInfo(caraByrId.toString());
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.hasil.CetakSemulaActionBean" id="cetak_semula">
    <s:errors />
    <div class="subtitle">
        <table style="width:99.2%" bgcolor="green">
            <tr><td width="100%" height="20" ><div style="background-color: green;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Cetak Semula Belakang Cek/ Wang Pos/ Kiriman Wang</font>
                    </div></td></tr>
        </table>
        <fieldset class="aras1">
            <legend>Maklumat Resit</legend>
            <p>
                <label>Daerah Terimaan :</label>
                ${actionBean.pengguna.kodCawangan.daerah.nama}&nbsp;
            </p>

            <p>
                <label>Nombor Resit :</label>
                ${actionBean.dokumenKewangan.idDokumenKewangan}&nbsp;
            </p>

            <p>
                <label>Nombor Kaunter :</label>
                ${actionBean.pengguna.idKaunter}&nbsp;
            </p>

            <p>
                <label>Operator :</label>
                ${actionBean.pengguna.idPengguna}&nbsp;
            </p>

            <p>
                <label>Nama Operator :</label>
                ${actionBean.pengguna.nama}&nbsp;
            </p>

            <p>
                <label>Amaun Diterima :</label>
                <fmt:formatNumber value="${actionBean.dokumenKewangan.amaunBayaran}" type="currency" currencySymbol="RM"/>
                &nbsp;
            </p>

            <p>
                <label>Tarikh :</label>
                <fmt:formatDate value="${actionBean.dokumenKewangan.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy"/>
                &nbsp;
            </p>

            <p>
                <label>Masa :</label>
                <fmt:formatDate value="${actionBean.dokumenKewangan.infoAudit.tarikhMasuk}" pattern="hh:mm aa"/>
                &nbsp;
            </p>
            <br>
            <div align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiCaraBayaran}" id="line">
                    <display:column title="Bil." style="text-align:center">
                        ${line_rowNum}.
                    </display:column>
                    <display:column  title="Cara Bayaran" property="caraBayaran.kodCaraBayaran.nama" style="width:300;"/>
                    <display:column  title="Bank / Agensi Pembayaran" style="width:250;">
                        <c:if test="${line.caraBayaran.bank.nama eq null}">-</c:if>
                        <c:if test="${line.caraBayaran.bank.nama ne null}">${line.caraBayaran.bank.nama}</c:if>
                    </display:column>
                    <display:column  title="Cawangan">
                        <c:if test="${line.caraBayaran.bankCawangan eq null}">-</c:if>
                        <c:if test="${line.caraBayaran.bankCawangan ne null}">${line.caraBayaran.bankCawangan}</c:if>
                    </display:column>
                    <display:column  title="No. Rujukan">
                        <c:if test="${line.caraBayaran.noRujukan eq null}">-</c:if>
                        <c:if test="${line.caraBayaran.noRujukan ne null}">${line.caraBayaran.noRujukan}</c:if>
                    </display:column>
                    <display:column  title="Amaun (RM)" style="text-align:right">
                        <fmt:formatNumber value="${line.caraBayaran.amaun}" pattern="0.00" />
                    </display:column>
                    <display:column  title="Cetak" style="width:150;text-align:center;">
                        <c:choose>
                            <c:when test="${line.caraBayaran.kodCaraBayaran.kod eq 'T'
                                            or line.caraBayaran.kodCaraBayaran.kod eq 'KK'
                                            or line.caraBayaran.kodCaraBayaran.kod eq 'VS'
                                            or line.caraBayaran.kodCaraBayaran.kod eq 'DK'}">
                                <s:button name=" " disabled="true" value="Cetak" class="btn"/>
                            </c:when>
                            <c:otherwise>
                                <%--<s:button name=" " value="Cetak" class="btn" onclick="cetak(this.form, '${line.caraBayaran.idCaraBayaran}');"/><br><br>--%>
                                <s:button name=" " value="Cetak Belakang Cek" class="longbtn" onclick="doPrintBelakangResit('${line.caraBayaran.idCaraBayaran}');"/>
                            </c:otherwise>
                        </c:choose>
                    </display:column>
                </display:table>
                <br>
            </div>
        </fieldset>
    </div>
    <table border="0" bgcolor="green" style="width:99.2%">
        <tr>
            <td align="right">
                <%--<s:button name=" " value="Cetak" class="btn" onclick="" disabled="${actionBean.flag}"/>--%>
                <s:submit name="main" value="Kembali" class="btn"/>
            </td>
        </tr>
    </table>

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
</s:form>
