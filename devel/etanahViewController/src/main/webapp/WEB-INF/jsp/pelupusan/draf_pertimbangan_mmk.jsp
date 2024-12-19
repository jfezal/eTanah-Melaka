<%-- 
    Document   : draf_pertimbangan_mmk
    Created on : Apr 20, 2010, 5:57:15 PM
    Author     : sitifariza.hanim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>

<s:form beanclass="etanah.view.stripes.pelupusan.DrafPertimbanganMMKActionBean">

 <s:messages/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Draf Pertimbangan MMK</legend>
            <p>
                <label>Huraian Pentadbir Tanah :</label>
                <s:textarea name="DrafPertimbanganMMKActionBean.huraianPTD" rows="5" cols="70"/>
            </p>
            <p>
                <label>Syor Pentadbir Tanah :</label>
                <s:textarea name="DrafPertimbanganMMKActionBean.syorPTD" rows="5" cols="70"/>
            </p>
            <p>
                <label>Huraian Pengarah Tanah Dan Galian :</label>
                <s:textarea name="DrafPertimbanganMMKActionBean.huraianPTG" rows="5" cols="70"/>
            </p>
            <p>
                <label>Syor Pengarah Tanah Dan Galian :</label>
                <s:textarea name="DrafPertimbanganMMKActionBean.syorPTG" rows="5" cols="70"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:button name="simpanPindahMilik" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
        </fieldset >
    </div>

</s:form>


