<%-- 
    Document   : PUV2
    Created on : Nov 12, 2012, 11:24:08 AM
    Author     : Shazwan
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

        strNama = ReplaceAll(strNama," ","_");
        strJawatan = ReplaceAll(strJawatan," ","_");
        alert("nama:" + strNama);
        alert ("jawatan:" + strJawatan);
        strIDStage = "g_penyediaan_pu";
        alert ("stageid:" + strIDStage);
        alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + strIDStage);
    }

    function SemakPU(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {

        alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
        strNama = ReplaceAll(strNama," ","_");
        strJawatan = ReplaceAll(strJawatan," ","_");
        var stageId = "g_semak_pu";
        alert("nama:" + strNama);
        alert ("jawatan:" + strJawatan);
        alert ("stageid:" + stageId);
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        objShell.Run(sysVar("etanahGISPU") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + stageId);
    }

    function SahPU(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {

        alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
        strNama = ReplaceAll(strNama," ","_");
        strJawatan = ReplaceAll(strJawatan," ","_");
        var stageId = "g_sahkan_pu";
        alert("nama:" + strNama);
        alert ("jawatan:" + strJawatan);
        alert ("stageid:" + stageId);
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        objShell.Run(sysVar("etanahGISPU") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + stageId);
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
            /*
                     alert("Sila Masukan No Rujukan Pejabat Ukur");
                     $("#rujukan").focus();
                     return false;*/
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',this.document).html(data);
            },'html');
            return true;
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
             
    function openFrame(type){
        doBlockUI();
        window.open("${pageContext.request.contextPath}/pelupusan/PUV2?openFrame&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //                var idHakmilik = $('#idHakmilik').val();
        //        alert(idHakmilik);
        //                window.open("${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?openFrame&idHakmilik="
        //                    +idHakmilik+"&type="+type, "eTanah",
        //                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }
    function refreshV2(type){
        var url = '${pageContext.request.contextPath}/pelupusan/PUV2?refreshpage&type='+type;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        doUnBlockUI();
    }
</script>
<s:form beanclass="etanah.view.stripes.pelupusan.common.PUV2ActionBean">
    <s:errors/>
    <s:messages/>    
    <div class="subtitle">
        <div class="subtitle">
           <%-- <fieldset class="aras1">
                <legend>TANDATANGAN</legend>
                <div align="center"> 
                    <table class="tablecloth" border="0">
                        <tr>
                            <td>
                                <b>
                                    Ditandatangan Oleh :</b>

                            </td>
                            <td>
                                <s:select name="ditundatangan" id="namapguna">
                                                               <s:option label="Sila Pilih" value="" />                          
                                                               <c:forEach items="${actionBean.penggunaList}" var="i" >                              
                                                                       <s:option value="${i.idPengguna}" >${i.jawatan} - ${i.nama}</s:option>
                                                                   </c:forEach>
                                                           </s:select>
                                </td>
                                <td>
                                <s:button name="simpanTandatangan" class="longbtn" value="Simpan Tandatangan" onclick="doSubmit(this.form,this.name,'page_div')"/>
                            </td>
                        </tr>
                    </table>
                </div>                                        
            </fieldset>
        </div>--%>
        <br/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>MAKLUMAT ASAS
                    <span style="float:right">
                        <%--<a onclick="viewPerihalTanah();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                        <c:if test="${actionBean.disPUController.mAsas}">
                            <a onclick="openFrame('mAsas');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                            </c:if>
                    </span>
                </legend>
                <div align="center"> 
                    <table class="tablecloth" border="0">
                        <tr>
                            <td style="text-align: right">
                                Rujukan Pejabat Tanah :
                            </td>
                            <td>:</td>
                            <td>
                                ${actionBean.idPermohonan}
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">
                                Rujukan Pejabat Ukur :
                            </td>
                            <td>:</td>
                            <td>
                                ${actionBean.permohonanUkur.noRujukanPejabatUkur}
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">
                                Negeri  :
                            </td>
                            <td>:</td>
                            <td>
                                ${actionBean.kodNegeri}
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">
                                No Permohonan Ukur :
                            </td>
                            <td>:</td>
                            <td>
                                ${actionBean.permohonanUkur.noPermohonanUkur}
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">
                                Tarikh Permohonan Ukur :
                            </td>
                            <td>:</td>
                            <td>
                                <fmt:formatDate value="${actionBean.permohonanUkur.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy"  />
                            </td>
                        </tr>
                    </table>
                </div>
            </fieldset>
        </div>
        <br/>                    
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>SURAT-SURAT HAKMILIK UNTUK DIKELUARKAN
                    <span style="float:right">
                        <%--<a onclick="viewPerihalTanah();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                        <c:if test="${actionBean.disPUController.sHakmilik}">
                            <a onclick="openFrame('sHakmilik');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                            </c:if>
                    </span>
                </legend>
                <div align="center">
                    <table class="tablecloth" border="0">
                        <tr>
                            <c:choose>
                                <c:when test="${actionBean.permohonanUkur.sebabUkur eq 'PTK'}">
                                    <td style="text-align: right">
                                        Sebab-sebab ukur
                                    </td>
                                    <td>:</td>
                                    <td>
                                        Pemberimilikan Tanah Kerajaan
                                    </td>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${actionBean.permohonanUkur.sebabUkur eq 'PL'}">
                                    <td style="text-align: right">
                                        Sebab-sebab Ukur
                                    </td>
                                    <td>:</td>
                                    <td>
                                        Pajakan Lombong
                                    </td>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${actionBean.permohonanUkur.sebabUkur eq 'PS'}">
                                    <td style="text-align: right">
                                        Sebab-sebab Ukur
                                    </td>
                                    <td>:</td>
                                    <td>
                                        Pecah Sempadan
                                    </td>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${actionBean.permohonanUkur.sebabUkur eq 'PBB'}">
                                    <td style="text-align: right">
                                        Sebab-sebab Ukur
                                    </td>
                                    <td>:</td>
                                    <td>
                                        Pecah Bahagian Bangunan
                                    </td>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${actionBean.permohonanUkur.sebabUkur eq 'PBT'}">
                                    <td style="text-align: right">
                                        Sebab-sebab Ukur
                                    </td>
                                    <td>:</td>
                                    <td>
                                        Pecah Bahagian Tanah
                                    </td>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${actionBean.permohonanUkur.sebabUkur eq 'GTS'}">
                                    <td style="text-align: right">
                                        Sebab-sebab Ukur
                                    </td>
                                    <td>:</td>
                                    <td>
                                        Mengganti Tanda Sempadan Yang Hilang
                                    </td>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${actionBean.permohonanUkur.sebabUkur eq 'BTS'}">
                                    <td style="text-align: right">
                                        Sebab-sebab Ukur
                                    </td>
                                    <td>:</td>
                                    <td>
                                        Membetulkan Tempat Tanda-tanda Sempadan
                                    </td>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${actionBean.permohonanUkur.sebabUkur eq 'PT'}">
                                    <td style="text-align: right">
                                        Penyatuan Tanah
                                    </td>
                                    <td>:</td>
                                    <td>
                                        ${actionBean.permohonanUkur.lot} *dan terus dipecah  ${actionBean.permohonanUkur.bahagian} 
                                    </td>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${actionBean.permohonanUkur.sebabUkur eq 'PSL'}">
                                    <td style="text-align: right">
                                        Penyerahan Sebahagian dan Lot-lot
                                    </td>
                                    <td>:</td>
                                    <td>
                                        ${actionBean.permohonanUkur.lot}
                                    </td>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${actionBean.permohonanUkur.sebabUkur eq 'PLL'}">
                                    <td style="text-align: right">
                                        Penyerahan Lot-lot
                                    </td>
                                    <td>:</td>
                                    <td>
                                        ${actionBean.permohonanUkur.lot} dan berimilik semula bahagian-bahagian  ${actionBean.permohonanUkur.bahagian}
                                    </td>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${actionBean.permohonanUkur.sebabUkur eq 'PSLL'}">
                                    <td style="text-align: right">
                                        Pengambilan Sebahagian dan Lot-lot
                                    </td>
                                    <td>:</td>
                                    <td>
                                        ${actionBean.permohonanUkur.lot} 
                                    </td>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${actionBean.permohonanUkur.sebabUkur eq 'US'}">
                                    <td style="text-align: right">
                                        Ukuran Semula Lot-lot
                                    </td>
                                    <td>:</td>
                                    <td>
                                        ${actionBean.permohonanUkur.lot}
                                    </td>
                                </c:when>
                            </c:choose>


                        </tr>

                        <tr>
                            <c:choose>
                                <c:when test="${actionBean.permohonanUkur.kodHakmilik.kod eq 'GRN'}">
                                    <td style="text-align: right">
                                        Geran (Borang 5B) 
                                    </td>
                                    <td>:</td>
                                    <td>
                                        ${actionBean.permohonanUkur.perincianBorang5b}
                                    </td>
                                </c:when>
                                <c:when test="${actionBean.permohonanUkur.kodHakmilik.kod eq 'LN'}">
                                    <td style="text-align: right">
                                        Pajakan Negeri (Borang 5C) 
                                    </td>
                                    <td>:</td>
                                    <td>
                                        ${actionBean.permohonanUkur.perincianBorang5c}
                                    </td>
                                </c:when>
                                <c:when test="${actionBean.permohonanUkur.kodHakmilik.kod eq 'GM'}">
                                    <td style="text-align: right">
                                        Geran Mukim (Borang 5D) 
                                    </td>
                                    <td>:</td>
                                    <td>
                                        ${actionBean.permohonanUkur.perincianBorang5d}
                                    </td>
                                </c:when>
                                <c:when test="${actionBean.permohonanUkur.kodHakmilik.kod eq 'PM'}">
                                    <td style="text-align: right">
                                        Pajakan Mukim (Borang 5E) 
                                    </td>
                                    <td>:</td>
                                    <td>
                                        ${actionBean.permohonanUkur.perincianBorang5e}
                                    </td>
                                </c:when>
                                <c:when test="${actionBean.permohonanUkur.kodHakmilik.kod eq 'PL'}">
                                    <td style="text-align: right">
                                        Pajakan Lombong didahului oleh
                                    </td>
                                    <td>:</td>
                                    <td>
                                        ${actionBean.permohonanUkur.perincianPajakanLombong}
                                    </td>
                                </c:when>
                                <c:when test="${actionBean.permohonanUkur.kodHakmilik.kod eq 'HS'}">
                                    <td style="text-align: right">
                                        Hakmilik Strata
                                    </td>
                                    <td>:</td>
                                    <td>
                                        ${actionBean.permohonanUkur.perincianStrata}
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    TIADA SURAT HAKMILIK TELAH DIPILIH
                                </c:otherwise>
                            </c:choose>                            
                        </tr>
                        <tr>
                            <td style="text-align: right">Status Surat Hakmilik</td>
                            <td>:</td>
                            <td>
                                <c:choose>
                                    <c:when test="${actionBean.permohonanUkur.statusSuratanHakmilik eq 'A'}">
                                        Akan dimansuhkan
                                    </c:when>
                                    <c:when test="${actionBean.permohonanUkur.statusSuratanHakmilik eq 'T'}">
                                        Telah dimansuhkan
                                    </c:when>
                                    <c:otherwise>

                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Dokumen-dokumen Hakmilik Sementara</td>
                            <td>:</td>
                            <td>
                                <c:choose>
                                    <c:when test="${actionBean.permohonanUkur.statusSuratanHakmilikSementara eq 'T'}">
                                        Telah Dikeluarkan
                                    </c:when>
                                    <c:when test="${actionBean.permohonanUkur.statusSuratanHakmilikSementara eq 'B'}">
                                        Belum Dikeluarkan
                                    </c:when>
                                    <c:otherwise>

                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </table>
                </div>                    
            </fieldset>
        </div>
        <br/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>BAYARAN UKUR
                    <span style="float:right">
                        <%--<a onclick="viewPerihalTanah();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                        <c:if test="${actionBean.disPUController.bUkur}">
                            <a onclick="openFrame('bUkur');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                            </c:if>
                    </span>
                </legend>
                <div align="center">
                    <table class="tablecloth" border="0">
                        <c:choose>
                            <c:when test="${actionBean.permohonanUkur.jumlahPengecualian eq null and actionBean.permohonanUkur.pemberiPengecualian ne null}">
                                <tr>
                                    <td>Dikecuali penuh oleh</td>
                                    <td>:</td>
                                    <td>
                                        ${actionBean.permohonanUkur.pemberiPengecualian}
                                    </td>
                                </tr>
                                <tr>
                                    <td>Dibawah Perenggan Kanun Tanah Negara (Bayaran Ukur) 1965</td>
                                    <td>:</td>
                                    <td>
                                        ${actionBean.permohonanUkur.perengganKTN}
                                    </td>
                                </tr>
                                <tr>
                                    <td>Rujukan</td>
                                    <td>:</td>
                                    <td>
                                        ${actionBean.permohonanUkur.rujukanKTN}
                                    </td>
                                </tr>
                            </c:when>
                            <c:when test="${actionBean.permohonanUkur.jumlahPengecualian ne null}">
                                <tr>
                                    <td>Dikecuali Sekatan (RM)</td>
                                    <td>:</td>
                                    <td>
                                        <fmt:formatNumber currencySymbol="RM" value="${actionBean.permohonanUkur.jumlahPengecualian}" pattern="#,#00.00"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Oleh</td>
                                    <td>:</td>
                                    <td>
                                        ${actionBean.permohonanUkur.pemberiPengecualian}
                                    </td>
                                </tr>
                                <tr>
                                    <td>Dibawah Perenggan Kanun Tanah Negara (Bayaran Ukur) 1965</td>
                                    <td>:</td>
                                    <td>
                                        ${actionBean.permohonanUkur.perengganKTN}
                                    </td>
                                </tr>
                                <tr>
                                    <td>Rujukan</td>
                                    <td>:</td>
                                    <td>
                                        ${actionBean.permohonanUkur.rujukanKTN}
                                    </td>
                                </tr>
                            </c:when>
                            <c:when test="${actionBean.permohonanUkur.jumlahBayaranUkur ne null and actionBean.permohonanUkur.jumlahPengecualian eq null and actionBean.permohonanUkur.pemberiPengecualian eq null}">                            
                                <tr>
                                    <td>Bayaran Ukur RM</td>
                                    <td>:</td>
                                    <td>
                                        <fmt:formatNumber currencySymbol="RM" value="${actionBean.permohonanUkur.jumlahBayaranUkur}" pattern="###,###.00"  />
                                    </td>
                                </tr>
                                <tr>
                                    <td>No Resit</td>
                                    <td>:</td>
                                    <td>
                                        ${actionBean.permohonanUkur.noResit}
                                    </td>
                                </tr>
                                <tr>
                                    <td>Tarikh Resit</td>
                                    <td>:</td>
                                    <td>
                                        <fmt:formatDate value="${actionBean.permohonanUkur.tarikhResit}"  pattern="dd/MM/yyyy"  />
                                    </td>
                                </tr>
                            </c:when>
                            <c:when test="${actionBean.permohonanUkur.jumlahBayaranUkur eq null and actionBean.permohonanUkur.jumlahPengecualian eq null and actionBean.permohonanUkur.pemberiPengecualian eq null and actionBean.permohonanUkur.diUkurOleh ne null}">                            
                                <tr>
                                    <td>Bayaran Ukur dibayar oleh :</td>
                                    <td>:</td>
                                    <td>
                                        ${actionBean.permohonanUkur.diUkurOleh}
                                    </td>
                                </tr>
                            </c:when>        
                            <c:otherwise>
                                TIADA MAKLUMAT TELAH DIISI
                            </c:otherwise>
                        </c:choose>

                    </table>
                </div>
            </fieldset>
        </div>
        <br/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>UNTUK KEGUNAAN GIS</legend>
                <div align="center">
                     <%--<c:choose>
                        <c:when test="${actionBean.disPUController.checkDMS}"> --%>
                            <%--<s:button name="transferFile" id="btnClick" value="Hantar PU JUPEM" class="longbtn" onclick="doSubmit(this.form, this.name, 'page_div')" />--%>
                        <%--</c:when> --%>
                      <%--  <c:otherwise>
                            <s:button name="simpan" id="save" value="Jana No PU" class="longbtn" onclick="validation(this.name, this.form);"/>
                            <s:button name="transferFile" id="btnClick" value="Hantar PU DMS" class="longbtn" onclick="doSubmit(this.form, this.name, 'page_div')" />
                            <s:button name="simpan" id="save" value="Penyediaan PU GIS" class="longbtn"  onclick="RunProgram('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>
                        </c:otherwise>
                    </c:choose>      --%>              
                </div>
            </fieldset>
        </div>
    </div>    
</s:form>

