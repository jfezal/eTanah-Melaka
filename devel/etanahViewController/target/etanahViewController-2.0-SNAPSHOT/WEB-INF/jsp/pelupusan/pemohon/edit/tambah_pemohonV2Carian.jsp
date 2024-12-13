<%-- 
    Document   : tambah_pemohonV2Carian
    Created on : Jun 14, 2013, 9:59:07 AM
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
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
    <c:if test="${fn:length(actionBean.listHakmilik) > 0 }">
            $('#cariPemilik').hide();
            $('#cariPihak').hide();
    </c:if>
    <c:if test="${fn:length(actionBean.listHakmilik) == 0 }">
            $('#cariPemilik').hide();
            $('#cariPihak').show();
    </c:if>    
//            $('#cari').attr("disabled", "true");
            $('#cari').hide();
            $('#tambah').hide() ;
        });
    
        function showCarian(){
            $('#cariPemilik').hide();
            $('#cariPihak').show();
        }
    
        function showLain(){
            $('#cariPemilik').show();
            $('#cariPihak').hide();
        }
    
        function doCheckUmur(v,id,type){
            var va = $('#jenisPengenalan').val();
            if(va == 'B'){
                return doValidateAge(v, id, 'jenisPengenalan',type);
            }else {
                return true;
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
            }//else{
            // $('#kp').attr("maxlength","30");
            // }
        }


        function doValidateAge(ic, id, id2,type){
  
            var valid = true;
            var v = $('#'+id2).val();
            if(v == 'B'){
                var date = ic.substring(0,6);
                var yyyy = date.substring(0,2);
                var mm = date.substring(2,4);
                var dd = date.substring(4,6);
                if(yyyy < 99 && yyyy > 30){//fixme :
                    yyyy = "19" + yyyy;
                }else {
                    yyyy = "20" + yyyy;
                }



                var days = new Date();
                var gdate = days.getDate();
                var gmonth = days.getMonth();
                var gyear = days.getFullYear();
                var age=gyear-yyyy;

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
    
        function checkPemohon(){

            var dt=document.getElementById("kp");
            var jenis=document.getElementById("jenisPengenalan").value;
            
//            if(jenis == "1"){
//                alert("Sila Pilih Jenis Pengenalan");
//                $('#cari').attr("disabled", "true");
//            }
            
            if(jenis == "0"){
                $('#cari').hide() ;
                $('#tambah').show() ;
                $('#kpeng').hide();
            }else{
                $('#cari').show();
                $('#tambah').hide() ;
                $('#kpeng').show();
            }
            
        }
        
//        function checkIc() {
//            var dt=document.getElementById("kp");
//            if(dt.value == ""){
//                alert("Sila Masukkan Nombor Pengenalan");
//                $('#cari').attr("disabled", "true");
//            }else{
//                $('#cari').removeAttr("disabled");
//            }
//        }
        function openFrame(type){

            NoPrompt();

            window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohonV2?openFrame&type="+type, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
        
        function checkIC(){
            var jenis=document.getElementById("jenisPengenalan").value;
            if(jenis == "B"){
                if (isDate(dt.value)==false){
                    dt.focus()
                }
            }
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
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPemohonV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Carian Pemohon</legend>
                <div class="content" align="center">
                    <table class="tablecloth" border="0">
                        <c:if test="${fn:length(actionBean.listHakmilik) > 0 }">
                            <tr>
                                <td><s:radio name="radiomate" id="radiomate" value="P" onclick="showLain()"/></td>
                                <td>Pemohon adalah Pemilik Tanah</td>
                            </tr>  

                            <tr>
                                <td><s:radio name="radiomate" id="radiomate" value="B" onclick="showCarian();"/></td>
                                <td>Pemohon Baru</td>
                            </tr>
                        </c:if>
                    </table>
                </div>
                <br/>
                <div id="cariPemilik" align="center">
                    <table class="tablecloth" border="0">
                        <tr>
                            <td colspan="2">
                                <s:submit name="showPemilik" value="Seterusnya" class="btn"/>
                                <s:button name="" id="Kembali" value="Kembali" class="btn" onclick="openFrame('tPemohon')"/>
                            </td>
                        </tr>
                    </table>
                </div>              
                <div id="cariPihak" align="center">
                    <table class="tablecloth" border="0">
                        <tr>
                            <td><font color="red">*</font>Jenis Pengenalan :</td>
                            <td><s:select name="pihak.jenisPengenalan.kod" id="jenisPengenalan"  onchange="checkPemohon();" >
                                    <s:option value="1">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiJenisPengenalanPelupusan}" label="nama" value="kod"/>
                                </s:select>
                            </td>
                        </tr>
                        <tr id="kpeng">
                            <td><font color="red">*</font>Nombor Pengenalan :</td>
                            <td>
                                <s:text name="pihak.noPengenalan" id="kp" size="40" onkeyup="dodacheck(this.value);"
                                        onblur="doUpperCase(this.id);doCheckUmur(this.value, this.id);" onchange="checkIC();"/><br/> 
                                <em>No KP Baru: 780104069871, No KP Lama: A2977884, No Syarikat: 115793-P, No Pertubuhan: 432483-U</em>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <s:submit name="cariPihakPemohon" value="Cari" class="btn" id="cari"/>
                                <s:submit name="tambahTidakBerkenaan" value="Tambah" class="btn" id="tambah"/>
                                <s:button name="" id="Kembali" value="Kembali" class="btn" onclick="openFrame('tPemohon')"/>
                            </td>
                        </tr>
                    </table>
                </div> 
            </fieldset>


        </div>

    </s:form>
</body>

