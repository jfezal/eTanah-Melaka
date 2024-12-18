<%--
    Document   : oksPopupEdit
    Created on : Apr 22, 2010, 4:55:12 PM
    Author     : aminah.abdmutalib
    modify by zabedah 17/04/2011
    modify by ctzainal 16.05.2011
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
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
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<script src="<%= request.getContextPath()%>/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        jenisPengenalan();
        jenisPengenalanWaris()
    });

    function limitText(limitField, limitNum) {
        if (limitField.value.length > limitNum) {
            limitField.value = limitField.value.substring(0, limitNum);
        }
    }
            
    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
        },'html');
        self.opener.refreshPage();
        self.close();
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
    function validateForm(){
        if(confirm('Adakah anda pasti untuk kemaskini?')) {
            if($('#nama').val() == ''){
                alert("Sila Isi Nama");
                $('#nama').focus();
                return false;
            }
            return true;
        }
    }
    function test(f) {
        $(f).clearForm();
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

    function calcDays(){
        var dari = document.getElementById('tarikhDitahan').value;
        var vsplit1 = dari.split('/');
        var fulldate1 = vsplit1[1]+'/'+vsplit1[0]+'/'+vsplit1[2];

        var hingga = document.getElementById('tarikhDiBebas').value;
        var vsplit2 = hingga.split('/');
        var fulldate2 = vsplit2[1]+'/'+vsplit2[0]+'/'+vsplit2[2];

        var sdate1 = new Date(fulldate1);
        var sdate2 = new Date(fulldate2);

        var m = sdate2 - sdate1;
        var day = (m/(1000*60*60))/24;
        var x = Math.floor(parseFloat(day));
        document.getElementById('diBebas').value=x+1;

    }

    function findDOB(){
        var noPengenalan = $('#noPengenalanOksBaru').val();

        if(noPengenalan.length =="12"){
            //tahun
            var year = noPengenalan.substr(0, 2);
            var tahun = "19"+year;
            var selectKodUrusan = document.getElementById('tahunReadonly');
               selectKodUrusan.selectedIndex = 0;
               for (var i = 0; i < selectKodUrusan.options.length; ++i){
                if (selectKodUrusan.options[i].value == tahun){
                    selectKodUrusan.selectedIndex = i;
                    document.getElementById('tahun').value = selectKodUrusan.options[i].value;
                    break;
                }
               }

            //month
            var month = noPengenalan.substr(2, 1);
            var bulan = "";
            if( month == "0"){
                bulan = noPengenalan.substr(3, 1);
            }
            else{
                bulan = noPengenalan.substr(2, 2);
                if(bulan >12){
                    alert("Sila masukkan bulan yang betul.");
                }
            }
            var selectKodUrusan = document.getElementById('bulanReadonly');
               selectKodUrusan.selectedIndex = 0;
               for (var i = 0; i < selectKodUrusan.options.length; ++i){
                if (selectKodUrusan.options[i].value == bulan){
                    selectKodUrusan.selectedIndex = i;
                    document.getElementById('bulan').value = selectKodUrusan.options[i].value;
                    break;
                 }
              }

            //day
            var hari = noPengenalan.substr(4,1);
            var day ="";
            if(hari == "0"){
                day = noPengenalan.substr(5,1);
            }
            else{
                day = noPengenalan.substr(4,2);
                if(day >31){
                    alert("Sila masukkan hari yang betul.");
                }
            }

            var selectKodUrusan = document.getElementById('hariReadonly');
            selectKodUrusan.selectedIndex = 0;
            for (var i = 0; i < selectKodUrusan.options.length; ++i){
                if (selectKodUrusan.options[i].value == day){
                    selectKodUrusan.selectedIndex = i;
                    document.getElementById('hari').value = selectKodUrusan.options[i].value;
                    break;
                }
            }
        }else{
            alert("Sila masukkan 12 digit nombor pengenalan");
        }
    }

    function jenisPengenalan(){
        if($('#pengenalan').val() == 'B'){
            $('#noPengenalanLain').hide();
            document.getElementById("noPengenalanBaru").style.visibility = 'visible';
            document.getElementById("noPengenalanBaru").style.display = '';
            document.getElementById('hariReadonly').disabled = true;
            document.getElementById('bulanReadonly').disabled = true;
            document.getElementById('tahunReadonly').disabled = true;

            $('#penyerahNoPengenalanLain').attr("disabled", true);
            $('#penyerahNoPengenalanBaru').attr("disabled", false);

        }else{
            $('#noPengenalanLain').show();
            document.getElementById("noPengenalanBaru").style.visibility = 'hidden';
            document.getElementById("noPengenalanBaru").style.display = 'none';
            document.getElementById('hariReadonly').disabled = false;
            document.getElementById('bulanReadonly').disabled = false;
            document.getElementById('tahunReadonly').disabled = false;

            $('#penyerahNoPengenalanBaru').attr("disabled", true);
            $('#penyerahNoPengenalanLain').attr("disabled", false);

        }

        var hari = $('#hari').val();
        var bulan = $('#bulan').val();
        var tahun = $('#tahun').val();

        document.getElementById('hariReadonly').value = hari;
        document.getElementById('bulanReadonly').value = bulan;
        document.getElementById('tahunReadonly').value = tahun;
    }


    function jenisPengenalanWaris(){
            if($('#pengenalanWaris').val() == 'B'){
                document.getElementById("noPengenalanBaruWaris").style.visibility = 'visible';
                document.getElementById("noPengenalanBaruWaris").style.display = '';
                $('#noPengenalanLainWaris').hide();

                $('#penyerahNoPengenalanLainWaris').attr("disabled", true);
                $('#penyerahNoPengenalanBaruWaris').attr("disabled", false);
            }else{
                $('#noPengenalanLainWaris').show();
                document.getElementById("noPengenalanBaruWaris").style.visibility = 'hidden';
                document.getElementById("noPengenalanBaruWaris").style.display = 'none';

                $('#penyerahNoPengenalanBaruWaris').attr("disabled", true);
                $('#penyerahNoPengenalanBaruWaris').attr("disabled", false);
            }
        }

     function findDOBW(){
            var noPengenalanWaris = $('#penyerahNoPengenalanBaruWaris').val();
            if(noPengenalanWaris.length =="12"){

                //tahun
                var year = noPengenalanWaris.substr(0, 2);
                var tahun = "19"+year;
                var selectKodUrusan = document.getElementById('tahun');
                   selectKodUrusan.selectedIndex = 0;
                  for (var i = 0; i < selectKodUrusan.options.length; ++i){
                    if (selectKodUrusan.options[i].value == tahun){
                        selectKodUrusan.selectedIndex = i;
                        break;
                    }
                  }

                //month
                var month = noPengenalanWaris.substr(2, 1);
                var bulan = "";
                if( month == "0"){
                    bulan = noPengenalanWaris.substr(3, 1);
                }
                else{
                    bulan= noPengenalanWaris.substr(2, 2);
                    if(bulan >12){
                        alert("Sila masukkan bulan yang betul.");
                    }
                }
                var selectKodUrusan = document.getElementById('bulan');
                   selectKodUrusan.selectedIndex = 0;
                     for (var i = 0; i < selectKodUrusan.options.length; ++i){
                                       if (selectKodUrusan.options[i].value == bulan){
                                               selectKodUrusan.selectedIndex = i;
                                               break;
                                       }
                               }
                //day
                var hari = noPengenalanWaris.substr(4,1);
                var day ="";
                if(hari == "0"){

                    day=noPengenalanWaris.substr(5,1);
                }
                else{
                    day=noPengenalanWaris.substr(4,2);
                    if(day>31){
                        alert("Sila masukkan hari yang betul.");
                    }

                }

                var selectKodUrusan = document.getElementById('hari');
                               selectKodUrusan.selectedIndex = 0;
                               for (var i = 0; i < selectKodUrusan.options.length; ++i){
                                       if (selectKodUrusan.options[i].value == day){
                                               selectKodUrusan.selectedIndex = i;
                                               break;
                                       }
                               }
            }else{
                alert("Sila masukkan 12 digit nombor pengenalan");
            }
                   }

        
</script>
<c:if test="${actionBean.beanFlag=='kemasukan_aduan'}">
    <c:set var="beanC" value="etanah.view.penguatkuasaan.KemasukanAduanActionBean"/>
</c:if>
<c:if test="${actionBean.beanFlag=='senarai_aduan'}">
    <c:set var="beanC" value="etanah.view.penguatkuasaan.SenaraiAduanActionBean"/>
</c:if>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<s:form  beanclass="${beanC}">
    <s:hidden name="idPermohonan" value="actionBean.idPermohonan" />
    <s:hidden name="idOrangKenaSyak" value="actionBean.idOrangKenaSyak" />
    <s:hidden name="id" value="${actionBean.id}"/>
    <s:messages/>
    <div class="instr" align="center">
        <s:errors/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend> Maklumat Orang Disyaki </legend>
            <div class="instr-fieldset">
                <font color="red">PERINGATAN:</font>Sila Masukkan Maklumat Berikut.
            </div>&nbsp;

            <p>
                <label><em>*</em>Nama :</label>
                <s:text name="nama" id="nama" size="30" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
            </p>

            <p>
                <label>Jenis Pengenalan :</label>
                <s:select name="kodJenisPengenalan.kod"  style="width:139px;" id="pengenalan" onchange="jenisPengenalan()">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" sort="nama" />
                </s:select>
                &nbsp;
            </p>
            <p id="noPengenalanLain">
                <label>No.Pengenalan :</label>
                <s:text name="noPengenalanLain" id="noPengenalanLain" maxlength="20" onkeyup="this.value=this.value.toUpperCase();" />
                &nbsp;
            </p>
            <p id="noPengenalanBaru" style="visibility: hidden; display: none">
                <label>No.Pengenalan :</label>
                <s:text name="noPengenalanBaru" id="noPengenalanOksBaru" maxlength="12" onkeyup="validateNumber(this,this.value);" onblur="javascript:findDOB(this.value)"/>
                <font color="red" size="1">cth : 850510075342 </font>
                &nbsp;
                <s:hidden name="hari" id="hari"/>
                <s:hidden name="bulan" id="bulan"/>
                <s:hidden name="tahun" id="tahun"/>
            </p>
            <p>
                <label>Tarikh Lahir:</label>
                <select name="hariReadonly" id="hariReadonly">
                    <option value=""> Hari </option>
                    <c:forEach var="hari" begin="1" end="31">
                        <option value="${hari}"> ${hari} </option>
                    </c:forEach>
                </select>
                <select name="bulanReadonly" id="bulanReadonly">
                    <option value=""> Bulan </option>
                    <c:forEach var="bulan" begin="01" end="12">
                        <option value="${bulan}">
                            <c:choose>
                                <c:when test="${bulan eq 1}">Januari</c:when>
                                <c:when test="${bulan eq 2}">Februari</c:when>
                                <c:when test="${bulan eq 3}">Mac</c:when>
                                <c:when test="${bulan eq 4}">April</c:when>
                                <c:when test="${bulan eq 5}">Mei</c:when>
                                <c:when test="${bulan eq 6}">Jun</c:when>
                                <c:when test="${bulan eq 7}">Julai</c:when>
                                <c:when test="${bulan eq 8}">Ogos</c:when>
                                <c:when test="${bulan eq 9}">September</c:when>
                                <c:when test="${bulan eq 10}">Oktober</c:when>
                                <c:when test="${bulan eq 11}">November</c:when>
                                <c:otherwise>Disember</c:otherwise>
                            </c:choose>
                        </option>
                    </c:forEach>
                </select>
                <select name="tahunReadonly" id="tahunReadonly">
                    <option value=""> Tahun </option>
                    <c:forEach var="tahun" begin="1900" end="2100">
                        <option value="${tahun}"> ${tahun} </option>
                    </c:forEach>
                </select>
            </p>

            <p>
                <label>Jantina :</label>
                <s:select name="kodJantina.kod"  style="width:139px;">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJantina}" label="nama" value="kod" sort="nama" />
                </s:select>
                &nbsp;
            </p>

            <p>
                <label>Pekerjaan :</label>
                <s:text name="pekerjaanOKS" id="pekerjaanOKS" size="30" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
            </p>
            <p>
                <label>Alamat :</label>
                <s:text name="alamat1" size="30" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="alamat2" size="30" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
            </p>
            <p>
                <label> &nbsp; </label>
                <s:text name="alamat3" size="30" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
            </p>
            <p>
                <label> &nbsp; </label>
                <s:text name="alamat4" size="30" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name="poskod" id="poskod" size="19" maxlength="5" onkeyup="validateNumber(this,this.value);" onchange="validatePoskodLength(this.value);"/>&nbsp;
            </p>
            <p>
                <label>Negeri :</label>
                <s:select name="negeriO" >
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>&nbsp;
            </p>
            <p>
                <label>Warna Kad Pengenalan :</label>
                <s:select name="kodWarnaKP.kod"  style="width:139px;">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodWarnaKp}" label="nama" value="kod" sort="nama" />
                </s:select>
                &nbsp;
            </p>
            <p>
                <label>Bangsa :</label>
                <s:select name="kodBangsaOKS"  style="width:139px;">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${actionBean.senaraiBangsa}" label="perihal" value="kod" sort="perihal" />
                </s:select>
                &nbsp;
            </p>
            <p>
                <label>Jenis Warganegara :</label>
                <s:select name="kodWarganegara.kod"  style="width:139px;">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiWarganegara}" label="nama" value="kod" sort="nama" />
                </s:select>
                &nbsp;
            </p>
            <p>
                <label>No Telefon :</label>
                <s:text name="noTelefon1" id="telefon" size="19" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);"/>&nbsp;
            </p>

            <p>
                <label>Lokasi Ditahan :</label>
                <s:text name="tempatOksDitahan" id="tempatOksDitahan" size="30" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
            </p>
            <p>
                <label>Tempat Direman :</label>
                <s:text name="tempatDireman" id="tempatDireman" size="30" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
            </p>
            <p>
                <label>Tarikh Mula Ditahan:</label>
                <s:text name="tarikhDitahan"  id="tarikhDitahan" class="datepicker" formatPattern="dd/MM/yyyy" />&nbsp;
                <font color="red" size="1">cth : hh / bb / tttt</font>
            </p>
            <p>
                <label>Tarikh Dilepaskan:</label>
                <s:text name="tarikhDiBebas" id="tarikhDiBebas" class="datepicker" formatPattern="dd/MM/yyyy" onchange="calcDays();"/>&nbsp;
                <font color="red" size="1">cth : hh / bb / tttt</font>
            </p>
            <p>
                <label>Tempoh Tahanan :</label>
                <s:text name="diBebas" id="diBebas" maxlength="12" readonly="true"/> hari&nbsp;
            </p>
            <p>
                <label>Disenarai Hitam</label>
                <s:radio name="senaraiHitam" id="dsh" value="B"/>&nbsp;Disenarai Hitam
                <s:radio name="senaraiHitam" id="tdsh" value="T"/>&nbsp; Tidak Disenarai Hitam

            </p>
            <p>
                <label>Tarikh Disenarai Hitam:</label>
                <s:text name="tarikhSenaraiHitam"  class="datepicker" formatPattern="dd/MM/yyyy" id="datepicker"/>&nbsp;
                <font color="red" size="1">cth : hh / bb / tttt</font>
            </p>
            <p>
                <label>Keterangan :</label>
                <s:textarea name="keterangan" rows="5" cols="50" onkeydown="limitText(this,3999);" onkeyup="limitText(this,3999);" onchange="this.value=this.value.toUpperCase();" />&nbsp;
            </p>
        </fieldset >

        <br>
        <fieldset class="aras1">
            <legend>Maklumat Majikan Orang Disyaki</legend>
            <div class="instr-fieldset">
                Sila Masukkan Maklumat Majikan Orang Disyaki.
            </div>&nbsp;
            <p>

                <label>Nama Majikan:</label>
                <s:text name="namaMajikan" id="namaMajikan" size="30" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
            </p>

            <p>
                <label>No Telefon Majikan:</label>
                <s:text name="noTelMajikan" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);"/>&nbsp;
            </p>

            <p>
                <label>No Faks Majikan:</label>
                <s:text name="noFaksMajikan" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);"/>&nbsp;
            </p>

            <p>
                <label>Alamat Majikan:</label>
                <s:text name="alamat1Majikan" size="30" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
            </p>

            <p>
                <label>&nbsp;</label>
                <s:text name="alamat2Majikan" size="30" onchange="this.value=this.value.toUpperCase();"/>&nbsp;</p>
            <p>
                <label> &nbsp; </label>
                <s:text name="alamat3Majikan" size="30" onchange="this.value=this.value.toUpperCase();"/>&nbsp;</p>
            <p>
                <label> &nbsp; </label>
                <s:text name="alamat4Majikan" size="30" onchange="this.value=this.value.toUpperCase();"/>&nbsp; </p>

            <p>
                <label>Poskod :</label>
                <s:text name="poskodMajikan" id="poskodMajikan" size="19" maxlength="5" onkeyup="validateNumber(this,this.value);" onchange="validatePoskodLength(this.value);"/>&nbsp;
            </p>

            <p>
                <label>Negeri :</label>
                <s:select name="kodNegeriMajikan.kod">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>&nbsp;
            </p>

        </fieldset>

        <br>

        <fieldset class="aras1">
            <legend>Maklumat Waris Orang Disyaki</legend>
            <div class="instr-fieldset">
                Sila Masukkan Maklumat Waris Orang Disyaki.
            </div>&nbsp;
            <p>

                <label>Nama Waris:</label>
                <s:text name="namaWaris" id="namaWaris" size="30" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
            </p>

            <p>

                <label>Hubungan:</label>
                <s:text name="hubungan" id="hubungan" size="30" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
            </p>

            <p>
                <label>Alamat Waris:</label>
                <s:text name="alamat1Waris" size="30" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
            </p>

            <p>
                <label>&nbsp;</label>
                <s:text name="alamat2Waris" size="30" onchange="this.value=this.value.toUpperCase();"/>&nbsp;</p>
            <p>
                <label> &nbsp; </label>
                <s:text name="alamat3Waris" size="30" onchange="this.value=this.value.toUpperCase();"/>&nbsp;</p>
            <p>
                <label> &nbsp; </label>
                <s:text name="alamat4Waris" size="30" onchange="this.value=this.value.toUpperCase();"/>&nbsp; </p>

            <p>
                <label>Poskod :</label>
                <s:text name="poskodWaris" id="poskodWaris" size="19" maxlength="5" onkeyup="validateNumber(this,this.value);" onchange="validatePoskodLength(this.value);"/>&nbsp;
            </p>
            <p>
                <label>Negeri :</label>
                <s:select name="kodNegeriWaris.kod">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>&nbsp;
            </p>

            <p>
                <label>Jenis Pengenalan Waris:</label>
                <s:select name="jenisPengenalanWaris.kod"  style="width:139px;" id="pengenalanWaris" onchange="jenisPengenalanWaris()">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" sort="nama" />
                </s:select>
                &nbsp;
            </p>

             <p id="noPengenalanLainWaris">
                <label>No.Pengenalan :</label>
                <s:text name="noPengenalanWaris" id="penyerahNoPengenalanLainWaris" maxlength="12" onkeyup="this.value=this.value.toUpperCase();"/>
                <%--<font color="red" size="1">cth : 850510075342 </font>--%>
                &nbsp;
            </p>

            <p id="noPengenalanBaruWaris" style="visibility: hidden; display: none">
                <label>No.Pengenalan :</label>
                <s:text name="noPengenalanWaris" id="penyerahNoPengenalanBaruWaris" maxlength="12" onkeyup="validateNumber(this,this.value);" onblur="javascript:findDOBW(this.value)"/>
                <font color="red" size="1">cth : 850510071213 </font>
                &nbsp;
            </p>

            <%-- <p>
                 <label>Jenis Bangsa Waris:</label>
                 <s:select name="kodBangsaWaris.kod"  style="width:139px;">
                     <s:option value="">Sila Pilih</s:option>
                     <s:options-collection collection="${listUtil.senaraiKodBangsa}" label="nama" value="kod" sort="nama" />
                 </s:select>
                 &nbsp;
             </p>--%>

            <p>
                <label>Jantina :</label>
                <s:select name="kodJantinaWaris.kod"  style="width:139px;">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJantina}" label="nama" value="kod" sort="nama" />
                </s:select>
                &nbsp;
            </p>

            <%-- <p>
                 <label>Jenis Warganegara :</label>
                 <s:select name="kodWarganegaraWaris.kod"  style="width:139px;">
                     <s:option value="">Sila Pilih</s:option>
                     <s:options-collection collection="${listUtil.senaraiWarganegara}" label="nama" value="kod" sort="nama" />
                 </s:select>
                 &nbsp;
             </p>--%>

            <p>
                <label>No Telefon :</label>
                <s:text name="noTelWaris" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);"/>&nbsp;
            </p>

            <p>
                <label>No Telefon Bimbit:</label>
                <s:text name="noTelBimbitWaris" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);"/>&nbsp;
            </p>



        </fieldset>

        <p align="right">
            <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
            <s:button class="btn" value="Simpan" name="editOKS"  onclick="if(validateForm())save(this.name,this.form);" />
            <s:submit class="btn"  value="Tutup" name="setAduan" onclick=" self.opener.refreshPage();self.close();"/>
        </p>
        <br>
    </div>
</s:form>

