<%-- 
    Document   : tambah_pemohon1
    Created on : Feb 11, 2010, 1:48:03 PM
    Author     : muhammad.amin
    Modified By: Rohan
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
       
        maximizeWindow(), $('.alphanumeric').alphanumeric(),
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'}),
    <%-- alert("pemohon");--%>
    <c:if test="${!flag}">
            opener.refreshMaklumatPemohon();
        <%-- alert("selfPemohon");--%>
                    self.close();
    </c:if>
           
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

         function savePemohon(event, f){
             // alert("save");

             var dt=document.getElementById("kp");
             var jenis=document.getElementById("jenisPengenalan").value;
            
             if(jenis == "0"){
                 alert("Sila Pilih Jenis Pengenalan");
                 return false;
             }
             if(dt.value == ""){
                 alert("Sila Masukkan Nombor Pengenalan");
                 return false;
             }

    <%--alert("Jenis:"+jenis);--%>
                     if(jenis == "B"){
                         if (isDate(dt.value)==false){
                             dt.focus()
                             return false
                         }else{
                             var q = $(f).formSerialize();
                             var url = f.action + '?' + event;
                             $.post(url,q,
                             function(data){
                                 $("#caw").html(data);
                             });
                         }
                     }else{
    <%--alert("savePemohon");--%>
                           var q = $(f).formSerialize();
                           var url = f.action + '?' + event;
                           $.post(url,q,
                           function(data){
                               $("#caw").html(data);
                           });

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


    <%--   function save(event, f){
            if(validateForm()){
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.close();
            },'html');
            }
        }--%>
        function doValidateAge(ic, id, id2,type){
    <%-- alert("--doValidateAge--");
     alert(ic);
     alert(id);
     alert(id2);
     alert("type:"+type);--%>
             var valid = true;
             var v = $('#'+id2).val();
             if(v == 'B'){
                 date = ic.substring(0,6);
                 yyyy = date.substring(0,2);
                 mm = date.substring(2,4);
                 dd = date.substring(4,6);
                 if(yyyy < 99 && yyyy > 30){//fixme :
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
<style type="text/css">
    input.error { background-color: yellow; }
</style>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<div class="subtitle" id="caw">
    <s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPemohon1ActionBean">

        <s:hidden name="pihak.idPihak"/>
        <s:hidden name="pihak.jenisPengenalan.kod"/>
        <s:hidden name="pihak.jenisPengenalan.nama"/>
        <s:hidden name="pihak.noPengenalan"/>
        <s:hidden name="pihak.bangsa.kod"/>
        <s:hidden name="permohonan.kodUrusan.kod"/>

        <c:if test="${tuanTanah}">
            <s:text name="tuanTanah" value="${tuanTanah}"/>
        </c:if>
        <div align="center">
            <s:errors/>
            <s:messages/>
        </div>
        <c:if test="${!actionBean.tambahCawFlag}">

            <fieldset class="aras1">
                <legend>Kemasukan Maklumat Pemohon</legend>
                <br/>
                <c:if test="${!actionBean.cariFlag}">
                    <p>
                        <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                        <s:select name="pihak.jenisPengenalan.kod" id="jenisPengenalan"  onchange="javaScript:changeHideSuku(this.options[selectedIndex].text)">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiJenisPengenalanPelupusan}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <p>
                        <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                        <s:text name="pihak.noPengenalan" id="kp" size="40" onkeyup="dodacheck(this.value);"
                                onblur="doUpperCase(this.id);doCheckUmur(this.value, this.id);"/> 
                        <font title="No KP Baru: 780104069871, No KP Lama: A2977884, No Syarikat: 115793-P, No Pertubuhan: 432483-U"><em>[780104069871]</em></font>

                    </p>

                    <p>
                        <label>&nbsp;</label>

                        <%--<s:submit name="cariPihakPemohon" value="Cari" id="cari" class="btn"/>--%>
                        <s:button name="cariPihakPemohon" value="Cari" class="btn" onclick="savePemohon(this.name, this.form);"/>
                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    </p>
                </c:if>

                <c:if test="${actionBean.cariFlag}">
                    <c:if test="${!actionBean.tiadaDataFlag}">

                        <font color ="red">Sila Masukkan Maklumat Yang Bertanda *</font>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'F'||actionBean.pihak.jenisPengenalan.kod eq 'N'}">
                            <p>
                                <label for="nama"><font color="red" >*</font>Nama :</label>
                                <s:text name="pihak.nama" size="40"  onkeyup="this.value=this.value.toUpperCase();"/>

                            </p>

                        </c:if>

                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'S'}">
                            <p>
                                <label for="nama"><font color="red" >*</font>Nama Syarikat:</label>
                                <s:text name="pihak.nama" size="40"  onkeyup="this.value=this.value.toUpperCase();"/>

                            </p>
                        </c:if>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'U'}">
                            <p>
                                <label for="nama"><font color="red" >*</font>Nama Pertubuhan:</label>
                                <s:text name="pihak.nama" size="40"  onkeyup="this.value=this.value.toUpperCase();"/>

                            </p>
                        </c:if>
                        <p>
                            <label for="Jenis Pengenalan">Jenis Pengenalan :</label>
                            <s:hidden name="pihak.jenisPengenalan.nama"/>
                            ${actionBean.pihak.jenisPengenalan.nama}&nbsp;
                        </p>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'S'}">
                            <p>
                                <label for="No Pengenalan">Nombor Syarikat :</label>
                                <s:hidden name="pihak.noPengenalan"/>
                                ${actionBean.pihak.noPengenalan}&nbsp;
                            </p>
                        </c:if>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'U'}">
                            <p>
                                <label for="No Pengenalan">Nombor Pertubuhan :</label>
                                <s:hidden name="pihak.noPengenalan"/>
                                ${actionBean.pihak.noPengenalan}&nbsp;
                            </p>
                        </c:if>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'F'||actionBean.pihak.jenisPengenalan.kod eq 'N'}">
                            <p>
                                <label for="No Pengenalan">Nombor Pengenalan :</label>
                                <s:hidden name="pihak.noPengenalan"/>
                                ${actionBean.pihak.noPengenalan}&nbsp;
                            </p>
                        </c:if>


                        <%--   <p>
                           <label><font color="red">*</font>Bangsa / Jenis Syarikat3 :</label>
                           <s:select name="pihak.bangsa.kod" style="width:300px">
                               <s:option value="">Sila Pilih</s:option>
                               <s:options-collection collection="${actionBean.senaraiKodBangsa}" label="nama" value="kod" />
                           </s:select>
                       </p>--%>

                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'S'|| actionBean.pihak.jenisPengenalan.kod eq 'U'}">
                            <p>
                                <label><font color="red">*</font>Jenis Syarikat :</label>
                                <s:select name="pihak.bangsa.kod" style="width:246px">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${actionBean.senaraiKodBangsa}" label="nama" value="kod" />
                                </s:select>
                            </p>
                            <p>
                                <label><font color="red">*</font>Tarikh Penubuhan :</label>
                                <s:text name="pihak.tarikhLahir" size="40" id="datepicker" class="datepicker"/> <em>[hh/bb/tttt]</em>
                            </p>
                        </c:if>

                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'F'||actionBean.pihak.jenisPengenalan.kod eq 'N'}">
                            <p>
                                <label>Bangsa :</label>
                                <s:select name="pihak.bangsa.kod" style="width:246px">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${actionBean.senaraiKodBangsa}" label="nama" value="kod" />
                                </s:select>
                            </p>
                        </c:if>


                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'F'||actionBean.pihak.jenisPengenalan.kod eq 'N'}">
                            <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B'}">
                                <p>
                                    <label>Warna Kad Pengenalan :</label>
                                    <s:select name="pihak.warnaKP" id="warnaKP" value="${actionBean.pihak.warnaKP}" style="width:100px">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:option value="B">Biru</s:option>
                                        <s:option value="C">Coklat</s:option>
                                        <s:option value="H">Hijau</s:option>
                                        <s:option value="M">Merah</s:option>
                                        <s:option value="L">Lain-lain</s:option>
                                    </s:select>
                                </p>
                            </c:if>

                            <%--  <p>
                                  <label><font color="red">*</font>Tarikh Lahir :</label>
                                  <s:text name="pihak.tarikhLahir" size="40" id="datepicker" class="datepicker" onchange="calAgeByDOB1(this.value);"formatPattern="dd/MM/yyyy"/>
                              </p>
                              <p>
                                  <label><font color="red">*</font>Umur1 :</label>
                                  <s:hidden name="pemohon.idPemohon"/>
                                  <s:text name="pemohon.umur" size="10" maxlength="3" id="umur" onkeyup="validateNumber(this,this.value);"/>
                              </p>--%>


                            <p>
                                <label>Tarikh Lahir :</label>
                                <s:text name="pihak.tarikhLahir" size="40" id="datepicker" class="datepicker" onchange="calAgeByDOB1(this.value);"formatPattern="dd/MM/yyyy"/> <em>[hh/bb/tttt]</em>
                            </p>

                            <p>
                                <label>Umur :</label>
                                <s:hidden name="pemohon.idPemohon"/>
                                <s:text name="pemohon.umur" size="10" maxlength="3" id="umur" onkeyup="validateNumber(this,this.value);"/>
                            </p>


                            <p>
                                <label>
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PRMP'}">
                                        <font color="red">*</font>
                                    </c:if>
                                    Tempat Lahir :
                                </label>
                                <s:text name="pihak.tempatLahir" size="40" value="${actionBean.pihak.tempatLahir}" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <p>
                                <label>Jantina :</label>
                                <s:radio name="pihak.kodJantina" id="jantina" value="1"/> Lelaki
                                <s:radio name="pihak.kodJantina" id="jantina" value="2"/> Perempuan


                            </p>

                            <c:if test="${Anak}">
                                <p>
                                    <label>
                                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PRMP'}">

                                        </c:if>
                                        Anak Tempatan :</label>
                                    <s:radio name="pihak.anakTempatan" id="anakTempatan" value="Y" />YA
                                    <s:radio name="pihak.anakTempatan" id="anakTempatan" value="T" />TIDAK

                                </p>

                                <p>
                                    <label>
                                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PRMP'}">

                                        </c:if>
                                        Tempoh tinggal di Melaka (tahun) :
                                    </label>
                                    <s:text name="pemohon.tempohMastautin" size="5" value="${actionBean.pemohon.tempohMastautin}" maxlength="3" onkeyup="validateNumber(this,this.value);"/>&nbsp;
                                </p>
                            </c:if>

                            <p>
                                <label>Kewarganegaraan :</label>
                                <s:select name="pihak.wargaNegara.kod" style="width:150px" value="${actionBean.pihak.wargaNegara.kod}"id="kod_warganegara">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                                </s:select>
                            </p>

                            <p>
                                <label>
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PRMP'}">

                                    </c:if>
                                    Pekerjaan :
                                </label>
                                <s:text name="pemohon.pekerjaan" size="40" value="${actionBean.pemohon.pekerjaan}" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label>
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PRMP'}">

                                    </c:if>
                                    Pendapatan Bulanan (RM):
                                </label>
                                <s:text name="pemohon.pendapatan" size="40"  formatPattern="#,##0.00" value="${actionBean.pemohon.pendapatan}" onkeyup="validateNumber(this,this.value);"/>
                            </p>

                            <p>
                                <label>
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PRMP'}">

                                    </c:if>
                                    Status Perkahwinan:
                                </label>
                                <s:select name="pemohon.statusKahwin" id="statusKahwin">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:option value="Bujang">Bujang</s:option>
                                    <s:option value="Berkahwin">Berkahwin</s:option>
                                    <s:option value="Duda">Duda</s:option>
                                    <s:option value="Janda">Janda</s:option>
                                </s:select>
                            </p>
                            <p>
                                <label>
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PRMP'}">

                                    </c:if>
                                    Tanggungan :
                                </label>
                                <s:text name="pemohon.tanggungan" size="10" maxlength="3" value="${actionBean.pemohon.tanggungan}"  onkeyup="validateNumber(this,this.value);"/>
                            </p>

                        </c:if>

                        <%-- add modal berbayar (Hairudin - 6 May 2010) --%>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'F'||actionBean.pihak.jenisPengenalan.kod eq 'N'}">
                            <p>
                                <label for="alamat"><font color="red">*</font>Alamat Berdaftar :</label>
                                <s:text name="pihak.alamat1" value="${actionBean.pihak.alamat1}" id="alamat1" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label> &nbsp;</label>
                                <s:text name="pihak.alamat2" value="${actionBean.pihak.alamat2}"size="40" id="alamat2" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label> &nbsp;</label>
                                <s:text name="pihak.alamat3" value="${actionBean.pihak.alamat3}"size="40"  id="alamat3" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label> &nbsp;</label>
                                <s:text name="pihak.alamat4" value="${actionBean.pihak.alamat4}"size="40"  id="alamat4"  onkeyup="this.value=this.value.toUpperCase();"/>

                            </p>

                            <p>
                                <label><font color="red">*</font>Poskod :</label>
                                <s:text name="pihak.poskod" value="${actionBean.pihak.poskod}" size="40" id="poskod" maxlength="5" onkeyup="validateNumber(this,this.value);"/>              </p>
                            <p>
                                <label><font color="red">*</font>Negeri :</label>
                                <s:select name="pihak.negeri.kod" id="negeri" >
                                    <s:option value="0">Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" />
                                </s:select>
                            </p>
                            <p>
                                <label>&nbsp;</label>
                                <input type="checkbox" id="add1" name="add1" value="" onclick="copyAdd();"/>
                                <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                            </p>
                        </c:if>
                        <p>
                            <label for="alamat"><font color="red">*</font>Alamat Surat-Menyurat :</label>
                            <s:text name="pihak.suratAlamat1" size="40" id="suratAlamat1"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat2" size="40" id="suratAlamat2"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat3" size="40" id="suratAlamat3"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat4" size="40" id="suratAlamat4"/>
                        </p>
                        <p>
                            <label><font color="red">*</font>Poskod :</label>
                            <s:text name="pihak.suratPoskod" size="40" maxlength="5" id="suratPoskod" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri"><font color="red">*</font>Negeri :</label>
                            <s:select name="pihak.suratNegeri.kod" id="suratNegeri">
                                <s:option value="0">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <%-- add no. telefon and faksimili (Hairudin - 16 May 2010) --%>

                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'F'||actionBean.pihak.jenisPengenalan.kod eq 'N'}">
                            <p>
                                <label>No. Telefon (Rumah)</label>
                                <s:text name="pihak.noTelefon1" id="noTelefon1" value="${actionBean.pihak.noTelefon1}"size="40" onkeyup="validateNumber(this,this.value);"/> <em>[0512345678]</em>
                            </p>
                            <p>
                                <label>No. Telefon (Bimbit)</label>
                                <s:text name="pihak.noTelefonBimbit" id="noTelefonBimbit" value="${actionBean.pihak.noTelefonBimbit}"size="40" onkeyup="validateNumber(this,this.value);"/> <em>[01712345678]</em>
                            </p>

                            <p>
                                <label>Emel :</label>
                                <s:text name="pihak.email" id="emel" value="${actionBean.pihak.email}"size="40"/> <em>[alamat_emel@gmail.com]</em>
                            </p>
                        </c:if>

                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'S' || actionBean.pihak.jenisPengenalan.kod eq 'U'}">
                            <p>
                                <label>No. Telefon (Office)</label>
                                <s:text name="pihak.noTelefon1" id="noTelefon1" value="${actionBean.pihak.noTelefon1}"size="40" onkeyup="validateNumber(this,this.value);"/> <em>[0512345678]</em>
                            </p>
                            <p>
                                <label>Emel :</label>
                                <s:text name="pihak.email" id="emel" value="${actionBean.pihak.email}"size="40"/> <em>[alamat_emel@gmail.com]</em>
                            </p>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTD' || actionBean.permohonan.kodUrusan.kod eq 'PBPTG' || actionBean.permohonan.kodUrusan.kod eq 'PPBB'}">
                            <p>
                                <label for="Bank">Jenis Bank :</label>
                                <s:select name="pihak.bank.kod" id="jenisBank" value="${actionBean.pihak.bank.kod}">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodBank}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                            <p>
                                <label>No. Akaun Bank :</label>
                                <s:text name="pihak.noAkaunBank" id="noAkaunBank" value="${actionBean.pihak.noAkaunBank}" size="28" onkeyup="validateNumber(this,this.value);"></s:text>
                            </p>
                        </c:if>

                        <p>
                            <label>No. Faksimili</label>
                            <s:text name="pihak.noTelefon2" id="noTelefon2" value="${actionBean.pihak.noTelefon2}"size="40" onkeyup="validateNumber(this,this.value);"/> <em>[0512345678]</em>
                        </p>


                        <%-- add alamat majikan (Hairudin - 6 May 2010) --%>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">

                            <p id="majikan">
                                <label>Nama/Majikan :</label>
                                <s:text name="pemohon.institusi" value="${actionBean.pemohon.institusi}" id="majikan" size="40" onkeyup="this.value=this.value.toUpperCase();" />
                            </p>
                            <p id="institusiAlamat1">
                                <label for="alamat">Alamat Majikan: </label>
                                <s:text name="pemohon.institusiAlamat1" id="institusiAlamat1" size="40" onkeyup="this.value=this.value.toUpperCase();" />
                            </p>
                            <p id="institusiAlamat2">
                                <label> &nbsp;</label>
                                <s:text name="pemohon.institusiAlamat2" id="institusiAlamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p id="institusiAlamat3">
                                <label> &nbsp;</label>
                                <s:text name="pemohon.institusiAlamat3" id="institusiAlamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p id="institusiAlamat4">
                                <label> &nbsp;</label>
                                <s:text name="pemohon.institusiAlamat4" id="institusiAlamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <p>
                                <label>Poskod :</label>
                                <s:text name="pemohon.institusiPoskod"  id ="institusiPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                            </p>

                            <p id="majikanNegeri">
                                <label for="Negeri">Negeri :</label>
                                <s:select name="pihak.negeriKelahiran.kod" id="majikanNegeri">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                        </c:if>

                    </c:if>
                    <%--tiada data--%>
                    <c:if test="${actionBean.tiadaDataFlag}">

                        <font color="red">Sila Masukkan Maklumat Yang Bertanda *</font>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'S'}">
                            <p>
                                <label for="nama"><font color="red">*</font>Nama Syarikat:</label>
                                <s:text name="pihak.nama" id="nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <p>
                                <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                <s:select name="pihak.jenisPengenalan.kod" id="jenisPengenalan"  onchange="javaScript:changeHideSuku(this.options[selectedIndex].text)">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiJenisPengenalanPelupusan}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                            <p>
                                <label for="No Pengenalan"><font color="red">*</font>Nombor Syarikat :</label>
                                <s:text name="pihak.noPengenalan" id="kp" size="40"/> <em>[127776-V atau 432483-U]</em>
                            </p>

                            <p>
                                <label><font color="red">*</font>Jenis Syarikat :</label>
                                <s:select name="pihak.bangsa.kod" style="width:246px">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${actionBean.senaraiKodBangsa}" label="nama" value="kod" />
                                </s:select>
                            </p>
                            <p>
                                <label><font color="red">*</font>Tarikh Penubuhan :</label>
                                <s:text name="pihak.tarikhLahir" size="40" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy"/> <em>[hh/bb/tttt]</em>
                            </p>
                        </c:if>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'U'}">
                            <p>
                                <label for="nama"><font color="red">*</font>Nama Pertubuhan:</label>
                                <s:text name="pihak.nama" id="nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <p>
                                <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                <s:select name="pihak.jenisPengenalan.kod" id="jenisPengenalan"  onchange="javaScript:changeHideSuku(this.options[selectedIndex].text)">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiJenisPengenalanPelupusan}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                            <p>
                                <label for="No Pengenalan"><font color="red">*</font>Nombor Pertubuhan :</label>
                                <s:text name="pihak.noPengenalan" id="kp" size="40"/> <em>[127776-V atau 432483-U]</em>
                            </p>

                            <p>
                                <label><font color="red">*</font>Jenis Pertubuhan :</label>
                                <s:select name="pihak.bangsa.kod" style="width:246px">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${actionBean.senaraiKodBangsa}" label="nama" value="kod" />
                                </s:select>
                            </p>
                            <p>
                                <label><font color="red">*</font>Tarikh Penubuhan :</label>
                                <s:text name="pihak.tarikhLahir" size="40" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy"/> <em>[hh/bb/tttt]</em>
                            </p>
                        </c:if>



                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'F'||actionBean.pihak.jenisPengenalan.kod eq 'N'}">

                            <p>
                                <label for="nama"><font color="red">*</font>Nama :</label>
                                <s:text name="pihak.nama" id="nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>

                            <p>
                                <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                <s:select name="pihak.jenisPengenalan.kod" id="jenisPengenalan"  onchange="javaScript:changeHideSuku(this.options[selectedIndex].text)">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiJenisPengenalanPelupusan}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                            <p>
                                <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                                <s:text name="pihak.noPengenalan" id="kp" size="40"/> <em>[700304059873 atau A2977884]</em>
                            </p>

                            <p>
                                <label>Bangsa :</label>
                                <s:select name="pihak.bangsa.kod" style="width:246px">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${actionBean.senaraiKodBangsa}" label="nama" value="kod" />
                                </s:select>
                            </p>

                            <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B'}">
                                <p>
                                    <label>Warna Kad Pengenalan :</label>
                                    <s:select name="pihak.warnaKP" id="warnaKP" value="${actionBean.pihak.warnaKP}" style="width:100px">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:option value="B">Biru</s:option>
                                        <s:option value="C">Coklat</s:option>
                                        <s:option value="H">Hijau</s:option>
                                        <s:option value="M">Merah</s:option>
                                        <s:option value="L">Lain-lain</s:option>
                                    </s:select>
                                </p>
                            </c:if>

                            <p>
                                <label>Tarikh Lahir :</label>
                                <s:text name="pihak.tarikhLahir" size="40" id="datepicker" class="datepicker" onchange="calAgeByDOB1(this.value);"/> <em>[hh/bb/tttt]</em>
                            </p>

                            <p>
                                <label>Umur :</label>
                                <s:hidden name="pemohon.idPemohon"/>
                                <s:text name="pemohon.umur" size="10" maxlength="3" id="umur" onkeyup="validateNumber(this,this.value);"/>
                            </p>

                            <p>
                                <label>Tempat Lahir :</label>
                                <s:text name="pihak.tempatLahir" size="40"onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>


                            <p>
                                <label>Jantina :</label>
                                <s:radio name="pihak.kodJantina" id="jantina" value="1"/> Lelaki
                                <s:radio name="pihak.kodJantina" id="jantina" value="2"/> Perempuan

                            </p>

                            <c:if test="${Anak}">
                                <p>
                                    <label>
                                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PRMP'}">
                                            
                                        </c:if>
                                        Anak Tempatan :
                                    </label>
                                    <s:radio name="pihak.anakTempatan" id="anakTempatan" value="Y" />YA
                                    <s:radio name="pihak.anakTempatan" id="anakTempatan" value="T" />TIDAK

                                </p>
                                <p>
                                    <label>
                                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PRMP'}">
                                            
                                        </c:if>
                                        Tempoh tinggal di Melaka (tahun):
                                    </label>
                                    <s:text name="pemohon.tempohMastautin" size="5" value="${actionBean.pemohon.tempohMastautin}" maxlength="3" onkeyup="validateNumber(this,this.value);"/>&nbsp;
                                </p>
                            </c:if>


                            <p>
                                <label>Kewarganegaraan :</label>
                                <s:select name="pihak.wargaNegara.kod" style="width:150px" value="${actionBean.pihak.wargaNegara.kod}"id="kod_warganegara">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                            <p>

                            <p>
                                <label>
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PRMP'}">
                                        
                                    </c:if>
                                    Pekerjaan:
                                </label>
                                <s:text name="pemohon.pekerjaan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label>
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PRMP'}">
                                        
                                    </c:if>
                                    Pendapatan Bulanan (RM):
                                </label>
                                <s:text name="pemohon.pendapatan" size="40" onkeyup="validateNumber(this,this.value);"/>
                            </p>

                            <p>
                                <label>
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PRMP'}">
                                        
                                    </c:if>
                                    Status Perkahwinan:
                                </label>
                                <s:select name="pemohon.statusKahwin" id="statusKahwin">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:option value="Bujang">Bujang</s:option>
                                    <s:option value="Berkahwin">Berkahwin</s:option>
                                    <s:option value="Duda">Duda</s:option>
                                    <s:option value="Janda">Janda</s:option>
                                </s:select>
                            </p>
                            <p>
                                <label>
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PRMP'}">
                                        
                                    </c:if>
                                    Tanggungan :
                                </label>
                                <s:text name="pemohon.tanggungan" size="10" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                            </p>

                        </c:if>

                        <%-- add modal berbayar (Hairudin - 6 May 2010) --%>
                        <%-- <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'S' || actionBean.pihak.jenisPengenalan.kod eq 'U'}">

                        </c:if>--%>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'F'||actionBean.pihak.jenisPengenalan.kod eq 'N'}">
                            <p>
                                <label for="alamat"><font color="red">*</font>Alamat Berdaftar :</label>
                                <s:text name="pihak.alamat1" value="${actionBean.pihak.alamat1}" id="alamat1" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label> &nbsp;</label>
                                <s:text name="pihak.alamat2" value="${actionBean.pihak.alamat2}"size="40" id="alamat2" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label> &nbsp;</label>
                                <s:text name="pihak.alamat3" value="${actionBean.pihak.alamat3}"size="40"  id="alamat3" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label> &nbsp;</label>
                                <s:text name="pihak.alamat4" value="${actionBean.pihak.alamat4}"size="40"  id="alamat4"  onkeyup="this.value=this.value.toUpperCase();"/>

                            </p>

                            <p>
                                <label><font color="red">*</font>Poskod :</label>
                                <s:text name="pihak.poskod" value="${actionBean.pihak.poskod}" size="40" id="poskod" maxlength="5" onkeyup="validateNumber(this,this.value);"/>              </p>
                            <p>
                                <label><font color="red">*</font>Negeri :</label>
                                <s:select name="pihak.negeri.kod" id="negeri" >
                                    <s:option value="0">Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" />
                                </s:select>
                            </p>
                            <p>
                                <label>&nbsp;</label>
                                <input type="checkbox" id="add1" name="add1" value="" onclick="copyAdd();"/>
                                <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                            </p>
                        </c:if>
                        <p>
                            <label for="alamat"><font color="red">*</font>Alamat Surat-Menyurat :</label>
                            <s:text name="pihak.suratAlamat1" size="40" id="suratAlamat1" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat2" size="40" id="suratAlamat2" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat3" size="40" id="suratAlamat3" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat4" size="40" id="suratAlamat4" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label><font color="red">*</font>Poskod :</label>
                            <s:text name="pihak.suratPoskod" size="40" maxlength="5" id="suratPoskod" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri"><font color="red">*</font>Negeri :</label>
                            <s:select name="pihak.suratNegeri.kod" id="suratNegeri">
                                <s:option value="0">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <%-- add no. telefon and faksimili (Hairudin - 16 May 2010) --%>

                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'F'||actionBean.pihak.jenisPengenalan.kod eq 'N'}">
                            <p>
                                <label>No. Telefon (Rumah)</label>
                                <s:text name="pihak.noTelefon1" id="noTelefon1" value="${actionBean.pihak.noTelefon1}"size="40" onkeyup="validateNumber(this,this.value);"/> <em>[0512345678]</em>
                            </p>
                        </c:if>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'S' || actionBean.pihak.jenisPengenalan.kod eq 'U'}">
                            <p>
                                <label>No. Telefon (Office)</label>
                                <s:text name="pihak.noTelefon1" id="noTelefon1" value="${actionBean.pihak.noTelefon1}"size="40" onkeyup="validateNumber(this,this.value);"/> <em>[0512345678]</em>
                            </p>
                            <p>
                                <label>Emel :</label>
                                <s:text name="pihak.email" id="emel" value="${actionBean.pihak.email}"size="40"/>
                            </p>
                        </c:if>

                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'F'||actionBean.pihak.jenisPengenalan.kod eq 'N'}">
                            <p>
                                <label>No. Telefon (Bimbit)</label>
                                <s:text name="pihak.noTelefonBimbit" id="noTelefonBimbit" value="${actionBean.pihak.noTelefonBimbit}"size="40" onkeyup="validateNumber(this,this.value);"/> <em>[01712345678]</em>
                            </p>


                            <p>
                                <label>Emel :</label>
                                <s:text name="pihak.email" id="emel" value="${actionBean.pihak.email}"size="40"/> <em>[alamat_emel@gmail.com]</em>
                            </p>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTD' || actionBean.permohonan.kodUrusan.kod eq 'PBPTG' || actionBean.permohonan.kodUrusan.kod eq 'PPBB'}">
                            <p>
                                <label for="Bank">Jenis Bank :</label>
                                <s:select name="pihak.bank.kod" id="jenisBank">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodBank}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                            <p>
                                <label>No. Akaun Bank :</label>
                                <s:text name="pihak.noAkaunBank" id="noAkaunBank" value="${actionBean.pihak.noAkaunBank}" size="28" onkeyup="validateNumber(this,this.value);"></s:text>
                            </p>
                        </c:if>

                        <p>
                            <label>No. Faksimili</label>
                            <s:text name="pihak.noTelefon2" id="noTelefon2" value="${actionBean.pihak.noTelefon2}"size="40" onkeyup="validateNumber(this,this.value);"/> <em>[0512345678]</em>
                        </p>

                        <%-- add alamat majikan (Hairudin - 6 May 2010) --%>


                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'F'||actionBean.pihak.jenisPengenalan.kod eq 'N'}">
                            <p id="majikan">
                                <label>Nama/Majikan :</label>
                                <s:text name="pemohon.institusi" value="${actionBean.pemohon.institusi}" id="majikan" size="40" onkeyup="this.value=this.value.toUpperCase();" />
                            </p>



                            <p id="institusiAlamat1">
                                <label for="alamat">Alamat Majikan: </label>
                                <s:text name="pemohon.institusiAlamat1" id="institusiAlamat1" size="40" onkeyup="this.value=this.value.toUpperCase();" />
                            </p>
                            <p id="institusiAlamat2">
                                <label> &nbsp;</label>
                                <s:text name="pemohon.institusiAlamat2" id="institusiAlamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p id="institusiAlamat3">
                                <label> &nbsp;</label>
                                <s:text name="pemohon.institusiAlamat3" id="institusiAlamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p id="institusiAlamat4">
                                <label> &nbsp;</label>
                                <s:text name="pemohon.institusiAlamat4" id="institusiAlamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label>Poskod :</label>
                                <s:text name="pemohon.institusiPoskod"  id ="institusiPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Negeri :</label>
                                <s:select name="pemohon.institusiNegeri.kod" id="negeri" >
                                    <s:option value="">Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" />
                                </s:select>
                            </p>

                        </c:if>
                    </c:if>
                </c:if>

                <%--              <c:if test="${!actionBean.cariFlag}">
                                  <p>
                                      <label>&nbsp;</label>

                        <s:submit name="cariPihakPemohon" value="Cari" id="cari" class="btn"/>
                        <s:button name="cariPihakPemohon" value="Cari" class="btn" onclick="savePemohon(this.name, this.form);"/>
                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    </p>
                </c:if>--%>

                <c:if test="${actionBean.cariFlag}">
                    <p>
                        <label>&nbsp;</label>
                        <s:submit name="simpanPemohonPopup" id="simpan" value="Simpan" class="btn" />
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod ne 'B' && actionBean.pihak.jenisPengenalan.kod ne 'L' && actionBean.pihak.jenisPengenalan.kod ne 'P' && actionBean.pihak.jenisPengenalan.kod ne 'T' && actionBean.pihak.jenisPengenalan.kod ne 'I'}">
                            <%-- <s:submit name="tambahCawanganPemohon" value="Tambah Cawangan" class="btn"/>--%>
                        </c:if>

                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    </p>
                </c:if>
                <br>
            </fieldset>
        </c:if>
    </s:form>
</div>
<script type="text/javascript">
    $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'})
    var v = '${actionBean.pihak.jenisPengenalan.kod}';
    if(v == "B"){
        var icNo = '${actionBean.pihak.noPengenalan}';
        $('#datepicker').val(icNo.substring(4,6)+"/"+icNo.substring(2,4)+"/"+19 + icNo.substring(0,2));
        var age = calculateAge(icNo.substring(4,6), icNo.substring(2,4), 19 + icNo.substring(0,2));
        $('#umur').val(age);
        document.getElementById("umur").value =age;
    }
</script>
