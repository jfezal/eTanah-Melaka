<%--
    Document   : rekodKeputusanMMK
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

<s:form beanclass="etanah.view.stripes.pengambilan.rekodKemasukanMMKActionBean">
    <s:errors/>
    <s:messages/>

    <c:if test="${form1}">
        <div class="subtitle">

            <s:hidden name="permohonanRujukanLuar.idPermohonan"/>
            <fieldset class="aras1">
                <%--<legend>Rekod Keputusan Kertas Pertimbangan Menteri Besar</legend>--%>
                <legend><c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SEK4' && actionBean.permohonan.cawangan.kod eq '05'}">Keputusan MB : Pengambilan Tanah Di bawah Seksyen 4</c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SEK4' && actionBean.permohonan.cawangan.kod eq '04'}">Keputusan eMMKN Pengambilan Seksyen 4</c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq '831A'}">Keputusan  MB Pengambilan Seksyen 3(1)(a)</c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq '831B'}">Keputusan  MMKN Pengambilan Seksyen 3(1)(b)</c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq '831C'}">Keputusan  MMKN Pengambilan Seksyen 3(1)(c)</c:if>
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
                <legend>Maklumat Mesyuarat</legend>
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
                    <c:if test="${keputusan}">
                        <tr >
                            <td width="50%" align="right"><font color="red">*</font><font color="#003194" size="2"><b>Keputusan :</b></font></td>
                            <td> <s:radio name="keputusan" value="L"/>Lulus&nbsp;
                                <s:radio name="keputusan" value="T"/>Tolak&nbsp;</td>
                        </tr>
                    </c:if>
                </table>

          <%--      <c:if test="${keputusan}">
                    <table width="80%" border="0">
                        <tr>
                            <td valign="top" width="50%" align="right">
                                <font color="#003194" size="2"><b>Syor Pengarah Tanah dan Galian :</b></font></td>
                            <td><table border="0" cellspacing="10">
                                    <c:forEach var="i" begin="1" end="${actionBean.count}">
                                        <c:set var="recordCount" value="0"/>
                                        <c:forEach items="${actionBean.senaraiSyor[i]}" var="senarai">
                                            <c:set var="recordCount" value="${recordCount+1}"/>
                                            <c:if test="${recordCount eq 1}">
                                                <tr>
                                                    <td><b>${i})&nbsp;</b></td>
                                                    <td colspan="2">${senarai.kandungan}</td>
                                                </tr>
                                            </c:if>
                                            <c:if test="${recordCount ne 1}">
                                                <tr>
                                                    <td></td>
                                                    <td><b><c:out value="(${actionBean.str[recordCount-1]}"/>)&nbsp;</b></td>
                                                    <td>${senarai.kandungan}</td>
                                                </tr>
                                            </c:if>
                                        </c:forEach>
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
                <legend><c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SEK4' && actionBean.permohonan.cawangan.kod eq '05'}">Keputusan MB : Pengambilan Tanah Di bawah Seksyen 4</c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SEK4' && actionBean.permohonan.cawangan.kod eq '04'}">Keputusan eMMKN Pengambilan Seksyen 4</c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq '831A'}">Keputusan  MB Pengambilan Seksyen 3(1)(a)</c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq '831B'}">Keputusan  MMKN Pengambilan Seksyen 3(1)(b)</c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq '831C'}">Keputusan  MMKN Pengambilan Seksyen 3(1)(c)</c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PB'}">Keputusan  MMKN Penarikan Balik</c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTSP'}">Keputusan  MMKN Pendudukan Sementara</c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SEK4A'}">Keputusan eMMKN Pengambilan Seksyen 4 - Aduan</c:if></legend>
                    <%--<legend>Rekod Keputusan Kertas Pertimbangan Menteri Besar</legend>--%>
                <br />
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
                    <%--<tr>
                        <td><label>Bilangan Mesyuarat :</label></td>
                        <td width="80%">${actionBean.permohonanRujukanLuar.noSidang}</td>
                    </tr>
                    <tr>
                        <td><label>Tarikh :</label></td>
                        <td width="80%">${actionBean.tarikhMesyuarat}</td>
                    </tr>--%>
                    <tr>
                        <td><label>Keputusan :</label></td>
                        <td width="80%">${actionBean.keputusanDisplay}</td>
                    </tr>
                </table>
                <%--<table width="80%" border="0">
                    <tr>
                        <td valign="top" width="45.9%" align="right">
                            <font color="#003194" size="2"><b>Syor Pengarah Tanah dan Galian (Syarat-Syarat Kelulusan) :</b></font></td>
                        <td align="left"><table border="0" cellspacing="10">
                                <c:forEach var="i" begin="1" end="${actionBean.count}">
                                    <c:set var="recordCount" value="0"/>
                                    <c:forEach items="${actionBean.senaraiSyor[i]}" var="senarai">
                                        <c:set var="recordCount" value="${recordCount+1}"/>
                                        <c:if test="${recordCount eq 1}">
                                            <tr>
                                                <td><b>${i})&nbsp;</b></td>
                                                <td colspan="2">${senarai.kandungan}</td>
                                            </tr>
                                        </c:if>
                                        <c:if test="${recordCount ne 1}">
                                            <tr>
                                                <td></td>
                                                <td><b><c:out value="(${actionBean.str[recordCount-1]}"/>)&nbsp;</b></td>
                                                <td>${senarai.kandungan}</td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                </table>--%>
            </fieldset >
        </div>
    </c:if>
</s:form>
