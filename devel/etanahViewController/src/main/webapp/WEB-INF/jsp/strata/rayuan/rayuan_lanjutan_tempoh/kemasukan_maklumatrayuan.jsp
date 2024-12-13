<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
KEMASUKAN MAKLUMAT PERMOHONAN RAYUAN PERLANJUTAN TEMPOH MEMOHON PECAH BAHAGI BANGUNAN(Borang 5)
<s:form beanclass="etanah.view.strata.rayuan">
   
 <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Penyerah</legend>
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
              <a href="rayuan?showForm68"<s:submit name="Terus" value="Terus" class="btn"/></a>
                <a href=""<s:submit name="isisemula" value="Isi Semula" class="btn"/></a>

            </p>
       </fieldset>
    </div>

    </s:form>