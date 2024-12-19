
<%--
    Document   : tambah_pemohon_permit
    Created on : Apr 26, 2010, 9:42:49 AM
    Author     : sitifariza.hanim
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

        var v = '${actionBean.pihak.jenisPengenalan.kod}';
        if(v == "S"){
            $('#suku').hide();
        }
        else{
            $('#suku').show();
        }
    });

    $(document).ready( function(){
        $('.alphanumeric').alphanumeric();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $('#jenisPengenalan').change(function(){
            dodacheck($("#kp").val());
        });
        var v = '${actionBean.pihak.jenisPengenalan.kod}';

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
            var age = calculateAge(value.substring(0,2), value.substring(3,5), 20 + value.substring(8,10));
            $('#umur').val(age);
        }

    function changeHideSuku(value){
        if(value == "S"){
            $('#suku').hide();
        }else{
            $('#suku').show();
        }
    }

    function savePemohon(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.opener.refreshPage();
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

    function removeCawanganPemohon(val){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/maklumat_pemohon?deleteCawanganPemohon&idCawangan='+val;
            $.get(url,
            function(data){
                $('#caw').html(data);
            },'html');
        }
    }

    function editCawanganPemohon(val){
        var url = '${pageContext.request.contextPath}/pelupusan/maklumat_pemohon?editCawanganPemohon&idCawangan='+val;
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

       function validation() {
	<%--if($("#alamat1").val() == ""){
		alert('Sila masukkan " Alamat " terlebih dahulu.');
  		$("#alamat1").focus();
		return false;
	}--%>
        if(document.tambah.nama.value == ""){
            alert('Sila isikan nama')
            document.edit.nama.focus();
            return false ;
        }
         if(document.tambah.bangsa.value == "Sila Pilih"){
		alert('Sila pilih " Bangsa " terlebih dahulu.');
  		document.edit.bangsa.value.focus();
		return false;
	}
         if(document.tambah.kwn.value == "Sila Pilih"){
		alert('Sila pilih " Kewarganegaraan " terlebih dahulu.');
  		<%--document.edit.suratNegeri.value.focus();--%>
		return false;
	}
         if(document.tambah.alamat1.value == "" || document.tambah.alamat2.value == "" || document.tambah.alamat3.value == ""  || document.tambah.alamat4.value == "" ){
		alert('Sila pilih " Alamat " terlebih dahulu.');

		return false;
	}
        if(document.tambah.negeri.value == "Sila Pilih"){
		alert('Sila pilih " Negeri " terlebih dahulu.');
  		document.edit.negeri.value.focus();
		return false;
	}
         if(document.tambah.poskod.value == ""){
		alert('Sila masukkan " Poskod " terlebih dahulu.');

		return false;
	}
        if(document.tambah.suratAlamat1.value == "" || document.tambah.suratAlamat2.value == "" || document.tambah.suratAlamat3.value == ""  || document.tambah.suratAlamat4.value == "" ){
		alert('Sila pilih " Alamat Surat Menyurat " terlebih dahulu.');
  		
		return false;
	}
        if(document.tambah.suratPoskod.value == ""){
		alert('Sila masukkan " Poskod Surat Menyurat " terlebih dahulu.');
  		
		return false;
	}
        if(document.tambah.suratNegeri.value == "Sila Pilih"){
		alert('Sila pilih " Negeri Surat Menyurat " terlebih dahulu.');
  		document.edit.suratNegeri.value.focus();
		return false;
	}
         
       
        return true;
    }

     function validation2() {
	<%--if($("#alamat1").val() == ""){
		alert('Sila masukkan " Alamat " terlebih dahulu.');
  		$("#alamat1").focus();
		return false;
	}--%>
        if(document.tambah.nama.value == ""){
            alert('Sila isikan nama')
            document.edit.nama.focus();
            return false ;
        }
         if(document.tambah.bangsa.value == "Sila Pilih"){
		alert('Sila pilih " Jenis Syarikat " terlebih dahulu.');
  		document.edit.bangsa.value.focus();
		return false;
	}
         if(document.tambah.alamat1.value == "" || document.tambah.alamat2.value == "" || document.tambah.alamat3.value == ""  || document.tambah.alamat4.value == "" ){
		alert('Sila pilih " Alamat " terlebih dahulu.');

		return false;
	}
        if(document.tambah.negeri.value == "Sila Pilih"){
		alert('Sila pilih " Negeri " terlebih dahulu.');
  		document.edit.negeri.value.focus();
		return false;
	}
         if(document.tambah.poskod.value == ""){
		alert('Sila masukkan " Poskod " terlebih dahulu.');

		return false;
	}

        if(document.tambah.suratAlamat1.value == "" || document.tambah.suratAlamat2.value == "" || document.tambah.suratAlamat3.value == ""  || document.tambah.suratAlamat4.value == "" ){
		alert('Sila pilih " Alamat " terlebih dahulu.');

		return false;
	}
        if(document.tambah.suratPoskod.value == ""){
		alert('Sila masukkan " Poskod " terlebih dahulu.');

		return false;
	}
        if(document.tambah.suratNegeri.value == "Sila Pilih"){
		alert('Sila pilih " Negeri " terlebih dahulu.');
  		document.edit.suratNegeri.value.focus();
		return false;
	}


        return true;
    }

</script>
<style type="text/css">
    input.error { background-color: yellow; }
</style>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<div class="subtitle" id="caw">
    <s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPermohonPermitActionBean" name="tambah">

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
                <legend>Kemasukan Maklumat Pemohon  </legend>
                <br/>
                <c:if test="${!actionBean.cariFlag}">
                    <p>
                        <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                        <s:select name="pihak.jenisPengenalan.kod" id="jenisPengenalan"  onchange="javaScript:changeHideSuku(this.options[selectedIndex].text)">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiJenisPengenalanPelupusan}" label="nama" value="kod"/>
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
                            <label for="nama">Nama Syarikat/Nama :</label>
                            <s:text name="pihak.nama" id="nama"/>&nbsp;
                        </p>
                        <p>
                            <label for="Jenis Pengenalan">Jenis Pengenalan :</label>
                            ${actionBean.pihak.jenisPengenalan.nama}&nbsp;
                        </p>
                        <p>
                            <label for="No Pengenalan">Nombor Pengenalan :</label>
                            ${actionBean.pihak.noPengenalan}&nbsp;
                        </p>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                             <p>
                            <label><font color="red">*</font>Bangsa :</label>
                            <s:select name="pihak.bangsa.kod" style="width:200px" id="bangsa">
                                <s:option>Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodBangsaPerseorangan}" label="nama" value="kod" />
                            </s:select>
                        </p>
                        </c:if>
                       
                         <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'S' || actionBean.pihak.jenisPengenalan.kod eq 'U'}">
                        <p>
                            <label><font color="red">*</font>Jenis Syarikat :</label>
                            <s:select name="pihak.bangsa.kod" style="width:300px" id="bangsa">
                                <s:option>Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiSyarikat}" label="nama" value="kod" />
                            </s:select>
                        </p>
                        </c:if>
                       <%-- <c:if test="${actionBean.pihak.suku.kod ne null}">
                            <p id="suku">
                                <label>Jenis Suku :</label>
                                ${actionBean.pihak.suku.nama}&nbsp;
                            </p>
                        </c:if>--%>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                           
                            <p>
                                <label>Tarikh Lahir :</label>
                                <s:text name="tarikhLahir" size="40" id="datepicker" class="datepicker"/>
                            </p>
                            <p>
                                <label>Umur :</label>
                                <s:text name="pemohon.umur" id="umur" size="10" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                            </p><%--
                            <p>
                                <label>Tempat Lahir :</label>
                                <s:text name="pihak.tempatLahir" size="40"/>
                            </p>--%>
                            <p>
                                <label>Kewarganegaraan :</label>
                                   <s:select name="pihak.wargaNegara.kod" id="kwn" style="width:200px">
                                    <s:option>Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                           <%-- <p>
                                <label>Status Perkahwinan :</label>
                                <s:text name="permohonanPihak.statusKahwin" size="40" maxlength="10"/>
                            </p>--%>
                            <p>
                                <label>Pekerjaan :</label>
                                <s:text name="pemohon.pekerjaan" size="40" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label>Telefon :</label>
                                <s:text name="pemohon.telefon" size="40" maxlength="12" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Pendapatan Bulanan (RM) :</label>
                                <s:text name="pemohon.pendapatan" size="40" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Tanggungan :</label>
                                <s:text name="pemohon.tangungan" size="40" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                            </p><%--
                            <p>
                                <label>Persaudaraan :</label>
                                <s:text name="permohonanPihak.kaitan" size="40"/>
                            </p>--%>
                        </c:if>
                         <p>
                            <label for="alamat"><font color="red">*</font>Alamat Berdaftar :</label>
                            <s:text name="pihak.alamat1" size="40" id="alamat1" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>

                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.alamat2" size="40" id="alamat2" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>

                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.alamat3" size="40" id="alamat3" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.alamat4" size="40" id="alamat4" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="pihak.poskod" size="40" maxlength="5" id="poskod" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select name="pihak.negeri.kod" id="negeri">
                                <s:option>Sila Pilih</s:option>
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
                            <s:text name="pihak.suratAlamat1" id="suratAlamat1" size="40" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat2" id="suratAlamat2" size="40" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat3" id="suratAlamat3" size="40" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat4" id="suratAlamat4" size="40" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="pihak.suratPoskod" id="suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select name="pihak.suratNegeri.kod" id="suratNegeri">
                                <s:option>Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                    </c:if>
                    <%--tiada data (permit)--%>
                    <c:if test="${actionBean.tiadaDataFlag}">
                          <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                        <p>
                            <label for="nama"><font color="red">*</font>Nama :</label>
                            <s:text name="pihak.nama" id="nama" size="50" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                          </c:if>
                          <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'S'}">
                        <p>
                            <label for="nama"><font color="red">*</font>Nama Syarikat :</label>
                            <s:text name="pihak.nama" id="nama" size="50" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                          </c:if>
                        <p>
                            <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                            <s:select name="pihak.jenisPengenalan.kod" onchange="javaScript:changeHideSuku(this.options[selectedIndex].text)">
                                <s:option value="0">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiJenisPengenalanPelupusan}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <p>
                            <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                            <s:text name="pihak.noPengenalan" id="kp" size="40"/>
                        </p>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                             <p>
                            <label><font color="red">*</font>Bangsa :</label>
                            <s:select name="pihak.bangsa.kod" style="width:200px" id="bangsa">
                                <s:option>Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodBangsaPerseorangan}" label="nama" value="kod" />
                            </s:select>
                        </p>
                        </c:if>
                            <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'S' || actionBean.pihak.jenisPengenalan.kod eq 'U'}">
                        <p>
                            <label><font color="red">*</font>Jenis Syarikat :</label>
                            <s:select name="pihak.bangsa.kod" style="width:300px" id="bangsa">
                                <s:option>Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiSyarikat}" label="nama" value="kod" />
                            </s:select>
                        </p>
                            </c:if>
                       <%-- <p id="suku">
                            <label for="Suku">Jenis Suku :</label>
                            <s:select name="pihak.suku.kod" style="width:200px">
                                <s:option value="0">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodSuku}" label="nama" value="kod"/>
                            </s:select>
                        </p>--%>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                            <p>
                                <label>Tarikh Lahir :</label>
                                <s:text name="tarikhLahir" size="40" id="datepicker" class="datepicker"/>
                            </p>
                            <p>
                                <label>Umur :</label>
                                <s:text name="pemohon.umur" id="umur" size="10" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                            </p><%--
                            <p>
                                <label>Tempat Lahir :</label>
                                <s:text name="pihak.tempatLahir" size="40"/>
                            </p>--%>
                            <p>
                                <label><font color="red">*</font>Kewarganegaraan :</label>
                                <s:select name="pihak.wargaNegara.kod" id="kwn" style="width:200px">
                                    <s:option>Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                           <%-- <p>
                                <label>Status Perkahwinan :</label>
                                <s:text name="permohonanPihak.statusKahwin" size="40" maxlength="10"/>
                            </p>--%>
                            <p>
                                <label>Pekerjaan :</label>
                                <s:text name="pemohon.pekerjaan" size="40" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label>Telefon :</label>
                                <s:text name="pemohon.telefon" size="40" maxlength="12" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Pendapatan Bulanan (RM) :</label>
                                <s:text name="pemohon.pendapatan" size="40" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Tanggungan :</label>
                                <s:text name="pemohon.tangungan" size="40" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                            </p><%--
                            <p>
                                <label>Persaudaraan :</label>
                                <s:text name="permohonanPihak.kaitan" size="40"/>
                            </p>--%>
                        </c:if>
                        <p>
                            <label for="alamat"><font color="red">*</font>Alamat Berdaftar :</label>
                            <s:text name="pihak.alamat1" size="40" id="alamat1" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>

                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.alamat2" size="40" id="alamat2" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>

                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.alamat3" size="40" id="alamat3" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.alamat4" size="40" id="alamat4" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="pihak.poskod" size="40" maxlength="5" id="poskod" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select name="pihak.negeri.kod" id="negeri">
                                <s:option>Sila Pilih</s:option>
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
                            <s:text name="pihak.suratAlamat1" id="suratAlamat1" size="40" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat2" id="suratAlamat2" size="40" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat3" id="suratAlamat3" size="40" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat4" id="suratAlamat4" size="40" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="pihak.suratPoskod" id="suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select name="pihak.suratNegeri.kod" id="suratNegeri">
                                <s:option>Sila Pilih</s:option>
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



                            </display:table>
                        </div>
                    </c:if>
                </c:if>

                <c:if test="${!actionBean.cariFlag}">
                    <p>
                        <label>&nbsp;</label>
                        <s:submit name="cariPihakPemohon" value="Cari" class="btn"/>
                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    </p>
                </c:if>

                <c:if test="${actionBean.cariFlag}">
                    <p>
                        <label>&nbsp;</label>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                        <s:button name="simpanPemohonPopup" id="simpan" value="Simpan" class="btn" onclick="if(validation())savePemohon(this.name, this.form);"/>
                        </c:if>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'S' || actionBean.pihak.jenisPengenalan.kod eq 'U'}">
                        <s:button name="simpanPemohonPopup" id="simpan" value="Simpan" class="btn" onclick="if(validation2())savePemohon(this.name, this.form);"/>
                        </c:if>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod ne 'B' && actionBean.pihak.jenisPengenalan.kod ne 'L' && actionBean.pihak.jenisPengenalan.kod ne 'P' && actionBean.pihak.jenisPengenalan.kod ne 'T' && actionBean.pihak.jenisPengenalan.kod ne 'I'}">
                            <s:submit name="tambahCawanganPemohon" value="Tambah Cawangan" class="btn"/>
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
                <legend>Kemasukan Cawangan Pemohon</legend>
                <p>
                    <label for="nama"><font color="red">*</font>Nama  :</label>
                    <s:text name="pihakCawangan.namaCawangan" id="nama" size="40" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label for="alamat">Alamat Berdaftar :</label>
                    <s:text name="pihakCawangan.alamat1" id="alamatCaw1" size="40" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.alamat2" id="alamatCaw2" size="40" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.alamat3" id="alamatCaw3" size="40" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.alamat4" id="alamatCaw4" size="40" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                <p>
                    <label>Poskod :</label>
                    <s:text name="pihakCawangan.poskod" size="40" id="poskodCaw" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                    <label for="Negeri">Negeri :</label>
                    <s:select name="pihakCawangan.negeri.kod" id="negeriCaw">
                        <s:option value="0">Sila Pilih</s:option>
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
                    <s:text name="pihakCawangan.suratAlamat1" id="suratAlamatCaw1" size="40" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.suratAlamat2" id="suratAlamatCaw2" size="40" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.suratAlamat3" id="suratAlamatCaw3" size="40" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.suratAlamat4" id="suratAlamatCaw4" size="40" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>Poskod :</label>
                    <s:text name="pihakCawangan.suratPoskod" id="suratPoskodCaw" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                    <label for="Negeri">Negeri :</label>
                    <s:select name="pihakCawangan.suratNegeri.kod" id="suratNegeriCaw">
                        <s:option value="0">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p>
                    <label for="alamat">Nombor Telefon :</label>
                    <s:text name="pihakCawangan.noTelefon1" size="40" maxlength="12" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:submit name="simpanCawanganPemohon" value="Simpan" class="btn"/>
                    <s:submit name="backCawanganPemohon" value="Tutup" class="btn"/>
                </p>
                <br>
            </fieldset>
        </c:if>
    </s:form>
</div>


