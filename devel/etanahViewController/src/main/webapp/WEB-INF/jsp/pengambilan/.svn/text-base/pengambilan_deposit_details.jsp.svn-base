<%--
    Document   : pengambilan_deposit
    Created on : Dec 15, 2012, 4:46:44 PM
    Author     : User
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<%-- carian hakmilik start--%>

<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<%-- carian hakmilik end--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    $(document).ready(function() {

        //            maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

        var bil = ${fn:length(actionBean.hakmilikPermohonan.hakmilik.senaraiPihakBerkepentingan)};
        for (var i = 0; i < bil; i++){
            if($("#pilihIdPihak"+i).val() == $('#idPihakInfoPembayaran').val()){
                document.getElementById("pilihIdPihak"+i).checked = true;
            }
        }

        if(document.getElementById("kodCaraBayaran5").checked == true){
            $("#jenisBank").attr("disabled",true);
            $("#noAkaun").attr("disabled",true);
        }


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
    <%--   function refreshpage(){
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
       }--%>
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
        function p(v){
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });

            $.get("${pageContext.request.contextPath}/pengambilan/depositMahkamah?pihakDetails&idPihak="+v,
            function(data){
                //alert(data);
                $("#perincianHakmilik").show();
                $("#perincianHakmilik").html(data);
                $.unblockUI();
            });

        }
        function HideforTunai() {
            $("#jenisBank").attr("disabled",true);
            $("#noAkaun").attr("disabled",true);
        }
        function Show(){
            $("#jenisBank").attr("disabled",false);
            $("#noAkaun").attr("disabled",false);
        }

        function validation() {
            if($("#jenisPengenalan").val() == ""){
                alert('Sila masukkan Jenis Pengenalan terlebih dahulu.');
                $("#jenisPengenalan").focus();
                return false;
            }
            if($("#noPengenalan").val() == ""){
                alert('Sila masukkan No Pengenalan terlebih dahulu.');
                $("#noPengenalan").focus();
                return false;
            }
            if($("#nama").val() == ""){
                alert('Sila masukkan Nama terlebih dahulu.');
                $("#nama").focus();
                return false;
            }
            if($("#alamat1").val() == ""){
                alert('Sila masukkan Alamat terlebih dahulu.');
                $("#alamat1").focus();
                return false;
            }
 //           if($("#alamat2").val() == ""){
 //               alert('Sila masukkan Alamat terlebih dahulu.');
 //               $("#alamat2").focus();
 //               return false;
 //           }
 //           if($("#alamat3").val() == ""){
 //               alert('Sila masukkan Alamat terlebih dahulu.');
 //               $("#alamat3").focus();
 //               return false;
 //           }
            if($("#poskod").val() == ""){
                alert('Sila masukkan Poskod terlebih dahulu.');
                $("#poskod").focus();
                return false;
            }
            if($("#negeri").val() == ""){
                alert('Sila masukkan Negeri terlebih dahulu.');
                $("#negeri").focus();
                return false;
            }
            if(document.getElementById("kodCaraBayaran5").checked == true){
                
            }
            else {
                if($("#jenisBank").val() == 0){
                    alert('Sila masukkan Jenis Bank terlebih dahulu.');
                    $("#jenisBank").focus();
                    return false;
                }
                if($("#noAkaun").val() == ""){
                    alert('Sila masukkan No Akaun terlebih dahulu.');
                    $("#noAkaun").focus();
                    return false;
                }
            }
            if($("#amaun").val() == ""){
                alert('Sila masukkan Amaun terlebih dahulu.');
                $("#amaun").focus();
                return false;
            }
            return true;
        }

    </script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pengambilan.PengambilanPendepositActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <fieldset class="aras1">
            <%--<c:if test="${edit}">--%>
            <legend>Maklumat Pendeposit</legend>
            <div class="subtitle" id="main">
                <div class="content" align="center">
                    <s:hidden name="pihak.idPihak" id="idPihakInfoPembayaran"/>
                    <s:hidden name="idPihak" id="idPihakInfoPembayaran" value="pihak.idPihak"/>
                    <s:hidden name="idPihak" id="idPihak" value="pihak.idPihak"/>
                    <%--${actionBean.pihak.idPihak}--%>
                    <table class="tablecloth" border="0" width="75%">
                        <tr>
                            <td>
                                <font color="red" size="4">*</font>Jenis Pengenalan :
                            </td>
                            <td>
                                <s:select name="kodJenisPengenalan.kod" id="jenisPengenalan" onchange="clearNoPengenalan();">
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
                                <%--  <input type=button name="searchPenyerah" value="Cari" class="btn" id="carianPihak"
                                  onclick="javascript:populatePenyerah(this);" />--%>
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
                            <td>No Telefon :</td>
                            <td>
                                <s:text name="pihak.noTelefon1" id="noTelefon1" value="${actionBean.pihak.noTelefon1}"size="40" onkeyup="validateNumber(this,this.value);"/> <em>[0512345678]</em>
                            </td>
                        </tr>
                        <tr>
                            <td>Emel :</td>
                            <td>
                                <s:text name="pihak.email" id="emel" value="${actionBean.pihak.email}"size="40"/> <em>[alamat_emel@gmail.com]</em>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="content" align="center">
                    <legend>Maklumat Bank</legend>
                    <table class="tablecloth" border="0" width="75%">
                        <tr>
                            <td>Cara Bayar :</td>
                            <td>
                                <s:radio name="caraBayaran" id="kodCaraBayaran2" value="CT" onclick="Show();"/> Cek Tempatan <br />
                        <tr>
                            <td><label >&nbsp;&nbsp;</label></td>
                            <td><s:radio name="caraBayaran" id="kodCaraBayaran3" value="EF" onclick="Show();"/> Wang Dalam Pindahan <br /></td>
                        </tr>
                        <tr>
                            <td><label >&nbsp;&nbsp;</label></td>
                            <td><s:radio name="caraBayaran" id="kodCaraBayaran4" value="DB" onclick="Show();"/> Deraf Bank <br /></td>
                        </tr>
                        <tr>
                            <td><label >&nbsp;&nbsp;</label></td>
                            <td><s:radio name="caraBayaran" id="kodCaraBayaran5" value="T" onclick="HideforTunai();"/> Tunai <br /></td>
                        </tr>


                        <%--<tr>
                        <td><font color="red" size="4">*</font>Cara Bayar :</td>
                        <td>
                            <s:select name="caraBayaran" id="caraBayar" >
                                <s:option value="0">Sila Pilih</s:option>
                                <s:option value="CT" onclick="Show();">Cek Tempatan</s:option>
                                <s:option value="EF" onclick="Show();">Wang Dalam Pindahan</s:option>
                                <s:option value="DB" onclick="Show();">Deraf Bank</s:option>
                                <s:option value="T" onclick="HideforTunai();">Tunai</s:option>
                            </s:select>
                        </td>
                    </tr>--%>
                        <tr>
                            <td><font color="red" size="4">*</font>Jenis Bank :</td>
                            <td>
                                <s:select name="kodBank.kod" id="jenisBank">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodBank}" label="nama" value="kod"/>
                                </s:select>
                            </td>
                        </tr>
                        <tr>
                            <td><font color="red" size="4">*</font>No Akaun Bank :</td>
                            <td>
                                <s:text name="noAkaun" id="noAkaun"  size="30" value="${actionBean.permohonanPihakPendeposit.noAkaun}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Amaun :</td>
                            <td>
                                RM <s:text name="amaun" id="amaun" formatPattern="#,##0.00" size="10" value="${actionBean.permohonanPihakPendeposit.amaun}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Catatan :</td>
                            <td>
                                <s:textarea name="catatan" id="catatan" cols="60" rows="5" value="${actionBean.permohonanPihakPendeposit.catatan}"/>
                            </td>
                        </tr>
                    </table>
                    <br/><br/>
                    <table class="tablecloth" border="0">
                        <tr>
                            <td align="right" colspan="2">
                                <%--<s:button name="simpanKandunganSempadan" id="save" value="Simpan" class="btn" onclick="saveSempadan(this.name, this.form);"/>--%>
                                <%--<s:submit name="simpanKandunganPendeposit" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div',idPihak)"/>--%>
                                <%--<s:button name="simpanKandunganPendeposit" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div',idPihak)"/>--%>
                                <s:button name="simpanKandunganPendeposit" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div',idPihak)"/>

                                <s:button name="showEditSempadan" value="Isi Semula" class="btn" onclick="resetUlasan(this.name, this.form);" />
                                <%--<s:button name="tutup" value="Kembali" class="btn" onclick="openFrame('pendeposit')"/>--%>
                                <%--<s:button name="closeWindow" value="Tutup" class="btn" onclick="closeWindow123(this.name, this.form)" />--%>
                            </td>
                        </tr>
                    </table>
                </div>

                <br/>
            </div>
            <%--</c:if>--%>
            <%--<c:if test="${!edit}">--%>

            <%--</c:if>--%>
        </fieldset>
    </s:form>
</body>