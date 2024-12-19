<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
KEMASUKAN MAKLUMAT PERMOHONAN RAYUAN MEMINDA PERMOHONAN HAKMILIK STRATA(Borang 1)
<s:form beanclass="etanah.view.strata.rayuan">
   
 <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Penyerah</legend>
            <p>
                <label>Nama/Nama Syarikat :</label>

            </p><br>
           <p>
                <label>Nombor Kad Pengenalan/Nombor Pendaftaran Syarikat :</label>

            </p><br>
             <p>
                <label>Alamat :</label>

            </p><br>
             <p>
                <label>Poskod :</label>

            </p><br>
             <p>
                <label>Bandar :</label>
              </p><br>

              <p>
                <label>Negeri :</label>  </p>
              <br>  
             
              <p><label>&nbsp;</label>
                  <s:checkbox name="pemohon_penyerah"></s:checkbox>
                   Pemohon adalah Penyerah 

                  <p><br>
                <label>&nbsp;</label>
              <a href="rayuan?showForm2"<s:submit name="Terus" value="Terus" class="btn"/></a>
                <a href="rayuan?showForm1"<s:submit name="isisemula" value="Isi Semula" class="btn"/></a>

            </p>
       </fieldset>
    </div>

    </s:form>