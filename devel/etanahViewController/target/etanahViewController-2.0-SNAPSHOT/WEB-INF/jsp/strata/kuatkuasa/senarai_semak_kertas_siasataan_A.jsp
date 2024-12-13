
<%--
    Document   : senarai_semak_kertas_siasataan_A
    Created on : Feb 8, 2011, 11:22:57 AM
    Author     : Murali
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">    

    function muatNaikForm1(folderId, dokumenId, idPermohonan) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/strata/SemakKertasSiasatanFoliA?muatNaikForm1&folderId=' + folderId + '&dokumenId='
            + dokumenId + '&idPermohonan=' + idPermohonan;
    <%--alert(url);--%>
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
        }

        function scan(dokumenId, idPermohonan) {
            var left = (screen.width/2)-(1000/2);
            var top = (screen.height/2)-(150/2);
            var url = '${pageContext.request.contextPath}/dokumen/scan/' + dokumenId + '?idPermohonan=' + idPermohonan;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=850,height=600");
        }

        function doPrintViaApplet (docId) {
            var a = document.getElementById('applet');
            a.doPrint(docId.toString());
        }

        function doViewReport(v) {
            var url = '${pageContext.request.contextPath}/dokumen/view/' + v ;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
        }

        //adding dokumen
   
        function addNew(v){
            window.open("${pageContext.request.contextPath}/strata/SemakKertasSiasatanFoliA?addDocForm&permohonan.idPermohonan=" + v, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=1500,height=800");
        }
  
        function kemaskiniCatatan(folderId, dokumenId){        
            var url = '${pageContext.request.contextPath}/strata/SemakKertasSiasatanFoliA?showCatatanEdit&folderId=' + folderId + '&dokumenId='
                + dokumenId;
            window.open(url, "etanah", "status=0,toolbar=0,location=0,menubar=0,width=900,height=350");
        }

</script>
<s:form beanclass="etanah.view.strata.SenaraiKertasSiasatanFoliAActionBean" name="form1" id="form1">

    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">            
            <legend>Folio A</legend>
            <br>
            <p><font size="2" color="Red">Arahan: Sila klik butang Tambah untuk menambah dokumen</font></p>
            <br>           
            <s:hidden name="folder.folderId"/>
            <s:hidden name="permohonan.idPermohonan"/>
            <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
            <display:table name="${actionBean.senaraiKandungan1}" class="tablecloth" id="row" style="width:100%">

                <display:column title="Pilih">
                    <c:if test="${actionBean.pengguna.idPengguna eq row.dokumen.infoAudit.dimasukOleh.idPengguna}">
                        <center><s:checkbox name="chkbox" id="chkbox" value="${row.dokumen.idDokumen}"/></center>
                    </c:if>
                    <s:hidden name="val" id="val_${row_rowNum-1}" value="${row.dokumen.idDokumen}"/>
                </display:column>
                <%--<display:column title="Kod Dokumen" property="dokumen.kodDokumen.kod"  />--%>
                <display:column title="No. Folio"> Folio A${row_rowNum} </display:column>
                <display:column title="Nama Folio" property="dokumen.kodDokumen.nama" />
                <%--<display:column title="Klasifikasi" property="dokumen.klasifikasi.nama" />--%>
                <%--<display:column title="Catatan" property="catatan" />--%>
                <display:column title="Catatan">                    
                    <p>${row.catatan}</p>                    
                    <p align="right"><img alt='Klik Untuk Kemaskini Catatan' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                          id="" title="Klik Untuk Kemaskini Catatan" onclick="kemaskiniCatatan('${row.folder.folderId}','${row.dokumen.idDokumen}');return false;" onmouseover="this.style.cursor='pointer';"/> </p>                       
                    </display:column>
                    <%--<display:column title="Dimasuk Oleh" property="dokumen.infoAudit.dimasukOleh.nama" />--%>
                    <display:column title="Tarikh" property="dokumen.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                    <display:column title="Tindakan">
                    <p align="center">
                        <%--  <c:if test="${row.dokumen.namaFizikal == null}">--%>
                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                             onclick="muatNaikForm1('${row.folder.folderId}','${row.dokumen.idDokumen}'
                                 ,'${actionBean.permohonan.idPermohonan}');return false;" height="30" width="30" alt="Muat Naik"
                             onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen ${row.dokumen.kodDokumen.nama}"/>
                        /
                        <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png"
                             onclick="scan('${row.dokumen.idDokumen}','${actionBean.permohonan.idPermohonan}');return false;" height="30" width="30" alt="Imbas"
                             onmouseover="this.style.cursor='pointer';" title="Imbas untuk Dokumen ${row.dokumen.kodDokumen.nama}"/>/
                        <%-- </c:if>--%>
                        <c:if test="${row.dokumen.namaFizikal != null}">
                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                 onclick="doViewReport('${row.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                 onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${row.dokumen.kodDokumen.nama}"/>

                            <c:if test="${row.dokumen.baru eq 'Y' || row.dokumen.baru eq ''}">
                                <img src="${pageContext.request.contextPath}/pub/images/baharu.gif" alt="baru"/>
                            </c:if>
                        </c:if>
                    </p>
                </display:column>
                <display:column title="Cetak">
                    <c:if test="${row.dokumen.namaFizikal != null}">
                        <p align="center">
                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                 onclick="doPrintViaApplet('${row.dokumen.idDokumen}');" height="30" width="30" alt="cetak"
                                 onmouseover="this.style.cursor='pointer';" title="Cetak Dokumen ${row.dokumen.kodDokumen.nama}"/>
                        </p>
                    </c:if>
                </display:column>
            </display:table>
            <p><label>&nbsp;</label>
                <s:button name="deleteSelected" value="Hapus" class="btn" onclick="doSubmit(this.form,this.name,'page_div');" />
                <s:button name="addDocForm" value="Tambah" class="btn" onclick="addNew('${actionBean.permohonan.idPermohonan}');"/>
            </p>
            <br/>
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
                <param name="uploadAction" value="<%=request.getContextPath()%>/PrintServlet"/>
                <param name ="method" value="pdfp"/>
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
            </applet>
        </fieldset>
    </div>
</s:form>