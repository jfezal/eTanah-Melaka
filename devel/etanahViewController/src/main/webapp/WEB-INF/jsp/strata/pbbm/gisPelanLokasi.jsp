<%-- 
    Document   : gisStrata
    Author     : faidzal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
    function RunProgram(strUserID, strJawatan, strIDPermohonan, strIDStage,event, f) {
        try{
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.close();
            },'html');
            var objShell = new ActiveXObject("WScript.Shell");
           var sysVar = objShell.Environment("System");
            //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
            //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")
            objShell.Run(sysVar("eTanahStrataGIS") + " " + strUserID + " " + strJawatan + " " + strIDPermohonan + " " + strIDStage);   
        }catch(ex){
            alert(ex);
        }
    }
</script>
<%--Original <s:form name="form1" beanclass="etanah.view.strata.GisStrataActionBean">--%>
<s:form beanclass="etanah.view.strata.GisStrataActionBean">
    <s:messages/>
    <s:errors/>
    <div>
        <fieldset class="aras1">
            <legend>Paparan Pelan</legend>
            <p><font size="1" color="Red"> * </font>Sila klik Pelan Lokasi untuk melihat pelan.</p>
            <br>
            <p>&nbsp;</p>

            <p>
                <%--<input type="button" id="btnClick" value="Strata Pelan" onclick="RunProgram('ptspoc1','Pembantu_Tadbir_SPOC','1003160501010156','123456')" />--%>
                <%--s:button name="transferFile" id="btnClick" value="Pelan Strata" class="btn" onclick="RunProgram('ptspoc1','Pembantu_Tadbir_SPOC','${actionBean.idPermohonan}','123456',this.name, this.form)" /--%>
                              <s:button name="transferFile" id="btnClick" value="Pelan Strata" class="btn" onclick="RunProgram('ptspoc1','Pembantu_Tadbir_SPOC','${actionBean.idPermohonan}','${actionBean.stageId}',this.name, this.form)" />
            </p>
        </fieldset>
    </div>

</s:form>