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
            
        } else if(f.elements['pguna.kodCawangan.kod'].value == '') {
    <c:if test="${!actionBean.melaka}">
                alert('Sila pilih Cawangan.');
    </c:if>
    <c:if test="${actionBean.melaka}">
                alert('Sila pilih Jabatan.');
    </c:if>
                f.elements['pguna.kodCawangan.kod'].focus();
                return false;

            } else if(f.elements['pguna.kodJabatan.kod'].value == '') {
    <c:if test="${!actionBean.melaka}">
                alert('Sila masukkan Jabatan.');
    </c:if>
    <c:if test="${actionBean.melaka}">
                alert('Sila masukkan Unit.');
    </c:if>
                f.elements['pguna.kodJabatan.kod'].focus();
                return false;
        
            } else if(f.elements['pguna.jawatan'].value == '') {
                alert('Sila masukkan Jawatan.');
                f.elements['pguna.jawatan'].focus();
                return false;
        
            }  else if(f.elements['pguna.alamat1'].value == '') {
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
                
            } else if(f.elements['pguna.noTelefon'].value == '') {
                alert('Sila masukkan No Telefon Pejabat.');
                f.elements['pguna.noTelefon'].focus();
                return false;

            } else if(f.elements['pguna.email'].value == '') {
                alert('Sila masukkan Email.');
                f.elements['pguna.email'].focus();
                return false;

            }  else if(f.elements['pguna.penyelia.idPengguna'].value == 0) {
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
   function isValidCharacter(txtTitle) {   
     var title = document.getElementById(txtTitle);
     var regExp = /^[a-zA-Z]*$/
     if (!regExp.test(title.value)) {
        title.value = '';
        return false;
        }
      else {      
           return true;
        }
   }


function Validation(){
 var txtTitles = document.getElementById('idpengguna');
  if (isValidCharacter(txtTitles.id) == false) {
   alert("Sila masukkan maklumat yang betul.Simbol atau karakter tidak dibenarkan");        
    return false;
  }  
 }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.uam.UserRegistrationBean" name="userForm" id="user_uam">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <s:hidden value="${actionBean.idPenggunaUser}" name="idPenggunaUser"  id="idPenggunaUser"/>


    <div id="hint"></div>
    <div id="strength"></div>
    <div class="subtitle" style="">
        <fieldset class="aras1">
            <legend>Maklumat Log Masuk</legend>
            <font size="1" color="Red"><em>Sila masukkan ID Pengguna</em></font>
            <p>
                <label><font color="red">*</font>Id Pengguna :</label>
                    <c:if test="${simpan}">
                        <s:text name="pguna.idPengguna" onkeyup="Validation();" onblur="this.value=this.value.toLowerCase();" id="idpengguna"/>
                    </c:if>
                    <c:if test="${!simpan}">
                        ${actionBean.idPenggunaUser}
                    </c:if>
					<font size="1" color="red">* Contoh Id Pengguna : rahim.hamzah</font>
            </p>
            <p>
                <label>&nbsp;</label>
                <c:if test="${simpan}">
                    <s:submit name="searchUser" id="search" value="Cari" class="btn" onclick="return checkId(this.form);"/>
                </c:if>
                <c:if test="${!simpan}">
                    <s:submit name="kembali1" value="Kembali" class="btn"/>
                </c:if>
            </p>
        </fieldset>
        <c:if test="${simpan}">
            <fieldset class="aras1">
                <legend>Maklumat Asas Pengguna</legend>

                <font size="1" color="Red"><em>Sila masukkan data Pengguna untuk membuat pendaftaran</em></font>
                <p>
                    <label><font color="red">*</font>Nama :</label>
                        <s:text name="pguna.nama" style="width:250px" onblur="this.value=this.value.toUpperCase();" id="nama"/>
                </p>              
                <p>
                    <label><font color="red">*</font>No Pengenalan :</label>
                        <s:text name="pguna.noPengenalan" id="kp" onkeyup="validateNumber(this,this.value);" maxlength="12"/>
                    <font size="1" color="red">(cth: 840706045494)</font>
                </p>
                <p> <c:if test="${!actionBean.melaka}">
                        <label><font color="red">*</font>Cawangan :</label></c:if>
                        <c:if test="${actionBean.melaka}">
                        <label><font color="red">*</font>Jabatan :</label></c:if>
                        <s:select id="cawangan" name="pguna.kodCawangan.kod" style="width:250px" onchange="doSomething2(this.value,this.form);">
                            <s:option value="" >Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodCawanganAktif}" value="kod" label="name" id="cawangan"/>
                    </s:select>
                </p>
                <p>
                    <c:if test="${!actionBean.melaka}">
                        <label><font color="red">*</font>Jabatan :</label></c:if>
                        <c:if test="${actionBean.melaka}">
                        <label><font color="red">*</font>Unit :</label></c:if>
                        <s:select id="jabatan" name="pguna.kodJabatan.kod" style="width:250px" onchange="doSomething2(this.value,this.form);">
                            <s:option value="" >Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodJabatan}" value="kod" label="nama" id="jabatan"/>
                    </s:select>
                </p>
                <p>
                    <label><font color="red">*</font>Jawatan :</label>
                        <s:select name="pguna.jawatan" style="width:397px">
                            <s:option value="" >Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodJawatan}" value="kod" label="nama" id="jawatan"/>
                    </s:select>
                </p>
                <p>
                    <label><font color="red">*</font>Alamat :</label>
                        <s:text name="pguna.alamat1" style="width:290px" onblur="this.value=this.value.toUpperCase();" id="alamat1"/>
                </p>
                <p><label>&nbsp;</label>
                    <s:text name="pguna.alamat2" style="width:290px" onblur="this.value=this.value.toUpperCase();" id="alamat2"/>
                </p>
                <p><label>&nbsp;</label>
                    <s:text name="pguna.alamat3" style="width:290px" onblur="this.value=this.value.toUpperCase();" id="alamat3"/>
                </p>
                <p>
                    <label><font color="red">*</font>Poskod :</label>
                        <s:text name="pguna.poskod" style="width:60px" id="poskod" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                    <label><font color="red">*</font>Negeri :</label>
                        <s:select name="pguna.negeri.kod" style="width:250px">
                            <s:option value="" >Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodNegeri}" value="kod" label="nama" id="negeri"/>
                    </s:select>
                </p>
                <p>
                    <label><font color="red">*</font>No Telefon Pejabat :</label>
                        <s:text name="pguna.noTelefon" style="width:250px" id="noTelPej" onkeyup="validateNumber(this,this.value);"/>
                    <font size="1" color="red">(cth: 062843057)</font>
                </p>
                <p>
                    <label>No Telefon Bimbit :</label>
                    <s:text name="pguna.noTelefonBimbit" style="width:100px" id="noTelBim" onkeyup="validateNumber(this,this.value);"/>
                    <font size="1" color="red">(cth: 0162843057)</font>
                </p>
                <p>
                    <label><font color="red">*</font>Email :</label>
                        <s:text name="pguna.email" style="width:250px" id="email"/>
                    <font size="1" color="red">(Sila masukan emel yang sah untuk penerimaan id )</font>
                </p> 
                <p>

                    <label><font color="red">*</font>Penyelia / Ketua Unit :</label>
                        <s:select name="pguna.penyelia.idPengguna" id="kodTr${row_rowNum-1}" onchange="checkUrusan('${row_rowNum-1}');">
                            <s:option value="0" label="Sila Pilih"/>
                            <c:forEach items="${actionBean.listPengguna}" var="c">
                                <s:option value="${c.idPengguna}" >${c.idPengguna} - ${c.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </p>
                <br>
                <p>
                    <label>&nbsp;</label>

                    <c:if test="${simpan}">
                        <s:submit name="newPengguna" id="save" value="Simpan" class="btn" onclick="return validateForm(this.form)"/>
                        <s:button  name="reset" value="Isi Semula" class="btn" onclick="clearText('user_uam');"/>
                        <s:submit name="kembali" value="Kembali" class="btn"/>
                    </c:if>

                </p>
            </fieldset>
        </c:if>
    </div>

</s:form>