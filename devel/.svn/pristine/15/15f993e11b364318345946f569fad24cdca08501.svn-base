<%--
    Document   : popup_edit_barang_tahanan
    Created on : May 5, 2010, 11:44:05 AM
    Author     : nurshahida.radzi
    modified by : ctzainal 16june 2011 and sitifariza.hanim (02Ogos2011)
    Modified by: ctzainal on 12Sept2011 -add field namaPemandu based on senaraiOKS
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
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
        $(".datepicker1").datepicker({dateFormat: 'dd/mm/yy'});
        if(document.getElementById("kod1").checked == true){
            //var idPemunya = $('#namaPemunyaWajibBukanKenderaan').val();
            //disableTextField(idPemunya, 'B');
            $('#pemegangPermit').hide(); //for kenderaan only - restructure layout
        }else{
            //var idPemunya = $('#namaPemunyaWajib').val();
            //disableTextField(idPemunya, 'K');
        }
        jenisPengenalanPemegangPermit();
        jenisPengenalanPenjamin();
        checkedPemunya();

        if($('#muatanTempatDuduk').val()=="0"){
            document.getElementById("muatanTempatDuduk").value = "";
        }
        
        $('#kodNegeriPemunyaTemp').hide();
        $('#kodPengenalanPemunyaDisplayText').hide();

    });

    <%--    function validateForm(){

        /*checking for K (kenderaan) id:kod*/
        if(document.getElementById("kod").checked == true){
            if(document.form1.item1.value=="")
            {
                alert('Sila masukkan Jenis Kenderaan');
                return false;
            }


    <c:if test="${actionBean.kodNegeriPermohonan eq '04'}">
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq '422' || actionBean.permohonan.kodUrusan.kod eq '423' || actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '426' || actionBean.permohonan.kodUrusan.kod eq '999'}">
                    if($('#namaPemunyaWajib').val()=="")
                    {
                        alert('Sila masukkan nama pemunya');
                        $('#namaPemunyaWajib').focus();
                        return false;namaPemunyaWajibBukanKenderaan
                    }

        </c:if>
    </c:if>
     if(document.form1.namaPenjamin.value=="")
     {
         alert('Sila masukkan Nama Penjamin');
         return false;
     }

             }

             /*checking for B (bukan kenderaan) id:kod*/
             if(document.getElementById("kod1").checked == true){
    alert("B");
                if(document.form1.barangRampas.value=="")
                {
                    alert('Sila masukkan Barang Yang Dirampas : B');
                    return false;
                }
                if(document.form1.kuantiti.value=="")
                {
                    alert('Sila masukkan Kuantiti');
                    return false;
                }

     if(document.form1.namaPenjamin.value=="")
     {
         alert('Sila masukkan Nama Penjamin');
         return false;
     }

    <c:if test="${actionBean.kodNegeriPermohonan eq '04'}">
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq '422' || actionBean.permohonan.kodUrusan.kod eq '423' || actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '426' || actionBean.permohonan.kodUrusan.kod eq '999'}">
                    if($('#namaPemunyaWajibBukanKenderaan').val()=="")
                    {
                        alert('Sila masukkan nama pemunya');
                        $('#namaPemunyaWajibBukanKenderaan').focus();
                        return false;
                    }

        </c:if>
    </c:if>

            }
            
            if($('#idOperasiAgensi').val()=="")
            {
                alert('Sila pilih laporan polis terlebih dahulu');
                $('#idOperasiAgensi').focus();
                return false;
            }


            return true;
            self.opener.refreshPageCeroboh();
            self.close();
        }--%>

            function validateForm(){

                /*checking for K (kenderaan) id:kod*/
                if(document.getElementById("kod").checked == true){
                    if(document.form1.item1.value=="")
                    {
                        alert('Sila masukkan Jenis Kenderaan');
                        $('#item1').focus();
                        return false;
                    }

    <c:if test="${actionBean.kodNegeriPermohonan eq '04'}">
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq '422' || actionBean.permohonan.kodUrusan.kod eq '423' || actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '425A' || actionBean.permohonan.kodUrusan.kod eq '426' || actionBean.permohonan.kodUrusan.kod eq '999'}">
                    //                    if($('#namaPemunyaWajib').val()=="")
                    //                    {
                    //                        alert('Sila masukkan nama pemunya');
                    //                        $('#namaPemunyaWajib').focus();
                    //                        return false;
                    //                    }

        </c:if>
    </c:if>

                if($('#datepicker1').val()=="")
                {
                    alert('Sila masukkan Tarikh Kenderaan Dirampas terlebih dahulu');
                    $('#datepicker1').focus();
                    return false;
                }

                if($('#jam').val()=="")
                {
                    alert('Sila masukkan jam rampasan terlebih dahulu');
                    $('#jam').focus();
                    return false;
                }
                if($('#minit').val()=="")
                {
                    alert('Sila isikan minit rampasan terlebih dahulu');
                    $('#minit').focus();
                    return false;
                }
                if($('#ampm').val()=="")
                {
                    alert('Sila pilih pagi atau petang pada bahagian masa barang dirampas');
                    $('#ampm').focus();
                    return false;
                }

    <%--if(document.form1.namaPenjamin.value=="")
    {
        alert('Sila masukkan Nama Penjamin');
        return false;
    }
    --%>
            }

            /*checking for B (bukan kenderaan) id:kod*/
            if(document.getElementById("kod1").checked == true){

                if(document.form1.barangRampas.value=="")
                {
                    alert('Sila masukkan Barang Yang Dirampas');
                    $('#barangRampas').focus();
                    return false;
                }

    <c:if test="${actionBean.kodNegeriPermohonan eq '04'}">
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq '422' || actionBean.permohonan.kodUrusan.kod eq '423' || actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '425A' || actionBean.permohonan.kodUrusan.kod eq '426' || actionBean.permohonan.kodUrusan.kod eq '999'}">
                    //                    if($('#namaPemunyaWajibBukanKenderaan').val()=="")
                    //                    {
                    //                        alert('Sila masukkan nama pemunya');
                    //                        $('#namaPemunyaWajibBukanKenderaan').focus();
                    //                        return false;
                    //                    }

        </c:if>
    </c:if>

                if($('#datepicker2').val()=="")
                {
                    alert('Sila isikan Tarikh Barang Rampasan terlebih dahulu');
                    $('#datepicker2').focus();
                    return false;
                }

                if($('#jam1').val()=="")
                {
                    alert('Sila isikan jam rampasan terlebih dahulu');
                    $('#jam1').focus();
                    return false;
                }
                if($('#minit1').val()=="")
                {
                    alert('Sila isikan minit rampasan terlebih dahulu');
                    $('#minit1').focus();
                    return false;
                }
                if($('#ampm1').val()=="")
                {
                    alert('Sila pilih pagi atau petang pada bahagian masa laporan');
                    $('#ampm1').focus();
                    return false;
                }
                if($('#kuantitiBarang').val()=='')
                {
                    alert('Sila masukkan Kuantiti barang rampasan');
                    $('#kuantitiBarang').focus();
                    return false;
                }
                if($('#kuantitiUnit').val()=="")
                {
                    alert('Sila masukkan Unit barang rampasan');
                    $('#kuantitiUnit').focus();
                    return false;
                }
                if($('#idOperasiAgensi').val()=="")
                {
                    alert('Sila pilih Laporan Polis terlebih dahulu');
                    $('#idOperasiAgensi').focus();
                    return false;
                }
                if($('#tempatTangkap').val()=="")
                {
                    alert('Sila masukkan Lokasi Tangkapan terlebih dahulu');
                    $('#tempatTangkap').focus();
                    return false;
                }
                if($('#namaSuspekWajib').val()=="")
                {
                    alert('Sila pilih Nama Suspek terlebih dahulu');
                    $('#namaSuspekWajib').focus();
                    return false;
                }
                if($('#namaPemunya').val()=="")
                {
                    alert('Sila masukkan Nama Pemunya Berdaftar terlebih dahulu');
                    $('#namaPemunya').focus();
                    return false;
                }

            }


            return true;
            self.opener.refreshPageCeroboh();
            self.close();
        }

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
                self.opener.refreshPageCeroboh();
                self.close();
            },'html');

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
    <%--function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }--%>

        function validatePoskodLength(value){
            var plength = value.length;
            if(plength != 5){
                alert('Poskod yang dimasukkan salah.');
                $('#poskod').val("");
                $('#poskod').focus();
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

        function jenisPengenalanPemegangPermit(){
            if($('#kodJenisPengenalanPemegangPermit').val() == 'B'){
                document.getElementById("noPengenalanBaruPemegangPermit").style.visibility = 'visible';
                document.getElementById("noPengenalanBaruPemegangPermit").style.display = '';
                $('#noPengenalanLainPemegangPermit').hide();

                $('#penyerahNoPengenalanLainPemegangPermit').attr("disabled", true);
                $('#penyerahNoPengenalanBaruPemegangPermit').attr("disabled", false);
            }else{
                $('#noPengenalanLainPemegangPermit').show();
                document.getElementById("noPengenalanBaruPemegangPermit").style.visibility = 'hidden';
                document.getElementById("noPengenalanBaruPemegangPermit").style.display = 'none';

                $('#penyerahNoPengenalanBaruPemegangPermit').attr("disabled", true);
                $('#penyerahNoPengenalanLainPemegangPermit').attr("disabled", false);
            }
        }

        function jenisPengenalanPenjamin(){
            if($('#pengenalanPenjamin').val() == 'B'){
                document.getElementById("noPengenalanBaruPenjamin").style.visibility = 'visible';
                document.getElementById("noPengenalanBaruPenjamin").style.display = '';
                $('#noPengenalanLainPenjamin').hide();
                $('#penyerahNoPengenalanLainPenjamin').attr("disabled", true);
                $('#penyerahNoPengenalanBaruPenjamin').attr("disabled", false);
            }else{
                $('#noPengenalanLainPenjamin').show();
                document.getElementById("noPengenalanBaruPenjamin").style.visibility = 'hidden';
                document.getElementById("noPengenalanBaruPenjamin").style.display = 'none';
                $('#penyerahNoPengenalanBaruPenjamin').attr("disabled", true);
                $('#penyerahNoPengenalanLainPenjamin').attr("disabled", false);
            }
        }
       
        function findPemunya(id){
            if(id != "" && id != "TDD"){
                var edit = 'edit';
                var idBarang = document.getElementById("idBarang").value;
                $.get('${pageContext.request.contextPath}/penguatkuasaan/maklumat_barang_tahanan?findOks&idOks='+id+'&pagePass='+edit+'&idBarang='+idBarang,
                function(data){

                    if(document.getElementById("kod1").checked == true){
                        //for bukan kenderaan
                        $("#maklumatPemunyaBukanKenderaan").replaceWith($('#maklumatPemunyaBukanKenderaan', $(data)));

                        $('#nokp').attr("readonly", true);
                        $('#alamat1').attr("readonly", true);
                        $('#alamat2').attr("readonly", true);
                        $('#alamat3').attr("readonly", true);
                        $('#alamat4').attr("readonly", true);
                        $('#poskod').attr("readonly", true);
                        $('#negeri').attr("readonly", true);
                    }else{
                        //for kenderaan
                        $("#maklumatPemunyaKenderaan").replaceWith($('#maklumatPemunyaKenderaan', $(data)));

                        $('#nokpPemunya').attr("readonly", true);
                        $('#alamatPemunya1').attr("readonly", true);
                        $('#alamatPemunya2').attr("readonly", true);
                        $('#alamatPemunya3').attr("readonly", true);
                        $('#alamatPemunya4').attr("readonly", true);
                        $('#poskodPemunya').attr("readonly", true);
                        $('#negeriPemunya').attr("readonly", true);
                    }

                }, 'html');
            }else{
                if(document.getElementById("kod1").checked == true){
                    //for bukan kenderaan
                    //clear value if select TDD
                    document.getElementById("nokp").value = "";
                    document.getElementById("alamat1").value = "";
                    document.getElementById("alamat2").value = "";
                    document.getElementById("alamat3").value = "";
                    document.getElementById("alamat4").value = "";
                    document.getElementById("poskod").value = "";
                    document.getElementById("negeri").value = "";

                    $('#nokp').attr("readonly", false);
                    $('#alamat1').attr("readonly", false);
                    $('#alamat2').attr("readonly", false);
                    $('#alamat3').attr("readonly", false);
                    $('#alamat4').attr("readonly", false);
                    $('#poskod').attr("readonly", false);
                    $('#negeri').attr("readonly", false);
                }else{
                    //for kenderaan
                    //clear value if select TDD
                    document.getElementById("nokpPemunya").value = "";
                    document.getElementById("alamatPemunya1").value = "";
                    document.getElementById("alamatPemunya2").value = "";
                    document.getElementById("alamatPemunya3").value = "";
                    document.getElementById("alamatPemunya4").value = "";
                    document.getElementById("poskodPemunya").value = "";
                    document.getElementById("negeriPemunya").value = "";

                    $('#nokpPemunya').attr("readonly", false);
                    $('#alamatPemunya1').attr("readonly", false);
                    $('#alamatPemunya2').attr("readonly", false);
                    $('#alamatPemunya3').attr("readonly", false);
                    $('#alamatPemunya4').attr("readonly", false);
                    $('#poskodPemunya').attr("readonly", false);
                    $('#negeriPemunya').attr("readonly", false);
                }
            }
        }

        function disableTextField (id, type){
            //alert(type);
            if(id != "TDD" && type == "K"){
                $('#nokpPemunya').attr("readonly", true);
                $('#alamatPemunya1').attr("readonly", true);
                $('#alamatPemunya2').attr("readonly", true);
                $('#alamatPemunya3').attr("readonly", true);
                $('#alamatPemunya4').attr("readonly", true);
                $('#poskodPemunya').attr("readonly", true);
                $('#negeriPemunya').attr("readonly", true);
            }else if(id != "TDD" && type == "B"){
                $('#nokp').attr("readonly", true);
                $('#alamat1').attr("readonly", true);
                $('#alamat2').attr("readonly", true);
                $('#alamat3').attr("readonly", true);
                $('#alamat4').attr("readonly", true);
                $('#poskod').attr("readonly", true);
                $('#negeri').attr("readonly", true);
            }

        }

        function validateTelLength(value){
            var plength = value.length;
            if(plength < 7){
                alert('No. Telefon yang dimasukkan salah.');
                $('#telefon').val("");
                $('#telefon').focus();
            }
        }
        
        
        function findSuspek(id){
            //alert(id);
            if(id != ""){
                var add = 'add';
                $.get('${pageContext.request.contextPath}/penguatkuasaan/maklumat_barang_tahanan?findSuspek&idOks='+id+'&pagePass='+add,
                function(data){
                    $("#suspekInfo").replaceWith($('#suspekInfo', $(data)));

    <c:if test="${actionBean.kodNegeriPermohonan eq '05'}">
                    if(document.getElementById("checkSame").checked == true){
                        $("#maklumatPemunyaInfo").replaceWith($('#maklumatPemunyaInfo', $(data)));
                        copyInfoSuspek();
                    }
    </c:if>
                }, 'html');
            }
        }

        function copyInfoSuspek(){
            //alert( "try"+document.getElementById("checkSame").checked);
            if(document.getElementById("checkSame").checked == true){
                $('#namaPemunya').val($('#namaSuspekTemp').val());
                $('#nokpPemunya').val($('#noPengenalanSuspek').val());
                $('#noTelPemunya').val($('#noTelSuspek').val());
                $('#alamatPemunya1').val($('#alamat1Suspek').val());
                $('#alamatPemunya2').val($('#alamat2Suspek').val());
                $('#poskodPemunya').val($('#poskodSuspek').val());
                $('#negeriPemunya').val($('#kodNegeriSuspek').val());
                $('#kodNegeriPemunyaTemp').val($('#alamat3Suspek').val());
                $('#negeriPemunya').hide();
                $('#kodNegeriPemunyaTemp').show();


                $('#namaPemunya').attr("readonly", true);
                $('#nokpPemunya').attr("readonly", true);
                $('#noTelPemunya').attr("readonly", true);
                $('#alamatPemunya1').attr("readonly", true);
                $('#alamatPemunya2').attr("readonly", true);
                $('#alamatPemunya3').attr("readonly", true);
                $('#alamatPemunya4').attr("readonly", true);
                $('#poskodPemunya').attr("readonly", true);
                $('#negeriPemunya').attr("readonly", true);
                $('#kodNegeriPemunyaTemp').attr("readonly", true);

            }else{
                $('#namaPemunya').val($('').val());
                $('#nokpPemunya').val($('').val());
                $('#noTelPemunya').val($('').val());
                $('#alamatPemunya1').val($('').val());
                $('#alamatPemunya2').val($('').val());
                $('#poskodPemunya').val($('').val());
                $('#negeriPemunya').val($('').val());
                $('#negeriPemunya').show();
                $('#kodNegeriPemunyaTemp').hide();

                $('#namaPemunya').attr("readonly", false);
                $('#nokpPemunya').attr("readonly", false);
                $('#noTelPemunya').attr("readonly", false);
                $('#alamatPemunya1').attr("readonly", false);
                $('#alamatPemunya2').attr("readonly", false);
                $('#alamatPemunya3').attr("readonly", false);
                $('#alamatPemunya4').attr("readonly", false);
                $('#poskodPemunya').attr("readonly", false);
                $('#negeriPemunya').attr("readonly", false);
            }
        }

        function checkedPemunya(){
            //alert($('#namaPemunya').val());
            //alert($('#namaSuspekTemp').val());
            if($('#namaPemunya').val() == $('#namaSuspekTemp').val()){
                //alert("sama");
                document.getElementById("checkSame").checked = true;
                $('#negeriPemunya').hide();
                $('#namaPemunya').attr("readonly", true);
                $('#nokpPemunya').attr("readonly", true);
                $('#noTelPemunya').attr("readonly", true);
                $('#alamatPemunya1').attr("readonly", true);
                $('#alamatPemunya2').attr("readonly", true);
                $('#alamatPemunya3').attr("readonly", true);
                $('#alamatPemunya4').attr("readonly", true);
                $('#poskodPemunya').attr("readonly", true);
                $('#kodNegeriPemunyaTemp').attr("readonly", true);
            }

        }
        
        function findOKSPemunya(id){
            //alert(id);
            if(id != ""){
                var add = 'add';
                if(id != "L"){
                    $.get('${pageContext.request.contextPath}/penguatkuasaan/maklumat_barang_tahanan?findSuspek&idOks='+id+'&pagePass='+add,
                    function(data){
                        $("#infoOks").replaceWith($('#infoOks', $(data)));

                        $('#namaPemunya').val($('#namaOks').val());
                        $('#noTelPemunya').val($('#noTelefonOks').val());
                        $('#alamatPemunya1').val($('#alamat1Oks').val());
                        $('#alamatPemunya2').val($('#alamat2Oks').val());
                        $('#alamatPemunya3').val($('#alamat3Oks').val());
                        $('#alamatPemunya4').val($('#alamat4Oks').val());
                        $('#poskodPemunya').val($('#poskodOks').val());
                        $('#negeriPemunya').val($('#negeriOks').val());
                        $('#kodNegeriPemunyaTemp').val($('#namaNegeriOks').val());
                        $('#negeriPemunya').hide();
                        $('#kodNegeriPemunyaTemp').show();
                      
                      
                        $('#kodJenisPengenalanPemunya').val($('#jenisPengenalanOks').val());
                        $('#kodJenisPengenalanPemunya').hide();
                        $('#kodPengenalanPemunyaDisplayText').show();
                        $('#kodPengenalanPemunyaDisplayText').val($('#jenisPengenalanNamaOks').val());
                      


                        $('#namaPemunya').attr("readonly", true);
                        $('#nokpPemunya').attr("readonly", true);
                        $('#noTelPemunya').attr("readonly", true);
                        $('#alamatPemunya1').attr("readonly", true);
                        $('#alamatPemunya2').attr("readonly", true);
                        $('#alamatPemunya3').attr("readonly", true);
                        $('#alamatPemunya4').attr("readonly", true);
                        $('#poskodPemunya').attr("readonly", true);
                        $('#negeriPemunya').attr("readonly", true);
                        $('#kodNegeriPemunyaTemp').attr("readonly", true);
                        $('#kodPengenalanPemunyaDisplayText').attr("readonly", true);
                      
                        //change label at jenis pengenalan
                      
                        var skillsSelect = document.getElementById("kodJenisPengenalanPemunya");
                        var selectedText = skillsSelect.options[skillsSelect.selectedIndex].text;
                      
                        $('#kodJenisPengenalanPemunya').attr("disabled", true);
                        if($('#kodJenisPengenalanPemunya').val() == 'B'){
                            $('#noPengenalanBaruPemunyaIcText').val($('#noPengenalanOks').val());
                            document.getElementById('noPengenalanBaruPemunyaText').innerHTML =selectedText+" :";
                          
                            document.getElementById("noPengenalanBaruPemunya").style.visibility = 'visible';
                            document.getElementById("noPengenalanBaruPemunya").style.display = '';
                            $('#noPengenalanLainPemunya').hide();

                            $('#noPengenalanLainPemunya').attr("disabled", true);
                            $('#noPengenalanBaruPemunya').attr("disabled", false);
                        }else{
                            $('#noPengenalanLainPemunya').val($('#noPengenalanOks').val());
                            document.getElementById('noPengenalanLainPemunyaText').innerHTML =selectedText+" :";
                          
                            $('#noPengenalanLainPemunya').show();
                            document.getElementById("noPengenalanBaruPemunya").style.visibility = 'hidden';
                            document.getElementById("noPengenalanBaruPemunya").style.display = 'none';

                            $('#noPengenalanBaruPemunya').attr("disabled", true);
                            $('#noPengenalanLainPemunya').attr("disabled", false);
                        }
                      
                    }, 'html');
                }else{
                    $('#namaPemunya').val('');
                    $('#noTelPemunya').val('');
                    $('#alamatPemunya1').val('');
                    $('#alamatPemunya2').val('');
                    $('#alamatPemunya3').val('');
                    $('#alamatPemunya4').val('');
                    $('#poskodPemunya').val('');
                    $('#negeriPemunya').val('');
                    $('#kodNegeriPemunyaTemp').val('');
                    $('#kodJenisPengenalanPemunya').val('');
                    $('#noPengenalanBaruPemunyaIcText').val('');
                    $('#noPengenalanLainPemunya').val('');
                    $('#negeriPemunya').show();
                    $('#kodNegeriPemunyaTemp').hide();
                      
                    $('#namaPemunya').attr("readonly", false);
                    $('#nokpPemunya').attr("readonly", false);
                    $('#noTelPemunya').attr("readonly", false);
                    $('#alamatPemunya1').attr("readonly", false);
                    $('#alamatPemunya2').attr("readonly", false);
                    $('#alamatPemunya3').attr("readonly", false);
                    $('#alamatPemunya4').attr("readonly", false);
                    $('#poskodPemunya').attr("readonly", false);
                    $('#negeriPemunya').attr("readonly", false);
                    $('#kodNegeriPemunyaTemp').attr("readonly", true);
                    $('#kodJenisPengenalanPemunya').attr("disabled", false);
                    //                      $('#kodPengenalanPemunyaDisplayText').attr("readonly", false);
                    $('#kodPengenalanPemunyaDisplayText').hide();
                    $('#kodJenisPengenalanPemunya').show();
                      
                }
                 
            }
        }
          
        function jenisPengenalanPemunya(){
            //              alert($('#kodJenisPengenalanPemunya').val());
             
              
            var skillsSelect = document.getElementById("kodJenisPengenalanPemunya");
            var selectedText = skillsSelect.options[skillsSelect.selectedIndex].text;
            alert(selectedText);
              
            //              $('#noPengenalanLainPemunyaText').val(selectedText);
            document.getElementById('noPengenalanLainPemunyaText').innerHTML =selectedText+" :";
            if($('#kodJenisPengenalanPemunya').val() == 'B'){
                document.getElementById("noPengenalanBaruPemunya").style.visibility = 'visible';
                document.getElementById("noPengenalanBaruPemunya").style.display = '';
                $('#noPengenalanLainPemunya').hide();

                $('#noPengenalanLainPemunya').attr("disabled", true);
                $('#noPengenalanBaruPemunya').attr("disabled", false);
            }else{
                $('#noPengenalanLainPemunya').show();
                document.getElementById("noPengenalanBaruPemunya").style.visibility = 'hidden';
                document.getElementById("noPengenalanBaruPemunya").style.display = 'none';

                $('#noPengenalanBaruPemunya').attr("disabled", true);
                $('#noPengenalanLainPemunya').attr("disabled", false);
            }
        }
    

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>

<s:form beanclass="etanah.view.penguatkuasaan.MaklumatBarangTahananActionBean" id="form1" name="form1">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Barang Rampasan</legend>
            <div class="content">

                <p>
                    <label>Jenis Barang Rampasan</label>
                    <s:radio name="barangRampasan.kodKategoriItemRampasan.kod" id="kod1" value="B" disabled="true" /> Bukan Kenderaan
                    <s:radio name="barangRampasan.kodKategoriItemRampasan.kod" id="kod" value="K" disabled="true" /> Kenderaan
                </p>

                <c:if test="${actionBean.barangRampasan.kodKategoriItemRampasan.kod eq 'K'}">
                    <p>
                        <label>Jenis Kenderaan :</label>
                        <s:text name="item" onchange="this.value=this.value.toUpperCase();" id="item1" maxlength="50"/>
                    </p>
                    <p>
                        <label>No. Pendaftaran :</label>
                        <s:text name="nomborPendaftaran" onchange="this.value=this.value.toUpperCase();" id="nomborPendaftaran" maxlength="20"/>
                    </p>
                    <p>
                        <label>No. Siri :</label>
                        <s:text name="noSiri" onchange="this.value=this.value.toUpperCase();" id="noSiri" maxlength="30" size="40"/>
                    </p>
                    <p>
                        <label>No. Enjin :</label>
                        <s:text name="nomborEnjin" onchange="this.value=this.value.toUpperCase();" id="nomborEnjin" maxlength="30" size="40"/>
                    </p>
                    <p>
                        <label>No. Casis :</label>
                        <s:text name="nomborCasis" onchange="this.value=this.value.toUpperCase();" id="nomborCasis" maxlength="30" size="40"/>
                    </p>
                    <p>
                        <label>Buatan :</label>
                        <s:text name="buatan" onchange="this.value=this.value.toUpperCase();" id="buatan" maxlength="30" size="40"/>
                    </p>
                    <p>
                        <label>Nama Model :</label>
                        <s:text name="namaModel" onchange="this.value=this.value.toUpperCase();" id="namaModel" maxlength="30" size="40"/>
                    </p>
                    <p>
                        <label>Keupayaan Enjin :</label>
                        <s:text name="kapasitiEnjin" onchange="this.value=this.value.toUpperCase();" id="kapasitiEnjin" maxlength="10"/>
                    </p>
                    <p>
                        <label>Bahan Bakar :</label>
                        <s:select name="kodBahanBakar" id="kodBahanBakar">
                            <s:option value="">Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodBahanBakar}" label="nama" value="kod" />
                        </s:select>
                    </p>
                    <p><label>&nbsp;</label>
                        <font color="red" size="1">Cth : Diesel, Petrol</font>
                    </p>
                    <p>
                        <label>Warna :</label>
                        <s:text name="warna" onchange="this.value=this.value.toUpperCase();" id="warna" maxlength="30"/>
                    </p>
                    <p>
                        <label>Kelas Kegunaan :</label>
                        <s:select name="kkkk" id="kodKegunaanKenderaan" >
                            <s:option value="">Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodKegunaanKenderaan}" label="nama" value="kod" />
                        </s:select>
                    </p>
                    <p><label>&nbsp;</label>
                        <font color="red" size="1">Cth : Barangan Regid, Barangan Tak Regid</font>
                    </p>
                    <p>
                        <label>Jenis Badan :</label>
                        <s:select name="kodjbk" id="kodJenisBadanKenderaan" >
                            <s:option value="">Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodJenisBadanKenderaan}" label="nama" value="kod" />
                        </s:select>
                    </p>
                    <p><label>&nbsp;</label>
                        <font color="red" size="1">Cth : Lori regid-dumper/tipper</font>
                    </p><p>
                        <label>Tahun Dibuat :</label>
                        <s:text name="tahunDibuat"  id="tahunDibuat" onkeyup="validateNumber(this,this.value);" maxlength="4"/>
                    </p>
                    <p>
                        <label>Tarikh Pendaftaran :</label>
                        <s:text name="tarikhPendaftaran" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="datepicker" />&nbsp;
                    </p>
                    <p><label>&nbsp;</label>
                        <font color="red" size="1">cth : hh / bb / tttt</font>
                    </p>
                    <p>
                        <label>Status Pemunya :</label>
                        <s:radio name="statusPemunya" value="S" />&nbsp;Syarikat
                        <s:radio name="statusPemunya" value="I" />&nbsp;Individu
                    </p>
                    <p>
                        <label>Muatan Tempat Duduk :</label>
                        <s:text name="muatanTempatDuduk" onkeyup="validateNumber(this,this.value);" id="muatanTempatDuduk" maxlength="3"/>
                    </p>
                    <p>
                        <label>Kadar Lesen Kenderaan Bermotor :</label>
                        <s:text name="kadarLesen" onkeyup="validateNumber(this,this.value);" id="kadarLesen" maxlength="5"/>
                    </p>
                    <p>
                        <label>Tarikh Kenderaan Dirampas:</label>
                        <s:text name="tarikhRampas" class="datepicker" formatPattern="dd/MM/yyyy" id="datepicker1" />&nbsp;
                    </p>
                    <p><label>&nbsp;</label>
                        <font color="red" size="1">cth : hh / bb / tttt</font>
                    </p>
                    <p>
                        <label>Masa Kenderaan Dirampas:</label>
                        <s:select name="jam"  id="jam" style="width:56px">
                            <s:option value="">Jam</s:option>
                            <s:option value="01">01</s:option>
                            <s:option value="02">02</s:option>
                            <s:option value="03">03</s:option>
                            <s:option value="04">04</s:option>
                            <s:option value="05">05</s:option>
                            <s:option value="06">06</s:option>
                            <s:option value="07">07</s:option>
                            <s:option value="08">08</s:option>
                            <s:option value="09">09</s:option>
                            <s:option value="10">10</s:option>
                            <s:option value="11">11</s:option>
                            <s:option value="12">12</s:option>
                        </s:select>:
                        <s:select name="minit" id="minit" style="width:60px">
                            <s:option value="">Minit</s:option>
                            <s:option value="00">00</s:option>
                            <s:option value="05">05</s:option>
                            <s:option value="10">10</s:option>
                            <s:option value="15">15</s:option>
                            <s:option value="20">20</s:option>
                            <s:option value="25">25</s:option>
                            <s:option value="30">30</s:option>
                            <s:option value="35">35</s:option>
                            <s:option value="40">40</s:option>
                            <s:option value="45">45</s:option>
                            <s:option value="50">50</s:option>
                            <s:option value="55">55</s:option>
                        </s:select>:

                        <s:select name="ampm" id="ampm" style="width:80px">
                            <s:option value="">Pilih</s:option>
                            <s:option value="AM">PAGI</s:option>
                            <s:option value="PM">PETANG</s:option>
                        </s:select>
                    </p>
                    <p>
                        <label>Jenis Batuan :</label>
                        <s:text name="jenisBatuan" onchange="this.value=this.value.toUpperCase();" id="jenisBatuan" maxlength="50" size="40" />
                    </p>
                </c:if>


                <c:if test="${actionBean.barangRampasan.kodKategoriItemRampasan.kod eq 'B'}">
                    <p>
                        <label><em>*</em>Barang Yang Dirampas :</label>
                        <s:text name="item" onchange="this.value=this.value.toUpperCase();" id="barangRampas" maxlength="50" size="40"/>
                    </p>
                    <p>
                        <label><em>*</em>Tarikh Barang Dirampas:</label>
                        <s:text name="tarikhRampas" class="datepicker" formatPattern="dd/MM/yyyy" id="datepicker1" />&nbsp;
                        <font color="red" size="1">cth : hh / bb / tttt</font>
                    </p>
                    <p>
                        <label><em>*</em>Masa Barang Dirampas:</label>
                        <s:select name="jam"  id="jam" style="width:56px">
                            <s:option value="">Jam</s:option>
                            <s:option value="01">01</s:option>
                            <s:option value="02">02</s:option>
                            <s:option value="03">03</s:option>
                            <s:option value="04">04</s:option>
                            <s:option value="05">05</s:option>
                            <s:option value="06">06</s:option>
                            <s:option value="07">07</s:option>
                            <s:option value="08">08</s:option>
                            <s:option value="09">09</s:option>
                            <s:option value="10">10</s:option>
                            <s:option value="11">11</s:option>
                            <s:option value="12">12</s:option>
                        </s:select>:
                        <s:select name="minit" id="minit" style="width:60px">
                            <s:option value="">Minit</s:option>
                            <s:option value="00">00</s:option>
                            <s:option value="05">05</s:option>
                            <s:option value="10">10</s:option>
                            <s:option value="15">15</s:option>
                            <s:option value="20">20</s:option>
                            <s:option value="25">25</s:option>
                            <s:option value="30">30</s:option>
                            <s:option value="35">35</s:option>
                            <s:option value="40">40</s:option>
                            <s:option value="45">45</s:option>
                            <s:option value="50">50</s:option>
                            <s:option value="55">55</s:option>
                        </s:select>:

                        <s:select name="ampm" id="ampm" style="width:80px">
                            <s:option value="">Pilih</s:option>
                            <s:option value="AM">PAGI</s:option>
                            <s:option value="PM">PETANG</s:option>
                        </s:select>
                    </p>
                    <p>
                        <label><em>*</em>Kuantiti :</label>
                        <s:text name="kuantiti" id="kuantiti" onkeyup="validateNumber(this,this.value);" maxlength="3"/>
                        <s:select name="kuantitiUnit" id="kuantitiUnit" value="${actionBean.kuantitiUnit}">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="Ekor">Ekor</s:option>
                            <s:option value="Kilogram">Kilogram</s:option>
                            <s:option value="Meterpadu">Meter Padu</s:option>
                            <s:option value="Tan">Tan</s:option>
                            <s:option value="Tan Metrik">Tan Metrik</s:option>
                            <s:option value="Unit">Unit</s:option>  
                        </s:select>
                    </p>
                </c:if>
                <p>
                    <label>Tempat Simpanan :</label>
                    <s:text name="tempatSimpanan" onchange="this.value=this.value.toUpperCase();" id="tempatSimpanan" maxlength="50" size="40" />
                </p>
                <p>
                    <label>Tempat Pengambilan :</label>
                    <s:text name="tempatPengambilan" onchange="this.value=this.value.toUpperCase();" id="tempatPengambilan" maxlength="50" size="40"/>
                </p>
                <p>
                    <label>Tempat Penghantaran :</label>
                    <s:text name="tempatPenghantaran" onchange="this.value=this.value.toUpperCase();" id="tempatPenghantaran" maxlength="50" size="40"/>
                </p>
                <p>
                    <label><em>*</em>Lokasi Tangkapan :</label>
                    <s:text name="tempatTangkap" onchange="this.value=this.value.toUpperCase();" id="tempatTangkap" maxlength="50" size="40"/>
                </p>
                <p>
                    <label>Tempat Bongkar :</label>
                    <s:text name="tempatBongkar" onchange="this.value=this.value.toUpperCase();" id="tempatBongkar" maxlength="50" size="40"/>
                </p>
                <p>
                    <label>Lain-lain Hal :</label>
                    <s:textarea name="catatan" rows="5" cols="40" onchange="this.value=this.value.toUpperCase();" id="catatan" onkeydown="limitText(this,999);" onkeyup="limitText(this,999);"/>&nbsp;
                </p>
                <p>
                    <label>Status :</label>
                    <s:select name="barangRampasanStatus" id="barangRampasanStatus" >
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.senaraiKodStatus}" label="nama" value="kod" />
                        <s:option value="T">Tiada</s:option>
                    </s:select>&nbsp;
                </p>
                <p>
                    <label><em>*</em>Laporan Polis :</label>
                    <s:select name="idOperasiAgensi" id="idOperasiAgensi" >
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.senaraiOperasiAgensi}" label="noRujukan" value="idOperasiAgensi" />
                        <s:option value="T">Tiada</s:option>
                    </s:select>&nbsp;
                </p>

                <br>
            </div>
        </fieldset>

        <fieldset class="aras1">
            <legend>Maklumat Suspek</legend>
            <c:if test="${actionBean.kodNegeriPermohonan eq '04'}">
                <c:choose>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '425A' || actionBean.permohonan.kodUrusan.kod eq '426' || actionBean.permohonan.kodUrusan.kod eq '999'}">
                        <%--for sek425 & sek426 & sek999, senarai oks based on id OP--%>
                        <p>
                            <label><em>*</em>Nama :</label>
                            <s:select name="namaSuspek" id="namaSuspekWajib" onchange="findSuspek(this.value);" style="width:160px">
                                <s:option value=""> Sila Pilih </s:option>
                                <c:forEach items="${actionBean.senaraiOKS}" var="line">
                                    <c:if test="${line.operasiPenguatkuasaan.idOperasi eq actionBean.idOp}">
                                        <s:option value="${line.idOrangKenaSyak}">${line.nama}</s:option>
                                    </c:if>
                                </c:forEach>
                            </s:select>
                        </p>
                    </c:when>
                    <c:otherwise>
                        <p>
                            <label>Nama :</label>
                            <s:select name="namaSuspek" id="namaSuspek" style="width:160px" onchange="findSuspek(this.value);">
                                <s:option value="">Pilih</s:option>
                                <c:forEach items="${actionBean.senaraiOKS}" var="line">
                                    <s:option value="${line.idOrangKenaSyak}">${line.nama}</s:option>
                                </c:forEach>
                            </s:select>
                        </p>
                    </c:otherwise>
                </c:choose>
            </c:if>
            <c:if test="${actionBean.kodNegeriPermohonan eq '05'}">
                <p>
                    <label><em>*</em> Nama :</label>
                    <s:select name="namaSuspek" id="namaSuspekWajib" onchange="findSuspek(this.value);" style="width:160px">
                        <s:option value=""> Sila Pilih </s:option>
                        <c:forEach items="${actionBean.senaraiOKS}" var="line">
                            <c:if test="${line.operasiPenguatkuasaan.idOperasi eq actionBean.idOp}">
                                <s:option value="${line.idOrangKenaSyak}">${line.nama}</s:option>
                            </c:if>
                        </c:forEach>
                    </s:select>
                </p>
            </c:if>
            <div id="suspekInfo">
                <p>
                    <label>No. Pengenalan :</label>
                    <s:text name="noPengenalanSuspek" onchange="this.value=this.value.toUpperCase();" id="noPengenalanSuspek" maxlength="12" size="20" readonly="true"/>
                    <s:hidden name="namaSuspekTemp" id="namaSuspekTemp"/>
                    <s:hidden name="kodNegeriSuspek" id="kodNegeriSuspek"/>
                </p>
                <p>
                    <label>No. Telefon :</label>
                    <s:text name="noTelSuspek" onchange="this.value=this.value.toUpperCase();" id="noTelSuspek" maxlength="12" size="20" readonly="true"/>
                </p>
                <p>
                    <label>Alamat :</label>
                    <s:text name="alamat1Suspek" id="alamat1Suspek" onchange="this.value=this.value.toUpperCase();" maxlength="50" size="40" readonly="true"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:text name="alamat2Suspek" id="alamat2Suspek" onchange="this.value=this.value.toUpperCase();" maxlength="50" size="40" readonly="true"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:text name="alamat3Suspek" id="alamat3Suspek" onchange="this.value=this.value.toUpperCase();" maxlength="50" size="40" readonly="true"/>
                </p>
                <p>
                    <label>Poskod :</label>
                    <s:text name="poskodSuspek" onkeyup="validateNumber(this,this.value);" id="poskodSuspek" maxlength="5" readonly="true"/>
                    <font color="red" size="1">cth : 78000 </font>
                </p>
            </div>
        </fieldset>

        <fieldset class="aras1">
            <legend>
                Maklumat Pemunya/Pemilik
            </legend>
            <c:if test="${actionBean.kodNegeriPermohonan eq '05'}">
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="checkSame" onclick="copyInfoSuspek();" id="checkSame"/> Maklumat Pemunya/Pemilik sama dengan suspek
                </p> 
            </c:if>
            <div id="infoOks" style="visibility: hidden; display: none">
                <input type="text" id="namaOks" value="${actionBean.oks.nama}">
                <input type="text" id="selectedIdOks" value="${actionBean.oks.idOrangKenaSyak}">
                <input type="text" id="jenisPengenalanOks" value="${actionBean.oks.kodJenisPengenalan.kod}">
                <input type="text" id="jenisPengenalanNamaOks" value="${actionBean.oks.kodJenisPengenalan.nama}">
                <input type="text" id="noPengenalanOks" value="${actionBean.oks.noPengenalan}">
                <input type="text" id="noTelefonOks" value="${actionBean.oks.noTelefon1}">
                <input type="text" id="alamat1Oks" value="${actionBean.oks.alamat.alamat1}">
                <input type="text" id="alamat2Oks" value="${actionBean.oks.alamat.alamat2}">
                <input type="text" id="alamat3Oks" value="${actionBean.oks.alamat.alamat3}">
                <input type="text" id="alamat4Oks" value="${actionBean.oks.alamat.alamat4}">
                <input type="text" id="poskodOks" value="${actionBean.oks.alamat.poskod}">
                <input type="text" id="negeriOks" value="${actionBean.oks.alamat.negeri.kod}">
                <input type="text" id="namaNegeriOks" value="${actionBean.oks.alamat.negeri.nama}">
            </div>
            <div id="maklumatPemunyaInfo">
                <c:if test="${actionBean.kodNegeriPermohonan eq '04'}">
                    <c:choose>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '425A' || actionBean.permohonan.kodUrusan.kod eq '426' || actionBean.permohonan.kodUrusan.kod eq '999'}">
                            <p>
                                <label><em>*</em> Sila Pilih :</label>
                                <s:select name="chooseOKS" id="chooseOKS" onchange="findOKSPemunya(this.value);" style="width:160px">
                                    <s:option value=""> Sila Pilih </s:option>
                                    <c:forEach items="${actionBean.senaraiOKS}" var="line">
                                        <c:if test="${line.operasiPenguatkuasaan.idOperasi eq actionBean.idOp}">
                                            <s:option value="${line.idOrangKenaSyak}">${line.nama}</s:option>
                                        </c:if>
                                    </c:forEach>
                                    <s:option value="L">Lain-lain</s:option>
                                </s:select>
                            </p>
                            <p>
                                <label><em>*</em>Nama Pemunya Berdaftar :</label>
                                <s:text name="namaPemunya" onchange="this.value=this.value.toUpperCase();" id="namaPemunya" maxlength="50" size="40"/>
                            </p>
                            <p>
                                <label>Jenis Pengenalan :</label>
                                <input type="text" id="kodPengenalanPemunyaDisplayText" value="">
                                <s:select name="kodJenisPengenalanPemunya" id="kodJenisPengenalanPemunya" style="width:139px;" onchange="jenisPengenalanPemunya()" value="${actionBean.kodJenisPengenalanPemunya}">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" sort="nama" />
                                </s:select>
                            </p>
                            <p id="noPengenalanLainPemunya">
                                <label id="noPengenalanLainPemunyaText">No.Pengenalan :</label>
                                <s:text name="noPengenalanPemunya" id="noPengenalanLainPemunya" maxlength="12" onkeyup="this.value=this.value.toUpperCase();" />
                                &nbsp;
                            </p>

                            <p id="noPengenalanBaruPemunya" style="visibility: hidden; display: none">
                                <label id="noPengenalanBaruPemunyaText">No.Pengenalan :</label>
                                <s:text name="noPengenalanPemunya" id="noPengenalanBaruPemunyaIcText" maxlength="12" onkeyup="validateNumber(this,this.value);"/>
                                <font color="red" size="1">cth : 850510071213 </font>
                                &nbsp;
                            </p>
                        </c:when>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq '422' || actionBean.permohonan.kodUrusan.kod eq '423'}">
                            <p>
                                <label><em>*</em>Nama Pemunya Berdaftar :</label>
                                <s:text name="namaPemunya" onchange="this.value=this.value.toUpperCase();" id="namaPemunya" maxlength="50" size="40"/>
                            </p>

                            <p>
                                <label>No. Kad Pengenalan :</label>
                                <s:text name="noPengenalanPemunya" onchange="this.value=this.value.toUpperCase();" id="nokpPemunya" maxlength="12" size="20"/>
                                <font color="red" size="1">cth : 850510075342 </font>
                            </p>
                        </c:when>
                        <c:otherwise>
                            <p>
                                <label>Nama Pemunya Berdaftar :</label>
                                <s:text name="namaPemunya" onchange="this.value=this.value.toUpperCase();" id="namaPemunya" maxlength="50" size="40"/>
                            </p>
                            <p>

                                <label>No. Kad Pengenalan :</label>
                                <s:text name="noPengenalanPemunya" onchange="this.value=this.value.toUpperCase();" id="nokpPemunya" maxlength="12" size="20"/>
                                <font color="red" size="1">cth : 850510075342 </font>
                            </p>
                        </c:otherwise>
                    </c:choose>

                </c:if>
                <c:if test="${actionBean.kodNegeriPermohonan eq '05'}">
                    <p>
                        <label><em>*</em> Nama Pemunya Berdaftar :</label>
                        <s:text name="namaPemunya" onchange="this.value=this.value.toUpperCase();" id="namaPemunya" maxlength="50" size="40"/>
                    </p>

                    <p>
                        <label>No. Kad Pengenalan :</label>
                        <s:text name="noPengenalanPemunya" onchange="this.value=this.value.toUpperCase();" id="nokpPemunya" maxlength="12" size="20"/>
                        <font color="red" size="1">cth : 850510075342 </font>
                    </p>
                </c:if>
                <p>
                    <label>No Telefon :</label>
                    <s:text name="noTelPemunya" id="noTelPemunya" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);"/>&nbsp;
                </p>
                <p>
                    <label>Alamat :</label>
                    <s:text name="alamat1" id="alamatPemunya1" onchange="this.value=this.value.toUpperCase();" maxlength="50" size="40"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:text name="alamat2" id="alamatPemunya2" onchange="this.value=this.value.toUpperCase();" maxlength="50" size="40"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:text name="alamat3" id="alamatPemunya3" onchange="this.value=this.value.toUpperCase();" maxlength="50" size="40"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:text name="alamat4" id="alamatPemunya4" onchange="this.value=this.value.toUpperCase();" maxlength="50" size="40"/>
                </p>
                <p>
                    <label>Poskod :</label>
                    <s:text name="poskod" onkeyup="validateNumber(this,this.value);" id="poskodPemunya" maxlength="5"/>
                    <font color="red" size="1">cth : 78000 </font>
                </p>
                <p>
                    <label>Negeri :</label>
                    <s:select name="kn" id="negeriPemunya">
                        <s:option value="">Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                    </s:select>
                    <s:text name="kodNegeriPemunyaTemp" id="kodNegeriPemunyaTemp" onchange="this.value=this.value.toUpperCase();" maxlength="50" size="40"/>
                </p>


            </div>
        </fieldset>

        <div id="pemegangPermit">
            <fieldset class="aras1">
                <legend>Maklumat Pemegang Permit</legend>

                <p>
                    <label>No Syarikat :</label>
                    <s:text name="noSyarikat" onchange="this.value=this.value.toUpperCase();" id="noSyarikat" maxlength="30" size="40"/>
                </p>
                <p>
                    <label>Nama Pemegang Permit :</label>
                    <s:text name="pemegangPermit" onchange="this.value=this.value.toUpperCase();" id="pemegangPermit" maxlength="30" size="40"/>
                </p>
                <p>
                    <label>Jenis Pengenalan Pemegang Permit:</label>
                    <s:select name="jppermit" id="kodJenisPengenalanPemegangPermit" style="width:139px;" onchange="jenisPengenalanPemegangPermit()" >
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" sort="nama" />
                    </s:select>
                    &nbsp;
                </p>
                <p id="noPengenalanLainPemegangPermit">
                    <label>No.Pengenalan Pemegang Permit :</label>
                    <s:text name="nomborPengenalanPemegangPermit" id="penyerahNoPengenalanLainPemegangPermit" maxlength="12" onkeyup="this.value=this.value.toUpperCase();"/>
                    &nbsp;
                </p>

                <p id="noPengenalanBaruPemegangPermit" style="visibility: hidden; display: none">
                    <label>No.Pengenalan Pemegang Permit :</label>
                    <s:text name="nomborPengenalanPemegangPermit" id="penyerahNoPengenalanBaruPemegangPermit" maxlength="12" onkeyup="validateNumber(this,this.value);"/>
                    <font color="red" size="1">cth : 850510071213 </font>
                    &nbsp;
                </p>
                <p>
                    <label>Alamat :</label>
                    <s:text name="alamat1PemegangPermit" onchange="this.value=this.value.toUpperCase();" id="alamat1PemegangPermit" maxlength="50" size="40"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:text name="alamat2PemegangPermit" onchange="this.value=this.value.toUpperCase();" id="alamat2PemegangPermit" maxlength="50" size="40"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:text name="alamat3PemegangPermit" onchange="this.value=this.value.toUpperCase();" id="alamat3PemegangPermit" maxlength="50" size="40"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:text name="alamat4PemegangPermit" onchange="this.value=this.value.toUpperCase();" id="alamat4PemegangPermit" maxlength="50" size="40"/>
                </p>
                <p>
                    <label>Poskod :</label>
                    <s:text name="poskodPemegangPermit" onkeyup="validateNumber(this,this.value);" id="poskodPemegangPermit" maxlength="5"/>
                    <font color="red" size="1">cth : 27000 </font>
                </p>
                <p>
                    <label>Negeri :</label>
                    <s:select name="knpermit">
                        <s:option value="">Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                    </s:select>
                </p>
            </fieldset>
        </div>

        <fieldset class ="aras1">
            <legend>Maklumat Penjamin</legend>
            &nbsp;
            <p>

                <label>Nama Penjamin:</label>
                <s:text name="namaPenjamin" id="namaPenjamin" onchange="this.value=this.value.toUpperCase();" maxlength="249"/>&nbsp;
            </p>

            <p>
                <label>Jenis Pengenalan :</label>
                <s:select name="kp"  style="width:139px;" id="pengenalanPenjamin" onchange="jenisPengenalanPenjamin()">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" sort="nama" />
                </s:select>
                &nbsp;
            </p>

            <%--<p>
                <label>No.Pengenalan :</label>
                <s:text name="noPengenalanPenjamin" maxlength="12" />
                <font color="red" size="1">cth : 850510075342 </font>&nbsp;
            </p>--%>
            <p id="noPengenalanLainPenjamin">
                <label>No.Pengenalan :</label>
                <s:text name="noPengenalanPenjamin" id="penyerahNoPengenalanLainPenjamin" maxlength="12" size="20" onkeyup="this.value=this.value.toUpperCase();"/>
                <%--<font color="red" size="1">cth : 850510075342 </font>--%>
                &nbsp;
            </p>

            <p id="noPengenalanBaruPenjamin" style="visibility: hidden; display: none">
                <label>No.Pengenalan :</label>
                <s:text name="noPengenalanPenjamin" id="penyerahNoPengenalanBaruPenjamin" maxlength="12" size="20" onkeyup="validateNumber(this,this.value);" onblur="javascript:findDOB(this.value)"/>
                <font color="red" size="1">cth : 850510071213 </font>
                &nbsp;
            </p>
            <p>
                <label>Alamat :</label>
                <s:text name="alamat1Penjamin" onchange="this.value=this.value.toUpperCase();" maxlength="49" size="40"/>&nbsp;
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="alamat2Penjamin" onchange="this.value=this.value.toUpperCase();" maxlength="49" size="40"/>&nbsp;</p>
            <p>
                <label> &nbsp; </label>
                <s:text name="alamat3Penjamin" onchange="this.value=this.value.toUpperCase();" maxlength="49" size="40"/>&nbsp;</p>
            <p>
                <label> &nbsp; </label>
                <s:text name="alamat4Penjamin" onchange="this.value=this.value.toUpperCase();" maxlength="49" size="40"/>&nbsp; </p>

            <p>
                <label>Poskod :</label>
                <s:text name="poskodPenjamin" id="poskod" size="19" maxlength="5" onkeyup="validateNumber(this,this.value);" onchange="validatePoskodLength(this.value);"/>&nbsp;
            </p>
            <p>
                <label>Negeri :</label>
                <s:select name="np" >
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>&nbsp;
            </p>
            <p>
                <label>Jantina :</label>
                <s:select name="kj"  style="width:139px;">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJantina}" label="nama" value="kod" sort="nama" />
                </s:select>
                &nbsp;
            </p>

            <%-- <p>
                 <label>Jenis Warganegara :</label>
                 <s:select name="kwg"  style="width:139px;">
                     <s:option value="">Sila Pilih</s:option>
                     <s:options-collection collection="${list.senaraiWarganegara}"  label="nama" value="kod" sort="nama" />
                 </s:select>
                 &nbsp;
             </p>--%>

            <p>
                <label>No Telefon :</label>
                <s:text name="noTelPenjamin" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);"/>&nbsp;
            </p>

            <p>
                <label>No Telefon Bimbit:</label>
                <s:text name="noTelBimbitPenjamin" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);"/>&nbsp;
            </p>

            <p>

                <label>Hubungan:</label>
                <s:text name="hubungan" id="hubungan" onkeyup="this.value=this.value.toUpperCase();" maxlength="49"/>&nbsp;
            </p>

            <p><label>&nbsp;</label>
                <%--<s:text name="idOperasi" value="${actionBean.OperasiPenguatkuasaan.idOperasi}"/>--%>
                <s:hidden name="idBarang" id="idBarang" value="${actionBean.barangRampasan.idBarang}"/>
                <s:hidden name="idOp"/>
                <%--<s:submit name="edit" id="edit" class="btn" value="Simpan" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');"/>--%>
                <s:button name="editBarang" id="simpan" value="Simpan" class="btn" onclick="if(validateForm())save(this.name,this.form);"/>
                <s:button name="tutup" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>

        </fieldset>
    </div>
</s:form>
