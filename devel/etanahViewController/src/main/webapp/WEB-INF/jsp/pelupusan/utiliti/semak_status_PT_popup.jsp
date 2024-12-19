<%-- 
    Document   : status_popup
    Created on : Jul 16, 2010, 11:56:47 AM
    Author     : muhammad.amin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<title>STATUS PERMOHONAN</title>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>

<s:form beanclass="etanah.view.stripes.pelupusan.utility.SemakStatusPTActionBean" id="cetak_semula">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">

        <fieldset class="aras1">
            <legend>Status Permohonan</legend>
            <br/>
            <c:if test="${actionBean.participant eq null}">
                <table align="center">
                    <tr align="center"><td>
                            <font style="text-transform:uppercase;" size="2"><b>
                                ${actionBean.permohonan.kodUrusan.nama}&nbsp;</b>
                            </font>
                        </td> </tr> 
                </table>
            </c:if>
            <c:if test="${actionBean.participant ne null}">
                <div align="center">Kedudukan Semasa Tugasan </div>
                <br/>
                <table align="center">
                    <tr align="center"><td>
                            <font style="text-transform:uppercase;" size="2"><b>
                                ${actionBean.permohonan.kodUrusan.nama}&nbsp;</b>
                            </font>
                        </td> </tr>
                </table>
                <p>
                    <label>Tindakan :</label>
                    <font style="text-transform:uppercase;"> ${actionBean.tindakan}</font>&nbsp;
                </p>

                <p>
                    <label>Kedudukan :</label>

                    <c:if test="${fn:length(actionBean.groupList) > 0}">
                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.groupList}" var="group">  
                            <font style="text-transform:uppercase;">
                            <c:if test="${fn:length(actionBean.groupList) > 1}">
                                ${count}.
                            </c:if>
                            <c:choose>
                                <c:when test="${group.id eq 'kptconsent'}">
                                    Ketua Pembantu Tadbir Consent &nbsp;
                                </c:when>
                                <c:when test="${group.id eq 'ptgkptconsent'}">
                                    Ketua Pembantu Tadbir Consent (PTG)  &nbsp;
                                </c:when>
                                <c:when test="${group.id eq 'ptgptconsent'}">
                                    Pembantu Tadbir Consent (PTG)&nbsp;
                                </c:when>
                                <c:when test="${group.id eq 'kppt'}">
                                    Ketua Penolong Pegawai Tanah &nbsp;
                                </c:when>
                                <c:when test="${group.id eq 'kpt'}">
                                    Ketua Pembantu Tadbir &nbsp;
                                </c:when>
                                <c:when test="${group.id eq 'kptptg'}">
                                    Ketua Pembantu Tadbir (PTG)&nbsp;
                                </c:when>
                                <c:when test="${group.id eq 'pengarahtanahgalian' || group.id eq 'ptg'}">
                                    Pengarah Tanah & Galian &nbsp;
                                </c:when>
                                <c:when test="${group.id eq 'pentadbirtanah'}">
                                    Pentadbir Tanah &nbsp;
                                </c:when>
                                <c:when test="${group.id eq 'ppptg'}">
                                    Penolong Pengarah PTG &nbsp;
                                </c:when>
                                <c:when test="${group.id eq 'ptconsent'}">
                                    Pembantu Tadbir Consent &nbsp;
                                </c:when>
                                <c:when test="${group.id eq 'ptpesaka'}">
                                    Pembantu Tadbir Pesaka &nbsp;
                                </c:when>
                                <c:when test="${group.id eq 'ptptgconsent'}">
                                    Pembantu Tadbir Consent (PTG) &nbsp;
                                </c:when>
                                <c:when test="${group.id eq 'ptspoc'}">
                                    Pembantu Tadbir SPOC &nbsp;
                                </c:when>
                                <c:when test="${group.id eq 'sumb'}">
                                    Setiausaha Menteri Besar &nbsp;
                                </c:when>
                                <c:when test="${group.id eq 'summk'}">
                                    Setiausaha Majlis Mesyuarat Kerajaan &nbsp;
                                </c:when>
                                <c:when test="${group.id eq 'tptd'}">
                                    Timbalan Pentadbir Tanah &nbsp;
                                </c:when>
                                <c:when test="${group.id eq 'tptg'}">
                                    Timbalan Pengarah Tanah & Galian &nbsp;
                                </c:when>
                                <c:when test="${group.id eq 'ptmmkn'}">
                                    Pembantu Tadbir (MMKN) &nbsp;
                                </c:when>
                                <c:when test="${group.id eq 'kptmmkn'}">
                                    Ketua Pembantu Tadbir (MMKN) &nbsp;
                                </c:when>
                                <c:when test="${group.id eq 'pptgmmkn'}">
                                    Penolong Pengarah PTG (MMKN) &nbsp;
                                </c:when>
                                <c:otherwise>
                                    ${group.id} <br/>
                                </c:otherwise>
                            </c:choose>

                            </font>
                            <c:set value="${count + 1}" var="count"/>
                        </c:forEach>&nbsp;
                    </c:if>
                    <c:if test="${fn:length(actionBean.groupList) == 0}">
                        TIADA DATA 
                    </c:if>
                </p>
                <p>
                    <label>Diambil Oleh :</label>
                    <font style="text-transform:uppercase;">
                    <c:if test="${actionBean.acquiredBy ne null}">
                        ${actionBean.acquiredBy}&nbsp;
                    </c:if>
                    <c:if test="${actionBean.acquiredBy eq null}"> Tugasan Belum Diambil </c:if>
                    </font>
                </p>
                <br/>
            </c:if>
            <p>
                <label>ID Permohonan :</label>
                <font style="text-transform:uppercase;">
                <c:if test="${actionBean.permohonan.idPermohonan ne null}"> ${actionBean.permohonan.idPermohonan}&nbsp; </c:if>
                <c:if test="${actionBean.permohonan.idPermohonan eq null}"> Tiada Data </c:if>
                </font>
            </p>
<!--            <p>
                <label>No PT :</label>
                <font style="text-transform:uppercase;">
                ${actionBean.noPT}
                </font>
            </p>-->
            <c:if test="${actionBean.permohonan.keputusan.kod ne 'TK'}">
                <p>
                    <label>Keputusan :</label>
                    <font style="text-transform:uppercase;">
                    <c:if test="${actionBean.permohonan.keputusan ne null}"> ${actionBean.permohonan.keputusan.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonan.keputusan eq null}"> Tiada Keputusan </c:if>
                    </font>
                </p>
                <c:if test="${actionBean.participant eq null}">
                    <p>
                        <label>Status Permohonan :</label>
                        PERMOHONAN INI SUDAH DISELESAIKAN
                    </p>
                </c:if>
            </c:if>
            <c:if test="${actionBean.permohonan.keputusan.kod eq 'TK'}">
                <p>
                    <label>Status Permohonan :</label>
                    PERMOHONAN INI TELAH DIBATALKAN
                </p>
                <p>
                    <label>Sebab Pembatalan :</label>
                    <font style="text-transform:uppercase;">${actionBean.permohonan.sebab}&nbsp;</font>
                </p>
            </c:if>

            <%-- SENARAI HAKMILIK: START --%>

            <div align="center">
                <div align="center">Senarai Hakmilik </div>
                <display:table name="${actionBean.disHakmilikPermohonanList}" cellpadding="0" cellspacing="0" class="tablecloth" id="line" style="width:70%"
                               requestURI="${pageContext.request.contextPath}/pelupusan/semak_statusPT">
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column title="No Hakmilik"><font style="text-transform:uppercase;"><fmt:parseNumber value="${line.hakmilikPermohonan.hakmilik.noHakmilik}"/></font></display:column>
                    <display:column title="No Lot"><font style="text-transform:uppercase;">
                        ${line.hakmilikPermohonan.hakmilik.lot.nama} <fmt:parseNumber value="${line.hakmilikPermohonan.hakmilik.noLot}"/></font>
                    </display:column>
                    <display:column title="No PT"><font style="text-transform:uppercase;">${line.noPT}</font></display:column>
                   
                    <c:choose>
                        <c:when test="${line.hakmilikPermohonan.hakmilik eq null}">
                             <display:column title="Bandar/Pekan/Mukim"><font style="text-transform:uppercase;">${line.hakmilikPermohonan.bandarPekanMukimBaru.nama}</font></display:column>
                            <display:column title="Daerah"><font style="text-transform:uppercase;">${line.hakmilikPermohonan.bandarPekanMukimBaru.daerah.nama}</font></display:column> 
                        </c:when>
                        <c:otherwise>
                             <display:column title="Bandar/Pekan/Mukim"><font style="text-transform:uppercase;">${line.hakmilikPermohonan.hakmilik.bandarPekanMukim.nama}</font></display:column>
                            <display:column title="Daerah"><font style="text-transform:uppercase;">${line.hakmilikPermohonan.hakmilik.daerah.nama}</font></display:column> 
                        </c:otherwise>
                    </c:choose>
                    
                    
                </display:table>
            </div>

            <%-- SENARAI HAKMILIK: END --%>

            <div class="content" align="center">
                <c:if test="${!fn:endsWith(actionBean.permohonan.kodUrusan.idWorkflow, 'MMK_melaka') && !fn:endsWith(actionBean.permohonan.kodUrusan.idWorkflow, 'Project1/mmk2')}">
                    <div align="center">Senarai Pemohon </div>
                </c:if>
                <c:if test="${fn:endsWith(actionBean.permohonan.kodUrusan.idWorkflow, 'MMK_melaka') || fn:endsWith(actionBean.permohonan.kodUrusan.idWorkflow, 'Project1/mmk2')}">
                    <div align="center">Senarai Pemohon/Penerima</div>
                </c:if>
                <display:table class="tablecloth" name="${actionBean.pemohonList}" cellpadding="0" cellspacing="0" id="line" style="width:70%"
                               requestURI="${pageContext.request.contextPath}/pelupusan/semak_statusPT">
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column title="Nama"><font style="text-transform:uppercase;">${line.pihak.nama}</font></display:column>
                    <display:column property="pihak.jenisPengenalan.nama" title="Jenis Pengenalan" />
                    <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                </display:table>
            </div>
            <!-- NOT USED
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'BTADT' && actionBean.permohonan.kodUrusan.kod ne 'PMMK1' && actionBean.permohonan.kodUrusan.kod ne 'PMMK2'}">
                <div class="content" align="center">
                    <div align="center">Senarai Penerima </div>
                    <display:table class="tablecloth" name="${actionBean.permohonanPihakList}" cellpadding="0" cellspacing="0" id="line" style="width:70%"
                                   requestURI="${pageContext.request.contextPath}/pelupusan/semak_statusPT">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Nama"><font style="text-transform:uppercase;">${line.pihak.nama}</font></display:column>
                        <display:column property="pihak.jenisPengenalan.nama" title="Jenis Pengenalan" />
                        <display:column title="Nombor Pengenalan" >${line.pihak.noPengenalan}</display:column>
                        <display:column title="Jenis Pihak" property="jenis.nama"></display:column>                        
                    </display:table>
                </div>
            </c:if>-->
            <div align="center">
                <div align="center">Rekod Tugasan </div>
                <display:table name="${actionBean.fasaPermohonanList}" cellpadding="0" cellspacing="0" class="tablecloth" pagesize="15" id="line" style="width:70%"
                               requestURI="${pageContext.request.contextPath}/pelupusan/semak_statusPT">
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column title="ID Pengguna" property="idPengguna"  style="text-transform:uppercase;"/>
                    <display:column title="Tarikh Terima" property="infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                    <display:column title="Tarikh Selesai">
                        <c:if test="${line.tarikhHantar ne null}"><fmt:formatDate pattern="dd/MM/yyyy hh:mm:ss" value="${line.tarikhHantar}"/> </c:if>
                        <c:if test="${line.tarikhHantar eq null}"> BELUM DISELESAIKAN </c:if>
                    </display:column>
                    <display:column title="Keputusan">
                        <c:if test="${line.keputusan ne null}"><div align="center"><font style="text-transform:uppercase;">${line.keputusan.nama}&nbsp; </font></div></c:if>
                        <c:if test="${line.keputusan eq null}"><div align="center">-</div></c:if>
                    </display:column>
                </display:table>
            </div>
            <br/>

            <p align="center">
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
            <br/>
        </fieldset>
    </div>
</s:form>
