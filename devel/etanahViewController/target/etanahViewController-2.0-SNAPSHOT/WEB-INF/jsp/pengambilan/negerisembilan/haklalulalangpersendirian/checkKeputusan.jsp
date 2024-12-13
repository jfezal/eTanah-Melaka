<%-- 
    Document   : checkKeputusan
    Created on : Feb 29, 2012, 11:50:25 AM
    Author     : massita
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:form beanclass="etanah.view.stripes.pengambilan.CheckKeputusanActionBean">
    <s:messages/>
    <s:errors/>
    <c:if test="${form1}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Notifikasi</legend>
                <div class="content" align="left">
                    <table border="0" width="80%" >
                        <td>
                            <font color="black">
                                keputusan adalah ${actionBean.fasaPermohonan.keputusan.nama}
                            </font>
                        </td>
                    </table>
                </div>
            </fieldset>
        </div>

        <div align="right">
        </div>
    </c:if>
</s:form>