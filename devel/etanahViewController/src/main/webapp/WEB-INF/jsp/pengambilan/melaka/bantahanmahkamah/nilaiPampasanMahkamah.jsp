<%-- 
    Document   : nilaiPampasanMahkamah
    Created on : May 31, 2011, 11:39:01 AM
    Author     : Rajesh
--%>

<%@ page language="java" contentType="text/html;" import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="etanah.model.Pengguna"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
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

    function ajaxLink(link, update) {
        $.get(link, function(data) {
            $(update).html(data);
            $(update).show();
        });
        return false;
    }
</script>
<!--KemasukkanIdhahmilik_BantahanMahkamahActionBean-->
<s:form name="form1" id="form1" beanclass="etanah.view.stripes.pengambilan.KemasukanBantahanMahkamahActionBean">
    <div  id="hakmilik_details">
        <s:messages/>
        <s:errors/>
        <s:hidden name="idPermohonanPihak" value="${actionBean.permohonanPihak.idPermohonanPihak}"/>
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <s:hidden name="idPihak" id="idPihak"/>
        <div id="maklumatpendeposit">
            <legend>Sila Pilih Tuan Tanah yang terlibat</legend>
            <div class="subtitle">

                <%--<c:if test="${actionBean.permohonanPihakPendeposit eq null}">--%>
                <fieldset class="aras1">
                    <div class="content" align="center">
                        <p>
                            <display:table style="width:75%" class="tablecloth" name="${actionBean.permohonanPihakPendepositList}"
                                           cellpadding="1" cellspacing="1" requestURI="/pengambilan/kemasukanaduan1" id="line">
                                <display:column title="Nama">

                                    <s:hidden name="hiddenIdPihak" id="hiddenIdPihak${line_rowNum-1}" value="${line.pihak.idPihak}"/>
                                    <%-- <s:link beanclass="etanah.view.stripes.pengambilan.PampasanPHLLActionBean"
                                                    event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                                <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>
                                                <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>
                                                <s:param name="simpanLaporan" value="${actionBean.simpanLaporan}"/>
                                                <font style="text-transform:uppercase;"><c:out value="${senarai.pihak.nama}"/></font>
                                            </s:link>--%>

                                    <s:link beanclass="etanah.view.stripes.pengambilan.KemasukanBantahanMahkamahActionBean"
                                            event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                        <s:param name="idPihak" value="${line.pihak.idPihak}"/>

                                        <font style="text-transform:uppercase;"><c:out value="${line.pihak.nama}"/></font>
                                    </s:link>
                                </display:column>
                                <%--<display:column title="Nama " property="pihak.nama" style="text-transform:uppercase;vertical-align:top;" />--%>
                                <%--<display:column title="No. Pengenalan" property="pihak.idPihak" style="vertical-align:top;"/>--%>
                                <display:column title="No. Pengenalan">
                                    <c:if test="${line.pihak.jenisPengenalan.kod eq 'B'}">
                                        No.KP Baru :&nbsp;
                                        ${line.pihak.noPengenalan}
                                    </c:if>
                                    <c:if test="${line.pihak.jenisPengenalan.kod eq 'L'}">
                                        No.KP Lama :&nbsp;
                                        ${line.pihak.noPengenalan}
                                    </c:if>
                                    <c:if test="${line.pihak.jenisPengenalan.kod eq 'S'}">
                                        No.Syarikat :&nbsp;
                                        ${line.pihak.noPengenalan}
                                    </c:if>
                                    <c:if test="${line.pihak.jenisPengenalan.kod eq 'D'}">
                                        No.Pendaftaran :&nbsp;
                                        ${line.pihak.noPengenalan}
                                    </c:if>
                                    <c:if test="${line.pihak.jenisPengenalan.kod eq 'F'}">
                                        No.Paksa :&nbsp;
                                        ${line.pihak.noPengenalan}
                                    </c:if>
                                    <c:if test="${line.pihak.jenisPengenalan.kod eq 'I'}">
                                        No.Polis :&nbsp;
                                        ${line.pihak.noPengenalan}
                                    </c:if>
                                    <c:if test="${line.pihak.jenisPengenalan.kod eq 'K'}">
                                        No.MyKid :&nbsp;
                                        ${line.pihak.noPengenalan}
                                    </c:if>
                                    <c:if test="${line.pihak.jenisPengenalan.kod eq 'N'}">
                                        No.Bank :&nbsp;
                                        ${line.pihak.noPengenalan}
                                    </c:if>
                                    <c:if test="${line.pihak.jenisPengenalan.kod eq 'P'}">
                                        No.Passport :&nbsp;
                                        ${line.pihak.noPengenalan}
                                    </c:if>
                                    <c:if test="${line.pihak.jenisPengenalan.kod eq 'T'}">
                                        No.Tentera :&nbsp;
                                        ${line.pihak.noPengenalan}
                                    </c:if>
                                    <c:if test="${line.pihak.jenisPengenalan.kod eq 'U'}">
                                        No.Pertubuhan :&nbsp;
                                        ${line.pihak.noPengenalan}
                                    </c:if>
                                </display:column>
                            </display:table>

                        </p>
                    </div>
                </fieldset>
                <%--</c:if>--%>
            </div>

            <br/>
        </div>

        <c:if test="${actionBean.hakmilik ne null && actionBean.pihak ne null && actionBean.permohonanPihak ne null}"  >
            <fieldset class="aras1">
                <div class="content" align="center">
                    <table class="tablecloth">
                        <tr>
                            <th>Id Hakmilik</th><th>Nama Tuan tanah</th><th>Amaun Pampasan dibawah Seksyen 6</th><th>Amaun Pampasan dibawah Seksyen 14</th><th>Amaun Pampasan Mahkamah</th>
                        </tr>
                        <tr>
                            <td>
                                ${actionBean.hakmilik.idHakmilik}
                            </td>
                            <td>
                                ${actionBean.permohonanPihak.pihak.nama}
                            </td>
                            <td>
                                <c:if test="${actionBean.amaunTawarRosak eq null}">-</c:if>
                                <c:if test="${actionBean.amaunTawarRosak ne null}">
                                    <s:text name="amaunTawarRosak" disabled="true" formatPattern="#,##0.00"/>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.amaunTawarPampasan eq null}">-</c:if>
                                <c:if test="${actionBean.amaunTawarPampasan ne null}">
                                    <s:text name="amaunTawarPampasan" onkeyup="validateNumber(this,this.value);" disabled="true" formatPattern="#,##0.00"/>
                                </c:if>
                            </td>
                            <td>
                                <s:text name="amaunTambahPampasan" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00"/>

                            </td>
                        </tr>
                    </table><br />
                    <s:button name="simpanMahkamah" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </div>
            </fieldset>
        </c:if>
        <br/>
    </div>
</s:form>
