<%-- 
    Document   : maklumat_pemohon_tambah
    Created on : Apr 14, 2010, 3:00:45 PM
    Author     : mazurahayati.yusop
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
    function validatePoskodLength(value){
        var plength = value.length;
        if(plength != 5){
            alert('Poskod yang dimasukkan salah.');
            $('#poskod').val("");
            $('#poskod').focus();
        }
    }

    function simpanPihak(event, f){
            if(validation()){

            }
            else{
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.opener.refreshPage();
            self.close();
        },'html');
    }
        }

       function validation() {
        if($("#nama").val() == ""){
		alert('Sila masukkan " Nama " terlebih dahulu.');
  		$("#nama").focus();
		return true;
	}
        if($("#pengenalan").val() == ""){
		alert('Sila masukkan " No. Pengenalan " terlebih dahulu.');
  		$("#pengenalan").focus();
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
        if($("#telefon").val() == ""){
		alert('Sila masukkan " No. Telefon " terlebih dahulu.');
  		$("#telefon").focus();
		return true;
	}
        if($("#pihak").val() == ""){
		alert('Sila pilih " Jenis Pihak " terlebih dahulu.');
  		$("#pihak").focus();
		return true;
	}
        
        return false;
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


<s:form name="pemohon" beanclass="etanah.view.stripes.lelong.MaklumatPemohonLActionBean">
    <s:messages/>
    <s:errors/>&nbsp;
    <s:hidden name="pihak.idPihak"/>
    <s:hidden name="permohonanPihak.idPermohonanPihak"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pemohon
            </legend>
            <div class="content">
                
                <p>
                    <label> Nama :</label>
                    <s:text id="nama" name="nama" onchange="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> No. Pengenalan :</label>
                    <s:text id="pengenalan" name="noPengenalan"/>
                </p>
                 <p>
                       <label>Alamat: </label>
                       <s:text id="alamat1" name="alamat1" onchange="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                       <label>&nbsp;</label>
                       <s:text name="alamat2" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                </p>
                <p>
                       <label>&nbsp;</label>
                       <s:text name="alamat3" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                </p>
                <p>
                       <label>&nbsp;</label>
                       <s:text name="alamat4" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                </p>
                <p>
                       <label>Poskod :</label>
                       <s:text id="poskod" name="poskod" size="19" maxlength="5" onkeyup="validateNumber(this,this.value);" onchange="validatePoskodLength(this.value);"/>&nbsp;
                </p>
                <p>
                    <label> Negeri :</label>
                    <s:select id="negeri" name="negeri">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" onchange="this.value=this.value.toUpperCase();"/>
                    </s:select>
                </p>
                <p>
                    <label> No. Telefon :</label>
                    <s:text id="telefon" name="noTelefon" size="19" maxlength="12" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                    <label> Alamat Email :</label>
                    <s:text id="email" name="email" size="40" maxlength="100" onblur="return ValidateEmail()"/>
                </p>
                <p>
                    <label> Jenis Pihak :</label>
                    <s:select id="pihak" name="jenis">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodJenisPihakBerkepentingan}" label="nama" value="kod"/>
                    </s:select>
                </p>


            </div>
        </fieldset>
    </div>
    <div class="content" align="center">
        <p>
            <s:button name="save" id="simpan" value="Simpan" class="btn" onclick="simpanPihak(this.name, this.form);"/>&nbsp;
            <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
        </p>
    </div><br>

</s:form>

