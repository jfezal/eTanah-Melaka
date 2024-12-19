<%-- 
    Document   : penyelarasan
    Created on : Jan 20, 2010, 10:14:32 AM
    Author     : farah.shafilla
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.penguatkuasaan.MesyuaratActionBean">
    <s:messages />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Keputusan Mesyuarat
            </legend>
            <div class="content">

                <p>
                    <label>Tarikh Mesyuarat :</label>
                    <fmt:formatDate value="${actionBean.permohonanRujukanLuar.tarikhSidang}" pattern="dd/MM/yyyy" />&nbsp;
                </p>
                <p>
                    <label>Masa :</label>
                    <fmt:formatDate value="${actionBean.permohonanRujukanLuar.tarikhSidang}" pattern="hh:mm:ss aaa" />&nbsp;
                </p>
                <p>
                    <label>Keputusan Mesyuarat :</label>
                    ${actionBean.permohonanRujukanLuar.catatan}&nbsp;

                </p>
            </div>
        </fieldset>
    </div>
</s:form>