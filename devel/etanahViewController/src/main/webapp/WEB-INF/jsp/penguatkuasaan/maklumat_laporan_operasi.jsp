<%-- 
    Document   : maklumat_laporan_operasi
    Created on : Nov 16, 2011, 11:24:21 AM
    Author     : latifah.iskak
--%>


<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>

<style type="text/css">

    .tablecloth{
        padding:0px;
        width:90%;
    }

    .infoLP{
        float: right;
        font-weight: bold;
        color:#003194;
        font-family:Tahoma;
        font-size: 13px;

    }
</style>


<script type="text/javascript">

    function addRecord(){
        //window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?popupTambahLaporanOperasi", "eTanah",
        //"status=0,toolbar=0,location=0,menubar=0,width=1100,height=600,scrollbars=yes");
        
        var url = "${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?popupTambahLaporanOperasi";

        params  = 'width='+screen.width;
        params += ', height='+screen.height;
        params += ', top=0, left=0'
        params += ', fullscreen=no';
        params += ', directories=no';
        params += ', location=no';
        params += ', menubar=no';
        params += ', resizable=no';
        params += ', scrollbars=yes';
        params += ', status=no';
        params += ', toolbar=no';
        newwin=window.open(url,'PopUp', params);
        if (window.focus) {newwin.focus()}
        return false;
    }

    function editRecord(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?popupEditLaporanOperasi&idOperasi='+id;
        params  = 'width='+screen.width;
        params += ', height='+screen.height;
        params += ', top=0, left=0'
        params += ', fullscreen=no';
        params += ', directories=no';
        params += ', location=no';
        params += ', menubar=no';
        params += ', resizable=no';
        params += ', scrollbars=yes';
        params += ', status=no';
        params += ', toolbar=no';
        newwin=window.open(url,'PopUp', params);
        if (window.focus) {newwin.focus()}
        return false;
        //window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1100,height=600,scrollbars=yes");
    }


    function deleteRecord(id){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?deleteOperasiPenguatkuasaan&idOperasi='+id;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
           
        }
    }
    
    function refreshPage(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
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
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?deleteSelected&idImej='+idImej;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                refreshPage();
            },'html');
        }
    }
    
    function viewRecord(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?popupViewLaporanOperasi&idOperasi='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }

    function viewRecordOP(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?popupViewLaporanOperasi&idOperasi='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }




</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatLaporanOperasiActionBean">
    <s:messages />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Laporan Operasi
            </legend>
            <c:if test="${edit}">
                <div class="instr-fieldset"><br>
                    Klik butang tambah untuk masukkan maklumat laporan operasi
                </div>
                <br>
                <div class="content" align="center">
                    <display:table name="${actionBean.senaraiOperasiPenguatkuasaan}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                        <%--<display:table name="${actionBean.senaraiOperasiPenguatkuasaan}" id="line" class="tablecloth" pagesize="10" cellpadding="0" cellspacing="0"
                                       requestURI="${pageContext.request.contextPath}/maklumat_laporan_operasi">--%>
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Maklumat Laporan Operasi">
                            <table width="10%" cellpadding="1">
                                <tr>
                                    <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                    <td width="20%"><u><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></u></td>
                                </tr>
                                <tr>
                                    <td width="20%"><font class="infoLP">Tarikh laporan :</font></td>
                                    <td width="20%">
                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhOperasi}"/></td>
                                </tr>
                                <tr>
                                    <td width="20%"><font class="infoLP">Masa laporan :</font></td>
                                    <td width="20%">
                                        <fmt:formatDate pattern="hh:mm" value="${line.tarikhOperasi}"/>
                                        <fmt:formatDate pattern="aaa" value="${line.tarikhOperasi}" var="time"/>
                                        <c:if test="${time eq 'AM'}">PAGI</c:if>
                                        <c:if test="${time eq 'PM'}">PETANG</c:if>
                                        </td>
                                    </tr>

                                </table>
                        </display:column>
                        <display:column title="Jenis Operasi" property="jenisTindakan" style="width:350px;"/>
                        <display:column title="Muat Naik/Papar">
                            <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                 onclick="muatNaikForm1('${actionBean.permohonan.folderDokumen.folderId}',
                                     '${actionBean.permohonan.idPermohonan}','LO','${line.idOperasi}');return false;" height="30" width="30" alt="Muat Naik"
                                 onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen"/>
                            <p align="center">
                                <c:set value="1" var="count"/>
                                <c:forEach items="${actionBean.permohonan.folderDokumen.senaraiKandungan}" var="senarai">
                                    <c:if test="${senarai.dokumen.kodDokumen.kod eq 'LO' && senarai.dokumen.perihal eq line.idOperasi}">
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
                        <display:column title="Kemaskini">
                            <div align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editRecord('${line.idOperasi}')"/>
                            </div>
                        </display:column>
                        <display:column title="Hapus">
                            <c:if test="${line_rowNum != 1}">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="deleteRecord('${actionBean.senaraiOperasiPenguatkuasaan[line_rowNum-1].idOperasi}','');" />
                                </div>
                            </c:if>
                        </display:column>

                    </display:table>
                    <br>

                    <s:button class="btn" value="Tambah" name="new" id="new" onclick="addRecord();"/>
                </div>
            </c:if>

            <c:if test="${view}">
                <br>
                <div class="content" align="center">
                    <display:table name="${actionBean.senaraiOperasiPenguatkuasaan}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                        <%--<display:table name="${actionBean.senaraiOperasiPenguatkuasaan}" id="line" class="tablecloth" pagesize="10" cellpadding="0" cellspacing="0"
                                       requestURI="${pageContext.request.contextPath}/maklumat_laporan_operasi">--%>
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Maklumat Laporan Operasi">
                            <table width="10%" cellpadding="1">
                                <tr>
                                    <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                    <td width="20%"><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></td>
                                </tr>
                                <tr>
                                    <td width="20%"><font class="infoLP">Tarikh laporan :</font></td>
                                    <td width="20%">
                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhOperasi}"/></td>
                                </tr>
                                <tr>
                                    <td width="20%"><font class="infoLP">Masa laporan :</font></td>
                                    <td width="20%">
                                        <fmt:formatDate pattern="hh:mm" value="${line.tarikhOperasi}"/>
                                        <fmt:formatDate pattern="aaa" value="${line.tarikhOperasi}" var="time"/>
                                        <c:if test="${time eq 'AM'}">PAGI</c:if>
                                        <c:if test="${time eq 'PM'}">PETANG</c:if>
                                        </td>
                                    </tr>

                                </table>
                        </display:column>
                        <display:column title="Jenis Operasi" property="jenisTindakan" style="width:350px;"/>
                        <display:column title="Papar">
                            <p align="center">
                                <c:set value="1" var="count"/>
                                <c:forEach items="${actionBean.permohonan.folderDokumen.senaraiKandungan}" var="senarai">
                                    <c:if test="${senarai.dokumen.kodDokumen.kod eq 'LO' && senarai.dokumen.perihal eq line.idOperasi}">
                                        <c:if test="${senarai.dokumen.namaFizikal != null}">
                                            <br>
                                            -
                                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                 onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                 onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                            ${count}-${senarai.dokumen.tajuk}/
                                            <c:set value="${count+1}" var="count"/>
                                        </c:if>
                                    </c:if>

                                </c:forEach>
                                <c:forEach items="${actionBean.senaraiKandungan}" var="senarai">
                                    <c:if test="${senarai.dokumen.kodDokumen.kod eq 'LO' && senarai.dokumen.perihal eq line.idOperasi}">
                                        <c:if test="${senarai.dokumen.namaFizikal != null}">
                                            <br>
                                            -
                                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                 onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                 onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                            ${count}-${senarai.dokumen.tajuk}/
                                            <c:set value="${count+1}" var="count"/>
                                        </c:if>
                                    </c:if>

                                </c:forEach>
                            </p>
                        </display:column>

                    </display:table>
                    <br>
                </div>
            </c:if>


        </fieldset>
    </div>
</s:form>

