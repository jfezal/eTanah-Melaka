<%-- 
    Document   : semak_notis
    Created on : Mar 9, 2011, 5:17:21 PM
    Author     : mdizzat.mashrom
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
</script>
<s:form beanclass="etanah.view.stripes.lelong.KemasukkanRekodActionBean" id="folder_div">

    <s:messages/>
    <s:errors/>&nbsp;
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                <c:if test="${actionBean.gantian eq false}">
                    Maklumat Penghantaran Notis Siasatan
                </c:if>
                <c:if test="${actionBean.gantian eq true}">
                    Maklumat Penghantaran Pemberitahuan Penangguhan Siasatan
                </c:if>
            </legend><br>

            <p class=instr>
                *<em>Petunjuk :</em>
            </p>
            <p class=instr>
                <em>H:</em> Tarikh Hantar
                <em>T:</em> Tarikh Terima
            </p>
            <font size="2" color="black"></font>
            <p>
                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                     width="20" height="20" /> - <font size="1" color="black">Papar Dokumen</font>&nbsp;&nbsp;&nbsp;
                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                     width="20" height="20" /> - <font size="1" color="black">Muat Naik Dokumen</font>&nbsp;&nbsp;&nbsp;
                <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png"
                     width="20" height="20" /> - <font size="1" color="black">Imbas Dokumen</font>
            </p><br>

            <div class="content" align="center">

                <display:table class="tablecloth" name="${actionBean.listNotis}" cellpadding="0" cellspacing="0" requestURI="/lelong/kemasukkan_rekod" id="line">
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column title="Nama" class="rowCount" style="text-transform:uppercase;">
                        ${line.hakmilikPihakBerkepentingan.pihak.nama}<br>
                        (
                        <c:if test="${line.pihak ne null}">
                            <c:if test="${line.pihak.jenis.kod eq 'PM'}">
                                Penggadai
                            </c:if>
                            <c:if test="${line.pihak.jenis.kod ne 'PM'}">
                                ${line.hakmilikPihakBerkepentingan.jenis.nama}
                            </c:if>
                        </c:if>
                        <c:if test="${line.pihak eq null}">
                            ${line.hakmilikPihakBerkepentingan.jenis.nama}
                        </c:if>)
                    </display:column>
                    <display:column title="Nama Penghantar Notis" style="text-transform:uppercase;">
                        ${line.penghantarNotis.nama}<br>
                        No.K/P:(${line.penghantarNotis.noPengenalan})
                    </display:column>
                    <display:column title="Status Penyampaian" property="kodStatusTerima.nama" style="text-transform:uppercase;"/>
                    <display:column title="Cara Penghantaran" property="kodCaraPenghantaran.nama" style="text-transform:uppercase;"/>
                    <display:column title="Tarikh">
                        <p>
                            H : <fmt:formatDate value="${line.tarikhHantar}" pattern="dd/MM/yyyy"/>
                        </p>
                        <p>
                            T : <fmt:formatDate value="${line.tarikhTerima}" pattern="dd/MM/yyyy"/>
                        </p>
                    </display:column>
                    <display:column  title="Catatan" property="catatanPenerimaan" style="text-transform:uppercase;"/>

                    <display:column title="Tindakan">
                        <c:if test="${line.buktiPenerimaan.idDokumen eq null}">
                            -
                        </c:if>
                        <c:if test="${line.buktiPenerimaan.idDokumen ne null}">
                            <p align="center">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                     onclick="doViewReport('${line.buktiPenerimaan.idDokumen}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                            </p>
                        </c:if>
                    </display:column>
                </display:table>
            </div>
            <br>
        </fieldset><br>

        <c:if test="${fn:length(actionBean.listNotisOld)>0}">
            <fieldset class="aras1">
                <legend>
                    Sejarah Notis Penghataran
                </legend>
                <div class="content" align="center">

                    <display:table class="tablecloth" name="${actionBean.listNotisOld}" cellpadding="0" cellspacing="0" requestURI="/lelong/kemasukkan_rekod" id="line">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Nama" class="rowCount" style="text-transform:uppercase;">
                            ${line.hakmilikPihakBerkepentingan.pihak.nama}<br>
                            (
                            <c:if test="${line.pihak ne null}">
                                <c:if test="${line.pihak.jenis.kod eq 'PM'}">
                                    Penggadai
                                </c:if>
                                <c:if test="${line.pihak.jenis.kod ne 'PM'}">
                                    ${line.hakmilikPihakBerkepentingan.jenis.nama}
                                </c:if>
                            </c:if>
                            <c:if test="${line.pihak eq null}">
                                ${line.hakmilikPihakBerkepentingan.jenis.nama}
                            </c:if>)
                        </display:column>
                        <display:column title="Nama Penghantar Notis" style="text-transform:uppercase;">
                            ${line.penghantarNotis.nama}<br>
                            No.K/P:(${line.penghantarNotis.noPengenalan})
                        </display:column>
                        <display:column title="Status Penyampaian" property="kodStatusTerima.nama" style="text-transform:uppercase;"/>
                        <display:column title="Cara Penghantaran" property="kodCaraPenghantaran.nama" style="text-transform:uppercase;"/>
                        <display:column title="Tarikh">
                            <p>
                                H : <fmt:formatDate value="${line.tarikhHantar}" pattern="dd/MM/yyyy"/>
                            </p>
                            <p>
                                T : <fmt:formatDate value="${line.tarikhTerima}" pattern="dd/MM/yyyy"/>
                            </p>
                        </display:column>
                        <display:column  title="Catatan" property="catatanPenerimaan" style="text-transform:uppercase;"/>

                        <display:column title="Tindakan">
                            <c:if test="${line.buktiPenerimaan.idDokumen eq null}">
                                -
                            </c:if>
                            <c:if test="${line.buktiPenerimaan.idDokumen ne null}">
                                <p align="center">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                         onclick="doViewReport('${line.buktiPenerimaan.idDokumen}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                </p>
                            </c:if>
                        </display:column>
                    </display:table>
                </div>
            </fieldset>
        </c:if>
    </div>
</s:form>