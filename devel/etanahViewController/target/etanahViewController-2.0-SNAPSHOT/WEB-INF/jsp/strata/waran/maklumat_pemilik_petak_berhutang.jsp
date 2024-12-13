<%--
    Document   : maklumat_pemilik_petak_berhutang
    Created on : Apr 7, 2011, 12:24:04 AM
    Author     : zadhirul.farihim
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
    $(document).ready(function(){
        $("#notisPertama0").datepicker({dateFormat: 'dd/mm/yy'});
        $("#notisKedua0").datepicker({dateFormat: 'dd/mm/yy'});
    });
    var dtCh= "/";
    var minYear=1900;
    var maxYear=2100;
    function save(event, f){

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div').html(data);

        },'html');

    }
    //
    //    function papar(idMh){
    //
    //        var left = (screen.width/2)-(1000/2);
    //        var top = (screen.height/2)-(150/2);
    //        var url = '${pageContext.request.contextPath}/strata/waran?displayItemWaran&idMh='+idMh;
    //        window.open(url,'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500, left=" + left + ",top=" + top);
    //
    //    }
    function CurrencyFormatted(elmnt,amount)
    {
        var i = parseFloat(amount);
        if(isNaN(i)) { i = 0.00; }
        var minus = '';
        if(i < 0) { minus = '-'; }
        i = Math.abs(i);
        i = parseInt((i + .005) * 100);
        i = i / 100;
        s = new String(i);
        if(s.indexOf('.') < 0) { s += '.00'; }
        if(s.indexOf('.') == (s.length - 2)) { s += '0'; }
        s = minus + s;
        //            $('#amaun').val(s);
        elmnt.value = s;
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
    function paparSenaraiHutang(v){

        var url = '${pageContext.request.contextPath}/strata/waran?paparSenaraiHutang&idMh='+v;
        $.ajax({
            type:"GET",
            url : url,
            success : function(data) {
                $('#page_div').html(data);
            }
        });

    }
    function tutup(){

        var url = '${pageContext.request.contextPath}/strata/waran?pemilikPetakBerhutang';
        $.ajax({
            type:"GET",
            url : url,
            success : function(data) {
                $('#page_div').html(data);
            }
        });

    }
    function popupTambahHutang(){
        var idMh = document.getElementById("idMh").value;
        //        alert(idMh);
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/strata/waran?popupItemWaran&idMh='+idMh;
        window.open(url,'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500, left=" + left + ",top=" + top);

    }
    function hapus(idMohonWaranItem){
        var idMh = $("#idMh").val();
        if(confirm("Adakah pasti hendak menghapuskan maklumat ini?")){
            var url = '${pageContext.request.contextPath}/strata/waran?hapusWaranItem&idMohonWaranItem='+idMohonWaranItem+'&idMh='+idMh;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }
    function kemaskini(idMohonWaranItem){
        var idMh = $("#idMh").val();
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/strata/waran?popupKemaskiniWaran&idMohonWaranItem='+idMohonWaranItem+'&idMh='+idMh;
        var msgWindow =  window.open(url,'etanah', "status=0,alwaysRaised=yes,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=1 left=" + left + ",top=" + top);
    }
    function dpicker(row){
        //    alert("t");
        $("#notisPertama0").datepicker({dateFormat: 'dd/mm/yy'});
        //       alert("t1");
    }
    function dpicker2(row){
        $("#notisKedua0").datepicker({dateFormat: 'dd/mm/yy'});
        //        var i;
        //        for(i=0;i<=row;i++){
        //            $("#notisKedua${i}").datepicker();
        //        }

        //       alert("t1");
    }

    function isNumberKey(evt)
    {
        var charCode = (evt.which) ? evt.which : event.keyCode
        if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

        return true;
    }



</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<%--<s:form name="form1" beanclass="etanah.view.strata.WaranPenahananActionBean"> By murali--%>
<s:form beanclass="etanah.view.strata.WaranPenahananActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Senarai Pemilik</legend>

            <div class="content" align="left" id="listpihak">
                <display:table class="tablecloth" style="width:80%;" name="${actionBean.listListHakmilikPihak}" cellpadding="0" cellspacing="0" id="linemohonpihak">
                    <display:column style="width:2%;" title="Bil"><div align="center">${linemohonpihak_rowNum}</div></display:column>
                    <display:column style="width:20%;"  title="Hakmilik" property="hakmilik.idHakmilik"><div align="center"></div></display:column>
                        <display:column style="width:10%;"  title="No Unit Petak" ><div align="center">
                                <s:text name="listListHakmilikPihak[${linemohonpihak_rowNum-1}].hakmilik.noPetak" readonly="true" size="5"></s:text></div>
                            </display:column>
                       <%-- <c:if test="${!actionBean.readOnly}">
                            <display:column style="width:10%;"  title="No Unit Petak" ><div align="center">
                                <s:text name="listListHakmilikPihak[${linemohonpihak_rowNum-1}].hakmilik.noUnitPetak" size="5"></s:text></div>
                            </display:column>
                        </c:if>--%>
                        <display:column style="width:25%;" title="Nama"><div align="center">
                            <c:forEach items="${linemohonpihak.listPihak}" var="line" >
                                <p align="left"> ${line.nama}</p></c:forEach></div>
                        </display:column>
                        <c:if test="${!actionBean.readOnly}">
                            <display:column style="width:7%;" title="Butir-Butir Wang Terhutang" >
                                <%--<p><font color="red"><b>RM 12113131.00</b></font></p>--%>
    <!--                            <p align="center"><img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" alt="Terperinci" title="Kemaskini" width="40" height="40" onmouseover="this.style.cursor='pointer';" onclick="kemaskini('${linemohonpihak.mohonHakmilik.id}');return false;" /></p><p align="center"><b>Kemaskini</b></p>-->
                            <p align="center"><img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"  width="40" height="40" onclick="paparSenaraiHutang('${linemohonpihak.mohonHakmilik.id}');return false;" onmouseover="this.style.cursor='pointer';"/></p>
                            </display:column>
                        </c:if>
                        <c:if test="${actionBean.readOnly}">
                            <display:column title="Butir-Butir Wang Terhutang" >
                                <%--<p><font color="red"><b>RM 12113131.00</b></font></p>--%>
<!--                            <p align="center"><img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" alt="Terperinci" width="40" height="40" onclick="papar('${linemohonpihak.mohonHakmilik.id}');return false;" /></p><p align="center"><b>Papar</b></p>-->
                            <p align="center"><img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" title='Klik untuk paparan terperinci' width="40" height="40" onclick="paparSenaraiHutang('${linemohonpihak.mohonHakmilik.id}');return false;" onmouseover="this.style.cursor='pointer';"/></p>
                            </display:column>
                        </c:if>
                        <c:if test="${actionBean.readOnly}">
                            <display:column style="width:15%;" title="Notis Pertama"><div align="center">
                                <p align="left">Amaun (RM) : <s:text readonly="true" name="listListHakmilikPihak[${linemohonpihak_rowNum-1}].listNotisWaran.amaun" formatPattern="#,##0.00"></s:text> </p>
                                <p align="left">Tarikh : <s:text readonly="true" formatPattern="dd/MM/yyyy" id="datepicker"  name="listListHakmilikPihak[${linemohonpihak_rowNum-1}].listNotisWaran.tarikhNotis" onchange="validateDate(this,this.value);"></s:text> </p>
                                <p align="left"><s:hidden  name="listListHakmilikPihak[${linemohonpihak_rowNum-1}].mohonHakmilik.id"></s:hidden> </p></div>

                        </display:column>

                        <display:column style="width:15%;" title="Notis Kedua">
                            <p align="lef">Amaun (RM): <s:text readonly="true" name="listListHakmilikPihak[${linemohonpihak_rowNum-1}].listNotisWaran2.amaun" formatPattern="#,##0.00"></s:text> </p>
                            <p align="left">Tarikh : <s:text readonly="true" formatPattern="dd/MM/yyyy"  id="datepicker2" onchange="validateDate(this,this.value);" name="listListHakmilikPihak[${linemohonpihak_rowNum-1}].listNotisWaran2.tarikhNotis" ></s:text> </p>

                        </display:column>
                    </c:if>
                    <c:if test="${!actionBean.readOnly}">
                        <display:column style="width:15%;" title="Notis Pertama"><div align="center">
                                <p align="left">Amaun (RM) : <s:text onkeyup="validateNumber(this,this.value);" onkeypress="return isNumberKey(event)" onchange="CurrencyFormatted(this,this.value);" name="listListHakmilikPihak[${linemohonpihak_rowNum-1}].listNotisWaran.amaun" formatPattern="#,##0.00"></s:text> </p>
                                <p align="left">Tarikh : <s:text formatPattern="dd/MM/yyyy" class="datepicker"  id="notisPertama${linemohonpihak_rowNum-1}"  name="listListHakmilikPihak[${linemohonpihak_rowNum-1}].listNotisWaran.tarikhNotis" onchange="validateDate(this,this.value);" onmouseover="dpicker(${linemohonpihak_rowNum-1})" onclick="dpicker(${linemohonpihak_rowNum-1});"></s:text> </p>
                                <p align="left"><s:hidden  name="listListHakmilikPihak[${linemohonpihak_rowNum-1}].mohonHakmilik.id"></s:hidden> </p></div>

                        </display:column>

                        <display:column style="width:15%;" title="Notis Kedua">
                            <p align="lef">Amaun (RM): <s:text onkeyup="validateNumber(this,this.value);" onkeypress="return isNumberKey(event)" onchange="CurrencyFormatted(this,this.value);" name="listListHakmilikPihak[${linemohonpihak_rowNum-1}].listNotisWaran2.amaun" formatPattern="#,##0.00"></s:text> </p>
                            <p align="left">Tarikh : <s:text formatPattern="dd/MM/yyyy"  id="notisKedua${linemohonpihak_rowNum-1}" onchange="validateDate(this,this.value);"  class="datepicker" name="listListHakmilikPihak[${linemohonpihak_rowNum-1}].listNotisWaran2.tarikhNotis" onmouseover="dpicker2(${linemohonpihak_rowNum-1})" onclick="dpicker2(${linemohonpihak_rowNum-1});"></s:text> </p>

                        </display:column>
                    </c:if>

                </display:table>

            </div>
            <c:if test="${!actionBean.readOnly}">
                <label>&nbsp;</label><s:button name="saveList" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')" />
            </c:if>
            <p></p>
        </fieldset>
    </div>
    <c:if test="${paparHutang}">
        <div id="senaraiHutang">
            <fieldset class="aras1">
                <legend>Butir-Butir Jumlah Wang yang Terhutang</legend>
                <p>&nbsp;</p>
                <s:hidden name="idMh" id="idMh" />
                <c:if test="${!actionBean.readOnly}">
                    <p> <s:button class="btn" name="tambahItemWaran" value="Tambah" onclick="popupTambahHutang();"/>
                        <s:button class="btn" name="returnPage" value="Tutup" onclick="doSubmit(this.form,this.name,'page_div')"/>
                    </p>
                </c:if>
                <c:if test="${actionBean.readOnly}">
                    <p>
                        <s:button class="btn" name="close" value="Tutup" onclick="doSubmit(this.form,this.name,'page_div')"/>
                    </p>
                </c:if>
                <br/>
                <p align="left">
                    <c:set value="0" var="object"></c:set>
                    <display:table class="tablecloth" name="${actionBean.listWaranItem}" cellpadding="0" cellspacing="0" id="line" ><c:set  value="${object+1}" var="object" ></c:set>
                        <display:column title="Bil" sortable="true" style="vertical-align:baseline">${object}<s:hidden id="hidden3" name="hidden3" value="${line_rowNum-1}"/></display:column>
                        <display:column title="Perihal Hutang" property="keterangan" />
                        <display:column title="Jumlah (RM)" property="amaun" format="{0,number,0.00}" />
                        <c:if test="${!actionBean.readOnly}">
                            <c:if test="${!actionBean.display}">
                                <display:column title="Hapus" ><p align="center"><img src="${pageContext.request.contextPath}/pub/images/not_ok.gif" alt="Hapus Data" width="15" height="15" onmouseover="this.style.cursor='pointer';" onclick="hapus('${line.idMohonWaranItem}');return false;" /></p>

                            </display:column></c:if>

                        <c:if test="${!actionBean.display}">
                            <display:column title="Kemaskini" ><p align="center"><img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" alt="Kemaskini" width="20" height="20" onmouseover="this.style.cursor='pointer';" onclick="kemaskini('${line.idMohonWaranItem}');return false;" /></p>

                            </display:column></c:if>
                    </c:if>
                    <display:footer>
                        <td>&nbsp;</td>
                        <td>Jumlah Keseluruhan (RM):</td>
                        <td>
                            <div align="right"><s:text name="jumlah" id="total" readonly="true" formatPattern="#,##0.00"/></div>
                        </td>
                        <c:if test="${!actionBean.readOnly}">
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </c:if>
                    </display:footer>
                </display:table>
                </p>
            </fieldset>

        </div>
    </c:if>
</s:form>
