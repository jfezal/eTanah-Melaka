<%-- 
    Document   : tukarganti_hm
    Created on : Jul 13, 2015, 11:09:05 AM
    Author     : haqqiem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="etanah.model.Pengguna"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Penyerahan Hakmilik</title>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
        <!--        <script type="text/javascript"
                src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>-->
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
        <link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
        <script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
        <c:set value="${actionBean.kodUrusan}" var="kodUrusan"/>
        <%
            Pengguna p = (Pengguna) request.getSession().getAttribute("_KEY_USER");
            //String kodDaerah = p.getKodCawangan().getDaerah().getKod();
        %>
        <script type="text/javascript">

            function save(event, f) {
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;

                $.post(url, q,
                        function(data) {
                            $('#page_div', opener.document).html(data);
                            self.close();
                        }, 'html');
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

            $(document).ready(function() {
                var kodUrusan = '${actionBean.kodUrusan}';
                var hm = document.getElementById('hakmilik0');
                if ((kodUrusan == 'HSSTB') || (kodUrusan == 'BETST') || ((hm.value).length > 0)) {
                    $("#Step4").attr("disabled", "");
                } else {
                    $("#Step4").attr("disabled", "true");
                }
                $('form').submit(function() {
                    $.blockUI({
                        message: $('#displayBox'),
                        css: {
                            top: ($(window).height() - 50) / 2 + 'px',
                            left: ($(window).width() - 50) / 2 + 'px',
                            width: '50px'
                        }
                    });
                });

                //$("#messages").html('<b>Sila masukkan maklumat berkaitan untuk menjana hakmilik baru</b>');
                //$("#messages").show();
                maximizeWindow();
                dialogInit('Carian Hakmilik');
            <%--<c:if test="${actionBean.urusan ne 'HKTHK' && actionBean.urusan ne 'HSTHK' }">--%>
            <c:forEach var="i" begin="1" end="${actionBean.bilHakmilik}" >
                $("#hakmilik${i - 1}").change(function() {
                    doBlockUI();
                    validateHakmilik(${i - 1});
                });
                $("#hakmilik${i - 1}").keyup(function() {
                    closeDialog();
                });
            </c:forEach>
            <%--</c:if>--%>
                $('input:text').each(function() {
                    $(this).focus(function() {
                        $(this).addClass('focus')
                    });
                    $(this).blur(function() {
                        $(this).removeClass('focus')
                    });
                });
                $("#lot").val('1');
                //$("#namaNegeri").val().toUpperCase();

                $("#namaDaerah").val($("#kodDaerah").val());

                $("#namaDaerah").change(function() {
                    var valueDaerah = $("#namaDaerah").val();
                    $("#kodDaerah").val(valueDaerah);
                });
                $("#kodDaerah").blur(function() {
                    var valueDaerah = $("#kodDaerah").val();
                    $("#namaDaerah").val(valueDaerah);
                });
                $("#kodBPM").blur(function() {
                    var valueBPM = $("#kodBPM").val();
                    $("#namaBPM").val(valueBPM);
                });
                $("#namaBPM").change(function() {
                    var valueBPM = $("#namaBPM").val();
                    $("#kodBPM").val(valueBPM);
                });
                $("#namaJenisHakmilik").change(function() {
                    var valueJenisHakmilik = $("#namaJenisHakmilik").val();
                    $("#kodJenisHakmilik").val(valueJenisHakmilik);
                });
                $("#kodJenisHakmilik").blur(function() {
                    var valueJenisHakmilik = $("#kodJenisHakmilik").val();
                    $("#namaJenisHakmilik").val(valueJenisHakmilik);
                });

                $("#idHakmilikAsal").blur(function() {
                    var kodUrusan = '${kodUrusan}';
                    if (kodUrusan == 'HKPS' || kodUrusan == 'HTSPS' || kodUrusan == 'HSPS' || kodUrusan == 'HKPB' || kodUrusan == 'HSPB'
                            || kodUrusan == 'HKBTK' || kodUrusan == 'HKPTK' || kodUrusan == 'HSBTK' || kodUrusan == 'HSPTK') {
                        //alert($(this).val());
                        checkHakmilik($(this).val());
                    } else {
                        checkHakmilik($(this).val());
                    }
                });

            });
        </script>
        <script type="text/javascript">
            function validateHakmilik(idxHakmilik) {
                var val = $("#hakmilik" + idxHakmilik).val();
                frm = this.form;
                if (val == null || val == "")
                    return;
                val = val.toUpperCase();
                $("#hakmilik" + idxHakmilik).val(val);

            <c:forEach var="i" begin="1" end="${actionBean.bilHakmilik}" >
                if ($("#hakmilik${i - 1}").val() == val && (idxHakmilik !=${i-1})) {
                    alert('Hakmilik telah dipilih.');
                    $("#hakmilik" + idxHakmilik).val('');
                    return false;
                }
            </c:forEach>
            checkingId(idxHakmilik);

                //jika kod betst, N8AB, takyah check status batal
            <c:if test="${!empty batal}">
                $.unblockUI();
                return;</c:if>
                var kodUrusan = '${actionBean.kodUrusan}';
                $.get("${pageContext.request.contextPath}/daftar/check_idhakmilik?doCheckHakmilik&idHakmilik=" + val + "&kodUrusan=" + kodUrusan,
                        function(data) {
                            if (data == '1') {
                                //$("#msg" + idxHakmilik).html("OK");
                                $("#Step4").attr("disabled", "");
                                $("#Step3b").attr("disabled", "");
                                $.unblockUI();
                            } else if (data == '0') {
                                // disable if invalid Hakmilik
                                // $("#collectPayment").attr("disabled", "true");
                                $("#hakmilik" + idxHakmilik).val("");
                                alert("ID Hakmilik " + val + " tidak wujud!");
                                // $("#msg" + idxHakmilik).html("<font color=red>ID Hakmilik " + val + " tidak wujud!</font>");
                                $("#Step4").attr("disabled", "true");
                                $("#Step3b").attr("disabled", "true");
                                $.unblockUI();
                            } else if (data == '2') {
                                // disable if invalid Hakmilik
                                // $("#collectPayment").attr("disabled", "true");
                                //$("#hakmilik" + idxHakmilik).val("");
                                //                            $(".errors").show();
                                //                            $(".errors").html('Cukai untuk Hakmilik ' + val + ' masih belum dijelaskan!');
                                //                            alert("Cukai untuk Hakmilik " + val + " masih belum dijelaskan!");
                                //                            $("#Step4").attr("disabled", "true");
                                //                            $("#Step3b").attr("disabled", "true");
                                //                            $("#Step4").hide();
                                //                            $("#Step3b").hide();
                                //                            $("#hakmilik" + idxHakmilik).val("");
                                $("#Step4").attr("disabled", "");
                                $("#Step3b").attr("disabled", "");
                                $.unblockUI();
                            } else if (data == '3') {
            <c:if test="${actionBean.kodUrusan ne 'HMVB' && actionBean.kodUrusan ne 'HMV'}">
                                $("#hakmilik" + idxHakmilik).val("");
                                alert("Status hakmilik Provisional. Tiada Urusniaga / Kaveat Persendirian / Lien dibenarkan.");
            </c:if>
                                $("#Step4").attr("disabled", "true");
                                $("#Step3b").attr("disabled", "true");
                                $.unblockUI();
                            } else if (data.charAt(0) == '4') {
                                $("#hakmilik" + idxHakmilik).val("");
                                var str = "Hakmilik " + val + " telah dibatalkan.";
                                if (data.substring(2).length > 0)
                                    str += " Hakmilik sambungan ialah " + data.substring(2) + ".";
                                alert(str);
                                $("#Step4").attr("disabled", "");
                                $("#Step3b").attr("disabled", "");
                                $.unblockUI();
                            } else if (data == '5') {
                                alert("Hakmilik tersebut mempunyai sekatan. Sila klik butang papar untuk melihat sekatan kepentingan terbabit.");
                                $("#msg" + idxHakmilik).html("Cukai Hakmilik telah dijelaskan. (" +
                                        "<a href=\"javascript:void(0);\" onclick=\"popupHakmilikDetails(" + idxHakmilik + ");\">Papar</a>)");
                                $("#Step4").attr("disabled", "");
                                $("#Step3b").attr("disabled", "");
                                $.unblockUI();
                            } else if (data == '6') {
                                alert("Cukai untuk Hakmilik " + val + " masih belum dijelaskan! Hakmilik tersebut juga mempunyai sekatan sila klik butang papar untuk melihat sekatan kepentingan terbabit.");
                                $("#msg" + idxHakmilik).html("Cukai untuk Hakmilik " + val + " masih belum dijelaskan! (" +
                                        "<a href=\"javascript:void(0);\" onclick=\"popupHakmilikDetails(" + idxHakmilik + ");\">Papar</a>)");
                                $("#Step4").attr("disabled", "");
                                $("#Step3b").attr("disabled", "");
                                $.unblockUI();
                            } else if (data == '7') {
                                alert("Hakmilik tersebut mempunyai perintah mahkamah / perintah larangan !!");
                                $("#Step4").attr("disabled", "");
                                $("#Step3b").attr("disabled", "");
                                $.unblockUI();
                            } else if (data == '8') {
                                $("#msg" + idxHakmilik).html("Pastikan pemilik beragama Islam (" +
                                        "<a href=\"javascript:void(0);\" onclick=\"popupHakmilikDetails(" + idxHakmilik + ");\">Papar</a>)");
                                alert(data);
                                $("#Step4").attr("disabled", "");
                                $("#Step3b").attr("disabled", "");
                                $.unblockUI();
                            } else if (data == '9') {
                                $("#msg" + idxHakmilik).html("Pastikan pemilik bukan beragama Islam (" +
                                        "<a href=\"javascript:void(0);\" onclick=\"popupHakmilikDetails(" + idxHakmilik + ");\">Papar</a>)");
                                alert(data);
                                $("#Step4").attr("disabled", "");
                                $("#Step3b").attr("disabled", "");
                                $.unblockUI();
                            } else if (data == '10') {
                                alert('KVSPC masih berkuatkuasa.');
                                $("#hakmilik" + idxHakmilik).val("");
                                $("#Step4").attr("disabled", "");
                                $("#Step3b").attr("disabled", "");
                                $.unblockUI();
                            } else if (data === '11') {
                                $("#hakmilik" + idxHakmilik).val("");
                                alert('Pastikan versi hakmilik adalah 0.');
                                $("#Step4").attr("disabled", "true");
                                $("#Step3b").attr("disabled", "true");
                                $.unblockUI();
                            } else if (data === '12') {
                                //                      $("#hakmilik" + idxHakmilik).val("");
                                //                      alert('Versi hakmilik adalah 0. Sila lakukan Urusan Tukar Ganti terlebih dahulu.');
                                //                      $("#Step4").attr("disabled", "true");
                                //                      $("#Step3b").attr("disabled", "true");
                                $("#Step4").attr("disabled", "");
                                $("#Step3b").attr("disabled", "");
                                $("#Step4").attr("disabled", "");
                                $("#Step3b").attr("disabled", "");
                                $.unblockUI();
                            } else if (data.substring(0, 2) === '13') {
                                $("#hakmilik" + idxHakmilik).val("");
                                //                      alert('Perhatian: Hakmilik sudah mempunyai urusan Tukarganti yang masih aktif.');
                                alert(data.substring(2));
                                $("#Step4").attr("disabled", "true");
                                $("#Step3b").attr("disabled", "true");
                                $.unblockUI();
                            } else if (data.substring(0, 2) === '14') {
                                $("#hakmilik" + idxHakmilik).val("");
                                //                      alert('Perhatian: Hakmilik sudah mempunyai urusan Tukarganti yang masih aktif.');
                                alert(data.substring(2));
                                $("#Step4").attr("disabled", "true");
                                $("#Step3b").attr("disabled", "true");
                                $.unblockUI();
                            } else if (data.substring(0, 2) === '15') {
                                //                      $("#hakmilik" + idxHakmilik).val("");
                                //                      alert('Perhatian: Hakmilik sudah mempunyai urusan Tukarganti yang masih aktif.');
                                alert(data.substring(2));
                                $("#Step4").attr("disabled", "");
                                $("#Step3b").attr("disabled", "");
                                $.unblockUI();
                            } else if (data.substring(0, 2) === '19') {
                                //                      $("#hakmilik" + idxHakmilik).val("");
                                //                      alert('Perhatian: Hakmilik sudah mempunyai urusan Tukarganti yang masih aktif.');
                                alert(data.substring(2));
                                $("#Step4").attr("disabled", "");
                                $("#Step3b").attr("disabled", "");
                                $.unblockUI();
                            } else if (data.substring(0, 2) === '17') {
                                //                      $("#hakmilik" + idxHakmilik).val("");
                                //                      alert('Perhatian: Hakmilik sudah mempunyai urusan Tukarganti yang masih aktif.');
                                alert(data.substring(2));
                                $("#Step4").attr("disabled", "");
                                $("#Step3b").attr("disabled", "");
                                $.unblockUI();
                            } else if (data.substring(0, 2) === '18') {
                                alert(data.substring(2));
                                $("#Step4").attr("disabled", "");
                                $("#Step3b").attr("disabled", "");
                                $.unblockUI();
                            } else {
                                alert(data);
                                $.unblockUI();
                            }
                        });
            }

            function checkHakmilikProsesSelainMH(idxHakmilik) {
                var val = $("#hakmilik" + idxHakmilik).val();
                frm = this.form;
                if (val == null || val == "")
                    return;
                val = val.toUpperCase();

                var kodUrusan = '${actionBean.kodUrusan}';
                //alert(kodUrusan);
                //$("#hakmilik" + idxHakmilik).val(val);
                $.get("${pageContext.request.contextPath}/daftar/check_idhakmilik?checkHakmilikSelainMH&idHakmilik=" + val + "&kodUrusan=" + kodUrusan,
                        function(data) {
                            //alert(data);
            <%--if(data == '0'){
                            
                $("#Step4").attr("disabled", "");
            } else if(data =='1'){
                            
                alert("ID Hakmilik " + val + " masih mempunyai urusan lain yang belum selesai");
                           
                $("#Step4").attr("disabled", "true");
            }else{
                alert(data);
            }--%>
                            if (data != '') {
                                $("#Step4").attr("disabled", "true");
                                $("#Step3b").attr("disabled", "true");
                                alert(data);
                            } else {
                                $("#Step4").attr("disabled", "");
                                $("#Step3b").attr("disabled", "");
                            }

                        });
            }
            function checkHakmilikProses(idxHakmilik) {
                var val = $("#hakmilik" + idxHakmilik).val();
                frm = this.form;
                if (val == null || val == "")
                    return;
                val = val.toUpperCase();

                var kodUrusan = '${actionBean.kodUrusan}';
                //alert(kodUrusan);
                //$("#hakmilik" + idxHakmilik).val(val);
                $.get("${pageContext.request.contextPath}/daftar/check_idhakmilik?checkHakmilikDalamProses&idHakmilik=" + val + "&kodUrusan=" + kodUrusan,
                        function(data) {
                            //alert(data);
                            if (data == '0') {
                                //$("#msg" + idxHakmilik).html("OK");
                                //$("#hakmilik" + idxHakmilik).val("");
                                //alert("ID Hakmilik " + val + " tidak mempunyai notting berkaitan untuk meneruskan urusan "+kodUrusan);
                                $("#Step4").attr("disabled", "");
                                $("#Step3b").attr("disabled", "");
                            } else if (data == '1') {
                                // disable if invalid Hakmilik
                                // $("#collectPayment").attr("disabled", "true");
                                alert("ID Hakmilik " + val + " masih mempunyai urusan MH yang belum selesai");
                                // $("#msg" + idxHakmilik).html("<font color=red>ID Hakmilik " + val + " tidak wujud!</font>");
                                $("#Step4").attr("disabled", "true");
                                $("#Step3b").attr("disabled", "true");
                            } else if (data == '8') {
                                $("#msg" + idxHakmilik).html("Pastikan pemilik beragama Islam");
                                alert(data);
                                $("#Step4").attr("disabled", "");
                                $("#Step3b").attr("disabled", "");
                            } else if (data == '9') {
                                $("#msg" + idxHakmilik).html("Pastikan pemilik bukan beragama Islam");
                                alert(data);
                                $("#Step4").attr("disabled", "");
                                $("#Step3b").attr("disabled", "");
                            } else {
                                alert(data);
                            }
                        });
            }
            function checkNottingMH(idxHakmilik) {
                //alert("checknotting");
                var val = $("#hakmilik" + idxHakmilik).val();
                frm = this.form;
                if (val == null || val == "")
                    return;
                val = val.toUpperCase();

                var kodUrusan = '${actionBean.kodUrusan}';
                //alert(kodUrusan);
                //$("#hakmilik" + idxHakmilik).val(val);
                $.get("${pageContext.request.contextPath}/daftar/check_idhakmilik?checkNottingMH&idHakmilik=" + val + "&kodUrusan=" + kodUrusan,
                        function(data) {
                            //alert(data);
                            if (data == '0') {
                                //$("#msg" + idxHakmilik).html("OK");
                                //$("#hakmilik" + idxHakmilik).val("");
                                //alert("ID Hakmilik " + val + " tidak mempunyai notting berkaitan untuk meneruskan urusan "+kodUrusan);
                            } else if (data == '1') {
                                // disable if invalid Hakmilik
                                // $("#collectPayment").attr("disabled", "true");
                                alert("ID Hakmilik " + val + " tidak mempunyai notting berkaitan untuk meneruskan urusan " + kodUrusan);
                                // $("#msg" + idxHakmilik).html("<font color=red>ID Hakmilik " + val + " tidak wujud!</font>");
                            } else {
                                alert(data);
                            }
                        });

            }
            function valisdateHakmilikBersiri(idx) {
                var dr = $("#idHakmilikSiriDari" + idx).val().toUpperCase();
                var ke = $("#idHakmilikSiriKe" + idx).val().toUpperCase();
                var val1 = $("#idHakmilikSiriDari" + idx).val().toUpperCase();
                var val2 = $("#idHakmilikSiriKe" + idx).val().toUpperCase();
                $("#idHakmilikSiriDari" + idx).val(val1);
                $("#idHakmilikSiriKe" + idx).val(val2);
                var kodSerah = '${actionBean.kodSerah}';
                var kodUrusan = '${actionBean.kodUrusan}';
                frm = this.form;
                if (dr == null || dr == "" || ke == null || ke == "")
                    return;
                $.get("${pageContext.request.contextPath}/daftar/check_siri_idhakmilik?doCheckHakmilik&" +
                        "idHakmilikDari=" + dr + "&idHakmilikKe=" + ke + "&kodSerah=" + kodSerah + "&kodUrusan=" + kodUrusan,
                        function(data) {
                            //                    alert(data);
                            if (data == '1') {
                                alert("ID Hakmilik Siri " + (idx + 1) + " adalah sah!");
                                $("#Step4").attr("disabled", "");
                                $("#Step3b").attr("disabled", "");
                            }
                            else if (data == '0') {
                                alert("Terdapat ID Hakmilik yang tidak wujud dalam Siri " + (idx + 1) + "!");
                                $("#Step4").attr("disabled", "true");
                                $("#Step3b").attr("disabled", "true");
                            }
                            else if (data == '2') {
                                alert("Terdapat cukai dalam Siri " + (idx + 1) + " yang masih belum dilunaskan!");
                                $("#Step4").attr("disabled", "true");
                                $("#Step3b").attr("disabled", "true");
                            } else if (data == "3") {
                                alert("ID Hakmilik Siri " + (idx + 1) + " tidak sah!");
                                $("#Step4").attr("disabled", "true");
                                $("#Step3b").attr("disabled", "true");
                            }
                            else if (data == "4") {
                                alert("ID Hakmilik Siri " + (idx + 1) + " masih mempunyai urusan yang belum selesai!");
                                $("#Step4").attr("disabled", "true");
                                $("#Step3b").attr("disabled", "true");
                            }
                            else if (data == "5") {
                                alert("ID Hakmilik Siri " + (idx + 1) + " masih mempunyai urusan MH yang belum selesai!");
                                $("#Step4").attr("disabled", "true");
                                $("#Step3b").attr("disabled", "true");
                            }
                            else if (data == "6") {
                                alert("ID Hakmilik Siri " + (idx + 1) + " tidak mempunyai notting yang berkaitan!");
                                $("#Step4").attr("disabled", "true");
                                $("#Step3b").attr("disabled", "true");
                            } else if (data === "11") {
                                alert("ID Hakmilik Siri " + (idx + 1) + " mempunyai versi hakmilik bukan 0.");
                                $("#Step4").attr("disabled", "true");
                                $("#Step3b").attr("disabled", "true");
                            }
            <%--if(data != ''){
                $("#Step4").attr("disabled", "true");
                $("#Step3b").attr("disabled", "true");
                alert(data);
            }else{
                $("#Step4").attr("disabled", "");
                $("#Step3b").attr("disabled", "");
            }--%>
                        });
            }

            function validate() {
                var bil = document.getElementById('bilHakmilik');
                if (bil.value <= 0) {
                    alert("Bilangan Hakmilik mestilah tidak kurang daripada 0");
                    return false;
                }
            }

            function nonNumber(elmnt, inputTxt) {
                var a = document.getElementById('bilHakmilik')

                if (isNaN(a.value)) {
                    alert("Nombor tidak sah.Sila masukkan Semula");
                    $("#bilHakmilik").focus();
                    elmnt.value = RemoveNonNumeric(inputTxt);
                    return;
                }
            }

            function RemoveNonNumeric(strString)
            {
                var strValidCharacters = "123456789.";
                var strReturn = "0";
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
            
            function checkingId(row){
                for(var x=0;x<${actionBean.bilHakmilik};x++){
                    var val = $("#hakmilik" + x).val();
                    var val1 = "";
                    if((x+1)<${actionBean.bilHakmilik}){
                        val1 = $("#hakmilik" + (x+1)).val();
                    }
                    if((val == "")&&(val1!="")){
                        alert("Sila Masukkan ID Hakmilik mengikut turutan yang betul.");
                        $("#hakmilik" + row).val("");
                        return false;
                    }
                }
                return true;
            }

            function appendAuto(val, id) {
                var bil = $('#bilHakmilikSiri' + id).val();
                var len = val.length;
                var intIndex = 0;
                var pre = "";

                if (val != '') {
                    val = val.toUpperCase();
                    $('#idHakmilikDari' + id).val(val);
                    bil = bil - 1;
                    if (parseInt(bil, 10) > 0) {
                        for (intIndex = len - 1; intIndex >= 0; intIndex--) {
                            var c = val.charAt(intIndex);
                            if (c >= '0' && c <= '9') {
                                pre = c + pre;
                            } else {
                                break;
                            }
                        }

                        var h = val.substring(0, intIndex + 1);//temp                
                        var val2 = parseInt(pre, 10) + parseInt(bil);
                        var len = (pre.length - String(val2).length);
                        if (String(val2).length < pre.length) {
                            for (var i = 0; i < len; i++) {
                                val2 = "0" + val2;
                            }
                        }

                        h = h + val2;
                        if (!isNaN(val2)) {
                            $('#idHakmilikHingga' + id).val(h);
                        }
                    }
                } else {
                    $('#idHakmilikHingga' + id).val('');
                }
            }

            function appendAutoAll(intIndex) {
                var val = $('#idHakmilikDari' + intIndex).val();
                if (val != '') {
                    appendAuto(val, intIndex);
                }
            }
            function popupHakmilikDetails(idx) {

                var idH = $('#hakmilik' + idx).val();
                var url = "${pageContext.request.contextPath}/hakmilik?popupHakmilikDetail&idHakmilik=" + idH;
                window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
            }
            
            function validateHakmilikBersiri(idx) {
                var dr = $("#idHakmilikDari" + idx).val().toUpperCase();
                var ke = $("#idHakmilikHingga" + idx).val().toUpperCase();
                var val1 = $("#idHakmilikDari" + idx).val().toUpperCase();
                var val2 = $("#idHakmilikHingga" + idx).val().toUpperCase();
                $("#idHakmilikDari" + idx).val(val1);
                $("#idHakmilikHingga" + idx).val(val2);
                var kodSerah = '${actionBean.kodSerah}';
                var kodUrusan = '${actionBean.kodUrusan}';
                frm = this.form;
                if (dr == null || dr == "" || ke == null || ke == "")
                  return;
                $.get("${pageContext.request.contextPath}/hasil/check_idhakmilik_accountNo?doCheckHakmilikSiri&" +
                        "idHakmilikDari=" + dr + "&idHakmilikKe=" + ke + "&kodSerah=" + kodSerah + "&kodUrusan=" + kodUrusan,
                        function(data) {
                          if (data == "X") {
                            alert("ID Hakmilik Bersiri tidak sah!");
                          }else if((data == "O")||(data == "0")) {
                            alert("Semua ID Hakmilik Bersiri yang dimasukkan tidak boleh ditukarganti!");
                          }else {
                              if (data.charAt(1) == ' '){
                                  alert("Terdapat "+data.substring(0,1)+" ID Hakmilik yang boleh ditukar ganti.");
                                  $('#txtSiri').val(data.substring(2));
                                  $('#Step4').removeAttr("disabled");
                              }
                              if (data.charAt(2) == ' '){
                                  alert("Terdapat "+data.substring(0,2)+" ID Hakmilik yang boleh ditukar ganti.");
                                  $('#txtSiri').val(data.substring(3));
                                  $('#Step4').removeAttr("disabled");
                              }
                          }
                        });
              }
        </script>
    </head>
    <body>
        <div align="center"><table style="width:100%" bgcolor="#00FFFF">
                <tr>
                    <td width="100%" height="20" >
                        <div style="background-color: 00FFFF;" align="center">
                            <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">PENDAFTARAN: Utiliti Tukar Ganti</font>
                        </div>
                    </td>
                </tr>
            </table></div>
        <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>



        <s:errors/>
        <s:messages/>
        <!--        <div class="messages" id="messages" style="display:none;"></div>-->
        <div class="errors" id="errors" style="display:none;"></div>
        <div class="subtitle">
            <s:useActionBean beanclass="etanah.view.stripes.KemasukanPerincianHakmilikActionBean" var="list"/>
            <s:form action="/daftar/utiliti_tukarganti" id="kemasukanPerincianHakmilik">
                <s:hidden name="${actionBean.ku}"/>
                <s:hidden name="${actionBean.kodUrusan}"/>

                <fieldset class="aras1">
                    <p class="title">Urusan : ${actionBean.ku.nama}</p>

                    <p class=title>Langkah 2: Tentukan Hakmilik-Hakmilik Terlibat</p>
                    <span style="font-weight:normal;color: black" class=instr>Masukkan semua ID Hakmilik yang terlibat. Biarkan kosong mana-mana medan
                        yang tidak berkaitan.</span>
                    <p>
                        <label for="bilHakmilik">Bil. Hakmilik:</label>
                        <s:text name="bilHakmilik" id="bilHakmilik" size="4" onblur="javascript:nonNumber(this, this.value);"/> atau kurang
                        <s:submit name="Step3" value="Kemaskini" class="btn" onclick="return validate()"/>
                    </p>
                    <div align="center">
                        <table border=0 class="tablecloth"><tr>
                                <c:forEach var="i" begin="1" end="${actionBean.bilHakmilik}" >
                                    <th align=right>
                                        ID Hakmilik ${i}:
                                    </th>
                                    <td align=left>
                                        <s:text class="hakmilik" name="hakmilikPermohonan[${i - 1}].hakmilik.idHakmilik" id="hakmilik${i - 1}" 
                                                onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/>&nbsp;
                                        <s:hidden name="hakmilikPermohonan[${i - 1}].hakmilik.noPetak" id="petak${i - 1}" />
                                        <div id="msg${i - 1}"/>
                                    </td>
                                    <c:if test="${i % 3 == 0}" >
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </table>
                    </div>
                    <p><label>&nbsp;</label>

                    </p>
                </fieldset>
                <fieldset class="aras1">
                    <legend>ID Hakmilik bersiri</legend>
                    <p align="center">
                        Bil. Hakmilik Bersiri 
                        <s:text name="bilHakmilikSiri" id="bilHakmilikSiri0" size="5" onchange="appendAutoAll('0');" maxlength="3"/>
                        ID Hakmilik dari
                        <s:text name="idHakmilikDari" id="idHakmilikDari0"
                                onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"
                                onkeyup="this.value=this.value.toUpperCase();"
                                onblur="appendAuto(this.value, '0');"/>
                        hingga <s:text name="idHakmilikHingga" id="idHakmilikHingga0" onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');" onkeyup="this.value=this.value.toUpperCase();" readonly="true"/>
                        <input type="button" value="Sahkan" onclick="validateHakmilikBersiri(0)" class="btn" />
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:textarea name="listHakmilikSiri" id="txtSiri" style="display:none"/>
                    </p>
                </fieldset>
                <div align="center"><table style="width:100%" bgcolor="#00FFFF">
                        <tr>
                            <td align="right">
                                <s:submit name="Step9" value="Batal" class="btn" />
                                <s:submit name="Step4" value="Seterusnya" class="btn" id="Step4" />
                            </td>
                        </tr>
                    </table></div>

            </s:form>
        </div>
    </body>
</html>