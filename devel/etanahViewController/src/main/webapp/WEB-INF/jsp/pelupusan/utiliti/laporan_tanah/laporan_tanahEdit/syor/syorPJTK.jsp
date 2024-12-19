<%-- 
    Document   : syorPBMT
    Created on : Mar 19, 2012, 11:14:35 AM
    Author     : Srinivas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SYOR PENOLONG PEGAWAI TANAH</title>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">
    var size = 0 ;
    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
        },'html');
    }

    $(document).ready(function() {
        //maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        
    <c:choose>
        <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL' }">
                $('#jikasokong').show();
                $('#tidaksokong').hide();
                $('#pbmtsyorlulus').hide();
        </c:when>
        <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'SU' }">            
                $('#pbmtsyorlulus').show();
                $('#tidaksokong').hide();
                $('#jikasokong').hide();
        </c:when>
        <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'ST' }">            
                $('#tidaksokong').show();
                $('#jikasokong').hide();
                $('#pbmtsyorlulus').hide();
        </c:when>
        <c:otherwise>
                $('#tidaksokong').hide();
                $('#jikasokong').hide();
                $('#pbmtsyorlulus').hide();
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'GRN'}">
                $('#jikaPajakan').hide(); 
        </c:when>
        <c:when test="${actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'GM'}">
                $('#jikaPajakan').hide(); 
        </c:when>            
        <c:otherwise>
                $('#jikaPajakan').show();
        </c:otherwise>
    </c:choose>
    <c:if test="${actionBean.hakmilikPermohonan.kategoriTanahBaru ne null}">
            filterKodGunaTanahKhas();
    </c:if>

        }); //END OF READY FUNCTION

        function refreshpage2(type){
            //        alert(type);
            var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?refreshpage&type='+type;
            window.open(url, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
        function showjikasokong(val) {
            $('#jikasokong').show();
            $('#tidaksokong').hide();
            $('#pbmtsyorlulus').hide();
            kiraCukaiKhas(this.form,$("#kodTanah").val());
            if(val == 'ST'){
                val = 'SL';
            }
            setKpsn2(val);
        }

        function doSomething(val){
          
            if(val=='GRN'|| val=='GM'){
                //alert(val);
                $('#jikaPajakan').hide();
            }else if (val=='PN' || val=='PM'){
                //alert(val);
                $('#jikaPajakan').show();
            }
            
        }
        function listkegunaantanah(){
            var katTanahPilihan = $('#_kodKatTanah').val();
            //alert(val);
            var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?carianIndependent&katTanahPilihan='+katTanahPilihan;
            //+'&mpb='+mpb+'&tempat='+tempat+'&Luas='+Luas+'&kULuas='+kULuas+'&kodlot='+kodlot+'&noLot='+noLot+'&Jarak='+Jarak+'&uJarak='+uJarak+'&jarakDari='+jarakDari+'&kodSeksyen='+kodSeksyen;
            $.ajax({
                url:url,
                success:function(data){
                    $('#page_div').html(data);
                }
            });
        }
 
        
        function showpbmtsyorlulus(val) {
            $('#pbmtsyorlulus').show();
            $('#jikasokong').hide();
            $('#tidaksokong').hide();
            setKpsn2(val);
        }

        function showtidaksokong(val) {
            $('#tidaksokong').show();
            $('#jikasokong').hide();
            $('#pbmtsyorlulus').hide();
            if(val == 'SL'){
                val = 'ST';
            }
            setKpsn2(val);
        }

        function addRow(index,f)
        {
            NoPrompt();
            var kodksn= document.getElementById("ksn").value;
            var kpsn = '';
            var ks = document.form.ksn;
            for(var i=0; i< ks.length; i++){
                if(ks[i].checked){
                    kpsn = ks[i].value;
                 
                }
            }
            
            if(kpsn != null){
                $('#syorKpsn').val(kpsn);
                $('#ksn').val(kpsn);
            }
            var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?tambahRow&index='+index+'&kodksn='+kodksn+'&ksn='+kpsn;
            window.document.forms[0].action = url;
            window.document.forms[0].submit();

        }
        function deleteRow(idKandungan,f,tName)
        {
            NoPrompt();
            var kpsn = '';
            var ks = document.form.ksn;
            for(var i=0; i< ks.length; i++){
                if(ks[i].checked){
                    kpsn = ks[i].value;
                }
            }
            if(kpsn != null){
                $('#syorKpsn').val(kpsn);
                $('#ksn').val(kpsn);
            }
            if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
                var q = $(f).formSerialize();
                $.post('${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?deleteRow&idKandungan='+idKandungan+'&tName='+tName+'&typeName=PPT&ksn='+kpsn,q,
                function(data){
                    $('#page_div').html(data);

                }, 'html');
                self.refreshpage2('syorPPT') ;
            }
        }

        function refreshpage(){
            //        alert('aa');
            NoPrompt();
            opener.refreshV2('main');
            self.close();
        }

        function CurrencyFormatted(amount)
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
            return s;
        }
        function calculateBayarKupon(){

            var kuponQty = document.getElementById('kuponQty').value;

            var kuponAmaun = document.getElementById('kuponAmaun').value;
            var jumlah = (kuponAmaun * kuponQty);
            document.getElementById('kupon').value = CurrencyFormatted(jumlah);
            calculateSyarat();
        }
        function calculateSyarat(){
            var kuantiti = document.getElementById('kuantitiSyarat').value;
            //alert(kuantiti);
            var bayaran = document.getElementById('bayaranSyarat').value;
            //alert(bayaranSyarat);
            var jumlah = kuantiti * bayaran;
            //alert(jumlah);
            var cagaran = 20/100 * jumlah;
            var cagarJalan = document.getElementById('cagarJalan').value;

            var kuponQty = document.getElementById('kuponQty').value;

            var kuponAmaun = document.getElementById('kuponAmaun').value;
            var jumlahKpnQty = (kuponAmaun * kuponQty);
            cagarJalan = cagarJalan*1;
            var totalAll = (jumlah) + (cagaran) + (jumlahKpnQty) + (cagarJalan);

            document.getElementById('jumlahSyarat').value = CurrencyFormatted(jumlah);
            document.getElementById('cagaranSyarat').value = CurrencyFormatted(cagaran);
            document.getElementById('kuantitiJumlahSyarat').value = kuantiti;
            document.getElementById('cagarJalan').value = CurrencyFormatted(cagarJalan);
            document.getElementById('totalAll').value = CurrencyFormatted(totalAll);
        }
        function setKandungan(i,idLaporUlas){
            var index = i;
            var kandungan = $('#kandungan5'+index).val();
            $('#'+idLaporUlas+'kandunganUlas').val(kandungan);
        }
        function setKpsn(){
            NoPrompt();
            var kpsn = '';
            var ks = document.form.ksn;
            for(var i=0; i< ks.length; i++){
                if(ks[i].checked){
                    kpsn = ks[i].value;
                }
            }
            if(kpsn != null)
                $('#syorKpsn').val(kpsn);
            $('#ksn').val(kpsn);

        }
        function setKpsn2(val){
            $('#ksn').val(val);
            $('#syorKpsn').val(val);
        }
        function cariPopup(val){
            NoPrompt();
            window.open("${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?searchSyarat&jenisSyarat="+val, "eTanah",
            "status=1,toolbar=0,location=1,menubar=0,width=910,height=800");
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
        
        function filterKodGunaTanah() {
            var katTanah = $("#_kodKatTanah").val();
            //        alert("hi");
            NoPrompt();
        
            $.post('${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?senaraiKodGunaTanahByKatTanah&kodKatTanah=' + katTanah,
            function(data) {
                if (data != '') {
                    $('#partialKodGunaTanah').html(data);
                    //                    $.unblockUI();
                }
            }, 'html');

        }
        
        function filterKodGunaTanahKhas() {
            var katTanah = $("#_kodKatTanah").val();
            //        alert("hi");
            NoPrompt();
        
            $.post('${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?senaraiKodGunaTanahByKatTanah&kodKatTanah=' + katTanah,
            function(data) {
                if (data != '') {
                    $('#partialKodGunaTanah').html(data);
//                    alert(data);
                    //                    $.unblockUI();
                }
            }, 'html');

        }
      
        function kiraCukai(f) {

            var kodTanah = $("#kodTanah").val();
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?kiraCukai&kodTanah=' + kodTanah , q,
            function(data) {
                if (data == '-1') {
                } else {
                    $('#cukai').val("RM "+CurrencyFormatted(data)+ " sehektar");
                }
            }, 'html');
        }
        
        function kiraCukaiKhas(f,kod) {

            var kodTanah = kod ;
//            alert(kodTanah);
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?kiraCukai&kodTanah=' + kodTanah , q,
            function(data) {
                if (data == '-1') {
                } else {
                    $('#cukai').val("RM "+CurrencyFormatted(data)+ " sehektar");
                }
            }, 'html');
        }
</script>
<body>
    <script type="text/javascript">
        // Allow the user to be warned by default.
        var allowPrompt = true;

        window.onbeforeunload = WarnUser;
        function WarnUser()
        {
            if(allowPrompt)
                refreshpage();
            if(allowPrompt)
            {
                event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
            }
            else
            {
                // Reset the flag to its default value.
                allowPrompt = true;
            }
        }
        function NoPrompt()
        {
            allowPrompt = false;
        }

    </script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pelupusan.utility.UtilitiLaporanTanahActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <s:hidden name="noPtSementara" id="noPtSementara"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <div id="perihaltanah">
                    <legend>
                        <c:if test="${actionBean.kodNegeri eq '04'}">
                            <c:choose>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                                    Syor Penolong Pegawai Tanah
                                </c:when>
                                <c:otherwise>
                                    Syor Penolong Pegawai Tanah
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                        <c:if test="${actionBean.kodNegeri eq '05'}">
                            <c:choose>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                                    Ulasan Penolong Pegawai Tanah
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS'}">
                                    Syor Penolong Pegawai Tanah
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                                    Syor Penolong Pegawai Tanah
                                </c:when>
                                <c:otherwise>
                                    Syor Penolong Pegawai Tanah
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </legend>
                </div>
                <c:choose>
                    <c:when test="${actionBean.kodNegeri eq '04'}">
                        <table class="tablecloth" align="center">
                            <tr>
                                <td>
                                    <font color="red" size="4">*</font>Syor:
                                </td>
                                <td>
                                    <s:radio name="ksn" id="ksn" value="SL" onclick="showjikasokong(this.value);" />&nbsp;Sokong Permohonan
                                    <s:radio name="ksn" id="ksn" value="ST" onclick="showtidaksokong(this.value);" />&nbsp;Tidak Sokong
                                    <s:hidden name="syorKpsn" id="syorKpsn"/>
                                </td>
                            </tr>
                        </table>
                        <br/>
                        <div id="jikasokong">
                            <table class="tablecloth" align="center">
                                <tr>
                                    <td><font color="red" size="4">*</font>Luas Disyorkan :</td>
                                    <td>
                                        <s:text name="hakmilikPermohonan.luasDiluluskan" size="20" formatPattern="#,###,##0.0000" onkeyup="validateNumber(this,this.value);"/>&nbsp;
                                        <s:select name="kodU" style="width:150px" value="" id="kodU">
                                            <s:option value="0">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodUOMByLuasLupus}" label="nama" value="kod"/>
                                        </s:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td><font color="red" size="4">*</font>Kategori</td>
                                    <td> 
                                        <s:select name="kategoriTanahBaru.kod" id="_kodKatTanah" value="${actionBean.hakmilikPermohonan.kategoriTanahBaru.kod}" onchange="filterKodGunaTanah();"> 
                                            <s:option value="">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" />
                                        </s:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td><font color="red" size="4">*</font>Kegunaan Tanah</td>
                                    <td id="partialKodGunaTanah"></td>
                                </tr>
                                <tr>
                                    <td><font color="red" size="4">*</font>Jenis Hakmilik :</td>
                                    <td>
                                        <s:select name="kodHmlk" id="kodHmlk" onchange="doSomething(this.value);" >
                                            <s:option value="0">--Sila Pilih--</s:option>
                                            <s:option value="GRN">Geran (Pendaftar)</s:option>
                                            <s:option value="PN">Pajakan Negeri (Pendaftar)</s:option> 
                                            <s:option value="GM">Geran Mukim (Pejabat Tanah)</s:option>
                                            <s:option value="PM">Pajakan Mukim (Pejabat Tanah)</s:option>
                                        </s:select>
                                    </td>
                                </tr>
                                <tr id="jikaPajakan">
                                    <td><font color="red" size="4">*</font>Tempoh Pajakan:</td>
                                    <td>
                                        <s:select name="hakmilikPermohonan.tempohPajakan" id="tempohPajakan">
                                            <s:option value="0">--Sila Pilih--</s:option>
                                            <s:option value="30"> 30 </s:option>
                                            <s:option value="60"> 60 </s:option>
                                            <s:option value="99"> 99 </s:option>
                                        </s:select>
                                    </td>
                                </tr>                              
                                <tr>
                                    <td><font color="red" size="4">*</font>Premium : </td>
                                    <td>
                                        <s:select name="keteranganKadarPremium" id="nama" onchange="javaScript:showPremimTxt(this.value)">
                                            <s:option value="">--Sila Pilih--</s:option>
                                            <s:options-collection collection="${actionBean.senaraikodKadarPremium}" />
                                        </s:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td><font color="red" size="4">*</font>Hasil (RM) :</td>
                                    <td>
                                        <s:text name="hakmilikPermohonan.keteranganCukaiBaru" size="10"/>
                                        <s:select name="jenishasil" id="jenishasil">
                                            <s:option value="0">--Sila Pilih--</s:option>
                                            <s:option value="1"> Bagi setiap 100 meter persegi (Bangunan) </s:option>
                                            <s:option value="2"> Kurang 5 Hektar (Pertanian) </s:option>
                                            <s:option value="3"> Lebih 5 Hektar (Pertanian) </s:option>
                                        </s:select> 
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <font color="red" size="4">*</font>Upah Ukur :
                                    </td>
                                    <td>
                                        <s:radio name="hakmilikPermohonan.agensiUpahUkur" value="JUP" />&nbsp;Mengikut PU(A)438
                                        <s:radio name="hakmilikPermohonan.agensiUpahUkur" value="JUB" />&nbsp;Juru Ukur Berlesen
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align:center;" colspan="3">      
                                        <s:submit name="simpanKandungan" value="Simpan" class="btn" />
                                    </td>
                                </tr>  
                            </table>       
                            <br/>
                            <legend>Syarat</legend>
                            <table class="tablecloth" align="center">
                                <tr>
                                    <td>
                                        <font color="red" size="4">*</font>Syarat Nyata :
                                    </td>
                                    <td>
                                        <s:textarea name="hakmilikPermohonan.syaratNyataBaru.syarat" id="syaratNyata" rows="5" cols="80" readonly="yes" class="tooltips" value="${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}"></s:textarea>
                                        <s:hidden name="disSyaratSekatan.kod" id="kod"/>                                    
                                    </td>
                                    <td style="vertical-align: middle;">
                                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup('nyata')"/>  
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <font color="red" size="4">*</font>Sekatan Kepentingan :
                                    </td>
                                    <td>
                                        <s:textarea name="hakmilikPermohonan.sekatanKepentinganBaru.sekatan" id="syaratNyata1" rows="5" cols="80" readonly="yes" class="tooltips" value="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}"></s:textarea>
                                        <s:hidden name="disSyaratSekatan.kodSktn" id="kodSktn"/>

                                    </td>
                                    <td style="vertical-align: middle;">
                                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup('sekatan')"/>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div id="pbmtsyorlulus">
                            <table class="tablecloth" align="center">
                                <tr>
                                    <td>
                                        <font color="red" size="4">*</font>Kegunaan :
                                    </td>
                                    <td>
                                        <s:select name="keg" style="width:300px" id="keg">
                                            <s:option value="0">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodItemPermit}" label="nama" value="kod"/>
                                        </s:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <font color="red" size="4">*</font>Bayaran (RM) :
                                    </td>
                                    <td>
                                        <s:text name="amnt" size="20" onkeyup="validateNumber(this,this.value);"/>&nbsp;Setahun
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <font color="red" size="4">*</font>Luas :
                                    </td>
                                    <td>
                                        <s:text name="hakmilikPermohonan.luasTerlibat" formatPattern="#,###,##0.0000" size="20" onkeyup="validateNumber(this,this.value);"/>&nbsp;
                                        <s:select name="kodSU" style="width:150px" value="" id="koduom">
                                            <s:option value="0">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodUOMByLuasLupus}" label="nama" value="kod"/>
                                        </s:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <font color="red" size="4">*</font>Syarat Tambahan :
                                    </td>
                                    <td>
                                        <s:textarea name="ulsn" rows="5" cols="80" />
                                    </td>
                                </tr>
                            </table>

                        </div>
                        <div id="tidaksokong">
                            <table class="tablecloth" align="center">
                                <tr>
                                    <td><font color="red" size="4">*</font>Sebab :</td>
                                    <td><s:textarea name="ulasan" rows="5" cols="80" id="ulasan" value="${actionBean.fasaPermohonan.ulasan}" /></td>
                                </tr>
                            </table>
                        </div>
                    </c:when>
                    <c:when test="${actionBean.kodNegeri eq '05'}">
                        <table class="tablecloth" align="center">
                            <tr>
                                <td>
                                    <font color="red" size="4">*</font>Syor:
                                </td>
                                <td>
                                    <s:radio name="ksn" id="ksn" value="SL" onclick="showjikasokong(this.value);" />&nbsp;Sokong Permohonan
                                    <s:radio name="ksn" id="ksn" value="ST" onclick="showtidaksokong(this.value);" />&nbsp;Tidak Sokong
                                    <s:hidden name="syorKpsn" id="syorKpsn"/>
                                </td>
                            </tr>
                        </table>
                        <br/>
                        <div id="jikasokong">
                            <table class="tablecloth" align="center">
                                <tr>
                                    <td><font color="red" size="4">*</font>Luas Disyorkan :</td>
                                    <td>
                                        <s:text name="hakmilikPermohonan.luasDiluluskan" size="20" formatPattern="#,###,##0.0000" onkeyup="validateNumber(this,this.value);"/>&nbsp;
                                        <s:select name="kodU" style="width:150px" value="" id="kodU">
                                            <s:option value="0">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodUOMByLuasLupus}" label="nama" value="kod"/>
                                        </s:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td><font color="red" size="4">*</font>Kategori</td>
                                    <td> 
                                        <s:select name="kategoriTanahBaru.kod" id="_kodKatTanah" value="${actionBean.hakmilikPermohonan.kategoriTanahBaru.kod}" onchange="filterKodGunaTanah();"> 
                                            <s:option value="">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" />
                                        </s:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td><font color="red" size="4">*</font>Kegunaan Tanah</td>
                                    <td id="partialKodGunaTanah"></td>
                                </tr>
                                <tr>
                                    <td><font color="red" size="4">*</font>Jenis Hakmilik :</td>
                                    <td>
                                        <s:select name="kodHmlk" id="kodHmlk" onchange="doSomething(this.value);" >
                                            <s:option value="0">--Sila Pilih--</s:option>
                                            <s:option value="GRN">Geran (Pendaftar)</s:option>
                                            <s:option value="PN">Pajakan Negeri (Pendaftar)</s:option> 
                                            <s:option value="GM">Geran Mukim (Pejabat Tanah)</s:option>
                                            <s:option value="PM">Pajakan Mukim (Pejabat Tanah)</s:option>
                                        </s:select>
                                    </td>
                                </tr>
                                <tr id="jikaPajakan">
                                    <td><font color="red" size="4">*</font>Tempoh Pajakan:</td>
                                    <td>
                                        <s:text name="tempohPajakan" id="tempohPajakan" maxlength="2" onkeyup="validateNumber(this,this.value);" />
                                    </td>
                                </tr>
                                <tr>
                                    <td><font color="red" size="1">* </font>Premium : </td>
                                    <td>
                                        <s:text name="keteranganKadarPremium" id="keteranganKadarPremium" size="20" onkeyup="validateNumber(this,this.value);" />
                                    </td>
                                </tr>
                                <%--<tr id="jikaPajakan">
                                        <td><font color="red" size="4">*</font>Tempoh Pajakan:</td>
                                        <td>
                                            <s:select name="hakmilikPermohonan.tempohPajakan" id="tempohPajakan">
                                                <s:option value="0">--Sila Pilih--</s:option>
                                                <s:option value="30"> 30 </s:option>
                                                <s:option value="60"> 60 </s:option>
                                                <s:option value="99"> 99 </s:option>
                                            </s:select>
                                        </td>
                                </tr> --%>
                                <%-- <tr>
                                     <td><font color="red" size="4">*</font>Premium : </td>
                                     <td>
                                         <s:select name="keteranganKadarPremium" id="nama" onchange="javaScript:showPremimTxt(this.value)">
                                             <s:option value="">--Sila Pilih--</s:option>
                                             <s:options-collection collection="${actionBean.senaraikodKadarPremium}" />
                                         </s:select>
                                     </td>
                                 </tr>--%>
                                <tr>
                                    <td><font color="red" size="4">*</font>Hasil (RM) :</td>
                                    <td>
                                        <s:text name="hakmilikPermohonan.keteranganCukaiBaru" id="cukai" size="20" readonly="true"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <font color="red" size="4">*</font>Upah Ukur :
                                    </td>
                                    <td>
                                        <s:radio name="hakmilikPermohonan.agensiUpahUkur" value="JUP" />&nbsp;Mengikut PU(A)438
                                        <s:radio name="hakmilikPermohonan.agensiUpahUkur" value="JUB" />&nbsp;Juru Ukur Berlesen
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align:center;" colspan="3">      
                                        <s:submit name="simpanKandungan" value="Simpan" class="btn" onclick="setKpsn();"/>
                                    </td>
                                </tr>  
                            </table>       
                            <br/>
                            <legend>Syarat</legend>
                            <table class="tablecloth" align="center">
                                <tr>
                                    <td>
                                        <font color="red" size="4">*</font>Syarat Nyata :
                                    </td>
                                    <td>
                                        <s:textarea name="hakmilikPermohonan.syaratNyataBaru.syarat" id="syaratNyata" rows="5" cols="80" readonly="yes" class="tooltips" value="${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}"></s:textarea>
                                        <s:hidden name="disSyaratSekatan.kod" id="kod"/>                                    
                                    </td>
                                    <td style="vertical-align: middle;">
                                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup('nyata')"/>  
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <font color="red" size="4">*</font>Sekatan Kepentingan :
                                    </td>
                                    <td>
                                        <s:textarea name="hakmilikPermohonan.sekatanKepentinganBaru.sekatan" id="syaratNyata1" rows="5" cols="80" readonly="yes" class="tooltips" value="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}"></s:textarea>
                                        <s:hidden name="disSyaratSekatan.kodSktn" id="kodSktn"/>

                                    </td>
                                    <td style="vertical-align: middle;">
                                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup('sekatan')"/>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div id="pbmtsyorlulus">
                            <table class="tablecloth" align="center">
                                <tr>
                                    <td>
                                        <font color="red" size="4">*</font>Kegunaan :
                                    </td>
                                    <td>
                                        <s:select name="keg" style="width:300px" id="keg">
                                            <s:option value="0">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodItemPermit}" label="nama" value="kod"/>
                                        </s:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <font color="red" size="4">*</font>Bayaran (RM) :
                                    </td>
                                    <td>
                                        <s:text name="amnt" size="20" onkeyup="validateNumber(this,this.value);"/>&nbsp;Setahun
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <font color="red" size="4">*</font>Luas :
                                    </td>
                                    <td>
                                        <s:text name="hakmilikPermohonan.luasTerlibat" formatPattern="#,###,##0.0000" size="20" onkeyup="validateNumber(this,this.value);"/>&nbsp;
                                        <s:select name="kodSU" style="width:150px" value="" id="koduom">
                                            <s:option value="0">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodUOMByLuasLupus}" label="nama" value="kod"/>
                                        </s:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <font color="red" size="4">*</font>Syarat Tambahan :
                                    </td>
                                    <td>
                                        <s:textarea name="ulsn" rows="5" cols="80" />
                                    </td>
                                </tr>
                            </table>

                        </div>
                        <div id="tidaksokong">
                            <table class="tablecloth" align="center">
                                <tr>
                                    <td><font color="red" size="4">*</font>Sebab :</td>
                                    <td><s:textarea name="ulasan" rows="5" cols="80" id="ulasan" value="${actionBean.fasaPermohonan.ulasan}" /></td>
                                </tr>
                            </table>
                        </div>
                    </c:when>
                </c:choose>
                <legend>Ulasan</legend>
                <table class="tablecloth" border="0" align="center">
                    <c:set var="i" value="1" />
                    <c:set var="num" value="1"/>
                    <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line">
                        <tr>
                            <td style="text-align: right"><c:out value="${num})"/></td>
                            <td>
                                <s:textarea  id="kandungan5${i}" name="senaraiLaporanKandungan1[${i-1}].ulasan" cols="80"  rows="5" onblur="setKandungan(${i},${line.idLaporUlas})" class="normal_text"/>
                                <s:hidden id="${line.idLaporUlas}kandunganUlas" name="${line.idLaporUlas}kandunganUlas"/>
                                <s:hidden name="${line.idLaporUlas}"/>
                            </td>
                            <td style="vertical-align: middle;">
                                <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idLaporUlas},this.form,'mohonLaporUlas')"></s:button> 
                                </td>
                            </tr>
                        <c:set var="i" value="${i+1}" />
                        <c:set var="num" value="${num+1}"/>
                    </c:forEach>
                    <tr>
                        <td style="text-align:center;" colspan="3">      
                            <s:button value="Tambah Ulasan" class="btn" name="simpan1"  onclick="addRow('5',this.form)"/>
                            <s:submit name="simpanKandungan" value="Kemaskini" class="btn" onclick="setKpsn();"/>
                            <s:hidden name="index" id="index" value="5"/>
                            <%--<s:button name="simpanSyor" value="Simpan" class="btn" onclick="menyimpan('5',${i-1},this.form)"/>--%>
                            <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                        </td>
                    </tr>                    
                </table>
            </fieldset>
        </div>
    </s:form>
</body>