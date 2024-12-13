<%--
    Document   : tambah_pemohon
    Created on : Feb 11, 2010, 1:48:03 PM
    Author     : muhammad.amin
    Modify by  : Siti Fariza Hanim (May 20, 2010)
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="/etanah/pub/js/ageCalculator.js"></script>

<script type="text/javascript">
        $(document).ready( function(){


<c:if test="${flag}">
            self.close();
            opener.refreshPageMaklumatPemohon();

        </c:if>
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



        function savePemohon(event, f){
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.opener.refreshPageMaklumatPemohon();
                self.close();
            },'html');
        }



        function calAgeByDOB(value){
            var age = calculateAge(value.substring(0,2), value.substring(3,5), 20 + value.substring(8,10));
            $('#umur').val(age);
        }

        function doValidation(){
            var val = $('#kod_warganegara').val();
            var val2 = $('#alamat1').val();
            var val3 = $('#nama').val();
            var val4 = $('#jenisPengenalan').val();
            var val5 = $('#warnaKP').val();
            var val6 = $('#bangsa').val();
            var val7 = $('#gaji').val();
            var val8 = $('#poskod').val();
            var val9 = $('#negeri').val();
            var val10 = $('#kp').val();
            if(val3 == ''){
                alert('Sila masukan nama .');
                return false;
            }else if (val4 == ''){
                alert('Sila pilih Jenis Pengenalan.');
                return false;
            }else if (val10 == ''){
                alert('Sila masukan No Kad Pengenalan.');
                return false;
            }else if (val5 == ''){
                alert('Sila pilih Warna Kad Pengenalan.');
                return false;
            }else if (val6 == ''){
                alert('Sila Pilih Bangsa.');
                return false;
            }else if(val == ''){
                alert('Sila pilih warganegara.');
                return false;
            }else if (val7 == ''){
                alert('Sila Masukan Pendapatan Bulanan.');
                return false;
            }else if(val2 == ''){
                alert('Sila masukan alamat.');
                return false;
            }else if (val8 == ''){
                alert('Sila Masukan Poskod.');
                return false;
            }else if (val9 == ''){
                alert('Sila Masukan Negeri.');
                return false;
            }
            return true;
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

        function addPemohon(){
            window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon?showTamshowbahMaklumatIsteri", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=810,height=600,scrollbars=yes");
        }
</script>
<style type="text/css">
    input.error { background-color: yellow; }
</style>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<div class="subtitle" id="caw">
    <s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPemohonActionBean">

        <c:if test="${tuanTanah}">
            <s:text name="tuanTanah" value="${tuanTanah}"/>
        </c:if>

        <s:errors/>
        <s:messages/>

        <c:if test="${!actionBean.tambahCawFlag}">

            <fieldset class="aras1">
                <legend>Kemasukan Maklumat Pemohon</legend><br/>
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

                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B'}">
                            <p>
                                <label for="Warna No Pengenalan">Warna Nombor Kad Pengenalan :</label>
                                ${actionBean.pihak.warnaKP}&nbsp;
                            </p>
                        </c:if>

                        <c:if test="${actionBean.pihak.bangsa.kod ne null}">
                            <p>
                                <label>Bangsa / Jenis Syarikat :</label>
                                ${actionBean.pihak.bangsa.nama}&nbsp;
                            </p>
                        </c:if>
                            <p>
                                <label><font color="red">*</font>Kewarganegaraan :</label>
                                <s:hidden name="pihak.wargaNegara.kod" id="kod_warganegara"/>
                                ${actionBean.pihak.wargaNegara.nama}&nbsp;
                            </p>

                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                            <p>
                                <label>Tarikh Lahir :</label>
                                <s:text name="tarikhLahir" size="40" id="datepicker" class="datepicker" onchange="calAgeByDOB(this.value);"/>
                            </p>
                            <p>
                                <label>Umur :</label>
                                <s:text name="pemohon.umur" size="10" maxlength="3" id="umur" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Tempat Lahir :</label>
                                <s:text name="pihak.tempatLahir" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                             <p>
                                <label>Jantina :</label>
                                <s:select name="pihak.kodJantina" id="jantina" value="kod">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option value="1">Lelaki</s:option>
                                    <s:option value="2">Perempuan</s:option>
                                </s:select>
                            </p>
                            <p>
                            	<label>Anak Tempatan :</label>
                                <s:select name="pihak.negeriKelahiran.kod" id="negeriLahir" value="kod" >
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option value="Y">YA</s:option>
                                    <s:option value="T">TIDAK</s:option>
                                </s:select>
                            </p>
                            <%--<p>
                                <label>Tempoh tinggal di Melaka :</label>
                                <s:text name="pemohon.tempohMastautin" size="10" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                            </p>--%>
                            <p>
                                <label>Kaitan :</label>
                                <s:text name="pemohon.kaitan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label>Pekerjaan :</label>
                                <s:text name="pemohon.pekerjaan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label><font color="red">*</font>Pendapatan Bulanan (RM) :</label>
                                <s:text name="pemohon.pendapatan" id="gaji" size="40" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Status Perkahwinan :</label>
                                <s:select name="pemohon.statusKahwin" value="stsKahwin">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option value="1">Bujang</s:option>
                                    <s:option value="2">Berkahwin</s:option>
                                </s:select>
                            </p>
                            <p>
                                <label>Tanggungan :</label>
                                <s:text name="pemohon.tanggungan" size="40" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                            </p>

                        </c:if>

                        <%-- add modal berbayar (Hairudin - 6 May 2010) --%>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'S' || actionBean.pihak.jenisPengenalan.kod eq 'U'}">
                            <p id="modalBerbayar">
                                <label>Modal Berbayar (RM) :</label>
                                <s:text name="pihak.modalBerbayar" size="40"/>
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
                            </p>
                            <p>
                                <label><font color="red">*</font>Poskod :</label>
                                ${actionBean.pihak.poskod}&nbsp;
                            </p>

                            <p>
                                <label for="Negeri"><font color="red">*</font>Negeri :</label>
                                ${actionBean.pihak.negeri.nama}&nbsp;
                            </p>
                            <p>
                                <label for="alamat"><font color="red">*</font>Alamat Surat-Menyurat :</label>
                                <s:text name="pihak.suratAlamat1" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label> &nbsp;</label>
                                <s:text name="pihak.suratAlamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label> &nbsp;</label>
                                <s:text name="pihak.suratAlamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label> &nbsp;</label>
                                <s:text name="pihak.suratAlamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label><font color="red">*</font>Poskod :</label>
                                <s:text name="pihak.suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                            </p>

                            <p>
                                <label for="Negeri"><font color="red">*</font>Negeri :</label>
                                <s:select name="pihak.suratNegeri.kod">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                                </s:select>
                            </p>

                            <p>
                                    <label><font color="red">*</font>No. Telefon</label>
                                    <s:text name="pihak.noTelefon1" id="noTelefon1" size="40" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                    <label>No. Faksimili</label>
                                    <s:text name="pihak.noTelefon2" id="noTelefon2" size="40" onkeyup="validateNumber(this,this.value);"/>
                            </p>

                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                            <p id="majikanAlamat1">
                                <label for="alamat">Alamat Majikan: </label>
                                <s:text name="institusiAlamat1" id="majikanAlamat1" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p id="majikanAlamat2">
                                <label> &nbsp;</label>
                                <s:text name="institusiAlamat2" id="majikanAlamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p id="majikanAlamat3">
                                <label> &nbsp;</label>
                                <s:text name="institusiAlamat3" id="majikanAlamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p id="majikanAlamat4">
                                <label> &nbsp;</label>
                                <s:text name="institusiAlamat4" id="majikanAlamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p id="majikanPoskod">
                                <label>Poskod :</label>
                                <s:text name="institusiPoskod" id="majikanPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                            </p>

                            <p id="majikanNegeri">
                                <label for="Negeri">Negeri :</label>
                                <s:select name="institusiNegeri.kod" id="majikanNegeri">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                        </c:if>

                    </c:if>
                    <%--tiada data--%>
                    <c:if test="${actionBean.tiadaDataFlag}">
                        <p>
                            <label for="nama"><font color="red">*</font>Nama :</label>
                            <s:text name="pihak.nama" id="nama" size="50" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                            <s:select name="pihak.jenisPengenalan.kod" id="jenisPengenalan" style="width:150px" value="">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <p>
                            <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                            <s:text name="pihak.noPengenalan" id="kp" size="20" maxlength="20" onkeyup="dodacheck(this.value);this.value=this.value.toUpperCase();"/>
                        </p>
                       <%--
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B'}">
                            <p>
                                <label for="Warna No Pengenalan"><font color="red">*</font>Warna Nombor Kad Pengenalan :</label>
                                <s:select name="pihak.warnaKP" id="warnaKP" style="width:100px">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:option value="B">Biru</s:option>
                                        <s:option value="C">Coklat</s:option>
                                        <s:option value="H">Hijau</s:option>
                                        <s:option value="M">Merah</s:option>
                                        <s:option value="L">Lain-lain</s:option>
                                </s:select>
                            </p>
                        </c:if>
--%>                        <p>
                            <label><font color="red">*</font>Bangsa / Jenis Syarikat :</label>
                      <%--      <s:hidden name="pihak.bangsa.kod"/>--%>
                            <s:select name="pihak.bangsa.kod"  style="width:275px">

                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraiKodBangsa}" label="nama" value="kod" />
                            </s:select>
                        </p>
                        <p>
                            <label><font color="red">*</font>Kewarganegaraan :</label>
                            <s:select name="pihak.wargaNegara.kod" style="width:150px" id="kod_warganegara">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                            </s:select>
                        </p>

                        <%--<p id="suku">
                            <label for="Suku">Jenis Suku :</label>
                            <s:select name="pihak.suku.kod" style="width:200px">
                                <s:option value="0">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodSuku}" label="nama" value="kod"/>
                            </s:select>
                        </p>--%>

                    <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                    <p>
                        <label><font color="red">*</font>Tarikh Lahir :</label>
                        <s:text name="tarikhLahir" size="10" id="datepicker" class="datepicker" onchange="calAgeByDOB(this.value);"/>&nbsp;(Contoh: dd/mm/yy)
                    </p>
                     <p>
                        <label><font color="red">*</font>Umur :</label>
                        <s:text name="pemohon.umur" size="5" maxlength="3" id="umur" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                    <p>
                        <label><font color="red">*</font>Tempat Lahir :</label>
                        <s:text name="pihak.tempatLahir" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label><font color="red">*</font>Jantina :</label>
                        <s:select name="pihak.kodJantina" id="jantina" value="" style="width:150px">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="1">Lelaki</s:option>
                            <s:option value="2">Perempuan</s:option>
                            <%--<s:option value="3">Tidak Dikenalpasti</s:option>
                            <s:option value="0">Tidak Berkenaan</s:option>--%>
                        </s:select>
                    </p>
                  <%--  <p>
                        <label><font color="red">*</font>Anak Tempatan :</label>
                         <s:select name="pihak.negeriKelahiran.kod" id="negeriLahir" value="kod" >
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="Y">YA</s:option>
                            <s:option value="T">TIDAK</s:option>
                        </s:select>
                    </p>--%>
                    <%--<p>
                    <label><font color="red">*</font>Tempoh tinggal di Melaka :</label>
                    <s:text name="pemohon.tempohMastautin" size="5" maxlength="3" onkeyup="validateNumber(this,this.value);"/>&nbsp;TAHUN
                    </p>--%>

                    <p>
                        <label><font color="red">*</font>Pekerjaan :</label>
                        <s:text name="pemohon.pekerjaan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label><font color="red">*</font>Pendapatan Bulanan (RM) :</label>
                        <s:text name="pemohon.pendapatan" id="gaji" size="20" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                   <p>
                        <label><font color="red">*</font>Status Perkahwinan :</label>
                        <s:select name="pemohon.statusKahwin" value="stsKahwin">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="1">Bujang</s:option>
                            <s:option value="2">Berkahwin</s:option>
                        </s:select>
                    </p>
                    <p>
                        <label><font color="red">*</font>Tanggungan :</label>
                        <s:text name="pemohon.tanggungan" size="10" maxlength="3" id="tangungan" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                 </c:if>

                <%-- add modal berbayar (Hairudin - 6 May 2010) --%>
                <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'S' || actionBean.pihak.jenisPengenalan.kod eq 'U'}">
                    <p id="modalBerbayar">
                        <label><font color="red">*</font>Modal Berbayar (RM) :</label>
                        <s:text name="pihak.modalBerbayar" size="20" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                </c:if>

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
                        <label> &nbsp;</label>
                        <s:text name="pihak.alamat4" size="40" id="alamat4" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>


                    <p>
                        <label for="Poskod"><font color="red">*</font>Poskod :</label>
                        <s:text name="pihak.poskod" size="40" maxlength="5" id="poskod" onkeyup="validateNumber(this,this.value);"/>
                    </p>

                    <p>
                        <label for="Negeri"><font color="red">*</font>Negeri :</label>
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
                        <label> &nbsp;</label>
                        <s:text name="pihak.suratAlamat4" id="suratAlamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label><font color="red">*</font>Poskod :</label>
                        <s:text name="pihak.suratPoskod" id="suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                    </p>

                    <p>
                        <label for="Negeri"><font color="red">*</font>Negeri :</label>
                        <s:select name="pihak.suratNegeri.kod" id="suratNegeri">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                        </s:select>
                    </p>

                    <%-- add no. telefon and faksimili (Hairudin - 16 May 2010) --%>
                    <p>
                        <label><font color="red">*</font>No. Telefon</label>
                        <s:text name="pihak.noTelefon1" id="noTelefon1" size="40" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                    <p>
                        <label>No. Faksimili</label>
                        <s:text name="pihak.noTelefon2" id="noTelefon2" size="40" onkeyup="validateNumber(this,this.value);"/>
                    </p>

                <%-- add alamat majikan (Hairudin - 6 May 2010) --%>
                <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                    <p id="majikan">
                            <label>Nama/Majikan :</label>
                            <s:text name="pemohon.institusi" id="majikan" size="40" onkeyup="this.value=this.value.toUpperCase();" />
                        </p>
                    <p id="majikanAlamat1">
                            <label for="alamat">Alamat Majikan: </label>
                            <s:text name="pemohon.institusiAlamat1" id="majikanAlamat1" size="40" onkeyup="this.value=this.value.toUpperCase();" />
                    </p>
                    <p id="majikanAlamat2">
                        <label> &nbsp;</label>
                        <s:text name="pemohon.institusiAlamat2" id="majikanAlamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p id="majikanAlamat3">
                        <label> &nbsp;</label>
                        <s:text name="pemohon.institusiAlamat3" id="majikanAlamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p id="majikanAlamat4">
                        <label> &nbsp;</label>
                        <s:text name="pemohon.institusiAlamat4" id="majikanAlamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>


                    <p id="majikanPoskod">
                        <label>Poskod :</label>
                        <s:text name="pemohon.institusiPoskod" id="majikanPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                    <p id="majikanNegeri">
                        <label for="Negeri">Negeri :</label>
                        <s:select name="pemohon.institusiNegeri.kod" id="majikanNegeri">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                </c:if>

            </c:if>
                    <%--
                <c:if test="${actionBean.pihak.jenisPengenalan.kod ne 'B' && actionBean.pihak.jenisPengenalan.kod ne 'L' && actionBean.pihak.jenisPengenalan.kod ne 'P' && actionBean.pihak.jenisPengenalan.kod ne 'T' && actionBean.pihak.jenisPengenalan.kod ne 'I'}">
                    <div class="content" align="center"><br/>
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
                </c:if>--%>
            </c:if>

                <c:if test="${!actionBean.cariFlag}">
                    <p>
                        <label>&nbsp;</label>
                        <s:submit name="cariPihakPemohon" value="Cari" class="btn" />
                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    </p>
                </c:if>

                <c:if test="${actionBean.cariFlag}">
                    <p>
                        <label>&nbsp;</label>
                        <%--<s:button name="simpanPemohonPopup" id="simpan" value="Simpan" class="btn" onclick="savePemohon(this.name, this.form);"/>--%>
                        <s:submit name="simpanPemohonPopup" id="simpan" value="Simpan" class="btn" />
                        <%--<c:if test="${actionBean.pihak.jenisPengenalan.kod ne 'B' && actionBean.pihak.jenisPengenalan.kod ne 'L' && actionBean.pihak.jenisPengenalan.kod ne 'P' && actionBean.pihak.jenisPengenalan.kod ne 'T' && actionBean.pihak.jenisPengenalan.kod ne 'I'}">
                            <s:submit name="tambahCawanganPemohon" value="Tambah Cawangan" class="btn"/>
                        </c:if>--%>
                       <%-- <s:button name="" id="seterusnya" value="Seterusnya" class="btn" onclick="addPemohon();"/>--%>
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
                    <label for="nama"><font color="red">*</font>Nama :</label>
                    <s:text name="pihakCawangan.namaCawangan" id="nama" size="40"/>
                </p>
                <p>
                    <label for="alamat"><font color="red">*</font>Alamat Berdaftar :</label>
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
                </p>
                <p>
                    <label><font color="red">*</font>Poskod :</label>
                    <s:text name="pihakCawangan.poskod" size="40" id="poskodCaw" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                    <label for="Negeri"><font color="red">*</font>Negeri :</label>
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
                    <label for="alamat"><font color="red">*</font>Alamat Surat-Menyurat :</label>
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
                    <label><font color="red">*</font>Poskod :</label>
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
                    <label for="alamat"><font color="red">*</font>Nombor Telefon :</label>
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
