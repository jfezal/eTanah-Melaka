<%-- 
    Document   : notis_gantian_upload
    Created on : Oct 8, 2010, 9:51:47 PM
    Author     : mdizzat.mashrom
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<%--<script type="text/javascript">
    function closeIt(){
        var idDasar = $("#dasar").val();
        var idhakmilik = $("#hakmilik").val();
        var kodStatus = $("#status").val();
        self.opener.reloadUpload(idDasar, idhakmilik, kodStatus);
        self.close();
    }
</script>--%>

<s:form beanclass="etanah.view.stripes.lelong.NotisGantianActionBean">
    <s:errors/>
    <s:messages/>
    <s:hidden name="idNotis"/>
    <s:hidden id="dasar" name="noDasar"/>
    <s:hidden id="hakmilik" name="idHakmilik"/>
    <s:hidden id="status" name="kodStatus"/>
    <fieldset class="aras1">
        <legend>Muat Naik</legend>
        <p>
            <label><%--idNotis : ${actionBean.idNotis}--%>&nbsp;</label>&nbsp;
            <s:file name="fileToBeUploaded"/>
            <%--<c:if test="${actionBean.fileToBeUploaded ne null}">
                ${actionBean.fileToBeUploaded.fileName}
            </c:if>--%>
        </p>
        <%--<p>
            idHakmilik  : ${actionBean.idHakmilik}<br>
            noDasar     : ${actionBean.noDasar}<br>
            kodStatus   : ${actionBean.kodStatus}<br>
        </p>--%>
        <br/>
        <p>
            <label>&nbsp;</label>&nbsp;
            <s:submit name="processUploadDoc" value="Simpan" class="btn"/>
            <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
        </p>
    </fieldset>
</s:form>
