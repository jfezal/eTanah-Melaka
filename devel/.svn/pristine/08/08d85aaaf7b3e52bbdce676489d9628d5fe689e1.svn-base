<%-- 
    Document   : keputusan_summk
    Created on : Apr 12, 2011, 12:16:45 PM
    Author     : zadhirul.farihim
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">
    function validateFrom(){
        var tarikhSidang = document.getElementById("tarikhSidang").value;
        var bilKertas = document.getElementById("bilKertas").value;
        var v1 = $('#keputusanSUMMK1').is(':checked');
        var v2 = $('#keputusanSUMMK2').is(':checked');
        var f = true;
        
        if(tarikhSidang ==""){
            alert("Sila Masukkan Tarikh Sidang.");
            document.getElementById("tarikhSidang").focus();
            return false;
        }
        if(bilKertas ==""){
            alert("Sila Masukkan Bil. Kertas.");
            document.getElementById("bilKertas").focus();
            return false;
        }
        if(!v1 && !v2) {
            f = false;
        }
        if(!f){
            alert('Sila Masukkan Keputusan.');
            return false;
        }
        return true;
    }
    
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
</script>

<s:form beanclass="etanah.view.strata.KeputusanMMKActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden id="idMohon" name="permohonan.idPermohonan"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Keputusan
            </legend>
            <div class="content" align="left">
                <table>
                    <tr>
                        <td class="rowlabel1"><label><font color="red">*</font>Tarikh Sidang :</label></td>
                        <td class="input1"> <s:text id="tarikhSidang" name="tarikhSidang" class="datepicker" onchange="validateDate(this,this.value);"/></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1"><label><font color="red">*</font>Bil. Kertas :</label></td>
                        <td class="input1"> <s:text id="bilKertas" name="bilKertas" class="normal_text"/></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1"><label><font color="red">*</font>Keputusan :</label></td>
                        <td class="input1">
                            <s:radio name="keputusanSUMMK" id="keputusanSUMMK1" value="L"/> Lulus
                            <s:radio name="keputusanSUMMK" id="keputusanSUMMK2" value="T"/> Tolak
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1" valign="top"><label>Ulasan :</label></td>
                        <td class="input1"><s:textarea name="ulasanSUMMK" rows="5" cols="50" class="normal_text"/></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">&nbsp;</td>
                        <td><s:button name="simpanKeputusanSUMMK" id="" value="Simpan" class="btn" onclick="if(validateFrom()==true){doSubmit(this.form, this.name, 'page_div')};"/></td>
                    </tr>
                </table>

            </div>
        </fieldset>
    </div>
    <br/>
    <!--    <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Syor Dan Ulasan
                </legend>
                <div class="content" align="center">
    <display:table class="tablecloth" name="${actionBean.fasaPermohonan}" cellpadding="0" cellspacing="0" id="line">
        <display:column title="No" sortable="true" style="vertical-align:top;">${line_rowNum}</display:column >
        <display:column property="idPengguna" title="Nama" style="vertical-align:top;"/>
        <display:column property="keputusan.nama" title="Keputusan" style="vertical-align:top;"/>
        <display:column title="Ulasan" style="width:80px;vertical-align:top;">
            <textarea name="" style="background: transparent; border: 0px" cols="80" rows="10" readonly="readonly" id="text">${line.ulasan}</textarea>
        </display:column>
        <display:column title="Tarikh Keputusan" style="width:90px;vertical-align:top;">
            <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm:ss"/>
        </display:column>
    </display:table>
</div>

</fieldset>
<br/>
</div>-->
</s:form>
