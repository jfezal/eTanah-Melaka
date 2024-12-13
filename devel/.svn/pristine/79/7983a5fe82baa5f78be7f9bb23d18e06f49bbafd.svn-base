<%--
    Document   : tambah_ibubapa
    Created on : 12 Nov 2010
    Author     : Rohan
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
        maximizeWindow();
    <c:if test="${!flag}">
            opener.refreshMaklumatPemohon();

            self.close();
    </c:if>

            $('.alphanumeric').alphanumeric();
            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
            $('#jenisPengenalan').change(function(){
                dodacheck($("#kp").val());
            });
             var v = '${actionBean.pemohonHbgn.jenisPengenalan.kod}';

            $('form').submit(function(){
                var val = $('#kp').val();
                var jenis = '${jenis}';
                return doCheckUmur(val, 'kp',jenis);
            });

              if(v == "B"){
                var icNo = '${actionBean.pemohonHbgn.noPengenalan}';
    //alert(icNo);
                $('#datepicker').val(icNo.substring(4,6)+"/"+icNo.substring(2,4)+"/"+19 + icNo.substring(0,2));
                var age = calculateAge(icNo.substring(4,6), icNo.substring(2,4), 19 + icNo.substring(0,2));
                $('#umur').val(age);
     //alert(age);
     //alert(document.getElementById("umur"));
                 document.getElementById("umur").value =age;
             }
             $('#kod_warganegara').val('MAL');
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
            $('#alamat').hide();
        } else {
             $('#alamat').show();
        }
   }
   function selectRadio(obj){
   if(obj.value == "T"){
    $('#p_tarikhMati').hide();
     }else{
        $('#p_tarikhMati').show();
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

</script>
<style type="text/css">
    input.error { background-color: yellow; }
</style>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<div class="subtitle" id="caw">
    <s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPemohon1ActionBean">

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
        <%--<s:hidden name="pihak.negeri.nama"/>--%>
        <s:hidden name="permohonanPihak.jenis.kod"/>
        <s:hidden name="pihakCawangan.idPihakCawangan"/>
        <%--<s:hidden name="jenisPihak" value="${jenis}"/>--%>
        <div align="center">
            <s:errors/>
            <s:messages/>
        </div>
        <fieldset class="aras1">
            <legend>Kemasukan Maklumat Ibu/Bapa Pemohon</legend>

            <p>
                <label for="nama"><font color="red">*</font>Nama :</label>
                <s:text name="pemohonHbgn.nama" id="nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>

          <%--  <p>
                <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                <s:select name="pemohonHbgn.jenisPengenalan.kod"  id="kod"  onchange ="hide(this.options[selectedIndex].text);" onchange="javaScript:changeHideSuku(this.options[selectedIndex].text)" style="width:244px">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiJenisPengenalan}" label="nama" value="kod"/>
                </s:select>
            </p>--%>

            <p>
                <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                <s:select name="pemohonHbgn.jenisPengenalan.kod"  id="jenisPengenalan"  onchange ="hideWarna(this.options[selectedIndex].text);"  style="width:244px">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiJenisPengenalan}" label="nama" value="kod"/>
                </s:select>
            </p>

            <p>
                <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                <s:text name="pemohonHbgn.noPengenalan" id="kp" size="40"  onblur="calAgeByNopeng(this.value);" onkeyup="dodacheck(this.value);" /> <em>[760908049835 atau A2977884]</em>
            </p>
<%-- <div id="div1">
           <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B'}">
               <p>
                    <label><font color="red">*</font>Warna Nombor Kad Pengenalan :</label>
                    <s:select name="pihak.warnaKP" id="warnaKP" style="width:100px"> value="${actionBean.pihak.warnaKP}"
                        <s:option value="0">SILA PILIH</s:option>
                        <s:option value="B">Biru</s:option>
                        <s:option value="C">Coklat</s:option>
                        <s:option value="H">Hijau</s:option>
                        <s:option value="M">Merah</s:option>
                        <s:option value="L">Lain-lain</s:option>
                    </s:select>
                </p>
            </c:if>
 </div>
--%>
            <p> <label><font color="red">*</font>Kaitan :</label>

                <s:select name="pemohonHbgn.kaitan" id="kaitan" style="width:244px" onchange="kpchecking(this.value)">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:option value="IBU">IBU</s:option>
                    <s:option value="BAPA">BAPA</s:option>
                </s:select>
            </p>

            <p>
                <label><font color="red">*</font>Tarikh Lahir :</label>
                <s:text name="pemohonHbgn.tarikhLahir" size="40"  id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy" onchange="calAgeByDOB1(this.value);"/> <em>[hh/bb/tttt]</em>
            </p>
            <p>
                <label><font color="red">*</font>Umur :</label>
                <s:text name="pemohonHbgn.umur" size="10" maxlength="3"  id="umur" onkeyup="validateNumber(this,this.value);"/>
            </p>

            <p><label><font color="red">*</font>Telah Meninggal :</label><s:radio name="pemohonHbgn.telahMeninggal" id="meninggal" value="Y" onclick="javascript:selectRadio(this)"/> Ya <s:radio name="pemohonHbgn.telahMeninggal" id="meninggal" value="T" onclick="javascript:selectRadio(this)"/> Tidak</p>
          
            <p id="p_tarikhMati"><label>Tarikh Meninggal :</label><s:text name="pemohonHbgn.tarikhMati" size="30" id="tarikhMati" class="datepicker" formatPattern="dd/MM/yyyy"/></p>
          
            <p>
                <label><font color="red">*</font>Tempat Lahir :</label>
                <s:text name="pemohonHbgn.tempatLahir" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label><font color="red">*</font>Kewarganegaraan :</label>
                <s:select name="pemohonHbgn.warganegara.kod" style="width:244px">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                </s:select>
            </p>

            <p>
                <label>Pekerjaan :</label>
                <s:text name="pemohonHbgn.pekerjaan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label>Pendapatan Bulanan(RM) :</label>
                <s:text name="pemohonHbgn.gaji" size="40" formatPattern="#,##0.00" onkeyup="validateNumber(this,this.value);"/>
            </p>

                 <p>
                <label>&nbsp;</label>
                <input type="checkbox" id="add1" name="add1" value="check" onclick="Hide1();"/>
                <font color="red">Alamat surat-menyurat sama dengan alamat pemohon.</font>
            </p>
            <div id="alamat">
            <p>
                <label for="alamat"><font color="red">*</font>Alamat Surat-Menyurat :</label>
                <s:text name="pemohonHbgn.alamat1" id="Alamat1" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pemohonHbgn.alamat2" id="Alamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pemohonHbgn.alamat3" id="Alamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pemohonHbgn.alamat4" id="Alamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>


            <p>
                <label><font color="red">*</font>Poskod :</label>
                <s:text name="pemohonHbgn.poskod" value="${actionBean.pemohonHbgn.poskod}"id="suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
            </p>

            <p id="negeri">
                <label for="Negeri"><font color="red">*</font>Negeri :</label>
                <s:select name="pemohonHbgn.negeri.kod" id="majikanNegeri" style="width:244px">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select>
            </p>
            </div>
            <p>
                <label>&nbsp;</label>
                <s:submit name="simpanPemohonIbuBapa" id="simpan" value="Simpan" class="btn"/>
                <s:button name=""  size="20"id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
            <br>
        </fieldset>
    </s:form>
</div>