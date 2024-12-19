
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.tools.min.js?select=full&debug=true"></script>
<script type="text/javascript">

    $(document).ready(function(){
        $('input:text').each(function(){
            $(this).focus(function(){
                $(this).addClass('focus');
            });
            $(this).blur(function(){
                $(this).removeClass('focus');
            });
        });
    });

    function validate(){
        var id = $('#idPermohonan').val();
        if (id == '') {
            alert('Sila masukan id permohonan.');
            $('#idPermohonan').focus();
            return false;
        }
        return true;
    }

    function scan(dokumenId, idPermohonan) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/dokumen/scan/' + dokumenId + '?idPermohonan=' + idPermohonan+ '&prm=1';
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=850,height=600");
    }

    function doEditDoc(v, idPermohonan) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v + '?showEditForm&prm=1&idPermohonan='
            + idPermohonan + '&prm=1';
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=400,scrollbars=yes");
    }

    function doViewLog(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v + '?viewLogDocument&prm=1';
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }

    function muatNaikForm(folderId, dokumenId, idPermohonan) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/upload?muatNaikForm&prm=1&folderId=' + folderId + '&dokumenId='
            + dokumenId + '&idPermohonan=' + idPermohonan;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }


</script>
<style type="text/css">
    .tooltip {
        display:none;
        background:transparent url(${pageContext.request.contextPath}/pub/images/black_arrow.png);
        font-size:12px;
        height:70px;
        width:160px;
        padding:25px;
        color:#fff;
    }

</style>
<s:messages/>
<s:errors/>
<s:messages/>
<div id="folder_div">
    <s:form beanclass="etanah.view.utility.DocumentUtilityAction" name="form1">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Carian Dokumen
                </legend>
                <p>
                    <label>ID Permohonan :</label>
                    <s:text name="idPermohonan" id="idPermohonan" onblur="toUppercase(this.id);"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:submit name="searchPermohonan" value="Cari" class="btn" onclick="return validate();"/>&nbsp;
                    <s:submit name="showForm" value="Isi Semula" class="btn"/>
                </p>
                <br/>
            </fieldset>
        </div>
        <br/>
        <c:if test="${!empty actionBean.permohonan}">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        ${actionBean.permohonan.kodUrusan.kod} - ${actionBean.permohonan.kodUrusan.nama} ( ${actionBean.permohonan.idPermohonan} )
                    </legend>
                    <br/>
                    <font size="2" color="black">Petunjuk :</font>
                    <p>
                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                             width="20" height="20" /> - <font size="2" color="black">Papar Dokumen</font>
                        &nbsp;<b>|</b>&nbsp;
                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                             width="20" height="20" /> - <font size="2" color="black">Cetak Dokumen</font>
                        &nbsp;<b>|</b>&nbsp;
                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                             width="20" height="20" /> - <font size="2" color="black">Muat Naik Dokumen</font>
                        &nbsp;<b>|</b>&nbsp;
                        <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png"
                             width="20" height="20" /> - <font size="2" color="black">Imbas Dokumen</font>
                        &nbsp;<b>|</b>&nbsp;
                        <img src="${pageContext.request.contextPath}/pub/images/icons/edit.png"
                             width="20" height="20" /> - <font size="2" color="black">Kemaskini Dokumen</font>
                    </p>
                    <%--  <p>
                          <label>ID Permohonan :</label> &nbsp; ${actionBean.permohonan.idPermohonan} &nbsp;
                      </p>
                      <p>
                          <label>Urusan :</label> &nbsp; ${actionBean.permohonan.kodUrusan.kod} - ${actionBean.permohonan.kodUrusan.nama}&nbsp;
                      </p>--%>
                    <p align="center">
                        <label>&nbsp;</label>&nbsp;
                        <display:table class="tablecloth" name="${actionBean.permohonan.folderDokumen.senaraiKandungan}"
                                       cellpadding="0" cellspacing="0" id="row">
                            <display:column title="Bil.">${row_rowNum}</display:column>
                            <display:column title="Kod Dokumen" group="1">
                            <div id="t" title="${row.dokumen.kodDokumen.nama}">${row.dokumen.kodDokumen.kod}</div>
                        </display:column>
                        <display:column title="Tajuk /<br/> No Rujukan"  >
                            <c:if test="${row.dokumen.kodDokumen.kod eq '0L'}">
                                <div id="${row_rowNum-1}" class="editable">${row.catatan}</div>
                                <s:hidden name="x" id="old_${row_rowNum-1}" value="${row.catatan}"/>
                            </c:if>
                            <c:if test="${row.dokumen.kodDokumen.kod ne '0L'}">
                                <div id="${row_rowNum-1}" class="editable">${row.dokumen.tajuk}</div>
                                <s:hidden name="x" id="old_${row_rowNum-1}" value="${row.dokumen.tajuk}"/>
                            </c:if>
                            <c:if test="${row.dokumen.noRujukan ne '' && row.dokumen.noRujukan ne null}">
                                <br/>
                                (No rujukan : <a href="#"
                                                 onclick="window.open('${pageContext.request.contextPath}/daftar/pertanyaanSBSWSA?paparOnly&idMohon=${row.dokumen.noRujukan}','popup','width=1000,height=600,scrollbars=yes,resizable=yes,toolbar=no,directories=no,location=no,menubar=no,status=no,left=0,top=0'); ">
                                    ${row.dokumen.noRujukan})</a>
                                </c:if>
                            </display:column>
                            <display:column title="Klas." property="dokumen.klasifikasi.nama" />
                            <display:column title="Dimasuk Oleh" property="dokumen.infoAudit.dimasukOleh.nama" />
                            <display:column title="Tarikh" property="dokumen.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}"/>
                            <display:column title="Tindakan">
                            <p align="center">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/edit.png"
                                     onclick="doEditDoc('${row.dokumen.idDokumen}','${actionBean.permohonan.idPermohonan}');" height="30" width="30" alt="edit"
                                     onmouseover="this.style.cursor='pointer';" title="Kemaskini Dokumen ${row.dokumen.kodDokumen.nama}"/> <b>|</b>
                                <img src="${pageContext.request.contextPath}/pub/images/icons/Logging-icon.png"
                                     onclick="doViewLog('${row.dokumen.idDokumen}');" height="30" width="30" alt="Log"
                                     onmouseover="this.style.cursor='pointer';" title="Log untuk Dokumen ${row.dokumen.kodDokumen.nama}"/> <b>|</b>
                                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                     onclick="muatNaikForm('${row.folder.folderId}','${row.dokumen.idDokumen}'
                                         ,'${actionBean.permohonan.idPermohonan}');return false;" height="30" width="30" alt="Muat Naik"
                                     onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen ${row.dokumen.kodDokumen.nama}"/> <b>|</b>
                                <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png"
                                     onclick="scan('${row.dokumen.idDokumen}','${actionBean.permohonan.idPermohonan}');return false;" height="30" width="30" alt="Imbas"
                                     onmouseover="this.style.cursor='pointer';" title="Imbas untuk Dokumen ${row.dokumen.kodDokumen.nama}"/> <b>|</b>
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
                    </display:table>
                    </p>
                </fieldset>
            </div>
            <br/>
        </c:if>
    </s:form>
</div>