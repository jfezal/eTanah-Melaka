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
            var url = '${pageContext.request.contextPath}/utility_kod/sloganSurat?viewSloganSurat&kod='+value+'&table_name='+value2;
            frm.action = url;
            frm.submit();
        }
         function goBack(frm) {
            var url = '${pageContext.request.contextPath}/utility_kod/kodRujukan?findKodRuj';
            frm.action = url;
            frm.submit();
        }
         function addDokumen(frm, value) {
            var url = '${pageContext.request.contextPath}/utility_kod/sloganSurat?viewAddForm&table_name='+value;
            frm.action = url;
            frm.submit();
        }
       
        
    </script>
    <s:useActionBean beanclass = "etanah.view.ListUtil" var="list"/>
    <s:form beanclass="etanah.view.utility.kod.SloganSuratActionBean" name ="sloganSurat" id ="sloganSurat">
        <s:messages/>
        <s:errors/>
        <div class ="subtitle displaytag">
            <fieldset class ="aras1">
                <legend>Slogan Surat</legend>     
            </fieldset>
        </div>
        <c:if test="${actionBean.flag}">   
            <div class="content" align="center">
                <fieldset class="aras1">
                    <legend>
                        Senarai Slogan
                    </legend>
                    <display:table  name="${actionBean.listSloganSurat}" id="line"  class="tablecloth" pagesize="10" requestURI="${pageContext.request.contextPath}/utility_kod/sloganSurat">
                        <display:column title="Bil" sortable = "true" style  = "verticle-align:baseline">${line_rowNum}</display:column>
                       
                         <display:column property ="sloganSurat" sortable="true" title="Slogan Surat" style = "verticle-align:baseline" ></display:column>
                   <display:column title="Kemaskini">
                            <div align ="center">
                                <img alt='Klik untuk kemaskini' border='0' src='${pageContext.request.contextPath}/images/view_icon.gif' class='rem'
                                     id='rem_${line_rowNum}' onclick="kemaskini(document.forms.sloganSurat,'${line.idSloganSurat}','${actionBean.nameTable}')" onmouseover="this.style.cursor='pointer';"></img>
                            </div>
                        </display:column>
                    </display:table>
                </fieldset>
            </div>                
        </c:if>
        <%--div class ="subtitle2">
            <fieldset class ="aras1">
                <p align="center">
                 <s:button name = "tambah" class="btn" value ="Tambah" onclick= "addDokumen(document.forms.sloganSurat,'${actionBean.nameTable}')" />
                </p>
         
            </fieldset>
        </div--%>
    </s:form>
</div>


