<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"    prefix="c" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<title>Belakang Kaunter | Penyerah</title>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<c:set var="action" value="/daftar/kaunter"/>
<c:set var="nextStep" value="Step5a"/>
<c:set var="word" value="Langkah 4"/>
<c:if test="${!empty carian}">
    <c:set var="action" value="/daftar/carian"/>
    <c:set var="nextStep" value="Step3"/>
    <c:set var="word" value="Langkah 2"/>
</c:if>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script>

    $(document).ready(function() {
        $('input:text').each(function(){
            $(this).focus(function() { $(this).addClass('focus')});
            $(this).blur(function() { $(this).removeClass('focus')});
        });
        $('input:select').each(function(){
            $(this).focus(function() { $(this).addClass('focus')});
            $(this).blur(function() { $(this).removeClass('focus')});
        });

        $('form').submit(function(){
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
        });
    });
    
    var DELIM = "__^$__";
    
    function populatePenyerah(btn){
        var url;
        var jenis = $('#penyerahKod').val();
        if (btn.id == "carianPenyerah"){
            $('#kod').val('1');
            var idx = $("#idPenyerah").val();
            var jenis = $('#penyerahKod').val();
            if (idx == null || idx.length == 0){
                //alert("Sila masukkan ID Penyerah.");               
               
                window.open("${pageContext.request.contextPath}/common/req_penyerah_info?showFormPopup&jenisPenyerah=" + jenis , "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600,scrollbars=1");
                return;
            }
            
            if (jenis == '0'){
                alert('Sila pilih Jenis Penyerah');
                return;
            } else if (jenis == '01'){ // PEGUAM
                url = "${pageContext.request.contextPath}/common/req_peguam_info?idPeguam=" + idx;
            } else if (jenis == '02'){ // JUBL
                url = "${pageContext.request.contextPath}/common/req_jubl_info?idJUBL=" + idx;
            } else if (jenis == '00') {
                url = "${pageContext.request.contextPath}/common/req_unit_info?idUnit=" + idx;
            } else if (jenis == '05') {
                url = "${pageContext.request.contextPath}/common/req_agensi_info?idAgensi=" + idx;
            } else if (jenis == '06') { //Jabatan Kerajaan
                url = "${pageContext.request.contextPath}/common/req_agensi_info?idAgensi=" + idx;
            } else if (jenis == '07') { //Badan Berkanun
                url = "${pageContext.request.contextPath}/common/req_agensi_info?idAgensi=" + idx;
            }else if (jenis == '04'){ // Jurulelong Berlesen
                url = "${pageContext.request.contextPath}/common/req_lelong_info?idAgensi=" + idx;
            }
        } else if (btn.id == "carianPihak"){
            $('#kod').val('2');
            var jP = $("#penyerahJenisPengenalan").val();
            var noP = $("#penyerahNoPengenalan").val();
            if (jP == null || jP.length == 0 || noP == null || noP.length == 0){
                alert("Sila masukkan Jenis Pengenalan/No.Pengenalan.");
                return;
            }
            url = "${pageContext.request.contextPath}/common/req_pihak_info?jenisPengenalan=" + jP
                + "&noPengenalan=" + noP;
        }
	 
        $.get(url,
        function(data){
            if (data == null || data.length == 0){
                alert("Maklumat tidak dijumpai");
                return;
            }
            var p = data.split(DELIM);
            
            //EDITED, cek status penyerah, aktif ata tidak aktif
            
            if (p[11]=="T")
           
            {$('#penyerahJenisPengenalan').val("");
                $('#penyerahNoPengenalan').val("");            
                $("#penyerahNama").val("");
                $("#penyerahAlamat1").val("");
                $("#penyerahAlamat2").val("");
                $("#penyerahAlamat3").val("");
                $("#penyerahAlamat4").val("");
                $("#penyerahPoskod").val("");
                $("#penyerahNegeri").val("");
        
                alert("Penyerah tidak aktif");}       
       
        
            
            else {
                $('#penyerahJenisPengenalan').val(p[0]);
                $('#penyerahNoPengenalan').val(p[1]);            
                $("#penyerahNama").val(p[2]);
                $("#penyerahAlamat1").val(p[3]);
                $("#penyerahAlamat2").val(p[4]);
                $("#penyerahAlamat3").val(p[5]);
                $("#penyerahAlamat4").val(p[6]);
                $("#penyerahPoskod").val(p[7]);
                $("#penyerahNegeri").val(p[8]);
            }
            
            //
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

    function clearNoPengenalan(){
        $('#penyerahNoPengenalan').val('');
    }

    function test(f) {
        $(f).clearForm();
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
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none" alt=""/>
<%--<p class=title>URUSAN: ${actionBean.senaraiUrusanLabel}</p>--%>
<p class="title">Urusan : ${actionBean.namaUrusan}</p>
<p class=title>${word}: Maklumat Penyerah</p>

<s:messages />
<s:errors />

<span class=instr>Masukkan maklumat penyerah Permohonan/Perserahan. Jika Penyerah adalah Peguam yang berdaftar,
    masukkan Kod Peguam untuk mencari maklumat dari Rekod. Medan bertanda <em>*</em> adalah mandatori.</span><br/>

<s:form action="${action}" id="penyerah">
    <s:hidden name="kod" id="kod"/>
    <fieldset class="aras1">
        <legend>Maklumat Penyerah </legend>
        <br>
        <p>
            <label>Carian Penyerah: </label>
            <s:select name="penyerahKod.kod" id="penyerahKod" onchange="">
                <s:option value="0">Pilih Jenis ...</s:option>
                <s:options-collection collection="${listUtil.senaraiKodPenyerah}" label="nama" value="kod"/>
            </s:select>
            <s:text name="idPenyerah" size="9" id="idPenyerah" />
            <input type="button" id="carianPenyerah"
                   value="Cari" class="btn" onclick="javascript:populatePenyerah(this);" />
        </p>

        <p>
            <label for="Jenis Pengenalan">Carian: No. Pengenalan :</label>
            <s:select name="penyerahJenisPengenalan.kod" id="penyerahJenisPengenalan" onchange="clearNoPengenalan();">
                <s:option value="0">Pilih Jenis...</s:option>
                <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
            </s:select>
            <s:text name="penyerahNoPengenalan" id="penyerahNoPengenalan" onkeyup="dodacheck(this.value);"
                    onblur="doUpperCase(this.id)" size="18"/>
            <input type=button name="searchPenyerah" value="Cari" class="btn" id="carianPihak"
                   onclick="javascript:populatePenyerah(this);" />
        </p>

        <p>
            <label>Nama</label><s:text name="penyerahNama" id="penyerahNama" size="42" onkeyup="this.value=this.value.toUpperCase();"/><em>*</em>
        </p>

        <p>
            <label>Alamat</label>
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
            <label>Bandar</label>
            <s:text name="penyerahAlamat4" id="penyerahAlamat4" size="30" onkeyup="this.value=this.value.toUpperCase();"/>
        </p>

        <p>
            <label>Poskod</label>
            <s:text name="penyerahPoskod" maxlength="5" id="penyerahPoskod" size="17" onkeyup="validateNumber(this,this.value);" /><em>*</em>
        </p>

        <p>
            <label>Negeri</label>
            <s:select name="penyerahNegeri.kod" id="penyerahNegeri" style="width:200px;">
                <s:option value="0">Pilih ...</s:option>
                <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
            </s:select><em>*</em> <s:submit  name="updatePenyerah" value="Kemaskini Penyerah" class="longbtn"/>
        </p>
        <br/>
        <p align="center">
            <%--<c:if test="${actionBean.urusan ne 'HSBM' &&  actionBean.urusan ne 'HKBM' && actionBean.urusan ne 'HT'}">
                <s:submit name="Step2" value="Kembali" class="btn" />
            </c:if>
            <c:if test="${actionBean.urusan eq 'HSBM' || actionBean.urusan eq 'HKBM' && actionBean.urusan ne 'HT'}">
                <s:submit name="Step2" value="Kembali" class="btn" />
            </c:if>--%>
            <s:submit name="CancelAll" value="Batal" class="btn" />
            <%--   <c:if test="${empty carian}">
                   <s:submit name="Step6a" value="Tambah Urusan" class="btn" />
               </c:if>--%>
            <s:button  name="reset" value="Isi Semula" class="btn" onclick="test(this.form);"/>
            <s:submit name="${nextStep}" value="Seterusnya" class="btn" />
        </p>
        <br><br>
    </fieldset>
</s:form>