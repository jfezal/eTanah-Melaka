<%--
    Document   : kemasukan_pihak_berkepentingan
    Created on : 15-Oct-2009, 03:49:43
    Author     : md.nurfikri
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ageCalculator.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('.empty').remove();
        $('.alphanumeric').alphanumeric();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $('#jenisPengenalan').change(function() {
            dodacheck($("#kp").val());
        });

        var v = $('#multiple').val();
        if (v == 'true') {
            $('#multiple_check').show();
        } else {
            $('#multiple_check').hide();
        }


        $("#pgAmanahDialog").dialog({
            autoOpen: false,
            height: 300,
            width: 900,
            modal: true,
            buttons: {
                'Simpan': function() {
                    var bValid = true;
                    bValid = bValid && ($('#namaAmanah').val() != '');
                    bValid = bValid && ($('#kpAmanah').val() != '');
                    bValid = bValid && ($('#nokpAmanah').val() != '');
                    bValid = bValid && ($('#alamatAmanah1').val() != '');
                    bValid = bValid && ($('#poskodAmanah').val() != '');
                    bValid = bValid && ($('#negeriAmanah').val() != '');

                    if (bValid) {
                        var nama = $('#namaAmanah').val();
                        var kp = $('#kpAmanah').val();
                        var noKp = $('#nokpAmanah').val();
                        var add1 = $('#alamatAmanah1').val();
                        var add2 = $('#alamatAmanah2').val();
                        var add3 = $('#alamatAmanah3').val();
                        var add4 = $('#alamatAmanah4').val();
                        var poskod = $('#poskodAmanah').val();
                        var negeri = $('#negeriAmanah').val();

                        var rowNo = $('table#pgAmanahTable tr').length;

                        $('table#pgAmanahTable tbody').append("<tr id='x" + rowNo + "' class='x'>" +
                                '<td>' + rowNo + '</td>' +
                                "<td><input type=hidden name='namaPA' value='" + nama + "'/>" + nama + '</td>' +
                                "<td><input type=hidden name='kpPA' value='" + kp + "'/>" + $('#kpAmanah option:selected').text() + '</td>' +
                                "<td><input type=hidden name='noKpPA' value='" + noKp + "'/>" + noKp + '</td>' +
                                "<td><input type=hidden name='add1PA' value='" + add1 + "'/>" + add1 + '</td>' +
                                "<td><input type=hidden name='add2PA' value='" + add2 + "'/>" + add2 + '</td>' +
                                "<td><input type=hidden name='add3PA' value='" + add3 + "'/>" + add3 + '</td>' +
                                "<td><input type=hidden name='add4PA' value='" + add4 + "'/>" + add4 + '</td>' +
                                "<td><input type=hidden name='poskodPA' value='" + poskod + "'/>" + poskod + '</td>' +
                                "<td><input type=hidden name='negeriPA' value='" + negeri + "'/>" + $('#negeriAmanah option:selected').text() + '</td>' +
                                '</tr>');
                        $(this).dialog('close');
                    } else {
                        alert('Sila masukan maklumat pihak dipegang amanah dengan lengkap. ');
                    }
                },
                'Tutup': function() {
                    $(this).dialog('close');
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


        $('#addPA').click(function() {
            $('#pgAmanahDialog').dialog('open');
        });

        var v = '${actionBean.pihak.jenisPengenalan.kod}';

        if (v == "B" || v == "L" || v == "P" || v == "T" || v == "I" || v == "F") {
            $('#kodBangsa').hide();
            $('#kodBangsaOrang').show();
            $('#kodBangsaSyarikat').hide();
            $('#tableCaw').hide();
            $('#btnCaw').hide();
            $('#btnAhli').hide();
        }

        else {
            $('#kodBangsa').hide();
            $('#kodBangsaOrang').hide();
            $('#kodBangsaSyarikat').show();
            $('#tableCaw').show();
            $('#btnCaw').show();
            $('#btnAhli').show();
        }

        if (v == '') {
            $('#kodBangsa').show();
            $('#kodBangsaOrang').hide();
            $('#kodBangsaSyarikat').hide();
            $('#tableCaw').hide();
            $('#btnCaw').hide();
            $('#btnAhli').hide();
        }

        if (v == "S") {
            $('#suku').hide();
        }

        else {
            $('#suku').show();
        }

        $('form').submit(function() {
            var val = $('#kp').val();
            var jenis = '${jenis}';
            return doCheckUmur(val, 'kp', jenis);
        });

        if (v == "B") {
            var icNo = '${actionBean.pihak.noPengenalan}';

            var year = icNo.substring(0, 2);

            if (year > 25 && year < 99) {//fixme :
                year = "19" + year;
            } else {
                year = "20" + year;
            }

            var age = calculateAge(icNo.substring(4, 6), icNo.substring(2, 4), year);
            $('#umur').val(age);
            $('#datepicker').val(icNo.substring(4, 6) + "/" + icNo.substring(2, 4) + "/" + year);
        }

        $('#kod_warganegara').val('MAL');
        $('#kod_warga_pengarah').val('MAL');

        var jenisPihak = $('#jenis_pihak').val();

        if (jenisPihak == "Pemegang Amanah" || jenisPihak == "PA") {
            $('#pgAmanah').show();
        } else {
            $('#pgAmanah').hide();
        }
    });

    function calAgeByDOB(value) {

        var year = value.substring(8, 10);

        if (year > 25 && year < 99) {//fixme :
            year = "19" + year;
        } else {
            year = "20" + year;
        }

        var age = calculateAge(value.substring(0, 2), value.substring(3, 5), year);
        $('#umur').val(age);
    }


    function changeHideSuku(value) {
        if (value == "S") {
            $('#suku').hide();
        } else {
            $('#suku').show();
        }
    }

    function changePgAmanah(value) {
        if (value == "Pemegang Amanah") {
            $('#pgAmanah').show();
        } else {
            $('#pgAmanah').hide();
        }
    }

    function doValidation() {
        var val = $('#kod_warganegara').val();
        var val2 = $('#alamat1').val();
        var val3 = $('#nama_pemohon').val();
        var val4 = $('#jenis_pihak').val();
        if (val3 == '') {
            alert('Sila masukan nama pihak.');
            return false;
        } else if (val == '') {
            alert('Sila pilih warganegara.');
            return false;
        } else if (val2 == '') {
            alert('Sila masukan alamat berdaftar.');
            return false;
        } else if (val4 == '') {
            alert('Sila Masukan Jenis Pihak Berkepentingan.');
            return false;
        }

    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PNPA' && actionBean.permohonan.kodUrusan.kod ne 'TRPA' }">
        if (val4 == "PA") {
            var val5 = $('#namaAmanah').val();
            var val6 = $('#kpAmanah').val();
            var val7 = $('#nokpAmanah').val();
            var val8 = $('#alamatAmanah1').val();

            //if(val5 == '' || val6 == '' || val7 == '' || val8 == ''){
            //    alert('Sila masukan maklumat pihak dipegang amanah dengan lengkap.');
            //    return false;
            //}
        }
    </c:if>
        return true;
    }

    function validationPB() {
        if ($('#jenis_pihak').val() == '') {
            alert('Sila Masukan Jenis Pihak Berkepentingan.');
            return false;
        }

        else if ($('#alamat1').val() == '') {
            alert('Sila Masukan Alamat Berdaftar.');
            return false;
        }
        return true;
    }

    function save(event, f, idPembetulan) {
        var jenisPB = $('#jenis_pihak').val();
        var v = $('#multiple').val();
        if (doValidation()) {

            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });

            var q = $(f).formSerialize();
            var url = f.action + '?' + event + '&idPembetulan=' + idPembetulan + '&jenisPB=' + jenisPB;
            $.post(url, q,
                    function(data) {
                        if (v == 'true') {
                            $('#multiple_check').show();
                            if (data == '1') {
                                $('#page_div', opener.document).html('Terdapat masalah berlaku.');
                                self.close();
                            } else if (data == '2') {
                                //alert('Pihak dengan jenis pihak yang sama telah dipilih. Sila Pilih Jenis Pihak yang berlainan.');
                            } else {
                                $('#div_content', opener.document).html(data);
                                self.close();
                            }
                            $.unblockUI();
                            self.close();
                        } else {
                            $('#multiple_check').hide();
                            if (data == '1') {
                                $('#page_div', opener.document).html('Terdapat masalah berlaku.');
                                $.unblockUI();
                                self.close();
                            } else if (data == '2') {
                                alert('Pihak dengan jenis pihak yang sama telah dipilih. Sila Pilih Jenis Pihak yang berlainan.');
                            } else {
                                $('#page_div', opener.document).html(data);
                                $.unblockUI();
                                self.close();
                            }

                        }
                    }, 'html');
        }
    }

    function savePemohon(event, f, idPembetulan) {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });
        var q = $(f).formSerialize();
        var url = f.action + '?' + event + '&idPembetulan=' + idPembetulan;
        $.post(url, q,
                function(data) {
    <c:choose>
        <c:when test="${multiple}">
                    $('#div_content', opener.document).html(data);
        </c:when>
        <c:otherwise>
                    if (data == '1') {
                        $('#page_div', opener.document).html('Terdapat masalah berlaku.');
                    } else {
                        $('#page_div', opener.document).html(data);
                    }
        </c:otherwise>
    </c:choose>
                    $.unblockUI();
                    self.close();
                }, 'html');
    }

    function changeHideSuku(value) {

        if (value == "S") {
            $('#suku').hide();
        }

        else {
            $('#suku').show();
        }
    }

    function changeJenisKP(value) {
        if (value == "B" || value == "L" || value == "P" || value == "T" || value == "I" || value == "F") {
            $('#kodBangsa').hide();
            $('#kodBangsaOrang').show();
            $('#kodBangsaSyarikat').hide();
            $('#tableCaw').hide();
            $('#btnCaw').hide();
            $('#btnAhli').hide();
        }

        else {
            $('#kodBangsa').hide();
            $('#kodBangsaOrang').hide();
            $('#kodBangsaSyarikat').show();
            $('#tableCaw').show();
            $('#btnCaw').show();
            $('#btnAhli').show();
        }
    }

    function copyAddCaw() {
        if ($('input[name=addCaw]').is(':checked')) {
            $('#suratAlamatCaw1').val($('#alamatCaw1').val());
            $('#suratAlamatCaw2').val($('#alamatCaw2').val());
            $('#suratAlamatCaw3').val($('#alamatCaw3').val());
            $('#suratAlamatCaw4').val($('#alamatCaw4').val());
            $('#suratPoskodCaw').val($('#poskodCaw').val());
            $('#suratNegeriCaw').val($('#negeriCaw').val());
        } else {
            $('#suratAlamatCaw1').val('');
            $('#suratAlamatCaw2').val('');
            $('#suratAlamatCaw3').val('');
            $('#suratAlamatCaw4').val('');
            $('#suratPoskodCaw').val('');
            $('#suratNegeriCaw').val('');

        }
    }

    function copyAdd() {
        if ($('input[name=add1]').is(':checked')) {
            $('#suratAlamat1').val($('#alamat1').val());
            $('#suratAlamat2').val($('#alamat2').val());
            $('#suratAlamat3').val($('#alamat3').val());
            $('#suratAlamat4').val($('#alamat4').val());
            $('#suratPoskod').val($('#poskod').val());
            $('#suratNegeri').val($('#negeri').val());
        } else {
            $('#suratAlamat1').val('');
            $('#suratAlamat2').val('');
            $('#suratAlamat3').val('');
            $('#suratAlamat4').val('');
            $('#suratPoskod').val('');
            $('#suratNegeri').val('');

        }
    }

    function removeCawangan(val) {
        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pihak_berkepentingan?deleteCawangan&idCawangan=' + val;
            $.get(url,
                    function(data) {
                        $('#caw').html(data);
                    }, 'html');
            location.reload(true);
        }
    }

    function removeCawanganPemohon(val) {
        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pihak_berkepentingan?deleteCawanganPemohon&idCawangan=' + val;
            $.get(url,
                    function(data) {
                        $('#caw').html(data);
                    }, 'html');
            location.reload(true);
        }
    }


    function selectName(val, val2) {
        var url = '${pageContext.request.contextPath}/daftar/pembetulan/betul?selectName&idPihak=' + val + '&idPembetulan=' + val2;
        $.get(url,
                function(data) {
                    $('#caw').html(data);
                }, 'html');
    }

    function selectNamePemohon(val, val2) {
        var url = '${pageContext.request.contextPath}/daftar/pembetulan/betul?selectNamePemohon&idPihak=' + val + '&idPembetulan=' + val2;
        $.get(url,
                function(data) {
                    $('#caw').html(data);
                }, 'html');
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

    function dodacheck(val) {
        //var mikExp = /[1\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\1\|]/;
        var v = $('#jenisPengenalan').val();

        if (v == 'B') {
            var strPass = val;
            var strLength = strPass.length;
            //$('#kp').attr("maxlength","12");
            if (strLength > 12) {
                var tst = val.substring(0, (strLength) - 1);
                $('#kp').val(tst);
            }
            var lchar = val.charAt((strLength) - 1);
            if (isNaN(lchar)) {
                //return false;
                var tst = val.substring(0, (strLength) - 1);
                $('#kp').val(tst);
            }
        }//else{
        // $('#kp').attr("maxlength","30");
        // }
    }

    function doCheckUmur(v, id, type) {
        var va = $('#jenisPengenalan').val();
        if (va == 'B') {
            return doValidateAge(v, id, 'jenisPengenalan', type);
        } else {
            return true;
        }
    }

</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<div class="subtitle" id="caw">
    <s:form name="form1" beanclass="etanah.view.stripes.nota.pembetulanActionBean">
        <s:hidden name="jenisPihak"/>
        <s:hidden name="pihak.nama"/>
        <s:hidden name="pihak.idPihak"/>
        <s:hidden name="pihak.jenisPengenalan.kod"/>
        <s:hidden name="pihak.jenisPengenalan.nama"/>
        <s:hidden name="pihak.noPengenalan"/>
        <s:hidden name="pihak.bangsa.kod"/>
        <s:hidden name="pihak.suku.kod"/>
        <s:hidden name="permohonanPihak.jenis.kod"/>
        <s:hidden name="pihakCawangan.idPihakCawangan"/>
        <s:hidden name="idPermohonanPihak" value="${actionBean.idPermohonanPihak}"/>
        <s:hidden name="idPembetulan" value="${actionBean.idPembetulan}"/>
        <s:hidden name="tambahWaris" value="${actionBean.tambahWaris}"/>

        <c:if test="${tuanTanah}">
            <s:text name="tuanTanah" value="${tuanTanah}"/>
        </c:if>
        <s:errors/>
        <s:messages/>


        <fieldset class="aras1">
            <legend>
                <c:if test="${pemohon}">
                    Kemasukan Maklumat Pemohon
                </c:if>

                <c:if test="${!pemohon}">
                    <c:choose>
                        <c:when test="${jenis eq 'PA'}">
                            Kemasukan Maklumat Pemegang Amanah
                        </c:when>
                        <c:when test="${jenis eq 'WAR'}">
                            Kemasukan Maklumat Penerima Amanah
                        </c:when>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'KVPT' || actionBean.permohonan.kodUrusan.kod eq 'KVPP'}">
                            Kemasukan Maklumat Pengkaveat
                        </c:when>
                        <c:otherwise>
                            Kemasukan Maklumat Penerima
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </legend>
            <br/>



            <c:if test="${actionBean.cariFlag}">

                <c:if test="${!actionBean.tiadaDataFlag}">
                    <p>
                        <label for="nama"><font color="red">*</font>Nama:</label>
                                <s:text name="pihak.nama" id="nama_pemohon" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>

                    <p>
                        <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                <s:select name="pihak.jenisPengenalan.kod" onchange="javaScript:changeJenisKP(this.options[selectedIndex].value)">
                                    <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <p>
                        <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                                <s:text name="pihak.noPengenalan" id="kp" size="40" onkeyup="dodacheck(this.value);this.value=this.value.toUpperCase();"/>
                    </p>


                    <c:if test="${actionBean.pihak.bangsa.kod ne null}">
                        <p>
                            <label>Bangsa / Jenis Syarikat :</label>
                            ${actionBean.pihak.bangsa.nama}&nbsp;
                        </p>
                    </c:if>



                    <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                        <p>
                            <label>Tarikh Lahir :</label>
                            <s:text name="tarikhLahir" size="40" id="datepicker" class="datepicker" onchange="calAgeByDOB(this.value);"/>
                        </p>
                        <p>
                            <label>Umur :</label>
                            <s:text name="permohonanPihak.umur" size="10" maxlength="3" id="umur" onkeyup="validateNumber(this,this.value);"/>
                        </p>
                        <p>
                            <label>Tempat Lahir :</label>
                            <s:select name="pihak.tempatLahir">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="nama"/>
                            </s:select>
                        </p>
                        <p>
                            <label>Kewarganegaraan :</label>
                            <s:hidden name="pihak.wargaNegara.kod" id=""/>
                            <s:select name="pihak.wargaNegara.kod" style="width:200px" id="kod_warganegara">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                            </s:select>
                        </p>
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
                        <p>
                            <label>Pekerjaan :</label>
                            <s:text name="permohonanPihak.pekerjaan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Pendapatan Bulanan (RM) :</label>
                            <s:text name="permohonanPihak.pendapatan" size="40" maxlength="12" onkeyup="validateNumber(this,this.value);"/>
                        </p>
                        <p>
                            <label>Tanggungan :</label>
                            <s:text name="permohonanPihak.tangungan" size="40" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                        </p>
                        <p>
                            <label>Hubungan :</label>
                            <s:select name="permohonanPihak.kaitan">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option>IBU BAPA KEPADA ANAK</s:option>
                                <s:option>ANAK KEPADA IBU BAPA</s:option>
                                <s:option>SUAMI KEPADA ISTERI</s:option>
                                <s:option>ISTERI KEPADA SUAMI</s:option>
                                <s:option>SAUDARA-MARA</s:option>
                                <s:option>PENJUAL / PEMBELI</s:option>
                                <s:option>LAIN-LAIN</s:option>
                            </s:select>
                        </p>
                    </c:if>

                    <c:if test="${!pemohon}">
                        <p>
                            <label for="nama"><font color="red">*</font>Jenis Pihak Berkepentingan :</label>
                                    <s:select name="jenisPihak" style="width:400px" id="jenis_pihak">

                                <s:option value="" >Sila Pilih</s:option>

                                <s:options-collection collection="${actionBean.senaraiKodPenerima}" label="nama" value="kod" />
                            </s:select>
                        </p>
                    </c:if>



                    <p>
                        <label for="alamat"><font color="red">*</font>Alamat Berdaftar :</label>
                                <s:text name="pihak.alamat1" id="alamat1" size="100" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="pihak.alamat2" id="alamat2" size="100" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="pihak.alamat3" id="alamat3" size="100" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Bandar :</label>
                        <s:text name="pihak.alamat4" id="alamat4" size="100" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Poskod :</label>
                        <s:text name="pihak.poskod" id="poskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                    </p>

                    <p>
                        <label for="Negeri">Negeri :</label>
                        <s:select name="pihak.negeri.kod" id="negeri">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <input type="checkbox" id="add1" name="add1" value="" onclick="copyAdd();"/>
                        <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                    </p>
                    <p>
                        <label for="alamat">Alamat Surat-Menyurat :</label>
                        <s:text name="pihak.suratAlamat1" id="suratAlamat1" size="100" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="pihak.suratAlamat2" id="suratAlamat2" size="100" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="pihak.suratAlamat3" id="suratAlamat3" size="100" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Bandar :</label>
                        <s:text name="pihak.suratAlamat4" id="suratAlamat4" size="100" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Poskod :</label>
                        <s:text name="pihak.suratPoskod" id="suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                    </p>

                    <p>
                        <label for="Negeri">Negeri :</label>
                        <s:select name="pihak.suratNegeri.kod" id="suratNegeri">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                </c:if>
                <%--tiada data--%>
                <c:if test="${actionBean.tiadaDataFlag}">
                    <c:if test="${actionBean.tambahWaris ne 'true'}">
                        <p>
                            <label for="nama"><font color="red">*</font>Nama :</label>
                                    <s:text name="pihak.nama" id="nama_pemohon" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p> 
                    </c:if>
                    <c:if test="${actionBean.tambahWaris eq 'true'}">
                        <p>
                            <label for="nama"><font color="red">*</font>Nama :</label>
                                    <s:text name="nama" id="nama_pemohon" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p> 
                    </c:if>




                    <c:if test="${actionBean.kodNegeri eq '04'}">
                        <c:if test="${actionBean.tambahWaris ne 'true'}">
                            <p>
                                <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                        <s:select name="pihak.jenisPengenalan.kod" onchange="javaScript:changeJenisKP(this.options[selectedIndex].value)">
                                            <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                        </c:if>
                        <c:if test="${actionBean.tambahWaris eq 'true'}">
                            <p>
                                <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan Waris:</label>
                                        <s:select name="jenisPengenalan" onchange="javaScript:changeJenisKP(this.options[selectedIndex].value)">
                                            <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                        </c:if>

                    </c:if>

                    <c:if test="${actionBean.tambahWaris ne 'true'}">
                        <p>
                            <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                                    <s:text name="pihak.noPengenalan" id="kp" size="40" onkeyup="dodacheck(this.value);this.value=this.value.toUpperCase();"/>
                        </p> 
                    </c:if>
                    <c:if test="${actionBean.tambahWaris eq 'true'}">
                        <p>
                            <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan Waris:</label>
                                    <s:text name="noPengenalan" id="kp" size="40" onkeyup="dodacheck(this.value);this.value=this.value.toUpperCase();"/>
                        </p> 
                    </c:if>

                    <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L'
                                  || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T'
                                  || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                          <p>
                              <label><font color="red">*</font>Kewarganegaraan :</label>
                                      <s:select name="pihak.wargaNegara.kod" style="width:200px" id="kod_warganegara">
                                          <s:option value="">Sila Pilih</s:option>
                                  <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                              </s:select>
                          </p>
                    </c:if>
                    <c:if test="${actionBean.tambahWaris eq 'true'}">
                        <p>
                            <label><font color="red">*</font>Kewarganegaraan :</label>
                                    <s:select name="wargaNegara" style="width:200px" id="kod_warganegara">
                                        <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                    </c:if>
                    <c:if test="${!pemohon}">
                        <c:if test="${actionBean.tambahWaris ne 'true'}">
                            <p>
                                <label for="nama"><font color="red">*</font>Jenis Pihak Berkepentingan :</label>
                                        <c:choose>
                                            <c:when test="${actionBean.permohonan.kodUrusan.kod ne 'PNPA' && actionBean.permohonan.kodUrusan.kod ne 'TRPA' }">
                                                <s:select name="jenisPihak" style="width:400px" id="jenis_pihak" onchange="javaScript:changePgAmanah(this.options[selectedIndex].text);">
                                                    <c:if test="${fn:length(actionBean.senaraiKodPenerima) > 1}">
                                                        <s:option value="" >Sila Pilih</s:option>
                                            </c:if>
                                            <s:options-collection collection="${actionBean.senaraiKodPenerima}" label="nama" value="kod" />
                                        </s:select>
                                    </c:when>
                                    <c:otherwise>
                                        <s:select name="jenisPihak" style="width:400px" id="jenis_pihak">
                                            <c:if test="${fn:length(actionBean.senaraiKodPenerima) > 1}">
                                                <s:option value="" >Sila Pilih</s:option>
                                            </c:if>
                                            <s:options-collection collection="${actionBean.senaraiKodPenerima}" label="nama" value="kod" />
                                        </s:select>
                                    </c:otherwise>
                                </c:choose>
                            </p>
                        </c:if>
                        <c:if test="${actionBean.tambahWaris eq 'true'}">
                            <p>
                                <label for="nama"><font color="red">*</font>Jenis Pihak Berkepentingan 123:</label>
                                        <s:select name="jenisPihakWaris" style="width:200px" id="jenisPihak">
                                            <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodJenisPihakBerkepentingan}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                        </c:if>
                    </c:if>
                    <c:if test="${actionBean.tambahWaris ne 'true'}">
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
                            <label>Bandar :</label>
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
                    </c:if>
                    <c:if test="${actionBean.tambahWaris eq 'true'}">
                        <p>
                            <label for="alamat"><font color="red">*</font>Alamat Berdaftar :</label>
                                    <s:text name="alamat1" size="40" id="alamat1" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>

                        <p>
                            <label> &nbsp;</label>
                            <s:text name="alamat2" size="40" id="alamat2" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>

                        <p>
                            <label> &nbsp;</label>
                            <s:text name="alamat3" size="40" id="alamat3" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Bandar :</label>
                            <s:text name="alamat4" size="40" id="alamat4" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="poskod" size="40" maxlength="5" id="poskod" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select name="negeri" id="negeri">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                    </c:if>

                    <c:if test="${actionBean.tambahWaris ne 'true'}">
                        <p>
                            <label>Bangsa / Jenis Syarikat :</label>
                            <s:select name="pihak.bangsa.kod" style="width:400px" id="kodBangsa">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraiKodBangsa}" label="nama" value="kod"/>
                            </s:select>
                            <s:select name="pihak.bangsa.kod" style="width:400px" id="kodBangsaOrang">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraiKodBangsaOrang}" label="nama" value="kod"/>
                            </s:select>
                            <s:select name="pihak.bangsa.kod" style="width:400px" id="kodBangsaSyarikat">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraiKodBangsaSyarikat}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                    </c:if>


                    <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                        <p>
                            <label>Tarikh Lahir :</label>
                            <s:text name="tarikhLahir" size="40" id="datepicker" class="datepicker" onchange="calAgeByDOB(this.value);"/>
                        </p>
                        <p>
                            <label>Umur :</label>
                            <s:text name="permohonanPihak.umur" size="10" maxlength="3" id="umur" onkeyup="validateNumber(this,this.value);"/>
                        </p>
                        <p>
                            <label>Tempat Lahir :</label>
                            <s:select name="pihak.tempatLahir">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="nama"/>
                            </s:select>
                        </p>
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
                        <p>
                            <label>Pekerjaan :</label>
                            <s:text name="permohonanPihak.pekerjaan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Pendapatan Bulanan (RM) :</label>
                            <s:text name="permohonanPihak.pendapatan" size="40" maxlength="12" onkeyup="validateNumber(this,this.value);"/>
                        </p>
                        <p>
                            <label>Tanggungan :</label>
                            <s:text name="permohonanPihak.tangungan" size="40" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                        </p>
                        <p>
                            <label>Hubungan :</label>
                            <s:select name="permohonanPihak.kaitan">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option>IBU BAPA KEPADA ANAK</s:option>
                                <s:option>ANAK KEPADA IBU BAPA</s:option>
                                <s:option>SUAMI KEPADA ISTERI</s:option>
                                <s:option>ISTERI KEPADA SUAMI</s:option>
                                <s:option>SAUDARA-MARA</s:option>
                                <s:option>PENJUAL / PEMBELI</s:option>
                                <s:option>LAIN-LAIN</s:option>
                            </s:select>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.tambahWaris ne 'true'}">
                        <p>
                            <label>&nbsp;</label>
                            <input type="checkbox" id="add1" name="add1" value="" onclick="copyAdd();"/>
                            <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                        </p>

                        <p>
                            <label for="alamat">Alamat Surat-Menyurat :</label>
                            <s:text name="pihak.suratAlamat1" id="suratAlamat1" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat2" id="suratAlamat2" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat3" id="suratAlamat3" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Bandar :</label>
                            <s:text name="pihak.suratAlamat4" id="suratAlamat4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="pihak.suratPoskod" id="suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select name="pihak.suratNegeri.kod" id="suratNegeri">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p> 
                    </c:if>


                </c:if>
                <c:if test="${actionBean.pihak.jenisPengenalan.kod ne 'B' && actionBean.pihak.jenisPengenalan.kod ne 'L' && actionBean.pihak.jenisPengenalan.kod ne 'P' && actionBean.pihak.jenisPengenalan.kod ne 'T' && actionBean.pihak.jenisPengenalan.kod ne 'I'}">
                    <div class="content" align="center" id="tableCaw">
                        <br>
                        Maklumat Cawangan

                        <display:table name="${actionBean.cawanganList}" id="line" class="tablecloth" >

                            <display:column title="Pilih"><s:radio name="idCawangan" value="${line.idPihakCawangan}"/></display:column>
                            <display:column property="namaCawangan" title="Nama"/>
                            <display:column title="Alamat" >${line.suratAlamat1}
                                <c:if test="${line.suratAlamat2 ne null}"> , </c:if>
                                ${line.suratAlamat2}
                                <c:if test="${line.suratAlamat3 ne null}"> , </c:if>
                                ${line.suratAlamat3}
                                <c:if test="${line.suratAlamat4 ne null}"> , </c:if>
                                ${line.suratAlamat4}
                                <c:if test="${line.suratPoskod ne null}"> , </c:if>
                                ${line.suratPoskod}
                                <c:if test="${line.suratNegeri ne null}"> , </c:if>
                                ${line.suratNegeri.nama}
                            </display:column>
                            <c:if test="${pemohon}">
                                <display:column title="Kemaskini">
                                    <p align="center">
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="editCawanganPemohon('${line.idPihakCawangan}')" onmouseover="this.style.cursor = 'pointer';">
                                    </p>
                                </display:column>
                                <display:column title="Hapus">
                                    <div align="center">
                                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                             id='rem_${line_rowNum}' onclick="removeCawanganPemohon('${line.idPihakCawangan}')" onmouseover="this.style.cursor = 'pointer';">
                                    </div>
                                </display:column>
                            </c:if>

                            <c:if test="${!pemohon}">
                                <display:column title="Kemaskini">
                                    <p align="center">
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="editCawangan('${line.idPihakCawangan}')" onmouseover="this.style.cursor = 'pointer';">
                                    </p>
                                </display:column>
                                <display:column title="Hapus">
                                    <div align="center">
                                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                             id='rem_${line_rowNum}' onclick="removeCawangan('${line.idPihakCawangan}')" onmouseover="this.style.cursor = 'pointer';">
                                    </div>
                                </display:column>
                            </c:if>
                        </display:table>
                        <br>

                    </div>
                </c:if>
                <br/>

            </c:if>

            <c:if test="${!actionBean.cariFlag}">
                <p align="center">
                    <c:if test="${pemohon}">
                        <s:submit name="cariPihakPemohon" value="Cari" class="btn"/>
                    </c:if>
                    <c:if test="${!pemohon}">
                        <s:submit name="cariPihak" value="Cari2" class="btn"/>
                    </c:if>
                    <s:submit name="cariSemula" value="Isi Semula" class="btn"/>&nbsp;
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
            </c:if>

            <c:if test="${actionBean.cariFlag}">

                <p align="center">
                    <c:if test="${actionBean.tambahWaris ne 'true'}">
                        <s:button name="simpanPemohonPopup" id="simpan" value="Simpan Pemohon" class="btn" onclick="savePemohon(this.name, this.form,'${actionBean.idPembetulan}');"/>
                        <s:button name="simpanPihakPopup" id="simpan" value="Simpan Penerima" class="btn" onclick="save(this.name, this.form,'${actionBean.idPembetulan}');"/> 
                    </c:if>
                    <c:if test="${actionBean.tambahWaris eq 'true'}">
                        <s:button name="simpanPihakWaris" id="simpan" value="Simpan" class="btn" onclick="savePemohon(this.name, this.form,'${actionBean.idPembetulan}','${actionBean.idPermohonanPihak}');"/>
                    </c:if>

                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
            </c:if>
            <br>
        </fieldset>





    </s:form>
</div>