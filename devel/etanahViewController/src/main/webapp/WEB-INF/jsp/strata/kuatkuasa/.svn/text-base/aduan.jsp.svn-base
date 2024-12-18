<%-- 
    Document   : Aduan
    Created on : Nov 3, 2010, 10:54:00 AM
    Author     : Srinu
    edited : July 7, 2011 by zadhirul.farihim
--%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">

    function validation(){

        if($("#tarikhSiasat").val() == ""){
            alert('Sila masukan " Tarikh Siasatan " terlebih dahulu.');
            $("#tarikhSiasat").focus();
            return false;
        }
     
        return true;


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
                       
        //compare tarikh masuk > tarikh hari ini.
        if ((tarikhmasuk > vtoday ))
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
    function test(f) {
        $(f).clearForm();
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form name="form1" id="form1"  beanclass="etanah.view.strata.LaporanSiasatKuatkuasaStrataActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">

            <legend>Kemasukan Maklumat Aduan</legend>
            <p><font color="green">Maklumat yang bertanda (</font><em>*</em><font color="green">) adalah wajib diisi. </font></p>
            <br/>
            <div class="content" align="center">
                <table width="70%" border="0">
                    <tr><td id="idLabel" align="right" width="35%"><b>Tarikh Siasatan </b></td>
                        <td align="center">:</td>
                        <td><s:text name="tarikhSiasat" size="30" id="tarikhSiasat" class="datepicker" onchange="validateDate(this,this.value);"/><em>*</em></td>

                    </tr>
                    <tr><td id="idLabel" align="right"><b>No. Pendaftaran Kenderaan Jabatan / Persendirian </b> </td>
                        <td align="center">:</td>
                        <td><s:text name="kenderaanSiasat" size="30" id="kenderaanSiasat" class="normal_text"/></td>

                    </tr>
                </table>
            </div>
        </fieldset>

        <fieldset class="aras1">
            <legend>Butiran Aduan</legend>
            <div class="content" align="center">
                <table width="70%" border="0">
                    <c:if test="${actionBean.noCF eq null}">
                        <tr><td id="idLabel" align="right" width="35%"><b>No.CF</b></td>
                            <td align="center">:</td>
                            <td><s:text name="noCF" size="30" class="normal_text"/></td>

                        </tr>
                    </c:if>
                    <c:if test="${actionBean.noCF ne null}">
                        <tr><td id="idLabel" align="right" width="35%"><b>No.CF</b></td>
                            <td align="center">:</td>
                            <td>${actionBean.noCF}<!--<s:text name="noCF" size="30" readonly="true"class="normal_text"/>-->
                            </td>

                        </tr>
                    </c:if>
                    <c:if test="${actionBean.tarikhCF eq null}">
                        <tr><td id="idLabel" align="right"><b>Tarikh CF Dikeluarkan</b></td>
                            <td align="center">:</td>
                            <td> <s:text name="tarikhCF" id="datepicker" size="30" class="datepicker" onchange="validateDate(this,this.value);"/></td>

                        </tr>
                    </c:if>
                    <c:if test="${actionBean.tarikhCF ne null}">
                        <tr><td id="idLabel" align="right"><b>Tarikh CF Dikeluarkan</b></td>
                            <td align="center">:</td>
                            <td> ${actionBean.tarikhCF}<!--<s:text name="tarikhCF" id="datepicker" readonly="true"size="30" onchange="validateDate(this,this.value);"/>-->
                            </td>

                        </tr>
                    </c:if>
                    <tr><td id="idLabel" align="right" width="35%"><b>No. Rujukan Pelan Bangunan</b></td>
                        <td align="center">:</td>
                        <td><s:text name="noRujukanPelanBngn" size="30" class="normal_text"/></td>


                    </tr>

                    <tr><td id="idLabel" align="right"><b>Kawasan PBT</b></td>
                        <td align="center">:</td>
<!--                        <td><s:text name="kawPBT"/></td>-->
                        <td><s:select name="kawPBT" id="kodPengenalan">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodPBT}" label="nama" value="kod"/>
                            </s:select>  
                        </td>


                    </tr>

                    <tr><td id="idLabel" align="right"><b>Lokasi</td>
                        <td align="center">:</td>
                        <td><s:text name="lokasi" size="30" class="normal_text"/></td>

                    </tr>

                    <tr><td valign="top" id="idLabel" align="right"><b>Alamat lokasi yang disiasat</b></td>
                        <td valign="top" align="center">:</td>
                        <td> <s:text name="alamat1" size="30" class="normal_text"/> &nbsp;
                        </td>
                    </tr>
                    <tr><td valign="top" id="idLabel"></td>
                        <td valign="top"></td>
                        <td> <s:text name="alamat2" size="30" class="normal_text"/> &nbsp;
                        </td>
                    </tr>
                    <tr><td valign="top" id="idLabel"></td>
                        <td valign="top"></td>
                        <td> <s:text name="alamat3" size="30" class="normal_text"/> &nbsp;
                        </td>
                    </tr>
                    <tr><td valign="top" id="idLabel"></td>
                        <td valign="top"></td>
                        <td> <s:text name="alamat4" size="30" class="normal_text"/> &nbsp;
                        </td>
                    </tr>
                    <tr><td valign="top" id="idLabel" align="right"><b>Poskod</b></td>
                        <td valign="top" align="center">:</td>
                        <td> <s:text name="poskod" size="30" maxlength="5" onkeyup="validateNumber(this,this.value);"/> &nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td valign="top" id="idLabel" align="right"><b>Negeri</b></td>
                        <td align="center">:</td>
                        <td> <s:select name="negeri" class="normal_text;" style="width:220px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" sort="nama" />
                            </s:select> &nbsp;
                        </td>
                    </tr>
                    <tr><td colspan="3">&nbsp;</td></tr>
                    <tr><td colspan="3">&nbsp;</td></tr>
                    <tr><td colspan="3"><center><s:button name="simpanAduan" id="save" value="Simpan" class="btn" onclick="if(validation()){doSubmit(this.form,this.name,'page_div')};"/>
                        <s:button  name="isiSemula" value="Isi Semula" class="btn" onclick="doSubmit(this.form,this.name,'page_div');"/></center></td></tr>

                </table>
            </div>
        </fieldset>
    </div>

</s:form>
