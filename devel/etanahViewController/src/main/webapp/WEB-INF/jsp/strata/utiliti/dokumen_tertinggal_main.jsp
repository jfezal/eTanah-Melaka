<%-- 
    Document   : dokumen_tertinggal_main
    Created on : Oct 31, 2012, 11:03:26 AM
    Author     : ei
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    function doSearch(f,e){       
        var v = $('#idPermohonan').val();
        if(v == ''){
            alert('Sila masukkan ID Permohonan.');
            $('#idPermohonan').focus();
            return;
        }
        f.action = f.action + '?' + e;        
        f.submit();
    }
    function clearForm(f) {
        $('#idPermohonan').val('');
    }
</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />
        <title>Utiliti Muat Naik Dokumen</title>
    </head>
    <body>
    <div class="subtitile">
    <stripes:errors/>
    <stripes:messages/>
    <stripes:form beanclass="etanah.view.strata.utiliti.TambahDokumenTertinggalActionBean" id="tambah">
       <fieldset class="aras1">
            <legend>Muat Naik Dokumen</legend>
            <p>
                <label> ID Permohonan :</label>
                <stripes:text name="permohonan.idPermohonan" size="34" maxlength="20" id="idPermohonan" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>        
            <p>
                <label>&nbsp;</label>
                <stripes:submit name="search" value="Cari"  onclick="doSearch(this.form, this.name);" class="btn"/>
                <stripes:submit name="reset" value="Isi Semula" class="btn" onclick=""/>&nbsp;
            </p>
            <br/>
        </fieldset>
    </stripes:form>
    </div>
</body>
</html>
     
