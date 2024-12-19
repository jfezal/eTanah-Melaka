<%-- 
    Document   : rekod_keputusanMMK
    Created on : Mar 27, 2013, 10:56:53 AM
    Author     : afham
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
        //        alert(idHakmilik);
        window.open("${pageContext.request.contextPath}/pelupusan/rekod_keputusanMMKV2?openFrame"
            +"&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }
        
    function refreshRekodKeputusanMMKV2(type){
        var url = '${pageContext.request.contextPath}/pelupusan/rekod_keputusanMMKV2?refreshpage&type='+type;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        doUnBlockUI();
    }
    
    function editMohonHakmilik(idMohonHakmilik,type){
        doBlockUI();
        //        alert(idHakmilik);
        window.open("${pageContext.request.contextPath}/pelupusan/rekod_keputusanMMKV2?editDetails&idMH="+idMohonHakmilik
            +"&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }
        
    function reload (val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/pelupusan/rekod_keputusanMMKV2?reload&idHakmilik=' + val;
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
        //alert("nama:" + strNama);
        //alert ("jawatan:" + strJawatan);
        strIDStage = "g_charting_keputusan";
        //alert ("stageid:" + strIDStage);
        //alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);

        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")
        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + strIDStage);
    }
    
    function RunProgram3(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {
        strNama = ReplaceAll(strNama," ","_");
        strJawatan = ReplaceAll(strJawatan," ","_");
        //alert("nama:" + strNama);
        //alert ("jawatan:" + strJawatan);
        strIDStage = "g_charting_semak";
        //alert ("stageid:" + strIDStage);
        //alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);

        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")
        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + strIDStage);
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
</script>

<s:form beanclass="etanah.view.stripes.pelupusan.RekodKeputusanMMKV2ActionBean">
    <s:errors/>
    <s:messages/>
    <div class="subtitle" align="center">

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
                    <c:choose>
                        <c:when test="${actionBean.kodNegeri eq '05'}">
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                                <tr>
                                    <td>Status Mesyuarat</td>
                                    <c:choose>
                                        <c:when test="${actionBean.mohonFasa.keputusan.kod eq 'XN'}">
                                            <td>telah Mendapat Keputusan</td>
                                        </c:when>
                                        <c:when test="${actionBean.mohonFasa.keputusan.kod eq '1M'}">
                                            <td>Ditangguh</td>
                                        </c:when>   
                                        <c:otherwise>
                                            <td></td>
                                        </c:otherwise>
                                    </c:choose>                                        
                                </tr>
                            </c:if>
                        </c:when>
                        <c:when test="${actionBean.kodNegeri eq '04'}">
                            <!--
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP' or actionBean.permohonan.kodUrusan.kod eq 'PPRU'}">
                                <tr>
                                    <td>Status Mesyuarat</td>
                                    <c:choose>
                                        <c:when test="${actionBean.mohonFasa.keputusan.kod eq 'L'}">
                                            <td>Lulus</td>
                                        </c:when>
                                        <c:when test="${actionBean.mohonFasa.keputusan.kod eq 'T'}">
                                            <td>Tolak</td>
                                        </c:when>   
                                        <c:otherwise>
                                            <td></td>
                                        </c:otherwise>
                                    </c:choose>                                        
                                </tr>
                            </c:if>
                            -->
                        </c:when>        
                    </c:choose>    
                    <tr>
                        <td>Nama Setiausaha MMK :</td>
                        <td> ${actionBean.permohonanKertas.penyediaSidang}&nbsp;</td>
                    </tr>            
                    <tr>
                        <td>Bilangan Mesyuarat :</td>
                        <td> ${actionBean.permohonanKertas.nomborRujukan}&nbsp;</td>
                    </tr>
                    <%--  
                    <tr>
                        <td>Keputusan :</td>
                        <td>${actionBean.permohonan.keputusan.nama}&nbsp;</td>
                    </tr>
                    --%>
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

        <div id="senaraiHakmilik">
            <fieldset class="aras1">
                <legend>Senarai Permohonan Belum Diputuskan <%--Senarai Hakmilik Tiada Keputusan--%>
                    <span style="float:right">
                        <c:if test="${actionBean.disRekodKeputusanController.sHakmilik}">
                            <a onclick="openFrame('sHakmilik');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                            </c:if>                    
                    </span>
                </legend>
                <display:table class="tablecloth" name="${actionBean.senaraiHakmilikTiadaKeputusan}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                               requestURI="/pelupusan/rekod_keputusanV2">
                    <display:column title="No">${line_rowNum}</display:column>
                    <c:if test="${actionBean.kelompok eq true}">
                        <display:column title="ID Permohonan" >${line.permohonan.idPermohonan}</display:column> 
                    </c:if>    
                    <c:if test="${line.hakmilik ne null}">
                        <display:column title="ID Hakmilik" >${line.hakmilik.idHakmilik}</display:column> 
                        <display:column title="No.Lot/PT">
                            <c:if test="${line.noLot eq null}">
                                ${line.hakmilik.noLot}
                            </c:if>
                            <c:if test="${line.noLot ne null}">
                                ${line.noLot}
                            </c:if>
                        </display:column>
                        <display:column title="Bandar/Pekan/Mukim">
                            <c:if test="${line.bandarPekanMukimBaru.nama eq null}">
                                ${line.hakmilik.bandarPekanMukim.nama}
                            </c:if>
                            <c:if test="${line.noLot ne null}">
                                ${line.bandarPekanMukimBaru.nama}
                            </c:if>                                
                        </display:column>
                        <display:column title="Nama Pemohon" >
                            <c:forEach items="${actionBean.permohonan.senaraiPemohon}" var="pemohon">
                                ${pemohon.pihak.nama}
                            </c:forEach>
                        </display:column>
                        <display:column title="No Pengenalan/No Syarikat/Dll" >
                            <c:forEach items="${actionBean.permohonan.senaraiPemohon}" var="pemohon">
                                ${pemohon.pihak.noPengenalan}
                            </c:forEach>
                        </display:column>
                    </c:if>
                    <c:if test="${line.hakmilik eq null}">
                        <display:column title="No.Lot/PT" >${line.noLot}</display:column>
                        <display:column title="Bandar/Pekan/Mukim" >${line.bandarPekanMukimBaru.nama}</display:column>
                        <display:column title="Nama Pemohon" >
                            <c:forEach items="${actionBean.permohonan.senaraiPemohon}" var="pemohon">
                                ${pemohon.pihak.nama}
                            </c:forEach>
                        </display:column>
                        <display:column title="No Pengenalan/No Syarikat/Dll" >
                            <c:forEach items="${actionBean.permohonan.senaraiPemohon}" var="pemohon">
                                ${pemohon.pihak.noPengenalan}
                            </c:forEach>
                        </display:column>
                    </c:if>
                </display:table>        
                <br>        
            </fieldset>
        </div>

        <div id="senaraiHakmilikLulus">
            <fieldset class="aras1">
                <legend>Senarai Permohonan Diluluskan<%--Senarai Hakmilik Lulus--%></legend>
                <display:table class="tablecloth" name="${actionBean.senaraiHakmilikLulus}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                               requestURI="/pelupusan/rekod_keputusanV2">
                    <display:column title="No">${line_rowNum}</display:column>
                    <c:if test="${actionBean.kelompok eq true}">
                        <display:column title="ID Permohonan" >${line.permohonan.idPermohonan}</display:column> 
                    </c:if>
                    <c:if test="${line.hakmilik ne null}">
                        <display:column title="ID Hakmilik" >${line.hakmilik.idHakmilik}</display:column> 
                        <display:column title="No.Lot/PT">
                            <c:if test="${line.noLot eq null}">
                                ${line.hakmilik.noLot}
                            </c:if>
                            <c:if test="${line.noLot ne null}">
                                ${line.noLot}
                            </c:if>
                        </display:column>
                        <display:column title="Bandar/Pekan/Mukim">
                            <c:if test="${line.bandarPekanMukimBaru.nama eq null}">
                                ${line.hakmilik.bandarPekanMukim.nama}
                            </c:if>
                            <c:if test="${line.noLot ne null}">
                                ${line.bandarPekanMukimBaru.nama}
                            </c:if>                                
                        </display:column>
                        <display:column title="Nama Pemohon" >
                            <c:forEach items="${actionBean.permohonan.senaraiPemohon}" var="pemohon">
                                ${pemohon.pihak.nama}
                            </c:forEach>
                        </display:column>
                        <display:column title="No Pengenalan/No Syarikat/Dll" >
                            <c:forEach items="${actionBean.permohonan.senaraiPemohon}" var="pemohon">
                                ${pemohon.pihak.noPengenalan}
                            </c:forEach>
                        </display:column>
                    </c:if>
                    <c:if test="${line.hakmilik eq null}">
                        <display:column title="No.Lot/PT" >${line.noLot}</display:column>
                        <display:column title="Bandar/Pekan/Mukim" >${line.bandarPekanMukimBaru.nama}</display:column>
                        <display:column title="Nama Pemohon" >
                            <c:forEach items="${actionBean.permohonan.senaraiPemohon}" var="pemohon">
                                ${pemohon.pihak.nama}
                            </c:forEach>
                        </display:column>
                        <display:column title="No Pengenalan/No Syarikat/Dll" >
                            <c:forEach items="${actionBean.permohonan.senaraiPemohon}" var="pemohon">
                                ${pemohon.pihak.noPengenalan}
                            </c:forEach>
                        </display:column>
                    </c:if>
                    <display:column title="Tindakan">
                        <c:if test="${actionBean.disRekodKeputusanController.sKelulusan}">
                            <a onclick="editMohonHakmilik(${line.id},'update')" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                            </c:if>
                            <c:if test="${!actionBean.disRekodKeputusanController.sHakmilik}">
                            <a onclick="editMohonHakmilik(${line.id},'view')" onmouseover="this.style.cursor='pointer';"><img alt="Papar"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                            </c:if>
                        </display:column>
                    </display:table>        
                <br>        
            </fieldset>
        </div> 


        <div id="senaraiHakmilikTolak">
            <fieldset class="aras1">
                <legend>Senarai Permohonan Ditolak<%--Senarai Hakmilik Tolak--%></legend>
                <display:table class="tablecloth" name="${actionBean.senaraiHakmilikTolak}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                               requestURI="/pelupusan/rekod_keputusanV2">
                    <display:column title="No">${line_rowNum}</display:column>
                    <c:if test="${actionBean.kelompok eq true}">
                        <display:column title="ID Permohonan" >${line.permohonan.idPermohonan}</display:column> 
                    </c:if>
                    <c:if test="${line.hakmilik ne null}">
                        <display:column title="ID Hakmilik" >${line.hakmilik.idHakmilik}</display:column> 
                        <display:column title="No.Lot/PT">
                            <c:if test="${line.noLot eq null}">
                                ${line.hakmilik.noLot}
                            </c:if>
                            <c:if test="${line.noLot ne null}">
                                ${line.noLot}
                            </c:if>
                        </display:column>
                        <display:column title="Bandar/Pekan/Mukim">
                            <c:if test="${line.bandarPekanMukimBaru.nama eq null}">
                                ${line.hakmilik.bandarPekanMukim.nama}
                            </c:if>
                            <c:if test="${line.noLot ne null}">
                                ${line.bandarPekanMukimBaru.nama}
                            </c:if>                                
                        </display:column>
                        <display:column title="Nama Pemohon" >
                            <c:forEach items="${actionBean.permohonan.senaraiPemohon}" var="pemohon">
                                ${pemohon.pihak.nama}
                            </c:forEach>
                        </display:column>
                        <display:column title="No Pengenalan/No Syarikat/Dll" >
                            <c:forEach items="${actionBean.permohonan.senaraiPemohon}" var="pemohon">
                                ${pemohon.pihak.noPengenalan}
                            </c:forEach>
                        </display:column>
                    </c:if>
                    <c:if test="${line.hakmilik eq null}">
                        <display:column title="No.Lot/PT" >${line.noLot}</display:column>
                        <display:column title="Bandar/Pekan/Mukim" >${line.bandarPekanMukimBaru.nama}</display:column>
                        <display:column title="Nama Pemohon" >
                            <c:forEach items="${actionBean.permohonan.senaraiPemohon}" var="pemohon">
                                ${pemohon.pihak.nama}
                            </c:forEach>
                        </display:column>
                        <display:column title="No Pengenalan/No Syarikat/Dll" >
                            <c:forEach items="${actionBean.permohonan.senaraiPemohon}" var="pemohon">
                                ${pemohon.pihak.noPengenalan}
                            </c:forEach>
                        </display:column>
                    </c:if>
                </display:table>        
                <br>        
            </fieldset>
        </div> 


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
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT' || actionBean.permohonan.kodUrusan.kod eq 'PLPS'}">
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
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                    <c:if test="${actionBean.stageId eq 'g_charting_keputusan'}">
                        <br>

                        <%-- <p align="center">
                             Sila charting pada lot tanah yang dipohon sebelum menyediakan laporan pelan.
                         </p> <br><br>--%>
                        <p align="center">
                            <s:button name="chart" id="chart" value="Charting" class="btn" onclick="RunProgram2('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                        </p>
                    </c:if>
                    <c:if test="${actionBean.stageId eq 'g_charting_keputusan_L'}">
                        <br>

                        <%-- <p align="center">
                             Sila charting pada lot tanah yang dipohon sebelum menyediakan laporan pelan.
                         </p> <br><br>--%>
                        <p align="center">
                            <s:button name="chart" id="chart" value="Charting" class="btn" onclick="RunProgram2('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                        </p>
                    </c:if>
                    <c:if test="${actionBean.stageId eq 'semak_charting_keputusan'}">
                        <br>

                        <%-- <p align="center">
                             Sila charting pada lot tanah yang dipohon sebelum menyediakan laporan pelan.
                         </p> <br><br>--%>
                        <p align="center">
                            <s:button name="chart" id="chart" value="Semak Charting" class="longbtn" onclick="RunProgram3('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                        </p>
                    </c:if>
                    <c:if test="${actionBean.stageId eq 'semak_chart_kpsn_L'}">
                        <br>

                        <%-- <p align="center">
                             Sila charting pada lot tanah yang dipohon sebelum menyediakan laporan pelan.
                         </p> <br><br>--%>
                        <p align="center">
                            <s:button name="chart" id="chart" value="Semak Charting" class="longbtn" onclick="RunProgram3('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                        </p>
                    </c:if>
                        <c:if test="${actionBean.stageId eq 'semak_chart_kpsn'}">
                        <br>

                        <%-- <p align="center">
                             Sila charting pada lot tanah yang dipohon sebelum menyediakan laporan pelan.
                         </p> <br><br>--%>
                        <p align="center">
                            <s:button name="chart" id="chart" value="Semak Charting" class="longbtn" onclick="RunProgram3('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                        </p>
                    </c:if>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                    <c:if test="${actionBean.stageId eq 'g_charting_keputusan'}">
                        <br>

                        <%-- <p align="center">
                             Sila charting pada lot tanah yang dipohon sebelum menyediakan laporan pelan.
                         </p> <br><br>--%>
                        <p align="center">
                            <s:button name="chart" id="chart" value="Charting" class="btn" onclick="RunProgram2('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                        </p>
                    </c:if>
                    <c:if test="${actionBean.stageId eq 'semak_charting_keputusan'}">
                        <br>

                        <%-- <p align="center">
                             Sila charting pada lot tanah yang dipohon sebelum menyediakan laporan pelan.
                         </p> <br><br>--%>
                        <p align="center">
                            <s:button name="chart" id="chart" value="Semak Charting" class="longbtn" onclick="RunProgram3('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                        </p>
                    </c:if>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PWGSA'}">
                    <c:if test="${actionBean.stageId eq 'g_charting_keputusan'}">
                        <br>

                        <%-- <p align="center">
                             Sila charting pada lot tanah yang dipohon sebelum menyediakan laporan pelan.
                         </p> <br><br>--%>
                        <p align="center">
                            <s:button name="chart" id="chart" value="Charting" class="btn" onclick="RunProgram2('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                        </p>
                    </c:if>
                    <c:if test="${actionBean.stageId eq 'semak_charting_keputusan'}">
                        <br>

                        <%-- <p align="center">
                             Sila charting pada lot tanah yang dipohon sebelum menyediakan laporan pelan.
                         </p> <br><br>--%>
                        <p align="center">
                            <s:button name="chart" id="chart" value="Semak Charting" class="longbtn" onclick="RunProgram3('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                        </p>
                    </c:if>
                </c:when>   
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PRMP'}">
                    <c:if test="${actionBean.stageId eq 'g_charting_keputusan'}">
                        <br>

                        <%-- <p align="center">
                             Sila charting pada lot tanah yang dipohon sebelum menyediakan laporan pelan.
                         </p> <br><br>--%>
                        <p align="center">
                            <s:button name="chart" id="chart" value="Charting" class="btn" onclick="RunProgram2('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                        </p>
                    </c:if>
                    <c:if test="${actionBean.stageId eq 'semakan_ck_T' or  actionBean.stageId eq 'semak_charting_keputusan'}">
                        <br>

                        <%-- <p align="center">
                             Sila charting pada lot tanah yang dipohon sebelum menyediakan laporan pelan.
                         </p> <br><br>--%>
                        <p align="center">
                            <s:button name="chart" id="chart" value="Semak Charting" class="longbtn" onclick="RunProgram3('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                        </p>
                    </c:if>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBRZ'}">
                    <c:if test="${actionBean.stageId eq 'g_charting_keputusan'}">
                        <br>

                        <%-- <p align="center">
                             Sila charting pada lot tanah yang dipohon sebelum menyediakan laporan pelan.
                         </p> <br><br>--%>
                        <p align="center">
                            <s:button name="chart" id="chart" value="Charting" class="btn" onclick="RunProgram2('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                        </p>
                    </c:if>
                    <c:if test="${actionBean.stageId eq 'g_charting_keputusanT'}">
                        <br>

                        <%-- <p align="center">
                             Sila charting pada lot tanah yang dipohon sebelum menyediakan laporan pelan.
                         </p> <br><br>--%>
                        <p align="center">
                            <s:button name="chart" id="chart" value="Charting" class="btn" onclick="RunProgram2('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                        </p>
                    </c:if>
                        <c:if test="${actionBean.stageId eq 'g_charting_keputusanT2'}">
                        <br>

                        <%-- <p align="center">
                             Sila charting pada lot tanah yang dipohon sebelum menyediakan laporan pelan.
                         </p> <br><br>--%>
                        <p align="center">
                            <s:button name="chart" id="chart" value="Charting" class="btn" onclick="RunProgram2('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                        </p>
                    </c:if>
                    <c:if test="${actionBean.stageId eq 'semak_charting_keputusan'}">
                        <br>

                        <%-- <p align="center">
                             Sila charting pada lot tanah yang dipohon sebelum menyediakan laporan pelan.
                         </p> <br><br>--%>
                        <p align="center">
                            <s:button name="chart" id="chart" value="Semak Charting" class="longbtn" onclick="RunProgram3('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                        </p>
                    </c:if>
                        <c:if test="${actionBean.stageId eq 'semak_charting_keputusan2'}">
                        <br>

                        <%-- <p align="center">
                             Sila charting pada lot tanah yang dipohon sebelum menyediakan laporan pelan.
                         </p> <br><br>--%>
                        <p align="center">
                            <s:button name="chart" id="chart" value="Semak Charting" class="longbtn" onclick="RunProgram3('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                        </p>
                    </c:if>
                </c:when>
            </c:choose>
        </div>
    </div>


</s:form>

