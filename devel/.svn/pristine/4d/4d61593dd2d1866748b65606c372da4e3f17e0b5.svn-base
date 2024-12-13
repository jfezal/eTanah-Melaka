<%--
    Document   : senarai_pihak_berkepentingan
    Created on : 15-Oct-2009, 03:41:03
    Author     : md.nurfikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">




    function addWaris() {
        var len = $('.pemohon').length;
        var param = '';

        for (var i = 1; i <= len; i++) {
            var ckd = $('#chkbox_pemohon_' + i).is(":checked");
            if (ckd) {
                param = param + '&idHakmilikPihakBerkepentingan=' + $('#chkbox_pemohon_' + i).val();
            }
        }
        if (param == '') {
            alert('Tiada pihak.');
            return;
        }
        var url = '${pageContext.request.contextPath}/daftar/pembetulan/PihakBerkepentingan?cariWaris' + param;

        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
            },
            success: function(data) {
                $('#page_div').html(data);
            }
        });
    }

    function viewWaris(id) {

        window.open("${pageContext.request.contextPath}/daftar/pembetulan/PihakBerkepentingan?editWaris&idWaris=" + id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=400,scrollbars=yes");
    }

    function viewPihakWaris(idHp) {

        window.open("${pageContext.request.contextPath}/daftar/pihak_kepentingan?PaparPihakWaris&idHp=" + idHp, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=400");
    }

    function removeWaris(val) {

        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/daftar/pembetulan/PihakBerkepentingan?deleteWaris&idWaris=' + val;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }

    function pihakKongsiPopup() {
        window.open("${pageContext.request.contextPath}/daftar/pihak_kepentingan?PaparPihakKongsi", "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
    }




</script>
<div class="subtitle displaytag">

    <s:form name="form1" beanclass="etanah.view.stripes.nota.PihakBerkepentinganActionBean">
        <s:messages/>
        <s:errors/>


        <fieldset class="aras1">

            <legend>
                <c:set var="title" value="Waris"/>
                Senarai ${title}
            </legend>
            <div class="subtitle">

                <div class="content" align="center">
                    Sila Klik pada kotak dan tekan Pilih untuk memilih ${title}

                    <display:table class="tablecloth" name="${actionBean.pihakWarisList}" cellpadding="0" cellspacing="0" id="line">
                        <display:column>
                            <s:checkbox name="checkbox" id="chkbox_pemohon_${line_rowNum}" value="${line.idHakmilikPihakBerkepentingan}"/>
                        </display:column>
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column property="pihak.nama" title="Nama" class="pemohon"/>
                        <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                        <display:column   title="Jenis Pihak" >
                            <a href="#" onclick="viewPihakWaris(${line.idHakmilikPihakBerkepentingan});
        return false;">${line.jenis.nama}</a>
                        </display:column> 
                        <display:column title="Bahagian yang dimiliki" >${line.syerPembilang}/${line.syerPenyebut}</display:column>
                    </display:table>

                </div>
                <p>
                    <label>&nbsp;</label>
                    <s:button class="btn" value="Pilih" name="pilih" id="pilih" onclick="addWaris();" onmouseover="this.style.cursor='pointer';"/>&nbsp;
                    <s:button class="btn" value="Pihak Kongsi" name="pihakKongsi" id="pihakKongsi" onclick="pihakKongsiPopup();"/>

                </p>
            </div>
            <br/>

        </fieldset>
        <br>
        <c:if test="${fn:length(actionBean.hwList) > 0}">
            <fieldset class="aras1">

                <legend>
                    <c:set var="title" value="Permohonan Waris"/>
                    Senarai ${title}
                </legend>


                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.hwList}" cellpadding="0" cellspacing="0" id="line">

                        <display:column title="Bil" sortable="true">${line_rowNum}
                            <s:hidden name="x" class="x${line_rowNum -1}" value="${line.idWaris}"/>
                        </display:column>
                        <display:column property="nama" title="Nama"/>
                        <display:column property="jenisPengenalan.nama" title="Jenis Pengenalan" />
                        <display:column property="noPengenalan" title="Nombor Pengenalan" />
                        <display:column title="Bahagian yang dimiliki" >${line.syerPembilang}/${line.syerPenyebut}</display:column>
                        <display:column property="wargaNegara.nama" title="Warganegara" />
                        <display:column property="negeri.nama" title="Negeri" />
                        <display:column title="Tarikh Pemilikan Tanah">
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/>
                        </display:column>
                        <display:column title="Kemaskini">
                            <p align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                     onclick="viewWaris('${line.idWaris}');
        return false;" onmouseover="this.style.cursor = 'pointer';">
                            </p>
                        </display:column>
                        <display:column title="Hapus Kemaskini">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onclick="removeWaris('${line.idWaris}')" onmouseover="this.style.cursor = 'pointer';">
                            </div>
                        </display:column>
                    </display:table>
                </div>
            </fieldset>
        </c:if>
        <c:if test="${fn:length(actionBean.hwBetulList) > 0}">
            <fieldset class="aras1">

                <legend>
                    <c:set var="title" value="Permohonan Waris"/>
                    Senarai Pembetulan Waris
                </legend>


                <div class="content" align="center">

                    <display:table class="tablecloth" name="${actionBean.hwBetulList}" cellpadding="0" cellspacing="0" id="line">

                        <display:column title="Bil" sortable="true">${line_rowNum}
                            <s:hidden name="x" class="x${line_rowNum -1}" value="${line.idWaris}"/>

                        </display:column>
                        <display:column title="Nama">
                            ${line.betulWaris.nama}
                        </display:column>
                        <%--<display:column property="jenisPengenalan.nama" title="Jenis Pengenalan" />
                        <display:column property="noPengenalan" title="Nombor Pengenalan" />
                        <display:column title="Bahagian yang dimiliki" >${line.syerPembilang}/${line.syerPenyebut}</display:column>
                        <display:column property="wargaNegara.nama" title="Warganegara" />
                        <display:column property="negeri.nama" title="Negeri" />
                        <display:column title="Tarikh Pemilikan Tanah">
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/>
                        </display:column>--%>
                        <display:column title="Kemaskini">
                            <p align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                     onclick="viewWaris('${line.betulWaris.idWaris}');
        return false;" onmouseover="this.style.cursor = 'pointer';">
                            </p>
                        </display:column>
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onclick="removeWaris('${line.idWaris}')" onmouseover="this.style.cursor = 'pointer';">
                            </div>
                        </display:column>
                    </display:table>
                </div>
            </fieldset>
        </c:if>
        <br/>

    </s:form>

</div>
