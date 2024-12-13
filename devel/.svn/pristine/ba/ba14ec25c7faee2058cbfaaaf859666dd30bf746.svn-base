<%--
    Document   : Pu
    Created on : Jul 26, 2010, 3:23:20 PM
    Author     : Rohan
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
     function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {

        alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
        var nama = strNama.replace(" ", "_");
        var jawatan =  strJawatan.replace(" ", "_");
        var stageId = "g_penyediaan_pu";

        alert("nama:" + nama);
        alert ("jawatan:" + jawatan);
        alert ("stageid:" + stageId);
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + nama + " " +  jawatan + " " + strIDPermohonan + " " + stageId);
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

 </script>

<s:form beanclass="etanah.view.stripes.pelupusan.PUActionBean">
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
                <table border="0" width="55%" cellspacing="10">
                    <tr>
                        <td id="tdLabel" width="45%"><b>Rujukan Pejabat Tanah </b></td>
                         <td id="tdLabel"><b> : </b></td>
                        <td>
                        <s:text name="permohonanUkur.noRujukanPejabatTanah" size="32"/>
                        <s:hidden name="permohonanUkur.idMohonUkur"/> </td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>Rujukan Pejabat Ukur </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.noRujukanPejabatUkur" size="32"/></td>
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
                        <td><s:text name="permohonanUkur.perincianBorang5b" size="30"/></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (b) Lease Negeri (Borang 5C) bagi</b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.perincianBorang5c" size="30"/></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (c) Geran Mukim (Borang 5D) bagi</b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.perincianBorang5d" size="30"/></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (d) Pajakan Mukim (Borang 5E) bagi</b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.perincianBorang5e" size="30"/></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (e) Pajakan Lombong didahului oleh <br>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Perakuan Lombong bagi</b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.perincianPajakanLombong" size="30"/></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (f) Hakmilik Strata bagi</b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.perincianStrata" size="30"/></td>
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
                        <td><s:text name="permohonanUkur.pemberiPengecualian" size="20"/></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; Dibawah Perenggan Kanun Tanah Negara <br>
                             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   (Bayaran Ukur) 1965 </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.perengganKTN" size="10"/></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; Rujukan </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.rujukanKTN" size="10"/></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (b) Dikecualikan sekatan RM </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td id="tdLabel">   <s:text name="permohonanUkur.jumlahPengecualian" size="15" onkeyup="validateNumber(this,this.value);"/>
                        &nbsp; <b> oleh </b><s:text name="permohonanUkur.pemberiPengecualian" size="15"/>
                        </td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; Dibawah Perenggan Kanun Tanah Negara <br>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; (Bayaran Ukur) 1965 </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.perengganKTN" size="10"/></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; Rujukan </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td>   <s:text name="permohonanUkur.rujukanKTN" size="10"/></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (c) Bayaran Ukur RM </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.jumlahBayaranUkur" size="15" onkeyup="validateNumber(this,this.value);"/></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp; &nbsp; No Resit </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.noResit" size="20"/></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp; &nbsp; Tarikh Resit </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.tarikhResit" class="datepicker" formatPattern="dd/MM/yyyy" size="12"/></td>
                    </tr>

                    <tr>
                        <td id="tdLabel"><b>Dokumen-dokumen Hakmilik Sementara </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td id="tdLabel"><s:radio name="permohonanUkur.statusSuratanHakmilikSementara" value="T"/>Telah Dikeluarkan
                            <s:radio name="permohonanUkur.statusSuratanHakmilikSementara" value="B"/>Belum Dikeluarkan</td>
                    </tr>
                </table>
                <p align="center">
                    <s:button name="simpan" id="save" value="Simpan & Jana No PU" class="longbtn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    <s:button name="gis1" id="gis1" value="Penyediaan PU" class="longbtn"/>&nbsp;
                    <s:button name="gis2" id="gis2" value="Penyediaan B2" class="longbtn" onclick="RunProgram('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>
                    <%--<s:button name="gis2" id="gis2" value="Penyediaan PU" class="longbtn"/>&nbsp;--%>
                </p>
            </div>
        </fieldset>
    </div>
</s:form>
<br>
<%--<div align="center">
   <s:form beanclass="etanah.view.stripes.pembangunan.NoPUNoPTActionBean">
        <s:button name="frmPT" id="frmPT" value="Papar No PT" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
   </s:form>
</div>--%>