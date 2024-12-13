<%-- 
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : ${user}
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        maximizeWindow();
    });

    function deletePenyerah(f){
        if($('#penyerah').is(':checked')){
            var kodPenyerah = $('#penyerahKod').val();
            var idPenyerah = $('input:radio[name=pilihPenyerah]:checked').val();
            f.action = f.action + '?hapus&idPenyerah=' + idPenyerah + '&kodPenyerah=' +kodPenyerah;
            f.submit();
        }else{
            alert('Sila pilih penyerah untuk tidak aktifkan');
        }
    }
    function aktifPenyerah(f){
        if($('#penyerah').is(':checked')){
            var kodPenyerah = $('#penyerahKod').val();
            var idPenyerah = $('input:radio[name=pilihPenyerah]:checked').val();
            f.action = f.action + '?aktif&idPenyerah=' + idPenyerah + '&kodPenyerah=' +kodPenyerah;
            f.submit();
        }else{
            alert('Sila pilih penyerah untuk diaktifkan');
        }

    }
    function tambahPenyerah(f){

        if($('#penyerahNama').val() == ""){
            alert('Sila Masukkan Nama Penyerah');
        }else if($('#idPenyerah').val() == ""){
            alert('Sila Masukkan Kod Penyerah');
        }else if($('#alamatPenyerah1').val() == ""){
            alert('Sila Masukkan Alamat Penyerah');
        }else if($('#penyerahPoskod').val() == ""){
            alert('Sila Masukkan Poskod Penyerah');
        }
        else if($('#penyerahNegeri').val() == ""){
            alert('Sila Masukkan Negeri Penyerah');
        }
        else{
            var kodPenyerah = $('#penyerahKod').val();
            var idPenyerah = $('#idPenyerah').val();
            f.action = f.action + '?tambah&idPenyerah=' + idPenyerah + '&kodPenyerah=' +kodPenyerah;
            f.submit();
        }
    }

    var DELIM = "__^$__";

    function cariPenyerah(e,f) {
        var k = $('#penyerahKod').val();
        if(k == '0') {
            alert('Sila Pilih Penyerah');
            $('#penyerahKod').focus();
            return;
        }
        if($('#penyerahKod').val() == '03' ){
            alert('Table Tiada');
            return;
        }
    <%--var i = $('#namaPenyerah').val();
    var l = $('#idPenyerah').val();
    if(i == '' && l == ''){
        alert('Sila Isi nama Penyerah atau Id Penyerah');
        //$('#penyerahKod').focus();
        return;
    }--%>
            f.action = f.action + '?' + e;
            f.submit();
        }
        function cariPenyerahPopup(e,f,val) {
		alert('l');
            var k = $('#penyerahKod').val();
            if(k == '0') {
                alert('Sila Pilih Penyerah');
                $('#penyerahKod').focus();
                return;
            }
            if($('#penyerahKod').val() == '03' ){
                alert('Maklumat Tiada.');
                return;
            }
            var i = $('#namaPenyerah').val();
            var l = $('#idPenyerah').val();
            if(i == '' && l == ''){
                alert('Sila Isi nama Penyerah atau Id Penyerah');
                //$('#penyerahKod').focus();
                return;
            }
            f.action = f.action + '?searchPenyerah'+'&idPermohonan='+val;
            f.submit();
        }

        function pilihPenyerah(){
            var jenis = $('#penyerahKod').val();
            var url;
            $('.penyerah').each(function(){
                if($(this).is(':checked')){
                    var va = $(this).val();

                    if(jenis == '0'){
                        alert('Sila pilih Jenis Penyerah');
                        return;
                    }else
                        if (jenis == '01'){ // PEGUAM
                            url = "${pageContext.request.contextPath}/lelong/peguam?idPenyerah=" + va;
                        }
                    $.get(url,
                    function(data){
                        if (data == null || data.length == 0){
                            alert("Maklumat tidak dijumpai");
                            return;
                        }
                        var p = data.split(DELIM);
                        $('#penyerahKod', window.opener.document).val(jenis);
                        $('#idPenyerah', window.opener.document).val(va);
                        $('#penyerahJenisPengenalan', window.opener.document).val(p[0]);
                        $('#penyerahNoPengenalan',opener.document).val(p[1]);
                        $("#penyerahNama",opener.document).val(p[2]);
                        $("#penyerahAlamat1",opener.document).val(p[3]);
                        $("#penyerahAlamat2",opener.document).val(p[4]);
                        $("#penyerahAlamat3",opener.document).val(p[5]);
                        $("#penyerahAlamat4",opener.document).val(p[6]);
                        $("#penyerahPoskod",opener.document).val(p[7]);
                        $("#penyerahNegeri",opener.document).val(p[8]);
                        self.close();
                    });
                }
            });
        } function validateNumber(elmnt,content) {
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

        function doUpperCase(id){
            var val = $('#'+id).val().toUpperCase();
            $('#' + id).val(val);
        }
        
        //function tambahBaru(){
		//	alert("klik masuk sini!!!");
            //window.open("${pageContext.request.contextPath}/lelong/peguam?tambahPeguam&idPermohonan="+val, "eTanah",
            //"status=0,toolbar=0,location=0,menubar=0,width=1000,height=1200");
        //}

        function save(event, f){
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.close();
            },'html');
        }
        
        function refreshRekod(idPermohonan){
        var url = '${pageContext.request.contextPath}/lelong/peguam?reloadPage&idPermohonan='+idPermohonan+';
        $.get(url,
        function(data){
            $('#tukar_peguam').html(data);
        }, 'html');
    }
	
</script>
<script type="text/javascript">
	function trytest(val){
		$('#penyerahId').val(val);
	}
	
	function pilih(val){            
		var len = $('.chkbox').length;
		var idP = document.getElementById('penyerahId');
		window.open("${pageContext.request.contextPath}/lelong/peguam?simpanPeguam&idPenyerah="+idP.value+ "&idPermohonan="+val, "eTanah",
		"status=0,toolbar=0,location=0,menubar=0,width=1000,height=1200");
	}
	
	     function tambahBaru(val){
            window.open("${pageContext.request.contextPath}/lelong/peguam?tambahPeguam&idPermohonan="+val, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=1000,height=1200");
        }
		</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.lelong.UtilitiPilihPeguamActionBean" id="tukar_peguam">
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Carian Penyerah 
                <s:text name="idPermohonan" size="20" value="${actionBean.idPermohonan}" style="display:none;"/>
            </legend>
            <p>
                <label>Nama Penyerah :</label>
                <s:text name="namaPenyerah" size="20" id="namaPenyerah" /><em>Atau</em>
            </p>
            <p>
                <label>Id Penyerah :</label>
                <s:text name="idPenyerah" size="20" id="idPenyerah" maxlength="5"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:submit name="searchPenyerah" value="Cari" class="btn" onclick="cariPenyerahPopup(this.name, this.form,'${actionBean.idPermohonan}');"/>
                <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
                <s:button name="" value="Tutup" class="btn"
                          onclick="self.close();"/>
            </p>
        </fieldset>
    </div>
    <br/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Keputusan Carian Penyerah
            </legend>
			<s:text name="" id="penyerahId" style="display:none;"/>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiPenyerah}" cellpadding="0" cellspacing="0" id="line" pagesize="10" requestURI="/lelong/peguam">
                    <display:column> <s:radio name="idPenyerah" class="chkbox${line_rowNum}" value="${line.idPenyerah}" onclick="trytest(this.value)"/></display:column>
                    <display:column property="idPenyerah" title="ID Penyerah"/>
                    <display:column property="nama" title="Nama Penyerah" class="chkbox"/>
                    <display:column title="Alamat">
                        ${line.alamat1}
                        ${line.alamat2}
                        ${line.alamat3}
                        ${line.alamat4}
                        ${line.poskod}
                        ${line.negeri.nama}
                    </display:column>
                    <display:column title="No Telefon" property="noTelefon1"/>
                    <display:column title="Status">
                        <c:if test="${line.aktif eq 'Y'}">
                            Aktif
                        </c:if>
                        <c:if test="${line.aktif eq 'T'}">
                            Tidak Aktif
                        </c:if>
                    </display:column>
                </display:table>
            </div>
            <br/>

            <c:if test="${fn:length(actionBean.senaraiPeguam)>0}">
                <p>
                    <label>&nbsp;</label><s:button name="simpanPeguam" value="Simpan" class="btn" onclick="pilih('${actionBean.idPermohonan}');"/>
                    <label>&nbsp;</label><s:button name="tambahPeguam" value="Peguam Baru" class="btn" onclick="tambahBaru('${actionBean.idPermohonan}');"/>
                </p>
            </c:if>
        </fieldset>
    </div>

</s:form>

