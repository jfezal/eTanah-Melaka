<%-- 
    Document   : paparan_hakmilik_single
    Created on : Nov 1, 2009, 6:15:42 PM
    Author     : wan.fairul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<s:form action="/daftar/kesinambungan" >
    <div class="subtitle">
       
            <fieldset class="aras1">
            <legend>
                Maklumat Syarikat MCL
            </legend>
           <table border="0">
        <tr><td>&nbsp;</td></tr>
        <tr>
            <td id="tdLabel"><label>Nama Syarikat :</label></td>
            <td id="tdDisplay">&nbsp;${actionBean.mohonPihak.nama}&nbsp;</td>
        </tr>
        <tr>
            <td id="tdLabel"><label>Nombor Syarikat :</label></td>
            <td id="tdDisplay">&nbsp;${actionBean.mohonPihak.noPengenalan}&nbsp;</td>
        </tr>
        <tr>
            <td id="tdLabel"><label>Status Syarikat :</label></td>
            <td id="tdDisplay">&nbsp;${actionBean.mohonPihak.statusMohonPihak.nama}&nbsp;</td>
        </tr>
        <tr>
            <td id="tdLabel"><label>Alamat Syarikat :</label></td>
            <td id="tdDisplay">&nbsp;${actionBean.mohonPihak.alamat.alamat1}&nbsp;</td>
        </tr>
        <c:if test="${actionBean.mohonPihak.alamat.alamat2 ne null}">
            <tr>
                <td id="tdLabel">&nbsp;</td>
                <td id="tdDisplay">&nbsp;${actionBean.mohonPihak.alamat.alamat2}&nbsp;</td>
            </tr>
        </c:if>
        <c:if test="${actionBean.mohonPihak.alamat.alamat3 ne null}">
            <tr>
                <td id="tdLabel">&nbsp;</td>
                <td id="tdDisplay">&nbsp;${actionBean.mohonPihak.alamat.alamat3}&nbsp;</td>
            </tr>
        </c:if>
        <tr>
            <td id="tdLabel"><label>Bandar :</label></td>
            <c:if test="${actionBean.mohonPihak.alamat.alamat4 ne null}">
                <td id="tdDisplay">&nbsp;${actionBean.mohonPihak.alamat.alamat4}&nbsp;</td>
            </c:if>
            <c:if test="${actionBean.mohonPihak.alamat.alamat4 eq null}">
                <td id="tdDisplay">&nbsp; - &nbsp;</td>
            </c:if>
        </tr>
        <tr>
            <td id="tdLabel"><label>Poskod :</label></td>
            <c:if test="${actionBean.mohonPihak.alamat.poskod ne null}">
                <td id="tdDisplay">&nbsp;${actionBean.mohonPihak.alamat.poskod}&nbsp;</td>
            </c:if>
            <c:if test="${actionBean.mohonPihak.alamat.poskod eq null}">
                <td id="tdDisplay">&nbsp; - &nbsp; </td>
            </c:if>
        </tr>
        <tr>
            <td id="tdLabel"><label>Negeri :<label></td>
            <c:if test="${actionBean.mohonPihak.alamat.negeri.nama ne null}">
                <td id="tdDisplay">&nbsp;${actionBean.mohonPihak.alamat.negeri.nama}&nbsp;</td>
            </c:if>
            <c:if test="${actionBean.mohonPihak.alamat.negeri.nama eq null}">
                <td id="tdDisplay"> - </td>
            </c:if>
        </tr>
      </table>
            <p align="center">
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>
        <br>
    </div>
</s:form>
