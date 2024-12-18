<%--
    Document   : rekodKeputusanMMK_PILDA
    Created on : Jan 25, 2011, 9:42:47 AM
    Author     : massita
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

<s:form beanclass="etanah.view.stripes.pengambilan.rekodKeputusanMMK_PILDA_ActionBean">
    <s:errors/>
    <s:messages/>

    <c:if test="${form1}">
    <div class="subtitle">

        <s:hidden name="permohonanRujukanLuar.idPermohonan"/>
        <fieldset class="aras1">
            <%--<legend>Rekod Keputusan Kertas Pertimbangan Menteri Besar</legend>--%>
            <legend>Rekod Keputusan Izin Lalu Akta Bekalan Letrik</legend><br />
            <table width="80%" border="0" >
                <tr>
                    <td width="50%" align="right"><font color="#003194" size="2"><b>ID Permohonan / Perserahan :</b></font></td>
                    <td>${actionBean.permohonan.idPermohonan}&nbsp;</td>
                </tr>
                <tr >
                    <td width="50%" align="right"><font color="#003194" size="2"><b>Urusan :</b></font></td>
                    <td> ${actionBean.permohonan.kodUrusan.nama}&nbsp;</td>
                </tr>
            </table>
        </fieldset >
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Mesyuarat</legend>
            <table width="80%" border="0" >
                <c:if test="${bilMesyuarat}">
                <tr >
                    <td width="50%" align="right"><font color="red">*</font><font color="#003194" size="2"><b>Bilangan Mesyuarat :</b></font></td>
                    <td><s:text name="permohonanRujukanLuar.noSidang" onkeyup="validateNumber(this,this.value);" maxlength="10"/>
                        <s:hidden name="permohonanRujukanLuar.idRujukan"/>
                    </td>
                </tr>
                </c:if>
                <c:if test="${tarikhMasa}">
                <tr >
                    <td width="50%" align="right"><font color="red">*</font><font color="#003194" size="2"><b>Tarikh :</b></font></td>
                    <td> <s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" /></td>
                </tr>
                </c:if>
                <c:if test="${keputusan}">
                <tr>
                    <td width="50%" align="right"><font color="red">*</font><font color="#003194" size="2"><b>Keputusan :</b></font></td>
                    <td><s:radio name="keputusan" value="L"/>Lulus&nbsp;
                    <s:radio name="keputusan" value="T"/>Tolak&nbsp;</td>
                </tr>
                </c:if>
            </table>

            <br/>
            <p>
                <label>&nbsp;</label>
                <c:if test="${simpanMesyuarat}">
                    <s:button name="simpanMesyuarat" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </c:if>
            </p>
        </fieldset >
    </div>
    </c:if>

    <c:if test="${view}">
         <div class="subtitle">
        <s:hidden name="permohonanRujukanLuar.idPermohonan"/>
        <fieldset class="aras1">
            <legend>Rekod Keputusan Izin Lalu Akta Bekalan Letrik</legend><br />
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
                <table width="80%" border="0" >
                    <tr>
                        <td><label>Bilangan Mesyuarat :</label></td>
                        <td width="80%">${actionBean.permohonanRujukanLuar.noSidang}</td>
                    </tr>
                    <tr>
                        <td><label>Tarikh :</label></td>
                        <td width="80%">${actionBean.tarikhMesyuarat}</td>
                    </tr>
                    <tr>
                        <td><label>Keputusan :</label></td>
                        <td width="80%">${actionBean.keputusanDisplay}</td>
                    </tr>
                </table>
                  
        </fieldset >
    </div>
    </c:if>
</s:form>
