<%-- 
    Document   : semak_charting
    Created on : Sept 26, 2012, 02:43:56 AM
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

    <%-- function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {
        
     alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
                 strNama = ReplaceAll(strNama," ","_");
                 strJawatan = ReplaceAll(strJawatan," ","_");
                 var stageId = "g_semak_charting_pw";
                 alert("nama:" + strNama);
                 alert ("jawatan:" + strJawatan);
                 alert ("stageid:" + stageId);
                 var objShell = new ActiveXObject("WScript.Shell");
                 var sysVar = objShell.Environment("System");
                 objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + stageId);
             }
     var stageId = "g_penyediaan_pu";
         // replace " " with "_"

        strNama = ReplaceAll(strNama," ","_");
        strJawatan = ReplaceAll(strJawatan," ","_");
        strStageID = ReplaceAll(strStageID," ","_");
        alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strStageID);
   
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
           

        objShell.Run(sysVar("eTanahGISPU") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + strStageID);
    }--%>
</script>


<s:form beanclass="etanah.view.stripes.pelupusan.GISIntegrationActionBean" name="form">

    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
            <title>JSP Page</title>
        </head>
        <body>
            <br><br>
            <p align="center">
                <s:button name="sediaGazette" id="sediaGazette" value="Charting Rizab" class="btn"  onclick="RunProgram('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
            </p>
        </body>
    </html>
</s:form>