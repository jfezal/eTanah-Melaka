<%-- 
    Document   : maklumat_agensi
    Created on : Oct 12, 2012, 4:33:14 PM
    Author     : sitifariza.hanim
--%>


<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

    function tambahBaru(){
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/6)-(150/6);
        window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_agensi_baru?popupAgensi", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=500,left=" + left + ",top=" + top);
    }

    function removeSingle(idRujukan)
    {
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_agensi_baru?deleteSingle&idRujukan='
                +idRujukan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
    <%--refreshPage();--%>
                            },'html');}
    <%--self.opener.refreshPage();--%>
            }

            function muatNaikForm1(folderId, idPermohonan, dokumenKod, idRujukan) {
                //alert("idRujukan : "+idRujukan);
                var left = (screen.width/2)-(1000/2);
                var top = (screen.height/2)-(150/2);
                var url = '${pageContext.request.contextPath}/penguatkuasaan/upload_action?popupUpload&folderId=' + folderId
                    + '&idPermohonan=' + idPermohonan + '&dokumenKod=' + dokumenKod + '&idRujukan=' + idRujukan;
                window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
            }

            function doViewReport(v) {
                var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
                window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
            }

            function removeImej(idImej) {
                if(confirm('Adakah anda pasti untuk hapus?')) {
                    var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_agensi_baru?deleteSelected&idImej='+idImej;
                    $.get(url,
                    function(data){
                        $('#page_div').html(data);
                        refreshPage();
                    },'html');
                }
            }

            function refreshPage(){
                var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_agensi_baru?refreshpage';
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }

</script>
<s:form name="form" id="form" beanclass="etanah.view.penguatkuasaan.AgensiActionBean">
    <div class="subtitle">
        <s:messages/>
        <s:errors/>
        <fieldset class="aras1">
            <c:if test="${edit}">
                <legend>
                    Maklumat Agensi
                </legend>

                <%--<div id="senaraiBangunan" align="center">--%>
                <div  class="content" align="center">

                    <display:table class="tablecloth" name="${actionBean.senaraiMohonRuj}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column title="Nama Agensi" property="namaAgensi"></display:column>
                        <display:column title="Alamat Agensi" property="lokasiAgensi"></display:column>
                        <display:column title="Tarikh Remedi">
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhKuatkuasa}"/>
                        </display:column>
                        <display:column title="Muat Naik/Papar">
                            <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                 onclick="muatNaikForm1('${actionBean.permohonan.folderDokumen.folderId}',
                                     '${actionBean.permohonan.idPermohonan}','BR','${line.idRujukan}');return false;" height="30" width="30" alt="Muat Naik"
                                 onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen"/>
                            <p align="center">
                                <c:set value="1" var="count"/>
                                <c:forEach items="${actionBean.permohonan.folderDokumen.senaraiKandungan}" var="senarai">
                                    <c:if test="${senarai.dokumen.kodDokumen.kod eq 'BR' && senarai.dokumen.perihal eq line.idRujukan}">
                                        <c:if test="${senarai.dokumen.namaFizikal != null}">
                                            <br>
                                            -
                                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                 onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                 onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                            ${count}-${senarai.dokumen.tajuk}/
                                            <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                 onclick="removeImej('${senarai.dokumen.idDokumen}');" height="15" width="15" alt="Hapus"
                                                 onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen"/>

                                            <c:set value="${count+1}" var="count"/>
                                        </c:if>
                                    </c:if>

                                </c:forEach>
                            </p>
                        </display:column>
                        <display:column title="Hapus">
                            <c:if test="${line.idRujukan ne null}">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.senaraiMohonRuj[line_rowNum-1].idRujukan}');" />
                                </div>
                            </c:if>
                        </display:column>
                    </display:table>
                </div>
                <div class="content" align="center">
                    <table>
                        <tr>
                            <td>
                                <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBaru();"/>
                                <%--<s:submit name="" value="Hapus" class="btn"/>--%>
                            </td>
                        </tr>
                    </table>
                </div>
            </c:if>

            <c:if test="${view}">
                <legend>
                    Maklumat Agensi
                </legend>
                <div  class="content" align="center">

                    <display:table class="tablecloth" name="${actionBean.senaraiMohonRuj}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column title="Nama Agensi" property="namaAgensi"></display:column>
                        <display:column title="Alamat Agensi" property="lokasiAgensi"></display:column>
                        <display:column title="Tarikh Remedi">
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhKuatkuasa}"/>
                        </display:column>
                        <display:column title="Papar">
                           <%-- <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                 onclick="muatNaikForm1('${actionBean.permohonan.folderDokumen.folderId}',
                                     '${actionBean.permohonan.idPermohonan}','BR','${line.idRujukan}');return false;" height="30" width="30" alt="Muat Naik"
                                 onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen"/>--%>
                            <p align="center">
                                <c:set value="1" var="count"/>
                                <c:forEach items="${actionBean.permohonan.folderDokumen.senaraiKandungan}" var="senarai">
                                    <c:if test="${senarai.dokumen.kodDokumen.kod eq 'BR' && senarai.dokumen.perihal eq line.idRujukan}">
                                        <c:if test="${senarai.dokumen.namaFizikal != null}">
                                            <br>
                                            
                                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                 onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                 onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                            ${count}-${senarai.dokumen.tajuk}
                                          <%--  <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                 onclick="removeImej('${senarai.dokumen.idDokumen}');" height="15" width="15" alt="Hapus"
                                                 onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen"/>--%>

                                            <c:set value="${count+1}" var="count"/>
                                        </c:if>
                                    </c:if>

                                </c:forEach>
                            </p>
                        </display:column>
                    </display:table>
                </div>


            </c:if>
        </fieldset>
    </div>

</s:form>