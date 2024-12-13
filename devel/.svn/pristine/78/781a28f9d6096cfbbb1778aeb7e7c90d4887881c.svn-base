<%--
    Document   : maklumat_orang_disyaki
    Created on :
    Author     : nurshahida.radzi
    modify by sitifariza.hanim (18042011)
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
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
    function tambahBaru(){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_orang_disyaki?hakMilikPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=600,scrollbars=yes");
    }


    function removeMaklumatOrangDisyaki(idOrangKenaSyak){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_orang_disyaki?deleteOrangKenaSyak&idOrangKenaSyak='+idOrangKenaSyak;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
    }

    function popup(h){

        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_orang_disyaki?editOrangKenaSyak&idOrangKenaSyak='+h;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=yes");
    }

    function viewOKS(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_orang_disyaki?viewOrangKenaSyak&idOrangKenaSyak='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=yes");
    }

    function refreshPageOKS(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_orang_disyaki?refreshpage';
        $.get(url,
        function(data){
            //$("#senaraiOks").replaceWith($('#senaraiOks', $(data)));
            $('#page_div').html(data);
        },'html');
    }

    function refreshPageUploadOKS(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_orang_disyaki?refreshpage';
        $.get(url,
        function(data){
            $("#uploadOKS").replaceWith($('#uploadOKS', $(data)));
        },'html');
    }

    function refreshPage(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_orang_disyaki?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
            //refreshPage();
        },'html');
    }

    function muatNaikForm(idOrangKenaSyak,idDokumen) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_orang_disyaki?popupUpload&idOrangKenaSyak='+idOrangKenaSyak+'&idDokumen=' + idDokumen;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=600");
    }

    function removeImej(idImej) {
        if (confirm('Adakah anda pasti untuk hapus Lampiran?')){
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_orang_disyaki?deleteSelected&idImej='+idImej;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                refreshPageOKS();
            },'html');}
    }

    function addRecord(id){
        //alert(id);
        window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_orang_disyaki?hakMilikPopup&idOp="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=yes");
    }

    function editRecord(idOks,idOp){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_orang_disyaki?editOrangKenaSyak&idOrangKenaSyak='+idOks+"&idOp="+idOp;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=yes");
    }

    function viewRecordOP(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?popupViewLaporanOperasi&idOperasi='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }


</script>

<s:form beanclass="etanah.view.penguatkuasaan.MaklumatOrangDisyakiActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <div class="content" align="center">
                <c:if test="${!multipleOp}">
                    <legend>
                        Maklumat Orang Yang Disyaki
                    </legend>
                    <div class="content" align="left">
                        <p>Klik butang tambah untuk masukkan maklumat orang yang disyaki</p>
                    </div>
                    <display:table class="tablecloth" name="${actionBean.permohonan.aduan.senaraiOrangKenaSyak}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil"><a class="popup" onclick="viewOKS(${line.idOrangKenaSyak})">${line_rowNum}</a></display:column>
                        <display:column title="No.Pengenalan">${line.noPengenalan}</display:column>
                        <display:column property="nama" title="Nama"></display:column>
                        <display:column title="Alamat" style="text-transform: uppercase">${line.alamat.alamat1}
                            <c:if test="${line.alamat.alamat2 ne null}"> , </c:if>
                            ${line.alamat.alamat2}
                            <c:if test="${line.alamat.alamat3 ne null}"> , </c:if>
                            ${line.alamat.alamat3}
                            <c:if test="${line.alamat.alamat4 ne null}"> , </c:if>
                            ${line.alamat.alamat4}
                            ${line.alamat.poskod}
                            ${line.alamat.negeri.nama}
                        </display:column>
                        <%--<display:column title="Keterangan" property="keterangan"></display:column>--%>
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeMaklumatOrangDisyaki('${actionBean.permohonan.aduan.senaraiOrangKenaSyak[line_rowNum-1].idOrangKenaSyak}');"/>
                            </div>
                        </display:column>

                        <display:column title="Kemaskini">
                            <div align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${line.idOrangKenaSyak}')"/>
                            </div>
                        </display:column>
                        <display:column title="Lampiran">
                            <div id="uploadOKS">
                                <p align="center">
                                    <c:if test="${line.dokumen.namaFizikal == null}">
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                             onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line.idOrangKenaSyak}','${line.dokumen.idDokumen}');return false;" title="Muat Naik Dokumen"/>
                                    </c:if>
                                </p>
                                <table>
                                    <c:if test="${line.dokumen.namaFizikal != null}">

                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                             onclick="doViewReport('${line.dokumen.idDokumen}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>

                                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                             onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line.idOrangKenaSyak}','${line.dokumen.idDokumen}');return false;" title="Muat Naik Dokumen"/>

                                        <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                             onclick="removeImej('${line.dokumen.idDokumen}');" height="15" width="15" alt="Hapus"
                                             onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen ${line.dokumen.kodDokumen.nama}"/>
                                    </c:if>
                                </table>
                            </div>

                        </display:column>

                    </display:table>

                    <table>
                        <tr>
                            <td align="right">
                                <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBaru();"/>
                            </td>
                        </tr>
                    </table>
                </c:if>
                <c:if test="${multipleOp}">
                    <c:if test="${actionBean.opFlag == true}">
                        <legend>
                            Maklumat Orang Yang Disyaki
                        </legend>
                        <div class="content" align="left">
                            <p>Klik butang tambah untuk masukkan maklumat orang yang disyaki</p>
                        </div>
                        <display:table name="${actionBean.listOp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Maklumat Laporan Operasi">
                                <table width="20%" cellpadding="1">
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
                            <display:column title="Maklumat Orang Disyaki">
                                <c:set value="1" var="count"/>
                                <c:if test="${fn:length(line.senaraiAduanOrangKenaSyak) ne 0}">
                                    <table width="100%" cellpadding="1">
                                        <tr>
                                            <th  width="1%" align="center"><b>Bil</b></th>
                                            <th  width="1%" align="center"><b>Nama</b></th>
                                            <th  width="5%" align="center" colspan="2"><b>Tindakan</b></th>
                                            <th  width="5%" align="center"><b>MuatNaik</b></th>

                                        </tr>
                                        <c:forEach items="${line.senaraiAduanOrangKenaSyak}" var="oks">
                                            <c:if test="${actionBean.statusIP}">
                                                <c:if test="${oks.permohonanAduanOrangKenaSyak.idPermohonan eq actionBean.permohonan.idPermohonan}">
                                                    <tr>
                                                        <td width="5%">${count}</td>
                                                        <td width="50%"><u><a class="popup" onclick="viewOKS(${oks.idOrangKenaSyak})">${oks.nama}</a></u></td>
                                                        <td width="5%">
                                                            <div align="center">
                                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" title="Kemasikini untuk OKS : ${oks.nama}" onclick="editRecord('${oks.idOrangKenaSyak}','${line.idOperasi}')"/>
                                                            </div>
                                                        </td>
                                                        <td width="5%">
                                                            <div align="center">
                                                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" title="Hapus untuk OKS : ${oks.nama}" onclick="removeMaklumatOrangDisyaki('${oks.idOrangKenaSyak}');"/>
                                                            </div>
                                                        </td>
                                                        <td width="30%">
                                                            <div id="uploadOKS">
                                                                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                                                     onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${oks.idOrangKenaSyak}','${oks.dokumen.idDokumen}');return false;" title="Muat Naik Dokumen"/>
                                                                <c:if test="${oks.dokumen.namaFizikal != null}">/
                                                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                         onclick="doViewReport('${oks.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                                         onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${oks.dokumen.kodDokumen.nama}"/> /
                                                                </c:if>
                                                                <c:if test="${oks.dokumen.namaFizikal != null}">
                                                                    <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                                         onclick="removeImej('${oks.dokumen.idDokumen}','${oks.idOrangKenaSyak}');" height="15" width="15" alt="Hapus"
                                                                         onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen ${oks.dokumen.kodDokumen.nama}"/>
                                                                </c:if>
                                                            </div>
                                                        </td>

                                                    </tr>
                                                    <c:set value="${count+1}" var="count"/>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${!actionBean.statusIP}">
                                                <tr>
                                                    <td width="5%">${count}</td>
                                                    <td width="50%"><u><a class="popup" onclick="viewOKS(${oks.idOrangKenaSyak})">${oks.nama}</a></u></td>
                                                    <td width="5%">
                                                        <div align="center">
                                                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" title="Kemasikini untuk OKS : ${oks.nama}" onclick="editRecord('${oks.idOrangKenaSyak}','${line.idOperasi}')"/>
                                                        </div>
                                                    </td>
                                                    <td width="5%">
                                                        <div align="center">
                                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" title="Hapus untuk OKS : ${oks.nama}" onclick="removeMaklumatOrangDisyaki('${oks.idOrangKenaSyak}');"/>
                                                        </div>
                                                    </td>
                                                    <td width="30%">
                                                        <div id="uploadOKS">
                                                            <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                                                 onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${oks.idOrangKenaSyak}','${oks.dokumen.idDokumen}');return false;" title="Muat Naik Dokumen"/>
                                                            <c:if test="${oks.dokumen.namaFizikal != null}">/
                                                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                     onclick="doViewReport('${oks.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${oks.dokumen.kodDokumen.nama}"/> /
                                                            </c:if>
                                                            <c:if test="${oks.dokumen.namaFizikal != null}">
                                                                <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                                     onclick="removeImej('${oks.dokumen.idDokumen}','${oks.idOrangKenaSyak}');" height="15" width="15" alt="Hapus"
                                                                     onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen ${oks.dokumen.kodDokumen.nama}"/>
                                                            </c:if>
                                                        </div>
                                                    </td>

                                                </tr>
                                                <c:set value="${count+1}" var="count"/>
                                            </c:if>

                                        </c:forEach>
                                    </table>
                                </c:if>
                            </display:column>
                            <display:column title="Tambah">
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