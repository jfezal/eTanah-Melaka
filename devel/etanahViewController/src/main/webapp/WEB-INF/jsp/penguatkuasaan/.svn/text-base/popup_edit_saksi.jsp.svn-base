<%-- 
    Document   : popup_edit_saksi
    Created on : Feb 9, 2011, 10:03:37 AM
    Author     : sitifariza.hanim
--%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
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
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        jenisPengenalanSaksi();
    });

    function validateForm2(){

        if(document.form3.nama.value=="")
        {
            alert("Sila masukkan Nama");
            return true;
        }
        if(document.form3.kp.value=="")
        {
            alert("Sila pilih Jenis Pengenalan");
            return true;
        }
        if(document.form3.noPengenalan.value=="")
        {
            alert("Sila masukkan No.Pengenalan");
            return true;
        }
        if(document.form3.alamat1.value=="")
        {
            alert("Sila masukkan Alamat");
            return true;
        }
        if(document.form3.poskod.value=="")
        {
            alert("Sila masukkan Poskod");
            return true;
        }
        if(document.form3.kodNegeriOrg.value=="")
        {
            alert("Sila pilih Negeri");
            return true;
        }
        if(document.form3.noTelefon.value=="")
        {
            alert("Sila masukkan No.Telefon");
            return true;
        }
        return false;

    }
    
    function validateNumber(elmnt,content) {

        //if it is fullstop (.) , then remove it..
        for( var i = 0; i < content.length; i++ )
        {
            var str = "";
            str = content.substr( i, 1 );
            if(str == "."){
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }

        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }

    }
    <%-- function validateNumber(elmnt,content) {
         //if it is character, then remove it..
         if (isNaN(content)) {
             elmnt.value = removeNonNumeric(content);
             return;
         }
     }--%>

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

         function validateTelLength(value){
             var plength = value.length;
             if(plength < 7){
                 alert('No. Telefon yang dimasukkan salah.');
                 $('#telefon').val("");
                 $('#telefon').focus();
             }
         }

         function simpan2(event, f){

             if(validateForm2()){

             }else{
                 alert("Anda pasti untuk simpan?");
                 var q = $(f).formSerialize();
                 var url = f.action + '?' + event;
                 var idOp = $('#idOp').val();
                 $.post(url,q,
                 function(html){
                     if(idOp == ""){
                         $("#senaraiPasukanDiv",opener.document).replaceWith($('#senaraiPasukanDiv', $(html)));
                     }else{
                         $("#senaraiSaksiLuarDiv",opener.document).replaceWith($('#senaraiSaksiLuarDiv', $(html)));
                     }
                 },'html');
                 if(idOp == ""){
                     self.opener.refreshListSaksi();
                 }else{
                     self.opener.refreshListSaksiMultipleOp();
                 }
                 self.close();
             }
         }

         function jenisPengenalanSaksi(){
             if($('#pengenalanSaksi').val() == 'B'){
                 document.getElementById("noPengenalanBaruSaksi").style.visibility = 'visible';
                 document.getElementById("noPengenalanBaruSaksi").style.display = '';
                 $('#noPengenalanLainSaksi').hide();

                 $('#penyerahNoPengenalanLainSaksi').attr("disabled", true);
                 $('#penyerahNoPengenalanBaruSaksi').attr("disabled", false);

             }else{
                 $('#noPengenalanLainSaksi').show();
                 document.getElementById("noPengenalanBaruSaksi").style.visibility = 'hidden';
                 document.getElementById("noPengenalanBaruSaksi").style.display = 'none';

                 $('#penyerahNoPengenalanBaruSaksi').attr("disabled", true);
                 $('#penyerahNoPengenalanLainSaksi').attr("disabled", false);
             }
         }
         
    function simpanSaksiOks(event, f){
alert("hii");
             if(validateForm2()){

             }else{
                 alert("Anda pasti untuk simpan?");
                 var q = $(f).formSerialize();
                 var url = f.action + '?' + event;
                 $.post(url,q,
                 function(data){
                     $('#page_div',opener.document).html(data);
                     self.opener.refreshPageSaksiOks();
                 },'html');
                 self.close();
             }
         }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<s:form name="form3" id="form" beanclass="etanah.view.penguatkuasaan.KeteranganSaksiActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
        <s:hidden name="idSaksi" value="${actionBean.permohonanSaksi.idSaksi}"/>
        <fieldset class="aras1">
            <legend>
                Maklumat Saksi
            </legend>
            <div class="content">
                <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                <%--<s:text name="permohonanSaksi.idSaksi" maxlength="12" />--%>
                <p>
                    <label><font color="red" size="1">* </font>Nama 2 :</label>
                        <s:text name="nama" id="nama" onkeyup="this.value=this.value.toUpperCase();"  size="30" maxlength="100"/>
                </p>
                <p>
                    <label><font color="red" size="1">* </font>Jenis Pengenalan :</label>
                        <s:select name="kp"  value="${actionBean.permohonanSaksi.jenisPengenalan.kod}"  style="width:139px;" id="pengenalanSaksi" onchange="jenisPengenalanSaksi()">
                            <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" sort="nama" />
                    </s:select>
                    &nbsp;
                </p>
                <%--<p>
                    <label><font color="red" size="1">* </font>No.Pengenalan :</label>
                    <s:text name="noPengenalan" id="noPengenalan" maxlength="12" size="30"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <font color="red" size="1">Contoh No. KP : 850510075342 </font>
                    </p><br/>--%>
                <p id="noPengenalanLainSaksi">
                    <label>No.Pengenalan :</label>
                    <s:text name="noPengenalan" id="penyerahNoPengenalanLainSaksi" maxlength="12" onkeyup="this.value=this.value.toUpperCase();" />
                    <%--<font color="red" size="1">cth : 850510075342 </font>--%>
                    &nbsp;
                </p>

                <p id="noPengenalanBaruSaksi" style="visibility: hidden; display: none">
                    <label>No.Pengenalan :</label>
                    <s:text name="noPengenalan" id="penyerahNoPengenalanBaruSaksi" maxlength="12" onkeyup="validateNumber(this,this.value);"/>
                    <font color="red" size="1">cth : 850510071213 </font>
                    &nbsp;
                </p>
                <p>
                    <label><font color="red" size="1">* </font>Alamat :</label>
                        <s:text name="alamat1" id="alamat1" onkeyup="this.value=this.value.toUpperCase();" maxlength="50" size="30" />
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:text name="alamat2" onkeyup="this.value=this.value.toUpperCase();" maxlength="50" size="30"/>
                </p>
                <p>
                    <label> &nbsp; </label>
                    <s:text name="alamat3" onkeyup="this.value=this.value.toUpperCase();" maxlength="50" size="30" />
                </p>
                <p>
                    <label> &nbsp; </label>
                    <s:text name="alamat4" onkeyup="this.value=this.value.toUpperCase();" maxlength="50" size="30" />
                </p>

                <p>
                    <label><font color="red" size="1">* </font>Poskod :</label>
                        <s:text name="poskod" id="poskod" onkeyup="validateNumber(this,this.value);" maxlength="5"/>
                </p>

                <p>
                <p>
                    <label><font color="red" size="1">* </font>Negeri :</label>
                        <s:select  name="kodNegeriOrg" id="kodNegeriOrg" value="${actionBean.permohonanSaksi.negeri.kod}">
                            <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                    </s:select>&nbsp;
                </p>
                <p>
                    <label><font color="red" size="1">* </font>No.Telefon :</label>
                        <s:text name="noTelefon" id="noTelefon" size="30" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);"/>
                </p>
                <p>
                    <label>Email :</label>
                    <s:text name="email" id="email" size="30" maxlength="20" onblur="return ValidateEmail()"/>
                </p>
                <p>
                    <label>Pekerjaan :</label>
                    <s:text name="pekerjaan" id="pekerjaan" onkeyup="this.value=this.value.toUpperCase();" size="30" maxlength="20" />
                </p>

                <p><label>&nbsp;</label>
                    <c:if test="${addSaksiOks}">
                        <s:button name="simpanSaksiOks" class="btn" value="Simpan" onclick="simpanSaksiOks(this.name,this.form);"/>
                    </c:if>
                    <c:if test="${!addSaksiOks}">
                        <s:button name="edit" class="btn" value="Simpan" onclick="simpan2(this.name,this.form);"/>
                    </c:if>
                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>

                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    <s:hidden name="idOp"/>
                </p>
                <br>
            </div>
        </fieldset>
    </div>
</s:form>
