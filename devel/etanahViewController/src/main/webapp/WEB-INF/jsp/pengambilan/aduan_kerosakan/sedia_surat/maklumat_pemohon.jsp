<%-- 
    Document   : maklumat_pemohon
    Created on : 19-Jan-2011, 11:01:49
    Author     : nordiyana
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<%--<%@ taglib uri="http://java.sun.com/jstl/core_rt"    prefix="c" %>--%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<c:set var="action" value="/kaunter/terima_bayaran"/>
<c:set var="nextStep" value="Step7"/>
<c:set var="prevStep" value="Step5"/>
<c:set var="word" value="Langkah 5"/>
<c:set var="tmbUrusan" value="Step6a"/>
<c:set var="tmbUrusanVal"/>
<c:if test="${!empty carian}">
    <c:set var="action" value="/daftar/carian"/>
    <c:set var="prevStep" value="Step2"/>
    <c:set var="nextStep" value="Step4"/>
    <c:set var="word" value="Langkah 3"/>
    <c:set var="tmbUrusan" value="Step1"/>
    <c:set var="tmbUrusanVal" value="Y"/>
</c:if>
<%--Added by Aizuddin for SSHMA--%>
<c:if test="${!empty SSHMA}">
    <c:set var="action" value="/daftar/carian_tanpa_bayaran"/>
    <c:set var="prevStep" value="Step2"/>
    <c:set var="nextStep" value="Step3"/>
    <c:set var="word" value="Langkah 3"/>
    <c:set var="tmbUrusan" value="Step1"/>
    <c:set var="tmbUrusanVal" value="Y"/>
</c:if>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/fraction.js" language="Javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript">

    var DELIM = "__^$__";

    function populatePenyerah(btn){
        var url;
        if (btn.id == "carianPenyerah"){
            $('#kod').val('1');
            var idx = $("#idPenyerah").val();
            var jenis = $('#penyerahKod').val();
            var SSHMA = $('#SSHMA').val();
            if (idx == null || idx.length == 0){
                //alert("Sila masukkan ID Penyerah.");
                if (SSHMA){
                    window.open("${pageContext.request.contextPath}/common/req_penyerah_info?showForm&SSHMA=true&popup=true&jenisPenyerah=" + jenis, "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=900,height=1024");
                } else {
                    window.open("${pageContext.request.contextPath}/common/req_penyerah_info?showForm&popup=true&jenisPenyerah=" + jenis, "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=900,height=1024");
                }
                return;
            }
            if (jenis == '0'){
                alert('Sila pilih Jenis Penyerah');
                return;
            } else if (jenis == '01'){ // PEGUAM
                url = "${pageContext.request.contextPath}/common/req_peguam_info?idPeguam=" + idx;
            } else if (jenis == '02'){ // JUBL
                url = "${pageContext.request.contextPath}/common/req_jubl_info?idJUBL=" + idx;
            } else if (jenis == '00') { //Unit dalaman
                url = "${pageContext.request.contextPath}/common/req_unit_info?idUnit=" + idx;
            } else if (jenis == '05') { //Perbadanan Pengurusan
                url = "${pageContext.request.contextPath}/common/req_agensi_info?idAgensi=" + idx;
            } else if (jenis == '06') { //Jabatan Kerajaan
                url = "${pageContext.request.contextPath}/common/req_agensi_info?idAgensi=" + idx;
            } else if (jenis == '07') { //Badan Berkanun
                url = "${pageContext.request.contextPath}/common/req_agensi_info?idAgensi=" + idx;
            }  else if (jenis == '03') { //Jururancang Berlesen
                url = "${pageContext.request.contextPath}/common/req_agensi_info?idAgensi=" + idx;
            }  else if (jenis == '04') { //Jurulelong Berlesen
                url = "${pageContext.request.contextPath}/common/req_lelong_info?idAgensi=" + idx;
            }
        } else if (btn.id == "carianPihak"){
            $('#kod').val('2');
            var jP = $("#penyerahJenisPengenalan").val();
            var noP = $("#penyerahNoPengenalan").val();
            if (jP == null || jP.length == 0 || noP == null || noP.length == 0){
                alert("Sila masukkan Jenis Pengenalan/No.Pengenalan.");
                return;
            }
            url = "${pageContext.request.contextPath}/common/req_pihak_info?jenisPengenalan=" + jP
                + "&noPengenalan=" + noP;
        }

        $.get(url,
        function(data){
            if (data == null || data.length == 0){
                alert("Maklumat tidak dijumpai");
                return;
            }
            var p = data.split(DELIM);
            $('#penyerahJenisPengenalan').val(p[0]);
            $('#penyerahNoPengenalan').val(p[1]);
            $("#penyerahNama").val(p[2]);
            $("#penyerahAlamat1").val(p[3]);
            $("#penyerahAlamat2").val(p[4]);
            $("#penyerahAlamat3").val(p[5]);
            $("#penyerahAlamat4").val(p[6]);
            $("#penyerahPoskod").val(p[7]);
            $("#penyerahNegeri").val(p[8]);
            $("#penyerahNoTelefon").val(p[9]);
            $("#penyerahEmail").val(p[10]);
        });
    }


    function dodacheck(val) {
        //var mikExp = /[1\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\1\|]/;
        var v = $('#penyerahJenisPengenalan').val();
        if(v == 'B') {
            var strPass = val;
            var strLength = strPass.length;
            if(strLength > 12) {
                var tst = val.substring(0, (strLength) - 1);
                $('#penyerahNoPengenalan').val(tst);
            }
            var lchar = val.charAt((strLength) - 1);
            if(isNaN(lchar)) {
                //return false;
                var tst = val.substring(0, (strLength) - 1);
                $('#penyerahNoPengenalan').val(tst);
            }
        }
    }

    function clearNoPengenalan(){
        $('#penyerahNoPengenalan').val('');
    }

    function test(f) {
        $(f).clearForm();
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

    function doUpperCase(id){
        var val = $('#'+id).val().toUpperCase();
        $('#' + id).val(val);
    }

    function updateKodPenyerahInputs(){
        //alert($("#penyerahKod").val());
        if ($("#penyerahKod").val() == '0'){
            // disable button for kod penyerah
            $("#idPenyerah").attr("disabled", "disable");
            $("#carianPenyerah").attr("disabled", "disabled");
            // enable carian pihak
            $("#penyerahJenisPengenalan").attr("disabled", "");
            $("#penyerahJenisPengenalan").focus();
            $("#penyerahNoPengenalan").attr("disabled", "");
            $("#carianPihak").attr("disabled", "");
        } else{
            // disable button for kod penyerah
            $("#idPenyerah").attr("disabled", "");
            $("#idPenyerah").focus();
            $("#carianPenyerah").attr("disabled", "");
            // enable carian pihak
            $("#penyerahJenisPengenalan").attr("disabled", "disabled");
            $("#penyerahNoPengenalan").attr("disabled", "disabled");
            $("#carianPihak").attr("disabled", "disabled");
            // code to clear data
            $('#penyerahJenisPengenalan').val("0");
            $('#penyerahNoPengenalan').val("");
            $("#penyerahNama").val("");
            $("#penyerahAlamat1").val("");
            $("#penyerahAlamat2").val("");
            $("#penyerahAlamat3").val("");
            $("#penyerahAlamat4").val("");
            $("#penyerahPoskod").val("");
            $("#penyerahNegeri").val("0");
            $("#penyerahNoTelefon").val("");
            $("#penyerahEmail").val("");
        }
    }

    $(document).ready( function(){
        var bil =  ${fn:length(actionBean.pemohonList)};
        if (bil != 0) {
            document.getElementById("hiddenPenyerah").style.visibility = 'hidden';
            document.getElementById("hiddenPenyerah").style.display = 'none';
            document.getElementById("hideMaklumatPenyerah").style.visibility = 'hidden';
            document.getElementById("hideMaklumatPenyerah").style.display = 'none';

        }

        if(document.getElementById("maklumatPenyerah").checked == false){
            $("#penyerahKod").attr("disabled",false);
            $("#penyerahKod").val("");
            $("#idPenyerah").attr("disabled",false);
            $("#idPenyerah").val("");
            $("#penyerahJenisPengenalan").attr("disabled",false);
            $("#penyerahJenisPengenalan").val("");
            $("#penyerahNoPengenalan").attr("disabled",false);
            $("#penyerahNoPengenalan").val("");
            $("#penyerahNama").attr("disabled",false);
            $("#penyerahAlamat1").attr("disabled",false);
            $("#penyerahAlamat2").attr("disabled",false);
            $("#penyerahAlamat3").attr("disabled",false);
            $("#penyerahAlamat4").attr("disabled",false);
            $("#penyerahPoskod").attr("disabled",false);
            $("#penyerahNegeri").attr("disabled",false);
            $("#penyerahNoTelefon").attr("disabled",false);
            $("#penyerahEmail").attr("disabled",false);
            $("#hapus").attr("disabled",false);
            $("#new").attr("disabled",true);
            $("#save").attr("disabled",true);
            return true;
        }

        if(document.getElementById("maklumatPenyerah").checked == true){
            $("#penyerahKod").attr("disabled",true);
            $("#penyerahKod").val("");
            $("#idPenyerah").attr("disabled",true);
            $("#idPenyerah").val("");
            $("#penyerahJenisPengenalan").attr("disabled",true);
            $("#penyerahJenisPengenalan").val("");
            $("#penyerahNoPengenalan").attr("disabled",true);
            $("#penyerahNoPengenalan").val("");
            $("#penyerahNama").attr("disabled",true);
            $("#penyerahAlamat1").attr("disabled",true);
            $("#penyerahAlamat2").attr("disabled",true);
            $("#penyerahAlamat3").attr("disabled",true);
            $("#penyerahAlamat4").attr("disabled",true);
            $("#penyerahPoskod").attr("disabled",true);
            $("#penyerahNegeri").attr("disabled",true);
            $("#penyerahNoTelefon").attr("disabled",true);
            $("#penyerahEmail").attr("disabled",true);
            $("#new").attr("disabled",false);
            $("#save").attr("disabled",true);
            return true;
        }

        

        $('.empty').remove();

        var len2 = $(".alamat").length;

        for ( var i=0; i<len2; i++ ){
            var d = $('.x'+i).val();

            $('.nama'+i).bind('click',d, function(){
                window.open("${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?showEditPemohon&idPihak="+d, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=750");
            });
        }

    });

    function viewPihak(id, jenis){
        window.open("${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?viewPihakDetail&idPihak="+id+"&jenis="+jenis, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=670,scrollbars=yes");
    }

    function popupExisting(){
        window.open("${pageContext.request.contextPath}/common/pihak", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }

    function addNewPB(){
        window.open("${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?pihakKepentinganPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=700,scrollbars=yes");
    }

    function addNewPemohon(){
        window.open("${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?pemohonPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=700,scrollbars=yes");
    }

    function penyerah(){

        window.open("${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?penyerah", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=700,scrollbars=yes");
    }

    function addRowMohonPihak(nama, kp ,syer){
        //TODO: to be complete
        var rowNo = $('table#lineMP tr').length;
        $('table#lineMP > tbody').append("<tr id='x"+rowNo+"' class='x'><td class='rowNo'>"+rowNo
            +"</td><td>"+nama+"</td><td>"+kp+"</td><td>"+syer+"</td><td>"+
            "<img alt='Click to Delete' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem' id='rem"+
            rowNo+"' onclick=\"remove(this.id,'line')\"></td></tr>");
    }

    function remove(val){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/common/mohon_pihak?delete&idPermohonanPihak='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }

    function removePemohon(val){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?deletePemohon&idPemohon='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }

    function removeChanges(val){
        var answer = confirm("adakah anda pasti untuk Hapus?");
        if (answer){
            var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?deleteChanges&idKkini='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            });
        }
    }

    function removeMultipleMohonPihak(value) {
        var param = '';
        if(confirm('Adakah anda pasti?')) {

            if(value == 'penerima'){
                $('.remove2').each(function(index){
                    var a = $('#rm_mp'+index).is(":checked");
                    if(a) {
                        param = param + '&idPermohonanPihak=' + $('#rm_mp'+index).val();
                    }
                });
            }

            else if(value == 'gadaian'){
                $('.remove3').each(function(index){
                    var a = $('#rm_mp2'+index).is(":checked");
                    if(a) {
                        param = param + '&idPermohonanPihak=' + $('#rm_mp2'+index).val();
                    }
                });
            }


            if(param == ''){
                alert('Sila Pilih Penerima terlebih dahulu.');
                return;
            }


            var url = '${pageContext.request.contextPath}/common/mohon_pihak?deleteMultiplePihakConsent'+param;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            }, 'html');
        }
    }

    function removeMultiplePemohon() {
        $("#penyerahJenisPengenalan").attr("disabled",true);
        $("#penyerahJenisPengenalan").val("");
        $("#penyerahNoPengenalan").attr("disabled",true);
        $("#penyerahNoPengenalan").val("");
        $("#penyerahNama").attr("disabled",true);
        $("#penyerahAlamat1").attr("disabled",true);
        $("#penyerahAlamat2").attr("disabled",true);
        $("#penyerahAlamat3").attr("disabled",true);
        $("#penyerahAlamat4").attr("disabled",true);
        $("#penyerahPoskod").attr("disabled",true);
        $("#penyerahNegeri").attr("disabled",true);
        $("#penyerahNoTelefon").attr("disabled",true);
        $("#penyerahEmail").attr("disabled",true);
        $("#save").attr("disabled",true);

        var param = '';
        if(confirm('Adakah anda pasti?')) {
            $('.remove').each(function(index){
                var a = $('#rm_'+index).is(":checked");
                if(a) {
                    param = param + '&idPihak=' + $('#rm_'+index).val();
                }
            });
            if(param == ''){
                alert('Sila Pilih Pemohon terlebih dahulu.');
                return;
            }
            var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?deleteSelectedPemohon'+param;
            $.ajax({
                type:"GET",
                url : url,
                dataType : 'html',
                error : function (xhr, ajaxOptions, thrownError){
                    alert("error=" + xhr.responseText);
                },
                success : function(data){
                    $('#page_div').html(data);
                }
            });

        }
    }

    <%--  function addPemohon(){
          var len = $('.nama').length;
          var param = '';

        for(var i=1; i<=len; i++){
            var ckd = $('#chkbox'+i).is(":checked");
            if(ckd){
                param = param + '&idPihak=' + $('#chkbox'+i).val();
            }
        }
        if(param == ''){
            alert('Tiada pemohon.');
            return;
        }
        var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?simpanPemohon'+param;

        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
            },
            success : function(data){
                $('#page_div').html(data);
            }
        });
    }
    --%>
        function semakSyer(f,value){
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?semakSyer&jenis='+value,q,
            function(data){
                if(data != ''){
                    alert(data);
                }
            }, 'html');
        }

        function samaRata(f, value){
            var q = $(f).formSerialize();
            $.get('${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?agihSamaRata&jenis='+value,q,
            function(data){
                if( data == null || data.length == 0) {
                    alert('Terdapat Masalah');
                    return;
                }
                var p = data.split(DELIM);

                if(value == "penerima"){
                    $('.pembilang').each(function(){
                        $(this).val(p[0]);
                    });
                    $('.penyebut').each(function(){
                        $(this).val(p[1]);
                    });
                }

                else if(value == "gadaian"){
                    $('.pembilang2').each(function(){
                        $(this).val(p[0]);
                    });
                    $('.penyebut2').each(function(){
                        $(this).val(p[1]);
                    });
                }

            });
        }

        function dopopup(i,kod){
            if(kod == "TN" || kod == "PNKP"){
                var url ="showEditNamaPemohon";
            }
            else if(kod == "TA"){
                var url = "showEditAlamatPemohon";
            }else
            {
                var url = "showEditPemohon";
            }
            var d = $('.x'+i).val();
            window.open("${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?"+url+"&idPihak="+d, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=890,height=700,scrollbars=yes");
        }

        function editPenerima(i,value){

            var d;
            if(value == 'penerima'){
                d = $('.a'+i).val();
            }
            else if(value == 'gadaian'){
                d = $('.a2'+i).val();
            }
            window.open("${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?showEditPenerima&idPihak="+d, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=890,height=700,scrollbars=yes");
        }

        //fikri : automatic insert into table for syer
        function updateSyer(idpihak, id) {
            var s1 = $('#syer1' + id).val();
            var s2 = $('#syer2' + id).val();

            if(s1 == '' || s2 == ''){
                return;
            }

            if(isNaN(s1) && isNaN(s2)){
                return;
            }
            var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?updateSyerMohonPihak&idpihak='+idpihak
                + '&syer1=' + s1 + '&syer2=' + s2;
            $.post( url,
            function(data){
            }, 'html');

        }

        function refreshPagePemohon(){
            var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?refreshpage';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

        function hidePenyerah(){
            document.getElementById("hiddenPenyerah").style.visibility = 'hidden';
            document.getElementById("hiddenPenyerah").style.display = 'none';
            $("#penyerahKod").attr("disabled",true);
            $("#penyerahKod").val("");
            $("#idPenyerah").attr("disabled",true);
            $("#idPenyerah").val("");
            $("#penyerahJenisPengenalan").attr("disabled",true);
    <%--$("#penyerahJenisPengenalan").val("");--%>
            $("#penyerahNoPengenalan").attr("disabled",true);
    <%--$("#penyerahNoPengenalan").val("");--%>
            $("#penyerahNama").attr("disabled",true);
            $("#penyerahAlamat1").attr("disabled",true);
            $("#penyerahAlamat2").attr("disabled",true);
            $("#penyerahAlamat3").attr("disabled",true);
            $("#penyerahAlamat4").attr("disabled",true);
            $("#penyerahPoskod").attr("disabled",true);
            $("#penyerahNegeri").attr("disabled",true);
            $("#penyerahNoTelefon").attr("disabled",true);
            $("#penyerahEmail").attr("disabled",true);
            $("#new").attr("disabled",false);
            $("#save").attr("disabled",true);
        }

        function showPenyerah(){
            document.getElementById("hiddenPenyerah").style.visibility = 'visible';
            document.getElementById("hiddenPenyerah").style.display = '';
            $("#penyerahKod").attr("disabled",false);
            $("#idPenyerah").attr("disabled",false);
            $("#penyerahJenisPengenalan").attr("disabled",false);
            $("#penyerahNoPengenalan").attr("disabled",false);
            $("#penyerahNama").attr("disabled",false);
            $("#penyerahAlamat1").attr("disabled",false);
            $("#penyerahAlamat2").attr("disabled",false);
            $("#penyerahAlamat3").attr("disabled",false);
            $("#penyerahAlamat4").attr("disabled",false);
            $("#penyerahPoskod").attr("disabled",false);
            $("#penyerahNegeri").attr("disabled",false);
            $("#penyerahNoTelefon").attr("disabled",false);
            $("#penyerahEmail").attr("disabled",false);
            $("#new").attr("disabled",true);
            $("#save").attr("disabled",false);
        }

        function validation() {
            if(document.getElementById("maklumatPenyerah").checked == false){
                alert('Sila Pilih Penyerah adalah Pemohon atau tidak.');
                return false;
            }
            if(document.getElementById("maklumatPenyerah").checked == true){
                
                $("#save").attr("disabled",true);
                $("#new").attr("disabled",false);
                return true;
            }
    <%--if(document.getElementById("penyerahJenisPengenalan").value == ""){
            alert('Sila Pilih Jenis Pengenalan');
            $('#penyerahJenisPengenalan').focus();
            return false;
        }
        if(document.getElementById("penyerahNoPengenalan").value == ""){
            alert('Sila Masukkan No. Pengenalan');
            $('#penyerahNoPengenalan').focus();
            return false;
        }
        return true;--%>
            }



</script>

<s:form beanclass="etanah.view.stripes.pengambilan.aduan.sedia_surat.pemohonAduanActionBean">
    <div class="subtitle displaytag">
        <s:messages/>
        <s:errors/>
        <s:hidden name="kod" id="kod"/>
        <c:if test="${fn:length(actionBean.pemohonList) eq 0}">
            <fieldset class="aras1">
                <legend>
                </legend>
                <div id="hideMaklumatPenyerah">
                    <label>&nbsp; &nbsp; &nbsp; Adakah Penyerah  adalah Pemohon: </label>
                    <p>
                        <s:radio name="maklumatPenyerah" id="maklumatPenyerah" checked="checked" value="Y" onclick="showPenyerah();"/> Ya &nbsp;
                        <s:radio name="maklumatPenyerah" id="maklumatPenyerah" value="T" onclick="hidePenyerah();"/> Tidak
                    </p>
                </div>

            </fieldset>
        </div>
        <br/>
        <fieldset class="aras1">
            <div id="hiddenPenyerah">
                <legend>Maklumat Penyerah Aduan</legend>

                <%--<p>
                    <label>Carian Penyerah: </label>
                    <c:if test="${empty SSHMA}">
                        <s:select name="kodPenyerah.kod" id="penyerahKod" onchange="javascript:updateKodPenyerahInputs();">
                            <s:option value="0">Individu/Syarikat</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodPenyerah}" label="nama" value="kod"/>
                        </s:select>
                    </c:if>
                    <c:if test="${!empty SSHMA}">
                        <s:select name="penyerahKod.kod" id="penyerahKod" onchange="javascript:updateKodPenyerahInputs();">
                            <s:option value="06">Jabatan Kerajaan</s:option>
                            <s:option value="07">Badan Berkanun</s:option>
                            <s:option value="00">Unit Dalaman</s:option>
                        </s:select>
                    </c:if>
                    <s:text name="idPenyerah" size="5" id="idPenyerah" maxlength="5" />
                    <input type="button" id="carianPenyerah"
                           value="Cari" class="btn" onclick="javascript:populatePenyerah(this);" />
                    (Biarkan kosong dan klik "Cari" untuk membuat rujukan)
                </p>--%>


                <c:if test="${!empty SSHMA}">
                    <p>
                        <label><em>*</em>Nama Pemohon / Jabatan / Unit</label><s:text name="penyerahEmail" id="penyerahEmail" size="42" onblur="doUpperCase(this.id)"/>
                    </p>
                </c:if>
                <p>
                    <label>Nama</label><s:text name="penyerahNama" id="penyerahNama" size="42" onblur="doUpperCase(this.id)"/>
                </p>
                <p>
                    <label><em>*</em>No. Pengenalan :</label>
                    <%--<s:hidden name="penyerahJenisPengenalan1" id="penyerahJenisPengenalan" value="${actionBean.penyerahJenisPengenalan1}"/>
                    <s:select name="pJenisPengenalan" id="penyerahJenisPengenalan" onchange="clearNoPengenalan();" value="${actionBean.permohonan.penyerahJenisPengenalan.nama}">
                    <s:option value="0">Pilih Jenis...</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                    </s:select>--%>

                    <c:choose>
                        <c:when test="${actionBean.penyerahKod eq '09' or actionBean.penyerahKod eq '10' or actionBean.penyerahKod eq '02' or actionBean.penyerahKod eq '04' or actionBean.penyerahKod eq '03' or actionBean.penyerahKod eq '01' or actionBean.penyerahKod eq '07' or actionBean.penyerahKod eq '00' or actionBean.penyerahKod eq '06' or actionBean.penyerahKod eq null}">
                            <s:hidden name="kod1" value="Z">Badan Kerajaan  </s:hidden>
                            <s:select name="kod" value="${actionBean.permohonan.penyerahJenisPengenalan.kod}" disabled="true">
                                <s:option value="">Pilih Jenis...</s:option>
                                <s:option value="B">No K/P Baru  </s:option>
                                <s:option value="S">No Syarikat  </s:option>
                                <s:option value="L">No K/P Lama  </s:option>
                                <s:option value="P">No Pasport  </s:option>
                                <s:option value="T">No Tentera  </s:option>
                                <s:option value="I">No Polis  </s:option>
                                <s:option value="0">Tidak Berkenaan  </s:option>
                                <s:option value="N">No Bank  </s:option>
                                <s:option value="F">No Paksa  </s:option>
                                <s:option value="U">No Pertubuhan  </s:option>
                                <s:option value="D">No Pendaftaran  </s:option>
                                <s:option value="Z">Badan Kerajaan  </s:option>
                                <s:option value="X">Tiada  </s:option>
                            </s:select>
                        </c:when>
                        <c:otherwise>
                            <s:select name="kod" value="${actionBean.permohonan.penyerahJenisPengenalan.kod}">
                                <s:option value="">Pilih Jenis...</s:option>
                                <s:option value="B">No K/P Baru  </s:option>
                                <s:option value="S">No Syarikat  </s:option>
                                <s:option value="L">No K/P Lama  </s:option>
                                <s:option value="P">No Pasport  </s:option>
                                <s:option value="T">No Tentera  </s:option>
                                <s:option value="I">No Polis  </s:option>
                                <s:option value="0">Tidak Berkenaan  </s:option>
                                <s:option value="N">No Bank  </s:option>
                                <s:option value="F">No Paksa  </s:option>
                                <s:option value="U">No Pertubuhan  </s:option>
                                <s:option value="D">No Pendaftaran  </s:option>
                                <s:option value="Z">Badan Kerajaan  </s:option>
                                <s:option value="X">Tiada  </s:option>
                            </s:select>
                        </c:otherwise>
                    </c:choose>

                    <%--<s:select name="kod" value="${actionBean.permohonan.penyerahJenisPengenalan.kod}">
                        <s:option value="">Pilih Jenis...</s:option>
                        <s:option value="B">No K/P Baru  </s:option>
                        <s:option value="S">No Syarikat  </s:option>
                        <s:option value="L">No K/P Lama  </s:option>
                        <s:option value="P">No Pasport  </s:option>
                        <s:option value="T">No Tentera  </s:option>
                        <s:option value="I">No Polis  </s:option>
                        <s:option value="0">Tidak Berkenaan  </s:option>
                        <s:option value="N">No Bank  </s:option>
                        <s:option value="F">No Paksa  </s:option>
                        <s:option value="U">No Pertubuhan  </s:option>
                        <s:option value="D">No Pendaftaran  </s:option>
                        <s:option value="Z">Badan Kerajaan  </s:option>
                        <s:option value="X">Tiada  </s:option>
                    </s:select>--%>

                    <s:hidden name="penyerahNoPengenalan" id="penyerahNoPengenalan" value="${actionBean.penyerahNoPengenalan}"/>
                    <s:text name="pNoPengenalan" id="penyerahNoPengenalan" size="10" value="${actionBean.permohonan.penyerahNoPengenalan}"/>
                    <em>[cth: 780901057893]</em>
                    <%--<input type=button name="searchPenyerah" value="Cari" class="btn" id="carianPihak"
                           onclick="javascript:populatePenyerah(this);" />--%>
                </p>
                <p>
                    <label><em>*</em>Alamat</label>
                    <s:text name="penyerahAlamat1" id="penyerahAlamat1" size="30" onblur="doUpperCase(this.id)"/>
                </p>

                <p>
                    <label>&nbsp;</label>
                    <s:text name="penyerahAlamat2" id="penyerahAlamat2" size="30" onblur="doUpperCase(this.id)"/>
                </p>

                <p>
                    <label>&nbsp;</label>
                    <s:text name="penyerahAlamat3" id="penyerahAlamat3" size="30" onblur="doUpperCase(this.id)"/>
                </p>

                <p>
                    <label>Bandar</label>
                    <s:text name="penyerahAlamat4" id="penyerahAlamat4" size="30" onblur="doUpperCase(this.id)"/>
                </p>

                <p>
                    <label>Poskod</label>
                    <s:text name="penyerahPoskod" maxlength="5" id="penyerahPoskod" size="17" onkeyup="validateNumber(this,this.value);" />
                    <em>5 Digit [cth : 12000]</em>
                </p>

                <p>
                    <label><em>*</em>Negeri</label>
                    <%--penyerahNegeri.kod--%>
                    <s:select name="penyerahNegeri" id="penyerahNegeri" >
                        <s:option value="0">Pilih ...</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                    </s:select>
                    <%--<s:submit  name="updatePenyerah" value="Kemaskini Penyerah" class="longbtn"/>--%>
                </p>
                <p>
                    <label>No.Telefon</label>
                    <s:text name="penyerahNoTelefon" id="penyerahNoTelefon" size="15"/>
                </p>
                <p>
                    <label>Email</label>
                    <s:text name="penyerahEmail" id="penyerahEmail" size="50"/>
                </p>
                <br/>
                <p align="center">
                    <%--<s:button name="simpan" id="save" value="Simpan" class="btn" onclick="validation(this.name, this.form);"/>--%>
                    <%--<s:button name="simpan" id="save" value="Simpan" class="btn" onclick="javascript:validation();"/>--%>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                    <%--<s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>--%>
                </p>
            </div>

        </fieldset>
    </c:if>
    <br>
    <fieldset class="aras1">
        <legend>
            Senarai Pemohon 
        </legend>
        <c:if test="${edit}">
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.pemohonList}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

                    <%-- <c:if test="${edit}">--%>
                    <display:column>
                        <s:checkbox name="rm" id="rm_${line_rowNum-1}" value="${line.idPemohon}" class="remove"/>
                    </display:column>
                    <%--</c:if>--%>

                    <display:column title="Bil" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                    </display:column>
                    <display:column  title="Nama" >
                        <%--<a href="#" onclick="viewPihak('${line.pihak.idPihak}', 'pemohon');return false;">--%>${line.pihak.nama}
                    </display:column>
                    <display:column property="pihak.noPengenalan" title="No. Pengenalan/No.Syarikat" />${line.pihak.noPengenalan}
                    <display:column title="Alamat 1">${line.pihak.alamat1}
                        <c:if test="${line.pihak.alamat2 ne null}">,</c:if>
                        ${line.pihak.alamat2}
                        <c:if test="${line.pihak.alamat3 ne null}"> </c:if>
                        ${line.pihak.alamat3}
                        <c:if test="${line.pihak.alamat4 ne null}">,</c:if>
                        ${line.pihak.alamat4}
                        <c:if test="${line.pihak.poskod ne null}">,</c:if>
                        ${line.pihak.poskod}
                        <c:if test="${line.pihak.negeri.kod ne null}">,
                            <c:if test="${line.pihak.negeri.kod eq '01'}">JOHOR</c:if>
                            <c:if test="${line.pihak.negeri.kod eq '02'}">KEDAH</c:if>
                            <c:if test="${line.pihak.negeri.kod eq '03'}">KELANTAN</c:if>
                            <c:if test="${line.pihak.negeri.kod eq '04'}">MELAKA</c:if>
                            <c:if test="${line.pihak.negeri.kod eq '05'}">NEGERI SEMBILAN</c:if>
                            <c:if test="${line.pihak.negeri.kod eq '06'}">PAHANG</c:if>
                            <c:if test="${line.pihak.negeri.kod eq '07'}">PULAU PINANG</c:if>
                            <c:if test="${line.pihak.negeri.kod eq '08'}">PERAK</c:if>
                            <c:if test="${line.pihak.negeri.kod eq '09'}">PERLIS</c:if>
                            <c:if test="${line.pihak.negeri.kod eq '10'}">SELANGOR</c:if>
                            <c:if test="${line.pihak.negeri.kod eq '11'}">TERENGGANU</c:if>
                            <c:if test="${line.pihak.negeri.kod eq '12'}">SABAH</c:if>
                            <c:if test="${line.pihak.negeri.kod eq '13'}">SARAWAK</c:if>
                            <c:if test="${line.pihak.negeri.kod eq '14'}">WP KUALA LUMPUR</c:if>
                            <c:if test="${line.pihak.negeri.kod eq '15'}">WP LABUAN</c:if>
                            <c:if test="${line.pihak.negeri.kod eq '16'}">WP PUTRAJAYA</c:if>
                        </c:if>
                        <%--<font style="text-transform:uppercase;">${line.pihak.negeri.nama}</font>--%>
                    </display:column>
                    <display:column property="pihak.noTelefon1" title="No.Telefon" />
                    <display:column property="pihak.noTelefon2" title="No.Fax" />
                    <display:column property="pihak.email" title="Email" />
                    <%--<c:if test="${edit}">--%>
                    <display:column title="Kemaskini">
                        <p align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                 onclick="dopopup('${line_rowNum -1}','${actionBean.p.kodUrusan.kod}');return false;" onmouseover="this.style.cursor='pointer';">
                        </p>
                    </display:column>
                    <%--</c:if>--%>
                </display:table>
            </div>
            <p align="center">
                <c:if test="${edit}">
                    <c:if test="${fn:length(actionBean.pemohonList) <= 0 }" >
                        <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addNewPemohon();"/>&nbsp;
                        <label>&nbsp;</label>
                    </c:if>
                </c:if>
                <%--<c:if test="${edit && fn:length(actionBean.pemohonList) > 0}" >--%>
                <s:button class="btn" value="Hapus" name="new" id="hapus" onclick="removeMultiplePemohon();"/>&nbsp;
                <s:button class="longbtn" value="Kemaskini Penyerah" name="new" id="new" onclick="penyerah();"/>&nbsp;
                <%--</c:if>--%>
            </p>

            <br/>
        </c:if>
        <c:if test="${!edit}">
            <%-- <p align="center">
                 <font size="2" color="red"><b>
                         SILA TAMBAH BARU JIKA PEMOHON BUKAN PENYERAH
                     </b>
                 </font>
             </p>--%>

            <%--second layer start--%>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.pemohonList}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

                    <%-- <c:if test="${edit}">--%>
                    <%--   <display:column>
                           <s:checkbox name="rm" id="rm_${line_rowNum-1}" value="${line.idPemohon}" class="remove"/>
                       </display:column>--%>
                    <%--</c:if>--%>

                    <display:column title="Bil" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                    </display:column>
                    <display:column  title="Nama" >
                        <%--<a href="#" onclick="viewPihak('${line.pihak.idPihak}', 'pemohon');return false;">${line.pihak.nama}</a>--%>
                        ${line.pihak.nama}
                    </display:column>
                    <display:column property="pihak.noPengenalan" title="No. Pengenalan/No.Syarikat" />${line.pihak.noPengenalan}
                    <display:column title="Alamat 2">${line.pihak.alamat1}
                        <c:if test="${line.pihak.alamat2 ne null}">,</c:if>
                        ${line.pihak.alamat2}
                        <c:if test="${line.pihak.alamat3 ne null}"> </c:if>
                        ${line.pihak.alamat3}
                        <c:if test="${line.pihak.alamat4 ne null}">,</c:if>
                        ${line.pihak.alamat4}
                        <c:if test="${line.pihak.poskod ne null}">,</c:if>
                        ${line.pihak.poskod}
                        <c:if test="${line.pihak.negeri.kod ne null}">,</c:if>
                        <font style="text-transform:uppercase;">${line.pihak.negeri.nama}</font>
                    </display:column>
                    <display:column property="pihak.noTelefon1" title="No.Telefon" />
                    <display:column property="pihak.noTelefon2" title="No.Fax" />
                    <display:column property="pihak.email" title="Email" />
                    <%--<c:if test="${edit}">--%>
                    <%--<display:column title="Kemaskini">
                       <p align="center">
                           <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                onclick="dopopup('${line_rowNum -1}','${actionBean.p.kodUrusan.kod}');return false;" onmouseover="this.style.cursor='pointer';">
                       </p>
                   </display:column>--%>
                    <%--</c:if>--%>
                </display:table>
            </div>
            <p align="center">
                <%--<c:if test="${edit}">--%>
                <c:if test="${fn:length(actionBean.pemohonList) < 0}" >
                    <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addNewPemohon();"/>&nbsp;
                    <label>&nbsp;</label>

                </c:if>
                <%--<c:if test="${edit && fn:length(actionBean.pemohonList) > 0}" >
                    <s:button class="btn" value="Hapus" name="new" id="new" onclick="removeMultiplePemohon();"/>&nbsp;
                </c:if>--%>
            </p>
            <%--</fieldset>--%>
            <br/>
        </c:if>
    </fieldset>
</s:form>



