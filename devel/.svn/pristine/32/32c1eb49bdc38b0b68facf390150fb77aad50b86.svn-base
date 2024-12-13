<%-- 
    Document   : semak_dokumen
    Created on : Sep 1, 2015, 3:28:24 PM
    Author     : nurul.hazirah
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    $(document).ready(function() {
        $('form').submit(function() {
            doBlockUI();
        });

    var idmhn = $('#idMohon').val();
    var kodUrus = $('#kodU').val();
        <%--alert('idmhn - '+ idmhn);
        alert('kodUrus - '+ kodUrus);--%>
    });


    function noenter(e) {
        e = e || window.event;
        var key = e.keyCode || e.charCode;
        return key !== 13;
    }

    function clearForm() {
        $("#idMohon").val('');
    }

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
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

    function moduledet(val){
        $.get('${pageContext.request.contextPath}/pelupusan/semak_dokumen?findKod&kod=' + val,
        function(data){
            $("#kodu").replaceWith($('#kodu', $(data)));
        }, 'html');
    }

    function ajaxLink(link, update) {
        $.get(link, function(data){
            $(update).replaceWith($(update, $(data)));
            $(update).show();
            $('#list').hide();
        }, 'html');

    var kodurusan = $('kodu').val('${actionBean.kodurus}');
        alert('kodurusan - '+ kodurusan);

        alert(link);
        alert(update);
        return false;
    }
</script>

<img alt="" id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form beanclass="etanah.view.stripes.pelupusan.utility.SemakanDokumenActionBean" id="semak_dokumen">
    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Semakan Dokumen</legend>
            <br/>
            <p>
                <label> ID Permohonan :</label>
                <s:text name="permohonan.idPermohonan" size="50" maxlength="20" id="idMohon" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> Modul :</label>
                <s:select name="jabatan" id="jabatan" style="width:250px;" onchange="moduledet(this.value);">
                    <s:option  value="">Sila Pilih</s:option>
                    <s:options-collection collection="${actionBean.kodjab}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
                <label> Urusan :</label>
                <s:select name="kodurus" id="kodu"  style="width:40%;" value="kod">
                    <s:option  value="">Sila Pilih</s:option>
                    <c:forEach items="${actionBean.ku}" var="i" >
                        <s:option value="${i.kod}" >${i.kod} - ${i.nama}</s:option>
                    </c:forEach>
                </s:select>
            </p>
            <br/>
            <c:if test="${fn:length(actionBean.permohonanList) == 0}">
                <p align="center">
                    <s:submit name="search" value="Cari" class="btn" onclick=""/>
                    <%--<s:submit name="reset" value="Carian Semula" class="btn" onclick=""/>--%>
                </p>
            </c:if>
        </fieldset>
    </div>
    <div id="list">
        <c:if test="${permohonan}">
            <c:if test="${fn:length(actionBean.permohonanList) > 0}">
                <font color="red" size="2"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sila klik pada ID Permohonan untuk paparan terperinci permohonan.</b></font>
                <div align="center">
                    <display:table name="${actionBean.permohonanList}" class="tablecloth" id="line" style="width:90%" pagesize="20" requestURI="${pageContext.request.contextPath}/pelupusan/semak_dokumen">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="ID Permohonan" sortable="true">
                            <s:link beanclass="etanah.view.stripes.pelupusan.utility.SemakanDokumenActionBean"
                                    event="viewStatus" onclick="return ajaxLink(this, '#view_Status');" >
                                <s:param name="idPermohonan" value="${line.idPermohonan}"/>${line.idPermohonan}
                                <s:param name="kodU" value="${line.kodUrusan.kod}"/></s:link>
                        </display:column>
                        <display:column title="Nama Permohonan" property="kodUrusan.nama" style="text-transform:uppercase;" sortable="true"/>
                        <display:column title="Tarikh Permohonan" property="infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" sortable="true"/>
                        <%--<c:choose>--%>
                        <c:if test = "${line.keputusan.kod ne 'TK'}">
                            <c:if test="${line.status eq 'TK' || line.status eq 'TA'}">
                                <c:if test="${line.kodUrusan.kod eq 'RTLK'}">
                                    <c:if test="${line.status eq 'TA'}">
                                        <display:column title="Status"  value="SEDANG DIPROSES" sortable="true"/>
                                    </c:if>
                                    <c:if test="${line.status eq 'TK'}">
                                        <display:column title="Status"  value="BATAL" sortable="true"/>
                                    </c:if>
                                </c:if>
                                <c:if test="${!(line.kodUrusan.kod eq 'RTLK')}">
                                    <display:column title="Status"  value="SEDANG DIPROSES" sortable="true"/>
                                </c:if>
                            </c:if>
                            <c:if test="${line.status eq 'SL'}">
                                <display:column title="Status"  value="SELESAI" sortable="true"/>
                            </c:if>
                            <c:if test="${!(line.status eq 'SL') && !(line.status eq 'TK') && !(line.status eq 'TA')}">
                                <display:column title="Status"  value="SEDANG DIPROSES" sortable="true"/>
                            </c:if>
                        </c:if>

                        <!--differentiate permohonan batal-->
                        <c:if test = "${line.keputusan.kod eq 'TK'}">
                            <display:column title="Status"  value="BATAL" sortable="true"/>
                        </c:if>
                        <%--</c:choose>--%>
                    </display:table>
                </div>
                <br/>
                <p align="center">
                    <s:submit name="reset" value="Carian Semula" class="btn" onclick=""/>
                </p>
                <br/>
            </c:if>
        </c:if>
    </div>
    <div id="view_Status">
        <c:if test="${dokumen}">
            <c:if test="${fn:length(actionBean.permohonan.folderDokumen.senaraiKandungan) > 0}">
                <fieldset class="aras1" id="row1">
                    <c:set var="rowNum" value="0"/><p align="center">
                        <display:table name="${actionBean.permohonan.folderDokumen.senaraiKandungan}" class="tablecloth" id="row" style="width:80%">
                            <display:column title="No" class="noUrusan">
                                ${row_rowNum}
                            </display:column>
                            <display:column title="Kod Dokumen">
                            <div id="t" title="${row.dokumen.kodDokumen.nama}">${row.dokumen.kodDokumen.kod}</div>
                        </display:column>
                        <display:column title="Dokumen">
                            <c:if test="${row.dokumen.kodDokumen.kod eq '0L'}">
                                <div id="${row_rowNum-1}" class="editable">${row.catatan}</div>
                                <s:hidden name="x" id="old_${row_rowNum-1}" value="${row.catatan}"/>
                            </c:if>
                            <c:if test="${row.dokumen.kodDokumen.kod ne '0L'}">
                                <div id="${row_rowNum-1}" class="editable">${row.dokumen.tajuk}</div>
                                <s:hidden name="x" id="old_${row_rowNum-1}" value="${row.dokumen.tajuk}"/>
                            </c:if>
                            <br/>  <c:if test="${row.dokumen.noRujukan ne '' && row.dokumen.noRujukan ne null}">(No rujukan : ${row.dokumen.noRujukan})</c:if>
                        </display:column>
                        <display:column title="Tarikh" property="dokumen.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                        <display:column title="Papar">
                            <p align="center">
                                <c:if test="${row.dokumen.namaFizikal != null}">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                         onclick="doViewReport('${row.dokumen.idDokumen}');
                                             return false;" height="30" width="30" alt="papar"
                                         onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen ${row.dokumen.kodDokumen.nama}"/>
                                    <c:if test="${row.dokumen.baru eq 'Y' || row.dokumen.baru eq ''}">
                                        <img src="${pageContext.request.contextPath}/pub/images/baharu.gif" alt="baru"/>
                                    </c:if>
                                </c:if>
                            </p>
                        </display:column>
                    </display:table>
                    <br/>
                </fieldset>
            </c:if>
        </c:if>
    </div>
    <br/>
</s:form>
