<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Pertanyaan SB/SW/SA</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />
    </head>
    <body>
        <stripes:messages />
        <stripes:errors />
        <!-- UTILITI PENDAFTARAN -->
        <stripes:form action="/daftar/pertanyaanSBSWSA">
            <fieldset class="aras1">
                <legend>Permohonan SB/SW/SA </legend><br>
                <p>
                    <label for="idPermohonan">Id Permohonan SA :</label>
                    <input type="text" name="permohonanSA" id="idPermohonanSA" onchange="document.getElementById('idPermohonanSB').value = '',document.getElementById('idPermohonanSW').value = '',this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label for="idPermohonan">Id Permohonan SB  :</label>
                    <input type="text" name="permohonanSB" id="idPermohonanSB" onchange="document.getElementById('idPermohonanSA').value = '',document.getElementById('idPermohonanSW').value = '',this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label for="idPermohonan">Id Permohonan SW :</label>
                    <input type="text" name="permohonanSW" id="idPermohonanSW" onchange="document.getElementById('idPermohonanSA').value = '',document.getElementById('idPermohonanSB').value = '',this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <input name="checkPermohonan" value="Seterusnya" class="btn" type="submit"/>
                </p><br>
            </fieldset>
            <fieldset class="aras1">
                <legend>Cari SB/SW/SA melalui nama</legend><br>
                <p>
                    <label for="idPermohonan">Nama Pemberi/Penerima :</label>
                    <input type="text" name="nama" id="nama" onKeyUp="this.value=this.value.toUpperCase();"/>
                </p>                
                <p>
                    <label>&nbsp;</label>
                    <input name="searchByName" value="Seterusnya" class="btn" type="submit"/>
                </p><br>
            </fieldset>
        </stripes:form>
    </div>
</body>
</html>