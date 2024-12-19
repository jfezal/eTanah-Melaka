<%-- 
    Document   : semakTandatangan
    Created on : Jul 17, 2020, 1:02:25 PM
    Author     : NURBAIZURA
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;" import="java.util.*"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript">



</script>

<s:form beanclass="etanah.view.stripes.pengambilan.aduan.SemakTandatanganActionBean">

    <fieldset class="aras1">

        <legend>
            Semak Tandatangan 
        </legend> 

        <p>
            <label>Ditandatangan oleh:</label>
            <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna">
                <s:option value="">-- Sila Pilih --</s:option>
                <s:option value="">-- PENOLONG PENTADBIR TANAH - FAIRUZ MD JANI --</s:option>
                <s:option value="">-- PENTADBIR TANAH - AHMAD ALI --</s:option>
                <%--<s:options-collection collection="${penggunaperanan.listPp}" value="idPengguna" label="nama" />--%>
            </s:select>

            <br><br>
        </p>
        <center>
            <s:button name="simpan" id="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form);"/>&nbsp;
        </center>

    </fieldset>
</s:form>