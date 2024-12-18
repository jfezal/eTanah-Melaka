<%-- 
    Document   : mlk_pelan_lokasi_GIS
    Created on : June 10, 2013, 6:06:05 PM
    Author     : murali
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">    
    function RunProgram(strUserID, strJawatan, strIDPermohonan, strIDStage,event, f) {
        //alert(strUserID);
        //alert(strJawatan);
        //alert(strIDPermohonan);
        //alert(strIDStage);
        
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
            alert("Maaf. PC anda tidak mempunyai kelengkapan GIS");
        }
    }
    
</script>
<s:form name="form1" beanclass="etanah.view.strata.GisStrataMlkActionBean">
    <s:messages/>
    <s:errors/>
    <div>
        <fieldset class="aras1">
            <legend>Paparan Pelan</legend>

            <c:if test="${actionBean.isDokumenUploaded}">
                <p><font size="1" color="Red"> * </font>Sila klik Pelan Strata untuk melihat pelan.</p>
                <br>
                <p>&nbsp;</p>
                <p>
                    <s:button name="transferFile" id="btnClick" value="Pelan Strata" class="btn" onclick="RunProgram('ptspoc1','Pembantu_Tadbir_SPOC','${actionBean.idPermohonan1}','${actionBean.stageId1}',this.name, this.form)" />
                </p>
            </c:if>
            <c:if test="${manual}"><p><font size="2" color="Red"> * Tab ini hanya berfungsi sekiranya menggunakan fail XML(Jadual Petak)</font></p></c:if>
            <c:if test="${!manual}">
                <c:if test="${!actionBean.isDokumenUploaded}">
                    <p><font size="2" color="Red"> * Fail xml (JPP) untuk permohonan sebelum belum di muat naik.</font></p>
                </c:if>
            </c:if>
        </fieldset>
    </div>

</s:form>