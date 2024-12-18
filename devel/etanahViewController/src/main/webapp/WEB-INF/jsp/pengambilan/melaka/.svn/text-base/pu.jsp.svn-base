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

     <%--$(document).ready( function() {
         alert("ready");
           $('#buttontandatangan').hide();
      });--%>

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
        var stageId = "g_penyediaan_pu";
        alert("nama:" + strNama);
        alert ("jawatan:" + strJawatan);
        alert ("stageid:" + stageId);
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + stageId);
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


  // testing
   function refreshRizab(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }


   function validation(event, f){

        var rujukan = $("#rujukan").val();
        if(rujukan == ""){
            alert("Silah Masukan No Rujukan Pejabat Ukur");
            $("#rujukan").focus();
            return false;
        }else{            
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',this.document).html(data);
            },'html');
             return true;
        }
  }



   <%-- function test() {
    if($('#namapguna').val() != ""){
       $('#buttontandatangan').show();
    }--%>


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

<s:form beanclass="etanah.view.stripes.pengambilan.PuActionBean">
    <s:hidden name="edit1" id="edit1" />
    <s:hidden name="edit2" id="edit2" />
    <s:hidden name="view" id="view" />

    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="center">
              <%--<c:if test="${actionBean.noPTSementara eq 0}">--%>  
                <table border="0" width="20%" align="center">
                    <tr>
                        <td><b><font color="#003194">MAKLUMAT PERMOHONAN UKUR </font></b></td>
                    </tr>
                </table>
                <br><br>
                <c:if test="${actionBean.edit1}">
                   <div align="left">

                 <p><label>Ditandatangan Oleh :</label></p>
                 <s:select name="ditundatangan" id="namapguna">
                           <s:option label="Sila Pilih" value="" />                          
                           <c:forEach items="${actionBean.penggunaList}" var="i" >                              
                               <s:option value="${i.idPengguna}" >${i.jawatan} - ${i.nama}</s:option>
                           </c:forEach>
                       </s:select>
                </div><br/>
                <div id="buttontandatangan" align="center">
                <s:button name="simpanTandatangan" class="longbtn" value="Simpan Tandatangan" onclick="doSubmit(this.form,this.name,'page_div')"/>
                 </div>

                <table border="0" width="55%" cellspacing="10">
                    <tr>
                        <td id="tdLabel" width="45%"><b>Rujukan Pejabat Tanah </b></td>
                         <td id="tdLabel"><b> : </b></td>
                         <td>${actionBean.idPermohonan}
                             <%--<s:text name="idPermohonan" size="30" readonly="readonly"/>--%>
                             <%--<s:text name="permohonanUkur.noRujukanPejabatTanah" size="30" readonly="readonly"/>--%>
                        <s:hidden name="permohonanUkur.idMohonUkur"/>
                        </td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>Rujukan Pejabat Ukur </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.noRujukanPejabatUkur" size="30" id="rujukan" class="normal_text"/></td>
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
                        <td id="tdLabel"><b>Tarikh Permohonan Ukur  </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><font color="black"><fmt:formatDate value="${actionBean.permohonanUkur.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy"  />  &nbsp;</font></td>
                    </tr>
                    <tr>
                        <td id="tdLabel" colspan="3"><b>Suratan-suratan Hakmilik yang dikehendaki untuk dikeluarkan :</b></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; <s:radio name="kodHakmilik" value="GRN"/>
                                (a) Geran (Borang 5B) bagi</b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.perincianBorang5b" size="30" class="normal_text"/></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; <s:radio name="kodHakmilik" value="PN" />
                                (b) Pajakan Negeri (Borang 5C) bagi</b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.perincianBorang5c" size="30" class="normal_text"/></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; <s:radio name="kodHakmilik" value="GM"/>
                                (c) Geran Mukim (Borang 5D) bagi</b></td>
                        <td id="tdLabel"><b> : </b></td>
                        
                        <c:if test="${actionBean.kodHakmilik eq 'GM' || actionBean.kodHakmilik eq null}">
                        <td><s:text name="permohonanUkur.perincianBorang5d" size="30" class="normal_text"/></td>
                        </c:if>
                        
                    </tr>
                    <c:if test="${actionBean.kodNegeri eq 'Melaka'}">
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; <s:radio name="kodHakmilik" value="GMM"/>
                                &nbsp; &nbsp;&nbsp;&nbsp; Geran Mukim MCL (Borang 5D) bagi</b></td>
                        <td id="tdLabel"><b> : </b></td>
                        
                         <c:if test="${actionBean.kodHakmilik eq 'GMM'|| actionBean.kodHakmilik eq null}">
                        <td><s:text name="permohonanUkur.perincianBorang5d" size="30" class="normal_text"/></td>
                        </c:if>
                        <%--<td><s:text name="permohonanUkur.lot" size="30" class="normal_text"/></td> --%>
                    </tr>
                    </c:if>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; <s:radio name="kodHakmilik" value="PM"/>
                                (d) Pajakan Mukim (Borang 5E) bagi</b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.perincianBorang5e" size="30" class="normal_text"/></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; <s:radio name="kodHakmilik" value="PL"/>
                                (e) Pajakan Lombong didahului oleh <br>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Perakuan Lombong bagi</b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.perincianPajakanLombong" size="30" class="normal_text"/></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; <s:radio name="kodHakmilik" value="HS"/>
                                (f) Hakmilik Strata bagi</b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.perincianStrata" size="30" class="normal_text"/></td>
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
                        <td><s:text name="permohonanUkur.pemberiPengecualian" size="30" class="normal_text"/></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; Dibawah Perenggan Kanun Tanah Negara <br>
                             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   (Bayaran Ukur) 1965 </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.perengganKTN" size="30" class="normal_text"/></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; Rujukan </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.rujukanKTN" size="30" class="normal_text"/></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (b) Dikecualikan setakat RM </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td id="tdLabel">   <s:text name="permohonanUkur.jumlahPengecualian"  formatPattern="#,#.00" onkeyup="validateNumber(this,this.value);" size="15"/>
                        &nbsp; <b> oleh </b><s:text name="permohonanUkur.pemberiPengecualian" size="15"/>
                        </td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; Dibawah Perenggan Kanun Tanah Negara <br>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; (Bayaran Ukur) 1965 </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.perengganKTN" size="30" class="normal_text"/></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; Rujukan </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td>   <s:text name="permohonanUkur.rujukanKTN" size="30" class="normal_text"/></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (c) Bayaran Ukur RM </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.jumlahBayaranUkur" size="30" formatPattern="#,#00.00" onkeyup="validateNumber(this,this.value);"/></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp; &nbsp; No Resit </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.noResit" size="30" class="normal_text"/></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp; &nbsp; Tarikh Resit </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.tarikhResit" class="datepicker" formatPattern="dd/MM/yyyy" size="30"/></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp; &nbsp; Catatan </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><s:text name="permohonanUkur.tujuan" class="normal_text" size="30"/></td>
                    </tr>

<%--                    <tr>
                        <td id="tdLabel"><b>Dokumen-dokumen Hakmilik Sementara </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td id="tdLabel"><s:radio name="permohonanUkur.statusSuratanHakmilikSementara" value="T"/>Telah Dikeluarkan
                            <s:radio name="permohonanUkur.statusSuratanHakmilikSementara" value="B"/>Belum Dikeluarkan</td>
                    </tr>--%>
                </table>
                    <br>
                <div class="content" align="center">
                    <s:button name="simpan" id="save" value="Simpan & Jana No PU" class="longbtn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    <%--<s:button name="simpan" id="save" value="Simpan & Jana No PU" class="longbtn" onclick="validation(this.name, this.form);"/>--%>
                    <s:button name="transferFile" id="btnClick" value="Hantar PU DMS" class="longbtn" onclick="doSubmit(this.form, this.name, 'page_div')" />
                    <s:button name="lakarPelan" id="lakarPelan" value="Penyediaan PU GIS" class="longbtn"  onclick="RunProgram('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                </div>
             </c:if>
             <c:if test="${actionBean.view}">
                <table border="0" width="55%" cellspacing="10">
                    <tr>
                        <td id="tdLabel" width="45%"><b>Rujukan Pejabat Tanah </b></td>
                         <td id="tdLabel"><b> : </b></td>
                        <%--<td>${actionBean.permohonanUkur.noRujukanPejabatTanah} </td>--%>
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
                    <tr>
                        <td id="tdLabel"><b>No Permohonan Ukur  </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><font color="black">${actionBean.permohonanUkur.noPermohonanUkur} &nbsp;</font></td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>Tarikh Permohonan Ukur  </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><font color="black"><fmt:formatDate value="${actionBean.permohonanUkur.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy"  />   &nbsp;</font></td>
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
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (b) Pajakan Negeri (Borang 5C) bagi</b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td>${actionBean.permohonanUkur.perincianBorang5c}</td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (c) Geran Mukim (Borang 5D) bagi</b></td>
                        <td id="tdLabel"><b> : </b></td>
                        
                        <c:if test="${actionBean.kodHakmilik eq 'GM'}">
                        <td>${actionBean.permohonanUkur.perincianBorang5d} </td>
                        </c:if>
                    </tr>
                    
                    <c:if test="${actionBean.kodNegeri eq 'Melaka'}">
                    <tr>
                        <td id="tdLabel"><b>&nbsp; &nbsp;&nbsp;&nbsp; Geran Mukim MCL (Borang 5D) bagi</b></td>                              
                        <td id="tdLabel"><b> : </b></td>
                        
                        <c:if test="${actionBean.kodHakmilik eq 'GMM'}">
                        <td>${actionBean.permohonanUkur.perincianBorang5d}</td>
                        </c:if>
                    </tr>
                    </c:if>
                    
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
                    </tr>
                    <tr><td id="tdLabel" colspan="3"><b>Bayaran-bayaran Ukur :</b></td></tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (a) Dikecuali penuh oleh </b> </td>
                        <td id="tdLabel"><b> : </b></td>
                        <td>${actionBean.permohonanUkur.pemberiPengecualian} </td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; Dibawah Perenggan Kanun Tanah Negara <br>
                             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   (Bayaran Ukur) 1965 </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td>${actionBean.permohonanUkur.perengganKTN} </td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; Rujukan </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td>${actionBean.permohonanUkur.rujukanKTN} </td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (b) Dikecualikan setakat RM </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td> ${actionBean.permohonanUkur.jumlahPengecualian}
                        &nbsp; oleh &nbsp;  ${actionBean.permohonanUkur.pemberiPengecualian}
                        </td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; Dibawah Perenggan Kanun Tanah Negara <br>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; (Bayaran Ukur) 1965 </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td>${actionBean.permohonanUkur.perengganKTN} </td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; Rujukan </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td> ${actionBean.permohonanUkur.rujukanKTN}  </td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (c) Bayaran Ukur RM </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td>${actionBean.permohonanUkur.jumlahBayaranUkur}</td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp; &nbsp; No Resit </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td>${actionBean.permohonanUkur.noResit}</td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><b>&nbsp; &nbsp; Tarikh Resit </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td><fmt:formatDate value="${actionBean.permohonanUkur.tarikhResit}" pattern="dd/MM/yyyy"  />  </td>
                    </tr>

                   <%-- <tr>
                        <td id="tdLabel"><b>Dokumen-dokumen Hakmilik Sementara </b></td>
                        <td id="tdLabel"><b> : </b></td>
                        <td>
                            <c:if test="${actionBean.permohonanUkur.statusSuratanHakmilikSementara  eq 'T'}">
                                Telah Dikeluarkan
                            </c:if>
                            <c:if test="${actionBean.permohonanUkur.statusSuratanHakmilikSementara  eq 'B'}">
                                Belum Dikeluarkan
                            </c:if>
                         </td>
                    </tr>--%>
                </table>                
              </c:if>

            <c:if test="${actionBean.edit2}">
                <div class="content" align="center">
                    <s:button name="lakarPelan" id="lakarPelan" value="Hantar PU ke Jupem" class="longbtn"  onclick="RunProgram2('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                </div>
             </c:if>
         <%--</c:if>--%>
        <c:if test="${actionBean.noPTSementara eq 1000}">
            Permohonan Ukur dibuat oleh "Juruukur Berlesen"
        </c:if>           
                    
            </div>
        </fieldset>
    </div>
</s:form>

<%--<div align="center">
    <c:if test="${actionBean.edit}">
     <s:form beanclass="etanah.view.stripes.pembangunan.AgihanTugasActionBean">
        <s:button name="showForm" id="showForm" value="Agian Tugasan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
   </s:form>
    </c:if>

   <s:form beanclass="etanah.view.stripes.pembangunan.NoPUNoPTActionBean">
        <s:button name="frmPT" id="frmPT" value="Papar No PT" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
   </s:form>
</div>--%>