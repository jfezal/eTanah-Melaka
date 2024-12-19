<%-- 
    Document   : pihak_berkepentingan
    Created on : Jul 19, 2010, 11:49:35 AM
    Author     : muhammad.amin
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/fraction.js" language="Javascript"></script>
<script type="text/javascript">

    var DELIM = "__^$__";

    $(document).ready( function(){
        $('.empty').remove();

        var len2 = $(".alamat").length;

        for ( var i=0; i<len2; i++ ){
            var d = $('.x'+i).val();

            $('.nama'+i).bind('click',d, function(){
                window.open("${pageContext.request.contextPath}/consent/pihak_consent?showEditPemohon&idPihak="+d, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=750");
            });
        }
    });

    function viewPihak(id, jenis){
        window.open("${pageContext.request.contextPath}/consent/pihak_consent?viewPihakDetail&idPihak="+id+"&jenis="+jenis, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=670,scrollbars=yes");
    }

    function popupExisting(){
        window.open("${pageContext.request.contextPath}/common/pihak", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }

    function addNewPB(){
        window.open("${pageContext.request.contextPath}/consent/pihak_consent?pihakKepentinganPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=700,scrollbars=yes");
    }

    function addNewPemohon(){
        window.open("${pageContext.request.contextPath}/consent/pihak_consent?pemohonPopup", "eTanah",
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
            var url = '${pageContext.request.contextPath}/consent/pihak_consent?deletePemohon&idPemohon='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }

    function removeChanges(val){
        var answer = confirm("adakah anda pasti untuk Hapus?");
        if (answer){
            var url = '${pageContext.request.contextPath}/consent/pihak_consent?deleteChanges&idKkini='+val;
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
            var url = '${pageContext.request.contextPath}/consent/pihak_consent?deleteSelectedPemohon'+param;
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

    function removeMultipleTerlibat() {
        var param = '';
        if(confirm('Adakah anda pasti?')) {

            $('.removeTerlibat').each(function(index){
                var a = $('#rm_terlibat'+index).is(":checked");
                if(a) {
                    param = param + '&idPermohonanPihak=' + $('#rm_terlibat'+index).val();
                }
            });

            if(param == ''){
                alert('Sila Pilih Tuan Tanah Terlibat terlebih dahulu.');
                return;
            }

            var url = '${pageContext.request.contextPath}/common/mohon_pihak?deleteMultiplePihakConsent'+param;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            }, 'html');
        }
    }
       
    function addPemohon(){
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
        var url = '${pageContext.request.contextPath}/consent/pihak_consent?simpanPemohon'+param;

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
       
    function addTerlibat(){
        var len = $('.nama').length;
        var param = '';

        for(var i=1; i<=len; i++){
            var ckd = $('#chkbox'+i).is(":checked");
            if(ckd){
                param = param + '&idPihak=' + $('#chkbox'+i).val();
            }
        }
        if(param == ''){
            alert('Tiada Pihak Terlibat.');
            return;
        }
        var url = '${pageContext.request.contextPath}/consent/pihak_consent?simpanPihakTerlibat'+param;

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

    function semakSyer(f,value){
        notComplete = false;

        if(value == "penerima"){
            var rowNo = $('table#lineMP tr').length;

            for (var i = 0; i < rowNo-1; i++){
                var s1 = $('#syer1' + i).val();
                var s2 = $('#syer2' + i).val();
                if(s1 == 0 || s1 == '' || s2 == 0 || s2 == ''){
                    notComplete = true;
                    break;
                }
            }
        }
        else  if(value == "gadaian"){
            var rowNo = $('table#lineMP2 tr').length;

            for (var i = 0; i < rowNo-1; i++){
                var s1 = $('#syer1B' + i).val();
                var s2 = $('#syer2B' + i).val();
                if(s1 == 0 || s1 == '' || s2 == 0 || s2 == ''){
                    notComplete = true;
                    break;
                }
            }
        }

        if(notComplete){
            alert("Sila Masukkan Maklumat Syer Dengan Betul");
        }

        else{
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/consent/pihak_consent?semakSyer&jenis='+value,q,
            function(data){
                if(data != ''){
                    alert(data);
                }
            }, 'html');
        }
    }

    function samaRata(f, value){
        var q = $(f).formSerialize();
        $.get('${pageContext.request.contextPath}/consent/pihak_consent?agihSamaRata&jenis='+value,q,
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
        }else{
            var url = "showEditPemohon";
        }
        var d = $('.x'+i).val();
        window.open("${pageContext.request.contextPath}/consent/pihak_consent?"+url+"&idPihak="+d, "eTanah",
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
        window.open("${pageContext.request.contextPath}/consent/pihak_consent?showEditPenerima&idPihak="+d, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=700,scrollbars=yes");
    }

    function editSimati(i, value){

        if(value == 'tuanTanah'){
            id = $('.yy'+i).val();
        }
        else if(value == 'terlibat'){
            id = $('.xx'+i).val();
        }

        window.open("${pageContext.request.contextPath}/consent/pihak_consent?showEditSimati&idPihak="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=700,scrollbars=yes");
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
        var url = '${pageContext.request.contextPath}/consent/pihak_consent?updateSyerMohonPihak&idpihak='+idpihak
            + '&syer1=' + s1 + '&syer2=' + s2;
        $.post( url,
        function(data){
        }, 'html');
    }
    
</script>
<div class="subtitle displaytag">
    <s:messages/>
    <s:errors/>
    <s:form beanclass="etanah.view.stripes.consent.PihakConsentActionBean">
        <fieldset class="aras1">
            <legend>Senarai Tuan Tanah</legend>
            <c:if test="${edit}">
                <c:if test="${fn:length(actionBean.pihakKepentinganList) == 1}">
                    <p align="center">
                        <font size="2" color="red">
                            <c:if test="${actionBean.p.kodUrusan.kod ne 'TMADT'}">
                                <b>Sila Pilih Butang 'Pilih Pemohon' Jika Tuan Tanah Adalah Pemohon</b>
                            </c:if>
                            <c:if test="${actionBean.p.kodUrusan.kod eq 'TMADT'}">
                                <b>Sila masukkan maklumat kematian pada bahagian kemaskini.</b>
                            </c:if>
                        </font>
                    </p>
                </c:if>
                <c:if test="${fn:length(actionBean.pihakKepentinganList) > 1}">
                    <p align="center">
                        <font size="2" color="red">
                            <c:if test="${actionBean.p.kodUrusan.kod ne 'TMADT'}">
                                <b>Sila Pilih Butang 'Pilih Pemohon' Jika Tuan Tanah Adalah Pemohon</b><br/>
                                <b>atau</b><br/>
                            </c:if>
                            <b>Sila Pilih Butang 'Pilih Pihak Terlibat' Jika Tuan Tanah Bukan Pemohon</b>
                        </font>
                    </p>
                </c:if>

            </c:if>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.pihakKepentinganList}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                    <c:if test="${edit}">
                        <c:if test="${fn:length(actionBean.pihakKepentinganList) > 0 && actionBean.p.kodUrusan.kod ne 'TMADT'}">
                            <display:column>
                                <s:checkbox name="checkbox" id="chkbox${line_rowNum}" value="${line.pihak.idPihak}"/>
                            </display:column>
                        </c:if>
                        <c:if test="${fn:length(actionBean.pihakKepentinganList) > 1 && actionBean.p.kodUrusan.kod eq 'TMADT'}">
                            <display:column>
                                <s:checkbox name="checkbox" id="chkbox${line_rowNum}" value="${line.pihak.idPihak}"/>
                            </display:column>
                        </c:if>
                    </c:if>
                    <display:column title="Bil" sortable="true">${line_rowNum}
                        <s:hidden name="yy" class="yy${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                    </display:column>
                    <display:column  title="Nama" class="nama">
                        <a href="#" onclick="viewPihak('${line.pihak.idPihak}','tuanTanah');return false;"><font style="text-transform:uppercase;">${line.pihak.nama}</font></a>
                    </display:column>
                    <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                    <display:column title="Bahagian yang dimiliki" >${line.syerPembilang}/${line.syerPenyebut}</display:column>
                    <display:column title="Tarikh Pemilikan Tanah">
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/>
                    </display:column>
                    <display:column property="jenis.nama" title="Jenis Pihak" style="text-transform:uppercase;"/>
                    <c:if test="${actionBean.p.kodUrusan.idWorkflow eq 'http://xmlns.oracle.com/Consent_Negeri/Consent/ADAT' || actionBean.p.kodUrusan.kod eq 'BTADT'}">
                        <c:if test="${edit}">
                            <display:column title="Kemaskini">
                                <c:if test="${line.pihak.jenisPengenalan.kod eq 'B' || line.pihak.jenisPengenalan.kod eq 'L' || line.pihak.jenisPengenalan.kod eq 'P' || line.pihak.jenisPengenalan.kod eq 'T' || line.pihak.jenisPengenalan.kod eq 'I' || line.pihak.jenisPengenalan.kod eq 'F'}">
                                    <p align="center">
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="editSimati('${line_rowNum -1}','tuanTanah');return false;" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </c:if>
                            </display:column>
                        </c:if>
                    </c:if>

                </display:table>
            </div>
            <c:if test="${fn:length(actionBean.pihakKepentinganList) > 0}">
                <c:if test="${edit}">
                    <p align="center">
                        <c:if test="${actionBean.p.kodUrusan.kod ne 'TMADT'}">
                            <s:button class="longbtn" value="Pilih Pemohon" name="pilih" id="pilih" onclick="addPemohon();"/>&nbsp;
                        </c:if>
                        <c:if test="${fn:length(actionBean.pihakKepentinganList) > 1}">
                            <s:button class="longbtn" value="Pilih Pihak Terlibat" name="pilih" id="pilih" onclick="addTerlibat();"/>&nbsp;
                        </c:if>
                    </p>
                </c:if>
            </c:if>
        </fieldset>
        <br/>
        <c:if test="${edit}">
            <c:if test="${fn:length(actionBean.pihakKepentinganList) > 1}">
                <c:if test="${fn:length(actionBean.tuanTanahTerlibatList) > 0}">
                    <fieldset class="aras1" id="terlibatList">
                        <legend>
                            Senarai Tuan Tanah Terlibat
                        </legend>
                        <div class="content" align="center">

                            <c:if test="${actionBean.p.kodUrusan.kod eq 'TMADT'}">
                                <c:if test="${edit}">
                                    <font size="2" color="red">
                                        <b>Sila masukkan maklumat kematian pada bahagian kemaskini.</b>
                                    </font>
                                </c:if>
                            </c:if>

                            <display:table class="tablecloth" name="${actionBean.tuanTanahTerlibatList}" cellpadding="0" cellspacing="0" id="lineTerlibat"
                                           requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

                                <c:if test="${edit}">
                                    <display:column>
                                        <s:checkbox name="rm_terlibat" id="rm_terlibat${lineTerlibat_rowNum-1}" value="${lineTerlibat.idPermohonanPihak}" class="removeTerlibat"/>
                                    </display:column>
                                </c:if>

                                <display:column title="Bil" sortable="true">${lineTerlibat_rowNum}
                                    <s:hidden name="xx" class="xx${lineTerlibat_rowNum -1}" value="${lineTerlibat.pihak.idPihak}"/>
                                </display:column>
                                <display:column  title="Nama" >
                                    <a href="#" onclick="viewPihak('${lineTerlibat.pihak.idPihak}', 'terlibat');return false;"><font style="text-transform:uppercase;">${lineTerlibat.pihak.nama}</font></a>
                                </display:column>
                                <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                                <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                                <c:if test="${actionBean.p.kodUrusan.idWorkflow eq 'http://xmlns.oracle.com/Consent_Negeri/Consent/ADAT' || actionBean.p.kodUrusan.kod eq 'BTADT'}">
                                    <c:if test="${line.pihak.jenisPengenalan.kod eq 'B' || line.pihak.jenisPengenalan.kod eq 'L' || line.pihak.jenisPengenalan.kod eq 'P' || line.pihak.jenisPengenalan.kod eq 'T' || line.pihak.jenisPengenalan.kod eq 'I' || line.pihak.jenisPengenalan.kod eq 'F'}">

                                        <c:if test="${edit}">
                                            <display:column title="Kemaskini">
                                                <p align="center">
                                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                         onclick="editSimati('${lineTerlibat_rowNum -1}', 'terlibat');return false;" onmouseover="this.style.cursor='pointer';">
                                                </p>
                                            </display:column>
                                        </c:if>
                                    </c:if>
                                </c:if>
                            </display:table>
                        </div>
                        <p align="center">
                            <c:if test="${edit && fn:length(actionBean.tuanTanahTerlibatList) > 0}" >
                                <s:button class="btn" value="Hapus" name="new" id="new" onclick="removeMultipleTerlibat();"/>&nbsp;
                            </c:if>
                        </p>
                    </fieldset>
                    <br/>
                </c:if>
            </c:if>
        </c:if>
        <c:if test="${!edit}">
            <c:if test="${fn:length(actionBean.pihakKepentinganList) > 1}">
                <c:if test="${fn:length(actionBean.tuanTanahTerlibatList) > 0}">
                    <fieldset class="aras1">
                        <legend>
                            Senarai Tuan Tanah Terlibat
                        </legend>
                        <div class="content" align="center">
                            <display:table class="tablecloth" name="${actionBean.tuanTanahTerlibatList}" cellpadding="0" cellspacing="0" id="lineTerlibat"
                                           requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                                <display:column title="Bil" sortable="true">${lineTerlibat_rowNum}
                                    <s:hidden name="xx" class="xx${lineTerlibat_rowNum -1}" value="${lineTerlibat.pihak.idPihak}"/>
                                </display:column>
                                <display:column  title="Nama" >
                                    <a href="#" onclick="viewPihak('${lineTerlibat.pihak.idPihak}', 'terlibat');return false;"><font style="text-transform:uppercase;">${lineTerlibat.pihak.nama}</font></a>
                                </display:column>
                                <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                                <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                            </display:table>
                        </div>
                    </fieldset>
                    <br/>
                </c:if>
            </c:if>
        </c:if>
        <fieldset class="aras1">
            <legend>
                Senarai Pemohon
            </legend>
            <c:if test="${edit}">
                <p align="center">
                    <font size="2" color="red">
                        <c:if test="${actionBean.p.kodUrusan.kod ne 'TMADT'}">
                            <b>
                                Sila Pilih Butang 'Tambah Baru' Jika Tuan Tanah Bukan Pemohon
                            </b>
                        </c:if>
                    </font>
                </p>
            </c:if>
            <%--second layer start--%>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.pemohonList}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

                    <c:if test="${edit}">
                        <display:column>
                            <s:checkbox name="rm" id="rm_${line_rowNum-1}" value="${line.idPemohon}" class="remove"/>
                        </display:column>
                    </c:if>

                    <display:column title="Bil" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                    </display:column>
                    <display:column  title="Nama" >
                        <a href="#" onclick="viewPihak('${line.pihak.idPihak}', 'pemohon');return false;"><font style="text-transform:uppercase;">${line.pihak.nama}</font></a>
                    </display:column>
                    <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                    <display:column title="Alamat Surat-menyurat"><font style="text-transform:uppercase;">${line.pihak.suratAlamat1}</font>
                        <c:if test="${line.pihak.suratAlamat2 ne null}">,</c:if>
                        <font style="text-transform:uppercase;"> ${line.pihak.suratAlamat2}</font>
                        <c:if test="${line.pihak.suratAlamat3 ne null}"> </c:if>
                        <font style="text-transform:uppercase;">${line.pihak.suratAlamat3}</font>
                        <c:if test="${line.pihak.suratAlamat4 ne null}">,</c:if>
                        <font style="text-transform:uppercase;">${line.pihak.suratAlamat4}</font>
                        <c:if test="${line.pihak.suratPoskod ne null}">,</c:if>
                        ${line.pihak.suratPoskod}
                        <c:if test="${line.pihak.suratNegeri.kod ne null}">,</c:if>
                        <font style="text-transform:uppercase;">${line.pihak.suratNegeri.nama}</font>
                    </display:column>
                    <c:if test="${edit}">
                        <display:column title="Kemaskini">
                            <p align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                     onclick="dopopup('${line_rowNum -1}','${actionBean.p.kodUrusan.kod}');return false;" onmouseover="this.style.cursor='pointer';">
                            </p>
                        </display:column>
                    </c:if>
                </display:table>
                <br/>
                <p align="center">
                    <c:if test="${edit}">
                        <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addNewPemohon();"/>&nbsp;
                    </c:if>
                    <c:if test="${edit && fn:length(actionBean.pemohonList) > 0}" >
                        <s:button class="btn" value="Hapus" name="new" id="new" onclick="removeMultiplePemohon();"/>&nbsp;
                    </c:if>
                </p>
            </div>
        </fieldset>
        <br/>
        <div id="mohon_pihak">
            <fieldset class="aras1">
                <legend>Senarai
                    <c:choose>
                        <c:when test="${actionBean.p.kodUrusan.kod eq 'PCPTD' || actionBean.p.kodUrusan.kod eq 'PGDMB' || actionBean.p.kodUrusan.kod eq 'PCMMK' || actionBean.p.kodUrusan.kod eq 'PMMAT' || actionBean.p.kodUrusan.kod eq 'PMPTD' || actionBean.p.kodUrusan.kod eq 'PJKTL' || actionBean.p.kodUrusan.kod eq 'PMJTL'}">
                            Penerima Pindah Milik
                        </c:when>
                        <c:when test="${actionBean.p.kodUrusan.kod eq 'PJPTD' || actionBean.p.kodUrusan.kod eq 'PJMMK'}">
                            Penerima Pajakan
                        </c:when>
                        <c:when  test="${actionBean.p.kodUrusan.kod eq 'MCPTD' || actionBean.p.kodUrusan.kod eq 'MCGDMB' || actionBean.p.kodUrusan.kod eq 'MCMMK'}">
                            Penerima Gadaian
                        </c:when>
                        <c:when test="${actionBean.p.kodUrusan.kod eq 'PJPTD' || actionBean.p.kodUrusan.kod eq 'PJMMK'}">
                            Penerima Pajakan
                        </c:when>
                        <c:when test="${actionBean.p.kodUrusan.kod eq 'BTADT'}">
                            Pihak Yang Menuntut
                        </c:when>
                        <c:when test="${actionBean.p.kodUrusan.kod eq 'PAADT'}">
                            Pemegang Amanah
                        </c:when>
                        <c:otherwise>
                            Penerima
                        </c:otherwise>
                    </c:choose>
                </legend>

                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.mohonPihakList}" cellpadding="0" cellspacing="0" id="lineMP">
                        <c:if test="${edit}">
                            <display:column>
                                <s:checkbox name="rm_mp" id="rm_mp${lineMP_rowNum-1}" value="${lineMP.idPermohonanPihak}" class="remove2"/>
                            </display:column>
                        </c:if>
                        <display:column title="Bil" sortable="true">${lineMP_rowNum}
                            <s:hidden name="a" class="a${lineMP_rowNum-1}" value="${lineMP.pihak.idPihak}"/>
                        </display:column>
                        <display:column  title="Nama" >
                            <a href="#" onclick="viewPihak('${lineMP.pihak.idPihak}','penerima');return false;"><font style="text-transform:uppercase;">${lineMP.pihak.nama}</font></a>
                        </display:column>

                        <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" class="penerimaKP"/>

                        <c:if test="${!edit}">
                            <c:if test="${actionBean.p.kodUrusan.kod ne 'BTADT'}">
                                <display:column title="Bahagian yang diterima">${lineMP.syerPembilang}/${lineMP.syerPenyebut}</display:column>
                            </c:if>
                        </c:if>

                        <c:if test="${edit}">
                            <c:if test="${actionBean.p.kodUrusan.kod ne 'BTADT'}">
                                <display:column title="Bahagian yang diterima">
                                    <div align="center">
                                        <s:text name="syer1[${lineMP_rowNum-1}]" size="5" id="syer1${lineMP_rowNum-1}"
                                                onblur="updateSyer('${lineMP.idPermohonanPihak}', '${lineMP_rowNum-1}')"
                                                onchange="updateSyer('${lineMP.idPermohonanPihak}', '${lineMP_rowNum-1}'"
                                                onkeyup="validateNumber(this,this.value);" class="pembilang"/> /
                                        <s:text name="syer2[${lineMP_rowNum-1}]" size="5" id="syer2${lineMP_rowNum-1}"
                                                onblur="updateSyer('${lineMP.idPermohonanPihak}', '${lineMP_rowNum-1}')"
                                                onchange="updateSyer('${lineMP.idPermohonanPihak}', '${lineMP_rowNum-1}'"
                                                onkeyup="validateNumber(this,this.value);" class="penyebut"/>
                                    </div>
                                </display:column>
                            </c:if>
                            <display:column title="Kemaskini">
                                <p align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                         onclick="editPenerima('${lineMP_rowNum-1}','penerima');return false;" onmouseover="this.style.cursor='pointer';">
                                </p>
                            </display:column>
                        </c:if>
                    </display:table>
                </div>

                <c:if test="${edit}">
                    <p align="center">
                        <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addNewPB();"/>&nbsp;
                        <c:if test="${actionBean.p.kodUrusan.kod ne 'BTADT'}">
                            <c:if test="${fn:length(actionBean.mohonPihakList) > 0}">
                                <s:button class="btn" value="Semak Syer" name="semak" id="semak" onclick="semakSyer(this.form,'penerima');"/>&nbsp;
                                <s:button class="longbtn" value="Agih Sama Rata" name="" id="_agihSamaRata" onclick="samaRata(this.form,'penerima');"/>&nbsp;
                            </c:if>
                        </c:if>
                        <c:if test="${fn:length(actionBean.mohonPihakList) > 0}">
                            <s:button class="btn" value="Hapus" name="" onclick="removeMultipleMohonPihak('penerima');"/>&nbsp;
                        </c:if>
                    </p>
                </c:if>
            </fieldset>
        </div>
        <c:if test="${actionBean.p.kodUrusan.kod eq 'PCPTD' || actionBean.p.kodUrusan.kod eq 'PGDMB' || actionBean.p.kodUrusan.kod eq 'PCMMK'}">
            <br/>
            <div id="mohon_gadaian">
                <fieldset class="aras1">
                    <legend>Senarai Penerima Gadaian </legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.penerimaGadaianList}" cellpadding="0" cellspacing="0" id="lineMP2">
                            <c:if test="${edit}">
                                <display:column>
                                    <s:checkbox name="rm_mp2" id="rm_mp2${lineMP2_rowNum-1}" value="${lineMP2.idPermohonanPihak}" class="remove3"/>
                                </display:column>
                            </c:if>
                            <display:column title="Bil" sortable="true">${lineMP2_rowNum}
                                <s:hidden name="a2" class="a2${lineMP2_rowNum-1}" value="${lineMP2.pihak.idPihak}"/>
                            </display:column>
                            <display:column  title="Nama" >
                                <a href="#" onclick="viewPihak('${lineMP2.pihak.idPihak}','penerima');return false;"><font style="text-transform:uppercase;">${lineMP2.pihak.nama}</font></a>
                            </display:column>
                            <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" class="penerimaKP"/>
                            <c:if test="${!edit}">
                                <display:column title="Bahagian yang diterima">${lineMP2.syerPembilang}/${lineMP2.syerPenyebut}</display:column>
                            </c:if>
                            <c:if test="${edit}">
                                <display:column title="Bahagian yang diterima">
                                    <div align="center">
                                        <s:text name="syer1B[${lineMP2_rowNum-1}]" size="5" id="syer1B${lineMP2_rowNum-1}"
                                                onblur="updateSyer('${lineMP2.idPermohonanPihak}', '${lineMP2_rowNum-1}')"
                                                onchange="updateSyer('${lineMP2.idPermohonanPihak}', '${lineMP2_rowNum-1}'"
                                                onkeyup="validateNumber(this,this.value);" class="pembilang2"/> /
                                        <s:text name="syer2B[${lineMP2_rowNum-1}]" size="5" id="syer2B${lineMP2_rowNum-1}"
                                                onblur="updateSyer('${lineMP2.idPermohonanPihak}', '${lineMP2_rowNum-1}')"
                                                onchange="updateSyer('${lineMP2.idPermohonanPihak}', '${lineMP2_rowNum-1}'"
                                                onkeyup="validateNumber(this,this.value);" class="penyebut2"/>
                                    </div>
                                </display:column>
                                <display:column title="Kemaskini">
                                    <p align="center">
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="editPenerima('${lineMP2_rowNum-1}','gadaian');return false;" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </display:column>

                            </c:if>
                        </display:table>
                    </div>

                    <c:if test="${edit}">
                        <p align="center">
                            <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addNewPB();"/>&nbsp;
                            <c:if test="${fn:length(actionBean.penerimaGadaianList) > 0}">
                                <s:button class="btn" value="Semak Syer" name="semak" id="semak" onclick="semakSyer(this.form,'gadaian');"/>&nbsp;
                                <s:button class="longbtn" value="Agih Sama Rata" name="" id="_agihSamaRata" onclick="samaRata(this.form,'gadaian');"/>&nbsp;
                            </c:if>

                            <c:if test="${fn:length(actionBean.penerimaGadaianList) > 0}">
                                <s:button class="btn" value="Hapus" name="" onclick="removeMultipleMohonPihak('gadaian');"/>&nbsp;
                            </c:if>
                        </p>
                    </c:if>
                </fieldset>
            </div>
        </c:if>
    </s:form>
</div>

