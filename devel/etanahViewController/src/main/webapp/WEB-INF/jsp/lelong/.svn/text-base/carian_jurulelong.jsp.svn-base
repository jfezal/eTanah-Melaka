<%-- 
    Document   : carian_jurulelong
    Created on : Jul 4, 2011, 12:40:47 PM
    Author     : mdizzat.mashrom
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
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

    function cariJurulelong(e,f) {
        f.action = f.action + '?' + e;
        f.submit();
    }
    function cariJurulelongPopup(e,f) {
        var i = $('#namaPenyerah').val();
        var l = $('#idPenyerah').val();
        if(i == '' && l == ''){
            alert('Sila Isi nama Penyerah atau Id Penyerah');
            //$('#penyerahKod').focus();
            return;
        }
        f.action = f.action + '?' + e;
        f.submit();
    }

    function pilihPenyerah(){
        var url;
        $('.penyerah').each(function(){
            if($(this).is(':checked')){
                var va = $(this).val();
                url = "${pageContext.request.contextPath}/lelong/pendaftaran_jurulelong?findByID&idJBL=" + va;
                $.get(url,
                function(data){
                    if (data == null || data.length == 0){
                        alert("Maklumat tidak dijumpai");
                        return;
                    }
                    var p = data.split(DELIM);
                    $('#jenisPengenalan', window.opener.document).val(p[0]);
                    $('#nokp1',opener.document).val(p[1]);
                    $("#nama",opener.document).val(p[2]);
                    $("#alamat1",opener.document).val(p[3]);
                    $("#alamat2",opener.document).val(p[4]);
                    $("#alamat3",opener.document).val(p[5]);
                    $("#alamat4",opener.document).val(p[6]);
                    $("#poskod",opener.document).val(p[7]);
                    $("#negeri",opener.document).val(p[8]);
                    $("#cawangan",opener.document).val(p[9]);
                    $("#noTelefon1",opener.document).val(p[10]);
                    $("#noTelefon2",opener.document).val(p[11]);
                    $("#idSyarikat",opener.document).val(p[12]);
                    $("#noSykt",opener.document).val(p[13]);
                    $('#idJLB', window.opener.document).val(p[14]);
                    $('#tahun', window.opener.document).val(p[15]);
					$("#aktif", window.opener.document).val(p[16]);
					$("#emel", window.opener.document).val(p[17]);
					$("#idjlb", window.opener.document).val(p[18]);
					$("#alert", window.opener.document).val(p[19]);
                    
					if(p[19] == "Jurulelong ini tidak aktif"){
						alert("Jurulelong ini tidak aktif.");
					}
					self.close();
                });
            }
        });
    }
    
    function pilihPenyerahN9(){
        var url;
        $('.penyerah').each(function(){
            if($(this).is(':checked')){
                var va = $(this).val();
                url = "${pageContext.request.contextPath}/lelong/pendaftaran_jurulelong?findByIDN9&idJBL=" + va;
                $.get(url,
                function(data){
                    if (data == null || data.length == 0){
                        alert("Maklumat tidak dijumpai");
                        return;
                    }
                    var p = data.split(DELIM);
                    $('#jenisPengenalan', window.opener.document).val(p[0]);
                    $('#nokp1',opener.document).val(p[1]);
                    $("#noSykt",opener.document).val(p[2]);
                    $("#aktif",opener.document).val(p[3]);
                    $("#idSyarikat",opener.document).val(p[4]);
                    $("#alert",opener.document).val(p[5]);
                    
					if(p[5] == "Jurulelong ini tidak aktif"){
						alert("Syarikat jurulelong ini tidak aktif.");
					}
					self.close();
                });
            }
        });
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

    function doUpperCase(id){
        var val = $('#'+id).val().toUpperCase();
        $('#' + id).val(val);
    }


</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.lelong.PendaftaranJurulelongBerlesenActionBean">
    <s:messages/>
	<c:if test="${actionBean.cariSykt eq false}">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Carian Jurulelong
            </legend>
            <p>
                <label> Carian :</label>
                Jurulelong Berlesen
            <p>
                <label>Id Jurulelong / No Pengenalan :</label>
                <s:text name="idPenyerah" size="20" id="idPenyerah"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:button name="searchJurulelong" value="Cari" class="btn" onclick="cariJurulelongPopup(this.name, this.form);"/>
                <s:button name="" value="Tutup" class="btn"
                          onclick="self.close();"/>
            </p>
        </fieldset>
    </div>
    <br/>
    <div class="subtitle">
        <fieldset class="aras1">
            
                <legend>
                    Keputusan Carian Jurulelong
                </legend>

                <div class="content" align="center">

                    <display:table class="tablecloth" name="${actionBean.senaraiJurulelong}"
                                   cellpadding="0" cellspacing="0" id="line" pagesize="10"
                                   requestURI="${pageContext.request.contextPath}/lelong/pendaftaran_jurulelong?searchJurulelongPopup" excludedParams="${url}">
                        <display:column>
                            <s:radio name="__pilihPenyerah" class="penyerah" id="pilihPenyerah${line_rowNum}" value="${line.idJlb}"/>
                        </display:column>
                        <display:column property="idJlb" title="ID Jurulelong"/>
                        <display:column property="nama" title="Nama Jurulelong"/>
                        <display:column property="sytJuruLelong.nama" title="Nama Syarikat"/>
                        <display:column property="sytJuruLelong.noPengenalan" title="No Syarikat"/>
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
                <c:if test="${fn:length(actionBean.senaraiJurulelong)>0}">
                    <p>
                        <label>&nbsp;</label><s:button name="_pilih" value="Pilih" class="btn" onclick="pilihPenyerah();"/>
                    </p>
                </c:if>
            
            
            
        </fieldset>     
    </div>
</c:if>

<c:if test="${actionBean.cariSykt eq true}">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Carian Syarikat Jurulelong
            </legend>
            <p>
                <label> Carian :</label>
                Syarikat Jurulelong Berlesen
            <p>
                <label>Nama Syarikat Jurulelong :</label>
                <s:text name="namaPenyerah" size="20" id="namaPenyerah" /><em>Atau</em>
            </p>
            <p>
                <label>Id Syarikat Jurulelong :</label>
                <s:text name="idPenyerah" size="20" id="idPenyerah" maxlength="5"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:button name="searchJurulelong2" value="Cari" class="btn" onclick="cariJurulelongPopup(this.name, this.form);"/>
                <s:button name="" value="Tutup" class="btn"
                          onclick="self.close();"/>
            </p>
        </fieldset>
    </div>
    <br/>
    <div class="subtitle">
        <fieldset class="aras1">
            
                <legend>
                    Keputusan Carian Syarikat Jurulelong
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.listSytJuruLelong}"
                                   cellpadding="0" cellspacing="0" id="line" pagesize="10"
                                   requestURI="${pageContext.request.contextPath}/lelong/pendaftaran_jurulelong?searchJurulelongPopup" excludedParams="${url}">
                        <display:column>
                            <s:radio name="__pilihPenyerahN9" class="penyerah" id="pilihPenyerahN9${line_rowNum}" value="${line.idSytJlb}"/>
                        </display:column>
                        <display:column property="idSytJlb" title="ID Syarikat Jurulelong"/>
                        <display:column property="nama" title="Nama Syarikat"/>
                        <display:column property="noPengenalan" title="No Syarikat"/>
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
                <c:if test="${fn:length(actionBean.listSytJuruLelong)>0}">
                    <p>
                        <label>&nbsp;</label><s:button name="_pilih" value="Pilih" class="btn" onclick="pilihPenyerahN9();"/>
                    </p>
                </c:if>
		</fieldset>
	</div>
</c:if>
</s:form>
