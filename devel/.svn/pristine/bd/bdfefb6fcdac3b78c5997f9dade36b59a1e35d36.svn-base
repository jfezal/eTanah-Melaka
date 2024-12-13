<%-- 
    Document   : kutipan_tanpa_denda_1
    Created on : Apr 12, 2011, 6:16:07 PM
    Author     : abdul.hakim
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Kutipan Tanpa Denda</font>
                </div>
            </td>
        </tr>
    </table></div>

<stripes:form beanclass="etanah.view.stripes.hasil.KutipanTanpaDendaActionBean" id="kutipan_tanpa_denda">
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Hasil Carian</legend>
            <p>
                <label>Nama Kumpulan : </label>
                ${actionBean.tagAkaun.idTag}
            </p>

            <p>
                <label>Catatan : </label>
                ${actionBean.tagAkaun.catatan}
            </p>

            <p>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiTagAkaunAhli}" id="line">
                    <display:column title="Bil."><div align="center">${line_rowNum}.</div></display:column>
                    <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                        <display:column property="akaun.noAkaun" title="Nombor Akaun" />
                    </c:if>
                    <display:column property="akaun.hakmilik.idHakmilik" title="ID Hakmilik"/>
                    <display:column property="akaun.hakmilik.noLot" title="No. Lot / PT"/>
                    <display:column property="akaun.hakmilik.lokasi" title="Lokasi"/>
                    <display:column property="akaun.pemegang.nama" title="Pembayar"/>
                    <display:column style="text-align:right" property="akaun.baki" title="Amaun (RM)" format="{0,number, 0.00}"/>
                </display:table>
            </div>
            </p>
        </fieldset>
    </div>

    <div align="center"><table style="width:99.2%" bgcolor="green">
            <tr>
                <td align="right">
                    <stripes:submit name="Step2" value="Kembali" class="btn"/>
                    <stripes:submit name="Step4" value="Kecualikan Denda" class="longBtn"/>
                </td>
            </tr>
        </table></div>
</stripes:form>
