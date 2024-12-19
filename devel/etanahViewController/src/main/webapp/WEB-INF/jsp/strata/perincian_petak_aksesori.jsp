<%-- 
    Document   : perincian_petak_aksesori
    Created on : Sep 23, 2013, 5:23:41 PM
    Author     : ida
--%>


<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<!DOCTYPE html>
<script type="text/javascript">  
    function validateNumber2(elmnt,content) {
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
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<%--<s:form beanclass="etanah.view.strata.StrataMaklumatHakmilikPermohonanActionBean" id="kemasukanPerincianHakmilik">--%>
<s:form beanclass="etanah.view.strata.StrataMaklumatHakmilikPermohonanActionBean">    
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Perincian Hakmilik</legend>            
            <p><label>ID Hakmilik :</label>
                ${actionBean.hakmilik.idHakmilik}
                &nbsp;
            </p>            
              <c:if test="${actionBean.kodNegeri eq '05'}">
            
            <p><label>No Bangunan :</label>
                <s:text name="noBangunan" id="noBangunan" readonly="true" size="32" />                         
            </p>
            <p><label>No Tingkat :</label>                
                  <s:text name="noTingkat" id="noTingkat" readonly="true" size="32"/>                                
            </p>
            <p><label>No Petak :</label>              
                  <s:text name="noPetak" id="noPetak" readonly="true" size="32"/>                                
            </p>          
            <p><label>Petak Aksesori :</label>
                <s:text name="petakAksesori2" readonly="true" size="32"/>
            </p>
             <p><label>No Helaian :</label>
                <s:text name="hakmilik.noPu" readonly="true" size="32"/>
            </p>
            <p><label>Unit Syer bagi Petak :</label>
                <s:text name="hakmilik.unitSyer" readonly="true" size="32"/>
            </p>
            <p><label>Keluasan :</label>
                <s:text name="hakmilik.luas" readonly="true" size="32"/>&nbsp; ${actionBean.hakmilik.kodUnitLuas.nama}
            </p>
            
            </c:if> 
            <c:if test="${actionBean.kodNegeri ne '05'}">
            <p><label>Petak Aksesori :</label>${actionBean.petakAksesori2}
                <%--<s:text name="petakAksesori2" readonly="true"/>--%>
            </p>
            </c:if>
                                
</s:form>
