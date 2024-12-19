<%-- 
    Document   : bypass_keutamaan
    Created on : Apr 6, 2010, 9:39:45 AM
    Author     : fikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript">
  
    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }

    function doSaveCapaian(v,b){
        var idHakmilik = b;
        var url = '${pageContext.request.contextPath}/strata/Geranstrata?cetakSemula&id_dokumen=' + v + '&idHakmilik=' + idHakmilik;
        $.ajax({
            type: "GET",
            url: url,
            success: function(data) {
                doPrintViaApplet(v);
            }
        });

    }
  
    function doPrintViaApplet(docId) {
        var a = document.getElementById('applet');
        a.doPrint(docId.toString());
    }

    function selectAll(a) {
        var len = $('.cetakSemua').length;
        for (var i = 0; i < len; i++) {
            var c = document.getElementById("cetaksemua" + i);
            c.checked = a.checked;
        }
    }

    function printSelectedDocuments() {
        var documentsList = '';
        var len = $('.cetakSemua').length;
        for (var i = 0; i < len; i++) {
            var c = document.getElementById("cetaksemua" + i);
            if (c.checked) {
                documentsList = documentsList + ',' + c.value;
            }
        }
        if (documentsList == '') {
            alert('Sila Pilih Dokumen untuk dicetak.');
            return;
        } else {
            doPrintViaApplet(documentsList);
        }
    }
</script>            
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:messages/>
<s:form action="/senaraiTugasan">
    <div class="content">
        <fieldset class="aras1">
            <legend>Cetak Dokumen </legend> 
            <p align="center"> <label>&nbsp;</label>
                <c:set var="rowNum" value="0"/>
                <display:table name="${actionBean.senaraiKandunganFolder2}" class="tablecloth" id="row2" style="width:100%">
                    <display:column title="Bil">${row2_rowNum}</display:column>
                    <display:column title="Kod Dokumen" property="dokumen.kodDokumen.kod" />
                    <display:column title="Nama Dokumen">
                        <c:if test="${row2.dokumen.kodDokumen.kod eq '0L'}">
                        <div id="${row2_rowNum-1}">${row2.catatan}</div>
                        <s:hidden name="x2" id="old2_${row2_rowNum-1}" value="${row2.catatan}"/>
                    </c:if>
                    <c:if test="${row2.dokumen.kodDokumen.kod ne '0L'}">
                        <div id="${row2_rowNum-1}">${row2.dokumen.tajuk}</div>
                        <s:hidden name="x2" id="old2_${row2_rowNum-1}" value="${row2.dokumen.tajuk}"/>
                    </c:if>
                    <c:if test="${row2.dokumen.kodDokumen.kod eq 'SB' 
                                  || row2.dokumen.kodDokumen.kod eq 'SA'
                                  || row2.dokumen.kodDokumen.kod eq 'SW' }">
                          <br/>
                          <c:if test="${row2.dokumen.noRujukan ne '' && row2.dokumen.noRujukan ne null}">
                              (No rujukan : <a href="#"
                                               onclick="window.open('${pageContext.request.contextPath}/daftar/pertanyaanSBSWSA?paparOnly&idMohon=${row2.dokumen.noRujukan}',
                                                   'popup', 'width=1000,height=600,scrollbars=yes,resizable=yes,toolbar=no,directories=no,location=no,menubar=no,status=no,left=0,top=0');">
                                  ${row2.dokumen.noRujukan})</a>
                              </c:if>
                          </c:if>
                    </display:column>        
                    <display:column title="Papar">
                    <p align="center">
                        <c:if test="${row2.dokumen.namaFizikal != null}">
                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                 onclick="doViewReport('${row2.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                 onmouseover="this.style.cursor = 'pointer';" title="Papar ${row2.dokumen.kodDokumen.nama}"/>                                  
                        </c:if>
                    </p>
                </display:column>
                <display:column title="<input type='checkbox' id='semua' name='semua' onclick='javascript:selectAll(this);' /> Cetak">
                    <p align="center">
                        <input type="checkbox" name="cetaksemua" id="cetaksemua${rowNum}" value="${row2.dokumen.idDokumen}" class="cetakSemua"/>
                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                             onclick="doSaveCapaian('${row2.dokumen.idDokumen}','${row2.dokumen.hakmilik}');" height="30" width="30" alt="cetak"
                             onmouseover="this.style.cursor = 'pointer';" title="Cetak Dokumen ${row2.dokumen.kodDokumen.nama}"/>
                    </p>
                    <c:set var="rowNum" value="${rowNum +1}"/>
                </display:column>  
            </display:table>

            <label>&nbsp;</label>&nbsp;

            <p>
                <label>&nbsp;</label>
                <c:if test="${fn:length(actionBean.senaraiKandunganFolder2) > 0}">           
                    <s:button name="cetakSemua" value="Cetak" class="btn" onclick="printSelectedDocuments();"/>
                </c:if>
                <s:submit name="" value="Senarai Tugasan" class="btn"/>
            </p>
        </fieldset>
    </div>


    <applet code="PrintApplet.class" ARCHIVE="${pageContext.request.contextPath}/PrintApplet.jar,
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
            <param name ="uploadAction" value="<%=request.getContextPath()%>/PrintServlet"/>
            <param name ="disabledWatermark" value="true"/>
            <param name ="withoutSignature" value="true"/>
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

</s:form>

