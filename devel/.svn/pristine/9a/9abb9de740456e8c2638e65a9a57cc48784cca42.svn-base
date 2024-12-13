<%-- 
    Document   : strata_laporan_param_melaka
    Created on : Aug 15, 2013, 12:22:57 PM
    Author     : norazila
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
    $(document).ready(function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $("#daerah").val(kodDaerah);
        $("#caw").val(kodCaw);
        $("#trh_mula").val(date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear());
        $("#trh_akhir").val(date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear());
        $("#trh_pungutan").val(date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear());
        $("#trh_masuk").val(date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear());
        $("#bulan").val(date.getMonth() + 1);
        $("#tahun").val(date.getFullYear());
        $("#seluruh").hide();
        $("#thn").hide();
        $("#terima").hide();
        $("#peratus").hide();
        $("#lalala").hide();
        $("#skim").hide();
        $("#satu").hide();
        $("#paparD").hide();
        $("#cari").hide();
        $("#paparTHN").hide();
        $("#paparSkim").hide();
        $("#noBuku").hide();
        $("#paparPTDJ").hide();
        $("#paparTolak").hide();
        $("#paparS").hide();
        $("#paparKmpl").hide();
        $("#paparTrm").hide();
        $("#paparTggk").hide();
        $("#bpmptg").show();
        
        
   

    });
    
    function doSubmit(f) {
      
        //        var noLot = document.getElementById('noLot').value;
        //        var noHakmilik = document.getElementById('noHakmilik').value;
        //        var mukim = document.getElementById('mukim').value;
        //        if (!noLot.match(/\S/)) {
        //            alert('Sila Masukkan No Lot');
        //            return false;
        //        }
        //        if (!noHakmilik.match(/\S/)) {
        //            alert('Sila Masukkan No Hakmilik');
        //            return false;
        //        }
        //        if (!mukim.match(/\S/)) {
        //            alert('Sila Masukkan Mukim');
        //            return false;
        //        }
        
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
        if ((report == 'STRStatKosRendah_MLK.rdf'
            || report == 'STRKumulatifSemasa_MLK.rdf'
            || report == 'STRKumulatifTerkumpul_MLK.rdf'
            || report == 'STRKumulatifTertunggak_MLK.rdf'
            || report == 'STRStatPemilik_MLK.rdf'
            || report == 'STRStatPrestasi_MLK.rdf'
            || report == 'STRStatPrestasiKeseluruhan_MLK.rdf'
            || report == 'STRStatPrestasiTerima_MLK.rdf'
            || report == 'STRStatPrestasiDaftar_MLK.rdf'
            || report == 'STRStatPrestasiTolak_MLK.rdf'
            || report == 'STRLaporanPermit_MLK.rdf'
            || report == 'STRLaporanBdnUrus_MLK.rdf'
            || report == 'STRLaporanPenguatkuasaan_MLK.rdf'
            || report == 'STRDaftarStrata_MLK.rdf'
            || report == 'STRLaporanDlmProses_MLK.rdf'
            || report == 'STRPembetulan380_MLK.rdf'
            || report == 'STRPindaSyaratNyata_MLK.rdf'
            || report == 'STRLaporanBayaran_MLK.rdf'
            || report == 'STRStatPemilikPeratus_MLK.rdf') && $('#caw').val() == "") {
            alert("Sila pilih Cawangan terlebih dahulu.");
            $('#caw').focus();
            $.unblockUI();
        }
        else {
            var url = form.replace(/&/g, "%26");
            //            alert(url);
            window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
            "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
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
            var url = '${pageContext.request.contextPath}/laporanStrata_MLK?doCollectKaunter&kodCawangan=' + kodCaw + '&reportNama=' + report;
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
        //alert("caw-1");
        var report = $("#reportname").val();
        if (kodCaw2 != null) {
            //alert("caw-2");
            var url = '${pageContext.request.contextPath}/laporanStrata_MLK?doFilterDaerahBPM&kodCawangan=' + kodCaw2 + '&reportNama=' + report;
            $.get(url,
            function(data) {
                $('#display').html(data);
                $('#caw').val(kodCaw2);
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#tahun').hide();
                $("#skim").hide();
                $("#paparPTDJ").hide();
                $("#paparTHN").hide();
                $("#paparSkim").hide();
                $("#papar").hide();
                $("#noBuku").show();
                $("#paparD").hide();
                $("#paparTolak").hide();
                $("#paparS").hide();
                $("#paparTrm").hide();
                $("#paparKmpl").hide();
                $("#paparTggk").hide();

            });
        }
        //alert("caw-3");
    }

    function doFilterBPM(kodDaerah1) {
        var report = $("#reportname").val();
        var caw;
        //        $('#daerah').hide();
        if ($('#caw').val() != '')
            caw = $('#caw').val();
        if (kodDaerah1 != "") {
            var url = '${pageContext.request.contextPath}/laporanStrata_MLK?doFilterBPM&kodDaerah=' + kodDaerah1 + '&reportNama=' + report;
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


    //            var pilih = document.getElementById("Jenis1");
    //            var display = document.getElementById("keluar");
    //            if (pilih.checked == true)
    //            {
    //                display.style.visibility = "visible";
    //            }
    //            else
    //            {
    //                display.style.visibility = "hidden";
    //            }
    //        }



    function getpilih2() {
        var pil = $('#selecthak').val();
        //            alert(pil);
        if (pil == 'PTD') {
            //alert("-----------PTD-----------");
            $('#daerah').show();
            $('#tahun').hide();
            $("#papar").hide();
            $("#paparPTDJ").show();
            $("#paparTHN").hide();
            $("#paparSkim").hide();
            $("#paparS").hide();
            $("#paparKmpl").hide();
            $('#paparTggk').hide();
        } else {
            $('#daerah').hide();
            $('#tahun').hide();
            $("#papar").show();
            $("#paparPTDJ").hide();
            $("#paparTHN").hide();
            $("#paparSkim").hide();
            $("#paparS").hide();
            $("#paparKmpl").hide();
            $('#paparTggk').hide();
        }
    }

    function doFilterBPM() {
        // alert("----PTD selected--------");
        var Ptd = $('#kd').val();
        // alert("--selected KD---" + Ptd);
        $('#paparPTDJ').show();
        $('#papar').hide();
        
    }




    function keseluruhan() {
        var pilih = document.getElementById("Jenis1").value;
        //alert(pilih);
        if (pilih == 'keseluruhan')
        {
            $('#satu').show();
            $('#thn').hide();
            $('#lalala').hide();
            $('#papar').show();
            $('#paparD').hide();
            $('#paparTolak').hide();
            $('#paparS').hide();
            $('#paparTrm').hide();
            $("#paparKmpl").hide();
            
        }
    }
    
    function pertama() {
        
        var pilih = document.getElementById("kat1").value;
        var a = document.getElementById("kodurusan").value;
        var ku1 = document.getElementById("ku1").value;
        var cw1 = document.getElementById("cw1").value;
        var tahun2 = document.getElementById("tahun2").value;
        //alert(ku1);
        if (pilih == 'ikutTahun')
        {
            $('#ku1').val("");
            $('#cw1').val("");
            $('#tahun2').val("");
            $('#seluruh').show();
            $('#lalala').hide();
            $('#thn').hide();
            $('#papar').show();
            $('#paparD').hide();
            $('#paparTolak').hide();
            $('#paparS').hide();
            $('#paparTrm').hide();
            $("#paparKmpl").hide();
            $('#paparTggk').hide();
            
        }
    }
    
    function kedua() {
        var pilih = document.getElementById("kat2").value;
        var kodurusan = document.getElementById("kodurusan").value;
        var kodcaw = document.getElementById("kodcaw").value;
        //alert(pilih);
        if (pilih == 'spesifik')
        {
            $('#kodurusan').val("");
            $('#kodcaw').val("");
            $('#lalala').show();
            $('#seluruh').hide();
            $('#thn').hide();
            $('#papar').hide();
            $('#paparD').hide();
            $('#paparTolak').hide();
            $('#paparS').show();
            $('#paparTrm').hide();
            $("#paparKmpl").hide();
            $('#paparTggk').hide();
            
        }
    }
    
    function tahunan() {
        var pilih = document.getElementById("Jenis2").value;
        var kodurusan2 = document.getElementById("kodurusan2").value;
        var kodcaw6 = document.getElementById("kodcaw6").value;
        var tahun3 = document.getElementById("tahun3").value;
        //        alert(pilih);
        if (pilih == 'tahun')
        {
            $('#kodurusan2').val("");
            $('#kodcaw6').val("");
            $('#tahun3').val("");
            $('#seluruh').hide();
            $('#thn').show();
            $('#satu').hide();
            $('#lalala').hide();
        }
    }
    
    function daftar() {
        var pilih = document.getElementById("JenisA").value;
        //alert(pilih);
        if (pilih == 'daftar')
        {
            $('#paparD').show();
            $('#papar').hide();
            $('#paparTolak').hide();
            $('#paparS').hide();
            $('#paparTrm').hide();
            $("#paparKmpl").hide();
            $('#paparTggk').hide();
        }
    }
    
    function doSubmitSeluruh(f) {
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
        var a = document.getElementById("kodcaw").value;
        var b = document.getElementById("kodurusan").value;
        var c = document.getElementById("tahun2").value;
        var pilih = document.getElementById("kat2").value;
        
        if ((report == 'STRStatPrestasiKeseluruhan_MLK.rdf') && $('#caw').val() == "") {
            alert("Sila pilih Cawangan terlebih dahulu.");
            $('#caw').focus();
            $.unblockUI();
        } else {
            if(pilih == 'spesifik'){
                //var url = form.replace(/&/g, "%26");
                var url = form.replace('STRStatPrestasi_MLK.rdf', "STRStatPrestasiKeseluruhan_MLK.rdf");
                //alert(url);
                //            window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
                //            "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
                //            if ($('#tahun2').val() == ""){
                
                window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+"&reportName="+"STRStatPrestasiKeseluruhan_MLK.rdf"+"&report_p_kod_urusan="+b+"&report_p_kod_caw="+a, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");}
            else {
                window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
            }
            
            

            
            $.unblockUI();
        }
    }
    
    function doSubmitDaftar(f) {
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
        var a = document.getElementById("kodcaw6").value;
        var b = document.getElementById("kodurusan2").value;
        var c = document.getElementById("tahun3").value;
        var pilihan = document.getElementById("JenisA").value;
        
        if ((report == 'STRStatPrestasiDaftar_MLK.rdf') && $('#caw').val() == "") {
            alert("Sila pilih Cawangan terlebih dahulu.");
            $('#caw').focus();
            $.unblockUI();
        } else {
            if(pilihan == 'daftar'){
                //var url = form.replace(/&/g, "%26");
                var url = form.replace('STRStatPrestasi_MLK.rdf', "STRStatPrestasiDaftar_MLK.rdf");
                //alert(url);
                //            window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
                //            "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
                //            if ($('#tahun2').val() == ""){
                
                window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+"&reportName="+"STRStatPrestasiDaftar_MLK.rdf"+"&report_p_kod_urusan="+b+"&report_p_kod_caw="+a+"&report_p_tahun="+c, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");}
            
            else {
                window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
            }
            
            

            
            $.unblockUI();
        }
    }
        
    function tolak() {
        var pilih = document.getElementById("JenisB").value;
        //alert(pilih);
        if (pilih == 'tolak')
        {
            $('#paparTolak').show();
            $('#papar').hide();
            $('#paparD').hide();
            $('#paparTrm').hide();
            $("#paparKmpl").hide();
        }
        
    }
    
    function doSubmitTolak(f) {
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
        var a = document.getElementById("kodcaw6").value;
        var b = document.getElementById("kodurusan2").value;
        var c = document.getElementById("tahun3").value;
        var pilihan = document.getElementById("JenisB").value;
        
        if ((report == 'STRStatPrestasiTolak_MLK.rdf') && $('#caw').val() == "") {
            alert("Sila pilih Cawangan terlebih dahulu.");
            $('#caw').focus();
            $.unblockUI();
        } else {
            if(pilihan == 'tolak'){
                //var url = form.replace(/&/g, "%26");
                var url = form.replace('STRStatPrestasi_MLK.rdf', "STRStatPrestasiTolak_MLK.rdf");
                //alert(url);
                //            window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
                //            "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
                //            if ($('#tahun2').val() == ""){
                
                window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+"&reportName="+"STRStatPrestasiTolak_MLK.rdf"+"&report_p_kod_urusan="+b+"&report_p_kod_caw="+a+"&report_p_tahun="+c, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");}
            
            else {
                window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
            }
            
            

            
            $.unblockUI();
        }
    }
    
    function terima() {
        var pilih = document.getElementById("JenisC").value;
        //alert(pilih);
        if (pilih == 'terima')
        {
            $('#paparTrm').show();
            $('#paparTolak').hide();
            $('#papar').hide();
            $('#paparD').hide();
            $("#paparKmpl").hide();
            $('#paparTggk').hide();
        }
    }
    
    function doSubmitTerima(f) {
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
        var a = document.getElementById("kodcaw6").value;
        var b = document.getElementById("kodurusan2").value;
        var c = document.getElementById("tahun3").value;
        var pilihan = document.getElementById("JenisC").value;
        
        if ((report == 'STRStatPrestasiTerima_MLK.rdf') && $('#caw').val() == "") {
            alert("Sila pilih Cawangan terlebih dahulu.");
            $('#caw').focus();
            $.unblockUI();
        } else {
            if(pilihan == 'terima'){
                //var url = form.replace(/&/g, "%26");
                var url = form.replace('STRStatPrestasi_MLK.rdf', "STRStatPrestasiTerima_MLK.rdf");
                //alert(url);
                //            window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
                //            "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
                //            if ($('#tahun2').val() == ""){
                
                window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+"&reportName="+"STRStatPrestasiTerima_MLK.rdf"+"&report_p_kod_urusan="+b+"&report_p_kod_caw="+a+"&report_p_tahun="+c, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");}
            
            else {
                window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
            }
            
            

            
            $.unblockUI();
        }
    }
        

    function skim() {
        var pilih = document.getElementById("pindahMilik1").value;
        //            alert(pilih);
        if (pilih == 'Skim')
        {
            $('#skim').show();
            $('#peratus').hide();
            $('#papar').hide();
            $('#paparSkim').show();

        }
    }

    function peratus() {
        var pilih = document.getElementById("pindahMilik2").value;
        //            alert(pilih);
        if (pilih == 'Peratus')
        {
            $('#peratus').show();
            $('#skim').hide();
        }
    }

    function doSubmitSkim(f) {
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
        if ((report == 'STRStatPemilik_MLK.rdf') && $('#caw').val() == "") {
            alert("Sila pilih Cawangan terlebih dahulu.");
            $('#caw').focus();
            $.unblockUI();
        } else {
            //var url = form.replace(/&/g, "%26");
            var url = form.replace('STRStatPemilikPeratus_MLK.rdf', "STRStatPemilik_MLK.rdf");
            //alert(url);
            window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
            "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    <%--f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
    f.submit();--%>
                $.unblockUI();
            }
        }

        function doSubmitBukuDaftar(f) {
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
            if ((report == 'STRDaftarStrata_MLK.rdf') && $('#caw').val() == "") {
                alert("Sila pilih Cawangan terlebih dahulu.");
                $('#caw').focus();
                $.unblockUI();
            } else {
                var url = form.replace(/&/g, "%26");
                //var url = form.replace('&kod_caw=', "");
                //alert(url);
                window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    <%--f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
    f.submit();--%>
                $.unblockUI();
            }

        }

        function doSubmitJasin(f) {
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
            if ((report == 'STRLaporanKumulatifPTD_MLK.rdf') && $('#caw').val() == "") {
                alert("Sila pilih Cawangan terlebih dahulu.");
                $('#caw').focus();
                $.unblockUI();
            } else {
                //var url = form.replace(/&/g, "%26");
                var url = form.replace('STRLaporanKumulatifPTG_MLK.rdf', "STRLaporanKumulatifPTD_MLK.rdf");
                //alert(url);
                window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    <%--f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
    f.submit();--%>
                $.unblockUI();
            }
        }
        function doSubmitThn(f) {
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
            if ((report == 'STRLaporanKumulatifTahun_MLK.rdf') && $('#caw').val() == "") {
                alert("Sila pilih Cawangan terlebih dahulu.");
                $('#caw').focus();
                $.unblockUI();
            } else {
                //var url = form.replace(/&/g, "%26");
                var url = form.replace('STRLaporanKumulatifPTG_MLK.rdf', "STRLaporanKumulatifTahun_MLK.rdf");
                //alert(url);
                window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    <%--f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
    f.submit();--%>
                $.unblockUI();
            }
        }

        //        function validateNoLot() {
        //            if ($("#noLot").val() == "") {
        //                alert("Sila Masukkan No Lot!");
        //                return false;
        //            }
        //            return true;
        //        }

        
        function kumpul() {
            var pilih = document.getElementById("typeB").value;
            //alert(pilih);
            if (pilih == 'kumpul')
            {
                $('#paparD').hide();
                $('#papar').hide();
                $('#paparTolak').hide();
                $('#paparS').hide();
                $('#paparTrm').hide();
                $('#paparKmpl').show();
                $('#paparTggk').hide();
                
            }
        }

        function doSubmitKumpul(f) {
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
            var a = document.getElementById("kodcaw8").value;
            var c = document.getElementById("tahun9").value;
            var pilih = document.getElementById("typeB").value;
        
            if ((report == 'STRKumulatifTerkumpul_MLK.rdf') && $('#caw').val() == "") {
                alert("Sila pilih Cawangan terlebih dahulu.");
                $('#caw').focus();
                $.unblockUI();
            } else {
                if(pilih == 'kumpul'){
                    //var url = form.replace(/&/g, "%26");
                    var url = form.replace('STRKumulatifSemasa_MLK.rdf', "STRKumulatifTerkumpul_MLK.rdf");
                    //alert(url);
                    //            window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
                    //            "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
                    //            if ($('#tahun2').val() == ""){
                
                    window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+"&reportName="+"STRKumulatifTerkumpul_MLK.rdf"+"&report_p_tahun="+c+"&report_p_kod_caw="+a, "eTanah",
                    "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");}
                else {
                    window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
                    "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
                }
            
            

            
                $.unblockUI();
            }
        }
        
        function tunggak() {
            var pilih = document.getElementById("typeC").value;
            //alert(pilih);
            if (pilih == 'tunggak')
            {
                $('#paparD').hide();
                $('#papar').hide();
                $('#paparTolak').hide();
                $('#paparS').hide();
                $('#paparTrm').hide();
                $('#paparKmpl').hide();
                $('#paparTggk').show();
                
            }
        }
        
        function semasa() {
            var pilih = document.getElementById("typeA").value;
            //alert(pilih);
            if (pilih == 'semasa')
            {
                $('#paparD').hide();
                $('#papar').show();
                $('#paparTolak').hide();
                $('#paparS').hide();
                $('#paparTrm').hide();
                $('#paparKmpl').hide();
                $('#paparTggk').hide();
                
            }
        }
       
        function doSubmitTunggak(f) {
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
            var a = document.getElementById("kodcaw8").value;
            var c = document.getElementById("tahun9").value;
            var pilih = document.getElementById("typeC").value;
        
            if ((report == 'STRKumulatifTertunggak_MLK.rdf') && $('#caw').val() == "") {
                alert("Sila pilih Cawangan terlebih dahulu.");
                $('#caw').focus();
                $.unblockUI();
            } else {
                if(pilih == 'tunggak'){
                    //var url = form.replace(/&/g, "%26");
                    var url = form.replace('STRKumulatifSemasa_MLK.rdf', "STRKumulatifTertunggak_MLK.rdf");
                    //alert(url);
                    //            window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
                    //            "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
                    //            if ($('#tahun2').val() == ""){
                
                    window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+"&reportName="+"STRKumulatifTertunggak_MLK.rdf"+"&report_p_tahun="+c+"&report_p_kod_caw="+a, "eTanah",
                    "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");}
                else {
                    window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
                    "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
                }
            
            

            
                $.unblockUI();
            }
        }




</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
     width="50" height="50" style="display: none" alt=""/>

<s:form beanclass="etanah.view.strata.StrataLaporanMelaka">
    <div id="display" class="subtitle">
        <s:hidden id="reportname" name="reportName"/>
        <fieldset class="aras1">
            <lagend>

                <c:if test="${actionBean.reportName eq 'STRKumulatifSemasa_MLK.rdf'}">
                    Laporan Kumulatif Pendaftaran Hakmilik Strata
                </c:if>

                <c:if test="${actionBean.reportName eq 'STRStatKosRendah_MLK.rdf'}">
                    Laporan Statistik Hakmilik Strata Kos Rendah 
                </c:if>

                <c:if test="${actionBean.reportName eq 'STRStatPemilik_MLK.rdf'}">
                    Laporan Pindah Milik Strata Mengikut Skim
                </c:if>

                <c:if test="${actionBean.reportName eq 'STRStatPrestasi_MLK.rdf'}">
                    Laporan Prestasi Permohonan Hakmilik Strata 
                </c:if>

                <c:if test="${actionBean.reportName eq 'STRLaporanPermit_MLK.rdf'}">
                    Senarai Hakmilik Strata yang mempunyai Permit Ruang Udara 
                </c:if>

                <c:if test="${actionBean.reportName eq 'STRLaporanBdnUrus_MLK.rdf'}">
                    Senarai Nama Perbadanan Pengurusan 
                </c:if>

                <c:if test="${actionBean.reportName eq 'STRLaporanPenguatkuasaan_MLK.rdf'}">
                    Laporan Penguatkuasaan di bawah Seksyen Akta Hakmilik Strata 1985 
                </c:if>

                <c:if test="${actionBean.reportName eq 'STRDaftarStrata_MLK.rdf'}">
                    Senarai Buku Daftar Strata 
                </c:if>

                <%--<c:if test="${actionBean.reportName eq 'STRPembetulan380_MLK.rdf'}">
                    Senarai Hakmilik  Strata yang terlibat dengan Pembetulan 380
                </c:if>--%>

                <c:if test="${actionBean.reportName eq 'STRLaporanSijilBdnUrus_MLK.rdf'}">
                    Senarai Sijil Perbadanan Pengurusan 
                </c:if>

                <c:if test="${actionBean.reportName eq 'STRTamatStrata_MLK.rdf'}">
                    Senarai Penamatan Skim Strata
                </c:if>

                <c:if test="${actionBean.reportName eq 'STRLaporanBayaran_MLK.rdf'}">
                    Senarai Pemohon Yang Belum Menjelaskan Bayaran Kelulusan Hakmilik Strata

                </c:if>
            </lagend>


            <legend></legend>
            <c:set value="${actionBean.reportName}" var="reportname"/>

            <c:set var="disable" value="false"/>
            <c:if test="${actionBean.pengguna.kodCawangan.kod ne '00'}">
                <c:set var="disable" value="true"/>
            </c:if>


            <%-- <c:if test="${reportname eq 'STRLaporanKumulatifPTG_MLK.rdf'}">
                <label>Laporan Kumulatif Pendaftaran Hakmilik Strata :</label>
                <p> <s:radio name="Jenis" id="Jenis1" value="Hakmilik" onclick="ptg1()"/> Hakmilik PTG/PTD 
                    <s:radio name="Jenis" id="Jenis2" value="Tahunan" onclick="tahunan()"/> Tahunan
                </p>
                <c:if test="${actionBean.ptg}">
                <div id ="keluar">
                    <label><em>*</em>Jenis Hakmilik</label>:
                    <s:select name="report_p_kod_daerah" id="selecthak" style="width:255px;" onchange="getpilih2()">
                        <s:option value="0">Sila pilih</s:option>
                        <s:option value="00">Hakmilik PTG</s:option>
                        <s:option value="PTD">Hakmilik PTD</s:option>
                    </s:select>

                </div>
                <p>
                <div id ="daerah">
                    <p>
                        <label><em>*</em>Cawangan :</label>
                        <s:select id="caw" name="kd" style="width:260px;" disabled="${disable}" onchange="doFilterDaerah(this.value);">
                            <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                        </s:select>
                        <s:hidden name="kod_caw" value="${actionBean.kodCaw}"/>
                    </p>
                </div>
                </p>



                <div id ="tahun">
                    <label>Tahun Mula</label>:
                    <s:select id="tahun" name="report_p_tahun" style="width:145px;">
                        <s:options-collection collection="${actionBean.listYear}"/>
                    </s:select>
                    <BR />
                    <BR />
                    <label>Tahun Akhir</label>:
                    <s:select id="tahun" name="report_p_tahun" style="width:145px;">
                        <s:options-collection collection="${actionBean.listYear}"/>
                    </s:select>

                </div>--%>


            <c:if test="${reportname eq 'STRKumulatifSemasa_MLK.rdf'}">
                <p>
                    <label>Jenis Permohonan :</label>
                <p> <s:radio name="type" id="typeB" value="kumpul" onclick="kumpul()"/>Terkumpul
                    <s:radio name="type" id="typeA" value="semasa" onclick="semasa()"/>Semasa 
                    <s:radio name="type" id="typeC" value="tunggak" onclick="tunggak()"/>Tertunggak
                </p>

                <p>
                    <label>Cawangan :</label>
                    <s:select id = "kodcaw8"  name="report_p_kod_caw">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="00">Pejabat Tanah dan Galian Melaka </s:option>
                        <s:option value="01">Pejabat Tanah Daerah Melaka Tengah</s:option>
                        <s:option value="02">Pejabat Tanah Daerah Jasin</s:option>
                        <s:option value="03">Pejabat Tanah Daerah Alor Gajah</s:option>
                    </s:select>
                </p>
                <p>
                    <label>Tahun :</label>
                    <s:select id="tahun9" name="report_p_tahun" style="width:145px;">
                        <s:option value="">--Sila Pilih--</s:option>
                        <%--<s:option value="2013">2013</s:option>--%>
                        <c:forEach items="${actionBean.senaraiTahunMasuk}" var="year">
                            <s:option value="${year}">${year}</s:option>
                        </c:forEach>
                    </s:select>
                </p>



            </c:if>

            <c:if test="${reportname eq 'STRLaporanPermit_MLK.rdf'}">
                <p style="color:red">
                    *Sila isi semua ruangan dan klik butang papar.
                </p>
                <p>
                    <label>Cawangan :</label>
                    <s:select id="caw" name="report_p_kod_caw" style="width:260px;" disabled="${disable}" onchange="doFilterDaerah(this.value);">
                        <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                    </s:select>
                    <s:hidden name="report_p_kod_caw" value="${actionBean.kodCaw}"/>
                </p>
                <div id="bpmptg">
                    <p>
                        <label>Bandar/Pekan/Mukim :</label>
                        <s:select id="bpm" name="report_p_kod_mukim" style="width:200px;">
                            <s:option value="">SEMUA</s:option>
                            <%--<s:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="kod"/>--%>
                            <s:options-collection collection="${actionBean.senaraiBPM}" label="nama" value="kod"/>
                        </s:select>
                        <s:hidden name="report_p_kod_mukim" value="${actionBean.senaraiBPM}"/>
                    </p>
                </div>
                <p>
                    <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>

                <p>
                    <label>Tarikh Akhir :</label>
                    <s:text id="trh_akhir" name="report_p_trh_akhir" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
            </c:if>
                
            <c:if test="${reportname eq 'STRStatKosRendah_MLK.rdf'
                  }">

                <p>
                    <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
            </c:if>

            <c:if test="${reportname eq 'STRStatPrestasi_MLK.rdf'}">

                <p style="color:red">
                    *Sila pilih jenis laporan yang ingin dipaparkan.
                </p>
                <p> <label>Jenis Laporan :</label>
                    <s:radio style="vertical-align: middle" name="Jenis" id="Jenis1" value="keseluruhan" onclick="keseluruhan()"/>Laporan Keseluruhan 
                    <s:radio style="vertical-align: middle" name="Jenis" id="Jenis2" value="tahun" onclick="tahunan()"/>Laporan Mengikut Tahun
                </p>
                <%--<c:if test="${actionBean.ptg}">--%>
                <div id="satu">
                    <p> <label>Laporan :</label>
                        <s:radio style="vertical-align: middle" name="kategori" id="kat1" value="ikutTahun" onclick="pertama()"/>Laporan Prestasi Permohonan Hakmilik Strata
                        <s:radio style="vertical-align: middle" name="kategori" id="kat2" value="spesifik" onclick="kedua()"/>Laporan Keseluruhan Prestasi Permohonan Hakmilik Strata
                    </p>
                </div>

                <div id ="seluruh">
                    <p>
                        <br></br>
                        <label>Kod Urusan :</label>
                        <s:select name="report_p_kod_urusan" id="ku1">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:option value="PBBD">Permohonan Pecah Bahagi Bangunan dan Tanah </s:option>
                            <s:option value="PBBS">Permohonan Pecah Bahagi Bangunan</s:option>
                            <s:option value="PHPP">Permohonan Penyatuan Petak</s:option>
                            <s:option value="PHPC">Permohonan Pecah Bahagi Petak</s:option>
                            <s:option value="PSBS">Permohonan Pecah Bahagi Bangunan Selepas Penyempurnaan Blok Sementara</s:option>
                        </s:select>
                    </p>
                    <p>
                        <label>Cawangan :</label>
                        <s:select name="report_p_kod_caw" id="cw1">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:option value="00">Pejabat Tanah dan Galian Melaka </s:option>
                            <s:option value="01">Pejabat Tanah Daerah Melaka Tengah</s:option>
                            <s:option value="02">Pejabat Tanah Daerah Jasin</s:option>
                            <s:option value="03">Pejabat Tanah Daerah Alor Gajah</s:option>
                        </s:select>
                    </p>
                    <p>
                        <label>Tahun :</label>
                        <s:select id="tahun2" name="report_p_tahun" style="width:145px;">
                            <s:option value="">--Sila Pilih--</s:option>
                            <%--<s:option value="2013">2013</s:option>--%>
                            <c:forEach items="${actionBean.senaraiTahunMasuk}" var="year">
                                <s:option value="${year}">${year}</s:option>
                            </c:forEach>
                        </s:select>
                    </p>
                </div>

                <div id ="lalala">
                    <p>
                        <br></br>
                        <label>Kod Urusan :</label>
                        <s:select id = "kodurusan" name="report_p_kod_urusan">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="PBBD">Permohonan Pecah Bahagi Bangunan dan Tanah </s:option>
                            <s:option value="PBBS">Permohonan Pecah Bahagi Bangunan</s:option>
                            <s:option value="PHPP">Permohonan Penyatuan Petak</s:option>
                            <s:option value="PHPC">Permohonan Pecah Bahagi Petak</s:option>
                            <s:option value="PSBS">Permohonan Pecah Bahagi Bangunan Selepas Penyempurnaan Blok Sementara</s:option>
                        </s:select>
                    </p>
                    <p>
                        <label>Cawangan :</label>
                        <s:select id = "kodcaw" name="report_p_kod_caw">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="00">Pejabat Tanah dan Galian Melaka </s:option>
                            <s:option value="01">Pejabat Tanah Daerah Melaka Tengah</s:option>
                            <s:option value="02">Pejabat Tanah Daerah Jasin</s:option>
                            <s:option value="03">Pejabat Tanah Daerah Alor Gajah</s:option>
                        </s:select>
                    </p>
                </div>

                <p>
                <div id ="thn">
                    <p>
                        <br></br>
                        <label>Kod Urusan :</label>
                        <s:select id = "kodurusan2" name="report_p_kod_urusan">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="PBBD">Permohonan Pecah Bahagi Bangunan dan Tanah </s:option>
                            <s:option value="PBBS">Permohonan Pecah Bahagi Bangunan</s:option>
                            <s:option value="PHPP">Permohonan Penyatuan Petak</s:option>
                            <s:option value="PHPC">Permohonan Pecah Bahagi Petak</s:option>
                            <s:option value="PSBS">Permohonan Pecah Bahagi Bangunan Selepas Penyempurnaan Blok Sementara</s:option>
                        </s:select>
                    </p>
                    <p>
                        <label>Cawangan :</label>
                        <s:select id = "kodcaw6"  name="report_p_kod_caw">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="00">Pejabat Tanah dan Galian Melaka </s:option>
                            <s:option value="01">Pejabat Tanah Daerah Melaka Tengah</s:option>
                            <s:option value="02">Pejabat Tanah Daerah Jasin</s:option>
                            <s:option value="03">Pejabat Tanah Daerah Alor Gajah</s:option>
                        </s:select>
                    </p>
                    <p>
                        <label>Tahun :</label>
                        <s:select id="tahun3" name="report_p_tahun" style="width:145px;">
                            <s:option value="">--Sila Pilih--</s:option>
                            <%--<s:option value="2013">2013</s:option>--%>
                            <c:forEach items="${actionBean.senaraiTahunMasuk}" var="year">
                                <s:option value="${year}">${year}</s:option>
                            </c:forEach>
                        </s:select>
                    </p>

                    <p>
                        <label>Jenis Permohonan :</label>
                    <p> <s:radio name="permohonan" id="JenisA" value="daftar" onclick="daftar()"/>Daftar 
                        <s:radio name="permohonan" id="JenisB" value="tolak" onclick="tolak()"/>Tolak
                        <s:radio name="permohonan" id="JenisC" value="terima" onclick="terima()"/>Terima
                    </p>

                </div>
                </p>
            </c:if>

            <c:if test="${reportname eq 'STRStatKosRendah_MLK.rdf'
                  }">
                <p>
                    <label>Tarikh Akhir :</label>
                    <s:text id="trh_akhir" name="report_p_trh_akhir" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
            </c:if>

            <c:if test="${reportname eq 'STRStatKosRendah_MLK.rdf'
                  }">
                <p>
                    <label>Cawangan :</label>
                    <s:select name="report_p_kod_caw">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="00">Pejabat Tanah dan Galian Melaka </s:option>
                        <s:option value="01">Pejabat Tanah Daerah Melaka Tengah</s:option>
                        <s:option value="02">Pejabat Tanah Daerah Jasin</s:option>
                        <s:option value="03">Pejabat Tanah Daerah Alor Gajah</s:option>
                    </s:select>
                </p>
            </c:if>

            <c:if test="${reportname eq 'STRLaporanBayaran_MLK.rdf'}">
                <p style="color:red">
                    *Sila pilih jenis laporan yang ingin dipaparkan.
                </p>
                    <p>
                    <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                
                <p>
                    <label>Tarikh Akhir :</label>
                    <s:text id="trh_akhir" name="report_p_trh_akhir" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                
                <p>
                    <label>Cawangan :</label>
                    <s:select name="report_p_kod_caw">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="00">Pejabat Tanah dan Galian Melaka </s:option>
                        <s:option value="01">Pejabat Tanah Daerah Melaka Tengah</s:option>
                        <s:option value="02">Pejabat Tanah Daerah Jasin</s:option>
                        <s:option value="03">Pejabat Tanah Daerah Alor Gajah</s:option>
                    </s:select>
                </p>
                    
                <p>
                    <label>Kod Urusan :</label>
                    <s:select id = "kodurusan2" name="report_p_kod_urusan">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="PBBD">Permohonan Pecah Bahagi Bangunan dan Tanah </s:option>
                        <s:option value="PBBS">Permohonan Pecah Bahagi Bangunan</s:option>
                        <s:option value="PHPP">Permohonan Penyatuan Petak</s:option>
                        <s:option value="PHPC">Permohonan Pecah Bahagi Petak</s:option>
                        <s:option value="PSBS">Permohonan Pecah Bahagi Bangunan Selepas Penyempurnaan Blok Sementara</s:option>
                    </s:select>
                </p>
            </c:if>

            <c:if test="${reportname eq 'STRTamatStrata_MLK.rdf'}">

                <p style="color:red">
                    *Sila pilih cawangan serta tahun dan klik butang papar.
                </p>

                <p>
                    <label>Cawangan :</label>
                    <s:select name="report_p_kod_caw">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="00">Pejabat Tanah dan Galian Melaka </s:option>
                        <s:option value="01">Pejabat Tanah Daerah Melaka Tengah</s:option>
                        <s:option value="02">Pejabat Tanah Daerah Jasin</s:option>
                        <s:option value="03">Pejabat Tanah Daerah Alor Gajah</s:option>
                    </s:select>
                </p>
                <p>
                    <label>Tahun :</label>
                    <s:select id="tahun4" name="report_p_tahun" style="width:145px;">
                        <s:option value="">--Sila Pilih--</s:option>
                        <%--<s:option value="2013">2013</s:option>--%>
                        <c:forEach items="${actionBean.senaraiTahunMasuk}" var="year">
                            <s:option value="${year}">${year}</s:option>
                        </c:forEach>
                    </s:select>
                </p>
            </c:if>

            <%--<c:if test="${reportname eq 'STRLaporanBdnUrus_MLK.rdf'}">
                <p style="color:red">
                    *Sila pilih tahun dan klik butang papar
                </p>
            </c:if>--%>

            <c:if test="${reportname eq 'STRLaporanBdnUrus_MLK.rdf'
                          or reportname eq 'STRLaporanSijilBdnUrus_MLK.rdf'}">

                  <p style="color:red">
                      *Sila tahun dan klik butang papar.
                  </p>

                  <p>
                      <label>Tahun :</label>
                      <s:text name ="report_p_tahun" style="width:145px;" id="tahun5"/>
                      <!--<s:select id="tahun5" name="report_p_tahun" style="width:145px;">
                          <s:option value="">--Sila Pilih--</s:option>
                          <%--<s:option value="2013">2013</s:option>--%>
                          <c:forEach items="${actionBean.senaraiTahunMasuk}" var="year">
                              <s:option value="${year}">${year}</s:option>
                          </c:forEach>
                      </s:select>-->
                  </p>
                  <p>atau</p>
                  <p><label>Nama Perbadanan Pengurusan :</label>
                      <input type="text" id="namabdnurus" name="report_p_nama_bdn_urus" onblur="this.value=this.value.toUpperCase();"/>
                  </p>
            </c:if>

            <%-- <c:if test="${reportname eq 'STRPembetulan380_MLK.rdf' }">
          <p>

                    <label>Daerah :</label>
                    <s:select name="kodDaerah" id="kodDaerah" style="width:260px;" onchange="doFilterBPM(this.value);">
                        <s:option value="">Sila pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod"/>
                    </s:select>
                    <s:hidden name="report_p_kod_daerah" value="${actionBean.kodDaerah}"/>

                </p>
</c:if>--%>


            <c:if test="${reportname eq 'STRDaftarStrata_MLK.rdf'}">
                <p>
                    <label>Cawangan :</label>
                    <s:select id="caw" name="report_p_kod_caw" style="width:260px;" disabled="${disable}" onchange="doFilterDaerah(this.value);">
                        <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                    </s:select>
                    <s:hidden name="report_p_kod_caw" value="${actionBean.kodCaw}"/>
                </p>

                <p>
                    <label>Bandar/Pekan/Mukim :</label>
                    <s:select id="bpm" name="report_p_kod_mukim" style="width:200px;">
                        <s:option value="">SEMUA</s:option>
                        <%--<s:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="kod"/>--%>
                        <s:options-collection collection="${actionBean.senaraiBPM}" label="nama" value="kod"/>
                    </s:select>
                    <s:hidden name="report_p_kod_mukim" value="${actionBean.senaraiBPM}"/>
                </p>


            </c:if>

            <c:if test="${actionBean.reportName eq 'STRStatPemilik_MLK.rdf'}">
                 <p style="color:red">
                    *Sila masukan maklumat berikut dan klik butang papar.
                </p>

                <p>
                    <label>Nama Skim :</label>
                        <input name="report_p_nama_skim" type="text" value='' id="namaProjek" style="text-transform: capitalize"> <font color="red">atau</font>
                        <%--<s:option value="">sila pilih</s:option>
                        <%--<s:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="kod"/>
                        <s:options-collection value="" collection="${actionBean.namaProjek}" />--%>
                    
                </p>

                <p>
                    <label>Nama Perbadanan Pengurusan :</label>
                    <input name="report_p_bdn_pengurusan" type="text" value='' id="bdnUrus" style="text-transform: capitalize ; width:200px;"> <font color="red">atau</font>
                    <%--<s:select id="bdnUrus" name="report_p_bdn_pengurusan" style="width:200px;" class="normal_text">
                        <s:option value="">sila pilih</s:option>
                        <s:options-collection collection="${actionBean.bdnUrus}" />
                    </s:select>--%>
                </p>

                <p>
                    <label>No Lot :</label> 
                    <%--<s:text name="noLot" maxlength="20" size="31" id="noLot"/>--%>
                    <input name="report_p_no_lot" type="text" value='' id="noLot"> <font color="red">atau</font>

                    <%--<s:submit name="find" value="Cari" onclick="return checkvalue(this);" class="btn" />--%>
                </p>
                <p>
                    <label>No Hakmilik :</label>
                    <%--<s:text name="noLot" maxlength="20" size="31" id="noLot"/>--%>
                    <input name="report_p_no_hakmilik" type="text" value='' id="noHakmilik"> <font color="red">atau</font>

                    <%--<s:submit name="find" value="Cari" onclick="return checkvalue(this);" class="btn" />--%>
                </p>
                <p>
                    <label>Bandar/Pekan/Mukim :</label>
                    <%--<s:text name="noLot" maxlength="20" size="31" id="noLot"/>--%>
                    <input name="report_p_mukim" type="text" value='' id="mukim" style="text-transform: capitalize"> 

                    <%--<s:submit name="find" value="Cari" onclick="return checkvalue(this);" class="btn" />--%>
                </p>
                <br>
            </c:if>

            <c:if test="${reportname eq 'STRLaporanPenguatkuasaan_MLK.rdf'}">

                <p style="color:red">
                    *Sila isi semua ruangan dan klik butang papar.
                </p>
                <p>
                    <label>Cawangan :</label>
                    <s:select id="caw" name="report_p_kod_caw" style="width:260px;" disabled="${disable}" onchange="doFilterDaerah(this.value);">
                        <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                    </s:select>
                    <s:hidden name="report_p_kod_caw" value="${actionBean.kodCaw}"/>
                </p>
                <p>
                    <label>Kod Urusan :</label>
                    <s:select name="report_p_kod_urusan" id="urusan1">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="P8">Penguatkuasaan Seksyen 8 Akta Hakmilik Strata 1985 </s:option>
                        <s:option value="P14A">Penguatkuasaan Seksyen 14A Akta Hakmilik Strata 1985</s:option>
                        <s:option value="P22A">Penguatkuasaan Seksyen 22A Akta Hakmilik Strata 1985</s:option>
                        <s:option value="P22B">Penguatkuasaan Seksyen 22B Akta Hakmilik Strata 1985</s:option>
                        <s:option value="P40A">Penguatkuasaan Seksyen 40A(3) Akta Hakmilik Strata 1985</s:option>
                    </s:select>
                </p>


            </c:if>
                <c:if test="${reportname eq 'STRroll_MLK.rdf' or reportname eq 'STRSijilMCManual_MLK.rdf'}">

                    <p style="color:red">
                        *Sila isi Id Hakmilik Induk pada ruangan dan klik butang papar.
                    </p>
                    <p>
                        <label>Id Hakmilik Induk :</label>
                        <s:text name="report_p_id_hakmilik" maxlength="20" size="31" id="idHakmilik"/>
                    </p>
                    <br>
                </c:if>


            <br>


            <div id="papar">
                <p align="center">
                    <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit(this.form);"/>&nbsp;
                    <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
                    <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
                </p>
            </div>


            <div id="noBuku">
                <p align="center">
                    <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmitBukuDaftar(this.form);"/>&nbsp;
                    <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
                    <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
                </p>
            </div>

            <div id="paparPTDJ">
                <p align="center">
                    <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmitJasin(this.form);"/>&nbsp;
                    <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
                    <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
                </p>
            </div>

            <div id="paparD">
                <p align="center">
                    <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmitDaftar(this.form);"/>&nbsp;
                    <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
                    <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
                </p>
            </div>

            <div id="paparKmpl">
                <p align="center">
                    <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmitKumpul(this.form);"/>&nbsp;
                    <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
                    <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
                </p>
            </div>

            <div id="paparTggk">
                <p align="center">
                    <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmitTunggak(this.form);"/>&nbsp;
                    <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
                    <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
                </p>
            </div>

            <div id="paparS">
                <p align="center">
                    <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmitSeluruh(this.form);"/>&nbsp;
                    <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
                    <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
                </p>
            </div>

            <div id="paparTolak">
                <p align="center">
                    <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmitTolak(this.form);"/>&nbsp;
                    <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
                    <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
                </p>
            </div>

            <div id="paparTrm">
                <p align="center">
                    <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmitTerima(this.form);"/>&nbsp;
                    <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
                    <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
                </p>
            </div>


            <div id="paparTHN">
                <p align="center">
                    <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmitThn(this.form);"/>&nbsp;
                    <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
                    <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
                </p>
            </div>

            <div id="paparSkim">
                <p align="center">
                    <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmitSkim(this.form);"/>&nbsp;
                    <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
                    <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
                </p>
            </div>

        </fieldset >
    </div>

</s:form>
