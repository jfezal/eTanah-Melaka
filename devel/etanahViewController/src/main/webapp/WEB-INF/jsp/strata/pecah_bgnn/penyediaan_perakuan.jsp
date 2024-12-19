<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
PENYEDIAAN KERTAS PERAKUAN
<s:form beanclass="etanah.view.strata.urusan">
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
            <legend>Maklumat Pemohon</legend>
           <p>
            <label>&nbsp;</label>
                         <display:table class="tablecloth" name=""  id="line">
                  <display:column title="Nama" class="nama" group=""></display:column>
                <display:column title="Jenis Pengenalan" group=""></display:column>
                 <display:column title="No Pengenalan/No Syarikat"/>
             </display:table>

               </p>


        </fieldset>
    </div>

 <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Asas Permohonan</legend>
             <br><p>
               <label>Jumlah Bayaran Proses Pemohonan (RM) :</label>&nbsp;

            </p><br>
            <p>
               <label>Status Cukai Tanah :</label>

            </p><br>
            <p>
             <label>Nama Pemaju :</label>

            </p><br>
            <p>
               <label>Nyatakan :</label>

            </p><br>
             <p>
             <label>Nama Projek :</label>

             </p><br>
                <p>
                <label>Jenis Kos Rendah :</label>

                </p><br>
                <p>
                <label>Nama Perbadanan Pengurusan :</label>
                </p>
                <br>
             <p>
             <label>Nombor Sijil Layak Menduduki :</label>
            </p>
            <br>
               <p>
                <label>Tarikh Sijil Layak Menduduki Dikeluarkan :</label>&nbsp;
               </p><br>
              <p>
             <label>Tujuan Permohonan :</label>
                </p><br>
                <p>
             <label>Kegunaan Bangunan :</label>
                </p>

                 </fieldset>
    </div>

<br>
               <p> <label>&nbsp;</label>
              <a href="urusan?showForm31"<s:submit name="Terus" value="Terus" class="btn"/></a>
                        </p>


    </s:form>