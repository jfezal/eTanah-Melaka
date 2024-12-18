<%-- 
    Document   : butiran_mesin_jentera_mlk
    Created on : Jun 6, 2011, 10:50:19 AM
    Author     : Akmal
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<script type="text/javascript">

    function addJentera() {

    <%--window.open("${pageContext.request.contextPath}/pelupusan/butiran_bahan_batuan?popup", "eTanah",--%>
            window.open("${pageContext.request.contextPath}/pelupusan/butiran_jentera?popup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=no");

        }

        function editJentera(idJentera) {

            window.open("${pageContext.request.contextPath}/pelupusan/butiran_jentera?updateJenteraPopup&idJentera=" + idJentera, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=no");

        }

        function refreshJentera() {
            var url = '${pageContext.request.contextPath}/pelupusan/butiran_jentera?refreshpage';
            $.get(url,
            function(data) {
                $('#page_div').html(data);
            }, 'html');
        }

        function removeJentera(idJentera)
        {
            if (confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pelupusan/butiran_jentera?deleteJentera&idJentera=' + idJentera;
                $.get(url,
                function(data) {
                    $('#page_div').html(data);
                    self.refreshJentera();
                }, 'html');
            }
        }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.stripes.pelupusan.ButiranJenteraActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <c:if test="${actionBean.permohonanBahanBatuan eq null}">
                <b><font style="text-transform:uppercase">SILA ISIKAN MAKLUMAT DI TAB MAKLUMAT BAHAN BATUAN TERLEBIH DAHULU  &nbsp;</font></b></c:if>
            <c:if test="${actionBean.permohonanBahanBatuan ne null}">
                <legend>Butir-butir Kenderaan/Jentera Yang Digunakan </legend><br/>

                <c:if test="${actionBean.kodNegeri eq '05'}">
                    <c:if test="${actionBean.stageId eq 'kemasukan'}">
                        <div class="content" align="center">
                            <display:table class="tablecloth" name="${actionBean.listJentera}" cellpadding="0" cellspacing="0" id="line"
                                           requestURI="${pageContext.request.contextPath}/pelupusan/butiran_bahan_batuan">

                                <display:column title="Bil" sortable="true">${line_rowNum}
                                    <%--<s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>--%>
                                    <s:hidden name="text" value="${line.id}"/>
                                </display:column>
                                <display:column title="Jenis Kenderaan/Jentera">${line.jenisJentera}</display:column>
                                <display:column title="No. Pendaftaran" >${line.noPendaftaranJentera}</display:column>
                                <display:column title="Kepunyaan">${line.kepunyaan}</display:column>
                                <display:column title="Kemaskini">
                                    <div align="center">
                                    <img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png' onclick="editJentera(${line.id}); return false;" onmouseover="this.style.cursor = 'pointer';"/>
                                </div>
                                </display:column>
                                <display:column title="Hapus">
                                    <div align="center">
                                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                             id='rem_${line_rowNum}' onclick="removeJentera(${line.id});" 
                                             onmouseover="this.style.cursor = 'pointer';"/>
                                    </div> 
                                </display:column>
                            </display:table>
                        </div>
                        <p align="center">
                            <s:button name="" id="new" value="Tambah" class="btn" onclick="addJentera();"/>
                        </p>
                    </c:if>

                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                        <c:if test="${actionBean.stageId eq '01Kemasukan'}">
                            <div class="content" align="center">
                                <display:table class="tablecloth" name="${actionBean.listJentera}" cellpadding="0" cellspacing="0" id="line"
                                               requestURI="${pageContext.request.contextPath}/pelupusan/butiran_bahan_batuan">

                                    <display:column title="Bil" sortable="true">${line_rowNum}
                                        <%--<s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>--%>
                                        <s:hidden name="text" value="${line.id}"/>
                                    </display:column>
                                    <display:column title="Jenis Kenderaan/Jentera">${line.jenisJentera}</display:column>
                                    <display:column title="No. Pendaftaran" >${line.noPendaftaranJentera}</display:column>
                                    <display:column title="Kepunyaan">${line.kepunyaan}</display:column>
                                           <display:column title="Kemaskini"><a href="#" onclick="editJentera(${line.id});
                                        return false;">Kemaskini</a></display:column>
                                    <display:column title="Hapus">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="removeJentera(${line.id});" 
                                                 onmouseover="this.style.cursor = 'pointer';"/>
                                        </div> 
                                    </display:column>
                                </display:table>
                            </div>
                            <p align="center">
                                <s:button name="" id="new" value="Tambah" class="btn" onclick="addJentera();"/>
                            </p>
                        </c:if>
                    </c:if>

                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMMK'}">
                        <c:if test="${actionBean.stageId eq '01Kemasukan'}">
                            <div class="content" align="center">
                                <display:table class="tablecloth" name="${actionBean.listJentera}" cellpadding="0" cellspacing="0" id="line"
                                               requestURI="${pageContext.request.contextPath}/pelupusan/butiran_bahan_batuan">

                                    <display:column title="Bil" sortable="true">${line_rowNum}
                                        <%--<s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>--%>
                                        <s:hidden name="text" value="${line.id}"/>
                                    </display:column>
                                    <display:column title="Jenis Kenderaan/Jentera">${line.jenisJentera}</display:column>
                                    <display:column title="No. Pendaftaran" >${line.noPendaftaranJentera}</display:column>
                                    <display:column title="Kepunyaan">${line.kepunyaan}</display:column>
                                           <display:column title="Kemaskini"><a href="#" onclick="editJentera(${line.id});
                                        return false;">Kemaskini</a></display:column>
                                    <display:column title="Hapus">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="removeJentera(${line.id});" 
                                                 onmouseover="this.style.cursor = 'pointer';"/>
                                        </div> 
                                    </display:column>
                                </display:table>
                            </div>
                            <p align="center">
                                <s:button name="" id="new" value="Tambah" class="btn" onclick="addJentera();"/>
                            </p>
                        </c:if>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTD'}">
                        <c:if test="${actionBean.stageId eq '01kemasukan'}">
                            <div class="content" align="center">
                                <display:table class="tablecloth" name="${actionBean.listJentera}" cellpadding="0" cellspacing="0" id="line"
                                               requestURI="${pageContext.request.contextPath}/pelupusan/butiran_bahan_batuan">

                                    <display:column title="Bil" sortable="true">${line_rowNum}
                                        <%--<s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>--%>
                                        <s:hidden name="text" value="${line.id}"/>
                                    </display:column>
                                    <display:column title="Jenis Kenderaan/Jentera">${line.jenisJentera}</display:column>
                                    <display:column title="No. Pendaftaran" >${line.noPendaftaranJentera}</display:column>
                                    <display:column title="Kepunyaan">${line.kepunyaan}</display:column>
                                           <display:column title="Kemaskini"><a href="#" onclick="editJentera(${line.id});
                                        return false;">Kemaskini</a></display:column>
                                    <display:column title="Hapus">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="removeJentera(${line.id});" 
                                                 onmouseover="this.style.cursor = 'pointer';"/>
                                        </div> 
                                    </display:column>
                                </display:table>
                            </div>
                            <p align="center">
                                <s:button name="" id="new" value="Tambah" class="btn" onclick="addJentera();"/>
                            </p>
                        </c:if>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTG'}">
                        <c:if test="${actionBean.stageId eq '01Kemasukan'}">
                            <div class="content" align="center">
                                <display:table class="tablecloth" name="${actionBean.listJentera}" cellpadding="0" cellspacing="0" id="line"
                                               requestURI="${pageContext.request.contextPath}/pelupusan/butiran_bahan_batuan">

                                    <display:column title="Bil" sortable="true">${line_rowNum}
                                        <%--<s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>--%>
                                        <s:hidden name="text" value="${line.id}"/>
                                    </display:column>
                                    <display:column title="Jenis Kenderaan/Jentera">${line.jenisJentera}</display:column>
                                    <display:column title="No. Pendaftaran" >${line.noPendaftaranJentera}</display:column>
                                    <display:column title="Kepunyaan">${line.kepunyaan}</display:column>
                                           <display:column title="Kemaskini"><a href="#" onclick="editJentera(${line.id});
                                        return false;">Kemaskini</a></display:column>
                                    <display:column title="Hapus">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="removeJentera(${line.id});" 
                                                 onmouseover="this.style.cursor = 'pointer';"/>
                                        </div> 
                                    </display:column>
                                </display:table>
                            </div>
                            <p align="center">
                                <s:button name="" id="new" value="Tambah" class="btn" onclick="addJentera();"/>
                            </p>
                        </c:if>
                    </c:if>
                    <c:if test="${actionBean.stageId ne 'kemasukan' && actionBean.stageId ne '01Kemasukan' && actionBean.stageId ne '01kemasukan'}">
                        <div class="content" align="center">
                            <display:table class="tablecloth" name="${actionBean.listJentera}" cellpadding="0" cellspacing="0" id="line"
                                           requestURI="${pageContext.request.contextPath}/pelupusan/butiran_bahan_batuan">

                                <display:column title="Bil" sortable="true">${line_rowNum}
                                    <%--<s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>--%>
                                    <s:hidden name="text" value="${line.id}"/>
                                </display:column>
                                <display:column title="Jenis Kenderaan/Jentera">${line.jenisJentera}</display:column>
                                <display:column title="No. Pendaftaran" >${line.noPendaftaranJentera}</display:column>
                                <display:column title="Kepunyaan">${line.kepunyaan}</display:column>

                            </display:table>
                        </div>
                    </c:if>
                </c:if>

                <c:if test="${actionBean.kodNegeri ne '05'}">
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.listJentera}" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="${pageContext.request.contextPath}/pelupusan/butiran_bahan_batuan">

                            <display:column title="Bil" sortable="true">${line_rowNum}
                                <%--<s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>--%>
                                <s:hidden name="text" value="${line.id}"/>
                            </display:column>
                            <display:column title="Jenis Kenderaan/Jentera">${line.jenisJentera}</display:column>
                            <display:column title="No. Pendaftaran" >${line.noPendaftaranJentera}</display:column>
                            <display:column title="Kepunyaan">${line.kepunyaan}</display:column>
                            <display:column title="Kemaskini">
                                <div align="center">
                                    <img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png' onclick="editJentera(${line.id}); return false;" onmouseover="this.style.cursor = 'pointer';"/>
                                </div>
                            </display:column>
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onclick="removeJentera(${line.id});" 
                                         onmouseover="this.style.cursor = 'pointer';"/>
                                </div> 
                            </display:column>
                        </display:table>
                    </div>
                    <%-- <c:if test="${!actionBean.view}"> --%>
                    <p align="center">
                        <s:button name="" id="new" value="Tambah" class="btn" onclick="addJentera();"/>
                    </p>
                    <%-- </c:if> --%>
                    <%--<c:if test="${actionBean.view}">
                    <p align="center">
                    <%-- show nothing
                     <s:button name="" id="new" value="Tambah" class="btn" onclick="addJentera();"/> 
                 </p>
                 </c:if>--%>
                </c:if>
            </c:if>
        </fieldset>
    </div>
</s:form>





