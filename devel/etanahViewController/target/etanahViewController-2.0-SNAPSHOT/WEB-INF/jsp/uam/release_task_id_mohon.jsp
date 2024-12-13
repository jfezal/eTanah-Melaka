<%-- 
    Document   : release_task
    Created on : Jul 16, 2013, 12:23:35 PM
    Author     : faidzal
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/pub/styles/jquery.tooltip.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.tools.min.js?select=full&debug=true"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/passwordChecking/libs/jquery.js"></script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.uam.UtilitiReleaseTaskIdMohonActionBean" name="userForm" id="user_uam">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>Carian menggunakan id pengguna
    <div class="subtitle" id ="aa">
        <fieldset class="aras1">
            <legend>
                Carian
            </legend>
            <p>
                <label> Id Pengguna :</label>
                <s:text name="idPengguna" id = "idPengguna" />
            </p>

            <p ><label>&nbsp;</label>
                <s:submit name="byIdPengguna" value="Cari" class="btn" onclick="doSearch(this.name, this.form);"/>
                <%--<s:button name=" " value="Isi Semula" class="btn" onclick="clearText();" />--%>
            </p>
        </fieldset>
    </div>
    <br/>
    <br/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Permohonan
            </legend>
            <p align="center">
                <label></label>
                <display:table name="${actionBean.listRelease}" class="tablecloth" id="row" style="width:100%">
                    <display:column title="Pilih">                    
                        <s:checkbox name="chkbox" value="${row.taskID}"/>                  
                        <s:hidden name="val" id="val_${row_rowNum-1}" value="${row.taskID}"/>
                    </display:column>
                    <display:column title="No">
                        ${row_rowNum}
                    </display:column>
                    <display:column title="Id Permohonan">
                        <div id="t" title="${row.idPermohonan}">${row.idPermohonan}</div>
                    </display:column>
                    <display:column title="Urusan" property="urusan" />
                    <display:column title="Tindakan" property="stage" />
                    <display:column title="Tarikh" property="tarikhTerima" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                    
                   
                </display:table>
            
            <br/>    
            <s:submit name="releaseTask" value="Release" class="btn"/>
        </fieldset>
    </div>
            <s:hidden name="RETURNJSP"/>
</s:form>