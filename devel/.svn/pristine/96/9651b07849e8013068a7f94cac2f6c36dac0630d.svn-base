<%-- 
    Document   : maklumat_perintah_jualan
    Created on : Mar 4, 2010, 10:48:02 AM
    Author     : mazurahayati
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<script type="text/javascript">
    function show_calendar(){
        window.open("${pageContext.request.contextPath}/lelong/maklumat_lelongan_awam?showForm3", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1200,height=1200,scrollbars=1");
    }
    function calc(value){
        var deposit = value * 0.1;
        var baki = value - deposit;
        $("#deposit").val(deposit);
        $("#baki").val(baki);
        $("#deposit1").val(deposit);
        $("#baki1").val(baki);

        var num = document.getElementById('deposit').value;
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
        $('#deposit').val((((sign)?'':'-') + num + '.' + cents));
    }

             
    function dateValidation(value){
        var now = new Date();
        var vsplit = value.split('/');
        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var date = new Date(fulldate);
        var date1 = (date.getUTCMonth()+1)+'/'+(date.getUTCDate()+119)+'/'+(date.getFullYear());//+1900);
        var dateNew = new Date(date1);
        var sdate = dateNew.getUTCDate()+'/'+(dateNew.getUTCMonth()+1)+'/'+(dateNew.getFullYear());//+1900);
        $('#tarikhBayar').val(sdate);
        $('#tarikhBayar1').val(sdate);
    }


    function validation() {
        if($("#datepicker").val() == ""){
            alert('Sila masukkan " Tarikh Lelongan "');
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

        if($("#hargaRizab").val() == ""){
            alert('Sila Masukkan " Harga Rizab "');
            $("#hari").focus();
            return false;
        }
        if($("#tempat").val() == ""){
            alert('Sila Masukkan " Tempat "');
            $("#tempat").focus();
            return false;
        }
        return true;
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
        var url = '${pageContext.request.contextPath}/lelong/maklumat_lelongan_awam?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function edit1(f, id1){
        var queryString = $(f).formSerialize()
        window.open("${pageContext.request.contextPath}/lelong/maklumat_lelongan_awam?cetak&"+queryString+"&notis.idNotis="+id1, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }

    var n = "";
    function validate(input) {
        if (input.length == 0) {
            alert ('Sila Masukkan Harga Rizab');
            $('#ejaRizab').val("");
            return true;
        }
        else convert(input);

        var num = document.getElementById('hargaRizab').value;
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
        $('#hargaRizab').val((((sign)?'':'-') + num + '.' + cents));
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

    function convert(input) {
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
        $('#ejaRizab').val(numName);
        return true;
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
</script>

<s:form beanclass="etanah.view.stripes.lelong.LelonganAwamActionBean">
    <s:messages/>
    <s:errors/>&nbsp;
    <c:if test="${error}">
        <c:if test="${actionBean.status ne 'RM'}">
            <fieldset class="aras1">
                <p>
                    <label>Status lelongan :</label>
                    <c:if test="${actionBean.lelong.bil eq '1'}">
                        Kali Pertama
                    </c:if>
                    <c:if test="${actionBean.lelong.bil eq '2'}">
                        Kali Kedua
                    </c:if>
                    <c:if test="${actionBean.lelong.bil eq '3'}">
                        Kali Ketiga
                    </c:if>
                </p>
                <br>

                <div class="subtitle" id="lain">
                    <fieldset class="aras1">
                        <legend>
                            Maklumat Perintah Jualan
                        </legend>
                        <div class="content">
                            <div class="subtitle">
                                <div class="content">

                                    <p>
                                        <label><font color="red">*</font>Tarikh Lelongan Awam:</label>
                                        <s:text name="tarikhLelong" id="datepicker" class="datepicker" onchange="dateValidation(this.value)"/>&nbsp;
                                        <a href="javascript:show_calendar();">
                                            <img src="${pageContext.request.contextPath}/pub/images/calendar.gif" id="" width=15 height=15 /></a>
                                        <font color="red" size="1">(cth : hh / bb / tttt)</font>
                                        &nbsp;
                                    </p>

                                    <p>
                                        <label><font color="red">*</font>Masa Lelongan Awam :</label>
                                        <s:select name="jam" style="width:56px">
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
                                        <s:select name="minit" style="width:65px">
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
                                        <s:select name="ampm" style="width:80px">
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
                                        <label><font color="red">*</font>Harga Rizab (RM): </label>
                                        <s:text id="hargaRizab" name="hargaRizab" value="numver" onkeyup="validateNumber(this,this.value);" onchange="calc(this.value);" onblur="validate(this.value)" formatPattern="###,###,###.00"/>&nbsp;
                                    </p>

                                    <p>
                                        <label>Harga Rizab Dalam Perkataan:</label>
                                        <s:textarea id="ejaRizab" name="ejaanHarga" cols="40" rows="3" /> Ringgit &nbsp;
                                    </p>

                                    <p>
                                        <label>Deposit (RM): </label>
                                        <s:text id="deposit" name="deposit" value="numver" disabled="true" formatPattern="###,###,###.00"/> (10% daripada harga rizab) &nbsp;
                                        <s:hidden name="deposit" id="deposit1"/>
                                    </p>

                                    <p>
                                        <label>Tarikh Akhir Bayaran : </label>
                                        <s:text id="tarikhBayar" name="tarikhAkhirBayaran" disabled="true" />&nbsp;(120 hari dari tarikh lelongan)
                                        <s:hidden name="tarikhAkhirBayaran" id="tarikhBayar1"/>
                                    </p>

                                    <c:if test="${actionBean.disabled eq null}">
                                        <p align="right">
                                            <s:button name="simpanLelong" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                                            <%--<s:reset name="" value="Isi Semula" class="btn"/>&nbsp;--%>
                                        </p>
                                    </c:if>

                                    <c:if test="${actionBean.disabled ne null}">
                                        <p align="center">
                                            <em>Sila tekan selesai untuk cetak sijil rujukan mahkamah</em>
                                            <%--<s:button name="cetak" id="save" value="Cetak Sijil Rujukan" class="longbtn"/>--%>
                                        </p>
                                    </c:if>

                                    <%--                                <c:if test="${actionBean.status eq 'RM'}">
                                                                        <p align="center">
                                                                            <em>Sila tekan selesai untuk cetak sijil rujukan mahkamah</em>
                                                                            <s:button name="cetak" id="save" value="Cetak Sijil Rujukan" class="longbtn"/>
                                                                        </p>
                                                                    </c:if>--%>

                                    <legend>
                                        Sejarah Lelongan
                                    </legend>
                                    <div class="content" align="center">
                                        <display:table class="tablecloth" name="${actionBean.senaraiLelongan}" id="line" >
                                            <display:column property="bil" title="Bil. Lelongan" class="nama${line_rowNum}" sortName="bil" />
                                            <display:column title="Tarikh Lelongan Awam"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhLelong}"/></display:column>
                                            <display:column title="Masa"><fmt:formatDate pattern="hh:mm aaa" value="${line.tarikhLelong}"/></display:column>
                                            <display:column property="tempat" title="Tempat" class="nama${line_rowNum}"/>
                                            <display:column title="Harga Rizab (RM)"><div align="right">
                                                    <fmt:formatNumber pattern="#,##0.00" value="${line.hargaRizab}"/>
                                                </div></display:column>
                                            <display:column title="Deposit (RM)"><div align="right">
                                                    <fmt:formatNumber pattern="#,##0.00" value="${line.deposit}"/>
                                                </div></display:column>
                                            <display:column title="Tarikh Akhir Bayaran"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhAkhirBayaran}"/></display:column>
                                            <display:column property="kodStatusLelongan.nama" title="Status" class=""/>
                                            <display:column property="" title="Ulasan" class=""/>
                                        </display:table>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </fieldset>
                </div>
            </c:if>
        </c:if>



        <c:if test="${actionBean.flag}">
            <fieldset class="aras1">
                <p>
                    <label>Status lelongan :</label>
                    <c:if test="${actionBean.lelong.bil eq '1'}">
                        Kali Pertama
                    </c:if>
                    <c:if test="${actionBean.lelong.bil eq '2'}">
                        Kali Kedua
                    </c:if>
                    <c:if test="${actionBean.lelong.bil eq '3'}">
                        Kali Ketiga
                    </c:if>
                </p>
                <br>
                <div class="subtitle" id="lain">
                    <fieldset class="aras1">
                        <legend>
                            Maklumat Perintah Jualan
                        </legend>
                        <div class="content">
                            <div class="subtitle">
                                <div class="content">
                                    <p>
                                        <label><font color="red">*</font>Tarikh Lelongan Awam:</label>
                                        <s:text name="tarikhLelong" id="datepicker" class="datepicker" onchange="dateValidation(this.value)"/>&nbsp;
                                        <a href="javascript:show_calendar();">
                                            <img src="${pageContext.request.contextPath}/pub/images/calendar.gif" id="" width=15 height=15 /></a>
                                        <font color="red" size="1">(cth : hh / bb / tttt)</font>
                                        &nbsp;
                                    </p>

                                    <p>
                                        <label><font color="red">*</font>Masa Lelongan Awam :</label>
                                        <s:select name="jam" style="width:56px">
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
                                        <s:select name="minit" style="width:65px">
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
                                        <s:select name="ampm" style="width:80px">
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
                                        <label><font color="red">*</font>Harga Rizab (RM): </label>
                                        <s:text id="hargaRizab" name="hargaRizab" value="numver" onkeyup="validateNumber(this,this.value);" onchange="calc(this.value);" onblur="validate(this.value)" formatPattern="###,###,###.00"/>&nbsp;
                                    </p>

                                    <p>
                                        <label>Harga Rizab Dalam Perkataan:</label>
                                        <s:textarea id="ejaRizab" name="ejaanHarga" cols="40" rows="3" /> Ringgit &nbsp;
                                    </p>

                                    <p>
                                        <label>Deposit (RM): </label>
                                        <s:text id="deposit" name="deposit" value="0.00" disabled="true" formatPattern="#,##0.00"/> (10% daripada harga rizab) &nbsp;
                                        <s:hidden name="deposit" id="deposit1"/>
                                    </p>

                                    <p>
                                        <label>Tarikh Akhir Bayaran : </label>
                                        <s:text id="tarikhBayar" name="tarikhAkhirBayaran" disabled="true" />&nbsp;(120 hari dari tarikh lelongan)
                                        <s:hidden name="tarikhAkhirBayaran" id="tarikhBayar1"/>
                                    </p>

                                    <c:if test="${actionBean.disabled eq null}">
                                        <p align="right">
                                            <s:button name="simpanLelong" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                                            <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
                                        </p>
                                    </c:if>

                                    <c:if test="${actionBean.disabled ne null}">
                                        <p align="right">
                                            <%--<em>Sila tekan selesai untuk cetak sijil rujukan mahkamah</em>--%>
                                            <s:button name="cetak" id="save" value="Cetak Sijil Rujukan" class="longbtn"/>
                                        </p>
                                    </c:if>


                                    <legend>
                                        Sejarah Lelongan
                                    </legend>
                                    <div class="content" align="center">
                                        <display:table class="tablecloth" name="${actionBean.senaraiLelongan}" pagesize="3" id="line" >
                                            <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                                            <display:column title="Tarikh Lelongan Awam"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhLelong}"/></display:column>
                                            <display:column title="Masa"><fmt:formatDate pattern="hh:mm aaa" value="${line.tarikhLelong}"/></display:column>
                                            <display:column property="tempat" title="Tempat" class="nama${line_rowNum}"/>
                                            <display:column title="Harga Rizab (RM)"><div align="right">
                                                    <fmt:formatNumber pattern="#,##0.00" value="${line.hargaRizab}"/>
                                                </div></display:column>
                                            <display:column title="Deposit (RM)"><div align="right">
                                                    <fmt:formatNumber pattern="#,##0.00" value="${line.deposit}"/>
                                                </div></display:column>
                                            <display:column title="Tarikh Akhir Bayaran"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhAkhirBayaran}"/></display:column>
                                        </display:table>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </fieldset>
                </div>
            </fieldset>
        </fieldset>
    </c:if>
</s:form>