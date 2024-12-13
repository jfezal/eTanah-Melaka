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
            <legend>Maklumat Jadual Petak-petak</legend>
            <p>
                <label>Jumlah Bangunan :</label>

            </p><br>
                    <p>
                        <label>Jumlah Petak :</label>

            </p><br>
            <p>
                <label>Jumlah Petak Aksesori :</label>

            </p>
            <br>
       </fieldset>
    </div>
<br>
<div class="subtitle">
        <fieldset class="aras1">
            <legend>Senarai Jadual Petak-petak</legend>
            <p>
                </p><br>
                  <display:table  class="tablecloth"   name=""  id="line">
                 <display:column title="Bil" class="bil" group=""></display:column>
                <display:column property="nama" title="Nama Bangunan" class="nama"/>
                <display:column property="senarai_tgkt" title="Senarai Tingkat"/>
                <display:column property="senarai_petak" title="Senarai Petak" class="senarai_petak"/>
                <display:column property="Unit_syer" title="Unit-unit Syer"/>
                 <display:column property="luas_petak" title="Luas Petak (meterpersegi)" class="luas_petak"/>
                <display:column property="kegunaan_petak" title="Kegunaan Petak"/>
                 <display:column property="Petak_Aksesori" title="Petak Aksesori"/>
                  <display:column property="Petak_Aksesori" title="Petak Aksesori Bersangkutan"/>

            </display:table>

<br>
               </p>
    </div>
              <label>&nbsp;</label>
              <a href="urusan?showForm17"<s:submit name="Terus" value="Terus" class="btn"/></a>
               <a href="urusan?showForm12"<s:submit name="Kembali" value="Kembali" class="btn"/></a>
                 <a href="urusan?showForm10"<s:submit name="Keluar" value="Keluar" class="btn"/></a>


       </fieldset>

    </s:form>