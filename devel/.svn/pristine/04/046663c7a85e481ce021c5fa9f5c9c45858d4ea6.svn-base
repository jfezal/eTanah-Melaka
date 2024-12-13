<%-- 
    Document   : jualan_pelan
    Created on : May 19, 2010, 4:18:12 PM
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
<div id="ak">
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Pembelian Pelan</font>
                </div>
            </td>
        </tr>
    </table></div>
    <s:form beanclass="etanah.view.stripes.hasil.JualanPelanActionBean" id="jualan_pelan">
    <div class="subtitle">
        <%--<fieldset class="aras1">
            <legend>Maklumat Bayaran</legend>
            <legend>Urusan : 12110 - Bayaran Pelan</legend>
            <div class="content" align="center">
                <table class="tablecloth" align="center">
                    <tr>
                        <th><input type='checkbox' name='semua' onclick='javascript:selectAll(this);' /></th>
                        <th>Urusan</th>
                        <th>Kuantiti</th>
                        <th>Jumlah (RM)</th>
                    </tr>
                    <tr>
                        <td align="center"><s:checkbox name="chkbox[0]" value="0" id="kandunganFolder0" onclick="test('0');"/></td>
                        <td width="400">12110 - Bayaran Pelan Saiz A3</td>
                        <td><em id="note0">*</em><s:text name="bil[0]" id="bil0" size="6" onblur="test1('0');" maxlength="3" style="text-align:right;"/></td>
                        <td><s:text name="total0" id="total0" size="12" readonly="true" style="text-align:right;"/></td>
                    </tr>
                    <tr>
                        <td align="center"><s:checkbox name="chkbox[1]" value="1" id="kandunganFolder1" onclick="test('1');"/></td>
                        <td>12110 - Bayaran Pelan Saiz A4</td>
                        <td><em id="note1">*</em><s:text name="bil[1]" id="bil1" size="6" onblur="test1('1');" maxlength="3" style="text-align:right;"/></td>
                        <td><s:text name="total1" id="total1" size="12" readonly="true" style="text-align:right;"/></td>
                    </tr>
                    <tr>
                        <td colspan="3" align="right"><div align="right"><b>Jumlah Perlu dibayar (RM) : </b></div></td>
                        <td class="number" align="right"><div align="center"><s:text name="total" value="0.00" id="jumlah" size="12"
                                    class="number" readonly="readonly" style="background:transparent;border:0 px;" /></div></td>
                    </tr>
                </table>
            </div>
        </fieldset>--%>
        <p></p>
        <fieldset class="aras1">
            <legend>Maklumat Pembeli</legend>
            <p>
                <label><em>*</em>Nama Pembeli :</label>
                <s:text name="dokumenKewangan.isuKepada" size="60" id="nama" onblur="this.value=this.value.toUpperCase();"/>
            </p>
            <p></p>
        </fieldset>
            <p></p>
        <%--<fieldset class="aras1">
            <legend>Maklumat Pelan</legend>
            <p>
                <label>Negeri :</label>
                &nbsp;
            </p>
            <p>
                <label>Daerah :</label>
                &nbsp;
            </p>
            <p>
                <label>Bandar/Pekan/Mukim :</label>
                &nbsp;
            </p>
            <p>
                <label>Seksyen :</label>
                &nbsp;
            </p>
            <p>
                <label>No. Lot :</label>
                &nbsp;
            </p>
            <p>
                <label>No. PT :</label>
                &nbsp;
            </p>
            <p>
                <label>Lot-Lot Bersebelahan :</label>
                &nbsp;
            </p>
            <p>
                <label>&nbsp;</label>
                &nbsp;
            </p>
        </fieldset>--%>
        <fieldset class="aras1">
            <legend>Maklumat Pelan</legend>
            <div class="content" align="center">
                <display:table name="${actionBean.senaraiUrusanCMS}" id="row" class="tablecloth">
                    <display:column title="Bil.">${row_rowNum}.</display:column>
                    <display:column title="ID Transaksi" property="idTransaksi"/>
                    <display:column title="Daerah" property="daerah.nama"/>
                    <display:column title="Jenis Carian" property="jenisCarian"/>
                    <display:column title="No. Carian" property="noCarian"/>
                    <display:column title="Jumlah Lot Bersebelahan" style="width:70;">
                        <s:text name="${row.jumlahLotSebelah}" value="${row.jumlahLotSebelah}" id="lot${row_rowNum-1}" style="display:none"/>${row.jumlahLotSebelah}
                    </display:column>
                    <display:column title="Saiz Kertas/Kuantiti" style="text-align:center">
                        <s:checkbox name="" id="achkbox${row_rowNum-1}" value="0" onclick="test('${row_rowNum-1}','A3')"/>  A3 &nbsp;&nbsp; /&nbsp;&nbsp;&nbsp;
                        <s:text name="a3Saiz[${row_rowNum-1}]" id="aqty${row_rowNum-1}" size="4" maxlength="2" style="text-align:right" onblur="tryTest1('${row_rowNum-1}','A3')"/><br>
                        <s:checkbox name="" id="bchkbox${row_rowNum-1}" value="1" onclick="testing('${row_rowNum-1}','A4')"/>  A4 &nbsp;&nbsp; /&nbsp;&nbsp;&nbsp;
                        <s:text name="a4Saiz[${row_rowNum-1}]" id="bqty${row_rowNum-1}" size="4" maxlength="2" style="text-align:right" onblur="tryTest1('${row_rowNum-1}','A4')"/>
                    </display:column>
                    <display:column title="Jumlah (RM)" style="text-align:center"><s:text name="" id="total${row_rowNum-1}" size="6" readonly="true" style="text-align:right"/></display:column>
                    <display:column title="" style="text-align:center">
                        <a href="#" onclick="maklumatPelan('${row.noTransaksi}','${row.idTransaksi}');return false;">Butiran</a>
                    </display:column>
                    <%--<display:column title="Hapus">
                        <div align="center"><a href="#">
                                <img alt='Klik Untuk Hapus' src='${pageContext.request.contextPath}/images/not_ok.gif'
                                     onclick="removeRow('${row.gisKey}');" /></a>
                        </div>
                    </display:column>--%>
                    <display:footer>
                        <tr>
                            <td colspan="7"><div align="right"><b>Jumlah (RM):</b></div></td>
                            <td><input name="" value="0.00" id="sum" size="12" class="number" readonly="true"/></td>
                        </tr>
                    </display:footer>
                </display:table>
            </div>
        </fieldset>
    </div>
    <p></p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Cara Bayaran</legend>
            <p class=instr><em><font color="black">Masukkan butir-butir pembayaran.<br>&nbsp;
                        <font color="red">PERINGATAN:</font> Tidak dibenarkan menggunakan cara pembayaran yang lain bersama dengan pembayaran menggunakan Cek ,
                        Deraf Bank dan Wang dalam Pindahan.</font></em>
            </p>

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
                        <%--<s:text name=" " id="trkh${row_rowNum - 1}" size="20" readonly="true" maxlength="10" class="datepicker"/>--%>
                        <s:text name=" " id="trkh${row_rowNum - 1}" size="20" readonly="true" maxlength="10" class="datepicker"/>
                    </display:column>

                    <display:column title="Amaun (RM)">
                        <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].amaun" size="12" class="number"
                                 id="amaun${row_rowNum - 1}"/>
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
    <div align="center"><table style="width:99.2%" bgcolor="green">
            <tr>
                <td align="right">
                    <s:submit name="save" value="Bayar" class="btn" onclick="return validate(this.form)"/>
                    <s:submit name="main" value="Isi Semula" class="btn" />
                </td>
            </tr>
        </table></div>
    </s:form>
    </div>
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
        var x = ${actionBean.bilTransaksi};
        for (var i = 0; i < x; i++){
            $('#aqty'+i).attr("disabled", "true");
            $('#bqty'+i).attr("disabled", "true");
            $('#aqty'+i).val("0");
            $('#bqty'+i).val("0");
            $('#total'+i).val("0");
            $('#x'+i).val(false);
            $('#xx'+i).val(false);
        }
    });
</script>
<script type="text/javascript">
    function calculate1 (t, i ,c, saiz){
        var tot = 0;
        var total = document.getElementById('total'+i);
        if(saiz == 'A3'){
            var abc = document.getElementById('bchkbox'+i);
            var b = document.getElementById('aqty'+i);
        }
        if(saiz == 'A4'){
            var abc = document.getElementById('achkbox'+i);
            var b = document.getElementById('bqty'+i);
        }

        if ((isNaN(b.value))||((b.value) =="")){
            alert("Nombor tidak sah");
            b.value = RemoveNonNumeric(b);
            $("#total").val("0.00");
            return;
        }
        if((c == "73151")){
            if((b.value == '1')){
                total = parseFloat(b.value)*parseFloat(t);
            }else if((b.value > '1')){
                var bal = parseInt(b.value) - 1;
                total = (bal*3)+ parseInt(t);
            }
        }else{
            tot = parseFloat(total.value) + (parseFloat(b.value)*parseFloat(t));
        }
            <%--tot = tot + parseFloat(l.value);--%>
        <%--$("#total"+i).val(tot.toFixed(2));--%>
        tryTest1(i, saiz);
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

        for (var i = 0; i < ${actionBean.bilTransaksi}; i++){
            var c = document.getElementById("achkbox" + i);
            var d = document.getElementById("bchkbox" + i);
            if (c == null) break;
            if (d == null) break;
            if(c.checked){
                flg = true;
                var bil = parseFloat($('#aqty'+i).val());
                if(bil == "0"){
                    alert("Sila masukkan 'Kuantiti'");
                    $('#aqty'+i).focus();
                    return false;
                }
            }
            if(d.checked){
                flg = true;
                var bil = parseFloat($('#bqty'+i).val());
                if(bil == "0"){
                    alert("Sila masukkan 'Kuantiti'");
                    $('#bqty'+i).focus();
                    return false;
                }
            }
        }
        if(flg == false){
            alert("Sila pilih saiz kertas untuk dicetak.");
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

    function test(row, saiz){
        var tot = 0;
        if(saiz == 'A3')
            var c = document.getElementById("achkbox" + row);
        if(saiz == 'A4')
            var c = document.getElementById("bchkbox" + row);
        if(c.checked){
            $('#xx'+row).val(true);
            $('#aqty'+row).val("1");
            $('#aqty'+row).removeAttr("disabled");
            if(c.value == "0"){
                var t = 50;
                calculate1(t, row, c.value, saiz);
            }
        }
        if(!c.checked){
            $('#xx'+row).val(false);
                $('#aqty'+row).attr("disabled", "true");
                $('#aqty'+row).val("0");
                var a4 = document.getElementById('bqty'+row);
                $('#total'+row).val((parseFloat(a4.value)*30).toFixed(2));
                calculate1(t, row, c.value, saiz);
        }
        for (var i = 0; i < 2; i++){
            var m = document.getElementById('total' + i);
            tot += parseFloat(m.value);
            $("#jumlah").val(tot.toFixed(2));
        }
    }

    function testing(row, saiz){
        var tot = 0;
        var c = document.getElementById("bchkbox" + row);
        if(c.checked){
            $('#x'+row).val(true);
            $('#bqty'+row).val("1");
            $('#bqty'+row).removeAttr("disabled");
            if(c.value == "1"){
                var t = 30;
                calculate1(t, row, c.value, saiz);
            }
        }
        if(!c.checked){
            $('#x'+row).val(false);
                $('#bqty'+row).attr("disabled", "true");
                $('#bqty'+row).val("0");
                var a3 = document.getElementById('aqty'+row);
                $('#total'+row).val((parseFloat(a3.value)*50).toFixed(2));
                calculate1(t, row, c.value, saiz);
        }
        for (var i = 0; i < 2; i++){
            var m = document.getElementById('total' + i);
            tot += parseFloat(m.value);
            $("#jumlah").val(tot.toFixed(2));
        }
    }

    function tryTest1(row, saiz){
        var ttl = 0;
        var a3a = document.getElementById("aqty" + row);
        var a4a = document.getElementById("bqty" + row);

        var aaa = document.getElementById("achkbox" + row);
        var bbb = document.getElementById("bchkbox" + row);

        if ((isNaN(a3a.value))||((a3a.value) =="")){
            alert("Nombor tidak sah");
            a3a.value = RemoveNonNumeric(a3a);
            $("#total"+row).val("0.00");
            return;
        }
        if ((isNaN(a4a.value))||((a4a.value) =="")){
            alert("Nombor tidak sah");
            a4a.value = RemoveNonNumeric(a4a);
            $("#total"+row).val("0.00");
            return;
        }
        var lot = document.getElementById('lot'+row);
        var tot = (parseFloat(a3a.value)*50) + (parseFloat(a4a.value)*30) + (parseFloat(lot.value)*1) ;
        $('#total'+row).val(tot.toFixed(2));
        for (var i = 0; i < ${actionBean.bilTransaksi}; i++){
            var m = document.getElementById('total' + i);
            ttl = ttl + parseFloat(m.value);
            $("#jumlah").val(ttl.toFixed(2));
            $("#jumCaraBayar").val(ttl.toFixed(2));
            $("#amaun0").val(ttl.toFixed(2));
        }
        $("#sum").val(ttl.toFixed(2));
        if((!aaa.checked)&&(!bbb.checked)){
            ttl = 0;
            $("#total"+row).val("0.00");
            for(var i=0;i<${actionBean.bilTransaksi};i++){
                var m = document.getElementById('total' + i);
                ttl = ttl + parseFloat(m.value);
                $("#sum").val(ttl.toFixed(2));
                $("#jumlah").val(ttl.toFixed(2));
                $("#jumCaraBayar").val(ttl.toFixed(2));
                $("#amaun0").val(ttl.toFixed(2));
            }
        }
    }

    function maklumatPelan(noTrans, idTrans){
        window.open("${pageContext.request.contextPath}/hasil/jualan_pelan?showPlanInfo&idTrans="+idTrans+"&noTrans="+noTrans, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=900,height=350");
    }

    function removeRow(id){
        alert("Hapus ID Transaksi : "+id+"?");
        var url = "${pageContext.request.contextPath}/hasil/jualan_pelan?delete&idTr="+id;
        $.get(url,
        function(data){
            $('#ak').html(data);
        },'html');
    }
</script>