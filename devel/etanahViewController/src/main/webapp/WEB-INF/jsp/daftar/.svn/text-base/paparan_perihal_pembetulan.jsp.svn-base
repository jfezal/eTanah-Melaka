<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252" language="java"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>


<style type="text/css">
    input.error { background-color: yellow; }
</style>

<form:form beanclass="etanah.view.stripes.pendaftaran.PembetulanActionBean">
    <s:hidden name="permohonan.idPermohonan"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Permohonan</legend>

            <p>
                <label for="idPermohonan">ID Permohonan :</label>
                ${actionBean.permohonan.idPermohonan}
            </p>
            <p>
                <label for="urusan">Urusan :</label>
                BETUL : Pembetulan di bawah Seksyen 380 KTN
            </p>
            <p>
                <label for="trhMasa">Tarikh dan Masa :</label>
                <fmt:formatDate value="${actionBean.permohonan.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm:ss"/>&nbsp;
            </p>
            <p>
                <label for="nota">Nota Pembetulan :</label>
                ${actionBean.permohonan.catatan}&nbsp;
            </p>            
        </fieldset>
    </div>
</form:form>
