<%-- 
    Document   : rekod_upload_doc
    Created on : 26 Julai 2010, 4:57:53 PM
    Author     : abu.mansur
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    function closeIt(){
        var dasar = $("#dasar").val();
        var hakmilik = $("#hakmilik").val();
        var status = $("#status").val();
        self.opener.doReloadPagePihak(hakmilik, dasar, status);
        self.close();
    }
</script>

<s:form beanclass="etanah.view.stripes.hasil.RekodPenghantaran2ActionBean">
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
            <c:if test="${actionBean.fileToBeUploaded ne null}">
                ${actionBean.fileToBeUploaded.fileName}
            </c:if>
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
            <s:button name="close" value="Tutup" onclick="closeIt();" class="btn"/>
        </p>
    </fieldset>
</s:form>
