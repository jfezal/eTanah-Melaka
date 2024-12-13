<%--
    Document   : bayaran_perihal
    Created on : Sep 28, 2010, 11:35:34 AM
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
    $(document).ready(function(){
        var c = document.getElementById("semua");
        c.checked = true;
        for (var i = 1; i < 50; i++){
            var c = document.getElementById("idKos" + i);
            c.checked = true;
            if (c == null) break;
        }
    });

    function changeFormat3(row){
        var num = document.getElementById('kadarbayar'+row).value;
        var jpnum = document.getElementById('jumlahPetak'+row).value;
        num = num.toString().replace(/\$|\,/g,'');
        if (isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100+0.50000000001);
        cents = num % 100;
        num = Math.floor(num / 100).toString();
        if(cents < 10)
            cents = "0" + cents;
        var totalamn = num * jpnum;
        totalamn = totalamn.toString().replace(/\$|\,/g,'');
        for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
            num = num.substring(0, num.length - (4 * i + 3))+','+
            num.substring(num.length-(4 * i + 3));
        for (var j = 0; j < Math.floor((totalamn.length - (1 + j)) / 3); j++)
            totalamn = totalamn.substring(0, totalamn.length - (4 * j + 3))+','+
            totalamn.substring(totalamn.length-(4 * j + 3));
        $('#amaun'+row).val((((sign)?'':'-') + totalamn  + '.' + cents))
        $('#kadarbayar'+row).val((((sign)?'':'-') + num + '.' + cents));
        test();
    }
    
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
    
    function isNumberKey(evt)
    {
        var charCode = (evt.which) ? evt.which : event.keyCode
        if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

        return true;
    }
    
    function validation(f,b,c){
        var url = '${pageContext.request.contextPath}/kaunter/strata/BayaranPerihalN9?simpanNoPetak';
        $.post(url,$("#form").serialize(),
        function(data){
            $('#page_div').html(data);
        },'html');
 
    }
    
            function validation2(){
            var noPetakProv = document.getElementById("noPetakProv").value;
           
            if(noPetakProv==0){
                alert("Sila masukkan jumlah petak");
                return false;
            }
                    
            return true;
        }
        
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }
        
    function removeNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
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



<s:form beanclass="etanah.view.kaunter.BayaranPerihalStrataN9ActionBean" name="form" id="form">
    <s:hidden name="selectedItem" id="selectedItem" />

    <c:if test="${actionBean.stageId eq 'terimabayaranprov'}">   
        <fieldset class="aras2"> 
            <legend>Maklumat Petak bagi Blok Sementara : </legend>

            <div class="instr-fieldset">
                <font color="green">Arahan: Sila masukkan Jumlah Petak bagi Blok Sementara dan klik butang 'Simpan' </font>
            </div>
            <p align="left">
                <br/><b>Jumlah Petak bagi Blok Sementara :</b>
                <%-- <s:text name="noPelan1" id="noPelan1" onkeyup="validateNumber2(this,this.value);"/>--%>
                <%--<s:text name="noPetakProv" id="noPetakProv" maxlength="6" onkeyup="validateNumber(this,this.value);" onblur="changeFormat3('bilPetak')"></s:text>--%>               
                <s:text name="noPetakProv" id="noPetakProv" maxlength="6" onkeypress="return isNumberKey(event)" onblur="changeFormat3('bilPetak')"></s:text>               

                </p>               
                <p>
                   
                <s:button name="simpanNoPetak" id="simpan"  value="Simpan" class="btn" onclick= "if(validation2()==true){doSubmit(this.form, this.name, 'page_div');}"/>               
                    <%--<s:button name="simpanNoPetak" id="simpan"  value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>--%>
                &nbsp;<%--s:button name="reset" id="reset" value="Isi Semula" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/></p> --%>
            <p></p>
        </fieldset> 
    </c:if>
    <p></p><p></p><p></p>
    <fieldset class="aras1">
        <legend>Terima Bayaran </legend></br>
        <p class=instr><em>*</em>
            <c:if test="${actionBean.stageId eq 'terimabayaranprov'}">
                Pastikan maklumat di bawah adalah betul dan masukkan butir-butir pembayaran. PERINGATAN: Tidak dibenarkan menggunakan cara pembayaran yang lain
                bersama dengan Cek dan hanya satu Cek dibenarkan.
            </c:if>
            <c:if test="${actionBean.stageId ne 'terimabayaranprov'}">
                Pastikan maklumat di bawah adalah betul dan masukkan butir-butir pembayaran. PERINGATAN: Tidak dibenarkan menggunakan cara pembayaran yang lain
                bersama dengan Cek dan hanya satu Cek dibenarkan.
            </c:if>
        </p></br>
        <%--<display:table name="${actionBean.senaraiTransaksi}" id="row1" class="tablecloth" style="width:95%;" >--%>
        <display:table name="${actionBean.listBayaran}" id="row1" class="tablecloth" style="width:100%;">

            <display:column title="Bil" style="width:5%;">
                ${row1_rowNum}
            </display:column>
            <%--<display:column title="<input type='checkbox' id='semua' name='semua' onclick='javascript:selectAll(this);' />">
                <stripes:checkbox id="idKos${row1_rowNum}" name="idKos${row1_rowNum}"
                                  value="${row1.idKos},${row1.amaun}"
                                  onclick="javascript:toggleAmount(this);"/>
            </display:column>--%>
            <%--            <display:column title="Urusan" group="1" >
                            <b>${row1.kodUrusan}</b><div id="controls${row1.kodUrusan}"/>
                        </display:column>--%>
            <display:column title="Urusan" group="1" >
                <b>${row1.permohonan.kodUrusan.nama}</b><div id="controls${row1.permohonan}"/>
            </display:column>
            <display:column title="Perihal Bayaran" >
                ${row1.namaUrusan}
            </display:column>

            <%--<c:if test="${actionBean.stageId eq 'terimabayaranprov'}">--%>              
            <%--<display:column title="Kuantiti" style="width:150px;text-align:center;" >--%>
            <%--<s:text name="noPetakProv" id="noPetakProv" maxlength="6" onkeypress="return isNumberKey(event)" onblur="changeFormat3('bilPetak')" ></s:text>--%>
            <%--</display:column>--%>
            <%--</c:if>--%>

            <c:if test="${actionBean.stageId ne 'terimabayaranprov2'}">              
                <display:column title="Kuantiti" style="width:100px;text-align:right;" >
                    ${row1.kuantiti}
                </display:column>
            </c:if>

            <%--<c:if test="${actionBean.stageId eq 'terimabayaranprov'}">--%>     
            <%--<display:column title="Caj (RM)" style="width:150px;text-align:center;" >--%>
            <%--<s:text name="jumlahCajProv" id="jumlahCajProv" readonly="true" onkeypress="return isNumberKey(event)" onblur="changeFormat3('bilPetak')" formatPattern="#,##0.00"></s:text>--%>
            <%--</display:column>--%>
            <%--</c:if>--%>

            <c:if test="${actionBean.stageId ne 'terimabayaranprov2'}">     
                <display:column property="amaun" title="Caj (RM)" style="text-align:right;width:100px;" format="{0,number, 0.00}" />
            </c:if>

            <display:footer>
                <tr>
                    <td colspan="4" align="left">Jumlah Perlu Dibayar (RM):</td>
                    <td>
                        <div align="right"><fmt:formatNumber value="${actionBean.jumlahCaj}" pattern="0.00"/></div>
                        <%--<stripes:text name="jumlahCaj" class="number" formatPattern="0.00"
                                              readonly="readonly" style="background:transparent;border:1 px;" id="total"/>--%>
                    </td>
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
                <tr>
                    <td colspan="6" align="right">Jumlah (RM):</td>
                    <td><input name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="12"
                               class="number" readonly="readonly" style="background:transparent;border:0px;" /></td>
                <tr>
                </display:footer>

            </display:table >
    </fieldset>
    <%-- </c:if>--%>

    <p align="center">
        <%--<s:submit name="Kembali" value="Kembali" class="btn" />--%>

        <c:if test="${actionBean.stageId eq 'bayaran5F'}">
            <s:submit name="save" value="Bayar" class="btn" onclick="" />
        </c:if>

        <c:if test="${actionBean.stageId eq 'terimabayaran'}">
            <c:if test="${actionBean.isBayaranVisible eq 'true'}">
                <s:submit name="save" value="Bayar" class="btn" onclick="" />
            </c:if>
            <c:if test="${actionBean.isBayaranVisible ne 'true' && actionBean.permohonRBHS == null }">
                <%--<s:submit name="suratPeringatan" value="Surat Peringatan" class="longbtn" onclick="" />--%>
            <center><p><em>*</em><font color="red">Maaf. Urusan ini tidak dapat diteruskan kerana pemohon tidak membuat bayaran dalam tempoh masa yang ditetapkan.</font></p></center> 
        </c:if>
        <c:if test="${actionBean.isBayaranVisible ne 'true' && actionBean.permohonRBHS != null }">
            <center><p><em>*</em><font color="red">Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Perlanjutan Tempoh Bayaran Kelulusan Permohonan Hakmilik Strata.</font></p></center>
        </c:if>
    </c:if>


    <c:if test="${actionBean.stageId eq 'terimabayaran2'}">
        <%--Jika belum tamat tempoh 30 hari --%>
        <c:if test="${actionBean.bayaran2Visible eq 'true' && actionBean.bayaran3Visible eq 'true'}">
            <s:submit name="save" value="Bayar" class="btn" onclick="" />
        </c:if>

        <c:if test="${actionBean.bayaran2Visible eq 'true' && actionBean.permohonRBHS == null}">
            <s:submit name="save" value="Bayar" class="btn" onclick="" />
        </c:if>


        <%--Jika RBHS belum selesai OK --%>
        <c:if test="${actionBean.bayaran2Visible ne 'true' && actionBean.permohonRBHS != null}">
            <center><p><em>*</em><font color="red">Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Perlanjutan Tempoh Bayaran Kelulusan Permohonan Hakmilik Strata.</font></p></center>
        </c:if> 
    </c:if>

    <c:if test="${actionBean.stageId eq 'terimabayaranprov'}">
        <c:if test="${actionBean.found eq 'true' }">
            <s:submit name="save" value="Bayar" class="btn" onclick="" />
        </c:if>

    </c:if>

    <%--Jika RBHS selesai dan tamat tempoh --%>
    <c:if test="${actionBean.bayaran2Visible eq 'true' && actionBean.permohonRBHS != null && actionBean.bayaran3Visible ne 'true' }">
        <center><p><em>*</em><font color="red">${actionBean.comment}</font></p></center>
        <%--s:submit name="suratBatal" value="Surat Pembatalan" class="longbtn" onclick="" /--%>
    </c:if>

    <%--Jika tidak buat RBHS OK --%>
    <c:if test="${actionBean.bayaran2Visible ne 'true' && actionBean.permohonRBHS == null}">
        <c:if test="${actionBean.kodNegeri eq '04' && actionBean.stageId eq 'terimabayaran2'}">
            <center><p><em>*</em><font color="red">Maaf. ID permohonan ini telah dibatalkan kerana melebihi tempoh masa yang telah ditetapkan.</font></p></center>
        </c:if>
        <c:if test="${actionBean.kodNegeri eq '05' && actionBean.stageId eq 'terimabayaran2'}">
            <center><p><em>*</em><font color="red">${actionBean.comment}</font></p></center>
            <%--s:submit name="suratBatal" value="Surat Pembatalan" class="longbtn" onclick="" />
            <%-- ida comment update on 6/6/2013
               <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PBBSS'}">
               <center><p><em>*</em><font color="red">Arahan: Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: <b>"Penguatkuasaan Seksyen 14A Akta Hakmilik Strata 1985" </b><br> IDPermohonan: <b>${actionBean.p14Amohon} </b>, IDPengguna: <b>${actionBean.userid}</b></font></p></center>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBBSS'}">
               <center><p><em>*</em><font color="red">Arahan: Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: <b>"Penguatkuasaan Seksyen 22B Akta Hakmilik Strata 1985" </b><br> IDPermohonan: <b>${actionBean.p14Amohon} </b>, IDPengguna: <b>${actionBean.userid}</b></font></p></center>
                </c:if>
            --%>
        </c:if>
        <%--<stripes:submit name="save" value="Simpan" class="btn" onclick="return checkJumlah();" />--%>


    </p>                  <!-- HIDDEN SUBMITS -->
    <s:submit name="removeUrusan" id="removeUrusan" value="removeUrusan" class="btn" style="display:none;"/>
    <s:submit name="editUrusan" id="editUrusan" value="editUrusan" class="btn" style="display:none;"/>
    <!-- HIDDEN -->
</p>




</c:if>
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