<%-- 
    Document   : pengambilan_deposit
    Created on : Dec 15, 2012, 4:46:44 PM
    Author     : User
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

        //            maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

        var bil = ${fn:length(actionBean.hakmilikPermohonan.hakmilik.senaraiPihakBerkepentingan)};
        for (var i = 0; i < bil; i++){
            if($("#pilihIdPihak"+i).val() == $('#idPihakInfoPembayaran').val()){
                document.getElementById("pilihIdPihak"+i).checked = true;
            }
        }

    }); //END OF READY FUNCTION



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
    function openFrame(type){
        NoPrompt();
        var idHakmilik = $('#idHakmilik').val();
        //    alert(idHakmilik);
        window.open("${pageContext.request.contextPath}/pelupusan/pendepositV2?openFrame&idHakmilik="
            +idHakmilik+"&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }
    <%--   function refreshpage(){
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
       }--%>
           function dodacheck(val) {
               //var mikExp = /[1\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\1\|]/;
               var v = $('#jenisPengenalan').val();
               if(v == 'B') {
                   var strPass = val;
                   var strLength = strPass.length;
                   if(strLength > 12) {
                       var tst = val.substring(0, (strLength) - 1);
                       $('#noPengenalan').val(tst);
                   }
                   var lchar = val.charAt((strLength) - 1);
                   if(isNaN(lchar)) {
                       //return false;
                       var tst = val.substring(0, (strLength) - 1);
                       $('#noPengenalan').val(tst);
                   }
               }
           }

           function clearNoPengenalan(){
               $('#noPengenalan').val('');
           }


           function populatePenyerah(btn){
               var url;
               var DELIM = "__^$__";
               if (btn.id == "carianPihak"){
                   $('#kod').val('2');
                   var jP = $("#jenisPengenalan").val();
                   var noP = $("#noPengenalan").val();
                   if (jP == null || jP.length == 0 || noP == null || noP.length == 0){
                       alert("Sila masukkan Jenis Pengenalan/No.Pengenalan.");
                       return;
                   }
                   url = "${pageContext.request.contextPath}/common/req_pihak_info?jenisPengenalan=" + jP
                       + "&noPengenalan=" + noP;
               }

               $.get(url,
               function(data){
                   if (data == null || data.length == 0){
                       alert("Maklumat tidak dijumpai");
                       $("#nama").val("");
                       $("#alamat1").val("");
                       $("#alamat2").val("");
                       $("#alamat3").val("");
                       $("#alamat4").val("");
                       $("#poskod").val("");
                       $("#negeri").val("");
                       $("#noTelefon1").val("");
                       $("#emel").val("");
                       return;
                   }
                   var p = data.split(DELIM);
                   $('#jenisPengenalan').val(p[0]);
                   $('#noPengenalan').val(p[1]);
                   $("#nama").val(p[2]);
                   $("#alamat1").val(p[3]);
                   $("#alamat2").val(p[4]);
                   $("#alamat3").val(p[5]);
                   $("#alamat4").val(p[6]);
                   $("#poskod").val(p[7]);
                   $("#negeri").val(p[8]);
                   $("#noTelefon1").val(p[9]);
                   $("#emel").val(p[10]);
               });
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
        function p(v){
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });

            $.get("${pageContext.request.contextPath}/pengambilan/depositMahkamah?pihakDetails&idPihak="+v,
            function(data){
                //alert(data);
                $("#perincianHakmilik").show();
                $("#perincianHakmilik").html(data);
                $.unblockUI();
            });

        }

    </script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pengambilan.PengambilanPendepositActionBean" name="form">
        <s:errors/>
        <s:messages/>

        <div class="subtitle" id="main">
            <fieldset class="aras1">
                <s:hidden name="idHakmilik" id="idHakmilik"/>
                <s:hidden name="idPihak" id="idPihak"/>
                <div id="maklumatpendeposit">
                    <legend>Sila Pilih Tuan Tanah yang terlibat</legend>
                    <div class="subtitle">

                        <%--<c:if test="${actionBean.permohonanPihakPendeposit eq null}">--%>
                        <fieldset class="aras1">
                            <div class="content" align="center">
                                <p>
                                    <display:table style="width:75%" class="tablecloth" name="${actionBean.hakmilikPermohonan.hakmilik.senaraiPihakBerkepentingan}"
                                                   cellpadding="1" cellspacing="1" requestURI="/pengambilan/kemasukanaduan1" id="line">
                                        <display:column title="Pilih">
                                            <s:radio name="radio_" id="pilihIdPihak${line_rowNum-1}" value="${line.pihak.idPihak}" style="width:15px"
                                                     onclick="p('${line.pihak.idPihak}');return false;"/>
                                            <s:hidden name="hiddenIdPihak" id="hiddenIdPihak${line_rowNum-1}" value="${line.pihak.idPihak}"/>
                                            <%-- <s:link beanclass="etanah.view.stripes.pengambilan.PampasanPHLLActionBean"
                                                            event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                                        <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>
                                                        <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>
                                                        <s:param name="simpanLaporan" value="${actionBean.simpanLaporan}"/>
                                                        <font style="text-transform:uppercase;"><c:out value="${senarai.pihak.nama}"/></font>
                                                    </s:link>--%>
                                        </display:column>
                                        <display:column title="Nama " property="pihak.nama" style="text-transform:uppercase;vertical-align:top;" />
                                        <%--<display:column title="No. Pengenalan" property="pihak.idPihak" style="vertical-align:top;"/>--%>
                                        <display:column title="No. Pengenalan">
                                            <c:if test="${line.pihak.jenisPengenalan.kod eq 'B'}">
                                                No.KP Baru :&nbsp;
                                                ${line.pihak.noPengenalan}
                                            </c:if>
                                            <c:if test="${line.pihak.jenisPengenalan.kod eq 'L'}">
                                                No.KP Lama :&nbsp;
                                                ${line.pihak.noPengenalan}
                                            </c:if>
                                            <c:if test="${line.pihak.jenisPengenalan.kod eq 'S'}">
                                                No.Syarikat :&nbsp;
                                                ${line.pihak.noPengenalan}
                                            </c:if>
                                            <c:if test="${line.pihak.jenisPengenalan.kod eq 'D'}">
                                                No.Pendaftaran :&nbsp;
                                                ${line.pihak.noPengenalan}
                                            </c:if>
                                            <c:if test="${line.pihak.jenisPengenalan.kod eq 'F'}">
                                                No.Paksa :&nbsp;
                                                ${line.pihak.noPengenalan}
                                            </c:if>
                                            <c:if test="${line.pihak.jenisPengenalan.kod eq 'I'}">
                                                No.Polis :&nbsp;
                                                ${line.pihak.noPengenalan}
                                            </c:if>
                                            <c:if test="${line.pihak.jenisPengenalan.kod eq 'K'}">
                                                No.MyKid :&nbsp;
                                                ${line.pihak.noPengenalan}
                                            </c:if>
                                            <c:if test="${line.pihak.jenisPengenalan.kod eq 'N'}">
                                                No.Bank :&nbsp;
                                                ${line.pihak.noPengenalan}
                                            </c:if>
                                            <c:if test="${line.pihak.jenisPengenalan.kod eq 'P'}">
                                                No.Passport :&nbsp;
                                                ${line.pihak.noPengenalan}
                                            </c:if>
                                            <c:if test="${line.pihak.jenisPengenalan.kod eq 'T'}">
                                                No.Tentera :&nbsp;
                                                ${line.pihak.noPengenalan}
                                            </c:if>
                                            <c:if test="${line.pihak.jenisPengenalan.kod eq 'U'}">
                                                No.Pertubuhan :&nbsp;
                                                ${line.pihak.noPengenalan}
                                            </c:if>
                                        </display:column>
                                    </display:table>

                                </p>
                            </div>
                        </fieldset>
                        <%--</c:if>--%>
                    </div>

                    <br/>
                </div>
            </fieldset>
        </div>
    </s:form>
    <div id="perincianHakmilik">
    </div>
</body>