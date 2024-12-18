<%--
    Document   : senarai_semak_kertas_siasatan
    Created on : Feb 8, 2011, 11:22:57 AM
    Author     : Murali
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

//    $(document).ready(function() {
//        var url = '${pageContext.request.contextPath}/strata/SemakKertasSiasatan?reload';
//        $.ajax({
//            type:"GET",
//            url : url,
//            success : function(data) {
//                $('#page_div').html(data);
//            }
//        });
//        document.getElementById("pilihAll").checked = false;
//        var table = document.getElementById("tbl");
//           var rowCount = table.rows.length;
//        for(var i=0; i<rowCount-1; i++) {
//            if($("#status"+i).val() == ""){
//                document.getElementById("pilih"+i).checked = false;
//                document.getElementById("status"+i).value = "T";
//            }else if($("#status"+i).val() != ""){
//                if($("#status"+i).val() == "Y"){
//                    document.getElementById("pilih"+i).checked = true;
//                }else if($("#status"+i).val() == "T"){
//                    document.getElementById("pilih"+i).checked = false;
//                }
//
//            }
//        }
//       
//    });

    function  changeValue(i){
        var obj = document.getElementById("pilih"+i);
        if(obj.checked == true){
            document.getElementById("status"+i).value = "Y";
        }else{
            document.getElementById("status"+i).value = "T";
        }
    }
    function selectall() {
        try {
                 var table = document.getElementById("tbl");
                 var rowCount = table.rows.length;
            var chkbox1 = table.rows[0].cells[0].childNodes[0];
            if(chkbox1!=null && chkbox1.checked == true){ 
                      for(var i=0; i<rowCount-1; i++) {
                    document.getElementById("pilih"+i).checked = true;
                    document.getElementById("status"+i).value = "Y";
                }
            }else{
                for(var i=0; i<rowCount-1; i++) {
                    document.getElementById("pilih"+i).checked = false;
                    document.getElementById("status"+i).value = "T";
                }
            }

        }catch(e){
            //            alert(e);
        }
    }

    function muatNaikForm(folderId, kodDokumen, idPermohonan) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        //        alert('upload form from this action bean');
        var url = '${pageContext.request.contextPath}/strata/SemakKertasSiasatan?muatNaikForm&folderId=' + folderId + '&kodDokumen='
            + kodDokumen + '&idPermohonan=' + idPermohonan;
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
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }

    //adding dokumen

    function addDok(v){
        window.open("${pageContext.request.contextPath}/strata/SemakKertasSiasatan?tambahDokumen&permohonan.idPermohonan=" + v, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=800,height=600");
    }

</script>
<s:form name="form1" beanclass="etanah.view.strata.SenaraiKertasSiasatanActionBean">
    <s:hidden name="folderDokumen.folderId"/>
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Senarai Semak Kertas Siasatan</legend>
            <div class="content" align="left">
                <table id="tbl" class="tablecloth">
                    <tr>
<!--                        <th align="center"><s:checkbox name="pilihAll" id="pilihAll" onclick="selectall()" />&nbsp;<b>Pilih</b></th>-->
                        <th align="center"><b>Bil</b></th>
                        <th align="center" width="40%"><b>Perkara</b></th>
                        <th align="center"><b>Tindakan</b></th>
                        <th align="center"><b>Catatan</b></th>
                        <th align="center"><b>Hapus Dokumen</b></th>
                    </tr>
                    <c:set value="0" var="i"/>
                    <c:forEach items="${actionBean.senaraiSemakDokumen}" var="line">
                        <tr style="font-weight:bold">
<!--                            <td><center><s:checkbox name="pilih${i}" id="pilih${i}" value="${line.adaDokumen}" onclick="changeValue(${i})" />
                                    <s:hidden name="status${i}" id="status${i}" value="${line.adaDokumen}"/>
                                </center></td>-->
                            <td><center><c:out value="${i+1}"/></center></td>
                            <td>${line.kodDokumen.nama}</td>
                            <td><table border="0"><tr><td>
                                            <center>
                                                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                                     onclick="muatNaikForm('${actionBean.kandunganFolder.folder.folderId}','${line.kodDokumen.kod}'
                                                         ,'${actionBean.permohonan.idPermohonan}');return false;" height="30" width="30" alt="Muat Naik"
                                                     onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen ${line.kodDokumen.nama}"/> <b>|</b>
                                                <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png"
                                                     onclick="scan('${actionBean.kandunganFolder.dokumen.idDokumen}','${actionBean.permohonan.idPermohonan}');return false;" height="30" width="30" alt="Imbas"
                                                     onmouseover="this.style.cursor='pointer';" title="Imbas untuk Dokumen "/> <b>|</b>
                                                <%--<c:if test="${row.dokumen.namaFizikal != null}">
                                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                         onclick="doViewReport('${row.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                         onmouseover="this.style.cursor='pointer';" title="Papar Dokumen "/>  <b>|</b>
                                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                                         onclick="doPrintViaApplet('${row.dokumen.idDokumen}');" height="30" width="30" alt="cetak"
                                                         onmouseover="this.style.cursor='pointer';" title="Cetak Dokumen "/>
                                                </c:if>--%>
                                                <c:forEach items="${actionBean.senaraiKandungan}" var="line2">
                                                    <c:if test="${line.kodDokumen.kod eq line2.dokumen.kodDokumen.kod}">
                                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                                             onclick="doPrintViaApplet('${actionBean.kandunganFolder.dokumen.idDokumen}');" height="30" width="30" alt="cetak"
                                                             onmouseover="this.style.cursor='pointer';" title="Cetak Dokumen"/>
                                                    </c:if>
                                                </c:forEach>
                                                <b>|</b>
                                                <c:if test="${actionBean.kandunganFolder.dokumen.namaFizikal != null}">
                                                    <c:forEach items="${actionBean.senaraiKandungan}" var="line2">
                                                        <c:if test="${line.kodDokumen.kod eq line2.dokumen.kodDokumen.kod}">
                                                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                 onclick="doViewReport('${line2.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                                 onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:if>
                                            </center>

                                        </td></tr></table></td>
                            <td><center><s:textarea name="catatan${i}" id="catatan${i}" value="${line.catatan}" rows="4" cols="30" /></center></td>
                            <td><center><s:checkbox name="select${i}" id="select" value="${line.idPermohonanSemakDokumen}" /></center></td>
                        </tr>
                        <c:set var="i" value="${i+1}" />
                    </c:forEach>
                </table>
            </div>
            <br>
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

            <p align="center">
                <s:button name="simpan" id="save" value="Simpan Dokumen" class="longbtn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                <s:button name="tambahDokumen" id="save" value="Tambah Dokumen" class="longbtn" onclick="addDok('${actionBean.permohonan.idPermohonan}');"/>
                <s:button name="hapusDokumen" id="save" value="Hapus Dokumen" class="longbtn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
        </fieldset>
    </div>
</s:form>