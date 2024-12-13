<%-- 
    Document   : senarai_tugasan
    Created on : Dec 10, 2009, 1:36:28 PM
    Author     : zadhirul.farihim
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body>

        <s:form beanclass="etanah.view.pelupusan.PelupusanActionBean">
            <div class="subtitle">
                <p>SENARAI TUGASAN</p>
                <fieldset class="aras1">
                    <legend>
                        Carian
                    </legend>
                    <p>
                        <label for="Unit">Unit :</label>
                        <s:select name="">
                            <s:option value="">-- Sila Pilih --</s:option>
                        </s:select>
                    </p>
                    <p>
                        <label>Kategori Permohonan :</label>
                        <s:text name=""/>
                    </p>
                    <p>
                        <label>ID Permohonan :</label>
                        <s:text name=""/>
                    </p>
                    <p>
                        <label>ID Perserahan :</label>
                        <s:text name=""/>
                    </p>
                    <p>
                        <label for="Nama">Nama Pemohon :</label>
                        <s:text name=""/>
                    </p>
                    <p>
                        <label>Tarikh Dari :</label>
                        <s:text class="datepicker" name=""/>
                    </p>
                    <p>
                        <label>Tarikh Hingga :</label>
                        <s:text class="datepicker" name=""/>
                    </p>
                    <p>
                        <label>&nbsp;</label><s:submit name="" value="Cari" class="btn"/>
                        <s:reset name="" value="Isi Semula" class="btn"/>
                    </p>

                </fieldset>
            </div>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Senarai Permohonan
                    </legend>
                    <p>
                        <label>&nbsp;</label>
                        <display:table name="" id="line" class="tablecloth">
                            <display:column title="No"/>
                            <display:column title="Unit/Kategori"/>
                            <display:column title="ID Berkaitan"/>
                            <display:column title="Tindakan"/>
                            <display:column title="Tarikh Diterima"/>
                            <display:column title="Keutamaan"/>
                            <display:column title="Sasaran (Hari)"/>
                            <display:column title="Bil Hari"/>
                        </display:table>
                    </p>
                </fieldset>
                <p>
                    <label>&nbsp;</label><s:submit name="" value="Keluar" class="btn"/>
                </p>
            </div>
        </s:form>

    </body>
</html>
