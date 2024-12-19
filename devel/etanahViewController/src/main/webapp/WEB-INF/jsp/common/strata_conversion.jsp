<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes-security.tld" prefix="ss"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.tabs.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<link type="text/css" rel="stylesheet" href="pub/styles/tabNavList.css"/>
<style type="text/css">
    .cursor_pointer {
        cursor:pointer;
    }
</style>
<script type="text/javascript">
    /*THIS JAVASCRIPT FIX DISPLAY TAG BUG TO OPEN PROPER TAB AFTER CLICK PAGE NUMEBR ON DISPLAY TAG*/
    $(document).ready(function () {
        $("#tab_hakmilik").tabs();
        for (var i = 0; i <${fn:length(actionBean.senaraiHakmilikStrataTemp)}; i++) {
            document.getElementById("simpanPetakEdit" + i).disabled = true;
        }


        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        var a = '${actionBean.selectedTabs}';
        if (a != null) {
            $('#tab_hakmilik').tabs('select', '#' + '${actionBean.selectedTabs}');
        }
    });

    function enableSimpan(count, elmnt, inputTxt) {
        document.getElementById("simpanPetakEdit" + count).disabled = false;
    }

    function filterDaerah(kodDaerah, frm) {
        //                            var jabatan = f.elements['pguna.kodJabatan.kod'].value ;
        var url = '${pageContext.request.contextPath}/strataConversion?penyukatanBPM&daerah=' + kodDaerah;
        frm.action = url;
        frm.submit();

    }

    function down() {
        if (window.event && window.event.keyCode == 13) {
            window.event.keyCode = 9;
        }
    }


    function down2() {
        if (window.event && window.event.keyCode == 13) {
            window.event.keyCode = 9;
            var noHakmilik = document.getElementById("noHakmilik").value;
            if (noHakmilik != null) {
                search();
            }
        }
    }

    function popupParam(nama, idhakmilik) {
        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?" + "&reportName=" + nama + "&report_p_id_hakmilik=" + idhakmilik, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    }

    function search(event, frm) {
        alert("Sila tunggu sebentar.");
        var daerah = document.getElementById("daerah").value;
        var bandarPekanMukim = document.getElementById("bandarPekanMukim").value;
        var kodHakmilik = document.getElementById("kodHakmilik").value;
        var noHakmilik = document.getElementById("noHakmilik").value;
        var selectedTabs = 'indeks_strata';
        var url = '${pageContext.request.contextPath}/strataConversion?search&daerah=' + daerah + '&bandarPekanMukim=' + bandarPekanMukim + '&kodHakmilik=' + kodHakmilik + '&noHakmilik=' + noHakmilik + '&selectedTabs=' + selectedTabs;
        frm.action = url;
        frm.submit();
    }

    function find(kodDaerah, frm) {
        var url = '${pageContext.request.contextPath}/strataConversion?search';
        frm.action = url;
        frm.submit();

    }

    function down3() {
        if (window.event && window.event.keyCode == 13) {
            window.event.keyCode = 9;
        }
    }
    function filterSimpan(kodDaerah, frm) {
        var daerah = document.getElementById("daerah").value;
        var kodLot = document.getElementById("kodLot").value;
        var noLot = document.getElementById("noLot").value;
        var bandarPekanMukim = document.getElementById("bandarPekanMukim").value;
        var kodHakmilik = document.getElementById("kodHakmilik").value;
        var noHakmilik = document.getElementById("noHakmilik").value;
    <%----%>var tarikhDaftar = document.getElementById("tarikhDaftar").value;
        var noFailRujukan = document.getElementById("noFailRujukan").value;
        var noSijil = document.getElementById("noSijil").value;
        if (noSijil == '') {
            alert("Sila Masukkan No.Skim");
            $("#noSijil").focus();
            return false;
        }
        var namaPerbadananPengurusan = document.getElementById("namaPerbadananPengurusan").value;
        var alamat1 = document.getElementById("alamat1").value;
        var alamat2 = document.getElementById("alamat2").value;
        var alamat3 = document.getElementById("alamat3").value;
        var alamat4 = document.getElementById("alamat4").value;
        var poskod = document.getElementById("poskod").value;
        var kodnegeri = document.getElementById("kodnegeri").value;
        var alamatSurat1 = document.getElementById("alamatSurat1").value;
        var alamatSurat2 = document.getElementById("alamatSurat2").value;
        var alamatSurat3 = document.getElementById("alamatSurat3").value;
        var alamatSurat4 = document.getElementById("alamatSurat4").value;
        var poskodSurat = document.getElementById("poskodSurat").value;
        var negeriSurat = document.getElementById("negeriSurat").value;
        var noBukuStrata = document.getElementById("noBukuStrata").value;
        var noSyit = document.getElementById("noSyit").value;
        var jumsyer = document.getElementById("jumsyer").value;
        var selectedTabs = 'indeks_strata';
    <%--var selectedTab = ''--%>
        alert("Maklumat sedang diproses. Sila tunggu sebentar.");
    <%--var url = '${pageContext.request.contextPath}/strataConversion?penyukatanBPM&daerah=' + kodDaerah;--%>
        var url = '${pageContext.request.contextPath}/strataConversion?simpan&daerah=' + daerah + '&kodLot=' + kodLot + '&noLot=' + noLot + '&bandarPekanMukim=' + bandarPekanMukim + '&kodHakmilik=' + kodHakmilik + '&noHakmilik=' + noHakmilik + '&noFailRujukan=' + noFailRujukan + '&noSijil'
                + noSijil + '&namaPerbadananPengurusan=' + namaPerbadananPengurusan + '&alamat1=' + alamat1 + '&alamat2=' + alamat2 + '&alamat3=' + alamat3 + '&alamat4=' + alamat4 + '&poskod=' + poskod + '&kodnegeri=' + kodnegeri + '&alamatSurat1=' + alamatSurat1 + '&alamatSurat2=' + alamatSurat2 + '&alamatSurat3=' + alamatSurat3 + '&alamatSurat4=' + alamatSurat4
                + '&poskodSurat=' + poskodSurat + '&negeriSurat=' + negeriSurat + '&noBukuStrata=' + noBukuStrata + '&noSyit=' + noSyit + '&jumsyer=' + jumsyer + '&tarikhDaftar=' + tarikhDaftar + '&selectedTabs=' + selectedTabs;
        frm.action = url;
        frm.submit();

    }

    function down4() {
        if (window.event && window.event.keyCode == 13) {
            window.event.keyCode = 9;
        }
    }

    function filterPetak(kodDaerah, frm) {
        alert("Sila Semak Hakmilik Strata di tab 'Semak Petak Strata & Petak Aksesori'.");
        var daerah = document.getElementById("daerah").value;
        var kodLot = document.getElementById("kodLot").value;
        var noLot = document.getElementById("noLot").value;
        var bandarPekanMukim = document.getElementById("bandarPekanMukim").value;
        var kodHakmilik = document.getElementById("kodHakmilik").value;
        var noHakmilik = document.getElementById("noHakmilik").value;
        var kodKegunaanBangunan = document.getElementById("kodKegunaanBangunan").value;
        var noFolio = document.getElementById("noFolio").value;
        var noBangunan = document.getElementById("noBangunan").value;
        var noTingkat = document.getElementById("noTingkat").value;
        var luas = document.getElementById("luas").value;
        var syerPetak = document.getElementById("syerPetak").value;
        var noPetakMula = document.getElementById("noPetakMula").value;
        var noPetakAkhir = document.getElementById("noPetakAkhir").value;
        var menara = document.getElementById("menara").value;
        var mezanin = document.getElementById("mezanin").value;
        var tarikhDaftar = '';
        var selectedTabs = 'petak_strata';
        var url = '${pageContext.request.contextPath}/strataConversion?petak&daerah=' + daerah + '&kodLot=' + kodLot + '&noLot=' + noLot + '&bandarPekanMukim=' + bandarPekanMukim + '&kodHakmilik=' + kodHakmilik + '&noHakmilik=' + noHakmilik
                + '&noPetakAkhir=' + noPetakAkhir + '&noPetakMula=' + noPetakMula + '&selectedTabs=' + selectedTabs + '&kodKegunaanBangunan=' + kodKegunaanBangunan + '&noFolio=' + noFolio + '&noBangunan=' + noBangunan + '&noTingkat=' + noTingkat + '&luas=' + luas
                + '&syerPetak=' + syerPetak + '&menara=' + menara + '&mezanin=' + mezanin + '&tarikhDaftar=' + tarikhDaftar;
        frm.action = url;
        frm.submit();
    }

    function simpanPetak(frm) {
        var f = '${actionBean.noPetakAkhir}';
        f = parseInt(f);
        var x = '${actionBean.noPetakMula}';
        x = parseInt(x);
        var negeri = $('#negeri').val();
        var daerah = $('#daerah').val();
        var bandarPekanMukim = $('#bandarPekanMukim').val();
        var kodHakmilik = $('#kodHakmilik').val();
        var noHakmilik = $('#noHakmilik').val();
        var kodKegunaanBangunan = $('#kodKegunaanBangunan').val();
        var noPetakAkhir = $('#noPetakAkhir').val();
        var tarikhDaftar = $('#tarikhDaftar').val();
        var noTingkat = $('#noTingkat').val();
        var selectedTabs = 'petak_strata';
        for (var i = x; i <= f; i++) {
    <%--var w = parseInt(x)+parseInt(i);--%>
            var noFolio = $('#noFolio' + i).val();
            var noBangunan = $('#noBangunan' + i).val();
            var noTingkat = $('#noTingkat' + i).val();
            var syerPetak = $('#syerPetak' + i).val();
            var luas = $('#luas' + i).val();
    <%--var noPetakTanah = $('#noPetakTanah'+w).val();--%>
            var noPetak = $('#noPetak1' + i).val();
            var url = '${pageContext.request.contextPath}/strataConversion?simpanPtk&daerah=' + daerah + '&bandarPekanMukim=' + bandarPekanMukim + '&kodHakmilik=' + kodHakmilik + '&noHakmilik=' + noHakmilik + '&tarikhDaftar=' + tarikhDaftar + '&noBangunan=' + noBangunan + '&noTingkat=' + noTingkat + '&noPetak=' + noPetak + '&noFolio=' + noFolio + '&syerPetak=' + syerPetak + '&kodKegunaanBangunan=' + kodKegunaanBangunan + '&noPetakAkhir=' + noPetakAkhir + '&noTingkat=' + noTingkat + '&noPetak=' + noPetak + '&luas=' + luas + '&selectedTabs=' + selectedTabs;
            frm.action = url;
            frm.submit();
        }
    }

    function hapusSelected2(frm) {
        var length = $('.hapusSelect').length;
        var i = 0;
        var check = 0;
        $('.hapusSelect').each(function () {
            if ($('#hapusid' + i).is(":checked")) {
                var idhakmilik = $('#hapusid' + i).val();
                var daerah = $('#daerah').val();
                var bandarPekanMukim = $('#bandarPekanMukim').val();
                var kodHakmilik = $('#kodHakmilik').val();
                var noHakmilik = $('#noHakmilik').val();
                var selectedTabs = 'petak_aksesori';

//                if (confirm('Adakah anda pasti untuk menghapus Id Hakmilik ini? ' + idhakmilik)) {
                var url = '${pageContext.request.contextPath}/strataConversion?hapusSingle&idHakmilikHapus=' + idhakmilik + '&daerah=' + daerah + '&bandarPekanMukim=' + bandarPekanMukim + '&kodHakmilik=' + kodHakmilik + '&noHakmilik=' + noHakmilik + '&selectedTabs=' + selectedTabs;
                frm.action = url;
                frm.submit();
//                }
                check++;
            }
            i++;
        });
        if (check == 0) {
            alert("Sila Pilih Id Hakmilik untuk dihapuskan.");
        }
    }


    function nonNumber(elmnt, inputTxt) {
        var a = document.getElementById('bilBangunan');

        if (isNaN(a.value)) {
            alert("Nombor tidak sah.Sila masukkan Semula");
            $("#bilBangunan").focus();
            elmnt.value = RemoveNonNumeric(inputTxt);
            return;
        }
    }

    function popup(f, y, x, frm)
    {
        var negeri = $('#negeri').val();
        var daerah = $('#daerah').val();
        var bandarPekanMukim = $('#bandarPekanMukim').val();
        var kodHakmilik = $('#kodHakmilik').val();
        var noHakmilik = $('#noHakmilik').val();
        var idHakmilikEdit = f;

        window.open("${pageContext.request.contextPath}/strataConversion?petakAksesoriPopup&idHakmilikEdit=" + idHakmilikEdit + '&bandarPekanMukim=' + bandarPekanMukim + '&noHakmilik=' + noHakmilik + '&kodHakmilik=' + kodHakmilik + '&daerah=' + daerah, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,scrollbars=1,width=910,height=600");
    }
    function popup2(f)
    {
        var idHakmilikInduk = f;

        window.open("${pageContext.request.contextPath}/strataConversion?petakAksesoriPopupView&idHakmilikInduk=" + idHakmilikInduk, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,scrollbars=1,width=1300,height=700");
    }
    function popupEdit(f)
    {
        var idHakmilik = f;

        window.open("${pageContext.request.contextPath}/strataConversion?editPetak&idHakmilik=" + idHakmilik, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,scrollbars=1,width=1300,height=700");
    }
    <%--function hapus(f)
    {
        alert("hapus" + f);
        var idHakmilikHapus = f;
        var url = '${pageContext.request.contextPath}/strataConversion?hapusSingle&idHakmilikHapus='+ idHakmilikHapus;

            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');


        }--%>
    function NoPrompt()
    {
        allowPrompt = false;
    }
    function hapus1(x, f, frm)
    {
        var daerah = $('#daerah').val();
        var bandarPekanMukim = $('#bandarPekanMukim').val();
        var kodHakmilik = $('#kodHakmilik').val();
        var noHakmilik = $('#noHakmilik').val();
        var selectedTabs = 'petak_aksesori';

        if (confirm('Adakah anda pasti untuk menghapus Id Hakmilik ini? ' + x)) {
            var url = '${pageContext.request.contextPath}/strataConversion?hapusSingle&idHakmilikHapus=' + x + '&daerah=' + daerah + '&bandarPekanMukim=' + bandarPekanMukim + '&kodHakmilik=' + kodHakmilik + '&noHakmilik=' + noHakmilik + '&selectedTabs=' + selectedTabs;
            frm.action = url;
            frm.submit();
        }

    }
    function simpanEdit(x, y, f, frm)
    {
        var noTingkatEdit = $('#noTingkatEdit' + y).val();
        var noPelanEdit = $('#noPelanEdit' + y).val();
        var unitSyerEdit = $('#unitSyerEdit' + y).val();
        var luasEdit = $('#luasEdit' + y).val();
        var menara = $('#menaraEdit' + y).val();
        var mezanin = $('#mezaninEdit' + y).val();
        var selectedTabs = 'petak_aksesori';

        var url = '${pageContext.request.contextPath}/strataConversion?simpanPetakEdit&idHakmilikEdit=' + x + '&noTingkatEdit=' + noTingkatEdit + '&noPelanEdit=' + noPelanEdit + '&unitSyerEdit=' + unitSyerEdit + '&luasEdit=' + luasEdit + '&selectedTabs=' + selectedTabs
                + '&menaraEdit=' + menara + '&mezaninEdit=' + mezanin;
        frm.action = url;
        frm.submit();

    }
    function edit(x, f, frm)
    {
        var daerah = $('#daerah').val();
        var bandarPekanMukim = $('#bandarPekanMukim').val();
        var kodHakmilik = $('#kodHakmilik').val();
        var noHakmilik = $('#noHakmilik').val();
        var selectedTabs = 'petak_aksesori';

        var url = '${pageContext.request.contextPath}/strataConversion?editPetak&idHakmilik=' + x + '&daerah=' + daerah + '&bandarPekanMukim=' + bandarPekanMukim + '&kodHakmilik=' + kodHakmilik + '&noHakmilik=' + noHakmilik + '&selectedTabs=' + selectedTabs;
        frm.action = url;
        frm.submit();

    }
    function daftarHakmilik(f, frm)
    {
        var daerah = $('#daerah').val();
        var bandarPekanMukim = $('#bandarPekanMukim').val();
        var kodHakmilik = $('#kodHakmilik').val();
        var noHakmilik = $('#noHakmilik').val();
        var selectedTabs = 'petak_aksesori';

        var url = '${pageContext.request.contextPath}/strataConversion?daftarH&daerah=' + daerah + '&bandarPekanMukim=' + bandarPekanMukim + '&kodHakmilik=' + kodHakmilik + '&noHakmilik=' + noHakmilik + '&selectedTabs=' + selectedTabs;
        frm.action = url;
        frm.submit();

    }

    function sss(f, frm) {
        var daerah = $('#daerah').val();
        var bandarPekanMukim = $('#bandarPekanMukim').val();
        var kodHakmilik = $('#kodHakmilik').val();
        var noHakmilik = $('#noHakmilik').val();
        var selectedTabs = 'petak_aksesori';

        var url = '${pageContext.request.contextPath}/strataConversion?search&daerah=' + daerah + '&bandarPekanMukim=' + bandarPekanMukim + '&kodHakmilik=' + kodHakmilik + '&noHakmilik=' + noHakmilik + '&selectedTabs=' + selectedTabs;
        frm.action = url;
        frm.submit();
    <%--$("#filter-bar .current").attr("href");--%>
    }

    function copyAdd() {
        if ($('input[name=checkAlamat]').is(':checked')) {
            $('#alamatSurat1').val($('#alamat1').val());
            $('#alamatSurat2').val($('#alamat2').val());
            $('#alamatSurat3').val($('#alamat3').val());
            $('#alamatSurat4').val($('#alamat4').val());
            $('#poskodSurat').val($('#poskod').val());
            $('#negeriSurat').val($('#kodnegeri').val());
        } else {
            $('#alamatSurat1').val('');
            $('#alamatSurat2').val('');
            $('#alamatSurat3').val('');
            $('#alamatSurat4').val('');
            $('#poskodSurat').val('');
            $('#negeriSurat').val('');
        }
    }

    function doPopup(nopelan) {
        // alert(daerah+ seksyen+ mukim+ noLot+ jenispelan);
        var url = '${pageContext.request.contextPath}/strataConversion?viewPelan&nopelan=' + nopelan;
        window.open(url, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=890,height=600,scrollbars=yes");
    }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<s:form beanclass="etanah.view.stripes.common.strataConversion" name="form">
    <s:messages />
    <s:errors />
    <div id="aa">
        <div id="tab_hakmilik">
            <ul>
                <li><a href="#indeks_strata" id="tab1">Indeks Strata</a></li>
                <li><a href="#petak_strata" id="tab2">Kemasukan Baru Petak Strata</a></li>
                <li><a href="#petak_aksesori" id="tab3">Semak Petak Strata & Petak Aksesori</a></li>
                <li><a href="#upload_pelan" id="tab4">Muat Naik Pelan</a></li>
                <li><a href="#cetak_borang" id="tab5">Cetakan Borang Versi 0</a></li>

            </ul>
            <div id="indeks_strata">


                <fieldset>
                    <font size="2"><b>
                        <legend>Indeks Strata</legend>
                        <br><br>

                        <center>
                            <table style="font-size: 13px">
                                <tr>
                                    <th align="right">Negeri :</th>
                                    <th>
                                        <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                                            <s:text name= "NeGERI" value="Melaka" size="15" onkeydown="return down(this, event);" readonly="true"/>
                                        </c:if>
                                        <c:if test="${actionBean.kodNegeri eq 'negeri'}">
                                            <s:text name= "NeGERI" value="Negeri Sembilan" size="15" onkeydown="return down(this, event);" readonly="true"/>
                                        </c:if>
                                    </th>
                                    <th align="right"><font color="red"><em>*</em></font>Daerah :</th>
                                    <th>
                                        <s:select name="daerah" id="daerah" style="width:210px;" onchange="filterDaerah(this.value,this.form);" onkeydown="return down(this, event);">
                                            <s:option value="">--Sila Pilih--</s:option>
                                            <%--<s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"  />--%>
                                            <c:forEach items="${listUtil.senaraiKodDaerah}" var="i" >
                                                <s:option value="${i.kod}">${i.kod} - ${i.nama}</s:option>
                                            </c:forEach>
                                        </s:select>
                                    </th>
                                    <th align="right"><font color="red"><em>*</em></font>Mukim/Bandar/Pekan :</th>
                                    <th>
                                        <s:select name="bandarPekanMukim" id="bandarPekanMukim" value="${actionBean.senaraiHakmilik[0].bandarPekanMukim.kod}" style="width:210px;" onkeydown="return down(this, event);">
                                            <s:option value="">--Sila Pilih--</s:option>
                                            <%--<s:options-collection collection="${actionBean.senaraiBPM}" label="nama" value="kod" sort="nama" />--%>
                                            <c:forEach items="${actionBean.senaraiBPM}" var="i" >
                                                <s:option value="${i.kod}">${i.nama}</s:option>
                                            </c:forEach>
                                        </s:select>
                                    </th>
                                    <th align="right"><font color="red"><em>*</em></font>Jenis Hakmilik :</th>
                                    <th>
                                        <s:select name="kodHakmilik" id="kodHakmilik" onkeydown="return down(this, event);">
                                            <s:option value="">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodHakmilikTetapMelaka}" label="nama" value="kod"/>
                                        </s:select>
                                    </th>
                                    <th align="right"><font color="red"><em>*</em></font>Nombor Hakmilik :</th>
                                    <th>
                                        <s:text name="noHakmilik" id="noHakmilik" maxlength="8"/>
                                    </th>
                                </tr>
                            </table>
                            <s:hidden name="selectedTabs" id="selectedTabs"/>
                            <s:button class="longbtn" name="cari" value="Cari" id="cari" onclick="search(this.value,this.form);"/>
                        </center>
                        <br>
                        <div id="detail">
                            <c:if test="${details}">
                                <center>
                                    <table style="font-size: 13px">
                                        <tr>
                                            <th align="right">Kod Lot :</th>
                                            <th>
                                                <s:select name="kodLot" id="kodLot" value="${actionBean.senaraiHakmilik[0].lot.kod}" onkeydown="return down(this, event);">
                                                    <s:option value="">Sila Pilih</s:option>
                                                    <s:options-collection collection="${listUtil.senaraiKodLot}" label="nama" value="kod"/>
                                                </s:select>
                                            </th>
                                            <th align="right">No Lot :</th>
                                            <th>
                                                <s:text name="noLot" id="noLot" maxlength="8" value="${actionBean.senaraiHakmilik[0].noLot}" onblur="javascript:nonNumber(this, this.value);" onkeydown="return down(this, event);"/>
                                            </th>
                                            <th align="right">Unit Luas :</th>
                                            <th>
                                                <s:select name="kodLuas" id="kodLuas" value="${actionBean.senaraiHakmilik[0].kodUnitLuas.kod}" onkeydown="return down(this, event);">
                                                    <s:option value="">Sila Pilih</s:option>
                                                    <c:forEach items="${listUtil.senaraiKodUOMByLuas}" var="i" >
                                                        <s:option value="${i.kod}">${i.kod} - ${i.nama}</s:option>
                                                    </c:forEach>
                                                </s:select>
                                            </th>
                                            <th align="right">Luas :</th>
                                            <th>
                                                <s:text name="noLuas" id="noLuas" maxlength="8" value="${actionBean.senaraiHakmilik[0].luas}" onblur="javascript:nonNumber(this, this.value);" onkeydown="return down(this, event);"/>
                                            </th>
                                        </tr>
                                    </table>
                                </center>

                                <table style="font-size: 13px">
                                    <%----%>
                                    <c:if test="${actionBean.versi eq '1' && actionBean.senaraiHakmilikStrata[0].kodStatusHakmilik.kod eq 'T'}">
                                        <%-- untuk petak wujud dari urusan pecah bahagi bngn di etanah, versi 1, status hakmilik T--%>
                                    <tr>
                                        <th align="right">Tarikh Daftar :</th>
                                        <th align="left"><s:text name="tarikhDaftar" id="tarikhDaftar" class="datepicker" formatPattern="dd/MM/yyyy" size="30" onkeydown="return down(this, event);"/></th>
                                    </tr>
                                    </c:if>
                                    <s:hidden name="tarikhDaftar" id="tarikhDaftar"/>
                                    <tr>
                                        <th align="right">No Fail Rujukan :</th>
                                        <th align="left"><s:text name="noFailRujukan" id="noFailRujukan" size="30" maxlength="50" value="${actionBean.senaraiHakmilik[0].noFail}" onkeydown="return down(this, event);"/></th>
                                        <th align="right">No Sijil (No Skim) :</th>
                                        <th align="left"><s:text name="noSijil" id="noSijil" size="30" maxlength="9" value="${actionBean.senaraiHakmilik[0].noPu}" onkeydown="return down(this, event);"/></th>
                                    </tr>
                                    <tr>
                                        <th align="right">Nama Perbadanan Pengurusan :</th>
                                        <th><s:text name="namaPerbadananPengurusan" id="namaPerbadananPengurusan" maxlength="250" size="120" onkeydown="return down(this, event);"/></th>
                                    </tr>
                                    <tr>
                                        <th align="right">Alamat Perbadanan Pengurusan :</th>
                                        <th><s:text name="alamat1" id="alamat1" size="120" maxlength="60" onkeydown="return down(this, event);"/></th>
                                    </tr>
                                    <tr>
                                        <th></th>
                                        <th><s:text name="alamat2" id="alamat2" size="120" maxlength="60" onkeydown="return down(this, event);"/></th>
                                    </tr>
                                    <tr>
                                        <th></th>
                                        <th><s:text name="alamat3" id="alamat3" size="120" maxlength="60" onkeydown="return down(this, event);"/></th>
                                    </tr>
                                    <tr>
                                        <th></th>
                                        <th><s:text name="alamat4" id="alamat4" size="120" maxlength="60" onkeydown="return down(this, event);"/></th>
                                    </tr>
                                    <tr>
                                        <th align="right">Poskod :</th>
                                        <th align="left"><s:text name="poskod" id="poskod" maxlength="5" onkeydown="return down(this, event);"/></th>
                                        <th  align="right">Negeri :</th>
                                        <th align="left"><s:select name="kodnegeri" id="kodnegeri" onkeydown="return down(this, event);">
                                                <s:option value="">Sila Pilih</s:option>
                                                <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
                                            </s:select>
                                        </th>
                                    </tr>
                                    <tr>
                                        <th align="right">
                                            <input type="checkbox" id="checkAlamat" name="checkAlamat" onclick="copyAdd();"/>
                                        </th>
                                        <th align="left">
                                            <font color="red">Alamat Penyampaian Dokumen sama dengan alamat Perbadanan Pengurusan.</font>
                                        </th>
                                    </tr>
                                    <tr>
                                        <th align="right">Alamat Penyampaian Dokumen :</th>
                                        <th><s:text name="alamatSurat1" id="alamatSurat1" size="120" maxlength="60" onkeydown="return down(this, event);"/></th>
                                    </tr>
                                    <tr>
                                        <th></th>
                                        <th><s:text name="alamatSurat2" id="alamatSurat2" size="120" maxlength="60" onkeydown="return down(this, event);"/></th>
                                    </tr>
                                    <tr>
                                        <th></th>
                                        <th><s:text name="alamatSurat3" id="alamatSurat3" size="120" maxlength="60" onkeydown="return down(this, event);"/></th>
                                    </tr>
                                    <tr>
                                        <th></th>
                                        <th><s:text name="alamatSurat4" id="alamatSurat4" size="120" maxlength="60" onkeydown="return down(this, event);"/></th>
                                    </tr>
                                    <tr>
                                        <th align="right">Poskod :</th>
                                        <th align="left"><s:text name="poskodSurat" id="poskodSurat" maxlength="5" onkeydown="return down(this, event);"/></th>
                                        <th align="right">Negeri :</th>
                                        <th align="left"><s:select name="negeriSurat" id="negeriSurat" onkeydown="return down(this, event);">
                                                <s:option value="">Sila Pilih</s:option>
                                                <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
                                            </s:select>
                                        </th>
                                    </tr>
                                    <tr>
                                        <th align="right">No Buku Pendaftaran Strata :</th>
                                        <th align="left"><s:text name="noBukuStrata" id="noBukuStrata" maxlength="20" size="30"/></th>
                                            <%-- <th align="right">No Buku Strata Asal :</th>
                                             <th align="left"><s:text name="noBukuStrataAsal" id="noBukuStrataAsal" size="30" readonly="true"/></th>--%>
                                    </tr>
                                    <tr>
                                        <th align="right">No Lembar Piawai (No Syit) :</th>
                                        <th align="left"><s:text name="noSyit" id="noSyit" maxlength="30" size="35"/></th>
                                    </tr>
                                    <tr>
                                        <th align="right">Jumlah Unit Syer :</th>
                                        <th align="left"><s:text name="jumsyer" id="jumsyer" maxlength="7" size="35"/></th>
                                    </tr>
                                    <c:if test="${actionBean.senaraiHakmilikStrata[0].kodStatusHakmilik.kod eq 'S'}">
                                    <tr>
                                        <th align="right">Status Hakmilik :</th>
                                        <th align="left"><s:select name="stsHakmilik" id="stsHakmilik" value="${actionBean.senaraiHakmilikStrata[0].kodStatusHakmilik.kod}" onkeydown="return down(this, event);">
                                                         <s:option value="">Sila Pilih</s:option>
                                                         <s:options-collection collection="${listUtil.senaraiKodStatusHakmilik}" label="nama" value="kod"/>
                                                         </s:select>
                                        </th>
                                    </tr>   
                                    </c:if>
                                </table>
                                <center>

                                    <c:if test="${actionBean.versi eq '0'}">
                                        <s:button class="longbtn" name="simpan" value="Simpan" id="simpan" onclick="filterSimpan(this.value,this.form);"/>
                                    </c:if>
                                    <c:if test="${actionBean.versi eq '1' && actionBean.senaraiHakmilikStrata[0].kodStatusHakmilik.kod eq 'T'}">
                                        <%-- untuk petak wujud dari urusan pecah bahagi bngn di etanah, versi 1, status hakmilik T--%>
                                        <s:button class="longbtn" name="simpan" value="Simpan" id="simpan" onclick="filterSimpan(this.value,this.form);"/>
                                    </c:if>
                                </center>
                            </c:if>
                        </div>
                    </b></font>
                    <br>
                </fieldset>


            </div>

            <div id="petak_strata">
                <%--<%@ include file="/WEB-INF/jsp/common/petakStrata.jsp" %>--%>
                <fieldset><font color="blue"><b>Jana Petak</b></font>
                    <s:hidden name="negeri" id="negeri"/>
                    <s:hidden name="daerah" id="daerah"/>
                    <s:hidden name="bandarPekanMukim" id="bandarPekanMukim"/>
                    <s:hidden name="kodHakmilik" id="kodHakmilik"/>
                    <s:hidden name="noHakmilik" id="noHakmilik"/>
                    <br>
                    <center>
                        <table>
                            <font color="red">Sila Isikan Maklumat Di Bawah Dan Klik Butang 'Simpan Petak'</font>
                            <tr>
                                <td>Tujuan / Maksud</td><td>:</td>
                                <td><s:select name="kodKegunaanBangunan" id="kodKegunaanBangunan" value="${actionBean.senaraiHakmilikStrata[0].kodKegunaanBangunan.kod}">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodKegunaanBangunan}" label="nama" value="kod"/>
                                    </s:select>
                                </td>
                            </tr>
                            <tr>
                                <td>Menara (Jika ada)</td><td>:</td>
                                <td><s:text name="menara" id="menara" onkeydown="return down(this, event);"/></td>
                            </tr>
                            <tr>
                                <td>Mezanin (Jika ada)</td><td>:</td>
                                <td><s:text name="mezanin" id="mezanin" onkeydown="return down(this, event);"/></td>
                            </tr>
                            <tr>
                                <td>No Folio (PA(B))</td><td>:</td>
                                <td><s:text name="noFolio" id="noFolio" onkeydown="return down(this, event);"/></td>
                            </tr>
                            <tr>
                                <td>No Bangunan</td><td>:</td>
                                <td><s:text name="noBangunan" id="noBangunan" onkeydown="return down(this, event);"/></td>
                            </tr>
                            <tr>
                                <td>No Tingkat</td><td>:</td>
                                <td><s:text name="noTingkat" id="noTingkat" onkeydown="return down(this, event);"/></td>
                            </tr>
                            <tr>
                                <td>No Petak Mula</td><td>:</td>
                                <td><s:text name="noPetakMula" id="noPetakMula" onkeydown="return down(this, event);"/></td>
                            </tr>
                            <tr>
                                <td>No Petak Akhir</td><td>:</td>
                                <td><s:text name="noPetakAkhir" id="noPetakAkhir" onkeydown="return down(this, event);"/></td>
                            </tr>
                            <tr>
                                <td>Syer Setiap Petak (Jika sama)</td><td>:</td>
                                <td><s:text name="syerPetak" id="syerPetak"/></td>
                            </tr>
                            <tr>
                                <td>Luas (Jika sama)</td><td>:</td>
                                <td><s:text name="luas" id="luas"/></td>
                            </tr>
                        </table>
                        <c:if test="${actionBean.versi eq '0'}">
                            <s:button class="longbtn" name="kemaskini" value="Simpan Petak" id="kemaskini" onclick="filterPetak(this.value,this.form);"/>
                        </c:if>
                        <c:if test="${actionBean.versi eq '1' && actionBean.senaraiHakmilikStrata[0].kodStatusHakmilik.kod eq 'T'}">
                             <%-- untuk petak wujud dari urusan pecah bahagi bngn di etanah, versi 1, status hakmilik T--%>
                             <s:button class="longbtn" name="kemaskini" value="Simpan Petak" id="kemaskini" onclick="filterPetak(this.value,this.form);"/>
                        </c:if>
                </fieldset>
                <br><br><p><br>
            </div>
            <div id="petak_aksesori">
                <font color="red" size="3"><b>Arahan : Sila klik 'Daftar' untuk daftar Hakmilik Strata yang baharu.</b></font>
                <br>
                <br>
                <br>
                <font color="blue" size="3"><b>Id Hakmilik Ini Mempunyai ${fn:length(actionBean.senaraiHakmilikStrataTemp)} Petak Strata.</b></font>
                <br>
                <font color="blue" size="3"><b>Jumlah Unit Syer = ${actionBean.senaraiHakmilikStrataTemp[0].jumlahUnitSyer}</b></font>
                <br>
                <br>
                <br>
                <fieldset><font color="blue"><b>Petak Strata dan Petak Aksesori</b></font>
                    <br><br>
                    <c:set value="0" var="count"/>
                    <display:table style="width:90%" class="tablecloth" name="${actionBean.senaraiHakmilikStrataTemp}" cellpadding="0" cellspacing="0" id="line"> <%--requestURI="/strataConversion" pagesize="50" --%>
                        <display:column title="Id Hakmilik Strata">${line.idHakmilik}</display:column>
                        <display:column title="Menara"><s:text name="menara${count}" id="menaraEdit${count}" value="${line.menara}" onchange="javascript:enableSimpan('${count}',this, this.value);"/></display:column>
                        <display:column title="Mezanin"><s:text name="mezanin${count}" id="mezaninEdit${count}" value="${line.mezanin}" onchange="javascript:enableSimpan('${count}',this, this.value);"/></display:column>
                        <display:column title="No Pelan">PA(B) <s:text name="noPelanEdit${count}" id="noPelanEdit${count}" value="${line.noPelan}" onchange="javascript:enableSimpan('${count}',this, this.value);"/></display:column>
                        <display:column title="No Bangunan">${line.noBangunan}</display:column>
                        <display:column title="No Tingkat"><s:text name="noTingkatEdit${count}" id="noTingkatEdit${count}" value="${line.noTingkat}" onchange="javascript:enableSimpan('${count}',this, this.value);"/></display:column>
                        <display:column title="No Petak">${line.noPetak}</display:column>
                        <display:column title="Syer"><s:text name="unitSyerEdit${count}" id="unitSyerEdit${count}" value="${line.unitSyer}" onchange="javascript:enableSimpan('${count}',this, this.value);"/></display:column>
                        <display:column title="Luas (M2)"><s:text name="luasEdit${count}" id="luasEdit${count}" value="${line.luas}" onchange="javascript:enableSimpan('${count}',this, this.value);"/></display:column>
                        <c:if test="${line.noVersiDhde < 1}">
                            <display:column title="Tindakan" style="width:60%">
                                <s:button class="longbtn" name="simpanPetakEdit" value="Simpan" id="simpanPetakEdit${count}" onclick="simpanEdit('${line.idHakmilik}','${count}',this.value,this.form);"/>
                                <s:button class="longbtn" name="petakAksesori" value="Kemaskini" id="petakAksesori${count}" onclick="popup('${line.idHakmilik}','${count}',this.value,this.form);"/>
                                <%--<img alt='Klik Untuk Simpan' border='0' src='${pageContext.request.contextPath}/pub/images/save.gif' class='rem'
                                       id="simpanPetakEdit${count}" onclick="simpanEdit('${line.idHakmilik}','${count}',this.value,this.form);"
                                       title="Klik Untuk Simpan" name="simpanPetakEdit"
                                       onmouseover="this.style.cursor='pointer';">--%>
                                <%--<img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                     id="petakAksesori${count}" title="Klik Untuk Kemaskini" name="petakAksesori"
                                     onclick="popup('${line.idHakmilik}','${count}',this.value,this.form);"
                                     onmouseover="this.style.cursor='pointer';">--%>
                                <s:button class="longbtn" name="hapus" value="Hapus" id="hapus" onclick="hapus1('${line.idHakmilik}',this.value,this.form);"/>
                                <%-- <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem' name="hapus"
                                                  id="hapus" onclick="hapus1('${line.idHakmilik}',this.value,this.form);" onmouseover="this.style.cursor = 'pointer';" >--%>
                                <input type="checkbox" id="hapusid${count}" name="hapusSelected" value ="${line.idHakmilik}" class="hapusSelect"/>
                            </display:column>
                        </c:if>
                        <c:if test="${line.noVersiDhde eq '1' && line.kodStatusHakmilik.kod eq 'T'}"> 
                            <%-- untuk petak wujud dari urusan pecah bahagi bngn di etanah, versi 1, status hakmilik T--%>
                            <display:column title="Tindakan." style="width:60%">
                                <s:button class="longbtn" name="simpanPetakEdit" value="Simpan" id="simpanPetakEdit${count}" onclick="simpanEdit('${line.idHakmilik}','${count}',this.value,this.form);"/>
                                <s:button class="longbtn" name="petakAksesori" value="Kemaskini" id="petakAksesori${count}" onclick="popup('${line.idHakmilik}','${count}',this.value,this.form);"/>
                                <input type="checkbox" id="hapusid${count}" name="hapusSelected" value ="${line.idHakmilik}" class="hapusSelect"/>
                            </display:column>
                        </c:if>
                        <c:set value="${count +1}" var="count"/>
                    </display:table>
                    <br />
                    <c:if test="${actionBean.versi eq '0'}">
                        <center>
                            Sila pastikan semakan pada borang versi 0 telah dibuat sebelum klik butang 'Daftar'!
                            <br />
                            <br />
                            <s:button class="longbtn" name="hapus2" value="Hapus" id="hapus2" onclick="hapusSelected2(this.form);"/>
                            <s:button class="longbtn" name="reload" value="Petak Aksesori" id="reload" onclick="popup2('${actionBean.senaraiHakmilik[0].idHakmilik}');"/><%--onclick="sss(this.value,this.form);"--%>
                            <s:button class="longbtn" name="daftar" value="Daftar" id="daftar" onclick="daftarHakmilik(this.value,this.form);"/>
                        </center>
                        <%--<s:button class="longbtn" name="reload" value="reload" id="reload" onclick="refreshpage(this.value,this.form);"/>--%>
                    </c:if>
                    <c:if test="${line.noVersiDhde eq '1' && line.kodStatusHakmilik.kod eq 'T'}">
                        <%-- untuk petak wujud dari urusan pecah bahagi bngn di etanah, versi 1, status hakmilik T--%>
                        <center>
                            <br />
                            <br />
                            <s:button class="longbtn" name="hapus2" value="Hapus" id="hapus2" onclick="hapusSelected2(this.form);"/>
                            <s:button class="longbtn" name="reload" value="Petak Aksesori" id="reload" onclick="popup2('${actionBean.senaraiHakmilik[0].idHakmilik}');"/><%--onclick="sss(this.value,this.form);"--%>
                        </center>
                    </c:if>
                </fieldset>
            </div>
            <div id="cetak_borang">
                <br><br>
                <display:table style="width:90%" class="tablecloth" name="${actionBean.senaraiHakmilik}" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="Id Hakmilik Induk">${line.idHakmilik}</display:column>
                    <display:column title="Tindakan" style="width:60%">
                        Borang 2K Indeks Daftar Strata(2K)
                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                             onclick="popupParam('UTILITIB2K_MLK', '${line.idHakmilik}');" onmouseover="this.style.cursor = 'pointer';"><br>
                        Pelan Borang 2K Indeks Daftar Strata
                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                             onclick="popupParam('UTILITIP2K_MLK', '${line.idHakmilik}');" onmouseover="this.style.cursor = 'pointer';"><br>
                        Borang 3K Penyata Daftar Strata(3K)
                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                             onclick="popupParam('UTILITIB3K_MLK', '${line.idHakmilik}');" onmouseover="this.style.cursor = 'pointer';"><br>
                    </display:column>
                </display:table>
                <br><br>
                <display:table style="width:90%" class="tablecloth" name="${actionBean.senaraiHakmilikStrataDaftar}" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="Id Hakmilik Strata">${line.idHakmilik}</display:column>
                    <display:column title="Tindakan" style="width:60%">
                        Borang 4K(DHDK)
                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                             onclick="popupParam('UTILITIB4KDHDK_MLK', '${line.idHakmilik}');" onmouseover="this.style.cursor = 'pointer';"><br>
                        Borang 4K(DHKK)
                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                             onclick="popupParam('UTILITIB4KDHKK_MLK', '${line.idHakmilik}');" onmouseover="this.style.cursor = 'pointer';"><br>
                        Borang SK(DHDK)
                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                             onclick="popupParam('UTILITIBSKDHDK_MLK', '${line.idHakmilik}');" onmouseover="this.style.cursor = 'pointer';"><br>
                        Borang SK(DHKK)
                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                             onclick="popupParam('UTILITIBSKDHKK_MLK', '${line.idHakmilik}');" onmouseover="this.style.cursor = 'pointer';"><br>
                        Pelan Borang S(K)
                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                             onclick="popupParam('UTILITIPSK_MLK', '${line.idHakmilik}');" onmouseover="this.style.cursor = 'pointer';"><br>
                    </display:column>
                </display:table>
                <%--<c:if test="${fn:length(actionBean.senaraiHakmilikStrataProv >0)}">
                --%><br><br>
                Bangunan Sementara
                <display:table style="width:90%" class="tablecloth" name="${actionBean.senaraiHakmilikStrataProv}" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="Id Hakmilik">${line.idHakmilikInduk}</display:column>
                    <display:column title="No. Bangunan">${line.noBangunan}</display:column>
                    <display:column title="Tindakan" style="width:60%">
                        Borang 4AK(DHDK)
                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                             onclick="popupParam('UTILITIB4AKDHDK_MLK', '${line.idHakmilik}');" onmouseover="this.style.cursor = 'pointer';"><br>
                        Borang 4AK(DHKK)
                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                             onclick="popupParam('UTILITIB4AKDHKK_MLK', '${line.idHakmilik}');" onmouseover="this.style.cursor = 'pointer';"><br>
                        Borang SK(DHDK)
                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                             onclick="popupParam('UTILITIBSKDHDK_MLK', '${line.idHakmilik}');" onmouseover="this.style.cursor = 'pointer';"><br>
                        Borang SK(DHKK)
                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                             onclick="popupParam('UTILITIBSKDHKK_MLK', '${line.idHakmilik}');" onmouseover="this.style.cursor = 'pointer';"><br>
                        Pelan Borang S(K)
                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                             onclick="popupParam('UTILITIPSK_MLK', '${line.idHakmilik}');" onmouseover="this.style.cursor = 'pointer';"><br>
                    </display:column>
                </display:table><%--
            </c:if>--%>
            </div>
            <div id="upload_pelan">
                <%--<c:if test="${fn:length(actionBean.senaraiPelanString>0)} ">--%>
                <legend>Sila Muat Naik Pelan ${actionBean.senaraiPelanString} : </legend>
                <s:file name="file" accept="image/tif"/>
                <br><br>
                <s:submit name="simpanPelan" value="Simpan" class="btn"/>
                <c:set value="0" var="count"/>
                <br><br>
                <display:table class="tablecloth" name="${actionBean.senaraiPelanString}" pagesize="30" cellpadding="0" requestURI="/strataConversion" cellspacing="0" id="line">
                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                    <display:column  title="Pelan" style="vertical-align:baseline">
                        PA(B)${line}
                    </display:column>
                    <display:column  title="Muat Naik Pelan" style="vertical-align:baseline">
                        <center>
                            <c:set value="0" var="count"/>
                            <c:forEach items="${actionBean.senaraiPelanUpload}" var="line2">
                                <c:if test="${line eq line2}">
                                    <img alt='Sudah Muat Naik' border='0' src='${pageContext.request.contextPath}/images/icons/KnobValidGreen.png'
                                         onmouseover="this.style.cursor = 'pointer';">
                                    <c:set value="${count +1}" var="count"/>
                                </c:if>
                            </c:forEach>
                            <c:if test="${count eq 0}">
                                <img alt='Belum Muat Naik' border='0' src='${pageContext.request.contextPath}/images/icons/KnobCancel.png'
                                     onmouseover="this.style.cursor = 'pointer';">
                            </c:if>
                        </center>
                    </display:column>
                </display:table>
                <%--</c:if>--%>
            </div>

        </div>
    </div>
</s:form>
