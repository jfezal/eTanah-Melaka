<%--
    Document   : maklumat_jadualpetakPBS
    Created on : Jan 04, 2011, 10:42:01 AM
    Author     : zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
        <title>Strata</title>
        <script type="text/javascript">
     
            function tambahBngn() {
                var url = "${pageContext.request.contextPath}/strata/urusan_pbs?tambahBngnSementara";
                window.open(url, "etanah", "status=0,toolbar=0,location=0,menubar=0,width=800,height=350");
               
            }
            function removeBngn(val){
                if(confirm('Adakah anda pasti?')) {
                    var url = '${pageContext.request.contextPath}/strata/urusan_pbs?deleteBngn&idBangunan='+val;
                    $.get(url,
                    function(data){
                        $('#page_div').html(data);
                    },'html');

                }
            }

            function save(event, f){

                var nama = document.form1.nama.value;
                var bil_tgkt = document.form1.bil_tgkt.value;

                if ((nama == ""))
                {
                    alert('Sila masukkan Nama Bangunan ');
                    document.form1.nama.focus();
                }
                else if ((bil_tgkt == ""))
                {
                    alert('Sila masukkan bilangan tingkat ');
                    document.form1.bil_tgkt.focus();
                }

                else
                {
                    var q = $(f).formSerialize();
                    var url = f.action + '?' + event;
                    $.post(url,q,
                    function(data){
                        $('#page_div').html(data);

                    },'html');
                }
            }
            $(document).ready(function() {
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $("#close").click( function(){
                    setTimeout(function(){
                        self.close();
                    }, 100);
                });
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
            function popup(b,p,idT){

                window.open("${pageContext.request.contextPath}/strata/urusan_pbbm?showForm2&namaBangunan="+b+"&tingkat="+p+"&idTingkat="+idT, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=800,height=250");
            }
            function popupPetakAksesori(b,t,p){

                window.open("${pageContext.request.contextPath}/strata/urusan_pbbm?showForm3&idBangunan="+b+"&idTingkat="+t+"&idPetak="+p, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=800,height=200");
            }
            

        </script>
    </head>
    <body>

        <s:form  name="form1" beanclass="etanah.view.strata.BangunanSementaraActionBean">
            <s:messages/>
            <s:errors/>
            <br>
            <div class="subtitle">
                <s:button name="Tambah" value="Tambah Bangunan" class="longbtn" onclick="tambahBngn();" />
                <%--  <fieldset class="aras1">
                  <a href="#bottom"><s:button name="Tambah" value="Tambah Bangunan" class="longbtn"/></a>
                  <s:submit name="extrakBangunan" value="Ekstrak Petak" class="longbtn"/>
              </fieldset>--%>
            </div>
            <br>
            <c:if test="${fn:length(actionBean.mhnBngnListM) > 0}">
                <div class="subtitle">
                    <fieldset class="aras1">
                        <legend>Senarai Bagi Bangunan Kekal</legend>
                        <br>
                        <p>
                        <table class="tablecloth">
                            <tr style="width: 100%">
                                <th>Bangunan (M)</th>

                                <th>Senarai Tingkat</th>

                                <th>Senarai Petak</th>
                                <th>Keluasan m2</th>
                                <th>Unit-unit Syer</th>
                                <th>Jenis Kegunaan</th>
                                <th></th>
                                <th>Petak Aksesori</th>
                                <th>Jenis Kegunaan</th>
                                <th>Lokasi Petak Aksesori</th>
                                <%-- <th></th>--%>




                            </tr>
                            <c:set var="items" value="0"/>
                            <c:set var="items2" value="0"/>
                            <c:set var="items3" value="0"/>



                            <c:forEach items="${actionBean.mhnBngnListM}" var="bgn" varStatus="statusB">

                                <tr>
                                    <td>${bgn.nama}
                                        <%-- <c:if test="${statusB.last}">
                                             <img title="Klik Untuk Hapus" alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                  onclick="removeBngn('${bgn.idBangunan}')" onmouseover="this.style.cursor='pointer';" >
                                         </c:if>--%>
                                    </td>

                                    <c:forEach items="${bgn.senaraiTingkat}" var="tgkt" varStatus="status">

                                        <td>${tgkt.tingkat}</td>

                                        <c:if test="${fn:length(tgkt.senaraiPetak) > 0}">
                                            <c:forEach items="${tgkt.senaraiPetak}" var="petak" varStatus="statusP">

                                                <td>${petak.nama}</td>
                                                <td><s:text name="petakLuas[${items}]"  size="10"/></td>
                                                <td><s:text name="petakSyer[${items}]" size="12"/></td>
                                                <td><s:select name="petakKegunaan[${items}]" style="width:100px">
                                                        <s:option value="0">Sila Pilih</s:option>
                                                        <s:options-collection collection="${actionBean.kGunaPetakL}" label="nama" value="kod" />
                                                    </s:select>
                                                    <%--  <td>
                                                          <c:if test="${status.last}">
                                                              <c:if test="${statusP.last}">
                                                                  <div align="center">
                                                                      <img title="Klik Untuk Hapus" alt='Klik Untuk Hapus ' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                                           onclick="removePetak('${petak.idPetak}')" onmouseover="this.style.cursor='pointer';" >
                                                                  </div>
                                                              </c:if>
                                                          </c:if>
                                                      </td>--%>

                                                <td>
                                                    <div align="center">
                                                        <img  title="Klik Untuk Tambah Petak Aksesori" alt='Klik Untuk Tambah Petak Aksesori' border='0' src='${pageContext.request.contextPath}/images/tambah.png' class='rem'
                                                              onclick="popupPetakAksesori('${bgn.idBangunan}','${tgkt.idTingkat}','${petak.idPetak}');" onmouseover="this.style.cursor='pointer';" >
                                                    </div>
                                                </td>

                                                <c:forEach items="${petak.senaraiPetakAksesori}" var="petakAksesori" varStatus="statusPA">
                                                    <td>${petakAksesori.nama}</td>
                                                    <td><s:select name="petakKegunaanAksr[${items3}]">
                                                            <s:option value="0">Sila Pilih</s:option>
                                                            <s:options-collection collection="${actionBean.kGunaPetakAksesoriL}" label="nama" value="kod" />
                                                        </s:select>
                                                    </td>
                                                    <td><s:select name="lokasiAksr[${items3}]">
                                                            <s:option value="0">Sila Pilih</s:option>
                                                            <s:option value="Luar Bangunan">Luar Bangunan</s:option>
                                                            <s:option value="Dalam Bangunan">Dalam Bangunan</s:option>
                                                        </s:select>

                                                        <%-- <td>
                                                             <c:if test="${status.last}">
                                                                 <c:if test="${statusP.last}">
                                                                     <c:if test="${statusPA.last}">
                                                                         <div align="center">
                                                                             <img title="Klik Untuk Hapus" alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                                                  onclick="removePtkAksr('${petakAksesori.idAksesori}')" onmouseover="this.style.cursor='pointer';" >
                                                                         </div>
                                                                     </c:if>
                                                                 </c:if>
                                                             </c:if>
                                                         </td>--%>
                                                <tr>
                                                    <c:if test="${not statusPA.last}">
                                                        <td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                        <%-- <td>&nbsp;</td>--%>

                                                        <td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                    </c:if>

                                                    <c:if test="${statusPA.last}">
                                                    </tr>

                                                </c:if>
                                                <c:set var="items3" value="${items3+1}"/>
                                            </c:forEach>


                                            </tr>

                                            <c:if test="${not statusP.last}">

                                                <td>&nbsp;</td>
                                                <td> <c:if test="${statusP.count eq 1}">
                                                        <s:checkbox name="tingkatMezanin[${items2}]" value="${bgn.tingkatMezanin.idTingkat}"></s:checkbox> Mezanin
                                                    </c:if>
                                                </td>

                                            </c:if>

                                            <c:if test="${ statusP.last}">

                                                <td>&nbsp;</td>
                                                <td><s:button  onclick="popup('${bgn.nama}','${tgkt.tingkat}','${tgkt.idTingkat}');" value="Tambah Petak" name="tambahPetak" class="btn" /></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <%-- <td>&nbsp;</td>--%>

                                                <%--<c:if test="${fn:length(actionBean.petakAksesoriL) > 0}">--%>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>

                                                <%--</c:if>--%>

                                            </c:if>

                                            <c:if test="${statusP.last}">
                                                <td>&nbsp;</td>
                                                <td> <c:if test="${statusPA.count eq 1}">
                                                        <s:checkbox name="tingkatMezanin[${items2}]" value="${bgn.tingkatMezanin.idTingkat}"></s:checkbox> Mezanin
                                                    </c:if>
                                                </td>
                                            </c:if>
                                            <c:set var="items" value="${items+1}"/>
                                        </c:forEach>
                                    </c:if>

                                    <c:if test="${fn:length(tgkt.senaraiPetak) == 0}">
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td> <s:button  onclick="popup('${bgn.nama}','${tgkt.tingkat}','${tgkt.idTingkat}');" value="Tambah Petak" name="tambahPetak" class="btn" /></td>
                                            <%-- <td>
                                                 <c:if test="${status.last}">
                                                     <div align="center">
                                                         <img title="Klik Untuk Hapus" alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                              onclick="removeTingkat('${tgkt.idTingkat}')" onmouseover="this.style.cursor='pointer';" >
                                                     </div>
                                                 </c:if>
                                             </td>--%>
                                        </c:if>
                                    </tr>
                                    <c:if test="${not status.last}">
                                        <tr>
                                            <td>&nbsp;</td>
                                        </c:if>

                                        <c:set var="items2" value="${items2+1}"/>

                                    </c:forEach>
                                </tr>
                            </c:forEach>
                        </table>
                        </p>
                        <br>
                        <label>&nbsp;</label>
                        <s:button name="kemaskini" value="Kemaskini" class="btn" onclick="if(confirm('Adakah anda pasti?'))doSubmit(this.form, this.name,'page_div');"/>
                        <br>
                        <br>
                    </fieldset>

                </div>
            </c:if>
            <c:if test="${fn:length(actionBean.mhnBngnListP) > 0}">
                <div class="subtitle">

                    <fieldset class="aras1">
                        <legend>Senarai Bagi Bangunan Sementara</legend>
                        <br>

                        <center>
                            <table class="tablecloth">
                                <tr style="width: 100%">
                                    <th>Bangunan (P)</th>
                                    <th>Bil Petak</th>
                                    <th>Unit Syer</th>
                                    <th>Tarikh Siap</th>
                                    <th>Hapus</th>
                                </tr>
                                <c:set var="items" value="0"/>
                                <c:set var="items2" value="0"/>
                                <c:set var="items3" value="0"/>
                                <c:forEach items="${actionBean.mhnBngnListP}" var="bgn" varStatus="status">

                                    <tr>
                                        <td><s:text name="mhnBngnListP[${items}].nama" size="10"onkeyup="validateNumber(this,this.value);"/></td>
                                        <%--${bgn.kategoriBangunan}${bgn.nama}</td>--%>
                                        <td><s:text name="mhnBngnListP[${items}].bilanganPetak" size="10"onkeyup="validateNumber(this,this.value);"/></td>
                                        <%--${bgn.bilanganPetak}</td>--%>
                                        <td>
                                            <s:text name="mhnBngnListP[${items}].syerBlok" size="10"onkeyup="validateNumber(this,this.value);"/></td>
                                            <%--${bgn.syerBlok}</td>--%>
                                        <td>
                                            <s:text name="bngn" size="10" class="datepicker"onchange="validateDate(this,this.value);"/></td>
                                            <%--<s:text name="bgn" size="10" class="datepicker" onchange="validateDate(this,this.value);"/></td>--%>
                                        <td> <div align="center">
                                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                     id='rem_${line_rowNum}' onclick="removeBngn('${bgn.idBangunan}')" onmouseover="this.style.cursor='pointer';">
                                            </div></td>
                                    </tr>

                                    <c:set var="items" value="${items+1}"/>

                                </c:forEach>
                            </table>
                        </center>
                        <br>
                        <label>&nbsp;</label>
                        <s:button name="kemaskiniBngnSementara" value="Kemaskini" class="btn" onclick="if(confirm('Adakah anda pasti?'))doSubmit(this.form, this.name,'page_div');"/>
                        <br>
                        <br>
                        <%--<a href="javascript:location.reload(true)">Refresh this page</a>--%>

                    </fieldset>
                </div>
            </c:if>

            <%--</c:if>--%>
        </s:form>
    </body>
</html>