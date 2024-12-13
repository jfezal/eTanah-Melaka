<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
KEMASUKAN MAKLUMAT PERMOHONAN RAYUAN MEMINDA PERMOHONAN HAKMILIK STRATA(Borang 7)
<s:form beanclass="etanah.view.strata.rayuan">
   
 <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Penyatuan Petak</legend>
<br>
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
            <p> <label>&nbsp;</label>
                 <display:table  class="tablecloth" name=""  id="line">
                 <display:column title="Bil" class="Bil" group=""></display:column>
                <display:column property="" title="Nombor Petak" />
                <display:column property="" title="Luas Petak (m.p)"/>
                <display:column property="" title="Unit Syer" />
                </display:table>
              

            </p>

<br>
            
            </fieldset>
    </div>
<br>
     <div class="subtitle">
        <fieldset class="aras1">

            <legend>Maklumat Penyatuan Petak</legend>
            <p>
                <label>Bilangan Petak Baru :</label>
            </p><br>
             <p>
                <label>Petak Baru :</label>
            </p><br>
             <p>
                <label>Luas Petak (m.p) :</label>
            </p><br>
             <p>
                <label>Unit Syer :</label>
            </p><br>
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
                <legend>Maklumat Permohonan</legend>
                <br>
                 <label>&nbsp;</label>
               <display:table  class="tablecloth" name=""  id="line">
                 <display:column title="Bil" class="Bil" group=""></display:column>
                <display:column property="" title="Nombor Petak" />
                <display:column property="" title="Luas Petak (m.p)"/>
                <display:column property="" title="Unit Syer" />
                </display:table>
              

            </p><br>

            </fieldset>
    </div>
                <br>

                 <div class="subtitle">
        <fieldset class="aras1">

            <legend>Maklumat Penyatuan Petak Baru</legend>
            <p>
                <label>Bilangan Petak Baru :</label>
            </p><br>
             <p>
                <label>Petak Baru :</label>
            </p><br>
             <p>
                <label>Luas Petak (m.p) :</label>
            </p><br>
             <p>
                <label>Unit Syer :</label>
            </p><br>
                      </fieldset>
    </div>

                <br>

                 <div class="subtitle">
        <fieldset class="aras1">

            <legend>Petak Aksesori Bersangkutan Dengan Petak Baru</legend>
            <p>
               <br>
                 <label>&nbsp;</label>
               <display:table  class="tablecloth" name=""  id="line">
                 <display:column title="Bil" class="Bil" group=""></display:column>
                <display:column property="" title="ID Petak Aksesori" />
                <display:column property="" title="Lokasi"/>
                <display:column property="" title="Kegunaan" />
                </display:table>


            </p><br>

            </fieldset>
    </div>

                <br>
  <label>&nbsp;</label>
              <a href="rayuan?showForm60"<s:submit name="Terus" value="Terus" class="btn"/></a>
                <a href=""<s:submit name="Kembali" value="Kembali" class="btn"/></a>
                 <a href=""<s:submit name="Keluar" value="Keluar" class="btn"/></a>
    </s:form>