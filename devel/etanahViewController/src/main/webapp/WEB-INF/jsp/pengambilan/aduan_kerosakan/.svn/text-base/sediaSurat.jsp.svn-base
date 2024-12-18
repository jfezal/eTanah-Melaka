<%-- 
    Document   : sediaSurat
    Created on : Jul 17, 2020, 1:00:10 PM
    Author     : NURBAIZURA
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;" import="java.util.*"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

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
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pengambilan.aduan.SediaSuratActionBean">




    <fieldset class="aras1">

        <legend>
            Maklumat Rundingan
        </legend> 
        <font color="red"> Arahan : Sila kemaskini setiap borang E sekiranya maklumat tidak sama. Maklumat akan berubah pada setiap borang sekiranya kemaskini dilakukan</font>

        <br>
        <br><p>
            <label>Tarikh Rundingan : </label><s:text name="tarikhRundingan" id="datepicker" class="datepicker"/>&nbsp;
        </p>
        <p>
            <label>Waktu Rundingan : </label><s:text name="masaRundingan" id="timepicker" class="timepicker"/>&nbsp;
        </p>
        <p>
            <label>Lokasi Rundingan : </label><s:text name="lokasi" id=""/>&nbsp;
        </p>
        <p>
            <label>Catatan : </label><s:textarea name="keterangan" id=""/>&nbsp;
        </p>
        <br>
        <p>
            <label>&nbsp; </label> <s:button class="btn" value="Simpan" name="simpan" onclick="doSubmit(this.form,this.name,'page_div')"/>
        </p>  
    </fieldset>
    <fieldset class="aras1">
        <legend>
            Senarai Kehadiran Rundingan
        </legend> 
        <div align="center">
            <display:table class="tablecloth" style="width:90%;" name="${actionBean.notaSiasatanLengkap}" cellpadding="0" cellspacing="0" id="line">
                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                <display:column title="Nama" property="borangPerPB.nama"  media="html"/>

                <display:column title="Tarikh Rundingan">
                    <fmt:formatDate value="${line.tarikhBicara}" pattern="dd/MM/yyyy"/>
                </display:column>

                <display:column title="Masa Rundingan" property="masaBicara"   media="html"/>

                <display:column title="Lokasi Rundingan" property="tempatBicara"   media="html"/>
            </display:table>
        </div>
        <br><br>
    </fieldset>

    <fieldset class="aras1">
        <legend>
            Rekod Maklumat Rundingan
        </legend> 
        <div align="center">
            <display:table class="tablecloth" style="width:90%;" name="${actionBean.listBorangPerHakmilik}" cellpadding="0" cellspacing="0" id="linemohonpihak">
                <display:column title="Bil" sortable="true">${linemohonpihak_rowNum}</display:column>
                <display:column title="Hakmilik" property="hakmilikPermohonan.hakmilik.idHakmilik"  media="html"/>

                <display:column title="Dokumen" property="dokumen.tajuk"  media="html"/>

                <display:column title="Tandatangan Oleh" property="ditandatangan"   media="html"/>
            </display:table>
        </div>
        <br><br>
    </fieldset>

    <br><br>



</s:form>