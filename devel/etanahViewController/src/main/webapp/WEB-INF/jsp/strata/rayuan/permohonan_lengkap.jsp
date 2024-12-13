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
      $('#semak').hide();

  });

 function change(value){

        if(value == "ada")
            {
           $('#ptanyaan').hide();
           $('#semak').show();
            }

        if(value == "tidak")
            {
           $('#ptanyaan').show();
            $('#semak').hide();
           }
 }




</script>

PENYEDIAAN KERTAS PERAKUAN
<s:form beanclass="etanah.view.strata.rayuan">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Status Permohonan</legend>
            <p>
                <label>Permohonan Lengkap :  </label>

            </p><br>
                 <p>
                   <s:radio name="tanya" value="ada" onchange="javaScript:change(this.value)"/>Ada
                    <s:radio name="tanya" value="tidak" onchange="javaScript:change(this.value)"/>Tidak
                    </p>
        </fieldset>
    </div>
    <br>

     <div id="ptanyaan" class="subtitle">
        <fieldset class="aras1">
            <legend>Pertanyaan Kepada Pemohon</legend>
             <label>&nbsp;</label>
                             <s:submit name="Tambah" value="Tambah" class="btn"/>
                            <s:submit name="Hapus" value="Hapus" class="btn"/>
                            <s:submit name="PaparSurat" value="Papar Surat" class="btn"/><br> 
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
            <div id="semak" class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Semakan</legend>
              <p>
               Saya telah menyemak dan meneliti permohonan ini dan ianya adalah teratur dan telah memenuhi kehendak-kehendak Sek. 28 Akta Hakmilik Strata 1985 dan diperlakukan untuk: 
              </p>
     </fieldset>
    </div>
          <label>&nbsp;</label>
              <a href="rayuan?showForm17"<s:submit name="Simpan" value="Simpan" class="btn"/></a>
               <a href=""<s:submit name="Hantar" value="Hantar" class="btn"/></a>
                <a href=""<s:submit name="Kembali" value="Kembali" class="btn"/></a>
                


        </s:form>