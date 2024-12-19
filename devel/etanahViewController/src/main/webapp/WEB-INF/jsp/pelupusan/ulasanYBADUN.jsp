<%--
    Document   : ulasan_YBADUN
    Created on : May 19, 2010, 4:36:02 PM
    Author     : nurul.izza
    Modified by: Rohan
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<%--<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>--%>

<s:form beanclass="etanah.view.stripes.pelupusan.Ulasan_YB_Adun">
  <s:messages/>
  <s:errors/>
  <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Ulasan YB ADUN
            </legend>
            <div class="content">
                <p><label>Kawasan :</label>
                    <s:text name="permohonanRujukanLuar.lokasiAgensi" size="60" id="nama" onkeyup="this.value=this.value.toUpperCase();"/>
                   
                </p>
                <p><label>Tarikh :</label>
                    <s:text name="permohonanRujukanLuar.tarikhRujukan" size="12" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy"/>
                </p>
                <p><label>Surat Bilangan :</label>
                    <s:text name="permohonanRujukanLuar.noRujukan" />
                </p>
                <p><label>Ulasan YB ADUN :</label>
                    <s:textarea name="permohonanRujukanLuar.ulasan" cols="58" rows="10" />
                </p>
                <br><br>
                <div align="center">
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </div>
            </div>

            <br>
        </fieldset>
    </div>
</s:form>


