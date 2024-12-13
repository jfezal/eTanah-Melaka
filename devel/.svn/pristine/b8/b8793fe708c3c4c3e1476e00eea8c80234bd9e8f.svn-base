<%-- 
    Document   : keputusanMMK_NS
    Created on : Jul 12, 2011, 9:30:52 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>


<s:form beanclass="etanah.view.stripes.pelupusan.KertasRingkasActionBeanNS">
    <s:messages/>
    <s:errors/>
    <div class="subtitle1" align="center">
    <fieldset class="aras1">
    <legend>Keputusan MMKN</legend>
    <c:if test="${edit}">
    <table>
        <tr>
            <td>Tarikh Sidang :</td>
            <td><s:text name ="tarikhSidang" class="datepicker" formatPattern="dd/MM/yyyy"/></td>
        </tr>
        <tr>
            <td>Bil Mesyuarat :</td>
            <td><s:text name ="bilMesy"/></td>
        </tr>
       
    </table>
        <br/>
            <p>
                <s:button name="simpanMMKN" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>
            </p>
            </c:if>
            <c:if test="${!edit}">
    <table>
        <tr>
            <td>Tarikh Sidang :</td>
            <td><s:format value="${actionBean.tarikhSidang}" formatPattern="dd/MM/yyyy"/></td>
        </tr>
        <tr>
            <td>Bil Mesyuarat :</td>
            <td>${actionBean.bilMesy}</td>
        </tr>
       
    </table>
        <br/>
            </c:if>
            </fieldset>
                </div>
</s:form>
