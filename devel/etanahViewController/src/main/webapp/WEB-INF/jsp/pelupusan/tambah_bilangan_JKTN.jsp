<%-- 
    Document   : tambah_bilangan_JKTN
    Created on : Dec 31, 2009, 9:22:35 AM
    Author     : zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.pelupusan.PelupusanActionBean">
    <div class="subtitle">
        <p>KEMASUKAN BILANGAN JKTN</p>
        <fieldset class="aras1">
            <legend>
                Tambah Bil JKTN
            </legend>
            <p>
                <label for=""> Bil. Mesyuarat Terakhir Setakat Ini : </label>
                45 Bertarikh 31/03/2007
            </p>
            <p>
                <label for=""> <font color="red">*</font>Bilangan Mesyuarat : </label>
                <s:text name=" "/>
            </p>
            <p>
                <label for=""> <font color="red">*</font>Tarikh : </label>
                <s:text class="datepicker" name=" "/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:reset name="" value="Isi Semula" class="btn"/>
                <s:submit name="" value="Tambah" class="btn"/>
            </p>
            <p>&nbsp;</p>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Bilangan Mesyuarat
            </legend>
            <p>
                <label>&nbsp;</label>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="No."/>
                    <display:column title="Bilangan Mesyuarat"/>
                    <display:column title="Tarikh"/>
                </display:table>
            </p>
            <p>&nbsp;</p>
        </fieldset>

        <p>
            <label>&nbsp;</label>
            <s:submit name="" value="Keluar" class="btn"/>
        </p>
    </div>

</s:form>



