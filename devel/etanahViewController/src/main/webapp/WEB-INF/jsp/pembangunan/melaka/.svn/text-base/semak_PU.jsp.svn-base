<%-- 
    Document   : semak_PU
    Created on : Mar 17, 2011, 7:20:20 PM
    Author     : NageswaraRao
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
        var strIDStage = "g_semak_pu";
        alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
        strNama = ReplaceAll(strNama," ","_");
        strJawatan = ReplaceAll(strJawatan," ","_");
        stageId = strIDStage;
//        var stageId = "g_semak_pu";
        //stageId = strIDStage;
        //alert("nama:" + strNama);
        //alert ("jawatan:" + strJawatan);
//        alert ("stageid:" + stageId);
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")
       objShell.Run(sysVar("eTanahGISPU") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + stageId);
    }
</script>


<s:form beanclass="etanah.view.stripes.pembangunan.SemakPUActionBean" name="form">

    <html>

        <body>
            <br><br>
            <p align="center">
            <s:button name="semak" id="semak" value="Semak PU" class="btn"  onclick="RunProgram('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
            </p>
        </body>
    </html>
</s:form>