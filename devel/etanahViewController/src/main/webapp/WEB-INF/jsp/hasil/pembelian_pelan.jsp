<%--
    Document   : pembelian_pelan
    Created on : Mar 15, 2010, 4:34:42 PM
    Author     : abdul.hakim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloths.css"/>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<table width="100%" bgcolor="green">
    <tr><td width="100%" height="20" ><div style="background-color: green;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Pembelian Pelan</font>
    </div></td></tr>
</table>

<s:form beanclass="etanah.view.stripes.hasil.PembelianPelanActionBean" id="beli_pelan">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Bayaran</legend>
            <div class="content" align="center">
                <display:table class="tablecloths" name="${actionBean.senaraiTransaksi}" id="line">
                    <display:column title="<input type='checkbox' name='semua' onclick='javascript:selectAll(this);' />">
                        <div align="center"><s:checkbox name="chkbox[${line_rowNum - 1}]"
                                    value="${line.kod}" id="kandunganFolder${line_rowNum - 1}" onclick="test(${line_rowNum - 1});"/></div>
                    </display:column>
                    <display:column  title="Urusan" style="width:800;">
                        ${line.kod} - ${line.nama}
                    </display:column>
                    <display:column  title="Kuantiti">
                        <em id="note${line_rowNum - 1}">*</em><s:text name="bil[${line_rowNum - 1}]" id="bil${line_rowNum - 1}" size="6" onblur="test1(${line_rowNum - 1});" maxlength="3" style="text-align:right;"/>
                    </display:column>
                    <%--<display:column property="caj" title="Jumlah (RM)" format="{0,number, 0.00}" style="text-align:right;">--%>
                    <display:column title="Jumlah Perlu Dibayar (RM)" format="{0,number, 0.00}" style="width:100;">
                        <div align="center"><s:text name="total" id="total${line_rowNum - 1}" size="12" readonly="true" style="text-align:right;"/></div>
                    </display:column>
                    <display:footer>
                        <tr>
                            <td colspan="3" align="right"><div align="right"><b>Jumlah Perlu dibayar (RM) : </b></div></td>
                            <td class="number" align="right"><div align="center"><s:text name="totalBayaran" value="0.00" id="jumlah" size="12"
                               class="number" readonly="readonly" style="background:transparent;border:0 px;" /></div></td>
                        </tr>
                    </display:footer>
                </display:table>
            </div>
        </fieldset>

        <fieldset class="aras1">
            <legend>Maklumat Pembeli</legend>
            <p>
            <p>
                <label><em>*</em>Nama Pembeli:</label>
                <s:text name="dokumenKewangan.isuKepada" size="60" id="nama" onblur="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
            <p>
            <%--<p>
                <label><em>*</em>Kuantiti :</label>
                <s:text name="bil" size="4" maxlength="2" onblur="calculate(this.value)" id="bil"/>
            </p>
            <p>
                <label>Jumlah yang perlu dibayar (RM):</label>
                <s:text name="total"  readonly="true" size="6" id="total" style="text-align:right"/>
                <s:text name="total"  readonly="true" size="6" id="total"/>
            </p>--%>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Cara Bayaran</legend>
            <p class=instr><em><font color="black">Masukkan butir-butir pembayaran.<br>&nbsp;
                        <font color="red">PERINGATAN:</font> Tidak dibenarkan menggunakan cara pembayaran yang lain bersama dengan pembayaran menggunakan Cek ,
                        Deraf Bank dan Wang dalam Pindahan.</font></em>
            </p>
            &nbsp;&nbsp;&nbsp;&nbsp;<s:errors field="amaun"/>
            <div align="center">
                <display:table name="${actionBean.senaraiCaraBayaran}" id="row" class="tablecloths">
                    <display:column title="Bil."><div align="center">${row_rowNum}.</div></display:column>
                    <display:column title="Cara Bayaran" class="tunai">
                        <s:select name="senaraiCaraBayaran[${row_rowNum - 1}].kodCaraBayaran.kod"
                                  id="senaraiCaraBayaran${row_rowNum - 1}" onchange="javaScript:change(this)">
                            <s:option value="0" label="Pilih ..." />
                            <s:options-collection collection="${listUtil.senaraiKodCaraBayaran}"  label="nama" value="kod" sort="nama"/>
                        </s:select>
                    </display:column>

                    <display:column title="Bank / Agensi Pembayaran" >
                        <s:select name="senaraiCaraBayaran[${row_rowNum - 1}].bank.kod" id="bank${row_rowNum - 1}">
                            <s:option label="Pilih..." value="0" />
                            <s:options-collection collection="${listUtil.senaraiKodBank}"  label="nama" value="kod" />
                        </s:select>
                    </display:column>

                    <display:column title="Cawangan" >
                        <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].bankCawangan" id="caw${row_rowNum - 1}" size="20" />
                    </display:column>

                    <display:column title="No. Rujukan" >
                        <em id="a${row_rowNum - 1}">*</em><s:text name="senaraiCaraBayaran[${row_rowNum - 1}].noRujukan" id="rujukan${row_rowNum - 1}" size="20" />
                    </display:column>

                    <display:column title="Tarikh">
                        <s:text name=" " id="trkh${row_rowNum - 1}" size="20" readonly="true" maxlength="10" class="datepicker"/>
                    </display:column>

                    <display:column title="Amaun (RM)">
                        <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].amaun" size="12" class="number"
                                onblur="javascript:updateTotal(this);" id="amaun${row_rowNum - 1}"/>
                    </display:column>
                    <display:footer>
                        <tr>
                            <td colspan="6"><div align="right"><b>Jumlah (RM):</b></div></td>
                            <td><input name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="12" class="number" readonly="true"/></td>
                        </tr>
                    </display:footer>
                </display:table >
                <%--<s:submit name="addCaraBayar" value="Tambah" class="btn"/>--%>
            </div>
            <br>
        </fieldset>
    </div>
    <table border="0" bgcolor="green" width="100%">
        <tr>
            <td align="right">
                <s:submit name="save" value="Bayar" class="btn" onclick="return validate(this.form)"/>
                <s:submit name="main" value="Isi Semula" class="btn" />
                <%--<s:button name=" " value="Isi Semula" class="btn" onclick="clearText1('beli_pelan');"/>--%>
            </td>
        </tr>
    </table>
</s:form>
<script language="javascript" >
    $(document).ready(function() {
        $('#senaraiCaraBayaran0').val('T');
        $("#jumCaraBayar").val("0.00");
        $("#jumlah").val("0.00");
        $('#amaun0').val("0");
        $("#bank0").hide();
        $("#caw0").hide();
        $("#rujukan0").hide();
        $("#trkh0").hide();
        $("#a0").hide();
        for (var i = 1; i < 5; i++){
            $("#a"+i).hide();
            $('#bank'+i).attr("disabled", "true");
            $('#caw'+i).attr("disabled", "true");
            $('#rujukan'+i).attr("disabled", "true");
            $('#trkh'+i).attr("disabled", "true");
            $('#amaun'+i).attr("disabled", "true");
            $('#amaun'+i).val("0");
        }
        for (var i = 0; i < 2; i++){
            $('#bil'+i).attr("disabled", "true");
            $('#bil'+i).val("0");
            $("#note"+i).hide();
        }
    });
</script>
<script type="text/javascript">
    function calculate (t, i ,c){
        var total = 0;
        var b = document.getElementById('bil'+i);
        if ((isNaN(b.value))||((b.value) =="")){
            alert("Nombor tidak sah");
            b.value = RemoveNonNumeric(b);
            $("#total").val("0.00");
            return;
        }
        if(c == "73151"){
            if((b.value == '1')){
                total = parseFloat(b.value)*parseFloat(t);
            }else if((b.value > '1')){
                var bal = parseInt(b.value) - 1;
                total = (bal*3)+ parseInt(t);
            }
        }else{
            total = parseFloat(b.value)*parseFloat(t);
        }
        $("#total"+i).val(total.toFixed(2));
    }

    function updateTotal (inputTxt){
        var total = 0;
        for (var i = 0; i <5; i++){
    	var a = document.getElementById('amaun' + i)
            if ((isNaN(a.value))||((a.value) =="")){
                alert("Nombor tidak sah");
                inputTxt.value = RemoveNonNumeric(a);
                $('#jumCaraBayar').val("0.00");
                return;
            }
            total += parseFloat(a.value);
        }
        var t = document.getElementById('jumCaraBayar');
        t.value = total.toFixed(2);
        inputTxt.value = parseFloat(inputTxt.value).toFixed(2);
    }

    function RemoveNonNumeric( strString ){
        var strValidCharacters = "1234567890.";
        var strReturn = "0";
        var strBuffer = "0";
        var intIndex = 0;
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

    function clearText1(id) {
        $("#"+id+" input:text").each( function(el) {
            $(this).val('');
        });

        $("#" + id +" select").each( function(el) {
            $(this).val('');
        });
        reset1();
    }

    function reset1(){
        $('#senaraiCaraBayaran0').val('T');
        $("#jumCaraBayar").val("0.00");
        $("#bank0").hide();
        $("#caw0").hide();
        $("#rujukan0").hide();
        $("#trkh0").hide();
        $("#a0").hide();
        $('#amaun0').val("0");
        for (var i = 1; i < 5; i++){
            $('#bank'+i).show();
            $('#caw'+i).show();
            $('#rujukan'+i).show();
            $('#trkh'+i).show();
            $("#a"+i).hide();
            $('#bank'+i).attr("disabled", "true");
            $('#caw'+i).attr("disabled", "true");
            $('#rujukan'+i).attr("disabled", "true");
            $('#trkh'+i).attr("disabled", "true");
            $('#amaun'+i).attr("disabled", "true");
            $('#amaun'+i).val("0");
        }
    }

    function change(){
        for (var i = 0; i < 5; i++){
            var a = document.getElementById('senaraiCaraBayaran'+i);
            if(a.value != '0'){
                    $('#bank'+i).removeAttr("disabled");
                    $('#caw'+i).removeAttr("disabled");
                    $('#rujukan'+i).removeAttr("disabled");
                    $('#trkh'+i).removeAttr("disabled");
                    $('#amaun'+i).removeAttr("disabled");
                if (a.value == 'T'){
                    $('#a'+i).hide();
                    $('#bank'+i).hide();
                    $("#caw"+i).hide();
                    $("#rujukan"+i).hide();
                    $("#trkh"+i).hide();
                }
                 else if((a.value == 'KW')||(a.value == 'WP')){
                    $('#bank'+i).val("PMB");
                    $('#bank'+i).attr("disabled", "true");
                    $('#bank'+i).show();
                    $('#caw'+i).show();
                    $('#rujukan'+i).show();
                    $('#trkh'+i).show();
                    $('#a'+i).show();
                    if(a.value == '0'){
                        $('#a'+i).hide();
                    }
                }
                else{
                    $('#bank'+i).removeAttr("disabled");
                    $('#bank'+i).show();
                    $('#caw'+i).show();
                    $('#rujukan'+i).show();
                    $('#trkh'+i).show();
                    $('#a'+i).show();
                    if(a.value == '0'){
                        $('#a'+i).hide();
                    }
                }
            }

            var b = document.getElementById('senaraiCaraBayaran'+(i+1));
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
        }
    }

    function validate(){
        var t = parseFloat($('#jumCaraBayar').val());
        var u = parseFloat($('#jumlah').val());
        var flg = false;

        for (var i = 0; i < 2; i++){
            var c = document.getElementById("kandunganFolder" + i);
            if (c == null) break;
            if(c.checked){
                flg = true;
                var bil = parseFloat($('#bil'+i).val());
                if(bil == "0"){
                    alert("Sila masukkan 'Kuantiti'");
                    $('#bil'+i).focus();
                    return false;
                }
            }
        }
        if(flg == false){
            alert("Sila pilih Urusan yang hendak dibuat.");
            return false;
        }
        var nama = document.getElementById('nama');
        if(u > t){
            var bal = u -t;
            alert("Bayaran anda kurang RM "+(bal).toFixed(2));
            return false;
        }
        if((nama.value)==""){
            alert("Sila masukkan 'Nama Pembeli'");
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

    function selectAll(a){
        for (var i = 0; i < 2; i++){
            var c = document.getElementById("kandunganFolder" + i);
            if (c == null) break;
            c.checked = a.checked;
            test(i);
        }
    }

    function test(row){
        var tot = 0;
        <%--for (var i = 0; i < 2; i++){--%>
            var c = document.getElementById("kandunganFolder" + row);
            <%--if (c == null) break;--%>
            if(c.checked){
                $('#bil'+row).removeAttr("disabled");
                $("#note"+row).show();
                $('#bil'+row).val("1");
                if(c.value == "73151"){
                    <%--$('#total'+i).val("5.00");--%>
                    var t = 5;
                    calculate(t, row, c.value);
                }else{
                    <%--$('#total'+i).val("6.00");--%>
                    var t = 6;
                    calculate(t, row, c.value);
                }
            }
            if(!c.checked){
                $('#bil'+row).val("0");
                $('#total'+row).val("0");
                $("#note"+row).hide();
                $('#bil'+row).attr("disabled", "true");
            }
        for (var i = 0; i < 2; i++){
            var m = document.getElementById('total' + i);
            tot += parseFloat(m.value);
            $("#jumlah").val(tot.toFixed(2));
        }
        $("#jumCaraBayar").val(tot.toFixed(2));
        $("#amaun0").val(tot.toFixed(2));
    }

    function test1(row){
        var tot = 0;
        <%--for (var i = 0; i < 2; i++){--%>
            var c = document.getElementById("kandunganFolder" + row);
            <%--if (c == null) break;--%>
            if(c.checked){
                $('#bil'+row).removeAttr("disabled");
                $("#note"+row).show();
                if(c.value == "73151"){
                    var t = 5;
                    calculate(t, row, c.value);
                }else{
                    var t = 6;
                    calculate(t, row, c.value);
                }
            }
            if(!c.checked){
                $('#bil'+row).val("0");
                $('#total'+row).val("0");
                $("#note"+row).hide();
                $('#bil'+row).attr("disabled", "true");
            }
        for (var i = 0; i < 2; i++){
            var m = document.getElementById('total' + i);
            tot += parseFloat(m.value);
            $("#jumlah").val(tot.toFixed(2));
        }
        $("#jumCaraBayar").val(tot.toFixed(2));
        $("#amaun0").val(tot.toFixed(2));
    }
</script>