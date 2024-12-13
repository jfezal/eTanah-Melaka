<%-- 
    Document   : listSiriNoPt
    Created on : Aug 1, 2013, 11:57:39 AM
    Author     : NageswaraRao
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
        
    </script>
    <s:useActionBean beanclass = "etanah.view.ListUtil" var="list"/>
    <s:form beanclass="etanah.view.stripes.pembangunan.utiliti.SiriNoPtActionBean" name ="kodBPM" id ="kodBPM">
        <s:messages/>
        <s:errors/>
        <div class ="subtitle displaytag">
            <fieldset class ="aras1">
                <legend>Carian</legend>
                <p><label>Daerah:</label>
                         ${actionBean.daerah}
                </p>
                <p><label><font color="red">*</font>Mukim:</label>
                         <s:select name="mukim" id="mukim" >
                                <s:option value="0">Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraiMukim}" label="nama" value="kod" />
                    </s:select>
                </p>
                <p align="center"><s:submit name="search" value="Cari" class="btn"/>&nbsp;<s:button name = "reset" class="btn" value ="Isi Semula" onclick= "clearText('kodBPM')" />&nbsp;   </p>
         
            </fieldset>
        </div>
         
            <div class="content">
                <fieldset class="aras1">
                    <legend>
                        Senarai Siri NoPt
                    </legend>
                    <center>
                    <%--<display:table  name="${actionBean.listSiriNoPt}" id="line"  class="tablecloth" pagesize="10" requestURI="${pageContext.request.contextPath}/utility_kod/siriNoPt">
                        <display:column title="Bil" sortable = "true" style  = "verticle-align:baseline">${line_rowNum}</display:column>
                        <display:column  sortable="true" title="No.PT Semasa" style ="align:center" >${line.noPt}</display:column>
                    </display:table> --%>
                    <display:table  name="${actionBean.listhakmilik}" id="line"  class="tablecloth" pagesize="10" requestURI="${pageContext.request.contextPath}/utility_kod/siriNoPt">
                        <display:column title="Bil" sortable = "true" style  = "verticle-align:baseline">${line_rowNum}</display:column>
                         <c:if test="${line.noFail != null}">
                        <display:column title="ID Permohonan" style ="align:center" >${line.noFail}</display:column>
                         </c:if>
                          <c:if test="${line.noFail == null}">
                        <display:column title="ID Permohonan" style ="align:center" ><center> - </cetnter></display:column>
                         </c:if>
                        <display:column sortable="true" title="No.PT Semasa" style ="align:center" >${line.noLot}</display:column>
                        <display:column title="Keluasan" style ="align:center" >${line.luas} ${line.kodUnitLuas.nama}</display:column>
                        <display:column title="No. Hakmilik Sementara" style ="align:center" >${line.idHakmilik}</display:column>
                        <display:column title="No. Syit Piawai" style ="align:center" >${line.noLitho}</display:column>
                        <display:column title="Kegunaan" style ="align:center" >${line.kategoriTanah.nama}</display:column>
                        <display:column title="Status Hakmilik" style ="align:center" ><center>${line.kodStatusHakmilik.nama}</center></display:column>
                    </display:table>
                    </center>    
                </fieldset>
            </div>
        
    </s:form>
</div>

