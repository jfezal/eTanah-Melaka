
<%--
    Document   : kemasukan_pihak_berkepentingan
    Created on : 15-Oct-2009, 03:49:43
    Author     : md.nurfikri
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ageCalculator.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $('.alphanumeric').alphanumeric();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $('#jenisPengenalan').change(function(){
            dodacheck($("#kp").val());
        });
        var v = '${actionBean.pihak.jenisPengenalan.kod}';
        if(v == "S"){
            $('#suku').hide();
        }

        else{
            $('#suku').show();
        }

        $('form').submit(function(){
            var val = $('#kp').val();
            var jenis = '${jenis}';
            return doCheckUmur(val, 'kp',jenis);
        });

        if(v == "B"){
            var icNo = '${actionBean.pihak.noPengenalan}';
            var age = calculateAge(icNo.substring(4,6), icNo.substring(2,4), 19 + icNo.substring(0,2));
            $('#umur').val(age);
            $('#datepicker').val(icNo.substring(4,6)+"/"+icNo.substring(2,4)+"/"+19 + icNo.substring(0,2));
        }

        $('#kod_warganegara').val('MAL');

    });

    function calAgeByDOB(value){
        var age = calculateAge(value.substring(0,2), value.substring(3,5), 19 + value.substring(8,10));
        $('#umur').val(age);
    }

    function changeHideSuku(value){
        if(value == "S"){
            $('#suku').hide();
        }else{
            $('#suku').show();
        }
    }

    function doValidation(){
        var val = $('#kod_warganegara').val();
        var val2 = $('#alamat1').val();
        var val3 = $('#nama_pemohon').val();
        var val4 = $('#jenis_pihak').val();
        if(val3 == ''){
            alert('Sila masukan nama pihak.');
            return false;
        } else if(val == ''){
            alert('Sila pilih warganegara.');
            return false;
        }else if(val2 == ''){
            alert('Sila masukan alamat berdaftar.');
            return false;
        }else if (val4 == ''){
            alert('Sila Masukan Jenis Pihak Berkepentingan.');
            return false;
        }
        return true;
    }

    function validationPB(){
        if ($('#jenis_pihak').val() == ''){
            alert('Sila Masukan Jenis Pihak Berkepentingan.');
            return false;
        }

        else  if ($('#alamat1').val() == ''){
            alert('Sila Masukan Alamat Berdaftar.');
            return false;
        }
        return true;
    }

    function save(event, f){
        if(doValidation()){
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);

                self.close();

            },'html');

        }
    }

    function savePemohon(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);            
            self.opener.refreshPagePemohon();
            self.close();
        },'html');
    }

    function changeHideSuku(value){

        if(value == "S"){
            $('#suku').hide();
        }

        else{
            $('#suku').show();
        }
    }

    function copyAddCaw(){
        if($('input[name=addCaw]').is(':checked')){
            $('#suratAlamatCaw1').val($('#alamatCaw1').val());
            $('#suratAlamatCaw2').val($('#alamatCaw2').val());
            $('#suratAlamatCaw3').val($('#alamatCaw3').val());
            $('#suratAlamatCaw4').val($('#alamatCaw4').val());
            $('#suratPoskodCaw').val($('#poskodCaw').val());
            $('#suratNegeriCaw').val($('#negeriCaw').val());
        }else{
            $('#suratAlamatCaw1').val('');
            $('#suratAlamatCaw2').val('');
            $('#suratAlamatCaw3').val('');
            $('#suratAlamatCaw4').val('');
            $('#suratPoskodCaw').val('');
            $('#suratNegeriCaw').val('');

        }
    }

    function copyAdd(){
        if($('input[name=add1]').is(':checked')){
            $('#suratAlamat1').val($('#alamat1').val());
            $('#suratAlamat2').val($('#alamat2').val());
            $('#suratAlamat3').val($('#alamat3').val());
            $('#suratAlamat4').val($('#alamat4').val());
            $('#suratPoskod').val($('#poskod').val());
            $('#suratNegeri').val($('#negeri').val());
        }else{
            $('#suratAlamat1').val('');
            $('#suratAlamat2').val('');
            $('#suratAlamat3').val('');
            $('#suratAlamat4').val('');
            $('#suratPoskod').val('');
            $('#suratNegeri').val('');

        }
    }

    function removeCawangan(val){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?deleteCawangan&idCawangan='+val;
            $.get(url,
            function(data){
                $('#caw').html(data);
            },'html');
            location.reload(true);
        }
    }

    function removeCawanganPemohon(val){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?deleteCawanganPemohon&idCawangan='+val;
            $.get(url,
            function(data){
                $('#caw').html(data);
            },'html');
            location.reload(true);
        }
    }

    function editCawangan(val){

        var jenisPB = $('#jenis_pihak').val();

        var url = '${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?editCawangan&idCawangan='+val+'&jenisPB='+jenisPB;
        $.get(url,
        function(data){
            $('#caw').html(data);
        },'html');
    }

    function editCawanganPemohon(val){
        var url = '${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?editCawanganPemohon&idCawangan='+val;
        $.get(url,
        function(data){
            $('#caw').html(data);
        },'html');
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

    function dodacheck(val) {
        //var mikExp = /[1\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\1\|]/;
        var v = $('#jenisPengenalan').val();

        if(v == 'B') {
            var strPass = val;
            var strLength = strPass.length;
            //$('#kp').attr("maxlength","12");
            if(strLength > 12) {
                var tst = val.substring(0, (strLength) - 1);
                $('#kp').val(tst);
            }
            var lchar = val.charAt((strLength) - 1);
            if(isNaN(lchar)) {
                //return false;
                var tst = val.substring(0, (strLength) - 1);
                $('#kp').val(tst);
            }
        }//else{
        // $('#kp').attr("maxlength","30");
        // }
    }

    function doCheckUmur(v,id,type){
        var va = $('#jenisPengenalan').val();
        if(va == 'B'){
            return doValidateAge(v, id, 'jenisPengenalan',type);
        }else {
            return true;
        }
    }

    <%--Newly Added--%>
        function editPengarahPemohon(val){
            var url = '${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?editPengarahPemohon&idPengarah='+val;
            $.get(url,
            function(data){
                $('#caw').html(data);
            },'html');
        }

        function removePengarahPemohon(val){
             if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?deletePengarahPemohon&idPengarah='+val;
                $.get(url,
                function(data){
                    $('#caw').html(data);
                },'html');
                location.reload(true);
            }
        }
        
        function editPengarah(val){
            var jenisPB = $('#jenis_pihak').val();
            var url = '${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?editPengarah&idPengarah='+val+'&jenisPB='+jenisPB;
            $.get(url,
            function(data){
                $('#caw').html(data);
            },'html');
        }

        function removePengarah(val){
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?deletePengarah&idPengarah='+val;
                $.get(url,
                function(data){
                    $('#caw').html(data);
                },'html');
                location.reload(true);
            }
        }

</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<div class="subtitle" id="caw">
    <s:form beanclass="etanah.view.stripes.pembangunan.PihakBerkepentinganActionBean">
        <s:hidden name="jenisPihak"/>
        <s:hidden name="pihak.nama"/>
        <s:hidden name="pihak.idPihak"/>
        <s:hidden name="pihak.jenisPengenalan.kod"/>
        <s:hidden name="pihak.jenisPengenalan.nama"/>
        <s:hidden name="pihak.noPengenalan"/>
        <s:hidden name="pihak.bangsa.kod"/>
        <s:hidden name="pihak.suku.kod"/>
        <s:hidden name="pihak.alamat1"/>
        <s:hidden name="pihak.alamat2"/>
        <s:hidden name="pihak.alamat3"/>
        <s:hidden name="pihak.alamat4"/>
        <s:hidden name="pihak.poskod"/>
        <s:hidden name="pihak.negeri.kod"/>
        <s:hidden name="pihak.negeri.nama"/>
        <s:hidden name="permohonanPihak.jenis.kod"/>
        <s:hidden name="pihakCawangan.idPihakCawangan"/>
        <s:hidden name="jenisPihak" value="${jenis}"/>

        <c:if test="${tuanTanah}">
            <s:text name="tuanTanah" value="${tuanTanah}"/>
        </c:if>
        <s:errors/>
        <s:messages/>
        <%--<c:if test="${!actionBean.tambahCawFlag}">--%>
        <c:if test="${!actionBean.tambahCawFlag && !actionBean.tambahPengarahFlag}">

            <fieldset class="aras1">
                <legend>
                    <%--<c:if test="${pemohon}">--%>
                        Kemasukan Maklumat Pemohon
                    <%--</c:if>--%>

                    <%--<c:if test="${!pemohon}">
                        <c:choose>
                            <c:when test="${jenis eq 'PA'}">
                                Kemasukan Maklumat Pemegang Amanah
                            </c:when>
                            <c:when test="${jenis eq 'WAR'}">
                                Kemasukan Maklumat Penerima Amanah
                            </c:when>
                            <c:otherwise>
                                Kemasukan Maklumat Penerima
                            </c:otherwise>
                        </c:choose>
                    </c:if>--%>
                </legend>
                <br/>

                <c:if test="${!actionBean.cariFlag}">

                    <c:if test="${actionBean.kodNegeri eq '05'}">
                        <p>
                            <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                            <s:select name="pihak.jenisPengenalan.kod" id="jenisPengenalan" onchange="javaScript:changeHideSuku(this.options[selectedIndex].text);">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                    </c:if>

                    <c:if test="${actionBean.kodNegeri eq '04'}">
                        <p>
                            <label for="Jenis Pengenalan"><font color="red">*</font>Jenis Pengenalan :</label>
                            <s:select name="pihak.jenisPengenalan.kod" id="jenisPengenalan">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                    </c:if>

                    <p>
                        <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                        <s:text name="pihak.noPengenalan" id="kp" size="40" onkeyup="dodacheck(this.value);this.value=this.value.toUpperCase();"
                                onblur="doCheckUmur(this.value, this.id,'${jenis}')"/>
                    </p>
                </c:if>

                <c:if test="${actionBean.cariFlag}">

                    <c:if test="${!actionBean.tiadaDataFlag}">
                        <p>
                            <label for="nama">Nama :</label>
                            ${actionBean.pihak.nama}&nbsp;
                        </p>
                        <p>
                            <label for="Jenis Pengenalan">Jenis Pengenalan :</label>
                            ${actionBean.pihak.jenisPengenalan.nama}&nbsp;
                        </p>
                        <p>
                            <label for="No Pengenalan">Nombor Pengenalan :</label>
                            ${actionBean.pihak.noPengenalan}&nbsp;
                        </p>

                        <c:if test="${actionBean.pihak.bangsa.kod ne null}">
                            <p>
                                <label>Bangsa / Jenis Syarikat :</label>
                                ${actionBean.pihak.bangsa.nama}&nbsp;
                            </p>
                        </c:if>

                        <c:if test="${actionBean.kodNegeri eq '05'}">
                            <c:if test="${actionBean.pihak.suku.kod ne null}">
                                <p id="suku">
                                    <label>Jenis Suku :</label>
                                    ${actionBean.pihak.suku.nama}&nbsp;
                                </p>
                            </c:if>
                        </c:if>

                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                            <p>
                                <label>Tarikh Lahir :</label>
                                <s:text name="tarikhLahir" size="40" id="datepicker" class="datepicker" onchange="calAgeByDOB(this.value);"/>
                            </p>
                            <p>
                                <label>Umur :</label>
                                <s:text name="permohonanPihak.umur" size="10" maxlength="3" id="umur" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Tempat Lahir :</label>
                                <s:select name="pihak.tempatLahir">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="nama"/>
                                </s:select>
                            </p>
                            <p>
                                <label>Kewarganegaraan :</label>
                                <s:hidden name="pihak.wargaNegara.kod" id="kod_warganegara"/>
                                ${actionBean.pihak.wargaNegara.nama}&nbsp;
                            </p>
                            <p>
                                <label>Status Perkahwinan :</label>
                                <s:select name="permohonanPihak.statusKahwin">
                                    <s:option>Sila Pilih</s:option>
                                    <s:option>Berkahwin</s:option>
                                    <s:option>Bujang</s:option>
                                    <s:option>Duda</s:option>
                                    <s:option>Ibu Tunggal</s:option>
                                </s:select>
                            </p>
                            <p>
                                <label>Pekerjaan :</label>
                                <s:text name="permohonanPihak.pekerjaan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label>Pendapatan Bulanan (RM) :</label>
                                <s:text name="permohonanPihak.pendapatan" size="40" maxlength="12" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Tanggungan :</label>
                                <s:text name="permohonanPihak.tangungan" size="40" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <%--<p>
                                <label>Hubungan :</label>
                                <s:text name="permohonanPihak.kaitan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>--%>
                        </c:if>
                        <%-- <c:if test="${!pemohon}">
                             <p>
                                 <label for="nama"><font color="red">*</font>Jenis Pihak Berkepentingan :</label>
                                 <s:select name="permohonanPihak.jenis.kod" style="width:400px" id="jenis_pihak">
                                     <s:option value="" >Sila Pilih</s:option>
                                     <c:if test="${actionBean.p.kodUrusan.jabatanNama ne 'Consent'}">
                                         <s:options-collection collection="${list.senaraiKodJenisPihakBerkepentingan}" label="nama" value="kod"/>
                                     </c:if>
                                     <c:if test="${actionBean.p.kodUrusan.jabatanNama eq 'Consent'}">
                                         <s:options-collection collection="${actionBean.senaraiKodPenerima}" label="nama" value="kod" />
                                     </c:if>
                                 </s:select>
                             </p>
                         </c:if>--%>

                        <c:if test="${!pemohon}">
                            <p>
                                <label for="nama"><font color="red">*</font>Jenis Pihak Berkepentingan :</label>
                                <s:select name="jenisPihak" style="width:400px" id="jenis_pihak">
                                    <s:option value="" >Sila Pilih</s:option>
                                    <%--<c:if test="${actionBean.p.kodUrusan.jabatanNama ne 'Consent'}">
                                        <s:options-collection collection="${list.senaraiKodJenisPihakBerkepentingan}" label="nama" value="kod"/>
                                    </c:if>
                                    <c:if test="${actionBean.p.kodUrusan.jabatanNama eq 'Consent'}">
                                        <s:options-collection collection="${actionBean.senaraiKodPenerima}" label="nama" value="kod" />
                                    </c:if>--%>
                                    <s:options-collection collection="${actionBean.senaraiKodPenerima}" label="nama" value="kod" />
                                </s:select>
                            </p>
                        </c:if>
                        <p>
                            <label for="alamat">Alamat Berdaftar :</label>
                            ${actionBean.pihak.alamat1}&nbsp;
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            ${actionBean.pihak.alamat2}&nbsp;
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            ${actionBean.pihak.alamat3}&nbsp;
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            ${actionBean.pihak.alamat4}&nbsp;
                        <p>
                            <label>Poskod :</label>
                            ${actionBean.pihak.poskod}&nbsp;
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            ${actionBean.pihak.negeri.nama}&nbsp;
                        </p>
                        <p>
                            <label for="alamat">Alamat Surat-Menyurat :</label>
                            <s:text name="pihak.suratAlamat1" size="40" maxlength="40" />
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat2" size="40" maxlength="40" />
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat3" size="40" maxlength="40"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat4" size="40" maxlength="40"/>
                        </p>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="pihak.suratPoskod" size="40" maxlength="5"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select name="pihak.suratNegeri.kod">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'S' || actionBean.pihak.jenisPengenalan.kod eq 'D' || actionBean.pihak.jenisPengenalan.kod eq 'N' || actionBean.pihak.jenisPengenalan.kod eq 'U'}">
                        <p>
                            <label>Modal Berbayar :</label>
                            <s:text name="pihak.modalBerbayar" id="modalBerbayar" size="40" maxlength="15" onkeyup="validateNumber(this,this.value);"/>
                        </p>
                        <p>
                            <label>Modal Dibenarkan :</label>
                            <s:text name="pihak.modalDibenar" id="modalDibenar" size="40" maxlength="15" onkeyup="validateNumber(this,this.value);"/>
                        </p>
                        <p>
                            <label>Tarikh Daftar Syarikat :</label>
                            <s:text name="pihak.tarikhLahir" id="datepicker" class="datepicker" size="40" formatPattern="dd/MM/yyyy" />
                        </p>
                        </c:if>

                    </c:if>
                    <%--tiada data--%>
                    <c:if test="${actionBean.tiadaDataFlag}">
                        <p>
                            <label for="nama"><font color="red">*</font>Nama :</label>
                            <s:text name="pihak.nama" id="nama_pemohon" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>

                        <c:if test="${actionBean.kodNegeri eq '05'}">
                            <p>
                                <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                <s:select name="pihak.jenisPengenalan.kod" onchange="javaScript:changeHideSuku(this.options[selectedIndex].text)">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                        </c:if>

                        <c:if test="${actionBean.kodNegeri eq '04'}">
                            <p>
                                <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                <s:select name="pihak.jenisPengenalan.kod">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                        </c:if>


                        <p>
                            <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                            <s:text name="pihak.noPengenalan" id="kp" size="40" onkeyup="dodacheck(this.value);this.value=this.value.toUpperCase();"/>
                        </p>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L'
                                      || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T'
                                      || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                              <p>
                                  <label><font color="red">*</font>Kewarganegaraan :</label>
                                  <s:select name="pihak.wargaNegara.kod" style="width:200px" id="kod_warganegara">
                                      <s:option value="">Sila Pilih</s:option>
                                      <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                                  </s:select>
                              </p>
                        </c:if>
                        <%--                        <c:if test="${!pemohon}">
                                                    <p>
                                                        <label for="nama"><font color="red">*</font>Jenis Pihak Berkepentingan :</label>
                                                        <s:select name="permohonanPihak.jenis.kod" style="width:400px" id="jenis_pihak">
                                                            <s:option value="" >Sila Pilih</s:option>
                                                            <c:if test="${actionBean.p.kodUrusan.jabatanNama ne 'Consent'}">
                                                                <s:options-collection collection="${list.senaraiKodJenisPihakBerkepentingan}" label="nama" value="kod"/>
                                                            </c:if>
                                                            <c:if test="${actionBean.p.kodUrusan.jabatanNama eq 'Consent'}">
                                                                <s:options-collection collection="${actionBean.senaraiKodPenerima}" label="nama" value="kod" />
                                                            </c:if>
                                                        </s:select>
                                                    </p>
                                                </c:if>--%>

                        <c:if test="${!pemohon}">
                            <p>
                                <label for="nama"><font color="red">*</font>Jenis Pihak Berkepentingan :</label>
                                <s:select name="jenisPihak" style="width:400px" id="jenis_pihak">
                                    <s:option value="" >Sila Pilih</s:option>
                                    <%--<c:if test="${actionBean.p.kodUrusan.jabatanNama ne 'Consent'}">
                                        <s:options-collection collection="${list.senaraiKodJenisPihakBerkepentingan}" label="nama" value="kod"/>
                                    </c:if>
                                    <c:if test="${actionBean.p.kodUrusan.jabatanNama eq 'Consent'}">
                                        <s:options-collection collection="${actionBean.senaraiKodPenerima}" label="nama" value="kod" />
                                    </c:if>--%>
                                    <s:options-collection collection="${actionBean.senaraiKodPenerima}" label="nama" value="kod" />
                                </s:select>
                            </p>
                        </c:if>


                        <p>
                            <label for="alamat"><font color="red">*</font>Alamat Berdaftar :</label>
                            <s:text name="pihak.alamat1" size="40" id="alamat1" maxlength="40"/>
                        </p>

                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.alamat2" size="40" id="alamat2" maxlength="40"/>
                        </p>

                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.alamat3" size="40" id="alamat3" maxlength="40"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.alamat4" size="40" id="alamat4" maxlength="40"/>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="pihak.poskod" size="40" maxlength="5" id="poskod" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select name="pihak.negeri.kod" id="negeri">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>

                        <p>
                            <label>Bangsa / Jenis Syarikat :</label>
                            <s:select name="pihak.bangsa.kod" style="width:400px">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraiKodBangsa}" label="nama" value="kod" />
                            </s:select>
                        </p>
                        <c:if test="${actionBean.kodNegeri eq '05'}">
                            <p id="suku">
                                <label for="Suku">Jenis Suku :</label>
                                <s:select name="pihak.suku.kod" style="width:200px">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodSuku}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                        </c:if>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                            <p>
                                <label>Tarikh Lahir :</label>
                                <s:text name="tarikhLahir" size="40" id="datepicker" class="datepicker" onchange="calAgeByDOB(this.value);"/>
                            </p>
                            <p>
                                <label>Umur :</label>
                                <s:text name="permohonanPihak.umur" size="10" maxlength="3" id="umur" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Tempat Lahir :</label>
                                <s:select name="pihak.tempatLahir">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="nama"/>
                                </s:select>
                            </p>
                            <p>
                                <label>Status Perkahwinan :</label>
                                <s:select name="permohonanPihak.statusKahwin">
                                    <s:option>Sila Pilih</s:option>
                                    <s:option>Berkahwin</s:option>
                                    <s:option>Bujang</s:option>
                                    <s:option>Duda</s:option>
                                    <s:option>Ibu Tunggal</s:option>
                                </s:select>
                            </p>
                            <p>
                                <label>Pekerjaan :</label>
                                <s:text name="permohonanPihak.pekerjaan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label>Pendapatan Bulanan (RM) :</label>
                                <s:text name="permohonanPihak.pendapatan" size="40" maxlength="12" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Tanggungan :</label>
                                <s:text name="permohonanPihak.tangungan" size="40" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Hubungan :</label>
                                <s:text name="permohonanPihak.kaitan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                        </c:if>

                        <p>
                            <label>&nbsp;</label>
                            <input type="checkbox" id="add1" name="add1" value="" onclick="copyAdd();"/>
                            <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                        </p>
                        <p>
                            <label for="alamat">Alamat Surat-Menyurat :</label>
                            <s:text name="pihak.suratAlamat1" id="suratAlamat1" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat2" id="suratAlamat2" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat3" id="suratAlamat3" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat4" id="suratAlamat4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="pihak.suratPoskod" id="suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select name="pihak.suratNegeri.kod" id="suratNegeri">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                       <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'S' || actionBean.pihak.jenisPengenalan.kod eq 'D' || actionBean.pihak.jenisPengenalan.kod eq 'N' || actionBean.pihak.jenisPengenalan.kod eq 'U'}">
                        <p>
                            <label>Modal Berbayar :</label>
                            <s:text name="pihak.modalBerbayar" id="modalBerbayar" size="40" maxlength="15" onkeyup="validateNumber(this,this.value);"/>
                        </p>
                        <p>
                            <label>Modal Dibenarkan :</label>
                            <s:text name="pihak.modalDibenar" id="modalDibenar" size="40" maxlength="15" onkeyup="validateNumber(this,this.value);"/>
                        </p>
                        <p>
                            <label>Tarikh Daftar Syarikat :</label>
                            <s:text name="pihak.tarikhLahir" id="datepicker" class="datepicker" size="40" />
                        </p>
                       </c:if>
                    </c:if>
                    <c:if test="${actionBean.pihak.jenisPengenalan.kod ne 'B' && actionBean.pihak.jenisPengenalan.kod ne 'L' && actionBean.pihak.jenisPengenalan.kod ne 'P' && actionBean.pihak.jenisPengenalan.kod ne 'T' && actionBean.pihak.jenisPengenalan.kod ne 'I'}">
                        <div class="content" align="center">
                            <br>
                            Maklumat Cawangan

                            <display:table name="${actionBean.cawanganList}" id="line" class="tablecloth" >

                                <display:column title="Pilih"><s:radio name="idCawangan" value="${line.idPihakCawangan}"/></display:column>
                                <display:column property="namaCawangan" title="Nama"/>
                                <display:column title="Alamat" >${line.suratAlamat1}
                                    <c:if test="${line.suratAlamat2 ne null}"> , </c:if>
                                    ${line.suratAlamat2}
                                    <c:if test="${line.suratAlamat3 ne null}"> , </c:if>
                                    ${line.suratAlamat3}
                                    <c:if test="${line.suratAlamat4 ne null}"> , </c:if>
                                    ${line.suratAlamat4}</display:column>
                                <display:column property="suratPoskod" title="Poskod" />
                                <display:column property="suratNegeri.nama" title="Negeri" />

                                <c:if test="${pemohon}">
                                    <display:column title="Kemaskini">
                                        <p align="center">
                                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                 onclick="editCawanganPemohon('${line.idPihakCawangan}')" onmouseover="this.style.cursor='pointer';">
                                        </p>
                                    </display:column>
                                    <display:column title="Hapus">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="removeCawanganPemohon('${line.idPihakCawangan}')" onmouseover="this.style.cursor='pointer';">
                                        </div>
                                    </display:column>
                                </c:if>

                                <c:if test="${!pemohon}">
                                    <display:column title="Kemaskini">
                                        <p align="center">
                                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                 onclick="editCawangan('${line.idPihakCawangan}')" onmouseover="this.style.cursor='pointer';">
                                        </p>
                                    </display:column>
                                    <display:column title="Hapus">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="removeCawangan('${line.idPihakCawangan}')" onmouseover="this.style.cursor='pointer';">
                                        </div>
                                    </display:column>
                                </c:if>

                            </display:table>

                            <%--Added for Maklumat Pengarah--%>
                            <%--<c:if test="${fn:length(actionBean.pengarahList) > 0}">--%>
                            <br>
                            Maklumat Ahli Lembaga Pengarah

                            <display:table name="${actionBean.pengarahList}" id="line2" class="tablecloth" >
                                <display:column title="Bil" sortable="true">${line2_rowNum}</display:column>
                                <display:column property="nama" title="Nama"/>
                                <display:column property="noPengenalan" title="Nombor Pengenalan"/>

                                <c:if test="${pemohon}">
                                    <display:column title="Kemaskini">
                                        <p align="center">
                                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                 onclick="editPengarahPemohon('${line2.idPengarah}')" onmouseover="this.style.cursor='pointer';">
                                        </p>
                                    </display:column>
                                    <display:column title="Hapus">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id='rem_${line2_rowNum}' onclick="removePengarahPemohon('${line2.idPengarah}')" onmouseover="this.style.cursor='pointer';">
                                        </div>
                                    </display:column>
                                </c:if>

                                <c:if test="${!pemohon}">
                                    <display:column title="Kemaskini">
                                        <p align="center">
                                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                 onclick="editPengarah('${line2.idPengarah}')" onmouseover="this.style.cursor='pointer';">
                                        </p>
                                    </display:column>
                                    <display:column title="Hapus">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id='rem_${line2_rowNum}' onclick="removePengarah('${line2.idPengarah}')" onmouseover="this.style.cursor='pointer';">
                                        </div>
                                    </display:column>
                                </c:if>

                            </display:table>
                            <%--</c:if>--%>
                            <%--Added Maklumat Pengarah--%>

                        </div>
                    </c:if>
                </c:if>

                <c:if test="${!actionBean.cariFlag}">
                    <p align="center">
                        <c:if test="${pemohon}">
                            <s:submit name="cariPihakPemohon" value="Cari" class="btn"/>
                        </c:if>
                        <c:if test="${!pemohon}">
                            <s:submit name="cariPihak" value="Cari" class="btn"/>
                        </c:if>
                        <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    </p>
                </c:if>

                <c:if test="${actionBean.cariFlag}">
                    <p align="center">
                        <c:if test="${pemohon}">
                            <s:button name="simpanPemohonPopup" id="simpan" value="Simpan" class="btn" onclick="savePemohon(this.name, this.form);"/>
                            <c:if test="${actionBean.pihak.jenisPengenalan.kod ne 'B' && actionBean.pihak.jenisPengenalan.kod ne 'L' && actionBean.pihak.jenisPengenalan.kod ne 'P' && actionBean.pihak.jenisPengenalan.kod ne 'T' && actionBean.pihak.jenisPengenalan.kod ne 'I'}">
                                <s:submit name="tambahCawanganPemohon" value="Tambah Cawangan" class="longbtn"/>
                                <s:submit name="tambahPengarahPemohon" value="Tambah Pengarah" class="longbtn"/>
                            </c:if>
                        </c:if>

                        <c:if test="${!pemohon}">
                            <s:button name="simpanPihakKepentinganPopup" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                            <c:if test="${actionBean.pihak.jenisPengenalan.kod ne 'B' && actionBean.pihak.jenisPengenalan.kod ne 'L' && actionBean.pihak.jenisPengenalan.kod ne 'P' && actionBean.pihak.jenisPengenalan.kod ne 'T' && actionBean.pihak.jenisPengenalan.kod ne 'I'}">
                                <s:submit name="tambahCawangan" value="Tambah Cawangan" class="longbtn" onclick="return validationPB();"/>
                                <s:submit name="tambahPengarah" value="Tambah Ahli Pengarah" class="longbtn" id="btnAhli" onclick="return validationPB();"/>
                            </c:if>
                        </c:if>
                        <s:submit name="cariSemula" value="Cari Semula" class="longbtn"/>
                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    </p>
                </c:if>
                <br>
            </fieldset>
        </c:if>

        <%--add new cawangan--%>

        <c:if test="${actionBean.tambahCawFlag}">
            <s:hidden name="pihakCawangan.negeri.kod"/>

            <fieldset class="aras1">
                <legend>
                    <c:if test="${pemohon}">
                        Kemasukan Cawangan Pemohon
                    </c:if>

                    <c:if test="${!pemohon}">
                        Kemasukan Cawangan Penerima
                    </c:if>
                </legend>
                <p>
                    <label for="nama"><font color="red">*</font>Nama :</label>
                    <s:text name="pihakCawangan.namaCawangan" id="namaCaw" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label for="alamat">Alamat Berdaftar :</label>
                    <s:text name="pihakCawangan.alamat1" id="alamatCaw1" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>

                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.alamat2" id="alamatCaw2" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>

                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.alamat3" id="alamatCaw3" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.alamat4" id="alamatCaw4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                <p>
                    <label>Poskod :</label>
                    <s:text name="pihakCawangan.poskod" size="40" id="poskodCaw" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                    <label for="Negeri">Negeri :</label>
                    <s:select name="pihakCawangan.negeri.kod" id="negeriCaw">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                    </s:select>

                </p>
                <p>
                    <label>&nbsp;</label>
                    <input type="checkbox" id="addCaw" name="addCaw" value="" onclick="copyAddCaw();"/>
                    <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                </p>
                <p>
                    <label for="alamat">Alamat Surat-Menyurat :</label>
                    <s:text name="pihakCawangan.suratAlamat1" id="suratAlamatCaw1" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.suratAlamat2" id="suratAlamatCaw2" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.suratAlamat3" id="suratAlamatCaw3" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.suratAlamat4" id="suratAlamatCaw4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>Poskod :</label>
                    <s:text name="pihakCawangan.suratPoskod" id="suratPoskodCaw" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                </p>

                <p>
                    <label for="Negeri">Negeri :</label>
                    <s:select name="pihakCawangan.suratNegeri.kod" id="suratNegeriCaw">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                    </s:select>
                </p>

                <p>
                    <label for="alamat">Nombor Telefon :</label>
                    <s:text name="pihakCawangan.noTelefon1" size="40" maxlength="12" onkeyup="validateNumber(this,this.value);"/>
                </p>

                <p>
                    <label>&nbsp;</label>

                    <c:if test="${pemohon}">
                        <s:submit name="simpanCawanganPemohon" value="Simpan" class="btn"/>
                        <s:submit name="backCawanganPemohon" value="Tutup" class="btn"/>
                    </c:if>

                    <c:if test="${!pemohon}">
                        <s:submit name="simpanCawangan" value="Simpan" class="btn"/>
                        <s:submit name="backCawangan" value="Tutup" class="btn"/>
                    </c:if>
                </p>
                <br>
            </fieldset>
        </c:if>

        <%--Add Lembaga Pengarah--%>

        <c:if test="${actionBean.tambahPengarahFlag}">
            <fieldset class="aras1">
                <legend>
                    <c:if test="${pemohon}">
                        Kemasukan Lembaga Pengarah Pemohon
                    </c:if>

                    <c:if test="${!pemohon}">
                        Kemasukan Lembaga Pengarah Penerima
                    </c:if>
                </legend>
                <s:hidden name="pihakPengarah.idPengarah"/>
                <p>
                    <label for="nama"><font color="red">*</font>Nama :</label>
                    <s:text name="pihakPengarah.nama" id="namaPengarah" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <c:if test="${actionBean.kodNegeri eq '05'}">
                    <p>
                        <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                        <s:select name="pihakPengarah.jenisPengenalan.kod" id="jenisPengenalan"  onchange="javaScript:changeHideSuku(this.options[selectedIndex].text);">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                </c:if>

                <c:if test="${actionBean.kodNegeri eq '04'}">
                    <p>
                        <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                        <s:select name="pihakPengarah.jenisPengenalan.kod" id="jenisPengenalan">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                </c:if>

                <p>
                    <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                    <s:text name="pihakPengarah.noPengenalan" id="kp" size="40" onkeyup="dodacheck(this.value);this.value=this.value.toUpperCase();"
                            onblur="doCheckUmur(this.value, this.id,'${jenis}')"/>&nbsp;<em style="font-style:normal">(cth : 800620125177)</em>
                </p>
                <p>
                    <label><font color="red">*</font>Kewarganegaraan :</label>
                    <s:select name="pihakPengarah.warganegara.kod" style="width:200px" id="kod_warga_pengarah">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p>
                    <label for="alamat">Alamat Surat-Menyurat :</label>
                    <s:text name="pihakPengarah.alamat1" id="alamat1" size="40" maxlength="40" />
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakPengarah.alamat2" id="alamat2" size="40" maxlength="40" />
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakPengarah.alamat3" id="alamat3" size="40" maxlength="40" />
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakPengarah.alamat4" id="alamat4" size="40" maxlength="40" />
                </p>
                <p>
                    <label>Poskod :</label>
                    <s:text name="pihakPengarah.poskod" id="poskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                    <label for="Negeri">Negeri :</label>
                    <s:select name="pihakPengarah.kodNegeri.kod" id="kodNegeri">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                    </s:select>
                </p>

                <p>
                    <label>&nbsp;</label>
                    <c:if test="${pemohon}">
                        <s:submit name="simpanPengarahPemohon" value="Simpan" class="btn"/>
                        <s:submit name="backCawanganPemohon" value="Tutup" class="btn"/>
                    </c:if>

                    <c:if test="${!pemohon}">
                        <s:submit name="simpanPengarah" value="Simpan" class="btn"/>
                        <s:submit name="backCawangan" value="Tutup" class="btn"/>
                    </c:if>
                </p>
                <br>
            </fieldset>
        </c:if>
    </s:form>
</div>