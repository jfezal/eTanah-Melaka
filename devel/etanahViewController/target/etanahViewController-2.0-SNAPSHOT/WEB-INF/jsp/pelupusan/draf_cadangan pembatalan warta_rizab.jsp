<%-- 
    Document   : draf_cadangan pembatalan warta_rizab
    Created on : May 14, 2010, 2:55:06 PM
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

<s:form beanclass="etanah.view.stripes.pelupusan.DrafWartaTanahRizabActionBean">

 <s:messages/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Draf Cadangan Pembatalan Rizab Tanah</legend>
            <p>
                <label>No.Warta Terdahulu :</label>
                <s:text name="noWarta" size="50"/>
            </p>
            <p>
                <label>Tarikh  :</label>
                <s:text name="tarikhAsal" class ="datepicker"/>
            </p>
            <p>
                <label>Tujuan Rizab :</label>
                <s:text name="tujuanRizab" size="50"/>
            </p>
            <p>
                <label>Jawatan :</label>
                <s:text name="jawatan" size="50"/>
            </p>
            <p>
                <label>Daerah :</label>
            </p><br>
            <p>
                <label>Mukim/Pekan/Bandar :</label>
            </p><br>
            <p>
                <label>No.Pelan Warta :</label>
            </p><br>
            <p>
                <label>No.Lot :</label>
            </p><br>
            <p>
                <label>Keluasan Tanah :</label>
            </p><br>
            <p>
                <label>Masa :</label>
                <s:text name="masa"   size="10"/>am/pm
            </p>
            <p>
                <label>Tarikh :</label>
                <s:text name="tarikh"  class="datepicker" id="tarikh" size="20"/>
            </p><br>
            <p>
                <label>&nbsp;</label>
                <s:button name="simpanWartaRizabTanah" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
        </fieldset >
    </div>

</s:form>
