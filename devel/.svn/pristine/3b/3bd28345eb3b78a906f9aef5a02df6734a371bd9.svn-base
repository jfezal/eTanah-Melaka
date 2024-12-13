<%-- 
    Document   : bayaran_ansuran_3
    Created on : Dec 30, 2010, 12:33:16 PM
    Author     : abdul.hakim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: SPOC Khusus</font>
                </div>
            </td>
        </tr>
    </table></div>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    function doPrintCukai(docId){
        var a = document.getElementById('applet2');
        a.printTaxInfo(docId.toString());
    }

    function doPrintBelakangResit(caraByrId){
        var a = document.getElementById('applet2');
        a.printChequeInfo(caraByrId.toString());
    }
</script>
<s:form beanclass="etanah.view.stripes.hasil.BayaranAnsuranActionBean" id="bayaran_ansuran">

    <font color="red"><b><s:messages/></b></font>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pembayaran</legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiDKB}" requestURI="kutipanHasil" id="line">
                    <display:column title="Bil." ><div align="center">${line_rowNum}</div></display:column>
                    <display:column property="caraBayaran.kodCaraBayaran.nama" title="Cara Bayaran"/>
                    <display:column title="Bank">
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
                    <display:column title="Cetak Pengesahan Cek/PO/MO" style="text-align:center">
                        <c:if test="${line.caraBayaran.kodCaraBayaran.kod eq 'T'}">
                            <s:button name=" " disabled="true" value="Cetak" class="btn"/>
                        </c:if>
                        <c:if test="${line.caraBayaran.kodCaraBayaran.kod ne 'T'}">
                            <s:button name=" " value="Cetak" class="btn" onclick="doPrintBelakangResit('${line.caraBayaran.idCaraBayaran}');"/>
                        </c:if>
                    </display:column>
                </display:table>
            </div>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Kutipan</legend>
            <p>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiKewDokumen}" id="line">
                    <display:column title="Bil" ><div align="center">${line_rowNum}</div></display:column>
                    <display:column title="Jenis Transaksi">61401 - CUKAI TANAH SEMASA</display:column>
                    <display:column property="akaun.hakmilik.idHakmilik" title="ID Hakmilik"/>
                    <%--<display:column property="akaunKredit.noAkaun" title="Nombor Akaun"/>--%>
                    <display:column property="akaun.hakmilik.noLot" title="Nombor Lot"/>
                    <display:column property="idDokumenKewangan" title="Nombor Resit"/>
                    <display:column property="amaunBayaran" title="Amaun (RM)" style="text-align:right" format="{0,number, 0.00}"/>
                    <display:column title="Cetak Resit">
                        <s:button name=" " onclick="doPrintCukai('${line.idDokumenKewangan}');" value="Cetak" class="btn"/>
                    </display:column>
                    <display:footer>
                        <tr>
                            <td colspan="5" align="right"><div align="right"><b>Jumlah (RM) : </b></div></td>
                            <td class="number" align="right"><div align="right"><fmt:formatNumber value="${actionBean.total}" pattern="0.00"/></div></td>
                        </tr>
                        <tr>
                            <td colspan="5" align="right"><div align="right"><b>Jumlah Yang Dibayar (RM) : </b></div></td>
                            <td class="number"><div align="right"><fmt:formatNumber value="${actionBean.jumCaraBayar}" pattern="0.00" /></div></td>
                        </tr>
                    </display:footer>
                </display:table>
            </div>
            <%--<div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.transList}" id="line">
                    <display:column title="Bil" ><div align="center">${line_rowNum}</div></display:column>
                    <display:column title="Jenis Transaksi">61401 - CUKAI TANAH SEMASA</display:column>
                    <display:column property="akaunKredit.hakmilik.idHakmilik" title="ID Hakmilik"/>
                    <display:column property="akaunKredit.noAkaun" title="Nombor Akaun"/>
                    <display:column property="akaunKredit.hakmilik.noLot" title="Nombor Lot"/>
                    <display:column property="dokumenKewangan.idDokumenKewangan" title="Nombor Resit"/>
                    <display:column property="amaun" title="Amaun (RM)" style="text-align:right" format="{0,number, 0.00}"/>
                    <display:column title="Cetak Resit">
                        <s:button name=" " onclick="doPrintCukai('${line.dokumenKewangan.idDokumenKewangan}');" value="Cetak" class="btn"/>
                    </display:column>
                    <display:footer>
                        <tr>
                            <td colspan="5" align="right"><div align="right"><b>Jumlah (RM) : </b></div></td>
                            <td class="number" align="right"><div align="right"><fmt:formatNumber value="${actionBean.total}" pattern="0.00"/></div></td>
                        </tr>
                        <tr>
                            <td colspan="5" align="right"><div align="right"><b>Jumlah Yang Dibayar (RM) : </b></div></td>
                            <td class="number"><div align="right"><fmt:formatNumber value="${actionBean.jumCaraBayar}" pattern="0.00" /></div></td>
                        </tr>
                    </display:footer>
                </display:table>
            </div>--%>
            </p>
        </fieldset>
    </div>

    <div align="center"><table style="width:99.2%" bgcolor="green">
            <tr>
                <td align="right">
                    <%--<s:button name=" " onclick="doPrintCukai('${line.dokumenKewangan.idDokumenKewangan}');" value="Cetak" class="btn"/>--%>
                    <s:submit name="Step1" value="Menu Utama" class="btn"/>
                </td>
            </tr>
        </table></div>

             <c:set value="05" var="negeri"/>
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