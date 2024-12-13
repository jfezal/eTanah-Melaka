<%-- 
    Document   : maklumat_undang
    Created on : Apr 11, 2011, 4:52:46 PM
    Author     : muhammad.amin
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

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

    function getDatokLembaga(kodSuku){
        if(kodSuku != ""){
            var url = '${pageContext.request.contextPath}/consent/maklumat_undang?getUndang&kodSuku='+kodSuku;
            $.get(url,
            function(data){
                $('#display').html(data);
            });
        }
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.consent.MaklumatUndangActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle" id="display">
        <s:hidden name="senaraiRujukan.kod"/>

        <fieldset class="aras1">
            <legend>Maklumat Undang Luak </legend>
            <c:if test="${edit}">
                <p id="suku">
                    <label for="Suku"><font color="red">*</font>Undang Luak :</label>
                    <s:select name="senaraiRujukan.senarai.kod" style="width:250px" onchange="getDatokLembaga(this.value);">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.kodSenaraiList}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p>
                    <label><font color="red">*</font>Nama Gelaran :</label>
                    <s:text name="senaraiRujukan.perihal"  size="80" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label><font color="red">*</font>Nama Undang Luak :</label>
                    <s:text name="senaraiRujukan.nama"  size="80" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </c:if>
            <c:if test="${!edit}">
                <p>
                    <label>Undang Luak :</label>
                    <c:if test="${actionBean.senaraiRujukan.senarai ne null}"><font style="text-transform:uppercase;">  ${actionBean.senaraiRujukan.senarai.nama}&nbsp; </font></c:if>
                    <c:if test="${actionBean.senaraiRujukan.senarai eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Nama Gelaran :</label>
                    <c:if test="${actionBean.senaraiRujukan.perihal ne null}"><font style="text-transform:uppercase;">  ${actionBean.senaraiRujukan.perihal}</font> </c:if>
                    <c:if test="${actionBean.senaraiRujukan.perihal eq null}"> Tiada Data </c:if>
                </p>

                <p>
                    <label>Nama Undang Luak :</label>
                    <c:if test="${actionBean.senaraiRujukan.nama ne null}"><font style="text-transform:uppercase;">  ${actionBean.senaraiRujukan.nama}</font> </c:if>
                    <c:if test="${actionBean.senaraiRujukan.nama eq null}"> Tiada Data </c:if>
                </p>
                <br/>
            </c:if>
        </fieldset>
    </div>
</s:form>