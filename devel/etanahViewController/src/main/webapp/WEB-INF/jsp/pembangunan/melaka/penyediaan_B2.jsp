<%-- 
    Document   : penyediaan_B2
    Created on : Mar 9, 2011, 11:58:01 PM
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
        strIDStage="g_terima_b2";
        alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
        strNama = ReplaceAll(strNama," ","_");
        strJawatan = ReplaceAll(strJawatan," ","_");
        //var stageId = "g_terima_b2";
        //alert("nama:" + strNama);
        //alert ("jawatan:" + strJawatan);
        //alert ("stageid:" + stageId);
        stageId = strIDStage;
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
//        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + stageId);
        objShell.Run(sysVar("eTanahGISB1") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + stageId);
    }
    
    function RunProgram2(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {
        strIDStage="g_penyediaan_b2";
        alert(" User id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
        strNama = ReplaceAll(strNama," ","_");
        strJawatan = ReplaceAll(strJawatan," ","_");
        //stageId = "g_penyediaan_b2";
        //alert("nama:" + strNama);
        //alert ("jawatan:" + strJawatan);
        //alert ("stageid:" + stageId);
        stageId = strIDStage;
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
//        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + stageId);
        objShell.Run(sysVar("eTanahGISB1") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + stageId);
    }
   

</script>


<s:form beanclass="etanah.view.stripes.pembangunan.PenyediaanB2ActionBean" name="form">

    <html>

        <body>
            <br><br>
            <p align="center">
                
            <c:if test="${actionBean.b2 == 'Y'}">
            <s:button name="lakarPelan" id="lakarPelan" value="Terima B2" class="longbtn"  onclick="RunProgram('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp; 
            </c:if>
            
            <c:if test="${actionBean.b2 == 'T'}">
            <s:button name="lakarPelan1" id="lakarPelan1" value="Penyediaan B2" class="longbtn"  onclick="RunProgram2('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.penyediaanB2}');"/>&nbsp;
            </c:if>
            </p>
        </body>
    </html>
</s:form>
