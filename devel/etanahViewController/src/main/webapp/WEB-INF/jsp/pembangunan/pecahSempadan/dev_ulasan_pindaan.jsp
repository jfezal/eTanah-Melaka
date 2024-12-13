<%-- 
    Document   : dev_ulasan_pindaan
    Created on : Mar 7, 2011, 11:36:38 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<s:form beanclass="etanah.view.stripes.pembangunan.UlasanPindaanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Ulasan Pindaan</legend>
            <p>
                <label>Ulasan :</label>
                ${actionBean.ulasan}&nbsp;
                <%--<c:if test="${edit}">
                    <s:textarea name="fp.ulasan" rows="5" cols="120"/>
                </c:if>
                <c:if test="${!edit}">
                    ${actionBean.fp.ulasan}&nbsp;
                </c:if>--%>
            </p>
           <%-- <c:if test="${edit}">
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </c:if>--%>
        </fieldset>
    </div>
</s:form>

