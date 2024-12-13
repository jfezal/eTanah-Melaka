<%-- 
    Document   : gisPabStrata
    Created on : Jun 13, 2013, 6:03:55 PM
    Author     : IDA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%--<script type="text/javascript">
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
</script>---%>
<%--Original <s:form name="form1" beanclass="etanah.view.strata.GisStrataActionBean">--%>
<s:form beanclass="etanah.view.strata.GisStrataActionBean">
    <s:messages/>
    <s:errors/>
    <div>
        <fieldset class="aras1">
            <legend>${actionBean.gisStage}</legend>
            <br/>
            <c:if test="${actionBean.isDokumenExist && actionBean.kaedah eq '1'}">
                <font size="2" color="Red">${actionBean.arahan}</font>
            </c:if>
            <c:if test="${actionBean.isDokumenExist && actionBean.kaedah eq '2'}">
                <font size="2" color="Red">Arahan: Fail dari JUPEM telah diterima. Sila klik butang Muat Turun</font>
            </c:if>
            <c:if test="${!actionBean.isDokumenExist}">
                <font size="2" color="Red">Fail dari JUPEM masih belum di terima. </font>
            </c:if>
            <%--  <c:if test="${actionBean.isDokumenExist}">
                       <p><font size="1" color="Red"> * </font>Sila klik butang 'Muat Turun' untuk muat turun Fail dari JUPEM.</p>  
               </c:if>--%>
            
            <c:if test="${actionBean.isDokumenExist && actionBean.kaedah eq '2'}">
                <br/>
            <br/>
                <s:button name="CheckJupem" id="btnClick" value="Muat Turun" class="btn" onclick="doSubmit(this.form,this.name,'page_div')" />
            </c:if>
            <%--<c:if test="${actionBean.isDokumenExist && actionBean.extractSuccess && actionBean.disableButton}">
                <s:button name="CheckJupem" id="btnClick" value="Muat Turun" class="btn" disabled="disabled" onclick="doSubmit(this.form,this.name,'page_div')" />
            </c:if>--%>
            <%-- <s:button name="extractZip" id="btnClick" value="Ekstrak File" class="btn" onclick="doSubmit(this.form,this.name,'page_div')" />--%>
        </fieldset>
    </div>

</s:form>