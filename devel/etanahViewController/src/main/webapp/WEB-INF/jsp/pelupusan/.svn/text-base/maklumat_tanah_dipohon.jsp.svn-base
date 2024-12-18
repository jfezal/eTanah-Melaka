<%--
    Document   : maklumat_tanah_dipohon
    Created on : May 19, 2010, 1:31:32 PM
    Author     : sitifariza.hanim
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    $(document).ready( function() {   
        if('${actionBean.permohonan.kodUrusan.kod}' == "PLPS" || '${actionBean.permohonan.kodUrusan.kod}' == "PLPTD" || '${actionBean.permohonan.kodUrusan.kod}' == "RLPS" || '${actionBean.permohonan.kodUrusan.kod}' == "RLPSK" || '${actionBean.permohonan.kodUrusan.kod}' == "LPSP" ){
            if('${actionBean.ppi.kodItemPermit.kod}' == "LN"){
                
                $('#catatanKwsn').show();
            }else
                $('#catatanKwsn').hide();
               
        }
                
        if('${actionBean.permohonan.kodUrusan.kod}' == "PPTR"){
            if('${actionBean.ppi.kodItemPermit.kod}' == "99"){
                
                $('#catatanKwsn').show();
            }else
                $('#catatanKwsn').hide();
               
        }
        if('${actionBean.permohonan.kodUrusan.kod}' == "LMCRG"){
            if('${actionBean.hakmilikPermohonan.jarakDari}' == "Pantai" || '${actionBean.hakmilikPermohonan.jarakDari}' == "Pekan" || '${actionBean.hakmilikPermohonan.jarakDari}' == "Sungai"){
                $('#namaJarak').show();
            }
            else {
                $('#namaJarak').hide();
            }
               
        }
        if('${actionBean.permohonan.kodUrusan.kod}' == "PRIZ"){
            if('${actionBean.tanahR}' == "99"){
                $('#catatanJenisRizab').show();
            }else{
                $('#catatanJenisRizab').hide();
            }
               
        }
        
        
        var len = $(".daerah").length;
       
        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                window.open("${pageContext.request.contextPath}/pembangunan/maklumat_hakmilik?hakmilikDetail&idHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }
         
    });

    function validateForm(){
      
        if(document.form2.mpb.value=="")
        {
            alert("Sila pilih Mukim atau Pekan atau Bandar");
            return false;
        }
        //        var kodSeksyen = $('#_kodSeksyen').val();
        //        <c:if test="${fn:length(actionBean.senaraiKodSeksyen) > 0}">
        //            if(kodSeksyen == ''){
        //                alert("Sila masukkan Seksyen");
        //                return false;
        //            }
        //        </c:if>
           
        //            if(document.form2.urusan.value != "PPBB" && document.form2.urusan.value != "PBPTG" && document.form2.urusan.value != "PBPTD"){
        //        if(document.form2.tempat.value=="")
        //        {
        //            alert("Sila masukkan Nama Tempat");
        //            return false;
        //        }
        //            }
        
        //      if(document.form2.Luas.value=="")
        //        {
        //            alert("Sila masukkan Luas");
        //            return false;
        //        }
        //        if(document.form2.kULuas.value=="")
        //        {
        //            alert("Sila masukkan kULuas");
        //            return false;
        //        }
        if($('urusan').val() == "PBMT")
        {
            if(document.form2._kodKatTanah.value=="")
            {
                alert("Sila masukkan KatTanah");
                return false;
            }
        }
        if(${actionBean.permohonan.kodUrusan.kod eq 'LMCRG'})
        {
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
        }

        if(${actionBean.negeri eq '04'})
        {
            if(${actionBean.permohonan.kodUrusan.kod eq 'PRIZ' })
            {
                if(document.form2.namaPenjaga.value=="")
                {
                    alert("Sila masukkan nama pegawai pengawal");
                    return false;
                }
            }
        }
   
    return true;

}

function validate_carian_hakmilik(){
    var a = $('#_kodKatTanah').val();
    var b = $('#_kodKegunaTanah').val();

    var f1 = true;
    var f2 = true;

    if (a == '' || b == ''){
        f1 = false;
    }

    if (!f1 && !f2) {
        alert('Sila isi maklumat dengan tepat.');
        return false;
    }
    return true;
}
    
function openLain_lain(val){
    if(val == 'LN'){
        $('#catatanKwsn').show();
    }else{
        $('#catatanKwsn').hide();
    }
}
function openJenisRizabLain_lain(val){
    //      alert(val);
    if(val == '99'){
        $('#catatanJenisRizab').show();
    }else{
        $('#catatanJenisRizab').hide();
    }
}
function doSomething() {
    var mpb = $('#mpb').val();
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
    <c:if test="${actionBean.negeri eq '05'}">
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
            var noLitho = $('#noLitho').val();
            var url = '${pageContext.request.contextPath}/pelupusan/maklumat_tanah?carianIndependent&katTanahPilihan='
                +katTanahPilihan+'&mpb='+mpb+'&tempat='+tempat+'&Luas='+Luas+'&kULuas='+kULuas+'&kodlot='+kodlot+'&noLot='+noLot+'&Jarak='+Jarak+'&uJarak='+uJarak+'&jarakDari='+jarakDari+'&kodSeksyen='+kodSeksyen+'&noLitho='+noLitho;
            
        </c:if>
    </c:if>
    <c:if test="${actionBean.negeri eq '05'}">
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PBMT'}">
            var url = '${pageContext.request.contextPath}/pelupusan/maklumat_tanah?carianIndependent&katTanahPilihan='
                +katTanahPilihan+'&mpb='+mpb+'&tempat='+tempat+'&Luas='+Luas+'&kULuas='+kULuas+'&kodlot='+kodlot+'&noLot='+noLot+'&Jarak='+Jarak+'&uJarak='+uJarak+'&jarakDari='+jarakDari+'&kodSeksyen='+kodSeksyen;
        </c:if>
    </c:if>
        <c:if test="${actionBean.negeri eq '04'}">
        var url = '${pageContext.request.contextPath}/pelupusan/maklumat_tanah?carianIndependent&katTanahPilihan='
            +katTanahPilihan+'&mpb='+mpb+'&tempat='+tempat+'&Luas='+Luas+'&kULuas='+kULuas+'&kodlot='+kodlot+'&noLot='+noLot+'&Jarak='+Jarak+'&uJarak='+uJarak+'&jarakDari='+jarakDari+'&kodSeksyen='+kodSeksyen;
    </c:if>
        $.ajax({
            url:url,
            success:function(data){
                $('#page_div').html(data);
            }
        });
    <%-- alert('katTanahPilihan='
         +katTanahPilihan+'&mpb='+mpb+'&tempat='+tempat+'&Luas='+Luas+'&kULuas='+kULuas+'&kodlot='+kodlot+'&noLot='+noLot+'&Jarak='+Jarak+'&uJarak='+uJarak+'&jarakDari='+jarakDari);--%>
        
        
 
         }
         function doSomething2() {
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
            var url = '${pageContext.request.contextPath}/pelupusan/maklumat_tanah?carianIndependent&katTanahPilihan='
                +katTanahPilihan+'&mpb='+mpb+'&tempat='+tempat+'&Luas='+Luas+'&kULuas='+kULuas+'&kodlot='+kodlot+'&noLot='+noLot+'&Jarak='+Jarak+'&uJarak='+uJarak+'&jarakDari='+jarakDari+'&kodSeksyen='+kodSeksyen+'&noLitho='+noLitho+'&kd='+kd;
            
        </c:if>
    </c:if>
    <c:if test="${actionBean.negeri eq '05'}">
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PBMT'}">
            var url = '${pageContext.request.contextPath}/pelupusan/maklumat_tanah?carianIndependent&katTanahPilihan='
                +katTanahPilihan+'&mpb='+mpb+'&tempat='+tempat+'&Luas='+Luas+'&kULuas='+kULuas+'&kodlot='+kodlot+'&noLot='+noLot+'&Jarak='+Jarak+'&uJarak='+uJarak+'&jarakDari='+jarakDari+'&kodSeksyen='+kodSeksyen;
        </c:if>
    </c:if>
        <c:if test="${actionBean.negeri eq '04'}">
        var url = '${pageContext.request.contextPath}/pelupusan/maklumat_tanah?carianIndependent&katTanahPilihan='
            +katTanahPilihan+'&mpb='+mpb+'&tempat='+tempat+'&Luas='+Luas+'&kULuas='+kULuas+'&kodlot='+kodlot+'&noLot='+noLot+'&Jarak='+Jarak+'&uJarak='+uJarak+'&jarakDari='+jarakDari+'&kodSeksyen='+kodSeksyen;
    </c:if>
        $.ajax({
            url:url,
            success:function(data){
                $('#page_div').html(data);
            }
        });
    <%-- alert('katTanahPilihan='
         +katTanahPilihan+'&mpb='+mpb+'&tempat='+tempat+'&Luas='+Luas+'&kULuas='+kULuas+'&kodlot='+kodlot+'&noLot='+noLot+'&Jarak='+Jarak+'&uJarak='+uJarak+'&jarakDari='+jarakDari);--%>
        
        
 
         }
         function changeMukim() {
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
        <c:if test="${actionBean.negeri eq '04'}">
        var url = '${pageContext.request.contextPath}/pelupusan/maklumat_tanah?carianIndependentMukim&katTanahPilihan='
            +katTanahPilihan+'&mpb='+mpb+'&tempat='+tempat+'&Luas='+Luas+'&kULuas='+kULuas+'&kodlot='+kodlot+'&noLot='+noLot+'&Jarak='+Jarak+'&uJarak='+uJarak+'&jarakDari='+jarakDari+'&kodSeksyen='+kodSeksyen+'&kd='+kd;
    </c:if>
        $.ajax({
            url:url,
            success:function(data){
                $('#page_div').html(data);
            }
        });
    }
    function cariPopup(){
        // alert("search:"+index);
        var url = '${pageContext.request.contextPath}/pelupusan/maklumat_tanah?search';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
    }
    function keluarNama(){
 
        if('${actionBean.permohonan.kodUrusan.kod}' == "LMCRG"){
            //            if(document.form2.jarakDari.value == "Pantai" || document.form2.jarakDari.value == "Pekan" || document.form2.jarakDari.value == "Sungai"){
            //                  $('#namaJarak').show();
            //            }
            //            else {
            //                 $('#namaJarak').hide();
            //            }
            $('#namaJarak').show();
        }
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


</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form name="form2" id="form2" beanclass="etanah.view.stripes.pelupusan.MaklumatTanahActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="urusan" id="urusan" value="${actionBean.permohonan.kodUrusan.kod}"/>
    <s:hidden name="forSeksyen" id="forSeksyen"/>
    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG' or actionBean.permohonan.kodUrusan.kod eq 'PJLB' }">
        <s:hidden name="forBPM" id="forBPM"/>
        <s:hidden name="kodBpm" id="kodBpm"/>
        <s:hidden name="kodDaerah" id="kodDaerah"/>
    </c:if>
    <c:if test="${hakmilik eq false && edit eq true}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Tanah Dipohon </legend>
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'LMCRG'}">
                    <p>
                        <label>Daerah :</label>
                        ${actionBean.permohonan.cawangan.daerah.nama}
                    </p>
                    <p>
                       <label><font color="red">*</font>Mukim/Pekan/Bandar :</label>
                        <c:if test="${actionBean.forSeksyen}">

                        </c:if> 
                       
                       <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJLB'}">
                        <p>
                            <label><font color="red">*</font>Mukim/Pekan/Bandar :</label>
                            <s:select name="bandarPekanMukimBaru.kod" value="${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.kod}" id="mpb" onchange="doSomething(this.form);">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraiKodBPMLMCRGPJLB}" label="nama" value="kod" />
                            </s:select>
                        </p>
                    </c:if>
                       
                       
                       <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PJLB'}">
                        <s:select name="bandarPekanMukimBaru.kod" value="${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.kod}" id="mpb" onchange="doSomething(this.form);">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${actionBean.senaraiKodBPM}" label="nama" value="kod" />
                        </s:select>
                    </p>
                </c:if>
                    <s:hidden name="test" value="${actionBean.hakmilikPermohonan.kodSeksyen.kod}"style="width:300px;" id="_kodSeksyen2"/>
                    <c:if test="${actionBean.forSeksyen}">
                        <p ><label>Seksyen :</label>
                            <s:select name="kodSeksyen.kod" value="${actionBean.hakmilikPermohonan.kodSeksyen.kod}"style="width:300px;" id="_kodSeksyen">
                                <s:option value="">-- Sila Pilih --</s:option>
                                <s:options-collection collection="${actionBean.senaraiKodSeksyen}" value="kod" label="nama" />

                            </s:select>
                        </p>
                    </c:if>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG'}">
                    <p>
                        <label>Daerah :</label>
                        <s:select name="kodDaerah" value="${actionBean.kodDaerah}" id="kd" onchange="changeMukim(this.form);">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" />
                        </s:select>
                        <%--${actionBean.permohonan.cawangan.daerah.nama}--%>
                    </p>
                    <c:if test="${actionBean.forBPM}">
                        <p>
                            <label><font color="red">*</font>Mukim/Pekan/Bandar :</label>
                            <s:select name="bandarPekanMukimBaru.kod" value="${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.kod}" id="mpb" onchange="doSomething2(this.form);">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraiKodBPMLMCRGPJLB}" label="nama" value="kod" />
                            </s:select>
                        </p>
                    </c:if>
                    <s:hidden name="test" value="${actionBean.hakmilikPermohonan.kodSeksyen.kod}"style="width:300px;" id="_kodSeksyen2"/>
                    <c:if test="${actionBean.forSeksyen}">
                        <p ><label>Seksyen :</label>
                            <s:select name="kodSeksyen.kod" value="${actionBean.hakmilikPermohonan.kodSeksyen.kod}"style="width:300px;" id="_kodSeksyen">
                                <s:option value="">-- Sila Pilih --</s:option>
                                <s:options-collection collection="${actionBean.senaraiKodSeksyen}" value="kod" label="nama" />

                            </s:select>
                        </p>
                    </c:if>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPBB' && actionBean.permohonan.kodUrusan.kod ne 'PBPTG' && actionBean.permohonan.kodUrusan.kod ne 'PBPTD' && actionBean.permohonan.kodUrusan.kod ne 'LMCRG' && actionBean.permohonan.kodUrusan.kod ne 'PCRG' && actionBean.permohonan.kodUrusan.kod ne 'PLPTD'}">
                    <p>
                        <label>Tempat/Lokasi Tanah Dipohon:</label>
                        <s:textarea name="hakmilikPermohonan.lokasi" rows="5" cols="70" id="tempat" class="normal_text"/>
                    </p>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB' || actionBean.permohonan.kodUrusan.kod eq 'PBPTG' || actionBean.permohonan.kodUrusan.kod eq 'PBPTD' || actionBean.permohonan.kodUrusan.kod eq 'LMCRG' || actionBean.permohonan.kodUrusan.kod eq 'PCRG' || actionBean.permohonan.kodUrusan.kod eq 'PLPTD'}">
                    <p>
                        <label><font color="red">*</font>Tempat/Lokasi Tanah Dipohon :</label>
                        <s:textarea name="hakmilikPermohonan.lokasi" rows="5" cols="70" id="tempat" class="normal_text"/>
                    </p>
                </c:if>

                <c:if test="${actionBean.negeri eq '04'}">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRIZ'|| actionBean.permohonan.kodUrusan.kod eq 'PBRZ' || actionBean.permohonan.kodUrusan.kod eq 'RAYT'}">

                        <p>
                            <label>Nombor Lot  :</label>
                            <s:select name="kodLot.kod" value="${actionBean.hakmilikPermohonan.lot.kod}" id="kodlot">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiLotPelupusan}" label="nama" value="kod" />
                            </s:select>
                            <s:text name="hakmilikPermohonan.noLot" onkeyup="validateNumber(this,this.value);" id="noLot"/>&nbsp;
                        </p>

                        <p>
                            <label>Luas Dipohon:</label>
                            <s:text name="mohonTrizab.luasTerlibat" formatPattern="#,###,##0.0000" maxlength="15" onkeyup="validateNumber(this,this.value);" id="Luas"/>
                            <s:select name="keluasanUOM" id="kULuas">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="H">Hektar</s:option>
                                <s:option value="M">Meter Persegi</s:option>
                            </s:select>
                        </p>

                        <p>
                            <label><em>*</em>Pegawai Pengawal :</label>
                            <s:text name="mohonTrizab.namaPenjaga" id="namaPenjaga" size="50"/>
                        </p>

                        <p>
                            <label>Jenis Rizab:</label>
                            <s:select name="tanahR" style="width:250px" id="tanahR" onchange="openJenisRizabLain_lain(this.value)">
                                <s:option value="0">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodRizab}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <div  id="catatanJenisRizab">
                            <p>
                                <label><font color="red"></font>Jika Lain-lain (Sila Nyatakan) :</label>
                                <s:textarea name="mohonTrizab.catatan" id="catatan"  rows="5" cols="40"/>
                            </p>
                        </div>
                    </c:if>
                        <p>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPTR'}">
                                <label>Tujuan:</label>
                                <s:textarea name="permohonan.sebab" rows="5" cols="50"/>
                            </c:if>    
                        
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRIZ'}">
                            <p align="center"><label></label>
                            <br>
                            <s:button name="simpanHakmilikPRIZMLK" id="save" value="Simpan" class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');"/>
                            </p>
                        </c:if>
                        <%-- <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBRZ'}">
                            <p align="center"><label></label>
                            <br>
                            <s:button name="simpanHakmilikPBRZMLK" id="save" value="Simpan" class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');"/>
                            </p>
                        </c:if> --%>
                    </c:if>

                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'LMCRG' && actionBean.permohonan.kodUrusan.kod ne 'PCRG'}">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPRU' and actionBean.permohonan.kodUrusan.kod ne 'PJBTR' and actionBean.permohonan.kodUrusan.kod ne 'PPTR' and actionBean.permohonan.kodUrusan.kod ne 'PRIZ' and actionBean.permohonan.kodUrusan.kod ne 'PBRZ' and actionBean.permohonan.kodUrusan.kod ne 'RAYT'}">
                        <p>
                            <label>Luas :</label>
                            <s:text name="hakmilikPermohonan.luasTerlibat" formatPattern="#,###,##0.0000" maxlength="10" onkeyup="validateNumber(this,this.value);" id="Luas"/>
                            <s:select name="keluasanUOM" id="kULuas">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="H">Hektar</s:option>
                                <s:option value="M">Meter Persegi</s:option>
                            </s:select>
                        </p>
                    </c:if>

                    <c:if test="${actionBean.negeri eq '05'}">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRIZ'}">
                            <p>
                                <label>Luas :</label>
                                <s:text name="hakmilikPermohonan.luasTerlibat" formatPattern="#,###,##0.0000" maxlength="10" onkeyup="validateNumber(this,this.value);" id="Luas"/>
                                <s:select name="keluasanUOM" id="kULuas">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option value="H">Hektar</s:option>
                                    <s:option value="M">Meter Persegi</s:option>
                                </s:select>
                            </p>
                        </c:if>
                            
                            
                    </c:if>

                    <c:if test="${actionBean.negeri eq '04'}">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR' || actionbean.permohonan.kodUrusan.kod eq 'MPJLB'}">

                            <p>
                                <label>Luas Dipohon:</label>
                                <s:text name="mohonTrizab.luasTerlibat" formatPattern="#,###,##0.0000" maxlength="15" onkeyup="validateNumber(this,this.value);" id="Luas"/>
                                <s:select name="keluasanUOM" id="kULuas">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option value="H">Hektar</s:option>
                                    <s:option value="M">Meter Persegi</s:option>
                                </s:select>
                            </p>
                            <p>
                                <label>Jenis Rizab:</label>
                                <s:select name="tanahR" style="width:250px" id="tanahR" onchange="openJenisRizabLain_lain(this.value)">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodRizab}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                            <div  id="catatanJenisRizab">
                                <p>
                                    <label><font color="red"></font>Jika Lain-lain (Sila Nyatakan) :</label>
                                    <s:textarea name="mohonTrizab.catatan" id="catatan"  rows="5" cols="40"/>
                                </p> 
                            </div>
                            <p>
                                <label>Tempoh Pajakan Dipohon:</label>
                                <s:text name="hakmilikPermohonan.tempohPajakan" maxlength="3" onkeyup="validateNumber(this,this.value);" /> &nbsp; Tahun
                            </p>    
                            <p>
                                <label>Tujuan:</label>
                                <s:textarea name="permohonan.sebab" rows="5" cols="50"/>
                            </p>
                        </c:if>
                    </c:if>

                    <c:if test="${actionBean.negeri eq '05'}">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR' || actionbean.permohonan.kodUrusan.kod eq 'MPJLB'}">

                            <p>
                                <label>Luas Dipohon:</label>
                                <s:text name="mohonTrizab.luasTerlibat" formatPattern="#,###,##0.0000" maxlength="15" onkeyup="validateNumber(this,this.value);" id="Luas"/>
                                <s:select name="keluasanUOM" id="kULuas">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option value="H">Hektar</s:option>
                                    <s:option value="M">Meter Persegi</s:option>
                                </s:select>
                            </p>
                            <p>
                                <label>Jenis Rizab:</label>
                                <s:select name="tanahR" style="width:250px" id="tanahR" onchange="openJenisRizabLain_lain(this.value)">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodRizab}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                            <div  id="catatanJenisRizab">
                                <p>
                                    <label><font color="red"></font>Jika Lain-lain (Sila Nyatakan) :</label>
                                    <s:textarea name="mohonTrizab.catatan" id="catatan"  rows="5" cols="40"/>
                                </p>
                            </div>
                            <p>
                                <label>Tempoh Pajakan Dipohon:</label>
                                <s:text name="hakmilikPermohonan.tempohPajakan" maxlength="3" onkeyup="validateNumber(this,this.value);" /> &nbsp; Tahun
                            </p>
                            <p>
                                <label>Tujuan:</label>
                                <s:textarea name="permohonan.sebab" rows="5" cols="50"/>
                            </p>
                        </c:if>
                            
                           
                    </c:if>


                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJBTR'}">
                        <p>
                            <label>Keluasan Terlibat (Isipadu) :</label>
                            <s:text name="hakmilikPermohonan.luasTerlibat" formatPattern="#,###,##0.0000" maxlength="15" onkeyup="validateNumber(this,this.value);" id="Luas"/>
                            <s:select name="keluasanUOM" id="kULuas">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="MP">Meter Padu</s:option>
                            </s:select>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU'}">
                        <p>
                            <label>Keluasan Terlibat:</label>
                            <s:text name="hakmilikPermohonan.luasTerlibat" formatPattern="#,###,##0.0000" maxlength="15" onkeyup="validateNumber(this,this.value);" id="Luas"/>
                            <s:select name="keluasanUOM" id="kULuas">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="MP">Meter Padu</s:option>
                            </s:select>
                        </p>
                    </c:if>
                </c:if>
                <c:if test="${actionBean.negeri eq '05'}">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PBGSA'}">
                        <p>
                            <label>Nombor Lot  :</label>
                            <s:select name="kodLot.kod" value="${actionBean.hakmilikPermohonan.lot.kod}" id="kodlot">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiLotPelupusan}" label="nama" value="kod" />
                            </s:select>
                            <s:text name="hakmilikPermohonan.noLot" onkeyup="validateNumber(this,this.value);" id="noLot"/>&nbsp;
                        </p>
                    </c:if>
                    
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRMMK'}">

<!--                            <p>
                                <label>Nombor Lot  :</label>
                                <s:select name="kodLot.kod" value="${actionBean.hakmilikPermohonan.lot.kod}" id="kodlot">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiLotPelupusan}" label="nama" value="kod" />
                                </s:select>
                                <s:text name="hakmilikPermohonan.noLot" onkeyup="validateNumber(this,this.value);" id="noLot"/>&nbsp;
                            </p>-->

                            <p>
                                <label>Luas Dipohon:</label>
                                <s:text name="mohonTrizab.luasTerlibat" formatPattern="#,###,##0.0000" maxlength="15" onkeyup="validateNumber(this,this.value);" id="Luas"/>
                                <s:select name="keluasanUOM" id="kULuas">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option value="H">Hektar</s:option>
                                    <s:option value="M">Meter Persegi</s:option>
                                </s:select>
                            </p>

                            
                            <p>
                                <label>Jenis Rizab:</label>
                                <s:select name="tanahR" style="width:250px" id="tanahR" onchange="openJenisRizabLain_lain(this.value)">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodRizab}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                            <div  id="catatanJenisRizab">
                                <p>
                                    <label><font color="red"></font>Jika Lain-lain (Sila Nyatakan) :</label>
                                    <s:textarea name="mohonTrizab.catatan" id="catatan"  rows="5" cols="40"/>
                                </p>
                            </div>
                                
                            <p>
                                <label><em>*</em>Pegawai Pengawal :</label>
                                <s:text name="mohonTrizab.namaPenjaga" id="namaPenjaga" size="50"/>
                            </p>
                            <p>
                                <label></label>
                                <s:button name="simpanHakmilikPRIZMLK" id="save" value="Simpan" class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');"/>
                            </p>
                    
                        </c:if> 
                </c:if>
                <c:if test="${actionBean.negeri eq '04'}">
                    <c:if test="${!(actionBean.permohonan.kodUrusan.kod eq 'LMCRG')}">
                        <c:if test="${!(actionBean.permohonan.kodUrusan.kod eq 'PPTR' || actionBean.permohonan.kodUrusan.kod eq 'PRIZ' || actionBean.permohonan.kodUrusan.kod eq 'PBRZ' || actionBean.permohonan.kodUrusan.kod eq 'RAYT')}">
                            <p>
                                <label><font color="red">*</font>Nombor Lot/PT:</label>
                                <s:select name="kodLot.kod" value="${actionBean.hakmilikPermohonan.lot.kod}" id="kodlot">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiLotPelupusan}" label="nama" value="kod" />
                                </s:select>
                                <s:text name="hakmilikPermohonan.noLot" onkeyup="validateNumber(this,this.value);" id="noLot"/>&nbsp;
                            </p>
                        </c:if>


                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG'}">
                        <p>
                            <label><font color="red">*</font>Nombor Lot/PT :</label>
                            <s:select name="kodLot.kod" value="${actionBean.hakmilikPermohonan.lot.kod}" id="kodlot">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiLotPelupusan}" label="nama" value="kod" />
                            </s:select>
                            <s:text name="hakmilikPermohonan.noLot" onkeyup="validateNumber(this,this.value);" id="noLot"/>&nbsp;
                        </p> 
                    </c:if>
                </c:if>
                <c:if test="${actionBean.negeri eq '05'}">
                      
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRIZ'}">
                        <p>
                            <label><font color="red">*</font>Nombor Lot/PT :</label>
                            <s:select name="kodLot.kod" value="${actionBean.hakmilikPermohonan.lot.kod}" id="kodlot">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiLotPelupusan}" label="nama" value="kod" />
                            </s:select>
                            <s:text name="hakmilikPermohonan.noLot" onkeyup="validateNumber(this,this.value);" id="noLot"/>&nbsp;
                        </p>
                    </c:if>

                </c:if>

                <c:if test="${actionBean.negeri eq '04'}">
                    <%--<c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPTR' and actionBean.permohonan.kodUrusan.kod ne 'PRIZ'}">--%>
                    <c:if test="${!(actionBean.permohonan.kodUrusan.kod eq 'PPTR' || actionBean.permohonan.kodUrusan.kod eq 'PRIZ' || actionBean.permohonan.kodUrusan.kod eq 'PBRZ' || actionBean.permohonan.kodUrusan.kod eq 'RAYT')}">
                        <p>
                            <label>Jarak:</label>
                            <s:text name="hakmilikPermohonan.jarak" maxlength="6" onkeyup="validateNumber(this,this.value);" id="Jarak"/>
                            <s:select name="unitJarak.kod" value="${actionBean.hakmilikPermohonan.unitJarak.kod}" id="uJarak">
                                <s:option value="">Sila Pilih</s:option>
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
                        </p>
                        <p>
                            <label>Nama Jalan/Keretapi/Pantai/Pekan/Sungai :</label>
                            <s:text name="hakmilikPermohonan.jarakDariNama" id="namaJ" size="40"/>

                        </p>
                        <p>
                            <label>Jarak Dari Kediaman:</label>
                            <s:text name="hakmilikPermohonan.jarakDariKediaman" maxlength="6" onkeyup="validateNumber(this,this.value);" id="JarakKediaman"/>
                            <s:select name="jarakDariKediamanUom.kod" value="${actionBean.hakmilikPermohonan.jarakDariKediamanUom.kod}" id="uJarakKUOM">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="KM">Kilometer</s:option>
                                <s:option value="JM">Meter</s:option>
                            </s:select>
                        </p> 
                    </c:if>                        
                </c:if>

                <c:if test="${actionBean.negeri eq '05'}">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRIZ'}">
                        <p>
                            <label>Jarak:</label>
                            <s:text name="hakmilikPermohonan.jarak" maxlength="6" onkeyup="validateNumber(this,this.value);" id="Jarak"/>
                            <s:select name="unitJarak.kod" value="${actionBean.hakmilikPermohonan.unitJarak.kod}" id="uJarak">
                                <s:option value="">Sila Pilih</s:option>
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
                        </p>
                        <p>
                            <label>Nama Jalan/Keretapi/Pantai/Pekan/Sungai :</label>
                            <s:text name="hakmilikPermohonan.jarakDariNama" id="namaJ" size="40"/>

                        </p>
                        <p>
                            <label>Jarak Dari Kediaman:</label>
                            <s:text name="hakmilikPermohonan.jarakDariKediaman" maxlength="6" onkeyup="validateNumber(this,this.value);" id="JarakKediaman"/>
                            <s:select name="jarakDariKediamanUom.kod" value="${actionBean.hakmilikPermohonan.jarakDariKediamanUom.kod}" id="uJarakKUOM">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="KM">Kilometer</s:option>
                                <s:option value="JM">Meter</s:option>
                            </s:select>
                        </p> 
                    </c:if>
                </c:if>


                <c:if test="${actionBean.negeri eq '05'}">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                        <p>
                            <label for="nolitho">No. Pelan Syit Piawai :</label>
                            <s:text name="noLitho" size="20" id="noLitho"/>
                        </p>
                    </c:if>                     
                </c:if>

                <c:if test="${actionBean.negeri eq '04'}">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG'}">
                        <p>
                            <label for="nolitho"><font color="red">*</font>No. Pelan Syit Piawai :</label>
                            <s:text name="noLitho" size="20" id="noLitho"/>
                        </p>
                        <p>
                            <label for="tandaBlok"><font color="red">*</font>Penandaan blok (unit): </label>
                            <s:text name="tandaBlok" size="30" id="tandaBlok"/>
                        </p>
                        <p>
                        <center>
                            (Suatu peta berdasarkan kepada Siri (Baru) Peta Topografi Malaysia berskala 1 : 63,360 <br>
                            atau 1 : 50,000 menunjukkan kawasan tersebut handaklah dikepilkan)
                        </center>
                        </p>
                        <p>
                            <label><font color="red">*</font>Keluasan :</label>
                            <s:text name="hakmilikPermohonan.luasTerlibat" formatPattern="#,###,##0.0000" maxlength="15" onkeyup="validateNumber(this,this.value);" id="Luas"/>
                            <s:select name="keluasanUOM" id="uJarakKUOM">
                                <s:option value="">Sila Pilih</s:option>
                                <%--<s:option value="JK">Kaki</s:option>--%>
                                <s:option value="H">Hektar</s:option>
                                <s:option value="M">Meter Persegi</s:option>
                            </s:select>
                            <s:hidden name="kodUnitLuas.kod" value="H"/>
                        </p>
                        <p>
                            <label><font color="red">*</font>Logam/Mineral yang dicari :</label>
                            <s:textarea name="permohonan.catatan" id="catatan"  rows="5" cols="50"/>
                        </p>

                        <p>
                            <label>Tujuan:</label>
                            <s:textarea name="permohonan.sebab" rows="5" cols="50"/>
                        </p>

                    </c:if>                     
                </c:if>
                <c:if test="${actionBean.negeri eq '05'}">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PCRG'}">
                        <p>
                            <label for="nolitho"><font color="red">*</font>No. Pelan Syit Piawai :</label>
                            <s:text name="noLitho" size="20" id="noLitho"/>
                        </p>
                        <p>
                            <label for="tandaBlok"><font color="red">*</font>Penandaan blok (unit): </label>
                            <s:text name="tandaBlok" size="30" id="tandaBlok"/>
                        </p>
                        <p>
                        <p><label></label>
                            (Suatu peta berdasarkan kepada Topografi Malaysia SIri Baru berskala 1 : 63,360 
                            atau 1 : 50,000 
                        </p>
                        <p>
                            <label></label>
                            menunjukkan kawasan tersebut hendaklah dikepilkan.
                        </p>
                        <p>
                            <label></label>
                            Penandaan peta perlu bersamaan dengan Bahagian 6 Peraturan)
                        </p>
                        <p>
                            <label><font color="red">*</font>Keluasan :</label>
                            <s:text name="hakmilikPermohonan.luasTerlibat" formatPattern="#,###,##0.0000" maxlength="15" onkeyup="validateNumber(this,this.value);" id="Luas"/>
                            <s:select name="keluasanUOM" id="uJarakKUOM">
                                <s:option value="">Sila Pilih</s:option>
                                <%--<s:option value="JK">Kaki</s:option>--%>
                                <s:option value="H">Hektar</s:option>
                                <s:option value="M">Meter Persegi</s:option>
                            </s:select>
                            <s:hidden name="kodUnitLuas.kod" value="H"/>
                        </p>
                        <p>
                            <label><font color="red">*</font>Logam/Mineral yang dicari :</label>
                            <s:textarea name="permohonan.catatan" id="catatan"  rows="5" cols="50"/>
                        </p>

                        <p>
                            <label>Tujuan:</label>
                            <s:textarea name="permohonan.sebab" rows="5" cols="50"/>
                        </p>

                    </c:if>                     
                </c:if>


                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT' or actionBean.permohonan.kodUrusan.kod eq 'PTBTS'}">
                    <legend>Tujuan Permohonan</legend>
                    <p>
                        <label><font color="red">*</font>Kategori Tanah</label>
                        <s:select name="kategoriTanahBaru.kod" id="_kodKatTanah" value="${actionBean.hakmilikPermohonan.kategoriTanahBaru.kod}"onchange="doSomething(this.form);">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" />
                        </s:select>
                    </p>


                    <p><label>Kegunaan Tanah:</label>
                        <s:select name="kodGunaTanah"  value="${actionBean.hakmilikPermohonan.kodKegunaanTanah.kod}"style="width:300px;" id="_kodKegunaTanah">
                            <s:option value="">-- Sila Pilih --</s:option>
                            <s:options-collection collection="${actionBean.senaraiKodKegunaanTanahs}" value="kod" label="nama" />

                        </s:select>
                    </p>

                    <c:if test="${actionBean.permohonan.sebab eq 'Lain-lain'}">
                        <p>
                            <label><font color="red"></font>Nyatakan :</label>
                            <s:textarea name="permohonan.catatan" id="catatan"  rows="5" cols="40"/>
                        </p>
                    </c:if>
                </c:if>

                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS' || actionBean.permohonan.kodUrusan.kod eq 'PLPTD' || actionBean.permohonan.kodUrusan.kod eq 'RLPS' || actionBean.permohonan.kodUrusan.kod eq 'RLPSK'}">
                    <p>
                        <label><font color="red">*</font>Tujuan LPS :</label>
                        <s:select name="kodPermit.kod" value="${actionBean.ppi.kodItemPermit.kod}" onchange="openLain_lain(this.value)">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${actionBean.senaraiKodItemPermit}"  label="nama" value="kod" />
                        </s:select>
                    </p>
                    <div  id="catatanKwsn">                       
                        <p>
                            <label><font color="red"></font>Jika Lain-lain (Sila Nyatakan):</label>
                            <s:textarea name="hakmilikPermohonan.catatan" id="catatan"  rows="5" cols="40"/>
                        </p>
                    </div>
                </c:if>
                    
                  <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                      <p>
                        <label><font color="red">*</font>Tujuan LPS :</label>
                        <div  id="catatanKwsn1">                       
                            <s:textarea name="hakmilikPermohonan.catatan" id="catatan"  rows="5" cols="40"/>
                        </div>
                      </p>
                  </c:if>

                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU'}">
                    <p>
                        <label><font color="red">*</font>Tujuan Permohonan:</label>
                        <s:textarea name="permohonan.sebab" rows="5" cols="40"/>
                    </p>
                </c:if>

                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRMP'}">
                    <p>
                        <label>Kegunaan Tanah :</label>
                        <s:select name="kodGunaTanah"  value="${kodGunaTanah}"style="width:300px;" id="kodGunaTanah">
                            <s:option value="">-- Sila Pilih --</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodItemPRMP}" value="kod" label="nama" />
                        </s:select>
                    </p>
                    <p>
                        <label>Luas Dipohon :</label>
                        <s:text name="hakmilikpermohonan.luasDipohonKIV" value=""/>
                        <s:select name="keluasanUOM" id="kULuas">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="H">Hektar</s:option>
                            <s:option value="M">Meter Persegi</s:option>
                        </s:select>
                    </p>
                </c:if>

                <br/>
                <c:if test="${actionBean.negeri eq '04'}">
                    <c:if test="${!(actionBean.permohonan.kodUrusan.kod eq 'PRIZ')}">
                        <p>
                            <label>&nbsp;</label>
                            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');"/>
                        </p>
                    </c:if>
                </c:if>
                <%--${actionBean.permohonan.kodUrusan.kod}....--%>
                <c:if test="${actionBean.negeri eq '05'}">
                    
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PRIZ' && actionBean.permohonan.kodUrusan.kod ne 'PRMMK'}">
                        <p>
                            <label>&nbsp;</label>
                            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');"/>
                        </p>
                    </c:if>
                    
                </c:if>

            </fieldset>
        </div>
    </c:if>

    <c:if test="${hakmilik eq false && view eq true}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Tanah Dipohon</legend>
                <br/>
                <p>
                    <label>Daerah :</label>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'LMCRG' and actionBean.permohonan.kodUrusan.kod ne 'PJLB' and actionBean.permohonan.kodUrusan.kod ne 'MPJLB'}">
                        ${actionBean.permohonan.cawangan.daerah.nama}
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG' or actionBean.permohonan.kodUrusan.kod eq 'PJLB' or actionBean.permohonan.kodUrusan.kod eq 'MPJLB'}">
                    <p>
                        ${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.daerah.nama}
                    </p>
                </c:if>
                </p>
                <p>
                    <label>Mukim/Pekan/Bandar :</label>
                    ${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama}
                </p>
                <p>
                    <label>Seksyen :</label>
                    <c:if test="${actionBean.hakmilikPermohonan.kodSeksyen eq null}">Tiada</c:if>
                    <c:if test="${actionBean.hakmilikPermohonan.kodSeksyen ne null}">${actionBean.hakmilikPermohonan.kodSeksyen.nama}</c:if>
                </p>
                <p>
                    <label>Tempat :</label>
                    ${actionBean.hakmilikPermohonan.lokasi}
                </p>
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPTR'}">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU' or actionBean.permohonan.kodUrusan.kod eq 'PJBTR'}">
                        <p>
                            <label>Keluasan Terlibat (Isipadu) :</label>
                            ${actionBean.hakmilikPermohonan.luasTerlibat}
                            ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                        </p>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPRU' and actionBean.permohonan.kodUrusan.kod ne 'PJBTR'}">
                        <p>
                            <label>Luas :</label>
                            ${actionBean.hakmilikPermohonan.luasTerlibat}
                            ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                        </p>
                    </c:if>
                </c:if>



                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR'}">

                    <p>
                        <label>Luas Dipohon:</label>
                        ${actionBean.mohonTrizab.luasTerlibat}
                        <c:if test="${actionBean.hakmilikPermohonan.kodUnitLuas.kod eq 'H'}">Hektar</c:if>
                        <c:if test="${actionBean.hakmilikPermohonan.kodUnitLuas.kod eq 'M'}">Meter Persegi</c:if>
                    </p>
                    <p>
                        <label>Jenis Rizab:</label>
                        ${actionBean.mohonTrizab.rizab.nama}
                    </p>
                    <p>
                        <label>Tempoh Pajakan Dipohon:</label>
                        ${actionBean.hakmilikPermohonan.tempohPajakan} Tahun
                    </p>
                    <p>
                        <label>Tujuan:</label>
                        ${actionBean.permohonan.sebab}
                    </p>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPTR'}">
                    <p>
                        <label>Nombor Lot/Pt (Jika ada) :</label>
                        <c:if test="${actionBean.hakmilikPermohonan.noLot eq null}">Tiada</c:if>
                        <c:if test="${actionBean.hakmilikPermohonan.noLot ne null}">${actionBean.hakmilikPermohonan.lot.nama} ${actionBean.hakmilikPermohonan.noLot}</c:if>
                    </p>
                </c:if>

                <c:if test="${actionBean.negeri eq '04'}">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPTR'}">
                        <p> <label>Jarak :</label>
                            <c:if test="${!empty actionBean.hakmilikPermohonan.jarak}">
                                ${actionBean.hakmilikPermohonan.jarak}
                                ${actionBean.hakmilikPermohonan.unitJarak.nama}&nbsp;dari
                                ${actionBean.hakmilikPermohonan.jarakDari}
                            </c:if>
                            <c:if test="${empty actionBean.hakmilikPermohonan.jarak}">
                                &nbsp;-
                            </c:if>
                        </p>
                        <p id="namaJarak">
                            <label>Nama Pantai/Pekan/Sungai :</label>
                            <c:if test="${!empty actionBean.jarakDariNama}">
                                ${actionBean.jarakDariNama}
                            </c:if>
                            <c:if test="${empty actionBean.jarakDariNama}">
                                &nbsp;-
                            </c:if>

                        </p>
                        <p> <label>Jarak Dari Kediaman :</label>
                            <c:if test="${!empty actionBean.hakmilikPermohonan.jarakDariKediaman}">
                                ${actionBean.hakmilikPermohonan.jarakDariKediaman}
                                ${actionBean.hakmilikPermohonan.jarakDariKediamanUom.nama}
                            </c:if>
                            <c:if test="${empty actionBean.hakmilikPermohonan.jarakDariKediaman}">
                                &nbsp;-
                            </c:if>
                        </p>
                    </c:if>

                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                    <br>
                    <legend>Tujuan Permohonan</legend>
                    <br>
                    <p>
                        <label>Kategori Tanah :</label>
                        ${actionBean.hakmilikPermohonan.kategoriTanahBaru.nama}
                    </p>
                    <p>
                        <label>Kegunaan Tanah :</label>
                        ${actionBean.hakmilikPermohonan.kodKegunaanTanah.nama}
                    </p>
                    <c:if test="${actionBean.permohonan.sebab eq 'Lain-lain'}">
                        <p> <label>&nbsp;</label>
                            ${actionBean.permohonan.catatan}
                        </p>
                    </c:if>

                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP' || actionBean.permohonan.kodUrusan.kod eq 'PLPS' || actionBean.permohonan.kodUrusan.kod eq 'PLPTD' || actionBean.permohonan.kodUrusan.kod eq 'RLPS' || actionBean.permohonan.kodUrusan.kod eq 'RLPSK'}">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'LPSP'}">
                        <p>
                            <label>Tujuan :</label>                       
                                ${actionBean.ppi.kodItemPermit.nama}     
                        </p>
                        <div id="catatanKwsn">
                            <c:if test="${actionBean.hakmilikPermohonan.catatan != null}">
                                <p> <label>Jika Lain-lain (Sila Nyatakan) :</label>
                                    ${actionBean.hakmilikPermohonan.catatan}
                                </p>
                            </c:if>
                        </div>
                    </c:if>
                    
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                        <p>
                            <label>Tujuan LPS :</label>
                                ${actionBean.hakmilikPermohonan.catatan}  
                        </p>
                        <div id="catatanKwsn">
                            <c:if test="${actionBean.hakmilikPermohonan.catatan != null}">
                                <p> <label>Jika Lain-lain (Sila Nyatakan) :</label>
                                    ${actionBean.hakmilikPermohonan.catatan}
                                </p>
                            </c:if>
                        </div>
                    </c:if>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU'}">
                    <p>
                        <label>Tujuan Permohonan:</label>
                        ${actionBean.permohonan.sebab}
                    </p>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRMP'}">
                    <p>
                        <label>Kegunaan Tanah :</label>
                        ${actionBean.permohonan.catatan}
                    </p>
                    <p>
                        <label>Luas Dipohon :</label>

                        ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}

                    </p>

                </c:if>
                <%--view melaka when kodUrusan eq LMCRG--%>
                <c:if test="${actionBean.negeri eq '04'}">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG'}">
                        <p>
                            <label for="nolitho">No. Pelan Syit Piawai :</label>
                            ${actionBean.noLitho}
                        </p>
                        <p>
                            <label for="tandaBlok">Penandaan blok (unit): </label>
                            <c:if test="${actionBean.tandaBlok ne null}">
                                ${actionBean.tandaBlok}
                            </c:if>
                            <c:if test="${actionBean.tandaBlok eq null}">
                                &nbsp;
                            </c:if>
                        </p>
                        <p>
                        <center>
                            (Suatu peta berdasarkan kepada Siri (Baru) Peta Topografi Malaysia berskala 1 : 63,360 <br>
                            atau 1 : 50,000 menunjukkan kawasan tersebut handaklah dikepilkan)
                        </center>
                        </p>
                        <p>
                            <label>Logam/Mineral yang dicari :</label>
                            ${actionBean.permohonan.catatan}
                        </p>
                        <p>
                            <label>Tujuan :</label>
                            ${actionBean.permohonan.sebab}
                        </p>
                    </c:if>
                </c:if>
                <c:if test="${actionBean.negeri eq '05'}">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PCRG'}">
                        <p>
                            <label for="nolitho">No. Pelan Syit Piawai :</label>
                            ${actionBean.noLitho}<br>
                        </p>
                        <p>
                            <label for="tandaBlok">Penandaan blok (unit): </label>
                            <c:if test="${actionBean.tandaBlok ne null}">
                                ${actionBean.tandaBlok}
                            </c:if>
                            <c:if test="${actionBean.tandaBlok eq null}">
                                &nbsp;
                            </c:if>
                        </p>
                        
                        <p><label></label>
                            (Suatu peta berdasarkan kepada Topografi Malaysia SIri Baru berskala 1 : 63,360 
                            atau 1 : 50,000 
                        </p>
                        <p>
                            <label></label>
                            menunjukkan kawasan tersebut hendaklah dikepilkan.
                        </p>
                        <p>
                            <label></label>
                            Penandaan peta perlu bersamaan dengan Bahagian 6 Peraturan)
                        </p>
                        <p>
                            <label>Logam/Mineral yang dicari :</label>
                            ${actionBean.permohonan.catatan}
                        </p>
                        <p>
                            <label>Tujuan :</label>
                            ${actionBean.permohonan.sebab}
                        </p>
                    </c:if>
                </c:if>
                <%--view melaka when kodUrusan eq PRIZ--%>
                <c:if test="${actionBean.negeri eq '04'}">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRIZ'}">
                        <p>
                            <label>Luas Dipohon:</label>
                            ${actionBean.mohonTrizab.luasTerlibat}
                            <c:if test="${actionBean.hakmilikPermohonan.kodUnitLuas.kod eq 'H'}">Hektar</c:if>
                            <c:if test="${actionBean.hakmilikPermohonan.kodUnitLuas.kod eq 'M'}">Meter Persegi</c:if>
                        </p>
                        <p>
                            <label>Jenis Rizab:</label>
                            ${actionBean.mohonTrizab.rizab.nama}
                        </p>
                        <p>
                            <label>Pegawai Pengawal:</label>
                            ${actionBean.mohonTrizab.namaPenjaga}
                        </p>
                        
                        <p>
                            <label>Tujuan:</label>
                            ${actionBean.permohonan.sebab}
                        </p>
                    </c:if>
                </c:if>

                <br>
            </fieldset>
        </div>
    </c:if>


    <c:if test="${hakmilik eq true && edit eq true}">
        <div class="subtitle displaytag">

            <fieldset class="aras1">
                <legend>
                    Maklumat Hakmilik Permohonan
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0"
                                   requestURI="/common/maklumat_hakmilik_permohonan" id="line">
                        <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}" style="vertical-align:baseline"/>
                        <display:column property="hakmilik.noLot" title="Nombor Lot/PT" style="vertical-align:baseline"/>
                        <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah" style="vertical-align:baseline"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" style="vertical-align:baseline"/>
                        <display:column title="Maklumat Atas Tanah">
                            ${actionBean.hakmilikPermohonanList[line_rowNum - 1].hakmilik.maklumatAtasTanah}
                            <%--<s:textarea name="hakmilikPermohonanList[${line_rowNum - 1}].hakmilik.maklumatAtasTanah"/> --%>
                        </display:column>


                    </display:table>
                </div>




                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRMP'}">
                    <p>
                        <label>Kegunaan Tanah :</label>
                        <s:select name="kodGunaTanah"  value="${actionBean.kodGunaTanah}"style="width:300px;" id="kodGunaTanah">
                            <s:option value="">-- Sila Pilih --</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodItemPRMP}" value="kod" label="nama" />
                        </s:select>
                        <%--<s:text name="permohonan.catatan" id="catatan"/>--%>
                    </p>
                    <p>
                        <label>Luas Dipohon :</label>
                        <s:text name="hakmilikPermohonan.luasTerlibat" formatPattern="#,###,##0.0000" maxlength="15" onkeyup="validateNumber(this,this.value);" id="Luas"/>
                        <s:select name="keluasanUOM" style="width:150px" id="kULuas">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="H">Hektar</s:option>
                            <s:option value="M">Meter Persegi</s:option>
                        </s:select>
                    </p>
                    <p>
                        <label>Syarat Nyata :</label>
                        <s:textarea name="syaratNyata" id="syaratNyata" rows="5" cols="80" readonly="yes" class="tooltips" value="${actionBean.hakmilikPermohonan.syaratNyataBaru.kod}--${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}"></s:textarea>
                        <s:hidden name="kod" id="kod"/><s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup()"/>
                    </p>
                    <p align="center"><label></label>
                        <br>
                        <s:button name="simpanHakmilikPermit" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJBTR'}">
                        <p>
                            <label>Isipadu Dipohon :</label>
                            <s:text name="hakmilikPermohonan.luasTerlibat" formatPattern="#,###,##0.0000" maxlength="15" onkeyup="validateNumber(this,this.value);" id="Luas"/>
                            <s:select name="keluasanUOM" style="width:150px" id="kULuas">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="MP">Meter Padu</s:option>
                            </s:select>
                        </p>
                        <p>
                            <label><font color="red">*</font>Tujuan Permohonan:</label>
                            <s:textarea name="permohonan.sebab" rows="5" cols="40"/>
                        </p>
                    </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PRMP' and actionBean.permohonan.kodUrusan.kod ne 'MCMCL' and actionBean.permohonan.kodUrusan.kod ne 'MMMCL'}">
                    <c:if test="${actionBean.negeri eq '05' and actionBean.permohonan.kodUrusan.kod eq 'PRIZ'}">
                        <p align="center"><label></label>
                            <br>
                            <s:button name="simpanHakmilik" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PRIZ'}">
                        <p align="center"><label></label>
                            <br>
                            <s:button name="simpanHakmilik" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </p>
                    </c:if>
                     
                </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTPBP'}">
                            <br>
                    <p>
                        <label>Kegunaan Tanah :</label>
                        <s:select name="kodGunaTanah"  value="${actionBean.kodGunaTanah}"style="width:300px;" id="kodGunaTanah">
                            <s:option value="">-- Sila Pilih --</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodItemPRMP}" value="kod" label="nama" />
                        </s:select>
                        <%--<s:text name="permohonan.catatan" id="catatan"/>--%>
                    </p>
                    <p>
                        <label>Luas Dipohon :</label>
                        <s:text name="hakmilikPermohonan.luasTerlibat" formatPattern="#,###,##0.0000" maxlength="15" onkeyup="validateNumber(this,this.value);" id="Luas"/>
                        <s:select name="keluasanUOM" style="width:150px" id="kULuas">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="H">Hektar</s:option>
                            <s:option value="M">Meter Persegi</s:option>
                        </s:select>
                    </p>
                    <p align="center"><label></label>
                        <br>
                        <s:button name="simpanHakmilikPermit" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </c:if>
            </fieldset>
        </div>
    </c:if>

    <c:if test="${hakmilik eq true && view eq true}">
        <div class="subtitle displaytag">

            <fieldset class="aras1">
                <legend>
                    Maklumat Hakmilik Permohonan
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0"
                                   requestURI="/common/maklumat_hakmilik_permohonan" id="line">
                        <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}" style="vertical-align:baseline"/>
                        <display:column property="hakmilik.noLot" title="Nombor Lot/PT" style="vertical-align:baseline"/>
                        <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah" style="vertical-align:baseline"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" style="vertical-align:baseline"/>
                        <display:column title="Maklumat Atas Tanah">
                            <%--<s:textarea name="hakmilikPermohonanList[${line_rowNum - 1}].hakmilik.maklumatAtasTanah"  readonly="true"/> --%>
                            ${actionBean.hakmilikPermohonanList[line_rowNum - 1].hakmilik.maklumatAtasTanah}
                        </display:column>


                    </display:table>
                </div>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRMP'}">
                    <p>
                        <label>Kegunaan Tanah :</label>

                        ${actionBean.ppi.kodItemPermit.nama}

                    </p>
                    <p>
                        <label>Luas Dipohon :</label>
                        <s:format value="${actionBean.hakmilikPermohonan.luasTerlibat}" formatPattern="#,###,##0.0000"/>
                        ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                    </p>
                    <p>
                        <label>Syarat Nyata :</label>
                        <s:textarea name="syaratNyata" id="syaratNyata" rows="5" cols="80" readonly="yes" class="tooltips" value="${actionBean.hakmilikPermohonan.syaratNyataBaru.kod}--${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}"></s:textarea>
                        <s:hidden name="kod" id="kod"/>
                    </p>
                </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTPBP'}">
                        <br>
                    <p>
                        <label>Kegunaan Tanah :</label>

                        ${actionBean.ppi.kodItemPermit.nama}

                    </p>
                    <p>
                        <label>Luas Dipohon :</label>
                        <s:format value="${actionBean.hakmilikPermohonan.luasTerlibat}" formatPattern="#,###,##0.0000"/>
                        ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                    </p>
                </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJBTR'}">
                    <p>
                        <label>Isipadu Dipohon :</label>
                        <s:format value="${actionBean.hakmilikPermohonan.luasTerlibat}" formatPattern="#,###,##0.0000"/>
                        ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                    </p>
                    <p>
                        <label>Tujuan Permohonan :</label>
                        ${actionBean.permohonan.sebab}
                    </p>
                </c:if>
                

            </fieldset>
        </div>
    </c:if>
</s:form>