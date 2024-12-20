
<%--
    Document   : kemasukan_perincian_hakmilik
    Created on : 21 Oktober 2009, 4:07:08 PM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<body>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
    <c:if test="${actionBean.p.kodUrusan.kod eq 'HSP' && actionBean.p.kodUrusan.kod eq 'HKP'}">
        <c:set var="disabled" value=""/>
    </c:if>

    <c:if test="${actionBean.p.kodUrusan.kod ne 'HSP' && actionBean.p.kodUrusan.kod ne 'HKP'}">
        <c:set var="disabled" value="disabled"/>
    </c:if>

    <%--<c:if test="${actionBean.p.kodUrusan.kod eq 'HKABS'}">
      <c:set var="disabledbtn" value="disabled"/>
    </c:if>--%>
    <script type="text/javascript">
        var DELIM = "__^$__";
        $(document).ready(function() {
            //-------------------------------------------------------
            /*shows the loading div every time we have an Ajax call*/
            //                $("#displayBox").bind("ajaxSend", function(){
            //                    $(this).show();
            //                   
            //                }).bind("ajaxComplete", function(){
            //                    $(this).hide();
            //                   
            //                });
            //-------------------------------------------------------
            $(".uppercase").bestupper();
            $(".wideselect")
                    .mouseover(function() {
                        $(this).data("origWidth", $(this).css("width")).css("width", "auto");
                    })
                    .mouseout(function() {
                        $(this).css("width", $(this).data("origWidth"));
                    });
            filterKodSeksyen();
            filterKodGunaTanah();
            filterKodUOM();
            filterKodUOMLain();
            filterKodBPM('${actionBean.hakmilik.daerah.kod}');
            $('#selectKodSeksyen').val($('#kodSeksyen').val());
            //$('#kodTanah').val($('#kodGunaTanah').val());
            //alert($('#kodTanah').val());
            //$('#kodUOM').val($('#kodUnitLuas').val());
            //alert($('#kodUOM').val());
            //alert($('#kodGunaTanah').val());
            $("#luas").keyup(function() {
                validNumber($('#luas').val(), 'luas');
                dodacheck($('#luas').val());
            });

        <c:if test="${actionBean.p.kodUrusan.kod eq 'HKABS' || actionBean.p.kodUrusan.kod eq 'HKSB' || actionBean.p.kodUrusan.kod eq 'HSSB' || actionBean.p.kodUrusan.kod eq 'HKABT'}">
            calcLuas($('#luasTerlibat').val());
        </c:if>

            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

        <%--<c:if test="${actionBean.p.kodUrusan.kod eq 'HKABS' || actionBean.p.kodUrusan.kod eq 'HKSB' || actionBean.p.kodUrusan.kod eq 'HKABT'}">
                   $("#kodUOM").change(function(){
                       calcLuas($('#luasTerlibat').val());
                   });
        </c:if>--%>
        });
        function doViewReport(v) {
            var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        function simpanPK(id) {

            var url = '${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?simpanPihakKelompok&idHakmilik=' + id;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    });

        }
        function calcLuas(f) {
            var luasAmbil = f;
            var idHakmilik = $('#hiddenIdHakmilik').val();
            var kodUOMKepada = $('#kodUnitLuas').val();
            //alert(kodUOMKepada);
            if (luasAmbil != "") {
                $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?calcLuasAmbilBalik&idHakmilik=' + idHakmilik + '&luasAmbil=' + luasAmbil + '&kodUOMKepada=' + kodUOMKepada,
                        function(data) {
                            if (data != '0') {
                                //alert('Pelan untuk no lot '+ noLot +' tiada');
                                $("#luas").val(data);

                            }
                        }, 'html');
            }

        }
        function selectAllUrusan(a) {
            //alert("selectall");
            //alert(a);
            for (i = 0; i < 100; i++) {
                var c = document.getElementById("checkboxurusan" + i);
                //alert(c);
                if (c == null)
                    break;
                c.checked = a.checked;
            }
        }
        function selectAllUrusan1(a) {
            //alert("selectall");
            //alert(a);
            for (i = 0; i < 100; i++) {
                var c = document.getElementById("checkboxurusan1" + i);
                //alert(c);
                if (c == null)
                    break;
                c.checked = a.checked;
            }
        }
        function selectAllHakmilikPihak(a) {
            //alert("selectall");
            //alert(a);
            for (i = 0; i < 100; i++) {
                var c = document.getElementById("checkboxhakmilikpihak" + i);
                //alert(c);
                if (c == null)
                    break;
                c.checked = a.checked;
            }
        }
        function selectAllPihak(a) {
            //alert("selectall");
            //alert(a);
            for (i = 0; i < 100; i++) {
                var c = document.getElementById("checkboxpihak" + i);
                //alert(c);
                if (c == null)
                    break;
                c.checked = a.checked;
            }
        }
        function selectAllPihakBaru(a) {
            //alert("selectall");
            //alert(a);
            for (var i = 0; i < 100; i++) {
                var c = document.getElementById("checkboxpihak1" + i);
                //alert(c);
                if (c == null)
                    break;
                c.checked = a.checked;
            }
        }

        function removeMultipleUrusan() {
            if (confirm('Adakah anda pasti untuk hapus Urusan ini')) {
                var id = $('#hiddenIdHakmilik').val();
                var param = '';
                $('.remove2').each(function(index) {
                    var a = $('#checkboxurusan' + index).is(":checked");
                    if (a) {
                        param = param + '&idUrusan=' + $('#checkboxurusan' + index).val();
                    }
                });

                if (param == '') {
                    alert('Sila Pilih Urusan terlebih dahulu.');
                    return;
                }

                var url = '${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?deleteMultipleUrusan' + param + '&idHakmilik=' + id;
                $.get(url,
                        function(data) {
                            $('#page_div').html(data);
                        }, 'html');
            }
        }
        function saveMultipleUrusan() {
            if (confirm('Adakah anda pasti untuk tambah Urusan ini')) {
                var id = $('#hiddenIdHakmilik').val();
                var param = '';
                $('.remove2').each(function(index) {
                    var a = $('#checkboxurusan1' + index).is(":checked");
                    if (a) {
                        param = param + '&idUrusan=' + $('#checkboxurusan1' + index).val();
                    }
                });

                if (param == '') {
                    alert('Sila Pilih Urusan terlebih dahulu.');
                    return;
                }

                var url = '${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?saveMultipleUrusan' + param + '&idHakmilik=' + id;
                $.get(url,
                        function(data) {
                            $('#page_div').html(data);
                        }, 'html');
            }
        }
        function saveMultipleHakmilikPihakTertinggal() {
            if (confirm('Adakah anda pasti untuk tambah Pihak ini')) {
                var id = $('#hiddenIdHakmilik').val();
                var param = '';
                $('.remove2').each(function(index) {
                    var a = $('#checkboxhakmilikpihak' + index).is(":checked");
                    if (a) {
                        param = param + '&idUrusan=' + $('#checkboxhakmilikpihak' + index).val();
                    }
                });

                if (param == '') {
                    alert('Sila Pilih Pihak terlebih dahulu.');
                    return;
                }

                var url = '${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?saveMultipleHakmilikPihakTertinggal' + param + '&idHakmilik=' + id;
                $.get(url,
                        function(data) {
                            $('#page_div').html(data);
                        }, 'html');
            }
        }

        function removeMultipleHakmilikPihak1() {
            if (confirm('Adakah anda pasti untuk hapus Pihak ini')) {
                var param = $('.checkboxpihak').length;
                var id = $('#hiddenIdHakmilik').val();

                if (param == '') {
                    alert('Sila Pilih Pihak terlebih dahulu.');
                    return;
                }
                alert("param" + param);
                alert("id" + id);
//                for (var i = 1; i <= param; i++) {
                if ($('#checkboxpihak' + i).is(":checked")) {
                    var url = '${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?deleteMultipleHakmilikPihak' + param + '&idHakmilik=' + id;
                    $.blockUI({
                        message: $('#displayBox'),
                        css: {
                            top: ($(window).height() - 50) / 2 + 'px',
                            left: ($(window).width() - 50) / 2 + 'px',
                            width: '50px'
                        }
                    });
                    $.get(url,
                            function(data) {
                                $('#page_div').html(data);
                                $.unblockUI();
                            }, 'html');
                }
//                }
            }
        }

        function removeMultipleHakmilikPihak() {
//        alert("Testing")
            if (confirm('Adakah anda pasti untuk hapus Pihak ini1')) {
                var id = $('#hiddenIdHakmilik').val();
                var param = '';
//                alert("Testing")
                $('.checkboxpihak1').each(function(index) {
                    var a = $('#checkboxpihak1' + index).is(":checked");
//                    alert("param" + a);
//                    alert("id" + id);
                    if (a) {
                        param = param + '&idHakmilikPihak=' + $('#checkboxpihak1' + index).val();
                    }
                });

                if (param == '') {
                    alert('Sila Pilih Pihak terlebih dahulu.');
                    return;
                }

                var url = '${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?deleteMultipleHakmilikPihak' + param + '&idHakmilik=' + id;
                $.blockUI({
                    message: $('#displayBox'),
                    css: {
                        top: ($(window).height() - 50) / 2 + 'px',
                        left: ($(window).width() - 50) / 2 + 'px',
                        width: '50px'
                    }
                });
                $.get(url,
                        function(data) {
                            $('#page_div').html(data);
                            $.unblockUI();
                        }, 'html');
            }
        }
        function checkPelan(f) {
            var kodDaerah = "";
        <c:if test="${actionBean.p.kodUrusan.kod ne 'HT'}">
            <c:if test="${disabledbtn eq 'disabled'}">
            var kodDaerah = $('#namaDaerah').val();
            </c:if>
            <c:if test="${disabledbtn ne 'disabled'}">
            var kodDaerah = $('#kodDaerah').val();
            </c:if>
            var noLot = zeroPad(f, 7);

            var kodBPM = $('#kodBpm').val();
            var kodNegeri = $('#kodNegeri').val();
            //var jenisLot = $('#jenisLot').val();
            var namaLot = $('#jenisLot :selected').text();
            var kodSeksyen = $('#selectKodSeksyen').val();
            var kodSeksyen1 = '${actionBean.hakmilik.seksyen.seksyen}'
//                    alert("kodSeksyen1 " + kodSeksyen1);
            if (kodSeksyen1 == "") {
                if (kodSeksyen == "") {
                    kodSeksyen = "000";
                }
            } else {
                kodSeksyen = kodSeksyen1;
            }
            var kodPelan = '${actionBean.kodPelan}';
            //var dmsPath = '${actionBean.dmsPath}';
            //alert(dmsPath);

            //alert("kodDaerah"+kodDaerah);
            //alert("kodBPM"+kodBPM);
            //alert("kodNegeri"+kodNegeri);
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });
            $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?checkPelan&noLot=' + noLot + '&kodDaerah=' + kodDaerah + '&kodSeksyen=' + kodSeksyen + '&kodNegeri=' + kodNegeri + '&kodBPM=' + kodBPM + '&kodSeksyen=' + kodSeksyen,
                    function(data) {
                        if (data != '1') {
                            //alert('Pelan untuk no lot '+ noLot +' tiada');
                            $('#checkPelanMessages').show();
                            $('#checkPelanMessages').attr('class', 'errors');
                            $('#checkPelanMessages').html('Pelan untuk No ' + namaLot + ' ' + noLot + ' tiada');
                            $.unblockUI();
                        } else {
                            $('#checkPelanMessages').show();
                            $('#checkPelanMessages').attr('class', 'messages');
                            //$('#checkPelanMessages').html('Pelan untuk No '+namaLot +' '+ noLot +' wujud');
                            //$('#checkPelanMessages').html('Pelan untuk No '+namaLot +' '+ noLot +' wujud'+ '<a href='+);
                            $('#checkPelanMessages').html('Pelan untuk No ' + namaLot + ' ' + noLot + ' wujud. ' + '<a href=${pageContext.request.contextPath}/pelan/view/' + kodNegeri + kodDaerah + kodBPM + kodSeksyen + kodPelan + noLot + ' target=parent>' + ' Klik Untuk Semak Pelan </a>');
                            $.unblockUI();
                        }
                    });
            $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?checkPelan&noLot=' + noLot + '&kodDaerah=' + kodDaerah + '&kodSeksyen=' + kodSeksyen + '&kodNegeri=' + kodNegeri + '&kodBPM=' + kodBPM + '&kodSeksyen=' + kodSeksyen,
                    function(data) {
                        if (data != '1') {
                            //alert('Pelan untuk no lot '+ noLot +' tiada');
                            $('#checkPelanMessages').show();
                            $('#checkPelanMessages').attr('class', 'errors');
                            $('#checkPelanMessages').html('Pelan untuk No ' + namaLot + ' ' + noLot + ' tiada');
                            $.unblockUI();
                        } else {
                            $('#checkPelanMessages').show();
                            $('#checkPelanMessages').attr('class', 'messages');
                            //$('#checkPelanMessages').html('Pelan untuk No '+namaLot +' '+ noLot +' wujud');
                            //$('#checkPelanMessages').html('Pelan untuk No '+namaLot +' '+ noLot +' wujud'+ '<a href='+);
//                                    alert('kodNegeri' + kodNegeri);
//                                    alert('kodDaerah' + kodDaerah);
//                                    alert('kodBPM' + kodBPM);
//                                    alert('kodSeksyen' + kodSeksyen);
//                                    alert('kodPelan' + kodPelan);
//                                    alert('noLot' + noLot);

                            if (kodSeksyen == "") {
                                kodSeksyen = "000";
                            }

                            $('#checkPelanMessages').html('Pelan untuk No ' + namaLot + ' ' + noLot + ' wujud. ' + '<a href=${pageContext.request.contextPath}/pelan/view/' + kodNegeri + kodDaerah + kodBPM + kodSeksyen + kodPelan + noLot + ' target=parent>' + ' Klik Untuk Semak Pelan </a>');
                            $.unblockUI();
                        }
                    }, 'html');

        </c:if>
        }
        function checkNoLot(f) {
        <c:if test="${actionBean.p.kodUrusan.kod ne 'HT'}">
            var noLot = zeroPad(f, 7);
            //alert(noLot);
            var kodDaerah = $('#kodDaerah').val();
            var kodBPM = $('#kodBpm').val();
            var kodNegeri = $('#kodNegeri').val();
            var jenisLot = $('#jenisLot').val();
            var kodSeksyen = $('#kodSeksyen').val();
            $('#noLot').val(noLot);
            //alert(kodBPM);

            //alert("kodDaerah"+kodDaerah);
            //alert("kodBPM"+kodBPM);
            //alert("kodNegeri"+kodNegeri);
            $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?checkNoLot&noLot=' + noLot + '&kodSeksyen=' + kodSeksyen + '&kodDaerah=' + kodDaerah + '&kodNegeri=' + kodNegeri + '&kodBPM=' + kodBPM + '&jenisLot=' + jenisLot,
                    function(data) {
                        var namaLot = $('#jenisLot :selected').text();
                        if (data != '1') {
                            //alert('Lot '+ noLot +' telah wujud');
                            $('#checkLotMessages').attr('class', 'errors');
                            $('#checkLotMessages').html('No ' + namaLot + ' ' + noLot + ' telah digunakan');
                            $('#checkLotMessages').show();
                            //$("#noLot").val("");
                            //$("#noLot").focus();
                        } else {

                            //$('#checkLotMessages').hide();
                            $('#checkLotMessages').attr('class', 'messages');
                            $('#checkLotMessages').html('No ' + namaLot + ' ' + noLot + ' belum pernah digunakan');
                            $('#checkLotMessages').show();
                        }


                    }, 'html');
        </c:if>
        }
        function zeroPad(num, count)
        {
            var numZeropad = num + '';
            while (numZeropad.length < count) {
                numZeropad = "0" + numZeropad;
            }
            return numZeropad;
        }
        function filterKodBPM(f) {
            var kodDaerah = f
            //alert(kodDaerah);
            //var q = $(f).formSerialize();
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });
            $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?senaraiKodBPMByDaerah&kodDaerah=' + kodDaerah,
                    function(data) {
                        if (data != '') {
                            $('#partialKodBPM').html(data);
                            $.unblockUI();
                        }
                    }, 'html');
        }
        function filterKodSeksyen() {
            //alert('filter');
            var kodBpm = $("#kodBPM").val();
            var kodDaerah = $("#kodDaerah").val();
            var idHakmilik2 = $("#hiddenIdHakmilik").val();
            //alert(kodBpm);
            //alert(kodDaerah);
            //var q = $(f).formSerialize();
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });
            $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?senaraiSeksyenByBPM&kodBpm=' + kodBpm + '&idHakmilik=' + idHakmilik2 + '&kodDaerah=' + kodDaerah,
                    function(data) {
                        if (data != '') {
                            $('#partialKodSeksyen').html(data);
                            $.unblockUI();
                        }
                    }, 'html');
        }
        function filterKodUOM() {
            //alert('filter');
            //var kodBpm = $("#kodBpm").val();
            //var kodDaerah = $("#kodDaerah").val();
            //alert(kodBpm);
            //alert(kodDaerah);
            //var q = $(f).formSerialize();

            var katTanah = $("#katTanah").val();
            //alert(katTanah);
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });
            $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?filterKodUOM&katTanah=' + katTanah,
                    function(data) {
                        if (data != '') {
                            $('#partialKodUOM').html(data);
                            $.unbloclUI();
                        }
                    }, 'html');
        }
        function filterKodUOMLain() {
            //alert('filter');
            //var kodBpm = $("#kodBpm").val();
            //var kodDaerah = $("#kodDaerah").val();
            //alert(kodBpm);
            //alert(kodDaerah);
            //var q = $(f).formSerialize();

            var katTanah = $("#katTanah").val();
            //alert(katTanah);
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });
            $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?filterKodUOMLain&katTanah=' + katTanah,
                    function(data) {
                        if (data != '') {
                            $('#partialKodUOMLain').html(data);
                            $.unbloclUI();
                        }
                    }, 'html');
        }
        function filterKodGunaTanah() {
            //alert('filter');
            var katTanah = $("#katTanah").val();
            //alert(katTanah);
            // var q = $(f).formSerialize();
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });
            $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?senaraiKodGunaTanahByKatTanah&kodKatTanah=' + katTanah,
                    function(data) {
                        if (data != '') {
                            $('#partialKodGunaTanah').html(data);
                            $.unblockUI();
                        }
                    }, 'html');

        }
        function validNumber(val, id) {
            var alphaOnly = /[A-Za-z]/g;
            var strLength = val.length;
            var lchar = val.charAt((strLength) - 1);
            //if(isNaN(lchar)){
            if (lchar.match(alphaOnly)) {
                var tst = val.substring(0, (strLength) - 1);
                $('#' + id).val(tst);
            }
        }

        //fikri : automatic insert into table for syer
        function updateSyer(idpihak, id) {
            var s1 = $('#syer1' + id).val();
            var s2 = $('#syer2' + id).val();

            if (s1 == '' || s2 == '') {
                return;
            }

            if (isNaN(s1) && isNaN(s2)) {
                return;
            }
            var url = '${pageContext.request.contextPath}/pihakBerkepentingan?updateSyerHakmilikPihak&idpihak=' + idpihak
                    + '&syer1=' + s1 + '&syer2=' + s2;
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });
            $.post(url,
                    function(data) {
                        $.unblockUI();
                    }, 'html');

        }

        function samaRata(f) {
            var q = $(f).formSerialize();
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });
            $.get('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?agihSamaRata', q,
                    function(data) {
                        if (data == null || data.length == 0) {
                            alert('Terdapat Masalah');
                            return;
                        }
                        var p = data.split(DELIM);
                        $('.pembilang').each(function() {
                            $(this).val(p[0]);
                        });
                        $('.penyebut').each(function() {
                            $(this).val(p[1]);
                        });
                        $.unblockUI();
                    });
        }
        //VALIDATION FOR 4 TITIK PERPULUHAN
        function dodacheck(val) {
            var luas = val;
            var temp = luas.split('.');
            var strLength = temp[1].length;
            if (strLength > 4) {
                var tst = temp[1].substring(0, (strLength) - 1);
                $('#luas').val(temp[0] + '.' + tst);
            }
            checkdot($('#luas').val());
        }
        //CHECK DOT IN LUAS AND ALLOW ONLY ONE DOT IN IT
        function checkdot(val) {
            var luas = val;
            var temp = luas.split('.');
            var arrLength = temp.length;
            var strLength = temp[1].length;
            if (arrLength > 2) {
                var tst = temp[1].substring(0, (strLength));
                $('#luas').val(temp[0] + '.' + tst);
            }
        }
        function popupKiraUnitLuas() {
            var url = "${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?popupAreaConversion";
            window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=800");
            //maximizeWindow();
        }
        function popupPihak(id) {
            var url = "${pageContext.request.contextPath}/pihakBerkepentingan?pihakKepentinganPopup&idHakmilik=" + id;
            window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=800");
            //maximizeWindow();
        }

        function popupFormSyaratNyata() {
            var url = "${pageContext.request.contextPath}/pendaftaran/maklumat_asas?showFormCariKodSyaratNyata";
            window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
        }

        function popupFormKodSekatan() {
            var url = "${pageContext.request.contextPath}/pendaftaran/maklumat_asas?showFormCariKodSekatan";
            window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
        }
        function popupHakmilikDetails(id) {
            //alert(id);
            var url = "${pageContext.request.contextPath}/hakmilik?popupHakmilikDetail&idHakmilik=" + id;
            window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
        }

        function popupHakmilikAsal(id) {
            var url = "${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?showCarianHakmilikAsal&idHakmilik=" + id;
            window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,width=900,height=600");
        }

        function popupHakmilikSblm(id) {
            var url = "${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?showCarianHakmilikSebelum&idHakmilik=" + id;
            window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,width=900,height=600");
        }

        function popupEditPihak(id, idH, idpbk) {
            var url = "${pageContext.request.contextPath}/pihakBerkepentingan?showFormEdit&idPihak=" + id + "&idHakmilik=" + idH + "&idPBK=" + idpbk;
            window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
        }

        function popupSenaraiPihak(idP) {
            //alert('idPermohonan'+idP);
            //alert('idHakmilik'+idH);
            var url = "${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?senaraiPBPopup&idPermohonan=" + idP;
            window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
        }

        function resetHakmilikUrusan() {
            var id = $('#hiddenIdHakmilik').val();
            var answer = confirm("adakah anda pasti untuk reset semula urusan?");
            if (answer) {
                var url = '${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?resetUrusan&idHakmilik=' + id;
                $.get(url,
                        function(data) {
                            $('#page_div').html(data);
                        });
            }
        }

        function resetHakmilikPihak() {
            var id = $('#hiddenIdHakmilik').val();
            var answer = confirm("adakah anda pasti untuk reset semula pihak?");
            if (answer) {
                var url = '${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?resetPihak&idHakmilik=' + id;
                $.get(url,
                        function(data) {
                            $('#page_div').html(data);
                        });
            }
        }

        function removePihakBerkepentingan(val) {
            alert(val);
            var id = $('#hiddenIdHakmilik').val();
            var answer = confirm("adakah anda pasti untuk Hapus?");
            if (answer) {
                var url = '${pageContext.request.contextPath}/pihakBerkepentingan?deletePihakKepentingan&id_hP=' + val + '&idHakmilik=' + id;
                $.blockUI({
                    message: $('#displayBox'),
                    css: {
                        top: ($(window).height() - 50) / 2 + 'px',
                        left: ($(window).width() - 50) / 2 + 'px',
                        width: '50px'
                    }
                });
                $.get(url,
                        function(data) {
                            $('#page_div').html(data);
                            $.unblockUI();
                        });
            }
        }

        function removeMohonPihak(val) {
            var id = $('#hiddenIdHakmilik').val();
            var answer = confirm("adakah anda pasti untuk Hapus?");
            if (answer) {
                var url = '${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?deleteMohonPihak&idMohonPihak=' + val + '&idHakmilik=' + id;
                $.blockUI({
                    message: $('#displayBox'),
                    css: {
                        top: ($(window).height() - 50) / 2 + 'px',
                        left: ($(window).width() - 50) / 2 + 'px',
                        width: '50px'
                    }
                });
                $.get(url,
                        function(data) {
                            $('#page_div').html(data);
                            $.unblockUI();
                        });
            }
        }

        function removePemohon(val) {
            var id = $('#hiddenIdHakmilik').val();
            var answer = confirm("adakah anda pasti untuk Hapus?");
            if (answer) {
                var url = '${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?deletePemohon&idPemohon=' + val + '&idHakmilik=' + id;
                $.blockUI({
                    message: $('#displayBox'),
                    css: {
                        top: ($(window).height() - 50) / 2 + 'px',
                        left: ($(window).width() - 50) / 2 + 'px',
                        width: '50px'
                    }
                });
                $.get(url,
                        function(data) {
                            $('#page_div').html(data);
                            $.unblockUI();
                        });
            }
        }
        function removeHakmilikUrusan(val) {
            var id = $('#hiddenIdHakmilik').val();
            var answer = confirm("adakah anda pasti untuk Hapus?");
            if (answer) {
                var url = '${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?deleteHakmilikUrusan&idUrusan=' + val + '&idHakmilik=' + id;
                $.blockUI({
                    message: $('#displayBox'),
                    css: {
                        top: ($(window).height() - 50) / 2 + 'px',
                        left: ($(window).width() - 50) / 2 + 'px',
                        width: '50px'
                    }
                });
                $.get(url,
                        function(data) {
                            $('#page_div').html(data);
                            $.unblockUI();
                        });
            }
        }

        function removeHakmilikAsal(val) {
            var id = $('#hiddenIdHakmilik').val();
            var answer = confirm("adakah anda pasti untuk Hapus?");
            if (answer) {
                var url = '${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?deleteHakmilikAsal&idHakmilikAsal=' + val + '&idHakmilik=' + id;
                $.get(url,
                        function(data) {
                            $('#page_div').html(data);
                        });
            }
        }

        function removeHakmilikSblm(val) {
            var id = $('#hiddenIdHakmilik').val();
            var answer = confirm("adakah anda pasti untuk Hapus?");
            if (answer) {
                var url = '${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?deleteHakmilikSblm&idHakmilikSblm=' + val + '&idHakmilik=' + id;
                $.get(url,
                        function(data) {
                            $('#page_div').html(data);
                        });
            }
        }

        function semakSyer(f, id) {
            var q = $(f).formSerialize();
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });
            $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?semakSyer&idHakmilik=' + id, q,
                    function(data) {
                        if (data != '') {
//                                alert(data);
                            $.unblockUI();
                        }
                    }, 'html');
        }

        function cariKadarCukai(f, id) {
            var kodTanah = $("#kodTanah").val();
            var kodBpm = $("#kodBpm").val();
            var luas = $("#luas").val();
            var kodUOM = $("#kodUOM").val();
            //alert(kodTanah);
            //alert(kodBpm);
            //alert(luas);
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?cariKadarCukai&idHakmilik=' + id + '&kodBpm=' + kodBpm + '&kodTanah=' + kodTanah + '&luas=' + luas + '&kodUOM=' + kodUOM, q,
                    function(data) {
                        if (data != '') {
                            $('#kadarCukai').val(convert(data, 'kadarCukai'));
                        } else {
                            $('#kadarCukai').val('');
                        }
                    }, 'html');
        }
        function cariKodCukai(f, id) {

            var kodTanah = $("#kodTanah").val();
            var kodBpm = $("#kodBpm").val();
            var luas = $("#luas").val();
            //alert(kodTanah);
            //alert(kodBpm);
            //alert(luas);
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?cariKodCukai&idHakmilik=' + id + '&kodBpm=' + kodBpm + '&kodTanah=' + kodTanah + '&luas=' + luas, q,
                    function(data) {
                        if (data != '') {
                            $('#kodCukai').val(data);

                        } else {
                            $('#kodCukai').val('');
                        }
                    }, 'html');
        }

        function kiraCukai(f, id) {
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });
            id = '${actionBean.hakmilik.idHakmilik}';
            var kodTanah = $("#kodTanah").val();
            var kodBpm = $("#kodBpm").val();
            var kodUOM = $("#kodUOM").val();
            var kodRizab = $("#kodRizab").val();
            //alert(kodRizab);
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?kiraCukai&idHakmilik=' + id + '&kodBpm=' + kodBpm + '&kodTanah=' + kodTanah + '&kodUOM=' + kodUOM + '&kodRizab=' + kodRizab, q,
                    function(data) {
                        if (data == '<1') {
                            alert("Luas baru tidak boleh kurang dari kosong");
                            $('#cukai').val('0');
                            $('#luas').val('0');
                            $.unblockUI();
                        } else if (data == '>1') {
                            alert("Luas baru tidak boleh melebihi luas asal");
                            $('#cukai').val('0');
                            $('#luas').val('0');
        <%--<c:if test="${actionBean.p.kodUrusan.kod eq 'HKPB' || actionBean.p.kodUrusan.kod eq 'HSPB' }">
                        $('luas').val('${actionBean.hakmilik.luas}')
        </c:if>
        <c:if test="${actionBean.p.kodUrusan.kod ne 'HKPB' && actionBean.p.kodUrusan.kod ne 'HSPB' }">
                        $('#luas').val('0');
        </c:if>--%>
                            $.unblockUI();
                        } else {
                            $('#cukai').val(convert(data, 'cukai'));
                            $.unblockUI();
                        }
                    }, 'html');
        }


        function validateNumber(elmnt, content) {
            //if it is character, then remove it..
            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }

        function removeNonNumeric(strString)
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

        function cariWaris(id) {
            var url = "${pageContext.request.contextPath}/daftar/pembetulan_pihak?cariWaris&idHakmilikPihakBerkepentingan=" + id;
            window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
        }

    </script>

    <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
    <s:form beanclass="etanah.view.stripes.KemasukanPerincianHakmilikActionBean" id="kemasukanPerincianHakmilik">
        <s:messages/>
        <s:errors/>
        <%--                            hidden default value                                                --%>
        <s:hidden name="hakmilik.cawangan.kod" value="${actionBean.hakmilik.cawangan.kod}"/>
        <%-- <s:hidden name="hakmilik.daerah.kod" id="kodDaerah" value="${actionBean.hakmilik.daerah.kod}"/>--%>
        <s:hidden name="kodNegeri" id="kodNegeri" value="${actionBean.kodNegeri}"/>
        <%-- <s:hidden name="hakmilik.kategoriTanah.kod" value="${actionBean.hakmilik.kategoriTanah.kod}"/>--%>
        <s:hidden name="hakmilik.milikPersekutuan" value="${actionBean.hakmilik.milikPersekutuan}"/>
        <s:hidden name="hakmilik.tarikhDaftar" value="${actionBean.hakmilik.tarikhDaftar}"/>
        <s:hidden name="hakmilik.kodHakmilik.kod" value="${actionBean.hakmilik.kodHakmilik.kod}"/>
        <%--<s:hidden name="hakmilik.rizab.kod" value="${actionBean.hakmilik.rizab.kod}"/>--%>
        <s:hidden name="hakmilik.pegangan" value="${actionBean.hakmilik.pegangan}"/>
        <s:hidden name="hakmilik.tempohPegangan" value="${actionBean.hakmilik.tempohPegangan}"/>
        <s:hidden name="hakmilik.tarikhLuput" value="${actionBean.hakmilik.tarikhLuput}"/>
        <s:hidden name="hakmilik.noPu" value="${actionBean.hakmilik.noPu}" />
        <%--<s:hidden name="hakmilik.syaratNyata.kod" value="${actionBean.hakmilik.syaratNyata.kod}" />--%>
        <%--<s:hidden name="hakmilik.sekatanKepentingan.kod" value="${actionBean.hakmilik.sekatanKepentingan.kod}" />--%>
        <s:hidden name="hakmilik.kodStatusHakmilik.kod" value="${actionBean.hakmilik.kodStatusHakmilik.kod}" />
        <%--<s:hidden name="hakmilik.pbt.kod" value="${actionBean.hakmilik.pbt.kod}" />--%>
        <s:hidden name="hakmilik.noHakmilik" value="${actionBean.hakmilik.noHakmilik}" />
        <%--                          end of  hidden default value                                           --%>
        <s:hidden name="hakmilik.idHakmilik" id="hiddenIdHakmilik" value="${actionBean.hakmilik.idHakmilik}"/>
        <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>


        <%-- <div id="checkLotMessages" style="display: none;margin-top: 10px;margin-bottom: 10px;"></div>
         <div id="checkPelanMessages" style="display: none;margin-top: 10px;margin-bottom: 10px;"></div>--%>

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Perincian Hakmilik</legend>
                <%--${disablebtn}--%>
                <p><label>ID Hakmilik :</label>
                    ${actionBean.hakmilik.idHakmilik}
                    &nbsp;
                </p>
                <c:if test="${actionBean.p.kodUrusan.kod ne 'HT'}">
                    <p><label>No Fail :</label>
                        <s:text name="hakmilik.noFail" value="${actionBean.hakmilik.noFail}" class="uppercase" size="40"/>
                    </p>

                    <%-- ${disabled}--%>
                    <p>
                        <label>Daerah :</label>
                        <c:if test="${disabled eq 'disabled'}" >
                            <s:hidden name="hakmilik.daerah.kod" id="kodDaerah" value="${actionBean.hakmilik.daerah.kod}"/>
                        </c:if>
                        <s:hidden name="hakmilik.daerah.kod" id="kodDaerah" value="${actionBean.hakmilik.daerah.kod}"/>
                        <s:select  disabled="${disabled}" name="hakmilik.daerah.kod" id="namaDaerah" onchange="filterKodBPM(this.value);" style="width:200pt">
                            <%--<s:option value="">-- Sila Pilih --</s:option>--%>
                            <s:options-collection collection="${actionBean.senaraiKodDaerah}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <%--${actionBean.kodBPM}--%>
                    <s:hidden name="hakmilik.bandarPekanMukim.bandarPekanMukim" id="kodBPM"/>
                    <div id="partialKodBPM"></div>

                    <c:if test="${actionBean.p.kodUrusan.kod ne 'BMSTM'}">
                        <p>
                            <label>Lokasi 1:</label>
                            <c:if test="${disabledbtn eq 'disabled'}">
                                <s:hidden name="hakmilik.lokasi" value="${actionBean.hakmilik.lokasi}"/>
                            </c:if>
                            <s:text name="hakmilik.lokasi" value="${actionBean.hakmilik.lokasi}" class="uppercase" disabled="${disabledbtn}" size="40"/>
                        </p>
                    </c:if>
                    <s:hidden name="hakmilik.seksyen.kod" id="kodSeksyen"/>

                    <div id="partialKodSeksyen">

                    </div>

                    <p><label>Kategori Tanah <c:if test="${actionBean.p.kodUrusan.kod eq 'BMSTM'}">Bawah Tanah </c:if> : </label>
                        <%--<c:if test="${disabledbtn eq 'disabled'}">
                            <s:hidden name="hakmilik.kategoriTanah.kod" value="${actionBean.hakmilik.kategoriTanah.kod}"/>
                        </c:if>--%>
                        <%--<s:select style="text-transform:uppercase" name="hakmilik.kategoriTanah.kod" id="katTanah" value="${actionBean.hakmilik.kategoriTanah.kod}" onchange="filterKodGunaTanah();filterKodUOM();kiraCukai(this.form,'${actionBean.hakmilik.idHakmilik}');" disabled="${disabledbtn}">--%>
                        <s:select style="text-transform:uppercase;width:200pt" name="hakmilik.kategoriTanah.kod" id="katTanah" value="${actionBean.hakmilik.kategoriTanah.kod}" onchange="filterKodGunaTanah();filterKodUOM();kiraCukai(this.form,'${actionBean.hakmilik.idHakmilik}');" >
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodKategoriTanah}" label="nama" value="kod" />
                        </s:select>
                    </p>

                    <s:hidden name="hakmilik.kegunaanTanah.kod" id="kodGunaTanah"/>
                    <%--<c:if test="${disabledbtn eq 'disabled'}">
                        <s:hidden name="kodGunaTanah" value="${actionBean.kodGunaTanah}"/>
                    </c:if>--%>
                    <%--${actionBean.kodGunaTanah}--%>
                    <div id="partialKodGunaTanah">
                        <%--<p><label>Kegunaan Tanah : </label>
                            <s:select style="text-transform:uppercase" class="wideselect" name="kodGunaTanah" id="kodTanah" onchange="kiraCukai(this.form,'${actionBean.hakmilik.idHakmilik}');cariKadarCukai(this.form,'${actionBean.hakmilik.idHakmilik}');cariKodCukai(this.form,'${actionBean.hakmilik.idHakmilik}');">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKegunaanTanah}" label="nama" value="kod" />
                            </s:select>
                        </p>--%>
                    </div>


                    <p><label>Kod Lot / No Lot <c:if test="${actionBean.p.kodUrusan.kod eq 'BMSTM'}">Stratum </c:if>: </label>
                        <s:select  style="text-transform:uppercase;width:100pt" name="hakmilik.lot.kod" id="jenisLot" value="${actionBean.hakmilik.lot.kod}">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodLot}" label="nama" value="kod" />
                        </s:select> /
                        <%--<s:text name="hakmilik.noLot" id="noLot" value="${actionBean.hakmilik.noLot}" onblur="checkPelan(this.value);checkNoLot(this.value);" />--%>
                        <s:text name="hakmilik.noLot" id="noLot" size="10" maxlength="7" value="${actionBean.hakmilik.noLot}" onblur="checkNoLot(this.value);checkPelan(this.value);"/>

                    </p>

                    <center><div id="checkLotMessages" style="font-size: 12px;display: none;margin-top: 10px;margin-bottom: 10px;width:50%;"></div>
                        <div id="checkPelanMessages" style="font-size: 12px;display: none;margin-top: 10px;margin-bottom: 10px;width:50%;"></div></center>

                    <%-- <p>
                         <label>&nbsp;</label>
                         <s:button name="kiraUnitLuas" value="Kira Unit Luas" id="kiraUnitLuas" class="longbtn" onclick="popupKiraUnitLuas();"/>
                     </p>--%>

                    <c:if test="${actionBean.p.kodUrusan.kod ne 'HKABS' 
                                  && actionBean.p.kodUrusan.kod ne 'HKSB' 
                                  && actionBean.p.kodUrusan.kod ne 'HSSB' 
                                  && actionBean.p.kodUrusan.kod ne 'HKABT'
                                  && actionBean.p.kodUrusan.kod ne 'HKSA' 
                                  && actionBean.p.kodUrusan.kod ne 'HSSA' }">
                          <p>
                              <label>
                                  <c:choose>
                                      <c:when test="${actionBean.p.kodUrusan.kod ne 'BMSTM'}">Keluasan / Unit :</c:when>
                                      <c:otherwise>Isipadu Lot Stratum / Unit</c:otherwise>
                                  </c:choose>

                              </label>
                              <s:text name="hakmilik.luas" id="luas" value="${actionBean.hakmilik.luas}" formatPattern="###0.0000" onblur="kiraCukai(this.form,'${actionBean.hakmilik.idHakmilik}');"/>
                              /
                              <s:hidden name="hakmilik.kodUnitLuas.kod" id="kodUnitLuas"/>

                              <s:select  style="text-transform:uppercase" id="kodUOM" name="kodUnitLuas" onchange="kiraCukai(this.form,'${actionBean.hakmilik.idHakmilik}');">
                                  <%-- <s:option value="">Sila Pilih</s:option>--%>
                                  <s:options-collection collection="${list.senaraiKodUOMByJarak}" label="nama" value="kod"/>
                              </s:select>


                          </p>
                          <c:if test="${actionBean.p.kodUrusan.kod eq 'BMSTM' || actionBean.p.kodUrusan.kod eq 'HSBM'
                                        || actionBean.p.kodUrusan.kod eq 'HKBM'}">
                                <p>
                                    <label>Kedalaman / Unit :</label>
                                    <s:text name="hakmilik.kedalamanTanah" onkeyup="validateNumber(this,this.value);"/>
                                    /
                                    <s:select  style="text-transform:uppercase" id="hakmilik.kedalamanTanahUOM.kod" name="hakmilik.kedalamanTanahUOM.kod">    
                                        <c:forEach items="${list.kodUOMByJarak}" var="item">
                                            <c:if test="${item.kod == 'JM'}">
                                                <s:option value="${item.kod}">${item.nama}</s:option>
                                            </c:if>
                                        </c:forEach>
                                        <%--<s:options-collection collection="${list.kodUOMByJarak}" label="nama" value="kod"/>--%>
                                    </s:select>
                                </p>
                          </c:if>
                    </c:if>
                    <c:if test="${actionBean.p.kodUrusan.kod eq 'HKSA' || actionBean.p.kodUrusan.kod eq 'HSSA' }">
                        <p>
                            <label>
                                <c:choose>
                                    <c:when test="${actionBean.p.kodUrusan.kod eq 'BMSTM'}">Isipadu </c:when>
                                    <c:otherwise>Keluasan </c:otherwise>
                                </c:choose>
                                / Unit :</label>
                                <s:text name="luasSA" id="luas" value="${actionBean.luasHakmilikAsal}" 
                                        formatPattern="###0.0000" onblur="kiraCukai(this.form,'${actionBean.luasHakmilikAsal}');"/>
                            /<span id="partialKodUOM"></span>
                            <s:hidden name="kodUnitLuasLama" id="kodUnitLuas"/>
                        </p>
                        <p>
                            <label>Cukai :</label>
                            RM <s:text name="cukaiSA" value="${actionBean.cukaiLama}" id="cukai" formatPattern="#,###.00"/>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.p.kodUrusan.kod eq 'HKABS' || actionBean.p.kodUrusan.kod eq 'HKSB' || actionBean.p.kodUrusan.kod eq 'HSSB' || actionBean.p.kodUrusan.kod eq 'HKABT'}">
                        <p>
                            <label>Keluasan Hakmilik Asal :</label>
                            <c:if test ="${actionBean.luasHakmilikAsal ne null && actionBean.kodUnitLuasLama ne null}">
                                ${actionBean.luasHakmilikAsal} ${actionBean.kodUnitLuasLama}
                            </c:if>
                            <c:if test ="${actionBean.luasHakmilikAsal eq null && actionBean.kodUnitLuasLama eq null}">
                                0
                            </c:if>
                            <%--<s:text name="hp.luasTerlibat" id="luasTerlibat" value="${actionBean.hp.luasTerlibat}" formatPattern=".####" onblur="calcLuas(this.value);"/>--%>
                        </p>
                        <p>
                            <label>Keluasan Yang Diambil :</label>
                            <%--<s:text name="luasTerlibat" id="luasTerlibat" formatPattern="#.0000" onblur="calcLuas(this.value);"/>--%>  <%--/ ${actionBean.kodUnitLuasLama}--%>
                            <s:text name="luasTerlibat" formatPattern="#.0000" id="luasTerlibat" size="11"/>
                            <!--comment out by azri: kat n9 bleh edit lak mendealah ni..-->
                            <%--<c:if test ="${actionBean.luasTerlibat ne null}">                 
                              ${actionBean.luasTerlibat}
                            </c:if>
                            <c:if test ="${actionBean.luasTerlibat eq null}">
                              0
                            </c:if>  --%>            
                            &nbsp;${actionBean.kodUnitLuasLama}
                            <s:hidden name="kodUnitLuas"/>
                            <%-- <s:hidden name="kodUnitLuas" id="kodUnitLuas"/>
                             <span id="partialKodUOM">
                             </span>--%>
                        </p>

                        <p>
                            <label>Keluasan Baru / Unit  :</label>

                            <s:text name="hakmilik.luas" id="hakmilik.luas" formatPattern="#.0000" onblur="kiraCukai(this.form,'${actionBean.hakmilik.idHakmilik}');"/>
                            /
                            <s:hidden name="hakmilik.kodUnitLuas.kod" id="kodUnitLuas"/>

                            <s:select  style="text-transform:uppercase" name="kodUOM" value="${actionBean.hakmilik.kodUnitLuas.kod}">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodUOMByLuas}" label="nama" value="kod"/>
                            </s:select>
                        </p>


                    </c:if>
                    <c:if test="${actionBean.p.kodUrusan.kod eq 'HKABS' || actionBean.p.kodUrusan.kod eq 'HKSB' || actionBean.p.kodUrusan.kod eq 'HSSB' || actionBean.p.kodUrusan.kod eq 'HKABT'}">
                        <p>
                            <label>Cukai Lama :</label>
                            RM ${actionBean.cukaiLama}
                        </p>
                    </c:if>
                    <c:if test="${actionBean.p.kodUrusan.kod ne 'HSSA' && actionBean.p.kodUrusan.kod ne 'HKSA'}">
                        <p>
                            <label>Cukai :</label>
                            RM <s:text name="hakmilik.cukai" value="${actionBean.hakmilik.cukai}" id="cukai" formatPattern="#,###.00"/>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.hakmilik.kodHakmilik.kod ne 'HSM' && actionBean.hakmilik.kodHakmilik.kod ne 'HSD' }">
                        <p>
                            <c:if test="${actionBean.hakmilik.kodHakmilik.kod eq 'HMM'}">
                                <label>No Permintaan Ukur :</label>
                            </c:if>
                            <c:if test="${actionBean.hakmilik.kodHakmilik.kod ne 'HMM'}">
                            <label>No Pelan <c:if test="${actionBean.p.kodUrusan.kod eq 'BMSTM'}">Stratum </c:if> Diperakui :</label>
                            </c:if>
                            <s:text name="hakmilik.noPelan" value="${actionBean.hakmilik.noPelan}" size="40"/>
                        </p>
                    </c:if>
                    <p>
                        <label>Keluasan Lain / Unit Lain :</label>
                        <s:text name="hakmilik.luasAlternatif" id="luas" formatPattern="###0.0000" />
                        /
                        <s:hidden name="hakmilik.kodUnitLuasAlternatif.kod" id="kodUnitLuasLain"/>
                        <span id="partialKodUOMLain">

                        </span>
                    </p>

                    <p>
                        <label>Nombor Lembaran Piawai :</label>
                        <s:text name="hakmilik.noLitho" value="${actionBean.hakmilik.noLitho}" onblur="this.value=this.value.toUpperCase();" size="40"/>
                    </p>
                    <p>
                        <label>Syarat Nyata :</label>
                        <s:text name="kodSyarat" id="syaratNyata" readonly="true" size="40"/> &nbsp;&nbsp;
                        <s:button name="cariKodSyaratNyata" value="Cari" id="cariKodSyaratNyata" class="btn" onclick="popupFormSyaratNyata();" disabled="${disabledbtn}" />
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:textarea name="syaratnyata" rows="10" style="width:40%;" readonly="true" id="syaratnyatadetails">${actionBean.hakmilik.syaratNyata.syarat}</s:textarea>
                        </p>
                        <p>
                            <label>Sekatan Kepentingan :</label>
                        <s:text name="kodSekatan" id="sekatan" readonly="true" size="40"/> &nbsp;&nbsp;
                        <s:button name="cariKodSekatan" value="Cari" id="cariKodSekatan" class="btn" onclick="popupFormKodSekatan();" disabled="${disabledbtn}"/>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:textarea name="sekatan" rows="10" style="width:40%;" readonly="true" id="sekatandetails" >${actionBean.hakmilik.sekatanKepentingan.sekatan}</s:textarea>
                        </p>
                    <%--<p><label>Kod Cukai :</label>
                        <s:text name="hakmilik.kodKadarCukai.kod" readonly="true" value="${actionBean.hakmilik.kodKadarCukai.kod}" id="kodCukai"/> &nbsp;
                    </p>--%>
                    <%-- <p>
                         <label>Kadar Cukai / Unit :</label>

                     RM <s:text name="hakmilik.kodKadarCukai.kadarMeterPersegi" id="kadarCukai" value="${actionBean.hakmilik.kodKadarCukai.kadarMeterPersegi}" readonly="true" onblur="convert(this.value,this.id);" formatPattern="#,###.00"/> /
                     Meter Persegi
                 </p>--%>

                    <%--  <p>
                          <label>Kaw. Berkuasa Tempatan :</label>

                  <s:select name="hakmilik.pbt.kod">
                      <s:option value="">Sila Pilih</s:option>
                      <s:options-collection collection="${list.senaraiKodPBT}" label="nama" value="kod" />
                  </s:select>
              </p>
                    --%>

                    <br>
                </fieldset>
            </div>
            <br>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Rezab & Warta</legend>
                    <p>
                        <label>Lot Bumiputera : </label>
                        <c:if test="${disabledbtn eq 'disabled'}">
                            <s:hidden name="hakmilik.lotBumiputera" value="${actionBean.hakmilik.lotBumiputera}" />
                        </c:if>
                        <s:select style="text-transform:uppercase" name="hakmilik.lotBumiputera" value="${actionBean.hakmilik.lotBumiputera}" disabled="${disabledbtn}">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="Y">Ya</s:option>
                            <s:option value="T">Tidak</s:option>
                        </s:select>
                    </p>
                    <p>
                        <label>Tanah Rezab :</label>
                        <c:if test="${disabledbtn eq 'disabled'}">
                            <s:hidden name="hakmilik.rizab.kod" value="${actionBean.hakmilik.rizab.kod}"/>
                        </c:if>
                        <s:select  style="text-transform:uppercase;width:200pt" name="hakmilik.rizab.kod" id="kodRizab" onchange="kiraCukai(this.form,'${actionBean.hakmilik.idHakmilik}');" disabled="${disabledbtn}">
                            <s:option value="">Tidak</s:option>
                            <s:options-collection collection="${list.senaraiKodRizab}" label="nama" value="kod" />
                        </s:select>
                    </p>
                    <div id="tanahRezab">
                        <p>
                            <label>Tarikh Warta Rezab :</label>
                            <c:if test="${disabledbtn eq 'disabled'}">
                                <s:hidden name="trhWartaRezab" value="${actionBean.trhWartaRezab}"/>
                            </c:if>
                            <s:text name="trhWartaRezab" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" maxlength="10" size="40" disabled="${disabledbtn}" />
                        </p>
                        <p>
                            <label>No Warta Rezab :</label>
                            <c:if test="${disabledbtn eq 'disabled'}">
                                <s:hidden name="hakmilik.rizabNoWarta" value="${actionBean.hakmilik.rizabNoWarta}"/>
                            </c:if>
                            <s:text name="hakmilik.rizabNoWarta"  onkeyup="this.value = this.value.toUpperCase()" disabled="${disabledbtn}" size="40"/>
                        </p>
                        <p>
                            <label>Kawasan Rezab :</label>
                            <c:if test="${disabledbtn eq 'disabled'}">
                                <s:hidden name="hakmilik.rizabKawasan" value="${actionBean.hakmilik.rizabKawasan}"/>
                            </c:if>
                            <s:text name="hakmilik.rizabKawasan" onkeyup="this.value = this.value.toUpperCase()" disabled="${disabledbtn}" size="40"/>
                        </p>
                    </div>
                    <div id="tanahPBT">
                        <p>
                            <label>Pihak Berkuasa Tempatan :</label>
                            <c:if test="${disabledbtn eq 'disabled'}">
                                <s:hidden name="hakmilik.pbt.kod" value="${actionBean.hakmilik.pbt.kod}"/>
                            </c:if>
                            <s:select style="text-transform:uppercase;width:200pt" name="hakmilik.pbt.kod" disabled="${disabledbtn}">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraiKodPBT}" label="nama" value="kod" />
                            </s:select>
                        </p>
                        <p>
                            <label>Tarikh Warta PBT :</label>
                            <c:if test="${disabledbtn eq 'disabled'}">
                                <s:hidden name="trhWartaPbt" value="${actionBean.trhWartaPbt}"/>
                            </c:if>
                            <s:text name="trhWartaPbt" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" maxlength="10" size="40" disabled="${disabledbtn}"/>
                        </p>
                        <p>
                            <label>No Warta PBT :</label>
                            <c:if test="${disabledbtn eq 'disabled'}">
                                <s:hidden name="hakmilik.pbtNoWarta" value="${actionBean.hakmilik.pbtNoWarta}"/>
                            </c:if>
                            <s:text name="hakmilik.pbtNoWarta" onkeyup="this.value = this.value.toUpperCase()" disabled="${disabledbtn}" size="40"/>
                        </p>
                        <p>
                            <label>Kawasan PBT :</label>
                            <c:if test="${disabledbtn eq 'disabled'}">
                                <s:hidden name="hakmilik.pbtKawasan" value="${actionBean.hakmilik.pbtKawasan}"/>
                            </c:if>
                            <s:text name="hakmilik.pbtKawasan" onkeyup="this.value = this.value.toUpperCase()" disabled="${disabledbtn}" size="40"/>
                        </p>
                    </div>
                    <div id="tanahGSA">
                        <p>
                            <label>Tarikh Warta GSA :</label>
                            <c:if test="${disabledbtn eq 'disabled'}">
                                <s:hidden name="trhWartaGsa" value="${actionBean.trhWartaGsa}"/>
                            </c:if>
                            <s:text name="trhWartaGsa" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" maxlength="10" size="40" disabled="${disabledbtn}"/>
                        </p>
                        <p>
                            <label>No Warta GSA :</label>
                            <c:if test="${disabledbtn eq 'disabled'}">
                                <s:hidden name="hakmilik.gsaNoWarta" value="${actionBean.hakmilik.gsaNoWarta}"/>
                            </c:if>
                            <s:text name="hakmilik.gsaNoWarta" onkeyup="this.value = this.value.toUpperCase()" disabled="${disabledbtn}" size="40"/>
                        </p>
                        <p><label>Kawasan GSA :</label>
                            <c:if test="${disabledbtn eq 'disabled'}">
                                <s:hidden name="hakmilik.gsaKawasan" value="${actionBean.hakmilik.gsaKawasan}"/>
                            </c:if>
                            <s:text name="hakmilik.gsaKawasan" onkeyup="this.value = this.value.toUpperCase()" disabled="${disabledbtn}" size="40"/>
                        </p>
                    </div>
                </c:if>
                <c:if test="${actionBean.p.kodUrusan.kod eq 'HT' || actionBean.p.kodUrusan.kod eq 'HTSC'
                              || actionBean.p.kodUrusan.kod eq 'HTSPB' || actionBean.p.kodUrusan.kod eq 'HTSPS'
                              || actionBean.p.kodUrusan.kod eq 'HTSPV'}">
                      <div class="subtitle">
                          <fieldset class="aras1">
                              <legend>Maklumat Strata</legend>
                          </fieldset>
                          <p><label>No Bangunan :</label>
                              <s:text name="hakmilik.noBangunan"/>
                          </p>
                          <p><label>No Tingkat :</label>
                              <s:text name="hakmilik.noTingkat"/>
                          </p>
                          <p><label>No Petak :</label>
                              <s:text name="hakmilik.noPetak"/>
                          </p>
                          <p><label>Cukai Tanah di kosong (RM) :</label>
                              <s:text name="hakmilik.cukai"/>
                          </p>
                          <p><label>Pajakan Selama :</label>
                              <s:text name="hakmilik.tempohPegangan"/>
                          </p>
                          <p><label>Berakhir pada :</label>
                              <s:text name="hakmilik.tarikhLuput"/>
                          </p>
                          <p>
                              <label>Daerah :</label>
                              <c:if test="${disabled eq 'disabled'}" >
                                  <s:hidden name="hakmilik.daerah.kod" id="kodDaerah" value="${actionBean.hakmilik.daerah.kod}"/>
                              </c:if>
                              <s:hidden name="hakmilik.daerah.kod" id="kodDaerah" value="${actionBean.hakmilik.daerah.kod}"/>
                              <s:select  disabled="${disabled}" name="hakmilik.daerah.kod" id="namaDaerah" onchange="filterKodBPM(this.value);">
                                  <s:options-collection collection="${actionBean.senaraiKodDaerah}" label="nama" value="kod"/>
                              </s:select>
                          </p>
                          <s:hidden name="hakmilik.bandarPekanMukim.bandarPekanMukim" id="kodBPM"/>
                          <div id="partialKodBPM"></div>
                          <p>
                              <label>Jenis Hakmilik :</label>
                              <c:if test="${disabled eq 'disabled'}" >
                                  <s:hidden name="hakmilik.kodHakmilik.kod" id="kodHakmilik" value="${actionBean.hakmilik.kodHakmilik.kod}"/>
                              </c:if>
                              <s:hidden name="hakmilik.kodHakmilik.kod" id="kodHakmilik" value="${actionBean.hakmilik.kodHakmilik.kod}"/>
                              <s:select  disabled="${disabled}" name="hakmilik.kodHakmilik.kod">
                                  <s:options-collection collection="${actionBean.senaraiKodHakmilik}" label="nama" value="kod"/>
                              </s:select>
                          </p>
                          <p><label>Kategori Tanah : </label>
                              <s:select disabled="${disabled}" style="text-transform:uppercase" name="hakmilik.kategoriTanah.kod" id="katTanah" value="${actionBean.hakmilik.kategoriTanah.kod}" onchange="filterKodGunaTanah();filterKodUOM();kiraCukai(this.form,'${actionBean.hakmilik.idHakmilik}');" >
                                  <s:options-collection collection="${list.senaraiKodKategoriTanah}" label="nama" value="kod" />
                              </s:select>
                          </p>
                          <p><label>Kod Lot / No Lot : </label>
                              <s:select  style="text-transform:uppercase" name="hakmilik.lot.kod" id="jenisLot" value="${actionBean.hakmilik.lot.kod}">
                                  <s:option value="">Sila Pilih</s:option>
                                  <s:options-collection collection="${list.senaraiKodLot}" label="nama" value="kod" />
                              </s:select> /
                              <s:text  name="hakmilik.noLot" id="noLot" size="7" maxlength="7" value="${actionBean.hakmilik.noLot}" onblur="checkNoLot(this.value);checkPelan(this.value);"/>
                          </p>
                          <center><div id="checkLotMessages" style="font-size: 12px;display: none;margin-top: 10px;margin-bottom: 10px;width:50%;"></div>
                              <div id="checkPelanMessages" style="font-size: 12px;display: none;margin-top: 10px;margin-bottom: 10px;width:50%;"></div></center>
                          <p><label>Petak Aksesori :</label>
                              <s:text name="petakAksesori"/>
                          </p>
                          <p><label>Unit Syer bagi Petak :</label>
                              <s:text name="hakmilik.unitSyer"/>
                          </p>
                          <p>
                              <label>Syarat Nyata :</label>
                              <s:textarea name="syaratnyata" rows="5" style="width:40%;" readonly="true" id="syaratnyatadetails">Tertakluk Kepada Syarat Dalam Hakmilik ${actionBean.hakmilik.kodHakmilik.kod} ${actionBean.hakmilik.idHakmilikInduk}</s:textarea>
                              </p>
                              <p>
                                  <label>Sekatan Kepentingan :</label>
                              <s:textarea name="sekatan" rows="5" style="width:40%;" readonly="true" id="sekatandetails" >Tertakluk Kepada Sekatan Dalam Hakmilik ${actionBean.hakmilik.kodHakmilik.kod} ${actionBean.hakmilik.idHakmilikInduk}</s:textarea>
                              </p>
                              <p>
                                  <label>No Pelan Diperakui :</label>
                              <s:text name="hakmilik.noPelan" value="${actionBean.hakmilik.noPelan}" size="25"/>
                          </p>
                          <p><label>Jumlah Unit Syer bagi semua bangunan yang dipecah bahagikan atas tanah :</label>
                              <s:text name="hakmilik.jumlahUnitSyer"/>
                          </p>
                          <br>
                      </div>
                </c:if>
                <%-- UNTUK NOTA SAHAJA--%>
                <%-- <p>
                     <label>Lot Orang Melayu :</label>
                     <s:select name="" id="rezabMelayubtn">
                         <s:option value="">Sila Pilih</s:option>
                         <s:option value="Y">Y</s:option>
                         <s:option value="T">T</s:option>
                     </s:select>
                 </p>--%>
                <br>
                <p>
                    <label>&nbsp;</label>
                    <s:hidden name="hakmilik.seksyen.kod" id="kodSeksyen"/>
                    <s:button name="simpan" id="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
                    <s:button name="simpanKelompok" id="simpanKelompok" value="Simpan Berkelompok" class="longbtn"  onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
                <br>
            </fieldset>
        </div>
        <%-- <div id="rezabMelayubox">
             <div class="subtitle">
                 <fieldset class="aras1">
                     <legend>Nota Tanah Rezab Melayu</legend>

                <p><label>Nota Perserahan : </label>
                    < database >
                </p>
                <p><label>Jenis Rezab / Urusan : </label>
                    <s:select name="hakmilik.rizab.kod">
                        <s:options-collection collection="${list.senaraiKodRizab}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p><label>Nota Warta : </label>
                    <s:text name="" />
                </p>
                <p><label>Tarikh Warta : </label>
                    <s:text name="" />
                </p>
                <p><label>Kawasan : </label>
                    <s:text name="" />
                </p>

            </fieldset>
        </div>
    </div>--%>
        <%--<c:if test="${actionBean.p.kodUrusan.kod ne 'HSBM' && actionBean.p.kodUrusan.kod ne 'HKBM' && actionBean.p.kodUrusan.kod ne 'HSPB' && actionBean.p.kodUrusan.kod ne 'HKPB' }">--%>
        <c:if test="${actionBean.p.kodUrusan.kod ne 'HSBM'
                      && actionBean.p.kodUrusan.kod ne 'BMSTM'
                      && actionBean.p.kodUrusan.kod ne 'HKBM' 
                      && actionBean.p.kodUrusan.kod ne 'HT' 
                      && actionBean.p.kodUrusan.kod ne 'HKTHK' 
                      && actionBean.p.kodUrusan.kod ne 'HT' 
                      && actionBean.p.kodUrusan.kod ne 'HSTHK'}">

              <div class="subtitle">
                  <fieldset class="aras1">
                      <legend>Hakmilik Urusan</legend>
                      <%-- <p>
                           <label>&nbsp;</label>
                           <c:if test="${fn:length(actionBean.senaraiHakmilikUrusan) > 0}">
                               <s:button class="btn" value="Hapus" name="" onclick="removeMultipleUrusan();"/>&nbsp;
                           </c:if>
                       </p>--%>
                      <div class="content" align="center">
                          <display:table class="tablecloth" style="width:90%;" name="${actionBean.senaraiHakmilikUrusan}" cellpadding="0" cellspacing="0" id="linehakmilikurusan">
                              <display:column title="<input type='checkbox' name='semua' onclick='javascript:selectAllUrusan(this);'>">
                                  <s:checkbox name="checkboxurusan" id="checkboxurusan${linehakmilikurusan_rowNum-1}" value="${linehakmilikurusan.idUrusan}" class="remove2"/>
                              </display:column>
                              <display:column title="Bil" sortable="true">${linehakmilikurusan_rowNum}</display:column>
                              <%--<display:column property="idHakmilikAsalPermohonan" title="ID Hakmilik Asal"/>--%>
                              <display:column property="kodUrusan.kod"  title="Kod Urusan"/>
                              <%--<display:column title="ID Perserahan">
                                  <a href="javascript:void(0);" onclick="popupSenaraiPihak('${linehakmilikurusan.idPerserahan}');">${linehakmilikurusan.idPerserahan}</a>
                              </display:column>--%>
                              <%--<display:column title="ID Hakmilik" property="hakmilik.idHakmilik"/>--%>
                              <display:column title="ID Perserahan">
                                  ${linehakmilikurusan.idPerserahan}
                              </display:column>
                              <%--<display:column property="noFail" title="No Rujukan Fail"/>--%>
                              <display:column property="kodUrusan.nama"  title="Nama"/>
                              <display:column title="Status">
                                  <c:if test="${fn:contains(linehakmilikurusan.aktif,'Y')}">
                                      Daftar
                                  </c:if>
                                  <c:if test="${fn:contains(linehakmilikurusan.aktif,'T')}">
                                      Tidak Berkuatkuasa
                                  </c:if>
                              </display:column>

                              <display:column property="tarikhDaftar" title="Tarikh Mula" format="{0,date,dd/MM/yyyy hh:mm:ss}"/>
                              <%--<display:column property="hakmilik.idHakmilik"  title="ID Hakmilik Lama"/>--%>
                              <%-- <display:column property="hakmilikAsal.tarikhDaftar" title="Tarikh mula diberimilik" format="{0,date,dd/MM/yyyy}"/>--%>
                              <c:if test="${actionBean.p.kodUrusan.kod eq 'HKGHS'}">
                                  <c:if test="${disabledbtn ne 'disabled'}">
                                      <display:column title="Hapus">
                                          <div align="center">
                                              <img alt='Klik Untuk Padam' border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'
                                                   id='rem_${linehakmilikasal_rowNum}' onclick="removeHakmilikUrusan('${linehakmilikurusan.idUrusan}');" style="cursor:hand">
                                          </div>
                                      </display:column>
                                  </c:if>
                              </c:if>
                          </display:table>
                      </div>

                      <p>
                          <label>&nbsp;</label>
                          <c:if test="${fn:length(actionBean.senaraiHakmilikUrusan) > 0}">
                              <s:button class="btn" value="Hapus" name="" onclick="removeMultipleUrusan();"/>&nbsp;
                          </c:if>
                          <%--<c:if test="${fn:length(actionBean.senaraiHakmilikUrusan) < 1}">--%>
                          <s:button class="btn" value="Reset" name="" onclick="resetHakmilikUrusan();"/>&nbsp;
                          <%-- </c:if>--%>
                      </p>
                      <br>
                  </fieldset>
              </div>
              <br>
              <div class="subtitle">
                  <fieldset class="aras1">
                      <legend>Hakmilik Urusan Tertinggal</legend>
                      <div class="content" align="center">
                          <display:table class="tablecloth" style="width:90%;" name="${actionBean.hakmilikUrusanHmLama}" cellpadding="0" cellspacing="0" id="linehakmilikurusan">
                              <display:column title="<input type='checkbox' name='semua' onclick='javascript:selectAllUrusan1(this);'>">
                                  <s:checkbox name="checkboxurusan1" id="checkboxurusan1${linehakmilikurusan_rowNum-1}" value="${linehakmilikurusan.idUrusan}" class="remove2"/>
                              </display:column>
                              <display:column title="Bil" sortable="true">${linehakmilikurusan_rowNum}</display:column>
                              <%--<display:column property="idHakmilikAsalPermohonan" title="ID Hakmilik Asal"/>--%>
                              <display:column property="kodUrusan.kod"  title="Kod Urusan"/>
                              <display:column title="ID Perserahan">
                                  ${linehakmilikurusan.idPerserahan}
                              </display:column>
                              <display:column property="kodUrusan.nama"  title="Nama"/>
                              <display:column title="Status">
                                  <c:if test="${fn:contains(linehakmilikurusan.aktif,'Y')}">
                                      Daftar
                                  </c:if>
                                  <c:if test="${fn:contains(linehakmilikurusan.aktif,'T')}">
                                      Tidak Berkuatkuasa
                                  </c:if>
                              </display:column>
                              <display:column property="tarikhDaftar" title="Tarikh Mula" format="{0,date,dd/MM/yyyy hh:mm:ss}"/>
                          </display:table>
                      </div>
                      <p>
                          <label>&nbsp;</label>
                          <c:if test="${fn:length(actionBean.hakmilikUrusanHmLama) > 0}">
                              <s:button class="btn" value="Simpan" name="" onclick="saveMultipleUrusan();"/>&nbsp;
                          </c:if>
                      </p>
                      <br>
                  </fieldset>
              </div>

              <div class="subtitle">
                  <fieldset class="aras1">
                      <legend>Pihak Berkepentingan Tertinggal</legend>
                      <div class="content" align="center">
                          <display:table class="tablecloth" style="width:90%;" name="${actionBean.senaraiHakmilikPihak}" cellpadding="0" cellspacing="0" id="linehakmilikurusan">
                              <display:column title="<input type='checkbox' name='semua' onclick='javascript:selectAllHakmilikPihak(this);'>">
                                  <s:checkbox name="checkboxhakmilikpihak" id="checkboxhakmilikpihak${linehakmilikurusan_rowNum-1}" value="${linehakmilikurusan.idHakmilikPihakBerkepentingan}" class="remove2"/>
                              </display:column>
                              <display:column title="Bil" sortable="true">${linehakmilikurusan_rowNum}</display:column>
                              <%--<display:column property="idHakmilikAsalPermohonan" title="ID Hakmilik Asal"/>--%>
                              <display:column property="nama" title="Nama"/>
                              <display:column property="noPengenalan" title="Nombor Pengenalan" />
                              <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                              <display:column   title="Jenis Pihak" >
                                  <c:if test="${linehakmilikurusan.jenis.kod eq 'PA'}">
                                      <a href="#" onclick="viewPihakWaris(${linehakmilikurusan.idHakmilikPihakBerkepentingan});
                                              return false;">${linehakmilikurusan.jenis.nama}</a>
                                  </c:if>    
                                  <c:if test="${linehakmilikurusan.jenis.kod ne 'PA'}">
                                      ${linehakmilikurusan.jenis.nama}
                                  </c:if>   
                              </display:column>
                              <display:column title="Tarikh Pemilikan Tanah">
                                  <fmt:formatDate pattern="dd/MM/yyyy" value="${linehakmilikurusan.infoAudit.tarikhMasuk}"/>
                              </display:column>
                          </display:table>
                          <c:if test="${fn:length(actionBean.senaraiHakmilikPihak) > 0}">
                              <s:button class="btn" value="Simpan" name="" onclick="saveMultipleHakmilikPihakTertinggal();"/>&nbsp;
                          </c:if>
                      </div>
                      <br>
                  </fieldset>
              </div>
              <div class="subtitle">
                  <fieldset class="aras1">
                      <legend>Senarai Permohonan Pihak</legend>
                      <div class="content" align="center" id="listpihak">
                          <display:table class="tablecloth" style="width:90%;" name="${actionBean.senaraiPermohonanPihak}" cellpadding="0" cellspacing="0" id="mohonPihak">
                              <display:column title="<input type='checkbox' name='semua' onclick='javascript:selectAllPihak(this);'>">
                                  <s:checkbox name="checkboxpihak" id="checkboxpihak${mohonPihak_rowNum-1}" value="${mohonPihak.idPermohonanPihak}" class="remove2"/>
                              </display:column>
                              <display:column title="Bil" sortable="true">${mohonPihak_rowNum}</display:column>
                              <display:column property="nama" title="Nama" style="text-transform:uppercase;"/>
                              <display:column property="noPengenalan" title="Nombor Pengenalan" />
                              <display:column property="permohonan.idPermohonan" title="ID Perserahan" />
                              <display:column property="jenis.nama" title="Jenis PB" />                      
                              <display:column title="Hapus">                
                                  <div align="center">
                                      <img alt='Klik Untuk Padam' border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'
                                           id='rem_${linepihak_rowNum}' onclick="removeMohonPihak('${mohonPihak.idPermohonanPihak}')" style="cursor:hand">
                                  </div>
                              </display:column>
                              <display:column title="ID Pihak" property="pihak.idPihak" class="hidden" headerClass="hidden" media="html"/>
                          </display:table>
                  </fieldset>
              </div>
              <div class="subtitle">
                  <fieldset class="aras1">
                      <legend>Senarai Pemohon</legend>
                      <div class="content" align="center" id="listpihak">
                          <display:table class="tablecloth" style="width:90%;" name="${actionBean.senaraiPemohon}" cellpadding="0" cellspacing="0" id="pemohon">
                              <display:column title="<input type='checkbox' name='semua' onclick='javascript:selectAllPihak(this);'>">
                                  <s:checkbox name="checkboxpihak" id="checkboxpihak${pemohon_rowNum-1}" value="${pemohon.idPemohon}" class="remove2"/>
                              </display:column>
                              <display:column title="Bil" sortable="true">${pemohon_rowNum}</display:column>
                              <display:column property="nama" title="Nama" style="text-transform:uppercase;"/>
                              <display:column property="noPengenalan" title="Nombor Pengenalan" />
                              <display:column property="permohonan.idPermohonan" title="ID Perserahan" />
                              <display:column property="jenis.nama" title="Jenis PB" />                      
                              <display:column title="Hapus">                
                                  <div align="center">
                                      <img alt='Klik Untuk Padam' border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'
                                           id='rem_${linepihak_rowNum}' onclick="removePemohon('${pemohon.idPemohon}')" style="cursor:hand">
                                  </div>
                              </display:column>
                              <display:column title="ID Pihak" property="pihak.idPihak" class="hidden" headerClass="hidden" media="html"/>
                          </display:table>
                      </div>
                  </fieldset>
              </div>


              <div class="subtitle">        
                  <fieldset class="aras1">
                      <legend>Hakmilik Asal</legend>
                      <div class="content" align="center">       
                          <display:table name="${actionBean.listHakmilikAsalPermohonan}" 
                                         class="tablecloth" style="width:30%;" 
                                         cellpadding="0" cellspacing="0" id="linehakmilikasal">
                              <display:column title="Bil" sortable="true">${linehakmilikasal_rowNum}</display:column>
                              <%--<display:column property="idHakmilikAsalPermohonan" title="ID Hakmilik Asal"/>--%>
                              <display:column title="ID Hakmilik Lama">
                                  <a href="javascript:void(0);" onclick="popupHakmilikDetails('${linehakmilikasal.hakmilikSejarah}');">
                                      ${linehakmilikasal.hakmilikSejarah}
                                  </a>
                              </display:column>
                              <%--<display:column property="hakmilik.idHakmilik"  title="ID Hakmilik Lama"/>--%>
                              <%-- <display:column property="hakmilikAsal.tarikhDaftar" title="Tarikh mula diberimilik" format="{0,date,dd/MM/yyyy}"/>--%>
                              <display:column title="Hapus">
                                  <div align="center">
                                      <img alt='Klik Untuk Padam' border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'
                                           id='rem_${linehakmilikasal_rowNum}' onclick="removeHakmilikAsal('${linehakmilikasal.idHakmilikAsalPermohonan}');" style="cursor:hand">
                                  </div>
                              </display:column>
                          </display:table>              
                      </div>
                  </fieldset>        
              </div>
              <br>
              <div class="subtitle">
                  <fieldset class="aras1">
                      <legend>Hakmilik Sebelum</legend>
                      <%-- <p>
                           <label>&nbsp;</label>
                           <s:button name="popuphakmiliksebelum" id="popuphakmiliksebelum" value="Tambah" class="btn" onclick="popupHakmilikSblm('${actionBean.hakmilik.idHakmilik}');"/>&nbsp;
                       </p>--%>
                      <div class="content" align="center">
                          <%--<display:table class="tablecloth" style="width:90%;" name="${actionBean.listHakmilikSebelum}" cellpadding="0" cellspacing="0" id="linehakmiliksebelum">--%>
                          <display:table name="${actionBean.listHakmilikSblmPermohonan}"
                                         class="tablecloth" style="width:30%;"  cellpadding="0" 
                                         cellspacing="0" id="linehakmiliksebelum">
                              <display:column title="Bil" sortable="true">${linehakmiliksebelum_rowNum}</display:column>
                              <%--<display:column property="idHakmilikSebelumPermohonan" title="ID Hakmilik Sebelum"/>--%>
                              <%--<display:column property="hakmilik.idHakmilik"  title="ID Hakmilik Sebelum Kini"/>--%>
                              <display:column title="ID Hakmilik Sebelum Kini">
                                  <a href="javascript:void(0);" onclick="popupHakmilikDetails('${linehakmiliksebelum.hakmilikSejarah}');">
                                      ${linehakmiliksebelum.hakmilikSejarah}
                                  </a>
                              </display:column>
                              <c:if test="${disabledbtn ne 'disabled'}">
                                  <display:column title="Hapus">
                                      <div align="center">
                                          <img alt='Klik Untuk Padam' border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'
                                               id='rem_${linehakmiliksebelum_rowNum}' onclick="removeHakmilikSblm('${linehakmiliksebelum.idHakmilikSebelumPermohonan}');" style="cursor:hand">
                                      </div>
                                  </display:column>
                              </c:if>
                          </display:table>
                      </div>
                  </fieldset>
              </div>
        </c:if>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Senarai Pihak Berkepentingan</legend>
                <div class="content" align="center" id="listpihak">
                    <display:table class="tablecloth" style="width:90%;" name="${actionBean.pihakKepentinganList}" cellpadding="0" cellspacing="0" id="linemohonpihak">
                        <display:column title="<input type='checkbox' name='semua' onclick='javascript:selectAllPihakBaru(this);'>">
                            <s:checkbox name="checkboxpihak1" id="checkboxpihak1${linemohonpihak_rowNum-1}" value="${linemohonpihak.idHakmilikPihakBerkepentingan}" class="checkboxpihak1"/>
                        </display:column>
                        <display:column title="Bil" sortable="true">${linemohonpihak_rowNum}</display:column>
                        <display:column property="nama" title="Nama" style="text-transform:uppercase;"/>
                        <display:column property="noPengenalan" title="Nombor Pengenalan" />
                        <display:column property="jenis.nama" title="Jenis PB" />
                        <display:column title="Waris">
                            <c:if test="${linemohonpihak.jenis.kod eq 'PP' ||linemohonpihak.jenis.kod eq 'PA' ||linemohonpihak.jenis.kod eq 'PK'}">
                                <div align="center">
                                    <img alt='Klik Untuk Tambah Waris' border='0' src='${pageContext.request.contextPath}/pub/images/tambah.png' class='rem'
                                         id='rem_${linemohonpihak_rowNum}' onclick="cariWaris('${linemohonpihak.idHakmilikPihakBerkepentingan}')" style="cursor:hand">
                                </div>
                            </c:if>
                        </display:column>                  
                        <display:column title="Bahagian yang diterima">
                            <div align="center">
                                <s:text name="syerPembilang[${linemohonpihak_rowNum-1}]" size="5" id="syer1${linemohonpihak_rowNum-1}"
                                        onblur="updateSyer('${linemohonpihak.idHakmilikPihakBerkepentingan}', '${linemohonpihak_rowNum-1}')"
                                        onchange="updateSyer('${linemohonpihak.idHakmilikPihakBerkepentingan}', '${linemohonpihak_rowNum-1}" class="pembilang"/> /
                                <s:text name="syerPenyebut[${linemohonpihak_rowNum-1}]" size="5" id="syer2${linemohonpihak_rowNum-1}"
                                        onblur="updateSyer('${linemohonpihak.idHakmilikPihakBerkepentingan}', '${linemohonpihak_rowNum-1}')"
                                        onchange="updateSyer('${linemohonpihak.idHakmilikPihakBerkepentingan}', '${linemohonpihak_rowNum-1}" class="penyebut"/>
                            </div>
                        </display:column>
                        <c:if test="${disabledbtn ne 'disabled'}">
                            <display:column title="Kemaskini">
                                <div align="center">
                                    <a href="#" onclick="popupEditPihak('${linemohonpihak.pihak.idPihak}', '${actionBean.hakmilik.idHakmilik}', '${linemohonpihak.idHakmilikPihakBerkepentingan}')">Kemaskini</a>
                                </div>
                            </display:column>
                            <display:column title="Hapus">                
                                <div align="center">
                                    <img alt='Klik Untuk Padam' border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'
                                         id='rem_${linepihak_rowNum}' onclick="removePihakBerkepentingan('${linemohonpihak.idHakmilikPihakBerkepentingan}')" style="cursor:hand">
                                </div>
                            </display:column>
                        </c:if>
                        <display:column title="ID Pihak" property="pihak.idPihak" class="hidden" headerClass="hidden" media="html"/>
                        <%--<s:hidden name="idpihak${line_rowNum}" class="idpihak${line_rowNum}" value="${actionBean.idPihak}" />--%>
                    </display:table>
                </div>

                <div align="center">
                    <s:button name="btnpopupPihak"  id="btnpopupPihak" disabled="${disabledbtn}"  value="Tambah" class="btn" onclick="popupPihak('${actionBean.hakmilik.idHakmilik}');" />
                    <c:if test="${fn:length(actionBean.pihakKepentinganList) > 0}">
                        <s:button class="btn" value="Hapus" name="" onclick="removeMultipleHakmilikPihak();" disabled="${disabledbtn}"/>&nbsp;
                        <s:button name="semak" disabled="${disabledbtn}" id="semak" value="Semak Syer" class="longbtn" onclick="semakSyer(this.form,'${actionBean.hakmilik.idHakmilik}');"/>
                        <%--<s:button class="longbtn" value="Agih Sama Rata" name="agihSamaRata" id="agihSamaRata" onclick="samaRata(this.form);"/>&nbsp;--%>
                        <s:button disabled="${disabledbtn}" class="longbtn" value="Agih Sama Rata" name="agihSamaRata" id="agihSamaRata" onclick="samaRata(this.form);"/>&nbsp;
                        <s:button disabled="${disabledbtn}" class="longbtn" value="Simpan Berkelompok" name="simpanPihakKelompok" onclick="simpanPK('${actionBean.hakmilik.idHakmilik}');"/>
                    </c:if>
                    <c:if test="${actionBean.p.kodUrusan.kod ne 'HSBM' && actionBean.p.kodUrusan.kod ne 'BMSTM' && actionBean.p.kodUrusan.kod ne 'HKBM' && actionBean.p.kodUrusan.kod ne 'HT'}">
                        <c:if test="${fn:length(actionBean.pihakKepentinganList) < 1 && fn:length(actionBean.pihakKepentinganSelainPMList) < 1 }">
                            <s:button class="btn" value="Reset" name="" onclick="resetHakmilikPihak();"/>&nbsp;
                        </c:if>
                    </c:if>
                </div>
            </fieldset>
        </div>
        <%--<div class="subtitle">
          <fieldset class="aras1">
            <legend>Senarai Pihak Berkepentingan Selain Pemilik</legend>
            <div class="content" align="center" id="listpihak">
              <p>
                <label>&nbsp;</label>

            <c:if test="${fn:length(actionBean.pihakKepentinganSelainPMList) > 0}">
              <s:button class="btn" value="Hapus" name="" onclick="removeMultipleHakmilikPihak();" disabled="${disabledbtn}"/>&nbsp;
            </c:if>
          </p>
          <display:table class="tablecloth" style="width:90%;" name="${actionBean.pihakKepentinganSelainPMList}" cellpadding="0" cellspacing="0" id="linepihak">
            <display:column title="<input type='checkbox' name='semua' onclick='javascript:selectAll(this);'>">
              <s:checkbox name="checkbox" id="checkbox${linepihak_rowNum}" value="${linepihak.idHakmilikPihakBerkepentingan}" class="remove2"/>
            </display:column>
            <display:column title="Bil" sortable="true">${linepihak_rowNum}</display:column>
            <display:column property="pihak.nama" title="Nama"/>
            <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
            <display:column property="jenis.nama" title="Jenis PB" />
        <%--<display:column title="Bahagian yang diterima">
           ${linepihak.syerPembilang}
            /
            ${linepihak.syerPenyebut}
        </display:column>
        <display:column title="Hapus">
          <div align="center">
            <img alt='Klik Untuk Padam' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                 id='rem_${linepihak_rowNum}' onclick="removePihakBerkepentingan('${linepihak.idHakmilikPihakBerkepentingan}')" style="cursor:hand">
          </div>
        </display:column>
        <%--<display:column title="ID Pihak" property="pihak.idPihak" class="hidden" headerClass="hidden" media="html"/>
        <s:hidden name="idpihak${line_rowNum}" class="idpihak${line_rowNum}" value="${actionBean.idPihak}" />
      </display:table>
    </div>
    </fieldset>
    </div>--%>
    </s:form>
</body>
