
<%-- Document  : hantar_PU
    Created on : March 03, 2011, 7:21:10 PM
    Author     : latifah.iskak--%>

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
       <%--alert(temp);--%>
        return temp;
    }

    function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {
        <%--var stageId = "g_hantar_pu";--%>
         // replace " " with "_"

         strNama = ReplaceAll(strNama," ","_");
         strJawatan = ReplaceAll(strJawatan," ","_");
         strStageID = ReplaceAll(strStageID," ","_");
         alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strStageID);
        <%--
        alert ("stageid:" + stageId);--%>
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


        objShell.Run(sysVar("eTanahGISPU") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + strStageID);
    }

</script>


<s:form beanclass="etanah.view.penguatkuasaan.Pu1ActionBean" name="form">

    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
            <title>JSP Page</title>
        </head>
        <body>
            <s:button name="transferFile" id="btnClick" value="Hantar Surat Iringan" class="longbtn" onclick="doSubmit(this.form, this.name, 'page_div')" />
            <%--<s:button name="hantarPU" id="hantarPU" value="Hantar PU" class="btn"  onclick="RunProgram('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;--%>
        </body>
    </html>
</s:form>