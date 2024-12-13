<%-- 
    Document   : kod_sekolah_bantuan_popup
    Created on : Sep 8, 2010, 12:58:18 PM
    Author     : Programmer
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">

    function save(event1, f1){
        if(validation1()){
        }
        else{
            var q = $(f1).formSerialize();
            var url = f1.action + '?' + event1;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.opener.refreshPage3();
                self.close();
            },'html');
        }
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
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

    function validateTelLength(id,value){
    <%--alert("id :"+id);--%>
                var plength = value.length;
                if(plength < 7){
                    alert('No. Telefon/Fax yang dimasukkan salah.');
                    $('#'+id).val("");
                    $('#'+id).focus();
                }
            }

            function validatePoskodLength(value){
                var plength = value.length;
                if(plength != 5){
                    alert('Poskod yang dimasukkan salah.');
                    $('#poskod').val("");
                    $('#poskod').focus();
                }
            }

            function validation1() {
                if($("#kod").val() == ""){
                    alert('Sila masukkan " Kod " terlebih dahulu.');
                    $("#kod").focus();
                    return true;
                }
                if($("#nama").val() == ""){
                    alert('Sila masukkan " Nama " terlebih dahulu.');
                    $("#nama").focus();
                    return true;
                }
                if($("#alamat1").val() == ""){
                    alert('Sila masukkan " Alamat " terlebih dahulu.');
                    $("#alamat1").focus();
                    return true;
                }
                if($("#poskod").val() == ""){
                    alert('Sila masukkan " Poskod " terlebih dahulu.');
                    $("#poskod").focus();
                    return true;
                }
                if($("#negeri").val() == ""){
                    alert('Sila pilih " Negeri " terlebih dahulu.');
                    $("#negeri").focus();
                    return true;
                }
                return false;
            }

         function ValidateEmail(){
             var emailID= $("#email").val();

             if ((emailID==null)||(emailID=="")){
                 return true;
             }
             if ((emailID!=null)||(emailID!="")){
                 if(emailcheck(emailID)==false){
                     $("#email").val("");
                     $("#email").focus();
                     return false;
                 }
             }
             return true;
         }

         function emailcheck(str) {

             var at="@";
             var dot=".";
             var lat=str.indexOf(at);
             var lstr=str.length;
             var ldot=str.indexOf(dot);
             if (str.indexOf(at)==-1){
                 alert('"Alamat Email" salah');
                 return false;
             }

             if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
                 alert('"Alamat Email" salah');
                 return false;
             }

             if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
                 alert('"Alamat Email" salah');
                 return false;
             }

             if (str.indexOf(at,(lat+1))!=-1){
                 alert('"Alamat Email" salah');
                 return false;
             }

             if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
                 alert('"Alamat Email" salah');
                 return false;
             }

             if (str.indexOf(dot,(lat+2))==-1){
                 alert('"Alamat Email" salah');
                 return false;
             }

             if (str.indexOf(" ")!=-1){
                 alert('"Alamat Email" salah');
                 return false;
             }

             return true;
         }

         function validateKod(v){
    <%--alert("v :"+v);--%>
                  var url = "${pageContext.request.contextPath}/hasil/kodSekolahBantuan?doCheckKod&kod=" + v;
                  $.get(url,
                  function(data){
    <%--alert("data :"+data);--%>
                          if(data == 'Ada'){
                              alert("Kod "+v+" sudah digunakan. Sila buat carian.");
                              $("#kod").val("");
                          }
                      });
                  }
     
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form  name="kodsekolah" beanclass="etanah.view.stripes.hasil.KodSekolahBantuanActionBean">
    <s:messages/>
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Kod Sekolah Bantuan</legend>
            <c:if test="${New}">
                <p>
                    <label><em>*</em>Kod :</label>
                    <s:text id="kod" name="senaraiRuju.kod" size="20" maxlength="8" readonly="false" onchange="validateKod(this.value);"/>
                </p>
            </c:if>
            <c:if test="${!New}">
                <p>
                    <label><em>*</em>Kod :</label>
                    ${actionBean.senaraiRuju.kod}&nbsp;<s:hidden id="kod" name="senaraiRuju.kod"/>
                </p>
            </c:if>

            <p>
                <label><em>*</em>Nama :</label>
                <s:text id="nama" name="senaraiRuju.nama" size="50" maxlength="60" onblur="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label><em>*</em>Alamat :</label>
                <s:text id="alamat1" name="senaraiRuju.alamat.alamat1" size="50" maxlength="50" onblur="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label>&nbsp;</label>
                <s:text name="senaraiRuju.alamat.alamat2" size="50" maxlength="50" onblur="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label>&nbsp;</label>
                <s:text name="senaraiRuju.alamat.alamat3" size="50" maxlength="50" onblur="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label>&nbsp;</label>
                <s:text name="senaraiRuju.alamat.alamat4" size="50" maxlength="50" onblur="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label><em>*</em>Poskod :</label>
                <s:text id="poskod" name="senaraiRuju.alamat.poskod" size="20" maxlength="5" onkeyup="validateNumber(this,this.value);" onchange="validatePoskodLength(this.value);"/>
            </p>

            <p>
                <label for="Negeri"><em>*</em>Negeri :</label>
                <s:select id="negeri" name="negeriEdit" style="width:140px;">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select>
            </p>

            <p>
                <label>No. Tel 1 :</label>
                <s:text id="telefon" name="senaraiRuju.noTel1" size="20" maxlength="20" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.id,this.value);"/>
            </p>

            <p>
                <label>No. Tel 2 :</label>
                <s:text id="telefon2" name="senaraiRuju.noTel2" size="20" maxlength="20" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.id,this.value);"/>
            </p>

            <p>
                <label>No. Fax :</label>
                <s:text id="fax" name="senaraiRuju.nofax" size="20" maxlength="20" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.id,this.value);"/>
            </p>

            <p>
                <label>Email :</label>
                <s:text id="email" name="senaraiRuju.email" size="50" maxlength="50" onblur="return ValidateEmail()"/>
            </p>
            <p>
                <label>&nbsp;</label>
                &nbsp;
            </p>

            <p>
                <label>&nbsp;</label>
                <s:button name="simpanKod"  id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>&nbsp;
                <s:button name="tutup" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>
    </div>
</s:form>