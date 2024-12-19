<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<%--<page:applyDecorator name="default" title="Ralat Sistem">--%>
        <div class="errors">
        <p>Maaf, terdapat gangguan pada sistem. Sila hubungi IT Helpdesk untuk tindakan selanjutnya.<br/>
        <%= new java.util.Date() %> <%= request.getServerName() %> - ${mesej}
        </p>
        </div>
<!--
        ${exception_msg}
-->
<%--</page:applyDecorator>--%>
