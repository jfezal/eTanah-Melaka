<%-- 
    Document   : upload_dok_pembida_jlb
    Created on : Jun 12, 2013, 11:58:25 AM
    Author     : nur.aqilah
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
<script type="text/javascript">
    function simpanPihak(){
        var id = ${id};
        $('#viewReport' + id, opener.document).show();
    }
            
</script>

<s:form beanclass="etanah.view.stripes.lelong.UtilitiSenaraiPermohonanLelonganPembidaJLBActionBean">
    <s:errors/>
    <s:messages/>
    <s:hidden name="idPembida"/>
    <s:hidden name="idPermohonan" value="${actionBean.idPermohonan}"/>
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
            <c:if test="${actionBean.caraLelong eq 'A'}">
                <s:submit name="processUploadDoc" value="Simpan" class="btn" onclick="simpanPihak(this.name, this.form);"/>
            </c:if>
            <c:if test="${actionBean.caraLelong eq 'B'}">
                <s:submit name="processUploadDocB" value="Simpan" class="btn" onclick="simpanPihak(this.name, this.form);"/>
            </c:if>
            <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
        </p>
    </fieldset>
</s:form>