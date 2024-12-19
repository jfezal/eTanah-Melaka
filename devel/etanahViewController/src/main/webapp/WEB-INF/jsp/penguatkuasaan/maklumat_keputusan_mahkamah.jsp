<%-- 
    Document   : maklumat_keputusan_mahkamah : for MELAKA sek 422,423,424,427,428,429
    Created on : Sep 22, 2011, 11:24:21 AM
    Author     : latifah.iskak
--%>


<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">

    function addRecord(){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_mahkamah?popupTarikhSebutan", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }

    function editRecord(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_mahkamah?popupEditTarikhSebutan&idRujukan='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=910,height=800,scrollbars=yes");
    }

    function kemaskiniKeputusan(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_mahkamah?popupKemaskiniKeputusan&idRujukan='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=910,height=800,scrollbars=yes");
    }

    function deleteRecord(idRujukan){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_mahkamah?deleteSingle&idRujukan='+idRujukan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
           
        }
    }

    function refreshPageMahkamah(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_mahkamah?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
    
    function refreshPage(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_mahkamah?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function refreshPageKemaskiniKeputusan(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_mahkamah?showForm3';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
    
    function refreshPageRekodPerbicaraan(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_mahkamah?showForm4';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
    
    function muatNaikForm1(folderId, dokumenId, idPermohonan, dokumenKod, idRujukan) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/upload?muatNaikForm1&folderId=' + folderId
            + '&idPermohonan=' + idPermohonan + '&dokumenKod=' + dokumenKod + '&idRujukan=' + idRujukan + '&dokumenId=' + dokumenId;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }
    
    function muatNaikForm(folderId, dokumenId, idPermohonan, dokumenKod, idRujukan) {
        var left = (screen.width/2)-(1000/2); 
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/upload_action?popupUpload&folderId=' + folderId+ '&idPermohonan=' + idPermohonan + '&dokumenKod=' + dokumenKod+ '&idRujukan=' + idRujukan;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }
    
    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function removeImej(idImej,idRujukan){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_mahkamah?deleteSelected&idImej='+idImej+'&idRujukan='+idRujukan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
        refreshPage();
    }
    
    function viewRecord(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_mahkamah?popupViewRecord&idRujukan='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }
    
    function kemaskiniRekod(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_mahkamah?popupKemaskiniRekodPerbicaraan&idRujukan='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=910,height=800,scrollbars=yes");
    }
    
    function addRecordMahkamah(){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_mahkamah?popupMaklumatMahkamah", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800,scrollbars=yes");
    }
    
    



</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatMahkamahActionBean">
    <s:messages />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Rekod Mahkamah
            </legend>
            <c:if test="${edit}">
                <div class="instr-fieldset"><br>
                    Klik butang tambah untuk masukkan maklumat mahkamah 
                </div>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiMahkamah}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="No.Rujukan"><a class="popup" onclick="viewRecord(${line.idRujukan})">${line.noRujukan}</a></display:column>
                        <display:column title="Mahkamah">${line.agensi.nama} ${line.namaSidang}</display:column>
                        <display:column title="Tarikh" style="${bcolor}"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhSidang}"/></display:column>
                        <display:column title="Status">${line.keputusanPendakwaan.nama} </display:column>
                        <display:column title="Minit" property="catatan"></display:column>
                        <display:column title="Muat Naik">
                            <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                 onclick="muatNaikForm('${actionBean.permohonan.folderDokumen.folderId}', '${line.dokumen.idDokumen}',
                                     '${actionBean.permohonan.idPermohonan}','KMD','${line.idRujukan}');return false;" height="30" width="30" alt="Muat Naik"
                                 onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen"/>
                            <p align="center">
                                <c:set value="1" var="count"/>
                                <c:forEach items="${actionBean.permohonan.folderDokumen.senaraiKandungan}" var="senarai">
                                    <c:if test="${senarai.dokumen.kodDokumen.kod eq 'KMD'}">
                                        <c:if test="${senarai.dokumen.namaFizikal != null && senarai.dokumen.perihal eq line.idRujukan}">
                                            <%--${count})--%>
                                            <br>
                                            -
                                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                 onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                 onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                            <%--${senarai.dokumen.kodDokumen.nama} ${count} (--%>${senarai.dokumen.tajuk}<%--)--%>/
                                            <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                 onclick="removeImej('${senarai.dokumen.idDokumen}','${line.idRujukan}');" height="15" width="15" alt="Hapus"
                                                 onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen ${line.dokumen.kodDokumen.nama}"/>

                                            <c:set value="${count+1}" var="count"/>
                                        </c:if>
                                    </c:if>

                                </c:forEach>
                            </p>
                        </display:column>

                        <display:column title="Kemaskini">
                            <div align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editRecord('${line.idRujukan}')"/>
                            </div>
                        </display:column>
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="deleteRecord('${actionBean.senaraiMahkamah[line_rowNum-1].idRujukan}');" />
                            </div>
                        </display:column>
                    </display:table>
                    <br>
                    <c:if test="${!actionBean.addInfoByPUU}">  
                        <s:button class="btn" value="Tambah" name="new" id="new" onclick="addRecord();"/>
                    </c:if>
                    <c:if test="${actionBean.addInfoByPUU}">  
                        <s:button class="btn" value="Tambah" name="new" id="new" onclick="addRecordMahkamah();"/>
                    </c:if>

                </div>
            </c:if>

            <c:if test="${kemaskiniKeputusan || editPerbicaraan}">
                <div class="instr-fieldset"><br>
                    <c:if test="${kemaskiniKeputusan}">
                        Sila klik kemaskini untuk memasukkan maklumat keputusan mahkamah
                    </c:if>
                    <c:if test="${editPerbicaraan}">
                        Sila klik kemaskini untuk memasukkan maklumat tarikh perbicaraan
                    </c:if>
                </div>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiMahkamah}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="No.Rujukan"><a class="popup" onclick="viewRecord(${line.idRujukan})">${line.noRujukan}</a></display:column>
                        <display:column title="Mahkamah">${line.agensi.nama} ${line.namaSidang}</display:column>
                        <display:column title="Tarikh" style="${bcolor}"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhSidang}"/></display:column>
                        <display:column title="Status">${line.keputusanPendakwaan.nama} </display:column>
                        <display:column title="Minit" property="catatan"></display:column>
                        <display:column title="Papar">
                            <c:if test="${line.dokumen.namaFizikal != null}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReport('${line.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${line.dokumen.kodDokumen.nama}"/>
                            </c:if>
                        </display:column>
                        <display:column title="Kemaskini">
                            <c:if test="${editPerbicaraan}">
                                <div align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="kemaskiniRekod('${line.idRujukan}')"/>
                                </div>
                            </c:if>
                            <c:if test="${kemaskiniKeputusan}">
                                <div align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="kemaskiniKeputusan('${line.idRujukan}')"/>
                                </div>
                            </c:if>


                        </display:column>

                    </display:table>
                    <br>

                </div>
            </c:if>

            <c:if test="${view}">
                <div align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiMahkamah}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="No.Rujukan"><a class="popup" onclick="viewRecord(${line.idRujukan})">${line.noRujukan}</a></display:column>
                        <display:column title="Mahkamah">${line.agensi.nama} ${line.namaSidang}</display:column>
                        <display:column title="Tarikh" style="${bcolor}"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhSidang}"/></display:column>
                        <display:column title="Status" >${line.keputusanPendakwaan.nama} </display:column>
                        <display:column title="Minit" property="catatan"></display:column>
                        <display:column title="Papar">
                            <p align="center">
                                <c:set value="1" var="count"/>
                                <c:forEach items="${actionBean.permohonan.folderDokumen.senaraiKandungan}" var="senarai">
                                    <c:if test="${senarai.dokumen.kodDokumen.kod eq 'KMD'}">
                                        <c:if test="${senarai.dokumen.namaFizikal != null && senarai.dokumen.perihal eq line.idRujukan}">
                                            <%--${count})--%>
                                            <br>
                                            -
                                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                 onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                 onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                            <%--${senarai.dokumen.kodDokumen.nama} ${count} (--%>${senarai.dokumen.tajuk}<%--)--%>/
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

