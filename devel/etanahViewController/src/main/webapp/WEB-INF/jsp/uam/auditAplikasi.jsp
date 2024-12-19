<%-- 
    Document   : auditMedan
    Created on : Jul 4, 2011, 7:54:44 PM
    Author     : zahidaziz
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
<div id="all">
    <script type="text/javascript">
        
        function viewPopup() {
            var url = '${pageContext.request.contextPath}/uam/auditAplikasi?viewPopup';
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=850,height=600,scrollbars=yes");
        }
        function checkFields() {
   
            var id = document.getElementById("idAuditData.idAudit").value;
            var jad = document.getElementById("jadual").value;
            var namaMed = document.getElementById("idAuditData.namaMedan").value;
            var akt = document.getElementById("aktiviti").value;
            var tDr = document.getElementById("tarikhDari").value;
            var tHg = document.getElementById("tarikhHingga").value;
    
            if(id=='' && jad=='' && namaMed=='' && akt=='' && tDr=='' && tHg==''){
                alert("Sila masukkan data pada mana-mana ruangan sebelum carian dilakukan!");
                return false;
            }
    
            return true;
        }

        function validateForm(f) {

            if(f.elements['idPengguna'].value == '') {
                alert('Sila masukkan Id Pengguna.');
                f.elements['idPengguna'].focus();
                return false;
            
            }else if(f.elements['tarikhDari'].value == '') {
                alert('Sila masukkan Tarikh.');
                f.elements['tarikhDari'].focus();
                return false;
                
            }else if(f.elements['tarikhHingga'].value == '') {
                alert('Sila masukkan Tarikh.');
                f.elements['tarikhHingga'].focus();
                return false;
            
            }else return true;
        }
        
        
    </script>
    <s:form beanclass="etanah.view.uam.AuditAplikasiBean" name ="auditAplikasiForm" id ="auditAplikasi" onsubmit="return checkFields();">
        <s:messages/>
        <s:errors/>
        <div class ="subtitle displaytag">
            <fieldset class ="aras1">
                <legend>Carian</legend>
                <p><label><font color="red">*</font>ID Pengguna :</label>
                        <s:text name="idPengguna" value = "${actionBean.idPengguna}"></s:text>
                    </p>

                    <p><label><font color="red">*</font>Tarikh Dari :</label>
                        <s:text name="tarikhDari" class="datepicker"  formatPattern="dd/MM/yyyy" value = ""></s:text>
                    </p>
                    <p><label><font color="red">*</font>Tarikh Hingga :</label>
                        <s:text name="tarikhHingga" class = "datepicker" formatType="date" formatPattern="dd/MM/yyyy" value = ""></s:text>
                    </p>
                    </p>
                    <p><label>Bil Paparan :</label>
                    <s:select name="paparan" value="${actionBean.paparan}">
                        <s:option></s:option>
                        <s:option value="5">5</s:option>
                        <s:option value="10">10</s:option>
                        <s:option value="15">15</s:option>
                        <s:option value="20">20</s:option>
                        <s:option value="25">25</s:option>
                        <s:option value="30">30</s:option>
                        <s:option value="35">35</s:option>
                        <s:option value="40">40</s:option>
                        <s:option value="45">45</s:option>
                        <s:option value="50">50</s:option>
                        <s:option value="100">100</s:option>
                    </s:select>
                </p>
                <div align ="center">
                    <p><s:submit name="findAuditAplikasi" value="Cari" class="btn" onclick="return validateForm(this.form)"/>&nbsp;<s:button name = "reset" class="btn" value ="Isi Semula" onclick= "clearText('auditAplikasi')" /></p>
                </div>
            </fieldset>
        </div>

        <c:if test="${actionBean.flag}">  
            <div class="content" align="center">
                <fieldset class="aras1">
                    <legend>
                        Senarai Audit
                    </legend>
                    <div class="content" align="center">
                        <display:table  name="${actionBean.logApp}" id="line" class="tablecloth" pagesize="${actionBean.paparan}" requestURI="${pageContext.request.contextPath}/uam/auditAplikasi">
                            <display:column title="Bil" sortable = "true" style  = "verticle-align:baseline">${line_rowNum}</display:column>
                            <display:column  property="idPgunaAppLog" sortable="true" title="ID Audit" style = "verticle-align:baseline" ></display:column>
                            <display:column property="idPguna" sortable="true" title="Pengguna" style = "verticle-align:baseline" ></display:column>
                            <display:column   property="url" sortable="true" title="URL" style = "verticle-align:baseline"></display:column>
                            <display:column  property="tarikhMasuk" sortable="true" title="Tarikh" style = "verticle-align:baseline" format="{0,date,dd/MM/yyyy - hh:mm:ss a}"></display:column>
                        </display:table>
                    </div>
                </fieldset>
            </div>
        </c:if>

        <div class="content" align="center">
            <fieldset class="aras1">
                <legend>
                    Papar Laporan
                </legend>
                <div align ="center">
                    <p><s:button name="findAuditAplikasi" value="Laporan" class="btn" onclick="viewPopup()"/>&nbsp;</p>
                </div>
            </fieldset>
        </div>
    </s:form>

</div>
