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
            <legend>Senarai Blok Sementara</legend>
          <br>




            <display:table  class="tablecloth" name=""  id="line">

                <display:column property="nama" title="Blok Sementara" class=""/>
                <display:column property="" title="Jumlah Tingkat"/>
                <display:column property="" title="Jumlah Petak" class=""/>
                <display:column property="" title="Jumlah Syer"/>
                <display:column property="" title="Tarikh Siap"/>

            </display:table>


            <s:button name="Baiki" value="Baiki" class="btn"/>



     </fieldset>
     
    </div>
    <br>

     
 

         <label>&nbsp;</label>
              <a href="urusan?showForm22"<s:submit name="Simpan" value="Simpan" class="btn"/></a>
               <a href=""<s:submit name="Kembali" value="Kembali" class="btn"/></a>
                 <a href=""<s:submit name="Keluar" value="Keluar" class="btn"/></a>

    
        </s:form>