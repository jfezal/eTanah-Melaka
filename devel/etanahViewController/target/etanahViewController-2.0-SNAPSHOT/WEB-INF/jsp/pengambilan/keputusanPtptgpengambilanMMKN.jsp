<%-- 
    Document   : keputusanPtptgpengambilanMMKN
    Created on : Jun 18, 2010, 9:42:47 AM
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

<s:form beanclass="etanah.view.stripes.pengambilan.KeputusanPtptgpengambilanMMKNActionBean">

    <div class="subtitle">
        <s:errors/>
        <s:messages/>
        <fieldset class="aras1">
            <legend>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SEK4' && actionBean.permohonan.cawangan.kod eq '05'}">Keputusan Kertas Menteri Besar(MB) Pengambilan Seksyen 4</c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SEK4' && actionBean.permohonan.cawangan.kod eq '04'}">Keputusan eMMKN Pengambilan Seksyen 4</c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq '831A'}">Keputusan  MB Pengambilan Seksyen 3(1)(a)</c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq '831BC'}">Keputusan  MMKN Pengambilan Seksyen 3(1)(b)</c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PB'}">Keputusan  MMKN Penarikan Balik</c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTSP'}">Keputusan  MMKN Pendudukan Sementara</c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SEK4A'}">Keputusan Kertas MMK</c:if>
                
            </legend><br />
            <p>
                <label for="ID Permohonan">ID Permohonan / Perserahan :</label>
                ${actionBean.permohonan.idPermohonan}&nbsp;
            </p>
            <p>
                <label for="Permohonan">Urusan :</label>
                ${actionBean.permohonan.kodUrusan.nama}&nbsp;
            </p>
              <c:if test="${view}">

                    <p>
                    <label>Keputusan :</label>
                    ${actionBean.keputusanDisplay}&nbsp;
                </p>
       <%-- </fieldset >
    </div>--%>
    </c:if>
        </fieldset >
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <c:if test="${keputusan}">
                <p>
                    <label><font color="red">*</font>Keputusan :</label>
                    <s:radio name="keputusan" value="L"/>Lulus&nbsp;
                    <s:radio name="keputusan" value="T"/>Tolak&nbsp;
                    <%--<s:radio name="keputusan" value="S"/>Tangguh--%>
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
