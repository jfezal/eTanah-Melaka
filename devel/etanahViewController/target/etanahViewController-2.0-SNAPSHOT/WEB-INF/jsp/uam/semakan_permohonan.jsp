<%-- 
    Document   : semakanPermohonan
    Created on : Oct 11, 2011, 2:57:48 PM
    Author     : amir.muhaimin
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    function validateForm(f) {

        if(f.elements['idPengguna'].value == '') {
            alert('Sila masukkan Id Pengguna.');
            f.elements['idPengguna'].focus();
            return false;
        }
        else return true;
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.uam.SemakanPermohonan" name="userForm" id="semakanPermohonan">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Semakan Permohonan</legend>
            <font size="1" color="Red"><em>Sila masukkan Id Pengguna</em></font>
            <p>
                <label><font color="red">*</font>Id Pengguna :</label>
                    <s:text name="idPengguna"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <c:if test="${simpan}">
                    <s:submit name="search" value="Cari" class="btn" onclick="return validateForm(this.form)"/>
                </c:if>
                <%--<c:if test="${simpan}">--%>
                    <s:submit name="kembali" value="Kembali" class="btn"/>
                <%--</c:if>--%>
            </p>
        </fieldset>
    </div>
    <p></p>
    <c:if test="${actionBean.flag}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Senarai Carian</legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.listPengguna}" id="row" >
                        <display:column title="Bil."><div align="center">${row_rowNum}.</div></display:column>
                        <display:column title="Id Pengguna" property="idPengguna"/>
                        <display:column title="Nama" property="nama"/>
                        <display:column title="Peringkat" property="status.nama"/>
                    </display:table>
                </div>
            </fieldset>
        </div>
    </c:if>
</s:form>