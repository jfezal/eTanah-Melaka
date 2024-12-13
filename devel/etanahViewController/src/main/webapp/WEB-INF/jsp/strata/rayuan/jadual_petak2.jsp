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
                <label>ID Menara :</label>

            </p><br>
                    <p>
                        <label>Nama Lain Bagi Menara :</label> <s:text name="nama_lain" ></s:text>

            </p>
            <p>
                <label>*Kegunaan Bangunan :</label><s:select name="kegunaan" ></s:select>

            </p>
            <p>
                 <label>*Bilangan Tingkat Bagi Menara :</label> <s:text name="bil_tgkt" ></s:text>

            </p>
            <p></p><br>
       </fieldset>
    </div>
                <br>
            <label>&nbsp;</label>
              <a href="rayuan?showForm8"<s:submit name="Terus" value="Terus" class="btn"/></a>
                 <a href="rayuan?showForm10"<s:submit name="Keluar" value="Keluar" class="btn"/></a>

 

    </s:form>