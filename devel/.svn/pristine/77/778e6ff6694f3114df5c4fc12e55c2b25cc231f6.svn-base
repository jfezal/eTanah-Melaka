<%-- 
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : ${user}
--%>

<%--
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : ${user}
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
    function baru(f){
        if(validationAddress()){
            return false;
        }
        else{
			//alert("dh masuk sini");
			//$('#simpanBaru').click();
            //self.opener.refreshB123(f);
            //self.opener.refreshPage123(f);
			//$('#simpanBaru').click();
			//self.opener.refreshPage1(f);
            //self.close();
        }
    }
	
	function tutupRefresh(f){
		self.opener.refreshPage123(f);
		self.close();
	}

</script>
<script type="text/javascript">
    function validateNumberPengenalan(elmnt,content) { 	
        if($("#jenis").val() == "B"){
            //if it is character, then remove it..
            if (isNaN(content)) {
                elmnt.value = RemoveNonNumeric(content);
                return;
            }
        }
    }
	
	function validateNumber(elmnt,content) { 	
            //if it is character, then remove it..
            if (isNaN(content)) {
                elmnt.value = RemoveNonNumeric(content);
                return;
            }
    }

function copyAdd(){
                     if($('input[name=checkAlamat]').is(':checked')){
                         $('#alamatSurat1').val($('#alamat1').val());
                         $('#alamatSurat2').val($('#alamat2').val());
                         $('#alamatSurat3').val($('#alamat3').val());
                         $('#alamatSurat4').val($('#alamat4').val());
                         $('#poskodSurat').val($('#poskod').val());
                         $('#negeriSurat').val($('#negeri').val());
                     }else{
                         $('#alamatSurat1').val('');
                         $('#alamatSurat2').val('');
                         $('#alamatSurat3').val('');
                         $('#alamatSurat4').val('');
                         $('#poskodSurat').val('');
                         $('#negeriSurat').val('');
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
        if($("#nama").val() == ""){
            alert('Sila masukkan " Nama " terlebih dahulu.');
            $("#nama").focus();
            return true;
        }
            if($("#jenis").val() == ""){
            alert('Sila masukkan " Jenis Pengenalan " terlebih dahulu.');
            $("#jenis").focus();
            return true;
        }
			if($("#jenis").val() != "X"){
				if($("#noP").val() == ""){
					alert('Sila masukkan " No Pengenalan " terlebih dahulu.');
					$("#noP").focus();
					return true;
				}
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
        if($("#alamatSurat1").val() == ""){
            alert('Sila masukkan " Alamat Surat-Menyurat " terlebih dahulu.');
            $("#alamatSurat1").focus();
            return true;
        }        
        <%--if($("#poskodSurat").val() == ""){
            alert('Sila masukkan " Poskod " terlebih dahulu.');
            $("#poskodSurat").focus();
            return true;
        }--%>
        if($("#negeriSurat").val() == ""){
            alert('Sila pilih " Negeri " terlebih dahulu.');
            $("#negeriSurat").focus();
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
<script type="text/javascript">
    function bal1(f, x, pihak){
        var queryString = $(f).formSerialize();
        $.get("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?saveTambah&"+queryString+"&idHakmilik="+x+"&idPihak="+pihak,
        setTimeout(function(){
            self.close();
        }, 100));

    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.hasil.PertanyaanHakmilikActionBean" id="pembayar">
<s:messages/>
    <s:hidden name="noAkaun" value="${actionBean.noAkaun}"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pembayar</legend>
            <p><label><em>*</em>Nama :</label>
      
                <s:text id="nama" name="pihak.nama" size="40"  onblur="this.value=this.value.toUpperCase();" />&nbsp;

            </p>
                <p>
                <label><em>*</em>Jenis Pengenalan :</label>

                  <s:select name="pihak.jenisPengenalan.kod" id="jenis" style="width:139px;">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" sort="nama"   onblur="this.value=this.value.toUpperCase();" />
                </s:select>
            </p>
                  <p>
                <label><em>*</em>No Pengenalan :</label>
                <s:text maxlength="12" name="pihak.noPengenalan" id="noP" size="40" onkeyup="validateNumberPengenalan(this,this.value);" onblur="this.value=this.value.toUpperCase();"/>&nbsp;<em style="font-style:normal">(cth : 800620125177)</em>
                  <%--<s:text name="pihak.noPengenalan" id="noP" size="40"  onblur="this.value=this.value.toUpperCase();" />&nbsp;--%>
            </p>

            <p>
                <label><em>*</em>Alamat :</label>

                <s:text name="pihak.alamat1"  id="alamat1" size="40"  onblur="this.value=this.value.toUpperCase();" />
            </p>

            <p>
                <label> &nbsp;</label>

                  <s:text name="pihak.alamat2" id="alamat2"  size="40"  onblur="this.value=this.value.toUpperCase();" />

            </p>
            <p>
                <label> &nbsp;</label>

                  <s:text name="pihak.alamat3"  id="alamat3" size="40"  onblur="this.value=this.value.toUpperCase();" />
            </p>
            <p>
                <label>Bandar :</label>

                  <s:text name="pihak.alamat4" id="alamat4"  size="40"  onblur="this.value=this.value.toUpperCase();" />
            </p>
            <p>
                <label>Poskod :</label>

                  <s:text maxlength="5" name="pihak.poskod" id="poskod" size="40" onkeyup="validateNumber(this,this.value);" />
            </p>
            <p>
                <label><em>*</em>Negeri :</label>

                  <s:select name="pihak.negeri.kod" id="negeri" style="width:139px;">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" sort="nama"   onblur="this.value=this.value.toUpperCase();" />
                </s:select>
            </p>

            
                        <p>
                            <label>&nbsp;</label>
                            <input type="checkbox" id="checkAlamat" name="checkAlamat" value="No" onclick="copyAdd();"/>
                            <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                        </p>
                   

                    
            <p>
                <label for="alamat"><em>*</em>Alamat Surat-Menyurat :</label>
                <s:text name="pihak.suratAlamat1"  id="alamatSurat1" size="40"  onblur="this.value=this.value.toUpperCase();" />
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.suratAlamat2"  id="alamatSurat2" size="40"  onblur="this.value=this.value.toUpperCase();" />
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.suratAlamat3"  id="alamatSurat3" size="40"   onblur="this.value=this.value.toUpperCase();"/>

            </p>
            <p>
                <label>Bandar :</label>
                <s:text name="pihak.suratAlamat4"  id="alamatSurat4" size="40"  onblur="this.value=this.value.toUpperCase();" />
            </p>
            <p>
                <label>Poskod :</label>
                <s:text maxlength="5" name="pihak.suratPoskod" id="poskodSurat" size="40" onkeyup="validateNumber(this,this.value);" />
            </p>

            <p>
                <label><em>*</em>Negeri :</label>
                <s:select name="pihak.suratNegeri.kod" id="negeriSurat" style="width:139px;">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" sort="nama"   onblur="this.value=this.value.toUpperCase();" />
                </s:select>
            </p>

            <p>
                <label for="alamat">Alamat Emel :</label>
                <s:text  name="pihak.email"  id="email" size="40" maxlength="100" onblur="return ValidateEmail()"/>

            </p>
            <p>
                <label for="alamat">Nombor Telefon :</label>
                <s:text  name="pihak.noTelefon1"  size="19" maxlength="12" onkeyup="validateNumber(this,this.value);"/>

            </p>

            <p>
                <label for="alamat">Nombor Telefon Bimbit :</label>

                <s:text name="pihak.noTelefonBimbit"  maxlength="12" onkeyup="validateNumber(this,this.value);"/>
                <s:hidden name="idHakmilik" value="idHakmilik"/>
            </p>
            <p>
                <div align="center">
                     <s:submit name="saveBaru" value="Simpan" class="btn" onclick="return baru(this.form);" />
					 <%--<s:button name="" value="Simpan" class="btn" onclick="return baru(this.form);" />--%>
					 <%--<s:submit name="saveBaru" value="Simpan" class="btn" id="simpanBaru" style="display:none;"/>--%>
                     <s:button name="" value="Isi Semula" class="btn" onclick="clearText('pembayar');"/>
                    <%--<s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close();"/>--%>
					<s:button name="" id="tutup" value="Tutup" class="btn" onclick="return tutupRefresh(this.form);"/>
                </div>
            </p>

           </fieldset>
    </div>

</s:form>
