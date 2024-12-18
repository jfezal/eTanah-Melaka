<%--
    Document   : popup_tambah_pembantah
    Created on : 04-March-2013, 12:27:21
    Author     : latifah.iskak
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<script src="<%= request.getContextPath()%>/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        var status = $('#statusKategoriPembantah').val();
        if(status == "L"){
            document.getElementById("lainLain").checked = true;
        }else if(status == "P"){
            document.getElementById("pihakBerkepentingan").checked = true;
        }
        
        var statusView = $('#statusView').val();
        
        if(statusView == "edit"){
            document.getElementById("lainLain").disabled = true;
            document.getElementById("pihakBerkepentingan").disabled = true;
            
            $('#namaP').val($('#namaPihak').val());
            $('#cariNoPengenalanPihak').val($('#noPengenalanPihakCarian').val());
            $('#nPengenalanP').val($('#noPengenalanPihakCarian').val());
            $('#jPengenalanP').val($('#jenisPengenalanPihak').val());
            $('#alamatP').val($('#alamatPihak').val());
            $('#poskodP').val($('#poskodPihak').val());
            $('#negeriP').val($('#negeriPihak').val());
            $('#telP1').val($('#noTelefon1Pihak').val());
            $('#telP2').val($('#noTelefon2Pihak').val());
            
            var statusEditJantina = ${actionBean.noEdit};
            if(statusEditJantina == false){ //if statusEditJantina false, can edit jantina
                $('#jantinaPihak').val($('#jantinaPihakCarian').val());
                $('#jantinaP').val("");
                document.getElementById("adaJantinaDiv").style.visibility = 'hidden';
                document.getElementById("adaJantinaDiv").style.display = 'none';
                        
                document.getElementById("tiadaJantinaDiv").style.visibility = 'visible';
                document.getElementById("tiadaJantinaDiv").style.display = '';
            }else{
                //if statusEditJantina true, cannot edit jantina 
                $('#jantinaP').val($('#jantinaPihakCarian').val());
                $('#jantinaPihak').val("");
                document.getElementById("tiadaJantinaDiv").style.visibility = 'hidden';
                document.getElementById("tiadaJantinaDiv").style.display = 'none';
                        
                document.getElementById("adaJantinaDiv").style.visibility = 'visible';
                document.getElementById("adaJantinaDiv").style.display = '';
            }
            
        }
        
        changeCategory(status,statusView);
        
    });

    function save(event, f){

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.opener.refreshPage();
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

    function test(f) {
        $(f).clearForm();
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

    function validateForm(){
        
        if(document.getElementById("pihakBerkepentingan").checked == true){
            if($('#cariNoPengenalanPihak').val() == ''){
                alert("Sila masukkan no pengenalan pihak terlebih dahulu.");
                $('#cariNoPengenalanPihak').focus();
                return false;
            } 
            
            var bil =  document.getElementById("recordCount").value;
            for (var i = 0; i < bil; i++){
                var pihakList = document.getElementById('idPihakPembantah'+i).value;
                if(pihakList == $('#idPihak').val()){
                    alert("Pihak ini telah wujud. Sila pilih pihak yang lain");
                    return false;
                }
            }
            if($('#jantinaPihak').val() == '' && $('#jantinaP').val() == ''){
                alert("Sila pilih jantina.");
                $('#jantinaPihak').focus();
                return false;
            }  
        }else{
            if($('#nama').val() == ''){
                alert("Sila masukkan nama.");
                $('#nama').focus();
                return false;
            }
            
            if($('#jenisPengenalan').val() == ''){
                alert("Sila pilih jenis pengenalan.");
                $('#jenisPengenalan').focus();
                return false;
            }
            
            if($('#jenisPengenalan').val() == 'B')
            {
                if($('#noPengenalanLainBaru').val() == ''){
                    alert("Sila masukkan No Pengenalan baru");
                    $('#noPengenalanLainBaru').focus();
                    return false;
                }
          
            }else{
                if($('#noPengenalanLain').val() == ''){
                    alert("Sila masukkan No Pengenalan");
                    $('#noPengenalanLain').focus();
                    return false;
                }
            }
            if($('#jantina').val() == ''){
                alert("Sila pilih jantina.");
                $('#jantina').focus();
                return false;
            }
            
         
        }
        
        if($('#kaitan1').val() == ''){
            alert("Sila masukkan hubungan");
            $('#kaitan1').focus();
            return false;
        }
        return true;
    }

    function test(f) {
        $(f).clearForm();
    }
    
    function changeCategory(value){
        //        alert("val :"+value);
        if(value == "P"){
            //if have id hakmilik
            $('#lainLainDiv').hide();
            $('#pihakBerkepentinganDiv').show();
            $('#kategoriPembantahStatus').val(value);
        }else if(value == "L"){
            //if dont have id hakmilik
            $('#lainLainDiv').show();
            $('#pihakBerkepentinganDiv').hide();
            $('#kategoriPembantahStatus').val(value);
        }else{
            $('#lainLainDiv').hide();
            $('#pihakBerkepentinganDiv').show();
            document.getElementById("pihakBerkepentingan").checked = true;
            $('#kategoriPembantahStatus').val("P");
        }
        
    }
    
    function validateNoPengenalan(){
        if ($('#noPengenalan').val() == '') {
            alert("Sila masukkan no pengenalan terlebih dahulu sebelum tekan butang cari.");
            $('#noPengenalan').focus();
            return false;
        }
        return true;
    }
    
    function carianPihak(){
        var noPengenalanPihak = $("#cariNoPengenalanPihak").val();
        if(noPengenalanPihak != ""){
            $.get('${pageContext.request.contextPath}/penguatkuasaan/maklumat_bantahan?searchNoPengenalan&noPengenalan='+noPengenalanPihak,
            function(data){
                $("#msgInfoDiv").replaceWith($('#msgInfoDiv', $(data)));
                $("#maklumatPihakDiv").replaceWith($('#maklumatPihakDiv', $(data)));
                var status = $('#statusCarian').val();
                if(status == "W"){
                    $('#namaP').val($('#namaPihak').val());
                    $('#noPengenalanPihak').val($('#noPengenalanPihakCarian').val());
                    $('#nPengenalanP').val($('#noPengenalanPihakCarian').val());
                    $('#jPengenalanP').val($('#jenisPengenalanPihak').val());
                    $('#alamatP').val($('#alamatPihak').val());
                    $('#poskodP').val($('#poskodPihak').val());
                    $('#negeriP').val($('#negeriPihak').val());
                    $('#telP1').val($('#noTelefon1Pihak').val());
                    $('#telP2').val($('#noTelefon2Pihak').val());
                   
                    var jantinaPihakCarian = $('#jantinaPihakCarian').val();
                    if(jantinaPihakCarian == ""){  //if jantina from table pihak is null, user need to insert
                        document.getElementById("tiadaJantinaDiv").style.visibility = 'visible';
                        document.getElementById("tiadaJantinaDiv").style.display = '';
                        document.getElementById("adaJantinaDiv").style.visibility = 'hidden';
                        document.getElementById("adaJantinaDiv").style.display = 'none';
                        $('#jantinaP').attr("disabled", true);
                        $('#jantinaPihak').val(jantinaPihakCarian);
                    }else{
                        //jantina from table pihak is not null
                        document.getElementById("adaJantinaDiv").style.visibility = 'visible';
                        document.getElementById("adaJantinaDiv").style.display = '';
                        document.getElementById("tiadaJantinaDiv").style.visibility = 'hidden';
                        document.getElementById("tiadaJantinaDiv").style.display = 'none';
                        
                        $('#jantinaP').val(jantinaPihakCarian);
                        $('#jantinaPihak').attr("disabled", true);
                    }
                }else if(status == "TW"){
                    $('#cariNoPengenalanPihak').val("");
                    $('#namaP').val("");
                    $('#noPengenalanPihak').val("");
                    $('#nPengenalanP').val("");
                    $('#jPengenalanP').val("");
                    $('#alamatP').val("");
                    $('#poskodP').val("");
                    $('#negeriP').val("");
                    $('#telP1').val("");
                    $('#telP2').val("");
                    $('#jantinaP').val("");
                }
            }, 'html');

        }else{
            alert("Sila masukkan no pengenalan");
            $("#noPengenalanPihak").focus();
        }

    }
    
    function findJenisPengenalan(){
   
        if($('#jenisPengenalan').val() == 'B'){
            document.getElementById("noPengenalanBaru").style.visibility = 'visible';
            document.getElementById("noPengenalanBaru").style.display = '';
            $('#noPengenalanLainLain').hide();
        }else{
            $('#noPengenalanLainLain').show();
            document.getElementById("noPengenalanBaru").style.visibility = 'hidden';
            document.getElementById("noPengenalanBaru").style.display = 'none';
        }
    }

  

</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatBantahanActionBean" name="form1">
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
    <div id ="msgInfoDiv">
        <s:messages/>
        <s:errors/>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Bantahan
            </legend>
            <div class="content">
                <s:hidden name = "kategoriPembantah" id="kategoriPembantahStatus"/>
                <s:hidden name = "statusView" id="statusView"/>
                <s:hidden name = "idMohonPihakBantah" id="idMohonPihakBantah"/>
                <div id="maklumatPihakDiv" style="visibility: hidden; display:none">
                    <s:hidden name = "statusCarian" id="statusCarian"/>
                    <s:hidden name="namaPihak" id="namaPihak"/>
                    <s:hidden name="jenisPengenalanPihak" id="jenisPengenalanPihak"/>
                    <s:hidden name="noPengenalanPihak" id="noPengenalanPihakCarian"/>
                    <s:hidden name="noTelefon1Pihak" id="noTelefon1Pihak"/>
                    <s:hidden name="noTelefon2Pihak" id="noTelefon2Pihak"/>
                    <s:hidden name="jantinaPihakCarian" id="jantinaPihakCarian"/>
                    <s:hidden name="alamatPihak" id="alamatPihak"/>
                    <s:hidden name="poskodPihak" id="poskodPihak"/>
                    <s:hidden name="negeriPihak" id="negeriPihak"/>
                    <s:hidden name = "idPihak" id="idPihak"/>

                    <input type="hidden" value="${fn:length(actionBean.listPihak)}" id="recordCount">
                    <c:set value="0" var="i"/>
                    <c:forEach items="${actionBean.listPihak}" var="senarai">
                        <input type="hidden" name="idPihakPembantah${i}" id="idPihakPembantah${i}" value="${senarai.pihak.idPihak}">
                        <c:set var="i" value="${i+1}" />
                    </c:forEach>
                </div>

                <input type="hidden" value="${fn:length(actionBean.listPihak)}" id="recordCount">
                <c:set value="0" var="i"/>
                <c:forEach items="${actionBean.listPihak}" var="senarai">
                    <input name="idPihakPembantah${i}" id="idPihakPembantah${i}" value="${senarai.pihak.idPihak}" type="hidden">
                    <c:set var="i" value="${i+1}" />
                </c:forEach>
                <c:if test="${actionBean.statusView eq 'add' || actionBean.statusView eq 'edit'}">
                    <p>
                        <label><em>*</em>Kategori Pembantah :</label>
                        <input type="hidden" name="statusKategoriPembantah" id="statusKategoriPembantah" value="${actionBean.kategoriPembantah}"/>
                        <input type="radio" name="kategoriPembantah" id="pihakBerkepentingan" value="P" onclick="changeCategory('P');" />Pihak Berkepentingan
                        <input type="radio" name="kategoriPembantah" id="lainLain" value="L" onclick="changeCategory('L');" />Lain-lain
                    </p>
                    <div id="pihakBerkepentinganDiv">
                        <p align="left">
                            <label><em>*</em>No.Pengenalan :</label>
                            <s:text name="noPengenalan" id="cariNoPengenalanPihak" maxlength="20"/>&nbsp;&nbsp;
                            <s:button class="btn" value="Cari" name="new" id="new" onclick="carianPihak();"/>&nbsp;&nbsp;

                        </p>
                        <p>
                            <label>Nama :</label>
                            <input type="text" id="namaP" size="30" disabled="true">&nbsp;

                        </p>
                        <p>
                            <label>Jenis Pengenalan :</label>
                            <input type="text" id="jPengenalanP" size="30" disabled="true">&nbsp;
                        </p>
                        <p>
                            <label>No.Pengenalan :</label>
                            <input type="text" id="nPengenalanP" size="30" disabled="true">&nbsp;
                        </p>
                        <p>
                            <label>Alamat :</label>
                            <textarea name="alamatP" cols="47" rows="3" id="alamatP" disabled="true"></textarea>
                        </p>
                        <p>
                            <label>Poskod :</label>
                            <input type="text" id="poskodP" size="30" disabled="true">&nbsp;
                        </p>
                        <p>
                            <label>Negeri :</label>
                            <input type="text" id="negeriP" size="30" disabled="true">&nbsp;

                        </p>
                        <div id="adaJantinaDiv" style="visibility: visible; display:">
                            <p>
                                <label>Jantina :</label>
                                <s:select name="jantinaPihak" id="jantinaP" disabled="true">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodJantina}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                        </div>
                        <div id="tiadaJantinaDiv" style="visibility: hidden; display: none">
                            <p>
                                <label><em>*</em>Jantina :</label>
                                <s:select name="jantinaPihak" id="jantinaPihak">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodJantina}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                        </div>
                        <p>
                            <label>Telefon Bimbit :</label>
                            <input type="text" id="telP1" size="30" disabled="true">&nbsp;
                        </p>
                        <p>
                            <label>Telefon Pejabat :</label>
                            <input type="text" id="telP2" size="30" disabled="true">&nbsp;
                        </p>
                    </div>
                    <div id="lainLainDiv">
                        <p>
                            <label><em>*</em>Nama :</label>
                            <s:text name="nama" id="nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label><em>*</em>Jenis Pengenalan :</label>
                            <s:select name="jenisPengenalan" id="jenisPengenalan" onchange="findJenisPengenalan()">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                            </s:select>
                        </p>

                        <p id="noPengenalanLainLain">
                            <label><em>*</em>No.Pengenalan :</label>
                            <s:text name="noPengenalan" id="noPengenalanLain" maxlength="12" />
                            <font color="red" size="1">cth : 860712335184 </font>
                            &nbsp;
                        </p>
                        <p id="noPengenalanBaru" style="visibility: hidden; display: none">
                            <label><em>*</em>No.Pengenalan :</label>
                            <s:text name="noPengenalan" id="noPengenalanLainBaru" maxlength="12" onkeyup="validateNumber(this,this.value);"/>
                            <font color="red" size="1">cth : 860712335184 </font>
                            &nbsp;
                        </p>
                        <p>
                            <label>Alamat :</label>
                            <s:text name="alamat1" id="alamat1" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="alamat2" id="alamat2" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="alamat3" id="alamat3" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="alamat4" id="alamat4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="poskod" id="poskod" onkeyup="validateNumber(this,this.value);" maxlength="5"/>
                        </p>
                        <p>
                            <label>Negeri :</label>
                            <s:select name="kodNgri" id="negeri">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                            </s:select>&nbsp;
                        </p>
                        <p>
                            <label><em>*</em>Jantina :</label>
                            <s:select name="jantina" id="jantina">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodJantina}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <p>
                            <label>Telefon Bimbit :</label>
                            <s:text name="noTelefon1" id="noTelefon1" size="19" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);"/>
                        </p>
                        <p>
                            <label>Telefon Pejabat :</label>
                            <s:text name="noTelefon2" id="noTelefon2" size="19" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);"/>
                    </div>

                    <p>
                        <label><em>*</em>Hubungan :</label>
                        <s:text name="kaitan" id="kaitan1" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Tarikh Bantahan:</label>
                        <s:text name="tarikhBantahan" class="datepicker" id="tarikhBantahanLain" formatPattern="dd/MM/yyyy" style="width:100px;"/>
                        &nbsp;
                    </p>
                    <p>
                        <label>Status Waris :</label>
                        <s:radio name="statusWaris" value="Y"/> Ya
                        <s:radio name="statusWaris" value="T"/> Tidak
                    </p>
                    <p>
                        <label>Catatan :</label>
                        <s:textarea name="catatan" rows="3" cols="50" id="catatan" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>

                    <br>

                    <br>
                    <p align="center">
                        <s:button class="btn" name="simpanBantahan" value="Simpan" onclick="if(validateForm())save(this.name,this.form);"/>
                        <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    </p>
                </c:if>

                <c:if test="${actionBean.statusView eq 'view'}">
                    <p>
                        <label>Kategori Pembantah :</label>
                        <c:if test="${actionBean.permohonanPihakMembantah.kategoriPihakMembantah eq 'L'}">
                            Lain-lain &nbsp;
                        </c:if>
                        <c:if test="${actionBean.permohonanPihakMembantah.kategoriPihakMembantah eq 'P'}">
                            Pihak Berkepentingan &nbsp;
                        </c:if>
                    </p>

                    <p>
                        <label>Nama :</label>
                        ${actionBean.permohonanPihakMembantah.nama}&nbsp;
                    </p>
                    <p>
                        <label>Jenis Pengenalan :</label>
                        ${actionBean.permohonanPihakMembantah.jenisPengenalan.nama}&nbsp;
                    </p>
                    <p>
                        <label>No.Pengenalan :</label>
                        ${actionBean.permohonanPihakMembantah.noPengenalan}&nbsp;
                    </p>
                    <p>
                        <label>Jantina :</label>
                        ${actionBean.permohonanPihakMembantah.jantina.nama}&nbsp;
                    </p>
                    <p>
                        <label>Alamat :</label>
                        ${actionBean.permohonanPihakMembantah.alamat1}&nbsp;
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        ${actionBean.permohonanPihakMembantah.alamat2}&nbsp;
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        ${actionBean.permohonanPihakMembantah.alamat3}&nbsp;
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        ${actionBean.permohonanPihakMembantah.alamat4}&nbsp;
                    </p>
                    <p>
                        <label>Poskod :</label>
                        ${actionBean.permohonanPihakMembantah.poskod}&nbsp;
                    </p>
                    <p>
                        <label>Negeri :</label>
                        ${actionBean.permohonanPihakMembantah.negeri.nama}&nbsp;
                    </p>
                    <p>
                        <label>Telefon Bimbit :</label>
                        ${actionBean.permohonanPihakMembantah.noTelefon1}&nbsp;
                    </p>
                    <p>
                        <label>Telefon Pejabat :</label>
                        ${actionBean.permohonanPihakMembantah.noTelefon2}&nbsp;
                    </p>
                    <p>
                        <label>Hubungan :</label>
                        ${actionBean.permohonanPihakMembantah.kaitan}&nbsp;
                    </p>
                    <p>
                        <label>Tarikh Bantahan:</label>
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permohonanPihakMembantah.tarikhBantahan}"/>&nbsp;
                    </p>
                    <p>
                        <label>Catatan :</label>
                        ${actionBean.permohonanPihakMembantah.catatan}&nbsp;
                    </p>
                </c:if>



            </div>
        </fieldset>
    </div>
</s:form>
