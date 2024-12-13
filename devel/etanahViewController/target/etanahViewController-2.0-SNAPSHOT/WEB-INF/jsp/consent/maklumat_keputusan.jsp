<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript">

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

<s:form beanclass="etanah.view.stripes.consent.MaklumatKeputusanActionBean">
    <s:errors/>
    <s:messages/>

    <div class="subtitle">

        <c:if test="${edit}">
            <fieldset class="aras1">
                <legend>
                    Maklumat Keputusan
                </legend>
                 <p>
                    <label>Syor Pentadbir Tanah :</label>
                    <s:radio name="syorPtd" value="L"/>&nbsp;Lulus
                    <s:radio name="syorPtd" value="T"/>&nbsp;Tolak
                </p>
                <br/>
                <p>
                    <label>Syor Jawatankuasa Tanah Ladang :</label>
                    <s:radio name="syorTanahLadang" value="L"/>&nbsp;Lulus
                    <s:radio name="syorTanahLadang" value="T"/>&nbsp;Tolak
                </p>
                <br/>
                <p>
                    <label>Syor Pengarah Tanah dan Galian :</label>       
                    <s:radio name="syorPtg" value="L"/>&nbsp;Lulus
                    <s:radio name="syorPtg" value="T"/>&nbsp;Tolak
                </p>
                <br/>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
                <br/>
            </fieldset>
        </c:if>
    </div>
</s:form>
