<%--
    Document   : upload_file
    Created on : May 9, 2011, 12:28:56 PM
    Author     : Rajesh
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

    $(document).ready(function() {
            self.opener.refreshNotis('<c:out value="${actionBean.idHakmilik}"/>','<c:out value="${actionBean.showPP}"/>','<c:out value="${actionBean.showHP}"/>','<c:out value="${actionBean.isMahkamah}"/>');
       <%-- var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_notis_borang?reload';
        $.ajax({
            type:"GET",
            url : url,
            success : function(data) {
               $('#folder_div',opener.document).html(data);
               alert('hi');
            }
        });--%>
    });
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.PenerimaanNotisBorangN9ActionBean">
    <s:errors/>
    <s:messages/>
    <%--<s:hidden name="idNotis"/>--%>
    <s:hidden id="dasar" name="noDasar"/>
    <s:hidden name="idPihak"/>
    <s:hidden name="idHakmilik"/>
    <s:hidden id="status" name="kodStatus"/>
    <s:hidden name="showPP" value="${actionBean.showPP}" id="showPP"/>
    <s:hidden name="showHP" value="${actionBean.showHP}" id="showHP"/>
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
            <c:if test="${actionBean.isPemohonPihak eq 'true'}">
                <s:submit name="processUploadDocPemohon" value="Simpan" class="btn" />
            </c:if>
            <c:if test="${actionBean.isMahkamah eq 'true'}">
                <s:submit name="processUploadDocMahkamah" value="Simpan" class="btn" />
            </c:if>
            <c:if test="${actionBean.isPemohonPihak ne 'true' && actionBean.isMahkamah ne 'true'}">
                <s:submit name="processUploadDoc" value="Simpan" class="btn" />
            </c:if>
            <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
        </p>
    </fieldset>
</s:form>
