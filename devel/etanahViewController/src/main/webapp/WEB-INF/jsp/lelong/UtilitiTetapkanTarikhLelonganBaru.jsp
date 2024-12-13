<%-- 
    Document   : UtilitiTetapkanTarikhLelonganBaru
    Created on : Sep 28, 2010, 6:35:39 PM
    Author     : mazurahayati.yusop
--%>


<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript">
    function show_calendar(v){
        window.open("${pageContext.request.contextPath}/lelong/utiliti_lelong?showForm3&idPermohonan=" +v, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1200,height=1200,scrollbars=1");
    }
    
  
    function show_calendarAdat(){
        window.open("${pageContext.request.contextPath}/lelong/keputusan_perintah?showFormD", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1200,height=1200,scrollbars=1");
    }

    function validation() {
        if($("#datepicker").val() == ""){
            alert('Sila masukkan " Tarikh Lelongan " terlebih dahulu.');
            $("#datepicker").focus();
            return false;
        }
        if($("#jam").val() == "00"){
            alert('Sila Pilih " Jam "');
            $("#jam").focus();
            return false;
        }

        if($("#minit").val() == "null"){
            alert('Sila Pilih " Minit "');
            $("#minit").focus();
            return false;
        }
        if($("#ampm").val() == "0"){
            alert('Sila Pilih " Pagi/Petang "');
            $("#ampm").focus();
            return false;
        }
        if($("#tempat").val() == ""){
            alert('Sila masukkan " Tempat " terlebih dahulu.');
            $("#tempat").focus();
            return false;
        }
        if($("#tarikhWarta").val() == ""){
            alert('Sila masukkan " Tarikh Warta " terlebih dahulu.');
            $("#tarikhWarta").focus();
            return false;
        }
        if($("#amaunTung").val() == ""){
            alert('Sila masukkan " Jumlah Tunggakan " terlebih dahulu.');
            $("#amaunTung").focus();
            return false;
        }
        if($("#hargaRizab").val() == ""){
            alert('Sila masukkan " Harga Rizab " terlebih dahulu.');
            $("#hargaRizab").focus();
            return false;
        }
        if($("#deposit").val() == ""){
            alert('Sila masukkan " Deposit " terlebih dahulu.');
            $("#deposit${line_rowNum}").focus();
            return false;
        }
        return true;
    }

    function showReport2(frm,event){

        if(validation()){
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            var url = frm.action + '?' + event;
            var queryString = $(frm).formSerialize();
            $.ajax({
                type:"POST",
                url : url,
                dataType : 'html',
                data : queryString,
                error : function(xhr, ajaxOptions, thrownError) {
                    alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                    $.unblockUI();
                },
                success : function(data) {
                    if(data.indexOf('Laporan telah dijana')==0){
                        alert(data);
                        $('#folder').click();
                    }else {
                        alert(data);
                        $('#urusan').click();
                    }
                    $.unblockUI();
                }
            });
        }
    }

    function calc(oh,value){
        var deposit = value * 0.1;
        var baki = value - deposit;

        $("#deposit"+oh).val(deposit);
        $("#baki"+oh).val(baki);

        var num = document.getElementById('deposit'+oh).value;

        num = num.toString().replace(/\$|\,/g,'');
        if (isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100+0.50000000001);
        cents = num % 100;
        num = Math.floor(num / 100).toString();
        if(cents < 10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
            num = num.substring(0, num.length - (4 * i + 3))+','+
            num.substring(num.length-(4 * i + 3));
        $('#deposit'+oh).val((((sign)?'':'-') + num + '.' + cents));
    }

    function dateValidation(dat,value){
        var vsplit = value.split('/');
        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var date = new Date(fulldate);
        var date1 = (date.getUTCMonth()+1)+'/'+(date.getUTCDate()+121)+'/'+(date.getFullYear());//+1900);
        var dateNew = new Date(date1);
        var sdate = dateNew.getUTCDate()+'/'+(dateNew.getUTCMonth()+1)+'/'+(dateNew.getFullYear());//+1900);
        $('#tarikhBayar'+dat).val(sdate);
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
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

    function refreshPage(){
        var url = '${pageContext.request.contextPath}/lelong/keputusan_perintah?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

       
    function cetakReport(){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
        var url = '${pageContext.request.contextPath}/lelong/utiliti_lelong?cetak';
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function(xhr, ajaxOptions, thrownError) {
                alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                $.unblockUI();
            },
            success : function(data) {
                if(data.indexOf('Laporan telah dijana')==0){
                    alert(data);
                    $('#folder').click();
                }else {
                    alert(data);
                    $('#urusan').click();
                }
                $.unblockUI();
            }
        });
    }


    var n = "";
    function validate(lol,input) {
        if (input.length == 0) {
            alert ('Sila Masukkan Harga Rizab');
            $('#ejaRizab'+lol).val("");
            return true;
        }
        else convert(lol,input);

        var num = document.getElementById('hargaRizab'+lol).value;
        num = num.toString().replace(/\$|\,/g,'');
        if (isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100+0.50000000001);
        cents = num % 100;
        num = Math.floor(num / 100).toString();
        if(cents < 10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
            num = num.substring(0, num.length - (4 * i + 3))+','+
            num.substring(num.length-(4 * i + 3));
        $('#hargaRizab'+lol).val((((sign)?'':'-') + num + '.' + cents));
    }

    function d1(x) { // single digit terms
        switch(x) {
            case '0': n= ""; break;
            case '1': n= " SATU "; break;
            case '2': n= " DUA "; break;
            case '3': n= " TIGA "; break;
            case '4': n= " EMPAT "; break;
            case '5': n= " LIMA "; break;
            case '6': n= " ENAM "; break;
            case '7': n= " TUJUH "; break;
            case '8': n= " LAPAN "; break;
            case '9': n= " SEMBILAN "; break;
            default: n = "Not a Number";
        }
        return n;
    }

    function d2(x) { // 10x digit terms
        switch(x) {
            case '0': n= ""; break;
            case '1': n= ""; break;
            case '2': n= " DUA PULUH"; break;
            case '3': n= " TIGA PULUH"; break;
            case '4': n= " EMPAT PULUH"; break;
            case '5': n= " LIMA PULUH"; break;
            case '6': n= " ENAM PULUH"; break;
            case '7': n= " TUJUH PULUH"; break;
            case '8': n= " LAPAN PULUH"; break;
            case '9': n= " SEMBILAN PULUH"; break;
            default: n = "Not a Number";
        }
        return n;
    }

    function d3(x) { // teen digit terms
        switch(x) {
            case '0': n= " SEPULUH "; break;
            case '1': n= " SEBELAS "; break;
            case '2': n= " DUA BELAS "; break;
            case '3': n= " TIGA BELAS "; break;
            case '4': n= " EMPAT BELAS "; break;
            case '5': n= " LIMA BELAS "; break;
            case '6': n= " ENAM BELAS "; break;
            case '7': n= " TUJUH BELAS "; break;
            case '8': n= " LAPAN BELAS "; break;
            case '9': n= " SEMBILAN BELAS "; break;
            default: n=  "Not a Number";
        }
        return n;
    }

    function convert(lol,input) {
        var inputlength = input.length;
        var x = 0;
        var teen1 = "";
        var teen2 = "";
        var teen3 = "";
        var numName = "";
        var invalidNum = "";
        var a1 = ""; // for insertion of million, thousand, hundred
        var a2 = "";
        var a3 = "";
        var a4 = "";
        var a5 = "";
        digit = new Array(inputlength); // stores output
        for (i = 0; i < inputlength; i++)  {
            // puts digits into array
            digit[inputlength - i] = input.charAt(i)
        };

        store = new Array(9); // store output
        for (i = 0; i < inputlength; i++) {
            x= inputlength - i;
            switch (x) { // assign text to each digit
                case x=9: d1(digit[x]); store[x] = n; break;
                case x=8: if (digit[x] == "1") {teen3 = "yes"}
                    else {teen3 = ""}; d2(digit[x]); store[x] = n; break;
                case x=7: if (teen3 == "yes") {teen3 = ""; d3(digit[x])}
                    else {d1(digit[x])}; store[x] = n; break;
                case x=6: d1(digit[x]); store[x] = n; break;
                case x=5: if (digit[x] == "1") {teen2 = "yes"}
                    else {teen2 = ""}; d2(digit[x]); store[x] = n; break;
                case x=4: if (teen2 == "yes") {teen2 = ""; d3(digit[x])}
                    else {d1(digit[x])}; store[x] = n; break;
                case x=3: d1(digit[x]); store[x] = n; break;
                case x=2: if (digit[x] == "1") {teen1 = "yes"}
                    else {teen1 = ""}; d2(digit[x]); store[x] = n; break;
                case x=1: if (teen1 == "yes") {teen1 = "";d3(digit[x])}
                    else {d1(digit[x])}; store[x] = n; break;
            }

            if (store[x] == "Not a Number"){invalidNum = "yes"};
            switch (inputlength){
                case 1:   store[2] = "";
                case 2:   store[3] = "";
                case 3:   store[4] = "";
                case 4:   store[5] = "";
                case 5:   store[6] = "";
                case 6:   store[7] = "";
                case 7:   store[8] = "";
                case 8:   store[9] = "";
            }

            if (store[9] != "") { a1 =" RATUS "} else {a1 = ""};
            if ((store[9] != "")||(store[8] != "")||(store[7] != ""))
            { a2 =" JUTA "} else {a2 = ""};
            if (store[6] != "") { a3 =" RATUS "} else {a3 = ""};
            if ((store[6] != "")||(store[5] != "")||(store[4] != ""))
            { a4 =" RIBU "} else {a4 = ""};
            if (store[3] != "") { a5 =" RATUS "} else {a5 = ""};
        }

        // add up text, cancel if invalid input found
        if (invalidNum == "yes"){
            numName = "Sila Masukkan Harga Rizab"
        }
        else {
            numName = "RINGGIT MALAYSIA" + store[9] + a1 + store[8] + store[7]
                + a2 + store[6] + a3 + store[5] + store[4]
                + a4 + store[3] + a5 + store[2] + store[1] + "SAHAJA" ;
        }
        store[1] = ""; store[2] = ""; store[3] = "";
        store[4] = ""; store[5] = ""; store[6] = "";
        store[7] = ""; store[8] = ""; store[9] = "";
        if (numName == ""){
            numName = "Kosong"
        };
        $('#ejaRizab'+lol).val(numName);
        return true;
    }
    

    var n = "";
    function changeFormat2(input) {

        if (input.length == 0) {
            alert ('Sila Masukkan Jumlah Keseluruhan Hutang');
            $('#amaunTung').val("");
            return true;
        }

        var num = document.getElementById('amaunTung').value;
        num = num.toString().replace(/\$|\,/g,'');
        if (isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100+0.50000000001);
        cents = num % 100;
        num = Math.floor(num / 100).toString();
        if(cents < 10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
            num = num.substring(0, num.length - (4 * i + 3))+','+
            num.substring(num.length-(4 * i + 3));
        $('#amaunTung').val((((sign)?'':'-') + num + '.' + cents));
    }
    
    function convertDay(value){
        var vsplit = value.split('/');
        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var date = new Date(fulldate);
        var date1 = date.getDay();
        var hari = "";
        switch(date1){
            case 0:  hari = "Ahad";
                break;
            case 1:  hari = "Isnin";
                break;
            case 2:  hari = "Selasa";
                break;
            case 3:  hari = "Rabu";
                break;
            case 4:  hari = "Khamis";
                break;
            case 5:  hari = "Jumaat";
                break;
            case 6:  hari = "Sabtu";
                break;
            default :hari = "salah";
                break;
        }
        $('#hari').val(hari);
    }
    
    function cetakSurat(f){
        alert ("---");
        var form = $(f).formSerialize();
        alert (form);
        var report = 'LLGBorang16HPenyerah_MLK.rdf';
        var param = '${actionBean.permohonan.idPermohonan}';
        var url = "reportName="+report+"%26report_p_id_mohon="+param;
        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url="+url, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    }
    
    function doViewReport(v) {
        alert (v);
        var url = '${pageContext.request.contextPath}/dokumen/view/idDokumen=" ' + v;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    
    function doSubmit(f){
        var form = $(f).formSerialize();
        var report = '${actionBean.reportName}';
        if(report == 'LLGBorang16HPenyerah_MLK.rdf')
            f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
        f.submit();
    }
</script>


<s:form name="UtilitiTetapkanTarikhLelonganBaru" beanclass="etanah.view.stripes.lelong.UtilitiTabsTarikhdanJurulelong">
    <p>
        <s:messages />
        <s:errors />
    </p>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Carian
            </legend>
            <br/>
            <p>
                <label> ID Permohonan :</label>
                <s:text name="idPermohonan" maxlength="20" size="31" id="idPermohonan"/>
            </p>
            <br/>
            <p align="center">
                <s:submit name="find" value="Cari" onclick="return validate();" class="btn" />
            </p></br>
        </fieldset>
    </div>

    <c:if test="${actionBean.flag eq true}">

        <s:hidden name="idPermohonan" id="permohonan.idPermohonan"  value="${actionBean.permohonan.idPermohonan}"/>
        <s:hidden name="idEnkuiri" value="${actionBean.enkuiri.idEnkuiri}"/>
            <s:hidden name="idDokumen" value="${actionBean.idDokumen}"/>
        <fieldset class="aras1">
            <legend>
                Maklumat Perintah Jualan  
            </legend>
            <br/>
            <em>* Perhatian : </em>
            <!--negeri9-->
            <font color="black">
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PJTA' && actionBean.negori ne true}">
                    Tarikh Lelongan Awam Hendaklah Tidak Kurang Daripada Satu Bulan Selepas Tarikh Perintah.
                </c:if>
                <!--melaka-->
                <c:if test="${actionBean.negori eq true}">
                    Tarikh Lelongan Awam Hendaklah Tidak Kurang Satu Bulan Dari Tarikh Akhir Siasatan.
                </c:if>
                <!--tanah adat-->
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJTA'}">
                    Tarikh Lelongan Awam Hendaklah Tidak Kurang Daripada Enam Bulan Selepas Tarikh Perintah.
                </c:if> 
            </font>
            <br/><br/>
            <p>
                <c:set value="1" var="dat"/>
                <label><font color="red">*</font>Tarikh Lelongan Awam :</label>
                <s:text name="tarikhLelong" id="datepicker" onclick="show_calendar();" formatPattern="dd/MM/yyyy"/>&nbsp;
                <font color="red" size="1">(cth : hh / bb / tttt)</font>
                &nbsp;
            </p>
            <p>
                <label><font color="red">*</font>Masa Lelongan Awam :</label>
                <s:select name="jam" style="width:56px" id="jam">
                    <s:option value="00">Jam</s:option>
                    <s:option value="01">01</s:option>
                    <s:option value="02">02</s:option>
                    <s:option value="03">03</s:option>
                    <s:option value="04">04</s:option>
                    <s:option value="05">05</s:option>
                    <s:option value="06">06</s:option>
                    <s:option value="07">07</s:option>
                    <s:option value="08">08</s:option>
                    <s:option value="09">09</s:option>
                    <s:option value="10">10</s:option>
                    <s:option value="11">11</s:option>
                    <s:option value="12">12</s:option>
                </s:select>:
                <s:select name="minit" style="width:65px" id="minit">
                    <s:option value="00">Minit</s:option>
                    <s:option value="00">00</s:option>
                    <s:option value="05">05</s:option>
                    <s:option value="10">10</s:option>
                    <s:option value="15">15</s:option>
                    <s:option value="20">20</s:option>
                    <s:option value="25">25</s:option>
                    <s:option value="30">30</s:option>
                    <s:option value="35">35</s:option>
                    <s:option value="40">40</s:option>
                    <s:option value="45">45</s:option>
                    <s:option value="50">50</s:option>
                    <s:option value="55">55</s:option>
                </s:select>:

                <s:select name="ampm" style="width:80px" id="ampm">
                    <s:option value="0">Pilih</s:option>
                    <s:option value="AM">Pagi</s:option>
                    <s:option value="PM">Petang</s:option>
                </s:select>

            <p>
                <label><font color="red">*</font> Hari :</label>
                <s:text id="hari" name="lelong.tarikhLelong" disabled="true" formatPattern="EEEE" />
            </p>

            <p>
                <label><font color="red">*</font> Tempat :</label>
                <s:textarea id="tempat" name="tempat" cols="50" rows="5"/>
            </p>

            <p>
                <label>Tarikh Akhir Bayaran : </label>
                <s:text id="tarikhBayar1" name="tarikhAkhirBayaran" formatPattern="dd/MM/yyyy" readonly="true" />&nbsp;(120 hari dari tarikh lelongan)
            </p>
            <br>

            <%--for negori Melake je--%>
            <c:if test="${actionBean.negori eq true}">
                <p>
                    <label><font color="red">*</font>Jumlah Keseluruhan Hutang : </label>RM
                    <s:text id="amaunTung" name="amaunTunggakan" formatPattern="###,###,###.00" onblur="changeFormat2(this.value);" onkeyup="validateNumber(this,this.value);"/>&nbsp;
                </p><br>
            </c:if>
            <div class="content" align="center">
                <c:if test="${same eq false}">
                    <display:table class="tablecloth" name="${actionBean.listLel}" id="line">
                        <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                        <display:column title="IDHakmilik" property="hakmilikPermohonan.hakmilik.idHakmilik" />
                        <display:column title="Harga Rizab" >
                            RM <s:text name="hargaRizab${line_rowNum - 1}" value="${line.hargaRizab}" id="hargaRizab${line_rowNum}" onkeyup="validateNumber(this,this.value);" onchange="calc(${line_rowNum},this.value);" onblur="validate(${line_rowNum},this.value)" formatPattern="###,###,###.00"/>
                        </display:column>
                        <display:column title="Deposit">
                            RM <s:text name="deposit${line_rowNum - 1}" value="${line.deposit}" id="deposit${line_rowNum}" readonly="true" formatPattern="###,###,###.00"/>
                        </display:column>
                    </display:table>
                </c:if>
                <c:if test="${same eq true}">
                    <display:table class="tablecloth" name="${actionBean.listLelongan}" id="line">
                        <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                        <display:column title="IDHakmilik" value="${actionBean.idHak}" property="hakmilikPermohonan.hakmilik.idHakmilik" />
                        <display:column title="Harga Rizab sama">
                            RM <s:text name="hargaRizab" id="hargaRizab${line_rowNum}" onkeyup="validateNumber(this,this.value);" onchange="calc(${line_rowNum},this.value);" onblur="validate(${line_rowNum},this.value)" formatPattern="###,###,###.00">
                                ${actionBean.enkuiri.hargaRizab}
                            </s:text>
                        </display:column>
                        <display:column title="Deposit">
                            RM <s:text name="deposit" id="deposit${line_rowNum}" readonly="true" formatPattern="###,###,###.00">
                                ${actionBean.enkuiri.deposit}
                            </s:text>
                        </display:column>
                    </display:table>
                </c:if>
            </div>

            <c:if test="${actionBean.button eq false}" >
                <%--berasingan--%>
                <c:if test="${same eq false}">
                    <p align="center">
                        <%--negeri 9--%>
                        <c:if test="${actionBean.negori eq false}">
                            <s:submit name="simpanLelong" id="save" value="Simpan Dan Jana" class="longbtn" onclick="showReport2(this.form,this.name);"/>
                        <p align="center">
                            <s:submit name="cetak" id="report" value="Borang 16H" class="longBtn" onclick="cetakReport();"/>
                        </p>
                    </c:if>
                    <%--melaka--%>
                    <c:if test="${actionBean.negori eq true}">
                        <s:submit name="simpanLelong" id="save" value="Simpan Dan Jana2" class="longbtn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                        <p align="center">
                            <s:submit name="cetak" id="report" value="Borang 16H" class="longBtn" onclick="cetakReport();"/>
                            <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit(this.form);"/>&nbsp;
                        </p>
                    </c:if>
                </p>
            </c:if>
            <%--bersama--%>
            <c:if test="${same eq true}">
                <p align="center">
                    <%--negeri 9--%>
                    <c:if test="${actionBean.negori eq false}">
                        <s:submit name="saveBersama" id="save" value="Simpan Dan Jana" class="longbtn" onclick="showReport2(this.form,this.name);"/>
                    <p align="center">
                        <s:submit name="cetak" id="report" value="Borang 16H" class="longBtn" onclick="cetakReport();"/>
                    </p>
                </c:if>
                <%--melaka--%>
                <c:if test="${actionBean.negori eq true}">
                    <s:submit name="saveBersama" id="save" value="Simpan Dan Jana2" class="longbtn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                    <p align="center">
                        <s:submit name="cetak" id="report" value="Borang 16H" class="longBtn" onclick="doViewReport('${actionBean.idDokumen}');"/>${actionBean.idDokumen} 
                    </p>
                </c:if>
            </p>
        </c:if>
    </c:if>
</fieldset>
<c:if test="${fn:length(actionBean.senaraiLelongan3)>0}">
    <fieldset class="aras1">
        <legend>
            Sejarah Lelongan
        </legend>
        <div class="content" align="center">
            <display:table class="tablecloth" name="${actionBean.senaraiLelongan3}" id="line" >
                <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                <display:column title="IDHakmilik" property="hakmilikPermohonan.hakmilik.idHakmilik"/>
                <display:column title="Tarikh Lelongan Awam"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhLelong}"/></display:column>
                <display:column title="Masa"><fmt:formatDate pattern="hh:mm aaa" value="${line.tarikhLelong}"/>
                </display:column>
                <display:column property="tempat" title="Tempat" class="nama${line_rowNum}"/>
                <display:column title="Harga Rizab (RM)"><div align="right">
                        <fmt:formatNumber pattern="#,##0.00" value="${line.hargaRizab}"/>
                    </div></display:column>
                <display:column title="Deposit (RM)"><div align="right">
                        <fmt:formatNumber pattern="#,##0.00" value="${line.deposit}"/>
                    </div></display:column>
                <display:column title="Tarikh Akhir Bayaran"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhAkhirBayaran}"/></display:column>
                <display:column property="kodStatusLelongan.nama" title="Status" class="" style="text-transform:uppercase;" />
                <%--<display:column title="Ulasan" class=""/>--%>
            </display:table>
        </div>
    </fieldset>
</c:if>
</c:if>
</s:form>