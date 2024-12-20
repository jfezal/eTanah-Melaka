<%-- 
    Document   : penyerahan_hakmilik_mh
    Created on : Sep 23, 2010, 9:41:37 AM
    Author     : khairil
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
        <c:set value="${actionBean.urusan}" var="kodUrusan"/>
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
            function filterKodBPM(f) {
                var kodDaerah = f
                //alert(kodDaerah);
                //var q = $(f).formSerialize();
                $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?senaraiKodBPMByDaerah&kodDaerah=' + kodDaerah + '&popup=true',
                        function(data) {
                            if (data != '') {
                                $('#partialKodBPM').html(data);
                            }
                        }, 'html');
            }

            function checkPelan(f) {
                //alert(f);
                var noLot = f
                var kodDaerah = $('#kodDaerah').val();
                var kodBPM = $('#kodBPM').val();
                var kodNegeri = $('#kodNegeri').val();
                //alert(kodDaerah);
                //var q = $(e).formSerialize();
                $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?checkPelan&noLot=' + noLot + '&kodDaerah=' + kodDaerah + '&kodNegeri=' + kodNegeri + '&kodBPM=' + kodBPM,
                        function(data) {
                            if (data != '1') {
                                //alert(data);
                                alert('Pelan untuk no lot ' + noLot + ' tiada');
                                $("#noLot").val("");
                                $("#noLot").focus();
                            }
                        }, 'html');
            }
            function checkHakmilik(f) {
                var idHakmilikAsal = f;
                if (f !== '') {

                    //alert("kodDaerah"+kodDaerah);
                    //alert("kodBPM"+kodBPM);
                    //alert("kodNegeri"+kodNegeri);
                    $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?checkHakmilik&idHakmilikAsal=' + idHakmilikAsal,
                            function(data) {
                                if (data != '1') {
                                    //alert('Hakmilik Asal '+ idHakmilikAsal +' tiada');
                                    $("#errors").html('<b>Hakmilik Asal ' + idHakmilikAsal + ' tiada</b>');
                                    //$("#errors").append();
                                    $("#errors").show();
                                    //$("#noLot").val("");
                                    //$("#noLot").focus();
                                    $("#janaBtn").attr("disabled", "true");
                                } else {
                                    $("#errors").hide();
                                    $("#messages").html('<b>Hakmilik Asal ' + idHakmilikAsal + ' wujud.Sila teruskan kemasukan.</b>');
                                    $("#messages").show();
                                    //alert($('#idHakmilikAsal').val().substr(4,2));
                                    $("#kodBPM").val($('#idHakmilikAsal').val().substr(4, 2));
                                    $("#namaBPM").val($('#idHakmilikAsal').val().substr(4, 2));
                                    $("#janaBtn").attr("disabled", "");

                                }
                            }, 'html');

                } else {
                    //$("#errors").hide();
                    $("#errors").html('<b>Sila masukkan hakmilik asal</b>');
                    $("#errors").show();
                    $("#janaBtn").attr("disabled", "true");
                }
            }

            function checkTotalPihak(f) {
                var idHakmilikAsal = f;
                //alert("kodDaerah"+kodDaerah);
                //alert("kodBPM"+kodBPM);
                //alert("kodNegeri"+kodNegeri);
                $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?checkTotalHakmilik&idHakmilikAsal=' + idHakmilikAsal,
                        function(data) {
                            //alert(data);
                            if (data != '0') {

                                //alert('Hakmilik Asal '+ idHakmilikAsal +' tiada');
                                //$("#noLot").val("");
                                //$("#noLot").focus();

                                $("#totalHakmilik").val(data);
                            } else {
                                //alert($('#idHakmilikAsal').val().substr(4,2));
                                //$("#kodBPM").val($('#idHakmilikAsal').val().substr(4,2));
                                //$("#namaBPM").val($('#idHakmilikAsal').val().substr(4,2));
                                $("#totalHakmilik").val("1");
                            }
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
                var kodUrusan = '${actionBean.urusan}';
                if ((kodUrusan == 'HSSTB') || (kodUrusan == 'BETST')) {
                    $("#Step4").attr("disabled", "");
                    $("#Step3b").attr("disabled", "");
                } else {
                    $("#Step4").attr("disabled", "true");
                    $("#Step3b").attr("disabled", "true");
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
                  filterKodBPM($('#kodDaerah').val());



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
                          checkTotalPihak($(this).val());
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
                    return;
                }
            </c:forEach>

                  //jika kod betst, N8AB, takyah check status batal
            <c:if test="${!empty batal}">
                  $.unblockUI();
                  return;</c:if>
                  var kodUrusan = '${actionBean.urusan}';
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
                                  alert("ID Hakmilik " + val + " tidak wujud!!!");
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
            <c:if test="${actionBean.urusan ne 'HMVB' && actionBean.urusan ne 'HMV'}">
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
            <c:if test="${actionBean.urusan ne 'HKABS' && 
                          actionBean.urusan ne 'HSABS'&& 
                          actionBean.urusan ne 'HKTHK'&& 
                          actionBean.urusan ne 'HSTHK'&& 
                          actionBean.urusan ne 'HKGHS'&& 
                          actionBean.urusan ne 'ECPI'&& 
                          actionBean.urusan ne 'HKSTK'&& 
                          actionBean.urusan ne 'HTIR'&& 
                          actionBean.urusan ne 'N6A'&&
                          actionBean.urusan ne 'N8A'&&
                          actionBean.urusan ne 'HMSC'&&
                          actionBean.urusan ne 'EUB'&&
                          actionBean.urusan ne 'SBTL'&&
                          actionBean.kodSerah ne 'NB'}">
                                  $("#Step4").attr("disabled", "true");
                                  $("#Step3b").attr("disabled", "true");
            </c:if>
            <c:if test="${actionBean.urusan eq 'HKABS' || 
                          actionBean.urusan eq 'HSABS'|| 
                          actionBean.urusan eq 'HKTHK'|| 
                          actionBean.urusan eq 'HSTHK'|| 
                          actionBean.urusan eq 'HKGHS'|| 
                          actionBean.urusan eq 'ECPI'|| 
                          actionBean.urusan eq 'HKSTK'|| 
                          actionBean.urusan eq 'HTIR'|| 
                          actionBean.urusan eq 'N6A'||
                          actionBean.urusan eq 'N8A'||
                          actionBean.urusan eq 'HMSC'||
                          actionBean.urusan eq 'EUB'||
                          actionBean.urusan eq 'SBTL'||
                          actionBean.kodSerah eq 'NB'}">
                                                  $("#Step4").attr("disabled", "");
                                                  $("#Step3b").attr("disabled", "");
            </c:if>    
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
                                              } else if (data.substring(0, 2) === '26') {
                                                  $("#hakmilik" + idxHakmilik).val("");
    //                      alert('Perhatian: Hakmilik sudah mempunyai urusan Tukarganti yang masih aktif.');
                                                  alert(data.substring(2));
                                                  $("#Step4").attr("disabled", "true");
                                                  $("#Step3b").attr("disabled", "true");
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

                                  $.get("${pageContext.request.contextPath}/daftar/check_kaveat?doCheckKaveat&idHakmilik=" + val,
                                          function(data) {
                                              if (data == '0') {
                                                  // nothing to do
                                              } else if (data == '1') {
                                                  alert("Hakmilik " + val + " mempunyai Kaveat!");
                                                  //for PAT Pembangunan  13/02/2013
                                                  $('#Step4').removeAttr("disabled");     //FIXME
                                              } else if (data == '2') {
                                                  alert("Hakmilik strata " + val + " mempunyai Kaveat pada Hakmilik induknya!");
                                                  //for PAT Pembangunan  13/02/2013
                                                  $('#Step4').removeAttr("disabled");
                                              } else if (data == '3') {
                                                  alert("Hakmilik " + val + " mempunyai Kaveat !");
                                                  //for PAT Pembangunan  13/02/2013
                                                  $('#Step4').removeAttr("disabled");
                                              } else if (data == '4') {
                                                  alert("Hakmilik " + val + " mempunyai Kaveat!.Hakmilik ini juga mempunyai Kaveat pada Hakmilik induknya!");
                                                  //for PAT Pembangunan  13/02/2013
                                                  $('#Step4').removeAttr("disabled");
                                              }
                                          });
                              }


                              function checkHakmilikProsesSelainMH(idxHakmilik) {
                                  var val = $("#hakmilik" + idxHakmilik).val();
                                  frm = this.form;
                                  if (val == null || val == "")
                                      return;
                                  val = val.toUpperCase();

                                  var kodUrusan = '${actionBean.urusan}';
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

                  var kodUrusan = '${actionBean.urusan}';
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

                  var kodUrusan = '${actionBean.urusan}';
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
              function validateHakmilikBersiri(idx) {
                  var dr = $("#idHakmilikSiriDari" + idx).val().toUpperCase();
                  var ke = $("#idHakmilikSiriKe" + idx).val().toUpperCase();
                  var val1 = $("#idHakmilikSiriDari" + idx).val().toUpperCase();
                  var val2 = $("#idHakmilikSiriKe" + idx).val().toUpperCase();
                  $("#idHakmilikSiriDari" + idx).val(val1);
                  $("#idHakmilikSiriKe" + idx).val(val2);
                  var kodSerah = '${actionBean.kodSerah}';
                  var kodUrusan = '${actionBean.urusan}';
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

              function appendAuto(val, id) {
                  var bil = $('#bilHakmilikBersiri' + id).val();
                  var len = val.length;
                  var intIndex = 0;
                  var pre = "";

                  if (val != '') {
                      val = val.toUpperCase();
                      $('#idHakmilikSiriDari' + id).val(val);
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
                              $('#idHakmilikSiriKe' + id).val(h);
                          }
                      }
                  } else {
                      $('#idHakmilikSiriKe' + id).val('');
                  }
              }

              function appendAutoAll(intIndex) {
                  var val = $('#idHakmilikSiriDari' + intIndex).val();
                  if (val != '') {
                      appendAuto(val, intIndex);
                  }
              }
              function popupHakmilikDetails(idx) {

                  var idH = $('#hakmilik' + idx).val();
                  var url = "${pageContext.request.contextPath}/hakmilik?popupHakmilikDetail&idHakmilik=" + idH;
                  window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
              }
        </script>
    </head>
    <body>
        <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>

        <p class="title">Urusan : ${actionBean.namaUrusan}</p>

        <p class=title>Langkah 3: Tentukan Hakmilik-Hakmilik Terlibat</p>
        <span class=instr>Masukkan semua ID Hakmilik yang terlibat. Biarkan kosong mana-mana medan
            yang tidak berkaitan. Anda boleh mencampurkan diantara ID Hakmilik yang bersiri dan tidak bersiri.</span><br/>
            
            <span class=instr>Untuk urusan Hakmilik Sementara Serahbalik Semua Tanah Dan Berimilik(HSSTB),Sila masukkan hakmilik cawangan sendiri terlebih dahulu sebagai rujukan.</span><br/>


        <s:errors/>
        <s:messages/>
        <!--        <div class="messages" id="messages" style="display:none;"></div>-->
        <div class="errors" id="errors" style="display:none;"></div>
        <div class="subtitle">
            <s:useActionBean beanclass="etanah.view.stripes.KemasukanPerincianHakmilikActionBean" var="list"/>
            <s:form action="/daftar/kaunter" id="kemasukanPerincianHakmilik">
                <%--kodUrusan != 'HSTK' && kodUrusan != 'HKTK' && kodUrusan != 'HKTKP' && kodUrusan != 'HSCTK' && kodUrusan != 'HKCTK'
              && kodUrusan != 'HSBTK' && kodUrusan != 'HKABT' && kodUrusan != 'HKBTK' && kodUrusan != 'HKSTK' && kodUrusan != 'HSPTK'--%>
                <%-- <c:if test="${actionBean.urusan ne 'HSTK' && actionBean.urusan ne 'HKTK' && actionBean.urusan ne 'HKTKP' &&
                               actionBean.urusan ne 'HSCTK' && actionBean.urusan ne 'HKCTK' && actionBean.urusan ne 'HSBTK' &&
                               actionBean.urusan ne 'HKABT' && actionBean.urusan ne 'HKBTK' && actionBean.urusan ne 'HKSTK' &&
                               actionBean.urusan ne 'HSPTK' && actionBean.urusan ne 'HKPTK'}">
                     <c:set var="disabled" value="disabled"/>
                 </c:if>--%>
                <%-- <c:if test="${actionBean.kodSerah eq 'MH' }">
                     <s:hidden name="idHakmilik" />
                     <fieldset class="aras1">
                         <legend>
                             Hakmilik Baru
                         </legend>--%>
                <%-- <p>
                     <label>No Perserahan :</label>
                     ${actionBean.p.idPermohonan}
                     &nbsp
                 </p>--%>
                <%-- <p>
                     <label>Urusan :</label>
                     ${actionBean.urusan} - ${actionBean.p.kodUrusan.nama}
                     &nbsp
                 </p>--%>
                <%--<p>
                    <label>Hakmilik Yang Dikeluarkan :</label>
                    <s:text name="totalHakmilik" id="totalHakmilik" size="4"/>
                </p>--%>
                <%--TODO: GET NAMA & KOD NEGERI FROM SESSION--%>
                <%--<c:if test="${disabled ne 'disabled'}">
                    <p>
                        <label>Negeri</label>
                        <s:text name="kodNegeri" id="kodNegeri" readonly="true" size="4"/> - <s:text name="namaNegeri" id="namaNegeri" readonly="true" style="text-transform: uppercase;"/>
                    </p>
                    <p>
                        <label>Daerah</label>
                        <s:text name="kodDaerah" size="4" id="kodDaerah" readonly="true" />
                        -
                        <s:select name="hakmilik.daerah.kod" id="namaDaerah" onchange="filterKodBPM(this.value);">
                             <s:option value="">-- Sila Pilih --</s:option>
                            <s:options-collection collection="${actionBean.senaraiKodDaerah}" label="nama" value="kod"/>
                        </s:select>
                                    </p>
                                    <div id="partialKodBPM">
                                    </div>
                                </c:if>
                                <p>
                                    <label>Jenis Hakmilik</label>
                                    <s:text name="kodJenisHakmilik" size="4" id="kodJenisHakmilik"/> -
                                    <s:select name="hakmilik.kodHakmilik.kod" id="namaJenisHakmilik">
                                        <s:option value="">-- Sila Pilih --</s:option>
                                        <s:options-collection collection="${actionBean.senaraiKodHakmilik}" label="nama" value="kod" />
                                    </s:select>
                                </p>--%>
                <%-- <p><label>Kod Lot / No Lot : </label>
                     <s:select name="hakmilik.lot.kod" id="lot" value="${actionBean.hakmilik.lot.kod}">
                         <s:option value="">Sila Pilih</s:option>
                         <s:options-collection collection="${list.senaraiKodLot}" label="nama" value="kod" />
                     </s:select> /
                     <s:text name="hakmilik.noLot" id="noLot" value="${actionBean.hakmilik.noLot}" onblur="checkPelan(this.value);"/>
                 </p>--%>
                <%--  </fieldset>
                        </div>              
                    </c:if>--%>

                <fieldset class="aras1">
                    <legend>ID Hakmilik tidak bersiri</legend>
                    <p><c:if test="${actionBean.urusan ne 'PCT'}">
                        <label for="bilHakmilik">Bil. Hakmilik:</label>
                        <s:text name="bilHakmilik" id="bilHakmilik" size="4" onblur="javascript:nonNumber(this, this.value);"/> atau kurang
                        
                            <s:submit name="Step3" value="Kemaskini" class="btn" onclick="return validate()"/> 
                        </c:if>
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
                </fieldset>

                <fieldset class="aras1">
                    <c:if test="${actionBean.urusan ne 'PCT'}"> 
                    <legend>ID Hakmilik bersiri</legend>
                    <p align="center">
                        Bil. Hakmilik Bersiri 1
                        <s:text name="bilHakmilikBersiri0" id="bilHakmilikBersiri0" size="5" onchange="appendAutoAll('0');" maxlength="3"/>
                        Siri 1: ID Hakmilik dari
                        <s:text name="idHakmilikSiriDari[0]" id="idHakmilikSiriDari0" 
                                onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"
                                onchange="appendAuto(this.value, '0');"/>
                        hingga <s:text name="idHakmilikSiriKe[0]" id="idHakmilikSiriKe0" onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/>
                        <input type="button" value="Sahkan" onclick="validateHakmilikBersiri(0)" class="btn" />

                    <p align="center">
                        Bil. Hakmilik Bersiri 2
                        <s:text name="bilHakmilikBersiri1" id="bilHakmilikBersiri1" size="5" onchange="appendAutoAll('1');" maxlength="3"/>
                        Siri 2: ID Hakmilik dari
                        <s:text name="idHakmilikSiriDari[1]" id="idHakmilikSiriDari1" onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"
                                onchange="appendAuto(this.value, '1');"/>
                        hingga <s:text name="idHakmilikSiriKe[1]" id="idHakmilikSiriKe1" onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/>
                        <input type="button" value="Sahkan" onclick="validateHakmilikBersiri(1)" class="btn" />
                    </p>
                    <p align="center">
                        Bil. Hakmilik Bersiri 3
                        <s:text name="bilHakmilikBersiri2" id="bilHakmilikBersiri2" size="5" onchange="appendAutoAll('2');" maxlength="3"/>
                        Siri 3: ID Hakmilik dari
                        <s:text name="idHakmilikSiriDari[2]" id="idHakmilikSiriDari2" onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"
                                onchange="appendAuto(this.value, '2');"/>
                        hingga <s:text name="idHakmilikSiriKe[2]" id="idHakmilikSiriKe2" onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/>
                        <input type="button" value="Sahkan" onclick="validateHakmilikBersiri(2)" class="btn" />
                    </p>
                    <p align="center">
                        Bil. Hakmilik Bersiri 4
                        <s:text name="bilHakmilikBersiri3" id="bilHakmilikBersiri3" size="5" onchange="appendAutoAll('3');" maxlength="3"/>
                        Siri 4: ID Hakmilik dari
                        <s:text name="idHakmilikSiriDari[3]" id="idHakmilikSiriDari3" onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"
                                onchange="appendAuto(this.value, '3');"/>
                        hingga <s:text name="idHakmilikSiriKe[3]" id="idHakmilikSiriKe3" onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/>
                        <input type="button" value="Sahkan" onclick="validateHakmilikBersiri(3)" class="btn" />
                    </p>
                    <p align="center">
                        Bil. Hakmilik Bersiri 5
                        <s:text name="bilHakmilikBersiri4" id="bilHakmilikBersiri4" size="5" onchange="appendAutoAll('4');" maxlength="3"/>
                        Siri 5: ID Hakmilik dari
                        <s:text name="idHakmilikSiriDari[4]" id="idHakmilikSiriDari4" onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"
                                onchange="appendAuto(this.value, '4');"/>
                        hingga <s:text name="idHakmilikSiriKe[4]" id="idHakmilikSiriKe4" onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/>
                        <input type="button" value="Sahkan" onclick="validateHakmilikBersiri(4)" class="btn" />
                    </p></c:if>

                    <p><label>&nbsp;</label>
                        <s:submit name="Step2" value="Kembali" class="btn" />
                        <s:submit name="Cancel" value="Batal" class="btn" />

                        <c:if test="${actionBean.kodSerah ne 'MH'}">
                            <s:submit name="Step4" value="Seterusnya" class="btn" id="Step4" />
                        </c:if>
                        <c:if test="${actionBean.kodSerah eq 'MH'}">
                            <s:submit name="Step3b" value="Seterusnya" class="btn" id="Step3b" />
                        </c:if>

                        <%-- <c:if test="${actionBean.kodSerah eq 'MH' && actionBean.urusan ne 'HKCSD' && actionBean.urusan ne 'HSCSD'  }">
                             <s:submit name="Step3b" value="Seterusnya" class="btn" id="Step3b" />
                         </c:if>
                         <c:if test="${actionBean.kodSerah eq 'MH' && actionBean.urusan eq 'HKCSD' || actionBean.urusan eq 'HSCSD'  }">
                             <s:submit name="Step6" value="Seterusnya" class="btn" id="Step6" />
                         </c:if>--%>
                    </p>
                </fieldset>


            </s:form>
        </div>
    </body>
</html>
