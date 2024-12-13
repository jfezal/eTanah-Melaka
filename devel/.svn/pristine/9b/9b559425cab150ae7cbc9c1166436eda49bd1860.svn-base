<%--
    Document   : senarai_semak_KS
    Created on : Dec 2, 2011, 11:22:57 AM
    Author     : latifah.iskak
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    $(document).ready(function() {

        var bil =  ${fn:length(actionBean.senaraiKandungan)};
        var bil2 =  ${fn:length(actionBean.senaraiSemakDokumen)};
        for (var i = 0; i < bil; i++){
            var checkbox = $("#chkbox"+i).val();
            //alert("checkbox :"+checkbox);
            for (var j = 0; j < bil2; j++){
                var kodDok = $("#semakDokExist"+j).val();
                //alert("kodDok :"+kodDok);
                if(checkbox == kodDok){
                    //alert("sama");
                    document.getElementById("chkbox"+i).checked = true;
                }
            }
        }
    });

    function muatNaikForm(folderId, idPermohonan, dokumenKod, idDokumen) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/upload_action?popupUpload&folderId=' + folderId+ '&idPermohonan=' + idPermohonan 
            + '&dokumenKod=' + dokumenKod + '&idDokumen=' + idDokumen + '&idRujukan=' + 'mohonSemakDok';
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

    function refreshPage(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/semak_kertas_siasatan?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatSenaraiSemakKSActionBean" name="form1" id="form1">

    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Senarai Semak Kertas Siasatan</legend>
            <br><br>           
            <div id="semakDok" style="visibility: hidden; display: none">
                <c:set value="0" var="i"/>
                <c:forEach items="${actionBean.senaraiSemakDokumen}" var="senarai">
                    <input name="semakDokExist${i}" id="semakDokExist${i}" value="${senarai.dokumen.idDokumen}">
                    <c:set var="i" value="${i+1}" />
                </c:forEach>
            </div>
            <c:if test="${edit}">
                <display:table name="${actionBean.senaraiKandungan}" class="tablecloth" id="row" style="width:100%">

                    <display:column title="Pilih">
                        <center>
                            <input type="checkbox" name="checkbox${row_rowNum-1}" id="chkbox${row_rowNum-1}" value="${row.dokumen.idDokumen}">
                        </center>
                        <input type="hidden" name="val" id="val_${row_rowNum-1}" value="${row.dokumen.idDokumen}">
                    </display:column>
                    <display:column title="Kod Dokumen" property="dokumen.kodDokumen.kod"  />
                    <display:column title="Nama Dokumen" property="dokumen.kodDokumen.nama" />
                    <display:column title="Catatan" property="catatan" />
                    <display:column title="Dimasuk Oleh" property="dokumen.infoAudit.dimasukOleh.nama" />
                    <display:column title="Tarikh" property="dokumen.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                    <display:column title="Tindakan">
                        <p align="center">
                            <c:if test="${actionBean.pengguna.idPengguna eq row.dokumen.infoAudit.dimasukOleh.idPengguna}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                     onclick="muatNaikForm('${row.folder.folderId}','${actionBean.permohonan.idPermohonan}'
                                         ,'${row.dokumen.kodDokumen.kod}','${row.dokumen.idDokumen}');return false;" height="30" width="30" alt="Muat Naik"
                                     onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen ${row.dokumen.kodDokumen.nama}"/>
                                /</c:if>
                            <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png"
                                 onclick="scan('${row.dokumen.idDokumen}','${actionBean.permohonan.idPermohonan}');return false;" height="30" width="30" alt="Imbas"
                                 onmouseover="this.style.cursor='pointer';" title="Imbas untuk Dokumen ${row.dokumen.kodDokumen.nama}"/>
                            <%-- </c:if>--%>
                            <c:if test="${row.dokumen.namaFizikal != null}">
                                /<img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
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
                    <%--<s:button name="deleteSelected" value="Hapus" class="btn" onclick="doSubmit(this.form,this.name,'page_div');" />
                    <s:button name="addDocForm" value="Tambah" class="btn" onclick="addNew('${actionBean.permohonan.idPermohonan}');"/>--%>
                    <s:button name="simpanSemakDokumen" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>

            </c:if>

            <c:if test="${view}">
                <display:table name="${actionBean.senaraiSemakDokumen}" class="tablecloth" id="row" style="width:100%">
                    <display:column title="Kod Dokumen" property="dokumen.kodDokumen.kod"  />
                    <display:column title="Nama Dokumen" property="dokumen.kodDokumen.nama" />
                    <display:column title="Catatan" property="catatan" />
                    <display:column title="Dimasuk Oleh" property="dokumen.infoAudit.dimasukOleh.nama" />
                    <display:column title="Tarikh" property="dokumen.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                    <display:column title="Tindakan">
                        <p align="center">
                            <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png"
                                 onclick="scan('${row.dokumen.idDokumen}','${actionBean.permohonan.idPermohonan}');return false;" height="30" width="30" alt="Imbas"
                                 onmouseover="this.style.cursor='pointer';" title="Imbas untuk Dokumen ${row.dokumen.kodDokumen.nama}"/>
                            <c:if test="${row.dokumen.namaFizikal != null}">
                                /<img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
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
            </c:if>
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