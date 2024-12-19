<%-- 
    Document   : TabJurulelongBerlesen
    Created on : Jan 11, 2013, 1:39:46 PM
    Author     : mazurahayati.yusop
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>


<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript">

    $(document).ready(function() {
        $("#nokp2").hide();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });

    function validate(){
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
        if($("#negeri").val() == "null"){
            alert('Sila pilih " Negeri "');
            $("#negeri").focus();
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
        $("#noSykt").val('');
        $("#tahun").val('');
    }

    function popup(){
        window.open("${pageContext.request.contextPath}/lelong/pendaftaran_jurulelong?showForm2","eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
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

        $.get(url,
        function(data){
            if (data == null || data.length == 0){
                alert("Maklumat tidak dijumpai");
                return;
            }
            var p = data.split(DELIM);
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
        });
    }

    function findNoSyarikat(idSykt){
        if(idSykt != ""){
            $.get('${pageContext.request.contextPath}/lelong/pendaftaran_jurulelong?findNoSyarikat&idSykt='+idSykt,
            function(data){
                $('#noSykt').val('');
                $('#noSykt').val(data);
            }, 'html');
        }
    }

    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.opener.refreshPagePilih();
            self.close();
        },'html');
    }

    $(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });

</script>
<s:form beanclass="etanah.view.stripes.lelong.TabJurulelongBerlesenActionBean">
    <div class="subtitle displaytag">
        <s:hidden name="idJLB" id="idJLB"/>
        <fieldset class="aras1">
            <p>
                <s:messages />
                <s:errors />&nbsp;
            </p>
        </fieldset>
        <br>
        <fieldset class="aras1">
            <legend>
                Daftar Jurulelong Berlesen
            </legend><br>

            <div class="subtitle displaytag">
                <p>
                    <label><font color="red">* </font>Nama Syarikat :</label>
                    <s:select id="idSyarikat" name="jurulelong.sytJuruLelong.idSytJlb" onchange="findNoSyarikat(this.value);" style="width:250px;">
                        <s:option value="null">-Sila Pilih-</s:option>
                        <s:options-collection collection="${actionBean.listSytJuruLelong}" label="nama" value="idSytJlb" />
                    </s:select>
                    &nbsp;
                    <img src="${pageContext.request.contextPath}/pub/images/tambah.png" height="15" width="15" alt="Tambah Maklumat Syarikat Jurulelong"
                         onmouseover="this.style.cursor='pointer';" onclick="popup();return false;" title="Tambah Maklumat Syarikat Jurulelong">
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
                        <s:option value="null">-Sila Pilih-</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" />
                    </s:select>
                </p>
                <p>
                    <label><font color="red">* </font>Nombor Pengenalan : </label>
                    <c:if test="${actionBean.melaka eq true}">
                        <s:text id="nokp1" name="jurulelong.noPengenalan" maxlength="12" onchange="validateIC,validateNewICLength(this.value);" onkeyup="validateNumber(this,this.value);" style="width:200px;"/>
                    </c:if>
                    <c:if test="${actionBean.melaka eq false}">
                        <s:text id="nokp1" name="jurulelong.noPengenalan" maxlength="12" onchange="validateNewICLength(this.value);" onkeyup="validateNumber(this,this.value);" style="width:200px;"/>
                    </c:if>
                    <s:text id="nokp2" name="jurulelong.noPengenalan" onblur="this.value=this.value.toUpperCase();" style="width:200px;"/>

                    <font color="red" size="1">(cth : 550201045678)</font>
                </p>
                <c:if test="${actionBean.melaka eq false}">
                    <p>
                        <label>Tahun Aktif :</label>
                        <%--<s:text id="tahun" name="jurulelong.thn_aktif" onblur="this.value=this.value.toUpperCase();" style="width:200px;"/>--%>
                        <s:select id="tahun" name="jurulelong.thn_aktif" style="width:200px;">
                            <s:option value="null">-Sila Pilih-</s:option>
                            <s:options-collection collection="${actionBean.listYear}"/>
                        </s:select>
                    </p>
                </c:if>
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
                    <label> &nbsp;</label>
                    <s:text id="alamat4" name="jurulelong.alamat4" onblur="this.value=this.value.toUpperCase();" style="width:200px;"/>
                </p>
                <p>
                    <label><font color="red">* </font>Poskod :</label>
                    <s:text id="poskod" name="jurulelong.poskod" value="" maxlength="5" onkeyup="validateNumber(this,this.value);" onchange="validatePoskodLength(this.value);" style="width:200px;"/>
                </p>
                <p>
                    <label><font color="red">* </font>Negeri :</label>
                    <s:select id="negeri" name="jurulelong.negeri.kod" style="width:200px;">
                        <s:option value="null">-Sila Pilih-</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                    </s:select>
                </p>
                <%--<!--            <p>
                                <label><font color="red">* </font>Cawangan :</label>
                                <s:select id="cawangan" name="jurulelong.cawangan.kod" style="width:200px;">
                                    <s:option value="null">-Sila Pilih-</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod" />
                                </s:select>
                            </p>-->--%>
                <p>
                    <label>No Telefon Bimbit :</label>
                    <s:text id="noTelefon2" name="jurulelong.noTelefon2" value="" onkeyup="validateNumber(this,this.value);" style="width:200px;"/>
                </p>
                <p>
                    <label><font color="red">* </font>Emel :</label>
                    <%--<s:text id="emel" name="jurulelong.emel" value=""  style="width:200px;"/>--%>
                    <s:text id="email" name="jurulelong.emel" size="40" maxlength="100" onblur="return ValidateEmail()"/>
                </p>

                <p align="center"><label></label>
                    <br>
                    <s:button name="simpan" id="add" value="Simpan" class="btn" onclick="save(this.name, this.form);" />

                    <s:button name="reset" value="Isi Semula" class="btn" onclick="clearForm();"/>
                </p>
            </div><br>
        </fieldset>
    </div>
</s:form>
