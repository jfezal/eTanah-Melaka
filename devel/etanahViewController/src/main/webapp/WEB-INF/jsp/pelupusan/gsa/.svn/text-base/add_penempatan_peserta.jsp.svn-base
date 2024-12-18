<%-- 
    Document   : add_penempatan_peserta
    Created on : Sep 14, 2012, 3:33:45 PM
    Author     : Navin
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
            opener.refreshPnmptnPsrta();
            self.close();
    </c:if>

            $('.alphanumeric').alphanumeric();
            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});



//                var icNo = '${actionBean.pnmptnPsrta.noPengenalan}';
//                $('#datepicker').val(icNo.substring(4,6)+"/"+icNo.substring(2,4)+"/"+19 + icNo.substring(0,2));
//                var age = calculateAge(icNo.substring(4,6), icNo.substring(2,4), 19 + icNo.substring(0,2));
//                $('#umur').val(age);
//                // document.getElementById("umur").value =age;
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
        
        
        var strPass = val;
        var strLength = strPass.length;
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



</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
    <s:form beanclass="etanah.view.stripes.pelupusan.PenempatanPesertaActionBean">

        <s:hidden name="pnmptnPsrta.nama"/>
        <s:hidden name="pnmptnPsrta.idPenempatanPeserta"/>
        <s:hidden name="pnmptnPsrta.noPengenalan"/>    
        <s:hidden name="pnmptnPsrta.umur"/>
        <s:hidden name="pnmptnPsrta.alamat1"/>
        <s:hidden name="pnmptnPsrta.alamat2"/>
        <s:hidden name="pnmptnPsrta.alamat3"/>
        <s:hidden name="pnmptnPsrta.alamat4"/>
        <s:hidden name="pnmptnPsrta.poskod"/>
        <s:hidden name="pnmptnPsrta.negeri.kod"/>
        <s:hidden name="pnmptnPsrta.markahTemuduga"/>
        <div align="center">
            <s:errors/>
            <s:messages/>
        </div>
        <fieldset class="aras1">
            <legend>Kemasukan Maklumat Penempatan Peserta</legend>

            <p>
                <label for="nama"><font color="red">*</font>Nama :</label>
                <s:text name="pnmptnPsrta.nama" id="nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                <s:text name="pnmptnPsrta.noPengenalan" id="kp" size="40"  onblur="calAgeByNopeng(this.value);" onkeyup="dodacheck(this.value);" /> <em>[760908049835]</em>
            </p>

            <p>
                <label><font color="red">*</font>Umur :</label>
                <s:text name="pnmptnPsrta.umur" size="10" maxlength="3"  id="umur" onkeyup="validateNumber(this,this.value);"/>
            </p>

            <div id="alamat">
            <p>
                <label for="alamat"><font color="red">*</font>Alamat Surat-Menyurat :</label>
                <s:text name="pnmptnPsrta.alamat1" id="Alamat1" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pnmptnPsrta.alamat2" id="Alamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pnmptnPsrta.alamat3" id="Alamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pnmptnPsrta.alamat4" id="Alamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>


            <p>
                <label><font color="red">*</font>Poskod :</label>
                <s:text name="pnmptnPsrta.poskod" value="${actionBean.pnmptnPsrta.poskod}"id="suratPoskod" size="5" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
            </p>

            <p>
                <label for="Negeri"><font color="red">*</font>Negeri :</label>
                <s:select name="pnmptnPsrta.negeri.kod" id="majikanNegeri" style="width:244px">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select>
            </p>
            
            <p>
                <label><font color="red">*</font>Markah Temuduga :</label>
                <s:text name="pnmptnPsrta.markahTemuduga" value="${actionBean.pnmptnPsrta.markahTemuduga}"id="markahTemuduga" size="5" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
            </p>
            </div>
            <p>
                <label>&nbsp;</label>
                <s:submit name="simpanPnmptnPsrta" id="simpan" value="Simpan" class="btn"/>
                <s:button name=""  size="20"id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
            <br>
        </fieldset>
    </s:form>

