<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">

    $(document).ready(function() {
        $('form').submit(function() {
            doBlockUI();
        });
    });
    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function jana(idMohon, kodDokumen) {
        alert(idMohon);
        alert(kodDokumen);
        doBlockUI();
        frm = document.form1;
        var url = '${pageContext.request.contextPath}/consent/cetak_semula?generateReport&idMohon=' + idMohon + '&kodDokumen=' + kodDokumen;
        frm.action = url;
        frm.submit();
    }

    function janaReport(idPermohonan) {
        doBlockUI();
        frm = document.form1;
        var url = '${pageContext.request.contextPath}/consent/cetak_semula?generateReports&idPermohonan=' + idPermohonan;
        frm.action = url;
        frm.submit();
    }

    function resetValue() {
        alert("hehe");
        $('#idMohon').val('');
    }

    function dodacheck(val) {
        //var mikExp = /[1\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\1\|]/;
        var v = $('#jenisKp').val();
        if (v == 'B') {
            var strPass = val;
            var strLength = strPass.length;
            //$('#kp').attr("maxlength","12");
            if (strLength > 12) {
                var tst = val.substring(0, (strLength) - 1);
                $('#noKp').val(tst);
            }
            var lchar = val.charAt((strLength) - 1);
            if (isNaN(lchar)) {
                //return false;
                var tst = val.substring(0, (strLength) - 1);
                $('#noKp').val(tst);
            }
        }
    }

    function doBlockUI() {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });
    }

    //    function popupParam(f){
    //        var form = $(f).formSerialize();
    //        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+form, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    //    }

    function popupParam(idMohon, kodUrusan) {
        var reportName;
        var kodNegeri = '${actionBean.kodNegeri}';

        if (kodNegeri === '05') {
            if (kodUrusan === 'PMPTD') {
                reportName = 'CONS_Surat_Keb_PMT_PTD_NS.rdf';
            }
            else if (kodUrusan === 'MCPTD') {
                reportName = 'CONS_Surat_Keb_GD_PTD_NS.rdf';
            }
            else {
                reportName = 'CONS_Surat_Keb_PMTGD_PTD_NS.rdf';
            }
        }
        else {
            reportName = 'CONS_SRT_KEB_PMT_MLK_PTG.rdf';
        }
        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?" + '&reportName=' + reportName + '&report_p_id_mohon=' + idMohon, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }



</script>

<img alt="" id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.consent.CetakSemulaActionBean" id="cetak_semula" name="form1">
    <s:messages/>
    <s:errors/>
    <div class="subtitle" id="dokumen">

        <fieldset class="aras1">
            <legend>Cetak Semula</legend>
            <p>
                <label for="Jenis Pengenalan"><em>*</em>Jenis Pengenalan Pemohon :</label>
                <s:select name="pihak.jenisPengenalan.kod" id="jenisKp">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                </s:select>
            </p>

            <p>
                <label for="No Pengenalan"><em>*</em>Nombor Pengenalan Pemohon :</label>
                <s:text name="pihak.noPengenalan" id="noKp" size="40" onkeyup="this.value=this.value.toUpperCase();"
                        onblur="doUpperCase(this.id);"/>
            </p>
            <p>
                <label>&nbsp;</label>
                atau
            </p>

            <p>
                <label><em>*</em>ID Permohonan :</label>
                <s:text name="permohonan.idPermohonan" size="40" maxlength="20" id="idMohon" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:submit name="search" value="Cari" class="btn" onclick=""/>
                <s:submit name="reset" value="Isi Semula" class="btn" onclick=""/>&nbsp;
            </p>

            <br/>
            <c:if test="${ fn:length(actionBean.senaraiPermohonan) > 0}">
                <p align="center">
                    <display:table name="${actionBean.senaraiPermohonan}" class="tablecloth" id="row" style="width:95%">
                        <display:column title="Bil" sortable="true">${row_rowNum}</display:column>
                        <display:column title="ID Permohonan" property="idPermohonan"/>
                        <c:if test="${row.infoAudit.dimasukOleh.idPengguna eq 'ECON'}">
                            <display:column title="Catatan">
                                DIMIGRASI DARI SISTEM ECONSENT
                            </display:column>
                            <display:column title="Papar" style="text-align: center">
                                <s:button name="" value="Papar" class="btn" onclick="popupParam('${row.idPermohonan}','${row.kodUrusan.kod}')"/>
                            </display:column>
                        </c:if>

                        <c:if test="${row.infoAudit.dimasukOleh.idPengguna ne 'ECON'}">
                            <display:column title="Nama Dokumen">
                                <c:forEach items="${row.folderDokumen.senaraiKandungan}" var="kandunganFolder">
                                    <c:if test="${kandunganFolder.dokumen.kodDokumen.kod eq 'SK' ||
                                                  kandunganFolder.dokumen.kodDokumen.kod eq 'SK2'||
                                                  kandunganFolder.dokumen.kodDokumen.kod eq 'SJK'||
                                                  kandunganFolder.dokumen.kodDokumen.kod eq 'PBI'}">
                                        <c:out value="${kandunganFolder.dokumen.kodDokumen.nama}"/><br/><br/>
                                    </c:if>
                                </c:forEach>
                            </display:column>
                            <display:column title="Dijana Oleh">

                                <c:forEach items="${row.folderDokumen.senaraiKandungan}" var="kandunganFolder">
                                    <c:if test="${kandunganFolder.dokumen.kodDokumen.kod eq 'SK' ||
                                                  kandunganFolder.dokumen.kodDokumen.kod eq 'SK2'||
                                                  kandunganFolder.dokumen.kodDokumen.kod eq 'SJK'||
                                                  kandunganFolder.dokumen.kodDokumen.kod eq 'PBI'}">
                                        <c:out value="${kandunganFolder.dokumen.infoAudit.dimasukOleh.nama}"/><br/><br/>
                                    </c:if>
                                </c:forEach>
                            </display:column>
                            <display:column title="Catatan">
                                <c:forEach items="${row.folderDokumen.senaraiKandungan}" var="kandunganFolder">
                                    <c:if test="${kandunganFolder.dokumen.kodDokumen.kod eq 'SK' ||
                                                  kandunganFolder.dokumen.kodDokumen.kod eq 'SK2'||
                                                  kandunganFolder.dokumen.kodDokumen.kod eq 'SJK'||
                                                  kandunganFolder.dokumen.kodDokumen.kod eq 'PBI'}">
                                        <c:if test="${kandunganFolder.catatan eq null}">
                                            Tiada
                                        </c:if>
                                        <c:if test="${kandunganFolder.catatan ne null}">
                                            kandunganFolder.catatan
                                        </c:if>
                                        <br/><br/>
                                    </c:if>
                                </c:forEach>
                            </display:column>
                            <display:column title="Tarikh">
                                <c:forEach items="${row.folderDokumen.senaraiKandungan}" var="kandunganFolder">
                                    <c:if test="${kandunganFolder.dokumen.kodDokumen.kod eq 'SK' ||
                                                  kandunganFolder.dokumen.kodDokumen.kod eq 'SK2'||
                                                  kandunganFolder.dokumen.kodDokumen.kod eq 'SJK'||
                                                  kandunganFolder.dokumen.kodDokumen.kod eq 'PBI'}">
                                        <fmt:formatDate pattern="dd/MM/yyyy hh:mm:ss" value="${kandunganFolder.dokumen.infoAudit.tarikhMasuk}"/><br/><br/>
                                    </c:if>
                                </c:forEach>
                            </display:column>
                            <display:column title="Jana">
                                <s:submit name="" value="Jana" class="btn"  onclick="janaReport('${row.idPermohonan}');"/><br/>
                            </display:column>

                        </c:if>
                    </display:table>
                    <br/>
                </p>
            </c:if>
            <c:if test="${ fn:length(actionBean.senaraiKandungan) > 0}">
                <br/>
                <p align="center">
                    <display:table name="${actionBean.senaraiKandungan}" class="tablecloth" id="row" style="width:95%">
                        <display:column title="Bil" sortable="true">${row_rowNum}</display:column>
                        <display:column title="Nama Dokumen" property="dokumen.kodDokumen.nama" />
                        <display:column title="Dijana Oleh" property="dokumen.infoAudit.dimasukOleh.nama"/>
                        <display:column title="Catatan">
                            <c:if test="${row.catatan eq null}">
                                Tiada
                            </c:if>
                            <c:if test="${row.catatan ne null}">
                                row.catatan
                            </c:if>
                        </display:column>
                        <display:column title="Tarikh" property="dokumen.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                    </display:table>
                </p>

                <p align="center">
                    <s:submit name="generateReports" value="Jana" class="btn" onclick=""/>&nbsp;
                </p>
                <br/>
            </c:if>
            <c:if test="${ fn:length(actionBean.senaraiKandunganGenarated) > 0}">
                <br/>
                <p align="center">
                    <display:table name="${actionBean.senaraiKandunganGenarated}" class="tablecloth" id="row" style="width:95%">
                        <display:column title="Bil" sortable="true">${row_rowNum}</display:column>
                        <display:column title="Nama Dokumen" property="dokumen.kodDokumen.nama" />
                        <display:column title="Dijana Oleh" property="dokumen.infoAudit.dimasukOleh.nama"/>
                        <display:column title="Catatan">
                            <c:if test="${row.catatan eq null}">
                                Tiada
                            </c:if>
                            <c:if test="${row.catatan ne null}">
                                row.catatan
                            </c:if>
                        </display:column>
                        <display:column title="Tarikh" property="dokumen.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                        <display:column title="Papar">
                            <c:if test="${row.dokumen.namaFizikal != null}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReport('${row.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor = 'pointer';" title="Papar ${row.dokumen.kodDokumen.nama}"/>
                            </c:if>
                        </display:column>
                    </display:table>
                </p>
                <br/>
            </c:if>
            <%--    
                ${actionBean.permohonan.idPermohonan}
                <s:hidden name="reportName" id="reportname" value="CONS_SRT_KEB_PMT_MLK_PTG.rdf"/>
            <%-- <s:hidden name="report_p_id_mohon" id="report_p_id_mohon" value="${actionBean.permohonan.idPermohonan}"/>--%>
        </fieldset>
    </div>
</s:form>


