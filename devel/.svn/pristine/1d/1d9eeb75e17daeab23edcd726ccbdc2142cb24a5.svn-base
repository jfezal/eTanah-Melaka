<%-- 
    Document   : release_task_user
    Created on : Nov 8, 2013, 10:53:51 AM
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

<script type="text/javascript">
    function assignUser(taskId,user,row,idM){
        //        alert(row);
        var valueText = taskId+","+user+","+idM;
        //        alert(valueText);
        document.getElementById("assignUserRow"+row).value = valueText;
        
        if(user != null){
            if(user == document.getElementById("selectedUser"+row).value){
                alert("Sila pilih pengguna lain.");
                document.getElementById("newAssignUser"+row).value = "";
                return false;
            }
        }
        
        if(document.getElementById("checkBoxTaskId"+row).checked == false){
            alert("Sila tanda di kotak pilih dahulu.");
            document.getElementById("newAssignUser"+row).value = "";
            $('#checkBoxTaskId'+row).focus();
            return false;
        }
        
    }
    

</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.uam.UtilitiReleaseTaskActionBean" name="userForm" id="user_uam">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle" id ="aa">
        <fieldset class="aras1">
            <legend>
                Carian
            </legend>
            <p>
                <label> Kumpulan Pengguna :</label>
                <%--<s:text name="idPengguna"/>--%>
                <s:select name="groupBpel" id="groupBpel" class="normal_text">
                    <s:option value="">Sila Pilih</s:option>
                    <c:forEach items="${actionBean.dropDownKumpulanBpel}" var="i">
                        <s:option value="${i}">${i}</s:option>
                    </c:forEach>
                </s:select>

            </p>

            <p align="right">
                <s:submit name="findByBpelGroup" value="Cari" class="btn"/>
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
                <s:hidden name="rowCount" id="rowCount"/>
                <display:table name="${actionBean.listRelease}" class="tablecloth" id="row" style="width:100%">
                    <display:column title="Pilih">                    
                        <s:checkbox name="chkbox" value="${row.taskID}" id="checkBoxTaskId${row_rowNum-1}"/>                  
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
                <display:column title="Pengguna">
                    <s:select name="selectedUser" id="selectedUser${row_rowNum-1}" value="${row.selectedUser}" disabled="true">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.listPengguna}" label="nama" value="idPengguna" />
                    </s:select>
                </display:column>
                <display:column title="Senarai Pengguna">
                    <input type="hidden" id="assignUserRow${row_rowNum-1}" value="" name="assignUserRow${row_rowNum-1}">
                    <s:select name="newAssignUser" id="newAssignUser${row_rowNum-1}" onchange="assignUser('${row.taskID}',this.value,${row_rowNum-1},'${row.idPermohonan}')">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.listPengguna}" label="nama" value="idPengguna" />
                    </s:select>
                </display:column>


            </display:table>

            <br/>    
            <s:submit name="releaseTaskAndReassign" value="Tukar Pegawai" class="btn"/>
        </fieldset>
    </div>
    <s:hidden name="RETURNJSP"/>
</s:form>
