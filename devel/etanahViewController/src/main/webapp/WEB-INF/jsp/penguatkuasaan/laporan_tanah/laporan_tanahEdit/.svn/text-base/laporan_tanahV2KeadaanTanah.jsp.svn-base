<%--
    Document   :  laporan_tanahV2KeadaanTanah.jsp
    Created on :  March 02, 2012, 10:16:13 AM
    Author     :  Shazwan
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PERIHAL TANAH</title>
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
         
        //            maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    <c:if test="${actionBean.laporanTanah.kecerunanTanah ne null}">
        <c:if test="${actionBean.laporanTanah.kecerunanTanah.kod eq 'TG'}">
                $('#tinggi').show();
                $('#cerun').hide();
                $('#dalam').hide();
        </c:if>
        <c:if test="${actionBean.laporanTanah.kecerunanTanah.kod eq 'RD'}">
                $('#tinggi').hide();
                $('#cerun').show();
                $('#dalam').hide();
        </c:if>
        <c:if test="${actionBean.laporanTanah.kecerunanTanah.kod eq 'BK'}">
                $('#tinggi').hide();
                $('#cerun').show();
                $('#dalam').hide();
        </c:if>
        <c:if test="${actionBean.laporanTanah.kecerunanTanah.kod eq 'CR'}">
                $('#tinggi').hide();
                $('#cerun').show();
                $('#dalam').hide();
        </c:if>
        <c:if test="${actionBean.laporanTanah.kecerunanTanah.kod eq 'PY'}">
                $('#tinggi').hide();
                $('#cerun').hide();
                $('#dalam').show();
        </c:if>
        <c:if test="${actionBean.laporanTanah.kecerunanTanah.kod ne 'TG' and actionBean.laporanTanah.kecerunanTanah.kod ne 'CR' and actionBean.laporanTanah.kecerunanTanah.kod ne 'PY' and actionBean.laporanTanah.kecerunanTanah.kod ne 'BK' and actionBean.laporanTanah.kecerunanTanah.kod ne 'RD'}">
                $('#tinggi').hide();
                $('#cerun').hide();
                $('#dalam').hide();
        </c:if>    
    </c:if>
    <c:if test="${actionBean.laporanTanah.kecerunanTanah eq null}">
            $('#tinggi').hide();
            $('#cerun').hide();
            $('#dalam').hide();
    </c:if>
    <c:if test="${actionBean.laporanTanah.kodKeadaanTanah ne null}">
        <c:if test="${actionBean.laporanTanah.kodKeadaanTanah.kod eq 'LL'}">
                $('#keadaantanahlainlain').show();
        </c:if>
        <c:if test="${actionBean.laporanTanah.kodKeadaanTanah.kod ne 'LL'}">
                $('#keadaantanahlainlain').hide();
        </c:if>
    </c:if>
    <c:if test="${actionBean.laporanTanah.kodKeadaanTanah eq null}">
            $('#keadaantanahlainlain').hide();
    </c:if>
    <c:if test="${actionBean.laporanTanah.usaha eq null or actionBean.laporanTanah.usaha eq 'T'}">
            $('#nilaianTanah').hide();
            $('#anggaranPokok ').hide();
            $('#luasTanam').hide();
            $('#jenisTanaman').hide();
            $('#tarikh').hide();
            $('#pengusuka').hide();
            $('#tempoh').hide();
    </c:if> 
    <c:if test="${actionBean.laporanTanah.adaBangunan ne null or actionBean.laporanTanah.adaBangunan eq 'Y'}">
            $('#bangunan ').show();
    </c:if>    
    <c:if test="${actionBean.laporanTanah.adaBangunan eq null or actionBean.laporanTanah.adaBangunan eq 'T'}">
            $('#bangunan ').hide();
    </c:if>
    <c:if test="${actionBean.permohonanLaporanUsaha.diUsahaOleh eq 'OL'}">
            $('#diUsahaOleh').show();
    </c:if>    
    <c:if test="${actionBean.permohonanLaporanUsaha.diUsahaOleh eq null}">
            $('#diUsahaOleh').hide();
    </c:if>
    <c:if test="${actionBean.laporanTanah.perenggan ne null or actionBean.laporanTanah.perenggan eq 'Y'}">
            $('#luasperenggan').show();
    </c:if>
    <c:if test="${actionBean.laporanTanah.perenggan eq null or actionBean.laporanTanah.perenggan eq 'T'}">
            $('#luasperenggan').hide();
    </c:if>
        
        }); //END OF READY FUNCTION
         
        function changeKeadaanTanah(text){
            /*
             * LEGEND KOD
             * RATA = RT
             * BERBUKIT = BK
             * TINGGI = TG
             * RENDAH = RD
             * CURAM = CR
             * BERPAYA = PY
             * SEPARAS DENGAN JALAN = SJ
             * TINGGI DARI ARAS JALAN = TJ
             * RENDAH DARI ARAS JALAN = RJ
             */
            if(text == "RT"){
                $('#tinggi').hide();
                $('#cerun').hide();
                $('#dalam').hide();
            }

            else if(text == "BK"){
                $('#tinggi').hide();
                $('#cerun').show();
                $('#dalam').hide();
            }

            else if(text == "TG"){
                $('#tinggi').show();
                $('#cerun').hide();
                $('#dalam').hide();
            }

            else if(text == "RD"){
                $('#tinggi').hide();
                $('#cerun').show();
                $('#dalam').hide();
            }

            else if(text == "CR"){
                $('#tinggi').hide();
                $('#cerun').show();
                $('#dalam').hide();
            }

            else if(text == "PY"){
                $('#tinggi').hide();
                $('#cerun').hide();
                $('#dalam').show();
            }
        
            else{
                $('#tinggi').hide();
                $('#cerun').hide();
                $('#dalam').hide();
            }
        }
    
        function validateNumber(elmnt,content) {
            //if it is character, then remove it..
            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }
    
        function showjenistanahlain(value){
            // alert(value);
            if(value == '99'){
                $('#jenistanahlainlain').show();
            }else{
                $('#jenistanahlainlain').hide();
            }
        }
    
        function showkeadaantanahlain(value){
            //alert(value);
            if(value == 'LL'){
                $('#keadaantanahlainlain').show();
            }else{
                $('#keadaantanahlainlain').hide();
            }
        }
    
        function showHidePengusaha(val){
            if(val=='show'){
                $('#pengusuka').show();
                $('#tarikh').show();
                $('#tempoh').show();
                $('#nilaianTanah').show();
                $('#anggaranPokok').show();
                $('#luasTanam').show();
                $('#jenisTanaman').show();
            }
            else if(val =='hide'){
                $('#pengusuka').hide();
                $('#tarikh').hide();
                $('#Bangunantable').hide();
                $('#tempoh').hide();
                $('#nilaianTanah').hide();
                $('#anggaranPokok').hide();
                $('#luasTanam').hide();
                $('#jenisTanaman').hide();
            }
            
        }
        
        function showHideLuasPerenggan(val){
            if(val == 'show'){
                $('#luasperenggan').show();
            }else if(val == 'hide'){
                $('#luasperenggan').hide();
            }
        }
        
        function showHideBilBangunan(val){
            if(val == 'show')
                $('#bangunan').show();            
            else if(val =='hide')
                $('#bangunan').hide();
        }
        function tambahBangunan(kategori){
            NoPrompt();
            window.open("${pageContext.request.contextPath}/penguatkuasaan/laporan_tanahV2?tambahBangunanPopup&kategori="+kategori+"&idLaporTanah="+${actionBean.laporanTanah.idLaporan}, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }
        
        function tambahLaporUsaha(kategori){
            NoPrompt();
            window.open("${pageContext.request.contextPath}/penguatkuasaan/laporan_tanahV2?tambahNamaPengusahaPopup&kategori="+kategori+"&idLaporTanah="+${actionBean.laporanTanah.idLaporan}, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }
        function deleteLaporUsaha(idLaporanUsaha){
            NoPrompt();
            window.open("${pageContext.request.contextPath}/penguatkuasaan/laporan_tanahV2?deleteNamaPengusaha&idLaporTanah="+${actionBean.laporanTanah.idLaporan}+"&idLaporanUsaha="+idLaporanUsaha, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }
        function showOrgLain(checkboxVal){
            if(checkboxVal.checked)
                $('#diUsahaOleh').show();
            else
                $('#diUsahaOleh').hide();
        }
        
        function refreshpage(){
            //            alert('aa');
            NoPrompt();
    <c:choose>
        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
                var idHakmilik = $('#idHakmilik').val();
                opener.refreshV2ManyHakmilik('main',idHakmilik);
        </c:when>
        <c:otherwise>
                opener.refreshV2('main');
        </c:otherwise>
    </c:choose>
            self.close();
        }
        
        function editLaporanBangunan(idLaporBangunan,kategori){
            NoPrompt();
            var idHakmilik = $('#idHakmilik').val();
            window.open("${pageContext.request.contextPath}/penguatkuasaan/laporan_tanahV2?editBangunanPopup&kategori="+kategori+"&idHakmilik="+idHakmilik+"&idLaporBangunan="+idLaporBangunan+"&idLaporTanah="+${actionBean.laporanTanah.idLaporan}, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }
</script>
<body>
    <script>
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
    <s:form beanclass="etanah.view.penguatkuasaan.LaporanTanahV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <s:hidden name="idLapor" id="idLapor" value="${actionBean.laporanTanah.idLaporan}"/>
        <s:hidden name="jenisLaporan" id="jenisLaporan"/>
        <div class="subtitle" id="main">
            <fieldset class="aras1">

                <div id="perihaltanah">
                    <legend>Keadaan Tanah</legend>
                    <div class="content" align="center">
                        <table class="tablecloth" border="0">
                            <c:if test="${actionBean.kodNegeri eq '05'}">
                                <tr>
                                    <td>
                                        Jarak :
                                    </td>
                                    <td>
                                        <s:text name="hakmilikPermohonan.jarak" maxlength="6" onkeyup="validateNumber(this,this.value);" id="Jarak"/>
                                        <s:select name="unitJarak.kod" value="${actionBean.hakmilikPermohonan.unitJarak.kod}" id="unitJarak">
                                            <s:option value="">Sila Pilih</s:option>
                                            <%--<s:option value="JK">Kaki</s:option>--%>
                                            <s:option value="KM">Kilometer</s:option>
                                            <s:option value="JM">Meter</s:option>
                                        </s:select>
                                        &nbsp;<font color="#003194"><b>dari</b></font>&nbsp;
                                        <s:select name="hakmilikPermohonan.jarakDari" id="jarakDari">
                                            <s:option value="">Sila Pilih</s:option>
                                            <s:option value="Jalan Raya">Jalan Raya</s:option>
                                            <s:option value="Landasan Keretapi">Landasan Keretapi</s:option>
                                            <s:option value="Pantai">Pantai</s:option>
                                            <s:option value="Pekan">Pekan</s:option>
                                            <s:option value="Sungai">Sungai</s:option>
                                        </s:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Jarak Dari Kediaman :
                                    </td>
                                    <td>
                                        <s:text name="hakmilikPermohonan.jarakDariKediaman" maxlength="6" onkeyup="validateNumber(this,this.value);" id="JarakKediaman"/>
                                        <s:select name="jarakDariKediamanUom.kod" value="${actionBean.hakmilikPermohonan.jarakDariKediamanUom.kod}" id="jarakDariKediamanUom">
                                            <s:option value="">Sila Pilih</s:option>
                                            <%--<s:option value="JK">Kaki</s:option>--%>
                                            <s:option value="KM">Kilometer</s:option>
                                            <s:option value="JM">Meter</s:option>
                                        </s:select>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${actionBean.kodNegeri eq '04'}">
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR'}">
                                    <tr>
                                        <td>
                                            <font color="red" size="4">*</font>Jarak :
                                        </td>
                                        <td>
                                            <s:text name="hakmilikPermohonan.jarak" maxlength="6" onkeyup="validateNumber(this,this.value);" id="Jarak"/>
                                            <s:select name="unitJarak.kod" value="${actionBean.hakmilikPermohonan.unitJarak.kod}" id="uJarak">
                                                <s:option value="">Sila Pilih</s:option>
                                                <%--<s:option value="JK">Kaki</s:option>--%>
                                                <s:option value="KM">Kilometer</s:option>
                                                <s:option value="JM">Meter</s:option>
                                            </s:select>
                                            &nbsp;<font color="#003194"><b>dari</b></font>&nbsp;
                                            <s:select name="hakmilikPermohonan.jarakDari" id="jarakDari" onchange="keluarNama();">
                                                <s:option value="">Sila Pilih</s:option>
                                                <s:option value="Jalan Raya">Jalan Raya</s:option>
                                                <s:option value="Landasan Keretapi">Landasan Keretapi</s:option>
                                                <s:option value="Pantai">Pantai</s:option>
                                                <s:option value="Pekan">Pekan</s:option>
                                                <s:option value="Sungai">Sungai</s:option>
                                            </s:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Nama Pantai/Pekan/Sungai :
                                        </td>
                                        <td>
                                            <s:text name="hakmilikPermohonan.jarakDariNama" id="namaJ" size="40"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <font color="red" size="4">*</font>Jarak Dari Kediaman :
                                        </td>
                                        <td>
                                            <s:text name="hakmilikPermohonan.jarakDariKediaman" maxlength="6" onkeyup="validateNumber(this,this.value);" id="JarakKediaman"/>
                                            <s:select name="jarakDariKediamanUom.kod" value="${actionBean.hakmilikPermohonan.jarakDariKediamanUom.kod}" id="uJarakKUOM">
                                                <s:option value="">Sila Pilih</s:option>
                                                <%--<s:option value="JK">Kaki</s:option>--%>
                                                <s:option value="KM">Kilometer</s:option>
                                                <s:option value="JM">Meter</s:option>
                                            </s:select>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:if>
                            <tr>
                                <c:choose>
                                    <c:when test = "${actionBean.permohonan.kodUrusan.kod ne '425'}">
                                        <td>
                                            <font color="red" size="4">*</font>Kecerunan Tanah :
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>
                                            Kecerunan Tanah :
                                        </td>
                                    </c:otherwise>    
                                </c:choose>

                                <td>
                                    <s:select name="kecerunanT" id="kecerunanT" onchange="javaScript:changeKeadaanTanah(this.options[selectedIndex].value)">
                                        <s:option value="">--Sila Pilih--</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodKecerunanTanah}" label="nama" value="kod" />
                                    </s:select>
                                </td>
                            </tr>
                            <tr id="tinggi">
                                <td>
                                    Ketinggian Dari Paras Jalan (m) :
                                </td>
                                <td>
                                    <s:text name="laporanTanah.ketinggianDariJalan" id="ketinggianDariJalan" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                                </td>
                            </tr>  
                            <tr id="cerun">
                                <td>
                                    Darjah Kecerunan :
                                </td>
                                <td>
                                    <s:text name="laporanTanah.kecerunanBukit" id="kecerunanBukit" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                                </td>
                            </tr>
                            <tr id="dalam">
                                <td>
                                    Dalam Paras Air (m) :
                                </td>
                                <td>
                                    <s:text name="laporanTanah.parasAir" id="parasAir" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                                </td>
                            </tr>
                            <tr>
                                <c:choose>
                                    <c:when test = "${actionBean.permohonan.kodUrusan.kod ne '425'}">
                                        <td>
                                            <font color="red" size="4">*</font>Klasifikasi Tanah :
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>
                                            Klasifikasi Tanah :
                                        </td>
                                    </c:otherwise>    
                                </c:choose>

                                <td>
                                    <s:select name="klasifikasiT" id="klasifikasiT" value="${actionBean.laporanTanah.kodKeadaanTanah.kod}">
                                        <s:option value="0">--Sila Pilih--</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodStrukturTanah}" label="nama" value="kod" />
                                    </s:select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Jika Lain-lain :
                                </td>
                                <td>
                                    <s:text name="laporanTanah.strukturTanahLain" id="strukturTanahLain" />
                                </td>
                            </tr>
                            <tr>
                                <c:choose>
                                    <c:when test = "${actionBean.permohonan.kodUrusan.kod ne '425'}">
                                        <td>
                                            <font color="red" size="4">*</font>Keadaan Tanah :
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>
                                            Keadaan Tanah :
                                        </td>
                                    </c:otherwise>    
                                </c:choose>

                                <td>
                                    <s:select name="keadaanTanah" id="keadaanTanah" onchange="javaScript:showkeadaantanahlain(this.value)">
                                        <s:option value="0">--Sila Pilih--</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodKeadaanTanah}" label="nama" value="kod" />
                                    </s:select>
                                </td>
                            </tr>
                            <tr id="keadaantanahlainlain">
                                <td>
                                    Lain-lain :
                                </td>
                                <td>
                                    <s:textarea name="keadaankand" rows="5" cols="50"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Tanah Dipohon Bertumpu :
                                </td>
                                <td>
                                    <s:select name="laporanTanah.tanahBertumpu" id="tanahBertumpu" >
                                        <s:option value="0">--Sila Pilih--</s:option>
                                        <s:option value="Y">Ya</s:option>
                                        <s:option value="T">Tidak</s:option>
                                    </s:select>
                                    &nbsp; Pada :     
                                    <s:select name="laporanTanah.keteranganTanahBertumpu" id="keteranganTanahBertumpu" >
                                        <s:option value="0">--Sila Pilih--</s:option>
                                        <s:option value="Jalanraya">Jalanraya</s:option>
                                        <s:option value="Jalan Keretapi">Jalan Keretapi</s:option>
                                        <s:option value="Sungai">Sungai</s:option>
                                        <s:option value="Pantai">Pantai</s:option>
                                        <s:option value="Tiada">Tiada</s:option>
                                        <%--<s:options-collection collection="${listUtil.senaraiKodKecerunanTanah}" label="nama" value="kod" />--%>
                                    </s:select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    &nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <b>Dilintasi Oleh </b> :
                                    * Sila tandakan di atas pelan
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right">
                                    Talian Elektrik / Laluan Elektrik Bawah Tanah :
                                </td>
                                <td>
                                    <s:checkbox name="laporanTanah.dilintasTiangElektrik" value="Y" id="dilintasTiangElektrik"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right"> 
                                    Tiang Telefon / Lalualn Telefon Bawah Tanah :
                                </td>
                                <td>
                                    <s:checkbox name="laporanTanah.dilintasTiangTelefon" value="Y" id="dilintasTiangTelefon"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right">
                                    Laluan Gas :
                                </td>
                                <td>
                                    <s:checkbox name="laporanTanah.dilintasLaluanGas" value="Y" id="dilintasLaluanGas"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right">
                                    Paip Air :
                                </td>
                                <td>
                                    <s:checkbox name="laporanTanah.dilintasPaip" value="Y" id="dilintasPaip"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right">
                                    Tali Air :
                                </td>
                                <td>
                                    <s:checkbox name="laporanTanah.dilintasTaliar" value="Y" id="dilintasTaliar"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right">
                                    Sungai :
                                </td>
                                <td>
                                    <s:checkbox name="laporanTanah.dilintasSungai" value="Y" id="dilintasSungai"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right">
                                    Parit :
                                </td>
                                <td>
                                    <s:checkbox name="laporanTanah.dilintasParit" value="Y" id="dilintasParit"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    &nbsp;
                                </td>
                            </tr>
                            <tr>
                                <c:choose>
                                    <c:when test = "${actionBean.permohonan.kodUrusan.kod ne '425'}">
                                        <td>
                                            <font color="red" size="4">*</font>Tanah Diusahakan :
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>
                                            Tanah Diusahakan :
                                        </td>
                                    </c:otherwise>    
                                </c:choose>

                                <td>
                                    <s:radio name="laporanTanah.usaha" value="Y" id="usaha" onclick="showHidePengusaha('show');"/>&nbsp;Ya
                                    <s:radio name="laporanTanah.usaha" value="T"  id="usaha" onclick="showHidePengusaha('hide');"/>&nbsp;Tidak
                                </td>
                            </tr>
                            <tr id="pengusuka">
                                <td>
                                    Oleh :
                                </td>
                                <td>
                                    <s:checkbox name="laporanTanah.diusaha" value="P" id="diusaha" /> &nbsp; Pemohon
                                    <s:checkbox name="permohonanLaporanUsaha.diUsahaOleh" value="OL" id="oleh" onclick="showOrgLain(this)" /> &nbsp; Orang Lain
                                </td>
                            </tr>
                            <tr id="diUsahaOleh">
                                <td colspan="2">
                                    <display:table class="tablecloth" name="${actionBean.listPermohonanLaporanUsaha}" pagesize="5" cellpadding="0" cellspacing="0"
                                                   requestURI="/pelupusan/laporan_tanahV2"  id="line">
                                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                        <display:column title="Pengusaha Tanah Selain Pemohon">
                                            ${line.diUsaha}
                                        </display:column>
                                        <display:column title="Padam">
                                            <a onclick="deleteLaporUsaha('${line.idLaporanUsaha}')" onmouseover="this.style.cursor='pointer';" ><img alt="Hapus" height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/not_ok.gif'/></a> |
                                        </display:column>
                                    </display:table>
                                    <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahLaporUsaha('OL');"/>
                                </td>
                            </tr>
                            <tr id="tarikh">
                                <td>
                                    Tarikh Mula Usaha :
                                </td>
                                <td>
                                    <s:text name="laporanTanah.tarikhMulaUsaha2" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy"/>
                                </td>
                            </tr>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                                <tr id="tempoh">
                                    <td>
                                        Tempoh Usaha Tanah :
                                    </td>
                                    <td>
                                        <s:text name="laporanTanah.tarikhMulaUsaha" id="tempohUsaha" size="10"/>
                                        &nbsp; Tahun
                                    </td>
                                </tr>       
                            </c:if>

                            <tr id="jenisTanaman">
                                <td>
                                    Jenis Tanaman
                                </td>
                                <td>
                                    <s:textarea name="laporanTanah.usahaTanam" id="usahaTanam" cols="80" rows="5" />
                                </td>
                            </tr>
                            <tr id="luasTanam">
                                <td>
                                    Luas Yang Ditanam (hektar)
                                </td>
                                <td>
                                    <s:text name="usahaLuas" id="usahaLuas" size="35" onkeyup="validateNumber(this,this.value);"/>
                                </td>
                            </tr>
                            <tr id="anggaranPokok">
                                <td>
                                    anggaran Bil.pokok
                                </td>
                                <td>
                                    <s:text name="usahaBilanganPokok" id="usahaBilanganPokok" onkeyup="validateNumber(this,this.value);"/>
                                </td>
                            </tr>
                            <tr id="nilaianTanah">
                                <td>
                                    Nilaian Tanah Termasuk Tanaman(RM)
                                </td>
                                <td>
                                    <s:text name="usahaHarga" id="usahaHarga" size="35" onkeyup="validateNumber(this,this.value);"/>
                                </td>
                            </tr>
                            <tr>
                                <c:choose>
                                    <c:when test = "${actionBean.permohonan.kodUrusan.kod ne '425'}">
                                        <td>
                                            <font color="red" size="4">*</font>Bangunan :
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>
                                            Bangunan :
                                        </td>
                                    </c:otherwise>    
                                </c:choose>

                                <td>
                                    <s:radio name="laporanTanah.adaBangunan" value="Y" onclick="showHideBilBangunan('show');"/>&nbsp;Ada
                                    <s:radio name="laporanTanah.adaBangunan" value="T" onclick="showHideBilBangunan('hide');"/>&nbsp;Tiada
                                </td>
                            </tr>
                            <tr>
                                <td colspan ="2">&nbsp;
                                </td>
                            </tr>
                            <tr id="bangunan">
                                <td colspan="2">
                                    <display:table class="tablecloth" name="${actionBean.permohonanLaporanBangunanList}" pagesize="5" cellpadding="0" cellspacing="0"
                                                   requestURI="/pelupusan/laporan_tanahV2"  id="line">
                                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                        <display:column title="Jenis Bangunan">
                                            <c:if test="${line.jenisBangunan eq 'KK'}">
                                                kekal
                                            </c:if>
                                            <c:if test="${line.jenisBangunan eq 'SK'}">
                                                separuh kekal
                                            </c:if>
                                            <c:if test="${line.jenisBangunan eq 'SM'}">
                                                sementara
                                            </c:if>
                                            <c:if test="${line.jenisBangunan eq 'LL'}">
                                                lain-lain
                                            </c:if>
                                        </display:column>
                                        <display:column title="Keterangan Jenis Bangunan" >
                                            <c:choose>
                                                <c:when test="${line.jenisBangunan eq 'LL'}">
                                                    ${line.keteranganJenisBngn}
                                                </c:when>     
                                                <c:otherwise>
                                                    -
                                                </c:otherwise>
                                            </c:choose> 
                                        </display:column>        
                                        <display:column property="ukuran" title="Ukuran Bangunan" />
                                        <display:column property="uomUkuran.nama" title="Unit Ukuran" />
                                        <display:column property="tahunDibina" title="Tahun Dibina" />
                                        <display:column property="keadaanBangunan" title="Keadaan Bangunan" />
                                        <display:column property="nilai" title="Nilai Bangunan" />
                                        <display:column property="namaPemunya" title="Pemilik" />
                                        <display:column property="namaKetua" title="Ketua Keluarga Yang Mendiami"/>
                                        <display:column title="Status">
                                            <c:if test="${line.jenisPendudukan.kod eq 'TT'}">
                                                Pemilik
                                            </c:if>
                                            <c:if test="${line.jenisPendudukan.kod eq 'TS'}">
                                                Pemilik dan Penyewa Bangunan
                                            </c:if>
                                            <c:if test="${line.jenisPendudukan.kod eq 'SS'}">
                                                Penyewa Tanah dan Bangunan
                                            </c:if>
                                        </display:column>
                                        <display:column title="Kemaskini">
                                            <div align="center">
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editLaporanBangunan('${line.idLaporBangunan}','B');"/>
                                            </div>  <%--alert('edit'+${line.idLaporBangunan});--%>
                                        </display:column>
                                    </display:table><s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBangunan('B');"/>
                                </td>
                            </tr>
                            <tr>
                                <c:choose>
                                    <c:when test = "${actionBean.permohonan.kodUrusan.kod ne '425'}">
                                        <td>
                                            <font color="red" size="4">*</font>Tanah sudah Diperenggan :
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>
                                            Tanah sudah Diperenggan :
                                        </td>
                                    </c:otherwise>    
                                </c:choose>

                                <td>
                                    <s:radio name="laporanTanah.perenggan" value="Y" onclick="showHideLuasPerenggan('show');" />&nbsp;Sudah
                                    <s:radio name="laporanTanah.perenggan" value="T" onclick="showHideLuasPerenggan('hide');"  />&nbsp;Belum
                                </td>
                            </tr>
                            <tr id="luasperenggan">
                                <td>
                                    Luas Diperenggan
                                </td>
                                <td>
                                    <s:text name="laporanTanah.perengganLuas" id="laporanTanah.perengganLuas"/>
                                    <s:select name="koduomluasperenggan" id="koduomluasperenggan">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodUOMByLuas}" label="nama" value="kod"/>
                                    </s:select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Rancangan Kerajaan Atas Tanah (Jika Ada) :
                                </td>
                                <td>
                                    <s:textarea name="laporanTanah.rancanganKerajaan" rows="5" cols="30" class="normal_text"/>
                                </td>
                            </tr>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PHLP'}">
                                <tr>
                                    <td>
                                        <font color="red" size="4">*</font>Nilai Anggaran : 
                                    </td>
                                    <td>
                                        RM <s:text name="laporanTanah.nilaiTanah" formatPattern="###,###,###.00"></s:text>
                                    </td>
                                </tr>
                            </c:if>
                        </table>        
                    </div>
                    <br/>
                </div>
            </fieldset>

            <fieldset class="aras1">
                <table  width="100%" border="0">
                    <tr>
                        <td align="center">
                            <s:submit name="simpanKeadaanTanah" value="Simpan" class="btn" onclick="NoPrompt();"/>
                            <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                        </td>
                    </tr>
                </table>   
            </fieldset>
        </div>
    </s:form>
</body>

