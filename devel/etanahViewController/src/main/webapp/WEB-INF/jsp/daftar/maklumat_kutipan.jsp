<%-- 
    Document   : maklumatKutipan
    Created on : 01 Oktober 2009, 11:13:30 AM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<table width="100%" bgcolor="#00FFFF">
    <tr>
        <td width="50%" height="20" ><div style="float:left;" class="formtitle"></div></td>
        <td width="50%"height="20" ><div style="float:right;" class="formtitle"></div></td>
    </tr>
</table>
<form:form beanclass="etanah.view.stripes.maklumatKutipanActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                ID Permohonan
            </legend>

            <p>
                <label>ID Permohonan :</label>

                ${actionBean.permohonan.idPermohonan}

            </p>

        </fieldset>

    </div>

    <p><label>&nbsp;</label>
        <s:submit name="agihKPT" value="Keluar" class="btn"/>
    </p>

</td>
</tr>
</table>
</form:form>

