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
<s:form name="form2" id="form2" beanclass="etanah.view.stripes.pelupusan.MaklumatTanahRAYTActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="urusan" id="urusan" value="${actionBean.permohonan.kodUrusan.kod}"/>
    <s:hidden name="forSeksyen" id="forSeksyen"/>
    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG' or actionBean.permohonan.kodUrusan.kod eq 'PJLB' or actionBean.permohonan.kodUrusan.kod eq 'MPJLB'}">
        <s:hidden name="forBPM" id="forBPM"/>
        <s:hidden name="kodBpm" id="kodBpm"/>
        <s:hidden name="kodDaerah" id="kodDaerah"/>
    </c:if>





    <div class="subtitle displaytag">

        <fieldset class="aras1">
            <legend>
                Maklumat Hakmilik Permohonan Rayuan Penolakan Permohonan 
            </legend>

            <!-- Create new field here by KHAD -->
            <p></p>                
            <p>
                <label>Daerah :</label>
                ${actionBean.permohonan.cawangan.daerah.nama}
            </p>
            <p>
                <label><font color="red">*</font>Mukim/Pekan/Bandar :</label>                        
                <s:select name="bandarPekanMukimBaru.kod" value="${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.kod}" id="mpb" onchange="doSomething(this.form);">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${actionBean.senaraiKodBPM}" label="nama" value="kod" />
                </s:select>
            </p>
            <p>
                <label><font color="red">*</font>Tempat/Lokasi Tanah Dipohon :</label>
                <s:textarea name="hakmilikPermohonan.lokasi" rows="5" cols="70" id="tempat" class="normal_text"/>
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
                <label>Nombor Lot/Pt (Jika ada) :</label>
                <c:if test="${actionBean.hakmilikPermohonan.noLot eq null}">Tiada</c:if>
                <c:if test="${actionBean.hakmilikPermohonan.noLot ne null}">${actionBean.hakmilikPermohonan.lot.nama} ${actionBean.hakmilikPermohonan.noLot}</c:if>
            </p>
             <p> 
                <label>Jarak :</label>
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
                
   <br><br>            
                        
            <!-- END Create new field here by KHAD -->



            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0"
                               requestURI="/common/maklumat_hakmilik_permohonan" id="line">
                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}" style="vertical-align:baseline"/>
                    <display:column property="hakmilik.noLot" title="Nombor Lot/PT" style="vertical-align:baseline"/>
                    <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah" style="vertical-align:baseline"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" style="vertical-align:baseline"/>
                    <display:column title="Maklumat Atas Tanah"><s:textarea name="hakmilikPermohonanList[${line_rowNum - 1}].hakmilik.maklumatAtasTanah"  readonly="true"/> </display:column>

                </display:table>
            </div>


        </fieldset>
    </div>

</s:form>