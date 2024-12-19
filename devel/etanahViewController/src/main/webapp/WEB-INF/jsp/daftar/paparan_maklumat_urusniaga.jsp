<%-- 
    Document   : paparan_maklumat_urusniaga
    Created on : 17-Oct-2009, 00:05:05
    Author     : md.nurfikri
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<s:form beanclass="etanah.view.stripes.pindahMilik.PindahMilikActionBean">
        <fieldset class="aras1">
            <legend>Maklumat Urusniaga</legend>
            <p>
                <label for="Amaun">Amaun :</label>
                <fmt:formatNumber value="${actionBean.urusan.perjanjianAmaun}" currencySymbol="RM" type="currency"/>&nbsp;
            </p>
            <p>
                <label for="Duti">Duti Setem :</label>
                <fmt:formatNumber value="${actionBean.urusan.perjanjianDutiSetem}" currencySymbol="RM" type="currency"/>&nbsp;
            </p>
            <p>
                <label for="Mahkamah">No Mahkamah :</label>
                ${actionBean.urusan.perjanjianNoRujukan}&nbsp;
            </p>          
        </fieldset>  
</s:form>