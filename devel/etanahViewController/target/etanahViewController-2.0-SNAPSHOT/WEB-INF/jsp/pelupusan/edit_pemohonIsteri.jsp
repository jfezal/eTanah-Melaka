<%--
    Document   : edit_pemohonIsteri
    Created on : Feb 12, 2010, 12:04:35 PM
    Author     : muhammad.amin
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
                var v = '${actionBean.pemohonHubungan.jenisPengenalan.kod}';

                $('form').submit(function(){
                    var val = $('#kp').val();
                    var jenis = '${jenis}';
                    return doCheckUmur(val, 'kp',jenis);
                });

                if(v == "B"){
                    var icNo = '${actionBean.pemohonHubungan.noPengenalan}';
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


      function saveMaklumatIsteri(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
        },'html');
    }

<%--
    function saveMaklumatIsteri(event, f){
           var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.opener.refreshPageMaklumatPemohon();
                alert("self");
                self.close();
            },'html');
        }
--%>
   <%--function save(event, f){
       alert('Simpan');
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.opener.refreshPageMaklumatPemohon();
                self.close();
            },'html');
        }--%>
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
<div class="subtitle" >
    <s:errors></s:errors>
    <s:messages></s:messages>
<s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPemohonActionBean">

        <fieldset class="aras1">
            <legend>
                Maklumat Suami/Isteri Pemohon
            </legend>
            <s:hidden name="isteri" value="edit_pemohonIsteri"/>
            <p>
                <label><font color="red">*</font>Nama Suami/Isteri :</label>
                <s:text name="pemohonHubungan.nama" value=" ${actionBean.pemohonHubungan.nama}" onkeyup="this.value=this.value.toUpperCase();"></s:text>
                <s:hidden name="pemohonHubungan.idHubungan" ></s:hidden>
            </p>
            <p>
                <label><font color="red">*</font>Jenis Pengenalan :</label>
                ${actionBean.pihak.jenisPengenalan.nama}&nbsp;
                <s:hidden name="pihak.jenisPengenalan.nama"></s:hidden>
                <%--<s:text name="pihak.idPihak"/>--%>
            </p>
             <p>
                <label>Nombor Pengenalan :</label>
                ${actionBean.pemohonHubungan.noPengenalan}&nbsp;
                <s:hidden name="pemohonHubungan.noPengenalan"></s:hidden>
            </p>
            <%--<p>
                <label><font color="red">*</font>Nombor Pengenalan :</label>
                ${actionBean.pemohonHubungan.noPengenalan}&nbsp;
                <s:hidden name="pemohonHubungan.noPengenalan"/>
                <s:text name="pemohonHubungan.idHubungan"/>
            </p>--%>
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
              </p>--%>

                        <p>
                            <label><font color="red">*</font>Bangsa / Jenis Syarikat :</label>
                            <s:select name="pemohonHubungan.bangsa.kod" value="${actionBean.pemohonHubungan.bangsa.kod}" id="bangsa" style="width:275px">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodBangsa}" label="nama" value="kod" />
                            </s:select>
                        </p>
                       <p>
                            <label><font color="red">*</font>Kewarganegaraan :</label>
                            <s:select name="pemohonHubungan.wargaNegara.kod" style="width:150px" id="kod_warganegara">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                            </s:select>
                        </p>

                   <p>
                                <label><font color="red">*</font>Tarikh Lahir :</label>
                                <s:text name="tarikhLahir" size="40" id="datepicker" class="datepicker" onchange="calAgeByDOB(this.value);"/>
                            </p>
<%--         <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
--%>
                            <p>
                                <label><font color="red">*</font>Umur :</label>
                                <s:text name="pemohonHubungan.umur" value="${actionBean.pemohonHubungan.umur}"size="10" maxlength="3" id="umur" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                <p>
                <label><font color="red">*</font>Tempat Lahir : </label>
                <s:text name="pemohonHubungan.tempatLahir" size="40" onkeyup="this.value=this.value.toUpperCase();"/>

            </p>


            <p>
                                <label><font color="red">*</font>Kaitan :</label>
                                <s:select name="pemohonHubungan.kaitan" id="kaitan" >
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option value="SUAMI">SUAMI</s:option>
                                    <s:option value="ISTERI">ISTERI</s:option>
                                </s:select>
        </p>


                <p>
                    <label><font color="red">*</font>Pekerjaan :</label>
                    <s:text name="pemohonHubungan.pekerjaan" size="40"  onkeyup="this.value=this.value.toUpperCase();"/>
                   <%-- <s:text name="pemohon.idPemohon" />--%>
                </p>
                <p>
                    <label><font color="red">*</font>Pendapatan Bulanan (RM) :</label>
                    <s:text name="pemohonHubungan.gaji"  size="40" onkeyup="validateNumber(this,this.value);"/>

                </p>

<%--            </c:if>
--%>
                 <p>
                                <label for="alamat"><font color="red">*</font>Alamat Berdaftar :</label>
                                <s:text name="pemohonHubungan.alamat1" value="${actionBean.pemohonHubungan.alamat1}"size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label> &nbsp;</label>
                               <s:text name="pemohonHubungan.alamat2" value="${actionBean.pemohonHubungan.alamat2}"size="40"onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label> &nbsp;</label>
                                <s:text name="pemohonHubungan.alamat3" value="${actionBean.pemohonHubungan.alamat3}"size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label> &nbsp;</label>
                                <s:text name="pemohonHubungan.alamat4" value="${actionBean.pemohonHubungan.alamat4}"size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                               <%-- ${actionBean.pihak.alamat4}&nbsp;--%>
                            </p>

            <p>
                <label><font color="red">*</font>Poskod :</label>
                <s:text name="pemohonHubungan.poskod" value="${actionBean.pihakCawangan.poskod}"size="40" id="poskodCaw" maxlength="5" onkeyup="validateNumber(this,this.value);"/>              </p>
            <p>
                <label><font color="red">*</font>Negeri :</label>
                <s:select name="pemohonHubungan.negeri.kod" >
                    <s:option value="">Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>
            </p>

           <p>
                <label>Nama/Majikan </label>
                <s:text name="pemohonHubungan.institusi" value="${actionBean.pemohon.pendapatan}"size="40" onkeyup="this.value=this.value.toUpperCase();"/>
             </p>
            <%--  <p>
                <label><font color="red">*</font>Alamat Majikan:</label>
                <s:text name="pemohon.pendapatan" size="40" onkeyup="validateNumber(this,this.value);"/>
             </p>--%>

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

             <p>
                    <label>Poskod :</label>
                    <s:text name="pemohonHubungan.institusiPoskod" value="{actionBean.pihakCawangan.suratPoskod}"id="suratPoskodCaw" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                </p>

                   <p>
                <label>Negeri :</label>
                <s:select name="pemohonHubungan.institusiNegeri.kod" >
                    <s:option value="">Pilih </s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>
            </p>


            <p>
               <label>&nbsp;</label>
                <%--<s:button name="simpanIsteri" id="simpan" value="Simpan111" class="btn" onclick="saveMaklumatIsteri(this.name, this.form);"/>--%>
                <s:submit name="simpanIsteri" id="simpan" value="Simpan" class="btn"/>
<%--<s:button name="simpanEditPemohon" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
--%>                <label>&nbsp;</label>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>


        </fieldset>
   </div>
 </s:form>
