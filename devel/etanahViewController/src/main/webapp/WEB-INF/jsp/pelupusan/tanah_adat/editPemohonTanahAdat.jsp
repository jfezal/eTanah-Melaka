<%-- 
    Document   : editPemohonTanahAdat
    Created on : Jul 12, 2011, 10:00:06 AM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
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
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>

<script type="text/javascript">
    $(document).ready( function(){
        maximizeWindow();
    <c:if test="${!flag}">
            opener.refreshMaklumatPemohon();
            self.close();
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
                var icNo = '${actionBean.pemohonHbgn.noPengenalan}';
                if(icNo != ""){
                    $('#datepicker').val(icNo.substring(4,6)+"/"+icNo.substring(2,4)+"/"+19 + icNo.substring(0,2));
                    var age = calculateAge(icNo.substring(4,6), icNo.substring(2,4), 19 + icNo.substring(0,2));
                    $('#umur').val(age);
                }

            }
           
        });

        function calculateUmar(){
            if($("#tarikhLahir").val() != ""){
                var value = $("#tarikhLahir").val();
                var yearStartVal = value.substring(6,8);
                if(yearStartVal == "19"){
                    var age = calculateAge(value.substring(0,2), value.substring(3,5), 19 + value.substring(8,10));
                }else{
                    var age = calculateAge(value.substring(0,2), value.substring(3,5), 20 + value.substring(8,10));
                }
                $('#umur').val(age);
            }
        }
        function doCheckUmur(v,id,type){
            var va = $('#jenisPengenalan').val();
            if(va == 'B'){
                return doValidateAge(v, id, 'jenisPengenalan',type);
            }else {
                return true;
            }
        }


        function calAgeByDOB(value){
            var age = calculateAge(value.substring(0,2), value.substring(3,5), 20 + value.substring(8,10));
            $('#umur').val(age);
        }

        function validateNumber(elmnt,content) {
            //if it is character, then remove it..
            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }


        function doCheckUmur(v,id,type){
            var va = $('#jenisPengenalan').val();
    <%-- alert("va::"+va);
     alert("type::"+type);--%>
             if(va == 'B'){
    <%--alert("if va::"+va);--%>
                return doValidateAge(v, id, 'jenisPengenalan',type);
            }else {
                return true;
            }
        }


        function doValidateAge(ic, id, id2,type){
    <%-- alert("--doValidateAge--");
     alert(ic);
     alert(id);
     alert(id2);
     alert("type:"+type);--%>
             var valid = true;
             var v = $('#'+id2).val();
             if(v == 'B'){
                 date = ic.substring(0,6);
                 yyyy = date.substring(0,2);
                 mm = date.substring(2,4);
                 dd = date.substring(4,6);
                 if(yyyy < 99 && yyyy > 30){//fixme :
                     yyyy = "19" + yyyy;
                 }else {
                     yyyy = "20" + yyyy;
                 }

    <%--alert(yyyy + ',' + mm + ','+ dd);--%>

                days = new Date();
                gdate = days.getDate();
                gmonth = days.getMonth();
                gyear = days.getFullYear();
                age=gyear-yyyy;

    <%--alert(gyear + ',' + yyyy + ',' + days.getFullYear());--%>
                if((mm==(gmonth+1))&&(dd<=parseInt(gdate))) {
                    age=age
                } else {
                    if(mm<=(gmonth)) {
                        age=age
                    } else {
                        age=age-1
                    }
                }
                if(type != 'WAR'){

                    if(age < 18){
                        alert('Umur Tidak mencukupi. Mesti 18 tahun dan keatas.');
                        $('#'+id).val('');
                        $('#'+id).focus();
                        valid = false;
                    }
                }else{
                    if(age > 18){
                        alert('Umur Penerima Mesti 18 tahun dan kebawah.');
                        $('#'+id).val('');
                        $('#'+id).focus();
                        valid = false;
                    }
                }
            }
            return valid;
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


        function copyAdd1(){
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

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPemohon1ActionBean">
    <div align="center">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle" id="caw">
        <fieldset class="aras1">
            <legend>
                Maklumat Pemohon
            </legend>
                <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'F'||actionBean.pihak.jenisPengenalan.kod eq 'N'}">
            <p>
                <label><font color="red">*</font>Nama     :</label>
                <s:text name="pihak.nama" value=" ${actionBean.pihak.nama}" onkeyup="this.value=this.value.toUpperCase();"></s:text>
                &nbsp;<s:hidden name="pihak.idPihak"/>
                 <s:hidden name="idPermohonan" id="idPermohonan"/>
            </p>
                </c:if>
            
              <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'S'|| actionBean.pihak.jenisPengenalan.kod eq 'U'}">
                <p>
                <label><font color="red">*</font>Nama Syarikat   :</label>
                <s:text name="pihak.nama" value=" ${actionBean.pihak.nama}" onkeyup="this.value=this.value.toUpperCase();"></s:text>
                &nbsp;<s:hidden name="pihak.idPihak"/>
            </p>
              </c:if>

            <p>
                <label>Jenis Pengenalan :</label>
                <s:hidden name="pihak.jenisPengenalan.nama"/>
                ${actionBean.pihak.jenisPengenalan.nama}&nbsp;
            </p>
            <p>
                <label>Nombor Pengenalan :</label>
                <s:hidden name="pihak.noPengenalan"/>
                ${actionBean.pihak.noPengenalan}&nbsp;
            </p><s:hidden name="pihak.bangsa.kod"/>
                    <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'S'|| actionBean.pihak.jenisPengenalan.kod eq 'U'}">
                            <p>
                                <label><font color="red">*</font>Jenis Syarikat :</label> 
                                <s:select name="kodBangsa" style="width:246px">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${actionBean.senaraiKodBangsa}" label="nama" value="kod" />
                                </s:select>
                            </p>
                            <p>
                                <label><font color="red">*</font>Tarikh Penubuhan :</label>
                                <s:text name="pihak.tarikhLahir" size="40" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy"/>
                            </p>
                        </c:if>

                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'F'||actionBean.pihak.jenisPengenalan.kod eq 'N'}">
                        <p>
                                <label>Bangsa :</label>
                                <s:select name="kodBangsa" style="width:246px">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${actionBean.senaraiKodBangsa}" label="nama" value="kod" />
                                </s:select>
                            </p>
                        </c:if>

            <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'F'||actionBean.pihak.jenisPengenalan.kod eq 'N'}">
            <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B'}">
                <p>
                    <label>Warna Nombor Kad Pengenalan :</label>
                    <s:select name="pihak.warnaKP" id="warnaKP" style="width:100px"> <%--value="${actionBean.pihak.warnaKP}"--%>
                        <s:option value="0">SILA PILIH</s:option>
                        <s:option value="B">Biru</s:option>
                        <s:option value="C">Coklat</s:option>
                        <s:option value="H">Hijau</s:option>
                        <s:option value="M">Merah</s:option>
                        <s:option value="L">Lain-lain</s:option>
                    </s:select>
                </p>
            </c:if>
                            <p>
                                <label>Suku :</label>
                                <s:select name="kodS" style="width:150px" value="${actionBean.pihak.suku.kod}"id="kod_suku">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodSuku}" label="nama" value="kod"/>
                                </s:select>
                            </p>

                <p>
                    <label>Kewarganegaraan :</label>
                    <s:select name="kodW" style="width:150px" value="${actionBean.pihak.wargaNegara.kod}"id="kod_warganegara">
                        <s:option value="0">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                    </s:select>
                </p>

                <p>
                    <label>Tarikh Lahir :</label>
                    <s:text name="pihak.tarikhLahir" size="40" id="tarikhLahir" class="datepicker" onchange="calculateUmar();"formatPattern="dd/MM/yyyy" />

                </p>
                <p>
                    <label>Umur :</label>
                    <s:text name="pemohon.umur" size="10" maxlength="3"  id="umur" onkeyup="validateNumber(this,this.value);"/>

                <p>
                    <label>Tempat Lahir : </label>
                    <s:text name="pihak.tempatLahir" size="40" onkeyup="this.value=this.value.toUpperCase();"/>

                </p>
                <p>
                    <label>Jantina :</label>
                    <s:radio name="pihak.kodJantina" id="jantina" value="1"/> Lelaki
                    <s:radio name="pihak.kodJantina" id="jantina" value="2"/> Perempuan

                </p>

                 <c:if test="${Anak}">
                <p>
                    <label> Anak Tempatan :</label>
                    <s:radio name="pihak.anakTempatan" id="anakTempatan" value="Y" />YA
                    <s:radio name="pihak.anakTempatan" id="anakTempatan" value="T" />TIDAK
                </p>
                  <p>
                    <label>Tempoh tinggal di Melaka : </label>
                    <s:text name="pemohon.tempohMastautin" size="5" maxlength="3" onkeyup="validateNumber(this,this.value);"/>&nbsp;TAHUN
                </p>
               </c:if>

              

                <p>
                    <label>Pekerjaan :</label>
                    <s:text name="pemohon.pekerjaan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>Pendapatan Bulanan (RM) :</label>
                    <s:text name="pemohon.pendapatan" size="40" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                    <label>Status Perkahwinan:</label>
                    <s:select name="pemohon.statusKahwin" id="statusKahwin">
                        <s:option value="0">Sila Pilih</s:option>
                        <s:option value="Bujang">Bujang</s:option>
                        <s:option value="Berkahwin">Berkahwin</s:option>
                         <s:option value="Duda">Duda</s:option>
                         <s:option value="Janda">Janda</s:option>
                    </s:select>
                </p>
                <p>
                    <label>Tanggungan :</label>
                    <s:text name="pemohon.tanggungan" size="10" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                </p>
            </c:if>

                  <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'F'||actionBean.pihak.jenisPengenalan.kod eq 'N'}">
            <p>
                <label for="alamat"><font color="red">*</font>Alamat Berdaftar :</label>
                <s:text name="pihak.alamat1" value="${actionBean.pihak.alamat1}" id="alamat1" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.alamat2" value="${actionBean.pihak.alamat2}"size="40" id="alamat2" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.alamat3" value="${actionBean.pihak.alamat3}"size="40"  id="alamat3" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.alamat4" value="${actionBean.pihak.alamat4}"size="40"  id="alamat4"  onkeyup="this.value=this.value.toUpperCase();"/>

            </p>


            <p>
                <label><font color="red">*</font>Poskod :</label>
                <s:text name="pihak.poskod" value="${actionBean.pihak.poskod}" size="40" id="poskod" maxlength="5" onkeyup="validateNumber(this,this.value);"/>              </p>
            <p>
                <label><font color="red">*</font>Negeri :</label>
                <s:select name="kodNegeri"  id="negeri" >
                    <s:option value="0">Pilih </s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>
            </p>
                  
            <p>
                <label>&nbsp;</label>
                <input type="checkbox" id="add1" name="add1" value="" onclick="copyAdd1();"/>
                <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
            </p>
            </c:if>
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
                <s:text name="pihak.suratPoskod" value="${actionBean.pihak.suratPoskod}"id="suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label><font color="red">*</font>Negeri :</label>
                <s:select name="suratNegeri" value="${actionBean.pihak.suratNegeri.kod}" id="suratNegeri" >
                    <s:option value="0">Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>
            </p>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'F'||actionBean.pihak.jenisPengenalan.kod eq 'N'}">
                            <p>
                            <label>No. Telefon (Rumah)</label>
                            <s:text name="pihak.noTelefon1" id="noTelefon1" value="${actionBean.pihak.noTelefon1}"size="40" onkeyup="validateNumber(this,this.value);"/>
                        </p>
                         </c:if>
                          <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'S' || actionBean.pihak.jenisPengenalan.kod eq 'U'}">
                         <p>
                            <label>No. Telefon (Office)</label>
                            <s:text name="pihak.noTelefon1" id="noTelefon1" value="${actionBean.pihak.noTelefon1}"size="40" onkeyup="validateNumber(this,this.value);"/>
                        </p>
                        <p>
                            <label>Emel :</label>
                            <s:text name="pihak.email" id="emel" value="${actionBean.pihak.email}"size="40"/>
                        </p>
                          </c:if>
                  <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'F'||actionBean.pihak.jenisPengenalan.kod eq 'N'}">
            <p>
                <label>No. Telefon (Bimbit)</label>
                <s:text name="pihak.noTelefonBimbit" id="noTelefonBimbit" value="${actionBean.pihak.noTelefonBimbit}"size="40" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTD' || actionBean.permohonan.kodUrusan.kod eq 'PBPTG' || actionBean.permohonan.kodUrusan.kod eq 'PPBB'}">
                            <p>
                                <label for="Bank">Jenis Bank :</label>
                                <s:select name="kodBan" value="${actionBean.pihak.bank.kod}" id="jenisBank">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodBank}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                            <p>
                                <label>No. Akaun Bank :</label>
                                <s:text name="pihak.noAkaunBank" id="noAkaunBank" value="${actionBean.pihak.noAkaunBank}" size="28" onkeyup="validateNumber(this,this.value);"></s:text>
                            </p>
                        </c:if>
             <p>
                 <label>Emel :</label>
                 <s:text name="pihak.email" id="emel" value="${actionBean.pihak.email}"size="40"/>
             </p>
                  </c:if>
            
            <p>
                <label>No. Faksimili</label>
                <s:text name="pihak.noTelefon2" id="noTelefon2" value="${actionBean.pihak.noTelefon2}"size="40" onkeyup="validateNumber(this,this.value);"/>
            </p>


            <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'F'||actionBean.pihak.jenisPengenalan.kod eq 'N'}">
                <p id="majikan">
                    <label>Nama/Majikan :</label>
                    <s:text name="pemohon.institusi" value="${actionBean.pemohon.institusi}" id="majikan" size="40" onkeyup="this.value=this.value.toUpperCase();" />
                </p>

                <p id="institusiAlamat1">
                    <label for="alamat">Alamat Majikan: </label>
                    <s:text name="pemohon.institusiAlamat1" id="institusiAlamat1" size="40" onkeyup="this.value=this.value.toUpperCase();" />
                </p>
                <p id="institusiAlamat2">
                    <label> &nbsp;</label>
                    <s:text name="pemohon.institusiAlamat2" id="institusiAlamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p id="institusiAlamat3">
                    <label> &nbsp;</label>
                    <s:text name="pemohon.institusiAlamat3" id="institusiAlamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p id="institusiAlamat4">
                    <label> &nbsp;</label>
                    <s:text name="pemohon.institusiAlamat4" id="institusiAlamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>


                <p>
                    <label>Poskod :</label>
                    <s:text name="pemohon.institusiPoskod"  id ="institusiPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                </p>

                <p>
                    <label>Negeri :</label>
                    <s:select name="institusiNegeri"  value="${actionBean.pemohon.institusiNegeri.kod}"id="institusiNegeri1" >
                        <s:option value="0">Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" />
                    </s:select>
                </p>
            </c:if>
            <br>
            <table width="86%">
                <tr>
                    <td align="center">
                        <s:submit name="simpanEditPemohonTanahAdat" id="simpan" value="Simpan" class="btn"/>

                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    </td>
                </tr>
            </table>
        </fieldset>
    </div>
</s:form>
