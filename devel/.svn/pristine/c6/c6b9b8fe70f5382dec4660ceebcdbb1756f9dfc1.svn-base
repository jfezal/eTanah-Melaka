<%--
    Document   : bayaran_pelbagai
    Created on : Apr 5, 2010, 5:51:34 PM
    Author     : w.fairul
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloths.css"/>

<script language="javascript" >
    $(document).ready(function() {
        // set the first default payment to Tunai
        $('#senaraiCaraBayaran0').val('T');
        $('#xx').val('');
        // focus on the first payment
        $('#amaun0').val((${actionBean.jumlahCaj}).toFixed(2));
        $('#jumCaraBayar').val((${actionBean.jumlahCaj}).toFixed(2));
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
          

        function stopRKey(evt) {
            var evt = (evt) ? evt : ((event) ? event : null);
            var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
            if ((evt.keyCode == 13) && (node.type=="text")) {return false;}
        }

        document.onkeypress = stopRKey;
    });
</script>
<script type="text/javascript">
   
    function updateTotals (inputTxt,row){
        var total = 0;
      
        var a = document.getElementById('amaun' + row)
        if ((isNaN(a.value))||((a.value) =="")){
            alert("Nombor tidak sah");
            $('#amaun' + row).val("0.00");
            return;
        }
        total += parseFloat(a.value);

        var t = document.getElementById('jumCaraBayar');
        t.value = total.toFixed(2);
        inputTxt.value = parseFloat(inputTxt.value).toFixed(2);
        updateTot();
        var yy = row - 1;
        if(yy>=0){
            var t = document.getElementById('jumCaraBayar');
            var bal = parseInt(${actionBean.jumlah}) - parseInt(t.value);
            if(bal > 0)
                $('#amaun' + (row+1)).val(bal);
        }else{
            var t = document.getElementById('jumCaraBayar');
            var bal = parseInt(${actionBean.jumlah}) - parseInt(a.value);
            if(bal > 0)
                $('#amaun' + (row+1)).val(bal);
        }
    }
   
    function updateTot(){
        var total = 0;
        for (var i=0; i<5; i++){
            var a = document.getElementById('amaun' + i)
            total += parseFloat(a.value);
        }
        var t = document.getElementById('jumCaraBayar');
        t.value = total.toFixed(2);
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

    function edit(id){
        window.open("${pageContext.request.contextPath}/hasil/kutipan_hasil?showEditPemohon&idHakmilik="+id, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=900,height=300");
    }

    function remove(id){
        alert("Hapus ID Hakmilik : "+id+"?");
        var url = "${pageContext.request.contextPath}/hasil/kutipan_hasil?delete&idHakmilik="+id;
        $.get(url,
        function(data){
            $('#ak').html(data);
        },'html');
    }

    function autoBalance(row, ax, curr){
        if(row != 0){
            if(ax.value != '0'){
                var t = document.getElementById('jumCaraBayar');
                var bal = (parseInt(t.value) + parseInt(curr.value)).toFixed(2);
                $("#jumCaraBayar").val(bal);
            }
        }
    }

    function change(row){
        var curr = document.getElementById('amaun' + row);
       
        var ax = document.getElementById('senaraiCaraBayaran'+row);

        for (var i = 0; i < (row+1); i++){
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
            }else{
                $("#a"+i).hide();
                $('#bank'+i).attr("disabled", "true");
                $('#caw'+i).attr("disabled", "true");
                $('#rujukan'+i).attr("disabled", "true");
                $('#trkh'+i).attr("disabled", "true");
                $('#amaun'+i).attr("disabled", "true");
                $('#amaun'+i).val("0");
                updateTotal();
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
            sequencePayment(row);
        }
        autoBalance(row, ax, curr);
    }
    function sequencePayment(row){
        if((row-1)>0){
            var x = document.getElementById('senaraiCaraBayaran'+(row-1));
            var y = document.getElementById('senaraiCaraBayaran'+(row));
            if((x.value == '0')&&(y.value != '0')){
                alert("Sila masukkan Mod Bayaran mengikut turutan.");
                return y.value = '0';
            }
        }
    }
    

    function validateByr(){
        var t = parseFloat($('#jumCaraBayar').val());
        var u = parseFloat(${actionBean.jumlahCaj});
        if(u > t){
            var bal = u -t;
            alert("Bayaran anda kurang RM "+(bal).toFixed(2));
            return false;
        }
        if(t > u){
            var bal = t - u;
            var x = document.getElementById('xx');
            if(x.value == ''){
                window.open("${pageContext.request.contextPath}/hasil/kutipan_hasil?chooseIDHakmilik&balance="+bal, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=900,height=600");
                return false;
            }
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
    }

    function addRows(akaun){
        $("#xx").val(akaun);
        document.getElementById("byr1").click();
    }

    function reset1(){
        $('#senaraiCaraBayaran0').val('T');
        $('#amaun0').val((${actionBean.jumlahCaj}).toFixed(2));
        $('#jumCaraBayar').val((${actionBean.jumlahCaj}).toFixed(2));
        $("#bank0").hide();
        $("#caw0").hide();
        $("#rujukan0").hide();
        $("#trkh0").hide();
        $("#a0").hide();
       
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

    function clearText1(id) {
        $("#"+id+" input:text").each( function(el) {
            $(this).val('');
        });

        $("#" + id +" select").each( function(el) {
            $(this).val('0');
        });
        reset1();
    }

    function validatePembayar(){
        var name = document.getElementById('nama');
        if(name.value == ""){
            alert("Sila Masukkan Nama Pembayar");
            $("#nama").focus();
            return false;
        }
        else{
            var asal = ${actionBean.jumlahCaj};
            var bayar = document.getElementById('jumCaraBayar');
            if(asal > bayar.value){
                alert("Amaun yang dibayar kurang.");
                return false;
            }
        }
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form  name="form1" beanclass="etanah.view.strata.BayaranUpahUkurActionBean">
    <c:if test="${fn:length(actionBean.kos) > 0}">
        <fieldset class="aras1">

            <legend>Maklumat Bayaran</legend>
            <br>
            <display:table class="tablecloth" name="${actionBean.kos}" cellpadding="0" cellspacing="0" id="line">
                <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                <display:column property="item" title="Jenis Bayaran" />
                <display:column property="amaunTuntutan" title="Jumlah Bayaran (RM)"  />
                <display:footer>
                    <tr>
                        <td colspan="2"><div align="right"><b>Jumlah (RM):</b></div></td>
                        <td>${actionBean.jumlahCaj}</td>
                    </tr>
                </display:footer>
            </display:table>

            <br>
        </fieldset>
    </c:if>

    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Kemasukan Maklumat Bayaran</legend>
            <p>
                <label>Nama Pembayar : </label>
                <s:text name="dokumenKewangan.isuKepada" id="nama" size="40"/><em>*</em>
            </p>
            <p>
                <label>Nombor Rujukan : </label>
                <s:text name="dokumenKewangan.noRujukan" size="40"/><em>*</em>
            </p>
            <br>
        </fieldset>

    </div>
    <br>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Cara Bayaran</legend>
            <p class=instr><em><font color="black">Masukkan butir-butir pembayaran.<br>&nbsp;
                        <font color="red">PERINGATAN:</font> Tidak dibenarkan menggunakan cara pembayaran yang lain bersama dengan pembayaran menggunakan Cek ,
                        Deraf Bank dan Wang dalam Pindahan.</font></em>
            </p>
            &nbsp;&nbsp;&nbsp;&nbsp;<s:errors field="amaun"/>
            <div align="center">
                <table>
                    <tr>
                        <td>
                            <display:table name="${actionBean.senaraiCaraBayaran}" id="row" class="tablecloths">
                                <display:column title="Bil."><div align="center">${row_rowNum}.</div></display:column>
                                <display:column title="Cara Bayaran" class="tunai">
                                    <s:select name="senaraiCaraBayaran[${row_rowNum - 1}].kodCaraBayaran.kod"
                                              id="senaraiCaraBayaran${row_rowNum - 1}" onchange="javaScript:change(${row_rowNum -1})">
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
                                    <s:text name="tarikhCek[${row_rowNum-1}]" id="trkh${row_rowNum - 1}" size="20" readonly="true" maxlength="10" class="datepicker"/>
                                </display:column>

                                <display:column title="Amaun (RM)">
                                    <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].amaun" size="12" class="number"
                                            onblur="javascript:updateTotals(this,${row_rowNum - 1});" id="amaun${row_rowNum - 1}"/>
                                </display:column>
                               
                        <display:footer>
                            <tr>
                                <td colspan="6"><div align="right"><b>Jumlah (RM):</b></div></td>
                                <td><input name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="12" class="number" readonly="true"/></td>
                            </tr>
                            <tr> <td colspan="7"><div align="right">

                                        <s:submit name="bayar" value="Bayar" onclick="return validatePembayar();" class="btn" id="byr"/>
                                    </div></td>
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

</s:form>
