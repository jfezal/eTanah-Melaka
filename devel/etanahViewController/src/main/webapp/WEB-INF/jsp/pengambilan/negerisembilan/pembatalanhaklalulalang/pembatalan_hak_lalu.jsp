<%-- 
    Document   : pembatalan_hak_lalu
    Created on : Oct 26, 2010, 3:26:44 PM
    Author     : massita
--%>


<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<s:form beanclass="etanah.view.stripes.pengambilan.PembatalanHakLaluLalang8ActionBean">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend> </legend>
            <display:table name="" class="tablecloth" id="row" style="width:100%">

              <display:column title="No" property=""/>
              <display:column title="Hakmilik Tanah" property=""/>
               <display:column title="Laporan Pelanggaran Syarat">
                        <div align="center">
                            <s:text name="laporan" />
                        </div>
              </display:column>
            </display:table><br />
            <p align="center"><s:button name="" id="" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/></p>

        </fieldset >

    </div>
</s:form>
