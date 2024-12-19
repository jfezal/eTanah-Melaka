<%-- 
    Document   : semakan_laporan_tanah
    Created on : 02 Disember 2009, 11:08:57 AM
    Author     : zadhirul.farihim
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.pelupusan.PelupusanActionBean">
    <p>LAPORAN TANAH</p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pemohon
            </legend>
            <p>
                <label> Status Semasa : </label>
                <s:text name=""/>
            </p>
            <p>
                <label> Urusan : </label>
                <s:text name=""/>
            </p>

        </fieldset>

    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Agihan Tugas
            </legend>
            <p>
                <label>Hantar Kepada :</label>
                <s:text name=""/>
            </p>

        </fieldset>
        <p>
            <label>&nbsp;</label><s:submit name="" value="Hantar" class="btn"/>
        </p>

    </div>



</s:form>