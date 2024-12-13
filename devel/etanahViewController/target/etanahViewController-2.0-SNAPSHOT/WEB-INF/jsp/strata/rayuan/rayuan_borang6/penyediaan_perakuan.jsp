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
            <legend>Maklumat Pemohon</legend>
            <p>
                <label>Nama :</label> 
            </p><br>
            <p>
                <label>Jenis Pengenalan :</label> 
            </p><br>
            <p>
                <label>Nombor Pengenalan :</label> 
           </p><br>
             <p>
                <label>Alamat :</label>
            </p><br>
             <p>

                <label>Poskod :</label>  

            </p><br>
             <p>
                 <label>Negeri :</label> 
              </p><br>
               <p>
                 <label>Bandar :</label> 
              </p><br>
              <p>
             <label>Nombor Telefon :</label> 
            </p><br>
            <p>
             <label>Nombor Telefon Pejabat :</label>Samb :
            </p>
            <p>
             <label>Nombor Telefon Bimbit :</label> 
                </p><br>
                 <p>
             <label>Nombor Faks :</label> 
                </p> <br> <p>
             <label>Email :</label> 
                </p>   <br>
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
             <label>Nama Projek :</label>

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
              <a href="rayuan?showForm49"<s:submit name="Terus" value="Terus" class="btn"/></a>
                        </p>


    </s:form>