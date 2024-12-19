<%-- 
    Document   : maklumat_permohonan_permit
    Created on : Apr 23, 2010, 12:07:15 PM
    Author     : sitifariza.hanim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form  beanclass="etanah.view.stripes.pelupusan.MaklumatPermohonanPermitActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Permohonan</legend>
            <p>
                <label for="Permohonan">Permohonan :</label>
               <%-- ${actionBean.permohonan.kodUrusan.nama}&nbsp;--%>
            </p>
            <p>
                <label for="ID Permohonan">ID Permohonan :</label>
                <%--${actionBean.permohonan.idPermohonan}&nbsp;--%>
            </p>
        </fieldset >
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Penyerah</legend>
            <p>
                <label>Nama :</label>
                <%--${actionBean.permohonan.penyerahNama}&nbsp;--%>
            </p>
            <p>
                <label>Nombor Rujukan Penyerah :</label>
                <%--<c:if test="${actionBean.permohonan.penyerahNoRujukan ne null}"> ${actionBean.permohonan.penyerahNoRujukan}&nbsp; </c:if>
                <c:if test="${actionBean.permohonan.penyerahNoRujukan eq null}"> Tiada Data </c:if>--%>
            </p>

            <p>
                <label>Alamat :</label>
                <%--${actionBean.permohonan.penyerahAlamat1}&nbsp;--%>
            </p>
            <p>
                <label> &nbsp;</label>
               <%-- ${actionBean.permohonan.penyerahAlamat2}&nbsp;--%>
            </p>
            <p>
                <label> &nbsp;</label>
                <%--${actionBean.permohonan.penyerahAlamat3}&nbsp;--%>
            </p>
            <p>
                <label> &nbsp;</label>
              <%--  ${actionBean.permohonan.penyerahAlamat4}&nbsp;--%>
            </p>
            <p>
                <label>Poskod :</label>
                <%--${actionBean.permohonan.penyerahPoskod}&nbsp;--%>
            </p>
            <p>
                <label>Negeri :</label>
                <%--${actionBean.permohonan.penyerahNegeri.nama}&nbsp;--%>
            </p>
        </fieldset >
    </div>
</s:form>

