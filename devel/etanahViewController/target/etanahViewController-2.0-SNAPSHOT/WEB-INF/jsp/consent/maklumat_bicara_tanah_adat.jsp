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

<s:form beanclass="etanah.view.stripes.consent.MaklumatBicaraTanahAdatActionBean">
    <s:errors/>
    <s:messages/>
    <s:hidden name="permohonanPerbicaraan.idPerbicaraan"/>

    <div class="subtitle">

        <c:if test="${fn:length(actionBean.listPerbicaraanTangguh) > 0}">
            <fieldset class="aras1">
                <legend>
                    Maklumat Bicara Terdahulu
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.listPerbicaraanTangguh}" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="${pageContext.request.contextPath}/consent/maklumat_bicara_tanah_adat">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Tarikh">
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhBicara}"/>
                        </display:column>
                        <display:column title="Masa">
                            <fmt:formatDate pattern="hh:mm aaa" value="${line.tarikhBicara}"/>
                        </display:column>
                        <display:column property="lokasiBicara" title="Tempat" />
                        <display:column title="Status">
                            TANGGUH
                        </display:column>
                    </display:table>
                </div>
            </fieldset>
            <br/>
        </c:if>

        <fieldset class="aras1">
            <legend>             
                Maklumat Bicara
            </legend>
            <c:if test="${edit}">
                <p>
                    <label><font color="red">*</font>Tarikh Bicara :</label>
                    <s:text name="tarikhMesyuarat" id="datepicker" class="datepicker"/>
                </p>
                <p>
                    <label><font color="red">*</font>Masa :</label>
                    <s:select name="jam" style="width:50px">
                        <s:option value="01">01</s:option>
                        <s:option value="02">02</s:option>
                        <s:option value="03">03</s:option>
                        <s:option value="04">04</s:option>
                        <s:option value="05">05</s:option>
                        <s:option value="06">06</s:option>
                        <s:option value="07">07</s:option>
                        <s:option value="08">08</s:option>
                        <s:option value="09">09</s:option>
                        <s:option value="10">10</s:option>
                        <s:option value="11">11</s:option>
                        <s:option value="12">12</s:option>
                    </s:select>:
                    <s:select name="minit" style="width:50px">
                        <s:option value="00">00</s:option>
                        <s:option value="05">05</s:option>
                        <s:option value="10">10</s:option>
                        <s:option value="15">15</s:option>
                        <s:option value="20">20</s:option>
                        <s:option value="25">25</s:option>
                        <s:option value="30">30</s:option>
                        <s:option value="35">35</s:option>
                        <s:option value="40">40</s:option>
                        <s:option value="45">45</s:option>
                        <s:option value="50">50</s:option>
                        <s:option value="55">55</s:option>
                    </s:select>
                    <s:select name="ampm" style="width:100px">
                        <s:option value="AM">Pagi</s:option>
                        <s:option value="PM">Petang</s:option>
                    </s:select>
                </p>
                <p>
                    <label><font color="red">*</font>Tempat Bicara :</label>
                    <s:text name="permohonanPerbicaraan.lokasiBicara" maxlength="50" size="50" id="tempat"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </c:if>
            <c:if test="${!edit}">
                <p>
                    <label>Tarikh Bicara :</label>
                    ${actionBean.tarikhMesyuarat}
                </p>
                <p>
                    <label>Masa :</label>
                    ${actionBean.jam}:${actionBean.minit}
                    <c:if test="${actionBean.ampm eq 'AM'}">
                        PAGI
                    </c:if>
                    <c:if test="${actionBean.ampm eq 'PM'}">
                        PETANG
                    </c:if>
                   
                </p>
                <p>
                    <label>Tempat Bicara :</label>
                    ${actionBean.permohonanPerbicaraan.lokasiBicara}
                </p>
            </c:if>
        </fieldset>

    </div>
</s:form>
