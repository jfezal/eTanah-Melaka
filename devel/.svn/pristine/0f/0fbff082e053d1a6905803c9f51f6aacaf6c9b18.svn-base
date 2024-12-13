<%--
    Document   : kemasukan_aduan
    Created on : Apr 7, 2010, 11:36:04 AM
    Author     : aminah.abdmutalib
    Modified By: Murali 18-09-2011
--%>
<%@ page contentType="text/html" import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="etanah.model.Pengguna"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%--hakmilik popup Start--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<%--end--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript">

    $(document).ready(function() {
        dialogInit('Carian Hakmilik');

        $("#idHakmilik").change(function(){validateHakmilik();});
        $("#idHakmilik").keyup(function(){
            closeDialog();
        });

        $('input:text').each(function(){
            $(this).focus(function() { $(this).addClass('focus')});
            $(this).blur(function() { $(this).removeClass('focus')});
        });          
    });
    function validateHakmilik(){
        var val = $("#idHakmilik").val();
        frm = this.form;
        if (val == null || val == "") return;    
        val = val.toUpperCase();
        $("#idHakmilik" ).val(val);


        $.get("${pageContext.request.contextPath}/daftar/check_idhakmilik?doCheckHakmilik&idHakmilik=" + val,
        function(data){
            if(data == '1'){
                $("#msg").html("Cukai Hakmilik telah dijelaskan. (" +
                    "<a href=\"javascript:void(0);\" onclick=\"popupHakmilikDetails();\">Papar</a>)");
            } else if(data =='0'){
                // disable if invalid Hakmilik 
                // $("#collectPayment").attr("disabled", "true");
                $("#idHakmilik").val("");
                alert("ID Hakmilik " + val + " tidak wujud!");
                // $("#msg" + idxHakmilik).html("<font color=red>ID Hakmilik " + val + " tidak wujud!</font>");
                /**
                 *} else if(data =='2'){
                // disable if invalid Hakmilik 
                // $("#collectPayment").attr("disabled", "true");
                //$("#hakmilik" + idxHakmilik).val("");
                $("#msg").html("Cukai untuk Hakmilik " + val + " masih belum dijelaskan!");         
                alert("Cukai untuk Hakmilik " + val + " masih belum dijelaskan!");                
            } else if (data == '3'){
                $("#idHakmilik").val("");
                alert("Status hakmilik Provisional. Tiada Urusniaga / Kaveat Persendirian / Lien dibenarkan.");
            } else if (data.charAt(0) == '4'){
   
                $("#idHakmilik").val("");
                var str = "Hakmilik " + val + " telah dibatalkan.";
                if (data.substring(2).length > 0) str += " Hakmilik sambungan ialah " + data.substring(2) + ".";
                alert(str);
                $("#msg").html(str);
                 **/
            } else{
                //                alert("Unknown reply: " + data);
            }
        });

        // check for kaveat
        $.get("${pageContext.request.contextPath}/daftar/check_kaveat?doCheckKaveat&idHakmilik=" + val,
        function(data){
            if(data == '0'){
                // nothing to do
            } else if(data =='1'){
                alert("Hakmilik " + val + " mempunyai Kaveat!");
            }
        });

        //alert(msg);
        
    }
    function popupHakmilikDetails(){
        //alert(id);
        var idH = $('#idHakmilik').val();
        var url ="${pageContext.request.contextPath}/hakmilik?popupHakmilikDetail&idHakmilik=" + idH;
        window.open(url,"eTanah","status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
    }

    function refreshPage(){
        var q = $('#form1').serialize();
        var url = document.form1.action + '?refreshPage&';// + event;
        window.location = url+q;

    }
    function validateForm(){

        if(document.form1.cara.value==""){
            alert("Sila Pilih Cara Aduan");
            return false;
        }
        if(document.form1.kodUrusan.value==""){
            alert("Sila Pilih Peruntukaan Seksyen Di bawah Akta Hakmilik Strata 1985");
            return false;
        }
        if(document.form1.idHakmilik.value==""){
            alert("Sila Pilih idHakmilik");
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
    //date validation :START

    <%--var fromDate = $("#from").val();
        var toDate = $("#to").val();       
       if(Date.parse(startdate).getTime() > Date.parse(enddate).getTime())
            {
              // do somthing
            return false;
            }

            var startDate = new Date($('#startDate').val());
        var endDate = new Date($('#endDate').val());

        if (startDate < endDate){
        // Do something
}
        } --%>

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

            //END

</script>

<s:form name="form1" id="form1" beanclass="etanah.view.strata.PenguatkuasaanStrataActionBean">
    <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
    <s:messages/>
    <div class="instr" align="center">
        <s:errors/>
    </div>
    <center><b><font size="3">STRATA: Kemasukan Aduan</font></b></center>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Aduan</legend>
            <div class="instr-fieldset">
                <font color="red">PERINGATAN:</font>Sila Masukkan Maklumat Berikut.
            </div>&nbsp;
            <p>
                <label>Tarikh :</label>
                <fmt:formatDate type="time" pattern="dd/MM/yyyy" value="<%=new java.util.Date()%>"/>
                &nbsp;</p>
            <p>
                <label>Daerah :</label>
                ${actionBean.pguna.kodCawangan.daerah.kod} - ${actionBean.pguna.kodCawangan.daerah.nama} &nbsp;
            </p>
            <p>
                <label>Cara Aduan :</label>
                <s:select name="caraPermohonan.kod"  style="width:139px;" id="cara">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${actionBean.senaraiKodCaraPermohonan}" label="nama" value="kod" sort="nama" />
                </s:select><em>*</em>&nbsp;
            </p>
            <p>
                <label>Ringkasan Aduan :</label>
                <s:textarea name="sebab" value="${actionBean.sebab}" rows="8" cols="60" />&nbsp;
            </p>
            <p>
                <label> Peruntukan Seksyen Di bawah Akta Hakmilik Strata 1985 :</label>
                <s:select name="kodUrusan.kod"  id="kodUrusan" value=""  style="width:400px;">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${actionBean.senaraiUrusan2}" label="nama" value="kod"/>
                </s:select><em>*</em>&nbsp;
            </p>
        </fieldset >
        <br>
        <fieldset class="aras1">
            <legend>Maklumat Pengadu</legend>

            <p>
                <label>Nama :</label>
                <s:text name="penyerahNama" size="30" /> &nbsp;
            </p>
            <p>
                <label>Jenis Pengenalan :</label>
                <s:select name="penyerahJenisPengenalan.kod"  style="width:205px;">
                    <s:option value="0">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" sort="nama" />
                </s:select> &nbsp;
            </p>
            <p>
                <label>No. Kad Pengenalan :</label>
                <s:text name="penyerahNoPengenalan" size="30" maxlength="12" /> &nbsp;
            </p>
            <p>
                <label>Alamat :</label>
                <s:text name="penyerahAlamat1" size="40"/> &nbsp;
            </p>
            <p>
                <label>&nbsp; </label>
                <s:text name="penyerahAlamat2" size="40"/> &nbsp;
            </p>
            <p>
                <label>&nbsp; </label>
                <s:text name="penyerahAlamat3" size="40"/> &nbsp;
            </p>
            <p>
                <label>&nbsp; </label>
                <s:text name="penyerahAlamat4" size="40"/> &nbsp;
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name="penyerahPoskod" maxlength="5" size="17" onkeyup="validateNumber(this,this.value);" /> &nbsp;
            </p>
            <p>
                <label>Negeri :</label>
                <s:select name="penyerahNegeri.kod"  style="width:139px;">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" sort="nama" />
                </s:select> &nbsp;
            </p>
            <p>
                <label>No. Telefon :</label>
                <s:text name="penyerahNoTelefon1" maxlength="10" size="17" onkeyup="validateNumber(this,this.value);" />
                <font color="red" size="1">(Contoh : 0376667660) </font> &nbsp;
            </p>

        </fieldset>
        <br>
        <fieldset class="aras1">
            <legend>Maklumat Hakmilik</legend>

            <p>
                <label>ID Hakmilik :</label>
                <s:text name="aduanStratahakmilik" id="idHakmilik" size="30" onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/><em>*</em>&nbsp;
            <div id="msg"/>
            </p>
            <p>
                <label>No. CF :</label>
                <s:text name="cfNoSijil" size="30"/> &nbsp;
            </p>
            <p>
                <label>Tarikh CF dikeluarkan :</label>
                <s:text name="cfTarikhKeluar" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy" size="30" onchange="validateDate(this,this.value);"/> &nbsp;
            </p>
            <p>
                <label>Tarikh Perjanjian Jual Beli :</label>
                <s:text name="tarikhTandatanganPerjanjianJualBeli" id="datepicker1" class="datepicker" formatPattern="dd/MM/yyyy" size="30" onchange="validateDate(this,this.value);" /> &nbsp;
            </p>
        </fieldset>
        <p align="right">
            <s:submit class="btn" name="sessionSave" value="Simpan" onclick="return validateForm();"/>
        </p>
    </div>
</s:form>