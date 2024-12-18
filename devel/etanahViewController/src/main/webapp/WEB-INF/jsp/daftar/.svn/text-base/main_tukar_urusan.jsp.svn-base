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

    </head>
    <body>

        <stripes:messages />
        <stripes:errors />

        <p></p>
        <!-- UTILITI PENDAFTARAN -->

        <stripes:form action="/daftar/tukar">

            <fieldset class="aras1">

                <legend>Permohonan Tukar Urusan</legend>
                <br>
                <p><label for="idPermohonan">Id Permohonan/ID Perserahan :</label>
                    <input type="text" name="permohonan.idPermohonan" id="idPermohonan" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>

                <p>
                    <label>&nbsp;</label>
                    <stripes:submit name="checkTkr" value="Seterusnya" class="btn" />
                </p>

            </fieldset>

        </stripes:form>


</body>
</html>