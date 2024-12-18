<%-- 
    Document   : penyediaan_permit
    Created on : Apr 5, 2013, 4:58:33 PM
    Author     : afham
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<script type="text/javascript">

    function RunProgramPermit(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {

        var strNama2 = ReplaceAll(strNama, " ", "_");
        var strJawatan2 = ReplaceAll(strJawatan, " ", "_");
        if (strStageID == 'g_penyediaan_tol') {
            var strStageID2 = "g_penyediaan_tol";
        } else {
            var strStageID2 = "g_penyediaan_permit";
        }
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama2 + " " + strJawatan2 + " " + strIDPermohonan + " " + strStageID2);
    }

//    function muatNaik() {
//       window.open("${pageContext.request.contextPath}/pelupusan/gisIntegration?papar&result="+$("#result").val()+"&path="+$("#path").val(),
//                "status=0,toolbar=0,location=0,menubar=0,width=1400px,height=800px,scrollbars=yes");
//    
//    }
    function muatNaik() {
        window.open("${pageContext.request.contextPath}/pelupusan/gisIntegration?papar&result=" + $("#result").val() + "&path=" + $("#path").val(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1400px,height=800px,scrollbars=yes");
    }


</script>

<s:form beanclass="etanah.view.stripes.pelupusan.GISIntegrationActionBean" name="form">
    <s:hidden name="path" id="path" value="${actionBean.path}"/>
    <s:hidden name="result" id="result" value="${actionBean.result}"/>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
            <title>JSP Page</title>
        </head>
        <body>
            <br>
            <br>
            <p align="center">
                <s:button name="chartingPermit" id="chartingPermit" style="font-size: 12px" value="Charting Permit" class="longbtn"  onclick="RunProgramPermit('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
            </p>
            <p>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTD'}">
                <p align="center">
                    <s:button name="upload" id="upload" style="font-size: 12px" value="Muatnaik P1" class="longbtn"  onclick="muatNaik();"/>&nbsp;
                </p>
            </c:if>
        </p>
    </body>
</html>
</s:form>
