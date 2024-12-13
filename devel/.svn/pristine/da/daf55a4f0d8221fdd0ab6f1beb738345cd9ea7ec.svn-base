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

<s:form beanclass="etanah.view.stripes.pelupusan.utility.SemakStatusActionBean" id="cetak_semula">
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
                <%--
                <table align="center">
                    <tr align="center"><td>
                            <font style="text-transform:uppercase;" size="2"><b>
                                ${actionBean.permohonan.kodUrusan.nama}&nbsp;</b>
                                <br/>
                                ${actionBean.strKategoriPemohon}
                            </font>
                        </td> </tr>
                </table>
                --%>
                <p>
                    <label>Urusan :</label>
                    <font style="text-transform:uppercase;"> ${actionBean.permohonan.kodUrusan.nama} </font>&nbsp;
                </p>
                
                <p>
                    <label>Kategori Pemohon :</label>
                    <font style="text-transform:uppercase;"> ${actionBean.strKategoriPemohon}</font>&nbsp;
                </p>
                
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
                <display:table name="${actionBean.permohonan.senaraiHakmilik}" cellpadding="0" cellspacing="0" class="tablecloth" id="line" style="width:70%"
                               requestURI="${pageContext.request.contextPath}/consent/semak_status">
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column title="No Hakmilik"><font style="text-transform:uppercase;"><fmt:parseNumber value="${line.hakmilik.noHakmilik}"/></font></display:column>
                    <display:column title="No Lot"><font style="text-transform:uppercase;">${line.hakmilik.lot.nama} <fmt:parseNumber value="${line.hakmilik.noLot}"/></font></display:column>
                    <display:column title="Bandar/Pekan/Mukim"><font style="text-transform:uppercase;">${line.hakmilik.bandarPekanMukim.nama}</font></display:column>
                    <display:column title="Daerah"><font style="text-transform:uppercase;">${line.hakmilik.daerah.nama}</font></display:column>
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
                               requestURI="${pageContext.request.contextPath}/pelupusan/semak_status">
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
                                   requestURI="${pageContext.request.contextPath}/pelupusan/semak_status">
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
                               requestURI="${pageContext.request.contextPath}/pelupusan/semak_status">
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column title="ID Pengguna" property="idPengguna"  style="text-transform:uppercase;"/>
                    <display:column title="Tarikh Terima" property="infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                    <display:column title="Tarikh Selesai">
                        <%--<c:if test="${line.tarikhHantar ne null}"><fmt:formatDate pattern="dd/MM/yyyy hh:mm:ss" value="${line.tarikhHantar}"/> </c:if>
                        <c:if test="${line.tarikhHantar eq null}"> BELUM DISELESAIKAN </c:if>--%>
                        <c:if test="${line.idAliran ne actionBean.stage}"><fmt:formatDate pattern="dd/MM/yyyy hh:mm:ss" value="${line.tarikhHantar}"/> </c:if>
                        <c:if test="${line.idAliran eq actionBean.stage}"> BELUM DISELESAIKAN </c:if>
                    </display:column>
                    <display:column title="Tindakan">
                        <%--PBMT--%>
                        <c:if test="${line.idAliran eq 'kemasukan'}">Kemasukan Maklumat</c:if>
                        <c:if test="${line.idAliran eq 'agih_tugasPP'}">Agihan Tugas</c:if>
                        <c:if test="${line.idAliran eq 'g_charting_permohonan'}">Charting Permohonan</c:if>
                        <c:if test="${line.idAliran eq 'semak_charting'}">Semak Charting</c:if>
                        <c:if test="${line.idAliran eq 'minit_pptd'}">Minit PPTD</c:if>
                        <c:if test="${line.idAliran eq 'semak_tolak_awal'}">Semak Tolak Awal</c:if>
                        <c:if test="${line.idAliran eq 'semak_surat_tolak'}">Semak Surat Penolakan</c:if>
                        <c:if test="${line.idAliran eq 'agih_tugas_T1'}">Agihan Tugasl</c:if>
                        <c:if test="${line.idAliran eq 'g_charting_kpsn_T1'}">Charting Keputusan</c:if>
                        <c:if test="${line.idAliran eq 'semak_charting_kpsn_T1'}">Semakan Charting Keputusan</c:if>
                        <c:if test="${line.idAliran eq 'agih_tugasLT'}">Agihan Tugas</c:if>
                        <c:if test="${line.idAliran eq 'semak_laporan_tanah'}">Semakan Laporan Tanah</c:if>
                        <c:if test="${line.idAliran eq 'kenalpasti_jabatan_teknikal'}">Kenalpasti Jabatan Teknikal</c:if>
                        <c:if test="${line.idAliran eq 'terima_ulasan_teknikal'}">Terima Ulasan Teknikal</c:if>
                        <c:if test="${line.idAliran eq 'sedia_draf_mmk'}">Sedia Draf MMK</c:if>
                        <c:if test="${line.idAliran eq 'semak_draf_mmkSO'}">Semak Draf MMK</c:if>
                        <c:if test="${line.idAliran eq 'semak_draf_mmkKPT'}">Perakuan Draf MMK</c:if>
                        <c:if test="${line.idAliran eq 'terima_draf_MMKN_PTG'}">Terima Draf MMKN</c:if>
                        <c:if test="${line.idAliran eq 'semak_draf_MMKN_PTG'}">Semak Draf MMK</c:if>
                        <c:if test="${line.idAliran eq 'semak_draf_MMKN_PTG2'}">Semak Draf MMK</c:if>
                        <c:if test="${line.idAliran eq 'semak_syor_draf_MMKN_PTG'}">Semak Draf MMK</c:if>
                        <c:if test="${line.idAliran eq 'pindaan_draf_MMKN_PTG'}">Pindaan Draf MMKN</c:if>
                        <c:if test="${line.idAliran eq 'pindaan_draf_MMKN_PTD'}">Pindaan Draf MMK</c:if>
                        <c:if test="${line.idAliran eq 'semak_pindaan_MMKN_PTD'}">Semak Pindaan Draf MMKN</c:if>
                        <c:if test="${line.idAliran eq 'betul_pindaan_MMKN_PTD'}">Betulkan Pindaan Draf MMKN</c:if>
                        <c:if test="${line.idAliran eq 'semak_pindaan_MMKN_PTD2'}">Semak Pindaan Draf MMKN</c:if>
                        <c:if test="${line.idAliran eq 'semak_peraku_MMKN_PTD'}">Perakuan Draf MMK</c:if>
                        <c:if test="${line.idAliran eq 'pindaan_draf_MMKN_PTG'}">Pindaan Draf MMKN</c:if>
                        <c:if test="${line.idAliran eq 'peraku_draf_MMKN_PTG'}">Perakuan Kertas MMKN</c:if>
                        <c:if test="${line.idAliran eq 'rekod_keputusan_MMKN_PTG'}">Rekod Keputusan MMK</c:if>
                        <c:if test="${line.idAliran eq 'terima_keputusan_MMKN'}">Terima Keputusan MMKN</c:if>
                        <c:if test="${line.idAliran eq 'semak_agihan_tugas'}">Agih Tugasan Charting Keputusan</c:if>
                        <c:if test="${line.idAliran eq 'g_charting_keputusan'}">Charting Keputusan</c:if>
                        <c:if test="${line.idAliran eq 'semak_charting_keputusan'}">Semak Charting Keputusan</c:if>
                        <c:if test="${line.idAliran eq 'sedia_surat_tolak_MMK'}">Sedia Surat Penolakan</c:if>
                        <c:if test="${line.idAliran eq 'semak_surat_tolak_MMK'}">Semak Surat Penolakan</c:if>
                        <c:if test="${line.idAliran eq 'tndtgn_srt_tolak_MMK'}">Tandatangan Surat Tolak</c:if>
                        <c:if test="${line.idAliran eq 'terima_laporan_nilaian'}">Terima Laporan Nilaian</c:if>
                        <c:if test="${line.idAliran eq 'maklum_premium_hasil'}">Semakan Premium Hasil</c:if>
                        <c:if test="${line.idAliran eq 'sedia_borang_5A'}">Sedia Syarat Kelulusan Dan Borang 5A</c:if>
                        <c:if test="${line.idAliran eq 'semak_Brg5A'}">Semakan Syarat Kelulusan Dan Borang 5A</c:if>
                        <c:if test="${line.idAliran eq 'tandatangan_5a'}">Tandatangan Borang 5A</c:if>
                        <c:if test="${line.idAliran eq 'bayaran_5A'}">Bayaran 5A</c:if>
                        <c:if test="${line.idAliran eq 'qt_ft'}">Keputusan QT Atau FT</c:if>
                        <c:if test="${line.idAliran eq 'arahan_pu'}">Arahkan Penyediaan No PT</c:if>
                        <c:if test="${line.idAliran eq 'g_penyediaan_pu_pt'}">Penyediaan PU</c:if>
                        <c:if test="${line.idAliran eq 'semak_pu'}">Semak PU serta Pelan B2</c:if>
                        <c:if test="${line.idAliran eq 'arah_precomp'}">Arahkan Penyediaan No PT</c:if>
                        <c:if test="${line.idAliran eq 'g_penyediaan_pu'}">Penyediaan Surat PU</c:if>
                        <c:if test="${line.idAliran eq 'semak_precomp'}">Semak PU serta Pelan Precomp</c:if>
                        <c:if test="${line.idAliran eq 'pengecualian_ukur'}">Pengeculian Ukur</c:if>
                        <c:if test="${line.idAliran eq 'sdia_drf_pngcualian_ukr'}">Sedia Bebas Ukur</c:if>
                        <c:if test="${line.idAliran eq 'semak_drf_pngcualian_ukr'}">Semak Surat dan Sahkan PU</c:if>
                        <c:if test="${line.idAliran eq 'hntr_pu_ke_ptg'}">Hantar Sijil Pengecualian Bayaran Ukur</c:if>
                        <c:if test="${line.idAliran eq 'terima_pu_dari_ptd'}">Terima Sijil Pengecualian Bayaran Ukur</c:if>
                        <c:if test="${line.idAliran eq 'hantar_pu_ptd'}">"Hantar Sijil Pengecualian Bayaran Ukur</c:if>
                        <c:if test="${line.idAliran eq 'terima_pngcualian_ukur'}">Terima Sijil Pengecualian Bayaran Ukur</c:if>
                        <c:if test="${line.idAliran eq 'terima_tndtgn_pu'}">Terima Dan Tandatangan PU</c:if>
                        <c:if test="${line.idAliran eq 'hantar_pu_jupem'}">Sahkan dan Hantar PU ke JUPEM</c:if>
                        <c:if test="${line.idAliran eq 'agih_chart_hakmilik'}">Agih Charting Hakmilik</c:if>
                        <c:if test="${line.idAliran eq 'g_charting_hakmilik'}">Charting Hakmilik</c:if>
                        <c:if test="${line.idAliran eq 'semak_chart_hakmilik'}">Semakan Charting Hakmilik</c:if>
                        <c:if test="${line.idAliran eq 'g_terima_pa_b1'}">Terima PA B1</c:if>
                        <c:if test="${line.idAliran eq 'arahan_rekod_lot_index'}">Arahkan PPT Rekod Dalam Lot Index dan PT Index</c:if>
                        <c:if test="${line.idAliran eq 'rekod_lot_index'}">Rekod Dalam Lot Index dan PT Index</c:if>
                        <c:if test="${line.idAliran eq 'semak_rekod_lot_index'}">Semak Charting Lot</c:if>
                        <c:if test="${line.idAliran eq 'arahan_sedia_draf_mmk'}">Agihan Tugas Sedia Kertas MMK</c:if>
                        <c:if test="${line.idAliran eq 'sedia_draf_mmk2'}">Penyediaan Draf Kertas Pertimbangan MMK</c:if>
                        <c:if test="${line.idAliran eq 'semak_draf_mmk2_1'}">Semak Draf Kertas Pertimbangan MMK</c:if>
                        <c:if test="${line.idAliran eq 'semak_draf_mmk2_2'}">Semak Dan Peraku Kertas Pertimbangan MMK</c:if>
                        <c:if test="${line.idAliran eq 'huraian_syor_mmk2'}">Terima Draf MMK dan Sedia Huraian dan Syor</c:if>
                        <c:if test="${line.idAliran eq 'semak_huraian_syor2_1'}">Semak Huraian dan Syor</c:if>
                        <c:if test="${line.idAliran eq 'semak_huraian_syor2_2'}">Semak Huraian dan Syor</c:if>
                        <c:if test="${line.idAliran eq 'semak_huraian_syor2_3'}">Semak Huraian dan Syor</c:if>
                        <c:if test="${line.idAliran eq 'pindaan_draf_MMKN_PTG2'}">Pindaan Draf MMKN</c:if>
                        <c:if test="${line.idAliran eq 'pindaan_draf_MMKN_PTD3'}">Pindaan Draf MMK</c:if>
                        <c:if test="${line.idAliran eq 'semak_pindaan_MMKN_PTD3'}">Semak Pindaan Draf MMKN</c:if>
                        <c:if test="${line.idAliran eq 'betul_pindaan_MMKN_PTD3'}">Betulkan Pindaan Draf MMKN</c:if>
                        <c:if test="${line.idAliran eq 'semak_pindaan_MMKN_PTD3_1'}">Semak Pindaan Draf MMKN</c:if>
                        <c:if test="${line.idAliran eq 'semak_peraku_MMKN_PTD3'}">Perakuan Draf MMK</c:if>
                        <c:if test="${line.idAliran eq 'perakuan_ptg_kertas_mmk2'}">Perakuan Risalat MMK</c:if>
                        <c:if test="${line.idAliran eq 'rekod_keputusan_MMK2'}">Rekod Keputusan MMK</c:if>
                        <c:if test="${line.idAliran eq 'terima_keputusan_tmbhn2'}">Terima Keputusan MMK Dan Arahkan Tuntutan Bayaran</c:if>
                        <c:if test="${line.idAliran eq 'agih_tugas_tmbhn2'}">Agih Tugasan Charting Keputusan</c:if>
                        <c:if test="${line.idAliran eq 'g_charting_kpsn_tmbhn2'}">Charting Keputusan</c:if>
                        <c:if test="${line.idAliran eq 'semak_charting_kpsn_tmbhn2'}">Semak Charting Keputusan</c:if>
                        <c:if test="${line.idAliran eq 'sdia_drf_pngcualian_ukr'}">Sedia Bebas Ukur</c:if>
                        <c:if test="${line.idAliran eq 'trm_lprn_nlaian_tmbhn2'}">Kiraan Semula Premium dan Nilaian</c:if>
                        <c:if test="${line.idAliran eq 'mklm_prmium_hsil_tmbhn2'}">Semak Cukai</c:if>
                        <c:if test="${line.idAliran eq 'sedia_srt_byrn_tmbhn2'}">Sedia Surat Tuntut Bayaran Tambahan</c:if>
                        <c:if test="${line.idAliran eq 'semak_srt_byrn_tmbhn2'}">Semakan Surat Tuntut Bayaran Tambahan</c:if>
                        <c:if test="${line.idAliran eq 'tndtgn_srt_byrn_tmbhn2'}">Sedia Surat Tuntut Bayaran Tambahan</c:if>
                        <c:if test="${line.idAliran eq 'bayaran_tambahan2'}">Bayaran 5A</c:if>
                        <c:if test="${line.idAliran eq 'arah_precomp_tmbhn2'}">Arahkan Penyediaan Pindaan PU</c:if>
                        <c:if test="${line.idAliran eq 'g_pindaan_pu_tmbhn2'}">Pindaan PU</c:if>
                        <c:if test="${line.idAliran eq 'semak_pindaan_pu_tmbhn2'}">Semak Pindaan PU</c:if>
                        <%--MMMCL--%>
                        <c:if test="${line.idAliran eq 'agihan_tugas'}">Agihan Tugas</c:if>
                        <c:if test="${line.idAliran eq 'semakan_laporan_tanah'}">Semakan Laporan Tanah</c:if>
                        <c:if test="${line.idAliran eq 'sedia_rencana'}">Sedia Draf Rencana</c:if>
                        <c:if test="${line.idAliran eq 'semak_rencana'}">Semak Draf Rencana</c:if>
                        <c:if test="${line.idAliran eq 'perakuan_ptd'}">Keputusan Pentadbir Tanah</c:if>
                        <c:if test="${line.idAliran eq 'agihan_tugas_kpsn'}">Agihan Tugas Keputusan</c:if>
                        <c:if test="${line.idAliran eq 'semak_charting_kpsn'}">Semakan Charting</c:if>
                        <c:if test="${line.idAliran eq 'sedia_surat_tolak'}">Sedia Surat Penolakan</c:if>
                        <c:if test="${line.idAliran eq 'tahun_hm_daftar'}">Tahun Hakmilik Daftar</c:if>
                        <c:if test="${line.idAliran eq 'sedia_surat_penilaian'}">Dapatkan Penilaian Tanah</c:if>
                        <c:if test="${line.idAliran eq 'surat_kelulusan_brg5A1'}">Sedia Surat Kelulusan dan Borang 5A</c:if>
                        <c:if test="${line.idAliran eq 'surat_kelulusan_brg5A'}">Sedia Surat Kelulusan dan Borang 5A</c:if>
                        <c:if test="${line.idAliran eq 'bayaran_brg5A'}">Bayaran Kelulusan 5A</c:if>
                        <c:if test="${line.idAliran eq 'maklum_bayaran'}">Maklum Bayaran</c:if>
                        <c:if test="${line.idAliran eq 'sedia_jadual'}">Sedia Jadual Hakmilik</c:if>
                        <c:if test="${line.idAliran eq 'agihan_tugas_lot'}">Agihan Tugas</c:if>
                        <c:if test="${line.idAliran eq 'g_charting_lot'}">Charting Kemaskini</c:if>
                        <c:if test="${line.idAliran eq 'semak_charting_lot'}">Semakan Charting Kemaskini</c:if>
                         <%--PLPS--%>
                        <c:if test="${line.idAliran eq 'minit_syor_tolak_awal'}">Minit Syor Penolakan Awal</c:if>
                        <c:if test="${line.idAliran eq 'tandatangan_surat_tolak'}">Tandatangan Surat Penolakan</c:if>
                        <c:if test="${line.idAliran eq 'agihan_tugas5'}">Agihan Tugas</c:if>
                        <c:if test="${line.idAliran eq 'g_kemaskini_charting'}">Kemaskini Maklumat Charting</c:if>
                        <c:if test="${line.idAliran eq 'agihan_tugas2'}">Agihan Tugas</c:if>
                        <c:if test="${line.idAliran eq 'laporan_tanah'}">Sedia Laporan Tanah</c:if>
                        <c:if test="${line.idAliran eq 'sedia_draf_mmkn'}">Sedia Draf MMKN</c:if>
                        <c:if test="${line.idAliran eq 'semak_draf_mmkn2'}">Semak Draf MMKN</c:if>
                        <c:if test="${line.idAliran eq 'perakuan_ptd_risalat_mmkn'}">Perakuan Risalat MMKN</c:if>
                        <c:if test="${line.idAliran eq 'terima_semak_risalat_mmkn'}">Terima dan Semak Risalat MMKN</c:if>
                        <c:if test="${line.idAliran eq 'semak_draf_mmkn3_1'}">Semakan Draf MMKN</c:if>
                        <c:if test="${line.idAliran eq 'semak_draf_mmkn3_2'}">Semakan Draf MMKN</c:if>
                        <c:if test="${line.idAliran eq 'semak_draf_mmkn3_3'}">Semakan Draf MMKN</c:if>
                        <c:if test="${line.idAliran eq 'pindaan_draf_mmkn'}">Pindaan Draf MMKN</c:if>
                        <c:if test="${line.idAliran eq 'perakuan_ptg_risalat_mmkn'}">Perakuan Risalat MMKN</c:if>
                        <c:if test="${line.idAliran eq 'rekod_keputusan_mmkn'}">Rekod Keputusan MMKN</c:if>
                        <c:if test="${line.idAliran eq 'terima_keputusan_mmkn'}">Terima Keputusan MMKN</c:if>
                        <c:if test="${line.idAliran eq 'agihan_tugas3'}">Agihan Tugas</c:if>
                        <c:if test="${line.idAliran eq 'sedia_surat_tolak2'}">Sedia Surat Penolakan</c:if>
                        <c:if test="${line.idAliran eq 'semak_surat_tolak2'}">Semak Surat Penolakan</c:if>
                        <c:if test="${line.idAliran eq 'tandatangan_surat_tolak2'}">Tandatangan Surat Penolakan</c:if>
                        <c:if test="${line.idAliran eq 'sedia_surat_kelulusan'}">Sedia Surat Kelulusan</c:if>
                        <c:if test="${line.idAliran eq 'semak_surat_kelulusan'}">Semak Surat Kelulusan</c:if>
                        <c:if test="${line.idAliran eq 'tandatangan_surat_kelulusan'}">Tandatangan Surat Kelulusan</c:if>
                        <c:if test="${line.idAliran eq 'bayaran_lesen'}">Terima Bayaran</c:if>
                        <c:if test="${line.idAliran eq 'sedia_borang_4a'}">Sedia Borang 4A</c:if>
                        <c:if test="${line.idAliran eq 'semak_borang_4a'}">Semak Borang 4A</c:if>
                        <c:if test="${line.idAliran eq 'agihan_tugas4'}">Agihan Tugas</c:if>
                        <c:if test="${line.idAliran eq 'g_charting_4a'}">Charting 4A</c:if>
                        <c:if test="${line.idAliran eq 'g_semak_permit'}">Semak Charting 4A</c:if>
                        <c:if test="${line.idAliran eq 'tandatangan_borang_4a'}">Tandatangan Borang 4A</c:if>
                        <%--PTGSA--%>
                        <c:if test="${line.idAliran eq '01Kemasukan'}">Kemasukan Permohonan</c:if>
                        <c:if test="${line.idAliran eq '02AgihanTugas'}">Agihan Tugas</c:if>
                        <c:if test="${line.idAliran eq 'g_charting_pemohonan'}">Sedia Charting dan Laporan Pelan</c:if>
                        <c:if test="${line.idAliran eq '03ArahLaporanTanah'}">Agihan Tugas</c:if>
                        <c:if test="${line.idAliran eq '04SediaLaporanTanah'}">Laporan Tanah</c:if>
                        <c:if test="${line.idAliran eq '05SemakLaporan'}">Semakan Laporan Tanah</c:if>
                        <c:if test="${line.idAliran eq '05ASediaSuratJabTek'}">Kenalpasti Jabatan Teknikal dan YB ADUN</c:if>
                        <c:if test="${line.idAliran eq '06SediaDrafMMK'}">Sedia Draf Mesyuarat MMK</c:if>
                        <c:if test="${line.idAliran eq '08SemakSyorMMK'}">Semak dan syor Draf Mesyuarat MMK</c:if>
                        <c:if test="${line.idAliran eq '09PerakuMMK'}">Peraku Draf Mesyuarat MMK</c:if>
                        <c:if test="${line.idAliran eq '10TerimaDraf'}">Terima Draf Mesyuarat MMK</c:if>
                        <c:if test="${line.idAliran eq '10aSemakDraf'}">Semakan Draf Mesyuarat MMK</c:if>
                        <c:if test="${line.idAliran eq '11SemakDraf'}">Semakan Draf Mesyuarat MMK</c:if>
                        <c:if test="${line.idAliran eq '13SemakSyorDraf'}">Semak dan Syor Draf Mesyuarat MMK</c:if>
                        <c:if test="${line.idAliran eq '13bPindaanPTG'}">Semak dan Syor Draf Mesyuarat MMK</c:if>
                        <c:if test="${line.idAliran eq '14aTerimaPindaanPTG'}">Pindaan Draf MMK dari PTG</c:if>
                        <c:if test="${line.idAliran eq '14bSemakPindaan'}">Pindaan Draf MMK dari PTG</c:if>
                        <c:if test="${line.idAliran eq '14cPindaanMMK'}">Pindaan Draf MMK dari PTG</c:if>
                        <c:if test="${line.idAliran eq '14dSemakPindaan'}">Pindaan Draf MMK dari PTG</c:if>
                        <c:if test="${line.idAliran eq '14ePerakuPindaan'}">Pindaan Draf MMK dari PTG</c:if>
                        <c:if test="${line.idAliran eq '14PerakuMMK'}">Perakuan Risalat MMKN</c:if>
                        <c:if test="${line.idAliran eq 'RekodKeputusanMMK'}">Rekod Keputusan MMKN</c:if>
                        <c:if test="${line.idAliran eq '15SediaDrafWarta'}">Penyediaan Draf Warta</c:if>
                        <c:if test="${line.idAliran eq '16SemakDraf'}">Semakan Draf Warta</c:if>
                        <c:if test="${line.idAliran eq '17SahkanDraf'}">Semak dan Peraku Draf Warta</c:if>
                        <c:if test="${line.idAliran eq '18TerimaWartaPNB'}">Terima Warta</c:if>
                        <c:if test="${line.idAliran eq '19TerimaSemakWarta'}">Terima dan Semak Warta</c:if>
                        <c:if test="${line.idAliran eq '19AMaklumanWarta'}">Terima dan Semak Warta</c:if>
                        <c:if test="${line.idAliran eq '20AgihanTugas'}">Agihan Tugas</c:if>
                        <c:if test="${line.idAliran eq '20bSemakanCharting'}">Semakan Charting</c:if>
                        <c:if test="${line.idAliran eq '20CetakHantarWarta'}">Cetak dan Hantar Warta</c:if>
                        <c:if test="${line.idAliran eq '21TerimaKPSNMMK'}">Terima Keputusan MMKN</c:if>
                        <c:if test="${line.idAliran eq '22MaklumanKPSNMMK'}">Makluman Keputusan MMKN</c:if>
                        <c:if test="${line.idAliran eq '23SediaSuratTolak'}">Sedia Surat Tolak</c:if>
                        <c:if test="${line.idAliran eq '20_1AgihanTugas'}">Agihan Tugas</c:if>
                        <c:if test="${line.idAliran eq '01g_charting_keputusan'}">Charting Keputusan</c:if>
                        <c:if test="${line.idAliran eq '20_cSemakanCharting'}">Semakan Charting</c:if>
                        <%--PRMP--%>
                        <c:if test="${line.idAliran eq 'sejarah_permohonan_syor_tolak'}">Sejarah Permohonan Syor Tolak</c:if>
                        <c:if test="${line.idAliran eq 'cadangan_tolak_ringkas'}">Cadangan Tolak Ringkas</c:if>
                        <c:if test="${line.idAliran eq 'tolak_ringkas'}">Keputusan Tolak Ringkas</c:if>
                        <c:if test="${line.idAliran eq 'sedia_draf_rencana_mmkn'}">Sedia Draf Rencana MMKN</c:if>
                        <c:if test="${line.idAliran eq 'semak_draf_rencana_mmkn1'}">Semak Draf Rencana MMKN</c:if>
                        <c:if test="${line.idAliran eq 'semak_draf_rencana_mmkn2'}">Semak Draf Rencana MMKN</c:if>
                        <c:if test="${line.idAliran eq 'perakuan_draf_rencana_mmkn'}">Perakuan Draf Rencana MMKN</c:if>
                        <c:if test="${line.idAliran eq 'semak_draf_mmkn1'}">Semakan Risalat MMKN</c:if>
                        <c:if test="${line.idAliran eq 'semak_draf_mmkn2'}">Semakan Draf MMKN</c:if>
                        <c:if test="${line.idAliran eq 'semak_draf_mmkn3'}">Semakan Draf MMKN</c:if>
                        <c:if test="${line.idAliran eq 'pindaan_draf_mmkn'}">Pindaan Draf MMKN</c:if>
                        <c:if test="${line.idAliran eq 'perakuan_ptg_risalat_mmkn'}">Perakuan PTG Risalat MMKN</c:if>
                        <c:if test="${line.idAliran eq 'arahan_tugasan3'}">Arahan Tugasan</c:if>
                        <c:if test="${line.idAliran eq 'terima_bayaran'}">Terima Bayaran</c:if>
                        <c:if test="${line.idAliran eq 'sedia_permit'}">Sedia Permit</c:if>
                        <c:if test="${line.idAliran eq 'tandatangan_permit'}">Tandatangan Permit</c:if>
                        <%--PBPTD--%>
                        <c:if test="${line.idAliran eq 'semak_draf_rencana_ptd1'}">Semak Draf Rencana PTD</c:if>
                        <c:if test="${line.idAliran eq 'semak_draf_rencana_ptd2'}">Semak Draf Rencana PTD</c:if>
                        <c:if test="${line.idAliran eq 'perakuan_draf_rencana_ptd'}">Perakuan Draf Rencana PTD</c:if>
                        <c:if test="${line.idAliran eq 'sedia_surat_lulustolak'}">Sedia Surat Lulus/Tolak</c:if>
                        <c:if test="${line.idAliran eq 'semak_surat_lulus'}">Semakan Surat Kelulusan</c:if>
                        <c:if test="${line.idAliran eq 'terima_cagaran'}">Terima Bayaran</c:if>
                        <c:if test="${line.idAliran eq 'rekodbayaran_sediapermit'}">Sedia Permit</c:if>
                        <c:if test="${line.idAliran eq 'semak_permit'}">Semak Permit</c:if>
                        <c:if test="${line.idAliran eq 'g_charting_kelulusan'}">Charting Kelulusan</c:if>
                        <c:if test="${line.idAliran eq 'semak_charting_kelulusan'}">Semak Charting Kelulusan</c:if>
                        <c:if test="${line.idAliran eq 'rekod_permit_cetak'}">Rekod Cetakan Permit</c:if>
                        <c:if test="${line.idAliran eq 'makluman_penguatkuasa'}">Makluman Penguatkuasaan</c:if>
                        <c:if test="${line.idAliran eq 'terima_surat_tuntut_cagaran'}">Terima Surat Tuntut Cagaran</c:if>
                        <c:if test="${line.idAliran eq 'agihan_tugas6'}">Agihan Tugas</c:if>
                        <c:if test="${line.idAliran eq 'sedia_laporan_pantau'}">Sedia Laporan</c:if>
                        <c:if test="${line.idAliran eq 'semak_laporan_pantau'}">Semak Laporan</c:if>
                        <c:if test="${line.idAliran eq 'semak_syor'}">Semak Syor</c:if>
                        <c:if test="${line.idAliran eq 'keputusan_ptd'}">Keputusan PTD</c:if>
                        <c:if test="${line.idAliran eq 'semakan_surat_pulang'}">Semakan Surat Pulang</c:if>
                        <c:if test="${line.idAliran eq 'pulang_wang_cagaran'}">Pulang Wang Cagaran</c:if>
                        <c:if test="${line.idAliran eq 'semakan_arahan_tolak'}">Semakan Arahan Tolak</c:if>
                        <c:if test="${line.idAliran eq 'semak_cetak_surat'}">Keluar Surat</c:if>
                        <%-- PBPTG--%>
                        <c:if test="${line.idAliran eq 'semak_draf_rencana_ptg2'}">Semak Draf Rencana PTG</c:if>
                        <c:if test="${line.idAliran eq 'perakuan_draf_rencana_ptg'}">Perakuan Draf Rencana PTG</c:if>
                        <c:if test="${line.idAliran eq 'sedia_rencana_syor'}">Sedia Rencana Syor</c:if>
                        <c:if test="${line.idAliran eq 'semakan_rencana1'}">Semak Rencana Syor</c:if>
                        <c:if test="${line.idAliran eq 'semakan_rencana1a'}">Semak Rencana Syor</c:if>
                        <c:if test="${line.idAliran eq 'semakan_rencana2'}">Semak Rencana Syor</c:if>
                        <c:if test="${line.idAliran eq 'semak_peraku'}">Semak dan Peraku Rencana Syor</c:if>
                        <c:if test="${line.idAliran eq 'terima_keputusan_ptg'}">Terima Permohonan PTG</c:if>
                        <c:if test="${line.idAliran eq 'semak_draf_rencana_ptg1'}">Semak Draf Rencana PTG</c:if>
                        <c:if test="${line.idAliran eq 'semak_surat_lulus'}">Semakan Surat Lulus</c:if>
                        <c:if test="${line.idAliran eq 'rekod_bayaran_sediapermit'}">Sedia Permit</c:if>
                        <c:if test="${line.idAliran eq 'maklum_penguatkuasa'}">Makluman Penguatkuasaan</c:if>
                        <c:if test="${line.idAliran eq 'agihan_tugas7'}">Agihan Tugas</c:if>
                        <c:if test="${line.idAliran eq 'cetak_surat_rampas'}">Cetak Surat Rampas</c:if>
                        <%--PRIZ--%>
                        <c:if test="${line.idAliran eq 'salinan_surat_keputusan1'}">Salinan Surat Keputusan</c:if>
                        <c:if test="${line.idAliran eq 'salinan_surat_keputusan2'}">Terima Pelan Akui</c:if>
                        <c:if test="${line.idAliran eq 'agihan_tugasan4'}">Semakan Maklumat</c:if>
                        <c:if test="${line.idAliran eq 'hantar_surat_kelulusan'}">Hantar Surat Kelulusan</c:if>
                        <c:if test="${line.idAliran eq 'ada_pa'}">Semakan PA</c:if>
                        <c:if test="${line.idAliran eq 'sedia_bebas_byrn_ukur'}">Sedia Bebas Ukur</c:if>
                        <c:if test="${line.idAliran eq 'tandatangan_surat'}">Tandatangan Surat dan Sahkan PU</c:if>
                        <c:if test="${line.idAliran eq 'hntr_pu_bebas_ukur'}">Hantar Sijil Pengecualian Bayaran Ukur</c:if>
                        <c:if test="${line.idAliran eq 'trm_bebas_byrn_ukur'}">Terima Sijil Pengecualian Bayaran Ukur</c:if>
                        <c:if test="${line.idAliran eq 'ttgn_bebas_byrn_ukur'}">Tandatangan Sijil Pengecualian Bayaran Ukur</c:if>
                        <c:if test="${line.idAliran eq 'hntr_bebas_byrn_ukur'}">Hantar Sijil Pengecualian Bayaran Ukur</c:if>
                        <c:if test="${line.idAliran eq 'trm_bebas_byrn_ukur2'}">Terima Sijil Pengecualian Bayaran Ukur</c:if>
                        <c:if test="${line.idAliran eq 'sedia_surat_pa_pw'}">Sedia Surat</c:if>
                        <c:if test="${line.idAliran eq 'tandatangan_surat2'}">Semak dan Tandatangan Surat</c:if>
                        <c:if test="${line.idAliran eq 'g_hantar_pu'}">Penghantaran PU</c:if>
                        <c:if test="${line.idAliran eq 'terima_pa_pw'}">Terima PA Dan PW</c:if>
                        <c:if test="${line.idAliran eq 'minit_pptkanan'}">Minit Ke Penolong Pegawai Tanah Kanan</c:if>
                        <c:if test="${line.idAliran eq 'g_charting'}">Charting Kemaskini</c:if>
                        <c:if test="${line.idAliran eq 'semak_charting'}">Semakan Charting Kemaskini</c:if>
                        <c:if test="${line.idAliran eq 'sedia_surat'}">Penyediaan Surat</c:if>
                        <c:if test="${line.idAliran eq 'tandatangan_surat3'}">Tandatangan Surat</c:if>
                        <c:if test="${line.idAliran eq 'hantar_surat_ptg'}">Hantar Surat ke PTG</c:if>
                        <c:if test="${line.idAliran eq 'terima_pa_pw_ptg'}">Terima PA Dan PW</c:if>
                        <c:if test="${line.idAliran eq 'sedia_draf_warta'}">Sedia Draf Warta</c:if>
                        <c:if test="${line.idAliran eq 'semak_draf_warta'}">Semak Draf Warta</c:if>
                        <c:if test="${line.idAliran eq 'terima_warta_rizab'}">Terima Warta Rizab</c:if>
                        <c:if test="${line.idAliran eq 'terima_warta_rizab_ptg'}">Terima Warta Rizab</c:if>
                        <c:if test="${line.idAliran eq 'g_charting_warta'}">Charting Kelulusan</c:if>
                        <c:if test="${line.idAliran eq 'semak_charting_warta'}">Semakan Charting Kelulusan</c:if>
                        <c:if test="${line.idAliran eq 'sedia_surat2'}">Sedia Surat Kepada Pihak Berkepentingan</c:if>
                        <c:if test="${line.idAliran eq 'tandatangan_surat4'}">Tandatangan Surat</c:if>
                        <c:if test="${line.idAliran eq 'hantar_surat'}">Hantar Surat kepada Pihak Berkepentingan</c:if>
                        <%--PPBB--%>
                        <c:if test="${line.idAliran eq 'semak_draf_rencana_km2'}">Semakan Draf Rencana Ketua Menteri</c:if>
                        <c:if test="${line.idAliran eq 'perakuan_draf_rencana_km'}">Perakuan Draf Rencana Ketua Menteri</c:if>
                        <c:if test="${line.idAliran eq 'terima_permohonan_ptd'}">Terima Permohonan PTD</c:if>
                        <c:if test="${line.idAliran eq 'arahan_tugasan4'}">Semak Permohonan dan Arahan Tugasan</c:if>
                        <c:if test="${line.idAliran eq 'sedia_draf_jkbb'}">Sedia Draf JKBB</c:if>
                        <c:if test="${line.idAliran eq 'semak_rencana_jkbb'}">Semak Rencana JKBB</c:if>
                        <c:if test="${line.idAliran eq 'semak_rencana_jkbb2'}">Semakan Rencana JKBB</c:if>
                        <c:if test="${line.idAliran eq 'perakuan_rencana_jkbb'}">Perakuan Rencana JKBB</c:if>
                        <c:if test="${line.idAliran eq 'sedia_dokumen'}">Sedia Dokumen</c:if>
                        <c:if test="${line.idAliran eq 'rekod_keputusan_jkbb'}">Rekod Keputusan</c:if>
                        <c:if test="${line.idAliran eq 'terima_permohonan_ptd2'}">Terima Permohonan PTD</c:if>
                        <c:if test="${line.idAliran eq 'arahan_tugasan4a'}">Semak Permohonan dan Arahan Tugasan</c:if>
                        <c:if test="${line.idAliran eq 'sedia_draf_km'}">Sedia Draf Ketua Menteri</c:if>
                        <c:if test="${line.idAliran eq 'semak_rencana_km'}">Semak Rencana Ketua Menteri</c:if>
                        <c:if test="${line.idAliran eq 'sedia_dokumen2'}">Sedia Dokumen</c:if>
                        <c:if test="${line.idAliran eq 'semak_rencana_km2'}">Semakan Rencana Ketua Menteri</c:if>
                        <c:if test="${line.idAliran eq 'perakuan_rencana_km'}">Perakuan Rencana Ketua Menteri</c:if>
                        <c:if test="${line.idAliran eq 'rekod_keputusan_km'}">Rekod Keputusan Ketua Menteri</c:if>
                        <c:if test="${line.idAliran eq 'terima_keputusan'}">Terima Keputusan</c:if>
                        <c:if test="${line.idAliran eq 'agihan_tugasan7'}">Agihan Tugasan</c:if>
                        <c:if test="${line.idAliran eq 'sedia_borang_4c'}">Sedia Borang 4C</c:if>
                        <c:if test="${line.idAliran eq 'semak_borang_4c'}">Semak Borang 4C</c:if>
                        <c:if test="${line.idAliran eq 'agihan_tugas8'}">Agihan Tugas</c:if>
                        <c:if test="${line.idAliran eq 'tandatangan_borang_4c'}">Tandatangan Borang 4C</c:if>
                        <c:if test="${line.idAliran eq 'makluman_kuatkuasa'}">Makluman Penguatkuasaan</c:if>
                        <%--PHLP--%>
                        <c:if test="${line.idAliran eq '01Kmskn'}">Kemasukan Maklumat</c:if>
                        <c:if test="${line.idAliran eq '02AghnTgs'}">Semakan dan Agihan Tugasan</c:if>
                        <c:if test="${line.idAliran eq '04Smkn'}">Semakan Laporan Pelukis Pelan</c:if>
                        <c:if test="${line.idAliran eq '05SedSrtPnlkn'}">Penyediaan Surat Penolakan</c:if>
                        <c:if test="${line.idAliran eq '06SmkCtkdanTndtngn'}">Semakan Surat Penolakan</c:if>
                        <c:if test="${line.idAliran eq '07SdknLapTnh'}">Sedia Laporan Tanah</c:if>
                        <c:if test="${line.idAliran eq '08SmknLapTnh'}">Semakan Laporan Tanah</c:if>
                        <c:if test="${line.idAliran eq '10SmkCtkdanTndtngn'}">Semakan Jabatan Teknikal</c:if>
                        <c:if test="${line.idAliran eq '11TrmdanRkd'}">Terima Laporan Nilaian</c:if>
                        <c:if test="${line.idAliran eq '12TtpTrkh'}">Penyediaan Notis 2A</c:if>
                        <c:if test="${line.idAliran eq '13SedNotis2A'}">Penyediaan Notis 2A</c:if>
                        <c:if test="${line.idAliran eq '14SmkdanJana'}">Semakan Notis 2A</c:if>
                        <c:if test="${line.idAliran eq '15CtkdanSrh'}">Hantar Notis 2A</c:if>
                        <c:if test="${line.idAliran eq '16ByrnSiasatan'}">Bayaran Siasatan</c:if>
                        <c:if test="${line.idAliran eq '17SedDokSiasatan'}">Siasatan Dan Perintah</c:if>
                        <c:if test="${line.idAliran eq '18KmsknMklmtSiasatan'}">Siasatan Dan Perintah</c:if>
                        <c:if test="${line.idAliran eq '19SmkdanSyor'}">Semakan Siasatan Dan Perintah</c:if>
                        <c:if test="${line.idAliran eq '20BtPrnth'}">Siasatan Dan Perintah</c:if>
                        <c:if test="${line.idAliran eq '21AghnTgs'}">Agihan Tugas</c:if>
                        <c:if test="${line.idAliran eq '23Smk'}">Semakan Charting</c:if>
                        <c:if test="${line.idAliran eq '24SedSrtPnlkn'}">Penyediaan Surat Penolakan</c:if>
                        <c:if test="${line.idAliran eq '25SmkdanCtkTndtngn'}">Semakan Surat Penolakan</c:if>
                        <c:if test="${line.idAliran eq '26SedSrt'}">Surat Pampasan</c:if>
                        <c:if test="${line.idAliran eq '27SmkdanCtkTndtngn'}">Semakan Surat Pampasan</c:if>
                        <c:if test="${line.idAliran eq '28SedSrt'}">Sedia Surat Terima Bayaran</c:if>
                        <c:if test="${line.idAliran eq '29SmkCtkdanTndtngn'}">Semak Surat Terima Bayaran</c:if>
                        <c:if test="${line.idAliran eq '30SmkCtkTndtngn'}">Semak Surat Terima Bayaran</c:if>
                        <c:if test="${line.idAliran eq '31AghnTgs'}">Agihan Tugasan</c:if>
                        <c:if test="${line.idAliran eq '32ChrtgKelulusan'}">Charting Kelulusan</c:if>
                        <c:if test="${line.idAliran eq '33Smk'}">Semakan Charting</c:if>
                        <c:if test="${line.idAliran eq '34TrmDHKK'}">Terima DHKK dan Sedia Borang 28B</c:if>
                        <c:if test="${line.idAliran eq '35SmkdanCtkTndtngn'}">Semak dan Tandatangan Borang 28B</c:if>
                        <c:if test="${line.idAliran eq 'A06NotingHLLA'}">Endorsan</c:if>
                        <c:if test="${line.idAliran eq '36LS'}">LS</c:if>
                        <c:if test="${line.idAliran eq 'B01SediaDrafSijil'}">Sediakan sijil Pembebasan Ukur</c:if>
                        <c:if test="${line.idAliran eq 'B02SemakSijil'}">Semak sijil Pembebasan Ukur</c:if>
                        <c:if test="${line.idAliran eq 'B03HantarDrafSijil'}">Hantar sijil Pembebasan Ukur</c:if>
                        <c:if test="${line.idAliran eq 'B04DTerimaSijil'}">Terima sijil Pembebasan Ukur</c:if>
                        <c:if test="${line.idAliran eq 'B05DTandatanganSijil'}">Tandatangan sijil Pembebasan Ukur</c:if>
                        <c:if test="${line.idAliran eq 'B06DTerimaSijil'}">Terima sijil Pembebasan Ukur</c:if>
                        <c:if test="${line.idAliran eq 'B07DTerimaSijil'}">Terima sijil Pembebasan Ukur</c:if>
                        <c:if test="${line.idAliran eq '37AghnTgs'}">Agihan Tugasan dan Keputusan</c:if>
                        <c:if test="${line.idAliran eq '38SedPelan'}">Pelan B2</c:if>
                        <c:if test="${line.idAliran eq '39SmkdanAghnTgs'}">Semak Pelan B2</c:if>
                        <c:if test="${line.idAliran eq 'g_kemaskini_hakmilik'}">Kemaskini hakmilik</c:if>
                        <c:if test="${line.idAliran eq 'A01SemakAgih'}">Semakan dan Agihan Tugasan</c:if>
                        <c:if test="${line.idAliran eq 'A02Penempatan'}">Penempatan</c:if>
                        <c:if test="${line.idAliran eq 'A03DaftarHMSementara'}">Daftar Hakmilik Sambungan</c:if>
                        <c:if test="${line.idAliran eq 'g_semak_pu'}">Semak Surat PU</c:if>
                        <c:if test="${line.idAliran eq 'g_sahkan_pu'}">Sahkan Surat PU</c:if>
                        <c:if test="${line.idAliran eq 'g_hantar_pu'}">Hantar Permohonan Ukur</c:if>
                        <c:if test="${line.idAliran eq 'g_terima_pa_b1'}">Terima PA dan B1</c:if>
                        <c:if test="${line.idAliran eq '59AgihanTugas'}">Semakan dan Agihan Tugasan</c:if>
                        <c:if test="${line.idAliran eq 'g_semak_pa_b1'}">Semak PA dan B1</c:if>
                        <c:if test="${line.idAliran eq 'g_charting_ft'}">Charting FT</c:if>
                        <c:if test="${line.idAliran eq '47Smk'}">Semakan Charting</c:if>
                        <c:if test="${line.idAliran eq 'A04Penempatan'}">Penempatan</c:if>
                        <c:if test="${line.idAliran eq 'A05DaftarHMFT'}">Daftar Hakmilik Sambungan</c:if>
                        <%--RAYT--%>
                        <c:if test="${line.idAliran eq 'semak_draf_mmkn'}">Semak Draf Pertimbangan MMKN</c:if>
                        <c:if test="${line.idAliran eq 'semak_draf_mmkn_g1'}">Semak Draf MMKN</c:if>
                        <c:if test="${line.idAliran eq 'semak_draf_mmkn_g2'}">Semak Draf MMKN</c:if>
                        <c:if test="${line.idAliran eq 'semak_draf_mmkn_g3'}">Semak Draf MMKN</c:if>
                        <c:if test="${line.idAliran eq 'sedia_surat_lulus_5a'}">Sedia Surat Kelulusan dan Borang 5A</c:if>
                        <c:if test="${line.idAliran eq 'semak_surat_lulus_5a'}">Semak Surat Kelulusan dan Borang 5A</c:if>
                        <c:if test="${line.idAliran eq 'sah_bayaran'}">Sah Bayaran</c:if>
                        <c:if test="${line.idAliran eq 'daftar_no_pt'}">Daftar No PT</c:if>
                        <%--RAYK--%>
                        <c:if test="${line.idAliran eq '02MaklumArah'}">Semak dan Arahan Tugasan</c:if>
                        <c:if test="${line.idAliran eq '03SediaMMKN'}">Sedia Draf MMKN</c:if>
                        <c:if test="${line.idAliran eq '04SemakMMKN'}">Semak Draf MMKN</c:if>
                        <c:if test="${line.idAliran eq '05PerakuanMMKN'}">Peraku Draf MMKN</c:if>
                        <c:if test="${line.idAliran eq '06TerimaMMKN'}">Terima Kertas MMKN</c:if>
                        <c:if test="${line.idAliran eq '07SemakDrafMMKN'}">Semak Kertas MMKN</c:if>
                        <c:if test="${line.idAliran eq '08SemakanMMKN'}">Semak Kertas MMKN</c:if>
                        <c:if test="${line.idAliran eq '09SemakanMMKN'}">Semak Kertas MMKN</c:if>
                        <c:if test="${line.idAliran eq '10PindaanMMKN'}">Pindaan Kertas MMKN</c:if>
                        <c:if test="${line.idAliran eq '11PerakuMMKN'}">Peraku Kertas MMKN</c:if>
                        <c:if test="${line.idAliran eq '12RekodKeputusan'}">Rekod Keputusan MMKN</c:if>
                        <c:if test="${line.idAliran eq '13TerimaKeputusan'}">Terima Keputusan</c:if>
                        <c:if test="${line.idAliran eq '14SediaSuratTolak'}">Sedia Surat Tolak</c:if>
                        <c:if test="${line.idAliran eq '15TerimaKeputusan'}">Terima Keputusan</c:if>
                        <c:if test="${line.idAliran eq '16SediaSuratBorang5A'}">Sedia Borang 5A</c:if>
                        <c:if test="${line.idAliran eq '17SemakBorang5A'}">Semak Borang 5A</c:if>
                        <%--PPRU--%>
                        <c:if test="${line.idAliran eq 'agihan_tugas1'}">Agihan Tugas</c:if>
                        <c:if test="${line.idAliran eq 'sedia_borang_4d'}">Sedia Borang 4D</c:if>
                        <c:if test="${line.idAliran eq 'semak_borang_4d'}">Semak Borang 4D</c:if>
                        <c:if test="${line.idAliran eq 'tandatangan_borang_4d'}">Tandatangan Borang 4D</c:if>
                        <c:if test="${line.idAliran eq 'g_charting_4d'}">Charting Kelulusan 4D</c:if>
                        <c:if test="${line.idAliran eq 'semak_charting_4d'}">Charting Kelulusan 4D</c:if>
                        <%--LPSP--%>
                        <c:if test="${line.idAliran eq 'sedia_draf_rencana_ptg'}">Sedia Draf MMKN</c:if>
                        <c:if test="${line.idAliran eq '13TerimaKeputusanArahTugas'}">Terima Keputusan Arah Tugas</c:if>
                        <c:if test="${line.idAliran eq '14ArahanTugas'}">Arahan Tugas</c:if>
                        <c:if test="${line.idAliran eq '15SediaSurat'}">Sedia Surat</c:if>
                        <c:if test="${line.idAliran eq '16SemakSurat'}">Semakan Surat</c:if>
                        <c:if test="${line.idAliran eq '17TerimaBayaran'}">Terima Bayaran</c:if>
                        <c:if test="${line.idAliran eq '18SediaPermit'}">Sedia Surat Kelulusan</c:if>
                        <c:if test="${line.idAliran eq '19SemakPermit'}">Semak Surat Kelulusan</c:if>
                        <c:if test="${line.idAliran eq '20TandatanganPermit'}">Tandatangan Surat Kelulusan Borang 4c</c:if>
                        <c:if test="${line.idAliran eq '21AgihanChartingKemaskini'}">Agihan Tugas</c:if>
                        <c:if test="${line.idAliran eq 'g_charting_kemaskini'}">Charting Permit</c:if>
                        <c:if test="${line.idAliran eq '23SemakanChartingKini'}">Semak Charting Permit</c:if>
                    </display:column>
                    <display:column title="Keputusan">
                        <c:if test="${line.keputusan ne null}"><div align="center"><font style="text-transform:uppercase;">${line.keputusan.nama}&nbsp; </font></div></c:if>
                        <c:if test="${line.keputusan eq null}"><div align="center">-</div></c:if>
                    </display:column>
                </display:table>
            </div>
            <br>

            <div align="center">
                <div align="center">Tugasan Semasa </div>
                <display:table name="${actionBean.lastStageOfFasaPermohonan}" cellpadding="0" cellspacing="0" class="tablecloth" pagesize="15" id="line" style="width:70%"
                               requestURI="${pageContext.request.contextPath}/pembangunan/semak_status">
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column title="ID Pengguna" property="idPengguna"/>
                    <display:column title="Tarikh Terima" property="infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                    <display:column title="Keputusan">
                        <c:if test="${line.keputusan ne null}"><div align="center"><font style="text-transform:uppercase;">${line.keputusan.nama}&nbsp; </font></div></c:if>
                        <c:if test="${line.keputusan eq null}"><div align="center">-</div></c:if>
                    </display:column>
                    <display:column title="Tindakan">
                        <c:if test="${line.tarikhHantar ne null}"><div align="center"> Sudah Dihantar Ke Peringkat Seterusnya &nbsp; </div></c:if>
                        <c:if test="${line.tarikhHantar eq null}"><div align="center"> Masih Dalam Proses &nbsp; </div></c:if>
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
