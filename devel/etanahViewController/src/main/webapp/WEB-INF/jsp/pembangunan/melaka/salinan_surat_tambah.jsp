<%-- 
    Document   : salinan_surat_tambah
    Created on : May 20, 2015, 4:08:43 PM
    Author     : k.Hazwan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Tambah Salinan Kepada</title>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>

<script type="text/javascript">
    
    $(document).ready(function() {
         
        //            maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
   
    }); //END OF READY FUNCTION
         
        
    
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
    function openFrame(){     
            
        NoPrompt();
        var idPermohonan = $('#idPermohonan').val();
        var kodDokumen = $('#kodDokumen').val();
        window.open("${pageContext.request.contextPath}/utiliti/utilitiSalinanDev?showFormSalinan&idPermohonan="+idPermohonan+"&kodDokumen="+kodDokumen, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }
    
    function dodacheck(val) {
        //var mikExp = /[1\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\1\|]/;
        var v = $('#jenisPengenalan').val();
        if(v == 'B') {
            var strPass = val;
            var strLength = strPass.length;
            if(strLength > 12) {
                var tst = val.substring(0, (strLength) - 1);
                $('#noPengenalan').val(tst);
            }
            var lchar = val.charAt((strLength) - 1);
            if(isNaN(lchar)) {
                //return false;
                var tst = val.substring(0, (strLength) - 1);
                $('#noPengenalan').val(tst);
            }
        }
    }

    function clearNoPengenalan(){
        $('#noPengenalan').val('');
    }
        
    
    function populatePenyerah(btn){
        var url;
        var DELIM = "__^$__";
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
        } 
        else if (btn.id == "carianPihak"){
            $('#kod').val('2');
            var jP = $("#jenisPengenalan").val();
            var noP = $("#noPengenalan").val();
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
                $("#nama").val("");
                $("#alamat1").val("");
                $("#alamat2").val("");
                $("#alamat3").val("");
                $("#alamat4").val("");
                $("#poskod").val("");
                $("#negeri").val("");
                return;
            }
            var p = data.split(DELIM);
            $('#jenisPengenalan').val(p[0]);
            $('#noPengenalan').val(p[1]);            
            $("#nama").val(p[2]);
            $("#alamat1").val(p[3]);
            $("#alamat2").val(p[4]);
            $("#alamat3").val(p[5]);
            $("#alamat4").val(p[6]);
            $("#poskod").val(p[7]);
            $("#negeri").val(p[8]);
        });    
    }

    function save1(event, f){
        
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.opener.refreshSK();
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
<body>

    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pembangunan.SalinanKepadaActionBean">
        <s:errors/>
        <s:messages/>
        <s:hidden name="salinanKepada.idSalinanKepada"/>
        <div class="subtitle" id="main">
            <fieldset class="aras1">
                <s:hidden name="idPermohonan" id="idPermohonan"/> 
                <s:hidden name="kodDokumen" id="kodDokumen"/>
                <div id="perihaltanah">
                    <legend>Tambah Salinan</legend>
                    <div class="content" align="center">
                        <table class="tablecloth" border="0">

                            <tr>
                                <td><font color="red" size="4">*</font>Nama :</td>
                                <td>
                                    <s:text name="salinanKepada.nama" value="${actionBean.salinanKepada.nama}" id="nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                                </td>

                            </tr>
                            <tr>
                                <td>
                                    <font color="red" size="4">*</font>Alamat :
                                </td>
                                <td>
                                    <s:text name="salinanKepada.alamat1" value="${actionBean.salinanKepada.alamat1}" id="alamat1" size="40" onkeyup="this.value=this.value.toUpperCase();"/>

                                </td>
                            </tr>
                            <tr>
                                <td>
                                    &nbsp;
                                </td>
                                <td>
                                    <s:text name="salinanKepada.alamat2" value="${actionBean.salinanKepada.alamat2}" id="alamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>

                                </td>
                            </tr>
                            <tr>
                                <td>
                                    &nbsp;
                                </td>
                                <td>
                                    <s:text name="salinanKepada.alamat3" value="${actionBean.salinanKepada.alamat3}" id="alamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>

                                </td>
                            </tr>
                            <tr>
                                <td>
                                    &nbsp;
                                </td>
                                <td>
                                    <s:text name="salinanKepada.alamat4" value="${actionBean.salinanKepada.alamat4}" id="alamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>

                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <em>*</em>Poskod :
                                </td>
                                <td>
                                    <s:text name="salinanKepada.poskod" value="${actionBean.salinanKepada.poskod}" size="40" id="poskod" maxlength="5" onkeyup="validateNumber(this,this.value);"/> 
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <em>*</em>Negeri :
                                </td>
                                <td>
                                    <s:select name="salinanKepada.negeri.kod" id="negeri" >
                                        <s:option value="0">Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                                    </s:select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                   Catatan :
                                </td>
                                <td>
                                    <s:textarea name="salinanKepada.catatan" cols="50" rows="10" value="${actionBean.salinanKepada.poskod}" />                                    
                                </td>
                            </tr>
                            <tr>
                                <td align="right" colspan="2">
                                    <s:button name="simpanSalinan" value="Simpan" class="btn" onclick="save1(this.name, this.form);"/>
                                    <%--<s:reset name="RESET" value="Isi Semula" class="btn"/>--%>
                                    <s:button name="tutup" value="Tutup" class="btn" onclick="self.close();"/>
                                </td>
                            </tr>
                        </table>

                    </div>
                    <br/>
                </div>
            </fieldset>
        </div>
    </s:form>
</body>


