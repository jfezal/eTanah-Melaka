<%--
    Document   : maklumat_bangunanReadOnly
    Created on : Oct 05, 2021, 12:08:09 PM
    Author     : nns 
--%>
<%@ page import="java.io.*,etanah.Configuration" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    //    $(function() {

    //    jQuery(function( $ ){
    $(document).ready(function(){
        $("#tarikhSiap").datepicker({dateFormat: 'dd/mm/yy'});
        $("#cfTarikhKeluar").datepicker({dateFormat: 'dd/mm/yy'});
        //        $('#divcat').hide();
        //                var lK12 = '${actionBean.mohonStrata.laporanKemudahan12}';
        //        if(lK12 != null && lK12 != ""){
        //            document.getElementById("aduan"+ '${actionBean.mohonStrata.laporanKemudahan12}').checked = true;
        //            $('#divcat').show();
        //        }else{
        //            $('#divcat').hide();
        //        } 
        //        
        
        var negeri = '${actionBean.kodNegeri}';        
        if(negeri == '04'){
            var lain = '${actionBean.mohonStrata.laporanKemudahan13}';
            if(lain != null && lain != ""){
                document.getElementById("aduan"+ '${actionBean.mohonStrata.laporanKemudahan13}').checked = true;
                $('#divcat').show();
            }else{
                $('#divcat').hide();
            } 
        }
    });
 
    function changeHide(value){
        if(value == true){
            $('#divcat').show();
        }else{
            $('#divcat').hide();
        }
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

    //global variable :
    var dtCh= "/";
    var minYear=1900;
    var maxYear=2100;

    function validateDate(elmnt,content)
    {

        var pos1=content.indexOf(dtCh)
        var pos2=content.indexOf(dtCh,pos1+1)
        var strDay=content.substring(0,pos1)
        var strMonth=content.substring(pos1+1,pos2)
        var strYear=content.substring(pos2+1)
        //format tarikh masuk from dd/mm/yyy to mm/dd/yyyy
        var strtarikhmasuk = strMonth+ '/'+ strDay + '/'+ strYear;
        var tarikhmasuk = new Date(strtarikhmasuk);

        //format today date mm/dd/yyyy
        var today = new Date();
        var curr_date = today.getDate();
        var curr_month = today.getMonth()+1;
        var curr_year = today.getFullYear();
        var strdatetoday = curr_month + '/'+ curr_date + '/'+ curr_year;
              
        var vtoday = new Date(strdatetoday);
               
        
        //compare tarikh masuk >= tarikh hari ini.
        if ((tarikhmasuk >= vtoday ))
        {
            //something else is wrong
            alert('Tarikh yang dimasukkan tidak sah!')
            elmnt.value ="";
            return false;
        }
        else if (isDate(content)==false){
            elmnt.value ="";
            return false
        }

        else
            return true;
    }

    function isInteger(s){
        var i;
        for (i = 0; i < s.length; i++){
            // Check that current character is number.
            var c = s.charAt(i);
            if (((c < "0") || (c > "9"))) return false;
        }
        // All characters are numbers.
        return true;
    }

    function stripCharsInBag(s, bag){
        var i;
        var returnString = "";
        // Search through string's characters one by one.
        // If character is not in bag, append to returnString.
        for (i = 0; i < s.length; i++){
            var c = s.charAt(i);
            if (bag.indexOf(c) == -1) returnString += c;
        }
        return returnString;
    }

    function daysInFebruary (year){
        // February has 29 days in any year evenly divisible by four,
        // EXCEPT for centurial years which are not also divisible by 400.
        return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
    }
    function DaysArray(n) {
        for (var i = 1; i <= n; i++) {
            this[i] = 31
            if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
            if (i==2) {this[i] = 29}
        }
        return this
    }

    function isDate(dtStr){
        var daysInMonth = DaysArray(12)
        var pos1=dtStr.indexOf(dtCh)
        var pos2=dtStr.indexOf(dtCh,pos1+1)
        var strDay=dtStr.substring(0,pos1)
        var strMonth=dtStr.substring(pos1+1,pos2)
        var strYear=dtStr.substring(pos2+1)
        strYr=strYear
        if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
        if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
        for (var i = 1; i <= 3; i++) {
            if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
        }
        month=parseInt(strMonth)
        day=parseInt(strDay)
        year=parseInt(strYr)
        if (pos1==-1 || pos2==-1){
            alert("Sila masukkan tarikh mengikut format : dd/mm/yyyy")
            return false
        }
        if (strMonth.length<1 || month<1 || month>12){
            alert("Sila masukkan bulan yang betul : 1 - 12")
            return false
        }
        if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
            alert("Sila masukkan hari yang betul.")
            return false
        }
        if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
            alert("Tahun yang anda masukkan tidak tepat.")
            return false
        }
        if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
            alert("Tarikh yang anda masukkan tidak tepat.")
            return false
        }
        return true
    }
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }
    function CurrencyFormatted(amount)
    {
        var i = parseFloat(amount);
        if(isNaN(i)) { i = 0.00; }
        var minus = '';
        if(i < 0) { minus = '-'; }
        i = Math.abs(i);
        i = parseInt((i + .005) * 100);
        i = i / 100;
        s = new String(i);
        if(s.indexOf('.') < 0) { s += '.00'; }
        if(s.indexOf('.') == (s.length - 2)) { s += '0'; }
        s = minus + s;
        $('#hargaPetak').val(s);
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
    function tambahBngn(event,f) {
        if(validate2()){
            if(save(event, f)==true){
                var url = "${pageContext.request.contextPath}/strata/bangunanPKBK?tambahBngnKhas";
                window.open(url, "etanah", "status=0,toolbar=0,location=0,menubar=0,width=800,height=350");
            }
        }
        return true;

    }
    function removeBngn(val){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/strata/bangunanPKBK?deleteBngn&idBangunan='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');

        }
    }
    function updateBngn(val){

        var url = '${pageContext.request.contextPath}/strata/bangunanPKBK?popupKemaskiniBngn&idBangunan='+val;

        window.open(url, "etanah", "status=0,toolbar=0,location=0,menubar=0,width=800,height=350");
    }
    function save(event,f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div').html(data);

        },'html');
        return true;
            
    }

    function validate(){
        var kodGunaPtk = document.getElementById("pkodGunaPetak").value;
        var hargaPtk = document.getElementById("hargaPetak").value;
        var noSijil = document.getElementById("cfNoSijil").value;
        var trhSiap = document.getElementById("tarikhSiap").value;
        var trhSijil = document.getElementById("cfTarikhKeluar").value;


        if ((kodGunaPtk == ""))
        {
            alert('Sila Pilih Jenis Pembinaan ');
            document.getElementById("pkodGunaPetak").focus();
            return false;
        }
        else if ((hargaPtk == ""))
        {
            alert('Sila masukkan harga petak');
            document.getElementById("hargaPetak").focus();
            return false;
        }
        else if ((trhSiap == ""))
        {
            alert('Sila masukkan tarikh bangunan siap ');
            document.getElementById("tarikhSiap").focus();
            return false;
        }
        else if ((noSijil == ""))
        {
            alert('Sila masukkan bilangan penduduk ');
            document.getElementById("cfNoSijil").focus();
            return false;
        }
        else if ((trhSijil == ""))
        {
            alert('Sila masukkan Tarikh/Tahun Menduduki Bangunan ');
            document.getElementById("cfTarikhKeluar").focus();
            return false;
        }
                   
        else{
            if(confirm("Adakah anda pasti untuk simpan maklumat ini?"))return true;
            //
            //                var q = $(f).formSerialize();
            //                var url = f.action + '?' + event;
            //                $.post(url,q,
            //                function(data){
            //                    $('#page_div').html(data);
            //
            //                },'html');
                
        }



    }
    function validate2(){
        var kodGunaPtk = document.getElementById("pkodGunaPetak").value;
        var hargaPtk = document.getElementById("hargaPetak").value;
        var noSijil = document.getElementById("cfNoSijil").value;
        var trhSiap = document.getElementById("tarikhSiap").value;
        var trhSijil = document.getElementById("cfTarikhKeluar").value;


        if ((kodGunaPtk == ""))
        {
            alert('Sila Pilih Jenis Pembinaan ');
            document.getElementById("pkodGunaPetak").focus();
            return false;
        }
        else if ((hargaPtk == ""))
        {
            alert('Sila masukkan harga petak');
            document.getElementById("hargaPetak").focus();
            return false;
        }
        else if ((trhSiap == ""))
        {
            alert('Sila masukkan tarikh bangunan siap ');
            document.getElementById("tarikhSiap").focus();
            return false;
        }
        else if ((noSijil == ""))
        {
            alert('Sila masukkan Bilangan Penduduk ');
            document.getElementById("cfNoSijil").focus();
            return false;
        }
        else if ((trhSijil == ""))
        {
            alert('Sila masukkan Tarikh/Tahun Menduduki Bangunan ');
            document.getElementById("cfTarikhKeluar").focus();
            return false;
        }
        return true;
    }

    function p(v,w){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
        $.get("${pageContext.request.contextPath}/strata/PKKR?paparPemilik?paparPemilik&idHakmilikPihakBerkepentingan="+v+"&idHakmilik="+w,
        function(data){
            $("#perincianPihak").show();
            $("#perincianPihak").html(data);
            $.unblockUI();
        });
    }
    //    function resetForm() {
    //        document.getElementById("form1").reset();
    //    }
    //    function resetForm(){
    //        var url = "${pageContext.request.contextPath}/strata/bangunanPKKR?resetForm";
    //        var confirmation = confirm("Adakah anda pasti untuk isi semula maklumat ini?");
    //        if(confirmation==true){
    //            $.post(url,
    //            function(data){
    //                $('#page_div').html(data);
    //            },'html');
    //        }
    //        return true;
    //    }
    function dpicker(){
        $("#tarikhSiap").datepicker({dateFormat: 'dd/mm/yy'});
    }
    function dpicker2(){
        $("#cfTarikhKeluar").datepicker({dateFormat: 'dd/mm/yy'});
    }
    
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form name="form1" id="form1" beanclass="etanah.view.strata.BangunanKhasActionBean">
    <s:errors/>
    <s:messages/>

    <div class="subtitle">
        <fieldset class="aras1">
            <br>
            <legend>Maklumat Bangunan Khas</legend>

            <p>
                <label>Lokasi Bangunan :</label>
                <s:textarea readonly="true" name="lokasiBangunan"  rows="5" cols="30"/>
            </p>
            <p>
                <label>Nama Skim :</label>
                <s:text readonly="true" name="namaSkim"  id="namaSkim" size="30" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Jenis Pembinaan Bangunan :</label>
                <s:text readonly="true" name="strKodGunaPetak"  style="width:190px;" id="pkodGunaPetak" value="${actionBean.strKodGunaPetak}"/>
            </p>     
            <p>
                <label>Harga Jualan Petak (RM) :</label>
                <s:text readonly="true" name="hargaPetak" id="hargaPetak" size="30" onchange="CurrencyFormatted(this.value);" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label>Tarikh Bangunan Siap :</label>
                <s:text readonly="true" name="tarikhSiap"  id="tarikhSiap" size="30" class="datepicker" formatPattern="dd/mm/yyyy" onchange="validateDate(this,this.value);" onmouseover="dpicker()" onclick="dpicker();"/><font size="1" color="green"> dd/mm/yyyy</font>
            </p>
            <p>
                <label>Bilangan Penduduk :</label>
                <s:text readonly="true" name="cfNoSijil" id="cfNoSijil" size="30"/>
            </p>
            <p>
                <label>Tarikh/Tahun Menduduki Bangunan :</label>
                <s:text readonly="true" name="cfTarikhKeluar" id="cfTarikhKeluar" size="30" class="datepicker"  formatPattern="dd/mm/yyyy" onchange="validateDate(this,this.value);" onmouseover="dpicker2()" onclick="dpicker2();"/><font size="1" color="green"> dd/mm/yyyy</font>
            </p>
            <p>

                <label>Harta Bersama :</label>
                <c:set var="i" value="0"/>
                <c:forEach items="${actionBean.senaraikodHartaBersama}" var="harta" varStatus="statusB">

                    <c:forEach items="${actionBean.senaraiHarta}" var="senaraiharta" varStatus="statusB">
                        <c:if test="${harta.nama eq senaraiharta && harta.nama ne 'Lain-lain'}">
                            <c:set var="i" value="1"/>
                        <input type="checkbox" name="${harta.nama}"  value="${harta.nama}" checked="checked" id="aduan${harta.nama}"/> ${harta.nama}<p/><p>
                            <label>&nbsp;</label>
                        </c:if>
                        <c:if test="${harta.nama eq senaraiharta && harta.nama eq 'Lain-lain'}">
                            <c:set var="i" value="1"/>
                        <input type="checkbox" name="${harta.nama}"  value="${harta.nama}" checked="checked" id="aduan${harta.nama}" onclick="javaScript:changeHide(this.checked)"/> ${harta.nama}<p/><p>
                            <label>&nbsp;</label>
                        </c:if>
                    </c:forEach>
                    <c:if test="${i eq 0 && harta.nama ne 'Lain-lain'}">
                    <input  type="checkbox" name="${harta.nama}"  value="${harta.nama}" id="aduan${harta.nama}"/> ${harta.nama}<p/><p>
                        <label>&nbsp;</label>
                    </c:if>
                    <c:if test="${i eq 0 && harta.nama eq 'Lain-lain'}">
                    <input  type="checkbox" name="${harta.nama}"  value="${harta.nama}" id="aduan${harta.nama}" onclick="javaScript:changeHide(this.checked)"/> ${harta.nama}<p/><p>
                        <label>&nbsp;</label>
                    </c:if>
                    <c:set var="i" value="0"/>
                </c:forEach>
            <div id="divcat">
                <s:textarea readonly="true" name="lainLain" rows="5" cols="36" id="lainLain" class="lainLain"/>
            </div>

            <br>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <br>
            <legend>Senarai Bangunan</legend>
            <label>&nbsp;</label>
            <p align="center">
            <center>
                <display:table   class="tablecloth" name="${actionBean.senaraiMhnBngn}" cellpadding="0" cellspacing="0" id="line" >
                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>

                    <display:column title="Blok (M)">${line.nama}</display:column>
                    <display:column title="Bil Tingkat">${line.bilanganTingkat}</display:column>
                    <display:column title="Bil Petak">${line.bilanganPetak}</display:column>
                </display:table>


            </center>
            <br>
            <p>
                <label>Jumlah Blok Keseluruhan :</label>
                <s:text  readonly="true" name="bilBangunan" size="30"/> 
            </p>
            <p>
                <label>Jumlah Petak Keseluruhan:</label>
                <s:text  readonly="true" name="bilPetak" size="30"/>
            </p>
            <br>

            <br><br><br>

        </fieldset>
    </div>
    <div id="perincianPihak" align="center">
        <p></p>
        <br>
    </div>
</s:form>