<%-- 
    Document   : tempoh_buka_halangan
    Created on : Nov 21, 2011, 3:50:57 PM
    Author     : ctzainal
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
    
    function test(f) {
        $(f).clearForm();
    }
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

    function validateForm(){

        
        if($("#tempoh").val()=="0" || $("#tempoh").val()=="" )
        {
            alert("Sila masukkan tempoh hari buka halangan");
            return false;
        }
        return true;
    }
</script>

<s:form beanclass="etanah.view.penguatkuasaan.MaklumatNotisActionBean" name="form1">
    <s:messages />
    <s:errors />

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Tempoh Buka Halangan
            </legend>
            <div class="content">
                <c:if test="${edit}">
                    <p>

                        <label><em>*</em>Tempoh Hari Buka Halangan:</label>
                        <s:text name="tempohHari" id="tempoh" onkeyup="validateNumber(this,this.value);" maxlength="2"/> hari&nbsp;

                    </p>
                </div>
                <p align="right">

                    <s:button name="reset" value="Isi Semula" class="btn" onclick="return test(this.form);"/>
                    <s:button class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Simpan"/>
                </p>
            </c:if>
            <c:if test="${view}">
                <p>
                    <label>Tarikh Notis :</label>
                    <fmt:formatDate value="${actionBean.notisPenguatkuasaan.tarikhNotis}" pattern="dd/MM/yyyy" />&nbsp;

                </p>
                <p>
                    <label>Tempoh Buka Halangan:</label>
                    ${actionBean.notisPenguatkuasaan.tempohHari} hari&nbsp;
                </p>
            </fieldset>
        </div>
    </c:if>
</s:form>
