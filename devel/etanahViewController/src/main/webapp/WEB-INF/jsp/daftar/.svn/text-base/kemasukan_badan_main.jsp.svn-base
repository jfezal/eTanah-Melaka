
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
        $(document).ready(function () {
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
                    .mouseover(function () {
                        $(this).data("origWidth", $(this).css("width")).css("width", "auto");
                    })
                    .mouseout(function () {
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
            $("#luas").keyup(function () {
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
                    function (data) {
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
                        function (data) {
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

        function removeMultipleUrusan() {
            if (confirm('Adakah anda pasti untuk hapus Urusan ini')) {
                var id = $('#hiddenIdHakmilik').val();
                var param = '';
                $('.remove2').each(function (index) {
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
                        function (data) {
                            $('#page_div').html(data);
                        }, 'html');
            }
        }
        function saveMultipleUrusan() {
            if (confirm('Adakah anda pasti untuk tambah Urusan ini')) {
                var id = $('#hiddenIdHakmilik').val();
                var param = '';
                $('.remove2').each(function (index) {
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
                        function (data) {
                            $('#page_div').html(data);
                        }, 'html');
            }
        }
        function saveMultipleHakmilikPihakTertinggal() {
            if (confirm('Adakah anda pasti untuk tambah Pihak ini')) {
                var id = $('#hiddenIdHakmilik').val();
                var param = '';
                $('.remove2').each(function (index) {
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
                        function (data) {
                            $('#page_div').html(data);
                        }, 'html');
            }
        }
        function removeMultipleHakmilikPihak() {
            if (confirm('Adakah anda pasti untuk hapus Pihak ini')) {
                var id = $('#hiddenIdHakmilik').val();
                var param = '';
                $('.remove2').each(function (index) {
                    var a = $('#checkboxpihak' + index).is(":checked");
                    if (a) {
                        param = param + '&idHakmilikPihak=' + $('#checkboxpihak' + index).val();
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
                        function (data) {
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
//            var kodSeksyen = '${actionBean.hakmilik.seksyen.seksyen}'
            alert(kodSeksyen);
            if (kodSeksyen == "") {
                kodSeksyen = "000";
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
                    function (data) {
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
                    function (data) {
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
                    function (data) {
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
                    function (data) {
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
                    function (data) {
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
                    function (data) {
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
                    function (data) {
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
                    function (data) {
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
                    function (data) {
                        if (data == null || data.length == 0) {
                            alert('Terdapat Masalah');
                            return;
                        }
                        var p = data.split(DELIM);
                        $('.pembilang').each(function () {
                            $(this).val(p[0]);
                        });
                        $('.penyebut').each(function () {
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
                        function (data) {
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
                        function (data) {
                            $('#page_div').html(data);
                        });
            }
        }

        function removePihakBerkepentingan(val) {
        <%-- alert(val);--%>
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
                        function (data) {
                            $('#page_div').html(data);
                            $.unblockUI();
                        });
            }
        }
        function removeHakmilikUrusan(val) {
            var id = $('#hiddenIdHakmilik').val();
            var answer = confirm("adakah anda pasti untuk Hapus11?");
            if (answer) {
                var url = '${pageContext.request.contextPath}/pendaftaran/KemasukanSubMC?deleteHakmilikUrusan&idUrusan=' + val + '&idHakmilik=' + id;
                $.blockUI({
                    message: $('#displayBox'),
                    css: {
                        top: ($(window).height() - 50) / 2 + 'px',
                        left: ($(window).width() - 50) / 2 + 'px',
                        width: '50px'
                    }
                });
                $.get(url,
                        function (data) {
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
                        function (data) {
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
                        function (data) {
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
                    function (data) {
                        if (data != '') {
                            alert(data);
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
                    function (data) {
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
                    function (data) {
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
                    function (data) {
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
//        ===================================*****===========================================
        function popupPihak(id) {
            var url = "${pageContext.request.contextPath}/pendaftaran/KemasukanSubMC?kemasukanSubMCBaru&idHakmilik=" + id;
            window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=800");
            //maximizeWindow();
        }

    </script>

    <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
    <s:form beanclass="etanah.view.stripes.KemasukanSubMC" id="kemasukanPerincianHakmilik">
        <s:messages/>
        <s:errors/>
        <%--                            hidden default value                                                --%>
        <%--<s:hidden name="hakmilik.cawangan.kod" value="${actionBean.hakmilik.cawangan.kod}"/>--%>
        <%-- <s:hidden name="hakmilik.daerah.kod" id="kodDaerah" value="${actionBean.hakmilik.daerah.kod}"/>--%>
        <%--<s:hidden name="kodNegeri" id="kodNegeri" value="${actionBean.kodNegeri}"/>--%>
        <%-- <s:hidden name="hakmilik.kategoriTanah.kod" value="${actionBean.hakmilik.kategoriTanah.kod}"/>--%>
        <%--<s:hidden name="hakmilik.milikPersekutuan" value="${actionBean.hakmilik.milikPersekutuan}"/>--%>
        <%--<s:hidden name="hakmilik.tarikhDaftar" value="${actionBean.hakmilik.tarikhDaftar}"/>--%>
        <%--<s:hidden name="hakmilik.kodHakmilik.kod" value="${actionBean.hakmilik.kodHakmilik.kod}"/>--%>
        <%--<s:hidden name="hakmilik.rizab.kod" value="${actionBean.hakmilik.rizab.kod}"/>--%>
        <%--<s:hidden name="hakmilik.pegangan" value="${actionBean.hakmilik.pegangan}"/>--%>
        <%--<s:hidden name="hakmilik.tempohPegangan" value="${actionBean.hakmilik.tempohPegangan}"/>--%>
        <%--<s:hidden name="hakmilik.tarikhLuput" value="${actionBean.hakmilik.tarikhLuput}"/>--%>
        <%--<s:hidden name="hakmilik.noPu" value="${actionBean.hakmilik.noPu}" />--%>
        <%--<s:hidden name="hakmilik.syaratNyata.kod" value="${actionBean.hakmilik.syaratNyata.kod}" />--%>
        <%--<s:hidden name="hakmilik.sekatanKepentingan.kod" value="${actionBean.hakmilik.sekatanKepentingan.kod}" />--%>
        <%--<s:hidden name="hakmilik.kodStatusHakmilik.kod" value="${actionBean.hakmilik.kodStatusHakmilik.kod}" />--%>
        <%--<s:hidden name="hakmilik.pbt.kod" value="${actionBean.hakmilik.pbt.kod}" />--%>
        <%--<s:hidden name="hakmilik.noHakmilik" value="${actionBean.hakmilik.noHakmilik}" />--%>
        <%--                          end of  hidden default value                                           --%>
        <%--<s:hidden name="hakmilik.idHakmilik" id="hiddenIdHakmilik" value="${actionBean.hakmilik.idHakmilik}"/>--%>
        <!--<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>-->


        <%--<c:if test="${actionBean.bdnUrusMain ne null}">--%>
        <fieldset class="aras1">
            <legend>Badan Pengurusan</legend>
            <%--${disablebtn}--%>
            <p><label>ID Hakmilik :</label>
                ${actionBean.hakmilik.idHakmilik}
                &nbsp;
            </p>
            <c:if test="${actionBean.bdnUrusMain.pihak.nama ne null}">                    
                <p><label>Nama :</label>
                    ${actionBean.bdnUrusMain.pihak.nama}
                    &nbsp;
                </p></c:if>

            <c:if test="${actionBean.bdnUrusMain.pihak.noPengenalan ne null}">   
                <p><label>No Pengenalan / Syarikat :</label>
                    ${actionBean.bdnUrusMain.pihak.noPengenalan}
                    &nbsp;
                </p></c:if>

            <c:if test="${actionBean.bdnUrusMain.pihak.alamat1 ne null}">
                <p><label>Alamat :</label>
                    ${actionBean.bdnUrusMain.pihak.alamat1}
                    &nbsp;
                </c:if>
            </p>
            <c:if test="${actionBean.bdnUrusMain.pihak.alamat2 ne null}">
                <p><label>&nbsp :</label>
                    ${actionBean.bdnUrusMain.pihak.alamat2}
                    &nbsp;
                </c:if>
            </p>

            <c:if test="${actionBean.bdnUrusMain.pihak.alamat3 ne null}">
                <p><label>&nbsp :</label>
                    ${actionBean.bdnUrusMain.pihak.alamat3}
                    &nbsp;
                </c:if>
            </p>

            <c:if test="${actionBean.bdnUrusMain.pihak.alamat4 ne null}">
                <p><label>&nbsp :</label>
                    ${actionBean.bdnUrusMain.pihak.alamat4}
                    &nbsp;
                </c:if>
            </p>

            <c:if test="${actionBean.bdnUrusMain.pihak.poskod ne null}"><p><label>Poskod :</label>
                    ${actionBean.bdnUrusMain.pihak.poskod}
                    &nbsp;
                </p>
            </c:if>

            <c:if test="${actionBean.bdnUrusMain.pihak.negeri ne null}"><p><label>Negeri :</label>
                    ${actionBean.bdnUrusMain.pihak.negeri.nama}
                    &nbsp;
                </p>
            </c:if>
        </fieldset>
        <%--</c:if>--%>
        <c:if test="${fn:length(actionBean.senaraiBdnUrusSUB) > 0}">
            <div class="content" align="center">
                <display:table class="tablecloth" style="width:90%;" name="${actionBean.senaraiBdnUrusSUB}" cellpadding="0" cellspacing="0" id="bdnUrusSUb">
                    <display:column title="<input type='checkbox' name='semua' onclick='javascript:selectAllUrusan(this);'>">
                        <s:checkbox name="checkboxurusan" id="checkboxurusan${bdnUrusSUb_rowNum-1}" value="${bdnUrusSUb.idWakil}" class="remove2"/>
                    </display:column>
                    <display:column title="Bil" sortable="true">${bdnUrusSUb_rowNum}</display:column>
                    <display:column property="pihak.nama"  title="Pengurusan Main"/>
                    <display:column property="nama"  title="Pengurusan Sub"/>
                    <display:column property="alamat1"  title="alamat1"/>
                    <display:column property="alamat2"  title="alamat2"/>
                    <display:column property="alamat3"  title="alamat3"/>
                    <display:column property="alamat4"  title="alamat4"/>
                    <display:column property="poskod"  title="poskod"/>
                    <display:column property="negeri.nama"  title="Hakmilik"/>
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Padam' border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'
                                 id='rem_${linehakmilikasal_rowNum}' onclick="removeHakmilikUrusan('${bdnUrusSUb.idWakil}');" style="cursor:hand">
                        </div>
                    </display:column>
                </display:table>
            </div>
        </c:if>
        <center>
            <s:button name="btnpopupPihak"  id="btnpopupPihak" disabled="${disabledbtn}"  value="Tambah" class="btn" onclick="popupPihak('${actionBean.hakmilik.idHakmilik}');" />

        </center>

        <c:if test="${actionBean.p.kodUrusan.kod ne 'HT'}">

        </c:if>

    </s:form>
</body>