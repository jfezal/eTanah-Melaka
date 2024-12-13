<%-- 
    Document   : kemaskini_hakmilik
    Created on : Nov 2, 2012, 03:07:56 PM
    Author     : zabedah.zainal
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

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

    function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {
        strNama = ReplaceAll(strNama," ","_");
        strJawatan = ReplaceAll(strJawatan," ","_");
//        alert("nama:" + strNama);
//        alert ("jawatan:" + strJawatan);
        strIDStage = "g_kemaskini_hakmilik";
//        alert ("stageid:" + strIDStage);
//        alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);

        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + strIDStage);
    }
    function RunProgram6(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {
        strNama = ReplaceAll(strNama," ","_");
        strJawatan = ReplaceAll(strJawatan," ","_");
//        alert("nama:" + strNama);
//        alert ("jawatan:" + strJawatan);
        strIDStage = "g_charting_ft";
//        alert ("stageid:" + strIDStage);
//        alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);

        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + strIDStage);
    }
        function RunProgram5(strUserID, strNama, strJawatan, strIDPermohonan, strStageID2) {

        var strNama2 = ReplaceAll(strNama," ","_");
        var strJawatan2 = ReplaceAll(strJawatan," ","_");
        var strStageID2 = "g_kemaskini_warta";
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama2 + " " +  strJawatan2 + " " + strIDPermohonan + " " + strStageID2);
    }

    
</script>


<s:form beanclass="etanah.view.stripes.pelupusan.GISIntegrationActionBean" name="form">

    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
            <title>JSP Page</title>
        </head>
        <body>
            <br>
            <br>
            <p align="center">
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'WMRE' and actionBean.permohonan.kodUrusan.kod ne 'BMRE'}">
                <s:button name="kemaskiniHakmilik" id="kemaskiniHakmilik" value="Charting Kemaskini Hakmilik" class="longbtn"  onclick="RunProgram('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                    <s:button name="kemaskiniHakmilik" id="kemaskiniHakmilik" value="Charting Kemaskini Hakmilik" class="longbtn"  onclick="RunProgram6('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'WMRE' or actionBean.permohonan.kodUrusan.kod eq 'MMRE' or actionBean.permohonan.kodUrusan.kod eq 'BMRE'}">
              <s:button name="" id="btnClick" value="Kemaskini Warta" class="longbtn" onclick="RunProgram5('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.permohonan.idPermohonan}','${actionBean.stageId}');"/>
                </c:if>
                  </p>
        </body>
    </html>
</s:form>
