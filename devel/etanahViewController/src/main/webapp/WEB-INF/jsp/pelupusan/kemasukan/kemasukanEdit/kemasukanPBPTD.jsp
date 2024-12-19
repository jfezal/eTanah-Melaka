<%-- 
    Document   : kemasukanPBPTD
    Created on : Mar 14, 2013, 5:22:58 PM
    Author     : afham
    Revised    : Iskandar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KEMASUKAN MAKLUMAT TANAH</title>
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
    }); //END OF READY FUNCTION

    function carianhakmilik(){
        //alert();
        NoPrompt();
        var url = '${pageContext.request.contextPath}/common/carian_hakmilik?pertanyaanHakmilik';
        window.open(url, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
    function refreshpage2(type){
        //        alert(type);
        var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?refreshpage&type='+type;
        window.open(url, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
    function doSomething(val){
          
        if(val=='GRN'||val=='GM'){
            //alert(val);
            $('#jikaPajakan').show();
        }else{
            //alert(val);
            $('#jikaPajakan').hide();
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
    
        function getSeksyen()
    {
        NoPrompt();
        var mpb = $('#mpb').val();
        var tempat = $('#tempat').val();
        var Luas = $('#Luas').val();
        var kd = $('#kd').val();
        var kULuas = $('#kULuas').val();
        var kodlot = $('#kodlot').val();
        var noLot = $('#noLot').val();
        var Jarak = $('#Jarak').val();
        var uJarak = $('#uJarak').val();
        var jarakDari = $('#jarakDari').val();
        var katTanahPilihan = $('#_kodKatTanah').val();
        var kodSeksyen = $('#_kodSeksyen2').val();
//        alert("Kod Seksyen" + kodSeksyen);
        var kodGunaTanah = $('#_kodKegunaTanah').val();
        var idMH = $('#idMH').val() ;
        var type = $('#type').val();
//        alert(type);
//        alert("Kod Tanah" + kodGunaTanah);
        self.refreshDataSeksyen(mpb,tempat,Luas,kd,kULuas,kodlot,noLot,Jarak,uJarak,jarakDari,katTanahPilihan,kodSeksyen,kodGunaTanah,idMH,type);
    }
    
    function refreshDataSeksyen(mpb,tempat,Luas,kd,kULuas,kodlot,noLot,Jarak,uJarak,jarakDari,katTanahPilihan,kodSeksyen,kodGunaTanah,idMH,type){
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_tanahV3?carianIndependent&katTanahPilihan="
            +katTanahPilihan+"&mpb="+mpb+"&tempat="+tempat+"&Luas="+Luas+"&kULuas="+kULuas+"&kodlot="+kodlot+"&noLot="+noLot+"&Jarak="+Jarak+'&uJarak='+uJarak+"&jarakDari="+jarakDari+"&kodSeksyen="+kodSeksyen+"&kodGunaTanah="
            +kodGunaTanah+"&idKandungan="+idMH+"&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }
    function putValueSeksyen(val){
        $('#_kodSeksyen2').val(val);
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
    <s:form beanclass="etanah.view.stripes.pelupusan.maklumat_tanah.MaklumatTanahV2ActionBean" name="form">
        <s:hidden name="idMH" id="idMH"/>
        <s:hidden name="type" id="type"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <c:choose>
                    <c:when test="${actionBean.negeri eq '04'}">                        
                        <table class="tablecloth" align="center">
                            <tr>
                                <td>Daerah :</td>
                                <td>
                                    ${actionBean.permohonan.cawangan.daerah.nama} 
                                </td>
                            </tr>
                                <tr>
                                    <td><font color="red" size="4">*</font>Mukim/Pekan/Bandar :</td>
                                    <td>
                                        <s:select name="bandarPekanMukimBaru.kod" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.bandarPekanMukimBaru.kod}" id="mpb" onchange="getSeksyen();">
                                            <s:option value="">Sila Pilih</s:option>
                                            <s:options-collection collection="${actionBean.senaraiKodBPM}" label="nama" value="kod" />
                                        </s:select>
                                    </td>
                                </tr>
                            <s:hidden name="test" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.kodSeksyen.kod}" style="width:300px;" id="_kodSeksyen2"/>
                            <c:if test="${actionBean.forSeksyen}">
                                <tr>
                                    <td>Seksyen :</td>
                                    <td>
                                        <s:select name="kodSeksyen.kod" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.kodSeksyen.kod}"style="width:300px;" id="_kodSeksyen" onchange="putValueSeksyen(this.value);">
                                            <s:option value="">-- Sila Pilih --</s:option>
                                            <s:options-collection collection="${actionBean.senaraiKodSeksyen}" value="kod" label="nama" />
                                        </s:select>
                                    </td>
                                </tr>
                            </c:if>
                            <tr>
                                <td>Tempat/Lokasi Tanah Dipohon :</td>
                                <td>
                                    <s:textarea name="hakmilikPermohonan.lokasi" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.lokasi}" rows="5" cols="70" id="tempat" class="normal_text"/>
                                </td>
                            </tr>
                            <tr>
                                <td>Tujuan :</td>
                                <td><s:textarea name="permohonan.sebab" rows="5" cols="50"/></td>
                            </tr>
                            <tr>
                                <td>Nombor Lot/PT :</td>
                                <td>
                                    <s:select name="kodLot.kod" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.lot.kod}" id="kodlot">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiLotPelupusan}" label="nama" value="kod" />
                                    </s:select>
                                    <s:text name="hakmilikPermohonan.noLot" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.noLot}" onkeyup="validateNumber(this,this.value);" id="noLot"/>&nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td>Luas Dipohon :</td>
                                <td>
                                    <s:text name="hakmilikPermohonan.luasTerlibat" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.luasTerlibat}" formatPattern="#,###,##0.0000" maxlength="15" onkeyup="validateNumber(this,this.value);" id="Luas"/>
                                    <s:select name="disHakmilikPermohonan.keluasanUOM" id="kULuas">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:option value="H">Hektar</s:option>
                                        <s:option value="M">Meter Persegi</s:option>
                                    </s:select>
                                </td>
                            </tr>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PBPTD' && actionBean.permohonan.kodUrusan.kod ne 'PBPTG' }">
                            <tr>
                                <td>Kategori Tanah :</td>
                                <td><s:select name="kategoriTanahBaru.kod" id="_kodKatTanah" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.kategoriTanahBaru.kod}" onchange="getSeksyen();">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" />
                                    </s:select></td>
                            </tr>
                            <tr>
                                <td>Kegunaan Tanah :</td>
                                <td><s:select name="kodGunaTanah"  value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.kodKegunaanTanah.kod}" style="width:300px;" id="_kodKegunaTanah" onchange="putValueGunaTanah(this.value);">
                                        <s:option value="">-- Sila Pilih --</s:option>
                                        <s:options-collection collection="${actionBean.senaraiKodKegunaanTanahs}" value="kod" label="nama" />
                                    </s:select></td>
                            </tr>
                            </c:if>
                            <tr>
                                <td>Jarak :</td>
                                <td><s:text name="hakmilikPermohonan.jarak" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.jarak}" maxlength="6" onkeyup="validateNumber(this,this.value);" id="Jarak"/>
                                    <s:select name="unitJarak.kod" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.unitJarak.kod}" id="uJarak">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:option value="KM">Kilometer</s:option>
                                        <s:option value="JM">Meter</s:option>
                                    </s:select>
                                    &nbsp;<font color="#003194"><b>dari</b></font>&nbsp;
                                    <s:select name="hakmilikPermohonan.jarakDari" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.jarakDari}" id="jarakDari">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:option value="Jalan Raya">Jalan Raya</s:option>
                                        <s:option value="Landasan Keretapi">Landasan Keretapi</s:option>
                                        <s:option value="Pantai">Pantai</s:option>
                                        <s:option value="Pekan">Pekan</s:option>
                                        <s:option value="Sungai">Sungai</s:option>
                                    </s:select></td>
                            </tr>
                            <tr>
                                <td>Nama Jalan/Keretapi/Pantai/Pekan/Sungai :</td>
                                <td><s:text name="hakmilikPermohonan.jarakDariNama" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.jarakDariNama}" id="namaJ" size="40"/></td>
                            </tr>
                            <tr>
                                <td>Jarak Dari Kediaman :</td>
                                <td><s:text name="hakmilikPermohonan.jarakDariKediaman" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.jarakDariKediaman}" maxlength="6" onkeyup="validateNumber(this,this.value);" id="JarakKediaman"/>
                                    <s:select name="jarakDariKediamanUom.kod" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.jarakDariKediamanUom.kod}" id="uJarakKUOM">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:option value="KM">Kilometer</s:option>
                                        <s:option value="JM">Meter</s:option>
                                    </s:select></td>
                            </tr>
                            <tr>
                                <td style="text-align:center;" colspan="3">
                                    <s:button name="carihm" value="Carian Hakmilik" class="btn" onclick="carianhakmilik();"/>
                                    <s:submit name="simpan" value="Simpan" class="btn" onclick="NoPrompt();"/>
                                    <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                                </td>
                            </tr>  
                        </table>       
                        <br/>
                    </c:when>
                    <c:when test="${actionBean.negeri eq '05'}">                        
                        <table class="tablecloth" align="center">
                            <tr>
                                <td>Daerah :</td>
                                <td>
                                    ${actionBean.permohonan.cawangan.daerah.nama}
                                </td>
                            </tr>
                                <tr>
                                    <td><font color="red" size="4">*</font>Mukim/Pekan/Bandar :</td>
                                    <td>
                                        <s:select name="bandarPekanMukimBaru.kod" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.bandarPekanMukimBaru.kod}" id="mpb" onchange="getSeksyen();">
                                            <s:option value="">Sila Pilih</s:option>
                                            <s:options-collection collection="${actionBean.senaraiKodBPM}" label="nama" value="kod" />
                                        </s:select>
                                    </td>
                                </tr>
                            <s:hidden name="test" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.kodSeksyen.kod}" style="width:300px;" id="_kodSeksyen2"/>
                            <c:if test="${actionBean.forSeksyen}">
                                <tr>
                                    <td>Seksyen :</td>
                                    <td>
                                        <s:select name="kodSeksyen.kod" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.kodSeksyen.kod}"style="width:300px;" id="_kodSeksyen" onchange="putValueSeksyen(this.value);">
                                            <s:option value="">-- Sila Pilih --</s:option>
                                            <s:options-collection collection="${actionBean.senaraiKodSeksyen}" value="kod" label="nama" />
                                        </s:select>
                                    </td>
                                </tr>
                            </c:if>
                            <tr>
                                <td>Tempat/Lokasi Tanah Dipohon :</td>
                                <td>
                                    <s:textarea name="hakmilikPermohonan.lokasi" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.lokasi}" rows="5" cols="70" id="tempat" class="normal_text"/>
                                </td>
                            </tr>
                            <tr>
                                <td>Nombor Lot/PT :</td>
                                <td>
                                    <s:select name="kodLot.kod" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.lot.kod}" id="kodlot">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiLotPelupusan}" label="nama" value="kod" />
                                    </s:select>
                                    <s:text name="hakmilikPermohonan.noLot" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.noLot}" onkeyup="validateNumber(this,this.value);" id="noLot"/>&nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td>Luas Dipohon :</td>
                                <td>
                                    <s:text name="hakmilikPermohonan.luasTerlibat" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.luasTerlibat}" formatPattern="#,###,##0.0000" maxlength="15" onkeyup="validateNumber(this,this.value);" id="Luas"/>
                                    <s:select name="disHakmilikPermohonan.keluasanUOM" id="kULuas">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:option value="H">Hektar</s:option>
                                        <s:option value="M">Meter Persegi</s:option>
                                    </s:select>
                                </td>
                            </tr>
                            <tr>
                                <td>Kategori Tanah :</td>
                                <td><s:select name="kategoriTanahBaru.kod" id="_kodKatTanah" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.kategoriTanahBaru.kod}" onchange="getSeksyen();">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" />
                                    </s:select></td>
                            </tr>
                            <tr>
                                <td>Kegunaan Tanah :</td>
                                <td><s:select name="kodGunaTanah"  value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.kodKegunaanTanah.kod}" style="width:300px;" id="_kodKegunaTanah" onchange="putValueGunaTanah(this.value);">
                                        <s:option value="">-- Sila Pilih --</s:option>
                                        <s:options-collection collection="${actionBean.senaraiKodKegunaanTanahs}" value="kod" label="nama" />
                                    </s:select></td>
                            </tr>
                            <tr>
                                <td>Jarak :</td>
                                <td><s:text name="hakmilikPermohonan.jarak" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.jarak}" maxlength="6" onkeyup="validateNumber(this,this.value);" id="Jarak"/>
                                    <s:select name="unitJarak.kod" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.unitJarak.kod}" id="uJarak">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:option value="KM">Kilometer</s:option>
                                        <s:option value="JM">Meter</s:option>
                                    </s:select>
                                    &nbsp;<font color="#003194"><b>dari</b></font>&nbsp;
                                    <s:select name="hakmilikPermohonan.jarakDari" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.jarakDari}" id="jarakDari">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:option value="Jalan Raya">Jalan Raya</s:option>
                                        <s:option value="Landasan Keretapi">Landasan Keretapi</s:option>
                                        <s:option value="Pantai">Pantai</s:option>
                                        <s:option value="Pekan">Pekan</s:option>
                                        <s:option value="Sungai">Sungai</s:option>
                                    </s:select></td>
                            </tr>
                            <tr>
                                <td>Nama Jalan/Keretapi/Pantai/Pekan/Sungai :</td>
                                <td><s:text name="hakmilikPermohonan.jarakDariNama" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.jarakDariNama}" id="namaJ" size="40"/></td>
                            </tr>
                            <tr>
                                <td>Jarak Dari Kediaman :</td>
                                <td><s:text name="hakmilikPermohonan.jarakDariKediaman" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.jarakDariKediaman}" maxlength="6" onkeyup="validateNumber(this,this.value);" id="JarakKediaman"/>
                                    <s:select name="jarakDariKediamanUom.kod" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.jarakDariKediamanUom.kod}" id="uJarakKUOM">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:option value="KM">Kilometer</s:option>
                                        <s:option value="JM">Meter</s:option>
                                    </s:select></td>
                            </tr>
                            <tr>
                                <td style="text-align:center;" colspan="3">      
                                    <s:submit name="simpan" value="Simpan" class="btn" onclick="NoPrompt();"/>
                                    <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                                </td>
                            </tr>  
                        </table>      
                        <br/>
                    </c:when>    
                </c:choose>
            </fieldset>
        </div>
    </s:form>
</body>