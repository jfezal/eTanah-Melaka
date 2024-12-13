<%-- 
    Document   : maklumat_keterangan_view
    Created on : 22-Mar-2010, 11:08:10
    Author     : nurshahida.radzi
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
</script>

<s:form beanclass="etanah.view.penguatkuasaan.RingkasanLaporanPolisActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Ringkasan Laporan Polis/SPRM
            </legend>
            <div class="content" >
                <c:if test="${actionBean.kodNegeri eq '05'}">
                    <p>
                        <label>Agensi Terlibat :</label>
                        ${actionBean.permohonanRujukanLuar.agensi.nama}

                    </p>
                </c:if>

                <p>
                    <label>Nombor <i>Report</i> :</label>
                    ${actionBean.permohonanRujukanLuar.noRujukan}&nbsp;
                </p>
                <p>
                    <label>Tarikh Laporan :</label>
                    <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permohonanRujukanLuar.tarikhRujukan}"/>&nbsp;
                </p>
                <p>
                    <label>Waktu Laporan :</label>
                    <fmt:formatDate pattern="hh:mm:ss aaa " value="${actionBean.permohonanRujukanLuar.tarikhRujukan}"/>&nbsp;
                    <%--<font color="red" size="1">cth : hh / bb / tttt</font>--%>
                </p>
                <p>
                    <c:choose>
                        <c:when test="${actionBean.kodNegeri eq '05'}">
                            <label>Lokasi Balai Polis / Cawangan SPRM :</label>
                        </c:when>
                        <c:otherwise>
                            <label>Lokasi Balai Polis :</label>
                        </c:otherwise>
                    </c:choose>
                    
                    ${actionBean.permohonanRujukanLuar.lokasiAgensi}&nbsp;
                </p>
                <%-- <p>
                     <label>Ringkasan Laporan Polis :</label>&nbsp;
                     ${actionBean.permohonanRujukanLuar.catatan}&nbsp;
                 </p>--%>
                <p>
                    <label>Lampiran :</label>
                    <%--   <c:if test="${actionBean.permohonanRujukanLuar.dokumen.namaFizikal != null}">
                                   <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                        onclick="doViewReport('${actionBean.permohonanRujukanLuar.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                        onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${actionBean.permohonanRujukanLuar.dokumen.kodDokumen.nama}"/>
                       (${actionBean.permohonanRujukanLuar.dokumen.tajuk})
                       </c:if>--%>

                </p>
                <p align="center">
                    <c:set value="1" var="count"/>
                    <c:forEach items="${actionBean.permohonan.folderDokumen.senaraiKandungan}" var="senarai">
                        <c:if test="${senarai.dokumen.kodDokumen.kod eq 'LP'}">
                            <c:if test="${senarai.dokumen.namaFizikal != null}">
                                <br>
                                -
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                <%--${senarai.dokumen.kodDokumen.nama} ${count} (--%>${senarai.dokumen.tajuk}<%--)--%>
                                <c:set value="${count+1}" var="count"/>
                            </c:if>
                        </c:if>
                    </c:forEach>
                </p>
            </div>
        </fieldset>
    </div>
</s:form>

