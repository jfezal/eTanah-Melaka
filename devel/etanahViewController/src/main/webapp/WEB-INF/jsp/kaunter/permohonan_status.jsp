<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/styles/styles.css" />
<title>Status Permohonan</title>

</head>
<body>

<stripes:messages />
<stripes:errors />

<stripes:form action="/kaunter/kesinambungan" >

<fieldset class="aras1">

    <legend>Status Permohonan</legend>

    <p><label for="idPermohonan">Id Permohonan/ID Perserahan :</label>
        ${actionBean.permohonan.idPermohonan}
    </p>
    
    <p><label for="kodUrusan">Urusan :</label>
        ${actionBean.permohonan.kodUrusan.kod} -
        ${actionBean.permohonan.kodUrusan.nama}
    </p>

    <p><label for="tarikhDaftar">Tarikh Daftar :</label>
        <stripes:text name="permohonan.infoAudit.tarikhMasuk" formatType="datetime" formatPattern="dd/MM/yyyy hh:mm aa" 
          disabled="true"/>
    </p>

    <p><label for="penyerah">Penyerah :</label>
        ${actionBean.permohonan.penyerahNama}
    </p>

    <p><label for="keputusan">Keputusan :</label>
        ${actionBean.permohonan.keputusan.kod == null ? "-" : actionBean.permohonan.keputusan.nama}
    </p>

    <p><label for="status">Status :</label>
        <stripes:select name="permohonan.status" disabled="true">
            <stripes:option value="TS" >TUNGGU SBLM</stripes:option>
            <stripes:option value="TA">TUNGGU AMBIL</stripes:option>
            <stripes:option value="AK">AKTIF</stripes:option>
            <stripes:option value="GB">GANTUNG SBB BAYARAN</stripes:option>
            <stripes:option value="TP">TUNGGU PEMOHON</stripes:option>
            <stripes:option value="SL">SELESAI</stripes:option>
        </stripes:select>
    </p>
       
</fieldset>

Proses permohonan:

<display:table name="${actionBean.permohonan.senaraiFasa}" class="tablecloth">

    <display:column property="infoAudit.tarikhMasuk" title="Tarikh" sortable="true" format="{0,date,dd/MM/yyyy hh:mm aa}"/>
    
    <display:column property="infoAudit.dimasukOleh.nama" title="Diproses Oleh" />
    
    <display:column property="keputusan.nama" title="Keputusan" />
    
    <display:column property="ulasan" title="Ulasan" />
    
</display:table>

</stripes:form>

</body>
</html>