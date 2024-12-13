
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<script type="text/javascript">
    function cetak(f, id2){
        var queryString = $(f).formSerialize()
        window.open("${pageContext.request.contextPath}/hasil/kutipan_hasil?cetak&"+queryString+"&idKew="+id2, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=900,height=300");
    }
</script>
<script type="text/javascript">
    function edit(f, id1, id2){
        var queryString = $(f).formSerialize()
        window.open("${pageContext.request.contextPath}/hasil/kutipan_hasil?cetak2&"+queryString+"&idHakmilik="+id1+"&idKew="+id2, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
                <%--doCetakViaApplet1(id2);--%>
                <%--edit1(f, id1, id2);--%>
    }

    function edit1(f, id1, id2){
        var queryString = $(f).formSerialize()
        window.open("${pageContext.request.contextPath}/hasil/kutipan_hasil?cetakReceipt&"+queryString+"&idHakmilik="+id1+"&idKew="+id2, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
                <%--doCetakResitViaApplet(id2);--%>
    }

    function doPrintViaApplet (docId) {
        var a = document.getElementById('applet');
        a.printDocument(docId.toString());
    }

    function doPrintCukai(docId){
        var a = document.getElementById('applet2');
        a.printTaxInfo(docId.toString());
    }

    function doCetakViaApplet (kewid) {
        var a = document.getElementById('applet');
        a.printOnTheFly('HSLResitBayaran2Frame.rdf','p_id_kew_dok',kewid.toString());
        doCetakResitViaApplet(kewid);
    }
    function doCetakResitViaApplet (kewid) {
        var a = document.getElementById('applet');
        a.printOnTheFly('HSLResitBayaran2FramePageTwo.rdf','p_id_kew_dok',kewid.toString());
    }
    function doPrintBelakangResit(caraByrId){
        var a = document.getElementById('applet2');
        a.printChequeInfo(caraByrId.toString());
    }

</script>
<s:form beanclass="etanah.view.stripes.hasil.KutipanHasilActionBean">
    <table width="100%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <%--<c:if test="${actionBean.header eq true}">--%>
                        <%--<font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Kutipan Cukai</font>--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${actionBean.header eq false}">--%>
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Gantian Resit Batal</font>
                    <%--</c:if>--%>
                </div>
            </td>
        </tr>
    </table>

    <font color="red"><b><s:messages/></b></font>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pembayaran</legend>
            <p>
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
                                <c:otherwise>
                                    <%--<s:button name=" " value="Cetak" class="btn" onclick="cetak(this.form, '${line.caraBayaran.idCaraBayaran}');"/>--%>
                                    <s:button name=" " value="Cetak" class="btn" onclick="doPrintBelakangResit('${line.caraBayaran.idCaraBayaran}');"/>
                                </c:otherwise>
                            </c:choose>
                        </display:column>
                    </display:table>
                </div>
            </p>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Kutipan</legend>
            <p>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.transList}" id="line">
                        <display:column title="Bil" ><div align="center">${line_rowNum}</div></display:column>
                              <display:column  title="Jenis Transaksi" >
                            ${line.kodTransaksi.kod} - ${line.kodTransaksi.nama}</display:column>
                       <display:column property="dokumenKewangan.idDokumenKewangan" title="Nombor Resit"/>
                        <display:column property="dokumenKewangan.amaunBayaran" title="Amaun (RM)" style="text-align:right" format="{0,number, 0.00}"/>
                        <display:column title="Cetak Resit">
                                <%--<s:button name=" " onclick="edit(this.form, '${line.akaunKredit.hakmilik.idHakmilik}','${line.dokumenKewangan.idDokumenKewangan}');" value="Cetak" class="btn"/>--%>
                                <s:button name=" " onclick="doPrintCukai('${line.dokumenKewangan.idDokumenKewangan}');" value="Cetak" class="btn"/>
                        </display:column>
                        <display:footer>
                            <tr>
                             
                                        <td colspan="4" align="right"><div align="right"><b>Jumlah (RM) : </b></div></td>
                           
                                <td class="number" align="right"><div align="right"><fmt:formatNumber value="${actionBean.jumlahCaj}" pattern="0.00"/></div></td>
                            </tr>
                            <tr>
                              
                                        <td colspan="4" align="right"><div align="right"><b>Jumlah Yang Dibayar (RM) : </b></div></td>
                        
                                <td class="number"><div align="right"><fmt:formatNumber value="${actionBean.jumCaraBayar}" pattern="0.00" /></div></td>
                            </tr>
                        </display:footer>
                    </display:table>
                </div>
            </p>
        </fieldset>
    </div>
    <table border="0" bgcolor="green" width="100%">
        <tr>
            <td align="right">
                <%--<s:submit name=" " value=" Cetak Resit " class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>--%>
                <s:button name=" " value="Cetak Semua" class="btn" onclick="doCetakViaApplet(${actionBean.kewDokID});"/>&nbsp;
                <s:submit name="main" value="Menu Utama" class="btn"/>
            </td>
        </tr>
    </table>
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