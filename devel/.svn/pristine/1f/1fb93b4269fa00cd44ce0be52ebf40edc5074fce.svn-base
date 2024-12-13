<%--
    Document   : rekodKeputusanMMK_new
    Created on : Sept 08, 2011, 10:42:47 AM
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

<s:form beanclass="etanah.view.stripes.pengambilan.RekodKemasukanMMK_newActionBean">
    <s:errors/>
    <s:messages/>

    <c:if test="${form1}">
    <div class="subtitle">

        <s:hidden name="permohonanRujukanLuar.idPermohonan"/>
        <fieldset class="aras1">
            <legend>Keputusan MMK Penarikan Balik</legend><br />
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
            <legend>Maklumat Mesyuarat</legend>
            <table width="80%" border="0" >
                <c:if test="${keputusan}">
                <tr >
                    <td width="50%" align="right"><font color="red">*</font><font color="#003194" size="2"><b>Keputusan :</b></font></td>
                    <td> <s:radio name="keputusan" value="L"/>Lulus&nbsp;
                    <s:radio name="keputusan" value="T"/>Tolak&nbsp;</td>
                </tr>
                </c:if>
            </table>

            <%--<c:if test="${keputusan}">
                <table width="80%" border="0">
                    <tr>
                        <td valign="top" width="50%" align="right">
                            <font color="#003194" size="2"><b>Syor Pengarah Tanah dan Galian :</b></font></td>
                        <td><table border="0" cellspacing="10">
                                    <c:forEach items="${actionBean.senaraiKertasKandungan9}" var="line">
                                            <tr>
                                                <td valign="top"><b><c:out value="${line.subtajuk}"/>)&nbsp;</b></td>
                                                <td>${line.kandungan}</td>
                                            </tr>
                                    </c:forEach>
                            </table>
                        </td>
                    </tr>
                </table>
            </c:if>--%>
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
            <legend>Rekod Keputusan Penarikan Balik</legend><br />
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
                        <td><label>Keputusan :</label></td>
                        <td width="80%">${actionBean.keputusanDisplay}</td>
                    </tr>
                </table>
                    <%--<table width="80%" border="0">
                    <tr>
                        <td valign="top" width="50%" align="right">
                            <font color="#003194" size="2"><b>Syor Pengarah Tanah dan Galian :</b></font></td>
                        <td><table border="0" cellspacing="10">
                                    <c:forEach items="${actionBean.senaraiKertasKandungan9}" var="line">
                                            <tr>
                                                <td valign="top"><b><c:out value="${line.subtajuk}"/>)&nbsp;</b></td>
                                                <td>${line.kandungan}</td>
                                            </tr>
                                    </c:forEach>
                            </table>
                        </td>
                    </tr>
                </table>--%>
        </fieldset >
    </div>
    </c:if>
</s:form>
