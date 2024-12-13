<%--
    Document   : kemasukan_waris
    Created on : Mar 17, 2010, 12:51:37 PM
    Author     : muhammad.amin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.2.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ageCalculator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $('.alphanumeric').alphanumeric();
        doBlockUI();
        
        var jenisKp = $('#jenisKp').val();
        if(jenisKp == "B"){
            var icNo = $('#kp').val();

            if(icNo.length == 12 || icNo.length == 14){
                var year = icNo.substring(0,2);

                if(year > 25 && year < 99){//fixme :
                    year = "19" + year;
                }else {
                    year = "20" + year;
                }

                if( $('#kp').val() != ''){
                    var age = calculateAge(icNo.substring(4,6), icNo.substring(2,4), year);
                    $('#umur').val(age);
                }
            }
        }
        $.unblockUI();
    });

    function save(event, f){
        if(doValidation()){
            doBlockUI();
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                $.unblockUI();
                self.close();
            },'html');
        }
    }

    function doValidation(){
        var noKp = $('#kp').val();
        var jenisKp = $('#jenisKp').val();
        var nama = $('#nama').val();
        var waris = $('#waris').val();
        var suku = $('#suku').val();
        var luak = $('#luak').val();
        var umur = $('#umur').val();  
        var alamat = $('#alamat1').val();
        var alamatSurat = $('#suratAlamat1').val();

        if(nama == ''){
            alert('Sila Masukkan Nama.');
            return false;
        }else if(jenisKp == ''){
            alert('Sila Masukkan Jenis Pengenalan.');
            return false;
        }else if(noKp == ''){
            alert('Sila Masukkan Nombor Pengenalan.');
            return false;
        } else if (waris == ''){
            alert('Sila Masukkan Jenis Waris.');
            return false;
        }
        else if (suku == ''){
            alert('Sila Masukkan Jenis Suku.');
            return false;
        }
        else if (luak == ''){
            alert('Sila Masukkan Jenis Luak.');
            return false;
        }
        else if (umur == ''){
            alert('Sila Masukkan Umur.');
            return false;
        }
        else if (alamat == ''){
            alert('Sila Masukkan Alamat.');
            return false;
        }
        else if (alamatSurat == ''){
            alert('Sila Masukkan Alamat Surat-Menyurat.');
            return false;
        }

        return true;
    }

    function selectName(val){
        var url = '${pageContext.request.contextPath}/consent/kemasukan_waris?selectName&idPihak='+val;
        $.get(url,
        function(data){
            $('#caw').html(data);
        },'html');
    }

    function changeIcNumber(icNo){

        var jenisKp = $('#jenisKp').val();
        
        if(jenisKp == 'B'){
            if(icNo.length == 12 || icNo.length == 14){
                var year = icNo.substring(0,2);

                if(year > 25 && year < 99){//fixme :
                    year = "19" + year;
                }else {
                    year = "20" + year;
                }

                if( $('#kp').val() != ''){
                    var age = calculateAge(icNo.substring(4,6), icNo.substring(2,4), year);
                    $('#umur').val(age);
                }
            }
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

    function doBlockUI () {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
    }

</script>
<style type="text/css">
    input.error { background-color: yellow; }
</style>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none" alt=""/>
<div class="subtitle" id="caw">
    <s:form beanclass="etanah.view.stripes.consent.KemasukanWarisActionBean" >

        <s:hidden name="pihak.nama"/>
        <s:hidden name="pihak.idPihak"/>
        <s:hidden name="pihak.jenisPengenalan.kod"/>
        <s:hidden name="pihak.jenisPengenalan.nama"/>
        <s:hidden name="pihak.noPengenalan"/>
        <s:hidden name="permohonanPihak.jenis.kod"/>

        <s:errors/>
        <s:messages/>

        <fieldset class="aras1">
            <legend>Kemasukan Maklumat Waris</legend>

            <c:if test="${!actionBean.cariFlag}">
                <p>
                    <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                    <s:select name="pihak.jenisPengenalan.kod" id="jenisPengenalan">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiJenisPengenalan}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p>
                    <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                    <%--<s:text name="pihak.noPengenalan" id="kp" size="40" onkeyup="dodacheck(this.value);this.value=this.value.toUpperCase();"/>--%>
                    <s:text name="pihak.noPengenalan" id="kp" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    &nbsp;<em style="font-style:normal">(cth : 800620125177)</em>
                </p>
                <p>
                    <label>&nbsp;</label>
                    atau
                </p>
                <p>
                    <label for="nama"><font color="red">*</font>Nama :</label>
                    <s:text name="pihak.nama" size="40" id="nama" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <c:if test="${fn:length(actionBean.pihakByNameList) > 0}">
                    <div align="center">
                        <display:table class="tablecloth" name="${actionBean.pihakByNameList}" cellpadding="0" cellspacing="0" id="line" pagesize="10"
                                       requestURI="${pageContext.request.contextPath}/consent/kemasukan_waris">
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column  title="Nama" class="nama">
                                <c:if test="${!pemohon}">
                                    <a href="#" onclick="selectName('${line.idPihak}');return false;">${line.nama}</a>
                                </c:if>
                                <c:if test="${pemohon}">
                                    <a href="#" onclick="selectNamePemohon('${line.idPihak}');return false;">${line.nama}</a>
                                </c:if>
                            </display:column>
                            <display:column property="jenisPengenalan.nama" title="Jenis Pengenalan" />
                            <display:column property="noPengenalan" title="Nombor Pengenalan" />
                            <display:column title="Alamat Berdaftar" >
                                <font style="text-transform:uppercase;">
                                    ${line.alamat1} 
                                    ${line.alamat2}
                                    ${line.alamat3}
                                    ${line.alamat4}
                                    ${line.poskod}
                                    ${line.negeri.nama}
                                </font>
                            </display:column>
                        </display:table>
                    </div>
                </c:if>
            </c:if>

            <c:if test="${actionBean.cariFlag}">

                <p>
                    <label for="nama"><font color="red">*</font>Nama :</label>
                    <s:text name="pihak.nama" id="nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>

                <p>
                    <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                    <s:select name="pihak.jenisPengenalan.kod" id="jenisKp" onchange="javaScript:changeHideSuku(this.options[selectedIndex].text)">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p>
                    <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                    <s:text name="pihak.noPengenalan" id="kp" size="40" onchange="changeIcNumber(this.value);"/>
                </p>
                <p>
                    <label><font color="red">*</font>Jenis Waris :</label>
                    <s:select name="permohonanPihak.kaitan" id="waris">
                        <s:option value="">SILA PILIH</s:option>
                        <s:option>ANAK LELAKI</s:option>
                        <s:option>ANAK PEREMPUAN</s:option>
                        <s:option>BUAPAK</s:option>
                        <s:option>CUCU PEREMPUAN</s:option>
                        <s:option>IBU</s:option>
                        <s:option>NENEK</s:option>
                        <s:option>SAUDARA KADIM</s:option>
                    </s:select>
                </p>
                <p>
                    <label><font color="red">*</font>Bangsa :</label>
                    <s:select name="pihak.bangsa.kod" style="width:200px">
                        <s:option value="MEL">MELAYU</s:option>
                    </s:select>
                </p>
                <p>
                    <label><font color="red">*</font>Jenis Suku :</label>
                    <s:select name="pihak.suku.kod" style="width:200px" id="suku">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodSuku}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p>
                    <label><font color="red">*</font>Luak :</label>
                    <s:select name="pihak.tempatSuku" style="width:250px" id="luak">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodLuak}" label="nama" value="nama"/>
                    </s:select>
                </p>
                <p>
                    <label><font color="red">*</font>Umur :</label>
                    <s:text name="permohonanPihak.umur" size="10" maxlength="3" id="umur" onkeyup="validateNumber(this,this.value);"/>
                </p>

                <p>
                    <label for="alamat"><font color="red">*</font>Alamat Berdaftar :</label>
                    <s:text name="pihak.alamat1" size="40" id="alamat1" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>

                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihak.alamat2" size="40" id="alamat2" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>

                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihak.alamat3" size="40" id="alamat3" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> Bandar :</label>
                    <s:text name="pihak.alamat4" size="40" id="alamat4" onkeyup="this.value=this.value.toUpperCase();"/>
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
                    <label>&nbsp;</label>
                    <input type="checkbox" id="add1" name="add1" value="" onclick="copyAdd();"/>
                    <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                </p>
                <p>
                    <label for="alamat"><font color="red">*</font>Alamat Surat-Menyurat :</label>
                    <s:text name="pihak.suratAlamat1" id="suratAlamat1" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihak.suratAlamat2" id="suratAlamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihak.suratAlamat3" id="suratAlamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> Bandar :</label>
                    <s:text name="pihak.suratAlamat4" id="suratAlamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
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
                <p>
                    <label>Nombor Telefon :</label>
                    <s:text name="pihak.noTelefon1" onkeyup="validateNumber(this,this.value);"/>
                </p>
            </c:if>

            <c:if test="${!actionBean.cariFlag}">      
                <div align="center">
                    <s:submit name="cariPihak" value="Cari" class="btn"/>
                    <s:submit name="cariSemula" value="Isi Semula" class="btn"/>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </div>

            </c:if>

            <c:if test="${actionBean.cariFlag}">
                <div align="center">
                    <s:button name="simpanWaris" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                    <s:submit name="cariSemula" value="Cari Semula" class="btn"/>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </div>
            </c:if>
            <br>
        </fieldset>

    </s:form>
</div>