<%-- 
    Document   : perincian_hakmilik_strata
    Created on : Dec 8, 2011, 12:33:24 PM
    Author     : zadhirul.farihim
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
<s:form beanclass="etanah.view.strata.StrataMaklumatHakmilikPermohonanActionBean" id="kemasukanPerincianHakmilik">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Perincian Hakmilik</legend>
            <p><label>ID Hakmilik :</label>
                ${actionBean.hakmilik.idHakmilik}
                &nbsp;
            </p>

            <legend>Maklumat Strata</legend>

            <p><label>No Bangunan :</label>
                <s:text name="hakmilik.noBangunan" readonly="true"/>
            </p>
            <p><label>No Tingkat :</label>
                <s:text name="hakmilik.noTingkat" readonly="true"/>
            </p>
            <p><label>No Petak :</label>
                <s:text name="hakmilik.noPetak" readonly="true"/>
            </p>            
            <center><div id="checkLotMessages" style="font-size: 12px;display: none;margin-top: 10px;margin-bottom: 10px;width:50%;"></div>
                <div id="checkPelanMessages" style="font-size: 12px;display: none;margin-top: 10px;margin-bottom: 10px;width:50%;"></div></center>
            <p><label>Petak Aksesori :</label>
                <s:text name="petakAksesori" readonly="true"/>
            </p>
            <p><label>Unit Syer bagi Petak :</label>
                <s:text name="hakmilik.unitSyer" readonly="true"/>
            </p> 
            <p>
                <label>No PA(B) :</label>
                <s:text name="noPelan1" id="noPelan1" onkeyup="validateNumber2(this,this.value);"/>
            </p>

            <br>
        </fieldset>
    </div>
    <s:hidden name="idHakmilik1" value="${actionBean.hakmilik.idHakmilik}"/>

    <p><label>&nbsp;</label><s:button name="simpanHakmilikStrata" id="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/></p>

</s:form>
