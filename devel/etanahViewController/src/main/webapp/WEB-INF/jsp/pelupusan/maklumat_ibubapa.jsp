<%--
    Document   : maklumat_ibubapa
    Created on : July 07, 2010, 5:27:45 PM
    Author     : sitifariza.hanim
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>

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
        <c:if test="${flag}">
            self.close();
            opener.refreshPageMaklumatPemohon();

        </c:if>
        $('.alphanumeric').alphanumeric();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $('#jenisPengenalan').change(function(){
            dodacheck($("#kp").val());
        });
        var v = '${actionBean.pemohonHubungan.jenisPengenalan.kod}';

        $('form').submit(function(){
            var val = $('#kp').val();
            var jenis = '${jenis}';
            return doCheckUmur(val, 'kp',jenis);
        });

        if(v == "B"){
            var icNo = '${actionBean.pemohonHubungan.noPengenalan}';<%--alert(icNo);alert(icNo.substring(4,6)+'/'+icNo.substring(2,4)+'/'+icNo.substring(0,2));--%>
            var age = calculateAge(icNo.substring(4,6), icNo.substring(2,4), 19 + icNo.substring(0,2));<%--alert(age);--%>
            $('#umur').val(age);
            $('#datepicker').val(icNo.substring(4,6)+"/"+icNo.substring(2,4)+"/"+19 + icNo.substring(0,2));
        }

        $('#kod_warganegara').val('MAL');

        });

        function calAgeByDOB(value){
            var age = calculateAge(value.substring(0,2), value.substring(3,5), 20 + value.substring(8,10));
            $('#umur').val(age);
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

</script>
<style type="text/css">
    input.error { background-color: yellow; }
</style>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<div class="subtitle" id="caw">
    <s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPemohonActionBean">
        <s:errors/>
        <s:messages/>

        <%--<s:text name="pemohon.idPemohon"/>--%>
        <c:if test="${!actionBean.tambahCawFlag}">

            <fieldset class="aras1">
                <legend>Kemasukan Maklumat Ibubapa Pemohon</legend><br/>
                <c:if test="${!actionBean.cariFlag}">
                    <p>
                        <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                        <s:select name="pemohonHubungan.jenisPengenalan.kod" id="jenisPengenalan"  onchange="javaScript:changeHideSuku(this.options[selectedIndex].text)">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="B">NO K/P BARU</s:option>
                            <s:option value="L">NO K/P LAMA</s:option>
                            <%--<s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>--%>
                        </s:select>
                    </p>
                    <p>
                        <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                        <s:text name="pemohonHubungan.noPengenalan" id="kp" size="40" onkeyup="dodacheck(this.value);this.value=this.value.toUpperCase();"
                                onblur="doCheckUmur(this.value, this.id,'${jenis}')"/>
                    </p>
                </c:if>

                <c:if test="${actionBean.cariFlag}">
                    <c:if test="${!actionBean.tiadaDataFlag}">
                        <p>
                            <label for="nama"><font color="red">*</font>Nama Suami/Ibubapa:</label>
                            ${actionBean.pemohonHubungan.nama}&nbsp;
                        </p>
                        <p>
                            <label for="Jenis Pengenalan"><font color="red">*</font>Jenis Pengenalan :</label>
                            ${actionBean.pemohonHubungan.jenisPengenalan.nama}&nbsp;
                        </p>
                        <p>
                            <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                            ${actionBean.pemohonHubungan.noPengenalan}&nbsp;
                        </p>

                        <%--<c:if test="${actionBean.pemohonHubungan.jenisPengenalan.kod eq 'B'}">
                            <p>
                                <label for="Warna No Pengenalan"><font color="red">*</font>Warna Nombor Kad Pengenalan :</label>
                                ${actionBean.pemohonHubungan.warnaKP}&nbsp;
                            </p>
                        </c:if>--%>

                        <c:if test="${actionBean.pemohonHubungan.bangsa.kod ne null}">
                            <p>
                                <label>Bangsa:</label>
                                ${actionBean.pemohonHubungan.bangsa.nama}&nbsp;
                            </p>
                        </c:if>
                            <p>
                                <label><font color="red">*</font>Kewarganegaraan :</label>
                                <s:hidden name="pemohonHubungan.wargaNegara.kod" id="kod_warganegara"/>
                                ${actionBean.pemohonHubungan.wargaNegara.nama}&nbsp;
                            </p>

                        <c:if test="${actionBean.pemohonHubungan.jenisPengenalan.kod eq 'B' || actionBean.pemohonHubungan.jenisPengenalan.kod eq 'L' || actionBean.pemohonHubungan.jenisPengenalan.kod eq 'P' || actionBean.pemohonHubungan.jenisPengenalan.kod eq 'T' || actionBean.pemohonHubungan.jenisPengenalan.kod eq 'I'}">
                            <p>
                                <label><font color="red">*</font>Tarikh Lahir :</label>
                                <s:text name="tarikhLahir" size="40" id="datepicker" class="datepicker" onchange="calAgeByDOB(this.value);"/>
                            </p>
                            <p>
                                <label><font color="red">*</font>Umur :</label>
                                <s:text name="pemohonHubungan.umur" size="10" maxlength="3" id="umur" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label><font color="red">*</font>Tempat Lahir :</label>
                                <s:text name="pemohonHubungan.tempatLahir" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                             <p>
                                <label><font color="red">*</font>1.Kaitan :</label>
                                <s:select name="pemohonHubungan.kaitan" id="kaitan" >
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option value="BAPA">BAPA</s:option>
                                    <s:option value="IBU">IBU</s:option>
                                </s:select>
                            </p>
                            <p>
                                <label>Pekerjaan :</label>
                                <s:text name="pemohonHubungan.pekerjaan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label><font color="red">*</font>Pendapatan Bulanan (RM) :</label>
                                <s:text name="pemohonHubungan.gaji" id="gaji" size="40" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                    <label for="alamat">Alamat Berdaftar :</label>
                                    <s:text name="pemohonHubungan.alamat1" size="40" id="alamat1" onkeyup="this.value=this.value.toUpperCase();"/>
                                </p>

                                <p>
                                    <label> &nbsp;</label>
                                    <s:text name="pemohonHubungan.alamat2" size="40" id="alamat2" onkeyup="this.value=this.value.toUpperCase();"/>
                                </p>

                                <p>
                                    <label> &nbsp;</label>
                                    <s:text name="pemohonHubungan.alamat3" size="40" id="alamat3" onkeyup="this.value=this.value.toUpperCase();"/>
                                </p>
                                <p>
                                    <label> &nbsp;</label>
                                    <s:text name="pemohonHubungan.alamat4" size="40" id="alamat4" onkeyup="this.value=this.value.toUpperCase();"/>
                                </p>
                                <p>
                                    <label for="Poskod">Poskod :</label>
                                    <s:text name="pemohonHubungan.poskod" size="40" maxlength="5" id="poskod" onkeyup="validateNumber(this,this.value);"/>
                                </p>

                                <p>
                                    <label for="Negeri">Negeri :</label>
                                    <s:select name="negeri.kod" id="negeri">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                                    </s:select>
                                </p>

                        </c:if>

                            <p id="majikanAlamat1">
                                <label for="alamat">Alamat Majikan: </label>
                                <s:text name="pemohonHubungan.institusiAlamat1" id="majikanAlamat1" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p id="majikanAlamat2">
                                <label> &nbsp;</label>
                                <s:text name="pemohonHubungan.institusiAlamat2" id="majikanAlamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p id="majikanAlamat3">
                                <label> &nbsp;</label>
                                <s:text name="pemohonHubungan.institusiAlamat3" id="majikanAlamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p id="majikanAlamat4">
                                <label> &nbsp;</label>
                                <s:text name="pemohonHubungan.institusiAlamat4" id="majikanAlamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p id="majikanPoskod">
                                <label>Poskod :</label>
                                <s:text name="pemohonHubungan.institusiPoskod" id="majikanPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                            </p>

                            <p id="majikanNegeri">
                        <label for="Negeri">Negeri :</label>
                        <s:select name="pemohonHubungan.institusiNegeri.kod"  value="${actionBean.pemohonHubungan.institusiNegeri.kod}">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                        </c:if>

                    </c:if>


                    <%--tiada data--%>
                    <c:if test="${actionBean.tiadaDataFlag}">

                        <s:hidden name="ibubapa" value="maklumat-ibubapa"></s:hidden>
                        <p>
                            <label for="nama"><font color="red">*</font>Nama Ibubapa :</label>
                            <s:text name="pemohonHubungan.nama" id="nama" size="50" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                            <s:select name="pemohonHubungan.jenisPengenalan.kod" id="jenisPengenalan" style="width:150px" value="">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="B">NO K/P BARU</s:option>
                                <s:option value="L">NO K/P LAMA</s:option>
                                <%--<s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>--%>
                            </s:select>
                        </p>
                        <p>
                            <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                            <s:text name="pemohonHubungan.noPengenalan" id="kp" size="20" maxlength="20" onkeyup="dodacheck(this.value);this.value=this.value.toUpperCase();"/>
                        </p>
                        <c:if test="${actionBean.pemohonHubungan.jenisPengenalan.kod eq 'B'}">
                           <%-- <p>
                                <label for="Warna No Pengenalan"><font color="red">*</font>Warna Nombor Kad Pengenalan :</label>
                                <s:select name="pemohonHubungan.warnaKP" id="warnaKP" style="width:100px">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:option value="B">Biru</s:option>
                                        <s:option value="C">Coklat</s:option>
                                        <s:option value="H">Hijau</s:option>
                                        <s:option value="M">Merah</s:option>
                                        <s:option value="L">Lain-lain</s:option>
                                </s:select>
                            </p>--%>
                        </c:if>
                        <p>
                            <label><font color="red">*</font>Bangsa:</label>
                            <s:select name="pemohonHubungan.bangsa.kod" id="bangsa" style="width:275px">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraiKodBangsa}" label="nama" value="kod" />
                            </s:select>
                        </p>
                        <p>
                            <label><font color="red">*</font>Kewarganegaraan :</label>
                            <s:select name="pemohonHubungan.wargaNegara.kod" style="width:150px" id="kod_warganegara">
                                <s:option value="0">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                            </s:select>
                        </p>

                        <%--<p id="suku">
                            <label for="Suku">Jenis Suku :</label>
                            <s:select name="pemohonHubungan.suku.kod" style="width:200px">
                                <s:option value="0">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodSuku}" label="nama" value="kod"/>
                            </s:select>
                        </p>--%>

                    <p>
                        <label><font color="red">*</font>Tarikh Lahir :</label>
                        <s:text name="pemohonHubungan.tarikhLahir" size="10" id="datepicker" class="datepicker" onchange="calAgeByDOB(this.value);"/>&nbsp;(Contoh: dd/mm/yy)
                    </p>
                     <p>
                        <label><font color="red">*</font>Umur :</label>
                        <s:text name="pemohonHubungan.umur" size="5" maxlength="3" id="umur" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                    <p>
                        <label><font color="red">*</font>Tempat Lahir :</label>
                        <s:text name="pemohonHubungan.tempatLahir" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label><font color="red">*</font>Kaitan :</label>
                        <s:select name="pemohonHubungan.kaitan" id="kaitan" >
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="BAPA">BAPA</s:option>
                            <s:option value="IBU">IBU</s:option>
                        </s:select>
                    </p>
                   <%-- <p>
                        <label>Jantina :</label>
                        <s:select name="pemohonHubungan.kodJantina" id="jantina" value="" style="width:150px">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="1">Lelaki</s:option>
                            <s:option value="2">Perempuan</s:option>
                            <s:option value="3">Tidak Dikenalpasti</s:option>
                            <s:option value="0">Tidak Berkenaan</s:option>
                        </s:select>
                    </p>
                    <p>
                        <label>Anak Tempatan :</label>
                        <s:radio value="Y" name="pemohonHubungan.negeriKelahiran.kod"/>&nbsp;YA
                        <s:radio value="T" name="pemohonHubungan.negeriKelahiran.kod"/>&nbsp;TIDAK
                    </p>
                    <p>
                    <label>Tempoh tinggal di Melaka :</label>
                    <s:text name="pemohonHubungan.tempohMastautin" size="5" maxlength="3" onkeyup="validateNumber(this,this.value);"/>&nbsp;TAHUN
                    </p>--%>

                    <p>
                        <label>Pekerjaan :</label>
                        <s:text name="pemohonHubungan.pekerjaan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label><font color="red">*</font>Pendapatan Bulanan (RM) :</label>
                        <s:text name="pemohonHubungan.gaji" id="gaji" size="20" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                   <%--<p>
                        <label>Status Perkahwinan :</label>
                        <s:select name="pemohonHubungan.statusKahwin" value="stsKahwin">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:option value="1">Bujang</s:option>
                            <s:option value="2">Berkahwin</s:option>
                        </s:select>
                    </p>
                    <p>
                        <label>Tanggungan :</label>
                        <s:text name="pemohonHubungan.tanggungan" size="10" maxlength="3" id="tangungan" onkeyup="validateNumber(this,this.value);"/>
                    </p>--%>



                  <%--     <p>
                        <label>&nbsp;</label>
                        <input type="checkbox" id="add1" name="add1" value="" onclick="copyAdd();"/>
                        <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                    </p>
                    <p>
                        <label for="alamat">Alamat Surat-Menyurat :</label>
                        <s:text name="pemohonHubungan.suratAlamat1" id="suratAlamat1" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="pemohonHubungan.suratAlamat2" id="suratAlamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="pemohonHubungan.suratAlamat3" id="suratAlamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="pemohonHubungan.suratAlamat4" id="suratAlamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Poskod :</label>
                        <s:text name="pemohonHubungan.suratPoskod" id="suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                    </p>

                    <p>
                        <label for="Negeri">Negeri :</label>
                        <s:select name="pemohonHubungan.suratNegeri.kod" id="suratNegeri">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                        </s:select>
                    </p>

                     add no. telefon and faksimili (Hairudin - 16 May 2010)
                    <p>
                        <label>No. Telefon</label>
                        <s:text name="pemohonHubungan.noTelefon1" id="noTelefon1" size="40" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                    <p>
                        <label>No. Faksimili</label>
                        <s:text name="pemohonHubungan.noTelefon2" id="noTelefon2" size="40" onkeyup="validateNumber(this,this.value);"/>
                    </p>
--%>
                    <p id="majikan">
                            <label>Nama/Majikan :</label>
                            <s:text name="pemohonHubungan.institusi" id="majikan" size="40" onkeyup="this.value=this.value.toUpperCase();" />
                        </p>
                    <p id="majikanAlamat1">
                            <label for="alamat">Alamat Majikan: </label>
                            <s:text name="pemohonHubungan.institusiAlamat1" id="majikanAlamat1" size="40" onkeyup="this.value=this.value.toUpperCase();" />
                    </p>
                    <p id="majikanAlamat2">
                        <label> &nbsp;</label>
                        <s:text name="pemohonHubungan.institusiAlamat2" id="majikanAlamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p id="majikanAlamat3">
                        <label> &nbsp;</label>
                        <s:text name="pemohonHubungan.institusiAlamat3" id="majikanAlamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p id="majikanAlamat4">
                        <label> &nbsp;</label>
                        <s:text name="pemohonHubungan.institusiAlamat4" id="majikanAlamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p id="majikanPoskod">
                        <label>Poskod :</label>
                        <s:text name="pemohonHubungan.institusiPoskod" id="majikanPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                     <p>
                                    <label for="Negeri">Negeri :</label>
                                    <s:select name="pemohonHubungan.institusiNegeri.kod" id="negeri" value="${actionBean.pemohonHubungan.institusiNegeri.kod}">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                                    </s:select>
                                </p>


                 <legend>(Jika telah meninggal dunia)</legend>
                    <p>
                        <label> Tarikh meninggal dunia</label>
                        <s:text name="pemohonHubungan.tarikhMati" size="10" class="datepicker" id="tarikhMati"/>
                    </p>

                    <p>
                        <label for="alamat">Alamat Terakhir :</label>
                        <s:text name="pemohonHubungan.alamat1" size="40" id="alamat1" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>

                    <p>
                        <label> &nbsp;</label>
                        <s:text name="pemohonHubungan.alamat2" size="40" id="alamat2" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>

                    <p>
                        <label> &nbsp;</label>
                        <s:text name="pemohonHubungan.alamat3" size="40" id="alamat3" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="pemohonHubungan.alamat4" size="40" id="alamat4" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>

                    <p>
                        <label for="Poskod">Poskod :</label>
                        <s:text name="pemohonHubungan.poskod" size="40" maxlength="5" id="poskod" onkeyup="validateNumber(this,this.value);"/>
                    </p>

                         <p>
                <label>Negeri :</label>
                <s:select name="pemohonHubungan.negeri.kod" >
                    <s:option value="">Pilih </s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>

                    </c:if>
            </c:if>

                <c:if test="${!actionBean.cariFlag}">
                    <p>
                        <label>&nbsp;</label>
                        <s:submit name="cariIbubapaPemohon" value="Cari" class="btn"/>
                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    </p>
                </c:if>

                <c:if test="${actionBean.cariFlag}">
                    <p>
                        <label>&nbsp;</label>
                        <%--<s:button name="simpanMaklumatIbubapa" id="simpan" value="Simpan" class="btn" onclick="savePemohon(this.name, this.form);"/>--%>
                        <s:submit name="simpanMaklumatIbubapa" id="simpan" value="Simpan" class="btn" />
                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    </p>
                </c:if>
                <br>
            </fieldset>

    </s:form>
</div>
