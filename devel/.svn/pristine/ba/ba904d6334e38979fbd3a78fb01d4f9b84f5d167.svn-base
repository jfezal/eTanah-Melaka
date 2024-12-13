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
        if($("#maklumatPenyerah").val() == "Y"){
            showPenyerah();
        }

        $('.empty').remove();

        var len2 = $(".alamat").length;

        for ( var i=0; i<len2; i++ ){
            var d = $('.x'+i).val();

            $('.nama'+i).bind('click',d, function(){
                window.open("${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon1?showEditPemohon&idPihak="+d, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=750");
            });
        }

    });

    function viewPihak(id, jenis){
        window.open("${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon1?viewPihakDetail&idPihak="+id+"&jenis="+jenis, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=670,scrollbars=yes");
    }

    function popupExisting(){
        window.open("${pageContext.request.contextPath}/common/pihak", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }

    function addNewPB(){
        window.open("${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon1?pihakKepentinganPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=700,scrollbars=yes");
    }

    function addNewPemohon(){
        window.open("${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon1?pemohonPopup", "eTanah",
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
            var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon1?deletePemohon&idPemohon='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }

    function removeChanges(val){
        var answer = confirm("adakah anda pasti untuk Hapus?");
        if (answer){
            var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon1?deleteChanges&idKkini='+val;
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
            var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon1?deleteSelectedPemohon'+param;
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
            $.post('${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon1?semakSyer&jenis='+value,q,
            function(data){
                if(data != ''){
                    alert(data);
                }
            }, 'html');
        }

        function samaRata(f, value){
            var q = $(f).formSerialize();
            $.get('${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon1?agihSamaRata&jenis='+value,q,
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
            window.open("${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon1?"+url+"&idPihak="+d, "eTanah",
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
            window.open("${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon1?showEditPenerima&idPihak="+d, "eTanah",
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
            var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon1?updateSyerMohonPihak&idpihak='+idpihak
                + '&syer1=' + s1 + '&syer2=' + s2;
            $.post( url,
            function(data){
            }, 'html');

        }

        function refreshPagePemohon(){
            var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon1?refreshpage';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }


</script>

<div class="subtitle displaytag">
    <s:messages/>
    <s:errors/>
    <s:form beanclass="etanah.view.stripes.pengambilan.pActionBean_1">
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
                    <display:column property="pihak.noPengenalan" title="No. Pengenalan/No.Syarikat" />
                    <display:column title="Alamat">${line.pihak.suratAlamat1}
                        <c:if test="${line.pihak.suratAlamat2 ne null}">,</c:if>
                        ${line.pihak.suratAlamat2}
                        <c:if test="${line.pihak.suratAlamat3 ne null}"> </c:if>
                        ${line.pihak.suratAlamat3}
                        <c:if test="${line.pihak.suratAlamat4 ne null}">,</c:if>
                        ${line.pihak.suratAlamat4}
                        <c:if test="${line.pihak.suratPoskod ne null}">,</c:if>
                        ${line.pihak.suratPoskod}
                        <c:if test="${line.pihak.suratNegeri.kod ne null}">,</c:if>
                        <font style="text-transform:uppercase;">${line.pihak.suratNegeri.nama}</font>
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
                    <s:button class="btn" value="Hapus" name="new" id="new" onclick="removeMultiplePemohon();"/>&nbsp;
                <%--</c:if>--%>
            </p>
        </fieldset>
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
                    <display:column property="pihak.noPengenalan" title="No. Pengenalan/No.Syarikat" />
                    <display:column title="Alamat">${line.pihak.suratAlamat1}
                        <c:if test="${line.pihak.suratAlamat2 ne null}">,</c:if>
                        ${line.pihak.suratAlamat2}
                        <c:if test="${line.pihak.suratAlamat3 ne null}"> </c:if>
                        ${line.pihak.suratAlamat3}
                        <c:if test="${line.pihak.suratAlamat4 ne null}">,</c:if>
                        ${line.pihak.suratAlamat4}
                        <c:if test="${line.pihak.suratPoskod ne null}">,</c:if>
                        ${line.pihak.suratPoskod}
                        <c:if test="${line.pihak.suratNegeri.kod ne null}">,</c:if>
                        <font style="text-transform:uppercase;">${line.pihak.suratNegeri.nama}</font>
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

    </s:form>
</div>



