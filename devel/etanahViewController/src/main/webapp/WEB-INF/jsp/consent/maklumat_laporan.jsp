<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    $(document).ready(function() {
        $('.alphanumeric').alphanumeric();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

    });

    function popupParam(f) {

        if ($('#trh_mula').val() == '' || $('#trh_tamat').val() == '' || $('#only_tahun').val() == '' || $('#tahun').val() == '' || $('#bulan').val() == '' || $('#kelulusan').val() == '' || $('#bangsa').val() == '' || $('#jawatan').val() == '' || $('#pejabat').val() == '' || $('#daerah').val() == '' || $('#mukim').val() == '' || $('#tanah').val() == '' || $('#trh_masuk').val() == '' || $('#projek').val() == '') {

            if ($('#trh_mula').val() == '') {
                alert("Sila Masukkan Tarikh Mula");
            }
            else if ($('#pejabat').val() == '') {
                alert("Sila Masukkan Pejabat");
            }
            else if ($('#daerah').val() == '') {
                alert("Sila Masukkan Daerah");
            }
            else if ($('#mukim').val() == '') {
                alert("Sila Masukkan Mukim");
            }
            else if ($('#trh_tamat').val() == '') {
                alert("Sila Masukkan Tarikh Tamat");
            }
            else if ($('#kelulusan').val() == '') {
                alert("Sila Masukkan Jenis Kelulusan");
            }
            else if ($('#tahun').val() == '') {
                alert("Sila Masukkan Tahun");
            }
            else if ($('#bulan').val() == '') {
                alert("Sila Masukkan Bulan");
            }
            else if ($('#only_tahun').val() == '') {
                alert("Sila Masukkan Tahun");
            }
            else if ($('#bangsa').val() == '') {
                alert("Sila Masukkan Bangsa");
            }
            else if ($('#jawatan').val() == '') {
                alert("Sila Masukkan Bidang Kuasa");
            }
            else if ($('#tanah').val() == '') {
                alert("Sila Masukkan Kategori Tanah");
            }
            else if ($('#trh_masuk').val() == '') {
                alert("Sila Masukkan Tarikh");
            }
            else if ($('#projek').val() == '') {
                alert("Sila Masukkan Jenis Projek");
            }
        }
        else {
            doBlockUI();
            var form = $(f).formSerialize();
            window.open("${pageContext.request.contextPath}/reportGeneratorServlet?" + form, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
    }

    function dateValidation(id, value) {
        var vsplit = value.split('/');
        var fulldate = vsplit[1] + '/' + vsplit[0] + '/' + vsplit[2];
        var today = new Date();
        var sdate = new Date(fulldate);
        if (sdate > today) {
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#' + id).val("");
        }
    }

    function doFilterBPM(kodDaerah) {
        var report = $("#reportname").val();
        var pejabat = $("#pejabat").val();
        var trhMula = $("#trh_mula").val();
        var trhTamat = $("#trh_tamat").val();
        var katgTanah = $("#tanah").val();
        var bangsa = $("#bangsa").val();
        if (kodDaerah != "") {
            var url = '${pageContext.request.contextPath}/consent/laporan?doFilterBPM&kodDaerah=' + kodDaerah + '&reportNama=' + report + '&pejabat=' + pejabat + '&trhMula=' + trhMula + '&trhTamat=' + trhTamat + '&katgTanah=' + katgTanah + '&bangsa=' + bangsa;
            $.get(url,
                    function(data) {
                        $('#display').html(data);
                    });
        }
    }

    function doBlockUI() {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });

    }

</script>
<img alt="" id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.consent.LaporanActionBean">

    <div id="display" class="subtitle">
        <s:hidden name="report_p_kod_caw"/>
        <fieldset class="aras1">
            <%--<c:if test="${fn:startsWith(actionBean.reportName, 'CONS_Stat_R1')}"></c:if>--%>
            <legend>
                <c:choose>
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_Stat_R1_')}">
                        Statistik Permohonan Mengikut Kategori Tanah
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_Stat_R2')}">
                        Statistik Hakmilik Mengikut Mukim
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_Stat_R3')}">
                        Statistik Keputusan Permohonan Mengikut Jenis Permohonan Dan Pejabat
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_Stat_R4')}">
                        Laporan Kemajuan Permohonan Kebenaran Urusniaga
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_Stat_R5')}">
                        Statistik Permohonan Dan Pergerakan Fail Permohonan Mengikut Pejabat
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_Stat_R6')}">
                        Graf Permohonan Mengikut Pejabat
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_Stat_R7')}">
                        Graf Permohonan Mengikut Jenis Kelulusan
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_Stat_R8')}">
                        Statistik Permohonan Mengikut Bangsa Pemohon
                    </c:when>
                    <c:when test="${fn:endsWith(actionBean.reportName, 'CONS_Stat_R9.rdf')}">
                        Statistik Keputusan Mengikut Tempoh Permohonan
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_Stat_R10.rdf')}">
                        Statistik Jumlah Permohonan
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_Stat_R10_MLK')}">
                        Senarai Permohonan Daripada Warganegara Asing Untuk Memiliki Hartanah Bagi Projek (Unit MMK) 
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_Stat_R11')}">
                        Statistik Kelulusan Mengikut Kategori Tanah
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_Stat_R12')}">
                        Statistik Permohonan Mengikut Bangsa Penerima Terperinci
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_Stat_R13')}">
                        Laporan Terperinci Permohonan Pindah Milik
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_Stat_R14')}">
                        Laporan Terperinci Permohonan Pindah Milik Dan Gadaian
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_Stat_R15')}">
                        Laporan Terperinci Permohonan Pajakan
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_Stat_R16')}">
                        Laporan Terperinci Permohonan Gadaian
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_Stat_R17') || fn:startsWith(actionBean.reportName, 'CONS_Stat_R9_MLK')}">
                        Laporan Prestasi Permohonan Kebenaran Pindahmilik
                    </c:when>
                    <c:when test="${actionBean.reportName eq 'CONS_Stat_A1.rdf'}">
                        Statistik Hakmilik Mengikut Mukim (Adat)
                    </c:when>
                    <c:when test="${actionBean.reportName eq 'CONS_Stat_A2.rdf'}">
                        Statistik Permohonan Mengikut Kategori Tanah (Adat)
                    </c:when>
                    <c:when test="${actionBean.reportName eq 'CONS_Stat_A3.rdf'}">
                        Statistik Keputusan Permohonan Mengikut Jenis Permohonan Dan Pejabat (Adat)
                    </c:when>
                    <c:when test="${actionBean.reportName eq 'CONS_Stat_A4.rdf'}">
                        Laporan Kemajuan Permohonan Kebenaran Urusniaga (Adat)
                    </c:when>
                    <c:when test="${actionBean.reportName eq 'CONS_Stat_A5.rdf'}">
                        Graf Permohonan Mengikut Pejabat (Adat)
                    </c:when>
                    <c:when test="${actionBean.reportName eq 'CONS_Stat_A6.rdf'}">
                        Graf Permohonan Mengikut Jenis Kelulusan (Adat)
                    </c:when>
                    <c:when test="${actionBean.reportName eq 'CONS_Stat_A7.rdf'}">
                        Statistik Keputusan Mengikut Tempoh Permohonan (Adat)
                    </c:when>
                    <c:when test="${actionBean.reportName eq 'CONS_Stat_A8.rdf'}">
                        Statistik Jumlah Permohonan (Adat)
                    </c:when>
                    <c:when test="${actionBean.reportName eq 'CONS_Stat_A9.rdf'}">
                        Statistik Kelulusan Mengikut Kategori Tanah (Adat)
                    </c:when>
                    <c:when test="${actionBean.reportName eq 'CONS_Stat_A10.rdf'}">
                        Laporan Terperinci Permohonan Pindah Milik (Adat)
                    </c:when>
                    <c:when test="${actionBean.reportName eq 'CONS_Stat_A11.rdf'}">
                        Laporan Terperinci Permohonan Pajakan (Adat)
                    </c:when>
                    <c:when test="${actionBean.reportName eq 'CONS_Stat_A12.rdf'}">
                        Laporan Terperinci Permohonan Gadaian (Adat)
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_Mohon_PindahmilikWNA1')}">
                        Senarai Permohonan Pindahmilik Warganegara Asing
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_Ringkasan_PindahmilikWNA2')}">
                        Senarai Permohonan Penerima Pindahmilik Warganegara Asing
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_Stat_Pindahmilik3')}">
                        Laporan Pemantauan Prestasi Urusan Kebenaran Pindahmilik/Cagaran Kelulusan Pengarah Tanah Dan Galian (PTG)
                    </c:when> 
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_Stat_Pindahmilik_CM.rdf')}">
                        Laporan Pemantauan Prestasi Urusan Kebenaran Pindahmilik/Cagaran/Pajakan/Sewaan Kelulusan Ketua Menteri
                    </c:when> 
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_Stat_Pemohon.rdf')}">
                        Laporan Statistik Maklumat Kebenaran Urusan Pindahmilik/Pajakan/Cagaran/Sewaan (Kategori Pemohon)
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_Stat_Penerima.rdf')}">
                        Laporan Statistik Maklumat Kebenaran Urusan Pindahmilik/Pajakan/Cagaran/Sewaan (Kategori Penerima)
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_Stat_Maklumat_Hakmilik.rdf')}">
                        Laporan Statistik Maklumat Kebenaran Urusan Pindahmilik/Pajakan/Cagaran/Sewaan (Maklumat Hakmilik)
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_RingkasanPTG_MLK.rdf')}">
                        Ringkasan Permohonan Kelulusan Pengarah Tanah Dan Galian (Keputusan PTG)
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_RingkasanPTG_KM_MLKNew.rdf')}">
                        Ringkasan Permohonan Kelulusan Ketua Menteri (Perakuan PTG)
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_RingkasanPTD_MLK.rdf')}">
                        Ringkasan Permohonan Kelulusan Pentadbir Tanah Daerah (PTD)
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_StatistikPTG_MLK.rdf')}">
                        Statistik Permohonan Kebenaran Pindahmilik Kelulusan Pengarah Tanah Dan Galian (PTG) (Mengikut Tempoh Masa)
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_StatistikKM_MLK.rdf')}">
                        Statistik Permohonan Kebenaran Pindahmilik Kelulusan Ketua Menteri (Mengikut Tempoh Masa)
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_RingkasanKMbyKatg_MLK.rdf')}">
                        Ringkasan Permohonan Kelulusan Ketua Menteri (Keputusan CM)
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.reportName, 'CONS_StatistikPTD_MLK.rdf')}">
                        Statistik Permohonan Kebenaran Pindahmilik Kelulusan Pentadbir Tanah Daerah (PTD) (Mengikut Tempoh Masa)
                    </c:when>    
                    <c:otherwise>
                        Maklumat Laporan
                    </c:otherwise>
                </c:choose>
            </legend><br>
            <s:hidden name="reportName" id="reportname"/>
            <c:if test="${pejabat}">
                <p>
                    <label><em>*</em>Pejabat :</label>
                    <s:select name="report_p_pejabat" id="pejabat" style="width:300px" onchange="doFilterBPM(this.value);">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="01">PTD JELEBU</s:option>
                        <s:option value="02">PTD KUALA PILAH</s:option>
                        <s:option value="03">PTD PORT DICKSON</s:option>
                        <s:option value="04">PTD REMBAU</s:option>
                        <s:option value="05">PTD SEREMBAN</s:option>
                        <s:option value="06">PTD TAMPIN</s:option>
                        <s:option value="07">PTD JEMPOL</s:option>
                        <s:option value="08">PTD GEMAS</s:option>

                        <%--<s:options-collection collection="${list.senaraiKodCawangan}" label="name" value="kod"/>--%>
                    </s:select>
                </p>
            </c:if>
            <c:if test="${daerah}">
                <p>
                    <label><em>*</em>Daerah :</label>
                    <s:select name="report_p_daerah" id="daerah" style="width:300px" onchange="doFilterBPM(this.value);">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodDaerah}" label="nama" value="kod"/>
                    </s:select>
                </p>
            </c:if>
            <c:if test="${mukim}">
                <p>
                    <label><em>*</em>Mukim :</label>
                    <s:select name="report_p_kod_bpm" id="mukim" style="width:270px">
                        <%-- <s:option value="">Sila Pilih</s:option>
                         <s:options-collection collection="${actionBean.senaraiBPM}" label="nama" value="kod" ></s:options-collection>
                     </s:select>--%>
                        <s:option value="">Sila Pilih</s:option>
                        <c:forEach items="${actionBean.senaraiBPM}" var="i" >
                            <s:option value="${i.kod}" >${i.bandarPekanMukim} - ${i.nama}</s:option>
                        </c:forEach>
                    </s:select>

                </p>
            </c:if>

            <c:if test="${tahunBulan}">
                <p>
                    <label><em>*</em>Tahun dan Bulan :</label>

                    <s:select name="report_p_tahun" id="tahun" style="width:100px">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.yearList}"/>
                    </s:select>

                    <s:select name="report_p_bulan" id="bulan" style="width:100px">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="01">Januari</s:option>
                        <s:option value="02">Februari</s:option>
                        <s:option value="03">Mac</s:option>
                        <s:option value="04">April</s:option>
                        <s:option value="05">Mei</s:option>
                        <s:option value="06">Jun</s:option>
                        <s:option value="07">Julai</s:option>
                        <s:option value="08">Ogos</s:option>
                        <s:option value="09">September</s:option>
                        <s:option value="10">Oktober</s:option>
                        <s:option value="11">November</s:option>
                        <s:option value="12">Disember</s:option>
                    </s:select>
                </p>
            </c:if>

            <c:if test="${date}">
                <p>
                    <label><em>*</em>Tarikh Mula dan Tamat :</label>
                    <s:text name="report_p_trh_mula" id="trh_mula" class="datepicker" size="9" onchange="dateValidation(this.id, this.value)"/> Hingga
                    <s:text name="report_p_trh_tamat" id="trh_tamat" class="datepicker" size="9" onchange="dateValidation(this.id, this.value)"/>
                </p>
            </c:if>

            <c:if test="${tarikhMasuk}">
                <p>
                    <label><em>*</em>Tarikh Daftar Urusan :</label>
                    <s:text name="report_p_tarikhMasuk" id="trh_masuk" class="datepicker" size="9" onchange="dateValidation(this.id, this.value)"/>
                </p>
            </c:if>

            <c:if test="${kelulusan}">
                <p>
                    <label><em>*</em>Jenis Kelulusan :</label>
                    <c:if test="${actionBean.kodNegeri eq '05'}">
                        <s:select name="report_p_kelulusan" id="kelulusan" style="width:400px">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="JKTLD">KELULUSAN JAWATANKUASA TANAH LADANG (DENGAN SEKATAN)</s:option>
                            <s:option value="JKTLT">KELULUSAN JAWATANKUASA TANAH LADANG (TANPA SEKATAN)</s:option>
                            <s:option value="MMK">KELULUSAN MAJLIS MESYUARAT KERAJAAN</s:option>
                            <s:option value="MB">KELULUSAN MENTERI BESAR</s:option>
                            <s:option value="PTG">KELULUSAN PENGARAH TANAH & GALIAN</s:option>
                            <s:option value="PTD">KELULUSAN PENTADBIR TANAH DAERAH</s:option>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.kodNegeri eq '04'}">
                        <s:select name="report_p_kelulusan" id="kelulusan" style="width:280px">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="JKTL">KELULUSAN JAWATANKUASA TANAH LADANG</s:option>
                            <s:option value="KMM">KELULUSAN KETUA MENTERI</s:option>
                            <s:option value="PTG">KELULUSAN PENGARAH TANAH & GALIAN</s:option>
                            <s:option value="MMK">KELULUSAN MAJLIS MESYUARAT KERAJAAN</s:option>
                        </s:select>
                    </c:if>
                </p>
            </c:if>
            <c:if test="${kelulusan2}">
                <p>
                    <label><em>*</em>Jenis Kelulusan :</label>
                    <c:if test="${actionBean.kodNegeri eq '05'}">
                        <s:select name="report_p_kelulusan" id="kelulusan" style="width:400px">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="JKTL">KELULUSAN JAWATANKUASA TANAH LADANG</s:option>
                            <s:option value="MMK">KELULUSAN MAJLIS MESYUARAT KERAJAAN</s:option>
                            <s:option value="MB">KELULUSAN MENTERI BESAR</s:option>
                            <s:option value="PTG">KELULUSAN PENGARAH TANAH & GALIAN</s:option>
                            <s:option value="PTD">KELULUSAN PENTADBIR TANAH DAERAH</s:option>
                        </s:select>
                    </c:if>
                </p>
            </c:if>
            <c:if test="${kelulusanAdat}">
                <p>
                    <label><em>*</em>Jenis Kelulusan :</label>
                    <s:select name="report_p_kelulusan" id="kelulusan" style="width:400px">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="MMK">KELULUSAN MAJLIS MESYUARAT KERAJAAN</s:option>
                        <s:option value="PTD">KELULUSAN PENTADBIR TANAH DAERAH</s:option>
                    </s:select>
                </p>
            </c:if>
            <c:if test="${kelulusanAdat2}">
                <p>
                    <label><em>*</em>Jenis Kelulusan :</label>
                    <s:select name="report_p_kelulusan" id="kelulusan" style="width:400px">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="MMK">KELULUSAN MAJLIS MESYUARAT KERAJAAN (WARGANEGARA ASING)</s:option>
                        <s:option value="MMK2">KELULUSAN MAJLIS MESYUARAT KERAJAAN (BANTAHAN)</s:option>
                        <s:option value="PTD">KELULUSAN PENTADBIR TANAH DAERAH</s:option>
                    </s:select>
                </p>
            </c:if>
            <c:if test="${tahun}">
                <p>
                    <label><em>*</em>Tahun :</label>
                    <s:select name="report_p_tahun" id="only_tahun" style="width:100px">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.yearList}"/>
                    </s:select>
                </p>
            </c:if>

            <c:if test="${jawatan}">
                <p>
                    <label><em>*</em>Bidang Kuasa :</label>
                    <s:select name="report_p_jawatan" id="jawatan" style="width:230px">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="PTD">Pentadbir Tanah Daerah (PTD)</s:option>
                        <s:option value="MMK">Majlis Mesyuarat Kerajaan (MMK)</s:option>
                        <s:option value="MB">Menteri Besar (MB)</s:option>
                        <s:option value="PTG">Pengarah Tanah Galian (PTG)</s:option>
                        <s:option value="ADAT">Tanah Adat</s:option>
                    </s:select>

                </p>
            </c:if>

            <c:if test="${kategoriTanah}">
                <p>
                    <label><em>*</em>Kategori Tanah :</label>
                    <s:select name="report_p_katg_tanah" id="tanah" style="width:200px">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodKategoriTanah}" label="nama" value="kod"/>
                    </s:select>
                </p>
            </c:if>
            <c:if test="${bangsa}">
                <p>
                    <label><em>*</em>Bangsa :</label>
                    <s:select name="report_p_bangsa" id="bangsa" style="width:200px">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodBangsaPerseorangan}" label="nama" value="kod"/>
                    </s:select>
                </p>
            </c:if>
            <c:if test="${projek}">
                <p>
                    <label><em>*</em>Projek :</label>
                    <s:select name="report_p_id_projek" id="projek" style="width:200px">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiProjek}" label="jenisProjek" value="idProjek"/>
                    </s:select>
                </p>
            </c:if>
            <c:if test="${urusan}">
                <p>
                    <label>Urusan :</label>
                    <s:select name="report_p_kod_urusan" id="urusan" style="width:400px">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="">Semua Urusan</s:option>
                        <s:option value="MCKMM">Permohonan Kebenaran Cagaran Kelulusan Ketua Menteri</s:option>
                        <s:option value="MCPTG">Permohonan Kebenaran Cagaran Kelulusan PTG</s:option>
                        <s:option value="PJKMM">Permohonan Kebenaran Pajakan Kelulusan Ketua Menteri</s:option>
                        <s:option value="PMKMM">Permohonan Kebenaran Pindahmilik Kelulusan Ketua Menteri</s:option>
                        <s:option value="PPTGM">Permohonan Kebenaran Pindahmilik Kelulusan PTG</s:option>
                        <s:option value="SWKMM">Permohonan Kebenaran Sewaan Kelulusan Ketua Menteri</s:option>
                        <s:option value="MCPTD">Permohonan Kebenaran Cagaran Kelulusan PTD</s:option>
                        <s:option value="PJPTD">Permohonan Kebenaran Pajakan Kelulusan PTD</s:option>
                        <s:option value="PMPTD">Permohonan Kebenaran Pindahmilik Kelulusan PTD</s:option>
                        <s:option value="SWPTD">Permohonan Kebenaran Sewaan Kelulusan PTD</s:option>
                    </s:select>
                </p>
            </c:if>
            <p>
                <label>&nbsp;</label>
                <s:button name="" value="Papar" class="btn" onclick="popupParam(this.form)"/>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
            <br/>
        </fieldset>
    </div>
</s:form>

