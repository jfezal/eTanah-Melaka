<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<title>Untitled 1</title>
	<script type="text/javascript">
        function RunProgram(strUserID, strPassword, strJawatan, strNoResit) {
            var objShell = new ActiveXObject("WScript.Shell");
            var sysVar = objShell.Environment("System");
            objShell.Run(sysVar("eTanahGISCharting") + " " + strUserID + " " + strPassword + " " + strJawatan + " " + strNoResit);
        }

          function runP(x,y,z){
        alert(x);
        alert(y);
        alert(z);
    }
    </script>
</head>

<body>
<%--<div>
        <input type="button" id="btnClick" value="Charting" onclick="runP('ptspoc1','eTanah123','Pembantu_Tadbir_SPOC','1003160501010156')" />
    </div>--%>
</body>

</html>
