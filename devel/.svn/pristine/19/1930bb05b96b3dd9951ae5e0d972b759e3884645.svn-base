<%--
    Document   : reset_access.jsp
    Created on : Nov 24, 2010, 10:19:39 AM
    Author     : khairil
--%>
<%--<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>--%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/styles/styles.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
        <script type="text/javascript"
        src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/scripts/jquerycornerv1.99.js"></script>
<stripes:form beanclass="etanah.view.utility.ResetAccess">
    <stripes:errors/>
    <stripes:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Reset ID Pengguna</legend>

            <p><label>ID Pengguna :</label>
                <stripes:text name="IDPengguna"/>
            </p>

            <p><label>&nbsp;</label>
                <stripes:submit name="resetAccess" value="Reset" class="btn"/>

            </p>

        </fieldset>
    </div>

</stripes:form>