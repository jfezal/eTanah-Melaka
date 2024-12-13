<%-- 
    Document   : tambah_latarbelakang_pemohon
    Created on : 14 May 2010
    Author     : Moha Hairudin Mansur
    Modified By: Rohan
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
            $('#kod_warganegara').val('MAL');
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

        function changeHideSuku(value){
            if(value == "S"){
                $('#suku').hide();
            }else{
                $('#suku').show();
            }
        }


        function savePemohonSuamiIsteri(event, f){
            alert("savePemohonSuami");
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.close();
            },'html');
        }


        function changeHideOnJenisPengenalan(value){
            if(value == "S"){
                $('#suku').hide();
                $('#modalBerbayar').show();
            }
            else{
                $('#suku').show();
                $('#modalBerbayar').hide();
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
                var url = '${pageContext.request.contextPath}/pelupusan/maklumat_pemohon_lps?deleteCawanganPemohon&idCawangan='+val;
                $.get(url,
                function(data){
                    $('#caw').html(data);
                },'html');
            }
        }

        function editCawanganPemohon(val){
            var url = '${pageContext.request.contextPath}/pelupusan/maklumat_pemohon_lps?editCawanganPemohon&idCawangan='+val;
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
    <%-- alert("va::"+va);
     alert("type::"+type);--%>
             if(va == 'B'){
    <%--alert("if va::"+va);--%>
                return doValidateAge(v, id, 'jenisPengenalan',type);
            }else {
                return true;
            }
        }
 
        function calAgeByDOB(value){
            var age = calculateAge(value.substring(0,2), value.substring(3,5), 20 + value.substring(8,10));
            $('#umur').val(age);
        }
            
        function addPemohon(){
            window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon_lps?showMaklumatAnak", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=710,height=400,scrollbars=yes");
        }

</script>
<style type="text/css">
    input.error { background-color: yellow; }
</style>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<div class="subtitle" id="caw">
    <s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPemohonLPSActionBean">

        <s:hidden name="pihak.nama"/>
        <s:hidden name="pihak.idPihak"/>
        <s:hidden name="pihak.jenisPengenalan.kod"/>
        <s:hidden name="pihak.jenisPengenalan.nama"/>
        <s:hidden name="pihak.noPengenalan"/>
        <s:hidden name="pihak.bangsa.kod"/>
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
        <div align="center">
            <s:errors/>
            <s:messages/>
        </div>
        <fieldset class="aras1">
            <legend>Kemasukan Maklumat Suami/Isteri Pemohon</legend>

            <p>
                <label for="nama"><font color="red">*</font>Nama :</label>
                <s:text name="pemohonHbgn.nama" id="nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                <s:select name="pemohonHbgn.jenisPengenalan.kod"  id="kod" onchange="javaScript:changeHideSuku(this.options[selectedIndex].text)" style="width:244px">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiJenisPengenalan}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
                <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                <s:text name="pemohonHbgn.noPengenalan" id="kp" size="40"/>
            </p>


            <p> <label><font color="red">*</font>Kaitan :</label>
                <s:select name="pemohonHbgn.kaitan" id="kaitan" style="width:244px" >
                    <s:option value="0">Sila Pilih</s:option>
                    <s:option value="SUAMI">SUAMI</s:option>
                    <s:option value="ISTERI">ISTERI</s:option>
                </s:select>
            </p>

            <p>
                <label><font color="red">*</font>Tarikh Lahir :</label>
                <s:text name="pemohonHbgn.tarikhLahir" size="40" id="tarikhLahir" class="datepicker" onchange="calculateUmar();" formatPattern="dd/mm/yyyy"/>
            </p>
            <p>
                <label><font color="red">*</font>Umur :</label>
                <s:text name="pemohonHbgn.umur" size="10" maxlength="3"  id="umur" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label><font color="red">*</font>Tempat Lahir :</label>
                <s:text name="pemohonHbgn.tempatLahir" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label><font color="red">*</font>Kewarganegaraan :</label>
                <s:select name="pemohonHbgn.warganegara.kod" style="width:244px">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                </s:select>
            </p>

            <p>
                <label><font color="red">*</font>Pekerjaan :</label>
                <s:text name="pemohonHbgn.pekerjaan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label for="alamat"><font color="red">*</font>Alamat Surat-Menyurat :</label>
                <s:text name="pemohonHbgn.alamat1" id="Alamat1" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pemohonHbgn.alamat2" id="Alamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pemohonHbgn.alamat3" id="Alamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pemohonHbgn.alamat4" id="Alamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>


            <p>
                <label><font color="red">*</font>Poskod :</label>
                <s:text name="pemohonHbgn.poskod" value="${actionBean.pemohonHbgn.poskod}"id="suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
            </p>

            <p id="negeri">
                <label for="Negeri"><font color="red">*</font>Negeri :</label>
                <s:select name="pemohonHbgn.negeri.kod" id="majikanNegeri" style="width:244px">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:submit name="simpanPemohonSuamiIsteri1" id="simpan" value="Simpan" class="btn"/>
                <s:button name=""  size="20"id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
            <br>
        </fieldset>
    </s:form>
</div>
