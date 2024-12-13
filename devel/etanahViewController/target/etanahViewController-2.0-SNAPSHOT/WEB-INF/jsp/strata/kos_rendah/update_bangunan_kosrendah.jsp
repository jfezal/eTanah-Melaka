<%--
    Document   : tambahBangunanPBS
    Created on : Dec 22, 2010, 09:35:51 AM
    Author     : zadhirul.farihim
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
        document.form1.bilPetak.value="";
        document.form1.bilTgkt.value="";
        document.form1.bilBilik.value="";
    
    });

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

        var today = new Date();
        /*
        var curr_date = today.getDate();
        var curr_month = today.getMonth();
        curr_month = curr_month + 1;
        var curr_year = today.getFullYear();
        var strdatetoday = curr_date + '/'+ curr_month + '/'+ curr_year;--%>
        alert(strdatetoday);
         */

        //format tarikh masuk from dd/mm/yyy to mm/dd/yyyy
        var strtarikhmasuk = strMonth+ '/'+ strDay + '/'+ strYear;
        var tarikhmasuk = new Date(strtarikhmasuk);


        if (tarikhmasuk<today )
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


    function kemaskini(event, f){
        var blok = document.getElementById("updatenamaLain").value;
        var bil_petak = document.getElementById("updatebilPetak").value;
        var bil_tingkat = document.getElementById("updatebilTgkt").value;
        
        if ((blok == ""))
        {
            alert('Sila masukkan blok');
            document.form1.updatenamaLain.focus();
            return false;
        }
        if ((bil_tingkat == ""))
        {
            alert('Sila masukkan bilangan tingkat ');
            document.form1.updatebilTgkt.focus();
            return false;
        }
        if ((bil_petak == ""))
        {
            alert('Sila masukkan bilangan petak ');
            document.form1.updatebilPetak.focus();
            return false;
        }else{
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
    <%--self.opener.refreshPageHakmilik();--%>
                    self.close();
                },'html');
                return true;
            }
        }
        //
        //
        //                                $(document).ready(function() {
        //                                    $("#close").click( function(){
        //                                        setTimeout(function(){
        //                                            self.close();
        //                                        }, 100);
        //                                    });
        //                                });


</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div class="subtitle" >
    <s:form  name="form1" beanclass="etanah.view.strata.BangunanKosRendahActionBean">
        <s:messages/>
        <s:errors/>
        <s:hidden name="idBangunan"/>

        <fieldset class="aras1">
            <legend>Kemaskini Bangunan</legend>
            <p>
                Yang bertanda(<em>*</em>) adalah wajib diisi.
            </p>

            <p>
                <label><em>*</em>Blok :</label>
                <s:text readonly="true" name="updatenamaLain" size="30" id="updatenamaLain" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label><em>*</em>Bilangan Tingkat :</label>
                <s:text name="updatebilTgkt"  size="30" id="updatebilTgkt" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label><em>*</em>Bilangan Petak :</label>
                <s:text name="updatebilPetak" size="30" id="updatebilPetak" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label>Bilangan Bilik :</label>
                <s:text name="updatebilBilik" size="30" id="updatebilBilik" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label><em>*</em>Jenis Kengunaan Bangunan :</label> 
                <s:select name="gunaBngn" value="${actionBean.gunaBngn}">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodKegunaanBangunan}" label="nama" value="kod"/>
                </s:select>
            </p>


            <br>
            <label>&nbsp;</label>
            <s:button name="updateBngn" id="simpan" value="Kemaskini" class="btn" onclick="kemaskini(this.name,this.form)"/>
            <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
            <%--<s:button name="tambahBangunan" value="Simpan" class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');"/>--%>
            <br>

            <br>
        </fieldset>


    </s:form>
</div>