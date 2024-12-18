<%-- 
    Document   : laporan_pelanV2
    Created on : Feb 19, 2013, 1:12:41 PM
    Author     : afham
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>


<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<script type="text/javascript">
    function reload(val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelanV2?reload&idHakmilik=' + val;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }

    function reloadPT(val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelanV2?reload&noPtSementara=' + val;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }

    function openFrame(type) {
        doBlockUI();
        var idHakmilik = $('#idHakmilik').val();
        var noPtSementara = $('#noPtSementara').val();
        //        alert(idHakmilik);
        window.open("${pageContext.request.contextPath}/pelupusan/laporan_pelanV2?openFrame&idHakmilik="
                + idHakmilik + "&noPtSementara=" + noPtSementara + "&type=" + type, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }

    function refreshV2(type) {
        var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelanV2?refreshpage&type=' + type;
        $.get(url,
                function(data) {
                    $('#page_div').html(data);
                }, 'html');
        doUnBlockUI();
    }

    function refreshV2ManyHakmilik(type, hakmilik) {
        var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelanV2?refreshpage&type=' + type + '&idHakmilik=' + hakmilik;
        $.get(url,
                function(data) {
                    $('#page_div').html(data);
                }, 'html');
        doUnBlockUI();
    }

    function ReplaceAll(Source, stringToFind, stringToReplace) {
        var temp = Source;
        var index = temp.indexOf(stringToFind);
        while (index != -1) {
            temp = temp.replace(stringToFind, stringToReplace);
            index = temp.indexOf(stringToFind);

        }
        return temp;
    }

    function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {

        var strNama2 = ReplaceAll(strNama, " ", "_");
        var strJawatan2 = ReplaceAll(strJawatan, " ", "_");
        var strStageID2 = "g_charting_permohonan";
        //        var strStageID2 = strStageID;
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama2 + " " + strJawatan2 + " " + strIDPermohonan + " " + strStageID2);
    }
    function RunProgramPLPS(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {

        var strNama2 = ReplaceAll(strNama, " ", "_");
        var strJawatan2 = ReplaceAll(strJawatan, " ", "_");
        var strStageID2 = "g_charting_keputusan";
        //var strStageID2 = "g_charting_permohonan";
        //        var strStageID2 = strStageID;
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama2 + " " + strJawatan2 + " " + strIDPermohonan + " " + strStageID2);
    }
    function RunProgramSemak(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {

        var strNama2 = ReplaceAll(strNama, " ", "_");
        var strJawatan2 = ReplaceAll(strJawatan, " ", "_");
        var strStageID2 = "g_semak_permohonan";
        //var strStageID2 = "g_charting_permohonan";
        //        var strStageID2 = strStageID;
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama2 + " " + strJawatan2 + " " + strIDPermohonan + " " + strStageID2);
    }
    function RunProgram4A(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {

        var strNama2 = ReplaceAll(strNama, " ", "_");
        var strJawatan2 = ReplaceAll(strJawatan, " ", "_");
        var strStageID2 = "g_penyediaan_permit";
        //var strStageID2 = "g_charting_permohonan";
        //        var strStageID2 = strStageID;
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama2 + " " + strJawatan2 + " " + strIDPermohonan + " " + strStageID2);
    }
    function RunProgramKeputusan(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {

        var strNama2 = ReplaceAll(strNama, " ", "_");
        var strJawatan2 = ReplaceAll(strJawatan, " ", "_");
        var strStageID2 = "g_charting_keputusan";
        //var strStageID2 = "g_charting_permohonan";
        //        var strStageID2 = strStageID;
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama2 + " " + strJawatan2 + " " + strIDPermohonan + " " + strStageID2);
    }
    function RunProgramSemak4A(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {

        var strNama2 = ReplaceAll(strNama, " ", "_");
        var strJawatan2 = ReplaceAll(strJawatan, " ", "_");
        var strStageID2 = "g_semak_permohonan";
        //var strStageID2 = "g_charting_permohonan";
        //        var strStageID2 = strStageID;
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama2 + " " + strJawatan2 + " " + strIDPermohonan + " " + strStageID2);
    }
    function RunProgramPWGSA(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {

        var strNama2 = ReplaceAll(strNama, " ", "_");
        var strJawatan2 = ReplaceAll(strJawatan, " ", "_");
        //var strStageID2 = "g_charting_keputusan";
        var strStageID2 = "g_charting_permohonan";
        //        var strStageID2 = strStageID;
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama2 + " " + strJawatan2 + " " + strIDPermohonan + " " + strStageID2);
    }
    function RunProgram2(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {

        var strNama2 = ReplaceAll(strNama, " ", "_");
        var strJawatan2 = ReplaceAll(strJawatan, " ", "_");
        var strStageID2 = "g_semak_permohonan";
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama2 + " " + strJawatan2 + " " + strIDPermohonan + " " + strStageID2);
    }

    function RunProgramPWGSA2(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {

        var strNama2 = ReplaceAll(strNama, " ", "_");
        var strJawatan2 = ReplaceAll(strJawatan, " ", "_");
        var strStageID2 = "g_semak_keputusan";
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama2 + " " + strJawatan2 + " " + strIDPermohonan + " " + strStageID2);
    }
</script>

<s:form beanclass="etanah.view.stripes.pelupusan.LaporanPelanV2ActionBean">
    <s:errors/>
    <s:messages/>
    <s:hidden name="idHakmilik" id="idHakmilik"/>
    <s:hidden name="noPtSementara" id="noPtSementara"/>
    <div class="subtitle displaytag" align="center">
        <fieldset class="aras1">
            <legend>Senarai Hakmilik Terlibat</legend>
            <div align="center">
                <c:if test="${fn:length(actionBean.senaraiHakmilikPermohonan) > 1}">
                    <p>
                        <font size="2" color="red">
                        <b>Permohonan Melibatkan Banyak Hakmilik</b>
                        </font>
                    </p>
                </c:if>
            </div>
            <div align="center">
                <c:if test="${fn:length(actionBean.senaraiHakmilikPermohonan) > 1}">
                    <font color="#003194" style="font-weight: bold; font-family:Tahoma; font-size: 13px;">
                    Hakmilik :
                    </font>
                </c:if>
                <c:if test="${fn:length(actionBean.senaraiHakmilikPermohonan) < 1}">
                    <font color="#003194" style="font-weight: bold; font-family:Tahoma; font-size: 13px;">
                    &nbsp;
                    </font>
                </c:if>
                <c:if test="${edit}">
                    <c:if test="${fn:length(actionBean.senaraiHakmilikPermohonan) > 1}">
                        <s:select name="idHakmilik" onchange="reload(this.value);" id="hakmilik">
                            <c:forEach items="${actionBean.senaraiHakmilikPermohonan}" var="item" varStatus="line">
                                <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                                    ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                                </s:option>
                            </c:forEach>
                        </s:select>
                    </c:if>
                    <c:if test="${fn:length(actionBean.senaraiHakmilikPermohonan) < 1}">
                        <c:set var="i" value="1" />
                        <s:select name="noPtSementara" onchange="reloadPT(this.value);" id="hakmilik">
                            <c:forEach items="${actionBean.senaraiNoPt}" var="item" varStatus="line">
                                <s:option value="${item.noPtSementara}" style="width:400px">
                                    Laporan Pelan ${i}
                                </s:option>
                                <c:set var="i" value="${i+1}" />
                            </c:forEach>
                        </s:select>
                    </c:if>
                </c:if>
                <c:if test="${!edit}">
                    <c:if test="${fn:length(actionBean.senaraiHakmilikPermohonan) > 1}">
                        <s:select name="idHakmilik" onchange="reload(this.value);" id="hakmilik">
                            <c:forEach items="${actionBean.senaraiHakmilikPermohonan}" var="item" varStatus="line">
                                <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                                    ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                                </s:option>
                            </c:forEach>
                        </s:select>
                    </c:if>
                    <c:if test="${fn:length(actionBean.senaraiHakmilikPermohonan) < 1}">
                        <c:set var="i" value="1" />
                        <s:select name="noPtSementara" onchange="reloadPT(this.value);" id="hakmilik">
                            <c:forEach items="${actionBean.senaraiNoPt}" var="item" varStatus="line">
                                <s:option value="${item.noPtSementara}" style="width:400px">
                                    Laporan Pelan ${i}
                                </s:option>
                                <c:set var="i" value="${i+1}" />
                            </c:forEach>
                        </s:select>
                    </c:if>                
                </c:if>
            </div>
        </fieldset> <br>
        <c:if test="${actionBean.stageId ne 'syor_SDP' and actionBean.stageId ne 'keputusan_SDP' and actionBean.stageId ne 'sdp'}">
            <div class="subtitle">
                <fieldset class="aras1" id="locate">
                    <legend>
                        Charting 
                    </legend>

                    <p align="center">
                        Sila charting pada lot tanah yang dipohon sebelum menyediakan laporan pelan.
                    </p> <br><br>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PWGSA'}">
                        <c:if test="${actionBean.disLaporanPelanController.charting}">
                            <p>
                                <s:button name="chart" id="chart" value="Charting" class="btn" onclick="RunProgramPWGSA('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                            </p>
                        </c:if>
                        <c:if test="${!actionBean.disLaporanPelanController.charting}">
                            <p>
                                <s:button name="chart" id="chart" value="Semak Charting" class="longbtn" onclick="RunProgramPWGSA2('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                            </p>
                        </c:if>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS'}">
                        <c:if test="${actionBean.disLaporanPelanController.charting}">
                            <p>
                                <s:button name="chart" id="chart" value="Charting" class="btn" onclick="RunProgram('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                            </p>
                        </c:if>
                        <c:if test="${!actionBean.disLaporanPelanController.charting}">
                            <c:if test="${actionBean.stageId eq 'g_kemaskini_charting'}">
                                <p>
                                    <s:button name="chart" id="chart" value="Semak Charting" class="longbtn" onclick="RunProgramPLPS('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                                </p>
                            </c:if>
                            <c:if test="${actionBean.stageId eq 'semak_charting'}">
                                <p>
                                    <s:button name="chart" id="chart" value="Semak Charting" class="longbtn" onclick="RunProgramSemak('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                                </p>
                            </c:if>
                            <c:if test="${actionBean.stageId eq 'g_charting_4a'}">
                                <p>
                                    <s:button name="chart" id="chart" value="Charting" class="longbtn" onclick="RunProgram4A('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                                </p>
                            </c:if>
                            <c:if test="${actionBean.stageId eq 'g_charting_keputusan'}">
                                <p>
                                    <s:button name="chart" id="chart" value="Charting" class="longbtn" onclick="RunProgramKeputusan('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                                </p>
                            </c:if>
                            <c:if test="${actionBean.stageId eq 'g_semak_permit'}">
                                <p>
                                    <s:button name="chart" id="chart" value="Charting" class="longbtn" onclick="RunProgramSemak4A('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                                </p>
                            </c:if>
                        </c:if>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PWGSA' && actionBean.permohonan.kodUrusan.kod ne 'PLPS'}">
                        <c:if test="${actionBean.disLaporanPelanController.charting}">
                            <p>
                                <s:button name="chart" id="chart" value="Charting" class="btn" onclick="RunProgram('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                            </p>
                        </c:if>
                        <c:if test="${!actionBean.disLaporanPelanController.charting}">
                            <p>
                                <s:button name="chart" id="chart" value="Semak Charting" class="longbtn" onclick="RunProgram2('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                            </p>
                        </c:if>
                    </c:if>

                </fieldset> <br>
            </div>
        </c:if>

        <div class="subtitle">
            <fieldset class="aras1" id="locate">
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS'}">
                    <c:if test="${actionBean.stageId eq 'g_charting_keputusan'}">
                        <legend>
                            Maklumat Luas Baru
                            <span style="float:right" align="right">
                                <%--<a onclick="viewPerihalTanah();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                                <%--<c:if test="${actionBean.disLaporanPelanController.LuasBaru}">--%>
                                <a onclick="openFrame('LuasBaru');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                    <%--</c:if>--%>
                            </span>
                        </legend>
                        <table class="tablecloth" border="0">
                            <tr>
                                <td>
                                    Luas Baru :
                                </td>
                                <td>
                                    ${actionBean.hakmilikPermohonan.luasDiluluskan}
                                    <%--<s:text name="hakmilikPermohonan.luasDiluluskan" formatPattern="0.0000" maxlength="15" onkeyup="validateNumber(this,this.value);" id="Luas"/>--%>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Kod :
                                </td>
                                <td>
                                    ${actionBean.hakmilikPermohonan.luasLulusUom.nama}
                                    <%--<s:select name="kodUnitLuas.kod" value="${actionBean.hakmilikPermohonan.luasLulusUom.kod}" id="kULuas">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:option value="H">Hektar</s:option>
                                        <s:option value="M">Meter Persegi</s:option>
                                    </s:select>--%>
                                </td>
                            </tr>
                        </table>
                        <%--<fieldset class="aras1">
                            <table  width="100%" border="0">
                                <tr>
                                    <td align="center">
                                        <s:submit name="simpanLuasBaru" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                                    </td>
                                </tr>
                            </table>
                        </fieldset>--%>
                    </c:if>
                </c:if>
            </fieldset>
        </div>
        <br>

        <div class="subtitle">
            <fieldset class="aras1" id="locate">
                <legend>
                    Maklumat Tanah Dipohon
                </legend>
                <c:set var ="statusHakmilik" value="T"/>
                <display:table class="tablecloth" name="${actionBean.senaraiHakmilikPermohonan}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                               requestURI="/pelupusan/laporan_pelanV2">
                    <c:if test="${line.hakmilik ne null}">
                        <display:column title="No" sortable="true">${line_rowNum}
                            <s:hidden name="x" class="x${line_rowNum -1}" value="${line.id}"/></display:column>
                        <display:column title="No.Lot/PT" property="hakmilik.noLot"/>
                        <display:column title="Lot/PT" property="hakmilik.lot.nama"/> 
                        <display:column title="Bandar/Pekan/Mukim" property="hakmilik.bandarPekanMukim.nama"/>
                        <display:column title="Seksyen">
                            <c:if test="${line.hakmilik.seksyen ne null}">
                                ${line.hakmilik.seksyen.nama}
                            </c:if>
                            <c:if test="${line.hakmilik.seksyen eq null}">
                                -
                            </c:if>
                        </display:column>
                        <display:column title="Daerah" property="hakmilik.bandarPekanMukim.daerah.nama"/>
                    </c:if>
                    <c:if test="${line.hakmilik eq null}">
                        <c:set var ="statusHakmilik" value="TW"/>
                        <display:column title="No" sortable="true">${line_rowNum}
                            <s:hidden name="x" class="x${line_rowNum -1}" value="${line.id}"/></display:column>
                        <display:column title="No.Lot/PT" property="noLot" />
                        <display:column title="Lot/PT" property="lot.nama"/> 
                        <display:column title="Bandar/Pekan/Mukim" property="bandarPekanMukimBaru.nama"/>
                        <display:column title="Seksyen ">
                            <c:if test="${actionBean.hakmilikPermohonan.kodSeksyen ne null}">
                                ${actionBean.hakmilikPermohonan.kodSeksyen.nama}
                            </c:if>
                            <c:if test="${actionBean.hakmilikPermohonan.kodSeksyen eq null}">
                                -
                            </c:if>
                        </display:column>
                        <display:column title="Daerah" property="bandarPekanMukimBaru.daerah.nama"/>
                    </c:if>
                </display:table>
            </fieldset> <br>
        </div>
        <div class="subtitle">
            <fieldset class="aras1" id="locate">
                <legend>
                    Status Tanah
                    <span style="float:right" align="right">
                        <%--<a onclick="viewPerihalTanah();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%> 
                        <c:if test="${actionBean.disLaporanPelanController.sTanah}">
                            <a onclick="openFrame('sTanah');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                            </c:if>                    
                    </span>
                </legend>
                <table class="tablecloth" border="0">
                    <tr>
                        <td>
                            No. Lembaran Piawai :
                        </td>
                        <td>
                            <c:if test="${actionBean.noLitho ne null}">
                                ${actionBean.noLitho}
                            </c:if>
                            <c:if test="${actionBean.noLitho eq null}">
                                -
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPRU' && actionBean.permohonan.kodUrusan.kod ne 'BMBT' && actionBean.permohonan.kodUrusan.kod ne 'PJBTR'}">
                            <td>
                                Luas :
                            </td>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS' || actionBean.permohonan.kodUrusan.kod eq 'PBPTD'}">
                                <td>
                                    <s:format value="${actionBean.luas}" formatPattern="#,###,##0.0000"/> ${actionBean.kodLuas}
                                </td>
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                                <td>
                                    <s:format value="${actionBean.hakmilikPermohonan.luasTerlibat}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                                </td>
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PLPS' && actionBean.permohonan.kodUrusan.kod ne 'PBPTD' && actionBean.permohonan.kodUrusan.kod ne 'PBMT'}">
                                <td>
                                    <s:format value="${actionBean.hakmilikPermohonan.luasDiluluskan}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.luasLulusUom.nama}
                                </td>
                            </c:if>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU'}">
                            <td>
                                Keluasan Terlibat (Isipadu) :
                            </td> 
                            <td>
                                <s:format value="${actionBean.hakmilikPermohonan.luasDiluluskan}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.luasLulusUom.nama}
                            </td>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJBTR'}">
                            <td>
                                Isipadu :
                            </td> 
                            <td>
                                <s:format value="${actionBean.hakmilikPermohonan.luasTerlibat}" formatPattern="#,###,##0.0000"/>  ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                            </td>
                        </c:if>    
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT'}">
                            <td>
                                Luas Atas Tanah :
                            </td> 
                            <td>
                                <s:format value="${actionBean.hakmilikPermohonan.luasTerlibat}" formatPattern="#,###,##0.0000"/>  ${actionBean.hakmilikPermohonan.hakmilik.kodUnitLuas.nama}
                            </td>
                        </c:if>                    

                    </tr>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT'}">
                        <tr>                      
                            <td>
                                Isipadu :
                            </td> 
                            <td>
                                <s:format value="${actionBean.hakmilikPermohonan.luasTerlibat}" formatPattern="#,###,##0.0000"/>  ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                            </td>

                        </tr>
                    </c:if>
                    <tr>
                        <td>
                            Status Tanah :
                        </td>
                        <td>
                            <c:if test="${actionBean.kodPString ne null}">
                                ${actionBean.kodPString}
                            </c:if>
                            <c:if test="${actionBean.kodPString eq null}">
                                -
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Jenis Tanah :
                        </td>
                        <td>
                            <c:if test="${actionBean.permohonanLaporanPelan.kodTanah ne null}">
                                ${actionBean.permohonanLaporanPelan.kodTanah.nama}
                            </c:if>
                            <c:if test="${actionBean.permohonanLaporanPelan eq null or actionBean.permohonanLaporanPelan.kodTanah eq null}">
                                -
                            </c:if>
                        </td>
                    </tr>
                    <c:if test="${actionBean.permohonanLaporanPelan.kodTanah.kod eq '99'}">
                        <tr>
                            <td>
                                Sila Nyatakan : 
                            </td>
                            <td>
                                <c:if test="${actionBean.ulasan ne null}">
                                    ${actionBean.ulasan}
                                </c:if>
                                <c:if test="${actionBean.ulasan eq null}">
                                    -
                                </c:if>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>
                            Jika ditanda untuk projek kerajaan, sila nyatakan :
                        </td>
                        <td>
                            <c:if test="${actionBean.projekKerajaan ne null}">
                                ${actionBean.projekKerajaan}
                            </c:if>
                            <c:if test="${actionBean.projekKerajaan eq null}">
                                -
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Kawasan Parlimen :
                        </td>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS' || actionBean.permohonan.kodUrusan.kod eq 'PBPTD' || actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                            <td>
                                ${actionBean.kodKawasanParlimen}&nbsp;
                            </td>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PLPS' && actionBean.permohonan.kodUrusan.kod ne 'PBPTD' && actionBean.permohonan.kodUrusan.kod ne 'PBMT'}">
                            <td>
                                ${actionBean.hakmilikPermohonan.kodKawasanParlimen.nama}&nbsp;
                            </td>
                        </c:if>
                    </tr>
                    <tr>
                        <td>
                            DUN :
                        </td>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS' || actionBean.permohonan.kodUrusan.kod eq 'PBPTD' || actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                            <td>
                                ${actionBean.kodDUN}&nbsp;
                            </td>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PLPS' && actionBean.permohonan.kodUrusan.kod ne 'PBPTD' && actionBean.permohonan.kodUrusan.kod ne 'PBMT'}">
                            <td>
                                ${actionBean.hakmilikPermohonan.kodDUN.nama}&nbsp;
                            </td>
                        </c:if>
                    </tr>
                </table>
            </fieldset> <br>
        </div>
        <div class="subtitle">
            <fieldset class="aras1" id="locate">
                <legend>
                    Permohonan Terdahulu
                    <span style="float:right">
                        <%--<a onclick="viewPerihalTanah();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%> 
                        <c:if test="${actionBean.disLaporanPelanController.pTerdahulu}">
                            <a onclick="openFrame('pTerdahulu');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                            </c:if>                    
                    </span>
                </legend>
                <display:table class="tablecloth" name="${actionBean.listpermohonanTanahTerdahulu}" pagesize="10" cellpadding="0" cellspacing="0" id="line1">
                    <s:hidden name="" class="${line1_rowNum -1}" value="${line1.permohonanManualSemasa.idMohonManual}"/>
                    <display:column title="No">${line1_rowNum}</display:column>
                    <display:column title="ID Permohanan/No Fail">
                        <c:if test="${line1.permohonanTerdahulu ne null}">
                            ${line1.permohonanTerdahulu.idPermohonan}
                        </c:if>
                        <c:if test="${line1.permohonanTerdahulu eq null}">
                            ${line1.noFail}
                        </c:if>
                    </display:column>
                    <display:column title="Urusan" property="permohonanTerdahulu.kodUrusan.nama"/>
                    <display:column title="Status Keputusan" property="statusKeputusan" />
                    <display:column title="Keputusan Oleh" property="keputusanOleh" />
                    <display:column title="Tarikh Keputusan" >
                        <s:format value="${line1.tarikhKeputusan}" formatPattern="dd/MM/yyyy"/>
                    </display:column>
                    <display:column title="Nama Pemohon" property="namaPemohon" />
                </display:table> <br/>
            </fieldset> <br>
        </div>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend> 
                    Dalam Kawasan
                    <span style="float:right">
                        <%--<a onclick="viewPerihalTanah();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%> 
                        <c:if test="${actionBean.disLaporanPelanController.dKawasan}">
                            <a onclick="openFrame('dKawasan');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                            </c:if>                    
                    </span>
                </legend>

                <p>Tanah Dipohon Berada Dalam Kawasan :
                <div id="DalamKawasanDiv">
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
                    </display:table><br/>
                </div>
            </fieldset> <br>
        </div>
        <div class="subtitle">
            <fieldset class="aras1" id="locate">
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PBMT'}">
                    <legend>
                        Lain-lain Hal
                        <span style="float:right">
                            <%--<a onclick="viewPerihalTanah();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                            <c:if test="${actionBean.disLaporanPelanController.catatan}">
                                <a onclick="openFrame('catatan');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                </c:if>
                        </span>
                    </legend>

                    <table class="tablecloth" border="0">
                        <tr>
                            <td>
                                Lain-lain hal / Catatan :
                            </td>
                            <td>
                                <c:if test="${actionBean.catatan ne null}">
                                    ${actionBean.catatan}
                                </c:if>
                                <c:if test="${actionBean.catatan eq null}">
                                    -
                                </c:if> 
                            </td>
                        </tr>
                    </table>
                </c:if>

                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                    <legend>
                        Catatan
                        <span style="float:right">
                            <%--<a onclick="viewPerihalTanah();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                            <c:if test="${actionBean.disLaporanPelanController.catatan}">
                                <a onclick="openFrame('catatan');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                </c:if>
                        </span>
                    </legend>
                    <table class="tablecloth" border="0">
                        <tr>
                            <td>
                                Jika Lain-lain, Sila Nyatakan :
                            </td>
                            <td>
                                <c:if test="${actionBean.catatan ne null}">
                                    ${actionBean.catatan}
                                </c:if>
                                <c:if test="${actionBean.catatan eq null}">
                                    -
                                </c:if> 
                            </td>
                        </tr>
                    </table>
                </c:if>
            </fieldset>
        </div>
    </div>
</s:form>