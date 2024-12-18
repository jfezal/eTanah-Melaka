<%-- 
    Document   : tindakanAduan
    Created on : Oct 9, 2013, 12:05:07 AM
    Author     : nurashidah
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">

    function showMaklumBalas() {
        if ($('#status').val() == '3') {
            document.getElementById("maklumBalas").style.visibility = 'visible';
            document.getElementById("maklumBalas").style.display = '';
            $('#maklumBalas').show();

            $('#maklumBalas').attr("disabled", false);
        } else {
            $('#maklumBalas').hide();
            document.getElementById("maklumBalas").style.visibility = 'hidden';
            document.getElementById("maklumBalas").style.display = 'none';
            $('#maklumBalas').attr("disabled", true);

        }
    }

    function validateForm() {

        if (document.tindakanAduanForm.status.value == "")
        {
            alert('Sila pilih status.');
            $('#status').focus();
            return false;
        }

        if (document.tindakanAduanForm.status.value == "3" && document.tindakanAduanForm.maklumBalas.value == "")
        {
            alert('Sila masukkan Maklum Balas.');
            $('#maklumBalas').focus();
            return false;
        }

        return true;
    }

    function test(f) {
        $(f).clearForm();
    }


</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>

<s:form beanclass="etanah.view.uam.TindakanAduanActionBean" name="tindakanAduanForm">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Aduan</legend>
            <br>
            <table>
                <s:hidden name="idAduan" id="idAduan" value="${actionBean.idAduan}"/>
                <tr>
                    <td><label for="nama">Nama Pengadu:</label></td>
                    <td>${actionBean.aduanPortal.nama}</td>
                    </td>
                </tr>
                <tr>
                    <td><label for="noTelefon">No. Telefon:</label></td>
                    <td>${actionBean.aduanPortal.noTelefon}</td>
                </tr>
                <tr>
                    <td><label for="noPengenalan">No. Kad Pengenalan:</label></td>
                    <td>${actionBean.aduanPortal.noPengenalan}</td>
                </tr>
                <tr>
                    <td><label for="email">Email:</label></td>
                    <td>${actionBean.aduanPortal.email}</td>
                </tr>
                <tr>
                    <td><label for="jenis">Jenis Aduan:</label></td>
                    <td>${actionBean.aduanPortal.kodAduanPortal.jenisAduan}</td>
                </tr>
                <tr>
                    <td><label for="nama">Tarikh Aduan:</label></td>
                    <td>${actionBean.aduanPortal.tarikhAduan}</td>
                </tr>
                <tr>
                    <td><label for="penerangan">Penerangan Aduan:</label></td>
                    <td>${actionBean.aduanPortal.penerangan}</td>
                </tr>
                <c:if test="${actionBean.aduanPortal.kodStatusPortal.status ne null}">
                    <tr>
                        <td><label for="statusCur" name="statusCur">Status :</label></td>
                        <td>${actionBean.aduanPortal.kodStatusPortal.status}</td>
                    </tr>
                </c:if> 
            </table>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Tindakan
            </legend>
            <p>
                <font color="red">PERINGATAN :</font>  Sila Masukkan Tindakan Bagi Aduan. Bertanda <font color="red">*</font> mandatori.
            </p>
            <p>
                <br> <label><font color="red">*</font>Tindakan :</label>
                    <s:select name="status" id="status" onchange="showMaklumBalas()">
                        <s:option value=""> Sila Pilih </s:option>
                    <c:forEach items="${actionBean.senaraiStatus}" var="line">
                        <s:option value="${line.kod}">${line.status}</s:option>
                    </c:forEach>
                </s:select>
            </p>
            <p id="maklumBalas" style="visibility: hidden; display: none">
                <label>Maklum Balas :</label>            
                <s:textarea name="maklumBalas" id="maklumBalas" rows="10" cols="20"/> 


            </p>
            <p align="center">
                <s:submit name="simpan" id="simpan" value="Simpan" class="btn" onclick="return validateForm();"/>
                <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
            </p>
        </s:form>