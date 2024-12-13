<%-- 
    Document   : bayaran_agensi_2
    Created on : Feb 22, 2011, 7:18:35 PM
    Author     : abdul.hakim
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloths.css"/>

<div align="center">
    <table style="width:99.2%" bgcolor="green">
        <tr><td width="100%" height="20" ><div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Bayaran Pelbagai</font>
                </div></td></tr>
    </table>
</div>
<script type="text/javascript">
    function cetakResit(f, resit){
        var form = $(f).formSerialize();
        var report = null;
        <%--var negeri = '${actionBean.kodNegeri}';--%>
                var negeri = document.getElementById('kNegeri');
        if(negeri.value == 'melaka'){
            report = 'HSLResitAgensi_MLK.rdf';
        }else{
             <%--report = 'HSLResitPelbagai.rdf';--%>
            report = 'HSLResitAgensi.rdf';
        }
        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+form+"&reportName="+report+"&report_p_id_kew_dok="+resit, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700")
    }

    function doPrintBelakangResit(caraByrId){
        var a = document.getElementById('applet2');
        a.printChequeInfo(caraByrId.toString());
    }
</script>
<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<stripes:form beanclass="etanah.view.stripes.hasil.BayaranAgensiActionBean" id="bayar_agensi">
    <stripes:errors />
    <stripes:text name="kodNegeri" value="${actionBean.kodNegeri}" style="display:none;" id="kNegeri"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pembayaran</legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.dkbList}" requestURI="kutipanHasil" id="line">
                    <display:column title="Bil." ><div align="center">${line_rowNum}</div></display:column>
                    <display:column property="caraBayaran.kodCaraBayaran.nama" title="Cara Bayaran"/>
                    <display:column title="Bank / Agensi Pembayaran">
                        <c:if test="${line.caraBayaran.bank.nama eq null}">-</c:if>
                        <c:if test="${line.caraBayaran.bank.nama ne null}">${line.caraBayaran.bank.nama}</c:if>
                    </display:column>
                    <display:column title="Cawangan">
                        <c:if test="${line.caraBayaran.bankCawangan eq null}">-</c:if>
                        <c:if test="${line.caraBayaran.bankCawangan ne null}">${line.caraBayaran.bankCawangan}</c:if>
                    </display:column>
                    <display:column title="Nombor Rujukan">
                        <c:if test="${line.caraBayaran.noRujukan eq null}">-</c:if>
                        <c:if test="${line.caraBayaran.noRujukan ne null}">${line.caraBayaran.noRujukan}</c:if>
                    </display:column>
                    <display:column property="caraBayaran.amaun" title="Amaun (RM)" style="text-align:right" format="{0,number, #,###,###.00}"/>
                    <display:column title="Cetak Pengesahan Cek/WP/KW" style="text-align:center">
                        <c:choose >
                            <c:when test="${line.caraBayaran.kodCaraBayaran.kod eq 'T'
                                            or line.caraBayaran.kodCaraBayaran.kod eq 'KK'
                                            or line.caraBayaran.kodCaraBayaran.kod eq 'EF'
                                            or line.caraBayaran.kodCaraBayaran.kod eq 'VS'}">
                                <stripes:button name=" " disabled="true" value="Cetak" class="btn"/>
                            </c:when>
                            <c:otherwise>
                                <stripes:button name=" " value="Cetak" class="btn" onclick="doPrintBelakangResit('${line.caraBayaran.idCaraBayaran}');"/>
                                <%--<stripes:button name=" " value="Cetak" class="btn" onclick="cetak(this.form, '${line.caraBayaran.idCaraBayaran}');"/>--%>
                            </c:otherwise>
                        </c:choose>
                    </display:column>
                </display:table>
            </div>
        </fieldset>
    </div>

    <%--<div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pembayaran</legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiTransaksi}" requestURI="kutipanHasil" id="line">
                    <display:column title="Bil." ><div align="center">${line_rowNum}</div></display:column>
                    <display:column property="kodTransaksi.nama" title="Transaksi" style="width:300px"/>
                    <display:column property="dokumenKewangan.idDokumenKewangan" title="Nombor Resit"/>
                    <display:column title="Tarikh Transaksi" property="infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss aa}"/>
                    <display:column title="Kuantiti" property="kuantiti"/>
                    <display:column property="amaun" title="Amaun (RM)" style="text-align:right" format="{0,number, 0.00}"/>
                    <display:column title="Cetak">
                        <stripes:button name="cetak" value="Cetak" class="btn" onclick="cetakResit(this.form, '${line.dokumenKewangan.idDokumenKewangan}');"/>
                    </display:column>
                </display:table>
            </div>
        </fieldset>
    </div>--%>

    <div class="subtitle">
            <fieldset class="aras1">
                <legend>Hasil Carian</legend>
                <div align="center">
                    <table class="tablecloth">
                        <tr>
                            <th>Bil.</th>
                            <%--<th>Kod Transaksi</th>--%>
                            <th width="500">Transaksi</th>
                            <th>Nombor Resit</th>
                            <th>Tarikh Transaksi</th>
                            <th>Amaun (RM)</th>
                        </tr>
                        <tr>
                            <td>1.</td>
                            <%--<td>61401</td>--%>
                            <td>61401 - Cukai Tanah Semasa</td>
                            <td>${actionBean.dokumenKewangan.idDokumenKewangan}</td>
                            <td><fmt:formatDate value="${actionBean.dokumenKewangan.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy HH:mm:ss a"/></td>
                            <td><div align="right"><fmt:formatNumber value="${actionBean.cukaiTanah}" pattern="#,##0.00"/></div></td>
                        </tr>
                        <tr>
                            <td>2.</td>
                            <%--<td>61402</td>--%>
                            <td>61402 - Cukai Tanah Tunggakan</td>
                            <td>${actionBean.dokumenKewangan.idDokumenKewangan}</td>
                            <td><fmt:formatDate value="${actionBean.dokumenKewangan.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy HH:mm:ss a"/></td>
                            <td><div align="right"><fmt:formatNumber value="${actionBean.tunggakanCukaiTanah}" pattern="#,##0.00"/></div></td>
                        </tr>
                        <tr>
                            <td>3.</td>
                            <%--<td>76152</td>--%>
                            <td>76152 - Notis dan Denda Lewat Bayaran Cukai Tanah</td>
                            <td>${actionBean.dokumenKewangan.idDokumenKewangan}</td>
                            <td><fmt:formatDate value="${actionBean.dokumenKewangan.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy HH:mm:ss a"/></td>
                            <td><div align="right"><fmt:formatNumber value="${actionBean.dendaLewat}" pattern="#,##0.00"/></div></td>
                        </tr>
                        <tr>
                            <td>4.</td>
                            <%--<td>76152</td>--%>
                            <td>99011 - Notis 6A</td>
                            <td>${actionBean.dokumenKewangan.idDokumenKewangan}</td>
                            <td><fmt:formatDate value="${actionBean.dokumenKewangan.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy HH:mm:ss a"/></td>
                            <td><div align="right"><fmt:formatNumber value="${actionBean.notis}" pattern="#,##0.00"/></div></td>
                        </tr>
                    </table>
                </div>
                <br>
            </fieldset>
        </div>

    <div align="center"><table border="0" bgcolor="green" style="width:99.2%">
            <tr>
                <td align="right">
                    <stripes:button name="cetak" value="Cetak" class="btn" onclick="cetakResit(this.form, '${actionBean.dokumenKewangan.idDokumenKewangan}');"/>
                    <stripes:submit name="Step5" value="Menu Utama" class="btn"/>
                </td>
            </tr>
        </table>
    </div>

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
</stripes:form>