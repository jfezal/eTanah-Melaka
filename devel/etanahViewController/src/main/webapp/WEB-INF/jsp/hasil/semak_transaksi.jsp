<%-- 
    Document   : semak_transaksi
    Created on : Apr 14, 2010, 5:06:52 PM
    Author     : abdul.hakim
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<div align="center">
     <table style="width:99.2%" bgcolor="green">
            <tr><td width="100%" height="20" ><div style="background-color: green;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Semakan Transaksi Akaun</font>
            </div></td></tr>
        </table>
</div>
<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<stripes:form beanclass="etanah.view.stripes.hasil.SemakanTransaksiActionBean" id="semak">
<stripes:errors />

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian</legend>
                <p>
                    <label>ID Hakmilik : </label>
                    <stripes:text name="hakmilik.idHakmilik" size="30" id="hakmilik" onblur="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>Nombor Resit : </label>
                    <stripes:text name="dokumenKewangan.idDokumenKewangan" size="30" id="resit" onblur="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>Nombor Resit Kew. 38 : </label>
                    <stripes:text name="kew38" size="30" id="kew" onblur="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <stripes:submit name="search" value="Cari" class="btn" onclick="return chck()" id="nxt"/>
                    <stripes:button name=" " value="Isi Semula" class="btn" onclick="clearText('semak');"/>
                </p>
            <br>
        </fieldset>
    </div>
    <p></p>
    <c:if test="${actionBean.flag}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Senarai Carian</legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiTransaksi}" id="row">
                        <display:column title="Bil."><div align="center">${row_rowNum}.</div></display:column>
                        <display:column  title="Nombor Resit" >
                            <c:choose>
                                <c:when test="${row.dokumenKewangan.noRujukan ne null}">
                                    <a href="#" onclick="edit('${row.dokumenKewangan.idDokumenKewangan}');return false;">${row.dokumenKewangan.noRujukan}</a>
                                </c:when>
                                <c:when test="${row.dokumenKewangan.noRujukanManual ne null}">
                                    <a href="#" onclick="edit('${row.dokumenKewangan.idDokumenKewangan}');return false;">${row.dokumenKewangan.noRujukanManual}</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="#" onclick="edit('${row.dokumenKewangan.idDokumenKewangan}');return false;">${row.dokumenKewangan.idDokumenKewangan}</a>
                                </c:otherwise>
                            </c:choose>
                        </display:column>
                        <display:column title="Tarikh Bayar" property="dokumenKewangan.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss aa}"/>
                        <display:column title="Status Pembayaran">
                            <c:if test="${row.status.kod eq 'T' or row.status.kod eq 'A'}">Jelas</c:if>
                            <c:if test="${row.status.kod eq 'B'}">Belum Jelas</c:if>
                            <%--${row.status.kod == 'T' ? 'Sah' : 'Tidak Sah'}--%>
                        </display:column>
                        <display:column title="Daerah Bayaran" property="dokumenKewangan.cawangan.daerah.nama"/>
                        <c:if test="${row.dokumenKewangan.noRujukan ne null}">
                            <display:column title="Jenis Bayaran">e-Payment</display:column>
                        </c:if>
                        <display:column title="Jumlah (RM)" property="dokumenKewangan.amaunBayaran" format="{0,number, 0.00}" style="text-align:right"/>
                        <display:column title="Cetak Resit">
                            <c:if test="${row.status.kod eq 'T' or row.status.kod eq 'A'}">
                                <stripes:button name="" value="Cetak" class="btn" onclick="cetak(this.form, '${row.dokumenKewangan.idDokumenKewangan}','${row.kodTransaksi.kod}')" id="ctkResit"/>
                            </c:if>
                            <c:if test="${row.status.kod eq 'B'}">
                                <stripes:button name="" value="Cetak" disabled="true" class="btn" id="ctkResit"/>
                            </c:if>
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
            <display:table class="tablecloth" name="${actionBean.dkbList}" id="line">
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
                     <display:column property="dokumenKewangan.infoAudit.tarikhMasuk" title="Tarikh Bayaran" format="{0,date,dd/MM/yyyy hh:mm:ss a}"/>
                <display:column property="caraBayaran.amaun" title="Amaun (RM)" style="text-align:right" format="{0,number, 0.00}"/>
                <display:column title="Cetak Pengesahan Cek/WP/KW" style="text-align:center">
                    <c:choose >
                        <c:when test="${line.caraBayaran.kodCaraBayaran.kod eq 'T'
                                                or line.caraBayaran.kodCaraBayaran.kod eq 'KK'
                                                or line.caraBayaran.kodCaraBayaran.kod eq 'VS'}">
                            <stripes:button  name=" " disabled="true" value="Cetak" class="btn"/>
                        </c:when>
                        <c:otherwise><stripes:button  name=" " value="Cetak" class="btn" onclick="doPrintBelakangResit('${line.caraBayaran.idCaraBayaran}');"/></c:otherwise>
                    </c:choose>
                </display:column>
            </display:table>
        </div>
    </fieldset>
</div>
</c:if>        
    <table style="width:99.2%" bgcolor="green">
        <tr>
            <td align="right">&nbsp;</td>
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
</stripes:form>

<script type="text/javascript">
    function chck(){
        var resit = document.getElementById('resit');
        var id = document.getElementById('hakmilik');
        var kew38 = document.getElementById('kew');
        if ((resit.value == "")&&(id.value == "")&&(kew38.value == "")){
            alert("Sila Masukkan ID Hakmilik atau Nombor Resit untuk membuat carian.");
            return false;
        }
    }

    function edit(id){
        window.open("${pageContext.request.contextPath}/hasil/semak_transaksi?popup&resit="+id, "eTanah",
                "status=0,scrollbars=1,resizable=1,toolbar=0,location=0,menubar=0,width=1024,height=600,top=20,left=20");
    }

    function cetak(f, resit, kodTrans){
        var form = $(f).formSerialize();
        var report = '';
        var kodNegeri = '${actionBean.negeri}';
        var validate = true;
        if(kodNegeri == '05'){
            if(kodTrans == '73199'){
                <%--Carian--%>
                report = 'HSLResitPelbagai_NS_Carian.rdf';
            }
            else if(kodTrans == '73151'){
                <%--Pelan--%>
                report = 'HSLResitJualanPelan.rdf';
            }
            else if(kodTrans == '72199'){
                <%--Urusan Tanah Pelbagai--%>
                report = 'HSLResitUrusanTanah.rdf';
            }
            else if(kodTrans == '72499' ||
                       kodTrans == '61999' ||
                       kodTrans == '65096' ||
                       kodTrans == '71711' ||
                       kodTrans == '71799' ||
                       kodTrans == '71784' ||
                       kodTrans == '71803' ||
                       kodTrans == '72199' ||
                       kodTrans == '72499' ||
                       kodTrans == '73151' ||
                       kodTrans == '73199' ||
                       kodTrans == '73601' ||
                       kodTrans == '73605' ||
                       kodTrans == '74999' ||
                       kodTrans == '76152' ||
                       kodTrans == '76199' ||
                       kodTrans == '79501' ||
                       kodTrans == '99020' ||
                       kodTrans == '99021' ||
                       kodTrans == '99022' ||
                       kodTrans == '99023' ||
                       kodTrans == '99024' ||
                       kodTrans == '99025' ||
                       kodTrans == '99026' ||
                       kodTrans == '99027'){
                <%--Resit Bayaran Pelbagai--%>
                report = 'HSLResitPelbagai.rdf';
            }
            else if(kodTrans == '61401' || kodTrans == '61402' || kodTrans == '76152' || kodTrans == '99011'){
                <%--Cukai Tanah--%>
                report = 'SPOCCetakanSemulaResit.rdf';
            }
            else{
                alert("Kod transaksi tidak dijumpai.");
                validate = false;
            }
        }else if(kodNegeri == '04'){
            if(kodTrans == '73199'){
                <%--Carian--%>
                report = 'HSLResitPelbagai_MLK_Carian.rdf';
            }
            else if(kodTrans == '73151'){
                <%--Pelan--%>
                report = 'HSLResitJualanPelan_MLK.rdf';
            }
            else if(kodTrans == '72199'){
                <%--Urusan Tanah Pelbagai--%>
                report = 'HSLResitUrusanTanah_MLK.rdf';
            }
            else if(kodTrans == '72499' || kodTrans == '12206' || kodTrans == '12298'){
                <%--Resit Bayaran Pelbagai--%>
                report = 'HSLResitPelbagai_MLK.rdf';
            }
            else if(kodTrans == '61401' || kodTrans == '61402' || kodTrans == '76152' || kodTrans == '99011'){
                <%--Cukai Tanah--%>
                report = 'SPOCCetakanSemulaResit_MLK.rdf';
            }
            else{
                alert("Kod transaksi tidak dijumpai.");
                validate = false;
            }
        }
        <%--var param = document.getElementById('id_hakmilik');--%>
    if(validate){
        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+form+"&reportName="+report+"&report_p_id_kew_dok="+resit, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
        }
    }
    function doPrintBelakangResit(caraByrId){
        var a = document.getElementById('applet2');
        a.printChequeInfo(caraByrId.toString());
    }
</script>