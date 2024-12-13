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
KEMASUKAN MAKLUMAT PERMOHONAN RAYUAN PERLANJUTAN TEMPOH MEMOHON PECAH BAHAGI BANGUNAN(Borang 1 & 5)
<s:form beanclass="etanah.view.strata.rayuan">

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
   <label>&nbsp;</label>
            <a href="rayuan?showForm71" <s:submit name="Terus" value="Terus" class="btn"/></a>
                <s:submit name="Kembali" value="Kembali" class="btn"/>
               <s:submit name="Keluar" value="Keluar" class="btn"/>
</s:form>
