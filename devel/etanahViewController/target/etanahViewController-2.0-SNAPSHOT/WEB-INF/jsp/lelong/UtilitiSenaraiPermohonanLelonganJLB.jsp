<%-- 
    Document   : UtilitiSenaraiPermohonanLelongan
    Created on : Apr 19, 2012, 3:34:54 PM
    Author     : mazurahayati.yusop, nur.aqilah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    function pilih(v) {
        window.open("${pageContext.request.contextPath}/lelong/senaraipembidaJLB?getPembida&idLelong= " + v, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=1200");
    }

    function tambah(v) {
        window.open("${pageContext.request.contextPath}/lelong/senaraipembidaJLB?tambahPembida&idPermohonan= " + v, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=1200");
    }

    function refreshingPagingFolder(idPermohonan) {
        var url = "${pageContext.request.contextPath}/lelong/senaraipembidaJLB?refresh&idPermohonan=" + idPermohonan;
        window.location = url;
    }

    function editStatus(val, val2, val3) {
        window.open("${pageContext.request.contextPath}/lelong/senaraipembidaJLB?tambahStatus&nama=" + val + "&idPembida=" + val2 + "&idLelong=" + val3, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=" + screen.width + ",height=" + screen.height + ",scrollbars=yes,left=0,top=0");
    }

    function updateStatus(idPermohonan, idPembida, idLelong, idHakmilik, kodStatus) {

        $.ajax({
            url: 'senaraipembidaJLB?simpanEditPembida&idPermohonan=' + idPermohonan + '&idPembida=' + idPembida + '&idLelong=' + idLelong + '&idHakmilik=' + idHakmilik + '&kodStatus=' + kodStatus,
            success: function() {
            },
            error: function() {
            }
        });
    }

    function updateStatusBersama(idPermohonan, idPihak, idLelong, kodStatus) {

        $.ajax({
            url: 'senaraipembidaJLB?simpanEditPembida&idPermohonan=' + idPermohonan + '&idPihak=' + idPihak + '&idLelong=' + idLelong + '&kodStatus=' + kodStatus,
            success: function() {
                alert(success);
            },
            error: function() {
                alert(error);
            }
        });
    }

    function popupAlamat(val, val2) {
        window.open("${pageContext.request.contextPath}/lelong/senaraipembidaJLB?tambahTarikhSenaraiHitam&idPembida=" + val + '&idPermohonan=' + val2, "eTanah",
                "status=0,toolbar=0,scrollbars=1,location=0,menubar=0,width=1000,height=1200");
    }
    function muatNaikForm(pembida, id, caraLelong) {
        var left = (screen.width / 2) - (1000 / 2);
        var top = (screen.height / 2) - (150 / 2);
        var url = '${pageContext.request.contextPath}/lelong/senaraipembida?popupUpload&idPembida=' + pembida + '&id=' + id + '&caraLelong=' + caraLelong;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }

    function scan(pembida, id) {
        var url = '${pageContext.request.contextPath}/lelong/senaraipembidaJLB?popupScan&idPembida=' + pembida + '&id=' + id;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=800,height=700");
    }

    //    function doViewReport(idDokumen) {
    //        var url= '${pageContext.request.contextPath}/lelong/dokumen/view/' + idDokumen;
    //            
    //        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    //    }
    function doViewReport(d) {
        window.open("${pageContext.request.contextPath}/lelong/dokumen/view?view&idDokumen=" + d, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }


    //    function doViewReport(idDokumen) {
    //        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
    //        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    //    }

    function doViewReport2(v) {
        var url = '${pageContext.request.contextPath}/lelong/senaraipembidaJLB?view&idPembida=' + v;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function doSearch(e, f) {
        var a = $('#idPermohonan').val();

        if (a == '') {
            alert('Sila masukan id Permohonan');
            return;
        }
        f.action = f.action + '?' + e;
        f.submit();
    }

    function clearForm() {

        $("#idPermohonan").val('');

    }

    $(document).ready(function() {
        var bil = $(".rowCount").length;

        for (var i = 0; i < bil; i++) {
            $('#viewReport' + i).hide();
        }
    });

    function tambahWakilPembida(v, v2) {

        window.open("${pageContext.request.contextPath}/lelong/senaraipembidaJLB?tambahWakil&idPembida= " + v + "&idPermohonan=" + v2, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=1200");
    }
    function kemaskiniPembida(val1, val2) {


        var idHakmilik = $('#hakmilik').val();
        var url = '${pageContext.request.contextPath}/lelong/senaraipembidaJLB?kemaskiniPembida&idPembida=' + val1 + "&idPermohonan=" + val2;
        window.open(url, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes");
    }

    function removePembida(val, val2) {
//        var id = $('#hiddenIdHakmilik').val();
//        alert("idPembida" + val);
//        alert("idPermohonan" + val2);
//alert();
        var answer = confirm("adakah anda pasti untuk Hapus?");
        if (answer) {
            var url = '${pageContext.request.contextPath}/lelong/senaraipembidaJLB?deletePembida&idPembida=' + val + '&idPermohonan=' + val2;
//            $.blockUI({
//                message: $('#displayBox'),
//                css: {
//                    top: ($(window).height() - 50) / 2 + 'px',
//                    left: ($(window).width() - 50) / 2 + 'px',
//                    width: '50px'
//                }
//            });
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
//                        $.unblockUI();
                    });
        }
    }
</script>
</script>
<s:form  beanclass="etanah.view.stripes.lelong.UtilitiSenaraiPermohonanLelonganPembidaJLBActionBean">
    <s:messages/>
    <s:errors/>

    <s:hidden id="idPembida" name="idPembida" value="${actionBean.idPembida}"/>

    <div class="subtitle" id ="aa">
        <fieldset class="aras1">
            <legend>
                Carian
            </legend>
            <p>
                <label> ID Permohonan :</label>
                <s:text name="idPermohonan" id ="idPermohonan" value="${actionBean.permohonan.idPermohonan}" maxlength="20" size="31" onblur="this.value=this.value.toUpperCase();"/>
            </p>

            <p align="right">
                <s:submit name="checkPermohonan" value="Cari" class="btn" onclick="doSearch(this.name, this.form);"/>
                <s:button name="reset" value="Isi Semula" class="btn" onclick="clearForm();"/>
                <%--<s:button name=" " value="Isi Semula" class="btn" onclick="clearText();" />--%>
            </p>
        </fieldset>
    </div>

    <c:if test="${actionBean.showForm eq true}">
        <c:if test="${fn:length(actionBean.senaraiPermohonan) > 0}">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Senarai Perserahan
                    </legend>
                    <p align="center">
                        <label></label>
                        <c:set var="row_num" value="${actionBean.__pg_start}"/>
                        <display:table class="tablecloth" name="${actionBean.senaraiPermohonan}"
                                       cellpadding="0" cellspacing="0" id="line"
                                       requestURI="/lelong/dokumen/folder"
                                       pagesize="10"                                   
                                       sort="external" size="${actionBean.__pg_total_records}" partialList="true">
                            <c:set var="row_num" value="${row_num+1}"/>
                            <display:column title="Bil">${row_num}</display:column>
                            <display:column title="ID Permohonan" style="vertical-align:top;" >
                                <s:link beanclass="etanah.view.stripes.lelong.UtilitiSenaraiPermohonanLelonganPembidaJLBActionBean"
                                        event="search" >
                                    <s:param name="idPermohonan" value="${line.idPermohonan}"/> ${line.idPermohonan}
                                </s:link>
                            </display:column>

                            <display:column property="kodUrusan.kod" title="Kod Urusan"/>
                            <display:column property="kodUrusan.nama" title="Urusan"/>
                            <display:column title="Tarikh Perserahan">
                                <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="d/MM/yyyy hh:mm:ss"/>
                            </display:column>

                        </display:table>
                    </p>
                    <br/>                
                </fieldset>
            </div>
        </c:if>
        <c:if test="${fn:length(actionBean.senaraiPermohonanCarian) > 0}">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Senarai Carian
                    </legend>
                    <p align="center">
                        <label></label>
                        <c:set var="row_num" value="${actionBean.__pg_start}"/>
                        <display:table class="tablecloth" name="${actionBean.senaraiPermohonanCarian}"
                                       cellpadding="0" cellspacing="0" id="line"
                                       requestURI="/lelong/dokumen/folder"
                                       pagesize="10"
                                       sort="external" size="${actionBean.__pg_total_records}" partialList="true">
                            <c:set var="row_num" value="${row_num+1}"/>
                            <display:column title="Bil">${row_num}</display:column>
                            <display:column title="ID Permohonan" style="vertical-align:top;" >
                                <s:link beanclass="etanah.view.stripes.lelong.UtilitiSenaraiPermohonanLelonganPembidaJLBActionBean"
                                        event="search" >
                                    <s:param name="idPermohonan" value="${line.idPermohonan}"/> ${line.idPermohonan}
                                </s:link>
                            </display:column>
                            <display:column property="urusan.kod" title="Kod Urusan"/>
                            <display:column property="urusan.nama" title="Urusan"/>
                            <display:column title="Tarikh Perserahan">
                                <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="d/MM/yyyy hh:mm:ss"/>
                            </display:column>
                        </display:table>
                    </p>
                    <br/>
                </fieldset>
            </div>
        </c:if>
    </c:if>
    <c:if test="${actionBean.view eq true}">
        <c:if test="${actionBean.enkuiri.caraLelong eq null}">
            <div class="subtitle">
                <fieldset class="aras1">

                    <legend>
                        Senarai Pembida untuk Permohonan :  ${actionBean.permohonan.idPermohonan} 
                    </legend> 
                    <div class="content" align="center"> 
                        <c:if test="${actionBean.permohonan.permohonanSebelum.idPermohonan ne null}">
                            <p>Permohonan Sebelum:  ${actionBean.permohonan.permohonanSebelum.idPermohonan}</p> 
                        </c:if>
                        <display:table name="${actionBean.list}" id="line1" class="tablecloth" requestURI="/lelong/senaraipembidaJLB">
                            <display:column title="Bil">
                                ${line1_rowNum}
                                <s:hidden name="" class="x${line1_rowNum -1}" value="${line.idLelong}"/>
                            </display:column>
                            <display:column property="pihak.nama"  title="Nama" style="text-transform:uppercase;"/>
                            <%--<a href="#" title="klik untuk paparan" onclick="editStatus('${line1.pihak.idPihak}','${line1.idPembida}','${line1.lelong.idLelong}');">${line1.pihak.nama}</a></display:column>--%>
                            <display:column property="pihak.noPengenalan" title="No. Pengenalan" style="text-transform:uppercase;" />
                            <display:column property="noRujukan" title="No Bank Draf" style="text-transform:uppercase;" />
                            <display:column property="pihak.noTelefonBimbit" title="No Telefon" style="text-transform:uppercase;" />
                            <display:column property="hakmilikPermohonan.hakmilik.idHakmilik"  title="ID Hakmilik" style="text-transform:uppercase;" />
                            <display:column title="Status" class="${line1_rowNum}" style="text-transform:uppercase;">
                                <s:hidden name="idPembida${line1_rowNum -1}" value="${line1.idPembida}"/>
                                <s:select name="kodSts${line1_rowNum -1}" id="" value="${line1.kodStsPembida.kod}" style="width:200px;" onchange="updateStatus('${actionBean.permohonan.idPermohonan}','${line1.idPembida}','${line1.lelong.idLelong}','${line1.hakmilikPermohonan.hakmilik.idHakmilik}',this.value)">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodStsPembida}" label="nama" value="kod" />
                                </s:select>
                                <c:if test="${line1.kodStsPembida.kod eq 'SH'}">
                                    <div align="right">
                                        <a id="" onclick="popupAlamat('${line1.idPembida}', '${actionBean.permohonan.idPermohonan}');
        return false;" onmouseover="this.style.cursor = 'pointer';">
                                            <img alt="Sila Klik Untuk Masukkan Tarikh Mula dan Tarikh Tamat" src='${pageContext.request.contextPath}/pub/images/edit.gif' />

                                        </a>
                                    </div>
                                </c:if>
                            </display:column> 

                            <display:column title="Tindakan">
                                <div align="center">
                                    <c:if test="${line1.dokumen.namaFizikal == null}">
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                             onmouseover="this.style.cursor = 'pointer';" onclick="muatNaikForm('${line1.idPembida}', '${line1_rowNum-1}', '${actionBean.enkuiri.caraLelong}');
        return false;" title="Muat Naik Dokumen">
                                        /
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                             onmouseover="this.style.cursor = 'pointer';" onclick="scan('${line1.idPembida}', '${line1_rowNum-1}');
        return false;" title="Imbas Dokumen">
                                        <div id="viewReport${line1_rowNum-1}">
                                            /<img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                                  onclick="doViewReport2('${line1.idPembida}');" onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen"></div>
                                        </c:if>
                                        <c:if test="${line1.dokumen.namaFizikal != null}">
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                             onmouseover="this.style.cursor = 'pointer';" onclick="muatNaikForm('${line1.idPembida}', '${line1_rowNum-1}', '${actionBean.enkuiri.caraLelong}');
        return false;" title="Muat Naik Dokumen">
                                        /
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                             onmouseover="this.style.cursor = 'pointer';" onclick="scan('${line1.idPembida}', '${line1_rowNum-1}');
        return false;" title="Imbas Dokumen">
                                        /
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                             onclick="doViewReport('${line1.dokumen.idDokumen}');" onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen">
                                        <s:hidden name="idDokumen" value="${line1.dokumen.idDokumen}"/>
                                    </c:if>
                                </div>
                            </display:column>
                            <display:column title="Kemaskini">
                                <div align="center">
                                    <img alt='Klik Untuk Tambah' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onclick="kemaskiniPembida('${line1.idPembida}', '${actionBean.permohonan.idPermohonan}')" onmouseover="this.style.cursor = 'pointer';">
                                </div>
                            </display:column>
                            <display:column title="Wakil Pembida">
                                <div align="center">
                                    <img alt='Klik Untuk Tambah' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onclick="tambahWakilPembida('${line1.idPembida}', '${actionBean.permohonan.idPermohonan}')" onmouseover="this.style.cursor = 'pointer';">

                                </div>
                            </display:column>
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Padam' border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onclick="removePembida('${line1.idPembida}', '${actionBean.permohonan.idPermohonan}');" style="cursor:hand">
                                </div>
                            </display:column>


                        </display:table>
                        <br>
                        <%--<c:if test="${fn:length(actionBean.list)>0}">--%>
                        <p>
                            <s:button name="" value="Tambah Nama Pembida" class="longbtn" disabled="disabled" onclick="tambah('${actionBean.permohonan.idPermohonan}')"  />
                            <%-- <c:if test="${fn:length(actionBean.list)>0}">
                                 <s:submit name="simpanEditPembida" id="simpanlee" value="Kemaskini Status" class="btn"/>
                             </p>
                         </c:if>--%>

                    </div>
                </fieldset>
            </div>
        </c:if>
        <c:if test="${actionBean.enkuiri.caraLelong eq 'A'}">
            <div class="subtitle">
                <fieldset class="aras1">

                    <legend>
                        Senarai Pembida untuk Permohonan :  ${actionBean.permohonan.idPermohonan} 
                    </legend> 
                    <div class="content" align="center"> 
                        <c:if test="${actionBean.permohonan.permohonanSebelum.idPermohonan ne null}">
                            <p>Permohonan Sebelum:  ${actionBean.permohonan.permohonanSebelum.idPermohonan}</p> 
                        </c:if>
                        <display:table name="${actionBean.list}" id="line1" class="tablecloth" requestURI="/lelong/senaraipembidaJLB">
                            <display:column title="Bil">
                                ${line1_rowNum}
                                <s:hidden name="" class="x${line1_rowNum -1}" value="${line.idLelong}"/>
                            </display:column>
                            <display:column property="pihak.nama"  class="rowCount" title="Nama" style="text-transform:uppercase;"/>
                            <%--<a href="#" title="klik untuk paparan" onclick="editStatus('${line1.pihak.idPihak}','${line1.idPembida}','${line1.lelong.idLelong}');">${line1.pihak.nama}</a></display:column>--%>
                            <display:column property="pihak.noPengenalan" title="No. Pengenalan" style="text-transform:uppercase;" />
                            <display:column property="noRujukan" title="No Bank Draf" style="text-transform:uppercase;" />
                            <display:column property="pihak.noTelefonBimbit" title="No Telefon" style="text-transform:uppercase;" />
                            <display:column property="hakmilikPermohonan.hakmilik.idHakmilik"  title="ID Hakmilik" style="text-transform:uppercase;" />
                            <display:column title="Status" class="${line1_rowNum}" style="text-transform:uppercase;">
                                <s:hidden name="idPembida${line1_rowNum -1}" value="${line1.idPembida}"/>
                                <s:select name="kodSts${line1_rowNum -1}" id="" value="${line1.kodStsPembida.kod}" style="width:200px;" onchange="updateStatus('${actionBean.permohonan.idPermohonan}','${line1.idPembida}','${line1.lelong.idLelong}','${line1.hakmilikPermohonan.hakmilik.idHakmilik}',this.value)">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodStsPembida}" label="nama" value="kod" />
                                </s:select>
                                <c:if test="${line1.kodStsPembida.kod eq 'SH'}">
                                    <div align="right">
                                        <a id="" onclick="popupAlamat('${line1.idPembida}', '${actionBean.permohonan.idPermohonan}');
        return false;" onmouseover="this.style.cursor = 'pointer';">
                                            <img alt="Sila Klik Untuk Masukkan Tarikh Mula dan Tarikh Tamat" src='${pageContext.request.contextPath}/pub/images/edit.gif' />

                                        </a>
                                    </div>
                                </c:if>
                            </display:column> 

                            <display:column title="Tindakan">
                                <div align="center">
                                    <c:if test="${line1.dokumen.namaFizikal == null}">
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                             onmouseover="this.style.cursor = 'pointer';" onclick="muatNaikForm('${line1.idPembida}', '${line1_rowNum-1}', '${actionBean.enkuiri.caraLelong}');
        return false;" title="Muat Naik Dokumen">
                                        /
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                             onmouseover="this.style.cursor = 'pointer';" onclick="scan('${line1.idPembida}', '${line1_rowNum-1}');
        return false;" title="Imbas Dokumen">
                                        <div id="viewReport${line1_rowNum-1}">
                                            /<img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                                  onclick="doViewReport2('${line1.idPembida}');" onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen"></div>
                                        </c:if>
                                        <c:if test="${line1.dokumen.namaFizikal != null}">
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                             onmouseover="this.style.cursor = 'pointer';" onclick="muatNaikForm('${line1.idPembida}', '${line1_rowNum-1}', '${actionBean.enkuiri.caraLelong}');
        return false;" title="Muat Naik Dokumen">
                                        /
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                             onmouseover="this.style.cursor = 'pointer';" onclick="scan('${line1.idPembida}', '${line1_rowNum-1}');
        return false;" title="Imbas Dokumen">
                                        /
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                             onclick="doViewReport('${line1.dokumen.idDokumen}');" onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen">
                                        <s:hidden name="idDokumen" value="${line1.dokumen.idDokumen}"/>
                                    </c:if>
                                </div>
                            </display:column>
                            <display:column title="Kemaskini">
                                <div align="center">
                                    <img alt='Klik Untuk Tambah' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onclick="kemaskiniPembida('${line1.idPembida}', '${actionBean.permohonan.idPermohonan}')" onmouseover="this.style.cursor = 'pointer';">
                                </div>
                            </display:column>
                            <display:column title="Wakil Pembida">
                                <div align="center">
                                    <img alt='Klik Untuk Tambah' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onclick="tambahWakilPembida('${line1.idPembida}', '${actionBean.permohonan.idPermohonan}')" onmouseover="this.style.cursor = 'pointer';">

                                </div>
                            </display:column>
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Padam' border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onclick="removePembida('${line1.idPembida}', '${actionBean.permohonan.idPermohonan}');" style="cursor:hand">
                                </div>
                            </display:column>

                        </display:table>
                        <br>
                        <%--<c:if test="${fn:length(actionBean.list)>0}">--%>
                        <p>
                            <s:button name="" value="Tambah Nama Pembida" class="longbtn" onclick="tambah('${actionBean.permohonan.idPermohonan}')"  />
                            <%-- <c:if test="${fn:length(actionBean.list)>0}">
                                 <s:submit name="simpanEditPembida" id="simpanlee" value="Kemaskini Status" class="btn"/>
                             </p>
                         </c:if>--%>

                    </div>
                </fieldset>
            </div>
        </c:if>

        <%--untuk 2 hakmilik bersama--%>
        <c:if test="${actionBean.enkuiri.caraLelong eq 'B'}">
            <div class="subtitle">
                <fieldset class="aras1">

                    <legend>
                        Senarai Pembida untuk Permohonan :  ${actionBean.permohonan.idPermohonan} 
                    </legend>


                    <div class="content" align="center">
                        <c:if test="${actionBean.permohonan.permohonanSebelum.idPermohonan ne null}">

                            <p>Permohonan Sebelum:  ${actionBean.permohonan.permohonanSebelum.idPermohonan} </p>

                        </c:if>
                        <display:table name="${actionBean.list}" id="line1" class="tablecloth" requestURI="/lelong/senaraipembidaJLB">
                            <display:column title="Bil">
                                ${line1_rowNum}
                                <s:hidden name="" class="x${line1_rowNum -1}" value="${line.idLelong}"/>
                            </display:column>
                            <display:column property="pihak.nama"  class="rowCount" title="Nama" style="text-transform:uppercase;"/>
                            <%--<a href="#" title="klik untuk paparan" onclick="editStatus('${line1.pihak.idPihak}','${line1.idPembida}','${line1.lelong.idLelong}');">${line1.pihak.nama}</a></display:column>--%>
                            <display:column property="pihak.noPengenalan" title="No. Pengenalan" style="text-transform:uppercase;" />
                            <display:column property="noRujukan" title="No Bank Draf" style="text-transform:uppercase;" />
                            <display:column property="pihak.noTelefonBimbit" title="No Telefon" style="text-transform:uppercase;" />
                            <display:column title="ID Hakmilik" >${actionBean.idHakmilik}  </display:column>
                            <display:column title="Status" class="${line1_rowNum}" style="text-transform:uppercase;">
                                <s:hidden name="idPembida${line1_rowNum -1}" value="${line1.idPembida}"/>
                                <s:select name="kodSts${line1_rowNum -1}" id="" value="${line1.kodStsPembida.kod}" style="width:200px;" onchange="updateStatusBersama('${actionBean.permohonan.idPermohonan}','${line1.pihak.idPihak}','${line1.lelong.idLelong}',this.value)">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodStsPembida}" label="nama" value="kod" />
                                </s:select>
                                <c:if test="${line1.kodStsPembida.kod eq 'SH'}">
                                    <div align="right">
                                        <a id="" onclick="popupAlamat('${line1.idPembida}', '${actionBean.permohonan.idPermohonan}');
        return false;" onmouseover="this.style.cursor = 'pointer';">
                                            <img alt="Sila Klik Untuk Masukkan Tarikh Mula dan Tarikh Tamat" src='${pageContext.request.contextPath}/pub/images/edit.gif' />

                                        </a>
                                    </div>
                                </c:if>
                            </display:column>
                            <display:column title="Tindakan">
                                <div align="center">
                                    <c:if test="${line1.dokumen.namaFizikal == null}">
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                             onmouseover="this.style.cursor = 'pointer';" onclick="muatNaikForm('${line1.idPembida}', '${line1_rowNum-1}', '${actionBean.enkuiri.caraLelong}');
        return false;" title="Muat Naik Dokumen">
                                        /
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                             onmouseover="this.style.cursor = 'pointer';" onclick="scan('${line1.idPembida}', '${line1_rowNum-1}');
        return false;" title="Imbas Dokumen">
                                        <div id="viewReport${line1_rowNum-1}">
                                            /<img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                                  onclick="doViewReport2('${line1.idPembida}');" onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen"></div>
                                        </c:if>
                                        <c:if test="${line1.dokumen.namaFizikal != null}">
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                             onmouseover="this.style.cursor = 'pointer';" onclick="muatNaikForm('${line1.idPembida}', '${line1_rowNum-1}', '${actionBean.enkuiri.caraLelong}');
        return false;" title="Muat Naik Dokumen">
                                        /
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                             onmouseover="this.style.cursor = 'pointer';" onclick="scan('${line1.idPembida}', '${line1_rowNum-1}');
        return false;" title="Imbas Dokumen">
                                        /
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                             onclick="doViewReport('${line1.dokumen.idDokumen}');" onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen">
                                        <s:hidden name="idDokumen" value="${line1.dokumen.idDokumen}"/>
                                    </c:if>
                                </div>
                            </display:column>
                            <display:column title="Kemaskini">
                                <div align="center">
                                    <img alt='Klik Untuk Tambah' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onclick="kemaskiniPembida('${line1.idPembida}', '${actionBean.permohonan.idPermohonan}')" onmouseover="this.style.cursor = 'pointer';">
                                </div>
                            </display:column>
                            <display:column title="Wakil Pembida">
                                <div align="center">
                                    <img alt='Klik Untuk Tambah' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onclick="tambahWakilPembida('${line1.idPembida}', '${actionBean.permohonan.idPermohonan}')" onmouseover="this.style.cursor = 'pointer';">

                                </div>
                            </display:column>
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Padam' border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onclick="removePembida('${line1.idPembida}', '${actionBean.permohonan.idPermohonan}');" style="cursor:hand">
                                </div>
                            </display:column>
                        </display:table>
                        <br>
                        <%--<c:if test="${fn:length(actionBean.list)>0}">--%>
                        <p>
                            <s:button name="" value="Tambah Nama Pembida" class="longbtn" onclick="tambah('${actionBean.permohonan.idPermohonan}')"  />
                            <%-- <c:if test="${fn:length(actionBean.list)>0}">
                                 <s:submit name="simpanEditPembida" id="simpanlee" value="Kemaskini Status" class="btn"/>
                             </p>
                         </c:if>--%>

                    </div>
                </fieldset>
            </div>
        </c:if>

    </fieldset>
</c:if>

</s:form>

