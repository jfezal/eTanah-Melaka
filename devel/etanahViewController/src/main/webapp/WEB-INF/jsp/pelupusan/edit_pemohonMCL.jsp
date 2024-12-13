<%--
    Document   : edit_pemohonMCL
    Created on : Julai 30, 2010, 4:23:38 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function save(event, f){
        
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    self.opener.refreshPage();
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

     function validateTelLength(value){
        var plength = value.length;
        if(plength < 7){
            alert('No. Telefon yang dimasukkan salah.');
            $('#telefon').val("");
            $('#telefon').focus();
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

    function validation() {
	<%--if($("#alamat1").val() == ""){
		alert('Sila masukkan " Alamat " terlebih dahulu.');
  		$("#alamat1").focus();
		return false;
	}--%>
        if(document.edit.alamat1.value == "" || document.edit.alamat2.value == "" || document.edit.alamat3.value == ""  || document.edit.alamat4.value == "" ){
		alert('Sila pilih " Alamat " terlebih dahulu.');
  		document.edit.negeri.value.focus();
		return false;
	}
        if($("#poskod").val() == ""){
		alert('Sila masukkan " Poskod " terlebih dahulu.');
  		$("#poskod").focus();
		return false;
	}
        if(document.edit.suratNegeri.value == "Pilih..."){
		alert('Sila pilih " Negeri " terlebih dahulu.');
  		document.edit.negeri.value.focus();
		return false;
	}
        return true;
    }

    $(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });

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

<s:form beanclass="etanah.view.stripes.pelupusan.CatitTanahMCL_ActionBean" name="edit">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pemohon
            </legend>
            <p>
                <font color="red">  Wajib isikan maklumat yang bertanda * </font>
            </p>
            <p>
                <label>Nama :</label>
                ${actionBean.pihak.nama}&nbsp;<s:hidden name="pihak.idPihak"/>
            </p>
            <p>
                <label>Nombor Pengenalan :</label>
                ${actionBean.pihak.noPengenalan}&nbsp;
            </p>


            <p>
                <label><em>*</em>Alamat Surat-Menyurat :</label>
                <s:text name="pihak.suratAlamat1" size="40" id="alamat1"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.suratAlamat2" size="40" id="alamat2"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.suratAlamat3" size="40" id="alamat3"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.suratAlamat4" size="40" id="alamat4"/>
            </p>
            <p>
                <label><em>*</em>Poskod :</label>
                <s:text name="pihak.suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);" onchange="validatePoskodLength(this.value);"/>
            </p>
            <p>
                <label><em>*</em>Negeri :</label>
                <s:select name="suratNegeri" value="${actionBean.pihak.suratNegeri.kod}" id="negeri">
                    <s:option>Pilih...</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>
            </p>
            <p>
                <label>No. Telefon :</label>
                <s:text id="telefon" name="pihak.noTelefon1" size="19" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);"/>
            </p>
            <p>
                <label>Alamat Email :</label>
                <s:text id="email" name="pihak.email" size="40" maxlength="100" onblur="return ValidateEmail()"/>
            </p>
            <p>

                <label>&nbsp;</label>
                 <s:button name="updatePihak" id="simpan" value="Simpan" class="btn" onclick="if(validation())save(this.name, this.form);"/>
               <%-- <label>&nbsp;</label>
                <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;--%>
                <label>&nbsp;</label>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>

        </fieldset >
    </div>

</s:form>