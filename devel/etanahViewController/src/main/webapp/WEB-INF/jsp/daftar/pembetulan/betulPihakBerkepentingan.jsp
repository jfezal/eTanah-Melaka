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




    ///
    function removePihak(val) {

        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/daftar/pembetulan/PihakBerkepentingan?deletePihak&idAtasPihak=' + val;
            $.get(url,
                    function (data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }

    <%-- function addPemohon(){

      


        var url = '${pageContext.request.contextPath}/daftar/pembetulan/PihakBerkepentingan?simpanPemohonPihak'+param;
        $.get(url,
        function(data){
            $('#page_div',opener.document).html(data);
        }, 'html');


    }--%>

    function addPemohon() {
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
        var url = '${pageContext.request.contextPath}/daftar/pembetulan/PihakBerkepentingan?simpanPemohonPihak' + param;

        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function (xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
            },
            success: function (data) {
                $('#page_div').html(data);
            }
        });
    }


    function addPihak() {

        var len = $('.nama').length;
        var param = '';

        for (var i = 1; i <= len; i++) {
            var ckd = $('#chkbox' + i).is(":checked");
            if (ckd) {
                param = param + '&idUrusan=' + $('#chkbox' + i).val();
            }
        }
        if (param == '') {
            alert('Tiada urusan.');
            return;
        }

        var idmhn = document.getElementById("idPermohonan").value;
        window.open("${pageContext.request.contextPath}/daftar/pembetulan/PihakBerkepentingan?caripihak" + param + "&idPermohonan=" + idmhn, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1200,height=600,scrollbars=yes");

    }
    function addPihak2() {

        var len = $('.nama').length;
        var param = '';

        for (var i = 1; i <= len; i++) {
            var ckd = $('#chkbox' + i).is(":checked");
            if (ckd) {
                param = param + '&idUrusan=' + $('#chkbox' + i).val();
            }
        }
        if (param == '') {
            alert('Tiada urusan.');
            return;
        }

        var idmhn = document.getElementById("idPermohonan").value;
        window.open("${pageContext.request.contextPath}/daftar/pembetulan/PihakBerkepentingan?caripihak2" + param + "&idPermohonan=" + idmhn, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1200,height=600,scrollbars=yes");

    }

    function dopopup(i) {

        var d = $('.x' + i).val();
        window.open("${pageContext.request.contextPath}/daftar/pembetulan/PihakBerkepentingan?showEditPemohon&idAtasPihak=" + d, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600,scrollbars=yes");
    }
    function viewPihakWaris(idHp) {

        window.open("${pageContext.request.contextPath}/daftar/pihak_kepentingan?PaparPihakWaris&idHp=" + idHp, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=400");
    }

    function popup(url)
    {
        params = 'width=' + screen.width;
        params += ', height=' + screen.height;
        params += ', top=0, left=0'
        params += ', fullscreen=yes';
        params += ', directories=no';
        params += ', location=no';
        params += ', menubar=no';
        params += ', resizable=no';
        params += ', scrollbars=yes';
        params += ', status=no';
        params += ', toolbar=no';
        newwin = window.open(url, 'PopUp', params);
        if (window.focus) {
            newwin.focus()
        }
        return false;
    }



</script>
<div class="subtitle displaytag">

    <s:form name="form1" beanclass="etanah.view.stripes.nota.PihakBerkepentinganActionBean">
        <s:messages/>
        <s:errors/>


        <fieldset class="aras1">

            <legend>
                <c:set var="title" value="Pihak Berkepentingan"/>
                Senarai ${title} (Rekod Ketuanpunyaan)
            </legend>
            <div class="subtitle">

                <div class="content" align="center">
                    Sila Klik pada kotak dan tekan Pilih untuk memilih ${title}

                    <display:table class="tablecloth" name="${actionBean.pihakKepentinganList}" cellpadding="0" cellspacing="0" id="line">
                        <display:column>
                            <s:checkbox name="checkbox" id="chkbox_pemohon_${line_rowNum}" value="${line.idHakmilikPihakBerkepentingan}"/>
                        </display:column>
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column property="nama" title="Nama" class="pemohon"/>
                        <display:column property="jenisPengenalan.nama" title="Pengenalan" />
                        <display:column property="noPengenalan" title="Nombor Pengenalan" />
                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                        <display:column   title="Jenis Pihak" >
                            <c:if test="${line.jenis.kod eq 'PA'}">
                                <a href="#" onclick="viewPihakWaris(${line.idHakmilikPihakBerkepentingan});
                                        return false;">${line.jenis.nama}</a>
                            </c:if>    
                            <c:if test="${line.jenis.kod ne 'PA'}">
                                ${line.jenis.nama}
                            </c:if>   
                        </display:column>
                        <display:column title="Bahagian yang dimiliki" >${line.syerPembilang}/${line.syerPenyebut}</display:column>
                        <display:column property="perserahan.idPerserahan" title="ID Perserahan" />
                        <display:column title="Tarikh Pemilikan Tanah">
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/>
                        </display:column>
                    </display:table>

                </div>
                <p>
                    <label>&nbsp;</label>
                    <s:button class="btn" value="Pilih" name="pilih" id="pilih" onclick="addPemohon();" onmouseover="this.style.cursor='pointer';"/>&nbsp;

                </p>
            </div>
            <br/>

        </fieldset>
        <br>
        <fieldset class="aras1">
            <s:hidden id="idPermohonan" name="idPermohonan" value="${actionBean.idPermohonan}"/> 
            <legend>
                Maklumat Urusan
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikUrusanListY}" cellpadding="0" cellspacing="0" id="line">
                    <display:column>
                        <s:checkbox name="checkbox" id="chkbox${line_rowNum}" value="${line.idUrusan}"/>
                    </display:column>   
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column property="idPerserahan" title="ID Perserahan" class="nama" />
                    <display:column property="idUrusan" title="ID Urusan" class="idUrusan"/>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                    <display:column property="kodUrusan.kod" title="Kod Urusan" />
                    <display:column property="kodUrusan.nama" title="Urusan" />
                    <display:column property="tarikhDaftar" title="Tarikh Daftar" format="{0,date,dd-MM-yyyy}"/>
                </display:table>

            </div>
            <p>
                <label>&nbsp;</label>
                <s:button class="btn" value="Pilih" name="pilih" id="pilih" onclick="addPihak2();" onmouseover="this.style.cursor='pointer';"/>&nbsp;
            </p>

            <br/>

        </fieldset>
        <br>
        <%--<c:if test="${actionBean.hakmilikPihak > 0}">--%>
        <fieldset class="aras1">

            <legend>
                <c:set var="title" value="Permohonan Pihak Kepentingan"/>
                Senarai ${title} (Rekod Ketuanpunyaan)
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiHakmilikPihak}" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="Bil" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.idHakmilikPihakBerkepentingan}"/>
                    </display:column>
                    <display:column property="nama" title="Nama"/>
                    <display:column property="noPengenalan" title="Nombor Pengenalan" />
                    <display:column title="Kemaskini">
                        <p align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                 onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/PihakBerkepentingan?showEditPemohon&idAtasPihak=${line.idHakmilikPihakBerkepentingan}');" onmouseover="this.style.cursor = 'pointer';">
                        </p>
                    </display:column>
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onclick="removePihak('${line.idHakmilikPihakBerkepentingan}')" onmouseover="this.style.cursor = 'pointer';">
                        </div>
                    </display:column>
                </display:table>
            </div>

            <%--  <p>
                  <label>&nbsp;</label>
                  <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addNewPemohon();"/>&nbsp;
              </p>--%>

        </fieldset>
        <%--</c:if>--%>
        <%--<c:if test="${actionBean.mohonPihak > 0}">--%>
        <fieldset class="aras1">

            <legend>
                <c:set var="title" value="Pihak Berkepentingan Terlibat Atas Urusan"/>
                Senarai ${title} 
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiMohonPihak}" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="Bil" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.idPermohonanPihak}"/>
                    </display:column>
                    <display:column property="nama" title="Nama"/>
                    <display:column property="noPengenalan" title="Nombor Pengenalan" />
                    <display:column property="permohonan.idPermohonan" title="Id Permohonan" />
                    <display:column title="Kemaskini">
                        
                        <p align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                 onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/PihakBerkepentingan?showEditMohonPihak&idPermohonanPihak=${line.idPermohonanPihak}');" onmouseover="this.style.cursor = 'pointer';">
                        </p>
                    </display:column>
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onclick="removePihak('${line.idPermohonanPihak}')" onmouseover="this.style.cursor = 'pointer';">
                        </div>
                    </display:column>
                </display:table>
            </div>

            <%--  <p>
                  <label>&nbsp;</label>
                  <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addNewPemohon();"/>&nbsp;
              </p>--%>

        </fieldset>
        <%--</c:if>--%>
        <fieldset class="aras1">

            <legend>
                <c:set var="title" value="Pemohon Atas Urusan"/>
                Senarai ${title}
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiPemohon}" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="Bil" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.idPemohon}"/>
                    </display:column>
                    <display:column property="nama" title="Nama"/>
                    <display:column property="noPengenalan" title="Nombor Pengenalan" />
                    <display:column property="permohonan.idPermohonan" title="Id Permohonan" />
                    <display:column title="Kemaskini">
                        <p align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                 onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/PihakBerkepentingan?showEditPemohonLama&idPemohon=${line.idPemohon}');" onmouseover="this.style.cursor = 'pointer';">
                        </p>
                    </display:column>
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onclick="removePihak('${line.idPemohon}')" onmouseover="this.style.cursor = 'pointer';">
                        </div>
                    </display:column>
                </display:table>
            </div>

            <%--  <p>
                  <label>&nbsp;</label>
                  <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addNewPemohon();"/>&nbsp;
              </p>--%>

        </fieldset>

        <br/>

    </s:form>

</div>
