<%-- 
    Document   : draf_pembebasan_upah_ukur
    Created on : May 11, 2010, 10:50:49 AM
    Author     : sitifariza.hanim
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>

<s:form beanclass="etanah.view.stripes.pelupusan.DrafPembebasanUpahUkurActionBean">

    <s:messages/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Draf Pembebasan Upah Ukur</legend>
            <p>
                <label>Keluasan Tanah :</label>
                ${actionBean.keluasanTanah}
            </p>
            <p>
                <label>Lokasi :</label>
                ${actionBean.tempat}
            </p>
            <p>
                <label>Mukim/Pekan/Bandar :</label>
                ${actionBean.mukimBandarPekan}
            </p>
            <p>
                <label>Daerah :</label>
                ${actionBean.daerah}
            </p>
            <p>
                <label>Tujuan Perizaban :</label>
                <s:textarea name="kegunaanTanah" id="kegunaanTanah" rows="5" cols="50"/>
            </p>
            <p>
                <label>Tarikh :</label>
                ${actionBean.tarikh}
            </p>
            <br>
            <p align="center">
                <s:button name="simpanPembebasanUpahUkur" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
        </fieldset >
    </div>

</s:form>




