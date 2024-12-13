<%-- 
    Document   : semakanPermohonan
    Created on : May 13, 2011, 11:52:22 AM
    Author     : latifah.iskak
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />

        <script type="text/javascript">            
            function clearForm(f) {
                $('#idPermohonan').val('');
            }
            
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
        </script>
    </head>
    <body>

        <stripes:messages />
        <stripes:errors />

        <p></p>
        <stripes:form action="/strata/status_permohonan" id="main_kesinambungan">

            <fieldset class="aras1">

                <legend>Status Permohonan Strata</legend>
                <br>
                <p><label for="idPermohonan">Id Permohonan :</label>
                    <input type="text" name="permohonan.idPermohonan" size="34" maxlength="20" id="idPermohonan" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>

                <p>
                    <label>&nbsp;</label>
                    <%--<stripes:submit name="checkPermohonan" value="Seterusnya" class="btn" />--%>
                    <stripes:submit name="checkPermohonan" value="Cari" onclick="doSearch(this.form, this.name);" class="btn"/>
                    <stripes:submit name="reset" value="Isi Semula" class="btn" onclick=""/>&nbsp;

                </p>
                <br/>
            </fieldset>
        </stripes:form>
    </body>
</html>
