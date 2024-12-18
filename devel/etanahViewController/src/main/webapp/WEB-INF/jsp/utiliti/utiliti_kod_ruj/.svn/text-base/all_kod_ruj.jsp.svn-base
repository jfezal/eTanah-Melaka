<%-- 
    Document   : all_kod_ruj
    Created on : Dec 29, 2011, 11:40:12 PM
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
    function goTo(frm, value,value1) {
            
            var url = '${pageContext.request.contextPath}'+value+'?KodList&table_name='+value1;
            frm.action = url;
            frm.submit();
            }    
    </script>

    <s:form beanclass="etanah.view.utility.kod.CarianKodRujActionBean" name ="all_kod_ruj" id ="all_kod_ruj">
        <s:messages/>
        <s:errors/>
        <div class ="subtitle displaytag" >
            <fieldset class ="aras1">
                <legend>Penyelenggaraan Kod-kod Rujukan</legend>
          
               <%-- <p><label><font color="red">*</font>Nama Jadual :</label>
                            <s:text id="table_name" name="table_name"   value="${actionBean.table_name}"></s:text>
                </p> --%>
                 <p><label><font color="red">*</font>Nama Jadual :</label>
                            <s:text id="table_act_name" name="table_act_name"   value="${actionBean.table_act_name}"></s:text>
                </p>
                <p align="center"><s:submit name="findKodRuj" value="Cari" class="btn"/>&nbsp;<s:button name = "reset" class="btn" value ="Isi Semula" onclick= "clearText('all_kod_ruj')" /></p>
         
            </fieldset>
        </div>
        <c:if test="${actionBean.flag}">   
            <div class="content" align="center">
                <fieldset class="aras1">
                    <legend>
                        Senarai Jadual Kod
                    </legend>
                    <display:table  name="${actionBean.kodRuj}" id="line"  class="tablecloth" pagesize="10" requestURI="${pageContext.request.contextPath}/utility_kod/kodRujukan">
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <%--<display:column property ="nama_jadual" sortable="true" titleKey="code.nama_jadual_label" style = "verticle-align:baseline" ></display:column> --%>
                        <display:column property ="nama" title="Nama Jadual" style = "verticle-align:baseline" />
                        
                        <display:column titleKey="code.tindakan_label">
                            <div align ="center">
                                <img alt='Klik untuk pilih jadual' border='0' src='${pageContext.request.contextPath}/images/view_icon.gif' class='rem'
                                     id='rem_${line_rowNum}' onclick="goTo(document.forms.all_kod_ruj,'${line.uri}','${line.nama_jadual}')" onmouseover="this.style.cursor='pointer';"></img>
                            </div>
                        </display:column>
                    </display:table>
                </fieldset>
            </div>
        </c:if>
    </s:form>
</div>



