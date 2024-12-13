<%--
    Document   : pihak_berkepentingan_edit
    Created on : Jul 19, 2010, 1:11:35 PM
    Author     : muhammad.amin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.2.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ageCalculator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<script type="text/javascript">
            $(document).ready(function(){
    $('.empty').remove();
            $('.alphanumeric').alphanumeric();
            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy', changeMonth:true, changeYear: true});
    <%--        $('#jenisPengenalan').change(function(){
                dodacheck($("#kp").val());
            });--%>
    var v = $('#jenisKP').val();
            if (v == "B" || v == "L" || v == "P" || v == "T" || v == "I" || v == "F" || v == "K"){
    $('#kodBangsa').hide();
            $('#kodBangsaOrang').show();
            $('#kodBangsaAdat').show();
            $('#kodBangsaSyarikat').hide();
            $("#kodBangsaData").attr("disabled", "true");
            $("#kodBangsaSyarikatData").attr("disabled", "true");
            $("#kodBangsaAdatData").attr("disabled", "true");
            $('#suku').show();
            $('#subSuku').show();
            $('#perut').show();
            $('#luak').show();
            if (v == "B"){
    var icNo = $('#kp').val();
            if (icNo.length == 12 || icNo.length == 14){
    var year = icNo.substring(0, 2);
            if (year > 25 && year < 99){//fixme :
    year = "19" + year;
    } else {
    year = "20" + year;
    }

    if ($('#kp').val() != ''){
    var age = calculateAge(icNo.substring(4, 6), icNo.substring(2, 4), year);
            $('#umur').val(age);
            $('#datepicker').val(icNo.substring(4, 6) + "/" + icNo.substring(2, 4) + "/" + year);
    }
    }
    }
    }

    else  if (v == "S" || v == "U" || v == "D" || v == "N"){
    $('#kodBangsa').hide();
            $('#kodBangsaOrang').hide();
            $('#kodBangsaAdat').hide();
            $('#kodBangsaSyarikat').show();
            $("#kodBangsaData").attr("disabled", "true");
            $("#kodBangsaOrangData").attr("disabled", "true");
            $("#kodBangsaAdatData").attr("disabled", "true");
            $('#suku').hide();
            $('#subSuku').hide();
            $('#perut').hide();
            $('#luak').hide();
    }

    else if (v == '' || v == "0"){
    $('#kodBangsa').show();
            $('#kodBangsaOrang').hide();
            $('#kodBangsaAdat').hide();
            $('#kodBangsaSyarikat').hide();
            $("#kodBangsaSyarikatData").attr("disabled", "true");
            $("#kodBangsaOrangData").attr("disabled", "true");
            $("#kodBangsaAdatData").attr("disabled", "true");
            $('#cawangan').hide();
            $('#pengarah').hide();
    }

    if ($('#jenis_pihak').val() == 'PA' || $('#jenis_pihak').val() == 'PP' || $('#jenis_pihak').val() == 'WS'){
    $('#pgAmanah').show();
    } else{
    $('#pgAmanah').hide();
    }

    var kodUrusan = '${actionBean.permohonan.kodUrusan.kod}';
            if (kodUrusan != 'PMMAT'){
    if ($('#kod_warganegara').val() == ''){
    $('#kod_warganegara').val('MAL');
    }
    }

    if ($('#kodMatawang').val() == ''){
    $('#kodMatawang').val('MYR');
    }

    var hubungan = $('#hubungan').val();
            hubungan = hubungan.toUpperCase();
            if (hubungan == "LAIN-LAIN"){
    $('#hubunganLain').show();
    } else{
    $('#hubunganLain').hide();
    }

    var tempatLahir = $('#tempatLahir').val();
            if (tempatLahir != null){
    tempatLahir = tempatLahir.toUpperCase();
            if (tempatLahir == "LAIN-LAIN"){
    $('#tempatLahirLain').show();
    } else{
    $('#tempatLahirLain').hide();
    }
    }

    $('#addPA').click(function(){
    if ($('#negeriAmanah').val() == "LAIN-LAIN"){
    $('#negeriAmanahLain_p').show();
    }
    else{
    $('#negeriAmanahLain_p').hide();
    }
    $('#pgAmanahDialog').dialog('open');
    });
            $('#addCaw').click(function(){
    $('#cawDialog').dialog('open');
    });
            $('#addPengarah').click(function(){
    $('#pengarahDialog').dialog('open');
    });
            $("#pengarahDialog").dialog({
    autoOpen: false,
            height: 350,
            width: 900,
            modal: true,
            buttons: {

            'Tutup' : function() {
            $(this).dialog('close');
            },
                    'Simpan': function() {
                    var bValid = true;
                            bValid = bValid && ($('#namaPengarah').val() != '');
                            bValid = bValid && ($('#kpPengarah').val() != '');
                            bValid = bValid && ($('#noKpPengarah').val() != '');
                            bValid = bValid && ($('#wargaPengarah').val() != '');
                            if (bValid) {
                    var nama = $('#namaPengarah').val();
                            var kp = $('#kpPengarah').val();
                            var noKp = $('#noKpPengarah').val();
                            var warga = $('#wargaPengarah').val();
                            var rowNo = $('table#pengarahTable tr').length;
                            $('table#pengarahTable tbody').append("<tr id='x" + rowNo + "' class='x'>" +
                            "<td><input type=hidden name='namaPgrh' value='" + nama + "'/>" + nama + '</td>' +
                            "<td><input type=hidden name='kpPgrh' value='" + kp + "'/>" + $('#kpPengarah option:selected').text() + '</td>' +
                            "<td><input type=hidden name='noKpPgrh' value='" + noKp + "'/>" + noKp + '</td>' +
                            "<td><input type=hidden name='wargaPgrh' value='" + warga + "'/>" + $('#wargaPengarah option:selected').text() + '</td>' +
                            "<td><div align=center><img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' \n\
                                onmouseover='this.style.cursor=\"pointer\";' onclick='editPengarah(\"" + rowNo + "\");'></div></td>" +
                            "<td><div align=center><img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' \n\
                                onmouseover='this.style.cursor=\"pointer\";' onclick='deleteWaris2(\"x" + rowNo + "\");'></div></td>" +
                            '</tr>');
                            $(this).dialog('close');
                    } else {
                    alert('Sila masukkan maklumat ahli lembaga pengarah dengan lengkap. ');
                    }
                    }
            },
            close: function() {
            $('#namaPengarah').val('');
                    $('#kpPengarah').val('');
                    $('#noKpPengarah').val('');
                    $('#wargaPengarah').val('');
            }
    });
            $("#pengarahDialogEdit").dialog({

    autoOpen: false,
            height: 350,
            width: 900,
            modal: true,
            buttons: {

            'Tutup' : function() {
            $(this).dialog('close');
            },
                    'Simpan': function() {
                    var bValid = true;
                            bValid = bValid && ($('#namaPengarahEdit').val() != '');
                            bValid = bValid && ($('#kpPengarahEdit').val() != '');
                            bValid = bValid && ($('#noKpPengarahEdit').val() != '');
                            bValid = bValid && ($('#wargaPengarahEdit').val() != '');
                            if (bValid) {
                    var nama = $('#namaPengarahEdit').val();
                            var kp = $('#kpPengarahEdit').val();
                            var noKp = $('#noKpPengarahEdit').val();
                            var warga = $('#wargaPengarahEdit').val();
                            var idPengarah = $('#idPengarahEdit').val();
                            var idMohonPihak = $('#idMohonPihak').val();
                            var idPemohon = $('#idPemohon').val();
                            var idPengguna = $('#idPengguna').val();
                            doBlockUI();
                            var url = '${pageContext.request.contextPath}/consent/pihak_kepentingan?savePengarahEdit&idPengarah=' + idPengarah + '&idMohonPihak=' + idMohonPihak + '&idPemohon=' + idPemohon + '&namaPengarah=' + nama + '&kpPengarah=' + kp + '&noKpPengarah=' + noKp + '&wargaPengarah=' + warga + '&idPengguna=' + idPengguna;
                            frm = document.form1;
                            frm.action = url;
                            frm.submit();
                            $(this).dialog('close');
                    } else {
                    alert('Sila masukkan maklumat ahli lembaga pengarah dengan lengkap. ');
                    }
                    }
            },
            close: function() {
            $('#namaPengarah').val('');
                    $('#kpPengarah').val('');
                    $('#noKpPengarah').val('');
                    $('#wargaPengarah').val('');
            }
    });
            $("#pgAmanahDialog").dialog({
    autoOpen: false,
            height: 400,
            width: 900,
            modal: true,
            buttons: {

            'Tutup' : function() {
            $(this).dialog('close');
            },
                    'Simpan': function() {
                    var bValid = true;
                            bValid = bValid && ($('#namaAmanah').val() != '');
                            bValid = bValid && ($('#kpAmanah').val() != '');
                            bValid = bValid && ($('#nokpAmanah').val() != '');
                            bValid = bValid && ($('#alamatAmanah1').val() != '');
                            bValid = bValid && ($('#negeriAmanah').val() != '');
                            bValid = bValid && ($('#syerPembilangAmanah').val() != '');
                            bValid = bValid && ($('#syerPenyebutAmanah').val() != '');
                            if (bValid) {
                    var nama = $('#namaAmanah').val();
                            var kp = $('#kpAmanah').val();
                            var noKp = $('#nokpAmanah').val();
                            var wargaPA = $('#warganegaraAmanah').val();
                            var add1 = $('#alamatAmanah1').val();
                            var add2 = $('#alamatAmanah2').val();
                            var add3 = $('#alamatAmanah3').val();
                            var add4 = $('#alamatAmanah4').val();
                            var poskod = $('#poskodAmanah').val();
                            var negeri = $('#negeriAmanah').val();
                            if (negeri == "LAIN-LAIN"){
                    negeri = $('#negaraAmanah').val();
                    }
                    var syerPembilang = $('#syerPembilangAmanah').val();
                            var syerPenyebut = $('#syerPenyebutAmanah').val();
                            var rowNo = $('table#pgAmanahTable tr').length;
                            $('table#pgAmanahTable tbody').append("<tr id='x" + rowNo + "' class='x'>" +
                            "<td><input type=hidden name='namaPA' value='" + nama + "'/>" + nama + '</td>' +
                            "<td><input type=hidden name='kpPA' value='" + kp + "'/>" + $('#kpAmanah option:selected').text() + '</td>' +
                            "<td><input type=hidden name='noKpPA' value='" + noKp + "'/>" + noKp + '</td>' +
                            "<td><input type=hidden name='wargaPA' value='" + wargaPA + "'/>" + $('#warganegaraAmanah option:selected').text() + '</td>' +
                            "<td><input type=hidden name='add1PA' value='" + add1 + "'/>" + add1 + '</td>' +
                            "<td><input type=hidden name='add2PA' value='" + add2 + "'/>" + add2 + '</td>' +
                            "<td><input type=hidden name='add3PA' value='" + add3 + "'/>" + add3 + '</td>' +
                            "<td><input type=hidden name='add4PA' value='" + add4 + "'/>" + add4 + '</td>' +
                            "<td><input type=hidden name='poskodPA' value='" + poskod + "'/>" + poskod + '</td>' +
                            "<td><input type=hidden name='negeriPA' value='" + negeri + "'/>" + negeri + '</td>' +
    <%--"<td><input type=hidden name='negeriPA' value='"+negeri+"'/>" + $('#negeriAmanah option:selected').text() + '</td>' +--%>
                    "<td><input type=hidden name='syerPembilangAmanah' value='" + syerPembilang + "'/><input type=hidden name='syerPenyebutAmanah' value='" + syerPenyebut + "'/>" + syerPembilang + '/' + syerPenyebut + '</td>' +
                            "<td><div align=center><img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' \n\
                                onmouseover='this.style.cursor=\"pointer\";' onclick='editPemegangAmanah(\"" + rowNo + "\");'></div></td>" +
                            "<td><div align=center><img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' \n\
                                onmouseover='this.style.cursor=\"pointer\";' onclick='deleteWaris2(\"x" + rowNo + "\");'></div></td>" +
                            '</tr>');
                            $(this).dialog('close');
                    } else {
                    alert('Sila masukkan maklumat dengan lengkap. ');
                    }
                    }
            },
            close: function() {
            $('#namaAmanah').val('');
                    $('#kpAmanah').val('');
                    $('#nokpAmanah').val('');
                    $('#alamatAmanah1').val('');
                    $('#alamatAmanah2').val('');
                    $('#alamatAmanah3').val('');
                    $('#alamatAmanah4').val('');
                    $('#poskodAmanah').val('');
                    $('#negeriAmanah').val('');
            }
    });
            $("#pgAmanahDialogEdit").dialog({
    autoOpen: false,
            height: 400,
            width: 900,
            modal: true,
            buttons: {

            'Tutup' : function() {
            $(this).dialog('close');
            },
                    'Simpan': function() {
                    var bValid = true;
                            bValid = bValid && ($('#namaAmanahEdit').val() != '');
                            bValid = bValid && ($('#kpAmanahEdit').val() != '');
                            bValid = bValid && ($('#nokpAmanahEdit').val() != '');
                            bValid = bValid && ($('#alamatAmanah1Edit').val() != '');
                            bValid = bValid && ($('#negeriAmanahEdit').val() != '');
                            bValid = bValid && ($('#syerPembilangAmanahEdit').val() != '');
                            bValid = bValid && ($('#syerPenyebutAmanahEdit').val() != '');
                            if (bValid) {
                    var nama = $('#namaAmanahEdit').val();
                            var jenisKp = $('#kpAmanahEdit').val();
                            var noKp = $('#nokpAmanahEdit').val();
                            var warga = $('#warganegaraAmanahEdit').val();
                            var add1 = $('#alamatAmanah1Edit').val();
                            var add2 = $('#alamatAmanah2Edit').val();
                            var add3 = $('#alamatAmanah3Edit').val();
                            var add4 = $('#alamatAmanah4Edit').val();
                            var poskod = $('#poskodAmanahEdit').val();
                            var negeri = $('#negeriAmanahEdit').val();
                            if (negeri == "LAIN-LAIN"){
                    negeri = $('#negaraAmanahEdit').val();
                    }
                    var syerPembilang = $('#syerPembilangAmanahEdit').val();
                            var syerPenyebut = $('#syerPenyebutAmanahEdit').val();
                            var idHubungan = $('#idAmanahEdit').val();
                            var idMohonPihak = $('#idMohonPihak').val();
                            doBlockUI();
                            var url = '${pageContext.request.contextPath}/consent/pihak_kepentingan?savePgAmanahEdit&idHubungan=' + idHubungan + '&idMohonPihak=' + idMohonPihak + '&nama=' + nama + '&jenisKp=' + jenisKp + '&noKp=' + noKp + '&alamat1=' + add1 + '&alamat2=' + add2 + '&alamat3=' + add3 + '&alamat4=' + add4 + '&poskod=' + poskod + '&negeri=' + negeri + '&warga=' + warga + '&syerPenyebut=' + syerPenyebut + '&syerPembilang=' + syerPembilang;
                            frm = document.form1;
                            frm.action = url;
                            frm.submit();
                            $(this).dialog('close');
                    } else {
                    alert('Sila masukkan maklumat dengan lengkap. ');
                    }
                    }
            },
            close: function() {
            $('#namaAmanahEdit').val('');
                    $('#kpAmanahEdit').val('');
                    $('#nokpAmanahEdit').val('');
                    $('#alamatAmanah1Edit').val('');
                    $('#alamatAmanah2Edit').val('');
                    $('#alamatAmanah3Edit').val('');
                    $('#alamatAmanah4Edit').val('');
                    $('#poskodAmanahEdit').val('');
                    $('#negeriAmanahEdit').val('');
                    $('#negaraAmanahEdit').val('');
            }
    });
            $("#cawDialog").dialog({
    autoOpen: false,
            height: 350,
            width: 900,
            modal: true,
            buttons: {

            'Tutup' : function() {
            $(this).dialog('close');
            },
                    'Simpan': function() {
                    var bValid = true;
                            bValid = bValid && ($('#namaCaw').val() != '');
                            bValid = bValid && ($('#alamatCaw1').val() != '');
                            bValid = bValid && ($('#negeriCaw').val() != '');
                            if (bValid) {
                    var nama = $('#namaCaw').val();
                            var add1 = $('#alamatCaw1').val();
                            var add2 = $('#alamatCaw2').val();
                            var add3 = $('#alamatCaw3').val();
                            var add4 = $('#alamatCaw4').val();
                            var poskod = $('#poskodCaw').val();
                            var negeri = $('#negeriCaw').val();
    <%--var telCaw = $('#telCaw').val();--%>

                    var rowNo = $('table#cawTable tr').length;
                            var no = $('.nw_pc').length;
                            $('table#cawTable tbody').append("<tr id='caw" + rowNo + "' class='x'>" +
                            "<td><input type=hidden class=nw_pc name=nw_pc/><input type=radio name=pilih value='pc_" + no + "'/></td>" +
    <%--    "<td><input type=hidden name='namaCaw' value='"+nama+"'/>\n\
    <input type=hidden name='telCaw' value='"+telCaw+"'/>\n\
    " + nama + '</td>' +--%>
                    "<td><input type=hidden name='namaCaw' value='" + nama + "'/>" + nama + '</td>' +
                            "<td><input type=hidden name='add1Caw' value='" + add1 + "'/>" + add1 + '</td>' +
                            "<td><input type=hidden name='add2Caw' value='" + add2 + "'/>" + add2 + '</td>' +
                            "<td><input type=hidden name='add3Caw' value='" + add3 + "'/>" + add3 + '</td>' +
                            "<td><input type=hidden name='add4Caw' value='" + add4 + "'/>" + add4 + '</td>' +
                            "<td><input type=hidden name='poskodCaw' value='" + poskod + "'/>" + poskod + '</td>' +
                            "<td><input type=hidden name='negeriCaw' value='" + negeri + "'/>" + $('#negeriCaw option:selected').text() + '</td>' +
                            "<td><div align=center><img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' \n\
                                onmouseover='this.style.cursor=\"pointer\";' onclick='editCawangan(\"" + rowNo + "\");'></div></td>" +
                            "<td><div align=center><img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' \n\
                                onmouseover='this.style.cursor=\"pointer\";' onclick='deleteWaris2(\"caw" + rowNo + "\");'></div></td>" +
                            "<td></td>" +
                            '</tr>');
                            $(this).dialog('close');
                    } else {
                    alert('Sila masukkan maklumat cawangan dengan lengkap. ');
                    }
                    }
            },
            close: function() {
            $('#namaCaw').val('');
                    $('#alamatCaw1').val('');
                    $('#alamatCaw2').val('');
                    $('#alamatCaw3').val('');
                    $('#alamatCaw4').val('');
                    $('#poskodCaw').val('');
                    $('#negeriCaw').val('');
                    $('#telCaw').val('');
            }
    });
            $("#cawDialogEdit").dialog({
    autoOpen: false,
            height: 350,
            width: 900,
            modal: true,
            buttons: {

            'Tutup' : function() {
            $(this).dialog('close');
            },
                    'Simpan': function() {
                    var bValid = true;
                            bValid = bValid && ($('#namaCawEdit').val() != '');
                            bValid = bValid && ($('#alamatCaw1Edit').val() != '');
                            bValid = bValid && ($('#negeriCawEdit').val() != '');
                            if (bValid) {
                    var namaCawangan = $('#namaCawEdit').val();
                            var alamat1 = $('#alamatCaw1Edit').val();
                            var alamat2 = $('#alamatCaw2Edit').val();
                            var alamat3 = $('#alamatCaw3Edit').val();
                            var alamat4 = $('#alamatCaw4Edit').val();
                            var poskod = $('#poskodCawEdit').val();
                            var negeri = $('#negeriCawEdit').val();
                            var idCawangan = $('#idCawEdit').val();
                            var idMohonPihak = $('#idMohonPihak').val();
                            var idPemohon = $('#idPemohon').val();
                            doBlockUI();
                            var url = '${pageContext.request.contextPath}/consent/pihak_kepentingan?saveCawanganEdit&idCawangan=' + idCawangan + '&idMohonPihak=' + idMohonPihak + '&idPemohon=' + idPemohon + '&namaCawangan=' + namaCawangan + '&alamat1=' + alamat1 + '&alamat2=' + alamat2 + '&alamat3=' + alamat3 + '&alamat4=' + alamat4 + '&poskod=' + poskod + '&negeri=' + negeri;
                            frm = document.form1;
                            frm.action = url;
                            frm.submit();
                            $(this).dialog('close');
                    } else {
                    alert('Sila masukkan maklumat cawangan dengan lengkap. ');
                    }
                    }
            },
            close: function() {
            $('#namaCawEdit').val('');
                    $('#alamatCaw1Edit').val('');
                    $('#alamatCaw2Edit').val('');
                    $('#alamatCaw3Edit').val('');
                    $('#alamatCaw4Edit').val('');
                    $('#poskodCawEdit').val('');
                    $('#negeriCawEdit').val('');
                    $('#telCawEdit').val('');
            }
    });
    });
            function save(event, f){

            if (doValidation()){
            doBlockUI();
                    var q = $(f).formSerialize();
                    var url = f.action + '?' + event;
                    $.post(url, q,
                            function(data){
                            $('#page_div', opener.document).html(data);
                                    $.unblockUI();
                                    self.close();
                            }, 'html');
            }
            }

    function convert(val, id){
    var amaun = CurrencyFormatted(val);
            amaun = CommaFormatted(amaun);
            $('#' + id).val(amaun);
    }

    function CurrencyFormatted(amount){
    var q = amount.indexOf(",");
            if (q > 0){
    amount = amount.replace (/,/g, "");
    }

    var i = parseFloat(amount);
            if (isNaN(i)) { i = 0.00; }
    var minus = '';
            if (i < 0) { minus = '-'; }
    i = Math.abs(i);
            i = parseInt((i + .005) * 100);
            i = i / 100;
            s = new String(i);
            if (s.indexOf('.') < 0) { s += '.00'; }
    if (s.indexOf('.') == (s.length - 2)) { s += '0'; }
    s = minus + s;
            return s;
    }

    function CommaFormatted(amount){
    var delimiter = ","; // replace comma if desired
            var a = amount.split('.', 2)
            var d = a[1];
            var i = parseInt(a[0]);
            if (isNaN(i)) { return ''; }
    var minus = '';
            if (i < 0) { minus = '-'; }
    i = Math.abs(i);
            var n = new String(i);
            var a = [];
            while (n.length > 3)
    {
    var nn = n.substr(n.length - 3);
            a.unshift(nn);
            n = n.substr(0, n.length - 3);
    }
    if (n.length > 0) { a.unshift(n); }
    n = a.join(delimiter);
            if (d.length < 1) { amount = n; }
    else { amount = n + '.' + d; }
    amount = minus + amount;
            return amount;
    }

    function changeHubungan(value){
    value = value.toUpperCase();
            if (value == "LAIN-LAIN"){
    $('#hubunganLain').show();
    }
    else{
    $('#hubunganLain').hide();
    }
    }

    function changeTempatLahir(value){
    value = value.toUpperCase();
            if (value == "LAIN-LAIN"){
    $('#tempatLahirLain').show();
    }
    else{
    $('#tempatLahirLain').hide();
    }
    }

    function changeNegeriAmanah(value){
    if (value == "LAIN-LAIN"){
    $('#negeriAmanahLain_p').show();
    }
    else{
    $('#negeriAmanahLain_p').hide();
    }
    }

    function changeNegeriAmanahEdit(value){
    if (value == "LAIN-LAIN"){
    $('#negeriAmanahLain_p_edit').show();
    }
    else{
    $('#negeriAmanahLain_p_edit').hide();
    }
    }

    function changeJenisKP(v){

    if (v == "B" || v == "L" || v == "P" || v == "T" || v == "I" || v == "F" || v == "K"){
    $('#kodBangsa').show();
            $('#kodBangsaOrang').show();
            $('#kodBangsaAdat').show();
            $('#kodBangsaSyarikat').hide();
            $('#suku').show();
            $('#subSuku').show();
            $('#perut').show();
            $('#luak').show();
            $('#cawangan').hide();
            $('#pengarah').hide();
            if (v == "B"){
    var icNo = $('#kp').val();
            if (icNo.length == 12 || icNo.length == 14){

    var year = icNo.substring(0, 2);
            if (year > 25 && year < 99){//fixme :
    year = "19" + year;
    } else {
    year = "20" + year;
    }

    if ($('#kp').val() != ''){
    var age = calculateAge(icNo.substring(4, 6), icNo.substring(2, 4), year);
            $('#umur').val(age);
            $('#datepicker').val(icNo.substring(4, 6) + "/" + icNo.substring(2, 4) + "/" + year);
    }
    }
    }
    }

    else  if (v == "S" || v == "U" || v == "D" || v == "0" || v == "N"){
    $('#kodBangsa').hide();
            $('#kodBangsaOrang').hide();
            $('#kodBangsaAdat').hide();
            $('#kodBangsaSyarikat').show();
            $('#suku').hide();
            $('#subSuku').hide();
            $('#perut').hide();
            $('#luak').hide();
            $('#cawangan').show();
            $('#pengarah').show();
    }

    else {

    $('#kodBangsa').show();
            $('#kodBangsaOrang').show();
            $('#kodBangsaAdat').show();
            $('#kodBangsaSyarikat').show();
            $('#cawangan').show();
            $('#pengarah').show();
    }
    }

    function changePgAmanah(value){
    if (value == "PA"){
    $('#pgAmanah').show();
    } else{
    $('#pgAmanah').hide();
    }
    }

    function changeIcNumber(icNo){
    var jenisKp = $('#jenisKP').val();
            if (jenisKp == 'B'){
    if (icNo.length == 12 || icNo.length == 14){
    var year = icNo.substring(0, 2);
            if (year > 25 && year < 99){//fixme :
    year = "19" + year;
    } else {
    year = "20" + year;
    }

    if ($('#kp').val() != ''){
    var age = calculateAge(icNo.substring(4, 6), icNo.substring(2, 4), year);
            $('#umur').val(age);
            $('#datepicker').val(icNo.substring(4, 6) + "/" + icNo.substring(2, 4) + "/" + year);
    }
    }
    }
    }

    function doValidation(){

    var val = $('#kod_warganegara').val();
            var val2 = $('#alamat1').val();
            var val3 = $('#nama_pemohon').val();
            var val4 = $('#jenis_pihak').val();
            var valueJenisKP = $('#jenisKP').val();
            var valueNoKP = $('#kp').val();
            var suratAlamat = $('#suratAlamat1').val();
            var var5 = $('#KatgPenerima').val();
            var var6 = $('#KatgPemohon').val();
            
            if (val3 == ''){
    alert('Sila masukkan nama pihak.');
            return false;
    }
    else if (valueJenisKP == ''){
    alert('Sila masukkan Jenis pengenalan.');
            return false;
    }
    if (valueJenisKP != null){
        if (valueJenisKP != '0'){
            if (valueNoKP == ''){
                alert('Sila masukkan nombor pengenalan.');
                return false;
            }
        }
    }
    
     if (val == ''){
    alert('Sila pilih warganegara.');
            return false;
    } else if (val2 == ''){
    alert('Sila masukkan alamat berdaftar.');
            return false;
    } else if (val4 == ''){
    alert('Sila Masukkan Jenis Penerima');
            return false;
    }
    else if (suratAlamat == ''){
    alert('Sila Masukkan Alamat Surat-menyurat.');
            return false;
    }else if (var5 == ''){
        alert('Sila Pilih Kategori Penerima.');
            return false;
    }else if (var6 == ''){
            alert('Sila pilih Kategori Pemohon');
            return false;    
    }
    
    var kodNegeri = '${actionBean.kodNegeri}';
            var kodUrusan = '${actionBean.permohonan.kodUrusan.kod}';
    <%-- Validation for Pemegang Amanah --%>
    if (val4 == "PA"){
    if ($('table#pgAmanahTable tr').length == 1){
    alert('Sila Masukkan Penerima Amanah.');
            return false;
    }
    }
    <%-- Validation for Pemegang Amanah --%>


    if (kodUrusan == 'TMADT' || kodUrusan == 'TMWNA'){
    var hubungan = $('#hubungan').val();
            if (hubungan == ''){
    alert('Sila masukkan maklumat hubungan dengan simati.');
            return false;
    }
    }

    if (kodNegeri == "05"){
    if (kodUrusan == 'TMADT' || kodUrusan == 'TMWNA' || kodUrusan == 'BTADT' ||
            kodUrusan == 'CGADT' || kodUrusan == 'PMADT' || kodUrusan == 'TTADT' ||
            kodUrusan == 'PJADT' || kodUrusan == 'PMWNA' || kodUrusan == 'PMWWA'){

    if (valueJenisKP == "B" || valueJenisKP == "L" || valueJenisKP == "P" || valueJenisKP == "T" ||
            valueJenisKP == "I" || valueJenisKP == "F" || valueJenisKP == "K"){

    var suku = $('#sukuData').val();
            var luak = $('#luakData').val();
            if (suku == ''){
    alert('Sila Masukkan Maklumat Suku.');
            return false;
    }
    else if (luak == ''){
    alert('Sila Masukkan Maklumat Luak.');
            return false;
    }
    }
    }
    }
    
    if (kodNegeri == '04' && kodUrusan == 'PMPTD') {
        var varPenerima = $('#KatgPenerima').val();
        var varPemohon = '${actionBean.pemohon.kodStatus}';
        alert(varPemohon);
        alert(varPenerima);
        if (varPemohon == '1001' && varPenerima == '1004') {
            alert('Terdapat Kesalahan pada Kod Urusan. Pindahmilik dari Melayu Bumiputera kepada Bukan Bumiputera adalah Kelulusan PTG. Mohon semakan lebih lanjut.');
            return false;
        } else if (varPemohon == '1001' && varPenerima == '1005') {
            alert('Terdapat Kesalahan pada Kod Urusan. Pindahmilik dari Melayu Bumiputera kepada Warga Asing adalah Kelulusan PTG. Mohon semakan lebih lanjut.');
            return false;
        } else if (varPemohon == '1002' && varPenerima == '1004') {
            alert('Terdapat Kesalahan pada Kod Urusan. Pindahmilik dari Bumiputera Sabah kepada Bukan Bumiputera adalah Kelulusan PTG. Mohon semakan lebih lanjut.');
            return false;
        } else if (varPemohon == '1002' && varPenerima == '1005') {
            alert('Terdapat Kesalahan pada Kod Urusan. Pindahmilik dari Bumiputera Sabah kepada Warga Asing adalah Kelulusan PTG. Mohon semakan lebih lanjut.');
            return false;
        } else if (varPemohon == '1003' && varPenerima == '1004') {
            alert('Terdapat Kesalahan pada Kod Urusan. Pindahmilik dari Bumiputera Sarawak kepada Bukan Bumiputera adalah Kelulusan PTG. Mohon semakan lebih lanjut.');
            return false;
        } else if (varPemohon == '1003' && varPenerima == '1005') {
            alert('Terdapat Kesalahan pada Kod Urusan. Pindahmilik dari Bumiputera Sarawak kepada Warga Asing adalah Kelulusan PTG. Mohon semakan lebih lanjut.');
            return false;
        } else if (varPemohon == '1001' && varPenerima == '2002') {
            alert('Terdapat Kesalahan pada Kod Urusan. Pindahmilik dari Melayu Bumiputera kepada Syarikat Bukan Bumiputera adalah Kelulusan PTG. Mohon semakan lebih lanjut.');
            return false;
        } else if (varPemohon == '1001' && varPenerima == '3001') {
            alert('Terdapat Kesalahan pada Kod Urusan. Pindahmilik dari Melayu Bumiputera kepada Syarikat Asing adalah Kelulusan PTG. Mohon semakan lebih lanjut.');
            return false;
        } else if (varPemohon == '1002' && varPenerima == '2002') {
            alert('Terdapat Kesalahan pada Kod Urusan. Pindahmilik dari Bumiputera Sabah kepada Syarikat Bukan Bumiputera adalah Kelulusan PTG. Mohon semakan lebih lanjut.');
            return false;
        } else if (varPemohon == '1002' && varPenerima == '3001') {
            alert('Terdapat Kesalahan pada Kod Urusan. Pindahmilik dari Bumiputera Sabah kepada Syarikat Asing adalah Kelulusan PTG. Mohon semakan lebih lanjut.');
            return false;
        } else if (varPemohon == '1003' && varPenerima == '2002') {
            alert('Terdapat Kesalahan pada Kod Urusan. Pindahmilik dari Bumiputera Sarawak kepada Syarikat Bukan Bumiputera adalah Kelulusan PTG. Mohon semakan lebih lanjut.');
            return false;
        } else if (varPemohon == '1003' && varPenerima == '3001') {
            alert('Terdapat Kesalahan pada Kod Urusan. Pindahmilik dari Bumiputera Sarawak kepada Syarikat Asing adalah Kelulusan PTG. Mohon semakan lebih lanjut.');
            return false;
        } else if (varPemohon == '2001' && varPenerima == '2002') {
            alert('Terdapat Kesalahan pada Kod Urusan. Pindahmilik dari Syarikat Bumiputera kepada Syarikat Bukan Bumiputera adalah Kelulusan PTG. Mohon semakan lebih lanjut.');
            return false;
        } else if (varPemohon == '2001' && varPenerima == '3001') {
            alert('Terdapat Kesalahan pada Kod Urusan. Pindahmilik dari Syarikat Bumiputera kepada Syarikat Asing adalah Kelulusan PTG. Mohon semakan lebih lanjut.');
            return false;
        } else if (varPemohon == '2001' && varPenerima == '1004') {
            alert('Terdapat Kesalahan pada Kod Urusan. Pindahmilik dari Syarikat Bumiputera kepada Bukan Bumiputera adalah Kelulusan PTG. Mohon semakan lebih lanjut.');
            return false;
        }
    }

    if ((kodNegeri == '04' && kodUrusan == 'PMMK1') || (kodNegeri == '04' && kodUrusan == 'PMMK2')){
    var jantina = $('#jantina').val();
    <%--var mastautin =  $('#mastautin').val();--%>
    var pendapatan = $('#pendapatan').val();
            var tarikhTubuh = $('#datepicker').val();
            var tempatLahir = $('#tempatLahir').val();
            var tempatLahirLain = $('#tempatLahirLainPenerima').val();
            var bangsa = $('#kodBangsaOrangData').val();
            if (valueJenisKP != "S" && valueJenisKP != "U" && valueJenisKP != "D" && valueJenisKP != "0" && valueJenisKP != "N"){
    if (bangsa == ''){
    alert('Sila masukkan maklumat bangsa.');
            return false;
    }
    else if (jantina == ''){
    alert('Sila masukkan maklumat jantina.');
            return false;
    }
    }
    <%--    else if(mastautin == ''){
            alert('Sila masukkan tempoh mastautin.');
            return false;
        }--%>
    <%--    else if(tempatLahir == ''){
            alert('Sila masukkan maklumat tempat lahir.');
            return false;
        }--%>
    else if (pendapatan == ''){
    alert('Sila masukkan maklumat pendapatan.');
            return false;
    }
    else if (valueJenisKP == "S" || valueJenisKP == "U" || valueJenisKP == "D" || valueJenisKP == "0" || valueJenisKP == "N"){
    if (tarikhTubuh == ''){
    alert('Sila masukkan tarikh ditubuhkan.');
            return false;
    }
    }
    if (tempatLahir != null){
    tempatLahir = tempatLahir.toUpperCase();
            if (tempatLahir == 'LAIN-LAIN'){
    if (tempatLahirLain == ''){
    alert('Sila masukkan maklumat lain-lain tempat lahir.');
            return false;
    }
    }
    }
    }
    return true;
    }

    function copyAdd(){
    if ($('input[name=add1]').is(':checked')){
    $('#suratAlamat1').val($('#alamat1').val());
            $('#suratAlamat2').val($('#alamat2').val());
            $('#suratAlamat3').val($('#alamat3').val());
            $('#suratAlamat4').val($('#alamat4').val());
            $('#suratPoskod').val($('#poskod').val());
            $('#suratNegeri').val($('#negeri').val());
    } else{
    $('#suratAlamat1').val('');
            $('#suratAlamat2').val('');
            $('#suratAlamat3').val('');
            $('#suratAlamat4').val('');
            $('#suratPoskod').val('');
            $('#suratNegeri').val('');
    }
    }

    function deleteWaris2(id) {
    $('#' + id).remove();
    }

    function editPemegangAmanah(row, idAmanah) {

    $('#namaAmanahEdit').val($('table#pgAmanahTable tr:eq(' + row + ') td:eq(' + 0 + ')').text());
            $('#kpAmanahEdit').val($('table#pgAmanahTable tr:eq(' + row + ') td:eq(' + 1 + ')').text());
            $('#nokpAmanahEdit').val($('table#pgAmanahTable tr:eq(' + row + ') td:eq(' + 2 + ')').text());
            $('#warganegaraAmanahEdit').val($('table#pgAmanahTable tr:eq(' + row + ') td:eq(' + 3 + ')').text());
            $('#alamatAmanah1Edit').val($('table#pgAmanahTable tr:eq(' + row + ') td:eq(' + 4 + ')').text());
            $('#alamatAmanah2Edit').val($('table#pgAmanahTable tr:eq(' + row + ') td:eq(' + 5 + ')').text());
            $('#alamatAmanah3Edit').val($('table#pgAmanahTable tr:eq(' + row + ') td:eq(' + 6 + ')').text());
            $('#alamatAmanah4Edit').val($('table#pgAmanahTable tr:eq(' + row + ') td:eq(' + 7 + ')').text());
            $('#poskodAmanahEdit').val($('table#pgAmanahTable tr:eq(' + row + ') td:eq(' + 8 + ')').text());
            var negeri = $('table#pgAmanahTable tr:eq(' + row + ') td:eq(' + 9 + ')').text().toUpperCase();
            var negeriTemp = $.trim($('table#pgAmanahTable tr:eq(' + row + ') td:eq(' + 9 + ')').text());
            if (negeri.match('PERLIS') || negeri.match('KEDAH') || negeri.match('PULAU PINANG') || negeri.match('PERAK') || negeri.match('SELANGOR')
                    || negeri.match('NEGERI SEMBILAN') || negeri.match('MELAKA') || negeri.match('JOHOR') || negeri.match('PAHANG') || negeri.match('TERENGGANU')
                    || negeri.match('KELANTAN') || negeri.match('SARAWAK') || negeri.match('SABAH') || negeri.match('WP PUTRAJAYA') || negeri.match('WP KUALA LUMPUR')
                    || negeri.match('WP LABUAN')){

    $('#negeriAmanahEdit').val(negeriTemp);
    }

    else {
    $('#negeriAmanahEdit').val("LAIN-LAIN");
            $('#negaraAmanahEdit').val(negeriTemp);
    }

    var syer = $('table#pgAmanahTable tr:eq(' + row + ') td:eq(' + 10 + ')').text();
            var splitSyer = syer.split('/');
            $('#syerPembilangAmanahEdit').val(splitSyer[0]);
            $('#syerPenyebutAmanahEdit').val(splitSyer[1]);
            $('#idAmanahEdit').val(idAmanah);
            if ($('#negeriAmanahEdit').val() == "LAIN-LAIN"){
    $('#negeriAmanahLain_p_edit').show();
    }
    else{
    $('#negeriAmanahLain_p_edit').hide();
    }

    $('#pgAmanahDialogEdit').dialog('open');
    }

    function editPengarah(row, idPengarah) {

    $('#namaPengarahEdit').val($('table#pengarahTable tr:eq(' + row + ') td:eq(' + 0 + ')').text());
    <%--$('#namaPengarahEdit').val($('#nama_pengarah'+value).val());--%>
    $('#kpPengarahEdit').val($('table#pengarahTable tr:eq(' + row + ') td:eq(' + 1 + ')').text());
            $('#noKpPengarahEdit').val($('table#pengarahTable tr:eq(' + row + ') td:eq(' + 2 + ')').text());
            $('#wargaPengarahEdit').val($('table#pengarahTable tr:eq(' + row + ') td:eq(' + 3 + ')').text());
            $('#idPengarahEdit').val(idPengarah);
            $('#pengarahDialogEdit').dialog('open');
    }

    function editCawangan(row, idCaw) {

    $('#namaCawEdit').val($('table#cawTable tr:eq(' + row + ') td:eq(' + 1 + ')').text());
            $('#alamatCaw1Edit').val($('table#cawTable tr:eq(' + row + ') td:eq(' + 2 + ')').text());
            $('#alamatCaw2Edit').val($('table#cawTable tr:eq(' + row + ') td:eq(' + 3 + ')').text());
            $('#alamatCaw3Edit').val($('table#cawTable tr:eq(' + row + ') td:eq(' + 4 + ')').text());
            $('#alamatCaw4Edit').val($('table#cawTable tr:eq(' + row + ') td:eq(' + 5 + ')').text());
            $('#poskodCawEdit').val($('table#cawTable tr:eq(' + row + ') td:eq(' + 6 + ')').text());
            $('#negeriCawEdit').val($('table#cawTable tr:eq(' + row + ') td:eq(' + 7 + ')').text());
            $('#telCawEdit').val($('table#cawTable tr:eq(' + row + ') td:eq(' + 8 + ')').text());
            $('#idCawEdit').val(idCaw);
            $('#cawDialogEdit').dialog('open');
    }

    function removePengarah(val, id){
    if (confirm('Adakah anda pasti?')) {
    doBlockUI();
            var url = '${pageContext.request.contextPath}/consent/pihak_kepentingan?deletePengarah&idPengarah=' + val + '&idPihak=' + $('#idPihak').val() + '&idMohonPihak=' + $('#idMohonPihak').val() + '&idPemohon=' + $('#idPemohon').val() + '&jenisAction=editPenerima';
            frm = document.form1;
            frm.action = url;
            frm.submit();
    }
    }

    function removeAmanah(val, id){
    if (confirm('Adakah anda pasti?')) {
    doBlockUI();
            var url = '${pageContext.request.contextPath}/consent/pihak_kepentingan?deletePihakHubungan&idHubungan=' + val + '&idPihak=' + $('#idPihak').val() + '&idMohonPihak=' + $('#idMohonPihak').val() + '&jenisAction=editPenerima';
            frm = document.form1;
            frm.action = url;
            frm.submit();
    }
    }

    function removeCawanganPemohon(val, id){
    if (confirm('Adakah anda pasti?')) {
    doBlockUI();
            var url = '${pageContext.request.contextPath}/consent/pihak_kepentingan?deletePihakCaw&idPihakCaw=' + val + '&idPihak=' + $('#idPihak').val() + '&idMohonPihak=' + $('#idMohonPihak').val() + '&idPemohon=' + $('#idPemohon').val() + '&jenisAction=editPenerima';
            frm = document.form1;
            frm.action = url;
            frm.submit();
    }
    }

    function validateNumber(elmnt, content) {
    //if it is character, then remove it..
    if (isNaN(content)) {
    elmnt.value = removeNonNumeric(content);
            return;
    }
    }

    function removeNonNumeric(strString)  {
    var strValidCharacters = "1234567890";
            var strReturn = "";
            var strBuffer = "";
            var intIndex = 0;
            // Loop through the string
            for (intIndex = 0; intIndex < strString.length; intIndex++)
    {
    strBuffer = strString.substr(intIndex, 1);
            // Is this a number
            if (strValidCharacters.indexOf(strBuffer) > - 1)
    {
    strReturn += strBuffer;
    }
    }
    return strReturn;
    }

    function doBlockUI () {
    $.blockUI({
    message: $('#displayBox'),
            css: {
            top:  ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
            }
    });
    }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none" alt=""/>
<div class="subtitle">
    <s:form beanclass="etanah.view.stripes.consent.PihakKepentinganActionBean" name="form1">
        <s:hidden name="pihak.idPihak" id="idPihak"/>
        <s:hidden name="pemohon.idPemohon" id="idPemohon"/>
        <s:hidden name="permohonanPihak.idPermohonanPihak" id="idMohonPihak"/>
        <s:hidden name="idHakmilik"/>
        <s:hidden name="kodNegeri"/>

        <%--================================================PENERIMA===============================================--%>
        <c:if test="${penerima || penerimaGadaian}">
            <fieldset class="aras1">
                <legend>
                    <c:if test="${penerimaGadaian}">
                        Kemaskini Maklumat Penerima Gadaian
                    </c:if>
                    <c:if test="${penerima}">

                        Kemaskini Maklumat
                        <c:choose>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PCPTD' || actionBean.permohonan.kodUrusan.kod eq 'PGDMB' 
                                            || actionBean.permohonan.kodUrusan.kod eq 'PCMMK' || actionBean.permohonan.kodUrusan.kod eq 'GSMMK' 
                                            || actionBean.permohonan.kodUrusan.kod eq 'PMJTL' || actionBean.permohonan.kodUrusan.kod eq 'PJKTL'
                                            || actionBean.permohonan.kodUrusan.kod eq 'RMJTL' || actionBean.permohonan.kodUrusan.kod eq 'RJKTL' 
                                            || actionBean.permohonan.kodUrusan.kod eq 'PMTMB' || actionBean.permohonan.kodUrusan.kod eq 'PMADT'
                                            || actionBean.permohonan.kodUrusan.kod eq 'PMWNA' || actionBean.permohonan.kodUrusan.kod eq 'PMWWA'
                                            || actionBean.permohonan.kodUrusan.kod eq 'PPTGM' || actionBean.permohonan.kodUrusan.kod eq 'PMKMM'
                                            || actionBean.permohonan.kodUrusan.kod eq 'PMMMK' || actionBean.permohonan.kodUrusan.kod eq 'PMPTD'
                                            || fn:startsWith(actionBean.permohonan.kodUrusan.kod, 'PPTD') ||  fn:startsWith(actionBean.permohonan.kodUrusan.kod, 'PMMK')}">
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
                                Pemohon/Penerima
                            </c:when>
                            <c:otherwise>
                                Penerima
                            </c:otherwise>
                        </c:choose>

                    </c:if>
                </legend>
                <br/>
                <em>Semua yang bertanda * adalah wajib dimasukkan.</em>
                <c:set var="disabled" value="disabled"/>
                <c:if test="${newPihak}">
                    <c:set var="disabled" />
                </c:if>
                <%--                <c:if test="${moreThanOneHakmilik}">
                                    <p>
                                        <label>&nbsp;</label>
                                        <font color="red" size="2">
                                            <input type="checkbox" name="copyToAll" value="1"/>
                                            <em>Sila klik jika sama untuk semua hakmilik.</em>
                                        </font>
                                    </p>
                                </c:if>--%>
                <p>
                    <label for="nama"><font color="red">*</font>Nama :</label>
                            <s:text name="pihak.nama" id="nama_pemohon" size="62" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>

                <p>
                    <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                            <s:select name="pihak.jenisPengenalan.kod" id="jenisKP" onchange="javaScript:changeJenisKP(this.options[selectedIndex].value)">
                                <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                    </s:select>
                </p>

                <p>
                    <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                    <s:text name="pihak.noPengenalan" id="kp" size="40" onkeyup="this.value=this.value.toUpperCase();dodacheck(this.value);" onchange="changeIcNumber(this.value);"/>&nbsp;<em style="font-style:normal">(cth : 800620125177)</em>
                </p>
                <p>
                    <label for="Jenis Pengenalan Lain">Jenis Pengenalan Lain:</label>
                            <s:select name="pihak.jenisPengenalanLain.kod" id="jenisKPLain" onchange="this.options[selectedIndex].value">
                                <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                    </s:select>
                </p>
                
                <p>
                    <label>Nombor Pengenalan Lain :</label>
                    <s:text name="pihak.noPengenalanLain" id="kpl" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <%--                <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L'
                                              || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T'
                                              || actionBean.pihak.jenisPengenalan.kod eq 'I' || actionBean.pihak.jenisPengenalan.kod eq 'K'}">
                                      <p>
                                          <label><font color="red">*</font>Kewarganegaraan :</label>
                                          <s:select name="pihak.wargaNegara.kod" style="width:200px" id="kod_warganegara">
                                              <s:option value="">Sila Pilih</s:option>
                                              <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                                          </s:select>
                                      </p>
                                </c:if>--%>

                <p>
                    <c:choose>
                        <c:when test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L'
                                        || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T'
                                        || actionBean.pihak.jenisPengenalan.kod eq 'I' || actionBean.pihak.jenisPengenalan.kod eq 'K'}">
                                <label><font color="red">*</font>Kewarganegaraan :</label>
                                    </c:when>
                                    <c:otherwise>
                                <label>Negara Syarikat :</label>
                        </c:otherwise>
                    </c:choose>
                    <s:select name="pihak.wargaNegara.kod" style="width:200px" id="kod_warganegara">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                    </s:select>
                </p>

                <p>
                    <label for="nama"><font color="red">*</font>Jenis Penerima :</label>
                            <s:select name="jenisPihak" style="width:400px" id="jenis_pihak" onchange="javaScript:changePgAmanah(this.options[selectedIndex].value);">
                                <s:option value="" >Sila Pilih</s:option>
                        <c:if test="${penerimaGadaian}">
                            <s:option value="PA">PEMEGANG AMANAH</s:option>
                            <s:option value="PGA">PENERIMA GADAIAN</s:option>
                        </c:if>
                        <c:if test="${penerima}">
                            <c:choose>
                                <c:when test="${actionBean.kodNegeri eq '05'}">
                                    <c:choose>
                                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PCPTD' || actionBean.permohonan.kodUrusan.kod eq 'PGDMB' 
                                                        || actionBean.permohonan.kodUrusan.kod eq 'PCMMK' || actionBean.permohonan.kodUrusan.kod eq 'GSMMK' 
                                                        || actionBean.permohonan.kodUrusan.kod eq 'PMJTL' || actionBean.permohonan.kodUrusan.kod eq 'PJKTL'
                                                        || actionBean.permohonan.kodUrusan.kod eq 'RMJTL' || actionBean.permohonan.kodUrusan.kod eq 'RJKTL' 
                                                        || actionBean.permohonan.kodUrusan.kod eq 'PMTMB' || actionBean.permohonan.kodUrusan.kod eq 'PMWNA' 
                                                        || actionBean.permohonan.kodUrusan.kod eq 'PMMMK' || actionBean.permohonan.kodUrusan.kod eq 'PMPTD' 
                                                        || actionBean.permohonan.kodUrusan.kod eq 'PMWWA'
                                                        || fn:startsWith(actionBean.permohonan.kodUrusan.kod, 'PPTD') ||  fn:startsWith(actionBean.permohonan.kodUrusan.kod, 'PMMK')}">
                                            <s:option value="PPM">PENERIMA PINDAH MILIK</s:option>
                                            <s:option value="PA">PEMEGANG AMANAH</s:option>                                            
                                        </c:when>
                                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PJPTD' || actionBean.permohonan.kodUrusan.kod eq 'PJMMK' 
                                                        || actionBean.permohonan.kodUrusan.kod eq 'PJADT'}">
                                            <s:option value="PPA">PENERIMA PAJAKAN</s:option>
                                            <s:option value="PA">PEMEGANG AMANAH</s:option>                                            
                                        </c:when>
                                        <c:when  test="${fn:startsWith(actionBean.permohonan.kodUrusan.kod,'GPTD') || actionBean.permohonan.kodUrusan.kod eq 'MCGMB' 
                                                         || actionBean.permohonan.kodUrusan.kod eq 'MCMMK' || actionBean.permohonan.kodUrusan.kod eq 'CGADT'
                                                         || actionBean.permohonan.kodUrusan.kod eq 'MCPTD'}">
                                            <s:option value="PGA">PENERIMA GADAIAN</s:option>
                                            <s:option value="PA">PEMEGANG AMANAH</s:option>                                            
                                        </c:when>         
                                        <c:when  test="${actionBean.permohonan.kodUrusan.kod eq 'TMWNA' || actionBean.permohonan.kodUrusan.kod eq 'TMADT'
                                                         || actionBean.permohonan.kodUrusan.kod eq 'DPWNA' || actionBean.permohonan.kodUrusan.kod eq 'PMADT'
                                                         || actionBean.permohonan.kodUrusan.kod eq 'PMMAT'}">
                                            <s:option value="PTM">PENERIMA TURUN MILIK</s:option>
                                            <s:option value="PA">PEMEGANG AMANAH</s:option>
                                            <s:option value="PUH">PENGHUNI SEUMUR HIDUP</s:option>
                                        </c:when>
                                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'TTADT' || actionBean.permohonan.kodUrusan.kod eq 'BTADT'}">
                                            <s:option value="PYT">PIHAK YANG MENUNTUT</s:option>                                          
                                        </c:when>    
                                        <c:otherwise>
                                            <s:options-collection collection="${actionBean.senaraiKodPenerima}" label="nama" value="kod" />
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:when test="${actionBean.kodNegeri eq '04'}">
                                    <c:choose>
                                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PMKMM' || actionBean.permohonan.kodUrusan.kod eq 'PPTGM' 
                                                        || actionBean.permohonan.kodUrusan.kod eq 'PMJTL' || actionBean.permohonan.kodUrusan.kod eq 'PMMK1' 
                                                        || actionBean.permohonan.kodUrusan.kod eq 'PMMK2' || actionBean.permohonan.kodUrusan.kod eq 'PMPTD'}">
                                            <s:option value="PA">PEMEGANG AMANAH</s:option>
                                            <s:option value="PPM">PENERIMA PINDAH MILIK</s:option>
                                            <s:option value="PP">PENTADBIR</s:option>   
                                            <s:option value="WS">WASI</s:option>  
                                        </c:when>
                                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PJKMM' || actionBean.permohonan.kodUrusan.kod eq 'PJPTD'}">
                                            <s:option value="PA">PEMEGANG AMANAH</s:option>
                                            <s:option value="PPA">PENERIMA PAJAKAN</s:option>
                                        </c:when>
                                        <c:when  test="${actionBean.permohonan.kodUrusan.kod eq 'SWKMM' || actionBean.permohonan.kodUrusan.kod eq 'SWPTD'}">
                                            <s:option value="PA">PEMEGANG AMANAH</s:option>
                                            <s:option value="PSA">PENERIMA SEWAAN</s:option>
                                        </c:when>
                                        <c:when  test="${actionBean.permohonan.kodUrusan.kod eq 'MCKMM' || actionBean.permohonan.kodUrusan.kod eq 'MCPTG' 
                                                         || actionBean.permohonan.kodUrusan.kod eq 'MCPTD'}">
                                            <s:option value="PA">PEMEGANG AMANAH</s:option>
                                            <s:option value="PCA">PENERIMA CAGARAN</s:option>
                                        </c:when>
                                        <c:otherwise>
                                            <s:options-collection collection="${actionBean.senaraiKodPenerima}" label="nama" value="kod" />
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    <s:options-collection collection="${actionBean.senaraiKodPenerima}" label="nama" value="kod" />
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </s:select>
                </p>
                
                <p>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'MCKMM' || actionBean.permohonan.kodUrusan.kod eq 'MCPTG' 
                                  || actionBean.permohonan.kodUrusan.kod eq 'PJKMM' || actionBean.permohonan.kodUrusan.kod eq 'SWKMM' 
                                  || actionBean.permohonan.kodUrusan.kod eq 'PPTGM' || actionBean.permohonan.kodUrusan.kod eq 'PMKMM' 
                                  || actionBean.permohonan.kodUrusan.kod eq 'PJPTD' || actionBean.permohonan.kodUrusan.kod eq 'PMPTD' 
                                  || actionBean.permohonan.kodUrusan.kod eq 'MCPTD' || actionBean.permohonan.kodUrusan.kod eq 'SWPTD'}">
                    <label for="nama"><font color="red">*</font>Kategori Penerima :</label>
                        <s:select name="permohonanPihak.kodStatus" id="KatgPenerima" onchange="javaScript:changeKatgPenerima(this.options[selectedIndex].value);">>
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodMaklumatInduk}" label="nama" value="kod"/>
                        </s:select> 
                            
                    </c:if>
                </p>

                <p>
                    <label for="alamat"><font color="red">*</font>Alamat Berdaftar :</label>
                            <s:text name="pihak.alamat1" size="40" id="alamat1" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>

                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihak.alamat2" size="40" id="alamat2" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>

                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihak.alamat3" size="40" id="alamat3" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> Bandar :</label>
                    <s:text name="pihak.alamat4" size="40" id="alamat4" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>Poskod :</label>
                    <s:text name="pihak.poskod" size="40" maxlength="5" id="poskod" onkeyup="validateNumber(this,this.value);"/>
                </p>

                <p>
                    <label for="Negeri">Negeri :</label>
                    <s:select name="pihak.negeri.kod" id="negeri">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                    </s:select>
                </p>

                <%---------------TEST KOD BANGSA-----------------%>

                <p id="kodBangsaOrang">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PMADT' && actionBean.permohonan.kodUrusan.kod ne 'CGADT' && actionBean.permohonan.kodUrusan.kod ne 'PAADT' && actionBean.permohonan.kodUrusan.kod ne 'TMADT' && actionBean.permohonan.kodUrusan.kod ne 'BTADT'}">
                        <c:choose>
                            <c:when test="${(actionBean.kodNegeri eq '04' && actionBean.permohonan.kodUrusan.kod eq 'PMMK1')|| (actionBean.kodNegeri eq '04' && actionBean.permohonan.kodUrusan.kod eq 'PMMK2')}">
                                <label><font color="red">*</font>Bangsa :</label>
                                    </c:when>
                                    <c:otherwise>
                                <label>Bangsa :</label>
                            </c:otherwise>
                        </c:choose>
                        <s:select id="kodBangsaOrangData" name="pihak.bangsa.kod" style="width:400px" >
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodBangsaPerseorangan}" label="nama" value="kod"/>
                        </s:select>
                    </c:if>
                </p>
                <p id="kodBangsa">
                    <label>Bangsa/Jenis Syarikat :</label>
                    <s:select id="kodBangsaData" name="pihak.bangsa.kod" style="width:400px" >
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodBangsa}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p id="kodBangsaAdat">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMADT' || actionBean.permohonan.kodUrusan.kod eq 'CGADT' || actionBean.permohonan.kodUrusan.kod eq 'PAADT' || actionBean.permohonan.kodUrusan.kod eq 'TMADT' || actionBean.permohonan.kodUrusan.kod eq 'BTADT'}">
                        <label>Bangsa :</label>
                        <s:select id="kodBangsaAdatData" name="pihak.bangsa.kod" style="width:200px" >
                            <s:option value="MEL">MELAYU</s:option>
                        </s:select>
                    </c:if>
                </p>
                <p id="kodBangsaSyarikat">
                    <label>Jenis Syarikat :</label>
                    <s:select id="kodBangsaSyarikatData" name="pihak.bangsa.kod" style="width:400px">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodBangsaSyarikat}" label="nama" value="kod"/>
                    </s:select>
                </p>



                <%----------------------------%>
                <%--<p id="kodBangsa">
                    <label>Bangsa/Jenis Syarikat :</label>
                    <s:select name="pihak.bangsa.kod" style="width:400px" >
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodBangsa}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p id="kodBangsaOrang">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PMADT' && actionBean.permohonan.kodUrusan.kod ne 'CGADT' && actionBean.permohonan.kodUrusan.kod ne 'PAADT' && actionBean.permohonan.kodUrusan.kod ne 'TMADT' && actionBean.permohonan.kodUrusan.kod ne 'BTADT'}">
                        <label>Bangsa :</label>
                        <s:select name="pihak.bangsa.kod" style="width:400px" >
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodBangsaPerseorangan}" label="nama" value="kod"/>
                        </s:select>
                    </c:if>
                </p>
                <p id="kodBangsaAdat">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMADT' || actionBean.permohonan.kodUrusan.kod eq 'CGADT' || actionBean.permohonan.kodUrusan.kod eq 'PAADT' || actionBean.permohonan.kodUrusan.kod eq 'TMADT' || actionBean.permohonan.kodUrusan.kod eq 'BTADT'}">
                        <label>Bangsa :</label>
                        <s:select name="pihak.bangsa.kod" style="width:200px" >
                            <s:option value="MEL">MELAYU</s:option>
                        </s:select>
                    </c:if>
                </p>
                <p id="kodBangsaSyarikat">
                    <label>Jenis Syarikat :</label>
                    <s:select name="pihak.bangsa.kod" style="width:400px">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodBangsaSyarikat}" label="nama" value="kod"/>
                    </s:select>
                </p>--%>

                <c:if test="${actionBean.kodNegeri eq '05'}">
                    <p id="suku">
                        <c:choose>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'TMADT' || actionBean.permohonan.kodUrusan.kod eq 'TMWNA' ||
                                            actionBean.permohonan.kodUrusan.kod eq 'BTADT' || actionBean.permohonan.kodUrusan.kod eq 'CGADT' ||
                                            actionBean.permohonan.kodUrusan.kod eq 'PMADT'  || actionBean.permohonan.kodUrusan.kod eq 'TTADT' ||
                                            actionBean.permohonan.kodUrusan.kod eq 'PJADT'  || actionBean.permohonan.kodUrusan.kod eq 'PMWNA' ||
                                            actionBean.permohonan.kodUrusan.kod eq 'PMWWA' }">
                                    <label><font color="red">*</font>Jenis Suku :</label>
                                        </c:when>
                                        <c:otherwise>
                                    <label>Jenis Suku :</label>
                            </c:otherwise>
                        </c:choose>
                        <s:select name="pihak.suku.kod" style="width:200px" id="sukuData">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodSuku}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <p id="subSuku">
                        <label for="subSuku">Pecahan Suku/Lengkongan :</label>
                        <s:text name="pihak.subSuku" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p id="perut">
                        <label for="perut">Perut :</label>
                        <s:text name="pihak.keturunan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p id="luak">
                        <c:choose>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'TMADT' || actionBean.permohonan.kodUrusan.kod eq 'TMWNA' ||
                                            actionBean.permohonan.kodUrusan.kod eq 'BTADT' || actionBean.permohonan.kodUrusan.kod eq 'CGADT' ||
                                            actionBean.permohonan.kodUrusan.kod eq 'PMADT'  || actionBean.permohonan.kodUrusan.kod eq 'TTADT' ||
                                            actionBean.permohonan.kodUrusan.kod eq 'PJADT'  || actionBean.permohonan.kodUrusan.kod eq 'PMWNA' ||
                                            actionBean.permohonan.kodUrusan.kod eq 'PMWWA' }">
                                    <label><font color="red">*</font>Luak :</label>
                                        </c:when>
                                        <c:otherwise>
                                    <label>Luak :</label>
                            </c:otherwise>
                        </c:choose>
                        <s:select name="pihak.tempatSuku" style="width:250px" id="luakData">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodLuak}" label="nama" value="nama"/>
                        </s:select>                      
                    </p>
                </c:if>
                <c:if test="${(actionBean.kodNegeri eq '04' && actionBean.permohonan.kodUrusan.kod eq 'PMMK1') || (actionBean.kodNegeri eq '04' && actionBean.permohonan.kodUrusan.kod eq 'PMMK2')}">
                    <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L'
                                  || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T'
                                  || actionBean.pihak.jenisPengenalan.kod eq 'I' || actionBean.pihak.jenisPengenalan.kod eq 'K'}">
                          <p>
                              <label><font color="red">*</font>Jantina :</label>
                                      <s:select name="pihak.kodJantina" style="width:200px">
                                          <s:option value="">Sila Pilih</s:option>
                                  <s:options-collection collection="${list.senaraiKodJantina}" label="nama" value="kod"/>
                              </s:select>
                          </p>
                          <p>
                              <%--<label><font color="red">*</font>Tempoh Mastautin (Tahun) :</label>--%>
                              <label>Tempoh Mastautin (Tahun) :</label>
                              <s:text name="pemohon.tempohMastautin" size="10" maxlength="3" id="mastautin" onkeyup="validateNumber(this,this.value);"/>
                          </p>
                    </c:if>

                    <c:if test="${actionBean.pihak.jenisPengenalan.kod ne 'B' && actionBean.pihak.jenisPengenalan.kod ne 'L'
                                  && actionBean.pihak.jenisPengenalan.kod ne 'P' && actionBean.pihak.jenisPengenalan.kod ne 'T'
                                  && actionBean.pihak.jenisPengenalan.kod ne 'I' && actionBean.pihak.jenisPengenalan.kod ne 'K'}">
                          <p>
                              <label><font color="red">*</font>Tarikh Ditubuhkan :</label>
                              <s:text name="tarikhLahir" size="40" id="datepicker" class="datepicker" />&nbsp;<em style="font-style:normal">(cth : hh / bb / tttt)</em>
                          </p>
                    </c:if>

                </c:if>

                <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L'
                              || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T'
                              || actionBean.pihak.jenisPengenalan.kod eq 'I' || actionBean.pihak.jenisPengenalan.kod eq 'K'}">
                      <p>
                          <label>Tarikh Lahir :</label>
                          <s:text name="tarikhLahir" size="40" id="datepicker" class="datepicker" onchange="calAgeByDOB(this.value);"/>&nbsp;<em style="font-style:normal">(cth : hh / bb / tttt)</em>
                      </p>
                      <p>
                          <label>Umur 2:</label>
                          <s:text name="permohonanPihak.umur" size="10" maxlength="3" id="umur" onkeyup="validateNumber(this,this.value);"/>
                      </p>
                      <p>
                          <%--<c:choose>
                              <c:when test="${(actionBean.kodNegeri eq '04' && actionBean.permohonan.kodUrusan.kod eq 'PMMK1') || (actionBean.kodNegeri eq '04' && actionBean.permohonan.kodUrusan.kod eq 'PMMK2')}">
                                  <label><font color="red">*</font>Tempat Lahir :</label>
                              </c:when>
                              <c:otherwise>
                                  <label>Tempat Lahir :</label>
                              </c:otherwise>
                          </c:choose>--%>
                          <label>Tempat Lahir :</label>
                          <s:select name="pihak.tempatLahir" id="tempatLahir" onchange="javaScript:changeTempatLahir(this.options[selectedIndex].text);">
                              <s:option value="">Sila Pilih</s:option>
                              <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="nama"/>
                              <s:option value="LAIN-LAIN">Lain-lain</s:option>
                          </s:select>
                      </p>
                      <p id="tempatLahirLain">
                          <c:choose>
                              <c:when test="${(actionBean.kodNegeri eq '04' && actionBean.permohonan.kodUrusan.kod eq 'PMMK1')|| (actionBean.kodNegeri eq '04' && actionBean.permohonan.kodUrusan.kod eq 'PMMK2')}">
                                  <label><font color="red">*</font>Lain-lain Tempat Lahir :</label>
                                      </c:when>
                                      <c:otherwise>
                                  <label>Lain-lain Tempat Lahir :</label>
                              </c:otherwise>
                          </c:choose>
                          <s:text name="tempatLahirLain" id="tempatLahirLainPenerima" size="40" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                      </p>

                      <%--                      <p>
                                                <label>Tempat Lahir :</label>
                                                <s:select name="pihak.tempatLahir" id="tempatLahir" onchange="javaScript:changeTempatLahir(this.options[selectedIndex].text);">
                                                    <s:option value="">Sila Pilih</s:option>
                                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="nama"/>
                                                    <s:option value="LAIN-LAIN">Lain-lain</s:option>
                                                </s:select>
                                            </p>
                                            <p id="tempatLahirLain">
                                                <label>Lain-lain Tempat Lahir :</label>
                                                <s:text name="tempatLahirLain" size="40" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                                            </p>
                      --%>
                      <p>
                          <label>Status Perkahwinan :</label>
                          <s:select name="permohonanPihak.statusKahwin">
                              <s:option value="">Sila Pilih</s:option>
                              <s:option>Berkahwin</s:option>
                              <s:option>Bujang</s:option>
                              <s:option>Duda</s:option>
                              <s:option>Ibu Tunggal</s:option>
                          </s:select>
                      </p>


                      <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PMADT' && actionBean.permohonan.kodUrusan.kod ne 'CGADT' && actionBean.permohonan.kodUrusan.kod ne 'PAADT' && actionBean.permohonan.kodUrusan.kod ne 'TMADT' && actionBean.permohonan.kodUrusan.kod ne 'BTADT'}">
                          <p>
                              <label>Pekerjaan :</label>
                              <s:text name="permohonanPihak.pekerjaan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                          </p>
                          <p>
                              <%--          <c:choose>
                                            <c:when test="${(actionBean.kodNegeri eq '04' && actionBean.permohonan.kodUrusan.kod eq 'PMMK1') || (actionBean.kodNegeri eq '04' && actionBean.permohonan.kodUrusan.kod eq 'PMMK2')}">
                                                <label><font color="red">*</font>Pendapatan Bulanan :</label>
                                            </c:when>
                                            <c:otherwise>
                                                <label>Pendapatan Bulanan :</label>
                                            </c:otherwise>
                              </c:choose>--%>
                              <label>Pendapatan Bulanan :</label>
                              <s:select name="permohonanPihak.kodMataWang.kod" id="kodMatawang">
                                  <s:option value="">Sila Pilih</s:option>
                                  <s:options-collection collection="${list.senaraiKodMatawang}" label="nama" value="kod"/>
                              </s:select>
                              <s:text name="permohonanPihak.pendapatan" id="pendapatan" size="14" maxlength="12" formatPattern="####.00"  onchange="convert(this.value, this.id);"  onkeyup="validateNumber(this,this.value);"/>
                          </p>

                          <p>
                              <label>Tanggungan :</label>
                              <s:text name="permohonanPihak.tangungan" size="40" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                          </p>
                      </c:if>
                </c:if>
                <p>
                    <label>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PMADT' && actionBean.permohonan.kodUrusan.kod ne 'CGADT' && actionBean.permohonan.kodUrusan.kod ne 'PAADT' && actionBean.permohonan.kodUrusan.kod ne 'TMADT' && actionBean.permohonan.kodUrusan.kod ne 'BTADT' && actionBean.permohonan.kodUrusan.kod ne 'TMWNA'}">
                            Hubungan Dengan Pemohon :
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMADT' || actionBean.permohonan.kodUrusan.kod eq 'CGADT' || actionBean.permohonan.kodUrusan.kod eq 'PAADT' || actionBean.permohonan.kodUrusan.kod eq 'TMADT' || actionBean.permohonan.kodUrusan.kod eq 'BTADT' || actionBean.permohonan.kodUrusan.kod eq 'TMWNA'}">
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TMADT' || actionBean.permohonan.kodUrusan.kod eq 'TMWNA'}">
                                Hubungan Dengan Simati :
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'TMADT' && actionBean.permohonan.kodUrusan.kod ne 'TMWNA'}">
                                Hubungan Dengan Tuan Tanah :
                            </c:if>
                        </c:if>
                    </label>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PMADT' && actionBean.permohonan.kodUrusan.kod ne 'CGADT' && actionBean.permohonan.kodUrusan.kod ne 'PAADT' && actionBean.permohonan.kodUrusan.kod ne 'TMADT' && actionBean.permohonan.kodUrusan.kod ne 'BTADT' && actionBean.permohonan.kodUrusan.kod ne 'TMWNA'}">
                        <s:select name="permohonanPihak.kaitan" id="hubungan" onchange="javaScript:changeHubungan(this.options[selectedIndex].text);">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option>Ibu Bapa Kepada Anak</s:option>
                            <s:option>Anak Kepada Ibu Bapa</s:option>
                            <s:option>Diri Sendiri</s:option>
                            <s:option>Suami Kepada Isteri</s:option>
                            <s:option>Isteri Kepada Suami</s:option>
                            <s:option>Saudara-mara</s:option>
                            <s:option>Adik-beradik</s:option>
                            <s:option>Penjual / Pembeli</s:option>
                            <s:option>Lain-lain</s:option>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMADT' || actionBean.permohonan.kodUrusan.kod eq 'CGADT' || actionBean.permohonan.kodUrusan.kod eq 'PAADT' || actionBean.permohonan.kodUrusan.kod eq 'TMADT' || actionBean.permohonan.kodUrusan.kod eq 'BTADT' || actionBean.permohonan.kodUrusan.kod eq 'TMWNA'}">
                        <s:select name="permohonanPihak.kaitan" id="hubungan" onchange="javaScript:changeHubungan(this.options[selectedIndex].text);">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option>Anak</s:option>
                            <s:option>Adik Beradik Sesuku</s:option>
                            <s:option>Cucu</s:option>
                            <s:option>Ibu Kandung</s:option>
                            <s:option>Ibu Saudara</s:option>
                            <s:option>Moyang</s:option>
                            <s:option>Nenek</s:option>
                            <s:option>Sepupu</s:option>
                            <s:option>Lain-lain</s:option>
                        </s:select>
                    </c:if>
                </p>

                <p id="hubunganLain">
                    <label>Lain-lain Hubungan :</label>
                    <s:text name="hubunganLain" size="40" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>

                <p>
                    <label>&nbsp;</label>
                    <input type="checkbox" id="add1" name="add1" value="" onclick="copyAdd();"/>
                    <font color="red">Alamat surat sama dengan alamat berdaftar.</font>
                </p>

                <p>
                    <label for="alamat"><font color="red">*</font>Alamat Surat-Menyurat :</label>
                            <s:text name="pihakAlamatTamb.alamatKetiga1" id="suratAlamat1" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakAlamatTamb.alamatKetiga2" id="suratAlamat2" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakAlamatTamb.alamatKetiga3" id="suratAlamat3" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> Bandar :</label>
                    <s:text name="pihakAlamatTamb.alamatKetiga4" id="suratAlamat4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>Poskod :</label>
                    <s:text name="pihakAlamatTamb.alamatKetigaPoskod" id="suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                </p>

                <p>
                    <label for="Negeri">Negeri :</label>
                    <s:select name="pihakAlamatTamb.alamatKetigaNegeri.kod" id="suratNegeri">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <c:if test="${actionBean.pihak.jenisPengenalan.kod ne 'B' && actionBean.pihak.jenisPengenalan.kod ne 'L'
                              && actionBean.pihak.jenisPengenalan.kod ne 'P' && actionBean.pihak.jenisPengenalan.kod ne 'T'
                              && actionBean.pihak.jenisPengenalan.kod ne 'I' && actionBean.pihak.jenisPengenalan.kod ne 'K'}">

                      <%--=========================TABLE CAWANGAN PENERIMA=========================--%>
                      <div class="content" align="center" id="cawangan">
                          <span class=instr><em>Sila klik pada imej <img alt="" src='${pageContext.request.contextPath}/images/tambah.png'/> untuk menambah cawangan baru.</em></span><br/>
                          <br>
                          Maklumat Cawangan
                          <display:table class="tablecloth" name="${actionBean.cawanganList}" cellpadding="0" cellspacing="0"
                                         id="cawTable" pagesize="10"
                                         requestURI="${pageContext.request.contextPath}/consent/pihak_kepentingan" style="width:90%">
                              <display:column title="Pilih"><s:radio name="pilih" value="${cawTable.idPihakCawangan}"/></display:column>
                              <display:column property="namaCawangan" title="Nama"/>
                              <display:column property="alamat1" title="Alamat1"/>
                              <display:column property="alamat2" title="Alamat2"/>
                              <display:column property="alamat3" title="Alamat3"/>
                              <display:column property="alamat4" title="Bandar"/>
                              <display:column property="poskod" title="Poskod"/>
                              <display:column property="negeri.nama" title="Negeri"/>
                              <display:column title="Kemaskini">
                                  <div align="center">
                                      <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                           onclick="editCawangan(${cawTable_rowNum}, ${cawTable.idPihakCawangan})" onmouseover="this.style.cursor = 'pointer';">
                                  </div>
                              </display:column>
                              <display:column title="Hapus">
                                  <div align="center">
                                      <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                           id='rem_caw_${line_rowNum}' onclick="removeCawanganPemohon('${cawTable.idPihakCawangan}', this.id)" onmouseover="this.style.cursor = 'pointer';">
                                  </div>
                              </display:column>
                              <display:column title="<img alt='Tambah Cawangan' id='addCaw'
                                              src='${pageContext.request.contextPath}/images/tambah.png' onmouseover=\"this.style.cursor='pointer';\"/>"/>
                          </display:table>
                          <br>
                      </div>
                      <%--=========================TABLE AHLI LEMBAGA PENGARAH=========================--%>
                      <div class="content" align="center" id="pengarah">
                          <span class=instr><em>Sila klik pada imej <img alt="" src='${pageContext.request.contextPath}/images/tambah.png'/> untuk menambah ahli lembaga pengarah baru.</em></span><br/>
                          <br>
                          Maklumat Ahli Lembaga Pengarah
                          <display:table class="tablecloth" name="${actionBean.pengarahList}" cellpadding="0" cellspacing="0"
                                         id="pengarahTable" pagesize="10" requestURI="${pageContext.request.contextPath}/consent/pihak_kepentingan">
                              <%--  <display:column title="Bil" sortable="true">${pengarahTable_rowNum}</display:column>--%>

                              <display:column title="Nama" class="nama" property="nama"></display:column>
                              <display:column title="Jenis Pengenalan" property="jenisPengenalan.nama"></display:column>
                              <display:column title="Nombor Pengenalan" property="noPengenalan"></display:column>
                              <display:column title="Warganegara" property="warganegara.nama"></display:column>
                              <display:column title="Kemaskini">
                                  <div align="center">
                                      <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                           onmouseover="this.style.cursor = 'pointer';" onclick="editPengarah(${pengarahTable_rowNum}, ${pengarahTable.idPengarah})">
                                  </div>
                              </display:column>
                              <display:column title="Hapus">
                                  <div align="center">
                                      <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                           id='rem_caw_${line_rowNum}' onclick="removePengarah('${pengarahTable.idPengarah}', this.id)" onmouseover="this.style.cursor = 'pointer';">
                                  </div>
                              </display:column>
                              <display:column title="<img alt='Tambah Ahli Lembaga Pengarah' id='addPengarah'
                                              src='${pageContext.request.contextPath}/images/tambah.png' onmouseover=\"this.style.cursor='pointer';\"/>"/>
                          </display:table>
                          <br/>
                      </div>
                </c:if>

                <%--=========================TABLE PEMEGANG AMANAH=========================--%>
                <div id="pgAmanah" align="center">
                    <span class=instr><em>Sila klik pada imej <img alt="" src='${pageContext.request.contextPath}/images/tambah.png'/> untuk menambah penerima.</em></span><br/>
                    <div align="center">
                        <display:table class="tablecloth" name="${actionBean.senaraiPihakHubungan}" cellpadding="0" cellspacing="0"
                                       id="pgAmanahTable" pagesize="10"                                       requestURI="${pageContext.request.contextPath}/consent/pihak_kepentingan">
                            <display:column title="Nama" class="nama" property="nama"></display:column>
                            <display:column title="Jenis Pengenalan" property="jenisPengenalan.nama"></display:column>
                            <display:column title="Nombor Pengenalan" property="noPengenalan"></display:column>
                            <display:column title="Warganegara" property="wargaNegara.nama"></display:column>
                            <display:column title="Alamat 1" property="alamat1"></display:column>
                            <display:column title="Alamat 2" property="alamat2"></display:column>
                            <display:column title="Alamat 3" property="alamat3"></display:column>
                            <display:column title="Bandar" property="alamat4"></display:column>
                            <display:column title="Poskod" property="poskod"></display:column>
                            <display:column title="Negeri">
                                <c:if test="${pgAmanahTable.negeri ne null}">
                                    ${pgAmanahTable.negeri.nama}
                                </c:if>
                                <c:if test="${pgAmanahTable.negara ne null}">
                                    ${pgAmanahTable.negara.nama}
                                </c:if>
                            </display:column>
                            <display:column title="Syer"> 
                                <div align="center"> ${pgAmanahTable.syerPembilang}/${pgAmanahTable.syerPenyebut}</div>
                            </display:column>
                            <display:column title="Kemaskini">
                                <div align="center">
                                    <img alt='Klik Untuk Kemaskini' id='editPA' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                         onmouseover="this.style.cursor = 'pointer';" onclick="editPemegangAmanah(${pgAmanahTable_rowNum}, ${pgAmanahTable.idHubungan})">
                                </div>
                            </display:column>
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_amanah_${pgAmanahTable_rowNum}' onclick="removeAmanah('${pgAmanahTable.idHubungan}', this.id)" onmouseover="this.style.cursor = 'pointer';">
                                </div>
                            </display:column>
                            <display:column title="<img alt='Tambah Penerima' id='addPA'
                                            src='${pageContext.request.contextPath}/images/tambah.png' onmouseover=\"this.style.cursor='pointer';\"/>"/>
                        </display:table>
                    </div>
                    <br/>
                </div>
                <%--=========================PEMEGANG AMANAH DIALOG=========================--%>
                <div id="pgAmanahDialog" style="display: none" align="left" title="Tambah Penerima">
                    <p align="left">
                        <label for="nama"><font color="red">*</font>Nama :</label>
                                <s:text name="permohonanPihakHubungan.nama" id="namaAmanah" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>

                    <p>
                        <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                <s:select name="permohonanPihakHubungan.jenisPengenalan.kod" id="kpAmanah">
                                    <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <p>
                        <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                                <s:text name="permohonanPihakHubungan.noPengenalan" id="nokpAmanah" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Warganegara :</label>
                        <s:select name="permohonanPihakHubungan.wargaNegara.kod" style="width:200px" id="warganegaraAmanah">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <p>
                        <label for="alamat"><font color="red">*</font>Alamat :</label>
                                <s:text name="permohonanPihakHubungan.alamat1" size="40" id="alamatAmanah1" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="permohonanPihakHubungan.alamat2" size="40" id="alamatAmanah2" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="permohonanPihakHubungan.alamat3" size="40" id="alamatAmanah3" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Bandar :</label>
                        <s:text name="permohonanPihakHubungan.alamat4" size="40" id="alamatAmanah4" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>

                    <p>
                        <label>Poskod :</label>
                        <s:text name="permohonanPihakHubungan.poskod" size="40" maxlength="5" id="poskodAmanah" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                    <p>
                        <label for="Negeri"><font color="red">*</font>Negeri :</label>
                                <s:select name="permohonanPihakHubungan.alamat4" id="negeriAmanah" onchange="javaScript:changeNegeriAmanah(this.options[selectedIndex].text);">
                                    <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="nama"/>
                            <s:option>LAIN-LAIN</s:option>
                        </s:select>
                    </p>
                    <p id="negeriAmanahLain_p">
                        <label for="Negeri"><font color="red">*</font>Negara :</label>
                                <s:select name="permohonanPihakHubungan.negara.kod" id="negaraAmanah">
                                    <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodNegara}" label="nama" value="nama"/>
                        </s:select>
                    </p>
                    <p>
                        <label for="Negeri"><font color="red">*</font>Syer :</label>
                        <s:text name="syerPembilang" size="5" id="syerPembilangAmanah"/> /
                        <s:text name="syerPenyebut" size="5" id="syerPenyebutAmanah"/>
                    </p>
                </div>
                <%--=========================PEMEGANG AMANAH DIALOG EDIT =========================--%>
                <div id="pgAmanahDialogEdit" style="display: none" align="left" title="Kemaskini Penerima">
                    <s:hidden name="permohonanPihakHubungan.idPihakHubungan" id="idAmanahEdit"/>
                    <p align="left">
                        <label for="nama"><font color="red">*</font>Nama :</label>
                                <s:text name="permohonanPihakHubungan.nama" id="namaAmanahEdit" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>

                    <p>
                        <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                <s:select name="permohonanPihakHubungan.jenisPengenalan.kod" id="kpAmanahEdit">
                                    <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <p>
                        <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                                <s:text name="permohonanPihakHubungan.noPengenalan" id="nokpAmanahEdit" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Warganegara :</label>
                        <s:select name="permohonanPihakHubungan.wargaNegara.kod" style="width:200px" id="warganegaraAmanahEdit">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <p>
                        <label for="alamat"><font color="red">*</font>Alamat :</label>
                                <s:text name="permohonanPihakHubungan.alamat1" size="40" id="alamatAmanah1Edit" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="permohonanPihakHubungan.alamat2" size="40" id="alamatAmanah2Edit" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="permohonanPihakHubungan.alamat3" size="40" id="alamatAmanah3Edit" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Bandar</label>
                        <s:text name="permohonanPihakHubungan.alamat4" size="40" id="alamatAmanah4Edit" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>

                    <p>
                        <label>Poskod :</label>
                        <s:text name="permohonanPihakHubungan.poskod" size="40" maxlength="5" id="poskodAmanahEdit" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                    <p>
                        <label for="Negeri"><font color="red">*</font>Negeri :</label>
                                <s:select name="permohonanPihakHubungan.negeri.nama" id="negeriAmanahEdit" onchange="javaScript:changeNegeriAmanahEdit(this.options[selectedIndex].text);">
                                    <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="nama"/>
                            <s:option>LAIN-LAIN</s:option>
                        </s:select>
                    </p>
                    <p id="negeriAmanahLain_p_edit">
                        <label for="Negera"><font color="red">*</font>Negara :</label>
                                <s:select name="permohonanPihakHubungan.negara.nama" id="negaraAmanahEdit">
                                    <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodNegara}" label="nama" value="nama"/>
                        </s:select>
                    </p>
                    <p>
                        <label for="Negeri"><font color="red">*</font>Syer :</label>
                        <s:text name="syerPembilang" size="5" id="syerPembilangAmanahEdit"/> /
                        <s:text name="syerPenyebut" size="5" id="syerPenyebutAmanahEdit"/>
                    </p>
                </div>
                <%--=========================AHLI LEMBAGA PENGARAH DIALOG=========================--%>
                <div id="pengarahDialog" style="display: none" align="left" title="Tambah Ahli Lembaga Pengarah">
                    <p align="left">
                        <label for="nama"><font color="red">*</font>Nama :</label>
                                <s:text name="pihakPengarah.nama" id="namaPengarah" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                <s:select name="pihakPengarah.jenisPengenalan.kod" id="kpPengarah">
                                    <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <p>
                        <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                                <s:text name="pihakPengarah.noPengenalan" id="noKpPengarah" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label><font color="red">*</font>Warganegara :</label>
                                <s:select name="pihakPengarah.warganegara.kod" style="width:200px" id="wargaPengarah">
                                    <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                </div>
                <%--=========================AHLI LEMBAGA PENGARAH DIALOG EDIT=========================--%>
                <div id="pengarahDialogEdit" style="display: none" align="left" title="Kemaskini Ahli Lembaga Pengarah">
                    <s:hidden name="pihakPengarah.idPengarah" id="idPengarahEdit"/>
                    <s:hidden name="pengguna.idPengguna" id="idPengguna"/>
                    <p align="left">
                        <label for="nama"><font color="red">*</font>Nama :</label>
                                <s:text name="pihakPengarah.nama" id="namaPengarahEdit" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                <s:select name="pihakPengarah.jenisPengenalan.kod" id="kpPengarahEdit">
                                    <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <p>
                        <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                                <s:text name="pihakPengarah.noPengenalan" id="noKpPengarahEdit" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label><font color="red">*</font>Warganegara :</label>
                                <s:select name="pihakPengarah.warganegara.kod" style="width:200px" id="wargaPengarahEdit">
                                    <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                </div>
                <%--=========================CAWANGAN DIALOG========================--%>
                <div id="cawDialog" style="display: none" align="left" title="Tambah Cawangan">
                    <p>
                        <label for="nama"><font color="red">*</font>Nama :</label>
                                <s:text name="pihakCawangan.namaCawangan" id="namaCaw" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label for="alamat"><font color="red">*</font>Alamat Berdaftar :</label>
                                <s:text name="pihakCawangan.alamat1" id="alamatCaw1" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>

                    <p>
                        <label> &nbsp;</label>
                        <s:text name="pihakCawangan.alamat2" id="alamatCaw2" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>

                    <p>
                        <label> &nbsp;</label>
                        <s:text name="pihakCawangan.alamat3" id="alamatCaw3" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Bandar :</label>
                        <s:text name="pihakCawangan.alamat4" id="alamatCaw4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Poskod :</label>
                        <s:text name="pihakCawangan.poskod" size="40" id="poskodCaw" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                    <p>
                        <label for="Negeri"><font color="red">*</font>Negeri :</label>
                                <s:select name="pihakCawangan.negeri.kod" id="negeriCaw">
                                    <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <%-- <p>
                         <label for="alamat">Nombor Telefon :</label>
                         <s:text name="pihakCawangan.noTelefon1" id="telCaw" size="40" maxlength="12" onkeyup="validateNumber(this,this.value);"/>
                     </p>--%>
                </div>

                <%--=========================CAWANGAN DIALOG EDIT========================--%>
                <div id="cawDialogEdit" style="display: none" align="left" title="Kemaskini Cawangan">
                    <s:hidden name="pihakCawangan.idCawangan" id="idCawEdit"/>
                    <p>
                        <label for="nama"><font color="red">*</font>Nama :</label>
                                <s:text name="pihakCawangan.namaCawangan" id="namaCawEdit" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label for="alamat"><font color="red">*</font>Alamat Berdaftar :</label>
                                <s:text name="pihakCawangan.alamat1" id="alamatCaw1Edit" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>

                    <p>
                        <label> &nbsp;</label>
                        <s:text name="pihakCawangan.alamat2" id="alamatCaw2Edit" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>

                    <p>
                        <label> &nbsp;</label>
                        <s:text name="pihakCawangan.alamat3" id="alamatCaw3Edit" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Bandar :</label>
                        <s:text name="pihakCawangan.alamat4" id="alamatCaw4Edit" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Poskod :</label>
                        <s:text name="pihakCawangan.poskod" size="40" id="poskodCawEdit" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                    <p>
                        <label for="Negeri"><font color="red">*</font>Negeri :</label>
                                <s:select name="pihakCawangan.negeri.kod" id="negeriCawEdit">
                                    <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <%--  <p>
                          <label for="alamat">Nombor Telefon :</label>
                          <s:text name="pihakCawangan.noTelefon1" id="telCawEdit" size="40" maxlength="12" onkeyup="validateNumber(this,this.value);"/>
                      </p>--%>
                </div>
                <p align="center">
                    <s:button name="simpanKemaskiniPenerima" class="btn" value="Simpan" onclick="save(this.name, this.form);"/>
                    <s:button name="close" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
                <br/>
            </fieldset>
        </c:if>

        <%--================================================PEMOHON===============================================--%>
        <c:if test="${pemohon}">
            <fieldset class="aras1">
                <legend>
                    Kemaskini Maklumat Pemohon
                </legend>
                <em>Semua yang bertanda * adalah wajib dimasukkan.</em>
                <c:set var="disabled" value="disabled"/>
                <c:if test="${newPihak}">
                    <c:set var="disabled" />
                </c:if>
                <p>
                    <label for="nama"><font color="red">*</font>Nama :</label>
                            <s:text name="pihak.nama" id="nama_pemohon" size="62" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>

                <p>
                    <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                            <s:select name="pihak.jenisPengenalan.kod" id="jenisKP" onchange="javaScript:changeJenisKP(this.options[selectedIndex].value)">
                                <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                    </s:select>
                </p>

                <p>
                    <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                    <s:text name="pihak.noPengenalan" id="kp" size="40" onkeyup="this.value=this.value.toUpperCase();dodacheck(this.value);" onchange="changeIcNumber(this.value);"/>&nbsp;<em style="font-style:normal">(cth : 800620125177)</em>
                </p>
                <p>
                    <label>Jenis Pengenalan Lain :</label>
                            <s:select name="pihak.jenisPengenalanLain.kod" id="jenisKPLain" onchange="this.options[selectedIndex].value">
                                <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                            </s:select>
                </p>
                <p>
                    <label>Nombor Pengenalan Lain :</label>
                    <s:text name="pihak.noPengenalanLain" id="kpl" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <%--                <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L'
                                              || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T'
                                              || actionBean.pihak.jenisPengenalan.kod eq 'I' || actionBean.pihak.jenisPengenalan.kod eq 'K'}">
                                      <p>
                                          <label><font color="red">*</font>Kewarganegaraan :</label>
                                          <s:select name="pihak.wargaNegara.kod" style="width:200px" id="kod_warganegara">
                                              <s:option value="">Sila Pilih</s:option>
                                              <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                                          </s:select>
                                      </p>
                                </c:if>--%>

                <p>
                    <c:choose>
                        <c:when test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L'
                                        || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T'
                                        || actionBean.pihak.jenisPengenalan.kod eq 'I' || actionBean.pihak.jenisPengenalan.kod eq 'K'}">
                                <label><font color="red">*</font>Kewarganegaraan :</label>
                                    </c:when>
                                    <c:otherwise>
                                <label>Negara Syarikat :</label>
                        </c:otherwise>
                    </c:choose>
                    <s:select name="pihak.wargaNegara.kod" style="width:200px" id="kod_warganegara">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                    </s:select>
                </p>
                
                <p>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'MCKMM' || actionBean.permohonan.kodUrusan.kod eq 'MCPTG' 
                                  || actionBean.permohonan.kodUrusan.kod eq 'PJKMM' || actionBean.permohonan.kodUrusan.kod eq 'SWKMM' 
                                  || actionBean.permohonan.kodUrusan.kod eq 'PPTGM' || actionBean.permohonan.kodUrusan.kod eq 'PMKMM' 
                                  || actionBean.permohonan.kodUrusan.kod eq 'PJPTD' || actionBean.permohonan.kodUrusan.kod eq 'PMPTD' 
                                  || actionBean.permohonan.kodUrusan.kod eq 'MCPTD' || actionBean.permohonan.kodUrusan.kod eq 'SWPTD'}">
                    <label for="nama"><font color="red">*</font>Kategori Pemohon:</label>
                        <s:select name="pemohon.kodStatus" id="KatgPemohon" onchange="javaScript:changeKatgPemohon(this.options[selectedIndex].value);">>
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodMaklumatInduk}" label="nama" value="kod"/>
                        </s:select> 
                            
                    </c:if>
                </p>
                
                <p>
                    <label for="alamat"><font color="red">*</font>Alamat Berdaftar :</label>
                            <s:text name="pihak.alamat1" size="40" id="alamat1" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>

                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihak.alamat2" size="40" id="alamat2" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>

                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihak.alamat3" size="40" id="alamat3" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> Bandar :</label>
                    <s:text name="pihak.alamat4" size="40" id="alamat4" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>Poskod :</label>
                    <s:text name="pihak.poskod" size="40" maxlength="5" id="poskod" onkeyup="validateNumber(this,this.value);"/>
                </p>

                <p>
                    <label for="Negeri">Negeri :</label>
                    <s:select name="pihak.negeri.kod" id="negeri">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p id="kodBangsaOrang">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PMADT' && actionBean.permohonan.kodUrusan.kod ne 'CGADT' && actionBean.permohonan.kodUrusan.kod ne 'PAADT' && actionBean.permohonan.kodUrusan.kod ne 'TMADT' && actionBean.permohonan.kodUrusan.kod ne 'BTADT'}">
                        <label>Bangsa :</label>
                        <s:select name="pihak.bangsa.kod" style="width:400px" >
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodBangsaPerseorangan}" label="nama" value="kod"/>
                        </s:select>
                    </c:if>
                </p>
                <p id="kodBangsa">
                    <label>Bangsa/Jenis Syarikat :</label>
                    <s:select name="pihak.bangsa.kod" style="width:400px" >
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodBangsa}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p id="kodBangsaAdat">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMADT' || actionBean.permohonan.kodUrusan.kod eq 'CGADT' || actionBean.permohonan.kodUrusan.kod eq 'PAADT' || actionBean.permohonan.kodUrusan.kod eq 'TMADT' || actionBean.permohonan.kodUrusan.kod eq 'BTADT'}">
                        <label>Bangsa :</label>
                        <s:select name="pihak.bangsa.kod" style="width:200px" >
                            <s:option value="MEL">MELAYU</s:option>
                        </s:select>
                    </c:if>
                </p>
                <p id="kodBangsaSyarikat">
                    <label>Jenis Syarikat :</label>
                    <s:select name="pihak.bangsa.kod" style="width:400px">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodBangsaSyarikat}" label="nama" value="kod"/>
                    </s:select>
                </p>

                <c:if test="${actionBean.kodNegeri eq '05'}">
                    <p id="suku">
                        <c:choose>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'TMADT' || actionBean.permohonan.kodUrusan.kod eq 'TMWNA' ||
                                            actionBean.permohonan.kodUrusan.kod eq 'BTADT' || actionBean.permohonan.kodUrusan.kod eq 'CGADT' ||
                                            actionBean.permohonan.kodUrusan.kod eq 'PMADT'  || actionBean.permohonan.kodUrusan.kod eq 'TTADT' ||
                                            actionBean.permohonan.kodUrusan.kod eq 'PJADT'  || actionBean.permohonan.kodUrusan.kod eq 'PMWNA' ||
                                            actionBean.permohonan.kodUrusan.kod eq 'PMWWA' }">
                                    <label><font color="red">*</font>Jenis Suku :</label>
                                        </c:when>
                                        <c:otherwise>
                                    <label>Jenis Suku :</label>
                            </c:otherwise>
                        </c:choose>
                        <s:select name="pihak.suku.kod" style="width:200px" id="sukuData">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodSuku}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <p id="subSuku">
                        <label for="subSuku">Pecahan Suku/Lengkongan :</label>
                        <s:text name="pihak.subSuku" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p id="perut">
                        <label for="perut">Perut :</label>
                        <s:text name="pihak.keturunan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p id="luak">
                        <c:choose>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'TMADT' || actionBean.permohonan.kodUrusan.kod eq 'TMWNA' ||
                                            actionBean.permohonan.kodUrusan.kod eq 'BTADT' || actionBean.permohonan.kodUrusan.kod eq 'CGADT' ||
                                            actionBean.permohonan.kodUrusan.kod eq 'PMADT'  || actionBean.permohonan.kodUrusan.kod eq 'TTADT' ||
                                            actionBean.permohonan.kodUrusan.kod eq 'PJADT'  || actionBean.permohonan.kodUrusan.kod eq 'PMWNA' ||
                                            actionBean.permohonan.kodUrusan.kod eq 'PMWWA' }">
                                    <label><font color="red">*</font>Luak :</label>
                                        </c:when>
                                        <c:otherwise>
                                    <label>Luak :</label>
                            </c:otherwise>
                        </c:choose>
                        <s:select name="pihak.tempatSuku" style="width:250px" id="luakData">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodLuak}" label="nama" value="nama"/>
                        </s:select>
                    </p>
                </c:if>
                <c:if test="${(actionBean.kodNegeri eq '04' && actionBean.permohonan.kodUrusan.kod eq 'PMMK1') || (actionBean.kodNegeri eq '04' && actionBean.permohonan.kodUrusan.kod eq 'PMMK2')}">
                    <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L'
                                  || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T'
                                  || actionBean.pihak.jenisPengenalan.kod eq 'I' || actionBean.pihak.jenisPengenalan.kod eq 'K'}">
                          <p>
                              <label><font color="red">*</font>Jantina :</label>
                                      <s:select name="pihak.kodJantina" style="width:200px">
                                          <s:option value="">Sila Pilih</s:option>
                                  <s:options-collection collection="${list.senaraiKodJantina}" label="nama" value="kod"/>
                              </s:select>
                          </p>
                          <p>
                              <%--<label><font color="red">*</font>Tempoh Mastautin (Tahun) :</label>--%>
                              <label>Tempoh Mastautin (Tahun) :</label>
                              <s:text name="pemohon.tempohMastautin" size="10" maxlength="3" id="mastautin" onkeyup="validateNumber(this,this.value);"/>
                          </p>
                    </c:if>

                    <c:if test="${actionBean.pihak.jenisPengenalan.kod ne 'B' && actionBean.pihak.jenisPengenalan.kod ne 'L'
                                  && actionBean.pihak.jenisPengenalan.kod ne 'P' && actionBean.pihak.jenisPengenalan.kod ne 'T'
                                  && actionBean.pihak.jenisPengenalan.kod ne 'I' && actionBean.pihak.jenisPengenalan.kod ne 'K'}">
                          <p>
                              <label><font color="red">*</font>Tarikh Ditubuhkan :</label>
                              <s:text name="tarikhLahir" size="40" id="datepicker" class="datepicker" />&nbsp;<em style="font-style:normal">(cth : hh / bb / tttt)</em>
                          </p>
                    </c:if>

                </c:if>
                <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L'
                              || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T'
                              || actionBean.pihak.jenisPengenalan.kod eq 'I' || actionBean.pihak.jenisPengenalan.kod eq 'K'}">
                      <p>
                          <label>Tarikh Lahir :</label>
                          <s:text name="tarikhLahir" size="40" id="datepicker" class="datepicker" onchange="calAgeByDOB(this.value);"/>&nbsp;<em style="font-style:normal">(cth : hh / bb / tttt)</em>
                      </p>
                      <p>
                          <label>Umur :</label>
                          <s:text name="pemohon.umur" size="10" maxlength="3" id="umur" onkeyup="validateNumber(this,this.value);"/>
                      </p>
                      <%--   <p>
                             <label>Tempat Lahir :</label>
                             <s:select name="pihak.tempatLahir" id="tempatLahir" onchange="javaScript:changeTempatLahir(this.options[selectedIndex].text);">>
                                 <s:option value="">Sila Pilih</s:option>
                                 <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="nama"/>
                                 <s:option value="LAIN-LAIN">Lain-lain</s:option>
                             </s:select>
                         </p>--%>
                      <p>
                          <c:choose>
                              <c:when test="${(actionBean.kodNegeri eq '04' && actionBean.permohonan.kodUrusan.kod eq 'PMMK1') || (actionBean.kodNegeri eq '04' && actionBean.permohonan.kodUrusan.kod eq 'PMMK2')}">
                                  <label><font color="red">*</font>Tempat Lahir :</label>
                                      </c:when>
                                      <c:otherwise>
                                  <label>Tempat Lahir :</label>
                              </c:otherwise>
                          </c:choose>
                          <s:select name="pihak.tempatLahir" id="tempatLahirPemohon" onchange="javaScript:changeTempatLahir(this.options[selectedIndex].text);">
                              <s:option value="">Sila Pilih</s:option>
                              <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="nama"/>
                              <s:option value="LAIN-LAIN">Lain-lain</s:option>
                          </s:select>
                      </p>
                      <p id="tempatLahirLain">
                          <c:choose>
                              <c:when test="${(actionBean.kodNegeri eq '04' && actionBean.permohonan.kodUrusan.kod eq 'PMMK1')|| (actionBean.kodNegeri eq '04' && actionBean.permohonan.kodUrusan.kod eq 'PMMK2')}">
                                  <label><font color="red">*</font>Lain-lain Tempat Lahir :</label>
                                      </c:when>
                                      <c:otherwise>
                                  <label>Lain-lain Tempat Lahir :</label>
                              </c:otherwise>
                          </c:choose>
                          <s:text name="tempatLahirLain" id="tempatLahirPemohonLain" size="40" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                      </p>

                      <p>
                          <label>Status Perkahwinan :</label>
                          <s:select name="pemohon.statusKahwin">
                              <s:option value="">Sila Pilih</s:option>
                              <s:option>Berkahwin</s:option>
                              <s:option>Bujang</s:option>
                              <s:option>Duda</s:option>
                              <s:option>Ibu Tunggal</s:option>
                          </s:select>
                      </p>
                      <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PMADT' && actionBean.permohonan.kodUrusan.kod ne 'CGADT' && actionBean.permohonan.kodUrusan.kod ne 'PAADT' && actionBean.permohonan.kodUrusan.kod ne 'TMADT' && actionBean.permohonan.kodUrusan.kod ne 'BTADT'}">
                          <p>
                              <label>Pekerjaan :</label>
                              <s:text name="pemohon.pekerjaan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                          </p>
                          <p>
                              <c:choose>
                                  <c:when test="${(actionBean.kodNegeri eq '04' && actionBean.permohonan.kodUrusan.kod eq 'PMMK1') || (actionBean.kodNegeri eq '04' && actionBean.permohonan.kodUrusan.kod eq 'PMMK2')}">
                                      <label><font color="red">*</font>Pendapatan Bulanan :</label>
                                          </c:when>
                                          <c:otherwise>
                                      <label>Pendapatan Bulanan :</label>
                                  </c:otherwise>
                              </c:choose>
                              <s:select name="pemohon.matawang.kod" id="kodMatawang">
                                  <s:option value="">Sila Pilih</s:option>
                                  <s:options-collection collection="${list.senaraiKodMatawang}" label="nama" value="kod"/>
                              </s:select>
                              <s:text name="pemohon.pendapatan" id="pendapatanPemohon" size="14" maxlength="12" formatPattern="####.00"  onkeyup="validateNumber(this,this.value);"/>
                          </p>
                          <p>
                              <label>Tanggungan :</label>
                              <s:text name="pemohon.tanggungan" size="40" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                          </p>
                      </c:if>
                </c:if>
                <p>
                    <label>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PMADT' && actionBean.permohonan.kodUrusan.kod ne 'CGADT' && actionBean.permohonan.kodUrusan.kod ne 'PAADT' && actionBean.permohonan.kodUrusan.kod ne 'TMADT' && actionBean.permohonan.kodUrusan.kod ne 'BTADT' && actionBean.permohonan.kodUrusan.kod ne 'TMWNA'}">
                            Hubungan Dengan Penerima :
                        </c:if>

                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMADT' || actionBean.permohonan.kodUrusan.kod eq 'CGADT' || actionBean.permohonan.kodUrusan.kod eq 'PAADT' || actionBean.permohonan.kodUrusan.kod eq 'TMADT' || actionBean.permohonan.kodUrusan.kod eq 'BTADT' || actionBean.permohonan.kodUrusan.kod eq 'TMWNA'}">

                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TMADT' || actionBean.permohonan.kodUrusan.kod eq 'TMWNA'}">
                                <font color="red">*</font>Hubungan Dengan Simati :
                                </c:if>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'TMADT' && actionBean.permohonan.kodUrusan.kod ne 'TMWNA'}">
                                Hubungan Dengan Tuan Tanah :
                            </c:if>
                        </c:if>

                    </label>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PMADT' && actionBean.permohonan.kodUrusan.kod ne 'CGADT' && actionBean.permohonan.kodUrusan.kod ne 'PAADT' && actionBean.permohonan.kodUrusan.kod ne 'TMADT' && actionBean.permohonan.kodUrusan.kod ne 'BTADT' && actionBean.permohonan.kodUrusan.kod ne 'TMWNA'}">
                        <s:select name="pemohon.kaitan" id="hubungan" onchange="javaScript:changeHubungan(this.options[selectedIndex].text);">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option>Ibu Bapa Kepada Anak</s:option>
                            <s:option>Anak Kepada Ibu Bapa</s:option>
                            <s:option>Diri Sendiri</s:option>
                            <s:option>Suami Kepada Isteri</s:option>
                            <s:option>Isteri Kepada Suami</s:option>
                            <s:option>Saudara-mara</s:option>
                            <s:option>Adik-beradik</s:option>
                            <s:option>Penjual / Pembeli</s:option>
                            <s:option>Lain-lain</s:option>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMADT' || actionBean.permohonan.kodUrusan.kod eq 'CGADT' || actionBean.permohonan.kodUrusan.kod eq 'PAADT' || actionBean.permohonan.kodUrusan.kod eq 'TMADT' || actionBean.permohonan.kodUrusan.kod eq 'BTADT' || actionBean.permohonan.kodUrusan.kod eq 'TMWNA'}">
                        <s:select name="pemohon.kaitan" id="hubungan" onchange="javaScript:changeHubungan(this.options[selectedIndex].text);">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option>Anak</s:option>
                            <s:option>Adik Beradik Sesuku</s:option>
                            <s:option>Cucu</s:option>
                            <s:option>Ibu Kandung</s:option>
                            <s:option>Ibu Saudara</s:option>
                            <s:option>Moyang</s:option>
                            <s:option>Nenek</s:option>
                            <s:option>Sepupu</s:option>
                            <s:option>Lain-lain</s:option>
                        </s:select>
                    </c:if>
                </p>
                <p id="hubunganLain">
                    <label>Lain-lain Hubungan :</label>
                    <s:text name="hubunganLain" size="40" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>

                <p>
                    <label>&nbsp;</label>
                    <input type="checkbox" id="add1" name="add1" value="" onclick="copyAdd();"/>
                    <font color="red">Alamat surat sama dengan alamat berdaftar.</font>
                </p>

                <p>
                    <label for="alamat"><font color="red">*</font>Alamat Surat-Menyurat :</label>
                            <s:text name="pihakAlamatTamb.alamatKetiga1" id="suratAlamat1" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakAlamatTamb.alamatKetiga2" id="suratAlamat2" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakAlamatTamb.alamatKetiga3" id="suratAlamat3" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> Bandar :</label>
                    <s:text name="pihakAlamatTamb.alamatKetiga4" id="suratAlamat4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>Poskod :</label>
                    <s:text name="pihakAlamatTamb.alamatKetigaPoskod" id="suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                    <label for="Negeri">Negeri :</label>
                    <s:select name="pihakAlamatTamb.alamatKetigaNegeri.kod" id="suratNegeri">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <br/>

                <c:if test="${actionBean.pihak.jenisPengenalan.kod ne 'B' && actionBean.pihak.jenisPengenalan.kod ne 'L'
                              && actionBean.pihak.jenisPengenalan.kod ne 'P' && actionBean.pihak.jenisPengenalan.kod ne 'T'
                              && actionBean.pihak.jenisPengenalan.kod ne 'I' && actionBean.pihak.jenisPengenalan.kod ne 'K'}">
                    <%--=========================CAWANGAN PEMOHON=========================--%>
                    <div class="content" align="center" id="cawangan">
                        <span class=instr><em>Sila klik pada imej <img alt="" src='${pageContext.request.contextPath}/images/tambah.png'/> untuk menambah cawangan baru.</em></span><br/>
                        <br>
                        Maklumat Cawangan
                        <display:table class="tablecloth" name="${actionBean.cawanganList}" cellpadding="0" cellspacing="0"
                                       id="cawTable" pagesize="10"
                                       requestURI="${pageContext.request.contextPath}/consent/pihak_kepentingan" style="width:90%">
                            <display:column title="Pilih"><s:radio name="pilih" value="${cawTable.idPihakCawangan}"/></display:column>
                            <display:column property="namaCawangan" title="Nama"/>
                            <display:column property="suratAlamat1" title="Alamat1"/>
                            <display:column property="suratAlamat2" title="Alamat2"/>
                            <display:column property="suratAlamat3" title="Alamat3"/>
                            <display:column property="suratAlamat4" title="Bandar"/>
                            <display:column property="suratPoskod" title="Poskod"/>
                            <display:column property="suratNegeri.nama" title="Negeri"/>
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_caw_${line_rowNum}' onclick="removeCawanganPemohon('${cawTable.idPihakCawangan}', this.id)" onmouseover="this.style.cursor = 'pointer';">
                                </div>
                            </display:column>
                            <display:column title="<img alt='Tambah Cawangan' id='addCaw'
                                            src='${pageContext.request.contextPath}/images/tambah.png' onmouseover=\"this.style.cursor='pointer';\"/>"/>
                        </display:table>
                        <br>
                    </div>
                    <%--=========================TABLE AHLI LEMBAGA PENGARAH=========================--%>
                    <div class="content" align="center" id="pengarah">
                        <span class=instr><em>Sila klik pada imej <img alt="" src='${pageContext.request.contextPath}/images/tambah.png'/> untuk menambah ahli lembaga pengarah baru.</em></span><br/>
                        <br>
                        Maklumat Ahli Lembaga Pengarah
                        <display:table class="tablecloth" name="${actionBean.pengarahList}" cellpadding="0" cellspacing="0"
                                       id="pengarahTable" pagesize="10" requestURI="${pageContext.request.contextPath}/consent/pihak_kepentingan">
                            <%--  <display:column title="Bil" sortable="true">${pengarahTable_rowNum}</display:column>--%>

                            <display:column title="Nama" class="nama" property="nama"></display:column>
                            <display:column title="Jenis Pengenalan" property="jenisPengenalan.nama"></display:column>
                            <display:column title="Nombor Pengenalan" property="noPengenalan"></display:column>
                            <display:column title="Warganegara" property="warganegara.nama"></display:column>
                            <display:column title="Kemaskini">
                                <div align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                         onmouseover="this.style.cursor = 'pointer';" onclick="editPengarah(${pengarahTable_rowNum}, ${pengarahTable.idPengarah})">
                                </div>
                            </display:column>
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_caw_${line_rowNum}' onclick="removePengarah('${pengarahTable.idPengarah}', this.id)" onmouseover="this.style.cursor = 'pointer';">
                                </div>
                            </display:column>
                            <display:column title="<img alt='Tambah Ahli Lembaga Pengarah' id='addPengarah'
                                            src='${pageContext.request.contextPath}/images/tambah.png' onmouseover=\"this.style.cursor='pointer';\"/>"/>
                        </display:table>
                        <br/>
                    </div>
                </c:if>
                <%--=========================CAW DIALOG EDIT PEMOHON=========================--%>
                <div id="cawDialog" style="display: none" align="left" title="Tambah Cawangan">
                    <p>
                        <label for="nama"><font color="red">*</font>Nama :</label>
                                <s:text name="pihakCawangan.namaCawangan" id="namaCaw" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label for="alamat"><font color="red">*</font>Alamat Berdaftar :</label>
                                <s:text name="pihakCawangan.alamat1" id="alamatCaw1" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>

                    <p>
                        <label> &nbsp;</label>
                        <s:text name="pihakCawangan.alamat2" id="alamatCaw2" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>

                    <p>
                        <label> &nbsp;</label>
                        <s:text name="pihakCawangan.alamat3" id="alamatCaw3" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Bandar :</label>
                        <s:text name="pihakCawangan.alamat4" id="alamatCaw4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Poskod :</label>
                        <s:text name="pihakCawangan.poskod" size="40" id="poskodCaw" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                    <p>
                        <label for="Negeri"><font color="red">*</font>Negeri :</label>
                                <s:select name="pihakCawangan.negeri.kod" id="negeriCaw">
                                    <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                        </s:select>

                    </p>
                    <%--  <p>
                          <label for="alamat">Nombor Telefon :</label>
                          <s:text name="pihakCawangan.noTelefon1" id="telCaw" size="40" maxlength="12" onkeyup="validateNumber(this,this.value);"/>
                      </p>--%>
                </div>
                <%--=========================AHLI LEMBAGA PENGARAH DIALOG PEMOHON=========================--%>
                <div id="pengarahDialog" style="display: none" align="left" title="Tambah Ahli Lembaga Pengarah">
                    <p align="left">
                        <label for="nama"><font color="red">*</font>Nama :</label>
                                <s:text name="pihakPengarah.nama" id="namaPengarah" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                <s:select name="pihakPengarah.jenisPengenalan.kod" id="kpPengarah">
                                    <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <p>
                        <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                                <s:text name="pihakPengarah.noPengenalan" id="noKpPengarah" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label><font color="red">*</font>Warganegara :</label>
                                <s:select name="pihakPengarah.warganegara.kod" style="width:200px" id="wargaPengarah">
                                    <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                </div>
                <%--=========================AHLI LEMBAGA PENGARAH DIALOG EDIT PEMOHON=========================--%>
                <div id="pengarahDialogEdit" style="display: none" align="left" title="Kemaskini Ahli Lembaga Pengarah">
                    <s:hidden name="pihakPengarah.idPengarah" id="idPengarahEdit"/>
                    <p align="left">
                        <label for="nama"><font color="red">*</font>Nama :</label>
                                <s:text name="pihakPengarah.nama" id="namaPengarahEdit" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                <s:select name="pihakPengarah.jenisPengenalan.kod" id="kpPengarahEdit">
                                    <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <p>
                        <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                                <s:text name="pihakPengarah.noPengenalan" id="noKpPengarahEdit" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label><font color="red">*</font>Warganegara :</label>
                                <s:select name="pihakPengarah.warganegara.kod" style="width:200px" id="wargaPengarahEdit">
                                    <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                </div>
                <p align="center">
                    <s:button name="simpanKemaskiniPemohon" class="btn" value="Simpan" onclick="save(this.name, this.form);"/>
                    <s:button name="close" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
                <br/>
            </fieldset>
        </c:if>
    </s:form>
</div>
