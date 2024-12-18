<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
     <head>
          <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />
          <title>Kaunter: Cetak Resit</title>

     </head>
     <body>

          <script language="javascript">

               function doPrintViaApplet(docId) {
                    var a = document.getElementById('applet');
                    //a.doPrint(docId.toString());
                    a.printDocument(docId.toString());
               }

               function doPrintBelakangResit(caraByrId) {
                    var a = document.getElementById('applet2');
                    a.printChequeInfo(caraByrId.toString());
               }

               function doViewReport(v) {
                    var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
                    window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
               }

          </script>

          <p class=title>Cetak Resit dan Lain-lain</p>

          <stripes:messages />
          <stripes:errors />
          <c:if test="${!empty tanpaBayaran}">
               <c:if test="${empty SSHMA}">
                    <span class=instr>Untuk cetakan Salinan Sah, sila ke Pendaftar.</span><br/><br/>
               </c:if>
          </c:if>
          <fieldset class="aras1">
               <legend>Maklumat Urusan</legend>
               <c:if test="${empty tanpaBayaran}">
                    <p>
                         <c:if test="${!empty actionBean.resitNo}">
                         <p><label for="noResit">No. Resit :</label>
                              ${actionBean.resitNo} <button class="btn" name="cetakResit"
                                      onclick="doPrintViaApplet('${resitId}');">Cetak Resit</button>

                              <button class="btn" name="paparResit"
                                      onclick="doViewReport('${resitId}');">Papar Resit</button>
                         </p>
                    </c:if>
               </p>
          </c:if>
     </fieldset>
     <c:set var="printMultiple" value=""/>
     <%-- <div class="subtitle"><s:text name="kodNegeri" id="negeri" style="display:none;"/>
          <fieldset class="aras1">
              <legend>Maklumat Pembayaran</legend>
              <p>
                  <div class="content" align="center">--%>
     <stripes:form beanclass="etanah.view.daftar.CarianActionBean">
          <display:table class="tablecloth" name="${actionBean.caraBayaranList}" requestURI="/daftar/carian" id="line">
               <display:column title="Bil." ><div align="center">${line_rowNum}</div></display:column>
               <display:column property="kodCaraBayaran.nama" title="Cara Bayaran"/>
               <display:column title="Bank / Agensi Pembayaran">
                    <c:if test="${line.bank.kod eq null}">-</c:if>
                    <c:if test="${line.bank.kod ne null}">${line.bank.kod} - ${line.bank.nama}</c:if>
               </display:column>
               <display:column title="Cawangan">
                    <c:if test="${line.bankCawangan eq null}">-</c:if>
                    <c:if test="${line.bankCawangan ne null}">${line.bankCawangan}</c:if>
               </display:column>
               <display:column title="Nombor Rujukan">
                    <c:if test="${line.noRujukan eq null}">-</c:if>
                    <c:if test="${line.noRujukan ne null}">${line.noRujukan}</c:if>
               </display:column>
               <display:column property="amaun" title="Amaun (RM)" style="text-align:right" format="{0,number, 0.00}"/>
               <display:column title="Cetak Pengesahan Cek/WP/KW" style="text-align:center">
                    <c:choose >
                         <c:when test="${line.kodCaraBayaran.kod eq 'T'
                                         or line.kodCaraBayaran.kod eq 'KK'
                                         or line.kodCaraBayaran.kod eq 'VS'
                                         or line.kodCaraBayaran.kod eq 'DK'}">
                              <s:button name=" " disabled="true" value="Cetak" class="btn"/>
                         </c:when>
                         <c:otherwise>
                              <stripes:button name=" " value="Cetak" class="btn" onclick="doPrintBelakangResit('${line.idCaraBayaran}');"/>
                         </c:otherwise>
                    </c:choose>
               </display:column>
          </display:table>
     </stripes:form>
     <br>
     <c:choose>
          <c:when test="${!empty SSHMA}">
               <display:table  name="${actionBean.senaraiKandunganFolder}" id="row" class="tablecloth" >
                    <display:column title="Bil" >${row_rowNum}</display:column>
                    <display:column title="Kod Dokumen">
                         ${row.dokumen.kodDokumen.kod}
                    </display:column>
                    <display:column title="Nama Dokumen">
                         ${row.dokumen.tajuk}
                    </display:column>
                    <display:column title="Papar" >
                         <button name="cetakPP${row_rowNum}"
                                 onclick="doViewReport('${row.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                 onmouseover="this.style.cursor = 'pointer';" title="Papar ${row.dokumen.kodDokumen.nama}"
                                 class="btn">Papar</button>&nbsp; 
                    </display:column>

                    <%--              <c:if test="${row.dokumen.namaFizikal != null}">
                                  <display:column title="Cetakan Carian">
                                          <button name="cetakPP${row_rowNum}"
                                                  onclick="doPrintViaApplet('${row.dokumen}');"
                                                  class="btn">Cetak</button>&nbsp; 
                                       <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                                   onclick="doPrintViaApplet('${row.dokumen.idDokumen}');" height="30" width="30" alt="cetak"
                                                   onmouseover="this.style.cursor='pointer';" title="Cetak Dokumen ${row.dokumen.kodDokumen.nama}"/>
                                  </display:column>
                                  </c:if>  --%>
               </display:table>
          </c:when>
          <c:otherwise>
               <display:table  name="${actionBean.senaraiPermohonan}" id="row" class="tablecloth" >
                    <display:column title="Bil" >${row_rowNum}</display:column>
                    <display:column title="Urusan">
                         ${row.kodUrusan} - ${row.namaUrusan}
                    </display:column>
                    <display:column title="ID Permohonan/Perserahan" property="idPermohonan" />
                    <c:if test="${empty tanpaBayaran}">
                         <display:column title="Akuan Penerimaan">
                              <c:set var="printMultiple" value="${printMultiple},${actionBean.senaraiPermohonan[(row_rowNum - 1)].akuanPenerimaan.idDokumen}"/>
                              <%--<button name="cetakAP${row_rowNum}" value="Cetak"
                                      onclick="doPrintViaApplet('${actionBean.senaraiPermohonan[(row_rowNum - 1)].akuanPenerimaan.idDokumen}');"
                                      class="btn">Cetak</button> --%>
                              <button name="cetakPP${row_rowNum}"
                                      onclick="doViewReport('${actionBean.senaraiPermohonan[(row_rowNum - 1)].akuanPenerimaan.idDokumen}');" height="30" width="30" alt="papar"
                                      onmouseover="this.style.cursor = 'pointer';" title="Papar ${row.namaUrusan}"
                                      class="btn">Papar/Cetak</button>

                         </display:column>
                    </c:if>
                    <c:if test="${fn:length(row.senaraiDokumen) > 0 && row.kodUrusan ne 'CRCK'}">
                         <%--<display:column title="Dokumen Carian">
                              <c:set var="itemToPrint" value=""/>
                              <c:forEach items="${row.senaraiDokumen}" var="items" varStatus="count">
                                   <c:set var="itemToPrint" value="${itemToPrint},${items}"/>
                                   <c:set var="printMultiple" value="${printMultiple},${items}"/>                            
                              </c:forEach>
                              <c:if test="${itemToPrint ne ''}">
                                   <button name="cetakPP${items_rowNum}"
                                           onclick="doPrintViaApplet('${itemToPrint}');"
                                           class="btn">Cetak</button>&nbsp;
                              </c:if>
                         </display:column>--%>
                         <c:if test="${row.kodUrusan ne 'SSHM'}">
                         <display:column title="Dokumen Carian">
                              <c:forEach items="${row.senaraiDokumen}" var="items" varStatus="count">
                                   <span style="height: 20px;"/>
                                   <button name="cetakPP${row_rowNum}"
                                           onclick="doViewReport('${items}');" height="30" width="30" alt="papar"
                                           onmouseover="this.style.cursor = 'pointer';" 
                                           title="Papar"
                                           class="btn">Papar/Cetak</button>
                                   <c:if test="${count.count%4==0}">
                                        <br/>
                                   </c:if>
                              </c:forEach>
                         </display:column>
                         </c:if>               
                    </c:if>
                    <c:if test="${empty tanpaBayaran}">
                         <c:if test="${actionBean.balance > 0}">
                              <display:footer>
                              <tr>
                                   <td colspan="4" align="right">Baki Yang Perlu Dipulangkan (RM):</td>
                                   <td align="right"><fmt:formatNumber value="${actionBean.balance}" pattern="#,###,###.00"/></td>
                                   <%-- <input name="balance" type="currency" id="jumCaraBayar" size="12"
                                           class="number" readonly="readonly" style="background:transparent;border:0 px;" /></td>--%>
                              <tr>
                              </display:footer>
                         </c:if>
                    </c:if>
               </display:table>
          </c:otherwise>
     </c:choose>
<br/>
<c:if test="${!empty actionBean.senaraiDokumen}">
     <display:table name="${actionBean.senaraiDokumen}" class="tablecloth" id="row2">
          <display:column title="Kod Dokumen" property="kodDokumen.kod" />
          <display:column title="Nama Dokumen" property="kodDokumen.tajuk" />
          <display:column title="Klasifikasi" property="klasifikasi.nama" />
          <display:column title="Cetak">
               <p align="center">
                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                         onclick="doPrintViaApplet('${row2.idDokumen}');" height="30" width="30" alt="cetak"
                         onmouseover="this.style.cursor = 'pointer';" title="Cetak Dokumen ${row2.kodDokumen.nama}"/>
               </p>
          </display:column>
     </display:table>

</c:if>

<c:set var="action" value="/daftar/carian"/>
<c:if test="${!empty tanpaBayaran}">
     <c:set var="action" value="/daftar/carian_tanpa_bayaran"/>
</c:if> 
<stripes:form action="${action}">
     <stripes:hidden name="semula" value="Y"/>&nbsp;    
     <c:if test="${printMultiple ne '' && fn:length(actionBean.senaraiPermohonan) > 1}">
          <stripes:button name="cetakSemua" value="Cetak Semua" class="longbtn"
                          onclick="doPrintViaApplet('${printMultiple}');"/>
          &nbsp;
     </c:if>
     <stripes:submit name="Step1" value="Kembali ke Utama" class="longbtn"/>
</stripes:form>


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
<%--<applet code="PrintApplet.class" ARCHIVE="${pageContext.request.contextPath}/PrintApplet.jar,
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
    <param name ="kodNegeri" value="05">
    <param name ="method" value="pdfp">
    <param name ="disabledWatermark" value="true"/>
    <param name ="idPengguna" value="${idPengguna}"/>
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
</applet>--%>

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