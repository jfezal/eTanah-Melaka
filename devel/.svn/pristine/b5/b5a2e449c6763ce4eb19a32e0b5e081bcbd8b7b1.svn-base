<%-- 
    Document   : status_permohonan
    Created on : Nov 1, 2009, 6:15:42 PM
    Author     : wan.fairul
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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<s:messages />
<s:errors />

<s:form action="/daftar/kesinambungan" >
    <br>
    
        <div class="subtitle" >
            <fieldset class="aras1">
                <legend>Maklumat Perserahan</legend>
                <br>
                <br>
                <p><label for="idPermohonan">ID Permohonan/ID Perserahan :</label>
                    ${actionBean.permohonan.idPermohonan}
                </p>
                <p><label for="kodUrusan">Kod Urusan/Urusan :</label>
                    ${actionBean.permohonan.kodUrusan.kod} -
                    ${actionBean.permohonan.kodUrusan.nama}
                </p>

                <p><label for="tarikhDaftar">Tarikh Permohonan/Perserahan :</label>
                    <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>     <fmt:formatDate pattern="hh:mm aa" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>
                </p>
                <p><label for="penyerah">Penyerah :</label>
                    ${actionBean.permohonan.penyerahNama == null ? "-" : actionBean.permohonan.penyerahNama}
                </p>
                <p><label for="keputusan">Keputusan :</label>
                    ${actionBean.permohonan.keputusan.kod == null ? "-" : actionBean.permohonan.keputusan.nama}
                </p>
                <c:if test="${actionBean.permohonan.keputusan.kod eq  'TK'}">
                    <p><label>Sebab Batal :</label>
                        ${actionBean.permohonan.sebab == null ? "-" : actionBean.permohonan.sebab}
                    </p>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.jabatan.kod ne '22'}">
                    <p><label>Peringkat :</label>
                        ${actionBean.stage == null ? "-" : actionBean.stage}
                    </p>
                </c:if>
                <p><label>Kerani Kemasukan :</label>
                    ${actionBean.permohonan.infoAudit.dimasukOleh.nama == null ? "-" : actionBean.permohonan.infoAudit.dimasukOleh.nama}
                </p>
                <c:if test="${actionBean.pendaftar ne null && actionBean.status eq 'SL'}">
                <p><label>Pendaftar :</label>
                    ${actionBean.pendaftar == null ? "-" : actionBean.pendaftar}
                </p>
                </c:if>

                <br>
            </fieldset> 
        </div>
   
    <c:if test="${actionBean.permohonan.kodUrusan.kodPerserahan.kod eq 'MH'}">
        <fieldset class="aras1">
            <legend>
                Maklumat Hakmilik Sebelum
            </legend>
            <c:if test="${actionBean.status eq 'SL'}">
                <p><div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.hmSebelumList}"  cellpadding="0" cellspacing="0" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column   title="ID Hakmilik" property="hakmilik.idHakmilik"/>
                        <display:column property="hakmilik.noLot" title="No Lot" />
                        <display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    </display:table>
                    &nbsp;
                </div></p>
                <p><div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.hmpSebelumList}"  cellpadding="0" cellspacing="0" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                         <display:column   title="ID Hakmilik" property="hakmilik.idHakmilik"/>
                        <display:column property="hakmilik.noLot" title="No Lot" />
                        <display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    </display:table>
                    &nbsp;
                </div></p>
            </c:if>
        </fieldset>
    </c:if>
    <br>
    <fieldset class="aras1">
        <legend>
            Maklumat Hakmilik<c:if test="${actionBean.permohonan.kodUrusan.kodPerserahan.kod eq 'MH'}"> Baru</c:if>
        </legend>
        <p><div class="content" align="center">

            <display:table class="tablecloth" name="${actionBean.permohonan.senaraiHakmilik}"  cellpadding="0" cellspacing="0" id="line">
                <display:column title="No" sortable="true">${line_rowNum}</display:column>
                <display:column   title="ID Hakmilik" property="hakmilik.idHakmilik"/>
                <display:column property="hakmilik.noLot" title="No Lot" />
                <display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
            </display:table>
            &nbsp;
        </div></p>
    </fieldset>
    <br>
    <fieldset class="aras1">
        <legend>
            Proses permohonan:
        </legend><div class="content" align="center">
        <display:table name="${actionBean.fasaMohon}" class="tablecloth" id="line">
            <display:column property="infoAudit.tarikhMasuk" title="Tarikh" sortable="true" format="{0,date,dd/MM/yyyy hh:mm aa}"/>
            <display:column title="Peringkat">
                        <c:if test="${line.idAliran eq 'agih_tugas'}">
                            Agih Tugasan
                        </c:if>
                        <c:if test="${line.idAliran eq 'kemasukan'}">
                            Kemasukan
                        </c:if>
                        <c:if test="${line.idAliran eq 'keputusan'}">
                            Keputusan
                        </c:if>
                    </display:column>
            <display:column property="infoAudit.dimasukOleh.nama" title="Diproses Oleh"/>
            <display:column title="Syor / Keputusan" property="keputusan.nama"/>
            <display:column property="ulasan" title="Ulasan"/>
        </display:table></div>
    </fieldset>
    <br>
    <div  align="center"><p><s:button name="tutup" value="Tutup" class="btn" onclick="javascript:window.close();"/></p></div>
</s:form>
</body>
</html>