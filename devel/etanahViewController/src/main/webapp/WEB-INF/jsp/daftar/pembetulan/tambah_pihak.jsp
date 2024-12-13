<%--
    Document   : tambah_pihak
    Created on : Feb 23, 2010, 2:05:49 PM
    Author     : wan.fairul
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>

<script language="javascript">
    function savePemohon(v)
    {
        var frm = document.form1;
        var len = $('.pemohon').length;
        var param = '';

        for (var i = 1; i <= len; i++) {
            var ckd = $('#chkbox_pemohon_' + i).is(":checked");
            if (ckd) {
                param = param + '&idHakmilikPihakBerkepentingan=' + $('#chkbox_pemohon_' + i).val();
            }
        }
        if (param == '') {
            alert('Tiada pemohon.');
            return;
        }

        var url = '${pageContext.request.contextPath}/daftar/pembetulan/betul?simpanPemohonPihak' + param + '&idPembetulan=' + v;
        frm.action = url;
        frm.submit();
    }

    function removePihak(val) {
        var frm = document.form1;
        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/daftar/pembetulan/betul?deletePihak&idPermohonanPihak=' + val;
            frm.action = url;
            frm.submit();
        }
    }
    function tambahWaris(val) {
        window.open("${pageContext.request.contextPath}/daftar/pembetulan/betul?pihakKepentinganPopup&idPembetulan=" + v, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
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

    function removePemohon(val) {
        var frm = document.form1;
        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/daftar/pembetulan/betul?deletePemohon&idPemohon=' + val;
            frm.action = url;
            frm.submit();
        }
    }

    function addNewPB(v) {
        window.open("${pageContext.request.contextPath}/daftar/pembetulan/betul?pihakKepentinganPopup&idPembetulan=" + v, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
    }

    <%--  function addNewPemohon(v){
          window.open("${pageContext.request.contextPath}/daftar/pembetulan/betul?pemohonPopup&idPembetulan="+v, "eTanah",
          "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
      }
    --%>


    function reload(v)
    {
        var frm = document.form1;
        var url = "${pageContext.request.contextPath}/daftar/pembetulan/betul?tambahPihak&idPembetulan=" + v;
        frm.action = url;
        frm.submit();

    }

    function updateSyer(idPermohonanPihak, id) {
        var s1 = $('#syer1' + id).val();
        var s2 = $('#syer2' + id).val();

        if (s1 == '' || s2 == '') {
            return;
        }

        if (isNaN(s1) && isNaN(s2)) {
            return;
        }
        var url = '${pageContext.request.contextPath}/daftar/pembetulan/betul?updateSyerMohonPihak&idPermohonanPihak=' + idPermohonanPihak
                + '&syer1=' + s1 + '&syer2=' + s2;
        ;
        $.post(url,
                function(data) {
                    $.unblockUI();
                }, 'html');

    }


    function updateSyer1(idPemohon, id) {
        var s1 = $('#syer3' + id).val();
        var s2 = $('#syer4' + id).val();

        if (s1 == '' || s2 == '') {
            return;
        }

        if (isNaN(s1) && isNaN(s2)) {
            return;
        }
        var url = '${pageContext.request.contextPath}/daftar/pembetulan/betul?updateSyerPemohon&idPemohon=' + idPemohon
                + '&syer3=' + s1 + '&syer4=' + s2;
        ;
        $.post(url,
                function(data) {
                    $.unblockUI();
                }, 'html');

    }


</script>


<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form name="form1" beanclass="etanah.view.stripes.nota.pembetulanActionBean">
    <s:hidden name="idPembetulan" value="${actionBean.idPembetulan}"/>
    <s:hidden name="kodUrusan" value="${actionBean.kodUrusan}"/>


    <s:messages />
    <s:errors />
    <c:if test="${actionBean.kodUrusan ne 'KVPT'
                  and actionBean.kodUrusan ne 'KVST'
          }">
        <fieldset class="aras1">

            <legend>
                Senarai Pihak Berkepentingan
            </legend>
            <div class="subtitle">

                <div class="content" align="center">
                    Sila Klik pada kotak dan tekan Pilih untuk memilih Pihak Berkepentingan

                    <display:table class="tablecloth" name="${actionBean.pihakKepentinganList}" cellpadding="0" cellspacing="0" id="line">
                        <display:column>
                            <s:checkbox name="checkbox" id="chkbox_pemohon_${line_rowNum}" value="${line.idHakmilikPihakBerkepentingan}"/>
                        </display:column>
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column property="nama" title="Nama" class="pemohon"/>
                        <display:column property="jenisPengenalan.kod" title="Jenis Pengenalan" />
                        <display:column property="noPengenalan" title="Nombor Pengenalan" />
                        <display:column property="jenis.nama" title="Jenis Pihak" />
                        <display:column title="Bahagian yang dimiliki" >${line.syerPembilang}/${line.syerPenyebut}</display:column>
                        <display:column title="Tarikh Pemilikan Tanah">
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/>
                        </display:column>
                    </display:table>

                </div>
                <p>
                    <label>&nbsp;</label>
                    <s:button class="btn" value="Pilih" name="pilih" id="pilih" onclick="savePemohon('${actionBean.idPembetulan}');" onmouseover="this.style.cursor='pointer';"/>&nbsp;

                </p>
            </div>
            <br/>

        </fieldset>
    </c:if>
    <fieldset class="aras1">

        <legend>
            <c:set var="title" value="Pemohon"/>
            Senarai ${title}
        </legend>


        <div class="content" align="center">
            <display:table class="tablecloth" name="${actionBean.pemohonList}" cellpadding="0" cellspacing="0" id="line">

                <display:column title="Bil" sortable="true">${line_rowNum}
                    <s:hidden name="x" class="x${line_rowNum -1}" value="${line.idPemohon}"/>
                </display:column>
                <display:column property="pihak.nama" title="Nama"/>
                <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                <display:column title="Bahagian yang diterima">
                    <div align="center">
                        <s:text name="syerPembilang1[${line_rowNum-1}]" size="5" id="syer3${line_rowNum-1}"
                                onblur="updateSyer1('${line.idPemohon}', '${line_rowNum-1}')"
                                onchange="updateSyer5('${line.idPemohon}', '${line_rowNum-1}" class="pembilang"/> /
                        <s:text name="syerPenyebut1[${line_rowNum-1}]" size="5" id="syer4${line_rowNum-1}"
                                onblur="updateSyer1('${line.idPemohon}', '${line_rowNum-1}')"
                                onchange="updateSyer5('${line.idPemohon}', '${line_rowNum-1}" class="penyebut"/>
                    </div>

                </display:column>
                


                <display:column title="Alamat" >${line.pihak.suratAlamat1}
                    <c:if test="${line.pihak.suratAlamat2 ne null}"> , </c:if>
                    ${line.pihak.suratAlamat2}
                    <c:if test="${line.pihak.suratAlamat3 ne null}"> , </c:if>
                    ${line.pihak.suratAlamat3}
                    <c:if test="${line.pihak.suratAlamat4 ne null}"> , </c:if>
                    ${line.pihak.suratAlamat4}</display:column>
                <display:column property="pihak.suratPoskod" title="Poskod" />
                <display:column property="pihak.suratNegeri.nama" title="Negeri" />
                <display:column title="Hapus">
                    <div align="center">
                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                             id='rem_${line_rowNum}' onclick="removePemohon('${line.idPemohon}')" onmouseover="this.style.cursor = 'pointer';">
                    </div>
                </display:column>
            </display:table>
        </div>



    </fieldset>
    <br/>

    <fieldset class="aras1">
        <legend>
            <c:set var="title" value="Penerima"/>
            Senarai ${title}
        </legend>


        <div class="content" align="center">
            <display:table class="tablecloth" name="${actionBean.permohonanPihakList}" cellpadding="0" cellspacing="0" id="line">

                <display:column title="Bil" sortable="true">${line_rowNum}
                    <s:hidden name="x" class="x${line_rowNum -1}" value="${line.idPermohonanPihak}"/>
                </display:column>
                <display:column property="pihak.nama" title="Nama"/>
                <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                <display:column property="jenis.nama" title="Jenis Pihak Berkepentingan" />
                <display:column title="Bahagian yang diterima">
                    <div align="center">
                        <s:text name="syerPembilang[${line_rowNum-1}]" size="5" id="syer1${line_rowNum-1}"
                                onblur="updateSyer('${line.idPermohonanPihak}', '${line_rowNum-1}')"
                                onchange="updateSyer4('${line.idPermohonanPihak}', '${line_rowNum-1}" class="pembilang"/> /
                        <s:text name="syerPenyebut[${line_rowNum-1}]" size="5" id="syer2${line_rowNum-1}"
                                onblur="updateSyer('${line.idPermohonanPihak}', '${line_rowNum-1}')"
                                onchange="updateSyer4('${line.idPermohonanPihak}', '${line_rowNum-1}" class="penyebut"/>
                    </div>

                </display:column>
                
                <display:column title="Alamat" >${line.pihak.suratAlamat1}
                    <c:if test="${line.pihak.suratAlamat2 ne null}"> , </c:if>
                    ${line.pihak.suratAlamat2}
                    <c:if test="${line.pihak.suratAlamat3 ne null}"> , </c:if>
                    ${line.pihak.suratAlamat3}
                    <c:if test="${line.pihak.suratAlamat4 ne null}"> , </c:if>
                    ${line.pihak.suratAlamat4}</display:column>
                <display:column property="pihak.suratPoskod" title="Poskod" />
                <display:column property="pihak.suratNegeri.nama" title="Negeri" />


                <display:column title="Tambah Waris">
                    <c:if test="${line.jenis.kod eq 'PA'}">
                        <div align="center">
                            <img alt='Klik Untuk Tambah' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                 id='rem_${line_rowNum}' onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?pihakKepentinganPopup&idPembetulan=${actionBean.idPembetulan}'+'&tambahWaris=' + 'true' + '&idPermohonanPihak=' + ${line.idPermohonanPihak});" onmouseover="this.style.cursor = 'pointer';">
                        </div>
                    </c:if>
                </display:column>
                  <display:column title="Kemaskini Waris">
                    <c:if test="${line.jenis.kod eq 'PA'}">
                        <div align="center">
                            <img alt='Klik Untuk Tambah' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                 id='rem_${line_rowNum}' onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?kemaskiniWarisPopap&idPembetulan=${actionBean.idPembetulan}'+'&tambahWaris=' + 'kemaskini' + '&idPermohonanPihak=' + ${line.idPermohonanPihak});" onmouseover="this.style.cursor = 'pointer';">
                        </div>
                    </c:if>
                </display:column>
                <display:column title="Hapus">
                    <div align="center">
                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                             id='rem_${line_rowNum}' onclick="removePihak('${line.idPermohonanPihak}')" onmouseover="this.style.cursor = 'pointer';">
                    </div>
                </display:column>
            </display:table>
        </div>


    </fieldset>

    <p>
    <center>
        <br>
        <br>
        <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?pihakKepentinganPopup&idPembetulan=${actionBean.idPembetulan}');"  onmouseover="this.style.cursor='pointer';"/>&nbsp;
        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
        <s:button name="" value="Klik Untuk Refresh" class="longbtn" onmouseover='this.style.cursor="pointer";' onclick="reload('${actionBean.idPembetulan}')"/>
    </center>
</p>

</s:form>


