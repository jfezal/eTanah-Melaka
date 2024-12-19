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
            <legend>Maklumat Pemohon</legend>
            <p>
                <label>Nama :</label> <s:text name="nama_pemohon" ></s:text>

            </p>
            <p>
                <label>Jenis Pengenalan :</label> <s:text name="jenis_pengenalan" ></s:text>

            </p>
            <p>
                <label>Nombor Pengenalan :</label> <s:text name="no_pengenalan" ></s:text>

            </p>
             <p>
                <label>Alamat :</label><s:text name="alamat" ></s:text>

            </p><p><label>&nbsp;</label>
            <s:text name="alamat1" ></s:text>
             </p><p><label>&nbsp;</label>
            <s:text name="alamat2" ></s:text>
             </p><p>
                 
                <label>Poskod :</label>  <s:text name="alamat2" ></s:text>

            </p>
             <p>
                 <label>Negeri :</label> <s:select name="negeri" ></s:select>
              </p>
               <p>
                 <label>Bandar :</label> <s:select name="bandar" ></s:select>
              </p><p>
             <label>Nombor Telefon :</label> <s:text name="no_telefon" ></s:text>
</p>
<p>
             <label>Nombor Telefon Pejabat :</label> <s:text name="no_pejabat" ></s:text> &nbsp; Samb :<s:text name="samb" ></s:text>
</p><p>
             <label>Nombor Telefon Bimbit :</label> <s:text name="no_bimbit" ></s:text>
</p>
     <p>
             <label>Nombor Faks :</label> <s:text name="no_faks" ></s:text>
</p>  <p>
             <label>Email :</label> <s:text name="email" ></s:text>
</p>   <br>
                 <p>
                <label>&nbsp;</label>
                <a href="rayuan?showForm59" <s:submit name="Terus" value="Terus" class="btn"/></a>
                <a href="" <s:submit name="isisemula" value="Isi Semula" class="btn" /></a>

            </p></fieldset>
    </div>




    </s:form>