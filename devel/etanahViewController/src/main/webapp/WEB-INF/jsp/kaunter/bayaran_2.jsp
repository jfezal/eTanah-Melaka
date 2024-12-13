<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>


<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <c:set var="title" value="Langkah 6:"/>
        <c:set var="action" value="/kaunter/terima_bayaran"/>
        <c:set var="kembali" value="Step6"/>
        <c:set var="selesai" value="Step8"/>

        <c:if test="${!empty carian}">
            <c:set var="title" value="Langkah 4:"/>
            <c:set var="action" value="/daftar/carian"/>
            <c:set var="kembali" value="Step3"/>
            <c:set var="selesai" value="Step5"/>
        </c:if>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />
        <%--<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>--%>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script language="JavaScript" src="<%= request.getContextPath()%>/pub/js/smartDate.js" type="text/javascript"></script>
        <title>${title} Bayaran</title>

    </head>
    <body>
        <script type="text/javascript" language="javascript" >

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

            function remove(item) {
                if (confirm('Adakah anda pasti untuk menghapuskan urusan ini?')) {
                    $('#selectedItem').val(item);
                    $('#removeUrusan').click();
                }
            }

            function edit(item) {
                $('#selectedItem').val(item);
                var submitBtn = document.getElementById('editUrusan');
                submitBtn.click();
            }

            function popupHakmilikDetails(id) {

                var url = "${pageContext.request.contextPath}/hakmilik?popupHakmilikDetail&idHakmilik=" + id;
                window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
            }

        </script>

        <p class=title>Terima Bayaran: Cara Bayaran</p>

        <stripes:messages />
        <stripes:errors />
        <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none" alt=""/>

        <p class=instr>Pastikan maklumat di bawah adalah betul dan masukkan butir-butir pembayaran. </p>

        <stripes:form action="${action}" >

            <c:set var="title" value="Hakmilik Terlibat"/>
            <c:if test="${perserahan}">
                <c:set var="title" value="Perserahan Terlibat"/>
            </c:if>

            <stripes:hidden name="selectedItem" id="selectedItem" />

            <fieldset class="aras1">

                <display:table name="${actionBean.senaraiTransaksi}" id="row1" class="tablecloth" style="width:95%;" >

                    <display:column title="Bil">
                        ${row1_rowNum}
                    </display:column>

                    <display:column title="Urusan" group="1" >
                        <b>${row1.utkUrusan.namaUrusan}</b><div id="controls${row1.utkUrusan.position}"/>
                    </display:column>

                    <display:column title="${title}" group="2">
                        <c:forTokens items="${row1.senaraiHakmilik}" delims="," var="item">
                            <c:choose>
                                <c:when test="${perserahan}">
                                    ${item}
                                </c:when>
                                <c:otherwise>
                                    <a href="javascript:void(0);" onclick="popupHakmilikDetails('${item}');">${item}</a>
                                    &nbsp;

                                </c:otherwise>
                            </c:choose>
                        </c:forTokens>
                    </display:column>

                    <display:column title="Transaksi" >
                        ${row1.kodUrusan}-${row1.namaUrusan}
                    </display:column>

                    <display:column title="Kuantiti" style="width:100px;text-align:right;" >
                        ${row1.kuantiti}
                    </display:column>

                    <display:column property="amaun" title="Fi (RM)" style="text-align:right;width:100px;" format="{0,number, 0.00}" />

                    <display:footer>
                        <tr>
                            <td colspan="5" align="left">Jumlah Perlu Dibayar (RM):</td>
                            <td>
                                <div align="right"><fmt:formatNumber value="${actionBean.jumlahCaj}" pattern="0.00"/></div>
                                <stripes:text name="jumlahCaj" class="number" formatPattern="0.00"
                                              readonly="readonly" style="background:transparent;border:1 px;display:none;" id="total" />
                            </td>
                        </tr>
                    </display:footer>

                </display:table>

                <script language="javascript" >

                    // replace inline the control buttons for above table
                    $(document).ready(function () {
                        for (i = 0; i < 1000; i++) { //asal dier limit 10 taktau kenapa. aku jadikan 1000 nak lepaskan kes je -yus- 13052019
                            if ($('#controls' + i) == null)
                                break;
                            $('#controls' + i).html('<img alt="Klik Untuk Hapus" border="0"                 ' +
                                    'src="${pageContext.request.contextPath}/images/not_ok.gif" class="rem"' +
                                    'id="rem' + i + '" onclick="remove(' + i + ')">&nbsp;' +
                                    '<img alt="Klik Untuk Kemaskini" b                order="0" ' +
                                    'src="${pageContext.request.contextPath}/images/edit.gif" class="edit"' +
                                    'id="edit' + i + '" onclick="edit(' + i + ')">');
                        }
                    });
                </script>

                <c:if test="${actionBean.jumlahCaj > 0}" >

                    <p class=title>Cara Bayaran</p>

                    <display:table name="${actionBean.senaraiCaraBayaran}" id="row"  class="tablecloth">

                        <display:column title="Bil">
                            ${row_rowNum}
                        </display:column>

                        <display:column title="Cara Bayaran" >
                            <stripes:select name="senaraiCaraBayaran[${row_rowNum - 1}].kodCaraBayaran.kod"
                                            id="senaraiCaraBayaran${row_rowNum - 1}" onchange="javaScript:change(${row_rowNum -1})">
                                <stripes:option value="0" label="Pilih ..." />
                                <stripes:options-collection collection="${listUtil.senaraiKodCaraBayaran}"  label="nama" value="kod" sort="nama"/>
                            </stripes:select>
                        </display:column>

                        <display:column title="Bank / Agensi Pembayaran" >
                            <stripes:select name="senaraiCaraBayaran[${row_rowNum - 1}].bank.kod" id="bank${row_rowNum - 1}">
                                <stripes:option label="Pilih..." value="0" />
                                <stripes:options-collection collection="${listUtil.senaraiKodBank}"  label="nama" value="kod" />
                            </stripes:select>
                        </display:column>

                        <display:column title="Cawangan" >
                          <em id="tCawangan${row_rowNum - 1}">*</em>  <stripes:text name="senaraiCaraBayaran[${row_rowNum - 1}].bankCawangan" size="20" id="caw${row_rowNum - 1}" onkeyup="this.value=this.value.toUpperCase();"/>
                        </display:column>

                        <display:column title="No. Rujukan" style="width:15%;text-align:center">
                            <em id="field${row_rowNum - 1}">*</em><stripes:text name="senaraiCaraBayaran[${row_rowNum - 1}].noRujukan" size="13" id="rujukan${row_rowNum - 1}"/>
                        </display:column>

                        <display:column title="Tarikh" style="width:15%;text-align:center">
                            <em id="tcek${row_rowNum - 1}">*</em>
                            <stripes:text name="tarikhCek[${row_rowNum-1}]" id="trkh${row_rowNum - 1}" size="10"
                                          onchange="dateValidation(this.value,${row_rowNum -1})" maxlength="10" class="datepicker"
                                          onblur="editDateBlur(this, 'DD/MM/YYYY')"
                                          onkeypress="return editDatePre(this, 'DD/MM/YYYY', event)"
                                          onkeyup="return editDate(this, 'DD/MM/YYYY', event);"
                                          />
                        </display:column>
                        <display:column title="No. Akaun Pembayar"  style="width:15%;text-align:center"><em id="v${row_rowNum - 1}">*</em>
                            <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].noAkaunCek" id="akaunpembayar${row_rowNum - 1}" size="15" class="number" onblur="javascript:numberValidation(this,${row_rowNum - 1});" />
                        </display:column>

                        <display:column title="Amaun (RM)" >
                            <stripes:text name="senaraiCaraBayaran[${row_rowNum - 1}].amaun" size="12" class="number"
                                          onblur="javascript:updateTotal(this,${row_rowNum - 1});" id="amaun${row_rowNum - 1}" />
                        </display:column>

                        <display:footer>
                            <tr>
                                <td colspan="7" align="right">Jumlah (RM):</td>
                                <td><input name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="12"
                                           class="number" readonly="readonly" style="background:transparent;border:0 px;" /></td>
                            <tr>
                            </display:footer>

                        </display:table >

                    </c:if>

                <fieldset class="aras2">
                    <p><label for="penyerah">Penyerah/Pembayar :</label>
                        ${actionBean.penyerahNama}
                        <stripes:submit name="editPenyerah" value="Kemaskini" class="btn" />
                    </p>
                </fieldset>

                <p><label>&nbsp;</label>
                    <stripes:submit name="CancelAll" value="Batal" class="btn" />
                    <c:if test="${empty carian}">
                        <stripes:submit name="tambahUrusan" value="Tambah Urusan" class="btn" />
                    </c:if>
                    <%--<stripes:submit name="tambahBayaran" value="Tambah Bayaran" class="btn" />--%>
                    <stripes:submit name="${selesai}" value="Selesai" class="btn" onclick="return validate(this.form);"/>
                    <!-- HIDDEN SUBMITS -->
                    <stripes:submit name="removeUrusan" id="removeUrusan" value="removeUrusan" class="btn" style="display:none;"/>
                    <stripes:submit name="editUrusan" id="editUrusan" value="editUrusan" class="btn" style="display:none;"/>
                    <!-- HIDDEN -->
                </p>


            </stripes:form>

            <script language="javascript" >
                $(document).ready(function () {

                    $('form').submit(function () {
                        $.blockUI({
                            message: $('#displayBox'),
                            css: {
                                top: ($(window).height() - 50) / 2 + 'px',
                                left: ($(window).width() - 50) / 2 + 'px',
                                width: '50px'
                            }
                        });
                    });


                    // set the first default payment to Tunai
                    $('#senaraiCaraBayaran0').val('T');
                    $('#amaun0').val((${actionBean.jumlahCaj}).toFixed(2));
                    $('#jumCaraBayar').val((${actionBean.jumlahCaj}).toFixed(2));
                    // focus on the first payment
                    $('#amaun0').focus();
                    $("#bank0").hide();
                    $("#caw0").hide();
                    $("#rujukan0").hide();
                    $("#trkh0").hide();
                    $("#field0").hide();
                    $('#akaunpembayar0').hide();
                    $("#tcek0").hide();
                    $("#tcek0").hide();
                    $('#tCawangan0').hide();
                                $('#v0').hide();
                    for (var i = 1; i < 5; i++) {
                        $("#field" + i).hide();
                        $('#tCawangan'+i).hide();
                        $("#tcek" + i).hide();
                        $('#bank' + i).attr("disabled", "true");
                        $('#caw' + i).attr("disabled", "true");
                        $('#rujukan' + i).attr("disabled", "true");
                        $('#trkh' + i).attr("disabled", "true");
                        $('#amaun' + i).attr("disabled", "true");
                        $('#amaun' + i).val("0");
                        $('#akaunpembayar' + i).hide();
                                $('#v' + i).hide();
                    }
                });
            </script>
            <script type="text/javascript">
                $("#amaun0").val(<fmt:formatNumber value="${actionBean.jumlahCaj}" pattern="0.00"/>);
                $("#jumCaraBayar").val(<fmt:formatNumber value="${actionBean.jumlahCaj}" pattern="0.00"/>);
            </script>
            <script type="text/javascript">
                function change(row) {
                    var curr = document.getElementById('amaun' + row);
                    var ax = document.getElementById('senaraiCaraBayaran' + row);
                    for (var i = 0; i < (row + 1); i++) {
                        var a = document.getElementById('senaraiCaraBayaran' + i);
                        if (a.value != '0') {
                            $('#bank' + i).removeAttr("disabled");
                            $('#caw' + i).removeAttr("disabled");
                            $('#rujukan' + i).removeAttr("disabled");
                            $('#trkh' + i).removeAttr("disabled");
                            $('#amaun' + i).removeAttr("disabled");
                            if (a.value == 'T') {
                                $('#field' + i).hide();
                                $('#tcek' + i).hide();
                                $('#tCawangan'+i).hide();
                                $('#bank' + i).hide();
                                $("#caw" + i).hide();
                                $("#rujukan" + i).hide();
                                $("#trkh" + i).hide();
                                $('#akaunpembayar' + i).hide();
                                $('#v' + i).hide();
                                var today = new Date();
                                $("#trkh" + i).val(today.getDate() + "/" + (today.getMonth() + 1) + "/" + today.getYear());
                            } else if ((a.value == 'KW') || (a.value == 'WP')) {
                                $('#bank' + i).val("PMB");
                                $('#bank' + i).attr("disabled", "true");
                                $('#bank' + i).show();
                                $('#caw' + i).show();
                                $('#rujukan' + i).show();
                                $('#akaunpembayar' + i).hide();
                                $('#v' + i).hide();
                                $('#trkh' + i).show();
                                $('#field' + i).show();
                                $('#tcek' + i).show();
                                if (a.value == '0') {
                                    $('#a' + i).hide();
                                }
                            } else if ((a.value == 'C') || (a.value == 'CT') || (a.value == 'CL') || (a.value == 'CT') || (a.value == 'CS')) {
                                 $('#bank' + i).removeAttr("disabled");
                                $('#bank' + i).show();
                                $('#caw' + i).show();
                                $('#tCawangan'+i).show();
                                $('#rujukan' + i).show();
                                $('#trkh' + i).show();
                                $('#field' + i).show();
                                $('#tcek' + i).show();
                                $('#akaunpembayar' + i).show();
                                $('#v' + i).show();
                                if (a.value == '0') {
                                    $('#field' + i).hide();
                                }
                            } else {
                                $('#bank' + i).removeAttr("disabled");
                                $('#bank' + i).show();
                                $('#caw' + i).show();
                                $('#tCawangan'+i).show();
                                $('#rujukan' + i).show();
                                $('#trkh' + i).show();
                                $('#field' + i).show();
                                $('#tcek' + i).show();
                                $('#akaunpembayar' + i).hide();
                                $('#v' + i).hide();
                                if (a.value == '0') {
                                    $('#field' + i).hide();
                                }
                            }
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
                        for (var j = i + 1; j < 5; j++) {
                            var c = document.getElementById('senaraiCaraBayaran' + j);
                            if (c.value != '0') {
                                if ((a.value == 'T') && (c.value == 'T')) {
                                    alert("Hanya satu mod Bayaran Tunai sahaja dibenarkan.");
                                    return c.value = '0';
                                }
                            }
                        }
                        sequencePayment2(row);
                    }
                    autoBalance3(row, ax, curr);
                }

                function sequencePayment2(row) {
                    if ((row - 1) > 0) {
                        var x = document.getElementById('senaraiCaraBayaran' + (row - 1));
                        var y = document.getElementById('senaraiCaraBayaran' + (row));
                        if ((x.value == '0') && (y.value != '0')) {
                            alert("Sila masukkan Mod Bayaran mengikut turutan.");
                            return y.value = '0';
                        }
                    }
                }

                function autoBalance3(row, ax, curr) {
                    if (row != 0) {
                        if (ax.value != '0') {
                            var t = document.getElementById('jumCaraBayar');
                            var bal = (parseInt(t.value) + parseInt(curr.value)).toFixed(2);
                            $("#jumCaraBayar").val(bal);
                        }
                    }
                }

                function updateTotal(inputTxt, row) {
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
                    updtTot();
                    var yy = row - 1;
                    if (yy >= 0) {
                        var t = document.getElementById('jumCaraBayar');
                        var bal = (parseInt(${actionBean.jumlahCaj}) - parseInt(t.value)).toFixed(2);
                        if (bal > 0)
                            $('#amaun' + (row + 1)).val(bal);
                        else {
                            $('#amaun' + (row + 1)).val('0');
                            updtTot();
                        }
                    } else {
                        var t = document.getElementById('jumCaraBayar');
                        var bal = (parseInt(${actionBean.jumlahCaj}) - parseInt(t.value)).toFixed(2);
                        if (bal > 0) {
                            $('#amaun' + (row + 1)).val(bal);
                        } else {
                            $('#amaun' + (row + 1)).val('0');
                            updtTot();
                        }
                    }
                <%--updtTot();--%>
                }

                function updtTot() {
                    var total = 0;
                    for (var i = 0; i < 5; i++) {
                        var a = document.getElementById('amaun' + i)
                        total += parseFloat(a.value);
                    }
                    var t = document.getElementById('jumCaraBayar');
                    t.value = total.toFixed(2);
                }
function numberValidation(inputTxt,row){
    	var a = document.getElementById('akaunpembayar' + row)
            if ((isNaN(a.value))||((a.value) =="")){
                alert("Nombor tidak sah");
                inputTxt.value = '';
                return;
            }
        
    }
                function validate() {
                    var u = parseFloat($('#total').val());
                    var t = parseFloat($('#jumCaraBayar').val());
                    if (u > t) {
                        var bal = u - t;
                        alert("Bayaran anda kurang RM " + (bal).toFixed(2));
                        return false;
                    }
                    for (var i = 0; i < 5; i++) {
                        var a = document.getElementById('senaraiCaraBayaran' + i);
                        var c = $('#rujukan' + i).val();
                        var d = $('#trkh' + i).val();
                        var bank = $('#bank' + i).val();
                        var caw = $('#caw' + i).val();
                        var akaunP = $('#akaunpembayar'+i).val();
                        if ((a.value != '0') && (a.value != 'T')) {
                            if(caw == "" &&((a.value !='KK')&&(a.value !='DK')&&(a.value !='WP')&&(a.value !='KW')&&(a.value !='AM'))){
                                alert("Sila Masukkan Cawangan.");
                                $('#caw' + i).focus();
                                return false;
                            }
                            if (c == "") {
                                alert("Sila Masukkan Nombor Rujukan.");
                                $('#rujukan' + i).focus();
                                return false;
                            }
                            if (d == "") {
                                alert("Sila Masukkan Tarikh.");
                                $('#trkh' + i).focus();
                                return false;
                            }
                            if (bank == "0") {
                                alert("Sila Masukkan Bank / Agensi Pembayaran.");
                                $('#bank' + i).focus();
                                return false;
                            }
                            
                            if (t > u) {
                                var bal = t - u;
                                alert("Bayaran anda lebih RM " + (bal).toFixed(2));
                                return false;
                            }
                            if(a.value=='C'||a.value=='CT'||a.value=='CS'||a.value=='CL'){
                    if(akaunP == ""){
                    alert("Sila Masukkan No Akaun Pembayar.");
                    $('#akaunpembayar'+i).focus();
                    return false;
                }
               }
                        }
                    }
                    return true;
                }
            </script>

    </body>
</html>