<%-- 
    Document   : pdf_viewer
    Created on : Nov 23, 2011, 11:51:27 AM
    Author     : mansur
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.stripes.common.PdfViewer">
    <div>
        <object width="100%" height="830" data="${pageContext.request.contextPath}/reportGeneratorServlet?${actionBean.pdf_url}" type="application/pdf" standby="Dokumen sedang dimuat-turun. Sila tunggu sebentar.">
            <%--<param value="${pageContext.request.contextPath}/reportGeneratorServlet?${actionBean.pdf_url}" name="src"/>--%>
            <%--<param value="transparent" name="wmode"/>--%>
        </object>
        <div  style="background: transparent url(${pageContext.request.contextPath}/pub/images/logo.gif) no-repeat;position: absolute; top: 50%; left: 50%; width: 50%; height: 50%" >
            
        </div>
    </div>
</s:form>