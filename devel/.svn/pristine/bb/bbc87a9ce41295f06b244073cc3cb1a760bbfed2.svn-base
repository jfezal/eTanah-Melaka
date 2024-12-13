<%--
    Document   : edit_maklumat_pembayar
    Created on : Dec 21, 2009, 7:07:28 PM
    Author     : nurfaizati
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        var flag = ${actionBean.editFlg};
        if(flag){
            self.close() ;
            self.opener.refreshPage1(this.form);
        }
    });
    function save1(f){
        alert(f);
        if(validationAddress()){
            return false;
        }
        else{
                return true;
        }
    }

</script>

<script type="text/javascript">
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

    function validationAddress() {
          if($("#alamat2").val() == ""){
            <%--alert('Sila masukkan " Alamat " terlebih dahulu.');--%>
            $("#alamat2").focus();
            return true;
        }

        if($("#alamat1").val() == ""){
            alert('Sila masukkan " Alamat " terlebih dahulu.');
            $("#alamat1").focus();
            return true;
        }
        <%--if($("#poskod").val() == ""){
            alert('Sila masukkan " Poskod " terlebih dahulu.');
            $("#poskod").focus();
            return true;
        }--%>
        if($("#negeri").val() == ""){
            alert('Sila pilih " Negeri " terlebih dahulu.');
            $("#negeri").focus();
            return true;
        }
        return false;
    }

    <%--$(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });--%>

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

</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.hasil.PertanyaanHakmilikActionBean" id= "pembayar">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pembayar</legend>
            <p><label>Nama :</label>
<%--                <s:text name="pihak.nama" id="alamat2" size="40"  onblur="this.value=this.value.toUpperCase();" />
--%>
                    ${actionBean.pihak.nama}&nbsp;
             
            </p>
            <p>
                <label>No Pengenalan :</label>
                ${actionBean.pihak.noPengenalan}&nbsp;
            </p>
            <p>
                <label>Alamat :</label>
                ${actionBean.pihak.alamat1}&nbsp;
            </p>
          <%--  <p>
                <label> &nbsp;</label>
                ${actionBean.pihak.alamat1}&nbsp;
            </p>--%>
            <p>
                <label> &nbsp;</label>
                ${actionBean.pihak.alamat2}&nbsp;
            </p>
            <p>
                <label> &nbsp;</label>
                ${actionBean.pihak.alamat3}&nbsp;
            </p>
            <p>
                <label>Bandar :</label>
                ${actionBean.pihak.alamat4}&nbsp;
            </p>
            <p>
                <label>Poskod :</label>
                ${actionBean.pihak.poskod}&nbsp;
            </p>
            <p>
                <label>Negeri :</label>
                ${actionBean.pihak.suratNegeri.nama}&nbsp;
            </p>

            <p>
                <label for="alamat"><em>*</em>Alamat Surat-Menyurat:</label>
                <s:text name="pihak.suratAlamat1"  id="alamat1" size="40"  onblur="this.value=this.value.toUpperCase();" />
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.suratAlamat2"   size="40"  onblur="this.value=this.value.toUpperCase();" />
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.suratAlamat3"   size="40"   onblur="this.value=this.value.toUpperCase();"/>

            </p>
            <p>
                <label>Bandar :</label>
                <s:text name="pihak.suratAlamat4"   size="40"  onblur="this.value=this.value.toUpperCase();" />
            </p>
            <p>
                <label>Poskod :</label>
                <s:text maxlength="5" name="pihak.suratPoskod" id="poskod" size="40" onkeyup="validateNumber(this,this.value);" />
            </p>

            <p>
                <label><em>*</em>  Negeri :</label>
                <s:select name="pihak.suratNegeri.kod" id="negeri" style="width:139px;">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" sort="nama"   onblur="this.value=this.value.toUpperCase();" />
                </s:select>
            </p>

            <p>
                <label for="alamat">Alamat Emel:</label>
                <s:text  name="pihak.email"  id="email" size="40" maxlength="100" onblur="return ValidateEmail()"/>

            </p>
            <p>
                <label for="alamat">Nombor Telefon:</label>
                <s:text  name="pihak.noTelefon1"  size="19" maxlength="12" onkeyup="validateNumber(this,this.value);"/>

            </p>

            <p>
                <label for="alamat">Nombor Telefon Bimbit:</label>

                <s:text name="pihak.noTelefonBimbit"  maxlength="12" onkeyup="validateNumber(this,this.value);"/>
                <s:hidden name="idHakmilik" value="idHakmilik"/>
            </p>


            <p>

            <div align="center">
                <s:submit name="save" value="Simpan" class="btn"  />
                 <%--<s:submit name="save" value="Tambah" class="btn" onclick="return save1(this.form);" />--%>
                 <s:button name="" value="Isi Semula" class="btn" onclick="clearText('pembayar');"/>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close();"/>
            </div>
            </p>
        </fieldset>
    </div>

</s:form>
