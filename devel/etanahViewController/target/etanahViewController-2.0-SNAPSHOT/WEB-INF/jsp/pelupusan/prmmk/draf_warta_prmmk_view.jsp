<%-- 
    Document   : draf_warta_priz
    Created on : Sept 12, 2012, 11:33:19 AM
    Author     : Ctzainal
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<script type="text/javascript">
    function ReplaceAll(Source,stringToFind,stringToReplace){
        var temp = Source;
        var index = temp.indexOf(stringToFind);
        while(index != -1){
            temp = temp.replace(stringToFind,stringToReplace);
            index = temp.indexOf(stringToFind);

        }
        return temp;
    }

    function RunProgram3(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {
        alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
        strNama = ReplaceAll(strNama," ","_");
        strJawatan = ReplaceAll(strJawatan," ","_");
    <%--var stageId = "g_semak_pu";--%>
            var stageId = strIDStage;
            alert("nama:" + strNama);
            alert ("jawatan:" + strJawatan);
            alert ("stageid:" + stageId);
            var objShell = new ActiveXObject("WScript.Shell");
            var sysVar = objShell.Environment("System");
            //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
            //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")
            objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + stageId);
        }

    <%--function RunProgram3(strUserID, strNama, strJawatan, strIDPermohonan) {

        var strNama = ReplaceAll(strNama," ","_");
        var strJawatan = ReplaceAll(strJawatan," ","_");
        var strStageID = "g_kemaskini_warta";
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + strStageID);
    }--%>

        function semak(strUserID, strNama, strJawatan, strIDPermohonan) {
            var strNama = ReplaceAll(strNama," ","_");
            var strJawatan = ReplaceAll(strJawatan," ","_");
            var strStageID = "g_semak_charting_pw";
            var objShell = new ActiveXObject("WScript.Shell");
            var sysVar = objShell.Environment("System");
            //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
            //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


            objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + strStageID);
        }
</script>
<s:form beanclass="etanah.view.stripes.pelupusan.DrafWartaPRMMKActionBean">

    <s:messages/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Draf Warta Rizab Tanah</legend>
            <p>
                <label>Tujuan Rizab :</label>
                ${actionBean.trizabPermohonan.permohonan.sebab}&nbsp;
                <%--<s:text name="tujuanRizab" size="50" readonly="true"/>--%>
            </p>

            <p>
                <label>Daerah :</label>
                ${actionBean.trizabPermohonan.daerah.nama}&nbsp;
                <%--<s:text name="daerah" size="50" readonly="true"/>--%>
            </p>
            <p>
                <label>Mukim/Pekan/Bandar :</label>
                ${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama}&nbsp;
                <%--<s:text name="mukimBandarPekan" readonly="true" size="50"/>--%>
            </p>
            <p>
                <label>No.Lot :</label>
                ${actionBean.hakmilikPermohonan.noLot}&nbsp;
                <%--<s:text name="noLot" size="10" readonly="true"/>--%>
            </p>
            <p>
                <label>Keluasan Tanah :</label>
                <%--<s:text name="keluasanTanah" size="20" readonly="true"/>--%>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRMMK'}">
                    ${actionBean.hakmilikPermohonan.luasDiluluskan}&nbsp;
                    ${actionBean.hakmilikPermohonan.luasLulusUom.nama}
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PRMMK'}">
                    ${actionBean.hakmilikPermohonan.luasTerlibat}&nbsp;
                    ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                </c:if>


            </p>

            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'MMRE'} || ${actionBean.permohonan.kodUrusan.kod ne 'WMRE'} || ${actionBean.permohonan.kodUrusan.kod ne 'BMRE'} ">
                <p>
                    <label>Pegawai Pengawal :</label>
                    ${actionBean.trizabPermohonan.namaPenjaga}&nbsp;
                    <%--<s:text name="peg_pengawal" size="50" readonly="true"/>--%>
                </p>
                <p>
                    <label>Pengawal Penyelengara :</label>
                    ${actionBean.trizabPermohonan.penjaga}&nbsp;
                    <%--<s:text name="peg_penyelengara" size="50"/>--%>
                </p>
            </c:if>
                
            <p>
                <label>No.Pelan Akui/Pelan Warta :</label>
                ${actionBean.trizabPermohonan.noPW}&nbsp;
                <%--<s:text name="noPelanAkui" size="10"/>--%>
            </p>


            <%-- <p>
                 <label>Tarikh Penyediaan Warta :</label>
                 <s:text name="trizabPermohonan.tarikhPenyediaanWarta"  class="datepicker" id="tarikh" formatPattern="dd/MM/yyyy"/>
             </p> --%>
            <p>
                <label>Tarikh Pengesahan Warta :</label>
                <s:format value="${actionBean.trizabPermohonan.tarikhPengesahanWarta}" formatPattern="dd/MM/yyyy"/>
            </p>

            <br>

            <!--            <p>
                            <label>&nbsp;</label>
            <s:button name="simpanWartaRizabTanah" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
        </p>-->
        </fieldset >
    </div>
    <c:if test="${charting}">
        <div class="content" align="center">
            <fieldset class="aras1">
                <legend>Charting</legend>
                <s:button name="charting" value="Charting" class="btn" onclick="RunProgram3('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.trizabPermohonan.permohonan.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
            </fieldset>
        </div>
    </c:if>

    <c:if test="${SemakCharting}">
        <div class="content" align="center">
            <fieldset class="aras1">
                <legend>Charting</legend>
                <s:button name="charting" value="Charting" class="btn" onclick="semak('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.trizabPermohonan.permohonan.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
            </fieldset>
        </div>
    </c:if>


</s:form>




