<%-- 
    Document   : maklumat_bidaan
    Created on : Oct 16, 2013, 12:36:30 PM
    Author     : nur.aqilah
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<script type="text/javascript">

    $(document).ready(function() {
        $("#tarikPermohonan").hide();
        $("#nokp2").hide();
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
        var bil = $(".rowLine").length;
        for (var i = 0; i < bil; i++){
            $('#edit'+i).hide();
            $('#tarik'+i).hide();
            $('#del'+i).hide();
        }
        $('#option').hide();
        $('#lain').hide();
        $('#lain2').hide();
        $("#idBank2").hide();
        $("#idRuj2").hide();
        $("#idCara").hide();
    });

    function calc(value){
        var num = document.getElementById('deposit').value;
        num = num.toString().replace(/\$|\,/g,'');
        if (isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100+0.50000000001);
        cents = num % 100;
        num = Math.floor(num / 100).toString();
        if(cents < 10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
            num = num.substring(0, num.length - (4 * i + 3))+
            num.substring(num.length-(4 * i + 3));
        $('#deposit').val((((sign)?'':'-') + num + '.' + cents));

        var deposit = document.getElementById('deposit').value;
        var deposit2 = document.getElementById('deposit2').value;
        
        if(deposit2 != 0){
            var num = document.getElementById('deposit2').value;
            num = num.toString().replace(/\$|\,/g,'');
            if (isNaN(num))
                num = "0";
            sign = (num == (num = Math.abs(num)));
            num = Math.floor(num * 100+0.50000000001);
            cents = num % 100;
            num = Math.floor(num / 100).toString();
            if(cents < 10)
                cents = "0" + cents;
            for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
                num = num.substring(0, num.length - (4 * i + 3))+
                num.substring(num.length-(4 * i + 3));
            $('#deposit2').val((((sign)?'':'-') + num + '.' + cents));

            var num2 = document.getElementById('deposit2').value;
            var baki2 = value - deposit - num2;
            $("#baki").val(baki2);

            var num = document.getElementById('deposit2').value;
            num = num.toString().replace(/\$|\,/g,'');
            if (isNaN(num))
                num = "0";
            sign = (num == (num = Math.abs(num)));
            num = Math.floor(num * 100+0.50000000001);
            cents = num % 100;
            num = Math.floor(num / 100).toString();
            if(cents < 10)
                cents = "0" + cents;
            for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
                num = num.substring(0, num.length - (4 * i + 3))+','+
                num.substring(num.length-(4 * i + 3));
            $('#deposit2').val((((sign)?'':'-') + num + '.' + cents));

        }else{
            var baki2 = value - deposit;
            $("#baki").val(baki2);
        }

        var num = document.getElementById('deposit').value;
        num = num.toString().replace(/\$|\,/g,'');
        if (isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100+0.50000000001);
        cents = num % 100;
        num = Math.floor(num / 100).toString();
        if(cents < 10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
            num = num.substring(0, num.length - (4 * i + 3))+','+
            num.substring(num.length-(4 * i + 3));
        $('#deposit').val((((sign)?'':'-') + num + '.' + cents));


        var num = document.getElementById('baki').value;
        num = num.toString().replace(/\$|\,/g,'');
        if (isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100+0.50000000001);
        cents = num % 100;
        num = Math.floor(num / 100).toString();
        if(cents < 10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
            num = num.substring(0, num.length - (4 * i + 3))+','+
            num.substring(num.length-(4 * i + 3));
        $('#baki').val((((sign)?'':'-') + num + '.' + cents));

        var num = document.getElementById('hargaBida').value;
        num = num.toString().replace(/\$|\,/g,'');
        if (isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100+0.50000000001);
        cents = num % 100;
        num = Math.floor(num / 100).toString();
        if(cents < 10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
            num = num.substring(0, num.length - (4 * i + 3))+','+
            num.substring(num.length-(4 * i + 3));
        $('#hargaBida').val((((sign)?'':'-') + num + '.' + cents));
    }


    function calc2(value){

        var num = document.getElementById('hargaBida').value;
        num = num.toString().replace(/\$|\,/g,'');
        if (isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100+0.50000000001);
        cents = num % 100;
        num = Math.floor(num / 100).toString();
        if(cents < 10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
            num = num.substring(0, num.length - (4 * i + 3)) +
            num.substring(num.length-(4 * i + 3));
        $('#hargaBida').val((((sign)?'':'-') + num + '.' + cents));

        var num = document.getElementById('deposit').value;
        num = num.toString().replace(/\$|\,/g,'');
        if (isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100+0.50000000001);
        cents = num % 100;
        num = Math.floor(num / 100).toString();
        if(cents < 10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
            num = num.substring(0, num.length - (4 * i + 3)) +
            num.substring(num.length-(4 * i + 3));
        $('#deposit').val((((sign)?'':'-') + num + '.' + cents));

        var deposit = document.getElementById('deposit').value;
        var bida = document.getElementById('hargaBida').value;
        var baki2 = bida - deposit - value;
        $("#baki").val(baki2);

        var num = document.getElementById('deposit').value;
        num = num.toString().replace(/\$|\,/g,'');
        if (isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100+0.50000000001);
        cents = num % 100;
        num = Math.floor(num / 100).toString();
        if(cents < 10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
            num = num.substring(0, num.length - (4 * i + 3))+','+
            num.substring(num.length-(4 * i + 3));
        $('#deposit').val((((sign)?'':'-') + num + '.' + cents));


        var num = document.getElementById('deposit2').value;
        num = num.toString().replace(/\$|\,/g,'');
        if (isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100+0.50000000001);
        cents = num % 100;
        num = Math.floor(num / 100).toString();
        if(cents < 10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
            num = num.substring(0, num.length - (4 * i + 3))+','+
            num.substring(num.length-(4 * i + 3));
        $('#deposit2').val((((sign)?'':'-') + num + '.' + cents));

        var num = document.getElementById('baki').value;
        num = num.toString().replace(/\$|\,/g,'');
        if (isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100+0.50000000001);
        cents = num % 100;
        num = Math.floor(num / 100).toString();
        if(cents < 10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
            num = num.substring(0, num.length - (4 * i + 3))+','+
            num.substring(num.length-(4 * i + 3));
        $('#baki').val((((sign)?'':'-') + num + '.' + cents));

        var num = document.getElementById('hargaBida').value;
        num = num.toString().replace(/\$|\,/g,'');
        if (isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100+0.50000000001);
        cents = num % 100;
        num = Math.floor(num / 100).toString();
        if(cents < 10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
            num = num.substring(0, num.length - (4 * i + 3))+','+
            num.substring(num.length-(4 * i + 3));
        $('#hargaBida').val((((sign)?'':'-') + num + '.' + cents));
        $("#idCara").show();
    }

    function dateValidation(value){
        var vsplit = value.split('/');
        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var date = new Date(fulldate);
        var date1 = (date.getMonth()+1)+'/'+(date.getDate()+119)+'/'+(date.getYear());//+1900);
        var dateNew = new Date(date1);
        var sdate = dateNew.getDate()+'/'+(dateNew.getMonth()+1)+'/'+(dateNew.getYear());//+1900);
        $('#tarikhBayar').val(sdate);
        $('#tarikhBayar1').val(sdate);
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
    
    function simpanBerjaya(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        window.location = url+q;
        $.get(url,q,
        function(d){
            //                $('#page_div',opener.document).html(d);
            self.opener.refreshPage();
            //                self.close();
        },'html');
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

    function validation() {
        if($("#nama").val() == ""){
            alert('Sila masukkan " Nama " terlebih dahulu.');
            $("#nama").focus();
            return false;
        }
        if($("#jenis").val() == ""){
            alert('Sila pilih " Jenis Kad Pengenalan " terlebih dahulu.');
            $("#jenis").focus();
            return false;
        }

        if($("#jenis").val() == "B"){
            if($("#nokp1").val() == ""){
                alert('Sila masukkan " Nombor Pengenalan " terlebih dahulu.');
                $("#nokp1").focus();
                return false;
            }
        }
        if($("#jenis").val() != "B"){
            if($("#nokp2").val() == ""){
                alert('Sila masukkan " Nombor Pengenalan " terlebih dahulu.');
                $("#nokp2").focus();
                return false;
            }
        }
        
        if($("#alamat1").val() == ""){
            alert('Sila masukkan " Alamat " terlebih dahulu.');
            $("#alamat1").focus();
            return false;
        }
        if($("#poskod").val() == ""){
            alert('Sila masukkan " Poskod " terlebih dahulu.');
            $("#poskod").focus();
            return false;
        }
        if($("#negeri").val() == ""){
            alert('Sila pilih " Negeri " terlebih dahulu.');
            $("#negeri").focus();
            return false;
        }
        if($("#hargaBida").val() == ""){
            alert('Sila masukkan " Harga Bidaan " terlebih dahulu.');
            $("#hargaBida").focus();
            return false;
        }
        
        if($("#kodCareBayar").val() == ""){
            alert('Sila pilih " Cara Bayar " terlebih dahulu.');
            $("#kodCareBayar").focus();
            return false;
        }

        if($("#kodCareBayar").val() != "T"){
            
            if($("#kodBank").val() == ""){
                alert('Sila pilih " Bank " terlebih dahulu.');
                $("#kodBank").focus();
                return false;
            }

            if($("#noRujuk").val() == ""){
                alert('Sila masukkan " No Rujukkan " terlebih dahulu.');
                $("#noRujuk").focus();
                return false;
            }
        }
        
        if($("#deposit2").val() != ""){
            if($("#kodCareBayar2").val() == ""){
                alert('Sila pilih " Cara Bayar " terlebih dahulu.');
                $("#kodCareBayar2").focus();
                return false;
            }
        }

        var negeri = '${actionBean.negeri}';
        if(negeri == true){ 
            if($("#kodCareBayar2").val() != ""){

                if($("#kodCareBayar2").val() != "T"){
                    if($("#kodBank2").val() == ""){
                        alert('Sila pilih " Bank " terlebih dahulu.');
                        $("#kodBank2").focus();
                        return false;
                    }

                    if($("#noRujuk2").val() == ""){
                        alert('Sila masukkan " No Rujukkan " terlebih dahulu.');
                        $("#noRujuk2").focus();
                        return false;
                    }
                }
            }
        }
        return true;
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    var n = "";
    function validate(input) {

        var num = document.getElementById('hargaBida').value;
        num = num.toString().replace(/\$|\,/g,'');
        if (isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100+0.50000000001);
        cents = num % 100;
        num = Math.floor(num / 100).toString();
        if(cents < 10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
            num = num.substring(0, num.length - (4 * i + 3))+','+
            num.substring(num.length-(4 * i + 3));
        $('#hargaBida').val((((sign)?'':'-') + num + '.' + cents));
    }

    function refreshPage(){
        var url = '${pageContext.request.contextPath}/lelong/rekod_bidaan?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function validateTelLength(value){
        var plength = value.length;
        if(plength < 7){
            alert('No. Telefon yang dimasukkan salah.');
            $('#telefon').val("");
            $('#telefon').focus();
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

    function popupPembida(val,id){
        window.open("${pageContext.request.contextPath}/lelong/rekod_bidaan?pembidaPopup&idLelong="+val+"&id="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=1500,scrollbars=1");
    }

    function changeLain(value,row){
        if(value=="Y"){
            $('#edit'+row).show();
            $('#tiada'+row).hide();
            $('#tarik'+row).hide();
            $('#tarik2'+row).hide();
            $("#tarikPermohonan").hide();
        }else if(value=="T"){
            $('#edit'+row).hide();
            $('#tiada'+row).show();
            $('#tarik'+row).hide();
            $("#tarikPermohonan").hide();
        }else if(value=="P"){
            $('#edit'+row).hide();
            $('#tarik'+row).show();
            $('#tarik2'+row).show();
            $('#tiada'+row).hide();
            $("#tarikPermohonan").show();
        }
        else{
            $('#edit'+row).hide();
        }
    }

    function deletePembida2(val,val1){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/lelong/rekod_bidaan?deletePembida2&idPihak='+ val+'&idLelong='+ val1;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
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

    function changeCareBayar( val ){
        var no = val;
        if(no == 'T'){
            $("#idBank").hide();
            $("#idRuj").hide();
            $("#kodBank").attr("disabled", "true");
            $("#noRujuk").attr("disabled", "true");
        }else{
            $("#idBank").show();
            $("#idRuj").show();
            $("#kodBank").attr("disabled", "");
            $("#noRujuk").attr("disabled", "");
        }
    }

    function changeCareBayar2( val ){
        var no = val;
        if(no == 'T'){
            $("#idBank2").hide();
            $("#idRuj2").hide();
            $("#kodBank2").attr("disabled", "true");
            $("#noRujuk2").attr("disabled", "true");
        }else{
            $("#idBank2").show();
            $("#idRuj2").show();
            $("#kodBank2").attr("disabled", "");
            $("#noRujuk2").attr("disabled", "");
        }
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>

<s:form beanclass="etanah.view.stripes.lelong.MaklumatRekodBidaan" id="bida">
    <s:messages/>
    <s:errors/>&nbsp;

    <s:hidden name="idLelong" value="${actionBean.idLelong}"/>

    <c:if test="${actionBean.error eq true}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Pembida Berjaya
                </legend>
                <p>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PJTA'}">
                        <label>Bilangan Lelongan :</label>
                        <c:if test="${actionBean.lelong.bil eq '1'}">
                            Kali Pertama
                        </c:if>
                        <c:if test="${actionBean.lelong.bil eq '2'}">
                            Kali Kedua
                        </c:if>
                        <c:if test="${actionBean.lelong.bil eq '3'}">
                            Kali Ketiga
                        </c:if>
                    </c:if>

                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJTA'}">
                        <label>Bilangan Lelongan : ${actionBean.lelong.bil}</label>
                    </c:if> 

                </p><br>
                <div class="content">
                    <c:if test="${actionBean.bida eq true}">
                        <c:if test="${actionBean.bida eq true && actionBean.status eq 'TB'}">
                            <p align="center">
                                <s:button name="kembali" value="Kembali" onclick="doSubmit(this.form, this.name, 'page_div')" class="btn" />
                            </p>
                        </c:if>
                        <c:if test="${actionBean.status eq 'AP'}">
                            <c:if test="${actionBean.negeri eq true}">
                                <p>
                                    <label><font color="red">*</font> Nama : </label>
                                        <s:text id="nama" name="pihak.nama" onchange="this.value=this.value.toUpperCase();" style="width:260px;"/>
                                </p>
                                <p>
                                    <label><font color="red">*</font>Jenis Pengenalan : </label>
                                        <s:select id="jenis" name="pihak.jenisPengenalan.kod" style="width:150px;" onchange="changeNOKP(this.value);">
                                            <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                    </s:select>
                                </p>
                                <p>
                                    <label> <font color="red">*</font>Nombor Pengenalan : </label>
                                        <s:text id="nokp1" name="pihak.noPengenalan" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateNewICLength(this.value);" style="width:150px;"/>
                                        <s:text id="nokp2" name="pihak.noPengenalan"  onchange="this.value=this.value.toUpperCase();" style="width:150px;"/>
                                </p>
                                <p>
                                    <label><font color="red">*</font>Alamat Surat Menyurat: </label>
                                        <s:text id="alamat1" name="pihak.alamat1" size="40" maxlength="40" onchange="this.value=this.value.toUpperCase();"/>
                                </p>
                                <p>
                                    <label>&nbsp;</label>
                                    <s:text name="pihak.alamat2" size="40" maxlength="40" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                                </p>
                                <p>
                                    <label>&nbsp;</label>
                                    <s:text name="pihak.alamat3" size="40" maxlength="40" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                                </p>
                                <p>
                                    <label>&nbsp;</label>
                                    <s:text name="pihak.alamat4" size="40" maxlength="40" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                                </p>
                                <p>
                                    <label><font color="red">*</font>Poskod : </label>
                                    <s:text id="poskod" name="pihak.poskod" size="19" maxlength="5" onkeyup="validateNumber(this,this.value);" onchange="validatePoskodLength(this.value);" style="width:150px;"/>&nbsp;
                                </p>

                                <p>
                                    <label> <font color="red">*</font>Negeri : </label>
                                        <s:text id="negeri" name="pihak.negeri.nama" size="19" style="width:150px;"/>
                                </p>
                                <p>
                                    <label> Nombor Telefon Rumah : </label>
                                    <s:text id="telefon" name="pihak.noTelefon1" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);" style="width:150px;"/>&nbsp;
                                </p>
                                <p>
                                    <label> Nombor Telefon Bimbit : </label>
                                    <s:text name="pihak.noTelefonBimbit" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);" style="width:150px;"/>&nbsp;
                                </p>
                                <p>
                                    <label> Nombor Telefon Pejabat : </label>
                                    <s:text name="pihak.noTelefon2" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);" style="width:150px;"/>&nbsp;
                                </p>
                                <br>
                                <p>
                                    <label>Tarikh Lelongan Awam : </label>
                                    <fmt:formatDate value="${actionBean.lelong.tarikhLelong}" pattern="dd/MM/yyyy" />&nbsp;
                                </p>
                                <p>
                                    <label>Tarikh Akhir Bayaran : </label>
                                    <fmt:formatDate value="${actionBean.lelong.tarikhAkhirBayaran}" pattern="dd/MM/yyyy" />&nbsp; (120 hari dari tarikh lelongan)
                                </p>
                                <p>
                                    <label> Harga Rizab (RM) : </label>
                                    <%--berasingan--%>
                                    <c:if test="${actionBean.bersameke eq false}">
                                        <s:format formatPattern="#,##0.00" value="${actionBean.lelong.hargaRizab}" /> &nbsp;
                                    </c:if>
                                    <%--bersama--%>
                                    <c:if test="${actionBean.bersameke eq true}">
                                        <s:format formatPattern="#,##0.00" value="${actionBean.enkuiri.hargaRizab}" /> &nbsp;
                                    </c:if>
                                </p>
                                <p>
                                    <label><font color="red">*</font> Harga Bidaan (RM) : </label>
                                    <s:text name="hargaBidaan" id="hargaBida" onkeyup="validateNumber(this,this.value);" onblur="validate(this.value)" onchange="calc(this.value);"  formatPattern="###,###,###.00" style="width:150px;"/>&nbsp;
                                </p>
                                <p>
                                    <label>Deposit (RM) : </label>
                                    <s:format formatPattern="#,##0.00" value="${actionBean.deposit}" /> &nbsp;
                                    <%--<s:format formatPattern="#,##0.00" value="${atd.amaun}" /> &nbsp;--%>
                                    <s:hidden id="deposit" name="deposit"/>(10% Daripada Harga Rizab) &nbsp;
                                </p>
                                <p>
                                    <label>Tarikh Terima Bayaran Deposit : </label>
                                    <fmt:formatDate value="${actionBean.lelong.tarikhLelong}" pattern="dd/MM/yyyy" />&nbsp;
                                    <s:hidden name="atd.tarikhTerima" value="${actionBean.lelong.tarikhLelong}" />
                                    <%--<s:text id="tarikhTerima" name="atd.tarikhTerima" class="datepicker" formatPattern="dd/MM/yyyy" style="width:150px;"/>--%>
                                </p>
                                <p>
                                    <%--melaka--%>
                                    <c:if test="${actionBean.negeri eq false}">
                                        <label>Cara Bayar : </label>Bank Deraf
                                    </c:if>
                                    <%--negeri9--%>
                                    <c:if test="${actionBean.negeri eq true}">
                                        <label><font color="red">*</font> Cara Bayar : </label>
                                            <s:select id="kodCareBayar" name="kodCareBayar" style="width:200px;" onchange="changeCareBayar(this.value);">
                                                <s:option value="">--Sila Pilih--</s:option>
                                            <s:option value="T">Tunai</s:option>
                                            <s:option value="DB">Deraf Bank</s:option>
                                            <s:option value="KW">Kiriman Wang</s:option>
                                            <s:option value="WP">Wang Pos</s:option>
                                        </s:select>
                                    </c:if>

                                </p>
                                <c:if test="${actionBean.kodCareBayar eq null}">
                                    <p>
                                        <label><font color="red" id="idBank">*</font> Bank : </label>
                                            <s:select id="kodBank" name="kodBank" style="width:390px;">
                                                <s:option value="">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodBank}" label="nama" value="kod"/>
                                        </s:select>
                                    </p>
                                    <p>
                                        <label><font color="red" id="idRuj">*</font> No.Rujukan : </label>
                                            <s:text id="noRujuk" name="atd.noDokumenBayar" style="width:150px;"/>
                                    </p>
                                </c:if>

                                <c:if test="${actionBean.kodCareBayar ne null}">

                                    <p>
                                        <label><font color="red" id="idBank">*</font> Bank : </label>
                                            <c:if test="${actionBean.kodCareBayar eq 'T'}">
                                                <s:select id="kodBank" disabled="disabled" name="kodBank" style="width:390px;">
                                                    <s:option value="">Sila Pilih</s:option>
                                                <s:options-collection collection="${listUtil.senaraiKodBank}" label="nama" value="kod"/>
                                            </s:select>
                                        </c:if>
                                        <c:if test="${actionBean.kodCareBayar ne 'T'}">
                                            <s:select id="kodBank" name="kodBank" style="width:390px;">
                                                <s:option value="">Sila Pilih</s:option>
                                                <s:options-collection collection="${listUtil.senaraiKodBank}" label="nama" value="kod"/>
                                            </s:select>
                                        </c:if>
                                    </p>
                                    <p>
                                        <label><font color="red" id="idRuj">*</font> No.Rujukan : </label>
                                            <c:if test="${actionBean.kodCareBayar eq 'T'}">
                                                <s:text id="noRujuk" disabled="disabled" name="atd.noDokumenBayar" style="width:150px;"/>
                                            </c:if>
                                            <c:if test="${actionBean.kodCareBayar ne 'T'}">
                                                <s:text id="noRujuk" name="atd.noDokumenBayar" style="width:150px;"/>
                                            </c:if>
                                    </p>
                                </c:if>
                                <br>

                                <p>
                                    <label>Deposit (RM) : </label>
                                    <s:text id="deposit2" name="deposit2" onchange="calc2(this.value);" onkeyup="validateNumber(this,this.value);" onblur="validate(this.value)" formatPattern="###,###,###.00" style="width:150px;"/> (Tambahan) &nbsp;
                                </p>
                                <p>
                                    <label>Tarikh Terima Bayaran Deposit : </label>
                                    <fmt:formatDate value="${actionBean.lelong.tarikhLelong}" pattern="dd/MM/yyyy" />&nbsp;
                                    <s:hidden name="atd2.tarikhTerima" value="${actionBean.lelong.tarikhLelong}" />
                                </p>
                                <p>
                                    <%--melaka--%>
                                    <c:if test="${actionBean.negeri eq false}">
                                        <label>Cara Bayar : </label>Bank Deraf
                                    </c:if>
                                    <%--negeri9--%>
                                    <c:if test="${actionBean.negeri eq true}">
                                        <label><font color="red" id="idCara">*</font> Cara Bayar : </label>
                                            <s:select id="kodCareBayar2" name="kodCareBayar2" style="width:200px;" onchange="changeCareBayar2(this.value);">
                                                <s:option value="">--Sila Pilih--</s:option>
                                            <s:option value="T">Tunai</s:option>
                                            <s:option value="DB">Deraf Bank</s:option>
                                            <s:option value="KW">Kiriman Wang</s:option>
                                            <s:option value="WP">Wang Pos</s:option>
                                        </s:select>
                                    </c:if>
                                </p>

                                <c:if test="${actionBean.kodCareBayar2 eq null}">
                                    <p>
                                        <label><font color="red" id="idBank2">*</font> Bank : </label>
                                            <s:select id="kodBank2" name="kodBank2" style="width:390px;">
                                                <s:option value="">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodBank}" label="nama" value="kod"/>
                                        </s:select>
                                    </p>
                                    <p>
                                        <label><font color="red" id="idRuj2">*</font> No.Rujukan : </label>
                                            <s:text id="noRujuk2" name="atd2.noDokumenBayar" style="width:150px;"/>
                                    </p>
                                </c:if>

                                <c:if test="${actionBean.kodCareBayar2 ne null}">
                                    <p>
                                        <label><font color="red" id="idBank2">*</font> Bank : </label>
                                            <c:if test="${actionBean.kodCareBayar2 eq 'T'}">
                                                <s:select id="kodBank2" disabled="disabled" name="kodBank2" style="width:390px;">
                                                    <s:option value="">Sila Pilih</s:option>
                                                <s:options-collection collection="${listUtil.senaraiKodBank}" label="nama" value="kod"/>
                                            </s:select>
                                        </c:if>
                                        <c:if test="${actionBean.kodCareBayar2 ne 'T'}">
                                            <s:select id="kodBank2" name="kodBank2" style="width:390px;">
                                                <s:option value="">Sila Pilih</s:option>
                                                <s:options-collection collection="${listUtil.senaraiKodBank}" label="nama" value="kod"/>
                                            </s:select>
                                        </c:if>
                                    </p>
                                    <p>
                                        <label><font color="red" id="idRuj2">*</font> No.Rujukan : </label>
                                            <c:if test="${actionBean.kodCareBayar2 eq 'T'}">
                                                <s:text id="noRujuk2" disabled="disabled" name="atd2.noDokumenBayar" style="width:150px;"/>
                                            </c:if>
                                            <c:if test="${actionBean.kodCareBayar2 ne 'T'}">
                                                <s:text id="noRujuk2" name="atd2.noDokumenBayar" style="width:150px;"/>
                                            </c:if>
                                    </p>
                                </c:if>
                                <br>

                                <p>
                                    <label>Baki (RM) : </label>
                                    <s:text id="baki" name="baki" value="0.00" readonly="true" formatPattern="#,##0.00" style="width:150px;"/>&nbsp;
                                </p>
                                <%--<div class="content" align="center">
                                    <p> 
                                        <s:button name="save" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                                        <s:button name="kembali" value="Kembali" onclick="doSubmit(this.form, this.name, 'page_div')" class="btn" />
                                        
                                    </p>
                                </div>--%>
                            </c:if>

                            <%--MLK--%>
                            <c:if test = "${actionBean.negeri eq false}">
                                <c:if test = "${actionBean.BJ eq false}">
                                    <c:if test="${fn:length(actionBean.senaraiPembida)==0}">
                                        <p>
                                            <font color="red">*</font><em><font color="red" size="2">Masukkan Nama Pembida di Utiliti Senarai Pembida </font></em>
                                        </p>

                                    </c:if>

                                    <c:if test = "${actionBean.pembidaSama eq true}">
                                        <div class="subtitle displaytag" align="center" >
                                            <br>
                                            <fieldset class="aras1">
                                                <legend>
                                                    Maklumat Senarai Pembida
                                                </legend>
                                                <br>
                                                <div class="subtitle displaytag">
                                                    <display:table class="tablecloth" name="${actionBean.senaraiPembida}" pagesize="10" id="line" requestURI="/lelong/keputusan_bidaan">

                                                        <display:column> <s:radio name="pembida" id="chkbox${line_rowNum}" value="${line.pihak.idPihak}" class="chkbox" onmouseover="this.style.cursor='pointer';"/></display:column>
                                                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                                        <display:column title="Nama Pembida" property="pihak.nama" class="nama"/>
                                                        <display:column title="Jenis Pengenalan" property="pihak.jenisPengenalan.nama"/>
                                                        <display:column title="No Pengenalan" property="pihak.noPengenalan"/>
                                                    </display:table>
                                                </div>
                                            </fieldset>
                                        </div>
                                    </c:if>

                                    <c:if test = "${actionBean.pembidaSama ne true}">
                                        <div class="subtitle displaytag" align="center" >
                                            <br>
                                            <fieldset class="aras1">
                                                <legend>
                                                    Maklumat Senarai Pembida
                                                </legend>
                                                <br>
                                                <div class="subtitle displaytag">
                                                    <display:table class="tablecloth" name="${actionBean.senaraiPembida}" pagesize="10" id="line" requestURI="/lelong/keputusan_bidaan">

                                                        <display:column> <s:radio name="pembida" id="chkbox${line_rowNum}" value="${line.idPembida}" class="chkbox" onmouseover="this.style.cursor='pointer';"/></display:column>
                                                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                                        <display:column title="Nama Pembida" property="pihak.nama" class="nama"/>
                                                        <display:column title="Jenis Pengenalan" property="pihak.jenisPengenalan.nama"/>
                                                        <display:column title="No Pengenalan" property="pihak.noPengenalan"/>
                                                    </display:table>
                                                </div>
                                            </fieldset>
                                        </div>
                                    </c:if>

                                    <%--<div class="content" align="center">
                                        <s:button name="kembali" value="Kembali" onclick="doSubmit(this.form, this.name, 'page_div')" class="btn" />
                                        <c:if test="${fn:length(actionBean.senaraiPembida)>=1}">
                                            <s:button name="simpanPembidaFlow" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                                        </c:if>
                                    </div>--%>
                                </c:if>

                                <%--MLK--%>

                                <c:if test = "${actionBean.BJ eq true}">
                                    </p><br> 

                                    <div class="subtitle">
                                        <fieldset class="aras1" id="">
                                            <p>
                                                <label><font color="red">*</font> Nama: </label>
                                                    <s:text id="nama" name="pembida.pihak.nama" onchange="this.value=this.value.toUpperCase();" style="width:260px;"/>
                                            </p>
                                            <p>
                                                <label><font color="red">*</font>Jenis Pengenalan : </label>
                                                    <s:select id="jenis" name="kodJenis" style="width:150px;" onchange="changeNOKP(this.value);">
                                                        <s:option value="">Sila Pilih</s:option>
                                                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                                </s:select>
                                            </p>
                                            <p>
                                                <label> <font color="red">*</font>Nombor Pengenalan : </label>
                                                    <s:text id="nokp1" name="pembida.pihak.noPengenalan" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateNewICLength(this.value);" style="width:150px;"/>
                                                    <s:text id="nokp2" name="pembida.pihak.noPengenalan" onchange="this.value=this.value.toUpperCase();" style="width:150px;"/>

                                                <font color="red" size="1">(cth : 550201045678)</font>
                                            </p>
                                            <p>
                                                <label><font color="red">*</font>Alamat : </label>
                                                    <s:text id="alamat1" name="pembida.pihak.alamat1" size="40" maxlength="40" onchange="this.value=this.value.toUpperCase();"/>
                                            </p>
                                            <p>
                                                <label>&nbsp;</label>
                                                <s:text name="pembida.pihak.alamat2" size="40" maxlength="40" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                                            </p>
                                            <p>
                                                <label>&nbsp;</label>
                                                <s:text name="pembida.pihak.alamat3" size="40" maxlength="40" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                                            </p>
                                            <p>
                                                <label>&nbsp;</label>
                                                <s:text name="pembida.pihak.alamat4" size="40" maxlength="40" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                                            </p>
                                            <p>
                                                <label><font color="red">*</font>Poskod : </label>
                                                <s:text id="poskod" name="pembida.pihak.poskod" size="19" maxlength="5" onkeyup="validateNumber(this,this.value);" onchange="validatePoskodLength(this.value);" style="width:150px;"/>&nbsp;
                                            </p>
                                            <p>
                                                <label><font color="red">*</font>Negeri : </label>
                                                    <s:text id="poskod" name="pihak.negeri.nama" size="19" style="width:150px;"/>
                                            </p>
                                            <p>
                                                <label> Nombor Telefon Rumah : </label>
                                                <s:text id="telefon" name="pembida.pihak.noTelefon1" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);" style="width:150px;"/>&nbsp;
                                            </p>
                                            <p>
                                                <label> Nombor Telefon Bimbit : </label>
                                                <s:text name="pembida.pihak.noTelefonBimbit" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);" style="width:150px;"/>&nbsp;
                                            </p>
                                            <p>
                                                <label> Nombor Telefon Pejabat : </label>
                                                <s:text name="pembida.pihak.noTelefon2" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);" style="width:150px;"/>&nbsp;
                                            </p>
                                            <br>
                                            <p>
                                                <label>Tarikh Lelongan Awam : </label>
                                                <fmt:formatDate value="${actionBean.lelong.tarikhLelong}" pattern="dd/MM/yyyy" />&nbsp;
                                            </p>
                                            <p>
                                                <label>Tarikh Akhir Bayaran : </label>
                                                <fmt:formatDate value="${actionBean.lelong.tarikhAkhirBayaran}" pattern="dd/MM/yyyy" />&nbsp; (120 Hari Dari Tarikh Lelongan)
                                            </p>
                                            <p>
                                                <label> Harga Rizab (RM) : </label>
                                                <%--berasingan--%>
                                                <c:if test="${actionBean.bersameke eq false}">
                                                    <s:format formatPattern="#,##0.00" value="${actionBean.lelong.hargaRizab}" /> &nbsp;
                                                </c:if>
                                                <%--bersama--%>
                                                <c:if test="${actionBean.bersameke eq true}">
                                                    <s:format formatPattern="#,##0.00" value="${actionBean.enkuiri.hargaRizab}" /> &nbsp;
                                                </c:if>
                                            </p>
                                            <p>
                                                <label><font color="red">*</font> Harga Bidaan (RM) : </label>
                                                <s:text name="hargaBidaan" id="bida" value="numver"  onkeyup="validateNumber(this,this.value);" onblur="validate(this.value)" onchange="calc(this.value);" formatPattern="###,###,###.00" style="width:150px;"/>&nbsp;
                                            </p>
                                            <p>
                                                <label>Deposit (RM) : </label>
                                                <s:format formatPattern="#,##0.00" value="${actionBean.deposit}" /> &nbsp;
                                                <%--<s:format formatPattern="#,##0.00" value="${actionBean.depositAwal}" /> &nbsp;--%>
                                                <s:hidden id="deposit" name="deposit"/>(10% Daripada Harga Rizab) &nbsp;
                                            </p>
                                            <p>
                                                <label>Tarikh Terima Bayaran Deposit : </label>
                                                <fmt:formatDate value="${actionBean.lelong.tarikhLelong}" pattern="dd/MM/yyyy" />&nbsp;
                                                <s:hidden name="atd.tarikhTerima" value="${actionBean.lelong.tarikhLelong}" />
                                                <%--<s:text id="datepicker" name="atd.tarikhTerima" class="datepicker" formatPattern="dd/MM/yyyy" style="width:150px;"/>--%>
                                            </p>
                                            <p>
                                                <%--melaka--%>
                                                <c:if test="${actionBean.negeri eq false}">
                                                    <label>Cara Bayar : </label>Bank Deraf
                                                </c:if>
                                                <%--negeri9--%>
                                                <c:if test="${actionBean.negeri eq true}">
                                                    <label><font color="red">*</font> Cara Bayar : </label>
                                                        <s:select id="kodCareBayar" name="kodCareBayar" style="width:200px;" onchange="changeCareBayar(this.value);">
                                                            <s:option value="">--Sila Pilih--</s:option>
                                                        <s:option value="T">Tunai</s:option>
                                                        <s:option value="DB">Deraf Bank</s:option>
                                                        <s:option value="KW">Kiriman Wang</s:option>
                                                        <s:option value="WP">Wang Pos</s:option>
                                                    </s:select>
                                                </c:if>

                                            </p>
                                            <c:if test="${actionBean.kodCareBayar eq null}">
                                                <p>
                                                    <label><font color="red" id="idBank">*</font> Bank : </label>
                                                        <s:select id="kodBank" name="kodBank" style="width:390px;">
                                                            <s:option value="">Sila Pilih</s:option>
                                                        <s:options-collection collection="${listUtil.senaraiKodBank}" label="nama" value="kod"/>
                                                    </s:select>
                                                </p>
                                                <p>
                                                    <label><font color="red" id="idRuj">*</font> No.Rujukan: </label>
                                                        <%--<s:text id="noRujuk" name="atd.noDokumenBayar" style="width:150px;"/>--%>
                                                        <s:text id="noRujuk" name="pembida.noRujukan" style="width:150px;"/>
                                                </p>
                                            </c:if>

                                            <c:if test="${actionBean.kodCareBayar ne null}">

                                                <p>
                                                    <label><font color="red" id="idBank">*</font> Bank : </label>
                                                        <c:if test="${actionBean.kodCareBayar eq 'T'}">
                                                            <s:select id="kodBank" disabled="disabled" name="kodBank" style="width:390px;">
                                                                <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${listUtil.senaraiKodBank}" label="nama" value="kod"/>
                                                        </s:select>
                                                    </c:if>
                                                    <c:if test="${actionBean.kodCareBayar ne 'T'}">
                                                        <s:select id="kodBank" name="kodBank" style="width:390px;">
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${listUtil.senaraiKodBank}" label="nama" value="kod"/>
                                                        </s:select>
                                                    </c:if>
                                                </p>
                                                <p>
                                                    <label><font color="red" id="idRuj">*</font> No.Rujukan: </label>
                                                        <c:if test="${actionBean.kodCareBayar eq 'T'}">
                                                            <%--<s:text id="noRujuk" disabled="disabled" name="atd.noDokumenBayar" style="width:150px;"/>--%>
                                                            <s:text id="noRujuk" name="pembida.noRujukan" style="width:150px;"/>
                                                        </c:if>
                                                        <c:if test="${actionBean.kodCareBayar ne 'T'}">
                                                            <%--<s:text id="noRujuk" name="atd.noDokumenBayar" style="width:150px;"/>--%>
                                                            <s:text id="noRujuk" name="pembida.noRujukan" style="width:150px;"/>
                                                        </c:if>
                                                </p>
                                            </c:if>
                                            <br>

                                            <p>
                                                <label>Deposit (RM) : </label>
                                                <s:text id="deposit2" name="deposit2" onchange="calc2(this.value);" onkeyup="validateNumber(this,this.value);" onblur="validate(this.value)" formatPattern="###,###,###.00" style="width:150px;"/> (Tambahan) &nbsp;
                                            </p>
                                            <p>
                                                <label>Tarikh Terima Bayaran Deposit : </label>
                                                <fmt:formatDate value="${actionBean.lelong.tarikhLelong}" pattern="dd/MM/yyyy" />&nbsp;
                                                <s:hidden name="atd2.tarikhTerima" value="${actionBean.lelong.tarikhLelong}" />
                                            </p>
                                            <p>
                                                <%--melaka--%>
                                                <c:if test="${actionBean.negeri eq false}">
                                                    <label>Cara Bayar : </label>Bank Deraf
                                                </c:if>
                                                <%--negeri9--%>
                                                <c:if test="${actionBean.negeri eq true}">
                                                    <label><font color="red" id="idCara">*</font> Cara Bayar : </label>
                                                        <s:select id="kodCareBayar2" name="kodCareBayar2" style="width:200px;" onchange="changeCareBayar2(this.value);">
                                                            <s:option value="">--Sila Pilih--</s:option>
                                                        <s:option value="T">Tunai</s:option>
                                                        <s:option value="DB">Deraf Bank</s:option>
                                                        <s:option value="KW">Kiriman Wang</s:option>
                                                        <s:option value="WP">Wang Pos</s:option>
                                                    </s:select>
                                                </c:if>
                                            </p>
                                            <c:if test="${actionBean.kodCareBayar2 eq null}">
                                                <p>
                                                    <label><font color="red" id="idBank2">*</font> Bank : </label>
                                                        <s:select id="kodBank2" name="kodBank2" style="width:390px;">
                                                            <s:option value="">Sila Pilih</s:option>
                                                        <s:options-collection collection="${listUtil.senaraiKodBank}" label="nama" value="kod"/>
                                                    </s:select>
                                                </p>
                                                <p>
                                                    <label><font color="red" id="idRuj2">*</font> No.Rujukan: </label>
                                                        <s:text id="noRujuk2" name="atd2.noDokumenBayar" style="width:150px;"/>
                                                </p>
                                            </c:if>

                                            <c:if test="${actionBean.kodCareBayar2 ne null}">
                                                <p>
                                                    <label><font color="red" id="idBank2">*</font> Bank : </label>
                                                        <c:if test="${actionBean.kodCareBayar2 eq 'T'}">
                                                            <s:select id="kodBank2" disabled="disabled" name="kodBank2" style="width:390px;">
                                                                <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${listUtil.senaraiKodBank}" label="nama" value="kod"/>
                                                        </s:select>
                                                    </c:if>
                                                    <c:if test="${actionBean.kodCareBayar2 ne 'T'}">
                                                        <s:select id="kodBank2" name="kodBank2" style="width:390px;">
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${listUtil.senaraiKodBank}" label="nama" value="kod"/>
                                                        </s:select>
                                                    </c:if>
                                                </p>
                                                <p>
                                                    <label><font color="red" id="idRuj2">*</font> No.Rujukan: </label>
                                                        <c:if test="${actionBean.kodCareBayar2 eq 'T'}">
                                                            <s:text id="noRujuk2" disabled="disabled" name="atd2.noDokumenBayar" style="width:150px;"/>
                                                        </c:if>
                                                        <c:if test="${actionBean.kodCareBayar2 ne 'T'}">
                                                            <s:text id="noRujuk2" name="atd2.noDokumenBayar" style="width:150px;"/>
                                                        </c:if>
                                                </p>
                                            </c:if>
                                            <br>
                                            <p>
                                                <label>Baki (RM) : </label>
                                                <s:text id="baki" name="baki" value="0.00" readonly="true" formatPattern="#,##0.00" style="width:150px;"/>&nbsp;
                                            </p>
                                            <%-- <div class="content" align="center">
                                                <p>
                                                    <s:button name="saveMLK" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                                                    <s:button name="kembali3" value="Kembali" onclick="doSubmit(this.form, this.name, 'page_div')" class="btn" />
                                                </p>
                                            </div>--%>
                                        </fieldset>
                                    </div>
                                </c:if>
                            </c:if>
                        </c:if>
                    </c:if>

                    <c:if test="${actionBean.bida eq false}">
                        <c:if test="${actionBean.negeri eq true}">
                            <div align="center">
                                <display:table class="tablecloth" name="${actionBean.senaraiLelongan2}" id="line">
                                    <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                                    <display:column title="IDHakmilik" property="hakmilikPermohonan.hakmilik.idHakmilik" class="rowLine"/>
                                    <c:if test="${actionBean.owh eq false}">
                                        <display:column title="Status Lelongan">
                                            <c:if test="${line.kodStatusLelongan.kod ne 'TP'}">
                                                <c:if test="${line.pembida.idPihak eq null}">
                                                    <input type="radio" name="ada${line_rowNum - 1}" value="Y" onclick="changeLain(this.value,${line_rowNum-1});"/>Ada Pembida&nbsp;
                                                    <%--<input type="radio" name="ada${line_rowNum - 1}" value="P" onclick="changeLain(this.value,${line_rowNum-1});"/>Tarik Permohonan &nbsp;--%>
                                                    <input type="radio" name="ada${line_rowNum - 1}" value="T" checked="checked"  onclick="changeLain(this.value,${line_rowNum-1});"/>Tiada Pembida
                                                </c:if>
                                                <c:if test="${line.pembida.idPihak ne null}">
                                                    <input type="radio" name="ada${line_rowNum - 1}" value="Y" checked="checked" onclick="changeLain(this.value,${line_rowNum-1});"/>Ada Pembida&nbsp;
                                                    <%--<input type="radio" name="ada${line_rowNum - 1}" value="P" onclick="changeLain(this.value,${line_rowNum-1});"/>Tarik Permohonan &nbsp;--%>
                                                    <input type="radio" name="ada${line_rowNum - 1}" value="T" onclick="changeLain(this.value,${line_rowNum-1});"/>Tiada Pembida
                                                </c:if>
                                            </c:if>
                                            <c:if test="${line.kodStatusLelongan.kod eq 'TP'}">
                                                <input type="radio" name="ada${line_rowNum - 1}" value="Y" onclick="changeLain(this.value,${line_rowNum-1});"/>Ada Pembida&nbsp;
                                                <%--<input type="radio" name="ada${line_rowNum - 1}" value="P" checked="checked"  onclick="changeLain(this.value,${line_rowNum-1});"/>Tarik Permohonan &nbsp;--%>
                                                <input type="radio" name="ada${line_rowNum - 1}" value="T" onclick="changeLain(this.value,${line_rowNum-1});"/>Tiada Pembida
                                            </c:if>
                                        </display:column>
                                    </c:if>
                                    <c:if test="${actionBean.owh eq true}">
                                        <display:column title="Status Lelongan">
                                            <c:if test="${kembali}">
                                                <c:if test="${line.kodStatusLelongan.kod ne 'TP'}">
                                                    <c:if test="${line.pembida.idPihak eq null}">
                                                        <input type="radio" name="ada${line_rowNum - 1}" value="Y" onclick="changeLain(this.value,${line_rowNum-1});"/>Ada Pembida&nbsp;
                                                        <input type="radio" name="ada${line_rowNum - 1}" value="P" onclick="changeLain(this.value,${line_rowNum-1});"/>Tarik Permohonan
                                                    </c:if>
                                                    <c:if test="${line.pembida.idPihak ne null}">
                                                        <input type="radio" name="ada${line_rowNum - 1}" value="Y" checked="checked" onclick="changeLain(this.value,${line_rowNum-1});"/>Ada Pembida&nbsp;
                                                        <input type="radio" name="ada${line_rowNum - 1}" value="P" onclick="changeLain(this.value,${line_rowNum-1});"/>Tarik Permohonan &nbsp;
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${line.kodStatusLelongan.kod eq 'TP'}">
                                                    <input type="radio" name="ada${line_rowNum - 1}" value="Y" onclick="changeLain(this.value,${line_rowNum-1});"/>Ada Pembida&nbsp;
                                                    <input type="radio" name="ada${line_rowNum - 1}" value="P" checked="checked"  onclick="changeLain(this.value,${line_rowNum-1});"/>Tarik Permohonan &nbsp;
                                                </c:if>
                                            </c:if>
                                            <c:if test="${!kembali}">
                                                ${line.kodStatusLelongan.nama}
                                            </c:if>
                                        </display:column>
                                    </c:if>
                                    <display:column title="Maklumat Pembida">
                                        <c:if test="${actionBean.owh eq false}">
                                            <c:if test="${line.pembida.idPihak eq null}">
                                                <div align="center" id="namaPembida${line_rowNum - 1}"></div>
                                                <div align="center" id="edit${line_rowNum - 1}" >
                                                    <a id="" onclick="popupPembida('${line.idLelong}','${line_rowNum - 1}');return false;" onmouseover="this.style.cursor='pointer';">
                                                        <img alt="Sila Klik Untuk Masukkan Maklumat Pembida" title="Sila Klik Untuk Masukkan Maklumat Pembida" src='${pageContext.request.contextPath}/pub/images/edit.gif'>
                                                    </a>
                                                    &nbsp;
                                                    <a id="del${line_rowNum - 1}" onclick="deletePembida2('${line.idLelong}');return false;" onmouseover="this.style.cursor='pointer';" >
                                                        <img alt='Klik Untuk Hapus' title="Klik Untuk Hapus" src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="">
                                                    </a>
                                                </div>
                                                <div align="center" id="tiada${line_rowNum - 1}" >
                                                    Tiada Pembida
                                                </div>
                                            </c:if>
                                            <c:if test="${line.pembida.idPihak ne null}">
                                                <div align="center" id="namaPembida${line_rowNum - 1}">${line.pembida.nama}</div>
                                                <div align="center">
                                                    <a onclick="popupPembida('${line.idLelong}','${line_rowNum - 1}');return false;" onmouseover="this.style.cursor='pointer';">
                                                        <img alt="Sila Klik Untuk Masukkan Maklumat Pembida" title="Sila Klik Untuk Masukkan Maklumat Pembida" src='${pageContext.request.contextPath}/pub/images/edit.gif' >
                                                    </a>
                                                    &nbsp;
                                                    <a onclick="deletePembida2('${line.idLelong}');return false;" onmouseover="this.style.cursor='pointer';" >
                                                        <img alt='Klik Untuk Hapus' title="Klik Untuk Hapus" src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="" >
                                                    </a>
                                                </div>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${actionBean.owh eq true}">
                                            <div align="center">
                                                <c:if test="${line.kodStatusLelongan.kod ne 'TP'}">
                                                    <c:if test="${line.pembida.idPihak eq null}">
                                                        <div align="center" id="namaPembida${line_rowNum - 1}"></div>
                                                        <div align="center" id="edit${line_rowNum - 1}" >
                                                            <a id="" onclick="popupPembida('${line.idLelong}','${line_rowNum - 1}');return false;" onmouseover="this.style.cursor='pointer';">
                                                                <img alt="Sila Klik Untuk Masukkan Maklumat Pembida" title="Sila Klik Untuk Masukkan Maklumat Pembida" src='${pageContext.request.contextPath}/pub/images/edit.gif' >
                                                            </a>
                                                            &nbsp;
                                                            <a id="del${line_rowNum - 1}" onclick="deletePembida2('${line.idLelong}');return false;" onmouseover="this.style.cursor='pointer';" >
                                                                <img alt='Klik Untuk Hapus'title="Klik Untuk Hapus" src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="" >
                                                            </a>
                                                        </div>
                                                        <div align="center" id="tarik${line_rowNum - 1}" >
                                                            Tarik Permohonan
                                                        </div>
                                                        <div align="center" id="tiada${line_rowNum - 1}" >
                                                            -
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${line.pembida.idPihak ne null}">
                                                        <div align="center" id="namaPembida${line_rowNum - 1}">${line.pembida.nama}</div>
                                                        <div align="center">
                                                            <a onclick="popupPembida('${line.idLelong}','${line_rowNum - 1}');return false;" onmouseover="this.style.cursor='pointer';">
                                                                <img alt="Sila Klik Untuk Masukkan Maklumat Pembida" title="Sila Klik Untuk Masukkan Maklumat Pembida" src='${pageContext.request.contextPath}/pub/images/edit.gif' >
                                                            </a>
                                                            &nbsp;
                                                            <c:if test="${kembali}">
                                                                <a onclick="deletePembida2('${line.idLelong}');return false;" onmouseover="this.style.cursor='pointer';" >
                                                                    <img alt='Klik Untuk Hapus' title="Klik Untuk Hapus" src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="" >
                                                                </a>
                                                            </c:if>
                                                        </div>
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${line.kodStatusLelongan.kod eq 'TP'}">
                                                    <div id="tarik2${line_rowNum - 1}">
                                                        Tarik Permohonan
                                                    </div>
                                                    <c:if test="${kembali}">
                                                        <div align="center" id="namaPembida${line_rowNum - 1}"></div>
                                                        <div align="center" id="edit${line_rowNum - 1}" >
                                                            <a id="" onclick="popupPembida('${line.idLelong}','${line_rowNum - 1}');return false;" onmouseover="this.style.cursor='pointer';">
                                                                <img alt="Sila Klik Untuk Masukkan Maklumat Pembida" title="Sila Klik Untuk Masukkan Maklumat Pembida" src='${pageContext.request.contextPath}/pub/images/edit.gif'>
                                                            </a>
                                                            &nbsp;
                                                            <a id="del${line_rowNum - 1}" onclick="deletePembida2('${line.idLelong}');return false;" onmouseover="this.style.cursor='pointer';" >
                                                                <img alt='Klik Untuk Hapus' title="Klik Untuk Hapus" src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="" >
                                                            </a>
                                                        </div>
                                                    </c:if>
                                                </c:if>
                                            </div>
                                        </c:if>
                                    </display:column>
                                </display:table><br>

                                <p>
                                    <%--<s:button id="tarikPermohonan" name="tarikPermohonan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                                    <c:if test="${kembali}">
                                        <s:button name="kembali" value="Kembali" onclick="doSubmit(this.form, this.name, 'page_div')" class="btn" />
                                    </c:if>--%>
                                </p>
                            </div>
                        </c:if>
                        <br>
                        <c:if test="${actionBean.negeri ne true}">
                            <c:if test = "${actionBean.BJ eq false}">
                                <c:if test="${fn:length(actionBean.senaraiPembida)==0}">
                                    <p>
                                        <font color="red">*</font><em><font color="red" size="2">Masukkan Nama Pembida di Utiliti Senarai Pembida </font></em>
                                    </p>

                                </c:if>
                            </c:if>
                            <div align="center">
                                <display:table class="tablecloth" name="${actionBean.senaraiLelongan2}" id="line">
                                    <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                                    <display:column title="IDHakmilik" property="hakmilikPermohonan.hakmilik.idHakmilik" class="rowLine"/>
                                    <c:if test="${actionBean.owh eq false}">
                                        <display:column title="Status Lelongan"> 
                                            <c:if test="${line.kodStatusLelongan.kod ne 'TP'}">
                                                <c:if test="${actionBean.lelong.idLelong eq null}">
                                                    <input type="radio" name="ada${line_rowNum - 1}" value="Y" onclick="changeLain(this.value,${line_rowNum-1});"/>Ada Pembida&nbsp;
                                                    <%--<input type="radio" name="ada${line_rowNum - 1}" value="P" onclick="changeLain(this.value,${line_rowNum-1});"/>Tarik Permohonan &nbsp;--%>
                                                    <input type="radio" name="ada${line_rowNum - 1}" value="T" checked="checked"  onclick="changeLain(this.value,${line_rowNum-1});"/>Tiada Pembida
                                                </c:if>
                                                <c:if test="${actionBean.lelong.idLelong ne null}">
                                                    <c:if test="${fn:length(actionBean.senaraiPembida)==0}">
                                                        <input type="radio" name="ada${line_rowNum - 1}" value="Y" onclick="changeLain(this.value,${line_rowNum-1});"/>Ada Pembida&nbsp;
                                                        <%--<input type="radio" name="ada${line_rowNum - 1}" value="P" onclick="changeLain(this.value,${line_rowNum-1});"/>Tarik Permohonan &nbsp;--%>
                                                        <input type="radio" name="ada${line_rowNum - 1}" value="T" checked="checked"  onclick="changeLain(this.value,${line_rowNum-1});"/>Tiada Pembida
                                                    </c:if>
                                                    <c:forEach items="${actionBean.senaraiPembida}" var="line2">
                                                        <c:if test="${fn:length(actionBean.senaraiPembida)==1}">
                                                            <c:if test="${line2.lelong.idLelong ne line.idLelong}">
                                                                <%--<c:if test="${line2.kodStsPembida.kod eq null}">--%>
                                                                <input type="radio" name="ada${line_rowNum - 1}" value="Y" onclick="changeLain(this.value,${line_rowNum-1});"/>Ada Pembida&nbsp;
                                                                <%--<input type="radio" name="ada${line_rowNum - 1}" value="P" onclick="changeLain(this.value,${line_rowNum-1});"/>Tarik Permohonan &nbsp;--%>
                                                                <input type="radio" name="ada${line_rowNum - 1}" value="T" checked="checked"  onclick="changeLain(this.value,${line_rowNum-1});"/>Tiada Pembida
                                                                <%--</c:if>--%>
                                                            </c:if>
                                                        </c:if>
                                                        <c:if test="${line2.lelong.idLelong eq line.idLelong}">
                                                            <c:if test="${line2.kodStsPembida.kod eq 'TB'}">
                                                                <input type="radio" name="ada${line_rowNum - 1}" value="Y" onclick="changeLain(this.value,${line_rowNum-1});"/>Ada Pembida&nbsp;
                                                                <%--<input type="radio" name="ada${line_rowNum - 1}" value="P" onclick="changeLain(this.value,${line_rowNum-1});"/>Tarik Permohonan &nbsp;--%>
                                                                <input type="radio" name="ada${line_rowNum - 1}" value="T" checked="checked"  onclick="changeLain(this.value,${line_rowNum-1});"/>Tiada Pembida
                                                            </c:if>
                                                            <c:if test="${line2.kodStsPembida.kod eq 'BJ'}">
                                                                <input type="radio" name="ada${line_rowNum - 1}" value="Y" checked="checked" onclick="changeLain(this.value,${line_rowNum-1});"/>Ada Pembida&nbsp;
                                                                <%--<input type="radio" name="ada${line_rowNum - 1}" value="P" onclick="changeLain(this.value,${line_rowNum-1});"/>Tarik Permohonan &nbsp;--%>
                                                                <input type="radio" name="ada${line_rowNum - 1}" value="T" onclick="changeLain(this.value,${line_rowNum-1});"/>Tiada Pembida
                                                            </c:if>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${line.kodStatusLelongan.kod eq 'TP'}">
                                                <input type="radio" name="ada${line_rowNum - 1}" value="Y" onclick="changeLain(this.value,${line_rowNum-1});"/>Ada Pembida&nbsp;
                                                <%--<input type="radio" name="ada${line_rowNum - 1}" value="P" checked="checked"  onclick="changeLain(this.value,${line_rowNum-1});"/>Tarik Permohonan &nbsp;--%>
                                                <input type="radio" name="ada${line_rowNum - 1}" value="T" onclick="changeLain(this.value,${line_rowNum-1});"/>Tiada Pembida
                                            </c:if>
                                        </display:column>
                                    </c:if>
                                    <c:if test="${actionBean.owh eq true}">
                                        <display:column title="Status Lelongan">
                                            <c:if test="${kembali}">
                                                <c:if test="${line.kodStatusLelongan.kod ne 'TP'}">
                                                    <c:if test="${actionBean.lelong.idLelong eq null}">
                                                        <input type="radio" name="ada${line_rowNum - 1}" value="Y" onclick="changeLain(this.value,${line_rowNum-1});"/>Ada Pembida&nbsp;
                                                        <input type="radio" name="ada${line_rowNum - 1}" value="P" onclick="changeLain(this.value,${line_rowNum-1});"/>Tarik Permohonan
                                                    </c:if>
                                                    <c:if test="${actionBean.lelong.idLelong ne null}">
                                                        <c:if test="${fn:length(actionBean.senaraiPembida)==0}">
                                                            <input type="radio" name="ada${line_rowNum - 1}" value="Y" onclick="changeLain(this.value,${line_rowNum-1});"/>Ada Pembida&nbsp;
                                                            <input type="radio" name="ada${line_rowNum - 1}" value="P" onclick="changeLain(this.value,${line_rowNum-1});"/>Tarik Permohonan
                                                        </c:if>
                                                        <c:forEach items="${actionBean.senaraiPembida}" var="line2">
                                                            <c:if test="${fn:length(actionBean.senaraiPembida)==1}">
                                                                <c:if test="${line2.lelong.idLelong ne line.idLelong}">
                                                                    <input type="radio" name="ada${line_rowNum - 1}" value="Y" onclick="changeLain(this.value,${line_rowNum-1});"/>Ada Pembida&nbsp;
                                                                    <input type="radio" name="ada${line_rowNum - 1}" value="P" onclick="changeLain(this.value,${line_rowNum-1});"/>Tarik Permohonan
                                                                </c:if>
                                                            </c:if>
                                                            <c:if test="${line2.lelong.idLelong eq line.idLelong}">
                                                                <c:if test="${line2.kodStsPembida.kod eq 'TB'}">
                                                                    <input type="radio" name="ada${line_rowNum - 1}" value="Y" onclick="changeLain(this.value,${line_rowNum-1});"/>Ada Pembida&nbsp;
                                                                    <input type="radio" name="ada${line_rowNum - 1}" value="P" onclick="changeLain(this.value,${line_rowNum-1});"/>Tarik Permohonan
                                                                </c:if>
                                                                <c:if test="${line2.kodStsPembida.kod eq 'BJ'}">
                                                                    <input type="radio" name="ada${line_rowNum - 1}" value="Y" checked="checked" onclick="changeLain(this.value,${line_rowNum-1});"/>Ada Pembida&nbsp;
                                                                    <input type="radio" name="ada${line_rowNum - 1}" value="P" onclick="changeLain(this.value,${line_rowNum-1});"/>Tarik Permohonan &nbsp;
                                                                </c:if>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${line.kodStatusLelongan.kod eq 'TP'}">
                                                    <input type="radio" name="ada${line_rowNum - 1}" value="Y" onclick="changeLain(this.value,${line_rowNum-1});"/>Ada Pembida&nbsp;
                                                    <input type="radio" name="ada${line_rowNum - 1}" value="P" checked="checked"  onclick="changeLain(this.value,${line_rowNum-1});"/>Tarik Permohonan &nbsp;
                                                </c:if>

                                            </c:if>
                                            <c:if test="${!kembali}">
                                                ${line.kodStatusLelongan.nama}
                                            </c:if>
                                        </display:column>
                                    </c:if>
                                    <display:column title="Maklumat Pembida">
                                        <c:if test="${actionBean.owh eq false}">
                                            <c:if test="${actionBean.lelong.idLelong eq null}">
                                                <div align="center" id="namaPembida${line_rowNum - 1}"></div>
                                                <div align="center" id="edit${line_rowNum - 1}" >
                                                    <a id="" onclick="popupPembida('${line2.lelong.idLelong}','${line_rowNum - 1}');return false;" onmouseover="this.style.cursor='pointer';">
                                                        <img alt="Sila Klik Untuk Masukkan Maklumat Pembida" title="Sila Klik Untuk Masukkan Maklumat Pembida" src='${pageContext.request.contextPath}/pub/images/edit.gif'>
                                                    </a>
                                                    &nbsp;
                                                    <a id="del${line_rowNum - 1}" onclick="deletePembida2('${line2.pihak.idPihak}','${line2.lelong.idLelong}');return false;" onmouseover="this.style.cursor='pointer';" >
                                                        <img alt='Klik Untuk Hapus' title="Klik Untuk Hapus" src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="">
                                                    </a>
                                                </div>
                                                <div align="center" id="tiada${line_rowNum - 1}" >
                                                    Tiada Pembida 
                                                </div>
                                            </c:if>
                                            <c:if test="${actionBean.lelong.idLelong ne null}">
                                                <c:if test="${fn:length(actionBean.senaraiPembida)==0}">
                                                    <div align="center" id="namaPembida${line_rowNum - 1}"></div>
                                                    <div align="center" id="edit${line_rowNum - 1}" >
                                                        <a id="" onclick="popupPembida('${line2.lelong.idLelong}','${line_rowNum - 1}');return false;" onmouseover="this.style.cursor='pointer';">
                                                            <img alt="Sila Klik Untuk Masukkan Maklumat Pembida" title="Sila Klik Untuk Masukkan Maklumat Pembida" src='${pageContext.request.contextPath}/pub/images/edit.gif'>
                                                        </a>
                                                        &nbsp;
                                                        <a id="del${line_rowNum - 1}" onclick="deletePembida2('${line2.pihak.idPihak}','${line2.lelong.idLelong}');return false;"onmouseover="this.style.cursor='pointer';" >
                                                            <img alt='Klik Untuk Hapus' title="Klik Untuk Hapus" src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="">
                                                        </a>
                                                    </div>
                                                    <div align="center" id="tiada${line_rowNum - 1}" >
                                                        Tiada Pembida 
                                                    </div>
                                                </c:if>
                                                <c:forEach items="${actionBean.senaraiPembida}" var="line2">
                                                    <c:if test="${fn:length(actionBean.senaraiPembida)==1}">
                                                        <c:if test="${line2.lelong.idLelong ne line.idLelong}">
                                                            <%--<c:if test="${line2.kodStsPembida.kod eq null}">--%>
                                                            <div align="center" id="namaPembida${line_rowNum - 1}"></div>
                                                            <div align="center" id="edit${line_rowNum - 1}" >
                                                                <a id="" onclick="popupPembida('${line.idLelong}','${line_rowNum - 1}');return false;" onmouseover="this.style.cursor='pointer';">
                                                                    <img alt="Sila Klik Untuk Masukkan Maklumat Pembida" title="Sila Klik Untuk Masukkan Maklumat Pembida" src='${pageContext.request.contextPath}/pub/images/edit.gif'>
                                                                </a>
                                                                &nbsp;
                                                                <a id="del${line_rowNum - 1}" onclick="deletePembida2('${line2.pihak.idPihak}','${line2.lelong.idLelong}');return false;" onmouseover="this.style.cursor='pointer';" >
                                                                    <img alt='Klik Untuk Hapus' title="Klik Untuk Hapus" src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="">
                                                                </a>
                                                            </div>
                                                            <div align="center" id="tiada${line_rowNum - 1}" >
                                                                Tiada Pembida 
                                                            </div>
                                                            <%--</c:if>--%>
                                                        </c:if>
                                                    </c:if>

                                                    <c:if test="${line2.lelong.idLelong eq line.idLelong}">
                                                        <c:if test="${line2.kodStsPembida.kod eq 'TB'}">
                                                            <div align="center" id="namaPembida${line_rowNum - 1}"></div>
                                                            <div align="center" id="edit${line_rowNum - 1}" >
                                                                <a id="" onclick="popupPembida('${line2.lelong.idLelong}','${line_rowNum - 1}');return false;" onmouseover="this.style.cursor='pointer';">
                                                                    <img alt="Sila Klik Untuk Masukkan Maklumat Pembida" title="Sila Klik Untuk Masukkan Maklumat Pembida" src='${pageContext.request.contextPath}/pub/images/edit.gif'>
                                                                </a>
                                                                &nbsp;
                                                                <a id="del${line_rowNum - 1}" onclick="deletePembida2('${line2.pihak.idPihak}','${line2.lelong.idLelong}');return false;" onmouseover="this.style.cursor='pointer';" >
                                                                    <img alt='Klik Untuk Hapus' title="Klik Untuk Hapus" src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="">
                                                                </a>
                                                            </div>
                                                            <div align="center" id="tiada${line_rowNum - 1}" >
                                                                Tiada Pembida 
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${line2.kodStsPembida.kod eq 'BJ'}">
                                                            <div align="center" id="namaPembida${line2_rowNum - 1}">${line2.pihak.nama}</div>
                                                            <div align="center">
                                                                <a onclick="popupPembida('${line2.lelong.idLelong}','${line2_rowNum - 1}');return false;" onmouseover="this.style.cursor='pointer';">
                                                                    <img alt="Sila Klik Untuk Masukkan Maklumat Pembida" title="Sila Klik Untuk Masukkan Maklumat Pembida" src='${pageContext.request.contextPath}/pub/images/edit.gif' >
                                                                </a>
                                                                &nbsp;
                                                                <a onclick="deletePembida2('${line2.pihak.idPihak}','${line2.lelong.idLelong}');return false;" onmouseover="this.style.cursor='pointer';" >
                                                                    <img alt='Klik Untuk Hapus' title="Klik Untuk Hapus" src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                                         id='rem_${line2_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="" >
                                                                </a>
                                                            </div>
                                                        </c:if> 
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${actionBean.owh eq true}">
                                            <div align="center">
                                                <c:if test="${line.kodStatusLelongan.kod ne 'TP'}">
                                                    <c:if test="${actionBean.lelong.idLelong eq null}">

                                                        <div align="center" id="namaPembida${line_rowNum - 1}"></div>
                                                        <div align="center" id="edit${line_rowNum - 1}" >
                                                            <a id="" onclick="popupPembida('${line2.lelong.idLelong}','${line_rowNum - 1}');return false;" onmouseover="this.style.cursor='pointer';">
                                                                <img alt="Sila Klik Untuk Masukkan Maklumat Pembida" title="Sila Klik Untuk Masukkan Maklumat Pembida" src='${pageContext.request.contextPath}/pub/images/edit.gif' >
                                                            </a>
                                                            &nbsp;
                                                            <a id="del${line_rowNum - 1}" onclick="deletePembida2('${line2.pihak.idPihak}','${line2.lelong.idLelong}');return false;" onmouseover="this.style.cursor='pointer';" >
                                                                <img alt='Klik Untuk Hapus'title="Klik Untuk Hapus" src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="" >
                                                            </a>
                                                        </div>
                                                        <div align="center" id="tarik${line_rowNum - 1}" >
                                                            Tarik Permohonan
                                                        </div>
                                                        <div align="center" id="tiada${line_rowNum - 1}" >
                                                            -
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${actionBean.lelong.idLelong ne null}">
                                                        <c:if test="${fn:length(actionBean.senaraiPembida)==0}">

                                                            <div align="center" id="namaPembida${line_rowNum - 1}"></div>
                                                            <div align="center" id="edit${line_rowNum - 1}" >
                                                                <a id="" onclick="popupPembida('${line2.lelong.idLelong}','${line_rowNum - 1}');return false;" onmouseover="this.style.cursor='pointer';">
                                                                    <img alt="Sila Klik Untuk Masukkan Maklumat Pembida" title="Sila Klik Untuk Masukkan Maklumat Pembida" src='${pageContext.request.contextPath}/pub/images/edit.gif' >
                                                                </a>
                                                                &nbsp;
                                                                <a id="del${line_rowNum - 1}" onclick="deletePembida2('${line2.pihak.idPihak}','${line2.lelong.idLelong}');return false;" onmouseover="this.style.cursor='pointer';" >
                                                                    <img alt='Klik Untuk Hapus'title="Klik Untuk Hapus" src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="" >
                                                                </a>
                                                            </div>
                                                            <div align="center" id="tarik${line_rowNum - 1}" >
                                                                Tarik Permohonan
                                                            </div>
                                                            <div align="center" id="tiada${line_rowNum - 1}" >
                                                                -
                                                            </div>
                                                        </c:if>
                                                        <c:forEach items="${actionBean.senaraiPembida}" var="line2">
                                                            <c:if test="${fn:length(actionBean.senaraiPembida)==1}">
                                                                <c:if test="${line2.lelong.idLelong ne line.idLelong}">

                                                                    <div align="center" id="namaPembida${line_rowNum - 1}"></div>
                                                                    <div align="center" id="edit${line_rowNum - 1}" >
                                                                        <a id="" onclick="popupPembida('${line.idLelong}','${line_rowNum - 1}');return false;" onmouseover="this.style.cursor='pointer';">
                                                                            <img alt="Sila Klik Untuk Masukkan Maklumat Pembida" title="Sila Klik Untuk Masukkan Maklumat Pembida" src='${pageContext.request.contextPath}/pub/images/edit.gif' >
                                                                        </a>
                                                                        &nbsp;
                                                                        <a id="del${line_rowNum - 1}" onclick="deletePembida2('${line2.pihak.idPihak}','${line2.lelong.idLelong}');return false;" onmouseover="this.style.cursor='pointer';" >
                                                                            <img alt='Klik Untuk Hapus'title="Klik Untuk Hapus" src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="" >
                                                                        </a>
                                                                    </div>
                                                                    <div align="center" id="tarik${line_rowNum - 1}" >
                                                                        Tarik Permohonan
                                                                    </div>
                                                                    <div align="center" id="tiada${line_rowNum - 1}" >
                                                                        -
                                                                    </div>
                                                                </c:if>
                                                            </c:if>
                                                            <c:if test="${line2.lelong.idLelong eq line.idLelong}">
                                                                <c:if test="${line2.kodStsPembida.kod eq 'TB'}">
                                                                    <div align="center" id="namaPembida${line_rowNum - 1}"></div>
                                                                    <div align="center" id="edit${line_rowNum - 1}" >
                                                                        <a id="" onclick="popupPembida('${line2.lelong.idLelong}','${line_rowNum - 1}');return false;" onmouseover="this.style.cursor='pointer';">
                                                                            <img alt="Sila Klik Untuk Masukkan Maklumat Pembida" title="Sila Klik Untuk Masukkan Maklumat Pembida" src='${pageContext.request.contextPath}/pub/images/edit.gif' >
                                                                        </a>
                                                                        &nbsp;
                                                                        <a id="del${line_rowNum - 1}" onclick="deletePembida2('${line2.pihak.idPihak}');return false;" onmouseover="this.style.cursor='pointer';" >
                                                                            <img alt='Klik Untuk Hapus'title="Klik Untuk Hapus" src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="" >
                                                                        </a>
                                                                    </div>
                                                                    <div align="center" id="tarik${line_rowNum - 1}" >
                                                                        Tarik Permohonan
                                                                    </div>
                                                                    <div align="center" id="tiada${line_rowNum - 1}" >
                                                                        -
                                                                    </div>
                                                                </c:if>
                                                                <c:if test="${line2.kodStsPembida.kod eq 'BJ'}">
                                                                    <div align="center" id="namaPembida${line_rowNum - 1}">${line2.pihak.nama}</div>
                                                                    <div align="center">
                                                                        <a onclick="popupPembida('${line2.lelong.idLelong}','${line_rowNum - 1}');return false;" onmouseover="this.style.cursor='pointer';">
                                                                            <img alt="Sila Klik Untuk Masukkan Maklumat Pembida" title="Sila Klik Untuk Masukkan Maklumat Pembida" src='${pageContext.request.contextPath}/pub/images/edit.gif' >
                                                                        </a>
                                                                        &nbsp;
                                                                        <c:if test="${kembali}">
                                                                            <a onclick="deletePembida2('${line2.pihak.idPihak}');return false;" onmouseover="this.style.cursor='pointer';" >
                                                                                <img alt='Klik Untuk Hapus' title="Klik Untuk Hapus" src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="" >
                                                                            </a>
                                                                        </c:if>
                                                                    </div>
                                                                </c:if>
                                                            </c:if>
                                                            <c:if test="${line.kodStatusLelongan.kod eq 'TP'}">
                                                                <div id="tarik2${line_rowNum - 1}">
                                                                    Tarik Permohonan
                                                                </div>
                                                                <c:if test="${kembali}">
                                                                    <div align="center" id="namaPembida${line_rowNum - 1}"></div>
                                                                    <div align="center" id="edit${line_rowNum - 1}" >
                                                                        <a id="" onclick="popupPembida('${line2.lelong.idLelong}','${line_rowNum - 1}');return false;" onmouseover="this.style.cursor='pointer';">
                                                                            <img alt="Sila Klik Untuk Masukkan Maklumat Pembida" title="Sila Klik Untuk Masukkan Maklumat Pembida" src='${pageContext.request.contextPath}/pub/images/edit.gif'>
                                                                        </a>
                                                                        &nbsp;
                                                                        <a id="del${line_rowNum - 1}" onclick="deletePembida2('${line2.lelong.idLelong}');return false;" onmouseover="this.style.cursor='pointer';" >
                                                                            <img alt='Klik Untuk Hapus' title="Klik Untuk Hapus" src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="" >
                                                                        </a>
                                                                    </div>
                                                                </c:if>
                                                            </c:if>

                                                        </c:forEach>
                                                    </c:if>
                                                </c:if>
                                            </div>
                                        </c:if>

                                    </display:column>

                                </display:table><br>
                                <p>
                                    <%--<s:button id="tarikPermohonan" name="tarikPermohonan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                                    <c:if test="${kembali}">
                                        <s:button name="kembali3" value="Kembali" onclick="doSubmit(this.form, this.name, 'page_div')" class="btn" />
                                    </c:if>--%>
                                </p>
                            </div>
                            <br>
                        </c:if>

                    </div>
                </fieldset>
            </div>
        </c:if>
    </c:if>
</s:form>
