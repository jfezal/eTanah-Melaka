<%-- 
    Document   : ansuran_3
    Created on : Jan 19, 2010, 3:00:00 PM
    Author     : abdul.hakim
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

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
    function cetak2(f, id2){
        var queryString = $(f).formSerialize()
        window.open("${pageContext.request.contextPath}/hasil/ansuran?cetak&"+queryString+"&idKew="+id2, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }

        function cetak(f, id1){
        var form = $(f).formSerialize();
        var report = null;
        var negeri = '${actionBean.kodNegeri}';
        if(negeri == 'melaka'){
            report = 'HSLResitPelbagai_MLK.rdf';
        }else{
            report = 'HSLResitPelbagai.rdf';
        }

        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+form+"&reportName="+report+"&report_p_id_kew_dok="+id1, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700")
    }

    function doPrintBelakangResit(caraByrId){
        var a = document.getElementById('applet2');
        <%--alert(caraByrId);--%>
        a.printChequeInfo(caraByrId.toString());
    }
</script>
<s:form beanclass="etanah.view.stripes.hasil.AnsuranActionBean">
    
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
                    <display:column title="Cetak Pengesahan Cek/WP/KW" style="text-align:center">
                            <c:choose >
                                <c:when test="${line.caraBayaran.kodCaraBayaran.kod eq 'T'
                                                or line.caraBayaran.kodCaraBayaran.kod eq 'KK'
                                                or line.caraBayaran.kodCaraBayaran.kod eq 'VS'
                                                or line.caraBayaran.kodCaraBayaran.kod eq 'DK'
                                                or line.caraBayaran.kodCaraBayaran.kod eq 'AM'}">
                                        <s:button name=" " disabled="true" value="Cetak" class="btn"/>
                                </c:when>
                                <c:otherwise>
                                    <%--<s:button name=" " value="Cetak" class="btn" onclick="cetak(this.form, '${line.caraBayaran.idCaraBayaran}');"/>--%>
                                    <s:button name=" " value="Cetak" class="btn" onclick="doPrintBelakangResit('${line.caraBayaran.idCaraBayaran}');"/>
                                </c:otherwise>
                            </c:choose>
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
                <display:table class="tablecloth" name="${actionBean.transList}" id="line">
                    <display:column title="Bil" ><div align="center">${line_rowNum}</div></display:column>
                    <display:column property="akaunKredit.kodAkaun.nama" title="Jenis Urusan"/>
                    <display:column property="akaunKredit.hakmilik.idHakmilik" title="ID Hakmilik"/>
                    <display:column property="akaunKredit.noAkaun" title="Nombor Akaun"/>
                    <display:column property="akaunKredit.hakmilik.noLot" title="Nombor Lot"/>
                    <display:column property="dokumenKewangan.idDokumenKewangan" title="Nombor Resit"/>
                    <display:column property="dokumenKewangan.amaunBayaran" title="Amaun (RM)" style="text-align:right" format="{0,number, 0.00}"/>
                    <display:column title="Cetak Resit">
                        <s:button name=" " onclick="cetak(this.form, '${line.dokumenKewangan.idDokumenKewangan}');" value="Cetak" class="btn"/>
                    </display:column>
                    <display:footer>
                        <tr>
                            <td colspan="6" align="right"><div align="right"><b>Jumlah (RM) : </b></div></td>
                            <td class="number" align="right"><div align="right"><fmt:formatNumber value="${actionBean.total}" pattern="0.00"/></div></td>
                        </tr>
                        <tr>
                            <td colspan="6" align="right"><div align="right"><b>Jumlah Yang Dibayar (RM) : </b></div></td>
                            <td class="number"><div align="right"><fmt:formatNumber value="${actionBean.jumCaraBayar}" pattern="0.00" /></div></td>
                        </tr>
                    </display:footer>
                </display:table>
            </div>
            </p>
        </fieldset>
    </div>

    <div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td align="right">
                <%--<s:button name=" " value="Cetak Resit" onclick="cetak(this.form, '${actionBean.dokumenKewangan.idDokumenKewangan}')" class="btn"/>&nbsp;--%>
                <s:submit name="Step5" value="Menu Utama" class="btn"/>
            </td>
        </tr>
    </table></div>
    <c:set value="05" var="negeri"/>
         <c:if test="${actionBean.kodNegeri eq 'melaka'}">
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