<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : keputusanPerintah
    Created on : Jun 1, 2010, 12:28:37 PM
    Author     : mazurahayati.yusop
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
    function show_calendar(){
        window.open("${pageContext.request.contextPath}/lelong/keputusan_perintah?showFormB", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1200,height=1200,scrollbars=1");
    }
    
    function show_calendarAdat(){
        window.open("${pageContext.request.contextPath}/lelong/keputusan_perintah?showFormD", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1200,height=1200,scrollbars=1");
    }
    //melaka
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
        if($("#pengguna").val() == ""){
            alert('Sila masukkan " Nama Pegawai Yang Menjatuhkan Perintah " terlebih dahulu.');
            $("#pengguna").focus();
            return false;
        }
        if($("#hargaRizab").val() == ""){
            alert('Sila masukkan " Harga Rizab " terlebih dahulu.');
            $("#hargaRizab").focus();
            return false;
        }
        return true;
    }
    //negeri sembilan
    function validation2() {
        
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
        //added by nur.aqilah
        if($("#pengguna").val() == ""){
            alert('Sila masukkan " Nama Pegawai Yang Menjatuhkan Perintah " terlebih dahulu.');
            $("#pengguna").focus();
            return false;
        }
        
        var bil = $(".kodsuku").length;
        if(bil > 0){
            var ckd = false;
            for (var i = 0; i < bil; i++){
                ckd = $('#kodsuku'+i).is(":checked");
                if(ckd == true){
                    break;
                }
            }
            if(ckd == false){
                alert('Sila pilih " Terbuka Kepada Suku " terlebih dahulu.');
                $("#kodsuku0").focus();
                return false;
            }
        }
        
        if($("#hargaRizab").val() == ""){
            alert('Sila masukkan " Harga Rizab " terlebih dahulu.');
            $("#hargaRizab").focus();
            return false;
        }
        
        return true;
    }

    function showReport2(frm,event){

        if(validation2()){
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

    function showReport(name){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
        var url = '${pageContext.request.contextPath}/lelong/keputusan_perintah?'+name;
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function(xhr, ajaxOptions, thrownError) {
                alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                $.unblockUI();
            },
            success : function(data) {
                alert(data);
                $('#folder').click();
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
    
    var p = "";
    function changeFormat3(input) {

        if (input.length == 0) {
            alert ('Sila Masukkan Jumlah Keseluruhan Hutang');
            $('#amaunTung2').val("");
            return true;
        }

        var num = document.getElementById('amaunTung2').value;
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
        $('#amaunTung2').val((((sign)?'':'-') + num + '.' + cents));
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
    
    function addRow() {
        var table = document.getElementById("add");
        var rowcount = table.rows.length;
        var rowNum = $('#rowCount3').val();
        if(rowNum == null){
            rowNum = 0;
        }
        var rowCount1 = (parseInt(rowNum)+1);
        var row = table.insertRow(rowcount);
        var cell2 = row.insertCell(0);
        cell2.innerHTML = "<p><label>&nbsp;</label>RM</p>";

        var cell1 = row.insertCell(1);
        var e1 = document.createElement("INPUT");
        e1.name = 'amaunTunggakan2';
        e1.id = 'amaunTung2';
        e1.type = 'text';
        e1.setAttribute("formatPattern", "###,###,###.00");
        e1.onblur = function(){changeFormat3(this);};
        e1.style.width = '150px';
        cell1.appendChild(e1);
        
        var cell2 = row.insertCell(2);
        cell2.innerHTML = "&nbsp;";

        //        var cell3 = row.insertCell(3);
        //        var e1 = document.createElement("img");
        //        e1.src = "${pageContext.request.contextPath}/images/not_ok.gif";
        //        e1.alt = "Klik Untuk Hapus1";
        //        e1.align = "top"
        //        e1.value="Hapus";
        //        e1.height = "15";
        //        e1.width = "15";
        //        e1.id =rowcount;
        //        e1.onclick=function(){deleteRow(this.id);};
        //        cell3.appendChild(e1);

        $('#rowCount3').val(parseInt(rowCount1));

    }
    
    function deleteRow(elementId)
    {
        var table = document.getElementById("add");
        var rowcount = table.rows.length;
        if(rowcount > 1){
            document.getElementById("add").deleteRow(elementId);
        }
        if(rowcount ==1 ){
            document.getElementById("add").deleteRow(0);
        }
        var rowNum = $('#rowCount3').val();
        $('#rowCount3').val(parseInt(rowNum)-1);
        regenerateBill();
    }

    function deleteRow3(id)
    {
        if(confirm("Adakah Anda Pasti?")){
            var url = '${pageContext.request.contextPath}/lelong/butirPinjaman?delete&id='+id;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }


    function regenerateBill(){

        var table = document.getElementById("add");
        var rowCount = table.rows.length;
        for(var i=0;i<rowCount;i++){
            //  alert("id:"+(table.rows[i].cells[2].childNodes[0].id));
            //  alert(table.rows[i].cells[0].childNodes[0].data);
            //  table.rows[i].cells[0].childNodes[0].data= "4."+(i+1);
            table.rows[i].cells[2].childNodes[0].id= i;
        }
    }



    
</script>


<s:form beanclass="etanah.view.stripes.lelong.KeputusanPerintahActionBean">
    <p>
        <s:messages />
        <s:errors />&nbsp;
    </p>
    <c:if test="${actionBean.suratTolak eq true}">
        <fieldset class="aras1">
            <legend>
                <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'T'}">
                    Jana Dan Cetak Surat Tutup Permohonan
                </c:if>
                <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'TT'}">
                    Jana Dan Cetak Surat Tolak Kes
                </c:if>
                <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'BP'}">
                    Jana Dan Cetak Surat Batal Permohonan
                </c:if>
            </legend><br>
            <p align="center">
                <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'T'}">
                    <s:button name="genReport" id="save" value="Surat Tolak" class="longbtn" onclick="showReport(this.name);"/>
                </c:if>
                <c:if test="${actionBean.negori eq true}">
                    <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'TT'}">
                        <s:button name="genReport" id="save" value="Surat Tutup Kes" class="longbtn" onclick="showReport(this.name);"/>
                    </c:if>
                </c:if>

                <c:if test="${actionBean.negori eq false}">
                    <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'ZY'}">
                        <s:button name="genReport" id="save" value="Surat Tutup Kes" class="longbtn" onclick="showReport(this.name);"/>
                    </c:if>
                </c:if>
                <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'BP'}">

                    <s:button name="genReport" id="save" value="Surat Batal" class="longbtn" onclick="showReport(this.name);"/>
                </c:if>

            </p><br>
        </fieldset>
    </c:if>

    <c:if test="${actionBean.error eq true}">
        <fieldset class="aras1">
            <legend>
                Maklumat Perintah Jualan
            </legend>

            <c:if test="${sblmPilih eq true}">
                <p>
                    <label><font color="red">*</font>Jenis Lelongan : </label>
                    <s:radio name="sss" id="" value="B"/> Lelongan Bersama &nbsp;&nbsp;
                    <s:radio name="sss" id="" value="A"/> Lelongan Berasingan
                </p><br>
                <p align="center">
                    <s:button name="jenisLelong" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p><br>
            </c:if>
            <c:if test="${sblmPilih eq false}">

                <c:if test="${actionBean.lelong.bil ne null}">
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
                    </p><br>
                </c:if>
                <p>
                    <c:set value="1" var="dat"/>
                    <label><font color="red">*</font>Tarikh Lelongan Awam :</label>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PJTA'}">
                        <s:text name="tarikhLelong" id="datepicker" onclick="show_calendar();" formatPattern="dd/MM/yyyy"/>&nbsp;
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJTA'}">
                        <s:text name="tarikhLelong" id="datepicker" onclick="show_calendarAdat();" formatPattern="dd/MM/yyyy"/>&nbsp;
                    </c:if>


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
                        <s:option value="null">Minit</s:option>
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
                    <s:textarea id="tempat" name="lelong.tempat" cols="50" rows="5"/>
                </p>

                <p>
                    <label>Tarikh Akhir Bayaran : </label>
                    <s:text id="tarikhBayar1" name="tarikhAkhirBayaran" formatPattern="dd/MM/yyyy" readonly="false" />&nbsp;(120 hari dari tarikh lelongan)
                </p>

                <%--<p>
                    <label><font color="red">*</font>Tarikh Warta : </label>
                    <s:text id="tarikhWarta" name="tarikhWarta" formatPattern="dd/MM/yyyy" class="datepicker"/>
                </p>--%>
                <br>

                <%--for negori Melako je--%>
                <c:if test="${actionBean.negori eq true}">
                    <c:set var="i" value="0" />
                    <p>
                        <label><font color="red">*</font>Jumlah Keseluruhan Hutang : </label>RM

                        <s:text id="amaunTung" name="amaunTunggakan" style="width:150px;" formatPattern="###,###,###.00" onblur="changeFormat2(this.value);" onkeyup="validateNumber(this,this.value);"/>&nbsp;                        
                        <img src="${pageContext.request.contextPath}/pub/images/tambah.png" height="15" width="15" alt="Tambah Jumlah Hutang"
                             onmouseover="this.style.cursor='pointer';" onclick="addRow();return false;" title="Tambah Jumlah Hutang">

                    </p>

                    <c:if test="${actionBean.amaunTunggakan2 ne null}">
                        <p>
                            <label>&nbsp;</label>RM
                            <s:text id="amaunTung2" name="amaunTunggakan2" style="width:150px;" formatPattern="###,###,###.00" onblur="changeFormat2(this.value);"/>&nbsp;
<!--                            <img src="${pageContext.request.contextPath}/images/not_ok.gif" height="15" width="15" alt="Klik Untuk Hapus"
                                 onmouseover="this.style.cursor='pointer';" onclick="deleteRow3('${line.idEnkuiriPeminjam}');return false;"  title="Klik Untuk Hapus">-->
                        </p>
                    </c:if>

                    <table id ="add" border="0"></table>
                    <c:set var="i" value="${i+1}" />
                    <s:hidden name="rowCount3"  id="rowCount3"/>


                </p><br>

                <c:if test="${actionBean.lelong.bil eq '1'}">

                    <p>
                        <label><font color="red">* </font>Pegawai Yang Menjatuhkan Perintah :</label>
                        <s:select name="idPengguna" style="width:250px"  id="pengguna">
                            <s:option id="pengguna" value="">-Sila Pilih-</s:option>
                            <s:options-collection collection="${actionBean.senaraiPT}" label="nama" id="pengguna" value="idPengguna" />
                        </s:select>
                        &nbsp;
                    </p>
                </c:if>
            </c:if>
        </c:if>

        <%--for PJTA--%>
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJTA'}">
            <label>Terbuka Kepada Suku : </label>
            <c:set var="i" value="0"/>
            <c:set var="j" value="0"/>
            <c:forEach items="${actionBean.list}" var="kod">
                <c:forEach items="${actionBean.listSuku}" var="suku">
                    <c:if test="${kod.kod eq suku.kodSuku.kod}">
                        <c:set var="i" value="1"/>
                        <input type="checkbox" class="kodsuku" id="kodsuku${j}" name="kodsuku${j}" checked="checked" value="${kod.kod}"/> <font color="black">${suku.kodSuku.nama}</font><p><p/>
                        <label>&nbsp;</label>
                    </c:if>
                </c:forEach>
                <c:if test="${i eq 0}">
                    <input type="checkbox" class="kodsuku" id="kodsuku${j}" name="kodsuku${j}" value="${kod.kod}"/> <font color="black">${kod.nama}</font><p><p/>
                    <label>&nbsp;</label>
                </c:if>
                <c:set var="i" value="0"/>    
                <c:set var="j" value="${j+1}"/>
            </c:forEach> <br/>


            <%---- <display:table class="tablecloth" name="${actionBean.list}"  cellpadding="0" cellspacing="0" id="line" requestURI="/lelong/keputusan_perintah">
              <display:column> <s:checkbox name="kodsuku${line_rowNum - 1}" id="chkbox${line_rowNum}" value="${line.kod}" class="chkbox" onmouseover="this.style.cursor='pointer';"/></display:column>
              <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
              <display:column title="Suku Pembida" property="nama" class="nama"/>
          </display:table>---%>
        </c:if>

        <div class="content" align="center">
            <c:if test="${same eq false}">
                <display:table class="tablecloth" name="${actionBean.listLel}" id="line">
                    <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                    <display:column title="IDHakmilik" property="hakmilikPermohonan.hakmilik.idHakmilik" />
                    <display:column title="Harga Rizab" >
                        RM <s:text name="listLel[${line_rowNum - 1}].hargaRizab" id="hargaRizab${line_rowNum}" onkeyup="validateNumber(this,this.value);" onchange="calc(${line_rowNum},this.value);" onblur="validate(${line_rowNum},this.value)" formatPattern="###,###,###.00">
                        </s:text>
                    </display:column>
                    <display:column title="Deposit">
                        RM <s:text name="listLel[${line_rowNum - 1}].deposit" value="${line.deposit}" id="deposit${line_rowNum}" readonly="true" formatPattern="###,###,###.00"/>
                    </display:column>
                </display:table>
            </c:if>
            <c:if test="${same eq true}">
                <display:table class="tablecloth" name="${actionBean.listLelongan}" id="line">
                    <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                    <display:column title="IDHakmilik" value="${actionBean.idHak}"/>
                    <display:column title="Harga Rizab" >
                        RM <s:text name="enkuiri.hargaRizab" id="hargaRizab${line_rowNum}" onkeyup="validateNumber(this,this.value);" onchange="calc(${line_rowNum},this.value);" onblur="validate(${line_rowNum},this.value)" formatPattern="###,###,###.00">
                            ${actionBean.enkuiri.hargaRizab}
                        </s:text>
                    </display:column>
                    <display:column title="Deposit">
                        RM <s:text name="enkuiri.deposit" id="deposit${line_rowNum}" readonly="true" formatPattern="###,###,###.00">
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
                        <s:button name="simpanLelong" id="save" value="Simpan Dan Jana" class="longbtn" onclick="showReport2(this.form,this.name);"/>
                    </c:if>
                    <%--melaka--%>
                    <c:if test="${actionBean.negori eq true}">
                        <s:button name="simpanLelong" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                    </c:if>
                    <s:reset name="isiMula" value="Isi Semula" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
                </p>
            </c:if>
            <%--bersama--%>
            <c:if test="${same eq true}">
                <p align="center">
                    <%--negeri 9--%>
                    <c:if test="${actionBean.negori eq false}">
                        <s:button name="saveBersama" id="save" value="Simpan Dan Jana" class="longbtn" onclick="showReport2(this.form,this.name);"/>
                    </c:if>
                    <%--melaka--%>
                    <c:if test="${actionBean.negori eq true}">
                        <s:button name="saveBersama" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                    </c:if>
                    <s:reset name="isiMulaBersama" value="Isi Semula" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
                </p>
            </c:if>
        </c:if>
    </fieldset>

    <c:if test="${fn:length(actionBean.senaraiEnkuiri3)>0}">
        <fieldset class="aras1">
            <legend>
                Sejarah Siasatan
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiEnkuiri3}" id="line">
                    <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                    <display:column title="Tarikh"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhEnkuiri}"/></display:column>
                    <display:column title="Masa"><fmt:formatDate pattern="hh:mm aaa" value="${line.tarikhEnkuiri}"/></display:column>
                    <display:column property="tempat" title="Tempat" class="nama${line_rowNum}"/>
                    <display:column property="perkara" title="Perkara" class="nama${line_rowNum}"/>
                    <display:column property="status.nama" title="Status" class=""/>
                    <display:column property="ulasanPegawai" title="Ulasan" class="nama${line_rowNum}"/>
                </display:table>
            </div>
        </fieldset>
    </c:if>
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
                    <display:column title="Masa"><fmt:formatDate pattern="hh:mm aaa" value="${line.tarikhLelong}"/></display:column>
                    <display:column property="tempat" title="Tempat" class="nama${line_rowNum}"/>
                    <display:column title="Harga Rizab (RM)"><div align="right">
                            <fmt:formatNumber pattern="#,##0.00" value="${line.hargaRizab}"/>
                        </div></display:column>
                    <display:column title="Deposit (RM)"><div align="right">
                            <fmt:formatNumber pattern="#,##0.00" value="${line.deposit}"/>
                        </div></display:column>
                    <display:column title="Tarikh Akhir Bayaran"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhAkhirBayaran}"/></display:column>
                    <display:column property="kodStatusLelongan.nama" title="Status" class="" style="text-transform:uppercase;"/>
                    <%--<display:column title="Ulasan" class=""/>--%>
                </display:table>
            </div>
        </fieldset>
    </c:if>

    <fieldset class="aras1">
        <legend>
            Maklumat Siasatan
        </legend>
        <div class="content">
            <table>
                <tr>
                    <td ><label> Tarikh :</label>
                    </td>
                    <td>
                        <fmt:formatDate value="${actionBean.enkuiri.tarikhEnkuiri}" pattern="dd/MM/yyyy"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Hari : </label>
                    </td>
                    <td>
                        <font style="text-transform: uppercase"><fmt:formatDate value="${actionBean.enkuiri.tarikhEnkuiri}" pattern="EEEE"/></font>&nbsp;
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Masa : </label>
                    </td>
                    <td>
                        <fmt:formatDate value="${actionBean.enkuiri.tarikhEnkuiri}" pattern="hh:mm"/>&nbsp;
                        <fmt:formatDate type="time"
                                        pattern="aaa"
                                        value="${actionBean.enkuiri.tarikhEnkuiri}" var="timeformat"/>
                        <c:if test="${timeformat eq 'AM'}"> PAGI</c:if>
                        <c:if test="${timeformat eq 'PM'}"> PETANG</c:if>
                        </td>
                    </tr>
                    <tr>
                        <td valign="top">
                            <label> Tempat : </label>
                        </td>
                        <td>
                            <font style="text-transform: uppercase">${actionBean.enkuiri.tempat}</font>&nbsp;&nbsp;
                    </td>
                </tr>
                <tr>
                    <td>
                        <label> Perkara : </label>
                    </td>
                    <td>
                        <font style="text-transform: uppercase">${actionBean.enkuiri.perkara}</font> &nbsp;
                    </td>
                </tr>
            </table>
        </div>
    </fieldset>

    <c:if test="${fn:length(actionBean.senaraiEnkuiri4)>0}">
        <br>
        <fieldset class="aras1">
            <legend>
                Sejarah Siasatan
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiEnkuiri4}" id="line">
                    <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                    <display:column title="Tarikh"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhEnkuiri}"/></display:column>
                    <display:column title="Masa"><fmt:formatDate pattern="hh:mm aaa" value="${line.tarikhEnkuiri}"/></display:column>
                    <display:column property="tempat" title="Tempat" class="nama${line_rowNum}"/>
                    <display:column property="perkara" title="Perkara" class="nama${line_rowNum}"/>
                    <display:column property="status.nama" style="text-transform: uppercase;" title="Status" class="nama${line_rowNum}"/>
                    <display:column property="ulasanPegawai" title="Ulasan" class="nama${line_rowNum}"/>
                </display:table>
            </div>
        </fieldset>
    </c:if>
    <br>
</c:if>

</s:form>