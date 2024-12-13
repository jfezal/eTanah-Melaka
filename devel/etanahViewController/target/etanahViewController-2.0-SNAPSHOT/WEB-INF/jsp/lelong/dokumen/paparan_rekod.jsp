<%-- 
    Document   : paparan_rekod
    Created on : May 15, 2013, 8:49:36 AM
    Author     : nur.aqilah
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    function doViewReport(d) {
        window.open("${pageContext.request.contextPath}/lelong/dokumen/view?view&idDokumen=" + d, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }
    function doViewReport2(v) {
        var url = '${pageContext.request.contextPath}/lelong/dokumen/folder?view&idNotis='+v;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    
    
</script>

<s:form action="/lelong/dokumen/folder" beanclass="etanah.view.lelong.dokumen.FolderAction">

    <div class="subtitle">
        <br/><br/>
        <fieldset class="aras1">
            <legend>
                Maklumat Kehadiran
            </legend>

            <br/>
            <display:table class="tablecloth" name="${actionBean.senaraiKehadiran}" id="line" defaultorder="ascending">                
                <display:column title="Bil">${line_rowNum}</display:column>
                <display:column title="Nama" class="nama" style="text-transform:uppercase;">
                    <c:if test="${line.idPenyerah eq null}">
                        ${line.hakmilikPihakBerkepentingan.pihak.nama}
                    </c:if>
                    <c:if test="${line.idPenyerah ne null}">
                        ${actionBean.peguam.nama}
                    </c:if>
                </display:column>
                <display:column title="Status" class="${line_rowNum}" style="text-transform:uppercase;">
                    <c:if test="${line.idPenyerah eq null}">
                        <c:if test="${line.permohonanPihak ne  null}">
                            <c:if test="${line.hakmilikPihakBerkepentingan.jenis.kod ne 'PG'}">
                                Penggadai
                            </c:if>
                            <c:if test="${line.hakmilikPihakBerkepentingan.jenis.kod eq 'PG'}">
                                ${line.hakmilikPihakBerkepentingan.jenis.nama}
                            </c:if>
                        </c:if>
                        <c:if test="${line.permohonanPihak eq null}">
                            ${line.hakmilikPihakBerkepentingan.jenis.nama}
                        </c:if>
                    </c:if>
                    <c:if test="${line.idPenyerah ne null}">
                        Peguam
                    </c:if>
                </display:column>

                <display:column title="Kehadiran" class="${line_rowNum}">
                    ${line.hadir eq 'Y' ? "YA" : "TIDAK" }
                </display:column>

                <display:column title="Wakil/Hadir Bersama">
                    <c:if test="${line.wakilNama eq null}">
                        -
                    </c:if>
                    ${line.wakilNama}
                </display:column>

                <display:column title="Catatan" class="${line_rowNum}" style="text-transform:uppercase;">
                    <c:if test="${line.keterangan eq null}">
                        -
                    </c:if>
                    ${line.keterangan}
                </display:column>

            </display:table>  

            <br/><br/>
            <legend>
                Maklumat Notis Siasatan
            </legend>
            <br/>
            <display:table class="tablecloth" name="${actionBean.senaraiNotis}" id="line" defaultorder="ascending">
                <display:column title="Bil"  >${line_rowNum}</display:column>
                <display:column title="Nama" style="text-transform:uppercase;">
                    ${line.hakmilikPihakBerkepentingan.pihak.nama}<br>
                    (
                    <c:if test="${line.pihak ne null}">
                        <c:if test="${line.pihak.jenis.kod ne 'PG'}">
                            Penggadai
                        </c:if>
                        <c:if test="${line.pihak.jenis.kod eq 'PG'}">
                            ${line.hakmilikPihakBerkepentingan.jenis.nama}
                        </c:if>
                    </c:if>
                    <c:if test="${line.pihak eq null}">
                        ${line.hakmilikPihakBerkepentingan.jenis.nama}
                    </c:if>)



                </display:column>
                <display:column title="Nama Penghantar Notis" style="text-transform:uppercase;" >
                    ${line.penghantarNotis.nama}<br>
                    No.K/P:(${line.penghantarNotis.noPengenalan})
                </display:column>
                <display:column title="Status Penyampaian" property="kodStatusTerima.nama" style="text-transform:uppercase;">${line.kodStatusTerima.nama}</display:column>
                <display:column title="Cara Penghantaran" property="kodCaraPenghantaran.nama" style="text-transform:uppercase;">${line.kodCaraPenghantaran.nama}</display:column>
                <display:column title="Tarikh">
                    <p>
                        H : <fmt:formatDate value="${line.tarikhHantar}" pattern="dd/MM/yyyy"/>
                    </p>
                    <p>
                        T : <fmt:formatDate value="${line.tarikhTerima}" pattern="dd/MM/yyyy"/>
                    </p>
                </display:column>
                <s:hidden name="idNotis">${line.idNotis}</s:hidden>
                <display:column title="Catatan" property="catatanPenerimaan" style="text-transform:uppercase;">${line.catatanPenerimaan}</display:column>
                <display:column title="Tindakan">
                    <div align="center">
                        <c:if test="${line.buktiPenerimaan.namaFizikal != null}">
                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                 onclick="doViewReport('${line.buktiPenerimaan.idDokumen}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen">
                            <s:hidden name="idDokumen">${line.buktiPenerimaan.idDokumen}</s:hidden>
                        </c:if>
                    </div>
                </display:column>
            </display:table>

            <br/><br/>
            <c:if test="${actionBean.show16H eq true}">
                <legend>
                    Maklumat Notis 16H
                </legend>
                <br/>
                <display:table class="tablecloth" name="${actionBean.senaraiNotis2}" id="line" defaultorder="ascending">
                    <display:column title="Bil"  >${line_rowNum}</display:column>
                    <display:column title="Nama" style="text-transform:uppercase;">
                        <c:if test="${line.penasihatUndang.nama eq null}">
                            ${line.hakmilikPihakBerkepentingan.pihak.nama}<br>
                            (
                            <c:if test="${line.pihak ne null}">
                                <c:if test="${line.pihak.jenis.kod ne 'PG'}">
                                    Penggadai 
                                </c:if>
                                <c:if test="${line.pihak.jenis.kod eq 'PG'}">
                                    ${line.hakmilikPihakBerkepentingan.jenis.nama}
                                </c:if>
                            </c:if>
                            <c:if test="${line.pihak eq null}">
                                ${line.hakmilikPihakBerkepentingan.jenis.nama}
                            </c:if>)
                        </c:if>
                        <c:if test="${line.penasihatUndang.nama ne null}">
                            ${line.penasihatUndang.nama}<br>
                            (Penasihat Undang-Undang)
                        </c:if>

                    </display:column>
                    <display:column title="Nama Penghantar Notis" style="text-transform:uppercase;" >
                        ${line.penghantarNotis.nama}<br>
                        No.K/P:(${line.penghantarNotis.noPengenalan})
                    </display:column>
                    <display:column title="Status Penyampaian" property="kodStatusTerima.nama" style="text-transform:uppercase;">${line.kodStatusTerima.nama}</display:column>
                    <display:column title="Cara Penghantaran" property="kodCaraPenghantaran.nama" style="text-transform:uppercase;">${line.kodCaraPenghantaran.nama}</display:column>
                    <display:column title="Tarikh">
                        <p>
                            H : <fmt:formatDate value="${line.tarikhHantar}" pattern="dd/MM/yyyy"/>
                        </p>
                        <p>
                            T : <fmt:formatDate value="${line.tarikhTerima}" pattern="dd/MM/yyyy"/>
                        </p>
                    </display:column>

                    <s:hidden name="idNotis">${line.idNotis}</s:hidden>
                    <display:column title="Catatan" property="catatanPenerimaan" style="text-transform:uppercase;">${line.catatanPenerimaan}</display:column>
                    <display:column title="Tindakan">
                        <div align="center">
                            <c:if test="${line.buktiPenerimaan.namaFizikal != null}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                     onclick="doViewReport('${line.buktiPenerimaan.idDokumen}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen">
                                <s:hidden name="idDokumen">${line.buktiPenerimaan.idDokumen}</s:hidden>
                            </c:if>
                        </div>
                    </display:column>
                </display:table>
            </c:if>
            <c:if test="${actionBean.show16H eq false}">
                <legend>
                    Maklumat Notis 16H
                </legend>
                <br/>                
                <display:table class="tablecloth" >
                    <display:column title="Bil"  ></display:column>
                    <display:column title="Nama" style="text-transform:uppercase;"></display:column>
                    <display:column title="Nama Penghantar Notis" style="text-transform:uppercase;" ></display:column>
                    <display:column title="Cara Penghantaran" style="text-transform:uppercase;"></display:column>
                    <display:column title="Tarikh">
                        <p>
                            H : <fmt:formatDate value="" pattern="dd/MM/yyyy"/>
                        </p>
                        <p>
                            T : <fmt:formatDate value="" pattern="dd/MM/yyyy"/>
                        </p>
                    </display:column>

                    <display:column title="Catatan" style="text-transform:uppercase;"></display:column>

                </display:table>
            </c:if>

            <p align="center">
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>
        <br>
    </div>
</s:form>
