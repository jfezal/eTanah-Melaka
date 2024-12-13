<%-- 
    Document   : tambah_pemohonV2
    Created on : Jun 13, 2013, 10:53:45 AM
    Author     : afham
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Maklumat Pemohon</title>
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
       
        $('.alphanumeric').alphanumeric(),
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'}),

           
        $('#jenisPengenalan').change(function(){
            dodacheck($("#kp").val());
        });
        var v = '${actionBean.pihak.jenisPengenalan.kod}';

        $('form').submit(function(){
            var val = $('#kp').val();
            var jenis = '${jenis}';
            return doCheckUmur(val, 'kp',jenis);
        });

        if(v == "B"){
            var icNo = '${actionBean.pihak.noPengenalan}';
    <%--alert(icNo);--%>
                $('#datepicker').val(icNo.substring(4,6)+"/"+icNo.substring(2,4)+"/"+19 + icNo.substring(0,2));
                var age = calculateAge(icNo.substring(4,6), icNo.substring(2,4), 19 + icNo.substring(0,2));
                $('#umur').val(age);
    <%--  alert(age);
      alert(document.getElementById("umur"));--%>
                  document.getElementById("umur").value =age;
              }
              $('#kod_warganegara').val('MAL');
          });

          function changeHideSuku(value){
              if(value == "S"){
                  $('#suku').hide();
              }else{
                  $('#suku').show();
              }
          }


          function changeHideSuku(value){
              if(value == "S"){
                  $('#suku').hide();
              }
              else{
                  $('#suku').show();
              }
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

          function calAgeByDOB1(value){
              var yearStartVal = value.substring(6,8);
              if(yearStartVal == "19"){
                  var age = calculateAge(value.substring(0,2), value.substring(3,5), 19 + value.substring(8,10));
              }else{
                  var age = calculateAge(value.substring(0,2), value.substring(3,5), 20 + value.substring(8,10));
              }
              $('#umur').val(age);
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
              }//else{
              // $('#kp').attr("maxlength","30");
              // }
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
        function addSeterusnya(){
            window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1?showTambahLatarbelakangPemohon", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=710,height=400,scrollbars=yes");
        }
        
        function addPemohon(){

        NoPrompt();

        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohonV2?showFormPopUp", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
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
    <s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPemohonV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="pihak.idPihak"/>
        <s:hidden name="pihak.jenisPengenalan.kod"/>
        <s:hidden name="pihak.jenisPengenalan.nama"/>
        <s:hidden name="pihak.noPengenalan"/>
        <s:hidden name="pihak.bangsa.kod"/>
         <s:hidden name="permohonan.kodUrusan.kod"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Tambah Pemohon</legend>
                <div class="content" align="center">
                    <font color="red">Sila Masukkan Maklumat Yang Bertanda *</font>
                    <table class="tablecloth" border="0">
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'F'||actionBean.pihak.jenisPengenalan.kod eq 'N'}">
                            <tr>
                                <td>
                                    <em>*</em>Nama :
                                </td>
                                <td>
                                    <s:text name="pihak.nama" id="nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <em>*</em>Jenis Pengenalan :
                                </td>
                                <td>
                                    <s:select name="pihak.jenisPengenalan.kod" id="jenisPengenalan" value="${actionBean.pihak.jenisPengenalan.kod}" disabled="true">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiJenisPengenalanPelupusan}" label="nama" value="kod"/>
                                    </s:select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <em>*</em>Nombor Pengenalan :
                                </td>
                                <td>
                                    <s:text name="pihak.noPengenalan" id="kp" size="40" disabled="true"/> <em>[700304059873 atau A2977884]</em>
                                </td>
                            </tr>
                            <tr>

                                <td>
                                    <em>*</em>Bangsa :
                                </td>
                                <td>
                                    <s:select name="pihak.bangsa.kod" style="width:246px">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${actionBean.senaraiKodBangsa}" label="nama" value="kod" />
                                    </s:select>
                                </td>
                            </tr>

                            <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B'}">
                                <tr>
                                    <td>
                                        <em>*</em>Warna Kad Pengenalan :
                                    </td>
                                    <td>
                                        <s:select name="pihak.warnaKP" id="warnaKP" value="${actionBean.pihak.warnaKP}" style="width:100px">
                                            <s:option value="0">Sila Pilih</s:option>
                                            <s:option value="B">Biru</s:option>
                                            <s:option value="C">Coklat</s:option>
                                            <s:option value="H">Hijau</s:option>
                                            <s:option value="M">Merah</s:option>
                                            <s:option value="L">Lain-lain</s:option>
                                        </s:select>
                                    </td>
                                </tr>
                            </c:if>
                            <tr>
                                <td>
                                    <em>*</em>Tarikh Lahir :
                                </td>
                                <td>
                                    <s:text name="pihak.tarikhLahir" size="40" id="datepicker" class="datepicker" onchange="calAgeByDOB1(this.value);"/> <em>[hh/bb/tttt]</em>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <em>*</em>Umur :
                                </td>
                                <td>
                                    <s:hidden name="pemohon.idPemohon"/>
                                    <s:text name="pemohon.umur" size="10" maxlength="3" id="umur" onkeyup="validateNumber(this,this.value);"/>
                                </td>
                            </tr>

                            <tr>
                                <td>Tempat Lahir :</td>
                                <td>
                                    <s:text name="pihak.tempatLahir" size="40"onkeyup="this.value=this.value.toUpperCase();"/>
                                </td>
                            </tr>


                            <tr>
                                <td>
                                    <em>*</em>Jantina :
                                </td>
                                <td>
                                    <s:radio name="pihak.kodJantina" id="jantina" value="1"/> Lelaki
                                    <s:radio name="pihak.kodJantina" id="jantina" value="2"/> Perempuan
                                </td>


                            </tr>

                            <c:if test="${actionBean.kodNegeri eq '04'}">
                                <tr>
                                    <td>
                                        Anak Tempatan :
                                    </td>
                                    <td>
                                        <s:radio name="pihak.anakTempatan" id="anakTempatan" value="Y" />Ya
                                        <s:radio name="pihak.anakTempatan" id="anakTempatan" value="T" />Tidak
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Tempoh tinggal di Melaka (tahun) : 
                                    </td>
                                    <td>
                                        <s:text name="pemohon.tempohMastautin" size="5" value="${actionBean.pemohon.tempohMastautin}" maxlength="3" onkeyup="validateNumber(this,this.value);"/>&nbsp;
                                    </td>
                                </tr>
                            </c:if>
                            <tr>
                                <td>
                                    <em>*</em>Kewarganegaraan :
                                </td>
                                <td>
                                    <s:select name="pihak.wargaNegara.kod" style="width:150px" value="${actionBean.pihak.wargaNegara.kod}"id="kod_warganegara">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                                    </s:select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Pekerjaan :
                                </td>
                                <td>
                                    <s:text name="pemohon.pekerjaan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>  
                                </td>

                            </tr>
                            <tr>
                                <td>
                                    Pendapatan Bulanan (RM) :
                                </td>
                                <td>
                                    <s:text name="pemohon.pendapatan" size="40" onkeyup="validateNumber(this,this.value);"/>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    Status Perkahwinan :
                                </td>
                                <td>
                                    <s:select name="pemohon.statusKahwin" id="statusKahwin">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:option value="Bujang">Bujang</s:option>
                                        <s:option value="Berkahwin">Berkahwin</s:option>
                                        <s:option value="Duda">Duda</s:option>
                                        <s:option value="Janda">Janda</s:option>
                                    </s:select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Tanggungan Anak :
                                </td>
                                <td>
                                    <s:text name="pemohon.tanggungan" size="10" maxlength="3" onkeyup="validateNumber(this,this.value);"/>  
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq '0'}">
                            <tr>
                                <td>
                                    <em>*</em>Nama :
                                </td>
                                <td>
                                    <s:text name="pihak.nama" id="nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <em>*</em>Jenis Pengenalan :
                                </td>
                                <td>
                                    <s:select name="pihak.jenisPengenalan.kod" id="jenisPengenalan" value="${actionBean.pihak.jenisPengenalan.kod}" disabled="true">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiJenisPengenalanPelupusan}" label="nama" value="kod"/>
                                    </s:select>
                                </td>
                            </tr>
                        </c:if>    
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'S'}">
                            <tr>
                                <td>
                                    <em>*</em>Nama Syarikat :
                                </td>
                                <td>
                                    <s:text name="pihak.nama" id="nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                                </td> 
                            </tr>
                            <tr>
                                <td> 
                                    <em>*</em>Jenis Pengenalan :
                                </td>
                                <td>
                                    <s:select name="pihak.jenisPengenalan.kod" id="jenisPengenalan"  disabled="true">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiJenisPengenalanPelupusan}" label="nama" value="kod"/>
                                    </s:select>
                                </td>                              
                            </tr>
                            <tr>
                                <td>
                                    <em>*</em>Nombor Syarikat :
                                </td>
                                <td>
                                    <s:text name="pihak.noPengenalan" id="kp" size="40" disabled="true"/> <em>[127776-V atau 432483-U]</em>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <em>*</em>Jenis Syarikat :
                                </td>
                                <td>
                                    <s:select name="pihak.bangsa.kod" style="width:246px">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${actionBean.senaraiKodBangsa}" label="nama" value="kod" />
                                    </s:select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <em>*</em>Tarikh Penubuhan :
                                </td>
                                <td>
                                    <s:text name="pihak.tarikhLahir" size="15" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy"/> <em>[hh/bb/tttt]</em>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'U'}">
                            <tr>
                                <td>
                                    <em>*</em>Nama Penubuhan :
                                </td>
                                <td>
                                    <s:text name="pihak.nama" id="nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                                </td> 
                            </tr>
                            <tr>
                                <td> 
                                    <em>*</em>Jenis Pengenalan :
                                </td>
                                <td>
                                    <s:select name="pihak.jenisPengenalan.kod" id="jenisPengenalan"  disabled="true">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiJenisPengenalanPelupusan}" label="nama" value="kod"/>
                                    </s:select>
                                </td>                              
                            </tr>
                            <tr>
                                <td>
                                    <em>*</em>Nombor Penubuhan :
                                </td>
                                <td>
                                    <s:text name="pihak.noPengenalan" id="kp" size="40" disabled="true"/> <em>[127776-V atau 432483-U]</em>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <em>*</em>Jenis Penubuhan :
                                </td>
                                <td>
                                    <s:select name="pihak.bangsa.kod" style="width:246px">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${actionBean.senaraiKodBangsa}" label="nama" value="kod" />
                                    </s:select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <em>*</em>Tarikh Penubuhan :
                                </td>
                                <td>
                                    <s:text name="pihak.tarikhLahir" size="15" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy"/> <em>[hh/bb/tttt]</em>
                                </td>
                            </tr>
                        </c:if>
                        <tr>
                            <td>
                                <em>*</em>Alamat Berdaftar :
                            </td>
                            <td>
                                <s:text name="pihak.alamat1" value="${actionBean.pihak.alamat1}" id="alamat1" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                &nbsp;
                            </td>
                            <td>
                                <s:text name="pihak.alamat2" value="${actionBean.pihak.alamat2}" id="alamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                &nbsp;
                            </td>
                            <td>
                                <s:text name="pihak.alamat3" value="${actionBean.pihak.alamat3}" id="alamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                &nbsp;
                            </td>
                            <td>
                                <s:text name="pihak.alamat4" value="${actionBean.pihak.alamat4}" id="alamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <em>*</em>Poskod :
                            </td>
                            <td>
                                <s:text name="pihak.poskod" value="${actionBean.pihak.poskod}" size="40" id="poskod" maxlength="5" onkeyup="validateNumber(this,this.value);"/> 
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <em>*</em>Negeri :
                            </td>
                            <td>
                                <s:select name="pihak.negeri.kod" id="negeri" >
                                    <s:option value="0">Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" />
                                </s:select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="checkbox" id="add1" name="add1" value="" onclick="copyAdd();"/>
                                <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                            </td>
                            <td>
                                &nbsp;
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <em>*</em>Alamat Surat-Menyurat :
                            </td>
                            <td>
                                <s:text name="pihakAlamatTambahan.alamatKetiga1" value="${actionBean.pihakAlamatTambahan.alamatKetiga1}" id="suratAlamat1" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                &nbsp;
                            </td>
                            <td>
                                <s:text name="pihakAlamatTambahan.alamatKetiga2" value="${actionBean.pihakAlamatTambahan.alamatKetiga2}" id="suratAlamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                &nbsp;
                            </td>
                            <td>
                                <s:text name="pihakAlamatTambahan.alamatKetiga3" value="${actionBean.pihakAlamatTambahan.alamatKetiga3}" id="suratAlamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                &nbsp;
                            </td>
                            <td>
                                <s:text name="pihakAlamatTambahan.alamatKetiga4" value="${actionBean.pihakAlamatTambahan.alamatKetiga4}" id="suratAlamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <em>*</em>Poskod :
                            </td>
                            <td>
                                <s:text name="pihakAlamatTambahan.alamatKetigaPoskod" size="40" maxlength="5" id="suratPoskod" onkeyup="validateNumber(this,this.value);"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <em>*</em>Negeri :
                            </td>
                            <td>
                                <s:select name="pihakAlamatTambahan.alamatKetigaNegeri.kod" id="suratNegeri">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                                </s:select>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'F'||actionBean.pihak.jenisPengenalan.kod eq 'N'}">
                                    No. Telefon (Rumah) :
                                </c:if>
                                <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'S' || actionBean.pihak.jenisPengenalan.kod eq 'U'}">
                                    No. Telefon (Ofis) :    
                                </c:if>
                                <c:if test="${actionBean.pihak.jenisPengenalan.kod eq '0'}">
                                    No. Telefon :    
                                </c:if>    
                            </td>
                            <td>
                                <s:text name="pihak.noTelefon1" id="noTelefon1" value="${actionBean.pihak.noTelefon1}"size="40" onkeyup="validateNumber(this,this.value);"/> <em>[0512345678]</em>
                            </td>
                        </tr>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'F'||actionBean.pihak.jenisPengenalan.kod eq 'N'}">
                            <tr>
                                <td>
                                    No. Telefon (Bimbit) :
                                </td>
                                <td>
                                    <s:text name="pihak.noTelefonBimbit" id="noTelefonBimbit" value="${actionBean.pihak.noTelefonBimbit}"size="40" onkeyup="validateNumber(this,this.value);"/> <em>[01712345678]</em>
                                </td>
                            </tr>
                        </c:if>
                        <tr>
                            <td>
                                Emel :
                            </td>
                            <td>
                                <s:text name="pihak.email" id="emel" value="${actionBean.pihak.email}"size="40"/> <em>[alamat_emel@gmail.com]</em>
                            </td>
                        </tr>
                    </table>
                    <br/>
                    <table class="tablecloth" border="0">
                        <tr>
                            <td colspan="2"><s:submit name="simpanPemohon" value="Simpan" class="btn" onclick="NoPrompt();"/>
                                <s:button name="Tambah" id="save" value="Carian Semula" class="longbtn" onclick="addPemohon()"/></td>
                        </tr>
                    </table>
                </div>
                <br/>

            </fieldset>
        </div>
    </s:form>
</body>
