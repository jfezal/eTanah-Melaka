<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


KEMASUKAN MAKLUMAT PERMOHONAN PECAH BAHAGI BANGUNAN (SEKSYEN 10 Akta Hakmilik Strata)
<s:form beanclass="etanah.view.strata.rayuan">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Jadual Petak-petak</legend>
            <p>
                <label>ID Bangunan :</label>

            </p><br>
                    <p>
                        <label>ID Tingkat :</label> <s:text name="id_tgkt" ></s:text>

            </p>
            <p>
                <label>*Nama Lain Bagi Tingkat :</label><s:select name="nama_lain" ></s:select>

            </p>
            <p>
                 <label>*Bilangan Petak Bagi Tingkat :</label> <s:text name="bil_petak" ></s:text>

            </p>
            <p></p><br>
       </fieldset>
    </div>
                <br>
            <label>&nbsp;</label>
              <a href="rayuan?showForm8"<s:submit name="Terus" value="Terus" class="btn"/></a>
                 <a href="rayuan?showForm10"<s:submit name="Keluar" value="Keluar" class="btn"/></a>

 

    </s:form>