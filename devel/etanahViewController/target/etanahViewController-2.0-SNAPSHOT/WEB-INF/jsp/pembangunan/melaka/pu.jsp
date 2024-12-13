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
        stageId = strIDStage;       
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + stageId);
    }

    function RunProgram2(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {

        alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
        strNama = ReplaceAll(strNama," ","_");
        strJawatan = ReplaceAll(strJawatan," ","_");
        stageId = strIDStage;       
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
</script>

<s:form beanclass="etanah.view.stripes.pembangunan.PuActionBean">
    <s:hidden name="edit1" id="edit1" />
    <s:hidden name="edit2" id="edit2" />
    <s:hidden name="view" id="view" />

    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="center">
                <c:if test="${actionBean.noPTSementara eq 0}">  
                    <table border="0" width="20%" align="center">
                        <tr>
                            <td><b><font color="#003194">MAKLUMAT PERMOHONAN UKUR</font></b></td>
                        </tr>
                    </table>
                    <br><br>

                    <div align="left">
                        <c:if test="${actionBean.stageId eq 'g_penyediaan_pu_pt' || actionBean.stageId eq 'g_semak_pu' || actionBean.stageId eq 'g_penyediaan_pu'}">
                            <p><label>Ditandatangan Oleh :</label></p>
                            <s:select name="ditundatangan" id="namapguna">
                                <s:option label="Sila Pilih" value="" />                          
                                <c:forEach items="${actionBean.penggunaList}" var="i" >      
                                    <s:option value="${i.idPengguna}" >${i.jawatan} - ${i.nama}</s:option>
                                </c:forEach>
                            </s:select>                
                        </div>
                        <br></br>
                        <div id="buttontandatangan" align="center">
                            <s:button name="simpanTandatangan" class="longbtn" value="Simpan Tandatangan" onclick="doSubmit(this.form,this.name,'page_div')"/>
                        </c:if>
                    </div>

                    <c:if test="${actionBean.edit1}">
                        <table border="0" width="55%" cellspacing="10">
                            <tr>
                                <td id="tdLabel" width="45%"><b>Rujukan Pejabat Tanah </b></td>
                                <td id="tdLabel"><b> : </b></td>
                                <td>${actionBean.idPermohonan}                                   
                                    <s:hidden name="permohonanUkur.idMohonUkur"/>
                                </td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><b>Rujukan Pejabat Ukur </b></td>
                                <td id="tdLabel"><b> : </b></td>
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
                        </table>
                        <br><br>
                        <table border="0" width="70%" cellspacing="10">
                            <tr>
                                <td id="tdLabel"><b>1)</b></td>
                                <td id="tdLabel" colspan="3"><b>Sila ukur tanah yang bertanda merah dalam pelan *di sebelah / berkembar untuk :</b></td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (a)</b></td>
                                <td id="tdLabel" colspan="3"><b><s:radio name="permohonanUkur.sebabUkur" value="a"/>Pemberimilikan Tanah Kerajaan / Pajakan Lombong</b></td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (b)</b></td>
                                <td id="tdLabel" colspan="3"><b><s:radio name="permohonanUkur.sebabUkur" value="b"/>Pecah Sempadan / Pecah Bahagian Tanah / Pecah Bahagian Bangunan</b></td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (c)</b></td>
                                <td id="tdLabel" colspan="3"><b><s:radio name="permohonanUkur.sebabUkur" value="c"/>Penyatuan Tanah <s:text name="permohonanUkur.camtumanLot" size="50" class="normal_text"/> *dan terus dipecah <s:text name="permohonanUkur.camtumanLotDipecah" size="50" class="normal_text"/></b></td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (d)</b></td>
                                <td id="tdLabel" colspan="3"><b><s:radio name="permohonanUkur.sebabUkur" value="d"/>Penyerahan sebahagian daripada lot-lot  <s:text name="permohonanUkur.serahSebahagianLot" size="30" class="normal_text"/></b></td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (e)</b></td>
                                <td id="tdLabel" colspan="3"><b><s:radio name="permohonanUkur.sebabUkur" value="e"/>Penyerahan lot-lot <s:text name="permohonanUkur.bermilikTanahKerajaan" size="30" class="normal_text"/> dan diberimilik semula bahagian - bahagian <s:text name="permohonanUkur.berimilikSemulaBahagian" size="30" class="normal_text"/></b></td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (f)</b></td>
                                <td id="tdLabel" colspan="3"><b><s:radio name="permohonanUkur.sebabUkur" value="f"/>Pengambilan sebahagian dari lot-lot <s:text name="permohonanUkur.ambilSebahagianLot" size="30" class="normal_text"/></b></td>
                            </tr>
                            <%--COMMIT SEMENTARA--%>    
                            <c:if test="${actionBean.kodNegeri eq 'Negeri Sembilan'}">
                                <tr>
                                    <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (g)</b></td>
                                    <td id="tdLabel" colspan="3"><b><s:radio name="permohonanUkur.sebabUkur" value="g"/>Ukuran semula lot-lot <s:text name="permohonanUkur.UkurSemulaLot" size="30" class="normal_text"/></b></td>
                                </tr>
                                <tr>
                                    <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (h)</b></td>
                                    <td id="tdLabel" colspan="3"><b><s:radio name="permohonanUkur.sebabUkur" value="h"/>*Mengganti tanda sempadan yang hilang / membetulkan tempat tanda-tanda sempadan</b></td>
                                </tr>
                            </c:if>
                            <c:if test="${actionBean.kodNegeri eq 'Melaka'}">                               
                                <tr>
                                    <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (g)</b></td>
                                    <td id="tdLabel" colspan="3"><b><s:radio name="permohonanUkur.sebabUkur" value="g"/>*Mengganti tanda sempadan yang hilang / membetulkan tempat tanda-tanda sempadan</b></td>
                                </tr>
                            </c:if>
                        </table>
                        <br><br>
                        <table border="0" width="70%" cellspacing="10">
                            <tr>
                                <td id="tdLabel"><b>2)</b></td>
                                <td id="tdLabel" colspan="4"><b>Surat-surat hakmilik yang dikehendaki untuk dikeluarkan ialah :</b></td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (a)</b></td>
                                <td id="tdLabel"><b><s:radio name="kodHakmilik" value="GRN"/>Geran (Borang 5B) bagi</b></td>
                                <td><b> : </b></td>
                                <td><s:text name="permohonanUkur.perincianBorang5b" size="30" class="normal_text"/></td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (b)</b></td>
                                <td id="tdLabel"><b><s:radio name="kodHakmilik" value="PN" />Pajakan Negeri (Borang 5C) bagi</b></td>
                                <td><b> : </b></td>
                                <td><s:text name="permohonanUkur.perincianBorang5c" size="30" class="normal_text"/></td>
                            </tr>
                            <c:if test="${actionBean.kodNegeri eq 'Negeri Sembilan'}">
                                <tr>
                                    <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (c)</b></td>
                                    <td id="tdLabel"><b><s:radio name="kodHakmilik" value="GM"/>Geran Mukim (Borang 5D) bagi</b></td>
                                    <td><b> : </b></td>
                                    <td><s:text name="permohonanUkur.perincianBorang5d" size="30" class="normal_text"/></td>
                                </tr>
                            </c:if>
                            <c:if test="${actionBean.kodNegeri eq 'Melaka'}">
                                <tr>
                                    <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (c)</b></td>
                                    <td id="tdLabel"><b><s:radio name="kodHakmilik" value="GMM"/>Geran Mukim MCL (Borang 5D) bagi</b></td>
                                    <td><b> : </b></td>
                                    <td><s:text name="permohonanUkur.perincianBorang5d" size="30" class="normal_text"/></td>
                                </tr>
                            </c:if>
                            <tr>
                                <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (d)</b></td>
                                <td id="tdLabel"><b><s:radio name="kodHakmilik" value="PM"/>Pajakan Mukim (Borang 5E) bagi</b></td>
                                <td><b> : </b></td>
                                <td><s:text name="permohonanUkur.perincianBorang5e" size="30" class="normal_text"/></td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (e)</b></td>
                                <td id="tdLabel"><b><s:radio name="kodHakmilik" value="PL"/>Pajakan Lombong didahului oleh Perakuan Lombong bagi</b></td>
                                <td><b> : </b></td>
                                <td><s:text name="permohonanUkur.perincianPajakanLombong" size="30" class="normal_text"/></td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (f)</b></td>
                                <td id="tdLabel"><b><s:radio name="kodHakmilik" value="HS"/>Hakmilik Strata bagi</b></td>
                                <td><b> : </b></td>
                                <td><s:text name="permohonanUkur.perincianStrata" size="30" class="normal_text"/></td>
                            </tr>
                        </table>
                        <br><br>
                        <table border="0" width="70%" cellspacing="10"> 
                            <tr>
                                <td id="tdLabel"><b>3) Suratan-suratan Hakmilik bagi lot-lot <s:text name="permohonanUkur.lot" size="30" class="normal_text"/> </b></td>
                                <td id="tdLabel">  <s:radio name="permohonanUkur.statusSuratanHakmilik" value="A"/>Akan dimansuhkan
                                    &nbsp;&nbsp; <s:radio name="permohonanUkur.statusSuratanHakmilik" value="T"/>Telah dimansuhkan</td>
                            </tr>
                        </table>
                        <br><br>
                        <table border="0" width="70%" cellspacing="10"> 
                            <tr><td id="tdLabel" colspan="2"><b>4) Bayaran-bayaran ukur :</b></td></tr>
                            <tr>
                                <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (a)</b></td>
                                <td id="tdLabel"><b><s:radio name="permohonanUkur.statusBayaranUkur" value="a"/> Dikecualikan penuh oleh <s:text name="permohonanUkur.pemberiPengecualian" size="30" class="normal_text"/> 
                                        di bawan perenggan <s:text name="permohonanUkur.perengganKTN" size="30" class="normal_text"/>
                                        Perintah Kanun Tanah Negara (Bayaran Ukur) 1965 (Rujukan <s:text name="permohonanUkur.rujukanKTN" size="30" class="normal_text"/>)
                                    </b>
                                </td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (b)</b></td>
                                <td id="tdLabel"><b><s:radio name="permohonanUkur.statusBayaranUkur" value="b"/>  Dikecualikan setakat RM <s:text name="permohonanUkur.jumlahPengecualian"  formatPattern="#,#.00" onkeyup="validateNumber(this,this.value);" size="15"/> 
                                        oleh <s:text name="permohonanUkur.pengencualian" size="15"/>
                                        dibawah perenggan <s:text name="permohonanUkur.perengganKtn" size="30" class="normal_text"/> Perintah Kanun Tanah Negara (Bayaran Ukur) 1965 (Rujukan <s:text name="permohonanUkur.rujukanKtn" size="30" class="normal_text"/>)
                                    </b>
                                </td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (c)</b></td>
                                <td id="tdLabel"><b><s:radio name="permohonanUkur.statusBayaranUkur" value="c"/>  Bayaran ukur sebanyak RM <s:text name="permohonanUkur.jumlahBayaranUkur" size="30" formatPattern="#,#.00" onkeyup="validateNumber(this,this.value);"/>
                                        telah di bayar (No Resit <s:text name="permohonanUkur.noResit" size="30" class="normal_text"/> bertarikh <s:text name="permohonanUkur.tarikhResit" class="datepicker" formatPattern="dd/MM/yyyy" size="30"/>)
                                        dan telah dimansuhkan kepada Hasil Persekutuan.
                                    </b>
                                </td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><b>&nbsp;&nbsp;&nbsp; (d)</b></td>
                                <td id="tdLabel"><b><s:radio name="permohonanUkur.statusBayaranUkur" value="d"/>  Di ukur oleh <s:text name="permohonanUkur.pengecualianOlehLihat" size="30" /> (No Resit <s:text name="permohonanUkur.noResitD" size="30" class="normal_text"/> bertarikh <s:text name="permohonanUkur.tarikhResitD" class="datepicker" formatPattern="dd/MM/yyyy" size="30"/>)
                                    </b>
                                </td>
                            </tr>
                        </table>
                        <br><br>
                        <table border="0" width="70%" cellspacing="10"> 
                            <tr><td id="tdLabel"><b>5) Nama dan alamat pemohon :</b></td></tr>

                            <c:set scope="request" value="${actionBean.uniquePemohonList2}" var="senaraiPihak"/>
                            <c:if test="${actionBean.kodNegeri eq 'Melaka'}">
                                <td  id="tdLabel">
                                    <table border="0" width="80%">
                                        <c:set value="1" var="i"/>
                                        <c:forEach items="${senaraiPihak}" var="pihak" end="0">
                                            <tr><td><font size="-1">                                                                
                                                    &nbsp;&nbsp;&nbsp; <c:out value="${pihak.nama}"/> 
                                                    <c:if test="${fn:length(senaraiPihak) > 1}">
                                                        dan ${fn:length(senaraiPihak)-1} yang lain
                                                    </c:if>                                                               
                                                    </font></td>
                                            </tr>
                                            <c:set value="${i + 1}" var="i"/>
                                        </c:forEach>
                                    </table>
                                </td>

                                <tr>
                                    <td  id="tdLabel">
                                        <table border="0" width="80%">
                                            <c:set value="1" var="i"/>
                                            <c:forEach items="${senaraiPihak}" var="pihak" end="0" >
                                                <tr>
                                                    <td>
                                                        <font size="-1">&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${pihak.alamat1}"/>&nbsp;</font>
                                                    </td>
                                                </tr>
                                                <c:if test="${pemohon.pihak.suratAlamat2 ne null}">
                                                    <tr>
                                                        <td>
                                                            <font size="-1">&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${pihak.alamat2}"/>&nbsp;</font>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                                <c:if test="${pemohon.pihak.suratAlamat3 ne null}">
                                                    <tr>
                                                        <td>
                                                            <font size="-1">&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${pihak.alamat3}"/>&nbsp;</font>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                                <c:if test="${pemohon.pihak.suratAlamat4 ne null}">
                                                    <tr>
                                                        <td>
                                                            <font size="-1">&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${pihak.alamat4}"/>&nbsp;</font>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                                <tr>
                                                    <td>
                                                        <font size="-1">&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${pihak.poskod}"/></font>
                                                        <font size="-1"><c:out value="${pihak.negeri.nama}"/>&nbsp;</font>
                                                    </td>
                                                </tr>
                                                <c:set value="${i + 1}" var="i"/>
                                            </c:forEach>
                                        </table>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${actionBean.kodNegeri eq 'Negeri Sembilan'}">                             
                                <tr>
                                    <td colspan="2"><b>&nbsp;&nbsp;&nbsp;&nbsp;${actionBean.pihak.nama}<br>&nbsp;&nbsp;&nbsp;&nbsp;${actionBean.pihak.alamat1}<br>&nbsp;&nbsp;&nbsp;&nbsp;${actionBean.pihak.alamat2}<br>&nbsp;&nbsp;&nbsp;&nbsp;${actionBean.pihak.alamat3} <c:if test="${actionBean.pihak.alamat4 ne null}"><br>&nbsp;&nbsp;&nbsp;&nbsp;${actionBean.pihak.alamat4}</c:if><br>&nbsp;&nbsp;&nbsp;&nbsp;${actionBean.pihak.poskod}<br>&nbsp;&nbsp;&nbsp;&nbsp;${actionBean.pihak.negeri.nama}</b></td>
                                </tr>
                            </c:if>
                        </table>
                        <br>
                        <table border="0" width="70%" cellspacing="10">
                            <c:if test="${actionBean.kodNegeri eq 'Melaka'}"> 
                                <c:set scope="request" value="${actionBean.uniquePemohonList2}" var="senaraiPihak"/>
                                <c:forEach items="${senaraiPihak}" var="pihak" end="0" >
                                    <tr><td id="tdLabel"><b>6) Daerah : ${actionBean.hakmilikMukim.hakmilik.daerah.nama}</b></td>
                                        <td id="tdLabel"><b>Mukim/Bandar/Pekan : ${actionBean.hakmilikMukim.hakmilik.bandarPekanMukim.nama}</b></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <c:if test="${actionBean.kodNegeri eq 'Negeri Sembilan'}"> 
                                <tr>
                                    <td id="tdLabel"><b>6) Daerah : ${actionBean.hakmilikMukim.hakmilik.daerah.nama}</b></td>
                                    <td id="tdLabel"><b>Mukim/Bandar/Pekan : ${actionBean.hakmilikMukim.hakmilik.bandarPekanMukim.nama}</b></td>
                                </tr>
                            </c:if>
                        </table>
                        <br><br>
                        <table border="0" width="70%" cellspacing="10"> 
                            <tr><td id="tdLabel"><b>7) Anggaran Keluasan :</b>
                                    <b>${actionBean.luasKeseluruhan} ${actionBean.kodKeseluruhan}</b>
                                </td>
                            </tr>
                        </table>
                        <br><br>
                        <table border="0" width="70%" cellspacing="10"> 
                            <c:if test="${actionBean.kodNegeri eq 'Melaka'}">
                                <tr><td id="tdLabel"><b>8) Kegunaan Tanah</b> :
                                        <c:forEach items="${actionBean.listMPP}" var="item1" begin="0">   
                                            ${item1.kegunaanTanah.nama}
                                        </c:forEach>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${actionBean.kodNegeri eq 'Negeri Sembilan'}">
                                <tr><td id="tdLabel"><b>8) Kegunaan Tanah</b> :
                                        <c:forEach items="${actionBean.listMPP}" var="item1" begin="0">   
                                            ${item1.kegunaanTanah.nama}
                                        </c:forEach>
                                    </td>
                                </tr>
                            </c:if>
                        </table>
                        <br><br>
                        <table border="0" width="70%" cellspacing="10">
                            <tr>
                                <td id="tdLabel"><b>9) Dokumen-dokumen Hakmilik Sementara : </b>
                                    <s:radio name="permohonanUkur.statusSuratanHakmilikSementara" value="T"/>telah dikeluarkan
                                    <s:radio name="permohonanUkur.statusSuratanHakmilikSementara" value="B"/>belum dikeluarkan</td>
                            </tr>
                        </table>
                        <br>
                        <div class="content" align="center">
                            <s:button name="simpan" id="save" value="Simpan & Jana No PU" class="longbtn" onclick="doSubmit(this.form, this.name, 'page_div')"/>                          
                            <s:button name="lakarPelan" id="lakarPelan" value="Penyediaan PU GIS" class="longbtn"  onclick="RunProgram('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                            <s:button name="transferFile" id="btnClick" value="Hantar PU DMS" class="longbtn" onclick="doSubmit(this.form, this.name, 'page_div')" />
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
                                <td colspan="3"></td>
                            </tr>
                            <tr>
                                <td id="tdLabel" colspan="3"><b>1) Sila ukur tanah yang bertanda merah dalam pelan *di sebelah / berkembar untuk :</b></td>
                            </tr>
                            <c:if test="${actionBean.permohonanUkur.sebabUkur eq 'a'}">
                                <tr>
                                    <td colspan="3">&nbsp;&nbsp;&nbsp; (a) Pemberimilikan Tanah Kerajaan / Pajakan Lombong</td>
                                </tr>
                            </c:if>
                            <c:if test="${actionBean.permohonanUkur.sebabUkur eq 'b'}">
                                <tr>
                                    <td colspan="3">&nbsp;&nbsp;&nbsp; (b)Pecah Sempadan / Pecah Bahagian Tanah / Pecah Bahagian Bangunan</td>
                                </tr>
                            </c:if>
                            <c:if test="${actionBean.permohonanUkur.sebabUkur eq 'c'}">
                                <tr>
                                    <td colspan="3">&nbsp;&nbsp;&nbsp; (c) Penyatuan Tanah <u>${actionBean.permohonanUkur.camtumanLot}</u> *dan terus dipecah <u>${actionBean.permohonanUkur.camtumanLotDipecah}</u></td>
                                </tr>
                            </c:if>
                            <c:if test="${actionBean.permohonanUkur.sebabUkur eq 'd'}">
                                <tr>
                                    <td colspan="3">&nbsp;&nbsp;&nbsp; (d) Penyerahan sebahagian daripada lot-lot <u>${actionBean.permohonanUkur.serahSebahagianLot}</u></td>
                                </tr>
                            </c:if>
                            <c:if test="${actionBean.permohonanUkur.sebabUkur eq 'e'}">
                                <tr>
                                    <td colspan="3">&nbsp;&nbsp;&nbsp; (e) Penyerahan lot-lot <u>${actionBean.permohonanUkur.bermilikTanahKerajaan}</u> dan diberimilik semula bahagian - bahagian <u>${actionBean.permohonanUkur.berimilikSemulaBahagian}</u></td>
                                </tr>
                            </c:if>
                            <c:if test="${actionBean.permohonanUkur.sebabUkur eq 'f'}">
                                <tr>
                                    <td colspan="3">&nbsp;&nbsp;&nbsp; (f) Pengambilan sebahagian dari lot-lot <u>${actionBean.permohonanUkur.ambilSebahagianLot}</u></td>
                                </tr>
                            </c:if>   

                            <%--COMMIT SEMENTARA--%>    
                            <c:if test="${actionBean.kodNegeri eq 'Negeri Sembilan'}">
                                <c:if test="${actionBean.permohonanUkur.sebabUkur eq 'g'}">
                                    <tr>
                                        <td colspan="3">&nbsp;&nbsp;&nbsp; (g) Ukuran semula lot-lot <u>${actionBean.permohonanUkur.UkurSemulaLot}</u></td>
                                    </tr>
                                </c:if>    

                                <c:if test="${actionBean.permohonanUkur.sebabUkur eq 'h'}">
                                    <tr>
                                        <td colspan="3">&nbsp;&nbsp;&nbsp; (h) *Mengganti tanda sempadan yang hilang / membetulkan tempat tanda-tanda sempadan</td>
                                    </tr>
                                </c:if>
                            </c:if>
                            <c:if test="${actionBean.kodNegeri eq 'Negeri Sembilan'}">                             
                                <c:if test="${actionBean.permohonanUkur.sebabUkur eq 'g'}">
                                    <tr>
                                        <td colspan="3">&nbsp;&nbsp;&nbsp; (g) *Mengganti tanda sempadan yang hilang / membetulkan tempat tanda-tanda sempadan</td>
                                    </tr>
                                </c:if>
                            </c:if>
                            <%----COMMIT SEMENTARA-- --%>

                            <tr>
                                <td colspan="3"></td>
                            </tr>
                            <tr>
                                <td id="tdLabel" colspan="3"><b>2) Suratan-suratan Hakmilik yang dikehendaki untuk dikeluarkan :</b></td>
                            </tr>
                            <c:if test="${actionBean.permohonanUkur.kodHakmilik.kod eq 'GRN'}">
                                <tr>
                                    <td colspan="3">&nbsp;&nbsp;&nbsp; (a) Geran (Borang 5B) bagi <u>${actionBean.permohonanUkur.perincianBorang5b}</u></td>
                                </tr>
                            </c:if>
                            <c:if test="${actionBean.permohonanUkur.kodHakmilik.kod eq 'PN'}">
                                <tr>
                                    <td colspan="3">&nbsp;&nbsp;&nbsp; (b) Pajakan Negeri (Borang 5C) bagi <u>${actionBean.permohonanUkur.perincianBorang5c}</u></td>
                                </tr>
                            </c:if>
                            <c:if test="${actionBean.permohonanUkur.kodHakmilik.kod eq 'GM'}">
                                <c:if test="${actionBean.kodNegeri eq 'Negeri Sembilan'}">
                                    <tr>
                                        <td colspan="3">&nbsp;&nbsp;&nbsp; (c) Geran Mukim (Borang 5D) bagi <u>${actionBean.permohonanUkur.perincianBorang5d}</u></td>
                                    </tr>
                                </c:if>
                                <c:if test="${actionBean.kodNegeri eq 'Melaka'}">
                                    <tr>
                                        <td colspan="3">&nbsp; &nbsp;&nbsp;&nbsp; (c) Geran Mukim MCL (Borang 5D) bagi <u>${actionBean.permohonanUkur.perincianBorang5d}</u></td>                              
                                    </tr>
                                </c:if>
                            </c:if>
                            <c:if test="${actionBean.permohonanUkur.kodHakmilik.kod eq 'PM'}">
                                <tr>
                                    <td colspan="3">&nbsp;&nbsp;&nbsp; (d) Pajakan Mukim (Borang 5E) bagi <u>${actionBean.permohonanUkur.perincianBorang5e}</u></td>
                                </tr>
                            </c:if>
                            <c:if test="${actionBean.permohonanUkur.kodHakmilik.kod eq 'PL'}">
                                <tr>
                                    <td colspan="3">&nbsp;&nbsp;&nbsp; (e) Pajakan Lombong didahului oleh Perakuan Lombong bagi <u>${actionBean.permohonanUkur.perincianPajakanLombong}</u></td>
                                </tr>
                            </c:if>
                            <c:if test="${actionBean.permohonanUkur.kodHakmilik.kod eq 'HS'}">
                                <tr>
                                    <td colspan="3">&nbsp;&nbsp;&nbsp; (f) Hakmilik Strata bagi <u>${actionBean.permohonanUkur.perincianStrata}</u></td>
                                </tr>
                            </c:if>
                            <tr>
                                <td colspan="3"></td>
                            </tr>
                            <tr>
                                <td id="tdLabel" colspan="3"><b>3) Suratan-suratan Hakmilik bagi lot-lot <u>${actionBean.permohonanUkur.lot}
                                            <c:if test="${actionBean.permohonanUkur.statusSuratanHakmilik eq 'A'}">
                                                akan dimansuhkan</u>.
                                            </c:if>
                                            <c:if test="${actionBean.permohonanUkur.statusSuratanHakmilik eq 'T'}">
                                            telah dimansuhkan</u>.
                                        </c:if>
                                    </b>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3"></td>
                            </tr>
                            <tr><td id="tdLabel" colspan="3"><b>4) Bayaran-bayaran Ukur :</b></td></tr>
                            <c:if test="${actionBean.permohonanUkur.statusBayaranUkur eq 'a'}">
                                <tr>
                                    <td colspan="3">&nbsp;&nbsp;&nbsp; (a) Dikecualikan penuh oleh <u>${actionBean.permohonanUkur.pemberiPengecualian}</u> di bawan perenggan <u>${actionBean.permohonanUkur.perengganKTN}</u> Perintah Kanun Tanah Negara (Bayaran Ukur) 1965 (Rujukan <u>${actionBean.permohonanUkur.rujukanKTN}</u>).
                                </td>
                                </tr>
                            </c:if>
                            <c:if test="${actionBean.permohonanUkur.statusBayaranUkur eq 'b'}">
                                <tr>
                                    <td colspan="3" valign="top">&nbsp;&nbsp;&nbsp; (b) dikecualikan setakat RM <u>${actionBean.permohonanUkur.jumlahPengecualian}</u> oleh <u>${actionBean.permohonanUkur.pengencualian}</u> dibawah perenggan <u>${actionBean.permohonanUkur.perengganKtn}</u> Perintah Kanun Tanah Negara (Bayaran Ukur) 1965 (Rujukan <u>${actionBean.permohonanUkur.rujukanKtn}</u>).
                                </td>
                                </tr>
                            </c:if>
                            <c:if test="${actionBean.permohonanUkur.statusBayaranUkur eq 'c'}">
                                <tr>
                                    <td colspan="3">&nbsp;&nbsp;&nbsp; (c) Bayaran ukur sebanyak RM <u>${actionBean.permohonanUkur.jumlahBayaranUkur}</u> telah di bayar (No Resit <u>${actionBean.permohonanUkur.noResit}</u> bertarikh <u><fmt:formatDate value="${actionBean.permohonanUkur.tarikhResit}" pattern="dd/MM/yyyy"  /></u>) dan telah dimansuhkan kepada Hasil Persekutuan.</td>
                                </tr>
                            </c:if>
                            <c:if test="${actionBean.permohonanUkur.statusBayaranUkur eq 'd'}">
                                <tr>
                                    <td colspan="3">&nbsp;&nbsp;&nbsp; (d) Di ukur oleh<u>${actionBean.permohonanUkur.pengecualianOlehLihat}</u> (No Resit <u>${actionBean.permohonanUkur.noResitD}</u> bertarikh <u><fmt:formatDate value="${actionBean.permohonanUkur.tarikhResitD}" pattern="dd/MM/yyyy"  /></u>).</td>
                                </tr>
                            </c:if>
                            <tr>
                                <td colspan="3"></td>
                            </tr>

                            <tr><td id="tdLabel"><b>5) Nama dan alamat pemohon :</b></td></tr>
                            <c:set scope="request" value="${actionBean.uniquePemohonList2}" var="senaraiPihak"/>
                            <c:if test="${actionBean.kodNegeri eq 'Melaka'}">
                                <td  id="tdLabel">
                                    <table border="0" width="80%">
                                        <c:set value="1" var="i"/>
                                        <c:forEach items="${senaraiPihak}" var="pihak" end="0">
                                            <tr><td><font size="-1">                                                                
                                                    &nbsp;&nbsp;&nbsp; <c:out value="${pihak.nama}"/> 
                                                    <c:if test="${fn:length(senaraiPihak) > 1}">
                                                        dan ${fn:length(senaraiPihak)-1} yang lain
                                                    </c:if>                                                               
                                                    </font></td>
                                            </tr>
                                            <c:set value="${i + 1}" var="i"/>
                                        </c:forEach>
                                    </table>
                                </td>

                                <tr>
                                    <td  id="tdLabel">
                                        <table border="0" width="80%">
                                            <c:set value="1" var="i"/>
                                            <c:forEach items="${senaraiPihak}" var="pihak" end="0" >
                                                <tr><td><font size="-1">                                                            
                                                        &nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${pihak.alamat1}"/>&nbsp;
                                                        </font></td>
                                                </tr>
                                                <c:if test="${pemohon.pihak.suratAlamat2 ne null}">
                                                    <tr><td><font size="-1">&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${pihak.alamat2}"/>&nbsp;</font></td></tr>
                                                        </c:if>
                                                        <c:if test="${pemohon.pihak.suratAlamat3 ne null}">
                                                    <tr><td><font size="-1">&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${pihak.alamat3}"/>&nbsp;</font></td></tr>
                                                        </c:if>
                                                        <c:if test="${pemohon.pihak.suratAlamat4 ne null}">
                                                    <tr><td><font size="-1">&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${pihak.alamat4}"/>&nbsp;</font></td></tr>
                                                        </c:if>
                                                <tr><td><font size="-1">&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${pihak.poskod}"/>&nbsp;</font></td></tr>
                                                <tr><td><font size="-1">&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${pihak.negeri.nama}"/>&nbsp;</font></td></tr>
                                                        <c:set value="${i + 1}" var="i"/>
                                                    </c:forEach>
                                        </table>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${actionBean.kodNegeri eq 'Negeri Sembilan'}">                              
                                <tr>
                                    <td colspan="2"><b>&nbsp;&nbsp;&nbsp;&nbsp;${actionBean.pihak.nama}<br>&nbsp;&nbsp;&nbsp;&nbsp;${actionBean.pihak.alamat1}<br>&nbsp;&nbsp;&nbsp;&nbsp;${actionBean.pihak.alamat2}<br>&nbsp;&nbsp;&nbsp;&nbsp;${actionBean.pihak.alamat3} <c:if test="${actionBean.pihak.alamat4 ne null}"><br>&nbsp;&nbsp;&nbsp;&nbsp;${actionBean.pihak.alamat4}</c:if><br>&nbsp;&nbsp;&nbsp;&nbsp;${actionBean.pihak.poskod}<br>&nbsp;&nbsp;&nbsp;&nbsp;${actionBean.pihak.negeri.nama}</b></td>
                                </tr>
                            </c:if>

                            <c:if test="${actionBean.kodNegeri eq 'Melaka'}"> 
                                <c:set scope="request" value="${actionBean.uniquePemohonList2}" var="senaraiPihak"/>
                                <c:forEach items="${senaraiPihak}" var="pihak" end="0" >
                                    <tr><td id="tdLabel"><b>6) Daerah : ${actionBean.hakmilikMukim.hakmilik.daerah.nama}</b></td>
                                        <td id="tdLabel"><b>Mukim/Bandar/Pekan : ${actionBean.hakmilikMukim.hakmilik.bandarPekanMukim.nama}</b></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <c:if test="${actionBean.kodNegeri eq 'Negeri Sembilan'}"> 
                                <tr><td id="tdLabel"><b>6) Daerah : ${actionBean.hakmilikMukim.hakmilik.daerah.nama}</b></td>
                                    <td id="tdLabel"><b>Mukim/Bandar/Pekan : ${actionBean.hakmilikMukim.hakmilik.bandarPekanMukim.nama}</b></td>
                                </tr>
                            </c:if>

                            <tr><td id="tdLabel"><b>7) Anggaran Keluasan :</b>
                                    ${actionBean.luasKeseluruhan} ${actionBean.kodKeseluruhan}
                                </td>
                            </tr>
                            <c:if test="${actionBean.kodNegeri eq 'Melaka'}">
                                <tr><td id="tdLabel"><b>8) Kegunaan Tanah</b> :
                                        <c:forEach items="${actionBean.listMPP}" var="item1" begin="0">   
                                            ${item1.kegunaanTanah.nama}
                                        </c:forEach>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${actionBean.kodNegeri eq 'Negeri Sembilan'}">
                                <tr><td id="tdLabel"><b>8) Kegunaan Tanah</b> :
                                        <c:forEach items="${actionBean.listMPP}" var="item1" begin="0">   
                                            ${item1.kegunaanTanah.nama}
                                        </c:forEach>
                                    </td>
                                </tr>
                            </c:if>
                            <tr>
                                <td id="tdLabel"><b>9) Dokumen-dokumen Hakmilik Sementara </b>:</td>
                                <td colspan="2">
                                    <c:if test="${actionBean.permohonanUkur.statusSuratanHakmilikSementara  eq 'T'}">
                                        Telah Dikeluarkan
                                    </c:if>
                                    <c:if test="${actionBean.permohonanUkur.statusSuratanHakmilikSementara  eq 'B'}">
                                        Belum Dikeluarkan
                                    </c:if>
                                </td>
                            </tr>
                        </table>                
                    </c:if>

                    <c:if test="${actionBean.edit2}">
                        <div class="content" align="center">
                            <s:button name="lakarPelan" id="lakarPelan" value="Hantar PU Jupem" class="longbtn"  onclick="RunProgram2('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                        </div>
                    </c:if>
                </c:if>
                <c:if test="${actionBean.noPTSementara eq 1000}">
                    Permohonan Ukur disediakan oleh "Juruukur Berlesen"
                    <br><br>
                    <table border="0" width="35%" cellspacing="10" align="center">
                        <tr>
                            <td id="tdLabel" width="40%"><b>Rujukan Pejabat Tanah </b></td>
                            <td id="tdLabel"><b> : </b></td>
                            <td>${actionBean.idPermohonan}                             
                                <s:hidden name="permohonanUkur.idMohonUkur"/>
                            </td>
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
                    </table>     
                </c:if>           

            </div>
        </fieldset>
    </div>
</s:form>