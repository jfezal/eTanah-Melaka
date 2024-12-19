<%--
    Document   : cetak_semula_resit_1
    Created on : Mar 24, 2010, 4:32:56 PM
    Author     : abdul.hakim
--%>

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
  $(document).ready(function() {
      var id = document.getElementById('hm');
      var l = id.value.length;
      if(l>1){
          

          $("#ctk").show();
          $("#ctk1").hide();
              
          
      }else{
            $("#ctk1").show();
            $("#ctk").hide();}
      
  });
  
</script>

<script type="text/javascript">

    function cetak(f,id1){

        var form = $(f).formSerialize();
        var report = null;
        var negeri = '${actionBean.kodNegeri}';
        if(negeri == 'melaka'){
            report = 'SPOCCetakanSemulaResit_MLK.rdf';
        }else{
            report = 'SPOCCetakanSemulaResit.rdf';
        }

        var url = "reportName="+report+"%26report_p_id_kew_dok="+id1;

        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url="+url, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
        
      <%--  window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+form+"&reportName="+report+"&report_p_id_kew_dok="+id1, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700")--%>
    }
    function cetak1(f,id2){

        var form = $(f).formSerialize();
        var report = null;
        var negeri = '${actionBean.kodNegeri}';
        if(negeri == 'negeri'){
            report = 'HSLResitPelbagai.rdf';
        }

        var url = "reportName="+report+"%26report_p_id_kew_dok="+id2;

        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url="+url, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
     }
     
     function cetakPelbagai(f, resit){
        var kodNegeri = '${actionBean.kodNegeri}';
        var form = $(f).formSerialize();
        var report =null;

        if(kodNegeri.value == 'negeri'){
            report = 'HSLResitPelbagai.rdf';
        }else{
            report = 'HSLResitPelbagai_MLK.rdf';
        }
        var url = "reportName="+report+"%26report_p_id_kew_dok="+resit;

        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url="+url, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    }

    function doPrintCukai1(docId){
        var a = document.getElementById('applet2');
        a.printTaxInfo(docId.toString());
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.hasil.CetakSemulaResitActionBean" id="cetak_semula_resit">
    <s:errors />
    <div class="subtitle">
        <table style="width:99.2%" bgcolor="green">
            <tr><td width="100%" height="20" ><div style="background-color: green;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Cetak Semula Resit</font>
            </div></td></tr>
        </table>
        <fieldset class="aras1">
            <legend>Maklumat Resit</legend>
           <p>
                <label>Daerah Terimaan :</label>
                ${actionBean.dokumenKewangan.cawangan.name}&nbsp;
            </p>

            <c:if test="${actionBean.dokumenKewangan.noRujukanManual == null}">
            <p>
                <label>Nombor Resit:</label>
                ${actionBean.dokumenKewangan.idDokumenKewangan}&nbsp;
            </p>
            </c:if>
            <c:if test="${actionBean.dokumenKewangan.noRujukanManual != null}">
            <p>
                <label>Nombor Resit :</label>
                ${actionBean.dokumenKewangan.noRujukanManual}&nbsp;
            </p>
            </c:if>


            <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                <p>
                    <label>Nombor Akaun Cukai Tanah :</label>
                    <c:if test="${actionBean.akaun eq null}"> - </c:if>
                    <c:if test="${actionBean.akaun ne null}">${actionBean.akaun.noAkaun}</c:if>
                </p>
            </c:if>

            <p>
                <label>ID Hakmilik :</label>
                <c:if test="${actionBean.akaun eq null}"> - </c:if>
                <c:if test="${actionBean.akaun ne null}">${actionBean.akaun.hakmilik.idHakmilik}</c:if>
                <s:text name ="akaun.hakmilik.idHakmilik" id="hm" value="${actionBean.akaun.hakmilik.idHakmilik}" style="display:none"/>
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
                <label>Cara Bayaran :</label>
                <table>
                    <c:forEach items="${actionBean.senaraiCaraBayaran}" var="senarai">
                        <tr>
                            <c:if test="${senarai.caraBayaran.kodCaraBayaran eq null}"><td><font size="2">-</font></td></c:if>
                            <c:if test="${senarai.caraBayaran.kodCaraBayaran.nama ne null}">
                                <td><font size="2"><c:out value="${senarai.caraBayaran.kodCaraBayaran.nama}"/></font></td>
                            </c:if>
                        <tr>
                    </c:forEach>
                </table>&nbsp;
            </p>

            <p>
                <label>Tarikh :</label>
                <fmt:formatDate value="${actionBean.dokumenKewangan.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy"/>
                &nbsp;
            </p>

            <p>
                <label>Masa :</label>
                <fmt:formatDate value="${actionBean.dokumenKewangan.infoAudit.tarikhMasuk}" pattern="hh:mm:ss aa"/>
                &nbsp;
            </p>
            <br>
            <div align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiTransaksi}" id="line">
                    <display:column title="Bil." style="text-align:center">
                        ${line_rowNum}.
                    </display:column>
                    <display:column  title="Transaksi" style="width:800;">
                       ${line.kodTransaksi.kod} - ${line.kodTransaksi.nama}
                    </display:column>
                    <display:column  title="Amaun (RM)" style="text-align:right">
                        <c:choose>
                            <c:when test="${line.akaunKredit.kodAkaun.kod eq 'AKH'}">
                                - <fmt:formatNumber value="${line.amaun}" pattern="0.00" />
                            </c:when>
                            <c:otherwise>
                                <fmt:formatNumber value="${line.amaun}" pattern="0.00" />
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
                <c:if test="${actionBean.akaun eq null}">
                    <s:button name=" " value="Cetak Resit Pelbagai" class="longbtn" onclick="cetakPelbagai(this.form, '${actionBean.dokumenKewangan.idDokumenKewangan}')"/>
                </c:if>
                <c:if test="${actionBean.akaun ne null}">
                    <s:button name=" " onclick="doPrintCukai1('${actionBean.dokumenKewangan.idDokumenKewangan}');" value="Cetak Pada Bil" class="btn"/>
                    <s:button name=" " id="ctk"value="Cetak" class="btn" onclick="cetak(this.form, '${actionBean.dokumenKewangan.idDokumenKewangan}')"/>
                    <s:button name=" " id="ctk1" value="Cetak" class="btn" onclick="cetak1(this.form, '${actionBean.dokumenKewangan.idDokumenKewangan}')"/>
                </c:if>
                <s:submit name="main" value="Kembali" class="btn"/>
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
