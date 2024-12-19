<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

 $(document).ready(function() {

      $('#ptanyaan').hide();
      

  });

 function change(value){

        if(value == "ada")
           $('#ptanyaan').show();
           
              
        if(value == "tidak")
           $('#ptanyaan').hide();
                
 }




</script>
RUJUKAN KE JUPEM BAGI PERMOHONAN PECAH BAHAGI BANGUNAN
<s:form beanclass="etanah.view.strata.urusan">

   <div class="subtitle">
        <fieldset class="aras1">
            <legend>Endosan Permohonan</legend>
            <p>
                 </p>
                 
                    <p> <label>Permohonan Telah Diendos Dalam Hakmilik :</label>

               </p>
            <br>
     </fieldset>
    </div>
<br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Rujukan Ke JUPEM</legend>
            <p>
                 </p>

                    <p> <label>Jenis Kos Rendah :</label>

               </p><br>
                <p> <label>Hakmilik Kekal :</label>

               </p>
            <br><br><label>&nbsp;</label>
                             <s:submit name="Tambah" value="Tambah" class="btn"/>
                            <s:submit name="Hapus" value="Hapus" class="btn"/>
                            <s:submit name="PaparSurat" value="Papar Surat" class="btn"/><br> <p>

                                <label>&nbsp;</label>
                 <display:table class="tablecloth" name=""  id="line">
               <display:column title="Pilih" class="bil" group=""></display:column>
                <display:column title="Bil" class="bil" group=""></display:column>
                <display:column  title="Butiran" />


            </display:table>
               </p>
     </fieldset>
    </div>
<br>

<div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Deposit</legend>
            <p>
                 </p>

                    <p> <label>Tarikh Notis :</label>

               </p><br>
                <p> <label>Tempoh Bayaran :</label>

               </p><br>
                 <p> <label>Tarikh Akhir Bayaran :</label>

               </p><br>
                <p> <label>Deposit Perlu Dibayar(RM) :</label>

               </p>
            <br>
                 <label>&nbsp;</label>
                 <s:submit name="PaparNotis" value="Papar Notis" class="btn"/>
                 <br>
                 <p>

                                <label>&nbsp;</label>
                 <display:table class="tablecloth" name=""  id="line">
               <display:column title="Blok Sementara" ></display:column>
                <display:column title="Jumlah Tingkat" ></display:column>
                <display:column  title="Jumlah Petak" />
                 <display:column  title="Jumlah Syor" />
                 <display:column  title="Tarikh Siap" />
                 <display:column  title="Amaun Deposit (RM)" />
            </display:table>
               </p>
                               
                 

     </fieldset>
    </div>
      <br>
      <div class="subtitle">
        <fieldset class="aras1">
            <legend>Pertanyaan Kepada Pemohon</legend>
            <p>
            <legend>Pertanyaan</legend>
                 </p><br>
<p>
                   <s:radio name="tanya" value="ada" onchange="javaScript:change(this.value)"/>Ada
                    <s:radio name="tanya" value="tidak" onchange="javaScript:change(this.value)"/>Tidak
                    </p>
            <br>
           <label>Tempoh Surat :</label>

               <br>

               <label>&nbsp;</label>
                             <s:submit name="Tambah" value="Tambah" class="btn"/>
                            <s:submit name="Hapus" value="Hapus" class="btn"/>
                            <s:submit name="PaparSurat" value="Papar Surat" class="btn"/><br> 
            
     </fieldset>
    </div>
                            <br>

     <div id="ptanyaan" class="subtitle">
        <fieldset class="aras1">
           <p>

                                <label>&nbsp;</label>
                 <display:table class="tablecloth" name=""  id="line">
               <display:column title="Pilih" class="bil" group=""></display:column>
                <display:column title="Bil" class="bil" group=""></display:column>
                <display:column  title="Butiran Pertanyaan" />


            </display:table>
               </p>
     </fieldset>
    </div>
                            <br>
<label>&nbsp;</label>
            <s:submit name="Simpan" value="Simpan" class="btn"/>
                <s:submit name="Hantar" value="Hantar" class="btn"/>
                <s:submit name="IsiSemula" value="Isi Semula" class="btn"/>
                <s:submit name="Keluar" value="Keluar" class="btn"/>
</s:form>
