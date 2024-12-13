<%--
    Document   : maklumat_barang_tahanan
    Created on : 04-Feb-2010, 12:27:21
    Author     : nurshahida.radzi
    Modify by  : sitifariza.hanim (02Ogos2011)
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>

<c:if test="${multipleOp}">
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

        .infoHeader{
            font-weight: bold;
            color:#003194;
            font-family:Tahoma;
            font-size: 13px;
            text-align: center;

        }

    </style>
</c:if>

<script type="text/javascript">
  
    function tambahBaru2(id){
        //alert(id);
        window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_barang_tahanan?hakMilikPopup&idOp="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=yes");
    }
    function tambahBaru(){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_barang_tahanan?hakMilikPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=yes");
    }

    function refreshPageCeroboh(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_barang_tahanan?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function removeMaklumatBarangTahanan(idBarang){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_barang_tahanan?deleteBarangRampasan&idBarang='+idBarang;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
    }

    function popupDetail(idBarang){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_barang_tahanan?editBarangRampasan&idBarang='+idBarang;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=yes");
    }

    function popup(idBarang){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_barang_tahanan?viewBarangDetail&idBarang='+idBarang;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=yes");
    }

    function test(f) {
        $(f).clearForm();
    }

    function refreshPage(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_barang_tahanan?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function muatNaikForm1(folderId, dokumenId, idPermohonan, dokumenKod, idBarang) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/upload?muatNaikForm1&folderId=' + folderId + '&dokumenId='
            + dokumenId + '&idPermohonan=' + idPermohonan + '&dokumenKod=' + dokumenKod + '&idBarang=' + idBarang;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }

    function muatNaikForm(folderId, idPermohonan, dokumenKod, idBarang, idDokumen) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/upload_action?popupUpload&folderId=' + folderId+ '&idPermohonan=' 
            + idPermohonan + '&dokumenKod=' + dokumenKod + '&idBarang=' + idBarang + '&idDokumen=' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }
            
    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,resizable=yes");
    }
            
    function removeImej(idImej,idBarang) {
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_barang_tahanan?deleteSelected&idImej='+idImej+'&idBarang='+idBarang;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                refreshPage();
            },'html');}
    }

    function viewRecordOP(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?popupViewLaporanOperasi&idOperasi='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }

    function addRecord(id){
        //alert(id);
        window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_barang_tahanan?addRecord&idOp="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=yes");
    }

    function editRecord(idBarang,idOp){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_barang_tahanan?editBarangRampasan&idBarang='+idBarang+"&idOp="+idOp;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=yes");
    }
</script>

<s:form beanclass="etanah.view.penguatkuasaan.MaklumatBarangTahananActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <c:if test="${actionBean.opFlag == true}">
                <legend>
                    Maklumat Barang Rampasan
                </legend>
                <div class="content" align="center">
                    <c:if test="${!multipleOp}">
                        <div class="instr-fieldset">
                            Klik butang tambah untuk masukkan maklumat
                        </div>&nbsp;
                        <display:table class="tablecloth" name="${actionBean.senaraiBarangRampasan}" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="/penguatkuasaan/maklumat_barang_tahanan">
                            <display:column title="Bil">${line_rowNum}</display:column>
                            <display:column title="Barang yang Dirampas"><a class="popup" onclick="popup(${line.idBarang})">${line.item}</a>&nbsp;&nbsp;${line.nomborPendaftaran}</display:column>
                            <display:column title="Tempat Simpanan" property="tempatSimpanan"></display:column>
                            <display:column title="Status" property="status.nama"></display:column>
                            <display:column title="Muat Naik/Papar">
                                <c:if test="${line.imej.namaFizikal != null}">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                         onclick="doViewReport('${line.imej.idDokumen}');" height="30" width="30" alt="papar"
                                         onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${line.imej.kodDokumen.nama}"/> /
                                </c:if>
                                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                     onclick="muatNaikForm('${actionBean.permohonan.folderDokumen.folderId}',
                                         '${actionBean.permohonan.idPermohonan}','IB','${line.idBarang}','${line.imej.idDokumen}');return false;" height="30" width="30" alt="Muat Naik"
                                     onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen ${line.imej.kodDokumen.nama}"/>
                                <c:if test="${line.imej.namaFizikal != null}">
                                    <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                         onclick="removeImej('${line.imej.idDokumen}','${line.idBarang}');" height="15" width="15" alt="Hapus"
                                         onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen ${line.imej.kodDokumen.nama}"/>
                                </c:if>
                            </display:column>
                            <display:column title="Hapus" >
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeMaklumatBarangTahanan('${line.idBarang}');"/>
                                </div>
                            </display:column>
                            <display:column title="Kemaskini">
                                <div align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popupDetail('${line.idBarang}')"/>
                                </div>
                            </display:column>
                        </display:table>
                        <table>
                            <tr>
                                <td align="right">
                                    <c:choose>
                                        <c:when test="${actionBean.kodNegeriPermohonan eq '05'}">
                                            <%--<s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBaru2('${actionBean.operasiPenguatkuasaan.idOperasi}');"/>--%>
                                            <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBaru();"/>
                                        </c:when>
                                        <c:otherwise>
                                            <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBaru();"/>
                                        </c:otherwise>
                                    </c:choose>
                                    
                                </td>
                            </tr>
                        </table>

                    </c:if>



                    <c:if test="${multipleOp}">
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
                            <display:column title="Maklumat Barang Rampasan">
                                <c:set value="1" var="count"/>
                                <c:if test="${fn:length(line.senaraiBarangRampasan) ne 0}">
                                    <table width="100%" cellpadding="1">
                                        <tr>
                                            <th  width="1%" align="center"><b>Bil</b></th>
                                            <th  width="1%" align="center"><b>Item</b></th>
                                            <th  width="5%" align="center" colspan="2"><b>Tindakan</b></th>
                                            <th  width="5%" align="center"><b>MuatNaik</b></th>

                                        </tr>
                                        <c:forEach items="${line.senaraiBarangRampasan}" var="barang">
                                            <tr>
                                                <td width="5%">${count}</td>
                                                <td width="50%"><a class="popup" onclick="popup(${barang.idBarang})">${barang.item} </a>&nbsp;&nbsp;${barang.nomborPendaftaran}</td>
                                                <td width="5%">
                                                    <div align="center">
                                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" title="Kemasikini untuk Barang ${barang.item}" onclick="editRecord('${barang.idBarang}','${line.idOperasi}')"/>
                                                    </div>
                                                </td>
                                                <td width="5%">
                                                    <div align="center">
                                                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" title="Hapus untuk Barang ${barang.item}" onclick="removeMaklumatBarangTahanan('${barang.idBarang}');"/>
                                                    </div>
                                                </td>
                                                <td width="30%">
                                                    <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                                         onclick="muatNaikForm('${actionBean.permohonan.folderDokumen.folderId}',
                                                             '${actionBean.permohonan.idPermohonan}','IB','${barang.idBarang}','${barang.imej.idDokumen}');return false;" height="30" width="30" alt="Muat Naik"
                                                         onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen "/>
                                                    <c:if test="${barang.imej.namaFizikal != null}">/
                                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                             onclick="doViewReport('${barang.imej.idDokumen}');" height="30" width="30" alt="papar"
                                                             onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${barang.imej.kodDokumen.nama}"/> /
                                                    </c:if>
                                                    <c:if test="${barang.imej.namaFizikal != null}">
                                                        <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                             onclick="removeImej('${barang.imej.idDokumen}','${barang.idBarang}');" height="15" width="15" alt="Hapus"
                                                             onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen ${barang.imej.kodDokumen.nama}"/>
                                                    </c:if>
                                                </td>

                                            </tr>

                                            <c:set value="${count+1}" var="count"/>
                                        </c:forEach>
                                    </table>
                                </c:if>
                            </display:column>
                            <display:column title="Tambah Barang">
                                <s:button class="btn" value="Tambah" name="new" id="new" onclick="addRecord('${line.idOperasi}');"/>
                            </display:column>

                        </display:table>
                    </c:if>


                </c:if>
                <br>
            </div>
        </fieldset>
    </div>
</s:form>
