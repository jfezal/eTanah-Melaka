<%-- 
    Document   : tambah_pemohonV2Pengarah
    Created on : Jul 23, 2013, 11:12:00 AM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>

<script type="text/javascript">
    
    $(document).ready( function(){
    });
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }
    function savePemohonhliLembaga(event, f){
        //alert("savePemohonhliLembaga");
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
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
    
    function editKeluarga()
    {
        NoPrompt();
        var idPemohon = $('#idPemohon').val();
        //            alert(idPemohon);
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohonV2?showFormMaklumatPengarah&idPemohon="+idPemohon, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }   
    
    function Hide1(){
                // alert($('input[value=check]').is(':checked'));
                if($('input[value=check]').is(':checked')){
                    $('#alamatPemohonHubungan1').hide();
                    $('#alamatPemohonHubungan2').hide();
                    $('#alamatPemohonHubungan3').hide();
                    $('#alamatPemohonHubungan4').hide();
                    $('#alamatPemohonHubungan5').hide();
                    $('#alamatPemohonHubungan6').hide();
                } else {
                    $('#alamatPemohonHubungan1').show();
                    $('#alamatPemohonHubungan2').show();
                    $('#alamatPemohonHubungan3').show();
                    $('#alamatPemohonHubungan4').show();
                    $('#alamatPemohonHubungan5').show();
                    $('#alamatPemohonHubungan6').show();
                }
            }
    
</script>
<body>
    <script>
        // Allow the user to be warned by default.
        var allowPrompt = true;
                
        window.onbeforeunload = WarnUser;
        function WarnUser()
        {   
            if(allowPrompt)
                refreshpage();
            if(allowPrompt)
            {
                event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
            }
            else
            {
                // Reset the flag to its default value.
                allowPrompt = true;
            }
        }
        function NoPrompt()
        {
            allowPrompt = false;
        }
        
    </script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="list" />
    <s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPemohonV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="permohonan.kodUrusan.kod"/>
        <s:hidden name="idPemohon" id="idPemohon"/>
        <s:hidden name="idPemohonHubungan" value="${actionBean.pihakPengarah.idPengarah}"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Tambah Ahli Lembaga Pengarah</legend>
                <div class="content" align="center">
                    <font color="red">Sila Masukkan Maklumat Yang Bertanda *</font>
                    <table class="tablecloth" border="0">
                        <tr>
                            <td>
                                <em>*</em>Nama Ahli :
                            </td>
                            <td>
                                <s:text name="pihakPengarah.nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <em>*</em>Jenis Pengenalan :
                            </td>
                            <td>
                                <s:select name="pihakPengarah.jenisPengenalan.kod" id="jenisPengenalan"  onchange="javaScript:changeHideSuku(this.options[selectedIndex].text)">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiJenisPengenalanIndividu}" label="nama" value="kod"/>
                                </s:select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <em>*</em>Nombor Kad Pengenalan :
                            </td>
                            <td>
                                <s:text name="pihakPengarah.noPengenalan" size="40" id="kp"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="checkbox" id="add1" name="add1" value="check" onclick="Hide1();"/>
                                <font color="red">Alamat surat-menyurat sama dengan alamat syarikat.</font>
                            </td>
                            <td>
                                &nbsp;
                            </td>
                        </tr>
                        <tr id="alamatPemohonHubungan1">
                            <td>
                                <em>*</em>Alamat :
                            </td>
                            <td>
                                <s:text name="pihakPengarah.alamat1" value="${actionBean.pihakPengarah.alamat1}" id="alamat1" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr id="alamatPemohonHubungan2">
                            <td>
                                &nbsp;
                            </td>
                            <td>
                                <s:text name="pihakPengarah.alamat2" value="${actionBean.pihakPengarah.alamat2}" id="alamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr id="alamatPemohonHubungan3">
                            <td>
                                &nbsp;
                            </td>
                            <td>
                                <s:text name="pihakPengarah.alamat3" value="${actionBean.pihakPengarah.alamat3}" id="alamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr id="alamatPemohonHubungan4">
                            <td>
                                &nbsp;
                            </td>
                            <td>
                                <s:text name="pihakPengarah.alamat4" value="${actionBean.pihakPengarah.alamat4}" id="alamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr id="alamatPemohonHubungan5">
                            <td>
                                <em>*</em>Poskod :
                            </td>
                            <td>
                                <s:text name="pihakPengarah.poskod" value="${actionBean.pihakPengarah.poskod}" size="40" id="poskod" maxlength="5" onkeyup="validateNumber(this,this.value);"/> 
                            </td>
                        </tr>
                        <tr id="alamatPemohonHubungan6">
                            <td>
                                <em>*</em>Negeri :
                            </td>
                            <td>
                                <s:select name="pihakPengarah.kodNegeri.kod" id="negeri" >
                                    <s:option value="0">Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" />
                                </s:select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Jumlah Saham :
                            </td>
                            <td>
                                <s:text name="pihakPengarah.jumlahSaham"  maxlength="3" value="${actionBean.pihakPengarah.jumlahSaham}" size="10" onkeyup="validateNumber(this,this.value);"/>
                                <b>Peratus (%)</b>
                            </td>
                        </tr>
                    </table>
                    <br/>
                    <table class="tablecloth" border="0">
                        <tr>
                            <td colspan="2"><s:submit name="simpanPemohonHubungan" value="Simpan" class="btn" onclick="NoPrompt();"/>
                                <s:hidden name="type" id="type" value="tPengarah"/>
                                <s:hidden name="status" id="status" value="${status}"/>
                                <s:button name="Tambah" id="save" value="Kembali" class="btn" onclick="editKeluarga()"/></td>
                        </tr>
                    </table>
                </div>
                <br/>

            </fieldset>
        </div>
    </s:form>
</body>

