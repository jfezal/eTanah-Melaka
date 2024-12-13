<%-- 
    Document   : laporan_tanah
    Created on : June 15, 2011, 2:56:40 PM
    Author     : Shazwan & Afham
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<%-- carian hakmilik start--%>

<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<%-- carian hakmilik end--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    $(document).ready(function() {
        // carian hakmilik start
        dialogInit('Carian Hakmilik'),$('#catatanKegunaan').hide();
    <%--<c:forEach var="i" begin="1" end="${actionBean.bilHakmilik}" >--%>
            $("#hakmilik${i - 1}").change(function(){validateHakmilik(${i - 1});});
            $("#hakmilik${i - 1}").keyup(function(){
                closeDialog();
            });
    <%--</c:forEach>--%>
            $('input:text').each(function(){
                $(this).focus(function() { $(this).addClass('focus')});
                $(this).blur(function() { $(this).removeClass('focus')});
            });
            //  });
            //carian hakmilik end
            /*
             * HIDE NGAN SHOW FIELD JSP
             */
            
            //$('#perihaltanah').hide();
            //$('#sempadan').hide();
            //$('#keadaantanah').hide();              
            //$('#lotsempadan').hide();
            
            /*
             *END
             */
            if('${actionBean.catatanKeg}'!=null)
            $('#catatanKegunaan').show();
       
            if('${actionBean.permohonan.kodUrusan.kod}' == 'PLPS' || '${actionBean.permohonan.kodUrusan.kod}' == 'PRMP'){
            
                if('${actionBean.ksn}'!=null&&'${actionBean.ksn}'!=''){
                
                    if('${actionBean.ksn}'=='SL'){
                        $('#plpssokong').show();
                        $('#plpstidaksokong').hide();
                    }
                    else if('${actionBean.ksn}'=='ST'){
                        $('#plpssokong').hide();
                        $('#plpstidaksokong').show();
                    }
                }else{
                    $('#plpssokong').hide();
                    $('#plpstidaksokong').hide();
                }
           
            }
            if('${actionBean.permohonan.kodUrusan.kod}' == 'PRMP'){
            
                if('${actionBean.ksn}'!=null&&'${actionBean.ksn}'!=''){
                
                    if('${actionBean.ksn}'=='SL'){
                        $('#prmpsokong').show();
                        $('#prmptidaksokong').hide();
                    }
                    else if('${actionBean.ksn}'=='ST'){
                        $('#prmpsokong').hide();
                        $('#prmptidaksokong').show();
                    }
                }else{
                    $('#prmpsokong').hide();
                    $('#prmptidaksokong').hide();
                }
           
            }
            if('${actionBean.permohonan.kodUrusan.kod}' == 'LPSP'){
            
                if('${actionBean.ksn}'!=null&&'${actionBean.ksn}'!=''){
                
                    if('${actionBean.ksn}'=='SL'){
                        $('#lpspsokong').show();
                        $('#lpsptidaksokong').hide();
                    }
                    else if('${actionBean.ksn}'=='ST'){
                        $('#lpspsokong').hide();
                        $('#lpsptidaksokong').show();
                    }
                }else{
                    $('#lpspsokong').hide();
                    $('#lpsptidaksokong').hide();
                }
           
            }
        
            if('${actionBean.permohonan.kodUrusan.kod}' == 'PPBB' || '${actionBean.permohonan.kodUrusan.kod}' == 'PBPTG' || '${actionBean.permohonan.kodUrusan.kod}' == 'PBPTD'){
            
                if('${actionBean.ksn}'!=null&&'${actionBean.ksn}'!=''){
                
                    if('${actionBean.ksn}'=='SL'){
                        $('#batuansokong').show();
                        $('#batuantidaksokong').hide();
                    }
                    else if('${actionBean.ksn}'=='ST'){
                        $('#batuansokong').hide();
                        $('#batuantidaksokong').show();
                    }
                }else{
                    $('#batuansokong').hide();
                    $('#batuantidaksokong').hide();
                }
           
            }
    <c:if test="${actionBean.kodNegeri eq '05'}">
    
            if('${actionBean.permohonan.kodUrusan.kod}' == 'PBMT'){
                if('${actionBean.ksn}'!=null&&'${actionBean.ksn}'!=''){
                
                    if('${actionBean.ksn}'=='SL'){
                        document.form.ksn[0].checked=true;
                        document.form.ksn[1].checked=false;
                        document.form.ksn[2].checked=false;
                        showjikasokong();
                    }
                    else if('${actionBean.ksn}'=='ST'){
                        document.form.ksn[0].checked=false;
                        document.form.ksn[1].checked=false;
                        document.form.ksn[2].checked=true;
                        showtidaksokong();
                    }
                    else if('${actionBean.ksn}'=='SU'){
                        document.form.ksn[0].checked=false;
                        document.form.ksn[1].checked=true;
                        document.form.ksn[2].checked=false;
                        showpbmtsyorlulus();
                    }
                }else{
                    document.form.ksn[0].checked=false;
                    document.form.ksn[1].checked=false;
                    document.form.ksn[2].checked=false;
                }
           
            }
    </c:if>
        
        
        
        
            $('#jenisRizab').hide();
            $('#jenisRizabno').hide();
            $('#carianHakmilik').hide();
            $('#tanahkerajaan').hide();
            $('#jikasebab').hide();
            $('#pengusuka').hide();
            $('#tarikh').hide();
            $('#laterbelaktable').hide();
            $('#Bangunantable').hide();
            $('#pbmtsyorlulus').hide();
            //        $('#plpstidaksokong').hide();
            //        $('#plpssokong').hide();
            $('#mT').hide();
            $('#aG').hide();
            $('#tB').hide();
            $('#bK').hide();
            $('#kM').hide();
            $('#jS').hide();
            $('#premiumTxt').hide();
            $('#jenistanahlainlain').hide();
            $('#keadaantanahlainlain').hide();

            var mh = '${actionBean.hakmilikPermohonan.kodMilik.kod}';
            var lt = '${actionBean.laporanTanah.bolehBerimilik}';

            if(mh == 'K'){
                $('#tanahkerajaan').show();
                $('#jikasebab').hide();
            }else{
                $('#tanahkerajaan').hide();
                $('#jikasebab').hide();
            }
            if((mh == 'K') && (lt == 'T')){
                $('#jikasebab').show();
            }else{
                $('#jikasebab').hide();
            }
            if(mh == 'H'){
                $('#carianHakmilik').show();
                $('#jikasebab').hide();
            }else {
                $('#carianHakmilik').hide();            
            }

            if(mh == 'R'){
                $('#jenisRizab').show();
                $('#jenisRizabno').show();
                $('#jikasebab').hide();
            }else{
                $('#jenisRizab').hide();
                $('#jenisRizabno').hide();
            }

            // Kawasan Parlimen && Dun
            var mhk = '${actionBean.hakmilikPermohonan.kodKawasanParlimen.kod}';
            var dun = '${actionBean.hakmilikPermohonan.kodDUN.kod}';
            // alert(mhk);
            // alert(dun);
            if(mhk == 'P134'){
                $('#kodDmT').val(dun);
                $('#mT').show();
                $('#aG').hide();
                $('#tB').hide();
                $('#bK').hide();
                $('#kM').hide();
                $('#jS').hide();
            }else if(mhk == 'P135'){
                $('#kodDaG').val(dun);
                $('#aG').show();
                $('#mT').hide();
                $('#tB').hide();
                $('#bK').hide();
                $('#kM').hide();
                $('#jS').hide();
            }else if(mhk == 'P136'){
                $('#kodDtB').val(dun);
                $('#tB').show();
                $('#aG').hide();
                $('#mT').hide();        
                $('#bK').hide();
                $('#kM').hide();
                $('#jS').hide();
            }else if(mhk == 'P137'){
                $('#kodDbK').val(dun);
                $('#bK').show();
                $('#tB').hide();
                $('#aG').hide();
                $('#mT').hide();        
                $('#kM').hide();
                $('#jS').hide();
            }else if(mhk == 'P138'){
                $('#kodDkM').val(dun);
                $('#kM').show();
                $('#bK').hide();
                $('#tB').hide();
                $('#aG').hide();
                $('#mT').hide();        
                $('#jS').hide();
            }else if(mhk == 'P139'){
                $('#kodDjS').val(dun);
                $('#jS').show();
                $('#kM').hide();
                $('#bK').hide();
                $('#tB').hide();
                $('#aG').hide();
                $('#mT').hide();        
            }else{
                $('#jS').hide();
                $('#kM').hide();
                $('#bK').hide();
                $('#tB').hide();
                $('#aG').hide();
                $('#mT').hide(); 
            }


            var ltu = '${actionBean.laporanTanah.usaha}';
            if(ltu == 'Y'){
                $('#pengusuka').show();
                $('#tarikh').show();
                $('#laterbelaktable').show();
            }else{
                $('#pengusuka').hide();
                $('#tarikh').hide();
                $('#laterbelaktable').hide();
            }
            var fm = '${actionBean.fasaPermohonan.keputusan.kod}';
            var kodurusan = '${actionBean.permohonan.kodUrusan.kod}';
            //alert(fm);
            if(kodurusan == 'PBMT'){
                //if(fm == 'DI'){
                if(fm == 'SL'){
                    $('#jikasokong').show();
                    $('#tidaksokong').hide();
                    $('#pbmtsyorlulus').hide();
                }
                //else if(fm == 'TI'){
                else if(fm == 'ST'){
                    $('#tidaksokong').show();
                    $('#jikasokong').hide();
                    $('#pbmtsyorlulus').hide();
                }
                //else if(fm == 'SL'){
                else if(fm == 'SU'){
                    $('#pbmtsyorlulus').show();
                    $('#tidaksokong').hide();
                    $('#jikasokong').hide();
        
                }
                else {
                    $('#tidaksokong').hide();
                    $('#jikasokong').hide();
                    $('#pbmtsyorlulus').hide();
                }
            }
            var jenistanah = '${actionBean.permohonanLaporanPelan.kodTanah.kod}';

            if(jenistanah == '99'){
                $('#jenistanahlainlain').show();
            }else{
                $('#jenistanahlainlain').hide();
            }

            var keadaantanah = '${actionBean.laporanTanah.kodKeadaanTanah.kod}';
            //alert(keadaantanah);

            if(keadaantanah == 'LL'){
                $('#keadaantanahlainlain').show();
            }else{
                $('#keadaantanahlainlain').hide();
            }
           
    
            var adbangunan = '${actionBean.laporanTanah.adaBangunan}';
            if(adbangunan == 'Y'){
                $('#Bangunantable').show();
            }else{
                $('#Bangunantable').hide();
            }
        
            if($('#adaBangunan').val() == 'Y')
                $('#bilanganBangunan').show();

            var v = '${actionBean.laporanTanah.kecerunanTanah.kod}';

            if(v == 'RT'){
                $('#tinggi').hide();
                $('#cerun').hide();
                $('#dalam').hide();
            }

            else if(v == 'BK'){
                $('#tinggi').hide();
                $('#cerun').show();
                $('#dalam').hide();
            }

            else if(v == 'TG'){
                $('#tinggi').show();
                $('#cerun').hide();
                $('#dalam').hide();
            }

            else if(v == 'RD'){
                $('#tinggi').hide();
                $('#cerun').show();
                $('#dalam').hide();
            }

            else if(v == 'CR'){
                $('#tinggi').hide();
                $('#cerun').show();
                $('#dalam').hide();
            }

            else if(v == 'PY'){
                $('#tinggi').hide();
                $('#cerun').hide();
                $('#dalam').show();
            }
            $('#jenisRizab1').hide();
            $('#noWarta').hide();

            $('#nyataRancangan').hide();
            $('#tanahMilik').hide();
            $('#tujuan').hide();

            $('#catatanTanahMilik').hide();
            $('#catatanPBT').hide();

        });



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
   
        function doViewReport(rowNo) {
            var idDokumen = document.getElementById("imej_"+rowNo).options[document.getElementById("imej_"+rowNo).selectedIndex].value;
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        function doViewReportUtara() {
            var idDokumen = document.getElementById("utaraImej").options[document.getElementById("utaraImej").selectedIndex].value;
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        function doViewReportSelatan() {
            var idDokumen = document.getElementById("selatanImej").options[document.getElementById("selatanImej").selectedIndex].value;
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        function doViewReportTimur() {
            var idDokumen = document.getElementById("timurImej").options[document.getElementById("timurImej").selectedIndex].value;
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        function doViewReportBarat() {
            var idDokumen = document.getElementById("baratImej").options[document.getElementById("baratImej").selectedIndex].value;
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        function doView(idDokumen) {
            // alert(idDokumen);
            // var idDokumen = document.getElementById("imej_"+rowNo).options[document.getElementById("imej_"+rowNo).selectedIndex].value;
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }

        // GIS

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

    <%--alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);--%>
            strNama = ReplaceAll(strNama," ","_");
            strJawatan = ReplaceAll(strJawatan," ","_");
            var stageId = "gis_lim";
    <%--        alert("nama:" + strNama);
            alert ("jawatan:" + strJawatan);
            alert ("stageid:" + stageId);--%>
                    var objShell = new ActiveXObject("WScript.Shell");
                    var sysVar = objShell.Environment("System");
                    //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
                    //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")
                    objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + stageId);
                }
                function doSetDokumenUtara(){
                    var idDokumen = document.getElementById("utaraImej").options[document.getElementById("utaraImej").selectedIndex].value;
                    var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
                    window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
                }

                function doSetDokumenTimur(){
                    var idDokumen = document.getElementById("timurImej").options[document.getElementById("timurImej").selectedIndex].value;
                    var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
                    window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
                }
                function doSetDokumenBarat(){
                    var idDokumen = document.getElementById("baratImej").options[document.getElementById("baratImej").selectedIndex].value;
                    var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
                    window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
                }
                function doSetDokumenSelatan(){
                    var idDokumen = document.getElementById("selatanImej").options[document.getElementById("selatanImej").selectedIndex].value;
                    var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
                    window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
                }
        
                function viewPerihalTanah(){
                    $('#perihaltanah').show();
                }
                function viewSempadan(){
                    $('#sempadan').show();
                }
                function  viewKeadaanTanah(){
                    $('#keadaantanah').show();
                }
                function  viewLotSempadan(){
                    $('#lotsempadan').show();
                }
                function  viewDalamKawasan(){
                    $('#dalamkawasan').show();
                }
        
                function reload (val) {
                    doBlockUI();
                    var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanahGSA?reload&idHakmilik=' + val;
                    $.ajax({
                        type:"GET",
                        url : url,
                        dataType : 'html',
                        error : function (xhr, ajaxOptions, thrownError){
                            alert("error=" + xhr.responseText);
                            doUnBlockUI();
                        },
                        success : function(data){
                            $('#page_div').html(data);
                            doUnBlockUI();
                        }
                    });
                }
        
                function updateLaporanTanah (val,stat) {
                    /*
                     * LEGEND
                     * 1=PERIHAL TANAH
                     */
                    doBlockUI();
                    var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanahGSA?reload&idHakmilik=' + val;
                    $.ajax({
                        type:"GET",
                        url : url,
                        dataType : 'html',
                        error : function (xhr, ajaxOptions, thrownError){
                            alert("error=" + xhr.responseText);
                            doUnBlockUI();
                        },
                        success : function(data){
                            $('#page_div').html(data);
                            doUnBlockUI();
                        }
                    });
                }
                function openFrame(type){
                    doBlockUI();
                    var idHakmilik = $('#idHakmilik').val();
                    //        alert(idHakmilik);
                    window.open("${pageContext.request.contextPath}/pelupusan/laporan_tanahGSA?openFrame&idHakmilik="
                        +idHakmilik+"&type="+type, "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
                    //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
                }
        
                function refreshGSA(type){
                        var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanahGSA?refreshpage&type='+type;
                        $.get(url,
                        function(data){
                            $('#page_div').html(data);
                        },'html');
                        doUnBlockUI();
                }

</script>

<s:form beanclass="etanah.view.stripes.pelupusan.LaporanTanahGSAPelupusanActionBean" name="form">
    <s:errors/>
    <s:messages/>
    <s:hidden name="checkTanahExist" value="${actionBean.checkTanahExist}"/>
    <s:hidden name="permohonan.idPermohonan"/>
    <s:hidden name="display"/>
    <s:hidden name="idHakmilik" id="idHakmilik"/>    
    <s:hidden name="kodD" id="kodD"/>
    <%--<s:text name="edit" id="edit"/>--%>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Senarai Hakmilik Terlibat</legend>
            <div align="center">
                <c:if test="${fn:length(actionBean.hakmilikPermohonanList) > 1}">
                    <p>
                        <font size="2" color="red">
                            <b>Permohonan Melibatkan Banyak Hakmilik</b>
                        </font>
                    </p>
                </c:if>
            </div>
            <div align="center">          
                <font color="#003194" style="font-weight: bold; font-family:Tahoma; font-size: 13px;">
                    Hakmilik :
                </font>
                <c:if test="${edit}">
                    <s:select name="idHakmilik" onchange="reload(this.value);" id="hakmilik">
                        <c:forEach items="${actionBean.senaraiHakmilikPermohonan}" var="item" varStatus="line">
                            <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                                ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                            </s:option>
                        </c:forEach>
                    </s:select>
                </c:if>
                <c:if test="${!edit}">
                    <s:select name="idHakmilik" onchange="reload(this.value);" id="hakmilik">
                        <c:forEach items="${actionBean.senaraiHakmilikPermohonan}" var="item" varStatus="line">
                            <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                                ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                            </s:option>
                        </c:forEach>
                    </s:select>
                </c:if>
            </div>            
        </fieldset>
        <br/>
        <fieldset class="aras1">
            <legend>Perihal Tanah 
                <span style="float:right">
                    <%--<a onclick="viewPerihalTanah();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%> 
                    <a onclick="openFrame('pTanah');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                </span>
            </legend>
            <div id="perihaltanah">
                <div class="content" align="center">
                    Tanah Kerajaan
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="/pelupusan/laporan_tanahGSA">
                        <display:column title="No" sortable="true">${line_rowNum}
                            <s:hidden name="x" class="x${line_rowNum -1}" value="${line.hakmilikPermohonan.id}"/></display:column>
                        <display:column title="ID Hakmilik" >${line.hakmilikPermohonan.hakmilik.idHakmilik}</display:column>    
                        <display:column title="No.Lot/PT" >${line.hakmilikPermohonan.noLot}</display:column>
                        <display:column title="Bandar/Pekan/Mukim" property="hakmilikPermohonan.bandarPekanMukimBaru.nama"/>
                        <display:column title="Seksyen" property="hakmilikPermohonan.kodSeksyen.nama" /><%----%><%--<s:select name="kodSeksyen.nama" value="${actionBean.hakmilikPermohonan.kodSeksyen.kod}">
                                <s:option value="0">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.kodSeksyenList}" label="nama" value="kod" /></s:select></display:column>--%>
                        <display:column title="Daerah" property="hakmilikPermohonan.bandarPekanMukimBaru.daerah.nama"/>
                        <display:column title="Luas Dipohon"><s:format formatPattern="#,###,##0.0000" value="${line.hakmilikPermohonan.luasTerlibat}"/> ${line.hakmilikPermohonan.kodUnitLuas.nama} </display:column>
                    </display:table>

                    <br>
                    <table class="tablecloth" border="0">
                        <tr>
                            <td>
                                Status Tanah :
                            </td>
                            <td>
                                ${actionBean.hakmilikPermohonan.kodMilik.nama}&nbsp;
                            </td>
                        </tr>
                        <c:if test="${actionBean.hakmilikPermohonan.kodMilik ne null}">
                            <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'K'}">
                                <tr>
                                    <td>
                                        Tanah Kerajaan Boleh diberimilik :
                                    </td>
                                    <td>
                                        <c:if test="${actionBean.laporanTanah.bolehBerimilik eq 'Y'}">
                                            Ya &nbsp;
                                        </c:if>
                                        <c:if test="${actionBean.laporanTanah.bolehBerimilik eq 'T'}">
                                            Tidak &nbsp;
                                        </c:if>
                                    </td>
                                </tr>
                                <c:if test="${actionBean.laporanTanah.bolehBerimilik eq 'T'}">
                                    <tr>
                                        <td>
                                            Jika Tidak Boleh diberimilik, sebab :
                                        </td>
                                        <td>
                                            ${actionBean.laporanTanah.sebabTidakBolehBerimilik}&nbsp;
                                        </td>
                                    </tr>
                                </c:if>                            
                            </c:if>
                        </c:if>
                        <c:if test="${actionBean.hakmilikPermohonan.kodMilik ne null}">
                            <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'H'}">
                                <tr>
                                    <td colspan="2">


                                        <display:table  name="${actionBean.permohonan.senaraiHakmilik}" id="line" class="tablecloth">
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

                                            <display:column property="hakmilik.kategoriTanah.nama" title="Kegunaan" >
                                                <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot ne null}"> ${actionBean.laporanTanah.sempadanUtaraNoLot}&nbsp; </c:if>
                                                <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot eq null}"> Tiada Data </c:if>
                                            </display:column>

                                            <display:column title="Cukai (RM)">
                                                <c:if test="${line.hakmilik.cukai ne null}"> <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukai}"/>&nbsp; </c:if>
                                                <c:if test="${line.hakmilik.cukai eq null}"> Tiada Data </c:if>
                                            </display:column>
                                        </display:table>      
                                    </td>
                                </tr>
                            </c:if>
                        </c:if>
                        <c:if test="${actionBean.hakmilikPermohonan.kodMilik ne null}">
                            <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'R'}">
                                <tr>
                                    <td>
                                        Jenis Rizab :
                                    </td>
                                    <td>
                                        ${actionBean.tanahrizabpermohonan1.rizab.nama} &nbsp;
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        No. Warta Kerajaan :
                                    </td>
                                    <td>
                                        ${actionBean.tanahrizabpermohonan1.noWarta}&nbsp;
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Jenis Tanah :
                                    </td>
                                    <td>
                                        ${actionBean.permohonanLaporanPelan.kodTanah.nama} &nbsp;
                                    </td>
                                </tr> 
                            </c:if>                            
                        </c:if>
                        <c:if test="${actionBean.permohonanLaporanPelan.kodTanah ne null}">
                            <c:if test="${actionBean.permohonanLaporanPelan.kodTanah.kod eq '99'}">
                                <tr>
                                    <td>Lain-lain :
                                    </td>
                                    <td>${actionBean.kand}&nbsp;
                                    </td>
                                </tr>
                            </c:if>
                        </c:if>
                        <c:if test="${actionBean.kodNegeri eq '04'}">
                            <tr>
                                <td>
                                    Kawasan Parlimen :
                                </td>
                                <td>
                                    ${actionBean.hakmilikPermohonan.kodDUN.nama}&nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    DUN :
                                </td>
                                <td>
                                    ${actionBean.hakmilikPermohonan.kodKawasanParlimen.nama}&nbsp;
                                </td>
                            </tr>
                        </c:if>
                        <tr>
                            <td>
                                Kedudukan Tanah :
                            </td>
                            <td>
                                ${actionBean.hakmilikPermohonan.lokasi}&nbsp;
                            </td>
                        </tr>
                    </table>
                    <br>
                    <legend>
                        Permohonan Terdahulu
                    </legend>
                    <display:table class="tablecloth" name="${actionBean.permohonanManualList}" pagesize="20" cellpadding="0" cellspacing="0" id="line1">
                        <s:hidden name="" class="${line1_rowNum -1}" value="${line1.idMohonManual}"/>
                        <display:column title="No">${line1_rowNum}</display:column>
                        <display:column title="ID Permohanan/No Fail"  property="idPermohonan.idPermohonan"/>
                        <display:column title="Urusan" property="idPermohonan.kodUrusan.nama"/>
                        <display:column title="Status Keputusan" property="idPermohonan.keputusan.nama" />
                        <c:if test="${actionBean.display eq 'false'}">
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line1_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removePermohonanTerdahulu('${line1.idMohonManual}')"/>
                                </div>
                            </display:column></c:if>
                    </display:table>
                </div>
                <br/>
            </div>
        </fieldset>
    </div>
    <br/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Bersempadan
                <span style="float:right">
                    <%-- <a onclick="viewSempadan();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%> 
                    <a onclick="openFrame('sempadan');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                </span>
            </legend>
            <div id="sempadan">
                <br>
                <div class="content" align="center">

                    <table class="tablecloth">
                        <tr>
                            <th>Bersempadan</th><th>Nama</th><th>Jarak (KM)</th>
                        </tr>
                        <tr>
                            <td>
                                Jalan Raya
                            </td>                        
                            <td>
                                ${actionBean.laporanTanah.namaSempadanJalanraya}
                            </td>
                            <td>
                                ${actionBean.laporanTanah.jarakSempadanJalanraya}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Landasan Keretapi
                            </td>
                            <td> 
                                ${actionBean.laporanTanah.namaSempadanKeretapi}
                            </td>
                            <td>
                                ${actionBean.laporanTanah.jarakSempadanKeretapi}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Laut
                            </td>
                            <td>
                                ${actionBean.laporanTanah.namaSempadanLaut}
                            </td>
                            <td>
                                ${actionBean.laporanTanah.jarakSempadanLaut}
                            </td>
                        </tr><tr>
                            <td>
                                Sungai
                            </td>
                            <td>
                                ${actionBean.laporanTanah.namaSempadanSungai}
                            </td>
                            <td>
                                ${actionBean.laporanTanah.jarakSempadanSungai}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Lain-lain
                            </td>
                            <td>
                                ${actionBean.laporanTanah.namaSempadanlain}
                            </td>
                            <td>
                                ${actionBean.laporanTanah.jarakSempadanLain}
                            </td>
                        </tr>
                    </table>
                    <br>
                    <table class="tablecloth">
                        <tr>
                            <td>
                                Jenis Jalan :
                            </td>
                            <td>
                                ${actionBean.laporanTanah.jenisJalan}&nbsp;
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Jalan Masuk :
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.adaJalanMasuk eq 'Y'}">
                                    Ada &nbsp;
                                </c:if>
                                <c:if test="${actionBean.laporanTanah.adaJalanMasuk eq 'T'}">
                                    Tiada &nbsp;
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Catatan :
                            </td>
                            <td>
                                ${actionBean.laporanTanah.catatanJalanMasuk}&nbsp;
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </fieldset>
    </div>
    <br/>        
    <div class="subtitle">
        <fieldset class="aras1">
            <s:hidden name="laporanTanah.idLaporan"/>
            <s:hidden name="laporanTanah.permohonan.idPermohonan"/>
            <legend>Keadaan Tanah
                <span style="float:right">
                   <%-- <a onclick="viewKeadaanTanah();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                    <a onclick="openFrame('kTanah');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                </span>
            </legend>
            <div id="keadaantanah" align="center">
                <table class="tablecloth" align="center">
                    <c:if test="${actionBean.kodNegeri eq '05'}">
                        <tr>
                            <td>
                                Jarak:
                            </td>
                            <td>
                                ${actionBean.hakmilikPermohonan.jarak} <c:if test="${actionBean.hakmilikPermohonan.unitJarak.kod eq 'KM'}">Kilometer</c:if><c:if test="${actionBean.hakmilikPermohonan.unitJarak.kod eq 'JM'}">Meter</c:if>
                                &nbsp;<font color="#003194"><b>dari</b></font>&nbsp; ${actionBean.hakmilikPermohonan.jarakDari} 
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Jarak Dari Kediaman:
                            </td>
                            <td>
                                ${actionBean.hakmilikPermohonan.jarakDariKediaman} <c:if test="${actionBean.hakmilikPermohonan.jarakDariKediamanUom.kod eq 'KM'}">Kilometer</c:if><c:if test="${actionBean.hakmilikPermohonan.jarakDariKediamanUom.kod eq 'JM'}">Meter</c:if>
                                </td>
                            </tr>
                    </c:if>
                    <tr>
                        <td>
                            Kecerunan Tanah :
                        </td>
                        <td>
                            ${actionBean.laporanTanah.kecerunanTanah.nama}&nbsp;
                        </td>
                    </tr>
                    <c:if test="${actionBean.laporanTanah.kecerunanTanah ne null}">
                        <c:if test="${actionBean.laporanTanah.kecerunanTanah.kod eq 'TG'}">
                            <tr>
                                <td>Ketinggian Dari Paras Jalan (m) :
                                </td>
                                <td>${actionBean.laporanTanah.ketinggianDariJalan}&nbsp;
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${actionBean.laporanTanah.kecerunanTanah.kod eq 'RD'}">
                            <tr>
                                <td>Darjah Kecerunan :
                                </td>
                                <td>${actionBean.laporanTanah.kecerunanBukit}&nbsp;
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${actionBean.laporanTanah.kecerunanTanah.kod eq 'CR'}">
                            <tr>
                                <td>Darjah Kecerunan :
                                </td>
                                <td>${actionBean.laporanTanah.kecerunanBukit}&nbsp;
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${actionBean.laporanTanah.kecerunanTanah.kod eq 'BK'}">
                            <tr>
                                <td>Darjah Kecerunan :
                                </td>
                                <td>${actionBean.laporanTanah.kecerunanBukit}&nbsp;
                                </td>
                            </tr>
                        </c:if>    
                        <c:if test="${actionBean.laporanTanah.kecerunanTanah.kod eq 'PY'}">
                            <tr>
                                <td>Dalam Paras Air (m) :
                                </td>
                                <td>${actionBean.laporanTanah.parasAir}&nbsp;
                                </td>
                            </tr>
                        </c:if>
                    </c:if>
                    <tr>
                        <td>Klasifikasi Tanah :
                        </td>
                        <td>${actionBean.laporanTanah.strukturTanah.nama}&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td>Jika Lain-lain :
                        </td>
                        <td>${actionBean.laporanTanah.strukturTanahLain}&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td>Keadaan Tanah :
                        </td>
                        <td>${actionBean.laporanTanah.kodKeadaanTanah.nama}&nbsp;
                        </td>
                    </tr>
                    <c:if test="${actionBean.laporanTanah.kodKeadaanTanah ne null}">
                        <c:if test="${actionBean.laporanTanah.kodKeadaanTanah.kod eq 'LL'}">
                            <tr>
                                <td>
                                    Lain-lain : 
                                </td>
                                <td>
                                    ${actionBean.keadaankand}&nbsp;
                                </td>
                            </tr>
                        </c:if>
                    </c:if>
                    <tr>
                        <td>
                            Tanah Dipohon Bertumpu :
                        </td>
                        <td>
                            <c:if test="${actionBean.laporanTanah.tanahBertumpu eq 'Y'}">
                                Ya &nbsp;
                            </c:if>
                            <c:if test="${actionBean.laporanTanah.tanahBertumpu eq 'T'}">
                                Tidak &nbsp;
                            </c:if>
                            &nbsp; Pada :
                            ${actionBean.laporanTanah.keteranganTanahBertumpu}&nbsp;
                        </td>
                    </tr>
                </table>
                <br>
                <legend>Dilintasi Oleh</legend>
                <table class="tablecloth" align="center">
                    <tr>
                        <th>Talian Elektrik</th><th>Talian Telefon</th><th>Laluan Gas</th><th>Paip Air</th><th>Tali Air</th><th>Sungai</th><th>Parit</th>
                    </tr>
                    <tr>
                        <td>
                            <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik eq 'Y'}">Ya</c:if>
                            <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik eq null}">Tiada</c:if>
                            </td>
                            <td>
                            <c:if test="${actionBean.laporanTanah.dilintasTiangTelefon eq 'Y'}">Ya</c:if>
                            <c:if test="${actionBean.laporanTanah.dilintasTiangTelefon eq null}">Tiada</c:if>
                            </td>
                            <td>
                            <c:if test="${actionBean.laporanTanah.dilintasLaluanGas eq 'Y'}">Ya</c:if>
                            <c:if test="${actionBean.laporanTanah.dilintasLaluanGas eq null}">Tiada</c:if>
                            </td>
                            <td>
                            <c:if test="${actionBean.laporanTanah.dilintasPaip eq 'Y'}">Ya</c:if>
                            <c:if test="${actionBean.laporanTanah.dilintasPaip eq null}">Tiada</c:if>
                            </td>
                            <td>
                            <c:if test="${actionBean.laporanTanah.dilintasTaliar eq 'Y'}">Ya</c:if>
                            <c:if test="${actionBean.laporanTanah.dilintasPaip eq null}">Tiada</c:if>
                            </td>
                            <td>
                            <c:if test="${actionBean.laporanTanah.dilintasSungai eq 'Y'}">Ya</c:if>
                            <c:if test="${actionBean.laporanTanah.dilintasSungai eq null}">Tiada</c:if>
                            </td>
                            <td>
                            <c:if test="${actionBean.laporanTanah.dilintasParit eq 'Y'}">Ya</c:if>
                            <c:if test="${actionBean.laporanTanah.dilintasParit eq null}">Tiada</c:if>
                            </td>
                        </tr>
                    </table>
                    <br>
                    <table class="tablecloth" align="center">
                        <tr>
                            <td>Tanah Diusahakan :</td>
                            <td> <c:if test="${actionBean.laporanTanah.usaha eq 'Y'}">Ya</c:if>
                            <c:if test="${actionBean.laporanTanah.usaha eq 'T'}">Tidak</c:if>
                            </td>
                        </tr>
                    <c:if test="${actionBean.laporanTanah.usaha eq 'Y'}">
                        <tr>
                            <td>Nama Pengusaha :</td>
                            <td> ${actionBean.laporanTanah.diusaha}</td>
                        </tr>
                        <tr>
                            <td>Tarikh Mula Usaha :</td>
                            <td> <fmt:formatDate value="${actionBean.laporanTanah.tarikhMulaUsaha2}" pattern="dd/MM/yyyy" /></td>
                        </tr>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                            <tr>
                                <td>Tempoh Usaha Tanah :</td>
                                <td>${actionBean.laporanTanah.tarikhMulaUsaha}</td>
                            </tr>
                        </c:if> 
                    </c:if>
                </table>
                <c:if test="${actionBean.laporanTanah.usaha eq 'Y'}">
                    <table class="tablecloth">
                        <tr>
                            <th>Jenis Tanaman</th><th>Luas Yang Ditanam (meter persegi)</th><th>anggaran Bil.pokok</th><th>Nilaian Tanah Termasuk Tanaman(RM)</th>
                        </tr>
                        <tr align="center">
                            <td>
                                ${actionBean.laporanTanah.usahaTanam} &nbsp;
                            </td>
                            <td>
                                ${actionBean.usahaLuas} &nbsp;
                            </td>
                            <td>
                                ${actionBean.usahaBilanganPokok} &nbsp;
                            </td>
                            <td>
                                ${actionBean.usahaHarga} &nbsp;
                            </td>
                        </tr>
                    </table>
                </c:if>
                <table class="tablecloth" align="center">
                    <tr>
                        <td>Bangunan :</td>
                        <td>
                            <c:if test="${actionBean.laporanTanah.adaBangunan eq 'Y'}">Ada</c:if>
                            <c:if test="${actionBean.laporanTanah.adaBangunan eq 'T'}">Tiada</c:if>
                            </td>   
                        </tr>
                    </table>

                <c:if test="${actionBean.laporanTanah.adaBangunan eq 'Y'}">
                    <br>
                    <display:table class="tablecloth" name="${actionBean.permohonanLaporanBangunanList}" pagesize="5" cellpadding="0" cellspacing="0"
                                   requestURI="/pelupusan/laporan_tanah4"  id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Jenis Bangunan">
                            <c:if test="${line.jenisBangunan eq 'KK'}">
                                Kekal
                            </c:if>
                            <c:if test="${line.jenisBangunan eq 'SK'}">
                                Separuh kekal
                            </c:if>
                            <c:if test="${line.jenisBangunan eq 'SM'}">
                                Sementara
                            </c:if>
                        </display:column>
                        <display:column property="ukuran" title="Ukuran Bangunan" />
                        <display:column property="tahunDibina" title="Tahun Dibina" />
                        <display:column property="nilai" title="Nilai Bangunan" />
                        <display:column property="namaPemunya" title="Pemilik" />
                        <display:column property="namaKetua" title="Ketua Keluarga Yang Mendiami"/>
                        <display:column title="Status">
                            <c:if test="${line.jenisPendudukan.kod eq 'TT'}">
                                Pemilik
                            </c:if>
                            <c:if test="${line.jenisPendudukan.kod eq 'TS'}">
                                Pemilik dan Penyewa Bangunan
                            </c:if>
                            <c:if test="${line.jenisPendudukan.kod eq 'SS'}">
                                Penyewa Tanah dan Bangunan
                            </c:if>
                        </display:column>
                    </display:table>

                    <br>
                </c:if>
                <table class="tablecloth" align="center">
                    <tr>
                        <td>Tanah sudah Diperenggan :</td>
                        <td>
                            <c:if test="${actionBean.laporanTanah.perenggan eq 'Y'}">Sudah</c:if>
                            <c:if test="${actionBean.laporanTanah.perenggan eq 'T'}">Belum</c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>Rancangan Kerajaan Atas Tanah (Jika Ada) :</td>
                            <td>${actionBean.laporanTanah.rancanganKerajaan}</td>
                    </tr>
                </table>
            </div>
        </fieldset>             
    </div>
    <br>
    <div class="subtitle" id="updiv">
        <fieldset class="aras1">
            <legend><font color="red">*</font> Perihal Lot-Lot Bersempadan
                <span style="float:right">
                   <%-- <a onclick="viewLotSempadan();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                    <a onclick="openFrame('lSempadan');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                </span>
            </legend>
            <div id ="lotsempadan">
                      <div class="content" align="center">
                <table class="tablecloth">
                    <tr>
                        <th>&nbsp;</th><th>No. Hakmilik</th><th>Kegunaan Tanah</th><th>Keadaan Tanah</th><th>Catatan</th><th>Milik Kerajaan</th>
                             <%--UTARA--%>
                             <c:if test="${!empty actionBean.listLaporTanahSpdnU}">
                                 <c:set var="i" value="1" />
                                 <tr>
                                            <th rowspan="${actionBean.uSize}">
                                                Utara
                                            </th>
                                    <c:forEach items="${actionBean.listLaporTanahSpdnU}" var="line">
                                        
                                            <td>
                                                ${line.laporanTanahSmpdn.hakmilik.idHakmilik}
                                                <s:hidden  id="kandunganSpdnUHM${i}" name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.hakmilik.idHakmilik" />
                                            </td>
                                            <td>
                                                ${line.laporanTanahSmpdn.guna}
                                                <s:hidden   id="kandunganSpdnUKEG${i}" name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.guna" />
                                            </td>
                                            <td>
                                                ${line.laporanTanahSmpdn.keadaanTanah}
                                                <s:hidden   id="kandunganSpdnUKEA${i}" name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.keadaanTanah" />
                                            </td>
                                            <td>
                                                ${line.laporanTanahSmpdn.catatan}
                                                <s:hidden   id="kandunganSpdnUCAT${i}" name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.catatan" />
                                            </td>
                                            <td>
                                                <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                                 <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'T'}">Milik</c:if>
                                                 <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'N'}">--</c:if>
                                                <s:hidden  name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.milikKerajaan" id="kandunganSpdnUMK${i}"/>

                                            </td>
                                        </tr>

                                    <c:set var="i" value="${i+1}" />        
                                    </c:forEach>
                                </c:if>
                             
                        <%--END OF UTARA--%>                             
                        <%--SELATAN--%>
                        <c:if test="${!empty actionBean.listLaporTanahSpdnS}">
                        <c:set var="i" value="1" />
                        <tr>
                                            <th rowspan="${actionBean.sSize}">
                                               Selatan
                                            </th>
                            <c:forEach items="${actionBean.listLaporTanahSpdnS}" var="line">

                                    <td>
                                        ${line.laporanTanahSmpdn.hakmilik.idHakmilik}
                                        <s:hidden  id="kandunganSpdnSHM${i}" name="listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.hakmilik.idHakmilik" />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.guna}
                                        <s:hidden  id="kandunganSpdnSKEG${i}" name="listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.guna" />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.keadaanTanah}
                                        <s:hidden  id="kandunganSpdnSKEA${i}" name="listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.keadaanTanah" />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.catatan}
                                        <s:hidden  id="kandunganSpdnSCAT${i}" name="listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.catatan" />
                                    </td>
                                    <td>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                                 <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'T'}">Milik</c:if>
                                                 <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'N'}">--</c:if>
                                        <s:hidden name="listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.milikKerajaan" id="kandunganSpdnSMK${i}"/>

                                    </td>
                                </tr>
                                   
                            <c:set var="i" value="${i+1}" />        
                            </c:forEach>
                           </c:if>
                                
                        <%--END OF SELATAN--%>
                        <%--TIMUR--%>
                        <c:if test="${!empty actionBean.listLaporTanahSpdnT}">
                        <c:set var="i" value="1" />
                         <tr>
                                            <th rowspan="${actionBean.tSize}">
                                               Timur
                                            </th>
                            <c:forEach items="${actionBean.listLaporTanahSpdnT}" var="line">

                                    <td>
                                        ${line.laporanTanahSmpdn.hakmilik.idHakmilik}
                                        <s:hidden  id="kandunganSpdnTHM${i}" name="listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.hakmilik.idHakmilik"  />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.guna}
                                        <s:hidden  id="kandunganSpdnTKEG${i}" name="listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.guna"  />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.keadaanTanah}
                                        <s:hidden  id="kandunganSpdnTKEA${i}" name="listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.keadaanTanah"  />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.catatan}
                                        <s:hidden  id="kandunganSpdnTCAT${i}" name="listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.catatan"  />
                                    </td>
                                    <td>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                                 <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'T'}">Milik</c:if>
                                                 <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'N'}">--</c:if>
                                        <s:hidden name="listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.milikKerajaan" id="kandunganSpdnTMK${i}" />

                                    </td>
                                </tr>
                                   
                            <c:set var="i" value="${i+1}" />        
                            </c:forEach>
                           
                        </c:if>
                                  
                         <%--END OF TIMUR--%>
                         <%--BARAT--%>
                        <c:if test="${!empty actionBean.listLaporTanahSpdnB}">
                        <c:set var="i" value="1" />
                        <tr>
                                            <th rowspan="${actionBean.bSize}">
                                               Barat
                                            </th>
                            <c:forEach items="${actionBean.listLaporTanahSpdnB}" var="line">
                                 
                                    <td>
                                        ${line.laporanTanahSmpdn.hakmilik.idHakmilik}
                                        <s:hidden  id="kandunganSpdnBHM${i}" name="listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.hakmilik.idHakmilik"  />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.guna}
                                        <s:hidden  id="kandunganSpdnBKEG${i}" name="listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.guna"  />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.keadaanTanah}
                                        <s:hidden  id="kandunganSpdnBKEA${i}" name="listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.keadaanTanah"  />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.catatan}
                                        <s:hidden  id="kandunganSpdnBCAT${i}" name="listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.catatan"  />
                                    </td>
                                    <td>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                                 <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'T'}">Milik</c:if>
                                                 <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'N'}">--</c:if>
                                        <s:hidden name="listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.milikKerajaan" id="kandunganSpdnBMK${i}" />

                                    </td>
                                </tr>
                                   
                            <c:set var="i" value="${i+1}" />        
                            </c:forEach>
                             
                        </c:if>
                                  
                         <%--END OF BARAT--%>
                         
                </table>
            </div>
            </div>
        </fieldset>
    </div>
    <br>
    <div class="subtitle" align="center">
        <fieldset class="aras1">
            <legend>
                Dalam Kawasan
                <span style="float:right">
                   <%-- <a onclick="viewDalamKawasan();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                    <a onclick="openFrame('dKawasan');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                </span>
            </legend>
            <br>
            <div id="dalamkawasan" align="center">
                <display:table  name="${actionBean.senaraiLaporanKawasan}" id="line9" class="tablecloth">
                    <s:hidden name="" class="${line9_rowNum -1}" value="${line9.idMohonlaporKws}"/>
                    <display:column title="No">${line9_rowNum}</display:column>
                    <display:column title="Jenis Rizab"  property="kodRizab.nama"/>
                    <display:column title="Catatan">
                        <c:if test="${line9.catatan ne null}">
                            ${line9.catatan}
                        </c:if>
                        <c:if test="${line9.catatan eq null}">
                            -
                        </c:if>
                    </display:column>
                    <display:column title="No Warta" property="noWarta"/>
                    <display:column title="Tarikh Warta" property="tarikhWarta" format="{0,date,dd-MM-yyyy}"/>
                    <display:column title="No Pelan Warta" property="noPelanWarta" />                    
                </display:table>
                <br/>
            </div>
        </fieldset> 
    </div>
    <br/>            
    <div class="subtitle">
        <fieldset class="aras1">
            
                <legend>
                    <c:if test="${actionBean.kodNegeri eq '04'}">Syor Penolong Pegawai Tanah</c:if>
                    <c:if test="${actionBean.kodNegeri eq '05'}">Ulasan Penolong Pegawai Tanah</c:if>
                     <span style="float:right">
                       <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                        <a onclick="openFrame('syorPPT');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                    </span>
                </legend>
                
            <%--PTGSA CASE--%>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
               <div id="syorPPT" align="center">
                    <table class="tablecloth" align="center">
                        <tr>
                            <td>
                                Syor :
                            </td>
                            <td>
                                 ${actionBean.fasaPermohonan.keputusan.nama}&nbsp;
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Ulasan :
                            </td>
                            <td>&nbsp;</td>
                                 
                        </tr>
                        <c:set var="i" value="1" />
                        <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line">
                            <tr><td style="text-align: right">${i}</td>
                                <td><s:textarea value="${line.ulasan}" cols="80" readonly="true" name="syorUlasan" rows="5"/></td>
                            </tr>
                            <c:set var="i" value="${i+1}" />
                        </c:forEach>
                    </table>
                </div>
            </c:if>
        </fieldset>
    </div>
</s:form>