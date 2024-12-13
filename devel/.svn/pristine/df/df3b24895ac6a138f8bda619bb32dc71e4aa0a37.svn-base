<%--
    Document   : edit_pemohon
    Created on : Nov 10, 2009, 4:23:38 PM
    Author     : muhammad.amin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<%--<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>--%>

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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    $(document).ready( function(){
        $('.alphanumeric').alphanumeric();

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

        return true;
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

<s:useActionBean beanclass="etanah.view.ListUtil" id="list" />
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none" alt=""/>
<s:form beanclass="etanah.view.stripes.consent.KemasukanWarisActionBean">

    <s:hidden name="pihak.idPihak"/>
    <s:hidden name="permohonanPihak.jenis.kod"/>
    <s:hidden name="permohonanPihak.idPermohonanPihak"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Waris</legend>

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
                <label>Bangsa :</label>
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
                <label for="alamat">Alamat Surat-Menyurat:</label>
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
            <p>
                <label>&nbsp;</label>
                <s:button name="simpanEditWaris" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                <label>&nbsp;</label>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>
    </div>

</s:form>