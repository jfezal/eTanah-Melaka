<%-- 
    Document   : arahan_endorsan
    Created on : Feb 24, 2010, 9:29:43 AM
    Author     : farah.shafilla
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<s:form beanclass="etanah.view.penguatkuasaan.PenguatkuasaanActionBean">
<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Arahan Endorsan Dokumen Hakmilik DHDe
        </legend>
            <div class="content" align="center">
                <table>
                    <tr>
                        <td class="rowlabel1"> Arahan :</td>
                        <td>
                                <s:textarea name="select" value="" rows="5" cols="50" />
                        </td>
                        </tr>
                </table>
            </div>
    </fieldset>
</div>
</s:form>