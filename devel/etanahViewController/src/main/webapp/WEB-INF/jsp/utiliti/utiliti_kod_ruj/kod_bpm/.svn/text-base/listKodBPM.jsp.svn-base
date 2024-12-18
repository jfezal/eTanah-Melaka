<%-- 
    Document   : listKodBPM
    Created on : Jan 12, 2012, 3:13:04 PM
    Author     : Aziz
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
     maximizeWindow();
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
    
        function kemaskini(frm, value,value2) {
            var url = '${pageContext.request.contextPath}/utility_kod/kodBPM?viewKodBPM&kod='+value+'&table_kod='+value2;
            frm.action = url;
            frm.submit();
        }
         function goBack(frm) {
            var url = '${pageContext.request.contextPath}/utility_kod/kodRujukan?findKodRuj';
            frm.action = url;
            frm.submit();
        }
         function addDokumen(frm, value) {
            var url = '${pageContext.request.contextPath}/utility_kod/kodBPM?viewAddForm&table_name='+value;
            frm.action = url;
            frm.submit();
        }
       
        
    </script>
    <s:useActionBean beanclass = "etanah.view.ListUtil" var="list"/>
    <s:form beanclass="etanah.view.utility.kod.KodBPMActionBean" name ="kodBPM" id ="kodBPM">
        <s:messages/>
        <s:errors/>
        <div class ="subtitle displaytag">
            <fieldset class ="aras1">
                <legend>Kod BPM</legend>
                <p><label><font color="red">*</font>Daerah:</label>
                         <s:select name="daerah" id="daerah" >
                                <s:option value="0">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodDaerah}" label="nama" value="kod" />
                    </s:select>
                </p>
                 <s:hidden value = "${actionBean.nameTable}" name = "nameTable"/>
                 <p><label><font color="red">*</font>Kod BPM :</label>
                            <s:text id="kodbpm" name="kodbpm"   value="${actionBean.kodbpm}"></s:text>
                </p>
                <p><label><font color="red">*</font>Nama :</label>
                            <s:text id="nama" name="nama"   value="${actionBean.nama}"></s:text>
                </p>
                <p align="center"><s:submit name="KodList2" value="Cari" class="btn"/>&nbsp;<s:button name = "reset" class="btn" value ="Isi Semula" onclick= "clearText('kodBPM')" />&nbsp;<s:button name = "tambah" class="btn" value ="Tambah" onclick= "addDokumen(document.forms.kodBPM,'${actionBean.nameTable}')" />&nbsp;<s:button name="kembali" value="Kembali" class="btn" onclick="goBack(document.forms.kodBPM)" /></p>
         
            </fieldset>
        </div>
        <c:if test="${actionBean.flag}">   
            <div class="content" align="center">
                <fieldset class="aras1">
                    <legend>
                        Senarai Kod BPM
                    </legend>
                    <display:table  name="${actionBean.listKodBPM}" id="line"  class="tablecloth" pagesize="10" requestURI="${pageContext.request.contextPath}/utility_kod/kodBPM">
                        <display:column title="Bil" sortable = "true" style  = "verticle-align:baseline">${line_rowNum}</display:column>
                        <display:column property ="bandarPekanMukim" sortable="true" title="Kod BPM" style = "verticle-align:baseline" ></display:column>
                         <display:column property ="daerah.nama" sortable="true" title="Daerah" style = "verticle-align:baseline" >${line.daerah.kod}-${line.daerah.nama}</display:column>
                          <display:column property ="nama" sortable="true" title="Nama" style = "verticle-align:baseline" ></display:column>
                        
                   <display:column title="Tindakan">
                            <div align ="center">
                                <img alt='Klik untuk pilih jadual' border='0' src='${pageContext.request.contextPath}/images/view_icon.gif' class='rem'
                                     id='rem_${line_rowNum}' onclick="kemaskini(document.forms.kodBPM,'${line.kod}','${actionBean.nameTable}')" onmouseover="this.style.cursor='pointer';"></img>
                            </div>
                        </display:column>
                    </display:table>
                </fieldset>
            </div>
        </c:if>
    </s:form>
</div>



