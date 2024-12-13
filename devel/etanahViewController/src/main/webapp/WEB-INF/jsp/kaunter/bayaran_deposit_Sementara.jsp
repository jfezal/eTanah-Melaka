<%--
    Document   : bayaran_deposit_Sementara
    Created on : Nov 12, 2013, 11:35:34 AM
    Author     : murali
--%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>

<script type="text/javascript" language="javascript" > 

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
    
    function isNumberKey(evt)
    {
        var charCode = (evt.which) ? evt.which : event.keyCode
        if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;        
        return true;       
    }
    
</script>

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
<s:messages />
<s:errors />
<s:form beanclass="etanah.view.kaunter.BayaranDepositSementaraActionBean">
    <fieldset class="aras2"> 
        <legend>Maklumat Petak bagi Blok Sementara : </legend>

        <div class="instr-fieldset">
            <font color="green">Arahan: Sila masukkan Jumlah Petak bagi Blok Sementara dan klik butang 'Simpan' </font>
        </div>
        <p align="left">
            <br/><b>Jumlah Petak bagi Blok Sementara :</b>           
            <s:text name="noPetakProv" id="noPetakProv" maxlength="6" onkeypress="return isNumberKey(event)"></s:text>
            <input type="hidden" name="idPermohonan" value="${actionBean.permohonan.idPermohonan}">
            <s:submit name="simpanNoPetak" value="simpan" class="btn"/>
        </p>
    </fieldset> 
    <br/><br />
    <fieldset class="aras1">
        <legend>Terima Bayaran</legend></br>
        <p class=instr><em>*</em>Pastikan maklumat di bawah adalah betul dan masukkan butir-butir pembayaran. PERINGATAN: Tidak dibenarkan menggunakan cara pembayaran yang lain
            bersama dengan Cek dan hanya satu Cek dibenarkan.</p></br>  
            <display:table name="${actionBean.listBayaran}" id="row1" class="tablecloth" style="width:100%;">
                <display:column title="Bil" style="width:5%;">
                    ${row1_rowNum}
                </display:column>            
                <display:column title="Urusan" group="1" >
                <b>${row1.permohonan.kodUrusan.nama}</b><div id="controls${row1.permohonan}"/>
            </display:column>
            <display:column title="Perihal Bayaran" >
                ${row1.namaUrusan}
            </display:column>
            <display:column title="Kuantiti" style="width:100px;text-align:right;" >
                ${row1.kuantiti}
            </display:column> 
            <display:column property="amaun" title="Caj (RM)" style="text-align:right;width:100px;" format="{0,number, 0.00}" />
            <display:footer>
                <tr><td colspan="4" align="left">Jumlah Perlu Dibayar (RM):</td>
                    <td><div align="right"><fmt:formatNumber value="${actionBean.jumlahCaj}" pattern="0.00"/></div></td></tr>
                </display:footer>
            </display:table>
    </fieldset>
    </br>
    <p class=title>Cara Bayaran</p>
    <fieldset class="aras1">
        <display:table name="${actionBean.senaraiCaraBayaran}" id="row" style="width:100%;" class="tablecloth">
            <display:column title="Bil">
                ${row_rowNum}
            </display:column>
            <display:column title="Cara Bayaran" >
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
                <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].bankCawangan" size="20" id="caw${row_rowNum - 1}"/>
            </display:column>
            <display:column title="No. Rujukan" >
                <em id="field${row_rowNum - 1}">*</em><s:text name="senaraiCaraBayaran[${row_rowNum - 1}].noRujukan" size="20" id="rujukan${row_rowNum - 1}"/>
            </display:column>
            <display:column title="Tarikh">
                <em id="tcek${row_rowNum - 1}">*</em><s:text name="tarikhCek[${row_rowNum-1}]" id="trkh${row_rowNum - 1}" size="15" formatPattern="dd/MM/yyyy" class="datepicker"/>
            </display:column>
            <display:column title="Amaun (RM)" >
                <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].amaun" size="12" class="number"
                        onblur="javascript:updateTotal(this,${row_rowNum - 1});" id="amaun${row_rowNum - 1}" />
            </display:column> 
            <display:footer>
                <tr><td colspan="6" align="right">Jumlah (RM):</td>
                    <td><input name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="12"
                               class="number" readonly="readonly" style="background:transparent;border:0px;" /></td>
                <tr>
                </display:footer>
            </display:table >
    </fieldset>   
    <p align="center">
        <c:if test="${actionBean.found eq 'true' }">
            <s:submit name="save" value="Bayar" class="btn" onclick="" />
        </c:if>
    </p>
</p>
</s:form>
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
        $("#tcek0").hide();
        for (var i = 1; i < 5; i++){
            $("#field"+i).hide();
            $("#tcek"+i).hide();
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
                    $('#tcek'+i).hide();
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
                    $('#tcek'+i).show();
                    if(a.value == '0'){
                        $('#a'+i).hide();
                    }
                }
                else{
                    $('#bank'+i).removeAttr("disabled");
                    $('#bank'+i).show();
                    $('#caw'+i).show();
                    $('#rujukan'+i).show();
                    $('#tcek'+i).show();
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