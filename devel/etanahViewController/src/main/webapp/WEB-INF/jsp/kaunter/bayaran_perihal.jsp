Jumlah Perlu Dibayar (RM):<%--
    Document   : bayaran_perihal
    Created on : Sep 28, 2010, 11:35:34 AM
    Author     : User
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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<script type="text/javascript" language="javascript" >
    
    $(document).ready(function(){
        
        
        //        var c = document.getElementById("semua");
        //        c.checked = true;
        //        for (var i = 1; i < 50; i++){
        //            var c = document.getElementById("idKos" + i);
        //            c.checked = true;
        //            if (c == null) break;
        //        }
        //        
        
       
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

    function changeFormat2(objId){
        objId = '#' + objId;
        var val = $(objId).val();
        if (isNaN( parseFloat(val))) $(objId).val("0.00");
        else $(objId).val(parseFloat(val).toFixed(2));

        // update total
        var pelepasan = 0;
        if ($('#pelepasan').val() != "" && !isNaN($('#pelepasan').val())) pelepasan = -1 * parseFloat($('#pelepasan').val());
        var total = ${actionBean.jumlahCaj} + pelepasan ;
        for (var i=0; i<1; i++){
            $('#amaun'+i).val(total.toFixed(2));
            $('#jumCaraBayar').val(total.toFixed(2));
        }
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
<script type="text/javascript" language="javascript" >
    $(document).ready(function() {
        $('form').submit(function(){
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
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
    
    function change(row){
        var curr = document.getElementById('amaun' + row);
        var ax = document.getElementById('senaraiCaraBayaran'+row);
        for (var i = 0; i < (row+1); i++){
            var a = document.getElementById('senaraiCaraBayaran'+row);
            if(a.value != '0'){
                $('#bank'+row).removeAttr("disabled");
                $('#caw'+row).removeAttr("disabled");
                $('#rujukan'+row).removeAttr("disabled");
                $('#amaun'+row).removeAttr("disabled");
                if (a.value == 'T'){
                    $('#field'+row).hide();
                    $('#bank'+row).hide();
                    $('#tcek'+row).hide();
                    $("#caw"+row).hide();
                    $("#rujukan"+row).hide();
                }
                else if((a.value == 'KW')||(a.value == 'WP')){
                    $('#bank'+row).val("PMB");
                    $('#bank'+row).attr("disabled", "true");
                    $('#bank'+row).show();
                    $('#caw'+row).show();
                    $('#rujukan'+row).show();
                    $('#field'+row).show();
                    $('#tcek'+row).show();
                    if(a.value == '0'){
                        $('#a'+i).hide();
                    }
                }
                else{
                    $('#bank'+row).removeAttr("disabled");
                    $('#bank'+row).show();
                    $('#caw'+row).show();
                    $('#rujukan'+i).show();
                    $('#tcek'+row).show();
                    $('#field'+row).show();
                    if(a.value == '0'){
                        $('#field'+row).hide();
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
                for(var j = 0; j < row; j++){
                    var c = document.getElementById('senaraiCaraBayaran'+j);
                    if(c.value != '0'){
                        if((a.value == 'T')&&(c.value == 'T')){
                            alert("Hanya satu mod Bayaran Tunai sahaja dibenarkan.");
                            return a.value = '0';
                        }
                    }
                }
                sequencePayment2(row);
            }
            autoBalance3(row, ax, curr);
        }
</script>
<script type="text/javascript" language="javascript" >
    $(document).ready(function() {
        $("form").bind("keypress", function(e) { // Disable keyboard <enter> key 
            if (e.keyCode == 13) {
                return false;
            }
        });
    });
</script>

<script type="text/javascript" language="javascript" >
    $("#amaun0").val(<fmt:formatNumber value="${actionBean.jumlahCaj}" pattern="0.00"/>);
    $("#jumCaraBayar").val(<fmt:formatNumber value="${actionBean.jumlahCaj}" pattern="0.00"/>);
    
</script>
<script type="text/javascript" language="javascript" >


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
                var s = document.getElementById('JumlahPerluBayar');
                if(${rayuan}){
                    var bal = 0.00;
                }else{
                    var bal = (parseInt(${actionBean.jumlahCaj}) - parseInt(t.value)).toFixed(2);  
                }          
                if(bal > 0)
                    $('#amaun' + (row+1)).val(bal);
            }else{
                var t = document.getElementById('jumCaraBayar');
                var s = document.getElementById('JumlahPerluBayar');
                 
                if(${rayuan}){
                    var bal = 0.00;
                }else{
                    var bal = (parseInt(${actionBean.jumlahCaj}) - parseInt(t.value)).toFixed(2);  
                }                       
                
                if(bal > 0)
                    $('#amaun' + (row+1)).val(bal);
            }
        }
        
        function updateBayaranRayuan(ttl,asl){
            var total = ttl-asl;
            var a = document.getElementById('caj12107');           
            var per = asl/3;
            if ((isNaN(a.value))||((a.value) =="")){
                alert("Nombor tidak sah");
                $("#caj12107").val(asl);
                $("#jumCaraBayar").val(ttl.toFixed(2));
                $("#JumlahPerluBayar").html(ttl.toFixed(2));
                $("#amaun0").val(ttl.toFixed(2));
            }
            else {
                if(per > a.value){
                    alert("Bayaran Premiun harus lebih besar atau 1/3 dari bayaran asal");
                    $("#caj12107").val(asl); 
                    $("#jumCaraBayar").val(ttl.toFixed(2));
                    $("#JumlahPerluBayar").html(ttl.toFixed(2));
                    $("#amaun0").val(ttl.toFixed(2));
                }
                else {
                    total += parseFloat(a.value);                
                    $("#jumCaraBayar").val(total.toFixed(2));
                    $("#amaun0").val(total.toFixed(2));
                    $("#JumlahPerluBayar").html(total.toFixed(2));               
                }
         
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

<s:messages />
<s:errors />
<s:form beanclass="etanah.view.kaunter.BayaranPerihalActionBean">

    <s:hidden name="selectedItem" id="selectedItem" />

    <fieldset class="aras1">
        <legend>Terima Bayaran  </legend></br>
        <p class=instr><em>*</em>Pastikan maklumat di bawah adalah betul dan masukkan butir-butir pembayaran. PERINGATAN: Tidak dibenarkan menggunakan cara pembayaran yang lain
            bersama dengan Cek dan hanya satu Cek dibenarkan.</p></br>
            <%--<display:table name="${actionBean.senaraiTransaksi}" id="row1" class="tablecloth" style="width:95%;" >--%>
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

            <c:if test="${!rayuan}">
                <display:column property="amaun" title="Caj (RM)" style="text-align:right;width:100px;" format="{0,number, 0.00}" />
            </c:if>

            <c:if test="${rayuan && row1.kodTransaksi ne '12107' }">
                <display:column title="Caj (RM)" style="text-align:right;width:100px;"><fmt:formatNumber value="${row1.amaun}" pattern="0.00"/></display:column>
            </c:if>

            <c:if test="${rayuan && row1.kodTransaksi eq '12107' && !semak3bln }">
                <display:column title="Caj (RM)" style="text-align:right;width:100px;"><fmt:formatNumber value="${row1.amaun}" pattern="0.00"/></display:column>
            </c:if>

            <c:if test="${rayuan && row1.kodTransaksi eq '12107' && semak3bln }">
                <display:column title="Caj (RM)" style="text-align:right;width:100px;">
                    <s:text name="caj${row.kodTransaksi}" size="12" class="number" onblur="javascript:updateBayaranRayuan(${actionBean.jumlahCaj},${row1.amaun});" id="caj${row1.kodTransaksi}" value="${row1.amaun}"/>   
                </display:column>
            </c:if>


            <display:footer>
                <tr>
                    <td colspan="4" align="left">Jumlah Perlu Dibayar (RM):</td>
                    <td>
                        <div align="right" id="JumlahPerluBayar"><fmt:formatNumber value="${actionBean.jumlahCaj}" pattern="0.00"/></div>

                    </td>
                </tr>
                <tr>
                    <td colspan="4" align="left"><b>(-) Pelepasan / Pengecualian</b><br>
                        &nbsp;&nbsp;&nbsp;&nbsp;No resit  : <br>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:text name="urusan.sebabKecuali" size="50" id="kecuali" onkeyup="this.value=this.value.toUpperCase();"/><br>
                        <%--&nbsp;&nbsp;&nbsp;&nbsp;Pegawai Meluluskan : <br>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:text name="urusan.pegawaiKecuali" size="50" id="pegKecuali" onkeyup="this.value=this.value.toUpperCase();"/></td>--%>
                    <td><br><div align="right">
                            <s:text name="urusan.cajPelepasan"  size="10" style="text-align:right"
                                    id="pelepasan" onblur="changeFormat2('pelepasan')" />
                        </div></td>
                </tr>
            </display:footer>
        </display:table>
    </fieldset>
    <%-- <c:if test="${actionBean.jumlahCaj > 0}" >--%>
    </br>
    <p class=title>Cara Bayaran</p>
    <fieldset class="aras1">
        <display:table name="${actionBean.senaraiCaraBayaran}" id="row" style="width:100%;" class="tablecloth">

            <display:column title="Bil">
                ${row_rowNum}
            </display:column>

            <display:column title="Cara Bayaran" >
                <s:select name="senaraiCaraBayaran[${row_rowNum - 1}].kodCaraBayaran.kod"
                          id="senaraiCaraBayaran${row_rowNum - 1}" onchange="change(${row_rowNum -1})">
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

            <c:if test="${!rayuan}">
                <%--id="amaun${row_rowNum - 1}"--%>
                <display:column title="Amaun (RM) " >
                    <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].amaun" size="12" class="number"
                            onblur="javascript:updateTotal(this,${row_rowNum - 1});" id="amaun${row_rowNum - 1}" />
                </display:column>
            </c:if>

            <c:if test="${rayuan}">
                <display:column title="Amaun (RM) " >
                    <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].amaun" size="12" class="number" onblur="javascript:updateTotal(this,${row_rowNum - 1});" id="amaun${row_rowNum - 1}" />
                </display:column>
            </c:if>

            <display:footer>
                <tr>
                    <td colspan="6" align="right">Jumlah (RM):</td>
                    <td><s:text name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="12"
                               class="number" readonly="readonly" style="background:transparent;border:0px;"/>
                        <%--<input name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="12"
                               class="number" readonly="readonly" style="background:transparent;border:0px;" />--%>
                    </td>
                <tr>
                </display:footer>

            </display:table >
    </fieldset>
    <%-- </c:if>--%>

    <p align="center">
        <%--<s:submit name="Kembali" value="Kembali" class="btn" />--%>
        <c:if test="${buttonsaverltb}">
            <c:if test="${semak3bln}">
                <s:submit name="save3bulan" value="Bayar" class="btn" onclick="" />  
            </c:if>

            <c:if test="${semak6bln}">
                <s:submit name="save6bulan" value="Bayar" class="btn" onclick="" />
                <s:submit name="lanjut12" value="Lanjut Bayaran" class="btn" onclick="" />  
            </c:if>

            <c:if test="${semak12bln}">
                <s:submit name="save12bulan" value="Bayar" class="btn" onclick="" />  
            </c:if>
        </c:if>

        <c:if test="${!buttonsaverltb}">
            <c:choose>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PMMK1'}" >
                    <c:if test="${!rayuanBayaran}">
                        <s:submit name="save" value="Bayar" class="btn" onclick="" />  
                        <s:submit name="rayuanBayaran" value="Rayuan Bayaran" class="btn" onclick=""/> 
                    </c:if>
                    <c:if test="${rayuanBayaran}">
                        <s:submit name="saveRayuan" value="Bayar" class="btn" onclick=""/> 
                        <s:submit name="back" value="Kembali" class="btn" onclick=""/> 
                    </c:if>
                </c:when>
                <c:otherwise>
                    <s:submit name="save" value="Bayar" class="btn" onclick="" />  
                </c:otherwise>
            </c:choose>
        </c:if>

        <%--<stripes:submit name="save" value="Simpan" class="btn" onclick="return checkJumlah();" />--%>

    </p>                  <!-- HIDDEN SUBMITS -->
    <s:submit name="removeUrusan" id="removeUrusan" value="removeUrusan" class="btn" style="display:none;"/>
    <s:submit name="editUrusan" id="editUrusan" value="editUrusan" class="btn" style="display:none;"/>
    <!-- HIDDEN -->
</p>
</s:form>
