<%--
    Document   : kemasukan_pihak_berkepentingan
    Created on : 15-Oct-2009, 03:49:43
    Author     : md.nurfikri
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ageCalculator.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $('.empty').remove();
        $('.alphanumeric').alphanumeric();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $('#jenisPengenalan').change(function(){
            dodacheck($("#kp").val());
        });

        var v = $('#multiple').val();
        if (v == 'true'){
            $('#multiple_check').show();
        }else{
            $('#multiple_check').hide();
        }


        $("#pgAmanahDialog").dialog({
			autoOpen: false,
			height: 300,
			width: 900,
			modal: true,
			buttons: {
				'Simpan': function() {
					var bValid = true;
					bValid = bValid && ($('#namaAmanah').val() != '');
                                        bValid = bValid && ($('#kpAmanah').val() != '');
                                        bValid = bValid && ($('#nokpAmanah').val() != '');
                                        bValid = bValid && ($('#alamatAmanah1').val() != '');
                                        bValid = bValid && ($('#poskodAmanah').val() != '');
                                        bValid = bValid && ($('#negeriAmanah').val() != '');

					if (bValid) {
                                            var nama = $('#namaAmanah').val();
                                            var kp = $('#kpAmanah').val();
                                            var noKp = $('#nokpAmanah').val();
                                            var add1 = $('#alamatAmanah1').val();
                                            var add2 = $('#alamatAmanah2').val();
                                            var add3 = $('#alamatAmanah3').val();
                                            var add4 = $('#alamatAmanah4').val();
                                            var poskod = $('#poskodAmanah').val();
                                            var negeri = $('#negeriAmanah').val();

                                            var rowNo = $('table#pgAmanahTable tr').length;
                                                
						$('table#pgAmanahTable tbody').append("<tr id='x"+rowNo+"' class='x'>" +
                                                    '<td>' + rowNo + '</td>' +
							"<td><input type=hidden name='namaPA' value='"+nama+"'/>" + nama + '</td>' +
							"<td><input type=hidden name='kpPA' value='"+kp+"'/>" + $('#kpAmanah option:selected').text() + '</td>' +
							"<td><input type=hidden name='noKpPA' value='"+noKp+"'/>" + noKp + '</td>' +
                                                        "<td><input type=hidden name='add1PA' value='"+add1+"'/>" + add1 + '</td>' +
                                                        "<td><input type=hidden name='add2PA' value='"+add2+"'/>" + add2 + '</td>' +
                                                        "<td><input type=hidden name='add3PA' value='"+add3+"'/>" + add3 + '</td>' +
                                                        "<td><input type=hidden name='add4PA' value='"+add4+"'/>" + add4 + '</td>' +
                                                        "<td><input type=hidden name='poskodPA' value='"+poskod+"'/>" + poskod + '</td>' +
                                                        "<td><input type=hidden name='negeriPA' value='"+negeri+"'/>" + $('#negeriAmanah option:selected').text() + '</td>' +
							'</tr>');
						$(this).dialog('close');
					} else {
                                            alert('Sila masukan maklumat pihak dipegang amanah dengan lengkap. ');
                                        }
				},
				'Tutup' : function() {
					$(this).dialog('close');
				}
			},
			close: function() {
				$('#namaAmanah').val('');
                                $('#kpAmanah').val('');
                                $('#nokpAmanah').val('');
                                $('#alamatAmanah1').val('');
                                $('#alamatAmanah2').val('');
                                $('#alamatAmanah3').val('');
                                $('#alamatAmanah4').val('');
                                $('#poskodAmanah').val('');
                                $('#negeriAmanah').val('');
			}
		});


        $('#addPA').click(function(){            
            $('#pgAmanahDialog').dialog('open');
        });

        var v = '${actionBean.pihak.jenisPengenalan.kod}';

        if(v == "B" || v == "L" || v == "P" || v == "T" || v == "I" || v == "F"){
            $('#kodBangsa').hide();
            $('#kodBangsaOrang').show();
            $('#kodBangsaSyarikat').hide();
            $('#tableCaw').hide();
            $('#btnCaw').hide();
            $('#btnAhli').hide();
        }

        else{
            $('#kodBangsa').hide();
            $('#kodBangsaOrang').hide();
            $('#kodBangsaSyarikat').show();
            $('#tableCaw').show();
            $('#btnCaw').show();
            $('#btnAhli').show();
        }

        if(v == ''){
            $('#kodBangsa').show();
            $('#kodBangsaOrang').hide();
            $('#kodBangsaSyarikat').hide();
            $('#tableCaw').hide();
            $('#btnCaw').hide();
            $('#btnAhli').hide();
        }

        if(v == "S"){
            $('#suku').hide();
        }

        else{
            $('#suku').show();
        }

        $('form').submit(function(){
            var val = $('#kp').val();
            var jenis = '${jenis}';            
            return doCheckUmur(val, 'kp',jenis);
        });
        
        if(v == "B"){      
            var icNo = '${actionBean.pihak.noPengenalan}';

            var year = icNo.substring(0,2);

            if(year > 25 && year < 99){//fixme :
                year = "19" + year;
            }else {
                year = "20" + year;
            }

            var age = calculateAge(icNo.substring(4,6), icNo.substring(2,4), year);
            $('#umur').val(age);
            $('#datepicker').val(icNo.substring(4,6)+"/"+icNo.substring(2,4)+"/"+year);
        }

        $('#kod_warganegara').val('MAL');
        $('#kod_warga_pengarah').val('MAL');

        var jenisPihak = $('#jenis_pihak').val();

        if(jenisPihak == "Pemegang Amanah" || jenisPihak == "PA"){
            $('#pgAmanah').show();
        }else{
            $('#pgAmanah').hide();
        }
    });

    function calAgeByDOB(value){

        var year = value.substring(8,10);

        if(year > 25 && year < 99){//fixme :
            year = "19" + year;
        }else {
            year = "20" + year;
        }

        var age = calculateAge(value.substring(0,2), value.substring(3,5), year);
        $('#umur').val(age);
    }

    
    function changeHideSuku(value){
        if(value == "S"){
            $('#suku').hide();
        }else{
            $('#suku').show();
        }
    }

    function changePgAmanah(value){
        if(value == "Pemegang Amanah"){
            $('#pgAmanah').show();
        }else{
            $('#pgAmanah').hide();
        }
    }

    function doValidation(){
        var val = $('#kod_warganegara').val();
        var val2 = $('#alamat1').val();
        var val3 = $('#nama_pemohon').val();
        var val4 = $('#jenis_pihak').val();
        if(val3 == ''){
            alert('Sila masukan nama pihak.');
            return false;
        } else if(val == ''){
            alert('Sila pilih warganegara.');
            return false;
        }else if(val2 == ''){
            alert('Sila masukan alamat berdaftar.');
            return false;
        }else if (val4 == ''){
            alert('Sila Masukan Jenis Pihak Berkepentingan.');
            return false;
        }

        <c:if test="${actionBean.p.kodUrusan.kod ne 'PNPA' && actionBean.p.kodUrusan.kod ne 'TRPA' }">
            if(val4 == "PA"){
                var val5 = $('#namaAmanah').val();
                var val6 = $('#kpAmanah').val();
                var val7 = $('#nokpAmanah').val();
                var val8 = $('#alamatAmanah1').val();

                //if(val5 == '' || val6 == '' || val7 == '' || val8 == ''){
                //    alert('Sila masukan maklumat pihak dipegang amanah dengan lengkap.');
                //    return false;
                //}
            }
        </c:if>
        return true;
    }

    function validationPB(){    
        if ($('#jenis_pihak').val() == ''){
            alert('Sila Masukan Jenis Pihak Berkepentingan.');
            return false;
        }

        else  if ($('#alamat1').val() == ''){
            alert('Sila Masukan Alamat Berdaftar.');
            return false;
        }
        return true;
    }

    function save(event, f){
        var v = $('#multiple').val();
        if(doValidation()){

            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });

            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){                
            if (v == 'true') {
                $('#multiple_check').show();
                if (data == '1') {
                            $('#page_div',opener.document).html('Terdapat masalah berlaku.');
                                 self.close();
                            }else if (data == '2'){
                                //alert('Pihak dengan jenis pihak yang sama telah dipilih. Sila Pilih Jenis Pihak yang berlainan.');
                            }else {
                                $('#div_content',opener.document).html(data);
                                 self.close();
                            }
                            $.unblockUI();
                        self.close();
            } else{
                $('#multiple_check').hide();
                 if (data == '1') {
                                $('#page_div',opener.document).html('Terdapat masalah berlaku.');
                                $.unblockUI();
                                 self.close();
                            }else if (data == '2'){
                                alert('Pihak dengan jenis pihak yang sama telah dipilih. Sila Pilih Jenis Pihak yang berlainan.');
                            }else {
                                $('#page_div',opener.document).html(data);
                                $.unblockUI();
                                 self.close();
                            }
                
            }                              
                },'html');
            }
        }

        function savePemohon(event, f){

            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
    <c:choose>
        <c:when test="${multiple}">
                    $('#div_content',opener.document).html(data);
        </c:when>
        <c:otherwise>
                    if (data == '1') {
                        $('#page_div',opener.document).html('Terdapat masalah berlaku.');
                    }else {
                        $('#page_div',opener.document).html(data);
                    }
        </c:otherwise>
    </c:choose>
                $.unblockUI();
                self.close();
            },'html');
        }

        function changeHideSuku(value){

            if(value == "S"){
                $('#suku').hide();
            }

            else{
                $('#suku').show();
            }
        }

        function changeJenisKP(value){
            if(value == "B" || value == "L" || value == "P" || value == "T" || value == "I" || value == "F"){
                $('#kodBangsa').hide();
                $('#kodBangsaOrang').show();
                $('#kodBangsaSyarikat').hide();
                $('#tableCaw').hide();
                $('#btnCaw').hide();
                $('#btnAhli').hide();
            }

            else{
                $('#kodBangsa').hide();
                $('#kodBangsaOrang').hide();
                $('#kodBangsaSyarikat').show();
                $('#tableCaw').show();
                $('#btnCaw').show();
                $('#btnAhli').show();
            }
        }

        function copyAddCaw(){
            if($('input[name=addCaw]').is(':checked')){
                $('#suratAlamatCaw1').val($('#alamatCaw1').val());
                $('#suratAlamatCaw2').val($('#alamatCaw2').val());
                $('#suratAlamatCaw3').val($('#alamatCaw3').val());
                $('#suratAlamatCaw4').val($('#alamatCaw4').val());
                $('#suratPoskodCaw').val($('#poskodCaw').val());
                $('#suratNegeriCaw').val($('#negeriCaw').val());
            }else{
                $('#suratAlamatCaw1').val('');
                $('#suratAlamatCaw2').val('');
                $('#suratAlamatCaw3').val('');
                $('#suratAlamatCaw4').val('');
                $('#suratPoskodCaw').val('');
                $('#suratNegeriCaw').val('');

            }
        }

        function copyAdd(){
            if($('input[name=add1]').is(':checked')){
                $('#suratAlamat1').val($('#alamat1').val());
                $('#suratAlamat2').val($('#alamat2').val());
                $('#suratAlamat3').val($('#alamat3').val());
                $('#suratAlamat4').val($('#alamat4').val());
                $('#suratPoskod').val($('#poskod').val());
                $('#suratNegeri').val($('#negeri').val());
            }else{
                $('#suratAlamat1').val('');
                $('#suratAlamat2').val('');
                $('#suratAlamat3').val('');
                $('#suratAlamat4').val('');
                $('#suratPoskod').val('');
                $('#suratNegeri').val('');

            }
        }

        function removeCawangan(val){
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pihak_berkepentingan?deleteCawangan&idCawangan='+val;
                $.get(url,
                function(data){
                    $('#caw').html(data);
                },'html');
                location.reload(true);
            }
        }

        function removeCawanganPemohon(val){
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pihak_berkepentingan?deleteCawanganPemohon&idCawangan='+val;
                $.get(url,
                function(data){
                    $('#caw').html(data);
                },'html');
                location.reload(true);
            }
        }

        function removePengarah(val){
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pihak_berkepentingan?deletePengarah&idPengarah='+val;
                $.get(url,
                function(data){
                    $('#caw').html(data);
                },'html');
                location.reload(true);
            }
        }

        function removePengarahPemohon(val){
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pihak_berkepentingan?deletePengarahPemohon&idPengarah='+val;
                $.get(url,
                function(data){
                    $('#caw').html(data);
                },'html');
                location.reload(true);
            }
        }


        function editCawangan(val){
            var jenisPB = $('#jenis_pihak').val();
            var url = '${pageContext.request.contextPath}/pihak_berkepentingan?editCawangan&idCawangan='+val+'&jenisPB='+jenisPB;
            $.get(url,
            function(data){
                $('#caw').html(data);
            },'html');
        }
    
        function editCawanganPemohon(val){
            var url = '${pageContext.request.contextPath}/pihak_berkepentingan?editCawanganPemohon&idCawangan='+val;
            $.get(url,
            function(data){
                $('#caw').html(data);
            },'html');
        }

        function editPengarah(val){
            var jenisPB = $('#jenis_pihak').val();
            var url = '${pageContext.request.contextPath}/pihak_berkepentingan?editPengarah&idPengarah='+val+'&jenisPB='+jenisPB;
            $.get(url,
            function(data){
                $('#caw').html(data);
            },'html');
        }

        function editPengarahPemohon(val){
            var url = '${pageContext.request.contextPath}/pihak_berkepentingan?editPengarahPemohon&idCawangan='+val;
            $.get(url,
            function(data){
                $('#caw').html(data);
            },'html');
        }

        function selectName(val){
            var url = '${pageContext.request.contextPath}/pihak_berkepentingan?selectName&idPihak='+val;
            $.get(url,
            function(data){
                $('#caw').html(data);
                $('#hakmilik').val('${hakmilik}');
                $('#multiple').val('${multiple}');
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

        function dodacheck(val) {
            //var mikExp = /[1\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\1\|]/;
            var v = $('#jenisPengenalan').val();

            if(v == 'B') {
                var strPass = val;
                var strLength = strPass.length;
                //$('#kp').attr("maxlength","12");
                if(strLength > 12) {
                    var tst = val.substring(0, (strLength) - 1);
                    $('#kp').val(tst);
                }
                var lchar = val.charAt((strLength) - 1);
                if(isNaN(lchar)) {
                    //return false;
                    var tst = val.substring(0, (strLength) - 1);
                    $('#kp').val(tst);
                }
            }//else{
            // $('#kp').attr("maxlength","30");
            // }
        }

        function doCheckUmur(v,id,type){
            var va = $('#jenisPengenalan').val();
            if(va == 'B'){
                return doValidateAge(v, id, 'jenisPengenalan',type);
            }else {
                return true;
            }
        }
    
</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<div class="subtitle" id="caw">
    <s:form beanclass="etanah.view.stripes.common.PihakBerkepentinganActionBean" name="form1">
        <s:hidden name="jenisPihak"/>
        <s:hidden name="pihak.nama"/>
        <s:hidden name="pihak.idPihak"/>
        <s:hidden name="pihak.jenisPengenalan.kod"/>
        <s:hidden name="pihak.jenisPengenalan.nama"/>
        <s:hidden name="pihak.noPengenalan"/>
        <s:hidden name="pihak.bangsa.kod"/>
        <s:hidden name="pihak.suku.kod"/>
        <s:hidden name="permohonanPihak.jenis.kod"/>
        <s:hidden name="pihakCawangan.idPihakCawangan"/>
        <s:hidden name="jenisPihak" value="${jenis}"/>
        <s:hidden name="hakmilik" value="${hakmilik}" id="hakmilik"/>
        <s:hidden id="multiple" value="${multiple}" name="multiple"/>

        <c:if test="${tuanTanah}">
            <s:text name="tuanTanah" value="${tuanTanah}"/>
        </c:if>
        <s:errors/>
        <s:messages/>
        <c:if test="${!actionBean.tambahCawFlag && !actionBean.tambahPengarahFlag}">

            <fieldset class="aras1">
                <legend>
                    <c:if test="${pemohon}">
                        Kemasukan Maklumat Pemohon
                    </c:if>

                    <c:if test="${!pemohon}">
                        <c:choose>
                            <c:when test="${jenis eq 'PA'}">
                                Kemasukan Maklumat Pemegang Amanah
                            </c:when>
                            <c:when test="${jenis eq 'WAR'}">
                                Kemasukan Maklumat Penerima Amanah
                            </c:when>
                            <c:when test="${actionBean.p.kodUrusan.kod eq 'KVPT' || actionBean.p.kodUrusan.kod eq 'KVPP'}">
                                Kemasukan Maklumat Pengkaveat
                            </c:when>
                            <c:otherwise>
                                Kemasukan Maklumat Penerima
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </legend>
                <br/>

                <c:if test="${!actionBean.cariFlag}">

                    <c:if test="${actionBean.kodNegeri eq '05'}">
                        <p>
                            <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                            <s:select name="pihak.jenisPengenalan.kod" id="jenisPengenalan"  onchange="javaScript:changeHideSuku(this.options[selectedIndex].text);">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                    </c:if>

                    <c:if test="${actionBean.kodNegeri eq '04'}">
                        <p>
                            <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                            <s:select name="pihak.jenisPengenalan.kod" id="jenisPengenalan">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                    </c:if>
                    <br/>
                    <p>
                        <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                        <s:text name="pihak.noPengenalan" id="kp" size="40" onkeyup="dodacheck(this.value);this.value=this.value.toUpperCase();" 
                                onblur="doCheckUmur(this.value, this.id,'${jenis}')"/>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        atau
                    </p>
                    <p>
                        <label for="nama"><font color="red">*</font>Nama :</label>
                        <s:text name="pihak.nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <c:if test="${fn:length(actionBean.pihakByNameList) > 0}">
                        <div align="center">
                            <display:table class="tablecloth" name="${actionBean.pihakByNameList}" cellpadding="0" cellspacing="0" id="line" pagesize="10"
                                           requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                <display:column  title="Nama" class="nama">
                                    <a href="#" onclick="selectName('${line.idPihak}');return false;">${line.nama}</a>
                                </display:column>
                                <display:column property="jenisPengenalan.nama" title="Jenis Pengenalan" />
                                <display:column property="noPengenalan" title="Nombor Pengenalan" />
                            </display:table>
                        </div>
                    </c:if>
                </c:if>

                <c:if test="${actionBean.cariFlag}">
                    <p id="multiple_check">
                        <label>&nbsp;</label>
                        <font color="red" size="2">
                            <input type="checkbox" name="copyToAll" value="1"/>
                                Sila klik jika sama untuk semua hakmilik.
                            </font>
                    </p>

                    <c:if test="${!actionBean.tiadaDataFlag}">
                        <p>
                            <label for="nama">Nama :</label>
                            ${actionBean.pihak.nama}&nbsp;
                        </p>
                        <p>
                            <label for="Jenis Pengenalan">Jenis Pengenalan :</label>
                            ${actionBean.pihak.jenisPengenalan.nama}&nbsp;
                        </p>
                        <p>
                            <label for="No Pengenalan">Nombor Pengenalan :</label>
                            ${actionBean.pihak.noPengenalan}&nbsp;
                        </p>

                        <c:if test="${actionBean.pihak.bangsa.kod ne null}">
                            <p>
                                <label>Bangsa / Jenis Syarikat :</label>
                                ${actionBean.pihak.bangsa.nama}&nbsp;
                            </p>
                        </c:if>

                        <c:if test="${actionBean.kodNegeri eq '05'}">
                            <c:if test="${actionBean.pihak.suku.kod ne null}">
                                <p id="suku">
                                    <label>Jenis Suku :</label>
                                    ${actionBean.pihak.suku.nama}&nbsp;
                                </p>
                            </c:if>
                        </c:if>

                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                            <p>
                                <label>Tarikh Lahir :</label>
                                <s:text name="tarikhLahir" size="40" id="datepicker" class="datepicker" onchange="calAgeByDOB(this.value);"/>
                            </p>
                            <p>
                                <label>Umur :</label>
                                <s:text name="permohonanPihak.umur" size="10" maxlength="3" id="umur" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Tempat Lahir :</label>
                                <s:select name="pihak.tempatLahir">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="nama"/>
                                </s:select>
                            </p>
                            <p>
                                <label>Kewarganegaraan :</label>
                                <s:hidden name="pihak.wargaNegara.kod" id=""/>
                                <s:select name="pihak.wargaNegara.kod" style="width:200px" id="kod_warganegara">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                            <p>
                                <label>Status Perkahwinan :</label>
                                <s:select name="permohonanPihak.statusKahwin">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option>Berkahwin</s:option>
                                    <s:option>Bujang</s:option>
                                    <s:option>Duda</s:option>
                                    <s:option>Ibu Tunggal</s:option>
                                </s:select>
                            </p>
                            <p>
                                <label>Pekerjaan :</label>
                                <s:text name="permohonanPihak.pekerjaan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label>Pendapatan Bulanan (RM) :</label>
                                <s:text name="permohonanPihak.pendapatan" size="40" maxlength="12" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Tanggungan :</label>
                                <s:text name="permohonanPihak.tangungan" size="40" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Hubungan :</label>
                                <s:select name="permohonanPihak.kaitan">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option>IBU BAPA KEPADA ANAK</s:option>
                                    <s:option>ANAK KEPADA IBU BAPA</s:option>
                                    <s:option>SUAMI KEPADA ISTERI</s:option>
                                    <s:option>ISTERI KEPADA SUAMI</s:option>
                                    <s:option>SAUDARA-MARA</s:option>
                                    <s:option>PENJUAL / PEMBELI</s:option>
                                    <s:option>LAIN-LAIN</s:option>
                                </s:select>
                            </p>
                        </c:if>

                        <c:if test="${!pemohon}">
                            <p>
                                <label for="nama"><font color="red">*</font>Jenis Pihak Berkepentingan :</label>
                                <c:choose>
                                    <c:when test="${actionBean.p.kodUrusan.kod ne 'PNPA' && actionBean.p.kodUrusan.kod ne 'TRPA' }">
                                        <s:select name="jenisPihak" style="width:400px" id="jenis_pihak" onchange="javaScript:changePgAmanah(this.options[selectedIndex].text);">
                                            <c:if test="${fn:length(actionBean.senaraiKodPenerima) > 1}">
                                                <s:option value="" >Sila Pilih</s:option>
                                              </c:if>
                                            <s:options-collection collection="${actionBean.senaraiKodPenerima}" label="nama" value="kod" />
                                        </s:select>
                                    </c:when>
                                    <c:otherwise>
                                        <s:select name="jenisPihak" style="width:400px" id="jenis_pihak">
                                            <c:if test="${fn:length(actionBean.senaraiKodPenerima) > 1}">
                                                <s:option value="" >Sila Pilih</s:option>
                                              </c:if>
                                              <s:options-collection collection="${actionBean.senaraiKodPenerima}" label="nama" value="kod" />
                                        </s:select>
                                    </c:otherwise>
                                </c:choose>                                
                            </p>
                        </c:if>

                        <p>
                            <label for="alamat"><font color="red">*</font>Alamat Berdaftar :</label>
                            <s:text name="pihak.alamat1" id="alamat1" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.alamat2" id="alamat2" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.alamat3" id="alamat3" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Bandar :</label>
                            <s:text name="pihak.alamat4" id="alamat4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="pihak.poskod" id="poskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select name="pihak.negeri.kod" id="negeri">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <p>
                            <label>&nbsp;</label>
                            <input type="checkbox" id="add1" name="add1" value="" onclick="copyAdd();"/>
                            <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                        </p>
                        <p>
                            <label for="alamat">Alamat Surat-Menyurat :</label>
                            <s:text name="pihak.suratAlamat1" id="suratAlamat1" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat2" id="suratAlamat2" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat3" id="suratAlamat3" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Bandar :</label>
                            <s:text name="pihak.suratAlamat4" id="suratAlamat4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="pihak.suratPoskod" id="suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select name="pihak.suratNegeri.kod" id="suratNegeri">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                    </c:if>
                    <%--tiada data--%>
                    <c:if test="${actionBean.tiadaDataFlag}">
                        <p>
                            <label for="nama"><font color="red">*</font>Nama :</label>
                            <s:text name="pihak.nama" id="nama_pemohon" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>

                        <c:if test="${actionBean.kodNegeri eq '05'}">
                            <p>
                                <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                <s:select name="pihak.jenisPengenalan.kod" onchange="javaScript:changeHideSuku(this.options[selectedIndex].text)">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                        </c:if>

                        <c:if test="${actionBean.kodNegeri eq '04'}">
                            <p>
                                <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                <s:select name="pihak.jenisPengenalan.kod" onchange="javaScript:changeJenisKP(this.options[selectedIndex].value)">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                        </c:if>

                        <p>
                            <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                            <s:text name="pihak.noPengenalan" id="kp" size="40" onkeyup="dodacheck(this.value);this.value=this.value.toUpperCase();"/>
                        </p>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L'
                                      || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T'
                                      || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                              <p>
                                  <label><font color="red">*</font>Kewarganegaraan :</label>
                                  <s:select name="pihak.wargaNegara.kod" style="width:200px" id="kod_warganegara">
                                      <s:option value="">Sila Pilih</s:option>
                                      <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                                  </s:select>
                              </p>
                        </c:if>
                        <c:if test="${!pemohon}">
                            <p>
                                <label for="nama"><font color="red">*</font>Jenis Pihak Berkepentingan :</label>
                                <%--s:select name="jenisPihak" style="width:400px" id="jenis_pihak" onchange="javaScript:changePgAmanah(this.options[selectedIndex].text);">
                                    <s:option value="" >Sila Pilih</s:option>
                                    <s:options-collection collection="${actionBean.senaraiKodPenerima}" label="nama" value="kod" />
                                </s:select--%>
                                <c:choose>
                                    <c:when test="${actionBean.p.kodUrusan.kod ne 'PNPA' && actionBean.p.kodUrusan.kod ne 'TRPA' }">
                                        <s:select name="jenisPihak" style="width:400px" id="jenis_pihak" onchange="javaScript:changePgAmanah(this.options[selectedIndex].text);">
                                            <c:if test="${fn:length(actionBean.senaraiKodPenerima) > 1}">
                                                <s:option value="" >Sila Pilih</s:option>
                                              </c:if>
                                            <s:options-collection collection="${actionBean.senaraiKodPenerima}" label="nama" value="kod" />
                                        </s:select>
                                    </c:when>
                                    <c:otherwise>
                                        <s:select name="jenisPihak" style="width:400px" id="jenis_pihak">
                                            <c:if test="${fn:length(actionBean.senaraiKodPenerima) > 1}">
                                                <s:option value="" >Sila Pilih</s:option>
                                              </c:if>
                                              <s:options-collection collection="${actionBean.senaraiKodPenerima}" label="nama" value="kod" />
                                        </s:select>
                                    </c:otherwise>
                                </c:choose>    
                            </p>
                        </c:if>
                        <p>
                            <label for="alamat"><font color="red">*</font>Alamat Berdaftar :</label>
                            <s:text name="pihak.alamat1" size="40" id="alamat1" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>

                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.alamat2" size="40" id="alamat2" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>

                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.alamat3" size="40" id="alamat3" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Bandar :</label>
                            <s:text name="pihak.alamat4" size="40" id="alamat4" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="pihak.poskod" size="40" maxlength="5" id="poskod" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select name="pihak.negeri.kod" id="negeri">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>

                        <p>
                            <label>Bangsa / Jenis Syarikat :</label>
                            <s:select name="pihak.bangsa.kod" style="width:400px" id="kodBangsa">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraiKodBangsa}" label="nama" value="kod"/>
                            </s:select>
                            <s:select name="pihak.bangsa.kod" style="width:400px" id="kodBangsaOrang">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraiKodBangsaOrang}" label="nama" value="kod"/>
                            </s:select>
                            <s:select name="pihak.bangsa.kod" style="width:400px" id="kodBangsaSyarikat">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraiKodBangsaSyarikat}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <c:if test="${actionBean.kodNegeri eq '05'}">
                            <p id="suku">
                                <label for="Suku">Jenis Suku :</label>
                                <s:select name="pihak.suku.kod" style="width:200px">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodSuku}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                        </c:if>
                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                            <p>
                                <label>Tarikh Lahir :</label>
                                <s:text name="tarikhLahir" size="40" id="datepicker" class="datepicker" onchange="calAgeByDOB(this.value);"/>
                            </p>
                            <p>
                                <label>Umur :</label>
                                <s:text name="permohonanPihak.umur" size="10" maxlength="3" id="umur" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Tempat Lahir :</label>
                                <s:select name="pihak.tempatLahir">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="nama"/>
                                </s:select>
                            </p>                           
                            <p>
                                <label>Status Perkahwinan :</label>
                                <s:select name="permohonanPihak.statusKahwin">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option>Berkahwin</s:option>
                                    <s:option>Bujang</s:option>
                                    <s:option>Duda</s:option>
                                    <s:option>Ibu Tunggal</s:option>
                                </s:select>
                            </p>
                            <p>
                                <label>Pekerjaan :</label>
                                <s:text name="permohonanPihak.pekerjaan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label>Pendapatan Bulanan (RM) :</label>
                                <s:text name="permohonanPihak.pendapatan" size="40" maxlength="12" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Tanggungan :</label>
                                <s:text name="permohonanPihak.tangungan" size="40" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Hubungan :</label>
                                <s:select name="permohonanPihak.kaitan">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option>IBU BAPA KEPADA ANAK</s:option>
                                    <s:option>ANAK KEPADA IBU BAPA</s:option>
                                    <s:option>SUAMI KEPADA ISTERI</s:option>
                                    <s:option>ISTERI KEPADA SUAMI</s:option>
                                    <s:option>SAUDARA-MARA</s:option>
                                    <s:option>PENJUAL / PEMBELI</s:option>
                                    <s:option>LAIN-LAIN</s:option>
                                </s:select>
                            </p>
                        </c:if>                       

                        <p>
                            <label>&nbsp;</label>
                            <input type="checkbox" id="add1" name="add1" value="" onclick="copyAdd();"/>
                            <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>                        
                        </p>

                        <p>
                            <label for="alamat">Alamat Surat-Menyurat :</label>
                            <s:text name="pihak.suratAlamat1" id="suratAlamat1" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat2" id="suratAlamat2" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat3" id="suratAlamat3" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Bandar :</label>
                            <s:text name="pihak.suratAlamat4" id="suratAlamat4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="pihak.suratPoskod" id="suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select name="pihak.suratNegeri.kod" id="suratNegeri">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>

                    </c:if>
                    <c:if test="${actionBean.pihak.jenisPengenalan.kod ne 'B' && actionBean.pihak.jenisPengenalan.kod ne 'L' && actionBean.pihak.jenisPengenalan.kod ne 'P' && actionBean.pihak.jenisPengenalan.kod ne 'T' && actionBean.pihak.jenisPengenalan.kod ne 'I'}">
                        <div class="content" align="center" id="tableCaw">
                            <br>
                            Maklumat Cawangan

                            <display:table name="${actionBean.cawanganList}" id="line" class="tablecloth" >

                                <display:column title="Pilih"><s:radio name="idCawangan" value="${line.idPihakCawangan}"/></display:column>
                                <display:column property="namaCawangan" title="Nama"/>
                                <display:column title="Alamat" >${line.suratAlamat1}
                                    <c:if test="${line.suratAlamat2 ne null}"> , </c:if>
                                    ${line.suratAlamat2}
                                    <c:if test="${line.suratAlamat3 ne null}"> , </c:if>
                                    ${line.suratAlamat3}
                                    <c:if test="${line.suratAlamat4 ne null}"> , </c:if>
                                    ${line.suratAlamat4}
                                    <c:if test="${line.suratPoskod ne null}"> , </c:if>
                                    ${line.suratPoskod}
                                    <c:if test="${line.suratNegeri ne null}"> , </c:if>
                                    ${line.suratNegeri.nama}
                                </display:column>                            
                                <c:if test="${pemohon}">
                                    <display:column title="Kemaskini">
                                        <p align="center">
                                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                 onclick="editCawanganPemohon('${line.idPihakCawangan}')" onmouseover="this.style.cursor='pointer';">
                                        </p>
                                    </display:column>
                                    <display:column title="Hapus">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="removeCawanganPemohon('${line.idPihakCawangan}')" onmouseover="this.style.cursor='pointer';">
                                        </div>
                                    </display:column>
                                </c:if>

                                <c:if test="${!pemohon}">
                                    <display:column title="Kemaskini">
                                        <p align="center">
                                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                 onclick="editCawangan('${line.idPihakCawangan}')" onmouseover="this.style.cursor='pointer';">
                                        </p>
                                    </display:column>
                                    <display:column title="Hapus">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="removeCawangan('${line.idPihakCawangan}')" onmouseover="this.style.cursor='pointer';">
                                        </div>
                                    </display:column>
                                </c:if>
                            </display:table>
                            <br>
                            Maklumat Ahli Lembaga Pengarah

                            <display:table name="${actionBean.pengarahList}" id="line2" class="tablecloth" >
                                <display:column property="nama" title="Nama"/>
                                <display:column property="noPengenalan" title="Nombor Pengenalan"/>

                                <c:if test="${pemohon}">
                                    <display:column title="Kemaskini">
                                        <p align="center">
                                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                 onclick="editPengarahPemohon('${line2.idPengarah}')" onmouseover="this.style.cursor='pointer';">
                                        </p>
                                    </display:column>
                                    <display:column title="Hapus">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id='rem_${line2_rowNum}' onclick="removePengarahPemohon('${line2.idPengarah}')" onmouseover="this.style.cursor='pointer';">
                                        </div>
                                    </display:column>
                                </c:if>

                                <c:if test="${!pemohon}">
                                    <display:column title="Kemaskini">
                                        <p align="center">
                                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                 onclick="editPengarah('${line2.idPengarah}')" onmouseover="this.style.cursor='pointer';">
                                        </p>
                                    </display:column>
                                    <display:column title="Hapus">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id='rem_${line2_rowNum}' onclick="removePengarah('${line2.idPengarah}')" onmouseover="this.style.cursor='pointer';">
                                        </div>
                                    </display:column>
                                </c:if>

                            </display:table>
                        </div>
                    </c:if>
                     <br/>
                    <div id="pgAmanah">
                         <div align="center">
                            <display:table class="tablecloth" name="${actionBean.senaraiPihakHubungan}" cellpadding="0" cellspacing="0"
                                           id="pgAmanahTable" pagesize="10"
                                           requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                <display:column  title="Nama" class="nama"></display:column>
                                <display:column title="Jenis Pengenalan"></display:column>
                                <display:column title="Nombor Pengenalan"></display:column>
                                <display:column title="Alamat 1"></display:column>
                                <display:column title="Alamat 2"></display:column>
                                <display:column title="Alamat 3"></display:column>
                                <display:column title="Bandar"></display:column>
                                <display:column title="Poskod"></display:column>
                                <display:column title="Negeri"></display:column>
                                <display:column title="Hapus"></display:column>
                            </display:table>
                        </div>
                        <br/>
                        <p><label>&nbsp;</label><s:button name="" value="Tambah" class="btn" id="addPA"/></p>
                    </div>
                    <div id="pgAmanahDialog" style="display: none" align="left" title="Tambah Pemegang Amanah">

<!--                        <div>
                            Maklumat Pihak Yang Dipegang Amanah
                        </div>-->
                        <p align="left">
                            <label for="nama"><font color="red">*</font>Nama :</label>
<!--                            <font color="red">*</font>Nama :-->
                            <s:text name="permohonanPihakHubungan.nama" id="namaAmanah" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>

                        <c:if test="${actionBean.kodNegeri eq '05'}">
                            <p>
                                <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
<!--                        <font color="red">*</font>Jenis Pengenalan :-->
                                <s:select name="permohonanPihakHubungan.jenisPengenalan.kod" id="kpAmanah">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                        </c:if>

                        <c:if test="${actionBean.kodNegeri eq '04'}">
                            <p>
                                <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
<!--                            <font color="red">*</font>Jenis Pengenalan :-->
                                <s:select name="permohonanPihakHubungan.jenisPengenalan.kod" id="kpAmanah">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                        </c:if>
                        <p>
                            <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
<!--                            <font color="red">*</font>Nombor Pengenalan :-->
                            <s:text name="permohonanPihakHubungan.noPengenalan" id="nokpAmanah" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label for="alamat"><font color="red">*</font>Alamat :</label>
<!--                            <font color="red">*</font>Alamat :-->
                            <s:text name="permohonanPihakHubungan.alamat1" size="40" id="alamatAmanah1" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>

                        <p>
                            <label> &nbsp;</label>
<!--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->
                            <s:text name="permohonanPihakHubungan.alamat2" size="40" id="alamatAmanah2" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>

                        <p>
                            <label> &nbsp;</label>
<!--                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->
                            <s:text name="permohonanPihakHubungan.alamat3" size="40" id="alamatAmanah3" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Bandar :</label>
<!--                            <font color="red">*</font>Bandar :-->
                            <s:text name="permohonanPihakHubungan.alamat4" size="40" id="alamatAmanah4" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Poskod :</label>
<!--                            <font color="red">*</font>Poskod :-->
                            <s:text name="permohonanPihakHubungan.poskod" size="40" maxlength="5" id="poskodAmanah" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
<!--                                <font color="red">*</font>Negeri :-->
                            <s:select name="permohonanPihakHubungan.negeri.kod" id="negeriAmanah">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                    </div>
                </c:if>

                <c:if test="${!actionBean.cariFlag}">
                    <p align="center">
                        <c:if test="${pemohon}">
                            <s:submit name="cariPihakPemohon" value="Cari" class="btn"/>
                        </c:if>
                        <c:if test="${!pemohon}">
                            <s:submit name="cariPihak" value="Cari" class="btn"/>
                        </c:if>
                        <s:submit name="cariSemula" value="Isi Semula" class="btn"/>&nbsp;
                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    </p>
                </c:if>

                <c:if test="${actionBean.cariFlag}">
                   
                    <p align="center">
                        <c:if test="${pemohon}">
                            <s:button name="simpanPemohonPopup" id="simpan" value="Simpan" class="btn" onclick="savePemohon(this.name, this.form);"/>
                            <c:if test="${actionBean.pihak.jenisPengenalan.kod ne 'B' && actionBean.pihak.jenisPengenalan.kod ne 'L' && actionBean.pihak.jenisPengenalan.kod ne 'P' && actionBean.pihak.jenisPengenalan.kod ne 'T' && actionBean.pihak.jenisPengenalan.kod ne 'I'}">
                                <s:submit name="tambahCawanganPemohon" value="Tambah Cawangan" class="longbtn"/>
                            </c:if>
                        </c:if>

                        <c:if test="${!pemohon}">
                            <s:button name="simpanPihakKepentinganPopup" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>

                            <c:if test="${actionBean.pihak.jenisPengenalan.kod ne 'B' && actionBean.pihak.jenisPengenalan.kod ne 'L' && actionBean.pihak.jenisPengenalan.kod ne 'P' && actionBean.pihak.jenisPengenalan.kod ne 'T' && actionBean.pihak.jenisPengenalan.kod ne 'I'}">
                                <s:submit name="tambahCawangan" value="Tambah Cawangan" class="longbtn" id="btnCaw" onclick="return validationPB();"/>
                                <s:submit name="tambahPengarah" value="Tambah Ahli Pengarah" class="longbtn" id="btnAhli" onclick="return validationPB();"/>
                            </c:if>

                        </c:if>
                        <s:submit name="cariSemula" value="Cari Semula" class="longbtn"/>
                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    </p>
                </c:if>
                <br>
            </fieldset>
        </c:if>

        <%--add cawangan--%>

        <c:if test="${actionBean.tambahCawFlag}">
            <s:hidden name="pihakCawangan.negeri.kod"/>

            <fieldset class="aras1">
                <legend>
                    <c:if test="${pemohon}">
                        Kemasukan Cawangan Pemohon
                    </c:if>

                    <c:if test="${!pemohon}">
                        Kemasukan Cawangan Penerima
                    </c:if>
                </legend>
                <p>
                    <label for="nama"><font color="red">*</font>Nama :</label>
                    <s:text name="pihakCawangan.namaCawangan" id="namaCaw" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label for="alamat"><font color="red">*</font>Alamat Berdaftar :</label>
                    <s:text name="pihakCawangan.alamat1" id="alamatCaw1" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>

                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.alamat2" id="alamatCaw2" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>

                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.alamat3" id="alamatCaw3" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>Bandar :</label>
                    <s:text name="pihakCawangan.alamat4" id="alamatCaw4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>Poskod :</label>
                    <s:text name="pihakCawangan.poskod" size="40" id="poskodCaw" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                    <label for="Negeri">Negeri :</label>
                    <s:select name="pihakCawangan.negeri.kod" id="negeriCaw">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                    </s:select>

                </p>
                <p>
                    <label>&nbsp;</label>
                    <input type="checkbox" id="addCaw" name="addCaw" value="" onclick="copyAddCaw();"/>
                    <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                </p>
                <p>
                    <label for="alamat">Alamat Surat-Menyurat :</label>
                    <s:text name="pihakCawangan.suratAlamat1" id="suratAlamatCaw1" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.suratAlamat2" id="suratAlamatCaw2" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.suratAlamat3" id="suratAlamatCaw3" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>Bandar :</label>
                    <s:text name="pihakCawangan.suratAlamat4" id="suratAlamatCaw4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>Poskod :</label>
                    <s:text name="pihakCawangan.suratPoskod" id="suratPoskodCaw" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                </p>

                <p>
                    <label for="Negeri">Negeri :</label>
                    <s:select name="pihakCawangan.suratNegeri.kod" id="suratNegeriCaw">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                    </s:select>
                </p>

                <p>
                    <label for="alamat">Nombor Telefon :</label>
                    <s:text name="pihakCawangan.noTelefon1" size="40" maxlength="12" onkeyup="validateNumber(this,this.value);"/>
                </p>

                <p>
                    <label>&nbsp;</label>
                    <c:if test="${pemohon}">
                        <s:submit name="simpanCawanganPemohon" value="Simpan" class="btn"/>
                        <s:submit name="backCawanganPemohon" value="Tutup" class="btn"/>
                    </c:if>

                    <c:if test="${!pemohon}">
                        <s:submit name="simpanCawangan" value="Simpan" class="btn"/>
                        <s:submit name="backCawangan" value="Tutup" class="btn"/>
                    </c:if>
                </p>
                <br>
            </fieldset>
        </c:if>

        <%--Add Lembaga Pengarah--%>

        <c:if test="${actionBean.tambahPengarahFlag}">
            <fieldset class="aras1">
                <legend>
                    <c:if test="${pemohon}">
                        Kemasukan Lembaga Pengarah Pemohon
                    </c:if>

                    <c:if test="${!pemohon}">
                        Kemasukan Lembaga Pengarah Penerima
                    </c:if>
                </legend>
                <s:hidden name="pihakPengarah.idPengarah"/>
                <p>
                    <label for="nama"><font color="red">*</font>Nama :</label>
                    <s:text name="pihakPengarah.nama" id="namaPengarah" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <c:if test="${actionBean.kodNegeri eq '05'}">
                    <p>
                        <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                        <s:select name="pihakPengarah.jenisPengenalan.kod" id="jenisPengenalan"  onchange="javaScript:changeHideSuku(this.options[selectedIndex].text);">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                </c:if>

                <c:if test="${actionBean.kodNegeri eq '04'}">
                    <p>
                        <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                        <s:select name="pihakPengarah.jenisPengenalan.kod" id="jenisPengenalan">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                </c:if>

                <p>
                    <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                    <s:text name="pihakPengarah.noPengenalan" id="kp" size="40" onkeyup="dodacheck(this.value);this.value=this.value.toUpperCase();"
                            onblur="doCheckUmur(this.value, this.id,'${jenis}')"/>
                </p>
                <p>
                    <label><font color="red">*</font>Kewarganegaraan :</label>
                    <s:select name="pihakPengarah.wargaNegara.kod" style="width:200px" id="kod_warga_pengarah">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <c:if test="${pemohon}">
                        <s:submit name="simpanCawanganPemohon" value="Simpan" class="btn"/>
                        <s:submit name="backCawanganPemohon" value="Tutup" class="btn"/>
                    </c:if>

                    <c:if test="${!pemohon}">
                        <s:submit name="simpanPengarah" value="Simpan" class="btn"/>
                        <s:submit name="backCawangan" value="Tutup" class="btn"/>
                    </c:if>
                </p>
                <br>
            </fieldset>
        </c:if>
    </s:form>
</div>