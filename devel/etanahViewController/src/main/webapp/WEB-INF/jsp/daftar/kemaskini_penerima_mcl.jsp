<%--
    Document   : edit_pemohon
    Created on : Nov 10, 2009, 4:23:38 PM
    Author     : muhammad.amin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="list" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ageCalculator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/formatCurrency.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    $(document).ready( function(){
        $('.empty').remove(); 
        
        var jen = $('#jenis_pihak').val();
        if (jen == 'PA') {
            $('#pgAmanah').show();
        }

        $('#jenis_pihak').change(function(){
            var val = $(this).val();
            if (val == 'PA') {
                $('#pgAmanah').show();
            } else {
                $('#pgAmanah').hide();
            }
        });
        

        $('#addPA').click(function(){
            $('#pgAmanahDialog').dialog('open');
        });

        $("#pgAmanahDialog").dialog({
            autoOpen: false,
            height: 350,
            width: 800,
            modal: true,
            buttons: {

                'Tutup' : function() {
                    $(this).dialog('close');
                },
                
                'Simpan': function() {
                    var bValid = true;
                    bValid = bValid && ($('#namaAmanah').val() != '');
                    bValid = bValid && ($('#kpAmanah').val() != '');
                    bValid = bValid && ($('#nokpAmanah').val() != '');
                    bValid = bValid && ($('#alamatAmanah1').val() != '');                    
                    bValid = bValid && ($('#negeriAmanah').val() != '');
                    bValid = bValid && ($('#syerPembilangAmanah').val() != '');
                    bValid = bValid && ($('#syerPenyebutAmanah').val() != '');

                    if (bValid) {
                        var nama = $('#namaAmanah').val();
                        var kp = $('#kpAmanah').val();
                        var noKp = $('#nokpAmanah').val();
                        var wargaPA = $('#warganegaraAmanah').val();
                        var add1 = $('#alamatAmanah1').val();
                        var add2 = $('#alamatAmanah2').val();
                        var add3 = $('#alamatAmanah3').val();
                        var add4 = $('#alamatAmanah4').val();
                        var poskod = $('#poskodAmanah').val();
                        var negeri = $('#negeriAmanah').val();
                        var syerPembilang = $('#syerPembilangAmanah').val();
                        var syerPenyebut = $('#syerPenyebutAmanah').val();

                        var rowNo = $('table#pgAmanahTable tr').length;

                        $('table#pgAmanahTable tbody').append("<tr id='x"+rowNo+"' class='x'>" +    
                            "<td><input type=hidden name='namaPA' value='"+nama+"'/>" + nama + '</td>' +
                            "<td><input type=hidden name='kpPA' value='"+kp+"'/>" + $('#kpAmanah option:selected').text() + '</td>' +
                            "<td><input type=hidden name='noKpPA' value='"+noKp+"'/>" + noKp + '</td>' +
                            "<td><input type=hidden name='wargaPA' value='"+wargaPA+"'/>" + $('#warganegaraAmanah option:selected').text() + '</td>' +
                            "<td><input type=hidden name='add1PA' value='"+add1+"'/>" + add1 + '</td>' +
                            "<td><input type=hidden name='add2PA' value='"+add2+"'/>" + add2 + '</td>' +
                            "<td><input type=hidden name='add3PA' value='"+add3+"'/>" + add3 + '</td>' +
                            "<td><input type=hidden name='add4PA' value='"+add4+"'/>" + add4 + '</td>' +
                            "<td><input type=hidden name='poskodPA' value='"+poskod+"'/>" + poskod + '</td>' +
                            "<td><input type=hidden name='negeriPA' value='"+negeri+"'/>" + $('#negeriAmanah option:selected').text() + '</td>' +
                            "<td><input type=hidden name='syerPembilangAmanah' value='"+syerPembilang+"'/><input type=hidden name='syerPenyebutAmanah' value='"+syerPenyebut+"'/>" + syerPembilang + '/' + syerPenyebut + '</td>' +
                            "<td><img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' \n\
                                onmouseover='this.style.cursor=\"pointer\";' onclick='deleteWaris2(\"x"+rowNo+"\");'></td>" +
                            '</tr>');
                        $(this).dialog('close');
                    } else {
                        alert('Sila masukan maklumat pihak dipegang amanah dengan lengkap. ');
                    }
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
    });

    function doBlockUI(){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
    }

    function convert(val, id){
        var amaun = CurrencyFormatted(val);
        amaun = CommaFormatted(amaun);
        $('#'+id).val(amaun);
    }

    function save(event, f){

        doBlockUI();

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            $.unblockUI();
            self.close();
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

    function deleteWaris (id) {
        doBlockUI();
        frm = document.form1;
        var url = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?deletePihakHubungan&source=kemaskiniPenerima&id='
            + id + '&idPihak=' + $('#idPihak').val() + '&idPermohonPihak=' + $('#idPermohonanPihak').val();
    
        frm.action = url;
        frm.submit();
    }

    function deleteWaris2(id) {       
        $('#'+id).remove();
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

</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form beanclass="etanah.view.daftar.PihakKepentinganAction" name="form1">
    <div class="subtitle">
        <fieldset class="aras1">
            <s:hidden name="pihak.idPihak" id="idPihak"/>
            <s:hidden name="idHakmilik"/>
            <s:hidden name="permohonanPihak.jenis.kod"/>

            <legend>Kemaskini Maklumat Syarikat MCL </legend>
            <p>
                <label>Nama Syarikat:</label>${actionBean.permohonanPihak.nama} <s:hidden name="permohonanPihak.idPermohonanPihak" id="idPermohonanPihak"/>
                <s:hidden name="permohonanPihak.nama"/>
            </p>
            <p>
                <label>No. Pendaftaran Syarikat :</label>
                ${actionBean.permohonanPihak.noPengenalan}&nbsp;
                <s:hidden name="permohonanPihak.noPengenalan"/>
            </p>
            <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'D' || actionBean.pihak.jenisPengenalan.kod eq 'S'
                          || actionBean.pihak.jenisPengenalan.kod eq 'U'|| actionBean.pihak.jenisPengenalan.kod eq 'N' }">
                  <p class="syarikat">
                      <label>Daftar Tubuh :</label>
                      <s:select name="permohonanPihak.penubuhanSyarikat.kod" id="daftarTubuh">
                          <s:option value="">Sila Pilih</s:option>
                          <s:options-collection collection="${list.senaraiKodPenubuhanSyarikat}" label="nama" value="kod"/>
                      </s:select>
                  </p>

            </c:if>
            <p>
                <label>Jenis Syarikat :</label>
                <s:select name="pihak.bangsa.kod" id="bangsa">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodBangsa}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
                <label for="alamat">Alamat Berdaftar :</label>
                <s:text name="permohonanPihak.alamat.alamat1" id="alamat1" disabled="${disabled}" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="permohonanPihak.alamat.alamat2" id="alamat2" disabled="${disabled}" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="permohonanPihak.alamat.alamat3" id="alamat3" disabled="${disabled}" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Bandar :</label>
                <s:text name="permohonanPihak.alamat.alamat4" id="alamat4" disabled="${disabled}" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name="permohonanPihak.alamat.poskod" id="poskod" disabled="${disabled}" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
            </p>

            <p>
                <label for="Negeri">Negeri :</label>
                <s:select name="permohonanPihak.alamat.negeri.kod" id="negeri" disabled="${disabled}">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
                <label>&nbsp;</label>
                <input type="checkbox" id="add1" name="add1" value="" onclick="copyAdd();"/>
                <em>Alamat surat-menyurat sama dengan alamat berdaftar.</em>
            </p>
            <p>
                <label for="alamat">Alamat Surat-Menyurat :</label>
                <s:text name="permohonanPihak.alamatSurat.alamatSurat1" id="suratAlamat1" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="permohonanPihak.alamatSurat.alamatSurat2" id="suratAlamat2" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="permohonanPihak.alamatSurat.alamatSurat3" id="suratAlamat3" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Bandar :</label>
                <s:text name="permohonanPihak.alamatSurat.alamatSurat4" id="suratAlamat4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name="permohonanPihak.alamatSurat.poskodSurat" id="suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
            </p>

            <p>
                <label for="Negeri">Negeri :</label>
                <s:select name="permohonanPihak.alamatSurat.negeriSurat.kod" id="suratNegeri">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select>
            </p> 
            <br/>                    
            <p>
                <label>&nbsp;</label>
                <s:button name="simpanKemaskiniPenerima" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>                
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset >
    </div>

</s:form>
