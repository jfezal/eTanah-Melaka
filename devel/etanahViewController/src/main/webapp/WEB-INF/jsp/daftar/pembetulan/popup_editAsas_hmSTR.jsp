<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<style type="text/css">
    td.s{
        font-weight:normal;
        color:black;
        text-align: left;
    }
    input , select {
        width: 95%;
    }
    td{
        font-size: 100% !important;
    }
    .arrow { background:transparent url(${pageContext.request.contextPath}/pub/images/arrows.png) no-repeat scroll 0px -16px; width:100%; height:16px; display:block;}
    .up { background-position:0px 0px;}

</style>

<script language="javascript">
    $(document).ready(function () {
        $('.alphanumeric').alphanumeric();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

        $('input').focus(function () {
            $(this).addClass("focus");
        });

        $('input').blur(function () {
            $(this).removeClass("focus");
        });

        $('select').focus(function () {
            $(this).addClass("focus");
        });

        $('select').blur(function () {
            $(this).removeClass("focus");
        });

    });

    function save(event, f, idH)
    {
        var q = $(f).formSerialize();
        var url = f.action + '?' + event + '&idH=' + idH;

        $.post(url, q,
                function (data) {
                    $('#page_div').html(data);
                }, 'html');
    }

    function showMe(thID) {

        $('#' + thID).toggle();
        $('.' + thID).find(".arrow").toggleClass("up");
    }

    function filterKodGunaTanah() {
        var katTanah = $("#katTanah").val();
        $.post('${pageContext.request.contextPath}/daftar/pembetulan/betul?senaraiKodGunaTanahByKatTanah&kodKatTanah=' + katTanah,
                function (data) {
                    if (data != '') {
                        $('#partialKodGunaTanah').html(data);
                        $.unblockUI();
                    }
                }, 'html');

    }

    function validateNumber(elmnt, content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function popup(url)
    {
        params = 'width=800px';
        params += ', height=500px';
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

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form name="form1" beanclass="etanah.view.stripes.nota.pembetulanSTRActionBean">
    <div id="page_div">
        <s:messages />
        <s:errors />

        <div class="subtitle">

            <fieldset class="aras1">
                <legend>
                    Maklumat Asas Hakmilik
                </legend>
                <p style="color:red">
                    *Isi ruang pembetulan kemudian tekan butang simpan.<br/>

                </p>
                <div align="center">
                    <c:if test="${hmstr}">
                        <table class="tablecloth" width="90%">
                            <tr><th colspan="3">ID Hakmilik - ${actionBean.hakmilik.idHakmilik}</th></tr>
                                    <s:hidden name = "idH" value ="${actionBean.hakmilik.idHakmilik}"/>
                            <tr><th colspan="3">Butiran Hakmilik</th></tr>
                            <tr><td colspan="3">
                            <tr><th style="width:20%">Perkara</th><th>Maklumat Lama</th><th>Maklumat Baru</th></tr>
                            <tr><td>No Tingkat:</td><td class="s">${actionBean.hakmilik.noTingkat}</td>
                                <td class="s">
                                    <s:text name="noTingkatSTR" value="${actionBean.noTingkatSTR}" />
                                <img alt='Klik Untuk Hapus' title="Hapus" border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem' id='rem_${line_rowNum}' 
                                         onmouseover="this.style.cursor = 'pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betulSTR?hapusAksr&medan=noTingkatSTR&idH=${actionBean.hakmilik.idHakmilik}');"/>
                                </td>
                            </tr>
                            <tr>
                                <td>No Petak :</td><td class="s">${actionBean.hakmilik.noPetak}</td>
                                <%--td class="s">${actionBean.noPetak}</td--%>
                                <td class="s">
                                    <s:text name="noPetakSTR" value="${actionBean.noPetak}" readonly="readonly"/>
                                </td>
                            </tr>
                            <tr><td>Petak aksesori:</td><td class="s">
                                    <c:forEach items="${actionBean.listHakmilikPetakAksesori}" var="i" >
                                        Nama : A${i.nama} <br />
                                        Lokasi : ${i.lokasi} <br />
                                        Luas :  ${i.luas}
                                        <br />
                                    </c:forEach>
                                </td>
                                <td><c:forEach items="${actionBean.permohonanPihakKemaskiniPetakAksr}" var="i" >
                                        Nama : A${i.nilaiBaru} <br />
                                        Lokasi : ${i.nilaiBerkaitan1} <br />
                                        Luas :  ${i.nilaiBerkaitan2}
                                        <img alt='Klik Untuk Kemaskini' title="Kemaskini" border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' 
                                             onmouseover="this.style.cursor = 'pointer';" 
                                             onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betulSTR?popupPetakAksr&idH=${actionBean.hakmilik.idHakmilik}&idKkini=${i.idKemaskini}');"/>
                                        <img alt='Klik Untuk Hapus' title="Hapus" border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem' id='rem_${line_rowNum}' 
                                             onmouseover="this.style.cursor = 'pointer';" 
                                             onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betulSTR?hapusAksr&medan=petakAksrSTR&idH=${actionBean.hakmilik.idHakmilik}&idKkini=${i.idKemaskini}');"/>
                                        <br />
                                    </c:forEach>
                                    <img alt='Klik Untuk Tambah' title="Tambah" border='0' src='${pageContext.request.contextPath}/pub/images/tambah.png' class='rem' id='rem_${line_rowNum}' 
                                         onmouseover="this.style.cursor = 'pointer';" 
                                         onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betulSTR?popupPetakAksr&idH=${actionBean.hakmilik.idHakmilik}');"/>
                                </td></tr>
                            </td>

                            </tr>
                            <tr><td>Unit Syer Petak:</td><td class="s">${actionBean.hakmilik.unitSyer}</td>
                                <td class="s">
                                    <s:text name="unitSyerSTR" value="unitSyerSTR" style="width:70%"/>
                                    <img alt='Klik Untuk Hapus' title="Hapus" border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem' id='rem_${line_rowNum}' 
                                         onmouseover="this.style.cursor = 'pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betulSTR?hapusAksr&medan=unitSyerSTR&idH=${actionBean.hakmilik.idHakmilik}');"/>
                                </td>
                            </tr>
                            <tr><td>No Pelan Petak :</td><td class="s">PA(B)${actionBean.hakmilik.noPelan}</td>
                                <td class="s">PA(B)<s:text name="noPelanPetakSTR" value="noPelanPetakSTR" style="width:70%"/>
                                    <img alt='Klik Untuk Hapus' title="Hapus" border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem' id='rem_${line_rowNum}' 
                                         onmouseover="this.style.cursor = 'pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betulSTR?hapusAksr&medan=noPelanPetakSTR&idH=${actionBean.hakmilik.idHakmilik}');"/>
                                </td>
                            </tr>
                            <tr><td>No Pelan Aksesori / Tambahan :</td><td class="s">${actionBean.noPelanList}</td>
                                <td><c:forEach items="${actionBean.permohonanPihakKemaskiniNoPelan}" var="i" >
                                        PA(B)${i.nilaiBaru}
                                        <img alt='Klik Untuk Hapus' title="Hapus" border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem' id='rem_${line_rowNum}' 
                                             onmouseover="this.style.cursor = 'pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betulSTR?hapusAksr&medan=noPelanSTR&idH=${actionBean.hakmilik.idHakmilik}&idKkini=${i.idKemaskini}');"/>
                                    </c:forEach>
                                    <img alt='Klik Untuk Tambah' title="Tambah" border='0' src='${pageContext.request.contextPath}/pub/images/tambah.png' class='rem' id='rem_${line_rowNum}' 
                                         onmouseover="this.style.cursor = 'pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betulSTR?popupPetakAksr&pelan=yes&idH=${actionBean.hakmilik.idHakmilik}');"/>
                                </td>
                            </tr>
                            <tr><td>Luas Petak (m²):</td><td class="s">${actionBean.hakmilik.luas}</td>
                                <td class="s">
                                    <s:text name="luasPetakSTR" value="luasPetakSTR" />
                                <img alt='Klik Untuk Hapus' title="Hapus" border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem' id='rem_${line_rowNum}' 
                                         onmouseover="this.style.cursor = 'pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betulSTR?hapusAksr&medan=luasPetakSTR&idH=${actionBean.hakmilik.idHakmilik}');"/>
                                </td>
                            </tr>
                            <tr><td>Cukai Petak (RM):</td><td class="s">${actionBean.hakmilik.cukai}</td>
                                <td class="s">
                                    <s:text name="cukaiPetakSTR" value="${actionBean.cukai}" readonly="readonly"/>
                                </td>
                            </tr>
                        </table> 

                        <tr><td>
                                <div align="center">
                                    <s:submit name="saveHmStr" value="Simpan" class="btn" />
                                    <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:self.close()"/>
                                </div>
                            </td></tr>
                        </table>
                    </c:if>
                    <c:if test="${!hmstr}">
                        <table class="tablecloth" width="70%">
                            <tr><th colspan="3">ID Hakmilik - ${actionBean.hakmilik.idHakmilikInduk}</th></tr>
                                    <s:hidden name = "idH" value ="${actionBean.hakmilik.idHakmilikInduk}"/>
                            <tr><th colspan="3">Butiran Hakmilik</th></tr>
                            <tr><td colspan="3"><table class="tablecloth">
                                        <tr><th>Perkara</th><th>Maklumat Lama</th><th>Maklumat Baru</th></tr>
                                        <tr><td>Nombor Fail:</td><td class="s">${actionBean.hakmilik.noFail}</td>
                                            <td><s:text name="noFailSTR" value="noFailSTR" onkeyup="this.value=this.value.toUpperCase();"/>
                                                <img alt='Klik Untuk Hapus' title="Hapus" border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem' id='rem_${line_rowNum}' 
                                                     onmouseover="this.style.cursor = 'pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betulSTR?hapus&medan=noFailSTR&idH=${actionBean.hakmilik.idHakmilikInduk}');"/>
                                            </td></tr>

                            </tr>
                            <tr><td>Tarikh Daftar 4K:</td><td class="s"><s:text name ="tarikhDaftr" value="${actionBean.hakmilik.tarikhDaftar}" readonly = "true" formatPattern="dd/MM/yyyy"/></td>
                                <td class="s">
                                    <s:text name="tarikhDaftarSTR" value="tarikhDaftarSTR" formatPattern="dd/MM/yyyy" class="datepicker"/>
                                    <img alt='Klik Untuk Hapus' title="Hapus" border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem' id='rem_${line_rowNum}' 
                                         onmouseover="this.style.cursor = 'pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betulSTR?hapus&medan=tarikhDaftarSTR&idH=${actionBean.hakmilik.idHakmilikInduk}');"/>
                                </td>
                        </table> </td></tr>
                        <tr onclick="showMe('mlot')" onmouseover="this.style.cursor = 'pointer';
                                this.style.text" class="mlot"><th colspan="3"><span class="arrow">Maklumat Lot</span></th></tr>
                        <tr id="mlot"><td><table>
                                    <tr><th>Perkara</th><th>Maklumat Lama</th><th>Maklumat Baru</th></tr>
                                    <tr><td>No. Lembaran Piawai :</td><td class="s">${actionBean.hakmilik.noPu}</td>
                                        <td><s:text name="nopuSTR" value="nopuSTR"/>
                                            <img alt='Klik Untuk Hapus' title="Hapus" border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem' id='rem_${line_rowNum}' 
                                                 onmouseover="this.style.cursor = 'pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betulSTR?hapus&medan=nopuSTR&idH=${actionBean.hakmilik.idHakmilikInduk}');"/>
                                        </td></tr>

                                    <tr><td>No. Buku Strata :</td><td class="s">${actionBean.hakmilik.noBukuDaftarStrata}</td>
                                        <td><s:text name="noBukuDaftarSTR" value="noBukuDaftarSTR"/>
                                            <img alt='Klik Untuk Hapus' title="Hapus" border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem' id='rem_${line_rowNum}' 
                                                 onmouseover="this.style.cursor = 'pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betulSTR?hapus&medan=noBukuDaftarSTR&idH=${actionBean.hakmilik.idHakmilikInduk}');"/>
                                        </td></tr>

                                    <tr><td>No. Skim :</td><td class="s">${actionBean.hakmilik.noSkim}</td>
                                        <td><s:text name="noSkimSTR" value="noSkimSTR"/>
                                            <img alt='Klik Untuk Hapus' title="Hapus" border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem' id='rem_${line_rowNum}' 
                                                 onmouseover="this.style.cursor = 'pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betulSTR?hapus&medan=noSkimSTR&idH=${actionBean.hakmilik.idHakmilikInduk}');"/>
                                        </td></tr>
                                    <tr><td>Jumlah Unit Syer :</td><td class="s">${actionBean.hakmilik.jumlahUnitSyer}</td>
                                        <td><s:text name="jumSyerSTR" value="jumSyerSTR"/>
                                            <img alt='Klik Untuk Hapus' title="Hapus" border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem' id='rem_${line_rowNum}' 
                                                 onmouseover="this.style.cursor = 'pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betulSTR?hapus&medan=jumSyerSTR&idH=${actionBean.hakmilik.idHakmilikInduk}');"/>
                                        </td></tr>

                                </table>
                            </td></tr>
                        <tr><td>
                                <div align="center">
                                    <s:submit name="saveBetul" value="Simpan" class="btn" />
                                    <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:self.close()"/>
                                </div>
                            </td></tr>
                        </table>
                    </c:if>
                </div>
            </fieldset>
        </div>
    </div>
</s:form>


