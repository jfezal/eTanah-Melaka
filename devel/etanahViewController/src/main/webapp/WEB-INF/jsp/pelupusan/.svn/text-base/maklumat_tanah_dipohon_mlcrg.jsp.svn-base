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
<link type="text/css" rel=" stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel=" stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel=" stylesheet"
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
        if('${actionBean.permohonan.kodUrusan.kod}' == "MLCRG"){
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
        if(${actionBean.permohonan.kodUrusan.kod eq 'MLCRG'})
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
 
            if('${actionBean.permohonan.kodUrusan.kod}' == "MLCRG"){
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
<s:form name="form2" id="form2" beanclass="etanah.view.stripes.pelupusan.MaklumatTanahMlcrgActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="urusan" id="urusan" value="MLCRG"/>
    <s:hidden name="forSeksyen" id="forSeksyen"/>
   
        <s:hidden name="forBPM" id="forBPM"/>
        <s:hidden name="kodBpm" id="kodBpm"/>
        <s:hidden name="kodDaerah" id="kodDaerah"/>
   
   
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Tanah Dipohon </legend>


                    <p>
                        <label>Daerah :</label>
                        ${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.daerah.nama}
                    </p>
                    <c:if test="${actionBean.forBPM}">
                        <p>
                            <label><font color="red">*</font>Mukim/Pekan/Bandar :</label>
                            ${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama}
                        </p>
                    </c:if>
                    <s:hidden name="test" value="${actionBean.hakmilikPermohonan.kodSeksyen.kod}" style="width:300px;" id="_kodSeksyen2"/>
                    <c:if test="${actionBean.forSeksyen}">
                        <p ><label>Seksyen :</label>
                            <s:select name="kodSeksyen.kod" value="${actionBean.hakmilikPermohonan.kodSeksyen.kod}" style="width:300px;" id="_kodSeksyen">
                                <s:option value="">-- Sila Pilih --</s:option>
                                <s:options-collection collection="${actionBean.senaraiKodSeksyen}" value="kod" label="nama" />

                            </s:select>
                        </p>
                    </c:if>
                




                <p>
                    <label><font color="red">*</font>Nombor ${actionBean.hakmilikPermohonan.lot.nama} :</label>
                    ${actionBean.hakmilikPermohonan.noLot}&nbsp;
                </p> 


                        <p>
                            <label for="nolitho"><font color="red">*</font>No. Pelan Syit Piawai :</label>
                            ${actionBean.noLitho}
                        </p>
                        <br>
                        <p>
                            <label for="tandaBlok"><font color="red">*</font>Penandaan blok (unit): </label>
                            ${actionBean.tandaBlok}
                        </p>
                        <p>
                        <center>
                            (Suatu peta berdasarkan kepada Siri (Baru) Peta Topografi Malaysia berskala 1 : 63,360 <br>
                            atau 1 : 50,000 menunjukkan kawasan tersebut handaklah dikepilkan)
                        </center>
                        </p>
                        <p>
                            <label><font color="red">*</font>Keluasan :</label>
                            
                            <s:text readonly="true" name="hakmilikPermohonan.luasTerlibat" formatPattern="#,###,##0.0000" maxlength="15" value="${actionBean.hakmilikPermohonan.luasTerlibat}" onkeyup="validateNumber(this,this.value);" id="Luas"/>

                            ${actionBean.unitKeluasan}
                        </p>
                        <p>
                            <label><font color="red">*</font>Logam/Mineral yang dicari:</label>
                            ${actionBean.permohonanSebelum.catatan} 

                        </p>

                <br/>

            </fieldset>
        </div>


</s:form>