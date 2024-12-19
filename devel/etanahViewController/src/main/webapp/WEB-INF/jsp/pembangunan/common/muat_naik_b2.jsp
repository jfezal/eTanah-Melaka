<%-- 
    Document   : muat_naik
    Created on : Feb 12, 2010, 8:19:28 AM
    Author     : fikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<s:errors/>
<s:messages/>
<div id="error"/>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form beanclass="etanah.view.stripes.pembangunan.pelan.MuatNaikPelanB2ActionBean">
                              <s:hidden name="nopt" value="${nopt}"/>
<s:hidden name="idPlot" value="${idPlot}"/>
            <s:hidden name="luas" value="${luas}"/>
                <s:hidden name="unitukur" value="${unitukur}"/>

    
    
    
    <fieldset class="aras1">
        <legend>Muat Naik</legend>
        <p>
            <label>&nbsp;</label>&nbsp;
            <s:file name="file"/>
            <%--<input type="file" name="filedokumentobeupload"/>--%>
            <c:if test="${actionBean.file ne null}">
                Fail yang dimuat naik :${actionBean.file.fileName}
            </c:if>
        </p>
        <br/>
        <p>
            <label>&nbsp;</label>&nbsp;
            <s:submit name="simpan" value="Simpan" class="btn"/>
            <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
        </p>
    </fieldset>
</s:form>
