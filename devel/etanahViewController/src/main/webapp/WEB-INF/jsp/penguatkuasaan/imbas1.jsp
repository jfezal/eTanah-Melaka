<%-- 
    Document   : imbas1
    Created on : Aug 11, 2010, 9:10:00 AM
    Author     : Programmer
--%>


<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">

    function successRedirection() {
        var url = '${pageContext.request.contextPath}/penguatkuasaan/kemasukan_aduan?reload&permohonan.idPermohonan=${idPermohonan}';
        $.ajax({
            type:"GET",
            url : url,
            success : function(data) {
               $('#folder_div',opener.document).html(data);
               self.close();
            }
        });
    }

    function confirmCancel(){
        if(confirm('Adakah anda pasti?')){
            self.close();
        }
    }

</script>

<s:form beanclass="etanah.view.dokumen.ViewDocumentAction">

<applet code="etanah.dokumen.scan.DocumentScanner"
        onload="javascript:focus();"
        archive="${pageContext.request.contextPath}/ScanApplet.jar, ${pageContext.request.contextPath}/JTwain.jar"
        codebase="${pageContext.request.contextPath}/"
        width="800" height="600">
            <param name="idDokumen" value="${actionBean.idDokumen}" />
            <!-- param name="isDialog" value="1" -->
            <%
            Cookie[] cookies = request.getCookies();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < cookies.length; i++) {
                sb.setLength(0);
                sb.append(cookies[i].getName());
                sb.append("=");
                sb.append(cookies[i].getValue());
                %>
                <param name="Cookie<%= i%>" value="<%= sb.toString()%>">
             <%
             }
             %>
    </applet>
</s:form>