<%--
    Document   : maklumat_laporan_polis
    Created on : Dec 22, 2011, 11:24:21 AM
    Author     : latifah.iskak
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>

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

    function refreshPagePolis(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_polis?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=600");
    }

    function viewRecordOP(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?popupViewLaporanOperasi&idOperasi='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
    }

     function viewRecordOPPO(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_polis?popupViewLaporanOperasi&idOperasiAgensi='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=800,height=300,scrollbars=yes");
    }

    function addRecord(id){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_polis?addRecord&idOp="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=400,scrollbars=yes");
    }

    function editRecord(idOperasi,index){
        if(index != ""){
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_polis?editRecord&idOperasi='+idOperasi+"&idOperasiAgensi="+index;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=yes");
        }

    }

    function deleteRecord(index){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            if(index != ""){
                var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_polis?deleteRecord&idOperasiAgensi='+index;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                    //refreshPage();
                },'html');
            }
        }
    }


    function muatNaikForm1(folderId, idPermohonan, dokumenKod, idRujukan, idDokumen) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/upload_action?popupUpload&folderId=' + folderId
            + '&idPermohonan=' + idPermohonan + '&dokumenKod=' + dokumenKod + '&idRujukan=' + idRujukan + '&idDokumen=' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }

    function removeImej(idImej) {
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_polis?deleteSelected&idImej='+idImej;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                refreshPagePolis();
            },'html');
        }
    }
</script>

<s:form beanclass="etanah.view.penguatkuasaan.MaklumatLaporanPolisActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <c:if test="${actionBean.opFlag == true}">
                <legend>
                    Senarai Laporan Polis
                </legend>
                <div class="content" align="center">
                    <c:if test="${edit}">
                        <display:table name="${actionBean.listOp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Maklumat Laporan Operasi">
                                <table width="20%" cellpadding="1">
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
                            <display:column title="Senarai Laporan Polis">
                                <c:set value="1" var="count"/>
                                <c:if test="${fn:length(line.senaraiAgensi) ne 0}">
                                    <table width="100%" cellpadding="1">
                                        <tr>
                                            <th  width="1%" align="center"><b>Bil</b></th>
                                            <th  width="1%" align="center"><b>No Laporan Polis dan Balai</b></th>
                                            <th  width="1%" align="center"><b>Tarikh</b></th>
                                            <th  width="5%" align="center" colspan="2"><b>Tindakan</b></th>
                                            <th  width="5%" align="center"><b>MuatNaik</b></th>

                                        </tr>
                                        <c:forEach items="${line.senaraiAgensi}" var="agensi">
                                            <tr>
                                                <td width="5%">${count}</td>
                                                <td width="20%"><a class="popup" onclick="viewRecordOPPO(${agensi.idOperasiAgensi})"> ${agensi.noRujukan}&nbsp;</a> </td>
                                                <td width="20%"><fmt:formatDate pattern="dd/MM/yyyy" value="${agensi.tarikhRujukan}"/></td>
                                                <td width="5%">
                                                    <div align="center">
                                                        <img align="center" alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editRecord('${line.idOperasi}','${agensi.idOperasiAgensi}')"/>
                                                    </div>
                                                </td>
                                                <td width="5%">
                                                    <div align="center">
                                                        <img align="center" alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="deleteRecord('${agensi.idOperasiAgensi}')"/>
                                                    </div>
                                                </td>
                                                <td width="30%">
                                                    <c:set value="1" var="count"/>
                                                    <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                                         onclick="muatNaikForm1('${actionBean.permohonan.folderDokumen.folderId}',
                                                             '${actionBean.permohonan.idPermohonan}','LP','${agensi.idOperasiAgensi}','');return false;" height="30" width="30" alt="Muat Naik"
                                                         onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen"/>
                                                    <c:forEach items="${actionBean.listDokumen}" var="kf">

                                                        <c:if test="${kf.dokumen.dalamanNilai1 eq agensi.idOperasiAgensi}">
                                                            <p align="left">

                                                                <c:if test="${kf.dokumen.kodDokumen.kod eq 'LP'}">
                                                                    <c:if test="${kf.dokumen.namaFizikal != null}">
                                                                        <br>
                                                                        -
                                                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                             onclick="doViewReport('${kf.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                                             onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${kf.dokumen.kodDokumen.nama}"/>
                                                                        ${count}-${kf.dokumen.tajuk}/
                                                                        <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                                             onclick="removeImej('${kf.dokumen.idDokumen}');" height="15" width="15" alt="Hapus"
                                                                             onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen"/>

                                                                        <c:set value="${count+1}" var="count"/>
                                                                    </c:if>
                                                                </c:if>
                                                            </p>
                                                        </c:if>
                                                    </c:forEach>

                                                </td>

                                            </tr>

                                            <c:set value="${count+1}" var="count"/>
                                        </c:forEach>
                                    </table>
                                </c:if>
                            </display:column>
                            <display:column title="Tambah Laporan Polis">
                                <s:button class="btn" value="Tambah" name="new" id="new" onclick="addRecord('${line.idOperasi}');"/>
                            </display:column>

                        </display:table>
                    </c:if>
                    <c:if test="${view}">
                        <display:table name="${actionBean.listOp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Maklumat Laporan Operasi">
                                <table width="20%" cellpadding="1">
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
                            <display:column title="Senarai Laporan Polis">
                                <c:set value="1" var="count"/>
                                <c:if test="${fn:length(line.senaraiAgensi) ne 0}">
                                    <table width="100%" cellpadding="1">
                                        <tr>
                                            <th  width="1%" align="center"><b>Bil</b></th>
                                            <th  width="1%" align="center"><b>No Laporan Polis dan Balai</b></th>
                                            <th  width="1%" align="center"><b>Tarikh</b></th>

                                            <th  width="5%" align="center"><b>Papar</b></th>

                                        </tr>
                                        <c:forEach items="${line.senaraiAgensi}" var="agensi">
                                            <tr>
                                                <td width="5%">${count}</td>
                                                <td width="20%"> ${agensi.noRujukan}&nbsp; </td>
                                                <td width="20%"><fmt:formatDate pattern="dd/MM/yyyy" value="${agensi.tarikhRujukan}"/></td>
                                                <td width="30%">
                                                    <c:set value="1" var="count"/>
                                                    <c:forEach items="${actionBean.listDokumen}" var="kf">

                                                        <c:if test="${kf.dokumen.dalamanNilai1 eq agensi.idOperasiAgensi}">
                                                            <p align="left">

                                                                <c:if test="${kf.dokumen.kodDokumen.kod eq 'LP'}">
                                                                    <c:if test="${kf.dokumen.namaFizikal != null}">
                                                                        <br>
                                                                        -
                                                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                             onclick="doViewReport('${kf.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                                             onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${kf.dokumen.kodDokumen.nama}"/>
                                                                        ${count}-${kf.dokumen.tajuk}/
                                                                        <c:set value="${count+1}" var="count"/>
                                                                    </c:if>
                                                                </c:if>
                                                            </p>
                                                        </c:if>
                                                    </c:forEach>
                                                </td>
                                            </tr>
                                            <c:set value="${count+1}" var="count"/>
                                        </c:forEach>
                                    </table>
                                </c:if>
                            </display:column>
                        </display:table>
                    </c:if>
                </div>
            </c:if>
        </fieldset>
    </div>
</s:form>
