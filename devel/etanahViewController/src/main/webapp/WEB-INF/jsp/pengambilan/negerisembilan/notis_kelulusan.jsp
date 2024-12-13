<%-- 
    Document   : notis kelulusan
    Created on : 11-Jun-2013, 12:41:03
    Author     : dayana
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

<s:form beanclass="etanah.view.stripes.pengambilan.rekodKeputusanMMKn9ActionBean">
    <s:errors/>
    <s:messages/>


    <div class="subtitle">

        <s:hidden name="permohonanRujukanLuar.idPermohonan"/>
        <fieldset class="aras1">
            <%--<legend>Rekod Keputusan Kertas Pertimbangan Menteri Besar</legend>--%>
            <legend align="center"><c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SEK4' && actionBean.permohonan.cawangan.kod eq '05'}">Keputusan MMK Pengambilan Seksyen 4</c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SEK4' && actionBean.permohonan.cawangan.kod eq '04'}">Keputusan eMMKN Pengambilan Seksyen 4</c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq '831A'}">Keputusan  MB Pengambilan Seksyen 3(1)(a)</c:if>

                <c:if test="${actionBean.permohonan.kodUrusan.kod eq '831C'}">Keputusan Persetujuan Rundingan</c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PB'}">Keputusan  MMKN Penarikan Balik</c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTSP'}">Keputusan  MMKN Pendudukan Sementara</c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SEK4A'}">Keputusan eMMKN Pengambilan Seksyen 4 - Aduan</c:if></legend><br />

            </fieldset >
        </div>
    <c:if test="${actionBean.keputusanDisplay eq 'Tolak'}">
        <p style="color: #FF0000">  SKRIN NOTIS KELULUSAN TIDAK DIPAPARKAN JIKA PERMOHONAN TIDAK DILULUSKAN  </p>
    </c:if>
    <div align="center">
        <c:if test="${actionBean.keputusanDisplay eq 'Lulus'}">
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq '831B'}"><b>NOTIS KELULUSAN</b></c:if>
        </div>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend align="center">Terma-terma dan Syarat-Syarat </legend>
                <table width="80%" border="0" >
                </table>
                <br/>

            </fieldset >
        </div>

        <div class="subtitle">
            <s:hidden name="permohonanRujukanLuar.idPermohonan"/>

        </div>
        <div class="subtitle">
            <fieldset class="aras1">                               
                <div style="text-align:center">
                    <s:textarea name="notis" style="alignment-baseline: center" cols="100" class="normal_text" rows="12" value="${actionBean.permohonan.catatan}"/></div>                    
                <br><br>
                <div style="text-align:center" > 
                    <s:button name="simpanNotis" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </div>
            </fieldset>
        </div>
    </c:if> 

</s:form>
