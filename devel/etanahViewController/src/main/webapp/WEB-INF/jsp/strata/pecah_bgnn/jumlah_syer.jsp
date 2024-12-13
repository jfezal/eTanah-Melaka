<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

KEMASUKAN MAKLUMAT PERMOHONAN 
<s:form beanclass="etanah.view.strata.urusan">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Jumlah Keseluruhan Syer</legend>
         <p>
                <label>Jumlah Syer Bangunan(M) :</label>

            </p> <br>
            <p>
                <label>Jumlah Syer Blok Sementara(P) :</label>

            </p> <br>
            <p>
                <label>Jumlah Keseluruhan Syer :</label>

            </p> <br>

     </fieldset>
     
    </div>
    <br>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Surat Kebenaran</legend>
         <p>
                <display:table  class="tablecloth" name=""  id="line">
                 <display:column title="Pilih" class="Pilih" group=""></display:column>
                <display:column property="" title="Pemberi Kebenaran" />
                <display:column property="" title="Nombor Pengenalan"/>
                <display:column property="" title="Alamat" />
                <display:column property="" title="Kepentingan"/>
                  </display:table>

            </p> <br>

     </fieldset>

    </div>
 
<br>
<div class="subtitle">
        <fieldset class="aras1">
            <legend>Permit Ruang Udara</legend>
         <p>
                <display:table  class="tablecloth" name=""  id="line">
                 <display:column title="Pilih" class="Pilih" group=""></display:column>
                  <display:column property="senarai_tgkt" title="Senarai Tingkat"/>
                   <display:column property="senarai_petak" title="Senarai petak"/>
                <display:column property="" title="Kegunaan Ruang Udara" />
                <display:column property="" title="Isipadu Ruang Udara(meterpadu)"/>
                <display:column property="" title="Tempoh Permit" />
                <display:column property="" title="Tarikh Luput Permit"/>
                  </display:table>

            </p> <br>

     </fieldset>

    </div>
         <label>&nbsp;</label>
              <a href="urusan?showForm23"<s:submit name="Simpan" value="Simpan" class="btn"/></a>
               <a href=""<s:submit name="Kembali" value="Kembali" class="btn"/></a>
                 <a href=""<s:submit name="Keluar" value="Keluar" class="btn"/></a>

    
        </s:form>