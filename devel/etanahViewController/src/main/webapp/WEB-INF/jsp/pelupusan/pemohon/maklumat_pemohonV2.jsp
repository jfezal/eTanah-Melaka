<%-- 
    Document   : maklumat_pemohonV2
    Created on : Jun 10, 2013, 3:49:36 PM
    Author     : afham
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>

<script type="text/javascript">
    function openFrame(type) {
        doBlockUI();
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohonV2?openFrame&type=" + type, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
    }

    function refreshV2Pemohon(type) {
        var url = '${pageContext.request.contextPath}/pelupusan/maklumat_pemohonV2?refreshpage&type=' + type;
        $.get(url,
                function(data) {
                    $('#page_div').html(data);
                }, 'html');
        doUnBlockUI();
    }

    function showMaklumatLuarNegeri(idPemohon, idPihak, status)
    {
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohonV2?viewFormMaklumatTanahMilik&idPemohon=" + idPemohon + "&idPihak=" + idPihak + "&status=" + status, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600,scrollbars=yes");
    }
    function showKeluarga(idPemohon)
    {
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohonV2?viewFormMaklumatKeluarga&idPemohon=" + idPemohon, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }

    function showPengarah(idPemohon)
    {
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohonV2?viewFormMaklumatPengarah&idPemohon=" + idPemohon, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
    function showMaklumatPemohon(idPemohon)
    {
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohonV2?viewFormMaklumatPemohon&idPemohon=" + idPemohon, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,scrollbars=yes,width=900,height=600");
    }
</script>

<s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPemohonV2ActionBean">
    <s:errors/>
    <s:messages/>
    <s:hidden name="idHakmilik" id="idHakmilik"/>
    <s:hidden name="noPtSementara" id="noPtSementara"/>
    <div class="subtitle displaytag" align="center">
        <div class="subtitle">
            <fieldset class="aras1" id="locate">
                
                    <legend>
                        Maklumat Pemohon
                        <span style="float:right">
                            <%--<a onclick="viewPerihalTanah();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                            <c:if test="${actionBean.disPemohonController.tPemohon}">
                                <c:if test="${fn:length(actionBean.senaraiKelompok) eq 0}">
                                    <a onclick="openFrame('tPemohon');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                    </c:if>
                                </c:if>
                        </span>
                    </legend>

                    <c:if test="${fn:length(actionBean.senaraiKelompok) ne 0}">

                        <display:table class="tablecloth" name="${actionBean.senaraiKelompok}" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="/pelupusan/maklumat_pemohonV2">
                            <display:column title="Bil" sortable="true">${line_rowNum}
                                <s:hidden name="x" class="x${line_rowNum -1}" value="${line.idMohonKelompok}"/>
                            </display:column>
                            <display:column title="Id Permohonan">
                                ${line.permohonan.idPermohonan}
                            </display:column>
                            <display:column title="Nama">
                                <c:forEach items="${line.permohonan.senaraiPemohon}" var="i">
                                    <a onclick="javascript:showMaklumatPemohon(${i.idPemohon})" onmouseover="this.style.cursor = 'pointer';" >${i.pihak.nama}</a>
                                </c:forEach>
                            </display:column>
                            <display:column title="No Pengenalan">
                                <c:forEach items="${line.permohonan.senaraiPemohon}" var="i">
                                    ${i.pihak.noPengenalan}
                                </c:forEach>
                            </display:column>
                            <display:column title="Alamat">
                                <c:forEach items="${line.permohonan.senaraiPemohon}" var="i">
                                    ${i.pihak.alamat1}
                                    <c:if test="${i.pihak.alamat2 ne null}">, </c:if>
                                    ${i.pihak.alamat2}
                                    <c:if test="${i.pihak.alamat3 ne null}">, </c:if>
                                    ${i.pihak.alamat3}
                                    <c:if test="${i.pihak.alamat4 ne null}">, </c:if>
                                    ${i.pihak.alamat4}
                                </c:forEach>
                            </display:column>
                            <display:column title="Poskod">
                                <c:forEach items="${line.permohonan.senaraiPemohon}" var="i">
                                    ${i.pihak.poskod}
                                </c:forEach>
                            </display:column>
                            <display:column title="Negeri">
                                <c:forEach items="${line.permohonan.senaraiPemohon}" var="i">
                                    ${i.pihak.negeri.nama}
                                </c:forEach>
                            </display:column>
                            <c:if test="${!actionBean.disPemohonController.tPemohon}">
                                <display:column title="Tindakan" >
                                    <c:forEach items="${line.permohonan.senaraiPemohon}" var="i">
                                        <a onclick="showMaklumatLuarNegeri('',${i.pihak.idPihak}, 'view')" onmouseover="this.style.cursor = 'pointer';" title="Maklumat Tanah Pemohon/Pemilik"><img alt="Maklumat Tanah Pemohon/Pemilik"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/land.png'/></a> |
                                            <c:if test="${i.pihak.jenisPengenalan.kod eq 'B' || i.pihak.jenisPengenalan.kod eq 'L' || i.pihak.jenisPengenalan.kod eq 'P' || i.pihak.jenisPengenalan.kod eq 'T' || i.pihak.jenisPengenalan.kod eq 'I'}">
                                            <a onclick="showKeluarga('')" onmouseover="this.style.cursor = 'pointer';" title="Maklumat Keluarga"><img alt="Maklumat Keluarga"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/family2.png'/></a>
                                            </c:if>
                                            <c:if test="${i.pihak.jenisPengenalan.kod eq 'S'|| i.pihak.jenisPengenalan.kod eq 'U'}">
                                            <a onclick="showPengarah('')" onmouseover="this.style.cursor = 'pointer';" title="Maklumat Pengarah"><img alt="Maklumat Pengarah"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/corporate.png'/></a>
                                            </c:if>
                                        </c:forEach>

                                </display:column>
                            </c:if>
                        </display:table>
                    </c:if>


                    <c:if test="${fn:length(actionBean.senaraiKelompok) eq 0}">
                        <display:table class="tablecloth" name="${actionBean.listPemohon}" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="/pelupusan/maklumat_pemohonV2">
                            <display:column title="Bil" sortable="true">${line_rowNum}
                                <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                            </display:column>
                            <c:if test="${!actionBean.disPemohonController.tPemohon}">
                                <display:column title="Nama">
                                    <u><a onclick="javascript:showMaklumatPemohon(${line.idPemohon})" onmouseover="this.style.cursor = 'pointer';" >${line.pihak.nama}</a></u>
                                    </display:column>
                                </c:if>
                                <c:if test="${actionBean.disPemohonController.tPemohon}">
                                    <display:column property="pihak.nama" title="Nama"/>
                                </c:if>
                                <%--<display:column property="pihak.nama" title="Nama"/>--%>
                                <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                                <display:column title="Alamat" >
                                    ${line.pihak.alamat1}
                                    <c:if test="${line.pihak.alamat2 ne null}">, </c:if>
                                ${line.pihak.alamat2}
                                <c:if test="${line.pihak.alamat3 ne null}">, </c:if>
                                ${line.pihak.alamat3}
                                <c:if test="${line.pihak.alamat4 ne null}">, </c:if>
                                ${line.pihak.alamat4}
                            </display:column>
                            <display:column property="pihak.poskod" title="Poskod" />
                            <display:column property="pihak.negeri.nama" title="Negeri" />
                            <c:if test="${!actionBean.disPemohonController.tPemohon}">
                                <display:column title="Tindakan" >
                                    <a onclick="showMaklumatLuarNegeri(${line.idPemohon},${line.pihak.idPihak}, 'view')" onmouseover="this.style.cursor = 'pointer';" title="Maklumat Tanah Pemohon/Pemilik"><img alt="Maklumat Tanah Pemohon/Pemilik"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/land.png'/></a> |
                                        <c:if test="${line.pihak.jenisPengenalan.kod eq 'B' || line.pihak.jenisPengenalan.kod eq 'L' || line.pihak.jenisPengenalan.kod eq 'P' || line.pihak.jenisPengenalan.kod eq 'T' || line.pihak.jenisPengenalan.kod eq 'I'}">
                                        <a onclick="showKeluarga(${line.idPemohon})" onmouseover="this.style.cursor = 'pointer';" title="Maklumat Keluarga"><img alt="Maklumat Keluarga"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/family2.png'/></a>
                                        </c:if>
                                        <c:if test="${line.pihak.jenisPengenalan.kod eq 'S'|| line.pihak.jenisPengenalan.kod eq 'U'}">
                                        <a onclick="showPengarah(${line.idPemohon})" onmouseover="this.style.cursor = 'pointer';" title="Maklumat Pengarah"><img alt="Maklumat Pengarah"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/corporate.png'/></a>
                                        </c:if>
                                    </display:column>
                                </c:if>
                            </display:table>
                        </c:if>
                    
            </fieldset> <br>
        </div>
    </div>
</s:form>
