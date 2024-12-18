<%--
    Document   :  puV2suratHakmilik.jsp
    Created on :  November 14, 2012, 01:10:13 AM
    Author     :  Shazwan
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SURAT-SURAT HAKMILIK UNTUK DIKELUARKAN</title>
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
    var size = 0 ;
    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
        },'html');
    }
   
    $(document).ready(function() {
       
        var sebabUkur = $('#sebabUkur').val();
         
        //            maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    <c:choose>
        <c:when test="${actionBean.permohonanUkur.kodHakmilik.kod eq 'GRN'}">
                $('#GRN').show();
                $('#LN').hide();
                $('#GM').hide();
                $('#PM').hide();
                $('#PL').hide();
                $('#HS').hide();
        </c:when>
        <c:when test="${actionBean.permohonanUkur.kodHakmilik.kod eq 'LN'}">
                $('#GRN').hide();
                $('#LN').show();
                $('#GM').hide();
                $('#PM').hide();
                $('#PL').hide();
                $('#HS').hide();
        </c:when>
        <c:when test="${actionBean.permohonanUkur.kodHakmilik.kod eq 'GM'}">
                $('#GRN').hide();
                $('#LN').hide();
                $('#GM').show();
                $('#PM').hide();
                $('#PL').hide();
                $('#HS').hide();
        </c:when>
        <c:when test="${actionBean.permohonanUkur.kodHakmilik.kod eq 'PM'}">
                $('#GRN').hide();
                $('#LN').hide();
                $('#GM').hide();
                $('#PM').show();
                $('#PL').hide();
                $('#HS').hide();
        </c:when>
        <c:when test="${actionBean.permohonanUkur.kodHakmilik.kod eq 'PL'}">
                $('#GRN').hide();
                $('#LN').hide();
                $('#GM').hide();
                $('#PM').hide();
                $('#PL').show();
                $('#HS').hide();
        </c:when>
        <c:when test="${actionBean.permohonanUkur.kodHakmilik.kod eq 'HS'}">
                $('#GRN').hide();
                $('#LN').hide();
                $('#GM').hide();
                $('#PM').hide();
                $('#PL').hide();
                $('#HS').show();
        </c:when>
        <c:when test="${actionBean.permohonanUkur.sebabUkur eq 'PT'}">
                $('#PTK').hide();
                $('#PL').hide();
                $('#PBB').hide();
                $('#PBT').hide();
                $('#PT').show();
                $('#PTTerusPecah').show();
                $('#PSL').hide();
                $('#PLL').hide();
                $('#PLLBerimilik').hide();
                $('#PSLL').hide();
                $('#US').hide();
                $('#GTS').hide();
                $('#BTS').hide();
        </c:when>
        <c:when test="${actionBean.permohonanUkur.sebabUkur eq 'SBT'}">
                $('#PTK').hide();
                $('#sebabUkur').show();
                $('#PL').hide();
                $('#PBB').hide();
                $('#PBT').hide();
                $('#PT').hide();
                $('#PTTerusPecah').hide();
                $('#PSL').hide();
                $('#PLL').hide();
                $('#PLLBerimilik').hide();
                $('#PSLL').hide();
                $('#US').hide();
                $('#GTS').hide();
                $('#BTS').hide();
        </c:when>
        <c:otherwise>
                $('#GRN').hide();
                $('#LN').hide();
                $('#GM').hide();
                $('#PM').hide();
                $('#PL').hide();
                $('#HS').hide();
        </c:otherwise>
    </c:choose>



    <c:choose>
        <c:when test="${actionBean.permohonanUkur.sebabUkur eq 'SBT' || actionBean.permohonanUkur.sebabUkur eq 'PTK' || actionBean.permohonanUkur.sebabUkur eq 'PL' || actionBean.permohonanUkur.sebabUkur eq 'PS' || actionBean.permohonanUkur.sebabUkur eq 'PBB' || actionBean.permohonanUkur.sebabUkur eq 'PBT' || actionBean.permohonanUkur.sebabUkur eq 'GTS' || actionBean.permohonanUkur.sebabUkur eq 'BTS'}">
                $('#PTK').hide();
                $('#PL').hide();
                $('#PBB').hide();
                $('#PBT').hide();
                $('#PT').hide();
                $('#PTTerusPecah').hide();
                $('#PSL').hide();
                $('#PLL').hide();
                $('#PLLBerimilik').hide();
                $('#PSLL').hide();
                $('#US').hide();
                $('#GTS').hide();
                $('#BTS').hide();
        </c:when>
        <c:when test="${actionBean.permohonanUkur.sebabUkur eq 'PT'}">
                $('#PTK').hide();
                $('#PL').hide();
                $('#PBB').hide();
                $('#PBT').hide();
                $('#PT').show();
                $('#PTTerusPecah').show();
                $('#PSL').hide();
                $('#PLL').hide();
                $('#PLLBerimilik').hide();
                $('#PSLL').hide();
                $('#US').hide();
                $('#GTS').hide();
                $('#BTS').hide();
        </c:when>
        <c:when test="${actionBean.permohonanUkur.sebabUkur eq 'PSL'}">
                $('#PTK').hide();
                $('#PL').hide();
                $('#PBB').hide();
                $('#PBT').hide();
                $('#PT').hide();
                $('#PTTerusPecah').hide();
                $('#PSL').show();
                $('#PLL').hide();
                $('#PLLBerimilik').hide();
                $('#PSLL').hide();
                $('#US').hide();
                $('#GTS').hide();
                $('#BTS').hide();
        </c:when>
        <c:when test="${actionBean.permohonanUkur.sebabUkur eq 'PLL'}">
                $('#PTK').hide();
                $('#PL').hide();
                $('#PBB').hide();
                $('#PBT').hide();
                $('#PT').hide();
                $('#PTTerusPecah').hide();
                $('#PSL').hide();
                $('#PLL').show();
                $('#PLLBerimilik').show();
                $('#PSLL').hide();
                $('#US').hide();
                $('#GTS').hide();
                $('#BTS').hide();
        </c:when>
        <c:when test="${actionBean.permohonanUkur.sebabUkur eq 'PSLL'}">
                $('#PTK').hide();
                $('#PL').hide();
                $('#PBB').hide();
                $('#PBT').hide();
                $('#PT').hide();
                $('#PTTerusPecah').hide();
                $('#PSL').hide();
                $('#PLL').hide();
                $('#PLLBerimilik').hide();
                $('#PSLL').show();
                $('#US').hide();
                $('#GTS').hide();
                $('#BTS').hide();
        </c:when>
        <c:when test="${actionBean.permohonanUkur.sebabUkur eq 'US'}">
                $('#PTK').hide();
                $('#PL').hide();
                $('#PBB').hide();
                $('#PBT').hide();
                $('#PT').hide();
                $('#PTTerusPecah').hide();
                $('#PSL').hide();
                $('#PLL').hide();
                $('#PLLBerimilik').hide();
                $('#PSLL').hide();
                $('#US').show();
                $('#GTS').hide();
                $('#BTS').hide();
        </c:when>

        <c:otherwise>
            
        </c:otherwise>
    </c:choose>
            if (sebabUkur == ""){
    <%--alert("hi");--%>
                $('#PTK').hide();
                $('#PL').hide();
                $('#PBB').hide();
                $('#PBT').hide();
                $('#PT').hide();
                $('#PTTerusPecah').hide();
                $('#PSL').hide();
                $('#PLL').hide();
                $('#PLLBerimilik').hide();
                $('#PSLL').hide();
                $('#US').hide();
                $('#GTS').hide();
                $('#BTS').hide();
            }
        
        }); //END OF READY FUNCTION
             
        function validateNumber(elmnt,content) {
            //if it is character, then remove it..
            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }
            
        function refreshpage(){
            //            alert('aa');
            NoPrompt();
            opener.refreshV2('main');
            self.close();
        }
    
        function openFieldHakmilik(val){
            if(val == 'GRN'){
                $('#GRN').show();
                $('#LN').hide();
                $('#GM').hide();
                $('#PM').hide();
                $('#PL').hide();
                $('#HS').hide();
            }else if(val == 'LN'){
                $('#GRN').hide();
                $('#LN').show();
                $('#GM').hide();
                $('#PM').hide();
                $('#PL').hide();
                $('#HS').hide();
            }else if(val == 'GM'){
                $('#GRN').hide();
                $('#LN').hide();
                $('#GM').show();
                $('#PM').hide();
                $('#PL').hide();
                $('#HS').hide();
            }else if(val == 'PM'){
                $('#GRN').hide();
                $('#LN').hide();
                $('#GM').hide();
                $('#PM').show();
                $('#PL').hide();
                $('#HS').hide();
            }else if(val == 'PL'){
                $('#GRN').hide();
                $('#LN').hide();
                $('#GM').hide();
                $('#PM').hide();
                $('#PL').show();
                $('#HS').hide();
            }else if(val == 'HS'){
                $('#GRN').hide();
                $('#LN').hide();
                $('#GM').hide();
                $('#PM').hide();
                $('#PL').hide();
                $('#HS').show();
            }
        }
        function checkStatusHakmilik(val){
            if(val=='A'){
                $("#telahDimansuhkan").attr("checked", false);
            }else if(val=='T'){
                $("#akanDimansuhkan").attr("checked", false);
            }else{
                $("#telahDimansuhkan").attr("checked", false);
                $("#akanDimansuhkan").attr("checked", false);
            }
        }
        function checkStatusHakmilikSementara(val){
            if(val=='T'){
                $('#belumDikeluarkan').attr("checked", false);
            }else if(val=='B'){
                $('#telahDikeluarkan').attr("checked", false);
            }else{
                $('#belumDikeluarkan').attr("checked", false);
                $('#telahDikeluarkan').attr("checked", false);
            }
        }
  
        function changeSebabUkur(val){
            if(val == 'PTK'){
                $('#PTK').hide();
                $('#PL').hide();
                $('#PBB').hide();
                $('#PBT').hide();
                $('#PT').hide();
                $('#PTTerusPecah').hide();
                $('#PSL').hide();
                $('#PLL').hide();
                $('#PLLBerimilik').hide();
                $('#PSLL').hide();
                $('#US').hide();
                $('#GTS').hide();
                $('#BTS').hide();
            }else if(val == 'SBT'){
                $('#PTK').hide();
                $('#PL').hide();
                $('#PBB').hide();
                $('#PBT').hide();
                $('#PT').hide();
                $('#PTTerusPecah').hide();
                $('#PSL').hide();
                $('#PLL').hide();
                $('#PLLBerimilik').hide();
                $('#PSLL').hide();
                $('#US').hide();
                $('#GTS').hide();
                $('#BTS').hide();
            }else if(val == 'PL'){
                $('#PTK').hide();
                $('#PL').hide();
                $('#PBB').hide();
                $('#PBT').hide();
                $('#PT').hide();
                $('#PTTerusPecah').hide();
                $('#PSL').hide();
                $('#PLL').hide();
                $('#PLLBerimilik').hide();
                $('#PSLL').hide();
                $('#US').hide();
                $('#GTS').hide();
                $('#BTS').hide();
            }else if(val == 'PBB'){
                $('#PTK').hide();
                $('#PL').hide();
                $('#PBB').hide();
                $('#PBT').hide();
                $('#PT').hide();
                $('#PTTerusPecah').hide();
                $('#PSL').hide();
                $('#PLL').hide();
                $('#PLLBerimilik').hide();
                $('#PSLL').hide();
                $('#US').hide();
                $('#GTS').hide();
                $('#BTS').hide();
            }else if(val == 'PBT'){
                $('#PTK').hide();
                $('#PL').hide();
                $('#PBB').hide();
                $('#PBT').hide();
                $('#PT').hide();
                $('#PTTerusPecah').hide();
                $('#PSL').hide();
                $('#PLL').hide();
                $('#PLLBerimilik').hide();
                $('#PSLL').hide();
                $('#US').hide();
                $('#GTS').hide();
                $('#BTS').hide();
            }else if(val == 'PT'){
                $('#PTK').hide();
                $('#PL').hide();
                $('#PBB').hide();
                $('#PBT').hide();
                $('#PT').show();
                $('#PTTerusPecah').show();
                $('#PSL').hide();
                $('#PLL').hide();
                $('#PLLBerimilik').hide();
                $('#PSLL').hide();
                $('#US').hide();
                $('#GTS').hide();
                $('#BTS').hide();
            }else if(val == 'PSL'){
                $('#PTK').hide();
                $('#PL').hide();
                $('#PBB').hide();
                $('#PBT').hide();
                $('#PT').hide();
                $('#PTTerusPecah').hide();
                $('#PSL').show();
                $('#PLL').hide();
                $('#PLLBerimilik').hide();
                $('#PSLL').hide();
                $('#US').hide();
                $('#GTS').hide();
                $('#BTS').hide();
            }else if(val == 'PLL'){
                $('#PTK').hide();
                $('#PL').hide();
                $('#PBB').hide();
                $('#PBT').hide();
                $('#PT').hide();
                $('#PTTerusPecah').hide();
                $('#PSL').hide();
                $('#PLL').show();
                $('#PLLBerimilik').show();
                $('#PSLL').hide();
                $('#US').hide();
                $('#GTS').hide();
                $('#BTS').hide();
            }else if(val == 'PSLL'){
                $('#PTK').hide();
                $('#PL').hide();
                $('#PBB').hide();
                $('#PBT').hide();
                $('#PT').hide();
                $('#PTTerusPecah').hide();
                $('#PSL').hide();
                $('#PLL').hide();
                $('#PLLBerimilik').hide();
                $('#PSLL').show();
                $('#US').hide();
                $('#GTS').hide();
                $('#BTS').hide();
            }else if(val == 'US'){
                $('#PTK').hide();
                $('#PL').hide();
                $('#PBB').hide();
                $('#PBT').hide();
                $('#PT').hide();
                $('#PTTerusPecah').hide();
                $('#PSL').hide();
                $('#PLL').hide();
                $('#PLLBerimilik').hide();
                $('#PSLL').hide();
                $('#US').show();
                $('#GTS').hide();
                $('#BTS').hide();
            }else if(val == 'GTS'){
                $('#PTK').hide();
                $('#PL').hide();
                $('#PBB').hide();
                $('#PBT').hide();
                $('#PT').hide();
                $('#PTTerusPecah').hide();
                $('#PSL').hide();
                $('#PLL').hide();
                $('#PLLBerimilik').hide();
                $('#PSLL').hide();
                $('#US').hide();
                $('#GTS').show();
                $('#BTS').hide();
            }else if(val == 'BTS'){
                $('#PTK').hide();
                $('#PL').hide();
                $('#PBB').hide();
                $('#PBT').hide();
                $('#PT').hide();
                $('#PTTerusPecah').hide();
                $('#PSL').hide();
                $('#PLL').hide();
                $('#PLLBerimilik').hide();
                $('#PSLL').hide();
                $('#US').hide();
                $('#GTS').hide();
                $('#BTS').show();
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
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pelupusan.common.PUV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <div class="subtitle" id="main">
            <fieldset class="aras1">

                <div id="perihaltanah">
                    <legend>Maklumat Asas</legend>
                    <div class="content" align="center">
                        <table class="tablecloth" border="0">

                            <tr>
                                <td>
                                    Sebab-sebab ukur
                                </td>
                                <td>:</td>
                                <td>
                                    <s:select name="sebabUkur" value="${actionBean.permohonanUkur.sebabUkur}" id="sebabUkur" onchange="changeSebabUkur(this.value)">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:option value="PTK">Pemberimilikan Tanah Kerajaan</s:option>
                                        <s:option value="SBT">Pemberimilikan Tanah Bawah Tanah</s:option>
                                        <s:option value="PL">Pajakan Lombong</s:option>
                                        <s:option value="PS">Pecah Sempadan</s:option>
                                        <s:option value="PBB">Pecah Bahagian Bangunan</s:option>
                                        <s:option value="PBT">Pecah Bahagian Tanah</s:option>
                                        <s:option value="PT">Penyatuan Tanah</s:option>
                                        <s:option value="PSL">Penyerahan Sebahagian dan Lot-lot</s:option>
                                        <s:option value="PLL">Penyerahan Lot-lot</s:option>
                                        <s:option value="PSLL">Pengambilan Sebahagian dan Lot-lot</s:option>
                                        <s:option value="US">Ukuran Semula Lot-lot</s:option>
                                        <s:option value="GTS">Menggantikan Tempat Sempadan Yang Hilang</s:option>
                                        <s:option value="BTS">Membetulkan Tempat Tanda-tanda Sempadan</s:option>
                                    </s:select>
                                </td>
                            </tr>
                            <tr id="PT">
                                <td>Penyatuan Tanah</td>
                                <td>:</td>
                                <td>
                                    <s:text name="permohonanUkur.lotPT" value="${actionBean.permohonanUkur.lot}" id="lotPT"/>
                                </td>
                            </tr>
                            <tr id="PTTerusPecah">
                                <td>Terus Pecah</td>
                                <td>:</td>
                                <td>
                                    <s:text name="permohonanUkur.bahagianPTTerusPecah" value="${actionBean.permohonanUkur.bahagian}" id="bhgnPT"/>
                                </td>
                            </tr>
                            <tr id="PSL">
                                <td>Penyerahan Sebahagian dan Lot-lot</td>
                                <td>:</td>
                                <td>
                                    <s:text name="permohonanUkur.lotPSL" value="${actionBean.permohonanUkur.lot}" id="textPSL"/>
                                </td>
                            </tr>
                            <tr id="PLL">
                                <td>Penyerahan Lot-lot</td>
                                <td>:</td>
                                <td>
                                    <s:text name="permohonanUkur.lotPLL" value="${actionBean.permohonanUkur.lot}" id="lotPLL"/>
                                </td>
                            </tr>
                            <tr id="PLLBerimilik">
                                <td>Berimilik Semula Bahagian</td>
                                <td>:</td>
                                <td>
                                    <s:text name="permohonanUkur.bahagianPLLBerimilik" value="${actionBean.permohonanUkur.bahagian}" id="bhgnPLL"/>
                                </td>
                            </tr>
                            <tr id="PSLL">
                                <td>Pengambilan Sebahagian Lot-lot</td>
                                <td>:</td>
                                <td>
                                    <s:text name="permohonanUkur.lotPSLL" value="${actionBean.permohonanUkur.lot}" id="lotPSLL"/>
                                </td>
                            </tr>
                            <tr id="US">
                                <td>Ukuran Semula Lot-lot</td>
                                <td>:</td>
                                <td>
                                    <s:text name="permohonanUkur.lotUS" value="${actionBean.permohonanUkur.lot}" id="lotUS"/>
                                </td>
                            </tr>



                            <tr>
                                <td>Jenis Surat Hakmilik untuk dikeluarkan</td>
                                <td>:</td>
                                <td>
                                    <s:select name="kodHakmilik" value="${actionBean.permohonanUkur.kodHakmilik.kod}" id="kodHakmilik" onchange="openFieldHakmilik(this.value)">
                                        <s:option value="">Sila Pilih</s:option>
                                        <%--<s:option value="JK">Kaki</s:option>--%>
                                        <s:option value="GRN">Geran (Borang 5B)</s:option>
                                        <s:option value="LN">Pajakan Negeri (Borang 5C)</s:option>
                                        <s:option value="GM">Geran Mukim (Borang 5D)</s:option>
                                        <s:option value="PM">Pajakan Mukim (Borang 5E)</s:option>
                                        <s:option value="PL">Pajakan Lombong</s:option>
                                        <s:option value="HS">Hakmilik Strata</s:option>
                                    </s:select>
                                </td>
                            </tr>
                            <tr id="GRN">
                                <td>Geran (Borang 5B)</td>
                                <td>:</td>
                                <td>
                                    <s:text name="permohonanUkur.perincianBorang5b" value="${actionBean.permohonanUkur.perincianBorang5b}" id="textGRN"/>
                                </td>
                            </tr>
                            <tr id="LN">
                                <td>Pajakan Negeri (Borang 5C)</td>
                                <td>:</td>
                                <td>
                                    <s:text name="permohonanUkur.perincianBorang5c" value="${actionBean.permohonanUkur.perincianBorang5c}" id="LN"/>
                                </td>
                            </tr>
                            <tr id="GM">
                                <td>Geran Mukim (Borang 5D)</td>
                                <td>:</td>
                                <td>
                                    <s:text name="permohonanUkur.perincianBorang5d" value="${actionBean.permohonanUkur.perincianBorang5d}" id="GM"/>
                                </td>
                            </tr>
                            <tr id="PM">
                                <td>Pajakan Mukim (Borang 5E)</td>
                                <td>:</td>
                                <td>
                                    <s:text name="permohonanUkur.perincianBorang5e" value="${actionBean.permohonanUkur.perincianBorang5e}" id="PM"/>
                                </td>
                            </tr>
                            <tr id="PL">
                                <td>Pajakan Lombong didahului oleh</td>
                                <td>:</td>
                                <td>
                                    <s:text name="permohonanUkur.perincianPajakanLombong" value="${actionBean.permohonanUkur.perincianPajakanLombong}" id="PL"/>
                                </td>
                            </tr>
                            <tr id="HS">
                                <td>Hakmilik Strata</td>
                                <td>:</td>
                                <td>
                                    <s:text name="permohonanUkur.perincianStrata" value="${actionBean.permohonanUkur.perincianStrata}" id="HS"/>
                                </td>
                            </tr>
                            <tr>
                                <td>Status Surat Hakmilik</td>
                                <td>:</td>
                                <td>
                                    <s:checkbox name="permohonanUkur.statusSuratanHakmilik" value="A" id="akanDimansuhkan" onclick="checkStatusHakmilik(this.value)"/>Akan Dimansuhkan
                                    <s:checkbox name="permohonanUkur.statusSuratanHakmilik" value="T" id="telahDimansuhkan" onclick="checkStatusHakmilik(this.value)"/>Telah Dimansuhkan
                                </td>
                            </tr>
                            <tr>
                                <td>Dokumen-Dokumen Hakmilik Sementara</td>
                                <td>:</td>
                                <td>
                                    <s:checkbox name="permohonanUkur.statusSuratanHakmilikSementara" value="T" id="telahDikeluarkan" onclick="checkStatusHakmilikSementara(this.value)"/>Telah Dikeluarkan
                                    <s:checkbox name="permohonanUkur.statusSuratanHakmilikSementara" value="B" id="belumDikeluarkan" onclick="checkStatusHakmilikSementara(this.value)"/>Belum Dikeluarkan
                                </td>
                            </tr>
                        </table>        
                    </div>
                    <br/>
                </div>
            </fieldset>

            <fieldset class="aras1">
                <table  width="100%" border="0">
                    <tr>
                        <td align="center">
                            <s:submit name="simpansuratHakmilik" value="Simpan" class="longbtn" onclick="NoPrompt();"/>
                            <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                        </td>
                    </tr>
                </table>   
            </fieldset>
        </div>
    </s:form>
</body>

