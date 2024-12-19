<%-- 
    Document   : popup_maklumat_tanah
    Created on : July 4, 2013, 11:01:15 AM
    Author     : latifah.iskak
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        
        setValueHakmilik();

        if($('#jenisKemasukan').val() == 'TS'){
            var idHakmilikTanahSempadan = $('#idHakmilikTanahSempadan').val();
            if(idHakmilikTanahSempadan != ""){
                chooseOptionHakmilik('Y');
            }else{
                chooseOptionHakmilik('T');
            }
        }
      
        
        if($('#adaBangunan').val() == 'Y'){
            $('#bilanganBangunan').show();
            $('#bilanganBangunan2').show();
            $('#bilanganBangunan3').show();
            $('#bilanganBangunan4').show();
        }

        if($('#diUsahakan').val() == 'Y'){
            $('#usaha1').show();
            $('#usaha2').show();
            $('#usaha3').show();
        }
        
        var v = '${actionBean.laporanTanah.kecerunanTanah.kod}';

        if(v == 'RT'){
            $('#tinggi').hide();
            $('#cerun').hide();
            $('#dalam').hide();
        }

        else if(v == 'BK'){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }

        else if(v == 'TG'){
            $('#tinggi').show();
            $('#cerun').hide();
            $('#dalam').hide();
        }

        else if(v == 'RD'){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }

        else if(v == 'CR'){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }

        else if(v == 'PY'){
            $('#tinggi').hide();
            $('#cerun').hide();
            $('#dalam').show();
        }
        else {
            $('#tinggi').hide();
            $('#cerun').hide();
            $('#dalam').hide();
        }
    });


    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }
    
    function validateNumberLuas(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumericLuas(content);
            return;
        }
    }
    
    function removeNonNumericLuas( strString )
    {
        var strValidCharacters = "1234567890.";
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
    function test(f) {
        $(f).clearForm();
    }


    function simpan1(event, f){
        alert("Anda pasti untuk simpan?");
        var q = $(f).formSerialize();
     
        //        alert("lala");
        setTimeout(function () {
            var url = f.action + '?' + event;
            $.post(url,q,
            function(html){
                //            $("#maklumatLaporanTanahDiv",opener.document).replaceWith($('#maklumatLaporanTanahDiv', $(html)));
                $('#page_div',opener.document).html(html);
            },'html');
            
        }, 5000);
        
        setTimeout(function () {
            var id = $('#idLaporanTanah').val();
            self.opener.refreshWholePage(id);
        }, 5000);
        self.close();
       
        //                self.opener.refresh();
        //        self.close();
    }

    function limitText(limitField, limitNum) {
        if (limitField.value.length > limitNum) {
            limitField.value = limitField.value.substring(0, limitNum);
        }
    }
    
    function changeKeadaanTanah(text){

        if(text == "Rata"){
            $('#tinggi').hide();
            $('#cerun').hide();
            $('#dalam').hide();
        }

        else if(text == "Berbukit"){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }
        
        else if(text == "Tinggi"){
            $('#tinggi').show();
            $('#cerun').hide();
            $('#dalam').hide();
        }

        else if(text == "Rendah"){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }

        else if(text == "Curam"){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }

        else if(text == "Berpaya"){
            $('#tinggi').hide();
            $('#cerun').hide();
            $('#dalam').show();
        }

        else if(text == "Lain-lain"){
            $('#tinggi').hide();
            $('#cerun').hide();
            $('#dalam').hide();
        }

    }
    
    function showUsaha() {
        $('#usaha1').show();
        $('#usaha2').show();
        $('#usaha3').show();
    }

    function hideUsaha() {
        $('#usaha1').hide();
        $('#usaha2').hide();
        $('#usaha3').hide();
    <%--   $('#diusaha').val("");
       $('#datepicker').val("");
       $('#usahaTanam').val("");--%>
           }
           
           function showBilBangunan() {
               $('#bilanganBangunan').show();
               $('#bilanganBangunan2').show();
               $('#bilanganBangunan3').show();
               $('#bilanganBangunan4').show();
           }
           function hideBilBangunan() {
               $('#bilanganBangunan').hide();
               $('#bilanganBangunan2').hide();
               $('#bilanganBangunan3').hide();
               $('#bilanganBangunan4').hide();
               $('#bangunanTahunDibina').val("");
           }
           
           function returnCurrency(amount){
               document.getElementById('nilai').value = CurrencyFormatted(amount);
           }
           
           function carianHakmilik(idHakmilik,section){
               //               var idHakmilik= $("#hakmilikCarian").val();
               
               if(section == 'TH'){
                   idHakmilik = $("#hakmilikCarian").val();
               }else{
                   idHakmilik = $("#idHakmilikTanahSempadan").val();
               }
               //               alert(idHakmilik);
            
               if(idHakmilik != ""){
                   $.get('${pageContext.request.contextPath}/penguatkuasaan/mohon_lapor_tanah?findHakmilikLTNH&hakmilikCarian='+idHakmilik,
                   function(data){
                       //                       alert(data);
                       $("#hasilCarianHakmilikDiv").replaceWith($('#hasilCarianHakmilikDiv', $(data)));
                       $("#msgInfoDiv").replaceWith($('#msgInfoDiv', $(data)));
                       var status = $('#statusCarianHakmilik').val();
                       //                       alert(status);
                       if(status == "W"){
                           if(section == 'TH'){
                               $('#hasilCarianKategoriTanah').val($('#kodKategoriTanahCarianHakmilik').val());
                               $('#hasilCarianNoLot').val($('#noLotCarianHakmilik').val());
                               $('#hasilCarianKodLot').val($('#namaLotCarianHakmilik').val());
                               $('#hasilCarianLuas').val($('#luasCarianHakmilik').val());
                               $('#hasilCarianKodUnitLuas').val($('#namaUnitLuasCarianHakmilik').val());
                               $('#hasilCarianDaerah').val($('#daerahCarianHakmilik').val());
                               $('#hasilCarianBPM').val($('#bpmCarianHakmilik').val());
                               $('#hasilCarianAtasTanah').val($('#atasTanahCarianHakmilik').val());
                           }else{
                               //                               alert("TS");
                               $('#kodKategoriTanahSempadanHakmilik').val($('#kodKategoriTanahCarianHakmilik').val());
                               $('#noLotTanahSempadanHakmilik').val($('#noLotCarianHakmilik').val());
                               $('#kodLotTanahSempadanHakmilik').val($('#namaLotCarianHakmilik').val());
                           }
                          
                       }
                       //                if(status == "TW"){
                       //                    alert("Id Hakmilik tidak wujud. Sila masukkan Id Hakmilik yang betul.");
                       //                    return false;
                       //                }
                   }, 'html');

               }else{
                   alert("Sila masukkan id hakmilik");
                   if(section == 'TH'){
                       $("#hakmilikCarian").focus();
                   }else{
                       $("#idHakmilikTanahSempadan").focus();
                   }
               }

           }
           
           function NoPrompt()
           {
               
               //               if($('#jenisKemasukan').val() == 'TS'){
               //                   var idHakmilikTanahSempadan = $('#idHakmilikTanahSempadan').val();
               //                   if(document.getElementById("pilihYa").checked == true && idHakmilikTanahSempadan == ""){
               //                       alert("Sila masukkan id hakmilik.");
               //                       return false;
               //                   }
               //               }
        
               allowPrompt = true;
               //               closeAndRefresh();

           }
          
           function closeAndRefresh(){
               var id = $('#idLaporanTanah').val();
               self.opener.refreshFindInfo(id);
               self.close();
           }
           
           function chooseOptionHakmilik(i){
               if(i == 'Y'){
                   //if have id hakmilik
                   document.getElementById("pilihTidak").checked = false;
                   document.getElementById("pilihYa").checked = true;
                   
                   document.getElementById("tiadaHakmilikDiv").style.visibility = 'hidden';
                   document.getElementById("tiadaHakmilikDiv").style.display = 'none';

                   document.getElementById("resultHakmilikDiv").style.visibility = 'visible';
                   document.getElementById("resultHakmilikDiv").style.display = '';
                   
                   $('#kodKategoriTanahTanpaHakmilik').attr("disabled", true);
                   $('#noLotTanpaHakmilik').attr("disabled", true);
                   $('#kodLotTanpaHakmilik').attr("disabled", true);
                   
                   $('#noLotTanahSempadanHakmilik').attr("disabled", false);
                   
                   $('#noLotTanpaHakmilik').val('');
                   $('#kodKategoriTanahSempadanHakmilik').val('');
                   $('#noLotTanahSempadanHakmilik').val('');
                   $('#kodLotTanahSempadanHakmilik').val('');
                   
                   
                   
               }else{
                   //if dont have id hakmilik
                   document.getElementById("pilihYa").checked = false;
                   document.getElementById("pilihTidak").checked = true;
                    
                   document.getElementById("resultHakmilikDiv").style.visibility = 'hidden';
                   document.getElementById("resultHakmilikDiv").style.display = 'none';

                   document.getElementById("tiadaHakmilikDiv").style.visibility = 'visible';
                   document.getElementById("tiadaHakmilikDiv").style.display = '';
                   
                   $('#kodKategoriTanahTanpaHakmilik').attr("disabled", false);
                   $('#noLotTanpaHakmilik').attr("disabled", false);
                   $('#kodLotTanpaHakmilik').attr("disabled", false);
                   $('#noLotTanahSempadanHakmilik').attr("disabled", true);
                   
                   
                  
               }
               
           }
           
           function setValueHakmilik(){
               $('#hasilCarianKategoriTanah').val($('#kodKategoriTanahCarianHakmilik').val());
               $('#hasilCarianNoLot').val($('#noLotCarianHakmilik').val());
               $('#hasilCarianKodLot').val($('#namaLotCarianHakmilik').val());
               $('#hasilCarianLuas').val($('#luasCarianHakmilik').val());
               $('#hasilCarianKodUnitLuas').val($('#namaUnitLuasCarianHakmilik').val());
               $('#hasilCarianDaerah').val($('#daerahCarianHakmilik').val());
               $('#hasilCarianBPM').val($('#bpmCarianHakmilik').val());
               $('#hasilCarianAtasTanah').val($('#atasTanahCarianHakmilik').val());
               $('#hakmilikCarian').val($('#idHakmilikCarianHakmilik').val());
               
           }
           
         
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<s:form  name="form2" beanclass="etanah.view.penguatkuasaan.MohonLaporTanahActionBean">
    <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
    <div id ="msgInfoDiv">
        <s:messages/>
        <s:errors/>
    </div>


    <s:hidden name="idLaporanTanah" id="idLaporanTanah"/>
    <s:hidden name="jenisKemasukan" id="jenisKemasukan"/>
    <s:hidden name="idValue" id="idValue"/>


    <div id="hasilCarianHakmilikDiv" style="visibility: hidden; display: none">
        <s:text name="statusCarian" id="statusCarianHakmilik"/>
        <s:text name="hakmilik.idHakmilik" id="idHakmilikCarianHakmilik"/>
        <s:text name="hakmilik.kategoriTanah.nama" id="kodKategoriTanahCarianHakmilik"/>
        <s:text name="hakmilik.noLot" id="noLotCarianHakmilik"/>
        <s:text name="hakmilik.lot.nama" id="namaLotCarianHakmilik"/>
        <s:text name="hakmilik.luas" id="luasCarianHakmilik"/>
        <s:text name="hakmilik.kodUnitLuas.nama" id="namaUnitLuasCarianHakmilik"/>
        <s:text name="hakmilik.daerah.nama" id="daerahCarianHakmilik"/>
        <s:text name="hakmilik.bandarPekanMukim.nama" id="bpmCarianHakmilik"/>
        <s:text name="hakmilik.maklumatAtasTanah" id="atasTanahCarianHakmilik"/>
    </div>

    <c:if test="${actionBean.jenisKemasukan eq 'TH'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Hakmilik
                </legend>
                <div class="content">
                    <p>
                        <label>Hakmilik :</label>
                        <s:text name="hakmilikCarian" id="hakmilikCarian" onkeyup="this.value=this.value.toUpperCase();"/>
                        <font color="red" size="1">cth : 050501GM00000001</font>
                        <s:button class="btn" value="Cari" name="new" id="new" onclick="carianHakmilik('','TH');"/>
                    </p>
                    <p>
                        <label>Kategori Tanah :</label>
                        <input type="text" id="hasilCarianKategoriTanah" readonly="true">&nbsp;
                    </p>
                    <p>
                        <label>No Lot :</label>
                        <input type="text" id="hasilCarianNoLot" readonly="true">&nbsp;
                    </p>
                    <p>
                        <label>Kod Lot:</label>
                        <input type="text" id="hasilCarianKodLot" readonly="true">&nbsp;
                    </p>
                    <p>
                        <label>Luas :</label>
                        <input type="text" id="hasilCarianLuas" readonly="true">&nbsp;
                    </p>
                    <p>
                        <label>Kod Luas:</label>
                        <input type="text" id="hasilCarianKodUnitLuas" readonly="true">&nbsp;
                    </p>
                    <p>
                        <label>Daerah:</label>
                        <input type="text" id="hasilCarianDaerah" readonly="true">&nbsp;
                    </p>
                    <p>
                        <label>Bandar/Pekan/Mukim:</label>
                        <input type="text" id="hasilCarianBPM" readonly="true">&nbsp;
                    </p>
                    <p>
                        <label>Jenis Penggunaan Tanah:</label>
                        <input type="text" id="hasilCarianAtasTanah" readonly="true">&nbsp;
                    </p>
                    <p>
                        <label>Kod PBT :</label>
                        <s:select name="kodPBT" id="kodPBT">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodPBT}" label="nama" value="kod" sort="nama" />
                        </s:select>
                    </p>
                    <p>
                        <label>Kod Dun :</label>
                        <s:select name="kodDun" id="kodDun">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodDUN}" label="nama" value="kod" sort="nama" />
                        </s:select>
                    </p>
                    <p>
                        <label>Lokaliti :</label>
                        <s:text name="lokasi" size="50" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/> &nbsp;
                    </p>
                </div>
            </fieldset>
        </div>

    </c:if>
    <c:if test="${actionBean.jenisKemasukan eq 'TK'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Tanah Kerajaan/Rizab
                </legend>
                <div class="content">
                    <p>
                        <label>No. Lot :</label>
                        <s:text name="noLot" id="noLot" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;
                    </p>
                    <p>
                        <label>Kod Lot :</label>
                        <s:select name="kodLot" id="kodLot">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodLot}" label="nama" value="kod" sort="nama" />
                        </s:select>
                    </p>
                    <p>
                        <label>Kategori Tanah :</label>
                        <s:select name="kategoriTanah" id="kategoriTanah">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" sort="nama" />
                        </s:select>
                    </p>
                    <p>
                        <label>Luas :</label>
                        <s:text name="luas" id="luas" onkeyup="validateNumberLuas(this,this.value);" maxlength="7"/>
                        <s:select name="kodLuas" id="kodLuas">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodUOMByLuas}" label="nama" value="kod" sort="nama" />
                        </s:select>
                    </p>
                    <p>
                        <label>Bandar/Pekan/Mukim :</label>
                        <s:select name="kodBPM" id="kodBPM">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="kod" sort="nama" />
                        </s:select>
                    </p>
                    <p>
                        <label>Kod PBT :</label>
                        <s:select name="kodPBT" id="kodPBT">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodPBT}" label="nama" value="kod" sort="nama" />
                        </s:select>
                    </p>
                    <p>
                        <label>Kod Dun :</label>
                        <s:select name="kodDun" id="kodDun">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodDUN}" label="nama" value="kod" sort="nama" />
                        </s:select>
                    </p>
                    <p>
                        <label>Badan Pengawal Tanah Kerajaan/Rezab :</label>
                        <s:text name="badanPengawal" id="badanPengawal" size="50" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;
                    </p>
                    <p>
                        <label>Catatan :</label>
                        <s:textarea name="catatan" id="catatan" cols="50" rows="5" onchange="this.value=this.value.toUpperCase();"/>
                    </p>
                </div>

            </fieldset>
        </div>
    </c:if>

    <c:if test="${actionBean.jenisKemasukan eq 'PT'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Perihal Tanah
                </legend>

                <div class="content">

                    <p>
                        <label>Nombor Warta Kerajaan:</label>
                        <s:text name="noWartaKerajaan" maxlength="50" id="noWartaKerajaan"/>&nbsp;
                    </p>

                    <p>
                        <label>Tarikh Lawatan:</label>
                        <s:text name="tarikhLawatan" id="tarikhLawatan" class="datepicker" formatPattern="dd/MM/yyyy"/>&nbsp;

                        <font color="red" size="1">cth : hh / bb / tttt</font>
                    </p>
                    <p>
                        <label>Masa Lawatan:</label>
                        <s:select name="jam" id="jam">
                            <s:option value=""> Jam </s:option>
                            <c:forEach var="jam" begin="1" end="12">
                                <s:option value="${jam}">${jam}</s:option>
                            </c:forEach>
                        </s:select>
                        <s:select name="minit" id="minit">
                            <s:option value=""> Minit </s:option>
                            <c:forEach var="minit" begin="00" end="59" >
                                <c:choose>
                                    <c:when test="${minit > 9}"><s:option value="${minit}">${minit}</s:option></c:when>
                                    <c:otherwise><s:option value="0${minit}">0${minit}</s:option></c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </s:select>
                        <s:select name="ampm" id="ampm">
                            <s:option value="">Pilih</s:option>
                            <s:option value="AM">PAGI</s:option>
                            <s:option value="PM">PETANG</s:option>
                        </s:select>
                    </p>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne '49'}">
                        <p>
                            <label>Anggaran Luas Terhakis/Ceroboh/Dilanggar Syarat :</label>
                            <s:text name="laporanTanah.usahaLuas" onkeyup="validateNumberLuas(this,this.value);"/>
                            <s:select name="anggaranLuasUOM">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodUOMByLuas}" label="nama" value="kod" sort="nama" />
                            </s:select>
                        </p>
                    </c:if>
                    <br>
                    <div class="content" align="center">
                        <c:set var="idAduan" value="${actionBean.permohonan.idPermohonan}" />
                        <c:set var="kodNegeri" value="${fn:substring(idAduan, 0, 2)}" />
                        <c:if test = "${kodNegeri eq '05'}">Bersempadan</c:if>
                        <c:if test = "${kodNegeri eq '04'}">Mercu Tanda</c:if>
                        <%--Bersempadan--%>
                        <table class="tablecloth">
                            <tr>
                                <th>Bersempadan</th><th>Nama</th><th>Jarak</th>
                            </tr>
                            <tr>
                                <td>
                                    Jalan Raya
                                </td>
                                <td>
                                    <s:text name="laporanTanah.namaSempadanJalanraya"/>
                                </td>
                                <td>
                                    <s:text name="laporanTanah.jarakSempadanJalanraya"  maxlength="5" onkeyup="validateNumberLuas(this,this.value);"/>
                                    <s:select name="jarakJalanUOM"  id="jenisWarta">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.kodUOMByJarak}" label="nama" value="kod" sort="nama" />
                                    </s:select>
                                </td>

                            </tr>
                            <tr>
                                <td>
                                    Landasan Keretapi
                                </td>
                                <td>
                                    <s:text name="laporanTanah.namaSempadanKeretapi" />
                                </td>
                                <td>
                                    <s:text name="laporanTanah.jarakSempadanKeretapi" maxlength="5" onkeyup="validateNumberLuas(this,this.value);"/>
                                    <s:select name="jarakKeretapiUOM"  id="jenisWarta">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.kodUOMByJarak}" label="nama" value="kod" sort="nama" />
                                    </s:select>
                                </td>

                            </tr>
                            <tr>
                                <td>
                                    Laut
                                </td>
                                <td>
                                    <s:text name="laporanTanah.namaSempadanLaut" />
                                </td>
                                <td>
                                    <s:text name="laporanTanah.jarakSempadanLaut"  maxlength="5" onkeyup="validateNumberLuas(this,this.value);"/>
                                    <s:select name="jarakLautUOM"  id="jenisWarta">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.kodUOMByJarak}" label="nama" value="kod" sort="nama" />
                                    </s:select>
                                </td>
                            </tr><tr>
                                <td>
                                    Sungai
                                </td>
                                <td>
                                    <s:text name="laporanTanah.namaSempadanSungai" />
                                </td>
                                <td>
                                    <s:text name="laporanTanah.jarakSempadanSungai"  maxlength="5" onkeyup="validateNumberLuas(this,this.value);"/>
                                    <s:select name="jarakSungaiUOM"  id="jenisWarta">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.kodUOMByJarak}" label="nama" value="kod" sort="nama" />
                                    </s:select>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <p>
                        <label>Jenis Jalan :</label>
                        <s:select name="laporanTanah.jenisJalan" style="width:200px" id="jabatan">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="JB">Jalan Berturap</s:option>
                            <s:option value="JL">Jalan Leterite</s:option>
                            <s:option value="JTM">Jalan Tanah Merah</s:option>
                            <s:option value="JT">Jalan Tanah</s:option>
                        </s:select>
                    </p>
                    <p>
                        <label>Jalan Masuk :</label>
                        <s:radio name="laporanTanah.adaJalanMasuk" value="Y"/>&nbsp;Ada
                        <s:radio name="laporanTanah.adaJalanMasuk" value="T"/>&nbsp;Tiada
                    </p>
                    <p>
                        <label>Catatan :</label>
                        <s:textarea name="laporanTanah.catatanJalanMasuk" class="normal_text" cols="50" rows="5"/>
                    </p>

                </div>
                <legend>
                    Permohonan Terdahulu 
                </legend>
                <div class="content">
                    <p>
                        <label>No. Fail:</label>
                        <s:text name="noFail" maxlength="50" id="noFail"/>&nbsp;
                    </p>
                </div>
            </fieldset>
        </div>
    </c:if>

    <c:if test="${actionBean.jenisKemasukan eq 'KT'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Keadaan Tanah</legend>
                <p id="kodKecerunanTanahDiv">
                    <label>Keadaan Tanah :</label>

                    <s:select name="kodKecerunanTanah" id="keadaanTanah" onchange="javaScript:changeKeadaanTanah(this.options[selectedIndex].text)">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodKecerunanTanah}" label="nama" value="kod" />
                    </s:select>
                </p>
                <p id="tinggi">
                    <label>Ketinggian Dari Paras Jalan (m) :</label>
                    <s:text name="laporanTanah.ketinggianDariJalan" maxlength="3" onkeyup="validateNumberLuas(this,this.value);"/>
                </p>
                <p id="cerun">
                    <label>Darjah Kecerunan (&deg;):</label>
                    <s:text name="laporanTanah.kecerunanBukit"  maxlength="3" onkeyup="validateNumberLuas(this,this.value);"/>
                </p>
                <p id="dalam">
                    <label>Dalam Paras Air (m) :</label>
                    <s:text name="laporanTanah.parasAir" maxlength="3" onkeyup="validateNumberLuas(this,this.value);"/>
                </p>
                <p>
                    <label>Klasifikasi Tanah :</label>
                    <s:select name="kodStrukturTanah">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodStrukturTanah}" label="nama" value="kod" />
                    </s:select>
                </p>
                <br>
                <p>
                    <label>Keadaan Atas Tanah :</label>
                    <s:textarea name="laporanTanah.keadaanTanah" class="normal_text" cols="50" rows="5" onkeydown="limitText(this,500);" onkeyup="limitText(this,500);"/>
                </p>
                <p>
                    <label>Dilintasi Oleh :</label>
                    <s:checkbox name="laporanTanah.dilintasTiangElektrik" value="Y"/>&nbsp; Talian Elektrik<br>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="laporanTanah.dilintasTiangTelefon" value="Y"/>&nbsp; Talian Telefon<br>

                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="laporanTanah.dilintasLaluanGas" value="Y"/>&nbsp; Laluan Gas<br>

                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="laporanTanah.dilintasPaip" value="Y"/>&nbsp; Paip Air<br>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="laporanTanah.dilintasTaliar" value="Y"/>&nbsp; Tali Air<br>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="laporanTanah.dilintasSungai" value="Y"/>&nbsp; Sungai<br>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="laporanTanah.dilintasParit" value="Y"/>&nbsp; Parit<br>
                </p>
            </fieldset>
        </div>
    </c:if>

    <c:if test="${actionBean.jenisKemasukan eq 'LBT'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Latar belakang Tanah</legend>
                <p>
                    <label>Diusahakan :</label>
                    <input name="diUsahakan" id="diUsahakan" value="${actionBean.laporanTanah.usaha}" type="hidden">
                    <%--<s:text name="diUsahakan" id="diUsahakan" value="${actionBean.laporanTanah.usaha}"/>--%>
                    <s:radio name="laporanTanah.usaha" value="Y" onclick="showUsaha();"/>&nbsp;Ya
                    <s:radio name="laporanTanah.usaha" value="T" onclick="hideUsaha();"/>&nbsp;Tidak
                </p>
                <p style="display:none" id="usaha1">
                    <label>Oleh :</label>
                    <s:text name="laporanTanah.diusaha" id="diusaha" size="40"/>
                </p>
                <p style="display:none" id="usaha2">
                    <label>Tarikh Mula Usaha :</label>
                    <s:text name="tarikhDiusahakan" class="datepicker" formatPattern="dd/MM/yyyy"/>

                </p>
                <p style="display:none" id="usaha3">
                    <label>Jenis Tanaman :</label>
                    <s:text name="laporanTanah.usahaTanam" id="usahaTanam" size="40"/>
                </p>
                <p>
                    <label>(Jika Ada)Bangunan :</label>
                    <input name="adaBangunan" id="adaBangunan" value="${actionBean.laporanTanah.adaBangunan}" type="hidden">
                    <s:radio name="laporanTanah.adaBangunan" value="Y" onclick="showBilBangunan();"/>&nbsp;Ada
                    <s:radio name="laporanTanah.adaBangunan" value="T" onclick="hideBilBangunan();"/>&nbsp;Tiada
                </p>
                <p style="display:none" id="bilanganBangunan">

                    <label>
                        Bil Bangunan :
                    </label>        
                    <s:select title="Bil Bangunan :" name="laporanTanah.bilanganBangunan" id="bilanganBangunan">
                        <s:option value="0">--Sila Pilih--</s:option>
                        <s:option>1</s:option>
                        <s:option>2</s:option>
                        <s:option>3</s:option>
                        <s:option>4</s:option>
                        <s:option>5</s:option>
                        <s:option>6</s:option>
                        <s:option>7</s:option>
                        <s:option>8</s:option>
                        <s:option>9</s:option>
                        <s:option>10</s:option>
                    </s:select>

                </p>
                <p style="display:none" id="bilanganBangunan2">
                    <label>Tahun Dibina :</label>
                    <s:text name="laporanTanah.bangunanTahunDibina" id="bangunanTahunDibina" maxlength="4" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p style="display:none" id="bilanganBangunan3">
                    <label>Diduduki :</label>
                    <s:radio name="laporanTanah.bangunanDidiami" id="bangunanDidiami" value="Y"/>&nbsp;Ya
                    <s:radio name="laporanTanah.bangunanDidiami" id="bangunanDidiami" value="T"/>&nbsp;Tidak
                </p>

                <p>
                    <label>Rancangan Kerajaan :</label>
                    <s:text name="laporanTanah.rancanganKerajaan" size="40"/>
                </p>
                <br>
            </fieldset>
        </div>
    </c:if>

    <c:if test="${actionBean.jenisKemasukan eq 'JB'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Jenis Bangunan
                </legend>

                <div class="content">
                    <p>
                        <label>Jenis Bangunan :</label>
                        <s:select name="jenisBangunan" id="jenisBangunan">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="SM">Sementara</s:option>
                            <s:option value="SK">Separuh Kekal</s:option>
                            <s:option value="KK">Kekal</s:option>
                            <s:option value="LL">Lain-lain</s:option>

                        </s:select>
                        &nbsp;
                    </p>
                    <p>
                        <label>Ukuran :</label>
                        <s:text name="ukuran" id="ukuran" onkeyup="validateNumberLuas(this,this.value);" size="5"/> x <s:text name="ukuranTemp" id="ukuran" onkeyup="validateNumberLuas(this,this.value);" size="5"/>
                        <font color="red" size="1">(Panjang) x (Lebar)</font>
                    </p>
                    <p>
                        <label>Unit Ukuran :</label>
                        <s:select name="uomUkuran" id="uomUkuran">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodUOM}" label="nama" value="kod" />
                        </s:select>&nbsp;
                    </p>
                    <p>
                        <label>Nilai (RM) :</label>
                        <s:text name="nilai" id="nilai" size="15" class="number" formatPattern="0.00" onblur="returnCurrency(this.value);"  maxlength="10" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                    <p>
                        <label>Nama Pemunya :</label>
                        <s:text name="namaPemunya" id="namaPemunya" onkeyup="this.value=this.value.toUpperCase();" size="40" maxlength="130" />
                    </p>
                    <p>
                        <label>Nama Penyewa :</label>
                        <s:text name="namaPenyewa" id="namaPenyewa" onkeyup="this.value=this.value.toUpperCase();" size="40" maxlength="130" />
                    </p>
                </div>
            </fieldset>
        </div>


    </c:if>
    <c:if test="${actionBean.jenisKemasukan eq 'TS'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Sempadan
                </legend>
                <div class="content">
                    <s:hidden name="idLaporTanahSpdn"/>
                    <p>
                        <label>Jenis Sempadan :</label>
                        <s:select name="jenisSempadan" id="jenisSempadan">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="U">Utara</s:option>
                            <s:option value="B">Barat</s:option>
                            <s:option value="T">Timur</s:option>
                            <s:option value="S">Selatan</s:option>

                        </s:select>
                        &nbsp;
                    </p>
                    <p>
                        <label>Ada Hakmilik : </label>
                        <input type="radio" value="Y" id="pilihYa" onclick="chooseOptionHakmilik('Y');" name="chooseHakmilik"> Ya
                        <input type="radio" value="T" id="pilihTidak" onclick="chooseOptionHakmilik('T');" name="chooseHakmilik"> Tidak

                    </p>

                    <div id="resultHakmilikDiv" style="visibility: hidden; display: none">
                        <p>
                            <label>Hakmilik :</label>
                            <s:text name="idHakmilikTanahSempadan" id="idHakmilikTanahSempadan" onkeyup="this.value=this.value.toUpperCase();"/>
                            <s:button class="btn" value="Cari" name="new" id="new" onclick="carianHakmilik('','TS');"/>
                        </p>
                        <p>
                            <label>Kegunaan :</label>
                            <s:select name="kodKategoriTanahSempadan" id="kodKategoriTanahSempadanHakmilik" disabled="true">
                                <s:option value="">Tiada</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" sort="nama"/>
                            </s:select>&nbsp;
                        </p>
                        <p>
                            <label>No Lot :</label>
                            <s:text name="noLotTanahSempadan" id="noLotTanahSempadanHakmilik" size="10" onkeyup="validateNumber(this,this.value);" readonly="true"/> &nbsp;
                        </p>
                        <p>
                            <label>Kod Lot:</label>
                            <s:select name="kodLotTanahSempadan" id="kodLotTanahSempadanHakmilik" disabled="true">
                                <s:option value="">Tiada</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodLot}" label="nama" value="kod" sort="nama" />
                            </s:select>
                        </p>
                    </div>
                    <div id="tiadaHakmilikDiv">
                        <p>
                            <label>Kegunaan :</label>
                            <s:select name="kodKategoriTanahSempadan" id="kodKategoriTanahTanpaHakmilik">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" sort="nama" />
                            </s:select>&nbsp;
                        </p>
                        <p>
                            <label>No Lot :</label>
                            <s:text name="noLotTanahSempadan" id="noLotTanpaHakmilik" size="10" onkeyup="validateNumber(this,this.value);"/> &nbsp;
                        </p>
                        <p>
                            <label>Kod Lot:</label>
                            <s:select name="kodLotTanahSempadan" id="kodLotTanpaHakmilik">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodLot}" label="nama" value="kod" sort="nama" />
                            </s:select>
                        </p>
                    </div>

                    <p>
                        <label>Catatan :</label>
                        <s:textarea name="catatan" id="catatan" cols="50" rows="6" onkeydown="limitText(this,150);" onkeyup="limitText(this,150);"/>
                    </p>
                </div>
            </fieldset>
        </div>

    </c:if>
    <c:if test="${actionBean.jenisKemasukan eq 'UPPT'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <div class="content">
                    <legend>Ulasan</legend>
                    <p>
                        <label><font style="text-transform: capitalize">Syor ${actionBean.pengguna.jawatan} : </font></label>
                            <s:textarea name="ulasanPPT" id="ulasanPPT" cols="70" rows="10" class="normal_text"/>
                    </p>
                </div>
            </fieldset>
        </div>

    </c:if>
    <c:if test="${actionBean.jenisKemasukan eq 'UPPTK'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <div class="content">
                    <legend>Ulasan</legend>
                    <p>
                        <label><font style="text-transform: capitalize">Syor ${actionBean.pengguna.jawatan} : </font></label>
                            <s:textarea name="ulasanPPTK" id="ulasanPPTK" cols="70" rows="10" class="normal_text"/>
                    </p>
                </div>
            </fieldset>
        </div>

    </c:if>
    <div class="subtitle">
        <fieldset class="aras1">
            <p align="center">
                <%--<s:button name="simpanLaporanTanah" id="simpan" class="btn" value="Simpan" onclick="simpan1(this.name,this.form);"/>--%>
                <s:submit name="simpanLaporanTanah" value="Simpan" class="btn" onclick="NoPrompt();"/>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="closeAndRefresh();"/>
                <s:button name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
            </p>
            <br>   
        </fieldset>

    </div>
</s:form>