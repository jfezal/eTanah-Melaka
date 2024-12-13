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
        function validateCarian(f) {

            if(f.elements['accountNo'].value == '') {
                alert('Sila masukkan Id Hakmilik.');
                f.elements['accountNo'].focus();
                return false;
            
            }else if(f.elements['transId'].value == '') {
                alert('Sila masukkan transId.');
                f.elements['transId'].focus();
                return false;
                
            }else if(f.elements['paymentDateTime'].value == '') {
                alert('Sila masukkan Tarikh.');
                f.elements['paymentDateTime'].focus();
                return false;
            }else if(f.elements['amaun'].value == '') {
                alert('Sila masukkan Tarikh.');
                f.elements['amaun'].focus();
                return false;
            }else if(f.elements['idPengguna'].value == '') {
                alert('Sila Id Pengguna.');
                f.elements['idPengguna'].focus();
                return false;
                }else if(f.elements['resitNo'].value == '') {
                alert('Sila No Resit.');
                f.elements['resitNo'].focus();
                return false;
                }else if(f.elements['namaPenyerah'].value == '') {
                alert('Sila Id Nama Penyerah.');
                f.elements['namaPenyerah'].focus();
                return false;
            
            }else return true;
        }
        
         function janaPortalTrans(idDok,idmohon,idHM){
            var url = '${pageContext.request.contextPath}/utiliti/carian_persendirian_online?generateCarianPOrtalTrans&idDokumen='+idDok+'&idmohon='+idmohon+'&idHakmilik='+idHM;
            $.get(url,
            function(data){
                alert("data:"+data);
                $('#carian').html(data);
            },'html');
        }
        
    </script>
    <s:form beanclass="etanah.view.uam.CPOnlineUtilitiActionBean" id ="auditAplikasi" onsubmit="return checkFields();">
        <s:messages/>
        <s:errors/>
        <div class ="subtitle displaytag">
            <fieldset class ="aras1">
                <legend>Carian</legend>
                

                    <p><label><font color="red">*</font>Tarikh Dari :</label>
                        <s:text name="tarikhDari" class="datepicker"  formatPattern="dd/MM/yyyy" value = ""></s:text>
                    </p>
                    <p><label><font color="red">*</font>Tarikh Hingga :</label>
                        <s:text name="tarikhHingga" class = "datepicker" formatType="date" formatPattern="dd/MM/yyyy" value = ""></s:text>
                    </p>
                    </p>
                    
                <div align ="center">
                    <p><s:submit name="findAuditAplikasi" value="Cari" class="btn" onclick="return validateForm(this.form)"/>&nbsp;<s:button name = "reset" class="btn" value ="Isi Semula" onclick= "clearText('auditAplikasi')" /></p>
                </div>
            </fieldset>
        </div>

      
            <div class="content" id="carian" align="center">
                <fieldset class="aras1">
                    <legend>
                        Senarai Carian
                    </legend>
                    <div class="content" align="center">
                        <display:table  name="${actionBean.listCPTransaksi}" id="line" class="tablecloth"  requestURI="${pageContext.request.contextPath}/uam/auditAplikasi">
                            <display:column title="Bil" sortable = "true" style  = "verticle-align:baseline">${line_rowNum}</display:column>
                            <display:column  property="idmohon" sortable="true" title="ID Carian" style = "verticle-align:baseline" ></display:column>
                            <display:column property="idHakmilik" sortable="true" title="ID Hakmilik" style = "verticle-align:baseline" ></display:column>
                            <display:column   property="noResit" sortable="true" title="No Resit" style = "verticle-align:baseline"></display:column>
                            <display:column  property="trhResit" sortable="true" title="Tarikh Resit" style = "verticle-align:baseline" format="{0,date,dd/MM/yyyy - hh:mm:ss a}"></display:column>
                            <display:column   sortable="true" title="Jana Semula" style = "verticle-align:baseline">
                                <p align="center">
                                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                 onclick="janaPortalTrans('${line.dokumen.idDokumen}','${line.idmohon}','${line.idHakmilik}')" onmouseover="this.style.cursor='pointer';">
                                </p>                                
                            </display:column>                        
                        </display:table>
                    </div>
                </fieldset>
            </div>
       
      <div class ="subtitle displaytag">
            <fieldset class="aras1">
                <legend>
                    Carian Baru
                </legend>
                    <p><label><font color="red">*</font>ID Hakmilik :</label>
                        <s:text name="accountNo"></s:text>
                    </p>
                    <p><label><font color="red">*</font>Trans ID :</label>
                        <s:text name="transId"></s:text>
                    </p>
                    <p><label><font color="red">*</font>Tarikh Bayaran :</label>
                        <s:text name="paymentDateTime"></s:text>
                    </p>
                    <p><label><font color="red">*</font>Amaun :</label>
                        <s:text name="amaun" ></s:text>
                    </p>
                    <p><label><font color="red">*</font>ID Pengguna :</label>
                        <s:text name="idPengguna" ></s:text>
                    </p>
                    <p><label><font color="red">*</font>No Resit :</label>
                        <s:text name="resitNo" ></s:text>
                    </p>
                    <p><label><font color="red">*</font>Nama Penyerah:</label>
                        <s:text name="namaPenyerah" ></s:text>
                    </p>
                    </p>
                     <div align ="center">
                    <p><s:submit name="generateCarian" value="Jana" class="btn" onclick="return validateCarian(this.form)"/>&nbsp;
                         <s:button name = "reset" class="btn" value ="Isi Semula" onclick= "clearText('auditAplikasi')" /></p>
                </div> </fieldset>
      </div>
    </s:form>

</div>
