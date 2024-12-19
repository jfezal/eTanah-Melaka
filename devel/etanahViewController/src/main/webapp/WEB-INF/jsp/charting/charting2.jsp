<%--
    Document   : Charting 2 - to be used by other modules
    Created on : 13 May 2010
    Author     : Hairudin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<script type="text/javascript">
    function RunProgram(strUserID, strJawatan, strNoResit ,strIDStage)
    {
        alert(strIDStage);
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //alert(sysVar("eTanahGIS"));
        objShell.Run(sysVar("eTanahGIS") + " " + strUserID + " " + strJawatan + " " + strNoResit + "" + strIDStage);
    }

</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<div class="subtitle">
	<fieldset class="aras1">
		<legend>Charting</legend>
		<p>&nbsp;</p>
		<p>
			<button onclick="RunProgram ('${actionBean.pguna.idPengguna}','${actionBean.jawatan}','0','${actionBean.stageId}')" class="btn">CHARTING</button>
		</p>
                ${actionBean.stageId}
               
	</fieldset>
</div>
