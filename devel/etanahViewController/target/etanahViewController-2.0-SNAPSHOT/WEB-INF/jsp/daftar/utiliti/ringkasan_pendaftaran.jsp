<%--
    Document   : susun_perserahan
    Created on : Apr 9, 2010, 11:39:21 AM
    Author     : fikri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.tabs.js"></script>
<script type="text/javascript">    
    $(function() {
        $('#tab_ringkasan').tabs();
        $('#tab1').text('Senarai Hakmilik Terlibat');
        $('#tab2').text('Senarai..');
        $('#tab3').text('Senarai..');
        $('#tab4').text('Senarai..');
        $('#tab5').text('Senarai..');
        $('#tab6').text('Senarai..');
        $('#tab7').text('Senarai..');
        $('#tab8').text('Senarai..');

        $('#tab1').click(function () {
            $(this).text('Senarai Hakmilik Terlibat');
            $('#tab2').text('Senarai..');            
            $('#tab3').text('Senarai..');
            $('#tab4').text('Senarai..');
            $('#tab5').text('Senarai..');
            $('#tab6').text('Senarai..');
            $('#tab7').text('Senarai..');
            $('#tab8').text('Senarai..');
        });
        $('#tab2').click(function () {
            $('#tab1').text('Senarai..');
            $(this).text('Senarai Pemohon Terlibat');
            $('#tab3').text('Senarai..');
            $('#tab4').text('Senarai..');
            $('#tab5').text('Senarai..');
            $('#tab6').text('Senarai..');
            $('#tab7').text('Senarai..');
            $('#tab8').text('Senarai..');
        });
        $('#tab3').click(function () {
            $('#tab1').text('Senarai..');
            $(this).text('Senarai Pemohon Pihak Terlibat');
            $('#tab2').text('Senarai..');
            $('#tab4').text('Senarai..');
            $('#tab5').text('Senarai..');
            $('#tab6').text('Senarai..');
            $('#tab7').text('Senarai..');
            $('#tab8').text('Senarai..');
        });
        $('#tab4').click(function () {
            $('#tab1').text('Senarai..');
            $(this).text('Senarai Dokumen');
            $('#tab2').text('Senarai..');
            $('#tab3').text('Senarai..');
            $('#tab5').text('Senarai..');
            $('#tab6').text('Senarai..');
            $('#tab7').text('Senarai..');
            $('#tab8').text('Senarai..');
        });
        $('#tab5').click(function () {
            $('#tab1').text('Senarai..');
            $(this).text('Senarai Mohon Atas Pihak');
            $('#tab2').text('Senarai..');
            $('#tab4').text('Senarai..');
            $('#tab3').text('Senarai..');
            $('#tab6').text('Senarai..');
            $('#tab7').text('Senarai..');
            $('#tab8').text('Senarai..');
        });
        $('#tab6').click(function () {
            $('#tab1').text('Senarai..');
            $(this).text('Senarai Mohon Atas Urusan');
            $('#tab2').text('Senarai..');
            $('#tab4').text('Senarai..');
            $('#tab3').text('Senarai..');
            $('#tab5').text('Senarai..');
            $('#tab7').text('Senarai..');
            $('#tab8').text('Senarai..');
        });
        $('#tab7').click(function () {
            $('#tab1').text('Senarai..');
            $(this).text('Senarai Fasa');
            $('#tab2').text('Senarai..');
            $('#tab4').text('Senarai..');
            $('#tab3').text('Senarai..');
            $('#tab5').text('Senarai..');
            $('#tab6').text('Senarai..');
            $('#tab8').text('Senarai..');
        });
        $('#tab8').click(function () {
            $('#tab1').text('Senarai..');
            $(this).text('Senarai Mohon Rujukan Luar');
            $('#tab2').text('Senarai..');
            $('#tab4').text('Senarai..');
            $('#tab3').text('Senarai..');
            $('#tab5').text('Senarai..');
            $('#tab6').text('Senarai..');
            $('#tab7').text('Senarai..');
        });
    });
</script>
<div class="subtitile">

    <s:form beanclass="etanah.view.daftar.utiliti.RingkasanPendaftaranAction">
    <s:errors/>
    <s:messages/>
    <fieldset class="aras1">
        <legend>Carian Perserahan</legend>
        <p>
            <label>ID Perserahan :</label> <s:text name="idPermohonan"/>
        </p>
        <p>
            <label>&nbsp;</label> <s:submit name="search" value="Cari" class="btn"/>
        </p>
    </fieldset>
    <br/>

    <c:if test="${actionBean.permohonan ne null}">
        <fieldset class="aras1">
            <legend>Maklumat Perserahan</legend>
            <p>
                <label>ID Perserahan :</label>${actionBean.permohonan.idPermohonan}&nbsp;
            </p>
            <p>
                <label>Kod Urusan :</label>${actionBean.permohonan.kodUrusan.kod}&nbsp;
            </p>
            <p>
                <label>Urusan :</label>${actionBean.permohonan.kodUrusan.nama}&nbsp;
            </p>
            <p>
                <label>Keputusan :</label>${actionBean.permohonan.keputusan.nama}&nbsp;
            </p>
            <p>
                <label>Keputusan Oleh:</label>${actionBean.permohonan.keputusanOleh.nama}&nbsp;
            </p>
            <p>
                <label>Status Perserahan:</label>
                <c:if test="${actionBean.permohonan.status eq 'TA'}">
                    Tunggu Ambil
                </c:if>
                <c:if test="${actionBean.permohonan.status eq 'SL'}">
                    Selesai
                </c:if>
                <c:if test="${actionBean.permohonan.status eq 'TK'}">
                    Tidak Aktif
                </c:if>
                &nbsp;
            </p>
            <br/>
        </fieldset>
        <br/>


        <div id="tab_ringkasan">
            <ul>
                <li><a href="#senarai_hakmilik" id="tab1" class="tab">Senarai Hakmilik Terlibat</a></li>
                <li><a href="#senarai_pemohon" id="tab2" class="tab">Senarai Pemohon Terlibat</a></li>
                <li><a href="#senarai_pemohon_pihak" id="tab3" class="tab">Senarai Pemohon Pihak Terlibat</a></li>
                <li><a href="#senarai_mohon_atas_pihak" id="tab5" class="tab">Senarai Mohon Atas Pihak</a></li>
                <li><a href="#senarai_mohon_atas_urusan" id="tab6" class="tab">Senarai Mohon Atas Urusan</a></li>
                <li><a href="#senarai_fasa" id="tab7" class="tab">Senarai Mohon Fasa</a></li>
                <li><a href="#senarai_ruj_luar" id="tab8" class="tab">Senarai Mohon Rujuk Luar</a></li>
                <li><a href="#senarai_dokumen" id="tab4" class="tab">Senarai Dokumen</a></li>
            </ul>
            <div id="senarai_hakmilik">
                <fieldset class="aras1">
                    <legend>Senarai Hakmilik</legend>
                    <br/>
                    <div align="center">
                        <display:table class="tablecloth" name="${actionBean.permohonan.senaraiHakmilik}" cellpadding="0" 
                                       cellspacing="0" id="line" style="width:700px"
                                       requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                            <display:column title="Bil">${line_rowNum}</display:column>
                            <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                            <display:column title="Senarai Pihak">
                                <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="item">
                                        ${item.pihak.nama} (${item.jenis.nama}) - (${item.perserahan.kodUrusan.kod} - ${item.perserahan.idPerserahan}) <br/>
                                </c:forEach>
                            </display:column>
                        </display:table>
                    </div>
                    <br/>
                </fieldset>
            </div>
            <div id="senarai_pemohon">
                <fieldset class="aras1">
                    <legend>Senarai Pemohon</legend>
                    <br/>
                    <div align="center">
                        <display:table class="tablecloth" name="${actionBean.permohonan.senaraiPemohon}" cellpadding="0" 
                                       cellspacing="0" id="line1" style="width:700px"
                                       requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                            <display:column title="Bil">${line1_rowNum}</display:column>
                            <display:column property="pihak.idPihak" title="ID Pihak"/>
                            <display:column property="pihak.nama" title="Nama"/>
                        </display:table>
                    </div>
                    <br/>
                </fieldset>
            </div>
            <div id="senarai_pemohon_pihak">
                <fieldset class="aras1">
                    <legend>Senarai Pemohon Pihak</legend>
                    <br/>
                    <div align="center">
                        <display:table class="tablecloth" name="${actionBean.permohonan.senaraiPihak}" cellpadding="0" 
                                       cellspacing="0" id="line2" style="width:700px"
                                       requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                            <display:column title="Bil">${line2_rowNum}</display:column>
                            <display:column property="pihak.idPihak" title="ID Pihak"/>
                            <display:column property="pihak.nama" title="Nama"/>
                            <display:column property="hakmilik.idHakmilik" title="Hakmilik"/>
                            <display:column property="jenis.nama" title="Jenis Pihak"/>
                        </display:table>
                    </div>
                    <br/>
                </fieldset>
            </div>
            <div id="senarai_mohon_atas_pihak">
                <fieldset class="aras1">
                    <legend>Senarai Mohon Atas Pihak</legend>
                    <br/>
                    <div align="center">
                        <display:table class="tablecloth" name="${actionBean.permohonan.senaraiPermohonanAtasPihakBerkepentingan}"
                                       cellpadding="0" cellspacing="0" id="line4" style="width:700px"
                                       requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                            <display:column title="Bil">${line4_rowNum}</display:column>
                            <display:column property="pihakBerkepentingan.idHakmilikPihakBerkepentingan" title="ID Pihak"/>
                            <display:column property="pihakBerkepentingan.pihak.nama" title="Nama"/>                            
                        </display:table>
                    </div>
                    <br/>
                </fieldset>
            </div>
            <div id="senarai_mohon_atas_urusan">
                <fieldset class="aras1">
                    <legend>Senarai Mohon Atas Urusan</legend>
                    <br/>
                    <div align="center">
                        <display:table class="tablecloth" name="${actionBean.permohonan.senaraiPermohonanAtasPerserahan}"
                                       cellpadding="0" cellspacing="0" id="line5" style="width:700px"
                                       requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                            <display:column title="Bil">${line5_rowNum}</display:column>
                            <display:column property="urusan.kodUrusan.kod" title="Kod Urusan"/>
                            <display:column property="urusan.kodUrusan.nama" title="Urusan"/>
                            <display:column property="urusan.idPerserahan" title="Perserahan"/>
                        </display:table>
                    </div>
                    <br/>
                </fieldset>
            </div>
            <div id="senarai_fasa">
                <fieldset class="aras1">
                    <legend>Senarai Mohon Fasa</legend>
                    <br/>
                    <div align="center">
                        <display:table class="tablecloth" name="${actionBean.permohonan.senaraiFasa}"
                                       cellpadding="0" cellspacing="0" id="line6" style="width:700px"
                                       requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                            <display:column title="Bil">${line6_rowNum}</display:column>
                            <display:column property="idAliran" title="ID Aliran"/>
                            <display:column property="idPengguna" title="ID Pengguna"/>
                            <display:column property="keputusan.nama" title="Keputusan"/>
                            <display:column property="ulasan" title="Ulasan"/>
                            <display:column title="Tarikh Hantar">
                                <fmt:formatDate value="${line6.tarikhHantar}" pattern="dd/MM/yyyy"/>
                            </display:column>
                            <display:column property="mesej" title="Mesej"/>
                            <display:column title="Tarikh Kemaskini">
                                <fmt:formatDate value="${line6.infoAudit.tarikhKemaskini}" pattern="dd/MM/yyyy"/>
                            </display:column>
                        </display:table>
                    </div>
                    <br/>
                </fieldset>
            </div>
            <div id="senarai_ruj_luar">
                <fieldset class="aras1">
                    <legend>Senarai Mohon Rujukan Luar</legend>
                    <br/>
                    <div align="center">
                        <display:table class="tablecloth" name="${actionBean.permohonan.senaraiRujukanLuar}"
                                       cellpadding="0" cellspacing="0" id="line7" style="width:1000px"
                                       requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                            <display:column title="Bil">${line7_rowNum}</display:column>
                            <display:column property="kodPerintah.nama" title="Perintah"/>
                            <display:column property="namaSidang" title="Nama Sidang"/>
                            <display:column property="noSidang" title="No Sidang"/>
                            <display:column property="noFail" title="No Fail"/>
                            <display:column property="noRujukan" title="No Rujukan"/>
                            <display:column title="Tarikh Sidang">
                                <fmt:formatDate value="${line7.tarikhSidang}" pattern="dd/MM/yyyy"/>
                            </display:column>
                            <display:column title="Tarikh Rujukan">
                                <fmt:formatDate value="${line7.tarikhRujukan}" pattern="dd/MM/yyyy"/>
                            </display:column>
                            <display:column title="Tarikh KuatKuasa">
                                <fmt:formatDate value="${line7.tarikhKuatkuasa}" pattern="dd/MM/yyyy"/>
                            </display:column>
                            <display:column title="Tarikh Tamat">
                                <fmt:formatDate value="${line7.tarikhTamat}" pattern="dd/MM/yyyy"/>
                            </display:column>
                            <display:column title="Tempoh">
                                Tahun : ${line7.tempohTahun} Bulan :${line7.tempohBulan} Hari :${line7.tempohHari}
                            </display:column>
                            
                        </display:table>
                    </div>
                    <br/>
                </fieldset>
            </div>
            <div id="senarai_dokumen">
                <fieldset class="aras1">
                    <legend>Senarai Dokumen</legend>
                    <br/>
                    <div align="center">
                        <display:table class="tablecloth" name="${actionBean.permohonan.folderDokumen.senaraiKandungan}"
                                       cellpadding="0" cellspacing="0" id="line3" style="width:700px"
                                       requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                            <display:column title="Bil">${line3_rowNum}</display:column>
                            <display:column property="dokumen.kodDokumen.kod" title="Kod Dokumen"/>
                            <display:column property="dokumen.kodDokumen.nama" title="Dokumen"/>
                        </display:table>
                    </div>
                    <br/>
                </fieldset>
            </div>
        </div>
    </c:if>
</s:form>
</div>
