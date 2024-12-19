<%--
    Document   : kemasukan_aduan
    Created on : Apr 7, 2010, 11:36:04 AM
    Author     : aminah.abdmutalib
--%>
<%@ page language="java" contentType="text/html;" import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="etanah.model.Pengguna"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript">
    function refreshPage(){
        var q = $('#form1').serialize();
        var url = document.form1.action + '?refreshPage&';// + event;
        window.location = url+q;
    }

    function refreshPageKemasukkanAduan(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/kemasukan_aduan?refreshPage';
        $.get(url, function(data){$('#page_div').html(data);},'html');
    }

    function addPopupForm(){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/kemasukan_aduan?oksPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=600,scrollbars=yes");
    }
    function addPopupAduanLokasi(){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/kemasukan_aduan?aduanLokasiPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }
    function editPopupForm(id){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/kemasukan_aduan?oksPopup2&id="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=600,scrollbars=yes");
    }
    function editPopupAduanLokasiForm(id){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/kemasukan_aduan?editAduanLokasi&id="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }
    function removeOKS(id){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            $.get('${pageContext.request.contextPath}/penguatkuasaan/kemasukan_aduan?removeOKS&id='+id,
            function(html){
                $("#oksDiv").replaceWith($('#oksDiv', $(html)));
            }, 'html');
            //to refresh page after delete oks - bugs fixing for server (list not updated after delete)
            var q = $('#form1').serialize();
            var url = document.form1.action + '?refreshPage&';// + event;
            window.location = url+q;
        }
    }

    function removeAduanLokasi(id){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            $.get('${pageContext.request.contextPath}/penguatkuasaan/kemasukan_aduan?removeAduanLokasi&id='+id,
            function(html){
                //alert(html);
                $("#seksyen49").replaceWith($('#seksyen49', $(html)));
                //chooseSeksyen();

            }, 'html');
        }
    }

    function validateForm(){

        if(document.form1.cara.value=="")
        {
            alert("Sila Pilih Cara Aduan");
            $("#cara").focus();
            return false;
        }
        if(document.form1.sebab.value=="")
        {
            alert("Sila isi Ringkasan Aduan");
            $("#sebab").focus();
            return false;
        }
        if(document.form1.kodUrusan.value=="")
        {
            alert("Sila pilih Peruntukan Seksyen");
            $("#kodUrusan").focus();
            return false;
        }
        if(document.form1.penyerahNama.value=="")
        {
            alert("Sila isi Nama Pengadu");
            $("#penyerahNama").focus();
            return false;
        }

        if(document.form1.bpm.value=="" && document.form1.kodUrusan.value != "429" && $("#kodNegeriAduan").val() == "05")
        {
            alert("Sila Pilih Bandar/Pekan/Mukim");
            $("#bpm").focus();
            return false;
        }
        if(document.form1.message.value=="")
        {
            alert("Sila isi Lokasi");
            $("#message").focus();
            return false;
        }

        return true;
    }
    function validateNumber(elmnt,content) {

        //if it is fullstop (.) , then remove it..
        for( var i = 0; i < content.length; i++ )
        {
            var str = "";
            str = content.substr( i, 1 );
            if(str == "."){
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }

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
    function textCounter(field, countfield, maxlimit) {
        if (field.value.length > maxlimit) // if too long...trim it!
            field.value = field.value.substring(0, maxlimit);
        // otherwise, update 'characters left' counter
        else
            countfield.value = maxlimit - field.value.length;
    }
    function validateTelLength(value){
        var plength = value.length;
        if(plength < 7){
            alert('No. Telefon yang dimasukkan salah.');
            $('#telefon').val("");
            $('#telefon').focus();
        }
    }

    function validatePoskodLength(value){
        var plength = value.length;
        if(plength != 5){
            alert('Poskod yang dimasukkan salah.');
            $('#poskod').val("");
            $('#poskod').focus();
        }
    }

    function validateKPLength(value){
        var plength = value.length;
        if(plength != 12){
            alert('Kad Pengenalan Baru yang dimasukkan salah.');
            $('#noPengenalanBaru').val("");
            $('#noPengenalanBaru').focus();
        }
    }

    $(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });

        copySeksyen();
        jenisPengenalan();
    <%--chooseSeksyen();--%>

        });

        function ValidateEmail(){
            var emailID= $("#email").val();

            if ((emailID==null)||(emailID=="")){
                return true;
            }
            if ((emailID!=null)||(emailID!="")){
                if(emailcheck(emailID)==false){
                    $("#email").val("");
                    $("#email").focus();
                    return false;
                }
            }
            return true;
        }

        function emailcheck(str) {

            var at="@";
            var dot=".";
            var lat=str.indexOf(at);
            var lstr=str.length;
            var ldot=str.indexOf(dot);
            if (str.indexOf(at)==-1){
                alert('"Alamat Email" salah');
                return false;
            }

            if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
                alert('"Alamat Email" salah');
                return false;
            }

            if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
                alert('"Alamat Email" salah');
                return false;
            }

            if (str.indexOf(at,(lat+1))!=-1){
                alert('"Alamat Email" salah');
                return false;
            }

            if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
                alert('"Alamat Email" salah');
                return false;
            }

            if (str.indexOf(dot,(lat+2))==-1){
                alert('"Alamat Email" salah');
                return false;
            }

            if (str.indexOf(" ")!=-1){
                alert('"Alamat Email" salah');
                return false;
            }

            return true;
        }

        function sek49(){
            if($('#seksyen').val() == '49'){
                document.getElementById("oksDiv").style.visibility = 'hidden';
                document.getElementById("oksDiv").style.display = 'none';
            } else{
                document.getElementById("oksDiv").style.visibility = 'visible';
                document.getElementById("oksDiv").style.display = '';
            }

        }

        function copySeksyen(){
            var i = $('#seksyen').val();
            document.getElementById("kodUrusan").value = i;
            showHideLokasiKejadian();
            //chooseSeksyen();

        }

        //        function chooseSeksyen(){
        //            if($('#kodNegeriAduan').val() == '04'){
        //                // alert("426");
        //                if($('#seksyen').val() == '426' || $('#seksyen').val() == '425' || $('#seksyen').val() == '425A' || $('#seksyen').val() == '127'){
        //                    document.getElementById("oksDiv").style.visibility = 'hidden';
        //                    document.getElementById("oksDiv").style.display = 'none';    
        //                }
        //            } else{
        //                document.getElementById("oksDiv").style.visibility = 'visible';
        //                document.getElementById("oksDiv").style.display = '';
        //            }
        //
        //        }

        function limitText(limitField, limitNum) {
            if (limitField.value.length > limitNum) {
                limitField.value = limitField.value.substring(0, limitNum);
            }
        }

        function findSeksyenKodUrusan(){
            var textKodUrusanKod = document.getElementById('kodUrusan');
            if (textKodUrusanKod.value.length > 0){
                var selectKodUrusan = document.getElementById('seksyen');
                selectKodUrusan.selectedIndex = 0;
                var kod = textKodUrusanKod.value.toUpperCase();
                for (var i = 0; i < selectKodUrusan.options.length; ++i){
                    if (selectKodUrusan.options[i].value == kod){
                        selectKodUrusan.selectedIndex = i;
                        break;
                    }
                }
                if (selectKodUrusan.selectedIndex == 0){
                    alert('Kod Urusan ' + kod + ' tidak dijumpai.');
                }
            }

            //chooseSeksyen();
        }

        function jenisPengenalan(){
            if($('#pengenalan').val() == 'B'){
                document.getElementById("noPengenalanBaru").style.visibility = 'visible';
                document.getElementById("noPengenalanBaru").style.display = '';
                $('#noPengenalanLain').hide();

            }else{
                $('#noPengenalanLain').show();
                document.getElementById("noPengenalanBaru").style.visibility = 'hidden';
                document.getElementById("noPengenalanBaru").style.display = 'none';

            }
        }

        function showHideLokasiKejadian(){
            var hide = ($("#kodUrusan").val()=="429");

            if(hide){
                //$("#lokasiKejadianDiv").hide();
                $("#lokasikejadian1 label em").html("");
                $("#othersSeksyen fieldset legend").html("Lokasi Aduan");
                /*
                $("#lokasikejadian1").hide();
            $("#lokasikejadian2").hide();
            $("#lokasikejadian3").hide();
            */
            }else{
                //$("#lokasiKejadianDiv").show();
                $("#lokasikejadian1 label em").html("*");
                $("#othersSeksyen fieldset legend").html("Lokasi Kejadian");
                /*
                $("#lokasikejadian1").show();
            $("#lokasikejadian2").show();
            $("#lokasikejadian3").show();
            */
            }
        }
</script>
<table width="100%" bgcolor="yellow">
    <tr>
        <td width="100%" height="20" >
            <div style="background-color: yellow;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">PENGUATKUASAAN : Kemasukan Aduan</font>
            </div>
        </td>
    </tr>
</table>
<s:form name="form1" id="form1" beanclass="etanah.view.penguatkuasaan.KemasukanAduanActionBean">
    <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
    <s:messages/>
    <div class="instr" align="center">
        <s:errors/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Tempat Aduan</legend>
            <s:hidden name="kodNegeri" id="kodNegeriAduan"/>
            <div class="instr-fieldset">
                <font color="red">PERINGATAN:</font>Sila Masukkan Maklumat Berikut.
            </div>&nbsp;
            <p>
                <label>Tarikh :</label>
                <fmt:formatDate type="time" pattern="dd/MM/yyyy" value="<%=new java.util.Date()%>"/>
                &nbsp;</p>
            <p>
                <label>Daerah :</label>
                ${kodDaerah} - ${daerah} &nbsp;
            </p>
            <p>
                <label><em>*</em>Cara Aduan :</label>
                <s:select name="caraPermohonan.kod"  style="width:139px;" id="cara">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${actionBean.senaraiKodCaraPermohonan}" label="nama" value="kod" sort="nama" />
                </s:select>&nbsp;
            </p>
            <p>
                <label><em>*</em>Ringkasan Aduan :</label>
                <s:textarea name="sebab" id="sebab" value="${actionBean.sebab}"  rows="5" cols="50" onkeydown="limitText(this,2500);" onkeyup="limitText(this,2500);"/>
            </p>
            <p>
                <label><em>*</em>Peruntukan Seksyen :</label>
                <input name="kodUrusanSeksyen" id="kodUrusan" onblur="javascript:findSeksyenKodUrusan(this.value)" onkeyup="this.value=this.value.toUpperCase();" size="3" onChange="javascript:showHideLokasiKejadian()"/>&nbsp;
                <%-- <input name="kodUrusanSeksyen" id="kodUrusan" onblur="javascript:findSeksyenKodUrusan(this.value)" onkeyup="this.value=this.value.toUpperCase();" size="3" onclick="javascript:sek49()"/>&nbsp;
                    <s:select name="kodUrusan.kod" value="${actionBean.kodUrusan.kod}" id="seksyen" onblur="javascript:sek49()" onchange="javascript:copySeksyen()"> --%>
                <s:select name="kodUrusan.kod" value="${actionBean.kodUrusan.kod}" id="seksyen" onchange="javascript:copySeksyen()">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${actionBean.senaraiUrusan}" label="nama" value="kod" sort="kod" />
                </s:select>&nbsp;
            </p>

        </fieldset >
        <br>
        <fieldset class="aras1">
            <legend>Maklumat Pengadu</legend>
            <p>
                <label><em>*</em>Nama :</label>
                <s:text name="penyerahNama" id="penyerahNama" size="42" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Jenis Pengenalan :</label>
                <s:select name="penyerahJenisPengenalan.kod"  value=""  style="width:139px;" id="pengenalan" onchange="jenisPengenalan()">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" sort="nama" />
                </s:select>
                &nbsp;
            </p>
            <p id="noPengenalanLain">
                <label>No.Pengenalan :</label>
                <s:text name="penyerahNoPengenalanLain" id="penyerahNoPengenalanLain" maxlength="20" onkeyup="this.value=this.value.toUpperCase();" />
                &nbsp;
            </p>
            <p id="noPengenalanBaru" style="visibility: hidden; display: none">
                <label>No.Pengenalan :</label>
                <s:text name="penyerahNoPengenalanBaru" id="penyerahNoPengenalanBaru" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateKPLength(this.value);"/>
                <font color="red" size="1">cth : 850510075342 </font>
                &nbsp;
            </p>
            <p>
                <label>Alamat :</label>
                <s:text name="penyerahAlamat1"  size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="penyerahAlamat2"  size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>

            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="penyerahAlamat3"  size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>

            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="penyerahAlamat4"  size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>

                &nbsp;
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name="penyerahPoskod" id="poskod" size="19" maxlength="5" onkeyup="validateNumber(this,this.value);" onchange="validatePoskodLength(this.value);"/>

            </p>
            <p>
                <label>Negeri :</label>
                <s:select name="penyerahNegeri.kod"  style="width:139px;">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" sort="nama" />
                </s:select>
            </p>
            <p>
                <label>No.Telefon :</label>
                <s:text name="penyerahNoTelefon1" id="telefon" size="19" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);"/>

            </p>
            <p>
                <label>Email :</label>
                <s:text name="penyerahEmail" id="email" size="40" maxlength="100" onblur="return ValidateEmail()"/>

            </p>
        </fieldset>
        <br>

        <div id="othersSeksyen">
            <fieldset class="aras1">
                <legend>Lokasi Kejadian</legend>
                <div class="instr-fieldset">
                    Sila masukkan maklumat lokasi.
                </div>
                <div id="lokasiKejadianDiv">
                <p id="lokasikejadian1">
                    <label><em>*</em>Bandar/Pekan/Mukim :</label>
                    <s:select name="bandarPekanMukim.kod" id="bpm">
                        <s:option value=""> Sila Pilih </s:option>
                        <c:forEach items="${actionBean.listBandarPekanMukim}" var="line">
                            <s:option value="${line.kod}">${line.bandarPekanMukim} - ${line.nama}</s:option>
                        </c:forEach>
                    </s:select>
                    &nbsp;
                </p>
                <p id="lokasikejadian2">
                    <label>Jenis Tanah :</label>
                    <s:select name="pemilikan.kod" id="milik">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodPemilikan}" label="nama" value="kod" sort="nama" />
                    </s:select>
                    &nbsp;
                </p>
                <p id="lokasikejadian3">
                    <label>Jenis Nombor:</label>
                    <s:select name="kodLot.kod" id="kodLot">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodLot}" label="nama" value="kod" sort="nama" />
                    </s:select>
                    <s:text name="noLot" onkeyup="validateNumber(this,this.value);"/> &nbsp;
                </p>
                </div>
                <p>
                    <label><em>*</em> Lokasi :</label>
                    <s:textarea name="lokasi" id="message" rows="5" cols="50" onkeydown="limitText(this,499);" onkeyup="limitText(this,499);" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                </p>
            </fieldset>
        </div>

        <div id="seksyen49" style="visibility: hidden; display: none">
            <fieldset class="aras1">
                <legend>Lokasi Kejadian</legend>
                <div class="content" align="center">
                    <div class="instr-fieldset">
                        <em>*</em> Klik butang tambah untuk masukkan maklumat lokasi kejadian.
                    </div>
                    <display:table class="tablecloth" name="${actionBean.senaraiLokasiKejadian}" pagesize="10" cellpadding="0" cellspacing="0" id="line1" >
                        <display:column title="Bil">${line1_rowNum}</display:column> <c:set var="jumLokasi" value="${line1_rowNum}" />
                        <display:column property="bandarPekanMukim.nama" title="Bandar"></display:column>
                        <display:column property="jenisTanah.nama" title="Jenis Tanah"></display:column>
                        <display:column property="noLot" title="No Lot"></display:column>
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line1_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeAduanLokasi('${line1_rowNum-1}');"/>
                            </div>
                        </display:column>
                        <display:column title="Kemaskini">
                            <div align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                     id='rem_${line1_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editPopupAduanLokasiForm('${line1_rowNum-1}');"/>
                            </div>
                        </display:column>
                    </display:table>
                    <s:button class="btn" value="Tambah" name="addPopup" id="addPopup" onclick="addPopupAduanLokasi();" />
                </div>
                <br>
            </fieldset>
        </div>

        <%--        <div id="oksDiv" style="visibility: hidden; display: none">
                    <fieldset class="aras1">
                        <legend>Maklumat Orang Yang Disyaki</legend>
                        <div class="content" align="center">
                            <div class="instr-fieldset">
                                Klik butang tambah untuk masukkan maklumat orang yang disyaki
                            </div>
                            <display:table class="tablecloth" name="${actionBean.senaraiOKS}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                                <display:column title="Bil">${line_rowNum}</display:column>
                                <display:column title="No Pengenalan">
                                    <c:if test="${line.kodJenisPengenalan.kod eq 'B'}">
                                        ${line.noPengenalanBaru}
                                    </c:if>
                                    <c:if test="${line.kodJenisPengenalan.kod ne 'B'}">
                                        ${line.noPengenalanLain}
                                    </c:if>
                                </display:column>
                                <display:column property="nama" title="Nama"></display:column>
                                <display:column title="Alamat" style="text-transform: uppercase">${line.alamat.alamat1}
                                    <c:if test="${line.alamat.alamat2 ne null}"> , </c:if>
                                    ${line.alamat.alamat2}
                                    <c:if test="${line.alamat.alamat3 ne null}"> , </c:if>
                                    ${line.alamat.alamat3}
                                    <c:if test="${line.alamat.alamat4 ne null}"> , </c:if>
                                    ${line.alamat.alamat4}
                                    <c:if test="${line.alamat.poskod ne null}"> , </c:if>
                                    ${line.alamat.poskod}
                                    <c:if test="${line.alamat.negeri.nama ne null}"> , </c:if>
                                    ${line.alamat.negeri.nama}</display:column>
                                <display:column title="Hapus">
                                    <div align="center">
                                        <img alt='Klik Untuk Hapus' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeOKS('${line_rowNum-1}');"/>
                                    </div>
                                </display:column>
                                <display:column title="Kemaskini">
                                    <div align="center">
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editPopupForm('${line_rowNum-1}');"/>
                                    </div>
                                </display:column>
                            </display:table>
                            <s:button class="btn" value="Tambah" name="addPopup" id="addPopup" onclick="javascript:addPopupForm();" />
                        </div>
                        <br>
                    </fieldset>
                </div>--%>

        <p align="right">
            <s:submit class="btn" name="saveMohon" value="Simpan" onclick="return validateForm();"/>
        </p>
    </div>
</s:form>

