<%--
    Document   :  laporan_tanahGSAPerihal.jsp
    Created on :  Jan 03, 2012, 10:18:13 AM
    Author     :  Shazwan
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PERIHAL TANAH</title>
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
        //            maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    <c:if test="${actionBean.laporanTanah.bolehBerimilik ne null}">
        <c:if test="${actionBean.laporanTanah.bolehBerimilik eq 'T'}">
                $('#jikasebab').show();
        </c:if>
        <c:if test="${actionBean.laporanTanah.bolehBerimilik eq 'Y'}">
                $('#jikasebab').hide();
        </c:if>
    </c:if>
    <c:if test="${actionBean.laporanTanah.bolehBerimilik eq null}">
            $('#jikasebab').hide();
    </c:if>
    <c:if test="${actionBean.permohonanLaporanPelan.kodTanah.kod eq '99'}">
            $('#jenistanahlainlain').show();
    </c:if>
    <c:if test="${actionBean.permohonanLaporanPelan.kodTanah.kod ne '99'}">
            $('#jenistanahlainlain').hide();
    </c:if>
            /*
             * KOD MILIK
             */
    <c:choose>
        <c:when test="${actionBean.kodP eq 'K'}">
                $('#tanahkerajaan').show();
                $('#jikasebab').hide();
                $('#carianHakmilik').hide();
                $('#jenisRizab').hide();
                $('#jenisRizabno').hide();
            <c:if test="${actionBean.laporanTanah ne null}">
                <c:if test="${!empty actionBean.laporanTanah.bolehBerimilik}">                        
                    <c:if test="${actionBean.laporanTanah.bolehBerimilik eq 'T'}">
                            $('#jikasebab').show();
                    </c:if>
                    <c:if test="${actionBean.laporanTanah.bolehBerimilik ne 'T'}">
                            $('#jikasebab').hide();
                    </c:if>
                </c:if>
            </c:if>
        </c:when>
        <c:when test="${actionBean.kodP eq 'H'}">                  
                $('#carianHakmilik').show();
                $('#tanahkerajaan').hide();
                $('#jikasebab').hide();
                $('#jenisRizab').hide();
                $('#jenisRizabno').hide();
        </c:when>
        <c:when test="${actionBean.kodP eq 'R'}">
                $('#jenisRizab').show();
                $('#jenisRizabno').show();
                $('#carianHakmilik').hide();
                $('#tanahkerajaan').hide();
                $('#jikasebab').hide();
        </c:when>
        <c:otherwise>
                $('#jenisRizab').hide();
                $('#jenisRizabno').hide();
                $('#carianHakmilik').hide();
                $('#tanahkerajaan').hide();
                $('#jikasebab').hide();
        </c:otherwise>
    </c:choose>
            /*
             * END
             */
            /*
             * PARLIMEN DAN DUN
             */
    <c:choose>
        <c:when test="${actionBean.hakmilikPermohonan.kodKawasanParlimen.kod eq 'P134'}">
                $('#mT').show();
                $('#aG').hide();
                $('#tB').hide();
                $('#bK').hide();
                $('#kM').hide();
                $('#jS').hide();
                $('#kodDmT').val('${actionBean.hakmilikPermohonan.kodDUN.kod}');
        </c:when>
        <c:when test="${actionBean.hakmilikPermohonan.kodKawasanParlimen.kod eq 'P135'}">
                $('#aG').show();
                $('#mT').hide();
                $('#tB').hide();
                $('#bK').hide();
                $('#kM').hide();
                $('#jS').hide();
                $('#kodDaG').val('${actionBean.hakmilikPermohonan.kodDUN.kod}');
        </c:when>
        <c:when test="${actionBean.hakmilikPermohonan.kodKawasanParlimen.kod eq 'P136'}">
                $('#tB').show();
                $('#aG').hide();
                $('#mT').hide();
                $('#bK').hide();
                $('#kM').hide();
                $('#jS').hide();
                $('#kodDtB').val('${actionBean.hakmilikPermohonan.kodDUN.kod}');
        </c:when>
        <c:when test="${actionBean.hakmilikPermohonan.kodKawasanParlimen.kod eq 'P137'}">
                $('#bK').show();
                $('#tB').hide();
                $('#aG').hide();
                $('#mT').hide();
                $('#kM').hide();
                $('#jS').hide();
                $('#kodDbK').val('${actionBean.hakmilikPermohonan.kodDUN.kod}');
        </c:when>
        <c:when test="${actionBean.hakmilikPermohonan.kodKawasanParlimen.kod eq 'P138'}">
                $('#kM').show();
                $('#bK').hide();
                $('#tB').hide();
                $('#aG').hide();
                $('#mT').hide();
                $('#jS').hide();
                $('#kodDkM').val('${actionBean.hakmilikPermohonan.kodDUN.kod}');
        </c:when>
        <c:when test="${actionBean.hakmilikPermohonan.kodKawasanParlimen.kod eq 'P139'}">
                $('#jS').show();
                $('#kM').hide();
                $('#bK').hide();
                $('#tB').hide();
                $('#aG').hide();
                $('#mT').hide();
                $('#kodDjS').val('${actionBean.hakmilikPermohonan.kodDUN.kod}');
        </c:when>
        <c:otherwise>
                $('#jS').hide();
                $('#kM').hide();
                $('#bK').hide();
                $('#tB').hide();
                $('#aG').hide();
                $('#mT').hide();
        </c:otherwise>
    </c:choose>
        
        }); //END OF READY FUNCTION
         
        function showSebab() {
            $('#jikasebab').show();
        }

        function hideSebab() {
            $('#jikasebab').hide();
        }

        function showjenistanahlain(value){
            // alert(value);
            if(value == '99'){
                $('#jenistanahlainlain').show();
            }else{
                $('#jenistanahlainlain').hide();
            }
        }
    
        function refreshpage(type){
            //        alert(type);
            var url = '${pageContext.request.contextPath}/pengambilan/laporan_tanahV2?refreshpage&type='+type;
            window.open(url, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
    
        function refreshpage(){
            //        alert('aa');
            NoPrompt();
    <%-- <c:choose>
         <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">--%>
                 var idHakmilik = $('#idHakmilik').val();
                 opener.refreshV2ManyHakmilik('main',idHakmilik);
    <%--   </c:when>
        <c:otherwise>
                opener.refreshV2('main');
        </c:otherwise>
    </c:choose>--%>
            
            self.close();
        }
        
        function openLain_lainPengawal(val){
            if(val == '3'){
                $('#catatanPengawal').show();
            }else{
                $('#catatanPengawal').hide();
            }
        }
                
        function changeHideDun(value){
            // alert(value);
            $('#kodD').val("");
            if(value == 'P134'){
                $('#mT').show();
                $('#aG').hide();
                $('#tB').hide();
                $('#bK').hide();
                $('#kM').hide();
                $('#jS').hide();
            }else if(value == 'P135'){
                $('#aG').show();
                $('#mT').hide();
                $('#tB').hide();
                $('#bK').hide();
                $('#kM').hide();
                $('#jS').hide();
            }else if(value == 'P136'){
                $('#tB').show();
                $('#aG').hide();
                $('#mT').hide();
                $('#bK').hide();
                $('#kM').hide();
                $('#jS').hide();
            }else if(value == 'P137'){
                $('#bK').show();
                $('#tB').hide();
                $('#aG').hide();
                $('#mT').hide();
                $('#kM').hide();
                $('#jS').hide();
            }else if(value == 'P138'){
                $('#kM').show();
                $('#bK').hide();
                $('#tB').hide();
                $('#aG').hide();
                $('#mT').hide();
                $('#jS').hide();
            }else if(value == 'P139'){
                $('#jS').show();
                $('#kM').hide();
                $('#bK').hide();
                $('#tB').hide();
                $('#aG').hide();
                $('#mT').hide();
            }
        }
        
        function changeHide(value){
                 // alert(value);
            if(value == 'K'){
                $('#tanahkerajaan').show();
                $('#jikasebab').hide();
            }else{
                $('#tanahkerajaan').hide();
            }

            if(value == 'R'){
                $('#jenisRizab').show();
                $('#jenisRizabno').show();
            }else{
                $('#jenisRizab').hide();
                $('#jenisRizabno').hide();
            }
            if(value == 'H'){
                $('#carianHakmilik').show();
                $('#jikasebab').hide();
            }else{
                $('#carianHakmilik').hide();
            }
        }
        
        function dun(value){
            $('#kodD').val(value);
        }
        
        function uploadForm(pandanganImej) {
            NoPrompt();
            var idLapor = $('#idLapor').val();
            var idHakmilik = $('#idHakmilik').val();
            //var idImej = $('#idImej').val();
            //alert(idImej);
            //            alert(idLapor);
            window.open("${pageContext.request.contextPath}/pengambilan/laporan_tanahV2?uploadDoc&pandanganImej="+pandanganImej+"&idLapor="+idLapor+"&idHakmilik="+idHakmilik, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
        
        function doSetDokumenHakmilik(){
            var idDokumen = document.getElementById("hmImej").options[document.getElementById("hmImej").selectedIndex].value;
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        
        function tambahPermohonanTerdahulu(){
            //            alert('test');
            NoPrompt();
            window.open("${pageContext.request.contextPath}/pengambilan/laporan_tanahV2?permohonanTerdahuluPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }
        
        function carianHakmilikPopUp(){
            // alert('popup');
            NoPrompt();
            var idHakmilik = $('#idHakmilik').val();
            window.open("${pageContext.request.contextPath}/pengambilan/laporan_tanahV2?carianHakmilikPopup&idHakmilik="+idHakmilik, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=980,height=800");
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
    <s:form beanclass="etanah.view.stripes.pengambilan.LaporanTanahNewDisposalActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="kodD" id="kodD"/>
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <%--<s:hidden name="idImej" id="idImej" value="${actionBean.hakmilikImejLaporanListEdit[0].idImej}"/>--%>
        <s:hidden name="idLapor" id="idLapor" value="${actionBean.laporanTanah.idLaporan}"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <div id="perihaltanah">
                    <legend>Perihal Tanah</legend>
                    <div class="content" align="center">
                        <c:if test="${actionBean.kodP eq 'K'}">
                            Tanah Kerajaan
                        </c:if>
                        <c:if test="${actionBean.kodP eq 'H'}">
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT'}">
                                Tanah Hakmilik Permukaan
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'BMBT'}">
                                Tanah Hakmilik 
                            </c:if>     
                        </c:if>
                        <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="/pengambilan/laporan_tanahV2">
                            <c:if test="${line.hakmilikPermohonan.hakmilik ne null}">
                                <display:column title="No" sortable="true">${line_rowNum}
                                    <s:hidden name="x" class="x${line_rowNum -1}" value="${line.hakmilikPermohonan.id}"/></display:column>
                                <display:column title="ID Hakmilik" >${line.hakmilikPermohonan.hakmilik.idHakmilik}</display:column> 
                                <display:column title="No.Lot/PT">
                                    <c:if test="${line.hakmilikPermohonan.noLot eq null}">
                                        ${line.hakmilikPermohonan.hakmilik.noLot}
                                    </c:if>
                                    <c:if test="${line.hakmilikPermohonan.noLot ne null}">
                                        ${line.hakmilikPermohonan.noLot}
                                    </c:if>
                                </display:column>
                                <display:column title="Bandar/Pekan/Mukim">
                                    <c:if test="${line.hakmilikPermohonan.bandarPekanMukimBaru.nama eq null}">
                                        ${line.hakmilikPermohonan.hakmilik.bandarPekanMukim.nama}
                                    </c:if>
                                    <c:if test="${line.hakmilikPermohonan.noLot ne null}">
                                        ${line.hakmilikPermohonan.bandarPekanMukimBaru.nama}
                                    </c:if>
                                </display:column>
                                <display:column title="Seksyen"><s:select name="kodSeksyen.nama" value="${actionBean.hakmilikPermohonan.hakmilik.seksyen.kod}">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:options-collection collection="${actionBean.kodSeksyenList}" label="nama" value="kod" /></s:select>
                                </display:column>
                                <display:column title="Daerah">
                                    <c:if test="${line.hakmilikPermohonan.bandarPekanMukimBaru.daerah.nama eq null}">
                                        ${line.hakmilikPermohonan.hakmilik.bandarPekanMukim.daerah.nama}
                                    </c:if>
                                    <c:if test="${line.hakmilikPermohonan.bandarPekanMukimBaru.daerah.nama ne null}">
                                        ${line.hakmilikPermohonan.bandarPekanMukimBaru.daerah.nama}
                                    </c:if>
                                </display:column>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT'}">
                                    <display:column title="Luas Tanah Permukaan"><s:format formatPattern="#,###,##0.0000" value="${line.hakmilikPermohonan.hakmilik.luas}"/> ${line.hakmilikPermohonan.hakmilik.kodUnitLuas.nama} </display:column>
                                    <display:column title="Luas Tanah Bawah Dipohon"><s:format formatPattern="#,###,##0.0000" value="${line.hakmilikPermohonan.luasTerlibat}"/> ${line.hakmilikPermohonan.kodUnitLuas.nama} </display:column>
                                </c:if>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'BMBT'}">
                                    <display:column title="Luas Diambil"><s:format formatPattern="#,###,##0.0000" value="${line.hakmilikPermohonan.luasTerlibat}"/> ${line.hakmilikPermohonan.kodUnitLuas.nama} </display:column>
                                </c:if>
                            </c:if>
                            <c:if test="${line.hakmilikPermohonan.hakmilik eq null}">
                                <display:column title="No" sortable="true">${line_rowNum}
                                    <s:hidden name="x" class="x${line_rowNum -1}" value="${line.hakmilikPermohonan.id}"/></display:column>
                                <display:column title="No.Lot/PT" >${line.hakmilikPermohonan.noLot}</display:column>
                                <display:column title="Bandar/Pekan/Mukim" >${line.hakmilikPermohonan.bandarPekanMukimBaru.nama}</display:column>
                                <display:column title="Seksyen"><s:select name="kodSeksyen.nama" value="${actionBean.hakmilikPermohonan.kodSeksyen.kod}">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:options-collection collection="${actionBean.kodSeksyenList}" label="nama" value="kod" /></s:select>
                                </display:column>
                                <display:column title="Daerah" >${line.hakmilikPermohonan.bandarPekanMukimBaru.daerah.nama}</display:column>
                                <display:column title="Luas Diambil"><s:format formatPattern="#,###,##0.0000" value="${line.hakmilikPermohonan.luasTerlibat}"/> ${line.hakmilikPermohonan.kodUnitLuas.nama}  </display:column>
                            </c:if>
                        </display:table>        
                        <br>
                        <table class="tablecloth" border="0">
                            <tr>
                                <td>
                                    <font color="red" size="4">*</font>Status Tanah :
                                </td>
                                <td>
                                    <s:select name="kodP" style="width:150px" id="kodP" onchange="javaScript:changeHide(this.value)">
                                        <c:choose>
                                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                                                <s:option value="0">Sila Pilih</s:option>
                                                <s:options-collection collection="${listUtil.senaraiKodPemilikanLPSP}" label="nama" value="kod"/>
                                            </c:when>
                                            <c:otherwise>
                                                <s:option value="0">Sila Pilih</s:option>
                                                <s:options-collection collection="${listUtil.kodPemilikanList}" label="nama" value="kod"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </s:select>
                                </td>
                            </tr>
                            <tr id="tanahkerajaan">
                                <td>
                                    <font color="red" size="4">*</font>Tanah Kerajaan Boleh diberimilik :
                                </td>
                                <td>
                                    <s:radio name="laporanTanah.bolehBerimilik" value="Y" onclick="hideSebab();" />&nbsp;Ya
                                    <s:radio name="laporanTanah.bolehBerimilik" value="T" onclick="showSebab();"/>&nbsp;Tidak
                                </td>
                            </tr>
                            <tr id="jikasebab">
                                <td>
                                    Jika Tidak Boleh diberimilik, sebab :
                                </td>
                                <td>
                                    <s:textarea name="laporanTanah.sebabTidakBolehBerimilik" rows="5" cols="50"/>
                                </td>
                            </tr>
                            <%--                          <tr id="carianHakmilik">
                                                            <td colspan="2">
                                                                <display:table  name="${actionBean.senaraiHakmilikPerihalTanah}" id="line" class="tablecloth">
                                                                    <display:column title="ID Hakmilik">
                                                                        <c:if test="${line.hakmilik.kodHakmilik.nama ne null}"> ${line.hakmilik.kodHakmilik.nama}&nbsp; </c:if>
                                                                        <c:if test="${line.hakmilik.kodHakmilik.nama eq null}"> Tiada Data </c:if>
                                                                    </display:column>

                                        <display:column title="Jenis Hakmilik">
                                            <c:if test="${line.hakmilik.idHakmilik ne null}"> ${line.hakmilik.idHakmilik}&nbsp; </c:if>
                                            <c:if test="${line.hakmilik.idHakmilik eq null}"> Tiada Data </c:if>
                                        </display:column>

                                        <display:column title="No Lot" >
                                            <c:if test="${line.hakmilik.noLot ne null}"> ${line.hakmilik.noLot}&nbsp; </c:if>
                                            <c:if test="${line.hakmilik.noLot eq null}"> Tiada Data </c:if>

                                        </display:column>

                                        <display:column title="Luas">
                                            <c:if test="${line.hakmilik.luas ne null}"> <fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</c:if>
                                            <c:if test="${line.hakmilik.luas eq null}"> Tiada Data </c:if>
                                        </display:column>

                                        <display:column property="hakmilik.kegunaanTanah.nama" title="Kegunaan" >
                                            <c:if test="${line.hakmilik.kegunaanTanah ne null}"> ${line.hakmilik.kegunaanTanah.nama}&nbsp; </c:if>
                                            <c:if test="${line.hakmilik.kegunaanTanah eq null}"> Tiada Data </c:if>
                                        </display:column>

                                        <display:column title="Cukai (RM)">
                                            <c:if test="${line.hakmilik.cukai ne null}"> <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukai}"/>&nbsp; </c:if>
                                            <c:if test="${line.hakmilik.cukai eq null}"> Tiada Data </c:if>
                                        </display:column>
                                    </display:table> 
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PRMP'}">
                                        <s:button class="longbtn" value="Carian Hakmilik" name="idHakmilik" id="idHakmilik" onclick="carianHakmilikPopUp();"/>&nbsp;
                                    </c:if>        
                                </td>
                            </tr>--%>
                            <tr id="jenisRizab">
                                <td>
                                    <font color="red" size="4">*</font>Jenis Rizab :
                                </td>
                                <td>
                                    <s:select name="tanahR" style="width:250px" id="tanahR" >
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodRizab}" label="nama" value="kod"/>
                                    </s:select>
                                </td>
                            </tr>
                            <tr id="jenisRizabno">
                                <td>
                                    <font color="red" size="4">*</font>No. Warta Kerajaan :
                                </td>
                                <td>
                                    <s:text name="tanahrizabpermohonan1.noWarta" />
                                </td>
                            </tr>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR'}">
                                <tr>
                                    <td>
                                        <font color="red" size="4">*</font>Tarikh Warta:
                                    </td>
                                    <td>
                                        <s:text name="tanahrizabpermohonan1.tarikhWarta" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <font color="red" size="4">*</font>No Pelan Warta:
                                    </td>
                                    <td>
                                        <s:text name="tanahrizabpermohonan1.noPW" size="30"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <font color="red" size="4">*</font>Pengawal:
                                    </td>
                                    <td>
                                        <s:select name="statBdnPngwl" style="width:350px" value="${actionBean.statBdnPngwl}" onchange="openLain_lainPengawal(this.value)">
                                            <s:option value="0">Sila Pilih</s:option>
                                            <s:option value="1">SUK Negeri</s:option>
                                            <s:option value="2">Pesuruhjaya Tanah Persekutuan</s:option>
                                            <s:option value="3">Lain-lain</s:option>
                                        </s:select>
                                    </td>
                                </tr>
                                <tr id="catatanPengawal">
                                    <td>
                                        <font color="red"></font>Jika Lain-lain (Sila Nyatakan) :
                                    </td>
                                    <td>
                                        <s:textarea name="tanahrizabpermohonan1.penjaga" id="catatan"  rows="5" cols="40"/>
                                    </td>
                                </tr>
                            </c:if>
                            <tr>
                                <td>
                                    <font color="red" size="4">*</font>Jenis Tanah :
                                </td>
                                <td>
                                    <s:select name="kodT" style="width:150px" value="" id="kodTanah" onchange="javaScript:showjenistanahlain(this.value)">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodTanah}" label="nama" value="kod"/>
                                    </s:select>
                                </td>
                            </tr> 
                            <tr id="jenistanahlainlain">
                                <td>
                                    jika lain-lain :
                                </td>
                                <td>
                                    <s:textarea name="kand" rows="5" cols="50"/>
                                </td>
                            </tr>
                            <c:if test="${actionBean.kodNegeri eq '04'}">
                                <tr>
                                    <td>
                                        <font color="red" size="4">*</font>Kawasan Parlimen :
                                    </td>
                                    <td>
                                        <s:select name="kodPar" style="width:150px" value="" id="kodPar" onchange="javaScript:changeHideDun(this.value)">
                                            <s:option value="0">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodKawasanparlimen}" label="nama" value="kod"/>
                                        </s:select>
                                    </td>
                                </tr>
                                <tr id="mT">
                                    <td>
                                        <font color="red" size="4">*</font>DUN :
                                    </td>
                                    <td>
                                        <s:select name="kodDmT" style="width:150px" value="" id="kodDmT" onchange="dun(this.value);">
                                            <s:option value="0">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiP134}" label="nama" value="kod"/>

                                        </s:select>
                                        <%--<s:select name="kodDmT" style="width:150px" value="" id="kodDmT" onchange="dun(this.value);">
                                            <s:option value="0">Sila Pilih</s:option>
                                            <s:option value="N01">Kuala Linggi</s:option>
                                            <s:option value="N02">Tanjung Bidara</s:option>
                                            <s:option value="N03">Ayer Limau</s:option>
                                            <s:option value="N04">Lendu</s:option>
                                            <s:option value="N05">Taboh Naning</s:option>
                                        </s:select>--%>
                                    </td>
                                </tr>
                                <tr id="aG">
                                    <td>
                                        <font color="red" size="4">*</font>DUN :
                                    </td>
                                    <td>
                                        <s:select name="kodDaG" style="width:150px" value="" id="kodDaG" onchange="dun(this.value);">
                                            <s:option value="0">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiP135}" label="nama" value="kod"/>

                                        </s:select>
                                        <%--<s:select name="kodDaG" style="width:150px" value="" id="kodDaG" onchange="dun(this.value);">
                                            <s:option value="0">Sila Pilih</s:option>
                                            <s:option value="N06">Rembia</s:option>
                                            <s:option value="N07">Gadek</s:option>
                                            <s:option value="N08">Machap</s:option>
                                            <s:option value="N09">Durian Tunggal</s:option>
                                            <s:option value="N10">Asahan</s:option>
                                        </s:select>--%>
                                    </td>
                                </tr>
                                <tr id="tB">
                                    <td>
                                        <font color="red" size="4">*</font>DUN :
                                    </td>
                                    <td>
                                        <s:select name="kodDtB" style="width:150px" value="" id="kodDtB" onchange="dun(this.value);">
                                            <s:option value="0">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiP136}" label="nama" value="kod"/>

                                        </s:select>
                                        <%--<s:select name="kodDtB" style="width:150px" value="" id="kodDtB" onchange="dun(this.value);">
                                            <s:option value="0">Sila Pilih</s:option>
                                            <s:option value="N11">Sungai Udang</s:option>
                                            <s:option value="N12">Pantai Kundor</s:option>
                                            <s:option value="N13">Paya Rumput</s:option>
                                            <s:option value="N14">Kelebang</s:option>
                                        </s:select>--%>
                                    </td>
                                </tr>
                                <tr id="bK">
                                    <td>
                                        <font color="red" size="4">*</font>DUN :
                                    </td>
                                    <td>
                                        <s:select name="kodDbK" style="width:150px" value="" id="kodDbK" onchange="dun(this.value);">
                                            <s:option value="0">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiP137}" label="nama" value="kod"/>

                                        </s:select>
                                        <%--<s:select name="kodDbK" style="width:150px" value="" id="kodDbK" onchange="dun(this.value);">
                                            <s:option value="0">Sila Pilih</s:option>
                                            <s:option value="N15">Bachang</s:option>
                                            <s:option value="N16">Ayer Keroh</s:option>
                                            <s:option value="N17">Bukit Baru</s:option>
                                            <s:option value="N18">Ayer Molek</s:option>
                                        </s:select>--%>
                                    </td>
                                </tr>
                                <tr id="kM">
                                    <td>
                                        <font color="red" size="4">*</font>DUN :
                                    </td>
                                    <td>
                                        <s:select name="kodDkM" style="width:150px" value="" id="kodDkM" onchange="dun(this.value);">
                                            <s:option value="0">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiP138}" label="nama" value="kod"/>

                                        </s:select>
                                        <%--<s:select name="kodDkM" style="width:150px" value="" id="kodDkM" onchange="dun(this.value);">
                                            <s:option value="0">Sila Pilih</s:option>
                                            <s:option value="N19">Kesidang</s:option>
                                            <s:option value="N20">Kota Laksamana</s:option>
                                            <s:option value="N21">Duyong</s:option>
                                            <s:option value="N22">Bandar Hilir</s:option>
                                            <s:option value="N23">Telok Mas</s:option>
                                        </s:select>--%>
                                    </td>
                                </tr>
                                <tr id="jS">
                                    <td>
                                        <font color="red" size="4">*</font>DUN :
                                    </td>
                                    <td>
                                        <s:select name="kodDjS" style="width:150px" value="" id="kodDjS" onchange="dun(this.value);">
                                            <s:option value="0">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiP139}" label="nama" value="kod"/>

                                        </s:select>
                                        <%--<s:select name="kodDjS" style="width:150px" value="" id="kodDjS" onchange="dun(this.value);">
                                            <s:option value="0">Sila Pilih</s:option>
                                            <s:option value="N24">Bemban</s:option>
                                            <s:option value="N25">Rim</s:option>
                                            <s:option value="N26">Serkam</s:option>
                                            <s:option value="N27">Merlimau</s:option>
                                            <s:option value="N28">Sungai Rambai</s:option>
                                        </s:select>--%>
                                    </td>
                                </tr>
                            </c:if>
                            <tr>
                                <td>
                                    <font color="red" size="4">*</font>Kedudukan Tanah :
                                </td>
                                <td>
                                    <s:textarea name="hakmilikPermohonan.lokasi" rows="5" cols="70" class="normal_text"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Muatnaik Gambar Tanah :
                                </td>
                                <td>
                                    <s:select name="hmImej" style="width:300px;" id="hmImej" onchange="doSetDokumenHakmilik();">
                                        <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                        <s:options-collection collection="${actionBean.hakmilikImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                                    </s:select>&nbsp;
                                    <s:hidden name="idDokumen" id="idDokumenH" />
                                    <s:button name="uploadDoc" id="display" value="Ubah Suai" class="btn" onclick="uploadForm('H');"/>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <br/>
                    <legend>
                        Permohonan Terdahulu
                    </legend>

                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.permohonanManualList}" pagesize="20" cellpadding="0" cellspacing="0" id="line1">
                            <s:hidden name="" class="${line1_rowNum -1}" value="${line1.idMohonManual}"/>
                            <display:column title="No">${line1_rowNum}</display:column>
                            <display:column title="ID Permohanan"  property="idPermohonan.idPermohonan"/>
                            <display:column title="No Fail"  property="noFail"/>
                            <%--<display:column title="Urusan" property="idPermohonan.kodUrusan.nama"/>
                            <display:column title="Status Keputusan" property="idPermohonan.keputusan.nama" />--%>
                        </display:table>
                        <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahPermohonanTerdahulu()"/>&nbsp;
                    </div>
                    <br/>
                </div>

            </fieldset>
        </div>
        <fieldset class="aras1">
            <table  width="100%" border="0">
                <tr>
                    <td align="center">
                        <s:submit name="simpanPerihal" value="Simpan" class="btn" onclick="NoPrompt();"/>
                        <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                    </td>
                </tr>
            </table>   
        </fieldset>
    </s:form>
</body>

