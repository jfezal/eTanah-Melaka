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
<s:form beanclass="etanah.view.uam.NewPeranan">
    <s:hidden name="peranan.kod"/>
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Peranan</legend>
            <font size="1" color="Red">Sila masukkan peranan untuk membuat carian.</font>
            <p>
                <label><font color="red">*</font>Peranan :</label>
                <s:text name="peranan.nama"/>
                <c:if test="${simpan}">
                    <s:submit name="searchPeranan" id="search" value="Cari" class="btn"/>
                </c:if>
            </p>
            <p>
                <label><font color="red">*</font>Skrin :</label>
                <s:text name="peranan.defaultScreen"/>
            </p>
            <p>
                <label><font color="red">*</font>Aktif :</label>
                <c:set var="yes" value=""/>
                <c:set var="no" value=""/>                
                <c:if test="${actionBean.peranan.aktif eq 'Y'}">
                    <c:set var="yes" value="checked"/>
                </c:if>
                <c:if test="${actionBean.peranan.aktif eq 'T'}">
                    <c:set var="no" value="checked"/>
                </c:if>

                <input type="radio" name="peranan.aktif" value='Y' ${yes}/>Ya
                <input type="radio" name="peranan.aktif" value='T' ${no}/>Tidak
            </p>
        </fieldset>
        <p>
            <label>&nbsp;</label>

            <c:if test="${simpan}">
                <s:submit name="newPeranan" id="save" value="Simpan" class="btn"/>
            </c:if>
            <c:if test="${kemaskini}">
                <s:submit name="updatePeranan" id="save" value="Kemaskini" class="btn"/>
            </c:if>
            <s:submit name="showForm" value="Isi Semula" class="btn" onclick="test(this.form);"/>
        </p>
    </div>
</s:form>
