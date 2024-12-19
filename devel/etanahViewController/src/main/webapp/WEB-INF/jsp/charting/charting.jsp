<%--
    Document   : Charting
    Created on : Mar 24, 2010, 10:01:17 AM
    Author     : User
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<table width="100%" bgcolor="green">
    <tr><td width="100%" height="20" ><div style="background-color: green;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">CHARTING</font>
            </div></td></tr>
</table>




<table width="100%" bgcolor="green">

    <script type="text/javascript">
        function RunProgram(strUserID, strJawatan, strNoResit)
        {
            var objShell = new ActiveXObject("WScript.Shell");
            var sysVar = objShell.Environment("System");
            //alert(sysVar("eTanahGIS"));
            objShell.Run(sysVar("eTanahGISCharting") + " " + strUserID + " " + strJawatan + " " + strNoResit + "");
        }
    </script>
    
</table>&nbsp;&nbsp;
<br><br><br><br><br><br><br><br><br><br><br><br>


<p align="center"><table border="0" bgcolor="green" width="100%">

    <tr>

        <td align="right">

            <button onclick="RunProgram ('${actionBean.pguna.idPengguna}','${actionBean.jawatan}','0')" class="btn">CHARTING</button>
    </tr>

</table>




