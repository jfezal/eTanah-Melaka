<%-- 
    Document   : 16H_upload_doc
    Created on : Aug 5, 2010, 10:12:50 AM
    Author     : mdizzat.mashrom
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    function simpanPihak(event, f){

        var id = ${id};
        $('#viewReport'+id,opener.document).show();
        var url = f.action + '?' + event;
        $.post(url,
        function(){
            self.close();
        },'html');
        setTimeout("self.close()",1000);
    }
</script>

<s:form beanclass="etanah.view.stripes.pembangunan.MaklumatPenghantaran16HPPCBActionBean">
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
            <s:file id="file" name="fileToBeUploaded"/>
            <%--<c:if test="${actionBean.fileToBeUploaded ne null}">
                ${actionBean.fileToBeUploaded.fileName}
            </c:if>--%>
        </p>
        <%--<p>
            idHakmilik  : ${actionBean.idHakmilik}<br>
            noDasar     : ${actionBean.noDasar}<br>
            kodStatus   : ${actionBean.kodStatus}<br>
        </p> onclick="simpanPihak(this.name, this.form);"--%>
        <br/>
        <p>
            <label>&nbsp;</label>&nbsp;
            <s:submit name="processUploadDoc" value="Simpan" class="btn"/>
            <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
        </p>
    </fieldset>
</s:form>