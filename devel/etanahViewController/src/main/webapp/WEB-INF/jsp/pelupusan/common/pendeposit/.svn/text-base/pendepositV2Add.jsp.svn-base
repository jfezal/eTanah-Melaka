<%-- 
    Document   : tambahPendeposit
    Created on : Nov 29, 2012, 12:44:23 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tambah Maklumat Pendeposit</title>
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
        function openFrame(type){
            NoPrompt();
            var idHakmilik = $('#idHakmilik').val();
            //    alert(idHakmilik);
            window.open("${pageContext.request.contextPath}/pelupusan/pendepositV2?openFrame&idHakmilik="
                +idHakmilik+"&type="+type, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
            //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
        }
        function refreshpage(){
            NoPrompt();
    <c:choose>
        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
                var idHakmilik = $('#idHakmilik').val();
                opener.refreshV2ManyHakmilik('main',idHakmilik);
        </c:when>
        <c:otherwise>
                opener.refreshV2('main');
        </c:otherwise>
    </c:choose>
            self.close();
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
        if (btn.id == "carianPihak"){
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
                $("#noTelefon1").val("");
                $("#emel").val("");
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
            $("#noTelefon1").val(p[9]);
            $("#emel").val(p[10]);
        });    
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
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pelupusan.common.PendepositV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>

        <div class="subtitle" id="main">
            <fieldset class="aras1">
                <s:hidden name="idHakmilik" id="idHakmilik"/>
                <div id="maklumatpendeposit">
                    <legend>Maklumat Pendeposit</legend>
                    <div class="content" align="center">
                        <table class="tablecloth" border="0">
                            <tr>
                                <td>
                                    <font color="red" size="4">*</font>Jenis Pengenalan :
                                </td>
                                <td>
                                    <s:select name="pihak.jenisPengenalan.kod" id="jenisPengenalan" onchange="clearNoPengenalan();">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiJenisPengenalanPelupusan}" label="nama" value="kod"/>
                                    </s:select>

                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>No Pengenalan :</td>
                                <td>
                                    <s:text name="pihak.noPengenalan" id="noPengenalan" size="20" onkeyup="dodacheck(this.value);" onblur="doUpperCase(this.id)"/> 
                                    <font title="No KP Baru: 780104069871, No KP Lama: A2977884, No Syarikat: 115793-P, No Pertubuhan: 432483-U"><em>[780104069871]</em></font>
                                    <input type=button name="searchPenyerah" value="Cari" class="btn" id="carianPihak"
                                    onclick="javascript:populatePenyerah(this);" />
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Nama :</td>
                                <td>
                                    <s:text name="pihak.nama" id="nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Alamat :</td>
                                <td>
                                    <s:text name="pihak.alamat1" value="${actionBean.pihak.alamat1}" id="alamat1" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>
                                    <s:text name="pihak.alamat2" value="${actionBean.pihak.alamat2}" id="alamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>
                                    <s:text name="pihak.alamat3" value="${actionBean.pihak.alamat3}" id="alamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>
                                    <s:text name="pihak.alamat4" value="${actionBean.pihak.alamat4}" id="alamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Poskod :</td>
                                <td>
                                    <s:text name="pihak.poskod" value="${actionBean.pihak.poskod}" size="5" id="poskod" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Negeri :</td>
                                <td>
                                    <s:select name="pihak.negeri.kod" id="negeri" >
                                        <s:option value="0">Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                                    </s:select>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>No Telefon :</td>
                                <td>
                                    <s:text name="pihak.noTelefon1" id="noTelefon1" value="${actionBean.pihak.noTelefon1}"size="40" onkeyup="validateNumber(this,this.value);"/> <em>[0512345678]</em>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Emel :</td>
                                <td>
                                    <s:text name="pihak.email" id="emel" value="${actionBean.pihak.email}"size="40"/> <em>[alamat_emel@gmail.com]</em>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="content" align="center">
                        <legend>Maklumat Bank</legend>
                        <table class="tablecloth" border="0">  
                        <tr>
                            <td><font color="red" size="4">*</font>Cara Bayar :</td>
                            <td>
                                <s:select name="permohonanPihakPendeposit.caraBayaran" id="caraBayar">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:option value="CT">Cek Tempatan</s:option>
                                    <s:option value="EF">Wang Dalam Pindahan</s:option>
                                    <s:option value="DB">Deraf Bank</s:option>
                                </s:select>
                            </td>
                        </tr>
                        <tr>
                            <td><font color="red" size="4">*</font>Jenis Bank :</td>
                            <td>
                                 <s:select name="permohonanPihakPendeposit.bank.kod" id="jenisBank">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodBank}" label="nama" value="kod"/>
                                </s:select>
                            </td>
                        </tr>
                         <tr>
                            <td><font color="red" size="4">*</font>No Akaun Bank :</td>
                            <td>
                                <s:text name="permohonanPihakPendeposit.noAkaun" id="noAkaun"  size="30"/> 
                            </td>
                        </tr>
                        <tr>
                            <td><font color="red" size="4">*</font>Amaun :</td>
                            <td>
                                RM <s:text name="permohonanPihakPendeposit.amaun" id="amaun" formatPattern="#,##0.00" size="10"/> 
                            </td>
                        </tr>
                        <tr>
                            <td><font color="red" size="4">*</font>Catatan :</td>
                            <td>
                                <s:textarea name="permohonanPihakPendeposit.catatan" id="catatan" cols="60" rows="5"/>
                            </td>
                        </tr>
                        </table>
                            <br/><br/>
                        <table class="tablecloth" border="0">        
                            <tr>
                                <td align="right" colspan="2">
                                    <%--<s:button name="simpanKandunganSempadan" id="save" value="Simpan" class="btn" onclick="saveSempadan(this.name, this.form);"/>--%>
                                    <s:submit name="simpanKandunganPendeposit" value="Simpan" class="btn" onclick="NoPrompt();"/>
                                    <s:button name="showEditSempadan" value="Isi Semula" class="btn" onclick="resetUlasan(this.name, this.form);" />
                                    <s:button name="tutup" value="Kembali" class="btn" onclick="openFrame('pendeposit')"/>
                                    <%--<s:button name="closeWindow" value="Tutup" class="btn" onclick="closeWindow123(this.name, this.form)" />--%> 
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
