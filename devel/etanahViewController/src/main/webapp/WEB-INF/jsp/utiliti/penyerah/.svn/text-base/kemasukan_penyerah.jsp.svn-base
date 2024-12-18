<%-- 
    Document   : kemasukan_penyerah
    Created on : Sep 19, 2013, 12:28:59 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<!--<script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquery-1.3.2.min.js"></script>-->

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>


<script type="text/javascript">

    $(document).ready(function(){
        var r = '${edit}';
        if(r == 'true'){
            $('.penyerah_detail').show();
        }
        var k = $('#penyerahKod').val();   
        if(k == '08'){
            $('#idPenyerah').attr('disabled', true);
            $('#jenisPengenalanPenyerah').removeAttr('disabled');
            $('#noPengenalanPenyerah').removeAttr('disabled');
        }else{
            $('#jenisPengenalanPenyerah').attr('disabled', true);
            $('#noPengenalanPenyerah').attr('disabled', true);
            $('#idPenyerah').removeAttr('disabled');
        }
    });


    var DELIM = "__^$__";
    function cariPenyerah(e,f) {       
        var k = $('#penyerahKod').val();
        if(k == '0') {
            alert('Sila Pilih Penyerah');
            $('#penyerahKod').focus();
            return;
        }
        if(k == '08'){
            var t = $('#jenisPengenalanPenyerah').val();
            if(t == '99'){
                alert('Sila Pilih Jenis Pengenalan Untuk Individu/Syarikat');
                $('#jenisPengenalanPenyerah').focus();
                return;
            }
        }
        //        var i = $('#namaPenyerah').val();
        //        if(i == ''){
        //            alert('Sila Isi nama Penyerah');
        //            $('#penyerahKod').focus();
        //            return;
        //        }
        f.action = f.action + '?' + e;
        f.submit();
        //i = '';
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
                }
    <c:if test = "${actionBean.melaka eq false}">
                    if (jenis == '00'){ // Unit Dalaman                       
                        url = "${pageContext.request.contextPath}/common/req_unit_info?idUnit=" + va;
                    } 
                    if (jenis == '01'){ // PEGUAM
                        url = "${pageContext.request.contextPath}/common/req_peguam_info?idPeguam=" + va;
                    } 
                    if (jenis == '02'){ // JUBL
                        url = "${pageContext.request.contextPath}/common/req_jubl_info?idJUBL=" + va;
                    } 
                    if (jenis == '03'){ // JURURANCANG
                        url = "${pageContext.request.contextPath}/common/req_juRncg_info?idJuruRncg=" + va;
                    }                    
                    if (jenis == '04'){ // Jurulelong Berlesen              
                        url = "${pageContext.request.contextPath}/common/req_julb_info?idJlb=" + va;       
                        //url = "${pageContext.request.contextPath}/common/req_lelong_info?idAgensi=" + va;
                    }
                    if (jenis == '05') { // PERBADANAN PENGURUSAN
                        url = "${pageContext.request.contextPath}/common/req_bdnUrus_info?idBdnUrus=" + va;
                    }
                    if (jenis == '06') { // Jabatan Kerajaan
                        url = "${pageContext.request.contextPath}/common/req_jntnKjaan_info?idJK=" + va;
                    }
                    if (jenis == '07') { // BADAN BERKANUN
                        url = "${pageContext.request.contextPath}/common/req_bdnKanun_info?idBdnKanun=" + va;
                    }            
                    if (jenis == '08'){
                        url = "${pageContext.request.contextPath}/utiliti/kemasukanPenyerah?doKemaskiniPihak&idPihak=" + va;
                    }
    </c:if>
    <c:if test = "${actionBean.melaka eq true}">
                    if (jenis == '00'){ // Unit Dalaman                       
                        url = "${pageContext.request.contextPath}/common/req_unit_info?idUnit=" + va;
                    } 
                    if (jenis == '01'){ // PEGUAM
                        url = "${pageContext.request.contextPath}/common/req_peguam_info?idPeguam=" + va;
                    } 
                    if (jenis == '02'){ // JUBL
                        url = "${pageContext.request.contextPath}/common/req_jubl_info?idJUBL=" + va;
                    }
                    if (jenis == '03'){ // JURURANCANG
                        url = "${pageContext.request.contextPath}/common/req_juRncg_info?idJuruRncg=" + va;
                    }                    
                    if (jenis == '04'){ // Jurulelong Berlesen              
                        url = "${pageContext.request.contextPath}/common/req_julb_info?idJlb=" + va;       
                        //url = "${pageContext.request.contextPath}/common/req_lelong_info?idAgensi=" + va;
                    }
                    if (jenis == '07') { // PERBADANAN PENGURUSAN
                        url = "${pageContext.request.contextPath}/common/req_bdnUrus_info?idBdnUrus=" + va;
                    }
                    if (jenis == '09') { // Jabatan Kerajaan
                        url = "${pageContext.request.contextPath}/common/req_jntnKjaan_info?idJK=" + va;
                    }
                    if (jenis == '10') { // BADAN BERKANUN
                        url = "${pageContext.request.contextPath}/common/req_bdnKanun_info?idBdnKanun=" + va;
                    }            
                    if (jenis == '08'){
                        url = "${pageContext.request.contextPath}/utiliti/kemasukanPenyerah?doKemaskiniPihak&idPihak=" + va;
                    }               
                    if (jenis == '06'){
                        url = "${pageContext.request.contextPath}/common/req_agensi_info?idAgensi=" + va;
                    }
    </c:if>
                                    

                    $.get(url,
                    function(data){
                        if (data == null || data.length == 0){
                            alert("Maklumat tidak dijumpai");
                            return;
                        }
                        var p = data.split(DELIM);
                        //$('#penyerahKod', window.opener.document).val(jenis);
                        $('#idPenyerah').val(va);
                        $('#penyerahJenisPengenalan').val(p[0]);
                        $('#penyerahNoPengenalan').val(p[1]);
                        $("#penyerahNama").val(p[2]);
                        $("#penyerahAlamat1").val(p[3]);
                        $("#penyerahAlamat2").val(p[4]);
                        $("#penyerahAlamat3").val(p[5]);
                        $("#penyerahAlamat4").val(p[6]);
                        $("#penyerahPoskod").val(p[7]);
                        $("#penyerahNegeri").val(p[8]);
                        $("#penyerahNoTelefon").val(p[9]);
                        $("#penyerahEmail").val(p[10]);
                        $("#penyerahStatus").val(p[11]);
                        if(p[11] == 'Y'){
                            $('#radio1').attr('checked', true);
                        }else{
                            $('#radio2').attr('checked', true);
                        }
                        $('.penyerah_detail').show();
                    });
                }
            });
        }
        function dodacheck(val) {
            //var mikExp = /[1\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\1\|]/;
            var v = $('#penyerahJenisPengenalan').val();
            if(v == 'B') {
                var strPass = val;
                var strLength = strPass.length;
                if(strLength > 12) {
                    var tst = val.substring(0, (strLength) - 1);
                    $('#penyerahNoPengenalan').val(tst);
                }
                var lchar = val.charAt((strLength) - 1);
                if(isNaN(lchar)) {
                    //return false;
                    var tst = val.substring(0, (strLength) - 1);
                    $('#penyerahNoPengenalan').val(tst);
                }
            }
        }
    
        function deletePenyerah(f) {

            var x = document.getElementsByTagName("input");
            var radChecked = false;

            for (i = 0; i < x.length; i++) {
                if (x[i].name == "pilihPenyerah" && x[i].checked)
                {
                    radChecked = true;
                    var kodPenyerah = $('#penyerahKod').val();
                    var idPenyerah = $('input:radio[name=pilihPenyerah]:checked').val();
                    f.action = f.action + '?hapus&idPenyerah=' + idPenyerah + '&kodPenyerah=' + kodPenyerah;
                    f.submit();
                }


                if (radChecked == false) {
                    if (x[i].name == "hapus")
                    {
                        alert('Sila pilih penyerah untuk tidak aktifkan');
                        break;
                    }
                }

            }
        }


        //function edited, check radio selected value
        function aktifPenyerah(f) {
            var x = document.getElementsByTagName("input");
            var radChecked = false;

            for (i = 0; i < x.length; i++) {
                if (x[i].name == "pilihPenyerah" && x[i].checked)
                {
                    radChecked = true;
                    var kodPenyerah = $('#penyerahKod').val();
                    var idPenyerah = $('input:radio[name=pilihPenyerah]:checked').val();
                    f.action = f.action + '?aktif&idPenyerah=' + idPenyerah + '&kodPenyerah=' + kodPenyerah;
                    f.submit();
                }

                if (radChecked == false) {
                    if (x[i].name == "aktif")
                    {
                        alert('Sila pilih penyerah untuk diaktifkan');
                        break;
                    }
                }

            }
        }
  
        function resetData() {
            $('#namaPenyerah').val("");
            $('#idPenyerah').val("");
            $('#jenisPengenalanPenyerah').val("99");
            $('#noPengenalanPenyerah').val("");
            $('.penyerah_detail').hide();
        }
    
        function changeValue(val) {
            if(val == '00'){           
                $('#Try').hide();
                alert ("Unit Dalaman Hanya Untuk Kemaskini Sahaja. Sila Klik Butang Cari Di Bawah Untuk Paparan Maklumat.");
                return false;
            }else{
                $('#Try').show();           
            }
        
    <c:if test = "${actionBean.melaka eq true}">
            if(val == '06'){           
                $('#Try').hide();
                return false;
            }                                   
            else{
                $('#Try').show();
            }
    </c:if>
        }
    
        function tambahData() {
            var k = $('#penyerahKod').val();
            if(k == '0') {
                alert('Sila Pilih Penyerah');
                $('#penyerahKod').focus();
                return;
            }
            $('.penyerah_detail').show();
        }
    
        function carianSemula(frm) {
            $('#namaPenyerah').val("");
            $('#idPenyerah').val("");
            $('#jenisPengenalanPenyerah').val("99");
            $('#noPengenalanPenyerah').val("");
            $('#penyerahKod').val("0");
            $('#penyerahJenisPengenalan').val("0");
            $('#penyerahNoPengenalan').val("");
            $("#penyerahNama").val("");
            $("#penyerahAlamat1").val("");
            $("#penyerahAlamat2").val("");
            $("#penyerahAlamat3").val("");
            $("#penyerahAlamat4").val("");
            $("#penyerahPoskod").val("");
            $("#penyerahNegeri").val("0");
            $("#penyerahNoTelefon").val("");
            $("#penyerahEmail").val("");
            $("#penyerahStatus").val("");
            var url = '${pageContext.request.contextPath}/utiliti/kemasukanPenyerah';
            frm.action = url;
            frm.submit();
        }
	
        function validateForm() {
            //		alert("CEK DULU TAW!!");
		
            if ($("#penyerahNama").val() == "") {
                alert('Sila masukkan nama.');
                $("#penyerahNama").focus();
                return false;
            }
            if ($("#penyerahAlamat1").val() == "") {
                alert('Sila masukkan alamat.');
                $("#penyerahAlamat1").focus();
                return false;
            }
            if ($("#penyerahNegeri").val() == "0") {
                alert('Sila pilih negeri.');
                $("#penyerahNegeri").focus();
                return false;
            }
            //return true;
				
        }
   
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.utility.KemasukanPenyerahActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Penambahan/Pembetulan Maklumat Penyerah
            </legend>
            <p>
                <label>Jenis Penyerah :</label>
                <s:select name="jenisPenyerah" id="penyerahKod" onchange="changeValue(this.value)">
                    <s:option value="0">Pilih Jenis ...</s:option>
                    <%--<s:option value="08">Individu/Syarikat</s:option>--%>
                    <c:if test = "${actionBean.melaka eq false}">
                        <s:options-collection collection="${listUtil.senaraiKodPenyerah}" label="nama" value="kod"/>
                    </c:if>
                    <c:if test = "${actionBean.melaka eq true}">
                        <s:options-collection collection="${listUtil.senaraiKodPenyerahFOrUtilityPenyerahMelaka}" label="nama" value="kod"/>
                    </c:if>
                </s:select>

            </p>
            <p>
                <label>Nama Penyerah :</label>
                <s:text name="namaPenyerah" size="20" id="namaPenyerah" onkeyup="this.value=this.value.toUpperCase();"/>
                <em>Atau</em>
            </p>

            <c:if test = "${actionBean.jenisPenyerah ne '00'}">
                <p>
                    <label>Id Penyerah :</label>
                    <s:text name="idPenyerah" size="20" id="idPenyerah" maxlength="10"/>
                    <em>Atau</em> 
                </p>   
            </c:if>

            <c:if test = "${actionBean.jenisPenyerah eq '00'}">
                <p>
                    <label>Kod Penyerah :</label>
                    <s:text name="idPenyerah" size="20" id="idPenyerah" maxlength="10"/>
                    <em>Atau</em> 
                </p>   
            </c:if>

            <p>
                <label for="Jenis Pengenalan">No. Pengenalan :</label>
                <s:select name="jenisPengenalanPenyerah" id="jenisPengenalanPenyerah" onchange="clearNoPengenalan();">
                    <s:option value="99">Pilih Jenis...</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                </s:select>
                <s:text name="noPengenalanPenyerah" id="noPengenalanPenyerah" onkeyup="dodacheck(this.value);"
                        onblur="doUpperCase(this.id)"/>
                <em>(Untuk Lain-lain)</em>

            </p>  
            <p>
                <label>&nbsp;</label>
                <s:button name="searchPenyerahForEdit" value="Cari" class="btn" onclick="cariPenyerah(this.name, this.form);"/>
                <c:if test="${actionBean.tambah eq true}">
                    <s:button name="tambah" value="Tambah Penyerah" class="btn" onclick="tambahData();" />
                </c:if>
                <s:button name="reset" value="Carian Semula" class="btn" onclick="carianSemula(this.form);" />

            </p>
        </fieldset>
    </div>
    <br/>
    <c:if test="${actionBean.pihakOnly eq false}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Keputusan Carian Penyerah
                </legend>
                <div class="content" align="center">
                    <display:table style="width:95%" class="tablecloth" name="${actionBean.senaraiPenyerah}" pagesize="10"
                                   cellpadding="0" cellspacing="0" id="line" requestURI="${pageContext.request.contextPath}/utiliti/kemasukanPenyerah?searchPenyerahForEdit" excludedParams="searchPenyerahForEdit">
                        <display:column>  
                            <c:if test = "${actionBean.jenisPenyerah ne '00'}">
                                <s:radio name="__pilihPenyerah" class="penyerah" id="pilihPenyerah${line_rowNum}" value="${line.idPenyerah}"/> 
                            </c:if>
                            <c:if test = "${actionBean.jenisPenyerah eq '00'}">
                                <s:radio name="__pilihPenyerah" class="penyerah" id="pilihPenyerah${line_rowNum}" value="${line.kod}"/> 
                            </c:if>

                        </display:column>
                        <c:if test = "${actionBean.jenisPenyerah eq '00'}">
                            <display:column property="kod" title="Kod Penyerah"/>
                        </c:if>

                        <c:if test = "${actionBean.jenisPenyerah ne '00'}">
                            <display:column property="idPenyerah" title="ID Penyerah"/>
                        </c:if>
                        <c:if test = "${actionBean.jenisPenyerah eq '00'}">
                            <display:column property="idPenyerah" title="Kod Jabatan"/>
                        </c:if>

                        <display:column property="nama" title="Nama Penyerah"/>
                        <display:column title="Alamat">
                            ${line.alamat1}
                            ${line.alamat2}
                            ${line.alamat3}
                            ${line.alamat4}
                            ${line.poskod}
                            ${line.negeri.nama}
                        </display:column>
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
                <c:if test="${fn:length(actionBean.senaraiPenyerah)>0}">
                    <p>
                        <label>&nbsp;</label><s:button name="_pilih" value="Pilih" class="btn" onclick="pilihPenyerah();"/>
                    </p>
                </c:if>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${actionBean.pihakOnly eq true}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Keputusan Carian Penyerah
                </legend>
                <div class="content" align="center">
                    <display:table style="width:95%" class="tablecloth" name="${actionBean.senaraiPihak}" pagesize="10"
                                   cellpadding="0" cellspacing="0" id="line" requestURI="${pageContext.request.contextPath}/utiliti/kemasukanPenyerah?searchPenyerahForEdit" excludedParams="searchPenyerahForEdit">
                        <display:column>
                            <s:radio name="__pilihPenyerah" class="penyerah" id="pilihPenyerah${line_rowNum}" value="${line.idPihak}"/> 

                        </display:column>
                        <display:column property="nama" title="Nama Penyerah"/>
                        <display:column property="noPengenalan" title="No Pengenalan"/>
                        <display:column title="Alamat">
                            ${line.alamat1}
                            ${line.alamat2}
                            ${line.alamat3}
                            ${line.alamat4}
                            ${line.poskod}
                            ${line.negeri.nama}
                        </display:column>
                    </display:table>
                </div>
                <br/>
                <c:if test="${fn:length(actionBean.senaraiPihak)>0}">
                    <p>
                        <label>&nbsp;</label><s:button name="_pilih" value="Pilih" class="btn" onclick="pilihPenyerah();"/>
                    </p>
                </c:if>
            </fieldset>
        </div>
    </c:if>

    <br/>
    <div class="subtitle penyerah_detail" style="display: none">
        <s:hidden name="idPenyerah" id="idPenyerah"/>
        <fieldset class="aras1">
            <legend>
                <c:if test="${actionBean.tambah eq true}">
                    Tambah Penyerah
                </c:if>
                <c:if test="${actionBean.tambah eq false}">
                    Kemaskini Penyerah
                </c:if>    
            </legend>

            <%--
            <p><label><font color="red">*</font>Kod Kementerian:</label>
                <s:select name="kementerian" id="kementerian" >
                    <s:option value="0">Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodKementerian}" label="nama" value="kod" />
                </s:select>
            </p>
            --%>

            <div id ="Try">
                <p>
                    <c:if test = "${actionBean.melaka eq false}">
                        <c:if test = "${(actionBean.jenisPenyerah ne '00')}">
                            <label for="Jenis Pengenalan">No. Pengenalan :</label>
                            <s:select name="penyerahJenisPengenalan" id="penyerahJenisPengenalan" onchange="clearNoPengenalan();">
                                <s:option value="0">Pilih Jenis...</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                            </s:select>
                            <s:text name="penyerahNoPengenalan" id="penyerahNoPengenalan" onkeyup="dodacheck(this.value);"
                                    onblur="doUpperCase(this.id)"/>     
                        </c:if>
                    </c:if>
                </p>

                <p>
                    <c:if test = "${actionBean.melaka eq true}">
                        <c:if test = "${!(actionBean.jenisPenyerah eq '00' || actionBean.jenisPenyerah eq '06')}">
                            <label for="Jenis Pengenalan">No. Pengenalan :</label>
                            <s:select name="penyerahJenisPengenalan" id="penyerahJenisPengenalan" onchange="clearNoPengenalan();">
                                <s:option value="0">Pilih Jenis...</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                            </s:select>
                            <s:text name="penyerahNoPengenalan" id="penyerahNoPengenalan" onkeyup="dodacheck(this.value);"
                                    onblur="doUpperCase(this.id)"/>     
                        </c:if>
                    </c:if>
                </p>
            </div>


            <p>
                <label>Nama :</label>
                <s:text name="penyerahNama" id="penyerahNama" size="42" onkeyup="this.value=this.value.toUpperCase();"/><em>*</em>
            </p>
            <p>
                <label>Alamat :</label>
                <s:text name="penyerahAlamat1" id="penyerahAlamat1" size="30" onkeyup="this.value=this.value.toUpperCase();"/><em>*</em>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="penyerahAlamat2" id="penyerahAlamat2" size="30" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="penyerahAlamat3" id="penyerahAlamat3" size="30" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Bandar :</label>
                <s:text name="penyerahAlamat4" id="penyerahAlamat4" size="30" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name="penyerahPoskod" maxlength="5" id="penyerahPoskod" size="17" onkeyup="validateNumber(this,this.value);" />
            </p>
            <p>
                <label>Negeri</label>
                <s:select name="penyerahNegeri" id="penyerahNegeri" >
                    <s:option value="0">Pilih ...</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select><em>*</em>
            </p>
            <p>
                <label>No.Telefon</label>
                <s:text name="penyerahNoTelefon" id="penyerahNoTelefon" size="15" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Email</label>
                <s:text name="penyerahEmail" id="penyerahEmail" size="50"/>
            </p>
            <%--
            <c:if test="${actionBean.jenisPenyerah eq '00' or actionBean.jenisPenyerah eq '01' or actionBean.jenisPenyerah eq '02' or actionBean.jenisPenyerah eq '03'
                          or actionBean.jenisPenyerah eq '04' or actionBean.jenisPenyerah eq '05' or actionBean.jenisPenyerah eq '06' or actionBean.jenisPenyerah eq '07'}">
            --%>                 
            <p>
                <label>Status</label>
                <s:radio name="penyerahStatus" id="radio1" value="Y"></s:radio> Aktif
                <s:radio name="penyerahStatus" id="radio2" value="T"/> Tidak Aktif
            </p>
            <%--</c:if>--%>

            <br/>            
            <p>
                <label>&nbsp;</label>
                <c:if test="${actionBean.tambah eq true}">                  
                    <s:submit name="tambah" value="Tambah" class="btn" onclick="return validateForm();"/>
                </c:if>
                <c:if test="${actionBean.tambah eq false}">                    
                    <s:submit name="update" value="Kemaskini" class="btn" onclick="return validateForm();"/>
                </c:if>

            </p>
        </fieldset>
    </div>          
</s:form>
