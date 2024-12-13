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
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">

</script>

<s:useActionBean beanclass="etanah.view.stripes.pengambilan.aduan.RekodKeputusanMmknActionBean" var="penggunaperanan"/>

<s:form beanclass="etanah.view.stripes.pengambilan.aduan.RekodKeputusanMmknActionBean">

    <fieldset class="aras1">

        <legend>
            Keputusan MMKN
        </legend> 

        <br><br>

        <p>
            <label>Tarikh Bersidang : </label><s:text name="tarikhRundingan" id="datepicker" class="datepicker"/>&nbsp;
        </p>
        <p>
            <label>Tempat Bersidang : </label><s:text name="" id="" class=""/>&nbsp;
        </p>
        <!--        <p>
                    <label>Waktu Rundingan : </label><s:text name="tarikhRundingan" id="timepicker" class="timepicker"/>&nbsp;
                </p>-->
        <p>
            <label><font color="red">*</font>Keputusan :</label>
            <s:radio name="" value="Lulus"/>&nbsp;Lulus
            <s:radio name="" value="Ditolak"/>&nbsp;Ditolak
        </p>
        <p>
            <label>Catatan :</label>
            <s:textarea id = "" name="" onblur="" rows="10" cols="50"/>&nbsp;
        </p>

        <br><br>

        <center>
            <s:button  name="simpan" value="Simpan" class="btn" onclick="doAgih(this.name, this.form);"/>&nbsp;
            <s:button  name="muatnaik" value="Muatnaik" class="btn" onclick="doAgih(this.name, this.form);"/>&nbsp;
        </center>

        <br>

    </fieldset>

    <br>

    <fieldset class="aras1">

        <legend>
            Senarai Dokumen
        </legend>

        <!--        <p align="center">Tiada rekod skrin</p>-->

        <div align="center">
            <table class="tablecloth">
                <tr>
                    <th>Bilangan</th>
                    <th>Id Mohon</th>
                    <th>Nama Dokumen</th>
                    <th>Tarikh</th>
                    <th>Muat Turun</th>
                </tr>
                <tr>
                    <td>1</td>
                    <td>0401ACQ2020000049</td>
                    <td>Surat Aduan</td>
                    <td><s:text name="tarikh" id="datepicker" class="datepicker"/>&nbsp;</td>
                    <td><img src="${pageContext.request.contextPath}/pub/images/icons/active_document.png"
                             onclick="doViewReport('${row.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                             onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen ${row.dokumen.kodDokumen.nama}"/></td>
                </tr>
            </table>
        </div>
        <br><br>
    </fieldset>

</s:form>