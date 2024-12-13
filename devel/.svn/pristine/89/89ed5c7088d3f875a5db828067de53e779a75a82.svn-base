<%-- 
    Document   : jana_dokumen
    Created on : Jul 14, 2010, 10:25:33 AM
    Author     : fikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    function popupParam(f){
        var form = $(f).formSerialize();
        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+form, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
</script>

<s:form beanclass="etanah.view.utility.JanaDokumenAction">
    <fieldset class="aras1">
    <legend>Jana Semula Dokumen</legend>
        <p>
            <label>Nama Report :</label>
            <s:text name="reportName"/>
        </p>
        <p>
            <label>ID Mohon :</label>
            <s:text name="report_p_id_mohon"/>
        </p>
        <p>
            <label>ID Hakmilik :</label>
            <s:text name="report_p_id_hakmilik"/>
        </p>
        <br/>
        <p>
            <label>&nbsp;</label>
            <s:button name="" id="simpan" value="Papar" class="btn" onclick="popupParam(this.form);"/>&nbsp;
        </p>
    </fieldset>
</s:form>