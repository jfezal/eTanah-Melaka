
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"    prefix="c" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    $(document).ready(function() {
    <c:forEach var="i" begin="1" end="${actionBean.bilHakmilik}" >
            $("#hakmilikStrata${i - 1}").blur(function() { // add by azri: 15/8/2013: new search function for hakmilik strata
                var hmStrata = $('#hakmilikStrata${i - 1}').val();
                var length = hmStrata.length;
                if (length === 28) {
                    var hmInduk = hmStrata.substring(0, 17);
                    var banggunan = hmStrata.substring(17, 20).replace(/^0+/, ''); // get substring building from id and ignore first zeros
                    var tinggat = hmStrata.substring(20, 23).replace(/^0+/, ''); // get substring floor from id and ignore first zeros
                    var petak = hmStrata.substring(23, 28).replace(/^0+/, ''); // get substring petak from id and ignore first zeros
                    $("#hakmilik${i - 1}").val(hmInduk);
                    $("#bangunan${i - 1}").val(banggunan);
                    $("#tingkat${i - 1}").val(tinggat);
                    $("#petak${i - 1}").val(petak);
                } else if (length === 27) {
                    var hmInduk = hmStrata.substring(0, 16);
                    var banggunan = hmStrata.substring(16, 19).replace(/^0+/, ''); // get substring building from id and ignore first zeros
                    var tinggat = hmStrata.substring(19, 22).replace(/^0+/, ''); // get substring floor from id and ignore first zeros
                    var petak = hmStrata.substring(22, 27).replace(/^0+/, ''); // get substring petak from id and ignore first zeros
                    $("#hakmilik${i - 1}").val(hmInduk);
                    $("#bangunan${i - 1}").val(banggunan);
                    $("#tingkat${i - 1}").val(tinggat);
                    $("#petak${i - 1}").val(petak);
                } else if (length === 23) { // landed
                    var hmInduk = hmStrata.substring(0, 17);
                    var banggunan = '';
                    var tinggat = ''; // not sure if tingkat is 0
                    var petak = hmStrata.substring(17, 21).replace(/^0+/, '') + hmStrata.substring(20, 23).replace(/^0+/, ''); // not sure if petak is 0
                    $("#hakmilik${i - 1}").val(hmInduk);
                    $("#bangunan${i - 1}").val(banggunan);
                    $("#tingkat${i - 1}").val(tinggat);
                    $("#petak${i - 1}").val(petak);
                } 
                    else if (length === 22) { // landed GRN GMM
                        var hmInduk = hmStrata.substring(0, 17);
                        var banggunan = '';
                        var tinggat = '';
                        //var petak = hmStrata.substring(17, 20).replace(/^0+/, '') + hmStrata.substring(20, 22).replace(/^0+/, ''); nad comment sebab dari L101 jadi L11
                        var petak = hmStrata.substring(17, 22).replace(/^0+/, ''); //add by nad 06/01/2020: utk baca petak L cth L101
                        $("#hakmilik${i - 1}").val(hmInduk);
                        $("#bangunan${i - 1}").val(banggunan);
                        $("#tingkat${i - 1}").val(tinggat);
                        $("#petak${i - 1}").val(petak);
                    }
                    else if (length === 21) { 
                        var jnshm = hmStrata.substring(6,9).replace(/0+$/, ''); // get substring building from id and ignore last zeros
                        //alert('Jenis Hakmilik: ' + jnshm);
                        //var hmInduk = hmStrata.substring(0, 16);
                        //var banggunan = '';
                        //var tinggat = '';
                        //var petak = hmStrata.substring(16, 19).replace(/^0+/, '') + hmStrata.substring(19, 21).replace(/^0+/, ''); nad comment sebab dari L101 jadi L11
                        if (jnshm === 'PN' || jnshm === 'PM' || jnshm === 'GM') { // landed PN PM GM
                            var hmInduk = hmStrata.substring(0, 16);
                            var banggunan = '';
                            var tinggat = '';
                            var petak = hmStrata.substring(16, 21).replace(/^0+/, ''); //add by nad 06/01/2020: utk baca petak L cth L101
                            $("#hakmilik${i - 1}").val(hmInduk);
                            $("#bangunan${i - 1}").val(banggunan);
                            $("#tingkat${i - 1}").val(tinggat);
                            $("#petak${i - 1}").val(petak);
                        } else if (jnshm === 'GRN' || jnshm === 'GMM') { // PL >=10 GRN GMM
                            var hmInduk = hmStrata.substring(0, 17);
                            var banggunan = hmStrata.substring(17, 21).replace(/^0+/, ''); // get substring building from id and ignore first zeros
                            var tinggat = '';
                            var petak = '';
                            $("#hakmilik${i - 1}").val(hmInduk);
                            $("#bangunan${i - 1}").val(banggunan);
                            $("#tingkat${i - 1}").val(tinggat);
                            $("#petak${i - 1}").val(petak);
                        }
                    }
                    else if (length === 20) { 
                        var jnshm = hmStrata.substring(6,9).replace(/0+$/, ''); // get substring building from id and ignore last zeros
                        //alert('Jenis Hakmilik: ' + jnshm);
                        //var hmInduk = hmStrata.substring(0, 17);
                        //var banggunan = hmStrata.substring(17, 20).replace(/^0+/, ''); // get substring building from id and ignore first zeros
                        //var tinggat = '';
                        //var petak = '';
                        if (jnshm === 'GRN' || jnshm === 'GMM') { // P & PL - GRN GMM
                            var hmInduk = hmStrata.substring(0, 17);
                            var banggunan = hmStrata.substring(17, 20).replace(/^0+/, ''); // get substring building from id and ignore first zeros
                            var tinggat = '';
                            var petak = '';
                            $("#hakmilik${i - 1}").val(hmInduk);
                            $("#bangunan${i - 1}").val(banggunan);
                            $("#tingkat${i - 1}").val(tinggat);
                            $("#petak${i - 1}").val(petak);
                        } else if (jnshm === 'GM' || jnshm === 'PM' || jnshm === 'PN') {
                            var hmInduk = hmStrata.substring(0, 16);
                            var banggunan = hmStrata.substring(16, 20).replace(/^0+/, ''); // get substring building from id and ignore first zeros
                            //alert('bangunan: ' + banggunan);
                            var tinggat = '';
                            var petak = '';
                            $("#hakmilik${i - 1}").val(hmInduk);
                            $("#bangunan${i - 1}").val(banggunan);
                            $("#tingkat${i - 1}").val(tinggat);
                            $("#petak${i - 1}").val(petak);
                        } 
                    }
                    else if (length === 19) { // P & PL - PN PM GM 
                        var hmInduk = hmStrata.substring(0, 16);
                        var banggunan = hmStrata.substring(16, 19).replace(/^0+/, ''); // get substring building from id and ignore first zeros
                        var tinggat = '';
                        var petak = '';
                        $("#hakmilik${i - 1}").val(hmInduk);
                        $("#bangunan${i - 1}").val(banggunan);
                        $("#tingkat${i - 1}").val(tinggat);
                        $("#petak${i - 1}").val(petak);
                    }
                    if (!validateSamaHakmilik(${i - 1})) {
                        $("#hakmilikStrata${i - 1}").val('');
                        $("#hakmilik${i - 1}") == null;
                        $("#bangunan${i - 1}").val('');
                        $("#tingkat${i - 1}").val('');
                        $("#petak${i - 1}").val('');
                        alert('Hakmilik Sama telah dipilih.');
                        return;
                    }
                    validateHakmilikStrata(${i - 1}, 'hakmilikStrata');
                });

                $("#hakmilik${i - 1}").blur(function() {
                    if (!validateSamaHakmilik(${i - 1})) {
                        $("#hakmilik${i - 1}")== null;
                        $("#bangunan${i - 1}").val('');
                        $("#tingkat${i - 1}").val('');
                        $("#petak${i - 1}").val('');
                        alert('Hakmilik Sama telah dipilih.');
                        return;
                    }
                    validateHakmilik(${i - 1});
                });

                $("#bangunan${i - 1}").blur(function() {
                    var val = $(this).val();
                    if (val == null || val == '')
                        return;
                    if (!isNaN(val)) {
                        $(this).val(appendLeadingZero(val.length, 3, val));
                    }
                    if (!validateSamaHakmilik(${i - 1})) {
                        $("#hakmilik${i - 1}")== null;
                        $("#bangunan${i - 1}").val('');
                        $("#tingkat${i - 1}").val('');
                        $("#petak${i - 1}").val('');
                        alert('Hakmilik Sama telah dipilih.');
                        return;
                    }
                    validateHakmilikStrata(${i - 1}, 'bangunan');
                });

                $("#tingkat${i - 1}").blur(function() {
                    var val = $(this).val();
                    if (val == null || val == '')
                        return;
                    if (!isNaN(val)) {
                        $(this).val(appendLeadingZero(val.length, 3, val));
                    }

                    if (!validateSamaHakmilik(${i - 1})) {
                        $("#hakmilik${i - 1}")== null;
                        $("#bangunan${i - 1}").val('');
                        $("#tingkat${i - 1}").val('');
                        $("#petak${i - 1}").val('');
                        alert('Hakmilik Sama telah dipilih.');
                        return;
                    }
                    validateHakmilikStrata(${i - 1}, 'tingkat');
                });

                $("#petak${i - 1}").blur(function() {
                    var val = $(this).val();
                    if (val == null || val == '')
                        return;
                    if (!isNaN(val)) {
                        $(this).val(appendLeadingZero(val.length, 5, val));
                    }

                    if (!validateSamaHakmilik(${i - 1})) {
                        $("#hakmilik${i - 1}")== null;
                        $("#bangunan${i - 1}").val('');
                        $("#tingkat${i - 1}").val('');
                        $("#petak${i - 1}").val('');
                        alert('Hakmilik Sama telah dipilih.');
                        return;
                    }
                    validateHakmilikStrata(${i - 1}, 'petak');
                });


    </c:forEach>
        });

        function appendLeadingZero(val, val2, val3) {
            var r = val3;
            for (var i = val; i < val2; i++) {
                r = "0" + r;
            }
            return r;
        }

        function validateSamaHakmilik(idxHakmilik) {

            var val = $("#hakmilik" + idxHakmilik).val();
            var bgn = $("#bangunan" + idxHakmilik).val();
            var tingkat = $("#tingkat" + idxHakmilik).val();
            var petak = $("#petak" + idxHakmilik).val();

    <c:forEach var="i" begin="1" end="${actionBean.bilHakmilik}" >
            if ($("#hakmilik${i - 1}").val() != '' && $("#bangunan${i - 1}").val() != ''
                && $("#tingkat${i - 1}").val() != '' && $("#petak${i - 1}").val() != '') {
                if (idxHakmilik !=${i-1} && $("#hakmilik${i - 1}").val() == val && $("#bangunan${i - 1}").val() == bgn
                    && $("#tingkat${i - 1}").val() == tingkat && $("#petak${i - 1}").val() == petak) {
                    return false;
                }
            }
    </c:forEach>
            return true;
        }

        function validateHakmilik(idxHakmilik) {
            var val = $("#hakmilik" + idxHakmilik).val();
            frm = this.form;
            if (val == null || val == "")
                return;
            val = val.toUpperCase();
            $("#hakmilik" + idxHakmilik).val(val);
            //utk kod urusan CRHMB, takyah check status hakmilik 'B'
            $.get("${pageContext.request.contextPath}/daftar/check_idhakmilik?doCheckHakmilikCarianStrata&idHakmilik=" + val,
            function(data) {
                if (data == '1') {
                    //$("#msg" + idxHakmilik).html("OK");
                } else if (data == '0') {
                    // disable if invalid Hakmilik
                    // $("#collectPayment").attr("disabled", "true");
                    $("#hakmilik" + idxHakmilik).val("");
                    alert("ID Hakmilik " + val + " tidak wujud!");
                    // $("#msg" + idxHakmilik).html("<font color=red>ID Hakmilik " + val + " tidak wujud!</font>");
                } else if (data == '2') {
                    // disable if invalid Hakmilik
                    // $("#collectPayment").attr("disabled", "true");
                    //$("#hakmilik" + idxHakmilik).val("");
                    //alert("Cukai untuk Hakmilik " + val + " masih belum dijelaskan!");
                } else if (data == '3') {
                    //$("#hakmilik" + idxHakmilik).val("");
                    //alert("Status hakmilik Provisional. Tiada Urusniaga / Kaveat Persendirian / Lien dibenarkan.");
                } else if (data.charAt(0) == '4') {
                    $("#hakmilik" + idxHakmilik).val("");
                    var str = "Hakmilik " + val + " telah dibatalkan.";
                    if (data.substring(2).length > 0)
                        str += " Hakmilik sambungan ialah " + data.substring(2) + ".";
                    alert(str);
                    $("#msg" + idxHakmilik).html(str);
                } else {
                    alert(data);
                    $("#hakmilik" + idxHakmilik).val("");
                }
            });
        }

        function validateHakmilikStrata(idxHakmilik, id) {
            var val = $("#hakmilik" + idxHakmilik).val();
            var bangunan = $("#bangunan" + idxHakmilik).val();
            var tingkat = $("#tingkat" + idxHakmilik).val();
            var petak = $("#petak" + idxHakmilik).val();
            var hmstrata = $("#hakmilikStrata" + idxHakmilik).val();
            frm = this.form;
            if (val == null || val == "")
                return;
            val = val.toUpperCase();
            $("#hakmilik" + idxHakmilik).val(val);

            $.get("${pageContext.request.contextPath}/daftar/check_idhakmilik_strata?doCheckHakmilikCarianStrata&idHakmilik=" + val
                + "&noBangunan=" + bangunan + "&noPetak=" + petak + "&noTingkat=" + tingkat+ "&hmstrata=" + hmstrata,
            function(data) {
                if (data == '0') {
                    //$("#msg" + idxHakmilik).html("OK");
                    $('#Step4').attr("disabled", false);
                } else if (data == '1') {
                    alert('Id Hakmilik ' + hmstrata + ' tidak dijumpai');
                    $('#hakmilikStrata' + idxHakmilik).val('');
                    $('#hakmilik' + idxHakmilik).val('');
                    $('#bangunan' + idxHakmilik).val('');
                    $('#tingkat' + idxHakmilik).val('');
                    $('#petak' + idxHakmilik).val('');
                    $('#Step4').attr("disabled", true);
                    //                  $('#hakmilikStrata'  + idxHakmilik).focus();
                }
                if(data == '2'){
                    alert("Hakmilik : " + hmstrata + " adalah Milik PTG.");
                    $('#Step4').attr("disabled", true);
                }
                if(data == '3'){
                    alert("Versi hakmilik " + hmstrata + " adalah versi 0.Urusan Tukar Ganti Hakmilik Strata perlu dilakukan terlebih dahulu");
                    $('#Step4').attr("disabled", true);
                }
            });


        }

        function validateHakmilikBersiri(idx) {
            var dr = $("#idHakmilikSiriDari" + idx).val();
            var ke = $("#idHakmilikSiriKe" + idx).val();
            var kodSerah = '${actionBean.kodSerah}';
            var kodUrusan = '${actionBean.urusan}';
            frm = this.form;
            if (dr == null || dr == "" || ke == null || ke == "")
                return;
            dr = dr.toUpperCase();
            ke = ke.toUpperCase();
            $.get("${pageContext.request.contextPath}/daftar/check_siri_idhakmilik?doCheckHakmilikBersiriStrata&kodUrusan=${kodUrusan}&" +
                "idHakmilikDari=" + dr + "&idHakmilikKe=" + ke + "&kodSerah=" + kodSerah + "&kodUrusan=" + kodUrusan,
            function(data) {
                if (data == '1') { // no problem
                    alert("ID Hakmilik Siri " + (idx + 1) + " adalah sah!");
                }
                else if (data == '0') { // hakmilik tidah wujud
                    // disable if invalid Hakmilik
                    //$("#Step4").attr("disabled", "true");
                    alert("Terdapat ID Hakmilik yang tidak wujud dalam Siri " + (idx + 1) + "!");
                }
                else if (data == '2') { // check payment
                    alert("ID Hakmilik Siri " + (idx + 1) + " adalah sah!");
                    // disable if invalid Hakmilik
                    //$("#collectPayment").attr("disabled", "true");
                    //$("#idHakmilikSiriDari" + idx).val("");
                    //$("#idHakmilikSiriKe" + idx).val("");
                    //alert("Terdapat cukai dalam Siri " + idx + " yang masih belum dilunaskan!");
                } else if (data == "3") { // invalid
                    alert("ID Hakmilik Siri " + (idx + 1) + " tidak sah!");
                } else if (data == "7") { // Batal
                    alert("Terdapat ID Hakmilik yang batal dalam Siri " + (idx + 1) + "!");
                }
            });
        }

        function validate() {
            var bil = document.getElementById('bilHakmilik');
            if (bil.value <= 0) {
                alert("Bilangan Perserahan mestilah tidak kurang daripada 0");
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

        function RemoveNonNumeric(strString) {
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
</script>
<p class=title>Langkah 2: Tentukan Hakmilik-hakmilik Terlibat</p>

<s:messages />
<s:errors />

<span class=instr>Masukkan semua Hakmilik yang terlibat. Biarkan kosong mana-mana medan
    yang tidak berkaitan. </span><br/>

<s:form action="/daftar/carian" id="pilih_hakmilik">

    <fieldset class="aras1">
        <legend>ID Perserahan</legend>

        <p>
            <label for="bilHakmilik">Bil. Hakmilik</label>
            <s:text name="bilHakmilik" id="bilHakmilik" size="4" onblur="javascript:nonNumber(this, this.value);"/> atau kurang
            <s:submit name="Step2" value="Kemaskini" class="btn" onclick="return validate()"/>
        </p>
        <div align="center">
            <table border=0 class="tablecloth">
                <tr>
                    <c:forEach var="i" begin="1" end="${actionBean.bilHakmilik}" >
                        <th align=right>
                            <c:if test="${actionBean.urusan eq 'CSR'}">ID Hakmilik Induk ${i}:</c:if>
                            <c:if test="${actionBean.urusan ne 'CSR'}">ID Hakmilik Strata ${i}:</c:if>
                        </th>
                        <td align=left>
                            <c:if test="${actionBean.urusan ne 'CSR'}">
                                <s:text name="hakmilikStrata${i - 1}" id="hakmilikStrata${i - 1}" size="37" onblur="validateHakmilikStrata();"/>&nbsp;
                            </c:if>
                            <c:if test="${actionBean.urusan eq 'CSR'}">
                                <s:text name="hakmilikStrata${i - 1}" id="hakmilikStrata${i - 1}" size="37" onblur="validateHakmilik();"/>&nbsp;
                            </c:if>
                            <s:hidden name="hakmilikPermohonan[${i - 1}].idHakmilik" id="hakmilik${i - 1}"/>
                            <s:hidden name="hakmilikPermohonan[${i - 1}].noBangunan" id="bangunan${i - 1}"/>
                            <s:hidden name="hakmilikPermohonan[${i - 1}].noTingkat" id="tingkat${i - 1}" />
                            <s:hidden name="hakmilikPermohonan[${i - 1}].noPetak" id="petak${i - 1}" />
                        </td>
                        <c:if test="${i % 2 == 0}" >
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </div>
        <!--comment out by azri 15/8/2013 : use above search function -->
        <%--
        <table border=0 class="tablecloth">
        <c:forEach var="i" begin="1" end="${actionBean.bilHakmilik}" >
    <tr>
    <th align=right>
          ID Hakmilik ${i}:
        </th>
        <td align=left>
          <s:text name="hakmilikPermohonan[${i - 1}].idHakmilik" id="hakmilik${i - 1}"/>&nbsp;
        </td>
        <th align=right>
          No Bangunan ${i}  :
        </th>
        <td align=left>
          <s:text name="hakmilikPermohonan[${i - 1}].noBangunan" id="bangunan" maxlength="3" size="10" onblur="this.value = this.value.toUpperCase();"/>&nbsp;
        </td>
        <th align=right>
          No Tingkat ${i}  :
        </th>
        <td align=left>
          <s:text name="hakmilikPermohonan[${i - 1}].noTingkat" id="tingkat" maxlength="3" size="10" onblur="this.value = this.value.toUpperCase();"/>&nbsp;
        </td>
        <th align=right>
          No Petak ${i}  :
        </th>
        <td align=left>
          <s:text name="hakmilikPermohonan[${i - 1}].noPetak" id="petak" maxlength="5" size="10" onblur="this.value = this.value.toUpperCase();"/>&nbsp;
        </td>
        </tr>
      </c:forEach>
    </table>--%>
        <br>
    </fieldset>
    <br>
    <fieldset class="aras1">
        <legend>ID Hakmilik Strata bersiri</legend>
        <div align="center">
            <p>
                Bil. Hakmilik Bersiri 1
                <s:text name="bilHakmilikBersiri0" id="bilHakmilikBersiri0" size="3" onchange="appendAutoAll('0');" maxlength="3"/>&nbsp;
                Siri 1: ID Hakmilik Strata dari
                <s:text name="idHakmilikSiriDari[0]" id="idHakmilikSiriDari0" size="35" maxlength="28"
                        onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"
                        onchange="appendAuto(this.value, '0');"/>
                hingga
                <s:text name="idHakmilikSiriKe[0]" id="idHakmilikSiriKe0" size="35" maxlength="28"
                        onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/>&nbsp;
                <input type="button" value="Sahkan" onclick="validateHakmilikBersiri(0)" class="btn" />
            </p><p>
                Bil. Hakmilik Bersiri 2
                <s:text name="bilHakmilikBersiri1" id="bilHakmilikBersiri1" size="3" onchange="appendAutoAll('1');" maxlength="3"/>&nbsp;
                Siri 2: ID Hakmilik Strata dari
                <s:text name="idHakmilikSiriDari[1]" id="idHakmilikSiriDari1" size="35" maxlength="28"
                        onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"
                        onchange="appendAuto(this.value, '1');"/>
                hingga
                <s:text name="idHakmilikSiriKe[1]" id="idHakmilikSiriKe1" size="35" maxlength="28"
                        onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/>&nbsp;
                <input type="button" value="Sahkan" onclick="validateHakmilikBersiri(1)" class="btn" />
            </p><p>
                Bil. Hakmilik Bersiri 3
                <s:text name="bilHakmilikBersiri2" id="bilHakmilikBersiri2" size="3" onchange="appendAutoAll('2');" maxlength="3"/>&nbsp;
                Siri 3: ID Hakmilik Strata dari
                <s:text name="idHakmilikSiriDari[2]" id="idHakmilikSiriDari2" size="35" maxlength="28"
                        onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"
                        onchange="appendAuto(this.value, '2');"/>
                hingga
                <s:text name="idHakmilikSiriKe[2]" id="idHakmilikSiriKe2" size="35" maxlength="28"
                        onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/>&nbsp;
                <input type="button" value="Sahkan" onclick="validateHakmilikBersiri(2)" class="btn" />
            </p><p>
                Bil. Hakmilik Bersiri 4
                <s:text name="bilHakmilikBersiri3" id="bilHakmilikBersiri3" size="3" onchange="appendAutoAll('3');" maxlength="3"/>&nbsp;
                Siri 4: ID Hakmilik Strata dari
                <s:text name="idHakmilikSiriDari[3]" id="idHakmilikSiriDari3" size="35" maxlength="28"
                        onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"
                        onchange="appendAuto(this.value, '3');"/>
                hingga
                <s:text name="idHakmilikSiriKe[3]" id="idHakmilikSiriKe3" size="35" maxlength="28"
                        onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/>&nbsp;
                <input type="button" value="Sahkan" onclick="validateHakmilikBersiri(3)" class="btn" />
            </p><p>
                Bil. Hakmilik Bersiri 5
                <s:text name="bilHakmilikBersiri4" id="bilHakmilikBersiri4" size="3" onchange="appendAutoAll('4');" maxlength="3"/>&nbsp;
                Siri 5: ID Hakmilik Strata dari
                <s:text name="idHakmilikSiriDari[4]" id="idHakmilikSiriDari4" size="35" maxlength="28"
                        onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"
                        onchange="appendAuto(this.value, '4');"/>
                hingga
                <s:text name="idHakmilikSiriKe[4]" id="idHakmilikSiriKe4" size="35" maxlength="28"
                        onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/>&nbsp;
                <input type="button" value="Sahkan" onclick="validateHakmilikBersiri(4)" class="btn" />
            </p>
        </div>
        <br/>
        <p><label>&nbsp;</label>
            <s:submit name="Step1" value="Kembali" class="btn" />
            <s:submit name="Cancel" value="Batal" class="btn" />
            <s:button name="" value="Isi Semula" class="btn" onclick="clearText('pilih_hakmilik');"/>
            <c:if test="${actionBean.urusan ne 'CSR'}">
                <s:submit name="Step2a" value="Seterusnya" class="btn" id="Step4" onclick="validateHakmilikStrata();"/>
            </c:if>
            <c:if test="${actionBean.urusan eq 'CSR'}">
                <s:submit name="Step2d" value="Seterusnya" class="btn" id="Step4" onclick="validateHakmilik();"/>
            </c:if>
        </p>
        <br>
    </fieldset>
</s:form>
