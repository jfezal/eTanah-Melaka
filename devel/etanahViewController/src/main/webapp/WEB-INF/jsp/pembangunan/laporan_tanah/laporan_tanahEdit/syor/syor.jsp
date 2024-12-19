<%--
    Document   :  syor.jsp
    Created on :  June 06, 2013, 04:53:00 PM
    Author     :  User
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SYOR PENOLONG PEGAWAI TANAH</title>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $('#perakuanSyor').hide();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    <c:if test="${actionBean.laporanTanah.syor eq 'Y'}">
        showUlasan("Y");
    </c:if>
    <c:if test="${actionBean.laporanTanah.syor eq 'T'}">
        showUlasan("T");
    </c:if>
    });

    function refreshpage() {
        NoPrompt();
        opener.refreshV2('main');
        self.close();
    }

    function showUlasan(value) {
        if (value == "Y")
        {
            $('#perakuanSyor').show();
            saveSyor('Y');
        }
        else if (value == "T")
        {
            $('#perakuanSyor').hide();
            saveSyor('T');
        }
    }

    function searchKodSyaratNyata(index) {
        window.open("${pageContext.request.contextPath}/pembangunan/carianSyaratSekatan?showFormCariKodSyaratNyata2&index='+index", "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=900px,height=700px,scrollbars=yes");

    }

    function addRow(index, f)
    {
        NoPrompt();
        var kodksn;
        var url = '${pageContext.request.contextPath}/pembangunan/laporanTanahv2?tambahRow&index=' + index + '&kodksn=' + kodksn;
        window.open(url, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }

    function setKandungan(i, idLaporUlas) {
        var index = i;
        var kandungan = $('#kandungan5' + index).val();
        $('#' + idLaporUlas + 'kandunganUlas').val(kandungan);
    }

    function deleteRow(idKandungan, f, tName)
    {
        NoPrompt();
        if (confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pembangunan/laporanTanahv2?deleteRow', {idKandungan: idKandungan, tName: tName, typeName: 'PPT'},
            function(data) {
                $('#page_div').html(data);
            }, 'html');
            self.refreshpage2('syorPPT');
        }
    }

    function saveSyor(kpsn)
    {
        //alert("Save Syor");
        NoPrompt();
        $.post('${pageContext.request.contextPath}/pembangunan/laporanTanahv2?saveSyor', {typeName: 'syorPPT', kpsn: kpsn},
        function(data) {
            $('#page_div').html(data);
        }, 'html');
    }

    function refreshpage2(type) {
        var url = '${pageContext.request.contextPath}/pembangunan/laporanTanahv2?refreshpage&type=' + type;
        window.open(url, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }

    function dipilih(value) {
        /*
         alert("V : "+value);
         alert("C : "+document.getElementById("syorPelanTanahPilihan1").checked);
         if(document.getElementById("syorPelanTanahPilihan1").checked == true){
         alert("true");
         $('#cantumBahagian').val("");
         $('#syorTukarKpd').val("");
         $('#syorTukarDari').val("");
         $('#syorserahbalikpertanian').val("");
         $('#syorserahbalikkediaman').val("");
         $('#syorserahbalikperniagaan').val("");
         $('#syorserahbalikindustri').val("");
         $('#syorserahbalikkemajuan').val("");
         }
         else if(value == '2'){
         
         $('#syornilaibahagi').val("");
         $('#syorlotpertanian').val("");
         $('#syorlotkediaman').val("");
         $('#syorlotperniagaan').val("");
         $('#syorlotindustri').val("");
         $('#syorlotlain').val("");
         $('#syorTukarKpd').val("");
         $('#syorTukarDari').val("");
         $('#syorserahbalikpertanian').val("");
         $('#syorserahbalikkediaman').val("");
         $('#syorserahbalikperniagaan').val("");
         $('#syorserahbalikindustri').val("");
         $('#syorserahbalikkemajuan').val("");
         } else if(value == '3'){
         
         $('#syornilaibahagi').val("");
         $('#syorlotpertanian').val("");
         $('#syorlotkediaman').val("");
         $('#syorlotperniagaan').val("");
         $('#syorlotindustri').val("");
         $('#syorlotlain').val("");
         $('#cantumBahagian').val("");
         $('#syorserahbalikpertanian').val("");
         $('#syorserahbalikkediaman').val("");
         $('#syorserahbalikperniagaan').val("");
         $('#syorserahbalikindustri').val("");
         $('#syorserahbalikkemajuan').val("");
         } else if(value == '4'){
         
         $('#syornilaibahagi').val("");
         $('#syorlotpertanian').val("");
         $('#syorlotkediaman').val("");
         $('#syorlotperniagaan').val("");
         $('#syorlotindustri').val("");
         $('#syorlotlain').val("");
         $('#cantumBahagian').val("");
         $('#syorTukarKpd').val("");
         $('#syorTukarDari').val("");
         }*/
    }
    function convert(val, id) {
        var amaun = CurrencyFormatted(val);
        amaun = CommaFormatted(amaun);
        $('#' + id).val(amaun);
    }

    function CurrencyFormatted(amount) {
        var q = amount.indexOf(",");

        if (q > 0) {
            amount = amount.replace(/,/g, "");
        }

        var i = parseFloat(amount);

        if (isNaN(i)) {
            i = 0.00;
        }
        var minus = '';
        if (i < 0) {
            minus = '-';
        }
        i = Math.abs(i);
        i = parseInt((i + .005) * 100);
        i = i / 100;
        s = new String(i);
        if (s.indexOf('.') < 0) {
            s += '.00';
        }
        if (s.indexOf('.') == (s.length - 2)) {
            s += '0';
        }
        s = minus + s;
        return s;
    }

    function CommaFormatted(amount) {
        var delimiter = ","; // replace comma if desired
        var a = amount.split('.', 2)
        var d = a[1];
        var i = parseInt(a[0]);
        if (isNaN(i)) {
            return '';
        }
        var minus = '';
        if (i < 0) {
            minus = '-';
        }
        i = Math.abs(i);
        var n = new String(i);
        var a = [];
        while (n.length > 3)
        {
            var nn = n.substr(n.length - 3);
            a.unshift(nn);
            n = n.substr(0, n.length - 3);
        }
        if (n.length > 0) {
            a.unshift(n);
        }
        n = a.join(delimiter);
        if (d.length < 1) {
            amount = n;
        }
        else {
            amount = n + '.' + d;
        }
        amount = minus + amount;
        return amount;
    }
    function kiraCukaiKelompok() {
        $("#kira").show();
        $("#cals").hide();
        var kodUOM = $("#kodluas").val();
        var luas = $("#luas").val();
        var idH = $("#idHakmilik").val();
        $.post('${pageContext.request.contextPath}/daftar/nota/nota_daftar?kiraCukaiKelompok&idHakmilik=' + idH + '&luas=' + luas + '&kodUOM=' + kodUOM,
                function(data) {
                    $('#cukai').val(convert(data, 'cukai'));
                    $("#kira").hide();
                    $("#cals").show();
                }, 'html');
    }

    function validateNumber(elmnt, content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric(strString)
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for (intIndex = 0; intIndex < strString.length; intIndex++)
        {
            strBuffer = strString.substr(intIndex, 1);
            // Is this a number
            if (strValidCharacters.indexOf(strBuffer) > -1)
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }

    function validate() {

        var syorYa = document.getElementById("syorYa").checked;
        var syorTidak = document.getElementById("syorTidak").checked;

        if (!syorYa && !syorTidak) {
            alert("Sila masukkan maklumat Jika diperakukan.");
            return false;
        }
        return true;

    }

</script>
<body>
    <script>
        // Allow the user to be warned by default.
        var allowPrompt = true;

        window.onbeforeunload = WarnUser;
        function WarnUser()
        {
            if (allowPrompt)
                refreshpage();
            if (allowPrompt)
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
    <s:form beanclass="etanah.view.stripes.pembangunan.LaporanTanahV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <s:hidden name="luas" id="luas"/>
        <s:hidden name="kodluas" id="kodluas"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <div id="perihaltanah">
                    <legend>
                        Syor Penolong Pegawai Tanah
                    </legend>
                </div>
                <p>
                    <s:radio name="laporanTanah.syorUlasanPilihan" id="ulasanPilihan1" value="1"/>Tukar Syarat / Pengubahan Jenis Penggunaan Sek 124 KTN<br>
                    <s:radio name="laporanTanah.syorUlasanPilihan" id="ulasanPilihan2" value="2"/>Letak Syarat / Meletak Jenis Penggunaan Sek 124 KTN<br>
                    <s:radio name="laporanTanah.syorUlasanPilihan" id="ulasanPilihan3" value="3"/>Pecah Sempadan Sek 135 KTN<br>
                    <s:radio name="laporanTanah.syorUlasanPilihan" id="ulasanPilihan4" value="4"/>Pecah Bahagian Sek 140 KTN<br>
                    <s:radio name="laporanTanah.syorUlasanPilihan" id="ulasanPilihan5" value="5"/>Penyatuan Tanah Sek 146 KTN<br>
                    <s:radio name="laporanTanah.syorUlasanPilihan" id="ulasanPilihan6" value="6"/>Mengubah Syarat dan Kategori Berkenaan dengan Bahagian Umpukan Sempadan Sek 124A KTN<br>
                    <s:radio name="laporanTanah.syorUlasanPilihan" id="ulasanPilihan7" value="7"/>Penyerahan Balik dan Pemberimilikan Semula Sek 204A KTN<br>
                    <s:radio name="laporanTanah.syorUlasanPilihan" id="ulasanPilihan8" value="8"/>Serahbalik Keseluruhan Menurut Sek 197 KTN. Kemudian Diberimilik Semula Menurut Sek 76 KTN<br>
                    <s:radio name="laporanTanah.syorUlasanPilihan" id="ulasanPilihan9" value="9"/>Serahbalik Keseluruhan (12A)<br>
                    <s:radio name="laporanTanah.syorUlasanPilihan" id="ulasanPilihan10" value="10"/>Sebahagian Tanah (12B)<br><br>

                    Pelan tanah ini seperti bertanda&nbsp;<s:text name="laporanTanah.syorUrusan" />&nbsp;di lampiran&nbsp;<s:text name="laporanTanah.syorLampiran" />&nbsp;mengikut pelan yang dikemukakan oleh pemohon.<br><br>
                </p>
                <table border="0" class="tablecloth">
                    <tr>
                        <td><s:checkbox name="laporanTanah.syorPelanTanahPilihan" value="1" id="syorPelanTanahPilihan1" onclick="dipilih(this.value)"/></td>
                        <td>hendak dibelah bagi kepada&nbsp;<s:text name="laporanTanah.syorNilaiBahagi" id="syornilaibahagi" />.&nbsp;Lot pertanian&nbsp;<s:text name="laporanTanah.syorLotPertanian" id="syorlotpertanian" />,&nbsp;lot kediaman&nbsp;<s:text name="laporanTanah.syorLotKediaman" id="syorlotkediaman" />, lot perniagaan&nbsp;<s:text name="laporanTanah.syorLotPerniagaan" id="syorlotperniagaan" />,&nbsp;lot perindustrian&nbsp;<s:text name="laporanTanah.syorLotIndustri" id="syorlotindustri" />&nbsp; dan lot lain-lain&nbsp;<s:text name="laporanTanah.syorLotLain" id="syorlotlain" />.</td>
                    </tr>
                    <tr><td colspan="2"></td></tr>
                    <tr>
                        <td><s:checkbox name="laporanTanah.syorPelanTanah1" id="syorPelanTanahPilihan2" value="2" onclick="dipilih(this.value)"/></td>
                        <td>hendak dicantum kepada&nbsp;<s:text name="laporanTanah.syorCantumBahagian" id="cantumBahagian" />&nbsp;bahagian.</td>
                    </tr>
                    <tr><td colspan="2"></td></tr>
                    <tr>
                        <td><s:checkbox name="laporanTanah.syorPelanTanah2" id="syorPelanTanahPilihan3" value="3" onclick="dipilih(this.value)"/></td>
                        <td>hendak ditukar syarat/pengubahan jenis penggunaan dari&nbsp;<s:text name="laporanTanah.syorTukarDari" id="syorTukarDari" />&nbsp;kepada&nbsp;<s:text name="laporanTanah.syorTukarKepada" id="syorTukarKpd"/>.</td>
                    </tr>
                    <tr><td colspan="2"></td></tr>
                    <tr>
                        <td><s:checkbox name="laporanTanah.syorPelanTanah3" id="syorPelanTanahPilihan4" value="4" onclick="dipilih(this.value)"/></td>
                        <td>hendak diserahbalik keseluruhan dan berimilik semula kepada&nbsp;<s:text name="laporanTanah.syorSerahLotPertanian" id="syorserahbalikpertanian" /> lot pertanian, <s:text name="laporanTanah.syorSerahLotKediaman" id="syorserahbalikkediaman" /> lot kediaman,<br>
                            <s:text name="laporanTanah.syorSerahLotPerniagaan" id="syorserahbalikperniagaan" /> lot perniagaan, <s:text name="laporanTanah.syorSerahLotIndustri" id="syorserahbalikindustri" /> lot industri dan <s:text name="laporanTanah.syorSerahLotKemajuan" id="syorserahbalikkemajuan" /> lot kemajuan akan datang.</td>
                    </tr>
                    <tr><td colspan="2"></td></tr>
                    <tr>
                        <td><s:checkbox name="laporanTanah.syorPelanTanah4" id="syorPelanTanahPilihan5" value="5" onclick="dipilih(this.value)"/></td>
                        <td>serahbalik sebahagian tanah kepada kerajaan seluas&nbsp;<s:text name="laporanTanah.syorSebahagianLuas" id="syorSebahagianLuas" />&nbsp;
                            <s:select  style="text-transform:uppercase" id="syorSebahagianOum" name="laporanTanah.syorSebahagianOum" onchange="kiraLuas(this.form);">
                                <s:option value="Hektar">Hektar</s:option>
                                <s:option value="Meter Persegi">Meter Persegi</s:option>
                                <s:option value="Kaki Persegi">Kaki Persegi</s:option>
                                <s:option value="Ekar">Ekar</s:option>
                            </s:select>
                            &nbsp;untuk tujuan&nbsp;<s:text name="laporanTanah.syorSebahagianTujuan" id="syorSebahagianTujuan" />.</td>
                    </tr>
                </table>
                <s:hidden name="laporanTanah.idLaporan" id="laporanTanah.idLaporan" />

                <c:if test = "${!(actionBean.permohonan.kodUrusan.kod eq 'SBMS' || actionBean.permohonan.kodUrusan.kod eq 'TSPSS')}">
                <p>
                    <label>Jika diperakukan :-</label>
                    <s:radio name="laporanTanah.syor" value="Y" onclick="javaScript:showUlasan(this.value)" id="syorYa"/>&nbsp;Ya
                    <s:radio name="laporanTanah.syor" value="T" onclick="javaScript:showUlasan(this.value)" id="syorTidak"/>&nbsp;Tidak
                </p>
                <div class="content" id="perakuanSyor">
                    <table border="0" cellspacing="2">
                        <c:if test="${actionBean.stageId eq 'g_laporan_tanah' || actionBean.stageId eq 'laporantanah'}">                            
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSPSS' || actionBean.permohonan.kodUrusan.kod eq 'SBMS'}">
                                <tr>
                                    <td><label>Premium Tambahan :</label></td>
                                    <td>RM
                                        <c:if test="${actionBean.hakmilikPermohonan.keteranganKadarPremium ne null}">
                                            <s:text name="hakmilikPermohonan.keteranganKadarPremium" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" disabled="${actionBean.disabled}"/>
                                        </c:if>
                                        <c:if test="${actionBean.hakmilikPermohonan.keteranganKadarPremium eq null}">
                                            <s:text name="hakmilikPermohonan.keteranganKadarPremium" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" disabled="${actionBean.disabled}"/>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:if>
                            <tr>
                                <td><label>Denda Premium (jika ada):</label></td>
                                <td>RM
                                    <c:if test="${actionBean.hakmilikPermohonan.dendaPremium ne null}">
                                        <s:text name="hakmilikPermohonan.dendaPremium" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" disabled="${actionBean.disabled}" />
                                    </c:if>
                                    <c:if test="${actionBean.hakmilikPermohonan.dendaPremium eq null}">
                                        <s:text name="hakmilikPermohonan.dendaPremium" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" disabled="${actionBean.disabled}" />
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td><label>Sewa Tahunan Baru :</label></td>
                                <td align="left">RM
                                    <c:if test="${actionBean.hakmilikPermohonan.keteranganCukaiBaru ne null}">
                                        <s:text name="hakmilikPermohonan.keteranganCukaiBaru" id="cukai" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" class="normal_text" disabled="${actionBean.disabled}"/>
                                    </c:if>
                                    <c:if test="${actionBean.hakmilikPermohonan.keteranganCukaiBaru eq null}">
                                        <s:text name="hakmilikPermohonan.keteranganCukaiBaru" id="cukai" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" class="normal_text" disabled="${actionBean.disabled}"/>
                                    </c:if>
                                    <!--
                                    <span id="kira" style="display:none">Jana Cukai...</span>
                                    <img alt="Mesin Kira" onmouseover="this.style.cursor = 'pointer';"  src='$//{pageContext.request.contextPath}/pub/images/icons/calc.png'  onclick="kiraCukaiKelompok();" id="cals"/>-->
                                </td>
                            </tr>
                            <%--
                            <tr>
                                <td><label>Luas (jika ada perubahan) :</label></td>
                                <td align="left"><s:text name="hakmilikPermohonan.luasTerlibat" id="luasTerlibat" disabled="${actionBean.disabled}" class="normal_text"/>${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                                </td>
                            </tr>
                            --%>
                            <c:choose>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK'}">
                                    <tr>
                                        <td><label>Kategori :</label></td>
                                        <td>${actionBean.hakmilikPermohonan.kategoriTanahBaru.nama}</td>
                                    </tr> 
                                    <tr>
                                        <td><label>Syarat Nyata : </label></td>
                                        <td><c:if test="${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat ne null}">${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}</c:if>
                                            <c:if test="${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat eq null}"> Tiada Data </c:if>
                                            </td>
                                        <s:hidden name="syaratBaru1" id="kod1" value="${actionBean.hakmilikPermohonan.syaratNyataBaru.kod}" />
                                    </tr>
                                    <tr>
                                        <td valign="top"><label>Sekatan Milik : </label></td>
                                        <td><c:if test="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan ne null}">${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}</c:if>
                                            <c:if test="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan eq null}"> Tiada Data </c:if>
                                            </td>
                                        <s:hidden name="sekatanBaru1" id="kod1" value="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.kod}" />
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td><label>Kategori :</label></td>
                                        <td>${actionBean.hakmilik.kategoriTanah.nama}</td>
                                    </tr> 
                                    <tr>
                                        <td><label>Syarat Nyata : </label></td>
                                        <td><c:if test="${actionBean.hakmilik.syaratNyata.syarat ne null}">${actionBean.hakmilik.syaratNyata.syarat}</c:if>
                                            <c:if test="${actionBean.hakmilik.syaratNyata.syarat eq null}"> Tiada Data </c:if>
                                            </td>
                                        <s:hidden name="syaratBaru1" id="kod1" value="${actionBean.hakmilik.syaratNyata.kod}" />
                                    </tr>
                                    <tr>
                                        <td valign="top"><label>Sekatan Milik : </label></td>
                                        <td><c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan ne null}">${actionBean.hakmilik.sekatanKepentingan.sekatan}</c:if>
                                            <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan eq null}"> Tiada Data </c:if>
                                            </td>
                                        <s:hidden name="sekatanBaru1" id="kod1" value="${actionBean.hakmilik.sekatanKepentingan.kod}" />
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                            <tr>
                                <td><label>Semua bayaran dijelaskan dalam tempoh:</label></td>
                                <c:choose>
                                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK'}">
                                        <td><b>3 bulan dari tarikh Borang 7G.</b></td>
                                    </c:when>
                                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'SBMS'}">
                                        <td><b>3 bulan dari tarikh Borang 5A.</b></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><b>3 bulan.</b></td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            <c:if test="${actionBean.textSyor}">
                                <tr>
                                    <td><label>&nbsp;</label></td>
                                    <td><b>Pemohon disyaratkan untuk membina rumah kos rendah kerana pembangunan melebihi 8 ekar.&nbsp;</b></td>
                                </tr>
                            </c:if>
                        </c:if>
                    </c:if>
                    </table>
                </div>
                <table class="tablecloth" border="0" align="center">
                    <c:set var="i" value="1" />
                    <c:set var="num" value="1"/>
                    <label>Ulasan : </label>
                    <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line">
                        <tr>
                            <td style="text-align: right"><c:out value="${num})"/></td>
                            <td>
                                <s:textarea  id="kandungan5${i}" name="senaraiLaporanKandungan1[${i-1}].ulasan" cols="80"  rows="5" onblur="setKandungan(${i},${line.idLaporUlas})" class="normal_text"></s:textarea>
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
                            <s:submit name="simpanKandungan" value="Simpan" class="btn" onclick="return validate();"/>
                            <s:hidden name="index" id="index" value="5"/>
                            <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </div>
    </s:form>
</body>


