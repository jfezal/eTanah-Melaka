<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
KEMASUKAN MAKLUMAT PERMOHONAN RAYUAN MEMINDA PERMOHONAN HAKMILIK STRATA(Borang 1,5,6&7)
<s:form beanclass="etanah.view.strata.rayuan">
   
<div class="subtitle">
        <fieldset class="aras1">
            <legend>Senarai Semak Dokumen Yang Disertakan</legend>
            <p>
                 </p><br>
                    <p> <label>&nbsp;</label>
                         <display:table class="tablecloth" name=""  id="line">
                <display:column title="No" class="no" group=""></display:column>
                  <display:column title="Dokumen" class="dokumen" group=""></display:column>
                <display:column title="Catatan" class="catatan" group=""></display:column>
                <display:column property="imbas" title="Imbas" class="Imbas"/>
                <display:column property="id_dokumen" title="ID Dokumen"/>
    

            </display:table>  
               </p>
            <br>
            

           
       </fieldset>
    </div>

                 <br>
                 <div class="subtitle">
        <fieldset class="aras1">
            <legend>Senarai Semak Tambahan</legend>
            <p>
               </p><br>
                    <p> <label>&nbsp;</label>
                        <display:table class="tablecloth" name=""  id="line">
                <display:column title="No" class="no" group=""></display:column>
                  <display:column title="Dokumen" class="dokumen" group=""></display:column>
                <display:column title="Catatan" class="catatan" group=""></display:column>
                <display:column property="imbas" title="Imbas" class="Imbas"/>
                <display:column property="id_dokumen" title="ID Dokumen"/>


            </display:table>
               </p>
            <br>
            <label>&nbsp;</label>
            
               <a href=""<s:submit name="Hantar" value="Hantar" class="btn"/></a>
                <a href="urusan?showForm12"<s:submit name="Kembali" value="Kembali" class="btn"/></a>
                 <a href=""<s:submit name="Keluar" value="Keluar" class="btn"/></a>

           
       </fieldset>
    </div>
    </s:form>