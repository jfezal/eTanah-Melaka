<%-- 
    Document   : terima_pa_jupem
    Created on : Oct 25, 2010, 3:40:23 PM
    Author     : NageswaraRao
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.stripes.pembangunan.TerimaPaJupemActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="center">
                <table border="0" width="80%">
                    <c:if test="${edit}">
                        <tr><td align="right"><b>No Pelan Akui (PA):</b></td>
                            <td><font style="text-transform: uppercase"><s:text  name="noPelan"/></font></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td align="right"><b>No Pelan Akui (PA):</b></td>
                            <td><font style="text-transform:capitalize"> ${actionBean.noPelan}</font></td></tr>
                    </c:if>
                 </table><br/><br/>
                    <c:if test="${edit}">
                        <br>
                        <p align="center">
                            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </p>
                    </c:if>
            </div>
        </fieldset>
    </div>
</s:form>
