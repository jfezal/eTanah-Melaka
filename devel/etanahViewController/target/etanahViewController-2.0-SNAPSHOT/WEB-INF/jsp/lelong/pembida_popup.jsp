<%-- 
    Document   : pembida_popup
    Created on : Jan 3, 2011, 2:51:29 PM
    Author     : mdizzat.mashrom
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

<script type="text/javascript">
    $(document).ready(function() {
        $("#nokp2").hide();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
        $("#idBank2").hide();
        $("#idRuj2").hide();
        $("#idCara").hide();
    });

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

    function pilihPembida(){
        var url;
        $('.pihak1').each(function(){
            if($(this).is(':checked')){
                var va = $(this).val();
                url = "${pageContext.request.contextPath}/lelong/keputusan_bidaan?findPembida&pembida=" + va;
                $.get(url,
                function(data){
                    if (data == null || data.length == 0){
                        alert("Maklumat tidak dijumpai");
                        return;
                    }
//                    var p = data.split(DELIM);
//                    $("#nama", window.opener.document).val(p[0]);
//                    $('#jenis',opener.document).val(p[1]);
//                    $('#nokp',opener.document).val(p[2]);
                    self.opener.loadPembida(data);
                    self.close();
                });
            }
        });
    }
    
    function simpanPihak(event, f){
        if(validation()){
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div', opener.document).html(data);
                self.opener.refreshPage();
                self.close();
            },'html');
        }
    }
    
    function cekNOKP(){
        var no = $("#jenis").val();
        alert(no);
        if(no == 'B'){
            $("#nokp2").hide();
            $("#nokp1").show();
        }else{
            $("#nokp2").show();
            $("#nokp1").hide();
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
        if($("#bida").val() == ""){
            alert('Sila masukkan " Harga Bidaan " terlebih dahulu.');
            $("#bida").focus();
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
        if(negeri == false){
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

        var num = document.getElementById('bida').value;
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
        $('#bida').val((((sign)?'':'-') + num + '.' + cents));
    }


    function calc2(value){

        var num = document.getElementById('bida').value;
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
        $('#bida').val((((sign)?'':'-') + num + '.' + cents));

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
        var bida = document.getElementById('bida').value;
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

        var num = document.getElementById('bida').value;
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
        $('#bida').val((((sign)?'':'-') + num + '.' + cents));
        $("#idCara").show();
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
    
    function save(event, f){
        if(validatela()){
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.close();
            },'html');
        }
    }

    function validatela(){
        var len = ${fn:length(actionBean.senaraiPembida)};
        //var len = $('.nama').length;
        for(var i=1; i<=len; i++){
            var ckd = $('#chkbox'+i).is(":checked");

            if(ckd == false && i == len){
                alert("Sila Pilih Pembida Berjaya");
                return false;
            }
            if(ckd == true){
                return true;
            }
        }
    }

    function popupPembida(val){
        window.open("${pageContext.request.contextPath}/lelong/keputusan_bidaan?pembidaPopup&idLelong="+val, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=1500,scrollbars=1");
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.stripes.lelong.KeputusanBidaanActionBean">
    <s:messages/>
    <s:errors/>&nbsp;
    <s:hidden id="idLelong" name="idLelong" value="${actionBean.lelong.idLelong}"/>
    <s:hidden id="idPihak" name="pembida.pihak.idPembida"/>
    <s:hidden id="idPembida" name="idPembida" value="${actionBean.idPembida}" />    
    <s:hidden id="idPermohonan" name="idPermohonan" value="${actionBean.idPermohonan}" />  
    <%--n9--%>
    <c:if test = "${actionBean.mlk eq false}">
            <div class="subtitle">
                <fieldset class="aras1">
                <br>
                <c:if test = "${actionBean.BJ ne true}">
                <c:if test = "${actionBean.pembidaSama eq true}">
                    <div class="content" align="center" >
                        <br
                            <legend>
                                Maklumat Senarai Pembida 1
                            </legend>
                            <c:if test="${fn:length(actionBean.senaraiPembida)==0}">
                                <p>
                                    <font color="red">*</font><em><font color="red" size="2">Masukkan Nama Pembida di Utiliti Pendaftaran Pembida </font></em>
                                </p>

                            </c:if>
                            <br>
                            <div class="content" align="center">
                                <display:table class="tablecloth" name="${actionBean.senaraiPembida}" 
                                               pagesize="10" id="line" 
                                               requestURI="${pageContext.request.contextPath}/lelong/keputusan_bidaan" excludedParams="${url}">
                                    <display:column>
                                        <s:radio name="__pilihPembida" class="pihak1" id="pilihPembida${line_rowNum}" value="${line.pihak.idPihak}"/>
                                    </display:column>
                                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                    <display:column title="Nama Pembida" property="pihak.nama" class="nama"/>
                                    <display:column title="Jenis Pengenalan" property="pihak.jenisPengenalan.nama" class="jenis"/>
                                    <display:column title="No Pengenalan" property="pihak.noPengenalan" class="nokp"/>
                                </display:table>
                            </div>
                    </div>
                </c:if>

                <c:if test = "${actionBean.pembidaSama ne true}">
                    <div class="subtitle displaytag" align="center" >
                        <br>
                        <fieldset class="aras1">
                            <legend>
                                Maklumat Senarai Pembida 2
                            </legend>
                            <c:if test="${fn:length(actionBean.senaraiPembida)==0}">
                                <p>
                                    <font color="red">*</font><em><font color="red" size="2">Masukkan Nama Pembida di Utiliti Pendaftaran Pembida </font></em>
                                </p>

                            </c:if>
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
                </fieldset>
            </div>
            <c:if test = "${actionBean.pembidaSama eq true}">
            <p align="center"><label></label>
                <br>
                <%--<s:button name="simpanPembida" value="Simpan" class="btn" id="add" onclick=""  />--%>
                <c:if test="${actionBean.buttonSimpan eq true}">
                    <%--<s:submit name="simpanPembidaFlowN9" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')self.close()"/>--%>
                    <s:button name="_pilih" value="Pilih" class="btn" onclick="pilihPembida()"/>               
                </c:if>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
            </c:if>
            <c:if test = "${actionBean.pembidaSama ne true}">
            <p align="center"><label></label>
                <br>
                <%--<s:button name="simpanPembida" value="Simpan" class="btn" id="add" onclick=""  />--%>
                <c:if test="${actionBean.buttonSimpan eq true}">
                    <s:submit name="simpanPembida" value="Simpan" class="btn" id="add"/>
                </c:if>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
            </c:if>
            </c:if>
       
        <c:if test = "${actionBean.BJ eq true}">

            <div class="subtitle">
                <fieldset class="aras1" id="">
                    <legend>
                        Maklumat Pembida

                    </legend>
                    <p>
                        <label><font color="red">*</font> Nama : </label>
                        <s:text id="nama" name="pembida.pihak.nama" onchange="this.value=this.value.toUpperCase();" style="width:260px;"/>
                    </p>
                    <p>
                        <label><font color="red">*</font>Jenis Pengenalan : </label>
                        <s:select id="jenis" name="pembida.pihak.jenisPengenalan.kod" style="width:150px;" onchange="changeNOKP(this.value);">
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
                        <s:select id="negeri" name="pembida.pihak.negeri.kod" style="width:150px;">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
                        </s:select>
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
                        <s:text name="lelong.hargaBidaan" id="bida" value="numver"  onkeyup="validateNumber(this,this.value);" onblur="validate(this.value)" onchange="calc(this.value);" formatPattern="###,###,###.00" style="width:150px;"/>&nbsp;
                    </p>
                    <p>
                        <label>Deposit (RM) : </label>
                        <s:format formatPattern="#,##0.00" value="${actionBean.deposit}" /> &nbsp;
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
                    <div class="content" align="center">
                        <p>
                            <c:if test="${bayarBaki eq false}">
                                <%--<c:if test="${actionBean.atd.jenisDeposit ne 'H'}">--%>
                                <s:button name="savePembida" id="savePembida" value="Simpan" class="btn" onclick="simpanPihak(this.name, this.form);"/>
                                <%--<s:button name="" value="Isi Semula" class="btn" />--%>
                                <%--</c:if>--%>
                            </c:if>

                            <%--<c:if test="${actionBean.atd.jenisDeposit eq 'H'}">
                                <s:button name="saveDepositTambahan" id="save" value="Simpan Deposit Tambahan" class="longbtn" onclick="simpanPihak(this.name, this.form);"/>
                                <s:button name="" value="Isi Semula" class="btn" />
                            </c:if>--%>

                            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                        </p>
                    </div>
                </fieldset>
            </div>
        </c:if>
        </c:if>



    <%--MLK--%>
    <c:if test = "${actionBean.mlk eq true}">
        <c:if test = "${actionBean.BJ eq false}">

            <div class="subtitle displaytag">
                <br>
                <c:if test = "${actionBean.pembidaSama eq true}">
                    <div class="subtitle displaytag" align="center" >
                        <br>
                        <fieldset class="aras1">
                            <legend>
                                Maklumat Senarai Pembida 1
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
                                Maklumat Senarai Pembida 2
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
            </div>
            <p align="center"><label></label>
                <br>
                <%--<s:button name="simpanPembida" value="Simpan" class="btn" id="add" onclick=""  />--%>
                <c:if test="${actionBean.buttonSimpan eq true}">
                    <s:submit name="simpanPembida" value="Simpan" class="btn" id="add"  />
                </c:if>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </c:if>


        <c:if test = "${actionBean.BJ eq true}">


            <div class="subtitle">
                <fieldset class="aras1" id="">
                    <legend>
                        Maklumat Pembida

                    </legend>
                    <p>
                        <label><font color="red">*</font> Nama : </label>
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
                        <s:select id="negeri" name="pembida.pihak.suratNegeri.kod" style="width:150px;">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
                        </s:select>
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
                            <s:format formatPattern="#,##0.00" value="${actionBean.pembida.lelong.hargaRizab}" /> &nbsp;
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
                        <c:if test="${actionBean.negeri eq true}">
                            <label>Cara Bayar : </label>Bank Deraf
                        </c:if>
                        <%--negeri9--%>
                        <c:if test="${actionBean.negeri eq false}">
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
                            <label><font color="red" id="idRuj">*</font> No.Rujukan : </label>
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
                        <c:if test="${actionBean.negeri eq true}">
                            <label>Cara Bayar : </label>Bank Deraf
                        </c:if>
                        <%--negeri9--%>
                        <c:if test="${actionBean.negeri eq false}">
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
                    <div class="content" align="center">
                        <p>
                            <c:if test="${bayarBaki eq false}">
                                <%--<c:if test="${actionBean.atd.jenisDeposit ne 'H'}">--%>
                                <s:button name="savePembidaMLK" id="save" value="Simpan" class="btn" onclick="simpanPihak(this.name, this.form);"/>
                                <%--<s:button name="" value="Isi Semula" class="btn" />--%>
                                <%--</c:if>--%>
                            </c:if>

                            <%--<c:if test="${actionBean.atd.jenisDeposit eq 'H'}">
                                <s:button name="saveDepositTambahan" id="save" value="Simpan Deposit Tambahan" class="longbtn" onclick="simpanPihak(this.name, this.form);"/>
                                <s:button name="" value="Isi Semula" class="btn" />
                            </c:if>--%>

                            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                        </p>
                    </div>
                </fieldset>
            </div>
        </c:if>
    </c:if>


</s:form>
