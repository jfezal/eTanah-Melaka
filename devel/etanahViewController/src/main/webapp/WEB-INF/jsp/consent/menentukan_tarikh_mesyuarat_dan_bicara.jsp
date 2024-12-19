<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>


<style type="text/css">
    input.error { background-color: yellow; }
</style>
<s:form beanclass="etanah.view.stripes.common.MaklumatPermohonanActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Permohonan</legend>
            <p>
                <label for="ID Permohonan">ID Permohonan / Perserahan :</label>
                ${actionBean.permohonan.idPermohonan}&nbsp;
            </p>
            <p>
                <label for="Permohonan">Urusan :</label>
                ${actionBean.permohonan.kodUrusan.nama}&nbsp;
            </p>
        </fieldset >
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat</legend>
            <p>
                <label>Tarikh :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Bilangan Mesyuarat :</label>
                <s:text name="" id="datepicker" class="datepicker"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:button name="simpanMesyuarat" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'status_div')"/>
            </p>
        </fieldset >
    </div>
</s:form>
