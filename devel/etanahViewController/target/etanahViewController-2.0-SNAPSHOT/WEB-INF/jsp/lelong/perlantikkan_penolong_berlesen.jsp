<%-- 
    Document   : perlantikkan_penolong_berlesen
    Created on : Apr 7, 2010, 5:23:20 PM
    Author     : mdizzat.mashrom, nur.aqilah
--%>

<%@page import="etanah.view.stripes.lelong.PendaftaranJurulelongBerlesenActionBean"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<%@ page import="java.text.*,java.util.*" session="true"%>
<!--<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />-->
<script type="text/javascript">

    $(document).ready(function() {
        
        var aktif = $('#aktif').val();
      
        
        $("#nokp2").hide();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });
    
    function confirmkemaskini(){
        var x;
        var r=confirm("Anda pasti untuk kemaskini?");
        if (r==true)
        {
            x="Ya";
        }
        else
        {
            x="Tidak";
        }
    }
    function validate(){
		if($("#idSyarikat").val() == " "){
            alert('Sila pilih " Nama Syarikat "');
            $("#idSyarikat").focus();
            return false;
        }
		if($("#noSykt").val() == null){
			alert('Sila kemaskini " Nombor Syarikat "');
			$("#noSykt").focus();
			return false;
		}
        if($("#nama").val() == ""){
            alert('Sila masukkan " Nama "');
            $("#nama").focus();
            return false;
        }

        if($("#jenisPengenalan").val() == ""){
            alert('Sila pilih " Jenis Pengenalan " terlebih dahulu.');
            $("#jenisPengenalan").focus();
            return false;
        }

        if($("#jenisPengenalan").val() == "B"){
            if($("#nokp1").val() == ""){
                alert('Sila masukkan " Nombor Pengenalan " terlebih dahulu.');
                $("#nokp1").focus();
                return false;
            }
        }
        if($("#jenisPengenalan").val() != "B"){
            if($("#nokp2").val() == ""){
                alert('Sila masukkan " Nombor Pengenalan " terlebih dahulu.');
                $("#nokp2").focus();
                return false;
            }
        }
        
        if($("#alamat1").val() == ""){
            alert('Sila masukkan " Alamat "');
            $("#alamat1").focus();
            return false;
        }
        if($("#poskod").val() == ""){
            alert('Sila masukkan " Poskod "');
            $("#poskod").focus();
            return false;
        }
        if($("#poskod").val().length != 5){
            alert('Sila masukkan " Poskod " 5 Angka Sahaja');
            $("#poskod").focus();
            return false;
        }
        if($("#negeri").val() == " "){
            alert('Sila pilih " Negeri "');
            $("#negeri").focus();
            return false;
        }
        if($("#aktif").val() == " "){
            alert('Sila pilih status Jurulelong');
            $("#aktif").focus();
            return false;
        }
        //        if($("#noTelefon1").val() == ""){
        //            alert('Sila masukkan " No Telefon Syarikat "');
        //            $("#noTelefon1").focus();
        //            return false;
        //        }
        //        if($("#noTelefon2").val() == ""){
        //            alert('Sila masukkan " No Telefon Bimbit "');
        //            $("#noTelefon2").focus();
        //            return false;
        //        }
        //        if($("#jenisPengenalan").val() == "null"){
        //            alert('Sila pilih " Kod Penganalan/Kod Syarikat "');
        //            $("#jenisPengenalan").focus();
        //            return false;
        //        }
        //        if($("#cawangan").val() == "null"){
        //            alert('Sila pilih " Kod Cawangan "');
        //            $("#cawangan").focus();
        //            return false;
        //        }

        return true;
    }

    function validatePoskodLength(value){
        var plength = value.length;
        if(plength != 5){
            alert('Poskod yang dimasukkan salah.');
            $('#poskod').val("");
            $('#poskod').focus();
        }
    }

    function validateNewICLength(value){
        var plength = value.length;
        if(plength != 12){
            alert('No Kad Pengenalan yang dimasukkan salah.');
            $('#nokp1').val("");
            $('#nokp1').focus();
        }
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }
    
    //for melaka
    function validateIC(icno){
        $.get("${pageContext.request.contextPath}/lelong/pendaftaran_jurulelong?doCheckJuruLelong&icno=" + icno,
        function(data){
            if(data =='1'){
                alert("Kad Pengenalan No " + icno + " telah wujud!");
                $("#nokp1").val("");
                $("#nokp1").focus();
                return false;
            }
        });
        return true;
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

    function test(f) {
        $(f).clearForm();
    }
	
	function clearText1(id) {
        $("#"+id+" input:text").each( function(el) {
            $(this).val('');
        });

        $("#" + id +" select").each( function(el) {
            $(this).val(' ');
        });
    }

    function clearForm() {
                
        $("#nama").val('');
        $("#alamat1").val('');
        $("#alamat2").val('');
        $("#alamat3").val('');
        $("#alamat4").val('');
        $("#poskod").val('');
        $("#negeri").val('');
        $("#noTelefon1").val('');
        $("#noTelefon2").val('');
        $("#jenisPengenalan").val('');
        $("#cawangan").val('');
        $('#nokp1').val('');
        $('#nokp2').val('');
        $('#idJLB').val('');
        $("#idSyarikat").val('');
        $("#idSykt").val('');
        $("#noSykt").val('');
        $("#tahun").val('');
        $("#aktif").val('null');
        $("#emel").val('');
        $("#idjlb").val('');
        $('#idPenyerah').val('');
        $('#penyerahNoPengenalan').val('');
        $('#idSyktPenyerah').val('');
          //url = "${pageContext.request.contextPath}/lelong/pendaftaran_jurulelong?showForm";

    }


    function popup(){
        window.open("${pageContext.request.contextPath}/lelong/pendaftaran_jurulelong?showForm2","eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function popup2(){
        var idSyarikat = $("#idSyarikat").val();
        var idjlb= $("#idjlb").val();
        
//        if(idjlb =='' && idSyarikat==0){
//            alert ("Sila Cari Jurulelong Terlebih Dahulu");
//            return;
//        }
//        else 
        if($("#idSyarikat").val()==0){
            alert ("Sila Pilih Syarikat Terlebih Dahulu");
            return;
        }
        else{
            window.open('${pageContext.request.contextPath}/lelong/pendaftaran_jurulelong?showEditSyarikat&idSyarikat=' + idSyarikat + '&idjlb=' + idjlb, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
            return;
        }
    }

    function changeNOKP( val ){
        var no = val;
        if(no == 'B'){
            $("#nokp2").hide();
            $("#nokp1").show();
        }else{
            $("#nokp2").show();
            $("#nokp1").hide();
        }
    }
	
    var DELIM = "__^$__";

    function populatePenyerah(btn){
        var url;
        if (btn.id == "carianPenyerah"){
            $('#kod').val('1');
            var idx = $("#idPenyerah").val();
            var jenis = '04';
            if (idx == null || idx.length == 0){
                //alert("Sila masukkan ID Penyerah.");
                window.open("${pageContext.request.contextPath}/lelong/pendaftaran_jurulelong?showFormPopup&jenisPenyerah=" + jenis, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=1024");

                return;
            }
            if (jenis == '0'){
                alert('Sila pilih Jenis Penyerah');
                return;
            } else if (jenis == '04'){ // Jurulelong
                url = "${pageContext.request.contextPath}/lelong/pendaftaran_jurulelong?findByID&idJBL=" + idx;
            }
        }else if(btn.id == "carianSyktPenyerah"){
            $('#kod').val('1');
            var idx = $("#idSyktPenyerah").val();
            var jenis = '04';
            if (idx == null || idx.length == 0){
                //alert("Sila masukkan ID Penyerah.");
                window.open("${pageContext.request.contextPath}/lelong/pendaftaran_jurulelong?showFormPopup2&jenisPenyerah=" + jenis, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=1024");

                return;
            }
            if (jenis == '0'){
                alert('Sila pilih Jenis Penyerah');
                return;
            } else if (jenis == '04'){ // Jurulelong
                url = "${pageContext.request.contextPath}/lelong/pendaftaran_jurulelong?findByIDN9&idJBL=" + idx;
            }
        } else if (btn.id == "carianPihak"){
            $('#kod').val('2');
            var jP = $("#penyerahJenisPengenalan").val();
            var noP = $("#penyerahNoPengenalan").val();
            if (jP == null || jP.length == 0 || noP == null || noP.length == 0){
                alert("Sila masukkan Jenis Pengenalan/No.Pengenalan.");
                return;
            }
            url = "${pageContext.request.contextPath}/lelong/pendaftaran_jurulelong?doCheckByNoKp&jenisPengenalan=" + jP
                + "&noPengenalan=" + noP;
        }
        
        var mess ;
        $.get(url,
        function(data){
            if (data == null || data.length == 0){
                alert("Maklumat tidak dijumpai");
                return;
            }
            var p = data.split(DELIM);
            if (btn.id == "carianSyktPenyerah"){
                //alert(p[0]+", "+p[1]+", "+p[2]+", "+p[3]+", "+p[4]+", "+p[5]+";");
                $('#jenisPengenalan').val(p[0]);
                $('#nokp1').val(p[1]);
                $("#noSykt").val(p[2]);
                $("#aktif").val(p[3]);
                $("#idSyarikat").val(p[4]);
                $("#alert").val(p[5]);
                if(p[5] == "Jurulelong ini tidak aktif"){
                        alert("Syarikat jurulelong ini tidak aktif.");
                }
            } else {
				//alert(p[13]);
				if (p[13] == 'null'){
					p[13] = "";
				}
				//alert(p[13]);
                $('#jenisPengenalan').val(p[0]);
                $('#nokp1').val(p[1]);
                $("#nama").val(p[2]);
                $("#alamat1").val(p[3]);
                $("#alamat2").val(p[4]);
                $("#alamat3").val(p[5]);
                $("#alamat4").val(p[6]);
                $("#poskod").val(p[7]);
                $("#negeri").val(p[8]);
                $("#cawangan").val(p[9]);
                $("#noTelefon1").val(p[10]);
                $("#noTelefon2").val(p[11]);
                $("#idSyarikat").val(p[12]);
                $("#noSykt").val(p[13]);
                $("#idJLB").val(p[14]);
                $("#tahun").val(p[15]);
                $("#aktif").val(p[16]);
                $("#emel").val(p[17]);
                $("#idjlb").val(p[18]);
                $("#alert").val(p[19]);

                if(p[19] == "Jurulelong ini tidak aktif"){
                        alert("Jurulelong ini tidak aktif.");
                }
				
            }
			
			if ($('#nokp1').val() != ''){
					document.getElementById("save1").style.visibility = 'hidden';
				} else {
					document.getElementById("save1").style.visibility = 'visible';
			}
			
        });
        
    }
    
    
    

    function findNoSyarikat(idSykt){
        //alert(idSykt);
		if(idSykt != ""){
            $.get('${pageContext.request.contextPath}/lelong/pendaftaran_jurulelong?findNoSyarikat&idSykt='+idSykt,
            function(data){
				if (data == null || data.length == 0){
					alert("Nombor Syarikat tidak diketahui");
					$('#noSykt').val('');
					return;
				}
                $('#noSykt').val('');
                $('#noSykt').val(data);
            }, 'html');
        }
    }

    function refreshPage(){
        alert("refreshPage");
        var url = '${pageContext.request.contextPath}/lelong/pendaftaran_jurulelong?showForm';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function refreshPagesAddress123(idSyarikat, idjlb) {
        var url = '${pageContext.request.contextPath}/lelong/pendaftaran_jurulelong?refresh&idSyarikat=' + idSyarikat + '&idjlb='
            + idjlb;
        $.get(url,
        function(data) {
            $('#page_div').html(data);
        }, 'html');
        
        window.location=url;
    }
    //    
    //    function refreshingPagingFolder(idSyarikat){
    //        var url = "${pageContext.request.contextPath}/lelong/pendaftaran_jurulelong?refresh&idSyarikat="+idSyarikat;
    //        window.location = url;
    //    }

    function changebutton( val ){
        var no = val;
        if(no == 'B'){
            $("#Y").hide();
            $("#N").show();
        }else{
            $("#Y").show();
            $("#N").hide();
        }
    }

</script>
<s:form beanclass="etanah.view.stripes.lelong.PendaftaranJurulelongBerlesenActionBean" id="page_div">
        <s:hidden name="idJLB" id="idJLB"/>
            <fieldset class="aras1">
                <p>
                    <s:messages />
                    <s:errors />&nbsp;
                </p>
                <legend>
                    Carian Jurulelong Berlesen
                </legend>
                <div class="subtitle displaytag">
                    <p>
                        <label>ID Jurulelong : </label>
                        <s:text name="idPenyerah" id="idPenyerah"/>
                        <input type="button" id="carianPenyerah"
                               value="Cari" class="btn" onclick="javascript:populatePenyerah(this);" />
                        (Biarkan kosong dan klik "Cari" untuk membuat rujukan)
                    </p>
					<c:if test="${actionBean.melaka eq false}">
                        <p>
                            <label>ID Syarikat Jurulelong : </label>
                            <s:text name="idSyktPenyerah" id="idSyktPenyerah"/>
                            <input type="button" id="carianSyktPenyerah"
                                   value="Cari" class="btn" onclick="javascript:populatePenyerah(this);" />
                        </p>
                    </c:if>
                    <p>
                        <label for="Jenis Pengenalan">Carian: No. Pengenalan :</label>
                        <s:select name="penyerahJenisPengenalan.kod" id="penyerahJenisPengenalan" onchange="clearNoPengenalan(),changebutton();">
                            <s:option value="0">Pilih Jenis...</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                        </s:select>
                        <s:text name="penyerahNoPengenalan" id="penyerahNoPengenalan" onkeyup="dodacheck(this.value);"
                                onblur="doUpperCase(this.id)"/>
                        <input type=button name="searchPenyerah" value="Cari" class="btn" id="carianPihak"
                               onclick="javascript:populatePenyerah(this);" />
                    </p><br>              
                </div>
            </fieldset>
            <br>
            <fieldset class="aras1">
                <legend>
                    Daftar Jurulelong Berlesen
                </legend><br>   
                <div class="subtitle displaytag">
                    <p>
                        <label>Id Jurulelong :</label>
                        <s:text id="idjlb" value="${actionBean.jurulelong.idJlb}"name="jurulelong.idjlb" readonly="true" style="width:200px;"/>

                    </p>
                
                    <p>
                        <label><font color="red">* </font>Nama Syarikat :</label>
						<s:select id="idSyarikat" name="jurulelong.sytJuruLelong.idSytJlb" onchange="findNoSyarikat(this.value);" style="width:250px;">
                        <s:option value=" " selected="selected">-Sila Pilih-</s:option>
                        <s:options-collection collection="${actionBean.listSytJuruLelong}" label="nama" value="idSytJlb" />
                        </s:select>
                        <!-- <s:text id="idSykt" name="jurulelong.sytJuruLelong.idSytJlb" style="width:200px;" />-->
                        &nbsp;
                        <img src="${pageContext.request.contextPath}/pub/images/tambah.png" height="15" width="15" alt="Tambah Maklumat Syarikat Jurulelong"
                             onmouseover="this.style.cursor='pointer';" onclick="popup();return false;" title="Tambah Maklumat Syarikat Jurulelong">
                        <img src="${pageContext.request.contextPath}/pub/images/edit.gif" height="15" width="15" alt="Kemaskini Maklumat Syarikat Jurulelong"
                             onmouseover="this.style.cursor='pointer';" onclick="popup2()" title="Kemaskini Maklumat Syarikat Jurulelong">
                    </p>
                    <p>
                        <label>No Syarikat :</label>
                        <s:text id="noSykt" name="jurulelong.sytJuruLelong.noPengenalan" readonly="true" style="width:200px;"/>
                    </p>
                
                    <p> 
                        <label><font color="red">* </font>Nama :</label>
                        <s:text id="nama" name="jurulelong.nama" onblur="this.value=this.value.toUpperCase();" style="width:200px;"/>
                    </p>
                    <p>
                        <label><font color="red">* </font>Jenis Pengenalan :</label>
                        <s:select id="jenisPengenalan" name="jurulelong.jenisPengenalan.kod" onchange="changeNOKP(this.value);" style="width:200px;">
                            <s:option value=" ">-Sila Pilih-</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" />
                        </s:select>
                    </p>
                    <p>
                        <label><font color="red">* </font>Nombor Pengenalan : </label>
                        <!--melaka no pngenalan-->
                        <s:text id="nokp1" name="jurulelong.noPengenalan" maxlength="12" onchange="validateIC,validateNewICLength(this.value);" onkeyup="validateNumber(this,this.value);" style="width:200px;"/>
                        
                        <s:text id="nokp2" name="jurulelong.noPengenalan" onblur="this.value=this.value.toUpperCase();" style="width:200px;" />

                        <font color="red" size="1">(cth : 550201045678)</font>
                    </p>
                    <p>
                        <label><font color="red">* </font>Alamat :</label>
                        <s:text id="alamat1" name="jurulelong.alamat1" onblur="this.value=this.value.toUpperCase();" style="width:200px;"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text id="alamat2" name="jurulelong.alamat2" onblur="this.value=this.value.toUpperCase();" style="width:200px;"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text id="alamat3" name="jurulelong.alamat3" onblur="this.value=this.value.toUpperCase();" style="width:200px;"/>
                    </p>
                    <p>
                        <label><font color="red">* </font>Bandar :</label>
                        <s:text id="alamat4" name="jurulelong.alamat4" onblur="this.value=this.value.toUpperCase();" style="width:200px;"/>
                    </p>
                    <p>
                        <label><font color="red">* </font>Poskod :</label>
                        <s:text id="poskod" name="jurulelong.poskod" value="" maxlength="5" onkeyup="validateNumber(this,this.value);" onchange="validatePoskodLength(this.value);" style="width:200px;"/>
                    </p>
                    <p>
                        <label><font color="red">* </font>Negeri :</label>
                        <s:select id="negeri" name="jurulelong.negeri.kod" style="width:200px;">
                            <s:option value=" ">-Sila Pilih-</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                        </s:select>
                    </p>
                    <p>
                        <label>No Telefon Bimbit :</label>
                        <s:text id="noTelefon2" name="jurulelong.noTelefon2"  onkeyup="validateNumber(this,this.value);" style="width:200px;"/>
                    </p>
                    <p>
                        <label><font color="red">* </font>Status :</label>
                        <c:if test="${aktif =='Y'}">
                            <s:text name="jurulelong.aktif" value="Aktif"style="width:200px;"/>
                        </c:if>
                        <c:if test="${aktif =='T'}">
                            <s:text name="jurulelong.aktif" value="Tidak Aktif" style="width:200px;"/>
                        </c:if>
                        <s:select id="aktif" name="aktif" style="width:200px;">
                            <s:option value=" " selected="selected">-Sila Pilih-</s:option>
                            <s:option value="Y">Aktif</s:option>
                            <s:option value="T">Tidak Aktif</s:option>
                        </s:select>
                    </p>
                    <p>
                        <label></font>Emel :</label> <%-- xretrieve pn dr table --%>
                        <s:text class="normal_text" id="emel" name="jurulelong.emel" maxlength="100" onblur="return ValidateEmail()" style="width:200px;"/>
                    </p>
                    <p align="center"><label></label>
                        <br>
                        <s:submit name="simpan" id="save1" value="Simpan" class="btn"  onclick="return validate();"/>
                        <s:button name="" value="Isi Semula" class="btn" onclick="clearText1('page_div');"/>
                        <s:submit name="kemaskini" id="save" value="Kemaskini" class="btn" onclick="return validate();"/>
                    </p>
                </div><br>
            </fieldset>      
</s:form>