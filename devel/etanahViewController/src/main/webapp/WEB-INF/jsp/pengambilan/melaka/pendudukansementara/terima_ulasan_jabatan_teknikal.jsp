<%--
    Document   : terima_ulasan_jbtn_teknikal
    Created on : Jul 14, 2010, 2:33:59 PM
    Author     : sitifariza.hanim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function editJabatanTeknikal(id){
    <%--alert(id);--%>
            window.open("${pageContext.request.contextPath}/pengambilan/jabatan_teknikal12?showEditJabatanTeknikal&idRujukan="+id, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }

        function editJabatanTeknikal1(id){
    <%--alert(id);--%>
            window.open("${pageContext.request.contextPath}/pengambilan/jabatan_teknikal12?showEditJabatanTeknikal1&idRujukan="+id, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }

</script>

<s:form beanclass="etanah.view.stripes.pengambilan.JabatanTeknikal2ActionBean">
    <div class="subtitle">

        <fieldset class="aras1">
            <br>
            <legend>
                Senarai Jabatan Teknikal  <c:if test="${actionBean.kNegeri eq '04'}">Dan YB Adun</c:if> Yang Terlibat
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiRujukanLuar}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pengambilan/jabatan_teknikal12">
                    <display:column title="Bil." sortable="true">${line_rowNum}</display:column>
                    <display:column title="Nama Jabatan" property="namaAgensi"/>
                    <c:if test="${fn:length(line.senaraiSalinan) == 0}">
                        <display:column title="Salinan Kepada">&nbsp;</display:column>
                    </c:if>
                    <c:if test="${line.senaraiSalinan ne ' '}">
                        <c:forEach items="${line.senaraiSalinan}" var="sal">
                            <display:column title="Salinan Kepada">${sal.kodAgensi.nama}</display:column>
                        </c:forEach>
                    </c:if>
                    <display:column title="Tarikh Hantar">
                        <fmt:formatDate value="${line.tarikhDisampai}" pattern="dd/MM/yyyy"/>
                    </display:column>
                    <display:column title="Tarikh Jangka Diterima">
                        <fmt:formatDate value="${line.tarikhJangkaTerima}" pattern="dd/MM/yyyy"/>
                    </display:column>
                    <display:column title="Tarikh Diterima" >
                        <fmt:formatDate value="${line.tarikhTerima}" pattern="dd/MM/yyyy"/>
                    </display:column>
                    <display:column title="Syor">
                    <!--  mengikut user, pilihan ini tidak sesuai - 16112011
                        <c:if test="${line.diSokong eq '1'}">
                            Boleh Diluluskan
                        </c:if>
                        <c:if test="${line.diSokong eq '2'}">
                            Tidak Diluluskan
                        </c:if>
                    -->
                        <c:if test="${line.diSokong eq '1'}">
                            Boleh Diluluskan
                        </c:if>
                        <c:if test="${line.diSokong eq '2'}">
                            Tidak Diluluskan
                        </c:if>
                        <c:if test="${line.diSokong eq '3'}">
                            Tiada Halangan
                        </c:if>
                        <c:if test="${line.diSokong eq '4'}">
                            Sesuai
                        </c:if>
                        <c:if test="${line.diSokong eq '5'}">
                            Tidak Sesuai
                        </c:if>
                        <c:if test="${line.diSokong eq '6'}">
                            Sokong
                        </c:if>     
                        <c:if test="${line.diSokong eq '7'}">
                            Tidak Disokong
                        </c:if> 
                        <c:if test="${line.diSokong eq '8'}">
                            Tidak Terima Ulasan
                        </c:if>    
                         
                    </display:column>
                    <display:column title="Mandatori">
                        <c:if test="${line.ulasanMandatori eq 'T'}">
                            Tidak Wajib
                        </c:if>
                        <c:if test="${line.ulasanMandatori eq 'Y'}">
                            Wajib
                        </c:if>
                    </display:column>        
                    <display:column title="Status">
                        <c:if test="${line.ulasan ne null}">
                            Lengkap
                        </c:if>
                        <c:if test="${line.ulasan eq null}">
                            <jsp:useBean id="now" class="java.util.Date"/>
                            <c:if test="${line.tarikhJangkaTerima gt now}">
                                Diproses
                            </c:if>
                            <c:if test="${line.tarikhJangkaTerima lt now}">
                                Tiada Ulasan
                            </c:if>
                        </c:if>
                    </display:column>
                          
                    <c:if test="${actionBean.stageId eq 'terima_ulasan_teknikal'}">
	                    <display:column title="Kemaskini">
                                <c:if test="${line.agensi.kategoriAgensi.kod eq 'ADN'}">
                                    <div align="center">
                                        <img alt='Adun' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                             id='edit_${line_rowNum}' onclick="editJabatanTeknikal('${line.idRujukan}')">
                                    </div>
                                </c:if>
                                <c:if test="${line.agensi.kategoriAgensi.kod eq 'JTK'}">
                                    <div align="center">
                                        <img alt='Jabatan Teknikal' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                             id='edit_${line_rowNum}' onclick="editJabatanTeknikal('${line.idRujukan}')"> 
                                    </div>
                                </c:if>	
                            </display:column>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG' || actionBean.permohonan.kodUrusan.kod eq 'PJLB' || actionBean.permohonan.kodUrusan.kod eq 'PPTR'}">
	                   <%--
                           <c:if test="${actionBean.stageId eq 'laporan_tanah'}">
	                        <display:column title="Kemaskini">
	                            <div align="center">    
	                                <img alt='Jabatan Teknikal' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
	                                     id='edit_${line_rowNum}' onclick="editJabatanTeknikal('${line.idRujukan}')">
	                            </div>
	                        </display:column>
	                    </c:if>
                          --%>
                        <c:if test="${actionBean.stageId eq 'sedia_surat_ulasan_pengawal' or actionBean.stageId eq 'laporan_tanah'}">
                            <display:column title="Kemaskini">
                                <c:if test="${line.agensi.kategoriAgensi.kod eq 'ADN'}">
                                    <div align="center">
                                        <img alt='Adun' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                             id='edit_${line_rowNum}' onclick="editJabatanTeknikal('${line.idRujukan}')">
                                    </div>
                                </c:if>
                                <c:if test="${line.agensi.kategoriAgensi.kod eq 'JTK'}">
                                    <div align="center">
                                        <img alt='Jabatan Teknikal' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                             id='edit_${line_rowNum}' onclick="editJabatanTeknikal1('${line.idRujukan}')">
                                    </div>
                                </c:if>	
                            </display:column>
                        </c:if>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'LMCRG' && actionBean.permohonan.kodUrusan.kod ne 'PJLB' && actionBean.permohonan.kodUrusan.kod ne 'PPTR'}">
                    	<c:if test="${actionBean.stageId ne 'terima_ulasan_teknikal'}">
                        	<display:column title="Kemaskini">
                                <c:if test="${line.agensi.kategoriAgensi.kod eq 'ADN'}">
                                    <div align="center">
                                        <img alt='Adun' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                             id='edit_${line_rowNum}' onclick="editJabatanTeknikal('${line.idRujukan}')">
                                    </div>
                                </c:if>
                                <c:if test="${line.agensi.kategoriAgensi.kod eq 'JTK'}">
                                    <div align="center">
                                        <img alt='Jabatan Teknikal' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                             id='edit_${line_rowNum}' onclick="editJabatanTeknikal('${line.idRujukan}')">
                                    </div>
                                </c:if>	
                            </display:column>
                    	</c:if>
                    </c:if>
                                
                </display:table>
            </div>
        </fieldset>
    </div>
 <%--   <div class="content" align="center">
        <fieldset class="aras1">
            <legend>
                Senarai Dokumen Yang Telah DiHantar
            </legend>
            <br>
            <display:table class="tablecloth" name="${actionBean.senaraiRujukanDok}" cellpadding="0" cellspacing="0" id="line">
                <display:column title="Bil" sortable="true">${line_rowNum}
                    <s:hidden name="x" class="x${line_rowNum -1}" value="${line.dokumen.idDokumen}"/>
                </display:column>
                <display:column title="Kod Dokumen">
                    ${line.dokumen.kodDokumen.kod}
                </display:column>
                <display:column title="Tajuk / No Rujukan">
                    ${line.dokumen.kodDokumen.nama}
                </display:column>
            </display:table>
        </fieldset>
    </div>--%>

</s:form>