<%--
    Document   : kemasukan_maklumat_ansuran
    Created on : Jan 18, 2010, 11:58:28 AM
    Author     : zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.pelupusan.PelupusanActionBean">
    <p>KEMASUKAN MAKLUMAT ANSURAN</p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Kemasukan Ansuran
            </legend>
            <p>
                <label>Bilangan Bayaran :</label>
                &nbsp;
            </p>
            <p>
                <label>Tarikh Bayaran :</label>
                <s:text name=" " class="datepicker"/>
            </p>
            <p>
                <label>&nbsp;</label>
                Bayaran mesti dilakukan sebelum 31 Mei setiap tahun!
            </p>
            <p>
                <label>Jumlah Yang Perlu Dibayar(RM) :</label>
                &nbsp;
            </p>
            <p>
                <label>Bayaran Premium(RM) :</label>
                &nbsp;
            </p>
            <p>
                <label>Bayaran Nominal(RM) :</label>
                &nbsp;
            </p>
            <p>
                <label>&nbsp;</label>
                <s:submit name="" value="Tambah" class="btn"/>
            </p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Jadual Bayaran
            </legend>
            <p>
                <label>&nbsp;</label>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="Pilih"/>
                    <display:column title="Bil. Bayaran"/>
                    <display:column title="Bayaran (RM)"/>
            </display:table>
            </p>
        </fieldset>
    </div>
    <p>
        <label>&nbsp;</label>
        <s:submit name="" value="Hantar" class="btn"/>
    </p>
</s:form>