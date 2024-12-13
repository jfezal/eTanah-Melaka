<%-- 
    Document   : submitForm
    Created on : 16-Sep-2009, 15:40:56
    Author     : md.nurfikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>--%>
<script type="text/javascript">
    $(document).ready(function () {
        var fr = document.form1;
        $("#dialog").dialog("destroy");
    <c:if test="${viewOnly}">
        $('input:button').hide();
    </c:if>
        //$('#semakSemula').attr("disabled","true");
    <c:if test="${!actionBean.documentGenerated}">
        $('.hantar').each(function () {
            $(this).attr("disabled", "true");
        })
    </c:if>
    <c:if test="${actionBean.documentGenerated}">
        $('.hantar').each(function () {
            $(this).removeClass('disablebtn');
            $(this).addClass('btn');
        });
    </c:if>
    <c:if test="${!actionBean.selesaiBtn}">
        $('.hantar').each(function () {
            $(this).attr("disabled", "true");
        });
    </c:if>
    <c:if test="${actionBean.selesaiBtn}">
        $('.hantar').each(function () {
            $(this).removeAttr("disabled");
            $(this).removeClass('disablebtn');
            $(this).addClass('btn');
        });
    </c:if>

        $('#report').click(function () {
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });
            var url = '${pageContext.request.contextPath}/stage?genReport';
            $.ajax({
                type: "GET",
                url: url,
                dataType: 'html',
                error: function (xhr, ajaxOptions, thrownError) {
                    //alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                    $("#folder_div").html('<div class=errors>Terdapat Masalah Teknikal. Sila Hubungi Pentadbir Sistem.</div><div id=t><a href="#">[ Lihat Terperinci ]</a></div><br/><div id=error class=b>' + xhr.responseText + '</div>');
                    $.unblockUI();
                },
                success: function (data) {
                    if (data.indexOf('Tiada keputusan') == 0) {
                        alert(data);
                        $('#status').click();
                    } else if (data.indexOf('Dokumen telah dijana') == 0) {
                        alert(data);

                        $('.hantar').each(function () {
                            $(this).removeAttr('disabled');
                            $(this).removeAttr('disablebtn');
                            $(this).addClass('btn');
                        });

//                            $('.hantar').removeAttr('disabled');
//                            $('.hantar').removeClass('disablebtn');
//                            $('.hantar').addClass('btn');
                        $('#folder').click();
                    } else {
                        alert(data);
                        $('#urusan').click();
                    }
                    $.unblockUI();
                }
            });
        });

        $("#dialog-form").dialog({
            autoOpen: false,
            height: 250,
            width: 350,
            modal: true,
            buttons: {
                'Ok': function () {
                    if ($('#mesej').val() === '') {
                        alert('Sila masukkan mesej pembetulan untuk mengembalikan tugasan ini.');
                    } else {
                        var f = document.form1;
                        var eve = $('#event').val();
                        doSend('', fr, eve);
                        $(this).dialog('close');
                    }

                },
                'Tutup': function () {
                    $(this).dialog('close');
                }
            }
        });

        $("#dialog-form2").dialog({
            autoOpen: false,
            height: 150,
            width: 250,
            modal: true,
            draggable: false,
            open: function (event, ui) {
                setTimeout(function () {
                    $('.ui-state-focus').each(function () {
                        $(this).blur();
                    });
                }, 1);
            },
            buttons: {
                'Tutup': function () {
                    $(this).dialog('close');
                },
                'Ok': function () {
                    doSend('', fr, 'APPROVE');
                    $(this).dialog('close');
                }

            }
        });

        $('#semakSemula')
                .click(function () {
                    $("#event").val("PUSHBACK");
                    $('#dialog-form').dialog('open');
                });

        $('#hantarNotifikasi')
                .click(function () {
                    $("#event").val("NOTIFIKASI");
                    $('#dialog-form').dialog('open');
                });

        $('#hantar2')
                .click(function () {
                    $('#dialog-form2').dialog('open');
                });

    });

    function doSend(event, frm, e) {
        var msg;
        var msg2;
        if (e == 'PUSHBACK') {
            msg2 = 'Adakah anda pasti untuk mengembalikan tugasan ini?';
        } else {
            msg2 = 'Adakah anda pasti? Tugasan akan dihantar ke peringkat seterusnya.';
        }
        //alert('e='+e);
        //return;
        if (e === 'NOTIFIKASI') {

            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });

            var url = '${pageContext.request.contextPath}/stage?sendNotifikasi&mesej=' + $('#mesej').val();

            $.ajax({
                type: "GET",
                url: url,
                dataType: 'html',
                error: function (xhr, ajaxOptions, thrownError) {
                    alert('Notifikasi tidak dapat dihantar');
                },
                success: function (data) {
                    if (data === '0') {
                        alert("Notifikasi berjaya dihantar.");
                        $.unblockUI();
                    } else {
                        alert(data);
                        $.unblockUI();
                    }
                }
            });

        } else {
            if (confirm(msg2)) {
                $.blockUI({
                    message: $('#displayBox'),
                    css: {
                        top: ($(window).height() - 50) / 2 + 'px',
                        left: ($(window).width() - 50) / 2 + 'px',
                        width: '50px'
                    }
                });
                var url = '${pageContext.request.contextPath}/stage?nextStage&event=' + e;
                if (e == 'PUSHBACK') {
                    var u = $('#mesej').val();
                    url = url + '&mesej=' + u;
                }

                $.ajax({
                    type: "GET",
                    url: url,
                    dataType: 'html',
                    error: function (xhr, ajaxOptions, thrownError) {
                        //alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                        msg = xhr.statusText + '<br/>${actionBean.idPermohonan} : Tugasan tidak dapat dihantar ke peringkat seterusnya. Sila hubungi Pentadbir Sistem';
                        frm.action = '${pageContext.request.contextPath}/senaraiTugasan?error=' + msg;
                        frm.submit();
                    },
                    success: function (data) {
                        if (data == '1') {
                            msg = 'Tugasan tidak dapat dihantar ke peringkat seterusnya. Sila hubungi Pentadbir Sistem';
                            frm.action = '${pageContext.request.contextPath}/senaraiTugasan?error=' + msg;
                            frm.submit();
                        } else if (data == '2') {
                            alert('Sila buat keputusan terlebih dahulu.');
                            $('#status').click();
                        } else if (data == '3') {
                            msg = 'Terdapat urusan sebelum yang masih belum selesai.';
                            frm.action = '${pageContext.request.contextPath}/senaraiTugasan?error=' + msg;
                            frm.submit();
                        } else if (data == '4') {
                            alert('Dokumen tidak ditandatangani. Sila tandatangan Dokumen terlebih dahulu');
                            $('#urusan').click();
                        } else if (data == '5') {
                            alert('Sila masukkan id permohonan terdahulu terlebih dahulu');
                            $('#page_id_5').click();
                        } else {
                            frm.action = '${pageContext.request.contextPath}/' + data;
                            frm.submit();
                        }
                    }
                });
            }
        }
    }

    function doSendKembali(event, frm) {
        if (confirm('Adakah anda pasti? Tugasan akan dihantar ke peringkat sebelumnya.')) {
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });
            frm.action = frm.action + '?' + event;
            frm.submit();
        }
    }

    function kembaliKeUrusanBerangkai(e, f) {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });
        var url = '${pageContext.request.contextPath}/workflow/kernal_action?idPermohonan=${actionBean.idPermohonan}';
                f.action = url;
                f.submit();

            }

            function doPaparSummary() {
                var url = '${pageContext.request.contextPath}/reportGeneratorServlet?reportName=CONSMaklumatPermohonan_MLK.rdf&report_p_id_mohon=${actionBean.idPermohonan}';
                        window.open(url, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
                    }

                    function semakKembali() {
                        $('#dialog-form').dialog('open');
                    }

</script>
<div id="dialog-form" title="Sila masukkan mesej" style="display: none" class="subtitle">
    <s:form beanclass="etanah.view.stripes.StageActionBean">            
        <s:textarea name="mesej" cols="38" rows="10" id="mesej"/>
        <s:hidden name="id" id="event"/>
    </s:form>
</div>

<div id="dialog-form2" title="Amaran" style="display: none" class="subtitle">
    <s:form beanclass="etanah.view.stripes.StageActionBean">       
        <span>
            Buat Keputusan Dengan Bijak!! <br/>Anda yakin dengan keputusan ini?
        </span>

    </s:form>
</div>            


<s:form beanclass="etanah.view.stripes.StageActionBean" name="form1">
    <c:if test="${finalStage}">
        <div align="right" >
            <br> <input type="radio" name="tik" id="tik" onchange="nextFunc()"/>
            &nbsp;Saya memperakui bahawa saya telah menyemak kesemua dokumen-dokumen yang telah disediakan<br> bagi perserahan ini sebelum saya membuat keputusan bagi urusan-urusan yang terlibat. 
            </p>
        </div>
    </c:if>
    <br>
    <s:hidden name="stageId"/>
    <div align="right" style="background-color:${actionBean.warnaModul}">
        <c:if test="${berangkai}">
            <s:button name="showForm" value="Kembali" class="btn" onclick="kembaliKeUrusanBerangkai(this.name, this.form);"/>&nbsp;
            <c:if test="${actionBean.report && !finalStage}">
                <s:button name="genReport" id="report" value="Jana Dokumen" class="btn"/>&nbsp;
            </c:if>  
        </c:if>
        <c:if test="${!berangkai && empty actionBean.perluConvert}">
            <c:if test="${!distribute}">
                <c:if test="${actionBean.summaryRequired}">
                    <s:button name="summary" id="summary" class="btn" value="Ringkasan" onclick="doPaparSummary();"/>
                </c:if>
                <c:if test="${actionBean.report}">
                    <c:if test="${empty actionBean.modulpendaftaran}">
                        <s:button name="genReport" id="report" value="Jana Dokumen" class="btn"/>&nbsp;
                    </c:if>                    
                </c:if>
                <c:choose>
                    <c:when test="${finalStage}">
                        <s:button name="nextStage" id="hantar2" value="${actionBean.tabBean.nextStageButton}" class="disablebtn hantar"/>&nbsp;
                    </c:when>
                    <c:otherwise>
                        <s:button name="nextStage" id="hantar" value="${actionBean.tabBean.nextStageButton}" class="disablebtn hantar" onclick="doSend(this.name, this.form,'APPROVE');"/>&nbsp;
                    </c:otherwise>
                </c:choose>
                <c:if test="${actionBean.isPushBack}">
                    <s:button name="prevStage" value="Semak Semula" class="btn" id="semakSemula"/>&nbsp;&nbsp;&nbsp;
                </c:if>                
            </c:if>
            <c:if test="${distribute && actionBean.isPushBack}">
                <s:button name="prevStage" value="Semak Semula" class="btn" id="semakSemula"/>&nbsp;&nbsp;&nbsp;
            </c:if>
            <c:if test="${!empty actionBean.notifikasi && actionBean.notifikasi eq 'true'}">
                <s:button name="sendNotification" value="Hantar Notifikasi" class="btn" id="hantarNotifikasi"/>&nbsp;&nbsp;&nbsp;
            </c:if>
        </c:if>
    </div>
    <br>
</s:form>
