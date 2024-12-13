<%-- 
    Document   : listKodAgensi
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
            var url = '${pageContext.request.contextPath}/utility_kod/kodDokumenAgensi?viewKodDokumenAgensi&kod='+value+'&table_name='+value2;
            frm.action = url;
            frm.submit();
        }
         function goBack(frm) {
            var url = '${pageContext.request.contextPath}/utility_kod/kodRujukan?findKodRuj';
            frm.action = url;
            frm.submit();
        }
         function addDokumen(frm, value) {
            var url = '${pageContext.request.contextPath}/utility_kod/kodDokumenAgensi?viewAddForm&table_name='+value;
            frm.action = url;
            frm.submit();
        }
       
        
    </script>
    <s:useActionBean beanclass = "etanah.view.ListUtil" var="list"/>
    <s:form beanclass="etanah.view.utility.kod.KodDokumenAgensiActionBean" name ="kodDokumenAgensi" id ="kodDokumenAgensi">
        <s:messages/>
        <s:errors/>
        <div class ="subtitle displaytag">
            <fieldset class ="aras1">
                <legend>Kod Dokumen Agensi</legend>
                <p><label><font color="red">*</font>Kod Urusan Agensi:</label>
                         <s:select name="kodUrusanAgensi" id="kodUrusanAgensi" >
                                <s:option value="0">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodUrusanAgensi}" label="idKodUrusanAgensi" value="idKodUrusanAgensi" />
                    </s:select>
                </p>
                 <s:hidden value = "${actionBean.nameTable}" name = "nameTable"/>
                <p><label><font color="red">*</font>Kod Dokumen :</label>
                          <s:select name="kodDokumen" id="kodDokumen" >
                                <s:option value="0">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodDokumen}" label="nama" value="kod" />
                    </s:select>
                </p>
                <p align="center"><s:submit name="KodList2" value="Cari" class="btn"/>&nbsp;<s:button name = "reset" class="btn" value ="Isi Semula" onclick= "clearText('kodDokumenAgensi')" />&nbsp;<s:button name = "tambah" class="btn" value ="Tambah" onclick= "addDokumen(document.forms.kodDokumenAgensi,'${actionBean.nameTable}')" />&nbsp;<s:button name="kembali" value="Kembali" class="btn" onclick="goBack(document.forms.kodDokumenAgensi)" /></p>
         
            </fieldset>
        </div>
        <c:if test="${actionBean.flag}">   
            <div class="content" align="center">
                <fieldset class="aras1">
                    <legend>
                        Senarai Kod Dokumen Agensi
                    </legend>
                    <display:table  name="${actionBean.listKodDokumenAgensi}" id="line"  class="tablecloth" pagesize="10" requestURI="${pageContext.request.contextPath}/utility_kod/kodDokumenAgensi">
                        <display:column title="Bil" sortable = "true" style  = "verticle-align:baseline">${line_rowNum}</display:column>
                        <display:column property ="kodUrusanAgensi.idKodUrusanAgensi" sortable="true" title="Kod Urusan Agensi" style = "verticle-align:baseline" ></display:column>
                          <display:column property ="kodDokumen.nama" sortable="true" title="Kod Dokumen" style = "verticle-align:baseline" ></display:column>
                   <display:column title="Tindakan">
                            <div align ="center">
                                <img alt='Klik untuk pilih jadual' border='0' src='${pageContext.request.contextPath}/images/view_icon.gif' class='rem'
                                     id='rem_${line_rowNum}' onclick="kemaskini(document.forms.kodDokumenAgensi,'${line.idKodDokAgensi}','${actionBean.nameTable}')" onmouseover="this.style.cursor='pointer';"></img>
                            </div>
                        </display:column>
                    </display:table>
                </fieldset>
            </div>
        </c:if>
    </s:form>
</div>



