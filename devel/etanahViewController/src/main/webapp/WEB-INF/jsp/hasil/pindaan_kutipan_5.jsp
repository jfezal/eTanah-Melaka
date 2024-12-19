<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Pindaan Kutipan</font>
                    </c:if>
                    <c:if test="${actionBean.kodNegeri ne 'melaka'}">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Pindahan Kutipan</font>
                    </c:if>
                </div>
            </td>
        </tr>
</table></div>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    function edit(f, id1, id2){
        var queryString = $(f).formSerialize()
        <%--window.open("${pageContext.request.contextPath}/kutipanHasil?cetak&"+queryString, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
        --%>window.open("${pageContext.request.contextPath}/hasil/pindaan_kutipan?cetak2&"+queryString+"&idHakmilik="+id1+"&idKew="+id2, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
                doPrintViaApplet(id2);
    }
    
    function doPrintViaApplet (docId) {
        //alert('tsttt');
        var a = document.getElementById('applet');
        a.printDocument(docId.toString());
    }
</script>
<s:form beanclass="etanah.view.stripes.hasil.PindaanKutipanActionBean">
    <fieldset class="aras1">
        <legend>Maklumat Kutipan Baru</legend>
        <div class="content" align="center">
            <p>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senarai}" id="line">
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <%--<display:column property="kodTransaksi.nama" title="Jenis Urusan" sortable="true"/>--%>
                    <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                    <display:column property="akaun.noAkaun" title="No Akaun"/>
                    </c:if>
                    <c:if test="${actionBean.kodNegeri ne 'melaka'}">
                    <display:column property="akaun.noAkaun" title="ID Hakmilik"/>
                    </c:if>
                    <display:column property="idDokumenKewangan" title="Nombor Resit" />
                    <display:column property="infoAudit.tarikhMasuk" title="Tarikh Bayar" format="{0,date,dd/MM/yyyy hh:mm aa}"/>
                    <display:column style="text-align:right" property="amaunBayaran" title="Amaun (RM)" format="{0,number, 0.00}"/>
                    <%--<display:column>
                        <s:button name=" " value="Cetak" class="btn" onclick="edit(this.form, '${line.akaunKredit.hakmilik.idHakmilik}','${line.dokumenKewangan.idDokumenKewangan}');"/>
                    </display:column>--%>
                </display:table>
            </div>
            </p>
        </div>
        <br>
    </fieldset>

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

    <fieldset class="aras1">
        <legend></legend>
        <div align="center"><table style="width:99.2%" bgcolor="green">
            <tr align="right">
                <td><s:submit name="kembali" value="Menu Utama" class="btn"/></td>
            </tr>
        </table></div>
    </fieldset>

</s:form>
