<%--
    Document   : bayaran_daftar_hakmilik
    Created on : Jan 31, 2011, 2:40:22 PM
    Author     : Administrator
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>

<script type="text/javascript">
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
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
<s:form beanclass="etanah.view.stripes.pembangunan.BayaranDaftarHakmilikActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Bayaran</legend>
            <div class="content" align="center">
                <table border="0" width="33%">            
                    <tr>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Hakmilik Sementara : RM <fmt:formatNumber pattern="#,##0.00" value="${actionBean.hmSementara}"/>&nbsp; X <s:text name="quantityHs" size="5" onkeyup="validateNumber(this,this.value);"/> Lot</font></td>
                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;"> = RM <fmt:formatNumber pattern="#,##0.00" value="${actionBean.amaunHs}"/>&nbsp;</font></td>
                    </tr>
                    <tr>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Hakmilik Tetap : RM <fmt:formatNumber pattern="#,##0.00" value="${actionBean.hmTetap}"/>&nbsp; X <s:text name="quantityHt" size="5" onkeyup="validateNumber(this,this.value);"/> Lot</font></td>
                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;"> = RM <fmt:formatNumber pattern="#,##0.00" value="${actionBean.amaunHt}"/>&nbsp;</font></td>
                    </tr>
                    <tr>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Pelan : RM <fmt:formatNumber pattern="#,##0.00" value="${actionBean.pelan}"/>&nbsp; X <s:text name="quantityPln" size="5" onkeyup="validateNumber(this,this.value);"/> Lot</font></td>
                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;"> = RM <fmt:formatNumber pattern="#,##0.00" value="${actionBean.amaunPln}"/>&nbsp;</font></td>
                    </tr>
                    <tr>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Jumlah : </font></td>
                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;"> = RM <fmt:formatNumber pattern="#,##0.00" value="${actionBean.total}"/>&nbsp;</font></td>
                    </tr>                    
                </table>
                <br/>

                <p align="center">
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </div>
        </fieldset>
    </div>
</s:form>
