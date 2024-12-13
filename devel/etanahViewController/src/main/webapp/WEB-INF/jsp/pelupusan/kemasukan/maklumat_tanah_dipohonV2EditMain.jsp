<%--
    Document   :  maklumat_tanah_dipohonV2EditMain(Editable).jsp
    Created on :  Feb 20, 2013, 05:12:13 PM
    Author     :  Shazwan
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KEMASUKAN MAKLUMAT TANAH</title>
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
        //maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});   
    }); //END OF READY FUNCTION
    
    function refreshpage2(type){
        //        alert(type);
        var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?refreshpage&type='+type;
        window.open(url, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
    
    
    function doSomething2() {
        var mpb = $('#mpb').val();
        var tempat = $('#tempat').val();
        var Luas = $('#Luas').val();
        var kd = $('#kd').val();
        var kULuas = $('#kULuas').val();
        var kodlot = $('#kodlot').val();
        var noLot = $('#noLot').val();
        var Jarak = $('#Jarak').val();
        var uJarak = $('#uJarak').val();
        var jarakDari = $('#jarakDari').val();
        var katTanahPilihan = $('#_kodKatTanah').val();
        var kodSeksyen = $('#_kodSeksyen2').val();
        alert(mpb);
   
        var url = '${pageContext.request.contextPath}/pelupusan/maklumat_tanahV3?carianIndependent&katTanahPilihan='
            +katTanahPilihan+'&mpb='+mpb+'&tempat='+tempat+'&Luas='+Luas+'&kULuas='+kULuas+'&kodlot='+kodlot+'&noLot='+noLot+'&Jarak='+Jarak+'&uJarak='+uJarak+'&jarakDari='+jarakDari+'&kodSeksyen='+kodSeksyen;
        $.get(url,
        function(data){
            alert(${actionBean.forSeksyen});
            $('#page_div').html(data);
        },'html');
        //        $.ajax({
        //            url:url,
        //            success:function(data){                
        //                $('#page_div').html(data);
        //            }
        //        });
    }
    
    function refreshpage(){
        //        alert('aa');
        NoPrompt();
        opener.refreshV2('main');
        self.close();
    }
    
    function tutupPapar(){
        //        alert('aa');
        NoPrompt();
        opener.tutupPapar();
        self.close();
    }
    
    function tutup(){
        NoPrompt();
        self.close();
    }
        
    function CurrencyFormatted(amount)
    {
        var i = parseFloat(amount);
        if(isNaN(i)) { i = 0.00; }
        var minus = '';
        if(i < 0) { minus = '-'; }
        i = Math.abs(i);
        i = parseInt((i + .005) * 100);
        i = i / 100;
        s = new String(i);
        if(s.indexOf('.') < 0) { s += '.00'; }
        if(s.indexOf('.') == (s.length - 2)) { s += '0'; }
        s = minus + s;
        return s;
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
    <s:form beanclass="etanah.view.stripes.pelupusan.maklumat_tanah.MaklumatTanahV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="idMH" id="idMH"/>
        <s:hidden name="type" id="type"/>
        <div class="subtitle">
            <c:choose>
                <c:when test="${edit}">
                    <fieldset class="aras1">
                        <div id="perihaltanah">
                            <legend>
                                KEMASUKAN MAKLUMAT TANAH
                            </legend>
                        </div>
                        <c:choose>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG'}">
                                <c:import url="kemasukanEdit/kemasukanLMCRG.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PJLB' }">
                                <c:import url="kemasukanEdit/kemasukanPJLB.jsp"></c:import>
                                <!-- TO DO -->
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'MPJLB' }">
                                <!-- TO DO -->
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB' }">
                                <c:import url="kemasukanEdit/kemasukanBatuan.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTG' }">
                                <c:import url="kemasukanEdit/kemasukanBatuan.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMMK' }">
                                <c:import url="kemasukanEdit/kemasukanPBMMK.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTD' }">
                                <c:import url="kemasukanEdit/kemasukanBatuan.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PLPTD' }">
                                <c:import url="kemasukanEdit/kemasukanPLPTD.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PRIZ' }">
                                <c:import url="kemasukanEdit/kemasukanPRIZ.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'BMRE' }">
                                <c:import url="kemasukanEdit/kemasukanPRIZ.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBRZ' }">
                                <c:import url="kemasukanEdit/kemasukanPRIZ.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'RAYT' }">
                                <!-- TO DO -->
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR' }">
                                <c:import url="kemasukanEdit/kemasukanPPTR.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU' }">
                                <c:import url="kemasukanEdit/kemasukanPPRU.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PJBTR' }">
                                <!-- TO DO -->
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT' }">
                                <c:import url="kemasukanEdit/kemasukanPBMT.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTBTS' }">
                                <c:import url="kemasukanEdit/kemasukanPTBT.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTBTC' }">
                                <c:import url="kemasukanEdit/kemasukanPTBT.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT' }">
                                <c:import url="kemasukanEdit/kemasukanPTBT.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS' }">
                                <c:import url="kemasukanEdit/kemasukanPLPS.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'RLPS' }">
                                <c:import url="kemasukanEdit/kemasukanRLPS.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'RLPSK' }">
                                <!-- TO DO -->
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP' }">
                                <c:import url="kemasukanEdit/kemasukanLPSP.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PRMP' }">
                                <!-- TO DO -->
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'MCMCL' }">
                                <!-- TO DO -->
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'MMMCL' }">
                                <!-- TO DO -->
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PJTK' }">
                                <c:import url="kemasukanEdit/kemasukanPJTK.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PWGSA' }">
                                <c:import url="kemasukanEdit/kemasukanPWGSA.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA' }">
                                <c:import url="kemasukanEdit/kemasukanPWGSA.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBGSA' }">
                                <c:import url="kemasukanEdit/kemasukanPBGSA.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PRMMK' }">
                                <c:import url="kemasukanEdit/kemasukanPRIZ.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'WMRE' }">
                                <c:import url="kemasukanEdit/kemasukanPRIZ.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PCRG'}">
                                <c:import url="kemasukanEdit/kemasukanPJTK.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LPJH'}">
                                <c:import url="kemasukanEdit/kemasukanPJTK.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LSTP' }">
                                <c:import url="kemasukanEdit/kemasukanPJLB.jsp"></c:import>
                                <!-- TO DO -->
                            </c:when>
                        </c:choose>
                    </fieldset>
                </c:when>
                <c:when test="${!edit}">
                    <fieldset class="aras1">
                        <div id="perihaltanah">
                            <legend>
                                PAPARAN MAKLUMAT TANAH
                            </legend>
                        </div>
                        <c:choose>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG' }">
                                <c:import url="kemasukanView/kemasukanLMCRG.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PJLB' }">
                                <!-- TO DO -->
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'MPJLB' }">
                                <!-- TO DO -->
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB' }">
                                <c:import url="kemasukanView/kemasukanBatuan.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTG' }">
                                <c:import url="kemasukanView/kemasukanBatuan.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTD' }">
                                <c:import url="kemasukanView/kemasukanBatuan.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMMK' }">
                                <c:import url="kemasukanView/kemasukanPBMMK.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PLPTD' }">
                                <c:import url="kemasukanView/kemasukanPLPTD.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PRIZ' }">
                                <c:import url="kemasukanView/kemasukanPRIZ.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'WMRE' }">
                                <c:import url="kemasukanView/kemasukanPRIZ.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBRZ' }">
                                <!-- TO DO -->
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'RAYT' }">
                                <!-- TO DO -->
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR' }">
                                <c:import url="kemasukanView/kemasukanPPTR.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU' }">
                                <c:import url="kemasukanView/kemasukanPPRU.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PJBTR' }">
                                <!-- TO DO -->
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT' }">
                                <c:import url="kemasukanView/kemasukanPBMT.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTBTS' }">
                                <c:import url="kemasukanView/kemasukanPTBT.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTBTC' }">
                                <c:import url="kemasukanView/kemasukanPTBT.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT' }">
                                <c:import url="kemasukanView/kemasukanPTBT.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS' }">
                                <c:import url="kemasukanView/kemasukanPLPS.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'RLPS' }">
                                <c:import url="kemasukanView/kemasukanRLPS.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'RLPSK' }">
                                <!-- TO DO -->
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP' }">
                                <c:import url="kemasukanView/kemasukanLPSP.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PRMP' }">
                                <!-- TO DO -->
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'MCMCL' }">
                                <!-- TO DO -->
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'MMMCL' }">
                                <!-- TO DO -->
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PJTK' }">
                                <c:import url="kemasukanView/kemasukanPJTK.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PWGSA' }">
                                <c:import url="kemasukanView/kemasukanPWGSA.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA' }">
                                <c:import url="kemasukanView/kemasukanPWGSA.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBGSA' }">
                                <c:import url="kemasukanView/kemasukanPBGSA.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PRMMK' }">
                                <c:import url="kemasukanView/kemasukanPRIZ.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PCRG'}">
                                <c:import url="kemasukanView/kemasukanPJTK.jsp"></c:import>
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LPJH'}">
                                <c:import url="kemasukanView/kemasukanPJTK.jsp"></c:import>
                            </c:when>
                        </c:choose>
                    </fieldset>
                </c:when>
            </c:choose>
        </div>
    </s:form>
</body>


