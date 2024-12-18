<%-- 
    Document   : UtilitiPenangguhanPembatalan
    Created on : Jul 4, 2013, 9:27:02 PM
    Author     : nur.aqilah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    function doSearch() {
        if($('#idPermohonan').val() == ''){
            alert('Sila Masukan Id Permohonan Terlebih Dahulu');            
            return false;
        }
        return true;
    }
    
    function validateTarikh(){
        var date = '${actionBean.tarikhLelongTerdahulu}';
        var dateSplit = date.split('/');
        var tarikhLelongDulu = new Date(dateSplit[0], dateSplit[1]-1, dateSplit[2].substr(0, 2));
        var date2 = $('#tarikhLelong').val();
        var dateSplit2 = date2.split('/');
        var tarikhLelongMasuk = new Date(dateSplit2[2], dateSplit2[1]-1, dateSplit2[0]);
    
        if(date2 < date){
            alert("Tarikh Lelong Yang Dimasukkan Mestilah Melebihi Dari Tarikh Lelong Terdahulu " + date);
            $('#tarikhLelong').val("");
        }else{ 
            var tarikhLelong = new Date(tarikhLelongMasuk);
            tarikhLelong.setDate(tarikhLelong.getDate()+120);
            $('#tarikhBayar1', opener.document).val(tarikhLelong.getDate() + "/" + tarikhLelong.getMonth()+1 + "/" + tarikhLelong.getFullYear());
        }
        
    }
    
    function show_calendar(){
        var idPermohonan = $('#idPermohonan').val();
        window.open("${pageContext.request.contextPath}/lelong/tangguhBatalPermohonan?showFormB&idPermohonan=" + idPermohonan, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1200,height=1200,scrollbars=1");
    }
    function clearForm() {
                
        $("#idPermohonan").val('');
      
    }
    function doViewReport(d) {
        window.open("${pageContext.request.contextPath}/lelong/dokumen/view?view&idDokumen=" + d, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }

    function validation(form) {
        if(form.jenis[0].checked==false&&form.jenis[1].checked==false){
            alert('Sila Pilih Tindakan Pejabat Tanah Dan Daerah');
            return false;
        }
    }
    
    function validation2() { 
        if($('#ulasan').val() == ''){
            alert('Sila Masukkan Ulasan');            
            return false;
        }
        return true;
    }

    function validation3(){
        if($('#datepicker').val()==''){
            alert('Sila Masukkan Tarikh');
            return false;
        }
        if($('#tempat').val()==''){
            alert('Sila Masukkan Tempat');
            return false;
        }
        //jam, minit, ampm
        if($("#jam").val() == "00"){
            alert('Sila Pilih " Jam "');
            $("#jam").focus();
            return false;
        }
        
        if($("#minit").val() == 'null'){
            alert('Sila Pilih " Minit "');
            $("#minit").focus();
            return false;
        }
        if($("#ampm").val() == "00"){
            alert('Sila Pilih " Pagi/Petang "');
            $("#ampm").focus();
            return false;
        }
        return true;
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
    
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }
    
    function clearForm1() {
        $("#hargaRizab").val("");
        $("#deposit").val("");
    } 
    //        $(document).ready(function() {
    //        $("#checkPermohonan").click(function(evt) {
    //            var idPermohonan= $("#idPermohonan").val();
    //            var q = $("form").serialize();
    //            var url = '?' + $(this).attr("name");
    //    
    //            $.post(url, q,function(data) {
    //                if(data =='1'){
    //                    alert("Id Mohon Tidak Wujud");
    //                    
    //                }else if(data =='2'){
    //                    alert("Permohonan - " + idPermohonan + " Tidak Dapat Di Teruskan");
    //                    
    //                }
    //                
    //            });
    //        });
    //    });
</script>

<s:form  beanclass="etanah.view.stripes.lelong.UtilitiPenangguhanPembatalanActionBean">
    <p>
        <s:messages />
        <s:errors />&nbsp;
    </p>

    <div class="subtitle" id ="aa">
        <p class="title open" id="f1">
            Carian
        </p>
        <fieldset class="aras1">
            <br/>

            <p>
                <label> ID Permohonan :</label>
                <s:text name="idPermohonan" id ="idPermohonan" maxlength="20" size="31" onblur="this.value=this.value.toUpperCase();"/>
            </p>
            <br/>
            <p align="center">
                <s:submit name="find" id="checkPermohonan"class="btn"value="Cari" onclick="return doSearch();"/>
                <s:submit name="reset" value="Isi Semula" class="btn" onclick="clearForm();"/>
            </p>

        </fieldset>
        <br/>
    </div>
    <c:if test="${actionBean.showForm eq true}">
        <c:if test="${fn:length(actionBean.senaraiPermohonan) > 0}">
            <div class="subtitle">
                <p class="title open" id="f1">
                    Senarai Perserahan
                </p>
                <fieldset class="aras1">
                    <p align="center">
                        <label></label>
                        <c:set var="row_num" value="${actionBean.__pg_start}"/>
                        <display:table class="tablecloth" name="${actionBean.senaraiPermohonan}"
                                       cellpadding="0" cellspacing="0" id="line"
                                       requestURI="/lelong/tangguhBatalPermohonan"
                                       pagesize="10"                                   
                                       sort="external" size="${actionBean.__pg_total_records}" partialList="true">
                            <c:set var="row_num" value="${row_num+1}"/>
                            <display:column title="Bil">${row_num}</display:column>
                            <display:column title="ID Permohonan" style="vertical-align:top;" >
                                <s:link beanclass="etanah.view.stripes.lelong.UtilitiPenangguhanPembatalanActionBean"
                                        event="checkPermohonan" >
                                    <s:param name="idPermohonan" value="${line.idPermohonan}"/> ${line.idPermohonan}
                                </s:link>
                            </display:column>

                            <display:column property="kodUrusan.kod" title="Kod Urusan"/>
                            <display:column property="kodUrusan.nama" title="Urusan"/>
                            <display:column title="Tarikh Perserahan">
                                <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="d/MM/yyyy hh:mm:ss"/>
                            </display:column>
                        </display:table>
                    </p>
                    <br/>                
                </fieldset>
            </div>
        </c:if>
        <c:if test="${fn:length(actionBean.senaraiPermohonanCarian) > 0}">
            <div class="subtitle">
                <p class="title open" id="f1">
                    Senarai Senarai Carian
                </p>
                <fieldset class="aras1">
                    <p align="center">
                        <label></label>
                        <c:set var="row_num" value="${actionBean.__pg_start}"/>
                        <display:table class="tablecloth" name="${actionBean.senaraiPermohonanCarian}"
                                       cellpadding="0" cellspacing="0" id="line"
                                       requestURI="/lelong/tangguhBatalPermohonan"
                                       pagesize="10"
                                       sort="external" size="${actionBean.__pg_total_records}" partialList="true">
                            <c:set var="row_num" value="${row_num+1}"/>
                            <display:column title="Bil">${row_num}</display:column>
                            <display:column title="ID Permohonan" style="vertical-align:top;" >
                                <s:link beanclass="etanah.view.stripes.lelong.UtilitiPenangguhanPembatalanActionBean"
                                        event="checkPermohonan" >
                                    <s:param name="idPermohonan" value="${line.idPermohonan}"/> ${line.idPermohonan}
                                </s:link>
                            </display:column>
                            <display:column property="urusan.kod" title="Kod Urusan"/>
                            <display:column property="urusan.nama" title="Urusan"/>
                            <display:column title="Tarikh Perserahan">
                                <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="d/MM/yyyy hh:mm:ss"/>
                            </display:column>
                        </display:table>
                    </p>
                    <br/>
                </fieldset>
            </div>
        </c:if>
    </c:if>
    <c:if test="${actionBean.showTangguhBatal eq true}">
        <div class="subtitle" id ="aa">
            <p class="title open" id="f1">
                Tindakan Pejabat Tanah Dan Daerah
            </p>
            <fieldset class="aras1">
                <br/>
                <c:if test="${actionBean.showRadio eq false}">
                <p>
                    <label><font color="red">*</font>Tindakan : </label>
                    <s:radio name="jenis" id="jenisPermohonan" value="BP"/> Pembatalan Oleh Pentadbir Tanah&nbsp;&nbsp;
                    <s:radio name="jenis" id="jenisPermohonan" value="TG"/> Penangguhan Oleh Pentadbir Tanah
                </p><br>
                </c:if>
                <c:if test="${actionBean.showRadio eq true}">
                    <p>
                        <label><font color="red">*</font>Tindakan : </label>
                        <s:radio name="jenis" id="jenisPermohonan" value="BP"/> Pembatalan Oleh Pentadbir Tanah&nbsp;&nbsp;
                        <s:radio name="jenis" id="jenisPermohonan" value="TG" disabled="disabled"/><font color="grey"> Penangguhan Oleh Pentadbir Tanah </font>
                    </p><br>
                </c:if>

                <br/>
                <p align="center"><s:submit name="jenisPermohonan" value="Simpan" class="btn" onclick="return validation(this.form)"/></p>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${actionBean.tangguh eq true}">
        <div class="subtitle" id ="aa">
            <p class="title open" id="f1">
                Maklumat Perintah Jualan
            </p>
            <fieldset class="aras1">
                <c:if test="${actionBean.lelongan.bil ne null}">
                    <p>
                        <label>Status lelongan : </label>
                        <c:if test="${actionBean.lelongan.bil eq '1'}">
                            Kali Pertama
                        </c:if>
                        <c:if test="${actionBean.lelongan.bil eq '2'}">
                            Kali Kedua
                        </c:if>
                        <c:if test="${actionBean.lelongan.bil eq '3'}">
                            Kali Ketiga
                        </c:if>
                    </p><br>
                </c:if>
                <p>
                    <c:set value="1" var="dat"/>
                    <label><font color="red">*</font>Tarikh Lelongan Awam :</label>
                    <s:text name="tarikhLelong" id="datepicker" onclick="show_calendar();" formatPattern="dd/MM/yyyy"/>&nbsp;
                    <font color="red" size="1">(cth : hh / bb / tttt)</font>
                    &nbsp;
                </p>
                <p>
                    <label><font color="red">*</font>Masa Lelongan Awam :</label>
                    <s:select id="jam" name="jam" style="width:56px">
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
                    <s:select name="minit" id="minit" style="width:65px">
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
                    <s:select name="ampm" id="ampm"style="width:80px">
                        <s:option value="00">Pilih</s:option>
                        <s:option value="AM">Pagi</s:option>
                        <s:option value="PM">Petang</s:option>
                    </s:select>
                <p>
                    <label><font color="red">*</font> Hari :</label>
                    <s:text id="hari" name="lelongan.tarikhLelong" disabled="true" formatPattern="EEEE" />
                </p>
                <p>
                    <label><font color="red">*</font> Tempat :</label>
                    <s:textarea id="tempat" name="lelongan.tempat" style="text-transform:uppercase;" onchange="this.value=this.value.toUpperCase();" cols="50" rows="5"/>
                </p>
                <p>
                    <label>Tarikh Akhir Bayaran : </label>
                    <s:text id="tarikhBayar1" name="tarikhAkhirBayaran" formatPattern="dd/MM/yyyy" readonly="true" />&nbsp;(120 hari dari tarikh lelongan)
                </p>
                <br>
                <%--MLK--%>
                <c:if test="${actionBean.melaka eq true}">
                    <c:set var="i" value="0" />
                    <p>
                        <label>Jumlah Keseluruhan Hutang : </label>RM

                        <s:text id="amaunTung" name="amaunTunggakan" readonly="true" style="width:150px;" formatPattern="###,###,###.00" onblur="changeFormat2(this.value);" onkeyup="validateNumber(this,this.value);"/>&nbsp;                        
        <!--                    <img src="${pageContext.request.contextPath}/pub/images/tambah.png" height="15" width="15" alt="Tambah Jumlah Hutang"
                             onmouseover="this.style.cursor='pointer';" onclick="addRow();return false;" title="Tambah Jumlah Hutang">-->

                    </p>
                    <c:if test="${actionBean.amaunTunggakan2 ne null}">
                        <p>
                            <label>&nbsp;</label>RM
                            <s:text id="amaunTung2" name="amaunTunggakan2" readonly="true" style="width:150px;" formatPattern="###,###,###.00" onblur="changeFormat2(this.value);"/>&nbsp;
        <!--                            <img src="${pageContext.request.contextPath}/images/not_ok.gif" height="15" width="15" alt="Klik Untuk Hapus"
                                 onmouseover="this.style.cursor='pointer';" onclick="deleteRow3('${line.idEnkuiriPeminjam}');return false;"  title="Klik Untuk Hapus">-->
                        </p>
                    </c:if>
                        
                    <table id ="add" border="0"></table>
                    <c:set var="i" value="${i+1}" />
                    <s:hidden name="rowCount3"  id="rowCount3"/>
                </c:if>
                <%--N9--%>
                <c:if test="${actionBean.melaka eq false}">
                    <c:set var="i" value="0" />
                    <p>
                        <label>Jumlah Keseluruhan Hutang : </label>RM
                        <s:text id="amaunTung" name="amaunTunggakan" onkeyup="validateNumber(this,this.value);" onchange="calc(${line_rowNum},this.value);" onblur="validate(${line_rowNum},this.value)" formatPattern="###,###,###.00"/>&nbsp;                        
                    </p>
                    <table id ="add" border="0"></table>
                    <c:set var="i" value="${i+1}" />
                    <s:hidden name="rowCount3"  id="rowCount3"/>
                </c:if>
                
                <br>    
                    <p>
                        <label><font color="red">* </font>Pegawai Yang Menandatangani :</label>
                        <s:select name="idPengguna" style="width:250px"  id="pengguna">
                            <s:option value="">-Sila Pilih-</s:option>
                            <s:options-collection collection="${actionBean.senaraiPT}" label="nama" id="pengguna" value="idPengguna" />
                        </s:select>
                        &nbsp;
                    </p>
                        
                <div class="content" align="center">
                    <c:if test="${same eq false}">
                        <display:table class="tablecloth" name="${actionBean.listLel2}" id="line">
                            <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                            <display:column title="IDHakmilik" property="hakmilikPermohonan.hakmilik.idHakmilik" />
                            <display:column title="Harga Rizab" >
                                RM <s:text name="listLel2[${line_rowNum - 1}].hargaRizab" id="hargaRizab${line_rowNum}" readonly="true" onkeyup="validateNumber(this,this.value);" onchange="calc(${line_rowNum},this.value);" onblur="validate(${line_rowNum},this.value)" formatPattern="###,###,###.00">
                                </s:text>
                            </display:column>
                            <display:column title="Deposit">
                                RM <s:text name="listLel2[${line_rowNum - 1}].deposit" value="${line.deposit}"id="deposit${line_rowNum}" readonly="true" formatPattern="###,###,###.00"/>
                            </display:column>
                        </display:table>
                    </c:if>
                    <c:if test="${same eq true}">
                        <display:table class="tablecloth" name="${actionBean.listLelongan}" id="line">
                            <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                            <display:column title="IDHakmilik" value="${actionBean.idHak}"/>
                            <display:column title="Harga Rizab" >
                                RM <s:text name="lelongan.hargaRizab" id="hargaRizab${line_rowNum}" onkeyup="validateNumber(this,this.value);" onchange="calc(${line_rowNum},this.value);" onblur="validate(${line_rowNum},this.value)" formatPattern="###,###,###.00">
                                    ${actionBean.lelongan.hargaRizab}
                                </s:text>
                            </display:column>
                            <display:column title="Deposit">
                                RM <s:text name="lelongan.deposit" id="deposit${line_rowNum}" readonly="true" formatPattern="###,###,###.00">
                                    ${actionBean.lelongan.deposit}
                                </s:text>
                            </display:column>
                        </display:table>
                    </c:if>
                    <br/>
                    <c:if test="${fn:length(actionBean.sejarahEnkuiri)>0}">
                        <fieldset class="aras1">
                            <legend>
                                Sejarah Siasatan
                            </legend>
                            <div class="content" align="center">
                                <display:table class="tablecloth" name="${actionBean.sejarahEnkuiri}" id="line">
                                    <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                                    <display:column title="Tarikh"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhEnkuiri}"/></display:column>
                                    <display:column title="Masa"><fmt:formatDate pattern="hh:mm aaa" value="${line.tarikhEnkuiri}"/></display:column>
                                    <display:column property="tempat" title="Tempat" class="nama${line_rowNum}"/>
                                    <display:column property="perkara" title="Perkara" class="nama${line_rowNum}"/>
                                    <display:column property="status.nama" title="Status" class="" style="text-transform:uppercase;"/>
                                    <display:column property="ulasanPegawai" title="Ulasan" class="nama${line_rowNum}"/>
                                </display:table>
                            </div>
                        </fieldset>
                    </c:if>
                    <c:if test="${fn:length(actionBean.sejarahLelongan)>0}">
                        <fieldset class="aras1">
                            <legend>
                                Sejarah Lelongan
                            </legend>
                            <div class="content" align="center">
                                <display:table class="tablecloth" name="${actionBean.sejarahLelongan}" id="line" >
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
                                </display:table>
                            </div>
                        </fieldset>
                    </c:if>
                    <br/>
                    <p align="center">
                        <%--berasingan--%>
                        <c:if test="${same eq false}">
                            <s:submit name="simpanLelong" id="save" value="Simpan" class="btn" onclick="return validation3();"/>
                        </c:if>
                        <%--bersama--%>
                        <c:if test="${same eq true}">
                            <s:submit name="saveBersama" id="save" value="Simpan" class="btn" onclick="return validation3();"/>
                        </c:if>
                        <c:if test="${actionBean.view2 eq true}">
                            <s:button name="" value="Cetak" class="btn" onclick="doViewReport(${actionBean.idDokumen});return false;"/>
                        </c:if>
                    </p>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${actionBean.batal eq true}">
        <div class="subtitle" id ="aa">
            <p class="title open" id="f1">
                Maklumat Keputusan
            </p>
            <fieldset class="aras1">
                <br/>
                <p>
                    <label>Ulasan Oleh Pentadbir Tanah : </label>
                    <s:textarea name="ulasan" id="ulasan" onchange="this.value=this.value.toUpperCase();" cols="60" rows="8" style="text-transform:uppercase;"/>
                </p>
                <br/>
                <p align="center">
                    <s:submit name="simpanBatal" id="save" value="Simpan" class="btn" onclick="return validation2();"/>
                    <c:if test="${actionBean.view eq true}">
                        <s:button name="" value="Cetak" class="btn" onclick="doViewReport(${actionBean.idDokumen});return false;"/>
                    </c:if>
                </p>
            </fieldset>
        </div>
    </c:if>
</s:form>
