<%--
    Document   : popup_view_tnh_sekeliling
    Created on : Jul 4, 2012, 6:38:28 PM
    Author     : User
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });
    function validateForm(){
        self.opener.refreshPageCeroboh();
        self.close();
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
    function test(f) {
        $(f).clearForm();
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<s:form name="form3" id="form" beanclass="etanah.view.penguatkuasaan.JenisSempadanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
        <s:hidden name="idLaporan" value="${actionBean.laporanTanahSempadan.laporanTanah.idLaporan}"/>
        <s:hidden name="idLaporTanahSpdn" value="${actionBean.laporanTanahSempadan.idLaporTanahSpdn}"/>
        <fieldset class="aras1">
            <legend> Maklumat Jenis Bangunan </legend>
            <table>
                <tr>
                    <td valign="top">
                        <p>
                            <label>Jenis Sempadan :</label></p></td>
                    <td valign="top">
                        <c:if test="${actionBean.laporanTanahSempadan.jenisSempadan eq 'U'}">Utara</c:if>
                        <c:if test="${actionBean.laporanTanahSempadan.jenisSempadan eq 'S'}">Selatan</c:if>
                        <c:if test="${actionBean.laporanTanahSempadan.jenisSempadan eq 'T'}">Timur</c:if>
                        <c:if test="${actionBean.laporanTanahSempadan.jenisSempadan eq 'B'}">Barat</c:if>
                    </td>
            </table>
            <table>
                <tr>
                    <td valign="top">
                        <p><label>Hakmilik :</label></p></td>
                    <td valign="top">
                        ${actionBean.laporanTanahSempadan.hakmilik.idHakmilik}&nbsp;</td>
                </tr>
            </table>
            <table>
                <tr>
                    <td valign="top">
                        <p><label>No Lot :</label></p></td>
                    <td valign="top">
                        ${actionBean.laporanTanahSempadan.noLot}&nbsp;</td>
                </tr>
            </table>
            <table>
                <tr>
                    <td valign="top">
                        <p><label>Kot Lot :</label></p></td>
                    <td valign="top">
                        ${actionBean.laporanTanahSempadan.kodLot.nama}&nbsp;</td>
                </tr>
            </table>
            <%--<p>
                <label>Hakmilik :</label>
                ${actionBean.laporanTanahSempadan.hakmilik.idHakmilik}
            </p>
            <p>
                <label>No Lot :</label>
                ${actionBean.laporanTanahSempadan.noLot}
            </p>
            <p>
                <label>Kod Lot :</label>
                ${actionBean.laporanTanahSempadan.kodLot.nama}
            </p>--%>

            <table>
                <tr>
                    <td valign="top">
                        <p><label>Catatan :</label></p></td>
                    <td valign="top">
                        ${actionBean.laporanTanahSempadan.catatan}&nbsp;</td>
                </tr>
            </table>
            <%-- <p>
                 <label>Catatan :</label>
                 ${actionBean.laporanTanahSempadan.catatan}
             </p>--%>


        </fieldset>

        <p><label>&nbsp;</label>
            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
        </p>
        <br>
    </div>
</s:form>