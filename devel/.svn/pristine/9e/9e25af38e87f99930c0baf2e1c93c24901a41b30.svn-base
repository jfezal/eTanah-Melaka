<%-- 
    Document   : rekodKeputusanMMKn9
    Created on : 22-Nov-2011, 12:41:03
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

<s:form beanclass="etanah.view.stripes.pengambilan.rekodKeputusanMMKn9ActionBean">
    <s:errors/>
    <s:messages/>


    <div class="subtitle">

        <s:hidden name="permohonanRujukanLuar.idPermohonan"/>
        <fieldset class="aras1">
            <%--<legend>Rekod Keputusan Kertas Pertimbangan Menteri Besar</legend>--%>
            <legend><c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SEK4' && actionBean.permohonan.cawangan.kod eq '05'}">Keputusan MMK Pengambilan Seksyen 4</c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SEK4' && actionBean.permohonan.cawangan.kod eq '04'}">Keputusan eMMKN Pengambilan Seksyen 4</c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq '831A'}">Keputusan  MB Pengambilan Seksyen 3(1)(a)</c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq '831B'}">Keputusan Persetujuan Rundingan</c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq '831C'}">Keputusan Persetujuan Rundingan</c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PB'}">Keputusan  MMKN Penarikan Balik</c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTSP'}">Keputusan  MMKN Pendudukan Sementara</c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SEK4A'}">Keputusan eMMKN Pengambilan Seksyen 4 - Aduan</c:if></legend><br />
            <table width="80%" border="0" >
                <tr >
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
            <legend>Keputusan Rundingan</legend>
            <table width="80%" border="0" >
                <%-- <c:if test="${bilMesyuarat}">
                 <tr >
                     <td width="50%" align="right"><font color="red">*</font><font color="#003194" size="2"><b>Bilangan Mesyuarat :</b></font></td>
                     <td><s:text name="permohonanRujukanLuar.noSidang" onkeyup="validateNumber(this,this.value);" maxlength="3"/>
                         <s:hidden name="permohonanRujukanLuar.idRujukan"/>
                     </td>
                 </tr>
                 </c:if>
                 <c:if test="${tarikhMasa}">
                 <tr >
                     <td width="50%" align="right"><font color="red">*</font><font color="#003194" size="2"><b>Tarikh :</b></font></td>
                     <td> <s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" /></td>
                 </tr>
                 </c:if>--%>

            </table>

            <c:if test="${keputusan}">

            </c:if>
            <br/>

        </fieldset >
    </div>

    <div class="subtitle">
        <s:hidden name="permohonanRujukanLuar.idPermohonan"/>

    </div>
    <div class="subtitle">
        <fieldset class="aras1">

            <table width="80%" border="0" >
                <%--<tr>
                    <td><label>Bilangan Mesyuarat :</label></td>
                    <td width="80%">${actionBean.permohonanRujukanLuar.noSidang}</td>
                </tr>
                <tr>
                    <td><label>Tarikh :</label></td>
                    <td width="80%">${actionBean.tarikhMesyuarat}</td>
                </tr>--%>
                <tr>
                    <td><label>Keputusan Rundingan :</label></td>
                    <td width="80%">${actionBean.keputusanDisplay}</td>
                </tr>
            </table>
            <table width="80%" border="0">
                <tr>
                    <td valign="top" width="45.9%" align="center">
                        <font color="#003194" size="2"><b>MEMO</b></font></td>
                    <td align="left">
                        <table border="0" cellspacing="10">
                            <tr><td>
                                    3.
                                </td><td><s:textarea name="memo" cols="150" class="normal_text" rows="15" value="${actionBean.permohonan.ulasan}"/></td></tr> 



                        </table>
                    </td>
                </tr>
            </table>
            <div style="alignment-baseline: center" > 
                <s:button name="simpanMemo" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </div>
        </fieldset >
    </div>

</s:form>
