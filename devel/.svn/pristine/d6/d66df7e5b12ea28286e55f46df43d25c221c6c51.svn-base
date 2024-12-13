<%-- 
    Document   : kemasukan_maklumat_hakmilik
    Created on : Nov 3, 2010, 11:48:51 AM
    Author     : Srinu
    edited : July 7, 2011 by zadhirul.farihim
--%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    //    $(document).ready( function(){
    //        document.getElementById("tanya1").checked =true;
    //    });
    $(document).ready( function(){
        $("#datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });
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
    
    function p(v){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
        $.get("${pageContext.request.contextPath}/strata/penguatkuasaan_strata?maklumatPemilikDetail&idPihak="+v,
        function(data){
            $("#perincianPihak").show();
            $("#perincianPihak").html(data);
            $.unblockUI();
        });
    }
    function senaraiHakmilikSamb(v){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
        $.get("${pageContext.request.contextPath}/strata/penguatkuasaan_strata?maklumatHakmilikMuktamad&idHakmilikMuktamad="+v,
        function(data){
            $("#hakmilikMuktamad").show();
            $("#hakmilikMuktamad").html(data);
            $.unblockUI();
        });
            
    }
//    function test1() {
//        document.getElementById("namaPemaju").value = "";
//        document.getElementById("namaPembeli").value = "";
//    }
//    function test2() {
//        document.getElementById("namaPemaju").value = "";
//        document.getElementById("namaPembeli").value = "";
//        document.getElementById("datepicker").value = "";
//    }
    
    function resetForm(){
        var url = "${pageContext.request.contextPath}/strata/penguatkuasaan_strata?resetForm";
          $.post(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        return true;
    }
</script>


<s:form name="form1" id="form1"  beanclass="etanah.view.strata.LaporanSiasatKuatkuasaStrataActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend> Kemasukan Maklumat Hakmilik</legend>
            <div class="content" align="left">
                <table  border="0">
                    <tr><td colspan="3"><b>1. Perihal Perjanjian Jual Beli</b></td></tr>
                    <tr><td colspan="3">&nbsp;</td></tr>
                    <tr><td id="idLabel" valign="top">Nama Pemilik  </td>
                        <td valign="top">:</td>
                        <td>
                            <!--<s:text name="namaPenjual" size="30" class="normal_text" value=""/>-->
                            <c:forEach items="${actionBean.listHakmilikPihak}" var="list" varStatus="line">
                                <!--<s:text name="namaPemilik" readonly="true" size="30" class="normal_text" value="${list.pihak.nama}"/>-->
                                ${list.pihak.nama}<br/>
                            </c:forEach>
                        </td>
                    </tr>
                    <tr><td id="idLabel">Nama Pemaju</td>
                        <td>:</td>
                        <td><s:text id="namaPemaju" name="namaPemaju" size="30" class="normal_text"/></td>
                    </tr>
                    <tr><td id="idLabel">Nama Pembeli</td>
                        <td>:</td>
                        <td><s:text id="namaPembeli" name="namaPembeli" size="30" class="normal_text"/></td>
                    </tr>
                    <c:if test="${actionBean.tarikhTandatangan ne null}">
                        <tr><td id="idLabel">Tarikh Ditandatangani</td>
                            <td>:</td>
                            <td> <s:text name="tarikhTandatangan" id="datepicker" readonly="true" size="30"/></td>
                        </tr>
                    </c:if>
                    <c:if test="${actionBean.tarikhTandatangan eq null}">
                        <tr><td id="idLabel">Tarikh Ditandatangani</td>
                            <td>:</td>
                            <td> <s:text name="tarikhTandatangan" id="datepicker"  class="datepicker" onchange="validateDate(this,this.value);" size="30"/></td>
                        </tr>
                    </c:if>
                    <tr><td id="idLabel">No. Hakmilik Tanah</td>
                        <td> :</td>
                        <td>
                            <!--<s:text name="noHakmilik" readonly="true" value="${actionBean.hakmilik.kodHakmilik.kod}${actionBean.hakmilik.noHakmilik}"size="30" class="normal_text"/>-->
                            ${actionBean.hakmilik.kodHakmilik.kod} ${actionBean.noHakmilik}
                        </td>
                    </tr>

                    <tr><td colspan="3">&nbsp;</td></tr>
                    <tr><td align="left" colspan="3"><b>2. Butiran Hakmilik Tanah</b></td></tr>
                    <tr><td colspan="3">&nbsp;</td></tr>
                    <tr><td id="idLabel">Jenis dan No. Hakmilik  </td>
                        <td>:</td>
                        <td>${actionBean.hakmilik.kodHakmilik.nama}  ${actionBean.noHakmilik}</td>
                    </tr>

                    <tr><td id="idLabel">No. PT/Lot</td>
                        <td>:</td>
                        <td>${actionBean.hakmilik.lot.nama}  ${actionBean.noLotHakmilik}</td>
                    </tr>

                    <tr><td id="idLabel">Bandar/Pekan/Mukim</td>
                        <td>:</td>
                        <td>${actionBean.hakmilik.bandarPekanMukim.nama}</td>
                    </tr>
                    
                    <tr><td id="idLabel">Seksyen</td>
                        <td>:</td>
                        <td>${actionBean.hakmilik.seksyen.nama}</td>
                    </tr>

                    <tr><td id="idLabel">Daerah</td>
                        <td>:</td>
                        <td>${actionBean.hakmilik.daerah.nama}</td>
                    </tr>

                    <tr><td id="idLabel">Kategori Tanah</td>
                        <td> :</td>
                        <td>${actionBean.hakmilik.kategoriTanah.nama}</td>

                    </tr>

                    <tr><td id="idLabel">Syarat Nyata Tanah</td>
                        <td> :</td>

                        <c:if test="${actionBean.hakmilik.syaratNyata.syarat ne null}">
                            <td>${actionBean.hakmilik.syaratNyata.syarat}&nbsp;</td>
                        </c:if>
                        <c:if test="${actionBean.hakmilik.syaratNyata.syarat eq null}">
                            <td>Tiada &nbsp;</td>
                        </c:if>

                    </tr>

                    <tr><td id="idLabel" valign="top">Nama Pemilik Berdaftar</td>
                        <td valign="top"> :</td>
                        <td><c:forEach items="${actionBean.listHakmilikPihak}" var="list" varStatus="line">

                                <a href="#" title="Sila klik untuk perincian maklumat" onclick="p('${list.pihak.idPihak}');return false;">${list.pihak.nama}</a>
                                <br/>
                            </c:forEach>

                        </td>
                    </tr>

                    <tr><td id="idLabel">Tarikh Didaftarkan</td>
                        <td> :</td>
                        <td><fmt:formatDate type="time" pattern="dd/MM/yyyy" value="${actionBean.hakmilik.tarikhDaftar}"/>&nbsp;</td>
                    </tr>

                    <tr><td id="idLabel">No. PA</td>
                        <td> :</td>
                        <td><c:if test="${actionBean.hakmilik.noPelan eq null}">Tiada</c:if>
                            <c:if test="${actionBean.hakmilik.noPelan ne null}">${actionBean.hakmilik.noPelan}&nbsp;</c:if>
                        </td>
                    </tr>

                    <tr><td id="idLabel">No. Syit</td>
                        <td> :</td>
                        <td><c:if test="${actionBean.hakmilik.noLitho eq null}">Tiada</c:if>
                            <c:if test="${actionBean.hakmilik.noLitho ne null}">${actionBean.hakmilik.noLitho}&nbsp;</c:if>
                        </td>
                    </tr>

                    <tr><td colspan="3">&nbsp;</td></tr>
                    <tr><td colspan="3">
                            <div id="perincianPihak">
                                <p></p>
                            </div>
                        </td>
                    </tr>
                    <tr><td colspan="3">&nbsp;</td></tr>
                    <tr><td colspan="3"><b>3.Lain-Lain Maklumat</b></td></tr>
                    <tr><td colspan="3">&nbsp;</td></tr>

                    <tr><td colspan="3"><b>3.1 Hakmilik Telah Dibatalkan :</b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    </tr>
                    <tr>
                        <td align="center">
                            <s:radio name="kodStatusHakmilik" value="B"  disabled="true"/>Ya &nbsp;&nbsp;
                            <s:radio name="kodStatusHakmilik" value="D"  disabled="true"/>Tidak
                        </td>
                        <td colspan="2">&nbsp;</td>
                    </tr>
                    <tr><td colspan="3">&nbsp;</td></tr>
                    <tr><td colspan="3"><b>3.2 Senarai nombor hakmilik sambungan :</b></td></tr>
                    <br><tr>
                        <td colspan="3">
                            <c:forEach items="${actionBean.listHakmilikSambungan}" var="list" varStatus="line">
<!--                                    <s:text name="idHakmilikSamb" size="30" class="normal_text" value="${list.idHakmilik}"/><p/>-->
                                <p>
                                    <a href="#" title="Sila klik untuk perincian maklumat" onclick="senaraiHakmilikSamb('${list.idHakmilik}');return false;">${list.idHakmilik}</a>
                                    <br/>
                                </p>
                            </c:forEach>
                            <c:if test="${fn:length(actionBean.listHakmilikSambungan) == 0}">
                                Tiada Data
                            </c:if>

                        </td></tr>
                    <tr><td colspan="3">&nbsp;</td></tr>
                    <tr><td colspan="3">
                            <div id="hakmilikMuktamad">
                                <p></p>
                            </div>
                        </td>
                    </tr>
                    <tr><td colspan="3">&nbsp;</td></tr>
                    <tr><td colspan="3"><b>3.3 Nombor fail permohonan serah balik/pecah sempadan tanah :</b></td></tr>
                    <tr><td colspan="3">
                            <c:forEach items="${actionBean.listMohonPecahSempadan}" var="list" varStatus="line">
<!--                                    <s:text name="idPemohonanPPCS" size="30" class="normal_text" value="${list.idPermohonan}"/><p/>-->
                                <p>${list.idPermohonan}</p>
                            </c:forEach>
                            <c:if test="${fn:length(actionBean.listMohonPecahSempadan) == 0}">
                                Tiada Data
                            </c:if>
                        </td>
                    </tr>
                    <tr><td colspan="3">&nbsp;</td></tr>

                </table>
                <center><s:button name="simpanMaklumatAduanHakmilik" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form,this.name,'page_div')"/>
                    <s:button class="btn" value="Isi Semula" name="reset" id="reset" onclick="resetForm()"/></center>
                    <%--  <c:if test="${actionBean.tarikhTandatangan ne null}">
                      <s:button  name="reset" value="Isi Semula" class="btn" onclick="test1()"/></center>
                      </c:if>
                      <c:if test="${actionBean.tarikhTandatangan eq null}">
                      <s:button  name="reset" value="Isi Semula" class="btn" onclick="test2()"/></center>
                      </c:if>--%>

            </div>
        </fieldset>
    </div>
</s:form>
