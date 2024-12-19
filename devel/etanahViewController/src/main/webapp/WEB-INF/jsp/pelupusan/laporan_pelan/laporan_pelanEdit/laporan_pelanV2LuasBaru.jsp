<%-- 
    Document   : laporan_pelanV2LuasBaru
    Created on : Feb 19, 2013, 1:33:12 PM
    Author     : afham
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
        <c:when test="${actionBean.hakmilikPermohonan.kodKawasanParlimen.kod ne null}">
                filterKodDun();
        </c:when>       
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
            var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelanV2?refreshpage&type='+type;
            window.open(url, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
    
        function refreshpage(){
            //        alert('aa');
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
        }
        
        function openLain_lainPengawal(val){
            if(val == '3'){
                $('#catatanPengawal').show();
            }else{
                $('#catatanPengawal').hide();
            }
        }
                
        //        function changeHideDun(value){
        //            // alert(value);
        //            $('#kodD').val("");
        //            if(value == 'P134'){
        //                $('#mT').show();
        //                $('#aG').hide();
        //                $('#tB').hide();
        //                $('#bK').hide();
        //                $('#kM').hide();
        //                $('#jS').hide();
        //            }else if(value == 'P135'){
        //                $('#aG').show();
        //                $('#mT').hide();
        //                $('#tB').hide();
        //                $('#bK').hide();
        //                $('#kM').hide();
        //                $('#jS').hide();
        //            }else if(value == 'P136'){
        //                $('#tB').show();
        //                $('#aG').hide();
        //                $('#mT').hide();
        //                $('#bK').hide();
        //                $('#kM').hide();
        //                $('#jS').hide();
        //            }else if(value == 'P137'){
        //                $('#bK').show();
        //                $('#tB').hide();
        //                $('#aG').hide();
        //                $('#mT').hide();
        //                $('#kM').hide();
        //                $('#jS').hide();
        //            }else if(value == 'P138'){
        //                $('#kM').show();
        //                $('#bK').hide();
        //                $('#tB').hide();
        //                $('#aG').hide();
        //                $('#mT').hide();
        //                $('#jS').hide();
        //            }else if(value == 'P139'){
        //                $('#jS').show();
        //                $('#kM').hide();
        //                $('#bK').hide();
        //                $('#tB').hide();
        //                $('#aG').hide();
        //                $('#mT').hide();
        //            }
        //        }
        
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
        
        function filterKodDun() {
            var kodPar = $("#kodPar").val();
            //        alert("hi");
            NoPrompt();
        
            $.post('${pageContext.request.contextPath}/pelupusan/laporan_pelanV2?senaraiKodDunByKodPar&kodPar=' + kodPar,
            function(data) {
                if (data != '') {
                    $('#partialKodDun').html(data);
                    //                    $.unblockUI();
                }
            }, 'html');

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
    <s:form beanclass="etanah.view.stripes.pelupusan.LaporanPelanV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="kodD" id="kodD"/>
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <s:hidden name="noPtSementara" id="noPtSementara"/>
        <s:hidden name="idLapor" id="idLapor" value="${actionBean.permohonanLaporanPelan.idLaporan}"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <div id="perihaltanah">
                    <legend>
                        Maklumat Luas Baru
                    </legend>
                    <table class="tablecloth" border="0" width="50%">
                        <tr>
                            <td>
                                Luas Baru :
                            </td>
                            <td>
                                <s:text name="hakmilikPermohonan.luasDiluluskan" formatPattern="0.0000" maxlength="15" onkeyup="validateNumber(this,this.value);" id="Luas"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Kod :
                            </td>
                            <td>
                                <s:select name="luasLulusUom.kod" value="${actionBean.hakmilikPermohonan.luasLulusUom.kod}" id="kULuas">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option value="H">Hektar</s:option>
                                    <s:option value="M">Meter Persegi</s:option>
                                </s:select>
                            </td>
                        </tr>
                    </table>
                    <fieldset class="aras1">
                        <table  width="100%" border="0">
                            <tr>
                                <td align="center">
                                    <s:submit name="simpanLuasBaru" value="Simpan" class="btn" onclick="NoPrompt();"/>
                                    <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                                    <%--<s:submit name="simpanLuasBaru" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>--%>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </div>
            </fieldset>
        </div>
    </s:form>
</body>
