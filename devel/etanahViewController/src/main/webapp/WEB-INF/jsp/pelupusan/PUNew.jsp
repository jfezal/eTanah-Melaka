<%-- 
    Document   : PUNew
    Created on : Apr 4, 2011, 10:16:08 PM
    Author     : Rohans
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
    
      $(document).ready(function() {
          var status = $('#status').val();
//          alert(status);
        if(status == 'P'){
           
            $('#penuh').show();
            $('#sebahagian').hide();
             document.getElementById('cara').checked = 'true';
        }
        else if(status == 'S'){
          
            $('#penuh').hide();
            $('#sebahagian').show();
              document.getElementById('cara2').checked = 'true';
        }
        else if(status == 'T'){
           
            $('#penuh').hide();
            $('#sebahagian').hide();
             document.getElementById('cara3').checked = 'true';
        }
        else {
            $('#penuh').hide();
            $('#sebahagian').hide();
        }
        
    });
    
    function hide() {
        $('#penuh').hide();
        $('#sebahagian').hide();
    }
    
    function show() {
        $('#penuh').show();
        $('#sebahagian').hide();
    }
    
    function show2() {
        $('#sebahagian').show();
        $('#penuh').hide();
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

      function ReplaceAll(Source,stringToFind,stringToReplace){
        var temp = Source;
        var index = temp.indexOf(stringToFind);
            while(index != -1){
                temp = temp.replace(stringToFind,stringToReplace);
                index = temp.indexOf(stringToFind);

            }
             return temp;
        }

     function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {

        alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
        strNama = ReplaceAll(strNama," ","_");
        strJawatan = ReplaceAll(strJawatan," ","_");
        var stageId = "g_penyediaan_pu_pt";
        alert("nama:" + strNama);
        alert ("jawatan:" + strJawatan);
        alert ("stageid:" + stageId);
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + stageId);
    }

        function RunProgram3(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {

        alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
        strNama = ReplaceAll(strNama," ","_");
        strJawatan = ReplaceAll(strJawatan," ","_");
        var stageId = "g_terima_pa_b1";
        alert("nama:" + strNama);
        alert ("jawatan:" + strJawatan);
        alert ("stageid:" + stageId);
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        objShell.Run(sysVar("eTanahGISB1") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + stageId);
    }

    function RunProgram2(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {

        alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
        strNama = ReplaceAll(strNama," ","_");
        strJawatan = ReplaceAll(strJawatan," ","_");
        var stageId = "g_hantar_pu";
        alert("nama:" + strNama);
        alert ("jawatan:" + strJawatan);
        alert ("stageid:" + stageId);
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        objShell.Run(sysVar("eTanahGISPU") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + stageId);
   }
    function RunProgram4(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {

        alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
        strNama = ReplaceAll(strNama," ","_");
        strJawatan = ReplaceAll(strJawatan," ","_");
        var stageId = "semak_pu";
        alert("nama:" + strNama);
        alert ("jawatan:" + strJawatan);
        alert ("stageid:" + stageId);
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        objShell.Run(sysVar("eTanahGISPU") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + stageId);
   }

  // testing
   function refreshRizab(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

  <%--function sendFile(strUserID,idPermohonan,idAliran){
      idAliran="agihancharting";
      idPermohonan="0405DEV2011007095";
      alert("idPermohonan:"+idPermohonan);
      alert("idAliran:"+idAliran);
      alert("strUserID:"+strUserID);
    var url = '${pageContext.request.contextPath}/utility/inboundIntegration?executeCMD&idPermohonan='+idPermohonan+'&pengguna='+strUserID+'&idAliran='+idAliran;
              $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
  }--%>

 </script>

<s:form beanclass="etanah.view.stripes.pelupusan.PUNewActionBean">
    <s:hidden name="edit1" id="edit1" />
    <s:hidden name="edit2" id="edit2" />
    <s:hidden name="edit3" id="edit3" />
    <s:hidden name="edit4" id="edit4" />
    <s:hidden name="view" id="view" />

    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>MAKLUMAT PERMOHONAN UKUR</legend>
            <div class="content" align="center">
                <s:hidden name="permohonanUkur.statusBayaranUkur" id="status"/>
                <c:if test="${actionBean.edit1}">
                <table border="0" width="55%" cellspacing="5">
                   <%-- <tr>
                        <td id="tdLabel" width="45%"><b>Rujukan Pejabat Tanah </b></td>
                         <td id="tdLabel"><b> : </b></td>
                         <td>${actionBean.permohonan.idPermohonan} &nbsp;
                        <s:text name="permohonanUkur.noRujukanPejabatTanah" size="30"/>
                        --%><s:hidden name="permohonanUkur.idMohonUkur"/><%--
                        </td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>Rujukan Pejabat Ukur </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.noRujukanPejabatUkur" size="30"/></td>
                    </tr>--%><%--
                    <tr>
                        <td id="tdLabel"><b>Negeri  </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><font color="black">${actionBean.kodNegeri}&nbsp;</font></td>
                    </tr>

                  --%>
                  <c:if test="${actionBean.permohonanUkur.idMohonUkur ne null}">
                      <tr>
                          <td>No Permohonan Ukur  </td>
                          <td> : </td>
                          <td>${actionBean.permohonanUkur.noPermohonanUkur} &nbsp;</td>
                      </tr>
                  </c:if>
                      <tr>
                          <td id="tdLabel">e) Bayaran Ukur</td>
                          <td id="tdLabel"><b> : </b></td>
                          <td><s:radio name="hakmilikPermohonan.agensiUpahUkur" id="bayaranUkur" value="JUP"/> Mengikut P.U (A) 438
                              <s:radio name="hakmilikPermohonan.agensiUpahUkur" id="bayaranUkur" value="JUB"/> Juru Ukur Lesen
                          </td>
                      </tr>
                    <tr>
                        <td id="tdLabel" colspan="3"><b>(i) Suratan-suratan Hakmilik yang dikehendaki untuk dikeluarkan :</b></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; <s:radio name="kodHakmilik" value="GRN"/>
                                (a) Geran (Borang 5B) bagi</b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.perincianBorang5b" size="30"/></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; <s:radio name="kodHakmilik" value="LN"/>
                                (b) Lease Negeri (Borang 5C) bagi</b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.perincianBorang5c" size="30"/></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; <s:radio name="kodHakmilik" value="GM"/>
                                (c) Geran Mukim (Borang 5D) bagi</b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.perincianBorang5d" size="30"/></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; <s:radio name="kodHakmilik" value="PM"/>
                                (d) Pajakan Mukim (Borang 5E) bagi</b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.perincianBorang5e" size="30"/></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; <s:radio name="kodHakmilik" value="PL"/>
                                (e) Pajakan Lombong didahului oleh <br>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Perakuan Lombong bagi</b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.perincianPajakanLombong" size="30"/></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; <s:radio name="kodHakmilik" value="HS"/>
                                (f) Hakmilik Strata bagi</b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.perincianStrata" size="30"/></td>
                    </tr><br>
                    <%--
                    <tr>
                        <td id="tdLabel" ><b>Suratan-suratan Hakmilik </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td id="tdLabel">  <s:radio name="permohonanUkur.statusSuratanHakmilik" value="A"/>Akan dimansuhkan
                            &nbsp;&nbsp; <s:radio name="permohonanUkur.statusSuratanHakmilik" value="T"/>Telah dimansuhkan</td>
                    </tr>--%>
                    <tr>
                        <td id="tdLabel"><b>
                                (ii) Pelan Deposit bagi</b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.tujuan" size="30"/></td>
                    </tr>
                    <tr><td><u><b>Bayaran-bayaran Ukur</b></u></td></tr>
                    
                <tr>
                    <td><s:radio name="cara" id="cara" value="P" onclick="show();"/>Dikecuali penuh</td>
                    <td><s:radio name="cara" id="cara2" value="S" onclick="show2();"/>Dikecuali sebahagian</td>
                    <td><s:radio name="cara" id="cara3" value="T" onclick="hide();"/>Tidak dikecualikan</td>
                </tr>
                </table>
                <table id="penuh" width="55%" cellspacing="5">
                    <tr>
                        <td>&nbsp;&nbsp;&nbsp; (a) Dikecuali penuh oleh</td>
                        <td> : </td>
                        <td><s:text name="permohonanUkur.pemberiPengecualian" size="30"/></td>
                    </tr>
                    <tr>
                        <td>&nbsp;&nbsp;&nbsp; Dibawah Perenggan Kanun Tanah Negara <br>
                            &nbsp;&nbsp;&nbsp; (Bayaran Ukur) 1965 </td>
                        <td>: </td>
                        <td><s:text name="permohonanUkur.perengganKTN" size="30"/></td>
                    </tr>
                    <tr>
                       
                        <td>&nbsp;&nbsp;&nbsp; Rujukan </td>
                        <td> : </td>
                        <td><s:text name="permohonanUkur.rujukanKTN" size="30"/></td>
                        
                    </tr>
                    </table>
                    
                        <table id="sebahagian" width="55%" cellspacing="5">
                    <tr>
                        <td>&nbsp;&nbsp;&nbsp; (b) Dikecualikan setakat RM </td>
                        <td> : </td>
                        <td><s:text name="permohonanUkur.jumlahPengecualian"  formatPattern="#,#00.00" onkeyup="validateNumber(this,this.value);" size="15"/>
                        &nbsp; oleh <s:text name="permohonanUkur.pemberiPengecualian" size="15"/>
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;&nbsp;&nbsp; Dibawah Perenggan Kanun Tanah Negara <br>
                            &nbsp;&nbsp;&nbsp; (Bayaran Ukur) 1965 </td>
                        <td> : </td>
                        <td><s:text name="permohonanUkur.perengganKTN" size="30"/></td>
                    </tr>
                    <tr>
                        <td>&nbsp;&nbsp;&nbsp; Rujukan </td>
                        <td> : </td>
                        <td>   <s:text name="permohonanUkur.rujukanKTN" size="30"/></td>
                    </tr>
                    </table>
                    <table width="55%" cellspacing="5">
                    <tr>
                        <td>&nbsp;&nbsp;&nbsp; (c) Bayaran Ukur RM </td>
                        <td> : </td>
                        <td><s:format value="${actionBean.amaun}" formatPattern="#,#00.00"/><%--<s:text name="permohonanUkur.jumlahBayaranUkur" size="30" formatPattern="#,#00.00" onkeyup="validateNumber(this,this.value);"/>--%></td>
                    </tr>
                    <tr>
                        <td>&nbsp; &nbsp; No Resit </td>
                        <td> : </td>
                        <td>${actionBean.noResit}<%--<s:text name="permohonanUkur.noResit" size="30"/>--%></td>
                    </tr>
                    <tr>
                        <td>&nbsp; &nbsp; Tarikh Resit </td>
                        <td> : </td>
                        <td><s:format value="${actionBean.tarikhResit}" formatPattern="dd/MM/yyyy"/><%--<s:text name="permohonanUkur.tarikhResit" class="datepicker" formatPattern="dd/MM/yyyy" size="30"/>--%></td>
                    </tr>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
                    <tr>
                        <td>&nbsp; &nbsp; Dokumen-dokumen Hakmilik Sementara </td>
                        <td> : </td>
                        <td><s:radio id="test" name="statusSHS" value="T"/>Telah Dikeluarkan
                            <s:radio id="test" name="statusSHS" value="B"/>Belum Dikeluarkan</td>
                    </tr>
                </table> <br>
                      </c:if>
                <c:if test="${actionBean.edit4}">
                <div class="content" align="center">
                    <s:button name="simpan" id="save" value="Simpan & Jana No PU" class="longbtn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    <s:button name="transferFile" id="btnClick" value="Hantar PU DMS" class="longbtn" onclick="doSubmit(this.form, this.name, 'page_div')" />
                    <s:button name="lakarPelan" id="lakarPelan" value="Penyediaan PU GIS" class="longbtn"  onclick="RunProgram('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                </div>
                 </c:if>
                
                  <c:if test="${actionBean.edit7}">
                <div class="content" align="center">
                    <s:button name="simpan" id="save" value="Simpan & Jana No PU" class="longbtn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </div>
                 </c:if>
           
                <c:if test="${actionBean.edit3}">
                     <s:button name="semakTerima" id="semakTerima" value="Semak Terima PA/B1" class="longbtn"  onclick="RunProgram3('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                </c:if>
             <c:if test="${actionBean.view}">
                <table border="0" width="55%" cellspacing="10">
                    <%--<tr>
                        <td id="tdLabel" width="45%"><b>Rujukan Pejabat Tanah </b></td>
                         <td id="tdLabel"><b> : </b></td>
                        <td>${actionBean.permohonanUkur.noRujukanPejabatTanah} </td>
                        <td>${actionBean.permohonan.idPermohonan} </td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>Rujukan Pejabat Ukur </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td>${actionBean.permohonanUkur.noRujukanPejabatUkur}</td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>Negeri  </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><font color="black">${actionBean.kodNegeri}&nbsp;</font></td>
                    </tr>
                    --%><tr>
                        <td><b>No Permohonan Ukur  </b></td>
                        <td> : </td>
                        <td>${actionBean.permohonanUkur.noPermohonanUkur} &nbsp;</td>
                    </tr>
                     <tr>
                          <td>Bayaran Ukur</td>
                          <td><b> : </b></td>
                          <td><c:if test="${actionBean.hakmilikPermohonan.agensiUpahUkur eq 'JUP'}">
                                  Mengikut P.U (A) 438
                              </c:if>
                              <c:if test="${actionBean.hakmilikPermohonan.agensiUpahUkur eq 'JUB'}">
                                  Juru Ukur Lesen
                              </c:if>
                          </td>
                      </tr>
                    <tr>
                        <td id="tdLabel" colspan="3"><b>Suratan-suratan Hakmilik yang dikehendaki untuk dikeluarkan :</b></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (a) Geran (Borang 5B) bagi</b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td>${actionBean.permohonanUkur.perincianBorang5b}</td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (b) Lease Negeri (Borang 5C) bagi</b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td>${actionBean.permohonanUkur.perincianBorang5c}</td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (c) Geran Mukim (Borang 5D) bagi</b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td>${actionBean.permohonanUkur.perincianBorang5d} </td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (d) Pajakan Mukim (Borang 5E) bagi</b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td>${actionBean.permohonanUkur.perincianBorang5e} </td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (e) Pajakan Lombong didahului oleh <br>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Perakuan Lombong bagi</b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td>${actionBean.permohonanUkur.perincianPajakanLombong} </td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (f) Hakmilik Strata bagi</b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td>${actionBean.permohonanUkur.perincianStrata} </td>
                    </tr><br>
                    <%--
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
                          </td>
                    </tr>--%>
                    <tr>
                        <td>Status Bayaran</td>
                        <td>:</td>
                        <td>
                            <c:if test="${actionBean.permohonanUkur.statusBayaranUkur eq 'P'}">
                                Dikecuali penuh
                            </c:if>
                            <c:if test="${actionBean.permohonanUkur.statusBayaranUkur eq 'S'}">
                                Dikecuali sebahagian
                            </c:if>
                             <c:if test="${actionBean.permohonanUkur.statusBayaranUkur eq 'T'}">
                                Tiada Pengecualian
                            </c:if>
                        </td>
                    </tr>
                    <tr><td><b><u>Bayaran-bayaran Ukur</u></b></td></tr>
                </table>
                    <table id="penuh" border="0" width="55%" cellspacing="10">
                    <tr>
                        <td>&nbsp;&nbsp;&nbsp; (a) Dikecuali penuh oleh </td>
                        <td> : </td>
                        <td>${actionBean.permohonanUkur.pemberiPengecualian} </td>
                    </tr>
                    <tr>
                        <td>&nbsp;&nbsp;&nbsp; Dibawah Perenggan Kanun Tanah Negara <br>
                            &nbsp;&nbsp;&nbsp; (Bayaran Ukur) 1965 </td>
                        <td> : </td>
                        <td>${actionBean.permohonanUkur.perengganKTN} </td>
                    </tr>
                    <tr>
                        <td>&nbsp;&nbsp;&nbsp; Rujukan </td>
                        <td> : </td>
                        <td>${actionBean.permohonanUkur.rujukanKTN} </td>
                    </tr>
                    </table>
                    <table id="sebahagian" border="0" width="55%" cellspacing="10">
                    <tr>
                        <td>&nbsp;&nbsp;&nbsp; (b) Dikecualikan sekatan RM </td>
                        <td> : </td>
                        <td> ${actionBean.permohonanUkur.jumlahPengecualian}
                        &nbsp; oleh &nbsp;  ${actionBean.permohonanUkur.pemberiPengecualian}
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;&nbsp;&nbsp; Dibawah Perenggan Kanun Tanah Negara <br>
                            &nbsp;&nbsp;&nbsp; (Bayaran Ukur) 1965 </td>
                        <td> : </td>
                        <td>${actionBean.permohonanUkur.perengganKTN} </td>
                    </tr>
                    <tr>
                        <td>&nbsp;&nbsp;&nbsp; Rujukan</td>
                        <td> : </td>
                        <td> ${actionBean.permohonanUkur.rujukanKTN}  </td>
                    </tr>
                    </table>
                    <table border="0" width="55%" cellspacing="10"> 
                    <tr>
                        <td>&nbsp;&nbsp;&nbsp; (c) Bayaran Ukur RM </td>
                        <td> : </td>
                        <td>${actionBean.permohonanUkur.jumlahBayaranUkur}</td>
                    </tr>
                    <tr>
                        <td>&nbsp; &nbsp; No Resit </td>
                        <td> : </td>
                        <td>${actionBean.permohonanUkur.noResit}</td>
                    </tr>
                    <tr>
                        <td>&nbsp; &nbsp; Tarikh Resit </td>
                        <td> : </td>
                        <td><fmt:formatDate value="${actionBean.permohonanUkur.tarikhResit}" pattern="dd/MM/yyyy"  />  </td>
                    </tr>

                    <tr>
                        <td>&nbsp; &nbsp; Dokumen-dokumen Hakmilik Sementara </td>
                        <td> : </td>
                        <td>
                            <c:if test="${actionBean.permohonanUkur.statusSuratanHakmilikSementara  eq 'T'}">
                                Telah Dikeluarkan
                            </c:if>
                            <c:if test="${actionBean.permohonanUkur.statusSuratanHakmilikSementara  eq 'B'}">
                                Belum Dikeluarkan
                            </c:if>
                         </td>
                    </tr>
                    </table>
                    
                    <%--<div class="content" align="center">
                             <s:button name="transferFile" id="btnClick" value="Hantar PU DMS" class="longbtn" onclick="doSubmit(this.form, this.name, 'page_div')" />
                         </div>--%>
              </c:if>
                    <%-- <c:if test="${actionBean.hantarDMS}">
                         <div class="content" align="center">
                             <s:button name="transferFile" id="btnClick" value="Hantar PU DMS" class="longbtn" onclick="doSubmit(this.form, this.name, 'page_div')" />
                         </div>
                     </c:if>--%>

            <c:if test="${actionBean.edit2}">
                <div class="content" align="center">
                    <s:button name="lakarPelan" id="lakarPelan" value="Hantar PU Jupem" class="longbtn"  onclick="RunProgram2('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                </div>
             </c:if>
             <c:if test="${actionBean.edit6}">
                <div class="content" align="center">
                    <s:button name="semakPelan" id="semakPelan" value="Semak PU" class="longbtn"  onclick="RunProgram4('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                </div>
             </c:if>
            </div>
        </fieldset>
    </div>
</s:form>

