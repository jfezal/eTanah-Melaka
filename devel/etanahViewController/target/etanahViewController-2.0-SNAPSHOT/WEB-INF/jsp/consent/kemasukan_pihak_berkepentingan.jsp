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
<script type="text/javascript">
    $(document).ready( function(){
        $('.alphanumeric').alphanumeric();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

        var v = '${actionBean.pihak.jenisPengenalan.kod}';
        if(v == "S"){
            $('#suku').hide();
        }

        else{
            $('#suku').show();
        }
       
    });

    function changeHideSuku(value){
        if(value == "S"){
            $('#suku').hide();
        }else{
            $('#suku').show();
        }
    }


    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            if(data == '1')
            {
                alert('Sila masukan data pada label yang bertanda *. ');
            }else{$('#page_div',opener.document).html(data);

                self.close();}

        },'html');
    }

    function savePemohon(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
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
            var url = '${pageContext.request.contextPath}/consent/pihak_turun_milik?deleteCawangan&idCawangan='+val;
            $.get(url,
            function(data){               
                $('#caw').html(data);
            },'html');
        }
    }

    function removeCawanganPemohon(val){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/consent/pihak_turun_milik?deleteCawanganPemohon&idCawangan='+val;
            $.get(url,
            function(data){
                $('#caw').html(data);
            },'html');
        }
    }

    function editCawangan(val){
        var url = '${pageContext.request.contextPath}/consent/pihak_turun_milik?editCawangan&idCawangan='+val;
        $.get(url,
        function(data){
            $('#caw').html(data);
        },'html');
    }
    
    function editCawanganPemohon(val){
        var url = '${pageContext.request.contextPath}/consent/pihak_turun_milik?editCawanganPemohon&idCawangan='+val;
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
        }
    }

    function doCheckUmur(v,id){
        var va = $('#jenisPengenalan').val();
        if(va == 'B'){
            doValidateAge(v, id, 'jenisPengenalan');
        }
    }

</script>
<style type="text/css">
    input.error { background-color: yellow; }
</style>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<div class="subtitle" id="caw">
    <s:form beanclass="etanah.view.stripes.consent.PihakTurunMilikActionBean" >

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
        <c:if test="${!actionBean.tambahCawFlag}">

            <fieldset class="aras1">
                <c:if test="${pemohon}">
                    <legend>Kemasukan Maklumat Pemohon</legend>
                </c:if>

                <c:if test="${!pemohon}">
                    <c:choose>
                        <c:when test="${jenis eq 'PA'}">
                            <legend>Kemasukan Maklumat Pemegang Amanah</legend>
                        </c:when>
                        <c:when test="${jenis eq 'WAR'}">
                            <legend>Kemasukan Maklumat Pemberi Amanah</legend>
                        </c:when>
                        <c:otherwise>
                            <legend>Kemasukan Maklumat Penerima</legend>
                        </c:otherwise>
                    </c:choose>                    
                </c:if>
                <br/>              

                <c:if test="${!actionBean.cariFlag}">

                    <p>
                        <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                        <s:select name="pihak.jenisPengenalan.kod" id="jenisPengenalan"  onchange="javaScript:changeHideSuku(this.options[selectedIndex].text)">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <p>
                        <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                        <s:text name="pihak.noPengenalan" id="kp" size="40" onkeyup="dodacheck(this.value);"
                                onblur="doUpperCase(this.id);doCheckUmur(this.value, this.id);"/>
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
                        <c:if test="${actionBean.pihak.suku.kod ne null}">
                            <p id="suku">
                                <label>Jenis Suku :</label>
                                ${actionBean.pihak.suku.nama}&nbsp;
                            </p>
                        </c:if>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                            <p>
                                <label>Tarikh Lahir :</label>
                                <s:text name="tarikhLahir" size="40" id="datepicker" class="datepicker"/>
                            </p>
                            <p>
                                <label>Umur :</label>
                                <s:text name="permohonanPihak.umur" size="10" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Tempat Lahir :</label>
                                <s:text name="pihak.tempatLahir" size="40"/>
                            </p>
                            <p>
                                <label>Kewarganegaraan :</label>
                                ${actionBean.pihak.wargaNegara.nama}&nbsp;
                            </p>
                            <p>
                                <label>Status Perkahwinan :</label>
                                <s:text name="permohonanPihak.statusKahwin" size="40" maxlength="10"/>
                            </p>
                            <p>
                                <label>Pekerjaan :</label>
                                <s:text name="permohonanPihak.pekerjaan" size="40"/>
                            </p>
                            <p>
                                <label>Pendapatan Bulanan (RM) :</label>
                                <s:text name="permohonanPihak.pendapatan" size="40" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Tanggungan :</label>
                                <s:text name="permohonanPihak.tangungan" size="40" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Persaudaraan :</label>
                                <s:text name="permohonanPihak.kaitan" size="40"/>
                            </p>
                        </c:if>
                        <c:if test="${!pemohon}">
                            <p>
                                <label for="nama"><font color="red">*</font>Jenis Pihak Berkepentingan :</label>
                                <s:select name="permohonanPihak.jenis.kod" style="width:400px">
                                    <s:option value="" >Sila Pilih</s:option>
                                    <c:if test="${actionBean.p.kodUrusan.jabatanNama ne 'Consent'}">
                                        <s:options-collection collection="${list.senaraiKodJenisPihakBerkepentingan}" label="nama" value="kod"/>
                                    </c:if>
                                    <c:if test="${actionBean.p.kodUrusan.jabatanNama eq 'Consent'}">
                                        <s:options-collection collection="${actionBean.senaraiKodPenerima}" label="nama" value="kod" />
                                    </c:if>
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
                            <s:text name="pihak.suratAlamat1" size="100"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat2" size="100"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat3" size="100"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat4" size="100"/>
                        </p>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="pihak.suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select name="pihak.suratNegeri.kod">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                    </c:if>
                    <%--tiada data--%>
                    <c:if test="${actionBean.tiadaDataFlag}">
                        <p>
                            <label for="nama"><font color="red">*</font>Nama :</label>
                            <s:text name="pihak.nama" id="nama" size="40"/>
                        </p>

                        <p>
                            <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                            <s:select name="pihak.jenisPengenalan.kod" onchange="javaScript:changeHideSuku(this.options[selectedIndex].text)">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <p>
                            <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                            <s:text name="pihak.noPengenalan" id="kp" size="40"/>
                        </p>

                        <p>
                            <label>Bangsa / Jenis Syarikat :</label>
                            <s:select name="pihak.bangsa.kod" style="width:400px">
                                <s:option>Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraiKodBangsa}" label="nama" value="kod" />
                            </s:select>
                        </p>
                        <p id="suku">
                            <label for="Suku">Jenis Suku :</label>
                            <s:select name="pihak.suku.kod" style="width:200px">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodSuku}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                            <p>
                                <label>Tarikh Lahir :</label>
                                <s:text name="tarikhLahir" size="40" id="datepicker" class="datepicker"/>
                            </p>
                            <p>
                                <label>Umur :</label>
                                <s:text name="permohonanPihak.umur" size="10" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Tempat Lahir :</label>
                                <s:text name="pihak.tempatLahir" size="40"/>
                            </p>
                            <p>
                                <label>Kewarganegaraan :</label>
                                <s:select name="pihak.wargaNegara.kod" style="width:200px">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                            <p>
                                <label>Status Perkahwinan :</label>
                                <s:text name="permohonanPihak.statusKahwin" size="40" maxlength="10"/>
                            </p>
                            <p>
                                <label>Pekerjaan :</label>
                                <s:text name="permohonanPihak.pekerjaan" size="40"/>
                            </p>
                            <p>
                                <label>Pendapatan Bulanan (RM) :</label>
                                <s:text name="permohonanPihak.pendapatan" size="40" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Tanggungan :</label>
                                <s:text name="permohonanPihak.tangungan" size="40" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Persaudaraan :</label>
                                <s:text name="permohonanPihak.kaitan" size="40"/>
                            </p>
                        </c:if>
                        <c:if test="${!pemohon}">
                            <p>
                                <label for="nama"><font color="red">*</font>Jenis Pihak Berkepentingan :</label>
                                <s:select name="permohonanPihak.jenis.kod" style="width:400px">
                                    <s:option value="" >Sila Pilih</s:option>
                                    <c:if test="${actionBean.p.kodUrusan.jabatanNama ne 'Consent'}">
                                        <s:options-collection collection="${list.senaraiKodJenisPihakBerkepentingan}" label="nama" value="kod"/>
                                    </c:if>
                                    <c:if test="${actionBean.p.kodUrusan.jabatanNama eq 'Consent'}">
                                        <s:options-collection collection="${actionBean.senaraiKodPenerima}" label="nama" value="kod" />
                                    </c:if>
                                </s:select>
                            </p>
                        </c:if>
                        <p>
                            <label for="alamat">Alamat Berdaftar :</label>
                            <s:text name="pihak.alamat1" size="100" id="alamat1"/>
                        </p>

                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.alamat2" size="100" id="alamat2"/>
                        </p>

                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.alamat3" size="100" id="alamat3"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.alamat4" size="100" id="alamat4"/>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="pihak.poskod" size="100" maxlength="5" id="poskod" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select name="pihak.negeri.kod" id="negeri">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <p>
                            <label>&nbsp;</label>
                            <input type="checkbox" id="add1" name="add1" value="" onclick="copyAdd();"/>
                            <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>                        
                        </p>
                        <p>
                            <label for="alamat">Alamat Surat-Menyurat :</label>
                            <s:text name="pihak.suratAlamat1" id="suratAlamat1" size="100"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat2" id="suratAlamat2" size="100"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat3" id="suratAlamat3" size="100"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat4" id="suratAlamat4" size="100"/>
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
                        </div>
                    </c:if>
                </c:if>

                <c:if test="${!actionBean.cariFlag}">
                    <p>
                        <label>&nbsp;</label>
                        <c:if test="${pemohon}">
                            <s:submit name="cariPihakPemohon" value="Cari" class="btn"/>
                        </c:if>
                        <c:if test="${!pemohon}">
                            <s:submit name="cariPihak" value="Cari" class="btn"/>
                        </c:if>

                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    </p>
                </c:if>

                <c:if test="${actionBean.cariFlag}">
                    <p>
                        <label>&nbsp;</label>

                        <c:if test="${pemohon}">
                            <s:button name="simpanPemohonPopup" id="simpan" value="Simpan" class="btn" onclick="savePemohon(this.name, this.form);"/>
                            <c:if test="${actionBean.pihak.jenisPengenalan.kod ne 'B' && actionBean.pihak.jenisPengenalan.kod ne 'L' && actionBean.pihak.jenisPengenalan.kod ne 'P' && actionBean.pihak.jenisPengenalan.kod ne 'T' && actionBean.pihak.jenisPengenalan.kod ne 'I'}">
                                <s:submit name="tambahCawanganPemohon" value="Tambah Cawangan" class="btn"/>
                            </c:if>
                        </c:if>

                        <c:if test="${!pemohon}">
                            <s:button name="simpanPihakKepentinganPopup" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                            <c:if test="${actionBean.pihak.jenisPengenalan.kod ne 'B' && actionBean.pihak.jenisPengenalan.kod ne 'L' && actionBean.pihak.jenisPengenalan.kod ne 'P' && actionBean.pihak.jenisPengenalan.kod ne 'T' && actionBean.pihak.jenisPengenalan.kod ne 'I'}">
                                <s:submit name="tambahCawangan" value="Tambah Cawangan" class="btn"/>
                            </c:if>
                        </c:if>

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

                <c:if test="${pemohon}">
                    <legend>Kemasukan Cawangan Pemohon</legend>
                </c:if>

                <c:if test="${!pemohon}">
                    <legend>Kemasukan Cawangan Penerima</legend>
                </c:if>
                <p>
                    <label for="nama"><font color="red">*</font>Nama :</label>
                    <s:text name="pihakCawangan.namaCawangan" id="nama" size="40"/>
                </p>
                <p>
                    <label for="alamat">Alamat Berdaftar :</label>
                    <s:text name="pihakCawangan.alamat1" id="alamatCaw1" size="40"/>
                </p>

                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.alamat2" id="alamatCaw2" size="40"/>
                </p>

                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.alamat3" id="alamatCaw3" size="40"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.alamat4" id="alamatCaw4" size="40"/>
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
                    <s:text name="pihakCawangan.suratAlamat1" id="suratAlamatCaw1" size="40"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.suratAlamat2" id="suratAlamatCaw2" size="40"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.suratAlamat3" id="suratAlamatCaw3" size="40"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.suratAlamat4" id="suratAlamatCaw4" size="40"/>
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
    </s:form>
</div>