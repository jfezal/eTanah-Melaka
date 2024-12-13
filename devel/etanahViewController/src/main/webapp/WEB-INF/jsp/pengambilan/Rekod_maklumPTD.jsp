<%-- 
    Document   : Rekod_maklumPTD
    Created on : 16-Dec-2010, 12:58:48
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

<s:form beanclass="etanah.view.stripes.pengambilan.notifikasiActionBean">

    <div class="subtitle">
        <s:errors/>
        <s:messages/>
        <fieldset class="aras1">
            <legend>
                        <%--<c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SEK4'}">Keputusan Pengambilan Seksyen 4 MMK</c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq '831A'}">Keputusan Pengambilan Seksyen 831(A) MMK</c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq '831BC'}">Keputusan Pengambilan Seksyen 831B MMK</c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PB'}">Keputusan Penarikan Balik MMK</c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTSP'}">Keputusan Pendudukan Sementara MMK</c:if>--%>

                <%--Keputusan Penarikan Balik MMKN Dari Pengambilan Balik Tanah--%></legend><br />
            <p>
                <label for="ID Permohonan">ID Permohonan / Perserahan :</label>0505ACQ2010007029
                <%--${actionBean.permohonan.idPermohonan}&nbsp;--%>
            </p>
            <p>
                <label for="Permohonan">Urusan :</label> Pengambilan Tanah Seksyen 831A
                <%--${actionBean.permohonan.kodUrusan.nama}&nbsp;--%>
            </p>
        </fieldset >
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
                <p>
                    <label>Keputusan :</label>
                    Tiada Data
                </p>
                <p>
                    <label>Kertas Mesyuarat No. :</label>
                    Tiada Data
                </p>
                <p>
                    <label>Warta Kerajaan Negeri </label>
                    <br>
                    <label>a)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No. Warta :</label>Tiada Data
                    <br>
                    <label>&nbsp;&nbsp;&nbsp;&nbsp;b)Tarikh Warta :</label>Tiada Data
                </p>
                <p>
                    <label>Tarikh Borang A :</label>
                   Tiada Data
                </p>
                <p>
                    <label>Tarikh Borang B :</label>
                    Tiada Data
                </p>

           
          
                
                
            
        </fieldset >
    </div>
</s:form>
