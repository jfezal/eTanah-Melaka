<%-- 
    Document   : rekod_keputusan
    Created on : Nov 11, 2012, 12:38:05 AM
    Author     : Afham
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<script type="text/javascript">
    function openFrame(type){
        doBlockUI();
        var idHakmilik = $('#idHakmilik').val();
        //        alert(idHakmilik);
        window.open("${pageContext.request.contextPath}/pengambilan/rekod_keputusan?openFrame&idHakmilik="
            +idHakmilik+"&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }
        
    function refreshRekodKeputusan(type){
        var url = '${pageContext.request.contextPath}/pengambilan/rekod_keputusan?refreshpage&type='+type;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        doUnBlockUI();
    }
        
    function reload (val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/pengambilan/rekod_keputusan?reload&idHakmilik=' + val;
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

    function RunProgram2(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {
        strNama = ReplaceAll(strNama," ","_");
        strJawatan = ReplaceAll(strJawatan," ","_");
        alert("nama:" + strNama);
        alert ("jawatan:" + strJawatan);
        strIDStage = "g_charting_keputusan";
        alert ("stageid:" + strIDStage);
        alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);

        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")
        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + strIDStage);
    }
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.RekodKeputusanActionBean">
    <s:errors/>
    <s:messages/>
    <s:hidden name="idHakmilik" id="idHakmilik"/>
    <div class="subtitle" align="center">
        <fieldset class="aras1">
            <%--<legend>Senarai Hakmilik Terlibat aaaaaaaaaaa</legend>
            <div align="center">
                <c:if test="${fn:length(actionBean.hakmilikPermohonanList) > 1}">
                    <p>
                        <font size="2" color="red">
                            <b>Permohonan Melibatkan Banyak Hakmilik</b>
                        </font>
                    </p>
                </c:if>
            </div>--%>
            <%--<div align="center">
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

                 <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT'}">
                     <c:if test="${actionBean.stageId eq 'g_charting_keputusan'}">
                         <legend>
                             Charting
                         </legend>

                        <p align="center">
                            Sila charting pada lot tanah yang dipohon sebelum menyediakan laporan pelan.
                        </p> <br><br>
                        <p align="center">
                            <s:button name="chart" id="chart" value="Charting" class="btn" onclick="RunProgram2('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                        </p>
                    </c:if>
                </c:if>
                
            </div>--%>
        </fieldset> 
        <div id="maklumat_permohonan">
            <fieldset class="aras1">
                <legend>Maklumat Permohonan</legend>
                <table class="tablecloth" border="0">
                    <tr>
                        <td>ID Permohonan / Perserahan :</td>
                        <td>${actionBean.permohonan.idPermohonan}&nbsp;</td>
                    </tr>
                    <tr>
                        <td>Urusan :</td>
                        <td>${actionBean.permohonan.kodUrusan.nama}&nbsp;</td>
                    </tr>
                </table>
            </fieldset>
        </div>
        <div id="keputusan_permohonan">
            <fieldset class="aras1">
                <legend>Maklumat Mesyuarat
                    <span style="float:right">
                        <c:if test="${actionBean.disRekodKeputusanController.mMesyuarat}">
                            <a onclick="openFrame('mMesyuarat');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                            </c:if>                    
                    </span>
                </legend>
                <table class="tablecloth" border="0">
                    <tr>
                        <td>Bilangan Mesyuarat :</td>
                        <td> ${actionBean.permohonanKertas.nomborRujukan}&nbsp;</td>
                    </tr>
                    <tr>
                        <td>Keputusan :</td>
                        <td>${actionBean.permohonan.keputusan.nama}&nbsp;</td>
                    </tr>
                    <tr>
                        <td>Tempat Bersidang :</td>
                        <td>${actionBean.permohonanKertas.tempatSidang}&nbsp;</td>
                    </tr>
                    <tr>
                        <td>Tarikh Bersidang :</td>
                        <td><s:format value="${actionBean.permohonanKertas.tarikhSidang}" formatPattern="dd/MM/yyyy"/>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>Tarikh Disahkan :</td>
                        <td><s:format value="${actionBean.permohonanKertas.tarikhSahKeputusan}" formatPattern="dd/MM/yyyy"/>&nbsp;</td>
                    </tr>
                </table>
            </fieldset>


        </div>




        <%--<c:if test="${actionBean.permohonan.keputusan.kod eq 'L'}">

            <div id="syarat">
                <fieldset class="aras1">
                    <legend>Syarat-syarat 
                        <span style="float:right">
                            <c:if test="${actionBean.disRekodKeputusanController.sKelulusan}">
                                <a onclick="openFrame('sKelulusan');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                </c:if> 
                        </span>
                    </legend>

                    <c:choose>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT'}">
                            <c:import url="keputusanView/syarat/syaratBMBT.jsp"></c:import>
                        </c:when>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PJBTR'}">
                            <c:import url="keputusanView/syarat/syaratPJBTR.jsp"></c:import>
                        </c:when>
                        <c:otherwise>

                        </c:otherwise>
                    </c:choose>
                </fieldset>
            </div>
        </c:if>--%>

        <div id="butang_charting">
            <c:choose>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PJBTR'}">
                    <c:if test="${actionBean.stageId eq 'g_charting_keputusan' or actionBean.stageId eq 'semak_charting_kpsn' or actionBean.stageId eq 'semak_charting_kpsn_lulus'}">
                        <br>

                       <%-- <p align="center">
                            Sila charting pada lot tanah yang dipohon sebelum menyediakan laporan pelan.
                        </p> <br><br>--%>
                        <p align="center">
                            <s:button name="chart" id="chart" value="Charting" class="btn" onclick="RunProgram2('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                        </p>
                    </c:if>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT'}">
                    <c:if test="${actionBean.stageId eq 'g_charting_keputusan'}">
                        <br>

                       <%-- <p align="center">
                            Sila charting pada lot tanah yang dipohon sebelum menyediakan laporan pelan.
                        </p> <br><br>--%>
                        <p align="center">
                            <s:button name="chart" id="chart" value="Charting" class="btn" onclick="RunProgram2('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                        </p>
                    </c:if>
                </c:when>
            </c:choose>
        </div>
    </div>


</s:form>
