<%-- 
    Document   : maklumat_tambahan_sbm
    Created on : Nov 19, 2009, 4:26:54 PM
    Author     : abu.mansur
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
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

    function validateTelLength(value){
        var plength = value.length;
        if(plength < 7){
            alert('No. Telefon yang dimasukkan salah.');
            $('#telefon').val("");
            $('#telefon').focus();
        }
    }

    function validation() {
        if($("#namaInst").val() == ""){
		alert('Sila masukkan " Nama Sekolah " terlebih dahulu.');
  		$("#namaInst").focus();
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

    function save(event, f){
            if(validation()){

            }
            else{
                doSubmit(f, event, 'page_div');
                <%--var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    self.opener.refreshPage();
                    self.close();
                },'html');--%>
            }
        }

    function refreshPageSekolah(){
        var url = '${pageContext.request.contextPath}/hasil/maklumat_tambah_SBM?refreshpage';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
    }

    function popupKodSekolah(f){
        var url = f.action + '?cari';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbar=1");
    }
</script>

<s:form beanclass="etanah.view.stripes.hasil.MaklumatTambahRemSBMActionBean" name="sbm_tambahan">
    <div class="subtitle">
        <%--<table width="100%" border="0" bgcolor="green">
            <tr>
                <td><h5>&nbsp;</h5></td>
                <td align="right">&nbsp;</td>
            </tr>
        </table>--%>
        <fieldset class="aras1">
            <legend>
                Maklumat Sekolah Bantuan Modal
            </legend>
            <p>
                <s:messages />
                <s:errors />&nbsp;
            </p>
            <div style="${actionBean.display}">
                <p align="center">
                    <s:button class="longbtn" onclick="popupKodSekolah(this.form);" name="cari" value="Cari Sekolah Bantuan"/>&nbsp;
                </p>
            </div>
            <p>
                <label><font color="red">*</font>Nama Sekolah :</label>
                <s:text id="namaInst" name="hakmilikAlamat.namaInst" size="40" maxlength="40"/>
            </p>
            <p>
                <label><font color="red">*</font>Alamat :</label>
                <s:text id="alamat1" name="hakmilikAlamat.alamat1" size="40" maxlength="40"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="hakmilikAlamat.alamat2" size="40" maxlength="40"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="hakmilikAlamat.alamat3" size="40" maxlength="40"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="hakmilikAlamat.alamat4" size="40" maxlength="40"/>&nbsp;<s:hidden name="hakmilikAlamat.idHakmilik" />
            </p>
            <p>
                <label><font color="red">*</font>Poskod :</label>
                <s:text id="poskod" name="hakmilikAlamat.poskod" size="19" maxlength="5" onkeyup="validateNumber(this,this.value);" onchange="validatePoskodLength(this.value);"/>
            </p>
            <p>
                <label><font color="red">*</font>Negeri :</label>
                <s:select id="negeri" name="kodNegeri" >
                    <s:option value="">Pilih ...</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>
            </p>
            <p>
                <label>No. Telefon :</label>
                <s:text id="telefon" name="hakmilikAlamat.noTel" size="19" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);"/>
            </p>
            <div style="${actionBean.display}">
                <p align="right">
                    <s:button class="btn" onclick="save(this.name, this.form);" name="saveOrUpdate" value="Simpan"/>&nbsp;
                    <%--<s:reset class="btn" name="reset" value="Isi Semula"/>--%>
                </p>
            </div>
        </fieldset>
    </div>
</s:form>
