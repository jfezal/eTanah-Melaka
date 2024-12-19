<%-- 
    Document   : suratLulusTolak
    Created on : 21/02/2011
    Author     : faidzal
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

    function addNewPemohon() {
        window.open("${pageContext.request.contextPath}/strata/maklumat_pemohon?pemohonPopup", "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=910,height=750");

    }
    function editPemohon(val) {
        window.open("${pageContext.request.contextPath}/strata/maklumat_pemohon?editpemohonPopup&idPihak=" + val, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=800,height=300");
    }

</script>
<s:form beanclass="etanah.view.strata.pageForViewJana">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <c:if test="${jana}">
                <legend><font size="2" color="Red">Sila klik butang 'Jana Dokumen' dan semak surat makluman pada tab dokumen.</font></legend>
                    </c:if>
                    <c:if test="${!jana}">
                <legend><font size="2" color="Red">Sila cetak pelan, klik butang 'Jana Dokumen' dan semak surat makluman pada tab dokumen.</font></legend>
                    </c:if>
            <p align="center"><br>
                <%--${actionBean.ayat}</p>--%>
            <p align="right">
                <%--<s:button name="jana" value="Jana" class="btn"/>--%>
            </p>


        </fieldset>
    </div>
    &nbsp;
    &nbsp;&nbsp;
</s:form>