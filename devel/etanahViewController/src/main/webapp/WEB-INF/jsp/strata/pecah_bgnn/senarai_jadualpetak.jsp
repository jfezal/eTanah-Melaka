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
            <legend>Senarai Jadual Petak-petak Bagi Bangunan</legend>
            <p>
                Sila Pilih Tingkat Yang Tidak Mempunyai Petak
                </p><br>
                    <p> <label>&nbsp;</label>
                         <display:table class="tablecloth" name=""  id="line">
                  <display:column title="Pilih" class="bil" group=""></display:column>
                <display:column title="Bil" class="bil" group=""></display:column>
                <display:column property="nama" title="Nama Bangunan" class="nama"/>
                <display:column property="senarai_tgkt" title="Senarai Tingkat"/>
    

            </display:table>  
               </p>
            <br>
            <label>&nbsp;</label>
              <a href="urusan?showForm13"<s:submit name="Terus" value="Terus" class="btn"/></a>
               <a href="urusan?showForm12"<s:submit name="Kembali" value="Kembali" class="btn"/></a>
                 <a href="urusan?showForm10"<s:submit name="Keluar" value="Keluar" class="btn"/></a>

           
       </fieldset>
    </div>
    </s:form>