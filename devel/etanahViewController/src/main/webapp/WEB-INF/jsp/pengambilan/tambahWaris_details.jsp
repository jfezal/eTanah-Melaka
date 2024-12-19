<%--
    Document   : tambahWaris_details
    Created on : Dec 15, 2012, 4:46:44 PM
    Author     : hazirah
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

            $.get("${pageContext.request.contextPath}/pengambilan/status_1?pihakDetails&idPihak="+v,
            function(data){
                //alert(data);
                $("#perincianHakmilik").show();
                $("#perincianHakmilik").html(data);
                $.unblockUI();
            });

        }
        function HideforTunai() {
            $("#jenisBank").attr("disabled",true);
        }
        function Show(){
            $("#jenisBank").attr("disabled",false);
        }

        function savePenjaga(event, f, id){
            alert (id);
        <%--if(validation()){
            alert("simpan1");
        }else{--%>
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    self.opener.refreshPagePBT();
        <%--self.opener.refreshPageHakmilik();--%>
                    self.close();
                },'html');
        <%--}--%>
            }

            function removeNilai(idWakilPermohonanPihak)
            {
                if(confirm('Adakah anda pasti?')) {
                    var url = '${pageContext.request.contextPath}/pengambilan/status_1?deletePbt&idWakilPermohonanPihak='
                        +idWakilPermohonanPihak;
                    $.get(url,
                    function(data){
                        $('#page_div').html(data);
                    },'html');
                }
            }

    </script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pengambilan.StatusAwardActionBean_1" name="form">
        <s:errors/>
        <s:messages/>
        <fieldset class="aras1">

            <legend>

            </legend>
            <br/>
            <table>
                <tr>
                    <td><label>Nama :</label></td>
                    <td><s:text name="permohonanPihakWakil.nama" size="40" id="nama" onkeyup="this.value=this.value.toUpperCase();"/><font color="red">*</font></td>
                </tr>
                <tr>
                    <td><label>No. Pengenalan :</label></td>
                    <td><s:text name="permohonanPihakWakil.nomborPengenalan" id="kp" size="40" maxlength="12" onkeyup="dodacheck(this.value);this.value=this.value.toUpperCase();"/>&nbsp;<em style="font-style:normal">(cth : 800620125177)</em><font color="red">*</font></td>
                </tr>
                <tr>
                    <td><label>Alamat Berdaftar :</label></td>
                    <td><s:text name="permohonanPihakWakil.alamat1" id="alamat1" size="40" maxlength="40"/><font color="red">*</font></td>
                </tr>
                <tr>
                    <td><b>&nbsp;</b></td>
                    <td><s:text name="permohonanPihakWakil.alamat2" id="alamat2" size="40" maxlength="40"/><font color="red">*</font></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><s:text name="permohonanPihakWakil.alamat3" id="alamat3" size="40" maxlength="40"/><font color="red">*</font></td>

                </tr>
                <tr>
                    <td><label>Bandar :</label></td>
                    <td><s:text name="permohonanPihakWakil.alamat4" id="bandar" size="40" maxlength="25"/><font color="red">*</font></td>

                </tr>
                <tr>
                    <td><label>Poskod :</label></td>
                    <td><s:text name="permohonanPihakWakil.poskod" id="poskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/><font color="red">*</font></td>

                </tr>
                <tr>
                    <td><label>Negeri :</label></td>
                    <td><s:select name="kodnegeri" id="negeri" value="kod">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
                        </s:select><font color="red">*</font></td>

                </tr>
                <tr>
                    <td><label>No. Telefon :</label></td>
                    <td><s:text name="permohonanPihakWakil.nomborTelefon" id="noTelefon" size="40" maxlength="11"/><font color="red">*</font></td>

                </tr>
                <tr>
                    <td><label>Bank :</label></td>
                    <td><s:select name="kodbank" style="width:300px;" id="kodbank" value="kod">
                            <s:option value="">-- Sila Pilih --</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodBank}" value="kod" label="nama"/>
                        </s:select>
                    </td>
                </tr>
                <tr>
                    <td><label >No. Akaun:</label></td>
                    <td><s:text name="permohonanPihakWakil.nomborAkaun" id="noAkaun" size="50"/></td>
                </tr>
            </table>
            <br/>
            <center>
                <table>
                    <tr>
                        <td>&nbsp;</td>
                        <s:hidden name="idHakmilik1" value="${actionBean.hakmilik.idHakmilik}"/>
                        <td><s:button name="simpanKehadiran" id="simpan" value="Simpan" class="btn" onclick="savePenjaga(this.name, this.form, '${actionBean.hakmilik.idHakmilik}');"/></td>
                        <td><s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/></td>

                    </tr>
                </table>
            </center>

        </fieldset>
    </body>