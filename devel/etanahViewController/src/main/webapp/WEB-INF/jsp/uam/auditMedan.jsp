
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
<div class= "all">
    <script type="text/javascript">
     
        //     $(document).ready(function(){
        //        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        //    });

        
        function validateNumber(elmnt,content) {
            //if it is character, then remove it..
            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }

        function removeNonNumeric( strString )
        {
            var strValidCharacters = "1234567890";
            var strReturn = "";
            var strBuffer = "";
            var intIndex = 0;
            // Loop through the string
            for( intIndex = 0; intIndex < strString.length; intIndex++ )
            {
                strBuffer = strString.substr( intIndex, 1 );
                // Is this a number
                if( strValidCharacters.indexOf( strBuffer ) > -1 )
                {
                    strReturn += strBuffer;
                }
            }
            return strReturn;
        
        }
    
        function viewDetails(frm, value1,value2,value3, report) {
//            var url = '${pageContext.request.contextPath}/uam/view_Audit_Data_Details?viewAuditData&idAudit='+value1+'&namaMedan='+value2;
//             var url = '${pageContext.request.contextPath}/uam/auditMedan?viewReport&idAudit='+value1;

var url = '${pageContext.request.contextPath}/reportGeneratorServlet?reportName='+report+'&report_p_id_audit='+value1+'&report_p_nama_table='+value2+'&report_p_nama_medan='+value3;

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

            if(f.elements['tarikhDari'].value == '') {
                alert('Sila masukkan Tarikh.');
                f.elements['tarikhDari'].focus();
                return false;
            
            } else if(f.elements['tarikhHingga'].value == '') {
                alert('Sila masukkan Tarikh.');
                f.elements['tarikhHingga'].focus();
                return false;
            
            } else if(f.elements['jadual'].value == '') {
                alert('Sila masukkan Jadual.');
                f.elements['jadual'].focus();
                return false;
            }
            else return true;
        }   
        
        
    </script>

    <s:form beanclass="etanah.view.uam.AuditMedanBean" name ="auditMedanForm" id ="auditMedanForms" onsubmit="return checkFields();">
        <s:messages/>
        <s:errors/>
        <div class ="subtitle displaytag">
            <fieldset class ="aras1">
                <legend>Carian</legend>
                <p><label><font color="red">*</font>Tarikh Dari :</label>
                        <s:text id="tarikhDari" name="tarikhDari" class="datepicker"  formatPattern="dd/MM/yyyy" value="${actionBean.tarikhDari}"></s:text>
                </p>
                <p><label><font color="red">*</font>Tarikh Hingga :</label>
                        <s:text id="tarikhHingga" name="tarikhHingga" class = "datepicker" formatType="date" formatPattern="dd/MM/yyyy" value = "${actionBean.tarikhHingga}"></s:text>
                </p>
                <p><label><font color="red">*</font>Nama Jadual :</label>
                    <s:text id="jadual" name="jadual" value = "${actionBean.jadual}" onblur = "this.value=this.value.toUpperCase();"></s:text> 
                </p>
                <p><label>Nama Medan :</label>
                    <s:text id="idAuditData.namaMedan" name="idAuditData.namaMedan" value = "${actionBean.idAuditData.namaMedan}" onblur = "this.value=this.value.toUpperCase();"></s:text> 
                </p>
                <p><label>Aktviti :</label>
                    <s:select id="aktiviti" name="aktiviti" value = "${actionBean.aktiviti}" onblur = "this.value=this.value.toUpperCase();">
                        <s:option></s:option>
                        <s:option>DATA BARU</s:option>
                        <s:option>KEMASKINI</s:option>
                        <s:option>HAPUS</s:option>
                    </s:select>
                </p>
                <p><label>Id Pengguna :</label>
                    <s:text name="idPengguna" value = "${actionBean.idPengguna}" class="normal_text"/>
                <p>
                    <label>Jumlah Paparan :</label>
                    <s:select name = "paparan" value = "${actionBean.paparan}">
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
                <p>
                    <label>&nbsp;</label>
                <p><s:submit name="findAudit" value="Cari" class="btn" onclick="return validateForm(this.form)"/>&nbsp;<s:button name = "reset" class="btn" value ="Isi Semula" onclick= "clearText('auditMedanForms')" /></p>
                </p>
            </fieldset>
        </div>
        <c:if test="${actionBean.flag}">   
            <div class="content" align="center">
                <fieldset class="aras1">
                    <legend>
                        Senarai Audit
                    </legend>
                    <display:table  name="${actionBean.auditMedanList}" id="line"  class="tablecloth" pagesize="${actionBean.paparan}" requestURI="${pageContext.request.contextPath}/uam/auditMedan">
                        <display:column title="Bil" sortable = "true" style  = "verticle-align:baseline">${line_rowNum}</display:column>
                        <display:column property ="auditDataId.idAudit" sortable="true" title="ID Audit" style = "verticle-align:baseline" ></display:column>
                        <display:column property ="namaTable" sortable="true" title="Nama Jadual" style = "verticle-align:baseline"></display:column>
                        <display:column property ="auditDataId.namaMedan" sortable="true" title="Nama Medan" style = "verticle-align:baseline"></display:column>
                        <display:column property ="masa" sortable="true" title="Tarikh/Masa" style = "verticle-align:baseline" format="{0,date,dd/MM/yyyy - hh:mm:ss a}" ></display:column>
                        <display:column property ="aktiviti" sortable="true" title="Aktiviti" style = "verticle-align:baseline"></display:column>
                        <display:column title="Maklumat Terperinci">
                            <div align ="center">
                                <img alt='Klik Untuk Papar Butiran' border='0' src='${pageContext.request.contextPath}/images/view_icon.gif' class='rem'
                                     id='rem_${line_rowNum}' onclick="viewDetails(document.forms.auditMedanForm, '${line.auditDataId.idAudit}','${line.namaTable}','${line.auditDataId.namaMedan}','${actionBean.reportName}')" onmouseover="this.style.cursor='pointer';"></img>
                            </div>
                        </display:column>
                    </display:table>
                </fieldset>
            </div>
        </c:if>
    </s:form>
</div>

