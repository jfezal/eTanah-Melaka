<%--
    Document   : tetap_enkuiri
    Created on : Mar 4, 2010, 6:43:00 PM
    Author     : mazurahayati
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="etanah.model.Pengguna"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript">
</script>
<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        text-align:right;
        width:15em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:60em;
    }
</style>

<s:form beanclass="etanah.view.stripes.lelong.MaklumatEnkuiriActionBean">
    <s:messages/>
    <s:errors/>&nbsp;
    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Siasatan
            </legend>
            <div class="content">
                <table>
                    <tr>
                        <td ><label> Tarikh :</label>
                        </td>
                        <td>
                            <fmt:formatDate value="${actionBean.enkuiri.tarikhEnkuiri}" pattern="dd/MM/yyyy"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Hari : </label>
                        </td>
                        <td>
                            <font style="text-transform: uppercase"><fmt:formatDate value="${actionBean.enkuiri.tarikhEnkuiri}" pattern="EEEE"/></font>&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Masa : </label>
                        </td>
                        <td>
                            <fmt:formatDate value="${actionBean.enkuiri.tarikhEnkuiri}" pattern="hh:mm"/>&nbsp;
                            <fmt:formatDate type="time"
                                            pattern="aaa"
                                            value="${actionBean.enkuiri.tarikhEnkuiri}" var="timeformat"/>
                            <c:if test="${timeformat eq 'AM'}"> PAGI</c:if>
                            <c:if test="${timeformat eq 'PM'}"> PETANG</c:if>
                        </td>
                    </tr>
                    <tr>
                        <td valign="top">
                            <label> Tempat : </label>
                        </td>
                        <td>
                            <font style="text-transform: uppercase">${actionBean.enkuiri.tempat}</font>&nbsp;&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label> Perkara : </label>
                        </td>
                        <td>
                            <font style="text-transform: uppercase">${actionBean.enkuiri.perkara}</font> &nbsp;
                        </td>
                    </tr>
                </table>
            </div>

        </fieldset>
    </div>

    <c:if test="${fn:length(actionBean.senaraiEnkuiri3)>0}">
        <br>
        <fieldset class="aras1">
            <legend>
                Sejarah Siasatan
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiEnkuiri3}" id="line">
                    <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                    <display:column title="Tarikh"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhEnkuiri}"/></display:column>
                    <display:column title="Masa"><fmt:formatDate pattern="hh:mm aaa" value="${line.tarikhEnkuiri}"/></display:column>
                    <display:column property="tempat" title="Tempat" class="nama${line_rowNum}"/>
                    <display:column property="perkara" title="Perkara" class="nama${line_rowNum}"/>
                    <display:column property="status.nama" style="text-transform: uppercase;" title="Status" class="nama${line_rowNum}"/>
                    <display:column property="ulasanPegawai" title="Ulasan" class="nama${line_rowNum}"/>
                </display:table>
            </div>
        </fieldset>
    </c:if>
    <br>

</s:form>
