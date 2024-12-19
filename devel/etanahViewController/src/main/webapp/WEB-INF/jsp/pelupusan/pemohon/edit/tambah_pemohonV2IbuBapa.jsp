<%-- 
    Document   : tambah_pemohonV2IbuBapa
    Created on : Jul 17, 2013, 1:02:48 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ageCalculator.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">

    $(document).ready( function(){
        var statusMati = '${actionBean.pemohonHubungan.telahMeninggal}';
        
    
        if(statusMati == "Y"){
            $('#p_tarikhMati').show();
            $('#jenisPengenalan').hide();
            $('#nomborPengenalan').hide();
            $('#tarihLahir').hide();
            $('#age').hide();
            $('#tempatLahir').hide();
            $('#pekerjaan').hide();
            $('#pendapatan').hide();
            $('#checkBoxAlamat').hide();
            $('#alamatPemohonHubungan1').hide();
            $('#alamatPemohonHubungan2').hide();
            $('#alamatPemohonHubungan3').hide();
            $('#alamatPemohonHubungan4').hide();
            $('#alamatPemohonHubungan5').hide();
            $('#alamatPemohonHubungan6').hide();  
        }
        
        else if(statusMati == "T"){
            $('#p_tarikhMati').hide();
            $('#jenisPengenalan').show();
            $('#nomborPengenalan').show();
            $('#tarihLahir').show();
            $('#age').show();
            $('#tempatLahir').show();
            $('#pekerjaan').show();
            $('#pendapatan').show();
            $('#checkBoxAlamat').show();
            $('#alamatPemohonHubungan1').show();
            $('#alamatPemohonHubungan2').show();
            $('#alamatPemohonHubungan3').show();
            $('#alamatPemohonHubungan4').show();
            $('#alamatPemohonHubungan5').show();
            $('#alamatPemohonHubungan6').show();
        }
       
    
        $('.alphanumeric').alphanumeric();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $('#jenisPengenalan').change(function(){
            dodacheck($("#kp").val());
        });
        var v = '${actionBean.pemohonHubungan.jenisPengenalan.kod}';

        $('form').submit(function(){
            var val = $('#kp').val();
            var jenis = '${jenis}';
            return doCheckUmur(val, 'kp',jenis);
        });

        if(v == "B"){
            var icNo = '${actionBean.pemohonHubungan.noPengenalan}';
            //alert(icNo);
            $('#datepicker').val(icNo.substring(4,6)+"/"+icNo.substring(2,4)+"/"+19 + icNo.substring(0,2));
            var age = calculateAge(icNo.substring(4,6), icNo.substring(2,4), 19 + icNo.substring(0,2));
            $('#umur').val(age);
            //alert(age);
            //alert(document.getElementById("umur"));
            document.getElementById("umur").value =age;
        }
//        $('#kod_warganegara').val('MAL');
    });


    function calAgeByDOB1(value){

        var yearStartVal = value.substring(6,8);
        if(yearStartVal == "19"){
            var age = calculateAge(value.substring(0,2), value.substring(3,5), 19 + value.substring(8,10));
        }else{
            var age = calculateAge(value.substring(0,2), value.substring(3,5), 20 + value.substring(8,10));
        }
        $('#umur').val(age);
    }


    function calAgeByNopeng(value){
        // var v = $('#jenisPengenalan').val();
    <%-- var v = '${actionBean.pemohonHbgn.jenisPengenalan.kod}';

        if(v == 'B') {--%>

                var icNo = value;
                $('#datepicker').val(icNo.substring(4,6)+"/"+icNo.substring(2,4)+"/"+19 + icNo.substring(0,2));
                var age = calculateAge(icNo.substring(4,6), icNo.substring(2,4), 19 + icNo.substring(0,2));
                $('#umur').val(age);
            }<%--}--%>

            function calculateUmar(){
                if($("#tarikhLahir").val() != ""){
                    var value = $("#tarikhLahir").val();
                    var yearStartVal = value.substring(6,8);
                    if(yearStartVal == "19"){
                        var age = calculateAge(value.substring(0,2), value.substring(3,5), 19 + value.substring(8,10));
                    }else{
                        var age = calculateAge(value.substring(0,2), value.substring(3,5), 20 + value.substring(8,10));
                    }
                    $('#umur').val(age);
                }
            }


            function changeHideSuku(value){
                if(value == "S"){
                    $('#suku').hide();
                }else{
                    $('#suku').show();
                }
            }


            function savePemohonSuamiIsteri(event, f){
                alert("savePemohonSuami");
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    self.close();
                },'html');
            }


            function changeHideOnJenisPengenalan(value){
                if(value == "S"){
                    $('#suku').hide();
                    $('#modalBerbayar').show();
                }
                else{
                    $('#suku').show();
                    $('#modalBerbayar').hide();
                }
            }

            function copyAddCaw(){
                if($('input[name=addCaw]').is(':checked')){
                    $('#suratAlamatCaw1').val($('#alamatCaw1').val());
                    $('#suratAlamatCaw2').val($('#alamatCaw2').val());
                    $('#suratAlamatCaw3').val($('#alamatCaw3').val());
                    $('#suratAlamatCaw4').val($('#alamatCaw4').val());
                    $('#suratPoskodCaw').val($('#poskodCaw').val());
                    $('#suratNegeriCaw').val($('#negeriCaw').val());
                }else{
                    $('#suratAlamatCaw1').val('');
                    $('#suratAlamatCaw2').val('');
                    $('#suratAlamatCaw3').val('');
                    $('#suratAlamatCaw4').val('');
                    $('#suratPoskodCaw').val('');
                    $('#suratNegeriCaw').val('');

                }
            }

            function copyAdd(){
                if($('input[name=add1]').is(':checked')){
                    $('#suratAlamat1').val($('#alamat1').val());
                    $('#suratAlamat2').val($('#alamat2').val());
                    $('#suratAlamat3').val($('#alamat3').val());
                    $('#suratAlamat4').val($('#alamat4').val());
                    $('#suratPoskod').val($('#poskod').val());
                    $('#suratNegeri').val($('#negeri').val());
                }else{
                    $('#suratAlamat1').val('');
                    $('#suratAlamat2').val('');
                    $('#suratAlamat3').val('');
                    $('#suratAlamat4').val('');
                    $('#suratPoskod').val('');
                    $('#suratNegeri').val('');

                }
            }

            function removeCawanganPemohon(val){
                if(confirm('Adakah anda pasti?')) {
                    var url = '${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1?deleteCawanganPemohon&idCawangan='+val;
                    $.get(url,
                    function(data){
                        $('#caw').html(data);
                    },'html');
                }
            }

            function editCawanganPemohon(val){
                var url = '${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1?editCawanganPemohon&idCawangan='+val;
                $.get(url,
                function(data){
                    $('#caw').html(data);
                },'html');
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

            function Hide1(){
                // alert($('input[value=check]').is(':checked'));
                if($('input[value=check]').is(':checked')){
                    $('#alamatPemohonHubungan1').hide();
                    $('#alamatPemohonHubungan2').hide();
                    $('#alamatPemohonHubungan3').hide();
                    $('#alamatPemohonHubungan4').hide();
                    $('#alamatPemohonHubungan5').hide();
                    $('#alamatPemohonHubungan6').hide();
                } else {
                    $('#alamatPemohonHubungan1').show();
                    $('#alamatPemohonHubungan2').show();
                    $('#alamatPemohonHubungan3').show();
                    $('#alamatPemohonHubungan4').show();
                    $('#alamatPemohonHubungan5').show();
                    $('#alamatPemohonHubungan6').show();
                }
            }
            function selectRadio(obj){
                if(obj.value == "T"){
                    
                    $('#p_tarikhMati').hide();
                    $('#jenisPengenalan').show();
                    $('#nomborPengenalan').show();
                    $('#tarihLahir').show();
                    $('#age').show();
                    $('#tempatLahir').show();
                    $('#pekerjaan').show();
                    $('#pendapatan').show();
                    $('#checkBoxAlamat').show();
                    $('#alamatPemohonHubungan1').show();
                    $('#alamatPemohonHubungan2').show();
                    $('#alamatPemohonHubungan3').show();
                    $('#alamatPemohonHubungan4').show();
                    $('#alamatPemohonHubungan5').show();
                    $('#alamatPemohonHubungan6').show();
                  }else if(obj.value == "Y"){
                    
                    $('#p_tarikhMati').show();
                    $('#jenisPengenalan').hide();
                    $('#nomborPengenalan').hide();
                    $('#tarihLahir').hide();
                    $('#age').hide();
                    $('#tempatLahir').hide();
                    $('#pekerjaan').hide();
                    $('#pendapatan').hide();
                    $('#checkBoxAlamat').hide();
                    $('#alamatPemohonHubungan1').hide();
                    $('#alamatPemohonHubungan2').hide();
                    $('#alamatPemohonHubungan3').hide();
                    $('#alamatPemohonHubungan4').hide();
                    $('#alamatPemohonHubungan5').hide();
                    $('#alamatPemohonHubungan6').hide();
                }
             }

            function dodacheck(val) {
                //var mikExp = /[1\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\1\|]/;

                var v = $('#jenisPengenalan').val();

                if(v == 'B') {
                    var strPass = val;
                    var strLength = strPass.length;
                    //$('#kp').attr("maxlength","12");
                    if(strLength > 12) {
                        var tst = val.substring(0, (strLength) - 1);
                        $('#kp').val(tst);
                    }
                    var lchar = val.charAt((strLength) - 1);
                    if(isNaN(lchar)) {
                        //return false;
                        var tst = val.substring(0, (strLength) - 1);
                        $('#kp').val(tst);
                    }
                } 
                //else{
                // $('#kp').attr("maxlength","30");
                // } 
           
                if (v%2==0) {
                    v == 'I' 
                }
                else {
                    v == 'B'
                }
            }

            function doCheckUmur(v,id,type){
                var va = $('#jenisPengenalan').val();
                if(va == 'B'){
                    return doValidateAge(v, id, 'jenisPengenalan',type);
                }else {
                    return true;
                }
            }
            
            function doValidateAge(ic, id, id2,type){
                var valid = true;
                var v = $('#'+id2).val();
                if(v == 'B'){
                    date = ic.substring(0,6);
                    yyyy = date.substring(0,2);
                    mm = date.substring(2,4);
                    dd = date.substring(4,6);
                    if(yyyy < 99 && yyyy > 10){//fixme :
                        yyyy = "19" + yyyy;
                    }else {
                        yyyy = "20" + yyyy;
                    }

    <%--alert(yyyy + ',' + mm + ','+ dd);--%>

                days = new Date();
                gdate = days.getDate();
                gmonth = days.getMonth();
                gyear = days.getFullYear();
                age=gyear-yyyy;

    <%--alert(gyear + ',' + yyyy + ',' + days.getFullYear());--%>
                if((mm==(gmonth+1))&&(dd<=parseInt(gdate))) {
                    age=age
                } else {
                    if(mm<=(gmonth)) {
                        age=age
                    } else {
                        age=age-1
                    }
                }
                if(type != 'WAR'){

                    if(age < 18){
                        alert('Umur tidak mencukupi. Mesti 18 tahun dan ke atas.');
                        $('#'+id).val('');
                        $('#'+id).focus();
                        valid = false;
                    }
                }else{
                    if(age > 18){
                        alert('Umur Penerima Mesti 18 tahun dan kebawah.');
                        $('#'+id).val('');
                        $('#'+id).focus();
                        valid = false;
                    }
                }
            }
            return valid;
        }

        function hideWarna(value){
            // alert($('input[value=check]').is(':checked'));
            //  alert("outside");
    <%--alert("123-------"+value);--%>
            if(value == "No K/P Baru"){
    <%--alert("inside"+value);--%>
                $('#div1').show();
            }
            else{
                $('#div1').hide();
            }
        }

        var dtCh= "/";
        var minYear=1900;
        var maxYear=2100;

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

            //	var strMonth=dtStr.substring(0,pos1)
            //	var strDay=dtStr.substring(pos1+1,pos2)
            //	var strYear=dtStr.substring(pos2+1)

            var strYear=dtStr.substring(0,2)
            var strMonth=dtStr.substring(2,4)
            var strDay=dtStr.substring(4,6)


            var strYr=(1900+parseInt(strYear))+"";

            if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)

            if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)

            for (var i = 1; i <= 3; i++) {
                if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
            }

            month=parseInt(strMonth)
            day=parseInt(strDay)
            year=parseInt(strYr)

            if (strMonth.length<1 || month<1 || month>12 ){
                alert("Please enter a valid month")
                return false
            }
            if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
                alert("Please enter a valid day")
                return false
            }


            if (strYr.length != 4 || year==0 || year<minYear || year>maxYear){
                alert("Please enter a valid 4 digit year between "+minYear+" and "+maxYear)
                return false
            }
            //	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
            //		alert("Please enter a valid date")
            //		return false
            //	}
            return true
        }

        function ValidateForm(){
    <%--alert("validate");--%>
            var dt=document.getElementById("kp");
            if (isDate(dt.value)==false){
                dt.focus()
                return false
            }
            return true
        }
 
        function calAgeByDOB(value){
            var dob = value;
            //             $('#datepicker').val(icNo.substring(4,6)+"/"+icNo.substring(2,4)+"/"+19 + icNo.substring(0,2));
            var age = calculateAge(dob);
        }

        function kpchecking(val) {

            var jkenal = $('#jenisPengenalan').val();
            var nokp = $('#kp').val();
            var nokplg = nokp.length;
            var kait = $('#kaitan').val();
            var last_char = nokp.charAt(nokplg-1);
            var b = nokp % 2; //digit terakhir ic
    
            if(jkenal=="B"){ //utk KP baru
                if (kait=="IBU" && b==0){
                    // donothing
                } else {
                    if (kait=="IBU" && b!=0){
                        alert('Sila Semak No. Kad Pengenalan dan Isi Semula');
                        $('#kp').val("");
                    }  
                }
      
                if (kait=="BAPA" && b!=0){
                    // donothing
                } else {
                    if (kait=="BAPA" && b==0){
                        alert('Sila Semak No. Kad Pengenalan dan Isi Semula');
                        $('#kp').val("");
                    }
                }
            }
       
        } 
        
        function editKeluarga()
        {
            NoPrompt();
            var idPemohon = $('#idPemohon').val();
            //            alert(idPemohon);
            window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohonV2?showFormMaklumatKeluarga&idPemohon="+idPemohon, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }

</script>
<body>
    <script>
        // Allow the user to be warned by default.
        var allowPrompt = true;
                
        window.onbeforeunload = WarnUser;
        function WarnUser()
        {   
            if(allowPrompt)
                refreshpage();
            if(allowPrompt)
            {
                event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
            }
            else
            {
                // Reset the flag to its default value.
                allowPrompt = true;
            }
        }
        function NoPrompt()
        {
            allowPrompt = false;
        }
        
    </script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="list" />
    <s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPemohonV2ActionBean" name="form" onsubmit="validation();">
        <s:errors/>
        <s:messages/>
        <s:hidden name="pihak.nama"/>
        <s:hidden name="pihak.idPihak"/>
        <s:hidden name="pihak.jenisPengenalan.kod"/>
        <s:hidden name="pihak.jenisPengenalan.nama"/>
        <s:hidden name="pihak.noPengenalan"/>
        <s:hidden name="pihak.bangsa.kod"/>
        <s:hidden name="pihak.alamat1"/>
        <s:hidden name="pihak.alamat2"/>
        <s:hidden name="pihak.alamat3"/>
        <s:hidden name="pihak.alamat4"/>
        <s:hidden name="pihak.poskod"/>
        <s:hidden name="pihak.negeri.kod"/>
        <s:hidden name="permohonan.kodUrusan.kod"/>
        <s:hidden name="idPemohonHubungan" value="${actionBean.pemohonHubungan.idHubungan}"/>
        <s:hidden name="idPemohon" id="idPemohon"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Tambah Ibu Bapa</legend>
                <div class="content" align="center">
                    <font color="red">Sila Masukkan Maklumat Yang Bertanda *</font>
                    <table class="tablecloth" border="0">
                        <tr>
                            <td>
                                <em>*</em>Nama :
                            </td>
                            <td>
                                <s:text name="pemohonHubungan.nama" id="nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <em>*</em>Telah Meninggal :
                            </td>
                            <td>
                                <s:radio name="pemohonHubungan.telahMeninggal" id="meninggal" value="Y" onclick="javascript:selectRadio(this)"/> Ya <s:radio name="pemohonHubungan.telahMeninggal" id="meninggal" value="T" onclick="javascript:selectRadio(this)"/> Tidak
                            </td>
                        </tr>
                        <tr id="jenisPengenalan">
                            <td>
                                <em>*</em>Jenis Pengenalan :
                            </td>
                            <td>
                                <s:select name="pemohonHubungan.jenisPengenalan.kod" id="jenisPengenalan"  onchange ="hideWarna(this.options[selectedIndex].text);">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiJenisPengenalan}" label="nama" value="kod"/>
                                </s:select>
                            </td>
                        </tr>
                        <tr id="nomborPengenalan">
                            <td>
                                <em>*</em>Nombor Pengenalan :
                            </td>
                            <td>
                                <s:text name="pemohonHubungan.noPengenalan" id="kp" size="40"  onblur="calAgeByNopeng(this.value);" onkeyup="dodacheck(this.value);" /> <em>[760908049835 atau A2977884]</em>
                            </td>
                        </tr>
                        <tr>

                            <td>
                                <em>*</em>Kaitan :
                            </td>
                            <td>
                                <s:select name="pemohonHubungan.kaitan" id="kaitan" style="width:244px" onchange="kpchecking(this.value)">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:option value="IBU">IBU</s:option>
                                    <s:option value="BAPA">BAPA</s:option>
                                </s:select>
                            </td>
                        </tr>
                        <tr id="tarihLahir">
                            <td>
                                <em>*</em>Tarikh Lahir :
                            </td>
                            <td>
                                <s:text name="pemohonHubungan.tarikhLahir" size="40"  id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy" onchange="calAgeByDOB1(this.value);"/> <em>[hh/bb/tttt]</em>
                            </td>
                        </tr>
                        <tr id="age">
                            <td>
                                <em>*</em>Umur :
                            </td>
                            <td>
                                <s:text name="pemohonHubungan.umur" size="10" maxlength="3"  id="umur" onkeyup="validateNumber(this,this.value);" readonly="true"/>
                            </td>
                        </tr>
                        <tr id="p_tarikhMati">
                            <td>
                                <em>*</em>Tarikh Meninggal :
                            </td>
                            <td>
                                <s:text name="pemohonHubungan.tarikhMati" size="30" id="tarikhMati" class="datepicker" formatPattern="dd/MM/yyyy"/>
                            </td>
                        </tr>

                        <tr id="tempatLahir">
                            <td>Tempat Lahir :</td>
                            <td>
                                <s:text name="pemohonHubungan.tempatLahir" size="40"onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <em>*</em>Kewarganegaraan :
                            </td>
                            <td>
                                <s:select name="pemohonHubungan.warganegara.kod" style="width:150px" id="kod_warganegara">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                                </s:select>
                            </td>
                        </tr>
                        <tr id="pekerjaan">
                            <td>
                                Pekerjaan :
                            </td>
                            <td>
                                <s:text name="pemohonHubungan.pekerjaan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>  
                            </td>

                        </tr>
                        <tr id="pendapatan">
                            <td>
                                Pendapatan Bulanan (RM) :
                            </td>
                            <td>
                                <s:text name="pemohonHubungan.gaji" size="40" onkeyup="validateNumber(this,this.value);"/>
                            </td>
                        </tr>
                        <tr id="checkBoxAlamat">
                            <td>
                                <input type="checkbox" id="add1" name="add1" value="check" onclick="Hide1();"/>
                                <font color="red">Alamat surat-menyurat sama dengan alamat pemohon.</font>
                            </td>
                            <td>
                                &nbsp;
                            </td>
                        </tr>
                        <tr id="alamatPemohonHubungan1">
                            <td>
                                <em>*</em>Alamat :
                            </td>
                            <td>
                                <s:text name="pemohonHubungan.alamat1" value="${actionBean.pemohonHubungan.alamat1}" id="alamat1" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr id="alamatPemohonHubungan2">
                            <td>
                                &nbsp;
                            </td>
                            <td>
                                <s:text name="pemohonHubungan.alamat2" value="${actionBean.pemohonHubungan.alamat2}" id="alamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr id="alamatPemohonHubungan3">
                            <td>
                                &nbsp;
                            </td>
                            <td>
                                <s:text name="pemohonHubungan.alamat3" value="${actionBean.pemohonHubungan.alamat3}" id="alamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr id="alamatPemohonHubungan4">
                            <td>
                                &nbsp;
                            </td>
                            <td>
                                <s:text name="pemohonHubungan.alamat4" value="${actionBean.pemohonHubungan.alamat4}" id="alamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr id="alamatPemohonHubungan5">
                            <td>
                                <em>*</em>Poskod :
                            </td>
                            <td>
                                <s:text name="pemohonHubungan.poskod" value="${actionBean.pemohonHubungan.poskod}" size="40" id="poskod" maxlength="5" onkeyup="validateNumber(this,this.value);"/> 
                            </td>
                        </tr>
                        <tr id="alamatPemohonHubungan6">
                            <td>
                                <em>*</em>Negeri :
                            </td>
                            <td>
                                <s:select name="pemohonHubungan.negeri.kod" id="negeri" >
                                    <s:option value="0">Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" />
                                </s:select>
                            </td>
                        </tr>


                    </table>
                    <br/>
                    <table class="tablecloth" border="0">
                        <tr>
                            <td colspan="2"><s:submit name="simpanPemohonHubungan" value="Simpan" class="btn" onclick="NoPrompt();"/>
                                <s:hidden name="type" id="type" value="tIbuBapa"/>
                                <s:hidden name="status" id="status" value="${status}"/>
                                <s:button name="Tambah" id="save" value="Kembali" class="btn" onclick="editKeluarga()"/></td>
                        </tr>
                    </table>
                </div>
                <br/>

            </fieldset>
        </div>
    </s:form>
</body>
