<%-- 
    Document   : draf_pertimbangan_ptg
    Created on : May 17, 2010, 4:44:23 PM
    Author     : sitifariza.hanim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>

<s:form beanclass="etanah.view.stripes.pelupusan.DrafPertimbanganPTGActionBean" name="drafptg">

 <s:messages/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Draf Pertimbangan PTG</legend>
            <p>
                <label>Huraian Pentadbir Tanah :</label>
                <s:textarea name="kandungan" rows="5" cols="70"/>
            </p>
            <p>
                <label>Syor Pentadbir Tanah :</label>
                <s:textarea name="kandungan1" rows="5" cols="70"/>
            </p>
            <p>
                <label>Huraian Pengarah Tanah Dan Galian :</label>
                <s:textarea name="kandungan2" rows="5" cols="70"/>
            </p>
            <p>
                <label>Syor Pengarah Tanah Dan Galian :</label>
                <s:textarea name="kandungan3" rows="5" cols="70"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:button name="simpanDrafPTG" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
        </fieldset >
    </div>

</s:form>



