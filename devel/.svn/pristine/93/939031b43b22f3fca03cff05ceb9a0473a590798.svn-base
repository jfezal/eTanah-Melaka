<%--
    Document   : Pu
    Created on : Jul 26, 2010, 3:23:20 PM
    Author     : Rohan
    Modify by  : Siti Fariza Hanim (12042011)
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />


<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:20em;
    }
</style>

<script type="text/javascript">

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
        var strReturn = "0.00";
        var strBuffer = "0";
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

    <%--  function updateTotal(){
          document.getElementById("jumlahPengecualian").value = parseFloat(document.getElementById("jumlahPengecualian").value).toFixed(2);

        document.getElementById("jumlahBayaranUkur").value = parseFloat(document.getElementById("jumlahBayaranUkur").value).toFixed(2);
    }--%>

        function validateForm(){

            if(document.form.noRujukanPejabatTanah.value=="")
            {
                alert("Sila masukkan No. Rujukan Pejabat Tanah");
                $('#noRujukanPejabatTanah').focus();
                return false;
            }
            if(document.form.tujuan.value=="")
            {
                alert("Sila masukkan tujuan");
                $('#tujuan').focus();
                return false;
            }
            
            if(document.form.tarikhPerakuan.value=="")
            {
                alert("Sila masukkan tarikh perakuan");
                $('#tarikhPerakuan').focus();
                return false;
            }
            return true;
        }

    <%--     function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {

        alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
        var nama = strNama.replace(" ", "_");
        var jawatan =  strJawatan.replace(" ", "_");
        var stageId = "g_penyediaan_pu";
        alert("nama:" + nama);
        alert ("jawatan:" + jawatan);
        alert ("stageid:" + stageId);
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + nama + " " +  jawatan + " " + strIDPermohonan + " " + stageId);
    }

    function RunProgram2(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {

        alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
        var nama = strNama.replace(" ", "_");
        var jawatan =  strJawatan.replace(" ", "_");
        var stageId = "g_hantar_pu";
        alert("nama:" + nama);
        alert ("jawatan:" + jawatan);
        alert ("stageid:" + stageId);
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        objShell.Run(sysVar("eTanahGISPU") + " " + strUserID + " " + nama + " " +  jawatan + " " + strIDPermohonan + " " + stageId);
    }

  // testing
  function sendFile(idPermohonan,idAliran){
      idAliran="g_hantar_pu";
      alert("idPermohonan:"+idPermohonan);
      alert("idAliran:"+idAliran);
    var url = '${pageContext.request.contextPath}/utility/inboundIntegration?executeCMD&idPermohonan='+idPermohonan+'&idAliran='+idAliran;
              $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
  }--%>

</script>

<s:form beanclass="etanah.view.penguatkuasaan.Pu1ActionBean" name="form" id="form">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="center">
                <table border="0" width="20%" align="center">
                    <tr>
                        <td><b><font color="#003194">MAKLUMAT PERMOHONAN UKUR </font></b></td>
                    </tr>
                </table>
                <c:if test="${edit}">
                    <table border="0" width="55%" cellspacing="10">
                        <tr>
                            <td id="tdLabel" width="45%"><em>*</em><b>No.Rujukan Pejabat Tanah </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td>
                                <s:text name="permohonanUkur.noRujukanPejabatTanah" id="noRujukanPejabatTanah" size="32" maxlength="20" onkeyup="this.value=this.value.toUpperCase();"/>
                                <s:hidden name="permohonanUkur.idMohonUkur"/> </td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>No.Rujukan Pejabat Ukur </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td><s:text name="permohonanUkur.noRujukanPejabatUkur" size="32" maxlength="12" onkeyup="this.value=this.value.toUpperCase();"/></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>Negeri  </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td><font color="black">${actionBean.kodNegeri}&nbsp;</font></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>No Permohonan Ukur  </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td><font color="black">${actionBean.permohonanUkur.noPermohonanUkur} &nbsp;</font></td>
                        </tr>
                        <tr>
                            <td id="tdLabel" colspan="3"><b>Suratan-suratan Hakmilik yang dikehendaki untuk dikeluarkan :</b></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (a) Geran (Borang 5B) bagi</b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td><s:text name="permohonanUkur.perincianBorang5b" size="50" maxlength="45" onkeyup="this.value=this.value.toUpperCase();"/></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (b) Lease Negeri (Borang 5C) bagi</b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td><s:text name="permohonanUkur.perincianBorang5c" size="50" maxlength="45" onkeyup="this.value=this.value.toUpperCase();"/></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (c) Geran Mukim (Borang 5D) bagi</b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td><s:text name="permohonanUkur.perincianBorang5d" size="50" maxlength="45" onkeyup="this.value=this.value.toUpperCase();"/></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (d) Pajakan Mukim (Borang 5E) bagi</b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td><s:text name="permohonanUkur.perincianBorang5e" size="50" maxlength="45" onkeyup="this.value=this.value.toUpperCase();"/></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (e) Pajakan Lombong didahului oleh <br>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Perakuan Lombong bagi</b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td><s:text name="permohonanUkur.perincianPajakanLombong" size="50" maxlength="45" onkeyup="this.value=this.value.toUpperCase();"/></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (f) Hakmilik Strata bagi</b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td><s:text name="permohonanUkur.perincianStrata" size="50" maxlength="45" onkeyup="this.value=this.value.toUpperCase();"/></td>
                        </tr><br>
                        <tr>
                            <td id="tdLabel" ><b>Suratan-suratan Hakmilik </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td id="tdLabel">  <s:radio name="permohonanUkur.statusSuratanHakmilik" value="A"/>Akan dimansuhkan
                                &nbsp;&nbsp; <s:radio name="permohonanUkur.statusSuratanHakmilik" value="T"/>Telah dimansuhkan</td>
                        </tr>
                        <tr><td id="tdLabel" colspan="3"><b>Bayaran-bayaran Ukur :</b></td></tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (a) Dikecuali penuh oleh </b> </td>
                            <td id="tdLabel"><b> : </b></td>
                            <td><s:text name="permohonanUkur.pemberiPengecualian" size="50" maxlength="45" onkeyup="this.value=this.value.toUpperCase();"/></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; Dibawah Perenggan Kanun Tanah Negara <br>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   (Bayaran Ukur) 1965 </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td><s:text name="permohonanUkur.perengganKTN" size="50" maxlength="45" onkeyup="this.value=this.value.toUpperCase();"/></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; Rujukan </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td><s:text name="permohonanUkur.rujukanKTN" size="20" maxlength="8" onkeyup="this.value=this.value.toUpperCase();"/></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (b) Dikecualikan sekatan RM </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td id="tdLabel">   <s:text name="permohonanUkur.jumlahPengecualian" id="jumlahPengecualian" class="number" formatPattern="0.00" maxlength="14" onkeyup="validateNumber(this,this.value);" size="15"/>
                                &nbsp; <b> oleh </b><s:text name="permohonanUkur.pemberiPengecualian" size="25" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; Dibawah Perenggan Kanun Tanah Negara <br>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; (Bayaran Ukur) 1965 </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td><s:text name="permohonanUkur.perengganKTN" size="20" onkeyup="this.value=this.value.toUpperCase();"/></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; Rujukan </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td>   <s:text name="permohonanUkur.rujukanKTN" size="20" onkeyup="this.value=this.value.toUpperCase();"/></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (c) Bayaran Ukur RM </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td><s:text name="permohonanUkur.jumlahBayaranUkur" id="jumlahBayaranUkur" size="15" class="number" formatPattern="0.00"  maxlength="14" onkeyup="validateNumber(this,this.value);"/></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp; &nbsp; No Resit </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td><s:text name="permohonanUkur.noResit" size="20" maxlength="10" onkeyup="this.value=this.value.toUpperCase();" /></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp; &nbsp; Tarikh Resit </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td><s:text name="permohonanUkur.tarikhResit" class="datepicker" formatPattern="dd/MM/yyyy" size="12"/></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b><em>*</em>Tarikh Perakuan </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td><s:text name="tarikhPerakuan" class="datepicker" id="tarikhPerakuan" formatPattern="dd/MM/yyyy" size="12"/></td>
                        </tr>

                        <tr>
                            <td id="tdLabel"><b>Dokumen-dokumen Hakmilik Sementara </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td id="tdLabel"><s:radio name="permohonanUkur.statusSuratanHakmilikSementara" value="T"/>Telah Dikeluarkan
                                <s:radio name="permohonanUkur.statusSuratanHakmilikSementara" value="B"/>Belum Dikeluarkan</td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b><em>*</em>Tujuan </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td><s:textarea name="permohonanUkur.tujuan" id="tujuan" lang="100" cols="50" rows="3" onkeyup="this.value=this.value.toUpperCase();" /></td>
                        </tr>
                    </table>
                    <p align="center">
                        <s:button name="simpan" id="save" value="Simpan & Jana No PU" class="longbtn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div')"/>
                        <%--<s:button name="lakarPelan" id="lakarPelan" value="Penyedian PU GIS" class="longbtn"  onclick="RunProgram('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;--%>
                        <%--<s:button name="lakarPelan" id="lakarPelan" value="Penghantaran PU" class="longbtn"  onclick="RunProgram2('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;--%>
                    </p>
                </c:if>
                <c:if test="${!edit}">
                    <table border="0" width="55%" cellspacing="10">
                        <tr>
                            <td id="tdLabel" width="45%"><b>Rujukan Pejabat Tanah </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td>${actionBean.permohonanUkur.noRujukanPejabatTanah}
                                <%--<s:text name="permohonanUkur.noRujukanPejabatTanah" size="30"/>
                                <s:hidden name="permohonanUkur.idMohonUkur"/> --%></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>Rujukan Pejabat Ukur </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td>${actionBean.permohonanUkur.noRujukanPejabatUkur}<%--<s:text name="permohonanUkur.noRujukanPejabatUkur" size="32"/>--%></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>Negeri  </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td><font color="black">${actionBean.kodNegeri}&nbsp;</font></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>No Permohonan Ukur  </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td><font color="black">${actionBean.permohonanUkur.noPermohonanUkur} &nbsp;</font></td>
                        </tr>
                        <tr>
                            <td id="tdLabel" colspan="3"><b>Suratan-suratan Hakmilik yang dikehendaki untuk dikeluarkan :</b></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (a) Geran (Borang 5B) bagi</b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td>${actionBean.permohonanUkur.perincianBorang5b}<%--<s:text name="permohonanUkur.perincianBorang5b" size="30"/>--%></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (b) Lease Negeri (Borang 5C) bagi</b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td>${actionBean.permohonanUkur.perincianBorang5c}<%--<s:text name="permohonanUkur.perincianBorang5c" size="30"/>--%></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (c) Geran Mukim (Borang 5D) bagi</b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td>${actionBean.permohonanUkur.perincianBorang5d} <%--<s:text name="permohonanUkur.perincianBorang5d" size="30"/>--%></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (d) Pajakan Mukim (Borang 5E) bagi</b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td>${actionBean.permohonanUkur.perincianBorang5e} <%--<s:text name="permohonanUkur.perincianBorang5e" size="30"/>--%></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (e) Pajakan Lombong didahului oleh <br>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Perakuan Lombong bagi</b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td>${actionBean.permohonanUkur.perincianPajakanLombong} <%--<s:text name="permohonanUkur.perincianPajakanLombong" size="30"/>--%></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (f) Hakmilik Strata bagi</b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td>${actionBean.permohonanUkur.perincianStrata} <%--<s:text name="permohonanUkur.perincianStrata" size="30"/>--%></td>
                        </tr><br>
                        <tr>
                            <td id="tdLabel" ><b>Suratan-suratan Hakmilik </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td>
                                <c:if test="${actionBean.permohonanUkur.statusSuratanHakmilik eq 'A'}">
                                    Akan dimansuhkan
                                </c:if>
                                <c:if test="${actionBean.permohonanUkur.statusSuratanHakmilik eq 'T'}">
                                    Telah dimansuhkan
                                </c:if>
                                <%--<s:radio name="permohonanUkur.statusSuratanHakmilik" value="A"/>Akan dimansuhkan
                                &nbsp;&nbsp; <s:radio name="permohonanUkur.statusSuratanHakmilik" value="T"/>Telah dimansuhkan--%></td>
                        </tr>
                        <tr><td id="tdLabel" colspan="3"><b>Bayaran-bayaran Ukur :</b></td></tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (a) Dikecuali penuh oleh </b> </td>
                            <td id="tdLabel"><b> : </b></td>
                            <td>${actionBean.permohonanUkur.pemberiPengecualian} <%--<s:text name="permohonanUkur.pemberiPengecualian" size="20"/>--%></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; Dibawah Perenggan Kanun Tanah Negara <br>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   (Bayaran Ukur) 1965 </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td>${actionBean.permohonanUkur.perengganKTN} <%--<s:text name="permohonanUkur.perengganKTN" size="10"/>--%></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; Rujukan </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td>${actionBean.permohonanUkur.rujukanKTN} <%--<s:text name="permohonanUkur.rujukanKTN" size="10"/>--%></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (b) Dikecualikan sekatan RM </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td> ${actionBean.permohonanUkur.jumlahPengecualian}  <%--<s:text name="permohonanUkur.jumlahPengecualian" onkeyup="validateNumber(this,this.value);" size="15"/>--%>
                                &nbsp; <b> oleh </b> &nbsp;  ${actionBean.permohonanUkur.pemberiPengecualian} <%--<s:text name="permohonanUkur.pemberiPengecualian" size="15"/>--%>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; Dibawah Perenggan Kanun Tanah Negara <br>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; (Bayaran Ukur) 1965 </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td>${actionBean.permohonanUkur.perengganKTN} <%--<s:text name="permohonanUkur.perengganKTN" size="10"/>--%></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; Rujukan </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td> ${actionBean.permohonanUkur.rujukanKTN}  <%--<s:text name="permohonanUkur.rujukanKTN" size="10"/>--%></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (c) Bayaran Ukur RM </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td>${actionBean.permohonanUkur.jumlahBayaranUkur}<%--<s:text name="permohonanUkur.jumlahBayaranUkur" size="15" onkeyup="validateNumber(this,this.value);"/>--%></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp; &nbsp; No Resit </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td>${actionBean.permohonanUkur.noResit}<%--<s:text name="permohonanUkur.noResit" size="20"/>--%></td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>&nbsp; &nbsp; Tarikh Resit </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td><fmt:formatDate value="${actionBean.permohonanUkur.tarikhResit}" pattern="dd/MM/yyyy"  />  <%-- <s:text name="permohonanUkur.tarikhResit" class="datepicker" formatPattern="dd/MM/yyyy" size="12"/>--%></td>
                        </tr>

                        <tr>
                            <td id="tdLabel"><b>Dokumen-dokumen Hakmilik Sementara </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td>
                                <c:if test="${actionBean.permohonanUkur.statusSuratanHakmilikSementara  eq 'T'}">
                                    Telah Dikeluarkan
                                </c:if>
                                <c:if test="${actionBean.permohonanUkur.statusSuratanHakmilikSementara  eq 'B'}">
                                    Belum Dikeluarkan
                                </c:if>
                                <%--<s:radio name="permohonanUkur.statusSuratanHakmilikSementara" value="T"/>Telah Dikeluarkan
                                <s:radio name="permohonanUkur.statusSuratanHakmilikSementara" value="B"/>Belum Dikeluarkan--%></td>
                        </tr><tr>
                            <td id="tdLabel"><b>&nbsp; &nbsp; Tujuan </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td>${actionBean.permohonanUkur.tujuan}<%--<s:text name="permohonanUkur.noResit" size="20"/>--%></td>
                        </tr>

                    </table>
                    <%--  <p align="center">
                          <s:button name="simpan" id="save" value="Simpan & Jana No PU" class="longbtn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                           <s:button name="transferFile" id="btnClick" value="Penghantaran PU" class="btn" onclick="RunProgram('ptspoc1','Pembantu_Tadbir_SPOC','${actionBean.idPermohonan}','123456',this.name, this.form)" />
                          <s:button name="transferFile" id="btnClick" value="Penghantaran PU" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')" />

                    <s:button name="lakarPelan" id="lakarPelan" value="Penghantaran PU2" class="longbtn"  onclick="RunProgram2('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;


                </p>--%>
                </c:if>
                <%--  <p align="center">
                      <s:button name="frmPT" id="frmPT" value=" Testing " class="btn" onclick="sendFile('${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                  </p>--%>
            </div>
        </fieldset>
    </div>
</s:form>
<br>
<div align="center">

    <%--   <s:form beanclass="etanah.view.stripes.pembangunan.NoPUNoPTActionBean">
            <s:button name="frmPT" id="frmPT" value="Papar No PT" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
       </s:form>--%>
</div>