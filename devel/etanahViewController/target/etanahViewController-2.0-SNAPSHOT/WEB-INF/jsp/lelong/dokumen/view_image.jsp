<%-- 
    Document   : view_image
    Created on : Nov 9, 2010, 2:24:27 PM
    Author     : fikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<s:form beanclass="etanah.view.dokumen.ViewImageAction">

    <br/>
        <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
    <br/>

    <c:choose>
        <c:when test="${fn:length(actionBean.senaraiKandungan) > 0}">
            <c:forEach items="${actionBean.senaraiKandungan}" var="item" varStatus="loop">
                <font color="black" size="3">${loop.count} . ${item.dokumen.kodDokumen.nama} </font><br/>
                <img src="${pageContext.request.contextPath}/dokumen/view/${item.dokumen.idDokumen}" title="${item.dokumen.tajuk}"/>
                <br/>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <font color="red">Tiada Imej Imbasan.</font>
        </c:otherwise>
    </c:choose>
    
</s:form>
