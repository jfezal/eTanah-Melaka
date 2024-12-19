<%-- 
    Document   : bayaran_pelbagai_1
    Created on : Apr 12, 2010, 12:28:56 PM
    Author     : abdul.hakim
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloths.css"/>

<div align="center">
    <table style="width:99.2%" bgcolor="green">
        <tr><td width="100%" height="20" ><div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Bayaran Pelbagai</font>
                </div></td></tr>
    </table>
</div>
<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<stripes:form beanclass="etanah.view.stripes.hasil.BayaranPelbagaiActionBean" id="bayaran_pelbagai">
    <stripes:errors />

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Cara Bayaran</legend>
            <p class=instr><em><font color="black">Masukkan butir-butir pembayaran.<br>&nbsp;
                    <%--<font color="red">PERINGATAN:</font> Tidak dibenarkan menggunakan cara pembayaran yang lain bersama dengan pembayaran menggunakan Cek ,
                    Deraf Bank dan Wang dalam Pindahan.--%></font></em>
            </p>
            &nbsp;&nbsp;&nbsp;&nbsp;<s:errors field="amaun"/>
            <div align="center">
                <table>
                    <%--<tr>
                        <td align="right">
                            <stripes:submit name="addCaraBayar" value="Tambah" class="btn"/>
                            <stripes:button name=" " value="Isi Semula" class="btn" onclick="clearText1('hasil');"/>
                        </td>
                    </tr>--%>
                    <tr>
                        <td>
                            <display:table name="${actionBean.senaraiCaraBayaran}" id="row" class="tablecloths">
                                <display:column title="Bil."><div align="center">${row_rowNum}.</div></display:column>
                                <display:column title="Cara Bayaran" class="tunai">
                                    <stripes:select name="senaraiCaraBayaran[${row_rowNum - 1}].kodCaraBayaran.kod"
                                                    id="senaraiCaraBayaran${row_rowNum - 1}" onchange="javaScript:change(${row_rowNum -1})">
                                        <stripes:option value="0" label="Pilih ..." />
                                        <stripes:options-collection collection="${listUtil.senaraiKodCaraBayaran}"  label="nama" value="kod" sort="nama"/>
                                    </stripes:select>
                                </display:column>

                                <display:column title="Bank / Agensi Pembayaran" >
                                    <em id="sBank${row_rowNum - 1}">*</em>
                                    <stripes:select name="senaraiCaraBayaran[${row_rowNum - 1}].bank.kod" id="bank${row_rowNum - 1}">
                                        <stripes:option label="Pilih..." value="0" />
                                        <stripes:options-collection collection="${listUtil.senaraiKodBank}"  label="nama" value="kod" />
                                    </stripes:select>
                                </display:column>

                                <display:column title="Cawangan" >
                                    <em id="tcek${row_rowNum - 1}">*</em><stripes:text name="senaraiCaraBayaran[${row_rowNum - 1}].bankCawangan" id="caw${row_rowNum - 1}" size="20"  onkeyup="this.value=this.value.toUpperCase();"/>
                                </display:column>

                                <display:column title="No. Rujukan" style="width:60%">
                                    <em id="a${row_rowNum - 1}">*</em><stripes:text name="senaraiCaraBayaran[${row_rowNum - 1}].noRujukan" id="rujukan${row_rowNum - 1}" size="13" />
                                </display:column>
                                <display:column title="No. Akaun Pembayar"  style="width:15%;text-align:center"><em id="v${row_rowNum - 1}">*</em>
                                    <stripes:text name="senaraiCaraBayaran[${row_rowNum - 1}].noAkaunCek" id="akaunpembayar${row_rowNum - 1}" size="15" class="number" onblur="javascript:numberValidation(this,${row_rowNum - 1});"/>
                                </display:column>
                                <display:column title="Tarikh"  style="width:65%">
                                    <em id="tcek${row_rowNum - 1}">*</em><stripes:text name="tarikhCek[${row_rowNum-1}]" id="trkh${row_rowNum - 1}" size="10" readonly="true" maxlength="10" onchange="dateValidation(this.value,${row_rowNum -1})" class="datepicker"/>
                                </display:column>

                                <display:column title="Amaun (RM)">
                                    <stripes:text name="senaraiCaraBayaran[${row_rowNum - 1}].amaun" size="12" class="number"
                                                  onblur="javascript:updateTotals(this,${row_rowNum - 1});" id="amaun${row_rowNum - 1}"/>
                                </display:column>
                                <display:footer>
                            <tr>
                                <td colspan="6"><div align="right"><b>Jumlah (RM):</b></div></td>
                                <td><input name="jumlahBayar" value="0.00" id="jumCaraBayar" size="12" class="number" readonly="true"/></td>
                            </tr>
                        </display:footer>
                    </display:table >
                    </td>
                    </tr>
                </table>
            </div>
            <br>
        </fieldset>
    </div>

    <div align="center"><table border="0" bgcolor="green" style="width:99.2%">
            <tr>
                <td align="right">
                    <stripes:submit name="bayar" value="Bayar" onclick="return checkAmount()" class="btn" id="pay"/>
                    <stripes:submit name="kembali" value="Kembali" class="btn"/>
                </td>
            </tr>
        </table></div>
    </stripes:form>
<script language="javascript" >
    $(document).ready(function() {
        // set the first default payment to Tunai
        $('#senaraiCaraBayaran0').val('T');
        // focus on the first payment
        $("#bank0").hide();
        $("#caw0").hide();
        $("#rujukan0").hide();
        $("#trkh0").hide();
        $("#a0").hide();
        $("#sBank0").hide();
        $("#tcek0").hide();
        $("#akaunpembayar0").hide();
        $('#amaun0').val((${actionBean.jumlah}).toFixed(2));
        $('#jumCaraBayar').val((${actionBean.jumlah}).toFixed(2));

        for (var i = 1; i < 5; i++) {
            $("#a" + i).hide();
            $("#sBank" + i).hide();
            $('#bank' + i).attr("disabled", "true");
            $('#caw' + i).attr("disabled", "true");
            $('#rujukan' + i).attr("disabled", "true");
            $('#trkh' + i).attr("disabled", "true");
            $('#amaun' + i).attr("disabled", "true");
            $('#amaun' + i).val("0");
            $('#akaunpembayar' + i).attr("disabled", "true");
            $("#tcek" + i).hide();
        }

        function stopRKey(evt) {
            var evt = (evt) ? evt : ((event) ? event : null);
            var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
            if ((evt.keyCode == 13) && (node.type == "text")) {
                return false;
            }
        }

        document.onkeypress = stopRKey;
    });
</script>
<script type="text/javascript">
    function checkAmount() {
        var asal = ${actionBean.jumlah};
        var bayar = document.getElementById('jumCaraBayar');
        if (asal > bayar.value) {
            alert("Amaun yang dibayar kurang.");
            return false;
        }
        for (var i = 0; i < 5; i++) {
            var a = document.getElementById('senaraiCaraBayaran' + i);
            var c = document.getElementById('rujukan' + i);
            var d = document.getElementById('trkh' + i);
            var e = document.getElementById('akaunpembayar' + i);
            var caw = document.getElementById('caw' + i);
            var bank = document.getElementById('bank' + i);
            if ((a.value != '0') && (a.value != 'T')) {
                if (bayar.value > asal) {
                    var j = parseInt(bayar.value) - parseInt(asal);
                    alert("Terdapat lebihan bayaran sebanyak RM " + j.toFixed(2));
                    return false;
                }
                if (c.value == "") {
                    alert("Sila Masukkan Nombor Rujukan.");
                    $('#rujukan' + i).focus();
                    return false;
                }
                if (d.value == "") {
                    alert("Sila Masukkan Tarikh.");
                    $('#trkh' + i).focus();
                    return false;
                }

                if (a.value == 'C' || a.value == 'CT' || a.value == 'CS' || a.value == 'CL' || a.value == 'DB') {
                    if (bank.value == "0") {
                        alert("Sila Masukkan Bank / Agensi Pembayaran.");
                        $('#bank' + i).focus();
                        return false;
                    }
                    if (e.value == "" && a.value != 'DB') {
                        alert("Sila Masukkan No Akaun Pembayar.");
                        $('#akaunpembayar' + i).focus();
                        return false;
                    }

                    if (caw.value == "") {
                        alert("Sila Masukkan Cawangan.");
                        $('#caw' + i).focus();
                        return false;
                    }
                }
//                if (caw.value == "" && ((a.value != 'KK') && (a.value != 'DK') && (a.value != 'WP') && (a.value != 'KW') && (a.value != 'AM'))) {
//                    alert("Sila Masukkan Cawangan.");
//                    $('#caw' + i).focus();
//                    return false;
//                }
            }
        }
    }

    function dateValidation(value, row) {
        var vsplit = value.split('/');
        var fulldate = vsplit[1] + '/' + vsplit[0] + '/' + vsplit[2];
        var today = new Date();
        var sdate = new Date(fulldate);
        if (sdate > today) {
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#trkh' + row).val("");
        }
    }

    function updateTotals(inputTxt, row) {
        var total = 0;
    <%--for (var i = 0; i <bil; i++){--%>
        var a = document.getElementById('amaun' + row)
        if ((isNaN(a.value)) || ((a.value) == "")) {
            alert("Nombor tidak sah");
            inputTxt.value = RemoveNonNumeric(a);
            $('#jumCaraBayar').val("0.00");
            return;
        }
        total += parseFloat(a.value);
    <%--}--%>
        var t = document.getElementById('jumCaraBayar');
        t.value = total.toFixed(2);
        inputTxt.value = parseFloat(inputTxt.value).toFixed(2);
        updateTot();
        var yy = row - 1;
        if (yy >= 0) {
            var t = document.getElementById('jumCaraBayar');
            var bal = (parseInt(${actionBean.jumlah}) - parseInt(t.value)).toFixed(2);
            if (bal > 0)
                $('#amaun' + (row + 1)).val(bal);
        } else {
            var t = document.getElementById('jumCaraBayar');
            var bal = (parseInt(${actionBean.jumlah}) - parseInt(t.value)).toFixed(2);
            if (bal > 0)
                $('#amaun' + (row + 1)).val(bal);
        }
    }

    function updateTot() {
        var total = 0;
        for (var i = 0; i < 5; i++) {
            var a = document.getElementById('amaun' + i)
            total += parseFloat(a.value);
        }
        var t = document.getElementById('jumCaraBayar');
        t.value = total.toFixed(2);
    }

    function RemoveNonNumeric(strString) {
        var strValidCharacters = "1234567890.";
        var strReturn = "0.00";
        var strBuffer = "0";
        var intIndex = 0.00;
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

    function edit(id) {
        window.open("${pageContext.request.contextPath}/hasil/kutipan_hasil?showEditPemohon&idHakmilik=" + id, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=900,height=300");
    }

    function remove(id) {
    <%--var queryString = $(f).formSerialize()--%>
        alert("Hapus ID Hakmilik : " + id + "?");
        var url = "${pageContext.request.contextPath}/hasil/kutipan_hasil?delete&idHakmilik=" + id;
        $.get(url,
                function(data) {
                    $('#page_div').html(data);
                });
    }

    function change(row) {
        var curr = document.getElementById('amaun' + row);
        var bil = parseInt(${actionBean.bil});
        var ax = document.getElementById('senaraiCaraBayaran' + row);
        $("#trkh" + row).val("");
        for (var i = 0; i < (row + 1); i++) {
            var a = document.getElementById('senaraiCaraBayaran' + i);
            if (a.value != '0') {
                $('#bank' + i).removeAttr("disabled");
                $('#caw' + i).removeAttr("disabled");
                $('#rujukan' + i).removeAttr("disabled");
                $('#trkh' + i).removeAttr("disabled");
                $('#amaun' + i).removeAttr("disabled");
                $('#akaunpembayar' + i).removeAttr("disabled");
    <%--$("#trkh"+i).val("");--%>
                if (a.value == 'T') {
                    $('#a' + i).hide();
                    $('#b' + i).hide();
                    $('#tcek' + i).hide();
                    $('#tCawangan' + i).hide();
                    $('#bank' + i).hide();
                    $("#caw" + i).hide();
                    $("#rujukan" + i).hide();
                    $("#akaunpembayar" + i).hide();
                    $("#trkh" + i).hide();
                    $('#a' + i).val("");
                    $('#bank' + i).val('0');
                    $("#caw" + i).val("");
                    $("#rujukan" + i).val("");
                    var today = new Date();
                    $("#trkh" + i).val(today.getDate() + "/" + (today.getMonth() + 1) + "/" + today.getYear());
                }
                else if ((a.value == 'KW') || (a.value == 'WP')) {
                    $('#bank' + i).val("PMB");
                    $('#bank' + i).attr("disabled", "true");
                    $('#bank' + i).show();
                    $('#caw' + i).show();
                    $('#rujukan' + i).show();
                    $('#akaunpembayar' + i).hide();
                    $('#trkh' + i).show();
                    $('#a' + i).show();
                    $('#b' + i).show();
                    $('#v' + i).hide();
                    $('#tcek' + i).show();
                    $('#tCawangan' + i).show();
                    $('#checking').val("N");
                    if (a.value == '0') {
                        $('#a' + i).hide();
                        $('#b' + i).hide();
                        $('#v' + i).hide();
                        $('#tcek' + i).hide();
                    }
                }
                else if ((a.value == 'C') || (a.value == 'CT') || (a.value == 'CL') || (a.value == 'CT') || (a.value == 'CS')) {
                    $('#bank' + i).removeAttr("disabled");
                    $('#bank' + i).show();
                    $('#caw' + i).show();
                    $('#rujukan' + i).show();
                    $('#akaunpembayar' + i).show();
                    $('#trkh' + i).show();
                    $('#a' + i).show();
                    $('#b' + i).show();
                    $('#v' + i).show();
                    $('#tcek' + i).show();
                    $('#tCawangan' + i).show();
                    $('#checking').val("N");
                    if (a.value == '0') {
                        $('#a' + i).hide();
                        $('#b' + i).hide();
                        $('#v' + i).hide();
                        $('#tcek' + i).hide();
                    }
                }
                else {
                    $('#bank' + i).removeAttr("disabled");
                    $('#bank' + i).show();
                    $('#caw' + i).show();
                    $('#rujukan' + i).show();
                    $('#akaunpembayar' + i).hide();
                    $('#trkh' + i).show();
                    $('#a' + i).show();
                    $('#b' + i).show();
                    $("#v" + i).hide();
                    $('#tcek' + i).show();
                    $('#tCawangan' + i).show();
                    $('#checking').val("N");
                    if (a.value == '0') {
                        $('#a' + i).hide();
                        $('#b' + i).hide();
                        $("#v" + i).hide();
                        $('#tcek' + i).hide();
                    }
                }
            } else {
                $("#a" + i).hide();
                $("#b" + i).hide();
                $("#v" + i).hide();
                $('#tcek' + i).hide();
                $('#tCawangan' + i).hide();
                $('#bank' + i).attr("disabled", "true");
                $('#caw' + i).attr("disabled", "true");
                $('#rujukan' + i).attr("disabled", "true");
                $('#akaunpembayar' + i).attr("disabled", "true");
                $('#trkh' + i).attr("disabled", "true");
                $('#amaun' + i).attr("disabled", "true");
                $('#amaun' + i).val("0");
                $('#bank' + i).val("0");
                $('#caw' + i).val("");
                $('#rujukan' + i).val("");
                $('#trkh' + i).val("");
                $('#checking').val("N");
                updateTotal();
            }

    <%--var b = document.getElementById('senaraiCaraBayaran'+(i+1));
    if(b.value != '0'){
        if(((a.value == 'CT')||(a.value == 'CL')||(a.value == 'CC')||(a.value == 'BC')||(a.value == 'IB')||(a.value == 'CB'))&&(b.value != '0')){
            alert("Bayaran Cek tidak boleh disertakan bersama Mod Bayaran lain.");
            return b.value = '0';
        }
        if(((b.value == 'CT')||(b.value == 'CL')||(b.value == 'CC')||(b.value == 'BC')||(b.value == 'IB')||(b.value == 'CB'))&&(a.value != '0')){
            alert("Bayaran Cek tidak boleh disertakan bersama Mod Bayaran lain.");
            return b.value = '0';
        }
        if((a.value == '0')&&(b.value != '0')){
            alert("Sila masukkan Mod Bayaran mengikut turutan.");
            return b.value = '0';
        }
        if((a.value == 'DB')&&(b.value != '0')){
            alert("Bayaran Deraf Bank tidak boleh disertakan bersama Mod Bayaran lain.");
            return b.value = '0';
        }
        if((b.value == 'DB')&&(a.value != '0')){
            alert("Bayaran Deraf Bank tidak boleh disertakan bersama Mod Bayaran lain.");
            return b.value = '0';
        }
        if((a.value == 'EF')&&(b.value != '0')){
            alert("Bayaran Wang Dalam Pindahan tidak boleh disertakan bersama Mod Bayaran lain.");
            return b.value = '0';
        }
        if((b.value == 'EF')&&(a.value != '0')){
            alert("Bayaran Wang Dalam Pindahan tidak boleh disertakan bersama Mod Bayaran lain.");
            return b.value = '0';
        }
        if((a.value == 'T')&&(b.value == 'T')){
            alert("Hanya satu mod Bayaran Tunai sahaja dibenarkan.");
            return b.value = '0';
        }
    }--%>
            for (var j = i + 1; j < bil; j++) {
                var c = document.getElementById('senaraiCaraBayaran' + j);
                if (c.value != '0') {
                    if ((a.value == 'T') && (c.value == 'T')) {
                        alert("Hanya satu mod Bayaran Tunai sahaja dibenarkan.");
                        return c.value = '0';
                    }
                }
            }
            sequencePayment(row);
        }
        autoBalance(row, ax, curr);
    }

    function autoBalance(row, ax, curr) {
        if (row != 0) {
            if (ax.value != '0') {
                var t = document.getElementById('jumCaraBayar');
                var bal = (parseInt(t.value) + parseInt(curr.value)).toFixed(2);
                $("#jumCaraBayar").val(bal);
            }
        }
    }

    function validate() {
        var t = parseFloat($('#jumCaraBayar').val());
        if (u > t) {
            var bal = u - t;
            alert("Bayaran anda kurang RM " + (bal).toFixed(2));
            return false;
        }
        for (var i = 0; i < 5; i++) {
            var a = document.getElementById('senaraiCaraBayaran' + i);
            var c = $('#rujukan' + i).val();
            if ((a.value != '0') && (a.value != 'T')) {
                if (c == "") {
                    alert("Sila Masukkan Nombor Rujukan.");
                    $('#rujukan' + i).focus();
                    return false;
                }
            }
        }
        return true;
    }

    function reset1() {
        $('#senaraiCaraBayaran0').val('T');
        $("#bank0").hide();
        $("#caw0").hide();
        $("#rujukan0").hide();
        $("#trkh0").hide();
        $("#a0").hide();
        for (var i = 1; i < 5; i++) {
            $('#bank' + i).show();
            $('#caw' + i).show();
            $('#rujukan' + i).show();
            $('#trkh' + i).show();
            $("#a" + i).hide();
            $('#bank' + i).attr("disabled", "true");
            $('#caw' + i).attr("disabled", "true");
            $('#rujukan' + i).attr("disabled", "true");
            $('#trkh' + i).attr("disabled", "true");
            $('#amaun' + i).attr("disabled", "true");
            $('#amaun' + i).val("0");
        }
    }

    function clearText1(id) {
        $("#" + id + " input:text").each(function(el) {
            $(this).val('');
        });

        $("#" + id + " select").each(function(el) {
            $(this).val('');
        });
        reset1();
    }
</script>