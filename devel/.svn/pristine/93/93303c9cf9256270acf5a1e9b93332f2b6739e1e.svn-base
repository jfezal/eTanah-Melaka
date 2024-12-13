<%-- 
    Document   : kemasKini_profile
    Created on : Jul 4, 2011, 6:17:24 PM
    Author     : amir.muhaimin
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
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

    function test(f) {
        $(f).clearForm();
    }

    function validateForm(f) {
        if(f.elements['nama'].value == '') {
            alert('Sila masukkan Nama.');
            return false;

        } else if(f.elements['noPengenalan'].value == '') {
            alert('Sila masukkan No Pengenalan.');
            return false;
            
        } else if(f.elements['cawangan'].value == '') {
            if (${!actionBean.melaka}){
                alert('Sila masukkan Cawangan.');
            }
            else if(${!actionBean.melaka}){
                alert('Sila masukkan Jabatan');
            }
            f.elements['cawangan'].focus();
            return false;

        } else if(f.elements['jabatan'].value == '') {
            if(${!actionBean.melaka}){
                alert('Sila masukkan Jabatan');
            }
            else if(${actionBean.melaka}){
                alert('Sila masukkan Unit');
            }
            f.elements['jabatan'].focus();
            return false;
            
        } else if(f.elements['kodJawatan'].value == 0) {
            alert('Sila masukkan Jawatan.');
            f.elements['kodJawatan'].focus();
            return false; 
        
//        }else if((f.elements['jabatan'].value == '16')||(f.elements['jabatan'].value == '17')){
//            if(f.elements['pguna.idKaunter'].value == ''){
//                alert("Sila masukkan Id Kaunter.");
//                f.elements['pguna.idKaunter'].focus();
//                return false;
//            }
//            return true;
            
        }else if(f.elements['alamat1'].value == '') {
            alert('Sila masukkan Alamat.');
            return false;

        } else if(f.elements['pguna.poskod'].value == '') {
            alert('Sila masukkan Poskod.');
            f.elements['poskod'].focus();
            return false;

        } else if(f.elements['negeri'].value == 0) {
            alert('Sila pilih Negeri.');
            f.elements['negeri'].focus();
            return false;
        
        } else if(f.elements['cawangan'].value == '') {
            alert('Sila pilih Cawangan.');
            f.elements['cawangan'].focus();
            return false;
            
        } else if(f.elements['pguna.noTelefon'].value == '') {
            alert('Sila pilih No Telefon Pejabat.');
            f.elements['noTelefon'].focus();
            return false;
        
        } else if(f.elements['pguna.email'].value == '') {
            alert('Sila pilih Email.');
            f.elements['email'].focus();
            return false;
        }
       
        else return true;
    }
    
    function validateEmail()
    {
        var x=document.forms["kemaskini"]["email"].value;
        var atpos=x.indexOf("@");
        var dotpos=x.lastIndexOf(".");
        if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length)
        {
            alert('Sila masukkan email yang lengkap.');
            return false;
        }
    }
    
    var browserType;

    if (document.layers) {browserType = "nn4"}
    if (document.all) {browserType = "ie"}
    if (window.navigator.userAgent.toLowerCase().match("gecko")) {
        browserType= "gecko"
    }
    
        function doSomething2(caw,f){
 
        var q = $(f).serialize();
        var url = '${pageContext.request.contextPath}/uam/kemaskini_profil?searchByCawangan&';
        window.location = url+q;
        
    }
    function filerUnit(f){
        if(f == '16' || f == '17'){
            if (browserType == "gecko" )
                document.poppedLayer =
                eval('document.getElementById("hideshow")');
            else if (browserType == "ie")
                document.poppedLayer =
                eval('document.getElementById("hideshow")');
            else
                document.poppedLayer =
                eval('document.layers["hideshow"]');
            document.poppedLayer.style.visibility = "visible";
        }
        else{
            if (browserType == "gecko" )
                document.poppedLayer =
                eval('document.getElementById("hideshow")');
            else if (browserType == "ie")
                document.poppedLayer =
                eval('document.getElementById("hideshow")');
            else
                document.poppedLayer =
                eval('document.layers["hideshow"]');
            document.poppedLayer.style.visibility = "hidden";
            
        }
        
    }
    $(document).ready( function(){
        
        var f = document.getElementById("jabatan1").options[document.getElementById("jabatan1").selectedIndex].value;
        //        alert("f 1: "+f);
        if(f == '16' || f == '17'){
            if (browserType == "gecko" )
                document.poppedLayer =
                eval('document.getElementById("hideshow")');
            else if (browserType == "ie")
                document.poppedLayer =
                eval('document.getElementById("hideshow")');
            else
                document.poppedLayer =
                eval('document.layers["hideshow"]');
            document.poppedLayer.style.visibility = "visible";
        }
    });

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.uam.KemasKiniProfilBean" name="kemaskini" onsubmit="return validateEmail();" method="post">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Id Pengguna</legend>
            <font size="1" color="Red">Sila masukkan data Pengguna untuk membuat kemaskini data</font>
            <p>
                <label><font color="red">*</font>Id Pengguna :</label>
                    ${actionBean.pguna.idPengguna}
            </p>
            <br>
        </fieldset >
        <fieldset class="aras1">
            <legend>Maklumat Asas Pengguna</legend>
            <p>
                <%-- <label><font color="red">*</font>Nama :</label>
<s:text name="nama" style="width:250px" onblur="this.value=this.value.toUpperCase();"/>--%>
                <label><font color="red">*</font>Nama :</label>
                    ${actionBean.pguna.nama}
            </p>
            <p>
                <label><font color="red">*</font>No Pengenalan :</label>
                    <s:text name="nokp" id="kp" onkeyup="validateNumber(this,this.value);" maxlength="12" style="width:100px"/>
                <font size="1" color="red">(cth: 840706045393)</font>
            </p>
                <p> <c:if test="${!actionBean.melaka}">
                        <label><font color="red">*</font>Cawangan :</label></c:if>
                        <c:if test="${actionBean.melaka}">
                        <label><font color="red">*</font>Jabatan :</label></c:if>
                        <s:select id="cawangan" name="cawangan" style="width:250px" onchange="doSomething2(this.value,this.form);">
                            <s:option value="" >Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodCawanganAktif}" value="kod" label="name" id="cawangan"/>
                    </s:select>
                </p>
                <p>
                    <c:if test="${!actionBean.melaka}">
                        <label><font color="red">*</font>Jabatan :</label></c:if>
                    <c:if test="${actionBean.melaka}">
                        <label><font color="red">*</font>Unit :</label></c:if>
                        <s:select id="jabatan" name="jabatan" style="width:250px" onchange="doSomething2(this.value,this.form);">
                            <s:option value="" >Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodJabatan}" value="kod" label="nama" id="jabatan"/>
                    </s:select>
                </p>
            <p>
                <%--<label><font color="red">*</font>Jawatan :</label>
                    <s:select name="kodJawatan" style="width:397px" >
                        <s:option value="" >Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodJawatan}" value="kod" label="nama"/>
                </s:select>--%>
                 <label><font color="red">*</font>Jawatan :</label>
                    ${actionBean.namaJawatan}
            </p>
<!--            <div id="hideshow" style="visibility: hidden">
                <p>
                    <label><font color="red">*</font>Id Kaunter :</label>
                        <s:text name="idKaunter" maxlength="2" style="width:30px" id="idKaunter"/>
                </p>
            </div>-->
            <p>
                <label><font color="red">*</font>Alamat :</label>
                    <s:text name="alamat1" style="width:290px" onblur="this.value=this.value.toUpperCase();" id="alamat1"/>
            </p>
            <p><label>&nbsp;</label>
                <s:text name="alamat2" style="width:290px" onblur="this.value=this.value.toUpperCase();" id="alamat2"/>
            </p>
            <p><label>&nbsp;</label>
                <s:text name="alamat3" style="width:290px" onblur="this.value=this.value.toUpperCase();" id="alamat3"/>
            </p>
            <p>
                <label><font color="red">*</font>Poskod :</label>
                    <s:text name="poskod" style="width:60px" onblur="this.value=this.value.toUpperCase();" onkeyup="validateNumber(this,this.value);" maxlength="5"/>
            </p>
            <p>
                <label><font color="red">*</font>Negeri :</label>
                    <s:select name="negeri" style="width:250px">
                        <s:option value="" >Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" value="kod" label="nama"/>
                </s:select>
            </p>
            <p>
                <label><font color="red">*</font>No Telefon Pejabat :</label>
                    <s:text name="noTelefon" style="width:100px" onblur="this.value=this.value.toUpperCase();" onkeyup="validateNumber(this,this.value);"/>
                <font size="1" color="red">(cth: 062843057)</font>
            </p>
            <p>
                <label>No Telefon Bimbit :</label>
                <s:text name="noTelefonBimbit" style="width:100px" onkeyup="validateNumber(this,this.value);"/>
                <font size="1" color="red">(cth: 0162843057)</font>
            </p>
            <p>
                <label><font color="red">*</font>Email :</label>
                    <s:text name="email" style="width:250px"/>
            </p>
                        
                            <p>

                    <label><font color="red">*</font>Penyelia / Ketua Unit :</label>
                        <s:select name="penyelia" id="kodTr${row_rowNum-1}" onchange="checkUrusan('${row_rowNum-1}');">
                            <s:option value="0" label="Sila Pilih"/>
                            <c:forEach items="${actionBean.listPengguna}" var="c">
                                <s:option value="${c.idPengguna}" >${c.idPengguna} - ${c.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </p>
            <br>
        </fieldset>
        <br />
        <p>
            <label>&nbsp;</label>

            <c:if test="${simpan}">
                <s:submit name="editPengguna" id="save" value="Simpan" class="btn" onclick="return validateForm(this.form)"/>
                <s:hidden name="pguna.idPengguna"/>
            </c:if>
            <s:submit name="showForm" value="Isi Semula" class="btn" onclick="test(this.form);"/>
        </p>
    </div>
</s:form>