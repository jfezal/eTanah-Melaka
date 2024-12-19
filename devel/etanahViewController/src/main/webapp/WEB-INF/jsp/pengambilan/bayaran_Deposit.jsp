<%-- 
    Document   : bayaran_Deposit
    Created on : 16-Apr-2012, 15:15:47
    Author     : nordiyana
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>

<s:form beanclass="etanah.view.pengambilan.BayaranDepositActionBean">

    <html>
        <head>
            <c:set var="kembali" value="Kembali"/>
            <c:set var="selesai" value="Step8"/>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />
            <title>Bayaran Deposit</title>

        </head>
        <body>
            <script type="text/javascript" language="javascript" >
                $(document).ready(function(){
                    var c = document.getElementById("semua");
                    c.checked = true;
                    for (var i = 1; i < 50; i++){
                        var c = document.getElementById("idKos" + i);
                        c.checked = true;
                        if (c == null) break;
                    }
                });

                function selectAll(a){
                    var tot = 0;
                    if (a.checked){
                        $('#jumCaraBayar').val(${actionBean.jumlahCaj});
                        $('#amaun0').val(${actionBean.jumlahCaj});
                    }else{
                        $('#jumCaraBayar').val(0);
                        $('#amaun0').val(0);
                    }
                    for (var i = 1; i < 50; i++){
                        var c = document.getElementById("idKos" + i);
                        c.checked = a.checked;
                        if (c == null) break;
                    }
                }

                function toggleAmount(selectInput){
                    var tot = 0;
                    var s = document.getElementById("semua");
                    if (s.checked) s.checked = false;
                    for (var i = 1; i < 50; i++){
                        var c = document.getElementById("idKos" + i);
                        if (c.checked) {
                            var arrAmaun = c.value.split(',');
                            tot = tot + parseInt(arrAmaun[1]);
                        }
                        $('#jumCaraBayar').val(tot);
                        $('#amaun0').val(tot);
                        if (c == null) break;
                    }
                }

                function checkJumlah(){
                    if ( $('#jumCaraBayar').val()==0){
                        alert('Sila pilih perihal bayaran');
                        return false;
                    }
                }

                function RemoveNonNumeric( strString ){
                    var strValidCharacters = "1234567890.";
                    var strReturn = "0.00";
                    var strBuffer = "0";
                    var intIndex = 0.00;
                    // Loop through the string
                    for( intIndex = 0; intIndex < strString.length; intIndex++ )
                    {
                        strBuffer = strString.substr( intIndex, 1 );
                        // Is this a number
                        if( strValidCharacters.indexOf( strBuffer ) > -1 )
                        {
                            strReturn += strBuffer;
                        }
                    }
                    return strReturn;
                }

                function remove(item){
                    if (confirm('Adakah anda pasti untuk menghapuskan urusan ini?')){
                        $('#selectedItem').val(item);
                        $('#removeUrusan').click();
                    }
                }

                function edit(item){
                    $('#selectedItem').val(item);
                    var submitBtn  = document.getElementById('editUrusan');
                    submitBtn.click();
                }

            </script>

            <p class=title>Terima Bayaran</p>

            <stripes:messages />
            <stripes:errors />

            <p class=instr>Pastikan maklumat di bawah adalah betul dan masukkan butir-butir pembayaran. PERINGATAN: Tidak dibenarkan menggunakan cara pembayaran yang lain
                bersama dengan Cek dan hanya satu Cek dibenarkan.</p>

            <stripes:form action="/pengambilan/Deposit" >

                <stripes:hidden name="selectedItem" id="selectedItem" />

                <fieldset class="aras1">

                    <%--<display:table name="${actionBean.senaraiTransaksi}" id="row1" class="tablecloth" style="width:95%;" >--%>
                    <display:table name="${actionBean.listBayaran}" id="row1" class="tablecloth" style="width:100%;">

                        <display:column title="Bil" style="width:5%;">
                            ${row1_rowNum}
                        </display:column>
                        <c:if test="${fn:length(actionBean.listMohonTuntut) eq 0}">
                            <display:column title="<input type='checkbox' id='semua' name='semua' onclick='javascript:selectAll(this);' />">
                                <stripes:checkbox id="idKos${row1_rowNum}" name="idKos${row1_rowNum}"
                                                  value="${row1.idKos},${row1.amaun}"
                                                  onclick="javascript:toggleAmount(this);"/>
                            </display:column>
                        </c:if>
                        <display:column title="Urusan" group="1" >
                            <b>${row1.kodUrusan}</b><div id="controls${row1.kodUrusan}"/>
                        </display:column>
                        <display:column title="Perihal Bayaran" >
                            ${row1.namaUrusan}
                        </display:column>
                        <display:column title="Kuantiti" style="width:100px;text-align:right;" >
                            ${row1.kuantiti}
                        </display:column>
                        <display:column property="amaun" title="Caj (RM)" style="text-align:right;width:100px;" format="{0,number, 0.00}" />

                        <display:footer>
                            <tr>
                                <c:if test="${fn:length(actionBean.listMohonTuntut) eq 0}"><td colspan="5" align="left">Jumlah Perlu Dibayar (RM):</td></c:if>
                                <c:if test="${fn:length(actionBean.listMohonTuntut) ne 0}"><td colspan="4" align="left">Jumlah Perlu Dibayar (RM):</td></c:if>
                                <td>
                                    <div align="right"><fmt:formatNumber value="${actionBean.jumlahCaj}" pattern="0.00"/></div>
                                    <%--<stripes:text name="jumlahCaj" class="number" formatPattern="0.00"
                                                          readonly="readonly" style="background:transparent;border:1 px;" id="total"/>--%>
                                </td>
                            </tr>
                        </display:footer>

                    </display:table>
                </fieldset>
                <script type="text/javascript" language="javascript" >

                    // replace inline the control buttons for above table
                    $(document).ready(function() {
                        for (i = 0; i < 10; i++){
                            if ($('#controls' + i) == null) break;
                            $('#controls' + i).html('<img alt="Klik Untuk Hapus" border="0"                 ' +
                                'src="${pageContext.request.contextPath}/images/not_ok.gif" class="rem"' +
                                'id="rem' + i + '" onclick="remove(' + i + ')">' +
                                '<img alt="Klik Untuk Kemaskini" b                order="0" ' +
                                'src="${pageContext.request.contextPath}/images/edit.gif" class="edit"' +
                                'id="edit' + i + '" onclick="edit(' + i + ')">');
                        }
                    });
                </script>

                <%-- <c:if test="${actionBean.jumlahCaj > 0}" >--%>

                <p class=title>Cara Bayaran</p>
                <fieldset class="aras1">
                    <display:table name="${actionBean.senaraiCaraBayaran}" id="row" style="width:100%;" class="tablecloth">

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
                            <stripes:text name="senaraiCaraBayaran[${row_rowNum - 1}].bankCawangan" size="20" id="caw${row_rowNum - 1}"/>
                        </display:column>

                        <display:column title="No. Rujukan" >
                            <em id="field${row_rowNum - 1}">*</em><stripes:text name="senaraiCaraBayaran[${row_rowNum - 1}].noRujukan" size="20" id="rujukan${row_rowNum - 1}"/>
                        </display:column>

                        <display:column title="Amaun (RM)" >
                            <stripes:text name="senaraiCaraBayaran[${row_rowNum - 1}].amaun" size="12" class="number"
                                          onblur="javascript:updateTotal(this,${row_rowNum - 1});" id="amaun${row_rowNum - 1}" />
                        </display:column>

                        <display:footer>
                            <tr>
                                <td colspan="5" align="right">Jumlah (RM):</td>
                                <td><input name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="12"
                                           class="number" readonly="readonly" style="background:transparent;border:0px;" /></td>
                            <tr>
                            </display:footer>

                        </display:table >
                </fieldset>
                <%-- </c:if>--%>

                <%--  <p align="center">
                      <stripes:submit name="save" value="Bayar" class="btn" onclick="" />
                      <stripes:submit name="save" value="Simpan" class="btn" onclick="return checkJumlah();" />

                </p>  --%>
                <br>
                <div align="right" style="background-color:${actionBean.warnaModul}">
                    <c:if test="${fn:length(actionBean.listMohonTuntut) ne 0}">
                        <stripes:submit name="save" value="Bayar" class="disablebtn" onclick="" disabled="true"/>
                    </c:if>
                    <c:if test="${fn:length(actionBean.listMohonTuntut) eq 0}">
                        <stripes:submit name="save" value="Bayar" class="btn" onclick="" />
                    </c:if>

                </div>
                <!-- HIDDEN SUBMITS -->
                <stripes:submit name="removeUrusan" id="removeUrusan" value="removeUrusan" class="btn" style="display:none;"/>
                <stripes:submit name="editUrusan" id="editUrusan" value="editUrusan" class="btn" style="display:none;"/>
                <!-- HIDDEN -->



            </stripes:form>

            <script type="text/javascript" language="javascript" >
                $(document).ready(function() {
                    // set the first default payment to Tunai
                    $('#senaraiCaraBayaran0').val('T');
                    $('#amaun0').val((${actionBean.jumlahCaj}).toFixed(2));
                    $('#jumCaraBayar').val((${actionBean.jumlahCaj}).toFixed(2));
                    // focus on the first payment
                    $('#amaun0').focus();
                    $("#bank0").hide();
                    $("#caw0").hide();
                    $("#rujukan0").hide();
                    $("#field0").hide();
                    for (var i = 1; i < 5; i++){
                        $("#field"+i).hide();
                        $('#bank'+i).attr("disabled", "true");
                        $('#caw'+i).attr("disabled", "true");
                        $('#rujukan'+i).attr("disabled", "true");
                        $('#amaun'+i).attr("disabled", "true");
                        $('#amaun'+i).val("0");
                    }
                });
            </script>
            <script type="text/javascript">
                $("#amaun0").val(<fmt:formatNumber value="${actionBean.jumlahCaj}" pattern="0.00"/>);
                $("#jumCaraBayar").val(<fmt:formatNumber value="${actionBean.jumlahCaj}" pattern="0.00"/>);
            </script>
            <script type="text/javascript">
                function change(row){
                    var curr = document.getElementById('amaun' + row);
                    var ax = document.getElementById('senaraiCaraBayaran'+row);
                    for (var i = 0; i < (row+1); i++){
                        var a = document.getElementById('senaraiCaraBayaran'+i);
                        if(a.value != '0'){
                            $('#bank'+i).removeAttr("disabled");
                            $('#caw'+i).removeAttr("disabled");
                            $('#rujukan'+i).removeAttr("disabled");
                            $('#amaun'+i).removeAttr("disabled");
                            if (a.value == 'T'){
                                $('#field'+i).hide();
                                $('#bank'+i).hide();
                                $("#caw"+i).hide();
                                $("#rujukan"+i).hide();
                            }
                            else if((a.value == 'KW')||(a.value == 'WP')){
                                $('#bank'+i).val("PMB");
                                $('#bank'+i).attr("disabled", "true");
                                $('#bank'+i).show();
                                $('#caw'+i).show();
                                $('#rujukan'+i).show();
                                $('#field'+i).show();
                                if(a.value == '0'){
                                    $('#a'+i).hide();
                                }
                            }
                            else{
                                $('#bank'+i).removeAttr("disabled");
                                $('#bank'+i).show();
                                $('#caw'+i).show();
                                $('#rujukan'+i).show();
                                $('#field'+i).show();
                                if(a.value == '0'){
                                    $('#field'+i).hide();
                                }
                            }
                        }
                        for(var j = i+1; j < 5; j++){
                            var c = document.getElementById('senaraiCaraBayaran'+j);
                            if(c.value != '0'){
                                if((a.value == 'T')&&(c.value == 'T')){
                                    alert("Hanya satu mod Bayaran Tunai sahaja dibenarkan.");
                                    return c.value = '0';
                                }
                            }
                        }
                        sequencePayment2(row);
                    }
                    autoBalance3(row, ax, curr);
                }

                function sequencePayment2(row){
                    if((row-1)>0){
                        var x = document.getElementById('senaraiCaraBayaran'+(row-1));
                        var y = document.getElementById('senaraiCaraBayaran'+(row));
                        if((x.value == '0')&&(y.value != '0')){
                            alert("Sila masukkan Mod Bayaran mengikut turutan.");
                            return y.value = '0';
                        }
                    }
                }

                function autoBalance3(row, ax, curr){
                    if(row != 0){
                        if(ax.value != '0'){
                            var t = document.getElementById('jumCaraBayar');
                            var bal = (parseInt(t.value) + parseInt(curr.value)).toFixed(2);
                            $("#jumCaraBayar").val(bal);
                        }
                    }
                }

                function updateTotal (inputTxt,row){
                    var total = 0;
                <%--for (var i = 0; i <bil; i++){--%>
                        var a = document.getElementById('amaun' + row)
                        if ((isNaN(a.value))||((a.value) =="")){
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
                        if(yy>=0){
                            var t = document.getElementById('jumCaraBayar');
                            var bal = (parseInt(${actionBean.jumlahCaj}) - parseInt(t.value)).toFixed(2);
                            if(bal > 0)
                                $('#amaun' + (row+1)).val(bal);
                        }else{
                            var t = document.getElementById('jumCaraBayar');
                            var bal = (parseInt(${actionBean.jumlahCaj}) - parseInt(t.value)).toFixed(2);
                            if(bal > 0)
                                $('#amaun' + (row+1)).val(bal);
                        }
                    }

                    function updtTot(){
                        var total = 0;
                        for (var i=0; i<5; i++){
                            var a = document.getElementById('amaun' + i)
                            total += parseFloat(a.value);
                        }
                        var t = document.getElementById('jumCaraBayar');
                        t.value = total.toFixed(2);
                    }

                    function validate(){
                        var u = parseFloat($('#total').val());
                        var t = parseFloat($('#jumCaraBayar').val());
                        if(u > t){
                            var bal = u -t;
                            alert("Bayaran anda kurang RM "+(bal).toFixed(2));
                            return false;
                        }
                        for (var i = 0; i < 5; i++){
                            var a = document.getElementById('senaraiCaraBayaran'+i);
                            var c = $('#rujukan'+i).val();
                            if((a.value != '0')&&(a.value != 'T')){
                                if(c == ""){
                                    alert("Sila Masukkan Nombor Rujukan.");
                                    $('#rujukan'+i).focus();
                                    return false;
                                }
                            }
                        }
                        return true;
                    }
            </script>

        </body>
    </html>
</s:form>