<%-- 
    Document   : pengguna_portal
    Created on : Jun 5, 2013, 1:07:02 PM
    Author     : faidzal
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/pub/styles/jquery.tooltip.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.tools.min.js?select=full&debug=true"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/passwordChecking/libs/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/passwordChecking/source/digitalspaghetti.password.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
            $('#jtek').hide();
            $('#kutipan').hide();
    });
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

    function test() {
        $(f).clearForm();
    }
    
        function change(value) {

        if (value == "0")
        {
            $('#jtek').show();
            $('#kutipan').hide();
 
        }
        if (value == "1")
        {
            $('#kutipan').show();
            $('#jtek').hide();
           
        }

    }

    function validateForm(f) {

        if(f.elements['pguna.idPengguna'].value == '') {
            alert('Sila masukkan Id Pengguna.');
            f.elements['pguna.idPengguna'].focus();
            return false;
            
        } else if(f.elements['pguna.nama'].value == '') {
            alert('Sila masukkan Nama.');
            f.elements['pguna.nama'].focus();
            return false;
            
        } else if(f.elements['pguna.noPengenalan'].value == '') {
            alert('Sila masukkan Kad Pengenalan.');
            f.elements['pguna.noPengenalan'].focus();
            return false;
            
        } else if(f.elements['pguna.jawatan'].value == '') {
            alert('Sila masukkan Jawatan.');
            f.elements['pguna.jawatan'].focus();
            return false;
        
        } else if(f.elements['pguna.kodJabatan.kod'].value == '') {
            alert('Sila masukkan Unit.');
            f.elements['pguna.kodJabatan.kod'].focus();
            return false;
        
        } else if(f.elements['pguna.alamat1'].value == '') {
            alert('Sila masukkan Alamat.');
            f.elements['pguna.alamat1'].focus();
            return false;

        } else if(f.elements['pguna.poskod'].value == '') {
            alert('Sila masukkan Poskod.');
            f.elements['pguna.poskod'].focus();
            return false;

        } else if(f.elements['pguna.negeri.kod'].value == 0) {
            alert('Sila pilih Negeri.');
            f.elements['pguna.negeri.kod'].focus();
            return false;
        
            //        } else if(f.elements['pguna.perananUtama.kod'].value == '') {
            //            alert('Sila pilih Peranan Utama.');
            //            f.elements['pguna.perananUtama.kod'].focus();
            //            return false;
            //        
        } 
        else if(f.elements['pguna.noTelefon'].value == '') {
            alert('Sila masukkan No Telefon Pejabat.');
            f.elements['pguna.noTelefon'].focus();
            return false;

        }else if(f.elements['pguna.email'].value == '') {
            alert('Sila masukkan Email.');
            f.elements['pguna.email'].focus();
            return false;

        } else if(f.elements['pguna.kodCawangan.kod'].value == '') {
            alert('Sila pilih Cawangan.');
            f.elements['pguna.kodCawangan.kod'].focus();
            return false;

        } else if(f.elements['pguna.penyelia.idPengguna'].value == 0) {
            alert('Sila pilih Penyelia.');
            f.elements['pguna.penyelia.idPengguna'].focus();
            return false;
        } else if(checkemail()) {
            return false;
        }
        else return true;
    }
    
    function checkId(f) {

        if(f.elements['pguna.idPengguna'].value == '') {
            alert('Sila masukkan Id Pengguna.');
            f.elements['pguna.idPengguna'].focus();
            return false;
            
        }
        else return true;
    }
    function checkemail(){
        var str= $('#email').val();
        var filter=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
        if (filter.test(str))
            testresults=false;
        else{
            alert("Sila masukkan email yang lengkap.");
            testresults=true;
        }
        return (testresults);
    }
    function checkUrusan(m){
        var xx = document.getElementById("kodTr"+m);
        if(xx.value != '0'){
            $('#nxt').removeAttr("disabled");
            $('#amt'+m).removeAttr("disabled");
            $('#amt'+m).focus();
        }
    }
    
    function doSomething2(caw,f){
        var jabatan = f.elements['pguna.kodJabatan.kod'].value ;
   
        var q = $(f).serialize();
        var url = '${pageContext.request.contextPath}/daftar_pengguna?searchByCawangan&cawangan='+caw;
        window.location = url+q;
    }
    
    function testts(jaw,f){
        var cawangan = $('#cawangan').val();
        var jabatan = $('#jabatan').val();
        //    alert('cawangan'+cawangan)
        //   alert(jaw);
        var q = $(f).serialize();
        var url = '${pageContext.request.contextPath}/daftar_pengguna?searchByJawatan&cawangan='+cawangan+'&jabatan='+jabatan+'&';
        window.location = url+q;
    }
    
    function dodacheck(val) {
        //var mikExp = /[1\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\1\|]/;
        var v = $('#noPengenalan').val();
        //            alert("test");
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
        }
    }
   

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.uam.PenggunaPortalActionBean" name="userForm" id="user_uam">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>


    <div id="hint"></div>
    <div id="strength"></div>
    <div class="subtitle" style="">


        <fieldset class="aras1">
            <legend>Maklumat Asas Pengguna</legend>

            <font size="1" color="Red"><em>Sila masukkan data Pengguna untuk membuat pendaftaran</em></font>
            <c:if test="${!actionBean.kemaskini}">
                <p>
                    <label><font color="red">*</font>Id Pengguna :</label>
                        <s:text name="portalPengguna.idPguna" style="width:250px"  id="nama" class="normal_text"/>

                </p>


            </c:if>
            <s:hidden name="portalPengguna.idPguna"/>
            <p>
                <label><font color="red">*</font>Nama :</label>
                    <s:text name="portalPengguna.nama" style="width:250px"  id="nama" class="normal_text"/>
            </p>

            <p>
                <label><font color="red">*</font>No Pengenalan :</label>
                    <s:text name="portalPengguna.noKp" id="kp" onkeyup="validateNumber(this,this.value);" maxlength="12" class="normal_text"/>
                <font size="1" color="red">(cth: 840706045494)</font>
            </p>


            <p>
                <label><font color="red">*</font>No Telefon :</label>
                    <s:text name="portalPengguna.noTel" style="width:250px" id="noTelPej" onkeyup="validateNumber(this,this.value);"/>
                <font size="1" color="red">(cth: 062843057)</font>
            </p>

            <p>
                <label><font color="red">*</font>Email :</label>
                    <s:text name="portalPengguna.email" style="width:250px" id="email" class="normal_text"/>
                <font size="1" color="red">(masukan emel yang sah untuk penerimaan id )</font>
            </p>
            <p>
                <label><font color="red">*</font>Status :</label>
                    <s:select name="portalPengguna.kodSts" style="width:250px">
                        <s:option value="" >Sila Pilih</s:option>
                    <s:option value="A" >Aktif</s:option>
                    <s:option value="X" >Tidak Aktif</s:option>


                </s:select>

            </p>
            <c:if test="${!actionBean.kemaskini}">
                                <p>
                    <label><font color="red">*</font>Kategori :</label>
                    <s:select name="Kategori" style="width:250px" onchange="javaScript:change(this.value)">
                            <s:option value="" >Sila Pilih</s:option>
                        <s:option value="0">Jabatan Teknikal</s:option>
                        <s:option value="1" >Kutipan Agensi</s:option>
                        
                    </s:select>
 

                </p>
                <div id="jtek">
                <p>
                    <label><font color="red">*</font>Agensi :</label>
                        <s:select name="portalPengguna.kodAgensi.kod" style="width:250px">
                            <s:option value="" >Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.lisJTEK}" value="kod" label="nama"/>
                    </s:select>
 

                </p>
                </div>
                 <div id="kutipan">
                           <p>
                    <label><font color="red">*</font>Agensi :</label>

                    <s:select name="portalPengguna.kodAgensiKutipan.kod" style="width:250px">
                            <s:option value="" >Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.listKutipanAgensi}" value="kod" label="nama"/>
                    </s:select>

                </p>
                </div>
            </c:if>
            <br>
            <p>
                <label>&nbsp;</label>

                <c:if test="${!actionBean.kemaskini}">
                    <s:submit name="newPengguna" id="save" value="Simpan" class="btn" onclick="return validateForm(this.form)"/>
                </c:if>
                <c:if test="${actionBean.kemaskini}">
                    <s:submit name="updatePengguna" id="save" value="Kemaskini" class="btn" onclick="return validateForm(this.form)"/>
                </c:if>

                <s:button  name="reset" value="Isi Semula" class="btn" onclick="clearText('user_uam');"/>
                <s:submit name="kembali" value="Kembali" class="btn"/>

            </p>
        </fieldset>

    </div>

</s:form>
