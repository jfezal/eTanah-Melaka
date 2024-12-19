<%-- 
    Document   : kemasukan_tarikh_keputusan_MMK
    Created on : 19-May-2010, 14:49:47
    Author     : nordiyana
--%>

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

<s:form beanclass="etanah.view.stripes.pengambilan.kemasukanKeputusanMMKActionBean">

    <div class="subtitle">
        <s:errors/>
        <s:messages/>
        <s:hidden name="permohonanRujukanLuar.idPermohonan"/>
        <fieldset class="aras1">
        <legend>Rekod Keputusan Kertas Pertimbangan Menteri Besar</legend>
        
            <%--<legend>Maklumat Permohonan</legend>--%>
            <p>
                <label for="ID Permohonan">ID Permohonan / Perserahan :</label>
                ${actionBean.permohonan.idPermohonan}&nbsp;
            </p>
            <p>
                <label for="Permohonan">Urusan :</label>
                ${actionBean.permohonan.kodUrusan.nama}&nbsp;
            </p>
        </fieldset >
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Mesyuarat</legend>
            <c:if test="${bilMesyuarat}">
                <p>
                    <label><font color="red">*</font>Bilangan Mesyuarat :</label>
                    <s:text name="noSidang" onkeyup="validateNumber(this,this.value);" maxlength="3"/>
                    <s:hidden name="permohonanRujukanLuar.idRujukan"/>
                </p>
            </c:if>
            <c:if test="${tarikhMasa}">
                <p>
                    <label><font color="red">*</font>Tarikh :</label>
                    <s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" />
                </p>
                
            </c:if>
            <c:if test="${keputusan}">
                <p>
                    <label><font color="red">*</font>Keputusan :</label>
                    <s:radio name="keputusan" value="L"/>Lulus&nbsp;
                    <s:radio name="keputusan" value="T"/>Tolak&nbsp;
                    <s:radio name="keputusan" value="S"/>Tangguh
                </p>
            </c:if>
            <p>
                <label>&nbsp;</label>
                <c:if test="${simpanMesyuarat}">
                    <s:button name="simpanMesyuarat" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </c:if>
            </p>
        </fieldset >
    </div>
</s:form>
