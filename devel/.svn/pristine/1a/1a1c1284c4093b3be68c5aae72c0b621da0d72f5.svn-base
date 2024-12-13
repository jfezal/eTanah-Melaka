<%-- 
    Document   : UtilitiKemasukanPerintahMahkamah
    Created on : Jan 7, 2013, 5:59:30 PM
    Author     : mazurahayati.yusop, nur.aqilah
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.text.*,java.util.*" session="true"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>

<script type="text/javascript">


    $( document ).ready(function() {
        $('#lain').hide();
        $('#lain2').hide();
        $('#lain3').hide();
        $('#enkuiriB').hide();
        $('#enkuiriA').hide();
        $('#selesai').hide();
   
    });
     
     var DELIM = "__^$__";
    function simpanPihak(event, f){
        if(validate()){

        }
        else{
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.get(url,q,
            function(data){
                if (data == null || data.length == 0){
                    alert("Maklumat tidak dijumpai");
                    return;
                }
                var p = data.split(DELIM);
                $('#idSyarikat', opener.document).append('<option value="'+p[2]+'">'+p[0]+'</option>');
                $('#idSyarikat option[value='+p[2]+']', opener.document).attr('selected','selected');
                $('#noSykt',opener.document).val(p[1]);
            },'html');
        }
    }
    
    function doViewReport(d) {
        window.open("${pageContext.request.contextPath}/lelong/dokumen/view?view&idDokumen=" + d, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }
    
    function changeLelong(value){
        if(value=="EM"){
            $('#lain').show();
            $('#selesai').show();
            $('#lain2').hide();
            $('#lain3').hide();
            $('#enkuiriB').hide();
            $('#enkuiriA').hide();
        }  
        if(value=="LM"){
            $('#lain2').show();
            $('#selesai').show();
            $('#lain').hide();
            $('#lain3').hide();
            $('#enkuiriB').hide();
            $('#enkuiriA').hide();
        }
        if(value=="C"){
            $('#lain3').show();
            $('#selesai').hide();
            $('#lain').hide();
            $('#lain2').hide();
            $('#enkuiriB').hide();
            $('#enkuiriA').hide();
        }
    }
    
    function returnvalidate(value){
        if(value == 'B'){
            $('#lain').show();
            $('#lain2').hide();
            $('#lain3').hide();
            $('#enkuiriB').show();
            $('#enkuiriA').hide();
        }
        if(value == 'A'){
            $('#lain').hide();
            $('#lain2').show();
            $('#lain3').hide();
            $('#enkuiriB').hide();
            $('#enkuiriA').show();
        }
    }
    
   
    function addCatatan(idPermohonan){
        $.post('utiliti_kemasukan_perintah_mahkamah1?saveMohonCatatan&idPermohonan='+idPermohonan,$("#form").serialize(), function(data) {});
    }
    
    function createNewRow(value){
        $.get('utiliti_kemasukan_perintah_mahkamah1?rowNew',$("#form").serialize(), function(data) {
            $("#listapeape").replaceWith($('#listapeape', $(data)));         
            $("#sejarahEnkuiri").replaceWith($('#sejarahEnkuiri', $(data)));
            $("#sejarahLelong").replaceWith($('#sejarahLelong', $(data)));
            changeLelong(value)
        });
    }
    
    //    function createNewRow(value){
    //        $.get('utiliti_kemasukan_perintah_mahkamah1?test',$("#form").serialize(), function(data) {          
    //            alert('here');
    //            $.get('utiliti_kemasukan_perintah_mahkamah1?getlistlel',$("#form").serialize(), function(data) {
    //                $("#klulain3").html(data);
    //            });
    //        });
    //    }
    
    //    function addCatatan(v){
    //        window.open("${pageContext.request.contextPath}lelong/utiliti_kemasukan_perintah_mahkamah1?saveMohonCatatan&idPermohonan=" + v, "eTanah",
    //        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    //    }
    
    function show_calendar(){
        window.open("${pageContext.request.contextPath}/lelong/utiliti_kemasukan_perintah_mahkamah1?showForm1", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1200,height=1200,scrollbars=1");
    }
    
    function show_calendarLelong(){
        window.open("${pageContext.request.contextPath}/lelong/utiliti_kemasukan_perintah_mahkamah1?showForm2", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1200,height=1200,scrollbars=1");
    }

    function validation() {
        if($("#datepicker").val() == ""){
            alert('Sila masukkan " Tarikh Enkuiri "');
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

        if($("#hari").val() == ""){
            alert('Sila Masukkan " Hari "');
            $("#hari").focus();
            return false;
        }
        
        if($("#tempat").val() == ""){
            alert('Sila Masukkan " Tempat "');
            $("#tempat").focus();
            return false;
        }
        
        if($("#catatan").val() == ""){
            alert('Sila Masukkan " Ulasan PTD "');
            $("#catatan").focus();
            return false;
        }
        return true;
    }
    
    function validationLelong() {
       
        if($("#datepicker1").val() == ""){
            alert('Sila masukkan " Tarikh Lelongan "');
            $("#datepicker1").focus();
            return false;
        }
        if($("#jam1").val() == "00"){
            alert('Sila Pilih " Jam "');
            $("#jam1").focus();
            return false;
        }
        
        if($("#minit1").val() == "null"){
            alert('Sila Pilih " Minit "');
            $("#minit1").focus();
            return false;
        }
        if($("#ampm1").val() == "0"){
            alert('Sila Pilih " Pagi/Petang "');
            $("#ampm1").focus();
            return false;
        }

        if($("#hari1").val() == ""){
            alert('Sila Masukkan " Hari "');
            $("#hari").focus();
            return false;
        }
        if($("#tempat1").val() == ""){
            alert('Sila Masukkan " Tempat "');
            $("#tempat1").focus();
            return false;
        }
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
        alert ("date");
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
    
    function clearForm() {
        $("#idPermohonan").val('');
        $("#idHakmilik").val('');
    } 
    
   
</script>

<s:form beanclass="etanah.view.stripes.lelong.UtilitiKemasukanPerintahMahkamahActionBean" id="form">
    <s:messages />
    <s:errors />
    <s:hidden name="idPermohonan" value="${actionBean.idPermohonan}"/>
    <s:hidden name="kpsn"/>
    <div class="subtitle">
        <p class="title open" id="f1">
            Carian 
        </p>
        <fieldset class="aras1">
            <p>
                <label> ID Permohonan :</label>
                <s:text name="idPermohonan" maxlength="20" size="31" onblur="this.value=this.value.toUpperCase();" id="idPermohonan"/>
                <s:submit name="checkPermohonan" value="Cari" class="btn" onclick="doSearch(this.name, this.form);"/></p>
            <p align="center"><font color="red">ATAU</font></p>
            <p>
                <label> ID Hakmilik :</label>
                <s:text name="idHakMilik" maxlength="20" size="31" onblur="this.value=this.value.toUpperCase();" id="idHakmilik"/>
                <s:submit name="checkIdHakMilik" value="Cari" class="btn"/>
            </p>

            <div class="content" align="right">
                <p>
                    <s:submit name="reset" value="Isi Semula" class="btn" onclick="clearForm();"/>
                </p>
            </div>
        </fieldset>
    </div>
    <br>
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
                                       requestURI="/lelong/utiliti_kemasukan_perintah_mahkamah1"
                                       pagesize="10"                                   
                                       sort="external" size="${actionBean.__pg_total_records}" partialList="true">
                            <c:set var="row_num" value="${row_num+1}"/>
                            <display:column title="Bil">${row_num}</display:column>
                            <display:column title="ID Permohonan" style="vertical-align:top;" >
                                <s:link beanclass="etanah.view.stripes.lelong.UtilitiKemasukanPerintahMahkamahActionBean"
                                        event="carian" >
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
                                       requestURI="/lelong/utiliti_kemasukan_perintah_mahkamah1"
                                       pagesize="10"
                                       sort="external" size="${actionBean.__pg_total_records}" partialList="true">
                            <c:set var="row_num" value="${row_num+1}"/>
                            <display:column title="Bil">${row_num}</display:column>
                            <display:column title="ID Permohonan" style="vertical-align:top;" >
                                <s:link beanclass="etanah.view.stripes.lelong.UtilitiKemasukanPerintahMahkamahActionBean"
                                        event="carian" >
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
    <%--<c:if test="${actionBean.enkuiriForm eq true}">--%> 
    <c:if test="${actionBean.view eq true}">
        <c:if test="${actionBean.view eq true}">
            <div class="subtitle" align="center">
                <p class="title open" id="f1" align="center">
                    <c:if test="${actionBean.idPermohonan ne null}">
                        ID Permohonan : <font color="black">${actionBean.idPermohonan}</font>  <br/> 
                    </c:if>
                    <c:if test="${actionBean.mohonrl ne null}">
                        ID Permohonan : <font color="black">${actionBean.mohonrl}</font>  <br/>    
                    </c:if>
                    <c:if test="${actionBean.idHakmilik ne null}">
                        ID Hakmilik : 
                        <font color="black">${actionBean.idHakmilik}</font>
                    </c:if>
                </p>
                <br/>
                <fieldset class="aras1">
                    <br/>
                    <display:table class="tablecloth" name="${actionBean.addListHakMilikUrusan}" id="line">
                        <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                        <c:if test="${actionBean.addCheckUrusan ne null}">
                            <c:forEach items="${actionBean.addCheckUrusan}" var="line2" >
                                <c:if test="${line.hakmilik.idHakmilik eq line2.hakmilik.idHakmilik}">
                                    <display:column title="Urusan">${line2.kodUrusan.kod} - ${line2.kodUrusan.nama}</display:column>
                                </c:if>
                            </c:forEach>
                        </c:if>
                        <display:column title="Nama Sidang" property="namaSidang" />
                        <display:column title="Tarikh Sidang"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhSidang}"/></display:column>
                        <display:column title="No Fail" property="noFail" />
                        <display:column title="Ulasan" property="ulasan" />
                        <c:if test="${line.permohonan.kodUrusan.kod eq 'PMHUK' || line.permohonan.kodUrusan.kod eq 'PMHUN'}">
                            <display:column title="Tempoh">
                                ${actionBean.tempoh}
                            </display:column>
                        </c:if>
                        <c:forEach items="${actionBean.addCheckUrusan}" var="line2" >
                            <c:if test="${line.hakmilik.idHakmilik eq line2.hakmilik.idHakmilik}">
                                <c:if test="${line2.kodUrusan.kod eq 'PMHUK' || line2.kodUrusan.kod eq 'PMHUN'}">
                                    <display:column title="Tempoh">
                                        ${actionBean.tempoh}
                                    </display:column>
                                </c:if>
                            </c:if>
                        </c:forEach>
                        <c:forEach items="${actionBean.tarikhPerserahan}" var="line3">
                            <display:column title="Id Perserahan">
                                ${line3.idPermohonan}
                            </display:column>
                            <display:column title="Tarikh Perserahan">
                                <fmt:formatDate value="${line3.infoAudit.tarikhMasuk}" pattern="d/MM/yyyy hh:mm:ss"/>
                            </display:column>
                        </c:forEach>
                    </display:table>
                    <br/>
                </fieldset>
            </div>
        </c:if>

        <br>
        <c:if test="${actionBean.permohonan.status ne 'SD'&& actionBean.permohonan.status ne 'SL'&& actionBean.permohonan.status ne 'BP' && actionBean.permohonan.status ne 'MK' && actionBean.permohonan.status ne 'RM'}"> 
            <p align="center">
                <s:submit name="aktif" id="save" value="Gantung" class="longbtn" />
            </p>
        </c:if>
    </c:if>
    <br>
    <br>      

    <c:if test="${actionBean.permohonan.status eq 'SD'}">
        <div class="subtitle" id="option">
            <font size="2" color="red">*</font><em><font size="2" color="red">Sila Masukkan Perintah Mahkamah</font></em><br><br>
            <p>
                <label>Perintah Mahkamah :</label>
                <s:select id="" name="kpsn" style="width:300px;" onchange="javaScript:createNewRow(this.value);">
                    <s:option value="">-Sila Pilih-</s:option>
                    <s:option value="EM">Siasatan Semula</s:option>
                    <s:option value="LM">Lelongan Semula</s:option>
                    <s:option value="C">Membatalkan Permohonan Perintah Jualan</s:option>
                </s:select>
            </p>
        </div> <br><br>

        <p>
            <label><font color="red">*</font>Ulasan PTD: </label>
            <s:textarea name="permohonan.catatan" rows="5" cols="48" id="catatan" onblur="this.value=this.value.toUpperCase();" />
        </p><br>

        <div class="subtitle" id="lain3">
            <fieldset class="aras1">
                <%--<c:if test="${actionBean.enkuiri.caraLelong eq 'B'}">--%>
                <p align="center">
                    <s:submit name="simpanButtonBatal" id="save" value="Batal" class="longbtn"/>
                </p>
                <%--</c:if>--%>
                <%--<c:if test="${actionBean.enkuiri.caraLelong eq 'A'}">
                   <p align="center">
                       <s:submit name="simpanButtonBatalAsing" id="save" value="Batal" class="longbtn"/>
                   </p>
               </c:if>--%>
            </fieldset>
        </div>
    </c:if>

    <c:if test="${actionBean.permohonan.status eq 'AK'}">    
        <c:if test="${actionBean.tarikhPerintah ne null}">
            <p align="center">
                <s:submit name="simpanButtonAktif" id="save" value="Aktif" class="longbtn" disabled="true"/>
            </p>
        </c:if>
    </c:if>
    <div class="subtitle">
        <div id="lain">       
            <fieldset class="aras1">
                <legend>
                    Maklumat Siasatan
                </legend>
                <div class="content">
                    <p>
                        <label><font color="red">*</font>Tarikh :</label>
                        <s:text name="tarikhEnkuiri" id="datepicker" onclick="show_calendar();"/>&nbsp;
                        <%--<a href="javascript:show_calendar();">
                            <img src="${pageContext.request.contextPath}/pub/images/calendar.gif" id="" width=15 height=15 /></a>--%>
                        <font color="red" size="1">(cth : hh / bb / tttt)</font>
                        &nbsp;
                    </p>

                    <p>
                        <label><font color="red">*</font>Masa :</label>
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
                        <s:select id="minit" name="minit" style="width:65px">
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

                        <s:select id="ampm" name="ampm" style="width:80px">
                            <s:option value="0">Pilih</s:option>
                            <s:option value="AM">Pagi</s:option>
                            <s:option value="PM">Petang</s:option>
                        </s:select>

                    <p>
                        <label><font color="red">*</font> Hari :</label>
                        <s:text id="hari" name="tarikhEnkuiri" disabled="true" formatPattern="EEEE" />
                    </p>

                    <p>
                        <label><font color="red">*</font> Tempat :</label>
                        <s:textarea id="tempat" name="tempat" cols="50" rows="5" onblur="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> Perkara :</label>
                        <s:textarea id="perkara" name="perkara" cols="50" rows="5" onblur="this.value=this.value.toUpperCase();" />
                    </p>
                </div>
            </fieldset>
            <div class="content" align="center">
                <p>
                    <s:submit name="simpanEnkuiri" id="save" value="Simpan" class="btn" onclick="return validation();"/>
                </p>

            </div>

            <div id="sejarahEnkuiri">
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
                <%--</c:if>--%>
            </div>
        </div>
    </div>
    <br>
    <%--</fieldset>--%>
    <%--</c:if>--%>
    <div class="subtitle" id="lain2">
        <fieldset class="aras1">
            <div class="content">
                <p>
                    <c:set value="1" var="dat"/>
                    <label><font color="red">*</font>Tarikh Lelongan Awam:</label>
                    <s:text name="tarikhLelong" id="datepicker1" onclick="show_calendarLelong();"  />&nbsp;
                    <font color="red" size="1">(cth : hh / bb / tttt)</font>
                    &nbsp;
                </p>
                <p>
                    <label><font color="red">*</font>Masa Lelongan Awam :</label>
                    <s:select name="jam1" style="width:56px" id="jam1">
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
                    <s:select name="minit1" style="width:65px" id="minit1">
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

                    <s:select name="ampm1" style="width:80px" id="ampm1">
                        <s:option value="0">Pilih</s:option>
                        <s:option value="AM">Pagi</s:option>
                        <s:option value="PM">Petang</s:option>
                    </s:select>

                <p>
                    <label><font color="red">*</font> Hari :</label>
                    <s:text id="hari1" name="tarikhLelong" disabled="true" formatPattern="EEEE" />
                </p>

                <p>
                    <label><font color="red">*</font> Tempat :</label>
                    <s:textarea id="tempat1" name="tempat1" cols="50" rows="5" onblur="this.value=this.value.toUpperCase();" />
                </p>

                <p>
                    <label>Tarikh Akhir Bayaran : </label>
                    <s:text id="tarikhBayar1" name="tarikhAkhirBayaran" formatPattern="dd/MM/yyyy" readonly="false" />&nbsp;(120 hari dari tarikh lelongan)
                </p>
                <br>
                
                <p>
                    <label><font color="red">*</font>Jumlah Keseluruhan Hutang : </label>RM
                    <s:text id="amaunTung" name="amaunTunggakan" formatPattern="###,###,###.00" onblur="changeFormat2(this.value);" onkeyup="validateNumber(this,this.value);"/>&nbsp;
                </p>

                <br>
               
                    <p>
                        <label><font color="red">* </font>Pegawai Yang Menjatuhkan Perintah :</label>
                        <s:select name="idPengguna" style="width:250px"  id="pengguna">
                            <s:option value="">-Sila Pilih-</s:option>
                            <s:options-collection collection="${actionBean.senaraiPT}" label="nama" id="pengguna" value="idPengguna" />
                        </s:select>
                        &nbsp;
                    </p>
                        
<!--                   <p>
                        <label><font color="red">* </font>Pegawai Yang Menjatuhkan Perintah :</label>
                        <s:select name="idPengguna" style="width:250px"  id="pengguna">
                            <s:option id="pengguna" value="">-Sila Pilih-</s:option>
                            <s:option value="mtpptlelong1">Mohd Firdaus</s:option>
                            <s:option value="rauidah">Rauidah binti Rahmat</s:option>
                        </s:select>
                        &nbsp;
                    </p>-->
                 
                <br>
                <div class="content" align="center">
                    <div class="content" align="center">
                        <div id="listapeape">
                            <div id="klulain3">
                                <c:if test="${same eq false}">
                                    <display:table class="tablecloth" name="${actionBean.listLel}" id="line1">
                                        <display:column title="Bil" sortable="true" >${line1_rowNum}</display:column>
                                        <display:column title="IDHakmilik" property="hakmilikPermohonan.hakmilik.idHakmilik" />
                                        <display:column title="Harga Rizab" >
                                            RM <s:text name="hargaRizab${line1_rowNum - 1}" value="${line1.hargaRizab}" id="hargaRizab${line1_rowNum}" onkeyup="validateNumber(this,this.value);" onchange="calc(${line1_rowNum},this.value);" onblur="validate(${line1_rowNum},this.value)" formatPattern="###,###,###.00">
                                            </s:text>
                                        </display:column>
                                        <display:column title="Deposit">
                                            RM <s:text name="deposit${line1_rowNum - 1}" value="${line1.deposit}" id="deposit${line1_rowNum}" readonly="true" formatPattern="###,###,###.00">
                                            </s:text>
                                        </display:column>
                                    </display:table>
                                </c:if>
                            </div>
                    
                            <c:if test="${same eq true}">
                                <display:table class="tablecloth" name="${actionBean.listLelongan}" id="line">
                                    <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                                    <display:column title="IDHakmilik" value="${actionBean.idHak1}"/>
                                    <display:column title="Harga Rizab" >
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
                    </div>
                </div>

            </div>
            <%--berasingan--%>
            <c:if test="${same eq false}">
                <p align="center">
                    <%--melaka--%>
                    <s:submit name="simpanLelong" id="save" value="Simpan Dan Jana" class="longbtn" onclick="return validationLelong();"/>
                </p>
            </c:if>

            <%--bersama--%>
            <c:if test="${same eq true}">
                <p align="center">
                    <s:submit name="saveBersama" id="save" value="Simpan Dan Jana" class="longbtn" onclick="return validationLelong();"/>

                </p>
            </c:if>
        </fieldset>

        <div id="sejarahLelong">
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
        </div>
    </div>
    <c:if test="${actionBean.showSelesai eq true}">
        <c:if test="${actionBean.showLelongan eq true}">
            <div class="content">
                <p>
                    <c:set value="1" var="dat"/>
                    <label><font color="red">*</font>Tarikh Lelongan Awam:</label>
                    <s:text name="tarikhLelong" id="datepicker1" />&nbsp;
                    <font color="red" size="1">(cth : hh / bb / tttt)</font>
                    &nbsp;
                </p>
                <p>
                    <label><font color="red">*</font>Masa Lelongan Awam :</label>
                    <s:select name="jam1" style="width:56px" id="jam1">
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
                    <s:select name="minit1" style="width:65px" id="minit1">
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

                    <s:select name="ampm1" style="width:80px" id="ampm1">
                        <s:option value="0">Pilih</s:option>
                        <s:option value="AM">Pagi</s:option>
                        <s:option value="PM">Petang</s:option>
                    </s:select>

                <p>
                    <label><font color="red">*</font> Tempat :</label>
                    <s:textarea id="tempat1" name="tempat1" cols="50" rows="5" onblur="this.value=this.value.toUpperCase();" />
                </p>

                <p>
                    <label>Tarikh Akhir Bayaran : </label>
                    <s:text id="tarikhBayar1" name="tarikhAkhirBayaran" formatPattern="dd/MM/yyyy" readonly="false" />&nbsp;(120 hari dari tarikh lelongan)
                </p>
                <br>

                <p>
                    <label><font color="red">*</font>Jumlah Keseluruhan Hutang : </label>RM
                    <s:text id="amaunTung" name="amaunTunggakan" readonly="true" formatPattern="###,###,###.00" onblur="changeFormat2(this.value);" onkeyup="validateNumber(this,this.value);"/>&nbsp;
                </p><br>                
				
				<p>
                        <label><font color="red">* </font>Pegawai Yang Menjatuhkan Perintah :</label>
                        <s:select name="idPengguna" style="width:250px"  id="pengguna">
                            <s:option value="">-Sila Pilih-</s:option>
                            <s:options-collection collection="${actionBean.senaraiPT}" label="nama" id="pengguna" value="idPengguna" />
                        </s:select>
                        &nbsp;
                </p>
<!--                <p>
                    <label><font color="red">* </font>Pegawai Yang Menjatuhkan Perintah :</label>
                    <s:select name="idPengguna" style="width:250px"  id="pengguna">
                        <s:option id="pengguna" value="">-Sila Pilih-</s:option>
                        <s:options-collection collection="${actionBean.senaraiPT}" label="nama" id="pengguna" value="idPengguna" />
                    </s:select>
                    &nbsp;
                </p><br>
                -->
                <div class="content" align="center">

                    <c:if test="${same eq false}">
                        <display:table class="tablecloth" name="${actionBean.listLel}" id="line1">
                            <display:column title="Bil" sortable="true" >${line1_rowNum}</display:column>
                            <display:column title="IDHakmilik" property="hakmilikPermohonan.hakmilik.idHakmilik" />
                            <display:column title="Harga Rizab" >
                                RM <s:text name="hargaRizab${line1_rowNum - 1}" value="${line1.hargaRizab}" id="hargaRizab${line1_rowNum}" onkeyup="validateNumber(this,this.value);" onchange="calc(${line1_rowNum},this.value);" onblur="validate(${line1_rowNum},this.value)" formatPattern="###,###,###.00">
                                </s:text>
                            </display:column>
                            <display:column title="Deposit">
                                RM <s:text name="deposit${line1_rowNum - 1}" value="${line1.deposit}" id="deposit${line1_rowNum}" readonly="true" formatPattern="###,###,###.00">
                                </s:text>
                            </display:column>
                        </display:table>
                    </c:if>

                    <c:if test="${same eq true}">
                        <display:table class="tablecloth" name="${actionBean.listLelongan}" id="line">
                            <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                            <display:column title="IDHakmilik" value="${actionBean.idHak1}"/>
                            <display:column title="Harga Rizab" >
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
            </div>
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

            <fieldset class="aras1">
                <div class="content" align="center">
                    <%--<c:if test="${actionBean.negori eq true}">--%>
                    <s:submit name="" value="Papar Borang 16 H" class="btn" onclick="doViewReport(${actionBean.idDokumen});return false;"/>
                    <%--</c:if>--%>
                    <%--<s:button name="" onclick="cetakBil(this.form)" value="Cetak Bil" id="bil" class="btn" style="display:${actionBean.btn2}"/>--%>
                    <%--<s:button name="" value="Jana Surat Warta" class="btn" onclick="doViewReport(${actionBean.idDokumenWarta});return false;"/>--%>
                </div>
            </fieldset>
        </c:if>

        <c:if test="${actionBean.showEnkuiri eq true}">
            <fieldset class="aras1">
                <legend>
                    Maklumat Siasatan
                </legend>
                <div class="content">
                    <p>
                        <label><font color="red">*</font>Tarikh :</label>
                        <s:text name="enkuiri.tarikhEnkuiri" id="datepicker" onclick="show_calendar();"/>&nbsp;
                        <%--<a href="javascript:show_calendar();">
                            <img src="${pageContext.request.contextPath}/pub/images/calendar.gif" id="" width=15 height=15 /></a>--%>
                        <font color="red" size="1">(cth : hh / bb / tttt)</font>
                        &nbsp;
                    </p>
                    <p>
                        <label><font color="red">*</font>Masa :</label>
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
                        <s:select id="minit" name="minit" style="width:65px">
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

                        <s:select id="ampm" name="ampm" style="width:80px">
                            <s:option value="0">Pilih</s:option>
                            <s:option value="AM">Pagi</s:option>
                            <s:option value="PM">Petang</s:option>
                        </s:select>

                    <p>
                        <label><font color="red">*</font> Tempat :</label>
                        <s:textarea id="tempat" name="enkuiri.tempat" cols="50" rows="5" onblur="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> Perkara :</label>
                        <s:textarea id="perkara" name="enkuiri.perkara" cols="50" rows="5" onblur="this.value=this.value.toUpperCase();" />
                    </p>
                </div>
            </fieldset>
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
        <br/>
        <p align="right">
            <s:submit name="selesai" id="save" value="Selesai" class="btn"/>
        </p>

    </c:if>
</s:form>

