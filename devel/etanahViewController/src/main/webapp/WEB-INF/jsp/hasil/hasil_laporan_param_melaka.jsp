<%-- 
    Document   : hasil_laporan_param_melaka
    Created on : Jun 1, 2010, 4:17:28 PM
    Author     : zadhirul.farihim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    var date = new Date();
    var kodCaw = '${actionBean.kodCaw}';
    var kodDaerah = '${actionBean.kodDaerah}';
    var report = '${actionBean.reportName}';


    $(document).ready(function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $("#daerah").val(kodDaerah);
        $("#caw").val(kodCaw);
        $("#trh_mula").val(date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear());
        $("#trh_tamat").val(date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear());
        $("#trh_pungutan").val(date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear());
        $("#trh_masuk").val(date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear());
        $("#bulan").val(date.getMonth() + 1);
        $("#tahun").val(date.getFullYear());
    });

    function doSubmit(f) {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });

        var form = $(f).formSerialize();
        var report = '${actionBean.reportName}';
        if ((report == 'HSL_R_01_MLK.rdf'
                || report == 'HSL_R_01_FED_MLK.rdf'
                || report == 'HSL_R_01_STATE_MLK.rdf'
                || report == 'HSL_R_02_MLK.rdf'
                || report == 'HSL_R_03_MLK.rdf'
                || report == 'HSL_R_04_MLK.rdf'
                || report == 'HSL_PP_MLK.rdf'
                || report == 'HSL_R_05_MLK.rdf'
                || report == 'HSL_R_06_MLK.rdf'
                || report == 'HSL_R_07_MLK.rdf'
                || report == 'HSL_R_09_MLK.rdf'
                || report == 'HSL_R_10_MLK.rdf'
                || report == 'HSL_R_14_MLK.rdf'
                || report == 'HSL_R_16_MLK.rdf'
                || report == 'HSL_R_70_MLK.rdf'
                || report == 'HSL_R_17_MLK.rdf'

                || report == 'HSL_R_19_MLK.rdf'
                || report == 'HSL_R_22_MLK.rdf'
                || report == 'HSL_R_63_MLK.rdf'
                || report == 'HSL_R_71_MLK.rdf'
                || report == 'HSL_R_23_MLK.rdf'
                || report == 'HSL_R_72_MLK.rdf'
                || report == 'HSL_R_24_MLK.rdf'
                || report == 'HSL_R_25_MLK.rdf'
                || report == 'HSL_R_27_MLK.rdf'
                || report == 'HSL_R_34_MLK.rdf'
                || report == 'HSL_R_73_MLK.rdf'
                || report == 'HSL_R_66_MLK.rdf'
                || report == 'HSL_R_74_MLK.rdf'
                || report == 'HSL_R_40_MLK.rdf'
                || report == 'HSL_R_15_MLK_JOMPAY.rdf'
                || report == 'HSL_R_50_MLK.rdf') && $('#caw').val() == "") {
            alert("Sila pilih Cawangan terlebih dahulu.");
            $('#caw').focus();
            $.unblockUI();
        } else {
            var url = form.replace(/&/g, "%26");
            window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
                    "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    <%--f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
    f.submit();--%>
            $.unblockUI();
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

        var report = '${actionBean.reportName}';

        if (report === 'HSL_R_48_MLK.rdf' || report === 'HSL_R_49_MLK.rdf') {

            if (id === 'trh_mula')
            {
                $("#trh_mula").val(value + " 00:00:00");
            } else if (id === 'trh_tamat') {
                $("#trh_tamat").val(value + " 23:59:59");
            }
        }
    }

    function validateNumber(elmnt, content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric(strString)
    {
        var strValidCharacters = "1234567890.";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for (intIndex = 0; intIndex < strString.length; intIndex++)
        {
            strBuffer = strString.substr(intIndex, 1);
            // Is this a number
            if (strValidCharacters.indexOf(strBuffer) > -1)
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }

    function validateYearLength(value) {
        var plength = value.length;
        if (plength != 4) {
            alert('"Tahun" yang dimasukkan salah.');
            $('#tahun').val("");
            $('#tahun').focus();
        }
    }

    function doFilterKaunter(kodCaw) {
        var report = $("#reportname").val();
        var tm = $("#trh_mula").val() + "";
        var tt = $("#trh_tamat").val() + "";
        if (kodCaw != "") {
            var url = '${pageContext.request.contextPath}/laporanHasil_MLK?doCollectKaunter&kodCawangan=' + kodCaw + '&reportNama=' + report;
            $.get(url,
                    function(data) {
                        $('#display').html(data);
                        $('#caw').val(kodCaw);
                        $("#trh_mula").val(tm);
                        $("#trh_tamat").val(tt);
                        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                    });
        }
    }

    function doFilterDaerah(kodCaw2) {
        var report = $("#reportname").val();
        if (kodCaw2 != null) {
            var url = '${pageContext.request.contextPath}/laporanHasil_MLK?doFilterDaerahBPM&kodCawangan=' + kodCaw2 + '&reportNama=' + report;
            $.get(url,
                    function(data) {
                        $('#display').html(data);
                        $('#caw').val(kodCaw2);
                        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                    });
        }
    }

    function doFilterBPM(kodDaerah1) {
        var report = $("#reportname").val();
        var caw;
        if ($('#caw').val() != '')
            caw = $('#caw').val();
        if (kodDaerah1 != "") {
            var url = '${pageContext.request.contextPath}/laporanHasil_MLK?doFilterBPM&kodDaerah=' + kodDaerah1 + '&reportNama=' + report;
            $.get(url,
                    function(data) {
                        $('#display').html(data);
                        $('#daerah').val(kodDaerah1);
                        if (caw != '')
                            $('#caw').val(caw);
                        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                    });
        }
    }
    function filterBPM(kodBPM, frm) {
        var daerah = $('#kodDaerah').val();
        var url = '${pageContext.request.contextPath}/laporanHasil_MLK?penyukatanSeksyen&bandarPekanMukim=' + kodBPM + '&daerah' + daerah;
        frm.action = url;
        frm.submit();
    }
    function filterDaerah(kodDaerah, frm) {
        //                            var jabatan = f.elements['pguna.kodJabatan.kod'].value ;
        var url = '${pageContext.request.contextPath}/laporanHasil_MLK?penyukatanBPM&daerah=' + kodDaerah1;
        frm.action = url;
        frm.submit();

    }
</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
     width="50" height="50" style="display: none" alt=""/>

<s:form beanclass="etanah.view.laporan.HasilLaporanMelakaActionBean">
    <div id="display" class="subtitle">
        <s:hidden id="reportname" name="reportName"/>
        <fieldset class="aras1">
            <lagend>

                <c:if test="${actionBean.reportName eq 'HSL_R_01_MLK.rdf'}">
                    Senarai Terimaan Harian 
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_02_MLK.rdf'}">
                    Senarai Penjenisan Hasil Mengikut Kod Jenis Hasil
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_03_MLK.rdf'}">
                    Kemajuan Cukai Tanah, Denda & Notis Mengikut Mukim 
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_04_MLK.rdf'}">
                    Senarai Kutipan Hasil Mengikut Kod Jenis Hasil 
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_PP_MLK.rdf'}">
                    Laporan Penyata Pemungut 
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_05_MLK.rdf'}">
                    Senarai Hakmilik Daerah Lain Yang Dikutip 
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_50_MLK.rdf'}">
                    Senarai Hakmilik Yang Dikutip oleh Daerah Lain
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_07_MLK.rdf'}">
                    Senarai Kutipan Batal
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_06_MLK.rdf'}">
                    Senarai Terimaan Harian Dari Agensi (eg: POS dan etc) 
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_09_MLK.rdf'}">
                    Senarai Kutipan Cukai Tahun Pertama 
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_12_MLK.rdf'}">
                    Statistik Permohonan Yang Ditolak
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_13_MLK.rdf'}">
                    Statistik Permohonan Yang Diterima 
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_14_MLK.rdf'}">
                    Statistik Pelanggan Mengikut Modul 
                </c:if>

                <%--><c:if test="${actionBean.reportName eq '2'}">
               Senarai Terimaan SPEKS 
               </c:if>--%>

                <c:if test="${actionBean.reportName eq 'HSL_R_16_MLK.rdf'}">
                    Laporan Penyata Kutipan Cukai Harian Mengikut Mukim
                </c:if>
                <c:if test="${actionBean.reportName eq 'HSL_R_70_MLK.rdf'}">
                    Laporan Penyata Kutipan Cukai Petak Harian Mengikut Mukim
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_17_MLK.rdf'}">
                    Laporan Kutipan Kaunter (Summary + Details)  
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_19_MLK.rdf'}">
                    Laporan Pembayaran Ansuran  
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_63_MLK.rdf'}">
                    Ringkasan Tunggakan Cukai Ikut Kategori (Summary)
                </c:if>
                <c:if test="${actionBean.reportName eq 'HSL_R_71_MLK.rdf'}">
                    Ringkasan Tunggakan Cukai Petak Ikut Kategori (Summary)
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_23_MLK.rdf'}">
                    Senarai Tunggakan Cukai Ikut Kategori
                </c:if>   
                <c:if test="${actionBean.reportName eq 'HSL_R_72_MLK.rdf'}">
                    Senarai Tunggakan Cukai Petak Ikut Kategori
                </c:if>   

                <c:if test="${actionBean.reportName eq 'HSL_R_68_MLK.rdf'}">
                    Senarai Terimaan Harian 
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_24_MLK.rdf'}">
                    Laporan Tunggakan (Mingguan / Bulanan) ... Tahun ... 
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_25_MLK.rdf'}">
                    Senarai Resit Batal (Kutipan Cek)
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_27_MLK.rdf'}">
                    Senarai Resit Yang Dibatalkan Bagi Bulan..Tahun.. 
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_34_MLK.rdf'}">
                    Laporan Induk Cukai Tanah
                </c:if>
                <c:if test="${actionBean.reportName eq 'HSL_R_73_MLK.rdf'}">
                    Laporan Induk Cukai Petak Tanah
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_66_MLK.rdf'}">
                    Ringkasan Induk Cukai Tanah
                </c:if>
                <c:if test="${actionBean.reportName eq 'HSL_R_74_MLK.rdf'}">
                    Ringkasan Induk Cukai Petak Tanah
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_37_MLK.rdf'}">
                    Senarai Hakmilik Yang Dilucuthakkan Melalui Borang 8A & Warta Kerajaan  Bagi Tahun… 
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_40_MLK.rdf'}">
                    Senarai Hakmilik Yang Mempunyai Jumlah Baki Dan Jumlah Debit/Kredit Tidak Sama
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_41_MLK.rdf'}">
                    Laporan Terimaan Harian Dari Agensi - Mengikut Kod Jenis Hasil 
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_45_MLK.rdf'}">
                    Senarai Akaun Masa Hadapan ( Hasil Tahun Pertama)
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_46_MLK.rdf'}">
                    Ringkasan Umuran Tunggakan Cukai Tanah 
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_48_MLK.rdf'}">
                    Penyata Buku Tunai (KEW 250) 
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_47_MLK.rdf'}">
                    Laporan Tiada Nama/Alamat bagi Pembayar
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_49_MLK.rdf'}">
                    Penyata Buku Tunai (KEW 248)
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_44_MLK.rdf'}">
                    Ringkasan Daftar Cukai Mengikut Jenis Hakmilik bagi Setiap Mukim  
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_43_MLK.rdf'}">
                    Maklumat Kutipan Cukai Tanah 
                </c:if>
                <c:if test="${actionBean.reportName eq 'HSL_R_75_MLK.rdf'}">
                    Maklumat Kutipan Cukai Tanah ( Strata )
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_42_MLK.rdf'}">
                    Senarai Daftar Cukai
                </c:if>
                <c:if test="${actionBean.reportName eq 'HSL_R_76_MLK.rdf'}">
                    Senarai Daftar Cukai ( Strata )
                </c:if>
                <c:if test="${actionBean.reportName eq 'HSL_R_61_MLK.rdf'}">
                    Ringkasan Denda Lewat Semasa
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_67_MLK.rdf'}">
                    Senarai Cukai Tanah Yang DiKutip Dari..
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_77_MLK.rdf'}">
                    Senarai Cukai Tanah Yang DiKutip Dari.. ( Strata )
                </c:if>

                <c:if test="${actionBean.reportName eq 'HSL_R_69_MLK.rdf'}">
                    Senarai Terimaan Harian Dari Agensi E-bayar
                </c:if>
                <c:if test="${actionBean.reportName eq 'HSL_R_69_MLK_PUKAL.rdf'}">
                    Senarai Terimaan Harian Dari Agensi E-bayar (Bayaran Pukal)
                </c:if>
                <c:if test="${actionBean.reportName eq 'HSL_R_15_MLK_JOMPAY.rdf'}">
                    Senarai Terimaan Harian Dari Agensi JomPay
                </c:if>

            </lagend>


            <legend>Parameter</legend>
            <c:set value="${actionBean.reportName}" var="reportname"/>

            <c:set var="disable" value="false"/>
            <c:if test="${actionBean.pengguna.kodCawangan.kod ne '00'}">
                <c:set var="disable" value="true"/>
            </c:if>

            <c:if test="${reportname   eq 'HSL_R_09_MLK.rdf'
                          or reportname eq 'HSL_R_14_MLK.rdf'
                          or reportname eq 'HSL_R_17_MLK.rdf'
                          or reportname eq 'HSL_R_19_MLK.rdf'
                          or reportname eq 'HSL_R_25_MLK.rdf'
                          or reportname eq 'HSL_R_27_MLK.rdf'
                          or reportname eq 'HSL_R_01_MLK.rdf'
                          or reportname eq 'HSL_R_68_MLK.rdf'
                          or reportname eq 'HSL_R_02_MLK.rdf'
                          or reportname eq 'HSL_R_04_MLK.rdf'
                          or reportname eq 'HSL_R_03_MLK.rdf'
                          or reportname eq 'HSL_PP_MLK.rdf'
                          or reportname eq 'HSL_R_07_MLK.rdf'
                          or reportname eq 'HSL_R_41_MLK.rdf'
                          or reportname eq 'HSL_R_48_MLK.rdf'
                          or reportname eq 'HSL_R_49_MLK.rdf'
                          or reportname eq 'HSL_R_05_MLK.rdf'
                          or reportname eq 'HSL_R_67_MLK.rdf'
                          or reportname eq 'HSL_R_77_MLK.rdf'
                          or reportname eq 'HSL_R_69_MLK.rdf'
                          or reportname eq 'HSL_R_69_MLK_PUKAL.rdf'
                          or reportname eq 'HSL_R_15_MLK_JOMPAY.rdf'
                  }">
                <p>
                    <label><em>*</em>Cawangan :</label>
                    <s:select id="caw" name="report_p_kod_caw" style="width:260px;" disabled="${disable}" onchange="doFilterDaerah(this.value);">
                        <c:if test="${reportname   eq 'HSL_R_69_MLK_PUKAL.rdf'}">
                            <s:option value="">Semua</s:option>  
                        </c:if>
                        <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                    </s:select>
                    <s:hidden name="report_p_kod_caw" value="${actionBean.kodCaw}"/>
                </p>

            </c:if>
            <c:if test="${reportname eq 'HSL_R_06_MLK.rdf'
                          or reportname eq 'HSL_R_16_MLK.rdf'
                          or reportname eq 'HSL_R_70_MLK.rdf'
                          or reportname eq 'HSL_R_19_MLK.rdf'
                          or reportname eq 'HSL_R_23_MLK.rdf'
                          or reportname eq 'HSL_R_72_MLK.rdf'
                          or reportname eq 'HSL_R_63_MLK.rdf'
                          or reportname eq 'HSL_R_71_MLK.rdf'
                          or reportname eq 'HSL_R_24_MLK.rdf'
                          or reportname eq 'HSL_R_34_MLK.rdf'
                          or reportname eq 'HSL_R_73_MLK.rdf'
                          or reportname eq 'HSL_R_37_MLK.rdf'
                          or reportname eq 'HSL_R_40_MLK.rdf'
                          or reportname eq 'HSL_R_41_MLK.rdf'
                          or reportname eq 'HSL_R_42_MLK.rdf'
                          or reportname eq 'HSL_R_76_MLK.rdf'
                          or reportname eq 'HSL_R_43_MLK.rdf'
                          or reportname eq 'HSL_R_75_MLK.rdf'
                          or reportname eq 'HSL_R_44_MLK.rdf'
                          or reportname eq 'HSL_R_45_MLK.rdf'
                          or reportname eq 'HSL_R_46_MLK.rdf'
                          or reportname eq 'HSL_R_47_MLK.rdf'

                          or reportname eq 'HSL_R_61_MLK.rdf'
                          or reportname eq 'HSL_R_50_MLK.rdf'
                          or reportname eq 'HSL_R_66_MLK.rdf'
                          or reportname eq 'HSL_R_74_MLK.rdf'
                          or reportname eq 'HSL_R_69_MLK.rdf'
                          or reportname eq 'HSL_R_69_MLK_PUKAL.rdf'
                          or reportname eq 'HSL_R_15_MLK_JOMPAY.rdf'

                  }">
                <p>
                    <label><em>*</em>Daerah :</label>
                    <c:if test="${reportname ne 'HSL_R_34_MLK.rdf' }">
                        <s:select name="kodDaerah" style="width:260px;" disabled="${disable}" onchange="doFilterBPM(this.value);">
                            <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod"/>
                        </s:select>
                        <s:hidden name="report_p_kod_daerah" value="${actionBean.kodDaerah}"/>
                    </c:if>

                    <c:if test="${reportname eq 'HSL_R_34_MLK.rdf' }">
                    <p>
                        <s:select name="kodDaerah" style="width:260px;" disabled="${disable}" onchange="filterBPM(this.value);">
                            <s:options-collection collection="${actionBean.senaraiKodDaerah}" label="nama" value="kod"/>
                        </s:select>
                        <s:hidden name="report_p_kod_daerah" value="${actionBean.kodDaerah1}"/>
                    </p>
                </c:if>

                </p>
            </c:if>

            <c:if test="${reportname eq 'HSL_R_19_MLK.rdf'
                          or reportname eq 'HSL_R_23_MLK.rdf'
                          or reportname eq 'HSL_R_72_MLK.rdf'
                          or reportname eq 'HSL_R_63_MLK.rdf'
                          or reportname eq 'HSL_R_71_MLK.rdf'
                          or reportname eq 'HSL_R_24_MLK.rdf'
                          or reportname eq 'HSL_R_34_MLK.rdf'
                          or reportname eq 'HSL_R_73_MLK.rdf'
                          or reportname eq 'HSL_R_37_MLK.rdf'
                          or reportname eq 'HSL_R_40_MLK.rdf'
                          or reportname eq 'HSL_R_44_MLK.rdf'
                          or reportname eq 'HSL_R_47_MLK.rdf'
                          or reportname eq 'HSL_R_42_MLK.rdf'
                          or reportname eq 'HSL_R_76_MLK.rdf'
                          or reportname eq 'HSL_R_43_MLK.rdf'
                          or reportname eq 'HSL_R_75_MLK.rdf'
                          or reportname eq 'HSL_R_66_MLK.rdf'
                          or reportname eq 'HSL_R_74_MLK.rdf'



                  }">
                <p>
                    <label>Bandar/Pekan/Mukim:</label>
                    <c:if test="${reportname ne 'HSL_R_34_MLK.rdf' }">
                        <s:select id="bpm" name="report_p_kod_bpm" style="width:200px;">
                            <s:option value="">SEMUA</s:option>
                            <%--<s:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="kod"/>--%>
                            <s:options-collection collection="${actionBean.senaraiBPM}" label="nama" value="kod"/>
                        </s:select>
                    </c:if>   
                    <c:if test="${reportname eq 'HSL_R_34_MLK.rdf' }">
                        <s:select name="report_p_kod_bpm" id="bandarPekanMukim" style="width:210px;" onchange="filterBPM(this.value,this.form);">
                            <s:option value="">--Sila Pilih--</s:option>
                            <%--<s:options-collection collection="${actionBean.senaraiBPM}" label="nama" value="kod" sort="nama" />--%>
                            <c:forEach items="${actionBean.senaraiBPM}" var="i" >
                                <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                            </c:forEach>
                        </s:select>
                        <c:if test="${actionBean.seksyen eq '1' }">        
                        <p>
                            <label>  Seksyen :</label>
                            <s:select name="report_p_kod_seksyen" id="seksyen" style="width:210px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <c:forEach items="${actionBean.senaraiKodSeksyen}" var="i" >
                                    <s:option value="${i.kod}">${i.nama}</s:option>
                                </c:forEach>
                            </s:select>
                        </p>
                    </c:if>
                </c:if>

                </p>
            </c:if>

            <c:if test="${reportname eq 'HSL_R_07_MLK.rdf'
                          or reportname eq 'HSL_R_09_MLK.rdf'
                  }">
                <p>
                    <label>Kaunter Mula :</label>
                    <s:select name="report_p_kaunter_mula" style="width:145px;">
                        <s:option value="">Semua</s:option>
                        <c:forEach items="${actionBean.senaraiKaunter}" var="kaunter" >
                            <s:option value="${kaunter}">${kaunter}</s:option>
                        </c:forEach>
                    </s:select>
                </p>
            </c:if>
            <c:if test="${reportname eq 'HSL_R_07_MLK.rdf'
                          or reportname eq 'HSL_R_09_MLK.rdf'
                  }">
                <p>
                    <label>Kaunter Akhir :</label>
                    <s:select name="report_p_kaunter_akhir" style="width:145px;">
                        <s:option value="">Semua</s:option>
                        <c:forEach items="${actionBean.senaraiKaunter}" var="kaunter" >
                            <s:option value="${kaunter}">${kaunter}</s:option>
                        </c:forEach>
                    </s:select>
                </p>
            </c:if>
            <c:if test="${reportname eq 'HSL_R_02_MLK.rdf'
                          or reportname eq 'HSL_R_01_MLK.rdf'
                          or reportname eq 'HSL_R_68_MLK.rdf'
                          or reportname eq 'HSL_R_03_MLK.rdf'
                          or reportname eq 'HSL_R_04_MLK.rdf'
                          or reportname eq 'HSL_PP_MLK.rdf'
                  }">
                <p>
                    <label>Kaunter :</label>
                    <s:select name="report_p_id_kaunter" style="width:145px;">
                        <s:option value="">Semua</s:option>
                        <c:forEach items="${actionBean.senaraiKaunter}" var="kaunter" >
                            <s:option value="${kaunter}">${kaunter}</s:option>
                        </c:forEach>
                    </s:select>
                </p>
            </c:if>
            <c:if test="${reportname eq 'HSL_R_02_MLK.rdf'
                          or reportname eq 'HSL_R_04_MLK.rdf'
                          or reportname eq 'HSL_R_06_MLK.rdf'
                          or reportname eq 'HSL_R_41_MLK.rdf'
                          or reportname eq 'HSL_R_45_MLK.rdf'
                          or reportname eq 'HSL_R_69_MLK.rdf'
                          or reportname eq 'HSL_R_69_MLK_PUKAL.rdf'
                          or reportname eq 'HSL_R_15_MLK_JOMPAY.rdf'
                  }">
                <p>
                    <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_masuk" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
            </c:if>

            <c:if test="${reportname eq 'HSL_R_01_MLK.rdf'
                          or reportname eq 'HSL_R_68_MLK.rdf'
                          or reportname eq 'HSL_R_16_MLK.rdf'
                          or reportname eq 'HSL_R_70_MLK.rdf'
                          or reportname eq 'HSL_R_SPEKS_MLK.rdf'
                          or reportname eq 'HSL_R_05_MLK.rdf'
                          or reportname eq 'HSL_R_50_MLK.rdf'
                          or reportname eq 'HSL_R_03_MLK.rdf'
                          or reportname eq 'HSL_R_24_MLK.rdf'
                          or reportname eq 'HSL_R_09_MLK.rdf'
                          or reportname eq 'HSL_R_07_MLK.rdf'
                          or reportname eq 'HSL_R_27_MLK.rdf'
                          or reportname eq 'HSL_R_67_MLK.rdf'
                          or reportname eq 'HSL_R_77_MLK.rdf'
                          or reportname eq 'HSL_R_19_MLK.rdf'

                  }">    
                <p>
                    <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
            </c:if>
            <c:if test="${reportname eq 'HSL_R_48_MLK.rdf'
                          or reportname eq 'HSL_R_49_MLK.rdf'
                  }">    
                <p>
                    <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula_kew" class="datepicker" formatPattern="dd/MM/yyyy" size="9"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
            </c:if>
            <c:if test="${reportname eq 'HSL_R_48_MLK.rdf'
                          or reportname eq 'HSL_R_49_MLK.rdf'
                  }">
                <p>
                    <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat_kew" class="datepicker" formatPattern="dd/MM/yyyy" size="9"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
            </c:if>
            <c:if test="${reportname eq 'HSL_R_24_MLK.rdf'
                          or reportname eq 'HSL_R_43_MLK.rdf'
                          or reportname eq 'HSL_R_75_MLK.rdf'
                          or reportname eq 'HSL_R_67_MLK.rdf'
                          or reportname eq 'HSL_R_77_MLK.rdf'
                          or reportname eq 'HSL_R_69_MLK.rdf'
                          or reportname eq 'HSL_R_69_MLK_PUKAL.rdf'
                          or reportname eq 'HSL_R_15_MLK_JOMPAY.rdf'
                          or reportname eq 'HSL_R_19_MLK.rdf'
                  }">
                <p>
                    <label>Tarikh Sehingga :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
            </c:if>
            <c:if test="${reportname eq 'HSL_R_02_MLK.rdf'
                          or reportname eq 'HSL_R_01_MLK.rdf'
                          or reportname eq 'HSL_R_68_MLK.rdf'
                          or reportname eq 'HSL_R_03_MLK.rdf'
                          or reportname eq 'HSL_R_04_MLK.rdf'



                          or reportname eq 'HSL_R_05_MLK.rdf'
                          or reportname eq 'HSL_R_07_MLK.rdf'
                          or reportname eq 'HSL_R_06_MLK.rdf'
                          or reportname eq 'HSL_R_09_MLK.rdf'
                          or reportname eq 'HSL_R_16_MLK.rdf'
                          or reportname eq 'HSL_R_70_MLK.rdf'

                          or reportname eq 'HSL_R_41_MLK.rdf'
                          or reportname eq 'HSL_R_27_MLK.rdf'
                          or reportname eq 'HSL_R_45_MLK.rdf'
                          or reportname eq 'HSL_R_SPEKS_MLK.rdf'
                          or reportname eq 'HSL_R_50_MLK.rdf'



                  }">
                <p>
                    <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
            </c:if>
            <c:if test="${reportname eq 'HSL_PP_MLK.rdf'

                  }">    
                <p>
                    <label>Tarikh :</label>
                    <s:text id="tarikh" name="report_p_tarikh" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
            </c:if>
            <c:if test="${reportname eq 'HSL_R_17_MLK.rdf'}">
                <p>
                    <label>Tarikh Pungutan :</label>
                    <s:text id="trh_pungutan" name="report_p_trh_kutipan" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
            </c:if>

            <c:if test="${ reportname eq 'HSL_R_25_MLK.rdf'
                           or reportname eq 'HSL_R_48_MLK.rdf'
                           or reportname eq 'HSL_R_49_MLK.rdf'}">
                  <p>
                      <label>Bulan :</label>
                      <s:select id="bulan" name="report_p_bulan" style="width:145px;">
                          <%--<s:option value="">--Sila Pilih--</s:option>--%>
                          <s:option value="01">Januari</s:option>
                          <s:option value="02">Februari</s:option>
                          <s:option value="03">Mac</s:option>
                          <s:option value="04">April</s:option>
                          <s:option value="05">May</s:option>
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

            <c:if test="${reportname eq 'HSL_R_12_MLK.rdf'
                          or reportname eq 'HSL_R_13_MLK.rdf'
                          or reportname eq 'HSL_R_14_MLK.rdf'
                          or reportname eq 'HSL_R_37_MLK.rdf'
                          or reportname eq 'HSL_R_25_MLK.rdf'}">
                  <p>
                      <label>Tahun :</label>
                      <s:select id="tahun" name="report_p_tahun" style="width:145px;">
                          <%--<s:option value="">--Sila Pilih--</s:option>--%>
                          <s:options-collection collection="${actionBean.listYear}"/>
                      </s:select>
                  </p>
            </c:if>
            <c:if test="${reportname eq 'HSL_R_02_MLK.rdf'
                          or reportname eq 'HSL_R_01_MLK.rdf'
                          or reportname eq 'HSL_R_68_MLK.rdf'
                          or reportname eq 'HSL_R_03_MLK.rdf'
                          or reportname eq 'HSL_R_04_MLK.rdf'
                          or reportname eq 'HSL_PP_MLK.rdf'
                  }">
                <p>
                    <label>Mod Bayar :</label>
                    <s:select name="report_p_mod_bayar" style="width:145px;">
                        <s:option value="">Semua</s:option>
                        <s:option value="B">Biasa</s:option>
                        <s:option value="L">Lewat</s:option>
                    </s:select>
                </p>
            </c:if>
            <%--<c:if test="${}">
                <p>
                    <label>Cara Bayaran :</label>
                    <s:select name="report_p_kod_cara_bayar" style="width:145px;">
                        <s:option value="">Semua</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodCaraBayaran}" label="nama" value="kod"/>
                    </s:select>
                </p>
           </c:if>--%> 
            <%--<c:if test="${reportname eq 'HSL_R_02_MLK.rdf'
                          or reportname eq 'HSL_R_01_MLK.rdf'
                          or reportname eq 'HSL_R_03_MLK.rdf'
                          or reportname eq 'HSL_R_04_MLK.rdf'
                          or reportname eq 'HSL_PP_MLK.rdf'
                          }">
                <p>
                    <label>Status :</label>
                    <s:select name="report_p_sts" style="width:145px;">
                        <s:option value="SEMUA">Semua</s:option>
                        <s:option value="LUAR">Luar</s:option>
                        <s:option value="TEMPATAN">Tempatan</s:option>
                    </s:select>
                </p>
            </c:if>--%>


            <c:if test="${reportname eq 'HSL_R_41_MLK.rdf'}">
                <p>
                    <label>Agensi :</label>
                    <s:select name="report_p_kod_agensi" style="width:145px;">
                        <s:option value="">Semua</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodAgensiKutipan}" label="nama" value="kod"/>
                    </s:select>
                </p>
            </c:if>

            <c:if test="${reportname eq 'HSL_R_06_MLK.rdf'}">
                <p>
                    <label>Agensi :</label>
                    <s:select name="report_p_kod_agensi" style="width:145px;">
                        <s:option value="">Semua</s:option>
                        <s:options-collection collection="${actionBean.senaraiKodAgensiKutipan}" label="nama" value="kod"/>
                    </s:select>
                </p>
            </c:if>

            <c:if test="${reportname eq 'HSL_R_63_MLK.rdf'
                          or reportname eq 'HSL_R_24_MLK.rdf'
                          or reportname eq 'HSL_R_71_MLK.rdf'
                          or reportname eq 'HSL_R_72_MLK.rdf'
                          or reportname eq 'HSL_R_23_MLK.rdf'}">
                  <p>
                      <label>Jenis Hakmilik :</label>
                      <s:select name="report_p_kod_hakmilik" style="width:200px;">
                          <s:option value="">SEMUA</s:option>
                          <s:options-collection collection="${listUtil.senaraiKodHakmilik}" label="nama" value="kod"/>
                      </s:select>
                  </p>
            </c:if>

            <c:if test="${reportname eq 'HSL_R_23_MLK.rdf'  
                          or reportname eq 'HSL_R_72_MLK.rdf'}">
                  <p>
                      <label>Nama Pemilik :</label>
                      <s:text id="nama" name="report_p_nama_pemilik" style="width:200px;"/>
                  </p>
            </c:if>

            <c:if test="${reportname eq 'HSL_R_63_MLK.rdf'
                          or reportname eq 'HSL_R_23_MLK.rdf'
                          or reportname eq 'HSL_R_71_MLK.rdf'
                          or reportname eq 'HSL_R_72_MLK.rdf'
                          or reportname eq 'HSL_R_24_MLK.rdf'}">
                  <p>
                      <label><em>*</em>Amaun Minimum :</label>
                      <s:text id="amaun" name="report_p_min_amt" onkeyup="validateNumber(this,this.value);"/>&nbsp;
                  </p>
                  <p>
                      <label>Kategori Pemilik :</label>
                      <s:select name="report_p_katg_pemilik" style="width:145px;">
                          <s:option value="">Semua</s:option>
                          <s:options-collection collection="${listUtil.senaraiKodBangsa}" label="nama" value="kod"/>
                      </s:select>
                  </p>
            </c:if>


            <c:if test="${reportname eq 'HSL_BUKU_TUNAI_MLK.rdf'

                  }">  
                <p>
                    <label><em>*</em>Cawangan :</label>
                    <s:select id="caw" name="report_p_kod_caw" style="width:260px;" disabled="${disable}" onchange="doFilterDaerah(this.value);">
                        <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                    </s:select>
                    <s:hidden name="report_p_kod_caw" value="${actionBean.kodCaw}"/>
                </p>
                <p>
                    <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
            </c:if>
            <c:if test="${reportname eq 'HSL_BUKU_TUNAI_MLK.rdf'
                  }">
                <p>
                    <label>Tarikh Sehingga :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label>Cara Bayaran :</label>
                    <s:select name="report_p_cara_bayar" style="width:145px;">
                        <s:option value="">Semua</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodCaraBayaran}" label="nama" value="kod"/>
                    </s:select>
                </p>
            </c:if>
                <c:if test="${reportname eq 'HSL_R_19_MLK.rdf'
                  }">
                <p>
                    <label>Jenis Pemohon :</label>
                    <s:select name="report_p_jenis_pemohon" style="width:145px;">
                         <s:option value="">Semua</s:option>
                        <s:option value="Syarikat">Syarikat</s:option>
                        <s:option value="Individu">Individu</s:option>
                    </s:select>
                </p>
                <p>
                    <label>Kategori Kelulusan :</label>
                    <s:select name="report_p_katg_lulus" style="width:145px;">
                         <s:option value="">Semua</s:option>
                        <s:option value="PTG">PTG</s:option>
                        <s:option value="PTD">PTD</s:option>
                        
                    </s:select>
                </p>
            </c:if>
            <br>
            <p align="center">
                <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit(this.form);"/>&nbsp;
                <c:if test="${reportname ne 'HSL_R_61_MLK.rdf'}">
                    <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
                </c:if>
                <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
            </p>

        </fieldset >
    </div>

</s:form>
