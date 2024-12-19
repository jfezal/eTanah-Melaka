<%-- 
    Document   : laporan_maklumaturusan_param
    Created on : May 18, 2010, 5:02:51 PM
    Author     : wan.fairul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<html>
    <head>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>


        <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">

    <script type="text/javascript">
        $(document).ready(function() {
            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
            var m = document.getElementById('rn');
            //            alert(m.value);
            $("#pendaftarDummy").show();
            $("#pendaftarPTG").hide();
            $("#pendaftarMT").hide();
            $("#pendaftarJasin").hide();
            $("#pendaftarAG").hide();

            $("#senaraiBPMdummy").show();
            $("#senaraiBPMptg").hide();
            $("#senaraiBPMmt").hide();
            $("#senaraiBPMjs").hide();
            $("#senaraiBPMag").hide();
            $("#nPenyerah").hide();

            $("#pHari").show();
            $("#pBulan").hide();
            $("#purata_mengikut").change(function() {
                if ($("#purata_mengikut").val() === "h") {
                    $("#pHari").show();
                    $("#pBulan").hide();
                    $("#purata_hari").val("");
                    $("#purata_bulan").val("");
                } else {
                    $("#pHari").hide();
                    $("#pBulan").show();
                    $("#purata_hari").val("");
                    $("#purata_bulan").val("");
                }
            });
            var today = new Date();
            var y = today.getFullYear();
            $("#tahun").val(y);
        });

        function ReplaceAll(Source, stringToFind, stringToReplace) {
            var temp = Source;
            var index = temp.indexOf(stringToFind);
            while (index != -1) {
                temp = temp.replace(stringToFind, stringToReplace);
                index = temp.indexOf(stringToFind);
            }
            return temp;
        }

        function changePejabat(val) {
            var no = val;

            if (no == '00') {
                $("#pendaftarDummy").hide();
                $("#pendaftarPTG").show();
                $("#pendaftarMT").hide();
                $("#pendaftarJasin").hide();
                $("#pendaftarAG").hide();

                $("#senaraiBPMdummy").hide();
                $("#senaraiBPMptg").show();
                $("#senaraiBPMmt").hide();
                $("#senaraiBPMjs").hide();
                $("#senaraiBPMag").hide();
            }
            else if (no == '01') {
                $("#pendaftarDummy").hide();
                $("#pendaftarPTG").hide();
                $("#pendaftarMT").show();
                $("#pendaftarJasin").hide();
                $("#pendaftarAG").hide();

                $("#senaraiBPMdummy").hide();
                $("#senaraiBPMptg").hide();
                $("#senaraiBPMmt").show();
                $("#senaraiBPMjs").hide();
                $("#senaraiBPMag").hide();
            }
            else if (no == '02') {
                $("#pendaftarDummy").hide();
                $("#pendaftarPTG").hide();
                $("#pendaftarMT").hide();
                $("#pendaftarJasin").show();
                $("#pendaftarAG").hide();

                $("#senaraiBPMdummy").hide();
                $("#senaraiBPMptg").hide();
                $("#senaraiBPMmt").hide();
                $("#senaraiBPMjs").show();
                $("#senaraiBPMag").hide();
            }
            else if (no == '03') {
                $("#pendaftarDummy").hide();
                $("#pendaftarPTG").hide();
                $("#pendaftarMT").hide();
                $("#pendaftarJasin").hide();
                $("#pendaftarAG").show();

                $("#senaraiBPMdummy").hide();
                $("#senaraiBPMptg").hide();
                $("#senaraiBPMmt").hide();
                $("#senaraiBPMjs").hide();
                $("#senaraiBPMag").show();
            }
            else {
                $("#pendaftarDummy").show();
                $("#pendaftarPTG").hide();
                $("#pendaftarMT").hide();
                $("#pendaftarJasin").hide();
                $("#pendaftarAG").hide();

                $("#senaraiBPMdummy").show();
                $("#senaraiBPMptg").hide();
                $("#senaraiBPMmt").hide();
                $("#senaraiBPMjs").hide();
                $("#senaraiBPMag").hide();
            }
        }

        function doSubmit(f) {
            var report = '${actionBean.reportName}';
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });

            if ($('#trh_mula').val() == '') {
                alert("Sila Masukkan Tarikh Mula");
                $.unblockUI();
            } else if ($('#trh_tamat').val() == '') {
                alert("Sila Masukkan Tarikh Tamat");
                $.unblockUI();
            } else if ($('#bulan').val() == '') {
                alert("Sila Masukkan Bulan");
                $.unblockUI();
            } else if ($('#hari').val() == '') {
                alert("Sila Masukkan Hari");
                $.unblockUI();
            } else if ($('#tahun').val() == '') {
                alert("Sila Masukkan Tahun");
                $.unblockUI();
            } else if ($('#bil_pemilik').val() == '') {
                alert("Sila Masukkan Bilangan Pemilik");
                $.unblockUI();
            } else if ($('#bil_kaveat').val() == '') {
                alert("Sila Masukkan Bilangan Kaveat");
                $.unblockUI();
            } else if ((report == 'ETMIS11_1.rdf') || (report == 'ETMIS42_1.rdf')) {
                var strNama = ReplaceAll($('#nama').val(), " ", "_");
                var form = $(f).formSerialize();
                f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?report_p_nama_pemilik=" + strNama + "&" + form;
                f.submit();
            } else if ((report == 'regPrestasiPendaftar.rdf')) {
                var month = $('#bulan').val();
                var year =  $('#tahun').val();       
                var montyear = month + "/" + year;                
                var form = $(f).formSerialize();
                f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?report_p_blnthn=" + montyear + "&" + form;
                f.submit();
            } else if((report == 'CetakanSemulaMLK.rdf')){
                var form = $(f).formSerialize();
                var hakmilik = $('#id_hakmilik').val();
                f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?report_p_id_hakmilik=" + hakmilik + "&" + form;
                f.submit();
            }
            else {
                var form = $(f).formSerialize();
                f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?" + form;
                f.submit();
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

        function dateValidation2(id, value) {
            var tMula = $('#trh_mula').val();
            var tTamat = $('#trh_tamat').val();
            var tMa = tMula.split('/');
            var tTm = tTamat.split('/');
            var totalM = tMa[2] + tMa[1] + tMa[0];
            var totalT = tTm[2] + tTm[1] + tTm[0];
            if (tMula == '') {
                alert("Sila masukkan Tarikh Mula");
                $('#' + id).val("");
            }
            else if (totalT < totalM) {
                alert("Tarikh Tamat tidak sesuai.");
                $('#' + id).val("");
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
            var strValidCharacters = "1234567890";
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

        function doFilterDaerah(kodCaw2) {
            if (kodCaw2 != null) {
                var url = '${pageContext.request.contextPath}/laporanMaklumatUrusanMis?doFilterDaerahBPM&kodCawangan=' + kodCaw2;
                $.get(url,
                        function(data) {
                            $('#display').html(data);
                            $('#caw').val(kodCaw2);
                            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                        });
            }
        }

        function filterDaerah(kodDaerah, report, reportName) {
  //      alert(kodDaerah);
            var url = '${pageContext.request.contextPath}/laporanMaklumatUrusanMis?penyukatanBPM&daerah=' + kodDaerah + '&namaReport=' + reportName + '&report=' + report;
            $.get(url,
                    function(data) {
                        $('#daerah4').html(data);
                        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                    }, 'html');
        }

        function filterCaw(kodDaerah, report, reportName) {
  //      alert(kodDaerah);
            var url = '${pageContext.request.contextPath}/laporanMaklumatUrusanMis?penyukatanBPM&daerah=' + kodDaerah + '&namaReport=' + reportName + '&report=' + report;
            $.get(url,
                    function(data) {
                        $('#daerah4').html(data);
                        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                    }, 'html');
        }

        function filterUrusan(kodPerserahan, frm, report) {
            var url = '${pageContext.request.contextPath}/laporanMaklumatUrusanMis?penyukatanUrusan&kodPerserahan=' + kodPerserahan + '&report=' + report;
            frm.action = url;
            frm.submit();
        }

        function padam3(id) {
            var serah = $('#jenisPenyerah').val();
            if (serah != "") {
                $('#idPenyerah').val("");
                $('#namaPenyerah').val("");
                $('#nPenyerah').show();
            } else {
                $('#nPenyerah').hide();
            }
        }
    </script>
</head>
<body>
    <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
         width="50" height="50" style="display: none" alt=""/>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<%--${actionBean.reportName}--%>
<div id="daerah4">   
    <s:form beanclass="etanah.view.laporan.LaporanMaklumatUrusanMis" id="laporanMaklumatUrusanMis">
        <s:hidden name="reportName" id="rn"/>
        <s:hidden name="reportName" id="rn"/>
        <%--${actionBean.reportName}--%>
        <div class="subtitle"> 
            <fieldset class="aras1">
                <legend>${actionBean.report}</legend>
                <c:set value="${actionBean.reportName}" var="reportname" /> 
                <%--${reportname}--%>
                <c:if test="${reportname eq 'ETMIS41_1.rdf'}">
                    <p>
                        <label>Pejabat :</label>
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">
                            <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                    </c:if>
                    </p>
                    <p>
                        <label>Jenis Hakmilik :</label>
                    <s:select name="report_p_kod_hakmilik" >
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodHakmilik}" label="nama" value="kod"/>
                    </s:select>
                    </p>
                    <p>
                        <label>No.Hakmilik :</label>
                    <s:text name="report_p_no_hakmilik"/>
                    </p>
                </c:if>

                <c:if test="${reportname eq 'ETMIS42_1.rdf'}">
                    <p>
                        <label>Pejabat :</label>
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">
                            <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                    </c:if>
                    </p>
                    <p>
                        <label>Nama :</label>
                    <s:text name="report_p_nama_pemilik" id="nama"/>
                    </p>
                    <p>
                        <label>Jenis Pengenalan :</label>
                    <s:select name="report_p_kod_pengenalan" id="" value="kod" >
                        <s:option value="">Pilih ...</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" />
                    </s:select>
                    </p>
                    <p>
                        <label>No. Pengenalan :</label>
                    <s:text name="report_p_no_pengenalan"/>
                    </p>
                </c:if>

                <c:if test="${reportname eq 'ETMIS24_1.rdf'
                              or reportname eq 'ETMIS71_1.rdf'
                              or reportname eq 'ETMIS24_1NS.rdf'}">
                    <p>
                        <label>Pejabat :</label>
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}" style="width:210px;">
                            <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                    </c:if>
                    </p>
                    <p>
                        <label>Jenis Kategori Tanah :</label>
                    <s:select name="report_p_kod_katg_tanah"  style="width:210px;">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod"/>
                    </s:select>
                    </p>
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;
                    <font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation2(this.id, this.value)"/>&nbsp;
                    <font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>

                <c:if test="${reportname eq 'ETMIS25_1.rdf'}">
                    <p>
                        <label>Pejabat :</label>
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">
                            <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                    </c:if>
                    </p>
                    <p>
                        <label>Jenis Bangsa :</label>
                    <s:select name="report_p_kod_bangsa" >
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodBangsa}" label="nama" value="kod"/>
                    </s:select>
                    </p>
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;
                    <font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;
                    <font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>

                <c:if test="${reportname eq 'ETMIS16_1.rdf'}">
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <p>
                            <label>Pejabat :</label>
                            <b>Pejabat Pendaftar</b>
                        <s:hidden name="" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                        <p>
                            <label>Daerah :</label>
                        <s:select name="report_p_kod_daerah" id="daerah" style="width:210px;" onchange="filterDaerah(this.value, '${actionBean.report}', '${actionBean.reportName}');">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"/>
                        </s:select>
                        </p>
                        <c:if test="${actionBean.kodNeg ne 'n9'}">
                            <p>
                                <label>Bandar/Pekan/Mukim :</label>
                            <s:select name="report_p_kod_bpm" style="width:210px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <c:forEach items="${actionBean.senaraiBPM}" var="i" >
                                    <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                                </c:forEach>
                            </s:select>
                            </p>
                        </c:if>
                    </c:if>

                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <p>
                            <label>Pejabat :</label>
                            ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                        <c:if test="${actionBean.kodNeg ne 'n9'}">
                            <p>
                                <label>Bandar/Pekan/Mukim :</label>
                            <s:select name="report_p_kod_bpm" style="width:210px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <c:forEach items="${actionBean.senaraiBPM04}" var="i" >
                                    <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                                </c:forEach>
                            </s:select>
                            </p>
                        </c:if>
                    </c:if>
                    <p>
                        <label>Bilangan Pemilik Yang Melebihi :</label>
                    <s:text name="report_p_bil_pemilik" style="width:210px;"/>&nbsp;
                    </p>
                </c:if>

                <c:if test="${reportname eq 'ETMIS23_1.rdf'
                              or reportname eq 'ETMIS15_1.rdf'
                              or reportname eq 'ETMIS14_1.rdf'
                              or reportname eq 'ETMIS38_1.rdf'
                              or reportname eq 'ETMIS32_1.rdf'
                              or reportname eq 'ETMIS39_1.rdf'
                              or reportname eq 'ETMIS36_1.rdf'
                              or reportname eq 'ETMIS35_1.rdf'
                              or reportname eq 'ETMIS37_1.rdf'
                              or reportname eq 'ETMIS70_1.rdf'
                              or reportname eq 'ETMIS26_1.rdf'
                              or reportname eq 'ETMIS06_1.rdf'
                              or reportname eq 'ETMIS08_1.rdf'
                              or reportname eq 'ETMIS10_1.rdf'
                              or reportname eq 'ETMIS12_1.rdf'
                              or reportname eq 'ETMIS20_1.rdf'
                              or reportname eq 'ETMIS17_1.rdf'
                              or reportname eq 'ETMIS21_1.rdf'
                              or reportname eq 'ETMIS55_1.rdf'}">  
                    <p>
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <p>
                            <label>Pejabat :</label>
                            <b>Pejabat Pendaftar</b>
                        <s:hidden name="" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                        <p>
                            <label>Daerah :</label>
                        <s:select name="report_p_kod_daerah" id="daerah" style="width:210px;" onchange="filterDaerah(this.value, '${actionBean.report}', '${actionBean.reportName}');">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"/>
                        </s:select>
                        </p>
                        <c:if test="${actionBean.kodNeg ne 'n9'}">
                            <p>
                                <label>Bandar/Pekan/Mukim :</label>
                            <s:select name="report_p_kod_bpm" style="width:210px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <c:forEach items="${actionBean.senaraiBPM}" var="i" >
                                    <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                                </c:forEach>
                            </s:select>
                            </p>
                        </c:if>
                    </c:if>

                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <p>
                            <label>Pejabat :</label>
                            ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                        <c:if test="${actionBean.kodNeg ne 'n9'}">
                            <p>
                                <label>Bandar/Pekan/Mukim :</label>
                            <s:select name="report_p_kod_bpm" style="width:210px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <c:forEach items="${actionBean.senaraiBPM04}" var="i" >
                                    <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                                </c:forEach>
                            </s:select>
                            </p>
                        </c:if>
                    </c:if>
                <c:if test="${reportname ne 'ETMIS20_1.rdf'}">  
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;
                    <font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;
                    <font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>
                </c:if>

                <c:if test="${reportname eq 'ETMIS12_1NS.rdf'
                              or reportname eq 'ETMIS10_1NS.rdf'
                              or reportname eq 'ETMIS55_1NS.rdf'
                              or reportname eq 'ETMIS06_1NS.rdf'
                              or reportname eq 'ETMIS20_1NS.rdf'
                              or reportname eq 'ETMIS08_1NS.rdf'
                              or reportname eq 'ETMIS59_1NS.rdf'}">  
                    <p>
                        <!--ptg-->
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <p>
                            <label>Pejabat :</label>
                            <b>Pejabat Pendaftar</b>
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                        <p>
                            <label>Daerah :</label>
                        <s:select name="report_p_kod_daerah" id="daerah" style="width:210px;" onchange="filterDaerah(this.value, '${actionBean.report}', '${actionBean.reportName}');">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"/>
                        </s:select>
                        </p>
                        <!--comment by penyu-->
                        <!--                        <p>
                                                      <label>Bandar/Pekan/Mukim :</label>
                        <s:select name="report_p_kod_bpm" style="width:210px;">
                          <s:option value="">--Sila Pilih--</s:option>
                          <c:forEach items="${actionBean.senaraiBPM}" var="i" >
                            <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                          </c:forEach>
                        </s:select>
                  </p>-->
                    </c:if>

                    <!--ptd-->
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <p>
                            <label>Pejabat :</label>
                            ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                        <%--<c:if test="${actionBean.kodNeg ne 'n9'}">--%>
                        <!--                        <p>
                                                      <label>Bandar/Pekan/Mukim :</label>
                        <s:select name="report_p_kod_bpm" style="width:210px;">
                          <s:option value="">--Sila Pilih--</s:option>
                          <c:forEach items="${actionBean.senaraiBPM05}" var="i" >
                            <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                          </c:forEach>
                        </s:select>
                  </p>-->
                        <%--</c:if>--%>
                    </c:if>
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;
                    <font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;
                    <font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>

                <c:if test="${reportname eq 'ETMIS37_1NS.rdf'}">
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <p>
                            <label>Pejabat :</label>
                            <b>Pejabat Pendaftar</b>
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                        <c:choose>
                            <c:when test="${actionBean.kodNeg eq 'n9'}">
                                <p>
                                    <label>Daerah :</label>
                                <s:select name="report_p_kod_daerah" id="daerah" style="width:210px;">
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"/>
                                </s:select>
                                </p>
                            </c:when>
                            <c:otherwise>
                                <p>
                                    <label>Daerah :</label>
                                <s:select name="report_p_kod_daerah" id="daerah" style="width:210px;" onchange="filterDaerah(this.value, '${actionBean.report}', '${actionBean.reportName}');">
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"/>
                                </s:select>
                                </p>
                                <p>
                                    <label>Bandar/Pekan/Mukim :</label>
                                <s:select name="report_p_kod_bpm" style="width:210px;">
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <c:forEach items="${actionBean.senaraiBPM}" var="i" >
                                        <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                                    </c:forEach>
                                </s:select>
                                </p>
                            </c:otherwise>
                        </c:choose>
                    </c:if>

                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <p>
                            <label>Pejabat :</label>
                            ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                    </c:if>
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;
                    <font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;
                    <font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>

                <c:if test="${reportname eq 'ETMIS17_1NS.rdf' or reportname eq 'ETMIS21_1NS.rdf'}">
                    <!--ptg-->
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <p>
                            <label>Pejabat :</label>
                            <b>Pejabat Pendaftar</b>
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                        <p>
                            <label>Daerah :</label>
                        <s:select name="report_p_kod_daerah" id="daerah" style="width:210px;" onchange="filterDaerah(this.value, '${actionBean.report}', '${actionBean.reportName}');">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"/>
                        </s:select>
                        </p>
                        <c:if test="${actionBean.kodNeg ne 'n9'}">
                            <p>
                                <label>Bandar/Pekan/Mukim :</label>
                            <s:select name="report_p_kod_bpm" style="width:210px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <c:forEach items="${actionBean.senaraiBPM}" var="i" >
                                    <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                                </c:forEach>
                            </s:select>
                            </p>
                        </c:if>
                    </c:if>

                    <!--ptd-->
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <p>
                            <label>Pejabat :</label>
                            ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                        <c:if test="${actionBean.kodNeg ne 'n9'}">
                            <p>
                                <label>Bandar/Pekan/Mukim :</label>
                            <s:select name="report_p_kod_bpm" style="width:210px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <c:forEach items="${actionBean.senaraiBPM05}" var="i" >
                                    <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                                </c:forEach>
                            </s:select>
                            </p>
                        </c:if>
                    </c:if>
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;
                    <font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;
                    <font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>
                <!--penyu_end-->

                <c:if test="${reportname eq 'ETMIS57_1.rdf'
                              or reportname eq 'ETMIS78_1NS.rdf'
                              or reportname eq 'ETMIS57_1NS.rdf'}">
                    <p>
                        <label>Pejabat :</label>
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">
                            <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                    </c:if>
                    </p>
                    <p>
                        <label>Jenis Hakmilik :</label>
                    <s:select name="report_p_kod_hakmilik" >
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodHakmilik}" label="nama" value="kod"/>
                    </s:select>
                    </p>
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;
                    <font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;
                    <font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if> 
                <c:if test="${reportname eq 'ETMIS_ADD_06.rdf'}">
                    <!--penyu-->
                    <!--ptg-->
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <p>
                            <label>Pejabat :</label>
                            <b>Pejabat Pendaftar</b>
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                        <p>
                            <label>Daerah :</label>
                        <s:select name="report_p_kod_daerah" id="daerah" style="width:210px;" onchange="filterDaerah(this.value, '${actionBean.report}', '${actionBean.reportName}');">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"/>
                        </s:select>
                        </p>
                    </c:if>
                    <!--ptd-->
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <p>
                            <label>Pejabat :</label>
                            ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                    </c:if>
                    <!--penyu_end-->                          
                    <p>
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <label>Jenis Hakmilik : </label>
                        <b> HSD - Hakmilik Sementara Daftar</b>
                        <s:hidden name="report_p_kod_hakmilik" value="HSD" id="hakmilik" style="width:100px"/>
                        <br />
                    </c:if>
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">                                  
                        <label>Jenis Hakmilik : </label>
                        <b> HSM - Hakmilik Sementara Mukim</b>
                        <s:hidden name="report_p_kod_hakmilik" value="HSM" id="hakmilik" style="width:100px"/>
                        <br /> 
                    </c:if>
                    </p>
                    <!--penyu-->
                    <p>
                        <label>Tahun :</label>
                    <s:select name="report_p_tahun" id="tahun" style="width:100px">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${actionBean.listYear}"/>
                    </s:select>
                    </p>
                </c:if>         

                <c:if test="${reportname eq 'ETMIS11_1.rdf'}">
                    <p>
                        <label>Pejabat :</label>
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">
                            <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                    </c:if>
                    </p>
                    <p>
                        <label>Nama :</label>
                    <s:text name="report_p_nama_pemilik" id="nama"/>
                    </p>
                    <p>
                        <label>No. Pengenalan :</label>
                    <s:text name="report_p_no_pengenalan"/>
                    </p>
                </c:if>

                <c:if test="${reportname eq 'ETMIS04_1.rdf'}">
                    <p>
                        <label>Pejabat :</label>
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">
                            <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                    </c:if>
                    </p>
                    <p>
                        <label>Jenis Pemilikan :</label>
                    <s:select name="report_p_pegangan">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="S">[S] SELAMA-LAMANYA </s:option>
                        <s:option value="P">[P] PAJAKAN</s:option>        
                    </s:select>
                    </p>
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;
                    <font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;
                    <font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>

                <!--penyu-->
                <!--Hakmilik Mengikut Bilangan Pemilik-->
                <c:if test="${reportname eq 'ETMIS16_1NS.rdf' or reportname eq 'ETMIS80_1NS.rdf' }">
                    <!--ptg-->
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <p>
                            <label>Pejabat :</label>
                            <b>Pejabat Pendaftar</b>
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                        <p>
                            <label>Daerah :</label>
                        <s:select name="report_p_kod_daerah" id="daerah" style="width:210px;" onchange="filterDaerah(this.value, '${actionBean.report}', '${actionBean.reportName}');">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"/>
                        </s:select>
                        </p>
                        <c:if test="${actionBean.kodNeg ne 'n9'}">
                            <p>
                                <label>Bandar/Pekan/Mukim :</label>
                            <s:select name="report_p_kod_bpm" style="width:210px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <c:forEach items="${actionBean.senaraiBPM}" var="i" >
                                    <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                                </c:forEach>
                            </s:select>
                            </p>
                        </c:if>
                    </c:if>

                    <!--ptd-->
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <p>
                            <label>Pejabat :</label>
                            ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                    </c:if>

                    <c:if test="${reportname eq 'ETMIS16_1NS.rdf'}">
                        <p>
                            <label><em>*</em>Bilangan Pemilik</label>
                        <s:text name="report_p_bil_pemilik" id="bil_pemilik"/>
                        </p>
                        <!--               Status bangsa dropdown list for Laporan Hakmilik Mengikut Bilangan Pemilik -->
                        <p>
                            <label>Status Bangsa :</label>
                        <s:select name="report_p_kod_bangsa" >
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodBangsa}" label="nama" value="kod"/>
                        </s:select>
                        </p>  
                    </c:if>
                </c:if>
                <!--penyu_end-->

                <!--                         Report Perserahan Bulanan        -->
                <c:if test="${reportname eq 'ETMIS65A_1.rdf'}">
                    <p>
                        <label>Pejabat :</label>
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">
                            <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                    </c:if>
                    </p>
                    <p>
                        <label>Bulan Perserahan :</label>
                    <s:select name="report_p_bulan" id="bulan" >
                        <s:option value="">--Sila Pilih--</s:option>
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
                    <p>
                        <label>Tahun Perserahan :</label>
                    <s:select name="report_p_tahun" id="tahun" style="width:100px">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${actionBean.listYear}"/>
                    </s:select>
                    </p>
                </c:if>

                <c:if test="${reportname eq 'ETMIS03_1.rdf'
                              or reportname eq 'ETMIS47A_1.rdf'
                              or reportname eq 'ETMIS54_1.rdf'
                              or reportname eq 'ETMIS01_1.rdf'
                              or reportname eq 'ETMIS34_1.rdf'
                              or reportname eq 'ETMIS66_1.rdf'
                              or reportname eq 'ETMIS72_1.rdf'
                              or reportname eq 'ETMIS43_1.rdf'
                              or reportname eq 'ETMIS44_1.rdf'
                              or reportname eq 'ETMIS33_1.rdf'
                              or reportname eq 'ETMIS67_1.rdf'
                              or reportname eq 'ETMIS08A_1.rdf'
                              or reportname eq 'ETMIS07_1.rdf'
                              or reportname eq 'ETMIS64_1.rdf'
                              or reportname eq 'ETMIS63_1.rdf'
                              or reportname eq 'ETMIS83_1.rdf'
                              or reportname eq 'ETMIS82_1.rdf'}">
                    <p>
                        <!--ptg-->
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <!--En Zamrud nak
                        <c:if test="${actionBean.peng.jawatan eq 'Pendaftar Hakmilik Geran Tanah Negeri Melaka'}">
                            <p>
                                <label>Pejabat Daerah :</label>
                                <s:select name="report_p_kod_caw" id="daerah" style="width:210px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                                </s:select>
                            </p>                        
                        </c:if>
                        <c:if test="${actionBean.peng.jawatan ne 'Pendaftar Hakmilik Geran Tanah Negeri Melaka'}">
                        <p>
                            <label>Pejabat :</label>
                            <b>Pejabat Pendaftar</b>
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                        </c:if> -->
                        
                        <!--Pn Zafirah nak (no.aduan 10906, 04/03/2024) -->
                        <c:if test="${actionBean.peng.perananUtama.kod eq '4'}">
                            <p>
                                <label>Pejabat :</label>
                                <s:select name="report_p_kod_caw" id="daerah" style="width:210px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                                </s:select>
                            </p>                        
                        </c:if>
                        <c:if test="${actionBean.peng.perananUtama.kod ne '4'}">
                        <p>
                            <label>Pejabat :</label>
                            <b>Pejabat Pendaftar</b>
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                        </c:if>
                        
                        <c:if test="${reportname ne 'ETMIS66_1.rdf' and reportname ne 'ETMIS72_1.rdf' and reportname ne 'ETMIS43_1.rdf' and reportname ne 'ETMIS44_1.rdf' and reportname ne 'ETMIS01_1.rdf' and reportname ne 'ETMIS63_1.rdf'}">
                            <p>
                                <label>Daerah :</label>
                            <s:select name="report_p_kod_daerah" id="daerah" style="width:210px;" onchange="filterDaerah(this.value, '${actionBean.report}', '${actionBean.reportName}');">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"/>
                            </s:select>
                            </p>
                        </c:if>
                        <c:if test="${actionBean.kodNeg ne 'n9'}">
                            <c:if test="${reportname ne 'ETMIS66_1.rdf' and reportname ne 'ETMIS72_1.rdf' and reportname ne 'ETMIS43_1.rdf' and reportname ne 'ETMIS44_1.rdf' and reportname ne 'ETMIS01_1.rdf' and reportname ne 'ETMIS63_1.rdf'}">
                                <p>
                                    <label>Bandar/Pekan/Mukim :</label>
                                <s:select name="report_p_kod_bpm" style="width:210px;">
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <c:forEach items="${actionBean.senaraiBPM}" var="i" >
                                        <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                                    </c:forEach>
                                </s:select>
                                </p>
                            </c:if>
                        </c:if>
                    </c:if>

                    <!--ptd-->
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <p>
                            <label>Pejabat :</label>
                            ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                        <!--
                        <p>
                              <label>Bandar/Pekan/Mukim :</label>
                        <s:select name="report_p_kod_bpm" style="width:210px;">
                          <s:option value="">--Sila Pilih--</s:option>
                          <c:forEach items="${actionBean.senaraiBPM04}" var="i" >
                            <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                          </c:forEach>
                        </s:select>
                  </p>
                        -->

                    </c:if>
                    <!--<c:if test="${reportname ne 'ETMIS01_1.rdf'}">
                        <p>
                            <label>Tarikh Mula :</label>
                        <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                                onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                        </p>
                        <p>
                            <label>Tarikh Tamat :</label>
                        <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                                onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                        </p>
                    </c:if>
                    <c:if test="${reportname eq 'ETMIS01_1.rdf'}">
                        <p>
                            <label>Tahun :</label>
                        <s:select name="report_p_tahun" id="tahun" style="width:100px">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${actionBean.listYear}"/>
                        </s:select>
                        </p>
                    </c:if>-->
                    <c:if test="${reportname ne 'ETMIS66_1.rdf'}">
                        <p>
                            <label>Tarikh Mula :</label>
                            <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                                    onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                        </p>
                        <p>
                            <label>Tarikh Tamat :</label>
                            <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                                    onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                        </p>
                    </c:if>
                    <c:if test="${reportname eq 'ETMIS66_1.rdf'}">
                        <p>
                            <label>Tarikh :</label>
                        <s:text name="report_p_tarikh" class="datepicker" formatPattern="dd/MM/yyyy"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                        </p>
                    </c:if>
                </c:if>

                <c:if test="${reportname eq 'ETMIS47A_1NS.rdf'
                              or reportname eq 'ETMIS54_1NS.rdf'
                              or reportname eq 'ETMIS43_1NS.rdf'
                              or reportname eq 'ETMIS44_1NS.rdf'
                              or reportname eq 'ETMIS65_1NS.rdf'
                              or reportname eq 'ETMIS50_1NS.rdf'
                              or reportname eq 'ETMIS49_1NS.rdf'
                              or reportname eq 'ETMIS77_1NS.rdf'
                              or reportname eq 'ETMIS13_1NS.rdf'
                              or reportname eq 'ETMIS07_1NS.rdf'}">                
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <p>
                            <label>Pejabat :</label>
                            <b>Pejabat Pendaftar</b>
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                        <c:if test="${reportname ne 'ETMIS43_1NS.rdf' and reportname ne 'ETMIS44_1NS.rdf'}">
                            <p>
                                <label>Daerah :</label>
                                <%--<s:select name="report_p_kod_daerah" id="daerah" style="width:210px;" 
                                          onchange="filterDaerah(this.value,'${actionBean.report}','${actionBean.reportName}');">--%>
                            <s:select name="report_p_kod_daerah" id="daerah" style="width:210px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"/>
                            </s:select>
                            </p>
                        </c:if>
                    </c:if>

                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <p>
                            <label>Pejabat :</label>
                            ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                    </c:if>

                    <!--Jenis Urusan for Laporan Perserahan Urusniaga -->
                    <!--                          <p>
                    <c:if test= "${reportname eq 'ETMIS50_1NS.rdf'}">
                       <label>Urusan :</label>
                      <s:select name="report_p_urusan" >
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodUrusanKodSerahSC}" label="nama" value="kod" sort="nama"/>--%>
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:option value="Gadaian">Gadaian</s:option>
                        <s:option value="Gadaian Pajakan">Gadaian Pajakan</s:option>
                        <s:option value="Ismen">Ismen</s:option>
                        <s:option value="Pajakan">Pajakan</s:option>
                        <s:option value="Perintah Jual Danaharta">Perintah Jual Danaharta</s:option>
                        <s:option value="Perintah Jual Mahkamah">Perintah Jual Mahkamah</s:option>
                        <s:option value="Perintah Jual Pentadbir">Perintah Jual Pentadbir</s:option>
                        <s:option value="Pindahmilik">Pindahmilik</s:option>
                        <s:option value="Tenansi">Tenansi</s:option>
                      </s:select>
                    </c:if>
                </p>--><!--Jenis Perserahan for Laporan Tunggakan Kerja Urusniaga/Bukan Urusniaga-->
                    <p>
                    <c:if test= "${reportname eq 'ETMIS77_1NS.rdf'}">
                        <label>Jenis Perserahan :</label>
                        <s:select name="report_p_kod_serah" >
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:option value="SC">Urusniaga</s:option>
                            <s:option value="B">Bukan Urusniaga</s:option>
                            <s:hidden name="report_p_bil_hari" value="${actionBean.report_p_bil_hari}"/>
                        </s:select>                  
                    </c:if>
                    </p>   
                    <!--penyu-->
                    <c:if test= "${reportname eq 'ETMIS43_1NS.rdf'}">
                        <p>
                            <label>Nama Kerani :</label>
                        <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                            <s:select name="report_p_pguna" >
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiPenggunaKerani}" label="nama" value="nama" sort="nama"/>

                            </s:select>
                        </c:if>
                        <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                            <s:select name="report_p_pguna" >
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiPenggunaKeraniPTD}" label="nama" value="nama" sort="nama"/>
                            </s:select>
                        </c:if>
                        </p>
                    </c:if>
                    <c:if test= "${reportname eq 'ETMIS44_1NS.rdf'}">
                        <p>
                            <label>Nama Pendaftar :</label>
                        <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                            <s:select name="report_p_pguna" >
                                <s:option value="">--Sila Pilih--</s:option>
                                    <%--<s:options-collection collection="${listUtil.senaraiPenggunaPendaftar}" label="nama" value="nama" sort="nama"/>--%>
                                <s:options-collection collection="${actionBean.senaraiPendaftar}" label="nama" value="nama" sort="nama"/>
                            </s:select>
                        </c:if>
                        <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                            <s:select name="report_p_pguna" >
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiPenggunaPendaftarPTD}" label="nama" value="nama" sort="nama"/>
                            </s:select>
                        </c:if>
                        </p>
                    </c:if>
                    <!--penyu_tapitapeh_end-->
                    <!--buang etmis49_1ns urusan-->
                    <p>
                      <!-- <c:if test ="${reportname eq 'ETMIS49_1NS.rdf'}">
                               <label>Urusan :</label>
                        <%--<s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodUrusanKodSerah}" label="nama" value="kod" sort="nama"/>--%>
                
                        <%--<s:option value="">--Sila Pilih--</s:option>
                             <s:options-collection collection="${listUtil.urusanPendaftaranList}" label="nama" value="kod"/>--%>
                         
                        <s:select name="report_p_urusan" >
                          <s:option value="">--Sila Pilih--</s:option>
                          <s:option value="Pembatalan Urusniaga Warga Asing">Pembatalan Urusniaga Warga Asing</s:option>
                          <s:option value="Gadaian">Gadaian</s:option>
                          <s:option value="Ismen">Ismen</s:option>
                          <s:option value="Pembatalan Perlantikan Penjaga">Pembatalan Perlantikan Penjaga</s:option>
                          <s:option value="Perintah Jual">Perintah Jual</s:option>
                          <s:option value="Kaveat">Kaveat</s:option>
                          <s:option value="Perintah Larangan">Perintah Larangan</s:option>
                          <s:option value="Perintah Mahkamah">Perintah Mahkamah</s:option>
                          <s:option value="Perletakhakan">Perletakhakan</s:option>
                          <s:option value="Pajakan">Pajakan</s:option>
                          <s:option value="Pindahmilik Tanah">Pindahmilik Tanah</s:option>
                          <s:option value="Pengemaskinian Nama Dan Nombor Kad Pengenalan">Pengemaskinian Nama Dan Nombor Kad Pengenalan</s:option>
                          <s:option value="Pemegang Amanah">Pemegang Amanah</s:option>
                          <s:option value="Penghuni Seumur Hidup">Penghuni Seumur Hidup</s:option>
                          <s:option value="Right of Survivorship">Right of Survivorship</s:option>
                          <s:option value="Akta Sewaan Padi">Akta Sewaan Padi</s:option>
                          <s:option value="Tukar Alamat">Tukar Alamat</s:option>
                          <s:option value="Tenansi">Tenansi</s:option>
                          <s:option value="Turunmilik">Turunmilik</s:option>
                          <s:option value="Tukar Nama">Tukar Nama</s:option>
        
                        </s:select>                              
                      </c:if> faiz tutup arahan lokman -->
                    </p>

                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>
                    &nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>

                <c:if test ="${reportname eq 'ETMIS47_1.rdf'}">
                    <p>
                        <!--ptg-->
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <p>
                            <label>Pejabat :</label>
                            <%--<s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">--%>
                            <%--<s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>--%>
                            <b>Pejabat Pendaftar</b>
                        <s:hidden name="" value="${actionBean.peng.kodCawangan.kod}"/>
                        <%--</s:select>--%>
                        </p>
                        <p>
                            <label>Daerah :</label>
                        <s:select name="report_p_kod_daerah" id="daerah" style="width:210px;" onchange="filterDaerah(this.value, '${actionBean.report}', '${actionBean.reportName}');">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"/>
                            <%--<c:forEach items="${listUtil.senaraiKodDaerah}" var="i" >
                                <s:option value="${i.kod}">${i.kod} - ${i.nama}</s:option>
                            </c:forEach>--%> 
                        </s:select>
                        </p>
                        <c:if test="${actionBean.kodNeg ne 'n9'}">
                            <p>
                                <label>Bandar/Pekan/Mukim :</label>
                            <s:select name="report_p_kod_bpm" style="width:210px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <c:forEach items="${actionBean.senaraiBPM}" var="i" >
                                    <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                                </c:forEach>
                            </s:select>
                            </p>
                        </c:if>
                    </c:if>

                    <!--ptd-->
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <p>
                            <label>Pejabat :</label>
                            ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>

                        <p>
                            <label>Bandar/Pekan/Mukim :</label>
                        <s:select name="report_p_kod_bpm" style="width:210px;">
                            <s:option value="">--Sila Pilih--</s:option>
                            <c:forEach items="${actionBean.senaraiBPM04}" var="i" >
                                <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                            </c:forEach>
                        </s:select>
                        </p>
                    </c:if>
                    <p>     
                        <label>Jenis Surat :</label>
                        <%--<s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodUrusanKodSerah}" label="nama" value="kod" sort="nama"/>--%>

                        <%--<s:option value="">--Sila Pilih--</s:option>
                             <s:options-collection collection="${listUtil.urusanPendaftaranList}" label="nama" value="kod"/>--%>

                    <s:select name="report_p_urusan" >
						<s:option value="">--Sila Pilih--</s:option>
						<s:option value="SA">Surat Amanah</s:option>
						<s:option value="SB">Surat Kebenaran</s:option>
						<s:option value="SW">Suratkuasa Wakil</s:option>
                        <!--<s:option value="">--Sila Pilih--</s:option>
                        <s:option value="Pembatalan Urusniaga Warga Asing">Pembatalan Urusniaga Warga Asing</s:option>
                        <s:option value="Gadaian">Gadaian</s:option>
                        <s:option value="Ismen">Ismen</s:option>
                        <s:option value="Pembatalan Perlantikan Penjaga">Pembatalan Perlantikan Penjaga</s:option>
                        <s:option value="Perintah Jual">Perintah Jual</s:option>
                        <s:option value="Kaveat">Kaveat</s:option>
                        <s:option value="Perintah Larangan">Perintah Larangan</s:option>
                        <s:option value="Perintah Mahkamah">Perintah Mahkamah</s:option>
                        <s:option value="Perletakhakan">Perletakhakan</s:option>
                        <s:option value="Pajakan">Pajakan</s:option>
                        <s:option value="Pindahmilik Tanah">Pindahmilik Tanah</s:option>
                        <s:option value="Pengemaskinian Nama Dan Nombor Kad Pengenalan">Pengemaskinian Nama Dan Nombor Kad Pengenalan</s:option>
                        <s:option value="Pemegang Amanah">Pemegang Amanah</s:option>
                        <s:option value="Penghuni Seumur Hidup">Penghuni Seumur Hidup</s:option>
                        <s:option value="Right of Survivorship">Right of Survivorship</s:option>
                        <s:option value="Akta Sewaan Padi">Akta Sewaan Padi</s:option>
                        <s:option value="Tukar Alamat">Tukar Alamat</s:option>
                        <s:option value="Tenansi">Tenansi</s:option>
                        <s:option value="Turunmilik">Turunmilik</s:option>
                        <s:option value="Tukar Nama">Tukar Nama</s:option>
                        </p>-->
                    </s:select>                              

                    <%--</p>--%>

                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>

                <c:if test="${reportname eq 'ETMIS51_1.rdf'
                              or reportname eq 'ETMIS53_1.rdf'
                              or reportname eq 'ETMIS51_1NS.rdf'
                              or reportname eq 'ETMIS52_1NS.rdf'
                              or reportname eq 'ETMIS48_1.rdf'
                              or reportname eq 'ETMIS81_1.rdf'
                              or reportname eq 'ETMIS48_1NS.rdf'}">
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <p>
                            <label>Pejabat :</label>
                            <b>Pejabat Pendaftar</b>
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                        <c:if test="${reportname ne 'ETMIS51_1NS.rdf'}">
                            <p>
                                <label>Daerah :</label>
                                <%--<s:select name="report_p_kod_daerah" id="daerah" style="width:200px;" 
                                          onchange="filterDaerah(this.value,'${actionBean.report}','${actionBean.reportName}');">--%>
                            <s:select name="report_p_kod_daerah" id="daerah" style="width:200px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"/>
                            </s:select>
                            </p>
                        </c:if>
                    </c:if>

                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <p>
                            <label>Pejabat :</label>
                            ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                    </c:if>
                    <p>
                    <c:choose>   
                        <c:when test= "${reportname eq 'ETMIS51_1NS.rdf' or reportname eq 'ETMIS52_1NS.rdf'}">
                            <label>Jenis Perserahan :</label>
                            <s:select name="report_p_kod_serah" style="width:200px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:option value="SC">Surat Cara</s:option>
                                <s:option value="B">Bukan Urusniaga (Borang)</s:option>
                                <s:option value="N">Nota</s:option>
                                <s:option value="MH">Permohonan Hakmilik</s:option>
                                <s:option value="NB">Nota Pembetulan</s:option>
                            </s:select>
                        </c:when>
                        <c:when test="${reportname eq 'ETMIS81_1.rdf'}">
                            <label>Jenis Perserahan :</label>
                            <s:select name="report_p_kod_serah" style="width:200px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:option value="CR">Carian Rasmi</s:option>
                                <s:option value="CS">Carian Persendirian</s:option>
                                <s:option value="SS">Salinan Sah</s:option>

                            </s:select>
                        </c:when>
                        <c:when test="${reportname eq 'ETMIS51_1.rdf'}">
                            <label>Jenis Perserahan :</label>
                            <s:select name="report_p_kod_serah" style="width:200px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:option value="SC">Urusniaga</s:option>
                                <s:option value="B">Bukan Urusniaga</s:option>
                                <s:option value="N">Nota</s:option>
                                <s:option value="NB">Nota betul</s:option>
                                <s:option value="MH">Mohon Hakmilik</s:option>
                            </s:select>
                        </c:when>
                        <c:otherwise>  
                            <label>Jenis Perserahan :</label>
                            <s:select name="report_p_kod_serah" style="width:200px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:option value="SC">Urusniaga</s:option>
                                <s:option value="B">Bukan Urusniaga</s:option>
                                <s:option value="N">Nota</s:option>
                                <s:option value="NB">Nota betul</s:option>
                            </s:select>
                        </c:otherwise>
                    </c:choose>
                    </p>
                    <!--penyu_end-->
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" 
                            formatPattern="dd/MM/yyyy" style="width:200px;"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" 
                            formatPattern="dd/MM/yyyy" style="width:200px;"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>
                <c:if test="${reportname eq 'ETMIS69_1.rdf'}">
                    <p>
                        <label>Pejabat :</label>
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">
                            <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                    </c:if>
                    </p>
                    <p>
                        <label>Kod Unit Luas :</label>
                    <s:select name="report_p_kod_uom" >
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodUOMByLuas}" label="nama" value="kod"/>
                    </s:select>
                    </p>

                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>
                <c:if test="${reportname eq 'ETMIS05_1.rdf'}">
                    <p>
                        <label>Pejabat :</label>
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">
                            <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                    </c:if>
                    </p>
                    <p>
                        <label><em>*</em>Luas :</label>
                    <s:text name="report_p_luas"/>
                    </p>
                    <p>
                        <label>Kod Unit Luas :</label>
                    <s:select name="report_p_kod_uom" >
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodUOMByLuas}" label="nama" value="kod"/>
                    </s:select>
                    </p>
                    <p>
                        <label>Bandar/Pekan/Mukim :</label>
                    <s:select name="report_p_kod_bpm" >
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="kod"/>
                    </s:select>
                    </p>
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>
                <c:if test="${reportname eq 'ETMIS19_1.rdf'
                              or reportname eq 'ETMIS19_1NS.rdf'}">
                    <p>
                        <label>Pejabat :</label>
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">
                            <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                    </c:if>
                    </p>
                    <c:if test="${actionBean.kodNeg ne 'n9'}">
                        <p>
                            <label>Bandar/Pekan/Mukim :</label>
                        <s:select name="report_p_kod_bpm" >
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="kod"/>
                        </s:select>
                        </p>
                    </c:if>
                    <p>
                        <label>Jenis Bangsa :</label>
                    <s:select name="report_p_kod_bangsa" >
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodBangsa}" label="nama" value="kod"/>
                    </s:select>
                    </p>
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>
                <c:if test="${reportname eq 'ETMIS27_1.rdf'}">
                    <p>
                        <label>Pejabat :</label>
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">
                            <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                    </c:if>
                    </p>
                    <p>
                        <label>Jenis Hakmilik :</label>
                    <s:select name="report_p_kod_hakmilik" >
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodHakmilik}" label="nama" value="kod"/>
                    </s:select>
                    </p>
                    <p>
                        <label>Bandar/Pekan/Mukim :</label>
                    <s:select name="report_p_kod_bpm" >
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="kod"/>
                    </s:select>
                    </p>
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>

                </c:if>

                <c:if test="${reportname eq 'ETMIS26A_1.rdf'
                              or reportname eq 'ETMIS26A_1NS.rdf'}">
                    <p>
                        <label>Pejabat :</label>
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">
                            <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                    </c:if>
                    </p>
                    <c:if test="${actionBean.kodNeg ne '00'}">
                        <p>
                            <label>Bandar/Pekan/Mukim :</label>
                        <s:select name="report_p_kod_bpm" >
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="kod"/>
                        </s:select>
                        </p>
                    </c:if>

                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>
                <c:if test="${reportname eq 'ETMIS09_1.rdf'
                              or reportname eq 'ETMIS09_1NS.rdf'}">
                    <p>
                        <label>Pejabat :</label>
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">
                            <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                    </c:if>
                    </p>
                    <c:if test="${actionBean.kodNeg ne 'n9'}">
                        <p>
                            <label>Bandar/Pekan/Mukim :</label>
                        <s:select name="report_p_kod_bpm" >
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="kod"/>
                        </s:select>
                        </p>
                    </c:if>
                    <p>
                        <label><em>*</em>Bilangan Kaveat :</label>
                    <s:text name="report_p_bil_kaveat" id="bil_kaveat"/>
                    </p>
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>
                <!--comment by penyu-->
                <%--<c:if test="${actionBean.kodNeg eq 'n9' and actionBean.peng.kodCawangan.kod ne '00'}">
                    <p>
                        <label>Bandar/Pekan/Mukim :</label>
                        <s:select name="report_p_kod_bpm" >
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                </c:if>--%>

                <c:if test="${reportname eq 'ETMIS_ADD_01.rdf'}">
                    <p>
                        <label>Pejabat :</label>
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <b>Pejabat Pendaftar</b>
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                    </c:if>
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                    </c:if>
                    </p>
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <p>
                            <label>Daerah :</label>
                        <s:select name="report_p_kod_daerah" id="daerah" style="width:210px;" onchange="filterDaerah(this.value, '${actionBean.report}', '${actionBean.reportName}');">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"/>
                            <%--<s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama" />--%>
                            <%--<c:forEach items="${listUtil.senaraiKodDaerah}" var="i" >
                                <s:option value="${i.kod}">${i.kod} - ${i.nama}</s:option>
                            </c:forEach>--%> 
                        </s:select>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.kodNeg ne 'n9'}">
                        <p>
                            <label>Bandar/Pekan/Mukim :</label>
                        <s:select name="report_p_kod_bpm" style="width:210px;" >
                            <s:option value="">--Sila Pilih--</s:option>
                                <%--<s:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="kod" sort="kod" />--%>
                            <c:forEach items="${actionBean.senaraiBPM}" var="i" >
                                <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                            </c:forEach> 
                        </s:select>
                        </p>
                    </c:if>
                    <p>
                        <label>Jenis Perserahan :</label>
                    <s:select name="report_p_kod_serah" onchange="filterUrusan(this.value, this.form, '${actionBean.report}');" style="width:210px;">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:option value="SC">Urusniaga</s:option>
                        <s:option value="B">Bukan Urusniaga</s:option>
                        <s:option value="N">Nota</s:option>
                        <s:option value="MH">Mohon Hakmilik</s:option>
                        <s:option value="NB">Nota betul</s:option>
                    </s:select>
                    </p>
                    <p>
                        <label>Urusan :</label>
                    <s:select name="report_p_kod_urusan" id="kodUrusan" style="width:510px;">
                        <s:option value="" > --Sila Pilih--</s:option>
                        <c:forEach items="${actionBean.senaraiUrusan}" var="i" >
                            <s:option value="${i.kod}">${i.nama}</s:option>
                        </c:forEach>                                
                    </s:select>
                    </p>
                    <p>
                        <label>Jenis Kategori Tanah :</label>
                    <s:select name="report_p_kod_katg_tanah" style="width:210px;">
                        <s:option value=""> --Sila Pilih-- </s:option>
                        <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod"/>
                    </s:select>
                    </p>
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>
                <c:if test="${reportname eq 'ETMIS_ADD_02.rdf'}">
                    <!--penyu-->
                    <!--ptg-->
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <p>
                            <label>Pejabat :</label>
                            <%--<s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">--%>
                            <%--<s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>--%>
                            <b>Pejabat Pendaftar</b>
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        <%--</s:select>--%>
                        </p>
                        <p>
                            <label>Daerah :</label>
                        <s:select name="report_p_kod_daerah" id="daerah" style="width:210px;" onchange="filterDaerah(this.value, '${actionBean.report}', '${actionBean.reportName}');">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"/>
                            <%--<c:forEach items="${listUtil.senaraiKodDaerah}" var="i" >
                                <s:option value="${i.kod}">${i.kod} - ${i.nama}</s:option>
                            </c:forEach>--%> 
                        </s:select>
                        </p>
                        <c:if test="${actionBean.kodNeg ne 'n9'}">
                            <p>
                                <label>Bandar/Pekan/Mukim :</label>
                            <s:select name="report_p_kod_bpm" style="width:210px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                    <%--<s:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="kod" sort="kod" />--%>
                                <c:forEach items="${actionBean.senaraiBPM}" var="i" >
                                    <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                                </c:forEach> /--%>
                            </s:select>
                            </p>
                        </c:if>
                    </c:if>
                    <!--ptd-->
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <p>
                            <label>Pejabat :</label>
                            ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                    </c:if>
                    <!--penyu_end-->
                    <!-- fix by faiz -->
                    <c:if test="${actionBean.kodNeg eq 'melaka'}">
                        <p>
                            <label>Urusan :</label>
                        <s:select name="report_p_kod_urusan" >
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:option value="ABT">Cadangan Pengambilan Tanah</s:option>
                            <s:option value="ABT-A">Kemungkinan Tanah Diambil - Borang A</s:option>
                            <s:option value="ABTB">Pembatalan Pengambilan Balik Tanah</s:option>
                            <s:option value="ABTBH">Pengambilan Sebahagian Tanah</s:option>                          
                            <s:option value="ABT-D">Cadangan Pengambilan Tanah - Borang D</s:option>
                            <s:option value="ABT-K">Pengambilan Kesemua Tanah - Borang K</s:option>
                            <s:option value="ABTKB">Pengambilan Sebahagian Tanah - Borang K</s:option>
                            <s:option value="ABTS">Pengambilan Semua Tanah</s:option>
                        </s:select>
                        </p>
                    </c:if>
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>
                <c:if test="${reportname eq 'regPrestasiPendaftar.rdf'}">
                    <p>
                        <label>Pejabat :</label>
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <s:select name="report_p_caw" value="${actionBean.peng.kodCawangan.kod}">
                            <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                    </c:if>
                    </p>
                    <p>
                        <label>Bulan Perserahan :</label>
                    <s:select name="" id="bulan" >
                        <s:option value="">--Sila Pilih--</s:option>
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
                    <p>
                        <label>Tahun Perserahan :</label>
                    <s:select name="" id="tahun" style="width:100px">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${actionBean.listYear}"/>
                    </s:select>
                    </p>
                </c:if>    
                <c:if test="${reportname eq 'ETMIS_ADD_03.rdf'}">
                    <!--penyu-->
                    <!--ptg-->
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <p>
                            <label>Pejabat :</label>
                            <%--<s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">--%>
                            <%--<s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>--%>
                            <b>Pejabat Pendaftar</b>
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        <%--</s:select>--%>
                        </p>
                        <p>
                            <label>Daerah :</label>
                        <s:select name="report_p_kod_daerah" id="daerah" style="width:210px;" onchange="filterDaerah(this.value, '${actionBean.report}', '${actionBean.reportName}');">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"/>
                            <%--<c:forEach items="${listUtil.senaraiKodDaerah}" var="i" >
                                <s:option value="${i.kod}">${i.kod} - ${i.nama}</s:option>
                            </c:forEach>--%> 
                        </s:select>
                        </p>
                        <c:if test="${actionBean.kodNeg ne 'n9'}">
                            <p>
                                <label>Bandar/Pekan/Mukim :</label>
                            <s:select name="report_p_kod_bpm" style="width:210px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                    <%--<s:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="kod" sort="kod" />--%>
                                <c:forEach items="${actionBean.senaraiBPM}" var="i" >
                                    <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                                </c:forEach> /--%>
                            </s:select>
                            </p>
                        </c:if>
                    </c:if>
                    <!--ptd-->
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <p>
                            <label>Pejabat :</label>
                            ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                    </c:if>
                    <!--penyu_end-->
                    <p>
                        <label>Urusan :</label>
                    <s:select name="report_p_kod_urusan" >
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:option value="N6A">N6A - Notis Cukai Tidak Dibayar</s:option>
                        <s:option value="N6AB">N6AB - Pembatalan Notis 6A</s:option>
                        <s:option value="N8A">N8A - Notis Perampasan Tanah</s:option>
                        <s:option value="N8AB">N8AB - Pembatalan Notis Perampasan Tanah</s:option>
                    </s:select>
                    </p>
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>

                <!--penyu-->
                <!--Laporan Hakmilik Sementara)-->
                <c:if test="${reportname eq 'ETMIS_ADD_04.rdf'}">
                    <!--ptg-->
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <p>
                            <label>Pejabat :</label>
                            <%--<s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">--%>
                            <%--<s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>--%>
                            <b>Pejabat Pendaftar</b>
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>

                        <%--</s:select>--%>
                        </p>
                        <p>
                            <label>  Daerah :</label>
                        <s:select name="report_p_kod_daerah" id="daerah" style="width:210px;" onchange="filterDaerah(this.value, '${actionBean.report}', '${actionBean.reportName}');">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"/>
                            <%--<c:forEach items="${listUtil.senaraiKodDaerah}" var="i" >
                                <s:option value="${i.kod}">${i.kod} - ${i.nama}</s:option>
                            </c:forEach>--%> 
                        </s:select>
                        </p>
                        <c:if test="${actionBean.kodNeg ne 'n9'}">
                            <p>
                                <label>Bandar/Pekan/Mukim :</label>
                            <s:select name="report_p_kod_bpm" style="width:210px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <c:forEach items="${actionBean.senaraiBPM}" var="i" >
                                    <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                                </c:forEach>
                            </s:select>
                            </p>
                        </c:if>
                    </c:if>

                    <!--ptd-->
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <p>
                            <label>Pejabat :</label>
                            ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                    </c:if>
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>
                <!--penyu_end-->

                <c:if test="${reportname eq 'ETMIS_ADD_05.rdf'}">
                    <p>
                        <label>Pejabat :</label>
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">
                            <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                    </c:if>
                    </p>
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <p>
                            <label>Daerah :</label>
                        <s:select name="report_p_kod_daerah" id="daerah" style="width:210px;" onchange="filterDaerah(this.value, '${actionBean.report}', '${actionBean.reportName}');">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"/>
                            <%--<c:forEach items="${listUtil.senaraiKodDaerah}" var="i" >
                                <s:option value="${i.kod}">${i.kod} - ${i.nama}</s:option>
                            </c:forEach>--%> 
                        </s:select>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.kodNeg ne 'n9'}">
                        <p>
                            <label>Bandar/Pekan/Mukim :</label>
                        <s:select name="report_p_kod_bpm" style="width:210px;">
                            <s:option value="">--Sila Pilih--</s:option>
                            <c:forEach items="${actionBean.senaraiBPM}" var="i" >
                                <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                            </c:forEach> 
                        </s:select>
                        </p>
                    </c:if>
                    <p>
                        <label>Tarikh Luput :</label>
                    <s:text id="trh_luput" name="report_p_trh_luput" class="datepicker" formatPattern="dd/MM/yyyy"/>&nbsp;
                    <font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>

                <c:if test="${reportname eq 'ETMIS_ADD_07.rdf'}">
                    <p>
                        <label>Pejabat :</label>
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">
                            <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                    </c:if>
                    </p>
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>
                <c:if test="${reportname eq 'ETMIS65A_1NS.rdf'}">
                    <!--penyu-->
                    <!--ptg-->
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <p>
                            <label>Pejabat :</label>
                            <%--<s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">--%>
                            <%--<s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>--%>
                            <b>Pejabat Pendaftar</b>
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        <%--</s:select>--%>
                        </p>
                    </c:if>
                    <!--ptd-->
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <p>
                            <label>Pejabat :</label>
                            ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                    </c:if>
                    <!--penyu_end-->
                    <p>
                        <label>Bulan :</label>
                    <s:select name="report_p_bulan" id="bulan">
                        <s:option value="">--Sila Pilih--</s:option>
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
                    <p>
                        <label>Tahun :</label>
                    <s:select name="report_p_tahun" id="tahun" style="width:100px">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${actionBean.listYear}"/>
                    </s:select>
                    </p>
                </c:if>
                <c:if test="${reportname eq 'ETMIS53_1NS.rdf'}">
                    <!--penyu-->
                    <!--ptg-->
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <p>
                            <label>Pejabat :</label>
                            <%--<s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">--%>
                            <%--<s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>--%>
                            <b>Pejabat Pendaftar</b>
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        <%--</s:select>--%>
                        </p>
                        <p>
                            <label>  Daerah:</label>
                            <%--<s:select name="report_p_kod_daerah" id="daerah" style="width:200px;" 
                                      onchange="filterDaerah(this.value,'${actionBean.report}','${actionBean.reportName}');">--%>
                        <s:select name="report_p_kod_daerah" id="daerah" style="width:200px;">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"/>
                        </s:select>
                        </p>
                    </c:if>

                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <p>
                            <label>Pejabat :</label>
                            ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                    </c:if>

                    <p>
                        <label>Status Perserahan :</label>
                    <s:select name="report_p_id_aliran" style="width:200px;">
                        <s:option value="">-- Sila Pilih --</s:option>
                        <s:option value="agih_tugas">Awalan</s:option>
                        <s:option value="kemasukan">Kemasukan</s:option>
                        <s:option value="keputusan">Daftar</s:option>
                    </s:select>
                    </p>
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>

                <c:if test ="${reportname eq 'ETMIS76_1NS.rdf'}" >
                    <!--penyu-->
                    <!--ptg-->
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <p>
                            <label>Pejabat :</label>
                            <%--<s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">--%>
                            <%--<s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>--%>
                            <b>Pejabat Pendaftar</b>
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        <%--</s:select>--%>
                        </p>
                        <p>
                            <label>Daerah :</label>
                        <s:select name="report_p_kod_daerah" id="daerah" style="width:210px;" onchange="filterDaerah(this.value, '${actionBean.report}', '${actionBean.reportName}');">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"/>
                            <%--<c:forEach items="${listUtil.senaraiKodDaerah}" var="i" >
                                <s:option value="${i.kod}">${i.kod} - ${i.nama}</s:option>
                            </c:forEach>--%> 
                        </s:select>
                        </p>
                        <c:if test="${actionBean.kodNeg ne 'n9'}">
                            <p>
                                <label>Bandar/Pekan/Mukim :</label>
                            <s:select name="report_p_kod_bpm" style="width:210px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                    <%--<s:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="kod" sort="kod" />--%>
                                <c:forEach items="${actionBean.senaraiBPM}" var="i" >
                                    <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                                </c:forEach> /--%>
                            </s:select>
                            </p>
                        </c:if>
                    </c:if>
                    <!--ptd-->
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <p>
                            <label>Pejabat :</label>
                            ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                        <c:if test="${actionBean.kodNeg ne 'n9'}">
                            <p>
                                <label>Bandar/Pekan/Mukim :</label>
                            <s:select name="report_p_kod_bpm" style="width:210px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                    <%--<s:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="kod" sort="kod" />--%>
                                <c:forEach items="${actionBean.senaraiBPM05}" var="i" >
                                    <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                                </c:forEach> /--%>
                            </s:select>
                            </p>
                        </c:if>
                    </c:if>
                    <!--penyu_end-->
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>

                    </p>                 
                </c:if>
                <c:if test="${reportname eq 'ETMIS79_1.rdf' or reportname eq 'ETMIS80_1.rdf'}">
                    <!--ptg-->
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <p>
                            <label>Pejabat :</label>
                            <%--<s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">--%>
                            <%--<s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>--%>
                            <b>Pejabat Pendaftar</b>
                        <s:hidden name="" value="${actionBean.peng.kodCawangan.kod}"/>
                        <%--</s:select>--%>
                        </p>
                        <p>
                            <label>Daerah :</label>
                        <s:select name="report_p_kod_daerah" id="daerah" style="width:210px;" onchange="filterDaerah(this.value, '${actionBean.report}', '${actionBean.reportName}');">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"/>
                            <%--<c:forEach items="${listUtil.senaraiKodDaerah}" var="i" >
                                <s:option value="${i.kod}">${i.kod} - ${i.nama}</s:option>
                            </c:forEach>--%> 
                        </s:select>
                        </p>
                        <p>
                            <label>Bandar/Pekan/Mukim :</label>
                        <s:select name="report_p_kod_bpm" style="width:210px;">
                            <s:option value="">--Sila Pilih--</s:option>
                            <c:forEach items="${actionBean.senaraiBPM}" var="i" >
                                <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                            </c:forEach>
                        </s:select>
                        </p>
                    </c:if>

                    <!--ptd-->
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <p>
                            <label>Pejabat :</label>
                            ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>

                        <p>
                            <label>Bandar/Pekan/Mukim :</label>
                        <s:select name="report_p_kod_bpm" style="width:210px;">
                            <s:option value="">--Sila Pilih--</s:option>
                            <c:forEach items="${actionBean.senaraiBPM04}" var="i" >
                                <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                            </c:forEach>
                        </s:select>
                        </p>
                    </c:if>
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>
                <c:if test="${reportname eq 'ETMIS65_1.rdf'}">
                    <p>
                        <label>Pejabat :</label>
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">
                            <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                    </c:if>
                    </p>
                    <p>
                        <label>Jenis Perserahan :</label>
                    <s:select name="report_p_kod_serah">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:option value="SC">Urusniaga</s:option>
                        <s:option value="B">Bukan Urusniaga</s:option>
                        <s:option value="N">Nota</s:option>
                        <s:option value="MH">Mohon Hakmilik</s:option>
                        <s:option value="NB">Nota betul</s:option>
                    </s:select>
                    </p>
                    <p>
                        <label>Tarikh Perserahan :</label>
                    <s:text name="report_p_tarikh" class="datepicker" formatPattern="dd/MM/yyyy"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>
                <c:if test="${reportname eq 'ETMIS85_1.rdf'
                              or reportname eq 'ETMIS87_1.rdf'}">
                    <p>
                        <label>Pejabat :</label>
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">
                            <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                    </c:if>
                    </p>
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>

                <!-- edit laporan urusniaga-->
                <c:if test="${reportname eq 'ETMIS86_1.rdf'}">
                    <p>
                        <label>Pejabat :</label>
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <s:select name="report_p_kod_caw" style="width:145px;" value="${actionBean.peng.kodCawangan.kod}">
                            <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                    </c:if>
                    </p>
                    <p>
                        <label>Urusan :</label>
                    <s:select name="report_p_kod_serah" style="width:145px;">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:option value="SC">Urusniaga</s:option>
                        <s:option value="B">Bukan Urusniaga</s:option>
                    </s:select>
                    </p>
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>

                <!-- end edit laporan urusniaga-->

                <c:if test="${reportname eq 'ETMIS75_1.rdf'}">
                    <p>
                        <label>Pejabat :</label>
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">
                            <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                    </c:if>
                    </p>
                    <p>
                        <label>Bandar/Pekan/Mukim :</label>
                    <s:select name="report_p_kod_bpm" >
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="kod"/>
                    </s:select>
                    </p>
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <!--                       <p>
                                                <label>Tarikh Perserahan :</label>-->
                    <%--<s:text name="report_p_tarikh" class="datepicker" formatPattern="dd/MM/yyyy"/>--%>
                    <!--                            &nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                                            </p>-->
                </c:if>

                <!--add                      -->
                <c:if test="${reportname eq 'ETMIS73_1.rdf'
                              or reportname eq 'ETMIS74_1.rdf'}">
                    <p>
                        <!--ptg-->
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <p>
                            <label>Pejabat :</label>
                            <b>Pejabat Pendaftar</b>
                        <s:hidden name="" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                        <%--                        <p>
                                                      <label>Daerah :</label>
                                                      <s:select name="report_p_kod_daerah" id="daerah" style="width:210px;" onchange="filterDaerah(this.value,'${actionBean.report}','${actionBean.reportName}');">
                                                          <s:option value="">--Sila Pilih--</s:option>
                                                          <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"/>
                                                      </s:select>
                                                </p>
                                                <p>
                                                      <label>Bandar/Pekan/Mukim :</label>
                                                      <s:select name="report_p_kod_bpm" style="width:210px;">
                                                          <s:option value="">--Sila Pilih--</s:option>
                                                          <c:forEach items="${actionBean.senaraiBPM}" var="i" >
                                                              <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                                                          </c:forEach>
                                                      </s:select>
                                                </p>                --%>

                        <p>
                            <label>Daerah :</label>
                        <s:select name="report_p_kod_caw" style="width:150px;" onchange="changePejabat(this.value);">
                            <s:option value = "">--Sila Pilih--</s:option>
                            <s:option value = "00">PTG Melaka</s:option>
                            <s:option value = "01">PTD Melaka Tengah</s:option>
                            <s:option value = "02">PTD Jasin</s:option>
                            <s:option value = "03">PTD Alor Gajah</s:option>
                        </s:select>
                        </p>
                        <p>
                            <label>Bandar/Pekan/Mukim :</label>
                        <s:select name="" style="width:150px;" id="senaraiBPMdummy">
                            <s:option value="">--Sila Pilih--</s:option>
                        </s:select>

                        <s:select name ="report_p_kod_bpm" style="width:150px;" id="senaraiBPMptg">
                            <s:option value = "">Sila Pilih</s:option>
                            <c:forEach items="${listUtil.senaraiKodBPMptg}" var="i">
                                <s:option value = "${i.bandarPekanMukim}">${i.nama}</s:option>
                            </c:forEach>
                        </s:select>
                        <s:select name ="report_p_kod_bpm" style="width:150px;" id="senaraiBPMmt">
                            <s:option value = "">Sila Pilih</s:option>
                            <c:forEach items="${listUtil.senaraiKodBPMmt}" var="i">
                                <s:option value = "${i.bandarPekanMukim}">${i.nama}</s:option>
                            </c:forEach>
                        </s:select>
                        <s:select name ="report_p_kod_bpm" style="width:150px;" id="senaraiBPMjs">
                            <s:option value = "">Sila Pilih</s:option>
                            <c:forEach items="${listUtil.senaraiKodBPMjs}" var="i">
                                <s:option value = "${i.bandarPekanMukim}">${i.nama}</s:option>
                            </c:forEach>
                        </s:select>
                        <s:select name ="report_p_kod_bpm" style="width:150px;" id="senaraiBPMag">
                            <s:option value = "">Sila Pilih</s:option>
                            <c:forEach items="${listUtil.senaraiKodBPMag}" var="i">
                                <s:option value = "${i.bandarPekanMukim}">${i.nama}</s:option>
                            </c:forEach>
                        </s:select>    
                        </p>
                    </c:if>

                    <!--ptd-->
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <p>
                            <label>Pejabat :</label>
                            ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                        <p>
                            <label>Bandar/Pekan/Mukim :</label>
                        <s:select name="report_p_kod_bpm" style="width:210px;">
                            <s:option value="">--Sila Pilih--</s:option>
                            <c:forEach items="${actionBean.senaraiBPM04}" var="i" >
                                <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                            </c:forEach>
                        </s:select>
                        </p>
                    </c:if>  
                <c:if test="${reportname ne 'ETMIS74_1.rdf'}">
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange=""/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation2(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>

                </c:if>
                    </c:if>

                <c:if test="${reportname eq 'ETMIS_ADD_08.rdf'}">
                    <p>
                    <c:if test="${actionBean.kodNeg ne 'n9'}">
                        <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                            <label>Pejabat :</label>
                            <s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">
                                <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                            </s:select>
                        </c:if>
                    </c:if>
                    <c:if test="${actionBean.kodNeg eq 'n9'}">
                        <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                            <label>Pejabat :</label>
                            <b>Pejabat Pendaftar</b>
                        </c:if>

                    </c:if>
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <p>
                            <label>Pejabat :</label>
                            ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                    </c:if>
                    </p>
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font></p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>
                    
                <c:if test="${reportname eq 'ETMIS91.rdf' or reportname eq 'ETMIS92_1.rdf'}">
                    <p>
                    
                        <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                            <label>Pejabat :</label>
                              <s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">
                                <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                              </s:select>
                        </c:if>                   
                        <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                            <p>
                                <label>Pejabat :</label>
                                ${actionBean.peng.kodCawangan.name}
                            <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                            </p>
                        </c:if>
                    </p>                                    
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font></p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>  
                <c:if test="${reportname eq 'ETMIS_CetakanSemulaMLK.rdf'}">
                    <p>
                    
                        <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                            <label>Pejabat :</label>
                              <s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">
                                <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                              </s:select>
                        </c:if>                   
                        <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                            <p>
                                <label>Pejabat :</label>
                                ${actionBean.peng.kodCawangan.name}
                            <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                            </p>
                        </c:if>
                    </p>                    
                        <p>
                            <label>Id Hakmilik :</label>
                            <s:text name="report_p_id_hakmilik" id="id_hakmilik" onkeyup="this.value = this.value.toUpperCase();" style="width:160px;"/>&nbsp;<font size="1" color="black"> -Tidak Wajib Diisi-</font></p>
                        </p>                                     
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font></p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>     
                    
                <c:if test="${reportname eq 'ETMIS88_1.rdf' or reportname eq 'ETMIS88_1STRATA.rdf' or reportname eq 'ETMIS88_1LANDED.rdf' 
                              or reportname eq 'ETMIS89_1.rdf' or reportname eq 'ETMIS90_1.rdf' 
                              or reportname eq 'ETMIS88_1DutiSetem.rdf' or reportname eq 'ETMIS88_1STRATAdutiSetem.rdf' or reportname eq 'ETMIS88_1LANDEDdutiSetem.rdf'}">
                    <p>
                    <c:if test="${actionBean.kodNeg ne 'n9'}">
                        <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                            <label>Pejabat :</label>
                            <b>Pejabat Pendaftar</b>
                        </c:if>
                    </c:if>
                    <c:if test="${actionBean.kodNeg eq 'n9'}">
                        <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                            <label>Pejabat :</label>
                            <b>Pejabat Pendaftar</b>
                        </c:if>

                    </c:if>
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <p>
                            <label>Pejabat :</label>
                            ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                    </c:if>
                    </p>
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font></p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>    
                    

                <c:if test="${reportname eq 'ETMIS_ADD_09.rdf'}">
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <p>
                            <label>Pejabat :</label>
                            <b>Pejabat Pendaftar</b>
                        </p>
                        <c:if test="${actionBean.kodNeg ne 'n9'}">
                            <p>
                                <label>Daerah :</label>
                            <s:select name="report_p_kod_caw" style="width:150px;" onchange="changePejabat(this.value);">
                                <s:option value = "">--Sila Pilih--</s:option>
                                <s:option value = "00">PTG Melaka</s:option>
                                <s:option value = "01">PTD Melaka Tengah</s:option>
                                <s:option value = "02">PTD Jasin</s:option>
                                <s:option value = "03">PTD Alor Gajah</s:option>
                            </s:select>
                            </p>
                        </c:if>
                        <c:if test="${actionBean.kodNeg ne 'n9'}">
                            <p>
                                <label>Nama Pendaftar :</label>
                            <s:select name="" style="width:150px;" id="pendaftarDummy">
                                <s:option value="">--Sila Pilih--</s:option>
                            </s:select>

                            <s:select name ="report_p_id_pguna" style="width:150px;" id="pendaftarPTG">
                                <s:option value = "">Sila Pilih</s:option>
                                <c:forEach items="${listUtil.senaraiPenggunaPendaftarPTG}" var="item">
                                    <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                                </c:forEach>
                            </s:select>

                            <s:select name ="report_p_id_pguna" style="width:150px;" id="pendaftarMT">
                                <s:option value = "">Sila Pilih</s:option>
                                <c:forEach items="${listUtil.senaraiPenggunaPendaftarMT}" var="item">
                                    <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                                </c:forEach>
                            </s:select>

                            <s:select name ="report_p_id_pguna" style="width:150px;" id="pendaftarJasin">
                                <s:option value = "">Sila Pilih</s:option>
                                <c:forEach items="${listUtil.senaraiPenggunaPendaftarJasin}" var="item">
                                    <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                                </c:forEach>
                            </s:select>

                            <s:select name ="report_p_id_pguna" style="width:150px;" id="pendaftarAG">
                                <s:option value = "">Sila Pilih</s:option>
                                <c:forEach items="${listUtil.senaraiPenggunaPendaftarAG}" var="item">
                                    <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                                </c:forEach>
                            </s:select>
                            </p>
                        </c:if>
                    </c:if>                                

                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <p>
                            <label>Pejabat :</label>
                            ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                        <c:if test="${actionBean.kodNeg ne 'n9'}">
                            <p>
                                <label>Nama Pendaftar :</label> 
                            <c:if test="${actionBean.peng.kodCawangan.kod eq '01'}">
                                <s:select name="report_p_id_pguna" style="width:150px;">
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:options-collection collection="${listUtil.senaraiPenggunaPendaftarMT}" label="nama" value="idPengguna" />
                                </s:select>
                            </c:if>

                            <c:if test="${actionBean.peng.kodCawangan.kod eq '02'}">
                                <s:select name="report_p_id_pguna" style="width:150px;">
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:options-collection collection="${listUtil.senaraiPenggunaPendaftarJasin}" label="nama" value="idPengguna" />
                                </s:select>
                            </c:if>

                            <c:if test="${actionBean.peng.kodCawangan.kod eq '03'}">
                                <s:select name="report_p_id_pguna" style="width:150px;">
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:options-collection collection="${listUtil.senaraiPenggunaPendaftarAG}" label="nama" value="idPengguna" />
                                </s:select>
                            </c:if>
                            </p>
                        </c:if>
                    </c:if>
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;
                    <font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </c:if>

                <!--add laporan MCL dan CPB                                    -->
                <c:if test="${reportname eq 'ETMIS_ADD_10.rdf'
                              or reportname eq 'ETMIS00_1.rdf'}"> 
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <p>
                            <label>Pejabat :</label>
                            <b>Pejabat Pendaftar</b>
                        <s:hidden name="" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                        <p>
                            <label>Daerah :</label>
                        <s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">
                            <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                        </s:select>
                        </p>
                    </c:if>        
                    <p>       
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <label>Pejabat :</label>
                        ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                    </c:if>
                    </p>         
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value), dateValidation2(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Status :</label>
                    <s:select name="report_p_aktif">
                        <s:option value = "">--Sila Pilih--</s:option>
                        <s:option value = "AK">AKTIF </s:option>
                        <s:option value = "TK">TAK AKTIF </s:option>
                    </s:select>
                    </p>
                </c:if>

                <!-- added 27/6/2013:  Laporan Kutipan Dokumen -->
                <c:if test="${reportname eq 'ETMIS_ADD_11.rdf'}"> 
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <p>
                            <label>Pejabat :</label>
                        <s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}" style="width:210px;">
                            <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                        </s:select>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <p>
                            <label>Pejabat :</label>
                            ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                    </c:if>

                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)" style="width:210px;"/>&nbsp;
                    <font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation2(this.id, this.value)" style="width:210px;"/>&nbsp;
                    <font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Jenis Penyerah :</label>              
                    <s:select name="report_p_jenis_penyerah" id="jenisPenyerah" style="width:210px;" onchange="padam3(this.id)">
                        <s:option value=""> --- Sila Pilih --- </s:option>
                        <s:options-collection collection="${listUtil.senaraiKodPenyerah}" label="nama" value="kod"/>
                    </s:select>
                    </p>
                    <div id="nPenyerah">
                        <p>
                            <label>No Penyerah :</label>
                        <s:text name="report_p_no_penyerah" id="idPenyerah" onkeyup="this.value = this.value.toUpperCase();" style="width:210px;"/>  
                        <font size="1" color="red" >&nbsp;atau</font>
                        </p>              
                        <p>
                            <label>Nama Penyerah :</label>
                        <s:text name="report_p_nama_penyerah" id="namaPenyerah" onkeyup="this.value = this.value.toUpperCase();" style="width:210px;"/>
                        </p>
                    </div>
                </c:if>


                <!-- 3/12/2013:  Laporan Tukarganti Mengikut Tarikh -->
                <c:if test="${reportname eq'ETTG01_NS.rdf'
                              or reportname eq'ETTG06_NS.rdf'}">
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <p>
                            <label>Pejabat :</label>
                            <b>Pejabat Pendaftar</b>
                        <s:hidden name="" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                        <p>
                            <label>  Daerah :</label>
                        <s:select name="report_p_daerah" id="daerah" style="width:210px;" >
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"/>
                        </s:select>
                        </p>
                    </c:if>        
                    <p>       
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <label>Pejabat :</label>
                        ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                    </c:if>
                    </p>

                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>                   
                </c:if>

                <!--3/12/2013 Laporan Tukarganti mengikut tahun/Pendaftar mengikut Tahun/Forecast Tukarganti-->
                <c:if test="${reportname eq'ETTG02_NS.rdf'
                              or reportname eq'ETTG04_NS.rdf'}">
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <p>
                            <label>Pejabat :</label>
                            <b>Pejabat Pendaftar</b>
                        <s:hidden name="" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                        <p>
                            <label>  Daerah :</label>
                        <s:select name="report_p_daerah" id="daerah" style="width:210px;" >
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"/>
                        </s:select>
                        </p>
                    </c:if>        
                    <p>       
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <label>Pejabat :</label>
                        ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                    </c:if>
                    </p>
                    <p>
                        <label>Tahun :</label>
                    <s:select name="report_p_tahun" id="tahun" style="width:100px">
                        <s:options-collection collection="${actionBean.listYear}"/>
                    </s:select>
                    </p>
                </c:if>

                <!-- 12/11/2013 laporan forecast-->
                <c:if test="${reportname eq'ETTG05_NS.rdf'}">
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <p>
                            <label>Pejabat :</label>
                            <b>Pejabat Pendaftar</b>
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                        <p>
                            <label>  Daerah :</label>
                        <s:select name="report_p_daerah" id="daerah" style="width:210px;" >
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"/>
                        </s:select>
                        </p>
                    </c:if>     
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <p>                     
                            <label>Pejabat :</label>
                            ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>              
                        </p> 
                    </c:if>
                    <p>
                        <label>Purata Mengikut :</label>
                    <s:select name="" id="purata_mengikut" onchange="changePurata(this.value);" style="width:100px;">
                        <s:option value="h">Hari</s:option>
                        <s:option value="b">Bulan </s:option>
                    </s:select>
                    </p>
                    <div id="pHari">
                        <p>
                            <label>Purata Bil Tukarganti Hakmilik Mengikut Hari :</label>
                        <s:text id="purata_hari" name="report_p_purata_hari" size="5"/>&nbsp;
                        </p>
                    </div>
                    <div id="pBulan">            
                        <p>
                            <label>Purata Bil Tukarganti Hakmilik Mengikut Bulan :</label>
                        <s:text id="purata_bulan" name="report_p_purata_bulan" size="5"/>&nbsp;
                        </p>
                    </div>            
                    <p>
                        <label>Tahun :</label>              
                    <s:text id="tahun" name="report_p_tahun" size="5" value="currentYear" readonly="true"/>
                    </p>
                </c:if>

                <!-- 4/12/2013:Laporan Tukarganti Mengikut Pendaftar-->
                <c:if test="${reportname eq'ETTG03_NS.rdf' or reportname eq'ETTG09.rdf'}">
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <p>
                            <label>Pejabat :</label>
                            <b>Pejabat Pendaftar</b>
                        <s:hidden name="" value="${actionBean.peng.kodCawangan.kod}"/>
                        </p>
                    </c:if>        
                    <p>       
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <label>Pejabat :</label>
                        ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                    </c:if>
                    </p>
                    <p>
                        <label>Nama Pendaftar :</label>
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <s:select name="report_p_pegawai" >
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${actionBean.senaraiPendaftar}" label="nama" value="nama" sort="nama"/>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <s:select name="report_p_pegawai" >
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${actionBean.senaraiPendaftar}" label="nama" value="nama" sort="nama"/>
                        </s:select>
                    </c:if>
                    </p>
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>                   
                </c:if>

                <!-- added 3/10/2013:  Laporan kutipan data-->
                <c:if test="${reportname eq'ETKD01_NS.rdf'}">
                    <p>
                        <label>Pejabat :</label>
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <s:select name="report_p_kod_caw" value="${actionBean.report_p_kod_daerah}" onchange="filterCaw(this.value, '${actionBean.report}', '${actionBean.reportName}');">
                            <s:options-collection collection="${actionBean.senaraiKodDaerahPTG}" label="nama" value="kod" />
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        ${actionBean.peng.kodCawangan.name}
                        <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                    </c:if>
                    </p>
                    <p>
                        <label>Bandar/Pekan/Mukim :</label>
                    <s:select name="report_p_kod_bpm" >
                        <s:option value="">--Sila Pilih--</s:option>
                        <c:forEach items="${actionBean.senaraiBPM}" var="i" >
                            <s:option value="${i.kod}">${i.nama}</s:option>
                        </c:forEach>
                    </s:select>
                    </p>
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>                   
                </c:if>  
                    
                    
                <c:if test="${reportname eq'ETTG08.rdf'}">
                    <p>
                        <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>                   
                </c:if>    

                <br>
                <p align="center">
                <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit(this.form);"/>&nbsp;
                <s:reset name="RESET" value="Isi Semula" class="btn"/>&nbsp;
                <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
                </p>
                <br>
                <br>
            </fieldset>
        </div>
    </s:form> 
</div>
</body>
</html>
