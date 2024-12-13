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

            <legend>Maklumat Tanah</legend>
            <p>

                <label>ID Hakmilik :</label>

            </p><br>
             <p>

                <label>Nama Pemilik Tanah :</label>

            </p><br>
             <p>
                <label>Negeri :</label>

            </p><br>
             <p>
                <label>Daerah :</label>

            </p><br>
            <p>
                <label>Bandar/Pekan/Mukim :</label>

            </p><br>
             <p>
                <label>Nombor Lot :</label>

            </p><br>
              <p>
                <label>Nombor Hakmilik :</label>
              </p><br>

              <p>
                <label>Luas :</label>

            </p><br>
           
            </fieldset>
    </div>
<br>

    <div class="subtitle">
        <fieldset class="aras1">

            <legend>Maklumat Permohonan Asal</legend>
            <p>

                <label>Nombor Petak :</label>

            </p><br>
             <p>

                <label>Luas :</label>

            </p><br>
            <p>

                <label>Unit Syer :</label>

            </p><br>
            
            </fieldset>
    </div>
<br>
     <div class="subtitle">
        <fieldset class="aras1">

            <legend>Maklumat Pecah Bahagi Bangunan</legend>
            <p>

                <label>Bilangan Petak Baru :</label>

            </p><br>
            <label>&nbsp;</label>
              <display:table  class="tablecloth" name=""  id="line">

                <display:column property="nama" title="Petak Baru" class=""/>
                <display:column property="" title="Luas Petak(m.p)"/>
                <display:column property="" title="Unit Syer" class=""/>
                <display:column property="" title="Petak Aksesori"/>

            </display:table>
            <br>

            </fieldset>
    </div>
<br>
 <div class="subtitle">
        <fieldset class="aras1">

            <legend>Harta Bersama</legend>
            <p>

                <label>Luas (m.p) :</label>

            </p><br>
             <p>

                <label>Unit Syer :</label>

            </p><br>
            <p>

                <label>Tujuan :</label>

            </p><br>

            </fieldset>
    </div>
<br>
   <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Berkaitan Yang Ingin Diubah</legend>
            <br>
           <p>
                   <s:radio name="no_petak" value="no_petak_br" /> Nombor Petak Baru
                    <s:radio name="no_petak" value="no_petak_asal" /> Nombor Petak Asal
                    </p>

                    <br>   
              
    
        </fieldset>
    </div>

                    <br>
   <div class="subtitle">
        <fieldset class="aras1">

            <legend>Maklumat Rayuan</legend>
            <p>
                <legend>Maklumat Pecah Bahagi Petak Asal</legend>
                <br>
                <label>No Petak Baru :</label><s:text name="no_petak_baru" ></s:text>

            </p><br>

            </fieldset>
    </div>
                <br>
  <label>&nbsp;</label>
              <a href="rayuan?showForm24"<s:submit name="Terus" value="Terus" class="btn"/></a>
                <a href=""<s:submit name="Kembali" value="Kembali" class="btn"/></a>
                 <a href=""<s:submit name="Keluar" value="Keluar" class="btn"/></a>
    </s:form>