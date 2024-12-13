<%--
    Document   : edit_pemohon
    Created on : Feb 12, 2010, 12:04:35 PM
    Author     : muhammad.amin
    Modify by  : Siti Fariza Hanim (May 20, 2010)
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

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

   function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
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

            function calAgeByDOB(value){
                var age = calculateAge(value.substring(0,2), value.substring(3,5), 20 + value.substring(8,10));
                $('#umur').val(age);
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
<s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPemohonActionBean">
    <s:errors/>
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pemohon
            </legend>
            <p>
                <label><font color="red">*</font>Nama     :</label>
                <%--ravi changs--%>
                <s:text name="pihak.nama" value=" ${actionBean.pihak.nama}" onkeyup="this.value=this.value.toUpperCase();"></s:text>
                &nbsp;<s:hidden name="pihak.idPihak"/>
            </p>
            <p>
                <label>Jenis Pengenalan :</label>
                ${actionBean.pihak.jenisPengenalan.nama}&nbsp;
                <s:hidden name="pihak.jenisPengenalan.nama"/>
                <s:hidden name="pihak.jenisPengenalan.kod"/>
            </p>
            <p>
                <label>Nombor Pengenalan :</label>
                ${actionBean.pihak.noPengenalan}&nbsp;
                 <s:hidden name="pihak.noPengenalan"/>
            </p>
            <%--<p>
                <label><font color="red">*</font>Warna Nombor Kad Pengenalan :</label>
                <s:select name="pihak.warnaKP" id="warnaKP" style="width:100px">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:option value="B">Biru</s:option>
                    <s:option value="C">Coklat</s:option>
                    <s:option value="H">Hijau</s:option>
                    <s:option value="M">Merah</s:option>
                    <s:option value="L">Lain-lain</s:option>
                </s:select>
            </p>
--%>


            <p>
                <label><font color="red">*</font>Bangsa / Jenis Syarikat :</label>
                <s:select name="kodBangsa" id="bangsa" style="width:275px">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodBangsa}" label="nama" value="kod" />
                </s:select>
            </p>
            <p>
                <label><font color="red">*</font>Kewarganegaraan :</label>
                <s:select name="pihak.wargaNegara.kod" style="width:150px" value="${actionBean.pihak.wargaNegara.kod}"id="kod_warganegara">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                </s:select>
            </p>


            <%--<c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">--%>
            <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                 <p>
                                <label><font color="red">*</font>Tarikh Lahir :</label>
                                <s:text name="tarikhLahir" size="40" id="datepicker" class="datepicker" onchange="calAgeByDOB(this.value);"/>
                            </p>
                            <p>
                                <label><font color="red">*</font>Umur :</label>
                                <s:text name="pemohon.umur" size="10" maxlength="3"  id="umur" onkeyup="validateNumber(this,this.value);"/>

                <p>
                    <label><font color="red">*</font>Tempat Lahir : </label>
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
                <%--<p>
                    <label><font color="red">*</font> Anak Tempatan : </label>i
                    <s:select name="pihak.negeriKelahiran.kod" id="negeriLahir" value="kod" >
                        <s:option value="0">Sila Pilih</s:option>
                        <s:option value="Y">YA</s:option>
                        <s:option value="T">TIDAK</s:option>
                    </s:select>
                </p>--%>

                <%--<p>
                    <label><font color="red">*</font>Tempoh tinggal di Melaka : </label>
                    <s:text name="pemohon.tempohMastautin" size="5" maxlength="3" onkeyup="validateNumber(this,this.value);"/>&nbsp;TAHUN
                </p>--%>

                <p>
                    <label><font color="red">*</font>Pekerjaan :</label>
                    <s:text name="pemohon.pekerjaan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label><font color="red">*</font>Pendapatan Bulanan (RM) :</label>
                    <s:text name="pemohon.pendapatan" size="40" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                                <label><font color="red">*</font>Status Perkahwinan :</label>
                                <s:select name="pemohon.statusKahwin" value="${actionBean.pemohon.statusKahwin}" >
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option value="1">Bujang</s:option>
                                    <s:option value="2">Berkahwin</s:option>
                                </s:select>
                </p>
                <%--<p>
                    <label><font color="red">*</font>Status Perkahwinan :</label>
                    <s:text name="pemohon.statusKahwin" maxlength="10" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>--%>
                <p>
                    <label><font color="red">*</font>Tanggungan :</label>
                    <s:text name="pemohon.tanggungan" size="10" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                </p>
           </c:if>

                <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'S' || actionBean.pihak.jenisPengenalan.kod eq 'U'}">
                    <p>
                        <label><font color="red">*</font>Modal Berbayar (RM) :</label>
                        <s:text name="pihak.modalBerbayar" size="20" value="${actionBean.pihak.modalBerbayar}" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                </c:if>
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
                <%-- ${actionBean.pihak.alamat4}&nbsp;--%>
            </p>

            <%--
             <p>
                 <label> &nbsp;</label>
                 <s:text name="pihak.suratAlamat4" size="40"/>
             </p>--%>
            <p>
                <label><font color="red">*</font>Poskod :</label>
                <s:text name="pihak.poskod" value="${actionBean.pihak.poskod}" size="40" id="poskod" maxlength="5" onkeyup="validateNumber(this,this.value);"/>              </p>
             <p>
                <label><font color="red">*</font>Negeri :</label>
                <%--<s:select name="pihak.negeri.kod" id="negeri" >--%>
                <s:select name="kodNegeri1" id="negeri" >
                    <s:option value="">Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>
            </p>
            <p>
                <label>&nbsp;</label>
                <input type="checkbox" id="add1" name="add1" value="" onclick="copyAdd1();"/>
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

            <p>
                <label><font color="red">*</font>Poskod :</label>
                <s:text name="pihak.suratPoskod" value="${actionBean.pihak.suratPoskod}"id="suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label><font color="red">*</font>Negeri :</label>
                <s:select name="suratNegeri" id="suratNegeri" >
                    <s:option>Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>
            </p>
            <p>
                <label><font color="red">*</font>No. Telefon</label>
                <s:text name="pihak.noTelefon1" id="noTelefon1" value="${actionBean.pihak.noTelefon1}"size="40" onkeyup="validateNumber(this,this.value);"/>
            </p>

            <p>
                <label>No. Faksimili</label>
                <s:text name="pihak.noTelefon2" id="noTelefon2" value="${actionBean.pihak.noTelefon2}"size="40" onkeyup="validateNumber(this,this.value);"/>
            </p>

            <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                <p id="majikan">
                    <label>Nama/Majikan :</label>
                    <s:text name="pemohon.institusi" value="${actionBean.pemohon.institusi}" id="majikan" size="40" onkeyup="this.value=this.value.toUpperCase();" />
                </p>



            <p id="institusiAlamat1">
                <label for="alamat">Alamat Majikan: </label>
                <s:text name="pemohon.institusiAlamat1" id="institusiAlamat1" value="${actionbean.pemohon.institusiAlamat1}" size="40" onkeyup="this.value=this.value.toUpperCase();" />
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
                <s:text name="pemohon.institusiPoskod" value="${actionBean.pemohon.institusiPoskod}" id ="institusiPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value); "/>
            </p>
            <%--<p>
               <label>Negeri:</label>
               <s:text name="pemohon.pendapatan" size="40" onkeyup="validateNumber(this,this.value);"/>
            </p>--%>

              <p>
                    <label>Negeri :</label>
                    <s:select name="pemohon.institusiNegeri.kod" id="negeri"  value="${actionBean.pemohon.institusiNegeri.kod}">
                        <s:option value="">Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" />
                    </s:select>
                </p>
 </c:if>

            <p>
                <label>&nbsp;</label>
               <%-- <s:button name="simpanEditPemohon" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>--%>
                <s:submit name="simpanEditPemohon" id="simpan" value="Simpan" class="btn" />

                <label>&nbsp;</label>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>
    </div>
</s:form>
