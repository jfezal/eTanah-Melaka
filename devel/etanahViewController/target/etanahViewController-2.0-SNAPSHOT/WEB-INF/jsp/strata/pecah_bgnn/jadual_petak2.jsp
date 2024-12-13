<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

KEMASUKAN MAKLUMAT PERMOHONAN 
<s:form beanclass="etanah.view.strata.urusan">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Jadual Petak-petak</legend>
            <p>
                <label>ID Bangunan :</label>

            </p><br>
            <p>
                <label>ID Tingkat :</label>

            </p><br>

                    <p>
                        <label>Nama Lain Bagi Tingkat :</label> <s:text name="nama_lain" ></s:text>

            </p>
             <p>
                        <label>Bilangan Petak Bagi Tingkat :</label> <s:text name="bil_tgkt" ></s:text>

            </p>
           
            <p>
                <label>*Menara :</label><s:radio name="menara" value="ya" onchange="javaScript:changebgnn(this.value)"></s:radio> Ya
                <s:radio name="menara" value="tidak" onchange="javaScript:changebgnn(this.value)"></s:radio> Tidak

            </p>
            <p></p><br>
       </fieldset>
    </div>

    </s:form>