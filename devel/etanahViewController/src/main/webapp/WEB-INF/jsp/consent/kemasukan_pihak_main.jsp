<%-- 
    Document   : kemasukan_pihak_main
    Created on : Nov 5, 2010, 11:54:31 AM
    Author     : fikri
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    var DELIM = "__^$__";

    function doEdit(d, j, d1) {
        var idHakmilik = $('#hakmilik').val();
        window.open("${pageContext.request.contextPath}/consent/pihak_kepentingan?showEditPemohonPenerimaForm&jenis_pihak="
                + j + "&id=" + d + "&idPihak=" + d1 + '&idHakmilik=' + idHakmilik, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }

    function selectPemohon() {
        var len = $('.nama').length;
        var param = '';
        doBlockUI();
        for (var i = 1; i <= len; i++) {
            var ckd = $('#chkbox' + i).is(":checked");
            if (ckd) {
                param = param + '&idPihakBerkepentingan=' + $('#chkbox' + i).val();
            }
        }
        if (param == '') {
            alert('Sila pilih maklumat terlebih dahulu.');
            doUnBlockUI();
            return;
        }
        var url = '${pageContext.request.contextPath}/consent/pihak_kepentingan?simpanPemohon' + param + '&idHakmilik=' + $('#hakmilik').val();

        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }

    function selectTerlibat() {
        var len = $('.nama').length;
        var param = '';
        doBlockUI();
        for (var i = 1; i <= len; i++) {
            var ckd = $('#chkbox' + i).is(":checked");
            if (ckd) {
                param = param + '&idPihakBerkepentingan=' + $('#chkbox' + i).val();
            }
        }
        if (param == '') {
            alert('Sila pilih maklumat terlebih dahulu.');
            doUnBlockUI();
            return;
        }
        var url = '${pageContext.request.contextPath}/consent/pihak_kepentingan?simpanPihakTerlibat' + param + '&idHakmilik=' + $('#hakmilik').val();

        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }


    function addNew(action) {
        var idHakmilik = $('#hakmilik').val();
        var len = $('.nama').length;
        var param = '';
        doBlockUI();

        for (var i = 1; i <= len; i++) {

            var ckd = $('#chkbox' + i).is(":checked");
            if (ckd) {
                param = param + '&uids=' + $('#chkbox' + i).val();
            }
        }

        if (param == '') {
            alert('Sila pilih maklumat terlebih dahulu.');
            doUnBlockUI();
            return;
        }

        var url = '${pageContext.request.contextPath}/consent/pihak_kepentingan?' + action + param + '&jenisPihak=pemohon&idHakmilik=' + idHakmilik;

        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }


    function remove(action, cn, jenis, id) {
        var idHakmilik = $('#hakmilik').val();
        var len = $('.' + cn).length;
        var param = '';
        doBlockUI();

        for (var i = 1; i <= len; i++) {

            var ckd = $('#' + id + '_' + i).is(":checked");
            if (ckd) {
                param = param + '&uids=' + $('#' + id + '_' + i).val();
            }
        }

        if (param == '') {
            alert('Sila pilih maklumat terlebih dahulu.');
            doUnBlockUI();
            return;
        }
        else {
            var url = '${pageContext.request.contextPath}/consent/pihak_kepentingan?' + action + param + '&jenisPihak=' + jenis + '&idHakmilik=' + idHakmilik;

            $.ajax({
                type: "GET",
                url: url,
                dataType: 'html',
                error: function(xhr, ajaxOptions, thrownError) {
                    alert("error=" + xhr.responseText);
                    doUnBlockUI();
                },
                success: function(data) {
                    $('#page_div').html(data);
                    //doPopupMsg("Kemaskini berjaya!");
                    doUnBlockUI();
                }
            });
        }
    }

    function removePemohon() {
        var param = '';
        doBlockUI();

        $('.removePemohon').each(function(index) {
            var a = $('#rm_pemohon_' + index).is(":checked");
            if (a) {
                param = param + '&idPemohon=' + $('#rm_pemohon_' + index).val();
            }
        });

        if (param == '') {
            alert('Sila Pilih Pemohon terlebih dahulu.');
            doUnBlockUI();
            return;
        }
        else {
            if (confirm('Adakah anda pasti?')) {

                var url = '${pageContext.request.contextPath}/consent/pihak_kepentingan?deletePemohon' + param;
                $.ajax({
                    type: "GET",
                    url: url,
                    dataType: 'html',
                    error: function(xhr, ajaxOptions, thrownError) {
                        alert("error=" + xhr.responseText);
                        doUnBlockUI();
                    },
                    success: function(data) {
                        $('#page_div').html(data);
                        //doPopupMsg("Kemaskini berjaya!");
                        doUnBlockUI();
                    }
                });
            }
        }
    }

    function reloadEdit(val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/consent/pihak_kepentingan?reloadEdit&idHakmilik=' + val;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });

    }

    function reload(val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/consent/pihak_kepentingan?reload&idHakmilik=' + val;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });

    }

    function doOpen(type) {
        var idHakmilik = $('#hakmilik').val();
        var url = '${pageContext.request.contextPath}/consent/pihak_kepentingan?showSearchForm&type=' + type + '&idHakmilik=' + idHakmilik;
        window.open(url, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }


    function semakSyer(f,p) {
        var q = $(f).formSerialize();

            $.post('${pageContext.request.contextPath}/consent/pihak_kepentingan?semakSyer&hakmilik=' + $('#hakmilik').val()+'&jenis='+p, q,
                    function(data) {
                        if (data != '') {
                            alert(data);
                        }
                    }, 'html');
       
    }


    function semakSyer2(f, value) {
        notComplete = false;
        if (value == "penerima") {
            var rowNo = $('table#linePenerima tr').length;
            for (var i = 0; i < rowNo - 1; i++) {
                var s1 = $('#syer1' + i).val();
                var s2 = $('#syer2' + i).val();

                alert(s1);
                alert(s2);

                if (s1 == 0 || s1 == '' || s2 == 0 || s2 == '') {
                    notComplete = true;
                    break;
                }
            }
        }
        else if (value == "gadaian") {
            var rowNo = $('table#lineGadaian tr').length;

            for (var i = 0; i < rowNo - 1; i++) {
                var s1 = $('#syer1B' + i).val();
                var s2 = $('#syer2B' + i).val();
                if (s1 == 0 || s1 == '' || s2 == 0 || s2 == '') {
                    notComplete = true;
                    break;
                }
            }
        }

        if (notComplete) {
            alert("Sila Masukkan Maklumat Syer Dengan Betul");
        }

        else {
            alert("Pembahagian Syer Berjaya");
        }
    }

    function updateSyer(idMohonPihak, id) {
        var s1 = $('#syer1' + id).val();
        var s2 = $('#syer2' + id).val();

        if (s1 == '' || s2 == '') {
            return;
        }

        if (isNaN(s1) && isNaN(s2)) {
            return;
        }
        var url = '${pageContext.request.contextPath}/consent/pihak_kepentingan?updateSyerMohonPihak&idMohonPihak=' + idMohonPihak + '&idHakmilik=' + $('#hakmilik').val()
                + '&syer1=' + s1 + '&syer2=' + s2;
        $.post(url,
                function(data) {
                }, 'html');

    }

    function updateLuas(idMohonPihak, id) {
        var luas = $('#luas' + id).val();
        var unitLuas = $('#unitLuas' + id).val();

        if (luas == '' || unitLuas == '') {
            return;
        }

        if (isNaN(luas) && isNaN(unitLuas)) {
            return;
        }
        var url = '${pageContext.request.contextPath}/consent/pihak_kepentingan?updateLuasMohonPihak&idMohonPihak=' + idMohonPihak + '&idHakmilik=' + $('#hakmilik').val()
                + '&luas=' + luas + '&unitLuas=' + unitLuas;
        $.post(url,
                function(data) {
                }, 'html');

    }

    function samaRata(f, value) {
        var idHakmilik = $('#hakmilik').val();
        var q = $(f).formSerialize();
        $.get('${pageContext.request.contextPath}/consent/pihak_kepentingan?agihSamaRata&jenis=' + value + '&hakmilik=' + idHakmilik, q,
                function(data) {
                    if (data == null || data.length == 0) {
                        alert('Terdapat Masalah');
                        return;
                    }
                    var p = data.split(DELIM);

                    if (value == "penerima") {
                        $('.pembilang').each(function() {
                            $(this).val(p[0]);
                        });
                        $('.penyebut').each(function() {
                            $(this).val(p[1]);
                        });
                    }

                    else if (value == "gadaian") {
                        $('.pembilang2').each(function() {
                            $(this).val(p[0]);
                        });
                        $('.penyebut2').each(function() {
                            $(this).val(p[1]);
                        });
                    }

                });
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

    function viewPihak(id, idHp, idPemohon, jenis) {
        window.open("${pageContext.request.contextPath}/consent/pihak_kepentingan?viewPihakDetail&idPihak=" + id + "&idHp=" + idHp + "&idPemohon=" + idPemohon +  "&jenis=" + jenis + '&idHakmilik=' + $('#hakmilik').val(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }

    function viewMohonPihak(idMohonPihak, jenis) {
        window.open("${pageContext.request.contextPath}/consent/pihak_kepentingan?viewMohonPihakDetail&idMohonPihak=" + idMohonPihak + "&jenis=" + jenis, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }

    function editTuanTanah(idPihak) {
        window.open("${pageContext.request.contextPath}/consent/pihak_kepentingan?editTuanTanah&idPihak=" + idPihak + "&hakmilik=" + $('#hakmilik').val(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }

    function editPihakTerlibat(idMohonPihak) {
        window.open("${pageContext.request.contextPath}/consent/pihak_kepentingan?editPihakTerlibat&idMohonPihak=" + idMohonPihak + "&hakmilik=" + $('#hakmilik').val(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }

    function editPemohon(idPemohon) {
        window.open("${pageContext.request.contextPath}/consent/pihak_kepentingan?editPemohon&idPemohon=" + idPemohon + '&hakmilik=' + $('#hakmilik').val(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }

    function editPenerima(idMohonPihak) {
        window.open("${pageContext.request.contextPath}/consent/pihak_kepentingan?editPenerima&idMohonPihak=" + idMohonPihak + '&hakmilik=' + $('#hakmilik').val(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }

    function editPenerimaGadaian(idMohonPihak) {
        window.open("${pageContext.request.contextPath}/consent/pihak_kepentingan?editPenerimaGadaian&idMohonPihak=" + idMohonPihak + '&hakmilik=' + $('#hakmilik').val(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }


</script>        

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<div class="subtitle displaytag">
    <s:messages/>
    <s:errors/>
    <s:form beanclass="etanah.view.stripes.consent.PihakKepentinganActionBean" name="form1">      
        <fieldset class="aras1">
            <legend>Senarai Hakmilik Terlibat</legend>
            <div align="center">
                <c:if test="${fn:length(actionBean.senaraiHakmilikTerlibat) > 1}">
                    <p>
                        <font size="2" color="red">
                            <b>Permohonan Melibatkan Banyak Hakmilik</b>
                        </font>
                    </p>
                </c:if>
            </div>
            <div align="center">          
                <font color="#003194" style="font-weight: bold; font-family:Tahoma; font-size: 13px;">
                    Hakmilik :
                </font>
                <c:if test="${edit}">
                    <s:select name="idHakmilik" onchange="reloadEdit(this.value);" id="hakmilik">
                        <c:forEach items="${actionBean.senaraiHakmilikTerlibat}" var="item" varStatus="line">
                            <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                                ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                            </s:option>
                        </c:forEach>
                    </s:select>
                </c:if>
                <c:if test="${!edit}">
                    <s:select name="idHakmilik" onchange="reload(this.value);" id="hakmilik">
                        <c:forEach items="${actionBean.senaraiHakmilikTerlibat}" var="item" varStatus="line">
                            <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                                ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                            </s:option>
                        </c:forEach>
                    </s:select>
                </c:if>
            </div>
            <br/>
        </fieldset>
        <br/>

        <fieldset class="aras1">
            <legend> Senarai Pemilik</legend>
            <c:if test="${edit}">
                <c:if test="${fn:length(actionBean.senaraiKeempunyaan) == 1}">
                    <p align="center">
                        <font size="2" color="red">
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'TMADT' && actionBean.permohonan.kodUrusan.kod ne 'TMWNA'
                                          && (actionBean.permohonan.kodUrusan.kod ne 'PMMK1' && actionBean.kodNegeri eq '04')
                                          && (actionBean.permohonan.kodUrusan.kod ne 'PMMK2' && actionBean.kodNegeri eq '04')}">
                                  <b>Sila Pilih Butang 'Pilih Pemohon' Jika Pemilik Adalah Pemohon</b>
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TMADT' || actionBean.permohonan.kodUrusan.kod eq 'TMWNA'}">
                                <b>Sila masukkan maklumat kematian pada bahagian kemaskini.</b>
                            </c:if>
                        </font>
                    </p>
                </c:if>
                <c:if test="${fn:length(actionBean.senaraiKeempunyaan) > 1}">
                    <p align="center">
                        <font size="2" color="red">
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'TMADT' && actionBean.permohonan.kodUrusan.kod ne 'TMWNA'}">
                                <b>Sila Pilih Butang 'Pilih Pihak Terlibat' Bagi Pemilik Yang Terlibat Untuk Permohonan Ini</b><br/>
                                <c:if test="${(actionBean.permohonan.kodUrusan.kod ne 'PMMK1' && actionBean.kodNegeri eq '04')
                                              && (actionBean.permohonan.kodUrusan.kod ne 'PMMK2' && actionBean.kodNegeri eq '04')}">
                                      <b>dan</b><br/>
                                </c:if>
                            </c:if>
                            <c:if test="${(actionBean.permohonan.kodUrusan.kod ne 'PMMK1' && actionBean.kodNegeri eq '04')
                                          && (actionBean.permohonan.kodUrusan.kod ne 'PMMK2' && actionBean.kodNegeri eq '04')}">
                                  <b>Sila Pilih Butang 'Pilih Pemohon' Jika Pemilik Adalah Pemohon</b>
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TMADT' || actionBean.permohonan.kodUrusan.kod eq 'TMWNA'}">
                                <b>Sila masukkan maklumat kematian pada bahagian kemaskini.</b>
                            </c:if>
                        </font>
                    </p>
                </c:if>
            </c:if>
            <div class="content" align="center">
                <display:table style="width:90%;" class="tablecloth" name="${actionBean.senaraiKeempunyaan}"
                               cellpadding="0" cellspacing="0" id="linePemilik">

                    <c:if test="${edit}">
                        <display:column title="Pilih" style="width:40;">
                            <s:checkbox name="checkbox" id="chkbox${linePemilik_rowNum}" value="${linePemilik.idHakmilikPihakBerkepentingan}"/>
                        </display:column>
                    </c:if>
                    <display:column title="Bil" sortable="true" style="width:40;">${linePemilik_rowNum}</display:column>
                    <display:column  title="Nama" class="nama">
                        <a href="#" onclick="viewPihak('${linePemilik.pihak.idPihak}','${linePemilik.idHakmilikPihakBerkepentingan}', 'tuanTanah');
                                return false;"><font style="text-transform:uppercase;">${linePemilik.nama}</font></a>
                            </display:column>
                            <display:column property="noPengenalan" title="Nombor Pengenalan" />
                            <display:column title="Syer Dimiliki" >
                        <div align="center">
                            ${linePemilik.syerPembilang}/${linePemilik.syerPenyebut}
                        </div>
                    </display:column>
                    <display:column title="Jenis Pihak"><font style="text-transform:uppercase;">${linePemilik.jenis.nama} </font></display:column>
                        <c:if test="${fn:endsWith(actionBean.permohonan.kodUrusan.idWorkflow, 'Project1/mmk2') ||  actionBean.permohonan.kodUrusan.idWorkflow eq 'http://xmlns.oracle.com/Consent_Negeri/Consent/ADAT' || actionBean.permohonan.kodUrusan.kod eq 'BTADT' || actionBean.permohonan.kodUrusan.kod eq 'TMWNA' || fn:endsWith(actionBean.permohonan.kodUrusan.idWorkflow, 'Project1/mmk2')}">
                            <c:if test="${edit}">
                                <display:column title="Kemaskini" style="width:70;">
                                    <c:if test="${linePemilik.pihak.jenisPengenalan.kod eq 'B' || linePemilik.pihak.jenisPengenalan.kod eq 'L' || linePemilik.pihak.jenisPengenalan.kod eq 'P' || 
                                                  linePemilik.pihak.jenisPengenalan.kod eq 'T' || linePemilik.pihak.jenisPengenalan.kod eq 'I' || linePemilik.pihak.jenisPengenalan.kod eq 'F'
                                                  || linePemilik.pihak.jenisPengenalan.kod eq '0'}">
                                    <p align="center">
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="editTuanTanah('${linePemilik.pihak.idPihak}', 'tuanTanah');
                                                     return false;" onmouseover="this.style.cursor = 'pointer';">
                                    </p>
                                </c:if>
                            </display:column>
                        </c:if>
                    </c:if>
                </display:table>
            </div>
            <c:if test="${fn:length(actionBean.senaraiKeempunyaan) > 0}">
                <c:if test="${edit}">
                    <p align="center">
                        <c:if test="${fn:length(actionBean.senaraiKeempunyaan) > 1}">
                            <s:button class="longbtn" value="Pilih Pihak Terlibat" name="pilih" id="pilih" onclick="selectTerlibat();"/>&nbsp;
                        </c:if>
                        <%--KES NEGERI SEMBILAN--%>
                        <c:if test="${( actionBean.kodNegeri eq '05')}">
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'TMADT' && actionBean.permohonan.kodUrusan.kod ne 'TMWNA'
                                          && actionBean.permohonan.kodUrusan.kod ne 'DPWNA'  && actionBean.permohonan.kodUrusan.kod ne 'PMMAT'}">
                                <s:button class="longbtn" value="Pilih Pemohon" name="pilih" id="pilih" onclick="selectPemohon();"/>&nbsp;
                            </c:if>
                        </c:if>
                        <%--KES MELAKA--%>
                        <c:if test="${( actionBean.kodNegeri eq '04')}">
                            <c:if test="${(actionBean.permohonan.kodUrusan.kod ne 'PMMK1') && (actionBean.permohonan.kodUrusan.kod ne 'PMMK2')}">
                                <s:button class="longbtn" value="Pilih Pemohon" name="pilih" id="pilih" onclick="selectPemohon();"/>&nbsp;
                            </c:if>
                        </c:if>
                    </p>
                </c:if>
            </c:if>
        </fieldset>
        <c:if test="${ fn:length(actionBean.senaraiKeempunyaan) > 0}">
            <br/>
            <fieldset class="aras1">
                <legend>Senarai Pemilik Terlibat 
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMMAT' || actionBean.permohonan.kodUrusan.kod eq 'DPWNA'}">
                                (Si Mati)
                            </c:if>
                </legend>
                <div class="content" align="center">
                    <display:table style="width:90%;" class="tablecloth" name="${actionBean.senaraiTuanTanahTerlibat}"
                                   cellpadding="0" cellspacing="0" id="lineTerlibat">
                        <c:if test="${edit}">
                            <display:column title="Pilih" style="width:40;">
                                <s:checkbox name="checkbox" id="rm_ter_${lineTerlibat_rowNum}" value="${lineTerlibat.idPermohonanPihak}"/>
                            </display:column>
                        </c:if>
                        <display:column title="Bil" style="width:40;">${lineTerlibat_rowNum}</display:column>
                        <display:column  title="Nama" class="remove">
                            <font style="text-transform:uppercase;">${lineTerlibat.nama}</font>
                                </display:column>
                                <display:column property="noPengenalan" title="Nombor Pengenalan" />
                                <display:column title="Syer Terlibat">
                            <div align="center">
                                ${lineTerlibat.syerPembilang}/${lineTerlibat.syerPenyebut}
                            </div>
                        </display:column>
                        <c:if test="${edit}">
                            <display:column title="Kemaskini" style="width:70;">
                                <p align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                         onclick="editPihakTerlibat('${lineTerlibat.idPermohonanPihak}', 'tuanTanahTerlibat');
                                                 return false;" onmouseover="this.style.cursor = 'pointer';">
                                </p>
                            </display:column>
                        </c:if>
                    </display:table>
                </div>

                <c:if test="${edit}">
                    <p align="center">
                        <c:if test="${fn:length(actionBean.senaraiTuanTanahTerlibat) > 0}">
                            <s:button name="delete" onclick="remove(this.name, 'remove', 'pihakTerlibat', 'rm_ter');" value="Hapus" class="btn"/>&nbsp;
                        </c:if>
                    </p>
                </c:if>
            </fieldset>
        </c:if>
        <c:if test="${!fn:endsWith(actionBean.permohonan.kodUrusan.idWorkflow, 'MMK_melaka') && !fn:endsWith(actionBean.permohonan.kodUrusan.idWorkflow, 'Project1/mmk2')}">
            <br/>
            <fieldset class="aras1">
                <legend>Senarai Pemohon</legend>

                <div class="content" align="center">
                    <display:table style="width:90%;" class="tablecloth" name="${actionBean.senaraiPemohon}"
                                   cellpadding="0" cellspacing="0" id="linePemohon">
                        <c:if test="${edit}">
                            <display:column title="Pilih" style="width:40;">
                                <s:checkbox name="checkbox" id="rm_pemohon_${linePemohon_rowNum-1}" value="${linePemohon.idPemohon}" class="removePemohon"/>
                            </display:column>
                        </c:if>
                        <display:column title="Bil" style="width:40;">${linePemohon_rowNum}</display:column>
                        <display:column  title="Nama">
                           <font style="text-transform:uppercase;">${linePemohon.nama}</font>
                        </display:column>
                        <display:column property="noPengenalan" title="Nombor Pengenalan" />
                        <display:column title="Alamat Surat Menyurat">
                                    ${linePemohon.pihak.senaraiAlamatTamb[0].alamatKetiga1}
                                    ${linePemohon.pihak.senaraiAlamatTamb[0].alamatKetiga2}
                                    ${linePemohon.pihak.senaraiAlamatTamb[0].alamatKetiga3}
                                    ${linePemohon.pihak.senaraiAlamatTamb[0].alamatKetiga4}
                                    ${linePemohon.pihak.senaraiAlamatTamb[0].alamatKetigaPoskod}
                            <font style="text-transform:uppercase;">${linePemohon.pihak.senaraiAlamatTamb[0].alamatKetigaNegeri.nama}</font>
                                <%-- ${linePemohon.pihak.suratAlamat1}
                                <%--<c:if test="${linePemohon.pihak.suratAlamat1 ne null && linePemohon.pihak.suratAlamat2 ne null}"> , </c:if>--%>

                            <%-- ${linePemohon.pihak.suratAlamat2}
                            <%--<c:if test="${linePemohon.pihak.suratAlamat2 ne null && linePemohon.pihak.suratAlamat3 ne null}"> , </c:if>--%>
                            <%-- ${linePemohon.pihak.suratAlamat3}
                            <%--<c:if test="${linePemohon.pihak.suratAlamat3 ne null && linePemohon.pihak.suratAlamat4 ne null}"> , </c:if>--%>
                            <%--${linePemohon.pihak.suratAlamat4}
                            <%--<c:if test="${linePemohon.pihak.suratAlamat4 ne null && linePemohon.pihak.suratPoskod ne null}">,</c:if>--%>
                            <%-- ${linePemohon.pihak.suratPoskod}
                            <%--<c:if test="${linePemohon.pihak.suratPoskod ne null && linePemohon.pihak.suratNegeri.kod ne null}">,</c:if>--%>
                            <%--<font style="text-transform:uppercase;">${linePemohon.pihak.suratNegeri.nama}</font>--%>
                        </display:column>
                        <display:column  title="Kategori Pemohon">
                           <!--<font style="text-transform:uppercase;">${linePemohon.kodStatus}</font>-->
                           <%--<c:if test="${linePemohon.jenis ne 'TER'}">--%>
                               <c:if test="${linePemohon.kodStatus ne null}">
                                   <c:if test="${linePemohon.kodStatus == '1001'}" >    
                                    <font style="text-transform:uppercase;">MELAYU BUMIPUTERA</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '1002'}" >    
                                    <font style="text-transform:uppercase;">BUMIPUTERA SABAH</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '1003'}" >    
                                    <font style="text-transform:uppercase;">BUMIPUTERA SARAWAK</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '1004'}" >    
                                    <font style="text-transform:uppercase;">BUKAN BUMIPUTERA</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '1005'}" >    
                                    <font style="text-transform:uppercase;">WARGA ASING</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '2001'}" >    
                                    <font style="text-transform:uppercase;">SYARIKAT BUMIPUTERA</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '2002'}" >    
                                    <font style="text-transform:uppercase;">SYARIKAT BUKAN BUMIPUTERA</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '3001'}" >    
                                    <font style="text-transform:uppercase;">SYARIKAT ASING</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '4001'}" >    
                                    <font style="text-transform:uppercase;">PERBADANAN KETUA MENTERI (CMI</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '4002'}" >    
                                    <font style="text-transform:uppercase;">PERBADANAN KEMAJUAN TANAH DAN ADAT (PERTAM</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '4003'}" >    
                                    <font style="text-transform:uppercase;">YAYASAN DMDI</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '4004'}" >    
                                    <font style="text-transform:uppercase;">YAYASAN MELAKA</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '4005'}" >    
                                    <font style="text-transform:uppercase;">MAJLIS AGAMA ISLAM MELAKA (MAIM</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '4006'}" >    
                                    <font style="text-transform:uppercase;">LAIN-LAIN</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '5001'}" >    
                                    <font style="text-transform:uppercase;">BADAN BERKANUN</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '6001'}" >    
                                    <font style="text-transform:uppercase;">NGO</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '7001'}" >    
                                    <font style="text-transform:uppercase;">BANK</font>        
                                   </c:if>
                               </c:if>
                            <%--</c:if>--%>
                        </display:column> 
                        <c:if test="${edit}">
                            <display:column title="Kemaskini" style="width:70;">
                                <p align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                         onclick="editPemohon('${linePemohon.idPemohon}');
                                                 return false;" onmouseover="this.style.cursor = 'pointer';">
                                </p>
                            </display:column>
                        </c:if>
                    </display:table>
                </div>
                <c:if test="${edit}">
                    <p align="center">
                        <s:button name="add" onclick="doOpen('pemohon');" value="Tambah" class="btn"/>
                        <c:if test="${(fn:length(actionBean.senaraiPemohon) > 0)}">
                            <s:button name="delete" onclick="removePemohon();" value="Hapus" class="btn"/>
                        </c:if>
                    </p>
                </c:if>
            </fieldset>
        </c:if>
        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'BTADT' && actionBean.permohonan.kodUrusan.kod ne 'TMADT' && actionBean.permohonan.kodUrusan.kod ne 'TMWNA'}">
            <br/>
            <fieldset class="aras1">
                <legend>Senarai
                    <c:choose>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PCPTD' || actionBean.permohonan.kodUrusan.kod eq 'PGDMB' 
                                        || actionBean.permohonan.kodUrusan.kod eq 'PCMMK' || actionBean.permohonan.kodUrusan.kod eq 'GSMMK' 
                                        || actionBean.permohonan.kodUrusan.kod eq 'PMJTL' || actionBean.permohonan.kodUrusan.kod eq 'PJKTL' 
                                        || actionBean.permohonan.kodUrusan.kod eq 'PMTMB' || actionBean.permohonan.kodUrusan.kod eq 'RMJTL' 
                                        || actionBean.permohonan.kodUrusan.kod eq 'RJKTL' || actionBean.permohonan.kodUrusan.kod eq 'PMADT'
                                        || actionBean.permohonan.kodUrusan.kod eq 'PMWNA' || actionBean.permohonan.kodUrusan.kod eq 'PMWWA'
                                        || actionBean.permohonan.kodUrusan.kod eq 'PPTGM' || actionBean.permohonan.kodUrusan.kod eq 'PMKMM'
                                        || actionBean.permohonan.kodUrusan.kod eq 'PMPTD' || actionBean.permohonan.kodUrusan.kod eq 'PMMMK'
                                        || fn:startsWith(actionBean.permohonan.kodUrusan.kod, 'PPTD') 
                                        ||  (fn:startsWith(actionBean.permohonan.kodUrusan.kod, 'PMMK') && actionBean.kodNegeri eq '05')}">
                                Penerima Pindah Milik
                        </c:when>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PJPTD' || actionBean.permohonan.kodUrusan.kod eq 'PJMMK' 
                                        || actionBean.permohonan.kodUrusan.kod eq 'PJADT' || actionBean.permohonan.kodUrusan.kod eq 'PJKMM'}">
                                Penerima Pajakan
                        </c:when>
                        <c:when  test="${fn:startsWith(actionBean.permohonan.kodUrusan.kod,'GPTD') || actionBean.permohonan.kodUrusan.kod eq 'MCGMB' 
                                         || actionBean.permohonan.kodUrusan.kod eq 'MCMMK' || actionBean.permohonan.kodUrusan.kod eq 'CGADT'}">
                                 Penerima Gadaian
                        </c:when>                        
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'MCKMM' || actionBean.permohonan.kodUrusan.kod eq 'MCPTG'
                                || actionBean.permohonan.kodUrusan.kod eq 'MCPTD'}">
                            Penerima Cagaran
                        </c:when>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'SWKMM' || actionBean.permohonan.kodUrusan.kod eq 'SWPTD'}">
                            Penerima Sewaan
                        </c:when>    
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'TTADT'}">
                            Pihak Yang Menuntut
                        </c:when>   
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PMMAT' || actionBean.permohonan.kodUrusan.kod eq 'DPWNA'}">
                            Penerima Turun Milik
                        </c:when>  
                        <c:when test="${(actionBean.permohonan.kodUrusan.kod eq 'PMMK1' && actionBean.kodNegeri eq '04') || (actionBean.permohonan.kodUrusan.kod eq 'PMMK2' && actionBean.kodNegeri eq '04')}">
                            Pemohon/Penerima Pindah Milik
                        </c:when>
                        <c:otherwise>
                            Penerima 
                        </c:otherwise>
                    </c:choose>
                </legend>

                <div class="content" align="center">
                    <display:table style="width:90%;" class="tablecloth" name="${actionBean.senaraiPermohonanPihak}"
                                   cellpadding="0" cellspacing="0" id="linePenerima">
                        <c:if test="${edit}">
                            <display:column title="Pilih" style="width:40;">
                                <s:checkbox name="checkbox" id="rm_penerima_${linePenerima_rowNum}" value="${linePenerima.idPermohonanPihak}"/>
                            </display:column>
                        </c:if>
                        <display:column title="Bil" style="width:40;">${linePenerima_rowNum}</display:column>
                        <display:column  title="Nama" class="remove">
                            <a href="#"  onclick="viewMohonPihak('${linePenerima.idPermohonanPihak}', 'penerima');
                                    return false;"><font style="text-transform:uppercase;">${linePenerima.pihak.nama}</font></a>
                        </display:column>
                        <display:column property="noPengenalan" title="Nombor Pengenalan" />
                        <display:column title="Alamat Surat Menyurat">
                                <c:if test="${linePenerima.pihakCawangan ne null}">

                                ${linePenerima.pihakCawangan.suratAlamat1}
                                <%--<c:if test="${linePenerima.pihakCawangan.suratAlamat1 ne null && linePenerima.pihakCawangan.suratAlamat2 ne null}">, </c:if>--%>
                                ${linePenerima.pihakCawangan.suratAlamat2}
                                <%--<c:if test="${linePenerima.pihakCawangan.suratAlamat2 ne null && linePenerima.pihakCawangan.suratAlamat3 ne null}">, </c:if>--%>
                                ${linePenerima.pihakCawangan.suratAlamat3}
                                <%--<c:if test="${linePenerima.pihakCawangan.suratAlamat3 ne null && linePenerima.pihakCawangan.suratAlamat4 ne null}">, </c:if>--%>
                                ${linePenerima.pihakCawangan.suratAlamat4}
                                <%--<c:if test="${linePenerima.pihakCawangan.suratAlamat4 ne null && linePenerima.pihakCawangan.suratPoskod ne null}">,</c:if>--%>
                                ${linePenerima.pihakCawangan.suratPoskod}
                                <%--<c:if test="${linePenerima.pihakCawangan.suratPoskod ne null && linePenerima.pihakCawangan.suratNegeri ne null}">,</c:if>--%>
                                <font style="text-transform:uppercase;">${linePenerima.pihakCawangan.suratNegeri.nama}</font>
                                </c:if>
                                <c:if test="${linePenerima.pihakCawangan eq null}">

                                ${linePenerima.pihak.senaraiAlamatTamb[0].alamatKetiga1}
                                ${linePenerima.pihak.senaraiAlamatTamb[0].alamatKetiga2}
                                ${linePenerima.pihak.senaraiAlamatTamb[0].alamatKetiga3}
                                ${linePenerima.pihak.senaraiAlamatTamb[0].alamatKetiga4}
                                ${linePenerima.pihak.senaraiAlamatTamb[0].alamatKetigaPoskod}
                                <font style="text-transform:uppercase;">${linePenerima.pihak.senaraiAlamatTamb[0].alamatKetigaNegeri.nama}</font>
                                    <%-- ${linePenerima.pihak.suratAlamat1}
                                    <%--<c:if test="${linePenerima.pihak.suratAlamat1 ne null && linePenerima.pihak.suratAlamat2 ne null}">,</c:if>--%>
                                    <%-- ${linePenerima.pihak.suratAlamat2}
                                    <%--<c:if test="${linePenerima.pihak.suratAlamat2 ne null && linePenerima.pihak.suratAlamat3 ne null}">,</c:if>--%>
                                    <%-- ${linePenerima.pihak.suratAlamat3}
                                    <%--<c:if test="${linePenerima.pihak.suratAlamat3 ne null && linePenerima.pihak.suratAlamat4 ne null}">,</c:if>--%>
                                    <%--  ${linePenerima.pihak.suratAlamat4}
                                    <%--<c:if test="${linePenerima.pihak.suratAlamat4 ne null && linePenerima.pihak.suratPoskod ne null}">,</c:if>--%>
                                    <%--  ${linePenerima.pihak.suratPoskod}
                                    <%--<c:if test="${linePenerima.pihak.suratPoskod ne null && linePenerima.pihak.suratNegeri ne null}">,</c:if>--%>
                                    <%-- <font style="text-transform:uppercase;">${linePenerima.pihak.suratNegeri.nama}</font>--%>
                                </c:if>
                            </display:column>
                            <display:column property="jenis.nama" title="Jenis Penerima" style="text-transform:uppercase;"/>
                            <display:column  title="Kategori Penerima">
                                <!--linePenerima<font style="text-transform:uppercase;">${linePenerima.kodStatus}</font>-->
                                <c:if test="${linePenerima.jenis ne 'TER'}">
                                   <c:if test="${linePenerima.kodStatus ne null}">
                                       <c:if test="${linePenerima.kodStatus == '1001'}" >    
                                        <font style="text-transform:uppercase;">MELAYU BUMIPUTERA</font>        
                                       </c:if>
                                       <c:if test="${linePenerima.kodStatus == '1002'}" >    
                                        <font style="text-transform:uppercase;">BUMIPUTERA SABAH</font>        
                                       </c:if>
                                       <c:if test="${linePenerima.kodStatus == '1003'}" >    
                                        <font style="text-transform:uppercase;">BUMIPUTERA SARAWAK</font>        
                                       </c:if>
                                       <c:if test="${linePenerima.kodStatus == '1004'}" >    
                                        <font style="text-transform:uppercase;">BUKAN BUMIPUTERA</font>        
                                       </c:if>
                                       <c:if test="${linePenerima.kodStatus == '1005'}" >    
                                        <font style="text-transform:uppercase;">WARGA ASING</font>        
                                       </c:if>
                                       <c:if test="${linePenerima.kodStatus == '2001'}" >    
                                        <font style="text-transform:uppercase;">SYARIKAT BUMIPUTERA</font>        
                                       </c:if>
                                       <c:if test="${linePenerima.kodStatus == '2002'}" >    
                                        <font style="text-transform:uppercase;">SYARIKAT BUKAN BUMIPUTERA</font>        
                                       </c:if>
                                       <c:if test="${linePenerima.kodStatus == '3001'}" >    
                                        <font style="text-transform:uppercase;">SYARIKAT ASING</font>        
                                       </c:if>
                                       <c:if test="${linePenerima.kodStatus == '4001'}" >    
                                        <font style="text-transform:uppercase;">PERBADANAN KETUA MENTERI (CMI</font>        
                                       </c:if>
                                       <c:if test="${linePenerima.kodStatus == '4002'}" >    
                                        <font style="text-transform:uppercase;">PERBADANAN KEMAJUAN TANAH DAN ADAT (PERTAM</font>        
                                       </c:if>
                                       <c:if test="${linePenerima.kodStatus == '4003'}" >    
                                        <font style="text-transform:uppercase;">YAYASAN DMDI</font>        
                                       </c:if>
                                       <c:if test="${linePenerima.kodStatus == '4004'}" >    
                                        <font style="text-transform:uppercase;">YAYASAN MELAKA</font>        
                                       </c:if>
                                       <c:if test="${linePenerima.kodStatus == '4005'}" >    
                                        <font style="text-transform:uppercase;">MAJLIS AGAMA ISLAM MELAKA (MAIM</font>        
                                       </c:if>
                                       <c:if test="${linePenerima.kodStatus == '4006'}" >    
                                        <font style="text-transform:uppercase;">LAIN-LAIN</font>        
                                       </c:if>
                                       <c:if test="${linePenerima.kodStatus == '5001'}" >    
                                        <font style="text-transform:uppercase;">BADAN BERKANUN</font>        
                                       </c:if>
                                       <c:if test="${linePenerima.kodStatus == '6001'}" >    
                                        <font style="text-transform:uppercase;">NGO</font>        
                                       </c:if>
                                       <c:if test="${linePenerima.kodStatus == '7001'}" >    
                                        <font style="text-transform:uppercase;">BANK</font>        
                                       </c:if>
                                   </c:if>
                                </c:if>
                            </display:column>
                            <c:if test="${edit}">
                                <display:column title="Syer Terlibat" style="width:165;">
                                <div align="center">
                                    <c:if test="${linePenerima.jenis.kod eq 'PA' || linePenerima.jenis.kod eq 'PP' || linePenerima.jenis.kod eq 'WS'}">
                                        <s:text name="senaraiPermohonanPihak[${linePenerima_rowNum-1}].syerPembilang" id="syer1${linePenerima_rowNum-1}"
                                                onblur="updateSyer('${linePenerima.idPermohonanPihak}', '${linePenerima_rowNum-1}')" class="pembilang"
                                                onchange="updateSyer('${linePenerima.idPermohonanPihak}', '${linePenerima_rowNum-1}')" size="6" maxlength="13"
                                                onkeyup="validateNumber(this,this.value);"/> /
                                        <s:text name="senaraiPermohonanPihak[${linePenerima_rowNum-1}].syerPenyebut" id="syer2${linePenerima_rowNum-1}"
                                                onblur="updateSyer('${linePenerima.idPermohonanPihak}', '${linePenerima_rowNum-1}')"
                                                onchange="updateSyer('${linePenerima.idPermohonanPihak}', '${linePenerima_rowNum-1}')"
                                                onkeyup="validateNumber(this,this.value);" class="penyebut" size="6" maxlength="13"/>
                                    </c:if>
                                    <c:if test="${linePenerima.jenis.kod ne 'PA' && linePenerima.jenis.kod ne 'PP' && linePenerima.jenis.kod ne 'WS'}">
                                        <s:text name="senaraiPermohonanPihak[${linePenerima_rowNum-1}].syerPembilang" id="syer1${linePenerima_rowNum-1}"
                                                onblur="updateSyer('${linePenerima.idPermohonanPihak}', '${linePenerima_rowNum-1}')" class="pembilang"
                                                onchange="updateSyer('${linePenerima.idPermohonanPihak}', '${linePenerima_rowNum-1}')" size="6" maxlength="13"
                                                onkeyup="validateNumber(this,this.value);"/> /
                                        <s:text name="senaraiPermohonanPihak[${linePenerima_rowNum-1}].syerPenyebut" id="syer2${linePenerima_rowNum-1}"
                                                onblur="updateSyer('${linePenerima.idPermohonanPihak}', '${linePenerima_rowNum-1}')"
                                                onchange="updateSyer('${linePenerima.idPermohonanPihak}', '${linePenerima_rowNum-1}')"
                                                onkeyup="validateNumber(this,this.value);" class="penyebut" size="6" maxlength="13"/>
                                    </c:if>
                                </div>
                            </display:column>
                            <c:if test="${(actionBean.permohonan.kodUrusan.kod eq 'PJADT') }">
                                <display:column title="Luas" style="width:320;">
                                    <div align="center">
                                        <c:if test="${linePenerima.jenis.kod eq 'PA' || linePenerima.jenis.kod eq 'PP' || linePenerima.jenis.kod eq 'WS'}">
                                            -
                                        </c:if>
                                        <c:if test="${linePenerima.jenis.kod ne 'PA' && linePenerima.jenis.kod ne 'PP' && linePenerima.jenis.kod ne 'WS'}">
                                            <s:text name="senaraiPermohonanPihak[${linePenerima_rowNum-1}].luasTerlibat" id="luas${linePenerima_rowNum-1}"
                                                    onblur="updateLuas('${linePenerima.idPermohonanPihak}', '${linePenerima_rowNum-1}')" class="luasClass"
                                                    onchange="updateLuas('${linePenerima.idPermohonanPihak}', '${linePenerima_rowNum-1}')" size="6" maxlength="13"
                                                    onkeyup="validateNumber(this,this.value);"/>
                                            <s:select name="senaraiPermohonanPihak[${linePenerima_rowNum-1}].kodUnitLuas.kod" id="unitLuas${linePenerima_rowNum-1}"
                                                      onblur="updateLuas('${linePenerima.idPermohonanPihak}', '${linePenerima_rowNum-1}')" class="unitLuasClass"
                                                      onchange="updateLuas('${linePenerima.idPermohonanPihak}', '${linePenerima_rowNum-1}')" >
                                                <s:option value="">SILA PILIH</s:option>
                                                <s:options-collection collection="${listUtil.senaraiKodUOMByLuas}" label="nama" value="kod"/>
                                            </s:select>
                                        </c:if>
                                    </div>
                                </display:column>
                            </c:if>
                        </c:if>

                        <c:if test="${!edit}">
                            <display:column title="Syer Terlibat" style="width:165;">
                                <div align="center">
                                    <c:if test="${linePenerima.jenis.kod eq 'PA' || linePenerima.jenis.kod eq 'PP' || linePenerima.jenis.kod eq 'WS'}">
                                        <c:if test="${linePenerima.syerPembilang eq null && linePenerima.syerPenyebut eq null}">
                                            -
                                        </c:if>
                                        <c:if test="${linePenerima.syerPembilang ne null && linePenerima.syerPenyebut ne null}">
                                            ${linePenerima.syerPembilang}/${linePenerima.syerPenyebut}
                                        </c:if>
                                    </c:if>
                                    <c:if test="${linePenerima.jenis.kod ne 'PA' && linePenerima.jenis.kod ne 'PP' && linePenerima.jenis.kod ne 'WS'}">
                                        <c:if test="${linePenerima.syerPembilang eq null && linePenerima.syerPenyebut eq null}">
                                            -
                                        </c:if>
                                        <c:if test="${linePenerima.syerPembilang ne null && linePenerima.syerPenyebut ne null}">
                                            ${linePenerima.syerPembilang}/${linePenerima.syerPenyebut}
                                        </c:if>
                                    </c:if>
                                </div>
                            </display:column>
                            <c:if test="${(actionBean.permohonan.kodUrusan.kod eq 'PJADT') }">
                                <display:column title="Luas" style="width:260;">
                                    <div align="center">
                                        <c:if test="${linePenerima.jenis.kod eq 'PA' || linePenerima.jenis.kod eq 'PP' || linePenerima.jenis.kod eq 'WS'}">
                                            -
                                        </c:if>
                                        <c:if test="${linePenerima.jenis.kod ne 'PA' && linePenerima.jenis.kod ne 'PP' && linePenerima.jenis.kod ne 'WS'}">

                                            <c:if test="${linePenerima.luasTerlibat eq null && linePenerima.kodUnitLuas eq null}">
                                                -
                                            </c:if>
                                            <c:if test="${linePenerima.luasTerlibat ne null || linePenerima.kodUnitLuas ne null}">
                                                ${linePenerima.luasTerlibat}&nbsp; ${linePenerima.kodUnitLuas.nama}
                                            </c:if>
                                        </c:if>
                                    </div>
                                </display:column>
                            </c:if>
                        </c:if>
                        <c:if test="${edit}">
                            <display:column title="Kemaskini" style="width:70;">
                                <p align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                         onclick="editPenerima('${linePenerima.idPermohonanPihak}', 'penerima');
                                                 return false;"
                                         onmouseover="this.style.cursor = 'pointer';">
                                </p>
                            </display:column>
                        </c:if>
                    </display:table>
                </div>
                <c:if test="${edit}">
                    <p align="center">
                        <s:button name="add" onclick="doOpen('penerima');" value="Tambah" class="btn"/>
                        <c:if test="${fn:length(actionBean.senaraiPermohonanPihak) > 0}">
                            <s:button class="btn" value="Semak Syer" name="" id="semak" onclick="semakSyer(this.form,'penerima');"/>&nbsp;
                            <s:button class="longbtn" value="Agih Sama Rata" name="" onclick="samaRata(this.form,'penerima');"/>&nbsp;
                            <s:button name="delete" onclick="remove(this.name, 'remove', 'penerima', 'rm_penerima');" value="Hapus" class="btn"/>&nbsp;
                        </c:if>
                    </p>
                </c:if>
            </fieldset>
        </c:if>
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PCPTD' || actionBean.permohonan.kodUrusan.kod eq 'PGDMB' || actionBean.permohonan.kodUrusan.kod eq 'PCMMK'}">
            <br/>
            <fieldset class="aras1">
                <legend>Senarai Penerima Gadaian</legend>

                <div class="content" align="center">
                    <display:table style="width:90%;" class="tablecloth" name="${actionBean.senaraiPenerimaGadaian}" cellpadding="0" cellspacing="0" id="lineGadaian">
                        <c:if test="${edit}">
                            <display:column title="Pilih" style="width:40;">
                                <s:checkbox name="checkbox" id="rm_gadaian_${lineGadaian_rowNum}" value="${lineGadaian.idPermohonanPihak}"/>
                            </display:column>
                        </c:if>
                        <display:column title="Bil" style="width:40;">${lineGadaian_rowNum}</display:column>
                        <display:column  title="Nama" >
                            <a href="#"  onclick="viewMohonPihak('${lineGadaian.idPermohonanPihak}', 'penerima');
                                    return false;"><font style="text-transform:uppercase;">${lineGadaian.pihak.nama}</font></a>
                                </display:column>
                                <display:column property="noPengenalan" title="Nombor Pengenalan" />
                                <display:column title="Alamat Surat Menyurat">
                                    <c:if test="${lineGadaian.pihakCawangan ne null}">



                                ${lineGadaian.pihakCawangan.suratAlamat1}
                                <%--<c:if test="${lineGadaian.pihakCawangan.suratAlamat1 ne null && lineGadaian.pihakCawangan.suratAlamat2 ne null}"> , </c:if>--%>
                                ${lineGadaian.pihakCawangan.suratAlamat2}
                                <%--<c:if test="${lineGadaian.pihakCawangan.suratAlamat2 ne null && lineGadaian.pihakCawangan.suratAlamat3 ne null}"> , </c:if>--%>
                                ${lineGadaian.pihakCawangan.suratAlamat3}
                                <%--<c:if test="${lineGadaian.pihakCawangan.suratAlamat3 ne null && lineGadaian.pihakCawangan.suratAlamat4 ne null}"> , </c:if>--%>
                                ${lineGadaian.pihakCawangan.suratAlamat4}
                                <%--<c:if test="${lineGadaian.pihakCawangan.suratAlamat4 ne null && lineGadaian.pihakCawangan.suratPoskod ne null}">,</c:if>--%>
                                ${lineGadaian.pihakCawangan.suratPoskod}
                                <%--<c:if test="${lineGadaian.pihakCawangan.suratPoskod ne null && lineGadaian.pihakCawangan.suratNegeri.kod ne null}">,</c:if>--%>
                                <font style="text-transform:uppercase;">${lineGadaian.pihakCawangan.suratNegeri.nama}</font>
                                </c:if>
                                <c:if test="${lineGadaian.pihakCawangan eq null}">

                                ${lineGadaian.pihak.senaraiAlamatTamb[0].alamatKetiga1}
                                ${lineGadaian.pihak.senaraiAlamatTamb[0].alamatKetiga2}
                                ${lineGadaian.pihak.senaraiAlamatTamb[0].alamatKetiga3}
                                ${lineGadaian.pihak.senaraiAlamatTamb[0].alamatKetiga4}
                                ${lineGadaian.pihak.senaraiAlamatTamb[0].alamatKetigaPoskod}
                                <font style="text-transform:uppercase;">${lineGadaian.pihak.senaraiAlamatTamb[0].alamatKetigaNegeri.nama}</font>

                                <%-- ${lineGadaian.pihak.suratAlamat1}
                                <%--<c:if test="${lineGadaian.pihak.suratAlamat1 ne null && lineGadaian.pihak.suratAlamat2 ne null}"> , </c:if>--%>
                                <%--${lineGadaian.pihak.suratAlamat2}
                                <%--<c:if test="${lineGadaian.pihak.suratAlamat2 ne null && lineGadaian.pihak.suratAlamat3 ne null}"> , </c:if>--%>
                                <%-- ${lineGadaian.pihak.suratAlamat3}
                                <%--<c:if test="${lineGadaian.pihak.suratAlamat3 ne null && lineGadaian.pihak.suratAlamat4 ne null}"> , </c:if>--%>
                                <%-- ${lineGadaian.pihak.suratAlamat4}
                                <%--<c:if test="${lineGadaian.pihak.suratAlamat4 ne null && lineGadaian.pihak.suratPoskod ne null}">,</c:if>--%>
                                <%-- ${lineGadaian.pihak.suratPoskod}
                                <%--<c:if test="${lineGadaian.pihak.suratPoskod ne null && lineGadaian.pihak.suratNegeri.kod ne null}">,</c:if>--%>
                                <%--<font style="text-transform:uppercase;">${lineGadaian.pihak.suratNegeri.nama}</font>--%>
                            </c:if>
                        </display:column>
                        <display:column property="jenis.nama" title="Jenis" style="text-transform:uppercase;"/>
                        <c:if test="${edit}">
                            <display:column title="Syer Terlibat" style="width:165;">
                                <div align="center">
                                    <c:if test="${lineGadaian.jenis.kod eq 'PA' || lineGadaian.jenis.kod eq 'PP' || lineGadaian.jenis.kod eq 'WS'}">
                                        -
                                    </c:if>
                                    <c:if test="${lineGadaian.jenis.kod ne 'PA' && lineGadaian.jenis.kod ne 'PP' && lineGadaian.jenis.kod ne 'WS'}">
                                        <s:text name="senaraiPenerimaGadaian[${lineGadaian_rowNum-1}].syerPembilang" id="syer1B${lineGadaian_rowNum-1}"
                                                onblur="updateSyer('${lineGadaian.idPermohonanPihak}', '${lineGadaian_rowNum-1}')" class="pembilang2"
                                                onchange="updateSyer('${lineGadaian.idPermohonanPihak}', '${lineGadaian_rowNum-1}')" size="6" maxlength="13"
                                                onkeyup="validateNumber(this,this.value);"/> /
                                        <s:text name="senaraiPenerimaGadaian[${lineGadaian_rowNum-1}].syerPenyebut"
                                                id="syer2B${lineGadaian_rowNum-1}"
                                                onblur="updateSyer('${lineGadaian.idPermohonanPihak}', '${lineGadaian_rowNum-1}')"
                                                onchange="updateSyer('${lineGadaian.idPermohonanPihak}', '${lineGadaian_rowNum-1}')" class="penyebut2"
                                                onkeyup="validateNumber(this,this.value);" size="6" maxlength="13"/>
                                    </c:if>
                                </div>
                            </display:column>
                        </c:if>
                        <c:if test="${!edit}">
                            <display:column title="Syer Terlibat" style="width:165;">
                                <div align="center">
                                    <c:if test="${lineGadaian.jenis.kod eq 'PA' || lineGadaian.jenis.kod eq 'PP' || lineGadaian.jenis.kod eq 'WS'}">
                                        -
                                    </c:if>
                                    <c:if test="${lineGadaian.jenis.kod ne 'PA' && lineGadaian.jenis.kod ne 'PP' && lineGadaian.jenis.kod ne 'WS'}">
                                        <c:if test="${lineGadaian.syerPembilang eq null && lineGadaian.syerPenyebut eq null}">
                                            -
                                        </c:if>
                                        <c:if test="${lineGadaian.syerPembilang ne null && lineGadaian.syerPenyebut ne null}">
                                            ${lineGadaian.syerPembilang}/${lineGadaian.syerPenyebut}
                                        </c:if>
                                    </c:if>
                                </div>
                            </display:column>
                        </c:if>
                        <c:if test="${edit}">
                            <display:column title="Kemaskini" style="width:70;">
                                <p align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                         onclick="editPenerimaGadaian('${lineGadaian.idPermohonanPihak}', 'penerimaGadaian');
                                                 return false;"
                                         onmouseover="this.style.cursor = 'pointer';">
                                </p>
                            </display:column>
                        </c:if>
                    </display:table>
                </div>

                <c:if test="${edit }">
                    <p align="center">
                        <s:button name="add" onclick="doOpen('penerimaGadaian');" value="Tambah" class="btn"/>
                        <c:if test="${fn:length(actionBean.senaraiPenerimaGadaian) > 0}">
                            <s:button class="btn" value="Semak Syer" name="semak" id="semak" onclick="semakSyer(this.form,'gadaian');"/>&nbsp;
                            <s:button class="longbtn" value="Agih Sama Rata" name="" onclick="samaRata(this.form,'gadaian');"/>&nbsp
                            <s:button name="delete" onclick="remove(this.name, 'remove', 'penerima', 'rm_gadaian');" value="Hapus" class="btn"/>&nbsp;
                        </c:if>
                    </p>
                </c:if>
            </fieldset>
        </c:if>
    </s:form>    
</div>
