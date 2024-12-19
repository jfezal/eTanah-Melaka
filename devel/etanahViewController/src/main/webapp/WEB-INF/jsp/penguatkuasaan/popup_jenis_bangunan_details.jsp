<%--
    Document   : popup_jenis_bangunan_details
    Created on : Jul 4, 2012, 5:04:48 PM
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
<s:form name="form3" id="form" beanclass="etanah.view.penguatkuasaan.JenisBangunanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
        <fieldset class="aras1">
            <legend> Maklumat Jenis Bangunan </legend>

            <p>
                <label>Jenis Bangunan :</label>
                <c:if test="${actionBean.permohonanLaporanBangunan.jenisBangunan eq 'KK'}">Kekal</c:if>
                <c:if test="${actionBean.permohonanLaporanBangunan.jenisBangunan eq 'SK'}">Separuh Kekal</c:if>
                <c:if test="${actionBean.permohonanLaporanBangunan.jenisBangunan eq 'SM'}">Sementara</c:if>
                <c:if test="${actionBean.permohonanLaporanBangunan.jenisBangunan eq 'LL'}">Lain-lain</c:if>
            </p>

            <p>
                <label>Ukuran :</label>
                ${actionBean.permohonanLaporanBangunan.ukuran}x${actionBean.permohonanLaporanBangunan.keteranganTahunBinaan}
            </p>
            <p>
                <label>Unit Ukuran :</label>
                ${actionBean.permohonanLaporanBangunan.uomUkuran.nama}
            </p>
            <p>
                <label>Nilai :</label>
                <%--${actionBean.permohonanLaporanBangunan.nilai}--%>
                <%--<s:format formatPattern="#,##0.00" value="${actionBean.permohonanLaporanBangunan.nilai}" />--%>
                <fmt:formatNumber pattern="#,##0.00" value="${actionBean.permohonanLaporanBangunan.nilai}"/>
            </p>
            <p>
                <label>Nama Pemunya :</label>
                ${actionBean.permohonanLaporanBangunan.namaPemunya}
            </p>
            <p>
                <label>Nama Penyewa :</label>
                ${actionBean.permohonanLaporanBangunan.namaPenyewa}
            </p>

        </fieldset>

        <p><label>&nbsp;</label>
            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
        </p>
        <br>
    </div>
</s:form>