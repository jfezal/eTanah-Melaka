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


    <div class="subtitle">
        <fieldset class="aras1">

            <legend>Maklumat Bangunan</legend>
            <p>

                <label>Nama Bangunan :</label>

            </p><br>
             <p>

                <label>Kategori Bangunan :</label>

            </p><br>
            
            </fieldset>
    </div>

     <div class="subtitle">
        <fieldset class="aras1">

            <legend>Maklumat Petak</legend>
            <p>

                <label>Bilangan Petak :</label>

            </p><br>
             <p>

                <label>Bilangan Petak Aksesori :</label>

            </p><br>
             <p>
                <label>Jumlah Unit Syer :</label>

            </p><br>

            </fieldset>
    </div>
<br>

   <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Rayuan</legend>
            <br>
             <legend>Maklumat Asal</legend>
             <p>

                <label>Jumlah Petak :</label>

            </p><br>
            <p>
      
                         <display:table class="tablecloth" name=""  id="line">
                  <display:column title="Bangunan" ></display:column>
                <display:column title="Senarai Tingkat-tingkat Dalam Setiap Bangunan"></display:column>
                <display:column title="Senarai Petak-petak Dalam Setiap Bangunan" />
                <display:column title="Unit-unit Syer Bagi Setiap Petak/Blok Semnetara"/>
                <display:column title="Petak Aksesori"/>

            </display:table>

               </p>
             <br>
             <legend>Senarai Petak Aksesori</legend>
             <p>
                  <display:table  class="tablecloth" name=""  id="line">
                 <display:column title="Bil" class="bil" group=""></display:column>
                <display:column property="" title="Petak Aksesori" />
                <display:column property="" title="Lokasi Petak Aksesori"/>
                <display:column property="" title="Kegunaan Petak Aksesori" />
                <display:column property="" title="Petak Aksesori ada bersangkutan"/>
                  </display:table>
                 </p>

                  <br>
             <legend>Senarai Petak Aksesori</legend>
             <p>
                 <display:table  class="tablecloth" name=""  id="line">

                <display:column property="nama" title="Blok Sementara" class=""/>
                <display:column property="" title="Jumlah Tingkat"/>
                <display:column property="" title="Jumlah Petak" class=""/>
                <display:column property="" title="Jumlah Syer"/>
                <display:column property="" title="Tarikh Siap"/>

            </display:table>
                 </p>

                 <p><br>
                <label>&nbsp;</label>
              <a href="rayuan?showForm4"<s:submit name="Terus" value="Terus" class="btn"/></a>
                <a href="rayuan?showForm1"<s:submit name="Kembali" value="Kembali" class="btn"/></a>
                 <a href="rayuan?showForm1"<s:submit name="Keluar" value="Keluar" class="btn"/></a>
            </p>
        </fieldset>
    </div>

    </s:form>