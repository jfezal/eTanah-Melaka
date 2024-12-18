<%-- 
    Document   : pergerakan_perserahan
    Created on : Oct 15, 2012, 12:33:13 PM
    Author     : ei
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">

    function test(f) {
        $(f).clearForm();
    }

</script>
<s:form beanclass="etanah.view.daftar.pergerakanPerserahan">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Pergerakan Perserahan</legend>
            <font size="1" color="Red">Sila masukkan ID Permohonan / ID Perserahan untuk carian.</font>
            <br><p>
                <label><font color="red">*</font>ID Permohonan / ID Perserahan :</label>
                <s:text name="id_mohon" onkeyup="this.value=this.value.toUpperCase();"/>
                <s:submit name="trace" id="trace" value="Cari" class="btn"/>
            </p>
            <p>
                <label>Peringkat :</label>
                <s:text name="stage" readonly="true"/>
            </p>
<%--            <p>
                <label>Tindakan :</label>
                <s:text name="tindakan"/>
            </p>
            <p>
                <label>Task ID :</label>
                <s:text name="task_id"/>
            </p>
            <p>
                <label>Task Number :</label>
                <s:text name="task_number"/>
            </p>--%>
            <p>
                <label>Kumpulan Pengguna :</label>
                <s:text name="participant" readonly="true"/>
            </p>
            <p>
                <label>Diproses Oleh :</label>
                <s:text name="acquired_by" readonly="true"/>
            </p>
<%--            <p>
                <label>Task State :</label>
                <s:text name="task_state"/>
            </p>--%>
        </fieldset>
    </div>
</s:form>
