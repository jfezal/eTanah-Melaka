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

        <stripes:form action="/daftar/batal_perserahan">
            <stripes:hidden name= "kodNegeri"/>
            <fieldset class="aras1">

                <legend>Permohonan Batal Perserahan</legend>
                <br>
                <div class="instr-fieldset">
                    <font color="red">PERINGATAN:</font>Sila Masukan Salah Satu Maklumat Berikut
                </div>&nbsp;

                <p><label for="idPermohonan">Id Permohonan/ID Perserahan :</label>
                    <input type="text" name="permohonan.idPermohonan" id="idPermohonan" />
                </p>
                <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                    <label>&nbsp;&nbsp;</label>
                    <p>
                        <font color="red">ATAU</font>
                    </p>
                    <p>
                        <label for="noAkaun">No Akaun :</label>
                        <input type="text" name="akaunKredit.noAkaun" />
                    </p>
                </c:if>
                     <label>&nbsp;&nbsp;</label>
                <p>
                      <font color="red">ATAU</font>
                </p>
                <p><label>No Resit :</label>
                    <input type="text" name="dokumenKewangan.idDokumenKewangan"/>

                </p>

                <p>
                    <label>&nbsp;</label>
                    <stripes:submit name="checkPermohonan" value="Seterusnya" class="btn" />
                </p>

            </fieldset>

        </stripes:form>

    </div>
</body>
</html>