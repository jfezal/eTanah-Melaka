<%-- 
    Document   : bayaran_pelbagai_2
    Created on : Apr 13, 2010, 2:44:24 PM
    Author     : abdul.hakim
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<div align="center">
     <table style="width:99.2%" bgcolor="green">
            <tr><td width="100%" height="20" ><div style="background-color: green;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Bayaran Pelbagai</font>
            </div></td></tr>
        </table>
</div>
<script language="javascript" >
        $(document).ready(function() {
            blinkFont();
            });
</script>
<script type="text/javascript">
    function cetak(f, resit){

        var kodNegeri = document.getElementById('negeri');
        var form = $(f).formSerialize();
        var report =null;

        if(kodNegeri.value == 'melaka'){
            report = 'HSLResitPelbagai_MLK.rdf';
        }else{
            report = 'HSLResitPelbagai.rdf';
        }
        var url = "reportName="+report+"%26report_p_id_kew_dok="+resit;

        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url="+url, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");

        <%--window.open("${pageContext.request.contextPath}/reportGeneratorServlet?reportName="+report+"&report_p_id_kew_dok="+resit, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700")--%>
    }

    function doPrintBelakangResit(caraByrId){
        var a = document.getElementById('applet2');
        a.printChequeInfo(caraByrId.toString());
    }
    
    function blinkFont(){
      document.getElementById("bal").style.color="red"
      setTimeout("setblinkFont()",300);
    }

    function setblinkFont(){
      document.getElementById("bal").style.color=""
      setTimeout("blinkFont()",300);
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.hasil.BayaranPelbagaiActionBean" id="bayaran_pelbagai">
<s:errors /><s:messages/>
<%--<s:text name="report" id="report" />--%>
<s:text name="negeri" id="negeri" style="display:none;"/>
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
                <display:column property="caraBayaran.amaun" title="Amaun (RM)" style="text-align:right" format="{0,number, 0.00}"/>
                <display:column title="Cetak Pengesahan Cek/WP/KW" style="text-align:center">
                    <c:choose >
                        <c:when test="${line.caraBayaran.kodCaraBayaran.kod eq 'T'
                                                or line.caraBayaran.kodCaraBayaran.kod eq 'KK'
                                                or line.caraBayaran.kodCaraBayaran.kod eq 'VS'}">
                            <s:button name=" " disabled="true" value="Cetak" class="btn"/>
                        </c:when>
                        <c:otherwise><s:button name=" " value="Cetak" class="btn" onclick="doPrintBelakangResit('${line.caraBayaran.idCaraBayaran}');"/></c:otherwise>
                    </c:choose>
                </display:column>
            </display:table>
        </div>
    </fieldset>
</div>
<p></p>
<div class="subtitle">
    <fieldset class="aras1">
        <legend>Maklumat Pembayaran</legend>
        <div class="content" align="center">
            <display:table class="tablecloth" name="${actionBean.senaraiTransaksi}" requestURI="kutipanHasil" id="line">
                <display:column title="Bil." ><div align="center">${line_rowNum}</div></display:column>
                <display:column title="Transaksi" style="width:500px">
                    <b>${line.kodTransaksi.kod} - ${line.kodTransaksi.nama}</b>
                    <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${line.kodUrusan} - ${line.perihal}
                    <c:if test="${line.permohonan.idPermohonan ne null}">
                        <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nombor Fail : ${line.permohonan.idPermohonan}
                    </c:if>
                </display:column>
                <%--<display:column property="kodUrusan" title="Kod Urusan"/>--%>
                <display:column property="dokumenKewangan.idDokumenKewangan" title="Nombor Resit"/>
                <display:column title="Tarikh Transaksi" property="infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss aa}"/>
                <display:column title="Kuantiti" property="kuantiti"/>
                <display:column property="amaun" title="Amaun (RM)" style="text-align:right" format="{0,number, 0.00}"/>
                <display:column title="Cetak">
                    <s:button name=" " value="Cetak" class="btn" onclick="cetak(this.form, '${line.dokumenKewangan.idDokumenKewangan}');"/>
                </display:column>
                <display:footer>
                    <tr>
                        <td colspan="5" align="right"><div align="right"><b>Jumlah (RM) : </b></div></td>
                        <td class="number" align="right"><div align="right"><fmt:formatNumber value="${actionBean.jumlah}" pattern="0.00"/></div></td>
                    </tr>
                    <tr>
                        <td colspan="5" align="right"><div align="right"><b>Jumlah Yang Dibayar (RM) : </b></div></td>
                        <td class="number"><div align="right"><fmt:formatNumber value="${actionBean.jumlahBayar}" pattern="0.00" /></div></td>
                    </tr>
                    <c:if test="${actionBean.balanceFlag}">
                        <tr>
                            <td colspan="5" align="right"><div align="right"><b>Baki Yang Perlu Dipulangkan (RM) : </b></div></td>
                            <td class="number"><div align="right" id="bal"><fmt:formatNumber value="${actionBean.returnBalance}" pattern="0.00" /></div></td>
                        </tr>
                    </c:if>
                </display:footer>
            </display:table>
        </div>
    </fieldset>
</div>

<div align="center"><table border="0" bgcolor="green" style="width:99.2%">
    <tr>
        <td align="right">
            <s:submit name="kembali" value="Menu Utama" class="btn"/>
        </td>
    </tr>
    </table>
</div>
    <c:set value="05" var="negeri"/>
         <c:if test="${actionBean.negeri eq 'melaka'}">
            <c:set value="04" var="negeri"/>
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

</s:form>