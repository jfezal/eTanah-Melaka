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
                $('#perihaltanah').hide();
                $('#sempadan').hide();
                $('#keadaantanah').hide();              
                $('#lotsempadan').hide();
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
        
        

</script>

    <s:form beanclass="etanah.view.stripes.pelupusan.LaporanTanahPelupusanActionBean" name="form">
    <s:errors/>
    <s:messages/>
    <s:hidden name="checkTanahExist" value="${actionBean.checkTanahExist}"/>
    <s:hidden name="permohonan.idPermohonan"/>
    <s:hidden name="display"/>
    
    <s:hidden name="kodD" id="kodD"/>
    <%--<s:text name="edit" id="edit"/>--%>
    <div class="subtitle">  
        <fieldset class="aras1">
            
               
            <legend>Perihal Tanah 
            <span style="float:right">
                <a onclick="viewPerihalTanah();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | 
                <a onclick="viewPerihalTanah();" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
            </span>
            </legend>
            <div id="perihaltanah">
           <c:if test="${actionBean.display eq 'true'}">
            <div class="content" align="center">
                Tanah Kerajaan
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                               requestURI="/pelupusan/laporan_tanah">
                    <display:column title="No" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.id}"/></display:column>
                    <display:column title="No.Lot/PT" property="noLot"/>
                    <display:column title="Bandar/Pekan/Mukim" property="bandarPekanMukimBaru.nama"/>
                    <display:column title="Seksyen" property="kodSeksyen.nama" /><%----%><%--<s:select name="kodSeksyen.nama" value="${actionBean.hakmilikPermohonan.kodSeksyen.kod}">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:options-collection collection="${actionBean.kodSeksyenList}" label="nama" value="kod" /></s:select></display:column>--%>
                    <display:column title="Daerah" property="bandarPekanMukimBaru.daerah.nama"/>
                    <display:column title="Luas Dipohon"><s:format formatPattern="#,###,##0.0000" value="${line.luasTerlibat}"/> ${line.kodUnitLuas.nama} </display:column>
                </display:table>
            </div>
            </c:if>
            <p>
                <label>Status Tanah :</label>
                <c:if test="${actionBean.display eq 'true'}">
                	${actionBean.hakmilikPermohonan.kodMilik.nama}&nbsp;
                </c:if>
            </p>
            <p id="tanahkerajaan">
                <label>Tanah Kerajaan Boleh diberimilik :</label>
                <c:if test="${actionBean.display eq 'true'}"><!--
                	${actionBean.laporanTanah.bolehBerimilik}
                	--><c:if test="${actionBean.laporanTanah.bolehBerimilik eq 'Y'}">
                		Ya &nbsp;
                	</c:if>
                	<c:if test="${actionBean.laporanTanah.bolehBerimilik eq 'T'}">
                		Tidak &nbsp;
                	</c:if>
                </c:if>
            </p>

            <p id="jikasebab">
                <label>Jika Tidak Boleh diberimilik, sebab :</label>
               <c:if test="${actionBean.display eq 'true'}">
                ${actionBean.laporanTanah.sebabTidakBolehBerimilik}&nbsp;
                 </c:if>
            </p>
            
            <div class="content" align="center" id="carianHakmilik">
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

                         <c:if test="${actionBean.display eq 'false'}">
                <s:button class="longbtn" value="Carian Hakmilik" name="idHakmilik" id="idHakmilik" onclick="carianHakmilikPopUp();"/>&nbsp;
                         </c:if>
                
            </div>

            <p id="jenisRizab">
                <label>Jenis Rizab :</label>
              <c:if test="${actionBean.display eq 'true'}">
                  ${actionBean.tanahrizabpermohonan1.rizab.nama} &nbsp;
              </c:if>
            </p>
            <p id="jenisRizabno">
                <label>No. Warta Kerajaan :</label>
                <c:if test="${actionBean.display eq 'true'}">
                           ${actionBean.tanahrizabpermohonan1.noWarta}&nbsp;
                </c:if>
            </p> 
            <p>
                <label>Jenis Tanah :</label>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.permohonanLaporanPelan.kodTanah.nama} &nbsp;
                    </c:if>
            </p>

             <p id="jenistanahlainlain">
                    <label>Lain-lain : </label>
                    <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.kand}&nbsp;
                        </c:if>
                </p>



            <c:if test="${actionBean.permohonan.penyerahNegeri.kod eq '04'}">
            <p>
                <label>Kawasan Parlimen :</label>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.kodKawasanParlimen.nama}&nbsp;
                    </c:if>
            </p>

            <p id="mT">
                <label>DUN :</label>
                <c:if test="${actionBean.display eq 'true'}">
                    ${actionBean.hakmilikPermohonan.kodDUN.nama}&nbsp;
                </c:if>
            </p>

            <p id="aG">
                <label>DUN :</label>
                 <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.kodDUN.nama}&nbsp;
                </c:if>
            </p>

            <p id="tB">
                <label>DUN :</label>
                 <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.kodDUN.nama}&nbsp;
                </c:if>
            </p>

            <p id="bK">
                <label>DUN :</label>
                  <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.kodDUN.nama}&nbsp;
                </c:if>
            </p>

            <p id="kM">
                <label>DUN :</label>
                 <c:if test="${actionBean.display eq 'true'}">
                    ${actionBean.hakmilikPermohonan.kodDUN.nama}&nbsp;
                </c:if>
            </p>

            <p id="jS">
                <label>DUN :</label>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.kodDUN.nama}&nbsp;
                </c:if>
            </p></c:if>

            <p>
                <label>Kedudukan Tanah :</label>
                 <c:if test="${actionBean.display eq 'true'}">
                          ${actionBean.hakmilikPermohonan.lokasi}&nbsp;
                 </c:if>
            <br/><%--<center>(Jika ada)</center>--%>

            <div class="content" align="center">
                <div id="mohonTerdahulu">
                Permohonan Terdahulu
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
            </div> <br/>
            </div>
            <legend>Bersempadan
              <span style="float:right">
                <a onclick="viewSempadan();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | 
                <a onclick="viewPerihalTanah();" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
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
                            <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.namaSempadanJalanraya}
                        </c:if>
                        </td>
                        <td>
                            <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.jarakSempadanJalanraya}
                        </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Landasan Keretapi
                        </td>
                        <td> 
                            <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.namaSempadanKeretapi}
                        </c:if>
                        </td>
                        <td>
                            <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.jarakSempadanKeretapi}
                        </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Laut
                        </td>
                        <td>
                             <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.namaSempadanLaut}
                        </c:if>
                        </td>
                        <td>
                            <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.jarakSempadanLaut}
                        </c:if>
                        </td>
                    </tr><tr>
                        <td>
                            Sungai
                        </td>
                        <td>
                            <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.namaSempadanSungai}
                        </c:if>
                        </td>
                        <td>
                            <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.jarakSempadanSungai}
                        </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Lain-lain
                        </td>
                        <td>
                            <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.namaSempadanlain}
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.jarakSempadanLain}
                        </c:if>
                        </td>
                    </tr>
                </table>
            </div>
            <p>
                <label>Jenis Jalan :</label>
                    <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.jenisJalan}&nbsp;
                        </c:if>
            </p>
            <p>
                <label>Jalan Masuk :</label>
                <c:if test="${actionBean.display eq 'true'}"><!--
                    ${actionBean.laporanTanah.adaJalanMasuk}&nbsp;
                    --><c:if test="${actionBean.laporanTanah.adaJalanMasuk eq 'Y'}">
                    	Ada &nbsp;
                    </c:if>
                    <c:if test="${actionBean.laporanTanah.adaJalanMasuk eq 'T'}">
                    	Tiada &nbsp;
                    </c:if>
               	</c:if>
            </p>
            <p>
                <label>Catatan :</label>
                <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.catatanJalanMasuk}&nbsp;
                        </c:if>
            </p>
        </div>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <s:hidden name="laporanTanah.idLaporan"/>
            <s:hidden name="laporanTanah.permohonan.idPermohonan"/>
            <legend>Keadaan Tanah
              <span style="float:right">
                <a onclick="viewKeadaanTanah();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | 
                <a onclick="viewPerihalTanah();" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
            </span>
            </legend>
            <div id="keadaantanah">
                 <c:if test="${actionBean.kodNegeri eq '05'}">
                     <c:if test="${actionBean.display eq 'true'}">
                         <p>
                            <label>Jarak:</label>
                            ${actionBean.hakmilikPermohonan.jarak} <c:if test="${actionBean.hakmilikPermohonan.unitJarak.kod eq 'KM'}">Kilometer</c:if><c:if test="${actionBean.hakmilikPermohonan.unitJarak.kod eq 'JM'}">Meter</c:if>
                            &nbsp;<font color="#003194"><b>dari</b></font>&nbsp; ${actionBean.hakmilikPermohonan.jarakDari}                           
                        </p>
                        <p>
                            <label>Jarak Dari Kediaman:</label> ${actionBean.hakmilikPermohonan.jarakDariKediaman} <c:if test="${actionBean.hakmilikPermohonan.jarakDariKediamanUom.kod eq 'KM'}">Kilometer</c:if><c:if test="${actionBean.hakmilikPermohonan.jarakDariKediamanUom.kod eq 'JM'}">Meter</c:if>
                           
                        </p>
                     </c:if>   
                </c:if>          
                <p>
                    <label>Kecerunan Tanah :</label>
                        <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.kecerunanTanah.nama}&nbsp;
                        </c:if>
                </p>

                <p id="tinggi">
                    <label>Ketinggian Dari Paras Jalan (m) :</label>
                    <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.ketinggianDariJalan}&nbsp;
                        </c:if>
                </p>&nbsp;
                <p id="cerun">
                    <label>Darjah Kecerunan :</label>
                    <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.kecerunanBukit}&nbsp;
                        </c:if>
                </p>
                <p id="dalam">
                    <label>Dalam Paras Air (m) :</label>
                    <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.parasAir}&nbsp;
                        </c:if>
                </p>                
                <p>
                    <label>Klasifikasi Tanah :</label>
                        <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.strukturTanah.nama}&nbsp;
                        </c:if>
                </p>
                <p>
                    <label>Jika Lain-lain :</label>
                    <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.strukturTanahLain}&nbsp;
                        </c:if>
                </p>
                <p>
                    <label>Keadaan Tanah :</label>
                        <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.kodKeadaanTanah.nama}&nbsp;
                        </c:if>
                </p>

                <p id="keadaantanahlainlain">
                    <label>Lain-lain : </label>
                    <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.keadaankand}&nbsp;
                        </c:if>
                </p>

                <p>
                    <label>Tanah Dipohon Bertumpu :</label>
                    <c:if test="${actionBean.display eq 'true'}"><!--
                    	${actionBean.laporanTanah.tanahBertumpu}&nbsp;
	                    --><c:if test="${actionBean.laporanTanah.tanahBertumpu eq 'Y'}">
	                    	Ya &nbsp;
	                    </c:if>
	                    <c:if test="${actionBean.laporanTanah.tanahBertumpu eq 'T'}">
	                    	Tidak &nbsp;
	                    </c:if>
                    </c:if>
                    &nbsp; Pada :
                        <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.keteranganTanahBertumpu}&nbsp;
                        </c:if>
                </p>
                <br>
                <p>
                    <label>Dilintasi Oleh :</label>
                    <c:if test="${actionBean.display eq 'true'}">
                        <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik eq 'Y'}">
                              Talian Elektrik - Ya<br>
                        </c:if>
                         <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik eq null}">
                              Talian Elektrik - Tiada<br>
                        </c:if>
                        </c:if>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <c:if test="${actionBean.display eq 'true'}">
                         <c:if test="${actionBean.laporanTanah.dilintasTiangTelefon eq 'Y'}">
                              Talian Telefon - Ya<br>
                        </c:if>
                         <c:if test="${actionBean.laporanTanah.dilintasTiangTelefon eq null}">
                              Talian Telefon - Tiada<br>
                        </c:if>
                    </c:if>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <c:if test="${actionBean.display eq 'true'}">
                        <c:if test="${actionBean.laporanTanah.dilintasLaluanGas eq 'Y'}">
                              Laluan Gas - Ya<br>
                        </c:if>
                         <c:if test="${actionBean.laporanTanah.dilintasLaluanGas eq null}">
                              Laluan Gas - Tiada<br>
                        </c:if>

                    </c:if>

                </p>
                <p>
                    <label>&nbsp;</label>
                    <c:if test="${actionBean.display eq 'true'}">
                        <c:if test="${actionBean.laporanTanah.dilintasPaip eq 'Y'}">
                              Paip Air - Ya<br>
                        </c:if>
                         <c:if test="${actionBean.laporanTanah.dilintasPaip eq null}">
                              Paip Air - Tiada<br>
                        </c:if>

                    </c:if>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <c:if test="${actionBean.display eq 'true'}">
                         <c:if test="${actionBean.laporanTanah.dilintasTaliar eq 'Y'}">
                              Tali Air - Ya<br>
                        </c:if>
                         <c:if test="${actionBean.laporanTanah.dilintasTaliar eq null}">
                              Tali Air - Tiada<br>
                        </c:if>

                    </c:if>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <c:if test="${actionBean.display eq 'true'}">
                         	<c:if test="${actionBean.laporanTanah.dilintasSungai eq 'Y'}">
                              Sungai - Ya<br>
                        </c:if>
                         <c:if test="${actionBean.laporanTanah.dilintasSungai eq null}">
                              Sungai - Tiada<br>
                        </c:if>

                    </c:if>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <c:if test="${actionBean.display eq 'true'}">
                        <c:if test="${actionBean.laporanTanah.dilintasParit eq 'Y'}">
                              Parit- Ya<br>
                        </c:if>
                         <c:if test="${actionBean.laporanTanah.dilintasParit eq null}">
                              Parit- Tiada<br>
                        </c:if>

                    </c:if>
                </p>
           
      <%--  </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Latar belakang Tanah</legend>--%>           
                <p>
                    <label>Tanah Diusahakan :</label>
                    <c:if test="${actionBean.display eq 'true'}"><!--
                        ${actionBean.laporanTanah.usaha} &nbsp;
                        --><c:if test="${actionBean.laporanTanah.usaha eq 'Y'}">
                        	Ya &nbsp;
                        </c:if>
                        <c:if test="${actionBean.laporanTanah.usaha eq 'T'}">
                        	Tidak &nbsp;
                        </c:if>
                    </c:if>
                </p>

                <p id="pengusuka">
                    <label>Nama Pengusaha :</label>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.laporanTanah.diusaha} &nbsp;
                    </c:if>
                </p>
                <p id="tarikh">
                    <label>Tarikh Mula Usaha :</label>
                    <c:if test="${actionBean.display eq 'true'}">
                        <fmt:formatDate value="${actionBean.laporanTanah.tarikhMulaUsaha2}" pattern="dd/MM/yyyy" />
                    </c:if>                   
                </p>
                <p id="tempoh">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                    <label>Tempoh Usaha Tanah :</label>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.laporanTanah.tarikhMulaUsaha}
                    </c:if>
                    </c:if>  
                </p>
                <div class="content" align="center" id="laterbelaktable">
                    <table class="tablecloth">
                        <tr>
                            <th>Jenis Tanaman</th><th>Luas Yang Ditanam (meter persegi)</th><th>anggaran Bil.pokok</th><th>Nilaian Tanah Termasuk Tanaman(RM)</th>
                        </tr>
                        <tr align="center">
                            <td>
                                <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.laporanTanah.usahaTanam} &nbsp;
                    </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.usahaLuas} &nbsp;
                    </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.usahaBilanganPokok} &nbsp;
                    </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.usahaHarga} &nbsp;
                    </c:if>
                            </td>
                        </tr></table>
                </div>
                <p>
                    <label>Bangunan :</label>
                    <c:if test="${actionBean.display eq 'true'}"><!--
                        ${actionBean.laporanTanah.adaBangunan} &nbsp;
                        --><c:if test="${actionBean.laporanTanah.adaBangunan eq 'Y'}">
                        	Ada &nbsp;
                        </c:if>
                        <c:if test="${actionBean.laporanTanah.adaBangunan eq 'T'}">
                        	Tiada &nbsp;
                        </c:if>
                    </c:if>
                </p>
                <c:if test="${actionBean.laporanTanah.adaBangunan eq 'Y'}">
                    <div align="center">
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
                    </div>
                    
                </c:if>        
<!--                <div class="content" align="center" id="Bangunantable" style="display:none">
                    <display:table class="tablecloth" name="${actionBean.permohonanLaporanBangunanList}" pagesize="5" cellpadding="0" cellspacing="0"
                                       requestURI="/pelupusan/laporan_tanah4"  id="line">
                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Jenis Bangunan">
                                <c:if test="${line.jenisBangunan eq 'KK'}">
                                    kekal
                                </c:if>
                                <c:if test="${line.jenisBangunan eq 'SK'}">
                                    separuh kekal
                                </c:if>
                                <c:if test="${line.jenisBangunan eq 'SM'}">
                                    sementara
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

               </div>-->
                <p>
                    <label>Tanah sudah Diperenggan :</label>
                    <c:if test="${actionBean.display eq 'true'}"><!--
                        ${actionBean.laporanTanah.perenggan} &nbsp;
                        --><c:if test="${actionBean.laporanTanah.perenggan eq 'Y'}">
                        	Sudah &nbsp;
                        </c:if>
                        <c:if test="${actionBean.laporanTanah.perenggan eq 'T'}">
                        	Belum &nbsp;
                        </c:if>
                    </c:if>
                </p>

                <p>
                    <label>Rancangan Kerajaan Atas Tanah (Jika Ada) :</label>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.laporanTanah.rancanganKerajaan} &nbsp;
                    </c:if>
                </p>  
                </div>
           </fieldset>
             
    </div>
                

    <br /><br />    
    <div class="subtitle" id="updiv">
        <fieldset class="aras1">
            <legend><font color="red">*</font> Perihal Lot-Lot Bersempadan
             <span style="float:right">
                <a onclick="viewLotSempadan();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | 
                <a onclick="viewPerihalTanah();" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
            </span>
            </legend>
            <div id ="lotsempadan">
            <div class="content" align="center">
                <table class="tablecloth">
                    <tr>
                        <th>&nbsp;</th><%--<th>Taraf Tanah</th>--%><th>Nombor Lot</th><th>Kegunaan</th><th>Gambar</th>
                    </tr>
                    <tr>
                        <th>
                            Utara
                        </th>
                        <%--<td>
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="T"/>&nbsp;Milik
                        </td>--%>
                        <td>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.laporanTanah.sempadanUtaraNoLot} &nbsp;
                    </c:if>
                        </td>
                        <td>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.laporanTanah.sempadanUtaraKegunaan} &nbsp;
                    </c:if>
                        </td>
                        <td>                            
                            <s:select name="utaraImej" style="width:300px;" id="utaraImej" onchange="doSetDokumenUtara();">
                                <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                <s:options-collection collection="${actionBean.utaraImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                            </s:select>&nbsp;                            
                            <s:hidden name="idDokumen" id="idDokumenU" />${actionBean.hakmilik.idHakmilik}
                        </td>
                    </tr>
                    <tr>
                        <th>
                            Selatan
                        </th>
                        <%--<td>
                            <s:radio name="laporanTanah.sempadanSelatanMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                            <s:radio name="laporanTanah.sempadanSelatanMilikKerajaan" value="T"/>&nbsp;Milik
                        </td>--%>
                        <td>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.laporanTanah.sempadanSelatanNoLot} &nbsp;
                    </c:if>
                        </td>
                        <td>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.laporanTanah.sempadanSelatanKegunaan} &nbsp;
                    </c:if>
                        </td>
                        <td>
                            <s:select name="selatanImej" style="width:300px;" id="selatanImej" onchange="doSetDokumenSelatan();">
                                <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                <s:options-collection collection="${actionBean.selatanImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                            </s:select>&nbsp;
                            <s:hidden name="idDokumen" id="idDokumenS" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            Timur
                        </th>
                        <%--<td>
                            <s:radio name="laporanTanah.sempadanTimurMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                            <s:radio name="laporanTanah.sempadanTimurMilikKerajaan" value="T"/>&nbsp;Milik
                        </td>--%>
                        <td>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.laporanTanah.sempadanTimurNoLot} &nbsp;
                    </c:if>
                        </td>
                        <td>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.laporanTanah.sempadanTimurKegunaan} &nbsp;
                    </c:if>
                        </td>
                        <td>
                            <s:select name="timurImej" style="width:300px;" id="timurImej" onchange="doSetDokumenTimur();">
                                <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                <s:options-collection collection="${actionBean.timurImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                            </s:select>&nbsp;
                            <s:hidden name="idDokumen" id="idDokumenT" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            Barat
                        </th>
                        <%--<td>
                            <s:radio name="laporanTanah.sempadanBaratMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                            <s:radio name="laporanTanah.sempadanBaratMilikKerajaan" value="T"/>&nbsp;Milik
                        </td>--%>
                        <td>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.laporanTanah.sempadanBaratNoLot} &nbsp;
                    </c:if>
                        </td>
                        <td>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.laporanTanah.sempadanBaratKegunaan} &nbsp;
                    </c:if>
                        </td>
                        <td>
                            <s:select name="baratImej" style="width:300px;" id="baratImej" onchange="doSetDokumenBarat();">
                                <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                <s:options-collection collection="${actionBean.baratImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                            </s:select>&nbsp;
                            <s:hidden name="idDokumen" id="idDokumenB" />
                        </td>
                    </tr>
                </table>
            </div>
                        </div>
        </fieldset>
    </div>

        <div class="subtitle" align="center">
            <fieldset class="aras1" id="locate">
        <legend>
            Dalam Kawasan
             <span style="float:right">
                <a onclick="viewDalamKawasan();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | 
                <a onclick="viewPerihalTanah();" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
            </span>
        </legend>
        <br>
        <div id="dalamkawasan">
        <p>
                <%--<label>Status Tanah :</label>--%>
                Tanah Dipohon Berada Dalam Kawasan :
                <%--<s:radio name="pbt" value="Y" />Ya--%>
                <%--<s:checkbox name="pbt" value="1"/> Di bawah Kawalan Pihak Berkuasa Tempatan--%>
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
                        <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line9_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeLaporKawasan('${line9.idMohonlaporKws}')"/>
                        </div>
                        </display:column>
                    </display:table><br/>
            </p>
            <p>

            </p>
<!--        <p>
            <label>Tanah Dipohon Berada Dalam Kawasan :</label>
            <c:if test="${actionBean.display eq 'true'}">
                ${actionBean.pbt1} Di bawah Kawalan Pihak Berkuasa Tempatan &nbsp;
            </c:if>
        </p>
        <p>
            <label>&nbsp;</label>
            <c:if test="${actionBean.display eq 'true'}">
            ${actionBean.rizab_melayu} Tanah Rizab Melayu &nbsp;
            </c:if>
        </p>
        <p>
            <label>&nbsp;</label>
            <c:if test="${actionBean.display eq 'true'}">
            ${actionBean.gsa} Rizab GSA &nbsp;
            </c:if>
        </p>
        <p>
            <label>&nbsp;</label>
            <c:if test="${actionBean.display eq 'true'}">
            ${actionBean.hutan} Rizab Hutan Simpan Kekal &nbsp;
            </c:if>
        </p>
        <p>
            <label>&nbsp; </label>
            <c:if test="${actionBean.display eq 'true'}">
            ${actionBean.lain} Lain-lain &nbsp;
            </c:if>
        </p>
        <p>
            <label for="catatanLain">Catatan :</label>
            <c:if test="${actionBean.display eq 'true'}">
            ${actionBean.catatanLain1} &nbsp;
            </c:if>
        </p>-->
            </div>
    </fieldset> </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <c:if test="${actionBean.kodNegeri eq '04'}">
                 <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT' }"><legend>Syor Penolong Pegawai Tanah</legend></c:if>
            </c:if>
            <c:if test="${actionBean.kodNegeri eq '05'}">
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT' }"><legend>Ulasan Penolong Pegawai Tanah</legend></c:if>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS' || actionBean.permohonan.kodUrusan.kod eq 'LPSP'}"><legend>Syor Penolong Pegawai Tanah</legend></c:if>
            <%--If PBMT--%>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT' }">
                <%--<p>
                    <label>Syor :</label>
                    <s:radio name="ksn" value="DI" onclick="showjikasokong();" />&nbsp;Sokong
                    <s:radio name="ksn" value="TI" onclick="showtidaksokong();" />&nbsp;Tidak Sokong
                </p>--%>
                <c:if test="${actionBean.kodNegeri eq '04'}">
                    <p>
                    <label>Syor :</label>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.fasaPermohonan.keputusan.nama}&nbsp;
                    </c:if>
                </p>
                </c:if>
                <c:if test="${actionBean.kodNegeri eq '05'}">
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PBMT'}">
                        <p>
                        <label>Syor :</label>
                        <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.fasaPermohonan.keputusan.nama}&nbsp;
                        </c:if>
                    </p>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                        <p>
                        <label>Pertimbangan :</label>
                        <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.fasaPermohonan.keputusan.nama}&nbsp;
                        </c:if>
                    </p>
                </c:if>
                </c:if>
                
                <c:if test="${actionBean.kodNegeri eq '04'}">
                    <div id="jikasokong">
                    <p>
                        <label>Diluluskan :</label>
                        <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.statusLuasDiluluskan}&nbsp;
                    </c:if>
                    </p>
                    <p>
                        <label>Luas Diluluskan:</label>
                        <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.noPT.luasDilulus}&nbsp;
                    </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.noPT.kodUOM.nama}&nbsp;
                    </c:if>
                    </p>
                    <p>
                        <label>Jenis Hakmilik :</label>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.kodHakmilik.nama}&nbsp;
                    </c:if>
                    </p>
                    <p>
                        <label>Tempoh Pajakan :</label>

                             <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.tempohPajakan}&nbsp;
                    </c:if>
                    </p>
                     <p>
                        <label>Premium :</label>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.keteranganKadarPremium}&nbsp;
                    </c:if>
                     <%--<p id="premiumTxt">
                         <label>Nilai :</label>
                         <c:if test="${actionBean.display eq 'false'}">
                         <s:text name="hakmilikPermohonan.kadarPremium" size="20" onkeyup="validateNumber(this,this.value);" />
                         </c:if>
                         <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.kadarPremium}&nbsp;
                    </c:if>--%>
                    </p>
                    <p>
                        <label>Hasil :</label>
                        <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.hakmilikPermohonan.keteranganCukaiBaru} &nbsp;
                    </c:if>
                    </p>
                    <p>
                        <label>Upah Ukur :</label>
                        <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.agensiUpahUkur}&nbsp;
                    </c:if>
                    </p>

                    <p>
                        <label>Syarat Nyata :</label>
                        <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}&nbsp;
                    </c:if>
                    </p>
                    <p>
                        <label>Sekatan Kepentingan :</label>
                        <c:if test="${actionBean.display eq 'true'}">
                        <table border="0"> <tr><td>
                        ${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}&nbsp; <br/>
                                </td></tr></table>
                    </c:if>
                    </p>
                </div>
                </c:if>
                <c:if test="${actionBean.kodNegeri eq '05'}">
                    <div id="jikasokong">
                    <p>
                        <label>Diluluskan :</label>

                        <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.statusLuasDiluluskan}&nbsp;
                    </c:if>
                    </p>
                    <p>
                        <label>Luas Diluluskan:</label>
                        <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.luasDilulus}&nbsp;
                    </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.noPT.kodUOM.nama}&nbsp;
                    </c:if>
                    </p>
                   <%-- <p>
                        <label>Penjenisan :</label>
                        <s:select name="hakmilikPermohonan.penjenisan" id="penjenis" >
                            <s:option value="0">--Sila Pilih--</s:option>
                            <s:option value="Bangunan">Bangunan</s:option>
                            <s:option value="Pertanian">Pertanian</s:option>
                            <s:option value="Perusahaan">Perusahaan</s:option>
                        </s:select>
                    </p>--%>
                    <p>
                        <label>Jenis Hakmilik Sementara:</label>
                       <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.kodHakmilikSementara.nama}&nbsp;
                    </c:if>
                    </p>
                    <p>
                        <label>Jenis Hakmilik Tetap :</label>
                        <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.kodHakmilikTetap.nama}&nbsp;
                         </c:if>
                    </p>
                    <p>
                        <label>Tempoh Pajakan :</label>
                             <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.tempohPajakan}&nbsp;
                    </c:if>
                    </p>
                     <p>
                        <label>Kadar Premium :</label>Mengikut NSPU 25/2002 dan Jabatan Penilaian dan Perkhidmatan Harta
                        <%--
                        <c:if test="${actionBean.display eq 'false'}">
                            <s:text name="keteranganKadarPremium" maxlength="300" size="50"/>
                            
                           </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.keteranganKadarPremium}&nbsp;
                        </c:if>--%>
                     <%--<p id="premiumTxt">
                         <label>Nilai :</label>
                         <c:if test="${actionBean.display eq 'false'}">
                         <s:text name="hakmilikPermohonan.kadarPremium" size="20" onkeyup="validateNumber(this,this.value);" />
                         </c:if>
                         <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.kadarPremium}&nbsp;
                    </c:if>--%>
                    </p>
                    <p>
                        <label>Kadar Cukai :</label>Mengikut NSPU 7/2005
                        <%--<c:if test="${actionBean.display eq 'false'}">
                        <s:text name="hakmilikPermohonan.keteranganCukaiBaru" size="50"/>
                        </c:if>
                        <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.hakmilikPermohonan.keteranganCukaiBaru}&nbsp;
                        </c:if>--%> 
                    </p>
                    <p>
                        <label>Upah Ukur :</label>
                        <%--Mengikut PU(A)438 Juru ukur Berlesen--%>
                        <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.agensiUpahUkur}&nbsp;
                    </c:if>
                    </p>
                    <p>
                        <label>Kegunaan :</label>
                        <%--Mengikut PU(A)438 Juru ukur Berlesen--%>
                        <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.penjenisan}&nbsp;
                    </c:if>
                    </p>
                    <p>
                        <label>Syarat Nyata :</label>
                        <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}&nbsp;
                    </c:if>
                    </p>
                    <p>
                        <label>Sekatan Kepentingan :</label>
                        <c:if test="${actionBean.display eq 'true'}">
                        <table border="0"> <tr><td>
                        ${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}&nbsp; <br/>
                                </td></tr></table>
                    </c:if>
                    </p>
                </div>
                </c:if>
                
                <p id="tidaksokong">
                    <label>Sebab :</label>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.fasaPermohonan.ulasan}&nbsp; <br/>
                    </c:if>
                </p>

                <div id="pbmtsyorlulus">
                <p>
                    <label>Kegunaan :</label>
                     <c:if test="${actionBean.display eq 'true'}">
                          ${actionBean.permohonanPermitItem.kodItemPermit.nama}&nbsp;
                    </c:if>
                </p>
                
                
                <p>
                    <label>Bayaran (RM) :</label>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.permohonanTuntutanKos.amaunTuntutan}&nbsp;
                    </c:if>
                     Setahun
                </p>
                
                <p>
                    
                    <label>Luas :</label>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.luasTerlibat}&nbsp;
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}&nbsp;
                    </c:if>
                     
                    </p>
                <p>
                    <label>Syarat Tambahan :</label>
                     <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.ulsn}&nbsp;
                    </c:if>
                </p>
                </div>
            </c:if>
            <%--if LPS--%>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS'}">

                <p>
                    <label>Syor :</label>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.fasaPermohonan.keputusan.nama}&nbsp;
                    </c:if>
                </p>

                <div id="plpssokong">
                <p>
                    <label>Kegunaan :</label>
                             <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.permohonanPermitItem.kodItemPermit.nama}&nbsp;
                    </c:if>
                </p>
                <div id="catatanKegunaan">
                    <p>
                    <label>Catatan :</label>
                            <c:if test="${actionBean.display eq 'true'}">
                                ${actionBean.catatanKeg}&nbsp;
                    </c:if>
                    </p>
                </div>
                <p>
                    <label>Bayaran (RM) :</label>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.permohonanTuntutanKos.amaunTuntutan}&nbsp;
                    </c:if>
                     Setahun
                </p>
                <p>
                    <label>Luas Diluluskan:</label>
                        <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.luasDiluluskan}&nbsp;
                    </c:if>
                     <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.luasLulusUom.nama}&nbsp;
                    </c:if>
                    </p>
                <%--<p>
                    <label>Syarat Tambahan :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:textarea name="plpulasan2" rows="5" cols="80" />
                    </c:if>
                     <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.plpulasan2}&nbsp;
                    </c:if>
                </p>--%>
                <p>
                    <label>Syarat Tambahan :</label>
                     <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.ulsn}&nbsp;
                    </c:if>
                </p>
                </div>             

               <p id="plpstidaksokong">
                    <label>Sebab :</label>
                    <c:if test="${actionBean.display eq 'true'}">
                        
                        <c:if test="${actionBean.fasaPermohonan.ulasan eq null}">
                        	- &nbsp;
                        </c:if>
                        <c:if test="${actionBean.fasaPermohonan.ulasan ne null}">
                        	${actionBean.fasaPermohonan.ulasan}&nbsp;
                        </c:if>
                    </c:if>
                </p>               
            </c:if>
            <%--if PRMP--%>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRMP'}">

                <p>
                    <label>Syor Penolong Pegawai:</label>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.fasaPermohonan.keputusan.nama}&nbsp;
                    </c:if>
                </p>

                <div id="prmpsokong">
                <p>
                    <label>Luas Diluluskan:</label>
                        <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.luasDiluluskan}&nbsp;
                    </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.luasLulusUom.nama}&nbsp;
                    </c:if>
                    </p>
                </div>             

               <p id="prmptidaksokong">
                    <label>Sebab :</label>
                    <c:if test="${actionBean.display eq 'true'}">
                        
                        <c:if test="${actionBean.fasaPermohonan.ulasan eq null}">
                        	- &nbsp;
                        </c:if>
                        <c:if test="${actionBean.fasaPermohonan.ulasan ne null}">
                        	${actionBean.fasaPermohonan.ulasan}&nbsp;
                        </c:if>
                    </c:if>
                </p>               
            </c:if>
<!--                For LPSP-->
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">

                <p>
                    <label>Syor :</label>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.fasaPermohonan.keputusan.nama}&nbsp;
                    </c:if>
                </p>
                 <div id="lpspsokong">
                     <p>
                    <label> Jumlah meter padu : </label>
                    <s:text name="permohonanBahanBatuan.jumlahIsipadu" size="10"/>
                    <s:select name="jumlahIsipaduUom.kod" id="isipaduUOM" value="${actionBean.permohonanBahanBatuan.jumlahIsipaduUom.kod}">
                    <s:option value="">Sila Pilih</s:option>
                    <s:option value="MP">Meterpadu</s:option>
                    <s:option value="KB">Ketul Batu</s:option>
                    </s:select>
                     </p>
                     <p> 
                    <label>Tempoh: </label><s:text name="permohonanBahanBatuan.tempohDisyor" size="20" id="tempoh" maxlength="3" onkeyup="validateNumber(this,this.value);" value="${actionBean.permohonanBahanBatuan.tempohDisyor}"/>
                <s:select name="tempohSyorUOM" id="tempohUOM">
                    <s:option value="">Sila Pilih</s:option>
                    <s:option value="HR">Hari</s:option>
                    <s:option value="B">Bulan</s:option>
                    <s:option value="T">Tahun</s:option>
                </s:select>
                    </p>
                 
                <p>
                    <label>Bayaran (RM) :</label>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.permohonanTuntutanKos.amaunTuntutan}&nbsp;
                    </c:if>
                     Setahun
                </p>
                <p>
                    <label>Luas Diluluskan:</label>
                        <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.luasDiluluskan}&nbsp;
                    </c:if>
                     <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.luasLulusUom.nama}&nbsp;
                    </c:if>
                    </p>
                </div>             

               <p id="lpsptidaksokong">
                    <label>Sebab :</label>
                    <c:if test="${actionBean.display eq 'true'}">
                        
                        <c:if test="${actionBean.fasaPermohonan.ulasan eq null}">
                        	- &nbsp;
                        </c:if>
                        <c:if test="${actionBean.fasaPermohonan.ulasan ne null}">
                        	${actionBean.fasaPermohonan.ulasan}&nbsp;
                        </c:if>
                    </c:if>
                </p>               
            </c:if>
                <%--Batuan--%>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB' || actionBean.permohonan.kodUrusan.kod eq 'PBPTD' || actionBean.permohonan.kodUrusan.kod eq 'PBPTG'}">
                       <p>
                    <label>Syor :</label>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.fasaPermohonan.keputusan.nama}&nbsp;
                    </c:if>
                </p>
                <div id="batuansokong">
                    <c:if test="${actionBean.display eq 'true'}">
                        <p>
                            <label> Jumlah meter padu : </label>
                            ${actionBean.permohonanBahanBatuan.jumlahIsipadu}
                            ${actionBean.permohonanBahanBatuan.jumlahIsipaduUom.nama}
                       
                        </p>
                        <p> 
                            <label>Tempoh: </label>
                            ${actionBean.permohonanBahanBatuan.tempohDisyor}
                            ${actionBean.permohonanBahanBatuan.tempohSyorUom.nama}
                        </p>
                    </c:if>
                    </div>
                  <p id="batuantidaksokong">
                    <label>Sebab :</label>
                    <c:if test="${actionBean.display eq 'true'}">
                        
                        <c:if test="${actionBean.fasaPermohonan.ulasan eq null}">
                        	- &nbsp;
                        </c:if>
                        <c:if test="${actionBean.fasaPermohonan.ulasan ne null}">
                        	${actionBean.fasaPermohonan.ulasan}&nbsp;
                        </c:if>
                    </c:if>
                </p>           
                </c:if>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
                <c:if test="${actionBean.display eq 'true'}">
                     <table align="left" border="0" width="85%"><tr><td>                             
                            <table id ="dataTable2" border="0">
                                <tr><td valign="top">
                             <label><font color="red">*</font>Ulasan :</label></td>
                                    <td><s:textarea name="plpulasan1" id="plpulasan1"  rows="7" cols="69" class="normal_text"></s:textarea></td>
                                </tr>
                                <c:set var="i" value="2" />
                                <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line" begin="1">
                                    <tr><td style="display:none">${line.idLaporUlas}</td>
                                        <td></td>
                                        <td>
                                            <s:textarea name="plpulasan${i}" id="plpulasan${i}"  rows="7" cols="69" class="normal_text" value="${line.ulasan}"></s:textarea></td>
                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table></td></tr></table>
                </c:if>
                
        </fieldset></div>
        <br/><br/><br/><br/>
                <div class="subtitle">
        
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <c:if test="${actionBean.kodNegeri eq '05'}">
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                <c:if test="${actionBean.stageId ne 'laporan_tanah'}">
                    
                    <legend>Ulasan Penolong Pegawai Tanah (Kanan)</legend> </br>
                    <c:if test="${actionBean.stageId eq 'semak_laporan_tanah'}">
                    <p align="center"><s:textarea  id="ulasanKanan" name="ulasanKanan" cols="150"  rows="5" class="normal_text" value="${actionBean.ulasanKanan}"/></p>
                    <p align="center"><s:button  name="ulasanPPTKanan" id="ulasanPPTKanan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
                    <s:button  name="kemaskiniUlasan" id="kemaskiniUlasan" value="Kemaskini" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/></p>
                    </c:if>
                    <c:if test="${actionBean.stageId eq 'kenalpasti_jabatan_teknikal'||actionBean.stageId eq 'terima_ulasan_teknikal'||actionBean.stageId eq 'draf_jktd' ||actionBean.stageId eq 'semak_draf_jktd1' ||actionBean.stageId eq 'semak_draf_jktd2'||actionBean.stageId eq 'persetujuan_draf_jktd'}">
                        <label>Ulasan :</label>${actionBean.ulasanKanan}&nbsp;
                    </c:if>
                        
                </c:if>
            </c:if>
           </c:if>
        </fieldset> 
    </div>
</s:form>