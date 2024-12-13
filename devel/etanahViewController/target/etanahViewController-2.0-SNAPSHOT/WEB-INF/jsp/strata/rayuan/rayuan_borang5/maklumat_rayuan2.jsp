<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
KEMASUKAN MAKLUMAT PERMOHONAN RAYUAN MEMINDA PERMOHONAN HAKMILIK STRATA(Borang 5)
<s:form beanclass="etanah.view.strata.rayuan">
   
 
 <div class="subtitle">
        <fieldset class="aras1">

            <legend>Harta Bersama (Jika Ada)</legend>
            <p>

                <label>Luas (m.p) :</label>

            </p><br>
             <p>

                <label>Unit Syer :</label>

            </p><br>
            <p>

                <label>Tujuan :</label><s:textarea name="tujuan" cols="70" rows="7"></s:textarea>

            </p>

            </fieldset>
    </div>
<br>
   <div class="subtitle">
        <fieldset class="aras1">
            <legend>Pelan Pecah Bahagi Petak</legend>
            <br>
            <p>
           <label>No Petak :</label>
            </p>
                    <br>   
             <p>
           <label>Imej Plan :</label> <a href=""<s:button name="Imbas" value="Imbas" class="btn"/></a>
            </p>
            <br>
    
        </fieldset>
    </div>

              
  <label>&nbsp;</label>
              <a href="rayuan?showForm24"<s:submit name="Terus" value="Terus" class="btn"/></a>
                <a href=""<s:submit name="Kembali" value="Kembali" class="btn"/></a>
                 <a href=""<s:submit name="Keluar" value="Keluar" class="btn"/></a>
    </s:form>