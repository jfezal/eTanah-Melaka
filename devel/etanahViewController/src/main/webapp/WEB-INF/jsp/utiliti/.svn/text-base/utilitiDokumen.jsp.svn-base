<%-- 
    Document   : utilitiDokumen
    Created on : Nov 1, 2011, 9:26:59 AM
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
    
        function goTo(frm, value1,value2,value3,value4) {
            var url = '${pageContext.request.contextPath}/utiliti/senarai_mohon_ruj_luar_dok?findMohonRujLuarDok&idMohon='+value1+'&idRujukan='+value2+'&jabatan='+value3+'&catatan='+value4;
            frm.action = url;
            frm.submit();
        }
       
        
    </script>

    <s:form beanclass="etanah.view.utility.CarianMohonRujLuarAction" name ="utilitiDokumen" id ="utilitiDokumen">
        <s:messages/>
        <s:errors/>
        <div class ="subtitle displaytag">
            <fieldset class ="aras1">
                <legend>Carian Permohonan</legend>
                <p><label><font color="red">*</font>ID Permohonan :</label>
                            <s:text id="idPermohonan" name="idPermohonan"   value="${actionBean.idPermohonan}"></s:text>
                </p>
                <p align="center"><s:submit name="findPermohonan" value="Cari" class="btn"/>&nbsp;<s:button name = "reset" class="btn" value ="Isi Semula" onclick= "clearText('utilitiDokumen')" /></p>
         
            </fieldset>
        </div>
        <c:if test="${actionBean.flag}">   
            <div class="content" align="center">
                <fieldset class="aras1">
                    <legend>
                        Senarai Jabatan Teknikal
                    </legend>
                    <display:table  name="${actionBean.mohonRujLuar}" id="line"  class="tablecloth" pagesize="" requestURI="${pageContext.request.contextPath}/utility/utiliti_dok">
                        <display:column title="Bil" sortable = "true" style  = "verticle-align:baseline">${line_rowNum}</display:column>
                        <display:column property ="agensi.nama" sortable="true" title="Nama Jabatan" style = "verticle-align:baseline" ></display:column>
                        
                   <display:column title="Senarai Dokumen">
                            <div align ="center">
                                <img alt='Klik untuk papar dokumen' border='0' src='${pageContext.request.contextPath}/images/view_icon.gif' class='rem'
                                     id='rem_${line_rowNum}' onclick="goTo(document.forms.utilitiDokumen,'${line.permohonan.idPermohonan}','${line.idRujukan}','${line.agensi.nama}','${line.catatan}')" onmouseover="this.style.cursor='pointer';"></img>
                            </div>
                        </display:column>
                    </display:table>
                </fieldset>
            </div>
        </c:if>
    </s:form>
</div>


