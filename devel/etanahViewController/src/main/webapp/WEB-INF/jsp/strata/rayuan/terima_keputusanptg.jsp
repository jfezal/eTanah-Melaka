<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
PENERIMAAN KEPUTUSAN PTG 
<s:form beanclass="etanah.view.strata.rayuan">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Tanah</legend>
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
              </p>
             <p>


        </fieldset>
    </div>
<br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Asas Permohonan</legend>
             <br><p>
               <label>Jumlah Bayaran Proses Pemohonan (RM) :</label>&nbsp;

            </p><br>
            <p>
               <label>Status Cukai Tanah :</label> 

            </p> <br>
            <p>
               <label>Lokasi :</label>

            </p><br>
             <p>
                <label>Bilangan Bangunan :</label>

                </p><br>
                <p>
                <label>Jenis Pembinaan Bangunan :</label> 

            </p><br>
             <p>
                 <label>Kos Bangunan/Bangunan-Bangunan (RM) :</label>&nbsp;
              </p><br>
               <p>
                 <label>Bilangan Tingkat :</label>
              </p><br>
              <p>
             <label>Bilangan Petak :</label>
</p><br>
<p>
             <label>Bilangan Bilik Dalam Setiap Petak :</label> 
</p><br><p>
             <label>Harga Jualan Petak (RM) :</label>
</p><br>
     <p>
             <label>Nama Pemaju :</label> 
</p> <br> <p>
             <label>Nama Projek :</label> 
</p>  <br>  <p>
                <label>Tarikh Bangunan Disiapkan :</label>
                                        </p<br> <p>
             <label>Nombor Sijil Layak Menduduki :</label>
</p> <br> <p>
                <label>Tarikh Sijil Layak Menduduki Dikeluarkan :</label>&nbsp;
              
                        </p> 
                 <p>
            </p></fieldset>
    </div>


<br>


 <div class="subtitle">
        <fieldset class="aras1">
             <legend>Keputusan PTG</legend>
            <p>
                <label for="syor">Keputusan PTG : </label>
               
            </p>
         
             <p><br>
 <label>&nbsp;</label>
             <a href="urusan?showForm5" <s:submit name="Hantar" value="Hantar" class="btn"/></a>
                <a href="urusan?showForm4" <s:submit name="Keluar" value="Keluar" class="btn" /></a>
            </p>
        </fieldset>
    </div>

    </s:form>