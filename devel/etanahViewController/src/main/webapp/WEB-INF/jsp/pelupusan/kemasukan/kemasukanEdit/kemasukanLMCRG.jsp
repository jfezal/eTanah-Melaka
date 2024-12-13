<%-- 
    Document   : kemasukanLMCRG
    Created on : Feb 21, 2013, 11:14:35 AM
    Author     : Shazwan
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
        
    function test1()
        {
        NoPrompt();
        self.close();
        opener.refreshV2MaklumatTanah();

        } 
        
        function refreshpage()
        {
        NoPrompt();
        self.close();
        opener.refreshV2MaklumatTanah();

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
        
    function validateForm(){
      
        if(document.form2.mpb.value=="")
        {
            alert("Sila pilih Mukim atau Pekan atau Bandar");
            return false;
        }
        if(document.form2.tempat.value=="")
        {
            alert("Sila masukkan Nama Tempat");
            return false;
        }
        if(document.form2.noLot.value=="")
        {
            alert("Sila masukkan No Lot");
            return false;
        }
        if(document.form2.noLitho.value=="")
        {
            alert("Sila masukkan No Pelan Syit Piawai");
            return false;
        }
        if(document.form2.tandaBlok.value=="")
        {
            alert("Sila masukkan No Tanda Blok");
            return false;
        }
        if(document.form2.Luas.value=="")
        {
            alert("Sila masukkan Luas");
            return false;
        }
        if(document.form2.catatan.value=="")
        {
            alert("Sila masukkan Logam/Mineral yang Dicari");
            return false;
        }
  
        return true;

    }
    
    function changeMukim() {
//        NoPrompt();
             var mpb = $('#mpb').val();
             var kd = $('#kd').val();
             var tempat = $('#tempat').val();
             var Luas = $('#Luas').val();
             var kULuas = $('#kULuas').val();
             var kodlot = $('#kodlot').val();
             var noLot = $('#noLot').val();
             var Jarak = $('#Jarak').val();
             var uJarak = $('#uJarak').val();
             var jarakDari = $('#jarakDari').val();
             var katTanahPilihan = $('#_kodKatTanah').val();
             var kodSeksyen = $('#_kodSeksyen2').val();

        var url = '${pageContext.request.contextPath}/pelupusan/maklumat_tanahV3?carianIndependentMukim&katTanahPilihan='
            +katTanahPilihan+'&mpb='+mpb+'&tempat='+tempat+'&Luas='+Luas+'&kULuas='+kULuas+'&kodlot='+kodlot+'&noLot='+noLot+'&Jarak='+Jarak+'&uJarak='+uJarak+'&jarakDari='+jarakDari+'&kodSeksyen='+kodSeksyen+'&kd='+kd;
 
        $.ajax({
            url:url,
            success:function(data){
                $('#page_div').html(data);
            }
        });
    }
        function doSomething2() {
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
    <c:if test="${actionBean.negeri eq '05'}">
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
            var noLitho = $('#noLitho').val();
            var url = '${pageContext.request.contextPath}/pelupusan/maklumat_tanahV3?carianIndependent&katTanahPilihan='
                +katTanahPilihan+'&mpb='+mpb+'&tempat='+tempat+'&Luas='+Luas+'&kULuas='+kULuas+'&kodlot='+kodlot+'&noLot='+noLot+'&Jarak='+Jarak+'&uJarak='+uJarak+'&jarakDari='+jarakDari+'&kodSeksyen='+kodSeksyen+'&noLitho='+noLitho+'&kd='+kd;
            
        </c:if>
    </c:if>
    <c:if test="${actionBean.negeri eq '05'}">
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PBMT'}">
            var url = '${pageContext.request.contextPath}/pelupusan/maklumat_tanahV3?carianIndependent&katTanahPilihan='
                +katTanahPilihan+'&mpb='+mpb+'&tempat='+tempat+'&Luas='+Luas+'&kULuas='+kULuas+'&kodlot='+kodlot+'&noLot='+noLot+'&Jarak='+Jarak+'&uJarak='+uJarak+'&jarakDari='+jarakDari+'&kodSeksyen='+kodSeksyen;
        </c:if>
    </c:if>
        <c:if test="${actionBean.negeri eq '04'}">
        var url = '${pageContext.request.contextPath}/pelupusan/maklumat_tanahV3?carianIndependent&katTanahPilihan='
            +katTanahPilihan+'&mpb='+mpb+'&tempat='+tempat+'&Luas='+Luas+'&kULuas='+kULuas+'&kodlot='+kodlot+'&noLot='+noLot+'&Jarak='+Jarak+'&uJarak='+uJarak+'&jarakDari='+jarakDari+'&kodSeksyen='+kodSeksyen;
    </c:if>
        $.ajax({
            url:url,
            success:function(data){
                $('#page_div').html(data);
            }
        });
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
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <c:choose>
                    <c:when test="${actionBean.negeri eq '04'}">                        
                        <table class="tablecloth" align="center">
                            <tr>
                                <td>Daerah :</td>
                                <td>
                                    <s:select name="kodDaerah" value="${actionBean.kodDaerah}" id="kd" onclick="changeMukim(this.form);">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" />
                                    </s:select>
                                </td>
                            </tr>
                            <s:text name="forBPM" value="${actionBean.forBPM}"/>
                            <c:if test="${actionBean.forBPM}">
                                <tr>
                                    <td><font color="red" size="4">*</font>Mukim/Pekan/Bandar :</td>
                                    <td>
                                        <s:select name="bandarPekanMukimBaru.kod" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.bandarPekanMukimBaru.kod}" id="mpb" onchange="doSomething2(this.form);">
                                            <s:option value="">Sila Pilih</s:option>
                                            <s:options-collection collection="${actionBean.senaraiKodBPM}" label="nama" value="kod" />
                                        </s:select>
                                    </td>
                                </tr>
                            </c:if>
                            <s:hidden name="test" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.kodSeksyen.kod}" style="width:300px;" id="_kodSeksyen2"/>
                            <c:if test="${actionBean.forSeksyen}">
                                <tr>
                                    <td>Seksyen :</td>
                                    <td>
                                        <s:select name="kodSeksyen.kod" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.kodSeksyen.kod}"style="width:300px;" id="_kodSeksyen">
                                            <s:option value="">-- Sila Pilih --</s:option>
                                            <s:options-collection collection="${actionBean.senaraiKodSeksyen}" value="kod" label="nama" />
                                        </s:select>
                                    </td>
                                </tr>
                            </c:if>
                            <tr>
                                <td><font color="red" size="4">*</font>Tempat/Lokasi Tanah Dipohon :</td>
                                <td>
                                    <s:textarea name="disHakmilikPermohonan.hakmilikPermohonan.lokasi" rows="5" cols="70" id="tempat" class="normal_text"/>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Nombor Lot/PT :</td>
                                <td>
                                    <s:select name="kodLot.kod" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.lot.kod}" id="kodlot">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiLotPelupusan}" label="nama" value="kod" />
                                    </s:select>
                                    <s:text name="disHakmilikPermohonan.hakmilikPermohonan.noLot" onkeyup="validateNumber(this,this.value);" id="noLot"/>&nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>No. Pelan Syit Piawai :</td>
                                <td>
                                    <s:text name="disPermohonanLaporanPelan.noLitho" size="20" id="noLitho"/>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Penandaan blok (unit):</td>
                                <td>
                                    <s:text name="disHakmilikPermohonan.tandaBlok" size="30" id="tandaBlok"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2"><center>
                                        (Suatu peta berdasarkan kepada Siri (Baru) Peta Topografi Malaysia berskala 1 : 63,360 <br>
                                        atau 1 : 50,000 menunjukkan kawasan tersebut handaklah dikepilkan)
                                    </center>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Keluasan :</td>
                                <td>
                                    <s:text name="disHakmilikPermohonan.hakmilikPermohonan.luasTerlibat" formatPattern="#,###,##0.0000" maxlength="15" onkeyup="validateNumber(this,this.value);" id="Luas"/>
                                    <s:select name="disHakmilikPermohonan.keluasanUOM" id="uJarakKUOM">
                                        <s:option value="">Sila Pilih</s:option>
                                        <%--<s:option value="JK">Kaki</s:option>--%>
                                        <s:option value="H">Hektar</s:option>
                                        <s:option value="M">Meter Persegi</s:option>
                                    </s:select>
                                    <s:hidden name="kodUnitLuas.kod" value="H"/>
                                </td>
                            </tr>
                            <tr>
							
                                <td style="text-align:center;" colspan="3">      
                                    <s:submit name="simpan" value="Simpan" class="btn" onclick="test1();"/>
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